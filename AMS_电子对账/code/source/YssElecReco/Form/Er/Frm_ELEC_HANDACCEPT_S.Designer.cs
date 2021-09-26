using System;
namespace YssElecReco.Form.Er
{
    partial class Frm_ELEC_HANDACCEPT_S
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
            this.dzDate = new Yss.Controls.DatePicker();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.cboDzType = new FAST.Core.BaseControl.YssSelCombox();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.cboRepType = new FAST.Core.BaseControl.YssSelCombox();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.cboSgyy = new FAST.Core.BaseControl.YssSelCombox();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.summaryTxt = new Yss.KRichEx.YssTextBox();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.row1 = new Yss.KTable.Models.Row();
            this.row3 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.row4 = new Yss.KTable.Models.Row();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.row5 = new Yss.KTable.Models.Row();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.column5 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column1 = new Yss.KTable.Models.Column();
            this.row2 = new Yss.KTable.Models.Row();
            this.row6 = new Yss.KTable.Models.Row();
            this.tbMain.SuspendLayout();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dzDate)).BeginInit();
            this.SuspendLayout();
            // 
            // tbMain
            // 
            // 
            // 
            // 
            this.tbMain.Border.BorderColor = System.Drawing.Color.Gray;
            this.tbMain.Border.Bottom = false;
            this.tbMain.Border.Left = false;
            this.tbMain.Border.Right = false;
            this.tbMain.Border.Top = false;
            this.tbMain.Columns.AddRange(new Yss.KTable.Models.Column[] {
            this.column1,
            this.column2,
            this.column3,
            this.column4,
            this.column5});
            this.tbMain.Controls.Add(this.summaryTxt);
            this.tbMain.Controls.Add(this.cboRepType);
            this.tbMain.Controls.Add(this.cboDzType);
            this.tbMain.Controls.Add(this.cboSgyy);
            this.tbMain.Controls.Add(this.dzDate);
            this.tbMain.GridLineColor = System.Drawing.Color.Maroon;
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3,
            this.row4,
            this.row5,
            this.row6});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(493, 162);
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
            this.stBarBottom.Location = new System.Drawing.Point(0, 192);
            // 
            // pnlMain
            // 
            this.pnlMain.Size = new System.Drawing.Size(493, 217);
            // 
            // yssPanel1
            // 
            // 
            // 
            // 
            this.yssPanel1.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.yssPanel1.Size = new System.Drawing.Size(493, 217);
            // 
            // dzDate
            // 
            this.dzDate.Location = new System.Drawing.Point(343, 66);
            this.dzDate.Name = "dzDate";
            this.dzDate.Size = new System.Drawing.Size(121, 21);
            this.dzDate.TabIndex = 8;
            this.dzDate.Tag = this.cell10;
            this.dzDate.Value = new System.DateTime(2018, 4, 3, 0, 0, 0, 0);
            this.dzDate.YssCaption = "对账日期";
            this.dzDate.YssIsMust = true;
            // 
            // cell10
            // 
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.InnerControl = this.dzDate;
            // 
            // cboDzType
            // 
            this.cboDzType.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboDzType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboDzType.KTableTree = true;
            this.cboDzType.Location = new System.Drawing.Point(110, 43);
            this.cboDzType.Margin = new System.Windows.Forms.Padding(0);
            this.cboDzType.MethodInfo.MethodName = "getDataList";
            this.cboDzType.Name = "cboDzType";
            this.cboDzType.NodeID = "C_DZ_CODE";
            this.cboDzType.Parameter = "C_DZ_NAME";
            this.cboDzType.ParaNodeID = "C_DZ_CODE_P";
            this.cboDzType.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconOnly;
            this.cboDzType.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.cboDzType.Size = new System.Drawing.Size(121, 21);
            this.cboDzType.SortColumn = "C_DZ_NAME";
            this.cboDzType.TabIndex = 0;
            this.cboDzType.Tag = this.cell2;
            this.cboDzType.YssAssociaType = YssElecReco.Context.AssociaType.base_erdztype;
            this.cboDzType.YssCaption = "对账类型";
            this.cboDzType.YssIsMust = true;
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.cboDzType;
            // 
            // cboRepType
            // 
            this.cboRepType.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboRepType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboRepType.DisplayName = "C_DV_NAME";
            this.cboRepType.DisplayValue = "C_DV_CODE";
            this.cboRepType.Location = new System.Drawing.Point(343, 43);
            this.cboRepType.Margin = new System.Windows.Forms.Padding(0);
            this.cboRepType.Name = "cboRepType";
            this.cboRepType.Parameter = "C_DV_NAME";
            this.cboRepType.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.cboRepType.Size = new System.Drawing.Size(121, 21);
            this.cboRepType.TabIndex = 10;
            this.cboRepType.Tag = this.cell5;
            this.cboRepType.YssAssociaType = ((FAST.Core.Context.Associa)(FAST.Core.Context.AssociaFAST.NULL));
            this.cboRepType.YssCaption = "报表类型";
            this.cboRepType.YssIsMust = true;
            this.cboRepType.YssOnBeforeLoadData += new FAST.Core.BaseControl.YssSelCombox.yssBeforeLoadData(this.cboRepType_setValue);
            // 
            // cell5
            // 
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.cboRepType;
            // 
            // cboSgyy
            // 
            this.cboSgyy.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboSgyy.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboSgyy.DisplayName = "C_DV_NAME";
            this.cboSgyy.DisplayValue = "C_DV_CODE";
            this.cboSgyy.Location = new System.Drawing.Point(110, 66);
            this.cboSgyy.Margin = new System.Windows.Forms.Padding(0);
            this.cboSgyy.Name = "cboSgyy";
            this.cboSgyy.Parameter = "C_DV_NAME";
            this.cboSgyy.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.cboSgyy.Size = new System.Drawing.Size(121, 21);
            this.cboSgyy.TabIndex = 11;
            this.cboSgyy.Tag = this.cell7;
            this.cboSgyy.YssAssociaType = ((FAST.Core.Context.Associa)(FAST.Core.Context.AssociaFAST.NULL));
            this.cboSgyy.YssCaption = "原因";
            this.cboSgyy.YssIsMust = true;
            this.cboSgyy.YssOnBeforeLoadData += new FAST.Core.BaseControl.YssSelCombox.yssBeforeLoadData(this.cboSgyy_setValue);
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.InnerControl = this.cboSgyy;
            // 
            // summaryTxt
            // 
            this.summaryTxt.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.summaryTxt.Location = new System.Drawing.Point(110, 89);
            this.summaryTxt.Multiline = true;
            this.summaryTxt.Name = "summaryTxt";
            this.summaryTxt.Size = new System.Drawing.Size(354, 45);
            this.summaryTxt.TabIndex = 12;
            this.summaryTxt.Tag = this.cell12;
            this.summaryTxt.YssCaption = "说明";
            this.summaryTxt.YssLength = 2000;
            // 
            // cell12
            // 
            this.cell12.ColSpan = 4;
            this.cell12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell12.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell12.InnerControl = this.summaryTxt;
            this.cell12.RowSpan = 2;
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("宋体", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row1.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row1.FullRowSelected = false;
            this.row1.GroupLineLength = 330;
            this.row1.Height = 33;
            this.row1.IsGroup = true;
            this.row1.Text = "基本信息";
            // 
            // row3
            // 
            this.row3.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell1,
            this.cell2,
            this.cell3,
            this.cell4,
            this.cell5});
            this.row3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row3.FullRowSelected = false;
            this.row3.Text = null;
            // 
            // cell1
            // 
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.InnerControl = null;
            this.cell1.Text = "   对账类型：";
            // 
            // cell3
            // 
            this.cell3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell3.InnerControl = null;
            // 
            // cell4
            // 
            this.cell4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell4.InnerControl = null;
            this.cell4.Text = "报表类型：";
            // 
            // row4
            // 
            this.row4.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell6,
            this.cell7,
            this.cell8,
            this.cell9,
            this.cell10});
            this.row4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row4.FullRowSelected = false;
            this.row4.Text = null;
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.InnerControl = null;
            this.cell6.Text = "       原因：";
            // 
            // cell8
            // 
            this.cell8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell8.InnerControl = null;
            // 
            // cell9
            // 
            this.cell9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell9.InnerControl = null;
            this.cell9.Text = "对账日期：";
            // 
            // row5
            // 
            this.row5.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell11,
            this.cell12});
            this.row5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row5.FullRowSelected = false;
            this.row5.Text = null;
            // 
            // cell11
            // 
            this.cell11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell11.InnerControl = null;
            this.cell11.RowSpan = 2;
            this.cell11.Text = "       说明：";
            // 
            // column5
            // 
            this.column5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column5.Width = 122;
            // 
            // column4
            // 
            this.column4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column4.Width = 91;
            // 
            // column3
            // 
            this.column3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column3.Width = 20;
            // 
            // column2
            // 
            this.column2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column2.Width = 122;
            // 
            // column1
            // 
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.Width = 110;
            // 
            // row2
            // 
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row2.FullRowSelected = false;
            this.row2.Height = 10;
            this.row2.Text = null;
            // 
            // row6
            // 
            this.row6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row6.FullRowSelected = false;
            this.row6.Text = null;
            // 
            // Frm_ELEC_HANDACCEPT_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(493, 217);
            this.DoubleBuffered = true;
            this.Name = "Frm_ELEC_HANDACCEPT_S";
            this.Text = "手工原因设置";
            this.Load += new System.EventHandler(this.Frm_ELEC_SGYY_S_Load);
            this.tbMain.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dzDate)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private FAST.Core.BaseControl.YssSelCombox cboSgyy;
        private FAST.Core.BaseControl.YssSelCombox cboRepType;
        private FAST.Core.BaseControl.YssSelCombox cboDzType;
        private Yss.Controls.DatePicker dzDate;
        private Yss.KRichEx.YssTextBox summaryTxt;
        private Yss.KTable.Models.Row row1;
        private Yss.KTable.Models.Row row3;
        private Yss.KTable.Models.Cell cell2;
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Cell cell3;
        private Yss.KTable.Models.Cell cell4;
        private Yss.KTable.Models.Cell cell5;
        private Yss.KTable.Models.Cell cell7;
        private Yss.KTable.Models.Cell cell10;
        private Yss.KTable.Models.Row row4;
        private Yss.KTable.Models.Cell cell6;
        private Yss.KTable.Models.Cell cell8;
        private Yss.KTable.Models.Cell cell9;
        private Yss.KTable.Models.Row row5;
        private Yss.KTable.Models.Cell cell11;
        private Yss.KTable.Models.Cell cell12;
        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
        private Yss.KTable.Models.Column column3;
        private Yss.KTable.Models.Column column4;
        private Yss.KTable.Models.Column column5;
        private Yss.KTable.Models.Row row2;
        private Yss.KTable.Models.Row row6;
    }
}