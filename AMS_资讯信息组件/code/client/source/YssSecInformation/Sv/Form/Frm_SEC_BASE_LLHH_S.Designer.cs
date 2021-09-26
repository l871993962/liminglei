﻿using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;

using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;
namespace YssSecInformation.Sv.Form
{
    partial class Frm_SEC_BASE_LLHH_S
    {
        /// <summary>
        /// 必需的设计器变量。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 清理所有正在使用的资源。
        /// </summary>
        /// <param name="disposing">如果应释放托管资源，为 true；否则为 false。</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows 窗体设计器生成的代码

        /// <summary>
        /// 设计器支持所需的方法 - 不要
        /// 使用代码编辑器修改此方法的内容。
        /// </summary>
        private void InitializeComponent()
        {
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo3 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo2 = new FAST.Core.BaseControl.ControlMethodInfo();
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo4 = new FAST.Core.BaseControl.ControlMethodInfo();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_SEC_BASE_LLHH_S));
            this.row1 = new Yss.KTable.Models.Row();
            this.row2 = new Yss.KTable.Models.Row();
            this.row3 = new Yss.KTable.Models.Row();
            this.cell1 = new Yss.KTable.Models.Cell();
            this.cell2 = new Yss.KTable.Models.Cell();
            this.txtSecCode = new Yss.KRichEx.YssTextBox();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.txtIsinCode = new Yss.KRichEx.YssTextBox();
            this.column1 = new Yss.KTable.Models.Column();
            this.column2 = new Yss.KTable.Models.Column();
            this.column3 = new Yss.KTable.Models.Column();
            this.column4 = new Yss.KTable.Models.Column();
            this.column5 = new Yss.KTable.Models.Column();
            this.txtSecMktCode = new Yss.KRichEx.YssTextBox();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.txtSecName = new Yss.KRichEx.YssTextBox();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.row4 = new Yss.KTable.Models.Row();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cboMkt = new FAST.Core.BaseControl.YssSelCombox();
            this.cell17 = new Yss.KTable.Models.Cell();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.cell13 = new Yss.KTable.Models.Cell();
            this.cell14 = new Yss.KTable.Models.Cell();
            this.cell15 = new Yss.KTable.Models.Cell();
            this.cboCury = new FAST.Core.BaseControl.YssSelCombox();
            this.cell20 = new Yss.KTable.Models.Cell();
            this.row5 = new Yss.KTable.Models.Row();
            this.cell16 = new Yss.KTable.Models.Cell();
            this.cell18 = new Yss.KTable.Models.Cell();
            this.cell19 = new Yss.KTable.Models.Cell();
            this.row6 = new Yss.KTable.Models.Row();
            this.row7 = new Yss.KTable.Models.Row();
            this.row8 = new Yss.KTable.Models.Row();
            this.cell21 = new Yss.KTable.Models.Cell();
            this.cell22 = new Yss.KTable.Models.Cell();
            this.dtpBegin = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.cell23 = new Yss.KTable.Models.Cell();
            this.cell24 = new Yss.KTable.Models.Cell();
            this.cell25 = new Yss.KTable.Models.Cell();
            this.dtpEnd = new FAST.Core.BaseControl.YssDateTimeInterval();
            this.row9 = new Yss.KTable.Models.Row();
            this.cell26 = new Yss.KTable.Models.Cell();
            this.cell27 = new Yss.KTable.Models.Cell();
            this.cboSecVar = new FAST.Core.BaseControl.YssSelCombox();
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
            this.tbMain.Controls.Add(this.cboSecVar);
            this.tbMain.Controls.Add(this.dtpEnd);
            this.tbMain.Controls.Add(this.dtpBegin);
            this.tbMain.Controls.Add(this.txtSecCode);
            this.tbMain.Controls.Add(this.txtSecName);
            this.tbMain.Controls.Add(this.cboCury);
            this.tbMain.Controls.Add(this.cboMkt);
            this.tbMain.Controls.Add(this.txtSecMktCode);
            this.tbMain.Controls.Add(this.txtIsinCode);
            this.tbMain.Location = new System.Drawing.Point(0, 31);
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3,
            this.row4,
            this.row5,
            this.row9,
            this.row6,
            this.row7,
            this.row8});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(493, 196);
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
            this.stBarBottom.Location = new System.Drawing.Point(0, 227);
            // 
            // pnlMain
            // 
            this.pnlMain.Padding = new System.Windows.Forms.Padding(0, 1, 0, 0);
            this.pnlMain.Size = new System.Drawing.Size(493, 252);
            //this.pnlMain.Style.Alignment = System.Drawing.StringAlignment.Center;
            //this.pnlMain.Style.BackColor1.Color = System.Drawing.Color.White;
            //this.pnlMain.Style.Border = YssDevComponents.DotNetBar.eBorderType.SingleLine;
            //this.pnlMain.Style.BorderColor.Color = System.Drawing.Color.White;
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
            this.yssPanel1.Size = new System.Drawing.Size(493, 252);
            // 
            // hpAssist
            // 
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
            this.row2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row2.FullRowSelected = false;
            this.row2.GroupPosition = -16;
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
            this.cell2.InnerControl = this.txtSecCode;
            // 
            // txtSecCode
            // 
            this.txtSecCode.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.txtSecCode.Border.Class = "TextBoxBorder";
            this.txtSecCode.Location = new System.Drawing.Point(110, 43);
            this.txtSecCode.Name = "txtSecCode";
            this.txtSecCode.Size = new System.Drawing.Size(121, 21);
            this.txtSecCode.TabIndex = 1;
            this.txtSecCode.Tag = this.cell2;
            this.txtSecCode.YssCaption = "证券内码";
            this.txtSecCode.YssIsMust = true;
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
            this.txtIsinCode.TabIndex = 2;
            this.txtIsinCode.Tag = this.cell5;
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
            // txtSecMktCode
            // 
            this.txtSecMktCode.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.txtSecMktCode.Border.Class = "TextBoxBorder";
            this.txtSecMktCode.Location = new System.Drawing.Point(110, 68);
            this.txtSecMktCode.Name = "txtSecMktCode";
            this.txtSecMktCode.Size = new System.Drawing.Size(121, 21);
            this.txtSecMktCode.TabIndex = 3;
            this.txtSecMktCode.Tag = this.cell7;
            this.txtSecMktCode.YssCaption = "上市代码";
            this.txtSecMktCode.YssIsMust = true;
            this.txtSecMktCode.YssLength = 30;
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.InnerControl = this.txtSecMktCode;
            // 
            // txtSecName
            // 
            this.txtSecName.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.txtSecName.Border.Class = "TextBoxBorder";
            this.txtSecName.Location = new System.Drawing.Point(353, 68);
            this.txtSecName.Name = "txtSecName";
            this.txtSecName.Size = new System.Drawing.Size(121, 21);
            this.txtSecName.TabIndex = 4;
            this.txtSecName.Tag = this.cell10;
            this.txtSecName.YssCaption = "证券名称";
            this.txtSecName.YssIsMust = true;
            this.txtSecName.YssLength = 50;
            // 
            // cell10
            // 
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell10.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell10.InnerControl = this.txtSecName;
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
            this.row4.Height = 25;
            this.row4.Text = null;
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.Text = "   互换代码：";
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
            this.cell9.Text = "互换名称：";
            // 
            // cboMkt
            // 
            this.cboMkt.AddedSelItemName = "";
            this.cboMkt.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.cboMkt.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboMkt.Border.Bottom = true;
            this.cboMkt.Border.Left = true;
            this.cboMkt.Border.Right = true;
            this.cboMkt.Border.Top = true;
            this.cboMkt.ClassName = "";
            this.cboMkt.DisplayName = "C_MKT_NAME";
            this.cboMkt.DisplayValue = "C_MKT_NO";
            this.cboMkt.DllName = "YssControls.dll";
            this.cboMkt.FilterCond = "";
            this.cboMkt.IsFillDecimal = false;
            this.cboMkt.KTableTree = true;
            this.cboMkt.Location = new System.Drawing.Point(353, 93);
            this.cboMkt.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo3.MethodName = "getDataList";
            controlMethodInfo3.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo3.MethodParams")));
            controlMethodInfo3.MethodParamValues = null;
            controlMethodInfo3.Methods = null;
            this.cboMkt.MethodInfo = controlMethodInfo3;
            this.cboMkt.Name = "cboMkt";
            this.cboMkt.NodeID = "C_MKT_CODE";
            this.cboMkt.Parameter = "C_MKT_CODE~C_MKT_NAME";
            this.cboMkt.ParaNodeID = "C_PARAENT_CODE";
            this.cboMkt.QueryCond = "";
            this.cboMkt.QueryType = "";
            this.cboMkt.Size = new System.Drawing.Size(121, 21);
            this.cboMkt.SortColumn = "C_MKT_NAME";
            this.cboMkt.TabIndex = 5;
            this.cboMkt.Tag = this.cell20;
            this.cboMkt.YssAssociaType = YssInformation.Support.Context.AssociaType.base_exchange;
            this.cboMkt.YssCaption = "交易市场";
            this.cboMkt.YssIsMust = true;
            this.cboMkt.YssKiloDelimiter = true;
            // 
            // cell17
            // 
            this.cell17.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell17.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell17.InnerControl = this.cboSecVar;
            // 
            // cell11
            // 
            this.cell11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell11.Text = "   合约期限：";
            // 
            // cell12
            // 
            this.cell12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell12.ForeColor = System.Drawing.SystemColors.ControlText;
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
            // 
            // cell15
            // 
            this.cell15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell15.ForeColor = System.Drawing.SystemColors.ControlText;
            // 
            // cboCury
            // 
            this.cboCury.AddedSelItemName = "";
            this.cboCury.BackColor = System.Drawing.Color.LightYellow;
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
            this.cboCury.Location = new System.Drawing.Point(110, 118);
            this.cboCury.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo2.MethodName = "getDataList";
            controlMethodInfo2.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo2.MethodParams")));
            controlMethodInfo2.MethodParamValues = null;
            controlMethodInfo2.Methods = null;
            this.cboCury.MethodInfo = controlMethodInfo2;
            this.cboCury.Name = "cboCury";
            this.cboCury.Parameter = "C_DC_NAME";
            this.cboCury.QueryCond = "";
            this.cboCury.QueryType = "";
            this.cboCury.Size = new System.Drawing.Size(121, 21);
            this.cboCury.TabIndex = 6;
            this.cboCury.Tag = this.cell27;
            this.cboCury.YssAssociaType = YssInformation.Support.Context.AssociaType.base_currency;
            this.cboCury.YssCaption = "交易币种";
            this.cboCury.YssIsMust = true;
            this.cboCury.YssKiloDelimiter = true;
            // 
            // cell20
            // 
            this.cell20.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell20.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell20.InnerControl = this.cboMkt;
            // 
            // row5
            // 
            this.row5.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell16,
            this.cell17,
            this.cell18,
            this.cell19,
            this.cell20});
            this.row5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row5.FullRowSelected = false;
            this.row5.Height = 25;
            this.row5.Text = null;
            // 
            // cell16
            // 
            this.cell16.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell16.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell16.Text = "   证券品种：";
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
            this.cell19.Name = "cell19";
            this.cell19.Text = "交易市场：";
            // 
            // row6
            // 
            this.row6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row6.FullRowSelected = false;
            this.row6.GroupLineLength = 310;
            this.row6.GroupPosition = 13;
            this.row6.Height = 10;
            this.row6.IsGroup = true;
            this.row6.Text = null;
            // 
            // row7
            // 
            this.row7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row7.FullRowSelected = false;
            this.row7.Height = 10;
            this.row7.Text = null;
            // 
            // row8
            // 
            this.row8.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell21,
            this.cell22,
            this.cell23,
            this.cell24,
            this.cell25});
            this.row8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row8.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row8.FullRowSelected = false;
            this.row8.Text = null;
            // 
            // cell21
            // 
            this.cell21.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell21.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell21.Text = "   开始日期：";
            // 
            // cell22
            // 
            this.cell22.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell22.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell22.InnerControl = this.dtpBegin;
            // 
            // dtpBegin
            // 
            this.dtpBegin.BackColor = System.Drawing.Color.Transparent;
            this.dtpBegin.DateBeginChecked = true;
            this.dtpBegin.DateEndChecked = true;
            this.dtpBegin.Location = new System.Drawing.Point(110, 161);
            this.dtpBegin.Margin = new System.Windows.Forms.Padding(0);
            this.dtpBegin.Name = "dtpBegin";
            this.dtpBegin.Size = new System.Drawing.Size(121, 21);
            this.dtpBegin.TabIndex = 7;
            this.dtpBegin.Tag = this.cell22;
            this.dtpBegin.yssEnabled = true;
            this.dtpBegin.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpBegin.yssInterval = false;
            this.dtpBegin.yssLabelStr = "至";
            this.dtpBegin.yssShowCheckBox = false;
            this.dtpBegin.yssShowOperLable = false;
            this.dtpBegin.YssShowSecond = true;
            this.dtpBegin.yssTimeControl = false;
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
            this.cell25.InnerControl = this.dtpEnd;
            // 
            // dtpEnd
            // 
            this.dtpEnd.BackColor = System.Drawing.Color.Transparent;
            this.dtpEnd.DateBeginChecked = true;
            this.dtpEnd.DateEndChecked = true;
            this.dtpEnd.Location = new System.Drawing.Point(353, 161);
            this.dtpEnd.Margin = new System.Windows.Forms.Padding(0);
            this.dtpEnd.Name = "dtpEnd";
            this.dtpEnd.Size = new System.Drawing.Size(121, 21);
            this.dtpEnd.TabIndex = 8;
            this.dtpEnd.Tag = this.cell25;
            this.dtpEnd.yssEnabled = true;
            this.dtpEnd.yssFormatDateStr = "yyyy-MM-dd";
            this.dtpEnd.yssInterval = false;
            this.dtpEnd.yssLabelStr = "至";
            this.dtpEnd.yssShowCheckBox = false;
            this.dtpEnd.yssShowOperLable = false;
            this.dtpEnd.YssShowSecond = true;
            this.dtpEnd.yssTimeControl = false;
            // 
            // row9
            // 
            this.row9.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell26,
            this.cell27});
            this.row9.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row9.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row9.FullRowSelected = false;
            this.row9.Text = null;
            // 
            // cell26
            // 
            this.cell26.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell26.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell26.Text = "   交易币种：";
            // 
            // cell27
            // 
            this.cell27.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell27.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell27.InnerControl = this.cboCury;
            // 
            // cboSecVar
            // 
            this.cboSecVar.AddedSelItemName = "";
            this.cboSecVar.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.cboSecVar.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboSecVar.Border.Bottom = true;
            this.cboSecVar.Border.Left = true;
            this.cboSecVar.Border.Right = true;
            this.cboSecVar.Border.Top = true;
            this.cboSecVar.ClassName = "";
            this.cboSecVar.DisplayName = "C_SEC_VAR_NAME";
            this.cboSecVar.DisplayValue = "C_SEC_VAR_CODE";
            this.cboSecVar.DllName = "YssControls.dll";
            this.cboSecVar.FilterCond = "";
            this.cboSecVar.IsFillDecimal = false;
            this.cboSecVar.KTableTree = false;
            this.cboSecVar.Location = new System.Drawing.Point(110, 93);
            this.cboSecVar.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo4.MethodName = "getDataListByTypes";
            controlMethodInfo4.MethodParams = null;
            controlMethodInfo4.MethodParamValues = new string[] {
        "HH,"};
            controlMethodInfo4.Methods = null;
            this.cboSecVar.MethodInfo = controlMethodInfo4;
            this.cboSecVar.Name = "cboSecVar";
            this.cboSecVar.Parameter = "C_SEC_VAR_NAME";
            this.cboSecVar.QueryCond = "HH";
            this.cboSecVar.QueryType = "CacheType";
            this.cboSecVar.Size = new System.Drawing.Size(121, 21);
            this.cboSecVar.TabIndex = 9;
            this.cboSecVar.Tag = this.cell17;
            this.cboSecVar.YssAssociaType = YssInformation.Support.Context.AssociaType.base_seccategory;
            this.cboSecVar.YssCaption = "证券品种";
            this.cboSecVar.YssIsMust = true;
            this.cboSecVar.YssKiloDelimiter = true;
            // 
            // Frm_SEC_BASE_LLHH_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.ClientSize = new System.Drawing.Size(493, 252);
            this.DoubleBuffered = true;
            this.Name = "Frm_SEC_BASE_LLHH_S";
            this.Text = "互换品种信息";
            this.YssSetStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssOnAfterNewClick += new FrmBaseSet.AfterNewClick(this.Frm_SEC_BASE_LLHH_S_YssOnAfterNewClick);
            this.Load += new System.EventHandler(this.Frm_SEC_BASE_LLHH_S_Load);
            this.YssOnBeforeSaveClick += new FrmBaseSet.BeforeSaveClick(this.Frm_SEC_BASE_LLHH_S_YssOnBeforeSaveClick);
            this.tbMain.ResumeLayout(false);
            this.pnlMain.ResumeLayout(false);
            this.yssPanel1.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

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
        private Yss.KTable.Models.Row row4;
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
        private Yss.KRichEx.YssTextBox txtSecMktCode;
        private Yss.KRichEx.YssTextBox txtIsinCode;
        private Yss.KRichEx.YssTextBox txtSecName;
        private FAST.Core.BaseControl.YssSelCombox cboMkt;
        private FAST.Core.BaseControl.YssSelCombox cboCury;
        private Yss.KTable.Models.Row row5;
        private Yss.KTable.Models.Cell cell16;
        private Yss.KTable.Models.Cell cell17;
        private Yss.KTable.Models.Cell cell18;
        private Yss.KTable.Models.Cell cell19;
        private Yss.KTable.Models.Cell cell20;
        private Yss.KRichEx.YssTextBox txtSecCode;
        private Yss.KTable.Models.Row row6;
        private Yss.KTable.Models.Row row7;
        private Yss.KTable.Models.Row row8;
        private Yss.KTable.Models.Cell cell21;
        private Yss.KTable.Models.Cell cell22;
        private Yss.KTable.Models.Cell cell23;
        private Yss.KTable.Models.Cell cell24;
        private Yss.KTable.Models.Cell cell25;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpEnd;
        private FAST.Core.BaseControl.YssDateTimeInterval dtpBegin;
        private Yss.KTable.Models.Row row9;
        private Yss.KTable.Models.Cell cell26;
        private Yss.KTable.Models.Cell cell27;
        private FAST.Core.BaseControl.YssSelCombox cboSecVar;
    }
}