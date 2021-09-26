namespace YssInformation.Sys.automaticSet.Form
{
    partial class Frm_AUTOMATIC_SET_TYPE_S
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
            this.selBusinessType = new FAST.Core.BaseControl.YssSelCombox();
            this.rtbMain = new Yss.KTableExpand.RichTable();
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.row1 = new Yss.KTable.Models.Row();
            this.tbMain.SuspendLayout();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // tbMain
            // 
            this.tbMain.Border.BorderColor = System.Drawing.Color.Gray;
            this.tbMain.Border.Bottom = false;
            this.tbMain.Border.Left = false;
            this.tbMain.Border.Right = false;
            this.tbMain.Border.Top = false;
            this.tbMain.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.column1,
            this.column2});
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1});
            this.tbMain.Controls.Add(this.selBusinessType);
            this.tbMain.Controls.Add(this.rtbMain);
            this.tbMain.Size = new System.Drawing.Size(602, 289);
            this.tbMain.ShowColumnHeader = false;
            // 
            // column1
            // 
            this.column1.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.Width = 80;
            // 
            // column2
            // 
            this.column2.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column2.Width = 122;
            // 
            // cell1
            // 
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.InnerControl = null;
            this.cell1.Text = "业务类型：";
            this.cell1.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.cell1.GridLinesBottom = false;
            this.cell1.GridLinesRight = false;
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.selBusinessType;
            this.cell2.InnerControlAlign = Yss.KTable.Enums.CellInnerControlAlign.Center;
            this.cell2.GridLinesBottom = false;
            this.cell2.GridLinesRight = false;
            // 
            // selBusinessType
            // 
            this.selBusinessType.AddedSelItemName = "";
            this.selBusinessType.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.selBusinessType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.selBusinessType.IsFillDecimal = false;
            this.selBusinessType.Location = new System.Drawing.Point(0, 0);
            this.selBusinessType.Margin = new System.Windows.Forms.Padding(0);
            this.selBusinessType.Name = "selBusinessType";
            this.selBusinessType.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.selBusinessType.Size = new System.Drawing.Size(121, 25);
            this.selBusinessType.TabIndex = 1;
            this.selBusinessType.Tag = this.cell2;
            this.selBusinessType.YssCaption = "业务类型";
            this.selBusinessType.DisplayName = "C_DV_NAME";
            this.selBusinessType.DisplayValue = "C_DV_CODE";
            this.selBusinessType.Parameter = "C_DV_NAME";
            this.selBusinessType.BeforeDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(this.selBusinessType_BeforeDropDownClick);
            this.selBusinessType.SelectedValueChanged += new System.EventHandler(this.selBusinessType_SelectedValueChanged);
            // 
            // row1
            // 
            this.row1.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell1,
            this.cell2});
            this.row1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row1.FullRowSelected = false;
            this.row1.Height = 40;
            this.row1.Text = null;
            // 
            // btnBar
            // 
            this.btnBar.Size = new System.Drawing.Size(602, 30);
            // 
            // stBarBottom
            // 
            this.stBarBottom.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.stBarBottom.Border.Bottom = false;
            this.stBarBottom.Border.Left = false;
            this.stBarBottom.Border.Right = false;
            this.stBarBottom.Location = new System.Drawing.Point(0, 319);
            this.stBarBottom.Size = new System.Drawing.Size(602, 25);
            // 
            // pnlMain
            // 
            this.pnlMain.Size = new System.Drawing.Size(602, 344);
            // 
            // yssPanel1
            // 
            this.yssPanel1.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.yssPanel1.Size = new System.Drawing.Size(602, 344);
            // 
            // rtbMain
            // 
            this.rtbMain.BackColor = System.Drawing.Color.WhiteSmoke;
            this.rtbMain.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.rtbMain.Location = new System.Drawing.Point(0, 40);
            this.rtbMain.Name = "rtbMain";
            this.rtbMain.ReadOnly = false;
            this.rtbMain.Size = new System.Drawing.Size(602, 249);
            this.rtbMain.TabIndex = 2;
            // 
            // Frm_AUTOMATIC_SET_TYPE_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(602, 344);
            this.DoubleBuffered = true;
            this.Name = "Frm_AUTOMATIC_SET_TYPE_S";
            this.Text = "业务类型设置";
            this.tbMain.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.ResumeLayout(false);
        }

        #endregion

        private FAST.Core.BaseControl.YssSelCombox selBusinessType;
        private Yss.KTableExpand.RichTable rtbMain;
        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Cell cell2;
        private Yss.KTable.Models.Row row1;
    }
}