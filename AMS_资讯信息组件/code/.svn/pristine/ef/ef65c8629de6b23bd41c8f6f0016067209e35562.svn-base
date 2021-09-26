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


using FAST.Core.BaseControl.Pojo;
using System.Threading;
using Yss.KTable.Models;
using FAST.Common.Service.Services;
using YssSecInformation.Support.Pojo.Sec;
using YssInformation.Support.Bi.Market.Service;
using YssSecInformation.Support.Mp.PreStock.Pojo;
using YssSecInformation.Support.Mp.PreStock.Service;
using FAST.Core.CRUD.Interface;

namespace YssSecInformation.Mp.PreStock.Form
{
    /// <summary>
    /// 功能简介：优先股业务浏览界面，负责优先股计息信息的显示和查询功能的实现
    /// 创建版本：V1.20.4.7
    /// 创建人： liyongjun
    /// 创建日期： 2015.12.07
    /// </summary>
    public partial class Frm_SEC_EQU_YXG_L : FrmBaseList, IFormDetailData
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
        /// 构造函数
        /// </summary>
        public Frm_SEC_EQU_YXG_L()
        {
            InitializeComponent();
            this.bUseMVCService = true;
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
        /// 封装条件对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            string search = this.yssBuildLeftCheckRowsStr("base_exchange");
            search = search.Replace("'", "");
            paraDict.Add("ARRAY_C_MKT_CODE", search);

            if (this.selSecurity.Value != null && this.selSecurity.Value.Trim().Length > 0)
            {
                paraDict.Add("C_SEC_CODE", this.selSecurity.Value);
            }

            return paraDict;
        }

        /// <summary>
        /// 复写审核按钮 弹出对应是否重新初始化付息及每百元利息确认提示信息
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnAudit_Click(object sender, EventArgs e)
        {
            if (this.tbMain.CheckedRows.Count == 0)
            {
                return;
            }

            List<PreStockInterest> preList = new List<PreStockInterest>();
            IPreStockInterestService iPreStockInterestService = ServiceFactory.createService<IPreStockInterestService>();
            foreach (Row row in this.tbMain.CheckedRows)
            {
                if (row.Tag == null)
                {
                    break;

                }

                PreStockInterest pre = row.Tag as PreStockInterest;
                ////只有当优先股的核算类型为金融负债的时候才生成历史付息和每日利息
                if (pre.C_DV_ACCOUNT_CODE == "HSLX_JRFZ")
                {
                    preList.Add(pre);
                }
            }

            base.btnAudit_Click(sender, e);
            if (preList.Count > 0)
            {
                iPreStockInterestService.multiplePreInitFi(preList);
                this.LabStatuInfo.Text = "优先股历史付息信息与优先股每日利息数据已重新生成!";
                this.LabStatuInfo.ForeColor = System.Drawing.Color.Red;
                 this.LabStatuInfo.Owner.Refresh();
            }
        }

        /// <summary>
        /// 开启线程，独立处理优先股初始化过程，防止界面卡死
        /// </summary>
        private void bondInitProcess()
        {
            List<PreStockInterest> preList = new List<PreStockInterest>();
            IPreStockInterestService iPreStockInterestService = (IPreStockInterestService)dataService;
            try
            {
                if (this.tbMain.CheckedRows.Count == 0)
                {
                    return;
                }

                foreach (Row row in this.tbMain.CheckedRows)
                {
                    //// 从前台获取优先股实体类对象
                    PreStockInterest pre = row.Tag as PreStockInterest;


                    if (null == pre)
                    {
                        break;
                    }

                    if (pre.C_DV_ACCOUNT_CODE.Equals("HSLX_JRFZ"))
                    {
                        preList.Add(pre);
                    }              
                }
                //// 开始处理后禁用生成按钮
                this.btnBar.setButtonDisabled(ClsButtonName.BtnGernerate);
                this.btnBar.setButtonText(ClsButtonName.BtnGernerate, "正在生成...");
                iPreStockInterestService.multiplePreInitFi(preList);
                this.LabStatuInfo.Text = "选中优先股的历史付息信息与优先股每日利息数据已重新生成!";
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
        /// 添加生成按钮
        /// </summary>
        private void addGreButton()
        {
            ClsButtonInfo btnGenerate = this.btnBar.getButton("btnGernerate");
            if (btnGenerate != null)
            {
                this.btnBar.removeButton(btnGenerate);
            }

            btnGenerate = new ClsButtonInfo();
            btnGenerate.Name = "btnGernerate";
            btnGenerate.Text = "生成";
            btnGenerate.Tooltip = "生成";
            btnGenerate.Image = FAST.Resource.Resource.btnGernerate_L;
            btnGenerate.ClickEvent += new System.EventHandler(this.btnGenerate_Click);
            this.btnBar.addButton(btnGenerate, 15);
            this.btnBar.setButtonEnabled(btnGenerate.Name, false);
            this.btnBar.setButtonVisable(btnGenerate.Name, true);
        }


        /// <summary>
        /// 添加生成按钮点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnGenerate_Click(object sender, EventArgs e)
        {
            Thread bondInitThread = new System.Threading.Thread(delegate()
            {
                bondInitProcess();
            });

            bondInitThread.SetApartmentState(ApartmentState.STA);

            bondInitThread.Start();
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
                this.geneParaAssemble.Add("dataClass", "PreStockInterest");
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
                paraDict.Add("dataClass", "PreStockInterest");
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


