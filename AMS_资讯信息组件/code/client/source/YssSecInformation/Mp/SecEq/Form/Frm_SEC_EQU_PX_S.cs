////using YssBaseCls.Fun;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
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
using System.Drawing;
using System.Collections;
using System.ComponentModel;
using System.Windows.Forms;
using System.Text.RegularExpressions;
using Yss.KRichEx;
using FAST.Core.BaseControl;
using FAST.Common.Service.Services;
using YssSecInformation.Support.Mp.SecEq.Service;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Mp.SecEq.Pojo;
using FAST.Core.CRUD.Interface;
using YssSecInformation.Support.Sv.Pojo;

namespace YssSecInformation.Mp.SecEq.Form
{
    /// <summary>
    /// chenbo
    /// 2017-06-24
    /// #42948 资讯信息管理组件化拆分
    /// Frm_SEC_EQU_PX_S 的摘要说明。
    /// 作用：对价派息信息设置，负责对价派息信息的增删改等功能
    ///  
    ///  作者：xuqiji 
    ///  
    ///  版本：v4.5.0.1
    ///  
    ///  修改内容：窗体重新绘制，功能方法实现
    ///  
    ///  修改日期：2010.11.29
    ///  
    /// 作者：lyh 
    ///  
    ///  版本：v4.5.0.2
    ///  
    ///  修改内容：调整新需求
    ///  
    ///  修改日期：2011.3.2
    ///   －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         返回对象和控件的showInfo错误直接抛出异常
    /// </summary>
    public partial class Frm_SEC_EQU_PX_S : FrmBaseSet
    {
        /// <summary>
        /// 权益信息数据服务对象
        /// </summary>
        private ISecPxService secPxService = null;

        /// <summary>
        /// 根据交易证券获取交易市场
        /// </summary>
        private string mktCode = "";

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_SEC_EQU_PX_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
            ////this.dtpRegisterDate.setDateTime(DateTime.Now);
            ////this.dtpExRightDate.setDateTime(DateTime.Now);
            ////this.dtpPayDate.setDateTime(DateTime.Now);
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
                    this.btnSecurityCode.YssReadOnly = true;
                    this.btnSecurityCode.Value = (frmDetailData.MainDataPojo as SecBase).C_SEC_CODE;
                }
            }
        }


        /// <summary>
        ///  窗体load事件
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        private void FrmDividend_Load(object sender, System.EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.secPxService = ServiceFactory.createService(serviceType) as ISecPxService;
            this.dataService = this.secPxService;

            this.btnSecurityCode.Focus();
            this.ActiveControl = this.btnSecurityCode;

            this.txtDistPrice.YssReadOnly = true;
            this.txtDistPrice.KeepDesignValue = true;
            ////edit by HuangJin 2016.10.21 BUG #142938 港股通接口清算进来的派息信息和送配信息中配售价格 和支付比例不能显示数据
            ////this.txtDistPrice.Text = "0";
        }

       

        /// <summary>
        /// 封装界面元素为pojo对象
        /// </summary>
        /// <returns>由界面元素组成的对象</returns>
        public override ClsBasePojo yssFaceInfoToObj()
        {
            Cls_SEC_EQU dividend = null;
            try
            {
                dividend = new Cls_SEC_EQU();

                // 判断list选中界面是否有选中数据，在修改时获取原数据的值
                if (null != this.yssGetBaseSelTypeItem())
                {
                    dividend.OldID = ((Cls_SEC_EQU)this.yssGetBaseSelTypeItem()).ID_D_MP_SEC_EQU;
                    dividend.ID_D_MP_SEC_EQU = ((Cls_SEC_EQU)this.yssGetBaseSelTypeItem()).ID_D_MP_SEC_EQU;
                }

                dividend.C_SEC_CODE = this.btnSecurityCode.Value;
                dividend.C_DV_CODE = this.cboDividendType.Value;
                dividend.C_DS_CODE = this.cboInterestCategory.Value;
                dividend.C_DC_CODE = this.cboCurry.Value;
                dividend.D_REG = this.dtpRegisterDate.getBeginDate.ToString("yyyy-MM-dd");
                dividend.D_EXR = this.dtpExRightDate.getBeginDate.ToString("yyyy-MM-dd");
                dividend.D_FINAL = this.dtpPayDate.getBeginDate.ToString("yyyy-MM-dd");
                dividend.N_EQU_RATIO_PT = this.txtPreTaxRatio.Value;
                dividend.N_EQU_RATIO_AT = this.txtAfterTaxRatio.Value;
                dividend.C_MKT_CODE = this.mktCode;
                dividend.C_EQU_CLS = "DJ";
                dividend.C_DATA_IDF = "H";
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return dividend;
        }

        /// <summary>
        /// 展示对象属性到窗体
        /// </summary>
        /// <param name="pojo">Pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                SecEqu dividend = (SecEqu)this.yssGetBaseSelTypeItemMVC();   // 从基类缓存中获取数据
                if (dividend == null)
                {
                    return;
                }

                this.btnSecurityCode.Value = dividend.C_SEC_CODE;
                this.cboDividendType.Value = dividend.C_DV_CODE;
                this.cboInterestCategory.Value = dividend.C_DS_CODE;
                this.cboCurry.Value = dividend.C_DC_CODE;
                this.dtpRegisterDate.setDateTime(Convert.ToDateTime(dividend.D_REG));
                this.dtpExRightDate.setDateTime(Convert.ToDateTime(dividend.D_EXR));
                this.dtpPayDate.setDateTime(Convert.ToDateTime(dividend.D_FINAL));
                this.txtPreTaxRatio.Text = dividend.N_EQU_RATIO_PT;
                this.txtAfterTaxRatio.Text = dividend.N_EQU_RATIO_AT;

                //// By Jinghehe 2014-10-24 配售价格
                this.txtDistPrice.Text = dividend.N_PRICE_PLAC;  
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 封装窗体数据到对象
        /// </summary>
        /// <returns>Pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            SecEqu dividend = null;
            try
            {
                dividend = new SecEqu();

                dividend.C_SEC_CODE = this.btnSecurityCode.Value;
                dividend.C_DV_CODE = this.cboDividendType.Value;
                dividend.C_DS_CODE = this.cboInterestCategory.Value;
                dividend.C_DC_CODE = this.cboCurry.Value;
                dividend.D_REG = this.dtpRegisterDate.getBeginDate.ToString("yyyy-MM-dd");
                dividend.D_EXR = this.dtpExRightDate.getBeginDate.ToString("yyyy-MM-dd");
                dividend.D_FINAL = this.dtpPayDate.getBeginDate.ToString("yyyy-MM-dd");
                dividend.N_EQU_RATIO_PT = this.txtPreTaxRatio.Value;
                dividend.N_EQU_RATIO_AT = this.txtAfterTaxRatio.Value;
                dividend.C_MKT_CODE = this.mktCode;
                dividend.C_DATA_IDF = "H";   ////数据来源手动
                dividend.C_EQU_CLS = "DJ";

                //// By Jinghehe 2014-10-24 配售价格
                dividend.N_PRICE_PLAC = this.txtDistPrice.Text;  

            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return dividend;
        }

        /// <summary>
        /// 交易证券text改变事件
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        private void btnSecurityCode_SelectedValueChanged(object sender, EventArgs e)
        {
            try
           {
               if (null != this.btnSecurityCode.Value && this.btnSecurityCode.Value.Trim().Length != 0 && btnSecurityCode.SelectedItem != null)
                {
                    SecBase secBase = (SecBase)this.btnSecurityCode.SelectedItem.DataEntity;
                    this.cboCurry.Value = secBase.C_DC_CODE;
                    this.mktCode = secBase.C_MKT_CODE;
                }

                ////I_SecBase secBase = ((I_SecBase)((Hashtable)((YssSelCombox)sender).getKeyCollection())[((YssSelCombox)sender).Value]);
                ////this.cboCurry.Value = secBase.C_DC_CODE;
                ////this.mktCode = secBase.C_MKT_CODE;

            }
            catch (Exception ye)
            {
                ////YssMessageBox.ShowDyanInformation("交易证券文本改变事件出错", ye.Message, "提示信息", MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtError("001", _formFun, status));
                ClsBaseException.DiscardException(ye);
            }
        }

        /// <summary>
        /// 派息类型发生变化 对价派息会显示配售价格空间
        /// By Jinghehe 2014-10-28
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboInterestCategory_SelectedValueChanged(object sender, EventArgs e)
        {
            if (null != this.cboInterestCategory.Value
                && this.cboInterestCategory.Value.Equals("DJPX_FHPX"))
            {
                this.txtDistPrice.YssReadOnly = false;
                this.txtDistPrice.KeepDesignValue = false;
            }
            else
            {
                this.txtDistPrice.YssReadOnly = true;
                this.txtDistPrice.KeepDesignValue = true;
                this.txtDistPrice.Text = "0";
            }
        }

        #region oldCode

        /////// <summary>
        /////// 交易证券text改变事件
        /////// </summary>
        /////// <param name="sender">引发事件的对象</param>
        /////// <param name="e">事件类</param>
        ////private void btnSecurityCode_TextChanged(object sender, EventArgs e)
        ////{
        ////    try
        ////    {
        ////        // 根据证券信息中的币种代码来选择币种下拉框中的数据
        ////        I_SecBase secBase = ((I_SecBase)((Hashtable)((YssSecuritySel)sender).getKeyCollection())[((YssSecuritySel)sender).Value]);
        ////        this.cboCurry.Value = secBase.C_DC_CODE;
        ////        this.mktCode = secBase.C_MKT_CODE;

        ////    }
        ////    catch (Exception ye)
        ////    {
        ////        YssMessageBox.ShowDyanInformation("交易证券文本改变事件出错", ye.Message, "提示信息", MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
        ////    }
        ////}


        /// <summary>
        /// 保存操作前的事件处理  add by caozhonghu  2011.3.20
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        ////private void Frm_SEC_EQU_PX_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        ////{
        ////    validateDate();
        ////}


        /// <summary>
        /// 校验【登记日期】、【到账日期】、【除权日期】必输项   add by caozhonghu  2011.3.20
        /// </summary>
        ////private void validateDate()
        ////{
        ////    if (this.dtpRegisterDate.Text == "")
        ////    {
        ////        ////throw new ClsBaseException("登记日期不能为空！");
        ////        throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("001", _formFun, ClsEnums.StatusSetting.YssSave));
        ////    }

        ////    if (this.dtpPayDate.Text == "")
        ////    {
        ////        ////throw new ClsBaseException("到账日期不能为空！");
        ////        throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("002", _formFun, ClsEnums.StatusSetting.YssSave));
        ////    }

        ////    if (this.dtpExRightDate.Text == "")
        ////    {
        ////        ////throw new ClsBaseException("除权日期不能为空！");
        ////        throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("003", _formFun, ClsEnums.StatusSetting.YssSave));
        ////    }

        ////}
        #endregion
    }
}


