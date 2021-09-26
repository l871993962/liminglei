namespace YssSecInformation.Mp.SecEq.Form
{
    partial class Frm_TRANSFER_PARA
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_SUSPEND_PARA));
            this.tbMain = new Yss.KTable.Models.Table(this.components);
            this.bar1 = new YssDevComponents.DotNetBar.Bar();
            this.btnOk = new YssDevComponents.DotNetBar.ButtonItem();
            this.btnQuit = new YssDevComponents.DotNetBar.ButtonItem();
            this.labelItem1 = new YssDevComponents.DotNetBar.LabelItem();
            this.pnlList = new System.Windows.Forms.Panel();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.bar1)).BeginInit();
            this.pnlList.SuspendLayout();
            this.SuspendLayout();
            // 
            // pnlMain
            // 
            this.pnlMain.Controls.Add(this.bar1);
            this.pnlMain.Controls.Add(this.pnlList);
            this.pnlMain.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.pnlMain.Size = new System.Drawing.Size(593, 302);
            ////this.pnlMain.Style.Alignment = System.Drawing.StringAlignment.Center;
            ////this.pnlMain.Style.BackColor1.Color = System.Drawing.Color.White;
            ////this.pnlMain.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            ////this.pnlMain.Style.BorderColor.Color = System.Drawing.Color.FromArgb(((int)(((byte)(121)))), ((int)(((byte)(173)))), ((int)(((byte)(225)))));
            ////this.pnlMain.Style.BorderSide = YssDevComponents.DotNetBar.eBorderSide.Top;
            ////this.pnlMain.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            ////this.pnlMain.Style.GradientAngle = 90;
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
            this.yssPanel1.Size = new System.Drawing.Size(593, 302);
            // 
            // tbMain
            // 
            this.tbMain.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.tbMain.Border.Bottom = true;
            this.tbMain.Border.Left = false;
            this.tbMain.Border.Right = false;
            this.tbMain.Border.Top = false;
            this.tbMain.DefaultToolStripItems = ((Yss.KTable.Enums.SysToolStripItems)((((((((((Yss.KTable.Enums.SysToolStripItems.ShowHideColumn | Yss.KTable.Enums.SysToolStripItems.GroupByColumn)
                        | Yss.KTable.Enums.SysToolStripItems.PrintSetup)
                        | Yss.KTable.Enums.SysToolStripItems.PrintPreview)
                        | Yss.KTable.Enums.SysToolStripItems.GardingMenu)
                        | Yss.KTable.Enums.SysToolStripItems.ShowStyleSetting)
                        | Yss.KTable.Enums.SysToolStripItems.ShowCopyTool)
                        | Yss.KTable.Enums.SysToolStripItems.ShowColumnsEdit)
                        | Yss.KTable.Enums.SysToolStripItems.ShowRowsEdit)
                        | Yss.KTable.Enums.SysToolStripItems.ShowCreateTable)));
            this.tbMain.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tbMain.FullRowSelect = false;
            this.tbMain.GridLine = Yss.KTable.Enums.GridLines.Both;
            this.tbMain.GridLineColor = System.Drawing.Color.LightSteelBlue;
            this.tbMain.Location = new System.Drawing.Point(0, 0);
            this.tbMain.Margin = new System.Windows.Forms.Padding(60, 60, 60, 60);
            this.tbMain.Name = "tbMain";
            this.tbMain.PlusMinusLineColor = System.Drawing.SystemColors.InactiveBorder;
            this.tbMain.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IndentOnly;
            this.tbMain.Size = new System.Drawing.Size(593, 302);
            this.tbMain.TabIndex = 1;
            this.tbMain.Text = "table2";
            this.tbMain.CellMouseClick += new Yss.KTable.Events.CellEventHandler(this.tbMain_CellMouseClick);
            ////this.tbMain.CellMouseDoubleClick += new Yss.KTable.Events.CellEventHandler(this.tbMain_CellMouseDoubleClick);
            // 
            // bar1
            // 
            this.bar1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(228)))), ((int)(((byte)(234)))), ((int)(((byte)(241)))));
            this.bar1.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.bar1.DockSide = YssDevComponents.DotNetBar.eDockSide.Document;
            this.bar1.DockTabAlignment = YssDevComponents.DotNetBar.eTabStripAlignment.Top;
            this.bar1.Items.AddRange(new YssDevComponents.DotNetBar.BaseItem[] {
            this.btnOk,
            this.btnQuit,
            this.labelItem1});
            this.bar1.Location = new System.Drawing.Point(0, 267);
            this.bar1.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.bar1.Name = "bar1";
            this.bar1.Size = new System.Drawing.Size(593, 35);
            this.bar1.Stretch = true;
            this.bar1.Style = YssDevComponents.DotNetBar.eDotNetBarStyle.Office2007;
            this.bar1.TabIndex = 12;
            this.bar1.TabStop = false;
            this.bar1.Text = "bar1";
            // 
            // btnOk
            // 
            this.btnOk.Image = ((System.Drawing.Image)(resources.GetObject("btnOk.Image")));
            this.btnOk.ItemAlignment = YssDevComponents.DotNetBar.eItemAlignment.Far;
            this.btnOk.Name = "btnOk";
            this.btnOk.Text = "确定";
            this.btnOk.Click += new System.EventHandler(this.btnOk_Click);
            // 
            // btnQuit
            // 
            this.btnQuit.Image = ((System.Drawing.Image)(resources.GetObject("btnQuit.Image")));
            this.btnQuit.ItemAlignment = YssDevComponents.DotNetBar.eItemAlignment.Far;
            this.btnQuit.Name = "btnQuit";
            this.btnQuit.Text = "取消";
            this.btnQuit.Click += new System.EventHandler(this.btnQuit_Click);
            // 
            // labelItem1
            // 
            this.labelItem1.Enabled = false;
            this.labelItem1.Name = "labelItem1";
            this.labelItem1.Text = "  ";
            // 
            // pnlList
            // 
            this.pnlList.Controls.Add(this.tbMain);
            this.pnlList.Dock = System.Windows.Forms.DockStyle.Fill;
            this.pnlList.Location = new System.Drawing.Point(0, 0);
            this.pnlList.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.pnlList.Name = "pnlList";
            this.pnlList.Size = new System.Drawing.Size(593, 302);
            this.pnlList.TabIndex = 1;
            // 
            // Frm_TRANSFER_PARA
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(593, 302);
            this.DoubleBuffered = true;
            this.Margin = new System.Windows.Forms.Padding(4, 4, 4, 4);
            this.Name = "Frm_TRANSFER_PARA";
            this.Text = "功能选项";
            this.YssStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.Load += new System.EventHandler(this.Frm_TRANSFER_PARA_Load);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.bar1)).EndInit();
            this.pnlList.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        public Yss.KTable.Models.Table tbMain;
        private YssDevComponents.DotNetBar.Bar bar1;
        private YssDevComponents.DotNetBar.ButtonItem btnOk;
        private YssDevComponents.DotNetBar.ButtonItem btnQuit;
        private YssDevComponents.DotNetBar.LabelItem labelItem1;
        private System.Windows.Forms.Panel pnlList;
    }
}