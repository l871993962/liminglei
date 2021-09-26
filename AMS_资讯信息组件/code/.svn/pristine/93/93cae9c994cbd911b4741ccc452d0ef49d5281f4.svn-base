using FAST.Core.Util;
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
    /// -------------------------------------
    /// 功能简介：证券发行信息设置，负责证券发行信息的增删改查等功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.04
    ///
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：wuwenlan
    /// 修改日期：20101208
    /// 修改简介：对set窗体的新建，修改，审核反审核方法的实现
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         返回对象和控件的showInfo错误直接抛出异常
    /// </summary>
    public partial class Frm_SEC_EQU_FX_S : FrmBaseSet
    {
        /// <summary>
        /// 权益信息数据服务对象
        /// </summary>
        private ISecFxService secFxService = null;

        /// <summary>
        /// 根据交易证券获取交易市场
        /// </summary>
        private string mktCode = "";

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_SEC_EQU_FX_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
        }


        /// <summary>
        /// 初始化界面控件
        /// </summary>
        ////public override void yssInitCtlAttr()
        ////{
        ////    try
        ////    {
        ////        this.dtpIssueDate.setDateTime(DateTime.Now);
        ////    }
        ////    catch (ClsBaseException e)
        ////    {
        ////        throw new ClsBaseException(e.Message, e);
        ////    }
        ////}

        /// <summary>
        /// 封装界面元素为pojo对象
        /// </summary>
        /// <returns>由界面元素组成的对象</returns>
        public override ClsBasePojo yssFaceInfoToObj()
        {
            Cls_SEC_EQU clsSecurityIssue = null;
            try
            {
                clsSecurityIssue = new Cls_SEC_EQU();

                // 判断list选中界面是否有选中数据，在修改时获取原数据的值
                if (null != this.yssGetBaseSelTypeItem())
                {
                    clsSecurityIssue.OldID = ((Cls_SEC_EQU)this.yssGetBaseSelTypeItem()).ID_D_MP_SEC_EQU;
                    clsSecurityIssue.ID_D_MP_SEC_EQU = ((Cls_SEC_EQU)this.yssGetBaseSelTypeItem()).ID_D_MP_SEC_EQU;
                }

                clsSecurityIssue.C_SEC_CODE = this.cboSecurity.Value;
                clsSecurityIssue.D_REG = this.dtpIssueDate.getBeginDateStr;
                clsSecurityIssue.N_PRICE_PLAC = this.txtIssuePrice.Text;
                clsSecurityIssue.C_SEC_CODE_TAG = this.txtIssueCode.Text;
                clsSecurityIssue.C_MKT_CODE = this.mktCode;
                clsSecurityIssue.C_EQU_CLS = "FX";
                clsSecurityIssue.C_DATA_IDF = "H";
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return clsSecurityIssue;
        }

        /// <summary>
        /// 【交易证券】文本值改变时的事件处理   add by caozhonghu 2011.3.20
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        ////private void cboSecurity_TextChanged(object sender, EventArgs e)
        ////{
        ////    if (cboSecurity.Value != null)
        ////    {
        ////        I_SecBase sec = cboSecurity.getKeyCollection()[cboSecurity.Value] as I_SecBase;
        ////        ////交易市场
        ////        this.mktCode = sec.C_MKT_CODE;
        ////        //// 债券品种
        ////        if (sec.C_DA_CODE.StartsWith("ZQ"))
        ////        {
        ////            txtIssuePrice.Text = "100";
        ////        }
        ////        else if (sec.C_DA_CODE.StartsWith("GP"))
        ////        {
        ////            txtIssuePrice.Text = "0";    // 股票品种
        ////        }
        ////    }
        ////}

        /// <summary>
        /// 【交易证券】文本值改变时的事件处理   
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        private void cboSecurity_SelectedValueChanged(object sender, EventArgs e)
        {
            if (cboSecurity.Value != null)
            {
                SecBase sec = cboSecurity.SelectedItem.DataEntity as SecBase;
                ////交易市场
                this.mktCode = sec.C_MKT_CODE;
                //// 债券品种
                if (sec.C_DA_CODE.StartsWith("ZQ"))
                {
                    txtIssuePrice.Text = "100";
                }
                else if (sec.C_DA_CODE.StartsWith("GP"))
                {
                    txtIssuePrice.Text = "0";    // 股票品种
                }
            }
        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_EQU_FX_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.secFxService = ServiceFactory.createService(serviceType) as ISecFxService;
            this.dataService = this.secFxService;
        }

        /// <summary>
        /// 展示对象属性到窗体
        /// </summary>
        /// <param name="pojo">Pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                SecEqu clsSecurityIssue = (SecEqu)this.yssGetBaseSelTypeItemMVC(); 
                if (clsSecurityIssue == null)
                {
                    return;
                }

                this.cboSecurity.Value = clsSecurityIssue.C_SEC_CODE;                                  // liuping 2011-03-29     BUG #1516 证券发行
                this.txtIssueCode.Text = clsSecurityIssue.C_SEC_CODE_TAG;
                this.dtpIssueDate.setDateTime(Convert.ToDateTime(clsSecurityIssue.D_REG));
                this.txtIssuePrice.Text = clsSecurityIssue.N_PRICE_PLAC;
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
            SecEqu clsSecurityIssue = null;
            try
            {
                clsSecurityIssue = new SecEqu();

                clsSecurityIssue.C_SEC_CODE = this.cboSecurity.Value;
                clsSecurityIssue.D_REG = this.dtpIssueDate.getBeginDateStr;
                clsSecurityIssue.N_PRICE_PLAC = this.txtIssuePrice.Value;
                clsSecurityIssue.C_SEC_CODE_TAG = this.txtIssueCode.Text;
                clsSecurityIssue.C_MKT_CODE = this.mktCode;
                clsSecurityIssue.C_EQU_CLS = "FX";
                clsSecurityIssue.C_DATA_IDF = "H";
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return clsSecurityIssue;
        }

        /// <summary>
        /// 保存数据之前判断这条数据是否已经存在
        /// wuwenlan 20110331
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        /// chenzhong 2011-04-29 
        ////private void Frm_SEC_EQU_FX_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        ////{
        ////    try
        ////    {
        ////        if (this.cboSecurity.Value != null && this.status != ClsEnums.StatusSetting.YssEdit)
        ////        {
        ////            // 交易证券+发行日期+发行代码
        ////            // 有数据返回YES，没有数据返回NO wuwenlan 20110331
        ////            if (this.txtIssueCode.Text == null || "".Equals(this.txtIssueCode.Text))
        ////            {
        ////                ////YssMessageBox.ShowDyanInformation("发行代码不能为空", "发行代码不能为空不能为空", MessageBoxIcon.Information, FAST.Core.Context.ClsConstant.ButtonType.Detail);
        ////                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, ClsEnums.StatusSetting.YssSave));
        ////                this.txtIssueCode.Focus();
        ////                e.IsCancel = true;
        ////                return; // chenzhong 2011-04-29 
        ////            }

        ////            if (this.txtIssuePrice.Text == null || "".Equals(this.txtIssuePrice.Text))
        ////            {
        ////                ////YssMessageBox.ShowDyanInformation("发行价格不能为空", "发行价格不能为空不能为空", MessageBoxIcon.Information, FAST.Core.Context.ClsConstant.ButtonType.Detail);
        ////                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("002", _formFun, ClsEnums.StatusSetting.YssSave));
        ////                this.txtIssuePrice.Focus();
        ////                e.IsCancel = true; // 屏蔽保存方法
        ////                return; // chenzhong 2011-04-29 
        ////            }

        ////            ////string str = frmBaseViewList.DataAdmin.GetSpecValue(this.cboSecurity.Value + "\f\r" + this.dtpIssueDate.getBeginDateStr + "\f\r" + this.txtIssueCode.Text, "checkData").ToString();
        ////            string str = "YES";
        ////            if (str.Equals("NO"))
        ////            {
        ////                e.IsCancel = false;  // 不屏蔽保存数据方法
        ////            }
        ////            else
        ////            {
        ////                // 错误数据提示 wuwenlan 20110331
        ////                ////YssMessageBox.ShowDyanInformation("保存数据错误！", "数据库已经存在交易证券【" + this.cboSecurity.Value + "】发行日期【" + this.dtpIssueDate.getBeginDateStr + "】发行代码【" + this.txtIssueCode.Text + "】的交易数据", MessageBoxIcon.Information, FAST.Core.Context.ClsConstant.ButtonType.Detail);
        ////                ClsRetInfo info = ClsRetInfoDealer.getExtWarn("003", _formFun, ClsEnums.StatusSetting.YssSave);
        ////                info.setSpecStr("Security", cboSecurity.Value);
        ////                info.setSpecStr("beginDate", dtpIssueDate.getBeginDateStr);
        ////                info.setSpecStr("issueCode", txtIssueCode.Text);

        ////                YssMessageBox.ShowCommonInfo(info);
        ////            }
        ////        }
        ////    }
        ////    catch (Exception ex) 
        ////    {
        ////        YssMessageBox.ShowDyanInformation("保存验证信息错误！", ex.Message, MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
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
    }
}


