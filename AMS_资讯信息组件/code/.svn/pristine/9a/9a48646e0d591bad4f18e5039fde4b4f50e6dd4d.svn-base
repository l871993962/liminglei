////using YssBaseCls.Fun;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
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
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Collections;

////using YssBaseCls.Pojo;
////using YssData.Pojo.Mp;

////using YssData.Service.Mp;


using Yss.KRichEx.AutoFilter.Model;
using YssInformation.Support.Pojo;
using YssInformation.Support.Fun;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Mp.SecMktMap.Service;
using YssSecInformation.Support.Mp.SecMktMap.Pojo;
////using YssSecInformation.Support.Fun;
////using YssPojos.Data.Mp;
////using YssData.Mp;

////namespace YssData.Form.Mp
namespace YssSecInformation.Mp.SecMktMap.Form
{
    /// <summary>
    /// FrmStreetPrice 的摘要说明。
    /// 作用：本类是为了实现场外行情数据的设置
    ///  
    ///  作者：chenyoulong
    ///  
    ///  版本：v4.5.0.1
    ///  
    ///  添加内容：窗体绘制，功能方法实现
    ///  
    ///  添加日期：2010.12.18
    ///   版本：v4.5.0.2
    ///  
    ///  添加内容：去掉窗体上去除的控件，
    ///  去掉增，删，改，审核，反审核中的提示
    ///  pojo类里面的属性值发生相应的变化
    ///  在增，删，改，审核，反审核中添加了filtype中的查询
    ///  添加日期：2010.12.18
    ///   －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         返回对象和控件的showInfo错误直接抛出异常
    /// </summary>
    public partial class Frm_SEC_MKT_CW_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private IOutMktService outMarketService = null;

        /// <summary>
        /// 将左边区域选中的市场代码传到set窗体，增加证券市场信息的时候的根据市场类型来增加
        /// </summary>
        private string my_martCode = "";

        /// <summary>
        /// 验证是否是当前日期 by tanhongpao 2012-6-16
        /// </summary>
        ////private DateTime now;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_SEC_MKT_CW_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 将左边区域选中的市场代码传到set窗体，增加证券市场信息的时候的根据市场类型来增加
        /// </summary>
        public string C_MARKET_CODE
        {
            get { return my_martCode; }
            set { my_martCode = value; }
        }

        /// <summary>
        /// 初始化界面控件
        /// </summary>
        public override void yssInitCtlAttr()
        {
            this.cboHqzt.Value = "HQZT_ZCJY";
        }

        /// <summary>
        /// 初始化界面控件
        /// </summary>
        ////public override void yssInitCtlAttr()
        ////{   
        ////    ////给属性赋值用来判断公告日期是否被改变 by tanhongpao 2012-6-16
        ////    now = DateTime.Now;
        ////    this.dtpNAVDate.setDateTime(now);   // 设置控件显示时间为当前时间
        ////    ////公告日期 by tanhongpao 2012-6-16
        ////    this.dtpPubDate.setDateTime(now);
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

                    ////Orlando 2012-9-4 add 要赋个默认值“ ”
                    ////clsExRatePrice.OldC_DV_PLAT = ((Cls_SEC_MKT)this.yssGetBaseSelTypeItem()).C_DV_PLAT;  DELETE BY ZXL 20130902
                }

                clsExRatePrice.C_SEC_CODE = this.selSecurity.Value;                // 证券代码           
                clsExRatePrice.D_MKT = this.dtpNAVDate.getBeginDateStr;  // 净值日期
                clsExRatePrice.D_PUB = this.dtpPubDate.getBeginDateStr;  // 公告日期
                clsExRatePrice.N_PRICE_CLOSE = this.txtNAVValue.Value;  // 收益
                clsExRatePrice.C_DV_PLAT = this.selDvPlat.Value; // 行情来源 ADD BY ZXL 20130902
                // add by yh 2012-08-27 如果是货币基金，行情分类调整OU_HB
                if (this.selSecurity.SelectedItem != null) 
                {
                    SecBase secBase = (SecBase)this.selSecurity.SelectedItem.DataEntity;
                    string seccode = secBase.C_SEC_CODE;
                    string sylx = outMarketService.getSYLX(seccode);
                    if (secBase.C_SEC_VAR_CODE.Equals("JJ_KFS_HBX") || secBase.C_SEC_VAR_CODE.Equals("LC_HBX") ||
                        secBase.C_SEC_VAR_CODE.Equals("JJ_KFS_HBX_SH") || "MGAINS".Equals(sylx))
                  {
                      clsExRatePrice.C_MKT_CLS = "OU_HB";
                  }
                  else 
                  {
                      clsExRatePrice.C_MKT_CLS = "OU";     // 不同的行情区别标志，汇率为"ER",场内为"IN"
                  }

                }

                ////clsExRatePrice.C_DV_PLAT = " ";  DELETE BY ZXL 20130902

                

            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return clsExRatePrice;
        }

        /// <summary>
        /// 对控件一一赋值
        /// update by wuwenlan 20110218
        /// pojo类的属性值发生变化
        ///  窗体上的控件减少了
        /// </summary>
        /// <param name="outMarketPrice">市场价格</param>
        public void showInfo(Cls_SEC_MKT outMarketPrice)
        {
            try
            {
                this.selSecurity.Text = "";
                this.selSecurity.Value = outMarketPrice.C_SEC_CODE;     // 交易证券
                this.txtNAVValue.Text = outMarketPrice.N_PRICE_CLOSE;   // 净值
                this.dtpNAVDate.setDateTime(Convert.ToDateTime(outMarketPrice.D_MKT));   // 行情日期
                this.dtpPubDate.setDateTime(Convert.ToDateTime(outMarketPrice.D_PUB));   // 公告日期
                this.selDvPlat.Value = outMarketPrice.C_DV_PLAT; // 行情来源 ADD BY ZXL 20130902
                this.mktcls.Value = outMarketPrice.C_MKT_CLS; // 行情分类 STORY #4540 场外净值行情界面增加元素区分货币和非货币类行情 --liuchi/2013.9.10
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }
        }

        /// <summary>
        /// 净值日期的值改变事件 by tanhongpao 2012-6-16
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void dtpNAVDate_FirstdateTimeInputValueChanged(object sender, EventArgs e)
        {
            //// 给净值日期给 公告日期
            this.dtpPubDate.setDateTime(this.dtpNAVDate.getBeginDate);
        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_MKT_CW_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.outMarketService = ServiceFactory.createService(serviceType) as IOutMktService;
            this.dataService = this.outMarketService;
        }

        /// <summary>
        /// 展示对象属性到窗体
        /// </summary>
        /// <param name="pojo">Pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                SecMkt outMarketPrice = this.yssGetBaseSelTypeItemMVC() as SecMkt;

                if (null != outMarketPrice)
                {
                    ////this.selSecurity.Text = "";
                    this.selSecurity.Value = outMarketPrice.C_SEC_CODE;     // 交易证券
                    this.txtNAVValue.Text = outMarketPrice.N_PRICE_CLOSE;   // 净值
                    this.dtpNAVDate.setDateTime(Convert.ToDateTime(outMarketPrice.D_MKT));   // 行情日期
                    this.dtpPubDate.setDateTime(Convert.ToDateTime(outMarketPrice.D_PUB));   // 公告日期
                    this.selDvPlat.Value = outMarketPrice.C_DV_PLAT;  // 行情来源 ADD BY ZXL 20130902
                    this.mktcls.Value = outMarketPrice.C_MKT_CLS; // 行情分类 STORY #4540 场外净值行情界面增加元素区分货币和非货币类行情 --liuchi/2013.9.10
                    this.cboHqzt.Value = outMarketPrice.C_HQZT_CODE; // 行情状态
                }
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }
        }

        /// <summary>
        /// 封装窗体数据到对象
        /// </summary>
        /// <returns>Pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            SecMkt clsExRatePrice = new SecMkt();
            try
            {
                clsExRatePrice.C_SEC_CODE = this.selSecurity.Value;                // 证券代码           
                clsExRatePrice.D_MKT = this.dtpNAVDate.getBeginDateStr;  // 净值日期
                clsExRatePrice.D_PUB = this.dtpPubDate.getBeginDateStr;  // 公告日期
                clsExRatePrice.N_PRICE_CLOSE = this.txtNAVValue.Value;  // 收益
                clsExRatePrice.C_DV_PLAT = this.selDvPlat.Value;  // 行情来源 ADD BY ZXL 20130902
                clsExRatePrice.C_HQZT_CODE = this.cboHqzt.Value == null ? "HQZT_ZCJY" : this.cboHqzt.Value; // 行情状态
                // add by yh 2012-08-27 如果是货币基金，行情分类调整OU_HB
                if (this.selSecurity.SelectedItem != null)
                {
                    SecBase secBase = (SecBase)this.selSecurity.SelectedItem.DataEntity;
                    string seccode = secBase.C_SEC_CODE;
                    string sylx = outMarketService.getSYLX(seccode);
                    /**20150203 modified by liubo.BUG #107410 场外净值行情：行情分类维护货币类行情，保存后变成非货币类行情
                     新增“基金品种_开放式_交易货币（JJ_KFS_HBX_JY）”，“基金品种_开放式_实时货币（JJ_KFS_HBX_SH）”两种品种的券，行情分类也为货币类行情*/
                    if (secBase.C_SEC_VAR_CODE.Equals("JJ_KFS_HBX") || secBase.C_SEC_VAR_CODE.Equals("LC_HBX") ||
                        secBase.C_SEC_VAR_CODE.Equals("JJ_KFS_HBX_SH") || "MGAINS".Equals(sylx))
                    {
                        clsExRatePrice.C_MKT_CLS = "OU_HB";
                    }
                    else
                    {
                        clsExRatePrice.C_MKT_CLS = "OU";     // 不同的行情区别标志，汇率为"ER",场内为"IN"
                    }

                }

                ////clsExRatePrice.C_DV_PLAT = " ";  DELETE BY ZXL 20130902
                clsExRatePrice.C_DATA_IDF = "H";

                ////add by liuxiang 2013/9/10 STORY #4101
                if (status == FAST.Core.Context.ClsEnums.StatusSetting.YssEdit)
                {
                    ////SecMkt oldTrade = this.frmBaseViewList.tbMain.SelectedRow.Tag as SecMkt;
                    SecMkt oldTrade = this.yssGetBaseSelTypeItemMVC() as SecMkt;
                    ////if (ClsBizCons.Z.Equals(oldTrade.C_DATA_IDF))
                    ////{
                    ////    clsExRatePrice.C_DATA_IDF = oldTrade.C_DATA_IDF;
                    ////}
                    ////else
                    ////{
                    ////    clsExRatePrice.C_DATA_IDF = ClsBizCons.H;
                    ////}

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
        /// 根据证券类型加载行情类型
        /// STORY #4540 场外净值行情界面增加元素区分货币和非货币类行情
        /// YSSUCO赢时胜2013年09月10日01_A 
        /// liuchi/2013.9.10
        /// </summary>
        /// <param name="sender">s</param>
        /// <param name="e">e</param>
        private void selSecurity_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.selSecurity.SelectedItem != null)
            {
                SecBase secBase = (SecBase)this.selSecurity.SelectedItem.DataEntity;
                /**20150203 modified by liubo.BUG #107410 场外净值行情：行情分类维护货币类行情，保存后变成非货币类行情
                 新增“基金品种_开放式_交易货币（JJ_KFS_HBX_JY）”，“基金品种_开放式_实时货币（JJ_KFS_HBX_SH）”两种品种的券，行情分类也为货币类行情*/
                string seccode = secBase.C_SEC_CODE;

                if (this.outMarketService == null)
                {
                    Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
                    this.outMarketService = ServiceFactory.createService(serviceType) as IOutMktService;
                }

                string sylx = outMarketService.getSYLX(seccode);
                if (secBase.C_SEC_VAR_CODE.Equals("JJ_KFS_HBX") || secBase.C_SEC_VAR_CODE.Equals("LC_HBX") ||
                    ////secBase.C_SEC_VAR_CODE.Equals("JJ_KFS_HBX_JY") || secBase.C_SEC_VAR_CODE.Equals("JJ_KFS_HBX_SH")
                    secBase.C_SEC_VAR_CODE.Equals("JJ_KFS_HBX_SH")
                    || "MGAINS".Equals(sylx))
                {
                    mktcls.Value = "OU_HB";
                }
                else
                {
                    mktcls.Value = "OU";
                }

            }
        }

        /// <summary>
        /// BUG #318348 多行情同时存在
        /// 相同 证券内码、行情日期、行情分类、行情来源，只允许有一条记录
        /// 重写保存方法
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnSave_Click(object sender, EventArgs e)
        {
            try
            {
                if (status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssCopy)
                {
                    ////检查重复数据
                    string result = outMarketService.checkDuplicate(this.selSecurity.Value, this.dtpNAVDate.getBeginDateStr, this.mktcls.Value, this.selDvPlat.Value);
                    if (string.IsNullOrEmpty(result))
                    {
                        base.btnSave_Click(sender, e);
                    }
                    else
                    {
                        YssMessageBox.currentForm = this;
                        ClsRetInfo inf = new ClsRetInfo();
                        inf.icon = MessageBoxIcon.Warning;
                        inf.infoTitle = "警告";
                        inf.infoCode = "014";
                        inf.infoContent = "系统中已存在证券代码、行情日期、行情分类、行情来源相同的数据！";
                        YssMessageBox.ShowCommonInfoText(inf);
                    }
                }
                else
                {
                    base.btnSave_Click(sender, e);
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }
    }
}


