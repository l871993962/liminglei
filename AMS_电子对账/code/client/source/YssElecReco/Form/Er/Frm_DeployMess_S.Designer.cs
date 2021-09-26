namespace YssElecReco.Form.Er
{
    partial class Frm_DeployMess_S
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
            this.btnOK = new Yss.Controls.ImageButton();
            this.btnCancel = new Yss.Controls.ImageButton();
            this.panelEx1 = new Yss.Controls.PanelEx(this.components);
            this.table1 = new Yss.KTable.Models.Table(this.components);
            this.panel1 = new System.Windows.Forms.Panel();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.panelEx1.SuspendLayout();
            this.panel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // pnlMain
            // 
            this.pnlMain.Controls.Add(this.panel1);
            // 
            // yssPanel1
            // 
            this.yssPanel1.Size = new System.Drawing.Size(896, 448);
            // 
            // btnOK
            // 
            this.btnOK.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            // 
            // 
            // 
            this.btnOK.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.btnOK.Border.Bottom = true;
            this.btnOK.Border.Left = true;
            this.btnOK.Border.Right = true;
            this.btnOK.Border.Top = true;
            this.btnOK.Location = new System.Drawing.Point(680, 9);
            this.btnOK.Name = "btnOK";
            this.btnOK.Size = new System.Drawing.Size(75, 23);
            this.btnOK.TabIndex = 0;
            this.btnOK.Text = "继续";
            this.btnOK.Click += new System.EventHandler(this.btnOK_Click);
            // 
            // btnCancel
            // 
            this.btnCancel.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            // 
            // 
            // 
            this.btnCancel.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.btnCancel.Border.Bottom = true;
            this.btnCancel.Border.Left = true;
            this.btnCancel.Border.Right = true;
            this.btnCancel.Border.Top = true;
            this.btnCancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.btnCancel.Location = new System.Drawing.Point(794, 9);
            this.btnCancel.Name = "btnCancel";
            this.btnCancel.Size = new System.Drawing.Size(75, 23);
            this.btnCancel.TabIndex = 1;
            this.btnCancel.Text = "取消";
            this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
            // 
            // panelEx1
            // 
            // 
            // 
            // 
            this.panelEx1.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.panelEx1.Border.Bottom = true;
            this.panelEx1.Border.Left = true;
            this.panelEx1.Border.Right = true;
            this.panelEx1.Border.Top = true;
            this.panelEx1.Controls.Add(this.btnOK);
            this.panelEx1.Controls.Add(this.btnCancel);
            this.panelEx1.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.panelEx1.Location = new System.Drawing.Point(0, 406);
            this.panelEx1.Name = "panelEx1";
            this.panelEx1.Size = new System.Drawing.Size(896, 42);
            this.panelEx1.TabIndex = 2;
            // 
            // table1
            // 
            this.table1.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.table1.Border.Bottom = false;
            this.table1.Border.Left = false;
            this.table1.Border.Right = false;
            this.table1.Border.Top = false;
            this.table1.ColumnHeight = 20;
            this.table1.DefaultToolStripItems = Yss.KTable.Enums.SysToolStripItems.None;
            this.table1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.table1.GridLine = Yss.KTable.Enums.GridLines.Both;
            this.table1.GridLineColor = System.Drawing.Color.LightSteelBlue;
            this.table1.Location = new System.Drawing.Point(0, 0);
            this.table1.Name = "table1";
            this.table1.PlusMinusLineColor = System.Drawing.SystemColors.InactiveBorder;
            this.table1.SelectionMode = System.Windows.Forms.SelectionMode.One;
            this.table1.Size = new System.Drawing.Size(896, 406);
            this.table1.Style = Yss.KTable.Enums.Style.Default;
            this.table1.TabIndex = 3;
            this.table1.Text = "table1";
            // 
            // panel1
            // 
            this.panel1.Controls.Add(this.table1);
            this.panel1.Controls.Add(this.panelEx1);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel1.Location = new System.Drawing.Point(0, 0);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(896, 448);
            this.panel1.TabIndex = 4;
            // 
            // Frm_DeployMess_S
            // 
            this.AcceptButton = this.btnOK;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(202)))), ((int)(((byte)(205)))), ((int)(((byte)(214)))));
            this.BottomLeftCornerSize = 0;
            this.BottomRightCornerSize = 0;
            this.CancelButton = this.btnCancel;
            this.ClientSize = new System.Drawing.Size(896, 448);
            this.DoubleBuffered = true;
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = false;
            this.Name = "Frm_DeployMess_S";
            this.ShowIcon = false;
            this.Text = "选择部署接口";
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.panelEx1.ResumeLayout(false);
            this.panel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        public Yss.Controls.ImageButton btnOK;
        public Yss.Controls.ImageButton btnCancel;
        private Yss.Controls.PanelEx panelEx1;
        private System.Windows.Forms.Panel panel1;
        public Yss.KTable.Models.Table table1;
    }
}