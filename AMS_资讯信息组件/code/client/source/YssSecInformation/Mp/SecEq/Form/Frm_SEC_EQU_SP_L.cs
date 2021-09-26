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
using System.Collections;

using System.Collections.Generic;
using FAST.Common.Service.Services;
using YssSecInformation.Support.Mp.SecEq.Service;
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
    /// 功能简介：证券送配信息浏览界面，负责证券送配信息的显示和查询功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.04
    ///
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：lyh
    /// 修改日期：2011.01.31
    /// 修改简介：加载a区市场信息
    ///   /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：wuwenlan
    /// 修改日期：2011.02.11
    /// 修改简介：添加回收站机制
    /// 
    /// 
    ///－－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：chenyoulong
    /// 修改日期：20110303
    /// 修改简介：
    /// 1、代码注释完善（包括方法作用注释，逻辑注释，类修改注释）
    /// 2、提示信息统一调整(前台用五个参数YssMessageBox.ShowDyanInformation("初始化窗体出错", ye.Message, "错误信息", MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail)）
    /// 3、需求中内容没有实现功能的调整 
    /// 4、删除不用的代码
    /// </summary>
    public partial class Frm_SEC_EQU_SP_L : FrmBaseList, IFormDetailData
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
        private ISecEquService secEquService = null;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_SEC_EQU_SP_L()
        {
            this.bUseMVCService = true;
            InitializeComponent();
            ////实现附件功能。何讯，20151208
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
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写
        ///  -----添加记录-----
        ///  添加人：chenyoulong
        ///  添加时间：20110303
        ///  添加简介：拼接初始查询条件
        /// </summary>
        /// <returns>初始化查询条件</returns>
        public override string yssInitQuery()
        {
            // 1 查询条件---权益类型标识---SP为送配
            string cond = "";

            // 3 初始只加载列头，若需要则设为true，反之不需要设置此参数
            this.IsOnlyHeder = true;

            return cond;
        }


        /// <summary>
        /// 获取list查询条件区的查询条件
        ///  -----添加记录-----
        ///  添加人：chenyoulong
        ///  添加时间：20110303
        ///  添加简介：拼接查询条件
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
            ////cond += " and a.C_DATA_IDF = 'SP'";
            quyStrUtil.addQuyCon("C_EQU_CLS", "SP", "=");

            // 封装除权日
            ////cond += " and a. D_EXR  between to_date( '" + this.dtpExRightDate.getBeginDate.ToString("yyyy-MM-dd").Trim() + "','yyyy-MM-dd') and to_date('" + this.dtpExRightDate.getEndDate.Date.ToString("yyyy-MM-dd").Trim() + "','yyyy-MM-dd')"; // 除权日
            quyStrUtil.addQuyCon("dExr", "D_EXR", dtpExRightDate.getBeginDate.ToString("yyyy-MM-dd").Trim() + "," + dtpExRightDate.getEndDate.Date.ToString("yyyy-MM-dd").Trim(), "BETWEEN");
            
            // 获取证券代码查询条件
            if (this.cboHoldSecurity.Value != null && this.cboHoldSecurity.Value.Trim().Length != 0)
            {
                ////cond += " and a.C_SEC_CODE = '" + this.cboHoldSecurity.Value.Trim() + "'";
                quyStrUtil.addQuyCon("C_SEC_CODE", this.cboHoldSecurity.Value.Trim(), "=");
            }

            // 获取送配类型查询条件
            if (this.cboDistType.Value != null && this.cboDistType.Value.Trim().Length != 0)
            {
                ////cond += " and a.C_DS_CODE = '" + this.cboDistType.Value.Trim() + "'";
                quyStrUtil.addQuyCon("C_DS_CODE", this.cboDistType.Value.Trim(), "=");

            }

            cond = quyStrUtil.getQuyStr("exchange");
            return cond;
        }

        /////// <summary>
        /////// list界面加载A区数据，子类重写
        ///////  -----添加记录-----
        ///////  添加人：chenyoulong
        ///////  添加时间：20110303
        ///////  添加简介：加载左侧列表交易市场
        /////// </summary>
        /////// <author>chenyoulong 2011.03.03</author>
        /////// <returns>A区数据</returns>
        ////public override string yssGetLeftData()
        ////{
        ////    // edit by yh 2011.03.09修改数据来源为枚举类型,防止赋值出错
        ////    FAST.Core.Context.ClsEnums.DataSrc dataSrc = FAST.Core.Context.ClsEnums.DataSrc.SrcCache;    // 数据来源是缓存
        ////    string funCode = "exchange";    // 交易市场
        ////    string headKeys = "C_MKT_NAME~!~C_MKT_CODE";   // 自定义列头

        ////    matchSearchStr = new string[2] { "C_MKT_NAME", "C_MKT_CODE" };     // 【搜索】功能匹配的属性

        ////    string result = null;

        ////    // 设定左侧数据的加载方式
        ////    YssLeftKTableShowMode = FAST.Core.Context.ClsEnums.KTableDataShowMode.TreeMode;

        ////    // 调用由子类提供参数的查询方法
        ////    result = this.yssGetLeftData(dataSrc, funCode, null, headKeys, null);
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
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_EQU_SP_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.secEquService = ServiceFactory.createService(serviceType) as ISecEquService;
            this.dataService = this.secEquService;
        }


        /// <summary>
        /// 封装条件到对象
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>paraDict</returns>
        protected override System.Collections.Generic.Dictionary<string, string> OnGetParaAssemble(System.Collections.Generic.Dictionary<string, string> paraDict)
        {
            string search = this.yssBuildLeftCheckRowsStr("exchange");
            search = search.Replace("'", "");
            paraDict.Add("ARRAY_C_MKT_CODE", search);
            paraDict.Add("C_EQU_CLS", "SP");

            // 获取证券代码查询条件
            if (this.cboHoldSecurity.Value != null && this.cboHoldSecurity.Value.Trim().Length != 0)
            {
                paraDict.Add("ARRAY_C_SEC_CODE", this.cboHoldSecurity.Value.Trim().Replace("|", ","));
            }

            // 获取送配类型查询条件
            if (this.cboDistType.Value != null && this.cboDistType.Value.Trim().Length != 0)
            {
                paraDict.Add("C_DS_CODE", this.cboDistType.Value.Trim());

            }

            paraDict.Add("D_BEGIN", dtpExRightDate.getBeginDate.ToString("yyyy-MM-dd").Trim());
            paraDict.Add("D_END", dtpExRightDate.getEndDate.Date.ToString("yyyy-MM-dd").Trim());

            return paraDict;
        }

        /// <summary>
        /// 设置参数
        /// </summary>
        /// <returns>参数集合</returns>
        public override Dictionary<string, string> setExtraCondition()
        {
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("C_EQU_CLS", "SP");
            return paraDict;
        }

        #region oldCode
        /// <summary>
        /// 窗体load事件中控制新增按钮不可用
        /// chenyoulong 20110303
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        ////private void Frm_SEC_EQU_SP_L_Load(object sender, EventArgs e)
        ////{
        ////    try
        ////    {
        ////    }
        ////    catch (Exception ex)
        ////    {
        ////        YssMessageBox.ShowDyanInformation(ex.Message, ex.StackTrace, "加载窗体操作开始之前发生错误", MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
        ////    }

        ////}

        /// <summary>
        /// 点击行的时候控制新增按钮是否可用
        /// chenyoulong 20110302
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        ////private void tbLeftMain_RowClicked_1(object sender, Yss.KTable.Events.RowEventArgs e)
        ////{
        ////    try
        ////    {
        ////        // 判断当前选中是否为明细节点，为明细节点新增按钮可用，否则不可用
        ////        if (this.tbLeftMain.SelectedRow != null && this.tbLeftMain.SelectedRow.SubRows.Count > 0)
        ////        {
        ////            this.btnNew.Enabled = false;
        ////        }
        ////        else
        ////        {
        ////            this.btnNew.Enabled = true;
        ////        }
        ////    }
        ////    catch (Exception ex)
        ////    {
        ////        YssMessageBox.ShowDyanInformation(ex.Message, ex.StackTrace, "点击行事件操作发生错误", MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
        ////    }
        ////}

        /// <summary>
        ///　刷新事件
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        ////private void Frm_SEC_EQU_SP_L_YssOnAfterRefreshListView(object sender, YssAfterLoadEventArgs e)
        ////{
        ////    this.tbMain.GroupBy(this.tbMain.Columns[4]);   // list界面送配类型分组  liuping  2011-03-29  BUG #1501 证券送配BUG
        ////    this.tbMain.Columns[4].Tag = ClsConstant.colGroup;
        ////    tbMain.Refresh();
        ////}

        /// <summary>
        /// 证券代码值改变事件处理获取不同的送配类型
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        ////private void cboHoldSecurity_TextChanged(object sender, EventArgs e)
        ////{
        ////    I_SecBase sec = null;
        ////    try
        ////    {
        ////        if (this.cboHoldSecurity.Value != null)
        ////        {
        ////            sec = this.cboHoldSecurity.getKeyCollection()[this.cboHoldSecurity.Value] as I_SecBase;
        ////            if (sec.C_DA_CODE.Contains("GP"))
        ////            {
        ////                this.cboDistType.QueryCond = " where C_DT_CODE in('ZQSP_SG','ZQSP_PG','ZQSP_SZPS')";
        ////            }
        ////            else if (sec.C_DA_CODE.Contains("QZ"))
        ////            {
        ////                this.cboDistType.QueryCond = " where C_DT_CODE in('ZQSP_CWSP','ZQSP_PWSP')";
        ////            }
        ////            else
        ////            {
        ////                this.cboDistType.QueryCond = " where C_BUSI_TYPE = 'ZQSP'";
        ////            }
        ////        }
        ////    }
        ////    catch (Exception ex) 
        ////    {
        ////        YssMessageBox.ShowDyanInformation("根据证券的品种属性获取送配类型出现异常", ex.Message, MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
        ////    }
        ////}
        #endregion

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
                this.geneParaAssemble.Add("C_EQU_CLS", "SP");
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

                paraDict.Add("C_EQU_CLS", "SP");

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


