namespace YssElecReco.Form.Er
{
    partial class Frm_DZ_RepCfg_S
    {
        /// <summary>
        /// 必需的设计器变量。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 清理所有正在使用的资源。
        /// </summary>
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_DZ_RepCfg_S));
            this.tableLayoutPanel = new System.Windows.Forms.TableLayoutPanel();
            this.logoPictureBox = new System.Windows.Forms.PictureBox();
            this.labelProductName = new System.Windows.Forms.Label();
            this.labelVersion = new System.Windows.Forms.Label();
            this.labelCopyright = new System.Windows.Forms.Label();
            this.labelCompanyName = new System.Windows.Forms.Label();
            this.textBoxDescription = new System.Windows.Forms.TextBox();
            this.okButton = new System.Windows.Forms.Button();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.row3 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.selOrgName = new FAST.Core.BaseControl.YssSelCombox();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.selDzbb = new FAST.Core.BaseControl.YssSelCombox();
            this.row4 = new Yss.KTable.Models.Row();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.selCwbb = new FAST.Core.BaseControl.YssSelCombox();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.cboAssType = new FAST.Core.BaseControl.YssSelCombox();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.row5 = new Yss.KTable.Models.Row();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.cell13 = new Yss.KTable.Models.Cell();
            this.cell14 = new Yss.KTable.Models.Cell();
            this.cell15 = new Yss.KTable.Models.Cell();
            this.cboRptType = new FAST.Core.BaseControl.YssSelCombox();
            this.grpPort = new FAST.Core.BaseControl.GroupTextBox();
            this.tbMain.SuspendLayout();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.tableLayoutPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.logoPictureBox)).BeginInit();
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
            this.tbMain.Controls.Add(this.grpPort);
            this.tbMain.Controls.Add(this.cboRptType);
            this.tbMain.Controls.Add(this.cboAssType);
            this.tbMain.Controls.Add(this.selCwbb);
            this.tbMain.Controls.Add(this.selDzbb);
            this.tbMain.Controls.Add(this.selOrgName);
            this.tbMain.Cursor = System.Windows.Forms.Cursors.Default;
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3,
            this.row4,
            this.row5});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(494, 148);
            // 
            // btnBar
            // 
            this.btnBar.Size = new System.Drawing.Size(494, 30);
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
            this.stBarBottom.Location = new System.Drawing.Point(0, 178);
            this.stBarBottom.Size = new System.Drawing.Size(494, 25);
            // 
            // pnlMain
            // 
            this.pnlMain.Size = new System.Drawing.Size(494, 203);
            // 
            // yssPanel1
            // 
            // 
            // 
            // 
            this.yssPanel1.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.yssPanel1.Size = new System.Drawing.Size(494, 203);
            // 
            // tableLayoutPanel
            // 
            this.tableLayoutPanel.ColumnCount = 2;
            this.tableLayoutPanel.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 33F));
            this.tableLayoutPanel.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 67F));
            this.tableLayoutPanel.Controls.Add(this.logoPictureBox, 0, 0);
            this.tableLayoutPanel.Controls.Add(this.labelProductName, 1, 0);
            this.tableLayoutPanel.Controls.Add(this.labelVersion, 1, 1);
            this.tableLayoutPanel.Controls.Add(this.labelCopyright, 1, 2);
            this.tableLayoutPanel.Controls.Add(this.labelCompanyName, 1, 3);
            this.tableLayoutPanel.Controls.Add(this.textBoxDescription, 1, 4);
            this.tableLayoutPanel.Controls.Add(this.okButton, 1, 5);
            this.tableLayoutPanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanel.Location = new System.Drawing.Point(0, 0);
            this.tableLayoutPanel.Name = "tableLayoutPanel";
            this.tableLayoutPanel.RowCount = 6;
            this.tableLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 10F));
            this.tableLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 10F));
            this.tableLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 10F));
            this.tableLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 10F));
            this.tableLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50F));
            this.tableLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 10F));
            this.tableLayoutPanel.Size = new System.Drawing.Size(494, 203);
            this.tableLayoutPanel.TabIndex = 0;
            // 
            // logoPictureBox
            // 
            this.logoPictureBox.Dock = System.Windows.Forms.DockStyle.Fill;
            this.logoPictureBox.Image = ((System.Drawing.Image)(resources.GetObject("logoPictureBox.Image")));
            this.logoPictureBox.Location = new System.Drawing.Point(3, 3);
            this.logoPictureBox.Name = "logoPictureBox";
            this.tableLayoutPanel.SetRowSpan(this.logoPictureBox, 6);
            this.logoPictureBox.Size = new System.Drawing.Size(157, 197);
            this.logoPictureBox.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.logoPictureBox.TabIndex = 12;
            this.logoPictureBox.TabStop = false;
            // 
            // labelProductName
            // 
            this.labelProductName.Dock = System.Windows.Forms.DockStyle.Fill;
            this.labelProductName.Location = new System.Drawing.Point(169, 0);
            this.labelProductName.Margin = new System.Windows.Forms.Padding(6, 0, 3, 0);
            this.labelProductName.MaximumSize = new System.Drawing.Size(0, 16);
            this.labelProductName.Name = "labelProductName";
            this.labelProductName.Size = new System.Drawing.Size(322, 16);
            this.labelProductName.TabIndex = 19;
            this.labelProductName.Text = "产品名称";
            this.labelProductName.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // labelVersion
            // 
            this.labelVersion.Dock = System.Windows.Forms.DockStyle.Fill;
            this.labelVersion.Location = new System.Drawing.Point(169, 20);
            this.labelVersion.Margin = new System.Windows.Forms.Padding(6, 0, 3, 0);
            this.labelVersion.MaximumSize = new System.Drawing.Size(0, 16);
            this.labelVersion.Name = "labelVersion";
            this.labelVersion.Size = new System.Drawing.Size(322, 16);
            this.labelVersion.TabIndex = 0;
            this.labelVersion.Text = "版本";
            this.labelVersion.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // labelCopyright
            // 
            this.labelCopyright.Dock = System.Windows.Forms.DockStyle.Fill;
            this.labelCopyright.Location = new System.Drawing.Point(169, 40);
            this.labelCopyright.Margin = new System.Windows.Forms.Padding(6, 0, 3, 0);
            this.labelCopyright.MaximumSize = new System.Drawing.Size(0, 16);
            this.labelCopyright.Name = "labelCopyright";
            this.labelCopyright.Size = new System.Drawing.Size(322, 16);
            this.labelCopyright.TabIndex = 21;
            this.labelCopyright.Text = "版权";
            this.labelCopyright.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // labelCompanyName
            // 
            this.labelCompanyName.Dock = System.Windows.Forms.DockStyle.Fill;
            this.labelCompanyName.Location = new System.Drawing.Point(169, 60);
            this.labelCompanyName.Margin = new System.Windows.Forms.Padding(6, 0, 3, 0);
            this.labelCompanyName.MaximumSize = new System.Drawing.Size(0, 16);
            this.labelCompanyName.Name = "labelCompanyName";
            this.labelCompanyName.Size = new System.Drawing.Size(322, 16);
            this.labelCompanyName.TabIndex = 22;
            this.labelCompanyName.Text = "公司名称";
            this.labelCompanyName.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // textBoxDescription
            // 
            this.textBoxDescription.Dock = System.Windows.Forms.DockStyle.Fill;
            this.textBoxDescription.Location = new System.Drawing.Point(169, 83);
            this.textBoxDescription.Margin = new System.Windows.Forms.Padding(6, 3, 3, 3);
            this.textBoxDescription.Multiline = true;
            this.textBoxDescription.Name = "textBoxDescription";
            this.textBoxDescription.ReadOnly = true;
            this.textBoxDescription.ScrollBars = System.Windows.Forms.ScrollBars.Both;
            this.textBoxDescription.Size = new System.Drawing.Size(322, 95);
            this.textBoxDescription.TabIndex = 23;
            this.textBoxDescription.TabStop = false;
            this.textBoxDescription.Text = "说明";
            // 
            // okButton
            // 
            this.okButton.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.okButton.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.okButton.Location = new System.Drawing.Point(416, 186);
            this.okButton.Name = "okButton";
            this.okButton.Size = new System.Drawing.Size(75, 14);
            this.okButton.TabIndex = 24;
            this.okButton.Text = "确定(&O)";
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("新宋体", 9.75F, System.Drawing.FontStyle.Bold);
            this.row1.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row1.FullRowSelected = false;
            this.row1.GroupLineLength = 310;
            this.row1.Height = 33;
            this.row1.IsGroup = true;
            this.row1.Name = "row1";
            this.row1.Text = "基本信息";
            // 
            // row2
            // 
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row2.FullRowSelected = false;
            this.row2.Height = 10;
            this.row2.Name = "row2";
            this.row2.Text = null;
            // 
            // row3
            // 
            this.row3.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell1,
            this.cell2,
            this.cell3,
            this.cell4,
            this.cell5});
            this.row3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row3.FullRowSelected = false;
            this.row3.Name = "row3";
            this.row3.Text = null;
            // 
            // cell1
            // 
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.InnerControl = null;
            this.cell1.Name = "cell1";
            this.cell1.Text = "   机构名称： ";
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.selOrgName;
            this.cell2.Name = "cell2";
            // 
            // selOrgName
            // 
            this.selOrgName.AddedSelItemName = "";
            this.selOrgName.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.selOrgName.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.selOrgName.DisplayName = "C_ORG_NAME";
            this.selOrgName.DisplayValue = "C_ORG_CODE";
            this.selOrgName.Location = new System.Drawing.Point(110, 43);
            this.selOrgName.Margin = new System.Windows.Forms.Padding(0);
            this.selOrgName.Name = "selOrgName";
            this.selOrgName.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.selOrgName.Size = new System.Drawing.Size(121, 21);
            this.selOrgName.TabIndex = 1;
            this.selOrgName.Tag = this.cell2;
            this.selOrgName.YssAssociaType = YssInformation.Support.Context.AssociaType.base_organ;
            this.selOrgName.YssCaption = "机构名称";
            this.selOrgName.YssIsMust = true;
            this.selOrgName.YssOnBeforeLoadData += new FAST.Core.BaseControl.YssSelCombox.yssBeforeLoadData(this.selOrgName_YssOnBeforeLoadData);
            // 
            // cell3
            // 
            this.cell3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell3.InnerControl = null;
            this.cell3.Name = "cell3";
            // 
            // cell4
            // 
            this.cell4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell4.InnerControl = null;
            this.cell4.Name = "cell4";
            this.cell4.Text = "电子对账报表：";
            // 
            // cell5
            // 
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.selDzbb;
            this.cell5.Name = "cell5";
            // 
            // selDzbb
            // 
            this.selDzbb.AddedSelItemName = "";
            this.selDzbb.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.selDzbb.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.selDzbb.Location = new System.Drawing.Point(343, 43);
            this.selDzbb.Margin = new System.Windows.Forms.Padding(0);
            this.selDzbb.MethodInfo.MethodName = "getDataListByTypes";
            this.selDzbb.MethodInfo.MethodParamValues = new string[] {
        "1701,1801,1711,1811,1911,1901,1903,"};
            this.selDzbb.Name = "selDzbb";
            this.selDzbb.NodeID = "C_DZ_CODE";
            this.selDzbb.ParaNodeID = "C_DZ_CODE_P";
            this.selDzbb.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.selDzbb.Size = new System.Drawing.Size(121, 21);
            this.selDzbb.TabIndex = 2;
            this.selDzbb.Tag = this.cell5;
            this.selDzbb.YssAssociaType = YssElecReco.Context.AssociaType.base_erdztype;
            this.selDzbb.YssCaption = "电子对账报表";
            this.selDzbb.YssIsMust = true;
            // 
            // row4
            // 
            this.row4.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell6,
            this.cell7,
            this.cell8,
            this.cell9,
            this.cell10});
            this.row4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row4.FullRowSelected = false;
            this.row4.Name = "row4";
            this.row4.Text = null;
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.InnerControl = null;
            this.cell6.Name = "cell6";
            this.cell6.Text = "   财务报表：";
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.InnerControl = this.selCwbb;
            this.cell7.Name = "cell7";
            // 
            // selCwbb
            // 
            this.selCwbb.AddedSelItemName = "";
            this.selCwbb.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.selCwbb.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            ////this.selCwbb.DetailDataProperty = "Tag";
            ////this.selCwbb.DetailMark = "TEMPLATE";
            this.selCwbb.SelectMode = Yss.KRichEx.AutoFilter.SelectMode.Detail;
            this.selCwbb.DisplayName = "C_REPORT_NAME";
            this.selCwbb.DisplayStyle = Yss.KRichEx.AutoFilter.DisplayStyle.Modal;
            this.selCwbb.DisplayValue = "C_REPORT_CODE";
            this.selCwbb.KTableTree = true;
            this.selCwbb.Location = new System.Drawing.Point(110, 66);
            this.selCwbb.Margin = new System.Windows.Forms.Padding(0);
            this.selCwbb.Name = "selCwbb";
            this.selCwbb.NodeID = "C_REPORT_CODE";
            this.selCwbb.Parameter = "C_REPORT_NAME~C_REPORT_CODE";
            this.selCwbb.ParaNodeID = "C_GROUP_CODE";
            this.selCwbb.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.selCwbb.Size = new System.Drawing.Size(121, 21);
            this.selCwbb.SortColumn = "C_REPORT_NAME";
            this.selCwbb.TabIndex = 3;
            this.selCwbb.Tag = this.cell7;
            this.selCwbb.YssAssociaType = FAST.Core.Context.AssociaFAST.NULL;
            this.selCwbb.YssCaption = "财务报表";
            this.selCwbb.YssIsMust = true;
            this.selCwbb.YssOnBeforeLoadData += new FAST.Core.BaseControl.YssSelCombox.yssBeforeLoadData(this.selCwbb_YssOnBeforeLoadData);
            // 
            // cell8
            // 
            this.cell8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell8.InnerControl = null;
            this.cell8.Name = "cell8";
            // 
            // cell9
            // 
            this.cell9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell9.InnerControl = null;
            this.cell9.Name = "cell9";
            this.cell9.Text = "资产类型：";
            // 
            // cell10
            // 
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.InnerControl = this.cboAssType;
            this.cell10.Name = "cell10";
            // 
            // cboAssType
            // 
            this.cboAssType.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboAssType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboAssType.DisplayName = "C_DAT_NAME";
            this.cboAssType.DisplayValue = "C_DAT_CODE";
            this.cboAssType.IsFillDecimal = false;
            this.cboAssType.Location = new System.Drawing.Point(343, 66);
            this.cboAssType.Margin = new System.Windows.Forms.Padding(0);
            this.cboAssType.MethodInfo.MethodName = "getDataListByTypes";
            this.cboAssType.MethodInfo.MethodParamValues = new string[] {
        "ASS,"};
            this.cboAssType.Name = "cboAssType";
            this.cboAssType.NodeID = "C_DAT_CODE";
            this.cboAssType.Parameter = "C_DAT_NAME";
            this.cboAssType.ParaNodeID = "C_DAT_CODE_P";
            this.cboAssType.PrefixBackColor = System.Drawing.Color.White;
            this.cboAssType.Size = new System.Drawing.Size(121, 21);
            this.cboAssType.TabIndex = 8;
            this.cboAssType.Tag = this.cell10;
            this.cboAssType.YssAssociaType = YssInformation.Support.Context.AssociaType.base_accType;
            this.cboAssType.YssCaption = "资产类型";
            this.cboAssType.YssKiloDelimiter = true;
            this.cboAssType.SelectedValueChanged += new System.EventHandler(this.cboAssType_SelectedValueChanged);
            // 
            // cell12
            // 
            this.cell12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell12.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell12.InnerControl = this.grpPort;
            this.cell12.Name = "cell12";
            // 
            // column1
            // 
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.Name = "column1";
            this.column1.Width = 110;
            // 
            // column2
            // 
            this.column2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column2.Name = "column2";
            this.column2.Width = 122;
            // 
            // column3
            // 
            this.column3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column3.Name = "column3";
            this.column3.Width = 20;
            // 
            // column4
            // 
            this.column4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column4.Name = "column4";
            this.column4.Width = 91;
            // 
            // column5
            // 
            this.column5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column5.Name = "column5";
            this.column5.Width = 122;
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
            this.row5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row5.FullRowSelected = false;
            this.row5.Name = "row5";
            this.row5.Text = null;
            // 
            // cell11
            // 
            this.cell11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell11.InnerControl = null;
            this.cell11.Name = "cell11";
            this.cell11.Text = "   组合/群组：";
            // 
            // cell13
            // 
            this.cell13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell13.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell13.InnerControl = null;
            this.cell13.Name = "cell13";
            // 
            // cell14
            // 
            this.cell14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell14.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell14.InnerControl = null;
            this.cell14.Name = "cell14";
            this.cell14.Text = "报表类型：";
            // 
            // cell15
            // 
            this.cell15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell15.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell15.InnerControl = this.cboRptType;
            this.cell15.Name = "cell15";
            // 
            // cboRptType
            // 
            this.cboRptType.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboRptType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboRptType.DisplayName = "C_DV_NAME";
            this.cboRptType.DisplayValue = "C_DV_CODE";
            this.cboRptType.Location = new System.Drawing.Point(343, 89);
            this.cboRptType.Margin = new System.Windows.Forms.Padding(0);
            this.cboRptType.Name = "cboRptType";
            this.cboRptType.Parameter = "C_DV_NAME";
            this.cboRptType.PrefixBackColor = System.Drawing.Color.White;
            this.cboRptType.ShowCheckBox = true;
            this.cboRptType.Size = new System.Drawing.Size(121, 21);
            this.cboRptType.TabIndex = 9;
            this.cboRptType.Tag = this.cell15;
            this.cboRptType.YssAssociaType = FAST.Core.Context.AssociaFAST.NULL;
            this.cboRptType.YssOnBeforeLoadData += new FAST.Core.BaseControl.YssSelCombox.yssBeforeLoadData(this.cboRptType_setValue);
            // 
            // grpPort
            // 
            // 
            // 
            // 
            this.grpPort.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.grpPort.EnterOnTailClick = false;
            this.grpPort.IsFillDecimal = false;
            this.grpPort.Location = new System.Drawing.Point(110, 89);
            this.grpPort.Margin = new System.Windows.Forms.Padding(0);
            this.grpPort.Name = "grpPort";
            this.grpPort.PrefixForeColor = System.Drawing.SystemColors.WindowText;
            this.grpPort.ShowCheckBox = true;
            this.grpPort.ShowColumnHeader = true;
            this.grpPort.ShowGroupPage = true;
            this.grpPort.Size = new System.Drawing.Size(121, 21);
            this.grpPort.SortColumn = "C_PORT_NAME_ST";
            this.grpPort.SufixForeColor = System.Drawing.SystemColors.WindowText;
            this.grpPort.TabIndex = 12;
            this.grpPort.Tag = this.cell10;
            this.grpPort.YssCaption = "组合/群组";
            this.grpPort.YssLength = 200000;
            this.grpPort.ValueChanged += new System.EventHandler(this.grpPort_SelectedValueChanged);
            // 
            // Frm_DZ_RepCfg_S
            // 
            this.AcceptButton = this.okButton;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(494, 203);
            this.Controls.Add(this.tableLayoutPanel);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedDialog;
            this.MinimizeBox = false;
            this.Name = "Frm_DZ_RepCfg_S";
            this.ShowInTaskbar = false;
            this.Text = "Frm_DZ_CONFIG_S";
            this.Controls.SetChildIndex(this.tableLayoutPanel, 0);
            this.Controls.SetChildIndex(this.yssPanel1, 0);
            this.tbMain.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.tableLayoutPanel.ResumeLayout(false);
            this.tableLayoutPanel.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.logoPictureBox)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.TableLayoutPanel tableLayoutPanel;
        private System.Windows.Forms.PictureBox logoPictureBox;
        private System.Windows.Forms.Label labelProductName;
        private System.Windows.Forms.Label labelVersion;
        private System.Windows.Forms.Label labelCopyright;
        private System.Windows.Forms.Label labelCompanyName;
        private System.Windows.Forms.TextBox textBoxDescription;
        private System.Windows.Forms.Button okButton;
        private Yss.KTable.Models.Row row1;
        private Yss.KTable.Models.Row row2;
        private Yss.KTable.Models.Row row3;
        private Yss.KTable.Models.Row row4;
        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
        private Yss.KTable.Models.Column column3;
        private Yss.KTable.Models.Column column4;
        private Yss.KTable.Models.Column column5;
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
        private FAST.Core.BaseControl.YssSelCombox selCwbb;
        private FAST.Core.BaseControl.YssSelCombox selDzbb;
        private FAST.Core.BaseControl.YssSelCombox selOrgName;
        private FAST.Core.BaseControl.YssSelCombox cboAssType;
        private Yss.KTable.Models.Cell cell12;
        private Yss.KTable.Models.Row row5;
        private Yss.KTable.Models.Cell cell11;
        private FAST.Core.BaseControl.YssSelCombox cboRptType;
        private Yss.KTable.Models.Cell cell15;
        private Yss.KTable.Models.Cell cell13;
        private Yss.KTable.Models.Cell cell14;
        private FAST.Core.BaseControl.GroupTextBox grpPort;
    }
}