using FAST.Core.BaseControl;
using FAST.Common.Service.Pojo;
namespace YssElecReco.Form.Er
{
    partial class Frm_ELEC_DETAIL_L
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
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo2 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo1 = new FAST.Core.BaseControl.ControlMethodInfo();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_ELEC_DETAIL_L));
            FAST.Common.Service.Pojo.ClsPageInation clsPageInation1 = new FAST.Common.Service.Pojo.ClsPageInation();
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.row1 = new Yss.KTable.Models.Row();
            this.tabPageXml = new Yss.Controls.TabPage();
            this.tabPageResult = new Yss.Controls.TabPage();
            this.tabPageState = new Yss.Controls.TabPage();
            this.tabPageTgh = new Yss.Controls.TabPage();
            this.label8 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.panel1 = new System.Windows.Forms.Panel();
            this.labelEx2 = new Yss.Controls.LabelEx();
            this.labelEx6 = new Yss.Controls.LabelEx();
            this.labelEx5 = new Yss.Controls.LabelEx();
            this.labelEx4 = new Yss.Controls.LabelEx();
            this.labelEx3 = new Yss.Controls.LabelEx();
            this.label16 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label15 = new System.Windows.Forms.Label();
            this.label14 = new System.Windows.Forms.Label();
            this.label13 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label17 = new System.Windows.Forms.Label();
            this.cboResultType = new FAST.Core.BaseControl.YssSelCombox();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.column3 = new Yss.KTable.Models.Column();
            this.row2 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cboXsjb = new FAST.Core.BaseControl.YssSelCombox();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cboXszyzb = new FAST.Core.BaseControl.YssSelCombox();
            this.cellBtnSzzb = new Yss.KTable.Models.Cell();
            this.btnSzzbfa = new Yss.Controls.ImageButton();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.chkCopySameData = new Yss.Controls.CheckBox();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.column6 = new Yss.KTable.Models.Column();
            this.column7 = new Yss.KTable.Models.Column();
            this.column8 = new Yss.KTable.Models.Column();
            this.column9 = new Yss.KTable.Models.Column();
            this.ColbtnSzzb = new Yss.KTable.Models.Column();
            this.column10 = new Yss.KTable.Models.Column();
            this.column11 = new Yss.KTable.Models.Column();
            this.tabPageRptLog = new Yss.Controls.TabPage();
            this.tbXML = new Yss.KTable.Models.Table(this.components);
            this.xmlWebBrowser = new System.Windows.Forms.WebBrowser();
            this.tabCtrlDataMain.SuspendLayout();
            this.tabPageDefault.SuspendLayout();
            this.pnlDetails.SuspendLayout();
            this.tbFilter.SuspendLayout();
            this.pnlFilter.SuspendLayout();
            this.pnlHost.SuspendLayout();
            this.navBarLeft.SuspendLayout();
            this.panelEx1.SuspendLayout();
            this.navigateItemMain.SuspendLayout();
            this.pnlLeftMain.SuspendLayout();
            this.pnlSearchLeft.SuspendLayout();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.tabPageXml.SuspendLayout();
            this.tabPageState.SuspendLayout();
            this.panel1.SuspendLayout();
            this.tbXML.SuspendLayout();
            this.SuspendLayout();
            // 
            // tabCtrlDataMain
            // 
            this.tabCtrlDataMain.Controls.Add(this.tabPageXml);
            this.tabCtrlDataMain.Controls.Add(this.tabPageResult);
            this.tabCtrlDataMain.Controls.Add(this.tabPageState);
            this.tabCtrlDataMain.Controls.Add(this.tabPageRptLog);
            this.tabCtrlDataMain.Controls.Add(this.tabPageTgh);
            this.tabCtrlDataMain.SelectedTab = this.tabPageRptLog;
            this.tabCtrlDataMain.Size = new System.Drawing.Size(890, 416);
            this.tabCtrlDataMain.TabAutoWidth = true;
            this.tabCtrlDataMain.TabPages.AddRange(new Yss.Controls.TabPage[] {
            this.tabPageXml,
            this.tabPageResult,
            this.tabPageState,
            this.tabPageRptLog,
            this.tabPageTgh});
            this.tabCtrlDataMain.Controls.SetChildIndex(this.tabPageRptLog, 0);
            this.tabCtrlDataMain.Controls.SetChildIndex(this.tabPageState, 0);
            this.tabCtrlDataMain.Controls.SetChildIndex(this.tabPageResult, 0);
            this.tabCtrlDataMain.Controls.SetChildIndex(this.tabPageXml, 0);
            this.tabCtrlDataMain.Controls.SetChildIndex(this.tabPageDefault, 0);
            this.tabCtrlDataMain.Controls.SetChildIndex(this.tabPageTgh, 0);
            // 
            // tabPageDefault
            // 
            this.tabPageDefault.Text = "对账数据明细";
            // 
            // pnlDetails
            // 
            // 
            // 
            // 
            this.pnlDetails.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.pnlDetails.Border.Bottom = false;
            this.pnlDetails.Border.Left = false;
            this.pnlDetails.Border.Right = false;
            this.pnlDetails.Border.Top = false;
            this.pnlDetails.Location = new System.Drawing.Point(0, 526);
            this.pnlDetails.Size = new System.Drawing.Size(892, 36);
            // 
            // tabCtrlDataDetail
            // 
            this.tabCtrlDataDetail.Size = new System.Drawing.Size(890, 34);
            // 
            // expandSplitterDetails
            // 
            this.expandSplitterDetails.Location = new System.Drawing.Point(0, 516);
            this.expandSplitterDetails.Size = new System.Drawing.Size(892, 10);
            // 
            // tbFilter
            // 
            // 
            // 
            // 
            this.tbFilter.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.tbFilter.Border.Bottom = false;
            this.tbFilter.Border.Left = false;
            this.tbFilter.Border.Right = false;
            this.tbFilter.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.column1,
            this.column2,
            this.column3,
            this.column4,
            this.column5,
            this.column6,
            this.column7,
            this.column8,
            this.column9,
            this.ColbtnSzzb,
            this.column10,
            this.column11});
            this.tbFilter.Controls.Add(this.chkCopySameData);
            this.tbFilter.Controls.Add(this.btnSzzbfa);
            this.tbFilter.Controls.Add(this.cboXszyzb);
            this.tbFilter.Controls.Add(this.cboXsjb);
            this.tbFilter.Controls.Add(this.cboResultType);
            this.tbFilter.GridLineColor = System.Drawing.Color.White;
            this.tbFilter.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2});
            this.tbFilter.Size = new System.Drawing.Size(892, 40);
            // 
            // tbMain
            // 
            // 
            // 
            // 
            this.tbMain.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.tbMain.Border.Bottom = false;
            this.tbMain.Border.Left = false;
            this.tbMain.Border.Right = false;
            this.tbMain.Border.Top = false;
            this.tbMain.DefaultToolStripItems = ((Yss.KTable.Enums.SysToolStripItems)((((((Yss.KTable.Enums.SysToolStripItems.ShowHideColumn | Yss.KTable.Enums.SysToolStripItems.GroupByColumn)
                        | Yss.KTable.Enums.SysToolStripItems.PrintSetup)
                        | Yss.KTable.Enums.SysToolStripItems.PrintPreview)
                        | Yss.KTable.Enums.SysToolStripItems.GardingMenu)
                        | Yss.KTable.Enums.SysToolStripItems.ShowCancelChildRowsChecked)));
            this.tbMain.EditCells = false;
            this.tbMain.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconIndent;
            this.tbMain.SelectionMode = System.Windows.Forms.SelectionMode.One;
            this.tbMain.Size = new System.Drawing.Size(888, 389);
            // 
            // pnlFilter
            // 
            this.pnlFilter.Size = new System.Drawing.Size(892, 40);
            this.pnlFilter.Style.Alignment = System.Drawing.StringAlignment.Center;
            this.pnlFilter.Style.BackColor1.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBackground;
            this.pnlFilter.Style.BackColor2.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBackground2;
            this.pnlFilter.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            this.pnlFilter.Style.BorderColor.Color = System.Drawing.Color.FromArgb(((int)(((byte)(102)))), ((int)(((byte)(136)))), ((int)(((byte)(184)))));
            this.pnlFilter.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            this.pnlFilter.Style.GradientAngle = 90;
            // 
            // pnlHost
            // 
            this.pnlHost.Size = new System.Drawing.Size(892, 443);
            // 
            // txtSearch
            // 
            // 
            // 
            // 
            this.txtSearch.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            // 
            // navBarLeft
            // 
            // 
            // 
            // 
            this.navBarLeft.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(164)))), ((int)(((byte)(187)))), ((int)(((byte)(217)))));
            // 
            // tbLeftMain
            // 
            // 
            // 
            // 
            this.tbLeftMain.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.tbLeftMain.Border.Bottom = false;
            this.tbLeftMain.Border.Left = false;
            this.tbLeftMain.Border.Right = false;
            this.tbLeftMain.Border.Top = false;
            this.tbLeftMain.Size = new System.Drawing.Size(243, 524);
            // 
            // btnArrow
            // 
            // 
            // 
            // 
            this.btnArrow.BackgroundStyle.BorderTopColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.DockSiteBackColor2;
            this.btnArrow.Location = new System.Drawing.Point(867, 0);
            // 
            // panelEx1
            // 
            this.panelEx1.Size = new System.Drawing.Size(892, 33);
            this.panelEx1.Style.Alignment = System.Drawing.StringAlignment.Center;
            this.panelEx1.Style.BackColor1.Color = System.Drawing.Color.FromArgb(((int)(((byte)(228)))), ((int)(((byte)(234)))), ((int)(((byte)(241)))));
            this.panelEx1.Style.BackColor2.Color = System.Drawing.Color.FromArgb(((int)(((byte)(228)))), ((int)(((byte)(234)))), ((int)(((byte)(241)))));
            this.panelEx1.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            this.panelEx1.Style.BorderColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBorder;
            this.panelEx1.Style.BorderSide = YssDevComponents.DotNetBar.eBorderSide.None;
            this.panelEx1.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            this.panelEx1.Style.GradientAngle = 90;
            // 
            // btnBar
            // 
            this.btnBar.Size = new System.Drawing.Size(867, 33);
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
            // 
            // pnlMain
            // 
            this.pnlMain.Location = new System.Drawing.Point(0, 0);
            this.pnlMain.Size = new System.Drawing.Size(892, 562);
            // 
            // yssPanel1
            // 
            // 
            // 
            // 
            this.yssPanel1.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.yssPanel1.Size = new System.Drawing.Size(892, 562);
            // 
            // column1
            // 
            this.column1.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.MaxPrintWidth = 0;
            this.column1.Width = 10;
            // 
            // column2
            // 
            this.column2.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column2.MaxPrintWidth = 0;
            this.column2.Width = 65;
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row1.FullRowSelected = false;
            this.row1.Height = 7;
            this.row1.Text = null;
            // 
            // tabPageXml
            // 
            this.tabPageXml.Controls.Add(this.tbXML);
            this.tabPageXml.Name = "tabPageXml";
            this.tabPageXml.TabCloseButtonVisible = false;
            this.tabPageXml.Text = "发送数据明细";
            // 
            // tabPageResult
            // 
            this.tabPageResult.Name = "tabPageResult";
            this.tabPageResult.TabCloseButtonVisible = false;
            this.tabPageResult.Text = "反馈结果明细";

            // 
            // tabPageTgh
            // 
            this.tabPageTgh.Name = "tabPageTgh";
            this.tabPageTgh.TabCloseButtonVisible = false;
            this.tabPageTgh.Text = "托管行数据";
            // 
            // tabPageState
            // 
            this.tabPageState.AutoScroll = true;
            this.tabPageState.Controls.Add(this.label8);
            this.tabPageState.Controls.Add(this.label7);
            this.tabPageState.Controls.Add(this.label2);
            this.tabPageState.Controls.Add(this.label1);
            this.tabPageState.Controls.Add(this.panel1);
            this.tabPageState.Name = "tabPageState";
            this.tabPageState.TabCloseButtonVisible = false;
            this.tabPageState.Text = "处理状态图";
            // 
            // label8
            // 
            this.label8.BackColor = System.Drawing.SystemColors.ActiveBorder;
            this.label8.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.label8.Font = new System.Drawing.Font("黑体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.label8.Location = new System.Drawing.Point(36, 86);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(75, 35);
            this.label8.TabIndex = 10;
            this.label8.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label7
            // 
            this.label7.BackColor = System.Drawing.SystemColors.Highlight;
            this.label7.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.label7.Font = new System.Drawing.Font("黑体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.label7.Location = new System.Drawing.Point(36, 10);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(75, 35);
            this.label7.TabIndex = 9;
            this.label7.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("微软雅黑", 10.5F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label2.Location = new System.Drawing.Point(32, 122);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(51, 20);
            this.label2.TabIndex = 8;
            this.label2.Text = "未完成";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("微软雅黑", 10.5F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label1.Location = new System.Drawing.Point(32, 47);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(51, 20);
            this.label1.TabIndex = 7;
            this.label1.Text = "已完成";
            // 
            // panel1
            // 
            this.panel1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.panel1.Controls.Add(this.labelEx2);
            this.panel1.Controls.Add(this.labelEx6);
            this.panel1.Controls.Add(this.labelEx5);
            this.panel1.Controls.Add(this.labelEx4);
            this.panel1.Controls.Add(this.labelEx3);
            this.panel1.Controls.Add(this.label16);
            this.panel1.Controls.Add(this.label3);
            this.panel1.Controls.Add(this.label6);
            this.panel1.Controls.Add(this.label5);
            this.panel1.Controls.Add(this.label15);
            this.panel1.Controls.Add(this.label14);
            this.panel1.Controls.Add(this.label13);
            this.panel1.Controls.Add(this.label4);
            this.panel1.Controls.Add(this.label17);
            this.panel1.Location = new System.Drawing.Point(187, 0);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(650, 400);
            this.panel1.TabIndex = 0;
            // 
            // labelEx2
            // 
            this.labelEx2.AutoSize = true;
            // 
            // 
            // 
            this.labelEx2.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.labelEx2.Border.Bottom = false;
            this.labelEx2.Border.Left = false;
            this.labelEx2.Border.Right = false;
            this.labelEx2.Border.Top = false;
            this.labelEx2.Location = new System.Drawing.Point(160, 20);
            this.labelEx2.Name = "labelEx2";
            this.labelEx2.Size = new System.Drawing.Size(455, 12);
            this.labelEx2.TabIndex = 28;
            this.labelEx2.Tag = "";
            this.labelEx2.Text = "";
            this.labelEx2.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // labelEx6
            // 
            this.labelEx6.AutoSize = true;
            // 
            // 
            // 
            this.labelEx6.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.labelEx6.Border.Bottom = false;
            this.labelEx6.Border.Left = false;
            this.labelEx6.Border.Right = false;
            this.labelEx6.Border.Top = false;
            this.labelEx6.Location = new System.Drawing.Point(160, 332);
            this.labelEx6.Name = "labelEx6";
            this.labelEx6.Size = new System.Drawing.Size(455, 12);
            this.labelEx6.TabIndex = 27;
            this.labelEx6.Tag = "";
            this.labelEx6.Text = "";
            this.labelEx6.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // labelEx5
            // 
            this.labelEx5.AutoSize = true;
            // 
            // 
            // 
            this.labelEx5.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.labelEx5.Border.Bottom = false;
            this.labelEx5.Border.Left = false;
            this.labelEx5.Border.Right = false;
            this.labelEx5.Border.Top = false;
            this.labelEx5.Location = new System.Drawing.Point(160, 251);
            this.labelEx5.Name = "labelEx5";
            this.labelEx5.Size = new System.Drawing.Size(455, 12);
            this.labelEx5.TabIndex = 26;
            this.labelEx5.Tag = "";
            this.labelEx5.Text = "";
            this.labelEx5.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // labelEx4
            // 
            this.labelEx4.AutoSize = true;
            // 
            // 
            // 
            this.labelEx4.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.labelEx4.Border.Bottom = false;
            this.labelEx4.Border.Left = false;
            this.labelEx4.Border.Right = false;
            this.labelEx4.Border.Top = false;
            this.labelEx4.Location = new System.Drawing.Point(160, 174);
            this.labelEx4.Name = "labelEx4";
            this.labelEx4.Size = new System.Drawing.Size(455, 12);
            this.labelEx4.TabIndex = 25;
            this.labelEx4.Tag = "";
            this.labelEx4.Text = "";
            this.labelEx4.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // labelEx3
            // 
            this.labelEx3.AutoSize = true;
            // 
            // 
            // 
            this.labelEx3.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.labelEx3.Border.Bottom = false;
            this.labelEx3.Border.Left = false;
            this.labelEx3.Border.Right = false;
            this.labelEx3.Border.Top = false;
            this.labelEx3.Location = new System.Drawing.Point(160, 90);
            this.labelEx3.Name = "labelEx3";
            this.labelEx3.Size = new System.Drawing.Size(455, 12);
            this.labelEx3.TabIndex = 21;
            this.labelEx3.Tag = "";
            this.labelEx3.Text = "";
            this.labelEx3.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // label16
            // 
            this.label16.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.label16.BackColor = System.Drawing.SystemColors.ActiveBorder;
            this.label16.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.label16.Font = new System.Drawing.Font("黑体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label16.ForeColor = System.Drawing.SystemColors.ControlText;
            this.label16.Location = new System.Drawing.Point(54, 161);
            this.label16.Name = "label16";
            this.label16.Size = new System.Drawing.Size(90, 35);
            this.label16.TabIndex = 18;
            this.label16.Text = "发送托管行";
            this.label16.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // label3
            // 
            this.label3.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.label3.BackColor = System.Drawing.SystemColors.ActiveBorder;
            this.label3.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.label3.Font = new System.Drawing.Font("黑体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.label3.Location = new System.Drawing.Point(54, 9);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(90, 35);
            this.label3.TabIndex = 7;
            this.label3.Text = "生成报文";
            this.label3.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // label6
            // 
            this.label6.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.label6.BackColor = System.Drawing.SystemColors.ActiveBorder;
            this.label6.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.label6.Font = new System.Drawing.Font("黑体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.label6.Location = new System.Drawing.Point(54, 83);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(90, 35);
            this.label6.TabIndex = 10;
            this.label6.Text = "发送深圳通";
            this.label6.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // label5
            // 
            this.label5.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.label5.BackColor = System.Drawing.SystemColors.ActiveBorder;
            this.label5.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.label5.Font = new System.Drawing.Font("黑体", 10.5F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.label5.Location = new System.Drawing.Point(54, 239);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(90, 35);
            this.label5.TabIndex = 9;
            this.label5.Text = "接收对账\r\n结果报文";
            this.label5.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // label15
            // 
            this.label15.Image = global::YssElecReco.Properties.Resources.arrow;
            this.label15.Location = new System.Drawing.Point(80, 38);
            this.label15.Name = "label15";
            this.label15.Size = new System.Drawing.Size(40, 45);
            this.label15.TabIndex = 17;
            // 
            // label14
            // 
            this.label14.Image = global::YssElecReco.Properties.Resources.arrow;
            this.label14.Location = new System.Drawing.Point(80, 273);
            this.label14.Name = "label14";
            this.label14.Size = new System.Drawing.Size(42, 45);
            this.label14.TabIndex = 16;
            // 
            // label13
            // 
            this.label13.Image = global::YssElecReco.Properties.Resources.arrow;
            this.label13.Location = new System.Drawing.Point(80, 118);
            this.label13.Name = "label13";
            this.label13.Size = new System.Drawing.Size(42, 45);
            this.label13.TabIndex = 15;
            // 
            // label4
            // 
            this.label4.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.label4.BackColor = System.Drawing.SystemColors.ActiveBorder;
            this.label4.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.label4.Font = new System.Drawing.Font("黑体", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.label4.Location = new System.Drawing.Point(54, 319);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(90, 35);
            this.label4.TabIndex = 8;
            this.label4.Text = "对账一致";
            this.label4.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // label17
            // 
            this.label17.Image = global::YssElecReco.Properties.Resources.arrow;
            this.label17.Location = new System.Drawing.Point(80, 193);
            this.label17.Name = "label17";
            this.label17.Size = new System.Drawing.Size(42, 45);
            this.label17.TabIndex = 19;
            // 
            // cboResultType
            // 
            this.cboResultType.AddedSelItemName = "";
            this.cboResultType.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.cboResultType.AutoScroll = true;
            // 
            // 
            // 
            this.cboResultType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboResultType.DefaultValue = "ALL_DATA";
            this.cboResultType.DisplayName = "C_DV_NAME";
            this.cboResultType.DisplayValue = "C_DV_CODE";
            this.cboResultType.FilterCond = "";
            this.cboResultType.IsFillDecimal = false;
            this.cboResultType.IsRefresh = false;
            this.cboResultType.Location = new System.Drawing.Point(75, 7);
            this.cboResultType.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo2.MethodName = "getDataListByTypes";
            controlMethodInfo2.MethodParams = null;
            controlMethodInfo2.MethodParamValues = new string[] {
        "RESULT_DATA,"};
            controlMethodInfo2.Methods = null;
            this.cboResultType.MethodInfo = controlMethodInfo2;
            this.cboResultType.Name = "cboResultType";
            this.cboResultType.NodeID = "C_DV_CODE";
            this.cboResultType.Parameter = "C_DV_NAME";
            this.cboResultType.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IndentOnly;
            this.cboResultType.PrefixBackColor = System.Drawing.Color.White;
            this.cboResultType.QueryCond = "";
            this.cboResultType.QueryType = "";
            this.cboResultType.ShowRefresh = false;
            this.cboResultType.Size = new System.Drawing.Size(99, 21);
            this.cboResultType.SortColumn = "C_DZ_NAME";
            this.cboResultType.TabIndex = 2;
            this.cboResultType.Tag = this.cell3;
            this.cboResultType.Text = "所有数据";
            this.cboResultType.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboResultType.YssCaption = "结果类型";
            // 
            // cell3
            // 
            this.cell3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell3.InnerControl = this.cboResultType;
            this.cell3.TextAlign = System.Drawing.ContentAlignment.TopRight;
            // 
            // column3
            // 
            this.column3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column3.MaxPrintWidth = 0;
            this.column3.Width = 100;
            // 
            // row2
            // 
            this.row2.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell1,
            this.cell2,
            this.cell3,
            this.cell4,
            this.cell5,
            this.cell6,
            this.cell7,
            this.cell8,
            this.cell9,
            this.cellBtnSzzb,
            this.cell10,
            this.cell11});
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row2.FullRowSelected = false;
            this.row2.Text = null;
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
            this.cell2.Text = "结果类型:";
            this.cell2.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
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
            this.cell5.Text = "显示级别:";
            this.cell5.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.InnerControl = this.cboXsjb;
            this.cboXsjb.AddedSelItemName = "";
            this.cboXsjb.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboXsjb.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboXsjb.Border.Bottom = true;
            this.cboXsjb.Border.Left = true;
            this.cboXsjb.Border.Right = true;
            this.cboXsjb.Border.Top = true;
            this.cboXsjb.FilterCond = "";
            this.cboXsjb.Location = new System.Drawing.Point(402, 7);
            this.cboXsjb.Margin = new System.Windows.Forms.Padding(0);
            this.cboXsjb.MethodInfo = null;
            this.cboXsjb.Name = "cboXsjb";
            this.cboXsjb.QueryCond = "";
            this.cboXsjb.QueryType = "";
            this.cboXsjb.Size = new System.Drawing.Size(121, 21);
            this.cboXsjb.TabIndex = 3;
            this.cboXsjb.Tag = this.cell6;
            this.cboXsjb.UseCustomerParameter = false;
            this.cboXsjb.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboXsjb.BeforeDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(this.cboXsjb_BeforeDropDownClick);
            this.cboXsjb.SelectedValueChanged += new System.EventHandler(this.cboXsjb_SelectedValueChanged);
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.InnerControl = null;
            // 
            // cell8
            // 
            this.cell8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell8.InnerControl = null;
            this.cell8.Text = "显示主要指标:";
            this.cell8.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // cell9
            // 
            this.cell9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell9.InnerControl = this.cboXszyzb;
            // 
            // cboXszyzb
            // 
            this.cboXszyzb.AddedSelItemName = "";
            this.cboXszyzb.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.cboXszyzb.AutoScroll = true;
            // 
            // 
            // 
            this.cboXszyzb.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboXszyzb.DisplayName = "C_PLAN_NAME";
            this.cboXszyzb.DisplayValue = "C_PLAN_CODE";
            this.cboXszyzb.FilterCond = "";
            this.cboXszyzb.IsRefresh = true;
            this.cboXszyzb.IsFillDecimal = false;
            this.cboXszyzb.Location = new System.Drawing.Point(470, 7);
            this.cboXszyzb.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataList";
            controlMethodInfo1.MethodParams = null;
            controlMethodInfo1.MethodParamValues = null;
            controlMethodInfo1.Methods = null;
            this.cboXszyzb.MethodInfo = controlMethodInfo1;
            this.cboXszyzb.Name = "cboXszyzb";
            this.cboXszyzb.NodeID = "C_PLAN_CODE";
            this.cboXszyzb.Parameter = "C_PLAN_NAME";
            this.cboXszyzb.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IndentOnly;
            this.cboXszyzb.PrefixBackColor = System.Drawing.Color.White;
            this.cboXszyzb.QueryCond = "";
            this.cboXszyzb.QueryType = "";
            this.cboXszyzb.Size = new System.Drawing.Size(99, 21);
            this.cboXszyzb.SortColumn = "C_PLAN_CODE";
            this.cboXszyzb.TabIndex = 4;
            this.cboXszyzb.Tag = this.cell9;
            this.cboXszyzb.YssAssociaType = YssElecReco.Context.AssociaType.base_erzyzb;
            this.cboXszyzb.YssCaption = "显示主要指标";
            // 
            // cellBtnSzzb
            // 
            this.cellBtnSzzb.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cellBtnSzzb.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cellBtnSzzb.InnerControl = this.btnSzzbfa;
            // 
            // btnSzzbfa
            // 
            this.btnSzzbfa.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("btnSzzbfa.BackgroundImage")));
            this.btnSzzbfa.BackgroundImageSize = new System.Drawing.Size(22, 22);
            // 
            // 
            // 
            this.btnSzzbfa.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.btnSzzbfa.Border.Bottom = false;
            this.btnSzzbfa.Border.Left = false;
            this.btnSzzbfa.Border.Right = false;
            this.btnSzzbfa.Border.Top = false;
            this.btnSzzbfa.Location = new System.Drawing.Point(570, 7);
            this.btnSzzbfa.Name = "btnSzzbfa";
            this.btnSzzbfa.Size = new System.Drawing.Size(24, 22);
            this.btnSzzbfa.TabIndex = 7;
            this.btnSzzbfa.Tag = this.cellBtnSzzb;
            this.btnSzzbfa.Click += new System.EventHandler(this.btnSzzbfa_Click);
            // 
            // cell10
            // 
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.InnerControl = null;
            // 
            // cell11
            // 
            this.cell11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell11.InnerControl = this.chkCopySameData;
            // 
            // chkCopySameData
            // 
            this.chkCopySameData.AutoSize = false;
            this.chkCopySameData.Location = new System.Drawing.Point(615, 7);
            this.chkCopySameData.Name = "chkCopySameData";
            this.chkCopySameData.Size = new System.Drawing.Size(199, 22);
            this.chkCopySameData.TabIndex = 8;
            this.chkCopySameData.Tag = this.cell11;
            this.chkCopySameData.Text = "对账一致时自动填写对方数据";
            this.chkCopySameData.CheckedChanged += new System.EventHandler(this.chkCopySameData_CheckedChanged);
            // 
            // column4
            // 
            this.column4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column4.MaxPrintWidth = 0;
            this.column4.Width = 20;
            // 
            // column5
            // 
            this.column5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column5.MaxPrintWidth = 0;
            this.column5.Width = 65;
            // 
            // column6
            // 
            this.column6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column6.MaxPrintWidth = 0;
            this.column6.Width = 100;
            // 
            // column7
            // 
            this.column7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column7.MaxPrintWidth = 0;
            this.column7.Width = 20;
            // 
            // column8
            // 
            this.column8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column8.MaxPrintWidth = 0;
            this.column8.Width = 90;
            // 
            // column9
            // 
            this.column9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column9.MaxPrintWidth = 0;
            this.column9.Width = 100;
            // 
            // ColbtnSzzb
            // 
            this.ColbtnSzzb.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.ColbtnSzzb.MaxPrintWidth = 0;
            this.ColbtnSzzb.Width = 25;
            // 
            // column10
            // 
            this.column10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column10.MaxPrintWidth = 0;
            this.column10.Width = 20;
            // 
            // column11
            // 
            this.column11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column11.MaxPrintWidth = 0;
            this.column11.Width = 200;

            // 
            // tabPageRptLog
            // 
            this.tabPageRptLog.Name = "tabPageRptLog";
            this.tabPageRptLog.TabCloseButtonVisible = false;
            this.tabPageRptLog.Text = "接收数据明细";

            // 
            // tbXML
            // 
            this.tbXML.BackColor = System.Drawing.Color.White;
            this.tbXML.Controls.Add(this.xmlWebBrowser);
            this.tbXML.DefaultToolStripItems = ((Yss.KTable.Enums.SysToolStripItems)((((((((((((Yss.KTable.Enums.SysToolStripItems.ShowHideColumn | Yss.KTable.Enums.SysToolStripItems.GroupByColumn)
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
            this.tbXML.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tbXML.Location = new System.Drawing.Point(1, 1);
            this.tbXML.Name = "tbXML";
            this.tbXML.Size = new System.Drawing.Size(888, 389);
            this.tbXML.TabIndex = 0;
            this.tbXML.Text = "table1";
            // 
            // xmlWebBrowser
            // 
            this.xmlWebBrowser.Dock = System.Windows.Forms.DockStyle.Fill;
            this.xmlWebBrowser.Location = new System.Drawing.Point(0, 0);
            this.xmlWebBrowser.MinimumSize = new System.Drawing.Size(20, 20);
            this.xmlWebBrowser.Name = "xmlWebBrowser";
            this.xmlWebBrowser.Size = new System.Drawing.Size(888, 389);
            this.xmlWebBrowser.TabIndex = 2;
            // 
            // Frm_ELEC_DETAIL_L
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(892, 562);
            this.DoubleBuffered = true;
            this.Name = "Frm_ELEC_DETAIL_L";
            clsPageInation1.bCreateQuery = true;
            clsPageInation1.CurrPage = 1;
            clsPageInation1.IsUsePage = false;
            clsPageInation1.PageCount = -1;
            clsPageInation1.PageSize = 100;
            clsPageInation1.sQueryStr = "";
            clsPageInation1.sUrl = "";
            clsPageInation1.TotalNum = 0;
            this.Page = clsPageInation1;
            this.ShowLeftPanel = false;
            this.ShowPageInation = false;
            this.Text = "对账详细信息";
            this.Load += new System.EventHandler(this.Frm_ELEC_DETAIL_L_Load);
            this.tabCtrlDataMain.ResumeLayout(false);
            this.tabPageDefault.ResumeLayout(false);
            this.pnlDetails.ResumeLayout(false);
            this.tbFilter.ResumeLayout(false);
            this.pnlFilter.ResumeLayout(false);
            this.pnlHost.ResumeLayout(false);
            this.pnlHost.PerformLayout();
            this.navBarLeft.ResumeLayout(false);
            this.panelEx1.ResumeLayout(false);
            this.navigateItemMain.ResumeLayout(false);
            this.pnlLeftMain.ResumeLayout(false);
            this.pnlSearchLeft.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.tabPageXml.ResumeLayout(false);
            this.tabPageState.ResumeLayout(false);
            this.tabPageState.PerformLayout();
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            this.tbXML.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
        private Yss.KTable.Models.Row row1;
        private Yss.Controls.TabPage tabPageXml;
        private Yss.Controls.TabPage tabPageResult;
        private Yss.Controls.TabPage tabPageState;
        private Yss.Controls.TabPage tabPageTgh;
        private YssSelCombox cboResultType;
        private Yss.KTable.Models.Column column3;
        private Yss.KTable.Models.Cell cell3;
        private Yss.KTable.Models.Row row2;
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Cell cell2;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Label label14;
        private System.Windows.Forms.Label label13;
        private System.Windows.Forms.Label label15;
        private System.Windows.Forms.Label label16;
        private System.Windows.Forms.Label label17;
        private Yss.Controls.LabelEx labelEx2;
        private Yss.Controls.LabelEx labelEx6;
        private Yss.Controls.LabelEx labelEx5;
        private Yss.Controls.LabelEx labelEx4;
        private Yss.Controls.LabelEx labelEx3;
        private Yss.KTable.Models.Column column4;
        private Yss.KTable.Models.Column column5;
        private Yss.KTable.Models.Column column6;
        private Yss.KTable.Models.Column column7;
        private Yss.KTable.Models.Column column8;
        private Yss.KTable.Models.Column column9;
        private YssSelCombox cboXszyzb;
        private YssSelCombox cboXsjb;
        private Yss.KTable.Models.Cell cell6;
        private Yss.KTable.Models.Cell cell4;
        private Yss.KTable.Models.Cell cell5;
        private Yss.KTable.Models.Cell cell7;
        private Yss.KTable.Models.Cell cell8;
        private Yss.KTable.Models.Cell cell9;
        private Yss.KTable.Models.Column ColbtnSzzb;
        private Yss.KTable.Models.Column column10;
        private Yss.KTable.Models.Cell cellBtnSzzb;
        private Yss.KTable.Models.Cell cell10;
        private Yss.Controls.ImageButton btnSzzbfa;
        private Yss.KTable.Models.Column column11;
        private Yss.Controls.CheckBox chkCopySameData;
        private Yss.KTable.Models.Cell cell11;
        private Yss.KTable.Models.Table tbXML;
        private System.Windows.Forms.WebBrowser xmlWebBrowser;
        private Yss.Controls.TabPage tabPageRptLog;
    }
}