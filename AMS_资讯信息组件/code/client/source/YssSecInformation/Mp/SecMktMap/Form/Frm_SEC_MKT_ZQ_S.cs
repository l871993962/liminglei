////using YssBaseCls.Fun;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
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
using YssSecInformation.Support.Mp.SecMktMap.Service;
using YssSecInformation.Support.Mp.SecMktMap.Pojo;
////using YssSecInformation.Support.Fun;

//using YssBaseCls.Pojo;
//using YssData.Pojo.Mp;

//using YssData.Service.Mp;


////using YssPojos.Data.Mp;

////namespace YssData.Form.Mp
namespace YssSecInformation.Mp.SecMktMap.Form
{
    /// <summary>
    /// FrmSecMKTPrice 的摘要说明。
    /// 作用：本类是为了实现证券行情数据的设置
    ///  
    ///  作者：chenyoulong
    ///  
    ///  版本：v4.5.0.1
    ///  
    ///  添加内容：窗体绘制，功能方法实现
    ///  
    ///  添加日期：2010.12.17
    /// 
    /// 修改 ：zhuangyuchen
    /// 版本：v4.5.0.2
    /// 修改日期：2011-2-11
    /// 修改内容：第二轮翻新
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：zhuangyuchen
    /// 修改日期： 2011-2-21
    /// 修改简介：
    /// 1:增加回收站开启关闭机制
    /// 2:删除添加修改等操作的操作成功信息
    /// 3：添加注释
    /// 4：删除初始化加载下拉框的代码，改成在控件的属性中设置
    /// 6：修改出错的提示信息
    /// 7:去掉增删改查成功的提示信息，由基类来提供
    /// 9：增加传到后台去的列头和窗体菜单
    /// 
    ///  /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：zhuangyuchen
    /// 修改日期： 2011-2-25
    /// 修改简介：
    /// 1：修改由于基类改变需要重新实现积累的方法
    /// 2：去掉增删改查的方法，改为基类来控制
    /// 3：增加对set窗体按钮的控制
    ///  －－－－修改记录－－－－
    /// 当前版本：V4.5.0.5
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         返回对象和控件的showInfo错误直接抛出异常
    ///  －－－－修改记录－－－－
    ///  当前版本：V4.5.0.6
    ///  修改人：dingxukun
    ///  修改日期： 2011-3-14
    ///  修改简介：STORY #34828 【南方基金】【紧急】增加参数控制项不活跃债券公允价值估值需求
    ///  1.添加成交数量、成交金额控件
    ///  2.修改添加,修改操作方法
    /// </summary>
    public partial class Frm_SEC_MKT_ZQ_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private ISecMktService marketValueService = null;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_SEC_MKT_ZQ_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
        }

      

        /// <summary>
        /// 初始化界面控件
        /// </summary>
        public override void yssInitCtlAttr()
        {
        ////    this.dtpMKTPriceDate.setDateTime(DateTime.Now);  // 设置控件显示时间为当前时间         
            this.cboHqzt.Value = "HQZT_ZCJY";
        }

        /// <summary>
        /// 封装界面元素为pojo对象
        /// </summary>
        /// <returns>由界面元素组成的对象</returns>
        public override ClsBasePojo yssFaceInfoToObj()
        {
            Cls_SEC_MKT clsExRatePrice = new Cls_SEC_MKT();
            try
            {
                // 判断list选中界面是否有选中数据，在修改时获取原数据的值
                if (null != this.yssGetBaseSelTypeItem())
                {
                    clsExRatePrice.OldC_SEC_CODE = ((Cls_SEC_MKT)this.yssGetBaseSelTypeItem()).C_SEC_CODE;
                    clsExRatePrice.OldC_DV_PLAT = ((Cls_SEC_MKT)this.yssGetBaseSelTypeItem()).C_DV_PLAT;
                    clsExRatePrice.OldD_MKT = ((Cls_SEC_MKT)this.yssGetBaseSelTypeItem()).D_MKT;
                }

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

                //// add by zhd 2016-11-09 赋默认值“正常交易”
                clsExRatePrice.C_HQZT_CODE = this.cboHqzt.Value == null ? "HQZT_ZCJY" : this.cboHqzt.Value; // 行情状态 ADD BY WZH STORY #32313 停牌股票信息生成做成公共层面，不关联组合
                // STORY #35353 （嘉实QD需求）原4.0需求：10354 【QDV4嘉实2011年11月09日01_A】估值检查步骤中，增加对除权股票的行情状态的判断
                clsExRatePrice.N_AMOUNT_TOTAL = FAST.Core.Util.ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.totalAmount.Text); // 总成交量
                clsExRatePrice.N_PRICE_TOTAL = FAST.Core.Util.ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.totalprice.Text); // 总成交额
                clsExRatePrice.C_MKT_STATUS = this.cboHqzt.Value; ////行情状态
              
               
                clsExRatePrice.C_MKT_CLS = "IN";   // 不同的行情区别标志，汇率为"ER",场内为"m_IN"

            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return clsExRatePrice;
        }

        ///<summary>
        ///  对控件一一赋值
        /// </summary>
        /// <param name="marketPrice">价格</param>
        public void showInfo(Cls_SEC_MKT marketPrice)
        {
            try
            {
                    ////this.selSecurity.Text = "";
                    this.selSecurity.Value = marketPrice.C_SEC_CODE;   // update by wuwenlan 类的属性变化了
                    this.selDvPlat.Value = marketPrice.C_DV_PLAT; ////行情来源
                    this.dtpMKTPriceDate.setDateTime(Convert.ToDateTime(marketPrice.D_MKT));     // update by wuwenlan 类的属性变化了
                    this.dtpMKTPriceTime.Value = Convert.ToDateTime(marketPrice.C_MKT_TIME);  // update by wuwenlan 类的属性变化了
                    this.txtClosePrice.Text = marketPrice.N_PRICE_CLOSE;   // update by wuwenlan 类的属性变化了
                    this.txtAveragePrice.Text = marketPrice.N_PRICE_AVG;   // update by wuwenlan 类的属性变化了
                    this.txtBuyPrice.Text = marketPrice.N_PRICE_BUY;     // update by wuwenlan 类的属性变化了
                    this.txtSellPrice.Text = marketPrice.N_PRICE_SELL;   // update by wuwenlan 类的属性变化了
                    this.txtOpenPrice.Text = marketPrice.N_PRICE_OPEN;   // update by wuwenlan 类的属性变化了
                    this.txtNewPrice.Text = marketPrice.N_PRICE_NEW;     // update by wuwenlan 类的属性变化了
                    this.txtHighPrice.Text = marketPrice.N_PRICE_HIGH;   // update by wuwenlan 类的属性变化了
                    this.txtLowPrice.Text = marketPrice.N_PRICE_LOW;     // update by wuwenlan 类的属性变化了
                    this.cboHqzt.Value = marketPrice.C_HQZT_CODE; // 行情状态 ADD BY WZH STORY #32313 停牌股票信息生成做成公共层面，不关联组合

                    // STORY #35353 （嘉实QD需求）原4.0需求：10354 【QDV4嘉实2011年11月09日01_A】估值检查步骤中，增加对除权股票的行情状态的判断
                    this.totalAmount.Text = marketPrice.N_AMOUNT_TOTAL; // 总成交量
                    this.totalprice.Text = marketPrice.N_PRICE_TOTAL; // 总成交额
                    this.cboHqzt.Text = marketPrice.C_MKT_STATUS; // 行情状态
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }
        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_MKT_ZQ_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.marketValueService = ServiceFactory.createService(serviceType) as ISecMktService;
            this.dataService = this.marketValueService;
        }

        /// <summary>
        /// 展示对象属性到窗体
        /// </summary>
        /// <param name="pojo">Pojo</param>
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
                    //// modified by dingshalu 20160622 BUG #132822 【紧急】中信证券-QDII清算彭博行情数据，行情来源错误
                    if (marketPrice.C_MKT_TIME.Trim() != "")  
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
                    this.txtSettPrice.Text = marketPrice.N_PRICE_SETT;  // 结算价 add by liyanjun 2016-4-29 STORY29425个股期权行情获取收盘价
                    this.txtTdAmount.Text = marketPrice.N_TD_AMOUNT;    ////  added by dingxukun story 34828
                    this.txtTdMoney.Text = marketPrice.N_TD_MONEY;      ////  added by dingxukun story 34828
                    ////STORY #25937 交易所行情清算需要支持保存昨日收盘价 guoguangyi 2015-10-15
                    this.txtClosePrice_Zr.Text = marketPrice.N_PRICE_ZRCLOSE;   ////add by guoguangyi
                    this.cboHqzt.Value = marketPrice.C_HQZT_CODE; // 行情状态 ADD BY WZH STORY #32313 停牌股票信息生成做成公共层面，不关联组合
                    // STORY #35353 （嘉实QD需求）原4.0需求：10354 【QDV4嘉实2011年11月09日01_A】估值检查步骤中，增加对除权股票的行情状态的判断
                    this.totalAmount.Text = marketPrice.N_AMOUNT_TOTAL; // 总成交量
                    this.totalprice.Text = marketPrice.N_PRICE_TOTAL; // 总成交额
                    this.cboHqzt.Text = marketPrice.C_MKT_STATUS; // 行情状态
                }
            }
            catch (ClsBaseException e)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-110023", _formFun, status));
                ClsBaseException.DiscardException(e);
            }
        }

        /// <summary>
        /// 封装窗体属性到对象
        /// modified by liyanjun 2016-4-29 STORY29425个股期权行情获取收盘价
        /// </summary>
        /// <returns>Pojo</returns>
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
                clsExRatePrice.N_PRICE_SETT = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtSettPrice.Text); // 结算价
                ////STORY 34828 Added by dingxukun 20161010
                clsExRatePrice.N_TD_AMOUNT = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtTdAmount.Text); // 成交数量
                clsExRatePrice.N_TD_MONEY = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtTdMoney.Text); // 成交金额
                
                //// add by zhd 2016-11-09 赋默认值“正常交易”
                clsExRatePrice.C_HQZT_CODE = this.cboHqzt.Value == null ? "HQZT_ZCJY" : this.cboHqzt.Value; // 行情状态 ADD BY WZH STORY #32313 停牌股票信息生成做成公共层面，不关联组合

                // STORY #35353 （嘉实QD需求）原4.0需求：10354 【QDV4嘉实2011年11月09日01_A】估值检查步骤中，增加对除权股票的行情状态的判断
                clsExRatePrice.N_AMOUNT_TOTAL = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.totalAmount.Text); // 总成交量
                clsExRatePrice.N_PRICE_TOTAL = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.totalprice.Text); // 总成交额
                clsExRatePrice.C_MKT_STATUS = this.cboHqzt.Text; ////行情状态

                clsExRatePrice.C_MKT_CLS = "IN";   // 不同的行情区别标志，汇率为"ER",场内为"m_IN"
                clsExRatePrice.C_DATA_IDF = "H";
                clsExRatePrice.D_PUB = clsExRatePrice.D_MKT;
                clsExRatePrice.D_PUB = clsExRatePrice.D_MKT;
                ////zhoushuhang 2016-1-19 BUG #150407 BUG单-南方基金功能测试第三轮BUG汇总1-估值2
                clsExRatePrice.N_PRICE_ZRCLOSE = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtClosePrice_Zr.Text); ////昨日收盘价

                ////add by liuxiang 2013/9/10 STORY #4101
                if (status == FAST.Core.Context.ClsEnums.StatusSetting.YssEdit)
                {
                    SecMkt oldTrade = this.frmBaseViewList.tbMain.SelectedRow.Tag as SecMkt;
                    if (ClsBizCons.Z.Equals(oldTrade.C_DATA_IDF))
                    {
                        clsExRatePrice.C_DATA_IDF = ClsBizCons.Z_H;
                    }
                    else
                    {
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
    }
}


