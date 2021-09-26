﻿using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;
using FAST.Core.BaseForm;
using Yss.KTable.Models;
using FAST.Core.BaseControl.Fun;
using FAST.Core.Communication.Service;
using FAST.Common.Service.Dict.Pojo;
using YssInformation.Support.Sys.PortBusinessRange.Service;
using Yss.KMessage;
using Yss.KRichEx;

namespace YssInformation.Sys.PortBusinessRange.Form
{
    /// <summary>
    /// 业务类型设置窗口
    /// </summary>
    public partial class Frm_PortBusinessTypeSet_S : FrmTabItemSet
    {
        /// <summary>
        /// 业务类型服务接口
        /// </summary>
        private IPortBusinessRangeService portBusinessService;

        /// <summary>
        /// 业务类型代码
        /// </summary>
        private YssTextBox txtBusinessCode;

        /// <summary>
        /// 业务类型名称
        /// </summary>
        private YssTextBox txtBusinessName;

        /// <summary>
        /// 业务类型
        /// </summary>
        private string businessType = "AO_AUTO_BUSINESS";

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_PortBusinessTypeSet_S()
        {
            InitializeComponent();
            this.rtbMain.Table.AllowResizeColumn = true;
            this.rtbMain.Table.Border.Top = false;
            this.rtbMain.Table.Border.Bottom = false;
            this.rtbMain.Table.Border.Left = false;
            this.rtbMain.Table.DefaultToolStripItems = Yss.KTable.Enums.SysToolStripItems.None;
            this.rtbMain.Table.SelectionMode = SelectionMode.One;
            this.rtbMain.Table.SelectedCellChanged += this.rtbMain_SelectedCellChanged;
            this.bUseMVCService = true;
            InitializeColumns();
            initServiceMVC();
            initTextBox();
        }

        /// <summary>
        /// 点击改变事件
        /// </summary>
        /// <param name="sender">事件源</param>
        /// <param name="e">事件参数</param>
        private void rtbMain_SelectedCellChanged(object sender, Yss.KTable.Events.CellEventArgs e)
        {
            if (e.Cell.RelColumn.DataPropertyName != "businessTypeCode" && e.Cell.RelColumn.DataPropertyName != "businessTypeName")
            {
                return;
            }

            if (e.Cell.RelColumn.DataPropertyName == "businessTypeCode")
            {
                if (!e.Cell.Selected)
                {
                    e.Cell.InnerControl = null;
                    e.Cell.Text = this.txtBusinessCode.Text;
                    e.Cell.Tag = e.Cell.Text;
                }
                else
                {
                    this.txtBusinessCode.Text = e.Cell.Text;
                    e.Cell.InnerControl = this.txtBusinessCode;
                }
            }
            else if (e.Cell.RelColumn.DataPropertyName == "businessTypeName")
            {
                if (!e.Cell.Selected)
                {
                    e.Cell.InnerControl = null;
                    e.Cell.Text = this.txtBusinessName.Text;
                    e.Cell.Tag = e.Cell.Text;
                }
                else
                {
                    this.txtBusinessName.Text = e.Cell.Text;
                    e.Cell.InnerControl = this.txtBusinessName;
                }
            }
        }

        /// <summary>
        /// 初始化列
        /// </summary>
        private void InitializeColumns()
        {
            Column newColumn = new MarkColumn();
            this.rtbMain.Table.Columns.Add(newColumn);
            newColumn = new Column();
            newColumn.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Center;
            newColumn.CellTextAlign = ContentAlignment.MiddleCenter;
            newColumn.DataPropertyName = "businessTypeCode";
            newColumn.Text = "业务类型代码";
            newColumn.Width = 220;
            this.rtbMain.Table.Columns.Add(newColumn);

            newColumn = new Column();
            newColumn.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Center;
            newColumn.CellTextAlign = ContentAlignment.MiddleCenter;
            newColumn.DataPropertyName = "businessTypeName";
            newColumn.Text = "业务类型名称";
            newColumn.Width = 318;
            this.rtbMain.Table.Columns.Add(newColumn);
        }

        /// <summary>
        /// 初始化服务
        /// </summary>
        protected override void initServiceMVC()
        {
            if (portBusinessService == null)
            {
                portBusinessService = ServiceFactory.createService<IPortBusinessRangeService>();
            }
        }

        /// <summary>
        /// 控件初始化
        /// </summary>
        private void initTextBox()
        {
            ////TextBox控件
            this.txtBusinessCode = new YssTextBox();
            this.txtBusinessName = new YssTextBox();
        }

        /// <summary>
        /// 加载保存按钮
        /// </summary>
        /// <param name="e">参数</param>
        protected override void OnLoad(EventArgs e)
        {
            this.btnBar.setAllButtonVisibled(false);
            this.btnBar.setButtonVisibled(ClsButtonName.BtnSave);
            this.btnBar.setButtonEnabled(ClsButtonName.BtnSave);
        }

        /// <summary>
        /// 保存按钮点击事件
        /// </summary>
        /// <param name="sender">事件源</param>
        /// <param name="e">事件参数</param>
        protected override void btnSave_Click(object sender, EventArgs e)
        {
            this.btnBar.setButtonDisabled(ClsButtonName.BtnSave);
            MessageDialog dlg = new MessageDialog();
            try
            {
                Dictionary<string, string> paraDict = new Dictionary<string, string>();
                if (this.rtbMain.Table.Rows.Count > 0)
                {
                    HashSet<string> codeSet = new HashSet<string>();
                    for (int i = 0; i < this.rtbMain.Table.Rows.Count; i++)
                    {
                        string vocabularyStr = "";
                        Row row = this.rtbMain.Table.Rows[i];
                        Cell cell = row.Cells[1];
                        if (cell != null)
                        {
                            if (string.IsNullOrEmpty(cell.Tag as string))
                            {
                                throw new Exception("业务类型代码不能为空");
                            }
                            else if (codeSet.Contains(cell.Tag as string))
                            {
                                throw new Exception("业务类型代码不可重复");
                            }
                            else
                            {
                                vocabularyStr = cell.Tag as string;
                                codeSet.Add(vocabularyStr);
                            }
                        }

                        cell = row.Cells[2];
                        if (cell != null)
                        {
                            if (string.IsNullOrEmpty(cell.Tag as string))
                            {
                                throw new Exception("业务类型名称不能为空");
                            }
                            else
                            {
                                vocabularyStr += "#" + cell.Tag as string;
                            }
                        }

                        paraDict.Add(i.ToString(), vocabularyStr);
                    }
                }

                bool ret = portBusinessService.updateDataList(businessType, paraDict);
                if (ret)
                {
                    dlg.Show("保存成功", "提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
                }
                else
                {
                    dlg.Show("保存失败", "提示", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                }
            }
            catch (Exception ex)
            {
                dlg.Show(ex.Message, "错误", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
            finally
            {
                this.btnBar.setButtonEnabled(ClsButtonName.BtnSave);
            }
        }

        /// <summary>
        /// 展示对象属性到窗体
        /// </summary>
        /// <param name="pojo">Pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            List<Vocabulary> businessTypeVoc = portBusinessService.getDataListByType(businessType);
            foreach (Vocabulary voc in businessTypeVoc)
            {
                Row row = new Row();
                Cell mkCell = new Cell();
                row.Cells.Add(mkCell);
                Cell codeCell = new Cell();
                codeCell.Text = voc.C_DV_CODE;
                codeCell.Tag = codeCell.Text;
                row.Cells.Add(codeCell);
                Cell nameCell = new Cell();
                nameCell.Text = voc.C_DV_NAME;
                nameCell.Tag = nameCell.Text;
                row.Cells.Add(nameCell);
                row.Tag = voc;
                this.rtbMain.Table.Rows.Add(row);
            }
        }
    }
}
