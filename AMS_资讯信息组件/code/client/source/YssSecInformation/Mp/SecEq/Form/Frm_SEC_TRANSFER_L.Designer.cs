using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Core.Context;
using FAST.Core.Resource;
namespace YssSecInformation.Mp.SecEq.Form
{
    partial class Frm_SEC_TRANSFER_L
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
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo4 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo3 = new FAST.Core.BaseControl.ControlMethodInfo();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_SEC_TRANSFER_L));
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo1 = new FAST.Core.BaseControl.ControlMethodInfo();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.cboSecCategory = new FAST.Core.BaseControl.YssSelCombox();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cboChanType = new FAST.Core.BaseControl.YssSelCombox();
            this.cboTransferRule = new FAST.Core.BaseControl.YssSelCombox();
            this.cell47 = new Yss.KTable.Models.Cell();
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.tabConMain = new Yss.Controls.TabControl();
            this.tabPageJYZQ = new Yss.Controls.TabPage();
            this.tabPageJYQD = new Yss.Controls.TabPage();
            this.column6 = new Yss.KTable.Models.Column();
            this.column7 = new Yss.KTable.Models.Column();
            this.column8 = new Yss.KTable.Models.Column();
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
            this.tabConMain.SuspendLayout();
            this.tabPageJYZQ.SuspendLayout();
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
            this.tbFilter.Controls.Add(this.cboChanType);
            this.tbFilter.Controls.Add(this.cboSecCategory);
            this.tbFilter.Controls.Add(this.cboTransferRule);
            this.tbFilter.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2});
            this.tbFilter.Size = new System.Drawing.Size(659, 40);
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
            this.tbMain.Location = new System.Drawing.Point(1, 1);
            this.tbMain.Size = new System.Drawing.Size(657, 462);
            // 
            // pnlFilter
            // 
            this.pnlFilter.Size = new System.Drawing.Size(659, 40);
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
            this.barFormStatus.Size = new System.Drawing.Size(659, 28);
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
            this.pnlHost.Controls.Add(this.tabConMain);
            this.pnlHost.Size = new System.Drawing.Size(659, 489);
            // 
            // cboPageSize
            // 
            ////this.cboPageSize.Location = new System.Drawing.Point(354, 2);
            // 
            // txtSearch
            // 
            // 
            // 
            // 
            this.txtSearch.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.txtSearch.Border.BorderColorHot = System.Drawing.Color.FromArgb(((int)(((byte)(109)))), ((int)(((byte)(129)))), ((int)(((byte)(165)))));
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
            this.navBarLeft.Size = new System.Drawing.Size(245, 529);
            // 
            // tbLeftMain
            // 
            this.tbLeftMain.AllowColumnDrag = false;
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
            this.tbLeftMain.ShowCheckBox = true;
            this.tbLeftMain.Size = new System.Drawing.Size(243, 500);
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
            this.btnArrow.Location = new System.Drawing.Point(634, 0);
            // 
            // panelEx1
            // 
            this.panelEx1.Size = new System.Drawing.Size(659, 33);
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
            ////this.txtToPage.Location = new System.Drawing.Point(575, 2);
            // 
            // barPort
            // 
            // 
            // btnBar
            // 
            this.btnBar.Size = new System.Drawing.Size(634, 33);
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
            this.pnlMain.Size = new System.Drawing.Size(659, 590);
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
            this.yssPanel1.Size = new System.Drawing.Size(912, 590);
            // 
            // hpAssist
            // 
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
            this.row2.Height = 25;
            this.row2.Text = null;
            // 
            // cell1
            // 
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.Text = "转换规则：";
            this.cell1.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
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
            this.cell4.Text = "证券品种：";
            this.cell4.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // cell5
            // 
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.cboSecCategory;
            // 
            // cboSecCategory
            // 
            this.cboSecCategory.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboSecCategory.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboSecCategory.Border.BorderColorHot = System.Drawing.Color.FromArgb(((int)(((byte)(109)))), ((int)(((byte)(129)))), ((int)(((byte)(165)))));
            this.cboSecCategory.Border.Bottom = true;
            this.cboSecCategory.Border.Left = true;
            this.cboSecCategory.Border.Right = true;
            this.cboSecCategory.Border.Top = true;
            this.cboSecCategory.DisplayName = "C_SEC_VAR_NAME";
            this.cboSecCategory.DisplayValue = "C_SEC_VAR_CODE";
            this.cboSecCategory.FilterCond = "";
            this.cboSecCategory.KTableTree = true;
            this.cboSecCategory.Location = new System.Drawing.Point(257, 7);
            this.cboSecCategory.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo4.MethodName = "getDataListByTypes";
            controlMethodInfo4.MethodParams = null;
            controlMethodInfo4.MethodParamValues = new string[] {
        "ZQ,"};
            controlMethodInfo4.Methods = null;
            this.cboSecCategory.MethodInfo = controlMethodInfo4;
            this.cboSecCategory.Name = "cboSecCategory";
            this.cboSecCategory.NodeID = "C_SEC_VAR_CODE";
            this.cboSecCategory.Parameter = "C_SEC_VAR_NAME";
            this.cboSecCategory.ParaNodeID = "C_DA_CODE_P";
            this.cboSecCategory.QueryCond = "ZQ";
            this.cboSecCategory.QueryType = "CacheType";
            this.cboSecCategory.SelectMode = Yss.KRichEx.AutoFilter.SelectMode.Any;
            this.cboSecCategory.Size = new System.Drawing.Size(119, 21);
            this.cboSecCategory.SortColumn = "C_SEC_VAR_NAME";
            this.cboSecCategory.YssAssociaType = YssSecInformation.Support.Context.AssociaType.sv_sec;
            this.cboSecCategory.TabIndex = 4;
            this.cboSecCategory.Tag = this.cell5;
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.cell6.ForeColor = System.Drawing.Color.Black;
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F);
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.Text = "渠道类别：";
            // 
            // cell8
            // 
            this.cell8.Font = new System.Drawing.Font("宋体", 9F);
            this.cell8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell8.InnerControl = this.cboChanType;
            // 
            // cboChanType
            // 
            this.cboChanType.AddedSelItemName = "";
            this.cboChanType.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.cboChanType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboChanType.Border.BorderColorHot = System.Drawing.Color.FromArgb(((int)(((byte)(109)))), ((int)(((byte)(129)))), ((int)(((byte)(165)))));
            this.cboChanType.Border.Bottom = true;
            this.cboChanType.Border.Left = true;
            this.cboChanType.Border.Right = true;
            this.cboChanType.Border.Top = true;
            this.cboChanType.DisplayName = "C_DV_NAME";
            this.cboChanType.DisplayValue = "C_DV_CODE";
            this.cboChanType.FilterCond = "";
            this.cboChanType.Location = new System.Drawing.Point(448, 7);
            this.cboChanType.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo3.MethodName = "getDataListByTypes";
            controlMethodInfo3.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo3.MethodParams")));
            controlMethodInfo3.MethodParamValues = new string[] {
        "CHAN_TYPE,"};
            controlMethodInfo3.Methods = new string[] {
        "getKeyConvertMap",
        "getDataByCode",
        "getDataList",
        "getDataListRes",
        "getDataListByTypes",
        "getQueryResByTypes",
        "getDataListByKeys",
        "getQueryResByKeys"};
            this.cboChanType.MethodInfo = controlMethodInfo3;
            this.cboChanType.Name = "cboChanType";
            this.cboChanType.Parameter = "C_DV_NAME";
            this.cboChanType.QueryCond = "CHAN_TYPE";
            this.cboChanType.QueryType = "CacheType";
            this.cboChanType.RequestEveryTime = true;
            this.cboChanType.Size = new System.Drawing.Size(119, 21);
            this.cboChanType.TabIndex = 2;
            this.cboChanType.Tag = this.cell8;
            this.cboChanType.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboChanType.YssCaption = "渠道类型";
            this.cboChanType.YssKiloDelimiter = true;
            // 
            // cboTransferRule
            // 
            this.cboTransferRule.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboTransferRule.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboTransferRule.Border.BorderColorHot = System.Drawing.Color.FromArgb(((int)(((byte)(109)))), ((int)(((byte)(129)))), ((int)(((byte)(165)))));
            this.cboTransferRule.Border.Bottom = true;
            this.cboTransferRule.Border.Left = true;
            this.cboTransferRule.Border.Right = true;
            this.cboTransferRule.Border.Top = true;
            this.cboTransferRule.DisplayName = "C_DV_NAME";
            this.cboTransferRule.DisplayValue = "C_DV_CODE";
            this.cboTransferRule.FilterCond = "";
            this.cboTransferRule.Location = new System.Drawing.Point(66, 7);
            this.cboTransferRule.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataListByKeys";
            controlMethodInfo1.MethodParams = null;
            controlMethodInfo1.MethodParamValues = new string[] {
        "TRAN_SBLSH,"};
            controlMethodInfo1.Methods = null;
            this.cboTransferRule.MethodInfo = controlMethodInfo1;
            this.cboTransferRule.Name = "cboTransferRule";
            this.cboTransferRule.Parameter = "C_DV_NAME";
            this.cboTransferRule.QueryCond = "ISSUE_MODE";
            this.cboTransferRule.QueryType = "CacheType";
            this.cboTransferRule.Size = new System.Drawing.Size(119, 21);
            this.cboTransferRule.TabIndex = 1;
            this.cboTransferRule.Tag = this.cell2;
            this.cboTransferRule.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            // 
            // cell47
            // 
            this.cell47.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell47.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // column1
            // 
            this.column1.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.Width = 66;
            // 
            // column2
            // 
            this.column2.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column2.Width = 120;
            // 
            // column3
            // 
            this.column3.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column3.Width = 5;
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
            this.column5.Width = 120;
            // 
            // tabConMain
            // 
            this.tabConMain.BackColor = System.Drawing.Color.WhiteSmoke;
            this.tabConMain.Controls.Add(this.tabPageJYZQ);
            this.tabConMain.Controls.Add(this.tabPageJYQD);
            this.tabConMain.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tabConMain.Location = new System.Drawing.Point(0, 0);
            this.tabConMain.Name = "tabConMain";
            this.tabConMain.SelectedTab = this.tabPageJYZQ;
            this.tabConMain.ShowTabAddButton = false;
            this.tabConMain.ShowTabHistoryButton = false;
            this.tabConMain.Size = new System.Drawing.Size(659, 489);
            this.tabConMain.TabAutoClose = false;
            this.tabConMain.TabAutoWidth = false;
            this.tabConMain.TabCloseButtonVisible = false;
            this.tabConMain.TabCloseImage = null;
            this.tabConMain.TabCloseImageHot = null;
            this.tabConMain.TabHeight = 25;
            this.tabConMain.TabImageLocation = new System.Drawing.Point(0, 0);
            this.tabConMain.TabImageSize = new System.Drawing.Size(0, 0);
            this.tabConMain.TabIndex = 2;
            this.tabConMain.TabPages.AddRange(new Yss.Controls.TabPage[] {
            this.tabPageJYZQ,
            this.tabPageJYQD});
            this.tabConMain.Tag = "";
            this.tabConMain.Text = "tabConMain";
            this.tabConMain.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.tabConMain.SelectedIndexChanged += new Yss.Controls.TabPageEventHandler(this.tabConMain_SelectedIndexChanged);
            // 
            // tabPageJYZQ
            // 
            this.tabPageJYZQ.Controls.Add(this.tbMain);
            this.tabPageJYZQ.Name = "tabPageJYZQ";
            this.tabPageJYZQ.TabCloseButtonVisible = false;
            this.tabPageJYZQ.Text = "交易证券";
            // 
            // tabPageJYQD
            // 
            this.tabPageJYQD.Name = "tabPageJYQD";
            this.tabPageJYQD.TabCloseButtonVisible = false;
            this.tabPageJYQD.Text = "交易渠道";
            // 
            // column6
            // 
            this.column6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column6.Width = 5;
            // 
            // column7
            // 
            this.column7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column7.Width = 66;
            // 
            // column8
            // 
            this.column8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column8.Width = 120;
            // 
            // Frm_SEC_TRANSFER_L
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.ClientSize = new System.Drawing.Size(912, 590);
            this.DoubleBuffered = true;
            this.Name = "Frm_SEC_TRANSFER_L";
            this.ShowFilterPanel = true;
            this.ShowLeftSearchPanel = true;
            this.Text = "证券代码转换";
            this.Load += new System.EventHandler(this.Frm_SEC_TRANSFER_L_Load);
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
            this.tabConMain.ResumeLayout(false);
            this.tabPageJYZQ.ResumeLayout(false);
            this.ResumeLayout(false);

        }
        #endregion
        /// <summary>
        /// 必需的设计器变量。
        /// </summary>
        private System.ComponentModel.Container components = null;
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
        private FAST.Core.BaseControl.YssSelCombox cboTransferRule;
        private Yss.KTable.Models.Cell cell47;
        private FAST.Core.BaseControl.YssSelCombox cboSecCategory;
        protected Yss.Controls.TabControl tabConMain;
        protected Yss.Controls.TabPage tabPageJYZQ;
        private Yss.Controls.TabPage tabPageJYQD;
        private Yss.KTable.Models.Column column6;
        private Yss.KTable.Models.Column column7;
        private Yss.KTable.Models.Column column8;
        private Yss.KTable.Models.Cell cell6;
        private Yss.KTable.Models.Cell cell7;
        private Yss.KTable.Models.Cell cell8;
        private FAST.Core.BaseControl.YssSelCombox cboChanType;
    }
}