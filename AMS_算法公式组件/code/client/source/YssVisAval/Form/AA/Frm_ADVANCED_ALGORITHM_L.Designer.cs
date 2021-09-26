
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;

using FAST.Core.Resource;

namespace YssVisAval.Form.AA
{
    partial class Frm_ADVANCED_ALGORITHM_L
    {

            // / <summary>
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_ADVANCED_ALGORITHM_L));
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.cboAlgoType = new FAST.Core.BaseControl.YssSelCombox();
            this.tbFilter.SuspendLayout();
            this.pnlFilter.SuspendLayout();

        ////((System.ComponentModel.ISupportInitialize)(this.barFormStatus)).BeginInit();
            this.barFormStatus.SuspendLayout();
            this.pnlHost.SuspendLayout();
            this.navBarLeft.SuspendLayout();
            this.navigateItemMain.SuspendLayout();
            this.panelEx1.SuspendLayout();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.SuspendLayout();

            //  
            /// tbFilter
            /// 
            /// 
            /// 
            /// 
            this.tbFilter.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.tbFilter.Border.Bottom = false;
            this.tbFilter.Border.Left = false;
            this.tbFilter.Border.Right = false;
            this.tbFilter.Border.Top = true;
            this.tbFilter.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.column1,
            this.column2});
            this.tbFilter.Controls.Add(this.cboAlgoType);

            //  
            /// 
            /// 
            this.tbFilter.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2});
            this.tbFilter.Size = new System.Drawing.Size(900, 40);

            //  
            /// tbMain
            /// 
            this.tbMain.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(255)))));

            //  
            /// 
            /// 
            this.tbMain.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.tbMain.Border.Bottom = true;
            this.tbMain.Border.Left = false;
            this.tbMain.Border.Right = false;
            this.tbMain.Border.Top = true;

            //  
            /// 
            /// 
            this.tbMain.MultiLine = true;
            this.tbMain.Size = new System.Drawing.Size(900, 509);

            //  
            /// pnlFilter
            /// 
            this.pnlFilter.Size = new System.Drawing.Size(900, 40);
            this.pnlFilter.Style.Alignment = System.Drawing.StringAlignment.Center;
            this.pnlFilter.Style.BackColor1.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBackground;
            this.pnlFilter.Style.BackColor2.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBackground2;
            this.pnlFilter.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            this.pnlFilter.Style.BorderColor.Color = System.Drawing.Color.FromArgb(((int)(((byte)(102)))), ((int)(((byte)(136)))), ((int)(((byte)(184)))));
            this.pnlFilter.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            this.pnlFilter.Style.GradientAngle = 90;

            //  
            /// barFormStatus
            /// 
            this.barFormStatus.Size = new System.Drawing.Size(900, 26);

            //  
            /// proBar
            /// 
            /// 
            /// 
            /// 
            //////// this.proBar.BackStyle.BorderBottom = YssDevComponents.DotNetBar.eStyleBorderType.DashDot;

        //////// this.proBar.BackStyle.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(0)))), ((int)(((byte)(0)))));

        //////// this.proBar.BackStyle.BorderLeft = YssDevComponents.DotNetBar.eStyleBorderType.DashDot;

        //////// this.proBar.BackStyle.BorderRight = YssDevComponents.DotNetBar.eStyleBorderType.DashDot;

        //////// this.proBar.BackStyle.BorderTop = YssDevComponents.DotNetBar.eStyleBorderType.DashDot;

            //  
            /// pnlHost
            /// 
            this.pnlHost.Size = new System.Drawing.Size(900, 509);

            //  
            /// cboPageSize
            /// 
            //// this.cboPageSize.Location = new System.Drawing.Point(604, 2);

            //  
            /// txtSearch
            /// 
            /// 
            /// 
            /// 
            this.txtSearch.PrefixForeColor = System.Drawing.Color.Gray;

            //  
            /// 
            /// 
            /// 
            /// pnlLeftMain
            /// 
            /// 
            /// 
            /// 
            /// 
            /// navigateExpandablePanel1
            /// 
            /// 
            /// 
            /// 
            /// 
            /// 
            /// 
            this.navigateItemMain.Controls.SetChildIndex(this.tbLeftMain, 0);

            //  
            /// tbLeftMain
            /// 
            this.tbLeftMain.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(255)))));

            //  
            /// 
            /// 
            this.tbLeftMain.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.tbLeftMain.Border.Bottom = false;
            this.tbLeftMain.Border.Left = false;
            this.tbLeftMain.Border.Right = false;
            this.tbLeftMain.Border.Top = false;

            //  
            /// 
            /// 
            /// 
            /// btnArrow
            /// 
            /// 
            /// 
            /// 
            this.btnArrow.BackgroundStyle.BorderTopColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.DockSiteBackColor2;
            this.btnArrow.Location = new System.Drawing.Point(875, 0);

            //  
            /// panelEx1
            /// 
            this.panelEx1.Size = new System.Drawing.Size(900, 33);
            this.panelEx1.Style.Alignment = System.Drawing.StringAlignment.Center;
            this.panelEx1.Style.BackColor1.Color = System.Drawing.Color.FromArgb(((int)(((byte)(228)))), ((int)(((byte)(234)))), ((int)(((byte)(241)))));
            this.panelEx1.Style.BackColor2.Color = System.Drawing.Color.FromArgb(((int)(((byte)(228)))), ((int)(((byte)(234)))), ((int)(((byte)(241)))));
            this.panelEx1.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            this.panelEx1.Style.BorderColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBorder;
            this.panelEx1.Style.BorderSide = YssDevComponents.DotNetBar.eBorderSide.None;
            this.panelEx1.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            this.panelEx1.Style.GradientAngle = 90;

            //  
            /// txtToPage
            /// 
            /// 
            /// 
            /// 
            //// this.txtToPage.Border.Class = "TextBoxBorder";

        //// this.txtToPage.Location = new System.Drawing.Point(816, 2);

            //  
            /// barPort
            /// 
            /// 
            /// btnBar
            /// 
            this.btnBar.Size = new System.Drawing.Size(875, 33);

            //  
            /// pnlMain
            /// 
            this.pnlMain.Location = new System.Drawing.Point(0, 0);
            this.pnlMain.Size = new System.Drawing.Size(900, 614);

            // this.pnlMain.Style.Alignment = System.Drawing.StringAlignment.Center;

            // this.pnlMain.Style.BackColor1.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.DockSiteBackColor;

            // this.pnlMain.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;

            // this.pnlMain.Style.BorderColor.Color = System.Drawing.Color.FromArgb(((int)(((byte)(132)))), ((int)(((byte)(152)))), ((int)(((byte)(178)))));

            // this.pnlMain.Style.BorderSide = YssDevComponents.DotNetBar.eBorderSide.Top;

            // this.pnlMain.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;

            // this.pnlMain.Style.GradientAngle = 90;

            //  
            /// hpAssist
            /// 
            this.hpAssist.HelpNamespace = "C:\\Program Files\\Microsoft Visual Studio 9.0\\Common7\\IDE\\V4.5金融资产管理平台.CHM";

            //  
            /// column1
            /// 
            this.column1.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column1.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column1.DataPropertyName = null;
            this.column1.ForeColor = System.Drawing.Color.Empty;
            this.column1.Tag = null;
            this.column1.Text = "";
            this.column1.Width = 80;

            //  
            /// column2
            /// 
            this.column2.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column2.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column2.DataPropertyName = null;
            this.column2.ForeColor = System.Drawing.Color.Empty;
            this.column2.Tag = null;
            this.column2.Text = "";
            this.column2.Width = 120;

            //  
            /// row1
            /// 
            this.row1.BackColor = System.Drawing.Color.Empty;
            this.row1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row1.FullRowSelected = true;
            this.row1.GroupLineLength = 100;
            this.row1.GroupPosition = 16;
            this.row1.Height = 7;
            this.row1.Key = null;
            this.row1.OwnTable = this.tbFilter;
            this.row1.ShowCheckBox = true;

            //  
            /// row2
            /// 
            this.row2.BackColor = System.Drawing.Color.Empty;
            this.row2.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell1,
            this.cell2});
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row2.FullRowSelected = true;
            this.row2.GroupLineLength = 100;
            this.row2.GroupPosition = 16;
            this.row2.Height = 25;
            this.row2.Key = null;
            this.row2.OwnTable = this.tbFilter;
            this.row2.ShowCheckBox = true;

            //  
            /// cell1
            /// 
            this.cell1.BackColor = System.Drawing.Color.Empty;
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.Key = null;
            this.cell1.Text = "算法类型：";
            this.cell1.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.cell1.ToolTip = null;

            //  
            /// cell2
            /// 
            this.cell2.BackColor = System.Drawing.Color.Empty;
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.cboAlgoType;
            this.cell2.Key = null;
            this.cell2.ToolTip = null;

            //  
            /// cboAlgoType
            /// 
            this.cboAlgoType.AddedSelItemName = "";
            this.cboAlgoType.BackColor = System.Drawing.Color.White;
            this.cboAlgoType.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.cboAlgoType.DisplayName = "";
            this.cboAlgoType.DisplayValue = "";
            this.cboAlgoType.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDown;
            this.cboAlgoType.IsFocused = false;
            this.cboAlgoType.KTableTree = false;
            this.cboAlgoType.Location = new System.Drawing.Point(81, 8);
            this.cboAlgoType.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataListByTypes";
            controlMethodInfo1.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo1.MethodParams")));
            controlMethodInfo1.MethodParamValues = new string[] {
        "SF_TYPE,"};
            controlMethodInfo1.Methods = new string[] {
        "getDataList",
        "getDataListRes",
        "getDataListByTypes",
        "getQueryResByTypes",
        "getDataListByKeys",
        "getQueryResByKeys",
        "getDataByCode",
        "getKeyConvertValue",
        "ToString",
        "Equals",
        "GetHashCode",
        "GetType"};
            this.cboAlgoType.MethodInfo = controlMethodInfo1;
            this.cboAlgoType.Name = "cboAlgoType";
            this.cboAlgoType.NodeID = "";
            this.cboAlgoType.Padding = new System.Windows.Forms.Padding(1, 2, 1, 3);
            this.cboAlgoType.Parameter = "";
            this.cboAlgoType.ParaNodeID = "";
            this.cboAlgoType.PasswordChar = '\0';

            //  
            /// 
            /// 
            this.cboAlgoType.QueryCond = "SF_TYPE";
            this.cboAlgoType.QueryType = "CacheType";
            this.cboAlgoType.RequestEveryTime = false;
            this.cboAlgoType.ShowCheckBox = false;
            this.cboAlgoType.ShowColumnHeader = false;
            this.cboAlgoType.Size = new System.Drawing.Size(118, 21);

            //  
            /// 
            /// 
            this.cboAlgoType.TabIndex = 0;
            this.cboAlgoType.TextAlign = System.Windows.Forms.HorizontalAlignment.Left;
            this.cboAlgoType.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
            this.cboAlgoType.TipText = "";
            this.cboAlgoType.TriggerTextLength = 1;
            this.cboAlgoType.UseErrorTip = true;
            this.cboAlgoType.YssAssociaType = YssVisAval.Context.AssociaType.pubvocabulary;
            this.cboAlgoType.YssCaption = "";
            this.cboAlgoType.YssIsMust = false;
            this.cboAlgoType.YssLength = 20;
            this.cboAlgoType.YssNumeric = "";
            this.cboAlgoType.YssReadOnly = false;
            this.cboAlgoType.YssShowButton = true;

            //  
            /// Frm_ADVANCED_ALGORITHM_L
            /// 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(900, 614);
            this.hpAssist.SetHelpKeyword(this, "FrmBaseList");
            this.hpAssist.SetHelpNavigator(this, System.Windows.Forms.HelpNavigator.KeywordIndex);
            this.Name = "Frm_ADVANCED_ALGORITHM_L";
            this.ShowFilterPanel = true;
            this.hpAssist.SetShowHelp(this, true);
            this.ShowLeftPanel = false;
            this.Text = "高级算法公式";
            this.tbFilter.ResumeLayout(false);
            this.pnlFilter.ResumeLayout(false);

        ////((System.ComponentModel.ISupportInitialize)(this.barFormStatus)).EndInit();
            this.barFormStatus.ResumeLayout(false);
            this.pnlHost.ResumeLayout(false);
            this.navBarLeft.ResumeLayout(false);
            this.navigateItemMain.ResumeLayout(false);
            this.panelEx1.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
        private FAST.Core.BaseControl.YssSelCombox cboAlgoType;
        private Yss.KTable.Models.Row row1;
        private Yss.KTable.Models.Row row2;
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Cell cell2;
    }
}