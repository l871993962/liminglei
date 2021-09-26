namespace YssInformation.Bi.Account.Form
{
    using System;
    using System.Collections;
    using System.Collections.Generic;
    using System.ComponentModel;
    using System.Data;
    using System.Drawing;
    using System.Linq;
    using System.Text;
    using System.Windows.Forms;
    using FAST.Common.Service.DataService;
    using FAST.Common.Service.Pojo;
    using FAST.Common.Service.Pojo.Base;
    using FAST.Common.Service.Services;
    using FAST.Core.BaseControl.Pojo;
    using FAST.Core.Cache;
    using FAST.Core.Communication.DataService;
    using FAST.Core.Communication.Service;
    using FAST.Core.Context;
    using FAST.Core.CRUD.Form;
    using FAST.Core.CRUD.Interface;
    using FAST.Core.Exceptions;
    using FAST.Core.Util;
    using Yss.KTable.Collections;
    using Yss.KTable.Models;
    using YssInformation.Support.Bi.Account.Pojo;
    using YssInformation.Support.Bi.Account.Service;
    using YssInformation.Support.Bi.AssociationOrgan.Cache;
    using YssInformation.Support.Bi.AssociationOrgan.Pojo;
    using FAST.Common.Service.DataService.Base;
    using Yss.KTable.Events;
    using YssInformation.Support.Bi.DacType.Service;
    using YssInformation.Support.Bi.DacType.Pojo;
    using FAST.Core.BaseControl.Fun;

    /// <summary>
    /// 资金账户信息设置 浏览界面
    /// 创建人：chenyoulong
    /// 创建日期：2012117
    /// 发布版本：v1.0.0.4
    /// </summary>
    public partial class Frm_FUND_ACC_L : FrmBaseListWithDetails, IFormDetailData
    {
        /// <summary>
        /// 获取或设置一个值，该值为主窗体传过来的数据。 当该窗体为某一窗体的附属窗体时有效。
        /// </summary>
        public BasePojo _mainDataPojo;

        /// <summary>
        /// 获取或设置当前窗体是否已经被 FrmBaseListWidthDetails窗体关联为子窗体
        /// </summary>
        private bool _hadBeenRelationed = false;

        /// <summary>
        /// 声明资金账户信息 service对象
        /// </summary>
        private IFundAccService fundAccService = null;

        /// <summary>
        /// BUG #187667 支付参数“账户是否二次录入”参数值的维护需优化
        /// 用于 作用是否开启二次校验组合信息
        /// </summary>
        private string checkAccInfo = string.Empty;

        /// <summary>
        /// 组合 BUG #305354 在选择款项类型为定期存款时，在综合指令界面新增收方账号，没有带出账号类型和账户名称和组合名称
        /// </summary>
        public string portName = "";

        /// <summary>
        /// 组合 BUG #305354 在选择款项类型为定期存款时，在综合指令界面新增收方账号，没有带出账号类型和账户名称和组合名称
        /// </summary>
        public string portCode = "";

        /// <summary>
        /// 款项类型 BUG #305354 在选择款项类型为定期存款时，在综合指令界面新增收方账号，没有带出账号类型和账户名称和组合名称
        /// </summary>
        public string payType = "";
        
        /// <summary>
        /// 账户明细类型字典
        /// </summary>
        private Dictionary<string, string> accountTypeDict;

        /// <summary>
        /// Initializes a new instance of the Frm_FUND_ACC_L class.
        /// 构 造 函 数
        /// </summary>
        public Frm_FUND_ACC_L()
        {
            bUseMVCServiceLeft = true;
            InitializeComponent();
            this.tabCtrlDataMain.ShowTabHeaderMenuStrip = false;
            this.bUseMVCService = true;
            ////wulongxing 20150803 需求#25153 公共账户、指令模板匹配设置优化
            this.ShowLeftPanel = true;
            //// 初次加载即显示数据
            ////招商现场2000多账户，初次加载慢，需要5秒左右，将打开页面和查询分开成两步，能达到更好的效果
            isLoadFirst = false;

            this.cboOrgan.BeforeDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(this.cboOrgan_BeforeDropDownClick);

            this.matchSearchStr = new string[] { "C_PORT_NAME_ST", "C_PORT_CODE", "C_PORT_NAME", "C_PORT_CODE_P", "C_DAT_CLS" };

            this.YssOnAfterRefreshListViewMVC += new AfterRefreshListViewMVC(Frm_FUND_ACC_L_YssOnAfterRefreshListViewMVC);

            if (!this.DesignMode)
            {
                ////根据配置修改A区可见性 BUG #194507 账户信息设置屏蔽A区设置
                this.fundAccService = ServiceFactory.createService<IFundAccService>();
                string showAreaA = this.fundAccService.showAreaA();
                this.ShowLeftPanel = !"false".Equals(showAreaA);
            }
        }

        /// <summary>
        /// Gets or sets a value indicating whether获取或设置当前窗体是否已经被FrmBaseListWidthDetails窗体关联为子窗体。 通过此属性可防止嵌套关联。
        /// </summary>
        public bool HadBeenRelationed
        {
            get
            {
                return this._hadBeenRelationed;
            }

            set
            {
                this._hadBeenRelationed = value;
            }
        }

        /// <summary>
        /// Gets or sets获取或设置一个值，该值为主窗体传过来的数据。 当该窗体为某一窗体的附属窗体时有效。
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
        /// 重写A区配置信息，走基类老旧模式。
        /// </summary>
        public override FAST.Core.Context.AreaAConfigInfo AreaAConfigInfo
        {
            get
            {
                return base.AreaAConfigInfo;
            }
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

        /// <summary>
        /// 刷新之后的事件 手动转换 账户类型 的值
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_FUND_ACC_L_YssOnAfterRefreshListViewMVC(object sender, EventArgs e)
        {
            RowCollection rows = this.tbMain.GetAllRows(this.tbMain.Rows, false, false);
            int accTypeIndex = 4;
            Column accTypeCol = this.tbMain.Columns["C_ACCOUNT_TYPE"];
            if (accTypeCol != null)
            {
                accTypeIndex = accTypeCol.Index;
            }

            if (accountTypeDict == null || accountTypeDict.Count == 0)
            {
                accountTypeDict = GetAccountTypeDict();
            }

            foreach (Row row in rows)
            {
                if (row.Tag == null)
                {
                    continue;
                }

                FundAcc rowPojo = row.Tag as FundAcc;
                if (!string.IsNullOrEmpty(rowPojo.C_ACCOUNT_TYPE) && rowPojo.C_ACCOUNT_TYPE.Contains('|'))
                {
                    string accountTypeText = "";
                    string[] acountTypeStr = rowPojo.C_ACCOUNT_TYPE.Split('|');
                    foreach (string accounttype in acountTypeStr)
                    {
                        if (accountTypeDict.ContainsKey(accounttype))
                        {
                            ////拼接账户类型名称
                            accountTypeText += accountTypeDict[accounttype] + "|";
                        }
                    }

                    if (!string.IsNullOrEmpty(accountTypeText))
                    {
                        ////截取掉最后一位
                        accountTypeText = accountTypeText.Remove(accountTypeText.Length - 1);
                    }

                    row.Cells[accTypeIndex].Text = accountTypeText;
                }
            }
        }

        /// <summary>
        /// 获取所有账户类型
        /// </summary>
        /// <returns>Dictionary</returns>
        private Dictionary<string, string> GetAccountTypeDict()
        {
            Dictionary<string, string> result = new Dictionary<string, string>();
            IAccountTypeDataService vocservice = DataServiceFactory.createService<IAccountTypeDataService>();
            string[] para = new string[] { "CHZH_ZHMXFL", "JYZH_ZHMXFL" };

            List<BasePojo> basePojos = vocservice.getDataListByTypes(para);
            foreach (BasePojo pj in basePojos)
            {
                AccountType vocPojo = pj as AccountType;
                result.Add(vocPojo.C_DAC_CODE, vocPojo.C_DAC_NAME);
            }

            return result;
        }

        /// <summary>
        /// 列头
        /// </summary>
        /// <returns>list</returns>
        public static List<ListHeadInfo> GetAccTreesViewTableListHead()
        {
            List<ListHeadInfo> listHeadList = new List<ListHeadInfo>();
            ListHeadInfo listhead = new ListHeadInfo();
            listhead.Key = "C_PORT_NAME_ST";
            listhead.Align = "LEFT";
            listhead.Format = string.Empty;
            listhead.ShowConvert = "false";
            listhead.Text = "组合简称";
            listHeadList.Add(listhead);

            listhead = new ListHeadInfo();
            listhead.Key = "C_PORT_CODE";
            listhead.Align = "LEFT";
            listhead.Format = string.Empty;
            listhead.ShowConvert = "false";
            listhead.Text = "组合代码";
            listHeadList.Add(listhead);

            listhead = new ListHeadInfo();
            listhead.Key = "C_DV_PROD_STATE";
            listhead.Align = "LEFT";
            listhead.Format = string.Empty;
            listhead.ShowConvert = "false";
            listhead.Text = "产品状态";
            listHeadList.Add(listhead);

            listhead = new ListHeadInfo();
            listhead.Key = "C_ASS_CODE";
            listhead.Align = "LEFT";
            listhead.Format = string.Empty;
            listhead.ShowConvert = "false";
            listhead.Text = "资产代码";
            listHeadList.Add(listhead);

            listhead = new ListHeadInfo();
            listhead.Key = "C_DAT_CLS";
            listhead.Align = "LEFT";
            listhead.Format = string.Empty;
            listhead.ShowConvert = "false";
            listhead.Text = "资产类别";
            listHeadList.Add(listhead);

            return listHeadList;
        }

        /// <summary>
        /// yssLoadLeftData
        /// </summary>
        public override void yssLoadLeftData()
        {
            FAST.Common.Service.DataService.IPortDataService portDataService = DataServiceFactory.createService<FAST.Common.Service.DataService.IPortDataService>();

            IPortOperValidService portOperValidService = ServiceFactory.createService<IPortOperValidService>();
            string needFilterRight = portOperValidService.isNeedFilterRight("port");
            QueryRes res = portDataService.doPortFilterRes(needFilterRight, string.Empty, string.Empty, string.Empty, ClsContext.DataRightList);
            IVocDataService vocDataService = DataServiceFactory.createService<IVocDataService>();
            List<string> keyList = new List<string>();
            keyList.Add("PD_STATUS");
            keyList.Add("DAT_CODE");
            Dictionary<string, string> vocDict = vocDataService.getVocDic(keyList);

            if (res != null)
            {
                List<BasePojo> pojoList = new List<BasePojo>();
                foreach (BasePojo pojo in res.DataList)
                {
                    Port pt = pojo as Port;
                    if (!string.IsNullOrEmpty(pt.C_DV_PROD_STATE) && vocDict.ContainsKey(pt.C_DV_PROD_STATE))
                    {
                        pt.C_DV_PROD_STATE = vocDict[pt.C_DV_PROD_STATE];
                    }

                    if (!string.IsNullOrEmpty(pt.C_DAT_CLS) && vocDict.ContainsKey(pt.C_DAT_CLS))
                    {
                        pt.C_DAT_CLS = vocDict[pt.C_DAT_CLS];
                    }

                    pojoList.Add(pojo);
                }

                res.DataList = pojoList;
                res.ListHeadList = new List<ListHeadInfo>();
                res.ListHeadList = GetAccTreesViewTableListHead();
                TableListLoader tableLoader = new TableListLoader();
                tableLoader.loadTable(tbLeftMain, res, false, false, ClsEnums.KTableDataShowMode.TreeMode);
                this.matchSearchStr = new string[5] { "C_PORT_NAME_ST", "C_PORT_CODE", "C_ASS_CODE", "C_DAT_CLS", "C_DV_PROD_STATE" };
                this.tbLeftMain.ShowColumnHeader = true;
            }
        }

        /// <summary>
        /// A区 默认加载方法
        /// </summary>
        public void MyLoadLeftData()
        {
            PCPortProduct portProduct = AreaAConfigInfo.SuspensionAreaA.GetPortControl(AreaType.Port) as PCPortProduct;
            if (portProduct != null)
            {
                this.tbLeftMain.Rows.AddRange(portProduct.TablePortProduct.Rows.Clone() as RowCollection);
                this.tbLeftMain.Columns.AddRange(portProduct.TablePortProduct.Columns.Clone() as ColumnCollection);
                this.tbLeftMain.UpdateTable();
            }

            this.matchSearchStr = new string[5] { "C_PORT_NAME_ST", "C_PORT_CODE", "C_ASS_CODE", "C_PORT_NAME", "C_DAT_CLS" };  // 【搜索】功能匹配的属性
            this.tbLeftMain.ShowColumnHeader = true;
        }

        /// <summary>
        /// 初始化 查询条件
        /// </summary>
        /// <returns>cond</returns>
        public override string yssInitQuery()
        {
            // 所有提供的参数项如下， 只需要设置子类需要的项即可
            // 1 查询条件
            string cond = string.Empty;

            // 3 初始只加载列头，若需要则设为true，反之不需要设置此参数
            this.IsOnlyHeder = true;

            return cond;
        }

        #region IFormDetailData 成员

        /// <summary>
        /// 验证 是否需要重新装载数据
        /// </summary>
        /// <param name="mainData">主表传过来的 数据</param>
        /// <returns>返回验证 结果</returns>
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
        /// 明细窗体 初始化(Fast1.2版本)
        /// </summary>
        /// <param name="parent">parent</param>
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
        /// 明细窗体 初始化(Fast1.1版本，无传参)
        /// </summary>
        public void InitializeDetailForm()
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
        /// 装载 数据
        /// </summary>
        /// <param name="mainData">主表 传过来的Pojo</param>
        public void LoadDetailData(BasePojo mainData)
        {
            if (page == null)
            {
                page = new ClsPageInation();
            }
            ////根据主界面来判断是否加载选择按钮
            ////BUG #244648 【鹏华基金】产品基本信息银行账户信息界面缺失选择按钮，不能在产品信息界面关联银行账户信息
            if (mainData != null)
            {
                this.btnBar.setButtonVisibled("btnRela");
            }

            page.CurrPage = 1;
            page.PageCount = 0;
            ////初次加载即显示数据
            ////isLoadFirst = true;
            bool validate = this.AllowReloadDetailData(mainData);
            if (validate)
            {
                this.AllowResetGeneParaAssemble = false;
                this.MainDataPojo = mainData;
                this.geneParaAssemble.Clear();
                this.geneParaAssemble.Add("dataClass", "FundAcc");

                ////if (this._mainDataPojo is CashAcc)
                ////{
                ////    this.geneParaAssemble.Add("C_CA_CODE", (mainData as CashAcc).C_CA_CODE);
                ////}
                if (this._mainDataPojo is Port)
                {
                    this.geneParaAssemble.Add("ARRAY_C_PORT_CODE", (mainData as Port).C_PORT_CODE);
                }
                else if (this._mainDataPojo is Org)
                {
                    this.geneParaAssemble.Add("C_ORG_CODE_HOLDER1", (mainData as Org).C_ORG_CODE);
                    this.geneParaAssemble.Add("C_ORG_CODE_HOLDER2", (mainData as Org).C_ORG_CODE);
                }

                ////验证通过，开始装载数据
                this.btnSearch_Click(this.btnBar.getButton(FAST.Core.BaseControl.Fun.ClsButtonName.BtnRefresh).Owner, EventArgs.Empty);
            }
        }

        /// <summary>
        /// 装载 明细窗体功能代码列表
        /// STORY #42242 歌斐支付平台-能在同一个界面查询所有账户的余额以及发生额 
        /// </summary>
        /// <returns>返回 明细窗体功能代码列表</returns>
        protected override List<SysFun> LoadDetailFormFuns()
        {
            List<SysFun> sysFuns = new List<SysFun>();
            SysFun newFun = new SysFun();

            if (ClsContext.sysMenuFunHash.ContainsKey("pd_relaPort"))
            {
                newFun = ClsContext.sysMenuFunHash["pd_relaPort"].Clone() as SysFun;
                sysFuns.Add(newFun);
            }

            //// 汇添富版本升级测试调整，判断key值不存在
            if (ClsContext.sysMenuFunHash.ContainsKey("accChanSet"))
            {
                newFun = ClsContext.sysMenuFunHash["accChanSet"].Clone() as SysFun;
                sysFuns.Add(newFun);
            }

            if (ClsContext.sysMenuFunHash.ContainsKey("accReminder"))
            {
                newFun = ClsContext.sysMenuFunHash["accReminder"].Clone() as SysFun;
                sysFuns.Add(newFun);
            }

            return sysFuns;
        }

        /// <summary>
        /// 重写
        /// STORY #42242 歌斐支付平台-能在同一个界面查询所有账户的余额以及发生额 
        /// </summary>
        /// <param name="rowMain">ss</param>
        protected override void ReloadDetailData(Row rowMain)
        {
            base.ReloadDetailData(rowMain);
            if (this.tabCtrlDataDetail.SelectedTab != null && this.tabCtrlDataDetail.SelectedTab.Controls.Count > 0 && this.tabCtrlDataDetail.SelectedTab.Controls[0] is FrmBaseList)
            {
                FrmBaseList frmDetail = this.tabCtrlDataDetail.SelectedTab.Controls[0] as FrmBaseList;
                this.expandSplitterDetails.Expanded = true;
                this.tbMain.Focus();
            }
        }

        /// <summary>
        /// 封装 条件对象
        /// </summary>
        /// <param name="paraDict">条件 对象</param>
        /// <returns>条件 对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            string search = this.yssBuildLeftCheckRowsStr("portfolio");
            if (search != null && !"''".Equals(search))
            {
                search = search.Replace("|", ",");
                paraDict.Add("ARRAY_C_PORT_CODE", search);
                paraDict.Add("SHOW_WITHOUT_PORT", "SHOW_WITHOUT_PORT");
                ////paraDict.Add("PUBLIC_ACC_CODE", "PUBLIC_ACC_CODE");
            }

            if (this.cboAccType.Value != null && this.cboAccType.Value.Trim().Length != 0)
            {
                string accType = this.cboAccType.Value.Replace("|", ",");
                paraDict.Add("ARRAY_ACCOUNT_TYPE", accType);
            }

            //// BUG #154291 【加急】南方基金-支付产品账户信息无法通过“开户名称”搜索 
            if (this.txtOpenName.Text.Trim().Length != 0)
            {
                paraDict.Add("C_OPEN_ACC_NAME", "%" + this.txtOpenName.Text + "%");
            }

            if (this.cboCury.Value != null && this.cboCury.Value.Trim().Length != 0)
            {
                paraDict.Add("C_DC_CODE", this.cboCury.Value);
            }

            if (this.txtOpenAccNo.Text.Trim().Length != 0)
            {
                paraDict.Add("C_OPEN_ACC_NO_LIKE", "%" + this.txtOpenAccNo.Text.Trim() + "%");
            }
            ////  STORY #55233 【富国基金】支付产品账户信息新增一个列
            if (this.cboOrgan.Value != null && this.cboOrgan.Value.Trim().Length != 0)
            {
                paraDict.Add("C_ORG_CODE", this.cboOrgan.Value);
            }
            ////// STORY #27326 2015-11-18资金管理系统（批量筛选并删除长时间未使用过的银行账户需求）
            ////if (this.cboDateRange.Value != null)
            ////{
            ////    string days = string.Empty;
            ////    switch (this.cboDateRange.Value)
            ////    {
            ////        case "FUNDACC_1M":
            ////            days = "30";
            ////            break;
            ////        case "FUNDACC_3M":
            ////            days = "90";
            ////            break;
            ////        case "FUNDACC_HALF_Y":
            ////            days = "180";
            ////            break;
            ////        case "FUNDACC_1Y":
            ////            days = "365";
            ////            break;
            ////    }

            ////    if (!string.IsNullOrEmpty(days))
            ////    {
            ////        paraDict.Add("N_DAYS1", days);
            ////        paraDict.Add("N_DAYS2", days);
            ////    }
            ////}
            ////STORY #76264 【产品账户信息】新增账户功能优化
            ////工单 #1893 估值_V1.300.7.0_UI自动化测试_自动化测试(261)  产品基本信息 下方功能保存审核反审核都报错
            if (this.showPubAcc.Checked && !this._hadBeenRelationed)
            {
                paraDict.Add("SHOW_PUB_ACC", "SHOW_PUB_ACC");
            }
            ////STORY #93234 银行账户信息界面新增查询条件
            if (this.txtOpenAddr.Text != null && this.txtOpenAddr.Text.Trim().Length != 0)
            {
                paraDict.Add("C_OPEN_ADDR", "%" + this.txtOpenAddr.Text + "%");
            }

            return paraDict;
        }

        /// <summary>
        /// 重写 基类
        /// </summary>
        /// <param name="paraDict">paradict</param>
        /// <returns> paradict</returns>
        protected override Dictionary<string, string> OnAfterGetParaAssemble(Dictionary<string, string> paraDict)
        {
            if (!paraDict.ContainsKey("dataClass"))
            {
                paraDict.Add("dataClass", "FundAcc");
            }

            if (this._mainDataPojo is Port)
            {
                if (!paraDict.ContainsKey("ARRAY_C_PORT_CODE") || string.IsNullOrEmpty(paraDict["ARRAY_C_PORT_CODE"]))
                {
                    if (null != this.geneParaAssemble && this.geneParaAssemble.ContainsKey("ARRAY_C_PORT_CODE"))
                    {
                        paraDict.Remove("ARRAY_C_PORT_CODE");
                        paraDict.Add("ARRAY_C_PORT_CODE", this.geneParaAssemble["ARRAY_C_PORT_CODE"]);
                    }
                }
            }
            else if (this._mainDataPojo is Org)
            {
                if (!paraDict.ContainsKey("C_ORG_CODE") || string.IsNullOrEmpty(paraDict["C_ORG_CODE"]))
                {
                    if (null != this.geneParaAssemble && this.geneParaAssemble.ContainsKey("C_ORG_CODE_HOLDER1"))
                    {
                        if (paraDict.ContainsKey("C_ORG_CODE_HOLDER1"))
                        {
                            paraDict.Remove("C_ORG_CODE_HOLDER1");
                            paraDict.Remove("C_ORG_CODE_HOLDER2");
                        }

                        paraDict.Add("C_ORG_CODE_HOLDER1", this.geneParaAssemble["C_ORG_CODE_HOLDER1"]);
                        paraDict.Add("C_ORG_CODE_HOLDER2", this.geneParaAssemble["C_ORG_CODE_HOLDER2"]);
                    }
                }
            }

            return paraDict;
        }

        #endregion

        /// <summary>
        /// 重写删除,STORY #35130 招商基金-删除账户信息时如果此账户已被用则需弹出提示框
        /// </summary>
        /// <param name="sender">控 件</param>
        /// <param name="e">事件 信息</param>
        protected override void btnDelete_Click(object sender, EventArgs e)
        {
            string c_open_addr;
            string c_open_acc_no;
            string c_open_acc_name;
            List<string> ids = new List<string>();
            Yss.KMessage.MessageDialog msg = new Yss.KMessage.MessageDialog();
            bool flag = false;
            foreach (Row checkRow in this.tbMain.CheckedRows)
            {
                FundAcc fundAcc = checkRow.Tag as FundAcc;
                if (fundAcc == null)
                {
                    continue;
                }

              
                if (0 == fundAcc.AuditState)
                {
                    c_open_addr = fundAcc.C_OPEN_ADDR;
                    c_open_acc_no = fundAcc.C_OPEN_ACC_NO;
                    c_open_acc_name = fundAcc.C_OPEN_ACC_NAME;
                    ids.Add(fundAcc.Id);

                    string s = string.Empty;
                    try
                    {
                        s = this.fundAccService.queryZfbyPort(c_open_addr, c_open_acc_no, c_open_acc_name);
                    }
                    catch (System.Exception ex)
                    {
                        YssMessageBox.ShowCommonInfo(ex.Message);
                    }

                    if ("true".Equals(s))
                    {
                        flag = true;
                        break;
                    }
                }
               
              
            }

            if (flag)
            {
                DialogResult dr = msg.Show("该账户已关联，是否继续删除", "提示", MessageBoxButtons.YesNo);
                if (dr == DialogResult.Yes)
                {
                    base.btnDelete_Click(sender, e);
                    ////STORY #41401 产品信息-产品账户设置，批量关联账户
                    if (this.fundAccService == null)
                    {
                        Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
                        this.fundAccService = ServiceFactory.createService(serviceType) as IFundAccService;
                    }

                    this.fundAccService.deleteByRealId(ids.ToArray());
                }
            }
            else
            {
                ////删除选否的时候，也把关联信息删除了
                ////base.btnDelete_Click(sender, e);

                try
                {
                    bool delResult = operAdaptMVC(sender, e, ClsEnums.StatusSetting.YssDel);
                    if (delResult)
                    {
                        ////proBar.Value = proBar.Minimum;
                        ////proBar.Visible = false;
                        ////proBar.Owner.Refresh();

                        ////double time = Math.Round((this._operEndTime - this._operBeginTime).TotalSeconds, 4);
                        ////SetQuerySpendTime(time.ToString());

                        ////STORY #41401 产品信息-产品账户设置，批量关联账户
                        if (this.fundAccService == null)
                        {
                            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
                            this.fundAccService = ServiceFactory.createService(serviceType) as IFundAccService;
                        }

                        this.fundAccService.deleteByRealId(ids.ToArray());
                    }
                }
                catch (System.Exception ex)
                {
                    YssMessageBox.ShowCommonInfos(TransferErrorMessageUtil.getTransferException(ex));
                }
            }

            btnSearch_Click(sender, e);
        }

        /// <summary>
        ///  STORY #76264 【产品账户信息】新增账户功能优化
        /// 按功能菜单对应的配置文件处理表格
        /// </summary>
        /// <param name="poTable">待处理的 表格</param>
        protected override void OnProcessTableByConfig(Table poTable)
        {
            ////读取用户自定义列配置信息
            this.ReadTableColumnsFromConfig(poTable, this.YssFormMenu.C_FUN_CODE);

            ////读取分组列信息。张绍林-20151124
            this.ReadTableGroupColumnFromConfig(poTable, this.YssFormMenu.C_FUN_CODE);

            ////读取用户自定义列宽信息。张绍林-20151201
            this.ReadColumnWidthFromConfig(poTable, this.YssFormMenu.C_FUN_CODE);

            ////STORY #50834 表格右键分级级别，实现本地化 hp 20180816
            ClsInterface.ReadGradingLevelConfigToTable(this.YssFormMenu, poTable);

            ////若选了展示公共户，优选展示与组合关联的数据，不需要重新排序；若没选，则按原逻辑排序
            if (this.showPubAcc.Checked)
            {
            }
            else
            {
                ////STORY #72474 内容区列表头增加排序记忆功能 hp 20190712
                ClsInterface.ReadTableSortColumn(poTable, this.YssFormMenu);
            }
        }

        /// <summary> 
        /// 关联 信息单击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected void btnRela_Click(object sender, EventArgs e)
        {
            if (this.MainDataPojo != null && this.MainDataPojo is YssProductInfo.Support.Ab.Port.Pojo.Port)
            {
                if (ClsContext.sysMenuFunHash.ContainsKey("pd_portfolio"))
                {
                    Frm_PORT_FUND_RELA_S frmSet = new Frm_PORT_FUND_RELA_S();
                    SysFun fun = new SysFun();
                    fun = (SysFun)ClsContext.sysFunHash["pd_portfolio"];
                    frmSet.YssFormMenu = fun;
                    frmSet.selectedPort = this.MainDataPojo as YssProductInfo.Support.Ab.Port.Pojo.Port;
                    frmSet.MinimizeBox = false;
                    frmSet.initControlStat();
                    frmSet.ShowDialog();
                    btnSearch_Click(sender, e);
                }
            }
        }

        /// <summary> 
        /// 修改 二次校验参数控制
        /// </summary>
        /// <param name="sender"> sender </param>
        /// <param name="e"> e </param>
        protected void btnCheckAccInfoAgain_Click(object sender, EventArgs e)
        {
            if (!string.IsNullOrEmpty(this.checkAccInfo))
            {
                if (this.checkAccInfo == "1")
                {
                    this.checkAccInfo = this.fundAccService.updateProperty("0");
                    this.btnBar.getButton("btnCheckAccInfoAgain").Owner.Text = "开启二次检查";
                }
                else
                {
                    this.checkAccInfo = this.fundAccService.updateProperty("1");
                    this.btnBar.getButton("btnCheckAccInfoAgain").Owner.Text = "关闭二次检查";
                }
            }
        }

        /// <summary>
        /// 资金账户信息设置浏览界面装载 事件
        /// 此处 用于在窗体装载的时候初始化公用划款账户服务对象 （IPubAccService）
        /// </summary>
        /// <param name="sender"> sender </param>
        /// <param name="e"> e </param>
        private void Frm_FUND_ACC_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.fundAccService = ServiceFactory.createService(serviceType) as IFundAccService;
            ////STORY #35130 招商基金-删除账户信息时如果此账户已被用则需弹出提示框
            ////this.autoHkzlUnifyPayService = ServiceFactory.createService<IAutoHkzlUnifyPayService>();
            this.dataService = this.fundAccService;
            //// STORY #40860 南方基金-“支付产品账户信息”支持全角输入
            this.txtOpenName.ForbiddenSBC = false;
            this.checkAccInfo = this.fundAccService.readProperty();
            this.addRelaButton();
        }

        /// <summary>
        /// STORY #60287 【富国基金】维护支付产品账户信息账户类型为托管户时，开户机构展示取值优化
        /// </summary>
        /// <param name="sender"> sender </param>
        /// <param name="e"> e </param>
        private void cboOrgan_AfterDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
        }

        /// <summary>
        /// 添加 关联信息按钮
        /// </summary>
        private void addRelaButton()
        {
            ClsButtonInfo btnCheckAccInfoAgain = new ClsButtonInfo();
            btnCheckAccInfoAgain.Image = new Bitmap(FAST.Resource.Resource.Test_L, 24, 24);
            if (this.checkAccInfo == "1")
            {
                btnCheckAccInfoAgain.Text = "关闭二次检查";
            }
            else if (this.checkAccInfo == "0")
            {
                btnCheckAccInfoAgain.Text = "开启二次检查";
            }

            btnCheckAccInfoAgain.Name = "btnCheckAccInfoAgain";
            btnCheckAccInfoAgain.Tooltip = "是否进行账户二次检查";
            btnCheckAccInfoAgain.ClickEvent = this.btnCheckAccInfoAgain_Click;
            this.btnBar.addButton(btnCheckAccInfoAgain, 6);
            ////this.btnBar.setUnVisible("btnCheckAccInfoAgain");
            ClsButtonInfo btnRela = new ClsButtonInfo();
            btnRela.Image = new Bitmap(FAST.Resource.Resource.btnItemSet, 24, 24);
            btnRela.Name = "btnRela";
            btnRela.Text = "选择";
            btnRela.Tooltip = "选择";
            btnRela.ClickEvent = this.btnRela_Click;
            this.btnBar.addButton(btnRela, 0);
            this.btnBar.setButtonEnabled("btnRela", true);
            this.btnBar.setUnVisible("btnRela");
            List<string> list = (List<string>)ClsContext.HtUserOperRight[_formFun.C_FUN_CODE];

            //// BUG #137690 点击"产品基本信息分页"，显示系统异常，未将对象引用设置到对象的实例。 
            //// 增加list非空判断 
            if (list != null && !list.Contains("btnRela"))
            {
                list.Add("btnRela");
            }

            ////STORY #99436 【东证资管】【300.7-1031-1202】产品基本信息下面的新增数据问题
            if (list != null && !list.Contains(ClsButtonName.BtnNew))
            {
                this.btnBar.setButtonEnabled("btnRela", false);
            }

            List<string> lstFun = (List<string>)ClsContext.HtFunRight[_formFun.C_FUN_CODE];
            if (lstFun != null && !lstFun.Contains("btnRela"))
            {
                lstFun.Add("btnRela");
            }
        }

        /// <summary>
        /// BUG #267458 【30.7UI】--账户信息设置修改账户时，机构不能进行查询，加载也很慢
        /// 机构下拉框事件
        /// </summary>
        /// <param name="sender"> sender </param>
        /// <param name="e"> e </param>
        private void cboOrgan_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            cboOrgan.Items.Clear();
            string[] args = new string[] { "ORG_BXZJ", "ORG_SY", "ORG_FDC", "ORG_QTJR", "ORG_GR", "ORG_BXGS", "ORG_CWGS", "ORG_JJGS", "ORG_JYS_QH", "ORG_JYS_ZQ", "ORG_QHGS", "ORG_QT", "ORG_SB", "ORG_SYYH", "ORG_XTGS", "ORG_ZCGL", "ORG_ZQDJJG", "ORG_ZQGS", "" };
            OrgCache orgCache = CacheFactory.CreateCache(CacheGroup.ORG) as OrgCache;
            List<BasePojo> pojos = orgCache.getDataListByTypes(args);

            foreach (BasePojo pojo in pojos)
            {
                Org org = pojo as Org;
                ////STORY #60287 【富国基金】维护支付产品账户信息账户类型为托管户时，开户机构展示取值优化
                if (string.IsNullOrEmpty(this.cboAccType.Value) || !this.cboAccType.Value.Contains("CPZH_TGH")
                        || (!string.IsNullOrEmpty(this.cboAccType.Value) && this.cboAccType.Value.Contains("CPZH_TGH") && (!string.IsNullOrEmpty(org.C_DV_TRUSTEE)
                        || !string.IsNullOrEmpty(org.C_DV_TRUSTEE_MA) || !string.IsNullOrEmpty(org.C_DV_TRUSTEE_SEC))))
                {
                    cboOrgan.Items.Add(new Yss.KRichEx.AutoFilter.Model.KTableEntity(pojo));
                }
            }
        }

        /// <summary>
        /// STORY #57098 银华基金-支付平台-DVP界面若已经导入了成交单需要有标识信息，并提示可以双击打开成交单
        /// </summary>
        /// <param name="rows"> 附件 </param>
        private void setFontColor(RowCollection rows)
        {
            Font font = new Font("宋体", 9, FontStyle.Bold);
            Column fileColumn = null;
            fileColumn = this.tbMain.Columns["N_FILE_COUNT"];
            
            if (fileColumn == null)
            {
                return;
            }

            fileColumn.Width = 100;

            foreach (Row row in rows)
            {
                if (row.SubRows != null && row.SubRows.Count > 0)
                {
                    this.setFontColor(row.SubRows);
                }
                else
                {
                    FundAcc pojo = row.Tag as FundAcc;
                    row.Cells.Remove(row.Cells[fileColumn.Index], true);
                    ButtonCell cell = new ButtonCell();
                    cell.Click += new CellEventHandler(this.btnFile_Click);
                    if (pojo.N_FILE_COUNT > 0)
                    {
                        cell.Font = font;
                        cell.ForeColor = Color.Blue;
                    }

                    cell.Text = "附件管理(" + pojo.N_FILE_COUNT + ")";

                    row.Cells.Insert(fileColumn.Index, cell);
                }
            }
        }

        /// <summary>
        /// 附件管理按钮点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnFile_Click(object sender, CellEventArgs e)
        {
            FundAcc acc = this.tbMain.SelectedRow.Tag as FundAcc;

            Frm_FILEMSG_BOX_S frmFileMsg = new Frm_FILEMSG_BOX_S(acc as BasePojo, "FundAcc");
            if (frmFileMsg.whetherShowBox == true)
            {
                frmFileMsg.ShowDialog();
            }

            bool rs = frmFileMsg.isSave;
            if (rs == true)
            {
                this.btnSearch_Click(sender, e);
            }

            //// 打开附件管理界面
            ////frm_DVP_ATTACH_MGR_S = new Frm_DVP_ATTACH_MGR_S(acc);
            ////frm_DVP_ATTACH_MGR_S.ShowDialog();
            ////frm_DVP_ATTACH_MGR_S.Dispose();
            ////this.btnSearch_Click(sender, e);
        }
    }
}
