using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;

using FAST.Core.Resource;
namespace YssSecInformation.Sv.Form
{
    partial class Frm_INDEX_INFO_S
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
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo3 = new FAST.Core.BaseControl.ControlMethodInfo();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_INDEX_INFO_S));
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo2 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo1 = new FAST.Core.BaseControl.ControlMethodInfo();
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.row3SecCode = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.txtsecCode = new Yss.KRichEx.YssTextBox();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.txtIsinCode = new Yss.KRichEx.YssTextBox();
            this.row4IndexCN = new Yss.KTable.Models.Row();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.txtIndexCode = new Yss.KRichEx.YssTextBox();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.txtIndexName = new Yss.KRichEx.YssTextBox();
            this.row3Type = new Yss.KTable.Models.Row();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.cboIndexType = new FAST.Core.BaseControl.YssSelCombox();
            this.cell13 = new Yss.KTable.Models.Cell();
            this.cell14 = new Yss.KTable.Models.Cell();
            this.cell15 = new Yss.KTable.Models.Cell();
            this.cboOrgCode = new FAST.Core.BaseControl.YssSelCombox();
            this.row3Dc = new Yss.KTable.Models.Row();
            this.cell16 = new Yss.KTable.Models.Cell();
            this.cell17 = new Yss.KTable.Models.Cell();
            this.cboCury = new FAST.Core.BaseControl.YssSelCombox();
            this.cell18 = new Yss.KTable.Models.Cell();
            this.cell19 = new Yss.KTable.Models.Cell();
            this.cell20 = new Yss.KTable.Models.Cell();
            this.txtBasePoint = new Yss.KRichEx.YssTextBox();
            this.row3divide = new Yss.KTable.Models.Row();
            this.row3blank = new Yss.KTable.Models.Row();
            this.row3Date = new Yss.KTable.Models.Row();
            this.cell21 = new Yss.KTable.Models.Cell();
            this.cell22 = new Yss.KTable.Models.Cell();
            this.dateBase = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell23 = new Yss.KTable.Models.Cell();
            this.cell24 = new Yss.KTable.Models.Cell();
            this.cell25 = new Yss.KTable.Models.Cell();
            this.dateEnd = new FAST.Core.BaseControl.YssDateTimeInterval();
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
            this.tbMain.Controls.Add(this.cboCury);
            this.tbMain.Controls.Add(this.txtsecCode);
            this.tbMain.Controls.Add(this.txtIsinCode);
            this.tbMain.Controls.Add(this.txtIndexCode);
            this.tbMain.Controls.Add(this.txtIndexName);
            this.tbMain.Controls.Add(this.txtBasePoint);
            this.tbMain.Controls.Add(this.dateBase);
            this.tbMain.Controls.Add(this.dateEnd);
            this.tbMain.Controls.Add(this.cboOrgCode);
            this.tbMain.Controls.Add(this.cboIndexType);
            this.tbMain.GridLineColor = System.Drawing.Color.Red;
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3SecCode,
            this.row4IndexCN,
            this.row3Type,
            this.row3Dc,
            this.row3divide,
            this.row3blank,
            this.row3Date});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(493, 202);
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
            this.stBarBottom.Location = new System.Drawing.Point(0, 233);
            // 
            // pnlMain
            // 
            this.pnlMain.Size = new System.Drawing.Size(493, 258);
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
            this.yssPanel1.Size = new System.Drawing.Size(493, 258);
            // 
            // column1
            // 
            this.column1.DataPropertyName = null;
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.Tag = null;
            this.column1.Text = "";
            this.column1.Width = 110;
            // 
            // column2
            // 
            this.column2.DataPropertyName = null;
            this.column2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column2.Tag = null;
            this.column2.Text = "";
            this.column2.Width = 122;
            // 
            // column3
            // 
            this.column3.DataPropertyName = null;
            this.column3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column3.Tag = null;
            this.column3.Text = "";
            this.column3.Width = 30;
            // 
            // column4
            // 
            this.column4.DataPropertyName = null;
            this.column4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column4.Tag = null;
            this.column4.Text = "";
            this.column4.Width = 91;
            // 
            // column5
            // 
            this.column5.DataPropertyName = null;
            this.column5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column5.Tag = null;
            this.column5.Text = "";
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
            // row3SecCode
            // 
            this.row3SecCode.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell1,
            this.cell2,
            this.cell3,
            this.cell4,
            this.cell5});
            this.row3SecCode.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row3SecCode.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row3SecCode.FullRowSelected = false;
            this.row3SecCode.GroupLineLength = 310;
            this.row3SecCode.GroupPosition = 13;
            this.row3SecCode.Height = 25;
            this.row3SecCode.Text = null;
            // 
            // cell1
            // 
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.Text = "   证券内码：";
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.txtsecCode;
            // 
            // txtsecCode
            // 
            this.txtsecCode.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.txtsecCode.Border.Class = "TextBoxBorder";
            this.txtsecCode.Location = new System.Drawing.Point(110, 43);
            this.txtsecCode.Name = "txtsecCode";
            this.txtsecCode.Size = new System.Drawing.Size(121, 21);
            this.txtsecCode.TabIndex = 12;
            this.txtsecCode.Tag = this.cell2;
            this.txtsecCode.YssCaption = "证券内码";
            this.txtsecCode.YssIsMust = true;
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
            this.cell4.Text = "ISIN代码：";
            // 
            // cell5
            // 
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.txtIsinCode;
            // 
            // txtIsinCode
            // 
            this.txtIsinCode.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.txtIsinCode.Border.Class = "TextBoxBorder";
            this.txtIsinCode.Location = new System.Drawing.Point(353, 43);
            this.txtIsinCode.Name = "txtIsinCode";
            this.txtIsinCode.Size = new System.Drawing.Size(121, 21);
            this.txtIsinCode.TabIndex = 12;
            this.txtIsinCode.Tag = this.cell5;
            this.txtIsinCode.YssCaption = "ISIN代码";
            // 
            // row4IndexCN
            // 
            this.row4IndexCN.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell6,
            this.cell7,
            this.cell8,
            this.cell9,
            this.cell10});
            this.row4IndexCN.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row4IndexCN.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row4IndexCN.FullRowSelected = false;
            this.row4IndexCN.GroupLineLength = 310;
            this.row4IndexCN.GroupPosition = 13;
            this.row4IndexCN.Height = 25;
            this.row4IndexCN.Text = null;
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.Text = "   指数代码：";
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.InnerControl = this.txtIndexCode;
            // 
            // txtIndexCode
            // 
            this.txtIndexCode.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.txtIndexCode.Border.Class = "TextBoxBorder";
            this.txtIndexCode.Location = new System.Drawing.Point(110, 68);
            this.txtIndexCode.Name = "txtIndexCode";
            this.txtIndexCode.Size = new System.Drawing.Size(121, 21);
            this.txtIndexCode.TabIndex = 12;
            this.txtIndexCode.Tag = this.cell7;
            this.txtIndexCode.YssCaption = "指数代码";
            this.txtIndexCode.YssIsMust = true;
            this.txtIndexCode.TextChanged += new System.EventHandler(this.txtIndexCode_TextChanged);
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
            this.cell9.Text = "指数名称：";
            // 
            // cell10
            // 
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.InnerControl = this.txtIndexName;
            // 
            // txtIndexName
            // 
            this.txtIndexName.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.txtIndexName.Border.Class = "TextBoxBorder";
            this.txtIndexName.Location = new System.Drawing.Point(353, 68);
            this.txtIndexName.Name = "txtIndexName";
            this.txtIndexName.Size = new System.Drawing.Size(121, 21);
            this.txtIndexName.TabIndex = 13;
            this.txtIndexName.Tag = this.cell10;
            this.txtIndexName.YssCaption = "指数名称";
            this.txtIndexName.YssIsMust = true;
            // 
            // row3Type
            // 
            this.row3Type.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell11,
            this.cell12,
            this.cell13,
            this.cell14,
            this.cell15});
            this.row3Type.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row3Type.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row3Type.FullRowSelected = false;
            this.row3Type.GroupLineLength = 310;
            this.row3Type.GroupPosition = 13;
            this.row3Type.Height = 25;
            this.row3Type.Text = null;
            // 
            // cell11
            // 
            this.cell11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell11.Text = "   指数类型：";
            // 
            // cell12
            // 
            this.cell12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell12.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell12.InnerControl = this.cboIndexType;
            // 
            // cboIndexType
            // 
            this.cboIndexType.AddedSelItemName = "";
            this.cboIndexType.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboIndexType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboIndexType.Border.Bottom = true;
            this.cboIndexType.Border.Left = true;
            this.cboIndexType.Border.Right = true;
            this.cboIndexType.Border.Top = true;
            this.cboIndexType.ClassName = "";
            this.cboIndexType.DisplayName = "C_DV_NAME";
            this.cboIndexType.DisplayValue = "C_DV_CODE";
            this.cboIndexType.DllName = "YssControls.dll";
            this.cboIndexType.FilterCond = "";
            this.cboIndexType.IsFillDecimal = false;
            this.cboIndexType.Location = new System.Drawing.Point(110, 93);
            this.cboIndexType.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo3.MethodName = "getDataListByTypes";
            controlMethodInfo3.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo3.MethodParams")));
            controlMethodInfo3.MethodParamValues = new string[] {
        "INDEX_TYPE,"};
            controlMethodInfo3.Methods = null;
            this.cboIndexType.MethodInfo = controlMethodInfo3;
            this.cboIndexType.Name = "cboIndexType";
            this.cboIndexType.Padding = new System.Windows.Forms.Padding(1, 3, 1, 3);
            this.cboIndexType.Parameter = "C_DV_NAME";
            this.cboIndexType.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconIndent;
            this.cboIndexType.QueryCond = "ISSUE_MODE";
            this.cboIndexType.QueryType = "CacheType";
            this.cboIndexType.Size = new System.Drawing.Size(121, 21);
            this.cboIndexType.TabIndex = 12;
            this.cboIndexType.Tag = this.cell12;
            this.cboIndexType.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboIndexType.YssCaption = "指数类型";
            this.cboIndexType.YssIsMust = true;
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
            this.cell14.Text = "指数编制机构：";
            // 
            // cell15
            // 
            this.cell15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell15.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell15.InnerControl = this.cboOrgCode;
            // 
            // cboOrgCode
            // 
            this.cboOrgCode.AddedSelItemName = "";
            this.cboOrgCode.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboOrgCode.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboOrgCode.Border.Bottom = true;
            this.cboOrgCode.Border.Left = true;
            this.cboOrgCode.Border.Right = true;
            this.cboOrgCode.Border.Top = true;
            this.cboOrgCode.ClassName = "";
            this.cboOrgCode.DisplayName = "C_ORG_NAME";
            this.cboOrgCode.DisplayValue = "C_ORG_CODE";
            this.cboOrgCode.DllName = "YssControls.dll";
            this.cboOrgCode.FilterCond = "";
            this.cboOrgCode.IsFillDecimal = false;
            this.cboOrgCode.KTableTree = true;
            this.cboOrgCode.Location = new System.Drawing.Point(353, 93);
            this.cboOrgCode.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo2.MethodName = "getDataList";
            controlMethodInfo2.MethodParams = null;
            controlMethodInfo2.MethodParamValues = null;
            controlMethodInfo2.Methods = new string[] {
        "getDataListByTypes",
        "getQueryResByTypes",
        "getDataListByKeys",
        "getQueryResByKeys",
        "getDataByCode",
        "getDataList",
        "getDataListRes",
        "getKeyConvertMap"};
            this.cboOrgCode.MethodInfo = controlMethodInfo2;
            this.cboOrgCode.Name = "cboOrgCode";
            this.cboOrgCode.NodeID = "C_ORG_CODE";
            this.cboOrgCode.Padding = new System.Windows.Forms.Padding(1, 2, 1, 3);
            this.cboOrgCode.Parameter = "C_ORG_CODE~C_ORG_NAME";
            this.cboOrgCode.ParaNodeID = "C_ORG_CODE_P";
            this.cboOrgCode.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconIndent;
            this.cboOrgCode.QueryCond = " where a.n_check_state = 1";
            this.cboOrgCode.QueryType = "";
            this.cboOrgCode.Size = new System.Drawing.Size(121, 21);
            this.cboOrgCode.SortColumn = "C_ORG_NAME";
            this.cboOrgCode.TabIndex = 12;
            this.cboOrgCode.Tag = this.cell15;
            this.cboOrgCode.YssAssociaType = YssInformation.Support.Context.AssociaType.base_organ;
            this.cboOrgCode.YssCaption = "指数编制机构";
            this.cboOrgCode.YssDataSrc = FAST.Core.Context.ClsEnums.DataSrc.SrcDB;
            this.cboOrgCode.YssIsMust = true;
            this.cboOrgCode.SelectedValueChanged += new System.EventHandler(this.cboOrgCode_SelectedValueChanged);
            // 
            // row3Dc
            // 
            this.row3Dc.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell16,
            this.cell17,
            this.cell18,
            this.cell19,
            this.cell20});
            this.row3Dc.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row3Dc.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row3Dc.FullRowSelected = false;
            this.row3Dc.GroupLineLength = 310;
            this.row3Dc.GroupPosition = 13;
            this.row3Dc.Height = 25;
            this.row3Dc.Text = null;
            // 
            // cell16
            // 
            this.cell16.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell16.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell16.Text = "   交易币种：";
            // 
            // cell17
            // 
            this.cell17.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell17.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell17.InnerControl = this.cboCury;
            // 
            // cboCury
            // 
            this.cboCury.AddedSelItemName = "";
            this.cboCury.BackColor = System.Drawing.Color.White;
            // 
            // 
            // 
            this.cboCury.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboCury.Border.Bottom = true;
            this.cboCury.Border.Left = true;
            this.cboCury.Border.Right = true;
            this.cboCury.Border.Top = true;
            this.cboCury.ClassName = "";
            this.cboCury.DisplayName = "C_DC_NAME";
            this.cboCury.DisplayValue = "C_DC_CODE";
            this.cboCury.DllName = "YssControls.dll";
            this.cboCury.FilterCond = "";
            this.cboCury.IsFillDecimal = false;
            this.cboCury.KeepDesignValue = false;
            this.cboCury.Location = new System.Drawing.Point(110, 118);
            this.cboCury.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataList";
            controlMethodInfo1.MethodParams = null;
            controlMethodInfo1.MethodParamValues = null;
            controlMethodInfo1.Methods = null;
            this.cboCury.MethodInfo = controlMethodInfo1;
            this.cboCury.Name = "cboCury";
            this.cboCury.Padding = new System.Windows.Forms.Padding(1, 2, 1, 3);
            this.cboCury.Parameter = "C_DC_NAME";
            this.cboCury.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconIndent;
            this.cboCury.QueryCond = "";
            this.cboCury.QueryType = "";
            this.cboCury.Size = new System.Drawing.Size(121, 21);
            this.cboCury.TabIndex = 12;
            this.cboCury.Tag = this.cell17;
            this.cboCury.YssAssociaType = YssInformation.Support.Context.AssociaType.base_currency;
            this.cboCury.YssCaption = "交易货币";
            this.cboCury.YssIsMust = true;
            // 
            // cell18
            // 
            this.cell18.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell18.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell19
            // 
            this.cell19.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell19.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell19.Text = "指数基准点数：";
            // 
            // cell20
            // 
            this.cell20.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell20.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell20.InnerControl = this.txtBasePoint;
            // 
            // txtBasePoint
            // 
            this.txtBasePoint.AutoTooltip = false;
            this.txtBasePoint.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.txtBasePoint.Border.Class = "TextBoxBorder";
            this.txtBasePoint.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.txtBasePoint.ForeColor = System.Drawing.Color.Blue;
            this.txtBasePoint.Location = new System.Drawing.Point(353, 118);
            this.txtBasePoint.Name = "txtBasePoint";
            this.txtBasePoint.Size = new System.Drawing.Size(121, 21);
            this.txtBasePoint.TabIndex = 17;
            this.txtBasePoint.Tag = this.cell20;
            this.txtBasePoint.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtBasePoint.YssCaption = "指数基准点数";
            this.txtBasePoint.YssIsMust = true;
            this.txtBasePoint.YssKiloDelimiter = true;
            this.txtBasePoint.YssLength = 18;
            this.txtBasePoint.YssNumeric = "14, 4";
            // 
            // row3divide
            // 
            this.row3divide.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row3divide.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row3divide.FullRowSelected = false;
            this.row3divide.GroupLineLength = 310;
            this.row3divide.GroupPosition = 13;
            this.row3divide.Height = 10;
            this.row3divide.IsGroup = true;
            this.row3divide.Text = null;
            // 
            // row3blank
            // 
            this.row3blank.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row3blank.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row3blank.FullRowSelected = false;
            this.row3blank.GroupLineLength = 310;
            this.row3blank.GroupPosition = 13;
            this.row3blank.Height = 5;
            this.row3blank.Text = null;
            // 
            // row3Date
            // 
            this.row3Date.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell21,
            this.cell22,
            this.cell23,
            this.cell24,
            this.cell25});
            this.row3Date.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row3Date.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row3Date.FullRowSelected = false;
            this.row3Date.Text = null;
            // 
            // cell21
            // 
            this.cell21.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell21.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell21.Text = "   基准日期：";
            // 
            // cell22
            // 
            this.cell22.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell22.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell22.InnerControl = this.dateBase;
            // 
            // dateBase
            // 
            this.dateBase.BackColor = System.Drawing.Color.Transparent;
            this.dateBase.Location = new System.Drawing.Point(110, 158);
            this.dateBase.Margin = new System.Windows.Forms.Padding(0);
            this.dateBase.Name = "dateBase";
            this.dateBase.ReadOnly = false;
            this.dateBase.Size = new System.Drawing.Size(121, 21);
            this.dateBase.TabIndex = 12;
            this.dateBase.Tag = this.cell22;
            this.dateBase.yssEnabled = true;
            this.dateBase.yssFormatDateStr = "yyyy-MM-dd";
            this.dateBase.yssInterval = false;
            this.dateBase.yssLabelStr = "至";
            this.dateBase.yssShowCheckBox = false;
            this.dateBase.yssShowOperLable = false;
            this.dateBase.YssShowSecond = true;
            this.dateBase.yssTimeControl = false;
            this.dateBase.BusinessDate = true;
            // 
            // cell23
            // 
            this.cell23.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell23.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell24
            // 
            this.cell24.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell24.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell24.Text = "结束日期：";
            // 
            // cell25
            // 
            this.cell25.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell25.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell25.InnerControl = this.dateEnd;
            // 
            // dateEnd
            // 
            this.dateEnd.BackColor = System.Drawing.Color.Transparent;
            this.dateEnd.Location = new System.Drawing.Point(353, 158);
            this.dateEnd.Margin = new System.Windows.Forms.Padding(0);
            this.dateEnd.Name = "dateEnd";
            this.dateEnd.ReadOnly = false;
            this.dateEnd.Size = new System.Drawing.Size(121, 21);
            this.dateEnd.TabIndex = 13;
            this.dateEnd.Tag = this.cell25;
            this.dateEnd.yssEnabled = true;
            this.dateEnd.yssFormatDateStr = "yyyy-MM-dd";
            this.dateEnd.yssInterval = false;
            this.dateEnd.yssLabelStr = "至";
            this.dateEnd.yssShowCheckBox = false;
            this.dateEnd.yssShowOperLable = false;
            this.dateEnd.YssShowSecond = true;
            this.dateEnd.yssTimeControl = false;
            this.dateEnd.BusinessDate = true;
            // 
            // Frm_INDEX_INFO_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(493, 258);
            this.DoubleBuffered = true;
            this.Name = "Frm_INDEX_INFO_S";
            this.Text = "指数基本信息";
            this.YssSetStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            ////this.Load += new System.EventHandler(this.Frm_INDEX_INFO_S_Load);
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
        private Yss.KTable.Models.Row row3SecCode;
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Cell cell2;
        private Yss.KTable.Models.Cell cell3;
        private Yss.KTable.Models.Cell cell4;
        private Yss.KTable.Models.Cell cell5;
        private Yss.KTable.Models.Row row4IndexCN;
        private Yss.KTable.Models.Cell cell6;
        private Yss.KTable.Models.Cell cell7;
        private Yss.KTable.Models.Cell cell8;
        private Yss.KTable.Models.Cell cell9;
        private Yss.KTable.Models.Cell cell10;
        private Yss.KTable.Models.Row row3Type;
        private Yss.KTable.Models.Cell cell11;
        private Yss.KTable.Models.Cell cell12;
        private Yss.KTable.Models.Cell cell13;
        private Yss.KTable.Models.Cell cell14;
        private Yss.KTable.Models.Cell cell15;
        private Yss.KTable.Models.Row row3Dc;
        private Yss.KTable.Models.Cell cell16;
        private Yss.KTable.Models.Cell cell17;
        private Yss.KTable.Models.Cell cell18;
        private Yss.KTable.Models.Cell cell19;
        private Yss.KTable.Models.Cell cell20;
        private Yss.KTable.Models.Row row3divide;
        private Yss.KTable.Models.Row row3blank;
        private Yss.KTable.Models.Row row3Date;
        private Yss.KTable.Models.Cell cell21;
        private Yss.KTable.Models.Cell cell22;
        private Yss.KTable.Models.Cell cell23;
        private Yss.KTable.Models.Cell cell24;
        private Yss.KTable.Models.Cell cell25;
        private FAST.Core.BaseControl.YssSelCombox cboCury;
        private Yss.KRichEx.YssTextBox txtsecCode;
        private Yss.KRichEx.YssTextBox txtIsinCode;
        private Yss.KRichEx.YssTextBox txtIndexCode;
        private Yss.KRichEx.YssTextBox txtIndexName;
        private Yss.KRichEx.YssTextBox txtBasePoint;
        private FAST.Core.BaseControl.YssDateTimeInterval dateBase;
        private FAST.Core.BaseControl.YssDateTimeInterval dateEnd;
        private FAST.Core.BaseControl.YssSelCombox cboOrgCode;
        private FAST.Core.BaseControl.YssSelCombox cboIndexType;
    }
}