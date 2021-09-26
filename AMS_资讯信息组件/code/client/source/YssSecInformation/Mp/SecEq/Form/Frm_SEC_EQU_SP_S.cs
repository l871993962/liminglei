////using YssBaseCls.Fun;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
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
using Yss.KRichEx.AutoFilter.Model;
using Yss.KRichEx.AutoFilter;

////using YssBaseCls.Pojo;

using FAST.Common.Service.Services;
using YssSecInformation.Support.Mp.SecEq.Service;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Mp.SecEq.Pojo;
using FAST.Core.CRUD.Interface;
using YssSecInformation.Support.Sv.Pojo;
////using FAST.Core.BaseControl;


namespace YssSecInformation.Mp.SecEq.Form
{
    /// <summary>
    /// chenbo
    /// 2017-06-24
    /// #42948 资讯信息管理组件化拆分
    /// -------------------------------
    /// 功能简介：证券送配信息设置，负责证券送配信息的增删改查等功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.04
    ///
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：wuwenlan
    /// 修改日期：20101208
    /// 修改简介：实现set具体的功能
    ///   /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：wuwenlan
    /// 修改日期：20110211
    /// 修改简介：删除了一些不需要的
    /// 添加了获取回收站机制的判断
    /// 对选择交易证券是的送配类型进行控制
    /// 
    ///－－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：chenyoulong
    /// 修改日期：20110303
    /// 修改简介：
    /// 1、代码注释完善（包括方法作用注释，逻辑注释，类修改注释）
    /// 2、提示信息统一调整(前台用五个参数YssMessageBox.ShowDyanInformation("初始化窗体出错", ye.Message, "错误信息", MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail)）
    /// 3、需求中内容没有实现功能的调整 
    /// 4、删除不用的代码
    ///  －－－－修改记录－－－－
    /// 当前版本：V4.5.0.5
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         返回对象和控件的showInfo错误直接抛出异常
    /// 
    /// </summary>
    public partial class Frm_SEC_EQU_SP_S : FrmBaseSet
    {
        /// <summary>
        /// 权益信息数据服务对象
        /// </summary>
        private ISecEquService secEquService = null;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_SEC_EQU_SP_S()
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
        ////        this.dtpAfficheDate.setDateTime(DateTime.Now);   // 设置控件显示时间为当前时间
        ////        this.dtpRecordDate.setDateTime(DateTime.Now);   // 设置控件显示时间为当前时间
        ////        this.dtpExRightDate.setDateTime(DateTime.Now);   // 设置控件显示时间为当前时间
        ////    }
        ////    catch (ClsBaseException e)
        ////    {
        ////        ////YssMessageBox.ShowDyanInformation("初始化界面控件报错", e.Message, "信息提示", MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
        ////        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500040", _formFun, status));
        ////        ClsBaseException.DiscardException(e);
        ////    }
        ////}

        /// <summary>
        /// 封装界面元素为pojo对象
        /// </summary>
        /// <returns>由界面元素组成的对象</returns>
        public override ClsBasePojo yssFaceInfoToObj()
        {
            Cls_SEC_EQU clsSecDistribution = null;
            I_SecBase sec = cboHoldSecurity.SelectedItem.DataEntity as I_SecBase;
            try
            {
                clsSecDistribution = new Cls_SEC_EQU();

                // 判断list选中界面是否有选中数据，在修改时获取原数据的值
                if (null != this.yssGetBaseSelTypeItem())
                {
                    clsSecDistribution.OldID = ((Cls_SEC_EQU)this.yssGetBaseSelTypeItem()).ID_D_MP_SEC_EQU;
                    clsSecDistribution.ID_D_MP_SEC_EQU = ((Cls_SEC_EQU)this.yssGetBaseSelTypeItem()).ID_D_MP_SEC_EQU;
                }

                clsSecDistribution.C_EQU_CLS = "SP";     // 类型标识，SP代表送配
                clsSecDistribution.C_DATA_IDF = "H";
                clsSecDistribution.C_SEC_CODE = this.cboHoldSecurity.Value;    // 交易证券
                clsSecDistribution.C_SEC_CODE_TAG = this.cobDistSecurity.Value;     // 送配证券
                clsSecDistribution.N_PRICE_PLAC = this.txtDistPrice.Text;          // 配售价格
                clsSecDistribution.C_DS_CODE = this.cboDistType.Value;             // 送配类型
                clsSecDistribution.C_ZS_CODE = this.cboZSType.Value;               // 折算类型
                clsSecDistribution.N_EQU_RATIO_PT = this.txtPreTaxRatio.Text;      // 税前权益比例
                clsSecDistribution.N_EQU_RATIO_AT = this.txtAfterTaxRatio.Text;    // 税后权益比例
                clsSecDistribution.D_REG = this.dtpRecordDate.getBeginDateStr;     // 缴款截止日
                clsSecDistribution.D_FINAL = this.dtpAfficheDate.getBeginDateStr;  // 登记日
                clsSecDistribution.D_EXR = this.dtpExRightDate.getBeginDateStr;     // 除权日
                clsSecDistribution.C_MKT_CODE = sec.C_MKT_CODE;

            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return clsSecDistribution;
        }

        /// <summary>
        /// ------添加记录-----
        /// 添加人：chenyoulong
        /// 添加时间：20110301
        /// 添加简介：Frm_SEC_EQU_SP_S窗体初始加载事件
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        private void Frm_SEC_EQU_SP_S_Load(object sender, EventArgs e)
        {
            try
            {
                Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
                this.secEquService = ServiceFactory.createService(serviceType) as ISecEquService;
                this.dataService = this.secEquService;
            }
            catch (Exception ye)
            {
                //// YssMessageBox.ShowDyanInformation("窗体初始加载报错", ye.Message, "信息提示", MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFormLoadErr(ye.Message));
            }
        }

      

        /// <summary>
        /// ------添加记录-----
        /// 添加人：chenyoulong
        /// 添加时间：20110303
        /// 添加简介：cboDistType值改变事件
        /// 
        /// edit by Yuntao Lau 2015.10.22 BUG #120763
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        private void cboDistType_SelectedValueChanged(object sender, EventArgs e)
        {
            try
            {
                if (this.status != ClsEnums.StatusSetting.YssBrow)
                {
                    this.txtPreTaxRatio.YssReadOnly = false;
                    this.txtAfterTaxRatio.YssReadOnly = false;

                    // 判断选择的送配类型是否为配股、市值配售、配债时
                    if (null != this.cboDistType.Value && (this.cboDistType.Value.Equals("ZQSP_PG") || this.cboDistType.Value.Equals("ZQSP_PZ")
                        || this.cboDistType.Value.Equals("ZQSP_SZPS") || this.cboDistType.Value.Equals("ZQSP_FCHB_GGT")
                        || this.cboDistType.Value.Equals("ZQSP_XJSG_GGT") || this.cboDistType.Value.Equals("ZQSP_XJGFSG_GGT")
                        || this.cboDistType.Value == "ZQSP_FJYGH"))
                    {
                        // 送配类型是否为配股、市值配售、配债时缴款截止日控件可用
                        this.dtpAfficheDate.Enabled = true;
                        this.txtDistPrice.YssReadOnly = false;  // 送配类型是否为配股、市值配售、配债时配售价格可用
                        ////this.txtDistPrice.KeepDesignValue = false;
                        // 非交易过户
                        if (this.cboDistType.Value == "ZQSP_FJYGH")   
                        {
                            this.txtPreTaxRatio.YssReadOnly = true;
                            this.txtAfterTaxRatio.YssReadOnly = true;
                            this.txtPreTaxRatio.Text = "0";
                            this.txtAfterTaxRatio.Text = "0";
                        }
                    }
                    else
                    {
                        this.dtpRecordDate.Enabled = true;  // 非以上类型时候缴款截止日控件不可用
                        this.dtpAfficheDate.Enabled = false;
                        this.dtpAfficheDate.setDateTime(this.dtpExRightDate.getBeginDate);
                        this.txtDistPrice.YssReadOnly = true;  // 送配类型是否为配股、市值配售、配债时配售价格不可用
                        ////this.txtDistPrice.KeepDesignValue = true;
                        this.txtDistPrice.Text = "0";
                    }
                }

                    ////if (null != this.cboDistType.Value && this.cboDistType.Value.Equals("ZQSP_FCHB_GGT"))
                    ////{
                    ////    this.cell9.Text = "第一次转换比例：";
                    ////    this.cell11.Text = "   第二次转换比例：";
                    ////    this.cell14.Text = "第三次转换比例：";

                    ////    this.cell16.Text = "   单柜交易首日：";
                    ////    this.cell19.Text = "并柜交易首日：";
                    ////    this.cell21.Text = "   新代码交易首日：";

                    ////    this.cell10.InnerControl = null;
                    ////    this.cell10.InnerControl = this.txtFirstRatio;
                    ////    this.txtDistPrice.Visible = false;
                    ////    this.txtFirstRatio.Visible = true;
                    ////}
                    ////else
                    ////{
                    ////    this.cell9.Text = "配售价格：";
                    ////    this.cell11.Text = "   税前权益比例：";
                    ////    this.cell14.Text = "税后权益比例：";

                    ////    this.cell16.Text = "   登记日期：";
                    ////    this.cell19.Text = "缴款截止日：";
                    ////    this.cell21.Text = "   除权日期：";

                    ////    this.cell10.InnerControl = null;
                    ////    this.cell10.InnerControl = this.txtDistPrice;
                    ////    this.txtDistPrice.Visible = true;
                    ////    this.txtFirstRatio.Visible = false;
                    ////}

                    if (null != this.cboDistType.Value &&
                        (this.cboDistType.Value.Equals("ZQSP_XJGFSG_GGT")
                        || this.cboDistType.Value.Equals("ZQSP_GFSG_GGT")))
                    {
                        this.cell11.Text = "   支付比例：";
                        this.txtAfterTaxRatio.YssReadOnly = true;
                        ////if (this.status != ClsEnums.StatusSetting.YssBrow)
                        ////{
                        ////    this.txtAfterTaxRatio.KeepDesignValue = true;
                        ////}

                        this.txtAfterTaxRatio.Text = "0";
                    }
                    else if (null == this.cboDistType.Value || !this.cboDistType.Value.Equals("ZQSP_FCHB_GGT"))
                    {
                        this.cell11.Text = "   税前权益比例：";
                        ////this.txtAfterTaxRatio.YssReadOnly = this.status == ClsEnums.StatusSetting.YssBrow;
                        ////this.txtAfterTaxRatio.KeepDesignValue = false;
                    }

                    /*add by liyongjun 20150909 STORY23795北京海丰：增加证券变动库Bdlx字段对00B的处理 对非交易过户的处理 */
                    if (null != this.cboDistType.Value && this.cboDistType.Value.Equals("ZQSP_FJYGH"))
                    {
                        this.cell19.Text = "";
                        this.cell20.InnerControl = null;
                        this.cell16.Text = "   变动日期：";
                        this.cell17.InnerControl = null;
                        this.cell17.InnerControl = this.dtpExRightDate;
                        this.cell21.Text = "";
                        this.cell22.InnerControl = null;
                        if (this.cboHoldSecurity.Value != this.cobDistSecurity.Value)
                        {
                            this.cobDistSecurity.Value = this.cboHoldSecurity.Value;
                        }
                    }

                    //// BUG #136365 港股通分拆合并hk_tzxx、hk_qtsl取数有误
                    //// 原有控制混乱，分拆合并变更后界面还会错误，因此调整位置
                    //// xiaozhilong 20160811
                    if (null != this.cboDistType.Value && this.cboDistType.Value.Equals("ZQSP_FCHB_GGT"))
                    {
                        this.cell9.Text = "第一次转换比例：";
                        this.cell11.Text = "   第二次转换比例：";
                        this.cell14.Text = "第三次转换比例：";

                        this.cell16.Text = "   单柜交易首日：";
                        this.cell19.Text = "并柜交易首日：";
                        this.cell21.Text = "   新代码交易首日：";

                        this.cell10.InnerControl = null;
                        this.cell10.InnerControl = this.txtFirstRatio;
                        this.txtDistPrice.Visible = false;
                        this.txtFirstRatio.Visible = true;
                    }
                    else
                    {
                        /*by guohui 20161111 cell16、19、20等值重新被定义，影响分叉合并类型的显示  BUG144814【紧急】_长江养老_港股通证券送配信息-分拆合并界面显示问题*/
                        ////this.cell16.Text = "   登记日期：";
                        this.cell17.InnerControl = null;
                        this.cell17.InnerControl = this.dtpRecordDate;
                        ////this.cell19.Text = "缴款截止日：";
                        this.cell20.InnerControl = this.dtpAfficheDate;
                        ////this.cell21.Text = "   除权日期：";
                        this.cell22.InnerControl = this.dtpExRightDate;
                    }

                    //// 送配类型为拆分时才显示折算类型
                    if (null != this.cboDistType.Value && this.cboDistType.Value.Equals("ZQSP_CF"))
                    {
                        this.row10.Visible = true;
                        this.cboZSType.YssIsMust = true;
                    }
                    else
                    {
                        this.row10.Visible = false;
                        this.cboZSType.YssIsMust = false;
                   ////     this.cboZSType.Value = " ";
                        this.cboZSType.Text = " ";
                    }

                    this.tbMain.Refresh();
            }
            catch (Exception)
            {
                ////YssMessageBox.ShowDyanInformation("送配类型值改变事件", ye.Message, "错误信息", MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
                ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("001", _formFun, ClsEnums.StatusSetting.YssSave));
                TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                transferErrorMessage.MESSAGESLINK = new List<string>();
                /* 组装提示信息对象ErrorMessage */
                string errorMess = ClsRetInfoDealer.getExtWarns("001", _formFun, ClsEnums.StatusSetting.YssSave);
                transferErrorMessage.MESSAGESLINK.Add(errorMess);
                throw new TransferException(JsonUtil.toJson(transferErrorMessage));
            }
        }

        /// <summary>
        /// 证券代码值改变事件处理获取不同的送配类型
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        private void cboHoldSecurity_SelectedValueChanged(object sender, EventArgs e)
        {
            SecBase sec = null;
            try
            {
                ////add by liyanjun 20140827 bug99986 
                cobDistSecurity.Value = cboHoldSecurity.Value;
                if (this.status != ClsEnums.StatusSetting.YssBrow)
                {
                    if (this.cboHoldSecurity.Value != null)
                    {
                        sec = this.cboHoldSecurity.SelectedItem.DataEntity as SecBase;
                        if (sec.C_DA_CODE.Contains("GP"))
                        {
                            this.cboDistType.QueryCond = "ZQSP_SG,ZQSP_PG,ZQSP_SZPS,ZQSP_GFDJ";
                            this.cboDistType.QueryType = "Key";
                            this.cboDistType.Text = " ";                   // liuping  2011-03-29  BUG #1501 证券送配BUG
                        }
                        else if (sec.C_DA_CODE.Contains("QZ"))
                        {
                            this.cboDistType.QueryCond = "ZQSP_CWSP,ZQSP_PWSP,ZQSP_KFLZSP";
                            this.cboDistType.QueryType = "Key";
                            this.cboDistType.Text = " ";                  // liuping  2011-03-29  BUG #1501 证券送配BUG
                        }
                        else if (sec.C_DA_CODE.Contains("ZQ"))
                        {
                            this.cboDistType.QueryCond = "ZQSP_PZ";
                            this.cboDistType.QueryType = "Key";
                            this.cboDistType.Text = " ";                  // liuping  2011-03-29  BUG #1501 证券送配BUG
                        }
                        else
                        {
                            this.cboDistType.QueryCond = "ZQSP";
                            this.cboDistType.QueryType = "CacheType";
                            this.cboDistType.Text = " ";                  // liuping  2011-03-29  BUG #1501 证券送配BUG
                        }

                    }
                }
            }
            catch (Exception ex)
            {
                //// YssMessageBox.ShowDyanInformation("根据证券的品种属性获取送配类型出现异常", ex.Message, MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtError("001", _formFun, status));
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// YssOnBeforeSaveClick
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void OnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        {
            // DateTime date1 = DateTime.Parse(this.dtpAfficheDate.getBeginDateStr);  // 登记日
            // edit by yh 2012-06-19 ，获取登记日日期，使用的界面控件名字错误，修改之
            // edit by liyongjun 20150911 非交易过户不存在登记日，只存在除权日 STORY23795北京海丰：增加证券变动库Bdlx字段对00B的处理        
            if (this.cboDistType.Value != "ZQSP_FJYGH")
            {
                DateTime date1 = DateTime.Parse(this.dtpRecordDate.getBeginDateStr);  // 登记日
                DateTime date2 = DateTime.Parse(this.dtpExRightDate.getBeginDateStr);     // 除权日
                ////BUG #140494 【汇添富基金】-证券送配信息页面，有除权日期不能小于登记日期限制。
                ////证券送配信息页面，有除权日期不能小于登记日期限制，但境外除权通常都是先除权 再登记
                /*
                if (date2 < date1)
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("005", this._formFun, ClsEnums.StatusSetting.YssAdd));

                    e.IsCancel = true;
                }
                */
            }
        }

        /// <summary>
        /// 除权日控件值改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void dtpExRightDate_FirstdateTimeInputValueChanged(object sender, EventArgs e)
        {
            try
            {
                if (null == this.cboDistType.Value)
                {
                    return;
                }

                if (this.status != ClsEnums.StatusSetting.YssBrow)
                {
                    // 判断选择的送配类型是否为配股、市值配售、配债时
                    if (!(this.cboDistType.Value.Equals("ZQSP_PG") || this.cboDistType.Value.Equals("ZQSP_PZ") || this.cboDistType.Value.Equals("ZQSP_SZPS")))
                    {
                        this.dtpAfficheDate.setDateTime(this.dtpExRightDate.getBeginDate);
                    }
                }
            }
            catch (Exception)
            {
                ////YssMessageBox.ShowDyanInformation("送配类型值改变事件", ye.Message, "错误信息", MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
                ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("001", _formFun, ClsEnums.StatusSetting.YssSave));
                TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                transferErrorMessage.MESSAGESLINK = new List<string>();
                /* 组装提示信息对象ErrorMessage */
                string errorMess = ClsRetInfoDealer.getExtWarns("001", _formFun, ClsEnums.StatusSetting.YssSave);
                transferErrorMessage.MESSAGESLINK.Add(errorMess);
                throw new TransferException(JsonUtil.toJson(transferErrorMessage));
            }
        }

        /// <summary>
        /// 控件控制
        /// ADD BY ZXL BUG #82814
        /// edit by Yuntao Lau 2015.10.22 BUG #120763
        /// </summary>
        protected override void YssChangeControlState()
        {
            base.YssChangeControlState();
            if (this.status != ClsEnums.StatusSetting.YssBrow)
            {
                // 判断选择的送配类型是否为配股、市值配售、配债时
                if (null != this.cboDistType.Value && (this.cboDistType.Value.Equals("ZQSP_PG") || this.cboDistType.Value.Equals("ZQSP_PZ") || this.cboDistType.Value.Equals("ZQSP_SZPS") || this.cboDistType.Value == "ZQSP_FJYGH"))
                {
                    // 送配类型是否为配股、市值配售、配债时缴款截止日控件可用
                    this.dtpAfficheDate.Enabled = true;
                    this.txtDistPrice.YssReadOnly = false;  // 送配类型是否为配股、市值配售、配债时配售价格可用
                    ////this.txtDistPrice.KeepDesignValue = false;
                }
                else
                {
                    this.dtpRecordDate.Enabled = true;  // 非以上类型时候缴款截止日控件不可用
                    this.dtpAfficheDate.Enabled = false;
                    this.dtpAfficheDate.setDateTime(this.dtpExRightDate.getBeginDate);
                    this.txtDistPrice.YssReadOnly = true;  // 送配类型是否为配股、市值配售、配债时配售价格不可用
                    ////this.txtDistPrice.KeepDesignValue = true;
                    this.txtDistPrice.Text = "0";
                }

                ////送配类型为拆分时才显示折算类型
                if (null != this.cboDistType.Value && this.cboDistType.Value.Equals("ZQSP_CF"))
                {
                    this.row10.Visible = true;
                }
                else
                {
                    this.row10.Visible = false;
                }

                // txtAfterTaxRatio 在不同送配类型下，值不同
                if (this.status != ClsEnums.StatusSetting.YssBrow)
                {
                    if (null != this.cboDistType.Value && (this.cboDistType.Value.Equals("ZQSP_FJYGH") || this.cboDistType.Value.Equals("ZQSP_XJGFSG_GGT")
                            || this.cboDistType.Value.Equals("ZQSP_GFSG_GGT")))
                    {
                        this.txtAfterTaxRatio.YssReadOnly = true;
                    }
                    else
                    {
                        this.txtAfterTaxRatio.YssReadOnly = false;
                    }

                    // 只有当cboDistType.Value = ZQSP_FJYGH 时， this.txtPreTaxRatio.YssReadOnly = true
                    if (null != this.cboDistType.Value && (this.cboDistType.Value.Equals("ZQSP_FJYGH")))
                    {
                        this.txtPreTaxRatio.YssReadOnly = true;
                    }
                    else
                    {
                        this.txtPreTaxRatio.YssReadOnly = false;
                    }

                    //// modified by HeLiang 2017-05-31 初始化数据库功能测试BUG修改
                    ////“配售价格”和“第一次转换比例”公用的一个控件，所以在设置可用不可用的时候不能只判断“配送类型”为“港股通_分拆合并”的情况，应该放开判断
                    this.txtFirstRatio.YssReadOnly = false;
                    ////if (null != this.cboDistType.Value && this.cboDistType.Value.Equals("ZQSP_FCHB_GGT"))
                    ////{
                    ////    this.txtFirstRatio.YssReadOnly = false;
                    ////}
                }
            }
            else
            {
                this.txtAfterTaxRatio.YssReadOnly = true;
                this.txtPreTaxRatio.YssReadOnly = true;
                this.txtDistPrice.YssReadOnly = true;
                //// modified by HeLiang 2017-05-31 初始化数据库功能测试BUG修改
                ////“配售价格”和“第一次转换比例”公用的一个控件，所以在设置可用不可用的时候不能只判断“配送类型”为“港股通_分拆合并”的情况，应该放开判断
                this.txtFirstRatio.YssReadOnly = true;
                ////if (null != this.cboDistType.Value && this.cboDistType.Value.Equals("ZQSP_FCHB_GGT"))
                ////{
                ////    this.txtFirstRatio.YssReadOnly = true;
                ////}
            }
        }

        /// <summary>
        /// 窗体LOAD事件
        /// edit by Yuntao Lau 2015.10.22 BUG #120763
        /// </summary>
        /// <param name="pojo">Pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                SecEqu clsSecDistribution = this.yssGetBaseSelTypeItemMVC() as SecEqu;
                if (null != clsSecDistribution)
                {
                    this.cboHoldSecurity.Value = clsSecDistribution.C_SEC_CODE; //// 交易证券
                    this.cobDistSecurity.Value = clsSecDistribution.C_SEC_CODE_TAG; //// 送配证券
                    

                    if ("ZQSP_FCHB_GGT".Equals(clsSecDistribution.C_DS_CODE))
                    {
                        this.txtFirstRatio.Text = clsSecDistribution.N_EQU_RATIO_PT; //// 第一次转换比例
                        this.txtPreTaxRatio.Text = clsSecDistribution.N_PRICE_PLAC; //// 第二次转换比例
                        ////this.dtpRecordDate.setDateTime(Convert.ToDateTime(clsSecDistribution.D_EXR)); //// 除权日
                        ////this.dtpExRightDate.setDateTime(Convert.ToDateTime(clsSecDistribution.D_REG)); //// 登记日
                        //// BUG #142523 add by chenwen
                        this.dtpRecordDate.setDateTime(Convert.ToDateTime(clsSecDistribution.D_REG)); //// 登记日
                        this.dtpExRightDate.setDateTime(Convert.ToDateTime(clsSecDistribution.D_EXR)); //// 除权日
                    }
                    else
                    {
                        this.txtFirstRatio.Text = clsSecDistribution.N_PRICE_PLAC; //// 配售价格
                        this.txtPreTaxRatio.Text = clsSecDistribution.N_EQU_RATIO_PT; //// 税前权益比例
                        this.dtpRecordDate.setDateTime(Convert.ToDateTime(clsSecDistribution.D_REG));   //// 登记日
                        this.dtpExRightDate.setDateTime(Convert.ToDateTime(clsSecDistribution.D_EXR));   //// 除权日
                    }

                    this.dtpAfficheDate.setDateTime(Convert.ToDateTime(clsSecDistribution.D_FINAL)); //// 缴款截止日
                    ////edit by HuangJin 2016.10.21 BUG #142938 港股通接口清算进来的派息信息和送配信息中配售价格和支付比例不能显示数据
                    this.txtPreTaxRatio.Text = clsSecDistribution.N_EQU_RATIO_PT; ////税前权益比例   
                    this.txtAfterTaxRatio.Text = clsSecDistribution.N_EQU_RATIO_AT; //// 税后权益比例
                    this.cboDistType.Value = clsSecDistribution.C_DS_CODE; //// 送配类型
                    this.cboZSType.Value = clsSecDistribution.C_ZS_CODE;                ////折算类型
                    ////送配类型为拆分时才显示折算类型
                    if (null != this.cboDistType.Value && this.cboDistType.Value.Equals("ZQSP_CF"))
                    {
                        this.row10.Visible = true;
                    }
                    else
                    {
                        this.row10.Visible = false;
                    }
                }

                //// txtAfterTaxRatio 在不同送配类型下，值不同
                if (this.status != ClsEnums.StatusSetting.YssBrow)
                {
                    if (null != this.cboDistType.Value && (this.cboDistType.Value.Equals("ZQSP_FJYGH") || this.cboDistType.Value.Equals("ZQSP_XJGFSG_GGT")
                            || this.cboDistType.Value.Equals("ZQSP_GFSG_GGT")))
                    {
                        this.txtAfterTaxRatio.YssReadOnly = true;
                    }
                    else
                    {
                        this.txtAfterTaxRatio.YssReadOnly = false;
                    }

                    // 只有当cboDistType.Value = ZQSP_FJYGH 时， this.txtPreTaxRatio.YssReadOnly = true
                    if (null != this.cboDistType.Value && (this.cboDistType.Value.Equals("ZQSP_FJYGH")))
                    {
                        this.txtPreTaxRatio.YssReadOnly = true;
                    }
                    else
                    {
                        this.txtPreTaxRatio.YssReadOnly = false;
                    }

                    if (null != this.cboDistType.Value && this.cboDistType.Value.Equals("ZQSP_FCHB_GGT"))
                    {
                        this.txtFirstRatio.YssReadOnly = false;
                    }
                }

                if (this.status == ClsEnums.StatusSetting.YssBrow)
                {
                    this.txtAfterTaxRatio.YssReadOnly = true;
                    this.txtPreTaxRatio.YssReadOnly = true;
                    this.txtDistPrice.YssReadOnly = true;
                    ////edit by YeLeiLei BUG #143955
                    this.txtFirstRatio.YssReadOnly = true;
                }

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
            SecEqu clsSecDistribution = null;
            SecBase sec = cboHoldSecurity.SelectedItem.DataEntity as SecBase;
            try
            {
                clsSecDistribution = new SecEqu();
                clsSecDistribution.C_EQU_CLS = "SP"; //// 类型标识，SP代表送配
                clsSecDistribution.C_DATA_IDF = "H";
                clsSecDistribution.C_SEC_CODE = this.cboHoldSecurity.Value; //// 交易证券
                clsSecDistribution.C_SEC_CODE_TAG = this.cobDistSecurity.Value; //// 送配证券
                clsSecDistribution.C_DS_CODE = this.cboDistType.Value; //// 送配类型
                clsSecDistribution.C_ZS_CODE = this.cboZSType.Value;        ////折算类型
                ////modified by liyanjun 20140820 BUG #99525 SET界面没有千分符 
                ////clsSecDistribution.N_EQU_RATIO_PT = Convert.ToString(Convert.ToDecimal(this.txtPreTaxRatio.Text)); //// 税前权益比例
                clsSecDistribution.N_EQU_RATIO_AT = Convert.ToString(Convert.ToDecimal(this.txtAfterTaxRatio.Text)); //// 税后权益比例
                clsSecDistribution.D_FINAL = this.dtpAfficheDate.getBeginDateStr; //// 缴款截止日
                
                if ("ZQSP_FCHB_GGT".Equals(clsSecDistribution.C_DS_CODE))
                {
                    clsSecDistribution.N_EQU_RATIO_PT = Convert.ToString(Convert.ToDecimal(this.txtFirstRatio.Text)); //// 第一次转换比例
                    clsSecDistribution.N_PRICE_PLAC = Convert.ToString(Convert.ToDecimal(this.txtPreTaxRatio.Text)); //// 第二次转换比例
                    ////clsSecDistribution.D_EXR = this.dtpRecordDate.getBeginDateStr; //// 除权日
                    ////clsSecDistribution.D_REG = this.dtpExRightDate.getBeginDateStr; //// 登记日
                    //// BUG #142523 add by chenwen
                    clsSecDistribution.D_REG = this.dtpRecordDate.getBeginDateStr; //// 登记日
                    clsSecDistribution.D_EXR = this.dtpExRightDate.getBeginDateStr; //// 除权日
                }
                else
                {
                    clsSecDistribution.N_PRICE_PLAC = this.txtFirstRatio.Text; //// 配售价格
                    clsSecDistribution.N_EQU_RATIO_PT = Convert.ToString(Convert.ToDecimal(this.txtPreTaxRatio.Text)); //// 税前权益比例
                    clsSecDistribution.D_REG = this.dtpRecordDate.getBeginDateStr; //// 登记日
                    clsSecDistribution.D_EXR = this.dtpExRightDate.getBeginDateStr; //// 除权日
                }

                clsSecDistribution.C_MKT_CODE = sec.C_MKT_CODE;

            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return clsSecDistribution;
        }

        #region oldCode
        /// <summary>
        /// 证券代码值改变事件处理获取不同的送配类型
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        ////private void cboHoldSecurity_TextChanged(object sender, EventArgs e)
        ////{
        ////    I_SecBase sec = null;
        ////    try
        ////    {
        ////        if (this.status != ClsEnums.StatusSetting.YssBrow)
        ////        {
        ////            if (this.cboHoldSecurity.Value != null)
        ////            {
        ////                sec = this.cboHoldSecurity.getKeyCollection()[this.cboHoldSecurity.Value] as I_SecBase;
        ////                if (sec.C_DA_CODE.Contains("GP"))
        ////                {
        ////                    this.cboDistType.QueryCond = " where C_DT_CODE in('ZQSP_SG','ZQSP_PG','ZQSP_SZPS')";
        ////                    this.cboDistType.Text = " ";                   // liuping  2011-03-29  BUG #1501 证券送配BUG
        ////                }
        ////                else if (sec.C_DA_CODE.Contains("QZ"))
        ////                {
        ////                    this.cboDistType.QueryCond = " where C_DT_CODE in('ZQSP_CWSP','ZQSP_PWSP')";
        ////                    this.cboDistType.Text = " ";                  // liuping  2011-03-29  BUG #1501 证券送配BUG
        ////                }
        ////                else
        ////                {
        ////                    this.cboDistType.QueryCond = " where C_BUSI_TYPE = 'ZQSP'";
        ////                    this.cboDistType.Text = " ";                  // liuping  2011-03-29  BUG #1501 证券送配BUG
        ////                }

        ////            }
        ////        }
        ////    }
        ////    catch (Exception ex)
        ////    {
        ////        YssMessageBox.ShowDyanInformation("根据证券的品种属性获取送配类型出现异常", ex.Message, MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
        ////    }
        ////}

        /// <summary>
        /// 校验【登记日期】、【缴款截止日】、【除权日期】必输项   add by caozhonghu  2011.3.20
        /// </summary>
        ////private void validateDate()
        ////{
        ////    // liuping  2011-03-29  BUG #1501 证券送配BUG
        ////    if (this.dtpAfficheDate.getBeginDateStr.Length == 0)
        ////    {
        ////        ////throw new ClsBaseException("登记日期不能为空！");
        ////        throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("002", _formFun, ClsEnums.StatusSetting.YssSave));
        ////    }

        ////    if (this.dtpRecordDate.getBeginDateStr.Length == 0)
        ////    {
        ////        ////throw new ClsBaseException("缴款截止日期不能为空！");
        ////        throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("003", _formFun, ClsEnums.StatusSetting.YssSave));
        ////    }

        ////    if (this.dtpExRightDate.getBeginDateStr.Length == 0)
        ////    {
        ////        ////throw new ClsBaseException("除权日期不能为空！");
        ////        throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("004", _formFun, ClsEnums.StatusSetting.YssSave));
        ////    }
        ////}


        /// <summary>
        /// 保存操作前的事件处理  add by caozhonghu  2011.3.20
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        ////private void Frm_SEC_EQU_SP_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        ////{
        ////    validateDate();          
        ////}
        #endregion 

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
                    this.cboHoldSecurity.YssReadOnly = true;
                    this.cboHoldSecurity.Value = (frmDetailData.MainDataPojo as SecBase).C_SEC_CODE;
                }
            }
        }
    }
}


