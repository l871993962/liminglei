using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;

using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;
using System;
using System.Collections.Generic;
using System.Windows.Forms;




using FAST.Core.BaseControl.Fun;


////using YssBaseCls.Fun;
using YssInformation.Support.Fun;
using YssSecInformation.Support.plate.Service;




namespace YssSecInformation.PlateSet.plateSub.Form
{
    ///<summary>
    /// 功能简介：板块信息浏览界面，负责板块发行信息的显示和关联证券信息的操作
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.13
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：chenyoulong
    /// 修改日期：20101215
    /// 修改简介：
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：chenyoulong
    /// 修改日期：20110307
    /// 修改简介：根据板块设置需求的重新设计，重新对板块设计进行开发
    /// </summary>
    public partial class Frm_PLATE_L : FrmBaseList
    {
        /// <summary>
        /// 窗体A区数据服务对象
        /// </summary>
        private IPlate_AService plateService = null;

        /// <summary>
        /// 定义对象
        /// </summary>
        public object objForm = null;

        /// <summary>
        /// 定义对象
        /// </summary>
        public ClsAssocia clsAssSec;

        /////// <summary>
        /////// 定义左侧区域的配置信息 by leeyu 2011-8-9
        /////// </summary>
        ////private ClsAssocia cls_Left_Associs = null;

        /////// <summary>
        /////// 定义list列表的数据配置信息
        /////// </summary>
        ////private ClsAssocia cls_List_Associs = null;



        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_PLATE_L()
        {
            this.bUseMVCService = true;
            this.bUseMVCServiceLeft = true;
            InitializeComponent();
            ////this.leftFormFunCode = "sectorChild_A";
            hasLeftSetForm = true;
            ////this.btnSave.Visible = true;
            ////this.btnSave.Enabled = false;
            btnBar.setButtonEnabled(ClsButtonName.BtnAudit, false);
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

        #region 详细设计--方法定义
        /// <summary>
        /// 重写父类的方法.
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
        /// 保存按钮单击事件的处理.
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        protected override void btnSave_Click(object sender, EventArgs e)
        {
        }

        /// <summary>
        /// .
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        private void frmList_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (objForm != null)
            {
                objForm = null;
            }

            ////this.btnNew.Enabled = true
            btnBar.setButtonEnabled(ClsButtonName.BtnNew, true);

        }

        #endregion


        /// <summary>
        /// －－－－修改记录－－－－.
        /// 当前版本：V4.5.0.2.
        /// 修改人：chenyoulong.
        /// 修改日期：20110307.
        /// 修改简介:list窗体加载事件.
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        private void Frm_PLATE_L_Load(object sender, EventArgs e)
        {
            try
            {
                ////Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
                ////dataService = serviceType as IServiceBus;

                ClsAssocia asc = new ClsAssocia();
                asc = ClsClzCfgMgr.getAssociaParam("sv_sectorChild_A");

                Type serviceTypeA = ReflectBase.YssGetType(asc.ServiceDllName, asc.ServiceName);
                plateService = (IPlate_AService)ServiceFactory.createService(serviceTypeA);

            }
            catch (Exception ye)
            {
                ClsBaseException.DiscardException(ye);
                //// YssMessageBox.ShowDyanInformation("【" + this.Text + "】窗体加载报错！", ye.Message, "信息提示", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFormLoadErr(this.Text));
            }
        }

        /// <summary>
        /// 点击删除之后的操作事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_PLATE_L_YssOnAfterDelClick(object sender, EventArgs e)
        {
            this.dataAdmin.CurFUN.C_FUN_CODE = "sv_sectorChild_A";
            btnSearch_Click(sender, new EventArgs());
            ////yssInitForm();
        }

        /// <summary>
        /// 获得list查询条件
        /// </summary>
        /// <returns>查询条件</returns>
        public string yssGetListCond()
        {
            string condstr = "";
            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
            string search = this.yssBuildLeftCheckRowsStr("sv_sectorChild_A");

            if (search != null)
            {
                quyStrUtil.addQuyCon("C_PLATE_CODE", search, "in");
            }

            condstr = quyStrUtil.getQuyStr();
            return condstr;
        }

        /// <summary>
        /// 封装条件对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            string search = this.yssBuildLeftCheckRowsStr("sv_sectorChild_A");
            search = search.Replace("'", "");
            paraDict.Add("ARRAY_C_PLATE_CODE", search);
            paraDict.Remove("dataClass");
            paraDict.Add("dataClass", "PlateSubExtend");
            /////添加证券代码做为查询条件 
            if (this.selSecurity.Value != null && this.selSecurity.Value.Trim().Length > 0)
            {
                paraDict.Add("C_SEC_CODE", selSecurity.Value);
            }
            
            return paraDict;
        }

        /// <summary>
        /// list界面加载A区数据，子类重写.
        /// </summary>
        /// <returns>查询数据对象</returns>
        public override QueryRes yssGetLeftDataMVC()
        {
            QueryRes res = null;
            ////由于测试需要，先把A区改为测试用的  故修改其funcode  后期调整过来   马向峰
            ////leftFormFunCode = "sectorChild_A";
            ////leftDataFunCode = "sectorChild_A";
            leftFormFunCode = "sv_sectorChild_A";
            leftDataFunCode = "sv_sectorChild_A";

            if (plateService == null)
            {
                ClsAssocia asc = new ClsAssocia();
                ////由于测试需要，先把A区改为测试用的  故修改其funcode  后期调整过来   马向峰
                ////asc = ClsClzCfgMgr.getAssociaParam("sectorChild_A");base_sectorChild_A
                asc = ClsClzCfgMgr.getAssociaParam("sv_sectorChild_A");
                Type serviceTypeA = ReflectBase.YssGetType(asc.ServiceDllName, asc.ServiceName);
                plateService = (IPlate_AService)ServiceFactory.createService(serviceTypeA);
            }

            this.matchSearchStr = new string[2] { "C_PLATE_NAME", "C_PLATE_CODE" }; // 【搜索】功能匹配的属性

            YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            res = plateService.getTreeViewData(paraDict);
            //// 保证A区列头名称在代码之前
            res.ListHeadList = reversalHeadList(res, "C_PLATE_CODE", "C_PLATE_NAME");
            return res;
        }
    }
}


