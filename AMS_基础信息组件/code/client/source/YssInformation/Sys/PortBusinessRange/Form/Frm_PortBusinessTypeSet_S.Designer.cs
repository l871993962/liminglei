namespace YssInformation.Sys.PortBusinessRange.Form
{
    partial class Frm_PortBusinessTypeSet_S
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
            this.rtbMain = new Yss.KTableExpand.RichTable();
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
            this.tbMain.Controls.Add(this.rtbMain);
            this.tbMain.Size = new System.Drawing.Size(602, 259);
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
            this.stBarBottom.Location = new System.Drawing.Point(0, 289);
            this.stBarBottom.Size = new System.Drawing.Size(602, 25);
            // 
            // pnlMain
            // 
            this.pnlMain.Size = new System.Drawing.Size(602, 314);
            // 
            // yssPanel1
            // 
            this.yssPanel1.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.yssPanel1.Size = new System.Drawing.Size(602, 314);
            // 
            // rtbMain
            // 
            this.rtbMain.BackColor = System.Drawing.Color.WhiteSmoke;
            this.rtbMain.Dock = System.Windows.Forms.DockStyle.Fill;
            this.rtbMain.Location = new System.Drawing.Point(0, 0);
            this.rtbMain.Name = "rtbMain";
            this.rtbMain.ReadOnly = false;
            this.rtbMain.Size = new System.Drawing.Size(602, 259);
            this.rtbMain.TabIndex = 2;
            // 
            // Frm_PortBusinessTypeSet_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(602, 314);
            this.DoubleBuffered = true;
            this.Name = "Frm_PortBusinessTypeSet_S";
            this.Text = "业务类型设置";
            this.tbMain.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.ResumeLayout(false);
        }

        #endregion

        private Yss.KTableExpand.RichTable rtbMain;
    }
}