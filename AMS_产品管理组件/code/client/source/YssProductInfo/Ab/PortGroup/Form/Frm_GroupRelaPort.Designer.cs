using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;

using FAST.Core.Resource;
namespace YssProductInfo.Ab.PortGroup.Form
{
    partial class Frm_GroupRelaPort
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
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.row3 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.cboGroup = new FAST.Core.BaseControl.YssSelCombox();
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.panelEx2 = new Yss.Controls.PanelEx(this.components);
            this.groupPanel2 = new FAST.Core.BaseControl.GroupPanel();
            this.tbSelectable = new Yss.KTable.Models.Table(this.components);
            this.panelEx1 = new Yss.Controls.PanelEx(this.components);
            this.rightToleftSelected = new Yss.Controls.ImageButton();
            this.leftTorightSelected = new Yss.Controls.ImageButton();
            this.leftTorightAll = new Yss.Controls.ImageButton();
            this.rightToleftAll = new Yss.Controls.ImageButton();
            this.groupPanel1 = new FAST.Core.BaseControl.GroupPanel();
            this.tbSelected = new Yss.KTable.Models.Table(this.components);
            this.column6 = new Yss.KTable.Models.Column();
            this.column7 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.tbMain.SuspendLayout();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.panelEx2.SuspendLayout();
            this.groupPanel2.SuspendLayout();
            this.panelEx1.SuspendLayout();
            this.groupPanel1.SuspendLayout();
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
            this.tbMain.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.column1,
            this.column2,
            this.column3,
            this.column4,
            this.column5});
            this.tbMain.Controls.Add(this.cboGroup);
            this.tbMain.Dock = System.Windows.Forms.DockStyle.Top;
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(780, 74);
            // 
            // btnBar
            // 
            this.btnBar.Size = new System.Drawing.Size(780, 30);
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
            this.stBarBottom.Location = new System.Drawing.Point(0, 538);
            this.stBarBottom.Size = new System.Drawing.Size(780, 25);
            // 
            // pnlMain
            // 
            this.pnlMain.Controls.Add(this.panelEx2);
            this.pnlMain.Size = new System.Drawing.Size(780, 563);
            //this.pnlMain.Style.Alignment = System.Drawing.StringAlignment.Center;
            //this.pnlMain.Style.BackColor1.Color = System.Drawing.Color.White;
            //this.pnlMain.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            //this.pnlMain.Style.BorderColor.Color = System.Drawing.Color.FromArgb(((int)(((byte)(121)))), ((int)(((byte)(173)))), ((int)(((byte)(225)))));
            //this.pnlMain.Style.BorderSide = YssDevComponents.DotNetBar.eBorderSide.Top;
            //this.pnlMain.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            //this.pnlMain.Style.GradientAngle = 90;
            this.pnlMain.Controls.SetChildIndex(this.stBarBottom, 0);
            this.pnlMain.Controls.SetChildIndex(this.btnBar, 0);
            this.pnlMain.Controls.SetChildIndex(this.tbMain, 0);
            this.pnlMain.Controls.SetChildIndex(this.panelEx2, 0);
            // 
            // yssPanel1
            // 
            this.yssPanel1.Size = new System.Drawing.Size(780, 563);
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row1.FullRowSelected = false;
            this.row1.GroupLineLength = 400;
            this.row1.GroupPosition = 16;
            this.row1.Height = 30;
            this.row1.IsGroup = true;
            this.row1.ShowCheckBox = true;
            this.row1.Text = "基本信息";
            // 
            // row2
            // 
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row2.FullRowSelected = false;
            this.row2.GroupLineLength = 100;
            this.row2.GroupPosition = 16;
            this.row2.Height = 10;
            this.row2.ShowCheckBox = true;
            this.row2.Text = null;
            // 
            // row3
            // 
            this.row3.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell1,
            this.cell2});
            this.row3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row3.FullRowSelected = false;
            this.row3.GroupLineLength = 100;
            this.row3.GroupPosition = 23;
            this.row3.Height = 22;
            this.row3.ShowCheckBox = true;
            this.row3.Text = null;
            // 
            // cell1
            // 
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.Text = "    群组名称：";
            this.cell1.ToolTip = null;
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.cboGroup;
            this.cell2.ToolTip = null;
            // 
            // cboGroup
            // 
            this.cboGroup.AddedSelItemName = "";
            this.cboGroup.AutoTooltip = false;
            this.cboGroup.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboGroup.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboGroup.Border.Bottom = true;
            this.cboGroup.Border.Left = true;
            this.cboGroup.Border.Right = true;
            this.cboGroup.Border.Top = true;
            this.cboGroup.ClassName = "";
            this.cboGroup.DisplayName = "";
            this.cboGroup.DisplayValue = "";
            this.cboGroup.DllName = "YssControls.dll";
            this.cboGroup.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDown;
            this.cboGroup.FilterCond = "";
            this.cboGroup.IsFillDecimal = true;
            this.cboGroup.IsFocused = false;
            this.cboGroup.KTableTree = false;
            this.cboGroup.Location = new System.Drawing.Point(110, 40);
            this.cboGroup.Margin = new System.Windows.Forms.Padding(0);
            this.cboGroup.MethodInfo = null;
            this.cboGroup.Name = "cboGroup";
            this.cboGroup.NodeID = "";
            this.cboGroup.Parameter = "";
            this.cboGroup.ParaNodeID = "";
            this.cboGroup.PasswordChar = '\0';
            this.cboGroup.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconIndentLine;
            this.cboGroup.QueryCond = "";
            this.cboGroup.QueryType = "";
            this.cboGroup.RequestEveryTime = false;
            this.cboGroup.ShowCheckBox = false;
            this.cboGroup.ShowColumnHeader = false;
            this.cboGroup.ShowItemsCount = true;
            this.cboGroup.Size = new System.Drawing.Size(119, 21);
            this.cboGroup.SortColumn = "";
            this.cboGroup.TabIndex = 1;
            this.cboGroup.Tag = this.cell2;
            this.cboGroup.TextAlign = System.Windows.Forms.HorizontalAlignment.Left;
            this.cboGroup.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Value_Name;
            this.cboGroup.TipText = "";
            this.cboGroup.TriggerTextLength = 1;
            this.cboGroup.UseErrorTip = true;
            this.cboGroup.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboGroup.YssCaption = "群组名称";
            this.cboGroup.YssIsMust = true;
            this.cboGroup.YssLength = 20;
            this.cboGroup.YssNumeric = "";
            this.cboGroup.YssReadOnly = false;
            this.cboGroup.BeforeDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(this.cboGroup_BeforeDropDownClick);
            this.cboGroup.TextChanged += new System.EventHandler(this.cboGroup_TextChanged);
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
            this.column1.Width = 110;
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
            // 
            // panelEx2
            // 
            this.panelEx2.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(243)))), ((int)(((byte)(245)))), ((int)(((byte)(249)))));
            // 
            // 
            // 
            this.panelEx2.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.panelEx2.Border.Bottom = true;
            this.panelEx2.Border.Left = true;
            this.panelEx2.Border.Right = true;
            this.panelEx2.Border.Top = true;
            this.panelEx2.Controls.Add(this.groupPanel2);
            this.panelEx2.Controls.Add(this.panelEx1);
            this.panelEx2.Controls.Add(this.groupPanel1);
            this.panelEx2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelEx2.Location = new System.Drawing.Point(0, 105);
            this.panelEx2.Name = "panelEx2";
            this.panelEx2.Size = new System.Drawing.Size(780, 433);
            this.panelEx2.TabIndex = 4;
            // 
            // groupPanel2
            // 
            // 
            // 
            // 
            this.groupPanel2.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.groupPanel2.Border.Bottom = true;
            this.groupPanel2.Border.DrawByPadding = true;
            this.groupPanel2.Border.Left = true;
            this.groupPanel2.Border.Right = true;
            this.groupPanel2.Border.Top = true;
            this.groupPanel2.CFunCode = null;
            this.groupPanel2.Controls.Add(this.tbSelectable);
            this.groupPanel2.Dock = System.Windows.Forms.DockStyle.Fill;
            this.groupPanel2.GroupLineColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.groupPanel2.GroupLineRectangle = new System.Drawing.Rectangle(5, 21, 250, 2);
            this.groupPanel2.Location = new System.Drawing.Point(414, 0);
            this.groupPanel2.Name = "groupPanel2";
            this.groupPanel2.Padding = new System.Windows.Forms.Padding(0, 25, 0, 0);
            this.groupPanel2.Size = new System.Drawing.Size(366, 433);
            this.groupPanel2.TabIndex = 11;
            this.groupPanel2.Text = "";
            this.groupPanel2.TextPoint = new System.Drawing.Point(5, 5);
            // 
            // tbSelectable
            // 
            this.tbSelectable.AllowColumnDrag = false;
            this.tbSelectable.AllowResizeColumn = true;
            this.tbSelectable.AlternatingColor = System.Drawing.Color.FromArgb(((int)(((byte)(247)))), ((int)(((byte)(247)))), ((int)(((byte)(247)))));
            this.tbSelectable.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.tbSelectable.AutoColumnWidth = true;
            this.tbSelectable.Border.Bottom = false;
            this.tbSelectable.Border.Left = false;
            this.tbSelectable.Border.Right = false;
            this.tbSelectable.Border.Top = false;
            this.tbSelectable.CheckChildWidthParent = false;
            this.tbSelectable.CheckParentWidthChild = true;
            this.tbSelectable.DefaultToolStripItems = Yss.KTable.Enums.SysToolStripItems.GardingMenu;
            this.tbSelectable.GridLineColor = System.Drawing.Color.LightSteelBlue;
            this.tbSelectable.Location = new System.Drawing.Point(3, 28);
            this.tbSelectable.Name = "tbSelectable";
            this.tbSelectable.PlusMinusLineColor = System.Drawing.SystemColors.InactiveBorder;
            this.tbSelectable.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconIndentLine;
            this.tbSelectable.SelectionMode = System.Windows.Forms.SelectionMode.MultiExtended;
            this.tbSelectable.ShowCheckBox = true;
            this.tbSelectable.ShowColumnHeader = true;
            this.tbSelectable.Size = new System.Drawing.Size(365, 402);
            this.tbSelectable.Style = Yss.KTable.Enums.Style.Default;
            this.tbSelectable.TabIndex = 1;
            this.tbSelectable.Text = "table2";
            // 
            // panelEx1
            // 
            // 
            // 
            // 
            this.panelEx1.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.panelEx1.Border.Bottom = true;
            this.panelEx1.Border.Left = false;
            this.panelEx1.Border.Right = false;
            this.panelEx1.Border.Top = false;
            this.panelEx1.Controls.Add(this.rightToleftSelected);
            this.panelEx1.Controls.Add(this.leftTorightSelected);
            this.panelEx1.Controls.Add(this.leftTorightAll);
            this.panelEx1.Controls.Add(this.rightToleftAll);
            this.panelEx1.Dock = System.Windows.Forms.DockStyle.Left;
            this.panelEx1.Location = new System.Drawing.Point(365, 0);
            this.panelEx1.Name = "panelEx1";
            this.panelEx1.Size = new System.Drawing.Size(49, 433);
            this.panelEx1.TabIndex = 10;
            // 
            // rightToleftSelected
            // 
            this.rightToleftSelected.BackgroundImage = null;
            // 
            // 
            // 
            this.rightToleftSelected.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.rightToleftSelected.Border.Bottom = true;
            this.rightToleftSelected.Border.Left = true;
            this.rightToleftSelected.Border.Right = true;
            this.rightToleftSelected.Border.Top = true;
            this.rightToleftSelected.Location = new System.Drawing.Point(15, 74);
            this.rightToleftSelected.Name = "rightToleftSelected";
            this.rightToleftSelected.Size = new System.Drawing.Size(20, 23);
            this.rightToleftSelected.TabIndex = 6;
            this.rightToleftSelected.Text = "<";
            this.rightToleftSelected.ToopTip = "将勾选项移到左边";
            this.rightToleftSelected.UseVisualStyleBackColor = true;
            this.rightToleftSelected.Click += new System.EventHandler(this.rightToleftSelected_Click);
            // 
            // leftTorightSelected
            // 
            this.leftTorightSelected.BackgroundImage = null;
            // 
            // 
            // 
            this.leftTorightSelected.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.leftTorightSelected.Border.Bottom = true;
            this.leftTorightSelected.Border.Left = true;
            this.leftTorightSelected.Border.Right = true;
            this.leftTorightSelected.Border.Top = true;
            this.leftTorightSelected.Location = new System.Drawing.Point(15, 103);
            this.leftTorightSelected.Name = "leftTorightSelected";
            this.leftTorightSelected.Size = new System.Drawing.Size(20, 23);
            this.leftTorightSelected.TabIndex = 7;
            this.leftTorightSelected.Text = ">";
            this.leftTorightSelected.ToopTip = "将勾选项移到右边";
            this.leftTorightSelected.UseVisualStyleBackColor = true;
            this.leftTorightSelected.Click += new System.EventHandler(this.leftTorightSelected_Click);
            // 
            // leftTorightAll
            // 
            this.leftTorightAll.BackgroundImage = null;
            // 
            // 
            // 
            this.leftTorightAll.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.leftTorightAll.Border.Bottom = true;
            this.leftTorightAll.Border.Left = true;
            this.leftTorightAll.Border.Right = true;
            this.leftTorightAll.Border.Top = true;
            this.leftTorightAll.Location = new System.Drawing.Point(15, 195);
            this.leftTorightAll.Name = "leftTorightAll";
            this.leftTorightAll.Size = new System.Drawing.Size(20, 23);
            this.leftTorightAll.TabIndex = 8;
            this.leftTorightAll.Text = ">>";
            this.leftTorightAll.ToopTip = "将所有项移到右边";
            this.leftTorightAll.UseVisualStyleBackColor = true;
            this.leftTorightAll.Click += new System.EventHandler(this.leftTorightAll_Click);
            // 
            // rightToleftAll
            // 
            this.rightToleftAll.BackgroundImage = null;
            // 
            // 
            // 
            this.rightToleftAll.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.rightToleftAll.Border.Bottom = true;
            this.rightToleftAll.Border.Left = true;
            this.rightToleftAll.Border.Right = true;
            this.rightToleftAll.Border.Top = true;
            this.rightToleftAll.Location = new System.Drawing.Point(15, 166);
            this.rightToleftAll.Name = "rightToleftAll";
            this.rightToleftAll.Size = new System.Drawing.Size(20, 23);
            this.rightToleftAll.TabIndex = 8;
            this.rightToleftAll.Text = "<<";
            this.rightToleftAll.ToopTip = "将所有项移到左边";
            this.rightToleftAll.UseVisualStyleBackColor = true;
            this.rightToleftAll.Click += new System.EventHandler(this.rightToleftAll_Click);
            // 
            // groupPanel1
            // 
            // 
            // 
            // 
            this.groupPanel1.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.groupPanel1.Border.Bottom = true;
            this.groupPanel1.Border.DrawByPadding = true;
            this.groupPanel1.Border.Left = true;
            this.groupPanel1.Border.Right = true;
            this.groupPanel1.Border.Top = true;
            this.groupPanel1.CFunCode = null;
            this.groupPanel1.Controls.Add(this.tbSelected);
            this.groupPanel1.Dock = System.Windows.Forms.DockStyle.Left;
            this.groupPanel1.GroupLineColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.groupPanel1.GroupLineRectangle = new System.Drawing.Rectangle(5, 21, 250, 2);
            this.groupPanel1.Location = new System.Drawing.Point(0, 0);
            this.groupPanel1.Name = "groupPanel1";
            this.groupPanel1.Padding = new System.Windows.Forms.Padding(0, 25, 0, 0);
            this.groupPanel1.Size = new System.Drawing.Size(365, 433);
            this.groupPanel1.TabIndex = 9;
            this.groupPanel1.Text = "";
            this.groupPanel1.TextPoint = new System.Drawing.Point(5, 5);
            // 
            // tbSelected
            // 
            this.tbSelected.AutoColumnWidth = true;
            this.tbSelected.AllowColumnDrag = false;
            this.tbSelected.AllowResizeColumn = true;
            this.tbSelected.AlternatingColor = System.Drawing.Color.FromArgb(((int)(((byte)(247)))), ((int)(((byte)(247)))), ((int)(((byte)(247)))));
            this.tbSelected.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.tbSelected.Border.Bottom = false;
            this.tbSelected.Border.Left = false;
            this.tbSelected.Border.Right = false;
            this.tbSelected.Border.Top = false;
            this.tbSelected.CheckChildWidthParent = false;
            this.tbSelected.CheckParentWidthChild = true;
            this.tbSelected.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.column6,
            this.column7});
            this.tbSelected.DefaultToolStripItems = Yss.KTable.Enums.SysToolStripItems.GardingMenu;
            this.tbSelected.GridLineColor = System.Drawing.Color.LightSteelBlue;
            this.tbSelected.Location = new System.Drawing.Point(0, 28);
            this.tbSelected.Name = "tbSelected";
            this.tbSelected.PlusMinusLineColor = System.Drawing.SystemColors.InactiveBorder;
            this.tbSelected.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconIndentLine;
            this.tbSelected.SelectionMode = System.Windows.Forms.SelectionMode.MultiExtended;
            this.tbSelected.ShowCheckBox = true;
            this.tbSelected.ShowColumnHeader = true;
            this.tbSelected.Size = new System.Drawing.Size(365, 405);
            this.tbSelected.Style = Yss.KTable.Enums.Style.Default;
            this.tbSelected.TabIndex = 0;
            this.tbSelected.Text = "table1";
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
            this.column6.Width = 100;
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
            this.column5.Width = 200;
            // 
            // Frm_GroupRelaPort
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(780, 563);
            this.DoubleBuffered = true;
            this.Name = "Frm_GroupRelaPort";
            this.Text = "群组功能管理";
            ////this.YssSetStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            ////this.YssStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.tbMain.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.panelEx2.ResumeLayout(false);
            this.groupPanel2.ResumeLayout(false);
            this.panelEx1.ResumeLayout(false);
            this.groupPanel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private Yss.KTable.Models.Row row1;
        private Yss.KTable.Models.Row row2;
        private Yss.KTable.Models.Row row3;
        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
        private FAST.Core.BaseControl.YssSelCombox cboGroup;
        private Yss.KTable.Models.Cell cell2;
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Column column3;
        private Yss.KTable.Models.Column column4;
        private Yss.Controls.PanelEx panelEx2;
        private Yss.KTable.Models.Column column5;
        private Yss.Controls.ImageButton leftTorightAll;
        private Yss.Controls.ImageButton rightToleftAll;
        private Yss.Controls.ImageButton leftTorightSelected;
        private Yss.Controls.ImageButton rightToleftSelected;
        private FAST.Core.BaseControl.GroupPanel groupPanel1;
        private Yss.Controls.PanelEx panelEx1;
        private FAST.Core.BaseControl.GroupPanel groupPanel2;
        private Yss.KTable.Models.Table tbSelectable;
        private Yss.KTable.Models.Table tbSelected;
        private Yss.KTable.Models.Column column6;
        private Yss.KTable.Models.Column column7;
    }
}