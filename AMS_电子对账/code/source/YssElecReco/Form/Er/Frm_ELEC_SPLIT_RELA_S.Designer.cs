namespace YssElecReco.Form.Er
{
    partial class Frm_ELEC_SPLIT_RELA_S
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
            this.cboTgh = new FAST.Core.BaseControl.YssSelCombox();
            this.txtSplitCode = new Yss.KRichEx.YssTextBox();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.row3 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.cboTZZH = new FAST.Core.BaseControl.YssSelCombox();
            this.row4 = new Yss.KTable.Models.Row();
            this.row5 = new Yss.KTable.Models.Row();
            this.row6 = new Yss.KTable.Models.Row();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.row7 = new Yss.KTable.Models.Row();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.cell13 = new Yss.KTable.Models.Cell();
            this.cell14 = new Yss.KTable.Models.Cell();
            this.cell15 = new Yss.KTable.Models.Cell();
            this.yssStartDate = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.yssEndDate = new FAST.Core.BaseControl.YssDateTimeInterval();
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
            this.tbMain.Controls.Add(this.yssEndDate);
            this.tbMain.Controls.Add(this.yssStartDate);
            this.tbMain.Controls.Add(this.cboTZZH);
            this.tbMain.Controls.Add(this.txtSplitCode);
            this.tbMain.Controls.Add(this.cboTgh);
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3,
            this.row4,
            this.row5,
            this.row6,
            this.row7});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(493, 163);
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
            this.stBarBottom.Location = new System.Drawing.Point(0, 193);
            // 
            // pnlMain
            // 
            this.pnlMain.Size = new System.Drawing.Size(493, 218);
            // 
            // yssPanel1
            // 
            // 
            // 
            // 
            this.yssPanel1.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.yssPanel1.Size = new System.Drawing.Size(493, 218);
            // 
            // cboTgh
            // 
            this.cboTgh.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboTgh.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboTgh.DisplayName = "C_ORG_NAME";
            this.cboTgh.DisplayValue = "C_ORG_CODE";
            this.cboTgh.FilterCond = "";
            this.cboTgh.Location = new System.Drawing.Point(100, 89);
            this.cboTgh.Margin = new System.Windows.Forms.Padding(0);
            this.cboTgh.MethodInfo = null;
            this.cboTgh.Name = "cboTgh";
            this.cboTgh.Parameter = "C_ORG_NAME";
            this.cboTgh.PrefixBackColor = System.Drawing.Color.White;
            this.cboTgh.QueryCond = "";
            this.cboTgh.QueryType = "";
            this.cboTgh.Size = new System.Drawing.Size(119, 21);
            this.cboTgh.TabIndex = 24;
            this.cboTgh.Tag = this.cell7;
            this.cboTgh.YssAssociaType = FAST.Core.Context.AssociaFAST.NULL;
            this.cboTgh.YssCaption = "托管机构";
            this.cboTgh.YssIsMust = true;
            this.cboTgh.YssOnBeforeLoadData += new FAST.Core.BaseControl.YssSelCombox.yssBeforeLoadData(this.cboTgh_YssOnBeforeLoadData);
            // 
            // txtSplitCode
            // 
            this.txtSplitCode.Location = new System.Drawing.Point(340, 89);
            this.txtSplitCode.Name = "txtSplitCode";
            this.txtSplitCode.Size = new System.Drawing.Size(119, 21);
            this.txtSplitCode.TabIndex = 27;
            this.txtSplitCode.Tag = this.cell10;
            this.txtSplitCode.YssCaption = "拆分代码";
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("宋体", 10F, System.Drawing.FontStyle.Bold);
            this.row1.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row1.FullRowSelected = false;
            this.row1.GroupLineLength = 310;
            this.row1.IsGroup = true;
            this.row1.Text = "基本信息";
            // 
            // row2
            // 
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row2.FullRowSelected = false;
            this.row2.Height = 10;
            this.row2.Text = null;
            // 
            // column1
            // 
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.Width = 100;
            // 
            // column2
            // 
            this.column2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column2.Width = 120;
            // 
            // column3
            // 
            this.column3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column3.Width = 40;
            // 
            // column4
            // 
            this.column4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            // 
            // column5
            // 
            this.column5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column5.Width = 120;
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
            this.cell1.Text = "     产品组合：";
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.cboTZZH;
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
            // 
            // cell5
            // 
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = null;
            // 
            // cboTZZH
            // 
            this.cboTZZH.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboTZZH.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboTZZH.DisplayName = "C_PORT_NAME";
            this.cboTZZH.DisplayValue = "C_PORT_CODE";
            this.cboTZZH.FilterCond = "";
            this.cboTZZH.KTableTree = true;
            this.cboTZZH.Location = new System.Drawing.Point(100, 33);
            this.cboTZZH.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataList";
            controlMethodInfo1.MethodParams = null;
            controlMethodInfo1.MethodParamValues = null;
            controlMethodInfo1.Methods = null;
            this.cboTZZH.MethodInfo = controlMethodInfo1;
            this.cboTZZH.Name = "cboTZZH";
            this.cboTZZH.NodeID = "C_PORT_CODE";
            this.cboTZZH.Parameter = "C_PORT_NAME";
            this.cboTZZH.ParaNodeID = "C_PORT_CODE_P";
            this.cboTZZH.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.cboTZZH.QueryCond = "";
            this.cboTZZH.QueryType = "";
            this.cboTZZH.Size = new System.Drawing.Size(119, 21);
            this.cboTZZH.SortColumn = "C_PORT_NAME";
            this.cboTZZH.TabIndex = 31;
            this.cboTZZH.Tag = this.cell2;
            this.cboTZZH.YssAssociaType = FAST.Core.Context.AssociaFAST.portfolio;
            this.cboTZZH.YssCaption = "投资组合";
            this.cboTZZH.YssIsMust = true;
            // 
            // row4
            // 
            this.row4.Font = new System.Drawing.Font("宋体", 10F, System.Drawing.FontStyle.Bold);
            this.row4.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row4.FullRowSelected = false;
            this.row4.GroupLineLength = 310;
            this.row4.IsGroup = true;
            this.row4.Text = "设置信息";
            // 
            // row5
            // 
            this.row5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row5.FullRowSelected = false;
            this.row5.Height = 10;
            this.row5.Text = null;
            // 
            // row6
            // 
            this.row6.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell6,
            this.cell7,
            this.cell8,
            this.cell9,
            this.cell10});
            this.row6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row6.FullRowSelected = false;
            this.row6.Text = null;
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.InnerControl = null;
            this.cell6.Text = "     托管机构：";
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.InnerControl = this.cboTgh;
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
            this.cell9.Text = "拆分代码：";
            // 
            // cell10
            // 
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.InnerControl = this.txtSplitCode;
            // 
            // row7
            // 
            this.row7.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell11,
            this.cell12,
            this.cell13,
            this.cell14,
            this.cell15});
            this.row7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row7.FullRowSelected = false;
            this.row7.Text = null;
            // 
            // cell11
            // 
            this.cell11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell11.InnerControl = null;
            this.cell11.Text = "     开始日期：";
            // 
            // cell12
            // 
            this.cell12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell12.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell12.InnerControl = this.yssStartDate;
            // 
            // cell13
            // 
            this.cell13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell13.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell13.InnerControl = null;
            // 
            // cell14
            // 
            this.cell14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell14.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell14.InnerControl = null;
            this.cell14.Text = "结束日期：";
            // 
            // cell15
            // 
            this.cell15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell15.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell15.InnerControl = this.yssEndDate;
            // 
            // yssStartDate
            // 
            this.yssStartDate.BackColor = System.Drawing.Color.Transparent;
            this.yssStartDate.DateBeginChecked = true;
            this.yssStartDate.DateEndChecked = true;
            this.yssStartDate.Location = new System.Drawing.Point(100, 112);
            this.yssStartDate.Margin = new System.Windows.Forms.Padding(0);
            this.yssStartDate.Name = "yssStartDate";
            this.yssStartDate.Size = new System.Drawing.Size(119, 21);
            this.yssStartDate.TabIndex = 32;
            this.yssStartDate.Tag = this.cell12;
            this.yssStartDate.yssEnabled = true;
            this.yssStartDate.yssFormatDateStr = "yyyy-MM-dd";
            this.yssStartDate.yssInterval = false;
            this.yssStartDate.yssLabelStr = "至";
            this.yssStartDate.yssShowOperLable = false;
            this.yssStartDate.YssShowSecond = true;
            this.yssStartDate.yssTimeControl = false;
            // 
            // yssEndDate
            // 
            this.yssEndDate.BackColor = System.Drawing.Color.Transparent;
            this.yssEndDate.DateBeginChecked = true;
            this.yssEndDate.DateEndChecked = true;
            this.yssEndDate.Location = new System.Drawing.Point(340, 112);
            this.yssEndDate.Margin = new System.Windows.Forms.Padding(0);
            this.yssEndDate.Name = "yssEndDate";
            this.yssEndDate.Size = new System.Drawing.Size(119, 21);
            this.yssEndDate.TabIndex = 33;
            this.yssEndDate.Tag = this.cell15;
            this.yssEndDate.yssEnabled = true;
            this.yssEndDate.yssFormatDateStr = "yyyy-MM-dd";
            this.yssEndDate.yssInterval = false;
            this.yssEndDate.yssLabelStr = "至";
            this.yssEndDate.yssShowOperLable = false;
            this.yssEndDate.YssShowSecond = true;
            this.yssEndDate.yssTimeControl = false;
            // 
            // Frm_ELEC_SPLIT_RELA_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(493, 218);
            this.DoubleBuffered = true;
            this.Name = "Frm_ELEC_SPLIT_RELA_S";
            this.Text = "多托管行拆分设置";
            this.tbMain.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private FAST.Core.BaseControl.YssSelCombox cboTgh;
        private Yss.KRichEx.YssTextBox txtSplitCode;
        private Yss.KTable.Models.Row row1;
        private Yss.KTable.Models.Row row2;
        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
        private Yss.KTable.Models.Column column3;
        private Yss.KTable.Models.Column column4;
        private Yss.KTable.Models.Column column5;
        private Yss.KTable.Models.Row row3;
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Cell cell2;
        private Yss.KTable.Models.Cell cell3;
        private Yss.KTable.Models.Cell cell4;
        private Yss.KTable.Models.Cell cell5;
        private FAST.Core.BaseControl.YssSelCombox cboTZZH;
        private Yss.KTable.Models.Cell cell15;
        private Yss.KTable.Models.Cell cell12;
        private Yss.KTable.Models.Cell cell10;
        private Yss.KTable.Models.Cell cell7;
        private Yss.KTable.Models.Row row4;
        private Yss.KTable.Models.Row row5;
        private Yss.KTable.Models.Row row6;
        private Yss.KTable.Models.Cell cell6;
        private Yss.KTable.Models.Cell cell8;
        private Yss.KTable.Models.Cell cell9;
        private Yss.KTable.Models.Row row7;
        private Yss.KTable.Models.Cell cell11;
        private Yss.KTable.Models.Cell cell13;
        private Yss.KTable.Models.Cell cell14;
        private FAST.Core.BaseControl.YssDateTimeInterval yssEndDate;
        private FAST.Core.BaseControl.YssDateTimeInterval yssStartDate;
    }
}