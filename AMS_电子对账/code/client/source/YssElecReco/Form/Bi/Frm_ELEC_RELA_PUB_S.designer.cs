using FAST.Core.BaseControl;
using FAST.Core.Context;
namespace YssElecReco.Form.Bi
{
    partial class Frm_ELEC_RELA_PUB_S
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_ELEC_RELA_PUB_S));
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo2 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo5 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo1 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo6 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo4 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo7 = new FAST.Core.BaseControl.ControlMethodInfo();
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
            this.txtZbCode = new Yss.KRichEx.YssTextBox();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.txtZbName = new Yss.KRichEx.YssTextBox();
            this.row4 = new Yss.KTable.Models.Row();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.cboDzType = new FAST.Core.BaseControl.YssSelCombox();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.cboClassType = new FAST.Core.BaseControl.YssSelCombox();
            this.cboClsPort = new FAST.Core.BaseControl.YssSelCombox();
            this.cell15 = new Yss.KTable.Models.Cell();
            this.row5 = new Yss.KTable.Models.Row();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.cboClassGrade = new FAST.Core.BaseControl.YssSelCombox();
            this.cell13 = new Yss.KTable.Models.Cell();
            this.cell14 = new Yss.KTable.Models.Cell();
            this.cboQuota = new FAST.Core.BaseControl.YssSelCombox();
            this.cboOrg = new FAST.Core.BaseControl.YssSelCombox();
            this.cell20 = new Yss.KTable.Models.Cell();
            this.row6 = new Yss.KTable.Models.Row();
            this.cell16 = new Yss.KTable.Models.Cell();
            this.cell17 = new Yss.KTable.Models.Cell();
            this.cboClassLevel = new FAST.Core.BaseControl.YssSelCombox();
            this.cell18 = new Yss.KTable.Models.Cell();
            this.cell19 = new Yss.KTable.Models.Cell();
            this.row7 = new Yss.KTable.Models.Row();
            this.row8 = new Yss.KTable.Models.Row();
            this.row9 = new Yss.KTable.Models.Row();
            this.cwbbLabel = new Yss.KTable.Models.Cell();
            this.cwbbCell = new Yss.KTable.Models.Cell();
            this.selCwbb = new FAST.Core.BaseControl.YssSelCombox();
            this.cell21 = new Yss.KTable.Models.Cell();
            this.zdhLabel = new Yss.KTable.Models.Cell();
            this.zdhCell = new Yss.KTable.Models.Cell();
            this.txtZdh = new Yss.KRichEx.YssTextBox();
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
            this.tbMain.Controls.Add(this.txtZdh);
            this.tbMain.Controls.Add(this.cboClassGrade);
            this.tbMain.Controls.Add(this.cboClassType);
            this.tbMain.Controls.Add(this.cboDzType);
            this.tbMain.Controls.Add(this.cboOrg);
            this.tbMain.Controls.Add(this.cboClsPort);
            this.tbMain.Controls.Add(this.cboQuota);
            this.tbMain.Controls.Add(this.txtZbName);
            this.tbMain.Controls.Add(this.txtZbCode);
            this.tbMain.Controls.Add(this.selCwbb);
            this.tbMain.Controls.Add(this.cboClassLevel);
            this.tbMain.Cursor = System.Windows.Forms.Cursors.Default;
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3,
            this.row4,
            this.row5,
            this.row6,
            this.row7,
            this.row8,
            this.row9});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(493, 220);
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
            this.stBarBottom.Location = new System.Drawing.Point(0, 250);
            // 
            // pnlMain
            // 
            this.pnlMain.Size = new System.Drawing.Size(493, 275);
            // 
            // yssPanel1
            // 
            // 
            // 
            // 
            this.yssPanel1.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.yssPanel1.Size = new System.Drawing.Size(493, 275);
            // 
            // hpAssist
            // 
            // 
            // column1
            // 
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.MaxPrintWidth = 0;
            this.column1.Width = 110;
            // 
            // column2
            // 
            this.column2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column2.MaxPrintWidth = 0;
            this.column2.Width = 120;
            // 
            // column3
            // 
            this.column3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column3.MaxPrintWidth = 0;
            this.column3.Width = 30;
            // 
            // column4
            // 
            this.column4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column4.MaxPrintWidth = 0;
            this.column4.Width = 91;
            // 
            // column5
            // 
            this.column5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column5.MaxPrintWidth = 0;
            this.column5.Width = 120;
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("新宋体", 10F, System.Drawing.FontStyle.Bold);
            this.row1.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row1.FullRowSelected = false;
            this.row1.GroupLineLength = 310;
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
            this.row3.GroupLineLength = 310;
            this.row3.Height = 25;
            this.row3.Text = null;
            // 
            // cell1
            // 
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.InnerControl = null;
            this.cell1.Text = "   指标代码：";
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.txtZbCode;
            // 
            // txtZbCode
            // 
            this.txtZbCode.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.txtZbCode.Location = new System.Drawing.Point(110, 43);
            this.txtZbCode.Name = "txtZbCode";
            this.txtZbCode.Size = new System.Drawing.Size(119, 21);
            this.txtZbCode.TabIndex = 0;
            this.txtZbCode.Tag = this.cell2;
            this.txtZbCode.YssCaption = "指标代码";
            this.txtZbCode.YssIsMust = true;
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
            this.cell4.Text = "指标名称：";
            // 
            // cell5
            // 
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.txtZbName;
            // 
            // txtZbName
            // 
            this.txtZbName.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.txtZbName.Location = new System.Drawing.Point(351, 43);
            this.txtZbName.Name = "txtZbName";
            this.txtZbName.Size = new System.Drawing.Size(119, 21);
            this.txtZbName.TabIndex = 1;
            this.txtZbName.Tag = this.cell5;
            this.txtZbName.YssCaption = "指标名称";
            this.txtZbName.YssIsMust = true;
            this.txtZbName.YssLength = 100;
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
            this.row4.GroupLineLength = 310;
            this.row4.Height = 25;
            this.row4.Text = null;
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.InnerControl = null;
            this.cell6.Text = "   对账类型：";
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.InnerControl = this.cboDzType;
            // 
            // cboDzType
            // 
            this.cboDzType.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboDzType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboDzType.DisplayName = "C_DZ_NAME";
            this.cboDzType.DisplayValue = "C_DZ_CODE";
            this.cboDzType.FilterCond = "";
            this.cboDzType.IsFillDecimal = false;
            this.cboDzType.KTableTree = true;
            this.cboDzType.Location = new System.Drawing.Point(110, 68);
            this.cboDzType.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo3.MethodName = "getDataList";
            controlMethodInfo3.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo3.MethodParams")));
            controlMethodInfo3.MethodParamValues = null;
            controlMethodInfo3.Methods = null;
            this.cboDzType.MethodInfo = controlMethodInfo3;
            this.cboDzType.Name = "cboDzType";
            this.cboDzType.NodeID = "C_DZ_CODE";
            this.cboDzType.Parameter = "C_DZ_NAME";
            this.cboDzType.ParaNodeID = "C_DZ_CODE_P";
            this.cboDzType.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.cboDzType.QueryCond = "";
            this.cboDzType.QueryType = "";
            this.cboDzType.Size = new System.Drawing.Size(119, 21);
            this.cboDzType.SortColumn = "C_DZ_NAME";
            this.cboDzType.TabIndex = 2;
            this.cboDzType.Tag = this.cell7;
            this.cboDzType.YssAssociaType = YssElecReco.Context.AssociaType.base_erdztype;
            this.cboDzType.YssCaption = "对账类型";
            this.cboDzType.YssIsMust = true;
            this.cboDzType.SelectedValueChanged += new System.EventHandler(this.cboDzType_SelectedValueChanged);
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
            this.cell9.Text = "分级类型：";
            // 
            // cell10
            // 
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.InnerControl = this.cboClassType;
            // 
            // cboClassType
            // 
            this.cboClassType.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboClassType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboClassType.DisplayName = "C_DV_NAME";
            this.cboClassType.DisplayValue = "C_DV_CODE";
            this.cboClassType.FilterCond = "";
            this.cboClassType.IsFillDecimal = false;
            this.cboClassType.Location = new System.Drawing.Point(351, 68);
            this.cboClassType.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo2.MethodName = "getDataListByTypes";
            controlMethodInfo2.MethodParams = null;
            controlMethodInfo2.MethodParamValues = new string[] {
        "PORT_CLS_TYPE,"};
            controlMethodInfo2.Methods = new string[] {
        "getKeyConvertMap",
        "getDataByCode",
        "getDataList",
        "getDataListRes",
        "getDataListByTypes",
        "getQueryResByTypes",
        "getDataListByKeys",
        "getQueryResByKeys"};
            this.cboClassType.MethodInfo = controlMethodInfo2;
            this.cboClassType.Name = "cboClassType";
            this.cboClassType.Parameter = "C_DV_NAME";
            this.cboClassType.PrefixBackColor = System.Drawing.Color.White;
            this.cboClassType.QueryCond = "PORT_CLS_TYPE";
            this.cboClassType.QueryType = "CacheType";
            this.cboClassType.RequestEveryTime = true;
            this.cboClassType.Size = new System.Drawing.Size(119, 21);
            this.cboClassType.TabIndex = 6;
            this.cboClassType.Tag = this.cell10;
            this.cboClassType.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboClassType.YssCaption = "分级类型";
            this.cboClassType.SelectedValueChanged += new System.EventHandler(this.cboClassType_SelectedValueChanged);
            // 
            // cboClsPort
            // 
            this.cboClsPort.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboClsPort.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboClsPort.DisplayName = "C_PORT_CLS_NAME";
            this.cboClsPort.DisplayValue = "C_PORT_CLS_CODE";
            this.cboClsPort.FilterCond = "";
            this.cboClsPort.IsFillDecimal = false;
            this.cboClsPort.Location = new System.Drawing.Point(110, 118);
            this.cboClsPort.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo5.MethodName = "getDataList";
            controlMethodInfo5.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo5.MethodParams")));
            controlMethodInfo5.MethodParamValues = null;
            controlMethodInfo5.Methods = null;
            this.cboClsPort.MethodInfo = controlMethodInfo5;
            this.cboClsPort.Name = "cboClsPort";
            this.cboClsPort.Parameter = "C_PORT_CLS_CODE~C_PORT_CLS_NAME";
            this.cboClsPort.PrefixBackColor = System.Drawing.Color.White;
            this.cboClsPort.PrefixForeColor = System.Drawing.Color.Coral;
            this.cboClsPort.QueryCond = "";
            this.cboClsPort.QueryType = "";
            this.cboClsPort.Size = new System.Drawing.Size(119, 21);
            this.cboClsPort.TabIndex = 3;
            this.cboClsPort.Tag = this.cell17;
            this.cboClsPort.YssAssociaType = YssProductInfo.Support.Context.AssociaType.pd_productgrade;
            this.cboClsPort.YssCaption = "分级组合";
            this.cboClsPort.YssOnBeforeLoadData += new FAST.Core.BaseControl.YssSelCombox.yssBeforeLoadData(this.cboClsPort_YssOnBeforeLoadData);
            // 
            // cell15
            // 
            this.cell15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell15.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell15.InnerControl = this.cboClassLevel;
            // 
            // row5
            // 
            this.row5.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell11,
            this.cell12,
            this.cell13,
            this.cell14,
            this.cell15});
            this.row5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row5.FullRowSelected = false;
            this.row5.GroupLineLength = 310;
            this.row5.Height = 25;
            this.row5.Text = null;
            // 
            // cell11
            // 
            this.cell11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell11.InnerControl = null;
            this.cell11.Text = "   级别类型：";
            // 
            // cell12
            // 
            this.cell12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell12.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell12.InnerControl = this.cboClassGrade;
            // 
            // cboClassGrade
            // 
            this.cboClassGrade.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboClassGrade.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboClassGrade.DisplayName = "C_DV_NAME";
            this.cboClassGrade.DisplayValue = "C_DV_CODE";
            this.cboClassGrade.FilterCond = "";
            this.cboClassGrade.IsFillDecimal = false;
            this.cboClassGrade.Location = new System.Drawing.Point(110, 93);
            this.cboClassGrade.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataListByTypes";
            controlMethodInfo1.MethodParams = null;
            controlMethodInfo1.MethodParamValues = new string[] {
        "PORT_CLS,"};
            controlMethodInfo1.Methods = new string[] {
        "getPortClsCode",
        "getDataListByTypes",
        "getQueryResByTypes",
        "getDataListByKeys",
        "getQueryResByKeys",
        "getDataByCode",
        "getDataList",
        "getDataListRes",
        "getKeyConvertMap"};
            this.cboClassGrade.MethodInfo = controlMethodInfo1;
            this.cboClassGrade.Name = "cboClassGrade";
            this.cboClassGrade.Parameter = "C_DV_NAME";
            this.cboClassGrade.PrefixBackColor = System.Drawing.Color.White;
            this.cboClassGrade.QueryCond = "";
            this.cboClassGrade.QueryType = "CacheType";
            this.cboClassGrade.RequestEveryTime = true;
            this.cboClassGrade.Size = new System.Drawing.Size(119, 21);
            this.cboClassGrade.TabIndex = 7;
            this.cboClassGrade.Tag = this.cell12;
            this.cboClassGrade.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboClassGrade.YssCaption = "级别类型";
            this.cboClassGrade.SelectedValueChanged += new System.EventHandler(this.cboClassGrade_SelectedValueChanged);
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
            this.cell14.Text = "分级级别：";
            // 
            // cboQuota
            // 
            this.cboQuota.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboQuota.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboQuota.DisplayName = "C_KEY_NAME";
            this.cboQuota.DisplayValue = "C_KEY_CODE";
            this.cboQuota.FilterCond = "";
            this.cboQuota.IsFillDecimal = false;
            this.cboQuota.Location = new System.Drawing.Point(110, 118);
            this.cboQuota.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo6.MethodName = "getDataList";
            controlMethodInfo6.MethodParams = null;
            controlMethodInfo6.MethodParamValues = null;
            controlMethodInfo6.Methods = null;
            this.cboQuota.MethodInfo = controlMethodInfo6;
            this.cboQuota.Name = "cboQuota";
            this.cboQuota.NodeID = "C_KEY_CODE";
            this.cboQuota.Parameter = "C_KEY_NAME";
            this.cboQuota.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconOnly;
            this.cboQuota.PrefixBackColor = System.Drawing.Color.White;
            this.cboQuota.PrefixForeColor = System.Drawing.Color.Coral;
            this.cboQuota.QueryCond = "";
            this.cboQuota.QueryType = "CacheType";
            this.cboQuota.Size = new System.Drawing.Size(119, 21);
            this.cboQuota.TabIndex = 4;
            this.cboQuota.Visible = false;
            this.cboQuota.YssAssociaType = YssElecReco.Context.AssociaType.totalIndex;
            this.cboQuota.YssCaption = "指标项名称";
            // 
            // cboOrg
            // 
            this.cboOrg.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboOrg.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboOrg.DisplayName = "C_ORG_NAME";
            this.cboOrg.DisplayValue = "C_ORG_CODE";
            this.cboOrg.FilterCond = "";
            this.cboOrg.Location = new System.Drawing.Point(351, 118);
            this.cboOrg.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo4.MethodName = "getDataList";
            controlMethodInfo4.MethodParams = null;
            controlMethodInfo4.MethodParamValues = null;
            controlMethodInfo4.Methods = null;
            this.cboOrg.MethodInfo = controlMethodInfo4;
            this.cboOrg.Name = "cboOrg";
            this.cboOrg.NodeID = "C_ORG_CODE";
            this.cboOrg.Parameter = "C_ORG_CODE~C_ORG_NAME";
            this.cboOrg.ParaNodeID = "C_ORG_CODE_P";
            this.cboOrg.PrefixBackColor = System.Drawing.Color.White;
            this.cboOrg.QueryCond = "";
            this.cboOrg.QueryType = "";
            this.cboOrg.Size = new System.Drawing.Size(119, 21);
            this.cboOrg.TabIndex = 5;
            this.cboOrg.Tag = this.cell20;
            this.cboOrg.YssAssociaType = YssInformation.Support.Context.AssociaType.NULL;
            this.cboOrg.YssCaption = "托管行";
            this.cboOrg.YssOnBeforeLoadData += new FAST.Core.BaseControl.YssSelCombox.yssBeforeLoadData(this.cboOrg_YssOnBeforeLoadData);
            // 
            // cell20
            // 
            this.cell20.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell20.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell20.InnerControl = this.cboOrg;
            // 
            // row6
            // 
            this.row6.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell16,
            this.cell17,
            this.cell18,
            this.cell19,
            this.cell20});
            this.row6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row6.FullRowSelected = false;
            this.row6.Text = null;
            // 
            // cell16
            // 
            this.cell16.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell16.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell16.InnerControl = null;
            this.cell16.Text = "   分级组合：";
            // 
            // cell17
            // 
            this.cell17.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell17.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell17.InnerControl = this.cboClsPort;
            // 
            // cboClassLevel
            // 
            this.cboClassLevel.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboClassLevel.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboClassLevel.DisplayName = "C_DV_NAME";
            this.cboClassLevel.DisplayValue = "C_DV_CODE";
            this.cboClassLevel.FilterCond = "";
            this.cboClassLevel.IsFillDecimal = false;
            this.cboClassLevel.Location = new System.Drawing.Point(351, 93);
            this.cboClassLevel.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo7.MethodName = "getDataListByTypes";
            controlMethodInfo7.MethodParams = null;
            controlMethodInfo7.MethodParamValues = null;
            //new string[] {"PORT_CLS_CREAT_TYPE,"};
            controlMethodInfo7.Methods = new string[] {
        "getPortClsCode",
        "getDataListByTypes",
        "getQueryResByTypes",
        "getDataListByKeys",
        "getQueryResByKeys",
        "getDataByCode",
        "getDataList",
        "getDataListRes",
        "getKeyConvertMap"};
            this.cboClassLevel.MethodInfo = controlMethodInfo7;
            this.cboClassLevel.Name = "cboClassLevel";
            this.cboClassLevel.Parameter = "C_DV_NAME";
            this.cboClassLevel.PrefixBackColor = System.Drawing.Color.White;
            this.cboClassLevel.QueryCond = "";
            this.cboClassLevel.QueryType = "CacheType";
            this.cboClassLevel.RequestEveryTime = true;
            this.cboClassLevel.Size = new System.Drawing.Size(119, 21);
            this.cboClassLevel.TabIndex = 8;
            this.cboClassLevel.Tag = this.cell15;
            this.cboClassLevel.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboClassLevel.YssCaption = "分级级别";
            this.cboClassLevel.BeforeDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(this.cboClassLevel_BeforeDropDownClick);
            this.cboClassLevel.SelectedValueChanged += new System.EventHandler(this.cboClassLevel_SelectedValueChanged);
            // 
            // cell18
            // 
            this.cell18.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell18.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell18.InnerControl = null;
            // 
            // cell19
            // 
            this.cell19.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell19.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell19.InnerControl = null;
            this.cell19.Text = "托管行：";
            // 
            // row7
            // 
            this.row7.Font = new System.Drawing.Font("新宋体", 10F, System.Drawing.FontStyle.Bold);
            this.row7.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row7.FullRowSelected = false;
            this.row7.GroupLineLength = 310;
            this.row7.Height = 33;
            this.row7.IsGroup = true;
            this.row7.Text = "关联设置";
            // 
            // row8
            // 
            this.row8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row8.FullRowSelected = false;
            this.row8.GroupLineLength = 310;
            this.row8.Height = 10;
            this.row8.Text = null;
            // 
            // row9
            // 
            this.row9.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cwbbLabel,
            this.cwbbCell,
            this.cell21,
            this.zdhLabel,
            this.zdhCell});
            this.row9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row9.FullRowSelected = false;
            this.row9.Text = null;
            // 
            // cwbbLabel
            // 
            this.cwbbLabel.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cwbbLabel.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cwbbLabel.InnerControl = null;
            this.cwbbLabel.Text = "   财务报表：";
            // 
            // cwbbCell
            // 
            this.cwbbCell.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cwbbCell.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cwbbCell.InnerControl = this.selCwbb;
            // 
            // selCwbb
            // 
            this.selCwbb.AddedSelItemName = "";
            // 
            // 
            // 
            this.selCwbb.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            ////this.selCwbb.DetailDataProperty = "Tag";
            ////this.selCwbb.DetailMark = "TEMPLATE";
            this.selCwbb.SelectMode = Yss.KRichEx.AutoFilter.SelectMode.Detail;
            this.selCwbb.DisplayName = "C_REPORT_NAME";
            this.selCwbb.DisplayStyle = Yss.KRichEx.AutoFilter.DisplayStyle.Modal;
            this.selCwbb.DisplayValue = "C_REPORT_CODE";
            this.selCwbb.FilterCond = "";
            this.selCwbb.KTableTree = true;
            this.selCwbb.Location = new System.Drawing.Point(110, 184);
            this.selCwbb.Margin = new System.Windows.Forms.Padding(0);
            this.selCwbb.MethodInfo = null;
            this.selCwbb.Name = "selCwbb";
            this.selCwbb.NodeID = "C_REPORT_CODE";
            this.selCwbb.Parameter = "C_REPORT_NAME~C_REPORT_CODE";
            this.selCwbb.ParaNodeID = "C_GROUP_CODE";
            this.selCwbb.PrefixBackColor = System.Drawing.Color.White;
            this.selCwbb.QueryCond = "";
            this.selCwbb.QueryType = "";
            this.selCwbb.ShowCheckBox = true;
            this.selCwbb.Size = new System.Drawing.Size(119, 21);
            this.selCwbb.SortColumn = "C_REPORT_NAME";
            this.selCwbb.TabIndex = 4;
            this.selCwbb.Tag = this.cwbbCell;
            this.selCwbb.YssAssociaType = FAST.Core.Context.AssociaFAST.NULL;
            this.selCwbb.YssCaption = "财务报表";
            this.selCwbb.YssOnBeforeLoadData += new FAST.Core.BaseControl.YssSelCombox.yssBeforeLoadData(this.selCwbb_YssOnBeforeLoadData);

            // 
            // cell21
            // 
            this.cell21.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell21.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell21.InnerControl = null;
            this.cell21.Name = "cell21";
            // 
            // zdhLabel
            // 
            this.zdhLabel.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.zdhLabel.ForeColor = System.Drawing.SystemColors.ControlText;
            this.zdhLabel.InnerControl = null;
            this.zdhLabel.Name = "zdhLabel";
            this.zdhLabel.Text = "指定行：";
            // 
            // zdhCell
            // 
            this.zdhCell.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.zdhCell.ForeColor = System.Drawing.SystemColors.ControlText;
            this.zdhCell.InnerControl = this.txtZdh;
            this.zdhCell.Name = "zdhCell";
            // 
            // txtZdh
            // 
            this.txtZdh.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.txtZdh.Location = new System.Drawing.Point(351, 184);
            this.txtZdh.Name = "txtZdh";
            this.txtZdh.Size = new System.Drawing.Size(119, 21);
            this.txtZdh.TabIndex = 12;
            this.txtZdh.Tag = this.zdhCell;
            this.txtZdh.YssCaption = "指定行";
            this.txtZdh.YssIsMust = false;

            // 
            // Frm_ELEC_RELA_PUB_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(493, 275);
            this.DoubleBuffered = true;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "Frm_ELEC_RELA_PUB_S";
            this.Text = "电子对账指标关联设置";
            this.YssSetStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.Load += new System.EventHandler(this.Frm_ELEC_RELA_S_Load);
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
        private Yss.KTable.Models.Row row5;
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
        private Yss.KRichEx.YssTextBox txtZbCode;
        private Yss.KRichEx.YssTextBox txtZbName;
        private YssSelCombox cboDzType;
        private YssSelCombox cboQuota;
        private YssSelCombox cboClsPort;
        private YssSelCombox cboOrg;
        private Yss.KTable.Models.Cell cell20;
        private Yss.KTable.Models.Row row6;
        private Yss.KTable.Models.Cell cell19;
        private YssSelCombox cboClassType;
        private YssSelCombox cboClassGrade;
        private Yss.KTable.Models.Row row7;
        private Yss.KTable.Models.Row row8;
        private Yss.KTable.Models.Row row9;
        private Yss.KTable.Models.Cell cwbbLabel;
        private Yss.KTable.Models.Cell cwbbCell;
        private FAST.Core.BaseControl.YssSelCombox selCwbb;
        private Yss.KTable.Models.Cell cell16;
        private Yss.KTable.Models.Cell cell17;
        private Yss.KTable.Models.Cell cell18;
        private FAST.Core.BaseControl.YssSelCombox cboClassLevel;
        private Yss.KTable.Models.Cell cell21;
        private Yss.KTable.Models.Cell zdhLabel;
        private Yss.KTable.Models.Cell zdhCell;
        private Yss.KRichEx.YssTextBox txtZdh;
    }
}