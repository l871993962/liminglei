﻿using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Interface;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;

using FAST.Core.Context;

using FAST.Platform.Right.Fun;
using FAST.Core.BaseControl.Fun;

using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

using Yss.KTable.Models;

using Yss.KTable.Events;
using Yss.KRichEx;
using FAST.Core.BaseControl;
using System.Collections;
using YssSecInformation.Support.Sv.Service;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Sv.Pojo;



namespace YssSecInformation.Sv.Form
{
    /// <summary>
    /// 期货保证金设置
    /// </summary>
    public partial class Frm_SEC_BASE_QHBZJ_S : FrmComplexSet
    {
        /// <summary>
        /// 期货保证金接口
        /// </summary>
        private ISecBaseQhbzjService myService = null;

        /// <summary>
        /// 主数据ID
        /// </summary>
        private string id = null;

        /// <summary>
        /// 证券代码
        /// </summary>
        private string secCode = null;

        /// <summary>
        /// 初始化
        /// </summary>
        public Frm_SEC_BASE_QHBZJ_S()
        {
            InitializeComponent();
            this.bUseMVCService = true;
        }

        /// <summary>
        /// 初始List窗体
        /// </summary>
        /// <param name="frm">list窗体</param>
        public override void initForm(FrmBaseList frm)
        {
            frmBaseViewList = frm;
            _formFun = frm.YssFormMenu;
            _formFun.C_FUN_CODE = "sv_qhbzj";
            _formFun.C_FUN_NAME = "期货保证金";

            if (clsInterface == null)
            {
                clsInterface = new ClsInterface();
            }

            if (dataAdmin == null)
            {
                dataAdmin = new ClsBaseDataAdmin();
                dataAdmin.CurFUN = _formFun;

                if (isLeftSetForm)
                {
                    dataAdmin.C_OperMenu_Code = formFunCode;
                }
            }

            if (frmBaseViewList != null)
            {
                ////status = ClsEnums.StatusSetting.YssBrow;
                dataAdmin.Page = frmBaseViewList.Page;
            }
        }


        /// <summary>
        /// 窗体Load方法
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_TRADE_IVT_COLLATERAL_S_Load(object sender, EventArgs e)
        {
            initColumnHeader();
            getInstanceService();
            ////status = ClsEnums.StatusSetting.YssBrow;
            status = ClsEnums.StatusSetting.YssAdd;
            BasePojo pojo = new BasePojo();
            showInfoMVC(pojo);
            ////btnBar.setButtonEnabled(ClsButtonName.BtnEdit, true);
            ////modified by liyanjun 20140812 bug99027 
            ////btnBar.setButtonVisable(ClsButtonName.BtnAudit, true);
            ////btnBar.setButtonVisable(ClsButtonName.BtnUnAudit, true);
            ////modified by yuyongjiang 20150316 bug109229 
            ////add by yeleilei 20161114 BUG #143982 
            btnBar.setButtonEnabled(ClsButtonName.BtnSave, true);
            btnBar.setButtonVisable(ClsButtonName.BtnDelete, true);
            btnBar.setButtonVisable(ClsButtonName.BtnCopy, true);
            ////add by gongyue 20170515
            ////复核机制设置为0
            this.YssFormMenu.N_CHECK = 0;
            ////加载界面时审核反审核按钮就设置为不可见
            this.btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnAudit, false);
            this.btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnUnAudit, false);
        }


        /// <summary>
        /// 获取list中选中记录，为界面元素赋值，显示数据
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            try
            {
                if (status == ClsEnums.StatusSetting.YssBrow)
                {
                    this.subTable.ToolBoxEnabled = false;
                    this.subTable.Table.ReadOnly = true;
                }
                else
                {
                    this.subTable.ToolBoxEnabled = true;
                    ////this.subTable.Table.ReadOnly = false;
                }

                QueryRes res = null;
                Dictionary<string, string> paraDict = new Dictionary<string, string>();

                // 获取期货保证金信息
                paraDict.Add("dataClass", "SecBaseQhbzj");
                paraDict.Add("C_SEC_CODE", secCode);
                
                ISecBaseQhbzjService bzjService = ServiceFactory.createService<ISecBaseQhbzjService>();
                res = bzjService.queryByCondition(paraDict);

                List<BasePojo> ratioList = res.DataList;
                loadInfoData(ratioList);
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }
        }


        /// <summary>
        /// 加载信息
        /// </summary>
        /// <param name="ratioList">ratioList</param>
        private void loadInfoData(List<BasePojo> ratioList)
        {
            try
            {
                if (this.subTable.Table.Rows.Count > 0)
                {
                    foreach (Row row in this.subTable.Table.Rows)
                    {
                        row.Cells.Clear(true);
                    }

                    this.subTable.Table.Rows.Clear();
                }

                this.subTable.Table.Refresh();

                if (null != ratioList && ratioList.Count > 0)
                {
                    foreach (SecBaseQhbzj secQhbzj in ratioList)
                    {
                        Row row = new Row();
                        Cell cell = new Cell();
                        row.Cells.Add(cell);

                        ////cell = new Cell();
                        ////initDefaultCell(cell);
                        ////(cell.InnerControl as YssSelCombox).Value = secCode; // 证券代码
                        ////row.Cells.Add(cell);
                        cell = new Cell();
                        initTextBoxCell(cell);
                        cell.InnerControl.Text = secCode; // 保证金利率
                        
                        row.Cells.Add(cell);


                        cell = new Cell();
                        ////initSecCell(cell);
                        initStartDateCell(cell);
                        (cell.InnerControl as YssDateTimeInterval).BusinessDate = false;
                        (cell.InnerControl as YssDateTimeInterval).setDateTime(secQhbzj.D_START, secQhbzj.D_START); // 启用日期
                        row.Cells.Add(cell);

                        cell = new Cell();
                        initTextCell(cell);
                        cell.InnerControl.Text = Convert.ToString(secQhbzj.N_RATE); // 保证金利率
                        row.Cells.Add(cell);

                        cell = new Cell();
                        initTextCell(cell);
                        cell.InnerControl.Text = Convert.ToString(secQhbzj.N_PRICE_ISSUE); // 固定保证金
                        row.Cells.Add(cell);

                        row.Tag = secQhbzj;
                        this.subTable.Table.Rows.Add(row);
                    }
                }

                this.subTable.Table.Refresh();
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }
        }

        /// <summary>
        /// 创建窗体接口实例
        /// </summary>
        private void getInstanceService()
        {
            if (null == this.myService)
            {
                ////Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
                this.myService = ServiceFactory.createService<ISecBaseQhbzjService>();
                this.dataService = this.myService;
            }
        }

        /// <summary>
        /// 加载信息设置列头
        /// </summary>
        private void initColumnHeader()
        {
            try
            {
                QueryRes res = null;
                Dictionary<string, string> paraDict = new Dictionary<string, string>();
                ////id = (this.frmBaseViewList.tbMain.SelectedRow.Tag as EtfMainTrade).Id;
                ////secCode = (this.frmBaseViewList.tbMain.SelectedRow.Tag as EtfMainTrade).C_SEC_CODE;
                if (null == this.frmBaseViewList.tbMain.SelectedRow)
                {
                    return;
                }

                secCode = (this.frmBaseViewList.tbMain.SelectedRow.Tag as SecBase).C_SEC_CODE;

                //// 期货保证金对象
                paraDict.Add("dataClass", "SecBaseQhbzj");

                ISecBaseQhbzjService bzjService = ServiceFactory.createService<ISecBaseQhbzjService>();
                res = bzjService.querybzj(paraDict);

                (new TableListLoader()).loadTable(this.subTable.Table, res, false, true, ClsEnums.KTableDataShowMode.ListMode);

                this.subTable.Table.Columns[0].Width = 20;
                this.subTable.Table.Columns[1].Width = 140;
                this.subTable.Table.Columns[2].Width = 140;
                this.subTable.Table.Columns[3].Width = 111;

                this.subTable.AfterRowAdded += new RowEventHandler(subTable_AfterRowAdded);
                this.subTable.BeforeRowRemoved += new RowEventHandler(subTable_BeforeRowRemoved);
                this.subTable.Table.SelectionMode = System.Windows.Forms.SelectionMode.One;
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }
        }

        /// <summary>
        /// 点击关联信息的删除行按钮触发的事件
        /// 为各列填充相应的控件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void subTable_BeforeRowRemoved(object sender, RowEventArgs e)
        {
            ////if (status == ClsEnums.StatusSetting.YssAdd)
            ////{
            ////    e.Cancel = true;
            ////}
        }

        /// <summary>
        /// 点击关联信息的添加行按钮触发的事件
        /// 为各列填充相应的控件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void subTable_AfterRowAdded(object sender, RowEventArgs e)
        {
            try
            {
                Row row = e.Row;
                for (int i = 1; i <= 4; i++)
                {
                    if (row.Cells[i].InnerControl == null)
                    {
                        switch (i)
                        {
                            case 1:
                                {
                                    ////initDefaultCell(row.Cells[i]);
                                    initTextBoxCell(row.Cells[i]);
                                    row.Cells[i].InnerControl.Text = secCode;
                                    break;
                                }

                            case 2:
                                {
                                    initStartDateCell(row.Cells[i]);
                                    break;
                                }   

                            case 3:
                                {
                                    initTextCell(row.Cells[i]);
                                    break;
                                }

                            case 4:
                                {
                                    initTextCell(row.Cells[i]);
                                    break;
                                }
                        }
                        
                    }
                }

                (row.Cells[1].InnerControl as YssTextBox).YssCaption = "证券代码";
                ////(row.Cells[2].InnerControl as YssDateTimeInterval).YssCaption = "启用日期";
                (row.Cells[3].InnerControl as YssTextBox).YssCaption = "保证金比例";
                (row.Cells[4].InnerControl as YssTextBox).YssCaption = "固定保证金";

                for (int i = 3; i <= 4; i++)
                {
                    if (4 != i)
                    {
                        (row.Cells[i].InnerControl as YssTextBox).AutoTooltip = false;
                    }

                    (row.Cells[i].InnerControl as YssTextBox).Border.BorderColor = row.Cells[i].InnerControl.BackColor;
                    row.Cells[i].BackColor = row.Cells[i].InnerControl.BackColor;
                }

                row.Cells[1].InnerControl.Select();
                row.Selected = true;
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 新增
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnNew_Click(object sender, EventArgs e)
        {
            base.btnNew_Click(sender, e);
            this.subTable.Table.ReadOnly = false;
            this.subTable.ToolBoxEnabled = true;
            foreach (Row row in this.subTable.Table.Rows)
            {
                (row.Cells[1].InnerControl as YssTextBox).YssReadOnly = true;  ////edt by zzk 20150516 控件类型错误 BUG #112478
            }

            this.subTable.Table.Refresh();
        }

        /// <summary>
        /// 修改
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnEdit_Click(object sender, EventArgs e)
        {
            base.btnEdit_Click(sender, e);
            this.subTable.Table.ReadOnly = false;
            this.subTable.ToolBoxEnabled = true;
            foreach (Row row in this.subTable.Table.Rows)
            {
                (row.Cells[1].InnerControl as YssTextBox).YssReadOnly = true; ////edt by zzk 20150516 控件类型错误 BUG #112478
                ////(row.Cells[2].InnerControl as YssSelCombox).YssReadOnly = false;
                ////(row.Cells[3].InnerControl as YssTextBox).YssReadOnly = false;
                (row.Cells[3].InnerControl as YssTextBox).YssIsMust = true;
            }

            this.subTable.Table.Refresh();
        }


        /// <summary>
        /// 获取list中选中记录，为界面元素赋值，显示数据
        /// </summary>
        public void showCollateralInfo()
        {
            try
            {
                if (status == ClsEnums.StatusSetting.YssBrow)
                {
                    this.subTable.ToolBoxEnabled = false;
                    this.subTable.Table.ReadOnly = true;
                }
                else
                {
                    this.subTable.ToolBoxEnabled = true;
                    this.subTable.Table.ReadOnly = false;
                }

                // 获取当前选中的数据项
                ////List<Collateral> colList = myService.getCollateralInfo(rowId);
                ////if (colList != null)
                ////{
                ////// 获取关联信息
                ////Dictionary<string, string> paraDict = new Dictionary<string, string>();
                ////paraDict.Add("C_IDEN_RELA", rowId);
                ////paraDict.Add("dataClass", "collateral");
                ////IServiceBus ratioInfoService = ServiceFactory.createService<IHgTradeService>();
                ////QueryRes res = myService.queryCollateralByCondition(paraDict);
                ////if (res.DataList != null && res.DataList.Count > 0)
                ////{
                ////    loadCollateralInfoData(res.DataList);
                ////}
                ////}
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }
        }

        /// <summary>
        /// 加载信息到list中
        /// </summary>
        /// <param name="collateralList">collateralList</param>
        private void loadCollateralInfoData(List<BasePojo> collateralList)
        {
            try
            {
                if (this.subTable.Table.Rows.Count > 0)
                {
                    foreach (Row row in this.subTable.Table.Rows)
                    {
                        row.Cells.Clear(true);
                    }

                    this.subTable.Table.Rows.Clear();
                }

                if (null != collateralList && collateralList.Count > 0)
                {
                    ////foreach (Collateral collateral in collateralList)
                    ////{
                    ////    Row row = new Row();

                    ////    Cell cell = new Cell();
                    ////    row.Cells.Add(cell);

                    ////    cell = new Cell();
                    ////    initSecCell(cell);
                    ////    cell.InnerControl.Text = Convert.ToString(collateral.C_SEC_CODE); // 证券代码
                    ////    (cell.InnerControl as YssTextBox).YssCaption = "证券代码";
                    ////    row.Cells.Add(cell);

                    ////    cell = new Cell();
                    ////    initSecCell(cell);
                    ////    cell.InnerControl.Text = Convert.ToString(collateral.C_SEC_CODE_TAG); // 标的证券
                    ////    (cell.InnerControl as YssTextBox).YssCaption = "标的证券";
                    ////    row.Cells.Add(cell);

                    ////    cell = new Cell();
                    ////    initTextCell(cell);
                    ////    cell.InnerControl.Text = Convert.ToString(collateral.N_TD_AMOUNT); // 成交数量
                    ////    (cell.InnerControl as YssTextBox).YssCaption = "成交数量";
                    ////    row.Cells.Add(cell);

                    ////    row.Tag = collateral;
                    ////    this.subTable.Table.Rows.Add(row);
                    ////}
                }

                this.subTable.Table.Refresh();
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }
        }

        /// <summary>
        /// 初始化证券cell
        /// </summary>
        /// <param name="cell">cell</param>
        private void initDefaultCell(Cell cell)
        {
            YssSelCombox selSec = new YssSelCombox();
            selSec.YssAssociaType = YssSecInformation.Support.Context.AssociaType.sv_sec;
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo = new FAST.Core.BaseControl.ControlMethodInfo();
            controlMethodInfo.MethodName = "getDataListByTypes";
            controlMethodInfo.MethodParamValues = new string[] { "QH," };
            selSec.MethodInfo = controlMethodInfo;
            selSec.DisplayName = "C_SEC_CODE";
            selSec.DisplayValue = "C_SEC_CODE";
            selSec.YssReadOnly = true;
            selSec.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
            selSec.YssIsMust = true;
            cell.InnerControl = selSec;
            ////cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowAlways;
        }

        /// <summary>
        /// 初始化Textcell
        /// </summary>
        /// <param name="cell">cell</param>
        private void initTextCell(Cell cell)
        {
            YssTextBox txtBox = new YssTextBox();
            txtBox.Border.Class = "TextBoxBorder";
            txtBox.Height = this.subTable.Table.RowHeight;
            txtBox.IsFillDecimal = false;
            txtBox.Tag = cell;
            txtBox.Text = "0";
            txtBox.TextAlign = HorizontalAlignment.Right;
            txtBox.YssReadOnly = (status == ClsEnums.StatusSetting.YssBrow);
            txtBox.YssNumeric = "18,4";
            txtBox.Border.BorderColor = txtBox.BackColor;
            cell.BackColor = txtBox.BackColor;
            cell.InnerControl = txtBox;
            cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowAlways;
        }


        /// <summary>
        /// 初始化Textcell
        /// </summary>
        /// <param name="cell">cell</param>
        private void initTextBoxCell(Cell cell)
        {
            YssTextBox txtBox = new YssTextBox();
            txtBox.Border.Class = "TextBoxBorder";
            txtBox.Height = this.subTable.Table.RowHeight;
            txtBox.IsFillDecimal = false;
            txtBox.Tag = cell;
            txtBox.Text = "0";
            txtBox.YssReadOnly = true;
            txtBox.Border.BorderColor = txtBox.BackColor;
            cell.BackColor = txtBox.BackColor;
            cell.InnerControl = txtBox;
            cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowAlways;
        }

        /// <summary>
        /// 初始化启用日期cell
        /// </summary>
        /// <param name="cell">cell</param>
        private void initStartDateCell(Cell cell)
        {
            ////////////////////////////////////
            YssDateTimeInterval startDate = new YssDateTimeInterval();
            cell.InnerControl = startDate;
            cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowAlways;
        }

        /// <summary>
        /// 封装基本信息
        /// </summary>
        /// <returns>信息</returns>
        public override ArrayList yssGetObjListMVC()
        {
            ArrayList secQhbzjList = new ArrayList();
            try
            {
                foreach (Row row in this.subTable.Table.Rows)
                {
                    SecBaseQhbzj secQhbzj = new SecBaseQhbzj();
                    int colIndex = 1;
                    foreach (Column col in this.subTable.Table.Columns)
                    {
                        if (colIndex == 1)
                        {
                            //// 证券代码
                            secQhbzj.C_SEC_CODE = this.secCode;
                        }
                        else if (colIndex == 2)
                        {
                            //// 启用日期
                            secQhbzj.D_START = (row.Cells[colIndex].InnerControl as YssDateTimeInterval).getBeginDate;
                        }
                        else if (colIndex == 3)
                        {
                            //// 保证金比例 %
                            secQhbzj.N_RATE = Convert.ToDecimal(row.Cells[colIndex].InnerControl.Text.Trim().Equals("") ? "0" : row.Cells[colIndex].InnerControl.Text) / 100;
                        }
                        else if (colIndex == 4)
                        {
                            //// 固定保证金
                            secQhbzj.N_PRICE_ISSUE = Convert.ToDecimal(row.Cells[colIndex].InnerControl.Text.Trim().Equals("") ? "0" : row.Cells[colIndex].InnerControl.Text);
                        }

                        colIndex = colIndex + 1;
                    }

                    ////info.C_IDEN_RELA = this.id;
                    secQhbzjList.Add(secQhbzj);
                }
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }


            if (0 == secQhbzjList.Count)
            {
                SecBaseQhbzj sec = new SecBaseQhbzj();
                sec.C_SEC_CODE = secCode;
                sec.Id = "-1";
                secQhbzjList.Add(sec);
            }

            return secQhbzjList;
        
        }

        /// <summary>
        /// 窗体关闭时设置funName;
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_BASE_QHBZJ_S_FormClosing(object sender, FormClosingEventArgs e)
        {
            _formFun.C_FUN_CODE = "sv_future";
            _formFun.C_FUN_NAME = "期货品种信息";
        }
    }
}




