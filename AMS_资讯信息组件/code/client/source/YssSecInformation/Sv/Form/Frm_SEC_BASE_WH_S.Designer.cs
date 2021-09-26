﻿using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;

using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;
using FAST.Core.Context;

namespace YssSecInformation.Sv.Form
{
    partial class Frm_SEC_BASE_WH_S
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
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo1 = new FAST.Core.BaseControl.ControlMethodInfo();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_SEC_BASE_WH_S));
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo3 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo2 = new FAST.Core.BaseControl.ControlMethodInfo();
            this.txtSecurityCode = new Yss.KRichEx.YssTextBox();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.column2 = new Yss.KTable.Models.Column();
            this.column1 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.row3 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.cboCuryPair = new FAST.Core.BaseControl.YssSelCombox();
            this.row4 = new Yss.KTable.Models.Row();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.cboMkt = new FAST.Core.BaseControl.YssSelCombox();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.cboQiXian = new FAST.Core.BaseControl.YssSelCombox();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.row6 = new Yss.KTable.Models.Row();
            this.cell13 = new Yss.KTable.Models.Cell();
            this.cell14 = new Yss.KTable.Models.Cell();
            this.txtSecCode = new Yss.KRichEx.YssTextBox();
            this.cell15 = new Yss.KTable.Models.Cell();
            this.cell16 = new Yss.KTable.Models.Cell();
            this.cell17 = new Yss.KTable.Models.Cell();
            this.txtSecName = new Yss.KRichEx.YssTextBox();
            this.tbMain.SuspendLayout();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // stBarPnl
            // 
            this.StatuType = "新增(&Add...)";
            // 
            // tbMain
            // 
            // 
            // 
            // 
            this.tbMain.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(111)))), ((int)(((byte)(157)))), ((int)(((byte)(217)))));
            this.tbMain.Border.BorderStyle = System.Drawing.Drawing2D.DashStyle.Solid;
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
            this.tbMain.Controls.Add(this.cboCuryPair);
            this.tbMain.Controls.Add(this.cboQiXian);
            this.tbMain.Controls.Add(this.cboMkt);
            this.tbMain.Controls.Add(this.txtSecName);
            this.tbMain.Controls.Add(this.txtSecCode);
            this.tbMain.Controls.Add(this.txtSecurityCode);
            // 
            // 
            // 
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3,
            this.row6,
            this.row4});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(493, 143);
            // 
            // stBarBottom
            // 
            this.stBarBottom.Location = new System.Drawing.Point(0, 173);
            // 
            // panelExLine
            // 
            // 
            // pnlMain
            // 
            this.pnlMain.Size = new System.Drawing.Size(493, 196);
            //this.pnlMain.Style.Alignment = System.Drawing.StringAlignment.Center;
            //this.pnlMain.Style.BackColor1.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.DockSiteBackColor;
            //this.pnlMain.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            //this.pnlMain.Style.BorderColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBorder;
            //this.pnlMain.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            //this.pnlMain.Style.GradientAngle = 90;
            // 
            // yssPanel1
            // 
            this.yssPanel1.Size = new System.Drawing.Size(493, 196);
            // 
            // hpAssist
            // 
            // 
            // txtSecurityCode
            // 
            this.txtSecurityCode.Border.Class = "TextBoxBorder";
            this.txtSecurityCode.Location = new System.Drawing.Point(110, 43);
            this.txtSecurityCode.Name = "txtSecurityCode";
            this.txtSecurityCode.Size = new System.Drawing.Size(120, 21);
            this.txtSecurityCode.TabIndex = 0;
            this.txtSecurityCode.Tag = this.cell2;
            this.txtSecurityCode.Value = "";
            this.txtSecurityCode.YssCaption = "证券内码";
            this.txtSecurityCode.YssIsMust = true;
            this.txtSecurityCode.YssLength = 50;
            this.txtSecurityCode.YssNumeric = "";
            // 
            // cell2
            // 
            this.cell2.BackColor = System.Drawing.Color.Empty;
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.txtSecurityCode;
            this.cell2.Key = null;
            this.cell2.ToolTip = null;
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
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row2.FullRowSelected = false;
            this.row2.GroupLineLength = 100;
            this.row2.GroupPosition = -16;
            this.row2.Height = 10;
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
            this.row3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row3.FullRowSelected = false;
            this.row3.GroupLineLength = 100;
            this.row3.GroupPosition = -16;
            this.row3.Height = 25;
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
            this.cell1.Text = "   证券内码：";
            this.cell1.ToolTip = null;
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
            this.cell4.Text = "货 币 对：";
            this.cell4.ToolTip = null;
            // 
            // cell5
            // 
            this.cell5.BackColor = System.Drawing.Color.Empty;
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.cboCuryPair;
            this.cell5.Key = null;
            this.cell5.ToolTip = null;
            // 
            // cboCuryPair
            // 
            this.cboCuryPair.AddedSelItemName = "";
            this.cboCuryPair.BackColor = System.Drawing.Color.LightYellow;
            this.cboCuryPair.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.cboCuryPair.ClassName = "";
            this.cboCuryPair.DisplayName = "C_CURY_PAIR_NAME";
            this.cboCuryPair.DisplayValue = "C_CURY_PAIR_CODE";
            this.cboCuryPair.DllName = "YssControls.dll";
            this.cboCuryPair.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDown;
            this.cboCuryPair.FilterCond = "";
            this.cboCuryPair.IsFillDecimal = true;
            this.cboCuryPair.IsFocused = false;
            this.cboCuryPair.KTableTree = false;
            this.cboCuryPair.Location = new System.Drawing.Point(353, 43);
            this.cboCuryPair.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataList";
            controlMethodInfo1.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo1.MethodParams")));
            controlMethodInfo1.MethodParamValues = null;
            controlMethodInfo1.Methods = null;
            this.cboCuryPair.MethodInfo = controlMethodInfo1;
            this.cboCuryPair.Name = "cboCuryPair";
            this.cboCuryPair.NodeID = "";
            this.cboCuryPair.Padding = new System.Windows.Forms.Padding(1, 3, 1, 3);
            this.cboCuryPair.Parameter = "C_CURY_PAIR_NAME";
            this.cboCuryPair.ParaNodeID = "";
            this.cboCuryPair.PasswordChar = '\0';
            this.cboCuryPair.PopupTitle = "数据筛选";
            // 
            // 
            // 
            this.cboCuryPair.QueryCond = "";
            this.cboCuryPair.QueryType = "";
            this.cboCuryPair.RequestEveryTime = false;
            this.cboCuryPair.ShowCheckBox = false;
            this.cboCuryPair.ShowColumnHeader = false;
            this.cboCuryPair.Size = new System.Drawing.Size(120, 21);
            // 
            // 
            // 
            this.cboCuryPair.TabIndex = 1;
            this.cboCuryPair.Tag = this.cell5;
            this.cboCuryPair.TextAlign = System.Windows.Forms.HorizontalAlignment.Left;
            this.cboCuryPair.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
            this.cboCuryPair.TipText = "";
            this.cboCuryPair.TriggerTextLength = 1;
            this.cboCuryPair.UseErrorTip = true;
            this.cboCuryPair.YssAssociaType = YssInformation.Support.Context.AssociaType.base_currencyPair;
            this.cboCuryPair.YssCaption = "货币对";
            this.cboCuryPair.YssIsMust = true;
            this.cboCuryPair.YssKiloDelimiter = true;
            this.cboCuryPair.YssLength = 20;
            this.cboCuryPair.YssNumeric = "";
            this.cboCuryPair.YssReadOnly = false;
            this.cboCuryPair.YssShowButton = true;
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
            this.row4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row4.FullRowSelected = false;
            this.row4.GroupLineLength = 100;
            this.row4.GroupPosition = -16;
            this.row4.Height = 25;
            this.row4.Key = null;
            this.row4.OwnTable = this.tbMain;
            this.row4.RowName = "row5";
            this.row4.ShowCheckBox = true;
            // 
            // cell6
            // 
            this.cell6.BackColor = System.Drawing.Color.Empty;
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.Key = null;
            this.cell6.Text = "   交易市场：";
            this.cell6.ToolTip = null;
            // 
            // cell7
            // 
            this.cell7.BackColor = System.Drawing.Color.Empty;
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.InnerControl = this.cboMkt;
            this.cell7.Key = null;
            this.cell7.ToolTip = null;
            // 
            // cboMkt
            // 
            this.cboMkt.AddedSelItemName = "";
            this.cboMkt.BackColor = System.Drawing.Color.LightYellow;
            this.cboMkt.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.cboMkt.ClassName = "";
            this.cboMkt.DisplayName = "C_MKT_NAME";
            this.cboMkt.DisplayValue = "C_MKT_NO";
            this.cboMkt.DllName = "YssControls.dll";
            this.cboMkt.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDown;
            this.cboMkt.FilterCond = "";
            this.cboMkt.IsFillDecimal = true;
            this.cboMkt.IsFocused = false;
            this.cboMkt.KTableTree = true;
            this.cboMkt.Location = new System.Drawing.Point(110, 91);
            this.cboMkt.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo3.MethodName = "getDataList";
            controlMethodInfo3.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo3.MethodParams")));
            controlMethodInfo3.MethodParamValues = null;
            controlMethodInfo3.Methods = null;
            this.cboMkt.MethodInfo = controlMethodInfo3;
            this.cboMkt.Name = "cboMkt";
            this.cboMkt.NodeID = "C_MKT_CODE";
            this.cboMkt.Padding = new System.Windows.Forms.Padding(1, 3, 1, 3);
            this.cboMkt.Parameter = "C_MKT_CODE~C_MKT_NAME";
            this.cboMkt.ParaNodeID = "C_PARAENT_CODE";
            this.cboMkt.PasswordChar = '\0';
            this.cboMkt.PopupTitle = "数据筛选";
            // 
            // 
            // 
            this.cboMkt.QueryCond = "";
            this.cboMkt.QueryType = "";
            this.cboMkt.RequestEveryTime = false;
            this.cboMkt.ShowCheckBox = false;
            this.cboMkt.ShowColumnHeader = false;
            this.cboMkt.Size = new System.Drawing.Size(120, 21);
            // 
            // 
            // 
            this.cboMkt.TabIndex = 4;
            this.cboMkt.Tag = this.cell7;
            this.cboMkt.TextAlign = System.Windows.Forms.HorizontalAlignment.Left;
            this.cboMkt.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Value_Name;
            this.cboMkt.TipText = "";
            this.cboMkt.TriggerTextLength = 1;
            this.cboMkt.UseErrorTip = true;
            this.cboMkt.YssAssociaType = YssInformation.Support.Context.AssociaType.base_exchange;
            this.cboMkt.YssCaption = "交易市场";
            this.cboMkt.YssIsMust = true;
            this.cboMkt.YssKiloDelimiter = true;
            this.cboMkt.YssLength = 20;
            this.cboMkt.YssNumeric = "";
            this.cboMkt.YssReadOnly = false;
            this.cboMkt.YssShowButton = true;
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
            this.cell9.Text = "期    限：";
            this.cell9.ToolTip = null;
            // 
            // cell10
            // 
            this.cell10.BackColor = System.Drawing.Color.Empty;
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.InnerControl = this.cboQiXian;
            this.cell10.Key = null;
            this.cell10.ToolTip = null;
            // 
            // cboQiXian
            // 
            this.cboQiXian.AddedSelItemName = "";
            this.cboQiXian.BackColor = System.Drawing.Color.LightYellow;
            this.cboQiXian.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.cboQiXian.ClassName = "";
            this.cboQiXian.DisplayName = "C_DV_NAME";
            this.cboQiXian.DisplayValue = "C_DV_CODE";
            this.cboQiXian.DllName = "YssControls.dll";
            this.cboQiXian.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDown;
            this.cboQiXian.FilterCond = "";
            this.cboQiXian.IsFillDecimal = true;
            this.cboQiXian.IsFocused = false;
            this.cboQiXian.KTableTree = false;
            this.cboQiXian.Location = new System.Drawing.Point(353, 91);
            this.cboQiXian.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo2.MethodName = "getDataListByTypes";
            controlMethodInfo2.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo2.MethodParams")));
            controlMethodInfo2.MethodParamValues = new string[] {
        "VAR_DUR,"};
            controlMethodInfo2.Methods = null;
            this.cboQiXian.MethodInfo = controlMethodInfo2;
            this.cboQiXian.Name = "cboQiXian";
            this.cboQiXian.NodeID = "";
            this.cboQiXian.Padding = new System.Windows.Forms.Padding(1, 3, 1, 3);
            this.cboQiXian.Parameter = "C_DV_NAME";
            this.cboQiXian.ParaNodeID = "";
            this.cboQiXian.PasswordChar = '\0';
            this.cboQiXian.PopupTitle = "数据筛选";
            // 
            // 
            // 
            this.cboQiXian.QueryCond = "VAR_DUR";
            this.cboQiXian.QueryType = "CacheType";
            this.cboQiXian.RequestEveryTime = false;
            this.cboQiXian.ShowCheckBox = false;
            this.cboQiXian.ShowColumnHeader = false;
            this.cboQiXian.Size = new System.Drawing.Size(120, 21);
            // 
            // 
            // 
            this.cboQiXian.TabIndex = 5;
            this.cboQiXian.Tag = this.cell10;
            this.cboQiXian.TextAlign = System.Windows.Forms.HorizontalAlignment.Left;
            this.cboQiXian.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
            this.cboQiXian.TipText = "";
            this.cboQiXian.TriggerTextLength = 1;
            this.cboQiXian.UseErrorTip = true;
            this.cboQiXian.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboQiXian.YssCaption = "期限";
            this.cboQiXian.YssIsMust = true;
            this.cboQiXian.YssKiloDelimiter = true;
            this.cboQiXian.YssLength = 20;
            this.cboQiXian.YssNumeric = "";
            this.cboQiXian.YssReadOnly = false;
            this.cboQiXian.YssShowButton = true;
            // 
            // cell11
            // 
            this.cell11.BackColor = System.Drawing.Color.Empty;
            this.cell11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell11.Key = null;
            this.cell11.Text = "  报价因子：";
            this.cell11.ToolTip = null;
            // 
            // cell12
            // 
            this.cell12.BackColor = System.Drawing.Color.Empty;
            this.cell12.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell12.Key = null;
            this.cell12.ToolTip = null;
            // 
            // row6
            // 
            this.row6.BackColor = System.Drawing.Color.Empty;
            this.row6.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell13,
            this.cell14,
            this.cell15,
            this.cell16,
            this.cell17});
            this.row6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row6.FullRowSelected = false;
            this.row6.GroupLineLength = 100;
            this.row6.GroupPosition = -16;
            this.row6.Height = 23;
            this.row6.Key = null;
            this.row6.OwnTable = this.tbMain;
            this.row6.RowName = "row4";
            this.row6.ShowCheckBox = true;
            // 
            // cell13
            // 
            this.cell13.BackColor = System.Drawing.Color.Empty;
            this.cell13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell13.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell13.Key = null;
            this.cell13.Text = "   上市代码：";
            this.cell13.ToolTip = null;
            // 
            // cell14
            // 
            this.cell14.BackColor = System.Drawing.Color.Empty;
            this.cell14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell14.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell14.InnerControl = this.txtSecCode;
            this.cell14.Key = null;
            this.cell14.ToolTip = null;
            // 
            // txtSecCode
            // 
            this.txtSecCode.Border.Class = "TextBoxBorder";
            this.txtSecCode.Location = new System.Drawing.Point(110, 68);
            this.txtSecCode.Name = "txtSecCode";
            this.txtSecCode.Size = new System.Drawing.Size(121, 21);
            this.txtSecCode.TabIndex = 2;
            this.txtSecCode.Tag = this.cell14;
            this.txtSecCode.Value = "";
            this.txtSecCode.YssCaption = "上市代码";
            this.txtSecCode.YssIsMust = true;
            this.txtSecCode.YssKiloDelimiter = true;
            this.txtSecCode.YssLength = 30;
            this.txtSecCode.YssNumeric = "";
            // 
            // cell15
            // 
            this.cell15.BackColor = System.Drawing.Color.Empty;
            this.cell15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell15.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell15.Key = null;
            this.cell15.ToolTip = null;
            // 
            // cell16
            // 
            this.cell16.BackColor = System.Drawing.Color.Empty;
            this.cell16.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell16.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell16.Key = null;
            this.cell16.Text = "上市名称：";
            this.cell16.ToolTip = null;
            // 
            // cell17
            // 
            this.cell17.BackColor = System.Drawing.Color.Empty;
            this.cell17.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell17.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell17.InnerControl = this.txtSecName;
            this.cell17.Key = null;
            this.cell17.ToolTip = null;
            // 
            // txtSecName
            // 
            this.txtSecName.Border.Class = "TextBoxBorder";
            this.txtSecName.Location = new System.Drawing.Point(353, 68);
            this.txtSecName.Name = "txtSecName";
            this.txtSecName.Size = new System.Drawing.Size(121, 21);
            this.txtSecName.TabIndex = 3;
            this.txtSecName.Tag = this.cell17;
            this.txtSecName.Value = "";
            this.txtSecName.YssCaption = "上市名称";
            this.txtSecName.YssIsMust = true;
            this.txtSecName.YssKiloDelimiter = true;
            this.txtSecName.YssLength = 50;
            this.txtSecName.YssNumeric = "";
            // 
            // Frm_SEC_BASE_WH_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(493, 196);
            this.DoubleBuffered = true;
            this.Name = "Frm_SEC_BASE_WH_S";
            this.ShowInTaskbar = false;
            this.Text = "外汇交易品种";
            this.YssSetStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssOnAfterNewClick += new FrmBaseSet.AfterNewClick(this.Frm_SEC_BASE_WH_S_YssOnAfterNewClick);
            this.Load += new System.EventHandler(this.Frm_SEC_BASE_WH_S_Load);
            this.YssOnBeforeSaveClick += new FrmBaseSet.BeforeSaveClick(this.Frm_SEC_BASE_WH_S_YssOnBeforeSaveClick);
            this.tbMain.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private Yss.KRichEx.YssTextBox txtSecurityCode;
        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
        private Yss.KTable.Models.Column column3;
        private Yss.KTable.Models.Column column4;
        private Yss.KTable.Models.Column column5;
        private Yss.KTable.Models.Row row1;
        private Yss.KTable.Models.Row row2;
        private Yss.KTable.Models.Row row3;
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Cell cell2;
        private Yss.KTable.Models.Cell cell3;
        private Yss.KTable.Models.Cell cell4;
        private Yss.KTable.Models.Cell cell5;
        private Yss.KTable.Models.Row row4;
        private Yss.KTable.Models.Cell cell6;
        private Yss.KTable.Models.Cell cell7;
        private Yss.KTable.Models.Cell cell8;
        private Yss.KTable.Models.Cell cell9;
        private Yss.KTable.Models.Cell cell10;
        private Yss.KTable.Models.Cell cell11;
        private Yss.KTable.Models.Cell cell12;
        private Yss.KTable.Models.Row row6;
        private Yss.KTable.Models.Cell cell13;
        private Yss.KTable.Models.Cell cell14;
        private Yss.KTable.Models.Cell cell15;
        private Yss.KTable.Models.Cell cell16;
        private Yss.KTable.Models.Cell cell17;
        private Yss.KRichEx.YssTextBox txtSecCode;
        private Yss.KRichEx.YssTextBox txtSecName;
        private FAST.Core.BaseControl.YssSelCombox cboMkt;
        private FAST.Core.BaseControl.YssSelCombox cboQiXian;
        private FAST.Core.BaseControl.YssSelCombox cboCuryPair;
    }
}