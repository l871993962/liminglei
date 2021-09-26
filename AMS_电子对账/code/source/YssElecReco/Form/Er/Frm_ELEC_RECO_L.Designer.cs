

using FAST.Common.Service.Pojo;
namespace YssElecReco.Form.Er
{
    partial class Frm_ELEC_RECO_L
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
            SysFun cls_BaseFun1 = new SysFun();
            ClsAssocia clsAssocia1 = new ClsAssocia();
            this.panelTop.SuspendLayout();
            this.pnlParams.SuspendLayout();
            this.pnlFilter.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.barFormStatus)).BeginInit();
            this.pnlHost.SuspendLayout();
            this.navBarLeft.SuspendLayout();
            this.panelEx1.SuspendLayout();
            this.navigateItemMain.SuspendLayout();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // panelTop
            // 
            this.panelTop.Location = new System.Drawing.Point(0, 39);
            this.panelTop.Style.Alignment = System.Drawing.StringAlignment.Center;
            this.panelTop.Style.BackColor1.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.BarBackground;
            this.panelTop.Style.BackColor2.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.BarBackground2;
            this.panelTop.Style.BorderColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.BarDockedBorder;
            this.panelTop.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.ItemText;
            this.panelTop.Style.GradientAngle = 90;
            // 
            // pnlParams
            // 
            this.pnlParams.Location = new System.Drawing.Point(0, 80);
            this.pnlParams.Style.Alignment = System.Drawing.StringAlignment.Center;
            this.pnlParams.Style.BackColor1.Color = System.Drawing.Color.FromArgb(((int)(((byte)(233)))), ((int)(((byte)(238)))), ((int)(((byte)(244)))));
            this.pnlParams.Style.BackColor2.Color = System.Drawing.Color.FromArgb(((int)(((byte)(233)))), ((int)(((byte)(238)))), ((int)(((byte)(244)))));
            this.pnlParams.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            this.pnlParams.Style.BorderColor.Color = System.Drawing.Color.FromArgb(((int)(((byte)(102)))), ((int)(((byte)(136)))), ((int)(((byte)(184)))));
            this.pnlParams.Style.BorderSide = YssDevComponents.DotNetBar.eBorderSide.None;
            this.pnlParams.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            this.pnlParams.Style.GradientAngle = 90;
            // 
            // lblDate
            // 
            this.lblDate.Size = new System.Drawing.Size(44, 18);
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
            // 
            // 
            // 
            this.tbFilter.Size = new System.Drawing.Size(796, 77);
            // 
            // tbMain
            // 
            ////this.tbMain.AlternatingRowColor = System.Drawing.Color.FromArgb(((int)(((byte)(232)))), ((int)(((byte)(237)))), ((int)(((byte)(241)))));
            // 
            // 
            // 
            this.tbMain.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(111)))), ((int)(((byte)(157)))), ((int)(((byte)(217)))));
            this.tbMain.Border.Bottom = true;
            this.tbMain.Border.Left = false;
            this.tbMain.Border.Right = false;
            this.tbMain.Border.Top = true;
            this.tbMain.ExpandMode = Yss.KTable.Enums.ExpandMode.PlusMinusOnly;
            // 
            // 
            // 
            this.tbMain.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IndentOnly;
            this.tbMain.SelectionMode = System.Windows.Forms.SelectionMode.None;
            this.tbMain.ShowCheckBox = false;
            this.tbMain.Size = new System.Drawing.Size(796, 499);
            this.tbMain.CheckStateChanged += new Yss.KTable.Events.CheckStateChanged(this.tbMain_CheckStateChanged);
            // 
            // pnlFilter
            // 
            this.pnlFilter.Location = new System.Drawing.Point(0, 80);
            this.pnlFilter.Style.Alignment = System.Drawing.StringAlignment.Center;
            this.pnlFilter.Style.BackColor1.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBackground;
            this.pnlFilter.Style.BackColor2.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBackground2;
            this.pnlFilter.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            this.pnlFilter.Style.BorderColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBorder;
            this.pnlFilter.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            this.pnlFilter.Style.GradientAngle = 90;
            // 
            // pnlHost
            // 
            this.pnlHost.Location = new System.Drawing.Point(0, 120);
            this.pnlHost.Size = new System.Drawing.Size(796, 499);
            // 
            // txtSearch
            // 
            // 
            // 
            // 
            this.txtSearch.PrefixForeColor = System.Drawing.Color.Gray;
            // 
            // 
            // 
            // 
            // pnlLeftMain
            // 
            // 
            // 
            // 
            // 
            // tbLeftMain
            // 
            ////this.tbLeftMain.AlternatingRowColor = System.Drawing.Color.FromArgb(((int)(((byte)(232)))), ((int)(((byte)(237)))), ((int)(((byte)(241)))));
            // 
            // 
            // 
            this.tbLeftMain.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(158)))), ((int)(((byte)(163)))), ((int)(((byte)(166)))));
            this.tbLeftMain.Border.Bottom = false;
            this.tbLeftMain.Border.Left = false;
            this.tbLeftMain.Border.Right = false;
            this.tbLeftMain.Border.Top = false;
            // 
            // 
            // 
            // 
            // btnArrow
            // 
            // 
            // 
            // 
            this.btnArrow.BackgroundStyle.BorderTop = YssDevComponents.DotNetBar.eStyleBorderType.Solid;
            this.btnArrow.BackgroundStyle.BorderTopColor = System.Drawing.Color.FromArgb(((int)(((byte)(102)))), ((int)(((byte)(136)))), ((int)(((byte)(184)))));
            this.btnArrow.BackgroundStyle.BorderTopWidth = 1;
            // 
            // panelEx1
            // 
            this.panelEx1.Style.Alignment = System.Drawing.StringAlignment.Center;
            this.panelEx1.Style.BackColor1.Color = System.Drawing.Color.FromArgb(((int)(((byte)(228)))), ((int)(((byte)(234)))), ((int)(((byte)(241)))));
            this.panelEx1.Style.BackColor2.Color = System.Drawing.Color.FromArgb(((int)(((byte)(228)))), ((int)(((byte)(234)))), ((int)(((byte)(241)))));
            this.panelEx1.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            this.panelEx1.Style.BorderColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBorder;
            this.panelEx1.Style.BorderSide = YssDevComponents.DotNetBar.eBorderSide.None;
            this.panelEx1.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            this.panelEx1.Style.GradientAngle = 90;
            // 
            // navigateExpandablePanel1
            // 
            // 
            // 
            // 
            // 
            // 
            // 
            this.navigateItemMain.Controls.SetChildIndex(this.tbLeftMain, 0);
            // 
            // pnlMain
            // 
            // 
            // Frm_ELEC_RECO_L
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(970, 645);
            this.Name = "Frm_ELEC_RECO_L";
            this.Text = "产生电子对账数据";
            cls_BaseFun1.C_DV_FUN_TYPE = "";
            cls_BaseFun1.C_DV_STATE = "";
            cls_BaseFun1.C_FUN_CODE = "";
            cls_BaseFun1.C_FUN_CODE_P = "";
            cls_BaseFun1.C_FUN_NAME = "";
            cls_BaseFun1.C_ICO_FILE = "";
            cls_BaseFun1.C_SRC_MARK = "";
            cls_BaseFun1.N_CHECK = 0;
            cls_BaseFun1.N_LEVEL = 1;
            cls_BaseFun1.N_LOCK = 0;
            cls_BaseFun1.N_RECYCLE = 0;
            cls_BaseFun1.N_USER = 0;
            cls_BaseFun1.OrderIndex = 0;
            clsAssocia1.CheckStateId = 0;
            clsAssocia1.CheckStateName = "";
            clsAssocia1.CheckTime = "";
            clsAssocia1.CheckUserCode = "";
            clsAssocia1.CheckUserName = "";
            clsAssocia1.CommonDataClass = "";
            clsAssocia1.CommonDataClassDll = "";
            clsAssocia1.CreatorCode = "";
            clsAssocia1.CreatorName = "";
            clsAssocia1.CreatorTime = "";
            clsAssocia1.Ctl_Ds_From_DB = true;
            clsAssocia1.DataClass = "";
            clsAssocia1.DataClassDll = "";
            clsAssocia1.FunCode = "";
            clsAssocia1.FunName = "";
            clsAssocia1.KeyValue = "";
            clsAssocia1.ListDllName = "";
            clsAssocia1.ListFormName = "";
            clsAssocia1.PojoClsName = "";
            clsAssocia1.Sel_DisplayColumns = "";
            clsAssocia1.Sel_DisplayName = "";
            clsAssocia1.Sel_DisplayValue = "";
            clsAssocia1.ServiceDllName = "";
            clsAssocia1.ServiceName = "";
            clsAssocia1.SetDllName = "";
            clsAssocia1.SetFormName = "";
            clsAssocia1.URL = "";
            cls_BaseFun1.YssAssocia = clsAssocia1;
            this.YssFormMenu = cls_BaseFun1;
            this.Load += new System.EventHandler(this.Frm_ELEC_RECO_L_Load);
            this.panelTop.ResumeLayout(false);
            this.pnlParams.ResumeLayout(false);
            this.pnlParams.PerformLayout();
            this.pnlFilter.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.barFormStatus)).EndInit();
            this.pnlHost.ResumeLayout(false);
            this.navBarLeft.ResumeLayout(false);
            this.panelEx1.ResumeLayout(false);
            this.navigateItemMain.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion
    }
}