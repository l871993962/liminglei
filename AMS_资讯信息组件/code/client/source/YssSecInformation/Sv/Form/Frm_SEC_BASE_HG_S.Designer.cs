﻿﻿using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;

using FAST.Core.Resource;
using FAST.Core.Context;
namespace YssSecInformation.Sv.Form
{
    partial class Frm_SEC_BASE_HG_S
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo4 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo3 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo2 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo1 = new FAST.Core.BaseControl.ControlMethodInfo();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_SEC_BASE_HG_S));
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.row3 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.txtSecCode = new Yss.KRichEx.YssTextBox();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.txtIsinCode = new Yss.KRichEx.YssTextBox();
            this.row4 = new Yss.KTable.Models.Row();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.txtSecMarketCode = new Yss.KRichEx.YssTextBox();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.txtPurchaseName = new Yss.KRichEx.YssTextBox();
            this.row5 = new Yss.KTable.Models.Row();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.cboPurchaseType = new FAST.Core.BaseControl.YssSelCombox();
            this.cell13 = new Yss.KTable.Models.Cell();
            this.cell14 = new Yss.KTable.Models.Cell();
            this.cell15 = new Yss.KTable.Models.Cell();
            this.cboMrket = new FAST.Core.BaseControl.YssSelCombox();
            this.row6 = new Yss.KTable.Models.Row();
            this.cell16 = new Yss.KTable.Models.Cell();
            this.cell17 = new Yss.KTable.Models.Cell();
            this.cboCury = new FAST.Core.BaseControl.YssSelCombox();
            this.cell18 = new Yss.KTable.Models.Cell();
            this.cell19 = new Yss.KTable.Models.Cell();
            this.cell20 = new Yss.KTable.Models.Cell();
            this.txtPurPeriod = new Yss.KRichEx.ImprovedTextBox();
            this.row7 = new Yss.KTable.Models.Row();
            this.cell21 = new Yss.KTable.Models.Cell();
            this.cell22 = new Yss.KTable.Models.Cell();
            this.txtHandAmount = new Yss.KRichEx.YssTextBox();
            this.cell28 = new Yss.KTable.Models.Cell();
            this.cell29 = new Yss.KTable.Models.Cell();
            this.cell30 = new Yss.KTable.Models.Cell();
            this.cboIssureMode = new FAST.Core.BaseControl.YssSelCombox();
            this.iniRateDays = new Yss.KRichEx.IntegerInputEx();
            this.cell32 = new Yss.KTable.Models.Cell();
            this.row8 = new Yss.KTable.Models.Row();
            this.row9 = new Yss.KTable.Models.Row();
            this.row10 = new Yss.KTable.Models.Row();
            this.cell23 = new Yss.KTable.Models.Cell();
            this.cell24 = new Yss.KTable.Models.Cell();
            this.dtpMarketDate = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell25 = new Yss.KTable.Models.Cell();
            this.cell26 = new Yss.KTable.Models.Cell();
            this.cell27 = new Yss.KTable.Models.Cell();
            this.dtpDelistDate = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.row11 = new Yss.KTable.Models.Row();
            this.cell31 = new Yss.KTable.Models.Cell();
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
            this.tbMain.Controls.Add(this.cboIssureMode);
            this.tbMain.Controls.Add(this.dtpDelistDate);
            this.tbMain.Controls.Add(this.dtpMarketDate);
            this.tbMain.Controls.Add(this.txtHandAmount);
            this.tbMain.Controls.Add(this.cboCury);
            this.tbMain.Controls.Add(this.txtIsinCode);
            this.tbMain.Controls.Add(this.iniRateDays);
            this.tbMain.Controls.Add(this.txtPurchaseName);
            this.tbMain.Controls.Add(this.txtSecCode);
            this.tbMain.Controls.Add(this.cboMrket);
            this.tbMain.Controls.Add(this.cboPurchaseType);
            this.tbMain.Controls.Add(this.txtSecMarketCode);
            this.tbMain.Controls.Add(this.txtPurPeriod);
            this.tbMain.GridLineColor = System.Drawing.Color.White;
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3,
            this.row4,
            this.row5,
            this.row6,
            this.row7,
            this.row11,
            this.row8,
            this.row9,
            this.row10});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(493, 255);
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
            this.stBarBottom.Location = new System.Drawing.Point(0, 286);
            this.stBarBottom.StatuType = "新增(&Add...)";
            // 
            // pnlMain
            // 
            this.pnlMain.Size = new System.Drawing.Size(493, 311);
            //this.pnlMain.Style.Alignment = System.Drawing.StringAlignment.Center;
            //this.pnlMain.Style.BackColor1.Color = System.Drawing.Color.White;
            //this.pnlMain.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            //this.pnlMain.Style.BorderColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBorder;
            //this.pnlMain.Style.BorderSide = YssDevComponents.DotNetBar.eBorderSide.Top;
            //this.pnlMain.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            //this.pnlMain.Style.GradientAngle = 90;
            // 
            // yssPanel1
            // 
            this.yssPanel1.BackColor = System.Drawing.Color.Transparent;
            this.yssPanel1.Size = new System.Drawing.Size(493, 311);
            // 
            // hpAssist
            // 
            // 
            // column1
            // 
            this.column1.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column1.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column1.DataPropertyName = null;
            this.column1.DataType = Yss.KTable.Enums.ColumnDataType.None;
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.column1.Tag = null;
            this.column1.Text = "";
            this.column1.Width = 110;
            // 
            // column2
            // 
            this.column2.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column2.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column2.DataPropertyName = null;
            this.column2.DataType = Yss.KTable.Enums.ColumnDataType.None;
            this.column2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.column2.Tag = null;
            this.column2.Text = "";
            this.column2.Width = 122;
            // 
            // column3
            // 
            this.column3.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column3.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column3.DataPropertyName = null;
            this.column3.DataType = Yss.KTable.Enums.ColumnDataType.None;
            this.column3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.column3.Tag = null;
            this.column3.Text = "";
            this.column3.Width = 30;
            // 
            // column4
            // 
            this.column4.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column4.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column4.DataPropertyName = null;
            this.column4.DataType = Yss.KTable.Enums.ColumnDataType.None;
            this.column4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.column4.Tag = null;
            this.column4.Text = "";
            this.column4.Width = 91;
            // 
            // column5
            // 
            this.column5.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column5.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column5.DataPropertyName = null;
            this.column5.DataType = Yss.KTable.Enums.ColumnDataType.None;
            this.column5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.column5.Tag = null;
            this.column5.Text = "";
            this.column5.Width = 122;
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("新宋体", 10F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row1.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row1.FullRowSelected = false;
            this.row1.GroupLineLength = 310;
            this.row1.GroupPosition = 13;
            this.row1.Height = 33;
            this.row1.IsGroup = true;
            this.row1.ShowCheckBox = true;
            this.row1.Text = "基本信息";
            // 
            // row2
            // 
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row2.ForeColor = System.Drawing.Color.Black;
            this.row2.FullRowSelected = false;
            this.row2.GroupLineLength = 100;
            this.row2.GroupPosition = -16;
            this.row2.Height = 10;
            this.row2.ShowCheckBox = true;
            this.row2.Text = null;
            // 
            // row3
            // 
            this.row3.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell1,
            this.cell2,
            this.cell3,
            this.cell4,
            this.cell5});
            this.row3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row3.ForeColor = System.Drawing.Color.Black;
            this.row3.FullRowSelected = false;
            this.row3.GroupLineLength = 100;
            this.row3.GroupPosition = -16;
            this.row3.Height = 25;
            this.row3.ShowCheckBox = true;
            this.row3.Text = null;
            // 
            // cell1
            // 
            this.cell1.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.Text = "   证券内码：";
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.txtSecCode;
            // 
            // txtSecCode
            // 
            this.txtSecCode.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.txtSecCode.Border.Class = "TextBoxBorder";
            this.txtSecCode.Location = new System.Drawing.Point(110, 43);
            this.txtSecCode.Name = "txtSecCode";
            this.txtSecCode.Size = new System.Drawing.Size(121, 21);
            this.txtSecCode.TabIndex = 0;
            this.txtSecCode.Tag = this.cell2;
            this.txtSecCode.YssCaption = "证券内码";
            this.txtSecCode.YssIsMust = true;
            this.txtSecCode.YssLength = 50;
            // 
            // cell3
            // 
            this.cell3.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell3.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell4
            // 
            this.cell4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell4.Text = "ISIN代码：";
            // 
            // cell5
            // 
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.txtIsinCode;
            // 
            // txtIsinCode
            // 
            this.txtIsinCode.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.txtIsinCode.Border.Class = "TextBoxBorder";
            this.txtIsinCode.Location = new System.Drawing.Point(353, 43);
            this.txtIsinCode.Name = "txtIsinCode";
            this.txtIsinCode.Size = new System.Drawing.Size(121, 21);
            this.txtIsinCode.TabIndex = 1;
            this.txtIsinCode.Tag = this.cell5;
            this.txtIsinCode.Leave += new System.EventHandler(this.txtIsinCode_Leave);
            // 
            // row4
            // 
            this.row4.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell6,
            this.cell7,
            this.cell8,
            this.cell9,
            this.cell10});
            this.row4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row4.ForeColor = System.Drawing.Color.Black;
            this.row4.FullRowSelected = false;
            this.row4.GroupLineLength = 100;
            this.row4.GroupPosition = -16;
            this.row4.Height = 25;
            this.row4.ShowCheckBox = true;
            this.row4.Text = null;
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.Text = "   上市代码：";
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.InnerControl = this.txtSecMarketCode;
            // 
            // txtSecMarketCode
            // 
            this.txtSecMarketCode.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.txtSecMarketCode.Border.Class = "TextBoxBorder";
            this.txtSecMarketCode.Location = new System.Drawing.Point(110, 68);
            this.txtSecMarketCode.Name = "txtSecMarketCode";
            this.txtSecMarketCode.Size = new System.Drawing.Size(121, 21);
            this.txtSecMarketCode.TabIndex = 2;
            this.txtSecMarketCode.Tag = this.cell7;
            this.txtSecMarketCode.YssCaption = "上市代码";
            this.txtSecMarketCode.YssIsMust = true;
            this.txtSecMarketCode.YssLength = 30;
            this.txtSecMarketCode.Leave += new System.EventHandler(this.txtSecMarketCode_Leave);
            // 
            // cell8
            // 
            this.cell8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell8.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell9
            // 
            this.cell9.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell9.Text = "回购名称：";
            // 
            // cell10
            // 
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.InnerControl = this.txtPurchaseName;
            // 
            // txtPurchaseName
            // 
            this.txtPurchaseName.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.txtPurchaseName.Border.Class = "TextBoxBorder";
            this.txtPurchaseName.Location = new System.Drawing.Point(353, 68);
            this.txtPurchaseName.Name = "txtPurchaseName";
            this.txtPurchaseName.Size = new System.Drawing.Size(121, 21);
            this.txtPurchaseName.TabIndex = 3;
            this.txtPurchaseName.Tag = this.cell10;
            this.txtPurchaseName.YssCaption = "回购名称";
            this.txtPurchaseName.YssIsMust = true;
            this.txtPurchaseName.YssLength = 50;
            this.txtPurchaseName.Leave += new System.EventHandler(this.txtPurchaseName_Leave);
            // 
            // row5
            // 
            this.row5.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell11,
            this.cell12,
            this.cell13,
            this.cell14,
            this.cell15});
            this.row5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row5.ForeColor = System.Drawing.Color.Black;
            this.row5.FullRowSelected = false;
            this.row5.GroupLineLength = 100;
            this.row5.GroupPosition = -16;
            this.row5.Height = 25;
            this.row5.ShowCheckBox = true;
            this.row5.Text = null;
            // 
            // cell11
            // 
            this.cell11.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell11.Text = "   证券品种：";
            // 
            // cell12
            // 
            this.cell12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell12.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell12.InnerControl = this.cboPurchaseType;
            // 
            // cboPurchaseType
            // 
            this.cboPurchaseType.AddedSelItemName = "";
            this.cboPurchaseType.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.cboPurchaseType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboPurchaseType.Border.Bottom = true;
            this.cboPurchaseType.Border.Left = true;
            this.cboPurchaseType.Border.Right = true;
            this.cboPurchaseType.Border.Top = true;
            this.cboPurchaseType.ClassName = "";
            this.cboPurchaseType.DisplayName = "C_SEC_VAR_NAME";
            this.cboPurchaseType.DisplayValue = "C_SEC_VAR_CODE";
            this.cboPurchaseType.DllName = "YssControls.dll";
            this.cboPurchaseType.FilterCond = "";
            this.cboPurchaseType.KTableTree = true;
            this.cboPurchaseType.Location = new System.Drawing.Point(110, 93);
            this.cboPurchaseType.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo4.MethodName = "getDataListByTypes";
            controlMethodInfo4.MethodParams = null;
            controlMethodInfo4.MethodParamValues = new string[] {
        "HG,"};
            controlMethodInfo4.Methods = new string[] {
        "getDataListByTypes",
        "getQueryResByTypes",
        "getDataListByKeys",
        "getQueryResByKeys",
        "getDataByCode",
        "getDataList",
        "getDataListRes",
        "getKeyConvertMap"};
            this.cboPurchaseType.MethodInfo = controlMethodInfo4;
            this.cboPurchaseType.Name = "cboPurchaseType";
            this.cboPurchaseType.NodeID = "C_SEC_VAR_CODE";
            this.cboPurchaseType.Padding = new System.Windows.Forms.Padding(1, 3, 1, 3);
            this.cboPurchaseType.Parameter = "C_SEC_VAR_NAME";
            this.cboPurchaseType.ParaNodeID = "C_DA_CODE_P";
            this.cboPurchaseType.QueryCond = "HG";
            this.cboPurchaseType.QueryType = "CacheType";
            this.cboPurchaseType.Size = new System.Drawing.Size(121, 21);
            this.cboPurchaseType.SortColumn = "C_SEC_VAR_NAME";
            this.cboPurchaseType.TabIndex = 4;
            this.cboPurchaseType.Tag = this.cell12;
            this.cboPurchaseType.YssAssociaType = YssInformation.Support.Context.AssociaType.base_seccategory;
            this.cboPurchaseType.YssCaption = "证券品种";
            this.cboPurchaseType.YssIsMust = true;
            this.cboPurchaseType.YssKiloDelimiter = true;
            // 
            // cell13
            // 
            this.cell13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell13.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell14
            // 
            this.cell14.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell14.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell14.Text = "交易市场：";
            // 
            // cell15
            // 
            this.cell15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell15.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell15.InnerControl = this.cboMrket;
            // 
            // cboMrket
            // 
            this.cboMrket.AddedSelItemName = "";
            this.cboMrket.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.cboMrket.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboMrket.Border.Bottom = true;
            this.cboMrket.Border.Left = true;
            this.cboMrket.Border.Right = true;
            this.cboMrket.Border.Top = true;
            this.cboMrket.ClassName = "";
            this.cboMrket.DisplayName = "C_MKT_NAME";
            this.cboMrket.DisplayValue = "C_MKT_NO";
            this.cboMrket.DllName = "YssControls.dll";
            this.cboMrket.FilterCond = "";
            this.cboMrket.KTableTree = true;
            this.cboMrket.Location = new System.Drawing.Point(353, 93);
            this.cboMrket.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo3.MethodName = "getDataList";
            controlMethodInfo3.MethodParams = null;
            controlMethodInfo3.MethodParamValues = null;
            controlMethodInfo3.Methods = new string[] {
        "getDataListByTypes",
        "getQueryResByTypes",
        "getDataListByKeys",
        "getQueryResByKeys",
        "getDataByCode",
        "getDataList",
        "getDataListRes",
        "getKeyConvertMap"};
            this.cboMrket.MethodInfo = controlMethodInfo3;
            this.cboMrket.Name = "cboMrket";
            this.cboMrket.NodeID = "C_MKT_CODE";
            this.cboMrket.Padding = new System.Windows.Forms.Padding(1, 3, 1, 3);
            this.cboMrket.Parameter = "C_MKT_CODE~C_MKT_NAME";
            this.cboMrket.ParaNodeID = "C_PARAENT_CODE";
            this.cboMrket.QueryCond = "";
            this.cboMrket.QueryType = "";
            this.cboMrket.Size = new System.Drawing.Size(121, 21);
            this.cboMrket.SortColumn = "C_MKT_NAME";
            this.cboMrket.TabIndex = 5;
            this.cboMrket.Tag = this.cell15;
            this.cboMrket.YssAssociaType = YssInformation.Support.Context.AssociaType.base_exchange;
            this.cboMrket.YssCaption = "交易市场";
            this.cboMrket.YssIsMust = true;
            this.cboMrket.YssKiloDelimiter = true;
            // 
            // row6
            // 
            this.row6.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell16,
            this.cell17,
            this.cell18,
            this.cell19,
            this.cell20});
            this.row6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row6.ForeColor = System.Drawing.Color.Black;
            this.row6.FullRowSelected = false;
            this.row6.GroupLineLength = 100;
            this.row6.GroupPosition = -16;
            this.row6.Height = 25;
            this.row6.ShowCheckBox = true;
            this.row6.Text = null;
            // 
            // cell16
            // 
            this.cell16.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell16.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell16.Text = "   交易币种：";
            // 
            // cell17
            // 
            this.cell17.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell17.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell17.InnerControl = this.cboCury;
            // 
            // cboCury
            // 
            this.cboCury.AddedSelItemName = "";
            this.cboCury.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.cboCury.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboCury.Border.Bottom = true;
            this.cboCury.Border.Left = true;
            this.cboCury.Border.Right = true;
            this.cboCury.Border.Top = true;
            this.cboCury.ClassName = "";
            this.cboCury.DisplayName = "C_DC_NAME";
            this.cboCury.DisplayValue = "C_DC_CODE";
            this.cboCury.DllName = "YssControls.dll";
            this.cboCury.FilterCond = "";
            this.cboCury.Location = new System.Drawing.Point(110, 118);
            this.cboCury.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo2.MethodName = "getDataList";
            controlMethodInfo2.MethodParams = null;
            controlMethodInfo2.MethodParamValues = null;
            controlMethodInfo2.Methods = new string[] {
        "getPortCurruncyList",
        "getPortCurruncyListRes",
        "getDataListByTypes",
        "getQueryResByTypes",
        "getDataListByKeys",
        "getQueryResByKeys",
        "getDataByCode",
        "getDataList",
        "getDataListRes",
        "getKeyConvertMap"};
            this.cboCury.MethodInfo = controlMethodInfo2;
            this.cboCury.Name = "cboCury";
            this.cboCury.Padding = new System.Windows.Forms.Padding(1, 3, 1, 3);
            this.cboCury.Parameter = "C_DC_NAME";
            this.cboCury.QueryCond = "";
            this.cboCury.QueryType = "";
            this.cboCury.Size = new System.Drawing.Size(121, 21);
            this.cboCury.TabIndex = 6;
            this.cboCury.Tag = this.cell17;
            this.cboCury.YssAssociaType = YssInformation.Support.Context.AssociaType.base_currency;
            this.cboCury.YssCaption = "交易币种";
            this.cboCury.YssIsMust = true;
            this.cboCury.YssKiloDelimiter = true;
            // 
            // cell18
            // 
            this.cell18.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell18.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell19
            // 
            this.cell19.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell19.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell19.Text = "回购期限：";
            // 
            // cell20
            // 
            this.cell20.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell20.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell20.InnerControl = this.txtPurPeriod;
            // 
            // txtPurPeriod
            // 
            this.txtPurPeriod.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.txtPurPeriod.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.txtPurPeriod.Border.Bottom = true;
            this.txtPurPeriod.Border.Left = true;
            this.txtPurPeriod.Border.Right = true;
            this.txtPurPeriod.Border.Top = true;
            this.txtPurPeriod.IsFillDecimal = false;
            this.txtPurPeriod.Location = new System.Drawing.Point(353, 118);
            this.txtPurPeriod.Name = "txtPurPeriod";
            this.txtPurPeriod.Padding = new System.Windows.Forms.Padding(1);
            this.txtPurPeriod.Size = new System.Drawing.Size(121, 21);
            this.txtPurPeriod.Sufix = "天";
            this.txtPurPeriod.TabIndex = 19;
            this.txtPurPeriod.Tag = this.cell20;
            this.txtPurPeriod.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtPurPeriod.YssCaption = "回购期限";
            this.txtPurPeriod.YssIsMust = true;
            this.txtPurPeriod.YssLength = 14;
            this.txtPurPeriod.YssNumeric = "14, 0";
            // 
            // row7
            // 
            this.row7.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell21,
            this.cell22,
            this.cell28,
            this.cell29,
            this.cell30});
            this.row7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row7.ForeColor = System.Drawing.Color.Black;
            this.row7.FullRowSelected = false;
            this.row7.GroupLineLength = 100;
            this.row7.GroupPosition = -16;
            this.row7.Height = 25;
            this.row7.ShowCheckBox = true;
            this.row7.Text = null;
            // 
            // cell21
            // 
            this.cell21.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell21.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell21.Text = "   每手数量：";
            // 
            // cell22
            // 
            this.cell22.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell22.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell22.InnerControl = this.txtHandAmount;
            // 
            // txtHandAmount
            // 
            this.txtHandAmount.AutoTooltip = false;
            this.txtHandAmount.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.txtHandAmount.Border.Class = "TextBoxBorder";
            this.txtHandAmount.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.txtHandAmount.ForeColor = System.Drawing.Color.Blue;
            this.txtHandAmount.Location = new System.Drawing.Point(110, 143);
            this.txtHandAmount.Name = "txtHandAmount";
            this.txtHandAmount.Size = new System.Drawing.Size(121, 21);
            this.txtHandAmount.TabIndex = 8;
            this.txtHandAmount.Tag = this.cell22;
            this.txtHandAmount.Text = "10";
            this.txtHandAmount.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtHandAmount.YssCaption = "每手数量";
            this.txtHandAmount.YssIsMust = true;
            this.txtHandAmount.YssKiloDelimiter = true;
            this.txtHandAmount.YssLength = 18;
            this.txtHandAmount.YssNumeric = "14, 4";
            // 
            // cell28
            // 
            this.cell28.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell28.ForeColor = System.Drawing.Color.Black;
            // 
            // cell29
            // 
            this.cell29.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell29.ForeColor = System.Drawing.Color.Black;
            this.cell29.Text = "核算方式：";
            // 
            // cell30
            // 
            this.cell30.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell30.ForeColor = System.Drawing.Color.Black;
            this.cell30.InnerControl = this.cboIssureMode;
            // 
            // cboIssureMode
            // 
            this.cboIssureMode.AddedSelItemName = "";
            this.cboIssureMode.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboIssureMode.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboIssureMode.Border.Bottom = true;
            this.cboIssureMode.Border.Left = true;
            this.cboIssureMode.Border.Right = true;
            this.cboIssureMode.Border.Top = true;
            this.cboIssureMode.ClassName = "";
            this.cboIssureMode.DllName = "YssControls.dll";
            this.cboIssureMode.FilterCond = "";
            this.cboIssureMode.IsFillDecimal = false;
            this.cboIssureMode.Location = new System.Drawing.Point(353, 143);
            this.cboIssureMode.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataListByTypes";
            controlMethodInfo1.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo1.MethodParams")));
            controlMethodInfo1.MethodParamValues = new string[] {
        "HSFS,"};
            controlMethodInfo1.Methods = null;
            this.cboIssureMode.MethodInfo = controlMethodInfo1;
            this.cboIssureMode.Name = "cboIssureMode";
            this.cboIssureMode.Padding = new System.Windows.Forms.Padding(1, 2, 1, 3);
            this.cboIssureMode.QueryCond = "ISSUE_MODE";
            this.cboIssureMode.QueryType = "CacheType";
            this.cboIssureMode.Size = new System.Drawing.Size(121, 21);
            this.cboIssureMode.TabIndex = 18;
            this.cboIssureMode.Tag = this.cell30;
            this.cboIssureMode.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboIssureMode.YssCaption = "核算方式";
            // 
            // iniRateDays
            // 
            this.iniRateDays.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.iniRateDays.Border.BorderColor = System.Drawing.Color.Silver;
            this.iniRateDays.Border.Bottom = true;
            this.iniRateDays.Border.Left = true;
            this.iniRateDays.Border.Right = true;
            this.iniRateDays.Border.Top = true;
            this.iniRateDays.Location = new System.Drawing.Point(110, 168);
            this.iniRateDays.Name = "iniRateDays";
            this.iniRateDays.Prefix = "";
            this.iniRateDays.Size = new System.Drawing.Size(121, 21);
            this.iniRateDays.TabIndex = 9;
            this.iniRateDays.Tag = this.cell32;
            this.iniRateDays.Value = 360;
            // 
            // cell32
            // 
            this.cell32.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell32.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell32.InnerControl = this.iniRateDays;
            // 
            // row8
            // 
            this.row8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row8.ForeColor = System.Drawing.Color.Black;
            this.row8.FullRowSelected = false;
            this.row8.GroupLineLength = 310;
            this.row8.GroupPosition = 13;
            this.row8.Height = 10;
            this.row8.IsGroup = true;
            this.row8.ShowCheckBox = true;
            this.row8.Text = null;
            // 
            // row9
            // 
            this.row9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row9.ForeColor = System.Drawing.Color.Black;
            this.row9.FullRowSelected = false;
            this.row9.GroupLineLength = 100;
            this.row9.GroupPosition = -16;
            this.row9.Height = 10;
            this.row9.ShowCheckBox = true;
            this.row9.Text = null;
            // 
            // row10
            // 
            this.row10.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell23,
            this.cell24,
            this.cell25,
            this.cell26,
            this.cell27});
            this.row10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row10.ForeColor = System.Drawing.Color.Black;
            this.row10.FullRowSelected = false;
            this.row10.GroupLineLength = 100;
            this.row10.GroupPosition = 16;
            this.row10.Height = 25;
            this.row10.ShowCheckBox = true;
            this.row10.Text = null;
            // 
            // cell23
            // 
            this.cell23.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell23.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell23.Text = "   上市日期：";
            // 
            // cell24
            // 
            this.cell24.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell24.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell24.InnerControl = this.dtpMarketDate;
            // 
            // dtpMarketDate
            // 
            this.dtpMarketDate.BackColor = System.Drawing.Color.Transparent;
            this.dtpMarketDate.Location = new System.Drawing.Point(110, 211);
            this.dtpMarketDate.Margin = new System.Windows.Forms.Padding(0);
            this.dtpMarketDate.Name = "dtpMarketDate";
            this.dtpMarketDate.ReadOnly = false;
            this.dtpMarketDate.Size = new System.Drawing.Size(121, 21);
            this.dtpMarketDate.TabIndex = 10;
            this.dtpMarketDate.Tag = this.cell24;
            this.dtpMarketDate.yssEnabled = true;
            this.dtpMarketDate.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpMarketDate.yssInterval = false;
            this.dtpMarketDate.yssLabelStr = "至";
            this.dtpMarketDate.yssShowCheckBox = false;
            this.dtpMarketDate.yssShowOperLable = false;
            this.dtpMarketDate.YssShowSecond = true;
            this.dtpMarketDate.yssTimeControl = false;
            this.dtpMarketDate.BusinessDate = true;
            // 
            // cell25
            // 
            this.cell25.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell25.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell26
            // 
            this.cell26.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell26.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell26.Text = "退市日期：";
            // 
            // cell27
            // 
            this.cell27.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell27.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell27.InnerControl = this.dtpDelistDate;
            // 
            // dtpDelistDate
            // 
            this.dtpDelistDate.BackColor = System.Drawing.Color.Transparent;
            this.dtpDelistDate.Location = new System.Drawing.Point(353, 211);
            this.dtpDelistDate.Margin = new System.Windows.Forms.Padding(0);
            this.dtpDelistDate.Name = "dtpDelistDate";
            this.dtpDelistDate.ReadOnly = false;
            this.dtpDelistDate.Size = new System.Drawing.Size(121, 21);
            this.dtpDelistDate.TabIndex = 17;
            this.dtpDelistDate.Tag = this.cell27;
            this.dtpDelistDate.yssEnabled = true;
            this.dtpDelistDate.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpDelistDate.yssInterval = false;
            this.dtpDelistDate.yssLabelStr = "至";
            this.dtpDelistDate.yssShowCheckBox = false;
            this.dtpDelistDate.yssShowOperLable = false;
            this.dtpDelistDate.YssShowSecond = true;
            this.dtpDelistDate.yssTimeControl = false;
            this.dtpDelistDate.BusinessDate = true;
            // 
            // row11
            // 
            this.row11.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell31,
            this.cell32});
            this.row11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row11.FullRowSelected = false;
            this.row11.GroupLineLength = 100;
            this.row11.GroupPosition = 16;
            this.row11.Height = 23;
            this.row11.ShowCheckBox = true;
            this.row11.Text = null;
            // 
            // cell31
            // 
            this.cell31.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell31.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell31.Text = "   利率年化天数：";
            // 
            // Frm_SEC_BASE_HG_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(493, 311);
            this.DoubleBuffered = true;
            this.Name = "Frm_SEC_BASE_HG_S";
            this.StatuType = "新增(&Add...)";
            this.Text = "回购基本信息设置";
            this.YssSetStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.Load += new System.EventHandler(this.Frm_SEC_BASE_HG_S_Load);
            this.tbMain.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
        private Yss.KTable.Models.Column column3;
        private Yss.KTable.Models.Column column4;
        private Yss.KTable.Models.Column column5;
        private Yss.KTable.Models.Row row1;
        private Yss.KTable.Models.Row row2;
        private Yss.KTable.Models.Row row3;
        private Yss.KTable.Models.Row row4;
        private Yss.KTable.Models.Row row5;
        private Yss.KTable.Models.Row row6;
        private Yss.KTable.Models.Row row7;
        private Yss.KTable.Models.Row row8;
        private Yss.KTable.Models.Row row9;
        private Yss.KTable.Models.Row row10;
        private FAST.Core.BaseControl.YssSelCombox cboCury;
        private FAST.Core.BaseControl.YssSelCombox cboMrket;
        private FAST.Core.BaseControl.YssSelCombox cboPurchaseType;
        private Yss.KRichEx.YssTextBox txtIsinCode;
        private Yss.KRichEx.YssTextBox txtPurchaseName;
        private Yss.KRichEx.YssTextBox txtSecMarketCode;
        private Yss.KRichEx.YssTextBox txtSecCode;
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Cell cell2;
        private Yss.KTable.Models.Cell cell3;
        private Yss.KTable.Models.Cell cell4;
        private Yss.KTable.Models.Cell cell5;
        private Yss.KTable.Models.Cell cell6;
        private Yss.KTable.Models.Cell cell7;
        private Yss.KTable.Models.Cell cell8;
        private Yss.KTable.Models.Cell cell9;
        private Yss.KTable.Models.Cell cell10;
        private Yss.KTable.Models.Cell cell11;
        private Yss.KTable.Models.Cell cell12;
        private Yss.KTable.Models.Cell cell13;
        private Yss.KTable.Models.Cell cell14;
        private Yss.KTable.Models.Cell cell15;
        private Yss.KTable.Models.Cell cell16;
        private Yss.KTable.Models.Cell cell17;
        private Yss.KTable.Models.Cell cell18;
        private Yss.KTable.Models.Cell cell19;
        private Yss.KTable.Models.Cell cell20;
        private Yss.KTable.Models.Cell cell21;
        private Yss.KTable.Models.Cell cell22;
        private Yss.KTable.Models.Cell cell23;
        private Yss.KTable.Models.Cell cell24;
        private Yss.KTable.Models.Cell cell25;
        private Yss.KTable.Models.Cell cell26;
        private Yss.KTable.Models.Cell cell27;
        private Yss.KTable.Models.Cell cell28;
        private Yss.KTable.Models.Cell cell29;
        private Yss.KTable.Models.Cell cell30;
        private Yss.KRichEx.YssTextBox txtHandAmount;
        private Yss.KRichEx.IntegerInputEx iniRateDays;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpDelistDate;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpMarketDate;
        private Yss.KRichEx.ImprovedTextBox txtPurPeriod;
        private Yss.KTable.Models.Row row11;
        private Yss.KTable.Models.Cell cell31;
        private Yss.KTable.Models.Cell cell32;
        protected FAST.Core.BaseControl.YssSelCombox cboIssureMode;
    }
}