namespace YssInformation.Bi.Account.Form
{
    using FAST.Core.BaseControl;

    /// <summary>
    /// 资金账户信息设置 浏览界面
    /// 创建人：chenyoulong
    /// 创建日期：2012117
    /// 发布版本：v1.0.0.4
    /// </summary>
    public partial class Frm_FUND_ACC_L
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Column column1;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Column column2;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Column column3;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Column column4;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Column column5;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Row row1;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Row row2;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Cell cell1;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Cell cell2;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Cell cell3;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Cell cell4;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Cell cell5;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KRichEx.YssTextBox txtOpenName;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private YssSelCombox cboCury;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Column column6;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Column column7;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Column column8;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Cell cell6;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Cell cell7;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Cell cell8;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KRichEx.YssTextBox txtOpenAccNo;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Column column9;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Column column10;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Cell cell10;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Cell cell9;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Row row3;

        ///// <summary>
        ///// This is a variable .
        ///// </summary>
        ////private Yss.KTable.Models.Cell cell11;

        ///// <summary>
        ///// This is a variable .
        ///// </summary>
        ////private Yss.KTable.Models.Cell cell12;

        ///// <summary>
        ///// This is a variable .
        ///// </summary>
        //// private Yss.KTable.Models.Cell cell13;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Cell cell14;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Cell cell15;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Cell cell16;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Cell cell17;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Cell cell18;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Cell cell19;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private Yss.KTable.Models.Cell cell20;

        ///// <summary>
        ///// This is a variable .
        ///// </summary>
        ////private YssSelCombox cboDateRange;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private YssSelCombox cboAccType;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private YssSelCombox cboOrgan;

        /// <summary>
        /// This is a variable .
        /// </summary>
        private System.Windows.Forms.CheckBox showPubAcc;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (this.components != null))
            {
                this.components.Dispose();
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_FUND_ACC_L));
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.txtOpenAccNo = new Yss.KRichEx.YssTextBox();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.txtOpenName = new Yss.KRichEx.YssTextBox();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cboCury = new FAST.Core.BaseControl.YssSelCombox();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.cboAccType = new FAST.Core.BaseControl.YssSelCombox();
            this.column6 = new Yss.KTable.Models.Column();
            this.column7 = new Yss.KTable.Models.Column();
            this.column8 = new Yss.KTable.Models.Column();
            this.column9 = new Yss.KTable.Models.Column();
            this.column10 = new Yss.KTable.Models.Column();
            this.row3 = new Yss.KTable.Models.Row();
            this.cell14 = new Yss.KTable.Models.Cell();
            this.cell15 = new Yss.KTable.Models.Cell();
            this.cboOrgan = new FAST.Core.BaseControl.YssSelCombox();
            this.cell18 = new Yss.KTable.Models.Cell();
            this.cell19 = new Yss.KTable.Models.Cell();
            this.cell20 = new Yss.KTable.Models.Cell();
            this.cell16 = new Yss.KTable.Models.Cell();
            this.cell17 = new Yss.KTable.Models.Cell();
            this.showPubAcc = new System.Windows.Forms.CheckBox();
            this.txtOpenAddr = new Yss.KRichEx.YssTextBox();
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
            this.SuspendLayout();
            // 
            // tabCtrlDataMain
            // 
            this.tabCtrlDataMain.Size = new System.Drawing.Size(772, 218);
            this.tabCtrlDataMain.TabHeight = 0;
            this.tabCtrlDataMain.Controls.SetChildIndex(this.tabPageDefault, 0);
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
            this.pnlDetails.Size = new System.Drawing.Size(774, 265);
            // 
            // tabCtrlDataDetail
            // 
            this.tabCtrlDataDetail.Size = new System.Drawing.Size(772, 263);
            // 
            // expandSplitterDetails
            // 
            this.expandSplitterDetails.Size = new System.Drawing.Size(774, 5);
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
            this.column10});
            this.tbFilter.Controls.Add(this.showPubAcc);
            this.tbFilter.Controls.Add(this.cboAccType);
            this.tbFilter.Controls.Add(this.txtOpenAccNo);
            this.tbFilter.Controls.Add(this.cboOrgan);
            this.tbFilter.Controls.Add(this.cboCury);
            this.tbFilter.Controls.Add(this.txtOpenName);
            this.tbFilter.Cursor = System.Windows.Forms.Cursors.Default;
            this.tbFilter.GridLineColor = System.Drawing.Color.White;
            this.tbFilter.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3});
            this.tbFilter.Size = new System.Drawing.Size(774, 65);
            // 
            // tbMain
            // 
            this.tbMain.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(255)))));
            // 
            // 
            // 
            this.tbMain.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.tbMain.Border.Bottom = false;
            this.tbMain.Border.Left = false;
            this.tbMain.Border.Right = false;
            this.tbMain.Border.Top = false;
            this.tbMain.Cursor = System.Windows.Forms.Cursors.Default;
            this.tbMain.DefaultToolStripItems = ((Yss.KTable.Enums.SysToolStripItems)(((((Yss.KTable.Enums.SysToolStripItems.ShowHideColumn | Yss.KTable.Enums.SysToolStripItems.GroupByColumn)
                        | Yss.KTable.Enums.SysToolStripItems.PrintSetup)
                        | Yss.KTable.Enums.SysToolStripItems.PrintPreview)
                        | Yss.KTable.Enums.SysToolStripItems.GardingMenu)));
            this.tbMain.Size = new System.Drawing.Size(770, 216);
            // 
            // pnlFilter
            // 
            this.pnlFilter.Controls.Add(this.txtOpenAddr);
            this.pnlFilter.Size = new System.Drawing.Size(774, 65);
            this.pnlFilter.Style.Alignment = System.Drawing.StringAlignment.Center;
            this.pnlFilter.Style.BackColor1.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBackground;
            this.pnlFilter.Style.BackColor2.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBackground2;
            this.pnlFilter.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            this.pnlFilter.Style.BorderColor.Color = System.Drawing.Color.FromArgb(((int)(((byte)(102)))), ((int)(((byte)(136)))), ((int)(((byte)(184)))));
            this.pnlFilter.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            this.pnlFilter.Style.GradientAngle = 90;
            this.pnlFilter.Controls.SetChildIndex(this.tbFilter, 0);
            this.pnlFilter.Controls.SetChildIndex(this.txtOpenAddr, 0);
            // 
            // pnlHost
            // 
            this.pnlHost.Location = new System.Drawing.Point(0, 98);
            this.pnlHost.Size = new System.Drawing.Size(774, 246);
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
            this.tbLeftMain.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(255)))));
            // 
            // 
            // 
            this.tbLeftMain.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.tbLeftMain.Border.Bottom = false;
            this.tbLeftMain.Border.Left = false;
            this.tbLeftMain.Border.Right = false;
            this.tbLeftMain.Border.Top = false;
            this.tbLeftMain.DefaultToolStripItems = ((Yss.KTable.Enums.SysToolStripItems)((Yss.KTable.Enums.SysToolStripItems.GardingMenu | Yss.KTable.Enums.SysToolStripItems.ShowCancelChildRowsChecked)));
            this.tbLeftMain.SearchType = Yss.KTable.Models.SearchType.None;
            this.tbLeftMain.ShowCheckBox = true;
            this.tbLeftMain.Size = new System.Drawing.Size(243, 524);
            // 
            // btnArrow
            // 
            // 
            // 
            // 
            this.btnArrow.BackgroundStyle.BorderTopColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.DockSiteBackColor2;
            this.btnArrow.Location = new System.Drawing.Point(749, 0);
            // 
            // panelEx1
            // 
            this.panelEx1.Size = new System.Drawing.Size(774, 33);
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
            this.btnBar.Size = new System.Drawing.Size(749, 33);
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
            this.pnlMain.Size = new System.Drawing.Size(774, 614);
            // 
            // yssPanel1
            // 
            // 
            // 
            // 
            this.yssPanel1.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.yssPanel1.Size = new System.Drawing.Size(1026, 614);
            // 
            // column1
            // 
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.Name = "column1";
            this.column1.Width = 75;
            // 
            // column2
            // 
            this.column2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column2.Name = "column2";
            this.column2.Width = 110;
            // 
            // column3
            // 
            this.column3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column3.Name = "column3";
            this.column3.Width = 10;
            // 
            // column4
            // 
            this.column4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column4.Name = "column4";
            this.column4.Width = 70;
            // 
            // column5
            // 
            this.column5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column5.Name = "column5";
            this.column5.Width = 110;
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row1.FullRowSelected = false;
            this.row1.Height = 7;
            this.row1.Name = "row1";
            this.row1.Text = null;
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
            this.cell10});
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row2.FullRowSelected = false;
            this.row2.Height = 25;
            this.row2.Name = "row2";
            this.row2.Text = null;
            // 
            // cell1
            // 
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.InnerControl = null;
            this.cell1.Name = "cell1";
            this.cell1.Text = " 开户账号：";
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.txtOpenAccNo;
            this.cell2.Name = "cell2";
            // 
            // txtOpenAccNo
            // 
            this.txtOpenAccNo.Location = new System.Drawing.Point(75, 7);
            this.txtOpenAccNo.Name = "txtOpenAccNo";
            this.txtOpenAccNo.Size = new System.Drawing.Size(109, 21);
            this.txtOpenAccNo.TabIndex = 3;
            this.txtOpenAccNo.Tag = this.cell2;
            this.txtOpenAccNo.YssLength = 30;
            // 
            // cell3
            // 
            this.cell3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell3.InnerControl = null;
            this.cell3.Name = "cell3";
            // 
            // cell4
            // 
            this.cell4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell4.InnerControl = null;
            this.cell4.Name = "cell4";
            this.cell4.Text = "开户名称：";
            // 
            // cell5
            // 
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.txtOpenName;
            this.cell5.Name = "cell5";
            // 
            // txtOpenName
            // 
            this.txtOpenName.Location = new System.Drawing.Point(265, 7);
            this.txtOpenName.Margin = new System.Windows.Forms.Padding(0);
            this.txtOpenName.Name = "txtOpenName";
            this.txtOpenName.Size = new System.Drawing.Size(109, 21);
            this.txtOpenName.TabIndex = 1;
            this.txtOpenName.Tag = this.cell5;
            this.txtOpenName.YssCaption = "开户名称";
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.InnerControl = null;
            this.cell6.Name = "cell6";
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.InnerControl = null;
            this.cell7.Name = "cell7";
            this.cell7.Text = "货币代码：";
            // 
            // cell8
            // 
            this.cell8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell8.InnerControl = this.cboCury;
            this.cell8.Name = "cell8";
            // 
            // cboCury
            // 
            this.cboCury.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboCury.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboCury.DataCollapse = true;
            this.cboCury.DisplayName = "C_DC_NAME";
            this.cboCury.DisplayValue = "C_DC_CODE";
            this.cboCury.IsFillDecimal = false;
            this.cboCury.Location = new System.Drawing.Point(455, 7);
            this.cboCury.Margin = new System.Windows.Forms.Padding(0);
            this.cboCury.MethodInfo.MethodName = "getDataList";
            this.cboCury.Name = "cboCury";
            this.cboCury.Parameter = "C_DC_NAME";
            this.cboCury.PrefixBackColor = System.Drawing.Color.White;
            this.cboCury.Size = new System.Drawing.Size(109, 21);
            this.cboCury.TabIndex = 2;
            this.cboCury.Tag = this.cell8;
            this.cboCury.YssAssociaType = YssInformation.Support.Context.AssociaType.base_currency;
            this.cboCury.YssCaption = "交易币种";
            // 
            // cell9
            // 
            this.cell9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell9.InnerControl = null;
            this.cell9.Name = "cell9";
            this.cell9.Text = "账户类型： ";
            // 
            // cell10
            // 
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.InnerControl = this.cboAccType;
            this.cell10.Name = "cell10";
            // 
            // cboAccType
            // 
            this.cboAccType.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboAccType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboAccType.DisplayName = "C_DAC_NAME";
            this.cboAccType.DisplayValue = "C_DAC_CODE";
            this.cboAccType.IsFillDecimal = false;
            this.cboAccType.KTableTree = true;
            this.cboAccType.Location = new System.Drawing.Point(640, 7);
            this.cboAccType.Margin = new System.Windows.Forms.Padding(0);
            this.cboAccType.MethodInfo.MethodName = "getDataList";
            this.cboAccType.Name = "cboAccType";
            this.cboAccType.NodeID = "C_DAC_CODE";
            this.cboAccType.Parameter = "C_DAC_NAME";
            this.cboAccType.ParaNodeID = "C_DAC_CODE_P";
            this.cboAccType.PrefixBackColor = System.Drawing.Color.White;
            this.cboAccType.SelectMode = Yss.KRichEx.AutoFilter.SelectMode.Any;
            this.cboAccType.ShowCheckBox = true;
            this.cboAccType.Size = new System.Drawing.Size(109, 21);
            this.cboAccType.SortColumn = "N_ORDER";
            this.cboAccType.TabIndex = 66;
            this.cboAccType.Tag = this.cell10;
            this.cboAccType.YssAssociaType = YssInformation.Support.Context.AssociaType.base_accountType;
            this.cboAccType.YssCaption = "账户类型";
            // 
            // column6
            // 
            this.column6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column6.Name = "column6";
            this.column6.Width = 10;
            // 
            // column7
            // 
            this.column7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column7.Name = "column7";
            this.column7.Width = 70;
            // 
            // column8
            // 
            this.column8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column8.Name = "column8";
            this.column8.Width = 110;
            // 
            // column9
            // 
            this.column9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column9.Name = "column9";
            this.column9.Width = 75;
            // 
            // column10
            // 
            this.column10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column10.Name = "column10";
            this.column10.Width = 110;
            // 
            // row3
            // 
            this.row3.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell14,
            this.cell15,
            this.cell18,
            this.cell19,
            this.cell20,
            this.cell16,
            this.cell17});
            this.row3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row3.FullRowSelected = false;
            this.row3.Height = 25;
            this.row3.Name = "row3";
            this.row3.Text = null;
            // 
            // cell14
            // 
            this.cell14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell14.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell14.InnerControl = null;
            this.cell14.Name = "cell14";
            this.cell14.Text = " 开户机构：";
            // 
            // cell15
            // 
            this.cell15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell15.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell15.InnerControl = this.cboOrgan;
            this.cell15.Name = "cell15";
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
            this.cboOrgan.IsFillDecimal = false;
            this.cboOrgan.IsRefresh = false;
            this.cboOrgan.Location = new System.Drawing.Point(75, 32);
            this.cboOrgan.Margin = new System.Windows.Forms.Padding(0);
            this.cboOrgan.MethodInfo.MethodName = "getDataList";
            this.cboOrgan.Name = "cboOrgan";
            this.cboOrgan.Parameter = "C_ORG_CODE~C_ORG_NAME";
            this.cboOrgan.PrefixBackColor = System.Drawing.Color.White;
            this.cboOrgan.ShowRefresh = false;
            this.cboOrgan.Size = new System.Drawing.Size(109, 21);
            this.cboOrgan.TabIndex = 6;
            this.cboOrgan.Tag = this.cell15;
            this.cboOrgan.UseCustomerParameter = true;
            this.cboOrgan.YssAssociaType = FAST.Core.Context.AssociaFAST.NULL;
            this.cboOrgan.YssCaption = "机构代码";
            this.cboOrgan.YssKiloDelimiter = true;
            this.cboOrgan.AfterDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(this.cboOrgan_AfterDropDownClick);
            // 
            // cell18
            // 
            this.cell18.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell18.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell18.InnerControl = null;
            this.cell18.Name = "cell18";
            // 
            // cell19
            // 
            this.cell19.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell19.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell19.InnerControl = null;
            this.cell19.Name = "cell19";
            this.cell19.Text = "开 户 行：";
            // 
            // cell20
            // 
            this.cell20.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell20.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell20.InnerControl = null;
            this.cell20.Name = "cell20";
            // 
            // cell16
            // 
            this.cell16.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell16.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell16.InnerControl = null;
            this.cell16.Name = "cell16";
            // 
            // cell17
            // 
            this.cell17.ColSpan = 2;
            this.cell17.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell17.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell17.InnerControl = this.showPubAcc;
            this.cell17.Name = "cell17";
            // 
            // showPubAcc
            // 
            this.showPubAcc.Location = new System.Drawing.Point(385, 32);
            this.showPubAcc.Name = "showPubAcc";
            this.showPubAcc.Size = new System.Drawing.Size(179, 24);
            this.showPubAcc.TabIndex = 12;
            this.showPubAcc.Tag = this.cell17;
            this.showPubAcc.Text = "展示公共户";
            this.showPubAcc.UseVisualStyleBackColor = true;
            // 
            // txtOpenAddr
            // 
            this.txtOpenAddr.Location = new System.Drawing.Point(265, 35);
            this.txtOpenAddr.Margin = new System.Windows.Forms.Padding(0);
            this.txtOpenAddr.Name = "txtOpenAddr";
            this.txtOpenAddr.Size = new System.Drawing.Size(109, 21);
            this.txtOpenAddr.TabIndex = 12;
            this.txtOpenAddr.Tag = this.cell5;
            this.txtOpenAddr.YssCaption = "开户名称";
            // 
            // Frm_FUND_ACC_L
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1026, 614);
            this.DoubleBuffered = true;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "Frm_FUND_ACC_L";
            this.ShowFilterPanel = true;
            this.ShowLeftSearchPanel = true;
            this.ShowPortSet = true;
            this.Text = "产品账户信息";
            this.Load += new System.EventHandler(this.Frm_FUND_ACC_L_Load);
            this.tabCtrlDataMain.ResumeLayout(false);
            this.tabPageDefault.ResumeLayout(false);
            this.pnlDetails.ResumeLayout(false);
            this.tbFilter.ResumeLayout(false);
            this.pnlFilter.ResumeLayout(false);
            this.pnlFilter.PerformLayout();
            this.pnlHost.ResumeLayout(false);
            this.pnlHost.PerformLayout();
            this.navBarLeft.ResumeLayout(false);
            this.panelEx1.ResumeLayout(false);
            this.navigateItemMain.ResumeLayout(false);
            this.pnlLeftMain.ResumeLayout(false);
            this.pnlSearchLeft.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private Yss.KRichEx.YssTextBox txtOpenAddr;
    }
}