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
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;



using System.Collections;
using FAST.Core.BaseControl.Pojo;
using Yss.KTable.Models;
using System.Threading;
using FAST.Common.Service.Services;
using YssInformation.Support.Fun;
using YssInformation.Support.Context;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Sv.Service;
using YssInformation.Support.Bi.Market.Service;
using FAST.Core.CRUD.Interface;
using YssSecInformation.Support.Sv.Pojo;


namespace YssSecInformation.Sv.Form
{
    /// <summary>
    /// 功能简介：债券付息信息浏览界面，负责债券付息信息的显示和查询功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.31
    ///
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：lyh
    /// 修改日期：2011.1.6
    /// 修改简介：实现具体方法
    /// ---修改记录---
    /// 当前版本：v4.5.03
    /// 修改人：wuwenlan 20110223
    /// 修改描述：pojo类的属性发生变化
    /// </summary>
    public partial class Frm_FI_PAY_L : FrmBaseList, IFormDetailData
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
        /// dataService
        /// </summary>
        private IMktDataService mktDataService = null;

        /// <summary>
        /// 债券付息窗体的构造方法
        /// </summary>
        public Frm_FI_PAY_L()
        {
            InitializeComponent();
            bUseMVCService = true;
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
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写.
        /// </summary>
        /// <returns>窗体的加载条件</returns>
        public override string yssInitQuery()
        {
            ////所有提供的参数项如下，只需要设置子类需要的项即可
            string cond = "";

            ////3 初始只加载列头，若需要则设为true，反之不需要设置此参数
            this.IsOnlyHeder = true;

            return cond;
        }

        /// <summary>
        /// 获取list查询条件区的查询条件
        /// </summary>
        /// <returns>字符串</returns>
        public string yssGetListCond()
        {
            string cond = "";
            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
            try
            {
                string search = this.yssBuildLeftCheckRowsStr("base_exchange");  // tanwenjie 2011.7.28 获取A区选中的行

                ////cond = " and c_mkt_code in (" + search + ") ";
                quyStrUtil.addQuyCon("portCode", "C_MKT_CODE", search, "IN");
                ////cond = cond + " and a.D_ADJ  between to_date('" + this.dtpResetInterestDate.getBeginDate.ToString("yyyy-MM-dd") + "','yyyy-MM-dd') and to_date('" + this.dtpResetInterestDate.getEndDate.ToString("yyyy-MM-dd") + "','yyyy-MM-dd')";
                quyStrUtil.addQuyCon("dExr", "D_ADJ", dtpResetInterestDate.getBeginDate.ToString("yyyy-MM-dd").Trim() + "," + dtpResetInterestDate.getEndDate.Date.ToString("yyyy-MM-dd").Trim(), "BETWEEN");
                if (this.selSecurity.Value != null)
                {
                    ////cond = cond + " and a.C_SEC_CODE = '" + selSecurity.Value.Trim() + "'";
                    quyStrUtil.addQuyCon("C_SEC_CODE", this.selSecurity.Value.Trim(), "=");

                }

                cond = quyStrUtil.getQuyStr("base_exchange");
            }
            catch (Exception e)
            {
                ClsBaseException.DiscardException(e);
                ////YssMessageBox.ShowDyanInformation("查询出错", e.Message, "错误信息", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500037", _formFun, status));
            }
            
            return cond;
        }

        /// <summary>
        /// 封装条件对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            string search = this.yssBuildLeftCheckRowsStr("base_exchange");
            search = search.Replace("'", "");
            paraDict.Add("ARRAY_C_MKT_CODE", search);

            paraDict.Add("D_START", dtpResetInterestDate.getBeginDate.ToString("yyyy-MM-dd").Trim());
            paraDict.Add("D_END", dtpResetInterestDate.getEndDate.Date.ToString("yyyy-MM-dd").Trim());
           
            if (this.selSecurity.Value != null && this.selSecurity.Value.Trim().Length > 0)
            {
                paraDict.Add("C_SEC_CODE", this.selSecurity.Value);
            }

            return paraDict;
        }

        /////// <summary>
        /////// list界面加载A区数据，子类重写
        /////// </summary>
        /////// <author>yh 2011.03.01</author>
        /////// <returns>交易市场拼接字符串</returns>
        ////public override string yssGetLeftData()
        ////{
        ////    ////edit by yh 2011.03.09修改数据来源为枚举类型,防止赋值出错
        ////    ClsEnums.DataSrc dataSrc = ClsEnums.DataSrc.SrcCache; // 数据来源是缓存
        ////    string funCode = "exchange"; // 交易市场
        ////    string headKeys = "C_MKT_NAME~!~C_MKT_CODE"; // 自定义列头

        ////    this.matchSearchStr = new string[2] { "C_MKT_NAME", "C_MKT_CODE" }; // 【搜索】功能匹配的属性

        ////    string result = null;
        ////    ////设定左侧数据的加载方式
        ////    YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;
        ////    ////调用由子类提供参数的查询方法
        ////    result = this.yssGetLeftData(dataSrc, funCode, null, headKeys, null);
        ////    return result;
        ////}

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
                leftDataFunCode = AssociaType.base_exchange.ToString();
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
        /// load
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_FI_PAY_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            dataService = (IServiceBus)ServiceFactory.createService(serviceType);

            ////if (!isBatchAudit)
            ////{
            ////    this.addGreButton();
            ////}  
        }

        //// eidt by Yuntao Lau 2015.12.02 默认按钮中有生成按钮，增加权限就可以了，不需要在代码里添加
        /////// <summary>
        /////// 添加生成按钮
        /////// </summary>
        ////private void addGreButton()
        ////{
        ////    ClsButtonInfo btnGenerate = this.btnBar.getButton("btnGernerate");
        ////    if (btnGenerate != null)
        ////    {
        ////        this.btnBar.removeButton(btnGenerate);
        ////    }

        ////    btnGenerate = new ClsButtonInfo();
        ////    btnGenerate.Name = "btnGernerate";
        ////    btnGenerate.Text = "生成";
        ////    btnGenerate.Tooltip = "生成";
        ////    btnGenerate.Image = FAST.Resource.Resource.btnGernerate_L;
        ////    btnGenerate.ClickEvent += new System.EventHandler(this.btnGenerate_Click);
        ////    this.btnBar.addButton(btnGenerate, 15);
        ////    this.btnBar.setButtonEnabled(btnGenerate.Name, false);
        ////    this.btnBar.setButtonVisable(btnGenerate.Name, true);
        ////}

        /// <summary>
        /// 添加生成按钮点击事件
        /// edit by Yuntao Lau 2015.12.02
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnGernerate_Click(object sender, EventArgs e)
        {
            ////List<FiPay> fipayList = new List<FiPay>();
            ////IFiPayService iFiPayService = (IFiPayService)dataService;
            ////try
            ////{
            ////    if (this.tbMain.CheckedRows.Count == 0)
            ////    {
            ////        return;
            ////    }

            ////    foreach (Row row in this.tbMain.CheckedRows)
            ////    {
            ////        FiPay fipay = row.Tag as FiPay;
            ////        if (null == fipay)
            ////        {
            ////            break;
            ////        }

            ////        fipayList.Add(fipay);
            ////    }

            ////    iFiPayService.multipleFiPayInit(fipayList);
            ////    MessageBox.Show("选中的历史付息所在期间的每日利息数据已重新生成!", "提示");
            ////}
            ////catch (ClsBaseException ex)
            ////{
            ////    ClsBaseException.DiscardException(ex);
            ////}
            //// 开线程初始化债券 modified by yh
            Thread bondInitThread = new System.Threading.Thread(delegate()
            {
                bondInitProcess();
            });

            bondInitThread.SetApartmentState(ApartmentState.STA);

            bondInitThread.Start();
        }

        /// <summary>
        /// 开启线程，独立处理债券初始化过程，防止界面卡死
        /// added by yh
        /// </summary>
        private void bondInitProcess()
        {
            List<FiPay> fipayList = new List<FiPay>();
            IFiPayService iFiPayService = (IFiPayService)dataService;
            try
            {
                if (this.tbMain.CheckedRows.Count == 0)
                {
                    return;
                }

                foreach (Row row in this.tbMain.CheckedRows)
                {
                    FiPay fipay = row.Tag as FiPay;
                    if (null == fipay)
                    {
                        break;
                    }

                    fipayList.Add(fipay);
                }
                //// 开始处理后禁用生成按钮
                this.btnBar.setButtonDisabled(ClsButtonName.BtnGernerate);
                this.btnBar.setButtonText(ClsButtonName.BtnGernerate, "正在生成...");
                iFiPayService.multipleFiPayInit(fipayList);
                ////MessageBox.Show("选中的债券每日利息数据已重新生成!", "提示");
                this.LabStatuInfo.Text = "选中的债券每日利息数据已重新生成!";
                this.LabStatuInfo.ForeColor = System.Drawing.Color.Red;
                this.LabStatuInfo.Width = 400;
                 this.LabStatuInfo.Owner.Refresh();
                //// 恢复生成按钮功能
                this.btnBar.setButtonEnabled(ClsButtonName.BtnGernerate, true, false);
                this.btnBar.setButtonText(ClsButtonName.BtnGernerate, "生成");
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 11
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbMain_SelectionChanged(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            this.LabStatuInfo.Text = "";
            base.tbMain_SelectionChanged(sender, e);
        }

        /////// <summary>
        /////// 新增按钮之前判断
        /////// </summary>
        /////// <param name="sender"></param>
        /////// <param name="e"></param>
        ////private void Frm_FI_PAY_L_YssOnBeforeNewClick(object sender, YssBeforeOperEventArgs e)
        ////{
        ////    if (this.tbLeftMain.SelectedRow != null && this.tbMain.Rows.Count > 0)
        ////    { 
        ////    }
        ////}

        /// <summary>
        /// 删除前校验数据
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnDelete_Click(object sender, EventArgs e)
        {
            IFiPayService iFiPayService = (IFiPayService)dataService;
            string flag = iFiPayService.checkDeleteData(getSelectTypeItemListAuditable());
            if (flag.Equals("Fault"))
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("008", _formFun, status));
            }

            base.btnDelete_Click(sender, e);
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
                this.geneParaAssemble.Add("dataClass", "FiPay");
                this.geneParaAssemble.Add("C_SEC_CODE", (mainData as SecBase).C_SEC_CODE);
                this.LabStatuInfo.Text = "";
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
                if (paraDict.ContainsKey("D_START"))
                {
                    paraDict.Remove("D_START");
                }

                if (paraDict.ContainsKey("D_END"))
                {
                    paraDict.Remove("D_END");
                }

                if (paraDict.ContainsKey("ARRAY_C_MKT_CODE"))
                {
                    paraDict.Remove("ARRAY_C_MKT_CODE");
                }
            }

            if (!paraDict.ContainsKey("dataClass"))
            {
                paraDict.Add("dataClass", "FiPay");
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


