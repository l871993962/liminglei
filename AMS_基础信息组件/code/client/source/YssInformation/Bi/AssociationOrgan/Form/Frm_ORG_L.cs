using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Communication.DataService;
using FAST.Common.Service.DataService.Base;
using FAST.Core.Exceptions;
using FAST.Core.Context;

using FAST.Platform.Right.Fun;
using FAST.Core.BaseControl.Fun;

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

using System.Collections;
using Yss.KTable.Models;






using System.Text.RegularExpressions;



////using YssBaseCls.Fun;
using YssInformation.Support.Bi.AssociationOrgan.Service;
using YssInformation.Support.Fun;







namespace YssInformation.Bi.AssociationOrgan.Form
{
    /// <summary>
    /// 功能简介：机构信息浏览界面，负责机构信息的显示和查询功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： lyh
    /// 创建日期： 2010.12.08
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人： lyh
    /// 修改日期：2011.01.26
    /// 修改简介：实现a区数据加载
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：caozhonghu
    /// 修改日期：20110216
    /// 修改简介：需求二次开发
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：lyh
    /// 修改日期：20110226
    /// 修改简介：调整代码新结构
    /// </summary>
    public partial class Frm_ORG_L : FrmBaseListWithDetails
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private IOrgService orgService = null;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_ORG_L()
        {
            this.bUseMVCService = true;
            leftDataFunCode = "pubvocabulary";
            InitializeComponent();
            this.ShowRowCheckBoxColumn = true;
            this.ShowRowIndexColumn = true;
        }

        /// <summary>
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写.
        /// </summary>
        /// <returns>返回查询条件.</returns>
        public override string yssInitQuery()
        {
            //// 所有提供的参数项如下，只需要设置子类需要的项即可
            //// 1 查询条件
            string cond = ""; // " and C_DV_ORG_TYPE<>'ORG_SEAT' ";
         
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
            ////if (!"".Equals(this.txtOrgCode.Text))
            ////{
            ////    ////cond = cond + " and a.C_ORG_CODE like '%" + this.txtOrgCode.Text + "%'"; // liuping  2011-03-13  支持模糊查询
            ////    quyStrUtil.addQuyCon("C_ORG_CODE", this.txtOrgCode.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
            ////}

            if (!"".Equals(this.txtOrgName.Text))
            {
                ////cond = cond + " and a.C_ORG_NAME like '%" + this.txtOrgName.Text + "%'";
                quyStrUtil.addQuyCon("C_ORG_NAME", this.txtOrgName.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            ////if (!"".Equals(this.txtShortCorpCode.Text))
            ////{
            ////    ////cond = cond + " and a.C_ORG_NAME_ST like '%" + this.txtShortCorpCode.Text + "%'";
            ////    quyStrUtil.addQuyCon("C_ORG_NAME_ST", this.txtShortCorpCode.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
            ////}

            ////if (tbLeftMain.SelectedRow != null)
            ////{
            ////    cond = cond + " and a.C_DV_ORG_TYPE ='" + ((Vocabulary)tbLeftMain.SelectedRow.Tag).C_DV_CODE + "'";
            ////}

            ////string search = this.yssBuildLeftCheckRowsStr("pubvocabulary");  // tanwenjie 2011.7.28 获取A区选中的行
            ////////cond += "and a.C_DV_ORG_TYPE in (" + search + ")";
            ////quyStrUtil.addQuyCon("C_DV_ORG_TYPE", "C_DV_ORG_TYPE", search, "IN"); 

            ////cond = quyStrUtil.getQuyStr("pubvocabulary"); 

            return cond;
        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ORG_L_Load(object sender, EventArgs e)
        {
            getServiceInstance();
        }

        /// <summary>
        /// 获取Service对象实体
        /// </summary>
        private void getServiceInstance() 
        {
            if (orgService == null) 
            {
                Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
                this.orgService = ServiceFactory.createService(serviceType) as IOrgService;
                this.dataService = this.orgService;
            }
        }

        /// <summary>
        /// 封装查询条件到对象
        /// </summary>
        /// <param name="paraDict">paradict</param>
        /// <returns>e</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            ////string search = this.yssBuildLeftCheckRowsStr("pubvocabulary");
            ////search = Regex.Replace(search, "'", "");

            ////if (!"".Equals(this.txtOrgCode.Text))
            ////{
            ////    paraDict.Add("C_ORG_CODE", "%" + this.txtOrgCode.Text + "%");
            ////}

            if (!"".Equals(this.txtOrgName.Text))
            {
                //// like '%%' 无法命中索引，有性能问题，处理为后半部分模糊查询
                paraDict.Add("C_ORG_NAME", this.txtOrgName.Text + "%");
            }

            if (this.cboQualification.Value != null && this.cboQualification.Value.Length > 0)
            {
                paraDict.Add("ARRAY_C_QUALIFICATION", this.cboQualification.Value.Replace('|', ','));
            }

            if (this.cboOrganType.Value != null && this.cboOrganType.Value.Length > 0)
            {
                paraDict.Add("ARRAY_C_DV_ORG_TYPE", this.cboOrganType.Value.Replace('|', ','));
            }

            if (!"".Equals(this.txtOrgCode.Text))
            {
                //// like '%%' 无法命中索引，有性能问题，处理为后半部分模糊查询
                paraDict.Add("C_ORG_CODE", this.txtOrgCode.Text + "%");
            }

            return paraDict;
        }

        /// <summary>
        /// 重新查询
        /// </summary>
        public new void Refresh()
        {
            this.btnSearch_Click(null, null);
        }
        //// zzk 20150122 博时合代码过来的，需注释掉
        /////// <summary>
        /////// 开始装载明细数据-这里定位明细数据窗体，主数据Pojo
        /////// </summary>
        /////// <param name="rowMain">当前主区域选中的行</param>
        /////// <param name="frmDetail">明细数据窗体</param>
        /////// <param name="mainData">主数据Pojo</param>
        /////// <returns>返回初始化结果</returns>
        ////protected override bool BeginReloadDetailData(Row rowMain, out FrmBase frmDetail, out BasePojo mainData)
        ////{
        ////    frmDetail = null;
        ////    mainData = null;

        ////    if (rowMain != null && rowMain.Tag != null && rowMain.Tag is BasePojo)
        ////    {
        ////        mainData = rowMain.Tag as BasePojo;
        ////    }

        ////    if (this.tabCtrlDataDetail.SelectedTab != null && this.tabCtrlDataDetail.SelectedTab.Controls.Count > 0 && this.tabCtrlDataDetail.SelectedTab.Controls[0] is FrmBaseList)
        ////    {
        ////        Org org = mainData as Org;
        ////        if (org != null)
        ////        {
        ////            frmDetail = this.tabCtrlDataDetail.SelectedTab.Controls[0] as FrmBase;

        ////            foreach (Yss.Controls.TabPage tabPage in this.tabCtrlDataDetail.TabPages) 
        ////            {
        ////                setTabPageVisble(tabPage, org.C_DV_ORG_TYPE);
        ////            }
        ////        }
        ////    }

        ////    return true;
        ////}

        /// <summary>
        /// 装载明细窗体功能代码列表
        /// </summary>
        /// <returns>返回明细窗体功能代码列表</returns>
        protected override List<SysFun> LoadDetailFormFuns()
        {
            List<SysFun> sysFuns = new List<SysFun>();
            /**
             * 注释：马向峰
             * 框架升级，下半区不再在代码中装载，改到配置文件内配置（systemConfig.xml）
             * 日期：2017.0526
             */
            //// SysFun newFun = new SysFun();

            //// if (ClsContext.sysMenuFunHash.ContainsKey("financial"))
           //// {
           ////     newFun = ClsContext.sysMenuFunHash["financial"] as SysFun;
           ////     newFun.C_FUN_NAME = "基金产品信息";
           ////     sysFuns.Add(newFun);
            //// }

            //// if (ClsContext.sysMenuFunHash.ContainsKey("abroadClearMessage"))
           //// {
           ////     newFun = ClsContext.sysMenuFunHash["abroadClearMessage"] as SysFun;
            ////    newFun.C_FUN_NAME = "境外清算信息";
            ////    sysFuns.Add(newFun);
            ////  }

            ////  if (ClsContext.sysMenuFunHash.ContainsKey("tradeAccount"))
          ////  {
          ////      newFun = ClsContext.sysMenuFunHash["tradeAccount"].Clone() as SysFun;
          ////      newFun.C_FUN_CODE = "shAccRelaOrg";
                ////newFun.C_FUN_NAME = "";
          ////      newFun.YssAssocia = ClsClzCfgMgr.getAssociaParam(newFun.C_FUN_CODE);
            ////  }



            ////  if (!ClsContext.HtUserOperRight.ContainsKey(newFun.C_FUN_CODE))
          ////  {
          ////      ClsContext.HtUserOperRight.Add(newFun.C_FUN_CODE, ClsContext.HtUserOperRight["tradeAccount"]);
            ////  }

            ////  if (!ClsContext.HtFunRight.ContainsKey(newFun.C_FUN_CODE))
           //// {
           ////     ClsContext.HtFunRight.Add(newFun.C_FUN_CODE, ClsContext.HtFunRight["tradeAccount"]);
            ////  }

            ////  if (!ClsContext.sysMenuFunHash.ContainsKey("shAccRelaOrg"))
          ////  {
         ////       ClsContext.sysMenuFunHash.Add("shAccRelaOrg", newFun);
            ////   }

            ////  sysFuns.Add(newFun);

            ////  newFun = ClsContext.sysMenuFunHash["fundAccInfo"].Clone() as SysFun;
            ////  sysFuns.Add(newFun);

            /**
             * 添加：侯方正
             * 稠州银行bug，打开关联机构报错 
             * 日期：2015.4.7
             */
            ////   if (ClsContext.sysMenuFunHash.ContainsKey("dzPara"))
          ////  {
          ////      newFun = ClsContext.sysMenuFunHash["dzPara"].Clone() as SysFun;
            ////  }

            ////    sysFuns.Add(newFun);

            ////   if (ClsContext.sysMenuFunHash.ContainsKey("fax"))
         ////   {
           ////     newFun = new SysFun();
           ////     newFun.C_FUN_CODE = "fax";
           ////     sysFuns.Add(newFun);
            ////  }
            
            return sysFuns;
        }
        //// zzk 20150122 博时合代码过来的，需注释掉
        /////// <summary>
        /////// 设置 tabPage是否显示
        /////// </summary>
        /////// <param name="tabPage">tabPage</param>
        /////// <param name="c_dv_org_type">c_dv_org_type</param>
        ////protected void setTabPageVisble(Yss.Controls.TabPage tabPage, string c_dv_org_type)
        ////{
        ////    if (c_dv_org_type.Equals("ORG_JJGS"))
        ////    {
        ////        if (tabPage.Name.Equals("dzPara"))
        ////        {
        ////            tabPage.Visible = false;
        ////        }
        ////        else 
        ////        {
        ////            tabPage.Visible = true;
        ////        }
        ////    }
        ////    else if (c_dv_org_type.Equals("ORG_SYYH"))
        ////    {
        ////        if (tabPage.Name.Equals("financial") || tabPage.Name.Equals("shAccRelaOrg"))
        ////        {
        ////            tabPage.Visible = false;
        ////        }
        ////        else
        ////        {
        ////            tabPage.Visible = true;
        ////        }
        ////    }
        ////    else
        ////    {
        ////        if (tabPage.Name.Equals("fax"))
        ////        {
        ////            tabPage.Visible = true;
        ////        }
        ////        else
        ////        {
        ////            tabPage.Visible = false;
        ////        }
        ////    }
        ////}

        /// <summary>
        /// 选择行改变时执行 
        /// add by weijj 20140410 
        /// STORY #16057 SET界面在新增和修改、复制的状态下，如果数据没有保存，在LIST界面数据切换时，给予提示
        /// 增加提示用户，是否切换set窗体。同时控制LIST窗体是否切换行。
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbMain_BeforeSelectionChanged(object sender, Yss.KTable.Events.RowSelectChangeEventArgs e)
        {
        }

        /// <summary>
        /// 为实现批量审核模块功能（有审核功能并且有自定义参数的 需要重写该方法）
        /// </summary>
        /// <returns>参数集合</returns>
        public override Dictionary<string, string> setExtraCondition()
        {
            this.btnBar.ShowRefreshStatus = true;
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            this.GetParaAssemble(paraDict);
            return paraDict;
        }

        /// <summary>
        /// 重写数据加载方法
        /// STORY #82131 【交银施罗德】2个操作界面优化需求 
        /// </summary>
        /// <param name="res">res</param>
        protected override void loadListContentMVC(QueryRes res)
        {
            TableListLoader listLoader = new TableListLoader();
            listLoader.FunCode = this.YssFormMenu.C_FUN_CODE;
            listLoader.AutoSort = false;
            listLoader.AutoLoadEnclosure = this.AutoLoadEnclosure;
            listLoader.loadTable(tbMain, res, bShowRowCheckBoxColumn, bShowRowIndexColumn, YssMainKTableShowMode);

            ////读取用户自定义列配置信息
            this.ReadTableColumnsFromConfig(this.tbMain, this.YssFormMenu.C_FUN_CODE);
            ////读取分组列信息。
            this.ReadTableGroupColumnFromConfig(this.tbMain, this.YssFormMenu.C_FUN_CODE);

            ////读取用户自定义列宽信息。张绍林-20151201
            this.ReadColumnWidthFromConfig(this.tbMain, this.YssFormMenu.C_FUN_CODE);

            if (this.clsInterface == null)
            {
                this.clsInterface = new ClsInterface();
            }

            ////STORY #72474 内容区列表头增加排序记忆功能 hp 20190712
            ClsInterface.ReadTableSortColumn(this.tbMain, this.YssFormMenu);
        }

    }
}


