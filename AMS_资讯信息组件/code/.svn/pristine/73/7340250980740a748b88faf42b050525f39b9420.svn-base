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
    /// 功能简介：期货品种信息设置，负责期货品种信息的增删改查等功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.30
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：chenyoulong
    /// 修改日期：20110107
    /// 修改简介：方法的具体实现
    ///  -----------修改记录----------
    /// 当前版本：v4.5.0.3
    /// 修改人：chenyoulong
    /// 修改日期：2011.02.20
    /// 修改简介：根据需求的二次更新，进行模块的二次开发
    /// 修改主要有以下几点：
    /// 1、根据新的表结构进行调整
    /// 2、注释完善
    /// 3、提示信息统一调整
    /// 4、需求中细节点的控制完善
    /// 6、列头调整
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：yh
    /// 修改日期： 2011.02.24
    /// 修改简介： 调整代码为新的结构
    ///－－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：chenyoulong
    /// 修改日期：20110302
    /// 修改简介：
    /// 1、代码注释完善（包括方法作用注释，逻辑注释，类修改注释）
    /// 2、提示信息统一调整(前台用五个参数YssMessageBox.ShowDyanInformation("初始化窗体出错", ye.Message, "错误信息", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail)）
    /// 3、需求中内容没有实现功能的调整 
    /// 4、删除废弃代码
    ///－－－－修改记录－－－－
    /// 当前版本：V4.5.0.5
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         返回对象错误直接抛出异常
    /// </summary>
    public partial class Frm_SEC_BASE_QH_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private ISecBaseQhService service;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_SEC_BASE_QH_S()
        {
            InitializeComponent();
            this.dtpToList.setDateTime(Convert.ToDateTime("1900-1-1"));
            this.dtpToEndList.setDateTime(Convert.ToDateTime("9998-12-31"));
            bUseMVCService = true;
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
                if (null != currSec && currSec.C_SEC_VAR_CODE != this.cboSecVar.Value)
                {
                    ISecBaseInfoDataService secBaseInfoDataService = DataServiceFactory.createService<ISecBaseInfoDataService>();
                    string isExistsStock = secBaseInfoDataService.isExistsStock(currSec.C_SEC_CODE);
                    if (isExistsStock == "true")
                    {
                        TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                        transferErrorMessage.MESSAGESLINK = new List<string>();
                        /* 组装提示信息对象ErrorMessage */
                        string errorMess = ClsRetInfoDealer.getExtWarns("009", _formFun, ClsEnums.StatusSetting.YssSave);
                        transferErrorMessage.MESSAGESLINK.Add(errorMess);
                        throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                    }
                }
				
                if (this.dtpToList.getBeginDate.CompareTo(this.dtpFinalTradeDate.getBeginDate) > 0)
                {
                    ////throw new ClsBaseException("上市日期应该小于最后交易日");
                    ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("001", _formFun, ClsEnums.StatusSetting.YssSave));
                    TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                    transferErrorMessage.MESSAGESLINK = new List<string>();
                    /* 组装提示信息对象ErrorMessage */
                    string errorMess = ClsRetInfoDealer.getExtWarns("001", _formFun, ClsEnums.StatusSetting.YssSave);
                    transferErrorMessage.MESSAGESLINK.Add(errorMess);
                    throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                }

                if (this.dtpToList.getBeginDate.CompareTo(this.dtpDelivDate.getBeginDate) > 0)
                {
                    ////throw new ClsBaseException("上市日期应该小于交割日期");
                    ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("007", _formFun, ClsEnums.StatusSetting.YssSave));
                    TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                    transferErrorMessage.MESSAGESLINK = new List<string>();
                    /* 组装提示信息对象ErrorMessage */
                    string errorMess = ClsRetInfoDealer.getExtWarns("007", _formFun, ClsEnums.StatusSetting.YssSave);
                    transferErrorMessage.MESSAGESLINK.Add(errorMess);
                    throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                }

                if (this.dtpToList.getBeginDate.CompareTo(this.dtpToEndList.getBeginDate) > 0)
                {
                    ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("008", _formFun, ClsEnums.StatusSetting.YssSave));
                    TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                    transferErrorMessage.MESSAGESLINK = new List<string>();
                    /* 组装提示信息对象ErrorMessage */
                    string errorMess = ClsRetInfoDealer.getExtWarns("008", _formFun, ClsEnums.StatusSetting.YssSave);
                    transferErrorMessage.MESSAGESLINK.Add(errorMess);
                    throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                }

                sec = new SecBase();
                sec.C_SEC_VAR_CODE = "QH";
                sec.C_SEC_CODE = this.txtSecCode.Text;
                sec.C_SEC_ISIN_CODE = this.txtIsinCode.Text;
                sec.C_SEC_MKT_CODE = this.txtSecMktCode.Text;
                sec.C_SEC_NAME = this.txtSecName.Text;
                sec.C_SEC_VAR_CODE = this.cboSecVar.Value;
                sec.C_DV_QUT_MOD = this.cboDeliveryMode.Value; // 交割方式
                sec.C_MKT_CODE = this.cboMkt.Value;
                sec.C_DC_CODE = this.cboCury.Value;
                sec.C_SEC_CODE_TRG = this.selTargetSec.Value; // 合约标的
                sec.N_FV_ISSUE = this.txtMultiple.Text; // 合约乘数
                
                ////sec.N_RATE = this.txtBailRatio.Text; // 保证金比例
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


                sec.N_PRICE_ISSUE = this.txtBailFixed.Value; // 固定保证金
                sec.D_TO_LIST = this.dtpToList.getBeginDate; //// 上市日期
                sec.D_AI_BEGIN = this.dtpDelivDate.getBeginDateStr; ////交割日期
                sec.D_AI_END = this.dtpFinalTradeDate.getBeginDateStr; ////最后交易日期
                sec.D_OFF_LIST = this.dtpToEndList.getBeginDate;

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
        /// 初始化界面控件.
        /// -----------修改记录----------.
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
                //// 由于下拉列表更改，下拉列表数据在控件间中配置，删除在初始的时候配置分级列表

                //// 如果set窗体的状态是新增状态，在点击新增之前事件中已经得到组合代码

                //// 然后将赋值给set窗体窗体中的组合代码

                //// 获取快捷区单选的选中行的对象
                ////mkt = this.frmBaseViewList.getSelectedRowTagMVC(mkt) as Cls_MKT;
                ////if (mkt != null)
                ////{
                ////    this.cboMkt.Value = mkt.C_MKT_CODE; // 交易市场代码
                ////}

                ////dtpToList.setDateTime(Convert.ToDateTime("1900-1-1"));
                ////dtpFinalTradeDate.setDateTime(DateTime.Now);
                ////dtpDelivDate.setDateTime(DateTime.Now);
                ////this.dtpToEndList.setDateTime(DateTime.Now);

                if (this.status == ClsEnums.StatusSetting.YssAdd)
                {
                    this.txtSecCode.Text = "";
                }
            }
            catch (ClsBaseException e)
            {
                ClsBaseException.DiscardException(e);
                //// YssMessageBox.ShowDyanInformation("初始化窗体发生错误", e.Message, "错误信息", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFormLoadErr(this.Text));
            }
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
                if (null != sec && sec.C_SEC_VAR_CODE.StartsWith("QH"))
                {
                    this.txtSecCode.Text = sec.C_SEC_CODE;  // 证券内码
                    this.txtIsinCode.Text = sec.C_SEC_ISIN_CODE; // ISIN代码
                    this.txtSecMktCode.Text = sec.C_SEC_MKT_CODE; // 上市代码
                    this.txtSecName.Text = sec.C_SEC_NAME; // 证券名称
                    this.cboSecVar.Value = sec.C_SEC_VAR_CODE; // 证券品种
                    this.cboDeliveryMode.Value = sec.C_DV_QUT_MOD; // 交割方式
                    this.cboMkt.Value = sec.C_MKT_CODE; // 交易市场
                    this.cboCury.Value = sec.C_DC_CODE; // 交易币种
                    this.selTargetSec.Value = sec.C_SEC_CODE_TRG; // 合约标的
                    this.txtMultiple.Text = sec.N_FV_ISSUE; // 合约乘数
                    this.txtBailFixed.Text = sec.N_PRICE_ISSUE == "null" ? "" : sec.N_PRICE_ISSUE; // 固定保证金
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

                    if (null != sec.D_TO_LIST)
                    {
                        this.dtpToList.setDateTime(sec.D_TO_LIST); // 上市日期
                    }

                    if (null != sec.D_OFF_LIST)
                    {
                        this.dtpToEndList.setDateTime(sec.D_OFF_LIST); // 退市日期
                    }

                    if (!string.IsNullOrEmpty(sec.D_AI_BEGIN))
                    {
                        this.dtpDelivDate.setDateTime(Convert.ToDateTime(sec.D_AI_BEGIN)); 
                    }

                    if (!string.IsNullOrEmpty(sec.D_AI_END))
                    {
                        this.dtpFinalTradeDate.setDateTime(Convert.ToDateTime(sec.D_AI_END)); // 最后交割日
                    }
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 当控件值改变时，用于判断固定保证金是否大于0，大于0则保证金比例不可编辑
        /// chenyoulong  20110321
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        private void txtBailFixed_TextChanged(object sender, EventArgs e)
        {
            try
            {
                if (this.status != ClsEnums.StatusSetting.YssBrow)
                {
                    if (Convert.ToDouble(this.txtBailFixed.Text.Trim().Length == 0 ? "0" : this.txtBailFixed.Text) > 0)
                    {
                        this.txtBailRatio.YssReadOnly = true;
                        this.txtBailRatio.YssIsMust = false;
                        //////this.txtBailRatio.Text = "";
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
                //// YssMessageBox.ShowDyanInformation("保证金比例控件控制报错", ye.Message, "信息提示", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, ClsEnums.StatusSetting.YssSave));
                ClsBaseException.DiscardException(ye);
            }
        }

        /// <summary>
        /// 当控件值改变时触发的事件，用于判断保证金比例是否大于0，大于0则固定保证金不可编辑.
        /// chenyoulong  20110320.
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        private void txtBailRatio_TextChanged(object sender, EventArgs e)
        {
            try
            {
                if (this.status != ClsEnums.StatusSetting.YssBrow)
                {
                    if (Convert.ToDouble(this.txtBailRatio.Text.Trim().Length == 0 ? "0" : this.txtBailRatio.Text) > 0)
                    {
                        this.txtBailFixed.YssReadOnly = true;
                        this.txtBailFixed.YssIsMust = false;
                        ////this.txtBailFixed.Text = "";
                    }
                    else
                    {
                        this.txtBailFixed.YssReadOnly = false;
                        this.txtBailFixed.YssIsMust = true;
                        ////this.txtBailFixed.Text = "";
                    }
                }
            }
            catch (Exception ye)
            {
                //// YssMessageBox.ShowDyanInformation("保证金比例控件控制报错", ye.Message, "信息提示", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, ClsEnums.StatusSetting.YssSave));
                ClsBaseException.DiscardException(ye);
            }
        }

        /// <summary>
        /// 合约标的下拉点击前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void selTargetSec_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            if (this.cboSecVar.Value != null)
            {
                if (this.cboSecVar.Value.Trim().Length > 0 && this.cboSecVar.Value.Equals("QH_GP"))
                {
                    this.selTargetSec.QueryCond = "GP";
                    this.selTargetSec.MethodInfo.MethodParamValues = new string[] { "GP," };
                }
                else if (this.cboSecVar.Value.Trim().Length > 0 && this.cboSecVar.Value.Equals("QH_QHGZ"))
                {
                    this.selTargetSec.QueryCond = "GZ";
                    this.selTargetSec.MethodInfo.MethodParamValues = new string[] { "QHGZ," };
                }
                else if (this.cboSecVar.Value.Trim().Length > 0 && this.cboSecVar.Value.Equals("QH_ZQ"))
                {
                    this.selTargetSec.QueryCond = "ZQ";
                    this.selTargetSec.MethodInfo.MethodParamValues = new string[] { "ZQ," };
                }
                else 
                {
                    this.selTargetSec.Items.Clear();
                    this.selTargetSec.Text = "";
                    e.Cancel = true;
                }
            }
            else 
            {
                this.selTargetSec.Items.Clear();
                this.selTargetSec.Text = "";
                e.Cancel = true;
            }
        }

        /// <summary>
        /// load
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_BASE_QH_S_Load(object sender, EventArgs e)
        {
            Type dataServiceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            service = (ISecBaseQhService)ServiceFactory.createService(dataServiceType);
            dataService = service;
            //// 通过下面的类统一实现证券内码的调整处理 byleeyu 20130426
            YssSecInformation.Support.Func.ClsAdjustSecCode clsAdjustSec = new YssSecInformation.Support.Func.ClsAdjustSecCode(ref txtSecCode, ref cboMkt, ref txtSecMktCode, this);
        }

        /// <summary>
        /// 初始化窗体控件状态 add by liuxiang 2013/9/4 bug9338
        /// </summary>
        public override void initControlStat()
        {
            base.initControlStat();
            if (this.status == ClsEnums.StatusSetting.YssEdit || this.status == ClsEnums.StatusSetting.YssCopy)
            {
                if (Convert.ToDouble(this.txtBailFixed.Text.Trim().Length == 0 ? "0" : this.txtBailFixed.Text) > 0)
                {
                    this.txtBailRatio.YssReadOnly = true;
                    this.txtBailRatio.YssIsMust = false;

                    this.txtBailFixed.YssReadOnly = false;
                    this.txtBailFixed.YssIsMust = true;
                }
                else
                {
                    this.txtBailRatio.YssReadOnly = false;
                    this.txtBailRatio.YssIsMust = true;

                    this.txtBailFixed.YssReadOnly = true;
                    this.txtBailFixed.YssIsMust = false;
                }
            }
        }

        /// <summary>
        /// add by weijj
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboSecVar_SelectedValueChanged(object sender, EventArgs e)
        {
            ////清空合约标的
            this.selTargetSec.Items.Clear();
            this.selTargetSec.Text = "";
        }

        /////// <summary>
        /////// 调整保证金点击事件
        /////// </summary>
        /////// <param name="sender">sender</param>
        /////// <param name="e">e</param>
        ////private void tzbzj_Click(object sender, EventArgs e)
        ////{
        ////    Frm_SEC_BASE_QHBZJ_S bzj = new Frm_SEC_BASE_QHBZJ_S();
        ////    bzj.yssShowForm(this.frmBaseViewList);
        ////}

        /// <summary>
        /// 保证金调整按钮点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void tailTextBox1_TailClick(object sender, EventArgs e)
        {
            Frm_SEC_BASE_QHBZJ_S bzj = new Frm_SEC_BASE_QHBZJ_S();
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
            if (result && status == ClsEnums.StatusSetting.YssEdit && !(this.frmBaseViewList.yssGetSelTypeItemMVC() as SecBase).C_SEC_VAR_CODE.Equals(this.cboSecVar.Value))
            {
                ClsSecTypeTip secTypeTip = new ClsSecTypeTip();
                result = secTypeTip.checkSecHold(this.txtSecCode.Text);
            }

            return result;
        }
    }
}




