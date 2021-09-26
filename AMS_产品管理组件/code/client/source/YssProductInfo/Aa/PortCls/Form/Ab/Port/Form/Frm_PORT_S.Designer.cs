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

namespace YssProductInfo.Ab.Port.Form
{
    partial class Frm_PORT_S
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
            this.components = new System.ComponentModel.Container();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo5 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo4 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo2 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo3 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo6 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo1 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo9 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo7 = new FAST.Core.BaseControl.ControlMethodInfo();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_PORT_S));
            this.row1 = new Yss.KTable.Models.Row();
            this.row3 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.txtPortCode = new Yss.KRichEx.YssTextBox();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.txtPortShortName = new Yss.KRichEx.YssTextBox();
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.row4 = new Yss.KTable.Models.Row();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.txtPortCNName = new Yss.KRichEx.YssTextBox();
            this.row5 = new Yss.KTable.Models.Row();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.txtPortENName = new Yss.KRichEx.YssTextBox();
            this.row6 = new Yss.KTable.Models.Row();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.txtAssetCode = new Yss.KRichEx.YssTextBox();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.cell13 = new Yss.KTable.Models.Cell();
            this.cell14 = new Yss.KTable.Models.Cell();
            this.cboCurrency = new FAST.Core.BaseControl.YssSelCombox();
            this.row7 = new Yss.KTable.Models.Row();
            this.cell15 = new Yss.KTable.Models.Cell();
            this.cell16 = new Yss.KTable.Models.Cell();
            this.cboAssetType = new FAST.Core.BaseControl.YssSelCombox();
            this.cell17 = new Yss.KTable.Models.Cell();
            this.cell18 = new Yss.KTable.Models.Cell();
            this.cell19 = new Yss.KTable.Models.Cell();
            this.cboHolidays = new FAST.Core.BaseControl.YssSelCombox();
            this.row8 = new Yss.KTable.Models.Row();
            this.cell20 = new Yss.KTable.Models.Cell();
            this.cell21 = new Yss.KTable.Models.Cell();
            this.dtpInceptionDate = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell22 = new Yss.KTable.Models.Cell();
            this.cell23 = new Yss.KTable.Models.Cell();
            this.cell24 = new Yss.KTable.Models.Cell();
            this.dtpExpirationDate = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell25 = new Yss.KTable.Models.Cell();
            this.cell26 = new Yss.KTable.Models.Cell();
            this.cell37 = new Yss.KTable.Models.Cell();
            this.cell38 = new Yss.KTable.Models.Cell();
            this.cell39 = new Yss.KTable.Models.Cell();
            this.cell27 = new Yss.KTable.Models.Cell();
            this.cell28 = new Yss.KTable.Models.Cell();
            this.cell40 = new Yss.KTable.Models.Cell();
            this.cell41 = new Yss.KTable.Models.Cell();
            this.cell42 = new Yss.KTable.Models.Cell();
            this.cell29 = new Yss.KTable.Models.Cell();
            this.cell30 = new Yss.KTable.Models.Cell();
            this.cell43 = new Yss.KTable.Models.Cell();
            this.cell44 = new Yss.KTable.Models.Cell();
            this.cell45 = new Yss.KTable.Models.Cell();
            this.cell31 = new Yss.KTable.Models.Cell();
            this.cell32 = new Yss.KTable.Models.Cell();
            this.cell33 = new Yss.KTable.Models.Cell();
            this.cell34 = new Yss.KTable.Models.Cell();
            this.cell35 = new Yss.KTable.Models.Cell();
            this.cell36 = new Yss.KTable.Models.Cell();
            this.row2 = new Yss.KTable.Models.Row();
            this.row14 = new Yss.KTable.Models.Row();
            this.row15 = new Yss.KTable.Models.Row();
            this.row9 = new Yss.KTable.Models.Row();
            this.cell46 = new Yss.KTable.Models.Cell();
            this.cell47 = new Yss.KTable.Models.Cell();
            this.cboPortLever = new FAST.Core.BaseControl.YssSelCombox();
            this.cell48 = new Yss.KTable.Models.Cell();
            this.cell49 = new Yss.KTable.Models.Cell();
            this.cell50 = new Yss.KTable.Models.Cell();
            this.cboPort = new FAST.Core.BaseControl.YssSelCombox();
            this.row10 = new Yss.KTable.Models.Row();
            this.cell51 = new Yss.KTable.Models.Cell();
            this.cell52 = new Yss.KTable.Models.Cell();
            this.cboAssetSort = new FAST.Core.BaseControl.YssSelCombox();
            this.cell53 = new Yss.KTable.Models.Cell();
            this.cell54 = new Yss.KTable.Models.Cell();
            this.cell55 = new Yss.KTable.Models.Cell();
            this.cboProdState = new FAST.Core.BaseControl.YssSelCombox();
            this.row11 = new Yss.KTable.Models.Row();
            this.cell56 = new Yss.KTable.Models.Cell();
            this.cell57 = new Yss.KTable.Models.Cell();
            this.dtpClear = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell58 = new Yss.KTable.Models.Cell();
            this.cell59 = new Yss.KTable.Models.Cell();
            this.cell60 = new Yss.KTable.Models.Cell();
            this.tbRightFilter = new Yss.KTable.Models.Table(this.components);
            this.column6 = new Yss.KTable.Models.Column();
            this.column7 = new Yss.KTable.Models.Column();
            this.cboPortCode = new FAST.Core.BaseControl.YssSelCombox();
            this.cell62 = new Yss.KTable.Models.Cell();
            this.row12 = new Yss.KTable.Models.Row();
            this.row13 = new Yss.KTable.Models.Row();
            this.cell61 = new Yss.KTable.Models.Cell();
            this.column24 = new Yss.KTable.Models.Column();
            this.column25 = new Yss.KTable.Models.Column();
            this.column26 = new Yss.KTable.Models.Column();
            this.column27 = new Yss.KTable.Models.Column();
            this.column28 = new Yss.KTable.Models.Column();
            this.row23 = new Yss.KTable.Models.Row();
            this.column15 = new Yss.KTable.Models.Column();
            this.column8 = new Yss.KTable.Models.Column();
            this.column9 = new Yss.KTable.Models.Column();
            this.column10 = new Yss.KTable.Models.Column();
            this.column11 = new Yss.KTable.Models.Column();
            this.column12 = new Yss.KTable.Models.Column();
            this.column13 = new Yss.KTable.Models.Column();
            this.column14 = new Yss.KTable.Models.Column();
            this.row20 = new Yss.KTable.Models.Row();
            this.row18 = new Yss.KTable.Models.Row();
            this.row19 = new Yss.KTable.Models.Row();
            this.tabItemRight = new YssDevComponents.DotNetBar.TabItem(this.components);
            this.column16 = new Yss.KTable.Models.Column();
            this.column17 = new Yss.KTable.Models.Column();
            this.column18 = new Yss.KTable.Models.Column();
            this.column19 = new Yss.KTable.Models.Column();
            this.column20 = new Yss.KTable.Models.Column();
            this.column21 = new Yss.KTable.Models.Column();
            this.column22 = new Yss.KTable.Models.Column();
            this.column23 = new Yss.KTable.Models.Column();
            this.tabItemAccount = new YssDevComponents.DotNetBar.TabItem(this.components);
            this.tabItemClear = new YssDevComponents.DotNetBar.TabItem(this.components);
            this.cell68 = new Yss.KTable.Models.Cell();
            this.checkBoxCell8 = new Yss.KTable.Models.CheckBoxCell();
            this.cell67 = new Yss.KTable.Models.Cell();
            this.checkBoxCell7 = new Yss.KTable.Models.CheckBoxCell();
            this.cell66 = new Yss.KTable.Models.Cell();
            this.checkBoxCell6 = new Yss.KTable.Models.CheckBoxCell();
            this.cell71 = new Yss.KTable.Models.Cell();
            this.checkBoxCell4 = new Yss.KTable.Models.CheckBoxCell();
            this.cell65 = new Yss.KTable.Models.Cell();
            this.checkBoxCell3 = new Yss.KTable.Models.CheckBoxCell();
            this.cell64 = new Yss.KTable.Models.Cell();
            this.checkBoxCell2 = new Yss.KTable.Models.CheckBoxCell();
            this.cell63 = new Yss.KTable.Models.Cell();
            this.checkBoxCell1 = new Yss.KTable.Models.CheckBoxCell();
            this.cell70 = new Yss.KTable.Models.Cell();
            this.checkBoxCell5 = new Yss.KTable.Models.CheckBoxCell();
            this.cell69 = new Yss.KTable.Models.Cell();
            this.tabControl1 = new YssDevComponents.DotNetBar.TabControl();
            this.tabControlPanel1 = new YssDevComponents.DotNetBar.TabControlPanel();
            this.splitRight = new Yss.Controls.ExpandableSplitter();
            this.pnlRight = new Yss.Controls.PanelEx(this.components);
            this.navBarRight = new Yss.KNavigation.NavigateBar();
            this.navigateItemMain = new Yss.KNavigation.NavigateItem();
            this.tbRightMain = new Yss.KTable.Models.Table(this.components);
            this.pnlBarPort = new Yss.Controls.PanelEx(this.components);
            this.barPort = new YssDevComponents.DotNetBar.Bar();
            this.chkCheckAll = new YssDevComponents.DotNetBar.CheckBoxItem();
            this.chkBoxCheckedRowsCount = new YssDevComponents.DotNetBar.CheckBoxItem();
            this.panelEx1 = new Yss.Controls.PanelEx(this.components);
            this.labFA = new System.Windows.Forms.Label();
            this.cboFA = new FAST.Core.BaseControl.YssSelCombox();
            this.checkSX = new System.Windows.Forms.CheckBox();
            this.tbMain.SuspendLayout();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.tbRightFilter.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.tabControl1)).BeginInit();
            this.tabControl1.SuspendLayout();
            this.pnlRight.SuspendLayout();
            this.navBarRight.SuspendLayout();
            this.navigateItemMain.SuspendLayout();
            this.pnlBarPort.SuspendLayout();
            this.panelEx1.SuspendLayout();
            this.SuspendLayout();
            // 
            // tbMain
            // 
            // 
            // 
            // 
            this.tbMain.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(111)))), ((int)(((byte)(157)))), ((int)(((byte)(217)))));
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
            this.tbMain.Controls.Add(this.cboProdState);
            this.tbMain.Controls.Add(this.cboAssetSort);
            this.tbMain.Controls.Add(this.cboHolidays);
            this.tbMain.Controls.Add(this.cboPortLever);
            this.tbMain.Controls.Add(this.dtpExpirationDate);
            this.tbMain.Controls.Add(this.dtpInceptionDate);
            this.tbMain.Controls.Add(this.dtpClear);
            this.tbMain.Controls.Add(this.cboAssetType);
            this.tbMain.Controls.Add(this.cboCurrency);
            this.tbMain.Controls.Add(this.txtPortCode);
            this.tbMain.Controls.Add(this.cboPort);
            this.tbMain.Controls.Add(this.txtPortCNName);
            this.tbMain.Controls.Add(this.txtPortENName);
            this.tbMain.Controls.Add(this.txtPortShortName);
            this.tbMain.Controls.Add(this.txtAssetCode);
            this.tbMain.Controls.Add(this.stBarBottom);
            this.tbMain.GridLineColor = System.Drawing.Color.Transparent;
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3,
            this.row4,
            this.row5,
            this.row6,
            this.row9,
            this.row7,
            this.row10,
            this.row14,
            this.row15,
            this.row8,
            this.row11});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(493, 319);
            this.tbMain.Controls.SetChildIndex(this.stBarBottom, 0);
            this.tbMain.Controls.SetChildIndex(this.txtAssetCode, 0);
            this.tbMain.Controls.SetChildIndex(this.txtPortShortName, 0);
            this.tbMain.Controls.SetChildIndex(this.txtPortENName, 0);
            this.tbMain.Controls.SetChildIndex(this.txtPortCNName, 0);
            this.tbMain.Controls.SetChildIndex(this.cboPort, 0);
            this.tbMain.Controls.SetChildIndex(this.txtPortCode, 0);
            this.tbMain.Controls.SetChildIndex(this.cboCurrency, 0);
            this.tbMain.Controls.SetChildIndex(this.cboAssetType, 0);
            this.tbMain.Controls.SetChildIndex(this.dtpClear, 0);
            this.tbMain.Controls.SetChildIndex(this.dtpInceptionDate, 0);
            this.tbMain.Controls.SetChildIndex(this.dtpExpirationDate, 0);
            this.tbMain.Controls.SetChildIndex(this.cboPortLever, 0);
            this.tbMain.Controls.SetChildIndex(this.cboHolidays, 0);
            this.tbMain.Controls.SetChildIndex(this.cboAssetSort, 0);
            this.tbMain.Controls.SetChildIndex(this.cboProdState, 0);
            // 
            // btnBar
            // 
            this.btnBar.Size = new System.Drawing.Size(743, 30);
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
            this.stBarBottom.Location = new System.Drawing.Point(0, 294);
            this.stBarBottom.StatuType = "新增(&Add...)";
            // 
            // pnlMain
            // 
            this.pnlMain.Controls.Add(this.splitRight);
            this.pnlMain.Controls.Add(this.pnlRight);
            this.pnlMain.Size = new System.Drawing.Size(743, 349);
            //this.pnlMain.Style.Alignment = System.Drawing.StringAlignment.Center;
            //this.pnlMain.Style.BackColor1.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.DockSiteBackColor;
            //this.pnlMain.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            //this.pnlMain.Style.BorderColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBorder;
            //this.pnlMain.Style.BorderSide = YssDevComponents.DotNetBar.eBorderSide.Top;
            //this.pnlMain.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            //this.pnlMain.Style.GradientAngle = 90;
            this.pnlMain.Controls.SetChildIndex(this.btnBar, 0);
            this.pnlMain.Controls.SetChildIndex(this.pnlRight, 0);
            this.pnlMain.Controls.SetChildIndex(this.splitRight, 0);
            this.pnlMain.Controls.SetChildIndex(this.tbMain, 0);
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
            this.yssPanel1.Size = new System.Drawing.Size(743, 349);
            // 
            // hpAssist
            // 
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("新宋体", 10F, System.Drawing.FontStyle.Bold);
            this.row1.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row1.FullRowSelected = false;
            this.row1.GroupLineLength = 310;
            this.row1.GroupPosition = 13;
            this.row1.Height = 33;
            this.row1.IsGroup = true;
            this.row1.Text = "基本信息";
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
            this.row3.GroupLineLength = 310;
            this.row3.GroupPosition = 13;
            this.row3.Height = 25;
            this.row3.Text = null;
            // 
            // cell1
            // 
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.Text = "   组合代码：";
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.txtPortCode;
            // 
            // txtPortCode
            // 
            this.txtPortCode.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.txtPortCode.Border.Class = "TextBoxBorder";
            this.txtPortCode.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtPortCode.Location = new System.Drawing.Point(110, 43);
            this.txtPortCode.Name = "txtPortCode";
            this.txtPortCode.Size = new System.Drawing.Size(121, 21);
            this.txtPortCode.TabIndex = 0;
            this.txtPortCode.Tag = this.cell2;
            this.txtPortCode.YssCaption = "组合代码";
            this.txtPortCode.YssDataComlumn = "C_PORT_CODE";
            this.txtPortCode.YssIsMust = true;
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
            this.cell4.Text = "组合简称：";
            // 
            // cell5
            // 
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.txtPortShortName;
            // 
            // txtPortShortName
            // 
            this.txtPortShortName.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.txtPortShortName.Border.Class = "TextBoxBorder";
            this.txtPortShortName.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtPortShortName.Location = new System.Drawing.Point(353, 43);
            this.txtPortShortName.Name = "txtPortShortName";
            this.txtPortShortName.Size = new System.Drawing.Size(121, 21);
            this.txtPortShortName.TabIndex = 1;
            this.txtPortShortName.Tag = this.cell5;
            this.txtPortShortName.YssCaption = "组合简称";
            this.txtPortShortName.YssDataComlumn = "C_PORT_NAME_ST";
            this.txtPortShortName.YssIsMust = true;
            this.txtPortShortName.YssLength = 50;
            // 
            // column1
            // 
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
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
            // row4
            // 
            this.row4.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell6,
            this.cell7});
            this.row4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row4.ForeColor = System.Drawing.Color.Black;
            this.row4.FullRowSelected = false;
            this.row4.GroupLineLength = 310;
            this.row4.GroupPosition = 13;
            this.row4.Height = 25;
            this.row4.Text = null;
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.Text = "   组合名称：";
            // 
            // cell7
            // 
            this.cell7.ColSpan = 4;
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.InnerControl = this.txtPortCNName;
            // 
            // txtPortCNName
            // 
            this.txtPortCNName.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.txtPortCNName.Border.Class = "TextBoxBorder";
            this.txtPortCNName.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtPortCNName.Location = new System.Drawing.Point(110, 68);
            this.txtPortCNName.Name = "txtPortCNName";
            this.txtPortCNName.Size = new System.Drawing.Size(364, 21);
            this.txtPortCNName.TabIndex = 2;
            this.txtPortCNName.Tag = this.cell7;
            this.txtPortCNName.YssCaption = "组合名称";
            this.txtPortCNName.YssIsMust = true;
            this.txtPortCNName.YssLength = 200;
            // 
            // row5
            // 
            this.row5.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell8,
            this.cell9});
            this.row5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row5.ForeColor = System.Drawing.Color.Black;
            this.row5.FullRowSelected = false;
            this.row5.GroupLineLength = 310;
            this.row5.GroupPosition = 13;
            this.row5.Height = 25;
            this.row5.Text = null;
            // 
            // cell8
            // 
            this.cell8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell8.Text = "   英文名称：";
            // 
            // cell9
            // 
            this.cell9.ColSpan = 4;
            this.cell9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell9.InnerControl = this.txtPortENName;
            // 
            // txtPortENName
            // 
            this.txtPortENName.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.txtPortENName.Border.Class = "TextBoxBorder";
            this.txtPortENName.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtPortENName.Location = new System.Drawing.Point(110, 93);
            this.txtPortENName.Name = "txtPortENName";
            this.txtPortENName.Size = new System.Drawing.Size(364, 21);
            this.txtPortENName.TabIndex = 3;
            this.txtPortENName.Tag = this.cell9;
            this.txtPortENName.YssCaption = "英文名称";
            this.txtPortENName.YssLength = 50;
            // 
            // row6
            // 
            this.row6.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell10,
            this.cell11,
            this.cell12,
            this.cell13,
            this.cell14});
            this.row6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row6.ForeColor = System.Drawing.Color.Black;
            this.row6.FullRowSelected = false;
            this.row6.GroupLineLength = 310;
            this.row6.GroupPosition = 13;
            this.row6.Height = 25;
            this.row6.Text = null;
            // 
            // cell10
            // 
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.Text = "   资产代码：";
            // 
            // cell11
            // 
            this.cell11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell11.InnerControl = this.txtAssetCode;
            // 
            // txtAssetCode
            // 
            this.txtAssetCode.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.txtAssetCode.Border.Class = "TextBoxBorder";
            this.txtAssetCode.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtAssetCode.Location = new System.Drawing.Point(110, 118);
            this.txtAssetCode.Name = "txtAssetCode";
            this.txtAssetCode.Size = new System.Drawing.Size(121, 21);
            this.txtAssetCode.TabIndex = 4;
            this.txtAssetCode.Tag = this.cell11;
            this.txtAssetCode.YssCaption = "资产代码";
            this.txtAssetCode.YssIsMust = true;
            // 
            // cell12
            // 
            this.cell12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell12.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell13
            // 
            this.cell13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell13.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell13.Text = "组合币种：";
            // 
            // cell14
            // 
            this.cell14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell14.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell14.InnerControl = this.cboCurrency;
            // 
            // cboCurrency
            // 
            this.cboCurrency.AddedSelItemName = "";
            this.cboCurrency.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.cboCurrency.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboCurrency.Border.Bottom = true;
            this.cboCurrency.Border.Left = true;
            this.cboCurrency.Border.Right = true;
            this.cboCurrency.Border.Top = true;
            this.cboCurrency.ClassName = "";
            this.cboCurrency.DisplayName = "C_DC_NAME";
            this.cboCurrency.DisplayValue = "C_DC_CODE";
            this.cboCurrency.DllName = "YssControls.dll";
            this.cboCurrency.FilterCond = "";
            this.cboCurrency.IsFillDecimal = false;
            this.cboCurrency.Location = new System.Drawing.Point(353, 118);
            this.cboCurrency.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo5.MethodName = "getDataList";
            controlMethodInfo5.MethodParams = null;
            controlMethodInfo5.MethodParamValues = null;
            controlMethodInfo5.Methods = null;
            this.cboCurrency.MethodInfo = controlMethodInfo5;
            this.cboCurrency.Name = "cboCurrency";
            this.cboCurrency.Parameter = "C_DC_NAME";
            this.cboCurrency.QueryCond = "";
            this.cboCurrency.QueryType = "";
            this.cboCurrency.Size = new System.Drawing.Size(121, 21);
            this.cboCurrency.TabIndex = 5;
            this.cboCurrency.Tag = this.cell14;
            this.cboCurrency.UseCustomerParameter = false;
            this.cboCurrency.YssAssociaType = YssInformation.Support.Context.AssociaType.base_currency;
            this.cboCurrency.YssCaption = "组合币种";
            this.cboCurrency.YssIsMust = true;
            this.cboCurrency.YssKiloDelimiter = true;
            // 
            // row7
            // 
            this.row7.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell15,
            this.cell16,
            this.cell17,
            this.cell18,
            this.cell19});
            this.row7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row7.ForeColor = System.Drawing.Color.Black;
            this.row7.FullRowSelected = false;
            this.row7.GroupLineLength = 310;
            this.row7.GroupPosition = 13;
            this.row7.Height = 25;
            this.row7.Text = null;
            // 
            // cell15
            // 
            this.cell15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell15.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell15.Text = "   资产类型：";
            // 
            // cell16
            // 
            this.cell16.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell16.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell16.InnerControl = this.cboAssetType;
            // 
            // cboAssetType
            // 
            this.cboAssetType.AddedSelItemName = "";
            this.cboAssetType.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.cboAssetType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboAssetType.Border.Bottom = true;
            this.cboAssetType.Border.Left = true;
            this.cboAssetType.Border.Right = true;
            this.cboAssetType.Border.Top = true;
            this.cboAssetType.ClassName = "";
            this.cboAssetType.DisplayName = "C_DAT_NAME";
            this.cboAssetType.DisplayValue = "C_DAT_CODE";
            this.cboAssetType.DllName = "YssControls.dll";
            this.cboAssetType.FilterCond = "";
            this.cboAssetType.IsFillDecimal = false;
            this.cboAssetType.Location = new System.Drawing.Point(110, 168);
            this.cboAssetType.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo4.MethodName = "getDataListByTypes";
            controlMethodInfo4.MethodParams = null;
            controlMethodInfo4.MethodParamValues = new string[] {
        "ASS,"};
            controlMethodInfo4.Methods = null;
            this.cboAssetType.MethodInfo = controlMethodInfo4;
            this.cboAssetType.Name = "cboAssetType";
            this.cboAssetType.NodeID = "C_DAT_CODE";
            this.cboAssetType.Parameter = "C_DAT_NAME";
            this.cboAssetType.ParaNodeID = "C_DAT_CODE_P";
            this.cboAssetType.QueryCond = "";
            this.cboAssetType.QueryType = "";
            this.cboAssetType.Size = new System.Drawing.Size(121, 21);
            this.cboAssetType.TabIndex = 8;
            this.cboAssetType.Tag = this.cell16;
            this.cboAssetType.UseCustomerParameter = false;
            this.cboAssetType.YssAssociaType = YssInformation.Support.Context.AssociaType.base_accType;
            this.cboAssetType.YssCaption = "资产类型";
            this.cboAssetType.YssIsMust = true;
            this.cboAssetType.YssKiloDelimiter = true;
            // 
            // cell17
            // 
            this.cell17.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell17.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell18
            // 
            this.cell18.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell18.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell18.Text = "节假日群：";
            // 
            // cell19
            // 
            this.cell19.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell19.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell19.InnerControl = this.cboHolidays;
            // 
            // cboHolidays
            // 
            this.cboHolidays.AddedSelItemName = "";
            this.cboHolidays.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboHolidays.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboHolidays.Border.Bottom = true;
            this.cboHolidays.Border.Left = true;
            this.cboHolidays.Border.Right = true;
            this.cboHolidays.Border.Top = true;
            this.cboHolidays.ClassName = "";
            this.cboHolidays.DisplayName = "C_HDAY_NAME";
            this.cboHolidays.DisplayValue = "C_HDAY_CODE";
            this.cboHolidays.DllName = "YssControls.dll";
            this.cboHolidays.FilterCond = "";
            this.cboHolidays.IsFillDecimal = false;
            this.cboHolidays.Location = new System.Drawing.Point(353, 168);
            this.cboHolidays.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo2.MethodName = "getDataList";
            controlMethodInfo2.MethodParams = null;
            controlMethodInfo2.MethodParamValues = null;
            controlMethodInfo2.Methods = null;
            this.cboHolidays.MethodInfo = controlMethodInfo2;
            this.cboHolidays.Name = "cboHolidays";
            this.cboHolidays.Parameter = "C_HDAY_CODE~C_HDAY_NAME";
            this.cboHolidays.QueryCond = "";
            this.cboHolidays.QueryType = "";
            this.cboHolidays.RequestEveryTime = true;
            this.cboHolidays.Size = new System.Drawing.Size(121, 21);
            this.cboHolidays.TabIndex = 9;
            this.cboHolidays.Tag = this.cell19;
            this.cboHolidays.UseCustomerParameter = false;
            this.cboHolidays.YssAssociaType = YssInformation.Support.Context.AssociaType.base_holidays_A;
            this.cboHolidays.YssCaption = "节假日群";
            this.cboHolidays.YssDataSrc = FAST.Core.Context.ClsEnums.DataSrc.SrcDB;
            this.cboHolidays.YssIsMust = true;
            this.cboHolidays.YssKiloDelimiter = true;
            // 
            // row8
            // 
            this.row8.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell20,
            this.cell21,
            this.cell22,
            this.cell23,
            this.cell24});
            this.row8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row8.ForeColor = System.Drawing.Color.Black;
            this.row8.FullRowSelected = false;
            this.row8.GroupLineLength = 310;
            this.row8.GroupPosition = 13;
            this.row8.Height = 25;
            this.row8.Text = null;
            // 
            // cell20
            // 
            this.cell20.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell20.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell20.Text = "   成立日期：";
            // 
            // cell21
            // 
            this.cell21.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell21.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell21.InnerControl = this.dtpInceptionDate;
            // 
            // dtpInceptionDate
            // 
            this.dtpInceptionDate.BackColor = System.Drawing.Color.Transparent;
            this.dtpInceptionDate.BusinessDate = true;
            this.dtpInceptionDate.DateBeginChecked = true;
            this.dtpInceptionDate.DateEndChecked = true;
            this.dtpInceptionDate.Location = new System.Drawing.Point(110, 236);
            this.dtpInceptionDate.Margin = new System.Windows.Forms.Padding(0);
            this.dtpInceptionDate.Name = "dtpInceptionDate";
            this.dtpInceptionDate.Size = new System.Drawing.Size(121, 21);
            this.dtpInceptionDate.TabIndex = 12;
            this.dtpInceptionDate.Tag = this.cell21;
            this.dtpInceptionDate.yssEnabled = true;
            this.dtpInceptionDate.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpInceptionDate.yssInterval = false;
            this.dtpInceptionDate.yssLabelStr = "至";
            this.dtpInceptionDate.yssShowCheckBox = false;
            this.dtpInceptionDate.yssShowOperLable = false;
            this.dtpInceptionDate.YssShowSecond = true;
            this.dtpInceptionDate.yssTimeControl = false;
            // 
            // cell22
            // 
            this.cell22.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell22.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell23
            // 
            this.cell23.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell23.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell23.Text = "到期日期：";
            // 
            // cell24
            // 
            this.cell24.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell24.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell24.InnerControl = this.dtpExpirationDate;
            // 
            // dtpExpirationDate
            // 
            this.dtpExpirationDate.BackColor = System.Drawing.Color.Transparent;
            this.dtpExpirationDate.DateBeginChecked = true;
            this.dtpExpirationDate.DateEndChecked = true;
            this.dtpExpirationDate.Location = new System.Drawing.Point(353, 236);
            this.dtpExpirationDate.Margin = new System.Windows.Forms.Padding(0);
            this.dtpExpirationDate.Name = "dtpExpirationDate";
            this.dtpExpirationDate.Size = new System.Drawing.Size(121, 21);
            this.dtpExpirationDate.TabIndex = 13;
            this.dtpExpirationDate.Tag = this.cell24;
            this.dtpExpirationDate.yssEnabled = true;
            this.dtpExpirationDate.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpExpirationDate.yssInterval = false;
            this.dtpExpirationDate.yssLabelStr = "至";
            this.dtpExpirationDate.yssShowCheckBox = false;
            this.dtpExpirationDate.yssShowOperLable = false;
            this.dtpExpirationDate.YssShowSecond = true;
            this.dtpExpirationDate.yssTimeControl = false;
            // 
            // cell25
            // 
            this.cell25.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell25.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell25.Text = "  管理人：";
            // 
            // cell26
            // 
            this.cell26.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell37
            // 
            this.cell37.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell38
            // 
            this.cell38.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell38.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell38.Text = "  担保人：";
            // 
            // cell39
            // 
            this.cell39.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell27
            // 
            this.cell27.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell27.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell27.Text = "  主托管人：";
            // 
            // cell28
            // 
            this.cell28.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell40
            // 
            this.cell40.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell41
            // 
            this.cell41.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell41.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell41.Text = "  受托人：";
            // 
            // cell42
            // 
            this.cell42.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell29
            // 
            this.cell29.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell29.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell29.Text = "  次托管人：";
            // 
            // cell30
            // 
            this.cell30.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell43
            // 
            this.cell43.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell44
            // 
            this.cell44.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell44.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell44.Text = "  投资顾问：";
            // 
            // cell45
            // 
            this.cell45.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell31
            // 
            this.cell31.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell31.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell31.Text = "  担保人：";
            // 
            // cell32
            // 
            this.cell32.ColSpan = 4;
            this.cell32.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell33
            // 
            this.cell33.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell33.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell33.Text = "  受托人：";
            // 
            // cell34
            // 
            this.cell34.ColSpan = 4;
            this.cell34.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell35
            // 
            this.cell35.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell35.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell35.Text = "  投资顾问：";
            // 
            // cell36
            // 
            this.cell36.ColSpan = 4;
            this.cell36.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // row2
            // 
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row2.ForeColor = System.Drawing.Color.Black;
            this.row2.FullRowSelected = false;
            this.row2.GroupLineLength = 310;
            this.row2.GroupPosition = 13;
            this.row2.Height = 10;
            this.row2.Text = null;
            // 
            // row14
            // 
            this.row14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row14.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row14.FullRowSelected = false;
            this.row14.GroupLineLength = 310;
            this.row14.GroupPosition = 13;
            this.row14.Height = 10;
            this.row14.IsGroup = true;
            this.row14.Text = null;
            // 
            // row15
            // 
            this.row15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row15.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row15.FullRowSelected = false;
            this.row15.GroupLineLength = 310;
            this.row15.GroupPosition = 13;
            this.row15.Height = 10;
            this.row15.Text = null;
            // 
            // row9
            // 
            this.row9.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell46,
            this.cell47,
            this.cell48,
            this.cell49,
            this.cell50});
            this.row9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row9.FullRowSelected = false;
            this.row9.Height = 25;
            this.row9.Text = null;
            // 
            // cell46
            // 
            this.cell46.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell46.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell46.Text = "   组合级别：";
            // 
            // cell47
            // 
            this.cell47.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell47.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell47.InnerControl = this.cboPortLever;
            // 
            // cboPortLever
            // 
            this.cboPortLever.AddedSelItemName = "";
            this.cboPortLever.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboPortLever.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboPortLever.Border.Bottom = true;
            this.cboPortLever.Border.Left = true;
            this.cboPortLever.Border.Right = true;
            this.cboPortLever.Border.Top = true;
            this.cboPortLever.ClassName = "";
            this.cboPortLever.DllName = "YssControls.dll";
            this.cboPortLever.FilterCond = "";
            this.cboPortLever.IsFillDecimal = false;
            this.cboPortLever.Location = new System.Drawing.Point(110, 143);
            this.cboPortLever.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo3.MethodName = "getDataListByTypes";
            controlMethodInfo3.MethodParams = null;
            controlMethodInfo3.MethodParamValues = new string[] {
        "PORT_LEVEL,"};
            controlMethodInfo3.Methods = null;
            this.cboPortLever.MethodInfo = controlMethodInfo3;
            this.cboPortLever.Name = "cboPortLever";
            this.cboPortLever.QueryCond = "PORT_LEVEL";
            this.cboPortLever.QueryType = "CacheType";
            this.cboPortLever.Size = new System.Drawing.Size(121, 21);
            this.cboPortLever.TabIndex = 6;
            this.cboPortLever.Tag = this.cell47;
            this.cboPortLever.UseCustomerParameter = false;
            this.cboPortLever.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboPortLever.YssCaption = "组合级别";
            this.cboPortLever.YssIsMust = true;
            this.cboPortLever.SelectedValueChanged += new System.EventHandler(this.cboPortLever_SelectedValueChanged);
            // 
            // cell48
            // 
            this.cell48.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell48.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell49
            // 
            this.cell49.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell49.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell49.Text = "上级组合：";
            // 
            // cell50
            // 
            this.cell50.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell50.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell50.InnerControl = this.cboPort;
            // 
            // cboPort
            // 
            this.cboPort.AddedSelItemName = "";
            this.cboPort.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboPort.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboPort.Border.Bottom = true;
            this.cboPort.Border.Left = true;
            this.cboPort.Border.Right = true;
            this.cboPort.Border.Top = true;
            this.cboPort.ClassName = "";
            this.cboPort.DisplayName = "C_PORT_NAME_ST";
            this.cboPort.DisplayValue = "C_PORT_CODE";
            this.cboPort.DllName = "YssControls.dll";
            this.cboPort.FilterCond = "";
            this.cboPort.IsFillDecimal = false;
            this.cboPort.KTableTree = true;
            this.cboPort.Location = new System.Drawing.Point(353, 143);
            this.cboPort.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo6.MethodName = "getFilterPortDataForOperRight";
            controlMethodInfo6.MethodParams = null;
            controlMethodInfo6.MethodParamValues = null;
            controlMethodInfo6.Methods = null;
            this.cboPort.MethodInfo = controlMethodInfo6;
            this.cboPort.Name = "cboPort";
            this.cboPort.NodeID = "C_PORT_CODE";
            this.cboPort.Parameter = "C_PORT_CODE~C_PORT_NAME_ST";
            this.cboPort.ParaNodeID = "C_PORT_CODE_P";
            this.cboPort.QueryCond = "";
            this.cboPort.QueryType = "";
            this.cboPort.ShowColumnHeader = true;
            this.cboPort.Size = new System.Drawing.Size(121, 21);
            this.cboPort.SortColumn = "C_PORT_NAME_ST";
            this.cboPort.TabIndex = 7;
            this.cboPort.Tag = this.cell50;
            this.cboPort.UseCustomerParameter = false;
            this.cboPort.YssAssociaType = YssProductInfo.Support.Context.AssociaType.pd_portfolio;
            this.cboPort.YssCaption = "上级组合";
            this.cboPort.YssDataComlumn = "C_PORT_CODE_P";
            this.cboPort.YssOnBeforeLoadData += new FAST.Core.BaseControl.YssSelCombox.yssBeforeLoadData(this.cboPort_YssOnBeforeLoadData);
            // 
            // row10
            // 
            this.row10.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell51,
            this.cell52,
            this.cell53,
            this.cell54,
            this.cell55});
            this.row10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row10.FullRowSelected = false;
            this.row10.Text = null;
            // 
            // cell51
            // 
            this.cell51.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell51.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell51.Text = "   资产类别：";
            // 
            // cell52
            // 
            this.cell52.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell52.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell52.InnerControl = this.cboAssetSort;
            // 
            // cboAssetSort
            // 
            this.cboAssetSort.AddedSelItemName = "";
            this.cboAssetSort.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboAssetSort.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboAssetSort.Border.Bottom = true;
            this.cboAssetSort.Border.Left = true;
            this.cboAssetSort.Border.Right = true;
            this.cboAssetSort.Border.Top = true;
            this.cboAssetSort.ClassName = "";
            this.cboAssetSort.DisplayName = "C_DAT_NAME";
            this.cboAssetSort.DisplayValue = "C_DAT_CODE";
            this.cboAssetSort.DllName = "YssControls.dll";
            this.cboAssetSort.FilterCond = "";
            this.cboAssetSort.Location = new System.Drawing.Point(110, 193);
            this.cboAssetSort.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataListByTypes";
            controlMethodInfo1.MethodParams = null;
            controlMethodInfo1.MethodParamValues = new string[] {
        "CLS,"};
            controlMethodInfo1.Methods = null;
            this.cboAssetSort.MethodInfo = controlMethodInfo1;
            this.cboAssetSort.Name = "cboAssetSort";
            this.cboAssetSort.QueryCond = "CLS_PT,CLS_HB,CLS_ETF,CLS_LJ";
            this.cboAssetSort.QueryType = "";
            this.cboAssetSort.Size = new System.Drawing.Size(121, 21);
            this.cboAssetSort.TabIndex = 10;
            this.cboAssetSort.Tag = this.cell52;
            this.cboAssetSort.UseCustomerParameter = false;
            this.cboAssetSort.YssAssociaType = YssInformation.Support.Context.AssociaType.base_accType;
            this.cboAssetSort.YssCaption = "资产类别";
            this.cboAssetSort.YssIsMust = true;
            // 
            // cell53
            // 
            this.cell53.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell53.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell54
            // 
            this.cell54.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell54.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell54.Text = "产品状态：";
            // 
            // cell55
            // 
            this.cell55.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell55.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell55.InnerControl = this.cboProdState;
            // 
            // cboProdState
            // 
            this.cboProdState.AddedSelItemName = "";
            this.cboProdState.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboProdState.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboProdState.Border.Bottom = true;
            this.cboProdState.Border.Left = true;
            this.cboProdState.Border.Right = true;
            this.cboProdState.Border.Top = true;
            this.cboProdState.ClassName = "";
            this.cboProdState.DisplayName = "C_DV_NAME";
            this.cboProdState.DisplayValue = "C_DV_CODE";
            this.cboProdState.DllName = "YssControls.dll";
            this.cboProdState.FilterCond = "";
            this.cboProdState.Location = new System.Drawing.Point(353, 193);
            this.cboProdState.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo9.MethodName = "getDataListByTypes";
            controlMethodInfo9.MethodParams = null;
            controlMethodInfo9.MethodParamValues = new string[] {
        "PD_STATUS,"};
            controlMethodInfo9.Methods = null;
            this.cboProdState.MethodInfo = controlMethodInfo9;
            this.cboProdState.Name = "cboProdState";
            this.cboProdState.QueryCond = "";
            this.cboProdState.QueryType = "";
            this.cboProdState.RequestEveryTime = true;
            this.cboProdState.Size = new System.Drawing.Size(121, 21);
            this.cboProdState.TabIndex = 11;
            this.cboProdState.Tag = this.cell55;
            this.cboProdState.UseCustomerParameter = false;
            this.cboProdState.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboProdState.YssCaption = "产品状态";
            this.cboProdState.YssIsMust = true;
            this.cboProdState.YssOnBeforeLoadData += new FAST.Core.BaseControl.YssSelCombox.yssBeforeLoadData(this.cboProdState_YssOnBeforeLoadData);
            this.cboProdState.SelectedValueChanged += new System.EventHandler(this.cboProdState_SelectedValueChanged);
            // 
            // row11
            // 
            this.row11.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell56,
            this.cell57,
            this.cell58,
            this.cell59,
            this.cell60});
            this.row11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row11.FullRowSelected = false;
            this.row11.Text = null;
            // 
            // cell56
            // 
            this.cell56.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell56.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell56.Text = "   清算日期：";
            // 
            // cell57
            // 
            this.cell57.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell57.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell57.InnerControl = this.dtpClear;
            // 
            // dtpClear
            // 
            this.dtpClear.BackColor = System.Drawing.Color.Transparent;
            this.dtpClear.DateBeginChecked = true;
            this.dtpClear.DateEndChecked = true;
            this.dtpClear.Location = new System.Drawing.Point(110, 261);
            this.dtpClear.Margin = new System.Windows.Forms.Padding(0);
            this.dtpClear.Name = "dtpClear";
            this.dtpClear.Size = new System.Drawing.Size(121, 21);
            this.dtpClear.TabIndex = 14;
            this.dtpClear.Tag = this.cell57;
            this.dtpClear.yssEnabled = true;
            this.dtpClear.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpClear.yssInterval = false;
            this.dtpClear.yssLabelStr = "至";
            this.dtpClear.yssShowCheckBox = false;
            this.dtpClear.yssShowOperLable = false;
            this.dtpClear.YssShowSecond = true;
            this.dtpClear.yssTimeControl = false;
            // 
            // cell58
            // 
            this.cell58.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell58.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell59
            // 
            this.cell59.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell59.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell60
            // 
            this.cell60.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell60.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // tbRightFilter
            // 
            this.tbRightFilter.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(243)))), ((int)(((byte)(245)))), ((int)(((byte)(249)))));
            // 
            // 
            // 
            this.tbRightFilter.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(111)))), ((int)(((byte)(157)))), ((int)(((byte)(217)))));
            this.tbRightFilter.Border.Bottom = false;
            this.tbRightFilter.Border.Left = false;
            this.tbRightFilter.Border.Right = false;
            this.tbRightFilter.Border.Top = false;
            this.tbRightFilter.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.tbRightFilter.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.column6,
            this.column7});
            this.tbRightFilter.Controls.Add(this.cboPortCode);
            this.tbRightFilter.DefaultToolStripItems = ((Yss.KTable.Enums.SysToolStripItems)((((((((((((Yss.KTable.Enums.SysToolStripItems.ShowHideColumn | Yss.KTable.Enums.SysToolStripItems.GroupByColumn)
                        | Yss.KTable.Enums.SysToolStripItems.PrintSetup)
                        | Yss.KTable.Enums.SysToolStripItems.PrintPreview)
                        | Yss.KTable.Enums.SysToolStripItems.GardingMenu)
                        | Yss.KTable.Enums.SysToolStripItems.ShowStyleSetting)
                        | Yss.KTable.Enums.SysToolStripItems.ShowCopyTool)
                        | Yss.KTable.Enums.SysToolStripItems.ShowColumnsEdit)
                        | Yss.KTable.Enums.SysToolStripItems.ShowRowsEdit)
                        | Yss.KTable.Enums.SysToolStripItems.ShowCreateTable)
                        | Yss.KTable.Enums.SysToolStripItems.ShowSendFax)
                        | Yss.KTable.Enums.SysToolStripItems.FaxSetup)));
            this.tbRightFilter.Dock = System.Windows.Forms.DockStyle.Top;
            this.tbRightFilter.IsShowScrollbar = false;
            this.tbRightFilter.Location = new System.Drawing.Point(0, 0);
            this.tbRightFilter.Name = "tbRightFilter";
            this.tbRightFilter.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row12,
            this.row13});
            this.tbRightFilter.ShowColumnHeader = false;
            this.tbRightFilter.Size = new System.Drawing.Size(244, 42);
            this.tbRightFilter.TabIndex = 0;
            this.tbRightFilter.Text = "table1";
            // 
            // column6
            // 
            this.column6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column6.Width = 110;
            // 
            // column7
            // 
            this.column7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column7.Width = 122;
            // 
            // cboPortCode
            // 
            this.cboPortCode.AddedSelItemName = "";
            this.cboPortCode.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboPortCode.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboPortCode.Border.Bottom = true;
            this.cboPortCode.Border.Left = true;
            this.cboPortCode.Border.Right = true;
            this.cboPortCode.Border.Top = true;
            this.cboPortCode.ClassName = "";
            this.cboPortCode.DetailDataProperty = "DATA_TYPE";
            this.cboPortCode.DetailMark = "PORT_TYPE";
            this.cboPortCode.DisplayName = "C_PORT_NAME_ST";
            this.cboPortCode.DisplayStyle = Yss.KRichEx.AutoFilter.DisplayStyle.Modal;
            this.cboPortCode.DisplayValue = "C_PORT_CODE";
            this.cboPortCode.DllName = "YssControls.dll";
            this.cboPortCode.FilterCond = "";
            this.cboPortCode.IsFillDecimal = false;
            this.cboPortCode.KTableTree = true;
            this.cboPortCode.Location = new System.Drawing.Point(110, 10);
            this.cboPortCode.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo7.MethodName = "getDataList";
            controlMethodInfo7.MethodParams = null;
            controlMethodInfo7.MethodParamValues = null;
            controlMethodInfo7.Methods = null;
            this.cboPortCode.MethodInfo = controlMethodInfo7;
            this.cboPortCode.Name = "cboPortCode";
            this.cboPortCode.NodeID = "C_PORT_CODE";
            this.cboPortCode.Parameter = "C_PORT_CODE~C_PORT_NAME_ST";
            this.cboPortCode.ParaNodeID = "C_PORT_CODE_P";
            this.cboPortCode.QueryCond = "";
            this.cboPortCode.QueryType = "";
            this.cboPortCode.ShowColumnHeader = true;
            this.cboPortCode.Size = new System.Drawing.Size(121, 21);
            this.cboPortCode.SortColumn = "C_PORT_NAME_ST";
            this.cboPortCode.TabIndex = 2;
            this.cboPortCode.Tag = this.cell62;
            this.cboPortCode.UseCustomerParameter = false;
            this.cboPortCode.YssAssociaType = YssProductInfo.Support.Context.AssociaType.pd_portfolio;
            this.cboPortCode.YssCaption = "参照组合";
            this.cboPortCode.YssIsMust = true;
            this.cboPortCode.SelectedValueChanged += new System.EventHandler(this.cboPortCode_SelectedValueChanged);
            // 
            // cell62
            // 
            this.cell62.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell62.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell62.InnerControl = this.cboPortCode;
            // 
            // row12
            // 
            this.row12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row12.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row12.Height = 10;
            this.row12.Text = null;
            // 
            // row13
            // 
            this.row13.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell61,
            this.cell62});
            this.row13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row13.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row13.Text = null;
            // 
            // cell61
            // 
            this.cell61.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell61.ForeColor = System.Drawing.Color.CornflowerBlue;
            this.cell61.Text = "参照组合：";
            // 
            // column24
            // 
            this.column24.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column24.ForeColor = System.Drawing.Color.Empty;
            this.column24.Width = 122;
            // 
            // column25
            // 
            this.column25.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column25.ForeColor = System.Drawing.Color.Empty;
            this.column25.Width = 122;
            // 
            // column26
            // 
            this.column26.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column26.ForeColor = System.Drawing.Color.Empty;
            this.column26.Width = 122;
            // 
            // column27
            // 
            this.column27.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column27.ForeColor = System.Drawing.Color.Empty;
            this.column27.Width = 122;
            // 
            // column28
            // 
            this.column28.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column28.ForeColor = System.Drawing.Color.Empty;
            this.column28.Width = 1;
            // 
            // row23
            // 
            this.row23.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row23.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row23.Text = null;
            // 
            // column15
            // 
            this.column15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column15.ForeColor = System.Drawing.Color.Empty;
            this.column15.Width = 10;
            // 
            // column8
            // 
            this.column8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column8.ForeColor = System.Drawing.Color.Empty;
            this.column8.Width = 100;
            // 
            // column9
            // 
            this.column9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column9.ForeColor = System.Drawing.Color.Empty;
            this.column9.Width = 20;
            // 
            // column10
            // 
            this.column10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column10.ForeColor = System.Drawing.Color.Empty;
            this.column10.Width = 100;
            // 
            // column11
            // 
            this.column11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column11.ForeColor = System.Drawing.Color.Empty;
            this.column11.Width = 20;
            // 
            // column12
            // 
            this.column12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column12.ForeColor = System.Drawing.Color.Empty;
            this.column12.Width = 100;
            // 
            // column13
            // 
            this.column13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column13.ForeColor = System.Drawing.Color.Empty;
            this.column13.Width = 20;
            // 
            // column14
            // 
            this.column14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column14.ForeColor = System.Drawing.Color.Empty;
            this.column14.Width = 100;
            // 
            // row20
            // 
            this.row20.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row20.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row20.FullRowSelected = false;
            this.row20.Text = null;
            // 
            // row18
            // 
            this.row18.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row18.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row18.FullRowSelected = false;
            this.row18.Text = null;
            // 
            // row19
            // 
            this.row19.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row19.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row19.FullRowSelected = false;
            this.row19.Text = null;
            // 
            // tabItemRight
            // 
            this.tabItemRight.Name = "tabItemRight";
            this.tabItemRight.Text = "权限及方案设置";
            // 
            // column16
            // 
            this.column16.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column16.ForeColor = System.Drawing.Color.Empty;
            this.column16.Width = 20;
            // 
            // column17
            // 
            this.column17.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column17.ForeColor = System.Drawing.Color.Empty;
            this.column17.Width = 100;
            // 
            // column18
            // 
            this.column18.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column18.ForeColor = System.Drawing.Color.Empty;
            this.column18.Width = 20;
            // 
            // column19
            // 
            this.column19.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column19.ForeColor = System.Drawing.Color.Empty;
            this.column19.Width = 100;
            // 
            // column20
            // 
            this.column20.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column20.ForeColor = System.Drawing.Color.Empty;
            this.column20.Width = 20;
            // 
            // column21
            // 
            this.column21.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column21.ForeColor = System.Drawing.Color.Empty;
            this.column21.Width = 100;
            // 
            // column22
            // 
            this.column22.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column22.ForeColor = System.Drawing.Color.Empty;
            this.column22.Width = 20;
            // 
            // column23
            // 
            this.column23.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column23.ForeColor = System.Drawing.Color.Empty;
            this.column23.Width = 100;
            // 
            // tabItemAccount
            // 
            this.tabItemAccount.Name = "tabItemAccount";
            this.tabItemAccount.Text = "核算相关设置";
            // 
            // tabItemClear
            // 
            this.tabItemClear.Name = "tabItemClear";
            this.tabItemClear.Text = "清算相关设置";
            // 
            // cell68
            // 
            this.cell68.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell68.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell68.Text = null;
            // 
            // checkBoxCell8
            // 
            this.checkBoxCell8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.checkBoxCell8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.checkBoxCell8.Text = null;
            // 
            // cell67
            // 
            this.cell67.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell67.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell67.Text = null;
            // 
            // checkBoxCell7
            // 
            this.checkBoxCell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.checkBoxCell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.checkBoxCell7.Text = null;
            // 
            // cell66
            // 
            this.cell66.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell66.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell66.Text = null;
            // 
            // checkBoxCell6
            // 
            this.checkBoxCell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.checkBoxCell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.checkBoxCell6.Text = null;
            // 
            // cell71
            // 
            this.cell71.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell71.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell71.Text = null;
            // 
            // checkBoxCell4
            // 
            this.checkBoxCell4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.checkBoxCell4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.checkBoxCell4.Text = null;
            // 
            // cell65
            // 
            this.cell65.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell65.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell65.Text = null;
            // 
            // checkBoxCell3
            // 
            this.checkBoxCell3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.checkBoxCell3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.checkBoxCell3.Text = null;
            // 
            // cell64
            // 
            this.cell64.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell64.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell64.Text = null;
            // 
            // checkBoxCell2
            // 
            this.checkBoxCell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.checkBoxCell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.checkBoxCell2.Text = null;
            // 
            // cell63
            // 
            this.cell63.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell63.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell63.Text = null;
            // 
            // checkBoxCell1
            // 
            this.checkBoxCell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.checkBoxCell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.checkBoxCell1.Text = null;
            // 
            // cell70
            // 
            this.cell70.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell70.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell70.Text = null;
            // 
            // checkBoxCell5
            // 
            this.checkBoxCell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.checkBoxCell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.checkBoxCell5.Text = null;
            // 
            // cell69
            // 
            this.cell69.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell69.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell69.Text = null;
            // 
            // tabControl1
            // 
            this.tabControl1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(233)))), ((int)(((byte)(238)))), ((int)(((byte)(244)))));
            this.tabControl1.CanReorderTabs = true;
            this.tabControl1.ColorScheme.TabBackground = System.Drawing.Color.White;
            this.tabControl1.ColorScheme.TabBorder = System.Drawing.Color.FromArgb(((int)(((byte)(172)))), ((int)(((byte)(168)))), ((int)(((byte)(153)))));
            this.tabControl1.ColorScheme.TabBorderSize = 1;
            this.tabControl1.ColorScheme.TabItemBackground = System.Drawing.Color.FromArgb(((int)(((byte)(239)))), ((int)(((byte)(239)))), ((int)(((byte)(239)))));
            this.tabControl1.ColorScheme.TabItemBackground2 = System.Drawing.Color.FromArgb(((int)(((byte)(239)))), ((int)(((byte)(239)))), ((int)(((byte)(239)))));
            this.tabControl1.ColorScheme.TabItemBorder = System.Drawing.Color.FromArgb(((int)(((byte)(233)))), ((int)(((byte)(238)))), ((int)(((byte)(244)))));
            this.tabControl1.ColorScheme.TabItemBorderDark = System.Drawing.Color.White;
            this.tabControl1.ColorScheme.TabItemBorderLight = System.Drawing.Color.Empty;
            this.tabControl1.ColorScheme.TabItemHotBackground = System.Drawing.Color.Empty;
            this.tabControl1.ColorScheme.TabItemHotBackground2 = System.Drawing.Color.FromArgb(((int)(((byte)(233)))), ((int)(((byte)(238)))), ((int)(((byte)(244)))));
            this.tabControl1.ColorScheme.TabItemHotBorder = System.Drawing.Color.FromArgb(((int)(((byte)(233)))), ((int)(((byte)(238)))), ((int)(((byte)(244)))));
            this.tabControl1.ColorScheme.TabItemHotBorderDark = System.Drawing.Color.FromArgb(((int)(((byte)(233)))), ((int)(((byte)(238)))), ((int)(((byte)(244)))));
            this.tabControl1.ColorScheme.TabItemHotBorderLight = System.Drawing.Color.FromArgb(((int)(((byte)(233)))), ((int)(((byte)(238)))), ((int)(((byte)(244)))));
            this.tabControl1.ColorScheme.TabItemSelectedBackground = System.Drawing.Color.FromArgb(((int)(((byte)(203)))), ((int)(((byte)(219)))), ((int)(((byte)(234)))));
            this.tabControl1.ColorScheme.TabItemSelectedBackground2 = System.Drawing.Color.FromArgb(((int)(((byte)(203)))), ((int)(((byte)(219)))), ((int)(((byte)(234)))));
            this.tabControl1.ColorScheme.TabItemSelectedBorder = System.Drawing.Color.FromArgb(((int)(((byte)(172)))), ((int)(((byte)(168)))), ((int)(((byte)(153)))));
            this.tabControl1.ColorScheme.TabItemSelectedBorderDark = System.Drawing.Color.Empty;
            this.tabControl1.ColorScheme.TabItemSelectedBorderLight = System.Drawing.Color.Empty;
            this.tabControl1.ColorScheme.TabItemSeparator = System.Drawing.Color.FromArgb(((int)(((byte)(172)))), ((int)(((byte)(168)))), ((int)(((byte)(153)))));
            this.tabControl1.ColorScheme.TabPanelBackground = System.Drawing.Color.FromArgb(((int)(((byte)(239)))), ((int)(((byte)(239)))), ((int)(((byte)(239)))));
            this.tabControl1.ColorScheme.TabPanelBackground2 = System.Drawing.Color.White;
            this.tabControl1.ColorScheme.TabPanelBorder = System.Drawing.Color.FromArgb(((int)(((byte)(172)))), ((int)(((byte)(168)))), ((int)(((byte)(153)))));
            this.tabControl1.Controls.Add(this.tabControlPanel1);
            this.tabControl1.Location = new System.Drawing.Point(0, 0);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedTabFont = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.tabControl1.SelectedTabIndex = -1;
            this.tabControl1.Size = new System.Drawing.Size(256, 152);
            this.tabControl1.TabIndex = 0;
            this.tabControl1.TabLayoutType = YssDevComponents.DotNetBar.eTabLayoutType.FixedWithNavigationBox;
            // 
            // tabControlPanel1
            // 
            this.tabControlPanel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tabControlPanel1.Location = new System.Drawing.Point(0, 26);
            this.tabControlPanel1.Name = "tabControlPanel1";
            this.tabControlPanel1.Padding = new System.Windows.Forms.Padding(1);
            this.tabControlPanel1.ScrollStyle = YssDevComponents.DotNetBar.ScrollBar.ScrollStyle.Concise;
            this.tabControlPanel1.Size = new System.Drawing.Size(256, 126);
            this.tabControlPanel1.Style.BackColor1.Color = System.Drawing.Color.FromArgb(((int)(((byte)(239)))), ((int)(((byte)(239)))), ((int)(((byte)(239)))));
            this.tabControlPanel1.Style.BackColor2.Color = System.Drawing.Color.White;
            this.tabControlPanel1.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            this.tabControlPanel1.Style.BorderColor.Color = System.Drawing.Color.FromArgb(((int)(((byte)(172)))), ((int)(((byte)(168)))), ((int)(((byte)(153)))));
            this.tabControlPanel1.Style.BorderSide = ((YssDevComponents.DotNetBar.eBorderSide)(((YssDevComponents.DotNetBar.eBorderSide.Left | YssDevComponents.DotNetBar.eBorderSide.Right)
                        | YssDevComponents.DotNetBar.eBorderSide.Bottom)));
            this.tabControlPanel1.Style.GradientAngle = 90;
            this.tabControlPanel1.TabIndex = 2;
            this.tabControlPanel1.TabItem = this.tabItemRight;
            // 
            // splitRight
            // 
            this.splitRight.Dock = System.Windows.Forms.DockStyle.Right;
            this.splitRight.ExpandableControl = this.pnlRight;
            this.splitRight.Location = new System.Drawing.Point(493, 30);
            this.splitRight.Margin = new System.Windows.Forms.Padding(3, 0, 3, 3);
            this.splitRight.Name = "splitRight";
            this.splitRight.Size = new System.Drawing.Size(6, 319);
            this.splitRight.TabIndex = 17;
            this.splitRight.TabStop = false;
            this.splitRight.ExpandedChanged += new Yss.Controls.ExpandEventHandler(this.splitRight_ExpandedChanged);
            // 
            // pnlRight
            // 
            // 
            // 
            // 
            this.pnlRight.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.pnlRight.Border.Bottom = false;
            this.pnlRight.Border.Left = false;
            this.pnlRight.Border.Right = false;
            this.pnlRight.Border.Top = false;
            this.pnlRight.Controls.Add(this.navBarRight);
            this.pnlRight.Controls.Add(this.pnlBarPort);
            this.pnlRight.Controls.Add(this.tbRightFilter);
            this.pnlRight.Dock = System.Windows.Forms.DockStyle.Right;
            this.pnlRight.Location = new System.Drawing.Point(499, 30);
            this.pnlRight.Name = "pnlRight";
            this.pnlRight.Size = new System.Drawing.Size(244, 319);
            this.pnlRight.TabIndex = 18;
            // 
            // navBarRight
            // 
            this.navBarRight.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.navBarRight.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(164)))), ((int)(((byte)(187)))), ((int)(((byte)(217)))));
            this.navBarRight.Border.Bottom = true;
            this.navBarRight.Border.Left = false;
            this.navBarRight.Border.Right = false;
            this.navBarRight.Border.Top = true;
            this.navBarRight.CaptionImage = ((System.Drawing.Image)(resources.GetObject("navBarRight.CaptionImage")));
            this.navBarRight.CaptionImageSelected = ((System.Drawing.Image)(resources.GetObject("navBarRight.CaptionImageSelected")));
            this.navBarRight.Controls.Add(this.navigateItemMain);
            this.navBarRight.Dock = System.Windows.Forms.DockStyle.Fill;
            this.navBarRight.Items.AddRange(new Yss.KNavigation.NavigateItem[] {
            this.navigateItemMain});
            this.navBarRight.Location = new System.Drawing.Point(0, 42);
            this.navBarRight.Name = "navBarRight";
            this.navBarRight.SelectedItem = this.navigateItemMain;
            this.navBarRight.Size = new System.Drawing.Size(244, 252);
            this.navBarRight.TabIndex = 1;
            this.navBarRight.Text = "navigateBar1";
            // 
            // navigateItemMain
            // 
            this.navigateItemMain.Controls.Add(this.tbRightMain);
            this.navigateItemMain.Controls.Add(this.panelEx1);
            this.navigateItemMain.Name = "navigateItemMain";
            this.navigateItemMain.ShowSetButton = true;
            this.navigateItemMain.Text = "继承内容";
            // 
            // tbRightMain
            // 
            this.tbRightMain.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.tbRightMain.Border.Bottom = false;
            this.tbRightMain.Border.Left = false;
            this.tbRightMain.Border.Right = false;
            this.tbRightMain.Border.Top = true;
            this.tbRightMain.DefaultToolStripItems = Yss.KTable.Enums.SysToolStripItems.GardingMenu;
            this.tbRightMain.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tbRightMain.GridLineColor = System.Drawing.Color.LightSteelBlue;
            this.tbRightMain.Location = new System.Drawing.Point(1, 61);
            this.tbRightMain.Name = "tbRightMain";
            this.tbRightMain.PlusMinusLineColor = System.Drawing.SystemColors.ControlText;
            this.tbRightMain.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconIndentLine;
            this.tbRightMain.SelectionMode = System.Windows.Forms.SelectionMode.MultiExtended;
            this.tbRightMain.ShowCheckBox = true;
            this.tbRightMain.ShowColumnHeader = false;
            this.tbRightMain.Size = new System.Drawing.Size(242, 190);
            this.tbRightMain.TabIndex = 2;
            this.tbRightMain.Text = "table1";
            this.tbRightMain.CheckStateChanged += new Yss.KTable.Events.CheckStateChanged(this.tbRightMain_CheckStateChanged);
            // 
            // pnlBarPort
            // 
            // 
            // 
            // 
            this.pnlBarPort.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.pnlBarPort.Border.Bottom = false;
            this.pnlBarPort.Border.Left = false;
            this.pnlBarPort.Border.Right = false;
            this.pnlBarPort.Border.Top = false;
            this.pnlBarPort.Controls.Add(this.barPort);
            this.pnlBarPort.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.pnlBarPort.Location = new System.Drawing.Point(0, 294);
            this.pnlBarPort.Name = "pnlBarPort";
            this.pnlBarPort.Size = new System.Drawing.Size(244, 25);
            this.pnlBarPort.TabIndex = 14;
            // 
            // barPort
            // 
            this.barPort.AccessibleRole = System.Windows.Forms.AccessibleRole.StatusBar;
            this.barPort.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(247)))), ((int)(((byte)(247)))), ((int)(((byte)(247)))));
            this.barPort.BarType = YssDevComponents.DotNetBar.eBarType.StatusBar;
            this.barPort.Dock = System.Windows.Forms.DockStyle.Fill;
            this.barPort.DockSide = YssDevComponents.DotNetBar.eDockSide.Bottom;
            this.barPort.Items.AddRange(new YssDevComponents.DotNetBar.BaseItem[] {
            this.chkCheckAll,
            this.chkBoxCheckedRowsCount});
            this.barPort.Name = "barPort";
            this.barPort.RoundCorners = false;
            this.barPort.Stretch = true;
            this.barPort.Style = YssDevComponents.DotNetBar.eDotNetBarStyle.Office2007;
            this.barPort.TabIndex = 3;
            this.barPort.TabStop = false;
            // 
            // chkCheckAll
            // 
            this.chkCheckAll.BeginGroup = true;
            this.chkCheckAll.Name = "chkCheckAll";
            this.chkCheckAll.Shortcuts.Add(YssDevComponents.DotNetBar.eShortcut.CtrlA);
            this.chkCheckAll.Text = "全选";
            this.chkCheckAll.Tooltip = "全选";
            this.chkCheckAll.CheckedChanged += new YssDevComponents.DotNetBar.CheckBoxChangeEventHandler(this.chkCheckAll_CheckedChanged);
            // 
            // chkBoxCheckedRowsCount
            // 
            this.chkBoxCheckedRowsCount.Name = "chkBoxCheckedRowsCount";
            this.chkBoxCheckedRowsCount.Text = "0";
            this.chkBoxCheckedRowsCount.TextColor = System.Drawing.Color.Red;
            this.chkBoxCheckedRowsCount.CheckedChanged += new YssDevComponents.DotNetBar.CheckBoxChangeEventHandler(this.chkBoxCheckedRowsCount_CheckedChanged);
            // 
            // panelEx1
            // 
            // 
            // 
            // 
            this.panelEx1.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.panelEx1.Border.Bottom = false;
            this.panelEx1.Border.Left = false;
            this.panelEx1.Border.Right = false;
            this.panelEx1.Border.Top = false;
            this.panelEx1.Controls.Add(this.checkSX);
            this.panelEx1.Controls.Add(this.cboFA);
            this.panelEx1.Controls.Add(this.labFA);
            this.panelEx1.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelEx1.Location = new System.Drawing.Point(1, 28);
            this.panelEx1.Name = "panelEx1";
            this.panelEx1.Size = new System.Drawing.Size(242, 33);
            this.panelEx1.TabIndex = 3;
            // 
            // labFA
            // 
            this.labFA.AutoSize = true;
            this.labFA.Location = new System.Drawing.Point(5, 7);
            this.labFA.Name = "labFA";
            this.labFA.Size = new System.Drawing.Size(41, 12);
            this.labFA.TabIndex = 0;
            this.labFA.Text = "方案：";
            this.labFA.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // cboFA
            // 
            this.cboFA.AddedSelItemName = "";
            this.cboFA.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboFA.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboFA.Border.BorderColorHot = System.Drawing.Color.FromArgb(((int)(((byte)(109)))), ((int)(((byte)(129)))), ((int)(((byte)(165)))));
            this.cboFA.Border.Bottom = true;
            this.cboFA.Border.Left = true;
            this.cboFA.Border.Right = true;
            this.cboFA.Border.Top = true;
            this.cboFA.FilterCond = "";
            this.cboFA.Location = new System.Drawing.Point(49, 4);
            this.cboFA.Margin = new System.Windows.Forms.Padding(0);
            this.cboFA.MethodInfo = null;
            this.cboFA.Name = "cboFA";
            this.cboFA.QueryCond = "";
            this.cboFA.QueryType = "";
            this.cboFA.Size = new System.Drawing.Size(102, 21);
            this.cboFA.TabIndex = 1;
            this.cboFA.UseErrorTip = false;
            this.cboFA.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboFA.BeforeDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(this.cboFA_BeforeDropDownClick);
            this.cboFA.SelectedValueChanged += new System.EventHandler(this.cboFA_SelectedValueChanged);
            // 
            // checkSX
            // 
            this.checkSX.AutoSize = true;
            this.checkSX.Location = new System.Drawing.Point(161, 6);
            this.checkSX.Name = "checkSX";
            this.checkSX.Size = new System.Drawing.Size(72, 16);
            this.checkSX.TabIndex = 2;
            this.checkSX.Text = "设为首选";
            this.checkSX.UseVisualStyleBackColor = true;
            // 
            // Frm_PORT_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(743, 349);
            this.DoubleBuffered = true;
            this.Name = "Frm_PORT_S";
            this.StatuType = "新增(&Add...)";
            this.Text = "组合基本参数";
            this.YssSetStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.Load += new System.EventHandler(this.Frm_PORT_S_Load);
            this.YssOnBeforeSaveClick += new FrmBaseSet.BeforeSaveClick(this.Frm_PORT_S_YssOnBeforeSaveClick);
            this.tbMain.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.tbRightFilter.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.tabControl1)).EndInit();
            this.tabControl1.ResumeLayout(false);
            this.pnlRight.ResumeLayout(false);
            this.navBarRight.ResumeLayout(false);
            this.navigateItemMain.ResumeLayout(false);
            this.pnlBarPort.ResumeLayout(false);
            this.panelEx1.ResumeLayout(false);
            this.panelEx1.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
        private Yss.KTable.Models.Column column3;
        private Yss.KTable.Models.Column column4;
        private Yss.KTable.Models.Column column5;
        private Yss.KTable.Models.Row row1;
        private Yss.KTable.Models.Row row3;
        private Yss.KTable.Models.Row row4;
        private Yss.KTable.Models.Row row5;
        private Yss.KTable.Models.Row row6;
        private Yss.KTable.Models.Row row7;
        private Yss.KTable.Models.Row row8;
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
        private Yss.KTable.Models.Cell cell31;
        private Yss.KTable.Models.Cell cell32;
        private Yss.KTable.Models.Cell cell33;
        private Yss.KTable.Models.Cell cell34;
        private Yss.KTable.Models.Cell cell35;
        private Yss.KTable.Models.Cell cell36;
        private Yss.KRichEx.YssTextBox txtPortCode;
        private Yss.KRichEx.YssTextBox txtPortShortName;
        private Yss.KRichEx.YssTextBox txtPortCNName;
        private Yss.KRichEx.YssTextBox txtPortENName;
        private Yss.KRichEx.YssTextBox txtAssetCode;
        private FAST.Core.BaseControl.YssSelCombox cboCurrency;
        private Yss.KTable.Models.Cell cell37;
        private Yss.KTable.Models.Cell cell38;
        private Yss.KTable.Models.Cell cell39;
        private Yss.KTable.Models.Cell cell40;
        private Yss.KTable.Models.Cell cell41;
        private Yss.KTable.Models.Cell cell42;
        private Yss.KTable.Models.Cell cell43;
        private Yss.KTable.Models.Cell cell44;
        private Yss.KTable.Models.Cell cell45;
        private Yss.KTable.Models.Row row2;
        private Yss.KTable.Models.Row row14;
        private Yss.KTable.Models.Row row15;
        private FAST.Core.BaseControl.YssSelCombox cboAssetType;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpExpirationDate;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpInceptionDate;
        private Yss.KTable.Models.Row row9;
        private Yss.KTable.Models.Cell cell46;
        private Yss.KTable.Models.Cell cell47;
        private Yss.KTable.Models.Cell cell48;
        private Yss.KTable.Models.Cell cell49;
        private Yss.KTable.Models.Cell cell50;
        private FAST.Core.BaseControl.YssSelCombox cboPortLever;
        private FAST.Core.BaseControl.YssSelCombox cboPort;
        private FAST.Core.BaseControl.YssSelCombox cboHolidays;
        private Yss.KTable.Models.Row row10;
        private Yss.KTable.Models.Cell cell51;
        private Yss.KTable.Models.Cell cell52;
        private Yss.KTable.Models.Row row11;
        private FAST.Core.BaseControl.YssSelCombox cboAssetSort;
        private Yss.KTable.Models.Cell cell53;
        private Yss.KTable.Models.Cell cell54;
        private Yss.KTable.Models.Cell cell55;
        private FAST.Core.BaseControl.YssSelCombox cboProdState;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpClear;
        private Yss.KTable.Models.Cell cell56;
        private Yss.KTable.Models.Cell cell57;
        private Yss.KTable.Models.Cell cell58;
        private Yss.KTable.Models.Cell cell59;
        private Yss.KTable.Models.Cell cell60;
        private YssDevComponents.DotNetBar.TabItem tabItemClear;
        private Yss.KTable.Models.Table tbRightFilter;
        private Yss.KTable.Models.Column column6;
        private Yss.KTable.Models.Column column7;
        private Yss.KTable.Models.Row row13;
        private Yss.KTable.Models.Cell cell61;
        private Yss.KTable.Models.Cell cell62;
        private FAST.Core.BaseControl.YssSelCombox cboPortCode;
        private Yss.KTable.Models.Column column8;
        private Yss.KTable.Models.Column column9;
        private Yss.KTable.Models.Column column10;
        private Yss.KTable.Models.Column column11;
        private Yss.KTable.Models.Column column12;
        private Yss.KTable.Models.Column column13;
        private Yss.KTable.Models.Column column14;
        private Yss.KTable.Models.Row row18;
        private Yss.KTable.Models.Row row19;
        private Yss.KTable.Models.Column column15;
        private Yss.KTable.Models.Row row20;
        private Yss.KTable.Models.Column column16;
        private Yss.KTable.Models.Column column17;
        private Yss.KTable.Models.Column column18;
        private Yss.KTable.Models.Column column19;
        private Yss.KTable.Models.Column column20;
        private Yss.KTable.Models.Column column21;
        private Yss.KTable.Models.Column column22;
        private Yss.KTable.Models.Column column23;
        private Yss.KTable.Models.Row row12;
        private YssDevComponents.DotNetBar.TabItem tabItemRight;
        private YssDevComponents.DotNetBar.TabItem tabItemAccount;
        private Yss.KTable.Models.Cell cell68;
        private Yss.KTable.Models.CheckBoxCell checkBoxCell8;
        private Yss.KTable.Models.Cell cell67;
        private Yss.KTable.Models.CheckBoxCell checkBoxCell7;
        private Yss.KTable.Models.Cell cell66;
        private Yss.KTable.Models.CheckBoxCell checkBoxCell6;
        private Yss.KTable.Models.Cell cell71;
        private Yss.KTable.Models.CheckBoxCell checkBoxCell4;
        private Yss.KTable.Models.Cell cell65;
        private Yss.KTable.Models.CheckBoxCell checkBoxCell3;
        private Yss.KTable.Models.Cell cell64;
        private Yss.KTable.Models.CheckBoxCell checkBoxCell2;
        private Yss.KTable.Models.Cell cell63;
        private Yss.KTable.Models.CheckBoxCell checkBoxCell1;
        private Yss.KTable.Models.Cell cell70;
        private Yss.KTable.Models.CheckBoxCell checkBoxCell5;
        private Yss.KTable.Models.Cell cell69;
        private YssDevComponents.DotNetBar.TabControl tabControl1;
        private YssDevComponents.DotNetBar.TabControlPanel tabControlPanel1;
        private Yss.KTable.Models.Column column24;
        private Yss.KTable.Models.Column column25;
        private Yss.KTable.Models.Column column26;
        private Yss.KTable.Models.Column column27;
        private Yss.KTable.Models.Column column28;
        private Yss.KTable.Models.Row row23;
        protected Yss.Controls.ExpandableSplitter splitRight;
        private Yss.Controls.PanelEx pnlRight;
        private Yss.KNavigation.NavigateBar navBarRight;
        public Yss.KTable.Models.Table tbRightMain;
        public Yss.KNavigation.NavigateItem navigateItemMain;
        protected Yss.Controls.PanelEx pnlBarPort;
        public YssDevComponents.DotNetBar.Bar barPort;
        private YssDevComponents.DotNetBar.CheckBoxItem chkCheckAll;
        private YssDevComponents.DotNetBar.CheckBoxItem chkBoxCheckedRowsCount;
        private Yss.Controls.PanelEx panelEx1;
        private System.Windows.Forms.Label labFA;
        private FAST.Core.BaseControl.YssSelCombox cboFA;
        private System.Windows.Forms.CheckBox checkSX;
    }
}