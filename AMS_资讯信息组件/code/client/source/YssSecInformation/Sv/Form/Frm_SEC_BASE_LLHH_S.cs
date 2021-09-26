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
using YssSecInformation.Support.Sv.Service;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Func;

namespace YssSecInformation.Sv.Form
{
    /// <summary>
    /// 功能简介：利率互换品种信息设置界面，利率互换品种信息的CRUD功能的实现 
    /// 创建版本：V1.0.0.2
    /// 创建人： jiangjin
    /// 创建日期： 2012.10.24
    /// </summary>
    public partial class Frm_SEC_BASE_LLHH_S : FrmBaseSet
    {
        /// <summary>
        /// 后台访问接口
        /// </summary>
        private ISecBaseLLHHService service;

        //// edit by Yuntao Lau 2015.09.24 STORY #25343
        /////// <summary>
        /////// 证券品种_互换
        /////// </summary>
        ////public const string SEC_VAR_CODE_HH = "HH";

        /////// <summary>
        /////// 证券品种_互换_利率
        /////// </summary>
        ////public const string SEC_VAR_CODE_HH_LL = "HH_LL";

        /// <summary>
        /// 构造
        /// </summary>
        public Frm_SEC_BASE_LLHH_S()
        {
            InitializeComponent();
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
                sec = new SecBase();

                sec.C_SEC_CODE = txtSecCode.Text;
                ////sec.C_SEC_VAR_CODE = Frm_SEC_BASE_LLHH_S.SEC_VAR_CODE_HH_LL; //// edit by Yuntao Lau 2015.09.24
                sec.C_DC_CODE = this.cboCury.Value;
                sec.C_SEC_MKT_CODE = this.txtSecMktCode.Text;
                sec.C_SEC_NAME = this.txtSecName.Text;
                //// add by Yuntao Lau 2015.09.24 STORY #25343
                sec.C_SEC_VAR_CODE = this.cboSecVar.Value; //// 证券品种
                sec.C_MKT_CODE = this.cboMkt.Value;
                sec.C_SEC_ISIN_CODE = this.txtIsinCode.Text;
                sec.D_TO_LIST = this.dtpBegin.getBeginDate; //// 开始日期
                sec.D_OFF_LIST = this.dtpEnd.getEndDate; //// 结束日期
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
                if (null != sec)
                {
                    this.txtSecMktCode.Text = sec.C_SEC_MKT_CODE;  //// 互换代码
                    this.txtSecName.Text = sec.C_SEC_NAME;    //// 互换名称
                    this.cboCury.Value = sec.C_DC_CODE;  //// 交易币种
                    this.cboMkt.Value = sec.C_MKT_CODE; //// 交易市场
                    this.txtIsinCode.Text = sec.C_SEC_ISIN_CODE; //// ISIN 代码
                    this.txtSecCode.Text = sec.C_SEC_CODE; //// 互换代码
                    //// add by Yuntao Lau 2015.09.24 STORY #25343
                    this.cboSecVar.Value = sec.C_SEC_VAR_CODE; //// 证券品种
                    this.dtpBegin.setDateTime(Convert.ToDateTime(sec.D_TO_LIST));
                    this.dtpEnd.setDateTime(Convert.ToDateTime(sec.D_OFF_LIST));
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /////// <summary>
        /////// 生成证券内码
        /////// </summary>
        /////// <param name="sec_mkt_code">证券上市代码</param>
        /////// <param name="c_mkt_code">交易市场代码</param>
        /////// <returns>证券内码</returns>
        ////private string createSecCode(string sec_mkt_code, string c_mkt_code)
        ////{
        ////    return sec_mkt_code + " " + c_mkt_code;
        ////}

        /// <summary>
        /// 界面加载初始化数据
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_BASE_LLHH_S_Load(object sender, EventArgs e)
        {
            Type dataServiceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            service = (ISecBaseLLHHService)ServiceFactory.createService(dataServiceType);
            dataService = service;
            //// 通过下面的类统一实现证券内码的调整处理 byleeyu 20130426
            ClsAdjustSecCode clsAdjustSec = new ClsAdjustSecCode(ref txtSecCode, ref cboMkt, ref txtSecMktCode, this);
        }

        /////// <summary>
        /////// 修改上市代码
        /////// </summary>
        /////// <param name="sender">sender</param>
        /////// <param name="e">e</param>
        ////private void txtSecMktCode_TextChanged(object sender, EventArgs e)
        ////{
        ////    if (this.cboMkt.SelectedItem != null)
        ////    {
        ////        txtSecCode.Text = createSecCode(txtSecMktCode.Text, ((Cls_MKT)this.cboMkt.SelectedItem.DataEntity).C_MKT_NO);
        ////    }

        //// }

        /////// <summary>
        /////// 修改交易市场
        /////// </summary>
        /////// <param name="sender">sender</param>
        /////// <param name="e">e</param>
        ////private void cboMkt_SelectedValueChanged(object sender, EventArgs e)
        ////{
        ////    txtSecCode.Text = createSecCode(txtSecMktCode.Text, cboMkt.Value);
        ////}

        /// <summary>
        /// 保存前判断启用日期和截止日期
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_BASE_LLHH_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        {
            if (ClsFunction.sub(this.dtpBegin.getBeginDate, this.dtpEnd.getBeginDate) > 0)
            {
                ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("001", _formFun, ClsEnums.StatusSetting.YssSave));
                ////e.IsCancel = true;
                TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                transferErrorMessage.MESSAGESLINK = new List<string>();
                /* 组装提示信息对象ErrorMessage */
                string errorMess = ClsRetInfoDealer.getExtWarns("001", _formFun, ClsEnums.StatusSetting.YssSave);
                transferErrorMessage.MESSAGESLINK.Add(errorMess);
                throw new TransferException(JsonUtil.toJson(transferErrorMessage));
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
        private void Frm_SEC_BASE_LLHH_S_YssOnAfterNewClick(object sender, EventArgs e)
        {
            this.txtSecCode.Text = "";
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




