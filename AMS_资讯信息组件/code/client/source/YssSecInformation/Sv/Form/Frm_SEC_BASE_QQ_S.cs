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

using System.Collections.Generic;
using FAST.Core.Communication.DataService;
using YssSecInformation.Support.Sv.Service;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Func;

namespace YssSecInformation.Sv.Form
{
    ///<summary>
    /// 功能简介：期权品种信息设置，负责期权品种信息的增删改查等功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.30
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：chenyoulong
    /// 修改日期：20110106
    /// 修改简介：方法的具体实现
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：yhm
    /// 修改日期：2011.02.26
    /// 修改简介：调整代码为新的结构
    ///－－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：chenyoulong
    /// 修改日期：20110302
    /// 修改简介：
    /// 1、代码注释完善（包括方法作用注释，逻辑注释，类修改注释）
    /// 2、提示信息统一调整(前台用五个参数YssMessageBox.ShowDyanInformation("初始化窗体出错", ye.Message, "错误信息", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail)）
    /// 3、需求中内容没有实现功能的调整 
    /// 4、删除废弃代码
    /// 5、修改属性字段
    ///    －－－－修改记录－－－－
    /// 当前版本：V4.5.0.5
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         返回对象错误直接抛出异常
    /// </summary>
    public partial class Frm_SEC_BASE_QQ_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private ISecBaseQqService service;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_SEC_BASE_QQ_S()
        {
            InitializeComponent();
            this.dtpToList.setDateTime(Convert.ToDateTime("1900-1-1"));
            this.dtpOffList.setDateTime(Convert.ToDateTime("9998-12-31"));
            bUseMVCService = true;
        }

        /// <summary>
        /// 初始化界面控件.
        /// 当前版本：v4.5.0.2.
        /// 修改人：chenyoulong.
        /// 修改日期：2011.02.20.
        /// 修改简介：异常提示信息统一调整.
        /// </summary>
        public override void yssInitCtlAttr()
        {
            ////Cls_MKT mkt = null;
            try
            {
                ////由于下拉列表更改，下拉列表数据在控件间中配置，删除在初始的时候配置分级列表

                ////如果set窗体的状态是新增状态，在点击新增之前事件中已经得到市场代码
                ////然后将赋值给set窗体窗体中的市场代码
                ////获取快捷区单选的选中行的对象
                ////mkt = this.frmBaseViewList.getSelectedRowTagMVC(mkt) as Cls_MKT;
                ////if (mkt != null)
                ////{
                ////    this.cboMarket.Value = mkt.C_MKT_CODE; // 交易市场代码
                ////}

                if (status == ClsEnums.StatusSetting.YssAdd)
                {
                    this.txtSecCode.Text = "";
                    this.dtpOffList.setDateTime(Convert.ToDateTime("9998-12-31"));
                }

               
                ////dtpToList.setDateTime(Convert.ToDateTime("1900-1-1"));
                ////dtpXqEnd.setDateTime(Convert.ToDateTime("9998-12-31"));
                ////dtpXqBegin.setDateTime(Convert.ToDateTime("9998-12-31"));
            }
            catch (ClsBaseException e)
            {
                ClsBaseException.DiscardException(e);
               //// YssMessageBox.ShowDyanInformation("初始化窗体发生错误", e.Message, "错误信息", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFormLoadErr(this.Text));
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
                if (null != currSec && currSec.C_SEC_VAR_CODE != this.cboSecCategory.Value)
                {
                    ISecBaseInfoDataService secBaseInfoDataService = DataServiceFactory.createService<ISecBaseInfoDataService>();
                    string isExistsStock = secBaseInfoDataService.isExistsStock(currSec.C_SEC_CODE);
                    if (isExistsStock == "true")
                    {
                        TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                        transferErrorMessage.MESSAGESLINK = new List<string>();
                        /* 组装提示信息对象ErrorMessage */
                        string errorMess = ClsRetInfoDealer.getExtWarns("006", _formFun, ClsEnums.StatusSetting.YssSave);
                        transferErrorMessage.MESSAGESLINK.Add(errorMess);
                        throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                    }
                }

                if (ClsFunction.sub(this.dtpXqBegin.getBeginDate, this.dtpXqEnd.getEndDate) > 0)
                {
                    ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("003", _formFun, ClsEnums.StatusSetting.YssSave));
                    TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                    transferErrorMessage.MESSAGESLINK = new List<string>();
                    /* 组装提示信息对象ErrorMessage */
                    string errorMess = ClsRetInfoDealer.getExtWarns("003", _formFun, ClsEnums.StatusSetting.YssSave);
                    transferErrorMessage.MESSAGESLINK.Add(errorMess);
                    throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                }

                if (ClsFunction.sub(this.dtpToList.getBeginDate, this.dtpOffList.getEndDate) > 0)
                {
                    ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("005", _formFun, ClsEnums.StatusSetting.YssSave));
                    TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                    transferErrorMessage.MESSAGESLINK = new List<string>();
                    /* 组装提示信息对象ErrorMessage */
                    string errorMess = ClsRetInfoDealer.getExtWarns("005", _formFun, ClsEnums.StatusSetting.YssSave);
                    transferErrorMessage.MESSAGESLINK.Add(errorMess);
                    throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                }

                ////if (this.dtpToList.getBeginDate.CompareTo(this.dtpXqBegin.getBeginDate) > 0)
                ////{
                ////    ////throw new ClsBaseException("上市日期应该小于等于交割日期");
                ////    throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("004", _formFun, ClsEnums.StatusSetting.YssSave));

                ////}

                sec = new SecBase();
                sec.C_SEC_VAR_CODE = "QQ";
                sec.C_SEC_CODE = this.txtSecCode.Text; // 证券内码
                sec.C_SEC_ISIN_CODE = this.txtSINCode.Text; // ISIN代码
                sec.C_SEC_MKT_CODE = this.txtOptionCode.Text; // 期权代码
                sec.C_SEC_NAME = this.txtOptionName.Text; // 期权名称
                sec.C_DV_VAR_DUR = this.cboOptionType.Value; // 期权类型
                sec.C_SEC_VAR_CODE = this.cboSecCategory.Value; // 期权品种
                sec.C_MKT_CODE = this.cboMarket.Value; // 交易市场
                sec.C_DC_CODE = this.cboCury.Value; // 交易币种
                sec.C_SEC_CODE_TRG = this.selTargetSec.Value; // 期权标的
                sec.N_FV_ISSUE = Convert.ToString(Convert.ToDouble(this.txtMultiple.Text)); // 合约乘数
                sec.N_RATE = this.txtBailRatio.Text; // 保证金比例
                ////sec.N_RATE = this.txtBailRatio.Text; // 保证金比例
                ////add by liyanjun 20140827
                sec.N_FV_IR = Convert.ToString(Convert.ToDouble(this.txtGDingBail.Text)); // 固定保证金
                if (this.txtBailRatio.Text.Equals(""))
                {
                    sec.N_RATE = "";
                }
                else
                {
                    double doublebzj = Convert.ToDouble(this.txtBailRatio.Text);
                    string strbjz = Convert.ToString(doublebzj / 100);
                    sec.N_RATE = strbjz;
                }

                sec.D_TO_LIST = this.dtpToList.getBeginDate; // 上市日期
                sec.D_OFF_LIST = this.dtpOffList.getBeginDate; // 上市日期
                sec.C_DV_AI_MOD = this.cboXQuanFShi.Value; // 行权方式
                sec.C_DV_QUT_MOD = this.cboDeliveryMode.Value; // 交割方式
                sec.N_PRICE_ISSUE = Convert.ToString(Convert.ToDouble(this.txtXqPrice.Text)); // 行权价格
                sec.D_AI_BEGIN = this.dtpXqBegin.getBeginDateStr; // 行权起始日
                sec.D_AI_END = this.dtpXqEnd.getBeginDateStr; // 行权截止日

                //// By Jinghehe 2014-7-31
                sec.N_AMOUNT_HD = Convert.ToString(Convert.ToDouble(this.txtAmountHd.Text)); // 没手数量
                if (this.txtBailRatio1.Text.Equals(""))
                {
                    sec.N_RATIO = "0";
                }
                else
                {
                    sec.N_RATIO = Convert.ToString(Convert.ToDouble(this.txtBailRatio1.Text) / 100);
                }

                sec.D_END = this.dtpEnd.getBeginDateStr; // 期权到期日
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
                if (null != sec && sec.C_SEC_VAR_CODE.StartsWith("QQ"))
                {
                    this.txtSecCode.Text = sec.C_SEC_CODE; // 证券内码
                    this.txtSINCode.Text = sec.C_SEC_ISIN_CODE; // ISIN代码
                    this.txtOptionCode.Text = sec.C_SEC_MKT_CODE; // 期权代码
                    this.txtOptionName.Text = sec.C_SEC_NAME;  // 期权名称
                    this.cboOptionType.Value = sec.C_DV_VAR_DUR; // 期权类型
                    this.cboSecCategory.Value = sec.C_SEC_VAR_CODE; // sec.C_SEC_VAR_CODE; 期权品种
                    this.cboMarket.Value = sec.C_MKT_CODE; // 交易市场
                    this.cboCury.Value = sec.C_DC_CODE; // 交易币种
                    this.selTargetSec.Value = sec.C_SEC_CODE_TRG; // 期权标的
                    this.txtMultiple.Text = sec.N_FV_ISSUE; // 合约乘数

                    //// 保证金比例
                    if (sec.N_RATE == "null")
                    {
                        this.txtBailRatio.Text = "";
                    }
                    else
                    {
                        double intbzj = Convert.ToDouble(sec.N_RATE);
                        string strbjz = Convert.ToString(intbzj * 100);
                        this.txtBailRatio.Text = strbjz;
                    }

                    this.txtGDingBail.Text = sec.N_FV_IR; // 固定保证金
                    if (null != sec.D_TO_LIST)
                    {
                        this.dtpToList.setDateTime(sec.D_TO_LIST); // 上市日期
                    }

                    if (null != sec.D_OFF_LIST)
                    {
                        this.dtpOffList.setDateTime(sec.D_OFF_LIST); // 退市日期
                    }

                    this.cboXQuanFShi.Value = sec.C_DV_AI_MOD; // 行权方式
                    this.cboDeliveryMode.Value = sec.C_DV_QUT_MOD; // 交割方式
                    this.txtXqPrice.Text = sec.N_PRICE_ISSUE; // 行权价格
                    if (!string.IsNullOrEmpty(sec.D_AI_BEGIN))
                    {
                        this.dtpXqBegin.setDateTime(Convert.ToDateTime(sec.D_AI_BEGIN)); // 行权起始日
                    }

                    if (!string.IsNullOrEmpty(sec.D_AI_END))
                    {
                        this.dtpXqEnd.setDateTime(Convert.ToDateTime(sec.D_AI_END)); // 行权截止日
                    }


                    if (!string.IsNullOrEmpty(sec.D_END))
                    {
                        this.dtpEnd.setDateTime(Convert.ToDateTime(sec.D_END)); // 期权到期日
                    }

                    if (sec.N_RATIO == "null")
                    {
                        this.txtBailRatio1.Text = "";
                    }
                    else
                    {
                        this.txtBailRatio1.Text = Convert.ToString(Convert.ToDouble(sec.N_RATIO) * 100);
                    }
                   
                    this.txtAmountHd.Text = sec.N_AMOUNT_HD;


                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 保证金比例的焦点离开事件
        /// </summary>
        /// <param name="sender">事件代码</param>
        /// <param name="e">事件名称</param>
        private void txtBailRatio_Leave(object sender, EventArgs e)
        {
            try
            {
                if (this.status != ClsEnums.StatusSetting.YssBrow)
                {
                    if (Convert.ToDouble(this.txtBailRatio.Text.Trim().Length == 0 ? "0" : this.txtBailRatio.Text) > 0)
                    {
                        this.txtGDingBail.YssReadOnly = true;
                        this.txtGDingBail.YssIsMust = false;
                        ////this.txtBailFixed.Text = "";
                    }
                    else
                    {
                        this.txtGDingBail.YssReadOnly = false;
                        this.txtGDingBail.YssIsMust = true;
                        ////this.txtBailFixed.Text = "";
                    }
                }
            }
            catch (Exception ye)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, ClsEnums.StatusSetting.YssSave));
                ClsBaseException.DiscardException(ye);
            }
        }

        /// <summary>
        /// 保证金控件的焦点离开事件
        /// </summary>
        /// <param name="sender">事件对象</param>
        /// <param name="e">事件类型</param>
        private void txtGDingBail_Leave(object sender, EventArgs e)
        {
            try
            {
                if (this.status != ClsEnums.StatusSetting.YssBrow)
                {
                    if (Convert.ToDouble(this.txtGDingBail.Text.Trim().Length == 0 ? "0" : this.txtGDingBail.Text) > 0)
                    {
                        this.txtBailRatio.YssReadOnly = true;
                        this.txtBailRatio.YssIsMust = false;
                        ////this.txtBailRatio.Text = "";
                    }
                    else
                    {
                        this.txtBailRatio.YssReadOnly = false;
                        this.txtBailRatio.YssIsMust = true;
                        ////this.txtBailRatio.Text = "";
                    }

                }
            }
            catch (Exception ye)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, ClsEnums.StatusSetting.YssSave));
                ClsBaseException.DiscardException(ye);
            }
        }

        /// <summary>
        /// 期权币种值改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboSecCategory_SelectedValueChanged(object sender, EventArgs e)
        {
            this.selTargetSec.MethodInfo.MethodName = "getDataListByTypes";
            this.selTargetSec.MethodInfo.MethodParams = null;
            this.selTargetSec.MethodInfo.MethodParamValues = new string[] { "QH,XH," };

            if (this.cboSecCategory.Value != null) 
            {
                if (this.cboSecCategory.Value.Trim().Length > 0 && this.cboSecCategory.Value.Equals("QQ_GP")) 
                {
                    ////this.selTargetSec.QueryCond = "GP,LC";
                    this.selTargetSec.MethodInfo.MethodParamValues = new string[] { "GP,LC," };
                }

                 if (this.cboSecCategory.Value.Trim().Length > 0 && this.cboSecCategory.Value.Equals("QQ_ZQ")) 
                {
                    ////this.selTargetSec.QueryCond = "ZQ";
                    this.selTargetSec.MethodInfo.MethodParamValues = new string[] { "ZQ," };
                }

                 if (this.cboSecCategory.Value.Trim().Length > 0 && this.cboSecCategory.Value.Equals("QQ_ZS"))
                 {
                     ////this.selTargetSec.QueryCond = "ZS";
                     this.selTargetSec.MethodInfo.MethodName = "getAllIndexDataList";
                     this.selTargetSec.MethodInfo.MethodParamValues = null;
                     this.selTargetSec.MethodInfo.MethodParams = null;
                 }
                 ////STORY39265商品期权业务 add by xuyuanhao 2017-3-30 
                 ////当期权品种为“期权品种_商品期货”时，需求加载期货品种信息中的“期货品种_商品”的数据。
                 if (this.cboSecCategory.Value.Trim().Length > 0 && this.cboSecCategory.Value.Equals("QQ_QH_SP"))
                 {
                     this.selTargetSec.MethodInfo.MethodParamValues = new string[] { "QH_SP," };
                 }

                 this.selTargetSec.Value = "";
                this.selTargetSec.Items.Clear();

            }
        }

        /// <summary>
        /// load
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_BASE_QQ_S_Load(object sender, EventArgs e)
        {
            Type dataServiceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            service = (ISecBaseQqService)ServiceFactory.createService(dataServiceType);
            dataService = service;
            //// 通过下面的类统一实现证券内码的调整处理 byleeyu 20130426
            ClsAdjustSecCode clsAdjustSec = new ClsAdjustSecCode(ref txtSecCode, ref cboMarket, ref txtOptionCode, this);
        }

        /// <summary>
        /// 鼠标进入
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void txtBailRatio_MouseEnter(object sender, EventArgs e)
        {
            try
            {
                if (this.status != ClsEnums.StatusSetting.YssBrow)
                {
                    if (Convert.ToDouble(this.txtGDingBail.Text.Trim().Length == 0 ? "0" : this.txtGDingBail.Text) > 0)
                    {
                        this.txtBailRatio.YssReadOnly = true;
                        this.txtBailRatio.YssIsMust = false;
                        ////this.txtBailRatio.Text = "";
                    }
                    else
                    {
                        this.txtBailRatio.YssReadOnly = false;
                        this.txtBailRatio.YssIsMust = true;
                        ////this.txtBailRatio.Text = "";
                    }

                }
            }
            catch (Exception ye)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, ClsEnums.StatusSetting.YssSave));
                ClsBaseException.DiscardException(ye);
            }
        }

        /// <summary>
        /// 鼠标进入
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void txtGDingBail_MouseEnter(object sender, EventArgs e)
        {
            try
            {                
                if (this.status != ClsEnums.StatusSetting.YssBrow)
                {
                    if (Convert.ToDouble(this.txtBailRatio.Text.Trim().Length == 0 ? "0" : this.txtBailRatio.Text) > 0)
                    {
                        this.txtGDingBail.YssReadOnly = true;
                        this.txtGDingBail.YssIsMust = false;
                        ////this.txtBailFixed.Text = "";
                    }
                    else
                    {
                        this.txtGDingBail.YssReadOnly = false;
                        this.txtGDingBail.YssIsMust = true;
                        ////this.txtBailFixed.Text = "";
                    }
                }
            }
            catch (Exception ye)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, ClsEnums.StatusSetting.YssSave));
                ClsBaseException.DiscardException(ye);
            }
        }

        /// <summary>
        /// 保证金调整按钮点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void tailTextBox1_TailClick(object sender, EventArgs e)
        {
            Frm_SEC_BASE_QQBZJ_S bzj = new Frm_SEC_BASE_QQBZJ_S();
            bzj.YssSetStatus = this.YssSetStatus;
            this.frmBaseViewList.YssOperation = status;
            bzj.yssShowForm(this.frmBaseViewList);
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

    }
}




