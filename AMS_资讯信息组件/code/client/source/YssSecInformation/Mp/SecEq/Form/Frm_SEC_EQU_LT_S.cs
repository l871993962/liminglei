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
using System.Collections.Generic;
using System.Collections;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
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
    /// ---------------------------------
    /// Frm_SEC_EQU_LT_S 的摘要说明。
    /// 作用：本类是为了实现证券流通权益信息浏览与设置
    ///  
    ///  作者：chenyoulong
    ///  
    ///  版本：v4.5.0.1
    ///  
    ///  添加内容：窗体绘制，功能方法实现
    ///  
    ///  添加日期：2010.11.29
    ///  作者：lyh 
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
    public partial class Frm_SEC_EQU_LT_S : FrmBaseSet
    {
        /// <summary>
        /// 权益信息数据服务对象
        /// </summary>
        private ISecLtService secLtService = null;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_SEC_EQU_LT_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 初始化时间控件的时间
        /// </summary>
        ////public override void yssInitCtlAttr()
        ////{
        ////    this.dtpCirculateDate.setDateTime(DateTime.Now);
        ////}

        /// <summary>
        /// 封装界面元素为pojo对象
        /// </summary>
        /// <returns>由界面元素组成的对象</returns>
        public override ClsBasePojo yssFaceInfoToObj()
        {
            Cls_SEC_EQU clsSecCirculate = null;
            I_SecBase sec = cboSecurity.SelectedItem.DataEntity as I_SecBase;
            try
            {
                clsSecCirculate = new Cls_SEC_EQU();

                // 判断list选中界面是否有选中数据，在修改时获取原数据的值
                if (null != this.yssGetBaseSelTypeItem())
                {
                    clsSecCirculate.OldID = ((Cls_SEC_EQU)this.yssGetBaseSelTypeItem()).ID_D_MP_SEC_EQU;
                    clsSecCirculate.ID_D_MP_SEC_EQU = ((Cls_SEC_EQU)this.yssGetBaseSelTypeItem()).ID_D_MP_SEC_EQU;
                }

                clsSecCirculate.C_DTA_CODE = this.cboCirculateType.Value;
                clsSecCirculate.C_DV_CODE = this.cboIssueStyle.Value;

                clsSecCirculate.C_SEC_CODE = this.cboSecurity.Value;
                ////clsSecCirculate.C_DS_CODE = Convert.ToString(this.cboCirculateType.Value);
                ////clsSecCirculate.C_DV_CODE = Convert.ToString(this.cboIssueStyle.Value);
                clsSecCirculate.D_EXR = this.dtpCirculateDate.getBeginDate.ToString("yyyy-MM-dd");
                ////clsSecCirculate.C_DV_VAR_DUR = this.cboTimeLimit.Value == null ? " " : this.cboTimeLimit.Value;
                clsSecCirculate.C_MKT_CODE = sec.C_MKT_CODE;
                clsSecCirculate.C_EQU_CLS = "LT";
                clsSecCirculate.C_DATA_IDF = "H";

            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return clsSecCirculate;
        }

        /////// <summary>
        /////// 发行方式值改变事件
        /////// </summary>
        /////// <param name="sender">sender</param>
        /////// <param name="e">e</param>
        ////private void cboIssueStyle_SelectedValueChanged(object sender, EventArgs e)
        ////{
        ////    try
        ////    {
        ////        this.setCboTimeLimitState();
        ////    }
        ////    catch (Exception ye)
        ////    {
        ////        ////YssMessageBox.ShowDyanInformation("交易方式下拉框值处理时间出错", ye.Message, "错误信息", MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
        ////        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtError("001", _formFun, status));
        ////        ClsBaseException.DiscardException(ye);
        ////    }
        ////}

        /////// <summary>
        /////// 刷新窗体状态,解决在SET界面点击修改/复制时控件状态随值改变的问题
        /////// </summary>
        ////public override void initControlStat()
        ////{
        ////    base.initControlStat();
        ////    this.setCboTimeLimitState();
        ////}

        /////// <summary>
        /////// 设置锁定期限的控件状态
        /////// </summary>
        ////private void setCboTimeLimitState()
        ////{
        ////    if (this.status != ClsEnums.StatusSetting.YssBrow)
        ////    {
        ////        // 当【发行方式】为"网下_非公开"时
        ////        if (this.cboIssueStyle.Value != null && this.cboIssueStyle.Value.Equals("OFF_LINE_PRV"))
        ////        {
        ////            this.cboTimeLimit.KeepDesignValue = false;
        ////            this.cboTimeLimit.YssReadOnly = false;
        ////            this.cboTimeLimit.YssIsMust = true;
        ////        }
        ////        else
        ////        {
        ////            this.cboTimeLimit.Text = "";
        ////            this.cboTimeLimit.KeepDesignValue = true;
        ////            this.cboTimeLimit.YssReadOnly = true;
        ////            this.cboTimeLimit.YssIsMust = false;
        ////        }
        ////    }
        ////}

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
                    this.cboSecurity.YssReadOnly = true;
                    this.cboSecurity.Value = (frmDetailData.MainDataPojo as SecBase).C_SEC_CODE;
                }
            }
        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_EQU_LT_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.secLtService = ServiceFactory.createService(serviceType) as ISecLtService;
            this.dataService = this.secLtService;
        }

        /// <summary>
        /// 封装窗体数据到对象
        /// </summary>
        /// <returns>Pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            SecEqu clsSecCirculate = null;
            SecBase sec = cboSecurity.SelectedItem.DataEntity as SecBase;
            try
            {
                clsSecCirculate = new SecEqu();

                clsSecCirculate.C_DTA_CODE = this.cboCirculateType.Value;
                clsSecCirculate.C_DV_CODE = this.cboIssueStyle.Value;

                clsSecCirculate.C_SEC_CODE = this.cboSecurity.Value;
                clsSecCirculate.D_EXR = this.dtpCirculateDate.getBeginDate.ToString("yyyy-MM-dd");
                ////clsSecCirculate.C_DV_VAR_DUR = this.cboTimeLimit.Value == null ? " " : this.cboTimeLimit.Value;
                clsSecCirculate.C_MKT_CODE = sec.C_MKT_CODE;
                clsSecCirculate.C_EQU_CLS = "LT";
                clsSecCirculate.C_DATA_IDF = "H";

            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return clsSecCirculate;
        }

        /// <summary>
        /// 展示对象属性到窗体
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                SecEqu clsSecCirculate = (SecEqu)this.yssGetBaseSelTypeItemMVC();  // 从基类缓存中获取数据
                if (clsSecCirculate == null)
                {
                    return;
                }

                this.cboSecurity.Value = clsSecCirculate.C_SEC_CODE;
                this.cboCirculateType.Value = clsSecCirculate.C_DTA_CODE;
                this.cboIssueStyle.Value = clsSecCirculate.C_DV_CODE;
                this.dtpCirculateDate.setDateTime(Convert.ToDateTime(clsSecCirculate.D_EXR));
                ////this.cboTimeLimit.Value = clsSecCirculate.C_DV_VAR_DUR;
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// chenzhong 2011-04-29 bug1834
        /// </summary>
        /// <param name="sender">e</param>
        /// <param name="e">e</param>
        ////private void Frm_SEC_EQU_LT_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        ////{
        ////    try
        ////    {
        ////        if (this.status != ClsEnums.StatusSetting.YssEdit)
        ////        {
        ////            if (this.cboCirculateType.Text == null || "".Equals(this.cboCirculateType.Text))
        ////            {
        ////                //// YssMessageBox.ShowDyanInformation("交易属性不能为空", "交易属性不能为空", MessageBoxIcon.Information, FAST.Core.Context.ClsConstant.ButtonType.Detail);
        ////                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, ClsEnums.StatusSetting.YssSave));
        ////                this.cboCirculateType.Focus();
        ////                e.IsCancel = true;
        ////                return; // chenzhong 2011-04-29 
        ////            }

        ////            if (this.cboIssueStyle.Text == null || "".Equals(this.cboIssueStyle.Text))
        ////            {
        ////                ////YssMessageBox.ShowDyanInformation("交易方式不能为空", "交易方式不能为空", MessageBoxIcon.Information, FAST.Core.Context.ClsConstant.ButtonType.Detail);
        ////                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("002", _formFun, ClsEnums.StatusSetting.YssSave));
        ////                this.cboIssueStyle.Focus();
        ////                e.IsCancel = true; // 屏蔽保存方法
        ////                return;  // chenzhong 2011-04-29 
        ////            }

        ////            // 【交易证券】、【交易属性】、【发行方式】、【锁定期限】、【流通日期】
        ////            // 有数据返回YES，没有数据返回NO wuwenlan 20110331
        ////            string str = frmBaseViewList.DataAdmin.GetSpecValue(this.cboSecurity.Value + "\f\r" + this.dtpCirculateDate.getBeginDate.ToString("yyyy-MM-dd") + "\f\r" + this.cboCirculateType.Value + "\f\r" + this.cboIssueStyle.Value + "\f\r" + this.cboTimeLimit.Value, "checkData").ToString();
        ////            if (str.Equals("NO"))
        ////            {
        ////                e.IsCancel = false;  // 不屏蔽保存数据方法
        ////            }
        ////            else
        ////            {
        ////                // 错误数据提示 wuwenlan 20110331
        ////                YssMessageBox.ShowDyanInformation("保存数据错误！", "数据库已经存在交易证券的交易数据", MessageBoxIcon.Information, FAST.Core.Context.ClsConstant.ButtonType.Detail);
        ////            }
        ////        }
        ////    }
        ////    catch (Exception ex)
        ////    {
        ////        YssMessageBox.ShowDyanInformation("保存验证信息错误！", ex.Message, MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
        ////    }
        ////}
    }

}


