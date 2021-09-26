﻿using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;

using FAST.Core.Context;

using FAST.Core.Resource;
namespace YssSecInformation.Sv.Form
{
    partial class Frm_SEC_BASE_LC_S
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
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo6 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo5 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo20 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo4 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo2 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo8 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo15 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo3 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo14 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo1 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo21 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo9 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo11 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo19 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo16 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo12 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo13 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo10 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo7 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo18 = new FAST.Core.BaseControl.ControlMethodInfo();
            this.cell96 = new Yss.KTable.Models.Cell();
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
            this.cell56 = new Yss.KTable.Models.Cell();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell29 = new Yss.KTable.Models.Cell();
            this.cell30 = new Yss.KTable.Models.Cell();
            this.txtIsinCode = new Yss.KRichEx.YssTextBox();
            this.row4 = new Yss.KTable.Models.Row();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.txtSecMktCode = new Yss.KRichEx.YssTextBox();
            this.cell57 = new Yss.KTable.Models.Cell();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.txtSecName = new Yss.KRichEx.YssTextBox();
            this.row5 = new Yss.KTable.Models.Row();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.cboSecVar = new FAST.Core.BaseControl.YssSelCombox();
            this.cell58 = new Yss.KTable.Models.Cell();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.cell13 = new Yss.KTable.Models.Cell();
            this.cboMkt = new FAST.Core.BaseControl.YssSelCombox();
            this.row6 = new Yss.KTable.Models.Row();
            this.cell14 = new Yss.KTable.Models.Cell();
            this.cell15 = new Yss.KTable.Models.Cell();
            this.cboCury = new FAST.Core.BaseControl.YssSelCombox();
            this.cell59 = new Yss.KTable.Models.Cell();
            this.cell16 = new Yss.KTable.Models.Cell();
            this.cell17 = new Yss.KTable.Models.Cell();
            this.cell18 = new Yss.KTable.Models.Cell();
            this.txtFvIssue = new Yss.KRichEx.YssTextBox();
            this.cell116 = new Yss.KTable.Models.Cell();
            this.panel1 = new System.Windows.Forms.Panel();
            this.row7 = new Yss.KTable.Models.Row();
            this.cell19 = new Yss.KTable.Models.Cell();
            this.cell20 = new Yss.KTable.Models.Cell();
            this.txtAmountHD = new Yss.KRichEx.YssTextBox();
            this.cell60 = new Yss.KTable.Models.Cell();
            this.cell21 = new Yss.KTable.Models.Cell();
            this.cell22 = new Yss.KTable.Models.Cell();
            this.cell23 = new Yss.KTable.Models.Cell();
            this.txtPriceFcr = new Yss.KRichEx.YssTextBox();
            this.dtpToList = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell97 = new Yss.KTable.Models.Cell();
            this.dtpOffList = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell107 = new Yss.KTable.Models.Cell();
            this.row11 = new Yss.KTable.Models.Row();
            this.cell31 = new Yss.KTable.Models.Cell();
            this.cell32 = new Yss.KTable.Models.Cell();
            this.cboAIMode = new FAST.Core.BaseControl.YssSelCombox();
            this.cell62 = new Yss.KTable.Models.Cell();
            this.cell33 = new Yss.KTable.Models.Cell();
            this.cell34 = new Yss.KTable.Models.Cell();
            this.cell35 = new Yss.KTable.Models.Cell();
            this.cboOrgan = new FAST.Core.BaseControl.PenetrationComboBox();
            this.row12 = new Yss.KTable.Models.Row();
            this.txtCouponRate = new Yss.KRichEx.ImprovedTextBox();
            this.cell81 = new Yss.KTable.Models.Cell();
            this.txtTaxRate = new Yss.KRichEx.ImprovedTextBox();
            this.cell101 = new Yss.KTable.Models.Cell();
            this.cboPayFrequency = new FAST.Core.BaseControl.YssSelCombox();
            this.cell85 = new Yss.KTable.Models.Cell();
            this.cboPayFormula = new FAST.Core.BaseControl.YssSelCombox();
            this.cell103 = new Yss.KTable.Models.Cell();
            this.dtpCountBeginDate = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell89 = new Yss.KTable.Models.Cell();
            this.improvedTextBox3 = new Yss.KRichEx.ImprovedTextBox();
            this.dtpCountEndDate = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell105 = new Yss.KTable.Models.Cell();
            this.row16 = new Yss.KTable.Models.Row();
            this.cell51 = new Yss.KTable.Models.Cell();
            this.cell52 = new Yss.KTable.Models.Cell();
            this.cboIncomeType = new FAST.Core.BaseControl.YssSelCombox();
            this.cell61 = new Yss.KTable.Models.Cell();
            this.cell53 = new Yss.KTable.Models.Cell();
            this.cell54 = new Yss.KTable.Models.Cell();
            this.cell55 = new Yss.KTable.Models.Cell();
            this.cboInvestment = new FAST.Core.BaseControl.YssSelCombox();
            this.column6 = new Yss.KTable.Models.Column();
            this.row17 = new Yss.KTable.Models.Row();
            this.cell67 = new Yss.KTable.Models.Cell();
            this.cell68 = new Yss.KTable.Models.Cell();
            this.cboFHZT = new FAST.Core.BaseControl.YssSelCombox();
            this.cell69 = new Yss.KTable.Models.Cell();
            this.iniDate = new Yss.KRichEx.IntegerInputEx();
            this.cell70 = new Yss.KTable.Models.Cell();
            this.cell71 = new Yss.KTable.Models.Cell();
            this.cell72 = new Yss.KTable.Models.Cell();
            this.cboDateType = new FAST.Core.BaseControl.YssSelCombox();
            this.cboBackward = new FAST.Core.BaseControl.YssSelCombox();
            this.cell93 = new Yss.KTable.Models.Cell();
            this.row19 = new Yss.KTable.Models.Row();
            this.cell76 = new Yss.KTable.Models.Cell();
            this.cell77 = new Yss.KTable.Models.Cell();
            this.cboETFType = new FAST.Core.BaseControl.YssSelCombox();
            this.cell78 = new Yss.KTable.Models.Cell();
            this.cell79 = new Yss.KTable.Models.Cell();
            this.cell123 = new Yss.KTable.Models.Cell();
            this.cell124 = new Yss.KTable.Models.Cell();
            this.iniRateYearDays = new Yss.KRichEx.IntegerInputEx();
            this.cell118 = new Yss.KTable.Models.Cell();
            this.yssDateTimeFX = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.tabControl1 = new Yss.Controls.TabControl();
            this.tabPaFBZC = new Yss.Controls.TabPage();
            this.tableFBZC1 = new Yss.KTable.Models.Table(this.components);
            this.column13 = new Yss.KTable.Models.Column();
            this.column14 = new Yss.KTable.Models.Column();
            this.column16 = new Yss.KTable.Models.Column();
            this.column17 = new Yss.KTable.Models.Column();
            this.column18 = new Yss.KTable.Models.Column();
            this.yssSelCombox_jtjz4nostd = new FAST.Core.BaseControl.YssSelCombox();
            this.cell130 = new Yss.KTable.Models.Cell();
            this.TextBoxLLSX = new Yss.KRichEx.ImprovedTextBox();
            this.cell50 = new Yss.KTable.Models.Cell();
            this.improvedTextBoxLC = new Yss.KRichEx.ImprovedTextBox();
            this.cell170 = new Yss.KTable.Models.Cell();
            this.yssTextBoxYJLX = new Yss.KRichEx.YssTextBox();
            this.cell119 = new Yss.KTable.Models.Cell();
            this.improvedTextBoxSLV = new Yss.KRichEx.ImprovedTextBox();
            this.cell108 = new Yss.KTable.Models.Cell();
            this.TextBoxJXLLV = new Yss.KRichEx.ImprovedTextBox();
            this.cell39 = new Yss.KTable.Models.Cell();
            this.TextBoxXX = new Yss.KRichEx.ImprovedTextBox();
            this.cell66 = new Yss.KTable.Models.Cell();
            this.TextBoxJZLIL = new Yss.KRichEx.ImprovedTextBox();
            this.cell38 = new Yss.KTable.Models.Cell();
            this.panel2 = new System.Windows.Forms.Panel();
            this.yssTextMM = new Yss.KRichEx.YssTextBox();
            this.yssSelFX = new FAST.Core.BaseControl.YssSelCombox();
            this.cell48 = new Yss.KTable.Models.Cell();
            this.yssTextBoxDay = new Yss.KRichEx.YssTextBox();
            this.yssSelJXGS = new FAST.Core.BaseControl.YssSelCombox();
            this.cell44 = new Yss.KTable.Models.Cell();
            this.TextBoxBLXS = new Yss.KRichEx.ImprovedTextBox();
            this.cell42 = new Yss.KTable.Models.Cell();
            this.yssSelLLLX = new FAST.Core.BaseControl.YssSelCombox();
            this.cell25 = new Yss.KTable.Models.Cell();
            this.yssSelLLDM = new FAST.Core.BaseControl.YssSelCombox();
            this.cell36 = new Yss.KTable.Models.Cell();
            this.yssDateTimeDQ = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell122 = new Yss.KTable.Models.Cell();
            this.yssSelDZ = new FAST.Core.BaseControl.YssSelCombox();
            this.cell112 = new Yss.KTable.Models.Cell();
            this.yssDateTimeQX = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell74 = new Yss.KTable.Models.Cell();
            this.yssDateTimeJX = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell110 = new Yss.KTable.Models.Cell();
            this.row8 = new Yss.KTable.Models.Row();
            this.cell24 = new Yss.KTable.Models.Cell();
            this.cell27 = new Yss.KTable.Models.Cell();
            this.cell28 = new Yss.KTable.Models.Cell();
            this.row9 = new Yss.KTable.Models.Row();
            this.cell37 = new Yss.KTable.Models.Cell();
            this.cell40 = new Yss.KTable.Models.Cell();
            this.cell41 = new Yss.KTable.Models.Cell();
            this.row10 = new Yss.KTable.Models.Row();
            this.cell43 = new Yss.KTable.Models.Cell();
            this.cell46 = new Yss.KTable.Models.Cell();
            this.cell47 = new Yss.KTable.Models.Cell();
            this.row13 = new Yss.KTable.Models.Row();
            this.cell49 = new Yss.KTable.Models.Cell();
            this.cell64 = new Yss.KTable.Models.Cell();
            this.cell65 = new Yss.KTable.Models.Cell();
            this.row35 = new Yss.KTable.Models.Row();
            this.cell26 = new Yss.KTable.Models.Cell();
            this.cell45 = new Yss.KTable.Models.Cell();
            this.cell63 = new Yss.KTable.Models.Cell();
            this.row36 = new Yss.KTable.Models.Row();
            this.cell113 = new Yss.KTable.Models.Cell();
            this.cell168 = new Yss.KTable.Models.Cell();
            this.cell169 = new Yss.KTable.Models.Cell();
            this.row14 = new Yss.KTable.Models.Row();
            this.cell73 = new Yss.KTable.Models.Cell();
            this.cell75 = new Yss.KTable.Models.Cell();
            this.cell109 = new Yss.KTable.Models.Cell();
            this.row15 = new Yss.KTable.Models.Row();
            this.cell111 = new Yss.KTable.Models.Cell();
            this.cell114 = new Yss.KTable.Models.Cell();
            this.cell115 = new Yss.KTable.Models.Cell();
            this.row18 = new Yss.KTable.Models.Row();
            this.cell117 = new Yss.KTable.Models.Cell();
            this.cell120 = new Yss.KTable.Models.Cell();
            this.cell121 = new Yss.KTable.Models.Cell();
            this.row38 = new Yss.KTable.Models.Row();
            this.cell129 = new Yss.KTable.Models.Cell();
            this.cell131 = new Yss.KTable.Models.Cell();
            this.cell132 = new Yss.KTable.Models.Cell();
            this.cell133 = new Yss.KTable.Models.Cell();
            this.tabPaBZZC = new Yss.Controls.TabPage();
            this.tableMin = new Yss.KTable.Models.Table(this.components);
            this.column7 = new Yss.KTable.Models.Column();
            this.column8 = new Yss.KTable.Models.Column();
            this.column9 = new Yss.KTable.Models.Column();
            this.column10 = new Yss.KTable.Models.Column();
            this.column11 = new Yss.KTable.Models.Column();
            this.column12 = new Yss.KTable.Models.Column();
            this.yssSelCombox_jtjz4std = new FAST.Core.BaseControl.YssSelCombox();
            this.row20 = new Yss.KTable.Models.Row();
            this.cell80 = new Yss.KTable.Models.Cell();
            this.cell82 = new Yss.KTable.Models.Cell();
            this.cell83 = new Yss.KTable.Models.Cell();
            this.cell100 = new Yss.KTable.Models.Cell();
            this.row21 = new Yss.KTable.Models.Row();
            this.cell84 = new Yss.KTable.Models.Cell();
            this.cell86 = new Yss.KTable.Models.Cell();
            this.cell87 = new Yss.KTable.Models.Cell();
            this.cell102 = new Yss.KTable.Models.Cell();
            this.row22 = new Yss.KTable.Models.Row();
            this.cell88 = new Yss.KTable.Models.Cell();
            this.cell90 = new Yss.KTable.Models.Cell();
            this.cell91 = new Yss.KTable.Models.Cell();
            this.cell104 = new Yss.KTable.Models.Cell();
            this.row23 = new Yss.KTable.Models.Row();
            this.cell92 = new Yss.KTable.Models.Cell();
            this.cell94 = new Yss.KTable.Models.Cell();
            this.cell95 = new Yss.KTable.Models.Cell();
            this.cell128 = new Yss.KTable.Models.Cell();
            this.row24 = new Yss.KTable.Models.Row();
            this.cell98 = new Yss.KTable.Models.Cell();
            this.cell99 = new Yss.KTable.Models.Cell();
            this.cell106 = new Yss.KTable.Models.Cell();
            this.row25 = new Yss.KTable.Models.Row();
            this.column24 = new Yss.KTable.Models.Column();
            this.column25 = new Yss.KTable.Models.Column();
            this.column26 = new Yss.KTable.Models.Column();
            this.column27 = new Yss.KTable.Models.Column();
            this.column28 = new Yss.KTable.Models.Column();
            this.row30 = new Yss.KTable.Models.Row();
            this.row31 = new Yss.KTable.Models.Row();
            this.row32 = new Yss.KTable.Models.Row();
            this.row33 = new Yss.KTable.Models.Row();
            this.row34 = new Yss.KTable.Models.Row();
            this.column19 = new Yss.KTable.Models.Column();
            this.column20 = new Yss.KTable.Models.Column();
            this.column21 = new Yss.KTable.Models.Column();
            this.column22 = new Yss.KTable.Models.Column();
            this.column23 = new Yss.KTable.Models.Column();
            this.row26 = new Yss.KTable.Models.Row();
            this.row27 = new Yss.KTable.Models.Row();
            this.row28 = new Yss.KTable.Models.Row();
            this.row29 = new Yss.KTable.Models.Row();
            this.tbDesc = new Yss.KTable.Models.Table(this.components);
            this.row37 = new Yss.KTable.Models.Row();
            this.cell125 = new Yss.KTable.Models.Cell();
            this.cell126 = new Yss.KTable.Models.Cell();
            this.portAndGroup = new FAST.Core.BaseControl.GroupTextBox();
            this.cell127 = new Yss.KTable.Models.Cell();
            this.cell134 = new Yss.KTable.Models.Cell();
            this.cell135 = new Yss.KTable.Models.Cell();
            this.cell136 = new Yss.KTable.Models.Cell();
            this.cboFinInstruments = new FAST.Core.BaseControl.YssSelCombox();
            this.tbMain.SuspendLayout();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.tabControl1.SuspendLayout();
            this.tabPaFBZC.SuspendLayout();
            this.tableFBZC1.SuspendLayout();
            this.panel2.SuspendLayout();
            this.tabPaBZZC.SuspendLayout();
            this.tableMin.SuspendLayout();
            this.SuspendLayout();
            // 
            // tbMain
            // 
            // 
            // 
            // 
            this.tbMain.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(111)))), ((int)(((byte)(157)))), ((int)(((byte)(217)))));
            this.tbMain.Border.Left = false;
            this.tbMain.Border.Right = false;
            this.tbMain.Border.Top = false;
            this.tbMain.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.column1,
            this.column2,
            this.column6,
            this.column3,
            this.column4,
            this.column5});
            this.tbMain.Controls.Add(this.cboFinInstruments);
            this.tbMain.Controls.Add(this.cboInvestment);
            this.tbMain.Controls.Add(this.cboETFType);
            this.tbMain.Controls.Add(this.cboDateType);
            this.tbMain.Controls.Add(this.txtPriceFcr);
            this.tbMain.Controls.Add(this.cboCury);
            this.tbMain.Controls.Add(this.cboFHZT);
            this.tbMain.Controls.Add(this.cboOrgan);
            this.tbMain.Controls.Add(this.cboIncomeType);
            this.tbMain.Controls.Add(this.cboAIMode);
            this.tbMain.Controls.Add(this.txtAmountHD);
            this.tbMain.Controls.Add(this.cboMkt);
            this.tbMain.Controls.Add(this.cboSecVar);
            this.tbMain.Controls.Add(this.txtSecCode);
            this.tbMain.Controls.Add(this.txtIsinCode);
            this.tbMain.Controls.Add(this.portAndGroup);
            this.tbMain.Controls.Add(this.txtSecMktCode);
            this.tbMain.Controls.Add(this.txtFvIssue);
            this.tbMain.Controls.Add(this.txtSecName);
            this.tbMain.Controls.Add(this.iniDate);
            this.tbMain.Controls.Add(this.iniRateYearDays);
            this.tbMain.Dock = System.Windows.Forms.DockStyle.Top;
            this.tbMain.GridLineColor = System.Drawing.Color.Transparent;
            this.tbMain.Location = new System.Drawing.Point(0, 31);
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3,
            this.row4,
            this.row5,
            this.row6,
            this.row7,
            this.row16,
            this.row11,
            this.row17,
            this.row19,
            this.row37,
            this.row12});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(489, 330);
            // 
            // btnBar
            // 
            this.btnBar.Location = new System.Drawing.Point(0, 1);
            this.btnBar.Size = new System.Drawing.Size(489, 30);
            // 
            // stBarBottom
            // 
            // 
            // 
            // 
            this.stBarBottom.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.stBarBottom.Border.Left = false;
            this.stBarBottom.Border.Right = false;
            this.stBarBottom.Location = new System.Drawing.Point(0, 644);
            this.stBarBottom.Size = new System.Drawing.Size(489, 25);
            this.stBarBottom.StatuType = "新增(&Add...)";
            // 
            // pnlMain
            // 
            this.pnlMain.Controls.Add(this.tabControl1);
            this.pnlMain.Controls.Add(this.tbDesc);
            this.pnlMain.Padding = new System.Windows.Forms.Padding(0, 1, 0, 0);
            this.pnlMain.Size = new System.Drawing.Size(489, 669);
            this.pnlMain.Controls.SetChildIndex(this.stBarBottom, 0);
            this.pnlMain.Controls.SetChildIndex(this.btnBar, 0);
            this.pnlMain.Controls.SetChildIndex(this.tbDesc, 0);
            this.pnlMain.Controls.SetChildIndex(this.tbMain, 0);
            this.pnlMain.Controls.SetChildIndex(this.tabControl1, 0);
            // 
            // yssPanel1
            // 
            // 
            // 
            // 
            this.yssPanel1.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.yssPanel1.Size = new System.Drawing.Size(489, 669);
            // 
            // hpAssist
            // 
            // 
            // cell96
            // 
            this.cell96.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell96.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell96.InnerControl = null;
            this.cell96.Text = "发行日期：";
            // 
            // column1
            // 
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.Width = 110;
            // 
            // column2
            // 
            this.column2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column2.Width = 60;
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
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("新宋体", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row1.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row1.FullRowSelected = false;
            this.row1.GroupLineLength = 310;
            this.row1.Height = 33;
            this.row1.IsGroup = true;
            this.row1.Text = "基本信息";
            // 
            // row2
            // 
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row2.FullRowSelected = false;
            this.row2.Height = 10;
            this.row2.Text = null;
            // 
            // row3
            // 
            this.row3.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell1,
            this.cell2,
            this.cell56,
            this.cell3,
            this.cell29,
            this.cell30});
            this.row3.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row3.FullRowSelected = false;
            this.row3.Height = 25;
            this.row3.Text = null;
            // 
            // cell1
            // 
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.InnerControl = null;
            this.cell1.Text = "   证券内码：";
            // 
            // cell2
            // 
            this.cell2.ColSpan = 2;
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.txtSecCode;
            // 
            // txtSecCode
            // 
            this.txtSecCode.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.txtSecCode.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtSecCode.Location = new System.Drawing.Point(110, 43);
            this.txtSecCode.Name = "txtSecCode";
            this.txtSecCode.Size = new System.Drawing.Size(119, 21);
            this.txtSecCode.TabIndex = 0;
            this.txtSecCode.Tag = this.cell2;
            this.txtSecCode.YssCaption = "证券内码";
            this.txtSecCode.YssIsMust = true;
            this.txtSecCode.YssLength = 50;
            // 
            // cell56
            // 
            this.cell56.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell56.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell56.InnerControl = null;
            // 
            // cell3
            // 
            this.cell3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell3.InnerControl = null;
            // 
            // cell29
            // 
            this.cell29.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell29.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell29.InnerControl = null;
            this.cell29.Text = "ISIN代码：";
            // 
            // cell30
            // 
            this.cell30.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell30.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell30.InnerControl = this.txtIsinCode;
            // 
            // txtIsinCode
            // 
            this.txtIsinCode.BackColor = System.Drawing.Color.White;
            this.txtIsinCode.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtIsinCode.Location = new System.Drawing.Point(351, 43);
            this.txtIsinCode.Name = "txtIsinCode";
            this.txtIsinCode.Size = new System.Drawing.Size(121, 21);
            this.txtIsinCode.TabIndex = 1;
            this.txtIsinCode.Tag = this.cell30;
            // 
            // row4
            // 
            this.row4.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell4,
            this.cell5,
            this.cell57,
            this.cell6,
            this.cell7,
            this.cell8});
            this.row4.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row4.FullRowSelected = false;
            this.row4.Height = 25;
            this.row4.Text = null;
            // 
            // cell4
            // 
            this.cell4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell4.InnerControl = null;
            this.cell4.Text = "   上市代码：";
            // 
            // cell5
            // 
            this.cell5.ColSpan = 2;
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.txtSecMktCode;
            // 
            // txtSecMktCode
            // 
            this.txtSecMktCode.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.txtSecMktCode.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtSecMktCode.Location = new System.Drawing.Point(110, 68);
            this.txtSecMktCode.Name = "txtSecMktCode";
            this.txtSecMktCode.Size = new System.Drawing.Size(119, 21);
            this.txtSecMktCode.TabIndex = 2;
            this.txtSecMktCode.Tag = this.cell5;
            this.txtSecMktCode.YssCaption = "上市代码";
            this.txtSecMktCode.YssIsMust = true;
            this.txtSecMktCode.YssLength = 30;
            // 
            // cell57
            // 
            this.cell57.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell57.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell57.InnerControl = null;
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.InnerControl = null;
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.InnerControl = null;
            this.cell7.Text = "理财名称：";
            // 
            // cell8
            // 
            this.cell8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell8.InnerControl = this.txtSecName;
            // 
            // txtSecName
            // 
            this.txtSecName.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.txtSecName.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtSecName.Location = new System.Drawing.Point(351, 68);
            this.txtSecName.Name = "txtSecName";
            this.txtSecName.Size = new System.Drawing.Size(121, 21);
            this.txtSecName.TabIndex = 3;
            this.txtSecName.Tag = this.cell8;
            this.txtSecName.YssCaption = "理财名称";
            this.txtSecName.YssIsMust = true;
            this.txtSecName.YssLength = 50;
            // 
            // row5
            // 
            this.row5.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell9,
            this.cell10,
            this.cell58,
            this.cell11,
            this.cell12,
            this.cell13});
            this.row5.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row5.FullRowSelected = false;
            this.row5.Height = 25;
            this.row5.Text = null;
            // 
            // cell9
            // 
            this.cell9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell9.InnerControl = null;
            this.cell9.Text = "   证券品种：";
            // 
            // cell10
            // 
            this.cell10.ColSpan = 2;
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.InnerControl = this.cboSecVar;
            // 
            // cboSecVar
            // 
            this.cboSecVar.AddedSelItemName = "";
            this.cboSecVar.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.cboSecVar.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboSecVar.DisplayName = "C_SEC_VAR_NAME";
            this.cboSecVar.DisplayValue = "C_SEC_VAR_CODE";
            this.cboSecVar.FilterCond = "";
            this.cboSecVar.IsFillDecimal = false;
            this.cboSecVar.KTableTree = true;
            this.cboSecVar.Location = new System.Drawing.Point(110, 93);
            this.cboSecVar.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo6.MethodName = "getDataListByTypes";
            controlMethodInfo6.MethodParams = null;
            controlMethodInfo6.MethodParamValues = new string[] {
        "JJ,LC"};
            controlMethodInfo6.Methods = null;
            this.cboSecVar.MethodInfo = controlMethodInfo6;
            this.cboSecVar.Name = "cboSecVar";
            this.cboSecVar.NodeID = "C_SEC_VAR_CODE";
            this.cboSecVar.Parameter = "C_SEC_VAR_NAME";
            this.cboSecVar.ParaNodeID = "C_DA_CODE_P";
            this.cboSecVar.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.cboSecVar.QueryCond = "JJ,LC";
            this.cboSecVar.QueryType = "CacheType";
            this.cboSecVar.Size = new System.Drawing.Size(119, 21);
            this.cboSecVar.SortColumn = "C_SEC_VAR_NAME";
            this.cboSecVar.TabIndex = 4;
            this.cboSecVar.Tag = this.cell10;
            this.cboSecVar.YssAssociaType = YssInformation.Support.Context.AssociaType.base_seccategory;
            this.cboSecVar.YssCaption = "证券品种";
            this.cboSecVar.YssIsMust = true;
            this.cboSecVar.YssKiloDelimiter = true;
            this.cboSecVar.SelectedValueChanged += new System.EventHandler(this.cboSecVar_SelectedValueChanged);
            // 
            // cell58
            // 
            this.cell58.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell58.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell58.InnerControl = null;
            // 
            // cell11
            // 
            this.cell11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell11.InnerControl = null;
            // 
            // cell12
            // 
            this.cell12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell12.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell12.InnerControl = null;
            this.cell12.Text = "交易市场：";
            // 
            // cell13
            // 
            this.cell13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell13.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell13.InnerControl = this.cboMkt;
            // 
            // cboMkt
            // 
            this.cboMkt.AddedSelItemName = "";
            this.cboMkt.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.cboMkt.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboMkt.DisplayName = "C_MKT_NAME";
            this.cboMkt.DisplayValue = "C_MKT_NO";
            this.cboMkt.FilterCond = "";
            this.cboMkt.IsFillDecimal = false;
            this.cboMkt.KTableTree = true;
            this.cboMkt.Location = new System.Drawing.Point(351, 93);
            this.cboMkt.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo5.MethodName = "getDataList";
            controlMethodInfo5.MethodParams = null;
            controlMethodInfo5.MethodParamValues = null;
            controlMethodInfo5.Methods = null;
            this.cboMkt.MethodInfo = controlMethodInfo5;
            this.cboMkt.Name = "cboMkt";
            this.cboMkt.NodeID = "C_MKT_CODE";
            this.cboMkt.Parameter = "C_MKT_CODE~C_MKT_NAME";
            this.cboMkt.ParaNodeID = "C_PARAENT_CODE";
            this.cboMkt.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.cboMkt.QueryCond = "";
            this.cboMkt.QueryType = "";
            this.cboMkt.Size = new System.Drawing.Size(121, 21);
            this.cboMkt.SortColumn = "C_MKT_NAME";
            this.cboMkt.TabIndex = 5;
            this.cboMkt.Tag = this.cell13;
            this.cboMkt.YssAssociaType = YssInformation.Support.Context.AssociaType.base_exchange;
            this.cboMkt.YssCaption = "交易市场";
            this.cboMkt.YssIsMust = true;
            this.cboMkt.YssKiloDelimiter = true;
            // 
            // row6
            // 
            this.row6.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell14,
            this.cell15,
            this.cell59,
            this.cell16,
            this.cell17,
            this.cell18});
            this.row6.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row6.FullRowSelected = false;
            this.row6.Height = 25;
            this.row6.Text = null;
            // 
            // cell14
            // 
            this.cell14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell14.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell14.InnerControl = null;
            this.cell14.Text = "   交易币种：";
            // 
            // cell15
            // 
            this.cell15.ColSpan = 2;
            this.cell15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell15.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell15.InnerControl = this.cboCury;
            // 
            // cboCury
            // 
            this.cboCury.AddedSelItemName = "";
            this.cboCury.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.cboCury.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboCury.DisplayName = "C_DC_NAME";
            this.cboCury.DisplayValue = "C_DC_CODE";
            this.cboCury.FilterCond = "";
            this.cboCury.IsFillDecimal = false;
            this.cboCury.Location = new System.Drawing.Point(110, 118);
            this.cboCury.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo20.MethodName = "getDataList";
            controlMethodInfo20.MethodParams = null;
            controlMethodInfo20.MethodParamValues = null;
            controlMethodInfo20.Methods = null;
            this.cboCury.MethodInfo = controlMethodInfo20;
            this.cboCury.Name = "cboCury";
            this.cboCury.Parameter = "C_DC_NAME";
            this.cboCury.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.cboCury.QueryCond = "";
            this.cboCury.QueryType = "";
            this.cboCury.Size = new System.Drawing.Size(119, 21);
            this.cboCury.TabIndex = 6;
            this.cboCury.Tag = this.cell15;
            this.cboCury.YssAssociaType = YssInformation.Support.Context.AssociaType.base_currency;
            this.cboCury.YssCaption = "交易币种";
            this.cboCury.YssIsMust = true;
            this.cboCury.YssKiloDelimiter = true;
            // 
            // cell59
            // 
            this.cell59.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell59.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell59.InnerControl = null;
            // 
            // cell16
            // 
            this.cell16.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell16.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell16.InnerControl = null;
            // 
            // cell17
            // 
            this.cell17.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell17.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell17.InnerControl = null;
            this.cell17.Text = "发行面值：";
            // 
            // cell18
            // 
            this.cell18.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell18.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell18.InnerControl = this.txtFvIssue;
            // 
            // txtFvIssue
            // 
            this.txtFvIssue.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.txtFvIssue.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.txtFvIssue.ForeColor = System.Drawing.Color.Blue;
            this.txtFvIssue.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtFvIssue.Location = new System.Drawing.Point(351, 118);
            this.txtFvIssue.Name = "txtFvIssue";
            this.txtFvIssue.Size = new System.Drawing.Size(121, 21);
            this.txtFvIssue.TabIndex = 7;
            this.txtFvIssue.Tag = this.cell18;
            this.txtFvIssue.Text = "1.00";
            this.txtFvIssue.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtFvIssue.YssCaption = "发行面值";
            this.txtFvIssue.YssIsMust = true;
            this.txtFvIssue.YssKiloDelimiter = true;
            this.txtFvIssue.YssLength = 18;
            this.txtFvIssue.YssNumeric = "14, 4";
            // 
            // cell116
            // 
            this.cell116.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell116.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell116.InnerControl = this.panel1;
            // 
            // panel1
            // 
            this.panel1.Location = new System.Drawing.Point(345, 161);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(117, 22);
            this.panel1.TabIndex = 39;
            this.panel1.Tag = this.cell116;
            // 
            // row7
            // 
            this.row7.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell19,
            this.cell20,
            this.cell60,
            this.cell21,
            this.cell22,
            this.cell23});
            this.row7.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row7.FullRowSelected = false;
            this.row7.Height = 25;
            this.row7.Text = null;
            // 
            // cell19
            // 
            this.cell19.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell19.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell19.InnerControl = null;
            this.cell19.Text = "   每手数量：";
            // 
            // cell20
            // 
            this.cell20.ColSpan = 2;
            this.cell20.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell20.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell20.InnerControl = this.txtAmountHD;
            // 
            // txtAmountHD
            // 
            this.txtAmountHD.AutoTooltip = false;
            this.txtAmountHD.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.txtAmountHD.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.txtAmountHD.ForeColor = System.Drawing.Color.Blue;
            this.txtAmountHD.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtAmountHD.Location = new System.Drawing.Point(110, 143);
            this.txtAmountHD.Name = "txtAmountHD";
            this.txtAmountHD.Size = new System.Drawing.Size(119, 21);
            this.txtAmountHD.TabIndex = 8;
            this.txtAmountHD.Tag = this.cell20;
            this.txtAmountHD.Text = "1";
            this.txtAmountHD.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtAmountHD.YssCaption = "每手数量";
            this.txtAmountHD.YssIsMust = true;
            this.txtAmountHD.YssKiloDelimiter = true;
            this.txtAmountHD.YssLength = 18;
            this.txtAmountHD.YssNumeric = "14, 4";
            // 
            // cell60
            // 
            this.cell60.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell60.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell60.InnerControl = null;
            // 
            // cell21
            // 
            this.cell21.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell21.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell21.InnerControl = null;
            // 
            // cell22
            // 
            this.cell22.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell22.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell22.InnerControl = null;
            this.cell22.Text = "报价因子：";
            // 
            // cell23
            // 
            this.cell23.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell23.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell23.InnerControl = this.txtPriceFcr;
            // 
            // txtPriceFcr
            // 
            this.txtPriceFcr.AutoTooltip = false;
            this.txtPriceFcr.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.txtPriceFcr.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.txtPriceFcr.ForeColor = System.Drawing.Color.Blue;
            this.txtPriceFcr.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtPriceFcr.Location = new System.Drawing.Point(351, 143);
            this.txtPriceFcr.Name = "txtPriceFcr";
            this.txtPriceFcr.Size = new System.Drawing.Size(121, 21);
            this.txtPriceFcr.TabIndex = 9;
            this.txtPriceFcr.Tag = this.cell23;
            this.txtPriceFcr.Text = "1";
            this.txtPriceFcr.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtPriceFcr.YssCaption = "报价因子";
            this.txtPriceFcr.YssIsMust = true;
            this.txtPriceFcr.YssKiloDelimiter = true;
            this.txtPriceFcr.YssLength = 18;
            this.txtPriceFcr.YssNumeric = "14, 4";
            // 
            // dtpToList
            // 
            this.dtpToList.BackColor = System.Drawing.Color.Transparent;
            this.dtpToList.DateBeginChecked = true;
            this.dtpToList.DateEndChecked = true;
            this.dtpToList.Location = new System.Drawing.Point(107, 92);
            this.dtpToList.Margin = new System.Windows.Forms.Padding(0);
            this.dtpToList.Name = "dtpToList";
            this.dtpToList.Size = new System.Drawing.Size(119, 21);
            this.dtpToList.TabIndex = 24;
            this.dtpToList.Tag = this.cell97;
            this.dtpToList.yssEnabled = true;
            this.dtpToList.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpToList.yssInterval = false;
            this.dtpToList.yssLabelStr = "至";
            this.dtpToList.yssShowOperLable = false;
            this.dtpToList.YssShowSecond = true;
            this.dtpToList.yssTimeControl = false;
            // 
            // cell97
            // 
            this.cell97.ColSpan = 2;
            this.cell97.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell97.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell97.InnerControl = this.dtpToList;
            // 
            // dtpOffList
            // 
            this.dtpOffList.BackColor = System.Drawing.Color.Transparent;
            this.dtpOffList.DateBeginChecked = true;
            this.dtpOffList.DateEndChecked = true;
            this.dtpOffList.Location = new System.Drawing.Point(348, 92);
            this.dtpOffList.Margin = new System.Windows.Forms.Padding(0);
            this.dtpOffList.Name = "dtpOffList";
            this.dtpOffList.Size = new System.Drawing.Size(121, 21);
            this.dtpOffList.TabIndex = 25;
            this.dtpOffList.Tag = this.cell107;
            this.dtpOffList.yssEnabled = true;
            this.dtpOffList.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpOffList.yssInterval = false;
            this.dtpOffList.yssLabelStr = "至";
            this.dtpOffList.yssShowOperLable = false;
            this.dtpOffList.YssShowSecond = true;
            this.dtpOffList.yssTimeControl = false;
            // 
            // cell107
            // 
            this.cell107.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell107.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell107.InnerControl = this.dtpOffList;
            // 
            // row11
            // 
            this.row11.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell31,
            this.cell32,
            this.cell62,
            this.cell33,
            this.cell34,
            this.cell35});
            this.row11.Font = new System.Drawing.Font("新宋体", 9.75F, System.Drawing.FontStyle.Bold);
            this.row11.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row11.FullRowSelected = false;
            this.row11.GroupLineLength = 310;
            this.row11.Height = 25;
            this.row11.Text = null;
            // 
            // cell31
            // 
            this.cell31.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell31.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell31.InnerControl = null;
            this.cell31.Text = "   计息方式：";
            // 
            // cell32
            // 
            this.cell32.ColSpan = 2;
            this.cell32.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell32.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell32.InnerControl = this.cboAIMode;
            // 
            // cboAIMode
            // 
            this.cboAIMode.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboAIMode.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboAIMode.DisplayName = "C_DV_NAME";
            this.cboAIMode.DisplayValue = "C_DV_CODE";
            this.cboAIMode.FilterCond = "";
            this.cboAIMode.IsFillDecimal = false;
            this.cboAIMode.Location = new System.Drawing.Point(110, 193);
            this.cboAIMode.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo4.MethodName = "getDataListByTypes";
            controlMethodInfo4.MethodParams = null;
            controlMethodInfo4.MethodParamValues = new string[] {
        "AI_MOD_TR,"};
            controlMethodInfo4.Methods = null;
            this.cboAIMode.MethodInfo = controlMethodInfo4;
            this.cboAIMode.Name = "cboAIMode";
            this.cboAIMode.Parameter = "C_DV_NAME";
            this.cboAIMode.PrefixBackColor = System.Drawing.Color.White;
            this.cboAIMode.QueryCond = "AI_MOD_TR";
            this.cboAIMode.QueryType = "CacheType";
            this.cboAIMode.Size = new System.Drawing.Size(119, 21);
            this.cboAIMode.TabIndex = 12;
            this.cboAIMode.Tag = this.cell32;
            this.cboAIMode.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboAIMode.YssCaption = "计息方式";
            // 
            // cell62
            // 
            this.cell62.Font = new System.Drawing.Font("新宋体", 9.75F, System.Drawing.FontStyle.Bold);
            this.cell62.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.cell62.InnerControl = null;
            // 
            // cell33
            // 
            this.cell33.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell33.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell33.InnerControl = null;
            // 
            // cell34
            // 
            this.cell34.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell34.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell34.InnerControl = null;
            this.cell34.Text = "机构名称：";
            // 
            // cell35
            // 
            this.cell35.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell35.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell35.InnerControl = this.cboOrgan;
            // 
            // cboOrgan
            // 
            this.cboOrgan.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboOrgan.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboOrgan.DisplayName = "C_ORG_NAME";
            this.cboOrgan.DisplayValue = "C_ORG_CODE";
            this.cboOrgan.ExpandButtonToolTip = "数据调整……";
            this.cboOrgan.FilterCond = "";
            this.cboOrgan.IsFillDecimal = false;
            this.cboOrgan.KTableTree = true;
            this.cboOrgan.Location = new System.Drawing.Point(351, 193);
            this.cboOrgan.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo2.MethodName = "getDataList";
            controlMethodInfo2.MethodParams = null;
            controlMethodInfo2.MethodParamValues = null;
            controlMethodInfo2.Methods = null;
            this.cboOrgan.MethodInfo = controlMethodInfo2;
            this.cboOrgan.Name = "cboOrgan";
            this.cboOrgan.NodeID = "C_ORG_CODE";
            this.cboOrgan.Parameter = "C_ORG_CODE~C_ORG_NAME";
            this.cboOrgan.ParaNodeID = "C_ORG_CODE_P";
            this.cboOrgan.PrefixBackColor = System.Drawing.Color.White;
            this.cboOrgan.QueryCond = "";
            this.cboOrgan.QueryType = "";
            this.cboOrgan.Size = new System.Drawing.Size(121, 21);
            this.cboOrgan.SortColumn = "C_ORG_NAME";
            this.cboOrgan.TabIndex = 13;
            this.cboOrgan.Tag = this.cell35;
            this.cboOrgan.YssAssociaType = YssInformation.Support.Context.AssociaType.base_organ;
            this.cboOrgan.YssCaption = "所属机构";
            this.cboOrgan.YssDataSrc = FAST.Core.Context.ClsEnums.DataSrc.SrcDB;
            this.cboOrgan.YssKiloDelimiter = true;
            // 
            // row12
            // 
            this.row12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row12.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row12.FullRowSelected = false;
            this.row12.Height = 25;
            this.row12.IsGroup = true;
            this.row12.Text = "计息信息";
            // 
            // txtCouponRate
            // 
            // 
            // 
            // 
            this.txtCouponRate.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.txtCouponRate.Location = new System.Drawing.Point(107, 0);
            this.txtCouponRate.Name = "txtCouponRate";
            this.txtCouponRate.PrefixForeColor = System.Drawing.Color.Blue;
            this.txtCouponRate.Size = new System.Drawing.Size(119, 21);
            this.txtCouponRate.Sufix = "%";
            this.txtCouponRate.SufixForeColor = System.Drawing.Color.Blue;
            this.txtCouponRate.TabIndex = 17;
            this.txtCouponRate.Tag = this.cell81;
            this.txtCouponRate.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtCouponRate.YssCaption = "票面利率";
            this.txtCouponRate.YssIsMust = true;
            this.txtCouponRate.YssKiloDelimiter = true;
            this.txtCouponRate.YssLength = 15;
            this.txtCouponRate.YssNumeric = "7, 8";
            // 
            // cell81
            // 
            this.cell81.ColSpan = 2;
            this.cell81.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell81.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell81.InnerControl = this.txtCouponRate;
            // 
            // txtTaxRate
            // 
            // 
            // 
            // 
            this.txtTaxRate.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.txtTaxRate.Location = new System.Drawing.Point(348, 0);
            this.txtTaxRate.Name = "txtTaxRate";
            this.txtTaxRate.PrefixForeColor = System.Drawing.Color.Blue;
            this.txtTaxRate.Size = new System.Drawing.Size(121, 21);
            this.txtTaxRate.Sufix = "%";
            this.txtTaxRate.SufixForeColor = System.Drawing.Color.Blue;
            this.txtTaxRate.TabIndex = 18;
            this.txtTaxRate.Tag = this.cell101;
            this.txtTaxRate.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtTaxRate.YssCaption = "税率";
            this.txtTaxRate.YssIsMust = true;
            this.txtTaxRate.YssKiloDelimiter = true;
            this.txtTaxRate.YssLength = 15;
            this.txtTaxRate.YssNumeric = "7, 8";
            // 
            // cell101
            // 
            this.cell101.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell101.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell101.InnerControl = this.txtTaxRate;
            // 
            // cboPayFrequency
            // 
            this.cboPayFrequency.AddedSelItemName = "";
            this.cboPayFrequency.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.cboPayFrequency.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboPayFrequency.DisplayName = "C_DV_NAME";
            this.cboPayFrequency.DisplayValue = "C_DV_CODE";
            this.cboPayFrequency.FilterCond = "";
            this.cboPayFrequency.Location = new System.Drawing.Point(107, 23);
            this.cboPayFrequency.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo8.MethodName = "getDataListByTypes";
            controlMethodInfo8.MethodParams = null;
            controlMethodInfo8.MethodParamValues = new string[] {
        "PI_FQCY_FI,"};
            controlMethodInfo8.Methods = null;
            this.cboPayFrequency.MethodInfo = controlMethodInfo8;
            this.cboPayFrequency.Name = "cboPayFrequency";
            this.cboPayFrequency.Parameter = "C_DV_NAME";
            this.cboPayFrequency.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.cboPayFrequency.QueryCond = "PI_FQCY_FI";
            this.cboPayFrequency.QueryType = "CacheType";
            this.cboPayFrequency.Size = new System.Drawing.Size(119, 21);
            this.cboPayFrequency.TabIndex = 19;
            this.cboPayFrequency.Tag = this.cell85;
            this.cboPayFrequency.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboPayFrequency.YssCaption = "付息频率";
            this.cboPayFrequency.YssIsMust = true;
            this.cboPayFrequency.YssKiloDelimiter = true;
            // 
            // cell85
            // 
            this.cell85.ColSpan = 2;
            this.cell85.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell85.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell85.InnerControl = this.cboPayFrequency;
            // 
            // cboPayFormula
            // 
            this.cboPayFormula.AddedSelItemName = "";
            this.cboPayFormula.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.cboPayFormula.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboPayFormula.DisplayName = "C_DV_NAME";
            this.cboPayFormula.DisplayValue = "C_DV_CODE";
            this.cboPayFormula.FilterCond = "";
            this.cboPayFormula.Location = new System.Drawing.Point(348, 23);
            this.cboPayFormula.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo15.MethodName = "getDataListByTypes";
            controlMethodInfo15.MethodParams = null;
            controlMethodInfo15.MethodParamValues = new string[] {
        "AI_FOA_FI,"};
            controlMethodInfo15.Methods = null;
            this.cboPayFormula.MethodInfo = controlMethodInfo15;
            this.cboPayFormula.Name = "cboPayFormula";
            this.cboPayFormula.Parameter = "C_DV_NAME";
            this.cboPayFormula.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.cboPayFormula.QueryCond = "AI_FOA_FI";
            this.cboPayFormula.QueryType = "CacheType";
            this.cboPayFormula.Size = new System.Drawing.Size(121, 21);
            this.cboPayFormula.TabIndex = 20;
            this.cboPayFormula.Tag = this.cell103;
            this.cboPayFormula.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboPayFormula.YssCaption = "计息公式";
            this.cboPayFormula.YssIsMust = true;
            this.cboPayFormula.YssKiloDelimiter = true;
            this.cboPayFormula.SelectedValueChanged += new System.EventHandler(this.cboPayFormula_SelectedValueChanged);
            // 
            // cell103
            // 
            this.cell103.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell103.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell103.InnerControl = this.cboPayFormula;
            // 
            // dtpCountBeginDate
            // 
            this.dtpCountBeginDate.BackColor = System.Drawing.Color.Transparent;
            this.dtpCountBeginDate.DateBeginChecked = true;
            this.dtpCountBeginDate.DateEndChecked = true;
            this.dtpCountBeginDate.Location = new System.Drawing.Point(107, 46);
            this.dtpCountBeginDate.Margin = new System.Windows.Forms.Padding(0);
            this.dtpCountBeginDate.Name = "dtpCountBeginDate";
            this.dtpCountBeginDate.Size = new System.Drawing.Size(119, 21);
            this.dtpCountBeginDate.TabIndex = 21;
            this.dtpCountBeginDate.Tag = this.cell89;
            this.dtpCountBeginDate.yssEnabled = true;
            this.dtpCountBeginDate.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpCountBeginDate.yssInterval = false;
            this.dtpCountBeginDate.yssLabelStr = "至";
            this.dtpCountBeginDate.yssShowOperLable = false;
            this.dtpCountBeginDate.YssShowSecond = true;
            this.dtpCountBeginDate.yssTimeControl = false;
            // 
            // cell89
            // 
            this.cell89.ColSpan = 2;
            this.cell89.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell89.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell89.InnerControl = this.dtpCountBeginDate;
            // 
            // improvedTextBox3
            // 
            // 
            // 
            // 
            this.improvedTextBox3.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.improvedTextBox3.Location = new System.Drawing.Point(107, 46);
            this.improvedTextBox3.Name = "improvedTextBox3";
            this.improvedTextBox3.PrefixForeColor = System.Drawing.Color.Blue;
            this.improvedTextBox3.Size = new System.Drawing.Size(119, 21);
            this.improvedTextBox3.Sufix = "%";
            this.improvedTextBox3.SufixForeColor = System.Drawing.Color.Blue;
            this.improvedTextBox3.TabIndex = 27;
            this.improvedTextBox3.Tag = this.cell89;
            this.improvedTextBox3.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.improvedTextBox3.YssCaption = "票面利率";
            this.improvedTextBox3.YssIsMust = true;
            this.improvedTextBox3.YssKiloDelimiter = true;
            this.improvedTextBox3.YssLength = 15;
            this.improvedTextBox3.YssNumeric = "7, 8";
            // 
            // dtpCountEndDate
            // 
            this.dtpCountEndDate.BackColor = System.Drawing.Color.Transparent;
            this.dtpCountEndDate.DateBeginChecked = true;
            this.dtpCountEndDate.DateEndChecked = true;
            this.dtpCountEndDate.Location = new System.Drawing.Point(348, 46);
            this.dtpCountEndDate.Margin = new System.Windows.Forms.Padding(0);
            this.dtpCountEndDate.Name = "dtpCountEndDate";
            this.dtpCountEndDate.Size = new System.Drawing.Size(121, 21);
            this.dtpCountEndDate.TabIndex = 22;
            this.dtpCountEndDate.Tag = this.cell105;
            this.dtpCountEndDate.yssEnabled = true;
            this.dtpCountEndDate.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpCountEndDate.yssInterval = false;
            this.dtpCountEndDate.yssLabelStr = "至";
            this.dtpCountEndDate.yssShowOperLable = false;
            this.dtpCountEndDate.YssShowSecond = true;
            this.dtpCountEndDate.yssTimeControl = false;
            // 
            // cell105
            // 
            this.cell105.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell105.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell105.InnerControl = this.dtpCountEndDate;
            // 
            // row16
            // 
            this.row16.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell51,
            this.cell52,
            this.cell61,
            this.cell53,
            this.cell54,
            this.cell55});
            this.row16.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row16.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row16.FullRowSelected = false;
            this.row16.Height = 25;
            this.row16.Text = null;
            // 
            // cell51
            // 
            this.cell51.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell51.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell51.InnerControl = null;
            this.cell51.Text = "   收益类型：";
            // 
            // cell52
            // 
            this.cell52.ColSpan = 2;
            this.cell52.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell52.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell52.InnerControl = this.cboIncomeType;
            // 
            // cboIncomeType
            // 
            this.cboIncomeType.AddedSelItemName = "";
            this.cboIncomeType.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.cboIncomeType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboIncomeType.DisplayName = "C_DV_NAME";
            this.cboIncomeType.DisplayValue = "C_DV_CODE";
            this.cboIncomeType.FilterCond = "";
            this.cboIncomeType.Location = new System.Drawing.Point(110, 168);
            this.cboIncomeType.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo3.MethodName = "getDataListByTypes";
            controlMethodInfo3.MethodParams = null;
            controlMethodInfo3.MethodParamValues = new string[] {
        "INCOME_TYPE_LC,"};
            controlMethodInfo3.Methods = null;
            this.cboIncomeType.MethodInfo = controlMethodInfo3;
            this.cboIncomeType.Name = "cboIncomeType";
            this.cboIncomeType.Parameter = "C_DV_NAME";
            this.cboIncomeType.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.cboIncomeType.QueryCond = "INCOME_TYPE_LC";
            this.cboIncomeType.QueryType = "CacheType";
            this.cboIncomeType.Size = new System.Drawing.Size(119, 21);
            this.cboIncomeType.TabIndex = 10;
            this.cboIncomeType.Tag = this.cell52;
            this.cboIncomeType.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboIncomeType.YssCaption = "收益类型";
            this.cboIncomeType.YssIsMust = true;
            this.cboIncomeType.YssKiloDelimiter = true;
            this.cboIncomeType.SelectedValueChanged += new System.EventHandler(this.cboIncomeType_SelectedValueChanged);
            // 
            // cell61
            // 
            this.cell61.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell61.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell61.InnerControl = null;
            // 
            // cell53
            // 
            this.cell53.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell53.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell53.InnerControl = null;
            // 
            // cell54
            // 
            this.cell54.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell54.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell54.InnerControl = null;
            this.cell54.Text = "投资方式：";
            // 
            // cell55
            // 
            this.cell55.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell55.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell55.InnerControl = this.cboInvestment;
            // 
            // cboInvestment
            // 
            this.cboInvestment.AddedSelItemName = "";
            this.cboInvestment.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.cboInvestment.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboInvestment.DisplayName = "C_DV_NAME";
            this.cboInvestment.DisplayValue = "C_DV_CODE";
            this.cboInvestment.FilterCond = "";
            this.cboInvestment.Location = new System.Drawing.Point(351, 168);
            this.cboInvestment.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo14.MethodName = "getDataListByTypes";
            controlMethodInfo14.MethodParams = null;
            controlMethodInfo14.MethodParamValues = new string[] {
        "INV,"};
            controlMethodInfo14.Methods = null;
            this.cboInvestment.MethodInfo = controlMethodInfo14;
            this.cboInvestment.Name = "cboInvestment";
            this.cboInvestment.Parameter = "C_DV_NAME";
            this.cboInvestment.PrefixBackColor = System.Drawing.Color.LightYellow;
            this.cboInvestment.QueryCond = "";
            this.cboInvestment.QueryType = "CacheType";
            this.cboInvestment.RequestEveryTime = true;
            this.cboInvestment.Size = new System.Drawing.Size(121, 21);
            this.cboInvestment.TabIndex = 11;
            this.cboInvestment.Tag = this.cell55;
            this.cboInvestment.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboInvestment.YssCaption = "投资方式";
            this.cboInvestment.YssKiloDelimiter = true;
            // 
            // column6
            // 
            this.column6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column6.Width = 60;
            // 
            // row17
            // 
            this.row17.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell67,
            this.cell68,
            this.cell69,
            this.cell70,
            this.cell71,
            this.cell72});
            this.row17.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row17.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row17.FullRowSelected = false;
            this.row17.Height = 25;
            this.row17.Text = null;
            // 
            // cell67
            // 
            this.cell67.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell67.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell67.InnerControl = null;
            this.cell67.Text = "   货基分投：";
            // 
            // cell68
            // 
            this.cell68.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell68.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell68.InnerControl = this.cboFHZT;
            // 
            // cboFHZT
            // 
            this.cboFHZT.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboFHZT.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboFHZT.FilterCond = "";
            this.cboFHZT.Location = new System.Drawing.Point(110, 218);
            this.cboFHZT.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataListByKeys";
            controlMethodInfo1.MethodParams = null;
            controlMethodInfo1.MethodParamValues = new string[] {
        "CF_DAY,CF_MONTH,CF_SEASON"};
            controlMethodInfo1.Methods = null;
            this.cboFHZT.MethodInfo = controlMethodInfo1;
            this.cboFHZT.Name = "cboFHZT";
            this.cboFHZT.PrefixBackColor = System.Drawing.Color.White;
            this.cboFHZT.QueryCond = "";
            this.cboFHZT.QueryType = "";
            this.cboFHZT.Size = new System.Drawing.Size(59, 21);
            this.cboFHZT.TabIndex = 14;
            this.cboFHZT.Tag = this.cell68;
            this.cboFHZT.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboFHZT.YssCaption = "货基分投";
            this.cboFHZT.SelectedValueChanged += new System.EventHandler(this.cboFHZT_SelectedValueChanged);
            // 
            // cell69
            // 
            this.cell69.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell69.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell69.InnerControl = this.iniDate;
            // 
            // iniDate
            // 
            // 
            // 
            // 
            this.iniDate.Border.BorderColor = System.Drawing.Color.Silver;
            this.iniDate.Location = new System.Drawing.Point(170, 218);
            this.iniDate.Name = "iniDate";
            this.iniDate.Prefix = "+";
            this.iniDate.Size = new System.Drawing.Size(59, 21);
            this.iniDate.TabIndex = 15;
            this.iniDate.Tag = this.cell69;
            this.iniDate.Value = 0;
            // 
            // cell70
            // 
            this.cell70.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell70.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell70.InnerControl = null;
            // 
            // cell71
            // 
            this.cell71.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell71.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell71.InnerControl = null;
            this.cell71.Text = "日期类型：";
            // 
            // cell72
            // 
            this.cell72.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell72.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell72.InnerControl = this.cboDateType;
            // 
            // cboDateType
            // 
            this.cboDateType.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboDateType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboDateType.FilterCond = "";
            this.cboDateType.Location = new System.Drawing.Point(351, 218);
            this.cboDateType.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo21.MethodName = "getDataListByTypes";
            controlMethodInfo21.MethodParams = null;
            controlMethodInfo21.MethodParamValues = new string[] {
        "DATE_TYPE,"};
            controlMethodInfo21.Methods = null;
            this.cboDateType.MethodInfo = controlMethodInfo21;
            this.cboDateType.Name = "cboDateType";
            this.cboDateType.PrefixBackColor = System.Drawing.Color.White;
            this.cboDateType.QueryCond = "";
            this.cboDateType.QueryType = "";
            this.cboDateType.Size = new System.Drawing.Size(121, 21);
            this.cboDateType.TabIndex = 16;
            this.cboDateType.Tag = this.cell72;
            this.cboDateType.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboDateType.YssCaption = "日期类型";
            // 
            // cboBackward
            // 
            this.cboBackward.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboBackward.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboBackward.FilterCond = "";
            this.cboBackward.Location = new System.Drawing.Point(107, 69);
            this.cboBackward.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo9.MethodName = "getDataListByKeys";
            controlMethodInfo9.MethodParams = null;
            controlMethodInfo9.MethodParamValues = new string[] {
        "MRGC,DQRGC,JXRGC,MRYEGC"};
            controlMethodInfo9.Methods = null;
            this.cboBackward.MethodInfo = controlMethodInfo9;
            this.cboBackward.Name = "cboBackward";
            this.cboBackward.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.cboBackward.QueryCond = "";
            this.cboBackward.QueryType = "";
            this.cboBackward.Size = new System.Drawing.Size(119, 21);
            this.cboBackward.TabIndex = 23;
            this.cboBackward.Tag = this.cell93;
            this.cboBackward.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboBackward.YssCaption = "倒置算法";
            this.cboBackward.YssIsMust = true;
            // 
            // cell93
            // 
            this.cell93.ColSpan = 2;
            this.cell93.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell93.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell93.InnerControl = this.cboBackward;
            // 
            // row19
            // 
            this.row19.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell76,
            this.cell77,
            this.cell78,
            this.cell79,
            this.cell123,
            this.cell124});
            this.row19.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row19.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row19.FullRowSelected = false;
            this.row19.Text = null;
            // 
            // cell76
            // 
            this.cell76.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell76.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell76.InnerControl = null;
            this.cell76.Text = "   ETF类型：";
            // 
            // cell77
            // 
            this.cell77.ColSpan = 2;
            this.cell77.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell77.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell77.InnerControl = this.cboETFType;
            // 
            // cboETFType
            // 
            this.cboETFType.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboETFType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboETFType.FilterCond = "";
            this.cboETFType.Location = new System.Drawing.Point(110, 243);
            this.cboETFType.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo11.MethodName = "getDataListByTypes";
            controlMethodInfo11.MethodParams = null;
            controlMethodInfo11.MethodParamValues = new string[] {
        "ETF_TYPE,"};
            controlMethodInfo11.Methods = new string[] {
        "getKeyConvertMap",
        "getDataByCode",
        "getDataList",
        "getDataListRes",
        "getDataListByTypes",
        "getQueryResByTypes",
        "getDataListByKeys",
        "getQueryResByKeys"};
            this.cboETFType.MethodInfo = controlMethodInfo11;
            this.cboETFType.Name = "cboETFType";
            this.cboETFType.PrefixBackColor = System.Drawing.Color.White;
            this.cboETFType.QueryCond = "";
            this.cboETFType.QueryType = "";
            this.cboETFType.Size = new System.Drawing.Size(119, 21);
            this.cboETFType.TabIndex = 26;
            this.cboETFType.Tag = this.cell77;
            this.cboETFType.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboETFType.YssCaption = "ETF类型";
            // 
            // cell78
            // 
            this.cell78.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell78.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell78.InnerControl = null;
            // 
            // cell79
            // 
            this.cell79.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell79.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell79.InnerControl = null;
            // 
            // cell123
            // 
            this.cell123.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell123.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell123.InnerControl = null;
            this.cell123.Text = "利率年化天数";
            // 
            // cell124
            // 
            this.cell124.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell124.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell124.InnerControl = this.iniRateYearDays;
            // 
            // iniRateYearDays
            // 
            // 
            // 
            // 
            this.iniRateYearDays.Border.BorderColor = System.Drawing.Color.Silver;
            this.iniRateYearDays.Location = new System.Drawing.Point(351, 243);
            this.iniRateYearDays.Name = "iniRateYearDays";
            this.iniRateYearDays.Prefix = "";
            this.iniRateYearDays.Size = new System.Drawing.Size(121, 21);
            this.iniRateYearDays.TabIndex = 15;
            this.iniRateYearDays.Tag = this.cell124;
            this.iniRateYearDays.Value = 0;
            // 
            // cell118
            // 
            this.cell118.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell118.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell118.InnerControl = this.yssDateTimeFX;
            // 
            // yssDateTimeFX
            // 
            this.yssDateTimeFX.BackColor = System.Drawing.Color.Transparent;
            this.yssDateTimeFX.DateBeginChecked = true;
            this.yssDateTimeFX.DateEndChecked = true;
            this.yssDateTimeFX.Location = new System.Drawing.Point(0, -21);
            this.yssDateTimeFX.Margin = new System.Windows.Forms.Padding(0);
            this.yssDateTimeFX.Name = "yssDateTimeFX";
            this.yssDateTimeFX.Size = new System.Drawing.Size(117, 21);
            this.yssDateTimeFX.TabIndex = 31;
            this.yssDateTimeFX.Tag = this.cell118;
            this.yssDateTimeFX.yssEnabled = true;
            this.yssDateTimeFX.yssFormatDateStr = "yyyy-MM-dd";
            this.yssDateTimeFX.yssInterval = false;
            this.yssDateTimeFX.yssLabelStr = "至";
            this.yssDateTimeFX.yssShowOperLable = false;
            this.yssDateTimeFX.YssShowSecond = true;
            this.yssDateTimeFX.yssTimeControl = false;
            // 
            // tabControl1
            // 
            this.tabControl1.BackColor = System.Drawing.Color.WhiteSmoke;
            this.tabControl1.Controls.Add(this.tabPaBZZC);
            this.tabControl1.Controls.Add(this.tabPaFBZC);
            this.tabControl1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tabControl1.Location = new System.Drawing.Point(0, 361);
            this.tabControl1.Name = "tabControl1";
            this.tabControl1.SelectedTab = this.tabPaBZZC;
            this.tabControl1.ShowTabAddButton = false;
            this.tabControl1.ShowTabDropDownButton = false;
            this.tabControl1.ShowTabHistoryButton = false;
            this.tabControl1.Size = new System.Drawing.Size(489, 208);
            this.tabControl1.TabBorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(194)))), ((int)(((byte)(217)))), ((int)(((byte)(247)))));
            this.tabControl1.TabBorderColorSelected = System.Drawing.Color.FromArgb(((int)(((byte)(194)))), ((int)(((byte)(217)))), ((int)(((byte)(247)))));
            this.tabControl1.TabCloseButtonVisible = false;
            this.tabControl1.TabCloseImage = null;
            this.tabControl1.TabCloseImageHot = null;
            this.tabControl1.TabIndex = 27;
            this.tabControl1.TabPages.AddRange(new Yss.Controls.TabPage[] {
            this.tabPaBZZC,
            this.tabPaFBZC});
            this.tabControl1.TabSideBarBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(251)))), ((int)(((byte)(222)))), ((int)(((byte)(129)))));
            this.tabControl1.Text = "tabControlBZ";
            this.tabControl1.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // tabPaFBZC
            // 
            this.tabPaFBZC.Controls.Add(this.tableFBZC1);
            this.tabPaFBZC.Name = "tabPaFBZC";
            this.tabPaFBZC.TabCloseButtonVisible = false;
            this.tabPaFBZC.Text = "非标资产";
            // 
            // tableFBZC1
            // 
            this.tableFBZC1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(243)))), ((int)(((byte)(245)))), ((int)(((byte)(249)))));
            // 
            // 
            // 
            this.tableFBZC1.Border.Bottom = false;
            this.tableFBZC1.Border.Left = false;
            this.tableFBZC1.Border.Right = false;
            this.tableFBZC1.Border.Top = false;
            this.tableFBZC1.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.column13,
            this.column14,
            this.column16,
            this.column17,
            this.column18});
            this.tableFBZC1.Controls.Add(this.yssSelCombox_jtjz4nostd);
            this.tableFBZC1.Controls.Add(this.TextBoxLLSX);
            this.tableFBZC1.Controls.Add(this.panel1);
            this.tableFBZC1.Controls.Add(this.improvedTextBoxLC);
            this.tableFBZC1.Controls.Add(this.yssTextBoxYJLX);
            this.tableFBZC1.Controls.Add(this.improvedTextBoxSLV);
            this.tableFBZC1.Controls.Add(this.TextBoxJXLLV);
            this.tableFBZC1.Controls.Add(this.TextBoxXX);
            this.tableFBZC1.Controls.Add(this.TextBoxJZLIL);
            this.tableFBZC1.Controls.Add(this.panel2);
            this.tableFBZC1.Controls.Add(this.yssSelJXGS);
            this.tableFBZC1.Controls.Add(this.TextBoxBLXS);
            this.tableFBZC1.Controls.Add(this.yssSelLLLX);
            this.tableFBZC1.Controls.Add(this.yssSelLLDM);
            this.tableFBZC1.Controls.Add(this.yssDateTimeFX);
            this.tableFBZC1.Controls.Add(this.yssDateTimeDQ);
            this.tableFBZC1.Controls.Add(this.yssSelDZ);
            this.tableFBZC1.Controls.Add(this.yssDateTimeQX);
            this.tableFBZC1.Controls.Add(this.yssDateTimeJX);
            this.tableFBZC1.DefaultToolStripItems = ((Yss.KTable.Enums.SysToolStripItems)((((((((((((Yss.KTable.Enums.SysToolStripItems.ShowHideColumn | Yss.KTable.Enums.SysToolStripItems.GroupByColumn)
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
            this.tableFBZC1.IsShowScrollbar = false;
            this.tableFBZC1.Location = new System.Drawing.Point(1, 1);
            this.tableFBZC1.Name = "tableFBZC1";
            this.tableFBZC1.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row8,
            this.row9,
            this.row10,
            this.row13,
            this.row35,
            this.row36,
            this.row14,
            this.row15,
            this.row18,
            this.row38});
            this.tableFBZC1.ShowColumnHeader = false;
            this.tableFBZC1.Size = new System.Drawing.Size(471, 183);
            this.tableFBZC1.TabIndex = 0;
            this.tableFBZC1.Text = "table1";
            // 
            // column13
            // 
            this.column13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column13.Width = 107;
            // 
            // column14
            // 
            this.column14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column14.Width = 118;
            // 
            // column16
            // 
            this.column16.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column16.Width = 30;
            // 
            // column17
            // 
            this.column17.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column17.Width = 90;
            // 
            // column18
            // 
            this.column18.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column18.Width = 118;
            // 
            // yssSelCombox_jtjz4nostd
            // 
            this.yssSelCombox_jtjz4nostd.AddedSelItemName = "";
            // 
            // 
            // 
            this.yssSelCombox_jtjz4nostd.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.yssSelCombox_jtjz4nostd.FilterCond = "";
            this.yssSelCombox_jtjz4nostd.Location = new System.Drawing.Point(0, -21);
            this.yssSelCombox_jtjz4nostd.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo19.MethodName = "getDataListByKeys";
            controlMethodInfo19.MethodParams = null;
            controlMethodInfo19.MethodParamValues = new string[] {
        "LC_SL,LC_JE"};
            controlMethodInfo19.Methods = null;
            this.yssSelCombox_jtjz4nostd.MethodInfo = controlMethodInfo19;
            this.yssSelCombox_jtjz4nostd.Name = "yssSelCombox_jtjz4nostd";
            this.yssSelCombox_jtjz4nostd.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.yssSelCombox_jtjz4nostd.QueryCond = "";
            this.yssSelCombox_jtjz4nostd.QueryType = "";
            this.yssSelCombox_jtjz4nostd.Size = new System.Drawing.Size(117, 21);
            this.yssSelCombox_jtjz4nostd.TabIndex = 41;
            this.yssSelCombox_jtjz4nostd.Tag = this.cell130;
            this.yssSelCombox_jtjz4nostd.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.yssSelCombox_jtjz4nostd.YssIsMust = true;
            // 
            // cell130
            // 
            this.cell130.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell130.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell130.InnerControl = this.yssSelCombox_jtjz4nostd;
            // 
            // TextBoxLLSX
            // 
            // 
            // 
            // 
            this.TextBoxLLSX.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.TextBoxLLSX.Location = new System.Drawing.Point(107, 69);
            this.TextBoxLLSX.Name = "TextBoxLLSX";
            this.TextBoxLLSX.PrefixForeColor = System.Drawing.Color.Blue;
            this.TextBoxLLSX.Size = new System.Drawing.Size(117, 21);
            this.TextBoxLLSX.Sufix = "%";
            this.TextBoxLLSX.SufixForeColor = System.Drawing.Color.Blue;
            this.TextBoxLLSX.TabIndex = 40;
            this.TextBoxLLSX.Tag = this.cell50;
            this.TextBoxLLSX.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.TextBoxLLSX.YssCaption = "利率上限";
            this.TextBoxLLSX.YssKiloDelimiter = true;
            this.TextBoxLLSX.YssLength = 15;
            this.TextBoxLLSX.YssNumeric = "7, 8";
            // 
            // cell50
            // 
            this.cell50.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell50.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell50.InnerControl = this.TextBoxLLSX;
            // 
            // improvedTextBoxLC
            // 
            // 
            // 
            // 
            this.improvedTextBoxLC.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.improvedTextBoxLC.Location = new System.Drawing.Point(345, 115);
            this.improvedTextBoxLC.Name = "improvedTextBoxLC";
            this.improvedTextBoxLC.PrefixForeColor = System.Drawing.Color.Blue;
            this.improvedTextBoxLC.Size = new System.Drawing.Size(117, 21);
            this.improvedTextBoxLC.Sufix = "Bp";
            this.improvedTextBoxLC.SufixForeColor = System.Drawing.Color.Blue;
            this.improvedTextBoxLC.TabIndex = 38;
            this.improvedTextBoxLC.Tag = this.cell170;
            this.improvedTextBoxLC.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.improvedTextBoxLC.YssCaption = "利差";
            this.improvedTextBoxLC.YssIsMust = true;
            this.improvedTextBoxLC.YssKiloDelimiter = true;
            this.improvedTextBoxLC.YssLength = 15;
            this.improvedTextBoxLC.YssNumeric = "7, 8";
            // 
            // cell170
            // 
            this.cell170.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell170.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell170.InnerControl = this.improvedTextBoxLC;
            // 
            // yssTextBoxYJLX
            // 
            this.yssTextBoxYJLX.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.yssTextBoxYJLX.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.yssTextBoxYJLX.ForeColor = System.Drawing.Color.Blue;
            this.yssTextBoxYJLX.ImeMode = System.Windows.Forms.ImeMode.NoControl;
            this.yssTextBoxYJLX.Location = new System.Drawing.Point(107, 115);
            this.yssTextBoxYJLX.Name = "yssTextBoxYJLX";
            this.yssTextBoxYJLX.Size = new System.Drawing.Size(117, 21);
            this.yssTextBoxYJLX.TabIndex = 37;
            this.yssTextBoxYJLX.Tag = this.cell119;
            this.yssTextBoxYJLX.YssCaption = "应计利息";
            this.yssTextBoxYJLX.YssIsMust = true;
            this.yssTextBoxYJLX.YssKiloDelimiter = true;
            this.yssTextBoxYJLX.YssLength = 18;
            this.yssTextBoxYJLX.YssNumeric = "16, 2";
            // 
            // cell119
            // 
            this.cell119.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell119.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell119.InnerControl = this.yssTextBoxYJLX;
            // 
            // improvedTextBoxSLV
            // 
            // 
            // 
            // 
            this.improvedTextBoxSLV.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.improvedTextBoxSLV.Location = new System.Drawing.Point(345, 92);
            this.improvedTextBoxSLV.Name = "improvedTextBoxSLV";
            this.improvedTextBoxSLV.PrefixForeColor = System.Drawing.Color.Blue;
            this.improvedTextBoxSLV.Size = new System.Drawing.Size(117, 21);
            this.improvedTextBoxSLV.Sufix = "%";
            this.improvedTextBoxSLV.SufixForeColor = System.Drawing.Color.Blue;
            this.improvedTextBoxSLV.TabIndex = 36;
            this.improvedTextBoxSLV.Tag = this.cell108;
            this.improvedTextBoxSLV.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.improvedTextBoxSLV.YssCaption = "税率";
            this.improvedTextBoxSLV.YssIsMust = true;
            this.improvedTextBoxSLV.YssKiloDelimiter = true;
            this.improvedTextBoxSLV.YssLength = 15;
            this.improvedTextBoxSLV.YssNumeric = "7, 8";
            // 
            // cell108
            // 
            this.cell108.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell108.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell108.InnerControl = this.improvedTextBoxSLV;
            // 
            // TextBoxJXLLV
            // 
            // 
            // 
            // 
            this.TextBoxJXLLV.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.TextBoxJXLLV.Location = new System.Drawing.Point(107, 92);
            this.TextBoxJXLLV.Name = "TextBoxJXLLV";
            this.TextBoxJXLLV.PrefixForeColor = System.Drawing.Color.Blue;
            this.TextBoxJXLLV.Size = new System.Drawing.Size(117, 21);
            this.TextBoxJXLLV.Sufix = "%";
            this.TextBoxJXLLV.SufixForeColor = System.Drawing.Color.Blue;
            this.TextBoxJXLLV.TabIndex = 35;
            this.TextBoxJXLLV.Tag = this.cell39;
            this.TextBoxJXLLV.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.TextBoxJXLLV.YssCaption = "计息利率";
            this.TextBoxJXLLV.YssIsMust = true;
            this.TextBoxJXLLV.YssKiloDelimiter = true;
            this.TextBoxJXLLV.YssLength = 15;
            this.TextBoxJXLLV.YssNumeric = "7, 8";
            // 
            // cell39
            // 
            this.cell39.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell39.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell39.InnerControl = this.TextBoxJXLLV;
            // 
            // TextBoxXX
            // 
            // 
            // 
            // 
            this.TextBoxXX.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.TextBoxXX.Location = new System.Drawing.Point(345, 69);
            this.TextBoxXX.Name = "TextBoxXX";
            this.TextBoxXX.PrefixForeColor = System.Drawing.Color.Blue;
            this.TextBoxXX.Size = new System.Drawing.Size(117, 21);
            this.TextBoxXX.Sufix = "%";
            this.TextBoxXX.SufixForeColor = System.Drawing.Color.Blue;
            this.TextBoxXX.TabIndex = 26;
            this.TextBoxXX.Tag = this.cell66;
            this.TextBoxXX.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.TextBoxXX.YssCaption = "利率下限";
            this.TextBoxXX.YssKiloDelimiter = true;
            this.TextBoxXX.YssLength = 15;
            this.TextBoxXX.YssNumeric = "7, 8";
            // 
            // cell66
            // 
            this.cell66.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell66.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell66.InnerControl = this.TextBoxXX;
            // 
            // TextBoxJZLIL
            // 
            // 
            // 
            // 
            this.TextBoxJZLIL.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.TextBoxJZLIL.Location = new System.Drawing.Point(107, 23);
            this.TextBoxJZLIL.Name = "TextBoxJZLIL";
            this.TextBoxJZLIL.PrefixForeColor = System.Drawing.Color.Blue;
            this.TextBoxJZLIL.Size = new System.Drawing.Size(117, 21);
            this.TextBoxJZLIL.Sufix = "%";
            this.TextBoxJZLIL.SufixForeColor = System.Drawing.Color.Blue;
            this.TextBoxJZLIL.TabIndex = 25;
            this.TextBoxJZLIL.Tag = this.cell38;
            this.TextBoxJZLIL.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.TextBoxJZLIL.YssCaption = "基准利率";
            this.TextBoxJZLIL.YssIsMust = true;
            this.TextBoxJZLIL.YssKiloDelimiter = true;
            this.TextBoxJZLIL.YssLength = 15;
            this.TextBoxJZLIL.YssNumeric = "7, 8";
            // 
            // cell38
            // 
            this.cell38.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell38.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell38.InnerControl = this.TextBoxJZLIL;
            // 
            // panel2
            // 
            this.panel2.Controls.Add(this.yssTextMM);
            this.panel2.Controls.Add(this.yssSelFX);
            this.panel2.Controls.Add(this.yssTextBoxDay);
            this.panel2.Location = new System.Drawing.Point(345, 46);
            this.panel2.Name = "panel2";
            this.panel2.Size = new System.Drawing.Size(117, 22);
            this.panel2.TabIndex = 24;
            this.panel2.Tag = this.cell48;
            // 
            // yssTextMM
            // 
            this.yssTextMM.AutoTooltip = false;
            this.yssTextMM.BackColor = System.Drawing.Color.White;
            this.yssTextMM.ImeMode = System.Windows.Forms.ImeMode.On;
            this.yssTextMM.Location = new System.Drawing.Point(58, 0);
            this.yssTextMM.Name = "yssTextMM";
            this.yssTextMM.Size = new System.Drawing.Size(59, 21);
            this.yssTextMM.TabIndex = 28;
            this.yssTextMM.Tag = this.cell30;
            this.yssTextMM.Text = "0";
            // 
            // yssSelFX
            // 
            this.yssSelFX.AddedSelItemName = "";
            // 
            // 
            // 
            this.yssSelFX.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.yssSelFX.FilterCond = "";
            this.yssSelFX.Location = new System.Drawing.Point(0, 1);
            this.yssSelFX.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo16.MethodName = "getDataListByTypes";
            controlMethodInfo16.MethodParams = null;
            controlMethodInfo16.MethodParamValues = new string[] {
        "FXZQMS,"};
            controlMethodInfo16.Methods = null;
            this.yssSelFX.MethodInfo = controlMethodInfo16;
            this.yssSelFX.Name = "yssSelFX";
            this.yssSelFX.PrefixBackColor = System.Drawing.Color.White;
            this.yssSelFX.QueryCond = "";
            this.yssSelFX.QueryType = "";
            this.yssSelFX.Size = new System.Drawing.Size(55, 21);
            this.yssSelFX.TabIndex = 21;
            this.yssSelFX.Tag = this.cell48;
            this.yssSelFX.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.yssSelFX.YssCaption = "检查间隔";
            this.yssSelFX.ExpandClick += new System.EventHandler(this.yssSelFX_ExpandClick);
            this.yssSelFX.SelectedValueChanged += new System.EventHandler(this.yssSelFX_SelectedValueChanged);
            // 
            // cell48
            // 
            this.cell48.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell48.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell48.InnerControl = this.panel2;
            // 
            // yssTextBoxDay
            // 
            this.yssTextBoxDay.AutoTooltip = false;
            this.yssTextBoxDay.BackColor = System.Drawing.Color.White;
            this.yssTextBoxDay.Dock = System.Windows.Forms.DockStyle.Right;
            this.yssTextBoxDay.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.yssTextBoxDay.ForeColor = System.Drawing.Color.Blue;
            this.yssTextBoxDay.ImeMode = System.Windows.Forms.ImeMode.On;
            this.yssTextBoxDay.Location = new System.Drawing.Point(91, 0);
            this.yssTextBoxDay.Name = "yssTextBoxDay";
            this.yssTextBoxDay.Size = new System.Drawing.Size(26, 21);
            this.yssTextBoxDay.TabIndex = 22;
            this.yssTextBoxDay.YssLength = 2;
            this.yssTextBoxDay.YssNumeric = "2";
            // 
            // yssSelJXGS
            // 
            this.yssSelJXGS.AddedSelItemName = "";
            this.yssSelJXGS.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.yssSelJXGS.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.yssSelJXGS.DisplayName = "C_DV_NAME";
            this.yssSelJXGS.DisplayValue = "C_DV_CODE";
            this.yssSelJXGS.FilterCond = "";
            this.yssSelJXGS.Location = new System.Drawing.Point(107, 46);
            this.yssSelJXGS.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo12.MethodName = "getDataListByTypes";
            controlMethodInfo12.MethodParams = null;
            controlMethodInfo12.MethodParamValues = new string[] {
        "AI_FOA_FI,"};
            controlMethodInfo12.Methods = null;
            this.yssSelJXGS.MethodInfo = controlMethodInfo12;
            this.yssSelJXGS.Name = "yssSelJXGS";
            this.yssSelJXGS.Parameter = "C_DV_NAME";
            this.yssSelJXGS.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.yssSelJXGS.QueryCond = "AI_FOA_FI";
            this.yssSelJXGS.QueryType = "CacheType";
            this.yssSelJXGS.Size = new System.Drawing.Size(117, 21);
            this.yssSelJXGS.TabIndex = 20;
            this.yssSelJXGS.Tag = this.cell44;
            this.yssSelJXGS.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.yssSelJXGS.YssCaption = "计息公式";
            this.yssSelJXGS.YssIsMust = true;
            this.yssSelJXGS.YssKiloDelimiter = true;
            this.yssSelJXGS.SelectedValueChanged += new System.EventHandler(this.yssSelJXGS_SelectedValueChanged);
            // 
            // cell44
            // 
            this.cell44.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell44.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell44.InnerControl = this.yssSelJXGS;
            // 
            // TextBoxBLXS
            // 
            // 
            // 
            // 
            this.TextBoxBLXS.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.TextBoxBLXS.Location = new System.Drawing.Point(345, 23);
            this.TextBoxBLXS.Name = "TextBoxBLXS";
            this.TextBoxBLXS.PrefixForeColor = System.Drawing.Color.Blue;
            this.TextBoxBLXS.Size = new System.Drawing.Size(117, 21);
            this.TextBoxBLXS.Sufix = "%";
            this.TextBoxBLXS.SufixForeColor = System.Drawing.Color.Blue;
            this.TextBoxBLXS.TabIndex = 19;
            this.TextBoxBLXS.Tag = this.cell42;
            this.TextBoxBLXS.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.TextBoxBLXS.YssCaption = "比例系数";
            this.TextBoxBLXS.YssIsMust = true;
            this.TextBoxBLXS.YssKiloDelimiter = true;
            this.TextBoxBLXS.YssLength = 15;
            this.TextBoxBLXS.YssNumeric = "7, 8";
            // 
            // cell42
            // 
            this.cell42.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell42.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell42.InnerControl = this.TextBoxBLXS;
            // 
            // yssSelLLLX
            // 
            this.yssSelLLLX.AddedSelItemName = "";
            this.yssSelLLLX.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.yssSelLLLX.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.yssSelLLLX.DisplayName = "C_DV_NAME";
            this.yssSelLLLX.DisplayValue = "C_DV_CODE";
            this.yssSelLLLX.FilterCond = "";
            this.yssSelLLLX.Location = new System.Drawing.Point(107, 0);
            this.yssSelLLLX.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo13.MethodName = "getDataListByKeys";
            controlMethodInfo13.MethodParams = null;
            controlMethodInfo13.MethodParamValues = new string[] {
        "RTA_FIX,RTA_RATE,RTA_RATE_FD,RTA_RATE_ZF,RTA_INC,RTA_FLOAT_BASE,RTA_FLOAT_LIKED"};
            controlMethodInfo13.Methods = null;
            this.yssSelLLLX.MethodInfo = controlMethodInfo13;
            this.yssSelLLLX.Name = "yssSelLLLX";
            this.yssSelLLLX.Parameter = "C_DV_NAME";
            this.yssSelLLLX.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.yssSelLLLX.QueryCond = "RATE_TYPE_ACCR";
            this.yssSelLLLX.QueryType = "CacheType";
            this.yssSelLLLX.Size = new System.Drawing.Size(117, 21);
            this.yssSelLLLX.TabIndex = 11;
            this.yssSelLLLX.Tag = this.cell25;
            this.yssSelLLLX.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.yssSelLLLX.YssCaption = "利率类型";
            this.yssSelLLLX.YssIsMust = true;
            this.yssSelLLLX.YssKiloDelimiter = true;
            this.yssSelLLLX.SelectedValueChanged += new System.EventHandler(this.yssSelLLLX_SelectedValueChanged);
            // 
            // cell25
            // 
            this.cell25.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell25.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell25.InnerControl = this.yssSelLLLX;
            this.cell25.Text = "利率类型";
            // 
            // yssSelLLDM
            // 
            this.yssSelLLDM.AddedSelItemName = "";
            this.yssSelLLDM.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.yssSelLLDM.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.yssSelLLDM.DisplayName = "C_SEC_NAME";
            this.yssSelLLDM.DisplayValue = "C_SEC_CODE";
            this.yssSelLLDM.FilterCond = "";
            this.yssSelLLDM.Location = new System.Drawing.Point(345, 0);
            this.yssSelLLDM.Margin = new System.Windows.Forms.Padding(0);
            this.yssSelLLDM.MethodInfo = null;
            this.yssSelLLDM.Name = "yssSelLLDM";
            this.yssSelLLDM.Parameter = "C_SEC_NAME";
            this.yssSelLLDM.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.yssSelLLDM.QueryCond = "";
            this.yssSelLLDM.QueryType = "CacheType";
            this.yssSelLLDM.Size = new System.Drawing.Size(117, 21);
            this.yssSelLLDM.TabIndex = 12;
            this.yssSelLLDM.Tag = this.cell36;
            this.yssSelLLDM.YssAssociaType = FAST.Core.Context.AssociaFAST.NULL;
            this.yssSelLLDM.YssCaption = "利率代码";
            this.yssSelLLDM.YssIsMust = true;
            this.yssSelLLDM.YssKiloDelimiter = true;
            this.yssSelLLDM.YssOnBeforeLoadData += new FAST.Core.BaseControl.YssSelCombox.yssBeforeLoadData(this.yssSelLLDM_YssOnBeforeLoadData);
            // 
            // cell36
            // 
            this.cell36.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell36.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell36.InnerControl = this.yssSelLLDM;
            this.cell36.Text = "利率代码";
            // 
            // yssDateTimeDQ
            // 
            this.yssDateTimeDQ.BackColor = System.Drawing.Color.Transparent;
            this.yssDateTimeDQ.DateBeginChecked = true;
            this.yssDateTimeDQ.DateEndChecked = true;
            this.yssDateTimeDQ.Location = new System.Drawing.Point(0, -21);
            this.yssDateTimeDQ.Margin = new System.Windows.Forms.Padding(0);
            this.yssDateTimeDQ.Name = "yssDateTimeDQ";
            this.yssDateTimeDQ.Size = new System.Drawing.Size(117, 21);
            this.yssDateTimeDQ.TabIndex = 32;
            this.yssDateTimeDQ.Tag = this.cell122;
            this.yssDateTimeDQ.yssEnabled = true;
            this.yssDateTimeDQ.yssFormatDateStr = "yyyy-MM-dd";
            this.yssDateTimeDQ.yssInterval = false;
            this.yssDateTimeDQ.yssLabelStr = "至";
            this.yssDateTimeDQ.yssShowOperLable = false;
            this.yssDateTimeDQ.YssShowSecond = true;
            this.yssDateTimeDQ.yssTimeControl = false;
            // 
            // cell122
            // 
            this.cell122.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell122.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell122.InnerControl = this.yssDateTimeDQ;
            // 
            // yssSelDZ
            // 
            this.yssSelDZ.AddedSelItemName = "";
            // 
            // 
            // 
            this.yssSelDZ.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.yssSelDZ.FilterCond = "";
            this.yssSelDZ.Location = new System.Drawing.Point(107, 161);
            this.yssSelDZ.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo10.MethodName = "getDataListByKeys";
            controlMethodInfo10.MethodParams = null;
            controlMethodInfo10.MethodParamValues = new string[] {
        "MRGC,DQRGC,JXRGC"};
            controlMethodInfo10.Methods = null;
            this.yssSelDZ.MethodInfo = controlMethodInfo10;
            this.yssSelDZ.Name = "yssSelDZ";
            this.yssSelDZ.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.yssSelDZ.QueryCond = "";
            this.yssSelDZ.QueryType = "";
            this.yssSelDZ.Size = new System.Drawing.Size(117, 21);
            this.yssSelDZ.TabIndex = 30;
            this.yssSelDZ.Tag = this.cell112;
            this.yssSelDZ.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.yssSelDZ.YssCaption = "倒置算法";
            this.yssSelDZ.YssIsMust = true;
            // 
            // cell112
            // 
            this.cell112.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell112.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell112.InnerControl = this.yssSelDZ;
            // 
            // yssDateTimeQX
            // 
            this.yssDateTimeQX.BackColor = System.Drawing.Color.Transparent;
            this.yssDateTimeQX.DateBeginChecked = true;
            this.yssDateTimeQX.DateEndChecked = true;
            this.yssDateTimeQX.Location = new System.Drawing.Point(107, 138);
            this.yssDateTimeQX.Margin = new System.Windows.Forms.Padding(0);
            this.yssDateTimeQX.Name = "yssDateTimeQX";
            this.yssDateTimeQX.Size = new System.Drawing.Size(117, 21);
            this.yssDateTimeQX.TabIndex = 34;
            this.yssDateTimeQX.Tag = this.cell74;
            this.yssDateTimeQX.yssEnabled = true;
            this.yssDateTimeQX.yssFormatDateStr = "yyyy-MM-dd";
            this.yssDateTimeQX.yssInterval = false;
            this.yssDateTimeQX.yssLabelStr = "至";
            this.yssDateTimeQX.yssShowOperLable = false;
            this.yssDateTimeQX.YssShowSecond = true;
            this.yssDateTimeQX.yssTimeControl = false;
            // 
            // cell74
            // 
            this.cell74.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell74.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell74.InnerControl = this.yssDateTimeQX;
            // 
            // yssDateTimeJX
            // 
            this.yssDateTimeJX.BackColor = System.Drawing.Color.Transparent;
            this.yssDateTimeJX.DateBeginChecked = true;
            this.yssDateTimeJX.DateEndChecked = true;
            this.yssDateTimeJX.Location = new System.Drawing.Point(345, 138);
            this.yssDateTimeJX.Margin = new System.Windows.Forms.Padding(0);
            this.yssDateTimeJX.Name = "yssDateTimeJX";
            this.yssDateTimeJX.Size = new System.Drawing.Size(117, 21);
            this.yssDateTimeJX.TabIndex = 29;
            this.yssDateTimeJX.Tag = this.cell110;
            this.yssDateTimeJX.yssEnabled = true;
            this.yssDateTimeJX.yssFormatDateStr = "yyyy-MM-dd";
            this.yssDateTimeJX.yssInterval = false;
            this.yssDateTimeJX.yssLabelStr = "至";
            this.yssDateTimeJX.yssShowOperLable = false;
            this.yssDateTimeJX.YssShowSecond = true;
            this.yssDateTimeJX.yssTimeControl = false;
            // 
            // cell110
            // 
            this.cell110.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell110.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell110.InnerControl = this.yssDateTimeJX;
            // 
            // row8
            // 
            this.row8.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell24,
            this.cell25,
            this.cell27,
            this.cell28,
            this.cell36});
            this.row8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row8.Text = null;
            // 
            // cell24
            // 
            this.cell24.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell24.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell24.InnerControl = null;
            this.cell24.Text = "利率类型：";
            // 
            // cell27
            // 
            this.cell27.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell27.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell27.InnerControl = null;
            // 
            // cell28
            // 
            this.cell28.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell28.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell28.InnerControl = null;
            this.cell28.Text = "利率代码：";
            // 
            // row9
            // 
            this.row9.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell37,
            this.cell38,
            this.cell40,
            this.cell41,
            this.cell42});
            this.row9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row9.Text = null;
            // 
            // cell37
            // 
            this.cell37.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell37.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell37.InnerControl = null;
            this.cell37.Text = "基准利率：";
            // 
            // cell40
            // 
            this.cell40.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell40.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell40.InnerControl = null;
            // 
            // cell41
            // 
            this.cell41.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell41.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell41.InnerControl = null;
            this.cell41.Text = "比例系数：";
            // 
            // row10
            // 
            this.row10.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell43,
            this.cell44,
            this.cell46,
            this.cell47,
            this.cell48});
            this.row10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row10.Text = null;
            // 
            // cell43
            // 
            this.cell43.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell43.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell43.InnerControl = null;
            this.cell43.Text = "计息公式：";
            // 
            // cell46
            // 
            this.cell46.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell46.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell46.InnerControl = null;
            // 
            // cell47
            // 
            this.cell47.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell47.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell47.InnerControl = null;
            this.cell47.Text = "检查间隔：";
            // 
            // row13
            // 
            this.row13.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell49,
            this.cell50,
            this.cell64,
            this.cell65,
            this.cell66});
            this.row13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row13.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row13.Text = null;
            // 
            // cell49
            // 
            this.cell49.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell49.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell49.InnerControl = null;
            this.cell49.Text = "利率上限：";
            // 
            // cell64
            // 
            this.cell64.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell64.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell64.InnerControl = null;
            // 
            // cell65
            // 
            this.cell65.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell65.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell65.InnerControl = null;
            this.cell65.Text = "利率下限：";
            // 
            // row35
            // 
            this.row35.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell26,
            this.cell39,
            this.cell45,
            this.cell63,
            this.cell108});
            this.row35.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row35.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row35.Text = null;
            // 
            // cell26
            // 
            this.cell26.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell26.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell26.InnerControl = null;
            this.cell26.Text = "计息利率：";
            // 
            // cell45
            // 
            this.cell45.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell45.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell45.InnerControl = null;
            // 
            // cell63
            // 
            this.cell63.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell63.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell63.InnerControl = null;
            this.cell63.Text = "税  率：";
            // 
            // row36
            // 
            this.row36.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell113,
            this.cell119,
            this.cell168,
            this.cell169,
            this.cell170});
            this.row36.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row36.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row36.Text = null;
            // 
            // cell113
            // 
            this.cell113.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell113.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell113.InnerControl = null;
            this.cell113.Text = "应计利息：";
            // 
            // cell168
            // 
            this.cell168.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell168.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell168.InnerControl = null;
            // 
            // cell169
            // 
            this.cell169.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell169.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell169.InnerControl = null;
            this.cell169.Text = "利  差：";
            // 
            // row14
            // 
            this.row14.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell73,
            this.cell74,
            this.cell75,
            this.cell109,
            this.cell110});
            this.row14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row14.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row14.Text = null;
            // 
            // cell73
            // 
            this.cell73.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell73.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell73.InnerControl = null;
            this.cell73.Text = "起息日期：";
            // 
            // cell75
            // 
            this.cell75.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell75.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell75.InnerControl = null;
            // 
            // cell109
            // 
            this.cell109.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell109.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell109.InnerControl = null;
            this.cell109.Text = "截息日期：";
            // 
            // row15
            // 
            this.row15.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell111,
            this.cell112,
            this.cell114,
            this.cell115,
            this.cell116});
            this.row15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row15.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row15.Text = null;
            // 
            // cell111
            // 
            this.cell111.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell111.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell111.InnerControl = null;
            this.cell111.Text = "倒置算法：";
            // 
            // cell114
            // 
            this.cell114.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell114.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell114.InnerControl = null;
            // 
            // cell115
            // 
            this.cell115.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell115.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell115.InnerControl = null;
            // 
            // row18
            // 
            this.row18.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell117,
            this.cell118,
            this.cell120,
            this.cell121,
            this.cell122});
            this.row18.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row18.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row18.Text = null;
            // 
            // cell117
            // 
            this.cell117.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell117.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell117.InnerControl = null;
            this.cell117.Text = "发行日期：";
            // 
            // cell120
            // 
            this.cell120.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell120.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell120.InnerControl = null;
            // 
            // cell121
            // 
            this.cell121.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell121.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell121.InnerControl = null;
            this.cell121.Text = "到期日期：";
            // 
            // row38
            // 
            this.row38.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell129,
            this.cell130,
            this.cell131,
            this.cell132,
            this.cell133});
            this.row38.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row38.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row38.Text = null;
            // 
            // cell129
            // 
            this.cell129.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell129.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell129.InnerControl = null;
            this.cell129.Text = "计提基准：";
            // 
            // cell131
            // 
            this.cell131.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell131.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell131.InnerControl = null;
            // 
            // cell132
            // 
            this.cell132.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell132.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell132.InnerControl = null;
            // 
            // cell133
            // 
            this.cell133.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell133.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell133.InnerControl = null;
            // 
            // tabPaBZZC
            // 
            this.tabPaBZZC.Controls.Add(this.tableMin);
            this.tabPaBZZC.Name = "tabPaBZZC";
            this.tabPaBZZC.TabCloseButtonVisible = false;
            this.tabPaBZZC.Text = "标准资产";
            // 
            // tableMin
            // 
            this.tableMin.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(243)))), ((int)(((byte)(245)))), ((int)(((byte)(249)))));
            // 
            // 
            // 
            this.tableMin.Border.Bottom = false;
            this.tableMin.Border.Left = false;
            this.tableMin.Border.Right = false;
            this.tableMin.Border.Top = false;
            this.tableMin.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.column7,
            this.column8,
            this.column9,
            this.column10,
            this.column11,
            this.column12});
            this.tableMin.Controls.Add(this.yssSelCombox_jtjz4std);
            this.tableMin.Controls.Add(this.txtCouponRate);
            this.tableMin.Controls.Add(this.txtTaxRate);
            this.tableMin.Controls.Add(this.cboPayFrequency);
            this.tableMin.Controls.Add(this.cboPayFormula);
            this.tableMin.Controls.Add(this.dtpCountBeginDate);
            this.tableMin.Controls.Add(this.dtpCountEndDate);
            this.tableMin.Controls.Add(this.cboBackward);
            this.tableMin.Controls.Add(this.dtpToList);
            this.tableMin.Controls.Add(this.dtpOffList);
            this.tableMin.Controls.Add(this.improvedTextBox3);
            this.tableMin.Cursor = System.Windows.Forms.Cursors.Default;
            this.tableMin.DefaultToolStripItems = ((Yss.KTable.Enums.SysToolStripItems)((((((((((((Yss.KTable.Enums.SysToolStripItems.ShowHideColumn | Yss.KTable.Enums.SysToolStripItems.GroupByColumn)
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
            this.tableMin.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableMin.Location = new System.Drawing.Point(1, 1);
            this.tableMin.Name = "tableMin";
            this.tableMin.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row20,
            this.row21,
            this.row22,
            this.row23,
            this.row24,
            this.row25});
            this.tableMin.ShowColumnHeader = false;
            this.tableMin.Size = new System.Drawing.Size(487, 175);
            this.tableMin.TabIndex = 0;
            this.tableMin.Text = "tableMinFB";
            // 
            // column7
            // 
            this.column7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column7.Width = 107;
            // 
            // column8
            // 
            this.column8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column8.Width = 60;
            // 
            // column9
            // 
            this.column9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column9.Width = 60;
            // 
            // column10
            // 
            this.column10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column10.Width = 30;
            // 
            // column11
            // 
            this.column11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column11.Width = 91;
            // 
            // column12
            // 
            this.column12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column12.Width = 122;
            // 
            // yssSelCombox_jtjz4std
            // 
            this.yssSelCombox_jtjz4std.AddedSelItemName = "";
            // 
            // 
            // 
            this.yssSelCombox_jtjz4std.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.yssSelCombox_jtjz4std.FilterCond = "";
            this.yssSelCombox_jtjz4std.Location = new System.Drawing.Point(348, 69);
            this.yssSelCombox_jtjz4std.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo7.MethodName = "getDataListByKeys";
            controlMethodInfo7.MethodParams = null;
            controlMethodInfo7.MethodParamValues = new string[] {
        "LC_SL,LC_JE"};
            controlMethodInfo7.Methods = null;
            this.yssSelCombox_jtjz4std.MethodInfo = controlMethodInfo7;
            this.yssSelCombox_jtjz4std.Name = "yssSelCombox_jtjz4std";
            this.yssSelCombox_jtjz4std.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.yssSelCombox_jtjz4std.QueryCond = "";
            this.yssSelCombox_jtjz4std.QueryType = "";
            this.yssSelCombox_jtjz4std.Size = new System.Drawing.Size(121, 21);
            this.yssSelCombox_jtjz4std.TabIndex = 28;
            this.yssSelCombox_jtjz4std.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.yssSelCombox_jtjz4std.YssCaption = "计提基准";
            this.yssSelCombox_jtjz4std.YssIsMust = true;
            // 
            // row20
            // 
            this.row20.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell80,
            this.cell81,
            this.cell82,
            this.cell83,
            this.cell100,
            this.cell101});
            this.row20.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row20.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row20.Text = null;
            // 
            // cell80
            // 
            this.cell80.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell80.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell80.InnerControl = null;
            this.cell80.Name = "";
            this.cell80.Text = "票面利率：";
            // 
            // cell82
            // 
            this.cell82.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell82.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell82.InnerControl = null;
            // 
            // cell83
            // 
            this.cell83.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell83.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell83.InnerControl = null;
            // 
            // cell100
            // 
            this.cell100.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell100.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell100.InnerControl = null;
            this.cell100.Text = "税   率：";
            // 
            // row21
            // 
            this.row21.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell84,
            this.cell85,
            this.cell86,
            this.cell87,
            this.cell102,
            this.cell103});
            this.row21.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row21.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row21.Text = null;
            // 
            // cell84
            // 
            this.cell84.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell84.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell84.InnerControl = null;
            this.cell84.Text = "付息频率：";
            // 
            // cell86
            // 
            this.cell86.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell86.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell86.InnerControl = null;
            // 
            // cell87
            // 
            this.cell87.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell87.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell87.InnerControl = null;
            // 
            // cell102
            // 
            this.cell102.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell102.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell102.InnerControl = null;
            this.cell102.Text = "计息公式：";
            // 
            // row22
            // 
            this.row22.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell88,
            this.cell89,
            this.cell90,
            this.cell91,
            this.cell104,
            this.cell105});
            this.row22.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row22.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row22.Text = null;
            // 
            // cell88
            // 
            this.cell88.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell88.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell88.InnerControl = null;
            this.cell88.Text = "起息日期：";
            // 
            // cell90
            // 
            this.cell90.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell90.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell90.InnerControl = null;
            // 
            // cell91
            // 
            this.cell91.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell91.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell91.InnerControl = null;
            // 
            // cell104
            // 
            this.cell104.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell104.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell104.InnerControl = null;
            this.cell104.Text = "截息日期：";
            // 
            // row23
            // 
            this.row23.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell92,
            this.cell93,
            this.cell94,
            this.cell95,
            this.cell128});
            this.row23.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row23.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row23.Text = null;
            // 
            // cell92
            // 
            this.cell92.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell92.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell92.InnerControl = null;
            this.cell92.Text = "倒置算法：";
            // 
            // cell94
            // 
            this.cell94.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell94.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell94.InnerControl = null;
            // 
            // cell95
            // 
            this.cell95.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell95.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell95.InnerControl = null;
            // 
            // cell128
            // 
            this.cell128.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell128.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell128.InnerControl = null;
            this.cell128.Text = "计提基准：";
            // 
            // row24
            // 
            this.row24.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell96,
            this.cell97,
            this.cell98,
            this.cell99,
            this.cell106,
            this.cell107});
            this.row24.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row24.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row24.Text = null;
            // 
            // cell98
            // 
            this.cell98.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell98.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell98.InnerControl = null;
            // 
            // cell99
            // 
            this.cell99.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell99.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell99.InnerControl = null;
            // 
            // cell106
            // 
            this.cell106.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell106.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell106.InnerControl = null;
            this.cell106.Text = "到期日期：";
            // 
            // row25
            // 
            this.row25.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row25.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row25.Text = null;
            // 
            // column24
            // 
            this.column24.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column24.ForeColor = System.Drawing.Color.Empty;
            this.column24.Width = 111;
            // 
            // column25
            // 
            this.column25.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column25.ForeColor = System.Drawing.Color.Empty;
            this.column25.Width = 118;
            // 
            // column26
            // 
            this.column26.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column26.ForeColor = System.Drawing.Color.Empty;
            this.column26.Width = 30;
            // 
            // column27
            // 
            this.column27.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column27.ForeColor = System.Drawing.Color.Empty;
            this.column27.Width = 91;
            // 
            // column28
            // 
            this.column28.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column28.ForeColor = System.Drawing.Color.Empty;
            this.column28.Width = 116;
            // 
            // row30
            // 
            this.row30.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row30.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row30.Text = null;
            // 
            // row31
            // 
            this.row31.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row31.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row31.Text = null;
            // 
            // row32
            // 
            this.row32.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row32.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row32.Text = null;
            // 
            // row33
            // 
            this.row33.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row33.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row33.Text = null;
            // 
            // row34
            // 
            this.row34.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row34.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row34.Text = null;
            // 
            // column19
            // 
            this.column19.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column19.ForeColor = System.Drawing.Color.Empty;
            this.column19.Width = 111;
            // 
            // column20
            // 
            this.column20.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column20.ForeColor = System.Drawing.Color.Empty;
            this.column20.Width = 118;
            // 
            // column21
            // 
            this.column21.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column21.ForeColor = System.Drawing.Color.Empty;
            this.column21.Width = 30;
            // 
            // column22
            // 
            this.column22.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column22.ForeColor = System.Drawing.Color.Empty;
            this.column22.Width = 91;
            // 
            // column23
            // 
            this.column23.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column23.ForeColor = System.Drawing.Color.Empty;
            this.column23.Width = 115;
            // 
            // row26
            // 
            this.row26.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row26.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row26.Text = null;
            // 
            // row27
            // 
            this.row27.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row27.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row27.Text = null;
            // 
            // row28
            // 
            this.row28.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row28.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row28.Text = null;
            // 
            // row29
            // 
            this.row29.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row29.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row29.Text = null;
            // 
            // tbDesc
            // 
            this.tbDesc.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(243)))), ((int)(((byte)(245)))), ((int)(((byte)(249)))));
            // 
            // 
            // 
            this.tbDesc.Border.Bottom = false;
            this.tbDesc.Border.Left = false;
            this.tbDesc.Border.Right = false;
            this.tbDesc.Border.Top = false;
            this.tbDesc.DefaultToolStripItems = Yss.KTable.Enums.SysToolStripItems.None;
            this.tbDesc.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.tbDesc.Location = new System.Drawing.Point(0, 569);
            this.tbDesc.Name = "tbDesc";
            this.tbDesc.ShowColumnHeader = false;
            this.tbDesc.Size = new System.Drawing.Size(489, 75);
            this.tbDesc.TabIndex = 16;
            this.tbDesc.Text = "table1";
            // 
            // row37
            // 
            this.row37.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell125,
            this.cell126,
            this.cell127,
            this.cell134,
            this.cell135,
            this.cell136});
            this.row37.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row37.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row37.FullRowSelected = false;
            this.row37.Text = null;
            // 
            // cell125
            // 
            this.cell125.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell125.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell125.InnerControl = null;
            this.cell125.Text = "   投资组合：";
            // 
            // cell126
            // 
            this.cell126.ColSpan = 2;
            this.cell126.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell126.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell126.InnerControl = this.portAndGroup;
            // 
            // portAndGroup
            // 
            this.portAndGroup.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.portAndGroup.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.portAndGroup.EnterOnTailClick = false;
            this.portAndGroup.IsFillDecimal = false;
            this.portAndGroup.Location = new System.Drawing.Point(110, 266);
            this.portAndGroup.Margin = new System.Windows.Forms.Padding(0);
            this.portAndGroup.Name = "portAndGroup";
            this.portAndGroup.PrefixForeColor = System.Drawing.SystemColors.WindowText;
            this.portAndGroup.ShowColumnHeader = true;
            this.portAndGroup.Size = new System.Drawing.Size(119, 21);
            this.portAndGroup.SortColumn = "C_PORT_NAME_ST";
            this.portAndGroup.SufixForeColor = System.Drawing.SystemColors.WindowText;
            this.portAndGroup.TabIndex = 39;
            this.portAndGroup.Tag = this.cell126;
            this.portAndGroup.YssCaption = "投资组合";
            this.portAndGroup.YssKiloDelimiter = true;
            // 
            // cell127
            // 
            this.cell127.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell127.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell127.InnerControl = null;
            // 
            // cell134
            // 
            this.cell134.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell134.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell134.InnerControl = null;
            // 
            // cell135
            // 
            this.cell135.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell135.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell135.InnerControl = null;
            this.cell135.Text = "金融工具：";
            // 
            // cell136
            // 
            this.cell136.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell136.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell136.InnerControl = this.cboFinInstruments;
            // 
            // cboFinInstruments
            // 
            this.cboFinInstruments.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboFinInstruments.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboFinInstruments.DefaultValue = "JRGJ_QYL";
            this.cboFinInstruments.DisplayName = "C_DC_NAME";
            this.cboFinInstruments.DisplayValue = "C_DC_CODE";
            this.cboFinInstruments.FilterCond = "";
            this.cboFinInstruments.Location = new System.Drawing.Point(351, 266);
            this.cboFinInstruments.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo18.MethodName = "getDataListByTypes";
            controlMethodInfo18.MethodParams = null;
            controlMethodInfo18.MethodParamValues = new string[] {
        "JRGJ_GJLX,"};
            controlMethodInfo18.Methods = null;
            this.cboFinInstruments.MethodInfo = controlMethodInfo18;
            this.cboFinInstruments.Name = "cboFinInstruments";
            this.cboFinInstruments.PrefixBackColor = System.Drawing.Color.White;
            this.cboFinInstruments.QueryCond = "JRGJ_GJLX";
            this.cboFinInstruments.QueryType = "CacheType";
            this.cboFinInstruments.Size = new System.Drawing.Size(121, 21);
            this.cboFinInstruments.TabIndex = 19;
            this.cboFinInstruments.Tag = this.cell136;
            this.cboFinInstruments.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboFinInstruments.YssCaption = "金融工具";
            // 
            // Frm_SEC_BASE_LC_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(489, 669);
            this.DoubleBuffered = true;
            this.Name = "Frm_SEC_BASE_LC_S";
            this.StatuType = "新增(&Add...)";
            this.Text = "";
            this.YssSetStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.Load += new System.EventHandler(this.Frm_SEC_BASE_LC_S_Load);
            this.tbMain.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.tabControl1.ResumeLayout(false);
            this.tabPaFBZC.ResumeLayout(false);
            this.tableFBZC1.ResumeLayout(false);
            this.panel2.ResumeLayout(false);
            this.panel2.PerformLayout();
            this.tabPaBZZC.ResumeLayout(false);
            this.tableMin.ResumeLayout(false);
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
        private Yss.KRichEx.YssTextBox txtSecCode;
        private FAST.Core.BaseControl.YssSelCombox cboMkt;
        private FAST.Core.BaseControl.YssSelCombox cboSecVar;
        private Yss.KRichEx.YssTextBox txtSecMktCode;
        private Yss.KRichEx.YssTextBox txtSecName;
        private FAST.Core.BaseControl.YssSelCombox cboCury;
        private Yss.KRichEx.YssTextBox txtFvIssue;
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
        private Yss.KTable.Models.Cell cell29;
        private Yss.KTable.Models.Cell cell30;
        private Yss.KTable.Models.Row row11;
        private Yss.KTable.Models.Cell cell31;
        private Yss.KTable.Models.Cell cell32;
        private Yss.KTable.Models.Cell cell33;
        private Yss.KRichEx.YssTextBox txtIsinCode;
        private Yss.KRichEx.YssTextBox txtAmountHD;
        private Yss.KRichEx.YssTextBox txtPriceFcr;
        private FAST.Core.BaseControl.YssSelCombox cboAIMode;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpOffList;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpToList;
        private FAST.Core.BaseControl.PenetrationComboBox cboOrgan;
        private Yss.KTable.Models.Cell cell34;
        private Yss.KTable.Models.Cell cell35;
        private Yss.KTable.Models.Row row12;
        private Yss.KRichEx.ImprovedTextBox txtCouponRate;
        private Yss.KRichEx.ImprovedTextBox txtTaxRate;
        private FAST.Core.BaseControl.YssSelCombox cboPayFrequency;
        private FAST.Core.BaseControl.YssSelCombox cboPayFormula;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpCountBeginDate;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpCountEndDate;
        private Yss.KTable.Models.Row row16;
        private Yss.KTable.Models.Cell cell55;
        private Yss.KTable.Models.Cell cell52;
        private Yss.KTable.Models.Cell cell51;
        private Yss.KTable.Models.Cell cell53;
        private Yss.KTable.Models.Cell cell54;
        private FAST.Core.BaseControl.YssSelCombox cboInvestment;
        private FAST.Core.BaseControl.YssSelCombox cboIncomeType;
        private Yss.KTable.Models.Column column6;
        private Yss.KTable.Models.Cell cell56;
        private Yss.KTable.Models.Cell cell57;
        private Yss.KTable.Models.Cell cell58;
        private Yss.KTable.Models.Cell cell59;
        private Yss.KTable.Models.Cell cell60;
        private Yss.KTable.Models.Cell cell61;
        private Yss.KTable.Models.Cell cell62;
        private Yss.KTable.Models.Row row17;
        private Yss.KTable.Models.Cell cell67;
        private Yss.KTable.Models.Cell cell68;
        private Yss.KTable.Models.Cell cell69;
        private Yss.KTable.Models.Cell cell70;
        private Yss.KTable.Models.Cell cell71;
        private Yss.KTable.Models.Cell cell72;
        private FAST.Core.BaseControl.YssSelCombox cboDateType;
        private FAST.Core.BaseControl.YssSelCombox cboFHZT;
        private FAST.Core.BaseControl.YssSelCombox cboBackward;
        private Yss.KRichEx.IntegerInputEx iniDate;
        private Yss.KTable.Models.Row row19;
        private Yss.KTable.Models.Cell cell76;
        private Yss.KTable.Models.Cell cell77;
        private Yss.KTable.Models.Cell cell78;
        private Yss.KTable.Models.Cell cell79;
        private FAST.Core.BaseControl.YssSelCombox cboETFType;
        private Yss.Controls.TabControl tabControl1;
        private Yss.Controls.TabPage tabPaBZZC;
        private Yss.Controls.TabPage tabPaFBZC;
        private Yss.KTable.Models.Table tableMin;
        private Yss.KTable.Models.Column column7;
        private Yss.KTable.Models.Column column8;
        private Yss.KTable.Models.Column column9;
        private Yss.KTable.Models.Column column10;
        private Yss.KTable.Models.Column column11;
        private Yss.KTable.Models.Row row20;
        private Yss.KTable.Models.Cell cell80;
        private Yss.KTable.Models.Cell cell81;
        private Yss.KTable.Models.Cell cell82;
        private Yss.KTable.Models.Cell cell83;
        private Yss.KTable.Models.Row row21;
        private Yss.KTable.Models.Cell cell84;
        private Yss.KTable.Models.Cell cell85;
        private Yss.KTable.Models.Cell cell86;
        private Yss.KTable.Models.Cell cell87;
        private Yss.KTable.Models.Row row22;
        private Yss.KTable.Models.Row row23;
        private Yss.KTable.Models.Row row24;
        private Yss.KTable.Models.Row row25;
        private Yss.KTable.Models.Cell cell88;
        private Yss.KTable.Models.Cell cell89;
        private Yss.KTable.Models.Cell cell90;
        private Yss.KTable.Models.Cell cell91;
        private Yss.KTable.Models.Cell cell92;
        private Yss.KTable.Models.Cell cell93;
        private Yss.KTable.Models.Cell cell94;
        private Yss.KTable.Models.Cell cell95;
        private Yss.KTable.Models.Cell cell97;
        private Yss.KTable.Models.Cell cell98;
        private Yss.KTable.Models.Cell cell99;
        private Yss.KTable.Models.Column column12;
        private Yss.KTable.Models.Cell cell100;
        private Yss.KTable.Models.Cell cell101;
        private Yss.KTable.Models.Cell cell102;
        private Yss.KTable.Models.Cell cell103;
        private Yss.KTable.Models.Cell cell104;
        private Yss.KTable.Models.Cell cell105;
        private Yss.KTable.Models.Cell cell107;
        private Yss.KTable.Models.Cell cell106;
        private Yss.KTable.Models.Table tableFBZC1;
        private Yss.KTable.Models.Column column13;
        private Yss.KTable.Models.Column column14;
        private Yss.KTable.Models.Column column16;
        private Yss.KTable.Models.Column column17;
        private Yss.KTable.Models.Column column18;
        private Yss.KTable.Models.Row row8;
        private Yss.KTable.Models.Cell cell24;
        private Yss.KTable.Models.Cell cell25;
        private Yss.KTable.Models.Cell cell27;
        private Yss.KTable.Models.Cell cell28;
        private Yss.KTable.Models.Cell cell36;
        private Yss.KTable.Models.Row row9;
        private Yss.KTable.Models.Cell cell37;
        private Yss.KTable.Models.Cell cell38;
        private Yss.KTable.Models.Cell cell40;
        private Yss.KTable.Models.Cell cell41;
        private Yss.KTable.Models.Cell cell42;
        private Yss.KTable.Models.Row row10;
        private Yss.KTable.Models.Cell cell43;
        private Yss.KTable.Models.Cell cell44;
        private Yss.KTable.Models.Cell cell46;
        private Yss.KTable.Models.Cell cell47;
        private Yss.KTable.Models.Cell cell48;
        private Yss.KTable.Models.Row row13;
        private Yss.KTable.Models.Cell cell49;
        private Yss.KTable.Models.Cell cell50;
        private Yss.KTable.Models.Cell cell64;
        private Yss.KTable.Models.Cell cell65;
        private Yss.KTable.Models.Cell cell66;
        private FAST.Core.BaseControl.YssSelCombox yssSelLLDM;
        private FAST.Core.BaseControl.YssSelCombox yssSelLLLX;
        private FAST.Core.BaseControl.YssSelCombox yssSelFX;
        private Yss.KRichEx.YssTextBox yssTextBoxDay;
        private FAST.Core.BaseControl.YssSelCombox yssSelJXGS;
        private Yss.KRichEx.ImprovedTextBox TextBoxBLXS;
        private System.Windows.Forms.Panel panel2;
        private Yss.KRichEx.ImprovedTextBox improvedTextBox3;
        private Yss.KRichEx.ImprovedTextBox TextBoxXX;
        private Yss.KRichEx.ImprovedTextBox TextBoxJZLIL;
        private FAST.Core.BaseControl.YssDateTimeInterval yssDateTimeDQ;
        private FAST.Core.BaseControl.YssDateTimeInterval yssDateTimeFX;
        private FAST.Core.BaseControl.YssSelCombox yssSelDZ;
        private FAST.Core.BaseControl.YssDateTimeInterval yssDateTimeJX;
        private Yss.KRichEx.YssTextBox yssTextMM;
        private FAST.Core.BaseControl.YssDateTimeInterval yssDateTimeQX;
        private Yss.KTable.Models.Column column19;
        private Yss.KTable.Models.Column column20;
        private Yss.KTable.Models.Column column21;
        private Yss.KTable.Models.Column column22;
        private Yss.KTable.Models.Column column23;
        private Yss.KTable.Models.Row row26;
        private Yss.KTable.Models.Row row27;
        private Yss.KTable.Models.Row row28;
        private Yss.KTable.Models.Row row29;
        private Yss.KTable.Models.Column column24;
        private Yss.KTable.Models.Column column25;
        private Yss.KTable.Models.Column column26;
        private Yss.KTable.Models.Column column27;
        private Yss.KTable.Models.Column column28;
        private Yss.KTable.Models.Row row30;
        private Yss.KTable.Models.Row row31;
        private Yss.KTable.Models.Row row32;
        private Yss.KTable.Models.Row row33;
        private Yss.KTable.Models.Row row34;
        private Yss.KRichEx.ImprovedTextBox improvedTextBoxLC;
        private Yss.KRichEx.YssTextBox yssTextBoxYJLX;
        private Yss.KTable.Models.Cell cell119;
        private Yss.KRichEx.ImprovedTextBox improvedTextBoxSLV;
        private Yss.KTable.Models.Cell cell108;
        private Yss.KRichEx.ImprovedTextBox TextBoxJXLLV;
        private Yss.KTable.Models.Cell cell39;
        private Yss.KTable.Models.Row row35;
        private Yss.KTable.Models.Cell cell26;
        private Yss.KTable.Models.Cell cell45;
        private Yss.KTable.Models.Cell cell63;
        private Yss.KTable.Models.Row row36;
        private Yss.KTable.Models.Cell cell113;
        private Yss.KTable.Models.Cell cell168;
        private Yss.KTable.Models.Cell cell169;
        private Yss.KTable.Models.Cell cell170;
        private Yss.KTable.Models.Row row14;
        private Yss.KTable.Models.Cell cell73;
        private Yss.KTable.Models.Cell cell74;
        private Yss.KTable.Models.Cell cell75;
        private Yss.KTable.Models.Cell cell109;
        private Yss.KTable.Models.Cell cell110;
        private Yss.KTable.Models.Row row15;
        private Yss.KTable.Models.Cell cell111;
        private Yss.KTable.Models.Cell cell112;
        private Yss.KTable.Models.Cell cell114;
        private Yss.KTable.Models.Row row18;
        private Yss.KTable.Models.Cell cell117;
        private Yss.KTable.Models.Cell cell118;
        private Yss.KTable.Models.Cell cell120;
        private Yss.KTable.Models.Cell cell121;
        private Yss.KTable.Models.Cell cell122;
        private System.Windows.Forms.Panel panel1;
        private Yss.KTable.Models.Cell cell116;
        private Yss.KTable.Models.Cell cell115;
        private Yss.KRichEx.ImprovedTextBox TextBoxLLSX;
        private Yss.KTable.Models.Cell cell96;
        private Yss.KTable.Models.Cell cell123;
        private Yss.KTable.Models.Cell cell124;
        private Yss.KRichEx.IntegerInputEx iniRateYearDays;
        private Yss.KTable.Models.Table tbDesc;
        private Yss.KTable.Models.Row row37;
        private Yss.KTable.Models.Cell cell125;
        private Yss.KTable.Models.Cell cell126;
        private Yss.KTable.Models.Cell cell127;
        private FAST.Core.BaseControl.GroupTextBox portAndGroup;
        private Yss.KTable.Models.Cell cell128;
        private FAST.Core.BaseControl.YssSelCombox yssSelCombox_jtjz4std;
        private FAST.Core.BaseControl.YssSelCombox yssSelCombox_jtjz4nostd;
        private Yss.KTable.Models.Cell cell130;
        private Yss.KTable.Models.Row row38;
        private Yss.KTable.Models.Cell cell129;
        private Yss.KTable.Models.Cell cell131;
        private Yss.KTable.Models.Cell cell132;
        private Yss.KTable.Models.Cell cell133;
        private Yss.KTable.Models.Cell cell134;
        private Yss.KTable.Models.Cell cell135;
        private Yss.KTable.Models.Cell cell136;
        private FAST.Core.BaseControl.YssSelCombox cboFinInstruments;
    }
}