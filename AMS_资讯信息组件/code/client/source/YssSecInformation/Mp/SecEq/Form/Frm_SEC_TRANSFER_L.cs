using System;
using System.Collections;
using System.Collections.Generic;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Services;
using FAST.Core.BaseControl.Fun;
using FAST.Core.Communication.Service;
using FAST.Core.Context;
using FAST.Core.CRUD.Form;
using FAST.Core.Util;
using FAST.Core.BaseControl.Pojo;
using YssSecInformation.Support.Mp.SecEq.Service;
using YssSecInformation.Support.Mp.SecEq.Pojo;
using YssSecInformation.Support.Pojo.Sec;
using YssInformation.Support.Bi.Market.Service;
using FAST.Core.Communication.DataService;
using FAST.Core.CRUD.Interface;

namespace YssSecInformation.Mp.SecEq.Form
{
    /// <summary>
    /// chenbo
    /// 2017-06-24
    /// #42948 资讯信息管理组件化拆分
    /// ------------------------------- 
    /// Frm_SEC_TRANSFER_L 的摘要说明。
    /// 作用：证券代码转换，负责证券代码转换的增删改等功能
    ///  
    ///  作者：guohui 
    ///  
    ///  版本：v4.5.0.1
    ///  
    ///  修改内容：窗体重新绘制，功能方法实现
    ///  
    ///  修改日期：20161024
    /// </summary>
    public partial class Frm_SEC_TRANSFER_L : FrmBaseList, IFormDetailData
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
        /// 证券代码转换数据服务对象
        /// </summary>
        private ISecTransferService secTransferService = null;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_SEC_TRANSFER_L()
        {
            this.bUseMVCService = true;
            InitializeComponent();
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
        /// </summary>
        /// <returns >初始化查询条件</returns>
        public override string yssInitQuery()
        {
            // 所有提供的参数项如下，只需要设置子类需要的项即可
            // 1 查询条件
            string cond = "";

            // 3 初始只加载列头，若需要则设为true，反之不需要设置此参数
            this.IsOnlyHeder = true;

            bool display = c_PR_TYPE.Equals("P");
            this.tbFilter.Columns[6].Visible = !display;
            this.tbFilter.Columns[7].Visible = !display;

            return cond;
        }

        /// <summary>
        /// 默认的操作类型
        /// </summary>
        public string c_PR_TYPE = "P";

        /// <summary>
        /// 加载左侧控件数据
        /// </summary>
        public override void yssLoadLeftData()
        {
            IMktDataService mktSVC = null;
            QueryRes res = null;
            ArrayList showColumnList = new ArrayList();
            try
            {
                // edit by yh 2011.03.09修改数据来源为枚举类型,防止赋值出错
                leftDataFunCode = YssInformation.Support.Context.AssociaType.base_exchange.ToString();

                matchSearchStr = new string[2] { "C_MKT_NAME", "C_MKT_CODE" };

                mktSVC = DataServiceFactory.createService<IMktDataService>();
                res = mktSVC.getDataListRes();
                showColumnList.Add("C_MKT_NAME");
                showColumnList.Add("C_MKT_CODE");
                //// 设定左侧数据的加载方式
                YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;

                new TableListLoader().loadTable(tbLeftMain, res, false, false, ClsEnums.KTableDataShowMode.TreeMode, showColumnList);


                ////YssLeftKTableShowMode = YssResources.Fun.ClsEnums.KTableDataShowMode.TreeMode;
                ////result = this.yssGetLeftData(dataSrc, funCode, null, headKeys, null);

            }
            catch (Exception ex)
            {
                ////YssMessageBox.ShowDyanInformation("加载左侧交易市场信息报错", ex.Message, MessageBoxIcon.Error, YssResources.Fun.ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getAareaLoadErr(ex.Message));
            }
        }

        /// <summary>
        /// 窗体LOAd事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_TRANSFER_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.secTransferService = ServiceFactory.createService<ISecTransferService>();
            this.dataService = this.secTransferService;
            ClsButtonInfo btnItemSet = new ClsButtonInfo();

            btnItemSet.Name = ClsButtonName.BtnUnAudit;
            btnItemSet.Text = "设置";
            btnItemSet.Tooltip = "设置";
            btnItemSet.Image = FAST.Resource.Resource.btnItemSet_L;
            btnItemSet.ClickEvent += new System.EventHandler(this.btnItemSet_Click);
            this.btnBar.addButton(btnItemSet, 1);
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

            if (this.cboTransferRule.Value != null && this.cboTransferRule.Text.Trim().Length != 0)
            {
                paraDict.Add("C_TRANSFER_CODE", this.cboTransferRule.Value);

            }

            if (this.cboSecCategory.Value != null && this.cboSecCategory.Value.Trim().Length != 0)
            {
                paraDict.Add("C_SEC_VAR_CODE", "%" + this.cboSecCategory.Value + "%");
            }

            if (this.cboChanType.Value != null && this.cboChanType.Value.Trim().Length != 0)
            {
                paraDict.Add("c_DV_CHAN_TYPE", "%" + this.cboChanType.Value + "%");
            }

            bool display = c_PR_TYPE.Equals("P");
            if (display)
            {
                paraDict.Add("c_SEC_TYPE", "P");
            }
            else
            {
                paraDict.Add("c_SEAT_TYPE", "R");
            }

            return paraDict;
        }

        /// <summary>
        /// 选项设置 edt by gh 20170122 STORY38131【南方基金】【紧急】社保理事会要求交易席位科目是6位，不足6位前面补0
        /// </summary>
        /// <param name="sender">s</param>
        /// <param name="e">e</param>
        protected override void btnItemSet_Click(object sender, EventArgs e)
        {
            Frm_TRANSFER_PARA paraSet = new Frm_TRANSFER_PARA();
            paraSet._fun = this._formFun;

            if (paraSet.ShowDialog() == System.Windows.Forms.DialogResult.OK)
            {
            }
        }

        /// <summary>
        /// 设置参数
        /// </summary>
        /// <returns>参数集合</returns>
        public override Dictionary<string, string> setExtraCondition()
        {
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            ////paraDict.Add("C_EQU_CLS", "DJ");
            return paraDict;
        }

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

            //// modified by HeLiang 2017-07-28 BUG #167504 【证券代码转换】界面作为下半区分页显示问题
            ////【证券代码转换】作为下半区详细界面初始化时，“交易证券”和“交易渠道”分页没有判断所属界面并进行隐藏
            //// 因此调整了IFormDetailData接口的InitializeDetailForm方法，传入FrmBaseListWithDetails父容器，并用于下面的判断
            if (null != parent)
            {
                if (parent is YssSecInformation.Sv.Form.Frm_SEC_BASE_ZQ_L)
                {
                    this.tabPageJYQD.Visible = false;
                }
                ////else if (parent is YssPara.Form.Ab.Frm_TD_CHAN_L)
                ////{
                ////    this.tabPageJYZQ.Visible = false;
                ////}
            }
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
            ////债券基本信息时
            if (validate && mainData is SecBase)
            {
                this.tabPageJYQD.Visible = false;
                c_PR_TYPE = "P";
                this.tabPageJYZQ.Controls.Add(this.tbMain);
                viewQueryCond();
                viewTbMainCols();
                this.AllowResetGeneParaAssemble = false;
                this.MainDataPojo = mainData;
                this.geneParaAssemble.Clear();
                this.geneParaAssemble.Add("dataClass", "SecTransfer");
                this.geneParaAssemble.Add("c_SEC_TYPE", "P");
                this.geneParaAssemble.Add("C_SEC_CODE", (mainData as SecBase).C_SEC_CODE);

                ////验证通过，开始装载数据
                this.btnSearch_Click(this.btnBar.getButton(FAST.Core.BaseControl.Fun.ClsButtonName.BtnRefresh).Owner, EventArgs.Empty);
            }

            ////交易渠道时
            if (validate && mainData is TdChan)
            {
                this.tabPageJYZQ.Visible = false;
                c_PR_TYPE = "R";
                this.tabPageJYQD.Controls.Add(this.tbMain);
                viewQueryCond();
                viewTbMainCols();
                this.AllowResetGeneParaAssemble = false;
                this.MainDataPojo = mainData;
                this.geneParaAssemble.Clear();
                this.geneParaAssemble.Add("dataClass", "SecTransfer");
                this.geneParaAssemble.Add("c_SEAT_TYPE", "R");
                this.geneParaAssemble.Add("C_SEC_CODE", (mainData as TdChan).C_TD_CHAN_CODE);

                ////验证通过，开始装载数据
                this.btnSearch_Click(this.btnBar.getButton(FAST.Core.BaseControl.Fun.ClsButtonName.BtnRefresh).Owner, EventArgs.Empty);
            }
        }

        /// <summary>
        ///  分页改变时的操作
        ///  add by guohui STORY38131【南方基金】【紧急】社保理事会要求交易席位科目是6位，不足6位前面补0
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void tabConMain_SelectedIndexChanged(object sender, Yss.Controls.TabPageEventArgs e)
        {
            if (tabConMain.SelectedTab == this.tabPageJYZQ)
            {
                c_PR_TYPE = "P";
                this.tabPageJYZQ.Controls.Add(this.tbMain);
                viewQueryCond();
                btnSearch_Click(sender, new EventArgs());
                viewTbMainCols();
            }
            else if (tabConMain.SelectedTab == this.tabPageJYQD)
            {
                c_PR_TYPE = "R";
                this.tabPageJYQD.Controls.Add(this.tbMain);
                viewQueryCond();
                btnSearch_Click(sender, new EventArgs());
                viewTbMainCols();
            }
        }

        /// <summary>
        /// 切换表列
        /// </summary>
        private void viewTbMainCols()
        {
            bool display = c_PR_TYPE.Equals("P");
            if (tbMain.Columns.Count > 0)
            {
                this.tbMain.Columns[2].Visible = display;
                this.tbMain.Columns[3].Visible = !display;
            }

            this.tbMain.Refresh();
            this.tbMain.AutoWidth();

        }

        /// <summary>
        /// 显示证券代码及币种代码查询条件
        /// </summary>
        private void viewQueryCond()
        {
            bool display = c_PR_TYPE.Equals("P");
            this.tbFilter.Columns[3].Visible = display;
            this.tbFilter.Columns[4].Visible = display;
            this.tbFilter.Columns[4].Width = 120;
            this.tbFilter.Columns[6].Visible = !display;
            this.tbFilter.Columns[7].Visible = !display;
            this.tbFilter.Columns[7].Width = 120;

            if (display)
            {
                this.cboChanType.Value = null;
            }
            else
            {
                this.cboSecCategory.Value = null;
            }

            this.tbFilter.Refresh();
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
                if (paraDict.ContainsKey("ARRAY_C_MKT_CODE"))
                {
                    paraDict.Remove("ARRAY_C_MKT_CODE");
                }

            }

            if (!paraDict.ContainsKey("dataClass"))
            {
                paraDict.Add("dataClass", "SecTransfer");
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