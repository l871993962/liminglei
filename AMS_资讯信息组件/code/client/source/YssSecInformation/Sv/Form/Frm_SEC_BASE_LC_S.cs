﻿using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;

using FAST.Common.Service.Interface;
using FAST.Common.Service.Pojo.Base;
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





using FAST.Core.BaseControl;


using Yss.KTable.Models;
using FAST.Core.Communication.DataService;
using YssSecInformation.Support.Sv.Service;
using YssInformation.Support.Bi.AssociationOrgan.Pojo;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Func;
using YssSecInformation.Func;


namespace YssSecInformation.Sv.Form
{
    /// <summary>
    /// 功能简介：理财产品信息设置，负责理财产品信息的增删改查等功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.16
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：chenyoulong
    /// 修改日期：20101216
    /// 修改简介：方法的具体实现
    ///－－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：zhuangyuchen
    /// 修改日期： 2011-2-20
    /// 修改简介：
    /// 1:增加回收站开启关闭机制
    /// 2:删除添加修改等操作的操作成功信息
    /// 3：添加注释
    /// 4：删除初始化加载下拉框的代码，改成在控件的属性中设置
    /// 6：修改出错的提示信息
    /// 7:去掉增删改查成功的提示信息，由基类来提供
    /// 8:修改了POJO类为公共类
    /// 9：增加传到后台去的列头和窗体菜单
    /// 10:增加公共表查询的分类查询的条件
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：yh
    /// 修改日期： 2011.02.24
    /// 修改简介： 调整代码为新的结构
    ///  －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：wuwenlan
    /// 修改日期： 2011.03.2
    /// 修改简介： 实现对交易市场控件的控制
    ///            控件按需求进行调整控制
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.5
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         返回对象错误直接抛出异常
    /// </summary>
    public partial class Frm_SEC_BASE_LC_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private ISecBaseLcService service;

        /// <summary>
        /// (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335 
        /// 获取设计时核算元素控件的布局信息
        /// </summary>
        private List<ControlAutoLayout> _designControlsLayout = null;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_SEC_BASE_LC_S()
        {
            InitializeComponent();
            dtpToList.setDateTime(Convert.ToDateTime("1900-1-1"));
            dtpOffList.setDateTime(Convert.ToDateTime("9998-12-31"));
            ////(合并代码 STORY24925)add by dingshlau 20160405 STORY #28335 
            this.yssDateTimeFX.setDateTime(Convert.ToDateTime("1900-1-1"));
            this.yssDateTimeDQ.setDateTime(Convert.ToDateTime("9998-12-31"));
            bUseMVCService = true;
            ////默认选择标准资产
            this.tabControl1.SelectedTab = this.tabPaBZZC;
            ////显示备注字段。张绍林-20150706
            this.ShowDescription = true;
        }

        /// <summary>
        ///  初始化set界面，并给各控件赋值.
        ///  wuwenlan.
        ///  20111302.
        /// </summary>
        public override void yssInitCtlAttr()
        {
            try
            {
                ////(合并代码 STORY24925)add by dingshlau 20160405 STORY #28335 
                ////获取设计时的元素布局信息
                this._designControlsLayout = ControlsLayoutLoader.GetDesignControlLayout(this.tableFBZC1);
                //// this.yssDateTimeFX.setDateTime(Convert.ToDateTime("1900-1-1"));
                ////this.yssDateTimeDQ.setDateTime(Convert.ToDateTime("9998-12-31"));
                this.yssTextMM.Width = 59;
                ////modify by dingshalu 20160428   BUG #130031 理财产品信息增加的功能导致现有功能有问题
                ////处理逻辑：当选择标准资产时，非标资产存在默认值比较容易引起误解，先将默认值去掉
                ////this.yssSelLLLX.Value = "RTA_FIX";
                ////this.yssSelJXGS.Value = "FI_A_365";
                //// this.choice("RTA_FIX");
                this.AutoLayoutFBZC();
                if (this.status == ClsEnums.StatusSetting.YssAdd)
                {
                    this.txtSecCode.Text = "";
                    this.cboInvestment.Text = "";
                    this.dtpOffList.setDateTime(Convert.ToDateTime("9998-12-31"));
                }
            }
            catch (ClsBaseException e)
            {
                ClsBaseException.DiscardException(e);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFormLoadErr(this.Text));
            }

        }

        /// <summary>
        /// 封装界面元素为pojo对象
        /// (合并代码 STORY24925) modify by dingshalu 20160406 STORY #28335 保险资管业务-非标（债权、信托）支持存款计息模式
        /// </summary>
        /// <returns>由界面元素组成的对象</returns>
        public override AuditableParamPojo faceInfoToObjMVC()
        {
            SecBase sec = null;
            try
            {
                sec = new SecBase();

                //// V4.5广发2014年3月6日01_B xzl
                //// 收益类型为净值行情时 起息日期不可操作，为保证数据日期校验通过起息日期赋值等遇到期日期
                if (this.cboIncomeType.Value.Equals("NETWORTH"))
                {
                    this.dtpCountBeginDate.setDateTime(this.dtpToList.getBeginDate);
                    this.dtpCountEndDate.setDateTime(this.dtpToList.getBeginDate);
                }

                if (this.tabControl1.SelectedTab == this.tabPaBZZC)
                {
                    ////保存的时候判断退市日期是否大于上市日期
                    if (ClsFunction.sub(this.dtpToList.getBeginDate, this.dtpOffList.getBeginDate) > 0)
                    {
                        ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("001", _formFun, ClsEnums.StatusSetting.YssSave));
                        TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                        transferErrorMessage.MESSAGESLINK = new List<string>();
                        /* 组装提示信息对象ErrorMessage */
                        string errorMess = ClsRetInfoDealer.getExtWarns("001", _formFun, ClsEnums.StatusSetting.YssSave);
                        transferErrorMessage.MESSAGESLINK.Add(errorMess);
                        throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                    }

                    if (ClsFunction.sub(this.dtpCountBeginDate.getBeginDate, this.dtpCountEndDate.getBeginDate) > 0)
                    {
                        ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("004", _formFun, ClsEnums.StatusSetting.YssSave));
                        TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                        transferErrorMessage.MESSAGESLINK = new List<string>();
                        /* 组装提示信息对象ErrorMessage */
                        string errorMess = ClsRetInfoDealer.getExtWarns("004", _formFun, ClsEnums.StatusSetting.YssSave);
                        transferErrorMessage.MESSAGESLINK.Add(errorMess);
                        throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                    }

                    if (ClsFunction.sub(this.dtpToList.getBeginDate, this.dtpCountBeginDate.getBeginDate) > 0)
                    {
                        ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("005", _formFun, ClsEnums.StatusSetting.YssSave));
                        TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                        transferErrorMessage.MESSAGESLINK = new List<string>();
                        /* 组装提示信息对象ErrorMessage */
                        string errorMess = ClsRetInfoDealer.getExtWarns("005", _formFun, ClsEnums.StatusSetting.YssSave);
                        transferErrorMessage.MESSAGESLINK.Add(errorMess);
                        throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                    }

                    if (ClsFunction.sub(this.dtpToList.getBeginDate, this.dtpCountEndDate.getBeginDate) > 0)
                    {
                        ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("006", _formFun, ClsEnums.StatusSetting.YssSave));
                        TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                        transferErrorMessage.MESSAGESLINK = new List<string>();
                        /* 组装提示信息对象ErrorMessage */
                        string errorMess = ClsRetInfoDealer.getExtWarns("006", _formFun, ClsEnums.StatusSetting.YssSave);
                        transferErrorMessage.MESSAGESLINK.Add(errorMess);
                        throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                    }
                }

                if (this.tabControl1.SelectedTab == this.tabPaFBZC)
                {
                    ////保存的时候判断退市日期是否大于上市日期
                    if (ClsFunction.sub(this.yssDateTimeFX.getBeginDate, this.yssDateTimeDQ.getBeginDate) > 0)
                    {
                        ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("001", _formFun, ClsEnums.StatusSetting.YssSave));
                        TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                        transferErrorMessage.MESSAGESLINK = new List<string>();
                        /* 组装提示信息对象ErrorMessage */
                        string errorMess = ClsRetInfoDealer.getExtWarns("001", _formFun, ClsEnums.StatusSetting.YssSave);
                        transferErrorMessage.MESSAGESLINK.Add(errorMess);
                        throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                    }

                    if (ClsFunction.sub(this.yssDateTimeQX.getBeginDate, this.yssDateTimeJX.getBeginDate) > 0)
                    {
                        ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("004", _formFun, ClsEnums.StatusSetting.YssSave));
                        TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                        transferErrorMessage.MESSAGESLINK = new List<string>();
                        /* 组装提示信息对象ErrorMessage */
                        string errorMess = ClsRetInfoDealer.getExtWarns("004", _formFun, ClsEnums.StatusSetting.YssSave);
                        transferErrorMessage.MESSAGESLINK.Add(errorMess);
                        throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                    }

                    if (ClsFunction.sub(this.yssDateTimeFX.getBeginDate, this.yssDateTimeQX.getBeginDate) > 0)
                    {
                        ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("005", _formFun, ClsEnums.StatusSetting.YssSave));
                        TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                        transferErrorMessage.MESSAGESLINK = new List<string>();
                        /* 组装提示信息对象ErrorMessage */
                        string errorMess = ClsRetInfoDealer.getExtWarns("005", _formFun, ClsEnums.StatusSetting.YssSave);
                        transferErrorMessage.MESSAGESLINK.Add(errorMess);
                        throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                    }

                    if (ClsFunction.sub(this.yssDateTimeFX.getBeginDate, this.yssDateTimeJX.getBeginDate) > 0)
                    {
                        ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("006", _formFun, ClsEnums.StatusSetting.YssSave));
                        TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                        transferErrorMessage.MESSAGESLINK = new List<string>();
                        /* 组装提示信息对象ErrorMessage */
                        string errorMess = ClsRetInfoDealer.getExtWarns("006", _formFun, ClsEnums.StatusSetting.YssSave);
                        transferErrorMessage.MESSAGESLINK.Add(errorMess);
                        throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                    }

                }

                // 基本信息 
                sec.C_SEC_CODE = this.txtSecCode.Text;
                sec.C_SEC_ISIN_CODE = this.txtIsinCode.Text;
                sec.C_SEC_MKT_CODE = this.txtSecMktCode.Text;
                sec.C_SEC_NAME = this.txtSecName.Text;
                sec.C_SEC_VAR_CODE = this.cboSecVar.Value;
                sec.C_MKT_CODE = this.cboMkt.Value;
                sec.C_DC_CODE = this.cboCury.Value;
                sec.N_FV_ISSUE = this.txtFvIssue.Value;
                sec.N_AMOUNT_HD = this.txtAmountHD.Value;
                sec.N_PRICE_FCR = this.txtPriceFcr.Value;
                sec.C_DV_AI_MOD = this.cboAIMode.Value == null ? " " : this.cboAIMode.Value;
                sec.C_ORG_CODE = this.cboOrgan.Value; // 所属机构
                sec.C_DV_ISSUE = this.cboFHZT.Value == null ? " " : this.cboFHZT.Value; // 分红转投类型
                sec.N_PRICE_ISSUE = this.iniDate.Text; // 偏移天数
                sec.C_DV_ASSURE = this.cboDateType.Value == null ? " " : this.cboDateType.Value; // 日期类型
                sec.C_ETF_TYPE = this.cboETFType.Value == null ? " " : this.cboETFType.Value; // ETF类型
                sec.C_DV_PI_MOD = this.cboIncomeType.Value; // 收益类型
                sec.C_DESC = this.TxtDescription.Text;
                sec.N_RATIO = this.iniRateYearDays.Value.ToString(); // 利率年化天数
                // 计息信息标准资产
                if (this.tabControl1.SelectedTab == this.tabPaBZZC)
                {
                    sec.C_DV_QUT_MOD = this.cboInvestment.Value == null ? " " : this.cboInvestment.Value; // 投资方式
                    sec.N_FV_IR = this.txtCouponRate.Text; // 票面利率
                    sec.N_RATE = ClsFunction.div(this.txtTaxRate.Text, "100"); // 税率
                    sec.C_DV_VAR_DUR = this.cboPayFrequency.Value; // 付息频率
                    sec.C_DV_AI_EXPR = this.cboPayFormula.Value; // 计息公式
                    sec.D_AI_BEGIN = this.dtpCountBeginDate.getBeginDateStr; // 起息日期
                    sec.D_AI_END = this.dtpCountEndDate.getBeginDateStr; // 截息日期
                    sec.C_CREDIT_RATING = this.cboBackward.Value == null ? " " : this.cboBackward.Value; // 倒置算法
                    sec.Modifier = ClsContext.currentUser.C_USER_CODE;
                    sec.ModifyDate = DateTime.Now.ToString("yyyyMMdd hh:mm:ss");
                    sec.C_JTJZ4STD_SETT = this.yssSelCombox_jtjz4std.Value; // 计提基准

                    // 成立日期
                    sec.D_TO_LIST = this.dtpToList.getBeginDate;
                    sec.D_OFF_LIST = this.dtpOffList.getBeginDate;
                }
                else
                {
                    ////非标资产
                    ////add by chenchen 2016.4.28 BUG #130011 到期理财损益结转报错
                    sec.C_DV_QUT_MOD = this.cboInvestment.Value == null ? " " : this.cboInvestment.Value; // 投资方式
                    ////sec.C_DV_QUT_MOD = " "; // 投资方式
                    sec.N_FV_IR = "0.0"; // 票面利率
                    sec.N_RATE = "0.0"; // 税率
                    sec.C_DV_VAR_DUR = " "; // 付息频率

                    sec.C_DV_RTA = this.yssSelLLLX.Value; ////利率类型
                    sec.C_EXPR_CODE = this.yssSelLLDM.Value == null ? " " : this.yssSelLLDM.Value; ////利率代码
                    sec.C_DV_AI_EXPR = this.yssSelJXGS.Value; ////计息公式
                    sec.N_RATE = ClsFunction.div(this.improvedTextBoxSLV.Text, "100"); //// 税率
                    sec.D_AI_BEGIN = this.yssDateTimeQX.getBeginDateStr; ////起息日期
                    sec.D_AI_END = this.yssDateTimeJX.getBeginDateStr; //// 截息日期
                    sec.C_CREDIT_RATING = this.yssSelDZ.Value == null ? " " : this.yssSelDZ.Value; //// 倒置算法
                    sec.C_JTJZ4NOTSTD_SETT = this.yssSelCombox_jtjz4nostd.Value; // 计提基准

                    if ("RTA_INC".Equals(sec.C_DV_RTA))
                    {
                        sec.N_FV_IR = this.yssTextBoxYJLX.Value; ////应计利息，在票面利率字段显示
                    }

                    if ("RTA_FIX".Equals(sec.C_DV_RTA))
                    {
                        sec.N_FV_IR = Convert.ToString(ClsFunction.toDecimal(this.TextBoxJXLLV.Text)); ////计息利率
                    }

                    sec.N_SPREAD = ClsFunction.div(this.improvedTextBoxLC.Text, "10000");  ////利差

                    if (this.yssSelFX.Value != null && !this.yssSelFX.Value.Equals(" "))
                    {
                        sec.C_JCJG = this.yssSelFX.Value; ////检查间隔
                    }

                    if (this.yssTextMM.Text != null && !this.yssTextMM.Text.Equals("0"))
                    {
                        sec.C_INTERVAL_TIME = this.yssTextMM.Text; ////周期                
                    }

                    if (this.yssTextBoxDay.Text != null && !this.yssTextBoxDay.Text.Equals("0"))
                    {
                        sec.C_INTERVAL_DAY = this.yssTextBoxDay.Text; ////天数
                    }

                    sec.N_UPPER_LIMIT = ClsFunction.div(this.TextBoxLLSX.Text, "100"); ////利率上限
                    sec.N_LOWER_LIMIT = ClsFunction.div(this.TextBoxXX.Text, "100"); ////下限
                    if ("RTA_FLOAT_BASE".Equals(sec.C_DV_RTA) || "RTA_FLOAT_LIKED".Equals(sec.C_DV_RTA))
                    {
                        sec.N_FV_IR = Convert.ToString(ClsFunction.toDecimal(this.TextBoxJZLIL.Text)); ////基准利率
                    }

                    sec.N_BLXS = ClsFunction.div(this.TextBoxBLXS.Text, "100"); ////比例系数
                    sec.D_TO_LIST = this.yssDateTimeFX.getBeginDate;
                    sec.D_OFF_LIST = this.yssDateTimeDQ.getBeginDate;
                }

                ////投资组合 liuxing 2016-5-9 STORY #25484 YSS:债券品种信息和理财品种信息增加组合私有化功能
                sec.C_PORT_CODE = string.IsNullOrEmpty(this.portAndGroup.Value) ? "" : this.portAndGroup.Value;
                //// added by HeLiang 2017-11-15 STORY #43829 新企业会计准则（Ifrs9）解决方案
                sec.C_FINANCE_TOOL= this.cboFinInstruments.Value == null ? " " : this.cboFinInstruments.Value;
            }
            catch (Exception ye)
            {
                if (ye is TransferException)
                {
                    throw ye;
                }
                else
                {
                    throw new ClsBaseException(ye.Message);
                }
            }

            return sec;
        }

        /////// <summary>
        /////// 重写保存按钮点击事件
        /////// </summary>
        /////// <param name="sender">sender</param>
        /////// <param name="e">e</param>
        ////protected override void btnSave_Click(object sender, EventArgs e)
        ////{
        ////    base.btnSave_Click(sender, e);
        ////    ((Frm_SEC_BASE_LC_L)this.frmBaseViewList).btnSearch_Click(sender, e);
        ////}

        /// <summary>
        /// 获取list中选中记录，为界面元素赋值，显示数据
        /// (合并代码 STORY24925) modify by dingshalu 20160406 STORY #28335 保险资管业务-非标（债权、信托）支持存款计息模式
        /// </summary>
        /// <param name="pojo">数据对象</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            try
            {
                SecBase sec = (SecBase)yssGetBaseSelTypeItemMVC();

                ////edit by liuxiang 2013/9/4 bug9337 添加条件C_SEC_VAR_CODE，判断证券类型
                if (null != sec && (sec.C_SEC_VAR_CODE.StartsWith("LC") || sec.C_SEC_VAR_CODE.StartsWith("JJ")))
                {
                    this.txtSecCode.Text = sec.C_SEC_CODE; // 证券内码
                    this.txtIsinCode.Text = sec.C_SEC_ISIN_CODE.Equals("null") ? "" : sec.C_SEC_ISIN_CODE; // ISIN代码
                    this.txtSecMktCode.Text = sec.C_SEC_MKT_CODE; // 上市代码
                    this.txtSecName.Text = sec.C_SEC_NAME; // 证券名称
                    this.cboSecVar.Value = sec.C_SEC_VAR_CODE; // 证券品种
                    this.cboMkt.Value = sec.C_MKT_CODE; // 交易市场
                    this.cboCury.Value = sec.C_DC_CODE; // 交易币种
                    this.txtFvIssue.Text = sec.N_FV_ISSUE; // 发行面值
                    this.txtAmountHD.Text = sec.N_AMOUNT_HD; // 每手数量
                    this.txtPriceFcr.Text = sec.N_PRICE_FCR; // 报价因子
                    this.cboAIMode.Value = sec.C_DV_AI_MOD; // 计息方式
                    this.cboOrgan.Value = sec.C_ORG_CODE; // 所属机构
                    this.cboFHZT.Value = sec.C_DV_ISSUE; // 分红转投类型
                    this.iniDate.Text = sec.N_PRICE_ISSUE; // 偏移天数
                    this.cboDateType.Value = sec.C_DV_ASSURE; // 日期类型
                    this.cboETFType.Value = sec.C_ETF_TYPE; // ETF类型
                    this.cboIncomeType.Value = sec.C_DV_PI_MOD; // 收益类型
                    this.iniRateYearDays.Value = Convert.ToInt32(sec.N_RATIO); // 利率年化天数借用保证金比例
                    this.iniRateYearDays.ReadOnly = true;
                    this.TxtDescription.Text = sec.C_DESC;
                    //// 计息信息标准资产
                    if (sec.C_DV_RTA == null || "".Equals(sec.C_DV_RTA.Trim()))
                    {
                        this.tabControl1.SelectedTab = this.tabPaBZZC;

                        this.cboInvestment.Value = sec.C_DV_QUT_MOD; // 投资方式
                        this.txtCouponRate.Text = sec.N_FV_IR; // 票面利率
                        this.txtTaxRate.Text = ClsFunction.mul(sec.N_RATE, "100"); // 税率
                        this.cboPayFrequency.Value = sec.C_DV_VAR_DUR; // 付息频率
                        this.cboPayFormula.Value = sec.C_DV_AI_EXPR; // 计息公式                  
                        this.cboBackward.Value = sec.C_CREDIT_RATING; // 倒置算法
                        this.yssSelCombox_jtjz4std.Value = sec.C_JTJZ4STD_SETT; // 计提基准
                        if (null != sec.D_AI_BEGIN && !sec.D_AI_BEGIN.Trim().Equals(""))
                        {
                            this.dtpCountBeginDate.setDateTime(Convert.ToDateTime(sec.D_AI_BEGIN));  // 起息日期
                        }

                        if (null != sec.D_AI_END && !sec.D_AI_END.Trim().Equals(""))
                        {
                            this.dtpCountEndDate.setDateTime(Convert.ToDateTime(sec.D_AI_END));  // 截息日期
                        }

                        if (null != sec.D_TO_LIST)
                        {
                            this.dtpToList.setDateTime(sec.D_TO_LIST); // 上市日期
                        }

                        if (null != sec.D_OFF_LIST)
                        {
                            this.dtpOffList.setDateTime(sec.D_OFF_LIST); // 退市日期
                        }
                    }
                    else
                    {
                        this.tabControl1.SelectedTab = this.tabPaFBZC;
                        ////这里要重新刷新下BUG #125443 非标产品计息功能业务需求
                        this.tabControl1.Refresh();
                        ////add by chenchen 2016.4.28 BUG #130011 到期理财损益结转报错
                        this.cboInvestment.Value = sec.C_DV_QUT_MOD; // 投资方式
                        this.yssSelLLLX.Value = sec.C_DV_RTA; ////利率类型

                        this.yssSelLLDM.Value = sec.C_EXPR_CODE; ////利率代码
                        this.yssSelJXGS.Value = sec.C_DV_AI_EXPR; ////计息公式
                        this.improvedTextBoxSLV.Text = ClsFunction.mul(sec.N_RATE, "100");  // 税率
                        if (sec.D_AI_BEGIN != null)
                        {
                            this.yssDateTimeQX.setDateTime(Convert.ToDateTime(sec.D_AI_BEGIN)); // 起息日期
                        }

                        if (sec.D_AI_END != null)
                        {
                            this.yssDateTimeJX.setDateTime(Convert.ToDateTime(sec.D_AI_END)); // 截息日期
                        }

                        this.yssSelDZ.Value = sec.C_CREDIT_RATING; // 倒置算法
                        this.yssSelCombox_jtjz4nostd.Value = sec.C_JTJZ4NOTSTD_SETT; // 计提基准
                        this.yssTextBoxYJLX.Text = sec.N_FV_IR; ////应计利息，在票面利率字段显示
                        this.TextBoxJXLLV.Text = sec.N_FV_IR; ////计息利率
                        this.improvedTextBoxLC.Text = ClsFunction.mul(sec.N_SPREAD, "10000"); ////利差
                        this.choice(sec.C_DV_RTA);
                        this.AutoLayoutFBZC();
                        if (sec.C_JCJG != null && !("").Equals(sec.C_JCJG))
                        {
                            this.yssSelFX.Value = sec.C_JCJG;

                            if (sec.C_INTERVAL_TIME != null && !("").Equals(sec.C_INTERVAL_TIME))
                            {
                                this.yssTextMM.Text = sec.C_INTERVAL_TIME; ////检查间隔
                            }

                            if (sec.C_INTERVAL_DAY != null && !("").Equals(sec.C_INTERVAL_DAY))
                            {
                                this.yssTextBoxDay.Text = sec.C_INTERVAL_DAY; ////周期天数
                            }
                        }

                        this.TextBoxLLSX.Text = ClsFunction.mul(sec.N_UPPER_LIMIT, "100");   ////利率上限
                        this.TextBoxXX.Text = ClsFunction.mul(sec.N_LOWER_LIMIT, "100");  ////下限
                        this.TextBoxJZLIL.Text = sec.N_FV_IR; ////基准利率
                        this.TextBoxBLXS.Text = ClsFunction.mul(sec.N_BLXS, "100");  ////比例系数()
                        if (sec.D_TO_LIST != null)
                        {
                            this.yssDateTimeFX.setDateTime(Convert.ToDateTime(sec.D_TO_LIST));
                        }

                        if (sec.D_OFF_LIST != null)
                        {
                            this.yssDateTimeDQ.setDateTime(Convert.ToDateTime(sec.D_OFF_LIST));
                        }
                    }

                    //////投资组合 liuxing 2016-5-9 STORY #25484 YSS:债券品种信息和理财品种信息增加组合私有化功能
                    this.portAndGroup.Value = sec.C_PORT_CODE;
                    this.iniDate.YssReadOnly = (status == ClsEnums.StatusSetting.YssBrow ? true : false);
                    //// added by HeLiang 2017-11-15 STORY #43829 新企业会计准则（Ifrs9）解决方案
                    this.cboFinInstruments.Value = sec.C_FINANCE_TOOL;
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /////// <summary>
        ///////  证券内码值改变方法，用于获取当用户输入上市代码和交易市场时，根据值改变证券内码.
        ///////  chenyoulong  20110318.
        /////// </summary>
        ////private void txtSecCodeTextChanged()
        ////{
        ////    try
        ////    {
        ////        //// if (this.status == ClsEnums.StatusSetting.YssAdd)
        ////        //// {
        ////        if (this.cboMkt.SelectedItem != null)
        ////        {
        ////        this.txtSecCode.Text = this.txtSecMktCode.Text.Trim() + " " + ((Cls_MKT)this.cboMkt.SelectedItem.DataEntity).C_MKT_NO;
        ////        }

        ////        //// }

        ////    }
        ////    catch (Exception ye)
        ////    {
        ////        ClsBaseException.DiscardException(ye);
        ////        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("002", _formFun, ClsEnums.StatusSetting.YssSave));
        ////    }
        ////}

        /////// <summary>
        ///////  证券内码值改变事件，用于获取当用户输入上市代码和交易市场时，根据值改变证券内码.
        ///////  chenyoulong  20110318.
        /////// </summary>
        /////// <param name="sender">sender.</param>
        /////// <param name="e">e.</param>
        ////private void txtSecMktCode_TextChanged(object sender, EventArgs e)
        ////{
        ////    txtSecCodeTextChanged();
        ////}

        /////// <summary>
        ///////  证券内码值改变事件，用于获取当用户输入上市代码和交易市场时，根据值改变证券内码.
        ///////  chenyoulong  20110318.
        /////// </summary>
        /////// <param name="sender">sender.</param>
        /////// <param name="e">e.</param>
        ////private void cboMkt_SelectedValueChanged(object sender, EventArgs e)
        ////{
        ////    txtSecCodeTextChanged();
        ////}

        /////// <summary>
        /////// 证券内码值改变事件，用于获取当用户输入上市代码和交易市场时，根据值改变证券内码.
        /////// 修改人：liuping.
        /////// 创建人：2011-03-25      BUG #1495 理财产品信息，去掉【交易市场】的数据后，【证券内码】随着变化.
        /////// </summary>
        /////// <param name="sender">sender.</param>
        /////// <param name="e">e.</param>
        ////private void cboMkt_Leave(object sender, EventArgs e)
        ////{
        ////    try
        ////    {
        ////        if (this.status != ClsEnums.StatusSetting.YssBrow)
        ////        {
        ////            if (this.cboMkt.Text.Trim().Length == 0)
        ////            {
        ////                this.txtSecCode.Text = this.txtSecMktCode.Text.Trim();
        ////            }
        ////        }
        ////    }
        ////    catch (Exception ye)
        ////    {
        ////        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("002", _formFun, ClsEnums.StatusSetting.YssSave));
        ////        ClsBaseException.DiscardException(ye);
        ////    }
        ////}

        /// <summary>
        /// load
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_BASE_LC_S_Load(object sender, EventArgs e)
        {
            Type dataServiceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            service = (ISecBaseLcService)ServiceFactory.createService(dataServiceType);
            dataService = service;
            //// 通过下面的类统一实现证券内码的调整处理 byleeyu 20130426
            ClsAdjustSecCode clsAdjustSec = new ClsAdjustSecCode(ref txtSecCode, ref cboMkt, ref txtSecMktCode, this);
            ////通过saveclick生成复习期间，在数据无需审核时无法在数据库中查到添加的数据，so 将代码移动到保存后事件中
            this.YssOnAfterSaveClick += new AfterSaveClick(Frm_SEC_BASE_LC_S_YssOnAfterSaveClick);

            ////新增状态组合不要自动填充 liuxiang 2016-5-24 STORY #25484 YSS:债券品种信息和理财品种信息增加组合私有化功能
            if (this.status == ClsEnums.StatusSetting.YssAdd)
            {
                this.portAndGroup.Value = null;
            }
        }

        /// <summary>
        /// 20141218 jiangjin
        /// 通过saveclick生成复习期间，在数据无需审核时无法在数据库中查到添加的数据，so 将代码移动到保存后事件中
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">message</param>
        private void Frm_SEC_BASE_LC_S_YssOnAfterSaveClick(object sender, YssBeforeOperEventArgs e)
        {
            if (this.cboIncomeType.Value == null || this.cboIncomeType.Value.Equals("NETWORTH"))
            {
                return;
            }

            if (this._formFun.N_CHECK == 0)
            {
                ClsRetInfo info = ClsRetInfoDealer.getCommonHint("HNT-000026", _formFun, status);
                info.setSpecStr("C_SEC_CODE", this.txtSecCode.Text);
                if (YssMessageBox.ShowCommonInfo(info) == DialogResult.Yes)
                {
                    service.singleSecInitFi(this.txtSecCode.Text);
                    ////MessageBox.Show("历史付息信息与每日利息数据已重新生成!", "提示");
                    this.stBarBottom.StatuInfo = "历史付息信息与每日利息数据已重新生成!";
                }
                else
                {
                    ////MessageBox.Show("请手动生成数据", "提示");
                    this.stBarBottom.StatuInfo = "请手动生成数据!";
                }
            }
        }

        /// <summary>
        /// 证券品种控制计息方式
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboSecVar_SelectedValueChanged(object sender, EventArgs e)
        {
            //// STORY19592投资养老金产品核算 xzl 去除关联事件
            ////SecVar secVar = null;            
            ////this.cboAIMode.Text = "";
            ////if (null != sender && ((YssSelCombox)sender).SelectedItem != null)
            ////{
            ////    secVar = ((YssSelCombox)sender).SelectedItem.DataEntity as SecVar;
            ////    if (status != ClsEnums.StatusSetting.YssBrow)
            ////    {
            ////        if (secVar.C_DA_CODE.Equals("JJ_KFS_HBX") || secVar.C_DA_CODE.Equals("LC_HBX")
            ////            || secVar.C_DA_CODE.Equals("JJ_KFS_HBX_JY") || secVar.C_DA_CODE.Equals("JJ_KFS_HBX_SH"))
            ////        {
            ////            this.cboAIMode.YssIsMust = true;
            ////            this.cboAIMode.YssReadOnly = false;
            ////        }
            ////        else
            ////        {
            ////            this.cboAIMode.YssIsMust = false;
            ////            ////this.cboAIMode.YssReadOnly = true;
            ////        }
            ////    }

            ////    if (secVar.C_DA_CODE.Length < 3)
            ////    {
            ////        this.cboInvestment.Items.Clear();
            ////    }
            ////    else if (secVar.C_DA_CODE.Substring(0, 3).Equals("JJ_")) 
            ////    {
            ////        //// 根据品种信息 加载投资方式 词汇
            ////        this.cboInvestment.YssAssociaType = YssResources.Fun.ClsEnums.AssociaType.pubvocabulary;
            ////        this.cboInvestment.MethodInfo.MethodName = "getDataListByTypes";
            ////        this.cboInvestment.MethodInfo.MethodParamValues = new string[] { "JJ_VAR," };
            ////        this.cboInvestment.QueryCond = "JJ_VAR";
            ////    }
            ////    else if (secVar.C_DA_CODE.Substring(0, 3).Equals("LC_"))
            ////    {
            ////        this.cboInvestment.YssAssociaType = YssResources.Fun.ClsEnums.AssociaType.pubvocabulary;
            ////        this.cboInvestment.MethodInfo.MethodName = "getDataListByTypes";
            ////        this.cboInvestment.MethodInfo.MethodParamValues = new string[] { "LC_VAR," };
            ////        this.cboInvestment.QueryCond = "LC_VAR";
            ////    }
            ////    else 
            ////    {
            ////        this.cboInvestment.Items.Clear();
            ////    }
            ////}
            ////(合并代码 STORY24925)add by dingshlau 20160405 STORY #28335 保险资管业务-非标（债权、信托）支持存款计息模式
            if (this.cboSecVar.Value == null || (this.cboSecVar.Value != null && (this.cboSecVar.Value.Equals("JJ") || this.cboSecVar.Value.Equals("LC"))))
            {
                return;
            }

            ////STORY #20257 etf投资区分市场，必须输入etf类型
            //// liuxiang 2015-11-9 BUG #121957 【紧急】资讯品种信息选择证券品种报错
            ////if (this.cboSecVar.Value.Equals("JJ_KFS_ETF"))
            if ("JJ_KFS_ETF".Equals(this.cboSecVar.Value))
            {
                this.cboETFType.YssIsMust = true;
            }
            else
            {
                this.cboETFType.YssIsMust = false;
                this.cboETFType.Value = null;
            }
        }

        /// <summary>
        /// 收益类型控制投资方式
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboIncomeType_SelectedValueChanged(object sender, EventArgs e)
        {
            if (status != ClsEnums.StatusSetting.YssBrow)
            {
                if (null != this.cboIncomeType.Value)
                {
                    if (this.cboIncomeType.Value.Equals("NETWORTH"))
                    {
                        this.txtCouponRate.YssReadOnly = true;
                        this.txtCouponRate.YssIsMust = false;
                        this.txtTaxRate.YssReadOnly = true;
                        this.txtTaxRate.YssIsMust = false;
                        this.cboPayFrequency.YssReadOnly = true;
                        this.cboPayFrequency.YssIsMust = false;
                        this.cboPayFormula.YssReadOnly = true;
                        this.cboPayFormula.YssIsMust = false;
                        this.cboBackward.YssReadOnly = true;
                        this.cboBackward.YssIsMust = false;
                        this.yssSelCombox_jtjz4std.YssReadOnly = true;
                        this.yssSelCombox_jtjz4std.YssIsMust = false;                        

                        this.dtpCountBeginDate.yssEnabled = false;
                        this.dtpCountEndDate.yssEnabled = false;
                        ////STORY #56339 嘉实基金-理财产品信息界面选择净值行情时，起息日和截息日无法修改 
                        this.dtpCountBeginDate.setDateTime(Convert.ToDateTime("1900-1-1"));
                        this.dtpCountEndDate.setDateTime(Convert.ToDateTime("9998-12-31"));
                        ////this.dtpCountBeginDate.setDateTime(this.dtpToList.getBeginDate);
                        ////this.dtpCountEndDate.setDateTime(this.dtpToList.getBeginDate);


                        ////BUG #125999 【深国投】货币基金信息修改后无法保存，为净值行情时，非标资产都不可操作 20160127 edit by LY
                        this.yssSelLLLX.YssReadOnly = true;
                        this.yssSelLLLX.YssIsMust = false;
                        this.yssSelJXGS.YssReadOnly = true;
                        this.yssSelJXGS.YssIsMust = false;
                        this.TextBoxJXLLV.YssReadOnly = true;
                        this.TextBoxJXLLV.YssIsMust = false;
                        this.improvedTextBoxSLV.YssReadOnly = true;
                        this.improvedTextBoxSLV.YssIsMust = false;
                        this.yssSelDZ.YssReadOnly = true;
                        this.yssSelDZ.YssIsMust = false;

                        this.yssDateTimeQX.yssEnabled = false;
                        this.yssDateTimeJX.yssEnabled = false;
                        this.yssDateTimeFX.yssEnabled = false;
                        this.yssDateTimeDQ.yssEnabled = false;
                        this.yssDateTimeFX.setDateTime(Convert.ToDateTime("1900-1-1"));
                        this.yssDateTimeDQ.setDateTime(Convert.ToDateTime("9998-12-31"));
                        this.yssSelCombox_jtjz4nostd.YssReadOnly = true;
                        this.yssSelCombox_jtjz4nostd.YssIsMust = false;
                    }
                    else
                    {
                        this.txtCouponRate.YssReadOnly = false;
                        this.txtCouponRate.YssIsMust = true;
                        this.txtTaxRate.YssReadOnly = false;
                        this.txtTaxRate.YssIsMust = true;
                        this.cboPayFrequency.YssReadOnly = false;
                        this.cboPayFrequency.YssIsMust = true;
                        this.cboPayFormula.YssReadOnly = false;
                        this.cboPayFormula.YssIsMust = true;
                        this.dtpCountBeginDate.yssEnabled = true;
                        this.dtpCountEndDate.yssEnabled = true;
                        this.cboBackward.YssReadOnly = false;
                        this.cboBackward.YssIsMust = true;
                        this.yssSelCombox_jtjz4std.YssReadOnly = false;
                        this.yssSelCombox_jtjz4std.YssIsMust = true;

                        ////BUG #125999 【深国投】货币基金信息修改后无法保存，为净值行情时，非标资产都不可操作 20160127 edit by LY
                        this.yssSelLLLX.YssReadOnly = false;
                        this.yssSelLLLX.YssIsMust = true;
                        this.yssSelJXGS.YssReadOnly = false;
                        this.yssSelJXGS.YssIsMust = true;
                        this.TextBoxJXLLV.YssReadOnly = false;
                        this.TextBoxJXLLV.YssIsMust = true;
                        this.improvedTextBoxSLV.YssReadOnly = false;
                        this.improvedTextBoxSLV.YssIsMust = true;
                        this.yssSelDZ.YssReadOnly = false;
                        this.yssSelDZ.YssIsMust = true;

                        this.yssDateTimeQX.yssEnabled = true;
                        this.yssDateTimeJX.yssEnabled = true;
                        this.yssDateTimeFX.yssEnabled = true;
                        this.yssDateTimeDQ.yssEnabled = true;
                        this.yssSelCombox_jtjz4nostd.YssReadOnly = false;
                        this.yssSelCombox_jtjz4nostd.YssIsMust = true;
                    }
                }
            }
        }

        /// <summary>
        /// 投资方式
        /// </summary>
        /// <param name="sender">投资方式</param>
        /// <param name="e">e</param>
        private void cboInvestment_BeforeDropDownClick(object sender, EventArgs e)
        {
            string secVar = this.cboIncomeType.Value;
            if (secVar.Length < 3)
            {
                this.cboInvestment.Items.Clear();
            }
            else if (secVar.Substring(0, 3).Equals("JJ_"))
            {
                //// 根据品种信息 加载投资方式 词汇
                this.cboInvestment.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
                this.cboInvestment.MethodInfo.MethodName = "getDataListByTypes";
                this.cboInvestment.MethodInfo.MethodParamValues = new string[] { "JJ_VAR," };
                this.cboInvestment.QueryCond = "JJ_VAR";
            }
            else if (secVar.Substring(0, 3).Equals("LC_"))
            {
                this.cboInvestment.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
                this.cboInvestment.MethodInfo.MethodName = "getDataListByTypes";
                this.cboInvestment.MethodInfo.MethodParamValues = new string[] { "LC_VAR," };
                this.cboInvestment.QueryCond = "LC_VAR";
            }
            else
            {
                this.cboInvestment.Items.Clear();
            }
        }

        /// <summary>
        /// btnCopy_Click  BUG #82791 T1.理财品种信息：SET页计息方式权限控制有误 Modifier by :zhengguiyu 20131102
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnCopy_Click(object sender, EventArgs e)
        {
            base.btnCopy_Click(sender, e);
            //// 控制利率年化天数控件属性
            cboPayFormula_SelectedValue();
            if (0 != this.cboSecVar.Value.Trim().Length)
            {
                if (("JJ_KFS_HBX").Equals(this.cboSecVar.Value) || ("LC_HBX").Equals(this.cboSecVar.Value))
                {
                    this.cboAIMode.YssIsMust = true;
                    this.cboAIMode.YssReadOnly = false;
                }
                else
                {
                    this.cboAIMode.YssIsMust = false;
                    ////this.cboAIMode.YssReadOnly = true;
                }
            }

        }

        /// <summary>
        /// btnEdit_Click  BUG #82791 T1.理财品种信息：SET页计息方式权限控制有误 Modifier by :zhengguiyu 20131102
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnEdit_Click(object sender, EventArgs e)
        {
            base.btnEdit_Click(sender, e);
            //// 控制利率年化天数控件属性
            cboPayFormula_SelectedValue();
            if (0 != this.cboSecVar.Value.Trim().Length)
            {
                if (("JJ_KFS_HBX").Equals(this.cboSecVar.Value) || ("LC_HBX").Equals(this.cboSecVar.Value))
                {
                    this.cboAIMode.YssIsMust = true;
                    this.cboAIMode.YssReadOnly = false;
                }
                else
                {
                    this.cboAIMode.YssIsMust = false;
                    ////this.cboAIMode.YssReadOnly = true;
                }
            }


        }

        /// <summary>
        /// 初始化控件
        /// </summary>
        public override void initControlStat()
        {
            base.initControlStat();
            tbDesc.ReadOnly = status == ClsEnums.StatusSetting.YssBrow;
            //// add by wangtangyao 20160405 利率年化天数设置为默认为365

            if (this.status == ClsEnums.StatusSetting.YssAdd)
            {
                cboBackward.Value = "MRGC";
                this.iniRateYearDays.Value = 365;
                this.yssSelDZ.Value = "MRGC";
                this.yssSelCombox_jtjz4std.Value = "LC_SL";
                this.yssSelCombox_jtjz4nostd.Value = "LC_SL";
            }

            //// add by fjl 20140109 修改时当收益类型为"净值行情"时计息信息不可用
            if (status == ClsEnums.StatusSetting.YssEdit || status == ClsEnums.StatusSetting.YssCopy)
            {
                if ("NETWORTH".Equals(this.cboIncomeType.Value))
                {
                    this.txtCouponRate.YssReadOnly = true;
                    this.txtCouponRate.YssIsMust = false;
                    this.txtTaxRate.YssReadOnly = true;
                    this.txtTaxRate.YssIsMust = false;
                    this.cboPayFrequency.YssReadOnly = true;
                    this.cboPayFrequency.YssIsMust = false;
                    this.cboPayFormula.YssReadOnly = true;
                    this.cboPayFormula.YssIsMust = false;
                    this.cboBackward.YssReadOnly = true;
                    this.cboBackward.YssIsMust = false;                    

                    this.dtpCountBeginDate.yssEnabled = false;
                    this.dtpCountEndDate.yssEnabled = false;
                    this.dtpCountBeginDate.setDateTime(this.dtpToList.getBeginDate);
                    this.dtpCountEndDate.setDateTime(this.dtpToList.getBeginDate);

                    ////BUG #125999 【深国投】货币基金信息修改后无法保存，为净值行情时，非标资产都不可操作 20160127 edit by LY
                    this.yssSelLLLX.YssReadOnly = true;
                    this.yssSelLLLX.YssIsMust = false;
                    this.yssSelJXGS.YssReadOnly = true;
                    this.yssSelJXGS.YssIsMust = false;
                    this.TextBoxJXLLV.YssReadOnly = true;
                    this.TextBoxJXLLV.YssIsMust = false;
                    this.improvedTextBoxSLV.YssReadOnly = true;
                    this.improvedTextBoxSLV.YssIsMust = false;
                    this.yssSelDZ.YssReadOnly = true;
                    this.yssSelDZ.YssIsMust = false;

                    this.yssDateTimeQX.yssEnabled = false;
                    this.yssDateTimeJX.yssEnabled = false;
                    this.yssDateTimeFX.yssEnabled = false;
                    this.yssDateTimeDQ.yssEnabled = false;
                    this.yssDateTimeFX.setDateTime(Convert.ToDateTime("1900-1-1"));
                    this.yssDateTimeDQ.setDateTime(Convert.ToDateTime("9998-12-31"));

                    this.yssSelCombox_jtjz4std.YssReadOnly = true;
                    this.yssSelCombox_jtjz4std.YssIsMust = false;
                    this.yssSelCombox_jtjz4nostd.YssReadOnly = true;
                    this.yssSelCombox_jtjz4nostd.YssIsMust = false;
                }
                else
                {
                    this.txtCouponRate.YssReadOnly = false;
                    this.txtCouponRate.YssIsMust = true;
                    this.txtTaxRate.YssReadOnly = false;
                    this.txtTaxRate.YssIsMust = true;
                    this.cboPayFrequency.YssReadOnly = false;
                    this.cboPayFrequency.YssIsMust = true;
                    this.cboPayFormula.YssReadOnly = false;
                    this.cboPayFormula.YssIsMust = true;
                    this.dtpCountBeginDate.yssEnabled = true;
                    this.dtpCountEndDate.yssEnabled = true;
                    this.cboBackward.YssReadOnly = false;
                    this.cboBackward.YssIsMust = true;

                    ////BUG #125999 【深国投】货币基金信息修改后无法保存，为净值行情时，非标资产都不可操作 20160127 edit by LY
                    this.yssSelLLLX.YssReadOnly = false;
                    this.yssSelLLLX.YssIsMust = true;
                    this.yssSelJXGS.YssReadOnly = false;
                    this.yssSelJXGS.YssIsMust = true;
                    this.TextBoxJXLLV.YssReadOnly = false;
                    this.TextBoxJXLLV.YssIsMust = true;
                    this.improvedTextBoxSLV.YssReadOnly = false;
                    this.improvedTextBoxSLV.YssIsMust = true;
                    this.yssSelDZ.YssReadOnly = false;
                    this.yssSelDZ.YssIsMust = true;

                    this.yssDateTimeQX.yssEnabled = true;
                    this.yssDateTimeJX.yssEnabled = true;
                    this.yssDateTimeFX.yssEnabled = true;
                    this.yssDateTimeDQ.yssEnabled = true;

                    this.yssSelCombox_jtjz4std.YssReadOnly = false;
                    this.yssSelCombox_jtjz4std.YssIsMust = true;
                    this.yssSelCombox_jtjz4nostd.YssReadOnly = false;
                    this.yssSelCombox_jtjz4nostd.YssIsMust = true;
                }
                ////(合并代码 STORY24925)add by dingshlau 20160405 STORY #28335
                this.dtpToList.yssEnabled = true;
                this.dtpOffList.yssEnabled = true;
                //// 非标资产
                ////this.yssSelLLLX.YssReadOnly = false;
                ////  this.yssSelLLLX.YssIsMust = true;
                this.yssSelLLDM.YssReadOnly = false;
                //// this.yssSelLLDM.YssIsMust = true;
                this.TextBoxJZLIL.YssReadOnly = false;
                this.TextBoxJZLIL.YssIsMust = true;
                this.TextBoxBLXS.YssReadOnly = false;
                this.TextBoxBLXS.YssIsMust = true;
                ////this.yssSelJXGS.YssReadOnly = false;
                ////this.yssSelJXGS.YssIsMust = true;
                this.yssSelFX.YssReadOnly = false;
                this.TextBoxXX.YssReadOnly = false;
                this.TextBoxLLSX.YssReadOnly = false;
                this.yssTextMM.YssReadOnly = false;
                this.TextBoxXX.YssReadOnly = false;
                this.yssTextBoxDay.YssReadOnly = false;
                ////this.TextBoxJXLLV.YssReadOnly = false;
                ////this.TextBoxJXLLV.YssIsMust = true;
                ////this.improvedTextBoxSLV.YssReadOnly = false;
                ////this.improvedTextBoxSLV.YssIsMust = true;
                this.yssTextBoxYJLX.YssReadOnly = false;
                this.yssTextBoxYJLX.YssIsMust = true;
                this.yssSelFX.ExpandButtonEnabled = true;
                this.improvedTextBoxLC.YssReadOnly = false;
                ////this.improvedTextBoxLC.YssIsMust = true;
                ////this.yssDateTimeQX.yssEnabled = true;
                ////this.yssDateTimeJX.yssEnabled = true;
                ////this.yssSelDZ.YssReadOnly = false;
                ////this.yssSelDZ.YssIsMust = true;
                ////this.yssDateTimeFX.yssEnabled = true;
                ////this.yssDateTimeDQ.yssEnabled = true;

            }

            if (this.status == ClsEnums.StatusSetting.YssBrow)
            {
                // 标准资产
                this.txtCouponRate.YssReadOnly = true;
                this.txtCouponRate.YssIsMust = true;
                this.txtTaxRate.YssReadOnly = true;
                this.txtTaxRate.YssIsMust = true;
                this.cboPayFrequency.YssReadOnly = true;
                this.cboPayFrequency.YssIsMust = true;
                this.cboPayFormula.YssReadOnly = true;
                this.cboPayFormula.YssIsMust = true;
                this.dtpCountBeginDate.yssEnabled = false;
                this.dtpCountEndDate.yssEnabled = false;
                this.cboBackward.YssReadOnly = true;
                this.cboBackward.YssIsMust = true;
                this.dtpToList.yssEnabled = false;
                this.dtpOffList.yssEnabled = false;
                this.yssSelCombox_jtjz4std.YssReadOnly = true;
                this.yssSelCombox_jtjz4std.YssIsMust = true;
                //// 非标资产
                this.yssSelLLLX.YssReadOnly = true;
                //// this.yssSelLLLX.YssIsMust = true;
                this.yssSelLLDM.YssReadOnly = true;
                ////this.yssSelLLDM.YssIsMust = true;
                this.TextBoxJZLIL.YssReadOnly = true;
                ////   this.TextBoxJZLIL.YssIsMust = true;
                this.TextBoxBLXS.YssReadOnly = true;
                //// this.TextBoxBLXS.YssIsMust = true;
                this.yssSelJXGS.YssReadOnly = true;
                ////  this.yssSelJXGS.YssIsMust = true;
                this.yssTextMM.YssReadOnly = true;
                this.TextBoxLLSX.YssReadOnly = true;
                this.yssSelFX.YssReadOnly = true;
                this.TextBoxXX.YssReadOnly = true;
                this.yssTextBoxDay.YssReadOnly = true;
                this.TextBoxJXLLV.YssReadOnly = true;
                ////this.TextBoxJXLLV.YssIsMust = true;
                this.improvedTextBoxSLV.YssReadOnly = true;
                //// this.improvedTextBoxSLV.YssIsMust = true;
                this.yssTextBoxYJLX.YssReadOnly = true;
                ////  this.yssTextBoxYJLX.YssIsMust = true;
                this.yssSelFX.ExpandButtonEnabled = false;
                this.improvedTextBoxLC.YssReadOnly = true;
                this.improvedTextBoxLC.YssIsMust = true;
                this.yssDateTimeQX.yssEnabled = false;

                this.yssDateTimeJX.yssEnabled = false;
                this.yssSelDZ.YssReadOnly = true;
                this.yssSelDZ.YssIsMust = true;
                this.yssDateTimeFX.yssEnabled = false;

                this.yssDateTimeDQ.yssEnabled = false;
                this.yssSelCombox_jtjz4nostd.YssReadOnly = true;
                this.yssSelCombox_jtjz4nostd.YssIsMust = true;

            }

            ////如果有父级菜单
            Frm_SEC_BASE_LC_L frmList = this.frmBaseViewList as Frm_SEC_BASE_LC_L;
            ////edt by zzk 20150515 frmList增加空值判断  BUG #112471::综合证券信息，点击新增中“理财品种”报错
            if (frmList != null)
            {
                Org org = frmList.MainDataPojo as Org;
                if (org != null)
                {
                    this.cboOrgan.Value = org.C_ORG_CODE;
                    this.cboOrgan.Text = org.C_ORG_NAME;
                    this.cboOrgan.YssReadOnly = true;
                }
            }
        }

        /// <summary>
        /// 分红转投类型改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboFHZT_SelectedValueChanged(object sender, EventArgs e)
        {
            if ("CF_DAY".Equals(this.cboFHZT.Value))
            {
                this.iniDate.Value = 0;
                this.iniDate.ReadOnly = true;
            }
            else
            {
                this.iniDate.ReadOnly = false;
            }
        }

        /// <summary>
        /// 重写保存按钮点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnSave_Click(object sender, EventArgs e)
        {
            ////(合并代码 STORY24925)add by dingshlau 20160405 STORY #28335
            ////这里要选择要校验的table控件 BUG #125443 非标产品计息功能业务需求
            ////Modifier by dingshalu 20160428 BUG #130024 理财品种信息反审核数据报错
            ////YssBaseCls.Fun.ClsInterface clsInterface = new YssBaseCls.Fun.ClsInterface();
            ////if (!clsInterface.checkControlsInput(this.tableFBZC1))
            ////{
            ////    return;
            ////}

            base.btnSave_Click(sender, e);
            if (this.cboIncomeType.Value == null || this.cboIncomeType.Value.Equals("NETWORTH"))
            {
                return;
            }

            if (this._formFun.N_CHECK == 0)
            {
                ClsRetInfo info = ClsRetInfoDealer.getCommonHint("HNT-000026", _formFun, status);
                info.setSpecStr("C_SEC_CODE", this.txtSecCode.Text);
                if (YssMessageBox.ShowCommonInfo(info) == DialogResult.Yes)
                {
                    service.singleSecInitFi(this.txtSecCode.Text);
                    ////MessageBox.Show("历史付息信息与每日利息数据已重新生成!", "提示");
                    this.stBarBottom.StatuInfo = "历史付息信息与每日利息数据已重新生成!";
                }
                else
                {
                    ////MessageBox.Show("请手动生成数据", "提示");
                    this.stBarBottom.StatuInfo = "请手动生成数据!";
                }
            }
        }

        /// <summary>
        /// 复写审核按钮 弹出对应是否重新初始化付息及每百元利息确认提示信息
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnAudit_Click(object sender, EventArgs e)
        {
            ////base.btnAudit_Click(sender, e);
            ////if (this.cboIncomeType.Value.Equals("NETWORTH"))
            ////{
            ////    return;
            ////}

            ////if (this.stBarBottom.StatuInfo.Equals("[理财产品信息][审核]操作成功!"))
            ////{
            ////    YssCore.Pojo.ClsRetInfo info = ClsRetInfoDealer.getCommonHint("HNT-000026", _formFun, status);
            ////    info.setSpecStr("C_SEC_CODE", this.txtSecCode.Text);
            ////    if (YssMessageBox.ShowCommonInfo(info) == DialogResult.Yes)
            ////    {
            ////       service.singleSecInitFi(this.txtSecCode.Text);
            ////        ////MessageBox.Show("历史付息信息与债券每日利息数据已重新生成!", "提示");
            ////        this.stBarBottom.StatuInfo = "历史付息信息与债券每日利息数据已重新生成!";
            ////   }
            ////   else
            ////    {
            ////        ////MessageBox.Show("请手动生成数据", "提示");
            ////        this.stBarBottom.StatuInfo = "请手动生成数据!";
            ////    }
            ////}

            ////BUG #122906 审核理财品种信息不会提示是否重新生成历史付息信息 edit by xhb 2015-11-25
            List<SecBase> secList = new List<SecBase>();
            ISecBaseLcService iSecBaseLcService = (ISecBaseLcService)dataService;
            SecBase sec = (SecBase)this.yssGetBaseSelTypeItemMVC();
            if (sec != null)
            {
                secList.Add(sec);
            }

            base.btnAudit_Click(sender, e);
            if (secList.Count > 0)
            {
                Dictionary<string, string> dict = new Dictionary<string, string>();
                dict.Add("dataClass", "SecBase");
                dict.Add("ids", sec.Id);
                List<BasePojo> dataList = iSecBaseLcService.queryByIds(dict).DataList;
                if (dataList != null && dataList.Count > 0 && (dataList[0] as SecBase).AuditState == 1)
                {
                    ClsRetInfo info = ClsRetInfoDealer.getCommonHint("HNT-000026", _formFun, status);
                    info.setSpecStr("C_SEC_CODE", this.txtSecCode.Text);
                    //// BUG148951 LIUCHI/20161227
                    if (!sec.C_DV_PI_MOD.Equals("NETWORTH") && YssMessageBox.ShowCommonInfo(info) == DialogResult.Yes)
                    {
                        iSecBaseLcService.multipleSecInitFi(secList);
                        YssMessageBox.currentForm = this;
                        this.stBarBottom.StatuInfo = "历史付息信息与每日利息数据已重新生成!";
                    }
                }

            }



        }

        /// <summary>
        /// add by wangtangyao 20160405
        /// 在保存前检查界面元素的输入是否合法
        /// </summary>
        /// <returns>isPass</returns>
        protected override bool checkInput()
        {
            bool isPass = base.checkInput();
            if (isPass)
            {
                if (this.iniRateYearDays.YssIsMust && this.iniRateYearDays.Value == 0)
                {
                    isPass = false;
                    TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                    transferErrorMessage.MESSAGESLINK = new List<string>();
                    /* 组装提示信息对象ErrorMessage */
                    string errorMess = ClsRetInfoDealer.getExtWarns("007", _formFun, ClsEnums.StatusSetting.YssSave);
                    transferErrorMessage.MESSAGESLINK.Add(errorMess);
                    throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                }
                ////modifier by dingshalu 20160428 BUG #130024 理财品种信息反审核数据报错
                Table table = null;
                if (this.tabControl1.SelectedTab == this.tabPaBZZC)
                {
                    table = this.tableMin;
                }
                else
                {
                    table = this.tableFBZC1;
                }

                isPass = clsInterface.checkControlsInput(table);
            }

            return isPass;
        }

        /// <summary>
        /// add by wangtangyao 20160405
        /// 计算公式改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboPayFormula_SelectedValueChanged(object sender, EventArgs e)
        {
            cboPayFormula_SelectedValue();
        }

        /// <summary>
        /// 根据窗口状态调整利率年化天数控件属性
        /// </summary>
        private void cboPayFormula_SelectedValue()
        {
            if ("FI_A_A_F".Equals(cboPayFormula.Value) && status != ClsEnums.StatusSetting.YssBrow)
            {
                this.iniRateYearDays.ReadOnly = false;
                this.iniRateYearDays.YssIsMust = true;
            }
            else
            {
                this.iniRateYearDays.ReadOnly = true;
                this.iniRateYearDays.YssIsMust = false;
            }

        }

        /// <summary>
        /// 根据窗口状态调整利率年化天数控件属性
        /// </summary>
        private void yssSelJXGS_SelectedValue()
        {
            if ("FI_A_A_F".Equals(yssSelJXGS.Value) && status != ClsEnums.StatusSetting.YssBrow)
            {
                this.iniRateYearDays.ReadOnly = false;
                this.iniRateYearDays.YssIsMust = true;
            }
            else
            {
                this.iniRateYearDays.ReadOnly = true;
                this.iniRateYearDays.YssIsMust = false;
            }

        }

        /// <summary>
        /// (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335 保险资管业务-非标（债权、信托）支持存款计息模式
        /// 根据不同的利率类型加载不同的控件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void yssSelLLLX_SelectedValueChanged(object sender, EventArgs e)
        {
            this.choice(this.yssSelLLLX.Value);
            this.AutoLayoutFBZC();

        }

        /// <summary>
        /// (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335 保险资管业务-非标（债权、信托）支持存款计息模式
        /// 控件自动布局
        /// </summary>
        private void AutoLayoutFBZC()
        {
            this.tableFBZC1.AllowUpdate = false;
            ////设序的标志
            bool isSet = false;
            ////处理元素的可见性
            foreach (ControlAutoLayout tempLayout in this._designControlsLayout)
            {
                if (tempLayout.InnerControl != null)
                {
                    if (tempLayout.InnerControl.Tag != null && tempLayout.InnerControl.Tag.ToString() == "false")
                    {
                        tempLayout.Visible = false;
                    }
                    else
                    {
                        ////对界面控件进行自定义排序
                        if (tempLayout.InnerControl == this.yssSelDZ)
                        {
                            isSet = true;
                        }

                        if (tempLayout.InnerControl == this.yssTextBoxYJLX)
                        {
                            if (isSet)
                            {
                                tempLayout.LayoutIndex = 16;
                            }
                        }

                        if (tempLayout.InnerControl == this.improvedTextBoxLC)
                        {
                            if (isSet)
                            {
                                tempLayout.LayoutIndex = 16;
                            }
                        }

                        tempLayout.Visible = true;
                    }
                }
            }
            ////排序一次（排序规则，可见靠前，索引升序）
            this._designControlsLayout.Sort();

            int layoutIndex = 0;
            foreach (Yss.KTable.Models.Row row in this.tableFBZC1.Rows)
            {
                if (row.IsGroup || row.Height < 21)
                {
                    continue;
                }

                for (int cellIndex = 0; cellIndex < row.Cells.Count - 1; cellIndex++)
                {
                    ////只有列宽大于60时，才校验。校验时，第一个匹配的单元格为标题，紧接其后为InnerControl
                    if (row.Cells[cellIndex].RelColumn.Width > 60)
                    {
                        if (layoutIndex < this._designControlsLayout.Count)
                        {
                            ControlAutoLayout ctrlLayout = this._designControlsLayout[layoutIndex];
                            row.Cells[cellIndex + 1].InnerControl = ctrlLayout.InnerControl;
                            if (ctrlLayout.Visible)
                            {
                                row.Cells[cellIndex].Text = ctrlLayout.Caption;
                                row.Cells[cellIndex + 1].InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowAlways;
                            }
                            else
                            {
                                row.Cells[cellIndex].Text = "";
                                row.Cells[cellIndex + 1].InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                            }
                        }

                        ////单元格索引和布局索引分别递增
                        cellIndex += 1;
                        layoutIndex += 1;
                    }
                }
            }

            this.tableFBZC1.AllowUpdate = true;
            this.tableFBZC1.UpdateTable();
        }

        /// <summary>
        /// (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335 保险资管业务-非标（债权、信托）支持存款计息模式
        /// 利率代码手动加载数据
        /// </summary>
        /// <param name="sender">sendrt</param>
        /// <param name="e">e</param>
        private void yssSelLLDM_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            ISecBaseLlService llservice = (ISecBaseLlService)ServiceFactory.createService<ISecBaseLlService>();
            Dictionary<string, string> pamap = new Dictionary<string, string>();
            pamap.Add("dataClass", "SecBase");
            QueryRes query = llservice.queryByCondition(pamap);
            List<BasePojo> pojoList = query.DataList;
            foreach (BasePojo basePojo in pojoList)
            {
                Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(((SecBase)basePojo));
                yssSelLLDM.Items.Add(entity);
            }

            e.IsCancel = true;
        }

        /// <summary>
        /// (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335 保险资管业务-非标（债权、信托）支持存款计息模式
        /// 传入不同的参数来控件控件的可见性
        /// </summary>
        /// <param name="str">str</param>
        private void choice(string str)
        {
            ////固定利率
            if (str.Equals("RTA_FIX"))
            {
                TextBoxJXLLV.Tag = "true";
                //// this.TextBoxJXLLV.Text = "0.0";
                this.improvedTextBoxSLV.Tag = "true";
                //// this.improvedTextBoxSLV.Text = "0.0";
                this.yssSelJXGS.Tag = "true";
                yssSelLLDM.Tag = "false";
                this.yssSelLLDM.YssIsMust = false;
                this.yssSelLLDM.Value = "";
                this.TextBoxJZLIL.Tag = "false";
                this.TextBoxJZLIL.Text = "0.0";
                TextBoxBLXS.Tag = "false";
                TextBoxBLXS.Text = "0.0";
                this.panel2.Tag = "false";
                this.yssSelFX.Value = " ";
                this.yssTextMM.Text = "0";
                this.TextBoxLLSX.Tag = "false";
                this.TextBoxLLSX.Text = "0.0";
                this.TextBoxXX.Tag = "false";
                this.TextBoxXX.Text = "0.0";
                this.yssTextBoxYJLX.Tag = "false";
                this.yssTextBoxYJLX.Text = "0.0";
                improvedTextBoxLC.Tag = "false";
                improvedTextBoxLC.Text = "0.0";
                this.panel1.Tag = "true";
                this.yssSelCombox_jtjz4nostd.Tag = "false";
            }
            else if (str.Equals("RTA_INC"))
            {
                ////固定收益
                this.yssTextBoxYJLX.Tag = "true";
                ////this.yssSelLLDM.Tag = "true";
                this.yssSelJXGS.Tag = "true";
                this.TextBoxJXLLV.Tag = "false";
                this.TextBoxJXLLV.Text = "0.0";
                this.improvedTextBoxSLV.Tag = "false";
                this.improvedTextBoxSLV.Text = "0.0";
                this.yssSelLLDM.Tag = "false";
                this.yssSelLLDM.YssIsMust = false;
                this.yssSelLLDM.Value = "";
                this.TextBoxJZLIL.Tag = "false";
                this.TextBoxJZLIL.Text = "0.0";
                this.TextBoxBLXS.Tag = "false";
                this.TextBoxBLXS.Text = "0.0";
                this.TextBoxLLSX.Tag = "false";
                this.TextBoxLLSX.Text = "0.0";
                this.TextBoxXX.Tag = "false";
                this.TextBoxXX.Text = "0.0";
                this.TextBoxJXLLV.Tag = "false";
                this.TextBoxJXLLV.Text = "0.0";
                this.improvedTextBoxSLV.Tag = "false";
                this.improvedTextBoxSLV.Text = "0.0";
                this.improvedTextBoxLC.Tag = "false";
                this.improvedTextBoxLC.Text = "0.0";
                this.panel2.Tag = "false";
                this.panel1.Tag = "false";
                this.yssSelFX.Value = " ";
                this.yssTextMM.Text = "0";
                this.yssSelCombox_jtjz4nostd.Tag = "true";
            }
            else if (str.Equals("RTA_RATE"))
            {
                ////利率品种
                this.yssSelLLDM.Tag = "true";
                this.yssSelLLDM.YssIsMust = true;
                this.improvedTextBoxLC.Tag = "true";
                this.panel2.Tag = "true";
                this.TextBoxLLSX.Tag = "true";
                this.TextBoxXX.Tag = "true";
                this.yssSelJXGS.Tag = "true";
                this.TextBoxJZLIL.Tag = "false";
                this.TextBoxJZLIL.Text = "0.0";
                this.TextBoxBLXS.Tag = "false";
                this.TextBoxBLXS.Text = "0.0";
                TextBoxJXLLV.Tag = "false";
                this.TextBoxJXLLV.Text = "0.0";
                improvedTextBoxSLV.Tag = "false";
                this.improvedTextBoxSLV.Text = "0.0";
                yssTextBoxYJLX.Tag = "false";
                this.yssTextBoxYJLX.Text = "0.0";
                this.panel1.Tag = "false";
                this.yssSelCombox_jtjz4nostd.Tag = "true";
            }
            else if (str.Equals("RTA_FLOAT_BASE") || str.Equals("RTA_FLOAT_LIKED"))
            {
                ////基准浮动利率、挂钩浮动利率
                this.yssSelLLDM.Tag = "true";
                this.yssSelLLDM.YssIsMust = true;
                this.TextBoxJZLIL.Tag = "true";
                this.panel1.Tag = "true";
                TextBoxBLXS.Tag = "true";
                this.yssSelJXGS.Tag = "true";
                this.panel2.Tag = "true";
                TextBoxLLSX.Tag = "true";
                this.TextBoxXX.Tag = "true";
                this.TextBoxXX.Text = "0.0";
                this.TextBoxJXLLV.Tag = "false";
                this.TextBoxJXLLV.Text = "0.0";
                this.improvedTextBoxSLV.Tag = "false";
                this.improvedTextBoxSLV.Text = "0.0";
                yssTextBoxYJLX.Tag = "false";
                this.yssTextBoxYJLX.Text = "0.0";
                this.improvedTextBoxLC.Tag = "false";
                this.improvedTextBoxLC.Text = "0.0";
                this.yssSelCombox_jtjz4nostd.Tag = "true";
            }

        }

        /// <summary>
        /// (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335 保险资管业务-非标（债权、信托）支持存款计息模式
        /// 检查间隔下拉框值改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void yssSelFX_SelectedValueChanged(object sender, EventArgs e)
        {
            if (yssSelFX.Value != null)
            {
                if (yssSelFX.Value.Equals("FXZQ_MONTH_DAY") || yssSelFX.Value.Equals("FXZQ_YEAR_MONTH_DAY") || this.yssSelFX.Value.Equals("FX_SEASON_MONTH_DAY"))
                {
                    this.yssTextMM.Width = 26;
                    this.yssTextMM.Text = "0";
                    this.yssTextMM.YssNumeric = "2";
                    this.yssTextBoxDay.Text = "0";
                    this.yssSelFX.ExpandButtonVisible = false;
                    this.yssTextMM.Visible = true;
                    this.yssTextBoxDay.Visible = true;
                    this.yssSelFX.Width = 59;

                }
                else if (this.yssSelFX.Value.Equals("FXZQ_CUSTOM"))
                {
                    this.yssTextMM.Text = "0";
                    this.yssTextMM.YssLength = 200;
                    this.yssTextMM.YssNumeric = "";
                    this.yssTextBoxDay.Text = "0";
                    this.yssSelFX.ExpandButtonVisible = true;
                    if (this.status == ClsEnums.StatusSetting.YssBrow)
                    {
                        this.yssSelFX.ExpandButtonEnabled = false;
                    }

                    if (status == ClsEnums.StatusSetting.YssEdit || status == ClsEnums.StatusSetting.YssCopy)
                    {
                        this.yssSelFX.ExpandButtonEnabled = true;
                    }

                    this.yssSelFX.ExpandButtonImage = FAST.Resource.Resource.btnItemSet;
                    this.yssSelFX.ExpandButtonText = "设置";
                    this.yssSelFX.ExpandButtonWidth = 40;
                    this.yssSelFX.Width = 100;
                    this.yssTextMM.Visible = false;
                    this.yssTextBoxDay.Visible = false;
                }
                else if (this.yssSelFX.Value.Equals("FXZQ_HALF_YEAR_END"))
                {
                    this.yssTextMM.Text = "0";
                    this.yssTextBoxDay.Text = "0";
                    this.yssTextMM.Visible = false;
                    this.yssTextBoxDay.Visible = false;
                    this.yssSelFX.ExpandButtonVisible = false;
                }
                else
                {
                    this.yssTextMM.Width = 59;
                    this.yssTextMM.Text = "0";
                    this.yssTextMM.YssNumeric = "2";
                    this.yssTextBoxDay.Text = "0";
                    this.yssSelFX.ExpandButtonVisible = false;
                    this.yssTextMM.Visible = true;
                    this.yssTextBoxDay.Visible = true;
                    this.yssSelFX.Width = 59;
                }

            }
        }

        /// <summary>
        /// (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335 保险资管业务-非标（债权、信托）支持存款计息模式
        /// 检查间隔的扩展按钮事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void yssSelFX_ExpandClick(object sender, EventArgs e)
        {
            //// 日期控件
            FrmLC_SelectDatePara paraForm = new FrmLC_SelectDatePara();
            paraForm.YssFormMenu = this._formFun;
            paraForm.initControlStat();
            if (this.yssTextMM.Text != null && !"".Equals(this.yssTextMM.Text) && !"0".Equals(this.yssTextMM.Text))
            {
                paraForm.SetSelectedDate(this.strtoDatetimelist(this.yssTextMM.Text));
            }

            paraForm.ShowDialog();
            string str = "";
            List<DateTime> datelist = paraForm.GetSelectedDate();
            datelist.Sort();
            ////去重复
            List<DateTime> datlist = datelist.Distinct().ToList();
            foreach (DateTime date in datlist)
            {
                str = Convert.ToString(date.ToString("yyyy-MM-dd")) + "," + str;
            }

            yssTextMM.Text = str;
        }

        /// <summary>
        /// (合并代码 STORY24925)add by dingshlau 20160405 STORY #28335 保险资管业务-非标（债权、信托）支持存款计息模式
        /// 转datetimelist
        /// </summary>
        /// <param name="str">str</param>
        /// <returns>datelist</returns>
        private List<DateTime> strtoDatetimelist(string str)
        {
            List<DateTime> datelist = new List<DateTime>();
            string[] str1 = str.Split(',');
            if (str1 == null || str1.Length == 0)
            {
                return datelist;
            }

            for (int t = 0; t < str1.Length - 1; t++)
            {
                datelist.Add(Convert.ToDateTime(str1[t]));
            }

            return datelist;
        }

        /// <summary>
        /// 增加描述信息文本框（追加到目标表格的最后一行）
        /// liuxiang 2015-8-24 STORY #25436 （紧急）海通20.4.4.1031版本，运营费用设置，无法一种费用设置多条记录
        /// </summary>
        /// <param name="tabSource">目标表格</param>
        /// <param name="descCellIndex">描述信息的单元格索引位置</param>
        /// <param name="description">描述信息</param>
        protected override void AddDecriptionTextBox(Table tabSource, int descCellIndex, string description)
        {
            this.tbDesc.ReadOnly = this.status == ClsEnums.StatusSetting.YssBrow;
            this.tbDesc.Clear();
            foreach (Column col in tbMain.Columns)
            {
                this.tbDesc.Columns.Add(col.Clone() as Column);
            }

            base.AddDecriptionTextBox(tbDesc, descCellIndex, description);
        }

        /// <summary>
        /// add by dingshalu 20160429
        /// 非标资产计息公式改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void yssSelJXGS_SelectedValueChanged(object sender, EventArgs e)
        {
            yssSelJXGS_SelectedValue();
        }
    }
}
