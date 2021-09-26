namespace YssSyncData.Form.Base
{
    partial class Frm_SyncData_S
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
            this.column1 = new Yss.KTable.Models.Column();
            this.row1 = new Yss.KTable.Models.Row();
            this.rTBFuncodeCfg = new Yss.KTableExpand.RichTable();
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
            this.tbMain.Border.BorderColor = System.Drawing.Color.Gray;
            this.tbMain.Border.Bottom = false;
            this.tbMain.Border.Left = false;
            this.tbMain.Border.Right = false;
            this.tbMain.Border.Top = false;
            this.tbMain.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.column1});
            this.tbMain.Controls.Add(this.rTBFuncodeCfg);
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1});
            this.tbMain.ShowColumnHeader = false;
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
            // 
            // yssPanel1
            // 
            // 
            // 
            // 
            this.yssPanel1.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            // 
            // column1
            // 
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.Width = 490;
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row1.FullRowSelected = false;
            this.row1.Height = 285;
            this.row1.Text = null;
            // 
            // rTBFuncodeCfg
            // 
            this.rTBFuncodeCfg.BackColor = System.Drawing.Color.WhiteSmoke;
            this.rTBFuncodeCfg.Dock = System.Windows.Forms.DockStyle.Fill;
            this.rTBFuncodeCfg.Location = new System.Drawing.Point(0, 0);
            this.rTBFuncodeCfg.Name = "rTBFuncodeCfg";
            this.rTBFuncodeCfg.ReadOnly = false;
            this.rTBFuncodeCfg.Size = new System.Drawing.Size(492, 324);
            this.rTBFuncodeCfg.TabIndex = 2;
            // 
            // Frm_SyncData_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(493, 380);
            this.DoubleBuffered = true;
            this.Name = "Frm_SyncData_S";
            this.Text = "Frm_SyncData_S";
            this.Load += new System.EventHandler(this.Frm_SyncData_S_Load);
            this.tbMain.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private Yss.KTable.Models.Column column1;
        private Yss.KTableExpand.RichTable rTBFuncodeCfg;
        private Yss.KTable.Models.Row row1;
    }
}