using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;


using FAST.Core.Context;

using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace YssSecInformation.Mp.SecEq.Form
{
    partial class Frm_SEC_EQU_YFX_S
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
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo2 = new FAST.Core.BaseControl.ControlMethodInfo();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_SEC_EQU_YFX_S));
            this.row1 = new Yss.KTable.Models.Row();
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.row2 = new Yss.KTable.Models.Row();
            this.row3 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.txtIssueCode = new Yss.KRichEx.YssTextBox();
            this.row4 = new Yss.KTable.Models.Row();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.cboBidType = new FAST.Core.BaseControl.YssSelCombox();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.txtBenchMarkReturn = new Yss.KRichEx.ImprovedTextBox();
            this.row5 = new Yss.KTable.Models.Row();
            this.row6 = new Yss.KTable.Models.Row();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.dtpStartDate = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell13 = new Yss.KTable.Models.Cell();
            this.cell14 = new Yss.KTable.Models.Cell();
            this.cell15 = new Yss.KTable.Models.Cell();
            this.dtpEndDate = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.yssDateTimeInterval1 = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.row8 = new Yss.KTable.Models.Row();
            this.cell21 = new Yss.KTable.Models.Cell();
            this.cell22 = new Yss.KTable.Models.Cell();
            this.dtpSettDate = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell23 = new Yss.KTable.Models.Cell();
            this.cell24 = new Yss.KTable.Models.Cell();
            this.cell25 = new Yss.KTable.Models.Cell();
            this.cboSecurity = new FAST.Core.BaseControl.PenetrationComboBox();
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
            this.tbMain.Border.BorderColor = System.Drawing.Color.Gray;
            this.tbMain.Border.Bottom = false;
            this.tbMain.Border.Left = false;
            this.tbMain.Border.Right = false;
            this.tbMain.Border.Top = false;
            this.tbMain.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.tbMain.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.column1,
            this.column2,
            this.column3,
            this.column4,
            this.column5});
            this.tbMain.Controls.Add(this.cboSecurity);
            this.tbMain.Controls.Add(this.dtpSettDate);
            this.tbMain.Controls.Add(this.txtBenchMarkReturn);
            this.tbMain.Controls.Add(this.dtpEndDate);
            this.tbMain.Controls.Add(this.txtIssueCode);
            this.tbMain.Controls.Add(this.dtpStartDate);
            this.tbMain.Controls.Add(this.cboBidType);
            this.tbMain.GridLineColor = System.Drawing.Color.Red;
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3,
            this.row4,
            this.row5,
            this.row6,
            this.row8});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(495, 157);
            // 
            // btnBar
            // 
            this.btnBar.Size = new System.Drawing.Size(495, 30);
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
            this.stBarBottom.Location = new System.Drawing.Point(0, 187);
            this.stBarBottom.Size = new System.Drawing.Size(495, 25);
            // 
            // pnlMain
            // 
            this.pnlMain.Size = new System.Drawing.Size(495, 212);
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
            this.yssPanel1.Size = new System.Drawing.Size(495, 212);
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row1.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row1.FullRowSelected = false;
            this.row1.GroupLineLength = 310;
            this.row1.GroupPosition = 13;
            this.row1.Height = 33;
            this.row1.IsGroup = true;
            this.row1.Text = "基本信息";
            // 
            // column1
            // 
            this.column1.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.Width = 111;
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
            // row2
            // 
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row2.FullRowSelected = false;
            this.row2.GroupLineLength = 310;
            this.row2.GroupPosition = 13;
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
            this.row3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row3.FullRowSelected = false;
            this.row3.Text = null;
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
            this.cell2.InnerControl = this.cboSecurity;
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
            this.cell4.Text = "   发行代码：";
            // 
            // cell5
            // 
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.txtIssueCode;
            // 
            // txtIssueCode
            // 
            this.txtIssueCode.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.txtIssueCode.Border.Class = "TextBoxBorder";
            this.txtIssueCode.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtIssueCode.Location = new System.Drawing.Point(354, 43);
            this.txtIssueCode.Name = "txtIssueCode";
            this.txtIssueCode.Size = new System.Drawing.Size(121, 21);
            this.txtIssueCode.TabIndex = 12;
            this.txtIssueCode.Tag = this.cell5;
            this.txtIssueCode.YssCaption = "发行代码";
            this.txtIssueCode.YssIsMust = true;
            // 
            // row4
            // 
            this.row4.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell6,
            this.cell7,
            this.cell8,
            this.cell9,
            this.cell10});
            this.row4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row4.FullRowSelected = false;
            this.row4.Text = null;
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.Text = "   招标类型：";
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.InnerControl = this.cboBidType;
            // 
            // cboBidType
            // 
            this.cboBidType.AddedSelItemName = "";
            this.cboBidType.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboBidType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboBidType.Border.Bottom = true;
            this.cboBidType.Border.Left = true;
            this.cboBidType.Border.Right = true;
            this.cboBidType.Border.Top = true;
            this.cboBidType.ClassName = "";
            this.cboBidType.DllName = "dll";
            this.cboBidType.FilterCond = "";
            this.cboBidType.IsFillDecimal = false;
            this.cboBidType.Location = new System.Drawing.Point(111, 66);
            this.cboBidType.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataListByTypes";
            controlMethodInfo1.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo1.MethodParams")));
            controlMethodInfo1.MethodParamValues = new string[] {
        "BID_TYPE,"};
            controlMethodInfo1.Methods = null;
            this.cboBidType.MethodInfo = controlMethodInfo1;
            this.cboBidType.Name = "cboBidType";
            this.cboBidType.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconIndent;
            this.cboBidType.QueryCond = "BID_TYPE";
            this.cboBidType.QueryType = "CacheType";
            this.cboBidType.Size = new System.Drawing.Size(121, 21);
            this.cboBidType.TabIndex = 12;
            this.cboBidType.Tag = this.cell7;
            this.cboBidType.UseCustomerParameter = false;
            this.cboBidType.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboBidType.YssCaption = "招标类型";
            this.cboBidType.YssIsMust = true;
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
            this.cell9.Text = "   基准收益：";
            // 
            // cell10
            // 
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.InnerControl = this.txtBenchMarkReturn;
            // 
            // txtBenchMarkReturn
            // 
            this.txtBenchMarkReturn.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.txtBenchMarkReturn.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.txtBenchMarkReturn.Border.Bottom = true;
            this.txtBenchMarkReturn.Border.Left = true;
            this.txtBenchMarkReturn.Border.Right = true;
            this.txtBenchMarkReturn.Border.Top = true;
            this.txtBenchMarkReturn.IsFillDecimal = false;
            this.txtBenchMarkReturn.Location = new System.Drawing.Point(354, 66);
            this.txtBenchMarkReturn.Name = "txtBenchMarkReturn";
            this.txtBenchMarkReturn.PrefixForeColor = System.Drawing.SystemColors.WindowText;
            this.txtBenchMarkReturn.Size = new System.Drawing.Size(121, 21);
            this.txtBenchMarkReturn.Sufix = "%";
            this.txtBenchMarkReturn.SufixForeColor = System.Drawing.SystemColors.WindowText;
            this.txtBenchMarkReturn.TabIndex = 27;
            this.txtBenchMarkReturn.Tag = this.cell10;
            this.txtBenchMarkReturn.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtBenchMarkReturn.Visible = false;
            this.txtBenchMarkReturn.YssCaption = "基准收益";
            this.txtBenchMarkReturn.YssIsMust = true;
            this.txtBenchMarkReturn.YssKiloDelimiter = true;
            this.txtBenchMarkReturn.YssLength = 30;
            this.txtBenchMarkReturn.YssNumeric = "15, 15";
            // 
            // row5
            // 
            this.row5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row5.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row5.FullRowSelected = false;
            this.row5.Height = 10;
            this.row5.IsGroup = true;
            this.row5.Text = null;
            // 
            // row6
            // 
            this.row6.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell11,
            this.cell12,
            this.cell13,
            this.cell14,
            this.cell15});
            this.row6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row6.FullRowSelected = false;
            this.row6.Text = null;
            // 
            // cell11
            // 
            this.cell11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell11.Text = "   发行日期：";
            // 
            // cell12
            // 
            this.cell12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell12.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell12.InnerControl = this.dtpStartDate;
            // 
            // dtpStartDate
            // 
            this.dtpStartDate.BackColor = System.Drawing.Color.Transparent;
            this.dtpStartDate.BusinessDate = true;
            this.dtpStartDate.DateBeginChecked = true;
            this.dtpStartDate.DateEndChecked = true;
            this.dtpStartDate.Location = new System.Drawing.Point(111, 99);
            this.dtpStartDate.Margin = new System.Windows.Forms.Padding(0);
            this.dtpStartDate.Name = "dtpStartDate";
            this.dtpStartDate.Size = new System.Drawing.Size(121, 21);
            this.dtpStartDate.TabIndex = 12;
            this.dtpStartDate.Tag = this.cell12;
            this.dtpStartDate.yssEnabled = true;
            this.dtpStartDate.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpStartDate.yssInterval = false;
            this.dtpStartDate.yssLabelStr = "至";
            this.dtpStartDate.yssShowCheckBox = false;
            this.dtpStartDate.yssShowOperLable = false;
            this.dtpStartDate.YssShowSecond = true;
            this.dtpStartDate.yssTimeControl = false;
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
            this.cell14.Text = "   结束日期：";
            // 
            // cell15
            // 
            this.cell15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell15.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell15.InnerControl = this.dtpEndDate;
            // 
            // dtpEndDate
            // 
            this.dtpEndDate.BackColor = System.Drawing.Color.Transparent;
            this.dtpEndDate.BusinessDate = true;
            this.dtpEndDate.DateBeginChecked = true;
            this.dtpEndDate.DateEndChecked = true;
            this.dtpEndDate.Location = new System.Drawing.Point(354, 99);
            this.dtpEndDate.Margin = new System.Windows.Forms.Padding(0);
            this.dtpEndDate.Name = "dtpEndDate";
            this.dtpEndDate.Size = new System.Drawing.Size(121, 21);
            this.dtpEndDate.TabIndex = 12;
            this.dtpEndDate.Tag = this.cell15;
            this.dtpEndDate.yssEnabled = true;
            this.dtpEndDate.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpEndDate.yssInterval = false;
            this.dtpEndDate.yssLabelStr = "至";
            this.dtpEndDate.yssShowCheckBox = false;
            this.dtpEndDate.yssShowOperLable = false;
            this.dtpEndDate.YssShowSecond = true;
            this.dtpEndDate.yssTimeControl = false;
            // 
            // yssDateTimeInterval1
            // 
            this.yssDateTimeInterval1.BackColor = System.Drawing.Color.Transparent;
            this.yssDateTimeInterval1.DateBeginChecked = true;
            this.yssDateTimeInterval1.DateEndChecked = true;
            this.yssDateTimeInterval1.Location = new System.Drawing.Point(111, 99);
            this.yssDateTimeInterval1.Margin = new System.Windows.Forms.Padding(0);
            this.yssDateTimeInterval1.Name = "yssDateTimeInterval1";
            this.yssDateTimeInterval1.Size = new System.Drawing.Size(121, 21);
            this.yssDateTimeInterval1.TabIndex = 13;
            this.yssDateTimeInterval1.Tag = this.cell12;
            this.yssDateTimeInterval1.yssEnabled = true;
            this.yssDateTimeInterval1.yssFormatDateStr = "yyyy-MM-dd";
            this.yssDateTimeInterval1.yssInterval = false;
            this.yssDateTimeInterval1.yssLabelStr = "至";
            this.yssDateTimeInterval1.yssShowCheckBox = false;
            this.yssDateTimeInterval1.yssShowOperLable = false;
            this.yssDateTimeInterval1.YssShowSecond = true;
            this.yssDateTimeInterval1.yssTimeControl = false;
            // 
            // row8
            // 
            this.row8.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell21,
            this.cell22,
            this.cell23,
            this.cell24,
            this.cell25});
            this.row8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row8.FullRowSelected = false;
            this.row8.Text = null;
            // 
            // cell21
            // 
            this.cell21.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell21.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell21.Text = "   交收日期：";
            // 
            // cell22
            // 
            this.cell22.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell22.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell22.InnerControl = this.dtpSettDate;
            // 
            // dtpSettDate
            // 
            this.dtpSettDate.BackColor = System.Drawing.Color.Transparent;
            this.dtpSettDate.BusinessDate = true;
            this.dtpSettDate.DateBeginChecked = true;
            this.dtpSettDate.DateEndChecked = true;
            this.dtpSettDate.Location = new System.Drawing.Point(111, 122);
            this.dtpSettDate.Margin = new System.Windows.Forms.Padding(0);
            this.dtpSettDate.Name = "dtpSettDate";
            this.dtpSettDate.Size = new System.Drawing.Size(121, 21);
            this.dtpSettDate.TabIndex = 14;
            this.dtpSettDate.Tag = this.cell22;
            this.dtpSettDate.yssEnabled = true;
            this.dtpSettDate.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpSettDate.yssInterval = false;
            this.dtpSettDate.yssLabelStr = "至";
            this.dtpSettDate.yssShowCheckBox = false;
            this.dtpSettDate.yssShowOperLable = false;
            this.dtpSettDate.YssShowSecond = true;
            this.dtpSettDate.yssTimeControl = false;
            // 
            // cell23
            // 
            this.cell23.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell23.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell24
            // 
            this.cell24.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell24.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell25
            // 
            this.cell25.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell25.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cboSecurity
            // 
            this.cboSecurity.AddedSelItemName = "";
            this.cboSecurity.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboSecurity.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboSecurity.Border.Bottom = true;
            this.cboSecurity.Border.Left = true;
            this.cboSecurity.Border.Right = true;
            this.cboSecurity.Border.Top = true;
            this.cboSecurity.ClassName = "";
            this.cboSecurity.DisplayStyle = Yss.KRichEx.AutoFilter.DisplayStyle.Modal;
            this.cboSecurity.DllName = "dll";
            this.cboSecurity.ExpandButtonToolTip = "数据调整……";
            this.cboSecurity.FilterCond = "";
            this.cboSecurity.FunCode = "sv_sec";
            this.cboSecurity.Location = new System.Drawing.Point(111, 43);
            this.cboSecurity.Margin = new System.Windows.Forms.Padding(0);

            controlMethodInfo2.MethodName = "getDataListByTypes";
            controlMethodInfo2.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo2.MethodParams")));
            controlMethodInfo2.MethodParamValues = new string[] {
        "GP,ZQ,JJ"};
            controlMethodInfo2.Methods = null;
            this.cboSecurity.MethodInfo = controlMethodInfo2;
            
            this.cboSecurity.Name = "cboSecurity";
            this.cboSecurity.QueryCond = "";
            this.cboSecurity.QueryType = "";
            this.cboSecurity.Size = new System.Drawing.Size(121, 21);
            this.cboSecurity.TabIndex = 28;
            this.cboSecurity.Tag = this.cell2;
            this.cboSecurity.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Value_Name;
            this.cboSecurity.UseCustomerParameter = false;
            this.cboSecurity.YssAssociaType = YssSecInformation.Support.Context.AssociaType.sv_sec;
            this.cboSecurity.YssCaption = "证券预发行交易证券";
            this.cboSecurity.YssIsMust = true;
            // 
            // Frm_SEC_EQU_YFX_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.ClientSize = new System.Drawing.Size(495, 212);
            this.Name = "Frm_SEC_EQU_YFX_S";
            this.Text = "证券预发行信息";
            this.YssSetStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssOnBeforeSaveClick += new FrmBaseSet.BeforeSaveClick(this.Frm_SEC_EQU_YFX_S_YssOnBeforeSaveClick);
            this.tbMain.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private Yss.KTable.Models.Row row1;
        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
        private Yss.KTable.Models.Column column3;
        private Yss.KTable.Models.Column column4;
        private Yss.KTable.Models.Column column5;
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
        private Yss.KTable.Models.Row row5;
        private Yss.KTable.Models.Row row6;
        private Yss.KTable.Models.Cell cell11;
        private Yss.KTable.Models.Cell cell12;
        private Yss.KTable.Models.Cell cell13;
        private Yss.KTable.Models.Cell cell14;
        private Yss.KTable.Models.Cell cell15;
        private Yss.KRichEx.YssTextBox txtIssueCode;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpStartDate;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpEndDate;
        protected Yss.KRichEx.ImprovedTextBox txtBenchMarkReturn;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpSettDate;
        private Yss.KTable.Models.Cell cell22;
        private Yss.KTable.Models.Row row8;
        private Yss.KTable.Models.Cell cell21;
        private Yss.KTable.Models.Cell cell23;
        private Yss.KTable.Models.Cell cell24;
        private Yss.KTable.Models.Cell cell25;
        private FAST.Core.BaseControl.YssDateTimeInterval yssDateTimeInterval1;
        protected FAST.Core.BaseControl.YssSelCombox cboBidType;
        private FAST.Core.BaseControl.PenetrationComboBox cboSecurity;
    }
}