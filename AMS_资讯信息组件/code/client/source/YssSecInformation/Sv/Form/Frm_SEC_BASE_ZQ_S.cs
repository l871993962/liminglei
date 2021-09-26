﻿using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
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




using System.Windows.Forms;




using System.Collections.Generic;
using System.Threading;

using FAST.Core.BaseControl;
using Yss.KRichEx.AutoFilter.Model;
using FAST.Common.Service.Dict.Pojo;
using FAST.Core.Communication.DataService;
using YssSecInformation.Support.Sv.Service;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Func;

namespace YssSecInformation.Sv.Form
{
    ///<summary>
    /// 功能简介：债券基本信息设置，负责债券基本信息的增删改查等功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.30
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：lyh
    /// 修改日期：2010.1.4
    /// 修改简介：债券基本信息设置，负责债券基本信息的增删改查等功能的实现 
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：zhuangyuchen
    /// 修改日期： 2011-2-21
    /// 修改简介：
    /// 1:增加回收站开启关闭机制
    /// 2:删除添加修改等操作的操作成功信息
    /// 3：添加注释
    /// 4：删除初始化加载下拉框的代码，改成在控件的属性中设置
    /// 6：修改出错的提示信息
    /// 7:去掉增删改查成功的提示信息，由基类来提供
    /// 8：增加传到后台去的列头和窗体菜单
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：yhm
    /// 修改日期：2011.02.26
    /// 修改简介：修改为新的代码结构
    ///  －－－－修改记录－－－－
    /// 当前版本：V4.5.0.5
    /// 修改人：wuwenlan
    /// 修改日期： 2011.03.2
    /// 修改简介： 实现对交易市场控件的控制
    ///            控件按需求进行调整控制
    ///            计息公式控件没有做
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.6
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         返回对象错误直接抛出异常
    /// </summary>
    public partial class Frm_SEC_BASE_ZQ_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private ISecBaseZqService service;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_SEC_BASE_ZQ_S()
        {
            InitializeComponent();
            bUseMVCService = true;
            ////显示备注字段。张绍林-20150706
            this.ShowDescription = true;
        }

        /// <summary>
        ///  初始化set界面，并给各控件赋值.
        ///  wuwenlan.
        ///  20111302.
        ///  zhuangyuchen
        ///  201-4-20
        ///  bug单号 1713，序号4
        /// </summary>
        public override void yssInitCtlAttr()
        {
            ////Cls_MKT mkt = null;
            try
            {
                //// 由于下拉列表更改，下拉列表数据在控件间中配置，删除在初始的时候配置分级列表

                //// 如果set窗体的状态是新增状态，在点击新增之前事件中已经得到组合代码
                //// 然后将赋值给set窗体窗体中的组合代码
                ////mkt = this.frmBaseViewList.getSelectedRowTagMVC(mkt) as Cls_MKT;
                ////if (mkt != null)
                ////{
                ////    this.cboMarket.Value = mkt.C_MKT_CODE; // 交易市场代码
                ////}

                ////this.dtpCountBeginDate.setDateTime(DateTime.Now);
                ////this.dtpCountEndDate.setDateTime(DateTime.Now);
                ////this.dtpExpireDate.setDateTime(DateTime.Now);
                ////this.dtpIssueDate.setDateTime(DateTime.Now);

                if (this.status == ClsEnums.StatusSetting.YssAdd)
                {
                    this.txtSecCode.Text = "";
                    //// add by zhd 2016-12-15
                    //// BUG147851【紧急】太平保险-债券基本信息理财产品信息增加默认值
                    this.txtHandAmount.Text = "10";
                    this.txtFactor.Text = "1";
                    this.dtpTransEndDate.setDateTime(Convert.ToDateTime("9998-12-31"));
                }

                ////STORY #39209 初始化转换起始日和转换截止日控件
                InitializeControl();
            }
            catch (ClsBaseException e)
            {
                ClsBaseException.DiscardException(e);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFormLoadErr(this.Text));
            }

        }

        /////// <summary>
        /////// 封装界面元素为pojo对象.
        /////// </summary>
        /////// <returns>由界面元素组成的对象.</returns>
        ////public override ClsBasePojo yssFaceInfoToObj()
        ////{
        ////    Cls_SEC_BASE sec = null;
        ////    ////如果起息日大于结息日，就报错
        ////    if (ClsFunction.sub(this.dtpCountBeginDate.getBeginDate, this.dtpCountEndDate.getBeginDate) > 0)
        ////    {
        ////        ////throw new ClsBaseException("债券起息日应该小于债券结息日");
        ////        throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("001", _formFun, ClsEnums.StatusSetting.YssSave));
        ////    }

        ////    if (ClsFunction.sub(this.dtpIssueDate.getBeginDate, this.dtpExpireDate.getBeginDate) > 0)
        ////    {
        ////        ////throw new ClsBaseException("债券发行日应该小于到期日");
        ////        throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("002", _formFun, ClsEnums.StatusSetting.YssSave));
        ////    }

        ////  ////add begin
        ////    if (ClsFunction.sub(this.dtpIssueDate.getBeginDate, this.dtpCountBeginDate.getBeginDate) > 0)
        ////    {
        ////        ////throw new ClsBaseException("债券发行日应该小于起息日");
        ////        throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("003", _formFun, ClsEnums.StatusSetting.YssSave));
        ////    }

        ////    if (ClsFunction.sub(this.dtpCountEndDate.getBeginDate, this.dtpExpireDate.getBeginDate) > 0)
        ////    {
        ////        ////throw new ClsBaseException("债券结息日应该小于到期日");
        ////        throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("004", _formFun, ClsEnums.StatusSetting.YssSave));
        ////    }
        ////    ////add end

        ////    try
        ////    {
        ////        sec = new Cls_SEC_BASE();
        ////        if (this.status != ClsEnums.StatusSetting.YssAdd)
        ////        {
        ////            //// 判断list选中界面是否有选中数据，在修改时获取原数据的值
        ////            if (null != yssGetBaseSelTypeItem())
        ////            {
        ////                Cls_SEC_BASE clsOldSec = yssGetBaseSelTypeItem() as Cls_SEC_BASE;
        ////                sec.OldSecCode = clsOldSec.C_SEC_CODE;
        ////                sec.OldD_TO_LIST = clsOldSec.D_TO_LIST;
        ////                sec.OldD_OFF_LIST = clsOldSec.D_OFF_LIST;
        ////            }
        ////        }

        ////        sec.C_SEC_CODE = this.txtSecCode.Text;
        ////        sec.C_SEC_ISIN_CODE = this.txtIsinCode.Text;
        ////        sec.C_SEC_MKT_CODE = this.txtSecMarketCode.Text;
        ////        sec.C_SEC_NAME = this.txtBondName.Text;
        ////        sec.C_SEC_VAR_CODE = this.cboSecCategory.Value;
        ////        sec.C_MKT_CODE = this.cboMarket.Value;
        ////        sec.C_DC_CODE = this.cboCury.Value;
        ////        sec.N_AMOUNT_HD = this.txtHandAmount.Text;
        ////        sec.C_DV_QUT_MOD = this.cboPriceMode.Value;
        ////        sec.N_PRICE_FCR = this.txtFactor.Text;
        ////        sec.N_FV_ISSUE = this.txtIssueFaceValue.Text;
        ////        sec.N_PRICE_ISSUE = this.txtIssuePrice.Text;
        ////        sec.N_FV_IR = this.txtCouponRate.Text;
        ////        ////sec.N_RATE = this.txtTaxRate.Text;
        ////        sec.N_RATE = ClsFunction.div(this.txtTaxRate.Text, "100");
        ////        sec.C_DV_AI_MOD = this.cboCountMode.Value;
        ////        sec.C_DV_AI_EXPR = this.cboCountFormula.Value; // 计息公式，数据库中无此字段，先空着
        ////        sec.C_DV_VAR_DUR = this.cboPayFrequency.Value;
        ////        sec.C_DV_PI_MOD = this.cboPayFormula.Value;
        ////        sec.D_AI_BEGIN = this.dtpCountBeginDate.getBeginDateStr;
        ////        sec.D_AI_END = this.dtpCountEndDate.getBeginDateStr;
        ////        sec.D_TO_LIST = this.dtpIssueDate.getBeginDateStr;
        ////        sec.D_OFF_LIST = this.dtpExpireDate.getBeginDateStr;
        ////        sec.C_CREDIT_RATING = this.cboZqXyPj.Value;    // 债券信用评级 20130619 zgy新增
        ////    }
        ////    catch (Exception ye)
        ////    {
        ////        throw new ClsBaseException(ye.Message);
        ////    }

        ////    return sec;
        ////}

        /// <summary>
        /// 封装界面元素为pojo对象
        /// </summary>
        /// <returns>由界面元素组成的对象</returns>
        public override AuditableParamPojo faceInfoToObjMVC()
        {
            SecBase sec = null;
            //// add by liyanjun 2016-8-29 STORY31079 修改咨询信息品种有持仓的情况下给予提醒
            SecBase currSec = (SecBase)yssGetBaseSelTypeItemMVC();
            //// 该证券有库存信息，修改有风险，请联系技术人员！
            if (null != currSec && currSec.C_SEC_VAR_CODE != this.cboSecCategory.Value)
            {
                ISecBaseInfoDataService secBaseInfoDataService = DataServiceFactory.createService<ISecBaseInfoDataService>();
                string isExistsStock = secBaseInfoDataService.isExistsStock(currSec.C_SEC_CODE);
                if (isExistsStock == "true")
                {
                    TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                    transferErrorMessage.MESSAGESLINK = new List<string>();
                    /* 组装提示信息对象ErrorMessage */
                    string errorMess = ClsRetInfoDealer.getExtWarns("005", _formFun, ClsEnums.StatusSetting.YssSave);
                    transferErrorMessage.MESSAGESLINK.Add(errorMess);
                    throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                }
            }

            ////如果起息日大于结息日，就报错
            if (ClsFunction.sub(this.dtpCountBeginDate.getBeginDate, this.dtpCountEndDate.getBeginDate) > 0)
            {
                ////throw new ClsBaseException("债券起息日应该小于债券结息日");
                ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("001", _formFun, ClsEnums.StatusSetting.YssSave));
                TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                transferErrorMessage.MESSAGESLINK = new List<string>();
                /* 组装提示信息对象ErrorMessage */
                string errorMess = ClsRetInfoDealer.getExtWarns("001", _formFun, ClsEnums.StatusSetting.YssSave);
                transferErrorMessage.MESSAGESLINK.Add(errorMess);
                throw new TransferException(JsonUtil.toJson(transferErrorMessage));
            }

            if (ClsFunction.sub(this.dtpIssueDate.getBeginDate, this.dtpExpireDate.getBeginDate) > 0)
            {
                ////throw new ClsBaseException("债券发行日应该小于到期日");
                ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("002", _formFun, ClsEnums.StatusSetting.YssSave));
                TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                transferErrorMessage.MESSAGESLINK = new List<string>();
                /* 组装提示信息对象ErrorMessage */
                string errorMess = ClsRetInfoDealer.getExtWarns("002", _formFun, ClsEnums.StatusSetting.YssSave);
                transferErrorMessage.MESSAGESLINK.Add(errorMess);
                throw new TransferException(JsonUtil.toJson(transferErrorMessage));
            }

            if (ClsFunction.sub(this.dtpIssueDate.getBeginDate, this.dtpCountBeginDate.getBeginDate) > 0)
            {
                ////throw new ClsBaseException("债券发行日应该小于起息日");
                ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("003", _formFun, ClsEnums.StatusSetting.YssSave));
                TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                transferErrorMessage.MESSAGESLINK = new List<string>();
                /* 组装提示信息对象ErrorMessage */
                string errorMess = ClsRetInfoDealer.getExtWarns("003", _formFun, ClsEnums.StatusSetting.YssSave);
                transferErrorMessage.MESSAGESLINK.Add(errorMess);
                throw new TransferException(JsonUtil.toJson(transferErrorMessage));
            }

            if (ClsFunction.sub(this.dtpCountEndDate.getBeginDate, this.dtpExpireDate.getBeginDate) > 0)
            {
                ////throw new ClsBaseException("债券结息日应该小于到期日");
                ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("004", _formFun, ClsEnums.StatusSetting.YssSave));
                TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                transferErrorMessage.MESSAGESLINK = new List<string>();
                /* 组装提示信息对象ErrorMessage */
                string errorMess = ClsRetInfoDealer.getExtWarns("004", _formFun, ClsEnums.StatusSetting.YssSave);
                transferErrorMessage.MESSAGESLINK.Add(errorMess);
                throw new TransferException(JsonUtil.toJson(transferErrorMessage));
            }

            try
            {
                sec = new SecBase();

                sec.C_SEC_CODE = this.txtSecCode.Text;
                sec.C_SEC_ISIN_CODE = this.txtIsinCode.Text;
                sec.C_SEC_MKT_CODE = this.txtSecMarketCode.Text;
                sec.C_SEC_NAME = this.txtBondName.Text;
                sec.C_SEC_VAR_CODE = this.cboSecCategory.Value;
                sec.C_MKT_CODE = this.cboMarket.Value;
                sec.C_SEC_NAME_CN = this.cboInvMode.Value; //// 投资方式 add by Yuntao Lau 2015.11.09 STORY #23426
                sec.C_DC_CODE = this.cboCury.Value;
                sec.N_AMOUNT_HD = this.txtHandAmount.Value;
                sec.C_FX_CODE = this.txtFxCode.Text; //// 分销代码 合并需求：STORY #38433 南方基金-OA债券分销：债券临时代码和正式代码设置转换关系 
                sec.C_DV_QUT_MOD = this.cboPriceMode.Value;
                sec.N_PRICE_FCR = this.txtFactor.Text;
                sec.N_FV_ISSUE = this.txtIssueFaceValue.Value;
                sec.N_PRICE_ISSUE = this.txtIssuePrice.Value;
                sec.N_FV_IR = this.txtCouponRate.Text;
                ////sec.N_RATE = this.txtTaxRate.Text;
                sec.N_RATE = ClsFunction.div(this.txtTaxRate.Text, "100");
                sec.C_DV_AI_MOD = this.cboCountMode.Value;
                sec.C_DV_AI_EXPR = this.cboCountFormula.Value; // 计息公式，数据库中无此字段，先空着
                sec.C_DV_VAR_DUR = this.cboPayFrequency.Value;
                sec.C_DV_PI_MOD = this.cboPayFormula.Value;
                sec.D_AI_BEGIN = this.dtpCountBeginDate.getBeginDateStr;
                sec.D_AI_END = this.dtpCountEndDate.getBeginDateStr;
                sec.D_TO_LIST = this.dtpIssueDate.getBeginDate;
                sec.D_OFF_LIST = this.dtpExpireDate.getBeginDate;
                sec.C_CREDIT_RATING = this.cboZqXyPj.Value;    // 债券信用评级 20130619 zgy新增
                sec.C_DV_ASSURE = this.cboJxrgz.Value;    // 债券截息日遇节假日是否顺延，借用字段担保方式  --liuchi/2014.2.12
                sec.C_SEC_CODE_TRG = this.cboSecRela.Value; // 关联标的证券，add by caowei，2015-01-27，STORY #19203
                sec.C_SETT_ORG = this.cboSettOrg.Value; // 结算机构，add by caowei，2015-03-07，STORY #18932
                sec.C_DV_RIGHT = this.cboContainRightFlag.Value; // 含权标志，add by caowei，2015-03-07，STORY #18932
                sec.D_END = this.dtpContainRightDate.getBeginDateStr; // 含权日期，add by caowei，2015-03-07，STORY #18932
                sec.C_DV_ISSUE = this.cboAssureMark.Value; ////担保标识，add by Yuntao Lau, 2015-08-19, STORY #25135
                sec.C_ETF_TYPE = this.txtPeriod.Text; ////期数，add by Yuntao Lau, 2015-08-19, STORY #25135
                sec.C_DESC = this.TxtDescription.Text;

                ////STORY #39209
                if (this.dtpTransBeginDate.Checked)
                {
                    sec.D_TRA_BEGIN = this.dtpTransBeginDate.getBeginDateStr; ////转换起始日
                }

                ////STORY #39209
                if (this.dtpTransEndDate.Checked)
                {
                    sec.D_TRA_END = this.dtpTransEndDate.getBeginDateStr;  ////转换截止日
                }

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
        /////// 获取list中选中记录，为界面元素赋值，显示数据.
        /////// </summary>
        ////public override void yssShowInfo()
        ////{
        ////    try
        ////    {
        ////        Cls_SEC_BASE sec = (Cls_SEC_BASE)yssGetBaseSelTypeItem();
        ////        if (null != sec)
        ////        {
        ////            this.txtSecCode.Text = sec.C_SEC_CODE;  // 证券内码
        ////            this.txtIsinCode.Text = sec.C_SEC_ISIN_CODE;    // ISIN代码
        ////            this.txtSecMarketCode.Text = sec.C_SEC_MKT_CODE;   // 上市代码
        ////            this.txtBondName.Text = sec.C_SEC_NAME;  // 债券名称
        ////            this.cboSecCategory.Value = sec.C_SEC_VAR_CODE;  // 证券品种
        ////            this.cboMarket.Value = sec.C_MKT_CODE; // 交易市场
        ////            this.cboCury.Value = sec.C_DC_CODE; // 交易币种
        ////            this.txtHandAmount.Text = sec.N_AMOUNT_HD; // 每手数量
        ////            this.cboPriceMode.Value = sec.C_DV_QUT_MOD; // 报价方式
        ////            this.txtFactor.Text = sec.N_PRICE_FCR; // 报价因子
        ////            this.txtIssueFaceValue.Text = sec.N_FV_ISSUE;  // 发行面值
        ////            this.txtIssuePrice.Text = sec.N_PRICE_ISSUE.Trim().Equals("null") ? "0.00" : sec.N_PRICE_ISSUE; // 发行价格
        ////            this.txtCouponRate.Text = sec.N_FV_IR.Trim().Equals("null") ? "0.00" : sec.N_FV_IR; // 票面利率
        ////            ////this.txtTaxRate.Text = sec.N_RATE.Trim().Equals("null") ? "0.00" : sec.N_RATE; // 税率
        ////            this.txtTaxRate.Text = sec.N_RATE.Trim().Equals("null") ? "0.00" : ClsFunction.mul(sec.N_RATE, "100"); // 税率
        ////            this.cboCountMode.Value = sec.C_DV_AI_MOD; // 计息方式
        ////            this.cboCountFormula.Value = sec.C_DV_AI_EXPR; // 计息公式，数据库中无此字段，先空着
        ////            this.cboPayFrequency.Value = sec.C_DV_VAR_DUR; // 付息频率
        ////            this.cboPayFormula.Value = sec.C_DV_PI_MOD; // 付息公式
        ////            this.cboZqXyPj.Value = sec.C_CREDIT_RATING;  // 债券信用评级 20130619 新增
        ////            if (!string.IsNullOrEmpty(sec.D_AI_BEGIN))
        ////            {
        ////                this.dtpCountBeginDate.setDateTime(Convert.ToDateTime(sec.D_AI_BEGIN)); // 债券起息日
        ////            }

        ////            if (!string.IsNullOrEmpty(sec.D_AI_END))
        ////            {
        ////                this.dtpCountEndDate.setDateTime(Convert.ToDateTime(sec.D_AI_END)); // 债券截息日
        ////            }

        ////            if (!string.IsNullOrEmpty(sec.D_TO_LIST))
        ////            {
        ////                this.dtpIssueDate.setDateTime(Convert.ToDateTime(sec.D_TO_LIST)); // 债券发行日
        ////            }

        ////            if (!string.IsNullOrEmpty(sec.D_OFF_LIST))
        ////            {
        ////                this.dtpExpireDate.setDateTime(Convert.ToDateTime(sec.D_OFF_LIST)); // 债券到期日
        ////            }
        ////        }

        ////    }
        ////    catch (Exception ex)
        ////    {
        ////        throw new ClsBaseException(ex.Message);
        ////    }
        ////}

        /// <summary>
        /// 获取list中选中记录，为界面元素赋值，显示数据
        /// </summary>
        /// <param name="pojo">数据对象</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            try
            {
                SecBase sec = (SecBase)yssGetBaseSelTypeItemMVC();

                ////edit by liuxiang 2013/9/4 bug9337 添加条件C_SEC_VAR_CODE，判断证券类型
                if (null != sec && sec.C_SEC_VAR_CODE.StartsWith("ZQ"))
                {
                    this.txtSecCode.Text = sec.C_SEC_CODE;  // 证券内码
                    this.txtIsinCode.Text = sec.C_SEC_ISIN_CODE;    // ISIN代码
                    this.txtSecMarketCode.Text = sec.C_SEC_MKT_CODE;   // 上市代码
                    this.txtBondName.Text = sec.C_SEC_NAME;  // 债券名称
                    this.cboSecCategory.Value = sec.C_SEC_VAR_CODE;  // 证券品种
                    this.cboMarket.Value = sec.C_MKT_CODE; // 交易市场
                    this.cboInvMode.Value = sec.C_SEC_NAME_CN; //// 投资方式 add by Yuntao Lau 2015.11.09 STORY #23426
                    this.cboCury.Value = sec.C_DC_CODE; // 交易币种
                    this.txtHandAmount.Text = sec.N_AMOUNT_HD; // 每手数量
                    this.txtFxCode.Text = sec.C_FX_CODE; // 分销代码 合并需求：STORY #38433 南方基金-OA债券分销：债券临时代码和正式代码设置转换关系
                    this.cboPriceMode.Value = sec.C_DV_QUT_MOD; // 报价方式
                    this.txtFactor.Text = sec.N_PRICE_FCR; // 报价因子
                    this.txtIssueFaceValue.Text = sec.N_FV_ISSUE;  // 发行面值
                    this.txtIssuePrice.Text = sec.N_PRICE_ISSUE.Trim().Equals("null") ? "0.00" : sec.N_PRICE_ISSUE; // 发行价格
                    this.txtCouponRate.Text = sec.N_FV_IR.Trim().Equals("null") ? "0.00" : sec.N_FV_IR; // 票面利率
                    ////this.txtTaxRate.Text = sec.N_RATE.Trim().Equals("null") ? "0.00" : sec.N_RATE; // 税率
                    this.txtTaxRate.Text = sec.N_RATE.Trim().Equals("null") ? "0.00" : ClsFunction.mul(sec.N_RATE, "100"); // 税率
                    this.cboCountMode.Value = sec.C_DV_AI_MOD; // 计息方式
                    this.cboCountFormula.Value = sec.C_DV_AI_EXPR; // 计息公式，数据库中无此字段，先空着
                    this.cboPayFrequency.Value = sec.C_DV_VAR_DUR; // 付息频率
                    this.cboPayFormula.Value = sec.C_DV_PI_MOD; // 付息公式
                    this.cboZqXyPj.Value = sec.C_CREDIT_RATING;  // 债券信用评级 20130619 新增
                    this.cboJxrgz.Value = sec.C_DV_ASSURE;  // 债券截息日遇节假日是否顺延，借用字段担保方式  --liuchi/2014.2.12
                    this.cboSecRela.Value = sec.C_SEC_CODE_TRG; // 关联标的证券，add by caowei，2015-01-27，STORY #19203
                    this.cboSettOrg.Value = sec.C_SETT_ORG; // 结算机构，add by caowei，2015-03-07，STORY #18932
                    this.cboContainRightFlag.Value = sec.C_DV_RIGHT; // 含权标志，add by caowei，2015-03-07，STORY #18932
                    this.cboAssureMark.Value = sec.C_DV_ISSUE; ////担保标识，add by Yuntao Lau, 2015-08-19, STORY #25135
                    this.txtPeriod.Text = sec.C_ETF_TYPE; ////期数，add by Yuntao Lau, 2015-08-19, STORY #25135
                    if (!string.IsNullOrEmpty(sec.D_END))
                    {
                        this.dtpContainRightDate.setDateTime(Convert.ToDateTime(sec.D_END)); // 含权日期，add by caowei，2015-03-07，STORY #18932
                    }

                    if (!string.IsNullOrEmpty(sec.D_AI_BEGIN))
                    {
                        this.dtpCountBeginDate.setDateTime(Convert.ToDateTime(sec.D_AI_BEGIN)); // 债券起息日
                    }

                    if (!string.IsNullOrEmpty(sec.D_AI_END))
                    {
                        this.dtpCountEndDate.setDateTime(Convert.ToDateTime(sec.D_AI_END)); // 债券截息日
                    }

                    if (null != sec.D_TO_LIST)
                    {
                        this.dtpIssueDate.setDateTime(sec.D_TO_LIST); // 债券发行日
                    }

                    if (null != sec.D_OFF_LIST)
                    {
                        this.dtpExpireDate.setDateTime(sec.D_OFF_LIST); // 债券到期日
                    }

                    ////STORY #39209                   
                    if (!string.IsNullOrEmpty(sec.D_TRA_BEGIN))
                    {
                        this.dtpTransBeginDate.setDateTime(Convert.ToDateTime(sec.D_TRA_BEGIN)); // STORY #39209 属性：转换起始日
                    }
                    else
                    {
                        this.dtpTransBeginDate.Checked = false;
                    }

                    ////STORY #39209
                    if (!string.IsNullOrEmpty(sec.D_TRA_END))
                    {
                        this.dtpTransEndDate.setDateTime(Convert.ToDateTime(sec.D_TRA_END)); // STORY #39209 属性：转换截止日
                    }
                    else
                    {
                        this.dtpTransEndDate.Checked = false;
                    }

                    this.TxtDescription.Text = sec.C_DESC;
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
        ///////  证券内码值改变事件，用于获取当用户输入上市代码和交易市场时，根据值改变证券内码.
        ///////  chenyoulong  20110318.
        /////// </summary>
        ////private void txtSecCodeTextChanged()
        ////{
        ////    try
        ////    {
        ////        ////if (this.status == ClsEnums.StatusSetting.YssAdd)
        ////        ////{
        ////        if (this.cboMarket.SelectedItem != null)
        ////        {
        ////            this.txtSecCode.Text = this.txtSecMarketCode.Text.Trim() + " " + ((Cls_MKT)this.cboMarket.SelectedItem.DataEntity).C_MKT_NO;
        ////        }

        ////        ////}
        ////    }
        ////    catch (Exception ye)
        ////    {
        ////        ClsBaseException.DiscardException(ye);
        ////        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtError("001", _formFun, ClsEnums.StatusSetting.YssSave));
        ////    }
        ////}

        /////// <summary>
        ///////  证券内码值改变事件，用于获取当用户输入上市代码和交易市场时，根据值改变证券内码.
        ///////  chenyoulong  20110318.
        /////// </summary>
        /////// <param name="sender">sender.</param>
        /////// <param name="e">e.</param>
        ////private void txtSecMarketCode_TextChanged(object sender, EventArgs e)
        ////{
        ////    txtSecCodeTextChanged();
        ////}

        /////// <summary>
        ///////  证券内码值改变事件，用于获取当用户输入上市代码和交易市场时，根据值改变证券内码.
        ///////  chenyoulong  20110318.
        /////// </summary>
        /////// <param name="sender">sender.</param>
        /////// <param name="e">e.</param>
        ////private void cboMarket_SelectedValueChanged(object sender, EventArgs e)
        ////{
        ////    txtSecCodeTextChanged();
        ////}

        /// <summary>
        /// STORY32682【南方基金】【V2.5需求】南方基金：增加债券价格波动提示和债券信息 自动勾选2.29计息 add liuyanni 20160829
        /// 交易市场改变事件。
        /// if 交易市场=‘银行间’then  计息公式=‘A/A-Bond’
        /// else if （交易市场‘上交所’ OR 交易市场‘深交所’）then  计息公式=‘A/365F’； 
        /// else then  计息公式=‘A/A’； 
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboMarket_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.status == ClsEnums.StatusSetting.YssAdd && cboMarket.Value != null)
            {
                if (cboMarket.Value.Equals("XCFE"))
                {
                    cboCountFormula.Value = "FI_A_A_BOND";
                }
                else if (cboMarket.Value.Equals("XSHG") || cboMarket.Value.Equals("XSHE"))
                {
                    cboCountFormula.Value = "FI_A_365F";
                }
                else 
                {
                    cboCountFormula.Value = "FI_A_A";
                }
            }
        }

        /// <summary>
        /// 1
        /// </summary>
        /// <param name="sender">1</param>
        /// <param name="e">1</param>
        private void txtIsinCode_TextChanged(object sender, EventArgs e)
        {
        }

        /// <summary>
        /// load
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_BASE_ZQ_S_Load(object sender, EventArgs e)
        {
            Type dataServiceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            service = (ISecBaseZqService)ServiceFactory.createService(dataServiceType);
            dataService = service;
            //// 通过下面的类统一实现证券内码的调整处理 byleeyu 20130426
            ClsAdjustSecCode clsAdjustSec = new ClsAdjustSecCode(ref txtSecCode, ref cboMarket, ref txtSecMarketCode, this);
        }

        /// <summary>
        /// 重写保存按钮点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnSave_Click(object sender, EventArgs e)
        {
            base.btnSave_Click(sender, e);
            if (this._formFun.N_CHECK == 0)
            {
                ClsRetInfo info = ClsRetInfoDealer.getCommonHint("HNT-000024", _formFun, status);
                info.setSpecStr("C_SEC_CODE", this.txtSecCode.Text);
                if (YssMessageBox.ShowCommonInfo(info) == DialogResult.Yes)
                {
                    //// 开线程初始化债券 Modified by xzl
                    Thread bondInitThread = new System.Threading.Thread(delegate()
                    {
                        bondInitProcess();
                    });

                    bondInitThread.SetApartmentState(ApartmentState.STA);

                    bondInitThread.Start();
                }
                else
                {
                    ////MessageBox.Show("请手动生成数据", "提示");
                    YssMessageBox.currentForm = this;
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtInfo("014", _formFun, ClsEnums.StatusSetting.YssSave));
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
            ////if (this.stBarBottom.StatuInfo.Equals("[债券基本信息][审核]操作成功!"))
            ////{
            ////    ClsRetInfo info = ClsRetInfoDealer.getCommonHint("HNT-000024", _formFun, status);
            ////    info.setSpecStr("C_SEC_CODE", this.txtSecCode.Text);
            ////    if (YssMessageBox.ShowCommonInfo(info) == DialogResult.Yes)
            ////    {
            ////        //// 开线程初始化债券 Modified by xzl
            ////        Thread bondInitThread = new System.Threading.Thread(delegate()
            ////        {
            ////            bondInitProcess();
            ////        });

            ////        bondInitThread.SetApartmentState(ApartmentState.STA);

            ////        bondInitThread.Start();
            ////    }
            ////    else
            ////    {
            ////        MessageBox.Show("请手动生成数据", "提示");
            ////    }
            ////}

            ////BUG #110628  by yyj 20150409   解决忽略付息频率的问题。
            List<SecBase> secList = new List<SecBase>();
            ISecBaseZqService iSecBaseLcService = (ISecBaseZqService)dataService;
            SecBase sec = (SecBase)this.yssGetBaseSelTypeItemMVC();
            if (sec != null)
            {
                secList.Add(sec);
            }

            base.btnAudit_Click(sender, e);
            if (secList.Count > 0)
            {
                ////liuxiang 2015-7-30 BUG #116591 【紧急】债券基本信息调整审核后，系统自动初始化债券历史付息会覆盖之前的利率
                Dictionary<string, string> dict = new Dictionary<string, string>();
                dict.Add("dataClass", "SecBase");
                dict.Add("ids", sec.Id);
                List<BasePojo> dataList = iSecBaseLcService.queryByIds(dict).DataList;
                if (dataList != null && dataList.Count > 0 && (dataList[0] as SecBase).AuditState == 1)
                {
                    ClsRetInfo info = ClsRetInfoDealer.getCommonHint("HNT-000024", _formFun, status);
                    info.setSpecStr("C_SEC_CODE", this.txtSecCode.Text);
                    if (YssMessageBox.ShowCommonInfo(info) == DialogResult.Yes)
                    {
                        iSecBaseLcService.multipleSecInitFi(secList);
                        YssMessageBox.currentForm = this;
                        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtInfo("011", _formFun, ClsEnums.StatusSetting.YssSave));
                    }
                }
            }
        }

        /// <summary>
        /// 债券初始化线程
        /// added by xzl
        /// </summary>
        private void bondInitProcess()
        {
            Type dataServiceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            service = (ISecBaseZqService)ServiceFactory.createService(dataServiceType);
            string secCode = this.txtSecCode.Text;
            ////MessageBox.Show(secCode + " 历史付息信息与债券每日利息数据【开始重新生成】!", "提示");
            YssMessageBox.currentForm = this;
            YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtInfo("012", _formFun, ClsEnums.StatusSetting.YssBrow));
            service.singleSecInitFi(this.txtSecCode.Text);
            ////MessageBox.Show(secCode + " 历史付息信息与债券每日利息数据【已重新生成】!", "提示");
            YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtInfo("013", _formFun, ClsEnums.StatusSetting.YssBrow));
        }

        /// <summary>
        /// BUG #117038 品种信息中在前台控制去除多余词汇，并不是从后台数据库删除词汇 
        /// 债券品种信息中(计息公式)去除A/A-F
        /// Yun-tao Lau  2015/08/05
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboCountFormula_YssOnAfterLoadData(object sender, YssBeforeOperEventArgs e)
        {
            for (int i = 0; i < e.Collection.Count; i++)
            {
                Vocabulary voc = (e.Collection[i] as ControlEntity).DataEntity as Vocabulary;
                if (voc.C_DV_CODE.Equals("FI_A_A_F"))
                {
                    e.Collection.RemoveAt(i);
                    return;
                }
            }
        }

        /// <summary>
        /// BUG #117038 品种信息中在前台控制去除多余词汇，并不是从后台数据库删除词汇 
        /// 债券品种信息中(付息公式)去除A/A-F
        /// Yun-tao Lau  2015/08/05
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboPayFormula_YssOnAfterLoadData(object sender, YssBeforeOperEventArgs e)
        {
            for (int i = 0; i < e.Collection.Count; i++)
            {
                Vocabulary voc = (e.Collection[i] as ControlEntity).DataEntity as Vocabulary;
                if (voc.C_DV_CODE.Equals("FI_A_A_F"))
                {
                    e.Collection.RemoveAt(i);
                    return;
                }
            }
        }

        /// <summary>
        /// * Title: STORY #27843 资讯信息调整增加提示功能
        /// * Author: chenchen
        /// * Status: Add
        /// * Date: 2016.8.25
        /// * Purpose:重写基类保存前检查方法
        /// * Description:编辑状态下，如果手工调整了证券品种，则检查该证券是否有持仓。
        /// </summary>
        /// <returns>result：true确认修改 false：不修改</returns>
        protected override bool checkInput()
        {
            bool result = base.checkInput();
            if (result && status == ClsEnums.StatusSetting.YssEdit && !(this.frmBaseViewList.yssGetSelTypeItemMVC() as SecBase).C_SEC_VAR_CODE.Equals(this.cboSecCategory.Value))
            {
                ClsSecTypeTip secTypeTip = new ClsSecTypeTip();
                result = secTypeTip.checkSecHold(this.txtSecCode.Text);
            }

            return result;
        }

        /// <summary>
        /// STORY #39209 当证券品种like‘%可转%’ or like‘%可交换%’，
        /// 则显示‘转换起始日’和‘转换截止日’两个日期控件，
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboSecCategory_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.cboSecCategory.Value != null && this.cboSecCategory.SelectedItem != null)
            {
                if ((this.cboSecCategory.Text.Contains("可转")) || (this.cboSecCategory.Text.Contains("可交换")))
                {
                    this.cell78.Text = "   转换起始日:";
                    this.cell81.Text = "转换截止日:";
                    this.cell79.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowAlways;
                    this.cell82.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowAlways;
                   
                }
                else
                {
                    InitializeControl();
                }
            }
        }

        /// <summary>
        /// STORY #39209 当证券品种like‘%可转%’ or like‘%可交换%’，
        /// 则显示‘转换起始日’和‘转换截止日’两个日期控件，
        /// </summary>
        private void InitializeControl()
        {
            this.cell78.Text = "";
            this.cell81.Text = "";
            this.cell79.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
            this.cell82.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
        }
    }
}




