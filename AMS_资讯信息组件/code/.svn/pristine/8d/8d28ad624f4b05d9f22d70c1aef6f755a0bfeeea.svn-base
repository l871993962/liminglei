using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;
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
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Sv.Pojo;

namespace YssSecInformation.Sv.Form
{
    /// <summary>
    /// chenbo
    /// 2017-06-22
    /// #42948 资讯信息管理组件化拆分
    /// 指数基本信息SET窗体
    /// </summary>
    public partial class Frm_INDEX_INFO_S : FrmBaseSet
    {
        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_INDEX_INFO_S()
        {
            bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 展示数据
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                IndexInfo indexInfo = this.yssGetBaseSelTypeItemMVC() as IndexInfo;

                if (null != indexInfo)
                {
                    this.txtsecCode.Text = indexInfo.C_SEC_CODE;
                    this.txtIndexCode.Text = indexInfo.C_INDEX_CODE;
                    this.txtIndexName.Text = indexInfo.C_INDEX_NAME;
                    this.txtIsinCode.Text = indexInfo.C_SEC_ISIN_CODE;
                    this.cboIndexType.Value = indexInfo.C_INDEX_TYPE;
                    this.cboOrgCode.Value = indexInfo.C_INDEX_ORG_CODE;
                    this.cboCury.Value = indexInfo.C_DC_CODE;
                    this.txtBasePoint.Text = indexInfo.N_INDEX_BASE.ToString();
                    this.dateBase.setDateTime(Convert.ToDateTime(indexInfo.D_BASE));
                    this.dateEnd.setDateTime(Convert.ToDateTime(indexInfo.D_END));
                }
            }
            catch (ClsBaseException e)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-110023", _formFun, status));
                ClsBaseException.DiscardException(e);
            }
        }

        /// <summary>
        /// 封装数据
        /// </summary>
        /// <returns>IndexInfo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            IndexInfo indexInfo = new IndexInfo();
            try
            {
                indexInfo.C_INDEX_CODE = this.txtIndexCode.Text;
                indexInfo.C_INDEX_NAME = this.txtIndexName.Text;
                indexInfo.C_INDEX_TYPE = this.cboIndexType.Value;
                indexInfo.C_INDEX_ORG_CODE = this.cboOrgCode.Value;
                indexInfo.C_DC_CODE = this.cboCury.Value;
                indexInfo.C_SEC_ISIN_CODE = this.txtIsinCode.Text.Trim().Length == 0 ? " " : this.txtIsinCode.Text;
                indexInfo.C_SEC_CODE = this.txtIndexCode.Text + "_" + this.cboOrgCode.Value;
                indexInfo.N_INDEX_BASE = Convert.ToDecimal(this.txtBasePoint.Text);
                indexInfo.D_BASE = this.dateBase.getBeginDate.ToString("yyyy-MM-dd");
                indexInfo.D_END = this.dateEnd.getBeginDate.ToString("yyyy-MM-dd");
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return indexInfo;
        }

        /////// <summary>
        /////// 窗体加载事件
        /////// </summary>
        /////// <param name="sender">sender</param>
        /////// <param name="e">e</param>
        ////private void Frm_INDEX_INFO_S_Load(object sender, EventArgs e)
        ////{
        ////    this.txtsecCode.YssReadOnly = true;
        ////}

        /// <summary>
        /// 指数代码改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void txtIndexCode_TextChanged(object sender, EventArgs e)
        {
            string[] secCode = null;
            ////if (null != this.txtsecCode.Text && 0 != this.txtsecCode.Text.Trim().Length)
            ////if (this.txtsecCode.Text.Contains("_"))
            ////{
            ////    secCode = this.txtsecCode.Text.Split('_');
            ////    this.txtsecCode.Text = this.txtIndexCode.Text + "_" + secCode[1];
            ////}
            ////else
            ////{
            ////    this.txtsecCode.Text = this.txtIndexCode.Text;
            ////}

            if (null != this.cboOrgCode.Value)
            {
                this.txtsecCode.Text = this.txtIndexCode.Text + "_" + this.cboOrgCode.Value;
            }
            else
            {
                this.txtsecCode.Text = this.txtIndexCode.Text;
            }

        }

        /// <summary>
        /// 指数编制结构改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboOrgCode_SelectedValueChanged(object sender, EventArgs e)
        {
            ////string[] secCode = null;
            ////if (null != this.txtsecCode.Text && 0 != this.txtsecCode.Text.Trim().Length)
            ////{
            ////    secCode = this.txtsecCode.Text.Split('_');
            ////    if (null != this.cboOrgCode.Value)
            ////    {
            ////        this.txtsecCode.Text = secCode[0] + "_" + this.cboOrgCode.Value;
            ////    }
            ////    else
            ////    {
            ////        this.txtsecCode.Text = secCode[0];
            ////    }
                
            ////}
            ////else
            ////{
            ////    this.txtsecCode.Text = "_" + this.cboOrgCode.Value;
            ////}

            if (null != this.txtsecCode.Text && 0 != this.txtsecCode.Text.Trim().Length)
            {
                if (null != this.cboOrgCode.Value)
                {
                    string orgCode = this.cboOrgCode.Value.Trim().Length == 0 ? "" : "_" + this.cboOrgCode.Value;
                    this.txtsecCode.Text = this.txtIndexCode.Text + orgCode;
                }
                else
                {
                    this.txtsecCode.Text = this.txtIndexCode.Text;
                }
            }

        }
    }
}


