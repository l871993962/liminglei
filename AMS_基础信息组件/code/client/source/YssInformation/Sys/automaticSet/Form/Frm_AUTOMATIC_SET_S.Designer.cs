﻿using FAST.Core.BaseControl;
namespace YssInformation.Sys.automaticSet.Form
{
    partial class Frm_AUTOMATIC_SET_S
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_AUTOMATIC_SET_S));
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.row3 = new Yss.KTable.Models.Row();
            this.row4 = new Yss.KTable.Models.Row();
            this.row5 = new Yss.KTable.Models.Row();
            this.row6 = new Yss.KTable.Models.Row();
            this.row7 = new Yss.KTable.Models.Row();
            this.row8 = new Yss.KTable.Models.Row();
            this.row9 = new Yss.KTable.Models.Row();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.cell13 = new Yss.KTable.Models.Cell();
            this.cell14 = new Yss.KTable.Models.Cell();
            this.selBusinessType = new FAST.Core.BaseControl.YssSelCombox();
            this.selBusinessCode = new FAST.Core.BaseControl.YssSelCombox();
            this.selVaTime = new FAST.Core.BaseControl.YssSelCombox();
            this.pnlSearchLeft = new System.Windows.Forms.Panel();
            this.txtSearch = new Yss.KRichEx.TailTextBox();
            this.tabControl.SuspendLayout();
            this.tabGroupPage.SuspendLayout();
            this.tabPortPage.SuspendLayout();
            this.tbMain.SuspendLayout();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.pnlSearchLeft.SuspendLayout();
            this.SuspendLayout();
            // 
            // tabControl
            // 
            this.tabControl.Location = new System.Drawing.Point(238, 61);
            this.tabControl.Size = new System.Drawing.Size(344, 319);
            this.tabControl.Controls.SetChildIndex(this.tabGroupPage, 0);
            this.tabControl.Controls.SetChildIndex(this.tabPortPage, 0);
            // 
            // tabGroupPage
            // 
            this.tabGroupPage.AccessibleRole = System.Windows.Forms.AccessibleRole.TitleBar;
            this.tabGroupPage.Enabled = false;
            this.tabGroupPage.Text = "";
            this.tabGroupPage.Visible = false;
            // 
            // tabPortPage
            // 
            this.tabPortPage.AccessibleRole = System.Windows.Forms.AccessibleRole.None;
            this.tabPortPage.BackColor = System.Drawing.Color.White;
            this.tabPortPage.TabCloseButtonVisibleWithParent = false;
            // 
            // tabPort
            // 
            // 
            // 
            // 
            this.tabPort.Border.BorderColor = System.Drawing.Color.DarkGray;
            this.tabPort.Border.Bottom = false;
            this.tabPort.Border.Left = false;
            this.tabPort.Border.Right = false;
            this.tabPort.Border.Top = false;
            this.tabPort.EditCells = false;
            this.tabPort.SelectionColor = System.Drawing.Color.FromArgb(((int)(((byte)(251)))), ((int)(((byte)(222)))), ((int)(((byte)(129)))));
            this.tabPort.SelectionMode = System.Windows.Forms.SelectionMode.MultiExtended;
            this.tabPort.ShowSelectionMarkColor = true;
            this.tabPort.Size = new System.Drawing.Size(342, 291);
            this.tabPort.CheckStateChanged += this.checkState_Changed;
            this.tabPort.DefaultToolStripItems = ((Yss.KTable.Enums.SysToolStripItems)(Yss.KTable.Enums.SysToolStripItems.GardingMenu));
            // 
            // tabGroup
            // 
            // 
            // 
            // 
            this.tabGroup.Border.BorderColor = System.Drawing.Color.DarkGray;
            this.tabGroup.Border.Left = false;
            this.tabGroup.Border.Right = false;
            this.tabGroup.Border.Top = false;
            this.tabGroup.Size = new System.Drawing.Size(342, 291);
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
            this.column2});
            this.tbMain.Controls.Add(this.selBusinessType);
            this.tbMain.Controls.Add(this.selBusinessCode);
            this.tbMain.Controls.Add(this.selVaTime);
            this.tbMain.GridLine = Yss.KTable.Enums.GridLines.None;
            this.tbMain.Location = new System.Drawing.Point(0, 28);
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row3,
            this.row4,
            this.row5,
            this.row6,
            this.row7,
            this.row8,
            this.row9});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(238, 352);
            // 
            // btnBar
            // 
            this.btnBar.Size = new System.Drawing.Size(582, 28);
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
            this.stBarBottom.Location = new System.Drawing.Point(0, 380);
            this.stBarBottom.Size = new System.Drawing.Size(582, 25);
            // 
            // pnlMain
            // 
            this.pnlMain.Controls.Add(this.pnlSearchLeft);
            this.pnlMain.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.pnlMain.Size = new System.Drawing.Size(582, 405);
            this.pnlMain.Controls.SetChildIndex(this.stBarBottom, 0);
            this.pnlMain.Controls.SetChildIndex(this.btnBar, 0);
            this.pnlMain.Controls.SetChildIndex(this.tbMain, 0);
            this.pnlMain.Controls.SetChildIndex(this.pnlSearchLeft, 0);
            this.pnlMain.Controls.SetChildIndex(this.tabControl, 0);
            // 
            // yssPanel1
            // 
            // 
            // 
            // 
            this.yssPanel1.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.yssPanel1.Size = new System.Drawing.Size(582, 405);
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
            // cell1
            // 
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.InnerControl = null;
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = null;
            this.cell2.Text = "投资组合：";
            this.cell2.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
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
            // 
            // cell5
            // 
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = null;
            this.cell5.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.InnerControl = null;
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("新宋体", 9.75F, System.Drawing.FontStyle.Bold);
            this.row1.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row1.Height = 33;
            this.row1.IsGroup = true;
            this.row1.Text = null;
            // 
            // row2
            // 
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row2.ForeColor = System.Drawing.Color.Empty;
            this.row2.Text = null;
            // 
            // row3
            // 
            this.row3.Font = new System.Drawing.Font("新宋体", 9.75F, System.Drawing.FontStyle.Bold);
            this.row3.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row3.FullRowSelected = false;
            this.row3.Height = 33;
            this.row3.IsGroup = true;
            this.row3.Text = "基本信息";
            // 
            // row4
            // 
            this.row4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row4.FullRowSelected = false;
            this.row4.Text = null;
            // 
            // row5
            // 
            this.row5.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell7,
            this.cell8});
            this.row5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row5.FullRowSelected = false;
            this.row5.Height = 25;
            this.row5.Text = null;
            // 
            // row6
            // 
            this.row6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row6.FullRowSelected = false;
            this.row6.Height = 10;
            this.row6.Text = null;
            // 
            // row7
            // 
            this.row7.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell9,
            this.cell10});
            this.row7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row7.FullRowSelected = false;
            this.row7.Height = 25;
            this.row7.Text = null;
            // 
            // row8
            // 
            this.row8.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell11,
            this.cell12});
            this.row8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row8.FullRowSelected = false;
            this.row8.Height = 190;
            this.row8.Text = null;
            // 
            // row9
            // 
            this.row9.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell13,
            this.cell14});
            this.row9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row9.FullRowSelected = false;
            this.row9.Height = 25;
            this.row9.Text = null;
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.InnerControl = null;
            this.cell7.Text = "  业务类型：";
            this.cell7.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // cell8
            // 
            this.cell8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell8.InnerControl = this.selBusinessType;
            this.cell8.InnerControlAlign = Yss.KTable.Enums.CellInnerControlAlign.Center;
            // 
            // selBusinessType
            // 
            this.selBusinessType.AddedSelItemName = "";
            this.selBusinessType.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.selBusinessType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.selBusinessType.IsFillDecimal = false;
            this.selBusinessType.Location = new System.Drawing.Point(100, 56);
            this.selBusinessType.Margin = new System.Windows.Forms.Padding(0);
            this.selBusinessType.Name = "selBusinessType";
            this.selBusinessType.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.selBusinessType.Size = new System.Drawing.Size(121, 21);
            this.selBusinessType.TabIndex = 5;
            this.selBusinessType.Tag = this.cell8;
            this.selBusinessType.YssCaption = "业务类型";
            this.selBusinessType.DisplayName = "C_DV_NAME";
            this.selBusinessType.DisplayValue = "C_DV_CODE";
            this.selBusinessType.Parameter = "C_DV_NAME";
            this.selBusinessType.BeforeDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(this.selBusinessType_BeforeDropDownClick);
            this.selBusinessType.SelectedValueChanged += new System.EventHandler(this.selBusinessType_SelectedValueChanged);
            // 
            // cell9
            // 
            this.cell9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell9.InnerControl = null;
            this.cell9.Text = "  明细类型：";
            this.cell9.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // cell10
            // 
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.InnerControl = this.selBusinessCode;
            this.cell10.InnerControlAlign = Yss.KTable.Enums.CellInnerControlAlign.Center;
            // 
            // selBusinessCode
            // 
            this.selBusinessCode.AddedSelItemName = "";
            this.selBusinessCode.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.selBusinessCode.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.selBusinessCode.IsFillDecimal = false;
            this.selBusinessCode.Location = new System.Drawing.Point(100, 56);
            this.selBusinessCode.Margin = new System.Windows.Forms.Padding(0);
            this.selBusinessCode.Name = "selBusinessCode";
            this.selBusinessCode.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.selBusinessCode.Size = new System.Drawing.Size(121, 21);
            this.selBusinessCode.TabIndex = 6;
            this.selBusinessCode.Tag = this.cell10;
            this.selBusinessCode.YssCaption = "明细类型";
            this.selBusinessCode.YssIsMust = true;
            this.selBusinessCode.DisplayName = "C_DV_NAME";
            this.selBusinessCode.DisplayValue = "C_DV_CODE";
            this.selBusinessCode.Parameter = "C_DV_NAME";
            this.selBusinessCode.BeforeDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(this.selBusinessCode_BeforeDropDownClick);
            this.selBusinessCode.SelectedValueChanged += new System.EventHandler(this.selBusinessCode_SelectedValueChanged);
            // 
            // cell11
            // 
            this.cell11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell11.InnerControl = null;
            this.cell11.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // cell12
            // 
            this.cell12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell12.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell12.InnerControl = null;
            // 
            // cell13
            // 
            this.cell13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell13.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell13.InnerControl = null;
            this.cell13.Text = "所属估值表日期：";
            this.cell13.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // cell14
            // 
            this.cell14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell14.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell14.InnerControl = this.selVaTime;
            this.cell14.InnerControlAlign = Yss.KTable.Enums.CellInnerControlAlign.Center;
            // 
            // selVaTime
            // 
            this.selVaTime.AddedSelItemName = "";
            this.selVaTime.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.selVaTime.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.selVaTime.IsFillDecimal = false;
            this.selVaTime.Location = new System.Drawing.Point(100, 56);
            this.selVaTime.Margin = new System.Windows.Forms.Padding(0);
            this.selVaTime.Name = "selVaTime";
            this.selVaTime.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.selVaTime.Size = new System.Drawing.Size(121, 21);
            this.selVaTime.TabIndex = 7;
            this.selVaTime.Tag = this.cell14;
            this.selVaTime.YssCaption = "所属估值表日期";
            this.selVaTime.DisplayName = "C_DV_NAME";
            this.selVaTime.DisplayValue = "C_DV_CODE";
            this.selVaTime.Parameter = "C_DV_NAME";
            this.selVaTime.BeforeDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(this.selVaTime_BeforeDropDownClick);
            this.selVaTime.SelectedValueChanged += new System.EventHandler(this.selVaTime_SelectedValueChanged);
            // 
            // pnlSearchLeft
            // 
            this.pnlSearchLeft.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(243)))), ((int)(((byte)(245)))), ((int)(((byte)(249)))));
            ////this.pnlSearchLeft.Controls.Add(this.txtSearch);
            this.pnlSearchLeft.Dock = System.Windows.Forms.DockStyle.Top;
            this.pnlSearchLeft.Location = new System.Drawing.Point(238, 28);
            this.pnlSearchLeft.Name = "pnlSearchLeft";
            this.pnlSearchLeft.Padding = new System.Windows.Forms.Padding(5, 6, 5, 0);
            this.pnlSearchLeft.Size = new System.Drawing.Size(344, 33);
            this.pnlSearchLeft.TabIndex = 17;
            // 
            // txtSearch
            // 
            // 
            // 
            // 
            this.txtSearch.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.txtSearch.Dock = System.Windows.Forms.DockStyle.Fill;
            this.txtSearch.IsFillDecimal = false;
            this.txtSearch.Location = new System.Drawing.Point(5, 6);
            this.txtSearch.Margin = new System.Windows.Forms.Padding(1);
            this.txtSearch.Name = "txtSearch";
            this.txtSearch.Prefix = "搜索一下";
            this.txtSearch.PrefixBackColor = System.Drawing.Color.Transparent;
            this.txtSearch.PrefixForeColor = System.Drawing.SystemColors.WindowText;
            this.txtSearch.PromptText = "搜索一下";
            this.txtSearch.ShowPrefixBackGround = false;
            this.txtSearch.Size = new System.Drawing.Size(334, 21);
            this.txtSearch.SufixForeColor = System.Drawing.SystemColors.WindowText;
            this.txtSearch.TabIndex = 0;
            this.txtSearch.TailFont = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.txtSearch.TailImage = ((System.Drawing.Image)(resources.GetObject("txtSearch.TailImage")));
            this.txtSearch.TailImageSize = new System.Drawing.Size(20, 19);
            this.txtSearch.YssLength = 200;
            this.txtSearch.TailClick += new System.EventHandler(this.txtSearch_TailClick);
            // 
            // Frm_AUTOMATIC_SET_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(582, 405);
            this.Name = "Frm_AUTOMATIC_SET_S";
            this.Text = "自动化业务设置";
            this.YssOnBeforeNewClick += new FAST.Core.CRUD.Form.FrmBaseSet.BeforeNewClick(this.Frm_AUTOMATIC_SET_S_YssOnBeforeNewClick);
            this.YssOnBeforeEditClick += new FAST.Core.CRUD.Form.FrmBaseSet.BeforeEditClick(this.Frm_AUTOMATIC_SET_S_YssOnBeforeEditClick);
            this.YssOnBeforeCopyClick += new FAST.Core.CRUD.Form.FrmBaseSet.BeforeCopyClick(this.Frm_AUTOMATIC_SET_S_YssOnBeforeCopyClick);
            this.YssOnBeforeSaveClick += new FAST.Core.CRUD.Form.FrmBaseSet.BeforeSaveClick(this.Frm_AUTOMATIC_SET_S_YssOnBeforeSaveClick);
            this.tabControl.ResumeLayout(false);
            this.tabGroupPage.ResumeLayout(false);
            this.tabPortPage.ResumeLayout(false);
            this.tbMain.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.pnlSearchLeft.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Cell cell2;
        private Yss.KTable.Models.Cell cell3;
        private Yss.KTable.Models.Cell cell4;
        private Yss.KTable.Models.Cell cell5;
        private Yss.KTable.Models.Cell cell6;
        private Yss.KTable.Models.Row row1;
        private Yss.KTable.Models.Row row2;
        private Yss.KTable.Models.Row row3;
        private Yss.KTable.Models.Row row4;
        private Yss.KTable.Models.Row row5;
        private Yss.KTable.Models.Row row6;
        private Yss.KTable.Models.Row row7;
        private Yss.KTable.Models.Row row8;
        private Yss.KTable.Models.Row row9;
        private Yss.KTable.Models.Cell cell7;
        private Yss.KTable.Models.Cell cell8;
        private Yss.KTable.Models.Cell cell9;
        private Yss.KTable.Models.Cell cell10;
        private Yss.KTable.Models.Cell cell11;
        private Yss.KTable.Models.Cell cell12;
        private Yss.KTable.Models.Cell cell13;
        private Yss.KTable.Models.Cell cell14;
        private YssSelCombox selBusinessType;
        private YssSelCombox selBusinessCode;
        private YssSelCombox selVaTime;
        public System.Windows.Forms.Panel pnlSearchLeft;
        public Yss.KRichEx.TailTextBox txtSearch;
    }
}