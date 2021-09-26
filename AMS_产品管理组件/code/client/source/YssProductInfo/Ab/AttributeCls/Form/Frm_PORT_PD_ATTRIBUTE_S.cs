using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;

using FAST.Core.Exceptions;

using FAST.Core.Context;

using FAST.Core.Resource;


using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;

using FAST.Common.Service.Pojo.Base;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.BaseControl;
using System.Collections;
using Yss.KTable.Models;
using FAST.Common.Service.Services;
using YssProductInfo.Support.Ab.AttributeCls.Service;


namespace YssProductInfo.Ab.AttributeCls.Form
{
    /// <summary>
    /// 描述：组合产品属性Set界面
    /// 创建人：zhengguiyu
    /// 创建时间：20140322
    /// </summary>
    public partial class Frm_PORT_PD_ATTRIBUTE_S : FrmBaseSet
    {
        /// <summary>
        /// 构造方法
        /// </summary>        
        public Frm_PORT_PD_ATTRIBUTE_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
            this.tbMain.ReadOnly = false;
            ////this.panelExLine.Visible = false;

            this.dataService = ServiceFactory.createService<YssProductInfo.Support.Ab.AttributeCls.Service.IPortPdAttributeService>();
            colAssetsType.CellDictionaries = this.getAssTypeDict();
            ////将所有的词汇都加载到投资对象的字典中
            colInvestCode.CellDictionaries = this.getVocabularyDict();
            colMxAssetsType.CellDictionaries = this.getVocabularyDict();

            this.cboAssetsCode.SelectedValueChanged += this.ComboBox_SelectedValueChanged;
            this.cboCollectCode.SelectedValueChanged += this.ComboBox_SelectedValueChanged;
            this.cboInvestCode.SelectedValueChanged += this.ComboBox_SelectedValueChanged;
            this.cboOperType.SelectedValueChanged += this.ComboBox_SelectedValueChanged;
            this.cboPortType.SelectedValueChanged += this.ComboBox_SelectedValueChanged;
            this.cboMxAssetsType.SelectedValueChanged += this.ComboBox_SelectedValueChanged;
            this.cboSettMode.SelectedValueChanged += this.ComboBox_SelectedValueChanged;

            //// BUG #115507 A区投资组合选中背景色记录，可带到Set界面，并没有在所有的界面进行实现 liyongjun
           //// setPortSelCombox(this.cmbGroups);
        }

        /// <summary>
        /// 更改控件的状态
        /// </summary>
        protected override void YssChangeControlState()
        {
            base.YssChangeControlState();

            if (status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssCopy)
            {
                if (status == ClsEnums.StatusSetting.YssAdd)
                {
                    cmbGroups.Text = "";
                }

                row1.Visible = true;
                row3.Visible = true;
                tbTop.Height = 73;
            }
            else
            {
                row3.Visible = false;
                row1.Visible = true;
                tbTop.Height = 40;
            }

            clsInterface.setControlsStatus(tbTop, status);
            tbTop.Refresh();
            tbMain.Refresh();
        }

        /// <summary>
        /// 初始化控件属性
        /// </summary>
        public override void yssInitCtlAttr()
        {
            base.yssInitCtlAttr();

            ////  BUG #115507 A区投资组合选中背景色记录，可带到Set界面，并没有在所有的界面进行实现 liyongjun
            ////YssControls.ControlMethodInfo controlMethodInfo = new FAST.Core.BaseControl.ControlMethodInfo();
            ////controlMethodInfo.MethodName = "getFilterPortDataForOperRight";
            ////this.cmbGroups.MethodInfo = controlMethodInfo;
        }

        /// <summary>
        /// 投资组合值改变事件,加载set界面tbmain的行数据
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cmbGroups_SelectedValueChanged(object sender, EventArgs e)
        {
            ////BUG #115507 A区投资组合选中背景色记录，可带到Set界面，并没有在所有的界面进行实现  liyongjun
            List<FAST.Common.Service.Pojo.Port> listPort = null;
            Dictionary<string, string> assetCodeDict = null;
            List<string> portCodeList = null;
            List<string> assCodeList = null;

            try
            {
                listPort = new List<FAST.Common.Service.Pojo.Port>();
                assetCodeDict = new Dictionary<string, string>();
                portCodeList = new List<string>();
                assCodeList = new List<string>();

                ////BUG #131480 无法访问已释放资源，时有时无的出现。
                ////清理行之前，先移走所有的InnerControl，以防InnerControl被同步销毁。张绍林-20160525
                this.tbMain.RemoveCellsControl();
                this.tbMain.Rows.Clear(true);
                this.tbMain.GridLine = Yss.KTable.Enums.GridLines.Both;
                this.tbMain.GridLineColor = System.Drawing.Color.Gray;
                List<Yss.KRichEx.AutoFilter.Model.ControlEntity> list = this.cmbGroups.CheckedItems;
                if (null == list || 0 == list.Count)
                {
                    return;
                }

                foreach (Yss.KRichEx.AutoFilter.Model.ControlEntity entity in list)
                {
                    FAST.Common.Service.Pojo.Port port = entity.DataEntity as FAST.Common.Service.Pojo.Port;
                    listPort.Add(port);
                    portCodeList.Add(port.C_PORT_CODE);
                }

                assetCodeDict = this.getAssCodeByPortCode(portCodeList);

                foreach (FAST.Common.Service.Pojo.Port port in listPort)
                {
                    //// 修改：新增时，勾选组合带有根节点时不保存。
                    if (port.C_PORT_CODE_P == "[root]")
                    {
                        continue;
                    }

                    Row tempRow = new Row();
                    tempRow.Height = 22;
                    this.tbMain.Rows.Add(tempRow);

                    foreach (Column tempColumn in this.tbMain.Columns)
                    {
                        Cell cell = null;
                        if (tempColumn == colPortCode)
                        {
                            ////投资组合
                            cell = new Cell(port.C_PORT_CODE);
                            tempRow.Cells.Add(cell);
                        }
                        else if (tempColumn == colPortName)
                        {
                            cell = new Cell(port.C_PORT_NAME);
                            tempRow.Cells.Add(cell);
                        }
                        else if (tempColumn == colAssetsType)
                        {
                            cell = new Cell(colAssetsType.CellDictionaries[assetCodeDict[port.C_PORT_CODE]]);
                            tempRow.Cells.Add(cell);
                        }
                        else if (tempColumn == colAssetsTypeCode)
                        {
                            cell = new Cell(assetCodeDict[port.C_PORT_CODE]);
                            tempRow.Cells.Add(cell);
                        }
                        else if (tempColumn == colMxAssetsType)
                        {
                            ////明细资产类型
                            cell = new Cell(colAssetsType.CellDictionaries[assetCodeDict[port.C_PORT_CODE]]);
                            if (cell.Text == "证券投资基金")
                            {
                                cell.Key = "DAS_GMJJ";
                                cell.Text = "公募基金";                                
                                tempRow.Cells.Add(cell);
                            }
                            else if (cell.Text == "社会保障基金")
                            {
                                cell.Key = "DAS_SBJJ";
                                cell.Text = "社保基金";
                                tempRow.Cells.Add(cell);
                            }
                            else if (cell.Text == "企业年金基金")
                            {
                                cell.Key = "DAS_QYNJ";
                                cell.Text = "企业年金";
                                tempRow.Cells.Add(cell);
                            }
                            else
                            {
                                cell = new Cell();
                                tempRow.Cells.Add(cell);
                            }
                        }
                        else
                        {
                            ////投资组合
                            cell = new Cell();
                            tempRow.Cells.Add(cell);
                        }
                    }

                }

                this.tbMain.Refresh();
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 单元格选择改变事件--用于动态去除编辑控件
        /// </summary>
        /// <param name="sender">Table</param>
        /// <param name="e">事件参数</param>
        private void tbMain_SelectedCellChanged(object sender, Yss.KTable.Events.CellEventArgs e)
        {
            try
            {
                tbMain.SelectionMode = SelectionMode.One;
                if (!e.Cell.Selected && e.Cell.InnerControl != null)
                {
                    string cellText = e.Cell.InnerControl.Text;

                    if (e.Cell.InnerControl is FAST.Core.BaseControl.YssSelCombox)
                    {
                        string comBoxValue = "";
                        ////存储下拉框的Value值。
                        comBoxValue = (e.Cell.InnerControl as FAST.Core.BaseControl.YssSelCombox).Value;
                        e.Cell.Key = comBoxValue;
                    }

                     if (e.Cell.InnerControl is Yss.KRichEx.YssTextBox)
                    {
                        string txtValue = (e.Cell.InnerControl as Yss.KRichEx.YssTextBox).Text;
                        e.Cell.Key = txtValue;
                    }

                    e.Cell.InnerControl = null;
                    e.Cell.Text = cellText;
                }
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 单元格事件--用于动态加载编辑控件
        /// </summary>
        /// <param name="sender">Table</param>
        /// <param name="e">事件参数</param>
        private void tbMain_CellMouseClick(object sender, Yss.KTable.Events.CellEventArgs e)
        {
            try
            {
                e.Cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowOnClick;
                int columnIndex = this.colInvestCode.Index;
                if (e.Cell.RelColumn == colOperType)
                {
                    e.Cell.InnerControl = this.cboOperType;
                    e.Cell.InnerControl.Tag = e.Cell;

                    ////运作类型
                    this.cboOperType.Value = e.Cell.Key;
                }
                else if (e.Cell.RelColumn == colMxAssetsType)
                {
                    e.Cell.InnerControl = this.cboMxAssetsType;
                    e.Cell.InnerControl.Tag = e.Cell;

                    ////明细资产类型
                    this.cboMxAssetsType.Value = e.Cell.Key;
                }
                else if (e.Cell.RelColumn == colCollectCode)
                {
                    e.Cell.InnerControl = this.cboCollectCode;
                    e.Cell.InnerControl.Tag = e.Cell;

                    ////募集对象
                    this.cboCollectCode.Value = e.Cell.Key;
                }
                else if (e.Cell.RelColumn == this.colClientType)
                {
                    e.Cell.InnerControl = this.cboClientType;
                    e.Cell.InnerControl.Tag = e.Cell;

                    ////客户类型
                    this.cboClientType.Value = e.Cell.Key;
                }
                else if (e.Cell.RelColumn == colInvestCode)
                {
                    e.Cell.InnerControl = this.cboInvestCode;
                    e.Cell.InnerControl.Tag = e.Cell;

                    ////投资对象
                    this.cboInvestCode.Value = e.Cell.Key;
                }
                else if (e.Cell.RelColumn == colAssetsCode)
                {
                    e.Cell.InnerControl = this.cboAssetsCode;
                    e.Cell.InnerControl.Tag = e.Cell;

                    ////资产种类
                    this.cboAssetsCode.Value = e.Cell.Key;
                }
                else if (e.Cell.RelColumn == this.colSettMode)
                {
                    e.Cell.InnerControl = this.cboSettMode;
                    e.Cell.InnerControl.Tag = e.Cell;

                    ////证券结算模式
                    this.cboSettMode.Value = e.Cell.Key;
                }
                else if (e.Cell.RelColumn == colPortType)
                {
                    string assetType = (e.Cell).OwnRow.Cells[columnIndex].Key;
                    if (!("INV_QYL".Equals(assetType)))
                     {
                       e.Cell.InnerControl = this.cboPortType;
                       e.Cell.InnerControl.Tag = e.Cell;
                    ////组合类别
                      this.cboPortType.Value = e.Cell.Key;
                     }
                }
                else if (e.Cell.RelColumn == colPdNum)
                {
                    e.Cell.InnerControl = this.txtPdNum;
                    e.Cell.InnerControl.Tag = e.Cell;

                    ////产品编号
                    this.txtPdNum.Text = e.Cell.Key;
                }
                else if (e.Cell.RelColumn == colShortNum)
                {
                    e.Cell.InnerControl = this.txtShortNum;
                    e.Cell.InnerControl.Tag = e.Cell;

                    ////短编码
                    ////add by guohui 20161026  STORY34547【南方基金】保险资产报表需要区分产品编码(使用协会统一编码）与短码////
                    this.txtShortNum.Text = e.Cell.Key;
                }
                else if (e.Cell.RelColumn == colContractName)
                {
                    e.Cell.InnerControl = this.txtContractName;
                    e.Cell.InnerControl.Tag = e.Cell;

                    ////合同名称
                    this.txtContractName.Text = e.Cell.Key;
                }
                else if (e.Cell.RelColumn == colDesc)
                {
                    e.Cell.InnerControl = this.txtDesc;
                    e.Cell.InnerControl.Tag = e.Cell;

                    ////描述
                    this.txtDesc.Text = e.Cell.Key;
                }
                else if (e.Cell.RelColumn == colttAccount)
                {
                    e.Cell.InnerControl = this.cbcttgAccount;
                    e.Cell.InnerControl.Tag = e.Cell;

                    ////次托托管账户 add by wsm 2016-5-7 STORY #30235【招商基金母公司】[QDII]TradeBlotter_EXP TradeBlotter招行导出接口
                    this.cbcttgAccount.Text = e.Cell.Key;
                }
                else if (e.Cell.RelColumn == zjhjc)
                {
                    e.Cell.InnerControl = this.zjhjctxt;
                    e.Cell.InnerControl.Tag = e.Cell;

                    ////证监会简称 add by wgl  STORY #36888 【南方基金】【紧急】产品信息多加一列证监会简称字段
                    this.zjhjctxt.Text = e.Cell.Key;
                }
                else
                {
                }
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 投资对象控件点击前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboInvestCode_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            string assetType = "";
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo = new FAST.Core.BaseControl.ControlMethodInfo();
            try
            {
                ////int columnIndex = this.colAssetsTypeCode.Index;
                ////assetType = (this.cboInvestCode.Tag as Cell).OwnRow.Cells[columnIndex].Text;
                ////if (null != assetType && 0 != assetType.Trim().Length)
                ////{
                ////    if ("ASS_ZQTZJJ".Equals(assetType) || "ASS_QDII".Equals(assetType))
                ////    {
                ////        controlMethodInfo.MethodName = "getDataListByTypes";
                ////        controlMethodInfo.MethodParamValues = new string[] { "INV_ZQTZJJ," };
                ////    }
                ////    else if ("ASS_XTZCCP".Equals(assetType))
                ////    {
                ////        controlMethodInfo.MethodName = "getDataListByTypes";
                ////        controlMethodInfo.MethodParamValues = new string[] { "INV_XTZCCP," };
                ////    }
                ////    else if ("ASS_ZCGLJH".Equals(assetType))
                ////    {
                ////        controlMethodInfo.MethodName = "getDataListByTypes";
                ////        controlMethodInfo.MethodParamValues = new string[] { "INV_ZCGLJH," };
                ////    }
                ////    else
                ////    { 
                ////    }
                ////}

                controlMethodInfo.MethodName = "getDataListByTypes";
                controlMethodInfo.MethodParamValues = new string[] { "INV," };
                this.cboInvestCode.MethodInfo = controlMethodInfo;
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 资产种类按钮点击前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboAssetsCode_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            string assetType = "";
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo = new FAST.Core.BaseControl.ControlMethodInfo();
            try
            {
                int columnIndex = this.colAssetsTypeCode.Index;
                assetType = (this.cboAssetsCode.Tag as Cell).OwnRow.Cells[columnIndex].Text;
                if (null != assetType && 0 != assetType.Trim().Length)
                {
                    if ("ASS_ZQTZJJ".Equals(assetType))
                    {
                        controlMethodInfo.MethodName = "getDataListByTypes";
                        controlMethodInfo.MethodParamValues = new string[] { "ASS_ZQTZJJ," };
                    }
                    else if ("ASS_ZCGLJH".Equals(assetType))
                    {
                        controlMethodInfo.MethodName = "getDataListByTypes";
                        controlMethodInfo.MethodParamValues = new string[] { "ASS_ZCGLJH," };
                    }
                    else if ("ASS_SBJJ".Equals(assetType))
                    {
                        controlMethodInfo.MethodName = "getDataListByTypes";
                        controlMethodInfo.MethodParamValues = new string[] { "ASS_SBJJ," };
                    }
                    else if ("ASS_QYNJJJ".Equals(assetType))
                    {
                        controlMethodInfo.MethodName = "getDataListByTypes";
                        controlMethodInfo.MethodParamValues = new string[] { "ASS_QYNJJJ," };
                    }
                    else if ("ASS_XTZCCP".Equals(assetType))
                    {
                        controlMethodInfo.MethodName = "getDataListByTypes";
                        controlMethodInfo.MethodParamValues = new string[] { "ASS_XTZCCP," };
                    }
                    else if ("ASS_QDII".Equals(assetType))
                    {
                        controlMethodInfo.MethodName = "getDataListByTypes";
                        controlMethodInfo.MethodParamValues = new string[] { "ASS_QDII," };
                    }
                    else if ("ASS_RQFII".Equals(assetType))
                    {
                        controlMethodInfo.MethodName = "getDataListByTypes";
                        controlMethodInfo.MethodParamValues = new string[] { "ASS_RQFII," };
                    }
                    else if ("ASS_YHZCCP".Equals(assetType))
                    {
                        controlMethodInfo.MethodName = "getDataListByTypes";
                        controlMethodInfo.MethodParamValues = new string[] { "ASS_YHZCCP," };
                    }
                    else
                    {
                    }
                }

                this.cboAssetsCode.MethodInfo = controlMethodInfo;
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 加载主界面List
        /// </summary>
        /// <param name="tempRow">tempRow</param>
        private void LoadTbMainList(Row tempRow)
        {
            this.tbMain.GridLine = Yss.KTable.Enums.GridLines.Both;
            this.tbMain.GridLineColor = System.Drawing.Color.Gray;

            foreach (Column tempColumn in this.tbMain.Columns)
            {
                Cell cell = new Cell();
                cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowOnClick;
                tempRow.Cells.Add(cell);
            }
        }

        /// <summary>
        /// 下拉框改变事件，将下拉框的Value值绑定到对应的单元格的Key值上。
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void ComboBox_SelectedValueChanged(object sender, EventArgs e)
        {
            FAST.Core.BaseControl.YssSelCombox selCombox = null;

            try
            {
                selCombox = sender as FAST.Core.BaseControl.YssSelCombox;
                if (!string.IsNullOrEmpty(selCombox.Value))
                {
                    if (selCombox.Tag != null)
                    {
                        (selCombox.Tag as Cell).Key = selCombox.Value;
                    }
                }
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 描述值改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void txtDesc_TextChanged(object sender, EventArgs e)
        {
            try
            {
                ((sender as Control).Tag as Cell).Key = (sender as Yss.KRichEx.YssTextBox).Text;
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 重写撤销事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnRecall_Click(object sender, EventArgs e)
        {
            ////BUG #131480 无法访问已释放资源，时有时无的出现。
            ////清理行之前，先移走所有的InnerControl，以防InnerControl被同步销毁。张绍林-20160525
            this.tbMain.RemoveCellsControl();
            this.tbMain.Rows.Clear(true);
            base.btnRecall_Click(sender, e);
        }

        /// <summary>
        /// 重写新增事件，清除tbMain数据
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnNew_Click(object sender, EventArgs e)
        {
            ////BUG #131480 无法访问已释放资源，时有时无的出现。
            ////清理行之前，先移走所有的InnerControl，以防InnerControl被同步销毁。张绍林-20160525
            this.tbMain.RemoveCellsControl();
            this.tbMain.Rows.Clear(true);
            base.btnNew_Click(sender, e);
        }

        /// <summary>
        /// 初始化控件状态
        /// </summary>
        public override void initControlStat()
        {
            base.initControlStat();

            if (status == ClsEnums.StatusSetting.YssAdd && this.frmBaseViewList.tbLeftMain.Visible == true && this.frmBaseViewList.tbLeftMain.SelectedRow != null)
            {
                FAST.Common.Service.Pojo.Port port = this.frmBaseViewList.tbLeftMain.SelectedRow.Tag as FAST.Common.Service.Pojo.Port;

                if (port != null)
                {
                    this.cmbGroups.Value = port.C_PORT_CODE;
                }
            }
        } 


        /// <summary>
        /// 组合类别只针对投资对象为非权益类的有意义
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboInvestCode_SelectedValueChanged(object sender, EventArgs e)
        {
            string investCode = "";
            try
            {
                if (this.cboInvestCode.Value != null && this.cboInvestCode.Value.Trim().Length != 0)
                {
                    investCode = this.cboInvestCode.Value;
                    int columnIndex = this.colPortType.Index;

                    if ("INV_QYL".Equals(investCode))
                    {
                        (this.cboInvestCode.Tag as Cell).OwnRow.Cells[columnIndex].Text = "";
                        (this.cboInvestCode.Tag as Cell).OwnRow.Cells[columnIndex].Key = "";

                    }
                }
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }

           
        }

        /// <summary>
        /// 获取界面布局、排版用的表格（用于自动化测试时，提取界面控件元素）。
        /// </summary>
        /// <returns>返回表格集</returns>
        protected override List<Table> GetLayoutTables()
        {
            List<Table> tableList = new List<Table>();
            tableList.Add(this.tbMain);
            tableList.Add(this.tbTop);
            return tableList;
        }

        /////// <summary>
        /////// 重写复制事件
        /////// </summary>
        /////// <param name="sender">sender</param>
        /////// <param name="e">e</param>
        ////protected override void btnCopy_Click(object sender, EventArgs e)
        ////{
        ////    ////this.tbMain.Rows.Clear(true);
        ////    base.btnCopy_Click(sender, e);
        ////}



    }
}


