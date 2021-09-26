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
    /// 功能简介：股票基本信息设置，负责股票信息的增删改查等功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.15
    ///
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：zhuangyuchen
    /// 修改日期： 2011-2-18
    /// 修改简介：
    /// 1:增加回收站开启关闭机制
    /// 2:删除添加修改等操作的操作成功信息
    /// 3：添加注释
    /// 4：删除初始化加载下拉框的代码，改成在控件的属性中设置
    /// 6：修改出错的提示信息
    /// 7:去掉增删改查成功的提示信息，由基类来提供
    /// 8:修改了POJO类为公共类
    /// 9：增加传到后台去的列头和窗体菜单
    /// 10:增加公共表查询的分类查询的条件
    /// </summary>
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：yh
    /// 修改日期： 2011.02.24
    /// 修改简介： 调整代码为新的结构
    /// 当前版本：V4.5.0.4
    /// 修改人：wuwenlan
    /// 修改日期： 2011.03.2
    /// 修改简介： 实现对交易市场控件的控制
    ///            控件按需求进行调整控制
    ///             －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         返回对象错误直接抛出异常
    /// 
    public partial class Frm_SEC_BASE_GP_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private ISecBaseGpService service;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_SEC_BASE_GP_S()
        {
            InitializeComponent();
            dtpToList.setDateTime(Convert.ToDateTime("1900-1-1"));
            dtpOffList.setDateTime(Convert.ToDateTime("9998-12-31"));
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
                ////由于下拉列表更改，下拉列表数据在控件间中配置，删除在初始的时候配置分级列表

                ////如果set窗体的状态是新增状态，在点击新增之前事件中已经得到组合代码
                ////然后将赋值给set窗体窗体中的组合代码
                ////mkt = this.frmBaseViewList.getSelectedRowTagMVC(mkt) as Cls_MKT;
                ////if (mkt != null)
                ////{
                ////    if (!mkt.C_MKT_CODE.Equals("OTC") && !mkt.C_MKT_CODE.Equals("FTM"))
                ////    {
                ////        this.cboMkt.Value = mkt.C_MKT_CODE; // 交易市场代码
                ////    }
                ////    else
                ////    {
                ////        txtSecMktCode_TextChanged(null,null);
                ////    }
                ////}

                ////dtpToList.setDateTime(Convert.ToDateTime("1900-1-1"));

                if (this.status == ClsEnums.StatusSetting.YssAdd)
                {
                    this.txtSecCode.Text = "";
                    this.dtpOffList.setDateTime(Convert.ToDateTime("9998-12-31"));
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

                if (this.dtpToList.getBeginDate.CompareTo(this.dtpOffList.getBeginDate) >= 0)
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
                sec.C_SEC_CODE = this.txtSecCode.Text;
                sec.C_SEC_ISIN_CODE = this.txtIsinCode.Text;
                sec.C_SEC_MKT_CODE = this.txtSecMktCode.Text;
                sec.C_SEC_NAME = this.txtSecName.Text;
                sec.C_SEC_VAR_CODE = this.cboSecVar.Value;
                sec.C_MKT_CODE = this.cboMkt.Value;
                //// EDIT BY WANGTANGYAO 20160704 BUG #133637 增加非空判断 防止已设置投资方式的券不能重新修改为空。
                sec.C_DV_QUT_MOD = string.IsNullOrEmpty(this.cboInvMode.Value) ? "" : this.cboInvMode.Value; //// 投资方式 add by Yuntao Lau 2015.11.09 STORY #23426
                sec.C_DC_CODE = this.cboCury.Value;
                sec.N_FV_ISSUE = Convert.ToString(Convert.ToDecimal(this.txtFvIssue.Text));
                sec.N_AMOUNT_HD = Convert.ToString(Convert.ToDecimal(this.txtAmountHD.Text));
                sec.N_PRICE_FCR = Convert.ToString(Convert.ToDecimal(this.txtPriceFcr.Text));
                sec.D_TO_LIST = this.dtpToList.getBeginDate;
                sec.D_OFF_LIST = this.dtpOffList.getBeginDate;
                sec.C_SEC_NAME_CN = this.txtSecCnName.Text;
                //// added by HeLiang 2017-11-15 STORY #43829 新企业会计准则（Ifrs9）解决方案
                sec.C_FINANCE_TOOL= this.cboFinInstruments.Value == null ? " " : this.cboFinInstruments.Value;
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
                if (null != sec && (sec.C_SEC_VAR_CODE.StartsWith("GP") || !(sec.C_SEC_VAR_CODE.StartsWith("ZQ") || sec.C_SEC_VAR_CODE.StartsWith("QZ") || sec.C_SEC_VAR_CODE.StartsWith("CJ") || sec.C_SEC_VAR_CODE.StartsWith("JJ")
                    || sec.C_SEC_VAR_CODE.StartsWith("LC") || sec.C_SEC_VAR_CODE.StartsWith("QH") || sec.C_SEC_VAR_CODE.StartsWith("QQ") || sec.C_SEC_VAR_CODE.StartsWith("YQ")
                    || sec.C_SEC_VAR_CODE.StartsWith("HG") || sec.C_SEC_VAR_CODE.StartsWith("CK") || sec.C_SEC_VAR_CODE.StartsWith("LL") || sec.C_SEC_VAR_CODE.StartsWith("WH"))))
                {
                    this.txtSecCode.Text = sec.C_SEC_CODE; // 证券内码
                    this.txtIsinCode.Text = sec.C_SEC_ISIN_CODE; // ISIN代码
                    this.txtSecMktCode.Text = sec.C_SEC_MKT_CODE; // 上市代码
                    this.txtSecName.Text = sec.C_SEC_NAME; // 证券名称
                    this.cboSecVar.Value = sec.C_SEC_VAR_CODE; // 证券品种
                    this.cboMkt.Value = sec.C_MKT_CODE; // 交易市场
                    this.cboInvMode.Value = sec.C_DV_QUT_MOD; //// 投资方式 //// 投资方式 add by Yuntao Lau 2015.11.09 STORY #23426
                    this.cboCury.Value = sec.C_DC_CODE; // 交易币种
                    this.txtFvIssue.Text = sec.N_FV_ISSUE; // 发行面值
                    this.txtAmountHD.Text = sec.N_AMOUNT_HD; // 每手数量
                    this.txtPriceFcr.Text = sec.N_PRICE_FCR; // 报价因子
                    this.dtpToList.setDateTime(Convert.ToDateTime(sec.D_TO_LIST)); // 上市日期
                    this.dtpOffList.setDateTime(Convert.ToDateTime(sec.D_OFF_LIST)); // 退市日期
                    this.txtSecCnName.Text = sec.C_SEC_NAME_CN;
                    //// added by HeLiang 2017-11-15 STORY #43829 新企业会计准则（Ifrs9）解决方案
                    this.cboFinInstruments.Value = sec.C_FINANCE_TOOL;
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
        private void Frm_SEC_BASE_GP_S_Load(object sender, EventArgs e)
        {
            Type dataServiceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            service = (ISecBaseGpService)ServiceFactory.createService(dataServiceType);
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




