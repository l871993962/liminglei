using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.CRUD.Form;
using FAST.Core.Context;
using Yss.KMessage;
using FAST.Common.Service.Pojo;
using FAST.Core.Resource;
using FAST.Core.Util;
using YssInformation.Support.Bi.AccountTree.Service;
using FAST.Core.Communication.Service;

namespace YssInformation.Bi.AccountTree.Form
{
    public partial class Frm_ACC_TREE_L : FrmBaseList
    {
        Frm_ACC_TREE_S_A treeASet = null;
        public Frm_ACC_TREE_L()
        {
            InitializeComponent();
            isLoadFirst = false;
            this.ShowPlanSet = true;
            bUseMVCServiceLeft = true;
            bUseMVCService = true;
            //this.tbMain.ShowCheckBox = true;
            this.YssMainKTableShowMode = FAST.Core.Context.ClsEnums.KTableDataShowMode.ListMode;
        }

        public override string yssInitQuery()
        {
            string con = "";
            this.IsOnlyHeder = true;
            return con;
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
        /// 获取查询区条件
        /// </summary>
        /// <param name="paraDict">查询条件cboPayType 款项类型</param>
        /// <returns></returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            string search = "";
            search = this.yssBuildLeftCheckRowsStr("base_accountTreeA");
            search = search.Replace("'", "");
            paraDict.Add("ARRAY_C_NODE_CODE_P", search);
            return paraDict;
        }

        /// <summary>
        /// 【设置】按钮响应事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnFunSet_Click(object sender, EventArgs e)
        {
            try
            {
                treeASet = new Frm_ACC_TREE_S_A();
                SysFun cls_FUN = null;
                if (ClsContext.sysFunHash.ContainsKey("base_accountTreeA"))
                {
                    cls_FUN = (SysFun)ClsContext.sysFunHash["base_accountTreeA"];
                }
                else
                {
                    new MessageDialog().Show("功能菜单【账户树形结构】-【base_accountTreeA】未启用或未授权", "提示", MessageBoxButtons.OK);
                    return;
                }

                treeASet.YssFormMenu = cls_FUN;
                treeASet.YssStatus = ClsEnums.StatusSetting.YssAdd;
                treeASet.initControlStat();
                treeASet.Show(this);
                this.loadLeftDataProcMVC();
            }
            catch (System.Exception ex)
            {
                new MessageDialog().Show("新增账户树形结构异常", "系统异常", MessageBoxButtons.OK, MessageBoxIcon.Error, ex.Message);
            }
        }

        /// <summary>
        /// list界面加载A区数据，子类重写.
        /// </summary>
        /// <returns>查询数据对象</returns>
        public override QueryRes yssGetLeftDataMVC()
        {
            this.YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;
            this.tbLeftMain.ShowCheckBox = true;
            QueryRes res = null;
            leftFormFunCode = "base_accountTreeA";
            leftDataFunCode = "base_accountTreeA";
            this.tbLeftMain.Text = "账户树形结构";
            ClsAssocia asc = new ClsAssocia();
            asc = ClsClzCfgMgr.getAssociaParam("base_accountTreeA");
            Type serviceTypeA = ReflectBase.YssGetType(asc.ServiceDllName, asc.ServiceName);
            IAccountTreeAService accountTreeAService = (IAccountTreeAService)ServiceFactory.createService(serviceTypeA);
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            ////add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
            //paraDict.Add("C_NODE_CODE_P", "[root]");
            res = accountTreeAService.getTreeViewData(paraDict);
            this.matchSearchStr = new string[] { "C_NODE_CODE", "C_NODE_CODE_P", "C_NODE_NAME" }; // 【搜索】功能匹配的属性
            return res;
        }

        /// <summary>
        /// 重写行的双击事件
        /// 根据双击后的数据条件加载相关数据到中间主界面控件中
        /// </summary>
        /// <param name="sender">控件对象</param>
        /// <param name="e">事件信息</param>
        protected override void tbLeftMain_RowDoubleClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            showLeftFormSet(ClsEnums.StatusSetting.YssBrow);
        }

    }
}
