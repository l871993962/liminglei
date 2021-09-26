using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;

using FAST.Core.Resource;
using FAST.Core.Context;
namespace YssInformation.Bi.CuryPair.Form
{
    partial class Frm_CURY_PAIR_HBD_L
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
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo2 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo1 = new FAST.Core.BaseControl.ControlMethodInfo();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_CURY_PAIR_HBD_L));
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.cboBasicCury = new FAST.Core.BaseControl.YssSelCombox();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.cboValCury = new FAST.Core.BaseControl.YssSelCombox();
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
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
            this.column5});
            this.tbFilter.Controls.Add(this.cboValCury);
            this.tbFilter.Controls.Add(this.cboBasicCury);
            // 
            // 
            // 
            this.tbFilter.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2});
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
            this.tbMain.Size = new System.Drawing.Size(912, 411);
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
            this.barFormStatus.Location = new System.Drawing.Point(0, 490);
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
            this.pnlHost.Size = new System.Drawing.Size(912, 411);
            // 
            // cboPageSize
            // 
            ////this.cboPageSize.Location = new System.Drawing.Point(616, 2);
            // 
            // txtSearch
            // 
            // 
            // 
            // 
            this.txtSearch.PrefixForeColor = System.Drawing.Color.Gray;
            // 
            // 
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
            this.pnlLeftMain.Visible = false;
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
            // 
            // btnArrow
            // 
            // 
            // 
            // 
            this.btnArrow.BackgroundStyle.BorderTopColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.DockSiteBackColor2;
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
            ////this.txtToPage.Location = new System.Drawing.Point(828, 2);
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
            this.pnlMain.Size = new System.Drawing.Size(912, 516);
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
            this.yssPanel1.Size = new System.Drawing.Size(912, 516);
            // 
            // hpAssist
            // 
            // 
            // row1
            // 
            this.row1.BackColor = System.Drawing.Color.Empty;
            this.row1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row1.FullRowSelected = true;
            this.row1.GroupLineLength = 100;
            this.row1.GroupPosition = -16;
            this.row1.Height = 7;
            this.row1.Key = null;
            this.row1.OwnTable = this.tbFilter;
            this.row1.ShowCheckBox = true;
            // 
            // row2
            // 
            this.row2.BackColor = System.Drawing.Color.Empty;
            this.row2.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell1,
            this.cell2,
            this.cell3,
            this.cell4,
            this.cell5});
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row2.FullRowSelected = true;
            this.row2.GroupLineLength = 100;
            this.row2.GroupPosition = -16;
            this.row2.Height = 25;
            this.row2.Key = null;
            this.row2.OwnTable = this.tbFilter;
            this.row2.ShowCheckBox = true;
            // 
            // cell1
            // 
            this.cell1.BackColor = System.Drawing.Color.Empty;
            this.cell1.CellEditStatus = false;
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.Key = null;
            this.cell1.Text = "基准货币：";
            this.cell1.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.cell1.ToolTip = null;
            // 
            // cell2
            // 
            this.cell2.BackColor = System.Drawing.Color.Empty;
            this.cell2.CellEditStatus = false;
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.cboBasicCury;
            this.cell2.Key = null;
            this.cell2.ToolTip = null;
            // 
            // cboBasicCury
            // 
            this.cboBasicCury.AddedSelItemName = "";
            this.cboBasicCury.BackColor = System.Drawing.Color.White;
            this.cboBasicCury.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.cboBasicCury.ClassName = "";
            this.cboBasicCury.DisplayName = "C_DC_NAME";
            this.cboBasicCury.DisplayValue = "C_DC_CODE";
            this.cboBasicCury.DllName = "YssControls.dll";
            this.cboBasicCury.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDown;
            this.cboBasicCury.IsFocused = false;
            this.cboBasicCury.KTableTree = false;
            this.cboBasicCury.Location = new System.Drawing.Point(87, 8);
            this.cboBasicCury.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo2.MethodName = "getDataList";
            controlMethodInfo2.MethodParamValues = null;
            this.cboBasicCury.MethodInfo = controlMethodInfo2;
            this.cboBasicCury.Name = "cboBasicCury";
            this.cboBasicCury.NodeID = "";
            this.cboBasicCury.Padding = new System.Windows.Forms.Padding(1, 3, 1, 3);
            this.cboBasicCury.Parameter = "C_DC_NAME";
            this.cboBasicCury.ParaNodeID = "";
            this.cboBasicCury.PasswordChar = '\0';
            // 
            // 
            // 
            this.cboBasicCury.QueryCond = "";
            this.cboBasicCury.QueryType = "";
            this.cboBasicCury.RequestEveryTime = true;
            this.cboBasicCury.ShowCheckBox = true;
            this.cboBasicCury.ShowColumnHeader = false;
            this.cboBasicCury.Size = new System.Drawing.Size(120, 21);
            // 
            // 
            // 
            this.cboBasicCury.TabIndex = 3;
            this.cboBasicCury.TextAlign = System.Windows.Forms.HorizontalAlignment.Left;
            this.cboBasicCury.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
            this.cboBasicCury.TipText = "";
            this.cboBasicCury.TriggerTextLength = 1;
            this.cboBasicCury.UseErrorTip = true;
            ////this.cboBasicCury.YssAssociaType = YssBaseCls.Context.AssociaType.currency;
            this.cboBasicCury.YssAssociaType = YssInformation.Support.Context.AssociaType.base_currency;
            this.cboBasicCury.YssCaption = "";
            this.cboBasicCury.YssDataSrc = FAST.Core.Context.ClsEnums.DataSrc.SrcDB;
            this.cboBasicCury.YssIsMust = false;
            this.cboBasicCury.YssKiloDelimiter = true;
            this.cboBasicCury.YssLength = 20;
            this.cboBasicCury.YssNumeric = "";
            this.cboBasicCury.YssReadOnly = false;
            this.cboBasicCury.YssShowButton = true;
            this.cboBasicCury.BeforeDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(this.cboBasicCury_BeforeDropDownClick);
            this.cboBasicCury.YssOnBeforeLoadData += new FAST.Core.BaseControl.YssSelCombox.yssBeforeLoadData(this.cboBasicCury_YssOnBeforeLoadData);
            // 
            // cell3
            // 
            this.cell3.BackColor = System.Drawing.Color.Empty;
            this.cell3.CellEditStatus = false;
            this.cell3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell3.Key = null;
            this.cell3.ToolTip = null;
            // 
            // cell4
            // 
            this.cell4.BackColor = System.Drawing.Color.Empty;
            this.cell4.CellEditStatus = false;
            this.cell4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell4.Key = null;
            this.cell4.Text = "计价货币：";
            this.cell4.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.cell4.ToolTip = null;
            // 
            // cell5
            // 
            this.cell5.BackColor = System.Drawing.Color.Empty;
            this.cell5.CellEditStatus = false;
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.cboValCury;
            this.cell5.Key = null;
            this.cell5.ToolTip = null;
            // 
            // cboValCury
            // 
            this.cboValCury.AddedSelItemName = "";
            this.cboValCury.BackColor = System.Drawing.Color.White;
            this.cboValCury.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.cboValCury.ClassName = "";
            this.cboValCury.DisplayName = "C_DC_NAME";
            this.cboValCury.DisplayValue = "C_DC_CODE";
            this.cboValCury.DllName = "YssControls.dll";
            this.cboValCury.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDown;
            this.cboValCury.IsFocused = false;
            this.cboValCury.KTableTree = false;
            this.cboValCury.Location = new System.Drawing.Point(305, 8);
            this.cboValCury.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataList";
            controlMethodInfo1.MethodParamValues = null;
            this.cboValCury.MethodInfo = controlMethodInfo1;
            this.cboValCury.Name = "cboValCury";
            this.cboValCury.NodeID = "";
            this.cboValCury.Padding = new System.Windows.Forms.Padding(1, 3, 1, 3);
            this.cboValCury.Parameter = "C_DC_NAME";
            this.cboValCury.ParaNodeID = "";
            this.cboValCury.PasswordChar = '\0';
            // 
            // 
            // 
            this.cboValCury.QueryCond = "";
            this.cboValCury.QueryType = "";
            this.cboValCury.RequestEveryTime = true;
            this.cboValCury.ShowCheckBox = true;
            this.cboValCury.ShowColumnHeader = false;
            this.cboValCury.Size = new System.Drawing.Size(120, 21);
            // 
            // 
            // 
            this.cboValCury.TabIndex = 4;
            this.cboValCury.TextAlign = System.Windows.Forms.HorizontalAlignment.Left;
            this.cboValCury.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
            this.cboValCury.TipText = "";
            this.cboValCury.TriggerTextLength = 1;
            this.cboValCury.UseErrorTip = true;
            ////this.cboValCury.YssAssociaType = YssBaseCls.Context.AssociaType.currency;
            this.cboValCury.YssAssociaType = YssInformation.Support.Context.AssociaType.base_currency;
            this.cboValCury.YssCaption = "";
            this.cboValCury.YssDataSrc = FAST.Core.Context.ClsEnums.DataSrc.SrcDB;
            this.cboValCury.YssIsMust = false;
            this.cboValCury.YssKiloDelimiter = true;
            this.cboValCury.YssLength = 20;
            this.cboValCury.YssNumeric = "";
            this.cboValCury.YssReadOnly = false;
            this.cboValCury.YssShowButton = true;
            this.cboValCury.BeforeDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(this.cboValCury_BeforeDropDownClick);
            this.cboValCury.YssOnBeforeLoadData += new FAST.Core.BaseControl.YssSelCombox.yssBeforeLoadData(this.cboValCury_YssOnBeforeLoadData);
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
            // Frm_CURY_PAIR_HBD_L
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(912, 516);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "Frm_CURY_PAIR_HBD_L";
            this.ShowFilterPanel = true;
            this.ShowLeftPanel = false;
            this.Text = "货币对基本信息";
            this.Load += new System.EventHandler(this.Frm_CURY_PAIR_HBD_L_Load);
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

        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
        private Yss.KTable.Models.Column column3;
        private Yss.KTable.Models.Column column4;
        private Yss.KTable.Models.Column column5;
        private Yss.KTable.Models.Row row1;
        private Yss.KTable.Models.Row row2;
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Cell cell2;
        private Yss.KTable.Models.Cell cell3;
        private Yss.KTable.Models.Cell cell4;
        private Yss.KTable.Models.Cell cell5;
        private FAST.Core.BaseControl.YssSelCombox cboBasicCury;
        private FAST.Core.BaseControl.YssSelCombox cboValCury;
    }
}