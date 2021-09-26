////using YssBaseCls.Fun;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;

using FAST.Core.Resource;
namespace YssSecInformation.Mp.SecEq.Form
{
    partial class Frm_SEC_EQU_PX_L
    {
        /// <summary>
        /// 清理所有正在使用的资源。
        /// </summary>
        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                if (components != null)
                {
                    components.Dispose();
                }
            }
            base.Dispose(disposing);
        }

        #region Windows 窗体设计器生成的代码
        /// <summary>
        /// 设计器支持所需的方法 - 不要使用代码编辑器修改
        /// 此方法的内容。
        /// </summary>
        private void InitializeComponent()
        {
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo1 = new FAST.Core.BaseControl.ControlMethodInfo();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_SEC_EQU_PX_L));
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo2 = new FAST.Core.BaseControl.ControlMethodInfo();
            this.dtpDividendDate = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.column8 = new Yss.KTable.Models.Column();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.btnSecurityCode = new FAST.Core.BaseControl.YssSelCombox();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.cboInterestCategory = new FAST.Core.BaseControl.YssSelCombox();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.column6 = new Yss.KTable.Models.Column();
            this.column7 = new Yss.KTable.Models.Column();
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
            this.tbFilter.Controls.Add(this.btnSecurityCode);
            this.tbFilter.Controls.Add(this.dtpDividendDate);
            this.tbFilter.Controls.Add(this.cboInterestCategory);
            this.tbFilter.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2});
            this.tbFilter.Size = new System.Drawing.Size(634, 40);
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
            this.tbMain.Size = new System.Drawing.Size(634, 483);
            // 
            // pnlFilter
            // 
            this.pnlFilter.Size = new System.Drawing.Size(634, 40);
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
            this.barFormStatus.Location = new System.Drawing.Point(0, 562);
            this.barFormStatus.Size = new System.Drawing.Size(634, 28);
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
            this.pnlHost.Size = new System.Drawing.Size(634, 483);
            // 
            // cboPageSize
            // 
            ////this.cboPageSize.Location = new System.Drawing.Point(442, 2);
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
            this.navBarLeft.Size = new System.Drawing.Size(245, 524);
            // 
            // tbLeftMain
            // 
            this.tbLeftMain.AllowColumnDrag = false;
            this.tbLeftMain.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(255)))));
            // 
            // 
            // 
            this.tbLeftMain.Border.Bottom = false;
            this.tbLeftMain.Border.Left = false;
            this.tbLeftMain.Border.Right = false;
            this.tbLeftMain.Border.Top = false;
            this.tbLeftMain.DefaultToolStripItems = ((Yss.KTable.Enums.SysToolStripItems)((Yss.KTable.Enums.SysToolStripItems.GardingMenu | Yss.KTable.Enums.SysToolStripItems.ShowCancelChildRowsChecked)));
            this.tbLeftMain.ShowCheckBox = true;
            this.tbLeftMain.Size = new System.Drawing.Size(243, 495);
            // 
            // splitLeft
            // 
            this.splitLeft.Size = new System.Drawing.Size(6, 590);
            // 
            // btnArrow
            // 
            // 
            // 
            // 
            this.btnArrow.BackgroundStyle.BorderTop = YssDevComponents.DotNetBar.eStyleBorderType.Solid;
            this.btnArrow.BackgroundStyle.BorderTopColor = System.Drawing.Color.FromArgb(((int)(((byte)(102)))), ((int)(((byte)(136)))), ((int)(((byte)(184)))));
            this.btnArrow.BackgroundStyle.BorderTopWidth = 1;
            this.btnArrow.Location = new System.Drawing.Point(609, 0);
            // 
            // panelEx1
            // 
            this.panelEx1.Size = new System.Drawing.Size(634, 33);
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
            ////this.txtToPage.Location = new System.Drawing.Point(654, 2);
            // 
            // barPort
            // 
            // 
            // btnBar
            // 
            this.btnBar.Size = new System.Drawing.Size(609, 33);
            // 
            // navigateItemMain
            // 
            this.navigateItemMain.Text = "交易市场";
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
            this.pnlLeftMain.Size = new System.Drawing.Size(247, 590);
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
            this.pnlBarPort.Location = new System.Drawing.Point(1, 562);
            // 
            // pnlMain
            // 
            this.pnlMain.Size = new System.Drawing.Size(634, 590);
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
            this.yssPanel1.Size = new System.Drawing.Size(912, 590);
            // 
            // hpAssist
            // 
            // 
            // dtpDividendDate
            // 
            this.dtpDividendDate.BackColor = System.Drawing.Color.Transparent;
            this.dtpDividendDate.Location = new System.Drawing.Point(448, 7);
            this.dtpDividendDate.Margin = new System.Windows.Forms.Padding(0);
            this.dtpDividendDate.Name = "dtpDividendDate";
            this.dtpDividendDate.ReadOnly = false;
            this.dtpDividendDate.Size = new System.Drawing.Size(229, 21);
            this.dtpDividendDate.TabIndex = 3;
            this.dtpDividendDate.Tag = this.cell8;
            this.dtpDividendDate.yssEnabled = true;
            this.dtpDividendDate.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpDividendDate.yssInterval = true;
            this.dtpDividendDate.yssLabelStr = "至";
            this.dtpDividendDate.yssShowCheckBox = false;
            this.dtpDividendDate.yssShowOperLable = false;
            this.dtpDividendDate.YssShowSecond = true;
            this.dtpDividendDate.yssTimeControl = false;
            this.dtpDividendDate.BusinessDate = true;
            // 
            // cell8
            // 
            this.cell8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell8.ForeColor = System.Drawing.Color.Black;
            this.cell8.InnerControl = this.dtpDividendDate;
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
            this.column8.Width = 230;
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row1.ForeColor = System.Drawing.Color.Black;
            this.row1.FullRowSelected = false;
            this.row1.GroupPosition = -16;
            this.row1.Height = 7;
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
            this.cell8});
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row2.ForeColor = System.Drawing.Color.Black;
            this.row2.FullRowSelected = false;
            this.row2.GroupPosition = -16;
            this.row2.Height = 25;
            this.row2.Text = null;
            // 
            // cell1
            // 
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.Text = "交易证券：";
            this.cell1.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.btnSecurityCode;
            // 
            // btnSecurityCode
            // 
            this.btnSecurityCode.AddedSelItemName = "";
            this.btnSecurityCode.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.btnSecurityCode.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.btnSecurityCode.Border.Bottom = true;
            this.btnSecurityCode.Border.Left = true;
            this.btnSecurityCode.Border.Right = true;
            this.btnSecurityCode.Border.Top = true;
            this.btnSecurityCode.ClassName = "";
            this.btnSecurityCode.DisplayStyle = Yss.KRichEx.AutoFilter.DisplayStyle.Modal;
            this.btnSecurityCode.DllName = "dll";
            this.btnSecurityCode.FilterCond = "";
            this.btnSecurityCode.Location = new System.Drawing.Point(66, 7);
            this.btnSecurityCode.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataListByTypes";
            controlMethodInfo1.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo1.MethodParams")));
            controlMethodInfo1.MethodParamValues = new string[] {
        "GP,ZQ,JJ,QZ,LC"};
            controlMethodInfo1.Methods = null;
            this.btnSecurityCode.MethodInfo = controlMethodInfo1;
            this.btnSecurityCode.Name = "btnSecurityCode";
            this.btnSecurityCode.ShowCheckBox = true;
            this.btnSecurityCode.Padding = new System.Windows.Forms.Padding(1, 2, 1, 3);
            this.btnSecurityCode.QueryCond = "ZQ,LC";
            this.btnSecurityCode.QueryType = "";
            this.btnSecurityCode.Size = new System.Drawing.Size(119, 21);
            this.btnSecurityCode.TabIndex = 1;
            this.btnSecurityCode.Tag = this.cell2;
            this.btnSecurityCode.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Value_Name;
            this.btnSecurityCode.YssAssociaType = YssSecInformation.Support.Context.AssociaType.sv_sec;
            this.btnSecurityCode.YssCaption = "交易证券";
            // 
            // cell3
            // 
            this.cell3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell3.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell4
            // 
            this.cell4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell4.Text = "权益类型：";
            this.cell4.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // cell5
            // 
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.cboInterestCategory;
            // 
            // cboInterestCategory
            // 
            this.cboInterestCategory.AddedSelItemName = "";
            this.cboInterestCategory.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboInterestCategory.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboInterestCategory.Border.Bottom = true;
            this.cboInterestCategory.Border.Left = true;
            this.cboInterestCategory.Border.Right = true;
            this.cboInterestCategory.Border.Top = true;
            this.cboInterestCategory.ClassName = "";
            this.cboInterestCategory.DisplayName = "C_DT_NAME";
            this.cboInterestCategory.DisplayValue = "C_DT_CODE";
            this.cboInterestCategory.DllName = "dll";
            this.cboInterestCategory.FilterCond = "";
            this.cboInterestCategory.Location = new System.Drawing.Point(257, 7);
            this.cboInterestCategory.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo2.MethodName = "getDataListByKeys";
            controlMethodInfo2.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo2.MethodParams")));
            controlMethodInfo2.MethodParamValues = new string[] {
        "DJPX_FHPX,DJPX_XJDJ,DJPX_DF,DJPX_HLBS,"};
            controlMethodInfo2.Methods = null;
            this.cboInterestCategory.MethodInfo = controlMethodInfo2;
            this.cboInterestCategory.Name = "cboInterestCategory";
            this.cboInterestCategory.Padding = new System.Windows.Forms.Padding(1, 3, 1, 3);
            this.cboInterestCategory.Parameter = "C_DT_NAME";
            this.cboInterestCategory.QueryCond = "DJPX";
            this.cboInterestCategory.QueryType = "CacheType";
            this.cboInterestCategory.Size = new System.Drawing.Size(119, 21);
            this.cboInterestCategory.TabIndex = 2;
            this.cboInterestCategory.Tag = this.cell5;
            this.cboInterestCategory.YssAssociaType = YssInformation.Support.Context.AssociaType.base_dttdmode;
            this.cboInterestCategory.YssKiloDelimiter = true;
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.Color.Black;
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.Color.Black;
            this.cell7.Text = "除权日期：";
            this.cell7.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
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
            this.column1.Width = 66;
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
            this.column3.Width = 5;
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
            this.column5.Width = 120;
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
            this.column7.Width = 66;
            // 
            // Frm_SEC_EQU_PX_L
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.ClientSize = new System.Drawing.Size(912, 590);
            this.DoubleBuffered = true;
            this.Name = "Frm_SEC_EQU_PX_L";
            this.ShowFilterPanel = true;
            this.ShowLeftSearchPanel = true;
            this.Text = "对价派息";
            this.Load += new System.EventHandler(this.Frm_SEC_EQU_PX_L_Load);
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
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }
        #endregion
        /// <summary>
        /// 必需的设计器变量。
        /// </summary>
        private System.ComponentModel.Container components = null;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpDividendDate;
        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
        private Yss.KTable.Models.Column column3;
        private Yss.KTable.Models.Column column4;
        private Yss.KTable.Models.Column column5;
        private Yss.KTable.Models.Column column6;
        private Yss.KTable.Models.Column column7;
        private Yss.KTable.Models.Column column8;
        private Yss.KTable.Models.Row row1;
        private Yss.KTable.Models.Row row2;
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Cell cell2;
        private Yss.KTable.Models.Cell cell3;
        private Yss.KTable.Models.Cell cell4;
        private Yss.KTable.Models.Cell cell5;
        private Yss.KTable.Models.Cell cell6;
        private Yss.KTable.Models.Cell cell7;
        private Yss.KTable.Models.Cell cell8;
        private FAST.Core.BaseControl.YssSelCombox cboInterestCategory;
        private FAST.Core.BaseControl.YssSelCombox btnSecurityCode;
    }
}