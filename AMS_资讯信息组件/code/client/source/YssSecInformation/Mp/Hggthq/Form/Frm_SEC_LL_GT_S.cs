using FAST.Core.Util;
using FAST.Common.Service.Pojo;
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
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

////using YssData.Pojo.Mp;
using YssSecInformation.Support.Mp.Hggthq.Pojo;



namespace YssSecInformation.Mp.Hggthq.Form
{
    /// <summary>
    /// 回购收益行情SET
    /// </summary>
    public partial class Frm_SEC_LL_GT_S : FrmBaseSet
    {
        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_SEC_LL_GT_S()
        {
            InitializeComponent();
            this.bUseMVCService = true;
        }

        /// <summary>
        /// 封装截面数据
        /// </summary>
        /// <returns>pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            CounterRate counterRate = new CounterRate();
            counterRate.D_MKT = dtpMktDate.getBeginDate;
            counterRate.C_BIZ_TYPE = null == cboBusiType.Value ? " " : cboBusiType.Value;
            counterRate.N_DURATION = null == cbodur.Value ? 0 : int.Parse(cbodur.Value);
            counterRate.N_RATE = ClsFunction.toDouble(txtRate.Text) / 100;
            counterRate.D_BEGIN = dtpBegin.getBeginDate;
            counterRate.D_END = dtpEnd.getBeginDate;
            counterRate.C_IS_PUBLIC = "1";
            return counterRate;
        }

        /// <summary>
        /// 展示数据
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                ////if (status == FAST.Core.Context.ClsEnums.StatusSetting.YssBrow) 
                ////{
                ////    this.txtRate.YssReadOnly = true;
                ////}

                CounterRate counterRate = (CounterRate)this.yssGetBaseSelTypeItemMVC();
                if (null == counterRate) 
                {
                    return;
                }
                
                this.cboBusiType.Value = counterRate.C_BIZ_TYPE;
                this.dtpMktDate.setDateTime(Convert.ToDateTime(counterRate.D_MKT));
                this.cbodur.Value = counterRate.N_DURATION.ToString();
                this.txtRate.Text = (counterRate.N_RATE * 100).ToString();
                this.dtpBegin.setDateTime(Convert.ToDateTime(counterRate.D_BEGIN));
                this.dtpEnd.setDateTime(Convert.ToDateTime(counterRate.D_END));
            }
            catch (Exception e) 
            {
                throw new ClsBaseException(e.Message);
            }
        }

        /// <summary>
        /// 新增前事件
        /// </summary>
        /// <param name="sender">s</param>
        /// <param name="e">e</param>
        private void Frm_SEC_LL_GT_S_YssOnAfterNewClick(object sender, EventArgs e)
        {
            this.txtRate.YssReadOnly = false;
            this.txtRate.Text = "0";
        }

        /// <summary>
        /// 复制事件，改变利率权限
        /// </summary>
        /// <param name="sender">s</param>
        /// <param name="e">e</param>
        private void Frm_SEC_LL_GT_S_YssOnAfterCopyClick(object sender, EventArgs e)
        {
            this.txtRate.YssReadOnly = false;
            this.txtRate.Text = "0";
        }

        /// <summary>
        /// 重写基类
        /// 控制控件权限
        /// </summary>
        public override void initControlStat()
        {
            this.cboBusiType.YssReadOnly = true;
            this.cbodur.YssReadOnly = true;
            this.dtpBegin.ReadOnly = true;
            this.dtpEnd.ReadOnly = true;
            this.dtpMktDate.ReadOnly = true;
            this.txtRate.YssReadOnly = true;

            base.initControlStat();

            if (status == FAST.Core.Context.ClsEnums.StatusSetting.YssEdit)
            {
                this.cboBusiType.YssReadOnly = true;
                this.cbodur.YssReadOnly = true;
                this.dtpBegin.ReadOnly = true;
                this.dtpEnd.ReadOnly = true;
                this.dtpMktDate.ReadOnly = true;
                this.txtRate.YssReadOnly = false;
            }
            else if (status == FAST.Core.Context.ClsEnums.StatusSetting.YssAdd)
            {
                this.cboBusiType.YssReadOnly = false;
                this.cbodur.YssReadOnly = false;
                this.dtpBegin.ReadOnly = false;
                this.dtpEnd.ReadOnly = false;
                this.dtpMktDate.ReadOnly = false;
                this.txtRate.YssReadOnly = false;
            }
            else if (status == FAST.Core.Context.ClsEnums.StatusSetting.YssCopy)
            {
                this.dtpBegin.ReadOnly = true;
                this.dtpEnd.ReadOnly = true;
                this.cboBusiType.YssReadOnly = false;
                this.cbodur.YssReadOnly = false;
                this.dtpMktDate.ReadOnly = false;
                this.txtRate.YssReadOnly = false;
            }
        }
    }
}


