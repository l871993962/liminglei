using System.ComponentModel;

namespace YssInformation.Bi.AccountTree.Form
{
    partial class Frm_ACC_TREE_SELECT_S
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private IContainer components = null;

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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_ACC_TREE_SELECT_S));
            this.pnlBottom = new System.Windows.Forms.Panel();
            this.chkBoxSpecial = new YssDevComponents.DotNetBar.Controls.CheckBoxX();
            this.btnCancel = new Yss.Controls.ImageButton();
            this.btnOK = new Yss.Controls.ImageButton();
            this.tabCtrl = new Yss.Controls.TabControl();
            this.pnlBottom.SuspendLayout();
            this.SuspendLayout();
            // 
            // pnlBottom
            // 
            this.pnlBottom.BackColor = System.Drawing.Color.Transparent;
            this.pnlBottom.Controls.Add(this.chkBoxSpecial);
            this.pnlBottom.Controls.Add(this.btnCancel);
            this.pnlBottom.Controls.Add(this.btnOK);
            this.pnlBottom.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.pnlBottom.Location = new System.Drawing.Point(2, 449);
            this.pnlBottom.Name = "pnlBottom";
            this.pnlBottom.Size = new System.Drawing.Size(413, 37);
            this.pnlBottom.TabIndex = 0;
            // 
            // chkBoxSpecial
            // 
            this.chkBoxSpecial.Checked = true;
            this.chkBoxSpecial.CheckState = System.Windows.Forms.CheckState.Checked;
            this.chkBoxSpecial.CheckValue = "Y";
            this.chkBoxSpecial.Location = new System.Drawing.Point(12, 7);
            this.chkBoxSpecial.Name = "chkBoxSpecial";
            this.chkBoxSpecial.Size = new System.Drawing.Size(95, 23);
            this.chkBoxSpecial.TabIndex = 36;
            this.chkBoxSpecial.Text = "本功能专用";
            // 
            // btnCancel
            // 
            this.btnCancel.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("btnCancel.BackgroundImage")));
            this.btnCancel.BackgroundImageSize = new System.Drawing.Size(73, 26);
            // 
            // 
            // 
            this.btnCancel.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.btnCancel.DialogResult = System.Windows.Forms.DialogResult.Cancel;
            this.btnCancel.Location = new System.Drawing.Point(330, 5);
            this.btnCancel.Name = "btnCancel";
            this.btnCancel.Size = new System.Drawing.Size(73, 26);
            this.btnCancel.TabIndex = 35;
            this.btnCancel.Click += new System.EventHandler(this.btnCancel_Click);
            // 
            // btnOK
            // 
            this.btnOK.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("btnOK.BackgroundImage")));
            this.btnOK.BackgroundImageSize = new System.Drawing.Size(72, 26);
            // 
            // 
            // 
            this.btnOK.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.btnOK.Location = new System.Drawing.Point(244, 5);
            this.btnOK.Name = "btnOK";
            this.btnOK.Size = new System.Drawing.Size(72, 26);
            this.btnOK.TabIndex = 34;
            this.btnOK.Click += new System.EventHandler(this.btnOK_Click);
            // 
            // tabCtrl
            // 
            this.tabCtrl.BackColor = System.Drawing.Color.WhiteSmoke;
            this.tabCtrl.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tabCtrl.Location = new System.Drawing.Point(2, 85);
            this.tabCtrl.Name = "tabCtrl";
            this.tabCtrl.ShowTabAddButton = false;
            this.tabCtrl.ShowTabHistoryButton = false;
            this.tabCtrl.Size = new System.Drawing.Size(413, 364);
            this.tabCtrl.TabAutoClose = false;
            this.tabCtrl.TabAutoWidth = false;
            this.tabCtrl.TabBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(222)))), ((int)(((byte)(237)))), ((int)(((byte)(245)))));
            this.tabCtrl.TabCloseButtonVisible = false;
            this.tabCtrl.TabCloseImage = null;
            this.tabCtrl.TabCloseImageHot = null;
            this.tabCtrl.TabFontSelected = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.tabCtrl.TabForeColorHot = System.Drawing.Color.Coral;
            this.tabCtrl.TabHeight = 28;
            this.tabCtrl.TabIndex = 26;
            this.tabCtrl.TabLayout = Yss.Controls.TabLayout.Left;
            this.tabCtrl.TabWidth = 100;
            this.tabCtrl.Text = "默认设置";
            this.tabCtrl.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.tabCtrl.SelectedIndexChanged += new Yss.Controls.TabPageEventHandler(this.tabCtrl_SelectedIndexChanged);
            // 
            // FrmBaseDefPort
            // 
            this.AcceptButton = this.btnOK;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("$this.BackgroundImage")));
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.CancelButton = this.btnCancel;
            this.ClientSize = new System.Drawing.Size(417, 486);
            this.Controls.Add(this.tabCtrl);
            this.Controls.Add(this.pnlBottom);
            this.DoubleBuffered = true;
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "Frm_ACC_TREE_SELECT_S";
            this.Padding = new System.Windows.Forms.Padding(2, 85, 2, 0);
            this.ShowInTaskbar = false;
            this.SizeGripStyle = System.Windows.Forms.SizeGripStyle.Hide;
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Frm_ACC_TREE_SELECT_S";
            this.Load += new System.EventHandler(this.Frm_ACC_TREE_SELECT_S_Load);
            this.MouseDown += new System.Windows.Forms.MouseEventHandler(this.Frm_ACC_TREE_SELECT_S_MouseDown);
            this.pnlBottom.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        protected System.Windows.Forms.Panel pnlBottom;
        protected Yss.Controls.ImageButton btnCancel;
        protected Yss.Controls.ImageButton btnOK;
        protected YssDevComponents.DotNetBar.Controls.CheckBoxX chkBoxSpecial;
        protected Yss.Controls.TabControl tabCtrl;
    }

}