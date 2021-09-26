﻿using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;

using FAST.Common.Service.Interface;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;

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

using System.Text.RegularExpressions;

using FAST.Core.Communication.DataService;
using YssSecInformation.Support.Sv.Service;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Func;





namespace YssSecInformation.Sv.Form
{
    ///<summary>
    /// 功能简介：回购基本信息设置，负责回购基本信息的增删改查等功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.16
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：lyh 
    /// 修改日期：2010.12.17
    /// 修改简介：回购基本信息设置，负责回购基本信息的增删改查等功能的实现 
    ///  －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：caozhonghu
    /// 修改日期：2010.12.22
    /// 修改简介：将回购基本信息前台pojo类统一替换成证券基础信息公共pojo类：ClsSecurityInfo，同时修改对应的属性名
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：yhm
    /// 修改日期：2011.02.26
    /// 修改简介：修改为新的代码结构
    /// 当前版本：V4.5.0.5
    /// 修改人：wuwenlan
    /// 修改日期： 2011.03.2
    /// 修改简介： 实现对交易市场控件的控制
    ///            控件按需求进行调整控制
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.6
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         返回对象错误直接抛出异常
    /// </summary>
    public partial class Frm_SEC_BASE_HG_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private ISecBaseHgService service;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_SEC_BASE_HG_S()
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
        ///  wuwenlan.
        ///  20111302.
        /// </summary>
        public override void yssInitCtlAttr()
        {
            ////Cls_MKT mkt = null;
            try
            {
                ////由于下拉列表更改，下拉列表数据在控件间中配置，删除在初始的时候配置分级列表

                ////如果set窗体的状态是新增状态，在点击新增之前事件中已经得到组合代码
                ////然后将赋值给set窗体窗体中的组合代码
                ////mkt = this.frmBaseViewList.getSelectedRowTagMVC(mkt) as Cls_MKT;
                if (status == ClsEnums.StatusSetting.YssAdd)
                {
                    /**Start 20150128 added by liubo.BUG #107100 股票质押式回购业务回购收益问题.
                     * 利率年化天数的默认值更改为365*/
                        this.iniRateDays.Text = "365";
                    /**End 20150128 added by liubo.BUG #107100 股票质押式回购业务回购收益问题.*/
                        this.txtSecCode.Text = "";
                    }

               
                    ////if (mkt != null)
                    ////{
                    ////    this.cboMrket.Value = mkt.C_MKT_CODE; // 交易市场代码
                    ////}

                    ////dtpMarketDate.setDateTime(Convert.ToDateTime("1900-1-1"));
                    dtpDelistDate.setDateTime(Convert.ToDateTime("9998-12-31"));
                }
                catch (ClsBaseException e)
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFormLoadErr(this.Text));
                    ClsBaseException.DiscardException(e);
                }

            }

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
                ////modified by liyanjun 20140820 BUG #99525 SET界面没有千分符 
                sec.N_AMOUNT_HD = Convert.ToString(Convert.ToDecimal(this.txtHandAmount.Text));
                sec.D_TO_LIST = this.dtpMarketDate.getBeginDate;
                sec.D_OFF_LIST = this.dtpDelistDate.getBeginDate;
                ////添加核算方式 20131224 zhengguiyu
                sec.C_DV_ISSUE = this.cboIssureMode.Value;


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
                if (null != sec && sec.C_SEC_VAR_CODE.StartsWith("HG"))
                {
                    this.txtSecCode.Text = sec.C_SEC_CODE;  // 证券内码
                    this.txtIsinCode.Text = sec.C_SEC_ISIN_CODE;    // ISIN代码
                    this.txtSecMarketCode.Text = sec.C_SEC_MKT_CODE;   // 上市代码
                    this.txtPurchaseName.Text = sec.C_SEC_NAME;  // 回购名称
                    this.cboPurchaseType.Value = sec.C_SEC_VAR_CODE;  // 回购类型
                    this.cboMrket.Value = sec.C_MKT_CODE; // 交易市场
                    this.cboCury.Value = sec.C_DC_CODE; // 交易币种
                    ////this.txtPurPeriod.Text = sec.C_DV_VAR_DUR; // 回购期限
                    ////添加核算方式
                    this.cboIssureMode.Value = sec.C_DV_ISSUE;

                    if (sec.C_DV_VAR_DUR.Trim().Length > 0)
                    {
                        this.txtPurPeriod.Text = sec.C_DV_VAR_DUR;
                    }

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
        ////        //// if (this.status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssCopy)
        ////        //// {
        ////        if (this.cboMrket.SelectedItem != null)
        ////        {
        ////            this.txtSecCode.Text = this.txtSecMarketCode.Text.Trim() + " " + ((Cls_MKT)this.cboMrket.SelectedItem.DataEntity).C_MKT_NO;
        ////        }

        ////            //// }
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
        private void Frm_SEC_BASE_HG_S_Load(object sender, EventArgs e)
        {
            Type dataServiceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            service = (ISecBaseHgService)ServiceFactory.createService(dataServiceType);
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
    }
}




