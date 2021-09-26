using FAST.Core.BaseControl;
using FAST.Core.Context;
namespace YssSztTool.Form.Para
{
    partial class Frm_DZ_PARA_ORG_S
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
            ControlMethodInfo controlMethodInfo1 = new ControlMethodInfo();
            ControlMethodInfo controlMethodInfo2 = new ControlMethodInfo();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.table1 = new Yss.KTable.Models.Table(this.components);
            this.table2 = new Yss.KTable.Models.Table(this.components);
            this.row3 = new Yss.KTable.Models.Row();
            this.row4 = new Yss.KTable.Models.Row();
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.row5 = new Yss.KTable.Models.Row();
            this.row6 = new Yss.KTable.Models.Row();
            this.row7 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.cboTgh = new YssSelCombox();
            this.cboOrgnType = new YssSelCombox();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.row8 = new Yss.KTable.Models.Row();
            this.row9 = new Yss.KTable.Models.Row();
            this.row10 = new Yss.KTable.Models.Row();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.tbMain.SuspendLayout();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // tbMain
            // 
            // 
            // 
            // 
            this.tbMain.Border.BorderColor = System.Drawing.Color.Transparent;
            this.tbMain.Border.Bottom = true;
            this.tbMain.Border.Left = false;
            this.tbMain.Border.Right = false;
            this.tbMain.Border.Top = false;
            this.tbMain.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.column1,
            this.column2});
            this.tbMain.Controls.Add(this.cboTgh);
            this.tbMain.Controls.Add(this.cboOrgnType);
            this.tbMain.Dock = System.Windows.Forms.DockStyle.Left;
            this.tbMain.Location = new System.Drawing.Point(0, 31);
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row5,
            this.row6,
            this.row7,
            this.row8,
            this.row9,
            this.row10});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(256, 355);
            // 
            // btnBar
            // 
            this.btnBar.Location = new System.Drawing.Point(0, 1);
            this.btnBar.Size = new System.Drawing.Size(628, 30);
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
            this.stBarBottom.Location = new System.Drawing.Point(0, 386);
            this.stBarBottom.Size = new System.Drawing.Size(628, 25);
            // 
            // pnlMain
            // 
            this.pnlMain.Controls.Add(this.table1);
            this.pnlMain.Controls.Add(this.table2);
            this.pnlMain.Padding = new System.Windows.Forms.Padding(0, 1, 0, 0);
            this.pnlMain.Size = new System.Drawing.Size(628, 411);
            this.pnlMain.Controls.SetChildIndex(this.btnBar, 0);
            this.pnlMain.Controls.SetChildIndex(this.stBarBottom, 0);
            this.pnlMain.Controls.SetChildIndex(this.tbMain, 0);
            this.pnlMain.Controls.SetChildIndex(this.table2, 0);
            this.pnlMain.Controls.SetChildIndex(this.table1, 0);
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
            this.yssPanel1.Size = new System.Drawing.Size(628, 411);
            // 
            // hpAssist
            // 
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row1.Height = 33;
            this.row1.Text = null;
            // 
            // row2
            // 
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row2.Text = null;
            // 
            // table1
            // 
            this.table1.AllowColumnDrag = false;
            this.table1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(243)))), ((int)(((byte)(245)))), ((int)(((byte)(249)))));
            // 
            // 
            // 
            this.table1.Border.BorderColor = System.Drawing.Color.DarkGray;
            this.table1.Border.Bottom = true;
            this.table1.Border.Left = true;
            this.table1.Border.Right = true;
            this.table1.Border.Top = true;
            this.table1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.table1.ColumnHeight = 18;
            this.table1.DefaultToolStripItems = Yss.KTable.Enums.SysToolStripItems.None;
            this.table1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.table1.FullRowSelect = false;
            this.table1.GridLine = Yss.KTable.Enums.GridLines.Both;
            this.table1.GridLineColor = System.Drawing.Color.LightSteelBlue;
            this.table1.Location = new System.Drawing.Point(256, 76);
            this.table1.Margin = new System.Windows.Forms.Padding(0);
            this.table1.Name = "table1";
            this.table1.PlusMinusLineColor = System.Drawing.SystemColors.InactiveBorder;
            this.table1.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IndentOnly;
            this.table1.SelectionMode = System.Windows.Forms.SelectionMode.One;
            this.table1.Size = new System.Drawing.Size(372, 310);
            this.table1.TabIndex = 23;
            this.table1.Text = "table1";
            // 
            // table2
            // 
            this.table2.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(243)))), ((int)(((byte)(245)))), ((int)(((byte)(249)))));
            // 
            // 
            // 
            this.table2.Border.BorderColor = System.Drawing.Color.White;
            this.table2.Border.Bottom = false;
            this.table2.Border.Left = false;
            this.table2.Border.Right = false;
            this.table2.Border.Top = false;
            this.table2.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.table2.DefaultToolStripItems = ((Yss.KTable.Enums.SysToolStripItems)((((Yss.KTable.Enums.SysToolStripItems.ShowHideColumn | Yss.KTable.Enums.SysToolStripItems.GroupByColumn)
                        | Yss.KTable.Enums.SysToolStripItems.PrintSetup)
                        | Yss.KTable.Enums.SysToolStripItems.PrintPreview)));
            this.table2.Dock = System.Windows.Forms.DockStyle.Top;
            this.table2.GridLineColor = System.Drawing.Color.Empty;
            this.table2.Location = new System.Drawing.Point(256, 31);
            this.table2.Name = "table2";
            this.table2.PlusMinusLineColor = System.Drawing.SystemColors.InactiveBorder;
            this.table2.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IndentOnly;
            this.table2.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row3,
            this.row4});
            this.table2.SearchType = Yss.KTable.Models.SearchType.None;
            this.table2.ShowColumnHeader = false;
            this.table2.Size = new System.Drawing.Size(372, 45);
            this.table2.TabIndex = 24;
            this.table2.Text = "table2";
            // 
            // row3
            // 
            this.row3.Font = new System.Drawing.Font("新宋体", 9.75F, System.Drawing.FontStyle.Bold);
            this.row3.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row3.GroupPosition = 3;
            this.row3.Height = 33;
            this.row3.IsGroup = true;
            this.row3.Text = "基本信息";
            // 
            // row4
            // 
            this.row4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row4.Height = 10;
            this.row4.Text = null;
            // 
            // column1
            // 
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.Width = 110;
            // 
            // column2
            // 
            this.column2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column2.Width = 122;
            // 
            // row5
            // 
            this.row5.Font = new System.Drawing.Font("新宋体", 9.75F, System.Drawing.FontStyle.Bold);
            this.row5.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row5.FullRowSelected = false;
            this.row5.GroupPosition = 3;
            this.row5.Height = 33;
            this.row5.IsGroup = true;
            this.row5.Text = "机构信息";
            // 
            // row6
            // 
            this.row6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row6.FullRowSelected = false;
            this.row6.Height = 10;
            this.row6.Text = null;
            // 
            // row7
            // 
            this.row7.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell1,
            this.cell2});
            this.row7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row7.FullRowSelected = false;
            this.row7.Height = 25;
            this.row7.Text = null;
            // 
            // cell1
            // 
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.Text = "   机构名称：";
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.cboTgh;
            // 
            // cboTgh
            // 
            this.cboTgh.AddedSelItemName = "";
            this.cboTgh.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboTgh.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboTgh.Border.Bottom = true;
            this.cboTgh.Border.Left = true;
            this.cboTgh.Border.Right = true;
            this.cboTgh.Border.Top = true;
            this.cboTgh.ClassName = "YssControls.YssSelCombox";
            this.cboTgh.DisplayName = "C_ORG_NAME";
            this.cboTgh.DisplayValue = "C_ORG_CODE";
            this.cboTgh.DllName = "YssControls.dll";
            this.cboTgh.FilterCond = "";
            this.cboTgh.Location = new System.Drawing.Point(110, 43);
            this.cboTgh.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataList";
            controlMethodInfo1.MethodParams = null;
            /*controlMethodInfo1.MethodParamValues = new string[] {
        "TRUSTEE,TRUSTEE_SEC,TRUSTEE_SEC,"};*/
            controlMethodInfo1.Methods = null;
            this.cboTgh.MethodInfo = controlMethodInfo1;
            this.cboTgh.Parameter = "C_ORG_CODE~C_ORG_NAME";
            this.cboTgh.Name = "cboTgh";
            this.cboTgh.QueryCond = "";
            this.cboTgh.QueryType = "";
            this.cboTgh.ShowCheckBox = true;
            this.cboTgh.Size = new System.Drawing.Size(121, 21);
            this.cboTgh.TabIndex = 26;
            this.cboTgh.Tag = this.cell2;
            this.cboTgh.YssAssociaType = YssInformation.Support.Context.AssociaType.base_organ;
            this.cboTgh.YssIsMust = true;
            // 
            // cboOrgnType
            // 
            this.cboOrgnType.AddedSelItemName = "";
            this.cboOrgnType.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboOrgnType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboOrgnType.Border.Bottom = true;
            this.cboOrgnType.Border.Left = true;
            this.cboOrgnType.Border.Right = true;
            this.cboOrgnType.Border.Top = true;
            this.cboOrgnType.ClassName = "YssControls.YssSelCombox";
            this.cboOrgnType.DisplayName = "C_DV_NAME";
            this.cboOrgnType.DisplayValue = "C_DV_CODE";
            this.cboOrgnType.DllName = "YssControls.dll";
            this.cboOrgnType.FilterCond = "";
            this.cboOrgnType.IsFillDecimal = false;
            this.cboOrgnType.Location = new System.Drawing.Point(110, 111);
            this.cboOrgnType.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo2.MethodName = "getDataListByTypes";
            controlMethodInfo2.MethodParams = null;
            controlMethodInfo2.MethodParamValues = new string[] {
        "RELA_ORG_TYPE,"};
            controlMethodInfo2.Methods = null;
            this.cboOrgnType.MethodInfo = controlMethodInfo2;
            this.cboOrgnType.Name = "cboOrgnType";
            this.cboOrgnType.Parameter = "C_DV_NAME";
            this.cboOrgnType.QueryCond = "RELA_ORG_TYPE";
            this.cboOrgnType.QueryType = "CacheType";
            this.cboOrgnType.Size = new System.Drawing.Size(121, 21);
            this.cboOrgnType.TabIndex = 25;
            this.cboOrgnType.Tag = this.cell4;
            this.cboOrgnType.Visible = false;
            this.cboOrgnType.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboOrgnType.YssIsMust = true;
            this.cboOrgnType.AfterDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(this.cboOrgnType_AfterDropDownClick);
            this.cboOrgnType.SelectedValueChanged += new System.EventHandler(this.cboOrgnType_SelectedValueChanged);
            // 
            // cell4
            // 
            this.cell4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell4.InnerControl = this.cboOrgnType;
            // 
            // row8
            // 
            this.row8.Font = new System.Drawing.Font("新宋体", 9.75F, System.Drawing.FontStyle.Bold);
            this.row8.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row8.FullRowSelected = false;
            this.row8.GroupPosition = 3;
            this.row8.Height = 33;
            this.row8.IsGroup = true;
            this.row8.Text = "关联机构";
            // 
            // row9
            // 
            this.row9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row9.FullRowSelected = false;
            this.row9.Height = 10;
            this.row9.Text = null;
            // 
            // row10
            // 
            this.row10.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell3,
            this.cell4});
            this.row10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row10.FullRowSelected = false;
            this.row10.Text = null;
            // 
            // cell3
            // 
            this.cell3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell3.Text = "   机构资质：";
            // 
            // Frm_DZ_PARA_ORG_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(628, 411);
            this.DoubleBuffered = true;
            this.Name = "Frm_DZ_PARA_ORG_S";
            this.Text = "关联机构";
            this.YssSetStatus = ClsEnums.StatusSetting.YssAdd;
            this.YssStatus = ClsEnums.StatusSetting.YssAdd;
            this.tbMain.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        private Yss.KTable.Models.Row row1;
        private Yss.KTable.Models.Row row2;
        private Yss.KTable.Models.Table table1;
        private Yss.KTable.Models.Table table2;
        private Yss.KTable.Models.Row row3;
        private Yss.KTable.Models.Row row4;
        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
        private Yss.KTable.Models.Row row5;
        private Yss.KTable.Models.Row row6;
        private Yss.KTable.Models.Row row7;
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Cell cell2;
        private Yss.KTable.Models.Row row8;
        private Yss.KTable.Models.Row row9;
        private Yss.KTable.Models.Row row10;
        private Yss.KTable.Models.Cell cell3;
        private Yss.KTable.Models.Cell cell4;
        protected YssSelCombox cboOrgnType;

        #endregion
        private YssSelCombox cboTgh;
    }
}