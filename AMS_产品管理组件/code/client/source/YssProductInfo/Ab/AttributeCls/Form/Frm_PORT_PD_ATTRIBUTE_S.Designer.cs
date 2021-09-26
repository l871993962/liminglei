using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;

using FAST.Core.Context;

using FAST.Core.Resource;
namespace YssProductInfo.Ab.AttributeCls.Form
{
    partial class Frm_PORT_PD_ATTRIBUTE_S
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
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo6 = new FAST.Core.BaseControl.ControlMethodInfo();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_PORT_PD_ATTRIBUTE_S));
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo1 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo2 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo3 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo4 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo5 = new FAST.Core.BaseControl.ControlMethodInfo();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.cell16 = new Yss.KTable.Models.Cell();
            this.cell17 = new Yss.KTable.Models.Cell();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.row5 = new Yss.KTable.Models.Row();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.cell13 = new Yss.KTable.Models.Cell();
            this.cell14 = new Yss.KTable.Models.Cell();
            this.cell15 = new Yss.KTable.Models.Cell();
            this.tbTop = new Yss.KTable.Models.Table(this.components);
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.cmbGroups = new FAST.Core.BaseControl.GroupTextBox();
            this.cell19 = new Yss.KTable.Models.Cell();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.row3 = new Yss.KTable.Models.Row();
            this.cell18 = new Yss.KTable.Models.Cell();
            this.checkBoxCol = new Yss.KTable.Models.CheckBoxColumn();
            this.markCol = new Yss.KTable.Models.MarkColumn();
            this.colAssetsType = new Yss.KTable.Models.Column();
            this.colOperType = new Yss.KTable.Models.Column();
            this.colCollectCode = new Yss.KTable.Models.Column();
            this.colInvestCode = new Yss.KTable.Models.Column();
            this.colAssetsCode = new Yss.KTable.Models.Column();
            this.colSettMode = new Yss.KTable.Models.Column();
            this.colPortCode = new Yss.KTable.Models.Column();
            this.colDesc = new Yss.KTable.Models.Column();
            this.pnlCtrlsBack = new System.Windows.Forms.Panel();
            this.cbcttgAccount = new Yss.KRichEx.YssTextBox();
            this.zjhjctxt = new Yss.KRichEx.YssTextBox();
            this.cboPortType = new FAST.Core.BaseControl.YssSelCombox();
            this.cboClientType = new FAST.Core.BaseControl.YssSelCombox();
            this.cboAssetsCode = new FAST.Core.BaseControl.YssSelCombox();
            this.cboSettMode = new FAST.Core.BaseControl.YssSelCombox();
            this.cboInvestCode = new FAST.Core.BaseControl.YssSelCombox();
            this.cboCollectCode = new FAST.Core.BaseControl.YssSelCombox();
            this.cboOperType = new FAST.Core.BaseControl.YssSelCombox();
            this.cboMxAssetsType = new FAST.Core.BaseControl.YssSelCombox();
            this.txtContractName = new Yss.KRichEx.YssTextBox();
            this.txtPdNum = new Yss.KRichEx.YssTextBox();
            this.txtShortNum = new Yss.KRichEx.YssTextBox();
            this.txtDesc = new Yss.KRichEx.YssTextBox();
            this.colAssetsTypeCode = new Yss.KTable.Models.Column();
            this.iden = new Yss.KTable.Models.Column();
            this.colPortName = new Yss.KTable.Models.Column();
            this.colClientType = new Yss.KTable.Models.Column();
            this.colPdNum = new Yss.KTable.Models.Column();
            this.colShortNum = new Yss.KTable.Models.Column();
            this.colContractName = new Yss.KTable.Models.Column();
            this.colPortType = new Yss.KTable.Models.Column();
            this.colttAccount = new Yss.KTable.Models.Column();
            this.colMxAssetsType = new Yss.KTable.Models.Column();
            this.zjhjc = new Yss.KTable.Models.Column();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.tbTop.SuspendLayout();
            this.pnlCtrlsBack.SuspendLayout();
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
            this.tbMain.Border.Top = true;
            this.tbMain.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.checkBoxCol,
            this.markCol,
            this.colPortName,
            this.colAssetsType,
            this.colMxAssetsType,
            this.colOperType,
            this.colCollectCode,
            this.colClientType,
            this.colInvestCode,
            this.colAssetsCode,
            this.colSettMode,
            this.colPortType,
            this.colPdNum,
            this.colShortNum,
            this.colContractName,
            this.colttAccount,
            this.zjhjc,
            this.colDesc,
            this.colPortCode,
            this.colAssetsTypeCode,
            this.iden});
            this.tbMain.Location = new System.Drawing.Point(0, 103);
            this.tbMain.SelectionMode = System.Windows.Forms.SelectionMode.One;
            this.tbMain.Size = new System.Drawing.Size(1111, 447);
            this.tbMain.SelectedCellChanged += new Yss.KTable.Events.CellEventHandler(this.tbMain_SelectedCellChanged);
            this.tbMain.CellMouseClick += new Yss.KTable.Events.CellEventHandler(this.tbMain_CellMouseClick);
            // 
            // btnBar
            // 
            this.btnBar.Size = new System.Drawing.Size(1111, 30);
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
            this.stBarBottom.Location = new System.Drawing.Point(0, 588);
            this.stBarBottom.Size = new System.Drawing.Size(1111, 25);
            // 
            // pnlMain
            // 
            this.pnlMain.Controls.Add(this.pnlCtrlsBack);
            this.pnlMain.Controls.Add(this.tbTop);
            this.pnlMain.Size = new System.Drawing.Size(1111, 613);
            //this.pnlMain.Style.Alignment = System.Drawing.StringAlignment.Center;
            //this.pnlMain.Style.BackColor1.Color = System.Drawing.Color.White;
            //this.pnlMain.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            //this.pnlMain.Style.BorderColor.Color = System.Drawing.Color.FromArgb(((int)(((byte)(121)))), ((int)(((byte)(173)))), ((int)(((byte)(225)))));
            //this.pnlMain.Style.BorderSide = YssDevComponents.DotNetBar.eBorderSide.Top;
            //this.pnlMain.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            //this.pnlMain.Style.GradientAngle = 90;
            this.pnlMain.Controls.SetChildIndex(this.stBarBottom, 0);
            this.pnlMain.Controls.SetChildIndex(this.btnBar, 0);
            this.pnlMain.Controls.SetChildIndex(this.tbTop, 0);
            this.pnlMain.Controls.SetChildIndex(this.pnlCtrlsBack, 0);
            this.pnlMain.Controls.SetChildIndex(this.tbMain, 0);
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
            this.yssPanel1.Size = new System.Drawing.Size(1111, 613);
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.Text = "   运作类型：";
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell8
            // 
            this.cell8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell8.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell9
            // 
            this.cell9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell9.Text = "募集对象：";
            // 
            // cell10
            // 
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // row5
            // 
            this.row5.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell11,
            this.cell12,
            this.cell13,
            this.cell14,
            this.cell15});
            this.row5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row5.ForeColor = System.Drawing.Color.Empty;
            this.row5.FullRowSelected = false;
            this.row5.Text = null;
            // 
            // cell11
            // 
            this.cell11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell11.Text = "   投资对象：";
            // 
            // cell12
            // 
            this.cell12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell12.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell13
            // 
            this.cell13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell13.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell14
            // 
            this.cell14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell14.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell14.Text = "资产种类：";
            // 
            // cell15
            // 
            this.cell15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell15.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // tbTop
            // 
            this.tbTop.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(243)))), ((int)(((byte)(245)))), ((int)(((byte)(249)))));
            // 
            // 
            // 
            this.tbTop.Border.BorderColor = System.Drawing.Color.White;
            this.tbTop.Border.Bottom = false;
            this.tbTop.Border.Left = false;
            this.tbTop.Border.Right = false;
            this.tbTop.Border.Top = false;
            this.tbTop.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.tbTop.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.column1,
            this.column2});
            this.tbTop.Controls.Add(this.cmbGroups);
            this.tbTop.DefaultToolStripItems = Yss.KTable.Enums.SysToolStripItems.None;
            this.tbTop.Dock = System.Windows.Forms.DockStyle.Top;
            this.tbTop.EditCells = true;
            this.tbTop.GridLineColor = System.Drawing.Color.Empty;
            this.tbTop.IsShowScrollbar = false;
            this.tbTop.Location = new System.Drawing.Point(0, 30);
            this.tbTop.Name = "tbTop";
            this.tbTop.PlusMinusLineColor = System.Drawing.SystemColors.InactiveBorder;
            this.tbTop.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IndentOnly;
            this.tbTop.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3});
            this.tbTop.SelectionColor = System.Drawing.Color.Empty;
            this.tbTop.ShowColumnHeader = false;
            this.tbTop.Size = new System.Drawing.Size(1111, 73);
            this.tbTop.TabIndex = 17;
            this.tbTop.Text = "table1";
            // 
            // column1
            // 
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.Width = 88;
            // 
            // column2
            // 
            this.column2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column2.Width = 780;
            // 
            // cmbGroups
            // 
            this.cmbGroups.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cmbGroups.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cmbGroups.Border.Bottom = true;
            this.cmbGroups.Border.Left = true;
            this.cmbGroups.Border.Right = true;
            this.cmbGroups.Border.Top = true;
            this.cmbGroups.DisplayName = "C_PORT_NAME";
            this.cmbGroups.DisplayValue = "C_PORT_CODE";
            this.cmbGroups.EnterOnTailClick = false;
            this.cmbGroups.Location = new System.Drawing.Point(88, 43);
            this.cmbGroups.Margin = new System.Windows.Forms.Padding(0);
            this.cmbGroups.Name = "cmbGroups";
            this.cmbGroups.Parameter = "C_PORT_CODE;组合代码~C_PORT_NAME;组合名称";
            this.cmbGroups.ParentNodeAvailablePermission = false;
            this.cmbGroups.PrefixForeColor = System.Drawing.SystemColors.WindowText;
            this.cmbGroups.ShowCheckBox = true;
            this.cmbGroups.ShowColumnHeader = true;
            this.cmbGroups.Size = new System.Drawing.Size(779, 21);
            this.cmbGroups.SortColumn = "C_PORT_NAME_ST";
            this.cmbGroups.SufixForeColor = System.Drawing.SystemColors.WindowText;
            this.cmbGroups.TabIndex = 3;
            this.cmbGroups.Tag = this.cell19;
            this.cmbGroups.YssCaption = "组合信息";
            this.cmbGroups.YssIsMust = true;
            this.cmbGroups.ValueChanged += new System.EventHandler(this.cmbGroups_SelectedValueChanged);
            // 
            // cell19
            // 
            this.cell19.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell19.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell19.InnerControl = this.cmbGroups;
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row1.Height = 33;
            this.row1.IsGroup = true;
            this.row1.Text = " 基本信息：";
            // 
            // row2
            // 
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row2.Height = 10;
            this.row2.Text = null;
            // 
            // row3
            // 
            this.row3.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell18,
            this.cell19});
            this.row3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row3.Text = null;
            // 
            // cell18
            // 
            this.cell18.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell18.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell18.Text = "   投资组合：";
            // 
            // checkBoxCol
            // 
            this.checkBoxCol.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.checkBoxCol.Width = 30;
            // 
            // markCol
            // 
            this.markCol.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.markCol.ForeColor = System.Drawing.Color.Red;
            // 
            // colAssetsType
            // 
            this.colAssetsType.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.colAssetsType.Text = "资产类型";
            this.colAssetsType.Width = 95;
            // 
            // colMxAssetsType
            // 
            this.colMxAssetsType.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.colMxAssetsType.Text = "明细资产类型";
            this.colMxAssetsType.Width = 95;
            // 
            // colOperType
            // 
            this.colOperType.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.colOperType.Text = "运作类型";
            this.colOperType.Width = 95;
            // 
            // colCollectCode
            // 
            this.colCollectCode.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.colCollectCode.Text = "募集对象";
            this.colCollectCode.Width = 95;
            // 
            // colInvestCode
            // 
            this.colInvestCode.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.colInvestCode.Text = "投资对象";
            this.colInvestCode.Width = 95;
            // 
            // colAssetsCode
            // 
            this.colAssetsCode.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.colAssetsCode.Text = "资产种类";
            this.colAssetsCode.Width = 95;
            // 
            // colSettMode
            // 
            this.colSettMode.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.colSettMode.Text = "证券结算模式";
            this.colSettMode.Width = 95;
            // 
            // colPortCode
            // 
            this.colPortCode.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.colPortCode.Text = "投资组合代码";
            this.colPortCode.Visible = false;
            this.colPortCode.Width = 100;
            // 
            // colDesc
            // 
            this.colDesc.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.colDesc.Text = "描述";
            this.colDesc.Width = 100;
            // 
            // pnlCtrlsBack
            // 
            this.pnlCtrlsBack.BackColor = System.Drawing.Color.WhiteSmoke;
            this.pnlCtrlsBack.Controls.Add(this.cbcttgAccount);
            this.pnlCtrlsBack.Controls.Add(this.zjhjctxt);
            this.pnlCtrlsBack.Controls.Add(this.cboPortType);
            this.pnlCtrlsBack.Controls.Add(this.cboClientType);
            this.pnlCtrlsBack.Controls.Add(this.cboAssetsCode);
            this.pnlCtrlsBack.Controls.Add(this.cboSettMode);
            this.pnlCtrlsBack.Controls.Add(this.cboInvestCode);
            this.pnlCtrlsBack.Controls.Add(this.cboCollectCode);
            this.pnlCtrlsBack.Controls.Add(this.cboOperType);
            this.pnlCtrlsBack.Controls.Add(this.cboMxAssetsType);
            this.pnlCtrlsBack.Controls.Add(this.txtContractName);
            this.pnlCtrlsBack.Controls.Add(this.txtPdNum);
            this.pnlCtrlsBack.Controls.Add(this.txtShortNum);
            this.pnlCtrlsBack.Controls.Add(this.txtDesc);
            this.pnlCtrlsBack.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.pnlCtrlsBack.Location = new System.Drawing.Point(0, 550);
            this.pnlCtrlsBack.Name = "pnlCtrlsBack";
            this.pnlCtrlsBack.Size = new System.Drawing.Size(1111, 38);
            this.pnlCtrlsBack.TabIndex = 28;
            this.pnlCtrlsBack.Tag = "这些是备选控件列表";
            this.pnlCtrlsBack.Visible = false;
            // 
            // cbcttgAccount
            // 
            this.cbcttgAccount.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cbcttgAccount.Border.Class = "TextBoxBorder";
            this.cbcttgAccount.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.cbcttgAccount.ForeColor = System.Drawing.Color.Blue;
            this.cbcttgAccount.Location = new System.Drawing.Point(496, 9);
            this.cbcttgAccount.Name = "cbcttgAccount";
            this.cbcttgAccount.Size = new System.Drawing.Size(118, 21);
            this.cbcttgAccount.TabIndex = 39;
            this.cbcttgAccount.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.cbcttgAccount.YssCaption = "次托托管账户";
            this.cbcttgAccount.YssLength = 50;
            // 
            // 
            // zjhjctxt
            // 
            this.zjhjctxt.BackColor = System.Drawing.Color.White;
            this.zjhjctxt.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.zjhjctxt.ForeColor = System.Drawing.Color.Blue;
            this.zjhjctxt.Location = new System.Drawing.Point(496, 9);
            this.zjhjctxt.Name = "zjhjctxt";
            this.zjhjctxt.Size = new System.Drawing.Size(118, 21);
            this.zjhjctxt.TabIndex = 39;
            this.zjhjctxt.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.zjhjctxt.YssCaption = "证监会简称";
            this.zjhjctxt.YssLength = 50;
            // 
            // cboPortType
            // 
            this.cboPortType.AddedSelItemName = "";
            this.cboPortType.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboPortType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboPortType.Border.Bottom = true;
            this.cboPortType.Border.Left = true;
            this.cboPortType.Border.Right = true;
            this.cboPortType.Border.Top = true;
            this.cboPortType.ClassName = "";
            this.cboPortType.DllName = "YssControls.dll";
            this.cboPortType.FilterCond = "";
            this.cboPortType.IsFillDecimal = false;
            this.cboPortType.Location = new System.Drawing.Point(671, 6);
            this.cboPortType.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo6.MethodName = "getDataListByTypes";
            controlMethodInfo6.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo6.MethodParams")));
            controlMethodInfo6.MethodParamValues = new string[] {
        "PT_TYPE,"};
            controlMethodInfo6.Methods = null;
            this.cboPortType.MethodInfo = controlMethodInfo6;
            this.cboPortType.Name = "cboPortType";
            this.cboPortType.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconOnly;
            this.cboPortType.QueryCond = "ISSUE_MODE";
            this.cboPortType.QueryType = "CacheType";
            this.cboPortType.Size = new System.Drawing.Size(121, 21);
            this.cboPortType.TabIndex = 38;
            this.cboPortType.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboPortType.YssCaption = "组合类别";
            // 
            // cboClientType
            // 
            this.cboClientType.AddedSelItemName = "";
            this.cboClientType.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboClientType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboClientType.Border.Bottom = true;
            this.cboClientType.Border.Left = true;
            this.cboClientType.Border.Right = true;
            this.cboClientType.Border.Top = true;
            this.cboClientType.ClassName = "";
            this.cboClientType.DllName = "YssControls.dll";
            this.cboClientType.FilterCond = "";
            this.cboClientType.IsFillDecimal = false;
            this.cboClientType.Location = new System.Drawing.Point(535, 6);
            this.cboClientType.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataListByTypes";
            controlMethodInfo1.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo1.MethodParams")));
            controlMethodInfo1.MethodParamValues = new string[] {
        "CLIENT_TYPE,"};
            controlMethodInfo1.Methods = null;
            this.cboClientType.MethodInfo = controlMethodInfo1;
            this.cboClientType.Name = "cboClientType";
            this.cboClientType.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconOnly;
            this.cboClientType.QueryCond = "ISSUE_MODE";
            this.cboClientType.QueryType = "CacheType";
            this.cboClientType.Size = new System.Drawing.Size(121, 21);
            this.cboClientType.TabIndex = 37;
            this.cboClientType.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboClientType.YssCaption = "募集对象";
            // 
            // cboAssetsCode
            // 
            this.cboAssetsCode.AddedSelItemName = "";
            this.cboAssetsCode.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboAssetsCode.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboAssetsCode.Border.Bottom = true;
            this.cboAssetsCode.Border.Left = true;
            this.cboAssetsCode.Border.Right = true;
            this.cboAssetsCode.Border.Top = true;
            this.cboAssetsCode.ClassName = "";
            this.cboAssetsCode.DllName = "YssControls.dll";
            this.cboAssetsCode.FilterCond = "";
            this.cboAssetsCode.IsFillDecimal = false;
            this.cboAssetsCode.Location = new System.Drawing.Point(402, 6);
            this.cboAssetsCode.Margin = new System.Windows.Forms.Padding(0);
            this.cboAssetsCode.MethodInfo = null;
            this.cboAssetsCode.Name = "cboAssetsCode";
            this.cboAssetsCode.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconOnly;
            this.cboAssetsCode.QueryCond = "ISSUE_MODE";
            this.cboAssetsCode.QueryType = "CacheType";
            this.cboAssetsCode.Size = new System.Drawing.Size(121, 21);
            this.cboAssetsCode.TabIndex = 36;
            this.cboAssetsCode.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboAssetsCode.YssCaption = "资产种类";
            this.cboAssetsCode.BeforeDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(this.cboAssetsCode_BeforeDropDownClick);
            // 
            // cboSettMode
            // 
            this.cboSettMode.AddedSelItemName = "";
            this.cboSettMode.BackColor = System.Drawing.Color.White;
            this.cboSettMode.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboSettMode.Border.Bottom = true;
            this.cboSettMode.Border.Left = true;
            this.cboSettMode.Border.Right = true;
            this.cboSettMode.Border.Top = true;
            this.cboSettMode.ClassName = "";
            this.cboSettMode.DllName = "YssControls.dll";
            this.cboSettMode.FilterCond = "";
            this.cboSettMode.IsFillDecimal = false;
            this.cboSettMode.Location = new System.Drawing.Point(402, 6);
            this.cboSettMode.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo5.MethodName = "getDataListByTypes";
            controlMethodInfo5.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo5.MethodParams")));
            controlMethodInfo5.MethodParamValues = new string[] {
        "DJPX_MS,"};
            controlMethodInfo5.Methods = null;
            this.cboSettMode.MethodInfo = controlMethodInfo5;
            this.cboSettMode.Name = "cboSettMode";
            this.cboSettMode.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconOnly;
            this.cboSettMode.QueryCond = "ISSUE_MODE";
            this.cboSettMode.QueryType = "CacheType";
            this.cboSettMode.Size = new System.Drawing.Size(121, 21);
            this.cboSettMode.TabIndex = 36;
            this.cboSettMode.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboSettMode.YssCaption = "证券结算模式";
            // 
            // cboInvestCode
            // 
            this.cboInvestCode.AddedSelItemName = "";
            this.cboInvestCode.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboInvestCode.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboInvestCode.Border.Bottom = true;
            this.cboInvestCode.Border.Left = true;
            this.cboInvestCode.Border.Right = true;
            this.cboInvestCode.Border.Top = true;
            this.cboInvestCode.ClassName = "";
            this.cboInvestCode.DllName = "YssControls.dll";
            this.cboInvestCode.FilterCond = "";
            this.cboInvestCode.IsFillDecimal = false;
            this.cboInvestCode.Location = new System.Drawing.Point(269, 6);
            this.cboInvestCode.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo2.MethodName = "getDataListByTypes";
            controlMethodInfo2.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo2.MethodParams")));
            controlMethodInfo2.MethodParamValues = new string[] {
        "INV_ZCGLJH,"};
            controlMethodInfo2.Methods = null;
            this.cboInvestCode.MethodInfo = controlMethodInfo2;
            this.cboInvestCode.Name = "cboInvestCode";
            this.cboInvestCode.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconOnly;
            this.cboInvestCode.QueryCond = "ISSUE_MODE";
            this.cboInvestCode.QueryType = "CacheType";
            this.cboInvestCode.Size = new System.Drawing.Size(121, 21);
            this.cboInvestCode.TabIndex = 35;
            this.cboInvestCode.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboInvestCode.YssCaption = "投资对象";
            this.cboInvestCode.BeforeDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(this.cboInvestCode_BeforeDropDownClick);
            this.cboInvestCode.SelectedValueChanged += new System.EventHandler(this.cboInvestCode_SelectedValueChanged);
            // 
            // cboCollectCode
            // 
            this.cboCollectCode.AddedSelItemName = "";
            this.cboCollectCode.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboCollectCode.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboCollectCode.Border.Bottom = true;
            this.cboCollectCode.Border.Left = true;
            this.cboCollectCode.Border.Right = true;
            this.cboCollectCode.Border.Top = true;
            this.cboCollectCode.ClassName = "";
            this.cboCollectCode.DllName = "YssControls.dll";
            this.cboCollectCode.FilterCond = "";
            this.cboCollectCode.IsFillDecimal = false;
            this.cboCollectCode.Location = new System.Drawing.Point(139, 6);
            this.cboCollectCode.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo3.MethodName = "getDataListByTypes";
            controlMethodInfo3.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo3.MethodParams")));
            controlMethodInfo3.MethodParamValues = new string[] {
        "COL_OBJ,"};
            controlMethodInfo3.Methods = null;
            this.cboCollectCode.MethodInfo = controlMethodInfo3;
            this.cboCollectCode.Name = "cboCollectCode";
            this.cboCollectCode.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconOnly;
            this.cboCollectCode.QueryCond = "ISSUE_MODE";
            this.cboCollectCode.QueryType = "CacheType";
            this.cboCollectCode.Size = new System.Drawing.Size(121, 21);
            this.cboCollectCode.TabIndex = 34;
            this.cboCollectCode.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboCollectCode.YssCaption = "募集对象";
            // 
            // cboOperType
            // 
            this.cboOperType.AddedSelItemName = "";
            this.cboOperType.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboOperType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboOperType.Border.Bottom = true;
            this.cboOperType.Border.Left = true;
            this.cboOperType.Border.Right = true;
            this.cboOperType.Border.Top = true;
            this.cboOperType.ClassName = "";
            this.cboOperType.DllName = "YssControls.dll";
            this.cboOperType.FilterCond = "";
            this.cboOperType.IsFillDecimal = false;
            this.cboOperType.Location = new System.Drawing.Point(9, 6);
            this.cboOperType.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo4.MethodName = "getDataListByTypes";
            controlMethodInfo4.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo4.MethodParams")));
            controlMethodInfo4.MethodParamValues = new string[] {
        "OPER,"};
            controlMethodInfo4.Methods = null;
            this.cboOperType.MethodInfo = controlMethodInfo4;
            this.cboOperType.Name = "cboOperType";
            this.cboOperType.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconOnly;
            this.cboOperType.QueryCond = "ISSUE_MODE";
            this.cboOperType.QueryType = "CacheType";
            this.cboOperType.Size = new System.Drawing.Size(121, 21);
            this.cboOperType.TabIndex = 33;
            this.cboOperType.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboOperType.YssCaption = "运作类型";
            // 
            // cboMxAssetsType
            // 
            this.cboMxAssetsType.AddedSelItemName = "";
            this.cboMxAssetsType.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboMxAssetsType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboMxAssetsType.Border.Bottom = true;
            this.cboMxAssetsType.Border.Left = true;
            this.cboMxAssetsType.Border.Right = true;
            this.cboMxAssetsType.Border.Top = true;
            this.cboMxAssetsType.ClassName = "";
            this.cboMxAssetsType.DllName = "YssControls.dll";
            this.cboMxAssetsType.FilterCond = "";
            this.cboMxAssetsType.IsFillDecimal = false;
            this.cboMxAssetsType.Location = new System.Drawing.Point(535, 6);
            this.cboMxAssetsType.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo6.MethodName = "getDataListByTypes";
            controlMethodInfo6.MethodParams = null;
            controlMethodInfo6.MethodParamValues = new string[] {
        "DAS,"};
            controlMethodInfo6.Methods = null;
            this.cboMxAssetsType.MethodInfo = controlMethodInfo6;
            this.cboMxAssetsType.Name = "cboMxAssetsType";
            this.cboMxAssetsType.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconOnly;
            this.cboMxAssetsType.QueryCond = "ISSUE_MODE";
            this.cboMxAssetsType.QueryType = "CacheType";
            this.cboMxAssetsType.Size = new System.Drawing.Size(121, 21);
            this.cboMxAssetsType.TabIndex = 37;
            this.cboMxAssetsType.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboMxAssetsType.YssCaption = "明细资产类型";
            // 
            // txtContractName
            // 
            this.txtContractName.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.txtContractName.Border.Class = "TextBoxBorder";
            this.txtContractName.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.txtContractName.ForeColor = System.Drawing.Color.Blue;
            this.txtContractName.Location = new System.Drawing.Point(919, 6);
            this.txtContractName.Name = "txtContractName";
            this.txtContractName.Size = new System.Drawing.Size(118, 21);
            this.txtContractName.TabIndex = 21;
            this.txtContractName.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtContractName.YssCaption = "合同名称";
            this.txtContractName.YssLength = 100;
            this.txtContractName.TextChanged += new System.EventHandler(this.txtDesc_TextChanged);
            // 
            // txtPdNum
            // 
            this.txtPdNum.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.txtPdNum.Border.Class = "TextBoxBorder";
            this.txtPdNum.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.txtPdNum.ForeColor = System.Drawing.Color.Blue;
            this.txtPdNum.Location = new System.Drawing.Point(795, 6);
            this.txtPdNum.Name = "txtPdNum";
            this.txtPdNum.Size = new System.Drawing.Size(118, 21);
            this.txtPdNum.TabIndex = 21;
            this.txtPdNum.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtPdNum.YssCaption = "产品编号";
            this.txtPdNum.YssLength = 100;
            this.txtPdNum.TextChanged += new System.EventHandler(this.txtDesc_TextChanged);
            //
            // txtShortNum
            // 
            this.txtShortNum.BackColor = System.Drawing.Color.White;
            this.txtShortNum.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.txtShortNum.ForeColor = System.Drawing.Color.Blue;
            this.txtShortNum.Location = new System.Drawing.Point(795, 6);
            this.txtShortNum.Name = "txtShortNum";
            this.txtShortNum.Size = new System.Drawing.Size(118, 21);
            this.txtShortNum.TabIndex = 21;
            this.txtShortNum.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtShortNum.YssCaption = "短编码";
            this.txtShortNum.YssLength = 100;
            this.txtShortNum.TextChanged += new System.EventHandler(this.txtDesc_TextChanged);
            // 
            // txtDesc
            // 
            this.txtDesc.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.txtDesc.Border.Class = "TextBoxBorder";
            this.txtDesc.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.txtDesc.ForeColor = System.Drawing.Color.Blue;
            this.txtDesc.Location = new System.Drawing.Point(1043, 6);
            this.txtDesc.Name = "txtDesc";
            this.txtDesc.Size = new System.Drawing.Size(118, 21);
            this.txtDesc.TabIndex = 21;
            this.txtDesc.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtDesc.YssCaption = "描述";
            this.txtDesc.YssLength = 100;
            this.txtDesc.TextChanged += new System.EventHandler(this.txtDesc_TextChanged);
            // 
            // colAssetsTypeCode
            // 
            this.colAssetsTypeCode.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.colAssetsTypeCode.Visible = false;
            this.colAssetsTypeCode.Width = 5;
            // 
            // iden
            // 
            this.iden.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.iden.Visible = false;
            this.iden.Width = 8;
            // 
            // colPortName
            // 
            this.colPortName.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.colPortName.Text = "投资组合";
            this.colPortName.Width = 95;
            // 
            // colClientType
            // 
            this.colClientType.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.colClientType.Text = "客户类型";
            this.colClientType.Width = 95;
            // 
            // colPdNum
            // 
            this.colPdNum.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.colPdNum.Text = "产品编号";
            this.colPdNum.Width = 100;
            // 
            // colContractName
            // 
            this.colContractName.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.colContractName.Text = "合同名称";
            this.colContractName.Width = 100;
            // 
            // colPortType
            // 
            this.colPortType.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.colPortType.Text = "组合类别";
            this.colPortType.Width = 95;
            // 
            // colMxAssetsType
            // 
            this.colMxAssetsType.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.colMxAssetsType.Text = "明细资产类型";
            this.colMxAssetsType.Width = 95;
            // 
            // colttAccount
            // 
            this.colttAccount.CellTextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.colttAccount.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.colttAccount.Text = "次托托管账户";
            this.colttAccount.Width = 95;
            // 
            // zjhjc
            // 
            this.zjhjc.CellTextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.zjhjc.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.zjhjc.Text = "证监会简称";
            this.zjhjc.Width = 95;
            // 
            // colShortNum
            // 
            this.colShortNum.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.colShortNum.Text = "短编码";
            this.colShortNum.Width = 100;
            // 
            // Frm_PORT_PD_ATTRIBUTE_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1111, 613);
            this.DoubleBuffered = true;
            this.Name = "Frm_PORT_PD_ATTRIBUTE_S";
            this.Text = "组合产品属性";
            this.YssSetStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.tbTop.ResumeLayout(false);
            this.pnlCtrlsBack.ResumeLayout(false);
            this.pnlCtrlsBack.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private Yss.KTable.Models.Row row5;
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Cell cell2;
        private Yss.KTable.Models.Cell cell3;
        private Yss.KTable.Models.Cell cell4;
        private Yss.KTable.Models.Cell cell5;
        private Yss.KTable.Models.Cell cell6;
        private Yss.KTable.Models.Cell cell7;
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
        private Yss.KTable.Models.Table tbTop;
        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
        private Yss.KTable.Models.Row row1;
        private Yss.KTable.Models.Row row2;
        private Yss.KTable.Models.Row row3;
        private Yss.KTable.Models.Cell cell18;
        private Yss.KTable.Models.Cell cell19;
        private FAST.Core.BaseControl.GroupTextBox cmbGroups;
        private Yss.KTable.Models.CheckBoxColumn checkBoxCol;
        private Yss.KTable.Models.MarkColumn markCol;
        private Yss.KTable.Models.Column colAssetsType;
        private Yss.KTable.Models.Column colOperType;
        private Yss.KTable.Models.Column colCollectCode;
        private Yss.KTable.Models.Column colInvestCode;
        private Yss.KTable.Models.Column colAssetsCode;
        private Yss.KTable.Models.Column colSettMode;
        private Yss.KTable.Models.Column colPortCode;
        private Yss.KTable.Models.Column colDesc;
        private System.Windows.Forms.Panel pnlCtrlsBack;
        private Yss.KRichEx.YssTextBox txtDesc;
        protected FAST.Core.BaseControl.YssSelCombox cboOperType;
        protected FAST.Core.BaseControl.YssSelCombox cboCollectCode;
        protected FAST.Core.BaseControl.YssSelCombox cboInvestCode;
        protected FAST.Core.BaseControl.YssSelCombox cboAssetsCode;
        protected FAST.Core.BaseControl.YssSelCombox cboSettMode;
        private Yss.KTable.Models.Column colAssetsTypeCode;
        private Yss.KTable.Models.Column iden;
        private Yss.KTable.Models.Column colPortName;
        protected FAST.Core.BaseControl.YssSelCombox cboClientType;
        private Yss.KTable.Models.Column colClientType;
        private Yss.KRichEx.YssTextBox txtPdNum;
        private Yss.KTable.Models.Column colPdNum;
        private Yss.KRichEx.YssTextBox txtShortNum;
        private Yss.KTable.Models.Column colContractName;
        private Yss.KRichEx.YssTextBox txtContractName;
        private Yss.KTable.Models.Column colPortType;
        protected FAST.Core.BaseControl.YssSelCombox cboPortType;
        private Yss.KTable.Models.Column colttAccount;
        private Yss.KRichEx.YssTextBox cbcttgAccount;
        private Yss.KTable.Models.Column colMxAssetsType;
        protected FAST.Core.BaseControl.YssSelCombox cboMxAssetsType;
        private Yss.KTable.Models.Column zjhjc;
        private Yss.KRichEx.YssTextBox zjhjctxt;
        private Yss.KTable.Models.Column colShortNum;
    }
}