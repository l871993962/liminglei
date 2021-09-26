using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;

using FAST.Core.Resource;

namespace YssSecInformation.Ac.Etfskep.Form
{
    partial class Frm_ETF_GP_BAKT_L
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_ETF_GP_BAKT_L));
            this.cell2 = new Yss.KTable.Models.Cell();
            this.cbosec = new FAST.Core.BaseControl.YssSelCombox();
            this.dtpTradeDate = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.column6 = new Yss.KTable.Models.Column();
            this.column7 = new Yss.KTable.Models.Column();
            this.column8 = new Yss.KTable.Models.Column();
            this.column9 = new Yss.KTable.Models.Column();
            this.txtJJcode = new Yss.KRichEx.YssTextBox();
            this.tbFilter.SuspendLayout();
            this.pnlFilter.SuspendLayout();
            ////((System.ComponentModel.ISupportInitialize)(this.barFormStatus)).BeginInit();
            this.barFormStatus.SuspendLayout();
            this.pnlHost.SuspendLayout();
            this.navBarLeft.SuspendLayout();
            this.panelEx1.SuspendLayout();
            this.navigateItemMain.SuspendLayout();
            this.pnlLeftMain.SuspendLayout();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.SuspendLayout();
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
            this.tbFilter.Border.Top = true;
            this.tbFilter.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.column1,
            this.column2,
            this.column3,
            this.column4,
            this.column5,
            this.column6,
            this.column7,
            this.column8,
            this.column9});
            this.tbFilter.Controls.Add(this.cbosec);
            this.tbFilter.Controls.Add(this.dtpTradeDate);
            this.tbFilter.Controls.Add(this.txtJJcode);
            this.tbFilter.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2});
            this.tbFilter.Size = new System.Drawing.Size(875, 40);
            // 
            // tbMain
            // 
            this.tbMain.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(255)))));
            // 
            // 
            // 
            this.tbMain.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.tbMain.Border.Bottom = true;
            this.tbMain.Border.Left = false;
            this.tbMain.Border.Right = false;
            this.tbMain.Border.Top = true;
            this.tbMain.DefaultToolStripItems = ((Yss.KTable.Enums.SysToolStripItems)((((Yss.KTable.Enums.SysToolStripItems.ShowHideColumn | Yss.KTable.Enums.SysToolStripItems.GroupByColumn)
                        | Yss.KTable.Enums.SysToolStripItems.PrintSetup)
                        | Yss.KTable.Enums.SysToolStripItems.PrintPreview)));
            this.tbMain.Size = new System.Drawing.Size(875, 507);
            // 
            // pnlFilter
            // 
            this.pnlFilter.Size = new System.Drawing.Size(875, 40);
            this.pnlFilter.Style.Alignment = System.Drawing.StringAlignment.Center;
            this.pnlFilter.Style.BackColor1.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBackground;
            this.pnlFilter.Style.BackColor2.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBackground2;
            this.pnlFilter.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            this.pnlFilter.Style.BorderColor.Color = System.Drawing.Color.FromArgb(((int)(((byte)(102)))), ((int)(((byte)(136)))), ((int)(((byte)(184)))));
            this.pnlFilter.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            this.pnlFilter.Style.GradientAngle = 90;
            // 
            // barFormStatus
            // 
            this.barFormStatus.Size = new System.Drawing.Size(875, 28);
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
            this.pnlHost.Size = new System.Drawing.Size(875, 507);
            // 
            // cboPageSize
            // 
            ////this.cboPageSize.Location = new System.Drawing.Point(413, 2);
            // 
            // txtSearch
            // 
            // 
            // 
            // 
            this.txtSearch.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.txtSearch.Border.Bottom = true;
            this.txtSearch.Border.Left = true;
            this.txtSearch.Border.Right = true;
            this.txtSearch.Border.Top = true;
            this.txtSearch.Size = new System.Drawing.Size(235, 21);
            // 
            // navBarLeft
            // 
            // 
            // 
            // 
            this.navBarLeft.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(164)))), ((int)(((byte)(187)))), ((int)(((byte)(217)))));
            this.navBarLeft.Border.Bottom = true;
            this.navBarLeft.Border.Left = true;
            this.navBarLeft.Border.Right = true;
            this.navBarLeft.Border.Top = true;
            this.navBarLeft.Size = new System.Drawing.Size(245, 549);
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
            this.tbLeftMain.Location = new System.Drawing.Point(1, 28);
            this.tbLeftMain.ShowCheckBox = true;
            this.tbLeftMain.Size = new System.Drawing.Size(243, 520);
            // 
            // btnArrow
            // 
            // 
            // 
            // 
            this.btnArrow.BackgroundStyle.BorderTopColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.DockSiteBackColor2;
            this.btnArrow.Location = new System.Drawing.Point(850, 0);
            // 
            // panelEx1
            // 
            this.panelEx1.Size = new System.Drawing.Size(875, 33);
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
            ////this.txtToPage.Location = new System.Drawing.Point(625, 2);
            // 
            // barPort
            // 
            // 
            // btnBar
            // 
            this.btnBar.Size = new System.Drawing.Size(850, 33);
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
            // pnlMain
            // 
            this.pnlMain.Location = new System.Drawing.Point(0, 0);
            this.pnlMain.Size = new System.Drawing.Size(875, 614);
            //this.pnlMain.Style.Alignment = System.Drawing.StringAlignment.Center;
            //this.pnlMain.Style.BackColor1.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.DockSiteBackColor;
            //this.pnlMain.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            //this.pnlMain.Style.BorderColor.Color = System.Drawing.Color.FromArgb(((int)(((byte)(132)))), ((int)(((byte)(152)))), ((int)(((byte)(178)))));
            //this.pnlMain.Style.BorderSide = YssDevComponents.DotNetBar.eBorderSide.Top;
            //this.pnlMain.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            //this.pnlMain.Style.GradientAngle = 90;
            // 
            // yssPanel1
            // 
            this.yssPanel1.Size = new System.Drawing.Size(900, 614);
            // 
            // hpAssist
            // 
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.cbosec;
            this.cell2.ToolTip = null;
            // 
            // cbosec
            // 
            this.cbosec.AddedSelItemName = "";
            this.cbosec.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cbosec.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cbosec.Border.Bottom = true;
            this.cbosec.Border.Left = true;
            this.cbosec.Border.Right = true;
            this.cbosec.Border.Top = true;
            this.cbosec.ClassName = "";
            this.cbosec.DisplayStyle = Yss.KRichEx.AutoFilter.DisplayStyle.Modal;
            this.cbosec.DllName = "dll";
            this.cbosec.FilterCond = "";
            this.cbosec.Location = new System.Drawing.Point(88, 10);
            this.cbosec.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataList";
            controlMethodInfo1.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo1.MethodParams")));
            controlMethodInfo1.MethodParamValues = null;
            controlMethodInfo1.Methods = null;
            this.cbosec.MethodInfo = controlMethodInfo1;
            this.cbosec.Name = "cbosec";
            this.cbosec.Padding = new System.Windows.Forms.Padding(1, 2, 1, 3);
            this.cbosec.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconIndent;
            this.cbosec.QueryCond = "GP,JJ";
            this.cbosec.QueryType = "CacheType";
            this.cbosec.Size = new System.Drawing.Size(119, 21);
            this.cbosec.TabIndex = 1;
            this.cbosec.Tag = this.cell2;
            this.cbosec.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Value_Name;
            this.cbosec.YssAssociaType = YssSecInformation.Support.Context.AssociaType.sv_sec;
            this.cbosec.YssCaption = "交易证券";
            // 
            // dtpTradeDate
            // 
            this.dtpTradeDate.BackColor = System.Drawing.Color.Transparent;
            this.dtpTradeDate.Location = new System.Drawing.Point(304, 10);
            this.dtpTradeDate.Margin = new System.Windows.Forms.Padding(0);
            this.dtpTradeDate.Name = "dtpTradeDate";
            this.dtpTradeDate.ReadOnly = false;
            this.dtpTradeDate.Size = new System.Drawing.Size(234, 21);
            this.dtpTradeDate.TabIndex = 4;
            this.dtpTradeDate.Tag = this.cell5;
            this.dtpTradeDate.yssEnabled = true;
            this.dtpTradeDate.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpTradeDate.yssInterval = true;
            this.dtpTradeDate.yssLabelStr = "至";
            this.dtpTradeDate.yssShowCheckBox = false;
            this.dtpTradeDate.yssShowOperLable = false;
            this.dtpTradeDate.YssShowSecond = true;
            this.dtpTradeDate.yssTimeControl = false;
            this.dtpTradeDate.BusinessDate = true;
            // 
            // cell5
            // 
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.dtpTradeDate;
            this.cell5.ToolTip = null;
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row1.FullRowSelected = false;
            this.row1.GroupLineLength = 100;
            this.row1.GroupPosition = 16;
            this.row1.Height = 10;
            this.row1.ShowCheckBox = true;
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
            this.cell9});
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row2.FullRowSelected = false;
            this.row2.GroupLineLength = 100;
            this.row2.GroupPosition = 16;
            this.row2.Height = 25;
            this.row2.ShowCheckBox = true;
            this.row2.Text = null;
            // 
            // cell1
            // 
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.Text = "交易证券：";
            this.cell1.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.cell1.ToolTip = null;
            // 
            // cell3
            // 
            this.cell3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell3.ToolTip = null;
            // 
            // cell4
            // 
            this.cell4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell4.Text = "业务日期：";
            this.cell4.ToolTip = null;
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.ToolTip = null;
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.ToolTip = null;
            // 
            // cell8
            // 
            this.cell8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell8.Text = "基金代码：";
            this.cell8.ToolTip = null;
            // 
            // cell9
            // 
            this.cell9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell9.InnerControl = this.txtJJcode;
            this.cell9.ToolTip = null;
            // 
            // column1
            // 
            this.column1.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column1.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column1.DataPropertyName = null;
            this.column1.DataType = Yss.KTable.Enums.ColumnDataType.None;
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.column1.Tag = null;
            this.column1.Text = "";
            this.column1.Width = 88;
            // 
            // column2
            // 
            this.column2.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column2.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column2.DataPropertyName = null;
            this.column2.DataType = Yss.KTable.Enums.ColumnDataType.None;
            this.column2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.column2.Tag = null;
            this.column2.Text = "";
            this.column2.Width = 120;
            // 
            // column3
            // 
            this.column3.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column3.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column3.DataPropertyName = null;
            this.column3.DataType = Yss.KTable.Enums.ColumnDataType.None;
            this.column3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.column3.Tag = null;
            this.column3.Text = "";
            this.column3.Width = 30;
            // 
            // column4
            // 
            this.column4.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column4.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column4.DataPropertyName = null;
            this.column4.DataType = Yss.KTable.Enums.ColumnDataType.None;
            this.column4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.column4.Tag = null;
            this.column4.Text = "";
            this.column4.Width = 66;
            // 
            // column5
            // 
            this.column5.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column5.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column5.DataPropertyName = null;
            this.column5.DataType = Yss.KTable.Enums.ColumnDataType.None;
            this.column5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.column5.Tag = null;
            this.column5.Text = "";
            this.column5.Width = 235;
            // 
            // column6
            // 
            this.column6.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column6.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column6.DataPropertyName = null;
            this.column6.DataType = Yss.KTable.Enums.ColumnDataType.None;
            this.column6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.column6.Tag = null;
            this.column6.Text = "";
            this.column6.Width = 5;
            // 
            // column7
            // 
            this.column7.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column7.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column7.DataPropertyName = null;
            this.column7.DataType = Yss.KTable.Enums.ColumnDataType.None;
            this.column7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.column7.Tag = null;
            this.column7.Text = "";
            this.column7.Width = 10;
            // 
            // column8
            // 
            this.column8.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column8.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column8.DataPropertyName = null;
            this.column8.DataType = Yss.KTable.Enums.ColumnDataType.None;
            this.column8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.column8.Tag = null;
            this.column8.Text = "";
            this.column8.Width = 66;
            // 
            // column9
            // 
            this.column9.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column9.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column9.DataPropertyName = null;
            this.column9.DataType = Yss.KTable.Enums.ColumnDataType.None;
            this.column9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.column9.Tag = null;
            this.column9.Text = "";
            this.column9.Width = 120;
            // 
            // txtJJcode
            // 
            this.txtJJcode.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.txtJJcode.Border.Class = "TextBoxBorder";
            this.txtJJcode.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtJJcode.Location = new System.Drawing.Point(620, 10);
            this.txtJJcode.Name = "txtJJcode";
            this.txtJJcode.Size = new System.Drawing.Size(119, 21);
            this.txtJJcode.TabIndex = 12;
            this.txtJJcode.Tag = this.cell9;
            // 
            // Frm_ETF_GP_BAKT_L
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(900, 614);
            this.DoubleBuffered = true;
            this.Name = "Frm_ETF_GP_BAKT_L";
            this.ShowFilterPanel = true;
            this.ShowLeftPanel = false;
            this.ShowPortSet = true;
            this.Text = "ETF股票篮";
            this.tbFilter.ResumeLayout(false);
            this.pnlFilter.ResumeLayout(false);
            ////((System.ComponentModel.ISupportInitialize)(this.barFormStatus)).EndInit();
            this.barFormStatus.ResumeLayout(false);
            this.pnlHost.ResumeLayout(false);
            this.navBarLeft.ResumeLayout(false);
            this.panelEx1.ResumeLayout(false);
            this.navigateItemMain.ResumeLayout(false);
            this.pnlLeftMain.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private FAST.Core.BaseControl.YssDateTimeInterval dtpTradeDate;
        private Yss.KTable.Models.Row row1;
        private Yss.KTable.Models.Row row2;
        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
        private Yss.KTable.Models.Column column3;
        private Yss.KTable.Models.Column column4;
        private Yss.KTable.Models.Column column5;
        private Yss.KTable.Models.Column column6;
        private Yss.KTable.Models.Column column7;
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Cell cell2;
        private Yss.KTable.Models.Cell cell3;
        private Yss.KTable.Models.Cell cell4;
        private Yss.KTable.Models.Cell cell5;
        private Yss.KTable.Models.Column column8;
        private FAST.Core.BaseControl.YssSelCombox cbosec;
        private Yss.KTable.Models.Column column9;
        private Yss.KTable.Models.Cell cell9;
        private Yss.KTable.Models.Cell cell6;
        private Yss.KTable.Models.Cell cell7;
        private Yss.KTable.Models.Cell cell8;
        private Yss.KRichEx.YssTextBox txtJJcode;
    }
}