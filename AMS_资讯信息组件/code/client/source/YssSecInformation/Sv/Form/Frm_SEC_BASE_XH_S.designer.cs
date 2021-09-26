﻿using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;

using FAST.Core.Resource;
namespace YssSecInformation.Sv.Form
{
    partial class Frm_SEC_BASE_XH_S
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
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo3 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo2 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo1 = new FAST.Core.BaseControl.ControlMethodInfo();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_SEC_BASE_XH_S));
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
            this.txtXHCode = new Yss.KRichEx.YssTextBox();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.txtXHName = new Yss.KRichEx.YssTextBox();
            this.cboXHType = new FAST.Core.BaseControl.YssSelCombox();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.row5 = new Yss.KTable.Models.Row();
            this.cell11 = new Yss.KTable.Models.Cell();
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
            this.txtBJUnit = new Yss.KRichEx.ImprovedTextBox();
            this.row7 = new Yss.KTable.Models.Row();
            this.cell21 = new Yss.KTable.Models.Cell();
            this.cell22 = new Yss.KTable.Models.Cell();
            this.txtJYUnit = new Yss.KRichEx.ImprovedTextBox();
            this.cell28 = new Yss.KTable.Models.Cell();
            this.cell29 = new Yss.KTable.Models.Cell();
            this.cell30 = new Yss.KTable.Models.Cell();
            this.txtBZJRatio = new Yss.KRichEx.ImprovedTextBox();
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
            this.tbMain.SuspendLayout();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // stBarPnl
            // 
            this.StatuType = "<font color=\"#BA1419\"></font>新增(&Add...)";
            // 
            // tbMain
            // 
            // 
            // 
            // 
            this.tbMain.Border.BorderColor = System.Drawing.Color.Gray;
            this.tbMain.Border.Bottom = false;
            this.tbMain.Border.Left = false;
            this.tbMain.Border.Right = false;
            this.tbMain.Border.Top = false;
            this.tbMain.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.column1,
            this.column2,
            this.column3,
            this.column4,
            this.column5});
            this.tbMain.Controls.Add(this.txtBZJRatio);
            this.tbMain.Controls.Add(this.txtXHCode);
            this.tbMain.Controls.Add(this.cboCury);
            this.tbMain.Controls.Add(this.txtBJUnit);
            this.tbMain.Controls.Add(this.txtJYUnit);
            this.tbMain.Controls.Add(this.dtpDelistDate);
            this.tbMain.Controls.Add(this.dtpMarketDate);
            this.tbMain.Controls.Add(this.txtIsinCode);
            this.tbMain.Controls.Add(this.txtSecCode);
            this.tbMain.Controls.Add(this.txtXHName);
            this.tbMain.Controls.Add(this.cboMrket);
            this.tbMain.Controls.Add(this.cboXHType);
            this.tbMain.GridLineColor = System.Drawing.Color.RosyBrown;
            // 
            // 
            // 
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3,
            this.row4,
            this.row5,
            this.row6,
            this.row7,
            this.row8,
            this.row9,
            this.row10});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(503, 276);
            // 
            // btnBar
            // 
            this.btnBar.Size = new System.Drawing.Size(503, 30);
            // 
            // stBarBottom
            // 
            this.stBarBottom.Location = new System.Drawing.Point(0, 306);
            this.stBarBottom.Size = new System.Drawing.Size(503, 23);
            // 
            // panelExLine
            // 
            // 
            // pnlMain
            // 
            this.pnlMain.Size = new System.Drawing.Size(503, 329);
            //this.pnlMain.Style.Alignment = System.Drawing.StringAlignment.Center;
            //this.pnlMain.Style.BackColor1.Color = System.Drawing.Color.White;
            //this.pnlMain.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            //this.pnlMain.Style.BorderColor.Color = System.Drawing.Color.White;
            //this.pnlMain.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            //this.pnlMain.Style.GradientAngle = 90;
            // 
            // yssPanel1
            // 
            this.yssPanel1.Size = new System.Drawing.Size(503, 329);
            // 
            // column1
            // 
            this.column1.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column1.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column1.DataPropertyName = null;
            this.column1.ForeColor = System.Drawing.Color.Empty;
            this.column1.Tag = null;
            this.column1.Text = "";
            this.column1.Width = 110;
            // 
            // column2
            // 
            this.column2.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column2.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column2.DataPropertyName = null;
            this.column2.ForeColor = System.Drawing.Color.Empty;
            this.column2.Tag = null;
            this.column2.Text = "";
            this.column2.Width = 122;
            // 
            // column3
            // 
            this.column3.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column3.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column3.DataPropertyName = null;
            this.column3.ForeColor = System.Drawing.Color.Empty;
            this.column3.Tag = null;
            this.column3.Text = "";
            this.column3.Width = 30;
            // 
            // column4
            // 
            this.column4.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column4.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column4.DataPropertyName = null;
            this.column4.ForeColor = System.Drawing.Color.Empty;
            this.column4.Tag = null;
            this.column4.Text = "";
            this.column4.Width = 91;
            // 
            // column5
            // 
            this.column5.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column5.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column5.DataPropertyName = null;
            this.column5.ForeColor = System.Drawing.Color.Empty;
            this.column5.Tag = null;
            this.column5.Text = "";
            this.column5.Width = 122;
            // 
            // row1
            // 
            this.row1.BackColor = System.Drawing.Color.Empty;
            this.row1.Font = new System.Drawing.Font("新宋体", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row1.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row1.FullRowSelected = false;
            this.row1.GroupLineLength = 310;
            this.row1.GroupPosition = 13;
            this.row1.Height = 33;
            this.row1.IsGroup = true;
            this.row1.Key = null;
            this.row1.OwnTable = this.tbMain;
            this.row1.RowName = "row1";
            this.row1.ShowCheckBox = true;
            this.row1.Text = "基本信息";
            // 
            // row2
            // 
            this.row2.BackColor = System.Drawing.Color.Empty;
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row2.FullRowSelected = false;
            this.row2.GroupLineLength = 100;
            this.row2.GroupPosition = 16;
            this.row2.Height = 23;
            this.row2.Key = null;
            this.row2.OwnTable = this.tbMain;
            this.row2.RowName = "row2";
            this.row2.ShowCheckBox = true;
            // 
            // row3
            // 
            this.row3.BackColor = System.Drawing.Color.Empty;
            this.row3.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell1,
            this.cell2,
            this.cell3,
            this.cell4,
            this.cell5});
            this.row3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row3.FullRowSelected = false;
            this.row3.GroupLineLength = 100;
            this.row3.GroupPosition = 16;
            this.row3.Height = 23;
            this.row3.Key = null;
            this.row3.OwnTable = this.tbMain;
            this.row3.RowName = "row3";
            this.row3.ShowCheckBox = true;
            // 
            // cell1
            // 
            this.cell1.BackColor = System.Drawing.Color.Empty;
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.Key = null;
            this.cell1.Text = "   证券内码:";
            this.cell1.ToolTip = null;
            // 
            // cell2
            // 
            this.cell2.BackColor = System.Drawing.Color.Empty;
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.txtSecCode;
            this.cell2.Key = null;
            this.cell2.ToolTip = null;
            // 
            // txtSecCode
            // 
            this.txtSecCode.Border.Class = "TextBoxBorder";
            this.txtSecCode.Location = new System.Drawing.Point(110, 56);
            this.txtSecCode.Name = "txtSecCode";
            this.txtSecCode.Size = new System.Drawing.Size(121, 21);
            this.txtSecCode.TabIndex = 1;
            this.txtSecCode.Tag = this.cell2;
            this.txtSecCode.Value = "";
            this.txtSecCode.YssCaption = "证券内码";
            this.txtSecCode.YssIsMust = true;
            this.txtSecCode.YssLength = 50;
            this.txtSecCode.YssNumeric = "";
            // 
            // cell3
            // 
            this.cell3.BackColor = System.Drawing.Color.Empty;
            this.cell3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell3.Key = null;
            this.cell3.ToolTip = null;
            // 
            // cell4
            // 
            this.cell4.BackColor = System.Drawing.Color.Empty;
            this.cell4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell4.Key = null;
            this.cell4.Text = "ISIN代码：";
            this.cell4.ToolTip = null;
            // 
            // cell5
            // 
            this.cell5.BackColor = System.Drawing.Color.Empty;
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.txtIsinCode;
            this.cell5.Key = null;
            this.cell5.ToolTip = null;
            // 
            // txtIsinCode
            // 
            // 
            // 
            // 
            this.txtIsinCode.Border.Class = "TextBoxBorder";
            this.txtIsinCode.Location = new System.Drawing.Point(353, 56);
            this.txtIsinCode.Name = "txtIsinCode";
            this.txtIsinCode.Size = new System.Drawing.Size(121, 21);
            this.txtIsinCode.TabIndex = 2;
            this.txtIsinCode.Tag = this.cell5;
            this.txtIsinCode.Value = "";
            this.txtIsinCode.YssCaption = "";
            this.txtIsinCode.YssNumeric = "";
            this.txtIsinCode.Leave += new System.EventHandler(this.txtIsinCode_Leave);
            // 
            // row4
            // 
            this.row4.BackColor = System.Drawing.Color.Empty;
            this.row4.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell6,
            this.cell7,
            this.cell8,
            this.cell9,
            this.cell10});
            this.row4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row4.FullRowSelected = false;
            this.row4.GroupLineLength = 100;
            this.row4.GroupPosition = 16;
            this.row4.Height = 23;
            this.row4.Key = null;
            this.row4.OwnTable = this.tbMain;
            this.row4.RowName = "row4";
            this.row4.ShowCheckBox = true;
            // 
            // cell6
            // 
            this.cell6.BackColor = System.Drawing.Color.Empty;
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.Key = null;
            this.cell6.Text = "   现货代码：";
            this.cell6.ToolTip = null;
            // 
            // cell7
            // 
            this.cell7.BackColor = System.Drawing.Color.Empty;
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.InnerControl = this.txtXHCode;
            this.cell7.Key = null;
            this.cell7.ToolTip = null;
            // 
            // txtXHCode
            // 
            this.txtXHCode.Border.Class = "TextBoxBorder";
            this.txtXHCode.Location = new System.Drawing.Point(110, 79);
            this.txtXHCode.Name = "txtXHCode";
            this.txtXHCode.Size = new System.Drawing.Size(121, 21);
            this.txtXHCode.TabIndex = 3;
            this.txtXHCode.Tag = this.cell7;
            this.txtXHCode.Value = "";
            this.txtXHCode.YssCaption = "现货代码";
            this.txtXHCode.YssIsMust = true;
            this.txtXHCode.YssNumeric = "";
            this.txtXHCode.YssLength = 30;
            this.txtXHCode.Leave += new System.EventHandler(this.txtSecMarketCode_Leave);
            // 
            // cell8
            // 
            this.cell8.BackColor = System.Drawing.Color.Empty;
            this.cell8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell8.Key = null;
            this.cell8.ToolTip = null;
            // 
            // cell9
            // 
            this.cell9.BackColor = System.Drawing.Color.Empty;
            this.cell9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell9.Key = null;
            this.cell9.Text = "现货名称：";
            this.cell9.ToolTip = null;
            // 
            // cell10
            // 
            this.cell10.BackColor = System.Drawing.Color.Empty;
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.InnerControl = this.txtXHName;
            this.cell10.Key = null;
            this.cell10.ToolTip = null;
            // 
            // txtXHName
            // 
            this.txtXHName.Border.Class = "TextBoxBorder";
            this.txtXHName.Location = new System.Drawing.Point(353, 79);
            this.txtXHName.Name = "txtXHName";
            this.txtXHName.Size = new System.Drawing.Size(121, 21);
            this.txtXHName.TabIndex = 4;
            this.txtXHName.Tag = this.cell10;
            this.txtXHName.Value = "";
            this.txtXHName.YssCaption = "现货名称";
            this.txtXHName.YssIsMust = true;
            this.txtXHName.YssLength = 50;
            this.txtXHName.YssNumeric = "";
            // 
            // cboXHType
            // 
            this.cboXHType.AddedSelItemName = "";
            this.cboXHType.BackColor = System.Drawing.Color.LightYellow;
            this.cboXHType.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.cboXHType.ClassName = "";
            this.cboXHType.DisplayName = "C_SEC_VAR_NAME";
            this.cboXHType.DisplayValue = "C_SEC_VAR_CODE";
            this.cboXHType.DllName = "YssControls.dll";
            this.cboXHType.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDown;
            this.cboXHType.FilterCond = "";
            this.cboXHType.IsFillDecimal = true;
            this.cboXHType.IsFocused = false;
            this.cboXHType.KTableTree = true;
            this.cboXHType.Location = new System.Drawing.Point(110, 102);
            this.cboXHType.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo3.MethodName = "getDataListByTypes";
            controlMethodInfo3.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo3.MethodParams")));
            controlMethodInfo3.MethodParamValues = new string[] {
        "XH,"};
            controlMethodInfo3.Methods = null;
            this.cboXHType.MethodInfo = controlMethodInfo3;
            this.cboXHType.Name = "cboXHType";
            this.cboXHType.NodeID = "C_SEC_VAR_CODE";
            this.cboXHType.Padding = new System.Windows.Forms.Padding(1, 3, 1, 3);
            this.cboXHType.Parameter = "C_SEC_VAR_NAME";
            this.cboXHType.ParaNodeID = "C_DA_CODE_P";
            this.cboXHType.PasswordChar = '\0';
            this.cboXHType.PopupTitle = "数据筛选";
            // 
            // 
            // 
            this.cboXHType.QueryCond = "XH";
            this.cboXHType.QueryType = "CacheType";
            this.cboXHType.RequestEveryTime = false;
            this.cboXHType.ShowCheckBox = false;
            this.cboXHType.ShowColumnHeader = false;
            this.cboXHType.Size = new System.Drawing.Size(121, 21);
            // 
            // 
            // 
            this.cboXHType.TabIndex = 5;
            this.cboXHType.Tag = this.cell12;
            this.cboXHType.TextAlign = System.Windows.Forms.HorizontalAlignment.Left;
            this.cboXHType.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
            this.cboXHType.TipText = "";
            this.cboXHType.TriggerTextLength = 1;
            this.cboXHType.UseErrorTip = true;
            this.cboXHType.YssAssociaType = YssInformation.Support.Context.AssociaType.base_seccategory;
            this.cboXHType.YssCaption = "证券品种";
            this.cboXHType.YssIsMust = true;
            this.cboXHType.YssKiloDelimiter = true;
            this.cboXHType.YssLength = 20;
            this.cboXHType.YssNumeric = "";
            this.cboXHType.YssReadOnly = false;
            this.cboXHType.YssShowButton = true;
            // 
            // cell12
            // 
            this.cell12.BackColor = System.Drawing.Color.Empty;
            this.cell12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell12.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell12.InnerControl = this.cboXHType;
            this.cell12.Key = null;
            this.cell12.ToolTip = null;
            // 
            // row5
            // 
            this.row5.BackColor = System.Drawing.Color.Empty;
            this.row5.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell11,
            this.cell12,
            this.cell13,
            this.cell14,
            this.cell15});
            this.row5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row5.FullRowSelected = false;
            this.row5.GroupLineLength = 100;
            this.row5.GroupPosition = 16;
            this.row5.Height = 23;
            this.row5.Key = null;
            this.row5.OwnTable = this.tbMain;
            this.row5.RowName = "row5";
            this.row5.ShowCheckBox = true;
            // 
            // cell11
            // 
            this.cell11.BackColor = System.Drawing.Color.Empty;
            this.cell11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell11.Key = null;
            this.cell11.Text = "   证券品种：";
            this.cell11.ToolTip = null;
            // 
            // cell13
            // 
            this.cell13.BackColor = System.Drawing.Color.Empty;
            this.cell13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell13.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell13.Key = null;
            this.cell13.ToolTip = null;
            // 
            // cell14
            // 
            this.cell14.BackColor = System.Drawing.Color.Empty;
            this.cell14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell14.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell14.Key = null;
            this.cell14.Text = "交易市场：";
            this.cell14.ToolTip = null;
            // 
            // cell15
            // 
            this.cell15.BackColor = System.Drawing.Color.Empty;
            this.cell15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell15.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell15.InnerControl = this.cboMrket;
            this.cell15.Key = null;
            this.cell15.ToolTip = null;
            // 
            // cboMrket
            // 
            this.cboMrket.AddedSelItemName = "";
            this.cboMrket.BackColor = System.Drawing.Color.LightYellow;
            this.cboMrket.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.cboMrket.ClassName = "";
            this.cboMrket.DisplayName = "C_MKT_NAME";
            this.cboMrket.DisplayValue = "C_MKT_NO";
            this.cboMrket.DllName = "YssControls.dll";
            this.cboMrket.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDown;
            this.cboMrket.FilterCond = "";
            this.cboMrket.IsFillDecimal = true;
            this.cboMrket.IsFocused = false;
            this.cboMrket.KTableTree = true;
            this.cboMrket.Location = new System.Drawing.Point(353, 102);
            this.cboMrket.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo2.MethodName = "getDataList";
            controlMethodInfo2.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo2.MethodParams")));
            controlMethodInfo2.MethodParamValues = null;
            controlMethodInfo2.Methods = null;
            this.cboMrket.MethodInfo = controlMethodInfo2;
            this.cboMrket.Name = "cboMrket";
            this.cboMrket.NodeID = "C_MKT_CODE";
            this.cboMrket.Padding = new System.Windows.Forms.Padding(1, 3, 1, 3);
            this.cboMrket.Parameter = "C_MKT_CODE~C_MKT_NAME";
            this.cboMrket.ParaNodeID = "C_PARAENT_CODE";
            this.cboMrket.PasswordChar = '\0';
            this.cboMrket.PopupTitle = "数据筛选";
            // 
            // 
            // 
            this.cboMrket.QueryCond = "";
            this.cboMrket.QueryType = "";
            this.cboMrket.RequestEveryTime = false;
            this.cboMrket.ShowCheckBox = false;
            this.cboMrket.ShowColumnHeader = false;
            this.cboMrket.Size = new System.Drawing.Size(121, 21);
            // 
            // 
            // 
            this.cboMrket.TabIndex = 6;
            this.cboMrket.Tag = this.cell15;
            this.cboMrket.TextAlign = System.Windows.Forms.HorizontalAlignment.Left;
            this.cboMrket.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
            this.cboMrket.TipText = "";
            this.cboMrket.TriggerTextLength = 1;
            this.cboMrket.UseErrorTip = true;
            this.cboMrket.YssAssociaType = YssInformation.Support.Context.AssociaType.base_exchange;
            this.cboMrket.YssCaption = "交易市场";
            this.cboMrket.YssIsMust = true;
            this.cboMrket.YssKiloDelimiter = true;
            this.cboMrket.YssLength = 20;
            this.cboMrket.YssNumeric = "";
            this.cboMrket.YssReadOnly = false;
            this.cboMrket.YssShowButton = true;
            // 
            // row6
            // 
            this.row6.BackColor = System.Drawing.Color.Empty;
            this.row6.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell16,
            this.cell17,
            this.cell18,
            this.cell19,
            this.cell20});
            this.row6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row6.FullRowSelected = false;
            this.row6.GroupLineLength = 100;
            this.row6.GroupPosition = 16;
            this.row6.Height = 23;
            this.row6.Key = null;
            this.row6.OwnTable = this.tbMain;
            this.row6.RowName = "row6";
            this.row6.ShowCheckBox = true;
            // 
            // cell16
            // 
            this.cell16.BackColor = System.Drawing.Color.Empty;
            this.cell16.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell16.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell16.Key = null;
            this.cell16.Text = "   交易币种：";
            this.cell16.ToolTip = null;
            // 
            // cell17
            // 
            this.cell17.BackColor = System.Drawing.Color.Empty;
            this.cell17.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell17.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell17.InnerControl = this.cboCury;
            this.cell17.Key = null;
            this.cell17.ToolTip = null;
            // 
            // cboCury
            // 
            this.cboCury.AddedSelItemName = "";
            this.cboCury.BackColor = System.Drawing.Color.LightYellow;
            this.cboCury.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.cboCury.ClassName = "";
            this.cboCury.DisplayName = "C_DC_NAME";
            this.cboCury.DisplayValue = "C_DC_CODE";
            this.cboCury.DllName = "YssControls.dll";
            this.cboCury.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDown;
            this.cboCury.FilterCond = "";
            this.cboCury.IsFillDecimal = true;
            this.cboCury.IsFocused = false;
            this.cboCury.KTableTree = false;
            this.cboCury.Location = new System.Drawing.Point(110, 125);
            this.cboCury.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataList";
            controlMethodInfo1.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo1.MethodParams")));
            controlMethodInfo1.MethodParamValues = null;
            controlMethodInfo1.Methods = null;
            this.cboCury.MethodInfo = controlMethodInfo1;
            this.cboCury.Name = "cboCury";
            this.cboCury.NodeID = "";
            this.cboCury.Padding = new System.Windows.Forms.Padding(1, 3, 1, 3);
            this.cboCury.Parameter = "C_DC_NAME";
            this.cboCury.ParaNodeID = "";
            this.cboCury.PasswordChar = '\0';
            this.cboCury.PopupTitle = "数据筛选";
            // 
            // 
            // 
            this.cboCury.QueryCond = "";
            this.cboCury.QueryType = "";
            this.cboCury.RequestEveryTime = false;
            this.cboCury.ShowCheckBox = false;
            this.cboCury.ShowColumnHeader = false;
            this.cboCury.Size = new System.Drawing.Size(121, 21);
            // 
            // 
            // 
            this.cboCury.TabIndex = 7;
            this.cboCury.Tag = this.cell17;
            this.cboCury.TextAlign = System.Windows.Forms.HorizontalAlignment.Left;
            this.cboCury.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
            this.cboCury.TipText = "";
            this.cboCury.TriggerTextLength = 1;
            this.cboCury.UseErrorTip = true;
            this.cboCury.YssAssociaType = YssInformation.Support.Context.AssociaType.base_currency;
            this.cboCury.YssCaption = "交易币种";
            this.cboCury.YssIsMust = true;
            this.cboCury.YssKiloDelimiter = true;
            this.cboCury.YssLength = 20;
            this.cboCury.YssNumeric = "";
            this.cboCury.YssReadOnly = false;
            this.cboCury.YssShowButton = true;
            // 
            // cell18
            // 
            this.cell18.BackColor = System.Drawing.Color.Empty;
            this.cell18.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell18.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell18.Key = null;
            this.cell18.ToolTip = null;
            // 
            // cell19
            // 
            this.cell19.BackColor = System.Drawing.Color.Empty;
            this.cell19.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell19.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell19.Key = null;
            this.cell19.Text = "报价单位：";
            this.cell19.ToolTip = null;
            // 
            // cell20
            // 
            this.cell20.BackColor = System.Drawing.Color.Empty;
            this.cell20.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell20.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell20.InnerControl = this.txtBJUnit;
            this.cell20.Key = null;
            this.cell20.ToolTip = null;
            // 
            // txtBJUnit
            // 
            this.txtBJUnit.BackColor = System.Drawing.Color.White;
            this.txtBJUnit.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.txtBJUnit.IsFillDecimal = true;
            this.txtBJUnit.Location = new System.Drawing.Point(353, 125);
            this.txtBJUnit.Name = "txtBJUnit";
            this.txtBJUnit.Padding = new System.Windows.Forms.Padding(1);
            this.txtBJUnit.PasswordChar = '\0';
            // 
            // 
            // 
            this.txtBJUnit.Size = new System.Drawing.Size(121, 21);
            // 
            // 
            // 
            this.txtBJUnit.Sufix = "g";
            this.txtBJUnit.TabIndex = 8;
            this.txtBJUnit.Tag = this.cell20;
            this.txtBJUnit.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtBJUnit.YssCaption = "报价单位";
            this.txtBJUnit.YssIsMust = true;
            this.txtBJUnit.YssLength = 15;
            this.txtBJUnit.YssNumeric = "7,8";
            this.txtBJUnit.YssReadOnly = false;
            // 
            // row7
            // 
            this.row7.BackColor = System.Drawing.Color.Empty;
            this.row7.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell21,
            this.cell22,
            this.cell28,
            this.cell29,
            this.cell30});
            this.row7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row7.FullRowSelected = false;
            this.row7.GroupLineLength = 100;
            this.row7.GroupPosition = 16;
            this.row7.Height = 23;
            this.row7.Key = null;
            this.row7.OwnTable = this.tbMain;
            this.row7.RowName = "row7";
            this.row7.ShowCheckBox = true;
            // 
            // cell21
            // 
            this.cell21.BackColor = System.Drawing.Color.Empty;
            this.cell21.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell21.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell21.Key = null;
            this.cell21.Text = "   交易单位：";
            this.cell21.ToolTip = null;
            // 
            // cell22
            // 
            this.cell22.BackColor = System.Drawing.Color.Empty;
            this.cell22.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell22.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell22.InnerControl = this.txtJYUnit;
            this.cell22.Key = null;
            this.cell22.ToolTip = null;
            // 
            // txtJYUnit
            // 
            this.txtJYUnit.BackColor = System.Drawing.Color.White;
            this.txtJYUnit.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.txtJYUnit.IsFillDecimal = true;
            this.txtJYUnit.Location = new System.Drawing.Point(110, 148);
            this.txtJYUnit.Name = "txtJYUnit";
            this.txtJYUnit.Padding = new System.Windows.Forms.Padding(1);
            this.txtJYUnit.PasswordChar = '\0';
            // 
            // 
            // 
            this.txtJYUnit.Size = new System.Drawing.Size(121, 21);
            // 
            // 
            // 
            this.txtJYUnit.Sufix = "g";
            this.txtJYUnit.TabIndex = 9;
            this.txtJYUnit.Tag = this.cell22;
            this.txtJYUnit.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtJYUnit.YssCaption = "交易单位";
            this.txtJYUnit.YssIsMust = true;
            this.txtJYUnit.YssLength = 15;
            this.txtJYUnit.YssNumeric = "7,8";
            this.txtJYUnit.YssReadOnly = false;
            // 
            // cell28
            // 
            this.cell28.BackColor = System.Drawing.Color.Empty;
            this.cell28.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell28.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell28.Key = null;
            this.cell28.ToolTip = null;
            // 
            // cell29
            // 
            this.cell29.BackColor = System.Drawing.Color.Empty;
            this.cell29.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell29.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell29.Key = null;
            this.cell29.Text = "保证金比例：";
            this.cell29.ToolTip = null;
            // 
            // cell30
            // 
            this.cell30.BackColor = System.Drawing.Color.Empty;
            this.cell30.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell30.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell30.InnerControl = this.txtBZJRatio;
            this.cell30.Key = null;
            this.cell30.ToolTip = null;
            // 
            // txtBZJRatio
            // 
            this.txtBZJRatio.BackColor = System.Drawing.Color.White;
            this.txtBZJRatio.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.txtBZJRatio.IsFillDecimal = true;
            this.txtBZJRatio.Location = new System.Drawing.Point(353, 148);
            this.txtBZJRatio.Name = "txtBZJRatio";
            this.txtBZJRatio.Padding = new System.Windows.Forms.Padding(1);
            this.txtBZJRatio.PasswordChar = '\0';
            // 
            // 
            // 
            this.txtBZJRatio.Size = new System.Drawing.Size(121, 21);
            // 
            // 
            // 
            this.txtBZJRatio.Sufix = "%";
            this.txtBZJRatio.TabIndex = 10;
            this.txtBZJRatio.Tag = this.cell30;
            this.txtBZJRatio.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtBZJRatio.YssCaption = "保证金比例";
            this.txtBZJRatio.YssIsMust = true;
            this.txtBZJRatio.YssLength = 15;
            this.txtBZJRatio.YssNumeric = "7,8";
            this.txtBZJRatio.YssReadOnly = false;
            // 
            // row8
            // 
            this.row8.BackColor = System.Drawing.Color.Empty;
            this.row8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row8.FullRowSelected = false;
            this.row8.GroupLineLength = 100;
            this.row8.GroupPosition = 16;
            this.row8.Height = 23;
            this.row8.IsGroup = true;
            this.row8.Key = null;
            this.row8.OwnTable = this.tbMain;
            this.row8.RowName = "row8";
            this.row8.ShowCheckBox = true;
            // 
            // row9
            // 
            this.row9.BackColor = System.Drawing.Color.Empty;
            this.row9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row9.FullRowSelected = false;
            this.row9.GroupLineLength = 100;
            this.row9.GroupPosition = 16;
            this.row9.Height = 23;
            this.row9.Key = null;
            this.row9.OwnTable = this.tbMain;
            this.row9.RowName = "row9";
            this.row9.ShowCheckBox = true;
            // 
            // row10
            // 
            this.row10.BackColor = System.Drawing.Color.Empty;
            this.row10.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell23,
            this.cell24,
            this.cell25,
            this.cell26,
            this.cell27});
            this.row10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row10.FullRowSelected = false;
            this.row10.GroupLineLength = 100;
            this.row10.GroupPosition = 16;
            this.row10.Height = 23;
            this.row10.Key = null;
            this.row10.OwnTable = this.tbMain;
            this.row10.RowName = "row10";
            this.row10.ShowCheckBox = true;
            // 
            // cell23
            // 
            this.cell23.BackColor = System.Drawing.Color.Empty;
            this.cell23.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell23.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell23.Key = null;
            this.cell23.Text = "   上市日期：";
            this.cell23.ToolTip = null;
            // 
            // cell24
            // 
            this.cell24.BackColor = System.Drawing.Color.Empty;
            this.cell24.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell24.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell24.InnerControl = this.dtpMarketDate;
            this.cell24.Key = null;
            this.cell24.ToolTip = null;
            // 
            // dtpMarketDate
            // 
            this.dtpMarketDate.BackColor = System.Drawing.Color.Transparent;
            this.dtpMarketDate.Location = new System.Drawing.Point(110, 217);
            this.dtpMarketDate.Margin = new System.Windows.Forms.Padding(0);
            this.dtpMarketDate.Name = "dtpMarketDate";
            this.dtpMarketDate.Size = new System.Drawing.Size(121, 21);
            this.dtpMarketDate.TabIndex = 11;
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
            this.cell25.BackColor = System.Drawing.Color.Empty;
            this.cell25.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell25.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell25.Key = null;
            this.cell25.ToolTip = null;
            // 
            // cell26
            // 
            this.cell26.BackColor = System.Drawing.Color.Empty;
            this.cell26.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell26.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell26.Key = null;
            this.cell26.Text = "退市日期：";
            this.cell26.ToolTip = null;
            // 
            // cell27
            // 
            this.cell27.BackColor = System.Drawing.Color.Empty;
            this.cell27.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell27.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell27.InnerControl = this.dtpDelistDate;
            this.cell27.Key = null;
            this.cell27.ToolTip = null;
            // 
            // dtpDelistDate
            // 
            this.dtpDelistDate.BackColor = System.Drawing.Color.Transparent;
            this.dtpDelistDate.Location = new System.Drawing.Point(353, 217);
            this.dtpDelistDate.Margin = new System.Windows.Forms.Padding(0);
            this.dtpDelistDate.Name = "dtpDelistDate";
            this.dtpDelistDate.Size = new System.Drawing.Size(121, 21);
            this.dtpDelistDate.TabIndex = 12;
            this.dtpDelistDate.Tag = this.cell27;
            this.dtpDelistDate.yssEnabled = true;
            this.dtpDelistDate.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpDelistDate.yssInterval = false;
            this.dtpDelistDate.yssLabelStr = "至";
            this.dtpDelistDate.yssShowCheckBox = false;
            this.dtpDelistDate.yssShowOperLable = false;
            this.dtpDelistDate.YssShowSecond = true;
            this.dtpDelistDate.yssTimeControl = false;
            // 
            // Frm_SEC_BASE_XH_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(503, 329);
            this.DoubleBuffered = true;
            this.Name = "Frm_SEC_BASE_XH_S";
            this.Text = "现货品种信息";
            this.YssSetStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.Load += new System.EventHandler(this.Frm_SEC_BASE_XH_S_Load);
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
        private Yss.KRichEx.YssTextBox txtSecCode;
        private Yss.KRichEx.YssTextBox txtIsinCode;
        private Yss.KRichEx.YssTextBox txtXHCode;
        private Yss.KRichEx.YssTextBox txtXHName;
        private FAST.Core.BaseControl.YssSelCombox cboXHType;
        private FAST.Core.BaseControl.YssSelCombox cboMrket;
        private FAST.Core.BaseControl.YssSelCombox cboCury;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpMarketDate;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpDelistDate;
        private Yss.KRichEx.ImprovedTextBox txtBZJRatio;
        private Yss.KRichEx.ImprovedTextBox txtBJUnit;
        private Yss.KRichEx.ImprovedTextBox txtJYUnit;
    }
}