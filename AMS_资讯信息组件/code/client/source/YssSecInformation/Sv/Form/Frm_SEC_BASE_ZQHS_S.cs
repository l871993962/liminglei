using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Communication.DataService;
using FAST.Common.Service.DataService.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;

using FAST.Core.Resource;


using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;
using System;




using System.Windows.Forms;

using System.Collections.Generic;
using System.Threading;
using System.Text.RegularExpressions;
using YssSecInformation.Support.Sv.Service;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Sv.Pojo;

namespace YssSecInformation.Sv.Form
{
    ///<summary>
    /// chenbo
    /// 2017-06-22
    /// #42948 资讯信息管理组件化拆分
    /// ------------------------------
    /// 功能简介：证券回售基本信息
    /// 创建版本：V4.5.0.1
    /// 创建人： caowei
    /// 创建日期： 2015-03-07
    /// </summary>
    public partial class Frm_SEC_BASE_ZQHS_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private ISecSoldBackService service;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_SEC_BASE_ZQHS_S()
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
            SecSoldBack sec = null;
            ////////如果起息日大于结息日，就报错
            ////if (ClsFunction.sub(this.dtpSoldBackBegin.getBeginDate, this.dtpSoldBackEnd.getBeginDate) > 0)
            ////{
            ////    ////throw new ClsBaseException("债券起息日应该小于债券结息日");
            ////    ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("001", _formFun, ClsEnums.StatusSetting.YssSave));
            ////    TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
            ////    transferErrorMessage.MESSAGESLINK = new List<string>();
            ////    /* 组装提示信息对象ErrorMessage */
            ////    string errorMess = ClsRetInfoDealer.getExtWarns("001", _formFun, ClsEnums.StatusSetting.YssSave);
            ////    transferErrorMessage.MESSAGESLINK.Add(errorMess);
            ////    throw new TransferException(JsonUtil.toJson(transferErrorMessage));
            ////}

            ////if (ClsFunction.sub(this.dtpIssueDate.getBeginDate, this.dtpExpireDate.getBeginDate) > 0)
            ////{
            ////    ////throw new ClsBaseException("债券发行日应该小于到期日");
            ////    ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("002", _formFun, ClsEnums.StatusSetting.YssSave));
            ////    TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
            ////    transferErrorMessage.MESSAGESLINK = new List<string>();
            ////    /* 组装提示信息对象ErrorMessage */
            ////    string errorMess = ClsRetInfoDealer.getExtWarns("002", _formFun, ClsEnums.StatusSetting.YssSave);
            ////    transferErrorMessage.MESSAGESLINK.Add(errorMess);
            ////    throw new TransferException(JsonUtil.toJson(transferErrorMessage));
            ////}

            ////if (ClsFunction.sub(this.dtpIssueDate.getBeginDate, this.dtpSoldBackBegin.getBeginDate) > 0)
            ////{
            ////    ////throw new ClsBaseException("债券发行日应该小于起息日");
            ////    ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("003", _formFun, ClsEnums.StatusSetting.YssSave));
            ////    TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
            ////    transferErrorMessage.MESSAGESLINK = new List<string>();
            ////    /* 组装提示信息对象ErrorMessage */
            ////    string errorMess = ClsRetInfoDealer.getExtWarns("003", _formFun, ClsEnums.StatusSetting.YssSave);
            ////    transferErrorMessage.MESSAGESLINK.Add(errorMess);
            ////    throw new TransferException(JsonUtil.toJson(transferErrorMessage));
            ////}

            ////if (ClsFunction.sub(this.dtpSoldBackEnd.getBeginDate, this.dtpExpireDate.getBeginDate) > 0)
            ////{
            ////    ////throw new ClsBaseException("债券结息日应该小于到期日");
            ////    ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("004", _formFun, ClsEnums.StatusSetting.YssSave));
            ////    TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
            ////    transferErrorMessage.MESSAGESLINK = new List<string>();
            ////    /* 组装提示信息对象ErrorMessage */
            ////    string errorMess = ClsRetInfoDealer.getExtWarns("004", _formFun, ClsEnums.StatusSetting.YssSave);
            ////    transferErrorMessage.MESSAGESLINK.Add(errorMess);
            ////    throw new TransferException(JsonUtil.toJson(transferErrorMessage));
            ////}

            try
            {
                sec = new SecSoldBack();
                sec.C_SEC_CODE = this.cboSec.Value;             
                ISecBaseInfoDataService infoSvc = DataServiceFactory.createService<ISecBaseInfoDataService>();
                SecBase secInfo = infoSvc.getDataByCode(string.IsNullOrEmpty(this.cboSec.Value) ? "" : this.cboSec.Value) as SecBase;
                ////edit by gongyue 20170427  secInfo为null时走下去会报错，所以增加判断
                if (secInfo != null)
                {
                    sec.C_SEC_MKT_CODE = secInfo.C_SEC_MKT_CODE;
                    sec.C_MKT_CODE = secInfo.C_MKT_CODE;
                    sec.N_SOLDBACK_PRICE = this.txtSoldBackPrice.Text;
                    sec.D_SOLDBACK_BEGIN = this.dtpSoldBackBegin.getBeginDateStr;
                    sec.D_SOLDBACK_END = this.dtpSoldBackEnd.getBeginDateStr;
                    sec.D_FINAL = this.dtpMoneyFinal.getBeginDateStr;
                }
               
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
                SecSoldBack sec = (SecSoldBack)yssGetBaseSelTypeItemMVC();

                this.cboSec.Value = sec.C_SEC_CODE;  // 证券内码
                this.txtSoldBackPrice.Text = sec.N_SOLDBACK_PRICE;    // 回售价格          
                if (!string.IsNullOrEmpty(sec.D_SOLDBACK_BEGIN))
                {
                    this.dtpSoldBackBegin.setDateTime(Convert.ToDateTime(sec.D_SOLDBACK_BEGIN)); // 回售开始日期
                }

                if (!string.IsNullOrEmpty(sec.D_SOLDBACK_END))
                {
                    this.dtpSoldBackEnd.setDateTime(Convert.ToDateTime(sec.D_SOLDBACK_END)); // 回售结束日期
                }

                if (!string.IsNullOrEmpty(sec.D_FINAL))
                {
                    this.dtpMoneyFinal.setDateTime(Convert.ToDateTime(sec.D_FINAL)); // 资金到期日期
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
        private void Frm_SEC_BASE_ZQ_S_Load(object sender, EventArgs e)
        {
            Type dataServiceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            service = (ISecSoldBackService)ServiceFactory.createService(dataServiceType);
            dataService = service;
            //////// 通过下面的类统一实现证券内码的调整处理 byleeyu 20130426
            ////YssPara.Func.Sv.ClsAdjustSecCode clsAdjustSec = new YssPara.Func.Sv.ClsAdjustSecCode(ref txtSecCode, ref cboMarket, ref txtSecMarketCode, this);
        }

    }
}


