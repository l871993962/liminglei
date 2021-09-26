﻿using FAST.Core.Util;
using FAST.Common.Service.Pojo;
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
using System.Drawing;
using System.Text.RegularExpressions;








using System.Collections.Generic;
using FAST.Core.Context.Events;
using FAST.Core.Communication.DataService;
using YssSecInformation.Support.Sv.Service;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Func;
using System.Windows.Forms;

namespace YssSecInformation.Sv.Form
{
    ///<summary>
    /// 同业拆借
    /// </summary>
    public partial class Frm_SEC_BASE_CJ_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private ISecBaseCjService service;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_SEC_BASE_CJ_S()
        {
            InitializeComponent();
            dtpMarketDate.setDateTime(Convert.ToDateTime("1900-1-1"));
            dtpDelistDate.setDateTime(Convert.ToDateTime("9998-12-31"));
            this.tbMain.Border.Bottom = true;
            this.tbMain.Border.BorderColor = Color.FromArgb(111, 157, 217);
            bUseMVCService = true;
        }

        /// <summary>
        ///  初始化set界面，并给各控件赋值.
        /// </summary>
        public override void yssInitCtlAttr()
        {
            try
            {
                if (status == ClsEnums.StatusSetting.YssAdd)
                {
                    this.iniRateDays.Text = "360";
                    this.txtSecCode.Text = "";
                    this.dtpDelistDate.setDateTime(Convert.ToDateTime("9998-12-31"));
                }
            }
            catch (ClsBaseException e)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFormLoadErr(this.Text));
                ClsBaseException.DiscardException(e);
            }

        }

        /////// <summary>
        /////// 封装界面元素为pojo对象.
        /////// </summary>
        /////// <returns>由界面元素组成的对象.</returns>
        ////public override ClsBasePojo yssFaceInfoToObj()
        ////{
        ////    SecBase sec = null;
        ////    try
        ////    {
        ////        if (ClsFunction.sub(this.dtpMarketDate.getBeginDate, this.dtpDelistDate.getEndDate) > 0)
        ////        {
        ////            ////throw new ClsBaseException("上市日期应该小于起始日期");
        ////            throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("001", _formFun, ClsEnums.StatusSetting.YssSave));

        ////        }

        ////        sec = new SecBase();
        ////        if (this.status != ClsEnums.StatusSetting.YssAdd)
        ////        {
        ////            ////判断list选中界面是否有选中数据，在修改时获取原数据的值
        ////            if (null != yssGetBaseSelTypeItem())
        ////            {
        ////                SecBase clsOldSec = yssGetBaseSelTypeItem() as SecBase;
        ////                sec.OldSecCode = clsOldSec.C_SEC_CODE;
        ////                sec.OldD_TO_LIST = clsOldSec.D_TO_LIST;
        ////                sec.OldD_OFF_LIST = clsOldSec.D_OFF_LIST;
        ////            }
        ////        }              

        ////        sec.C_SEC_CODE = this.txtSecCode.Text;
        ////        sec.C_SEC_ISIN_CODE = this.txtIsinCode.Text;
        ////        sec.C_SEC_MKT_CODE = this.txtSecMarketCode.Text;
        ////        sec.C_SEC_NAME = this.txtPurchaseName.Text;
        ////        sec.C_SEC_VAR_CODE = this.cboPurchaseType.Value;
        ////        sec.C_MKT_CODE = this.cboMrket.Value;
        ////        sec.C_DC_CODE = this.cboCury.Value;
        ////        sec.C_DV_VAR_DUR = this.txtPurPeriod.Text;
        ////        sec.C_DV_QUT_MOD = this.iniRateDays.Text;
        ////        sec.N_AMOUNT_HD = this.txtHandAmount.Text;
        ////        sec.D_TO_LIST = this.dtpMarketDate.getBeginDateStr;
        ////        sec.D_OFF_LIST = this.dtpDelistDate.getBeginDateStr;


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

            try
            {
                //// add by liyanjun 2016-8-29 STORY31079 修改咨询信息品种有持仓的情况下给予提醒
                SecBase currSec = (SecBase)yssGetBaseSelTypeItemMVC();
                //// 该证券有库存信息，修改有风险，请联系技术人员！
                if (null != currSec && currSec.C_SEC_VAR_CODE != this.cboPurchaseType.Value)
                {
                    ISecBaseInfoDataService secBaseInfoDataService = DataServiceFactory.createService<ISecBaseInfoDataService>();
                    string isExistsStock = secBaseInfoDataService.isExistsStock(currSec.C_SEC_CODE);
                    if (isExistsStock == "true")
                    {
                        TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                        transferErrorMessage.MESSAGESLINK = new List<string>();
                        /* 组装提示信息对象ErrorMessage */
                        string errorMess = ClsRetInfoDealer.getExtWarns("007", _formFun, ClsEnums.StatusSetting.YssSave);
                        transferErrorMessage.MESSAGESLINK.Add(errorMess);
                        throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                    }
                }

                if (ClsFunction.sub(this.dtpMarketDate.getBeginDate, this.dtpDelistDate.getEndDate) > 0)
                {
                    ////throw new ClsBaseException("上市日期应该小于起始日期");
                    ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("001", _formFun, ClsEnums.StatusSetting.YssSave));
                    TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                    transferErrorMessage.MESSAGESLINK = new List<string>();
                    /* 组装提示信息对象ErrorMessage */
                    string errorMess = ClsRetInfoDealer.getExtWarns("001", _formFun, ClsEnums.StatusSetting.YssSave);
                    transferErrorMessage.MESSAGESLINK.Add(errorMess);
                    throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                }

                sec = new SecBase();

                sec.C_SEC_CODE = this.txtSecCode.Text;
                sec.C_SEC_ISIN_CODE = this.txtIsinCode.Text;
                sec.C_SEC_MKT_CODE = this.txtSecMarketCode.Text;
                sec.C_SEC_NAME = this.txtPurchaseName.Text;
                sec.C_SEC_VAR_CODE = this.cboPurchaseType.Value;
                sec.C_MKT_CODE = this.cboMrket.Value;
                sec.C_DC_CODE = this.cboCury.Value;
                sec.C_DV_VAR_DUR = (null == this.txtPurPeriod.Text || this.txtPurPeriod.Text.Trim().Length == 0) ? "0" : this.txtPurPeriod.Text;
                sec.C_DV_QUT_MOD = this.iniRateDays.Text;
                sec.N_AMOUNT_HD = this.txtHandAmount.Text;
                sec.D_TO_LIST = this.dtpMarketDate.getBeginDate;
                sec.D_OFF_LIST = this.dtpDelistDate.getBeginDate;
                ////添加核算方式
                sec.C_DV_ISSUE = this.cboIssureMode.Value == null ? " " : this.cboIssureMode.Value;
                sec.C_SJSSZQ = this.cboSjsszq.Value == null ? "" : this.cboSjsszq.Value; // 实际所属证券 add by zhd 2016-09-07 STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分

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
        ////        SecBase sec = (SecBase)yssGetBaseSelTypeItem();
        ////        if (null != sec)
        ////        {
        ////            this.txtSecCode.Text = sec.C_SEC_CODE;  // 证券内码
        ////            this.txtIsinCode.Text = sec.C_SEC_ISIN_CODE;    // ISIN代码
        ////            this.txtSecMarketCode.Text = sec.C_SEC_MKT_CODE;   // 上市代码
        ////            this.txtPurchaseName.Text = sec.C_SEC_NAME;  // 回购名称
        ////            this.cboPurchaseType.Value = sec.C_SEC_VAR_CODE;  // 回购类型
        ////            this.cboMrket.Value = sec.C_MKT_CODE; // 交易市场
        ////            this.cboCury.Value = sec.C_DC_CODE; // 交易币种
        ////            this.txtPurPeriod.Text = sec.C_DV_VAR_DUR; // 回购期限

        ////            // tanwenjie 2011.8.2 综合参数里设置的回购基本信息里利率年化天数为空格，因此在回购基本信息里给一个默认值360
        ////            if (sec.C_DV_QUT_MOD.Trim().Length == 0 || sec.C_DV_QUT_MOD.Trim().Equals("null"))
        ////            {   ////如果数据库中的数据时空格时给默认值0 tanhongpao 2012614
        ////                this.iniRateDays.Text = "0";
        ////            }
        ////            else
        ////            {   ////如果在数据库在为null时给默认值0 防止导入的数据出错 tanhongpao 2012614
        ////                this.iniRateDays.Text = sec.C_DV_QUT_MOD; // 利率年化天数
        ////            }

        ////            this.txtHandAmount.Text = sec.N_AMOUNT_HD;    // 每手数量
        ////            this.dtpMarketDate.setDateTime(Convert.ToDateTime(sec.D_TO_LIST));   // 上市日期
        ////            this.dtpDelistDate.setDateTime(Convert.ToDateTime(sec.D_OFF_LIST)); // 退市日期
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
                if (null != sec && sec.C_SEC_VAR_CODE.StartsWith("CJ"))
                {
                    this.txtSecCode.Text = sec.C_SEC_CODE;  // 证券内码
                    this.txtIsinCode.Text = sec.C_SEC_ISIN_CODE;    // ISIN代码
                    this.txtSecMarketCode.Text = sec.C_SEC_MKT_CODE;   // 上市代码
                    this.txtPurchaseName.Text = sec.C_SEC_NAME;  // 回购名称
                    this.cboPurchaseType.Value = sec.C_SEC_VAR_CODE;  // 回购类型
                    this.cboMrket.Value = sec.C_MKT_CODE; // 交易市场
                    this.cboCury.Value = sec.C_DC_CODE; // 交易币种
                    ////核算方式
                    this.cboIssureMode.Value = sec.C_DV_ISSUE;
                    this.txtPurPeriod.Text = sec.C_DV_VAR_DUR; // 回购期限
                    this.cboSjsszq.Value = sec.C_SJSSZQ; // 实际所属证券 add by zhd 2016-09-07 STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分

                    // tanwenjie 2011.8.2 综合参数里设置的回购基本信息里利率年化天数为空格，因此在回购基本信息里给一个默认值360
                    if (sec.C_DV_QUT_MOD.Trim().Length == 0 || sec.C_DV_QUT_MOD.Trim().Equals("null"))
                    {   ////如果数据库中的数据时空格时给默认值0 tanhongpao 2012614
                        this.iniRateDays.Text = "0";
                    }
                    else
                    {   ////如果在数据库在为null时给默认值0 防止导入的数据出错 tanhongpao 2012614
                        this.iniRateDays.Text = sec.C_DV_QUT_MOD; // 利率年化天数
                    }

                    this.txtHandAmount.Text = sec.N_AMOUNT_HD;    // 每手数量
                    if (null != sec.D_TO_LIST)
                    {
                        this.dtpMarketDate.setDateTime(sec.D_TO_LIST);   // 上市日期
                    }

                    if (null != sec.D_OFF_LIST)
                    {
                        this.dtpDelistDate.setDateTime(sec.D_OFF_LIST); // 退市日期
                    }
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
        ////        ////if (this.status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssCopy)
        ////        ////{
        ////        if (this.cboMrket.SelectedItem != null)
        ////        {
        ////            this.txtSecCode.Text = this.txtSecMarketCode.Text.Trim() + " " + ((Cls_MKT)this.cboMrket.SelectedItem.DataEntity).C_MKT_NO;
        ////        }
    
        ////        ////}
        ////    }
        ////    catch (Exception ye)
        ////    {
        ////        ClsBaseException.DiscardException(ye);
        ////        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("006", _formFun, ClsEnums.StatusSetting.YssSave));
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
        ////private void cboMrket_SelectedValueChanged(object sender, EventArgs e)
        ////{
        ////    txtSecCodeTextChanged();
        ////}

        /// <summary>
        /// ISIN 代码的文本框处理事件
        /// 当输入特殊字符如（@#￥%）等的时候，应该检查，防止非法数据进入数据库
        /// zhuangyuchen
        /// 2011-4-11
        /// </summary>
        /// <param name="sender">事件对象</param>
        /// <param name="e">事件类型</param>
        private void txtIsinCode_Leave(object sender, EventArgs e)
        {
            ////调用私有方法检查输入的合法性
            try
            {
                checkInput(this.txtIsinCode.Text.Trim());
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
                //// YssMessageBox.ShowDyanInformation("ISIN代码输入出错", ex.Message, MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("003", _formFun, ClsEnums.StatusSetting.YssSave));
            }

        }

        /// <summary>
        /// 回购的文本框处理事件
        /// 当输入特殊字符如（@#￥%）等的时候，应该检查，防止非法数据进入数据库
        /// zhuangyuchen
        /// 2011-4-11
        /// </summary>
        /// <param name="sender">事件对象</param>
        /// <param name="e">事件类型</param>
        private void txtPurchaseName_Leave(object sender, EventArgs e)
        {
            ////调用私有方法检查输入的合法性
            try
            {
                checkInput(this.txtPurchaseName.Text);
            }
            catch (ClsBaseException ex)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("004", _formFun, ClsEnums.StatusSetting.YssSave));
                ClsBaseException.DiscardException(ex);
            }

        }

        /// <summary>
        /// 私有方法，检查是否输入特殊字符
        /// </summary>
        /// <param name="source">要检测的字符串</param>
        private void checkInput(string source)
        {
            if (source.Length > 0)
            {
                ////如果输入的字符不包含大小写字母或者数字
                if (!Regex.IsMatch(source, @"(\w)+"))
                {
                    ////throw new ClsBaseException("输入的字符串中含有@#等特殊字符");
                    ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("002", _formFun, ClsEnums.StatusSetting.YssSave));
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("002", _formFun, ClsEnums.StatusSetting.YssSave));
                }
            }

        }

        /// <summary>
        /// 与数据库交互后的操作，包括刷新窗体的和消息提示
        /// BUG #313253 【同业拆借信息】界面，复制成功后，实际数据并没有新增
        /// </summary>
        /// <param name="operResult">操作后返回信息</param>
        protected override void operAfterSave(string operResult)
        {
            base.operAfterSave(operResult);
            if (status == ClsEnums.StatusSetting.YssCopy)
            {
                if (ClsRetInfoDealer.isJsonInfo(operResult) && operResult.Trim() != string.Empty)
                {
                    ClsRetInfo retInfo = ClsRetInfoDealer.getReturnInfo(operResult);
                    if (retInfo.operRes == "Fault")
                    {
                        YssMessageBox.currentForm = this;
                        ClsRetInfo inf = new ClsRetInfo();
                        inf.icon = MessageBoxIcon.Warning;
                        inf.infoTitle = "警告";
                        inf.infoCode = retInfo.infoCode;
                        inf.infoContent = retInfo.detailInfo;
                        YssMessageBox.ShowCommonInfoText(inf);
                    }
                }
            }
        }

        /// <summary>
        /// 上市代码的文本框离开事件
        /// 当输入特殊字符如（@#￥%）等的时候，应该检查，防止非法数据进入数据库
        /// zhuangyuchen
        /// 2011-4-11
        /// </summary>
        /// <param name="sender">事件对象</param>
        /// <param name="e">事件类型</param>
        private void txtSecMarketCode_Leave(object sender, EventArgs e)
        {
            try
            {
                checkInput(this.txtSecMarketCode.Text);
            }
            catch (ClsBaseException ex)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("005", _formFun, ClsEnums.StatusSetting.YssSave));
                ClsBaseException.DiscardException(ex);
            }

        }

        /// <summary>
        /// load
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_BASE_CJ_S_Load(object sender, EventArgs e)
        {
            Type dataServiceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            service = (ISecBaseCjService)ServiceFactory.createService(dataServiceType);
            dataService = service;
            //// 通过下面的类统一实现证券内码的调整处理 byleeyu 20130426
            ClsAdjustSecCode clsAdjustSec = new ClsAdjustSecCode(ref txtSecCode, ref cboMrket, ref txtSecMarketCode, this);
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
            if (result && status == ClsEnums.StatusSetting.YssEdit && !(this.frmBaseViewList.yssGetSelTypeItemMVC() as SecBase).C_SEC_VAR_CODE.Equals(this.cboPurchaseType.Value))
            {
                ClsSecTypeTip secTypeTip = new ClsSecTypeTip();
                result = secTypeTip.checkSecHold(this.txtSecCode.Text);
            }

            return result;
        }

        /// <summary>
        /// add by zhd 2016-09-08
        /// STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
        /// 实际所属证券加载前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboSjsszq_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            e.IsCancel = true;
            Type dataServiceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            service = (ISecBaseCjService)ServiceFactory.createService(dataServiceType);
            List<BasePojo> pojoList = service.queryForSjsszq();
            foreach (BasePojo basePojo in pojoList)
            {
                Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(((SecBase)basePojo));
                cboSjsszq.Items.Add(entity);
            }
        }
    }
}




