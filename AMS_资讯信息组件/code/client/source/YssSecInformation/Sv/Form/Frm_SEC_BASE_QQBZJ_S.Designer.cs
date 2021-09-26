﻿using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
namespace YssSecInformation.Sv.Form
{
    partial class Frm_SEC_BASE_QQBZJ_S
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_SEC_BASE_QQBZJ_S));
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.cell13 = new Yss.KTable.Models.Cell();
            this.cell14 = new Yss.KTable.Models.Cell();
            this.cell15 = new Yss.KTable.Models.Cell();
            this.cell16 = new Yss.KTable.Models.Cell();
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.column6 = new Yss.KTable.Models.Column();
            this.column7 = new Yss.KTable.Models.Column();
            this.column8 = new Yss.KTable.Models.Column();
            this.subTable = new Yss.KTableExpand.RichTable();
            this.column9 = new Yss.KTable.Models.Column();
            this.pnlBottom.SuspendLayout();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // pnlBottom
            // 
            this.pnlBottom.Controls.Add(this.subTable);
            this.pnlBottom.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.pnlBottom.Location = new System.Drawing.Point(0, 31);
            this.pnlBottom.Size = new System.Drawing.Size(578, 253);
            this.pnlBottom.Style.Alignment = System.Drawing.StringAlignment.Center;
            this.pnlBottom.Style.BackColor1.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBackground;
            this.pnlBottom.Style.BackColor2.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBackground2;
            this.pnlBottom.Style.BorderColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBorder;
            this.pnlBottom.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            this.pnlBottom.Style.GradientAngle = 90;
            this.pnlBottom.Controls.SetChildIndex(this.tabOther, 0);
            this.pnlBottom.Controls.SetChildIndex(this.tbBottom, 0);
            this.pnlBottom.Controls.SetChildIndex(this.subTable, 0);
            // 
            // tabOther
            // 
            // 
            // 
            // 
            this.tabOther.Border.Bottom = false;
            this.tabOther.Border.Left = false;
            this.tabOther.Border.Right = false;
            this.tabOther.Border.Top = false;
            this.tabOther.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.column9});
            this.tabOther.ShowColumnHeader = false;
            this.tabOther.Size = new System.Drawing.Size(578, 35);
            // 
            // tbBottom
            // 
            this.tbBottom.AlternatingColor = System.Drawing.Color.FromArgb(((int)(((byte)(246)))), ((int)(((byte)(248)))), ((int)(((byte)(250)))));
            this.tbBottom.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(255)))));
            // 
            // 
            // 
            this.tbBottom.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(111)))), ((int)(((byte)(157)))), ((int)(((byte)(217)))));
            this.tbBottom.Border.Bottom = true;
            this.tbBottom.Border.Left = false;
            this.tbBottom.Border.Right = false;
            this.tbBottom.Border.Top = false;
            this.tbBottom.Dock = System.Windows.Forms.DockStyle.None;
            this.tbBottom.Location = new System.Drawing.Point(0, 212);
            this.tbBottom.Size = new System.Drawing.Size(297, 49);
            // 
            // row1
            // 
            this.row1.Height = 25;
            this.row1.Text = "基本信息";
            // 
            // tbMain
            // 
            // 
            // 
            // 
            this.tbMain.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(111)))), ((int)(((byte)(157)))), ((int)(((byte)(217)))));
            this.tbMain.Border.Bottom = false;
            this.tbMain.Border.Left = false;
            this.tbMain.Border.Right = false;
            this.tbMain.Border.Top = false;
            this.tbMain.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.column1,
            this.column2,
            this.column3,
            this.column4,
            this.column5,
            this.column6,
            this.column7,
            this.column8});
            this.tbMain.ColumnSort = false;
            this.tbMain.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tbMain.GridLine = Yss.KTable.Enums.GridLines.Both;
            this.tbMain.GridLineColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(128)))), ((int)(((byte)(128)))));
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(578, 253);
            // 
            // btnBar
            // 
            this.btnBar.Size = new System.Drawing.Size(578, 30);
            // 
            // stBarBottom
            // 
            // 
            // 
            // 
            this.stBarBottom.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.stBarBottom.Border.Bottom = true;
            this.stBarBottom.Border.Left = false;
            this.stBarBottom.Border.Right = false;
            this.stBarBottom.Border.Top = true;
            this.stBarBottom.Location = new System.Drawing.Point(0, 284);
            this.stBarBottom.Size = new System.Drawing.Size(578, 25);
            // 
            // pnlMain
            // 
            this.pnlMain.Size = new System.Drawing.Size(578, 309);
            //this.pnlMain.Style.Alignment = System.Drawing.StringAlignment.Center;
            //this.pnlMain.Style.BackColor1.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.DockSiteBackColor;
            //this.pnlMain.Style.BackColor2.Color = System.Drawing.Color.FromArgb(((int)(((byte)(243)))), ((int)(((byte)(245)))), ((int)(((byte)(249)))));
            //this.pnlMain.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            //this.pnlMain.Style.BorderColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBorder;
            //this.pnlMain.Style.BorderSide = YssDevComponents.DotNetBar.eBorderSide.Top;
            //this.pnlMain.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            //this.pnlMain.Style.GradientAngle = 90;
            // 
            // yssPanel1
            // 
            this.yssPanel1.Size = new System.Drawing.Size(578, 309);
            // 
            // hpAssist
            // 
            // 
            // cell1
            // 
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.Text = "   公式代码：";
            this.cell1.ToolTip = null;
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.ToolTip = null;
            // 
            // cell3
            // 
            this.cell3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell3.ToolTip = null;
            // 
            // cell4
            // 
            this.cell4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell4.Text = "公式名称：";
            this.cell4.ToolTip = null;
            // 
            // cell5
            // 
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.ToolTip = null;
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.ToolTip = null;
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.Text = "比率类型：";
            this.cell7.ToolTip = null;
            // 
            // cell8
            // 
            this.cell8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell8.ToolTip = null;
            // 
            // cell9
            // 
            this.cell9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell9.Text = "   开始日期：";
            this.cell9.ToolTip = null;
            // 
            // cell10
            // 
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.ToolTip = null;
            // 
            // cell11
            // 
            this.cell11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell11.ToolTip = null;
            // 
            // cell12
            // 
            this.cell12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell12.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell12.Text = "结束日期：";
            this.cell12.ToolTip = null;
            // 
            // cell13
            // 
            this.cell13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell13.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell13.ToolTip = null;
            // 
            // cell14
            // 
            this.cell14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell14.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell14.ToolTip = null;
            // 
            // cell15
            // 
            this.cell15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell15.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell15.ToolTip = null;
            // 
            // cell16
            // 
            this.cell16.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell16.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell16.ToolTip = null;
            // 
            // column1
            // 
            this.column1.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column1.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column1.DataPropertyName = null;
            this.column1.DataType = Yss.KTable.Enums.ColumnDataType.None;
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.column1.Tag = null;
            this.column1.Text = "";
            this.column1.Width = 110;
            // 
            // column2
            // 
            this.column2.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column2.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column2.DataPropertyName = null;
            this.column2.DataType = Yss.KTable.Enums.ColumnDataType.None;
            this.column2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.column2.Tag = null;
            this.column2.Text = "";
            this.column2.Width = 122;
            // 
            // column3
            // 
            this.column3.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column3.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column3.DataPropertyName = null;
            this.column3.DataType = Yss.KTable.Enums.ColumnDataType.None;
            this.column3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.column3.Tag = null;
            this.column3.Text = "";
            this.column3.Width = 20;
            // 
            // column4
            // 
            this.column4.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column4.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column4.DataPropertyName = null;
            this.column4.DataType = Yss.KTable.Enums.ColumnDataType.None;
            this.column4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.column4.Tag = null;
            this.column4.Text = "";
            this.column4.Width = 91;
            // 
            // column5
            // 
            this.column5.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column5.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column5.DataPropertyName = null;
            this.column5.DataType = Yss.KTable.Enums.ColumnDataType.None;
            this.column5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.column5.Tag = null;
            this.column5.Text = "";
            this.column5.Width = 122;
            // 
            // column6
            // 
            this.column6.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column6.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column6.DataPropertyName = null;
            this.column6.DataType = Yss.KTable.Enums.ColumnDataType.None;
            this.column6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.column6.Tag = null;
            this.column6.Text = "";
            this.column6.Width = 20;
            // 
            // column7
            // 
            this.column7.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column7.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column7.DataPropertyName = null;
            this.column7.DataType = Yss.KTable.Enums.ColumnDataType.None;
            this.column7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.column7.Tag = null;
            this.column7.Text = "";
            this.column7.Width = 91;
            // 
            // column8
            // 
            this.column8.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column8.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column8.DataPropertyName = null;
            this.column8.DataType = Yss.KTable.Enums.ColumnDataType.None;
            this.column8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.column8.Tag = null;
            this.column8.Text = "";
            this.column8.Width = 122;
            // 
            // subTable
            // 
            this.subTable.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(243)))), ((int)(((byte)(245)))), ((int)(((byte)(249)))));
            this.subTable.Dock = System.Windows.Forms.DockStyle.Fill;
            this.subTable.Location = new System.Drawing.Point(0, 35);
            this.subTable.Name = "subTable";
            this.subTable.Padding = new System.Windows.Forms.Padding(10, 0, 0, 0);
            this.subTable.ReadOnly = false;
            this.subTable.Size = new System.Drawing.Size(578, 218);
            this.subTable.TabIndex = 17;
            // 
            // column9
            // 
            this.column9.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            this.column9.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column9.DataPropertyName = null;
            this.column9.DataType = Yss.KTable.Enums.ColumnDataType.None;
            this.column9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.column9.Tag = null;
            this.column9.Text = "";
            // 
            // Frm_SEC_BASE_QQBZJ_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(578, 309);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "Frm_SEC_BASE_QQBZJ_S";
            this.TabComplexText = "基本信息";
            this.Text = "保证金调整信息";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Frm_SEC_BASE_QHBZJ_S_FormClosing);
            this.Load += new System.EventHandler(this.Frm_TRADE_IVT_COLLATERAL_S_Load);
            this.Controls.SetChildIndex(this.yssPanel1, 0);
            this.pnlBottom.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
        private Yss.KTable.Models.Column column3;
        private Yss.KTable.Models.Column column4;
        private Yss.KTable.Models.Column column5;
        private Yss.KTable.Models.Column column6;
        private Yss.KTable.Models.Column column7;
        private Yss.KTable.Models.Column column8;
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Cell cell2;
        private Yss.KTable.Models.Cell cell3;
        private Yss.KTable.Models.Cell cell4;
        private Yss.KTable.Models.Cell cell5;
        private Yss.KTable.Models.Cell cell6;
        private Yss.KTable.Models.Cell cell7;
        private Yss.KTable.Models.Cell cell8;
        private Yss.KTable.Models.Cell cell9;
        private Yss.KTable.Models.Cell cell10;
        private Yss.KTable.Models.Cell cell11;
        private Yss.KTable.Models.Cell cell12;
        private Yss.KTable.Models.Cell cell13;
        private Yss.KTable.Models.Cell cell14;
        private Yss.KTable.Models.Cell cell15;
        private Yss.KTable.Models.Cell cell16;
        private Yss.KTableExpand.RichTable subTable;
        #endregion
        private Yss.KTable.Models.Column column9;
    }
}