﻿using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;

using FAST.Core.Context;

using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;

namespace YssSecInformation.Sv.Form
{
    partial class Frm_SEC_BASE_CK_S
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
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo13 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo12 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo11 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo5 = new FAST.Core.BaseControl.ControlMethodInfo();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_SEC_BASE_CK_S));
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo9 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo10 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo8 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo7 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo6 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo2 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo1 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo3 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo4 = new FAST.Core.BaseControl.ControlMethodInfo();
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.row1 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.txtSecCode = new Yss.KRichEx.YssTextBox();
            this.cell25 = new Yss.KTable.Models.Cell();
            this.cboSavingType = new FAST.Core.BaseControl.YssSelCombox();
            this.cell35 = new Yss.KTable.Models.Cell();
            this.txtSavingCode = new Yss.KRichEx.YssTextBox();
            this.cell30 = new Yss.KTable.Models.Cell();
            this.txtSavingName = new Yss.KRichEx.YssTextBox();
            this.cell33 = new Yss.KTable.Models.Cell();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.cell13 = new Yss.KTable.Models.Cell();
            this.cell14 = new Yss.KTable.Models.Cell();
            this.cell15 = new Yss.KTable.Models.Cell();
            this.cboCury = new FAST.Core.BaseControl.YssSelCombox();
            this.cell40 = new Yss.KTable.Models.Cell();
            this.cboMarket = new FAST.Core.BaseControl.YssSelCombox();
            this.cell38 = new Yss.KTable.Models.Cell();
            this.cell16 = new Yss.KTable.Models.Cell();
            this.cell17 = new Yss.KTable.Models.Cell();
            this.iniRateYearDays = new Yss.KRichEx.IntegerInputEx();
            this.cell60 = new Yss.KTable.Models.Cell();
            this.cell50 = new Yss.KTable.Models.Cell();
            this.cboIssureMode = new FAST.Core.BaseControl.YssSelCombox();
            this.cell18 = new Yss.KTable.Models.Cell();
            this.cell19 = new Yss.KTable.Models.Cell();
            this.cell20 = new Yss.KTable.Models.Cell();
            this.cboSavingLimit = new FAST.Core.BaseControl.YssSelCombox();
            this.cell21 = new Yss.KTable.Models.Cell();
            this.cell22 = new Yss.KTable.Models.Cell();
            this.cell53 = new Yss.KTable.Models.Cell();
            this.cboOrgan = new FAST.Core.BaseControl.PenetrationComboBox();
            this.cell23 = new Yss.KTable.Models.Cell();
            this.row2 = new Yss.KTable.Models.Row();
            this.txtIsinCode = new Yss.KRichEx.YssTextBox();
            this.cell28 = new Yss.KTable.Models.Cell();
            this.cboCountMode = new FAST.Core.BaseControl.YssSelCombox();
            this.cell43 = new Yss.KTable.Models.Cell();
            this.cboCountFormula = new FAST.Core.BaseControl.YssSelCombox();
            this.cell48 = new Yss.KTable.Models.Cell();
            this.cboPayFrequency = new FAST.Core.BaseControl.YssSelCombox();
            this.cell45 = new Yss.KTable.Models.Cell();
            this.dtpBegin = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell55 = new Yss.KTable.Models.Cell();
            this.dtpEnd = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell58 = new Yss.KTable.Models.Cell();
            this.row3 = new Yss.KTable.Models.Row();
            this.cell24 = new Yss.KTable.Models.Cell();
            this.cell26 = new Yss.KTable.Models.Cell();
            this.cell27 = new Yss.KTable.Models.Cell();
            this.row4 = new Yss.KTable.Models.Row();
            this.cell29 = new Yss.KTable.Models.Cell();
            this.cell31 = new Yss.KTable.Models.Cell();
            this.cell32 = new Yss.KTable.Models.Cell();
            this.row5 = new Yss.KTable.Models.Row();
            this.cell34 = new Yss.KTable.Models.Cell();
            this.cell36 = new Yss.KTable.Models.Cell();
            this.cell37 = new Yss.KTable.Models.Cell();
            this.row6 = new Yss.KTable.Models.Row();
            this.cell39 = new Yss.KTable.Models.Cell();
            this.cell41 = new Yss.KTable.Models.Cell();
            this.cell42 = new Yss.KTable.Models.Cell();
            this.row7 = new Yss.KTable.Models.Row();
            this.cell44 = new Yss.KTable.Models.Cell();
            this.cell46 = new Yss.KTable.Models.Cell();
            this.cell47 = new Yss.KTable.Models.Cell();
            this.row8 = new Yss.KTable.Models.Row();
            this.cell49 = new Yss.KTable.Models.Cell();
            this.cell51 = new Yss.KTable.Models.Cell();
            this.cell52 = new Yss.KTable.Models.Cell();
            this.row9 = new Yss.KTable.Models.Row();
            this.row10 = new Yss.KTable.Models.Row();
            this.row11 = new Yss.KTable.Models.Row();
            this.cell54 = new Yss.KTable.Models.Cell();
            this.cell56 = new Yss.KTable.Models.Cell();
            this.cell57 = new Yss.KTable.Models.Cell();
            this.row12 = new Yss.KTable.Models.Row();
            this.cell59 = new Yss.KTable.Models.Cell();
            this.cell62 = new Yss.KTable.Models.Cell();
            this.cell63 = new Yss.KTable.Models.Cell();
            this.cell64 = new Yss.KTable.Models.Cell();
            this.txtPurPeriod = new Yss.KRichEx.ImprovedTextBox();
            this.cell61 = new Yss.KTable.Models.Cell();
            this.yssSelCombox1 = new FAST.Core.BaseControl.YssSelCombox();
            this.row13 = new Yss.KTable.Models.Row();
            this.cell65 = new Yss.KTable.Models.Cell();
            this.cell66 = new Yss.KTable.Models.Cell();
            this.cboLimit = new FAST.Core.BaseControl.YssSelCombox();
            this.cell67 = new Yss.KTable.Models.Cell();
            this.cell68 = new Yss.KTable.Models.Cell();
            this.cell69 = new Yss.KTable.Models.Cell();
            this.cboSjsszq = new FAST.Core.BaseControl.YssSelCombox();
            this.row14 = new Yss.KTable.Models.Row();
            this.cell70 = new Yss.KTable.Models.Cell();
            this.cell71 = new Yss.KTable.Models.Cell();
            this.bankHead = new FAST.Core.BaseControl.YssSelCombox();
            this.cell72 = new Yss.KTable.Models.Cell();
            this.cell73 = new Yss.KTable.Models.Cell();
            this.cell74 = new Yss.KTable.Models.Cell();
            this.bankBranch = new FAST.Core.BaseControl.YssSelCombox();
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
            this.tbMain.Controls.Add(this.cboSjsszq);
            this.tbMain.Controls.Add(this.cboLimit);
            this.tbMain.Controls.Add(this.dtpEnd);
            this.tbMain.Controls.Add(this.bankHead);
            this.tbMain.Controls.Add(this.dtpBegin);
            this.tbMain.Controls.Add(this.bankBranch);
            this.tbMain.Controls.Add(this.cboIssureMode);
            this.tbMain.Controls.Add(this.cboPayFrequency);
            this.tbMain.Controls.Add(this.cboCountFormula);
            this.tbMain.Controls.Add(this.cboCountMode);
            this.tbMain.Controls.Add(this.txtIsinCode);
            this.tbMain.Controls.Add(this.cboSavingLimit);
            this.tbMain.Controls.Add(this.cboOrgan);
            this.tbMain.Controls.Add(this.txtSecCode);
            this.tbMain.Controls.Add(this.cboMarket);
            this.tbMain.Controls.Add(this.cboCury);
            this.tbMain.Controls.Add(this.txtSavingName);
            this.tbMain.Controls.Add(this.txtPurPeriod);
            this.tbMain.Controls.Add(this.iniRateYearDays);
            this.tbMain.Controls.Add(this.txtSavingCode);
            this.tbMain.Controls.Add(this.cboSavingType);
            this.tbMain.GridLineColor = System.Drawing.Color.Red;
            this.tbMain.Location = new System.Drawing.Point(0, 31);
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
            this.row13,
            this.row14,
            this.row9,
            this.row10,
            this.row11});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(493, 331);
            // 
            // btnBar
            // 
            this.btnBar.Location = new System.Drawing.Point(0, 1);
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
            this.stBarBottom.Location = new System.Drawing.Point(0, 362);
            // 
            // pnlMain
            // 
            this.pnlMain.Padding = new System.Windows.Forms.Padding(0, 1, 0, 0);
            this.pnlMain.Size = new System.Drawing.Size(493, 387);
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
            this.yssPanel1.Size = new System.Drawing.Size(493, 387);
            // 
            // hpAssist
            // 
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
            // cell1
            // 
            this.cell1.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
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
            // 
            // cell5
            // 
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // txtSecCode
            // 
            this.txtSecCode.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.txtSecCode.Location = new System.Drawing.Point(110, 43);
            this.txtSecCode.Name = "txtSecCode";
            this.txtSecCode.Size = new System.Drawing.Size(121, 21);
            this.txtSecCode.TabIndex = 0;
            this.txtSecCode.Tag = this.cell25;
            this.txtSecCode.YssCaption = "证券内码";
            this.txtSecCode.YssIsMust = true;
            // 
            // cell25
            // 
            this.cell25.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell25.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell25.InnerControl = this.txtSecCode;
            // 
            // cboSavingType
            // 
            this.cboSavingType.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboSavingType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboSavingType.Border.BorderColorHot = System.Drawing.Color.FromArgb(((int)(((byte)(109)))), ((int)(((byte)(129)))), ((int)(((byte)(165)))));
            this.cboSavingType.Border.Bottom = true;
            this.cboSavingType.Border.Left = true;
            this.cboSavingType.Border.Right = true;
            this.cboSavingType.Border.Top = true;
            this.cboSavingType.DisplayName = "C_SEC_VAR_NAME";
            this.cboSavingType.DisplayValue = "C_SEC_VAR_CODE";
            this.cboSavingType.FilterCond = "";
            this.cboSavingType.IsFillDecimal = false;
            this.cboSavingType.KTableTree = true;
            this.cboSavingType.Location = new System.Drawing.Point(110, 93);
            this.cboSavingType.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo13.MethodName = "getDataListByTypes";
            controlMethodInfo13.MethodParams = null;
            controlMethodInfo13.MethodParamValues = new string[] {
        "CK,DK,"};
            controlMethodInfo13.Methods = null;
            this.cboSavingType.MethodInfo = controlMethodInfo13;
            this.cboSavingType.Name = "cboSavingType";
            this.cboSavingType.NodeID = "C_SEC_VAR_CODE";
            this.cboSavingType.Parameter = "C_SEC_VAR_NAME";
            this.cboSavingType.ParaNodeID = "C_DA_CODE_P";
            this.cboSavingType.QueryCond = "CK";
            this.cboSavingType.QueryType = "CacheType";
            this.cboSavingType.Size = new System.Drawing.Size(121, 21);
            this.cboSavingType.SortColumn = "C_SEC_VAR_NAME";
            this.cboSavingType.TabIndex = 4;
            this.cboSavingType.Tag = this.cell35;
            this.cboSavingType.YssAssociaType = YssInformation.Support.Context.AssociaType.base_seccategory;
            this.cboSavingType.YssCaption = "证券品种";
            this.cboSavingType.YssIsMust = true;
            this.cboSavingType.SelectedValueChanged += new System.EventHandler(this.cboSavingType_SelectedValueChanged);
            // 
            // cell35
            // 
            this.cell35.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell35.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell35.InnerControl = this.cboSavingType;
            // 
            // txtSavingCode
            // 
            this.txtSavingCode.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.txtSavingCode.Location = new System.Drawing.Point(110, 68);
            this.txtSavingCode.Name = "txtSavingCode";
            this.txtSavingCode.Size = new System.Drawing.Size(121, 21);
            this.txtSavingCode.TabIndex = 2;
            this.txtSavingCode.Tag = this.cell30;
            this.txtSavingCode.YssCaption = "存放代码";
            this.txtSavingCode.YssIsMust = true;
            this.txtSavingCode.YssLength = 30;
            // 
            // cell30
            // 
            this.cell30.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell30.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell30.InnerControl = this.txtSavingCode;
            // 
            // txtSavingName
            // 
            this.txtSavingName.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.txtSavingName.Location = new System.Drawing.Point(353, 68);
            this.txtSavingName.Name = "txtSavingName";
            this.txtSavingName.Size = new System.Drawing.Size(121, 21);
            this.txtSavingName.TabIndex = 3;
            this.txtSavingName.Tag = this.cell33;
            this.txtSavingName.YssCaption = "存放名称";
            this.txtSavingName.YssIsMust = true;
            this.txtSavingName.YssLength = 50;
            // 
            // cell33
            // 
            this.cell33.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell33.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell33.InnerControl = this.txtSavingName;
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
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
            // 
            // cell10
            // 
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell11
            // 
            this.cell11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
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
            // 
            // cell14
            // 
            this.cell14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell14.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell15
            // 
            this.cell15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell15.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cboCury
            // 
            this.cboCury.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboCury.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboCury.Border.BorderColorHot = System.Drawing.Color.FromArgb(((int)(((byte)(109)))), ((int)(((byte)(129)))), ((int)(((byte)(165)))));
            this.cboCury.Border.Bottom = true;
            this.cboCury.Border.Left = true;
            this.cboCury.Border.Right = true;
            this.cboCury.Border.Top = true;
            this.cboCury.DisplayName = "C_DC_NAME";
            this.cboCury.DisplayValue = "C_DC_CODE";
            this.cboCury.FilterCond = "";
            this.cboCury.IsFillDecimal = false;
            this.cboCury.Location = new System.Drawing.Point(110, 118);
            this.cboCury.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo12.MethodName = "getDataList";
            controlMethodInfo12.MethodParams = null;
            controlMethodInfo12.MethodParamValues = null;
            controlMethodInfo12.Methods = null;
            this.cboCury.MethodInfo = controlMethodInfo12;
            this.cboCury.Name = "cboCury";
            this.cboCury.Parameter = "C_DC_NAME";
            this.cboCury.QueryCond = "";
            this.cboCury.QueryType = "";
            this.cboCury.Size = new System.Drawing.Size(121, 21);
            this.cboCury.TabIndex = 6;
            this.cboCury.Tag = this.cell40;
            this.cboCury.YssAssociaType = YssInformation.Support.Context.AssociaType.base_currency;
            this.cboCury.YssCaption = "交易币种";
            this.cboCury.YssIsMust = true;
            // 
            // cell40
            // 
            this.cell40.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell40.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell40.InnerControl = this.cboCury;
            // 
            // cboMarket
            // 
            this.cboMarket.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboMarket.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboMarket.Border.BorderColorHot = System.Drawing.Color.FromArgb(((int)(((byte)(109)))), ((int)(((byte)(129)))), ((int)(((byte)(165)))));
            this.cboMarket.Border.Bottom = true;
            this.cboMarket.Border.Left = true;
            this.cboMarket.Border.Right = true;
            this.cboMarket.Border.Top = true;
            this.cboMarket.DisplayName = "C_MKT_NAME";
            this.cboMarket.DisplayValue = "C_MKT_NO";
            this.cboMarket.FilterCond = "";
            this.cboMarket.IsFillDecimal = false;
            this.cboMarket.KTableTree = true;
            this.cboMarket.Location = new System.Drawing.Point(353, 93);
            this.cboMarket.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo11.MethodName = "getDataList";
            controlMethodInfo11.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo11.MethodParams")));
            controlMethodInfo11.MethodParamValues = null;
            controlMethodInfo11.Methods = null;
            this.cboMarket.MethodInfo = controlMethodInfo11;
            this.cboMarket.Name = "cboMarket";
            this.cboMarket.NodeID = "C_MKT_CODE";
            this.cboMarket.Parameter = "C_MKT_CODE~C_MKT_NAME";
            this.cboMarket.ParaNodeID = "C_PARAENT_CODE";
            this.cboMarket.QueryCond = "";
            this.cboMarket.QueryType = "";
            this.cboMarket.Size = new System.Drawing.Size(121, 21);
            this.cboMarket.SortColumn = "C_MKT_NAME";
            this.cboMarket.TabIndex = 5;
            this.cboMarket.Tag = this.cell38;
            this.cboMarket.YssAssociaType = YssInformation.Support.Context.AssociaType.base_exchange;
            this.cboMarket.YssCaption = "交易市场";
            this.cboMarket.YssIsMust = true;
            // 
            // cell38
            // 
            this.cell38.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell38.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell38.InnerControl = this.cboMarket;
            // 
            // cell16
            // 
            this.cell16.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell16.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell17
            // 
            this.cell17.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell17.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // iniRateYearDays
            // 
            // 
            // 
            // 
            this.iniRateYearDays.Border.BorderColor = System.Drawing.Color.Silver;
            this.iniRateYearDays.Border.BorderColorHot = System.Drawing.Color.FromArgb(((int)(((byte)(109)))), ((int)(((byte)(129)))), ((int)(((byte)(165)))));
            this.iniRateYearDays.Border.Bottom = true;
            this.iniRateYearDays.Border.Left = true;
            this.iniRateYearDays.Border.Right = true;
            this.iniRateYearDays.Border.Top = true;
            this.iniRateYearDays.Location = new System.Drawing.Point(110, 193);
            this.iniRateYearDays.MinValue = 1;
            this.iniRateYearDays.Name = "iniRateYearDays";
            this.iniRateYearDays.Prefix = "";
            this.iniRateYearDays.RightToLeft = System.Windows.Forms.RightToLeft.No;
            this.iniRateYearDays.Size = new System.Drawing.Size(121, 21);
            this.iniRateYearDays.TabIndex = 10;
            this.iniRateYearDays.Tag = this.cell60;
            this.iniRateYearDays.Text = "360";
            this.iniRateYearDays.Value = 360;
            // 
            // cell60
            // 
            this.cell60.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell60.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell60.InnerControl = this.iniRateYearDays;
            this.cell60.Text = "360";
            // 
            // cell50
            // 
            this.cell50.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell50.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell50.InnerControl = this.cboIssureMode;
            // 
            // cboIssureMode
            // 
            this.cboIssureMode.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboIssureMode.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboIssureMode.Border.BorderColorHot = System.Drawing.Color.FromArgb(((int)(((byte)(109)))), ((int)(((byte)(129)))), ((int)(((byte)(165)))));
            this.cboIssureMode.Border.Bottom = true;
            this.cboIssureMode.Border.Left = true;
            this.cboIssureMode.Border.Right = true;
            this.cboIssureMode.Border.Top = true;
            this.cboIssureMode.FilterCond = "";
            this.cboIssureMode.IsFillDecimal = false;
            this.cboIssureMode.Location = new System.Drawing.Point(110, 168);
            this.cboIssureMode.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo5.MethodName = "getDataListByTypes";
            controlMethodInfo5.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo5.MethodParams")));
            controlMethodInfo5.MethodParamValues = new string[] {
        "HSFS,"};
            controlMethodInfo5.Methods = null;
            this.cboIssureMode.MethodInfo = controlMethodInfo5;
            this.cboIssureMode.Name = "cboIssureMode";
            this.cboIssureMode.QueryCond = "ISSUE_MODE";
            this.cboIssureMode.QueryType = "CacheType";
            this.cboIssureMode.Size = new System.Drawing.Size(121, 21);
            this.cboIssureMode.TabIndex = 19;
            this.cboIssureMode.Tag = this.cell50;
            this.cboIssureMode.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboIssureMode.YssCaption = "核算方式";
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
            // 
            // cell20
            // 
            this.cell20.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell20.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cboSavingLimit
            // 
            this.cboSavingLimit.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboSavingLimit.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboSavingLimit.Border.BorderColorHot = System.Drawing.Color.FromArgb(((int)(((byte)(109)))), ((int)(((byte)(129)))), ((int)(((byte)(165)))));
            this.cboSavingLimit.Border.Bottom = true;
            this.cboSavingLimit.Border.Left = true;
            this.cboSavingLimit.Border.Right = true;
            this.cboSavingLimit.Border.Top = true;
            this.cboSavingLimit.DisplayName = "C_DV_NAME";
            this.cboSavingLimit.DisplayValue = "C_DV_CODE";
            this.cboSavingLimit.FilterCond = "";
            this.cboSavingLimit.IsFillDecimal = false;
            this.cboSavingLimit.Location = new System.Drawing.Point(354, 119);
            this.cboSavingLimit.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo9.MethodName = "";
            controlMethodInfo9.MethodParams = null;
            controlMethodInfo9.MethodParamValues = null;
            controlMethodInfo9.Methods = null;
            this.cboSavingLimit.MethodInfo = controlMethodInfo9;
            this.cboSavingLimit.Name = "cboSavingLimit";
            this.cboSavingLimit.Parameter = "C_DV_NAME";
            this.cboSavingLimit.ParaNodeID = "C_PARAENT_CODE";
            this.cboSavingLimit.QueryCond = "VAR_DUR";
            this.cboSavingLimit.QueryType = "CacheType";
            this.cboSavingLimit.Size = new System.Drawing.Size(120, 21);
            this.cboSavingLimit.TabIndex = 17;
            this.cboSavingLimit.Visible = false;
            this.cboSavingLimit.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboSavingLimit.YssCaption = "期限";
            this.cboSavingLimit.YssIsMust = true;
            // 
            // cell21
            // 
            this.cell21.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell21.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell22
            // 
            this.cell22.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell22.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell53
            // 
            this.cell53.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell53.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell53.InnerControl = this.cboOrgan;
            // 
            // cboOrgan
            // 
            this.cboOrgan.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboOrgan.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboOrgan.Border.BorderColorHot = System.Drawing.Color.FromArgb(((int)(((byte)(109)))), ((int)(((byte)(129)))), ((int)(((byte)(165)))));
            this.cboOrgan.Border.Bottom = true;
            this.cboOrgan.Border.Left = true;
            this.cboOrgan.Border.Right = true;
            this.cboOrgan.Border.Top = true;
            this.cboOrgan.DisplayName = "C_ORG_NAME";
            this.cboOrgan.DisplayValue = "C_ORG_CODE";
            this.cboOrgan.ExpandButtonToolTip = "数据调整……";
            this.cboOrgan.FilterCond = "";
            this.cboOrgan.FunCode = "base_organ";
            this.cboOrgan.KeepDesignValue = true;
            this.cboOrgan.Location = new System.Drawing.Point(353, 168);
            this.cboOrgan.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo10.MethodName = "getDataListByTypes";
            controlMethodInfo10.MethodParams = null;
            controlMethodInfo10.MethodParamValues = new string[] {
        "ORG_SYYH,"};
            controlMethodInfo10.Methods = null;
            this.cboOrgan.MethodInfo = controlMethodInfo10;
            this.cboOrgan.Name = "cboOrgan";
            this.cboOrgan.NodeID = "C_ORG_CODE";
            this.cboOrgan.Parameter = "C_ORG_CODE~C_ORG_NAME";
            this.cboOrgan.ParaNodeID = "C_ORG_CODE_P";
            this.cboOrgan.QueryCond = "ORG_SYYH";
            this.cboOrgan.QueryType = "";
            this.cboOrgan.Size = new System.Drawing.Size(121, 21);
            this.cboOrgan.SortColumn = "C_ORG_NAME";
            this.cboOrgan.TabIndex = 21;
            this.cboOrgan.Tag = this.cell53;
            this.cboOrgan.YssAssociaType = YssInformation.Support.Context.AssociaType.base_organ;
            this.cboOrgan.YssCaption = "所属机构";
            this.cboOrgan.YssKiloDelimiter = true;
            // 
            // cell23
            // 
            this.cell23.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell23.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // row2
            // 
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row2.FullRowSelected = false;
            this.row2.Height = 10;
            this.row2.Text = null;
            // 
            // txtIsinCode
            // 
            this.txtIsinCode.BackColor = System.Drawing.Color.White;
            this.txtIsinCode.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtIsinCode.Location = new System.Drawing.Point(353, 43);
            this.txtIsinCode.Name = "txtIsinCode";
            this.txtIsinCode.Size = new System.Drawing.Size(121, 21);
            this.txtIsinCode.TabIndex = 1;
            this.txtIsinCode.Tag = this.cell28;
            // 
            // cell28
            // 
            this.cell28.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell28.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell28.InnerControl = this.txtIsinCode;
            // 
            // cboCountMode
            // 
            this.cboCountMode.AddedSelItemName = "";
            this.cboCountMode.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.cboCountMode.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboCountMode.Border.BorderColorHot = System.Drawing.Color.FromArgb(((int)(((byte)(109)))), ((int)(((byte)(129)))), ((int)(((byte)(165)))));
            this.cboCountMode.Border.Bottom = true;
            this.cboCountMode.Border.Left = true;
            this.cboCountMode.Border.Right = true;
            this.cboCountMode.Border.Top = true;
            this.cboCountMode.DisplayName = "C_DV_NAME";
            this.cboCountMode.DisplayValue = "C_DV_CODE";
            this.cboCountMode.FilterCond = "";
            this.cboCountMode.IsFillDecimal = false;
            this.cboCountMode.Location = new System.Drawing.Point(353, 118);
            this.cboCountMode.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo8.MethodName = "getDataListByTypes";
            controlMethodInfo8.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo8.MethodParams")));
            controlMethodInfo8.MethodParamValues = new string[] {
        "AI_MOD_TR,"};
            controlMethodInfo8.Methods = null;
            this.cboCountMode.MethodInfo = controlMethodInfo8;
            this.cboCountMode.Name = "cboCountMode";
            this.cboCountMode.Parameter = "C_DV_NAME";
            this.cboCountMode.QueryCond = "AI_MOD_TR";
            this.cboCountMode.QueryType = "CacheType";
            this.cboCountMode.Size = new System.Drawing.Size(121, 21);
            this.cboCountMode.TabIndex = 7;
            this.cboCountMode.Tag = this.cell43;
            this.cboCountMode.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboCountMode.YssCaption = "计息方式";
            this.cboCountMode.YssIsMust = true;
            this.cboCountMode.YssKiloDelimiter = true;
            // 
            // cell43
            // 
            this.cell43.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell43.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell43.InnerControl = this.cboCountMode;
            // 
            // cboCountFormula
            // 
            this.cboCountFormula.AddedSelItemName = "";
            this.cboCountFormula.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.cboCountFormula.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboCountFormula.Border.BorderColorHot = System.Drawing.Color.FromArgb(((int)(((byte)(109)))), ((int)(((byte)(129)))), ((int)(((byte)(165)))));
            this.cboCountFormula.Border.Bottom = true;
            this.cboCountFormula.Border.Left = true;
            this.cboCountFormula.Border.Right = true;
            this.cboCountFormula.Border.Top = true;
            this.cboCountFormula.DisplayName = "C_DV_NAME";
            this.cboCountFormula.DisplayValue = "C_DV_CODE";
            this.cboCountFormula.FilterCond = "";
            this.cboCountFormula.IsFillDecimal = false;
            this.cboCountFormula.Location = new System.Drawing.Point(353, 143);
            this.cboCountFormula.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo7.MethodName = "getDataListByTypes";
            controlMethodInfo7.MethodParams = null;
            controlMethodInfo7.MethodParamValues = new string[] {
        "AI_FOA_FI,"};
            controlMethodInfo7.Methods = null;
            this.cboCountFormula.MethodInfo = controlMethodInfo7;
            this.cboCountFormula.Name = "cboCountFormula";
            this.cboCountFormula.Parameter = "C_DV_NAME";
            this.cboCountFormula.QueryCond = "AI_FOA_FI";
            this.cboCountFormula.QueryType = "CacheType";
            this.cboCountFormula.Size = new System.Drawing.Size(121, 21);
            this.cboCountFormula.TabIndex = 9;
            this.cboCountFormula.Tag = this.cell48;
            this.cboCountFormula.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboCountFormula.YssCaption = "计息公式";
            this.cboCountFormula.YssIsMust = true;
            this.cboCountFormula.YssKiloDelimiter = true;
            this.cboCountFormula.SelectedValueChanged += new System.EventHandler(this.cboCountFormula_SelectedValueChanged);
            // 
            // cell48
            // 
            this.cell48.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell48.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell48.InnerControl = this.cboCountFormula;
            // 
            // cboPayFrequency
            // 
            this.cboPayFrequency.AddedSelItemName = "";
            this.cboPayFrequency.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.cboPayFrequency.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboPayFrequency.Border.BorderColorHot = System.Drawing.Color.FromArgb(((int)(((byte)(109)))), ((int)(((byte)(129)))), ((int)(((byte)(165)))));
            this.cboPayFrequency.Border.Bottom = true;
            this.cboPayFrequency.Border.Left = true;
            this.cboPayFrequency.Border.Right = true;
            this.cboPayFrequency.Border.Top = true;
            this.cboPayFrequency.DisplayName = "C_DV_NAME";
            this.cboPayFrequency.DisplayValue = "C_DV_CODE";
            this.cboPayFrequency.FilterCond = "";
            this.cboPayFrequency.IsFillDecimal = false;
            this.cboPayFrequency.Location = new System.Drawing.Point(110, 143);
            this.cboPayFrequency.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo6.MethodName = "getDataListByTypes";
            controlMethodInfo6.MethodParams = null;
            controlMethodInfo6.MethodParamValues = new string[] {
        "PI_FQCY_FI,"};
            controlMethodInfo6.Methods = null;
            this.cboPayFrequency.MethodInfo = controlMethodInfo6;
            this.cboPayFrequency.Name = "cboPayFrequency";
            this.cboPayFrequency.Parameter = "C_DV_NAME";
            this.cboPayFrequency.QueryCond = "PI_FQCY_FI";
            this.cboPayFrequency.QueryType = "CacheType";
            this.cboPayFrequency.Size = new System.Drawing.Size(121, 21);
            this.cboPayFrequency.TabIndex = 8;
            this.cboPayFrequency.Tag = this.cell45;
            this.cboPayFrequency.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboPayFrequency.YssCaption = "付息频率";
            this.cboPayFrequency.YssIsMust = true;
            this.cboPayFrequency.YssKiloDelimiter = true;
            // 
            // cell45
            // 
            this.cell45.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell45.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell45.InnerControl = this.cboPayFrequency;
            // 
            // dtpBegin
            // 
            this.dtpBegin.BackColor = System.Drawing.Color.Transparent;
            this.dtpBegin.DateBeginChecked = true;
            this.dtpBegin.DateEndChecked = true;
            this.dtpBegin.Location = new System.Drawing.Point(110, 282);
            this.dtpBegin.Margin = new System.Windows.Forms.Padding(0);
            this.dtpBegin.Name = "dtpBegin";
            this.dtpBegin.Size = new System.Drawing.Size(121, 21);
            this.dtpBegin.TabIndex = 12;
            this.dtpBegin.Tag = this.cell55;
            this.dtpBegin.yssEnabled = true;
            this.dtpBegin.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpBegin.yssInterval = false;
            this.dtpBegin.yssLabelStr = "至";
            this.dtpBegin.yssShowOperLable = false;
            this.dtpBegin.YssShowSecond = true;
            this.dtpBegin.yssTimeControl = false;
            // 
            // cell55
            // 
            this.cell55.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell55.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell55.InnerControl = this.dtpBegin;
            // 
            // dtpEnd
            // 
            this.dtpEnd.BackColor = System.Drawing.Color.Transparent;
            this.dtpEnd.DateBeginChecked = true;
            this.dtpEnd.DateEndChecked = true;
            this.dtpEnd.Location = new System.Drawing.Point(353, 282);
            this.dtpEnd.Margin = new System.Windows.Forms.Padding(0);
            this.dtpEnd.Name = "dtpEnd";
            this.dtpEnd.Size = new System.Drawing.Size(121, 21);
            this.dtpEnd.TabIndex = 13;
            this.dtpEnd.Tag = this.cell58;
            this.dtpEnd.yssEnabled = true;
            this.dtpEnd.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpEnd.yssInterval = false;
            this.dtpEnd.yssLabelStr = "至";
            this.dtpEnd.yssShowOperLable = false;
            this.dtpEnd.YssShowSecond = true;
            this.dtpEnd.yssTimeControl = false;
            // 
            // cell58
            // 
            this.cell58.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell58.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell58.InnerControl = this.dtpEnd;
            // 
            // row3
            // 
            this.row3.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell24,
            this.cell25,
            this.cell26,
            this.cell27,
            this.cell28});
            this.row3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row3.FullRowSelected = false;
            this.row3.Height = 25;
            this.row3.Text = null;
            // 
            // cell24
            // 
            this.cell24.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell24.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell24.Text = "   证券内码：";
            // 
            // cell26
            // 
            this.cell26.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell26.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell27
            // 
            this.cell27.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell27.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell27.Text = "ISIN代码：";
            // 
            // row4
            // 
            this.row4.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell29,
            this.cell30,
            this.cell31,
            this.cell32,
            this.cell33});
            this.row4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row4.FullRowSelected = false;
            this.row4.Height = 25;
            this.row4.Text = null;
            // 
            // cell29
            // 
            this.cell29.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell29.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell29.Text = "   存放代码：";
            // 
            // cell31
            // 
            this.cell31.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell31.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell32
            // 
            this.cell32.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell32.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell32.Text = "存放名称：";
            // 
            // row5
            // 
            this.row5.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell34,
            this.cell35,
            this.cell36,
            this.cell37,
            this.cell38});
            this.row5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row5.FullRowSelected = false;
            this.row5.Height = 25;
            this.row5.Text = null;
            // 
            // cell34
            // 
            this.cell34.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell34.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell34.Text = "   证券品种：";
            // 
            // cell36
            // 
            this.cell36.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell36.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell37
            // 
            this.cell37.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell37.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell37.Text = "交易市场：";
            // 
            // row6
            // 
            this.row6.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell39,
            this.cell40,
            this.cell41,
            this.cell42,
            this.cell43});
            this.row6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row6.FullRowSelected = false;
            this.row6.Height = 25;
            this.row6.Text = null;
            // 
            // cell39
            // 
            this.cell39.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell39.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell39.Text = "   交易币种：";
            // 
            // cell41
            // 
            this.cell41.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell41.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell42
            // 
            this.cell42.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell42.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell42.Text = "计息方式：";
            // 
            // row7
            // 
            this.row7.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell44,
            this.cell45,
            this.cell46,
            this.cell47,
            this.cell48});
            this.row7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row7.FullRowSelected = false;
            this.row7.Height = 25;
            this.row7.Text = null;
            // 
            // cell44
            // 
            this.cell44.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell44.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell44.Text = "   付息频率：";
            // 
            // cell46
            // 
            this.cell46.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell46.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell47
            // 
            this.cell47.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell47.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell47.Text = "计息公式：";
            // 
            // row8
            // 
            this.row8.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell49,
            this.cell50,
            this.cell51,
            this.cell52,
            this.cell53});
            this.row8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row8.FullRowSelected = false;
            this.row8.Height = 25;
            this.row8.Text = null;
            // 
            // cell49
            // 
            this.cell49.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell49.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell49.Text = "   核算方式：";
            // 
            // cell51
            // 
            this.cell51.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell51.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell52
            // 
            this.cell52.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell52.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell52.Text = "机构名称：";
            // 
            // row9
            // 
            this.row9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row9.FullRowSelected = false;
            this.row9.GroupLineLength = 310;
            this.row9.Height = 10;
            this.row9.IsGroup = true;
            this.row9.Text = null;
            // 
            // row10
            // 
            this.row10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row10.FullRowSelected = false;
            this.row10.Height = 10;
            this.row10.Text = null;
            // 
            // row11
            // 
            this.row11.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell54,
            this.cell55,
            this.cell56,
            this.cell57,
            this.cell58});
            this.row11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row11.FullRowSelected = false;
            this.row11.Height = 25;
            this.row11.Text = null;
            // 
            // cell54
            // 
            this.cell54.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell54.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell54.Text = "   开始日期：";
            // 
            // cell56
            // 
            this.cell56.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell56.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell57
            // 
            this.cell57.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell57.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell57.Text = "结束日期：";
            // 
            // row12
            // 
            this.row12.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell59,
            this.cell60,
            this.cell62,
            this.cell63,
            this.cell64});
            this.row12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row12.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row12.FullRowSelected = false;
            this.row12.Text = null;
            // 
            // cell59
            // 
            this.cell59.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell59.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell59.Text = "   利率年化天数：";
            // 
            // cell62
            // 
            this.cell62.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell62.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell63
            // 
            this.cell63.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell63.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell63.Text = "存放天数：";
            // 
            // cell64
            // 
            this.cell64.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell64.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell64.InnerControl = this.txtPurPeriod;
            // 
            // txtPurPeriod
            // 
            // 
            // 
            // 
            this.txtPurPeriod.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.txtPurPeriod.Border.BorderColorHot = System.Drawing.Color.FromArgb(((int)(((byte)(109)))), ((int)(((byte)(129)))), ((int)(((byte)(165)))));
            this.txtPurPeriod.Border.Bottom = true;
            this.txtPurPeriod.Border.Left = true;
            this.txtPurPeriod.Border.Right = true;
            this.txtPurPeriod.Border.Top = true;
            this.txtPurPeriod.IsFillDecimal = false;
            this.txtPurPeriod.Location = new System.Drawing.Point(353, 193);
            this.txtPurPeriod.Name = "txtPurPeriod";
            this.txtPurPeriod.PrefixForeColor = System.Drawing.Color.Blue;
            this.txtPurPeriod.Size = new System.Drawing.Size(121, 21);
            this.txtPurPeriod.Sufix = "天";
            this.txtPurPeriod.SufixForeColor = System.Drawing.Color.Blue;
            this.txtPurPeriod.TabIndex = 18;
            this.txtPurPeriod.Tag = this.cell61;
            this.txtPurPeriod.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtPurPeriod.YssCaption = "存放期限";
            this.txtPurPeriod.YssLength = 18;
            this.txtPurPeriod.YssNumeric = "14, 4";
            // 
            // cell61
            // 
            this.cell61.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell61.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell61.InnerControl = this.txtPurPeriod;
            // 
            // yssSelCombox1
            // 
            this.yssSelCombox1.AddedSelItemName = "";
            // 
            // 
            // 
            this.yssSelCombox1.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.yssSelCombox1.Border.BorderColorHot = System.Drawing.Color.FromArgb(((int)(((byte)(109)))), ((int)(((byte)(129)))), ((int)(((byte)(165)))));
            this.yssSelCombox1.Border.Bottom = true;
            this.yssSelCombox1.Border.Left = true;
            this.yssSelCombox1.Border.Right = true;
            this.yssSelCombox1.Border.Top = true;
            this.yssSelCombox1.FilterCond = "";
            this.yssSelCombox1.Location = new System.Drawing.Point(0, 0);
            this.yssSelCombox1.Margin = new System.Windows.Forms.Padding(0);
            this.yssSelCombox1.MethodInfo = null;
            this.yssSelCombox1.Name = "yssSelCombox1";
            this.yssSelCombox1.QueryCond = "";
            this.yssSelCombox1.QueryType = "";
            this.yssSelCombox1.Size = new System.Drawing.Size(152, 21);
            this.yssSelCombox1.TabIndex = 12;
            this.yssSelCombox1.Text = "yssSelCombox1";
            this.yssSelCombox1.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            // 
            // row13
            // 
            this.row13.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell65,
            this.cell66,
            this.cell67,
            this.cell68,
            this.cell69});
            this.row13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row13.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row13.FullRowSelected = false;
            this.row13.Text = null;
            // 
            // cell65
            // 
            this.cell65.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell65.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell65.Text = "   品种期限：";
            // 
            // cell66
            // 
            this.cell66.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell66.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell66.InnerControl = this.cboLimit;
            // 
            // cboLimit
            // 
            this.cboLimit.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboLimit.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboLimit.Border.BorderColorHot = System.Drawing.Color.FromArgb(((int)(((byte)(109)))), ((int)(((byte)(129)))), ((int)(((byte)(165)))));
            this.cboLimit.Border.Bottom = true;
            this.cboLimit.Border.Left = true;
            this.cboLimit.Border.Right = true;
            this.cboLimit.Border.Top = true;
            this.cboLimit.DisplayName = "C_DV_NAME";
            this.cboLimit.DisplayValue = "C_DV_CODE";
            this.cboLimit.FilterCond = "";
            this.cboLimit.IsFillDecimal = false;
            this.cboLimit.Location = new System.Drawing.Point(110, 216);
            this.cboLimit.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo2.MethodName = "getDataListByKeys";
            controlMethodInfo2.MethodParams = null;
            controlMethodInfo2.MethodParamValues = new string[] {
        "SEC_ON,SEC_1D,SEC_7D,SEC_3M,SEC_6M,SEC_1Y,SEC_2Y,SEC_3Y,SEC_5Y,"};
            controlMethodInfo2.Methods = null;
            this.cboLimit.MethodInfo = controlMethodInfo2;
            this.cboLimit.Name = "cboLimit";
            this.cboLimit.Parameter = "C_DV_NAME";
            this.cboLimit.QueryCond = "";
            this.cboLimit.QueryType = "";
            this.cboLimit.RequestEveryTime = true;
            this.cboLimit.Size = new System.Drawing.Size(121, 21);
            this.cboLimit.SortColumn = "C_DV_NAME";
            this.cboLimit.TabIndex = 20;
            this.cboLimit.Tag = this.cell66;
            this.cboLimit.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboLimit.YssCaption = "品种期限";
            this.cboLimit.YssKiloDelimiter = true;
            // 
            // cell67
            // 
            this.cell67.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell67.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell68
            // 
            this.cell68.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell68.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell68.Text = "实际所属证券：";
            // 
            // cell69
            // 
            this.cell69.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell69.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell69.InnerControl = this.cboSjsszq;
            // 
            // cboSjsszq
            // 
            this.cboSjsszq.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboSjsszq.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboSjsszq.Border.BorderColorHot = System.Drawing.Color.FromArgb(((int)(((byte)(109)))), ((int)(((byte)(129)))), ((int)(((byte)(165)))));
            this.cboSjsszq.Border.Bottom = true;
            this.cboSjsszq.Border.Left = true;
            this.cboSjsszq.Border.Right = true;
            this.cboSjsszq.Border.Top = true;
            this.cboSjsszq.DisplayName = "C_SEC_CODE";
            this.cboSjsszq.DisplayValue = "C_SEC_CODE";
            this.cboSjsszq.FilterCond = "";
            this.cboSjsszq.Location = new System.Drawing.Point(353, 216);
            this.cboSjsszq.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = null;
            controlMethodInfo1.MethodParams = null;
            controlMethodInfo1.MethodParamValues = new string[] {
        "CK,DK,"};
            controlMethodInfo1.Methods = null;
            this.cboSjsszq.MethodInfo = controlMethodInfo1;
            this.cboSjsszq.Name = "cboSjsszq";
            this.cboSjsszq.Parameter = "C_SEC_CODE~C_SEC_NAME~C_SEC_MKT_CODE";
            this.cboSjsszq.QueryCond = "";
            this.cboSjsszq.QueryType = "";
            this.cboSjsszq.Size = new System.Drawing.Size(121, 21);
            this.cboSjsszq.TabIndex = 22;
            this.cboSjsszq.Tag = this.cell69;
            this.cboSjsszq.YssAssociaType = FAST.Core.Context.AssociaFAST.NULL;
            this.cboSjsszq.YssKiloDelimiter = true;
            this.cboSjsszq.YssOnBeforeLoadData += new FAST.Core.BaseControl.YssSelCombox.yssBeforeLoadData(this.cboSjsszq_YssOnBeforeLoadData);
            // 
            // row14
            // 
            this.row14.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell70,
            this.cell71,
            this.cell72,
            this.cell73,
            this.cell74});
            this.row14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row14.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row14.FullRowSelected = false;
            this.row14.Text = null;
            // 
            // cell70
            // 
            this.cell70.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell70.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell70.Text = "   银行总行：";
            // 
            // cell71
            // 
            this.cell71.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell71.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell71.InnerControl = this.bankHead;
            // 
            // bankHead
            // 
            this.bankHead.AddedSelItemName = "";
            // 
            // 
            // 
            this.bankHead.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.bankHead.Border.BorderColorHot = System.Drawing.Color.FromArgb(((int)(((byte)(109)))), ((int)(((byte)(129)))), ((int)(((byte)(165)))));
            this.bankHead.Border.Bottom = true;
            this.bankHead.Border.Left = true;
            this.bankHead.Border.Right = true;
            this.bankHead.Border.Top = true;
            this.bankHead.DisplayName = "C_ORG_NAME";
            this.bankHead.DisplayValue = "C_ORG_CODE";
            this.bankHead.FilterCond = "";
            this.bankHead.IsFillDecimal = false;
            this.bankHead.Location = new System.Drawing.Point(110, 239);
            this.bankHead.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo3.MethodName = "getDataListByKeys";
            controlMethodInfo3.MethodParams = null;
            controlMethodInfo3.MethodParamValues = new string[] {
        "SEC_ON,SEC_1D,SEC_7D,SEC_3M,SEC_6M,SEC_1Y,SEC_2Y,SEC_3Y,SEC_5Y,"};
            controlMethodInfo3.Methods = null;
            this.bankHead.MethodInfo = controlMethodInfo3;
            this.bankHead.Name = "bankHead";
            this.bankHead.Parameter = "C_ORG_CODE~C_ORG_NAME";
            this.bankHead.ParaNodeID = "C_ORG_CODE_P";
            this.bankHead.QueryCond = "where a.n_check_state = 1 and a.c_org_code_p is null ";
            this.bankHead.QueryType = "";
            this.bankHead.RequestEveryTime = true;
            this.bankHead.Size = new System.Drawing.Size(121, 21);
            this.bankHead.SortColumn = "C_ORG_NAME";
            this.bankHead.TabIndex = 21;
            this.bankHead.Tag = this.cell71;
            this.bankHead.YssAssociaType = YssInformation.Support.Context.AssociaType.base_organ;
            this.bankHead.YssCaption = "银行总行";
            this.bankHead.YssDataSrc = FAST.Core.Context.ClsEnums.DataSrc.SrcDB;
            // 
            // cell72
            // 
            this.cell72.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell72.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell73
            // 
            this.cell73.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell73.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell73.Text = "银行支行：";
            // 
            // cell74
            // 
            this.cell74.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell74.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell74.InnerControl = this.bankBranch;
            // 
            // bankBranch
            // 
            this.bankBranch.AddedSelItemName = "";
            // 
            // 
            // 
            this.bankBranch.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.bankBranch.Border.BorderColorHot = System.Drawing.Color.FromArgb(((int)(((byte)(109)))), ((int)(((byte)(129)))), ((int)(((byte)(165)))));
            this.bankBranch.Border.Bottom = true;
            this.bankBranch.Border.Left = true;
            this.bankBranch.Border.Right = true;
            this.bankBranch.Border.Top = true;
            this.bankBranch.DisplayName = "C_DV_NAME";
            this.bankBranch.FilterCond = "";
            this.bankBranch.IsFillDecimal = false;
            this.bankBranch.Location = new System.Drawing.Point(353, 239);
            this.bankBranch.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo4.MethodName = "getDataListByKeys";
            controlMethodInfo4.MethodParams = null;
            controlMethodInfo4.MethodParamValues = new string[] {
        "SEC_ON,SEC_1D,SEC_7D,SEC_3M,SEC_6M,SEC_1Y,SEC_2Y,SEC_3Y,SEC_5Y,"};
            controlMethodInfo4.Methods = null;
            this.bankBranch.MethodInfo = controlMethodInfo4;
            this.bankBranch.Name = "bankBranch";
            this.bankBranch.QueryCond = "";
            this.bankBranch.QueryType = "CacheType";
            this.bankBranch.RequestEveryTime = true;
            this.bankBranch.SelectMode = Yss.KRichEx.AutoFilter.SelectMode.Any;
            this.bankBranch.Size = new System.Drawing.Size(121, 21);
            this.bankBranch.SortColumn = "C_DV_NAME";
            this.bankBranch.TabIndex = 20;
            this.bankBranch.Tag = this.cell74;
            this.bankBranch.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            // 
            // Frm_SEC_BASE_CK_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(493, 387);
            this.Controls.Add(this.yssSelCombox1);
            this.DoubleBuffered = true;
            this.Name = "Frm_SEC_BASE_CK_S";
            this.Text = "存放品种信息";
            this.YssSetStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.Load += new System.EventHandler(this.Frm_SEC_BASE_CK_S_Load);
            this.YssOnBeforeSaveClick += new FAST.Core.CRUD.Form.FrmBaseSet.BeforeSaveClick(this.Frm_SEC_BASE_CK_S_YssOnBeforeSaveClick);
            this.Controls.SetChildIndex(this.yssSelCombox1, 0);
            this.Controls.SetChildIndex(this.yssPanel1, 0);
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
        private Yss.KRichEx.YssTextBox txtSavingCode;
        private Yss.KRichEx.YssTextBox txtSavingName;
        private FAST.Core.BaseControl.YssSelCombox cboCury;
        private FAST.Core.BaseControl.YssSelCombox cboMarket;
        private FAST.Core.BaseControl.YssSelCombox cboSavingType;
        private Yss.KTable.Models.Cell cell18;
        private Yss.KTable.Models.Cell cell19;
        private Yss.KTable.Models.Cell cell20;
        private FAST.Core.BaseControl.YssSelCombox cboSavingLimit;
        private Yss.KRichEx.YssTextBox txtSecCode;
        private Yss.KTable.Models.Row row1;
        private Yss.KRichEx.IntegerInputEx iniRateYearDays;
        private Yss.KTable.Models.Cell cell21;
        private Yss.KTable.Models.Cell cell22;
        private Yss.KTable.Models.Cell cell23;
        private Yss.KTable.Models.Row row2;
        private Yss.KRichEx.YssTextBox txtIsinCode;
        private FAST.Core.BaseControl.YssSelCombox cboCountMode;
        private FAST.Core.BaseControl.YssSelCombox cboCountFormula;
        private FAST.Core.BaseControl.YssSelCombox cboPayFrequency;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpBegin;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpEnd;
        private Yss.KTable.Models.Row row3;
        private Yss.KTable.Models.Cell cell24;
        private Yss.KTable.Models.Cell cell25;
        private Yss.KTable.Models.Cell cell26;
        private Yss.KTable.Models.Cell cell27;
        private Yss.KTable.Models.Cell cell28;
        private Yss.KTable.Models.Row row4;
        private Yss.KTable.Models.Cell cell29;
        private Yss.KTable.Models.Cell cell30;
        private Yss.KTable.Models.Cell cell31;
        private Yss.KTable.Models.Cell cell32;
        private Yss.KTable.Models.Cell cell33;
        private Yss.KTable.Models.Row row5;
        private Yss.KTable.Models.Cell cell34;
        private Yss.KTable.Models.Cell cell35;
        private Yss.KTable.Models.Cell cell36;
        private Yss.KTable.Models.Cell cell37;
        private Yss.KTable.Models.Cell cell38;
        private Yss.KTable.Models.Row row6;
        private Yss.KTable.Models.Cell cell39;
        private Yss.KTable.Models.Cell cell40;
        private Yss.KTable.Models.Cell cell41;
        private Yss.KTable.Models.Cell cell42;
        private Yss.KTable.Models.Cell cell43;
        private Yss.KTable.Models.Row row7;
        private Yss.KTable.Models.Cell cell44;
        private Yss.KTable.Models.Cell cell45;
        private Yss.KTable.Models.Cell cell46;
        private Yss.KTable.Models.Cell cell47;
        private Yss.KTable.Models.Cell cell48;
        private Yss.KTable.Models.Row row8;
        private Yss.KTable.Models.Cell cell49;
        private Yss.KTable.Models.Cell cell50;
        private Yss.KTable.Models.Cell cell51;
        private Yss.KTable.Models.Cell cell52;
        private Yss.KTable.Models.Cell cell53;
        private Yss.KTable.Models.Row row9;
        private Yss.KTable.Models.Row row10;
        private Yss.KTable.Models.Row row11;
        private Yss.KTable.Models.Cell cell54;
        private Yss.KTable.Models.Cell cell55;
        private Yss.KTable.Models.Cell cell56;
        private Yss.KTable.Models.Cell cell57;
        private Yss.KTable.Models.Cell cell58;
        private Yss.KTable.Models.Row row12;
        private Yss.KTable.Models.Cell cell60;
        private Yss.KTable.Models.Cell cell59;
        protected FAST.Core.BaseControl.YssSelCombox cboIssureMode;
        private Yss.KRichEx.ImprovedTextBox txtPurPeriod;
        private Yss.KTable.Models.Cell cell61;
        private Yss.KTable.Models.Cell cell62;
        private Yss.KTable.Models.Cell cell63;
        private Yss.KTable.Models.Cell cell64;
        private FAST.Core.BaseControl.YssSelCombox yssSelCombox1;
        private Yss.KTable.Models.Row row13;
        private Yss.KTable.Models.Cell cell65;
        private Yss.KTable.Models.Cell cell66;
        private FAST.Core.BaseControl.YssSelCombox cboLimit;
        private FAST.Core.BaseControl.PenetrationComboBox cboOrgan;
        private FAST.Core.BaseControl.YssSelCombox cboSjsszq;
        private Yss.KTable.Models.Cell cell69;
        private Yss.KTable.Models.Cell cell67;
        private Yss.KTable.Models.Cell cell68;
        private Yss.KTable.Models.Row row14;
        private Yss.KTable.Models.Cell cell70;
        private Yss.KTable.Models.Cell cell71;
        private Yss.KTable.Models.Cell cell72;
        private Yss.KTable.Models.Cell cell73;
        private Yss.KTable.Models.Cell cell74;
        private FAST.Core.BaseControl.YssSelCombox bankHead;
        private FAST.Core.BaseControl.YssSelCombox bankBranch;
    }
}