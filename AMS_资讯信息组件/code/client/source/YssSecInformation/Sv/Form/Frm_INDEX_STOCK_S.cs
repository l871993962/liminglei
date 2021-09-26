using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
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

using YssSecInformation.Support.Sv.Service;

using System.Collections;
////using YssSecInformation.Support.Pojo.Sec;



using Yss.KTable.Models;
using Yss.KTable.Collections;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Sv.Pojo;
////using YssBaseCls.Pojo;


namespace YssSecInformation.Sv.Form
{
    /// <summary>
    ///  chenbo
    /// 2017-06-24
    /// #42948 资讯信息管理组件化拆分
    /// 指数成分券信息维护界面
    /// add by liuxaing 2014/8/11
    /// </summary>
    public partial class Frm_INDEX_STOCK_S : FrmBaseSet
    {
        /// <summary>
        /// 指数成分券信息服务
        /// </summary>
        private IIndexStockService service = null;

        /// <summary>
        /// 列头信息
        /// </summary>
        private ArrayList showColumnList = new ArrayList();

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_INDEX_STOCK_S()
        {
            bUseMVCService = true;
            InitializeComponent();
            initService();
            initListHead();
        }

        /// <summary>
        /// 为row设置所属table
        /// </summary>
        /// <param name="row">要设置的行</param>
        /// <param name="ownTable">所属table</param>
        private void setOwnTable(Row row, Table ownTable)
        {
            if (null != row)
            {
                if (row.SubRows.Count > 0)
                {
                    foreach (Row subRow in row.SubRows)
                    {
                        setOwnTable(subRow, ownTable);
                    }
                }

                row.OwnTable = ownTable;
            }
        }

        /// <summary>
        /// 取消行的勾选状态
        /// </summary>
        /// <param name="row">row</param>
        private void cancelCheckState(Row row)
        {
            if (null != row)
            {
                row.Checked = false;
                if (row.SubRows.Count > 0)
                {
                    foreach (Row subRow in row.SubRows)
                    {
                        cancelCheckState(subRow);
                    }
                }
            }
        }

        /// <summary>
        /// 将选中项目从右侧移动到左侧
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void rightToleftSelected_Click(object sender, EventArgs e)
        {
            if (this.tbSelected.CheckedRows.Count == 0)
            {
                return;
            }

            while (this.tbSelected.CheckedRows.Count > 0)
            {
                Row tempRow = this.tbSelected.CheckedRows[0];

                if (tempRow.ParentRow != null)
                {
                    tempRow.ParentRow.SubRows.Remove(tempRow);
                    tempRow.ParentRow = null;
                }
                else
                {
                    this.tbSelected.Rows.Remove(tempRow);
                }

                tempRow.OwnTable = null;

                //// 取消行的选择状态
                cancelCheckState(tempRow);

                this.tbSelectable.Rows.Add(tempRow);
                setOwnTable(tempRow, tbSelectable);
            }

            refreshAfterMove();
        }

        /// <summary>
        /// 将选中项目从左侧移动到右侧
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void leftTorightSelected_Click(object sender, EventArgs e)
        {
            if (this.tbSelectable.CheckedRows.Count == 0)
            {
                return;
            }

            while (this.tbSelectable.CheckedRows.Count > 0)
            {
                Row tempRow = this.tbSelectable.CheckedRows[0];

                if (tempRow.ParentRow != null)
                {
                    tempRow.ParentRow.SubRows.Remove(tempRow);
                    tempRow.ParentRow = null;
                }
                else
                {
                    this.tbSelectable.Rows.Remove(tempRow);
                }

                tempRow.OwnTable = null;
                cancelCheckState(tempRow);

                this.tbSelected.Rows.Insert(0, tempRow);
                setOwnTable(tempRow, tbSelected);
            }

            refreshAfterMove();
        }

        /// <summary>
        /// 将所有项目从右侧移动到左侧
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void rightToleftAll_Click(object sender, EventArgs e)
        {
            if (this.tbSelected.Rows.Count > 0 && YssMessageBox.ShowQuestion("是否要将全部证券执行移动操作？", "提示") == DialogResult.Yes)
            {
                while (this.tbSelected.Rows.Count > 0)
                {
                    Row tempRow = this.tbSelected.Rows[0];

                    if (tempRow.ParentRow != null)
                    {
                        tempRow.ParentRow.SubRows.Remove(tempRow);
                        tempRow.ParentRow = null;
                    }
                    else
                    {
                        this.tbSelected.Rows.Remove(tempRow);
                    }

                    tempRow.OwnTable = null;

                    //// 取消行的选择状态
                    cancelCheckState(tempRow);

                    this.tbSelectable.Rows.Add(tempRow);
                    setOwnTable(tempRow, tbSelectable);
                }

                refreshAfterMove();
            }
        }

        /// <summary>
        /// 将所有项目从左侧移动到右侧
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void leftTorightAll_Click(object sender, EventArgs e)
        {
            if (this.tbSelectable.Rows.Count > 0 && YssMessageBox.ShowQuestion("是否要将全部证券执行移动操作？", "提示") == DialogResult.Yes)
            {
                while (this.tbSelectable.Rows.Count > 0)
                {
                    Row tempRow = this.tbSelectable.Rows[0];

                    if (tempRow.ParentRow != null)
                    {
                        tempRow.ParentRow.SubRows.Remove(tempRow);
                        tempRow.ParentRow = null;
                    }
                    else
                    {
                        this.tbSelectable.Rows.Remove(tempRow);
                    }

                    tempRow.OwnTable = null;
                    cancelCheckState(tempRow);

                    this.tbSelected.Rows.Add(tempRow);
                    setOwnTable(tempRow, tbSelected);
                }

                refreshAfterMove();
            }
        }

        /// <summary>
        /// 列表数据移动后刷新table
        /// </summary>
        private void refreshAfterMove()
        {
            try
            {
                this.tbSelected.CancelGroup();
                this.tbSelectable.CancelGroup();

                if (tbSelectable.Columns["C_MKT_CODE"] != null)
                {
                    tbSelectable.GroupBy(tbSelectable.Columns["C_MKT_CODE"]);
                }

                if (tbSelected.Columns["C_MKT_CODE"] != null)
                {
                    tbSelected.GroupBy(tbSelected.Columns["C_MKT_CODE"]);
                }

                this.tbSelected.Refresh();
                this.tbSelectable.Refresh();
            }
            catch (Exception e)
            {
                throw (e);
            }
        }

        /// <summary>
        /// 指数代码值改变事件---重新加载列表数据
        /// </summary>
        /// <param name="sender">sneder</param>
        /// <param name="e">e</param>
        private void cboIndexCode_SelectedValueChanged(object sender, EventArgs e)
        {
            try
            {
                if (null != this.cboIndexCode.Value && !"".Equals(this.cboIndexCode.Value))
                {
                    loadSecBaseTreeR();
                    //// add by liuxiang 2015/2/5 BUG #107477 指数成分券设置界面的新增功能调整
                    if (this.tbSelected.Rows.Count == 0 && status == ClsEnums.StatusSetting.YssAdd)
                    {
                        loadLastSecBaseTreeL();
                        loadLastSecBaseTreeR();
                    }
                    else
                    {
                        loadSecBaseTreeL();
                    }
                }
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }
        }

        /// <summary>
        /// 日期改变事件---重新加载列表数据
        /// </summary>
        /// <param name="sender">sneder</param>
        /// <param name="e">e</param>
        private void dtpBegin_FirstdateTimeInputValueChanged(object sender, EventArgs e)
        {
            try
            {
                //// eidt by liuxiang 2015/1/31
                //// BUG #107324 指数成分券信息设置界面 点击复制，然后选择日期，会把所有已经选择的成分券去除
                if (null != this.cboIndexCode.Value && !"".Equals(this.cboIndexCode.Value)
                    && !(status == ClsEnums.StatusSetting.YssCopy || status == ClsEnums.StatusSetting.YssEdit))
                {
                    loadSecBaseTreeL();
                    loadSecBaseTreeR();
                }
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }
        }

        /// <summary>
        /// 服务实例化
        /// </summary>
        private void initService()
        {
            if (null == service)
            {
                service = ServiceFactory.createService<IIndexStockService>();
            }
        }
        
        /// <summary>
        /// 列头信息
        /// </summary>
        private void initListHead()
        {
            showColumnList.Add("C_SEC_CODE");
            showColumnList.Add("C_SEC_MKT_CODE");
            showColumnList.Add("C_SEC_NAME");
            showColumnList.Add("C_MKT_CODE");
        }

        /// <summary>
        /// 加载证券列表
        /// </summary>
        private void loadSecBaseTreeL()
        {
            try
            {
                QueryRes res = service.getUnSelectedSecBase(this.cboIndexCode.Value, this.dtpBegin.getBeginDateStr);
                new TableListLoader().loadTable(this.tbSelectable, res, true, true, ClsEnums.KTableDataShowMode.ListMode, showColumnList);

                if (tbSelectable.Columns["C_MKT_CODE"] != null)
                {
                    tbSelectable.GroupBy(tbSelectable.Columns["C_MKT_CODE"]);
                }

                this.tbSelectable.Refresh();
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 加载已选中的证券列表
        /// </summary>
        private void loadSecBaseTreeR()
        {
            try
            {
                QueryRes res = service.getSelectedSecBase(this.cboIndexCode.Value, this.dtpBegin.getBeginDate.ToString("yyyy-MM-dd").Trim());
                new TableListLoader().loadTable(this.tbSelected, res, true, true, ClsEnums.KTableDataShowMode.ListMode, showColumnList);

                if (tbSelected.Columns["C_MKT_CODE"] != null)
                {
                    tbSelected.GroupBy(tbSelected.Columns["C_MKT_CODE"]);
                }

                this.tbSelected.Refresh();
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 加载最近未选中的证券列表
        /// </summary>
        private void loadLastSecBaseTreeL()
        {
            try
            {
                QueryRes res = service.getLastUnSelectedSecBase(this.cboIndexCode.Value, this.dtpBegin.getBeginDateStr);
                new TableListLoader().loadTable(this.tbSelectable, res, true, true, ClsEnums.KTableDataShowMode.ListMode, showColumnList);

                if (tbSelectable.Columns["C_MKT_CODE"] != null)
                {
                    tbSelectable.GroupBy(tbSelectable.Columns["C_MKT_CODE"]);
                }

                this.tbSelectable.Refresh();
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 加载最近已选中的证券列表
        /// </summary>
        private void loadLastSecBaseTreeR()
        {
            try
            {
                QueryRes res = service.getLastSelectedSecBase(this.cboIndexCode.Value, this.dtpBegin.getBeginDate.ToString("yyyy-MM-dd").Trim());
                new TableListLoader().loadTable(this.tbSelected, res, true, true, ClsEnums.KTableDataShowMode.ListMode, showColumnList);

                if (tbSelected.Columns["C_MKT_CODE"] != null)
                {
                    tbSelected.GroupBy(tbSelected.Columns["C_MKT_CODE"]);
                }

                this.tbSelected.Refresh();
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 新增时返回要操作的一组数据
        /// </summary>
        /// <returns>要操作的一组数据</returns>
        public override ArrayList yssGetObjListMVC()
        {
            ArrayList pojoList = new ArrayList();
            ////招商基金现场：录入指数成分股信息时，前台报错 addby wsm 2016-6-21 BUG #132938 【招商基金】录入指数成分股信息时，前台报错
            if (status == ClsEnums.StatusSetting.YssAdd)
            {
                Row row1 = null;
                RowCollection rows = new RowCollection();
                bool flag = false;
                foreach (Row row in this.tbSelected.CheckedRows)
                {
                    if (row.SubRows.Count > 0)
                    {
                        row1 = row;
                        flag = true;
                        rows.Add(row1);
                    }
                }


                if (flag)
                {
                    getSelectedData(rows, pojoList);
                }
                else
                {
                    getSelectedData(this.tbSelected.CheckedRows, pojoList);
                }
            }
            else
            {
                getSelectedData(this.tbSelected.Rows, pojoList);
            }
            ////end edit by wsm 2016-6-21 BUG #132938 【招商基金】录入指数成分股信息时，前台报错
            return pojoList;
        }

        /// <summary>
        /// 遍历需要保存的项目
        /// </summary>
        /// <param name="rows">遍历的集合</param>
        /// <param name="pojoList">数据集合</param>
        private void getSelectedData(RowCollection rows, ArrayList pojoList)
        {
            try
            {
                IndexStock pojo = null;
                SecBase sec = null;

                foreach (Row row in rows)
                {
                    ////保存自身节点
                    sec = row.Tag as SecBase;
                    if (sec != null)
                    {
                        pojo = new IndexStock();
                        pojo.C_INDEX_CODE = this.cboIndexCode.Value;
                        pojo.C_SEC_CODE = sec.C_SEC_CODE;
                        pojo.C_SEC_MKT_CODE = sec.C_SEC_MKT_CODE;
                        pojo.D_BEGIN = this.dtpBegin.getBeginDate;
                        pojoList.Add(pojo);
                    }

                    if (row.SubRows.Count != 0)
                    {
                        getSelectedData(row.SubRows, pojoList);
                    }
                }
            }
            catch (System.Exception e)
            {
                YssMessageBox.ShowCommonInfo("遍历已选项目异常");
            }
        }

        /// <summary>
        /// 获取list中选中记录，为界面元素赋值，显示数据
        /// </summary>
        /// <param name="pojo">BasePojo</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            try
            {
                IndexStock indexStock = yssGetBaseSelTypeItemMVC() as IndexStock;
                if (null != indexStock)
                {
                    this.dtpBegin.setDateTime(indexStock.D_BEGIN);
                    this.cboIndexCode.Value = indexStock.C_INDEX_CODE;
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 初始化控件状态 '修改'时：【指数代码】和【启用日期】不可修改
        /// </summary>
        public override void initControlStat()
        {
            base.initControlStat();

            if (status == ClsEnums.StatusSetting.YssEdit || status == ClsEnums.StatusSetting.YssBrow)
            {
                this.cboIndexCode.YssReadOnly = true;
                this.dtpBegin.Enabled = false;
            }
            else
            {
                this.cboIndexCode.YssReadOnly = false;
                this.dtpBegin.Enabled = true;
            }

            if (status == ClsEnums.StatusSetting.YssBrow)
            {
                this.tbSelectable.ReadOnly = true;
                this.tbSelected.ReadOnly = true;
                this.leftTorightAll.Enabled = false;
                this.leftTorightSelected.Enabled = false;
                this.rightToleftAll.Enabled = false;
                this.rightToleftSelected.Enabled = false;
            }
            else
            {
                this.tbSelectable.ReadOnly = false;
                this.tbSelected.ReadOnly = false;
                this.leftTorightAll.Enabled = true;
                this.leftTorightSelected.Enabled = true;
                this.rightToleftAll.Enabled = true;
                this.rightToleftSelected.Enabled = true;
            }

            if (this.status == ClsEnums.StatusSetting.YssAdd)
            {
                this.tbSelectable.Clear();
                this.tbSelected.Clear();
            }
        }

        /// <summary>
        /// 重写此方法,审核反审核重新获取选中的行
        /// </summary>
        /// <param name="status">当前窗体的打开状态</param>
        /// <returns>保存数据后后台返回的操作结果信息</returns>
        public override string yssFormOperationMVC(ClsEnums.StatusSetting status)
        {
            ////获取PojoList
            string result = "";
            ArrayList pojoList = null;
            if (status == ClsEnums.StatusSetting.YssAudit || status == ClsEnums.StatusSetting.YssUnAudit || status == ClsEnums.StatusSetting.YssDel)
            {
                pojoList = this.getListSelectedRows();
            }
            else
            {
                pojoList = yssGetDataObjMVC(pojoList);
            }

            if (pojoList == null)
            {
                pojoList = new ArrayList();
            }

            if (pojoList.Count > 0)
            {
                result = yssDoSetFormOperMVC(pojoList, status);
            }

            return result;
        }

        /// <summary>
        /// 获取浏览界面选中的行
        /// </summary>
        /// <returns>ArrayList</returns>
        private ArrayList getListSelectedRows()
        {
            ArrayList pojoList = null;
            if (0 != frmBaseViewList.tbMain.SelectedRows.Count)
            {
                pojoList = new ArrayList();
                foreach (Row row in frmBaseViewList.tbMain.SelectedRows)
                {
                    pojoList.Add(row.Tag);
                }
            }

            return pojoList;
        }

        /// <summary>
        /// 重写此方法,审核和反审核获取浏览界面的选中多行记录,去除统一获取Id
        /// </summary>
        /// <param name="pojoList">操作的数据对象</param>
        /// <param name="status">窗体的打开状态</param>
        /// <returns>保存数据后后台返回的操作结果信息</returns>
        protected override string yssDoSetFormOperMVC(ArrayList pojoList, ClsEnums.StatusSetting status)
        {
            string operResult = ""; 
            try
            {
                if (status == ClsEnums.StatusSetting.YssAudit || status == ClsEnums.StatusSetting.YssUnAudit)
                {
                    pojoList = this.getListSelectedRows();
                    foreach (AuditableParamPojo pojo in pojoList)
                    {
                        pojo.Modifier = ClsContext.currentUser.C_USER_CODE;
                        pojo.OperUser = ClsContext.currentUser.C_USER_CODE;
                    }
                }

                switch (status)
                {
                    case ClsEnums.StatusSetting.YssAdd:
                        foreach (object pojo in pojoList)
                        {
                            setAddOperPojoInfo((BasePojo)pojo);
                        }

                        operResult = dataService.insert(pojoList);
                        break;
                    case ClsEnums.StatusSetting.YssCopy:
                        foreach (object pojo in pojoList)
                        {
                            setAddOperPojoInfo((BasePojo)pojo);
                        }

                        operResult = dataService.insert(pojoList);
                        break;
                    case ClsEnums.StatusSetting.YssEdit:
                        foreach (object pojo in pojoList)
                        {
                            setEditOperPojoInfo((BasePojo)pojo);
                        }

                        operResult = dataService.updateById(pojoList);
                        break;
                    case ClsEnums.StatusSetting.YssDel:
                        foreach (object pojo in pojoList)
                        {
                            setDelOperPojoInfo((BasePojo)pojo);
                        }

                        operResult = dataService.deleteById(pojoList);
                        break;
                    case ClsEnums.StatusSetting.YssAudit:
                        DataFunction.setAuditStateByOperState(pojoList, status);
                        operResult = dataService.auditById(pojoList);
                        break;
                    case ClsEnums.StatusSetting.YssUnAudit:
                        DataFunction.setAuditStateByOperState(pojoList, status);
                        operResult = dataService.unAuditById(pojoList);
                        break;
                    case ClsEnums.StatusSetting.YssBrow:
                        break;
                }

            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }

            return operResult;
        }
    }
}


