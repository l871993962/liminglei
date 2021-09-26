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

using YssInformation.Support.Bi.CuryPair.Pojo;




using YssInformation.Support.Bi.CuryPair.Service;


namespace YssInformation.Bi.CuryPair.Form
{
    /// <summary>
    /// 功能简介：货币对信息设置界面，显示数据和实现数据的增加，删除，修改，审核，反审核功能
    /// 创建版本：V4.5.0.1
    /// 创建人： caozhonghu
    /// 创建日期： 2011.03.07
    /// </summary>
    /// ============================================
    /// <summary>
    /// 功能简介：货币对信息设置界面，显示数据和实现数据的增加，删除，修改，审核，反审核功能
    /// 创建版本：V4.5.0.2
    /// 创建人： zhuangyuchen
    /// 创建日期： 2011.03.09
    /// 简介：实现代码
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         
    /// </summary>
    public partial class Frm_CURY_PAIR_HBD_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private ICuryPairService curyPairHdbService = null;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_CURY_PAIR_HBD_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 初始化时设置为只读 BUG #95489
        /// </summary>
        public override void initControlStat()
        {
            base.initControlStat();
            this.txtCuryPairCode.YssReadOnly = true;
            this.txtHBDName.YssReadOnly = true;
        }

        /// <summary>
        /// 计价货币下拉框值改变时触发的事件的处理.
        /// 证券内码=基准货币内码加上计价货币的内码.
        /// 货币对的名称=基准货币的名称+计价货币的名称.
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        private void cboValCury_SelectedValueChanged(object sender, EventArgs e)
        {
            if (status != ClsEnums.StatusSetting.YssBrow)
            {
                ////如果基准货币和计价货币下拉框里面的值不为空
                if (this.cboValCury.Value != null && this.cboBasicCury.Value != null)
                {
                    ////证券内码的值等于基准货币的内码加计价货币的内码
                    this.txtCuryPairCode.Text = this.cboBasicCury.Value + "/"  + this.cboValCury.Value;   
                    ////货币对的名称等于基准货币的下拉框选中的名称+“/” +计价货币的下拉框选中的名称
                    ////得到下拉框的文本格式为代码+“_”+"名称"
                    ////string basicCuryName = Regex.Split(this.cboBasicCury.Text, "_")[1];
                    ////string valcuryName = Regex.Split(this.cboValCury.Text, "_")[1];
                    string basicCuryName = this.cboBasicCury.Text;
                    string valcuryName = this.cboValCury.Text;
                    this.txtHBDName.Text = basicCuryName + "/" + valcuryName;
                }
            }

        }

        /// <summary>
        /// 基准货币下拉框值更改事件.
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        private void cboBasicCury_SelectedValueChanged(object sender, EventArgs e)
        {
            if (status != ClsEnums.StatusSetting.YssBrow)
            {
                ////如果基准货币和计价货币下拉框里面的值不为空
                if (this.cboValCury.Value != null && this.cboBasicCury.Value != null)
                {
                    ////证券内码的值等于基准货币的内码加计价货币的内码
                    this.txtCuryPairCode.Text = this.cboBasicCury.Value + "/" + this.cboValCury.Value;
                    ////货币对的名称等于基准货币的下拉框选中的名称+“/” +计价货币的下拉框选中的名称
                    ////得到下拉框的文本格式为代码+“_”+"名称"
                    ////string basicCuryName = Regex.Split(this.cboBasicCury.Text, "_")[1];
                    ////string valcuryName = Regex.Split(this.cboValCury.Text, "_")[1];
                    string basicCuryName = this.cboBasicCury.Text;
                    string valcuryName = this.cboValCury.Text;
                    this.txtHBDName.Text = basicCuryName + "/" + valcuryName;
                }
            }
        }

        /// <summary>
        /// 基准货币下拉框点击之前事件，处理获取当前计价货币下的计价货币代码.
        /// 修改人：liuping.
        /// 修改时间：2011-03-18        BUG #1488 货币对设置BUG.
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        private void cboBasicCury_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            try
            {
                if (this.cboValCury.Value != null && this.cboValCury.Value.Length > 0)
                {
                    this.cboBasicCury.QueryCond = " where a.C_DC_CODE <> '" + this.cboValCury.Value + "'";
                    this.cboBasicCury.RequestEveryTime = true;
                }
                else
                {
                    this.cboBasicCury.QueryCond = "";
                }

            }
            catch (Exception ex)
            {
                ClsBaseException.DiscardException(ex);
                ////YssMessageBox.ShowDyanInformation("获取基准货币数据出现异常", ex.Message, MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, ClsEnums.StatusSetting.YssSave));
            }
        }


        /// <summary>
        /// 计价货币下拉框点击之前事件，处理获取当前基准货币下的基准货币代码.
        /// 修改人：liuping.
        /// 修改时间：2011-03-18             BUG #1488 货币对设置BUG.
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        private void cboValCury_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            try
            {
                if (this.cboBasicCury.Value != null && this.cboBasicCury.Value.Length > 0)
                {
                    this.cboValCury.QueryCond = " where a.C_DC_CODE <> '" + this.cboBasicCury.Value + "' ";
                    this.cboValCury.RequestEveryTime = true;
                }
                else
                {
                    this.cboValCury.QueryCond = "";
                }
            }
            catch (Exception ex)
            {
                ClsBaseException.DiscardException(ex);
                ////YssMessageBox.ShowDyanInformation("获取计价货币数据出现异常", ex.Message, MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("002", _formFun, ClsEnums.StatusSetting.YssSave));
            }
        }


        /// <summary>
        ///  报价因子输入离开后验证输入数据.
        /// 修改人：liuping.
        /// 修改时间：2011-03-18     BUG #1488 货币对设置BUG.
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        private void txtPriceFactor_Leave(object sender, EventArgs e)
        {
            try
            {
                if (!ClsFunction.IsNumeric(this.txtPriceFactor.Value))
                {
                    return;
                }
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("003", _formFun, ClsEnums.StatusSetting.YssSave));
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 计价货币下拉框刷新事件之前，处理获取当前基准货币下的基准货币代码.
        /// 修改人：liuping.
        /// 修改时间：2011-03-18     BUG #1488 货币对设置BUG .
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e.</param>
        private void cboValCury_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            try
            {
                if (status != ClsEnums.StatusSetting.YssBrow)
                {
                    if (this.cboBasicCury.Value != null && this.cboBasicCury.Value.Length > 0)
                    {
                        this.cboValCury.QueryCond = " where a.C_DC_CODE <> '" + this.cboBasicCury.Value + "'";
                        this.cboValCury.RequestEveryTime = false;
                    }
                    else
                    {
                        this.cboValCury.RequestEveryTime = false;
                    }
                }
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("002", _formFun, ClsEnums.StatusSetting.YssSave));
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 基准货币下拉框刷新事件之前，处理获取当前计价货币下的计价货币代码.
        /// 修改人：liuping.
        /// 修改时间：2011-03-18      BUG #1488 货币对设置BUG.
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        private void cboBasicCury_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            try
            {
                if (status != ClsEnums.StatusSetting.YssBrow)
                {
                    if (this.cboValCury.Value != null && this.cboValCury.Value.Length > 0)
                    {
                        this.cboBasicCury.QueryCond = " where a.C_DC_CODE <> '" + this.cboValCury.Value + "'";
                        this.cboBasicCury.RequestEveryTime = false;
                    }
                    else
                    {
                        this.cboBasicCury.RequestEveryTime = false;
                    }
                }
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("002", _formFun, ClsEnums.StatusSetting.YssSave));
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_CURY_PAIR_HBD_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.curyPairHdbService = ServiceFactory.createService(serviceType) as ICuryPairService;
            this.dataService = this.curyPairHdbService;
        }

        /// <summary>
        /// 封装窗体数据到对象
        /// </summary>
        /// <returns>Pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            YssInformation.Support.Bi.CuryPair.Pojo.CuryPair curyPair = null;
            try
            {
                curyPair = new YssInformation.Support.Bi.CuryPair.Pojo.CuryPair();
                curyPair.C_CURY_PAIR_CODE = this.txtCuryPairCode.Text; // 货币对代码
                curyPair.C_CURY_PAIR_NAME = this.txtHBDName.Text; // 货币对名称
                curyPair.C_DC_CODE_MARK = this.cboBasicCury.Value; // 基准货币
                curyPair.C_DC_CODE_PRICE = this.cboValCury.Value; // 计价货币
                curyPair.N_QTE_FACTO = Convert.ToDecimal(this.txtPriceFactor.Text); // 报价因子
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return curyPair;
        }

        /// <summary>
        /// 展示对象属性到窗体
        /// </summary>
        /// <param name="pojo">Pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                YssInformation.Support.Bi.CuryPair.Pojo.CuryPair curyPair = (YssInformation.Support.Bi.CuryPair.Pojo.CuryPair)yssGetBaseSelTypeItemMVC();
                if (null != curyPair)
                {
                    this.txtCuryPairCode.Text = curyPair.C_CURY_PAIR_CODE; // 货币对代码
                    this.txtHBDName.Text = curyPair.C_CURY_PAIR_NAME; // 货币对名称
                    this.cboBasicCury.Value = curyPair.C_DC_CODE_MARK; // 基准货币
                    this.cboValCury.Value = curyPair.C_DC_CODE_PRICE; // 计价货币
                    this.txtPriceFactor.Text = curyPair.N_QTE_FACTO.ToString(); // 报价因子
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }

        }
    }
}


