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
using System.Text;
using System.Windows.Forms;
using System.Collections;
using YssInformation.Support.Fun;
using YssInformation.Support.Bi.Market.Service;


namespace YssSecInformation.Sv.Form
{
    /// <summary>
    /// sdg
    /// </summary>
    public partial class Frm_SEC_BASE_LLHH_L : FrmBaseList
    {
        /// <summary>
        /// dataService
        /// </summary>
        private IMktDataService mktDataService = null;

        /// <summary>
        /// 构造
        /// </summary>
        public Frm_SEC_BASE_LLHH_L()
        {
            InitializeComponent();
            this.bUseMVCService = true;
            ////实现附件功能。何讯，20151207
            this.AutoLoadEnclosure = true;
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
        /// 初始化
        /// </summary>
        protected override void initServiceMVC()
        {
            base.initServiceMVC();
            mktDataService = DataServiceFactory.createService<IMktDataService>();

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
                // edit by yh 2011.03.09修改数据来源为枚举类型,防止赋值出错
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

        ///////<summary>
        /////// list界面加载A区数据，子类重写.
        /////// </summary>
        /////// <author>jiangjin 2012-10-26.</author>
        /////// <returns>the result</returns>
        ////public override string yssGetLeftData()
        ////{
        ////    ClsEnums.DataSrc dataSrc = ClsEnums.DataSrc.SrcCache; // 数据来源是缓存
        ////    string funCode = "exchange"; // 交易市场
        ////    string headKeys = "C_MKT_NAME~!~C_MKT_CODE"; // 自定义列头

        ////    this.matchSearchStr = new string[2] { "C_MKT_NAME", "C_MKT_CODE" }; // 【搜索】功能匹配的属性

        ////    string result = null;

        ////    // 设定左侧数据的加载方式
        ////    YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;

        ////    // 调用由子类提供参数的查询方法
        ////    result = this.yssGetLeftData(dataSrc, funCode, null, headKeys, null);
        ////    return result;
        ////}

        /// <summary>
        /// 获取list查询条件区的查询条件.
        /// </summary>
        /// <returns>返回查询条件.</returns>
        public string yssGetListCond()
        {
            string cond = "";
            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
            string search = this.yssBuildLeftCheckRowsStr("exchange");
            //// edit by Yuntao Lau 2015.09.24 STORY #25343
            quyStrUtil.addQuyCon("C_DA_CODE", "C_DA_CODE", "C_SEC_VAR_CODE", ClsConstant.SQL_RA_HYPHEN_LIKE, ClsConstant._LIKE_MARCH_LEFT);
           
            quyStrUtil.addQuyCon("portCode", "C_MKT_CODE", search, "IN");
            if (this.txtSecMktCode.Text != "")
            {
                quyStrUtil.addQuyCon("C_SEC_CODE", this.txtSecMktCode.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            if (this.txtSecMktCode.Text != "")
            {
                quyStrUtil.addQuyCon("C_SEC_MKT_CODE", this.txtSecMktCode.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            if (this.txtSecMktCode.Text != "")
            {
                quyStrUtil.addQuyCon("C_SEC_ISIN_CODE", this.txtSecMktCode.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            if (this.txtSecName.Text != "")
            {
                quyStrUtil.addQuyCon("C_SEC_NAME", this.txtSecName.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            cond = quyStrUtil.getQuyStr("exchange");
            return cond;
        }

        /// <summary>
        /// 封装条件对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            ////STORY #33096 【招商基金】【QDII】紧急-彭博证券信息优化
            string search = this.yssBuildLeftCheckRowsStr("exchange");
            search = search.Replace("'", "");
            if (search != "")
            {
                paraDict.Add("ARRAY_C_MKT_CODE", search);
            }

            if (this.txtSecMktCode.Text.Trim().Length > 0)
            {
                paraDict.Add("C_SEC_CODE", "%" + this.txtSecMktCode.Text.Trim() + "%");
            }

            if (this.txtSecMktCode.Text.Trim().Length > 0)
            {
                paraDict.Add("C_SEC_MKT_CODE", "%" + this.txtSecMktCode.Text.Trim() + "%");
            }

            if (this.txtSecMktCode.Text.Trim().Length > 0)
            {
                paraDict.Add("C_SEC_ISIN_CODE", "%" + this.txtSecMktCode.Text.Trim() + "%");
            }

            if (this.txtSecName.Text.Trim().Length > 0)
            {
                paraDict.Add("C_SEC_NAME", "%" + this.txtSecName.Text.Trim() + "%");
            }

            return paraDict;
        }

        /// <summary>
        /// 界面初始化
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_BASE_LLHH_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            dataService = (IServiceBus)ServiceFactory.createService(serviceType);
        }
    }
}


