using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;

using FAST.Core.Exceptions;

using FAST.Core.Context;

using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;

using FAST.Common.Service.Pojo.Base;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Collections;
using Yss.KTable.Collections;
using Yss.KRichEx.AutoFilter.Model;
using Yss.KTable.Models;
using Yss.KMessage;
using YssProductInfo.Support.Ab.PortGroup.Service;
using YssProductInfo.Support.Ab.PortGroup.Pojo;

namespace YssProductInfo.Ab.PortGroup.Form
{
    /// <summary>
    /// 群组管理列表界面
    /// chenwenhai
    /// 20140516
    /// </summary>
    public partial class Frm_PORT_GROUP_L : FrmBaseList
    {
        /// <summary>
        /// 群组service
        /// </summary>
        private IPortGroupService groupService = null;

        /// <summary>
        /// 群组关联组合service
        /// </summary>
        private IPortGroupRelaService groupRelaService = null;

        /// <summary>
        /// set窗体
        /// </summary>
        private Frm_PORT_GROUP_S frmSet = null;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_PORT_GROUP_L()
        {
            InitializeComponent();
            hasLeftSetForm = true;
            bUseMVCServiceLeft = true;
            bUseMVCService = true;
        }

        /// <summary>
        /// 重写A区配置信息，走基类老旧模式。
        /// </summary>
        public override AreaAConfigInfo AreaAConfigInfo
        {
            get
            {
                base.AreaAConfigInfo.AreaType = AreaType.BaseDefault;
                return base.AreaAConfigInfo;
            }
        }

        /// <summary>
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写
        /// </summary>
        /// <returns>查询条件</returns>
        public override string yssInitQuery()
        {
            // 1 查询条件
            string cond = "";

            // 3 初始只加载列头，
            this.IsOnlyHeder = true;
            return cond;
        }

        /// <summary>
        /// list界面加载A区数据，子类重写.
        /// </summary>
        /// <returns>查询数据对象</returns>
        public override QueryRes yssGetLeftDataMVC()
        {
            QueryRes res = null;
            //// 【搜索】功能匹配的属性
            this.matchSearchStr = new string[2] { "C_GROUP_CODE", "C_GROUP_NAME" };
            //// modified by HeLiang 2017-06-22 STORY #42921 产品信息组件拆分开发
            //// FunCode使用新增的，避免获取映射关系错误
            leftFormFunCode = "pd_portGroup";
            leftDataFunCode = "pd_portGroup";
            if (groupService == null)
            {
                groupService = ServiceFactory.createService<IPortGroupService>();
            }

            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            res = groupService.getPortGroupA(paraDict);
            //// 保证A区列头名称在代码之前
            res.ListHeadList = reversalHeadList(res, "C_GROUP_CODE", "C_GROUP_NAME");
            YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.ListMode;
            return res;
        }

        /// <summary>
        /// 封装条件对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            ////Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            ////this.groupService = ServiceFactory.createService(serviceType) as IPortGroupService;
            ////this.dataService = this.groupService;
            ////if (this.tbLeftMain.CheckedRows.Count > 0)
            ////{
            ////    string search = this.yssBuildLeftCheckRowsStr("portGroup");
            ////    search = search.Replace("'", "");
            ////    paraDict.Add("ARRAY_C_GROUP_CODE", search);
            ////}

            if (this.tbLeftMain.SelectedRow != null)
            {
                YssProductInfo.Support.Ab.PortGroup.Pojo.PortGroup portGroup = this.tbLeftMain.SelectedRow.Tag as YssProductInfo.Support.Ab.PortGroup.Pojo.PortGroup;
                paraDict.Add("C_GROUP_CODE", portGroup.C_GROUP_CODE);
            }
            else if (this.tbLeftMain.Rows != null && this.tbLeftMain.Rows.Count > 0)
            {
                this.tbLeftMain.Rows[0].Selected = true;
                YssProductInfo.Support.Ab.PortGroup.Pojo.PortGroup portGroup = this.tbLeftMain.SelectedRow.Tag as YssProductInfo.Support.Ab.PortGroup.Pojo.PortGroup;
                paraDict.Add("C_GROUP_CODE", portGroup.C_GROUP_CODE);
            }

            return paraDict;
        }

        /// <summary>
        /// 获取群组关联组合service
        /// </summary>
        private void getPortGroupRelaService()
        {
            if (null == groupRelaService)
            {
                groupRelaService = ServiceFactory.createService<IPortGroupRelaService>();
            }
        }

        /// <summary>
        /// 屏蔽方法
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbMain_RowDoubleClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
        }

        /// <summary>
        /// 1
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnNew_Click(object sender, EventArgs e)
        {
            if (this.tbLeftMain.SelectedRow == null) 
            {
                if (this.tbLeftMain.Rows == null || this.tbLeftMain.Rows.Count == 0)
                {
                    string msg = "请设置群组！";
                    MessageDialog msgDialog = new MessageDialog();
                    msgDialog.Show(msg, "警告", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                    return;
                }
                else 
                {
                    this.tbLeftMain.Rows[0].Selected = true;
                }
            }
            //// edt by zzk 20150925 增加frmSet.Visible == false的判断 BUG 11736 问题8 重复点击新增按钮时，不会出现组合信息
            if (frmSet == null || frmSet.IsDisposed)
            {
                frmSet = new Frm_PORT_GROUP_S();
                frmSet.AllowRebuilderTable = true;
                frmSet.FrmList = this;
            }

            if (frmSet.ShowDialog(this) == DialogResult.OK)
            {
                savePort(frmSet.CheckedRows);
                btnSearch_Click(null, null);
            }
        }


        /// <summary>
        /// 保存组合
        /// </summary>
        /// <param name="rows">选中的行</param>
        private void savePort(RowCollection rows)
        {
            ArrayList list = new ArrayList();
            YssProductInfo.Support.Ab.PortGroup.Pojo.PortGroup portGroup = this.tbLeftMain.SelectedRow.Tag as YssProductInfo.Support.Ab.PortGroup.Pojo.PortGroup;
            PortGroupRela portGroupRela = null;
            foreach (Row tempRow in rows)
            {
                FAST.Common.Service.Pojo.Port port = ((ControlEntity)tempRow.Tag).DataEntity as FAST.Common.Service.Pojo.Port;
                if (port.DATA_TYPE.Equals("PORT_TYPE"))
                {
                    portGroupRela = new PortGroupRela();
                    portGroupRela.C_GROUP_CODE = portGroup.C_GROUP_CODE;
                    portGroupRela.C_PORT_CODE = port.C_PORT_CODE;
                    portGroupRela.C_GROUP_NAME = portGroup.C_GROUP_NAME;
                    portGroupRela.Modifier = ClsContext.currentUser.C_USER_CODE;

                    portGroupRela.AuditState = (_formFun.N_CHECK == 1) ? 0 : 1; // 当功能窗体启用审核机制时新增状态为0 byleeyu20130403
                    //// 当未开启审核状态时，这时是需要将审核人，审核时间填到POJO中去的byleeyu20130719
                    if (_formFun.N_CHECK <= 0)
                    {
                        portGroupRela.OperUser = ClsContext.currentUser.C_USER_CODE;
                        portGroupRela.AuditDate = ClsFunction.formatDateTime("yyyyMMdd HH:mm:ss", DateTime.Now.ToString());
                    }

                    list.Add(portGroupRela);
                }
            }

            dataService.insert(list);
        }
    }
}


