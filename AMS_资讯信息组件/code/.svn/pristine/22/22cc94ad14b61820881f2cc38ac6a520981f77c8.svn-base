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


using FAST.Core.Communication.DataService;
using FAST.Core.BaseControl;
using YssSecInformation.Support.Sv.Service;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Func;






namespace YssSecInformation.Sv.Form
{
    /// <summary>
    /// 功能简介：存款种类信息设置，负责存款种类信息的增删改查等功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2011.01.20
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         返回对象错误直接抛出异常
    /// </summary>
    public partial class Frm_SEC_BASE_CK_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private ISecBaseCkService service;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_SEC_BASE_CK_S()
        {
            InitializeComponent();
            bUseMVCService = true;
        }

        /// <summary>
        /// 初始化查询模块控件.
        /// </summary>
        public override void yssInitCtlAttr()
        {
            try
            {
                if (status == ClsEnums.StatusSetting.YssAdd)
                {
                    this.iniRateYearDays.Text = "360";
                    this.txtSecCode.Text = "";
                }   
            }
            catch (ClsBaseException e)
            {
                throw new ClsBaseException(e.Message);
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
                if (null != currSec && currSec.C_SEC_VAR_CODE != this.cboSavingType.Value)
                {
                    ISecBaseInfoDataService secBaseInfoDataService = DataServiceFactory.createService<ISecBaseInfoDataService>();
                    string isExistsStock = secBaseInfoDataService.isExistsStock(currSec.C_SEC_CODE);
                    if (isExistsStock == "true")
                    {
                        TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                        transferErrorMessage.MESSAGESLINK = new List<string>();
                        /* 组装提示信息对象ErrorMessage */
                        string errorMess = ClsRetInfoDealer.getExtWarns("002", _formFun, ClsEnums.StatusSetting.YssSave);
                        transferErrorMessage.MESSAGESLINK.Add(errorMess);
                        throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                    }
                }

                sec = new SecBase();
                sec.C_SEC_CODE = this.txtSecCode.Text; // 证券内码
                sec.C_SEC_ISIN_CODE = this.txtIsinCode.Text; // isin代码
                sec.C_SEC_MKT_CODE = this.txtSavingCode.Value; // 利率代码
                sec.C_SEC_NAME = this.txtSavingName.Text; // 利率名称
                sec.C_SEC_VAR_CODE = this.cboSavingType.Value; // 证券品种
                sec.C_MKT_CODE = this.cboMarket.Value; // 交易市场
                sec.C_DC_CODE = this.cboCury.Value; // 货币
                sec.C_DV_AI_MOD = this.cboCountMode.Value; // 计息方式
                sec.C_DV_VAR_DUR = this.cboPayFrequency.Value; // 付息频率
                sec.C_DV_AI_EXPR = this.cboCountFormula.Value; // 计息公式
                sec.N_AMOUNT_HD = this.iniRateYearDays.Value.ToString(); // 利率年化天数
                sec.C_ORG_CODE = this.cboOrgan.Value == null ? "" : this.cboOrgan.Value; // 所属机构
                sec.C_DV_QUT_MOD = this.cboLimit.Value == null ? "" : this.cboLimit.Value; //// 品种期限 add by Yuntao Lau 2015.11.16 STORY #27224
                sec.C_SJSSZQ = this.cboSjsszq.Value == null ? "" : this.cboSjsszq.Value; // 实际所属证券 add by zhd 2016-09-07 STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
				sec.C_BANK_HEAD = this.bankHead.Value == null ? "" : this.bankHead.Value; // 银行总行 add by liudaiqiang 2017.03.22 Story #38881
                sec.C_BANK_BRANCH = this.bankBranch.Value == null ? "" : this.bankBranch.Value; // 银行支行 add by liudaiqiang 2017.03.22 Story #38881
                sec.D_TO_LIST = this.dtpBegin.getBeginDate; ////开始日期
                sec.D_OFF_LIST = this.dtpEnd.getEndDate; ////结束日期
                ////核算方式
                sec.C_DV_ISSUE = this.cboIssureMode.Value == null ? " " : this.cboIssureMode.Value;
                sec.N_PRICE_ISSUE = this.txtPurPeriod.Text;  //// 存放期限 STORY #16824 存放，拆借，回购到期日期自动推算 update by chenwenhai 20140514 
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
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
                if (null != sec && (sec.C_SEC_VAR_CODE.StartsWith("CK") || sec.C_SEC_VAR_CODE.StartsWith("DK")))
                {
                    this.txtSecCode.Text = sec.C_SEC_CODE;  // 证券代码
                    this.txtIsinCode.Text = sec.C_SEC_ISIN_CODE; // isin代码
                    this.txtSavingCode.Text = sec.C_SEC_MKT_CODE; // 利率代码
                    this.txtSavingName.Text = sec.C_SEC_NAME;    // 利率名称名称
                    this.cboSavingType.Value = sec.C_SEC_VAR_CODE;   // 存款品种类型
                    this.cboMarket.Value = sec.C_MKT_CODE; // 交易市场
                    this.cboCury.Value = sec.C_DC_CODE;  // 存款币种
                    this.cboCountMode.Value = sec.C_DV_AI_MOD; // 计息方式
                    this.cboPayFrequency.Value = sec.C_DV_VAR_DUR; // 付息频率
                    this.cboCountFormula.Value = sec.C_DV_AI_EXPR; // 计息公式 
                    this.iniRateYearDays.Value = Convert.ToInt32(sec.N_AMOUNT_HD); // 利率年化天数
                    this.cboOrgan.Value = sec.C_ORG_CODE; // 所属机构
                    this.cboLimit.Value = sec.C_DV_QUT_MOD; //// 品种期限 add by Yuntao Lau 2015.11.16 STORY #27224
                    this.cboSjsszq.Value = sec.C_SJSSZQ; // 实际所属证券 add by zhd 2016-09-07 STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
					this.bankHead.Value = sec.C_BANK_HEAD;  //// 银行总行 add by liudaiqiang 2017.03.22 Story #38881
                    this.bankBranch.Value = sec.C_BANK_BRANCH;  //// 银行支行 add by liudaiqiang 2017.03.22 Story #38881
                    this.dtpBegin.setDateTime(Convert.ToDateTime(sec.D_TO_LIST)); ////开始日期
                    this.dtpEnd.setDateTime(Convert.ToDateTime(sec.D_OFF_LIST)); ////结束日期
                    this.cboIssureMode.Value = sec.C_DV_ISSUE; ////核算方式
                    this.txtPurPeriod.Text = sec.N_PRICE_ISSUE; //// 存放期限
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /////// <summary>
        ///////  给证券内码赋值.
        /////// </summary>
        ////private void txtSecCodeTextChanged()
        ////{
        ////    //// if (status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssCopy)
        ////    //// {
        ////    if (this.cboMarket.SelectedItem != null)
        ////    {
        ////        this.txtSecCode.Text = this.txtSavingCode.Text + " " + ((Cls_MKT)this.cboMarket.SelectedItem.DataEntity).C_MKT_NO;
        ////    }
            
        ////    //// }
            
        ////}

        /////// <summary>
        /////// 根据利率代码变化给证券内码赋值.
        /////// </summary>
        /////// <param name="sender">sender.</param>
        /////// <param name="e">e.</param>
        ////private void txtSavingCode_TextChanged(object sender, EventArgs e)
        ////{
        ////    txtSecCodeTextChanged();
        ////}

        /////// <summary>
        /////// 根据交易市场变化给证券内码赋值.
        /////// </summary>
        /////// <param name="sender">sender.</param>
        /////// <param name="e">e.</param>
        ////private void cboMarket_SelectedValueChanged(object sender, EventArgs e)
        ////{
        ////    txtSecCodeTextChanged();
        ////}


        /// <summary>
        /// load
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_BASE_CK_S_Load(object sender, EventArgs e)
        {
            Type dataServiceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            service = (ISecBaseCkService)ServiceFactory.createService(dataServiceType);
            dataService = service;
            //// 通过下面的类统一实现证券内码的调整处理 byleeyu 20130426
            ClsAdjustSecCode clsAdjustSec = new ClsAdjustSecCode(ref txtSecCode, ref cboMarket, ref txtSavingCode, this);
        }

        /// <summary>
        /// 保存前验证事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_BASE_CK_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        {
            if (ClsFunction.sub(this.dtpBegin.getBeginDate, this.dtpEnd.getBeginDate) > 0)
            {
                ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("001", _formFun, ClsEnums.StatusSetting.YssSave));
                TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                transferErrorMessage.MESSAGESLINK = new List<string>();
                /* 组装提示信息对象ErrorMessage */
                string errorMess = ClsRetInfoDealer.getExtWarns("001", _formFun, ClsEnums.StatusSetting.YssSave);
                transferErrorMessage.MESSAGESLINK.Add(errorMess);
                throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                e.IsCancel = true;
            }
            else
            {
                e.IsCancel = false;
            }
        }

        /// <summary>
        /// 计息公式值改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboCountFormula_SelectedValueChanged(object sender, EventArgs e)
        {
            if (cboCountFormula.Text.Trim().Length != 0 && cboCountFormula.Text.IndexOf("365") != -1)
            {
                this.iniRateYearDays.Text = "365";
            }
            else 
            {
                this.iniRateYearDays.Text = "360";
            }
        }

        /// <summary>
        /// 浏览状态机构名称要置灰，不可用状态
        /// </summary>
        protected override void YssChangeControlState()
        {
            base.YssChangeControlState();
            if (this.status == ClsEnums.StatusSetting.YssBrow)
            {
                this.cboOrgan.YssReadOnly = true;
                this.bankHead.YssReadOnly = true;
                this.bankBranch.YssReadOnly = true;
            }
            else
            {
                this.cboOrgan.YssReadOnly = false;
                this.bankHead.YssReadOnly = false;
                this.bankBranch.YssReadOnly = false;
            }
        }
             
        /// <summary>
        /// 证券品种下拉框值改变事件
        /// add by Yuntao Lau 2015.11.16 STORY #27224
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboSavingType_SelectedValueChanged(object sender, EventArgs e)
        {
            this.cboLimit.Value = null;
            if ("CK_TZ".Equals(this.cboSavingType.Value))
            {
                this.cboLimit.MethodInfo.MethodParamValues = new string[] { "SEC_1D,SEC_7D," };
            }
            else if ("CK_DQ".Equals(this.cboSavingType.Value))
            {
                this.cboLimit.MethodInfo.MethodParamValues = new string[] { "SEC_3M,SEC_6M,SEC_1Y,SEC_2Y,SEC_3Y,SEC_5Y," };
            }
            else if ("CK_QT".Equals(this.cboSavingType.Value))
            {
                this.cboLimit.MethodInfo.MethodParamValues = new string[] { "SEC_ON," };
            }
            else
            {
                this.cboLimit.MethodInfo.MethodParamValues = new string[] { "SEC_ON,SEC_1D,SEC_7D,SEC_3M,SEC_6M,SEC_1Y,SEC_2Y,SEC_3Y,SEC_5Y," };
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
            if (result && status == ClsEnums.StatusSetting.YssEdit && !(this.frmBaseViewList.yssGetSelTypeItemMVC() as SecBase).C_SEC_VAR_CODE.Equals(this.cboSavingType.Value))
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
            service = (ISecBaseCkService)ServiceFactory.createService(dataServiceType);
            List<BasePojo> pojoList = service.queryForSjsszq();
            foreach (BasePojo basePojo in pojoList)
            {
                Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(((SecBase)basePojo));
                cboSjsszq.Items.Add(entity);
            }
        }
		
		
        /// <summary>
        /// 银行支行加载前时间
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void bankBranch_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            string strSecBase = "";
            try
            {
                if (this.bankHead.Text.Trim().Length > 0)
                {
                    this.bankBranch.DisplayName = "C_ORG_NAME";
                    this.bankBranch.DisplayValue = "C_ORG_CODE";
                    ControlMethodInfo controlMethodInfo = new ControlMethodInfo();
                    controlMethodInfo.MethodName = "getBankBranchByHead";
                    controlMethodInfo.MethodParams = null;
                    controlMethodInfo.Methods = null;
                    strSecBase = bankHead.Value;
                    controlMethodInfo.MethodParamValues = new string[] { strSecBase + "," };
                    bankBranch.MethodInfo = controlMethodInfo;
                    this.bankBranch.NodeID = "C_ORG_CODE";
                    this.bankBranch.Parameter = "C_ORG_CODE~C_ORG_NAME";
                    this.bankBranch.ParaNodeID = "C_ORG_CODE_P";
                    this.bankBranch.YssAssociaType = YssInformation.Support.Context.AssociaType.base_organ;
                    this.bankBranch.YssCaption = "银行支行";
                    this.bankBranch.YssDataSrc = FAST.Core.Context.ClsEnums.DataSrc.SrcDB;
                }
                else
                {
                    this.bankBranch.Items.Clear();
                    e.IsCancel = true;
                }
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }
        }
		
    }
}




