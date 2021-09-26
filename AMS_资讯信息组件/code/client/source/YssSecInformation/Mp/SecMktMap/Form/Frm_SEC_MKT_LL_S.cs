////using YssBaseCls.Fun;
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

////using YssBaseCls.Pojo;

////using YssData.Pojo.Mp;

////using YssData.Service.Mp;



////using YssBaseCls.Interface;
using FAST.Common.Service.Services;
////using YssSecInformation.Support.Fun;
using YssInformation.Support.Pojo;
using YssInformation.Support.Fun;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Mp.SecMktMap.Service;
using YssSecInformation.Support.Mp.SecMktMap.Pojo;
using FAST.Core.CRUD.Interface;


////using YssPojos.Data.Mp;

////namespace YssData.Form.Mp
namespace YssSecInformation.Mp.SecMktMap.Form
{
    /// <summary>
    /// 功能简介：存款利率数据设置，负责存款利率数据的增删改查等功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2011.01.25
    ///
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：zhuangyuchen 
    /// 修改日期：2011-1-27
    /// 修改简介：实现功能
    /// 
    /// 
    /// /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：zhuangyuchen
    /// 修改日期： 2011-2-23
    /// 修改简介：
    /// 1:增加回收站开启关闭机制
    /// 2:删除添加修改等操作的操作成功信息
    /// 3：添加注释
    /// 4：删除初始化加载下拉框的代码，改成在控件的属性中设置
    /// 6：修改出错的提示信息
    /// 7:去掉增删改查成功的提示信息，由基类来提供
    /// 8：增加传到后台去的列头和窗体菜单
    ///  －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         返回对象和控件的showInfo错误直接抛出异常
    /// </summary>
    public partial class Frm_SEC_MKT_LL_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private ICkMktService myService = null;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_SEC_MKT_LL_S()
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
        /// 初始化界面控件
        /// </summary>
        ////public override void yssInitCtlAttr()
        ////{
        ////    try
        ////    {
        ////        this.dtpAdjustDate.setDateTime(DateTime.Now);    // 设置控件显示时间为当前时间
              
        ////    }
        ////    catch (Exception ye)
        ////    {
        ////        ////YssMessageBox.ShowDyanInformation("获取市场代码出错", ye.Message, "错误信息", MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
        ////        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtError("001", _formFun, status));
        ////        ClsBaseException.DiscardException(ye);
        ////    }
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
                if (null != this.yssGetBaseSelTypeItem() && this.status == ClsEnums.StatusSetting.YssEdit)
                {
                    clsExRatePrice.OldC_SEC_CODE = ((Cls_SEC_MKT)this.yssGetBaseSelTypeItem()).C_SEC_CODE;
                    clsExRatePrice.OldD_MKT = ((Cls_SEC_MKT)this.yssGetBaseSelTypeItem()).D_MKT;
                    clsExRatePrice.OldC_DV_PLAT = ((Cls_SEC_MKT)this.yssGetBaseSelTypeItem()).C_DV_PLAT;
                }

                clsExRatePrice.C_SEC_CODE = this.selSecurity.Value;    // 利率品种   
                clsExRatePrice.D_MKT = this.dtpAdjustDate.getBeginDate.ToString("yyyy-MM-dd");  // 调息日期
                //// 因为有百分号，所以实际的数值除以100 add by yh 2012-08-17
                ////clsExRatePrice.N_PRICE_NEW = YssCore.Fun.ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtSavingRate.Text);     // 利率
                clsExRatePrice.N_PRICE_NEW = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, ClsFunction.div(this.txtSavingRate.Text, "100"));     // 利率
                clsExRatePrice.BeanId = "exratepriceadmin";    // 不同调用者的辨别标志
                clsExRatePrice.C_MKT_CLS = "DP";      // 存款利率
                clsExRatePrice.C_DV_PLAT = " ";

            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return clsExRatePrice;
        }

        /// <summary>
        /// 保存操作前的事件处理  add by chenzhong  2011.4.28
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        /// 2011-04-28 chenzhong bug 1791 序号2
        private void Frm_SEC_MKT_LL_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        {
            validateDate();
        }


        /// <summary>
        /// 校验【调息日期】必输项   add by chenzhong  2011.4.28
        /// </summary>
        private void validateDate()
        {
            if (this.dtpAdjustDate.getBeginDateStr == "")
            {
                ////throw new ClsBaseException("调息日不能为空！");
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
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_MKT_LL_S_Load(object sender, EventArgs e)
        {
            this.getServiceInstance();
        }

        /// <summary>
        /// 创建窗体服务
        /// </summary>
        private void getServiceInstance()
        {
            if (null == this.dataService)
            {
                Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
                this.myService = ServiceFactory.createService(serviceType) as ICkMktService;
                this.dataService = this.myService;
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
                clsExRatePrice.C_SEC_CODE = this.selSecurity.Value;    // 利率品种   
                clsExRatePrice.D_MKT = this.dtpAdjustDate.getBeginDate.ToString("yyyy-MM-dd");  // 调息日期
                clsExRatePrice.C_HQZT_CODE = this.cboHqzt.Value == null ? "HQZT_ZCJY" : this.cboHqzt.Value; // 行情状态
                //// 因为有百分号，所以实际的数值除以100 add by yh 2012-08-17
                ////clsExRatePrice.N_PRICE_NEW = YssCore.Fun.ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.txtSavingRate.Text);     // 利率
                clsExRatePrice.N_PRICE_NEW = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, ClsFunction.div(this.txtSavingRate.Text, "100"));     // 利率
                clsExRatePrice.N_PRICE_CLOSE = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, ClsFunction.div(this.txtSavingRate.Text, "100"));     // 利率同时保持到“收盘价”字段，adder:tangshifeng
                ////clsExRatePrice.BeanId = "exratepriceadmin";    // 不同调用者的辨别标志
                clsExRatePrice.C_MKT_CLS = "DP";      // 存款利率
                clsExRatePrice.C_DV_PLAT = " ";
                clsExRatePrice.D_PUB = clsExRatePrice.D_MKT;
                clsExRatePrice.C_DATA_IDF = "H";

                ////add by liuxiang 2013/9/10 STORY #4101
                if (status == FAST.Core.Context.ClsEnums.StatusSetting.YssEdit)
                {
                    SecMkt oldTrade = this.frmBaseViewList.tbMain.SelectedRow.Tag as SecMkt;
                    if (ClsBizCons.Z.Equals(oldTrade.C_DATA_IDF))
                    {
                        clsExRatePrice.C_DATA_IDF = oldTrade.C_DATA_IDF;
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
        /// 展示对象属性到窗体
        /// </summary>
        /// <param name="pojo">Pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                SecMkt secMKETValue = (SecMkt)this.yssGetBaseSelTypeItemMVC();
                if (secMKETValue != null)
                {
                    ////this.selSecurity.Text = "";
                    this.selSecurity.Value = secMKETValue.C_SEC_CODE;      // 存款品种
                    this.dtpAdjustDate.setDateTime(Convert.ToDateTime(secMKETValue.D_MKT)); // 调息日期
                    // add by yh 2012-08-17 因为有百分号显示，所以把实际的值乘以100 
                    this.txtSavingRate.Text = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.NumberToString, ClsFunction.mul(secMKETValue.N_PRICE_NEW, "100"));           // 存款利率
                    this.cboHqzt.Value = secMKETValue.C_HQZT_CODE; // 行情状态
                }

            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 锁定特殊控件的状态
        /// </summary>
        protected override void OnLockSpecialControlState()
        {
            base.OnLockSpecialControlState();

            ////List界面被关联内嵌至其他界面时，证券不能被更改
            if (this.frmBaseViewList != null && this.frmBaseViewList is IFormDetailData)
            {
                IFormDetailData frmDetailData = this.frmBaseViewList as IFormDetailData;
                if (frmDetailData.MainDataPojo != null)
                {
                    this.selSecurity.YssReadOnly = true;
                    this.selSecurity.Value = (frmDetailData.MainDataPojo as SecBase).C_SEC_CODE;
                }
            }
        }

    }
}


