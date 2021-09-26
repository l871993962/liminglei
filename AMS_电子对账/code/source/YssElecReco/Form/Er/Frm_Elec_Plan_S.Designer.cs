namespace YssElecReco.Form.Er
{
    partial class Frm_Elec_Plan_S
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
            this.tablefangan = new Yss.KTable.Models.Table(this.components);
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.nameTextBox = new Yss.KRichEx.YssTextBox();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.codeTextBox = new Yss.KRichEx.YssTextBox();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.row3 = new Yss.KTable.Models.Row();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.row4 = new Yss.KTable.Models.Row();
            this.row5 = new Yss.KTable.Models.Row();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.row6 = new Yss.KTable.Models.Row();
            this.row7 = new Yss.KTable.Models.Row();
            this.panelData = new Yss.Controls.PanelEx(this.components);
            this.panelEx1 = new Yss.Controls.PanelEx(this.components);
            this.tableList = new Yss.KTable.Models.Table(this.components);
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.tablefangan.SuspendLayout();
            this.panelData.SuspendLayout();
            this.panelEx1.SuspendLayout();
            this.SuspendLayout();
            // 
            // tbMain
            // 
            this.tbMain.Anchor = System.Windows.Forms.AnchorStyles.Top;
            this.tbMain.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.tbMain.Border.BorderColor = System.Drawing.Color.Blue;
            this.tbMain.Border.Bottom = false;
            this.tbMain.Border.Left = false;
            this.tbMain.Border.Right = false;
            this.tbMain.Border.Top = false;
            this.tbMain.Dock = System.Windows.Forms.DockStyle.None;
            this.tbMain.Location = new System.Drawing.Point(-2, 161);
            this.tbMain.Margin = new System.Windows.Forms.Padding(3);
            this.tbMain.Size = new System.Drawing.Size(0, 0);
            // 
            // btnBar
            // 
            this.btnBar.Size = new System.Drawing.Size(448, 30);
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
            this.stBarBottom.Location = new System.Drawing.Point(0, 626);
            this.stBarBottom.Size = new System.Drawing.Size(448, 25);
            // 
            // pnlMain
            // 
            this.pnlMain.Controls.Add(this.panelEx1);
            this.pnlMain.Controls.Add(this.panelData);
            this.pnlMain.Size = new System.Drawing.Size(448, 651);
            this.pnlMain.Controls.SetChildIndex(this.stBarBottom, 0);
            this.pnlMain.Controls.SetChildIndex(this.btnBar, 0);
            this.pnlMain.Controls.SetChildIndex(this.tbMain, 0);
            this.pnlMain.Controls.SetChildIndex(this.panelData, 0);
            this.pnlMain.Controls.SetChildIndex(this.panelEx1, 0);
            // 
            // yssPanel1
            // 
            // 
            // 
            // 
            this.yssPanel1.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.yssPanel1.Size = new System.Drawing.Size(448, 651);
            // 
            // tablefangan
            // 
            this.tablefangan.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.tablefangan.Border.BorderColor = System.Drawing.Color.Empty;
            this.tablefangan.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.column1,
            this.column2});
            this.tablefangan.Controls.Add(this.nameTextBox);
            this.tablefangan.Controls.Add(this.codeTextBox);
            this.tablefangan.DefaultToolStripItems = ((Yss.KTable.Enums.SysToolStripItems)((((((((((((Yss.KTable.Enums.SysToolStripItems.ShowHideColumn | Yss.KTable.Enums.SysToolStripItems.GroupByColumn)
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
            this.tablefangan.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tablefangan.Location = new System.Drawing.Point(0, 0);
            this.tablefangan.Name = "tablefangan";
            this.tablefangan.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3,
            this.row4,
            this.row5,
            this.row6,
            this.row7});
            this.tablefangan.ShowColumnHeader = false;
            this.tablefangan.Size = new System.Drawing.Size(448, 131);
            this.tablefangan.TabIndex = 16;
            this.tablefangan.Text = "table1";
            // 
            // column1
            // 
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.MaxPrintWidth = 0;
            this.column1.Width = 100;
            // 
            // column2
            // 
            this.column2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column2.MaxPrintWidth = 0;
            this.column2.Width = 120;
            // 
            // nameTextBox
            // 
            this.nameTextBox.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.nameTextBox.Location = new System.Drawing.Point(100, 61);
            this.nameTextBox.Name = "nameTextBox";
            this.nameTextBox.Size = new System.Drawing.Size(119, 21);
            this.nameTextBox.TabIndex = 3;
            this.nameTextBox.Tag = this.cell5;
            this.nameTextBox.YssCaption = "方案名称";
            this.nameTextBox.YssIsMust = true;
            // 
            // cell5
            // 
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.nameTextBox;
            // 
            // codeTextBox
            // 
            this.codeTextBox.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.codeTextBox.Location = new System.Drawing.Point(100, 33);
            this.codeTextBox.Name = "codeTextBox";
            this.codeTextBox.Size = new System.Drawing.Size(119, 21);
            this.codeTextBox.TabIndex = 2;
            this.codeTextBox.Tag = this.cell3;
            this.codeTextBox.YssCaption = "方案代码";
            this.codeTextBox.YssIsMust = true;
            // 
            // cell3
            // 
            this.cell3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell3.InnerControl = this.codeTextBox;
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("宋体", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row1.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row1.GroupLineLength = 500;
            this.row1.IsGroup = true;
            this.row1.Text = "主要指标方案";
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
            this.cell2,
            this.cell3});
            this.row3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row3.Text = null;
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = null;
            this.cell2.Text = "    方案代码:";
            // 
            // row4
            // 
            this.row4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row4.Height = 5;
            this.row4.Text = null;
            // 
            // row5
            // 
            this.row5.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell4,
            this.cell5});
            this.row5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row5.Text = null;
            // 
            // cell4
            // 
            this.cell4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell4.InnerControl = null;
            this.cell4.Text = "    方案名称:";
            // 
            // row6
            // 
            this.row6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row6.Height = 5;
            this.row6.Text = null;
            // 
            // row7
            // 
            this.row7.Font = new System.Drawing.Font("宋体", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row7.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row7.GroupLineLength = 500;
            this.row7.IsGroup = true;
            this.row7.Text = "主要指标信息";
            // 
            // panelData
            // 
            // 
            // 
            // 
            this.panelData.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.panelData.Controls.Add(this.tablefangan);
            this.panelData.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelData.Location = new System.Drawing.Point(0, 30);
            this.panelData.Name = "panelData";
            this.panelData.Size = new System.Drawing.Size(448, 131);
            this.panelData.TabIndex = 3;
            // 
            // panelEx1
            // 
            this.panelEx1.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.panelEx1.Border.BorderColor = System.Drawing.Color.Empty;
            this.panelEx1.Border.Bottom = false;
            this.panelEx1.Border.Left = false;
            this.panelEx1.Border.Right = false;
            this.panelEx1.Border.Top = false;
            this.panelEx1.Controls.Add(this.tableList);
            this.panelEx1.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.panelEx1.Location = new System.Drawing.Point(0, 152);
            this.panelEx1.Name = "panelEx1";
            this.panelEx1.Size = new System.Drawing.Size(448, 474);
            this.panelEx1.TabIndex = 16;
            // 
            // tableList
            // 
            this.tableList.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom)
                        | System.Windows.Forms.AnchorStyles.Left)
                        | System.Windows.Forms.AnchorStyles.Right)));
            this.tableList.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.tableList.Border.BorderColor = System.Drawing.Color.Empty;
            this.tableList.DefaultToolStripItems = ((Yss.KTable.Enums.SysToolStripItems)((((((((((((Yss.KTable.Enums.SysToolStripItems.ShowHideColumn | Yss.KTable.Enums.SysToolStripItems.GroupByColumn)
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
            this.tableList.Location = new System.Drawing.Point(20, 0);
            this.tableList.Name = "tableList";
            this.tableList.Size = new System.Drawing.Size(428, 474);
            this.tableList.TabIndex = 0;
            this.tableList.Text = "table1";
            // 
            // Frm_Elec_Plan_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(448, 651);
            this.DoubleBuffered = true;
            this.Name = "Frm_Elec_Plan_S";
            this.Text = "Frm_Elec_Plan_S";
            this.Load += new System.EventHandler(this.Frm_Elec_Plan_S_Load);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.tablefangan.ResumeLayout(false);
            this.panelData.ResumeLayout(false);
            this.panelEx1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private Yss.KTable.Models.Table tablefangan;
        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
        private Yss.KRichEx.YssTextBox nameTextBox;
        private Yss.KRichEx.YssTextBox codeTextBox;
        private Yss.KTable.Models.Row row1;
        private Yss.KTable.Models.Row row2;
        private Yss.KTable.Models.Row row3;
        private Yss.KTable.Models.Cell cell2;
        private Yss.KTable.Models.Cell cell3;
        private Yss.KTable.Models.Row row5;
        private Yss.KTable.Models.Cell cell4;
        private Yss.KTable.Models.Cell cell5;
        private Yss.KTable.Models.Row row4;
        private Yss.KTable.Models.Row row6;
        private Yss.KTable.Models.Row row7;
        private Yss.Controls.PanelEx panelData;
        private Yss.Controls.PanelEx panelEx1;
        private Yss.KTable.Models.Table tableList;

    }
}