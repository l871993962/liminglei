using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;

using FAST.Core.Resource;

using FAST.Core.Context;
namespace YssProductInfo.Ab.Port.Form
{
    partial class Frm_PORT_L
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_PORT_L));
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo1 = new FAST.Core.BaseControl.ControlMethodInfo();
            ClsPageInation clsPageInation1 = new ClsPageInation();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.txtPortName = new Yss.KRichEx.YssTextBox();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.txtPortCode = new Yss.KRichEx.YssTextBox();
            this.column7 = new Yss.KTable.Models.Column();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.cboAssetType = new FAST.Core.BaseControl.YssSelCombox();
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column8 = new Yss.KTable.Models.Column();
            this.column6 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.tabPageYDQ = new Yss.Controls.TabPage();
            this.tabPageYQS = new Yss.Controls.TabPage();
            this.tabPageCPK = new Yss.Controls.TabPage();
            this.row3 = new Yss.KTable.Models.Row();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.cell13 = new Yss.KTable.Models.Cell();
            this.txtAssetCode = new Yss.KRichEx.YssTextBox();
            this.cboPortLever = new FAST.Core.BaseControl.YssSelCombox();
			this.tabPageMJQ = new Yss.Controls.TabPage();
            this.tabPageDFX = new Yss.Controls.TabPage();
            this.tabPageCG = new Yss.Controls.TabPage();
            this.tabCtrlDataMain.SuspendLayout();
            this.tabPageDefault.SuspendLayout();
            this.pnlDetails.SuspendLayout();
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
            this.SuspendLayout();
            // 
            // tabCtrlDataMain
            // 
			////this.tabCtrlDataMain.Controls.Add(this.tabPageCG);
            ////this.tabCtrlDataMain.Controls.Add(this.tabPageDFX);
            ////this.tabCtrlDataMain.Controls.Add(this.tabPageMJQ);
            ////this.tabCtrlDataMain.Controls.Add(this.tabPageCPK);
            ////this.tabCtrlDataMain.Controls.Add(this.tabPageYDQ);
            ////this.tabCtrlDataMain.Controls.Add(this.tabPageYQS);
            this.tabCtrlDataMain.Size = new System.Drawing.Size(910, 129);
            this.tabCtrlDataMain.TabPages.AddRange(new Yss.Controls.TabPage[] {
            ////this.tabPageCG,
            ////this.tabPageDFX,
            ////this.tabPageMJQ,
            ////this.tabPageYDQ,
            ////this.tabPageYQS,
            ////this.tabPageYGZ,
            this.tabPageCPK});
            this.tabCtrlDataMain.Controls.SetChildIndex(this.tabPageCPK, 0);
            ////this.tabCtrlDataMain.Controls.SetChildIndex(this.tabPageYQS, 0);
            ////this.tabCtrlDataMain.Controls.SetChildIndex(this.tabPageYGZ, 0);
            ////this.tabCtrlDataMain.Controls.SetChildIndex(this.tabPageYDQ, 0);
            ////this.tabCtrlDataMain.Controls.SetChildIndex(this.tabPageDefault, 0);
            ////this.tabCtrlDataMain.Controls.SetChildIndex(this.tabPageMJQ, 0);
            ////this.tabCtrlDataMain.Controls.SetChildIndex(this.tabPageDFX, 0);
            ////this.tabCtrlDataMain.Controls.SetChildIndex(this.tabPageCG, 0);
            // 
            // tabPageDefault
            // 
            this.tabPageDefault.Text = "存续期";
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
            this.pnlDetails.Location = new System.Drawing.Point(0, 251);
            this.pnlDetails.Size = new System.Drawing.Size(912, 265);
            // 
            // tabCtrlDataDetail
            // 
            this.tabCtrlDataDetail.Size = new System.Drawing.Size(910, 263);
            // 
            // expandSplitterDetails
            // 
            this.expandSplitterDetails.Location = new System.Drawing.Point(0, 245);
            this.expandSplitterDetails.Size = new System.Drawing.Size(912, 6);
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
            this.column8,
            this.column6,
            this.column7,
            this.column3,
            this.column4,
            this.column5});
            this.tbFilter.Controls.Add(this.cboPortLever);
            this.tbFilter.Controls.Add(this.txtAssetCode);
            this.tbFilter.Controls.Add(this.cboAssetType);
            this.tbFilter.Controls.Add(this.txtPortName);
            this.tbFilter.Controls.Add(this.txtPortCode);
            this.tbFilter.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3});
            this.tbFilter.Size = new System.Drawing.Size(912, 59);
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
			this.tbMain.DefaultToolStripItems = ((Yss.KTable.Enums.SysToolStripItems)(((((Yss.KTable.Enums.SysToolStripItems.ShowHideColumn | Yss.KTable.Enums.SysToolStripItems.GroupByColumn)
                        | Yss.KTable.Enums.SysToolStripItems.PrintSetup)
                        | Yss.KTable.Enums.SysToolStripItems.PrintPreview)
                        | Yss.KTable.Enums.SysToolStripItems.GardingMenu)));
            this.tbMain.Size = new System.Drawing.Size(908, 102);
            // 
            // pnlFilter
            // 
            this.pnlFilter.Size = new System.Drawing.Size(912, 59);
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
            this.barFormStatus.Location = new System.Drawing.Point(1, 130);
            this.barFormStatus.Size = new System.Drawing.Size(910, 28);
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
            this.pnlHost.Location = new System.Drawing.Point(0, 92);
            this.pnlHost.Size = new System.Drawing.Size(912, 159);
            // 
            // cboPageSize
            // 
            ////this.cboPageSize.Location = new System.Drawing.Point(605, 2);
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
            this.tbLeftMain.ShowCheckBox = true;
            this.tbLeftMain.Size = new System.Drawing.Size(243, 524);
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
            ////this.txtToPage.Location = new System.Drawing.Point(826, 2);
            // 
            // barPort
            // 
            // 
            // btnBar
            // 
            this.btnBar.Size = new System.Drawing.Size(887, 33);
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
            // 
            // 
            // 
            this.yssPanel1.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.yssPanel1.Border.Bottom = true;
            this.yssPanel1.Border.Left = true;
            this.yssPanel1.Border.Right = true;
            this.yssPanel1.Border.Top = true;
            this.yssPanel1.Size = new System.Drawing.Size(912, 516);
            // 
            // hpAssist
            // 
            // 
            // cell5
            // 
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.cboAssetType;
            // 
            // txtPortName
            // 
            this.txtPortName.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.txtPortName.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtPortName.Location = new System.Drawing.Point(304, 7);
            this.txtPortName.Name = "txtPortName";
            this.txtPortName.Size = new System.Drawing.Size(121, 21);
            this.txtPortName.TabIndex = 8;
            this.txtPortName.Tag = this.cell7;
            // 
            // cell3
            // 
            this.cell3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell3.ForeColor = System.Drawing.Color.Black;
            this.cell3.InnerControl = this.txtPortCode;
            // 
            // txtPortCode
            // 
            this.txtPortCode.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.txtPortCode.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtPortCode.Location = new System.Drawing.Point(86, 7);
            this.txtPortCode.Name = "txtPortCode";
            this.txtPortCode.Size = new System.Drawing.Size(121, 21);
            this.txtPortCode.TabIndex = 0;
            this.txtPortCode.Tag = this.cell3;
            // 
            // column7
            // 
            this.column7.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column7.Width = 122;
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row1.ForeColor = System.Drawing.Color.Black;
            this.row1.FullRowSelected = false;
            this.row1.Height = 7;
            this.row1.Text = null;
            // 
            // row2
            // 
            this.row2.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell2,
            this.cell3,
            this.cell1,
            this.cell4,
            this.cell5,
            this.cell8,
            this.cell6,
            this.cell7});
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row2.ForeColor = System.Drawing.Color.Black;
            this.row2.FullRowSelected = false;
            this.row2.GroupPosition = -16;
            this.row2.Height = 25;
            this.row2.Text = null;
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.Color.Black;
            this.cell2.Text = "组合代码：";
            this.cell2.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // cell1
            // 
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.Color.Black;
            // 
            // cell4
            // 
            this.cell4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell4.Text = "资产类型：";
            // 
            // cell8
            // 
            this.cell8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.cell8.ForeColor = System.Drawing.Color.Black;
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F);
            this.cell6.ForeColor = System.Drawing.Color.Black;
            this.cell6.Text = "组合名称：";
            this.cell6.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.cell7.ForeColor = System.Drawing.Color.Black;
            this.cell7.InnerControl = this.txtPortName; 
            this.cell7.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // cboAssetType
            // 
            this.cboAssetType.AddedSelItemName = "";
            this.cboAssetType.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.cboAssetType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboAssetType.Border.Bottom = true;
            this.cboAssetType.Border.Left = true;
            this.cboAssetType.Border.Right = true;
            this.cboAssetType.Border.Top = true;
            this.cboAssetType.DisplayName = "C_DAT_NAME";
            this.cboAssetType.DisplayValue = "C_DAT_CODE";
            this.cboAssetType.FilterCond = "";
            this.cboAssetType.IsFillDecimal = false;
            this.cboAssetType.Location = new System.Drawing.Point(522, 7);
            this.cboAssetType.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo2.MethodName = "getDataListByTypes";
            controlMethodInfo2.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo2.MethodParams")));
            controlMethodInfo2.MethodParamValues = new string[] {
        "ASS,"};
            controlMethodInfo2.Methods = null;
            this.cboAssetType.MethodInfo = controlMethodInfo2;
            this.cboAssetType.Name = "cboAssetType";
            this.cboAssetType.NodeID = "C_DAT_CODE";
            this.cboAssetType.Parameter = "C_DAT_NAME";
            this.cboAssetType.ParaNodeID = "C_DAT_CODE_P";
            this.cboAssetType.QueryCond = "";
            this.cboAssetType.QueryType = "";
            this.cboAssetType.Size = new System.Drawing.Size(121, 21);
            this.cboAssetType.TabIndex = 7;
            this.cboAssetType.Tag = this.cell5;
            this.cboAssetType.YssAssociaType = YssInformation.Support.Context.AssociaType.base_accType;
            this.cboAssetType.YssCaption = "资产类型";
            this.cboAssetType.YssKiloDelimiter = true;
            // 
            // column1
            // 
            this.column1.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.Width = 86;
            // 
            // column2
            // 
            this.column2.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column2.Width = 122;
            // 
            // column8
            // 
            this.column8.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column8.Width = 30;
            // 
            // column6
            // 
            this.column6.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column6.Width = 66;
            // 
            // column3
            // 
            this.column3.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column3.Width = 30;
            // 
            // column4
            // 
            this.column4.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column4.Width = 66;
            // 
            // column5
            // 
            this.column5.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column5.Width = 222;
            // 
            // tabPageYDQ
            // 
            this.tabPageYDQ.Name = "tabPageYDQ";
            this.tabPageYDQ.TabCloseButtonVisible = false;
            this.tabPageYDQ.Text = "已到期";
            // 
            // tabPageYQS
            // 
            this.tabPageYQS.Name = "tabPageYQS";
            this.tabPageYQS.TabCloseButtonVisible = false;
            this.tabPageYQS.Text = "已清算";
            // 
            // tabPageCPK
            // 
            this.tabPageCPK.Name = "tabPageCPK";
            this.tabPageCPK.TabCloseButtonVisible = false;
            this.tabPageCPK.Text = "产品库";
            // 
            // row3
            // 
            this.row3.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell9,
            this.cell10,
            this.cell11,
            this.cell12,
            this.cell13});
            this.row3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row3.FullRowSelected = false;
            this.row3.Text = null;
            // 
            // cell9
            // 
            this.cell9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell9.Text = "资产代码：";
            this.cell9.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            // 
            // cell10
            // 
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.InnerControl = this.txtAssetCode;
            // 
            // cell11
            // 
            this.cell11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell12
            // 
            this.cell12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell12.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell12.Text = "组合级别：";
            // 
            // cell13
            // 
            this.cell13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell13.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell13.InnerControl = this.cboPortLever;
            // 
            // txtAssetCode
            // 
            this.txtAssetCode.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.txtAssetCode.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtAssetCode.Location = new System.Drawing.Point(86, 32);
            this.txtAssetCode.Name = "txtAssetCode";
            this.txtAssetCode.Size = new System.Drawing.Size(121, 21);
            this.txtAssetCode.TabIndex = 9;
            this.txtAssetCode.Tag = this.cell10;
            // 
            // cboPortLever
            // 
            this.cboPortLever.AddedSelItemName = "";
            this.cboPortLever.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboPortLever.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboPortLever.Border.Bottom = true;
            this.cboPortLever.Border.Left = true;
            this.cboPortLever.Border.Right = true;
            this.cboPortLever.Border.Top = true;
            this.cboPortLever.FilterCond = "";
            this.cboPortLever.IsFillDecimal = false;
            this.cboPortLever.Location = new System.Drawing.Point(304, 32);
            this.cboPortLever.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataListByTypes";
            controlMethodInfo1.MethodParams = null;
            controlMethodInfo1.MethodParamValues = new string[] {
        "PORT_LEVEL,"};
            controlMethodInfo1.Methods = null;
            this.cboPortLever.MethodInfo = controlMethodInfo1;
            this.cboPortLever.Name = "cboPortLever";
            this.cboPortLever.QueryCond = "PORT_LEVEL";
            this.cboPortLever.QueryType = "CacheType";
            this.cboPortLever.Size = new System.Drawing.Size(121, 21);
            this.cboPortLever.TabIndex = 10;
            this.cboPortLever.Tag = this.cell13;
            this.cboPortLever.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboPortLever.YssCaption = "组合级别";
            // 
            // tabPageMJQ
            // 
            this.tabPageMJQ.Name = "tabPageMJQ";
            this.tabPageMJQ.TabCloseButtonVisible = false;
            this.tabPageMJQ.Text = "募集期";
            // 
            // tabPageDFX
            // 
            this.tabPageDFX.Name = "tabPageDFX";
            this.tabPageDFX.TabCloseButtonVisible = false;
            this.tabPageDFX.Text = "待发行";
            // 
            // tabPageCG
            // 
            this.tabPageCG.Name = "tabPageCG";
            this.tabPageCG.TabCloseButtonVisible = false;
            this.tabPageCG.Text = "草稿";
            // 
            // Frm_PORT_L
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(912, 516);
            this.Name = "Frm_PORT_L";
            clsPageInation1.bCreateQuery = true;
            clsPageInation1.CurrPage = 1;
            clsPageInation1.IsUsePage = true;
            clsPageInation1.PageCount = -1;
            clsPageInation1.PageSize = 100;
            clsPageInation1.sQueryStr = "";
            clsPageInation1.sUrl = "";
            clsPageInation1.TotalNum = 0;
            this.Page = clsPageInation1;
            this.ShowFilterPanel = true;
            this.ShowLeftPanel = false;
            this.Text = "组合基本参数";
            this.Load += new System.EventHandler(this.Frm_PORT_L_Load);
            this.tabCtrlDataMain.ResumeLayout(false);
            this.tabPageDefault.ResumeLayout(false);
            this.pnlDetails.ResumeLayout(false);
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
        private Yss.KTable.Models.Cell cell4;
        private Yss.KTable.Models.Cell cell5;
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Cell cell2;
        private Yss.KTable.Models.Cell cell3;
        private Yss.KTable.Models.Column column8;
        private Yss.KTable.Models.Column column6;
        private Yss.KTable.Models.Column column7;
        private Yss.KRichEx.YssTextBox txtPortCode;
        private Yss.KRichEx.YssTextBox txtPortName;
        private Yss.KTable.Models.Cell cell6;
        private Yss.KTable.Models.Cell cell7;
        private Yss.KTable.Models.Cell cell8;
        private FAST.Core.BaseControl.YssSelCombox cboAssetType;
        private Yss.Controls.TabPage tabPageYDQ;
        private Yss.Controls.TabPage tabPageYQS;
        private Yss.Controls.TabPage tabPageCPK;
        private Yss.KRichEx.YssTextBox txtAssetCode;
        private Yss.KTable.Models.Row row3;
        private Yss.KTable.Models.Cell cell9;
        private Yss.KTable.Models.Cell cell10;
        private Yss.KTable.Models.Cell cell11;
        private Yss.KTable.Models.Cell cell12;
        private Yss.KTable.Models.Cell cell13;
        private FAST.Core.BaseControl.YssSelCombox cboPortLever;
        private Yss.Controls.TabPage tabPageMJQ;
        private Yss.Controls.TabPage tabPageDFX;
        private Yss.Controls.TabPage tabPageCG;
    }
}