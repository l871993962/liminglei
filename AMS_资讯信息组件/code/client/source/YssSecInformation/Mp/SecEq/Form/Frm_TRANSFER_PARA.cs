using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.BaseForm;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Pojo;
////using YssPara.Service.Bi;
using FAST.Core.BaseControl;
using Yss.KTable.Models;
using FAST.Core.Context;
using FAST.Core.Util;
////using YssPara.Pojo.Bi;
using System.Collections;
using FAST.Core.Communication.Service;
using FAST.Common.Service.DataService;
using FAST.Core.Communication.DataService;
using FAST.Core.Exceptions;
using YssSecInformation.Support.Mp.SecEq.Service;
using YssSecInformation.Support.Mp.SecEq.Pojo;

namespace YssSecInformation.Mp.SecEq.Form
{
    /// <summary>
    /// chenbo
    /// 2017-06-24
    /// #42948 资讯信息管理组件化拆分
    /// -------------------------------
    /// 证券代码转换选项界面
    /// </summary>
    public partial class Frm_TRANSFER_PARA : FrmBase
    {
        /// <summary>
        /// 定义功能菜单对象
        /// </summary>
        public IBaseFun _fun;

        /// <summary>
        /// 功能选项服务
        /// </summary>
        private ISecTransferParaService condService = null;

        /// <summary>
        /// Color对应的QueryRes
        /// </summary>
        private QueryRes queryRes = null;

        /// <summary>
        /// 当前单元格
        /// </summary>
        private Cell currentCell = null;

        /// <summary>
        /// 应用条件下拉框
        /// </summary>
        private YssSelCombox _cboBoxA = null;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_TRANSFER_PARA()
        {
            InitializeComponent();
        }

        /// <summary>
        /// 应用条件下拉框
        /// </summary>
        private YssSelCombox cboBoxA
        {
            get
            {
                if (this._cboBoxA == null || this._cboBoxA.IsDisposed)
                {
                    this._cboBoxA = new YssSelCombox();
                    _cboBoxA.DisplayName = "C_DV_NAME";
                    _cboBoxA.DisplayValue = "C_DV_CODE";
                    _cboBoxA.MethodInfo = new ControlMethodInfo();
                    _cboBoxA.MethodInfo.MethodName = "getDataListByTypes";
                    _cboBoxA.Parameter = "C_DV_CODE~C_DV_NAME";
                    _cboBoxA.MethodInfo.MethodParams = null;
                    _cboBoxA.MethodInfo.MethodParamValues = new string[] { "SBLSHGZ," };
                    _cboBoxA.QueryByValues = false;
                    _cboBoxA.ShowCheckBox = false;
                    _cboBoxA.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
                    _cboBoxA.YssCaption = "应用条件";
                    _cboBoxA.ShowCheckBox = true;
                }

                _cboBoxA.YssReadOnly = (status == ClsEnums.StatusSetting.YssBrow);
                return this._cboBoxA;
            }
        }

        /// <summary>
        /// 取消按钮点击事件，
        /// 当点击取消时，该界面关闭
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnOk_Click(object sender, EventArgs e)
        {
            ////进行更新操作
            try
            {
                initCurrentCell(currentCell);
                string operResult = condService.updateConds(yssGetObjListMVC());
                ////operAfterSave(operResult);
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtInfo("004", this._fun, status));
            }
            finally
            {
                //// 设置后关闭窗体
                this.Close();
            }

        }

        /// <summary>
        /// 确定按钮点击事件
        /// 当用户操作完成时，点击该按钮，进行确认，并关闭界面
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnQuit_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.OK;
        }

        /// <summary>
        /// 封装窗体数据
        /// </summary>
        /// <returns>ArrayList</returns>
        public List<BasePojo> yssGetObjListMVC()
        {
            List<BasePojo> dataList = new List<BasePojo>();
            SecTransferPara secPara = null;
            foreach (Row row in this.tbMain.Rows)
            {
                secPara = (SecTransferPara)row.Tag;
                foreach (Cell cell in row.Cells)
                {
                    if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE"))
                    {
                        ////secPara.C_ITEM_VALUE = cell.Text;

                        ////若下拉框控件未null时，直接将cell值保存，若下拉框控件启用，则用控件值
                        if (cboBoxA.Value == null)
                        {
                            secPara.C_ITEM_VALUE = cell.Text;

                            if (cell.Text.Equals("交易证券"))
                            {
                                secPara.C_ITEM_VALUE = "SBLSHGZ_SEC";
                            }
                            else if (cell.Text.Equals("交易渠道"))
                            {
                                secPara.C_ITEM_VALUE = "SBLSHGZ_SEAT";
                            }
                            else if (cell.Text.Equals("交易证券|交易渠道"))
                            {
                                secPara.C_ITEM_VALUE = "SBLSHGZ_SEC|SBLSHGZ_SEAT";
                            }
                            else if (cell.Text.Equals("交易渠道|交易证券"))
                            {
                                secPara.C_ITEM_VALUE = "SBLSHGZ_SEAT|SBLSHGZ_SEC";
                            }
                            else if (cell.Text.Equals(""))
                            {
                                secPara.C_ITEM_VALUE = "";
                            }
                        }
                        else
                        {
                            secPara.C_ITEM_VALUE = cboBoxA.Value;
                        }

                        cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                    }

                    if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_NAME"))
                    {
                        ////cond.C_ITEM_VALUE = null == cell.Tag ? cell.Text : cell.Tag.ToString();
                        if (cell.Text.Equals("社保理事会债券代码转换规则"))
                        {
                            secPara.C_ITEM_CODE = "SBLSHGZ";
                            secPara.C_ITEM_NAME = "社保理事会债券代码转换规则";
                        }

                        cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                    }

                    if (cell.RelColumn.DataPropertyName.Equals("C_LOGICAL_JUDGMENT"))
                    {
                        if (cell.Text.Equals("不启用"))
                        {
                            secPara.C_LOGICAL_JUDGMENT = "DisEnabled";
                        }
                        else if (cell.Text.Equals("启用"))
                        {
                            secPara.C_LOGICAL_JUDGMENT = "Enabled";
                        }
                        else
                        {
                            secPara.C_LOGICAL_JUDGMENT = cell.Text;
                        }

                        cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                    }
                }

                dataList.Add(secPara);
            }

            return dataList;
        }

        /// <summary>
        /// 初始化信息
        /// </summary>
        public void initColumnHeader()
        {
            Dictionary<string, string> paraDict = new Dictionary<string, string>();

            ////// 获取配置规则数据实体
            paraDict.Add("dataClass", "SecTransferPara");

            queryRes = condService.queryByCondition(paraDict);

            ArrayList showSpecColumns = new ArrayList();   
            showSpecColumns.Add("Id");
            showSpecColumns.Add("C_LOGICAL_JUDGMENT");
            showSpecColumns.Add("C_ITEM_NAME");
            showSpecColumns.Add("C_ITEM_VALUE");

            TableListLoader tableLoadList = new TableListLoader();
            tableLoadList.loadTable(this.tbMain, queryRes, false, false, ClsEnums.KTableDataShowMode.ListMode, showSpecColumns);
            int pos = 0;

            this.tbMain.Columns[0].Width = 40;
            this.tbMain.Columns[1].Width = 80;
            this.tbMain.Columns[2].Width = 240;
            this.tbMain.Columns[3].Width = 220;
            this.tbMain.Columns[0].CellTextAlign = ContentAlignment.MiddleCenter;
            this.tbMain.Columns[1].CellTextAlign = ContentAlignment.MiddleCenter;
            this.tbMain.Columns[2].CellTextAlign = ContentAlignment.MiddleCenter;
            this.tbMain.Columns[3].CellTextAlign = ContentAlignment.MiddleCenter;

            this.tbMain.Refresh();
        }

        /// <summary>
        /// 窗体加载方法
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_TRANSFER_PARA_Load(object sender, EventArgs e)
        {
            condService = ServiceFactory.createService<ISecTransferParaService>();
            initColumnHeader();
        }

        /// <summary>
        /// 双击事件
        /// 增加过滤新股信息
        /// 由于选项界面加载的数据是根据数据库控制的，这里的((SecTransferPara)cell.OwnRow.Tag).Id直接取得是数据库的ID，所以在后续开发过程中，注意对数据库中ID值的控制
        /// </summary>
        /// <param name="sender">s</param>
        /// <param name="e">e</param>
        private void tbMain_CellMouseDoubleClick(object sender, Yss.KTable.Events.CellEventArgs e)
        {
            if (sender != null && sender is Cell)
            {
                Cell cell = sender as Cell;

                ////////选中应用条件时，给空间赋值
                ////if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE"))
                ////{
                ////    YssSelCombox cboValue = new YssSelCombox();
                ////    cboValue.ShowCheckBox = true;
                ////    cboValue.YssAssociaType = ClsEnums.AssociaType.pubvocabulary;
                ////    cboValue.QueryType = ClsConstant.CacheType;
                ////    cboValue.QueryCond = "SBLSHGZ";
                ////    cboValue.DisplayName = "C_DV_CODE";
                ////    cboValue.DisplayValue = "C_DV_NAME";
                ////    cboValue.Parameter = "C_DV_CODE";
                ////    ////cboValue.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
                ////    cboValue.YssIsMust = false;
                ////    cboValue.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconIndent;

                ////    YssControls.ControlMethodInfo controlmethod = new ControlMethodInfo();
                ////    controlmethod.MethodName = "getDataListByTypes";
                ////    controlmethod.MethodParamValues = new string[] { "SBLSHGZ," };
                ////    cboValue.MethodInfo = controlmethod;

                ////    ////if (cboValue.Items.Count == 0)
                ////    ////{
                ////    ////    IVocDataService vocSvc = DataServiceFactory.createService<IVocDataService>();
                ////    ////    List<BasePojo> pojoList = vocSvc.getDataListByTypes(new string[] { "SBLSHGZ" });
                ////    ////    if (pojoList.Count > 0)
                ////    ////    {
                ////    ////        foreach (BasePojo pojo in pojoList)
                ////    ////        {
                ////    ////            ControlEntity entity = new KTableEntity(pojo);
                ////    ////            cboValue.Items.Add(entity);
                ////    ////        }
                ////    ////    }

                ////    ////}

                ////    cell.InnerControl = cboValue;
                ////    ////给当前单元格变量赋值
                ////    currentCell = cell;

                ////    if (cell.Text.Equals("交易证券"))
                ////    {
                ////        cboValue.Value = "SBLSHGZ_SEC";
                ////    }
                ////    else if (cell.Text.Equals("交易渠道"))
                ////    {
                ////        cboValue.Value = "SBLSHGZ_SEAT";
                ////    }
                ////    else if (cell.Text.Equals("交易证券|交易渠道"))
                ////    {
                ////        cboValue.Value = "SBLSHGZ_SEC|SBLSHGZ_SEAT";
                ////    }
                ////    else if (cell.Text.Equals("交易渠道|交易证券"))
                ////    {
                ////        cboValue.Value = "SBLSHGZ_SEAT|SBLSHGZ_SEC";
                ////    }
                ////    else if (cell.Text.Equals(""))
                ////    {
                ////        cboValue.Value = "";
                ////    }
                ////}

                //// 选中逻辑判断时，给控件赋值
                if (cell.RelColumn.DataPropertyName.Equals("C_LOGICAL_JUDGMENT"))
                {
                    YssSelCombox cboValue = new YssSelCombox();
                    cboValue.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
                    cboValue.QueryType = ClsConstant.CacheType;
                    cboValue.QueryCond = "TRANSFER_STATE";
                    cboValue.DisplayName = "C_DV_NAME";
                    cboValue.DisplayValue = "C_DV_CODE";
                    cboValue.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
                    cboValue.YssIsMust = false;

                    FAST.Core.BaseControl.ControlMethodInfo controlmethod = new ControlMethodInfo();
                    controlmethod.MethodName = "getDataListByTypes";
                    controlmethod.MethodParamValues = new string[] { "TRANSFER_STATE," };
                    cboValue.MethodInfo = controlmethod;

                    cell.InnerControl = cboValue;
                    ////给当前单元格变量赋值
                    currentCell = cell;

                    if (cell.Text.Equals("不启用"))
                    {
                        cboValue.Value = "DisEnabled";
                    }
                    else if (cell.Text.Equals("启用"))
                    {
                        cboValue.Value = "Enabled";
                    }
                }

                //// 选中规则名称时，给控件赋值
                if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_NAME") && ((SecTransferPara)cell.OwnRow.Tag).C_ITEM_CODE.Equals("SBLSHGZ"))
                {
                    YssSelCombox cboValue = new YssSelCombox();
                    cboValue.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
                    cboValue.QueryType = ClsConstant.CacheType;
                    cboValue.QueryCond = "SBLSHGZ";
                    cboValue.DisplayName = "C_DV_NAME";
                    cboValue.DisplayValue = "C_DV_CODE";
                    cboValue.YssIsMust = false;

                    FAST.Core.BaseControl.ControlMethodInfo controlmethod = new ControlMethodInfo();
                    controlmethod.MethodName = "getDataListByTypes";
                    controlmethod.MethodParamValues = new string[] { "TRANSFER_RULE," };
                    cboValue.MethodInfo = controlmethod;

                    cell.InnerControl = cboValue;
                    ////给当前单元格变量赋值
                    currentCell = cell;

                    if (cell.Text.Equals("社保理事会债券代码转换规则"))
                    {
                        cboValue.Value = "SBLSHGZ";
                    }

                }
            }
        }

        /// <summary>
        /// 规则设置列表界面初始化
        /// </summary>
        /// <param name="cell">cell</param>
        private void initCurrentCell(Cell cell)
        {
            try
            {
                if (null == cell)
                {
                    return;
                }

                if (null == cell.InnerControl)
                {
                    return;
                }

                if (null == cell.RelColumn)
                {
                    return;
                }

                if (null == cell.OwnRow)
                {
                    return;
                }

                Column currentCol = cell.RelColumn;

                if (currentCol.DataPropertyName.Equals("C_ITEM_VALUE"))
                {
                    YssSelCombox itb = cell.InnerControl as YssSelCombox;
                    cell.Text = itb.Text;
                    cell.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
                    itb.Dock = DockStyle.None;
                    itb.Visible = true;
                    itb.Enabled = true;
                    cell.CellEditStatus = true;
                    cell.Selected = true;
                }
                ////else if (currentCol.DataPropertyName.Equals("C_ITEM_VALUE"))
                ////{
                ////    YssSelCombox comBox = cell.InnerControl as YssSelCombox;
                ////    IVocDataService vocSvc = DataServiceFactory.createService<IVocDataService>();
                ////    cell.Text = vocSvc.getKeyConvertMap()[comBox.Value];
                ////    cell.Tag = comBox.Value;
                ////}

                if (currentCol.DataPropertyName.Equals("C_ITEM_NAME"))
                {
                    YssSelCombox comBox = cell.InnerControl as YssSelCombox;
                    cell.Text = comBox.Text;
                    cell.Tag = comBox.Value;
                }

                if (currentCol.DataPropertyName.Equals("C_LOGICAL_JUDGMENT"))
                {
                    YssSelCombox comBox = cell.InnerControl as YssSelCombox;
                    IVocDataService vocSvc = DataServiceFactory.createService<IVocDataService>();
                    cell.Text = vocSvc.getKeyConvertMap()[comBox.Value];
                    cell.Tag = comBox.Value;
                }

                this.tbMain.AutoColumnWidth = true;
                ////cell.InnerControl.Dispose();
                ////cell.InnerControl = null;
                this.tbMain.Refresh();

            }
            catch (Exception ex)
            {
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 隐藏控件
        /// </summary>
        private void hideSelCombox()
        {
            foreach (Row row in tbMain.Rows)
            {
                if (row.Cells.Count > 1 && row.Cells[1].InnerControl != null)
                {
                    if (row.Cells[1].InnerControl is FAST.Core.BaseControl.YssSelCombox)
                    {
                        FAST.Core.BaseControl.YssSelCombox com = row.Cells[1].InnerControl as FAST.Core.BaseControl.YssSelCombox;
                        com.Visible = false;
                        row.Cells[1].InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                        if (com.SelectedItem != null)
                        {
                            row.Cells[1].Text = com.SelectedItem.DisplayName;
                        }
                    }
                }

                if (row.Cells.Count > 1 && row.Cells[2].InnerControl != null)
                {
                    if (row.Cells[2].InnerControl is FAST.Core.BaseControl.YssSelCombox)
                    {
                        FAST.Core.BaseControl.YssSelCombox com = row.Cells[2].InnerControl as FAST.Core.BaseControl.YssSelCombox;
                        com.Visible = false;
                        row.Cells[2].InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                        if (com.SelectedItem != null)
                        {
                            row.Cells[2].Text = com.SelectedItem.DisplayName;
                        }
                    }
                }

                if (row.Cells.Count > 2 && row.Cells[3].InnerControl != null)
                {
                    if (row.Cells[3].InnerControl is FAST.Core.BaseControl.YssSelCombox)
                    {
                        FAST.Core.BaseControl.YssSelCombox com = row.Cells[3].InnerControl as FAST.Core.BaseControl.YssSelCombox;
                        com.Visible = false;
                        row.Cells[3].InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                        if (com.SelectedItem != null)
                        {
                            row.Cells[3].Text = com.SelectedItem.DisplayName;
                        }
                    }
                    else if (row.Cells[3].InnerControl is Yss.KRichEx.YssTextBox)
                    {
                        Yss.KRichEx.YssTextBox box = row.Cells[3].InnerControl as Yss.KRichEx.YssTextBox;
                        box.Visible = false;
                        row.Cells[3].Text = box.Text;
                        row.Cells[3].InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                    }
                    else if (row.Cells[3].InnerControl is Yss.KRichEx.ImprovedTextBox)
                    {
                        Yss.KRichEx.ImprovedTextBox box = row.Cells[3].InnerControl as Yss.KRichEx.ImprovedTextBox;
                        box.Visible = false;
                        row.Cells[3].Text = box.Text;
                        row.Cells[3].InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                    }
                }

            }
        }

        /// <summary>
        /// 单击事件
        /// </summary>
        /// <param name="sender">s</param>
        /// <param name="e">e</param>
        private void tbMain_CellMouseClick(object sender, Yss.KTable.Events.CellEventArgs e)
        {
            if (sender != null && sender is Cell)
            {
                Cell cell = sender as Cell;
                hideSelCombox();

                if (cell.RelColumn.DataPropertyName.Equals("C_LOGICAL_JUDGMENT"))
                {
                    this.tbMain_CellMouseDoubleClick(sender, null);
                    cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowAlways;
                    tbMain.Refresh();
                }

                if (cell.RelColumn.DataPropertyName.Equals("C_VALUE_TYPE"))
                {
                    this.tbMain_CellMouseDoubleClick(sender, null);
                    cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowAlways;
                    tbMain.Refresh();
                }

                if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE"))
                {
                    e.Cell.InnerControl = this.cboBoxA;
                    if (e.Cell.InnerControl != null)
                    {
                        cboBoxA.Text = e.Cell.Text;
                        ////cboBoxA.Value = e.Cell.Key;
                        if (e.Cell.Text.Equals("交易证券"))
                        {
                            cboBoxA.Value = "SBLSHGZ_SEC";
                        }
                        else if (e.Cell.Text.Equals("交易渠道"))
                        {
                            cboBoxA.Value = "SBLSHGZ_SEAT";
                        }
                        else if (e.Cell.Text.Equals("交易证券|交易渠道"))
                        {
                            cboBoxA.Value = "SBLSHGZ_SEC|SBLSHGZ_SEAT";
                        }
                        else if (e.Cell.Text.Equals("交易渠道|交易证券"))
                        {
                            cboBoxA.Value = "SBLSHGZ_SEAT|SBLSHGZ_SEC";
                        }
                        else if (e.Cell.Text.Equals(""))
                        {
                            cboBoxA.Value = "";
                        }
                    }
                    ////this.tbMain_CellMouseDoubleClick(sender, null);
                    cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowAlways;
                }

                if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_NAME"))
                {
                    this.tbMain_CellMouseDoubleClick(sender, null);
                    cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowAlways;
                }
            }
        }

    }
}
