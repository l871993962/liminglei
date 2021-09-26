using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;

using FAST.Core.Resource;
namespace YssSecInformation.Mp.Indexmp.Form
{
    partial class Frm_SEC_MKT_INDEX_S
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
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo2 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo1 = new FAST.Core.BaseControl.ControlMethodInfo();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_SEC_MKT_INDEX_S));
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.row1 = new Yss.KTable.Models.Row();
            this.row2blank = new Yss.KTable.Models.Row();
            this.row3 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.selSecurity = new FAST.Core.BaseControl.YssSelCombox();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.selDvPlat = new FAST.Core.BaseControl.YssSelCombox();
            this.row4 = new Yss.KTable.Models.Row();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.dtpMKTPriceDate = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.dtpMKTPriceTime = new YssDevComponents.Editors.DateTimeAdv.DateTimeInput();
            this.row2 = new Yss.KTable.Models.Row();
            this.row5blank = new Yss.KTable.Models.Row();
            this.row5 = new Yss.KTable.Models.Row();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.txtClosePrice = new Yss.KRichEx.YssTextBox();
            this.cell13 = new Yss.KTable.Models.Cell();
            this.cell14 = new Yss.KTable.Models.Cell();
            this.cell15 = new Yss.KTable.Models.Cell();
            this.txtAveragePrice = new Yss.KRichEx.YssTextBox();
            this.row6 = new Yss.KTable.Models.Row();
            this.cell16 = new Yss.KTable.Models.Cell();
            this.cell17 = new Yss.KTable.Models.Cell();
            this.txtBuyPrice = new Yss.KRichEx.YssTextBox();
            this.cell18 = new Yss.KTable.Models.Cell();
            this.cell19 = new Yss.KTable.Models.Cell();
            this.cell20 = new Yss.KTable.Models.Cell();
            this.txtSellPrice = new Yss.KRichEx.YssTextBox();
            this.row7 = new Yss.KTable.Models.Row();
            this.cell21 = new Yss.KTable.Models.Cell();
            this.cell22 = new Yss.KTable.Models.Cell();
            this.txtOpenPrice = new Yss.KRichEx.YssTextBox();
            this.cell23 = new Yss.KTable.Models.Cell();
            this.cell24 = new Yss.KTable.Models.Cell();
            this.cell25 = new Yss.KTable.Models.Cell();
            this.txtNewPrice = new Yss.KRichEx.YssTextBox();
            this.row8 = new Yss.KTable.Models.Row();
            this.cell26 = new Yss.KTable.Models.Cell();
            this.cell27 = new Yss.KTable.Models.Cell();
            this.txtHighPrice = new Yss.KRichEx.YssTextBox();
            this.cell28 = new Yss.KTable.Models.Cell();
            this.cell29 = new Yss.KTable.Models.Cell();
            this.cell30 = new Yss.KTable.Models.Cell();
            this.txtLowPrice = new Yss.KRichEx.YssTextBox();
            this.row9 = new Yss.KTable.Models.Row();
            this.cell31 = new Yss.KTable.Models.Cell();
            this.cell32 = new Yss.KTable.Models.Cell();
            this.cboHqzt = new FAST.Core.BaseControl.YssSelCombox();
            this.tbMain.SuspendLayout();
            this.pnlMain.SuspendLayout();
            this.yssPanel1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.dtpMKTPriceTime)).BeginInit();
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
            this.tbMain.Controls.Add(this.cboHqzt);
            this.tbMain.Controls.Add(this.selDvPlat);
            this.tbMain.Controls.Add(this.selSecurity);
            this.tbMain.Controls.Add(this.dtpMKTPriceDate);
            this.tbMain.Controls.Add(this.dtpMKTPriceTime);
            this.tbMain.Controls.Add(this.txtClosePrice);
            this.tbMain.Controls.Add(this.txtAveragePrice);
            this.tbMain.Controls.Add(this.txtBuyPrice);
            this.tbMain.Controls.Add(this.txtSellPrice);
            this.tbMain.Controls.Add(this.txtOpenPrice);
            this.tbMain.Controls.Add(this.txtNewPrice);
            this.tbMain.Controls.Add(this.txtHighPrice);
            this.tbMain.Controls.Add(this.txtLowPrice);
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2blank,
            this.row3,
            this.row4,
            this.row2,
            this.row5blank,
            this.row5,
            this.row6,
            this.row7,
            this.row8,
            this.row9});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(493, 262);
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
            this.stBarBottom.Location = new System.Drawing.Point(0, 292);
            // 
            // pnlMain
            // 
            this.pnlMain.Size = new System.Drawing.Size(493, 317);
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
            this.yssPanel1.Size = new System.Drawing.Size(493, 317);
            // 
            // column1
            // 
            this.column1.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.Width = 110;
            // 
            // column2
            // 
            this.column2.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column2.Width = 122;
            // 
            // column3
            // 
            this.column3.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column3.Width = 30;
            // 
            // column4
            // 
            this.column4.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column4.Width = 91;
            // 
            // column5
            // 
            this.column5.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
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
            // row2blank
            // 
            this.row2blank.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row2blank.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row2blank.FullRowSelected = false;
            this.row2blank.GroupPosition = -16;
            this.row2blank.Height = 10;
            this.row2blank.Text = null;
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
            this.row3.GroupPosition = -16;
            this.row3.Height = 25;
            this.row3.Text = null;
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
            this.cell2.InnerControl = this.selSecurity;
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
            this.selSecurity.DisplayName = "C_INDEX_NAME";
            this.selSecurity.DisplayStyle = Yss.KRichEx.AutoFilter.DisplayStyle.Modal;
            this.selSecurity.DisplayValue = "C_SEC_CODE";
            this.selSecurity.FilterCond = "";
            this.selSecurity.Location = new System.Drawing.Point(110, 43);
            this.selSecurity.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo3.MethodName = "getDataList";
            controlMethodInfo3.MethodParams = null;
            controlMethodInfo3.MethodParamValues = null;
            controlMethodInfo3.Methods = null;
            this.selSecurity.MethodInfo = controlMethodInfo3;
            this.selSecurity.Name = "selSecurity";
            this.selSecurity.Parameter = "C_SEC_CODE~C_INDEX_NAME";
            this.selSecurity.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconIndent;
            this.selSecurity.QueryCond = "";
            this.selSecurity.QueryType = "";
            this.selSecurity.Size = new System.Drawing.Size(121, 21);
            this.selSecurity.SortColumn = "C_SEC_CODE";
            this.selSecurity.TabIndex = 13;
            this.selSecurity.Tag = this.cell2;
            this.selSecurity.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Value_Name;
            this.selSecurity.YssAssociaType = YssSecInformation.Support.Context.AssociaType.sv_indexinfo;
            this.selSecurity.YssCaption = "交易证券";
            this.selSecurity.YssIsMust = true;
            this.selSecurity.YssOnAfterLoadData += new FAST.Core.BaseControl.YssSelCombox.yssAfterLoadData(this.selSecurity_YssOnAfterLoadData);
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
            this.cell4.Text = "行情来源：";
            // 
            // cell5
            // 
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.selDvPlat;
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
            this.selDvPlat.Location = new System.Drawing.Point(353, 43);
            this.selDvPlat.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo2.MethodName = "getDataListByTypes";
            controlMethodInfo2.MethodParams = null;
            controlMethodInfo2.MethodParamValues = new string[] {
        "PLAT_TYPE,"};
            controlMethodInfo2.Methods = null;
            this.selDvPlat.MethodInfo = controlMethodInfo2;
            this.selDvPlat.Name = "selDvPlat";
            this.selDvPlat.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconIndent;
            this.selDvPlat.QueryCond = "PLAT_TYPE";
            this.selDvPlat.QueryType = "CacheType";
            this.selDvPlat.Size = new System.Drawing.Size(121, 21);
            this.selDvPlat.TabIndex = 14;
            this.selDvPlat.Tag = this.cell5;
            this.selDvPlat.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.selDvPlat.YssCaption = "行情来源";
            this.selDvPlat.YssIsMust = true;
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
            this.row4.GroupPosition = -16;
            this.row4.Height = 25;
            this.row4.Text = null;
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.Text = "   行情日期：";
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.InnerControl = this.dtpMKTPriceDate;
            // 
            // dtpMKTPriceDate
            // 
            this.dtpMKTPriceDate.BackColor = System.Drawing.Color.Transparent;
            this.dtpMKTPriceDate.DateBeginChecked = true;
            this.dtpMKTPriceDate.DateEndChecked = true;
            this.dtpMKTPriceDate.Location = new System.Drawing.Point(110, 68);
            this.dtpMKTPriceDate.Margin = new System.Windows.Forms.Padding(0);
            this.dtpMKTPriceDate.Name = "dtpMKTPriceDate";
            this.dtpMKTPriceDate.Size = new System.Drawing.Size(121, 21);
            this.dtpMKTPriceDate.TabIndex = 15;
            this.dtpMKTPriceDate.Tag = this.cell7;
            this.dtpMKTPriceDate.yssEnabled = true;
            this.dtpMKTPriceDate.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpMKTPriceDate.yssInterval = false;
            this.dtpMKTPriceDate.yssLabelStr = "至";
            this.dtpMKTPriceDate.yssShowOperLable = false;
            this.dtpMKTPriceDate.YssShowSecond = true;
            this.dtpMKTPriceDate.yssTimeControl = false;
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
            this.cell9.Text = "行情时间：";
            // 
            // cell10
            // 
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.InnerControl = this.dtpMKTPriceTime;
            // 
            // dtpMKTPriceTime
            // 
            // 
            // 
            // 
            this.dtpMKTPriceTime.BackgroundStyle.Class = "DateTimeInputBackground";
            this.dtpMKTPriceTime.CustomFormat = "HH:mm:ss";
            this.dtpMKTPriceTime.Format = YssDevComponents.Editors.eDateTimePickerFormat.Custom;
            this.dtpMKTPriceTime.Location = new System.Drawing.Point(353, 68);
            // 
            // 
            // 
            this.dtpMKTPriceTime.MonthCalendar.AnnuallyMarkedDates = new System.DateTime[0];
            // 
            // 
            // 
            this.dtpMKTPriceTime.MonthCalendar.BackgroundStyle.BackColor = System.Drawing.Color.White;
            this.dtpMKTPriceTime.MonthCalendar.ClearButtonVisible = true;
            // 
            // 
            // 
            this.dtpMKTPriceTime.MonthCalendar.CommandsBackgroundStyle.BackColor2SchemePart = YssDevComponents.DotNetBar.eColorSchemePart.BarBackground2;
            this.dtpMKTPriceTime.MonthCalendar.CommandsBackgroundStyle.BackColorGradientAngle = 90;
            this.dtpMKTPriceTime.MonthCalendar.CommandsBackgroundStyle.BackColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.BarBackground;
            this.dtpMKTPriceTime.MonthCalendar.CommandsBackgroundStyle.BorderTop = YssDevComponents.DotNetBar.eStyleBorderType.Solid;
            this.dtpMKTPriceTime.MonthCalendar.CommandsBackgroundStyle.BorderTopColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.BarDockedBorder;
            this.dtpMKTPriceTime.MonthCalendar.CommandsBackgroundStyle.BorderTopWidth = 1;
            this.dtpMKTPriceTime.MonthCalendar.DaySize = new System.Drawing.Size(35, 20);
            this.dtpMKTPriceTime.MonthCalendar.DisplayMonth = new System.DateTime(2010, 12, 1, 0, 0, 0, 0);
            this.dtpMKTPriceTime.MonthCalendar.MarkedDates = new System.DateTime[0];
            this.dtpMKTPriceTime.MonthCalendar.MonthlyMarkedDates = new System.DateTime[0];
            // 
            // 
            // 
            this.dtpMKTPriceTime.MonthCalendar.NavigationBackgroundStyle.BackColor2SchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBackground2;
            this.dtpMKTPriceTime.MonthCalendar.NavigationBackgroundStyle.BackColorGradientAngle = 90;
            this.dtpMKTPriceTime.MonthCalendar.NavigationBackgroundStyle.BackColorSchemePart = YssDevComponents.DotNetBar.eColorSchemePart.PanelBackground;
            this.dtpMKTPriceTime.MonthCalendar.TodayButtonVisible = true;
            this.dtpMKTPriceTime.MonthCalendar.WeeklyMarkedDays = new System.DayOfWeek[0];
            this.dtpMKTPriceTime.Name = "dtpMKTPriceTime";
            this.dtpMKTPriceTime.Size = new System.Drawing.Size(121, 21);
            this.dtpMKTPriceTime.TabIndex = 16;
            this.dtpMKTPriceTime.Tag = this.cell10;
            this.dtpMKTPriceTime.Value = new System.DateTime(2011, 2, 12, 0, 0, 0, 0);
            // 
            // row2
            // 
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row2.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row2.FullRowSelected = false;
            this.row2.GroupLineLength = 310;
            this.row2.GroupPosition = 13;
            this.row2.Height = 33;
            this.row2.IsGroup = true;
            this.row2.Text = "行情信息";
            // 
            // row5blank
            // 
            this.row5blank.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row5blank.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row5blank.FullRowSelected = false;
            this.row5blank.GroupPosition = -16;
            this.row5blank.Height = 10;
            this.row5blank.Text = null;
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
            this.row5.GroupPosition = -16;
            this.row5.Height = 25;
            this.row5.Text = null;
            // 
            // cell11
            // 
            this.cell11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell11.Text = "   收 盘 价：";
            // 
            // cell12
            // 
            this.cell12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell12.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell12.InnerControl = this.txtClosePrice;
            // 
            // txtClosePrice
            // 
            this.txtClosePrice.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.txtClosePrice.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.txtClosePrice.ForeColor = System.Drawing.Color.Blue;
            this.txtClosePrice.ImeMode = System.Windows.Forms.ImeMode.On;
            this.txtClosePrice.Location = new System.Drawing.Point(110, 136);
            this.txtClosePrice.Name = "txtClosePrice";
            this.txtClosePrice.Size = new System.Drawing.Size(121, 21);
            this.txtClosePrice.TabIndex = 12;
            this.txtClosePrice.Tag = this.cell12;
            this.txtClosePrice.Text = "0";
            this.txtClosePrice.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtClosePrice.YssCaption = "收盘价";
            this.txtClosePrice.YssIsMust = true;
            this.txtClosePrice.YssKiloDelimiter = true;
            this.txtClosePrice.YssLength = 28;
            this.txtClosePrice.YssNumeric = "15, 13";
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
            this.cell14.Text = "平 均 价：";
            // 
            // cell15
            // 
            this.cell15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell15.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell15.InnerControl = this.txtAveragePrice;
            // 
            // txtAveragePrice
            // 
            this.txtAveragePrice.BackColor = System.Drawing.Color.White;
            this.txtAveragePrice.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.txtAveragePrice.ForeColor = System.Drawing.Color.Blue;
            this.txtAveragePrice.Location = new System.Drawing.Point(353, 136);
            this.txtAveragePrice.Name = "txtAveragePrice";
            this.txtAveragePrice.Size = new System.Drawing.Size(121, 21);
            this.txtAveragePrice.TabIndex = 13;
            this.txtAveragePrice.Tag = this.cell15;
            this.txtAveragePrice.Text = "0";
            this.txtAveragePrice.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtAveragePrice.YssCaption = "平均价";
            this.txtAveragePrice.YssKiloDelimiter = true;
            this.txtAveragePrice.YssLength = 28;
            this.txtAveragePrice.YssNumeric = "15, 13";
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
            this.row6.GroupPosition = -16;
            this.row6.Height = 25;
            this.row6.Text = null;
            // 
            // cell16
            // 
            this.cell16.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell16.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell16.Text = "   买 一 价：";
            // 
            // cell17
            // 
            this.cell17.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell17.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell17.InnerControl = this.txtBuyPrice;
            // 
            // txtBuyPrice
            // 
            this.txtBuyPrice.BackColor = System.Drawing.Color.White;
            this.txtBuyPrice.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.txtBuyPrice.ForeColor = System.Drawing.Color.Blue;
            this.txtBuyPrice.Location = new System.Drawing.Point(110, 161);
            this.txtBuyPrice.Name = "txtBuyPrice";
            this.txtBuyPrice.Size = new System.Drawing.Size(121, 21);
            this.txtBuyPrice.TabIndex = 14;
            this.txtBuyPrice.Tag = this.cell17;
            this.txtBuyPrice.Text = "0";
            this.txtBuyPrice.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtBuyPrice.YssCaption = "买一价";
            this.txtBuyPrice.YssKiloDelimiter = true;
            this.txtBuyPrice.YssLength = 28;
            this.txtBuyPrice.YssNumeric = "15, 13";
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
            this.cell19.Text = "卖 一 价：";
            // 
            // cell20
            // 
            this.cell20.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell20.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell20.InnerControl = this.txtSellPrice;
            // 
            // txtSellPrice
            // 
            this.txtSellPrice.BackColor = System.Drawing.Color.White;
            this.txtSellPrice.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.txtSellPrice.ForeColor = System.Drawing.Color.Blue;
            this.txtSellPrice.Location = new System.Drawing.Point(353, 161);
            this.txtSellPrice.Name = "txtSellPrice";
            this.txtSellPrice.Size = new System.Drawing.Size(121, 21);
            this.txtSellPrice.TabIndex = 15;
            this.txtSellPrice.Tag = this.cell20;
            this.txtSellPrice.Text = "0";
            this.txtSellPrice.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtSellPrice.YssCaption = "卖一价";
            this.txtSellPrice.YssKiloDelimiter = true;
            this.txtSellPrice.YssLength = 28;
            this.txtSellPrice.YssNumeric = "15, 13";
            // 
            // row7
            // 
            this.row7.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell21,
            this.cell22,
            this.cell23,
            this.cell24,
            this.cell25});
            this.row7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row7.FullRowSelected = false;
            this.row7.GroupPosition = -16;
            this.row7.Height = 25;
            this.row7.Text = null;
            // 
            // cell21
            // 
            this.cell21.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell21.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell21.Text = "   开 盘 价：";
            // 
            // cell22
            // 
            this.cell22.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell22.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell22.InnerControl = this.txtOpenPrice;
            // 
            // txtOpenPrice
            // 
            this.txtOpenPrice.BackColor = System.Drawing.Color.White;
            this.txtOpenPrice.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.txtOpenPrice.ForeColor = System.Drawing.Color.Blue;
            this.txtOpenPrice.Location = new System.Drawing.Point(110, 186);
            this.txtOpenPrice.Name = "txtOpenPrice";
            this.txtOpenPrice.Size = new System.Drawing.Size(121, 21);
            this.txtOpenPrice.TabIndex = 16;
            this.txtOpenPrice.Tag = this.cell22;
            this.txtOpenPrice.Text = "0";
            this.txtOpenPrice.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtOpenPrice.YssCaption = "开盘价";
            this.txtOpenPrice.YssKiloDelimiter = true;
            this.txtOpenPrice.YssLength = 28;
            this.txtOpenPrice.YssNumeric = "15, 13";
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
            this.cell24.Text = "最 新 价：";
            // 
            // cell25
            // 
            this.cell25.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell25.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell25.InnerControl = this.txtNewPrice;
            // 
            // txtNewPrice
            // 
            this.txtNewPrice.BackColor = System.Drawing.Color.White;
            this.txtNewPrice.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.txtNewPrice.ForeColor = System.Drawing.Color.Blue;
            this.txtNewPrice.Location = new System.Drawing.Point(353, 186);
            this.txtNewPrice.Name = "txtNewPrice";
            this.txtNewPrice.Size = new System.Drawing.Size(121, 21);
            this.txtNewPrice.TabIndex = 17;
            this.txtNewPrice.Tag = this.cell25;
            this.txtNewPrice.Text = "0";
            this.txtNewPrice.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtNewPrice.YssCaption = "最新价";
            this.txtNewPrice.YssKiloDelimiter = true;
            this.txtNewPrice.YssLength = 28;
            this.txtNewPrice.YssNumeric = "15, 13";
            // 
            // row8
            // 
            this.row8.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell26,
            this.cell27,
            this.cell28,
            this.cell29,
            this.cell30});
            this.row8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row8.FullRowSelected = false;
            this.row8.GroupPosition = -16;
            this.row8.Height = 25;
            this.row8.Text = null;
            // 
            // cell26
            // 
            this.cell26.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell26.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell26.Text = "   最 高 价：";
            // 
            // cell27
            // 
            this.cell27.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell27.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell27.InnerControl = this.txtHighPrice;
            // 
            // txtHighPrice
            // 
            this.txtHighPrice.BackColor = System.Drawing.Color.White;
            this.txtHighPrice.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.txtHighPrice.ForeColor = System.Drawing.Color.Blue;
            this.txtHighPrice.Location = new System.Drawing.Point(110, 211);
            this.txtHighPrice.Name = "txtHighPrice";
            this.txtHighPrice.Size = new System.Drawing.Size(121, 21);
            this.txtHighPrice.TabIndex = 18;
            this.txtHighPrice.Tag = this.cell27;
            this.txtHighPrice.Text = "0";
            this.txtHighPrice.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtHighPrice.YssCaption = "最高价";
            this.txtHighPrice.YssKiloDelimiter = true;
            this.txtHighPrice.YssLength = 28;
            this.txtHighPrice.YssNumeric = "15, 13";
            // 
            // cell28
            // 
            this.cell28.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell28.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cell29
            // 
            this.cell29.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell29.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell29.Text = "最 低 价：";
            // 
            // cell30
            // 
            this.cell30.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell30.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell30.InnerControl = this.txtLowPrice;
            // 
            // txtLowPrice
            // 
            this.txtLowPrice.BackColor = System.Drawing.Color.White;
            this.txtLowPrice.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.txtLowPrice.ForeColor = System.Drawing.Color.Blue;
            this.txtLowPrice.Location = new System.Drawing.Point(353, 211);
            this.txtLowPrice.Name = "txtLowPrice";
            this.txtLowPrice.Size = new System.Drawing.Size(121, 21);
            this.txtLowPrice.TabIndex = 19;
            this.txtLowPrice.Tag = this.cell30;
            this.txtLowPrice.Text = "0";
            this.txtLowPrice.TextAlign = System.Windows.Forms.HorizontalAlignment.Right;
            this.txtLowPrice.YssCaption = "最低价";
            this.txtLowPrice.YssKiloDelimiter = true;
            this.txtLowPrice.YssLength = 28;
            this.txtLowPrice.YssNumeric = "15, 13";
            // 
            // row9
            // 
            this.row9.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell31,
            this.cell32});
            this.row9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row9.FullRowSelected = false;
            this.row9.Text = null;
            // 
            // cell31
            // 
            this.cell31.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell31.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell31.Text = "   行情状态：";
            // 
            // cell32
            // 
            this.cell32.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell32.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell32.InnerControl = this.cboHqzt;
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
            this.cboHqzt.Location = new System.Drawing.Point(110, 236);
            this.cboHqzt.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataListByTypes";
            controlMethodInfo1.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo1.MethodParams")));
            controlMethodInfo1.MethodParamValues = new string[] {
        "HQZT_HQZL,"};
            controlMethodInfo1.Methods = null;
            this.cboHqzt.MethodInfo = controlMethodInfo1;
            this.cboHqzt.Name = "cboHqzt";
            this.cboHqzt.Parameter = "C_DV_NAME";
            this.cboHqzt.QueryCond = "HQZT_HQZL";
            this.cboHqzt.QueryType = "CacheType";
            this.cboHqzt.Size = new System.Drawing.Size(121, 21);
            this.cboHqzt.TabIndex = 20;
            this.cboHqzt.Tag = this.cell32;
            this.cboHqzt.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboHqzt.YssCaption = "行情状态";
            // 
            // Frm_SEC_MKT_INDEX_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(493, 317);
            this.DoubleBuffered = true;
            this.Name = "Frm_SEC_MKT_INDEX_S";
            this.Text = "指数行情资料";
            this.YssSetStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.tbMain.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.dtpMKTPriceTime)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private Yss.KTable.Models.Column column1;
        private Yss.KTable.Models.Column column2;
        private Yss.KTable.Models.Column column3;
        private Yss.KTable.Models.Column column4;
        private Yss.KTable.Models.Column column5;
        private Yss.KTable.Models.Row row1;
        private Yss.KTable.Models.Row row2blank;
        private Yss.KTable.Models.Row row3;
        private Yss.KTable.Models.Row row4;
        private Yss.KTable.Models.Row row2;
        private Yss.KTable.Models.Row row5blank;
        private Yss.KTable.Models.Row row5;
        private Yss.KTable.Models.Row row6;
        private Yss.KTable.Models.Row row7;
        private Yss.KTable.Models.Row row8;
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
        private Yss.KTable.Models.Cell cell18;
        private Yss.KTable.Models.Cell cell19;
        private Yss.KTable.Models.Cell cell20;
        private Yss.KTable.Models.Cell cell21;
        private Yss.KTable.Models.Cell cell22;
        private Yss.KTable.Models.Cell cell23;
        private Yss.KTable.Models.Cell cell24;
        private Yss.KTable.Models.Cell cell25;
        private Yss.KTable.Models.Cell cell26;
        private Yss.KTable.Models.Cell cell27;
        private Yss.KTable.Models.Cell cell28;
        private Yss.KTable.Models.Cell cell29;
        private Yss.KTable.Models.Cell cell30;
        private FAST.Core.BaseControl.YssSelCombox selSecurity;
        private FAST.Core.BaseControl.YssSelCombox selDvPlat;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpMKTPriceDate;
        private YssDevComponents.Editors.DateTimeAdv.DateTimeInput dtpMKTPriceTime;
        private Yss.KRichEx.YssTextBox txtClosePrice;
        private Yss.KRichEx.YssTextBox txtAveragePrice;
        private Yss.KRichEx.YssTextBox txtBuyPrice;
        private Yss.KRichEx.YssTextBox txtSellPrice;
        private Yss.KRichEx.YssTextBox txtOpenPrice;
        private Yss.KRichEx.YssTextBox txtNewPrice;
        private Yss.KRichEx.YssTextBox txtHighPrice;
        private Yss.KRichEx.YssTextBox txtLowPrice;
        private Yss.KTable.Models.Row row9;
        private Yss.KTable.Models.Cell cell31;
        private Yss.KTable.Models.Cell cell32;
        private FAST.Core.BaseControl.YssSelCombox cboHqzt;
    }
}