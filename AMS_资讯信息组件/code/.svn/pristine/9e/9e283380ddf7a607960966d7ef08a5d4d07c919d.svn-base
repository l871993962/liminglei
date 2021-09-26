using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;

using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;
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


using FAST.Common.Service.Services;
using YssSecInformation.Support.Sv.Service;
using YssSecInformation.Support.Pojo.Sec;
using FAST.Core.CRUD.Interface;
using YssSecInformation.Support.Sv.Pojo;

namespace YssSecInformation.Sv.Form
{
    /// <summary>
    /// 每日百元利息
    /// </summary>
    public partial class Frm_SEC_MRLX_ZQ_S : FrmBaseSet
    {
        /// <summary>
        /// 债券每日利息数据服务接口
        /// </summary>
        private IFiIncomeService myService = null;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_SEC_MRLX_ZQ_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 初始化界面控件
        /// </summary>
        ////public override void yssInitCtlAttr()
        ////{
        ////    this.dtpjx.setDateTime(DateTime.Now);  // 设置控件显示时间为当前时间         
        ////}

        /// <summary>
        /// 封装界面元素为pojo对象
        /// </summary>
        /// <returns>由界面元素组成的对象</returns>
        public override ClsBasePojo yssFaceInfoToObj()
        {
            Cls_SEC_ZQLX clszqlx = null;
            try
            {
                clszqlx = new Cls_SEC_ZQLX();

                if (null != this.yssGetBaseSelTypeItem())
                {
                    Cls_SEC_ZQLX clslx = (Cls_SEC_ZQLX)this.yssGetBaseSelTypeItem();
                    clszqlx.OldC_SEC_CODE = clslx.OldC_SEC_CODE;
                    clszqlx.OldD_INCOME = clslx.OldD_INCOME;
                }

                clszqlx.C_SEC_CODE = this.cboSec.Value;
                clszqlx.C_DV_QUT_MOD = this.cbobj.Value;
                clszqlx.D_INCOME = this.dtpjx.getBeginDateStr;
                clszqlx.N_INCOME_DAYS = this.txtlxts.Value;
                clszqlx.N_COUP_RATE = this.txtpiaomian.Value;
                clszqlx.N_REM_COR = this.txtbenjin.Value;
                clszqlx.N_INCOME_PT = this.tilsqian.Value;
                clszqlx.N_INCOME_PT_DUE = this.txtshuiqian.Value;
                clszqlx.N_INCOME_AT = this.tailTexshuihou.Value;
                clszqlx.N_INCOME_AT_DUE = this.txtshuihou.Value;

            }
            catch (Exception e) 
            {
                YssMessageBox.ShowCommonInfo(e.Message);
            }

            return clszqlx;
        }

        /// <summary>
        /// From load action
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_MRLX_ZQ_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.myService = ServiceFactory.createService(serviceType) as IFiIncomeService;
            this.dataService = this.myService;
        }

        /// <summary>
        /// 展示对象属性到窗体
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                FiIncome clslx = (FiIncome)this.yssGetBaseSelTypeItemMVC();
                if (clslx != null)
                {
                    this.cboSec.Value = clslx.C_SEC_CODE;
                    this.cbobj.Value = clslx.C_DV_QUT_MOD;
                    this.dtpjx.setDateTime(Convert.ToDateTime(clslx.D_INCOME));
                    this.txtlxts.Text = clslx.N_INCOME_DAYS;
                    this.txtbenjin.Text = clslx.N_REM_COR;
                    this.txtpiaomian.Text = clslx.N_COUP_RATE;
                    this.tilsqian.Text = clslx.N_INCOME_PT;
                    this.txtshuiqian.Text = clslx.N_INCOME_AT;
                    this.tailTexshuihou.Text = clslx.N_INCOME_PT_DUE;
                    ////this.txtshuiqian.Text = clslx.N_INCOME_PT_DUE;
                    ////this.tailTexshuihou.Text = clslx.N_INCOME_AT;
                    this.txtshuihou.Text = clslx.N_INCOME_AT_DUE;
                }
            }
            catch (Exception e)
            {
                YssMessageBox.ShowCommonInfo(e.Message);
            }
        }

        /// <summary>
        /// 封装窗体数据对对象
        /// </summary>
        /// <returns>Pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            FiIncome clszqlx = null;
            try
            {
                clszqlx = new FiIncome();
                clszqlx.C_SEC_CODE = this.cboSec.Value;
                clszqlx.C_DV_QUT_MOD = this.cbobj.Value;
                clszqlx.D_INCOME = this.dtpjx.getBeginDateStr;
                clszqlx.N_INCOME_DAYS = this.txtlxts.Value;
                clszqlx.N_COUP_RATE = this.txtpiaomian.Value;
                clszqlx.N_REM_COR = this.txtbenjin.Value;
                clszqlx.N_INCOME_PT = this.tilsqian.Value;
                clszqlx.N_INCOME_AT = this.txtshuiqian.Value;
                ////clszqlx.N_INCOME_PT_DUE = this.txtshuiqian.Value;
                clszqlx.N_INCOME_PT_DUE = this.tailTexshuihou.Value;
                clszqlx.N_INCOME_AT_DUE = this.txtshuihou.Value;

            }
            catch (Exception e)
            {
                YssMessageBox.ShowCommonInfo(e.Message);
            }

            return clszqlx;
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
                    this.cboSec.YssReadOnly = true;
                    this.cboSec.Value = (frmDetailData.MainDataPojo as SecBase).C_SEC_CODE;
                }
            }
        }

        /// <summary>
        /// 税前计息利息按钮点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void tilsqian_TailClick(object sender, EventArgs e)
        {
            if (this.cboSec.Value != null && this.txtlxts.Value != null && this.txtpiaomian.Value != null && this.txtbenjin.Value != null)
            {
                Dictionary<string, string> paraDict = new Dictionary<string, string>();
                paraDict.Add("C_SEC_CODE", this.cboSec.Value);
                paraDict.Add("D_DATE", this.dtpjx.getBeginDateStr);
                paraDict.Add("N_DAYS", this.txtlxts.Value);
                paraDict.Add("N_COUP_RATE", this.txtpiaomian.Value);
                paraDict.Add("N_REM_COR", this.txtbenjin.Value);
                string res = myService.calcBeforeTaxAndDue(paraDict);
                this.tilsqian.Text = res;
            }
            else
            {
                this.tilsqian.Text = "0";
            }

        }

        /// <summary>
        /// 税前计息利息按钮点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void tailTexshuihou_TailClick(object sender, EventArgs e)
        {
            if (this.cboSec.Value != null && this.txtlxts.Value != null && this.txtpiaomian.Value != null && this.txtbenjin.Value != null)
            {
                Dictionary<string, string> paraDict = new Dictionary<string, string>();
                paraDict.Add("C_SEC_CODE", this.cboSec.Value);
                paraDict.Add("D_DATE", this.dtpjx.getBeginDateStr);
                paraDict.Add("N_DAYS", this.txtlxts.Value);
                paraDict.Add("N_COUP_RATE", this.txtpiaomian.Value);
                paraDict.Add("N_REM_COR", this.txtbenjin.Value);
                string res = myService.calcBeforeTaxAndDue(paraDict);
                this.tailTexshuihou.Text = res;
            }
            else
            {
                this.tailTexshuihou.Text = "0";
            }
        }
    }
}


