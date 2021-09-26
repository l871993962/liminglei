using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;

using FAST.Core.Resource;
using FAST.Core.Context;
namespace YssInformation.Bi.SourceRecord.Form
{
    partial class Frm_SRC_SIGN_S
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_SRC_SIGN_S));
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.row3 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.txtSignCode = new Yss.KRichEx.YssTextBox();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.txtSignName = new Yss.KRichEx.YssTextBox();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.tbMain.SuspendLayout();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // stBarPnl
            // 
            this.StatuType = "新增(&Add...)";
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
            this.tbMain.Border.Top = false;
            this.tbMain.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.column1,
            this.column2,
            this.column3,
            this.column4,
            this.column5});
            this.tbMain.Controls.Add(this.txtSignCode);
            this.tbMain.Controls.Add(this.txtSignName);
            // 
            // 
            // 
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(493, 95);
            // 
            // stBarBottom
            // 
            this.stBarBottom.Location = new System.Drawing.Point(0, 125);
            // 
            // panelExLine
            // 
            // 
            // pnlMain
            // 
            this.pnlMain.Size = new System.Drawing.Size(493, 148);
            //this.pnlMain.Style.Alignment = System.Drawing.StringAlignment.Center;
            //this.pnlMain.Style.BackColor1.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.DockSiteBackColor;
            //this.pnlMain.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            //this.pnlMain.Style.BorderColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBorder;
            //this.pnlMain.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            //this.pnlMain.Style.GradientAngle = 90;
            // 
            // yssPanel1
            // 
            this.yssPanel1.Size = new System.Drawing.Size(493, 148);
            // 
            // hpAssist
            // 
            // 
            // column1
            // 
            this.column1.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column1.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column1.DataPropertyName = null;
            this.column1.ForeColor = System.Drawing.Color.Empty;
            this.column1.Tag = null;
            this.column1.Text = "";
            this.column1.Width = 110;
            // 
            // column2
            // 
            this.column2.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column2.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column2.DataPropertyName = null;
            this.column2.ForeColor = System.Drawing.Color.Empty;
            this.column2.Tag = null;
            this.column2.Text = "";
            this.column2.Width = 122;
            // 
            // column3
            // 
            this.column3.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column3.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column3.DataPropertyName = null;
            this.column3.ForeColor = System.Drawing.Color.Empty;
            this.column3.Tag = null;
            this.column3.Text = "";
            this.column3.Width = 30;
            // 
            // column4
            // 
            this.column4.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column4.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column4.DataPropertyName = null;
            this.column4.ForeColor = System.Drawing.Color.Empty;
            this.column4.Tag = null;
            this.column4.Text = "";
            this.column4.Width = 91;
            // 
            // column5
            // 
            this.column5.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column5.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column5.DataPropertyName = null;
            this.column5.ForeColor = System.Drawing.Color.Empty;
            this.column5.Tag = null;
            this.column5.Text = "";
            this.column5.Width = 122;
            // 
            // row3
            // 
            this.row3.BackColor = System.Drawing.Color.Empty;
            this.row3.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell1,
            this.cell2,
            this.cell3,
            this.cell4,
            this.cell5});
            this.row3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row3.ForeColor = System.Drawing.Color.Black;
            this.row3.FullRowSelected = true;
            this.row3.GroupLineLength = 310;
            this.row3.GroupPosition = 13;
            this.row3.Height = 25;
            this.row3.Key = null;
            this.row3.OwnTable = this.tbMain;
            this.row3.ShowCheckBox = true;
            // 
            // cell1
            // 
            this.cell1.BackColor = System.Drawing.Color.Empty;
            this.cell1.CellEditStatus = false;
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.Key = null;
            this.cell1.Text = "   标识代码：";
            this.cell1.ToolTip = null;
            // 
            // cell2
            // 
            this.cell2.BackColor = System.Drawing.Color.Empty;
            this.cell2.CellEditStatus = false;
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.txtSignCode;
            this.cell2.Key = null;
            this.cell2.ToolTip = null;
            // 
            // txtSignCode
            // 
            this.txtSignCode.Border.Class = "TextBoxBorder";
            this.txtSignCode.Location = new System.Drawing.Point(111, 44);
            this.txtSignCode.Name = "txtSignCode";
            this.txtSignCode.Size = new System.Drawing.Size(120, 21);
            this.txtSignCode.TabIndex = 2;
            this.txtSignCode.Value = "";
            this.txtSignCode.YssCaption = "标识代码";
            this.txtSignCode.YssIsMust = true;
            this.txtSignCode.YssNumeric = "";
            // 
            // cell3
            // 
            this.cell3.BackColor = System.Drawing.Color.Empty;
            this.cell3.CellEditStatus = false;
            this.cell3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell3.ForeColor = System.Drawing.Color.Black;
            this.cell3.Key = null;
            this.cell3.ToolTip = null;
            // 
            // cell4
            // 
            this.cell4.BackColor = System.Drawing.Color.Empty;
            this.cell4.CellEditStatus = false;
            this.cell4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell4.ForeColor = System.Drawing.Color.Black;
            this.cell4.Key = null;
            this.cell4.Text = "标识名称：";
            this.cell4.ToolTip = null;
            // 
            // cell5
            // 
            this.cell5.BackColor = System.Drawing.Color.Empty;
            this.cell5.CellEditStatus = false;
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.Color.Black;
            this.cell5.InnerControl = this.txtSignName;
            this.cell5.Key = null;
            this.cell5.ToolTip = null;
            // 
            // txtSignName
            // 
            this.txtSignName.Border.Class = "TextBoxBorder";
            this.txtSignName.Location = new System.Drawing.Point(354, 44);
            this.txtSignName.Name = "txtSignName";
            this.txtSignName.Size = new System.Drawing.Size(120, 21);
            this.txtSignName.TabIndex = 2;
            this.txtSignName.Value = "";
            this.txtSignName.YssCaption = "标识名称";
            this.txtSignName.YssIsMust = true;
            this.txtSignName.YssLength = 50;
            this.txtSignName.YssNumeric = "";
            // 
            // row1
            // 
            this.row1.BackColor = System.Drawing.Color.Empty;
            this.row1.Font = new System.Drawing.Font("新宋体", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row1.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row1.FullRowSelected = true;
            this.row1.GroupLineLength = 310;
            this.row1.GroupPosition = 13;
            this.row1.Height = 33;
            this.row1.IsGroup = true;
            this.row1.Key = null;
            this.row1.OwnTable = this.tbMain;
            this.row1.ShowCheckBox = true;
            this.row1.Text = "基本信息";
            // 
            // row2
            // 
            this.row2.BackColor = System.Drawing.Color.Empty;
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row2.ForeColor = System.Drawing.Color.Black;
            this.row2.FullRowSelected = true;
            this.row2.GroupLineLength = 310;
            this.row2.GroupPosition = 13;
            this.row2.Height = 10;
            this.row2.Key = null;
            this.row2.OwnTable = this.tbMain;
            this.row2.ShowCheckBox = true;
            // 
            // cell7
            // 
            this.cell7.BackColor = System.Drawing.Color.Empty;
            this.cell7.CellEditStatus = false;
            this.cell7.ColSpan = 2;
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.Key = null;
            this.cell7.ToolTip = null;
            // 
            // Frm_SRC_SIGN_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(493, 148);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "Frm_SRC_SIGN_S";
            this.Text = "来源标识设置";
            this.YssSetStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.Load += new System.EventHandler(this.Frm_SRC_SIGN_S_Load);
            this.tbMain.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
        private Yss.KTable.Models.Column column3;
        private Yss.KTable.Models.Column column4;
        private Yss.KTable.Models.Column column5;
        private Yss.KRichEx.YssTextBox txtSignCode;
        private Yss.KRichEx.YssTextBox txtSignName;
        private Yss.KTable.Models.Row row3;
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Cell cell2;
        private Yss.KTable.Models.Row row1;
        private Yss.KTable.Models.Row row2;
        private Yss.KTable.Models.Cell cell7;
        private Yss.KTable.Models.Cell cell3;
        private Yss.KTable.Models.Cell cell4;
        private Yss.KTable.Models.Cell cell5;
    }
}