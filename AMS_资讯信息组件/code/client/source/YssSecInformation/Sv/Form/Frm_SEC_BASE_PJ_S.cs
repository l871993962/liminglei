﻿using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;

using FAST.Common.Service.Interface;
using FAST.Core.Exceptions;

using FAST.Core.Context;

using FAST.Core.Resource;


using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;

using FAST.Common.Service.Pojo.Base;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;






using FAST.Core.BaseControl;
using System.Collections;

using FAST.Core.Communication.DataService;
using YssSecInformation.Support.Sv.Service;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Func;



namespace YssSecInformation.Sv.Form
{
    /// <summary>
    /// 功能简介：票据基本信息设置，负责票据信息的增删改查等功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： Tangshifeng
    /// 创建日期： 2013.3.18
    /// </summary>
    public partial class Frm_SEC_BASE_PJ_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private ISecBasePjService service;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_SEC_BASE_PJ_S()
        {
            InitializeComponent();
            bUseMVCService = true;
        }

        /// <summary>
        ///  初始化set界面，并给各控件赋值.
        ///  Tangshifeng.
        ///  20130318.
        /// </summary>
        public override void yssInitCtlAttr()
        {
            try
            {
                ////dtpToList.setDateTime(DateTime.Now.Date); by Yuntao Lau
                dtpOffList.setDateTime(Convert.ToDateTime("9998-12-31"));

                if (this.status == ClsEnums.StatusSetting.YssAdd)
                {
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
                if (null != currSec && currSec.C_SEC_VAR_CODE != this.cboSecVar.Value)
                {
                    ISecBaseInfoDataService secBaseInfoDataService = DataServiceFactory.createService<ISecBaseInfoDataService>();
                    string isExistsStock = secBaseInfoDataService.isExistsStock(currSec.C_SEC_CODE);
                    if (isExistsStock == "true")
                    {
                        TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                        transferErrorMessage.MESSAGESLINK = new List<string>();
                        /* 组装提示信息对象ErrorMessage */
                        string errorMess = ClsRetInfoDealer.getExtWarns("003", _formFun, ClsEnums.StatusSetting.YssSave);
                        transferErrorMessage.MESSAGESLINK.Add(errorMess);
                        throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                    }
                }

                sec = new SecBase();

                sec.C_SEC_CODE = this.txtSecCode.Text;
                sec.C_SEC_ISIN_CODE = this.txtIsinCode.Text;
                sec.C_SEC_MKT_CODE = this.txtSecMktCode.Text;
                sec.C_SEC_NAME = this.txtSecName.Text;
                sec.C_SEC_VAR_CODE = this.cboSecVar.Value;
                sec.C_MKT_CODE = this.cboMkt.Value;
                sec.C_DC_CODE = this.cboCury.Value;
                ////sec.N_FV_ISSUE = this.txtFvIssue.Text;
                //// QDII优化赢时胜2013年12月06日01_B Modified By xzl
                sec.N_FV_ISSUE = Convert.ToDecimal(this.txtFvIssue.Text).ToString();
                sec.C_DV_QUT_MOD = this.cboMod.Value;
                sec.N_PRICE_ISSUE = this.txtPriceIssue.Text;
                sec.C_ORG_NAME = this.txtOrgName.Text;
                if (null != this.txtAmountHd.Text && !("").Equals(this.txtAmountHd.Text))
                {
                    sec.N_AMOUNT_HD = this.txtAmountHd.Text;
                }
                else 
                {
                    sec.N_AMOUNT_HD = "0";
                }

                sec.C_OPEN_ACC_NAME = this.txtOpenAccName.Text;
                sec.C_OPEN_ACC_NO = this.txtOperAccNo.Text;
                sec.C_SYS_CODE = this.cboSysCode.Value;
                sec.D_TO_LIST = this.dtpToList.getBeginDate;
                sec.D_OFF_LIST = this.dtpOffList.getBeginDate;
                sec.C_DV_ISSUE = this.cboIssueType.Value; // 发行类型
                sec.C_DV_ASSURE = this.cboAssureType.Value; // 担保方式

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
                    this.txtSecCode.Text = sec.C_SEC_CODE; // 证券内码
                    this.txtIsinCode.Text = sec.C_SEC_ISIN_CODE; // ISIN代码
                    this.txtSecMktCode.Text = sec.C_SEC_MKT_CODE; // 票据号码
                    this.txtSecName.Text = sec.C_SEC_NAME; // 票据名称
                    this.cboSecVar.Value = sec.C_SEC_VAR_CODE; // 证券品种
                    this.cboMkt.Value = sec.C_MKT_CODE; // 交易市场
                    this.cboCury.Value = sec.C_DC_CODE; // 交易币种
                    this.txtFvIssue.Text = sec.N_FV_ISSUE; // 票面金额
                    this.cboMod.Value = sec.C_DV_QUT_MOD; // 票据介质
                    this.txtPriceIssue.Text = sec.N_PRICE_ISSUE; // 加计天数
                    this.txtOrgName.Text = sec.C_ORG_NAME; // 出票人
                    // 拆分单位
                    if (!("0").Equals(sec.N_AMOUNT_HD)) 
                    {
                        this.txtAmountHd.Text = sec.N_AMOUNT_HD;
                    }
                    else
                    {
                        this.txtAmountHd.Text = "";
                    }

                    this.txtOpenAccName.Text = sec.C_OPEN_ACC_NAME; // 承兑人行名
                    this.txtOperAccNo.Text = sec.C_OPEN_ACC_NO; // 承兑人行号
                    this.cboSysCode.Text = sec.C_SYS_CODE; // 大额支付号
                    this.dtpToList.setDateTime(Convert.ToDateTime(sec.D_TO_LIST)); // 出票日期
                    this.dtpOffList.setDateTime(Convert.ToDateTime(sec.D_OFF_LIST)); // 到期日期
                    this.cboIssueType.Value = sec.C_DV_ISSUE; // 发行类型
                    this.cboAssureType.Value = sec.C_DV_ASSURE; // 担保方式
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }



        /// <summary>
        /// Load事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_BASE_PJ_S_Load(object sender, EventArgs e)
        {
            service = ServiceFactory.createService<ISecBasePjService>();
            dataService = service;
            //// 通过下面的类统一实现证券内码的调整处理 byleeyu 20130426
            ClsAdjustSecCode clsAdjustSec = new ClsAdjustSecCode(ref txtSecCode, ref cboMkt, ref txtSecMktCode, this);
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




