﻿using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;

using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;


namespace YssSecInformation.Support.Func
{
    /// <summary>
    /// 类功能：
    /// 　根据上市代码与证券市场调整证券内码
    /// 　并根据
    /// </summary>
    public class ClsAdjustSecCode
    {
        /// <summary>
        /// 定义证券内码控件
        /// </summary>
        private Yss.KRichEx.YssTextBox txtSecCode;

        /// <summary>
        /// 定义市场控件
        /// </summary>
        private FAST.Core.BaseControl.YssSelCombox cboMktCode;

        /// <summary>
        /// 定义上市代码控件
        /// </summary>
        private Yss.KRichEx.YssTextBox txtSecMktCode;

        /// <summary>
        /// 定义第三个条件下拉控件
        /// </summary>
        private FAST.Core.BaseControl.YssSelCombox cboThird;

        /// <summary>
        /// 市场代码
        /// </summary>
        private string cMktCode = null;

        /// <summary>
        /// 上市代码
        /// </summary>
        private string cSecMktCode = null;

        /// <summary>
        /// 第三个值
        /// </summary>
        private string cThirdCode = null;

        /// <summary>
        /// 设置窗体
        /// </summary>
        private FrmBaseSet frmBaseSet = null;

        /// <summary>
        /// 调整证券内码的值
        /// </summary>
        /// <param name="txtSecCode">证券内码控件</param>
        /// <param name="cboMktCode">市场代码控件</param>
        /// <param name="txtSecMktCode">上市代码控件</param>
        /// <param name="frmBaseSet">窗体</param>
        public ClsAdjustSecCode(ref Yss.KRichEx.YssTextBox txtSecCode, ref FAST.Core.BaseControl.YssSelCombox cboMktCode, ref Yss.KRichEx.YssTextBox txtSecMktCode, FrmBaseSet frmBaseSet)
        {
            this.txtSecCode = txtSecCode;
            this.cboMktCode = cboMktCode;
            this.txtSecMktCode = txtSecMktCode;
            cboMktCode.SelectedValueChanged += new EventHandler(controls_ValueChangedEvent);
            txtSecMktCode.TextChanged += new EventHandler(controls_ValueChangedEvent);
            this.frmBaseSet = frmBaseSet;
        }

        /// <summary>
        /// 调整证券内码的值
        /// </summary>
        /// <param name="txtSecCode">证券内码控件</param>
        /// <param name="cboMktCode">市场代码控件</param>
        /// <param name="txtSecMktCode">上市代码控件</param>
        /// <param name="cboThird">第三个条件字段</param>
        /// <param name="frmBaseSet">窗体</param>
        public ClsAdjustSecCode(ref Yss.KRichEx.YssTextBox txtSecCode, ref FAST.Core.BaseControl.YssSelCombox cboMktCode, ref Yss.KRichEx.YssTextBox txtSecMktCode, ref FAST.Core.BaseControl.YssSelCombox cboThird, FrmBaseSet frmBaseSet)
        {
            this.txtSecCode = txtSecCode;
            this.cboMktCode = cboMktCode;
            this.txtSecMktCode = txtSecMktCode;
            this.cboThird = cboThird;
            cboMktCode.SelectedValueChanged += new EventHandler(controls_ValueChangedEvent);
            cboThird.SelectedValueChanged += new EventHandler(controls_ValueChangedEvent);
            txtSecMktCode.TextChanged += new EventHandler(controls_ValueChangedEvent);
            this.frmBaseSet = frmBaseSet;
        }

        /// <summary>
        /// 取市场代码
        /// </summary>
        /// <returns>市场代码的值</returns>
        private string getMkt()
        {
            if (frmBaseSet != null && frmBaseSet.YssStatus != FAST.Core.Context.ClsEnums.StatusSetting.YssBrow)
            {
                return cMktCode;
            }

            return null;
        }

        /// <summary>
        /// 取上市代码
        /// </summary>
        /// <returns>上市代码的值</returns>
        private string getSecMkt()
        {
            if (frmBaseSet != null && frmBaseSet.YssStatus != FAST.Core.Context.ClsEnums.StatusSetting.YssBrow)
            {
                return cSecMktCode;
            }

            return null;
        }

        /// <summary>
        /// 取第三个值
        /// </summary>
        /// <returns>第三个控件的值</returns>
        private string getThird()
        {
            if (frmBaseSet != null && frmBaseSet.YssStatus != FAST.Core.Context.ClsEnums.StatusSetting.YssBrow)
            {
                return cThirdCode;
            }

            return null;
        }

        /// <summary>
        /// 控件的事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void controls_ValueChangedEvent(object sender, EventArgs e)
        {
            if (sender != null)
            {
                cSecMktCode = txtSecMktCode.Text;
                if (cboMktCode.Value != null && cboMktCode.SelectedItem != null)
                {
                    YssInformation.Support.Bi.Market.Pojo.MktExtend clsMkt = cboMktCode.SelectedItem.DataEntity as YssInformation.Support.Bi.Market.Pojo.MktExtend;
                    cMktCode = clsMkt.C_MKT_NO;
                }

                if (cboThird != null && cboThird.Value != null)
                {
                    cThirdCode = cboThird.Value;
                }

                string cSecMkt = getSecMkt();
                string cMkt = getMkt();
                string cThird = getThird();
                string cSecCode = txtSecCode.Text; // 新的证券代码的值
                if (cSecMkt == null && cMkt == null && cThird == null)
                {
                    //// 如果两个都为空则直接返回
                    return;
                }

                if (cSecMkt == null)
                {
                    cSecMkt = "";
                }

                if (cMkt == null)
                {
                    cMkt = "";
                }

                if (cThird == null)
                {
                    cThird = "";
                }

                cSecCode = cSecMkt + " " + cMkt + (cboThird != null ? (" " + cThird) : "");
                ////edt by zzk 20150521 证券内码不能有‘.’会影响科目的显示 BUG #112801 【紧急】现货品种信息的内码不能带"." 
                txtSecCode.Text = cSecCode.Replace(".", "");
                ////txtSecCode.Text = cSecCode;
            }
        }
    }
}




