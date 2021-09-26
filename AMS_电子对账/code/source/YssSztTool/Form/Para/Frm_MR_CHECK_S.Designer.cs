namespace YssSztTool.Form.Para
{
    partial class Frm_MR_CHECK_S
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
            this.tbMain = new Yss.KTable.Models.Table(this.components);
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.InputInterval = new Yss.KRichEx.IntegerInputEx();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.cboUser = new FAST.Core.BaseControl.YssSelCombox();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.btnSave = new Yss.Controls.ImageButton();
            this.btnCancel = new Yss.Controls.ImageButton();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.row3 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.row4 = new Yss.KTable.Models.Row();
            this.row5 = new Yss.KTable.Models.Row();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.tbMain.SuspendLayout();
            this.SuspendLayout();
            // 
            // pnlMain
            // 
            this.pnlMain.Controls.Add(this.tbMain);
            this.pnlMain.Size = new System.Drawing.Size(456, 233);
            // 
            // yssPanel1
            // 
            this.yssPanel1.Size = new System.Drawing.Size(456, 233);
            // 
            // tbMain
            // 
            this.tbMain.BackColor = System.Drawing.Color.White;
            this.tbMain.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.column1,
            this.column2,
            this.column3});
            this.tbMain.Controls.Add(this.InputInterval);
            this.tbMain.Controls.Add(this.cboUser);
            this.tbMain.Controls.Add(this.btnSave);
            this.tbMain.Controls.Add(this.btnCancel);
            this.tbMain.DefaultToolStripItems = ((Yss.KTable.Enums.SysToolStripItems)((((((((((((Yss.KTable.Enums.SysToolStripItems.ShowHideColumn | Yss.KTable.Enums.SysToolStripItems.GroupByColumn)
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
            this.tbMain.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tbMain.Location = new System.Drawing.Point(0, 0);
            this.tbMain.Name = "tbMain";
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3,
            this.row4,
            this.row5});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(456, 233);
            this.tbMain.TabIndex = 0;
            this.tbMain.Text = "table1";
            // 
            // column1
            // 
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.Width = 125;
            // 
            // column2
            // 
            this.column2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column2.Width = 120;
            // 
            // column3
            // 
            this.column3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column3.Width = 180;
            // 
            // InputInterval
            // 
            // 
            // 
            // 
            this.InputInterval.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.InputInterval.Location = new System.Drawing.Point(125, 33);
            this.InputInterval.MaxValue = 99999;
            this.InputInterval.MinValue = 1;
            this.InputInterval.Name = "InputInterval";
            this.InputInterval.PositiveOnly = true;
            this.InputInterval.Prefix = "每隔";
            this.InputInterval.Size = new System.Drawing.Size(119, 21);
            this.InputInterval.Sufix = "分钟";
            this.InputInterval.TabIndex = 3;
            this.InputInterval.Tag = this.cell2;
            this.InputInterval.Text = "1";
            this.InputInterval.Value = 1;
            this.InputInterval.YssCaption = "自动检测频率";
            this.InputInterval.YssIsMust = true;
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.InputInterval;
            // 
            // cboUser
            // 
            this.cboUser.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboUser.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboUser.DisplayName = "C_CORP_ORG_NAME";
            this.cboUser.DisplayValue = "C_CORP_ORG_CODE";
            this.cboUser.FilterCond = "";
            this.cboUser.KTableTree = true;
            this.cboUser.Location = new System.Drawing.Point(125, 66);
            this.cboUser.Margin = new System.Windows.Forms.Padding(0);
            this.cboUser.MethodInfo = null;
            this.cboUser.Name = "cboUser";
            this.cboUser.NodeID = "C_CORP_ORG_CODE";
            this.cboUser.Parameter = "C_CORP_ORG_CODE~C_CORP_ORG_NAME";
            this.cboUser.ParaNodeID = "C_CORP_ORG_CODE_P";
            this.cboUser.PrefixBackColor = System.Drawing.Color.White;
            this.cboUser.QueryCond = "";
            this.cboUser.QueryType = "";
            this.cboUser.ShowCheckBox = true;
            this.cboUser.Size = new System.Drawing.Size(299, 21);
            this.cboUser.SortColumn = "C_CORP_ORG_NAME";
            this.cboUser.TabIndex = 2;
            this.cboUser.Tag = this.cell4;
            this.cboUser.YssAssociaType = FAST.Core.Context.AssociaFAST.NULL;
            this.cboUser.YssCaption = "消息提醒用户";
            this.cboUser.YssOnBeforeLoadData += new FAST.Core.BaseControl.YssSelCombox.yssBeforeLoadData(this.cboUser_YssOnBeforeLoadData);
            // 
            // cell4
            // 
            this.cell4.ColSpan = 2;
            this.cell4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell4.InnerControl = this.cboUser;
            // 
            // btnSave
            // 
            this.btnSave.Location = new System.Drawing.Point(244, 172);
            this.btnSave.Name = "btnSave";
            this.btnSave.Size = new System.Drawing.Size(75, 23);
            this.btnSave.TabIndex = 4;
            this.btnSave.Text = "保存";
            this.btnSave.Click += new System.EventHandler(this.btnSave_Click);
            // 
            // btnCancel
            // 
            this.btnCancel.Location = new System.Drawing.Point(349, 172);
            this.btnCancel.Name = "btnCancel";
            this.btnCancel.Size = new System.Drawing.Size(75, 23);
            this.btnCancel.TabIndex = 5;
            this.btnCancel.Text = "取消";
            this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("宋体", 9.75F, System.Drawing.FontStyle.Bold);
            this.row1.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(133)))));
            this.row1.GroupLineLength = 500;
            this.row1.IsGroup = true;
            this.row1.Text = "基本信息";
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
            this.cell1,
            this.cell2});
            this.row3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row3.Text = null;
            // 
            // cell1
            // 
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.InnerControl = null;
            this.cell1.Text = "   自动检测频率：";
            // 
            // row4
            // 
            this.row4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row4.Height = 10;
            this.row4.Text = null;
            // 
            // row5
            // 
            this.row5.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell3,
            this.cell4});
            this.row5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row5.Text = null;
            // 
            // cell3
            // 
            this.cell3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell3.InnerControl = null;
            this.cell3.Text = "   消息提醒用户：";
            // 
            // Frm_MR_CHECK_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(456, 233);
            this.DoubleBuffered = true;
            this.Name = "Frm_MR_CHECK_S";
            this.Text = "连接检测设置";
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.tbMain.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private Yss.KTable.Models.Table tbMain;
        private Yss.KTable.Models.Row row1;
        private Yss.KTable.Models.Row row2;
        private Yss.KTable.Models.Row row3;
        private Yss.KTable.Models.Row row4;
        private Yss.KTable.Models.Row row5;
        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
        private Yss.KTable.Models.Column column3;
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Cell cell2;
        private Yss.KTable.Models.Cell cell3;
        private Yss.KTable.Models.Cell cell4;
        private Yss.KRichEx.IntegerInputEx InputInterval;
        private FAST.Core.BaseControl.YssSelCombox cboUser;
        private Yss.Controls.ImageButton btnSave;
        private Yss.Controls.ImageButton btnCancel;
    }
}