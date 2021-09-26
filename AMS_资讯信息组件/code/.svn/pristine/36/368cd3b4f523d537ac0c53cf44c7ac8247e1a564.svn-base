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
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using YssSecInformation.Support.Sv.Service;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Func;


namespace YssSecInformation.Sv.Form
{
    /// <summary>
    /// 利率品种信息设置
    /// author yh 2012.10.30
    /// </summary>
    public partial class Frm_SEC_BASE_LL_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private ISecBaseLlService service;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_SEC_BASE_LL_S()
        {
            InitializeComponent();
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
            ////this.dtpEnd.Enabled = false;

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
                sec = new SecBase();
                sec.C_SEC_CODE = this.txtSecCode.Value; //// 证券内码
                sec.C_SEC_ISIN_CODE = this.txtIsinCode.Value; ////isin代码
                sec.C_SEC_MKT_CODE = this.txtInterestRateCode.Value; ////利率代码
                sec.C_SEC_NAME = this.txtInterestRateName.Value; //// 利率名称
                sec.C_SEC_VAR_CODE = "LL";
                sec.C_DC_CODE = this.cboCury.Value; //// 货币
                sec.C_MKT_CODE = this.cboMarket.Value; //// 交易市场
                double rate = Convert.ToDouble(this.txtTaxRate.Text);
                rate = rate != 0 ? rate / 100 : 0;
                sec.N_RATE = rate.ToString(); //// 利率
                sec.C_DV_VAR_DUR = this.cboInterestRateLimit.Value; //// 期限
                sec.C_ORG_CODE = this.cboOrgan.Value; //// 所属机构
                sec.D_TO_LIST = this.dtpBegin.getBeginDate; ////开始日期
                sec.D_OFF_LIST = this.dtpEnd.getEndDate; ////结束日期
                sec.C_RZRQ_MARK = this.cboRzrq.Value; ////融资融券标示
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return sec;
        }

        /// <summary>
        /// 根据用户选择的交易市场获取结转日期.
        /// bugID 1465  .
        /// chenyoulong 20110329.
        /// </summary>
        /// <param name="sender">object</param>
        /// <param name="e">EventArgs</param>
        private void cboMarket_SelectedValueChanged(object sender, EventArgs e)
        {
            try
            {
                ////if (!(status == ClsEnums.StatusSetting.YssBrow))
                ////{
                ////    if (this.cboMarket.SelectedItems.Count != 0)
                ////    {
                ////        if (!"".Equals(cboMarket.Value.Trim()))
                ////        {
                ////            ////MktExtend market = (MktExtend)this.cboMarket.SelectedItem.DataEntity;
                ////            ////if (market != null)
                ////            ////{
                ////            ////    ////this.iniSettleDate.Text = market.N_SM_DAYS;
                ////            ////}
                ////        }

                ////    }
                ////    else
                ////    {
                ////        ////this.iniSettleDate.Text = "0";
                ////    }
                ////}
            }
            catch (Exception ex)
            {
                ClsBaseException.DiscardException(ex);
                //// YssMessageBox.ShowDyanInformation("计算结算日期出错", ex.Message, "错误信息", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("004", _formFun, ClsEnums.StatusSetting.YssSave));
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
                if (null != sec && sec.C_SEC_VAR_CODE.StartsWith("LL"))
                {
                    this.txtSecCode.Text = sec.C_SEC_CODE;  //// 证券代码
                    this.txtIsinCode.Text = sec.C_SEC_ISIN_CODE; ////isin码
                    this.txtInterestRateCode.Text = sec.C_SEC_MKT_CODE; ////利率代码
                    this.txtInterestRateName.Text = sec.C_SEC_NAME;    //// 利率名称
                    this.cboCury.Value = sec.C_DC_CODE;  //// 存款币种
                    this.cboMarket.Value = sec.C_MKT_CODE; //// 交易市场
                    double rate = Convert.ToDouble(sec.N_RATE);
                    rate = rate * 100;
                    this.txtTaxRate.Text = rate.ToString(); //// 利率
                    this.cboInterestRateLimit.Value = sec.C_DV_VAR_DUR;  //// 期限
                    this.cboOrgan.Value = sec.C_ORG_CODE; //// 所属机构
                    this.dtpBegin.setDateTime(Convert.ToDateTime(sec.D_TO_LIST)); ////开始日期
                    this.dtpEnd.setDateTime(Convert.ToDateTime(sec.D_OFF_LIST)); ////结束日期
                    this.cboRzrq.Value = sec.C_RZRQ_MARK; ////融资融券标示 

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
        ////    if (status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssCopy)
        ////    {
        ////        if (this.cboMarket.SelectedItem != null)
        ////        {
        ////            this.txtSecCode.Text = this.txtInterestRateCode.Text + " " + ((Cls_MKT)this.cboMarket.SelectedItem.DataEntity).C_MKT_NO;
        ////        }

        ////    }

        ////}

        /////// <summary>
        /////// 根据利率代码变化给证券内码赋值.
        /////// </summary>
        /////// <param name="sender">sender.</param>
        /////// <param name="e">e.</param>
        ////private void txtInterestRateCode_TextChanged(object sender, EventArgs e)
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
            service = (ISecBaseLlService)ServiceFactory.createService(dataServiceType);
            dataService = service;
            //// 通过下面的类统一实现证券内码的调整处理 byleeyu 20130426
            ClsAdjustSecCode clsAdjustSec = new ClsAdjustSecCode(ref txtSecCode, ref cboMarket, ref txtInterestRateCode, this);
        }

        /// <summary>
        /// 保存前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_BASE_LL_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
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
        /// 点击新增清空数据
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_BASE_LL_S_YssOnAfterNewClick(object sender, EventArgs e)
        {
            this.txtSecCode.Text = "";
        }



    }
}




