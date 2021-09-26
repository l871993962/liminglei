////using YssBaseCls.Fun;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;

using FAST.Core.Resource;

////namespace YssData.Form.Mp
namespace YssSecInformation.Mp.SecMktMap.Form
{
    partial class Frm_SEC_MKT_CW_S
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
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo4 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo2 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo3 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo1 = new FAST.Core.BaseControl.ControlMethodInfo();
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
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.selSecurity = new FAST.Core.BaseControl.YssSelCombox();
            this.txtNAVValue = new Yss.KRichEx.YssTextBox();
            this.row4 = new Yss.KTable.Models.Row();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.dtpNAVDate = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.dtpPubDate = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.row5 = new Yss.KTable.Models.Row();
            this.cell13 = new Yss.KTable.Models.Cell();
            this.cell14 = new Yss.KTable.Models.Cell();
            this.cell15 = new Yss.KTable.Models.Cell();
            this.cell16 = new Yss.KTable.Models.Cell();
            this.cell17 = new Yss.KTable.Models.Cell();
            this.selDvPlat = new FAST.Core.BaseControl.YssSelCombox();
            this.mktcls = new FAST.Core.BaseControl.YssSelCombox();
            this.row6 = new Yss.KTable.Models.Row();
            this.cell18 = new Yss.KTable.Models.Cell();
            this.cell19 = new Yss.KTable.Models.Cell();
            this.cboHqzt = new FAST.Core.BaseControl.YssSelCombox();
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
            this.tbMain.Controls.Add(this.cboHqzt);
            this.tbMain.Controls.Add(this.selDvPlat);
            this.tbMain.Controls.Add(this.dtpPubDate);
            this.tbMain.Controls.Add(this.dtpNAVDate);
            this.tbMain.Controls.Add(this.mktcls);
            this.tbMain.Controls.Add(this.txtNAVValue);
            this.tbMain.Controls.Add(this.selSecurity);
            this.tbMain.GridLineColor = System.Drawing.Color.Maroon;
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3,
            this.row4,
            this.row5,
            this.row6});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(493, 158);
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
            this.stBarBottom.Location = new System.Drawing.Point(0, 188);
            this.stBarBottom.StatuType = "新增(&Add...)";
            // 
            // pnlMain
            // 
            this.pnlMain.Size = new System.Drawing.Size(493, 213);
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
            this.yssPanel1.Size = new System.Drawing.Size(493, 213);
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
            this.column4.Width = 98;
            // 
            // column5
            // 
            this.column5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column5.Width = 122;
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("新宋体", 10F, System.Drawing.FontStyle.Bold);
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
            this.row3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row3.FullRowSelected = false;
            this.row3.GroupLineLength = 310;
            this.row3.GroupPosition = 13;
            this.row3.Height = 25;
            this.row3.Text = null;
            // 
            // cell1
            // 
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.Text = "   交易证券：";
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.selSecurity;
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
            this.cell4.Text = "单位净值/收益：";
            // 
            // cell5
            // 
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.txtNAVValue;
            // 
            // selSecurity
            // 
            this.selSecurity.AddedSelItemName = "";
            this.selSecurity.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.selSecurity.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.selSecurity.Border.BorderColorHot = System.Drawing.Color.FromArgb(((int)(((byte)(109)))), ((int)(((byte)(129)))), ((int)(((byte)(165)))));
            this.selSecurity.Border.Bottom = true;
            this.selSecurity.Border.Left = true;
            this.selSecurity.Border.Right = true;
            this.selSecurity.Border.Top = true;
            this.selSecurity.DisplayName = "C_SEC_NAME";
            this.selSecurity.DisplayStyle = Yss.KRichEx.AutoFilter.DisplayStyle.Modal;
            this.selSecurity.DisplayValue = "C_SEC_CODE";
            this.selSecurity.FilterCond = "";
            this.selSecurity.Location = new System.Drawing.Point(110, 43);
            this.selSecurity.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo4.MethodName = "getDataListByTypes";
            controlMethodInfo4.MethodParams = null;
            controlMethodInfo4.MethodParamValues = new string[] {
        "JJ,LC"};
            controlMethodInfo4.Methods = null;
            this.selSecurity.MethodInfo = controlMethodInfo4;
            this.selSecurity.Name = "selSecurity";
            this.selSecurity.Parameter = "C_SEC_CODE~C_SEC_NAME";
            this.selSecurity.QueryCond = "JJ,LC";
            this.selSecurity.QueryType = "CacheType";
            this.selSecurity.Size = new System.Drawing.Size(121, 21);
            this.selSecurity.TabIndex = 6;
            this.selSecurity.Tag = this.cell2;
            this.selSecurity.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Value_Name;
            ////this.selSecurity.YssAssociaType = new FAST.Core.Context.Associa("sec");
            this.selSecurity.YssAssociaType = YssSecInformation.Support.Context.AssociaType.sv_sec;
            this.selSecurity.YssCaption = "交易证券";
            this.selSecurity.YssIsMust = true;
            this.selSecurity.SelectedValueChanged += new System.EventHandler(this.selSecurity_SelectedValueChanged);
            // 
            // txtNAVValue
            // 
            this.txtNAVValue.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.txtNAVValue.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.txtNAVValue.ForeColor = System.Drawing.Color.Blue;
            this.txtNAVValue.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtNAVValue.Location = new System.Drawing.Point(360, 43);
            this.txtNAVValue.Name = "txtNAVValue";
            this.txtNAVValue.Size = new System.Drawing.Size(121, 21);
            this.txtNAVValue.TabIndex = 1;
            this.txtNAVValue.Tag = this.cell5;
            this.txtNAVValue.Text = "0";
            this.txtNAVValue.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtNAVValue.YssCaption = "净值/收益";
            this.txtNAVValue.YssIsMust = true;
            this.txtNAVValue.YssKiloDelimiter = true;
            this.txtNAVValue.YssLength = 30;
            this.txtNAVValue.YssNumeric = "15, 15";
            // 
            // row4
            // 
            this.row4.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell9,
            this.cell10,
            this.cell6,
            this.cell7,
            this.cell8});
            this.row4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row4.FullRowSelected = false;
            this.row4.GroupLineLength = 310;
            this.row4.GroupPosition = 13;
            this.row4.Height = 25;
            this.row4.Text = null;
            // 
            // cell9
            // 
            this.cell9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell9.Text = "   净值日期：";
            // 
            // cell10
            // 
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.InnerControl = this.dtpNAVDate;
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F);
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.Text = "公告日期：";
            // 
            // cell8
            // 
            this.cell8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.cell8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell8.InnerControl = this.dtpPubDate;
            // 
            // dtpNAVDate
            // 
            this.dtpNAVDate.BackColor = System.Drawing.Color.Transparent;
            this.dtpNAVDate.DateBeginChecked = true;
            this.dtpNAVDate.DateEndChecked = true;
            this.dtpNAVDate.Location = new System.Drawing.Point(110, 68);
            this.dtpNAVDate.Margin = new System.Windows.Forms.Padding(0);
            this.dtpNAVDate.Name = "dtpNAVDate";
            this.dtpNAVDate.Size = new System.Drawing.Size(121, 21);
            this.dtpNAVDate.TabIndex = 2;
            this.dtpNAVDate.Tag = this.cell10;
            this.dtpNAVDate.yssEnabled = true;
            this.dtpNAVDate.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpNAVDate.yssInterval = false;
            this.dtpNAVDate.yssLabelStr = "至";
            this.dtpNAVDate.yssShowOperLable = false;
            this.dtpNAVDate.YssShowSecond = true;
            this.dtpNAVDate.yssTimeControl = false;
            this.dtpNAVDate.FirstdateTimeInputValueChanged += new System.EventHandler(this.dtpNAVDate_FirstdateTimeInputValueChanged);
            // 
            // cell12
            // 
            this.cell12.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // dtpPubDate
            // 
            this.dtpPubDate.BackColor = System.Drawing.Color.Transparent;
            this.dtpPubDate.DateBeginChecked = true;
            this.dtpPubDate.DateEndChecked = true;
            this.dtpPubDate.Location = new System.Drawing.Point(360, 68);
            this.dtpPubDate.Margin = new System.Windows.Forms.Padding(0);
            this.dtpPubDate.Name = "dtpPubDate";
            this.dtpPubDate.Size = new System.Drawing.Size(121, 21);
            this.dtpPubDate.TabIndex = 3;
            this.dtpPubDate.Tag = this.cell8;
            this.dtpPubDate.yssEnabled = true;
            this.dtpPubDate.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpPubDate.yssInterval = false;
            this.dtpPubDate.yssLabelStr = "至";
            this.dtpPubDate.yssShowOperLable = false;
            this.dtpPubDate.YssShowSecond = true;
            this.dtpPubDate.yssTimeControl = false;
            // 
            // cell11
            // 
            this.cell11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell11.Text = "  公告日期：";
            // 
            // row5
            // 
            this.row5.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell13,
            this.cell14,
            this.cell15,
            this.cell16,
            this.cell17});
            this.row5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row5.FullRowSelected = false;
            this.row5.Height = 25;
            this.row5.Text = null;
            // 
            // cell13
            // 
            this.cell13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell13.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell13.Text = "   行情来源：";
            // 
            // cell14
            // 
            this.cell14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell14.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell14.InnerControl = this.selDvPlat;
            // 
            // cell15
            // 
            this.cell15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell15.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell16
            // 
            this.cell16.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell16.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell16.Text = "行情分类：";
            // 
            // cell17
            // 
            this.cell17.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell17.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell17.InnerControl = this.mktcls;
            // 
            // selDvPlat
            // 
            this.selDvPlat.AddedSelItemName = "";
            this.selDvPlat.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.selDvPlat.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.selDvPlat.Border.BorderColorHot = System.Drawing.Color.FromArgb(((int)(((byte)(109)))), ((int)(((byte)(129)))), ((int)(((byte)(165)))));
            this.selDvPlat.Border.Bottom = true;
            this.selDvPlat.Border.Left = true;
            this.selDvPlat.Border.Right = true;
            this.selDvPlat.Border.Top = true;
            this.selDvPlat.FilterCond = "";
            this.selDvPlat.Location = new System.Drawing.Point(110, 93);
            this.selDvPlat.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo2.MethodName = "getDataListByTypes";
            controlMethodInfo2.MethodParams = null;
            controlMethodInfo2.MethodParamValues = new string[] {
        "PLAT_TYPE,"};
            controlMethodInfo2.Methods = null;
            this.selDvPlat.MethodInfo = controlMethodInfo2;
            this.selDvPlat.Name = "selDvPlat";
            this.selDvPlat.QueryCond = "";
            this.selDvPlat.QueryType = "";
            this.selDvPlat.Size = new System.Drawing.Size(121, 21);
            this.selDvPlat.TabIndex = 4;
            this.selDvPlat.Tag = this.cell14;
            ////this.selDvPlat.YssAssociaType = new FAST.Core.Context.Associa("pubvocabulary");
            this.selDvPlat.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.selDvPlat.YssCaption = "行情来源";
            this.selDvPlat.YssIsMust = true;
            // 
            // mktcls
            // 
            this.mktcls.AddedSelItemName = "";
            this.mktcls.AutoValidate = System.Windows.Forms.AutoValidate.Disable;
            this.mktcls.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.mktcls.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.mktcls.Border.BorderColorHot = System.Drawing.Color.FromArgb(((int)(((byte)(109)))), ((int)(((byte)(129)))), ((int)(((byte)(165)))));
            this.mktcls.Border.Bottom = true;
            this.mktcls.Border.Left = true;
            this.mktcls.Border.Right = true;
            this.mktcls.Border.Top = true;
            this.mktcls.CausesValidation = false;
            this.mktcls.DisplayName = "C_DV_NAME";
            this.mktcls.DisplayValue = "C_DV_CODE";
            this.mktcls.FilterCond = "";
            this.mktcls.IsRefresh = false;
            this.mktcls.Location = new System.Drawing.Point(360, 93);
            this.mktcls.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo3.MethodName = "getDataListByTypes";
            controlMethodInfo3.MethodParams = null;
            controlMethodInfo3.MethodParamValues = new string[] {
        "MKT_CLS,"};
            controlMethodInfo3.Methods = null;
            this.mktcls.MethodInfo = controlMethodInfo3;
            this.mktcls.Name = "mktcls";
            this.mktcls.Parameter = "C_DV_CODE~C_DV_NAME";
            this.mktcls.QueryCond = "";
            this.mktcls.QueryType = "";
            this.mktcls.ShowRefresh = false;
            this.mktcls.Size = new System.Drawing.Size(121, 21);
            this.mktcls.TabIndex = 5;
            this.mktcls.Tag = this.cell17;
            ////this.mktcls.YssAssociaType = new FAST.Core.Context.Associa("pubvocabulary");
            this.mktcls.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.mktcls.YssCaption = "行情分类";
            this.mktcls.YssIsMust = true;
            // 
            // row6
            // 
            this.row6.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell18,
            this.cell19});
            this.row6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row6.FullRowSelected = false;
            this.row6.Height = 25;
            this.row6.Text = null;
            // 
            // cell18
            // 
            this.cell18.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell18.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell18.Text = "   行情状态：";
            // 
            // cell19
            // 
            this.cell19.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell19.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell19.InnerControl = this.cboHqzt;
            // 
            // cboHqzt
            // 
            this.cboHqzt.AddedSelItemName = "";
            this.cboHqzt.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboHqzt.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboHqzt.Border.BorderColorHot = System.Drawing.Color.FromArgb(((int)(((byte)(109)))), ((int)(((byte)(129)))), ((int)(((byte)(165)))));
            this.cboHqzt.Border.Bottom = true;
            this.cboHqzt.Border.Left = true;
            this.cboHqzt.Border.Right = true;
            this.cboHqzt.Border.Top = true;
            this.cboHqzt.DisplayName = "C_DV_NAME";
            this.cboHqzt.DisplayValue = "C_DV_CODE";
            this.cboHqzt.FilterCond = "";
            this.cboHqzt.IsFillDecimal = false;
            this.cboHqzt.Location = new System.Drawing.Point(110, 118);
            this.cboHqzt.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataListByTypes";
            controlMethodInfo1.MethodParams = null;
            controlMethodInfo1.MethodParamValues = new string[] {
        "HQZT_HQZL,"};
            controlMethodInfo1.Methods = null;
            this.cboHqzt.MethodInfo = controlMethodInfo1;
            this.cboHqzt.Name = "cboHqzt";
            this.cboHqzt.Parameter = "C_DV_NAME";
            this.cboHqzt.QueryCond = "HQZT_HQZL";
            this.cboHqzt.QueryType = "CacheType";
            this.cboHqzt.Size = new System.Drawing.Size(121, 21);
            this.cboHqzt.TabIndex = 7;
            this.cboHqzt.Tag = this.cell19;
            ////this.cboHqzt.YssAssociaType = new FAST.Core.Context.Associa("pubvocabulary");
            this.cboHqzt.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboHqzt.YssCaption = "行情状态";
            // 
            // Frm_SEC_MKT_CW_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(493, 213);
            this.DoubleBuffered = true;
            this.Name = "Frm_SEC_MKT_CW_S";
            this.StatuType = "新增(&Add...)";
            this.Text = "场外行情";
            this.YssSetStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.Load += new System.EventHandler(this.Frm_SEC_MKT_CW_S_Load);
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
        private Yss.KRichEx.YssTextBox txtNAVValue;
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Cell cell2;
        private Yss.KTable.Models.Cell cell3;
        private Yss.KTable.Models.Cell cell4;
        private Yss.KTable.Models.Cell cell5;
        private Yss.KTable.Models.Cell cell9;
        private Yss.KTable.Models.Cell cell10;
        private Yss.KTable.Models.Cell cell11;
        private Yss.KTable.Models.Cell cell12;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpNAVDate;
        private Yss.KTable.Models.Cell cell6;
        private Yss.KTable.Models.Cell cell7;
        private Yss.KTable.Models.Cell cell8;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpPubDate;
        private Yss.KTable.Models.Row row5;
        private Yss.KTable.Models.Cell cell13;
        private Yss.KTable.Models.Cell cell14;
        private FAST.Core.BaseControl.YssSelCombox selDvPlat;
        private Yss.KTable.Models.Cell cell15;
        private Yss.KTable.Models.Cell cell16;
        private Yss.KTable.Models.Cell cell17;
        private FAST.Core.BaseControl.YssSelCombox mktcls;
        private FAST.Core.BaseControl.YssSelCombox selSecurity;
        private Yss.KTable.Models.Row row6;
        private Yss.KTable.Models.Cell cell18;
        private FAST.Core.BaseControl.YssSelCombox cboHqzt;
        private Yss.KTable.Models.Cell cell19;
    }
}