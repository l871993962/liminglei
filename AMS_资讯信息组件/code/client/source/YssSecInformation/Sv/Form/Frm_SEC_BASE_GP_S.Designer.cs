﻿using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;

using FAST.Core.Resource;
using FAST.Core.Context;
namespace YssSecInformation.Sv.Form
{
    partial class Frm_SEC_BASE_GP_S
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
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo5 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo4 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo1 = new FAST.Core.BaseControl.ControlMethodInfo();
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
            this.cell32 = new Yss.KTable.Models.Cell();
            this.txtAmountHD = new Yss.KRichEx.YssTextBox();
            this.row4 = new Yss.KTable.Models.Row();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.txtSecMktCode = new Yss.KRichEx.YssTextBox();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.txtSecName = new Yss.KRichEx.YssTextBox();
            this.row5 = new Yss.KTable.Models.Row();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.cboSecVar = new FAST.Core.BaseControl.YssSelCombox();
            this.cell13 = new Yss.KTable.Models.Cell();
            this.cell14 = new Yss.KTable.Models.Cell();
            this.cell15 = new Yss.KTable.Models.Cell();
            this.cboMkt = new FAST.Core.BaseControl.YssSelCombox();
            this.row6 = new Yss.KTable.Models.Row();
            this.cell16 = new Yss.KTable.Models.Cell();
            this.cell17 = new Yss.KTable.Models.Cell();
            this.cboInvMode = new FAST.Core.BaseControl.YssSelCombox();
            this.cell18 = new Yss.KTable.Models.Cell();
            this.cell19 = new Yss.KTable.Models.Cell();
            this.cell20 = new Yss.KTable.Models.Cell();
            this.txtFvIssue = new Yss.KRichEx.YssTextBox();
            this.cboCury = new FAST.Core.BaseControl.YssSelCombox();
            this.cell22 = new Yss.KTable.Models.Cell();
            this.row7 = new Yss.KTable.Models.Row();
            this.cell21 = new Yss.KTable.Models.Cell();
            this.cell23 = new Yss.KTable.Models.Cell();
            this.cell24 = new Yss.KTable.Models.Cell();
            this.cell25 = new Yss.KTable.Models.Cell();
            this.txtPriceFcr = new Yss.KRichEx.YssTextBox();
            this.row8 = new Yss.KTable.Models.Row();
            this.cell31 = new Yss.KTable.Models.Cell();
            this.cell33 = new Yss.KTable.Models.Cell();
            this.cell34 = new Yss.KTable.Models.Cell();
            this.cell35 = new Yss.KTable.Models.Cell();
            this.txtSecCnName = new Yss.KRichEx.YssTextBox();
            this.row9 = new Yss.KTable.Models.Row();
            this.row10 = new Yss.KTable.Models.Row();
            this.dtpToList = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell27 = new Yss.KTable.Models.Cell();
            this.dtpOffList = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell30 = new Yss.KTable.Models.Cell();
            this.row11 = new Yss.KTable.Models.Row();
            this.cell26 = new Yss.KTable.Models.Cell();
            this.cell28 = new Yss.KTable.Models.Cell();
            this.cell29 = new Yss.KTable.Models.Cell();
            this.row12 = new Yss.KTable.Models.Row();
            this.cell36 = new Yss.KTable.Models.Cell();
            this.cell37 = new Yss.KTable.Models.Cell();
            this.cboFinInstruments = new FAST.Core.BaseControl.YssSelCombox();
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
            this.tbMain.Controls.Add(this.cboFinInstruments);
            this.tbMain.Controls.Add(this.txtPriceFcr);
            this.tbMain.Controls.Add(this.txtSecCnName);
            this.tbMain.Controls.Add(this.txtAmountHD);
            this.tbMain.Controls.Add(this.txtIsinCode);
            this.tbMain.Controls.Add(this.cboMkt);
            this.tbMain.Controls.Add(this.cboSecVar);
            this.tbMain.Controls.Add(this.cboCury);
            this.tbMain.Controls.Add(this.txtSecName);
            this.tbMain.Controls.Add(this.txtSecMktCode);
            this.tbMain.Controls.Add(this.txtSecCode);
            this.tbMain.Controls.Add(this.txtFvIssue);
            this.tbMain.Controls.Add(this.dtpToList);
            this.tbMain.Controls.Add(this.dtpOffList);
            this.tbMain.Controls.Add(this.cboInvMode);
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3,
            this.row4,
            this.row5,
            this.row6,
            this.row7,
            this.row8,
            this.row12,
            this.row9,
            this.row10,
            this.row11});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(493, 385);
            // 
            // stBarBottom
            // 
            // 
            // 
            // 
            this.stBarBottom.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.stBarBottom.Border.Bottom = false;
            this.stBarBottom.Border.Left = false;
            this.stBarBottom.Border.Right = false;
            this.stBarBottom.Location = new System.Drawing.Point(0, 415);
            this.stBarBottom.StatuType = "新增(&Add...)";
            // 
            // pnlMain
            // 
            this.pnlMain.Size = new System.Drawing.Size(493, 440);
            // 
            // yssPanel1
            // 
            // 
            // 
            // 
            this.yssPanel1.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.yssPanel1.Size = new System.Drawing.Size(493, 440);
            // 
            // hpAssist
            // 
            // 
            // column1
            // 
            this.column1.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.Width = 110;
            // 
            // column2
            // 
            this.column2.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column2.Width = 122;
            // 
            // column3
            // 
            this.column3.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column3.Width = 30;
            // 
            // column4
            // 
            this.column4.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column4.Width = 91;
            // 
            // column5
            // 
            this.column5.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
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
            this.row2.ForeColor = System.Drawing.Color.Black;
            this.row2.FullRowSelected = false;
            this.row2.Height = 10;
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
            this.txtSecCode.Size = new System.Drawing.Size(121, 21);
            this.txtSecCode.TabIndex = 0;
            this.txtSecCode.Tag = this.cell2;
            this.txtSecCode.YssCaption = "证券内码";
            this.txtSecCode.YssIsMust = true;
            this.txtSecCode.YssLength = 50;
            // 
            // cell3
            // 
            this.cell3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell3.InnerControl = null;
            // 
            // cell4
            // 
            this.cell4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell4.InnerControl = null;
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
            this.txtIsinCode.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtIsinCode.Location = new System.Drawing.Point(353, 43);
            this.txtIsinCode.Name = "txtIsinCode";
            this.txtIsinCode.Size = new System.Drawing.Size(121, 21);
            this.txtIsinCode.TabIndex = 1;
            this.txtIsinCode.Tag = this.cell5;
            // 
            // cell32
            // 
            this.cell32.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.cell32.ForeColor = System.Drawing.Color.Black;
            this.cell32.InnerControl = this.txtAmountHD;
            // 
            // txtAmountHD
            // 
            this.txtAmountHD.AutoTooltip = false;
            this.txtAmountHD.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.txtAmountHD.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.txtAmountHD.ForeColor = System.Drawing.Color.Blue;
            this.txtAmountHD.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtAmountHD.Location = new System.Drawing.Point(110, 168);
            this.txtAmountHD.Name = "txtAmountHD";
            this.txtAmountHD.Size = new System.Drawing.Size(121, 21);
            this.txtAmountHD.TabIndex = 8;
            this.txtAmountHD.Tag = this.cell32;
            this.txtAmountHD.Text = "100";
            this.txtAmountHD.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtAmountHD.YssCaption = "每手股数";
            this.txtAmountHD.YssIsMust = true;
            this.txtAmountHD.YssKiloDelimiter = true;
            this.txtAmountHD.YssLength = 18;
            this.txtAmountHD.YssNumeric = "14, 4";
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
            this.row4.Height = 25;
            this.row4.Text = null;
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.InnerControl = null;
            this.cell6.Text = "   上市代码：";
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.InnerControl = this.txtSecMktCode;
            // 
            // txtSecMktCode
            // 
            this.txtSecMktCode.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.txtSecMktCode.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtSecMktCode.Location = new System.Drawing.Point(110, 68);
            this.txtSecMktCode.Name = "txtSecMktCode";
            this.txtSecMktCode.Size = new System.Drawing.Size(121, 21);
            this.txtSecMktCode.TabIndex = 2;
            this.txtSecMktCode.Tag = this.cell7;
            this.txtSecMktCode.YssCaption = "上市代码";
            this.txtSecMktCode.YssIsMust = true;
            this.txtSecMktCode.YssLength = 30;
            // 
            // cell8
            // 
            this.cell8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell8.InnerControl = null;
            // 
            // cell9
            // 
            this.cell9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell9.InnerControl = null;
            this.cell9.Text = "股票名称：";
            // 
            // cell10
            // 
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.InnerControl = this.txtSecName;
            // 
            // txtSecName
            // 
            this.txtSecName.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.txtSecName.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtSecName.Location = new System.Drawing.Point(353, 68);
            this.txtSecName.Name = "txtSecName";
            this.txtSecName.Size = new System.Drawing.Size(121, 21);
            this.txtSecName.TabIndex = 3;
            this.txtSecName.Tag = this.cell10;
            this.txtSecName.YssCaption = "股票名称";
            this.txtSecName.YssIsMust = true;
            this.txtSecName.YssLength = 50;
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
            this.row5.Height = 25;
            this.row5.Text = null;
            // 
            // cell11
            // 
            this.cell11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell11.InnerControl = null;
            this.cell11.Text = "   证券品种：";
            // 
            // cell12
            // 
            this.cell12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell12.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell12.InnerControl = this.cboSecVar;
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
            this.cboSecVar.KTableTree = true;
            this.cboSecVar.Location = new System.Drawing.Point(110, 93);
            this.cboSecVar.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo3.MethodName = "getDataListByTypes";
            controlMethodInfo3.MethodParams = null;
            controlMethodInfo3.MethodParamValues = new string[] {
        "GP,"};
            controlMethodInfo3.Methods = new string[] {
        "getDataListByTypes",
        "getQueryResByTypes",
        "getDataListByKeys",
        "getQueryResByKeys",
        "getDataByCode",
        "getDataList",
        "getDataListRes",
        "getKeyConvertMap"};
            this.cboSecVar.MethodInfo = controlMethodInfo3;
            this.cboSecVar.Name = "cboSecVar";
            this.cboSecVar.NodeID = "C_SEC_VAR_CODE";
            this.cboSecVar.Parameter = "C_SEC_VAR_NAME";
            this.cboSecVar.ParaNodeID = "C_DA_CODE_P";
            this.cboSecVar.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.cboSecVar.QueryCond = "GP";
            this.cboSecVar.QueryType = "CacheType";
            this.cboSecVar.Size = new System.Drawing.Size(121, 21);
            this.cboSecVar.SortColumn = "C_SEC_VAR_NAME";
            this.cboSecVar.TabIndex = 4;
            this.cboSecVar.Tag = this.cell12;
            this.cboSecVar.YssAssociaType = YssInformation.Support.Context.AssociaType.base_seccategory;
            this.cboSecVar.YssCaption = "证券品种";
            this.cboSecVar.YssIsMust = true;
            this.cboSecVar.YssKiloDelimiter = true;
            // 
            // cell13
            // 
            this.cell13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell13.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell13.InnerControl = null;
            // 
            // cell14
            // 
            this.cell14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell14.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell14.InnerControl = null;
            this.cell14.Text = "交易市场：";
            // 
            // cell15
            // 
            this.cell15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell15.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell15.InnerControl = this.cboMkt;
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
            this.cboMkt.KTableTree = true;
            this.cboMkt.Location = new System.Drawing.Point(353, 93);
            this.cboMkt.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo2.MethodName = "getDataList";
            controlMethodInfo2.MethodParams = null;
            controlMethodInfo2.MethodParamValues = null;
            controlMethodInfo2.Methods = new string[] {
        "getDataListByTypes",
        "getQueryResByTypes",
        "getDataListByKeys",
        "getQueryResByKeys",
        "getDataByCode",
        "getDataList",
        "getDataListRes",
        "getKeyConvertMap"};
            this.cboMkt.MethodInfo = controlMethodInfo2;
            this.cboMkt.Name = "cboMkt";
            this.cboMkt.NodeID = "C_MKT_CODE";
            this.cboMkt.Parameter = "C_MKT_CODE~C_MKT_NAME";
            this.cboMkt.ParaNodeID = "C_PARAENT_CODE";
            this.cboMkt.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.cboMkt.QueryCond = "";
            this.cboMkt.QueryType = "";
            this.cboMkt.ShowColumnHeader = true;
            this.cboMkt.Size = new System.Drawing.Size(121, 21);
            this.cboMkt.SortColumn = "C_MKT_NAME";
            this.cboMkt.TabIndex = 5;
            this.cboMkt.Tag = this.cell15;
            this.cboMkt.YssAssociaType = YssInformation.Support.Context.AssociaType.base_exchange;
            this.cboMkt.YssCaption = "交易市场";
            this.cboMkt.YssIsMust = true;
            this.cboMkt.YssKiloDelimiter = true;
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
            this.row6.Height = 25;
            this.row6.Text = null;
            // 
            // cell16
            // 
            this.cell16.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell16.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell16.InnerControl = null;
            this.cell16.Text = "   投资方式：";
            // 
            // cell17
            // 
            this.cell17.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell17.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell17.InnerControl = this.cboInvMode;
            // 
            // cboInvMode
            // 
            this.cboInvMode.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboInvMode.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboInvMode.DisplayName = "C_DC_NAME";
            this.cboInvMode.DisplayValue = "C_DC_CODE";
            this.cboInvMode.FilterCond = "";
            this.cboInvMode.Location = new System.Drawing.Point(110, 118);
            this.cboInvMode.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo5.MethodName = "getDataListByKeys";
            controlMethodInfo5.MethodParams = null;
            controlMethodInfo5.MethodParamValues = new string[] {
        "INV_GQ_WSSGS,INV_GQ_TZJH,INV_GQ_TZJJ"};
            controlMethodInfo5.Methods = null;
            this.cboInvMode.MethodInfo = controlMethodInfo5;
            this.cboInvMode.Name = "cboInvMode";
            this.cboInvMode.PrefixBackColor = System.Drawing.Color.White;
            this.cboInvMode.QueryCond = "";
            this.cboInvMode.QueryType = "";
            this.cboInvMode.Size = new System.Drawing.Size(121, 21);
            this.cboInvMode.TabIndex = 15;
            this.cboInvMode.Tag = this.cell17;
            this.cboInvMode.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboInvMode.YssCaption = "投资方式";
            this.cboInvMode.YssKiloDelimiter = true;
            // 
            // cell18
            // 
            this.cell18.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell18.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell18.InnerControl = null;
            // 
            // cell19
            // 
            this.cell19.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell19.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell19.InnerControl = null;
            this.cell19.Text = "发行面值：";
            // 
            // cell20
            // 
            this.cell20.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell20.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell20.InnerControl = this.txtFvIssue;
            // 
            // txtFvIssue
            // 
            this.txtFvIssue.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.txtFvIssue.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.txtFvIssue.ForeColor = System.Drawing.Color.Blue;
            this.txtFvIssue.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtFvIssue.Location = new System.Drawing.Point(353, 118);
            this.txtFvIssue.Name = "txtFvIssue";
            this.txtFvIssue.Size = new System.Drawing.Size(121, 21);
            this.txtFvIssue.TabIndex = 7;
            this.txtFvIssue.Tag = this.cell20;
            this.txtFvIssue.Text = "1.00";
            this.txtFvIssue.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtFvIssue.YssCaption = "发行面值";
            this.txtFvIssue.YssIsMust = true;
            this.txtFvIssue.YssKiloDelimiter = true;
            this.txtFvIssue.YssLength = 19;
            this.txtFvIssue.YssNumeric = "14, 5";
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
            this.cboCury.Location = new System.Drawing.Point(110, 143);
            this.cboCury.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo4.MethodName = "getDataList";
            controlMethodInfo4.MethodParams = null;
            controlMethodInfo4.MethodParamValues = null;
            controlMethodInfo4.Methods = new string[] {
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
            this.cboCury.MethodInfo = controlMethodInfo4;
            this.cboCury.Name = "cboCury";
            this.cboCury.Parameter = "C_DC_NAME";
            this.cboCury.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.cboCury.QueryCond = "";
            this.cboCury.QueryType = "";
            this.cboCury.Size = new System.Drawing.Size(121, 21);
            this.cboCury.TabIndex = 6;
            this.cboCury.Tag = this.cell22;
            this.cboCury.YssAssociaType = YssInformation.Support.Context.AssociaType.base_currency;
            this.cboCury.YssCaption = "交易币种";
            this.cboCury.YssIsMust = true;
            this.cboCury.YssKiloDelimiter = true;
            // 
            // cell22
            // 
            this.cell22.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell22.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell22.InnerControl = this.cboCury;
            // 
            // row7
            // 
            this.row7.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell21,
            this.cell22,
            this.cell23,
            this.cell24,
            this.cell25});
            this.row7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row7.ForeColor = System.Drawing.Color.Black;
            this.row7.FullRowSelected = false;
            this.row7.Height = 25;
            this.row7.Text = null;
            // 
            // cell21
            // 
            this.cell21.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell21.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell21.InnerControl = null;
            this.cell21.Text = "   交易币种：";
            // 
            // cell23
            // 
            this.cell23.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell23.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell23.InnerControl = null;
            // 
            // cell24
            // 
            this.cell24.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell24.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell24.InnerControl = null;
            this.cell24.Text = "报价因子：";
            // 
            // cell25
            // 
            this.cell25.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell25.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell25.InnerControl = this.txtPriceFcr;
            // 
            // txtPriceFcr
            // 
            this.txtPriceFcr.AutoTooltip = false;
            this.txtPriceFcr.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.txtPriceFcr.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.txtPriceFcr.ForeColor = System.Drawing.Color.Blue;
            this.txtPriceFcr.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtPriceFcr.Location = new System.Drawing.Point(353, 143);
            this.txtPriceFcr.Name = "txtPriceFcr";
            this.txtPriceFcr.Size = new System.Drawing.Size(121, 21);
            this.txtPriceFcr.TabIndex = 9;
            this.txtPriceFcr.Tag = this.cell25;
            this.txtPriceFcr.Text = "1";
            this.txtPriceFcr.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtPriceFcr.YssCaption = "报价因子";
            this.txtPriceFcr.YssIsMust = true;
            this.txtPriceFcr.YssKiloDelimiter = true;
            this.txtPriceFcr.YssLength = 18;
            this.txtPriceFcr.YssNumeric = "14, 4";
            // 
            // row8
            // 
            this.row8.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell31,
            this.cell32,
            this.cell33,
            this.cell34,
            this.cell35});
            this.row8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row8.ForeColor = System.Drawing.Color.Black;
            this.row8.FullRowSelected = false;
            this.row8.Height = 25;
            this.row8.Text = null;
            // 
            // cell31
            // 
            this.cell31.Font = new System.Drawing.Font("宋体", 9F);
            this.cell31.ForeColor = System.Drawing.Color.Black;
            this.cell31.InnerControl = null;
            this.cell31.Text = "   每手股数：";
            // 
            // cell33
            // 
            this.cell33.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.cell33.ForeColor = System.Drawing.Color.Black;
            this.cell33.InnerControl = null;
            // 
            // cell34
            // 
            this.cell34.Font = new System.Drawing.Font("宋体", 9F);
            this.cell34.ForeColor = System.Drawing.Color.Black;
            this.cell34.InnerControl = null;
            this.cell34.Text = "股票中文名称：";
            // 
            // cell35
            // 
            this.cell35.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.cell35.ForeColor = System.Drawing.Color.Black;
            this.cell35.InnerControl = this.txtSecCnName;
            // 
            // txtSecCnName
            // 
            this.txtSecCnName.BackColor = System.Drawing.Color.White;
            this.txtSecCnName.Location = new System.Drawing.Point(353, 168);
            this.txtSecCnName.Name = "txtSecCnName";
            this.txtSecCnName.Size = new System.Drawing.Size(121, 21);
            this.txtSecCnName.TabIndex = 14;
            this.txtSecCnName.Tag = this.cell35;
            this.txtSecCnName.YssCaption = "股票中文名称";
            this.txtSecCnName.YssLength = 50;
            // 
            // row9
            // 
            this.row9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row9.ForeColor = System.Drawing.Color.Black;
            this.row9.FullRowSelected = false;
            this.row9.GroupLineLength = 310;
            this.row9.Height = 10;
            this.row9.IsGroup = true;
            this.row9.Text = null;
            // 
            // row10
            // 
            this.row10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row10.ForeColor = System.Drawing.Color.Black;
            this.row10.FullRowSelected = false;
            this.row10.Height = 10;
            this.row10.Text = null;
            // 
            // dtpToList
            // 
            this.dtpToList.BackColor = System.Drawing.Color.Transparent;
            this.dtpToList.DateBeginChecked = true;
            this.dtpToList.DateEndChecked = true;
            this.dtpToList.Location = new System.Drawing.Point(110, 236);
            this.dtpToList.Margin = new System.Windows.Forms.Padding(0);
            this.dtpToList.Name = "dtpToList";
            this.dtpToList.Size = new System.Drawing.Size(121, 21);
            this.dtpToList.TabIndex = 12;
            this.dtpToList.Tag = this.cell27;
            this.dtpToList.yssEnabled = true;
            this.dtpToList.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpToList.yssInterval = false;
            this.dtpToList.yssLabelStr = "至";
            this.dtpToList.yssShowOperLable = false;
            this.dtpToList.YssShowSecond = true;
            this.dtpToList.yssTimeControl = false;
            // 
            // cell27
            // 
            this.cell27.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.cell27.ForeColor = System.Drawing.Color.Black;
            this.cell27.InnerControl = this.dtpToList;
            // 
            // dtpOffList
            // 
            this.dtpOffList.BackColor = System.Drawing.Color.Transparent;
            this.dtpOffList.DateBeginChecked = true;
            this.dtpOffList.DateEndChecked = true;
            this.dtpOffList.Location = new System.Drawing.Point(353, 236);
            this.dtpOffList.Margin = new System.Windows.Forms.Padding(0);
            this.dtpOffList.Name = "dtpOffList";
            this.dtpOffList.Size = new System.Drawing.Size(121, 21);
            this.dtpOffList.TabIndex = 13;
            this.dtpOffList.Tag = this.cell30;
            this.dtpOffList.yssEnabled = true;
            this.dtpOffList.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpOffList.yssInterval = false;
            this.dtpOffList.yssLabelStr = "至";
            this.dtpOffList.yssShowOperLable = false;
            this.dtpOffList.YssShowSecond = true;
            this.dtpOffList.yssTimeControl = false;
            // 
            // cell30
            // 
            this.cell30.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.cell30.ForeColor = System.Drawing.Color.Black;
            this.cell30.InnerControl = this.dtpOffList;
            // 
            // row11
            // 
            this.row11.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell26,
            this.cell27,
            this.cell28,
            this.cell29,
            this.cell30});
            this.row11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row11.ForeColor = System.Drawing.Color.Black;
            this.row11.FullRowSelected = false;
            this.row11.Height = 25;
            this.row11.Text = null;
            // 
            // cell26
            // 
            this.cell26.Font = new System.Drawing.Font("宋体", 9F);
            this.cell26.ForeColor = System.Drawing.Color.Black;
            this.cell26.InnerControl = null;
            this.cell26.Text = "   上市日期：";
            // 
            // cell28
            // 
            this.cell28.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.cell28.ForeColor = System.Drawing.Color.Black;
            this.cell28.InnerControl = null;
            // 
            // cell29
            // 
            this.cell29.Font = new System.Drawing.Font("宋体", 9F);
            this.cell29.ForeColor = System.Drawing.Color.Black;
            this.cell29.InnerControl = null;
            this.cell29.Text = "退市日期：";
            // 
            // row12
            // 
            this.row12.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell36,
            this.cell37});
            this.row12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row12.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row12.FullRowSelected = false;
            this.row12.Text = null;
            // 
            // cell36
            // 
            this.cell36.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell36.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell36.InnerControl = null;
            this.cell36.Text = "   金融工具：";
            // 
            // cell37
            // 
            this.cell37.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell37.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell37.InnerControl = this.cboFinInstruments;
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
            this.cboFinInstruments.Location = new System.Drawing.Point(110, 193);
            this.cboFinInstruments.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataListByTypes";
            controlMethodInfo1.MethodParams = null;
            controlMethodInfo1.MethodParamValues = new string[] {
        "JRGJ_GJLX,"};
            controlMethodInfo1.Methods = null;
            this.cboFinInstruments.MethodInfo = controlMethodInfo1;
            this.cboFinInstruments.Name = "cboFinInstruments";
            this.cboFinInstruments.PrefixBackColor = System.Drawing.Color.White;
            this.cboFinInstruments.QueryCond = "JRGJ_GJLX";
            this.cboFinInstruments.QueryType = "CacheType";
            this.cboFinInstruments.Size = new System.Drawing.Size(121, 21);
            this.cboFinInstruments.TabIndex = 18;
            this.cboFinInstruments.Tag = this.cell37;
            this.cboFinInstruments.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboFinInstruments.YssCaption = "金融工具";
            // 
            // Frm_SEC_BASE_GP_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(493, 324);
            this.DoubleBuffered = true;
            this.Name = "Frm_SEC_BASE_GP_S";
            this.StatuType = "新增(&Add...)";
            this.Text = "股票基本信息设置";
            this.YssSetStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.Load += new System.EventHandler(this.Frm_SEC_BASE_GP_S_Load);
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
        private Yss.KRichEx.YssTextBox txtAmountHD;
        private Yss.KRichEx.YssTextBox txtPriceFcr;
        private Yss.KRichEx.YssTextBox txtIsinCode;
        private FAST.Core.BaseControl.YssSelCombox cboCury;
        private FAST.Core.BaseControl.YssSelCombox cboMkt;
        private FAST.Core.BaseControl.YssSelCombox cboSecVar;
        private Yss.KRichEx.YssTextBox txtSecName;
        private Yss.KRichEx.YssTextBox txtSecMktCode;
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
        private FAST.Core.BaseControl.YssDateTimeInterval dtpToList;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpOffList;
        private Yss.KRichEx.YssTextBox txtFvIssue;
        private Yss.KTable.Models.Row row11;
        private Yss.KTable.Models.Cell cell27;
        private Yss.KTable.Models.Cell cell30;
        private Yss.KTable.Models.Cell cell26;
        private Yss.KTable.Models.Cell cell28;
        private Yss.KTable.Models.Cell cell29;
        private Yss.KTable.Models.Cell cell31;
        private Yss.KTable.Models.Cell cell32;
        private Yss.KRichEx.YssTextBox txtSecCnName;
        private Yss.KTable.Models.Cell cell33;
        private Yss.KTable.Models.Cell cell34;
        private Yss.KTable.Models.Cell cell35;
        private FAST.Core.BaseControl.YssSelCombox cboInvMode;
        private Yss.KTable.Models.Row row12;
        private Yss.KTable.Models.Cell cell36;
        private Yss.KTable.Models.Cell cell37;
        private FAST.Core.BaseControl.YssSelCombox cboFinInstruments;
    }
}