using FAST.Core.BaseForm;
namespace YssElecReco.Form.Er
{
    partial class Frm_ELEC_GENE_L
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
            this.panelTop.SuspendLayout();
            this.pnlParams.SuspendLayout();
            this.pnlFilter.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.barFormStatus)).BeginInit();
            this.pnlHost.SuspendLayout();
            this.navBarLeft.SuspendLayout();
            this.panelEx1.SuspendLayout();
            this.navigateItemMain.SuspendLayout();
            this.pnlLeftMain.SuspendLayout();
            this.pnlBarPort.SuspendLayout();
            this.pnlSearchLeft.SuspendLayout();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // panelTop
            // 
            this.panelTop.Location = new System.Drawing.Point(0, 39);
            this.panelTop.Size = new System.Drawing.Size(659, 33);
            this.panelTop.Style.Alignment = System.Drawing.StringAlignment.Center;
            this.panelTop.Style.BackColor1.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.BarBackground;
            this.panelTop.Style.BackColor2.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.BarBackground2;
            this.panelTop.Style.BorderColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.BarDockedBorder;
            this.panelTop.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.ItemText;
            this.panelTop.Style.GradientAngle = 90;
            // 
            // pnlParams
            // 
            this.pnlParams.Location = new System.Drawing.Point(0, 157);
            this.pnlParams.Size = new System.Drawing.Size(659, 40);
            this.pnlParams.Style.Alignment = System.Drawing.StringAlignment.Center;
            this.pnlParams.Style.BackColor1.Color = System.Drawing.Color.FromArgb(((int)(((byte)(233)))), ((int)(((byte)(238)))), ((int)(((byte)(244)))));
            this.pnlParams.Style.BackColor2.Color = System.Drawing.Color.FromArgb(((int)(((byte)(233)))), ((int)(((byte)(238)))), ((int)(((byte)(244)))));
            this.pnlParams.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            this.pnlParams.Style.BorderColor.Color = System.Drawing.Color.FromArgb(((int)(((byte)(102)))), ((int)(((byte)(136)))), ((int)(((byte)(184)))));
            this.pnlParams.Style.BorderSide = YssDevComponents.DotNetBar.eBorderSide.None;
            this.pnlParams.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            this.pnlParams.Style.GradientAngle = 90;
            // 
            // yssDateTime
            // 
            this.yssDateTime.BusinessDate = true;
            // 
            // lblDate
            // 
            this.lblDate.Size = new System.Drawing.Size(44, 18);
            // 
            // btnBarOper
            // 
            this.btnBarOper.Size = new System.Drawing.Size(659, 33);
            // 
            // pnlLog
            // 
            this.pnlLog.Location = new System.Drawing.Point(0, 280);
            this.pnlLog.Size = new System.Drawing.Size(659, 131);
            this.pnlLog.Style.Alignment = System.Drawing.StringAlignment.Center;
            this.pnlLog.Style.BackColor1.Color = System.Drawing.Color.FromArgb(((int)(((byte)(233)))), ((int)(((byte)(238)))), ((int)(((byte)(244)))));
            this.pnlLog.Style.BackColor2.Color = System.Drawing.Color.FromArgb(((int)(((byte)(233)))), ((int)(((byte)(238)))), ((int)(((byte)(244)))));
            this.pnlLog.Style.BorderColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBorder;
            this.pnlLog.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            this.pnlLog.Style.GradientAngle = 90;
            // 
            // expandableSplitterX1
            // 
            this.expandableSplitterX1.Location = new System.Drawing.Point(0, 274);
            this.expandableSplitterX1.Size = new System.Drawing.Size(659, 6);
            // 
            // tbFilter
            // 
            // 
            // 
            // 
            this.tbFilter.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(102)))), ((int)(((byte)(136)))), ((int)(((byte)(184)))));
            this.tbFilter.Border.Bottom = false;
            this.tbFilter.Border.Left = false;
            this.tbFilter.Border.Right = false;
            this.tbFilter.Border.Top = true;
            this.tbFilter.Size = new System.Drawing.Size(659, 77);
            // 
            // tbMain
            // 
            // 
            // 
            // 
            this.tbMain.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(111)))), ((int)(((byte)(157)))), ((int)(((byte)(217)))));
            this.tbMain.Border.Bottom = true;
            this.tbMain.Border.Left = false;
            this.tbMain.Border.Right = false;
            this.tbMain.Border.Top = true;
            this.tbMain.ExpandMode = Yss.KTable.Enums.ExpandMode.PlusMinusOnly;
            this.tbMain.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IndentOnly;
            this.tbMain.SelectionMode = System.Windows.Forms.SelectionMode.None;
            this.tbMain.ShowCheckBox = false;
            this.tbMain.Size = new System.Drawing.Size(659, 274);
            this.tbMain.CheckStateChanged += new Yss.KTable.Events.CheckStateChanged(this.tbMain_CheckStateChanged);
            // 
            // pnlFilter
            // 
            this.pnlFilter.Location = new System.Drawing.Point(0, 80);
            this.pnlFilter.Size = new System.Drawing.Size(659, 77);
            this.pnlFilter.Style.Alignment = System.Drawing.StringAlignment.Center;
            this.pnlFilter.Style.BackColor1.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBackground;
            this.pnlFilter.Style.BackColor2.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBackground2;
            this.pnlFilter.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            this.pnlFilter.Style.BorderColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBorder;
            this.pnlFilter.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            this.pnlFilter.Style.GradientAngle = 90;
            // 
            // barFormStatus
            // 
            this.barFormStatus.Location = new System.Drawing.Point(0, 608);
            this.barFormStatus.Size = new System.Drawing.Size(659, 26);
            // 
            // pnlHost
            // 
            this.pnlHost.Location = new System.Drawing.Point(0, 197);
            this.pnlHost.Size = new System.Drawing.Size(659, 411);
            // 
            // txtSearch
            // 
            // 
            // 
            // 
            this.txtSearch.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.txtSearch.Border.Bottom = true;
            this.txtSearch.Border.Left = true;
            this.txtSearch.Border.Right = true;
            this.txtSearch.Border.Top = true;
            this.txtSearch.PrefixBackColor = System.Drawing.Color.Transparent;
            // 
            // navBarLeft
            // 
            // 
            // 
            // 
            this.navBarLeft.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(164)))), ((int)(((byte)(187)))), ((int)(((byte)(217)))));
            this.navBarLeft.Border.Bottom = true;
            this.navBarLeft.Border.Left = true;
            this.navBarLeft.Border.Right = true;
            this.navBarLeft.Border.Top = true;
            this.navBarLeft.Size = new System.Drawing.Size(245, 568);
            // 
            // tbLeftMain
            // 
            // 
            // 
            // 
            this.tbLeftMain.Border.Bottom = false;
            this.tbLeftMain.Border.Left = false;
            this.tbLeftMain.Border.Right = false;
            this.tbLeftMain.Border.Top = false;
            this.tbLeftMain.Size = new System.Drawing.Size(243, 539);
            // 
            // splitLeft
            // 
            this.splitLeft.Location = new System.Drawing.Point(247, 0);
            this.splitLeft.Size = new System.Drawing.Size(6, 634);
            // 
            // btnArrow
            // 
            // 
            // 
            // 
            this.btnArrow.BackgroundStyle.BorderTop = YssDevComponents.DotNetBar.eStyleBorderType.Solid;
            this.btnArrow.BackgroundStyle.BorderTopColor = System.Drawing.Color.FromArgb(((int)(((byte)(102)))), ((int)(((byte)(136)))), ((int)(((byte)(184)))));
            this.btnArrow.BackgroundStyle.BorderTopWidth = 1;
            this.btnArrow.Location = new System.Drawing.Point(631, 0);
            // 
            // panelEx1
            // 
            this.panelEx1.Size = new System.Drawing.Size(659, 33);
            this.panelEx1.Style.Alignment = System.Drawing.StringAlignment.Center;
            this.panelEx1.Style.BackColor1.Color = System.Drawing.Color.FromArgb(((int)(((byte)(228)))), ((int)(((byte)(234)))), ((int)(((byte)(241)))));
            this.panelEx1.Style.BackColor2.Color = System.Drawing.Color.FromArgb(((int)(((byte)(228)))), ((int)(((byte)(234)))), ((int)(((byte)(241)))));
            this.panelEx1.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            this.panelEx1.Style.BorderColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBorder;
            this.panelEx1.Style.BorderSide = YssDevComponents.DotNetBar.eBorderSide.None;
            this.panelEx1.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            this.panelEx1.Style.GradientAngle = 90;
            // 
            // barPort
            // 
            // 
            // btnBar
            // 
            this.btnBar.Size = new System.Drawing.Size(631, 33);
            this.navigateItemMain.Controls.SetChildIndex(this.tbLeftMain, 0);
            // 
            // pnlLeftMain
            // 
            // 
            // 
            // 
            this.pnlLeftMain.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.pnlLeftMain.Border.Bottom = false;
            this.pnlLeftMain.Border.Left = false;
            this.pnlLeftMain.Border.Right = false;
            this.pnlLeftMain.Border.Top = false;
            this.pnlLeftMain.Size = new System.Drawing.Size(247, 634);
            // 
            // pnlBarPort
            // 
            // 
            // 
            // 
            this.pnlBarPort.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.pnlBarPort.Border.Bottom = false;
            this.pnlBarPort.Border.Left = false;
            this.pnlBarPort.Border.Right = false;
            this.pnlBarPort.Border.Top = false;
            this.pnlBarPort.Location = new System.Drawing.Point(1, 606);
            // 
            // pnlMain
            // 
            this.pnlMain.Size = new System.Drawing.Size(659, 634);
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
            this.yssPanel1.Size = new System.Drawing.Size(912, 634);
            // 
            // Frm_ELEC_GENE_L
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(912, 634);
            this.DoubleBuffered = true;
            this.Name = "Frm_ELEC_GENE_L";
            this.ShowLeftSearchPanel = true;
            this.Text = "生成电子对账信息";
            this.YssOnFrmClose += new FrmBase.FrmClose(this.Frm_ELEC_GENE_L_YssOnFrmClose);
            this.Load += new System.EventHandler(this.Frm_ELEC_GENE_L_Load);
            this.panelTop.ResumeLayout(false);
            this.pnlParams.ResumeLayout(false);
            this.pnlParams.PerformLayout();
            this.pnlFilter.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.barFormStatus)).EndInit();
            this.pnlHost.ResumeLayout(false);
            this.navBarLeft.ResumeLayout(false);
            this.panelEx1.ResumeLayout(false);
            this.navigateItemMain.ResumeLayout(false);
            this.pnlLeftMain.ResumeLayout(false);
            this.pnlBarPort.ResumeLayout(false);
            this.pnlSearchLeft.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion
    }
}