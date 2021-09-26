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
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using YssInformation.Support.Fun;

using YssSecInformation.Support.Mp.SecMktMap.Pojo;


namespace YssSecInformation.Mp.Indexmp.Form
{
    /// <summary>
    /// 指数行情SET窗体
    /// </summary>
    public partial class Frm_SEC_MKT_INDEX_S : FrmBaseSet
    {
        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_SEC_MKT_INDEX_S()
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
        /// 封装界面数据
        /// </summary>
        /// <returns>pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            SecMkt clsExRatePrice = new SecMkt();
            try
            {
                clsExRatePrice.C_SEC_CODE = this.selSecurity.Value;    // 证券代码 
                clsExRatePrice.C_DV_PLAT = this.selDvPlat.Value; ////行情来源
                clsExRatePrice.D_MKT = this.dtpMKTPriceDate.getBeginDate.ToString("yyyy-MM-dd");   // 行情日期
                clsExRatePrice.C_MKT_TIME = string.Format("{0:T}", this.dtpMKTPriceTime.Value);     // 行情时间
                clsExRatePrice.N_PRICE_NEW = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtNewPrice.Text);     // 最新价
                clsExRatePrice.N_PRICE_BUY = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtBuyPrice.Text);     // 买一价
                clsExRatePrice.N_PRICE_SELL = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtSellPrice.Text);   // 卖一价
                clsExRatePrice.N_PRICE_HIGH = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtHighPrice.Text);   // 最高价
                clsExRatePrice.N_PRICE_LOW = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtLowPrice.Text);     // 最低价
                clsExRatePrice.N_PRICE_OPEN = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtOpenPrice.Text);   // 开盘价
                clsExRatePrice.N_PRICE_AVG = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtAveragePrice.Text); // 平均价
                clsExRatePrice.N_PRICE_CLOSE = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtClosePrice.Text); // 收盘价
                clsExRatePrice.C_HQZT_CODE = this.cboHqzt.Value == null ? "HQZT_ZCJY" : this.cboHqzt.Value; // 行情状态

                ////BUG #122592 【紧急】【广发证券】多空杠杆指数行情清算进来错误的问题 edit by xhb 2015-11-19 行情设置成指数
                clsExRatePrice.C_MKT_CLS = "IX";   //// 不同的行情区别标志，汇率为"ER",场内为"m_IN"
                clsExRatePrice.C_DATA_IDF = "H";
                clsExRatePrice.D_PUB = clsExRatePrice.D_MKT;

                ////add by liuxiang 2013/9/10 STORY #4101
                if (status == FAST.Core.Context.ClsEnums.StatusSetting.YssEdit)
                {
                    SecMkt oldTrade = this.frmBaseViewList.tbMain.SelectedRow.Tag as SecMkt;
                    //// BUG #138125 公共处理AMAC行业指数历史行情时，若有手工数据提示后不勾选重新生成清算报错 且自动转手工数据没有提示信息
                    //// update by 黄凯旋 2016-8-25 自动转手工
                    if (ClsBizCons.Z.Equals(oldTrade.C_DATA_IDF))
                    {
                        ////clsExRatePrice.C_DATA_IDF = oldTrade.C_DATA_IDF;
                        clsExRatePrice.C_DATA_IDF = ClsBizCons.Z_H;
                    }
                    else
                    {
                        ////clsExRatePrice.C_DATA_IDF = ClsBizCons.H;
                        clsExRatePrice.C_DATA_IDF = oldTrade.C_DATA_IDF;
                    }
                }
                else if (status == FAST.Core.Context.ClsEnums.StatusSetting.YssAdd || status == FAST.Core.Context.ClsEnums.StatusSetting.YssCopy)
                {
                    clsExRatePrice.C_DATA_IDF = ClsBizCons.H;
                }
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return clsExRatePrice;
        }

        /// <summary>
        /// 展示界面数据
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                SecMkt marketPrice = this.yssGetBaseSelTypeItemMVC() as SecMkt;

                if (null != marketPrice)
                {
                    this.selSecurity.Value = marketPrice.C_SEC_CODE;   // update by wuwenlan 类的属性变化了
                    this.selDvPlat.Value = marketPrice.C_DV_PLAT; ////行情来源
                    this.dtpMKTPriceDate.setDateTime(Convert.ToDateTime(marketPrice.D_MKT));     // update by wuwenlan 类的属性变化了

                    ///// by weijj 没有判断空格，双击打开报错。
                    if (marketPrice.C_MKT_TIME != null && marketPrice.C_MKT_TIME.Trim().Length > 0)  
                    {
                        this.dtpMKTPriceTime.Value = Convert.ToDateTime(marketPrice.C_MKT_TIME);  // update by wuwenlan 类的属性变化了
                    }

                    this.txtClosePrice.Text = marketPrice.N_PRICE_CLOSE;   // update by wuwenlan 类的属性变化了
                    this.txtAveragePrice.Text = marketPrice.N_PRICE_AVG;   // update by wuwenlan 类的属性变化了
                    this.txtBuyPrice.Text = marketPrice.N_PRICE_BUY;     // update by wuwenlan 类的属性变化了
                    this.txtSellPrice.Text = marketPrice.N_PRICE_SELL;   // update by wuwenlan 类的属性变化了
                    this.txtOpenPrice.Text = marketPrice.N_PRICE_OPEN;   // update by wuwenlan 类的属性变化了
                    this.txtNewPrice.Text = marketPrice.N_PRICE_NEW;     // update by wuwenlan 类的属性变化了
                    this.txtHighPrice.Text = marketPrice.N_PRICE_HIGH;   // update by wuwenlan 类的属性变化了
                    this.txtLowPrice.Text = marketPrice.N_PRICE_LOW;     // update by wuwenlan 类的属性变化了
                    this.cboHqzt.Value = marketPrice.C_HQZT_CODE; // 行情状态
                }
            }
            catch (ClsBaseException e)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-110023", _formFun, status));
                ClsBaseException.DiscardException(e);
            }
        }

        /// <summary>
        /// 加载后事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void selSecurity_YssOnAfterLoadData(object sender, YssBeforeOperEventArgs e)
        {
            FAST.Core.BaseControl.YssSelCombox comBox = sender as FAST.Core.BaseControl.YssSelCombox;
            comBox.DisplayValue = "C_SEC_CODE";
            comBox.Parameter = "C_SEC_CODE~C_INDEX_NAME";
        }
    }
}


