using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
using FAST.Core.CRUD.Form;
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
using System.Linq;
using System.Text;
using FAST.Common.Service.Services;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Mp.SecEq.Pojo;
using FAST.Core.CRUD.Interface;

namespace YssSecInformation.Mp.SecEq.Form
{
    /// <summary>
    /// chenbo
    /// 2017-06-24
    /// #42948 资讯信息管理组件化拆分
    /// -------------------------------
    /// 证券预发行信息SET
    /// </summary>
    public partial class Frm_SEC_EQU_YFX_S : FrmBaseSet
    {
        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_SEC_EQU_YFX_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 封装数据
        /// </summary>
        /// <returns>保存数据</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            SecEqu prePub = null;

            try
            {
                prePub = new SecEqu();
                prePub.C_DATA_IDF = "YFX";
                prePub.C_SEC_CODE = this.cboSecurity.Value; // 证券代码

                SecBase sec = cboSecurity.SelectedItem.DataEntity as SecBase;
                prePub.C_MKT_CODE = sec.C_MKT_CODE;         ////交易市场6
                prePub.C_SEC_CODE_TAG = this.txtIssueCode.Text; // 发行代码
                prePub.C_DV_CODE = this.cboBidType.Value;   // 招标类型
                ////modified by liyanjun 20140820 BUG #99525 SET界面没有千分符 
                prePub.N_PRICE_PLAC = Convert.ToString(Convert.ToDecimal(this.txtBenchMarkReturn.Text));      // 基准收益
                prePub.D_REG = this.dtpStartDate.getBeginDateStr;  // 开始日期
                prePub.D_EXR = this.dtpEndDate.getBeginDateStr;  // 结束日期
                prePub.D_FINAL = this.dtpSettDate.getBeginDateStr;   // 交收日期
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }

            return prePub;
        }

        /// <summary>
        /// 展示数据
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                SecEqu clsSecurityIssue = (SecEqu)this.yssGetBaseSelTypeItemMVC();
                if (clsSecurityIssue == null)
                {
                    return;
                }

                ////this.cboSecurity.Text = clsSecurityIssue.C_SEC_CODE;
                this.cboSecurity.Value = clsSecurityIssue.C_SEC_CODE;  
                this.txtIssueCode.Text = clsSecurityIssue.C_SEC_CODE_TAG;
                this.cboBidType.Value = clsSecurityIssue.C_DV_CODE;
                this.txtBenchMarkReturn.Text = clsSecurityIssue.N_PRICE_PLAC;
                this.dtpStartDate.setDateTime(Convert.ToDateTime(clsSecurityIssue.D_REG));
                this.dtpEndDate.setDateTime(Convert.ToDateTime(clsSecurityIssue.D_EXR));
                this.dtpSettDate.setDateTime(Convert.ToDateTime(clsSecurityIssue.D_FINAL));
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 重写set界面新增按钮
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnNew_Click(object sender, EventArgs e)
        {
            this.txtBenchMarkReturn.Text = null;
            base.btnNew_Click(sender, e);
        }


        /// <summary>
        /// YssOnBeforeSaveClick
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_EQU_YFX_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        {
            DateTime date1 = DateTime.Parse(this.dtpStartDate.getBeginDateStr);  // 发行日
            DateTime date2 = DateTime.Parse(this.dtpEndDate.getBeginDateStr);     // 结束日
            DateTime date3 = DateTime.Parse(this.dtpSettDate.getBeginDateStr);     // 交收日
            if (date2 < date1)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", this._formFun, ClsEnums.StatusSetting.YssAdd));
                e.IsCancel = true;
            }

            if (date3 < date2)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("002", this._formFun, ClsEnums.StatusSetting.YssAdd));
                e.IsCancel = true;
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
                    this.cboSecurity.YssReadOnly = true;
                    this.cboSecurity.Value = (frmDetailData.MainDataPojo as SecBase).C_SEC_CODE;
                }
            }
        }
    }
}


