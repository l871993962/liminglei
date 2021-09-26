using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Core.Context;
using FAST.Core.Resource;
using Yss.KRichEx;
using System;

namespace YssSecInformation.Mp.SecEq.Form
{
    partial class Frm_SEC_EQU_PX_S
    {
        /// <summary>
        /// 必需的设计器变量。
        /// </summary>
        private System.ComponentModel.Container components = null;

        /// <summary>
        /// 清理所有正在使用的资源。
        /// </summary>
        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                if (components != null)
                {
                    components.Dispose();
                }
            }
            base.Dispose(disposing);
        }
        #region Windows 窗体设计器生成的代码
        /// <summary>
        /// 设计器支持所需的方法 - 不要使用代码编辑器修改
        /// 此方法的内容。
        /// </summary>
        private void InitializeComponent()
        {
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo4 = new FAST.Core.BaseControl.ControlMethodInfo();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_SEC_EQU_PX_S));
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo1 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo2 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo3 = new FAST.Core.BaseControl.ControlMethodInfo();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.cboInterestCategory = new FAST.Core.BaseControl.YssSelCombox();
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.cboDividendType = new FAST.Core.BaseControl.YssSelCombox();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.row3 = new Yss.KTable.Models.Row();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.cboCurry = new FAST.Core.BaseControl.YssSelCombox();
            this.row4 = new Yss.KTable.Models.Row();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.txtPreTaxRatio = new Yss.KRichEx.YssTextBox();
            this.cell13 = new Yss.KTable.Models.Cell();
            this.cell14 = new Yss.KTable.Models.Cell();
            this.cell15 = new Yss.KTable.Models.Cell();
            this.txtAfterTaxRatio = new Yss.KRichEx.YssTextBox();
            this.row5 = new Yss.KTable.Models.Row();
            this.cell16 = new Yss.KTable.Models.Cell();
            this.cell17 = new Yss.KTable.Models.Cell();
            this.dtpRegisterDate = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell18 = new Yss.KTable.Models.Cell();
            this.cell19 = new Yss.KTable.Models.Cell();
            this.cell20 = new Yss.KTable.Models.Cell();
            this.dtpExRightDate = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.row6 = new Yss.KTable.Models.Row();
            this.cell21 = new Yss.KTable.Models.Cell();
            this.cell22 = new Yss.KTable.Models.Cell();
            this.dtpPayDate = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.row7 = new Yss.KTable.Models.Row();
            this.row8 = new Yss.KTable.Models.Row();
            this.row9 = new Yss.KTable.Models.Row();
            this.balloonTip = new YssDevComponents.DotNetBar.BalloonTip();
            this.row10 = new Yss.KTable.Models.Row();
            this.cell23 = new Yss.KTable.Models.Cell();
            this.cell24 = new Yss.KTable.Models.Cell();
            this.txtDistPrice = new Yss.KRichEx.YssTextBox();
            this.btnSecurityCode = new FAST.Core.BaseControl.PenetrationComboBox();
            this.tbMain.SuspendLayout();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // tbMain
            // 
            // 
            // 
            // 
            this.tbMain.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(111)))), ((int)(((byte)(157)))), ((int)(((byte)(217)))));
            this.tbMain.Border.Bottom = true;
            this.tbMain.Border.Left = false;
            this.tbMain.Border.Right = false;
            this.tbMain.Border.Top = false;
            this.tbMain.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.column1,
            this.column2,
            this.column3,
            this.column4,
            this.column5});
            this.tbMain.Controls.Add(this.btnSecurityCode);
            this.tbMain.Controls.Add(this.dtpPayDate);
            this.tbMain.Controls.Add(this.dtpExRightDate);
            this.tbMain.Controls.Add(this.dtpRegisterDate);
            this.tbMain.Controls.Add(this.cboInterestCategory);
            this.tbMain.Controls.Add(this.cboDividendType);
            this.tbMain.Controls.Add(this.cboCurry);
            this.tbMain.Controls.Add(this.txtAfterTaxRatio);
            this.tbMain.Controls.Add(this.txtPreTaxRatio);
            this.tbMain.Controls.Add(this.txtDistPrice);
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row7,
            this.row2,
            this.row3,
            this.row4,
            this.row10,
            this.row8,
            this.row9,
            this.row5,
            this.row6});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(493, 249);
            // 
            // stBarBottom
            // 
            // 
            // 
            // 
            this.stBarBottom.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.stBarBottom.Border.Bottom = true;
            this.stBarBottom.Border.Left = false;
            this.stBarBottom.Border.Right = false;
            this.stBarBottom.Border.Top = true;
            this.stBarBottom.Location = new System.Drawing.Point(0, 279);
            this.stBarBottom.StatuType = "新建(&N)...";
            // 
            // pnlMain
            // 
            this.pnlMain.Size = new System.Drawing.Size(493, 304);
            // 
            // yssPanel1
            // 
            // 
            // 
            // 
            this.yssPanel1.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.yssPanel1.Border.Bottom = true;
            this.yssPanel1.Border.Left = true;
            this.yssPanel1.Border.Right = true;
            this.yssPanel1.Border.Top = true;
            this.yssPanel1.Size = new System.Drawing.Size(493, 304);
            // 
            // hpAssist
            // 
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("新宋体", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row1.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row1.FullRowSelected = false;
            this.row1.GroupLineLength = 310;
            this.row1.GroupPosition = 13;
            this.row1.Height = 33;
            this.row1.IsGroup = true;
            this.row1.Text = " 基本信息";
            // 
            // row2
            // 
            this.row2.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell1,
            this.cell2,
            this.cell3,
            this.cell4,
            this.cell5});
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row2.ForeColor = System.Drawing.Color.Black;
            this.row2.FullRowSelected = false;
            this.row2.GroupPosition = -16;
            this.row2.Height = 25;
            this.row2.Text = null;
            // 
            // cell1
            // 
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.Text = "   交易证券：";
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.btnSecurityCode;
            // 
            // cell3
            // 
            this.cell3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell3.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell4
            // 
            this.cell4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell4.Text = "权益类型：";
            // 
            // cell5
            // 
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.cboInterestCategory;
            // 
            // cboInterestCategory
            // 
            this.cboInterestCategory.AddedSelItemName = "";
            this.cboInterestCategory.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.cboInterestCategory.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboInterestCategory.Border.Bottom = true;
            this.cboInterestCategory.Border.Left = true;
            this.cboInterestCategory.Border.Right = true;
            this.cboInterestCategory.Border.Top = true;
            this.cboInterestCategory.ClassName = "";
            this.cboInterestCategory.DisplayName = "C_DT_NAME";
            this.cboInterestCategory.DisplayValue = "C_DT_CODE";
            this.cboInterestCategory.DllName = "dll";
            this.cboInterestCategory.FilterCond = "";
            this.cboInterestCategory.IsFillDecimal = false;
            this.cboInterestCategory.Location = new System.Drawing.Point(353, 43);
            this.cboInterestCategory.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo4.MethodName = "getDataListByKeys";
            controlMethodInfo4.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo4.MethodParams")));
            controlMethodInfo4.MethodParamValues = new string[] {
        "DJPX_FHPX,DJPX_XJDJ,DJPX_DF,DJPX_HLBS,"};
            controlMethodInfo4.Methods = null;
            this.cboInterestCategory.MethodInfo = controlMethodInfo4;
            this.cboInterestCategory.Name = "cboInterestCategory";
            this.cboInterestCategory.Parameter = "C_DT_NAME";
            this.cboInterestCategory.QueryCond = "DJPX";
            this.cboInterestCategory.QueryType = "CacheType";
            this.cboInterestCategory.Size = new System.Drawing.Size(121, 21);
            this.cboInterestCategory.TabIndex = 2;
            this.cboInterestCategory.Tag = this.cell5;
            this.cboInterestCategory.UseCustomerParameter = false;
            this.cboInterestCategory.YssAssociaType = YssInformation.Support.Context.AssociaType.base_dttdmode;
            this.cboInterestCategory.YssCaption = "权益类型";
            this.cboInterestCategory.YssIsMust = true;
            this.cboInterestCategory.YssKiloDelimiter = true;
            this.cboInterestCategory.SelectedValueChanged += new System.EventHandler(this.cboInterestCategory_SelectedValueChanged);
            // 
            // column1
            // 
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.Text = " ";
            this.column1.Width = 110;
            // 
            // column2
            // 
            this.column2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column2.Width = 122;
            // 
            // column3
            // 
            this.column3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column3.Width = 30;
            // 
            // column4
            // 
            this.column4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column4.Width = 91;
            // 
            // column5
            // 
            this.column5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column5.Width = 122;
            // 
            // cboDividendType
            // 
            this.cboDividendType.AddedSelItemName = "";
            this.cboDividendType.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboDividendType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboDividendType.Border.Bottom = true;
            this.cboDividendType.Border.Left = true;
            this.cboDividendType.Border.Right = true;
            this.cboDividendType.Border.Top = true;
            this.cboDividendType.ClassName = "";
            this.cboDividendType.DisplayName = "C_DV_NAME";
            this.cboDividendType.DisplayValue = "C_DV_CODE";
            this.cboDividendType.DllName = "dll";
            this.cboDividendType.FilterCond = "";
            this.cboDividendType.IsFillDecimal = false;
            this.cboDividendType.Location = new System.Drawing.Point(110, 68);
            this.cboDividendType.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataListByTypes";
            controlMethodInfo1.MethodParams = null;
            controlMethodInfo1.MethodParamValues = new string[] {
        "DIV_TYPE,"};
            controlMethodInfo1.Methods = null;
            this.cboDividendType.MethodInfo = controlMethodInfo1;
            this.cboDividendType.Name = "cboDividendType";
            this.cboDividendType.Parameter = "C_DV_NAME";
            this.cboDividendType.QueryCond = "DIV_TYPE";
            this.cboDividendType.QueryType = "CacheType";
            this.cboDividendType.Size = new System.Drawing.Size(121, 21);
            this.cboDividendType.TabIndex = 3;
            this.cboDividendType.Tag = this.cell7;
            this.cboDividendType.UseCustomerParameter = false;
            this.cboDividendType.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboDividendType.YssCaption = "分红类型";
            this.cboDividendType.YssKiloDelimiter = true;
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.InnerControl = this.cboDividendType;
            // 
            // row3
            // 
            this.row3.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell6,
            this.cell7,
            this.cell8,
            this.cell9,
            this.cell10});
            this.row3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row3.ForeColor = System.Drawing.Color.Black;
            this.row3.FullRowSelected = false;
            this.row3.GroupPosition = -16;
            this.row3.Height = 25;
            this.row3.Text = null;
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.Text = "   分红类型：";
            // 
            // cell8
            // 
            this.cell8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell8.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell9
            // 
            this.cell9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell9.Text = "分红货币：";
            // 
            // cell10
            // 
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.InnerControl = this.cboCurry;
            // 
            // cboCurry
            // 
            this.cboCurry.AddedSelItemName = "";
            this.cboCurry.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.cboCurry.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboCurry.Border.Bottom = true;
            this.cboCurry.Border.Left = true;
            this.cboCurry.Border.Right = true;
            this.cboCurry.Border.Top = true;
            this.cboCurry.ClassName = "";
            this.cboCurry.DisplayName = "C_DC_NAME";
            this.cboCurry.DisplayValue = "C_DC_CODE";
            this.cboCurry.DllName = "dll";
            this.cboCurry.FilterCond = "";
            this.cboCurry.IsFillDecimal = false;
            this.cboCurry.Location = new System.Drawing.Point(353, 68);
            this.cboCurry.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo2.MethodName = "getDataList";
            controlMethodInfo2.MethodParams = null;
            controlMethodInfo2.MethodParamValues = null;
            controlMethodInfo2.Methods = null;
            this.cboCurry.MethodInfo = controlMethodInfo2;
            this.cboCurry.Name = "cboCurry";
            this.cboCurry.Parameter = "C_DC_NAME";
            this.cboCurry.QueryCond = "";
            this.cboCurry.QueryType = "";
            this.cboCurry.Size = new System.Drawing.Size(121, 21);
            this.cboCurry.TabIndex = 4;
            this.cboCurry.Tag = this.cell10;
            this.cboCurry.UseCustomerParameter = false;
            this.cboCurry.YssAssociaType = YssInformation.Support.Context.AssociaType.base_currency;
            this.cboCurry.YssCaption = "分红货币";
            this.cboCurry.YssIsMust = true;
            this.cboCurry.YssKiloDelimiter = true;
            // 
            // row4
            // 
            this.row4.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell11,
            this.cell12,
            this.cell13,
            this.cell14,
            this.cell15});
            this.row4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row4.ForeColor = System.Drawing.Color.Black;
            this.row4.FullRowSelected = false;
            this.row4.GroupPosition = -16;
            this.row4.Height = 25;
            this.row4.Text = null;
            // 
            // cell11
            // 
            this.cell11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell11.Text = "   税前权益比例：";
            // 
            // cell12
            // 
            this.cell12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell12.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell12.InnerControl = this.txtPreTaxRatio;
            // 
            // txtPreTaxRatio
            // 
            this.txtPreTaxRatio.AutoTooltip = false;
            this.txtPreTaxRatio.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.balloonTip.SetBalloonCaption(this.txtPreTaxRatio, "10送5，则输入0.5");
            // 
            // 
            // 
            this.txtPreTaxRatio.Border.Class = "TextBoxBorder";
            this.txtPreTaxRatio.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.txtPreTaxRatio.ForeColor = System.Drawing.Color.Blue;
            this.txtPreTaxRatio.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtPreTaxRatio.Location = new System.Drawing.Point(110, 93);
            this.txtPreTaxRatio.Name = "txtPreTaxRatio";
            this.txtPreTaxRatio.Size = new System.Drawing.Size(121, 21);
            this.txtPreTaxRatio.TabIndex = 5;
            this.txtPreTaxRatio.Tag = this.cell12;
            this.txtPreTaxRatio.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtPreTaxRatio.YssCaption = "税前权益比例";
            this.txtPreTaxRatio.YssIsMust = true;
            this.txtPreTaxRatio.YssKiloDelimiter = true;
            this.txtPreTaxRatio.YssLength = 18;
            this.txtPreTaxRatio.YssNumeric = "3, 15";
            // 
            // cell13
            // 
            this.cell13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell13.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell14
            // 
            this.cell14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell14.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell14.Text = "税后权益比例：";
            // 
            // cell15
            // 
            this.cell15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell15.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell15.InnerControl = this.txtAfterTaxRatio;
            // 
            // txtAfterTaxRatio
            // 
            this.txtAfterTaxRatio.AutoTooltip = false;
            this.txtAfterTaxRatio.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.balloonTip.SetBalloonCaption(this.txtAfterTaxRatio, "10送5，则输入0.5");
            // 
            // 
            // 
            this.txtAfterTaxRatio.Border.Class = "TextBoxBorder";
            this.txtAfterTaxRatio.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.txtAfterTaxRatio.ForeColor = System.Drawing.Color.Blue;
            this.txtAfterTaxRatio.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtAfterTaxRatio.Location = new System.Drawing.Point(353, 93);
            this.txtAfterTaxRatio.Name = "txtAfterTaxRatio";
            this.txtAfterTaxRatio.Size = new System.Drawing.Size(121, 21);
            this.txtAfterTaxRatio.TabIndex = 6;
            this.txtAfterTaxRatio.Tag = this.cell15;
            this.txtAfterTaxRatio.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtAfterTaxRatio.YssCaption = "税后权益比例";
            this.txtAfterTaxRatio.YssIsMust = true;
            this.txtAfterTaxRatio.YssKiloDelimiter = true;
            this.txtAfterTaxRatio.YssLength = 18;
            this.txtAfterTaxRatio.YssNumeric = "3, 15";
            // 
            // row5
            // 
            this.row5.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell16,
            this.cell17,
            this.cell18,
            this.cell19,
            this.cell20});
            this.row5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row5.ForeColor = System.Drawing.Color.Black;
            this.row5.FullRowSelected = false;
            this.row5.GroupPosition = -16;
            this.row5.Height = 25;
            this.row5.Text = null;
            // 
            // cell16
            // 
            this.cell16.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell16.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell16.Text = "   登记日期：";
            // 
            // cell17
            // 
            this.cell17.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell17.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell17.InnerControl = this.dtpRegisterDate;
            // 
            // dtpRegisterDate
            // 
            this.dtpRegisterDate.BackColor = System.Drawing.Color.Transparent;
            this.dtpRegisterDate.BusinessDate = true;
            this.dtpRegisterDate.DateBeginChecked = true;
            this.dtpRegisterDate.DateEndChecked = true;
            this.dtpRegisterDate.Location = new System.Drawing.Point(110, 161);
            this.dtpRegisterDate.Margin = new System.Windows.Forms.Padding(0);
            this.dtpRegisterDate.Name = "dtpRegisterDate";
            this.dtpRegisterDate.Size = new System.Drawing.Size(121, 21);
            this.dtpRegisterDate.TabIndex = 10;
            this.dtpRegisterDate.Tag = this.cell17;
            this.dtpRegisterDate.yssEnabled = true;
            this.dtpRegisterDate.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpRegisterDate.yssInterval = false;
            this.dtpRegisterDate.yssLabelStr = "至";
            this.dtpRegisterDate.yssShowCheckBox = false;
            this.dtpRegisterDate.yssShowOperLable = false;
            this.dtpRegisterDate.YssShowSecond = true;
            this.dtpRegisterDate.yssTimeControl = false;
            // 
            // cell18
            // 
            this.cell18.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell18.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell19
            // 
            this.cell19.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell19.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell19.Text = "除权日期：";
            // 
            // cell20
            // 
            this.cell20.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell20.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell20.InnerControl = this.dtpExRightDate;
            // 
            // dtpExRightDate
            // 
            this.dtpExRightDate.BackColor = System.Drawing.Color.Transparent;
            this.dtpExRightDate.BusinessDate = true;
            this.dtpExRightDate.DateBeginChecked = true;
            this.dtpExRightDate.DateEndChecked = true;
            this.dtpExRightDate.Location = new System.Drawing.Point(353, 161);
            this.dtpExRightDate.Margin = new System.Windows.Forms.Padding(0);
            this.dtpExRightDate.Name = "dtpExRightDate";
            this.dtpExRightDate.Size = new System.Drawing.Size(121, 21);
            this.dtpExRightDate.TabIndex = 11;
            this.dtpExRightDate.Tag = this.cell20;
            this.dtpExRightDate.yssEnabled = true;
            this.dtpExRightDate.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpExRightDate.yssInterval = false;
            this.dtpExRightDate.yssLabelStr = "至";
            this.dtpExRightDate.yssShowCheckBox = false;
            this.dtpExRightDate.yssShowOperLable = false;
            this.dtpExRightDate.YssShowSecond = true;
            this.dtpExRightDate.yssTimeControl = false;
            // 
            // row6
            // 
            this.row6.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell21,
            this.cell22});
            this.row6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row6.ForeColor = System.Drawing.Color.Black;
            this.row6.FullRowSelected = false;
            this.row6.GroupPosition = -16;
            this.row6.Height = 25;
            this.row6.Text = null;
            // 
            // cell21
            // 
            this.cell21.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell21.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell21.Text = "   到账日期：";
            // 
            // cell22
            // 
            this.cell22.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell22.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell22.InnerControl = this.dtpPayDate;
            // 
            // dtpPayDate
            // 
            this.dtpPayDate.BackColor = System.Drawing.Color.Transparent;
            this.dtpPayDate.BusinessDate = true;
            this.dtpPayDate.DateBeginChecked = true;
            this.dtpPayDate.DateEndChecked = true;
            this.dtpPayDate.Location = new System.Drawing.Point(110, 186);
            this.dtpPayDate.Margin = new System.Windows.Forms.Padding(0);
            this.dtpPayDate.Name = "dtpPayDate";
            this.dtpPayDate.Size = new System.Drawing.Size(121, 21);
            this.dtpPayDate.TabIndex = 12;
            this.dtpPayDate.Tag = this.cell22;
            this.dtpPayDate.yssEnabled = true;
            this.dtpPayDate.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpPayDate.yssInterval = false;
            this.dtpPayDate.yssLabelStr = "至";
            this.dtpPayDate.yssShowCheckBox = false;
            this.dtpPayDate.yssShowOperLable = false;
            this.dtpPayDate.YssShowSecond = true;
            this.dtpPayDate.yssTimeControl = false;
            // 
            // row7
            // 
            this.row7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row7.ForeColor = System.Drawing.Color.Black;
            this.row7.FullRowSelected = false;
            this.row7.GroupPosition = -16;
            this.row7.Height = 10;
            this.row7.Text = null;
            // 
            // row8
            // 
            this.row8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row8.FullRowSelected = false;
            this.row8.GroupLineLength = 310;
            this.row8.Height = 10;
            this.row8.IsGroup = true;
            this.row8.Text = null;
            // 
            // row9
            // 
            this.row9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row9.FullRowSelected = false;
            this.row9.GroupPosition = -16;
            this.row9.Height = 10;
            this.row9.Text = null;
            // 
            // row10
            // 
            this.row10.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell23,
            this.cell24});
            this.row10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row10.FullRowSelected = false;
            this.row10.Text = null;
            // 
            // cell23
            // 
            this.cell23.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell23.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell23.Text = "   配售价格：";
            // 
            // cell24
            // 
            this.cell24.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell24.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell24.InnerControl = this.txtDistPrice;
            // 
            // txtDistPrice
            // 
            this.txtDistPrice.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.txtDistPrice.Border.Class = "TextBoxBorder";
            this.txtDistPrice.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.txtDistPrice.ForeColor = System.Drawing.Color.Blue;
            this.txtDistPrice.Location = new System.Drawing.Point(110, 118);
            this.txtDistPrice.MaxLength = 25;
            this.txtDistPrice.Name = "txtDistPrice";
            this.txtDistPrice.Size = new System.Drawing.Size(121, 21);
            this.txtDistPrice.TabIndex = 4;
            this.txtDistPrice.Tag = this.cell24;
            this.txtDistPrice.Text = "0";
            this.txtDistPrice.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtDistPrice.YssCaption = "配售价格";
            this.txtDistPrice.YssIsMust = true;
            this.txtDistPrice.YssLength = 30;
            this.txtDistPrice.YssNumeric = "15, 15";
            // 
            // btnSecurityCode
            // 
            this.btnSecurityCode.AddedSelItemName = "";
            this.btnSecurityCode.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.btnSecurityCode.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.btnSecurityCode.Border.Bottom = true;
            this.btnSecurityCode.Border.Left = true;
            this.btnSecurityCode.Border.Right = true;
            this.btnSecurityCode.Border.Top = true;
            this.btnSecurityCode.ClassName = "";
            this.btnSecurityCode.DisplayStyle = Yss.KRichEx.AutoFilter.DisplayStyle.Modal;
            this.btnSecurityCode.DllName = "dll";
            this.btnSecurityCode.ExpandButtonToolTip = "数据调整……";
            this.btnSecurityCode.FilterCond = "";
            this.btnSecurityCode.FunCode = "sv_sec";
            this.btnSecurityCode.Location = new System.Drawing.Point(110, 43);
            this.btnSecurityCode.Margin = new System.Windows.Forms.Padding(0);

            controlMethodInfo3.MethodName = "getDataListByTypes";
            controlMethodInfo3.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo3.MethodParams")));
            controlMethodInfo3.MethodParamValues = new string[] {
        "GP,ZQ,JJ,QZ,LC"};
            controlMethodInfo3.Methods = null;
            this.btnSecurityCode.MethodInfo = controlMethodInfo3;
            
            this.btnSecurityCode.Name = "btnSecurityCode";
            this.btnSecurityCode.QueryCond = "ZQ,LC";
            this.btnSecurityCode.QueryType = "";
            this.btnSecurityCode.Size = new System.Drawing.Size(121, 21);
            this.btnSecurityCode.TabIndex = 13;
            this.btnSecurityCode.Tag = this.cell2;
            this.btnSecurityCode.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Value_Name;
            this.btnSecurityCode.UseCustomerParameter = false;
            this.btnSecurityCode.YssAssociaType = YssSecInformation.Support.Context.AssociaType.sv_sec;
            this.btnSecurityCode.YssCaption = "对价派息交易证券";
            this.btnSecurityCode.YssIsMust = true;
            this.btnSecurityCode.SelectedValueChanged += new System.EventHandler(this.btnSecurityCode_SelectedValueChanged);
            // 
            // Frm_SEC_EQU_PX_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.ClientSize = new System.Drawing.Size(493, 304);
            this.Name = "Frm_SEC_EQU_PX_S";
            this.StatuType = "新建(&N)...";
            this.Text = "对价派息";
            this.YssSetStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.Load += new System.EventHandler(this.FrmDividend_Load);
            this.tbMain.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }
        #endregion
        #region
        private Yss.KTable.Models.Row row1;
        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Row row2;
        private Yss.KTable.Models.Row row3;
        private Yss.KTable.Models.Row row4;
        private Yss.KTable.Models.Row row5;
        private Yss.KTable.Models.Row row6;
        private Yss.KTable.Models.Column column2;
        private Yss.KTable.Models.Column column3;
        private Yss.KTable.Models.Column column4;
        private Yss.KTable.Models.Column column5;
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Cell cell2;
        private Yss.KTable.Models.Cell cell3;
        private Yss.KTable.Models.Cell cell4;
        private Yss.KTable.Models.Cell cell5;
        private Yss.KTable.Models.Row row7;
        private FAST.Core.BaseControl.YssSelCombox cboCurry;
        private Yss.KTable.Models.Cell cell6;
        private Yss.KTable.Models.Cell cell7;
        private Yss.KTable.Models.Cell cell8;
        private Yss.KTable.Models.Cell cell9;
        private Yss.KTable.Models.Cell cell10;
        private YssTextBox txtPreTaxRatio;
        private Yss.KTable.Models.Cell cell11;
        private Yss.KTable.Models.Cell cell12;
        private Yss.KTable.Models.Cell cell13;
        private Yss.KTable.Models.Cell cell14;
        private Yss.KTable.Models.Cell cell15;
        private YssTextBox txtAfterTaxRatio;
        private Yss.KTable.Models.Cell cell16;
        private Yss.KTable.Models.Cell cell17;
        private Yss.KTable.Models.Cell cell18;
        private Yss.KTable.Models.Cell cell19;
        private Yss.KTable.Models.Cell cell20;
        private Yss.KTable.Models.Cell cell21;
        private Yss.KTable.Models.Cell cell22;
        private FAST.Core.BaseControl.YssSelCombox cboDividendType;
        private FAST.Core.BaseControl.YssSelCombox cboInterestCategory;
        private Yss.KTable.Models.Row row8;
        private Yss.KTable.Models.Row row9;
        private YssDevComponents.DotNetBar.BalloonTip balloonTip;

        #endregion
        private FAST.Core.BaseControl.YssDateTimeInterval dtpPayDate;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpExRightDate;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpRegisterDate;
        private Yss.KTable.Models.Row row10;
        private Yss.KTable.Models.Cell cell23;
        private Yss.KTable.Models.Cell cell24;
        private Yss.KRichEx.YssTextBox txtDistPrice;
        private FAST.Core.BaseControl.PenetrationComboBox btnSecurityCode;
    }

}