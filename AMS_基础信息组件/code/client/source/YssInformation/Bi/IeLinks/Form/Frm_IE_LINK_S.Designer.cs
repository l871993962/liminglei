using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;

using FAST.Core.Resource;
namespace YssInformation.Bi.IeLinks.Form
{
    partial class Frm_IE_LINK_S
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Frm_IE_LINK_S));
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
            this.cboIeItem = new FAST.Core.BaseControl.YssSelCombox();
            this.cboFee = new FAST.Core.BaseControl.YssSelCombox();
            this.row4 = new Yss.KTable.Models.Row();
            this.cell6 = new Yss.KTable.Models.Cell();
            this.cell7 = new Yss.KTable.Models.Cell();
            this.cboFeeP = new FAST.Core.BaseControl.YssSelCombox();
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
            this.tbMain.Controls.Add(this.cboFeeP);
            this.tbMain.Controls.Add(this.cboFee);
            this.tbMain.Controls.Add(this.cboIeItem);
            this.tbMain.Location = new System.Drawing.Point(0, 31);
            this.tbMain.Rows.AddRange(new Yss.KTable.Models.Row[] {
            this.row1,
            this.row2,
            this.row3,
            this.row4});
            this.tbMain.ShowColumnHeader = false;
            this.tbMain.Size = new System.Drawing.Size(493, 110);
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
            this.stBarBottom.Location = new System.Drawing.Point(0, 141);
            // 
            // pnlMain
            // 
            this.pnlMain.Padding = new System.Windows.Forms.Padding(0, 1, 0, 0);
            this.pnlMain.Size = new System.Drawing.Size(493, 166);
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
            this.yssPanel1.Size = new System.Drawing.Size(493, 166);
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
            this.column4.Width = 91;
            // 
            // column5
            // 
            this.column5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.column5.Width = 122;
            // 
            // row1
            // 
            this.row1.Font = new System.Drawing.Font("新宋体", 9.75F, System.Drawing.FontStyle.Bold);
            this.row1.ForeColor = System.Drawing.Color.FromArgb(((int)(((byte)(54)))), ((int)(((byte)(80)))), ((int)(((byte)(113)))));
            this.row1.FullRowSelected = false;
            this.row1.GroupLineLength = 310;
            this.row1.Height = 30;
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
            this.cell1.Text = "   收支代码：";
            // 
            // cell2
            // 
            this.cell2.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell2.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell2.InnerControl = this.cboFee;
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
            this.cell4.Text = "收支项目：";
            // 
            // cell5
            // 
            this.cell5.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell5.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell5.InnerControl = this.cboIeItem;
            // 
            // cboIeItem
            // 
            this.cboIeItem.AddedSelItemName = "";
            this.cboIeItem.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.cboIeItem.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboIeItem.Border.Bottom = true;
            this.cboIeItem.Border.Left = true;
            this.cboIeItem.Border.Right = true;
            this.cboIeItem.Border.Top = true;
            this.cboIeItem.ClassName = "";
            this.cboIeItem.DisplayName = "C_IE_NAME";
            this.cboIeItem.DisplayValue = "C_IE_CODE";
            this.cboIeItem.DllName = "YssControls.dll";
            this.cboIeItem.FilterCond = "";
            this.cboIeItem.IsFillDecimal = false;
            this.cboIeItem.Location = new System.Drawing.Point(353, 40);
            this.cboIeItem.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo3.MethodName = "getDataList";
            controlMethodInfo3.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo3.MethodParams")));
            controlMethodInfo3.MethodParamValues = null;
            controlMethodInfo3.Methods = null;
            this.cboIeItem.MethodInfo = controlMethodInfo3;
            this.cboIeItem.Name = "cboIeItem";
            this.cboIeItem.Parameter = "C_IE_CODE~C_IE_NAME";
            this.cboIeItem.QueryCond = "";
            this.cboIeItem.QueryType = "CacheType";
            this.cboIeItem.SelectMode = Yss.KRichEx.AutoFilter.SelectMode.Any;
            this.cboIeItem.Size = new System.Drawing.Size(121, 21);
            this.cboIeItem.TabIndex = 4;
            this.cboIeItem.Tag = this.cell5;
            this.cboIeItem.YssAssociaType = YssInformation.Support.Context.AssociaType.base_IeItem;
            this.cboIeItem.YssCaption = "收支项目";
            this.cboIeItem.YssIsMust = true;
            this.cboIeItem.YssKiloDelimiter = true;
            // 
            // cboFee
            // 
            this.cboFee.AddedSelItemName = "";
            this.cboFee.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.cboFee.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboFee.Border.Bottom = true;
            this.cboFee.Border.Left = true;
            this.cboFee.Border.Right = true;
            this.cboFee.Border.Top = true;
            this.cboFee.ClassName = "";
            this.cboFee.DisplayName = "C_IE_CODE_C_IE_NAME";
            this.cboFee.DisplayValue = "C_IE_CODE";
            this.cboFee.DllName = "YssControls.dll";
            this.cboFee.FilterCond = "";
            this.cboFee.IsFillDecimal = false;
            this.cboFee.Location = new System.Drawing.Point(110, 40);
            this.cboFee.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo2.MethodName = "getDataList";
            controlMethodInfo2.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo2.MethodParams")));
            controlMethodInfo2.MethodParamValues = null;
            controlMethodInfo2.Methods = null;
            this.cboFee.MethodInfo = controlMethodInfo2;
            this.cboFee.Name = "cboFee";
            this.cboFee.Parameter = "C_IE_CODE~C_IE_NAME";
            this.cboFee.QueryCond = "";
            this.cboFee.QueryType = "";
            this.cboFee.SelectMode = Yss.KRichEx.AutoFilter.SelectMode.Any;
            this.cboFee.Size = new System.Drawing.Size(121, 21);
            this.cboFee.TabIndex = 5;
            this.cboFee.Tag = this.cell2;
            this.cboFee.YssAssociaType = YssInformation.Support.Context.AssociaType.base_ie;
            this.cboFee.YssCaption = "收支代码";
            this.cboFee.YssIsMust = true;
            this.cboFee.YssKiloDelimiter = true;
            this.cboFee.AfterDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(this.cboFee_AfterDropDownClick);
            this.cboFee.SelectedValueChanged += new System.EventHandler(this.cboFee_SelectedValueChanged);
            // 
            // row4
            // 
            this.row4.Cells.AddRange(new Yss.KTable.Models.Cell[] {
            this.cell6,
            this.cell7});
            this.row4.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.row4.ForeColor = System.Drawing.SystemColors.ControlText;
            this.row4.FullRowSelected = false;
            this.row4.Text = null;
            // 
            // cell6
            // 
            this.cell6.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell6.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell6.Text = "   上级费用：";
            // 
            // cell7
            // 
            this.cell7.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.cell7.ForeColor = System.Drawing.SystemColors.ControlText;
            this.cell7.InnerControl = this.cboFeeP;
            // 
            // cboFeeP
            // 
            this.cboFeeP.AddedSelItemName = "";
            this.cboFeeP.BackColor = System.Drawing.Color.LightYellow;
            // 
            // 
            // 
            this.cboFeeP.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboFeeP.Border.Bottom = true;
            this.cboFeeP.Border.Left = true;
            this.cboFeeP.Border.Right = true;
            this.cboFeeP.Border.Top = true;
            this.cboFeeP.ClassName = "";
            this.cboFeeP.DisplayName = "C_IE_CODE_C_IE_NAME";
            this.cboFeeP.DisplayValue = "C_IE_CODE";
            this.cboFeeP.DllName = "YssControls.dll";
            this.cboFeeP.FilterCond = "";
            this.cboFeeP.IsFillDecimal = false;
            this.cboFeeP.Location = new System.Drawing.Point(110, 65);
            this.cboFeeP.Margin = new System.Windows.Forms.Padding(0);
            controlMethodInfo1.MethodName = "getDataList";
            controlMethodInfo1.MethodParams = ((System.Collections.Generic.Dictionary<string, string[]>)(resources.GetObject("controlMethodInfo1.MethodParams")));
            controlMethodInfo1.MethodParamValues = null;
            controlMethodInfo1.Methods = null;
            this.cboFeeP.MethodInfo = controlMethodInfo1;
            this.cboFeeP.Name = "cboFeeP";
            this.cboFeeP.Parameter = "C_IE_CODE~C_IE_NAME";
            this.cboFeeP.QueryCond = "";
            this.cboFeeP.QueryType = "";
            this.cboFeeP.SelectMode = Yss.KRichEx.AutoFilter.SelectMode.Any;
            this.cboFeeP.Size = new System.Drawing.Size(121, 21);
            this.cboFeeP.TabIndex = 6;
            this.cboFeeP.Tag = this.cell7;
            this.cboFeeP.YssAssociaType = YssInformation.Support.Context.AssociaType.base_ie;
            this.cboFeeP.YssCaption = "收支代码";
            this.cboFeeP.YssKiloDelimiter = true;
            this.cboFeeP.YssOnBeforeLoadData += new FAST.Core.BaseControl.YssSelCombox.yssBeforeLoadData(this.cboFeeP_YssOnBeforeLoadData);
            this.cboFeeP.SelectedValueChanged += new System.EventHandler(this.cboFeeP_SelectedValueChanged);
            // 
            // Frm_IE_LINK_S
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(493, 166);
            this.DoubleBuffered = true;
            this.Name = "Frm_IE_LINK_S";
            this.Text = "收支链接设置";
            this.YssSetStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.YssStatus = FAST.Core.Context.ClsEnums.StatusSetting.YssAdd;
            this.Load += new System.EventHandler(this.Frm_IE_LINK_S_Load);
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
        private Yss.KTable.Models.Cell cell1;
        private Yss.KTable.Models.Cell cell2;
        private Yss.KTable.Models.Cell cell3;
        private Yss.KTable.Models.Cell cell4;
        private Yss.KTable.Models.Cell cell5;
        private FAST.Core.BaseControl.YssSelCombox cboFee;
        private FAST.Core.BaseControl.YssSelCombox cboIeItem;
        private Yss.KTable.Models.Row row4;
        private Yss.KTable.Models.Cell cell6;
        private Yss.KTable.Models.Cell cell7;
        private FAST.Core.BaseControl.YssSelCombox cboFeeP;
    }
}