﻿using FAST.Core.Util;
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
using YssSecInformation.Support.Sv.Service;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Func;



namespace YssSecInformation.Sv.Form
{
    /// <summary>
    /// 功能简介：外汇交易品种设置，负责外汇交易品种数据的增删改查等功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2011.01.07
    ///
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：chenyoulong
    /// 修改日期：20110110
    /// 修改简介：方法的具体实现
    /// 
    ///  -----------修改记录----------
    /// 当前版本：v4.5.0.3
    /// 修改人：chenyoulong
    /// 修改日期：2011.02.16
    /// 修改简介：根据需求的二次更新，进行模块的二次开发
    /// 修改主要有以下几点：
    /// 1、根据新的表结构进行调整
    /// 2、注释完善
    /// 3、提示信息统一调整
    /// 4、需求中细节点的控制完善
    /// 5、界面需要调整textAlign 和RighttoLeft属性值以及负号需要输入等
    /// 6、列头调整
    /// 7、界面上调整textBox
    /// 
    /// 作者：lyh 
    ///  
    ///  版本：v4.5.0.4
    ///  
    ///  修改内容：调整新需求
    ///  
    ///  修改日期：2011.3.2
    ///－－－－修改记录－－－－
    /// 当前版本：V4.5.0.5
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    /// 返回对象错误直接抛出异常
    /// </summary>
    public partial class Frm_SEC_BASE_WH_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private ISecBaseWhService service;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_SEC_BASE_WH_S()
        {
            InitializeComponent();
            bUseMVCService = true;
        }

        /// <summary>
        ///  初始化set界面，并给各控件赋值.
        ///  wuwenlan.
        ///  20111302.
        /// </summary>
        public override void yssInitCtlAttr()
        {
            ////Cls_MKT mkt = null;
            try
            {
                // 由于下拉列表更改，下拉列表数据在控件间中配置，删除在初始的时候配置分级列表

                // 如果set窗体的状态是新增状态，在点击新增之前事件中已经得到组合代码
                // 然后将赋值给set窗体窗体中的组合代码
                ////mkt = this.frmBaseViewList.getSelectedRowTagMVC(mkt) as Cls_MKT;
                ////if (mkt != null)
                ////{
                ////    this.cboMkt.Value = mkt.C_MKT_CODE; // 交易市场代码
                ////}

                if (this.status == ClsEnums.StatusSetting.YssAdd)
                {
                    this.txtSecCode.Text = "";
                }

            }
            catch (ClsBaseException e)
            {
                ClsBaseException.DiscardException(e);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFormLoadErr(this.Text));
            }

        }

        /// <summary>
        /// 封装界面元素为pojo对象
        /// </summary>
        /// <returns>由界面元素组成的对象</returns>
        public override AuditableParamPojo faceInfoToObjMVC()
        {
            SecBase sec = null;
            try
            {
                sec = new SecBase();

                sec.C_SEC_CODE = this.txtSecurityCode.Text;
                sec.C_SEC_VAR_CODE = "WH";
                sec.C_DC_CODE = " ";
                sec.C_SEC_CODE_TRG = this.cboCuryPair.Value;
                sec.C_SEC_MKT_CODE = this.txtSecCode.Text;
                sec.C_SEC_NAME = this.txtSecName.Text;
                sec.C_MKT_CODE = this.cboMkt.Value;
                sec.C_DV_VAR_DUR = this.cboQiXian.Value;
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return sec;
        }

        /// <summary>
        /// 获取list中选中记录，为界面元素赋值，显示数据
        /// </summary>
        /// <param name="pojo">数据对象</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            try
            {
                SecBase sec = (SecBase)yssGetBaseSelTypeItemMVC();

                ////edit by liuxiang 2013/9/4 bug9337 添加条件C_SEC_VAR_CODE，判断证券类型
                if (null != sec && sec.C_SEC_VAR_CODE.StartsWith("WH"))
                {
                    this.txtSecurityCode.Text = sec.C_SEC_CODE;
                    this.cboCuryPair.Value = sec.C_SEC_CODE_TRG;
                    this.txtSecCode.Text = sec.C_SEC_MKT_CODE;
                    this.txtSecName.Text = sec.C_SEC_NAME;
                    this.cboMkt.Value = sec.C_MKT_CODE;
                    this.cboQiXian.Value = sec.C_DV_VAR_DUR;
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 保存之前的点击事件
        /// zhaungyuchen 2011-4-13
        /// bug 单号：1662
        /// 保存之前判断交易市场，期限，货币对的值是否为空
        /// 如果为空就抛出异常
        /// </summary>
        /// <param name="sender">事件对象</param>
        /// <param name="e">事件类型</param>
        private void Frm_SEC_BASE_WH_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        {
                ////edit by liuxiang 2013/8/23 bug9162
                ////if (this.cboCuryPair.Value == null || this.cboCuryPair.Value.Equals("null"))
                ////{
                ////    ////throw new ClsBaseException("货币对的值不能输入，请在下拉框中选择");
                ////    throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("001", _formFun, ClsEnums.StatusSetting.YssSave));
                ////}

                ////if (this.cboMkt.Value == null || this.cboMkt.Value.Equals("null"))
                ////{
                ////    ////throw new ClsBaseException("交易市场的值不能输入，请在下拉框中选择");
                ////    throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("002", _formFun, ClsEnums.StatusSetting.YssSave));
                ////}

                ////if (this.cboQiXian.Value == null || this.cboQiXian.Value.Equals("null"))
                ////{
                ////    ////throw new ClsBaseException("期限的值不能输入，请在下拉框中选择");
                ////    throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("003", _formFun, ClsEnums.StatusSetting.YssSave));
                ////}
          
        }

        /////// <summary>
        /////// 上市代码值改变事件
        /////// </summary>
        /////// <param name="sender">sender</param>
        /////// <param name="e">e</param>
        ////private void txtSecCode_TextChanged(object sender, EventArgs e)
        ////{
        ////    txtSecCodeTextChanged();
        ////}

        /// <summary>
        /// load
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_BASE_WH_S_Load(object sender, EventArgs e)
        {
            Type dataServiceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            service = (ISecBaseWhService)ServiceFactory.createService(dataServiceType);
            dataService = service;
            //// 通过下面的类统一实现证券内码的调整处理 byleeyu 20130426
            ClsAdjustSecCode clsAdjustSec = new ClsAdjustSecCode(ref txtSecurityCode, ref cboMkt, ref txtSecCode, ref cboQiXian, this);
        }

        /// <summary>
        /// 点击新增清空数据
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_BASE_WH_S_YssOnAfterNewClick(object sender, EventArgs e)
        {
            this.txtSecurityCode.Text = "";
        }
    }
}




