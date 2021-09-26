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
using YssSecInformation.Support.Pojo.Mp;
////using YssSecInformation.Support.Fun;

////using YssBaseCls.Pojo;
////using YssData.Pojo.Mp;

////using YssData.Service.Mp;


////using YssPojos.Data.Mp;

////namespace YssData.Form.Mp
namespace YssSecInformation.Mp.SecMktMap.Form
{
    /// <summary>
    /// 功能简介：汇率行情设置，负责汇率行情的增删改查等功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.21
    ///
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：WUWENLAN    
    /// 修改日期：20110113
    /// 修改简介：
    /// 
    /// 
    /// /// －－－－修改记录－－－－
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
    /// 8：增加传到后台去的列头和窗体菜单
    /// 
    ///  /// /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：zhuangyuchen
    /// 修改日期： 2011-2-24
    /// 修改简介：
    ///1:根据新的基类，修改原有代码，增删改查有基类来实现
    ///2：根据list 界面传过来的节点名，修改按钮的状态
    ///
    ///   ///  /// /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：zhuangyuchen
    /// 修改日期： 2011-3-2
    /// 修改简介：
    ///增加市场代码属性，将代码传到后台，增加的时候，按市场代码增加
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.5
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         返回对象和控件的showInfo错误直接抛出异常
    /// </summary>
    public partial class Frm_SEC_MKT_HL_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private IHlMktService marketValueService = null;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_SEC_MKT_HL_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
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
            Cls_SEC_MKT clsExRatePrice = new Cls_SEC_MKT();
            try
            {
                // 判断list选中界面是否有选中数据，在修改时获取原数据的值
                if (null != this.yssGetBaseSelTypeItem())
                {
                    clsExRatePrice.OldC_SEC_CODE = ((Cls_SEC_MKT)this.yssGetBaseSelTypeItem()).C_SEC_CODE;
                    clsExRatePrice.OldD_MKT = ((Cls_SEC_MKT)this.yssGetBaseSelTypeItem()).D_MKT;
                    clsExRatePrice.OldC_DV_PLAT = ((Cls_SEC_MKT)this.yssGetBaseSelTypeItem()).C_DV_PLAT;
                }

                clsExRatePrice.C_SEC_CODE = this.cboCury.Value;     // 货币对
                clsExRatePrice.C_DV_PLAT = this.selDvPlat.Value; ////行情来源
                clsExRatePrice.D_MKT = this.dtpMktDate.getBeginDateStr;   // 行情日期
                clsExRatePrice.C_MKT_TIME = string.Format("{0:T}", this.dtpMktTime.Value);     // 行情时间 this.dtpMktTime.Value.TimeOfDay.ToString();  // 行情时间
                clsExRatePrice.N_PRICE_NEW = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtNewPrice.Value);       // 最新价
                clsExRatePrice.N_PRICE_BUY = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtBuyPrice.Value);       // 买一价
                clsExRatePrice.N_PRICE_SELL = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtSellPrice.Value);     // 卖一价
                clsExRatePrice.N_PRICE_HIGH = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtHighPrice.Value);     // 最高价
                clsExRatePrice.N_PRICE_LOW = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtLowPrice.Value);       // 最低价
                clsExRatePrice.N_PRICE_OPEN = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtOpeningPrice.Value);   // 开盘价
                clsExRatePrice.N_PRICE_AVG = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtMiddlePrice.Value);    // 中间价
                clsExRatePrice.N_PRICE_CLOSE = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtClosingPrice.Value); // 收盘价
                clsExRatePrice.BeanId = "exratepriceadmin";   // 不同调用者的辨别标志
                clsExRatePrice.C_MKT_CLS = "ER";            // 不同的行情区别标志，汇率为"ER",场内为"m_IN"

            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return clsExRatePrice;
        }

        /// <summary>
        /// 对控件一一赋值
        /// </summary>
        /// <param name="clsExRatePrice">价格</param>
        public void showInfo(Cls_SEC_MKT clsExRatePrice)
        {
            try
            {
                this.cboCury.Value = clsExRatePrice.C_SEC_CODE;   // 货币对  
                this.selDvPlat.Value = clsExRatePrice.C_DV_PLAT; ////行情来源
                this.dtpMktDate.setDateTime(Convert.ToDateTime(clsExRatePrice.D_MKT));       // 行情日期
                ////this.dtpMktTime.yssInitDateTime = Convert.ToDateTime(clsExRatePrice.C_MKT_TIME);  // 行情时间
                this.dtpMktTime.Value = Convert.ToDateTime(clsExRatePrice.C_MKT_TIME);
                this.txtNewPrice.Text = clsExRatePrice.N_PRICE_NEW;        // 最新价
                this.txtBuyPrice.Text = clsExRatePrice.N_PRICE_BUY;        // 买一价
                this.txtSellPrice.Text = clsExRatePrice.N_PRICE_SELL;     // 卖一价
                this.txtHighPrice.Text = clsExRatePrice.N_PRICE_HIGH;     // 最高价
                this.txtLowPrice.Text = clsExRatePrice.N_PRICE_LOW;       // 最低价
                this.txtOpeningPrice.Text = clsExRatePrice.N_PRICE_OPEN;   // 开盘价
                this.txtMiddlePrice.Text = clsExRatePrice.N_PRICE_AVG;     // 中间价
                this.txtClosingPrice.Text = clsExRatePrice.N_PRICE_CLOSE;  // 收盘价
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }
        }

        /// <summary>
        /// 封装窗体到对象
        /// </summary>
        /// <returns>Pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            SecMkt clsExRatePrice = new SecMkt();
            try
            {
                clsExRatePrice.C_SEC_CODE = this.cboCury.Value;     // 货币对
                clsExRatePrice.C_DV_PLAT = this.selDvPlat.Value; ////行情来源
                clsExRatePrice.D_MKT = this.dtpMktDate.getBeginDateStr;   // 行情日期
                clsExRatePrice.C_MKT_TIME = string.Format("{0:T}", this.dtpMktTime.Value);     // 行情时间 this.dtpMktTime.Value.TimeOfDay.ToString();  // 行情时间
                clsExRatePrice.N_PRICE_NEW = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtNewPrice.Value);       // 最新价
                clsExRatePrice.N_PRICE_BUY = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtBuyPrice.Value);       // 买一价
                clsExRatePrice.N_PRICE_SELL = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtSellPrice.Value);     // 卖一价
                clsExRatePrice.N_PRICE_HIGH = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtHighPrice.Value);     // 最高价
                clsExRatePrice.N_PRICE_LOW = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtLowPrice.Value);       // 最低价
                clsExRatePrice.N_PRICE_OPEN = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtOpeningPrice.Value);   // 开盘价
                clsExRatePrice.N_PRICE_AVG = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtMiddlePrice.Value);    // 中间价
                clsExRatePrice.N_PRICE_CLOSE = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtClosingPrice.Value); // 收盘价
                ////clsExRatePrice.BeanId = "exratepriceadmin";   // 不同调用者的辨别标志
                clsExRatePrice.C_MKT_CLS = "ER";            // 不同的行情区别标志，汇率为"ER",场内为"m_IN"
                clsExRatePrice.D_PUB = clsExRatePrice.D_MKT;
                clsExRatePrice.C_DATA_IDF = "H";

                ////STORY #35336 （嘉实QD需求）原4.0需求：26150 需求北京-[嘉实基金]QDII资产管理系统[高]2015928001（QDII汇率导入需求)
                if (this.cboPort.SelectedItem != null && "[root]".Equals(this.cboPort.SelectedItem.ParentNodeID))
                {
                    clsExRatePrice.C_PORT_CODE = "";  ////置空
                }
                else
                {
                    clsExRatePrice.C_PORT_CODE = this.cboPort.Value;  ////投资组合
                }
                

                ////add by liuxiang 2013/9/10 STORY #4101
                if (status == FAST.Core.Context.ClsEnums.StatusSetting.YssEdit)
                {
                    ////SecMkt oldTrade = this.frmBaseViewList.tbMain.SelectedRow.Tag as SecMkt;
                    ////if (ClsBizCons.Z.Equals(oldTrade.C_DATA_IDF))
                    ////{
                    ////    clsExRatePrice.C_DATA_IDF = oldTrade.C_DATA_IDF;
                    ////}
                    ////else
                    ////{
                    ////    clsExRatePrice.C_DATA_IDF = ClsBizCons.H;
                    ////}

                    GzPrice oldTrade = this.yssGetBaseSelTypeItemMVC() as GzPrice;
                    if (oldTrade != null)
                    {
                        //////自动的需要改为自动转手动
                        if (oldTrade.C_DATA_IDF.Equals(ClsBizCons.Z))
                        {
                            clsExRatePrice.C_DATA_IDF = ClsBizCons.Z_H;
                        }
                        else
                        {
                            clsExRatePrice.C_DATA_IDF = oldTrade.C_DATA_IDF;
                        }
                    }
                    else
                    {
                        clsExRatePrice.C_DATA_IDF = ClsBizCons.H;
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
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_MKT_HL_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.marketValueService = ServiceFactory.createService(serviceType) as IHlMktService;
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
                SecMkt clsExRatePrice = this.yssGetBaseSelTypeItemMVC() as SecMkt;

                if (null != clsExRatePrice)
                {
                    this.cboCury.Value = clsExRatePrice.C_SEC_CODE;   // 货币对  
                    this.selDvPlat.Value = clsExRatePrice.C_DV_PLAT; ////行情来源
                    this.dtpMktDate.setDateTime(Convert.ToDateTime(clsExRatePrice.D_MKT));       // 行情日期
                    this.dtpMktTime.Value = Convert.ToDateTime(clsExRatePrice.C_MKT_TIME);
                    this.cboPort.Value = clsExRatePrice.C_PORT_CODE;   ////投资组合
                    this.txtNewPrice.Text = clsExRatePrice.N_PRICE_NEW;        // 最新价
                    this.txtBuyPrice.Text = clsExRatePrice.N_PRICE_BUY;        // 买一价
                    this.txtSellPrice.Text = clsExRatePrice.N_PRICE_SELL;     // 卖一价
                    this.txtHighPrice.Text = clsExRatePrice.N_PRICE_HIGH;     // 最高价
                    this.txtLowPrice.Text = clsExRatePrice.N_PRICE_LOW;       // 最低价
                    this.txtOpeningPrice.Text = clsExRatePrice.N_PRICE_OPEN;   // 开盘价
                    this.txtMiddlePrice.Text = clsExRatePrice.N_PRICE_AVG;     // 中间价
                    this.txtClosingPrice.Text = clsExRatePrice.N_PRICE_CLOSE;  // 收盘价
                }
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }
        }
    }
}


