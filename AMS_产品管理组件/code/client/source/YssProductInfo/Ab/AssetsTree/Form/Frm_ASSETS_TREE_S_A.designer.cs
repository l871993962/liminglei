//-----------------------------------------------------------------------
// <copyright file="Frm_ASSETS_TREE_S_A.designer.cs" company="yss">
//     Company copyright tag.
// </copyright>
//-----------------------------------------------------------------------
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;

using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;
using FAST.Core.Context;
namespace YssProductInfo.Ab.AssetsTree.Form
{
    public partial class Frm_ASSETS_TREE_S_A
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
            if (disposing && (this.components != null))
            {
                this.components.Dispose();
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
            this.txtSectorCode = new Yss.KRichEx.YssTextBox();
            this.cell3 = new Yss.KTable.Models.Cell();
            this.cell4 = new Yss.KTable.Models.Cell();
            this.cell5 = new Yss.KTable.Models.Cell();
            this.txtSectorName = new Yss.KRichEx.YssTextBox();
            this.row4 = new Yss.KTable.Models.Row();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.cboSector = new FAST.Core.BaseControl.YssSelCombox();
            this.cell8 = new Yss.KTable.Models.Cell();
            this.cell9 = new Yss.KTable.Models.Cell();
            this.cell10 = new Yss.KTable.Models.Cell();
            this.cboFlGZ = new FAST.Core.BaseControl.YssSelCombox();
            this.cboCode = new FAST.Core.BaseControl.YssSelCombox();
            this.cell12 = new Yss.KTable.Models.Cell();
            this.row5 = new Yss.KTable.Models.Row();
            this.cell11 = new Yss.KTable.Models.Cell();
            this.cell13 = new Yss.KTable.Models.Cell();
            this.cell14 = new Yss.KTable.Models.Cell();
            this.cell15 = new Yss.KTable.Models.Cell();
            this.cboUndis = new FAST.Core.BaseControl.YssSelCombox();
            this.row6 = new Yss.KTable.Models.Row();
            this.cell16 = new Yss.KTable.Models.Cell();
            this.cell17 = new Yss.KTable.Models.Cell();
            this.cboAutoZr = new FAST.Core.BaseControl.YssSelCombox();
            this.cell18 = new Yss.KTable.Models.Cell();
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
            this.tbMain.Controls.Add(this.cboCode);
            this.tbMain.Controls.Add(this.cboSector);
            this.tbMain.Controls.Add(this.cboFlGZ);
            this.tbMain.Controls.Add(this.txtSectorCode);
            this.tbMain.Controls.Add(this.txtSectorName);
            this.tbMain.Controls.Add(this.cboUndis);
            this.tbMain.Controls.Add(this.cboAutoZr);
            this.tbMain.GridLineColor = System.Drawing.Color.White;
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3,
            this.row4,
            this.row5,
            this.row6});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(493, 147);
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
            this.stBarBottom.Location = new System.Drawing.Point(0, 177);
            this.stBarBottom.StatuType = "新增(&Add...)";
            // 
            // pnlMain
            // 
            this.pnlMain.Size = new System.Drawing.Size(493, 202);
            // 
            // yssPanel1
            // 
            // 
            // 
            // 
            this.yssPanel1.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(129)))), ((int)(((byte)(155)))), ((int)(((byte)(182)))));
            this.yssPanel1.Size = new System.Drawing.Size(493, 202);
            // 
            // hpAssist
            // 
            // 
            // column1
            // 
            this.column1.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column1.Name = "column1";
            this.column1.Width = 110;
            // 
            // column2
            // 
            this.column2.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column2.Name = "column2";
            this.column2.Width = 122;
            // 
            // column3
            // 
            this.column3.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column3.Name = "column3";
            this.column3.Width = 30;
            // 
            // column4
            // 
            this.column4.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column4.Name = "column4";
            this.column4.Width = 91;
            // 
            // column5
            // 
            this.column5.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Left;
            this.column5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column5.Name = "column5";
            this.column5.Width = 122;
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("新宋体", 9.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row1.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row1.FullRowSelected = false;
            this.row1.GroupLineLength = 310;
            this.row1.Height = 33;
            this.row1.IsGroup = true;
            this.row1.Name = "row1";
            this.row1.Text = "基本信息";
            // 
            // row2
            // 
            this.row2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.row2.ForeColor = System.Drawing.Color.Black;
            this.row2.FullRowSelected = false;
            this.row2.GroupLineLength = 310;
            this.row2.Height = 10;
            this.row2.Name = "row2";
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
            this.row3.ForeColor = System.Drawing.Color.Black;
            this.row3.FullRowSelected = false;
            this.row3.GroupLineLength = 310;
            this.row3.Height = 25;
            this.row3.Name = "row3";
            this.row3.Text = null;
            // 
            // cell1
            // 
            this.cell1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell1.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell1.InnerControl = null;
            this.cell1.Name = "cell1";
            this.cell1.Text = "   结构代码：";
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.txtSectorCode;
            this.cell2.Name = "cell2";
            // 
            // txtSectorCode
            // 
            this.txtSectorCode.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.txtSectorCode.Location = new System.Drawing.Point(110, 43);
            this.txtSectorCode.Name = "txtSectorCode";
            this.txtSectorCode.Size = new System.Drawing.Size(121, 21);
            this.txtSectorCode.TabIndex = 1;
            this.txtSectorCode.Tag = this.cell2;
            this.txtSectorCode.YssCaption = "结构代码";
            this.txtSectorCode.YssIsMust = true;
            // 
            // cell3
            // 
            this.cell3.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell3.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell3.InnerControl = null;
            this.cell3.Name = "cell3";
            // 
            // cell4
            // 
            this.cell4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell4.InnerControl = null;
            this.cell4.Name = "cell4";
            this.cell4.Text = "结构名称：";
            // 
            // cell5
            // 
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.txtSectorName;
            this.cell5.Name = "cell5";
            // 
            // txtSectorName
            // 
            this.txtSectorName.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.txtSectorName.Location = new System.Drawing.Point(353, 43);
            this.txtSectorName.Name = "txtSectorName";
            this.txtSectorName.Size = new System.Drawing.Size(121, 21);
            this.txtSectorName.TabIndex = 2;
            this.txtSectorName.Tag = this.cell5;
            this.txtSectorName.YssCaption = "结构名称";
            this.txtSectorName.YssIsMust = true;
            // 
            // row4
            // 
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
            this.row4.Height = 25;
            this.row4.Name = "row4";
            this.row4.Text = null;
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.InnerControl = null;
            this.cell6.Name = "cell6";
            this.cell6.Text = "   上级结构：";
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.InnerControl = this.cboSector;
            this.cell7.Name = "cell7";
            // 
            // cboSector
            // 
            this.cboSector.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboSector.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboSector.DisplayName = "C_TR_NAME";
            this.cboSector.DisplayValue = "C_TR_CODE";
            this.cboSector.IsFillDecimal = false;
            this.cboSector.KTableTree = true;
            this.cboSector.Location = new System.Drawing.Point(110, 68);
            this.cboSector.Margin = new System.Windows.Forms.Padding(0);
            this.cboSector.MethodInfo.MethodName = "getDataList";
            this.cboSector.Name = "cboSector";
            this.cboSector.NodeID = "C_TR_CODE";
            this.cboSector.Parameter = "C_TR_CODE~C_TR_NAME~C_TR_CODE_P";
            this.cboSector.ParaNodeID = "C_TR_CODE_P";
            this.cboSector.PrefixBackColor = System.Drawing.Color.White;
            this.cboSector.SelectMode = Yss.KRichEx.AutoFilter.SelectMode.Any;
            this.cboSector.Size = new System.Drawing.Size(121, 21);
            this.cboSector.SortColumn = "C_TR_NAME";
            this.cboSector.TabIndex = 6;
            this.cboSector.Tag = this.cell7;
            this.cboSector.YssAssociaType = FAST.Core.Context.AssociaFAST.NULL;
            this.cboSector.BeforeDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(this.cboSector_BeforeDropDownClick);
            this.cboSector.YssOnBeforeLoadData += new FAST.Core.BaseControl.YssSelCombox.yssBeforeLoadData(this.cboSector_YssOnBeforeLoadData);
            // 
            // cell8
            // 
            this.cell8.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.cell8.ForeColor = System.Drawing.Color.Black;
            this.cell8.InnerControl = null;
            this.cell8.Name = "cell8";
            // 
            // cell9
            // 
            this.cell9.Font = new System.Drawing.Font("宋体", 9F);
            this.cell9.ForeColor = System.Drawing.Color.Black;
            this.cell9.InnerControl = null;
            this.cell9.Name = "cell9";
            this.cell9.Text = "分类规则：";
            // 
            // cell10
            // 
            this.cell10.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold);
            this.cell10.ForeColor = System.Drawing.Color.Black;
            this.cell10.InnerControl = this.cboFlGZ;
            this.cell10.Name = "cell10";
            // 
            // cboFlGZ
            // 
            this.cboFlGZ.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboFlGZ.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            ////this.cboFlGZ.ExpandButtonImage = global::YssPara.Properties.Resources.SetExpand_Hot;
            this.cboFlGZ.ExpandButtonImageSize = new System.Drawing.Size(18, 19);
            this.cboFlGZ.IsFillDecimal = false;
            this.cboFlGZ.Location = new System.Drawing.Point(353, 68);
            this.cboFlGZ.Margin = new System.Windows.Forms.Padding(0);
            this.cboFlGZ.MethodInfo.MethodName = "getDataListByTypes";
            this.cboFlGZ.MethodInfo.MethodParamValues = new string[] {
        "ASSET_TREE_TYPE,"};
            this.cboFlGZ.Name = "cboFlGZ";
            this.cboFlGZ.PrefixBackColor = System.Drawing.Color.White;
            this.cboFlGZ.Size = new System.Drawing.Size(121, 21);
            this.cboFlGZ.TabIndex = 5;
            this.cboFlGZ.Tag = this.cell10;
            this.cboFlGZ.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboFlGZ.YssCaption = "分类规则";
            this.cboFlGZ.ExpandClick += new System.EventHandler(this.cboFlGZ_ExpandClick);
            this.cboFlGZ.SelectedValueChanged += new System.EventHandler(this.cboFlGZ_SelectedValueChanged);
            // 
            // cboCode
            // 
            this.cboCode.AddedSelItemName = "";
            // 
            // 
            // 
            this.cboCode.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboCode.DisplayName = "C_PORT_NAME_ST";
            this.cboCode.DisplayValue = "C_PORT_CODE";
            this.cboCode.IsFillDecimal = false;
            this.cboCode.IsFocused = true;
            this.cboCode.Location = new System.Drawing.Point(110, 93);
            this.cboCode.Margin = new System.Windows.Forms.Padding(0);
            this.cboCode.MethodInfo.MethodName = "getDataList";
            this.cboCode.Name = "cboCode";
            this.cboCode.Parameter = "C_PORT_CODE~C_PORT_NAME_ST";
            this.cboCode.PrefixBackColor = System.Drawing.Color.White;
            this.cboCode.RequestEveryTime = true;
            this.cboCode.SelectMode = Yss.KRichEx.AutoFilter.SelectMode.Any;
            this.cboCode.ShowPrefixBackGround = false;
            this.cboCode.Size = new System.Drawing.Size(121, 21);
            this.cboCode.TabIndex = 7;
            this.cboCode.Tag = this.cell12;
            this.cboCode.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Value_Name;
            this.cboCode.YssAssociaType = FAST.Core.Context.AssociaFAST.usermanage;
            // 
            // cell12
            // 
            this.cell12.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell12.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell12.InnerControl = this.cboCode;
            this.cell12.Name = "cell12";
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
            this.row5.Name = "row5";
            this.row5.Text = null;
            // 
            // cell11
            // 
            this.cell11.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell11.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell11.InnerControl = null;
            this.cell11.Name = "cell11";
            this.cell11.Text = "   用户：";
            // 
            // cell13
            // 
            this.cell13.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell13.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell13.InnerControl = null;
            this.cell13.Name = "cell13";
            // 
            // cell14
            // 
            this.cell14.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell14.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell14.InnerControl = null;
            this.cell14.Name = "cell14";
            this.cell14.Text = "显示未分配：";
            // 
            // cell15
            // 
            this.cell15.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell15.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell15.InnerControl = this.cboUndis;
            this.cell15.Name = "cell15";
            // 
            // cboUndis
            // 
            this.cboUndis.AddedSelItemName = "";
            this.cboUndis.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.cboUndis.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboUndis.DisplayName = "C_DV_NAME";
            this.cboUndis.DisplayValue = "C_DV_CODE";
            this.cboUndis.Location = new System.Drawing.Point(353, 93);
            this.cboUndis.Margin = new System.Windows.Forms.Padding(0);
            this.cboUndis.MethodInfo.MethodName = "getDataListByTypes";
            this.cboUndis.MethodInfo.MethodParamValues = new string[] {
        "BOOL_TYPE,"};
            this.cboUndis.Name = "cboUndis";
            this.cboUndis.Parameter = "C_DV_NAME";
            this.cboUndis.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.cboUndis.Size = new System.Drawing.Size(121, 21);
            this.cboUndis.TabIndex = 8;
            this.cboUndis.Tag = this.cell15;
            this.cboUndis.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboUndis.YssCaption = "显示未分配";
            this.cboUndis.YssIsMust = true;
            // 
            // row6
            // 
            this.row6.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell16,
            this.cell17,
            this.cell18});
            this.row6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row6.FullRowSelected = false;
            this.row6.Name = "row6";
            this.row6.Text = null;
            // 
            // cell16
            // 
            this.cell16.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell16.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell16.InnerControl = null;
            this.cell16.Name = "cell16";
            this.cell16.Text = "   自动转入：";
            // 
            // cell17
            // 
            this.cell17.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell17.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell17.InnerControl = this.cboAutoZr;
            this.cell17.Name = "cell17";
            // 
            // cboAutoZr
            // 
            this.cboAutoZr.AddedSelItemName = "";
            this.cboAutoZr.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            // 
            // 
            // 
            this.cboAutoZr.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboAutoZr.ExpandButtonImageSize = new System.Drawing.Size(18, 19);
            this.cboAutoZr.IsFillDecimal = false;
            this.cboAutoZr.Location = new System.Drawing.Point(110, 116);
            this.cboAutoZr.Margin = new System.Windows.Forms.Padding(0);
            this.cboAutoZr.MethodInfo.MethodName = "getDataListByTypes";
            this.cboAutoZr.MethodInfo.MethodParamValues = new string[] {
        "ASSET_TREE_AUTO,"};
            this.cboAutoZr.Name = "cboAutoZr";
            this.cboAutoZr.PrefixBackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(245)))), ((int)(((byte)(182)))));
            this.cboAutoZr.Size = new System.Drawing.Size(121, 21);
            this.cboAutoZr.TabIndex = 7;
            this.cboAutoZr.Tag = this.cell17;
            this.cboAutoZr.Visible = false;
            this.cboAutoZr.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboAutoZr.YssCaption = "自动转入";
            this.cboAutoZr.YssIsMust = true;
            // 
            // cell18
            // 
            this.cell18.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell18.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell18.InnerControl = null;
            this.cell18.Name = "cell18";
            // 
            // Frm_ASSETS_TREE_S_A
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(493, 202);
            this.DoubleBuffered = true;
            this.Name = "Frm_ASSETS_TREE_S_A";
            this.StatuType = "新增(&Add...)";
            this.Text = "板块信息设置";
            this.YssSetStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.Load += new System.EventHandler(this.Frm_ASSETS_TREE_S_A_Load);
            this.YssOnBeforeSaveClick += new FAST.Core.CRUD.Form.FrmBaseSet.BeforeSaveClick(this.Frm_ASSETS_TREE_S_A_YssOnBeforeSaveClick);
            this.YssOnBeforeDelClick += new FAST.Core.CRUD.Form.FrmBaseSet.BeforeDelClick(this.Frm_ASSETS_TREE_S_A_YssOnBeforeDelClick);
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
        private Yss.KRichEx.YssTextBox txtSectorName;
        private Yss.KRichEx.YssTextBox txtSectorCode;
        private Yss.KTable.Models.Cell cell8;
        private Yss.KTable.Models.Cell cell9;
        private Yss.KTable.Models.Cell cell10;
        private FAST.Core.BaseControl.YssSelCombox cboFlGZ;
        private FAST.Core.BaseControl.YssSelCombox cboSector;
        private FAST.Core.BaseControl.YssSelCombox cboCode;
        private Yss.KTable.Models.Cell cell12;
        private Yss.KTable.Models.Row row5;
        private Yss.KTable.Models.Cell cell11;
        private Yss.KTable.Models.Cell cell13;
        private Yss.KTable.Models.Cell cell14;
        private Yss.KTable.Models.Cell cell15;
        private FAST.Core.BaseControl.YssSelCombox cboUndis;
        private Yss.KTable.Models.Row row6;
        private Yss.KTable.Models.Cell cell16;
        private Yss.KTable.Models.Cell cell17;
        private Yss.KTable.Models.Cell cell18;
        private FAST.Core.BaseControl.YssSelCombox cboAutoZr;
    }
}