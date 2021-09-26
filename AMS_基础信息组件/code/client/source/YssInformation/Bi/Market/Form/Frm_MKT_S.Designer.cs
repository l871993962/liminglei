using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;

using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;
using Yss.KRichEx;
using FAST.Core.Context;


namespace YssInformation.Bi.Market.Form
{
    partial class Frm_MKT_S
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_MKT_S));
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo1 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo4 = new FAST.Core.BaseControl.ControlMethodInfo();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.row3 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.txtExchangeCode = new Yss.KRichEx.YssTextBox();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.txtExchangeName = new Yss.KRichEx.YssTextBox();
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.row4 = new Yss.KTable.Models.Row();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.textMktdesc = new Yss.KRichEx.YssTextBox();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.textenglishName = new Yss.KRichEx.YssTextBox();
            this.cboExchange = new FAST.Core.BaseControl.YssSelCombox();
            this.cell22 = new Yss.KTable.Models.Cell();
            this.cboHolidays = new FAST.Core.BaseControl.YssSelCombox();
            this.cell27 = new Yss.KTable.Models.Cell();
            this.row5 = new Yss.KTable.Models.Row();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.textSWIFTCODE = new Yss.KRichEx.YssTextBox();
            this.cell13 = new Yss.KTable.Models.Cell();
            this.cell14 = new Yss.KTable.Models.Cell();
            this.cell15 = new Yss.KTable.Models.Cell();
            this.fixCodeTxt = new Yss.KRichEx.YssTextBox();
            this.cboArea = new FAST.Core.BaseControl.YssSelCombox();
            this.cell19 = new Yss.KTable.Models.Cell();
            this.cell16 = new Yss.KTable.Models.Cell();
            this.cell17 = new Yss.KTable.Models.Cell();
            this.row6 = new Yss.KTable.Models.Row();
            this.cell18 = new Yss.KTable.Models.Cell();
            this.cell20 = new Yss.KTable.Models.Cell();
            this.cell21 = new Yss.KTable.Models.Cell();
            this.iniSettleDays = new Yss.KRichEx.IntegerInputEx();
            this.cell24 = new Yss.KTable.Models.Cell();
            this.row7 = new Yss.KTable.Models.Row();
            this.cell23 = new Yss.KTable.Models.Cell();
            this.cell25 = new Yss.KTable.Models.Cell();
            this.cell26 = new Yss.KTable.Models.Cell();
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
            this.tbMain.Controls.Add(this.fixCodeTxt);
            this.tbMain.Controls.Add(this.textSWIFTCODE);
            this.tbMain.Controls.Add(this.textMktdesc);
            this.tbMain.Controls.Add(this.textenglishName);
            this.tbMain.Controls.Add(this.iniSettleDays);
            this.tbMain.Controls.Add(this.txtExchangeName);
            this.tbMain.Controls.Add(this.cboExchange);
            this.tbMain.Controls.Add(this.cboArea);
            this.tbMain.Controls.Add(this.txtExchangeCode);
            this.tbMain.Controls.Add(this.cboHolidays);
            this.tbMain.GridLineColor = System.Drawing.Color.Black;
            // 
            // 
            // 
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3,
            this.row4,
            this.row5,
            this.row6,
            this.row7});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(493, 188);
            this.tbMain.Text = "交易所设置";
            // 
            // stBarBottom
            // 
            this.stBarBottom.Location = new System.Drawing.Point(0, 218);
            // 
            // panelExLine
            // 
            // 
            // pnlMain
            // 
            this.pnlMain.Size = new System.Drawing.Size(493, 241);
            //this.pnlMain.Style.Alignment = System.Drawing.StringAlignment.Center;
            //this.pnlMain.Style.BackColor1.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.DockSiteBackColor;
            //this.pnlMain.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            //this.pnlMain.Style.BorderColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBorder;
            //this.pnlMain.Style.ForeColor.ColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelText;
            //this.pnlMain.Style.GradientAngle = 90;
            // 
            // yssPanel1
            // 
            this.yssPanel1.Size = new System.Drawing.Size(493, 241);
            // 
            // hpAssist
            // 
            // 
            // row1
            // 
            this.row1.BackColor = System.Drawing.Color.Empty;
            this.row1.Font = new System.Drawing.Font("新宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row1.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row1.FullRowSelected = false;
            this.row1.GroupLineLength = 310;
            this.row1.GroupPosition = 13;
            this.row1.Height = 33;
            this.row1.IsGroup = true;
            this.row1.Key = null;
            this.row1.OwnTable = this.tbMain;
            this.row1.RowName = "row1";
            this.row1.ShowCheckBox = true;
            this.row1.Text = "基本信息";
            // 
            // row2
            // 
            this.row2.BackColor = System.Drawing.Color.Empty;
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row2.ForeColor = System.Drawing.Color.Black;
            this.row2.FullRowSelected = false;
            this.row2.GroupLineLength = 310;
            this.row2.GroupPosition = 13;
            this.row2.Height = 10;
            this.row2.Key = null;
            this.row2.OwnTable = this.tbMain;
            this.row2.RowName = "row2";
            this.row2.ShowCheckBox = true;
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
            this.row3.FullRowSelected = false;
            this.row3.GroupLineLength = 310;
            this.row3.GroupPosition = 13;
            this.row3.Height = 25;
            this.row3.Key = null;
            this.row3.OwnTable = this.tbMain;
            this.row3.RowName = "row3";
            this.row3.ShowCheckBox = true;
            // 
            // cell1
            // 
            this.cell1.BackColor = System.Drawing.Color.Empty;
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.Key = null;
            this.cell1.Text = "   市场代码：";
            this.cell1.ToolTip = null;
            // 
            // cell2
            // 
            this.cell2.BackColor = System.Drawing.Color.Empty;
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.txtExchangeCode;
            this.cell2.Key = null;
            this.cell2.ToolTip = null;
            // 
            // txtExchangeCode
            // 
            this.txtExchangeCode.Border.Class = "TextBoxBorder";
            this.txtExchangeCode.KeepDesignValue = true;
            this.txtExchangeCode.Location = new System.Drawing.Point(110, 43);
            this.txtExchangeCode.Name = "txtExchangeCode";
            this.txtExchangeCode.Size = new System.Drawing.Size(120, 21);
            this.txtExchangeCode.TabIndex = 50;
            this.txtExchangeCode.Tag = this.cell2;
            this.txtExchangeCode.Value = "";
            this.txtExchangeCode.YssCaption = "市场代码";
            this.txtExchangeCode.YssIsMust = true;
            this.txtExchangeCode.YssKiloDelimiter = true;
            this.txtExchangeCode.YssNumeric = "";
            // 
            // cell3
            // 
            this.cell3.BackColor = System.Drawing.Color.Empty;
            this.cell3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell3.Key = null;
            this.cell3.ToolTip = null;
            // 
            // cell4
            // 
            this.cell4.BackColor = System.Drawing.Color.Empty;
            this.cell4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell4.Key = null;
            this.cell4.Text = "市场名称：";
            this.cell4.ToolTip = null;
            // 
            // cell5
            // 
            this.cell5.BackColor = System.Drawing.Color.Empty;
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.txtExchangeName;
            this.cell5.Key = null;
            this.cell5.ToolTip = null;
            // 
            // txtExchangeName
            // 
            this.txtExchangeName.Border.Class = "TextBoxBorder";
            this.txtExchangeName.KeepDesignValue = true;
            this.txtExchangeName.Location = new System.Drawing.Point(353, 43);
            this.txtExchangeName.Name = "txtExchangeName";
            this.txtExchangeName.Size = new System.Drawing.Size(120, 21);
            this.txtExchangeName.TabIndex = 51;
            this.txtExchangeName.Tag = this.cell5;
            this.txtExchangeName.Value = "";
            this.txtExchangeName.YssCaption = "市场名称";
            this.txtExchangeName.YssIsMust = true;
            this.txtExchangeName.YssKiloDelimiter = true;
            this.txtExchangeName.YssLength = 50;
            this.txtExchangeName.YssNumeric = "";
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
            // row4
            // 
            this.row4.BackColor = System.Drawing.Color.Empty;
            this.row4.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell6,
            this.cell7,
            this.cell8,
            this.cell9,
            this.cell10});
            this.row4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row4.ForeColor = System.Drawing.Color.Black;
            this.row4.FullRowSelected = false;
            this.row4.GroupLineLength = 310;
            this.row4.GroupPosition = 13;
            this.row4.Height = 25;
            this.row4.Key = null;
            this.row4.OwnTable = this.tbMain;
            this.row4.RowName = "row4";
            this.row4.ShowCheckBox = true;
            // 
            // cell6
            // 
            this.cell6.BackColor = System.Drawing.Color.Empty;
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.Key = null;
            this.cell6.Text = "   市场简称：";
            this.cell6.ToolTip = null;
            // 
            // cell7
            // 
            this.cell7.BackColor = System.Drawing.Color.Empty;
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.InnerControl = this.textMktdesc;
            this.cell7.Key = null;
            this.cell7.ToolTip = null;
            // 
            // textMktdesc
            // 
            this.textMktdesc.Border.Class = "TextBoxBorder";
            this.textMktdesc.Location = new System.Drawing.Point(110, 68);
            this.textMktdesc.Name = "textMktdesc";
            this.textMktdesc.Size = new System.Drawing.Size(120, 21);
            this.textMktdesc.TabIndex = 52;
            this.textMktdesc.Tag = this.cell7;
            this.textMktdesc.Value = "";
            this.textMktdesc.YssCaption = "市场简称";
            this.textMktdesc.YssIsMust = true;
            this.textMktdesc.YssNumeric = "";
            // 
            // cell8
            // 
            this.cell8.BackColor = System.Drawing.Color.Empty;
            this.cell8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell8.Key = null;
            this.cell8.ToolTip = null;
            // 
            // cell9
            // 
            this.cell9.BackColor = System.Drawing.Color.Empty;
            this.cell9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell9.Key = null;
            this.cell9.Text = "英文名称：";
            this.cell9.ToolTip = null;
            // 
            // cell10
            // 
            this.cell10.BackColor = System.Drawing.Color.Empty;
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.InnerControl = this.textenglishName;
            this.cell10.Key = null;
            this.cell10.ToolTip = null;
            // 
            // textenglishName
            // 
            // 
            // 
            // 
            this.textenglishName.Border.Class = "TextBoxBorder";
            this.textenglishName.Location = new System.Drawing.Point(353, 68);
            this.textenglishName.Name = "textenglishName";
            this.textenglishName.Size = new System.Drawing.Size(120, 21);
            this.textenglishName.TabIndex = 53;
            this.textenglishName.Tag = this.cell10;
            this.textenglishName.Value = "";
            this.textenglishName.YssCaption = "";
            this.textenglishName.YssNumeric = "";
            // 
            // cboExchange
            // 
            this.cboExchange.AddedSelItemName = "";
            this.cboExchange.BackColor = System.Drawing.Color.White;
            this.cboExchange.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.cboExchange.ClassName = "";
            this.cboExchange.DisplayName = "C_MKTVOC_NAME";
            this.cboExchange.DisplayValue = "C_MKTVOC_CODE";
            this.cboExchange.DllName = "YssControls.dll";
            this.cboExchange.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDown;
            this.cboExchange.FilterCond = "";
            this.cboExchange.IsFillDecimal = false;
            this.cboExchange.IsFocused = false;
            this.cboExchange.KTableTree = true;
            this.cboExchange.Location = new System.Drawing.Point(353, 118);
            this.cboExchange.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo3.MethodName = "getDataList";
            controlMethodInfo3.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo3.MethodParams")));
            controlMethodInfo3.MethodParamValues = null;
            controlMethodInfo3.Methods = null;
            this.cboExchange.MethodInfo = controlMethodInfo3;
            this.cboExchange.Name = "cboExchange";
            this.cboExchange.NodeID = "C_MKTVOC_CODE";
            this.cboExchange.Padding = new System.Windows.Forms.Padding(1, 3, 1, 3);
            this.cboExchange.Parameter = "C_MKTVOC_CODE~C_MKTVOC_NAME";
            this.cboExchange.ParaNodeID = "C_DV_MKT_TYPE";
            this.cboExchange.PasswordChar = '\0';
            this.cboExchange.PopupTitle = "数据筛选";
            // 
            // 
            // 
            this.cboExchange.QueryCond = "";
            this.cboExchange.QueryType = "";
            this.cboExchange.RequestEveryTime = false;
            this.cboExchange.ShowCheckBox = false;
            this.cboExchange.ShowColumnHeader = false;
            this.cboExchange.Size = new System.Drawing.Size(121, 21);
            // 
            // 
            // 
            this.cboExchange.TabIndex = 57;
            this.cboExchange.Tag = this.cell22;
            this.cboExchange.TextAlign = System.Windows.Forms.HorizontalAlignment.Left;
            this.cboExchange.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
            this.cboExchange.TipText = "";
            this.cboExchange.TriggerTextLength = 1;
            this.cboExchange.UseErrorTip = true;
            ////this.cboExchange.YssAssociaType = YssBaseCls.Context.AssociaType.exMarketVoc;
            this.cboExchange.YssAssociaType = YssInformation.Support.Context.AssociaType.base_exMarketVoc;
            this.cboExchange.YssCaption = "所属市场";
            this.cboExchange.YssIsMust = true;
            this.cboExchange.YssLength = 20;
            this.cboExchange.YssNumeric = "";
            this.cboExchange.YssReadOnly = false;
            this.cboExchange.YssShowButton = true;
            this.cboExchange.YssOnBeforeLoadData += new FAST.Core.BaseControl.YssSelCombox.yssBeforeLoadData(this.cboExchange_YssOnBeforeLoadData);
            // 
            // cell22
            // 
            this.cell22.BackColor = System.Drawing.Color.Empty;
            this.cell22.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell22.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell22.InnerControl = this.cboExchange;
            this.cell22.Key = null;
            this.cell22.ToolTip = null;
            // 
            // cboHolidays
            // 
            this.cboHolidays.AddedSelItemName = "";
            this.cboHolidays.BackColor = System.Drawing.Color.White;
            this.cboHolidays.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.cboHolidays.ClassName = "";
            this.cboHolidays.DisplayName = "C_HDAY_NAME";
            this.cboHolidays.DisplayValue = "C_HDAY_CODE";
            this.cboHolidays.DllName = "YssControls.dll";
            this.cboHolidays.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDown;
            this.cboHolidays.FilterCond = "";
            this.cboHolidays.IsFillDecimal = false;
            this.cboHolidays.IsFocused = false;
            this.cboHolidays.KTableTree = false;
            this.cboHolidays.Location = new System.Drawing.Point(353, 143);
            this.cboHolidays.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataList";
            controlMethodInfo1.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo1.MethodParams")));
            controlMethodInfo1.MethodParamValues = null;
            controlMethodInfo1.Methods = null;
            this.cboHolidays.MethodInfo = controlMethodInfo1;
            this.cboHolidays.Name = "cboHolidays";
            this.cboHolidays.NodeID = "";
            this.cboHolidays.Padding = new System.Windows.Forms.Padding(1, 3, 1, 3);
            this.cboHolidays.Parameter = "C_HDAY_CODE~C_HDAY_NAME";
            this.cboHolidays.ParaNodeID = "";
            this.cboHolidays.PasswordChar = '\0';
            this.cboHolidays.PopupTitle = "数据筛选";
            // 
            // 
            // 
            this.cboHolidays.QueryCond = "";
            this.cboHolidays.QueryType = "";
            this.cboHolidays.RequestEveryTime = true;
            this.cboHolidays.ShowCheckBox = false;
            this.cboHolidays.ShowColumnHeader = false;
            this.cboHolidays.Size = new System.Drawing.Size(121, 21);
            // 
            // 
            // 
            this.cboHolidays.TabIndex = 59;
            this.cboHolidays.Tag = this.cell27;
            this.cboHolidays.TextAlign = System.Windows.Forms.HorizontalAlignment.Left;
            this.cboHolidays.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
            this.cboHolidays.TipText = "";
            this.cboHolidays.TriggerTextLength = 1;
            this.cboHolidays.UseErrorTip = true;
            this.cboHolidays.YssAssociaType = YssInformation.Support.Context.AssociaType.base_holidays_A;
            this.cboHolidays.YssCaption = "节假日群";
            this.cboHolidays.YssDataSrc = FAST.Core.Context.ClsEnums.DataSrc.SrcDB;
            this.cboHolidays.YssIsMust = true;
            this.cboHolidays.YssKiloDelimiter = true;
            this.cboHolidays.YssLength = 20;
            this.cboHolidays.YssNumeric = "";
            this.cboHolidays.YssReadOnly = false;
            this.cboHolidays.YssShowButton = true;
            // 
            // cell27
            // 
            this.cell27.BackColor = System.Drawing.Color.Empty;
            this.cell27.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell27.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell27.InnerControl = this.cboHolidays;
            this.cell27.Key = null;
            this.cell27.ToolTip = null;
            // 
            // row5
            // 
            this.row5.BackColor = System.Drawing.Color.Empty;
            this.row5.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell11,
            this.cell12,
            this.cell13,
            this.cell14,
            this.cell15});
            this.row5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row5.ForeColor = System.Drawing.Color.Black;
            this.row5.FullRowSelected = false;
            this.row5.GroupLineLength = 310;
            this.row5.GroupPosition = 13;
            this.row5.Height = 25;
            this.row5.Key = null;
            this.row5.OwnTable = this.tbMain;
            this.row5.RowName = "row5";
            this.row5.ShowCheckBox = true;
            this.row5.Text = "  所属地区：";
            // 
            // cell11
            // 
            this.cell11.BackColor = System.Drawing.Color.Empty;
            this.cell11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell11.Key = null;
            this.cell11.Text = "   SWIFT CODE：";
            this.cell11.ToolTip = null;
            // 
            // cell12
            // 
            this.cell12.BackColor = System.Drawing.Color.Empty;
            this.cell12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell12.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell12.InnerControl = this.textSWIFTCODE;
            this.cell12.Key = null;
            this.cell12.ToolTip = null;
            // 
            // textSWIFTCODE
            // 
            // 
            // 
            // 
            this.textSWIFTCODE.Border.Class = "TextBoxBorder";
            this.textSWIFTCODE.Font = new System.Drawing.Font("宋体", 9F);
            this.textSWIFTCODE.Location = new System.Drawing.Point(110, 93);
            this.textSWIFTCODE.Name = "textSWIFTCODE";
            this.textSWIFTCODE.Size = new System.Drawing.Size(120, 21);
            this.textSWIFTCODE.TabIndex = 54;
            this.textSWIFTCODE.Tag = this.cell12;
            this.textSWIFTCODE.Value = "";
            this.textSWIFTCODE.YssCaption = "";
            this.textSWIFTCODE.YssLength = 10;
            this.textSWIFTCODE.YssNumeric = "";
            // 
            // cell13
            // 
            this.cell13.BackColor = System.Drawing.Color.Empty;
            this.cell13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell13.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell13.Key = null;
            this.cell13.ToolTip = null;
            // 
            // cell14
            // 
            this.cell14.BackColor = System.Drawing.Color.Empty;
            this.cell14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell14.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell14.Key = null;
            this.cell14.Text = "FIX CODE：";
            this.cell14.ToolTip = null;
            // 
            // cell15
            // 
            this.cell15.BackColor = System.Drawing.Color.Empty;
            this.cell15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell15.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell15.InnerControl = this.fixCodeTxt;
            this.cell15.Key = null;
            this.cell15.ToolTip = null;
            // 
            // fixCodeTxt
            // 
            // 
            // 
            // 
            this.fixCodeTxt.Border.Class = "TextBoxBorder";
            this.fixCodeTxt.Location = new System.Drawing.Point(353, 93);
            this.fixCodeTxt.Name = "fixCodeTxt";
            this.fixCodeTxt.Size = new System.Drawing.Size(121, 21);
            this.fixCodeTxt.TabIndex = 55;
            this.fixCodeTxt.Tag = this.cell15;
            this.fixCodeTxt.Value = "";
            this.fixCodeTxt.YssCaption = "";
            this.fixCodeTxt.YssNumeric = "";
            // 
            // cboArea
            // 
            this.cboArea.AddedSelItemName = "";
            this.cboArea.BackColor = System.Drawing.Color.White;
            this.cboArea.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.cboArea.ClassName = "";
            this.cboArea.DisplayName = "C_AREA_NAME";
            this.cboArea.DisplayValue = "C_AREA_CODE";
            this.cboArea.DllName = "YssControls.dll";
            this.cboArea.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDown;
            this.cboArea.FilterCond = "";
            this.cboArea.IsFillDecimal = false;
            this.cboArea.IsFocused = false;
            this.cboArea.KeepDesignValue = true;
            this.cboArea.KTableTree = true;
            this.cboArea.Location = new System.Drawing.Point(110, 118);
            this.cboArea.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo4.MethodName = "getAllAreas";
            controlMethodInfo4.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo4.MethodParams")));
            controlMethodInfo4.MethodParamValues = null;
            controlMethodInfo4.Methods = null;
            this.cboArea.MethodInfo = controlMethodInfo4;
            this.cboArea.Name = "cboArea";
            this.cboArea.NodeID = "C_AREA_CODE";
            this.cboArea.Padding = new System.Windows.Forms.Padding(1, 3, 1, 3);
            this.cboArea.Parameter = "C_AREA_CODE~C_AREA_NAME";
            this.cboArea.ParaNodeID = "C_AREA_CODE_P";
            this.cboArea.PasswordChar = '\0';
            this.cboArea.PopupTitle = "数据筛选";
            // 
            // 
            // 
            this.cboArea.QueryCond = "";
            this.cboArea.QueryType = "";
            this.cboArea.RequestEveryTime = false;
            this.cboArea.ShowCheckBox = false;
            this.cboArea.ShowColumnHeader = false;
            this.cboArea.Size = new System.Drawing.Size(121, 21);
            // 
            // 
            // 
            this.cboArea.TabIndex = 56;
            this.cboArea.Tag = this.cell19;
            this.cboArea.TextAlign = System.Windows.Forms.HorizontalAlignment.Left;
            this.cboArea.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
            this.cboArea.TipText = "";
            this.cboArea.TriggerTextLength = 1;
            this.cboArea.UseErrorTip = true;
            this.cboArea.YssAssociaType = YssInformation.Support.Context.AssociaType.base_area;
            this.cboArea.YssCaption = "所属地区";
            this.cboArea.YssDataSrc = FAST.Core.Context.ClsEnums.DataSrc.SrcDB;
            this.cboArea.YssIsMust = true;
            this.cboArea.YssKiloDelimiter = true;
            this.cboArea.YssLength = 20;
            this.cboArea.YssNumeric = "";
            this.cboArea.YssReadOnly = false;
            this.cboArea.YssShowButton = true;
            // 
            // cell19
            // 
            this.cell19.BackColor = System.Drawing.Color.Empty;
            this.cell19.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell19.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell19.InnerControl = this.cboArea;
            this.cell19.Key = null;
            this.cell19.ToolTip = null;
            // 
            // cell16
            // 
            this.cell16.BackColor = System.Drawing.Color.Empty;
            this.cell16.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell16.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell16.Key = null;
            this.cell16.Text = "  描述：";
            this.cell16.ToolTip = null;
            // 
            // cell17
            // 
            this.cell17.BackColor = System.Drawing.Color.Empty;
            this.cell17.ColSpan = 4;
            this.cell17.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell17.Key = null;
            this.cell17.ToolTip = null;
            // 
            // row6
            // 
            this.row6.BackColor = System.Drawing.Color.Empty;
            this.row6.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell18,
            this.cell19,
            this.cell20,
            this.cell21,
            this.cell22});
            this.row6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row6.FullRowSelected = false;
            this.row6.GroupLineLength = 310;
            this.row6.GroupPosition = 13;
            this.row6.Height = 25;
            this.row6.Key = null;
            this.row6.OwnTable = this.tbMain;
            this.row6.RowName = "row6";
            this.row6.ShowCheckBox = true;
            // 
            // cell18
            // 
            this.cell18.BackColor = System.Drawing.Color.Empty;
            this.cell18.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell18.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell18.Key = null;
            this.cell18.Text = "   所属地区：";
            this.cell18.ToolTip = null;
            // 
            // cell20
            // 
            this.cell20.BackColor = System.Drawing.Color.Empty;
            this.cell20.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell20.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell20.Key = null;
            this.cell20.ToolTip = null;
            // 
            // cell21
            // 
            this.cell21.BackColor = System.Drawing.Color.Empty;
            this.cell21.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell21.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell21.Key = null;
            this.cell21.Text = "所属市场：";
            this.cell21.ToolTip = null;
            // 
            // iniSettleDays
            // 
            this.iniSettleDays.BackColor = System.Drawing.Color.White;
            this.iniSettleDays.Location = new System.Drawing.Point(110, 143);
            this.iniSettleDays.MaxValue = 100;
            this.iniSettleDays.Name = "iniSettleDays";
            this.iniSettleDays.Prefix = "交易日期+";
            this.iniSettleDays.Size = new System.Drawing.Size(121, 21);
            this.iniSettleDays.TabIndex = 58;
            this.iniSettleDays.Tag = this.cell24;
            this.iniSettleDays.Value = 0;
            // 
            // cell24
            // 
            this.cell24.BackColor = System.Drawing.Color.Empty;
            this.cell24.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell24.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell24.InnerControl = this.iniSettleDays;
            this.cell24.Key = null;
            this.cell24.ToolTip = null;
            // 
            // row7
            // 
            this.row7.BackColor = System.Drawing.Color.Empty;
            this.row7.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell23,
            this.cell24,
            this.cell25,
            this.cell26,
            this.cell27});
            this.row7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row7.FullRowSelected = false;
            this.row7.GroupLineLength = 100;
            this.row7.GroupPosition = 16;
            this.row7.Height = 23;
            this.row7.Key = null;
            this.row7.OwnTable = this.tbMain;
            this.row7.RowName = "row7";
            this.row7.ShowCheckBox = true;
            // 
            // cell23
            // 
            this.cell23.BackColor = System.Drawing.Color.Empty;
            this.cell23.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell23.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell23.Key = null;
            this.cell23.Text = "   结转天数：";
            this.cell23.ToolTip = null;
            // 
            // cell25
            // 
            this.cell25.BackColor = System.Drawing.Color.Empty;
            this.cell25.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell25.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell25.Key = null;
            this.cell25.ToolTip = null;
            // 
            // cell26
            // 
            this.cell26.BackColor = System.Drawing.Color.Empty;
            this.cell26.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell26.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell26.Key = null;
            this.cell26.Text = "节假日群：";
            this.cell26.ToolTip = null;
            // 
            // Frm_MKT_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(493, 241);
            this.DoubleBuffered = true;
            this.Name = "Frm_MKT_S";
            this.Text = "交易市场设置";
            this.YssSetStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.Load += new System.EventHandler(this.FrmExchange_Load);
            this.YssOnBeforeSaveClick += new FrmBaseSet.BeforeSaveClick(this.Frm_MKT_S_YssOnBeforeSaveClick);
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
        private Yss.KTable.Models.Cell cell16;
        private Yss.KTable.Models.Cell cell17;
        private FAST.Core.BaseControl.YssSelCombox cboArea;
        private YssTextBox txtExchangeName;
        private YssTextBox txtExchangeCode;
        private FAST.Core.BaseControl.YssSelCombox cboHolidays;
        private FAST.Core.BaseControl.YssSelCombox cboExchange;
        private Yss.KTable.Models.Row row6;
        private Yss.KTable.Models.Cell cell18;
        private Yss.KTable.Models.Cell cell19;
        private Yss.KTable.Models.Cell cell20;
        private Yss.KTable.Models.Cell cell21;
        private Yss.KTable.Models.Cell cell22;
        private Yss.KRichEx.IntegerInputEx iniSettleDays;
        private YssTextBox textenglishName;
        private YssTextBox textMktdesc;
        private YssTextBox textSWIFTCODE;
        private Yss.KTable.Models.Row row7;
        private Yss.KTable.Models.Cell cell23;
        private Yss.KTable.Models.Cell cell24;
        private Yss.KTable.Models.Cell cell25;
        private Yss.KTable.Models.Cell cell26;
        private Yss.KTable.Models.Cell cell27;
        private YssTextBox fixCodeTxt;



    }
}