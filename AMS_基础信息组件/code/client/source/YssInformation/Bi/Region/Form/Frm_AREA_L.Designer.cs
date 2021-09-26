using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;

namespace YssInformation.Bi.Region.Form
{
    partial class Frm_AREA_L
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_AREA_L));
            ClsPageInation clsPageInation2 = new ClsPageInation();
            this.pnlFilter.SuspendLayout();
            ////((System.ComponentModel.ISupportInitialize)(this.barFormStatus)).BeginInit();
            this.barFormStatus.SuspendLayout();
            this.pnlHost.SuspendLayout();
            this.pnlLeftMain.SuspendLayout();
            this.panelEx1.SuspendLayout();
            this.navigateItemMain.SuspendLayout();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // tbMain
            // 
            this.tbMain.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(255)))));
            // 
            // 
            // 
            this.tbMain.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.tbMain.Border.Bottom = true;
            this.tbMain.Border.Left = false;
            this.tbMain.Border.Right = false;
            this.tbMain.Border.Top = true;
            this.tbMain.DefaultToolStripItems = ((Yss.KTable.Enums.SysToolStripItems)((((Yss.KTable.Enums.SysToolStripItems.ShowHideColumn | Yss.KTable.Enums.SysToolStripItems.GroupByColumn)
                        | Yss.KTable.Enums.SysToolStripItems.PrintSetup)
                        | Yss.KTable.Enums.SysToolStripItems.PrintPreview)));
            // 
            // 
            // 
            this.tbMain.Size = new System.Drawing.Size(912, 451);
            // 
            // Frm_AREA_L
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(912, 516);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "Frm_AREA_L";
            clsPageInation2.bCreateQuery = true;
            clsPageInation2.CurrPage = 1;
            clsPageInation2.IsUsePage = true;
            clsPageInation2.PageCount = -1;
            clsPageInation2.PageSize = 100;
            clsPageInation2.sQueryStr = "";
            clsPageInation2.sUrl = "";
            clsPageInation2.TotalNum = 0;
            this.Page = clsPageInation2;
            this.ShowLeftPanel = false;
            this.Text = "地区信息";
            this.Load += new System.EventHandler(this.Frm_AREA_L_Load);
            this.pnlFilter.ResumeLayout(false);
            ////((System.ComponentModel.ISupportInitialize)(this.barFormStatus)).EndInit();
            this.barFormStatus.ResumeLayout(false);
            this.pnlHost.ResumeLayout(false);
            this.pnlLeftMain.ResumeLayout(false);
            this.panelEx1.ResumeLayout(false);
            this.navigateItemMain.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        ////private Yss.KTable.Models.Column column1;
        ////private Yss.KTable.Models.Column column2;
        ////private Yss.KTable.Models.Column column3;
        ////private Yss.KTable.Models.Column column4;
        ////private Yss.KTable.Models.Column column5;
        ////private Yss.KTable.Models.Row row1;
        ////private Yss.KTable.Models.Row row2;
        ////private Yss.KTable.Models.Cell cell1;
        ////private Yss.KTable.Models.Cell cell2;
        ////private Yss.KTable.Models.Cell cell3;
        ////private Yss.KTable.Models.Cell cell4;
        ////private Yss.KTable.Models.Cell cell5;
        //private Yss.KRichEx.YssTextBox txtAlgoCode;
    }
}