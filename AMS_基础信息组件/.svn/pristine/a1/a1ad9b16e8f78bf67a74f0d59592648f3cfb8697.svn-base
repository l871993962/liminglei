using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Windows.Forms;
using FAST.Common.Service.Dict.Pojo;
using FAST.Core.BaseControl;
using FAST.Core.BaseControl.Fun;
using FAST.Core.BaseForm;
using FAST.Core.Communication.Service;
using FAST.Platform.DataIntegration.Imp.Pojos;
using FAST.Platform.DataIntegration.Imp.Service;
using Yss.KMessage;
using Yss.KRichEx;
using Yss.KRichEx.AutoFilter.Events;
using Yss.KRichEx.AutoFilter.Model;
using Yss.KTable.Models;
using YssInformation.Support.Sys.automaticSet.Service;

namespace YssInformation.Sys.automaticSet.Form
{
    public partial class Frm_AUTOMATIC_SET_INTE_TYPE_S : FrmTabItemSet
    {
        /// <summary>
        /// 业务类型服务接口
        /// </summary>
        private IAutomaticSetPathService automaticSetPathService;

        /// <summary>
        /// 产品业务分类
        /// </summary>
        private YssTextBox txtProductCode;

        /// <summary>
        /// 接口代码下拉框
        /// </summary>
        private YssSelCombox selInterfaceCode;

        /// <summary>
        /// 单元格事件
        /// </summary>
        private Yss.KTable.Events.CellEventArgs tempCellEvent;

        /// <summary>
        /// 数据导入接口集合
        /// </summary>
        private List<ImportOperItem> list = new List<ImportOperItem>();

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_AUTOMATIC_SET_INTE_TYPE_S()
        {
            InitializeComponent();
            this.rtbMain.Table.AllowResizeColumn = true;
            this.rtbMain.Table.Border.Top = false;
            this.rtbMain.Table.Border.Bottom = false;
            this.rtbMain.Table.Border.Left = false;
            this.rtbMain.Table.DefaultToolStripItems = Yss.KTable.Enums.SysToolStripItems.None;
            this.rtbMain.Table.SelectionMode = SelectionMode.One;
            this.rtbMain.Table.CellMouseClick += this.rtbMain_ClieCellChanged;
            this.rtbMain.Table.SelectedCellChanged += this.rtbMain_SelectedCellChanged;
            this.bUseMVCService = true;
            InitializeColumns();
            initServiceMVC();
            initTextBox();
        }

        /// <summary>
        /// 列表下拉框
        /// </summary>
        private YssSelCombox ProcessListDropDown
        {
            get
            {
                this.selInterfaceCode = new YssSelCombox();
                this.selInterfaceCode.AutoTooltip = true;
                this.selInterfaceCode.BeforeDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(selInterfaceCode_BeforeDropDownClick);
                this.selInterfaceCode.SelectedValueChanged += new EventHandler(processListDropdown_SelectedValueChanged);
                this.selInterfaceCode.ShowCheckBox = true;
                this.selInterfaceCode.KTableTree = true;
                this.selInterfaceCode.DefaultValue = "";
                this.selInterfaceCode.DisplayName = "C_Cfg_Name";
                this.selInterfaceCode.DisplayValue = "C_Cfg_Code";
                this.selInterfaceCode.MethodInfo = null;
                this.selInterfaceCode.Name = "selInterfaceCode";
                this.selInterfaceCode.Parameter = "C_Custom_Property~C_Cfg_Name";
                this.selInterfaceCode.NodeID = "C_Cfg_Code";
                this.selInterfaceCode.ParaNodeID = "C_Group_Code";
                this.selInterfaceCode.SortColumn = "C_Group_Name";
                this.selInterfaceCode.PrefixBackColor = System.Drawing.Color.White;
                this.selInterfaceCode.TabIndex = 26;
                this.selInterfaceCode.Visible = true;
                this.selInterfaceCode.YssAssociaType = ((FAST.Core.Context.Associa)(FAST.Core.Context.AssociaFAST.NULL));
                this.selInterfaceCode.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;

                return this.selInterfaceCode;
            }
        }



        /// <summary>
        /// 点击改变事件
        /// </summary>
        /// <param name="sender">事件源</param>
        /// <param name="e">事件参数</param>
        private void rtbMain_SelectedCellChanged(object sender, Yss.KTable.Events.CellEventArgs e)
        {
            if (e.Cell.RelColumn.DataPropertyName != "txtProductCode" && e.Cell.RelColumn.DataPropertyName != "txtInterfaceCode")
            {
                return;
            }

            if (e.Cell.RelColumn.DataPropertyName == "txtProductCode")
            {
                if (!e.Cell.Selected)
                {
                    e.Cell.InnerControl = null;
                    e.Cell.Text = this.txtProductCode.Text;
                    e.Cell.Tag = e.Cell.Text;
                }
                else
                {
                    this.txtProductCode.Text = e.Cell.Text;
                    e.Cell.InnerControl = this.txtProductCode;
                }
            }
        }


        /// <summary>
        /// 点击单元格改变事件
        /// </summary>
        /// <param name="sender">事件源</param>
        /// <param name="e">事件参数</param>
        private void rtbMain_ClieCellChanged(object sender, Yss.KTable.Events.CellEventArgs e)
        {
            if (e.Cell.RelColumn.DataPropertyName != "txtProductCode" && e.Cell.RelColumn.DataPropertyName != "txtInterfaceCode")
            {
                return;
            }
            if (e.Cell.RelColumn.DataPropertyName == "txtInterfaceCode")
            {
                tempCellEvent = e;
                if (e.Cell.Selected)
                {
                    //先记录下单元格文本，避免给InnerControl赋值时，获取不到真实的文本值。
                    string lcCellText = e.Cell.Text;
                    List<ImportOperItem> list = e.Cell.Tag as List<ImportOperItem>;
                    ////选中，把控件放进去
                    e.Cell.InnerControl = this.ProcessListDropDown;
                    if (e.Cell.InnerControl != null)
                    {
                        ////初始化控件文本
                        (e.Cell.InnerControl as YssSelCombox).Text = lcCellText;
                        string cellValue = "";
                        if (list != null && list.Count > 0)
                        {
                            foreach (ImportOperItem item in list)
                            {
                                if ("".Equals(cellValue))
                                {
                                    cellValue = item.C_Cfg_Code + "#" + item.C_Group_Code;
                                }
                                else
                                {
                                    cellValue += "|" + item.C_Cfg_Code + "#" + item.C_Group_Code;
                                }
                            }
                        }
                        (e.Cell.InnerControl as YssSelCombox).Value = cellValue;
                    }
                }
            }
        }

        /// <summary>
        /// 下拉框选择改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void processListDropdown_SelectedValueChanged(object sender, EventArgs e)
        {
            tempCellEvent.Row.Cells[tempCellEvent.Cell.ColumnIndex].Tag = (sender as YssSelCombox).CheckedItems;
        }

        /// <summary>
        /// 接口代码下拉框取值
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void selInterfaceCode_BeforeDropDownClick(object sender, DropDownEventArgs e)
        {
            e.Cancel = true;
            if (this.selInterfaceCode.Items.Count > 0)
            {
                return;
            }
            IImportDataService importDataService = ServiceFactory.createService<IImportDataService>();
            list = fliterOutEmptyImpGroup(importDataService.getAllEnableCfgList(""));

            if (list != null && list.Count > 0)
            {
                foreach (ImportOperItem item in list)
                {
                    if ("[root]".Equals(item.C_Cfg_Code))
                    {
                        item.C_Cfg_Code = item.C_Group_Code;
                        item.C_Cfg_Name = item.C_Group_Name;
                    }

                    item.C_Custom_Property = item.C_Cfg_Code;
                    item.C_Cfg_Code = item.C_Cfg_Code + "#" + item.C_Group_Code;
                    item.C_Group_Code = item.C_Group_Code + "#" + item.C_Group_Code_P;
                    KTableEntity entity = new KTableEntity(item);
                    this.selInterfaceCode.Items.Add(entity);
                }

                foreach (ControlEntity entity in this.selInterfaceCode.Items)
                {
                    ImportOperItem item = (ImportOperItem)entity.DataEntity;
                    if (item.C_Cfg_Code.Contains("#"))
                    {
                        item.C_Cfg_Code = item.C_Cfg_Code.Split('#')[0];
                    }

                    if (item.C_Group_Code.Contains("#"))
                    {
                        item.C_Group_Code = item.C_Group_Code.Split('#')[0];
                    }
                }
            }
        }


        /// <summary>
        /// 过滤结果集
        /// </summary>
        /// <param name="list">list</param>
        /// <returns>List</returns>
        private List<ImportOperItem> fliterOutEmptyImpGroup(List<ImportOperItem> list)
        {
            List<ImportOperItem> result = new List<ImportOperItem>();
            result = list.Where(p => p.C_Cfg_Code != "[root]").ToList();

            List<ImportOperItem> appendResults = new List<ImportOperItem>();

            while (true)
            {
                appendResults = list.Where(p => p.C_Cfg_Code == "[root]" &&
                                                result.Where(k => k.C_Group_Code_P == p.C_Group_Code).Count() > 0 &&
                                                result.Where(k => k.C_Cfg_Code == "[root]" && k.C_Group_Code == p.C_Group_Code).Count() == 0).ToList();

                if (appendResults.Count == 0)
                {
                    return result.ToList();
                }

                result.AddRange(appendResults);
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
            newColumn.DataPropertyName = "txtProductCode";
            newColumn.Text = "产品业务分类";
            newColumn.Width = 200;
            this.rtbMain.Table.Columns.Add(newColumn);

            newColumn = new Column();
            newColumn.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Center;
            newColumn.CellTextAlign = ContentAlignment.MiddleCenter;
            newColumn.DataPropertyName = "txtInterfaceCode";
            newColumn.Text = "接口代码";
            newColumn.Width = 338;
            this.rtbMain.Table.Columns.Add(newColumn);
        }

        /// <summary>
        /// 控件初始化
        /// </summary>
        private void initTextBox()
        {
            ////TextBox控件
            this.txtProductCode = new YssTextBox();
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
        /// 初始化服务
        /// </summary>
        protected override void initServiceMVC()
        {
            if (automaticSetPathService == null)
            {
                automaticSetPathService = ServiceFactory.createService<IAutomaticSetPathService>();
            }
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
                List<Dictionary<string, string>> paraList = new List<Dictionary<string, string>>();
                if (this.rtbMain.Table.Rows.Count > 0)
                {
                    for (int i = 0; i < this.rtbMain.Table.Rows.Count; i++)
                    {
                        Dictionary<string, string> paraDict = new Dictionary<string, string>();
                        Row row = this.rtbMain.Table.Rows[i];
                        Cell cell = row.Cells[1];
                        if (cell != null)
                        {
                            if (string.IsNullOrEmpty(cell.Tag as string))
                            {
                                throw new Exception("产品业务分类不能为空");
                            }
                            else
                            {
                                string productName = cell.Tag as string;
                                paraDict.Add("productName", productName);
                            }
                        }

                        cell = row.Cells[2];
                        if (cell != null)
                        {
                            string interfacePid = "";
                            string interfaceCode = "";
                            string interfaceName = "";
                            if (string.IsNullOrEmpty(cell.Text as string))
                            {
                                throw new Exception("接口代码不能为空");
                            }
                            else if (!string.IsNullOrEmpty(cell.Value as string))
                            {
                                List<ControlEntity> list = cell.Tag as List<ControlEntity>;
                                foreach (ControlEntity controlEntity in list)
                                {
                                    string productName = paraDict["productName"];
                                    ImportOperItem importOperItem = (ImportOperItem) controlEntity.DataEntity;
                                    interfacePid = importOperItem.C_Group_Code;
                                    interfaceCode = importOperItem.C_Cfg_Code;
                                    interfaceName = importOperItem.C_Cfg_Name;
                                    
                                    ///父级id、接口代码、接口名称
                                    paraDict = new Dictionary<string, string>();
                                    paraDict.Add("productName", productName);
                                    paraDict.Add("interfacePid", interfacePid);
                                    paraDict.Add("interfaceCode", interfaceCode);
                                    paraDict.Add("interfaceName", interfaceName);
                                    paraList.Add(paraDict);
                                }
                            }
                            else
                            {
                                List<ImportOperItem> list = cell.Tag as List<ImportOperItem>;
                                foreach (ImportOperItem importOperItem in list)
                                {
                                    string productName = paraDict["productName"];
                                    interfacePid = importOperItem.C_Group_Code;
                                    interfaceCode = importOperItem.C_Cfg_Code;
                                    interfaceName = importOperItem.C_Cfg_Name;

                                    ///父级id、接口代码、接口名称
                                    paraDict = new Dictionary<string, string>();
                                    paraDict.Add("productName", productName);
                                    paraDict.Add("interfacePid", interfacePid);
                                    paraDict.Add("interfaceCode", interfaceCode);
                                    paraDict.Add("interfaceName", interfaceName);
                                    paraList.Add(paraDict);
                                }
                            }
                        }
                    }
                }

                bool ret = automaticSetPathService.updateDataList(paraList);
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
            this.rtbMain.Table.Rows.Clear();
            Dictionary<string, List<ImportOperItem>> rowTagDict = new Dictionary<string, List<ImportOperItem>>();
            Dictionary<string, string> rowCodeDict = new Dictionary<string, string>();
            Dictionary<string, string> rowNameDict = new Dictionary<string, string>();
            List<Vocabulary> InterfaceClassVoc = automaticSetPathService.getInterfaceClass();
            foreach (Vocabulary voc in InterfaceClassVoc)
            {
                string productName = voc.C_DV_CODE;
                string interfaceCode = voc.N_ORDER;
                string interfaceName = voc.C_DV_NAME;
                if (!rowTagDict.ContainsKey(productName))
                {
                    rowTagDict.Add(productName, new List<ImportOperItem>());
                }

                if (!rowCodeDict.ContainsKey(productName))
                {
                    rowCodeDict.Add(productName, interfaceCode);
                }
                else
                {
                    rowCodeDict[productName] += "|" + interfaceCode;
                }

                if (!rowNameDict.ContainsKey(productName))
                {
                    rowNameDict.Add(productName, interfaceName);
                }
                else
                {
                    rowNameDict[productName] += "|" + interfaceName;
                }

                ImportOperItem importOperItem = new ImportOperItem();
                importOperItem.C_Cfg_Code = interfaceCode;
                importOperItem.C_Cfg_Name = interfaceName;
                importOperItem.C_Group_Code = voc.C_DESC;
                rowTagDict[productName].Add(importOperItem);
            }

            foreach (string key in rowNameDict.Keys)
            {
                Row row = new Row();
                row.Cells.Add(new Cell());
                Cell codeCell = new Cell();
                codeCell.Text = key;
                codeCell.Tag = codeCell.Text;
                row.Cells.Add(codeCell);
                Cell nameCell = new Cell();
                nameCell.Text = rowNameDict[key];
                nameCell.Tag = rowTagDict[key];
                nameCell.Key = rowCodeDict[key];
                row.Cells.Add(nameCell);
                this.rtbMain.Table.Rows.Add(row);
            }
        }
    }
}
