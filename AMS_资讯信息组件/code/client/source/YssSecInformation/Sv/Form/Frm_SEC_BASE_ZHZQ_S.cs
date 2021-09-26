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
using YssSecInformation.Support.Sv.Service;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Func;


namespace YssSecInformation.Sv.Form
{
    /// <summary>
    /// 功能简介：证券信息设置
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.04
    ///
    /// 当前版本：V4.5.0.4
    /// 修改人：wuwenlan
    /// 修改日期： 2011.03.2
    /// 修改简介： 实现对交易市场控件的控制
    ///            控件按需求进行调整控制
    ///  －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         返回对象错误直接抛出异常
    /// </summary>
    public partial class FrmSEC_BASE_ZHZQ_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private ISecBaseZhzqService service;

        /// <summary>
        /// 构造方法
        /// </summary>
        public FrmSEC_BASE_ZHZQ_S()
        {
            InitializeComponent();
            dtpToList.setDateTime(Convert.ToDateTime("1900-1-1"));
            dtpOffList.setDateTime(Convert.ToDateTime("9998-12-31"));
            bUseMVCService = true;
        }

        /// <summary>
        ///  初始化set界面，并给各控件赋值
        ///  wuwenlan
        ///  20111302
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
                ////    // 交易市场代码
                ////    this.cboMkt.Value = mkt.C_MKT_CODE;
                ////}

                ////dtpToList.setDateTime(Convert.ToDateTime("1900-1-1"));
                ////dtpOffList.setDateTime(Convert.ToDateTime("9998-12-31"));

                ////this.dtpOffList.Enabled = false;

                if (this.status == ClsEnums.StatusSetting.YssAdd)
                {
                    this.txtSecCode.Text = "";
                }
            }
            catch (ClsBaseException e)
            {
                ClsBaseException.DiscardException(e);
                ////YssMessageBox.ShowDyanInformation("初始化窗体发生错误", e.Message, "错误信息", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
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
                if (ClsFunction.sub(this.dtpToList.getBeginDate, this.dtpOffList.getBeginDate) > 0)
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
                sec.C_SEC_VAR_CODE = "ZHZQ";
                sec.C_SEC_CODE = this.txtSecCode.Text;
                sec.C_SEC_ISIN_CODE = this.txtIsinCode.Text;
                sec.C_SEC_MKT_CODE = this.txtSecMktCode.Text;
                sec.C_SEC_NAME = this.txtSecName.Text;
                sec.C_SEC_VAR_CODE = this.cboSecVar.Value;
                sec.C_MKT_CODE = this.cboMkt.Value;
                sec.C_DC_CODE = this.cboCury.Value;
                sec.N_FV_ISSUE = this.txtFvIssue.Text;
                sec.N_AMOUNT_HD = this.txtAmountHD.Text;
                sec.N_PRICE_FCR = this.txtPriceFcr.Text;
                sec.D_TO_LIST = this.dtpToList.getBeginDate;
                sec.D_OFF_LIST = this.dtpOffList.getBeginDate;

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
                if (null != sec)
                {
                    // 证券内码
                    this.txtSecCode.Text = sec.C_SEC_CODE;

                    // ISIN代码
                    this.txtIsinCode.Text = (sec.C_SEC_ISIN_CODE.Equals("null") ? "" : sec.C_SEC_ISIN_CODE);

                    // 上市代码
                    this.txtSecMktCode.Text = sec.C_SEC_MKT_CODE;

                    // 证券名称
                    this.txtSecName.Text = sec.C_SEC_NAME;

                    // 证券品种
                    this.cboSecVar.Value = sec.C_SEC_VAR_CODE;

                    // 交易市场
                    this.cboMkt.Value = sec.C_MKT_CODE;

                    // 交易币种
                    this.cboCury.Value = sec.C_DC_CODE;

                    // 发行面值
                    this.txtFvIssue.Text = sec.N_FV_ISSUE;

                    // 每手数量
                    this.txtAmountHD.Text = sec.N_AMOUNT_HD;

                    // 报价因子
                    this.txtPriceFcr.Text = sec.N_PRICE_FCR;


                    if (null != sec.D_TO_LIST)
                    {
                        this.dtpToList.setDateTime(sec.D_TO_LIST); // 上市日期
                    }

                    if (null != sec.D_OFF_LIST)
                    {
                        this.dtpOffList.setDateTime(sec.D_OFF_LIST); // 退市日期
                    }
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }
        
        /// <summary>
        /// 验证时间的合法性
        /// </summary>
        public new void checkInput()
        {
            if (this.dtpToList.getBeginDate > this.dtpOffList.getBeginDate)
            {
                ////throw new ClsBaseException("【退市日期】不能小于【上市日期】");
                ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("001", _formFun, ClsEnums.StatusSetting.YssSave));
                TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                transferErrorMessage.MESSAGESLINK = new List<string>();
                /* 组装提示信息对象ErrorMessage */
                string errorMess = ClsRetInfoDealer.getExtWarns("001", _formFun, ClsEnums.StatusSetting.YssSave);
                transferErrorMessage.MESSAGESLINK.Add(errorMess);
                throw new TransferException(JsonUtil.toJson(transferErrorMessage));
            }
        }

        /// <summary>
        /// load
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void FrmSEC_BASE_ZHZQ_S_Load(object sender, EventArgs e)
        {
            Type dataServiceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            service = (ISecBaseZhzqService)ServiceFactory.createService(dataServiceType);
            dataService = service;
            //// 通过下面的类统一实现证券内码的调整处理 byleeyu 20130426
            ClsAdjustSecCode clsAdjustSec = new ClsAdjustSecCode(ref txtSecCode, ref cboMkt, ref txtSecMktCode, this);
        }
    }
}




