using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;

using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;

namespace YssInformation.Bi.Holidays_A.Form
{
    partial class Frm_HDAY_L
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
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.yearCalendar1 = new Yss.Controls.YearCalendar();
            this.column9 = new Yss.KTable.Models.Column();
            this.column10 = new Yss.KTable.Models.Column();
            this.column11 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column6 = new Yss.KTable.Models.Column();
            this.column7 = new Yss.KTable.Models.Column();
            this.column8 = new Yss.KTable.Models.Column();
            this.cell19 = new Yss.KTable.Models.Cell();
            this.cell20 = new Yss.KTable.Models.Cell();
            this.cell21 = new Yss.KTable.Models.Cell();
            this.cell22 = new Yss.KTable.Models.Cell();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.column12 = new Yss.KTable.Models.Column();
            this.column13 = new Yss.KTable.Models.Column();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.cboYear = new FAST.Core.BaseControl.YssSelCombox();
            this.cell13 = new Yss.KTable.Models.Cell();
            this.cell14 = new Yss.KTable.Models.Cell();
            this.rbdList = new System.Windows.Forms.RadioButton();
            this.cell15 = new Yss.KTable.Models.Cell();
            this.rbdWiew = new System.Windows.Forms.RadioButton();
            this.column14 = new Yss.KTable.Models.Column();
            this.column15 = new Yss.KTable.Models.Column();
            this.column16 = new Yss.KTable.Models.Column();
            this.pnlCalendar = new YssDevComponents.DotNetBar.Controls.GroupPanel();
            this.tbFilter.SuspendLayout();
            this.pnlFilter.SuspendLayout();
            ////((System.ComponentModel.ISupportInitialize)(this.barFormStatus)).BeginInit();
            this.barFormStatus.SuspendLayout();
            this.pnlHost.SuspendLayout();
            this.navBarLeft.SuspendLayout();
            this.panelEx1.SuspendLayout();
            this.navigateItemMain.SuspendLayout();
            this.pnlLeftMain.SuspendLayout();
            this.pnlBarPort.SuspendLayout();
            this.pnlSearchLeft.SuspendLayout();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.pnlCalendar.SuspendLayout();
            this.SuspendLayout();
            // 
            // tbFilter
            // 
            // 
            // 
            // 
            this.tbFilter.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(102)))), ((int)(((byte)(136)))), ((int)(((byte)(184)))));
            this.tbFilter.Border.Bottom = false;
            this.tbFilter.Border.Left = false;
            this.tbFilter.Border.Right = false;
            this.tbFilter.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.column12,
            this.column13,
            this.column14,
            this.column15,
            this.column16});
            this.tbFilter.Controls.Add(this.rbdList);
            this.tbFilter.Controls.Add(this.rbdWiew);
            this.tbFilter.Controls.Add(this.cboYear);
            this.tbFilter.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2});
            this.tbFilter.Size = new System.Drawing.Size(893, 40);
            // 
            // tbMain
            // 
            this.tbMain.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(255)))));
            // 
            // 
            // 
            this.tbMain.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(102)))), ((int)(((byte)(136)))), ((int)(((byte)(184)))));
            this.tbMain.Border.Left = false;
            this.tbMain.Border.Right = false;
            this.tbMain.DefaultToolStripItems = ((Yss.KTable.Enums.SysToolStripItems)((((Yss.KTable.Enums.SysToolStripItems.ShowHideColumn | Yss.KTable.Enums.SysToolStripItems.GroupByColumn)
                        | Yss.KTable.Enums.SysToolStripItems.PrintSetup)
                        | Yss.KTable.Enums.SysToolStripItems.PrintPreview)));
            this.tbMain.Size = new System.Drawing.Size(893, 534);
            // 
            // pnlFilter
            // 
            this.pnlFilter.Size = new System.Drawing.Size(893, 40);
            this.pnlFilter.Style.Alignment = System.Drawing.StringAlignment.Center;
            this.pnlFilter.Style.BackColor1.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBackground;
            this.pnlFilter.Style.BackColor2.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBackground2;
            this.pnlFilter.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            this.pnlFilter.Style.BorderColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBorder;
            this.pnlFilter.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            this.pnlFilter.Style.GradientAngle = 90;
            // 
            // barFormStatus
            // 
            this.barFormStatus.Location = new System.Drawing.Point(0, 607);
            this.barFormStatus.Size = new System.Drawing.Size(893, 28);
            // 
            // proBar
            // 
            // 
            // 
            // 
            ////////////this.proBar.BackStyle.BorderBottom = YssDevComponents.DotNetBar.eStyleBorderType.DashDot;
            ////////////this.proBar.BackStyle.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(0)))), ((int)(((byte)(0)))));
            ////////////this.proBar.BackStyle.BorderLeft = YssDevComponents.DotNetBar.eStyleBorderType.DashDot;
            ////////////this.proBar.BackStyle.BorderRight = YssDevComponents.DotNetBar.eStyleBorderType.DashDot;
            ////////////this.proBar.BackStyle.BorderTop = YssDevComponents.DotNetBar.eStyleBorderType.DashDot;
            // 
            // pnlHost
            // 
            this.pnlHost.Controls.Add(this.pnlCalendar);
            this.pnlHost.Size = new System.Drawing.Size(893, 534);
            this.pnlHost.Controls.SetChildIndex(this.tbMain, 0);
            this.pnlHost.Controls.SetChildIndex(this.pnlCalendar, 0);
            // 
            // cboPageSize
            // 
            ////this.cboPageSize.Location = new System.Drawing.Point(588, 2);
            // 
            // txtSearch
            // 
            // 
            // 
            // 
            this.txtSearch.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.txtSearch.Size = new System.Drawing.Size(156, 21);
            // 
            // navBarLeft
            // 
            // 
            // 
            // 
            this.navBarLeft.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(164)))), ((int)(((byte)(187)))), ((int)(((byte)(217)))));
            this.navBarLeft.Size = new System.Drawing.Size(166, 574);
            // 
            // tbLeftMain
            // 
            this.tbLeftMain.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(255)))));
            // 
            // 
            // 
            this.tbLeftMain.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.tbLeftMain.Border.Bottom = false;
            this.tbLeftMain.Border.Left = false;
            this.tbLeftMain.Border.Right = false;
            this.tbLeftMain.Border.Top = false;
            this.tbLeftMain.SelectionMode = System.Windows.Forms.SelectionMode.One;
            this.tbLeftMain.Size = new System.Drawing.Size(164, 545);
            // 
            // splitLeft
            // 
            this.splitLeft.Location = new System.Drawing.Point(168, 0);
            this.splitLeft.Size = new System.Drawing.Size(6, 635);
            // 
            // btnArrow
            // 
            // 
            // 
            // 
            this.btnArrow.BackgroundStyle.BorderTop = YssDevComponents.DotNetBar.eStyleBorderType.Solid;
            this.btnArrow.BackgroundStyle.BorderTopColor = System.Drawing.Color.FromArgb(((int)(((byte)(102)))), ((int)(((byte)(136)))), ((int)(((byte)(184)))));
            this.btnArrow.BackgroundStyle.BorderTopWidth = 1;
            this.btnArrow.Location = new System.Drawing.Point(868, 0);
            // 
            // panelEx1
            // 
            this.panelEx1.Size = new System.Drawing.Size(893, 33);
            this.panelEx1.Style.Alignment = System.Drawing.StringAlignment.Center;
            this.panelEx1.Style.BackColor1.Color = System.Drawing.Color.FromArgb(((int)(((byte)(228)))), ((int)(((byte)(234)))), ((int)(((byte)(241)))));
            this.panelEx1.Style.BackColor2.Color = System.Drawing.Color.FromArgb(((int)(((byte)(228)))), ((int)(((byte)(234)))), ((int)(((byte)(241)))));
            this.panelEx1.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            this.panelEx1.Style.BorderColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBorder;
            this.panelEx1.Style.BorderSide = YssDevComponents.DotNetBar.eBorderSide.None;
            this.panelEx1.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            this.panelEx1.Style.GradientAngle = 90;
            // 
            // txtToPage
            // 
            // 
            // 
            // 
            ////this.txtToPage.Border.Class = "TextBoxBorder";
            ////this.txtToPage.Location = new System.Drawing.Point(809, 2);
            // 
            // barPort
            // 
            // 
            // btnBar
            // 
            this.btnBar.Size = new System.Drawing.Size(868, 33);
            // 
            // navigateItemMain
            // 
            this.navigateItemMain.Text = "";
            this.navigateItemMain.Controls.SetChildIndex(this.tbLeftMain, 0);
            // 
            // pnlLeftMain
            // 
            // 
            // 
            // 
            this.pnlLeftMain.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.pnlLeftMain.Border.Bottom = false;
            this.pnlLeftMain.Border.Left = false;
            this.pnlLeftMain.Border.Right = false;
            this.pnlLeftMain.Border.Top = false;
            this.pnlLeftMain.Size = new System.Drawing.Size(168, 635);
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
            this.pnlBarPort.Location = new System.Drawing.Point(1, 607);
            this.pnlBarPort.Size = new System.Drawing.Size(166, 28);
            // 
            // pnlSearchLeft
            // 
            this.pnlSearchLeft.Size = new System.Drawing.Size(166, 33);
            // 
            // pnlMain
            // 
            this.pnlMain.Location = new System.Drawing.Point(174, 0);
            this.pnlMain.Size = new System.Drawing.Size(893, 635);
            // 
            // yssPanel1
            // 
            // 
            // 
            // 
            this.yssPanel1.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.yssPanel1.Size = new System.Drawing.Size(1067, 635);
            // 
            // hpAssist
            // 
            // 
            // column1
            // 
            this.column1.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column1.Font = null;
            this.column1.ForeColor = System.Drawing.Color.Empty;
            this.column1.Width = 91;
            // 
            // column2
            // 
            this.column2.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column2.Font = null;
            this.column2.ForeColor = System.Drawing.Color.Empty;
            this.column2.Width = 92;
            // 
            // column4
            // 
            this.column4.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column4.Font = null;
            this.column4.ForeColor = System.Drawing.Color.Empty;
            this.column4.Width = 91;
            // 
            // column5
            // 
            this.column5.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column5.Font = null;
            this.column5.ForeColor = System.Drawing.Color.Empty;
            this.column5.Width = 92;
            // 
            // yearCalendar1
            // 
            this.yearCalendar1.BackColor = System.Drawing.Color.Transparent;
            this.yearCalendar1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.yearCalendar1.Enabled = false;
            this.yearCalendar1.Location = new System.Drawing.Point(0, 0);
            this.yearCalendar1.Name = "yearCalendar1";
            this.yearCalendar1.Size = new System.Drawing.Size(887, 528);
            this.yearCalendar1.TabIndex = 0;
            this.yearCalendar1.Visible = false;
            this.yearCalendar1.Year = 2011;
            // 
            // column9
            // 
            this.column9.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column9.Font = null;
            this.column9.ForeColor = System.Drawing.Color.Empty;
            this.column9.Width = 186;
            // 
            // column10
            // 
            this.column10.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column10.Font = null;
            this.column10.ForeColor = System.Drawing.Color.Empty;
            this.column10.Width = 186;
            // 
            // column11
            // 
            this.column11.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column11.Font = null;
            this.column11.ForeColor = System.Drawing.Color.Empty;
            this.column11.Width = 186;
            // 
            // column3
            // 
            this.column3.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column3.Font = null;
            this.column3.ForeColor = System.Drawing.Color.Empty;
            this.column3.Width = 111;
            // 
            // column6
            // 
            this.column6.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column6.Font = null;
            this.column6.ForeColor = System.Drawing.Color.Empty;
            this.column6.Width = 122;
            // 
            // column7
            // 
            this.column7.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column7.Font = null;
            this.column7.ForeColor = System.Drawing.Color.Empty;
            this.column7.Width = 111;
            // 
            // column8
            // 
            this.column8.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column8.Font = null;
            this.column8.ForeColor = System.Drawing.Color.Empty;
            this.column8.Width = 122;
            // 
            // cell19
            // 
            this.cell19.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell19.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell19.InnerControl = null;
            this.cell19.Text = "    年份选择：";
            // 
            // cell20
            // 
            this.cell20.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell20.InnerControl = null;
            // 
            // cell21
            // 
            this.cell21.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell21.InnerControl = null;
            // 
            // cell22
            // 
            this.cell22.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell22.InnerControl = null;
            // 
            // cell1
            // 
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.InnerControl = null;
            // 
            // cell2
            // 
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = null;
            // 
            // cell3
            // 
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
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = null;
            // 
            // cell6
            // 
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.InnerControl = null;
            // 
            // cell7
            // 
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.InnerControl = null;
            // 
            // cell8
            // 
            this.cell8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell8.InnerControl = null;
            // 
            // cell9
            // 
            this.cell9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell9.InnerControl = null;
            // 
            // cell10
            // 
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.InnerControl = null;
            // 
            // column12
            // 
            this.column12.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column12.Width = 60;
            // 
            // column13
            // 
            this.column13.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column13.Width = 120;
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row1.FullRowSelected = false;
            this.row1.Height = 9;
            this.row1.Text = null;
            // 
            // row2
            // 
            this.row2.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell11,
            this.cell12,
            this.cell13,
            this.cell14,
            this.cell15});
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row2.FullRowSelected = false;
            this.row2.Height = 28;
            this.row2.Text = null;
            // 
            // cell11
            // 
            this.cell11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell11.InnerControl = null;
            this.cell11.Text = "年份：";
            this.cell11.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // cell12
            // 
            this.cell12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell12.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell12.InnerControl = this.cboYear;
            // 
            // cboYear
            // 
            this.cboYear.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboYear.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboYear.DisplayName = "C_DV_NAME";
            this.cboYear.DisplayValue = "C_DV_CODE";
            this.cboYear.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cboYear.FilterCond = "";
            this.cboYear.IsFillDecimal = false;
            this.cboYear.IsRefresh = false;
            this.cboYear.Location = new System.Drawing.Point(60, 9);
            this.cboYear.Margin = new System.Windows.Forms.Padding(0);
            this.cboYear.MethodInfo = null;
            this.cboYear.Name = "cboYear";
            this.cboYear.Parameter = "C_DV_CODE~C_DV_NAME";
            this.cboYear.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconIndent;
            this.cboYear.PrefixBackColor = System.Drawing.Color.White;
            this.cboYear.QueryCond = "";
            this.cboYear.QueryType = "";
            this.cboYear.ShowRefresh = false;
            this.cboYear.Size = new System.Drawing.Size(119, 21);
            this.cboYear.TabIndex = 0;
            this.cboYear.Tag = this.cell12;
            this.cboYear.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboYear.YssDataSrc = FAST.Core.Context.ClsEnums.DataSrc.SrcDB;
            this.cboYear.BeforeDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(this.cboYear_BeforeDropDownClick);
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
            this.cell14.InnerControl = this.rbdList;
            // 
            // rbdList
            // 
            this.rbdList.Checked = true;
            this.rbdList.Location = new System.Drawing.Point(260, 9);
            this.rbdList.Name = "rbdList";
            this.rbdList.Size = new System.Drawing.Size(119, 27);
            this.rbdList.TabIndex = 2;
            this.rbdList.TabStop = true;
            this.rbdList.Tag = this.cell14;
            this.rbdList.Text = "列表模式";
            this.rbdList.UseVisualStyleBackColor = true;
            this.rbdList.CheckedChanged += new System.EventHandler(this.rbdList_CheckedChanged);
            // 
            // cell15
            // 
            this.cell15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell15.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell15.InnerControl = this.rbdWiew;
            // 
            // rbdWiew
            // 
            this.rbdWiew.Location = new System.Drawing.Point(380, 9);
            this.rbdWiew.Name = "rbdWiew";
            this.rbdWiew.Size = new System.Drawing.Size(79, 27);
            this.rbdWiew.TabIndex = 1;
            this.rbdWiew.Tag = this.cell15;
            this.rbdWiew.Text = "视图模式";
            this.rbdWiew.UseVisualStyleBackColor = true;
            // 
            // column14
            // 
            this.column14.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            // 
            // column15
            // 
            this.column15.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column15.Width = 120;
            // 
            // column16
            // 
            this.column16.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column16.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            // 
            // pnlCalendar
            // 
            this.pnlCalendar.CanvasColor = System.Drawing.SystemColors.Control;
            this.pnlCalendar.ColorSchemeStyle = YssDevComponents.DotNetBar.eDotNetBarStyle.Office2007;
            this.pnlCalendar.Controls.Add(this.yearCalendar1);
            this.pnlCalendar.Dock = System.Windows.Forms.DockStyle.Fill;
            this.pnlCalendar.Location = new System.Drawing.Point(0, 0);
            this.pnlCalendar.Name = "pnlCalendar";
            this.pnlCalendar.Size = new System.Drawing.Size(893, 534);
            // 
            // 
            // 
            this.pnlCalendar.Style.BackColor2SchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBackground2;
            this.pnlCalendar.Style.BackColorGradientAngle = 90;
            this.pnlCalendar.Style.BackColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBackground;
            this.pnlCalendar.Style.BorderBottom = YssDevComponents.DotNetBar.eStyleBorderType.Solid;
            this.pnlCalendar.Style.BorderBottomWidth = 1;
            this.pnlCalendar.Style.BorderColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBorder;
            this.pnlCalendar.Style.BorderLeft = YssDevComponents.DotNetBar.eStyleBorderType.Solid;
            this.pnlCalendar.Style.BorderLeftWidth = 1;
            this.pnlCalendar.Style.BorderRight = YssDevComponents.DotNetBar.eStyleBorderType.Solid;
            this.pnlCalendar.Style.BorderRightWidth = 1;
            this.pnlCalendar.Style.BorderTop = YssDevComponents.DotNetBar.eStyleBorderType.Solid;
            this.pnlCalendar.Style.BorderTopWidth = 1;
            this.pnlCalendar.Style.CornerDiameter = 4;
            this.pnlCalendar.Style.CornerType = YssDevComponents.DotNetBar.eCornerType.Rounded;
            this.pnlCalendar.Style.TextAlignment = YssDevComponents.DotNetBar.eStyleTextAlignment.Center;
            this.pnlCalendar.Style.TextColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            this.pnlCalendar.Style.TextLineAlignment = YssDevComponents.DotNetBar.eStyleTextAlignment.Near;
            this.pnlCalendar.TabIndex = 27;
            this.pnlCalendar.Visible = false;
            // 
            // Frm_HDAY_L
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1067, 635);
            this.DoubleBuffered = true;
            this.Name = "Frm_HDAY_L";
            this.ShowFilterPanel = true;
            this.ShowLeftSearchPanel = true;
            this.ShowPlanSet = true;
            this.Text = "节假日信息";
            this.YssOnBeforeLeftRowDoubleClick += new FAST.Core.CRUD.Form.FrmBaseList.BeforeLeftRowDoubleClick(this.Frm_HDAY_L_YssOnBeforeLeftRowDoubleClick);
            this.tbFilter.ResumeLayout(false);
            this.pnlFilter.ResumeLayout(false);
            ////((System.ComponentModel.ISupportInitialize)(this.barFormStatus)).EndInit();
            this.barFormStatus.ResumeLayout(false);
            this.pnlHost.ResumeLayout(false);
            this.navBarLeft.ResumeLayout(false);
            this.panelEx1.ResumeLayout(false);
            this.navigateItemMain.ResumeLayout(false);
            this.pnlLeftMain.ResumeLayout(false);
            this.pnlBarPort.ResumeLayout(false);
            this.pnlSearchLeft.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.pnlCalendar.ResumeLayout(false);
            this.pnlCalendar.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
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
        private Yss.KTable.Models.Column column3;
        private Yss.KTable.Models.Column column6;
        private Yss.KTable.Models.Column column7;
        private Yss.KTable.Models.Column column8;
        private Yss.KTable.Models.Cell cell19;
        private Yss.KTable.Models.Cell cell20;
        private Yss.KTable.Models.Cell cell21;
        private Yss.KTable.Models.Cell cell22;
        private Yss.KTable.Models.Column column9;
        private Yss.KTable.Models.Column column10;
        private Yss.KTable.Models.Column column11;
        private Yss.Controls.YearCalendar yearCalendar1;
        private Yss.KTable.Models.Column column12;
        private Yss.KTable.Models.Column column13;
        private FAST.Core.BaseControl.YssSelCombox cboYear;
        private Yss.KTable.Models.Row row1;
        private Yss.KTable.Models.Row row2;
        private Yss.KTable.Models.Cell cell11;
        private Yss.KTable.Models.Cell cell12;
        private Yss.KTable.Models.Column column14;
        private Yss.KTable.Models.Column column15;
        private Yss.KTable.Models.Column column16;
        private Yss.KTable.Models.Cell cell13;
        private Yss.KTable.Models.Cell cell14;
        private Yss.KTable.Models.Cell cell15;
        public System.Windows.Forms.RadioButton rbdList;
        private System.Windows.Forms.RadioButton rbdWiew;
        private YssDevComponents.DotNetBar.Controls.GroupPanel pnlCalendar;


    }
}