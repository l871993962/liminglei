using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;

using FAST.Core.Context;

using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;


namespace YssProductInfo.Aa.PortCls.Form
{
    partial class Frm_PORT_CLS_S
    {
        /// <summary>
        /// 必需的设计器变量。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 清理所有正在使用的资源。
        /// </summary>
        /// <param name="disposing">如果应释放托管资源，为 true；否则为 false。</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows 窗体设计器生成的代码

        /// <summary>
        /// 设计器支持所需的方法 - 不要
        /// 使用代码编辑器修改此方法的内容。
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_PORT_CLS_S));
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.txtClsPortName = new Yss.KRichEx.YssTextBox();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.row3 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.cboPort = new Yss.KRichEx.TailTextBox();
            this.cell25 = new Yss.KTable.Models.Cell();
            this.cell26 = new Yss.KTable.Models.Cell();
            this.cell27 = new Yss.KTable.Models.Cell();
            this.cboPortP = new FAST.Core.BaseControl.YssSelCombox();
            this.cboClassType = new FAST.Core.BaseControl.YssSelCombox();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.row4 = new Yss.KTable.Models.Row();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.txtClsPortCode = new Yss.KRichEx.YssTextBox();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.row5 = new Yss.KTable.Models.Row();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.cboClassGrade = new FAST.Core.BaseControl.YssSelCombox();
            this.selDvPortCls = new FAST.Core.BaseControl.YssSelCombox();
            this.cell34 = new Yss.KTable.Models.Cell();
            this.dtpFoundDate = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell29 = new Yss.KTable.Models.Cell();
            this.row6 = new Yss.KTable.Models.Row();
            this.cell13 = new Yss.KTable.Models.Cell();
            this.cell14 = new Yss.KTable.Models.Cell();
            this.selDtCode = new FAST.Core.BaseControl.YssSelCombox();
            this.cell15 = new Yss.KTable.Models.Cell();
            this.cell16 = new Yss.KTable.Models.Cell();
            this.cell17 = new Yss.KTable.Models.Cell();
            this.isNetting = new FAST.Core.BaseControl.YssSelCombox();
            this.cboNetFormula = new FAST.Core.BaseControl.YssSelCombox();
            this.cell39 = new Yss.KTable.Models.Cell();
            this.dtpExpiryDate = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell32 = new Yss.KTable.Models.Cell();
            this.cell18 = new Yss.KTable.Models.Cell();
            this.cell19 = new Yss.KTable.Models.Cell();
            this.cell20 = new Yss.KTable.Models.Cell();
            this.cell21 = new Yss.KTable.Models.Cell();
            this.cell22 = new Yss.KTable.Models.Cell();
            this.cell23 = new Yss.KTable.Models.Cell();
            this.cell24 = new Yss.KTable.Models.Cell();
            this.row11 = new Yss.KTable.Models.Row();
            this.cell28 = new Yss.KTable.Models.Cell();
            this.cell30 = new Yss.KTable.Models.Cell();
            this.cell31 = new Yss.KTable.Models.Cell();
            this.row12 = new Yss.KTable.Models.Row();
            this.row13 = new Yss.KTable.Models.Row();
            this.row7 = new Yss.KTable.Models.Row();
            this.cell33 = new Yss.KTable.Models.Cell();
            this.cell35 = new Yss.KTable.Models.Cell();
            this.cell36 = new Yss.KTable.Models.Cell();
            this.cell37 = new Yss.KTable.Models.Cell();
            this.selSyfp = new FAST.Core.BaseControl.YssSelCombox();
            this.row8 = new Yss.KTable.Models.Row();
            this.cell38 = new Yss.KTable.Models.Cell();
            this.cell40 = new Yss.KTable.Models.Cell();
            this.cell41 = new Yss.KTable.Models.Cell();
            this.cell42 = new Yss.KTable.Models.Cell();
            this.dtpLiquid = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell51 = new Yss.KTable.Models.Cell();
            this.row9 = new Yss.KTable.Models.Row();
            this.row10 = new Yss.KTable.Models.Row();
            this.row14 = new Yss.KTable.Models.Row();
            this.row15 = new Yss.KTable.Models.Row();
            this.cell43 = new Yss.KTable.Models.Cell();
            this.cell44 = new Yss.KTable.Models.Cell();
            this.SelRateType = new FAST.Core.BaseControl.YssSelCombox();
            this.cell45 = new Yss.KTable.Models.Cell();
            this.cell46 = new Yss.KTable.Models.Cell();
            this.cell47 = new Yss.KTable.Models.Cell();
            this.txtYield = new Yss.KRichEx.ImprovedTextBox();
            this.cell49 = new Yss.KTable.Models.Cell();
            this.cboRateFormula = new FAST.Core.BaseControl.YssSelCombox();
            this.row16 = new Yss.KTable.Models.Row();
            this.cell48 = new Yss.KTable.Models.Cell();
            this.cell57 = new Yss.KTable.Models.Cell();
            this.cell58 = new Yss.KTable.Models.Cell();
            this.cell59 = new Yss.KTable.Models.Cell();
            this.cboXyPj = new FAST.Core.BaseControl.YssSelCombox();
            this.selRatioFormula = new FAST.Core.BaseControl.YssSelCombox();
            this.row17 = new Yss.KTable.Models.Row();
            this.cell50 = new Yss.KTable.Models.Cell();
            this.cell52 = new Yss.KTable.Models.Cell();
            this.cell53 = new Yss.KTable.Models.Cell();
            this.cell54 = new Yss.KTable.Models.Cell();
            this.splitRight = new Yss.Controls.ExpandableSplitter();
            this.pnlRight = new Yss.Controls.PanelEx(this.components);
            this.navBarRight = new Yss.KNavigation.NavigateBar();
            this.navigateItemMain = new Yss.KNavigation.NavigateItem();
            this.tbRightMain = new Yss.KTable.Models.Table(this.components);
            this.pnlBarPort = new Yss.Controls.PanelEx(this.components);
            this.barPort = new YssDevComponents.DotNetBar.Bar();
            this.chkCheckAll = new YssDevComponents.DotNetBar.CheckBoxItem();
            this.chkBoxCheckedRowsCount = new YssDevComponents.DotNetBar.CheckBoxItem();
            this.tbRightFilter = new Yss.KTable.Models.Table(this.components);
            this.column6 = new Yss.KTable.Models.Column();
            this.column7 = new Yss.KTable.Models.Column();
            this.cboPortCode = new FAST.Core.BaseControl.YssSelCombox();
            this.cell56 = new Yss.KTable.Models.Cell();
            this.row18 = new Yss.KTable.Models.Row();
            this.row19 = new Yss.KTable.Models.Row();
            this.cell55 = new Yss.KTable.Models.Cell();
            this.tbMain.SuspendLayout();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.pnlRight.SuspendLayout();
            this.navBarRight.SuspendLayout();
            this.navigateItemMain.SuspendLayout();
            this.pnlBarPort.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.barPort)).BeginInit();
            this.tbRightFilter.SuspendLayout();
            this.SuspendLayout();
            // 
            // tbMain
            // 
            // 
            // 
            // 
            this.tbMain.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(111)))), ((int)(((byte)(157)))), ((int)(((byte)(217)))));
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
            this.tbMain.Controls.Add(this.cboXyPj);
            this.tbMain.Controls.Add(this.cboRateFormula);
            this.tbMain.Controls.Add(this.selSyfp);
            this.tbMain.Controls.Add(this.cboPort);
            this.tbMain.Controls.Add(this.dtpLiquid);
            this.tbMain.Controls.Add(this.SelRateType);
            this.tbMain.Controls.Add(this.cboPortP);
            this.tbMain.Controls.Add(this.isNetting);
            this.tbMain.Controls.Add(this.selDtCode);
            this.tbMain.Controls.Add(this.selDvPortCls);
            this.tbMain.Controls.Add(this.dtpExpiryDate);
            this.tbMain.Controls.Add(this.dtpFoundDate);
            this.tbMain.Controls.Add(this.txtYield);
            this.tbMain.Controls.Add(this.cboClassGrade);
            this.tbMain.Controls.Add(this.cboNetFormula);
            this.tbMain.Controls.Add(this.cboClassType);
            this.tbMain.Controls.Add(this.txtClsPortName);
            this.tbMain.Controls.Add(this.txtClsPortCode);
            this.tbMain.Controls.Add(this.selRatioFormula);
            this.tbMain.Cursor = System.Windows.Forms.Cursors.Default;
            this.tbMain.GridLineColor = System.Drawing.Color.Transparent;
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3,
            this.row4,
            this.row5,
            this.row6,
            this.row7,
            this.row8,
            this.row9,
            this.row10,
            this.row14,
            this.row15,
            this.row16,
            this.row12,
            this.row13,
            this.row11,
            this.row17});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(746, 372);
            // 
            // btnBar
            // 
            this.btnBar.Size = new System.Drawing.Size(746, 30);
            // 
            // stBarBottom
            // 
            // 
            // 
            // 
            this.stBarBottom.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.stBarBottom.Border.Bottom = false;
            this.stBarBottom.Border.Left = false;
            this.stBarBottom.Border.Right = false;
            this.stBarBottom.Location = new System.Drawing.Point(0, 402);
            this.stBarBottom.Size = new System.Drawing.Size(746, 25);
            this.stBarBottom.StatuType = "新增(&Add...)";
            // 
            // pnlMain
            // 
            this.pnlMain.Controls.Add(this.splitRight);
            this.pnlMain.Controls.Add(this.pnlRight);
            this.pnlMain.Size = new System.Drawing.Size(746, 427);
            this.pnlMain.Controls.SetChildIndex(this.stBarBottom, 0);
            this.pnlMain.Controls.SetChildIndex(this.btnBar, 0);
            this.pnlMain.Controls.SetChildIndex(this.tbMain, 0);
            this.pnlMain.Controls.SetChildIndex(this.pnlRight, 0);
            this.pnlMain.Controls.SetChildIndex(this.splitRight, 0);
            // 
            // yssPanel1
            // 
            // 
            // 
            // 
            this.yssPanel1.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.yssPanel1.Size = new System.Drawing.Size(746, 427);
            // 
            // column1
            // 
            this.column1.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.Name = "column1";
            this.column1.Width = 110;
            // 
            // column2
            // 
            this.column2.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column2.Name = "column2";
            this.column2.Width = 122;
            // 
            // column3
            // 
            this.column3.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column3.Name = "column3";
            this.column3.Width = 30;
            // 
            // column4
            // 
            this.column4.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column4.Name = "column4";
            this.column4.Width = 91;
            // 
            // column5
            // 
            this.column5.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column5.Name = "column5";
            this.column5.Width = 122;
            // 
            // txtClsPortName
            // 
            this.txtClsPortName.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.txtClsPortName.Location = new System.Drawing.Point(353, 68);
            this.txtClsPortName.Name = "txtClsPortName";
            this.txtClsPortName.Size = new System.Drawing.Size(121, 21);
            this.txtClsPortName.TabIndex = 3;
            this.txtClsPortName.Tag = this.cell7;
            this.txtClsPortName.YssCaption = "分级名称";
            this.txtClsPortName.YssIsMust = true;
            this.txtClsPortName.YssLength = 100;
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.InnerControl = this.txtClsPortName;
            this.cell7.Name = "cell7";
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("新宋体", 10F, System.Drawing.FontStyle.Bold);
            this.row1.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row1.FullRowSelected = false;
            this.row1.GroupLineLength = 310;
            this.row1.Height = 33;
            this.row1.IsGroup = true;
            this.row1.Name = "row1";
            this.row1.Text = "分级基本信息";
            // 
            // row2
            // 
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row2.FullRowSelected = false;
            this.row2.GroupLineLength = 310;
            this.row2.Height = 10;
            this.row2.Name = "row2";
            this.row2.Text = null;
            // 
            // row3
            // 
            this.row3.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell1,
            this.cell2,
            this.cell25,
            this.cell26,
            this.cell27});
            this.row3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row3.FullRowSelected = false;
            this.row3.GroupLineLength = 310;
            this.row3.Height = 25;
            this.row3.Name = "row3";
            this.row3.Text = null;
            // 
            // cell1
            // 
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.InnerControl = null;
            this.cell1.Name = "cell1";
            this.cell1.Text = "   投资组合：";
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.cboPort;
            this.cell2.Name = "cell2";
            // 
            // cboPort
            // 
            this.cboPort.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.cboPort.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboPort.Location = new System.Drawing.Point(110, 43);
            this.cboPort.Name = "cboPort";
            this.cboPort.Size = new System.Drawing.Size(121, 21);
            this.cboPort.TabIndex = 22;
            this.cboPort.Tag = this.cell2;
            this.cboPort.TailFont = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cboPort.TailImage = global::YssProductInfo.Properties.Resources.btnCtlSearch;
            this.cboPort.TailImagePosition = new System.Drawing.Point(2, 0);
            this.cboPort.TailImageSize = new System.Drawing.Size(20, 20);
            this.cboPort.YssIsMust = true;
            this.cboPort.TailClick += new System.EventHandler(this.cboPort_TailClick);
            // 
            // cell25
            // 
            this.cell25.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell25.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell25.InnerControl = null;
            this.cell25.Name = "cell25";
            // 
            // cell26
            // 
            this.cell26.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell26.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell26.InnerControl = null;
            this.cell26.Name = "cell26";
            this.cell26.Text = "上级分级组合：";
            // 
            // cell27
            // 
            this.cell27.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell27.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell27.InnerControl = this.cboPortP;
            this.cell27.Name = "cell27";
            // 
            // cboPortP
            // 
            this.cboPortP.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboPortP.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboPortP.DisplayName = "C_PORT_CLS_NAME";
            this.cboPortP.DisplayValue = "C_PORT_CLS_CODE";
            this.cboPortP.IsFillDecimal = false;
            this.cboPortP.KeepDesignValue = true;
            this.cboPortP.KTableTree = true;
            this.cboPortP.Location = new System.Drawing.Point(353, 43);
            this.cboPortP.Margin = new System.Windows.Forms.Padding(0);
            this.cboPortP.Name = "cboPortP";
            this.cboPortP.NodeID = "C_PORT_CLS_CODE";
            this.cboPortP.Parameter = "C_PORT_CLS_CODE~C_PORT_CLS_NAME";
            this.cboPortP.ParaNodeID = "C_PORT_CLS_CODE_P";
            this.cboPortP.PrefixBackColor = System.Drawing.Color.White;
            this.cboPortP.RequestEveryTime = true;
            this.cboPortP.SelectMode = Yss.KRichEx.AutoFilter.SelectMode.Any;
            this.cboPortP.Size = new System.Drawing.Size(121, 21);
            this.cboPortP.SortColumn = "C_PORT_CLS_NAME";
            this.cboPortP.TabIndex = 1;
            this.cboPortP.Tag = this.cell27;
            this.cboPortP.YssAssociaType = YssProductInfo.Support.Context.AssociaType.pd_productgrade;
            this.cboPortP.YssCaption = "分级组合";
            this.cboPortP.YssOnBeforeLoadData += new FAST.Core.BaseControl.YssSelCombox.yssBeforeLoadData(this.cboPortP_YssOnBeforeLoadData);
            // 
            // cboClassType
            // 
            this.cboClassType.AddedSelItemName = "";
            this.cboClassType.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.cboClassType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboClassType.DisplayName = "C_DV_NAME";
            this.cboClassType.DisplayValue = "C_DV_CODE";
            this.cboClassType.IsFillDecimal = false;
            this.cboClassType.Location = new System.Drawing.Point(110, 93);
            this.cboClassType.Margin = new System.Windows.Forms.Padding(0);
            this.cboClassType.MethodInfo.MethodName = "getDataListByTypes";
            this.cboClassType.MethodInfo.MethodParamValues = new string[] {
        "PORT_CLS_TYPE,"};
            this.cboClassType.Name = "cboClassType";
            this.cboClassType.Parameter = "C_DV_NAME";
            this.cboClassType.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.cboClassType.RequestEveryTime = true;
            this.cboClassType.Size = new System.Drawing.Size(121, 21);
            this.cboClassType.TabIndex = 4;
            this.cboClassType.Tag = this.cell9;
            this.cboClassType.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboClassType.YssCaption = "分级类型";
            this.cboClassType.YssIsMust = true;
            this.cboClassType.SelectedValueChanged += new System.EventHandler(this.cboClassType_SelectedValueChanged);
            // 
            // cell9
            // 
            this.cell9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.cell9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell9.InnerControl = this.cboClassType;
            this.cell9.Name = "cell9";
            // 
            // row4
            // 
            this.row4.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell3,
            this.cell4,
            this.cell5,
            this.cell6,
            this.cell7});
            this.row4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row4.FullRowSelected = false;
            this.row4.GroupLineLength = 310;
            this.row4.Height = 25;
            this.row4.Name = "row4";
            this.row4.Text = null;
            // 
            // cell3
            // 
            this.cell3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell3.InnerControl = null;
            this.cell3.Name = "cell3";
            this.cell3.Text = "   分级组合：";
            // 
            // cell4
            // 
            this.cell4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell4.InnerControl = this.txtClsPortCode;
            this.cell4.Name = "cell4";
            // 
            // txtClsPortCode
            // 
            this.txtClsPortCode.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.txtClsPortCode.Location = new System.Drawing.Point(110, 68);
            this.txtClsPortCode.Name = "txtClsPortCode";
            this.txtClsPortCode.Size = new System.Drawing.Size(121, 21);
            this.txtClsPortCode.TabIndex = 2;
            this.txtClsPortCode.Tag = this.cell4;
            this.txtClsPortCode.YssCaption = "分级组合";
            this.txtClsPortCode.YssIsMust = true;
            // 
            // cell5
            // 
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = null;
            this.cell5.Name = "cell5";
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.InnerControl = null;
            this.cell6.Name = "cell6";
            this.cell6.Text = "分级名称：";
            // 
            // row5
            // 
            this.row5.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell8,
            this.cell9,
            this.cell10,
            this.cell11,
            this.cell12});
            this.row5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row5.FullRowSelected = false;
            this.row5.GroupLineLength = 310;
            this.row5.Height = 25;
            this.row5.Name = "row5";
            this.row5.Text = null;
            // 
            // cell8
            // 
            this.cell8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell8.InnerControl = null;
            this.cell8.Name = "cell8";
            this.cell8.Text = "   分级类型：";
            // 
            // cell10
            // 
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.InnerControl = null;
            this.cell10.Name = "cell10";
            // 
            // cell11
            // 
            this.cell11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell11.InnerControl = null;
            this.cell11.Name = "cell11";
            this.cell11.Text = "分级级别：";
            // 
            // cell12
            // 
            this.cell12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.cell12.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell12.InnerControl = this.cboClassGrade;
            this.cell12.Name = "cell12";
            // 
            // cboClassGrade
            // 
            this.cboClassGrade.AddedSelItemName = "";
            this.cboClassGrade.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.cboClassGrade.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboClassGrade.DisplayName = "C_DV_NAME";
            this.cboClassGrade.DisplayValue = "C_DV_CODE";
            this.cboClassGrade.IsFillDecimal = false;
            this.cboClassGrade.Location = new System.Drawing.Point(353, 93);
            this.cboClassGrade.Margin = new System.Windows.Forms.Padding(0);
            this.cboClassGrade.MethodInfo.MethodName = "getDataList";
            this.cboClassGrade.Name = "cboClassGrade";
            this.cboClassGrade.Parameter = "C_DV_NAME";
            this.cboClassGrade.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.cboClassGrade.RequestEveryTime = true;
            this.cboClassGrade.Size = new System.Drawing.Size(121, 21);
            this.cboClassGrade.TabIndex = 5;
            this.cboClassGrade.Tag = this.cell12;
            this.cboClassGrade.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboClassGrade.YssCaption = "分级级别";
            this.cboClassGrade.YssIsMust = true;
            this.cboClassGrade.BeforeDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(this.cboClassGrade_BeforeDropDownClick);
            // 
            // selDvPortCls
            // 
            this.selDvPortCls.AddedSelItemName = "";
            this.selDvPortCls.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.selDvPortCls.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.selDvPortCls.IsFillDecimal = false;
            this.selDvPortCls.Location = new System.Drawing.Point(110, 143);
            this.selDvPortCls.Margin = new System.Windows.Forms.Padding(0);
            this.selDvPortCls.MethodInfo.MethodName = "getDataListByTypes";
            this.selDvPortCls.MethodInfo.MethodParamValues = new string[] {
        "PORT_CLS,"};
            this.selDvPortCls.Name = "selDvPortCls";
            this.selDvPortCls.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.selDvPortCls.Size = new System.Drawing.Size(121, 21);
            this.selDvPortCls.TabIndex = 8;
            this.selDvPortCls.Tag = this.cell34;
            this.selDvPortCls.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.selDvPortCls.YssCaption = "级别类型";
            this.selDvPortCls.YssIsMust = true;
            // 
            // cell34
            // 
            this.cell34.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell34.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell34.InnerControl = this.selDvPortCls;
            this.cell34.Name = "cell34";
            // 
            // dtpFoundDate
            // 
            this.dtpFoundDate.BackColor = System.Drawing.Color.Transparent;
            this.dtpFoundDate.DateBeginChecked = true;
            this.dtpFoundDate.DateEndChecked = true;
            this.dtpFoundDate.Location = new System.Drawing.Point(110, 294);
            this.dtpFoundDate.Margin = new System.Windows.Forms.Padding(0);
            this.dtpFoundDate.Name = "dtpFoundDate";
            this.dtpFoundDate.Size = new System.Drawing.Size(121, 21);
            this.dtpFoundDate.TabIndex = 13;
            this.dtpFoundDate.Tag = this.cell29;
            // 
            // cell29
            // 
            this.cell29.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell29.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell29.InnerControl = this.dtpFoundDate;
            this.cell29.Name = "cell29";
            // 
            // row6
            // 
            this.row6.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell13,
            this.cell14,
            this.cell15,
            this.cell16,
            this.cell17});
            this.row6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row6.FullRowSelected = false;
            this.row6.GroupLineLength = 310;
            this.row6.Height = 25;
            this.row6.Name = "row6";
            this.row6.Text = null;
            // 
            // cell13
            // 
            this.cell13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell13.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell13.InnerControl = null;
            this.cell13.Name = "cell13";
            this.cell13.Text = "   分级币种：";
            // 
            // cell14
            // 
            this.cell14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.cell14.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell14.InnerControl = this.selDtCode;
            this.cell14.Name = "cell14";
            // 
            // selDtCode
            // 
            this.selDtCode.AddedSelItemName = "";
            this.selDtCode.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.selDtCode.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.selDtCode.IsFillDecimal = false;
            this.selDtCode.Location = new System.Drawing.Point(110, 118);
            this.selDtCode.Margin = new System.Windows.Forms.Padding(0);
            this.selDtCode.MethodInfo.MethodName = "getDataList";
            this.selDtCode.Name = "selDtCode";
            this.selDtCode.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.selDtCode.Size = new System.Drawing.Size(121, 21);
            this.selDtCode.TabIndex = 6;
            this.selDtCode.Tag = this.cell14;
            this.selDtCode.YssAssociaType = YssInformation.Support.Context.AssociaType.base_currency;
            this.selDtCode.YssCaption = "分级币种";
            this.selDtCode.YssIsMust = true;
            // 
            // cell15
            // 
            this.cell15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.cell15.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell15.InnerControl = null;
            this.cell15.Name = "cell15";
            // 
            // cell16
            // 
            this.cell16.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell16.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell16.InnerControl = null;
            this.cell16.Name = "cell16";
            this.cell16.Text = "轧   差：";
            // 
            // cell17
            // 
            this.cell17.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.cell17.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell17.InnerControl = this.isNetting;
            this.cell17.Name = "cell17";
            // 
            // isNetting
            // 
            this.isNetting.AddedSelItemName = "";
            this.isNetting.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.isNetting.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.isNetting.DisplayName = "C_DV_NAME";
            this.isNetting.DisplayValue = "C_DV_CODE";
            this.isNetting.IsFillDecimal = false;
            this.isNetting.Location = new System.Drawing.Point(353, 118);
            this.isNetting.Margin = new System.Windows.Forms.Padding(0);
            this.isNetting.MethodInfo.MethodName = "getDataListByTypes";
            this.isNetting.MethodInfo.MethodParamValues = new string[] {
        "BOOL_TYPE,"};
            this.isNetting.Name = "isNetting";
            this.isNetting.NodeID = "C_DV_CODE";
            this.isNetting.Parameter = "C_DV_NAME";
            this.isNetting.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.isNetting.RequestEveryTime = true;
            this.isNetting.Size = new System.Drawing.Size(121, 21);
            this.isNetting.TabIndex = 7;
            this.isNetting.Tag = this.cell17;
            this.isNetting.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.isNetting.YssCaption = "轧差";
            this.isNetting.YssIsMust = true;
            // 
            // cboNetFormula
            // 
            this.cboNetFormula.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboNetFormula.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboNetFormula.DisplayName = "C_ALGO_NAME";
            this.cboNetFormula.DisplayValue = "C_ALGO_CODE";
            this.cboNetFormula.ExpandButtonImage = ((System.Drawing.Image)(resources.GetObject("cboNetFormula.ExpandButtonImage")));
            this.cboNetFormula.ExpandButtonImageSize = new System.Drawing.Size(18, 19);
            this.cboNetFormula.ExpandButtonVisible = true;
            this.cboNetFormula.IsFillDecimal = false;
            this.cboNetFormula.Location = new System.Drawing.Point(110, 166);
            this.cboNetFormula.Margin = new System.Windows.Forms.Padding(0);
            this.cboNetFormula.MethodInfo.MethodName = "getDataListByTypes";
            this.cboNetFormula.MethodInfo.MethodParamValues = new string[] {
        "SF_FJCS,"};
            this.cboNetFormula.Name = "cboNetFormula";
            this.cboNetFormula.Parameter = "C_ALGO_CODE~C_ALGO_NAME";
            this.cboNetFormula.PrefixBackColor = System.Drawing.Color.White;
            this.cboNetFormula.ServiceComponent = "YSSUCO";
            this.cboNetFormula.Size = new System.Drawing.Size(121, 21);
            this.cboNetFormula.TabIndex = 10;
            this.cboNetFormula.Tag = this.cell39;
            this.cboNetFormula.ValidateServiceComponent = true;
            this.cboNetFormula.Visible = false;
            this.cboNetFormula.YssAssociaType = FAST.Core.Context.AssociaFAST.NULL;
            this.cboNetFormula.ExpandClick += new System.EventHandler(this.formula_ExpandClick);
            this.cboNetFormula.TextChanged += new System.EventHandler(this.cboNetFormula_TextChanged);
            // 
            // cell39
            // 
            this.cell39.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell39.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell39.InnerControl = this.cboNetFormula;
            this.cell39.Name = "cell39";
            // 
            // dtpExpiryDate
            // 
            this.dtpExpiryDate.BackColor = System.Drawing.Color.Transparent;
            this.dtpExpiryDate.DateBeginChecked = true;
            this.dtpExpiryDate.DateEndChecked = true;
            this.dtpExpiryDate.Location = new System.Drawing.Point(353, 294);
            this.dtpExpiryDate.Margin = new System.Windows.Forms.Padding(0);
            this.dtpExpiryDate.Name = "dtpExpiryDate";
            this.dtpExpiryDate.Size = new System.Drawing.Size(121, 21);
            this.dtpExpiryDate.TabIndex = 14;
            this.dtpExpiryDate.Tag = this.cell32;
            // 
            // cell32
            // 
            this.cell32.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell32.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell32.InnerControl = this.dtpExpiryDate;
            this.cell32.Name = "cell32";
            // 
            // cell18
            // 
            this.cell18.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell18.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell18.InnerControl = null;
            this.cell18.Name = "cell18";
            this.cell18.Text = "   年化收益率：";
            // 
            // cell19
            // 
            this.cell19.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell19.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell19.InnerControl = null;
            this.cell19.Name = "cell19";
            // 
            // cell20
            // 
            this.cell20.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell20.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell20.InnerControl = null;
            this.cell20.Name = "cell20";
            // 
            // cell21
            // 
            this.cell21.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell21.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell21.InnerControl = null;
            this.cell21.Name = "cell21";
            this.cell21.Text = "额外收益率：";
            // 
            // cell22
            // 
            this.cell22.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell22.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell22.InnerControl = null;
            this.cell22.Name = "cell22";
            // 
            // cell23
            // 
            this.cell23.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell23.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell23.InnerControl = null;
            this.cell23.Name = "cell23";
            this.cell23.Text = "   净值计算公式：";
            // 
            // cell24
            // 
            this.cell24.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell24.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell24.InnerControl = null;
            this.cell24.Name = "cell24";
            // 
            // row11
            // 
            this.row11.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell28,
            this.cell29,
            this.cell30,
            this.cell31,
            this.cell32});
            this.row11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row11.FullRowSelected = false;
            this.row11.Height = 25;
            this.row11.Name = "row11";
            this.row11.Text = null;
            // 
            // cell28
            // 
            this.cell28.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell28.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell28.InnerControl = null;
            this.cell28.Name = "cell28";
            this.cell28.Text = "   成立日期：";
            // 
            // cell30
            // 
            this.cell30.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell30.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell30.InnerControl = null;
            this.cell30.Name = "cell30";
            // 
            // cell31
            // 
            this.cell31.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell31.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell31.InnerControl = null;
            this.cell31.Name = "cell31";
            this.cell31.Text = "到期日期：";
            // 
            // row12
            // 
            this.row12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row12.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row12.FullRowSelected = false;
            this.row12.GroupLineLength = 310;
            this.row12.Height = 10;
            this.row12.IsGroup = true;
            this.row12.Name = "row12";
            this.row12.Text = null;
            // 
            // row13
            // 
            this.row13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row13.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row13.FullRowSelected = false;
            this.row13.Height = 6;
            this.row13.Name = "row13";
            this.row13.Text = null;
            // 
            // row7
            // 
            this.row7.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell33,
            this.cell34,
            this.cell35,
            this.cell36,
            this.cell37});
            this.row7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row7.FullRowSelected = false;
            this.row7.Name = "row7";
            this.row7.Text = null;
            // 
            // cell33
            // 
            this.cell33.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell33.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell33.InnerControl = null;
            this.cell33.Name = "cell33";
            this.cell33.Text = "   级别类型：";
            // 
            // cell35
            // 
            this.cell35.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell35.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell35.InnerControl = null;
            this.cell35.Name = "cell35";
            // 
            // cell36
            // 
            this.cell36.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell36.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell36.InnerControl = null;
            this.cell36.Name = "cell36";
            this.cell36.Text = "收益分配：";
            // 
            // cell37
            // 
            this.cell37.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell37.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell37.InnerControl = this.selSyfp;
            this.cell37.Name = "cell37";
            // 
            // selSyfp
            // 
            this.selSyfp.AddedSelItemName = "";
            this.selSyfp.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.selSyfp.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.selSyfp.DisplayName = "C_DV_NAME";
            this.selSyfp.DisplayValue = "C_DV_CODE";
            this.selSyfp.IsFillDecimal = false;
            this.selSyfp.Location = new System.Drawing.Point(353, 143);
            this.selSyfp.Margin = new System.Windows.Forms.Padding(0);
            this.selSyfp.MethodInfo.MethodName = "getDataListByTypes";
            this.selSyfp.MethodInfo.MethodParamValues = new string[] {
        "SYFP,"};
            this.selSyfp.Name = "selSyfp";
            this.selSyfp.Parameter = "C_DV_NAME";
            this.selSyfp.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.selSyfp.RequestEveryTime = true;
            this.selSyfp.Size = new System.Drawing.Size(121, 21);
            this.selSyfp.TabIndex = 9;
            this.selSyfp.Tag = this.cell37;
            this.selSyfp.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.selSyfp.YssCaption = "收益分配";
            this.selSyfp.YssIsMust = true;
            // 
            // row8
            // 
            this.row8.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell38,
            this.cell39,
            this.cell40,
            this.cell41,
            this.cell42});
            this.row8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row8.FullRowSelected = false;
            this.row8.Name = "row8";
            this.row8.Text = null;
            // 
            // cell38
            // 
            this.cell38.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell38.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell38.InnerControl = null;
            this.cell38.Name = "cell38";
            this.cell38.Text = "   算法公式：";
            // 
            // cell40
            // 
            this.cell40.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell40.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell40.InnerControl = null;
            this.cell40.Name = "cell40";
            // 
            // cell41
            // 
            this.cell41.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell41.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell41.InnerControl = null;
            this.cell41.Name = "cell41";
            // 
            // cell42
            // 
            this.cell42.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell42.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell42.InnerControl = null;
            this.cell42.Name = "cell42";
            // 
            // dtpLiquid
            // 
            this.dtpLiquid.BackColor = System.Drawing.Color.Transparent;
            this.dtpLiquid.DateBeginChecked = true;
            this.dtpLiquid.DateEndChecked = true;
            this.dtpLiquid.Location = new System.Drawing.Point(110, 319);
            this.dtpLiquid.Margin = new System.Windows.Forms.Padding(0);
            this.dtpLiquid.Name = "dtpLiquid";
            this.dtpLiquid.Size = new System.Drawing.Size(121, 21);
            this.dtpLiquid.TabIndex = 21;
            this.dtpLiquid.Tag = this.cell51;
            // 
            // cell51
            // 
            this.cell51.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell51.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell51.InnerControl = this.dtpLiquid;
            this.cell51.Name = "cell51";
            // 
            // row9
            // 
            this.row9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row9.FullRowSelected = false;
            this.row9.Height = 10;
            this.row9.Name = "row9";
            this.row9.Text = null;
            // 
            // row10
            // 
            this.row10.Font = new System.Drawing.Font("新宋体", 10F, System.Drawing.FontStyle.Bold);
            this.row10.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row10.FullRowSelected = false;
            this.row10.GroupLineLength = 310;
            this.row10.IsGroup = true;
            this.row10.Name = "row10";
            this.row10.Text = "收益率信息";
            // 
            // row14
            // 
            this.row14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row14.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row14.FullRowSelected = false;
            this.row14.GroupLineLength = 310;
            this.row14.Height = 10;
            this.row14.Name = "row14";
            this.row14.Text = null;
            // 
            // row15
            // 
            this.row15.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell43,
            this.cell44,
            this.cell45,
            this.cell46,
            this.cell47});
            this.row15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row15.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row15.FullRowSelected = false;
            this.row15.Name = "row15";
            this.row15.Text = null;
            // 
            // cell43
            // 
            this.cell43.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell43.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell43.InnerControl = null;
            this.cell43.Name = "cell43";
            this.cell43.Text = "   收益率类型：";
            // 
            // cell44
            // 
            this.cell44.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell44.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell44.InnerControl = this.SelRateType;
            this.cell44.Name = "cell44";
            // 
            // SelRateType
            // 
            this.SelRateType.AddedSelItemName = "";
            this.SelRateType.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.SelRateType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.SelRateType.DisplayName = "C_DV_NAME";
            this.SelRateType.DisplayValue = "C_DV_CODE";
            this.SelRateType.IsFillDecimal = false;
            this.SelRateType.Location = new System.Drawing.Point(110, 232);
            this.SelRateType.Margin = new System.Windows.Forms.Padding(0);
            this.SelRateType.MethodInfo.MethodName = "getDataListByTypes";
            this.SelRateType.MethodInfo.MethodParamValues = new string[] {
        "SYLLX,"};
            this.SelRateType.Name = "SelRateType";
            this.SelRateType.Parameter = "C_DV_NAME";
            this.SelRateType.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.SelRateType.RequestEveryTime = true;
            this.SelRateType.Size = new System.Drawing.Size(121, 21);
            this.SelRateType.TabIndex = 15;
            this.SelRateType.Tag = this.cell44;
            this.SelRateType.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.SelRateType.YssCaption = "收益率类型";
            this.SelRateType.YssIsMust = true;
            this.SelRateType.SelectedValueChanged += new System.EventHandler(this.selRateType_SelectedValueChanged);
            // 
            // cell45
            // 
            this.cell45.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell45.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell45.InnerControl = null;
            this.cell45.Name = "cell45";
            // 
            // cell46
            // 
            this.cell46.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell46.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell46.InnerControl = null;
            this.cell46.Name = "cell46";
            this.cell46.Text = "年化收益率：";
            // 
            // cell47
            // 
            this.cell47.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell47.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell47.InnerControl = this.txtYield;
            this.cell47.Name = "cell47";
            // 
            // txtYield
            // 
            // 
            // 
            // 
            this.txtYield.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.txtYield.IsFillDecimal = false;
            this.txtYield.Location = new System.Drawing.Point(353, 232);
            this.txtYield.Name = "txtYield";
            this.txtYield.PrefixForeColor = System.Drawing.Color.Blue;
            this.txtYield.Size = new System.Drawing.Size(121, 21);
            this.txtYield.SufixForeColor = System.Drawing.Color.Blue;
            this.txtYield.TabIndex = 11;
            this.txtYield.Tag = this.cell47;
            this.txtYield.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtYield.YssCaption = "年化收益率";
            this.txtYield.YssKiloDelimiter = true;
            this.txtYield.YssLength = 45;
            this.txtYield.YssNumeric = "30, 15";
            // 
            // cell49
            // 
            this.cell49.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell49.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell49.InnerControl = this.cboRateFormula;
            this.cell49.Name = "cell49";
            // 
            // cboRateFormula
            // 
            this.cboRateFormula.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboRateFormula.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboRateFormula.DisplayName = "C_ALGO_NAME";
            this.cboRateFormula.DisplayValue = "C_ALGO_CODE";
            this.cboRateFormula.ExpandButtonImage = ((System.Drawing.Image)(resources.GetObject("cboRateFormula.ExpandButtonImage")));
            this.cboRateFormula.ExpandButtonImageSize = new System.Drawing.Size(18, 19);
            this.cboRateFormula.ExpandButtonVisible = true;
            this.cboRateFormula.IsFillDecimal = false;
            this.cboRateFormula.Location = new System.Drawing.Point(110, 255);
            this.cboRateFormula.Margin = new System.Windows.Forms.Padding(0);
            this.cboRateFormula.MethodInfo.MethodName = "getDataListByTypes";
            this.cboRateFormula.MethodInfo.MethodParamValues = new string[] {
        "SF_FJCS,"};
            this.cboRateFormula.Name = "cboRateFormula";
            this.cboRateFormula.Parameter = "C_ALGO_CODE~C_ALGO_NAME";
            this.cboRateFormula.PrefixBackColor = System.Drawing.Color.White;
            this.cboRateFormula.ServiceComponent = "YSSUCO";
            this.cboRateFormula.Size = new System.Drawing.Size(121, 21);
            this.cboRateFormula.TabIndex = 20;
            this.cboRateFormula.Tag = this.cell49;
            this.cboRateFormula.ValidateServiceComponent = true;
            this.cboRateFormula.YssAssociaType = FAST.Core.Context.AssociaFAST.NULL;
            this.cboRateFormula.YssCaption = "计息方式";
            this.cboRateFormula.ExpandClick += new System.EventHandler(this.formula_ExpandClick);
            // 
            // row16
            // 
            this.row16.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell48,
            this.cell49,
            this.cell57,
            this.cell58,
            this.cell59});
            this.row16.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row16.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row16.FullRowSelected = false;
            this.row16.Name = "row16";
            this.row16.Text = null;
            // 
            // cell48
            // 
            this.cell48.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell48.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell48.InnerControl = null;
            this.cell48.Name = "cell48";
            this.cell48.Text = "   收益率公式：";
            // 
            // cell57
            // 
            this.cell57.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell57.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell57.InnerControl = null;
            this.cell57.Name = "cell57";
            // 
            // cell58
            // 
            this.cell58.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell58.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell58.InnerControl = null;
            this.cell58.Name = "cell58";
            this.cell58.Text = "信用评级：";
            // 
            // cell59
            // 
            this.cell59.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell59.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell59.InnerControl = this.cboXyPj;
            this.cell59.Name = "cell59";
            // 
            // cboXyPj
            // 
            this.cboXyPj.AddedSelItemName = "";
            this.cboXyPj.DisplayName = "C_DV_NAME";
            this.cboXyPj.DisplayValue = "C_DV_CODE";
            this.cboXyPj.Location = new System.Drawing.Point(353, 255);
            this.cboXyPj.Margin = new System.Windows.Forms.Padding(0);
            this.cboXyPj.MethodInfo.MethodName = "getDataListByTypes";
            this.cboXyPj.MethodInfo.MethodParamValues = new string[] {
        "ZQXY_GREAD,"};
            this.cboXyPj.Name = "cboXyPj";
            this.cboXyPj.PrefixBackColor = System.Drawing.Color.White;
            this.cboXyPj.Size = new System.Drawing.Size(121, 21);
            this.cboXyPj.TabIndex = 23;
            this.cboXyPj.Tag = this.cell59;
            this.cboXyPj.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboXyPj.YssCaption = "信用评级";
            // 
            // selRatioFormula
            // 
            this.selRatioFormula.AddedSelItemName = "";
            // 
            // 
            // 
            this.selRatioFormula.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.selRatioFormula.DisplayName = "C_FORMULA_NAME";
            this.selRatioFormula.DisplayValue = "C_FORMULA_CODE";
            this.selRatioFormula.IsFillDecimal = false;
            this.selRatioFormula.Location = new System.Drawing.Point(344, 14);
            this.selRatioFormula.Margin = new System.Windows.Forms.Padding(0);
            this.selRatioFormula.MethodInfo.MethodName = "getDataList";
            this.selRatioFormula.Name = "selRatioFormula";
            this.selRatioFormula.Parameter = "C_FORMULA_CODE~C_FORMULA_NAME";
            this.selRatioFormula.PrefixBackColor = System.Drawing.Color.White;
            this.selRatioFormula.ServiceComponent = "YSSUCO";
            this.selRatioFormula.Size = new System.Drawing.Size(11031, 21);
            this.selRatioFormula.TabIndex = 9;
            this.selRatioFormula.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Value_Name;
            this.selRatioFormula.ValidateServiceComponent = true;
            this.selRatioFormula.Visible = false;
            this.selRatioFormula.YssAssociaType = FAST.Core.Context.AssociaFAST.NULL;
            this.selRatioFormula.YssCaption = "比率公式";
            // 
            // row17
            // 
            this.row17.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell50,
            this.cell51,
            this.cell52,
            this.cell53,
            this.cell54});
            this.row17.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row17.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row17.FullRowSelected = false;
            this.row17.Name = "row17";
            this.row17.Text = null;
            // 
            // cell50
            // 
            this.cell50.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell50.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell50.InnerControl = null;
            this.cell50.Name = "cell50";
            this.cell50.Text = "   清算日期：";
            // 
            // cell52
            // 
            this.cell52.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell52.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell52.InnerControl = null;
            this.cell52.Name = "cell52";
            // 
            // cell53
            // 
            this.cell53.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell53.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell53.InnerControl = null;
            this.cell53.Name = "cell53";
            // 
            // cell54
            // 
            this.cell54.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell54.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell54.InnerControl = null;
            this.cell54.Name = "cell54";
            // 
            // splitRight
            // 
            this.splitRight.Dock = System.Windows.Forms.DockStyle.Right;
            this.splitRight.Location = new System.Drawing.Point(497, 30);
            this.splitRight.Margin = new System.Windows.Forms.Padding(3, 0, 3, 3);
            this.splitRight.Name = "splitRight";
            this.splitRight.Size = new System.Drawing.Size(5, 372);
            this.splitRight.TabIndex = 22;
            this.splitRight.TabStop = false;
            // 
            // pnlRight
            // 
            // 
            // 
            // 
            this.pnlRight.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.pnlRight.Border.Bottom = false;
            this.pnlRight.Border.Left = false;
            this.pnlRight.Border.Right = false;
            this.pnlRight.Border.Top = false;
            this.pnlRight.Controls.Add(this.navBarRight);
            this.pnlRight.Controls.Add(this.pnlBarPort);
            this.pnlRight.Controls.Add(this.tbRightFilter);
            this.pnlRight.Dock = System.Windows.Forms.DockStyle.Right;
            this.pnlRight.Location = new System.Drawing.Point(502, 30);
            this.pnlRight.Name = "pnlRight";
            this.pnlRight.Size = new System.Drawing.Size(244, 372);
            this.pnlRight.TabIndex = 23;
            // 
            // navBarRight
            // 
            this.navBarRight.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.navBarRight.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(164)))), ((int)(((byte)(187)))), ((int)(((byte)(217)))));
            this.navBarRight.Border.Left = false;
            this.navBarRight.Border.Right = false;
            this.navBarRight.CaptionImage = ((System.Drawing.Image)(resources.GetObject("navBarRight.CaptionImage")));
            this.navBarRight.CaptionImageSelected = ((System.Drawing.Image)(resources.GetObject("navBarRight.CaptionImageSelected")));
            this.navBarRight.Controls.Add(this.navigateItemMain);
            this.navBarRight.Dock = System.Windows.Forms.DockStyle.Fill;
            this.navBarRight.Items.AddRange(new Yss.KNavigation.NavigateItem[] {
            this.navigateItemMain});
            this.navBarRight.Location = new System.Drawing.Point(0, 42);
            this.navBarRight.Name = "navBarRight";
            this.navBarRight.SelectedItem = this.navigateItemMain;
            this.navBarRight.Size = new System.Drawing.Size(244, 305);
            this.navBarRight.TabIndex = 1;
            this.navBarRight.Text = "navigateBar1";
            // 
            // navigateItemMain
            // 
            this.navigateItemMain.Controls.Add(this.tbRightMain);
            this.navigateItemMain.Name = "navigateItemMain";
            this.navigateItemMain.Text = "继承内容";
            // 
            // tbRightMain
            // 
            this.tbRightMain.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.tbRightMain.Border.Bottom = false;
            this.tbRightMain.Border.Left = false;
            this.tbRightMain.Border.Right = false;
            this.tbRightMain.DefaultToolStripItems = Yss.KTable.Enums.SysToolStripItems.GardingMenu;
            this.tbRightMain.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tbRightMain.GridLineColor = System.Drawing.Color.LightSteelBlue;
            this.tbRightMain.Location = new System.Drawing.Point(1, 28);
            this.tbRightMain.Name = "tbRightMain";
            this.tbRightMain.PlusMinusLineColor = System.Drawing.SystemColors.ControlText;
            this.tbRightMain.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconIndentLine;
            this.tbRightMain.SelectionMode = System.Windows.Forms.SelectionMode.MultiExtended;
            this.tbRightMain.ShowCheckBox = true;
            this.tbRightMain.ShowColumnHeader = false;
            this.tbRightMain.Size = new System.Drawing.Size(242, 276);
            this.tbRightMain.TabIndex = 2;
            this.tbRightMain.Text = "table1";
            this.tbRightMain.CheckStateChanged += new Yss.KTable.Events.CheckStateChanged(this.tbRightMain_CheckStateChanged);
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
            this.pnlBarPort.Controls.Add(this.barPort);
            this.pnlBarPort.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.pnlBarPort.Location = new System.Drawing.Point(0, 347);
            this.pnlBarPort.Name = "pnlBarPort";
            this.pnlBarPort.Size = new System.Drawing.Size(244, 25);
            this.pnlBarPort.TabIndex = 14;
            // 
            // barPort
            // 
            this.barPort.AccessibleRole = System.Windows.Forms.AccessibleRole.StatusBar;
            this.barPort.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(247)))), ((int)(((byte)(247)))), ((int)(((byte)(247)))));
            this.barPort.BarType = YssDevComponents.DotNetBar.eBarType.StatusBar;
            this.barPort.Dock = System.Windows.Forms.DockStyle.Fill;
            this.barPort.DockSide = YssDevComponents.DotNetBar.eDockSide.Bottom;
            this.barPort.Items.AddRange(new YssDevComponents.DotNetBar.BaseItem[] {
            this.chkCheckAll,
            this.chkBoxCheckedRowsCount});
            this.barPort.Location = new System.Drawing.Point(0, 0);
            this.barPort.Name = "barPort";
            this.barPort.RoundCorners = false;
            this.barPort.Size = new System.Drawing.Size(244, 27);
            this.barPort.Stretch = true;
            this.barPort.Style = YssDevComponents.DotNetBar.eDotNetBarStyle.Office2007;
            this.barPort.TabIndex = 3;
            this.barPort.TabStop = false;
            // 
            // chkCheckAll
            // 
            this.chkCheckAll.BeginGroup = true;
            this.chkCheckAll.Name = "chkCheckAll";
            this.chkCheckAll.Shortcuts.Add(YssDevComponents.DotNetBar.eShortcut.CtrlA);
            this.chkCheckAll.Text = "全选";
            this.chkCheckAll.Tooltip = "全选";
            this.chkCheckAll.CheckedChanged += new YssDevComponents.DotNetBar.CheckBoxChangeEventHandler(this.chkCheckAll_CheckedChanged);
            // 
            // chkBoxCheckedRowsCount
            // 
            this.chkBoxCheckedRowsCount.Name = "chkBoxCheckedRowsCount";
            this.chkBoxCheckedRowsCount.Text = "0";
            this.chkBoxCheckedRowsCount.TextColor = System.Drawing.Color.Red;
            this.chkBoxCheckedRowsCount.CheckedChanged += new YssDevComponents.DotNetBar.CheckBoxChangeEventHandler(this.chkBoxCheckedRowsCount_CheckedChanged);
            // 
            // tbRightFilter
            // 
            this.tbRightFilter.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(243)))), ((int)(((byte)(245)))), ((int)(((byte)(249)))));
            // 
            // 
            // 
            this.tbRightFilter.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(111)))), ((int)(((byte)(157)))), ((int)(((byte)(217)))));
            this.tbRightFilter.Border.Bottom = false;
            this.tbRightFilter.Border.Left = false;
            this.tbRightFilter.Border.Right = false;
            this.tbRightFilter.Border.Top = false;
            this.tbRightFilter.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.tbRightFilter.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.column6,
            this.column7});
            this.tbRightFilter.Controls.Add(this.cboPortCode);
            this.tbRightFilter.Cursor = System.Windows.Forms.Cursors.Default;
            this.tbRightFilter.DefaultToolStripItems = ((Yss.KTable.Enums.SysToolStripItems)((((((((((((Yss.KTable.Enums.SysToolStripItems.ShowHideColumn | Yss.KTable.Enums.SysToolStripItems.GroupByColumn)
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
            this.tbRightFilter.Dock = System.Windows.Forms.DockStyle.Top;
            this.tbRightFilter.IsShowScrollbar = false;
            this.tbRightFilter.Location = new System.Drawing.Point(0, 0);
            this.tbRightFilter.Name = "tbRightFilter";
            this.tbRightFilter.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row18,
            this.row19});
            this.tbRightFilter.ShowColumnHeader = false;
            this.tbRightFilter.Size = new System.Drawing.Size(244, 42);
            this.tbRightFilter.TabIndex = 0;
            this.tbRightFilter.Text = "table1";
            // 
            // column6
            // 
            this.column6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column6.Name = "column6";
            this.column6.Width = 110;
            // 
            // column7
            // 
            this.column7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column7.Name = "column7";
            this.column7.Width = 122;
            // 
            // cboPortCode
            // 
            this.cboPortCode.AddedSelItemName = "";
            this.cboPortCode.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.cboPortCode.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboPortCode.DisplayName = "C_PORT_CLS_NAME";
            this.cboPortCode.DisplayStyle = Yss.KRichEx.AutoFilter.DisplayStyle.Modal;
            this.cboPortCode.DisplayValue = "C_PORT_CLS_CODE";
            this.cboPortCode.IsFillDecimal = false;
            this.cboPortCode.KTableTree = true;
            this.cboPortCode.Location = new System.Drawing.Point(110, 10);
            this.cboPortCode.Margin = new System.Windows.Forms.Padding(0);
            this.cboPortCode.Name = "cboPortCode";
            this.cboPortCode.NodeID = "C_PORT_CLS_CODE";
            this.cboPortCode.Parameter = "C_PORT_CLS_CODE~C_PORT_CLS_NAME";
            this.cboPortCode.ParaNodeID = "C_PORT_CLS_CODE_P";
            this.cboPortCode.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.cboPortCode.ShowColumnHeader = true;
            this.cboPortCode.Size = new System.Drawing.Size(121, 21);
            this.cboPortCode.SortColumn = "C_PORT_CLS_NAME";
            this.cboPortCode.TabIndex = 2;
            this.cboPortCode.Tag = this.cell56;
            this.cboPortCode.YssAssociaType = YssProductInfo.Support.Context.AssociaType.pd_productgrade;
            this.cboPortCode.YssCaption = "参照组合";
            this.cboPortCode.YssIsMust = true;
            this.cboPortCode.BeforeDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(this.cboPortCode_BeforeDropDownClick);
            // 
            // cell56
            // 
            this.cell56.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell56.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell56.InnerControl = this.cboPortCode;
            this.cell56.Name = "cell56";
            // 
            // row18
            // 
            this.row18.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row18.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row18.Height = 10;
            this.row18.Name = "row18";
            this.row18.Text = null;
            // 
            // row19
            // 
            this.row19.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell55,
            this.cell56});
            this.row19.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row19.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row19.Name = "row19";
            this.row19.Text = null;
            // 
            // cell55
            // 
            this.cell55.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.cell55.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell55.InnerControl = null;
            this.cell55.Name = "cell55";
            this.cell55.Text = "参照组合：";
            // 
            // Frm_PORT_CLS_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.ClientSize = new System.Drawing.Size(746, 427);
            this.DoubleBuffered = true;
            this.Name = "Frm_PORT_CLS_S";
            this.StatuType = "新增(&Add...)";
            this.Text = "分级产品参数";
            this.YssSetStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.Load += new System.EventHandler(this.Frm_PORT_CLS_S_Load);
            this.tbMain.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.pnlRight.ResumeLayout(false);
            this.navBarRight.ResumeLayout(false);
            this.navigateItemMain.ResumeLayout(false);
            this.pnlBarPort.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.barPort)).EndInit();
            this.tbRightFilter.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
        private Yss.KTable.Models.Column column3;
        private Yss.KTable.Models.Column column4;
        private Yss.KTable.Models.Column column5;
        private Yss.KRichEx.YssTextBox txtClsPortName;
        private Yss.KTable.Models.Row row1;
        private Yss.KTable.Models.Row row2;
        private Yss.KTable.Models.Row row3;
        private Yss.KTable.Models.Row row4;
        private Yss.KTable.Models.Row row5;
        private Yss.KTable.Models.Row row6;
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Cell cell2;
        private Yss.KTable.Models.Cell cell3;
        private Yss.KTable.Models.Cell cell4;
        private Yss.KTable.Models.Cell cell5;
        private Yss.KTable.Models.Cell cell6;
        private Yss.KTable.Models.Cell cell7;
        private Yss.KTable.Models.Cell cell18;
        private Yss.KTable.Models.Cell cell19;
        private Yss.KTable.Models.Cell cell20;
        private Yss.KTable.Models.Cell cell21;
        private Yss.KTable.Models.Cell cell22;
        private Yss.KTable.Models.Cell cell23;
        private Yss.KTable.Models.Cell cell24;
        private Yss.KTable.Models.Cell cell25;
        private Yss.KTable.Models.Cell cell26;
        private Yss.KTable.Models.Cell cell27;
        private FAST.Core.BaseControl.YssSelCombox cboClassGrade;
        private FAST.Core.BaseControl.YssSelCombox cboClassType;
        private FAST.Core.BaseControl.YssSelCombox cboNetFormula;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpExpiryDate;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpFoundDate;
        private FAST.Core.BaseControl.YssSelCombox selDvPortCls;
        private FAST.Core.BaseControl.YssSelCombox selDtCode;
        private Yss.KTable.Models.Row row11;
        private Yss.KTable.Models.Cell cell28;
        private Yss.KTable.Models.Cell cell29;
        private Yss.KTable.Models.Cell cell30;
        private Yss.KTable.Models.Cell cell31;
        private Yss.KTable.Models.Cell cell32;
        private Yss.KTable.Models.Row row12;
        private Yss.KTable.Models.Row row13;
        private Yss.KRichEx.YssTextBox txtClsPortCode;
        private FAST.Core.BaseControl.YssSelCombox isNetting;
        private Yss.KTable.Models.Row row7;
        private Yss.KTable.Models.Cell cell8;
        private Yss.KTable.Models.Cell cell9;
        private Yss.KTable.Models.Cell cell10;
        private Yss.KTable.Models.Cell cell11;
        private Yss.KTable.Models.Cell cell12;
        private Yss.KTable.Models.Cell cell13;
        private Yss.KTable.Models.Cell cell14;
        private Yss.KTable.Models.Cell cell15;
        private Yss.KTable.Models.Cell cell16;
        private Yss.KTable.Models.Cell cell17;
        private Yss.KTable.Models.Cell cell33;
        private Yss.KTable.Models.Cell cell34;
        private Yss.KTable.Models.Cell cell35;
        private Yss.KTable.Models.Cell cell36;
        private Yss.KTable.Models.Cell cell37;
        private FAST.Core.BaseControl.YssSelCombox cboPortP;
        private FAST.Core.BaseControl.YssSelCombox selSyfp;
        private Yss.KTable.Models.Row row8;
        private Yss.KTable.Models.Cell cell38;
        private Yss.KTable.Models.Cell cell39;
        private Yss.KTable.Models.Cell cell40;
        private Yss.KTable.Models.Cell cell41;
        private Yss.KTable.Models.Cell cell42;
        private Yss.KTable.Models.Row row9;
        private Yss.KTable.Models.Row row10;
        private Yss.KTable.Models.Row row14;
        private Yss.KTable.Models.Row row15;
        private Yss.KTable.Models.Cell cell43;
        private Yss.KTable.Models.Cell cell44;
        private Yss.KTable.Models.Cell cell45;
        private Yss.KTable.Models.Cell cell46;
        private Yss.KTable.Models.Cell cell47;
        private Yss.KRichEx.ImprovedTextBox txtYield;
        private Yss.KTable.Models.Row row16;
        private Yss.KTable.Models.Cell cell48;
        private Yss.KTable.Models.Cell cell49;
        private FAST.Core.BaseControl.YssSelCombox SelRateType;
        protected FAST.Core.BaseControl.YssSelCombox selRatioFormula;
        protected FAST.Core.BaseControl.YssSelCombox cboRateFormula;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpLiquid;
        private Yss.KTable.Models.Row row17;
        private Yss.KTable.Models.Cell cell51;
        private Yss.KTable.Models.Cell cell50;
        private Yss.KTable.Models.Cell cell52;
        private Yss.KTable.Models.Cell cell53;
        private Yss.KTable.Models.Cell cell54;
        protected Yss.Controls.ExpandableSplitter splitRight;
        private Yss.Controls.PanelEx pnlRight;
        private Yss.KNavigation.NavigateBar navBarRight;
        public Yss.KNavigation.NavigateItem navigateItemMain;
        public Yss.KTable.Models.Table tbRightMain;
        protected Yss.Controls.PanelEx pnlBarPort;
        public YssDevComponents.DotNetBar.Bar barPort;
        private YssDevComponents.DotNetBar.CheckBoxItem chkCheckAll;
        private YssDevComponents.DotNetBar.CheckBoxItem chkBoxCheckedRowsCount;
        private Yss.KTable.Models.Table tbRightFilter;
        private FAST.Core.BaseControl.YssSelCombox cboPortCode;
        private Yss.KTable.Models.Cell cell55;
        private Yss.KTable.Models.Row row18;
        private Yss.KTable.Models.Row row19;
        private Yss.KTable.Models.Cell cell56;
        private Yss.KTable.Models.Column column6;
        private Yss.KTable.Models.Column column7;
        private Yss.KRichEx.TailTextBox cboPort;
        private FAST.Core.BaseControl.YssSelCombox cboXyPj;
        private Yss.KTable.Models.Cell cell59;
        private Yss.KTable.Models.Cell cell57;
        private Yss.KTable.Models.Cell cell58;
    }
}