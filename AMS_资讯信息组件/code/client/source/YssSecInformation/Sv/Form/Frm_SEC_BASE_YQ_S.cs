﻿using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;

using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Interface;
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
using FAST.Core.BaseControl;
using FAST.Core.Communication.DataService;
using YssSecInformation.Support.Sv.Service;
using YssSecInformation.Support.Pojo.Sec;
using YssInformation.Support.Sys.Dictionary.Pojo;
using YssInformation.Support.Bi.CuryPair.Pojo;
using YssSecInformation.Support.Func;





namespace YssSecInformation.Sv.Form
{
    /// <summary>
    /// 功能简介：远期品种信息设置界面，负责远期信息的显示和查询功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： liuliang
    /// 创建日期： 2012.05.29
    /// </summary>
    public partial class Frm_SEC_BASE_YQ_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private ISecBaseYqService service;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_SEC_BASE_YQ_S()
        {
            InitializeComponent();
            this.dtpToList.setDateTime(Convert.ToDateTime("1900-1-1"));
            this.dtpOffList.setDateTime(Convert.ToDateTime("9998-12-31"));
            bUseMVCService = true;
        }

        /// <summary>
        ///  初始化set界面，并给各控件赋值.
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
                ////if (mkt != null)
                ////{
                ////    this.cboMkt.Value = mkt.C_MKT_CODE; // 交易市场代码
                ////}

                ////dtpToList.setDateTime(Convert.ToDateTime("1900-1-1"));
                dtpOffList.setDateTime(Convert.ToDateTime("9998-12-31"));

                if (this.status == ClsEnums.StatusSetting.YssAdd)
                {
                    this.txtSecCode.Text = "";
                    this.cboPCYK.Value = "CNY";
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
                if (null != currSec && currSec.C_SEC_VAR_CODE != this.cboSecVar.Value)
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

                if (this.dtpToList.getBeginDate.CompareTo(this.dtpOffList.getBeginDate) > 0)
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
                sec.C_SEC_VAR_CODE = "YQ";
                sec.C_SEC_CODE = this.txtSecCode.Text;
                sec.C_SEC_ISIN_CODE = this.txtIsinCode.Text;
                sec.C_SEC_MKT_CODE = this.txtSecMktCode.Text;
                sec.C_SEC_NAME = this.txtSecName.Text;
                sec.C_SEC_VAR_CODE = this.cboSecVar.Value;
                sec.C_MKT_CODE = this.cboMkt.Value;
                sec.C_SEC_CODE_TRG = this.cboSecCodeTag.Value;
                sec.C_DV_VAR_DUR = this.cboDvVarDur.Value;

                ////sec.N_RATE = this.txtNRate.Text;
                if (this.txtNRate.Text.Equals(""))
                {
                    sec.N_RATE = null;
                }
                else
                {
                    double doublebzj = Convert.ToDouble(this.txtNRate.Text);
                    string strbjz = Convert.ToString(doublebzj / 100);
                    sec.N_RATE = strbjz;
                }

                sec.N_FV_IR = this.txtFvIr.Value;
                sec.D_TO_LIST = this.dtpToList.getBeginDate;
                sec.D_OFF_LIST = this.dtpOffList.getBeginDate;
                sec.C_DC_CODE = this.cboCury.Value;
                sec.C_PCYK_CURY = this.cboPCYK.Value;

            }
            catch (Exception ye)
            {
                if (ye is TransferErrorMessage)
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
                if (null != sec && sec.C_SEC_VAR_CODE.StartsWith("YQ"))
                {
                    this.txtSecCode.Text = sec.C_SEC_CODE; // 证券内码
                    this.txtIsinCode.Text = sec.C_SEC_ISIN_CODE == "null" ? "" : sec.C_SEC_ISIN_CODE; // ISIN代码
                    this.txtSecMktCode.Text = sec.C_SEC_MKT_CODE; // 上市代码
                    this.txtSecName.Text = sec.C_SEC_NAME; // 证券名称
                    this.cboSecVar.Value = sec.C_SEC_VAR_CODE; // 证券品种
                    this.cboMkt.Value = sec.C_MKT_CODE; // 交易市场
                    this.cboSecCodeTag.Text = sec.C_SEC_CODE_TRG;       
                    this.cboDvVarDur.Value = sec.C_DV_VAR_DUR;

                    //// 保证金比例
                    ////if (sec.N_RATE == "null")
                    ////{
                    ////    this.txtNRate.Text = "";
                    ////}
                    ////else
                    ////{
                    ////    double doublebzj = Convert.ToDouble(sec.N_RATE);
                    ////    string strbjz = Convert.ToString(doublebzj * 100);
                    ////    this.txtNRate.Text = strbjz;
                    ////}
                    this.txtNRate.Text = Convert.ToString(Convert.ToDouble(sec.N_RATE) * 100);

                    this.txtFvIr.Text = sec.N_FV_IR;
                    this.cboCury.Value = sec.C_DC_CODE;
                    this.cboPCYK.Value = sec.C_PCYK_CURY;
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
        /// 通过证券品种加载标的证券
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboSecCodeTag_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            try
            {
                string secType = "";
                if (null != this.cboSecVar.Value && null != this.cboSecVar.Value)
                {
                    string secCode = ((SecVar)this.cboSecVar.SelectedItem.DataEntity).C_DA_CODE;
                    if (secCode.Contains("_"))
                    {
                        secType = this.cboSecCodeTag.QueryCond = secCode.Split('_')[1];
                    }
                    else 
                    {
                        secType = this.cboSecCodeTag.QueryCond;
                    }

                    if ("WH".Equals(secType))
                    {
                        cboSecCodeTag.YssAssociaType = YssInformation.Support.Context.AssociaType.base_currencyPair;
                        cboSecCodeTag.QueryType = "KEY";
                        cboSecCodeTag.QueryCond = "";
                    }
                    else
                    {
                        cboSecCodeTag.YssAssociaType = YssSecInformation.Support.Context.AssociaType.sv_sec;
                        cboSecCodeTag.QueryType = "CacheType";
                        cboSecCodeTag.QueryCond = secType;
                    }
                }
                else
                {
                    cboSecCodeTag.Items.Clear();
                    e.Cancel = true;
                }
            }
            catch (Exception ex) 
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }
        }

        /// <summary>
        /// 证券品种改变清空标的证券
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboSecVar_SelectedValueChanged(object sender, EventArgs e)
        {
            try
            {
                string secType = "";
                string secCode = "";
                if (null != this.cboSecVar.Value && cboSecVar.Value.Trim().Length != 0 && cboSecVar.SelectedItem != null)
                {
                    secCode = ((SecVar)this.cboSecVar.SelectedItem.DataEntity).C_DA_CODE;
                    if (secCode.Contains("_"))
                    {
                        secType = this.cboSecCodeTag.QueryCond = secCode.Split('_')[1];
                    }
                    else
                    {
                        secType = this.cboSecCodeTag.QueryCond = secCode;
                    }
                    ////modify by fj 20130923 下拉框条件处理方式调整
                    if ("WH".Equals(secType))
                    {
                        cboSecCodeTag.YssAssociaType = YssInformation.Support.Context.AssociaType.base_currencyPair;
                        FAST.Core.BaseControl.ControlMethodInfo contromMethodInfo = new FAST.Core.BaseControl.ControlMethodInfo();
                        contromMethodInfo.MethodName = "getDataList";
                        contromMethodInfo.MethodParamValues = new string[] { };
                        this.cboSecCodeTag.MethodInfo = contromMethodInfo;
                        ////cboSecCodeTag.QueryType = "CacheType";
                        ////cboSecCodeTag.QueryCond = "WH";
                    }
                    else if ("LL".Equals(secType))
                    {
                        cboSecCodeTag.YssAssociaType = YssSecInformation.Support.Context.AssociaType.sv_sec;
                        ////cboSecCodeTag.QueryType = "CacheType";
                        ////cboSecCodeTag.QueryCond = "CK";
                        FAST.Core.BaseControl.ControlMethodInfo contromMethodInfo = new FAST.Core.BaseControl.ControlMethodInfo();
                        contromMethodInfo.MethodName = "getDataListByTypes";
                        contromMethodInfo.MethodParamValues = new string[] { "CK," };
                        this.cboSecCodeTag.MethodInfo = contromMethodInfo;
                    }
                    else
                    {
                        cboSecCodeTag.YssAssociaType = YssSecInformation.Support.Context.AssociaType.sv_sec;
                        ////cboSecCodeTag.QueryType = "CacheType";
                        ////cboSecCodeTag.QueryCond = secType;
                        FAST.Core.BaseControl.ControlMethodInfo contromMethodInfo = new FAST.Core.BaseControl.ControlMethodInfo();
                        contromMethodInfo.MethodName = "getDataListByTypes";
                        contromMethodInfo.MethodParamValues = new string[] { secType + "," };
                        this.cboSecCodeTag.MethodInfo = contromMethodInfo;
                    }
                    ////end by fj 20130923 下拉框条件处理方式调整
                }
                

                this.cboSecCodeTag.Items.Clear();
                this.cboSecCodeTag.Text = "";

                //// bug 81424 By Jinghehe 2013-10-17 在远期品种没有数据的以及status = ClsEnums.StatusSetting.YssBrow情况下 标的证券设置为只读
                if (this.cboSecVar.Value == null || (this.cboSecVar.Value != null && this.cboSecVar.Value.Trim().Length == 0))
                {
                    this.cboSecCodeTag.YssReadOnly = true;
                }
                else
                {
                    this.cboSecCodeTag.YssReadOnly = status == ClsEnums.StatusSetting.YssBrow ? true : false;
                }


            }
            catch (Exception ex) 
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }
        }

        /// <summary>
        /// 当控件值改变时触发的事件，用于判断保证金比例是否大于0，大于0则固定保证金不可编辑.
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        private void txtNRate_TextChanged(object sender, EventArgs e)
        {
            try
            {
                if (this.status != ClsEnums.StatusSetting.YssBrow)
                {
                    if (Convert.ToDouble(this.txtNRate.Text.Trim().Length == 0 ? "0" : this.txtNRate.Text) > 0)
                    {
                        this.txtFvIr.YssReadOnly = true;
                        this.txtFvIr.YssIsMust = false;
                        this.txtFvIr.Text = "0";
                        ////this.txtBailFixed.Text = "";
                    }
                    else
                    {
                        this.txtFvIr.YssReadOnly = false;
                        this.txtFvIr.YssIsMust = true;
                        ////this.txtBailFixed.Text = "";
                    }
                }
            }
            catch (Exception ye)
            {
                ClsBaseException.DiscardException(ye);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, ClsEnums.StatusSetting.YssSave));
            }
        }

        /// <summary>
        /// 当控件值改变时触发的事件，用于判断保证金比例是否大于0，大于0则固定保证金不可编辑.
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        private void txtFvIr_TextChanged(object sender, EventArgs e)
        {
            try
            {
                if (this.status != ClsEnums.StatusSetting.YssBrow)
                {
                    if (Convert.ToDouble(this.txtFvIr.Text.Trim().Length == 0 ? "0" : this.txtFvIr.Text) > 0)
                    {
                        this.txtNRate.YssReadOnly = true;
                        this.txtNRate.YssIsMust = false;
                        this.txtNRate.Text = "0";
                        ////this.txtBailFixed.Text = "";
                    }
                    else
                    {
                        this.txtNRate.YssReadOnly = false;
                        this.txtNRate.YssIsMust = true;
                        ////this.txtBailFixed.Text = "";
                    }
                }
            }
            catch (Exception ye)
            {
                ClsBaseException.DiscardException(ye);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, ClsEnums.StatusSetting.YssSave));
            }

        }

        /// <summary>
        /// 通过标的证券加载交易币种
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboSecCodeTag_SelectedValueChanged(object sender, EventArgs e)
        {
            try
            {
                if (null != this.cboSecCodeTag.Value && null != this.cboSecVar.Value)
                {
                    if (this.cboSecVar.SelectedItem != null)
                    {
                        string secCode = ((SecVar)this.cboSecVar.SelectedItem.DataEntity).C_DA_CODE;
                        if (secCode.Contains("_") && secCode.Split('_')[1].Equals("WH"))
                        {
                            if (((YssSelCombox)sender).SelectedItem.DisplayValue == ((YssSelCombox)sender).Value)
                            {
                                CuryPair cury_Pair = ((YssSelCombox)sender).SelectedItem.DataEntity as CuryPair;
                                if (cury_Pair != null && cury_Pair.C_CURY_PAIR_CODE.Contains("/"))
                                {
                                    this.cboCury.Value = cury_Pair.C_CURY_PAIR_CODE.Split('/')[1];
                                }
                            }

                        }
                    }
                    else
                    {
                        if (((YssSelCombox)sender).SelectedItem != null)
                        {
                            // 当选择交易证券时，应取出交易证券中的币种信息和证券品种信息，并将他们赋值到对应的控件当中
                            SecBase secBase = ((YssSelCombox)sender).SelectedItem.DataEntity as SecBase;
                            if (secBase != null)
                            {
                                this.cboCury.Value = secBase.C_DC_CODE;
                            }
                        }

                    }
                }
                else
                {
                    this.cboCury.Value = null;
                    this.cboCury.Text = "";
                }
                


            }
            catch (Exception ye)
            {
                ClsBaseException.DiscardException(ye);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-110023", _formFun, status));
            }
        }

        /// <summary>
        /// load
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_BASE_YQ_S_Load(object sender, EventArgs e)
        {
            Type dataServiceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            service = (ISecBaseYqService)ServiceFactory.createService(dataServiceType);
            dataService = service;
            //// 通过下面的类统一实现证券内码的调整处理 byleeyu 20130426
            YssSecInformation.Support.Func.ClsAdjustSecCode clsAdjustSec = new YssSecInformation.Support.Func.ClsAdjustSecCode(ref txtSecCode, ref cboMkt, ref txtSecMktCode, ref cboDvVarDur, this);

            //// bug 81424 By Jinghehe 2013-10-17 在远期品种没有数据的以及status = ClsEnums.StatusSetting.YssBrow情况下 标的证券设置为只读
            if (this.cboSecVar.Value == null || (this.cboSecVar.Value != null && this.cboSecVar.Value.Trim().Length == 0))
            {
                this.cboSecCodeTag.YssReadOnly = true;
            }
            else
            {
                this.cboSecCodeTag.YssReadOnly = status == ClsEnums.StatusSetting.YssBrow ? true : false;
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
            if (result && status == ClsEnums.StatusSetting.YssEdit && !(this.frmBaseViewList.yssGetSelTypeItemMVC() as SecBase).C_SEC_VAR_CODE.Equals(this.cboSecVar.Value))
            {
                ClsSecTypeTip secTypeTip = new ClsSecTypeTip();
                result = secTypeTip.checkSecHold(this.txtSecCode.Text);
            }

            return result;
        }

    }
}




