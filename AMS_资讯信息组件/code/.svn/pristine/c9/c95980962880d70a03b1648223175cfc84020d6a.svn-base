﻿using FAST.Core.Util;
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

using FAST.Core.BaseControl;
using System.Collections;
////using YssPara.Service.Sv;
using YssSecInformation.Support.Sv.Service;

////using YssPara.Pojo.Sv;
using YssSecInformation.Support.Pojo.Sec;

////using YssBaseCls.Interface;
using FAST.Common.Service.Services;
using FAST.Core.CRUD.Interface;
using YssSecInformation.Support.Sv.Pojo;
////using YssBaseCls.Pojo;

namespace YssSecInformation.Sv.Form
{
    /// <summary>
    /// 国债期货债券转换因子设置界面
    /// </summary>
    public partial class Frm_SEC_BASE_QHZH_S : FrmBaseSet
    {
        /// <summary>
        /// 转换因子信息服务接口
        /// </summary>
        private IFutureFactorService futureFactorSvc = null;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_SEC_BASE_QHZH_S()
        {
            bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 初始化控件
        /// </summary>
        public override void initControlStat()
        {
            base.initControlStat();
            this.cboMkt.YssReadOnly = true;
        }

        /// <summary>
        /// 合约代码改变事件
        /// 自动加载交易市场
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboSec_SelectedValueChanged(object sender, EventArgs e)
        {
            try
            {
                if (((YssSelCombox)sender).SelectedItem != null)
                {
                    // 当选择交易证券时，应取出交易证券中的币种信息和证券品种信息，并将他们赋值到对应的控件当中
                    SecBase secBase = ((SecBase)((YssSelCombox)sender).SelectedItem.DataEntity);
                    if (secBase != null)
                    {
                        this.cboMkt.Value = secBase.C_MKT_CODE;
                    }
                }
            }
            catch (Exception ye)
            {
                ////YssMessageBox.ShowDyanInformation("控件赋值出错", ye.Message, "信息提示", MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-110023", _formFun, status));
                ClsBaseException.DiscardException(ye);
            }
        }

        /// <summary>
        /// 初始化服务
        /// </summary>
        protected override void initServiceMVC()
        {
            base.initServiceMVC();
            futureFactorSvc = ServiceFactory.createService<IFutureFactorService>();
        }

        /// <summary>
        /// 封装数据
        /// </summary>
        /// <returns>pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            FutureFactor futureFactor = null;

            try
            {
                futureFactor = new FutureFactor();
                futureFactor.C_CONTRACT_CODE = this.cboSec.Value;
                futureFactor.C_MKT_CODE = this.cboMkt.Value;
                futureFactor.C_SEC_CODE = this.cboSecTag.Value;
                futureFactor.N_CONVERT_FACTOR = Convert.ToDecimal(this.txtConvertFactor.Text);
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }

            return futureFactor;
        }

        /// <summary>
        /// 展示数据
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                FutureFactor futureFactor = (FutureFactor)this.yssGetBaseSelTypeItemMVC();
                if (futureFactor != null)
                {
                    this.cboMkt.Value = futureFactor.C_MKT_CODE;
                    this.cboSec.Value = futureFactor.C_CONTRACT_CODE;
                    this.cboSecTag.Value = futureFactor.C_SEC_CODE;
                    this.txtConvertFactor.Text = futureFactor.N_CONVERT_FACTOR.ToString();
                }
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }
        }

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
                    this.cboSec.YssReadOnly = true;
                    SecBase sec = frmDetailData.MainDataPojo as SecBase;
                    //// eidt by Yuntao Lau 2015.11.18 BUG #122454
                    ////if (sec.C_SEC_VAR_CODE.EndsWith("QH_ZQ"))
                    ////{
                        this.cboSec.Value = sec.C_SEC_CODE;
                        this.cboMkt.Value = sec.C_MKT_CODE;
                    ////}
                }
            }
        }
    }
}




