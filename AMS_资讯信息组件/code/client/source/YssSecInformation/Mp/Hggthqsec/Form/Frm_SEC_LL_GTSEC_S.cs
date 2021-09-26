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



namespace YssSecInformation.Mp.Hggthqsec.Form
{
    /// <summary>
    /// 回购收益行情SET
    /// </summary>
    public partial class Frm_SEC_LL_GTSEC_S : FrmBaseSet
    {
        /// <summary>
        /// 公共数据
        /// </summary>
        private CounterRate mainData = null;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_SEC_LL_GTSEC_S()
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
            counterRate.C_IS_PUBLIC = "0";
            counterRate.C_SEC_CODE = this.selSecurity.Value;
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
                this.selSecurity.Value = counterRate.C_SEC_CODE;
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
        private void Frm_SEC_LL_GTSEC_S_YssOnAfterNewClick(object sender, EventArgs e)
        {
            this.txtRate.YssReadOnly = false;
            this.txtRate.Text = "0";
        }

        /// <summary>
        /// 复制事件，改变利率权限
        /// </summary>
        /// <param name="sender">s</param>
        /// <param name="e">e</param>
        private void Frm_SEC_LL_GTSEC_S_YssOnAfterCopyClick(object sender, EventArgs e)
        {
            this.txtRate.YssReadOnly = false;
            ////this.txtRate.Text = "0";
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
            this.selSecurity.YssReadOnly = true;

            base.initControlStat();

            if (status == FAST.Core.Context.ClsEnums.StatusSetting.YssEdit)
            {
                this.cboBusiType.YssReadOnly = true;
                this.cbodur.YssReadOnly = true;
                ////this.dtpBegin.ReadOnly = true;
                ////this.dtpEnd.ReadOnly = true;
                this.dtpMktDate.ReadOnly = true;
                this.txtRate.YssReadOnly = false;
                this.selSecurity.YssReadOnly = false;
            }
            else if (status == FAST.Core.Context.ClsEnums.StatusSetting.YssAdd)
            {
                this.cboBusiType.YssReadOnly = true;
                this.cbodur.YssReadOnly = true;
                ////this.dtpBegin.ReadOnly = false;
                ////this.dtpEnd.ReadOnly = false;
                this.dtpMktDate.ReadOnly = true;
                this.txtRate.YssReadOnly = false;
                this.selSecurity.YssReadOnly = false;
            }
            else if (status == FAST.Core.Context.ClsEnums.StatusSetting.YssCopy) 
            {
                this.dtpBegin.ReadOnly = true;
                this.dtpEnd.ReadOnly = true;
                this.cboBusiType.YssReadOnly = true;
                this.cbodur.YssReadOnly = true;
                this.dtpMktDate.ReadOnly = true;
                this.txtRate.YssReadOnly = false;
                this.selSecurity.YssReadOnly = false;
            }
        }

        /// <summary>
        /// 重写基类方法
        /// </summary>
        /// <param name="frm">list</param>
        public override void yssShowForm(FrmBaseList frm)
        {
            CounterRate mainRateRecord = ((Frm_SEC_LL_GTSEC_L)frm).MainDataPojo as CounterRate;
            if (null != mainRateRecord) 
            {
                mainData = mainRateRecord;
            }

            base.yssShowForm(frm);
        }

        /// <summary>
        /// 加载事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_LL_GTSEC_S_Load(object sender, EventArgs e)
        {
            if (null != mainData) 
            {
                this.dtpMktDate.setDateTime(Convert.ToDateTime(mainData.D_MKT));
                this.cboBusiType.Value = mainData.C_BIZ_TYPE;
                this.cbodur.Value = mainData.N_DURATION.ToString();
                this.txtRate.Text = mainData.N_RATE.ToString();
            }
        }

        /// <summary>
        /// 回购期限变化事件
        /// 根据回购期限和启用日期计算停用日期
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cbodur_SelectedValueChanged(object sender, EventArgs e)
        {
            if (null != this.cbodur.Value)
            {
                int dur = int.Parse(this.cbodur.Value);
                dtpEnd.setDateTime(Convert.ToDateTime(ClsFunction.addDate(dtpBegin.getBeginDate, dur, FAST.Core.Context.ClsEnums.DateType.Day)));
                if (7 == dur) 
                {
                    dtpBegin.setDateTime(dtpMktDate.getBeginDate);
                }
                else if (30 == dur)
                {
                    dtpBegin.setDateTime(Convert.ToDateTime(ClsFunction.addDate(dtpMktDate.getBeginDate, 7, FAST.Core.Context.ClsEnums.DateType.Day)));
                }
                else if (91 == dur) 
                {
                    dtpBegin.setDateTime(Convert.ToDateTime(ClsFunction.addDate(dtpMktDate.getBeginDate, 30, FAST.Core.Context.ClsEnums.DateType.Day)));
                }
                else if (182 == dur) 
                {
                    dtpBegin.setDateTime(Convert.ToDateTime(ClsFunction.addDate(dtpMktDate.getBeginDate, 91, FAST.Core.Context.ClsEnums.DateType.Day)));
                }
                else if (365 == dur) 
                {
                    dtpBegin.setDateTime(Convert.ToDateTime(ClsFunction.addDate(dtpMktDate.getBeginDate, 182, FAST.Core.Context.ClsEnums.DateType.Day)));
                }

                dtpEnd.setDateTime(Convert.ToDateTime(ClsFunction.addDate(dtpBegin.getBeginDate, dur - 1, FAST.Core.Context.ClsEnums.DateType.Day)));
            }
        }

        /// <summary>
        /// 启用日期变化事件
        /// 根据启用日期和回购期限计算停用日期
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void dtpBegin_FirstdateTimeInputValueChanged(object sender, EventArgs e)
        {
            ////if (null != this.cbodur.Value) 
            ////{
            ////    int dur = int.Parse(this.cbodur.Value);
            ////    dtpEnd.setDateTime(Convert.ToDateTime(ClsFunction.addDate(dtpBegin.getBeginDate, dur, FAST.Core.Context.ClsEnums.DateType.Day)));
            ////}
        }
    }
}


