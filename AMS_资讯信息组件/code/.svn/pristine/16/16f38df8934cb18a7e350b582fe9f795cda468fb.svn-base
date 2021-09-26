using FAST.Core.Util;
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


using System.Threading;
using FAST.Common.Service.Services;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Mp.PreStock.Service;
using YssSecInformation.Support.Mp.PreStock.Pojo;
using FAST.Core.CRUD.Interface;

namespace YssSecInformation.Mp.PreStock.Form
{
    /// <summary>
    /// Frm_SEC_EQU_YXG_S 的摘要说明。
    /// 作用：本类是为了实现优先股计息信息浏览与设置
    ///  
    ///  作者：liyongjun
    ///  
    ///  版本：v4.6.4.7  
    ///  
    ///  添加日期：2010.12.07
    /// </summary>
    public partial class Frm_SEC_EQU_YXG_S : FrmBaseSet
    {
        /// <summary>
        /// 权益信息数据服务对象
        /// </summary>
        private IPreStockInterestService preStockInterestService = null;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_SEC_EQU_YXG_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 初始化界面控件
        /// </summary>
        public override void yssInitCtlAttr()
        {
            this.cboPriceMode.Value = "NET";
            this.cboPriceMode.Text = "净价";
            this.cboPriceMode.YssReadOnly = true;
        }

        /// <summary>
        /// 重写初始化状态
        /// </summary>
        public override void initControlStat()
        {
            base.initControlStat();
            this.cboPriceMode.Value = "NET";
            this.cboPriceMode.Text = "净价";
            this.cboPriceMode.YssReadOnly = true;

        }
        
        /// <summary>
        /// 封装界面元素为pojo对象
        /// </summary>
        /// <returns>由界面元素组成的对象</returns>
        public override AuditableParamPojo faceInfoToObjMVC()
        {
            PreStockInterest preStockInterest = null;
            SecBase secBase = cboHoldSecurity.SelectedItem.DataEntity as SecBase;

            try
            {
                checkDateInput();

                if (!secBase.C_SEC_VAR_CODE.Equals("GP_YXG") && !secBase.C_SEC_VAR_CODE.Equals("GP_GQ_YXG"))
                {
                    ////TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                    ////transferErrorMessage.MESSAGESLINK = new List<string>();
                    /* 组装提示信息对象ErrorMessage */
                    ////string errorMess = "证券品种为非优先股类型";
                    ////transferErrorMessage.MESSAGESLINK.Add(errorMess);
                    ////throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                    ////ClsBaseException.DiscardException(e);
                    ////YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("CL-PREI-000001", _formFun, status));
                    ////TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                    ////transferErrorMessage.MESSAGESLINK = new List<string>();
                    /////* 组装提示信息对象ErrorMessage */
                    ////string errorMess = ClsRetInfoDealer.getExtWarns("001", _formFun, ClsEnums.StatusSetting.YssSave);
                    ////transferErrorMessage.MESSAGESLINK.Add(errorMess);
                    ////throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("002", this._formFun, ClsEnums.StatusSetting.YssSave));
                }

                preStockInterest = new PreStockInterest();

                preStockInterest.C_SEC_CODE = this.cboHoldSecurity.Value;
                preStockInterest.C_SEC_CODE_TRG = this.cobDistSecurity.Value;
                preStockInterest.D_AI_BEGIN = this.dtpIssueDate.getBeginDate;
                preStockInterest.D_AI_END = this.dtpExpireDate.getBeginDate;
                //// modified by liyanjun 2016-9-27 BUG #141675 优先股业务测试问题汇总01
                preStockInterest.N_FV_IR = ClsFunction.toDecimal(this.txtTaxRate.Text);
                preStockInterest.N_RATE = ClsFunction.toDecimal(ClsFunction.div(this.txtCouponRate.Text, "100")); 
                preStockInterest.C_DV_AI_MOD = this.cboCountMode.Value;
                preStockInterest.C_DV_AI_EXPR = this.cboCountFormula.Value;
                preStockInterest.C_DV_VAR_DUR = this.cboPayFrequency.Value;
                preStockInterest.C_DV_PI_MOD = this.cboPayFormula.Value;
                preStockInterest.C_DV_ACCOUNT_CODE = this.cboBusType.Value;
                preStockInterest.C_DV_TOTAL_INCOME = this.cboisLjgx.Value;
                preStockInterest.C_DV_ATTEND_PROFIT = this.cboisCyfh.Value;
                preStockInterest.C_DV_EXCHGE_STOCK = this.cboIsZhptg.Value;
                ////检索hashtable里面是否有这个值 如没有检索到 InputErr
                if (cboHoldSecurity.SelectedItem != null)
                {
                    preStockInterest.C_MKT_CODE = (cboHoldSecurity.SelectedItem.DataEntity as SecBase).C_MKT_CODE;
                }
                
                preStockInterest.C_DV_QUT_MOD = this.cboPriceMode.Value;
                preStockInterest.C_DV_DELAY_PAY = this.cboIsYqzh.Value;
            }
            catch (Exception ye)
            {
                YssMessageBox.ShowCommonInfo(ye.Message);
            }

            return preStockInterest;
        }

        /// <summary>
        /// 获取list中选中记录，为界面元素赋值，显示数据
        /// </summary>
        public void yssShowInfo()
        {
            try
            {
                SecBase secBase = cboHoldSecurity.SelectedItem.DataEntity as SecBase;
                PreStockInterest preStockInterest = (PreStockInterest)this.yssGetBaseSelTypeItemMVC();  //// 从基类缓存中获取数据
                this.cboHoldSecurity.Value = preStockInterest.C_SEC_CODE;
                this.cobDistSecurity.Value = preStockInterest.C_SEC_CODE_TRG;
                this.dtpIssueDate.setDateTime(Convert.ToDateTime(preStockInterest.D_AI_BEGIN));
                this.dtpExpireDate.setDateTime(Convert.ToDateTime(preStockInterest.D_AI_BEGIN));
                this.txtTaxRate.Text = preStockInterest.N_FV_IR.ToString();
                this.txtCouponRate.Text = preStockInterest.N_RATE.ToString();
                this.cboCountMode.Value = preStockInterest.C_DV_AI_MOD;
                this.cboCountFormula.Value = preStockInterest.C_DV_AI_EXPR;
                this.cboPayFrequency.Value = preStockInterest.C_DV_VAR_DUR;
                this.cboPayFormula.Value = preStockInterest.C_DV_PI_MOD;
                this.cboBusType.Value = preStockInterest.C_DV_ACCOUNT_CODE;
                this.cboisLjgx.Value = preStockInterest.C_DV_TOTAL_INCOME;
                this.cboisCyfh.Value = preStockInterest.C_DV_ATTEND_PROFIT;
                this.cboIsZhptg.Value = preStockInterest.C_DV_EXCHGE_STOCK;
                this.cboPriceMode.Value = preStockInterest.C_DV_QUT_MOD;
                this.cboIsYqzh.Value = preStockInterest.C_DV_DELAY_PAY;
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 锁定特殊控件的状态
        /// </summary>
        protected override void OnLockSpecialControlState()
        {
            base.OnLockSpecialControlState();

            ////List界面被关联内嵌至其他界面时，组合不能被更改
            if (this.frmBaseViewList != null && this.frmBaseViewList is IFormDetailData)
            {
                IFormDetailData frmDetailData = this.frmBaseViewList as IFormDetailData;
                if (frmDetailData.MainDataPojo != null)
                {
                    this.cboHoldSecurity.YssReadOnly = true;
                    this.cboHoldSecurity.Value = (frmDetailData.MainDataPojo as SecBase).C_SEC_CODE;
                }
            }
        }

        /// <summary>
        /// 初始化服务
        /// </summary>
        protected override void initServiceMVC()
        {
            base.initServiceMVC();
            preStockInterestService = ServiceFactory.createService<IPreStockInterestService>();
            dataService = preStockInterestService;
        }

        /// <summary>
        /// 展示对象属性到窗体
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                PreStockInterest preStockInterest = (PreStockInterest)this.yssGetBaseSelTypeItemMVC(); // 从基类缓存中获取数据
                if (preStockInterest == null)
                {
                    return;
                }

                this.cboHoldSecurity.Value = preStockInterest.C_SEC_CODE;
                this.cobDistSecurity.Value = preStockInterest.C_SEC_CODE_TRG;
                this.dtpIssueDate.setDateTime(Convert.ToDateTime(preStockInterest.D_AI_BEGIN));
                this.dtpExpireDate.setDateTime(Convert.ToDateTime(preStockInterest.D_AI_END));
                //// modified by liyanjun 2016-9-27 BUG #141675 优先股业务测试问题汇总01
                this.txtTaxRate.Text = (preStockInterest.N_FV_IR).ToString();
                this.txtCouponRate.Text = (preStockInterest.N_RATE * 100).ToString();
                this.cboCountMode.Value = preStockInterest.C_DV_AI_MOD;
                this.cboCountFormula.Value = preStockInterest.C_DV_AI_EXPR;
                this.cboPayFrequency.Value = preStockInterest.C_DV_VAR_DUR;
                this.cboPayFormula.Value = preStockInterest.C_DV_PI_MOD;
                this.cboBusType.Value = preStockInterest.C_DV_ACCOUNT_CODE;
                this.cboisLjgx.Value = preStockInterest.C_DV_TOTAL_INCOME;
                this.cboisCyfh.Value = preStockInterest.C_DV_ATTEND_PROFIT;
                this.cboIsZhptg.Value = preStockInterest.C_DV_EXCHGE_STOCK;
                this.cboPriceMode.Value = preStockInterest.C_DV_QUT_MOD;
                this.cboIsYqzh.Value = preStockInterest.C_DV_DELAY_PAY;

                this.cboPriceMode.YssReadOnly = true;
                this.cboPriceMode.Value = "NET";
                this.cboPriceMode.Text = "净价";
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 验证时间控件输入日期的合法性
        /// </summary>
        public void checkDateInput()
        {
            if (ClsFunction.sub(this.dtpIssueDate.getBeginDate, this.dtpExpireDate.getBeginDate) > 0)
            {
                TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                transferErrorMessage.MESSAGESLINK = new List<string>();
                /* 组装提示信息对象ErrorMessage */
                string errorMess = ClsRetInfoDealer.getExtWarns("001", _formFun, ClsEnums.StatusSetting.YssSave);
                transferErrorMessage.MESSAGESLINK.Add(errorMess);
                throw new TransferException(JsonUtil.toJson(transferErrorMessage));
            }
        }

        /// <summary>
        /// 重写保存按钮点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnSave_Click(object sender, EventArgs e)
        {
            base.btnSave_Click(sender, e);
            if (this._formFun.N_CHECK == 0)
            {
                //// add by liyanjun 2016-9-27 BUG #141675 优先股业务测试问题汇总01
                PreStockInterest pre = (PreStockInterest)this.yssGetBaseSelTypeItemMVC();
                if (pre != null && !pre.C_DV_ACCOUNT_CODE.Equals("HSLX_JRFZ"))
                {
                    return;
                }

                ClsRetInfo info = ClsRetInfoDealer.getCommonHint("HNT-000024", _formFun, status);
                info.setSpecStr("C_SEC_CODE", this.cboHoldSecurity.Text);
                if (YssMessageBox.ShowCommonInfo(info) == DialogResult.Yes)
                {
                    //// 开线程初始化优先股
                    Thread bondInitThread = new System.Threading.Thread(delegate()
                    {
                        bondInitProcess();
                    });

                    bondInitThread.SetApartmentState(ApartmentState.STA);

                    bondInitThread.Start();
                }
                else
                {
                    YssMessageBox.currentForm = this;
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtInfo("014", _formFun, ClsEnums.StatusSetting.YssSave));
                }
            }
        }

        /// <summary>
        /// 复写审核按钮 弹出对应是否重新初始化付息及每百元利息确认提示信息
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnAudit_Click(object sender, EventArgs e)
        {
            List<PreStockInterest> preList = new List<PreStockInterest>();
            IPreStockInterestService iPreStockInterestService = (IPreStockInterestService)dataService;
            PreStockInterest pre = (PreStockInterest)this.yssGetBaseSelTypeItemMVC();
            if (pre != null && pre.C_DV_ACCOUNT_CODE.Equals("HSLX_JRFZ"))
            {
                preList.Add(pre);
            }

            base.btnAudit_Click(sender, e);
            if (preList.Count > 0)
            {
                iPreStockInterestService.multiplePreInitFi(preList);
                YssMessageBox.currentForm = this;
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtInfo("011", _formFun, ClsEnums.StatusSetting.YssSave));
            }
        }

        /// <summary>
        /// 优先股初始化线程
        /// added by xzl
        /// </summary>
        private void bondInitProcess()
        {
            Type dataServiceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            preStockInterestService = (IPreStockInterestService)ServiceFactory.createService(dataServiceType);
            PreStockInterest pre = (PreStockInterest)this.yssGetBaseSelTypeItemMVC();
            if (pre != null && pre.C_DV_ACCOUNT_CODE.Equals("HSLX_JRFZ"))
            {
                YssMessageBox.currentForm = this;
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtInfo("012", _formFun, ClsEnums.StatusSetting.YssBrow));
                preStockInterestService.singleSecInitFi(pre);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtInfo("013", _formFun, ClsEnums.StatusSetting.YssBrow));
            }
        }

        /// <summary>
        /// cboBusType值改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboBusType_SelectedValueChanged(object sender, EventArgs e)
        {
            try
            {
               if (this.status != ClsEnums.StatusSetting.YssBrow)
                {
                    // 判断选择的核算类型为权益工具的时候
                    if (null != this.cboBusType.Value && (this.cboBusType.Value.Equals("HSLX_QYGJ")))
                    {
                        this.txtTaxRate.YssReadOnly = true;
                        this.txtTaxRate.YssIsMust = true;
                        this.txtTaxRate.Enabled = true;
                        this.txtCouponRate.YssReadOnly = true;
                        this.txtCouponRate.YssIsMust = true;
                        this.txtCouponRate.Enabled = true;
                        this.cboCountMode.YssReadOnly = true;
                        this.cboCountMode.YssIsMust = false;
                        this.cboCountFormula.YssReadOnly = true;
                        this.cboCountFormula.YssIsMust = false;
                        this.cboPayFrequency.YssReadOnly = true;
                        this.cboPayFrequency.YssIsMust = false;
                        this.cboPayFormula.YssReadOnly = true;
                        this.cboPayFormula.YssIsMust = false;
                        this.dtpIssueDate.Enabled = false;
                        this.dtpExpireDate.Enabled = false;
                        this.cboPriceMode.YssReadOnly = true;
                        this.cboPriceMode.YssIsMust = false;
                        this.cboIsYqzh.YssReadOnly = true;
                        this.cboIsYqzh.YssIsMust = false;
                    }
                    else
                    {
                        this.txtTaxRate.YssReadOnly = false;
                        this.txtTaxRate.YssIsMust = true;
                        this.txtCouponRate.YssReadOnly = false;
                        this.txtCouponRate.YssIsMust = true;
                        this.cboCountMode.YssReadOnly = false;
                        this.cboCountMode.YssIsMust = true;
                        this.cboCountFormula.YssReadOnly = false;
                        this.cboCountFormula.YssIsMust = true;
                        this.cboPayFrequency.YssReadOnly = false;
                        this.cboPayFrequency.YssIsMust = true;
                        this.cboPayFormula.YssReadOnly = false;
                        this.cboPayFormula.YssIsMust = true;
                        this.dtpIssueDate.Enabled = true;
                        this.dtpExpireDate.Enabled = true;
                        this.cboPriceMode.YssReadOnly = false;
                        this.cboPriceMode.YssIsMust = true;
                        this.cboIsYqzh.YssReadOnly = false;
                        this.cboIsYqzh.YssIsMust = true;
                    }
                }

                this.tbMain.Refresh();
            }
            catch (Exception)
            {
                TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                transferErrorMessage.MESSAGESLINK = new List<string>();
                /* 组装提示信息对象ErrorMessage */
                string errorMess = ClsRetInfoDealer.getExtWarns("001", _formFun, ClsEnums.StatusSetting.YssSave);
                transferErrorMessage.MESSAGESLINK.Add(errorMess);
                throw new TransferException(JsonUtil.toJson(transferErrorMessage));
            }
        }
    }
}


