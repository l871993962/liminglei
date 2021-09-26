using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
////
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
using YssSecInformation.Support.Mp.SecEq.Service;


using FAST.Common.Service.Services;
using YssSecInformation.Support.Pojo.Sec;
using YssInformation.Support.Fun;
using YssInformation.Support.Bi.Market.Service;
using FAST.Core.CRUD.Interface;

namespace YssSecInformation.Mp.SecEq.Form
{
    /// <summary>
    /// chenbo
    /// 2017-06-24
    /// #42948 资讯信息管理组件化拆分 
    /// -------------------------------
    /// Frm_SEC_EQU_LT_L 的摘要说明。
    /// 作用：本类是为了实现证券流通权益信息浏览
    ///  
    ///  作者：chenyoulong
    ///  
    ///  版本：v4.5.0.1
    ///  
    ///  添加内容：窗体绘制，功能方法实现
    ///  
    ///  添加日期：2010.11.29
    ///  作者：lyh 
    ///  
    ///  版本：v4.5.0.2
    ///  
    ///  修改内容：调整新需求
    ///  
    ///  修改日期：2011.3.2
    /// </summary>
    public partial class Frm_SEC_EQU_LT_L : FrmBaseList, IFormDetailData
    {
        /// <summary>
        /// 获取或设置一个值，该值为主窗体传过来的数据。当该窗体为某一窗体的附属窗体时有效。
        /// </summary>
        private BasePojo _mainDataPojo;

        /// <summary>
        /// 获取或设置当前窗体是否已经被FrmBaseListWidthDetails窗体关联为子窗体
        /// </summary>
        private bool _hadBeenRelationed = false;

        /// <summary>
        /// 权益信息数据服务对象
        /// </summary>
        private ISecLtService secLtService = null;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_SEC_EQU_LT_L()
        {
            this.bUseMVCService = true;
            InitializeComponent();
            ////实现附件功能。何讯，20151208
            this.AutoLoadEnclosure = true;

            // 创建数据加载后事件 在此实例化此事件保证基类在把数据加载到列表中后调用此方法，对数据进行分类
            ////YssOnAfterRefreshListView += new AfterRefreshListView(Frm_SEC_EQU_LT_L_YssOnAfterRefreshListView);
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
        /// 获取或设置一个值，该值为主窗体传过来的数据。当该窗体为某一窗体的附属窗体时有效。
        /// </summary>
        public BasePojo MainDataPojo
        {
            get
            {
                return this._mainDataPojo;
            }

            set
            {
                if (this._mainDataPojo != value)
                {
                    this._mainDataPojo = value;
                }
            }
        }

        /// <summary>
        /// 获取或设置当前窗体是否已经被FrmBaseListWidthDetails窗体关联为子窗体。通过此属性可防止嵌套关联。
        /// </summary>
        public bool HadBeenRelationed
        {
            get
            {
                return _hadBeenRelationed;
            }

            set
            {
                _hadBeenRelationed = value;
            }
        }


        /// <summary>
        /// 数据加载好设定按交易费用分组
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        ////public void Frm_SEC_EQU_LT_L_YssOnAfterRefreshListView(object sender, YssAfterLoadEventArgs e)
        ////{
        ////    // 设定数据按业务类型分组
        ////    ////tbMain.GroupBy(tbMain.Columns[4]);
        ////    tbMain.Refresh();
        ////}

        /////// <summary>
        /////// 加载左侧控件数据
        /////// </summary>
        /////// <returns>A区数据</returns>
        ////public override string yssGetLeftData()
        ////{
        ////    string result = "";
        ////    try
        ////    {
        ////        // edit by yh 2011.03.09修改数据来源为枚举类型,防止赋值出错
        ////        FAST.Core.Context.ClsEnums.DataSrc dataSrc = FAST.Core.Context.ClsEnums.DataSrc.SrcCache;    // 数据来源是缓存
        ////        string funCode = "exchange";
        ////        string headKeys = "C_MKT_NAME~!~C_MKT_CODE";

        ////        matchSearchStr = new string[2] { "C_MKT_NAME", "C_MKT_CODE" };     // 【搜索】功能匹配的属性

        ////        YssLeftKTableShowMode = FAST.Core.Context.ClsEnums.KTableDataShowMode.TreeMode;
        ////        result = this.yssGetLeftData(dataSrc, funCode, null, headKeys, null);

        ////    }
        ////    catch (Exception ex)
        ////    {
        ////       //// YssMessageBox.ShowDyanInformation("加载左侧交易市场信息报错", ex.Message, MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
        ////        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getAareaLoadErr(ex.Message));
        ////    }

        ////    return result;
        ////}

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
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写
        /// </summary>
        /// <returns>初始化查询条件</returns>
        public override string yssInitQuery()
        {
            // 所有提供的参数项如下，只需要设置子类需要的项即可
            // 1 查询条件
            string cond = "";

            // 3 初始只加载列头，若需要则设为true，反之不需要设置此参数
            this.IsOnlyHeder = true;

            return cond;
        }

        /// <summary>
        /// 获取list查询条件区的查询条件
        /// </summary>
        /// <returns>list查询条件</returns>
        public string yssGetListCond()
        {
            string cond = "";
            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();

            // 如果选中的是根节点，则新增按钮不可以用。
            ////if (this.tbLeftMain.SelectedRow != null)
            ////{
            ////    if (((I_BaseMkt)tbLeftMain.SelectedRow.Tag).MKT_CODE_P.Equals("[root]"))
            ////    {
            ////        this.btnNew.Enabled = false;
            ////        cond = " and d.c_dv_mkt_type = '" + ((I_BaseMkt)tbLeftMain.SelectedRow.Tag).C_MKT_CODE + "'";
            ////    }
            ////    else
            ////    {
            ////        this.btnNew.Enabled = true;
            ////        cond = " and d.c_mkt_code = '" + ((I_BaseMkt)tbLeftMain.SelectedRow.Tag).C_MKT_CODE + "'";
            ////    }
            ////}

            string search = this.yssBuildLeftCheckRowsStr("exchange");  // tanwenjie 2011.7.28 获取A区选中的行
            ////cond += "and d.c_mkt_code in (" + search + ")";
            quyStrUtil.addQuyCon("C_MKT_CODE", "C_MKT_CODE", search, "IN");

            ////cond += " and a.C_DATA_IDF = 'LT'";
            quyStrUtil.addQuyCon("C_EQU_CLS", "LT", "=");
            ////cond += " and a.D_EXR  between to_date('" + this.dtpCirculateDate.getBeginDate.ToString("yyyy-MM-dd") + "','yyyy-MM-dd') and to_date('" + this.dtpCirculateDate.getEndDate.ToString("yyyy-MM-dd") + "','yyyy-MM-dd')";
            quyStrUtil.addQuyCon("dExr", "D_EXR", dtpCirculateDate.getBeginDate.ToString("yyyy-MM-dd").Trim() + "," + dtpCirculateDate.getEndDate.Date.ToString("yyyy-MM-dd").Trim(), "BETWEEN");
            if (this.cboSecurity.Value != null && this.cboSecurity.Text.Trim().Length != 0)
            {
                ////cond += " and a.C_SEC_CODE = '" + this.cboSecurity.Value + "'";
                quyStrUtil.addQuyCon("C_SEC_CODE", this.cboSecurity.Value.Replace("|", ","), "=");
            }

            if (this.cboIssueStyle.Value != null && this.cboIssueStyle.Value.Trim().Length != 0)
            {
                ////cond += " and a.C_DV_CODE = '" + this.cboIssueStyle.Value + "'";
                quyStrUtil.addQuyCon("C_DV_CODE", this.cboIssueStyle.Value, "=");

            }

            cond = quyStrUtil.getQuyStr("exchange");
            return cond;
        }

        /// <summary>
        /// 获取服务实例
        /// </summary>
        private void getServiceIntstance()
        {
            if (null == this.dataService)
            {
                Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
                this.secLtService = ServiceFactory.createService(serviceType) as ISecLtService;
                this.dataService = this.secLtService;
            }
        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_EQU_LT_L_Load(object sender, EventArgs e)
        {
            this.getServiceIntstance();
        }

        /// <summary>
        /// 封装查询条件到对象
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>paraDict</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            string search = this.yssBuildLeftCheckRowsStr("exchange");
            search = search.Replace("'", "");
            paraDict.Add("ARRAY_C_MKT_CODE", search);

            paraDict.Add("C_EQU_CLS", "LT");
            if (this.cboSecurity.Value != null && this.cboSecurity.Text.Trim().Length != 0)
            {
                paraDict.Add("ARRAY_C_SEC_CODE", this.cboSecurity.Value.Replace("|", ","));

            }

            if (this.cboIssueStyle.Value != null && this.cboIssueStyle.Value.Trim().Length != 0)
            {
                paraDict.Add("C_DV_CODE", this.cboIssueStyle.Value);
            }

            paraDict.Add("D_BEGIN", dtpCirculateDate.getBeginDate.ToString("yyyy-MM-dd").Trim());
            paraDict.Add("D_END", dtpCirculateDate.getEndDate.Date.ToString("yyyy-MM-dd").Trim());

            return paraDict;
        }

        /// <summary>
        /// 设置参数
        /// </summary>
        /// <returns>参数集合</returns>
        public override Dictionary<string, string> setExtraCondition()
        {
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("C_EQU_CLS", "LT");
            return paraDict;
        }

        /////// <summary>
        /////// 重写查询方法
        /////// </summary>
        /////// <param name="paraDict">paraDict</param>
        /////// <param name="page">page</param>
        /////// <returns>QueryRes</returns>
        ////protected override QueryRes getQueryResultMVC(Dictionary<string, string> paraDict, PageInation page)
        ////{
        ////    QueryRes queryRes = new QueryRes();
        ////    this.getServiceIntstance();
        ////    queryRes = this.secEquService.selSecLt(paraDict, page, _formFun.C_FUN_CODE);
        ////    return queryRes;
        ////}

        #region IFormDetailData 成员

        /// <summary>
        /// 验证是否需要重新装载数据
        /// </summary>
        /// <param name="mainData">主表传过来的数据</param>
        /// <returns>返回验证结果</returns>
        public bool AllowReloadDetailData(BasePojo mainData)
        {
            bool retValue = false;
            if (mainData != null && mainData != this.MainDataPojo)
            {
                retValue = true;
            }

            return retValue;
        }

        /// <summary>
        /// 明细窗体初始化
        /// </summary>
        /// <param name="parent">FrmBaseListWithDetails父容器</param>
        public void InitializeDetailForm(FrmBaseListWithDetails parent)
        {
            this.sDllName = _formFun.YssAssocia.SetDllName;
            this.sSetClassName = _formFun.YssAssocia.SetFormName;
            this.sPojoClassName = _formFun.YssAssocia.PojoClsName;
            this.sPojoDllName = (_formFun.YssAssocia.PojoDllName != null && _formFun.YssAssocia.PojoDllName.Length > 0) ? _formFun.YssAssocia.PojoDllName : ClsFunction.getDllName(_formFun.YssAssocia.PojoClsName);
            if (_formFun != null)
            {
                this.Text = _formFun.C_FUN_NAME;
            }

            this.ShowLeftPanel = false;
            this.ShowFilterPanel = false;
        }

        /// <summary>
        /// 装载数据
        /// </summary>
        /// <param name="mainData">主表传过来的Pojo</param>
        public void LoadDetailData(FAST.Common.Service.Pojo.Base.BasePojo mainData)
        {
            if (page == null)
            {
                page = new ClsPageInation();
            }

            page.CurrPage = 1;
            page.PageCount = 0;

            bool validate = this.AllowReloadDetailData(mainData);
            if (validate)
            {
                this.AllowResetGeneParaAssemble = false;
                this.MainDataPojo = mainData;
                this.geneParaAssemble.Clear();
                this.geneParaAssemble.Add("dataClass", "SecEqu");
                this.geneParaAssemble.Add("C_EQU_CLS", "LT");
                this.geneParaAssemble.Add("C_SEC_CODE", (mainData as SecBase).C_SEC_CODE);

                ////验证通过，开始装载数据
                this.btnSearch_Click(this.btnBar.getButton(FAST.Core.BaseControl.Fun.ClsButtonName.BtnRefresh).Owner, EventArgs.Empty);
            }
        }

        /// <summary>
        /// 重写基类
        /// </summary>
        /// <param name="paraDict">paradict</param>
        /// <returns>paradict</returns>
        protected override Dictionary<string, string> OnAfterGetParaAssemble(Dictionary<string, string> paraDict)
        {
            if (null != this.MainDataPojo)
            {
                if (paraDict.ContainsKey("D_BEGIN"))
                {
                    paraDict.Remove("D_BEGIN");
                }

                if (paraDict.ContainsKey("D_END"))
                {
                    paraDict.Remove("D_END");
                }

                if (paraDict.ContainsKey("ARRAY_C_MKT_CODE"))
                {
                    paraDict.Remove("ARRAY_C_MKT_CODE");
                }

                if (paraDict.ContainsKey("C_EQU_CLS"))
                {
                    paraDict.Remove("C_EQU_CLS");
                }

                paraDict.Add("C_EQU_CLS", "LT");
                
            }

            if (!paraDict.ContainsKey("dataClass"))
            {
                paraDict.Add("dataClass", "SecEqu");
            }

            if (!paraDict.ContainsKey("C_SEC_CODE") || string.IsNullOrEmpty(paraDict["C_SEC_CODE"]))
            {
                if (null != this.geneParaAssemble && this.geneParaAssemble.ContainsKey("C_SEC_CODE"))
                {
                    paraDict.Remove("C_SEC_CODE");
                    paraDict.Add("C_SEC_CODE", this.geneParaAssemble["C_SEC_CODE"]);
                    
                }
            }

            return paraDict;
        }

        #endregion

    }
}


