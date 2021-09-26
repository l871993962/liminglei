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
    /// <summary>
    /// 功能简介：权证基本信息设置，负责权证基本信息的增删改查等功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.16
    ///
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：yh
    /// 修改日期：2010.12.17
    /// 修改简介：实现具体方法
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：caozhonghu
    /// 修改日期：2011.02.21
    /// 修改简介：将权证基本信息前台pojo类统一替换成证券基础信息公共pojo类：ClsSecurityInfo，同时修改对应的属性名
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：yhm
    /// 修改日期： 2011.02.26
    /// 修改简介： 调整代码为新的结构
    ///  /当前版本：V4.5.0.5
    /// 修改人：wuwenlan
    /// 修改日期： 2011.03.2
    /// 修改简介： 实现对交易市场控件的控制
    /// 控件按需求进行调整控制
    /// 
    /// zhuangyuchen 
    /// 2011-4-20
    /// bug单号1716 序号6
    /// </summary>
    public partial class Frm_SEC_BASE_QZ_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private ISecBaseQzService service;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_SEC_BASE_QZ_S()
        {
            InitializeComponent();
            dtpMarketDate.setDateTime(Convert.ToDateTime("1900-1-1"));
            dtpDelistDate.setDateTime(Convert.ToDateTime("9998-12-31"));
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
                // 由于下拉列表更改，下拉列表数据在控件间中配置，删除在初始的时候配置分级列表

                // 如果set窗体的状态是新增状态，在点击新增之前事件中已经得到组合代码
                // 然后将赋值给set窗体窗体中的组合代码
                ////mkt = this.frmBaseViewList.getSelectedRowTagMVC(mkt) as Cls_MKT;
                ////if (mkt != null)
                ////{
                ////    this.cboMarket.Value = mkt.C_MKT_CODE; // 交易市场代码
                ////}
                ////this.dtpDelistDate.Enabled = false;

                this.cboExeSettleMode.Value = "EUR";
                this.cboExerciseMode.Value = "PhysicalSettlement";
                ////dtpMarketDate.setDateTime(Convert.ToDateTime("1900-1-1"));
                this.dtpDelistDate.setDateTime(Convert.ToDateTime("9998-12-31"));

                ////行权起始日期默认为系统日期
                ////this.dtpExerciseBeginDate.setDateTime(DateTime.Now);
                ////行权截止日期默认为当前日期
                ////this.dtpExeriseEndDate.setDateTime(DateTime.Now);
                ////默认行权方式
                this.cboExeSettleMode.Value = "ES_OS";
                ////默认结算方式
                this.cboExerciseMode.Value = "QSM_SQGF";

                if (this.status == ClsEnums.StatusSetting.YssAdd)
                {
                    this.txtSecCode.Text = "";
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
                        string errorMess = ClsRetInfoDealer.getExtWarns("004", _formFun, ClsEnums.StatusSetting.YssSave);
                        transferErrorMessage.MESSAGESLINK.Add(errorMess);
                        throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                    }
                }

                ////如果上市日期早于退市日期
                if (ClsFunction.sub(this.dtpMarketDate.getBeginDate, this.dtpDelistDate.getBeginDate) > 0)
                {
                    ////throw new ClsBaseException("上市日期不应该早于退市日期");
                    ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("001", _formFun, ClsEnums.StatusSetting.YssSave));
                    TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                    transferErrorMessage.MESSAGESLINK = new List<string>();
                    /* 组装提示信息对象ErrorMessage */
                    string errorMess = ClsRetInfoDealer.getExtWarns("001", _formFun, ClsEnums.StatusSetting.YssSave);
                    transferErrorMessage.MESSAGESLINK.Add(errorMess);
                    throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                }

                ////如果行权起始日早于行权截止日
                if (ClsFunction.sub(this.dtpExerciseBeginDate.getBeginDate, this.dtpExeriseEndDate.getBeginDate) > 0)
                {
                    ////throw new ClsBaseException("行权起始日不应该早于行权截止日");
                    ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("002", _formFun, ClsEnums.StatusSetting.YssSave));
                    TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                    transferErrorMessage.MESSAGESLINK = new List<string>();
                    /* 组装提示信息对象ErrorMessage */
                    string errorMess = ClsRetInfoDealer.getExtWarns("002", _formFun, ClsEnums.StatusSetting.YssSave);
                    transferErrorMessage.MESSAGESLINK.Add(errorMess);
                    throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                }

                sec = new SecBase();
                sec.C_SEC_VAR_CODE = "QZ";
                sec.C_SEC_CODE = this.txtSecCode.Text;
                sec.C_SEC_MKT_CODE = this.txtSecMarketCode.Text;
                sec.C_SEC_NAME = this.txtCertificateName.Text;
                sec.C_MKT_CODE = this.cboMarket.Value;
                sec.C_DC_CODE = this.cboCury.Value;
                sec.C_SEC_CODE_TRG = this.cboTargetSec.Value;
                ////modified by liyanjun 20140820 BUG #99525 SET界面没有千分符 
                sec.N_FV_ISSUE = Convert.ToString(Convert.ToDecimal(this.txtIssueFaceValue.Text));
                sec.N_AMOUNT_HD = Convert.ToString(Convert.ToDecimal(this.txtHandAmount.Text));
                sec.N_PRICE_FCR = Convert.ToString(Convert.ToDecimal(this.txtFactor.Text));
                sec.C_SEC_ISIN_CODE = this.txtIsinCode.Text;
                sec.C_SEC_VAR_CODE = this.cboSecCategory.Value;
                sec.D_TO_LIST = this.dtpMarketDate.getBeginDate;
                sec.D_OFF_LIST = this.dtpDelistDate.getBeginDate;
                sec.C_DV_VAR_DUR = this.txtExerciseCode.Text;
                sec.C_DV_AI_MOD = this.cboExerciseMode.Value; // 结算方式
                sec.N_FV_IR = Convert.ToString(Convert.ToDecimal(this.txtExerciseRatio.Text)); 
                sec.N_PRICE_ISSUE = Convert.ToString(Convert.ToDecimal(this.txtExercisePrice.Text)); 
                sec.C_DV_PI_MOD = this.cboDVPIMOD.Value;
                sec.D_AI_BEGIN = this.dtpExerciseBeginDate.getBeginDateStr;
                sec.D_AI_END = this.dtpExeriseEndDate.getBeginDateStr;
                sec.C_DV_QUT_MOD = this.cboExeSettleMode.Value; // 行权方式
                sec.N_RATE = Convert.ToString(Convert.ToDecimal(this.txtSettPrice.Text));
        
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
                if (null != sec && sec.C_SEC_VAR_CODE.StartsWith("QZ"))
                {
                    this.txtSecCode.Text = sec.C_SEC_CODE; // 证券内码
                    this.txtSecMarketCode.Text = sec.C_SEC_MKT_CODE; // 上市代码
                    this.txtCertificateName.Text = sec.C_SEC_NAME;  // 权证名称
                    this.cboMarket.Value = sec.C_MKT_CODE; // 交易市场
                    this.cboCury.Value = sec.C_DC_CODE; // 交易币种
                    if (sec.C_SEC_CODE_TRG.Equals("null"))
                    {
                        this.cboTargetSec.Text = " ";
                    }
                    else
                    {
                        this.cboTargetSec.Value = sec.C_SEC_CODE_TRG;
                    }

                    this.txtIssueFaceValue.Text = sec.N_FV_ISSUE; // 发行面值
                    this.txtHandAmount.Text = sec.N_AMOUNT_HD; // 每手数量
                    this.txtFactor.Text = sec.N_PRICE_FCR; // 报价因子
                    this.txtIsinCode.Text = sec.C_SEC_ISIN_CODE; // ININ码
                    this.cboSecCategory.Value = sec.C_SEC_VAR_CODE; // 证券品种
                    if (null != sec.D_TO_LIST)
                    {
                        this.dtpMarketDate.setDateTime(sec.D_TO_LIST); // 上市日期
                    }

                    if (null != sec.D_OFF_LIST)
                    {
                        this.dtpDelistDate.setDateTime(sec.D_OFF_LIST); // 到期日期
                    }

                    this.txtExerciseCode.Text = sec.C_DV_VAR_DUR.Equals("null") ? "" : sec.C_DV_VAR_DUR; // 行权代码
                    this.cboExerciseMode.Value = sec.C_DV_AI_MOD.Equals("null") ? "" : sec.C_DV_AI_MOD; // 行权方式
                    this.txtExerciseRatio.Text = sec.N_FV_IR.Equals("null") ? "" : sec.N_FV_IR; // 行权比例
                    this.txtExercisePrice.Text = sec.N_PRICE_ISSUE.Equals("null") ? "" : sec.N_PRICE_ISSUE; // 行权价格
                    this.txtSettPrice.Text = sec.N_RATE.Equals("null") ? "" : sec.N_RATE; // 结算价格
                    if (!string.IsNullOrEmpty(sec.D_AI_BEGIN))
                    {
                        this.dtpExerciseBeginDate.setDateTime(Convert.ToDateTime(sec.D_AI_BEGIN)); // 行权起始日
                    }

                    if (!string.IsNullOrEmpty(sec.D_AI_END))
                    {
                        this.dtpExeriseEndDate.setDateTime(Convert.ToDateTime(sec.D_AI_END)); // 行权截至日
                    }

                    this.cboExeSettleMode.Value = sec.C_DV_QUT_MOD; // 行权结算方式
                    this.cboDVPIMOD.Value = sec.C_DV_PI_MOD;
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// load
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_BASE_QZ_S_Load(object sender, EventArgs e)
        {
            Type dataServiceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            service = (ISecBaseQzService)ServiceFactory.createService(dataServiceType);
            dataService = service;
            //// 通过下面的类统一实现证券内码的调整处理 byleeyu 20130426
            ClsAdjustSecCode clsAdjustSec = new ClsAdjustSecCode(ref txtSecCode, ref cboMarket, ref txtSecMarketCode, this);
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




