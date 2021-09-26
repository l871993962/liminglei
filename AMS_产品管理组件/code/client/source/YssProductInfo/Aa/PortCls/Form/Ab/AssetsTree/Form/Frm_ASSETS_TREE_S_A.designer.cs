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

namespace YssProductInfo.Ab.AssetsTree.Form
{
    partial class Frm_ASSETS_TREE_S_A
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_ASSETS_TREE_S_A));
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo2 = new FAST.Core.BaseControl.ControlMethodInfo();
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
            this.txtSectorCode = new Yss.KRichEx.YssTextBox();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.txtSectorName = new Yss.KRichEx.YssTextBox();
            this.row4 = new Yss.KTable.Models.Row();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.cboSector = new FAST.Core.BaseControl.YssSelCombox();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.cboFlGZ = new FAST.Core.BaseControl.YssSelCombox();
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
            this.tbMain.Controls.Add(this.cboSector);
            this.tbMain.Controls.Add(this.cboFlGZ);
            this.tbMain.Controls.Add(this.txtSectorCode);
            this.tbMain.Controls.Add(this.txtSectorName);
            // 
            // 
            // 
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3,
            this.row4});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(493, 120);
            // 
            // stBarBottom
            // 
            this.stBarBottom.Location = new System.Drawing.Point(0, 150);
            // 
            // panelExLine
            // 
            // 
            // pnlMain
            // 
            this.pnlMain.Size = new System.Drawing.Size(493, 173);
            //this.pnlMain.Style.Alignment = System.Drawing.StringAlignment.Center;
            //this.pnlMain.Style.BackColor1.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.DockSiteBackColor;
            //this.pnlMain.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            //this.pnlMain.Style.BorderColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBorder;
            //this.pnlMain.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            //this.pnlMain.Style.GradientAngle = 90;
            // 
            // yssPanel1
            // 
            this.yssPanel1.Size = new System.Drawing.Size(493, 173);
            // 
            // hpAssist
            // 
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
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row2.ForeColor = System.Drawing.Color.Black;
            this.row2.FullRowSelected = false;
            this.row2.GroupLineLength = 310;
            this.row2.GroupPosition = 13;
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
            this.row3.ForeColor = System.Drawing.Color.Black;
            this.row3.FullRowSelected = false;
            this.row3.GroupLineLength = 310;
            this.row3.GroupPosition = 13;
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
            this.cell1.Text = "   结构代码：";
            this.cell1.ToolTip = null;
            // 
            // cell2
            // 
            this.cell2.BackColor = System.Drawing.Color.Empty;
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.txtSectorCode;
            this.cell2.Key = null;
            this.cell2.ToolTip = null;
            // 
            // txtSectorCode
            // 
            this.txtSectorCode.Border.Class = "TextBoxBorder";
            this.txtSectorCode.Location = new System.Drawing.Point(110, 43);
            this.txtSectorCode.Name = "txtSectorCode";
            this.txtSectorCode.Size = new System.Drawing.Size(120, 21);
            this.txtSectorCode.TabIndex = 1;
            this.txtSectorCode.Tag = this.cell2;
            this.txtSectorCode.Value = "";
            this.txtSectorCode.YssCaption = "结构代码";
            this.txtSectorCode.YssIsMust = true;
            this.txtSectorCode.YssNumeric = "";
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
            this.cell4.Text = "结构名称：";
            this.cell4.ToolTip = null;
            // 
            // cell5
            // 
            this.cell5.BackColor = System.Drawing.Color.Empty;
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.txtSectorName;
            this.cell5.Key = null;
            this.cell5.ToolTip = null;
            // 
            // txtSectorName
            // 
            this.txtSectorName.Border.Class = "TextBoxBorder";
            this.txtSectorName.Location = new System.Drawing.Point(353, 43);
            this.txtSectorName.Name = "txtSectorName";
            this.txtSectorName.Size = new System.Drawing.Size(120, 21);
            this.txtSectorName.TabIndex = 2;
            this.txtSectorName.Tag = this.cell5;
            this.txtSectorName.Value = "";
            this.txtSectorName.YssCaption = "结构名称";
            this.txtSectorName.YssIsMust = true;
            this.txtSectorName.YssNumeric = "";
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
            this.row4.ForeColor = System.Drawing.Color.Black;
            this.row4.FullRowSelected = false;
            this.row4.GroupLineLength = 310;
            this.row4.GroupPosition = 13;
            this.row4.Height = 25;
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
            this.cell6.Text = "   上级结构：";
            this.cell6.ToolTip = null;
            // 
            // cell7
            // 
            this.cell7.BackColor = System.Drawing.Color.Empty;
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.InnerControl = this.cboSector;
            this.cell7.Key = null;
            this.cell7.ToolTip = null;
            // 
            // cboSector
            // 
            this.cboSector.AddedSelItemName = "";
            this.cboSector.BackColor = System.Drawing.Color.White;
            this.cboSector.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.cboSector.ClassName = "";
            this.cboSector.DisplayName = "C_TR_NAME";
            this.cboSector.DisplayValue = "C_TR_CODE";
            this.cboSector.DllName = "YssControls.dll";
            this.cboSector.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDown;
            this.cboSector.FilterCond = "";
            this.cboSector.IsFillDecimal = false;
            this.cboSector.IsFocused = false;
            this.cboSector.KTableTree = true;
            this.cboSector.Location = new System.Drawing.Point(110, 68);
            this.cboSector.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataList";
            controlMethodInfo1.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo1.MethodParams")));
            controlMethodInfo1.MethodParamValues = null;
            controlMethodInfo1.Methods = null;
            this.cboSector.MethodInfo = controlMethodInfo1;
            this.cboSector.Name = "cboSector";
            this.cboSector.NodeID = "C_TR_CODE";
            this.cboSector.Padding = new System.Windows.Forms.Padding(1, 2, 1, 3);
            this.cboSector.Parameter = "C_TR_CODE~C_TR_NAME~C_TR_CODE_P";
            this.cboSector.ParaNodeID = "C_TR_CODE_P";
            this.cboSector.PasswordChar = '\0';
            this.cboSector.PopupTitle = "数据筛选";
            // 
            // 
            // 
            this.cboSector.QueryCond = "";
            this.cboSector.QueryType = "";
            this.cboSector.RequestEveryTime = false;
            this.cboSector.SelectMode = Yss.KRichEx.AutoFilter.SelectMode.Any;
            this.cboSector.ShowCheckBox = false;
            this.cboSector.ShowColumnHeader = false;
            this.cboSector.Size = new System.Drawing.Size(120, 21);
            // 
            // 
            // 
            this.cboSector.TabIndex = 6;
            this.cboSector.Tag = this.cell7;
            this.cboSector.TextAlign = System.Windows.Forms.HorizontalAlignment.Left;
            this.cboSector.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
            this.cboSector.TipText = "";
            this.cboSector.TriggerTextLength = 1;
            this.cboSector.UseErrorTip = true;
            this.cboSector.YssAssociaType = FAST.Core.Context.AssociaFAST.NULL;
            this.cboSector.YssCaption = "";
            this.cboSector.YssDataSrc = FAST.Core.Context.ClsEnums.DataSrc.SrcDB;
            this.cboSector.YssIsMust = false;
            this.cboSector.YssLength = 20;
            this.cboSector.YssNumeric = "";
            this.cboSector.YssReadOnly = false;
            this.cboSector.YssShowButton = true;
            this.cboSector.BeforeDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(this.cboSector_BeforeDropDownClick);
            this.cboSector.YssOnBeforeLoadData += new FAST.Core.BaseControl.YssSelCombox.yssBeforeLoadData(this.cboSector_YssOnBeforeLoadData);
            // 
            // cell8
            // 
            this.cell8.BackColor = System.Drawing.Color.Empty;
            this.cell8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.cell8.ForeColor = System.Drawing.Color.Black;
            this.cell8.Key = null;
            this.cell8.ToolTip = null;
            // 
            // cell9
            // 
            this.cell9.BackColor = System.Drawing.Color.Empty;
            this.cell9.Font = new System.Drawing.Font("宋体", 9F);
            this.cell9.ForeColor = System.Drawing.Color.Black;
            this.cell9.Key = null;
            this.cell9.Text = "分类规则：";
            this.cell9.ToolTip = null;
            // 
            // cell10
            // 
            this.cell10.BackColor = System.Drawing.Color.Empty;
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.cell10.ForeColor = System.Drawing.Color.Black;
            this.cell10.InnerControl = this.cboFlGZ;
            this.cell10.Key = null;
            this.cell10.ToolTip = null;
            // 
            // cboFlGZ
            // 
            this.cboFlGZ.AddedSelItemName = "";
            this.cboFlGZ.BackColor = System.Drawing.Color.White;
            this.cboFlGZ.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.cboFlGZ.ClassName = "";
            this.cboFlGZ.DisplayName = "";
            this.cboFlGZ.DisplayValue = "";
            this.cboFlGZ.DllName = "YssControls.dll";
            this.cboFlGZ.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDown;
            this.cboFlGZ.FilterCond = "";
            this.cboFlGZ.IsFillDecimal = false;
            this.cboFlGZ.IsFocused = false;
            this.cboFlGZ.KTableTree = false;
            this.cboFlGZ.Location = new System.Drawing.Point(353, 68);
            this.cboFlGZ.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo2.MethodName = "getDataListByTypes";
            controlMethodInfo2.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo2.MethodParams")));
            controlMethodInfo2.MethodParamValues = new string[] {
        "ASSET_TREE_TYPE,"};
            controlMethodInfo2.Methods = new string[] {
        "getKeyConvertMap",
        "getDataByCode",
        "getDataList",
        "getDataListRes",
        "getDataListByTypes",
        "getQueryResByTypes",
        "getDataListByKeys",
        "getQueryResByKeys"};
            this.cboFlGZ.MethodInfo = controlMethodInfo2;
            this.cboFlGZ.Name = "cboFlGZ";
            this.cboFlGZ.NodeID = "";
            this.cboFlGZ.Padding = new System.Windows.Forms.Padding(1, 2, 1, 3);
            this.cboFlGZ.Parameter = "";
            this.cboFlGZ.ParaNodeID = "";
            this.cboFlGZ.PasswordChar = '\0';
            this.cboFlGZ.PopupTitle = "数据筛选";
            // 
            // 
            // 
            this.cboFlGZ.QueryCond = "ASSET_TREE_TYPE";
            this.cboFlGZ.QueryType = "CacheType";
            this.cboFlGZ.RequestEveryTime = false;
            this.cboFlGZ.ShowCheckBox = false;
            this.cboFlGZ.ShowColumnHeader = false;
            this.cboFlGZ.Size = new System.Drawing.Size(121, 21);
            // 
            // 
            // 
            this.cboFlGZ.TabIndex = 5;
            this.cboFlGZ.Tag = this.cell10;
            this.cboFlGZ.TextAlign = System.Windows.Forms.HorizontalAlignment.Left;
            this.cboFlGZ.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
            this.cboFlGZ.TipText = "";
            this.cboFlGZ.TriggerTextLength = 1;
            this.cboFlGZ.UseErrorTip = true;
            this.cboFlGZ.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboFlGZ.YssCaption = "分类规则";
            this.cboFlGZ.YssIsMust = true;
            this.cboFlGZ.YssLength = 20;
            this.cboFlGZ.YssNumeric = "";
            this.cboFlGZ.YssReadOnly = false;
            this.cboFlGZ.YssShowButton = true;
            // 
            // Frm_ASSETS_TREE_S_A
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(493, 173);
            this.Name = "Frm_ASSETS_TREE_S_A";
            this.Text = "板块信息设置";
            this.YssSetStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssOnBeforeDelClick += new FrmBaseSet.BeforeDelClick(this.Frm_ASSETS_TREE_S_A_YssOnBeforeDelClick);
            this.Load += new System.EventHandler(this.Frm_ASSETS_TREE_S_A_Load);
            this.YssOnBeforeSaveClick += new FrmBaseSet.BeforeSaveClick(this.Frm_ASSETS_TREE_S_A_YssOnBeforeSaveClick);
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
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Cell cell2;
        private Yss.KTable.Models.Cell cell3;
        private Yss.KTable.Models.Cell cell4;
        private Yss.KTable.Models.Cell cell5;
        private Yss.KTable.Models.Cell cell6;
        private Yss.KTable.Models.Cell cell7;
        private Yss.KRichEx.YssTextBox txtSectorName;
        private Yss.KRichEx.YssTextBox txtSectorCode;
        private Yss.KTable.Models.Cell cell8;
        private Yss.KTable.Models.Cell cell9;
        private Yss.KTable.Models.Cell cell10;
        private FAST.Core.BaseControl.YssSelCombox cboFlGZ;
        private FAST.Core.BaseControl.YssSelCombox cboSector;
    }
}