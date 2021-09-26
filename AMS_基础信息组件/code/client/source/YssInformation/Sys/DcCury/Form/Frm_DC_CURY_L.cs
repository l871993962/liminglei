using FAST.Common.Service.Pojo.Base;
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
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

using System.Xml;



using FAST.Core.BaseControl.Fun;
using FAST.Core.Context.Events;
////using YssBaseCls.Fun;
using YssInformation.Support.Sys.DcCury.Service;
using YssInformation.Support.Fun;






namespace YssInformation.Sys.DcCury.Form
{
    /// <summary>
    /// 功能简介：添加表头自定义方法
    /// 创建版本：V4.5.0.1
    /// 创建人： lyh
    /// 创建日期： 2011.02.10
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：caozhonghu
    /// 修改日期：2011.02.17
    /// 修改简介：需求二次开发
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：lyh
    /// 修改日期：2011.02.26
    /// 修改简介：调整界面新结构
    /// </summary>
    public partial class Frm_DC_CURY_L : FrmBaseList
    {
        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_DC_CURY_L()
        {
            this.bUseMVCService = true;
            InitializeComponent();
            ////bShowRefreshStatus = false;
            if (!this.DesignMode)
            {
                ////根据配置修改A区可见性,BUG #194185 针对货币信息设置功能，A区可以默认不展示，但目前此页面默认展示组合A区
                ShowLeftPanel = false;
            }
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
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写.
        /// </summary>
        /// <returns>返回查询条件.</returns>
        public override string yssInitQuery()
        {
            //// 所有提供的参数项如下，只需要设置子类需要的项即可
            //// 1 查询条件
            string cond = "";
            //// 3 初始只加载列头，若需要则设为true，反之不需要设置此参数
            IsOnlyHeder = true;
            return cond;
        }

        /// <summary>
        /// 获取list查询条件区的查询条件.
        /// </summary>
        /// <returns>返回查询条件.</returns>
        public string yssGetListCond()
        {
            string cond = "";
            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil(); 

            if (!"".Equals(this.txtCuryCode.Text))
            {
                ////cond = cond + " a.C_DC_CODE like '%" + this.txtCuryCode.Text + "%'";
                quyStrUtil.addQuyCon("C_DC_CODE", this.txtCuryCode.Text, ClsConstant.SQL_RA_HYPHEN_LIKE); 
            }

            if (!"".Equals(this.txtCutyName.Text))
            {
                //////如果第一个条件为空
                ////if ("".Equals(this.txtCuryCode.Text))
                ////{
                ////    ////cond = cond + "  a.C_DC_NAME like '%" + this.txtCutyName.Text + "%'";
                ////    quyStrUtil.addQuyCon("C_DC_NAME", this.txtCutyName.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
                ////}
                ////else
                ////{
                    ////cond = cond + " and a.C_DC_NAME like '%" + this.txtCutyName.Text + "%'";
                quyStrUtil.addQuyCon("C_DC_NAME", this.txtCutyName.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
                ////}
            }

            if (!"".Equals(this.txtCurySymail.Text))
            {
                ////if ("".Equals(this.txtCuryCode.Text) && "".Equals(this.txtCutyName.Text))
                ////{
                ////    ////cond = cond + " a.C_DC_SIGN like '%" + this.txtCurySymail.Text + "%'";
                ////    quyStrUtil.addQuyCon("C_DC_SIGN", this.txtCurySymail.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
                ////}
                ////else
                ////{
                    ////cond = cond + " and a.C_DC_SIGN like '%" + this.txtCurySymail.Text + "%'";
                quyStrUtil.addQuyCon("C_DC_SIGN", this.txtCurySymail.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
                
                ////}
               
            }

            cond = quyStrUtil.getQuyStr();
            return cond; 
        }

        /// <summary>
        /// KTable的单击事件
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void tbMain_RowClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
        }


        /// <summary>
        /// 重写基类的列表界面的双击方法，币种没有设置界面，所有不需要双击事件.
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        protected override void tbMain_RowDoubleClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {   
        }

        /// <summary>
        /// 增加窗体加载事件，为了处理审核、分审核按钮隐藏掉，.
        /// xuqiji 2011-03-06.
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        private void Frm_DC_CURY_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.dataService = ServiceFactory.createService(serviceType) as IServiceBus;

            ////this.btnAudit.Visible = false;
            ////this.btnUnAudit.Visible = false;
            btnBar.setButtonEnabled(ClsButtonName.BtnAudit, false);
            btnBar.setButtonEnabled(ClsButtonName.BtnUnAudit, false);
        }

        /// <summary>
        /// 封装查询条件为对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            if (!"".Equals(this.txtCuryCode.Text))
            {
                paraDict.Add("C_DC_CODE", "%" + this.txtCuryCode.Text + "%");
            }

            if (!"".Equals(this.txtCutyName.Text))
            {
                paraDict.Add("C_DC_NAME", "%" + this.txtCutyName.Text + "%");
            }

            if (!"".Equals(this.txtCurySymail.Text))
            {
                paraDict.Add("C_DC_SIGN", "%" + this.txtCurySymail.Text + "%");
            }

            return paraDict;
        }

        /// <summary>
        /// AfterRefreshListViewMVC
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_DC_CURY_L_YssOnAfterRefreshListViewMVC(object sender, YssAfterLoadEventArgsMVC e)
        {
            foreach (Yss.KTable.Models.Row row in this.tbMain.Rows)
            {
                for (int i = 0; i < this.tbMain.Columns.Count; i++)
                {
                    row.Cells[i].ForeColor = Color.Black;
                }
            }

            this.tbMain.Refresh();
        }
    }
}


