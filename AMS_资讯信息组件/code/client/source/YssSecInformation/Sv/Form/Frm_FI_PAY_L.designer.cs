using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;

using FAST.Core.Resource;
using FAST.Core.Context;
namespace YssSecInformation.Sv.Form
{
    partial class Frm_FI_PAY_L
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
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo1 = new FAST.Core.BaseControl.ControlMethodInfo();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_FI_PAY_L));
            this.cell2 = new Yss.KTable.Models.Cell();
            this.selSecurity = new FAST.Core.BaseControl.YssSelCombox();
            this.column2 = new Yss.KTable.Models.Column();
            this.column1 = new Yss.KTable.Models.Column();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.dtpResetInterestDate = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.label1 = new System.Windows.Forms.ToolStripLabel();
            this.tbFilter.SuspendLayout();
            this.pnlFilter.SuspendLayout();
            ////((System.ComponentModel.ISupportInitialize)(this.barFormStatus)).BeginInit();
            this.barFormStatus.SuspendLayout();
            this.pnlHost.SuspendLayout();
            this.navBarLeft.SuspendLayout();
            this.panelEx1.SuspendLayout();
            this.navigateItemMain.SuspendLayout();
            this.pnlLeftMain.SuspendLayout();
            this.pnlBarPort.SuspendLayout();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // tbFilter
            // 
            // 
            // 
            // 
            this.tbFilter.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.tbFilter.Border.Bottom = false;
            this.tbFilter.Border.Left = false;
            this.tbFilter.Border.Right = false;
            this.tbFilter.Border.Top = true;
            this.tbFilter.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.column1,
            this.column2,
            this.column3,
            this.column4,
            this.column5});
            this.tbFilter.Controls.Add(this.selSecurity);
            this.tbFilter.Controls.Add(this.dtpResetInterestDate);
            this.tbFilter.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2});
            this.tbFilter.Size = new System.Drawing.Size(641, 40);
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
            this.tbMain.Size = new System.Drawing.Size(641, 399);
            // 
            // pnlFilter
            // 
            this.pnlFilter.Size = new System.Drawing.Size(641, 40);
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
            ////this.barFormStatus.Controls.Add(this.label1);
            this.barFormStatus.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
                this.label1                
            });
            this.barFormStatus.Location = new System.Drawing.Point(0, 478);
            this.barFormStatus.Size = new System.Drawing.Size(641, 28);
            ////this.barFormStatus.Controls.SetChildIndex(this.label1, 0);
            ////this.barFormStatus.Controls.SetChildIndex(this.txtToPage, 0);
            ////this.barFormStatus.Controls.SetChildIndex(this.cboPageSize, 0);
            // 
            // proBar
            // 
            // 
            // 
            // 
            ////////////this.proBar.BackStyle.BorderBottom = YssDevComponents.DotNetBar.eStyleBorderType.DashDot;
            ////////////this.proBar.BackStyle.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(0)))), ((int)(((byte)(0)))));
            ////////////this.proBar.BackStyle.BorderLeft = YssDevComponents.DotNetBar.eStyleBorderType.DashDot;
            ////////////this.proBar.BackStyle.BorderRight = YssDevComponents.DotNetBar.eStyleBorderType.DashDot;
            ////////////this.proBar.BackStyle.BorderTop = YssDevComponents.DotNetBar.eStyleBorderType.DashDot;
            // 
            // pnlHost
            // 
            this.pnlHost.Size = new System.Drawing.Size(641, 399);
            // 
            // cboPageSize
            // 
            ////this.cboPageSize.Location = new System.Drawing.Point(336, 2);
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
            this.navBarLeft.Size = new System.Drawing.Size(245, 440);
            // 
            // tbLeftMain
            // 
            this.tbLeftMain.AllowColumnDrag = false;
            this.tbLeftMain.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(255)))));
            // 
            // 
            // 
            this.tbLeftMain.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.tbLeftMain.Border.Bottom = false;
            this.tbLeftMain.Border.Left = false;
            this.tbLeftMain.Border.Right = false;
            this.tbLeftMain.Border.Top = false;
            this.tbLeftMain.ShowCheckBox = true;
            this.tbLeftMain.Size = new System.Drawing.Size(243, 411);
            // 
            // splitLeft
            // 
            this.splitLeft.Size = new System.Drawing.Size(6, 506);
            // 
            // btnArrow
            // 
            // 
            // 
            // 
            this.btnArrow.BackgroundStyle.BorderTop = YssDevComponents.DotNetBar.eStyleBorderType.Solid;
            this.btnArrow.BackgroundStyle.BorderTopColor = System.Drawing.Color.FromArgb(((int)(((byte)(102)))), ((int)(((byte)(136)))), ((int)(((byte)(184)))));
            this.btnArrow.BackgroundStyle.BorderTopWidth = 1;
            this.btnArrow.Location = new System.Drawing.Point(616, 0);
            // 
            // panelEx1
            // 
            this.panelEx1.Size = new System.Drawing.Size(641, 33);
            this.panelEx1.Style.Alignment = System.Drawing.StringAlignment.Center;
            this.panelEx1.Style.BackColor1.Color = System.Drawing.Color.FromArgb(((int)(((byte)(228)))), ((int)(((byte)(234)))), ((int)(((byte)(241)))));
            this.panelEx1.Style.BackColor2.Color = System.Drawing.Color.FromArgb(((int)(((byte)(228)))), ((int)(((byte)(234)))), ((int)(((byte)(241)))));
            this.panelEx1.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            this.panelEx1.Style.BorderColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBorder;
            this.panelEx1.Style.BorderSide = YssDevComponents.DotNetBar.eBorderSide.None;
            this.panelEx1.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            this.panelEx1.Style.GradientAngle = 90;
            // 
            // txtToPage
            // 
            // 
            // 
            // 
            ////this.txtToPage.Border.Class = "TextBoxBorder";
            ////this.txtToPage.Location = new System.Drawing.Point(557, 2);
            // 
            // barPort
            // 
            // 
            // btnBar
            // 
            this.btnBar.Size = new System.Drawing.Size(616, 33);
            // 
            // navigateItemMain
            // 
            this.navigateItemMain.Text = "交易市场";
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
            this.pnlLeftMain.Size = new System.Drawing.Size(247, 506);
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
            this.pnlBarPort.Location = new System.Drawing.Point(1, 478);
            // 
            // pnlMain
            // 
            this.pnlMain.Size = new System.Drawing.Size(641, 506);
            //this.pnlMain.Style.Alignment = System.Drawing.StringAlignment.Center;
            //this.pnlMain.Style.BackColor1.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.DockSiteBackColor;
            //this.pnlMain.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            //this.pnlMain.Style.BorderColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBorder;
            //this.pnlMain.Style.BorderSide = YssDevComponents.DotNetBar.eBorderSide.Top;
            //this.pnlMain.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            //this.pnlMain.Style.GradientAngle = 90;
            // 
            // yssPanel1
            // 
            this.yssPanel1.Size = new System.Drawing.Size(894, 506);
            // 
            // hpAssist
            // 
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.selSecurity;
            // 
            // selSecurity
            // 
            this.selSecurity.AddedSelItemName = "";
            this.selSecurity.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.selSecurity.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.selSecurity.Border.Bottom = true;
            this.selSecurity.Border.Left = true;
            this.selSecurity.Border.Right = true;
            this.selSecurity.Border.Top = true;
            this.selSecurity.ClassName = "";
            this.selSecurity.DisplayStyle = Yss.KRichEx.AutoFilter.DisplayStyle.Modal;
            this.selSecurity.DllName = "YssControls.dll";
            this.selSecurity.FilterCond = "";
            this.selSecurity.Location = new System.Drawing.Point(86, 7);
            this.selSecurity.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataListByTypes";
            controlMethodInfo1.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo1.MethodParams")));
            controlMethodInfo1.MethodParamValues = new string[] {
        "ZQ,LC"};
            controlMethodInfo1.Methods = null;
            this.selSecurity.MethodInfo = controlMethodInfo1;
            this.selSecurity.Name = "selSecurity";
            this.selSecurity.QueryCond = "ZQ";
            this.selSecurity.QueryType = "CacheType";
            this.selSecurity.Size = new System.Drawing.Size(121, 21);
            this.selSecurity.TabIndex = 1;
            this.selSecurity.Tag = this.cell2;
            this.selSecurity.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Value_Name;
            this.selSecurity.YssAssociaType = YssSecInformation.Support.Context.AssociaType.sv_sec;
            this.selSecurity.YssCaption = "交易证券";
            // 
            // column2
            // 
            this.column2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column2.Width = 122;
            // 
            // column1
            // 
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.Width = 86;
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row1.FullRowSelected = false;
            this.row1.GroupPosition = -16;
            this.row1.Height = 7;
            this.row1.Text = null;
            // 
            // row2
            // 
            this.row2.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell1,
            this.cell2,
            this.cell3,
            this.cell4,
            this.cell5});
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row2.FullRowSelected = false;
            this.row2.GroupPosition = -16;
            this.row2.Height = 25;
            this.row2.Text = null;
            // 
            // cell1
            // 
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.Text = "交易证券：";
            this.cell1.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // cell3
            // 
            this.cell3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell3.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell4
            // 
            this.cell4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell4.Text = "调 息 日：";
            this.cell4.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
            // 
            // cell5
            // 
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.dtpResetInterestDate;
            // 
            // dtpResetInterestDate
            // 
            this.dtpResetInterestDate.BackColor = System.Drawing.Color.Transparent;
            this.dtpResetInterestDate.DateBeginChecked = true;
            this.dtpResetInterestDate.DateEndChecked = true;
            this.dtpResetInterestDate.Location = new System.Drawing.Point(304, 7);
            this.dtpResetInterestDate.Margin = new System.Windows.Forms.Padding(0);
            this.dtpResetInterestDate.Name = "dtpResetInterestDate";
            this.dtpResetInterestDate.Size = new System.Drawing.Size(229, 21);
            this.dtpResetInterestDate.TabIndex = 2;
            this.dtpResetInterestDate.Tag = this.cell5;
            this.dtpResetInterestDate.yssEnabled = true;
            this.dtpResetInterestDate.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpResetInterestDate.yssInterval = true;
            this.dtpResetInterestDate.yssLabelStr = "至";
            this.dtpResetInterestDate.yssShowCheckBox = false;
            this.dtpResetInterestDate.yssShowOperLable = false;
            this.dtpResetInterestDate.YssShowSecond = true;
            this.dtpResetInterestDate.yssTimeControl = false;
            this.dtpResetInterestDate.BusinessDate = true;
            // 
            // column3
            // 
            this.column3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column3.Width = 30;
            // 
            // column4
            // 
            this.column4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column4.Width = 66;
            // 
            // column5
            // 
            this.column5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column5.Width = 230;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            ////this.label1.Location = new System.Drawing.Point(6, 4);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(0, 17);
            ////this.label1.TabIndex = 10004;
            // 
            // Frm_FI_PAY_L
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(894, 506);
            this.DoubleBuffered = true;
            this.Name = "Frm_FI_PAY_L";
            this.ShowFilterPanel = true;
            this.ShowLeftSearchPanel = true;
            this.Text = "历史付息信息";
            this.Load += new System.EventHandler(this.Frm_FI_PAY_L_Load);
            this.tbFilter.ResumeLayout(false);
            this.pnlFilter.ResumeLayout(false);
            ////((System.ComponentModel.ISupportInitialize)(this.barFormStatus)).EndInit();
            this.barFormStatus.ResumeLayout(false);
            this.barFormStatus.PerformLayout();
            this.pnlHost.ResumeLayout(false);
            this.navBarLeft.ResumeLayout(false);
            this.panelEx1.ResumeLayout(false);
            this.navigateItemMain.ResumeLayout(false);
            this.pnlLeftMain.ResumeLayout(false);
            this.pnlBarPort.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
        private Yss.KTable.Models.Row row1;
        private Yss.KTable.Models.Row row2;
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Cell cell2;
        private Yss.KTable.Models.Column column3;
        private Yss.KTable.Models.Column column4;
        private Yss.KTable.Models.Column column5;
        private Yss.KTable.Models.Cell cell3;
        private Yss.KTable.Models.Cell cell4;
        private Yss.KTable.Models.Cell cell5;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpResetInterestDate;
        private FAST.Core.BaseControl.YssSelCombox selSecurity;
        private System.Windows.Forms.ToolStripLabel label1;
    }
}