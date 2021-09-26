////using YssBaseCls.Fun;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;

using FAST.Core.Resource;
namespace YssSecInformation.Mp.SecEq.Form
{
    partial class Frm_SEC_EQU_PD_S
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
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo2 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo1 = new FAST.Core.BaseControl.ControlMethodInfo();
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.cboSec = new FAST.Core.BaseControl.YssSelCombox();
            this.row4 = new Yss.KTable.Models.Row();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.cell14 = new Yss.KTable.Models.Cell();
            this.cell15 = new Yss.KTable.Models.Cell();
            this.cboSecTag = new FAST.Core.BaseControl.YssSelCombox();
            this.tbMain.SuspendLayout();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            this.SuspendLayout();
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
            this.tbMain.Controls.Add(this.cboSecTag);
            this.tbMain.Controls.Add(this.cboSec);
            this.tbMain.Location = new System.Drawing.Point(0, 31);
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row4});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(493, 122);
            // 
            // btnBar
            // 
            this.btnBar.Location = new System.Drawing.Point(0, 1);
            // 
            // stBarBottom
            // 
            // 
            // 
            // 
            this.stBarBottom.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.stBarBottom.Border.Bottom = false;
            this.stBarBottom.Border.Left = false;
            this.stBarBottom.Border.Right = false;
            this.stBarBottom.Border.Top = true;
            this.stBarBottom.Location = new System.Drawing.Point(0, 153);
            this.stBarBottom.StatuType = "新增(&Add...)";
            // 
            // pnlMain
            // 
            this.pnlMain.Padding = new System.Windows.Forms.Padding(0, 1, 0, 0);
            this.pnlMain.Size = new System.Drawing.Size(493, 178);
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
            // 
            // 
            // 
            this.yssPanel1.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.yssPanel1.Border.Bottom = true;
            this.yssPanel1.Border.Left = true;
            this.yssPanel1.Border.Right = true;
            this.yssPanel1.Border.Top = true;
            this.yssPanel1.Size = new System.Drawing.Size(493, 178);
            // 
            // hpAssist
            // 
            // 
            // column1
            // 
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.Width = 110;
            // 
            // column2
            // 
            this.column2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column2.Width = 122;
            // 
            // column3
            // 
            this.column3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column3.Width = 30;
            // 
            // column4
            // 
            this.column4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column4.Width = 91;
            // 
            // column5
            // 
            this.column5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column5.Width = 122;
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("新宋体", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row1.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row1.FullRowSelected = false;
            this.row1.GroupLineLength = 310;
            this.row1.GroupPosition = 13;
            this.row1.Height = 33;
            this.row1.IsGroup = true;
            this.row1.Text = "基本信息";
            // 
            // row2
            // 
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row2.ForeColor = System.Drawing.Color.Black;
            this.row2.FullRowSelected = false;
            this.row2.GroupPosition = -16;
            this.row2.Height = 10;
            this.row2.Text = null;
            // 
            // cboSec
            // 
            this.cboSec.AddedSelItemName = "";
            this.cboSec.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboSec.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboSec.Border.Bottom = true;
            this.cboSec.Border.Left = true;
            this.cboSec.Border.Right = true;
            this.cboSec.Border.Top = true;
            this.cboSec.ClassName = "";
            this.cboSec.DisplayStyle = Yss.KRichEx.AutoFilter.DisplayStyle.Modal;
            this.cboSec.DllName = "dll";
            this.cboSec.FilterCond = "";
            this.cboSec.Location = new System.Drawing.Point(110, 43);
            this.cboSec.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo2.MethodName = "getDataListByTypes";
            controlMethodInfo2.MethodParams = null;
            controlMethodInfo2.MethodParamValues = new string[] {"JJ,"};
            controlMethodInfo2.Methods = null;
            this.cboSec.MethodInfo = controlMethodInfo2;
            this.cboSec.Name = "cboSec";
            this.cboSec.QueryCond = "";
            this.cboSec.QueryType = "";
            this.cboSec.Size = new System.Drawing.Size(121, 21);
            this.cboSec.TabIndex = 1;
            this.cboSec.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Value_Name;
            this.cboSec.YssAssociaType = YssSecInformation.Support.Context.AssociaType.sv_sec;
            this.cboSec.YssCaption = "交易证券";
            this.cboSec.YssIsMust = true;
            // 
            // row4
            // 
            this.row4.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell7,
            this.cell8,
            this.cell6,
            this.cell9,
            this.cell10});
            this.row4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row4.ForeColor = System.Drawing.Color.Black;
            this.row4.FullRowSelected = false;
            this.row4.GroupPosition = -16;
            this.row4.Height = 25;
            this.row4.Text = null;
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.Color.Black;
            this.cell7.Text = "   交易证券(主)：";
            // 
            // cell8
            // 
            this.cell8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell8.ForeColor = System.Drawing.Color.Black;
            this.cell8.InnerControl = this.cboSec;

            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.Color.Black;
            // 
            // cell9
            // 
            this.cell9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell9.Text = "交易证券(子)：";
            // 
            // cell10
            // 
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.InnerControl = this.cboSecTag;

            // 
            // cell14
            // 
            this.cell14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell14.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell14.Text = "   流通日期：";
            // 
            // cell15
            // 
            this.cell15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell15.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cboSecTag
            // 
            this.cboSecTag.AddedSelItemName = "";
            this.cboSecTag.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboSecTag.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboSecTag.Border.Bottom = true;
            this.cboSecTag.Border.Left = true;
            this.cboSecTag.Border.Right = true;
            this.cboSecTag.Border.Top = true;
            this.cboSecTag.ClassName = "";
            this.cboSecTag.DisplayStyle = Yss.KRichEx.AutoFilter.DisplayStyle.Modal;
            this.cboSecTag.DllName = "dll";
            this.cboSecTag.FilterCond = "";
            this.cboSecTag.Location = new System.Drawing.Point(353, 43);
            this.cboSecTag.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataListByTypes";
            controlMethodInfo1.MethodParams = null;
            controlMethodInfo1.MethodParamValues = new string[] { "JJ," };
            controlMethodInfo1.Methods = null;
            this.cboSecTag.MethodInfo = controlMethodInfo1;
            this.cboSecTag.Name = "cboSecTag";
            this.cboSecTag.QueryCond = "";
            this.cboSecTag.QueryType = "";
            this.cboSecTag.Size = new System.Drawing.Size(121, 21);
            this.cboSecTag.TabIndex = 7;
            this.cboSecTag.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Value_Name;
            this.cboSecTag.YssAssociaType = YssSecInformation.Support.Context.AssociaType.sv_sec;
            this.cboSecTag.YssCaption = "交易证券";
            this.cboSecTag.YssIsMust = true;
            // 
            // Frm_SEC_EQU_PD_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(493, 178);
            this.DoubleBuffered = true;
            this.Name = "Frm_SEC_EQU_PD_S";
            this.StatuType = "新增(&Add...)";
            this.Text = "证券配对信息";
            this.YssSetStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.Load += new System.EventHandler(this.Frm_SEC_EQU_PD_S_Load);
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
        private Yss.KTable.Models.Row row1;
        private Yss.KTable.Models.Row row2;
        private Yss.KTable.Models.Row row4;
        private Yss.KTable.Models.Cell cell9;
        private Yss.KTable.Models.Cell cell10;
        private Yss.KTable.Models.Cell cell14;
        private Yss.KTable.Models.Cell cell15;
        private Yss.KTable.Models.Cell cell6;
        private Yss.KTable.Models.Cell cell7;
        private Yss.KTable.Models.Cell cell8;
        private FAST.Core.BaseControl.YssSelCombox cboSec;
        private FAST.Core.BaseControl.YssSelCombox cboSecTag;
    }
}