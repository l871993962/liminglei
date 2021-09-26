using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;

using FAST.Core.Resource;
namespace YssInformation.Bi.TaselNet.Form
{
    partial class Frm_SALES_NET_L
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_SALES_NET_L));
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo1 = new FAST.Core.BaseControl.ControlMethodInfo();
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.txtTANetCode = new Yss.KRichEx.YssTextBox();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.txtTANetName = new Yss.KRichEx.YssTextBox();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.row3 = new Yss.KTable.Models.Row();
            this.row4 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.column6 = new Yss.KTable.Models.Column();
            this.column7 = new Yss.KTable.Models.Column();
            this.column8 = new Yss.KTable.Models.Column();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cboNetType = new FAST.Core.BaseControl.YssSelCombox();
            this.tbFilter.SuspendLayout();
            this.pnlFilter.SuspendLayout();
            ////((System.ComponentModel.ISupportInitialize)(this.barFormStatus)).BeginInit();
            this.barFormStatus.SuspendLayout();
            this.pnlHost.SuspendLayout();
            this.pnlLeftMain.SuspendLayout();
            this.panelEx1.SuspendLayout();
            this.navigateItemMain.SuspendLayout();
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
            this.column8});
            this.tbFilter.Controls.Add(this.cboNetType);
            this.tbFilter.Controls.Add(this.txtTANetCode);
            this.tbFilter.Controls.Add(this.txtTANetName);
            // 
            // 
            // 
            this.tbFilter.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row3,
            this.row4});
            this.tbFilter.Size = new System.Drawing.Size(912, 40);
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
            // 
            // 
            // 
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2});
            this.tbMain.Size = new System.Drawing.Size(912, 529);
            // 
            // pnlFilter
            // 
            this.pnlFilter.Size = new System.Drawing.Size(912, 40);
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
            this.barFormStatus.Location = new System.Drawing.Point(0, 608);
            this.barFormStatus.Size = new System.Drawing.Size(912, 26);
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
            this.pnlHost.Size = new System.Drawing.Size(912, 529);
            // 
            // cboPageSize
            // 
            ////this.cboPageSize.Location = new System.Drawing.Point(439, 2);
            // 
            // txtSearch
            // 
            // 
            // 
            // 
            this.txtSearch.PrefixForeColor = System.Drawing.Color.Gray;
            //
            // 
            //tabCtrlDataMain
            //
            //
            this.tabCtrlDataMain.TabHeight = 0;
            //
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
            ////this.pnlLeftMain.Controls.SetChildIndex(this.barPort, 0);
            // 
            // tbLeftMain
            // 
            this.tbLeftMain.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(255)))));
            // 
            // 
            // 
            this.tbLeftMain.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(158)))), ((int)(((byte)(163)))), ((int)(((byte)(166)))));
            this.tbLeftMain.Border.Bottom = false;
            this.tbLeftMain.Border.Left = false;
            this.tbLeftMain.Border.Right = false;
            this.tbLeftMain.Border.Top = false;
            // 
            // 
            // 
            this.tbLeftMain.ShowCheckBox = true;
            this.tbLeftMain.Size = new System.Drawing.Size(166, 549);
            // 
            // btnArrow
            // 
            // 
            // 
            // 
            this.btnArrow.BackgroundStyle.BorderTop = YssDevComponents.DotNetBar.eStyleBorderType.Solid;
            this.btnArrow.BackgroundStyle.BorderTopColor = System.Drawing.Color.FromArgb(((int)(((byte)(102)))), ((int)(((byte)(136)))), ((int)(((byte)(184)))));
            this.btnArrow.BackgroundStyle.BorderTopWidth = 1;
            this.btnArrow.Location = new System.Drawing.Point(887, 0);
            // 
            // panelEx1
            // 
            this.panelEx1.Size = new System.Drawing.Size(912, 33);
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
            // 
            // barPort
            // 
            // 
            // btnBar
            // 
            this.btnBar.Size = new System.Drawing.Size(887, 33);
            // 
            // navigateExpandablePanel1
            // 
            // 
            // 
            // 
            // 
            // 
            // 
            this.navigateItemMain.Controls.SetChildIndex(this.tbLeftMain, 0);
            // 
            // pnlMain
            // 
            this.pnlMain.Location = new System.Drawing.Point(0, 0);
            this.pnlMain.Size = new System.Drawing.Size(912, 634);
            //this.pnlMain.Style.Alignment = System.Drawing.StringAlignment.Center;
            //this.pnlMain.Style.BackColor1.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.DockSiteBackColor;
            //this.pnlMain.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            //this.pnlMain.Style.BorderColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBorder;
            //this.pnlMain.Style.BorderSide = YssDevComponents.DotNetBar.eBorderSide.Top;
            //this.pnlMain.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            //this.pnlMain.Style.GradientAngle = 90;
            // 
            // yssPanel1
            // 
            this.yssPanel1.Size = new System.Drawing.Size(912, 634);
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
            this.column1.Width = 86;
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
            this.column4.Width = 66;
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
            this.row1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(100)))), ((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(255)))));
            this.row1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row1.ForeColor = System.Drawing.Color.Black;
            this.row1.FullRowSelected = true;
            this.row1.GroupLineLength = 100;
            this.row1.GroupPosition = -16;
            this.row1.Height = 10;
            this.row1.Key = null;
            this.row1.OwnTable = this.tbMain;
            this.row1.RowName = "row1";
            this.row1.ShowCheckBox = true;
            // 
            // row2
            // 
            this.row2.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(100)))), ((int)(((byte)(243)))), ((int)(((byte)(247)))), ((int)(((byte)(250)))));
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row2.ForeColor = System.Drawing.Color.Black;
            this.row2.FullRowSelected = true;
            this.row2.GroupLineLength = 100;
            this.row2.GroupPosition = -16;
            this.row2.Height = 25;
            this.row2.Key = null;
            this.row2.OwnTable = this.tbMain;
            this.row2.RowName = "row2";
            this.row2.ShowCheckBox = true;
            // 
            // txtTANetCode
            // 
            // 
            // 
            // 
            this.txtTANetCode.Border.Class = "TextBoxBorder";
            this.txtTANetCode.Location = new System.Drawing.Point(86, 7);
            this.txtTANetCode.Name = "txtTANetCode";
            this.txtTANetCode.Size = new System.Drawing.Size(120, 21);
            this.txtTANetCode.TabIndex = 0;
            this.txtTANetCode.Tag = this.cell2;
            this.txtTANetCode.Value = "";
            this.txtTANetCode.YssCaption = "网店代码";
            this.txtTANetCode.YssNumeric = "";
            // 
            // cell2
            // 
            this.cell2.BackColor = System.Drawing.Color.Empty;
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.txtTANetCode;
            this.cell2.Key = null;
            this.cell2.ToolTip = null;
            // 
            // txtTANetName
            // 
            // 
            // 
            // 
            this.txtTANetName.Border.Class = "TextBoxBorder";
            this.txtTANetName.Location = new System.Drawing.Point(304, 7);
            this.txtTANetName.Name = "txtTANetName";
            this.txtTANetName.Size = new System.Drawing.Size(120, 21);
            this.txtTANetName.TabIndex = 1;
            this.txtTANetName.Tag = this.cell5;
            this.txtTANetName.Value = "";
            this.txtTANetName.YssCaption = "网店名称";
            this.txtTANetName.YssNumeric = "";
            // 
            // cell5
            // 
            this.cell5.BackColor = System.Drawing.Color.Empty;
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.txtTANetName;
            this.cell5.Key = null;
            this.cell5.ToolTip = null;
            // 
            // row3
            // 
            this.row3.BackColor = System.Drawing.Color.Empty;
            this.row3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row3.ForeColor = System.Drawing.Color.Black;
            this.row3.FullRowSelected = false;
            this.row3.GroupLineLength = 100;
            this.row3.GroupPosition = -16;
            this.row3.Height = 7;
            this.row3.Key = null;
            this.row3.OwnTable = this.tbFilter;
            this.row3.RowName = "row1";
            this.row3.ShowCheckBox = true;
            // 
            // row4
            // 
            this.row4.BackColor = System.Drawing.Color.Empty;
            this.row4.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell1,
            this.cell2,
            this.cell3,
            this.cell4,
            this.cell5,
            this.cell6,
            this.cell7,
            this.cell8});
            this.row4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row4.ForeColor = System.Drawing.Color.Black;
            this.row4.FullRowSelected = false;
            this.row4.GroupLineLength = 100;
            this.row4.GroupPosition = -16;
            this.row4.Height = 25;
            this.row4.Key = null;
            this.row4.OwnTable = this.tbFilter;
            this.row4.RowName = "row2";
            this.row4.ShowCheckBox = true;
            // 
            // cell1
            // 
            this.cell1.BackColor = System.Drawing.Color.Empty;
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.Key = null;
            this.cell1.Text = "网点代码：";
            this.cell1.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.cell1.ToolTip = null;
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
            this.cell4.Text = "网点名称：";
            this.cell4.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.cell4.ToolTip = null;
            // 
            // column6
            // 
            this.column6.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column6.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column6.DataPropertyName = null;
            this.column6.ForeColor = System.Drawing.Color.Empty;
            this.column6.Tag = null;
            this.column6.Text = "";
            this.column6.Width = 30;
            // 
            // column7
            // 
            this.column7.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column7.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column7.DataPropertyName = null;
            this.column7.ForeColor = System.Drawing.Color.Empty;
            this.column7.Tag = null;
            this.column7.Text = "";
            this.column7.Width = 66;
            // 
            // column8
            // 
            this.column8.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column8.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column8.DataPropertyName = null;
            this.column8.ForeColor = System.Drawing.Color.Empty;
            this.column8.Tag = null;
            this.column8.Text = "";
            this.column8.Width = 122;
            // 
            // cell6
            // 
            this.cell6.BackColor = System.Drawing.Color.Empty;
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.cell6.ForeColor = System.Drawing.Color.Black;
            this.cell6.Key = null;
            this.cell6.ToolTip = null;
            // 
            // cell7
            // 
            this.cell7.BackColor = System.Drawing.Color.Empty;
            this.cell7.Font = new System.Drawing.Font("宋体", 9F);
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.Key = null;
            this.cell7.Text = "网点类型：";
            this.cell7.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.cell7.ToolTip = null;
            // 
            // cell8
            // 
            this.cell8.BackColor = System.Drawing.Color.Empty;
            this.cell8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.cell8.ForeColor = System.Drawing.Color.Black;
            this.cell8.InnerControl = this.cboNetType;
            this.cell8.Key = null;
            this.cell8.ToolTip = null;
            // 
            // cboNetType
            // 
            this.cboNetType.AddedSelItemName = "";
            this.cboNetType.BackColor = System.Drawing.Color.White;
            this.cboNetType.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.cboNetType.ClassName = "";
            this.cboNetType.DisplayName = "C_DV_NAME";
            this.cboNetType.DisplayValue = "C_DV_CODE";
            this.cboNetType.DllName = "YssControls.dll";
            this.cboNetType.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDown;
            this.cboNetType.FilterCond = "";
            this.cboNetType.IsFillDecimal = false;
            this.cboNetType.IsFocused = false;
            this.cboNetType.KTableTree = false;
            this.cboNetType.Location = new System.Drawing.Point(522, 7);
            this.cboNetType.Margin = new System.Windows.Forms.Padding(0);
            this.cboNetType.Name = "cboNetType";
            this.cboNetType.NodeID = "";
            this.cboNetType.Padding = new System.Windows.Forms.Padding(1, 3, 1, 3);
            this.cboNetType.Parameter = "C_DV_NAME";
            this.cboNetType.ParaNodeID = "";
            this.cboNetType.PasswordChar = '\0';
            this.cboNetType.PopupTitle = "数据筛选";
            // 
            // 
            controlMethodInfo1.MethodName = "getDataListByTypes";
            controlMethodInfo1.MethodParamValues = new string[] {
        "NET_TYPE,"};
            this.cboNetType.MethodInfo = controlMethodInfo1;
            this.cboNetType.QueryCond = "NET_TYPE";
            this.cboNetType.QueryType = "CacheType";
            this.cboNetType.RequestEveryTime = false;
            this.cboNetType.ShowCheckBox = false;
            this.cboNetType.ShowColumnHeader = false;
            this.cboNetType.Size = new System.Drawing.Size(121, 21);
            // 
            // 
            // 
            this.cboNetType.TabIndex = 3;
            this.cboNetType.Tag = this.cell8;
            this.cboNetType.TextAlign = System.Windows.Forms.HorizontalAlignment.Left;
            this.cboNetType.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
            this.cboNetType.TipText = "";
            this.cboNetType.TriggerTextLength = 1;
            this.cboNetType.UseErrorTip = true;
            ////this.cboNetType.YssAssociaType = YssInformation.Support.Context.AssociaType.pubvocabulary;
            this.cboNetType.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboNetType.YssCaption = "网点类型";
            this.cboNetType.YssIsMust = false;
            this.cboNetType.YssLength = 20;
            this.cboNetType.YssNumeric = "";
            this.cboNetType.YssReadOnly = false;
            this.cboNetType.YssShowButton = true;
            // 
            // Frm_SALES_NET_L
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(912, 634);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "Frm_SALES_NET_L";
            this.ShowFilterPanel = true;
            this.ShowLeftPanel = false;
            this.Text = "销售网点设置";
            this.Load += new System.EventHandler(this.Frm_SALES_NET_L_Load);
            this.tbFilter.ResumeLayout(false);
            this.pnlFilter.ResumeLayout(false);
            ////((System.ComponentModel.ISupportInitialize)(this.barFormStatus)).EndInit();
            this.barFormStatus.ResumeLayout(false);
            this.pnlHost.ResumeLayout(false);
            this.pnlLeftMain.ResumeLayout(false);
            this.panelEx1.ResumeLayout(false);
            this.navigateItemMain.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private Yss.KTable.Models.Row row1;
        private Yss.KTable.Models.Row row2;
        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
        private Yss.KTable.Models.Column column3;
        private Yss.KTable.Models.Column column4;
        private Yss.KTable.Models.Column column5;
        private Yss.KRichEx.YssTextBox txtTANetCode;
        private Yss.KRichEx.YssTextBox txtTANetName;
        private Yss.KTable.Models.Row row3;
        private Yss.KTable.Models.Row row4;
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Cell cell2;
        private Yss.KTable.Models.Cell cell3;
        private Yss.KTable.Models.Cell cell4;
        private Yss.KTable.Models.Cell cell5;
        private Yss.KTable.Models.Column column6;
        private Yss.KTable.Models.Column column7;
        private Yss.KTable.Models.Column column8;
        private Yss.KTable.Models.Cell cell6;
        private Yss.KTable.Models.Cell cell7;
        private Yss.KTable.Models.Cell cell8;
        private FAST.Core.BaseControl.YssSelCombox cboNetType;
    }
}