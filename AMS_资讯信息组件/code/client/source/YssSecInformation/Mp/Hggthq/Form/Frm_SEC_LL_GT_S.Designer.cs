using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;

using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;
namespace YssSecInformation.Mp.Hggthq.Form
{
    partial class Frm_SEC_LL_GT_S
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_SEC_LL_GT_S));
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo3 = new FAST.Core.BaseControl.ControlMethodInfo();
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.row3 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.dtpMktDate = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.cboBusiType = new FAST.Core.BaseControl.YssSelCombox();
            this.row4 = new Yss.KTable.Models.Row();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.cbodur = new FAST.Core.BaseControl.YssSelCombox();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.txtRate = new Yss.KRichEx.ImprovedTextBox();
            this.row5 = new Yss.KTable.Models.Row();
            this.row7 = new Yss.KTable.Models.Row();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.dtpBegin = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell13 = new Yss.KTable.Models.Cell();
            this.cell14 = new Yss.KTable.Models.Cell();
            this.cell15 = new Yss.KTable.Models.Cell();
            this.dtpEnd = new FAST.Core.BaseControl.YssDateTimeInterval();
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
            this.tbMain.Controls.Add(this.dtpMktDate);
            this.tbMain.Controls.Add(this.cboBusiType);
            this.tbMain.Controls.Add(this.cbodur);
            this.tbMain.Controls.Add(this.txtRate);
            this.tbMain.Controls.Add(this.dtpBegin);
            this.tbMain.Controls.Add(this.dtpEnd);
            this.tbMain.GridLineColor = System.Drawing.Color.Red;
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3,
            this.row4,
            this.row5,
            this.row7});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(493, 150);
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
            this.stBarBottom.Location = new System.Drawing.Point(0, 181);
            // 
            // pnlMain
            // 
            this.pnlMain.Size = new System.Drawing.Size(493, 206);
            //this.pnlMain.Style.Alignment = System.Drawing.StringAlignment.Center;
            //this.pnlMain.Style.BackColor1.Color = System.Drawing.Color.White;
            //this.pnlMain.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            //this.pnlMain.Style.BorderColor.Color = System.Drawing.Color.FromArgb(((int)(((byte)(121)))), ((int)(((byte)(173)))), ((int)(((byte)(225)))));
            //this.pnlMain.Style.BorderSide = YssDevComponents.DotNetBar.eBorderSide.Top;
            //this.pnlMain.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            //this.pnlMain.Style.GradientAngle = 90;
            // 
            // yssPanel1
            // 
            this.yssPanel1.Size = new System.Drawing.Size(493, 206);
            // 
            // column1
            // 
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.Width = 111;
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
            this.row1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
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
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row2.FullRowSelected = false;
            this.row2.GroupLineLength = 310;
            this.row2.GroupPosition = 13;
            this.row2.Height = 10;
            this.row2.Text = null;
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
            this.cell1.Text = "   行情日期：";
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.dtpMktDate;
            // 
            // dtpMktDate
            // 
            this.dtpMktDate.BackColor = System.Drawing.Color.Transparent;
            this.dtpMktDate.DateBeginChecked = true;
            this.dtpMktDate.DateEndChecked = true;
            this.dtpMktDate.Location = new System.Drawing.Point(111, 43);
            this.dtpMktDate.Margin = new System.Windows.Forms.Padding(0);
            this.dtpMktDate.Name = "dtpMktDate";
            this.dtpMktDate.Size = new System.Drawing.Size(121, 21);
            this.dtpMktDate.TabIndex = 16;
            this.dtpMktDate.Tag = this.cell2;
            this.dtpMktDate.yssEnabled = true;
            this.dtpMktDate.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpMktDate.yssInterval = false;
            this.dtpMktDate.yssLabelStr = "至";
            this.dtpMktDate.yssShowCheckBox = false;
            this.dtpMktDate.yssShowOperLable = false;
            this.dtpMktDate.YssShowSecond = true;
            this.dtpMktDate.yssTimeControl = false;
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
            this.cell4.Text = "业务类型：";
            // 
            // cell5
            // 
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.cboBusiType;
            // 
            // cboBusiType
            // 
            this.cboBusiType.AddedSelItemName = "";
            this.cboBusiType.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboBusiType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboBusiType.Border.Bottom = true;
            this.cboBusiType.Border.Left = true;
            this.cboBusiType.Border.Right = true;
            this.cboBusiType.Border.Top = true;
            this.cboBusiType.ClassName = "";
            this.cboBusiType.DllName = "dll";
            this.cboBusiType.FilterCond = "";
            this.cboBusiType.IsFillDecimal = false;
            this.cboBusiType.Location = new System.Drawing.Point(354, 43);
            this.cboBusiType.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo2.MethodName = "getDataListByTypes";
            controlMethodInfo2.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo2.MethodParams")));
            controlMethodInfo2.MethodParamValues = new string[] {
        "HG_GP_LV,"};
            controlMethodInfo2.Methods = null;
            this.cboBusiType.MethodInfo = controlMethodInfo2;
            this.cboBusiType.Name = "cboBusiType";
            this.cboBusiType.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconIndent;
            this.cboBusiType.QueryCond = "BID_TYPE";
            this.cboBusiType.QueryType = "CacheType";
            this.cboBusiType.Size = new System.Drawing.Size(121, 21);
            this.cboBusiType.TabIndex = 15;
            this.cboBusiType.Tag = this.cell5;
            this.cboBusiType.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.cboBusiType.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboBusiType.YssCaption = "柜台回购利率业务方式";
            this.cboBusiType.YssIsMust = true;
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
            this.cell6.Text = "   回购期限：";
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.InnerControl = this.cbodur;
            // 
            // cbodur
            // 
            this.cbodur.AddedSelItemName = "";
            this.cbodur.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cbodur.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cbodur.Border.Bottom = true;
            this.cbodur.Border.Left = true;
            this.cbodur.Border.Right = true;
            this.cbodur.Border.Top = true;
            this.cbodur.ClassName = "";
            this.cbodur.DllName = "dll";
            this.cbodur.FilterCond = "";
            this.cbodur.IsFillDecimal = false;
            this.cbodur.Location = new System.Drawing.Point(111, 66);
            this.cbodur.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo3.MethodName = "getDataListByTypes";
            controlMethodInfo3.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo3.MethodParams")));
            controlMethodInfo3.MethodParamValues = new string[] {
        "GTHGHQ,"};
            controlMethodInfo3.Methods = null;
            this.cbodur.MethodInfo = controlMethodInfo3;
            this.cbodur.Name = "cbodur";
            this.cbodur.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconIndent;
            this.cbodur.QueryCond = "BID_TYPE";
            this.cbodur.QueryType = "CacheType";
            this.cbodur.Size = new System.Drawing.Size(121, 21);
            this.cbodur.TabIndex = 14;
            this.cbodur.Tag = this.cell7;
            this.cbodur.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.cbodur.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cbodur.YssCaption = "回购期限";
            this.cbodur.YssIsMust = true;
            // 
            // cell8
            // 
            this.cell8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell8.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell9
            // 
            this.cell9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell9.Text = "回购利率：";
            // 
            // cell10
            // 
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.InnerControl = this.txtRate;
            // 
            // txtRate
            // 
            this.txtRate.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.txtRate.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.txtRate.Border.Bottom = true;
            this.txtRate.Border.Left = true;
            this.txtRate.Border.Right = true;
            this.txtRate.Border.Top = true;
            this.txtRate.IsFillDecimal = false;
            this.txtRate.KeepDesignValue = true;
            this.txtRate.Location = new System.Drawing.Point(354, 66);
            this.txtRate.Name = "txtRate";
            this.txtRate.Size = new System.Drawing.Size(121, 21);
            this.txtRate.Sufix = "%";
            this.txtRate.TabIndex = 28;
            this.txtRate.Tag = this.cell10;
            this.txtRate.Text = "0";
            this.txtRate.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtRate.YssCaption = "基准收益";
            this.txtRate.YssIsMust = true;
            this.txtRate.YssKiloDelimiter = true;
            this.txtRate.YssLength = 4;
            this.txtRate.YssNumeric = "2, 2";
            // 
            // row5
            // 
            this.row5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row5.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row5.FullRowSelected = false;
            this.row5.Height = 10;
            this.row5.IsGroup = true;
            this.row5.Text = null;
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
            this.cell11.Text = "   启用日期：";
            // 
            // cell12
            // 
            this.cell12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell12.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell12.InnerControl = this.dtpBegin;
            // 
            // dtpBegin
            // 
            this.dtpBegin.BackColor = System.Drawing.Color.Transparent;
            this.dtpBegin.DateBeginChecked = true;
            this.dtpBegin.DateEndChecked = true;
            this.dtpBegin.Location = new System.Drawing.Point(111, 99);
            this.dtpBegin.Margin = new System.Windows.Forms.Padding(0);
            this.dtpBegin.Name = "dtpBegin";
            this.dtpBegin.Size = new System.Drawing.Size(121, 21);
            this.dtpBegin.TabIndex = 17;
            this.dtpBegin.Tag = this.cell12;
            this.dtpBegin.yssEnabled = true;
            this.dtpBegin.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpBegin.yssInterval = false;
            this.dtpBegin.yssLabelStr = "至";
            this.dtpBegin.yssShowCheckBox = false;
            this.dtpBegin.yssShowOperLable = false;
            this.dtpBegin.YssShowSecond = true;
            this.dtpBegin.yssTimeControl = false;
            // 
            // cell13
            // 
            this.cell13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell13.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell14
            // 
            this.cell14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell14.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell14.Text = "停用日期：";
            // 
            // cell15
            // 
            this.cell15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell15.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell15.InnerControl = this.dtpEnd;
            // 
            // dtpEnd
            // 
            this.dtpEnd.BackColor = System.Drawing.Color.Transparent;
            this.dtpEnd.DateBeginChecked = true;
            this.dtpEnd.DateEndChecked = true;
            this.dtpEnd.Location = new System.Drawing.Point(354, 99);
            this.dtpEnd.Margin = new System.Windows.Forms.Padding(0);
            this.dtpEnd.Name = "dtpEnd";
            this.dtpEnd.Size = new System.Drawing.Size(121, 21);
            this.dtpEnd.TabIndex = 18;
            this.dtpEnd.Tag = this.cell15;
            this.dtpEnd.yssEnabled = true;
            this.dtpEnd.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpEnd.yssInterval = false;
            this.dtpEnd.yssLabelStr = "至";
            this.dtpEnd.yssShowCheckBox = false;
            this.dtpEnd.yssShowOperLable = false;
            this.dtpEnd.YssShowSecond = true;
            this.dtpEnd.yssTimeControl = false;
            // 
            // Frm_SEC_LL_GT_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(493, 206);
            this.DoubleBuffered = true;
            this.Name = "Frm_SEC_LL_GT_S";
            this.Text = "回购收益行情";
            this.YssOnAfterCopyClick += new FrmBaseSet.AfterCopyClick(this.Frm_SEC_LL_GT_S_YssOnAfterCopyClick);
            this.YssOnAfterNewClick += new FrmBaseSet.AfterNewClick(this.Frm_SEC_LL_GT_S_YssOnAfterNewClick);
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
        private Yss.KTable.Models.Row row3;
        private Yss.KTable.Models.Row row4;
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
        private Yss.KTable.Models.Row row5;
        private Yss.KTable.Models.Row row7;
        private Yss.KTable.Models.Cell cell11;
        private Yss.KTable.Models.Cell cell12;
        private Yss.KTable.Models.Cell cell13;
        private Yss.KTable.Models.Cell cell14;
        private Yss.KTable.Models.Cell cell15;
        protected FAST.Core.BaseControl.YssSelCombox cbodur;
        protected FAST.Core.BaseControl.YssSelCombox cboBusiType;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpMktDate;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpBegin;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpEnd;
        protected Yss.KRichEx.ImprovedTextBox txtRate;
    }
}