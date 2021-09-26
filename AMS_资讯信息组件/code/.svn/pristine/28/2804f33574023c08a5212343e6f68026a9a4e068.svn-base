using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
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

using YssSecInformation.Support.Mp.Indexmp.Service;

using System.Collections;



using FAST.Common.Service.DataService;
using YssSecInformation.Support.Mp.SecMktMap.Service;


namespace YssSecInformation.Mp.Indexmp.Form
{
    /// <summary>
    /// 指数行情List界面
    /// </summary>
    public partial class Frm_SEC_MKT_INDEX_L : FrmBaseList
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private ISecMktService marketValueService = null;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_SEC_MKT_INDEX_L()
        {
            this.ShowLeftPanel = false;
            this.ShowFilterPanel = true;
            this.bUseMVCService = true;
            InitializeComponent();
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
        /// 加载左侧控件数据
        /// </summary>
        public override void yssLoadLeftData()
        {
            QueryRes res = null;
            ArrayList showColumnList = new ArrayList();
            try
            {
                IMktDataService mktDataService = DataServiceFactory.createService<IMktDataService>();
                //// edit by yh 2011.03.09修改数据来源为枚举类型,防止赋值出错
                leftDataFunCode = YssInformation.Support.Context.AssociaType.base_exchange.ToString();
                matchSearchStr = new string[2] { "C_MKT_NAME", "C_MKT_CODE" };     // 【搜索】功能匹配的属性
                res = mktDataService.getDataListRes();
                showColumnList.Add("C_MKT_NAME");
                showColumnList.Add("C_MKT_CODE");
                //// 设定左侧数据的加载方式
                YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;
                new TableListLoader().loadTable(tbLeftMain, res, false, false, ClsEnums.KTableDataShowMode.TreeMode, showColumnList);

            }
            catch (Exception ex)
            {
                ////YssMessageBox.ShowDyanInformation("加载左侧交易市场信息报错", ex.Message, MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getAareaLoadErr(ex.Message));
            }
        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_MKT_ZQ_L_Load(object sender, EventArgs e)
        {
            this.getServiceInstance();
        }

        /// <summary>
        /// 获取服务对象
        /// </summary>
        private void getServiceInstance()
        {
            if (null == this.dataService)
            {
                this.marketValueService = ServiceFactory.createService<ISecMktService>();
                this.dataService = this.marketValueService;
            }
        }

        /// <summary>
        /// 封装前台条件对象
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>paraDict</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            ////BUG140454【紧急】华宝信托V5深交所指数行情清算成功但前台查不出数据
            ////paraDict.Add("C_MKT_CLS", "IX");  ////edit by xhb 2015-11-19 行情设置成指数 BUG #122592

            //// BUG #93108 指数行情资料、指数基本信息、打开报错 xzl
            ////string search = this.yssBuildLeftCheckRowsStr("exchange");
            ////search = search.Replace("'", "");
            ////paraDict.Add("ARRAY_C_MKT_CODE", search);

            if (this.selSecurity.Value != null && this.selSecurity.Value.Trim().Length > 0)
            {
                paraDict.Add("ARRAY_C_SEC_CODE", this.selSecurity.Value.Replace("|", ","));
            }

            paraDict.Add("D_BEGIN", dtpMKTPriceDate.getBeginDate.ToString("yyyy-MM-dd").Trim());
            paraDict.Add("D_END", dtpMKTPriceDate.getEndDate.Date.ToString("yyyy-MM-dd").Trim());

            return paraDict;
        }

        /// <summary>
        /// 设置参数
        /// </summary>
        /// <returns>参数集合</returns>
        public override Dictionary<string, string> setExtraCondition()
        {
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("C_MKT_CLS", "IN");
            return paraDict;
        }

        /// <summary>
        /// 加载后事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void selSecurity_YssOnAfterLoadData(object sender, YssBeforeOperEventArgs e)
        {
            FAST.Core.BaseControl.YssSelCombox comBox = sender as FAST.Core.BaseControl.YssSelCombox;
            comBox.DisplayValue = "C_SEC_CODE";
            comBox.Parameter = "C_SEC_CODE~C_INDEX_NAME~C_INDEX_CODE";
        }
    }

}


