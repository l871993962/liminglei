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
using System.Text.RegularExpressions;



using System.Collections.Generic;
using FAST.Core.Communication.DataService;
using YssSecInformation.Support.Sv.Service;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Func;

namespace YssSecInformation.Sv.Form
{
    /// <summary>
    /// 现货品种信息
    /// 创建版本：V4.5.0.1
    /// 创建人： zgy
    /// 创建日期： 2013.03.25
    /// </summary>
    public partial class Frm_SEC_BASE_XH_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private ISecBaseXhService service;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_SEC_BASE_XH_S()
        {
            InitializeComponent();
            dtpMarketDate.setDateTime(Convert.ToDateTime("1900-1-1"));
            dtpDelistDate.setDateTime(Convert.ToDateTime("9998-12-31"));
            bUseMVCService = true;
        }

        /// <summary>
        ///  初始化set界面，并给各控件赋值.
        /// </summary>
        public override void yssInitCtlAttr()
        {
            try
            {
                ////dtpMarketDate.setDateTime(Convert.ToDateTime("1900-1-1")); by Yuntao Lau
                dtpDelistDate.setDateTime(Convert.ToDateTime("9998-12-31"));
                if (status == ClsEnums.StatusSetting.YssAdd)
                {
                    this.txtSecCode.Text = "";
                }
            }
            catch (ClsBaseException e)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFormLoadErr(this.Text));
                ClsBaseException.DiscardException(e);
            }

        }

        ///<summary>
        ///封装界面元素为pojo对象
        ///</summary>
        ///<returns>由界面元素组成的对象</returns>
        public override AuditableParamPojo faceInfoToObjMVC()
        {
            SecBase sec = null;
            try
            {
                //// add by liyanjun 2016-8-29 STORY31079 修改咨询信息品种有持仓的情况下给予提醒
                SecBase currSec = (SecBase)yssGetBaseSelTypeItemMVC();
                //// 该证券有库存信息，修改有风险，请联系技术人员！
                if (null != currSec && currSec.C_SEC_VAR_CODE != this.cboXHType.Value)
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

                if (ClsFunction.sub(this.dtpMarketDate.getBeginDate, this.dtpDelistDate.getEndDate) > 0)
                {
                    SysFun fun = new SysFun();
                    fun.C_FUN_CODE = "sec";
                    ////throw new ClsBaseException("上市日期应该小于起始日期");
                    ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("001", fun, ClsEnums.StatusSetting.YssSave));
                    TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                    transferErrorMessage.MESSAGESLINK = new List<string>();
                    /* 组装提示信息对象ErrorMessage */
                    string errorMess = ClsRetInfoDealer.getExtWarns("001", _formFun, ClsEnums.StatusSetting.YssSave);
                    transferErrorMessage.MESSAGESLINK.Add(errorMess);
                    throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                }

                sec = new SecBase();
                sec.C_SEC_VAR_CODE = "XH";
                sec.C_SEC_CODE = this.txtSecCode.Text;
                sec.C_SEC_ISIN_CODE = this.txtIsinCode.Text;
                sec.C_SEC_MKT_CODE = this.txtXHCode.Text;
                sec.C_SEC_NAME = this.txtXHName.Text;
                sec.C_SEC_VAR_CODE = this.cboXHType.Value;
                sec.C_MKT_CODE = this.cboMrket.Value;
                sec.C_DC_CODE = this.cboCury.Value;
                sec.N_AMOUNT_HD = this.txtBJUnit.Text;
                sec.N_FV_ISSUE = this.txtJYUnit.Text;
                ////sec.N_RATE = this.txtBZJRatio.Text;  // 保证金比例
                if (this.txtBZJRatio.Text.Equals(""))
                {
                    sec.N_RATE = "";
                }
                else
                {
                    double doublebzj = Convert.ToDouble(this.txtBZJRatio.Text);
                    string strbjz = Convert.ToString(doublebzj / 100);
                    sec.N_RATE = strbjz;
                }

                sec.D_TO_LIST = this.dtpMarketDate.getBeginDate;
                sec.D_OFF_LIST = this.dtpDelistDate.getBeginDate;
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
                    this.txtSecCode.Text = sec.C_SEC_CODE;
                    this.txtIsinCode.Text = sec.C_SEC_ISIN_CODE;
                    this.txtXHCode.Text = sec.C_SEC_MKT_CODE;
                    this.txtXHName.Text = sec.C_SEC_NAME;
                    this.cboXHType.Value = sec.C_SEC_VAR_CODE;
                    this.cboMrket.Value = sec.C_MKT_CODE;
                    this.cboCury.Value = sec.C_DC_CODE;
                    this.txtBJUnit.Text = sec.N_AMOUNT_HD;
                    this.txtJYUnit.Text = sec.N_FV_ISSUE;
                    //// 保证金比例
                    if (sec.N_RATE == "null")
                    {
                        this.txtBZJRatio.Text = "";
                    }
                    else
                    {
                        double intbzj = Convert.ToDouble(sec.N_RATE);
                        string strbjz = Convert.ToString(intbzj * 100);
                        this.txtBZJRatio.Text = strbjz;
                    }

                    if (null != sec.D_TO_LIST)
                    {
                        this.dtpMarketDate.setDateTime(Convert.ToDateTime(sec.D_TO_LIST));   // 上市日期
                    }

                    if (null != sec.D_OFF_LIST)
                    {
                        this.dtpDelistDate.setDateTime(Convert.ToDateTime(sec.D_OFF_LIST)); // 退市日期
                    }
                }
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }
        }

        /// <summary>
        /// ISIN 代码的文本框处理事件
        /// 当输入特殊字符如（@#￥%）等的时候，应该检查，防止非法数据进入数据库
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
                checkInput(this.txtXHName.Text);
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
                checkInput(this.txtXHCode.Text);
                ////证券内码不能保护逗号，判定科目时避免判定成子级。
                this.txtSecCode.Text = this.txtSecCode.Text.Replace(".", "");
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
        private void Frm_SEC_BASE_XH_S_Load(object sender, EventArgs e)
        {
            Type dataServiceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            service = (ISecBaseXhService)ServiceFactory.createService(dataServiceType);
            dataService = service;
            //// 通过下面的类统一实现证券内码的调整处理 byleeyu 20130426
            ClsAdjustSecCode clsAdjustSec = new ClsAdjustSecCode(ref txtSecCode, ref cboMrket, ref txtXHCode, this);
        }

        /// <summary>
        /// 证券内码去掉.BUG #112801 【紧急】现货品种信息的内码不能带"." 
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void txtSecCode_TextChanged(object sender, EventArgs e)
        {
            this.txtSecCode.Text = this.txtSecCode.Text.Replace(".", "");
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
            if (result && status == ClsEnums.StatusSetting.YssEdit && !(this.frmBaseViewList.yssGetSelTypeItemMVC() as SecBase).C_SEC_VAR_CODE.Equals(this.cboXHType.Value))
            {
                ClsSecTypeTip secTypeTip = new ClsSecTypeTip();
                result = secTypeTip.checkSecHold(this.txtSecCode.Text);
            }

            return result;
        }

    }
}




