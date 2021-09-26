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
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Collections;
using YssInformation.Support.Fun;
////using YssBaseCls.Pojo;
using YssSecInformation.Support.Mp.FwMkt.Pojo;


using YssSecInformation.Support.Mp.FwMkt.Service;


////using YssPojos.Data.Mp;

namespace YssSecInformation.Mp.FwMkt.Form
{
    /// <summary>
    /// 远期外汇
    /// </summary>
    public partial class Frm_SEC_MKT_FQ_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private IFwMktValueService mktService = null;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_SEC_MKT_FQ_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 初始化界面控件
        /// </summary>
        public override void yssInitCtlAttr()
        {
            this.cboHqzt.Value = "HQZT_ZCJY";
        }

        /// <summary>
        /// 根据list 界面传过来的节点名，修改按钮的状态
        /// </summary>
        ////public override void yssInitCtlAttr()
        ////{
        ////    this.dtpMktDate.setDateTime(DateTime.Now);       // 设置控件显示时间为当前时间

        ////}

        /// <summary>
        /// 封装界面元素为pojo对象
        /// </summary>
        /// <returns>由界面元素组成的对象</returns>
        public override ClsBasePojo yssFaceInfoToObj()
        {
            Cls_SEC_FQ clsfq = new Cls_SEC_FQ();
            try
            {
                if (this.yssGetBaseSelTypeItem() != null)
                {
                    clsfq.OLDC_SEC_CODE = ((Cls_SEC_FQ)this.yssGetBaseSelTypeItem()).C_SEC_CODE;
                    clsfq.OLDD_MKT = ((Cls_SEC_FQ)this.yssGetBaseSelTypeItem()).D_MKT;
                    clsfq.OLDC_MKT_CLS = ((Cls_SEC_FQ)this.yssGetBaseSelTypeItem()).C_MKT_CLS;
                    clsfq.OLDC_DV_VAR_DUR = ((Cls_SEC_FQ)this.yssGetBaseSelTypeItem()).C_DV_VAR_DUR;
                }

                clsfq.C_SEC_CODE = this.cboCury.Value;
                clsfq.C_DV_VAR_DUR = this.cboDUR.Value;
                clsfq.D_MKT = this.dtpMktDate.getBeginDateStr;
                clsfq.C_MKT_CLS = "FW";
                clsfq.C_MKT_TIME = this.dtpMktTime.Text.Trim().Length == 0 ? "00:00:00" : this.dtpMktTime.Text;
                clsfq.D_SPOT = this.dtmjiqi.getBeginDateStr;
                clsfq.D_FW = this.dtmyuanqi.getEndDateStr;
                clsfq.N_PRICE_BUY = this.txtBuyPrice.Text;
                clsfq.N_PRICE_SELL = this.txtSellPrice.Text;
                clsfq.N_POINT_BUY = this.txtOpeningPrice.Text;
                clsfq.N_POINT_SELL = this.txtNewPrice.Text;
                clsfq.C_DESC = "";
                clsfq.C_DATA_IDF = "H";
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }

            return clsfq;
        }

        /// <summary>
        /// 对控件一一赋值
        /// </summary>
        /// <param name="clsExRatePrice">价格</param>
        public void showInfo(Cls_SEC_FQ clsExRatePrice)
        {
            try
            {
                this.cboCury.Value = clsExRatePrice.C_SEC_CODE;
                this.cboDUR.Value = clsExRatePrice.C_DV_VAR_DUR;
                this.dtpMktDate.setDateTime(Convert.ToDateTime(clsExRatePrice.D_MKT));
                this.dtpMktTime.Text = clsExRatePrice.C_MKT_TIME;
                this.dtmjiqi.setDateTime(Convert.ToDateTime(clsExRatePrice.D_SPOT));
                this.dtmyuanqi.setDateTime(Convert.ToDateTime(clsExRatePrice.D_FW));
                this.txtBuyPrice.Text = clsExRatePrice.N_PRICE_BUY;
                this.txtSellPrice.Text = clsExRatePrice.N_PRICE_SELL;
                this.txtOpeningPrice.Text = clsExRatePrice.N_POINT_BUY;
                this.txtNewPrice.Text = clsExRatePrice.N_POINT_SELL;
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }
        }

        /// <summary>
        /// 保存前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_FEQ_FQ_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        {
            if (ClsFunction.sub(this.dtmjiqi.getBeginDate, this.dtmyuanqi.getEndDate) > 0) 
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, status));
                e.IsCancel = true;
                return;
            }
        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_MKT_FQ_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.mktService = ServiceFactory.createService(serviceType) as IFwMktValueService;
            this.dataService = this.mktService;
        }

        /// <summary>
        /// 封装窗体数据到对象
        /// </summary>
        /// <returns>Pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            FwMktValue clsfq = new FwMktValue();
            try
            {
                clsfq.C_SEC_CODE = this.cboCury.Value;
                clsfq.C_DV_VAR_DUR = this.cboDUR.Value;
                clsfq.D_MKT = this.dtpMktDate.getBeginDateStr;
                clsfq.C_MKT_CLS = "FW";
                clsfq.C_MKT_TIME = this.dtpMktTime.Text.Trim().Length == 0 ? "00:00:00" : this.dtpMktTime.Text;
                clsfq.D_SPOT = this.dtmjiqi.getBeginDateStr;
                clsfq.D_FW = this.dtmyuanqi.getEndDateStr;
                clsfq.N_PRICE_BUY = this.txtBuyPrice.Value;
                clsfq.N_PRICE_SELL = this.txtSellPrice.Value;
                clsfq.N_POINT_BUY = this.txtOpeningPrice.Value;
                clsfq.N_POINT_SELL = this.txtNewPrice.Value;
                clsfq.C_HQZT_CODE = this.cboHqzt.Value == null ? "HQZT_ZCJY" : this.cboHqzt.Value; // 行情状态
                clsfq.C_DESC = "";
                clsfq.C_DATA_IDF = "H";

                ////add by liuxiang 2013/9/10 STORY #4101
                if (status == FAST.Core.Context.ClsEnums.StatusSetting.YssEdit)
                {
                    ////FwMktValue oldTrade = this.frmBaseViewList.tbMain.SelectedRow.Tag as FwMktValue;
                    ////if (ClsBizCons.Z.Equals(oldTrade.C_DATA_IDF))
                    ////{
                    ////    clsfq.C_DATA_IDF = oldTrade.C_DATA_IDF;
                    ////}
                    ////else
                    ////{
                    ////    clsfq.C_DATA_IDF = ClsBizCons.H;
                    ////}

                    FwMktValue oldTrade = this.yssGetBaseSelTypeItemMVC() as FwMktValue;
                    if (oldTrade != null)
                    {
                        //////自动的需要改为自动转手动
                        if (oldTrade.C_DATA_IDF.Equals(ClsBizCons.Z))
                        {
                            clsfq.C_DATA_IDF = ClsBizCons.Z_H;
                        }
                        else
                        {
                            clsfq.C_DATA_IDF = oldTrade.C_DATA_IDF;
                        }
                    }
                    else
                    {
                        clsfq.C_DATA_IDF = ClsBizCons.H;
                    }
                }
                else if (status == FAST.Core.Context.ClsEnums.StatusSetting.YssAdd || status == FAST.Core.Context.ClsEnums.StatusSetting.YssCopy)
                {
                    clsfq.C_DATA_IDF = ClsBizCons.H;
                }
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }

            return clsfq;
        }

        /// <summary>
        /// 展示对象属性到窗体
        /// </summary>
        /// <param name="pojo">Pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                FwMktValue clsExRatePrice = (FwMktValue)this.yssGetBaseSelTypeItemMVC();

                if (null != clsExRatePrice)
                {
                    this.cboCury.Value = clsExRatePrice.C_SEC_CODE;
                    this.cboDUR.Value = clsExRatePrice.C_DV_VAR_DUR;
                    this.dtpMktDate.setDateTime(Convert.ToDateTime(clsExRatePrice.D_MKT));
                    this.dtpMktTime.Text = clsExRatePrice.C_MKT_TIME;
                    this.dtmjiqi.setDateTime(Convert.ToDateTime(clsExRatePrice.D_SPOT));
                    this.dtmyuanqi.setDateTime(Convert.ToDateTime(clsExRatePrice.D_FW));
                    this.txtBuyPrice.Text = clsExRatePrice.N_PRICE_BUY;
                    this.txtSellPrice.Text = clsExRatePrice.N_PRICE_SELL;
                    this.txtOpeningPrice.Text = clsExRatePrice.N_POINT_BUY;
                    this.txtNewPrice.Text = clsExRatePrice.N_POINT_SELL;
                    this.cboHqzt.Value = clsExRatePrice.C_HQZT_CODE; // 行情状态
                }
            }
            catch (ClsBaseException e)
            {
                ////YssMessageBox.ShowDyanInformation("赋值给窗体控件出错", e.Message, "错误信息", MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-110023", _formFun, status));
                ClsBaseException.DiscardException(e);
            }
        }
    }
}


