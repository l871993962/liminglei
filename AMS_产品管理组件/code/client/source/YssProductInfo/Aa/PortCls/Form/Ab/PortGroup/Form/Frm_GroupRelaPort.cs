using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;

using FAST.Core.Communication.DataService;
using FAST.Common.Service.DataService.Base;
using FAST.Core.Exceptions;
using FAST.Core.Context;

using FAST.Platform.Right.Fun;
using FAST.Core.BaseControl.Fun;

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
using YssDevComponents.AdvTree;
using System.Collections;
using Yss.KTable.Models;
using Yss.KTable.Collections;
using FAST.Common.Service.DataService;
using YssProductInfo.Support.Ab.PortGroup.Service;
using YssProductInfo.Support.Ab.PortGroup.Pojo;
using YssProductInfo.Support.Ab.Port.Service;



namespace YssProductInfo.Ab.PortGroup.Form
{
    /// <summary>
    /// 群组关联组合
    /// Author:chenwenhai
    /// Time:20140519
    /// </summary>
    public partial class Frm_GroupRelaPort : FrmBaseSet
    {
        /// <summary>
        ///群组服务类
        /// </summary>
        private IPortGroupRelaService groupRelaService = null;

        /// <summary>
        /// 群组A区服务类
        /// </summary>
        private IPortGroupService groupService = null;

        /// <summary>
        /// 组合需要显示的列
        /// </summary>
        private ArrayList showColumnList = new ArrayList();

        /// <summary>
        /// 包含所有组合的返回对象QueryRes
        /// </summary>
        private QueryRes resPortAll = null;

        /// <summary>
        /// QueryRes 左侧加载
        /// </summary>
        private QueryRes resPortL = null;

        /// <summary>
        /// QureryRes 右侧加载
        /// </summary>
        private QueryRes resPortR = null;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_GroupRelaPort()
        {
            InitializeComponent();
            this.bUseMVCService = true;
            showColumnList.Add("C_PORT_NAME_ST");
            showColumnList.Add("C_PORT_NAME");
            showColumnList.Add("C_PORT_CODE");
            showColumnList.Add("C_DAT_CLS");
            resPortAll = loadPortData("", "", "");
            if (status == ClsEnums.StatusSetting.YssAdd)
            {
                loadPortTreeR("");
                loadPortTreeL("");
            }
        }


        /// <summary>
        /// 加载组合项目列表右侧
        /// </summary>
        /// <param name="c_group_code">c_group_code</param>
        private void loadPortTreeR(string c_group_code)
        {
            try
            {
                getGroupRelaService();
                List<BasePojo> basePojoList = new List<BasePojo>();
                List<FAST.Common.Service.Pojo.Port> portList = this.groupRelaService.querySelectablePortList(c_group_code);
                if (null != portList && portList.Count > 0)
                {
                    foreach (FAST.Common.Service.Pojo.Port port in portList)
                    {
                        basePojoList.Add(port);
                    }
                }

                resPortR = resPortAll;
                resPortR.DataList = basePojoList;
                loadPortTree(resPortR, this.tbSelectable);
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 加载组合项目列表左侧
        /// </summary>
        /// <param name="c_group_code">c_group_code</param>
        private void loadPortTreeL(string c_group_code)
        {
            try
            {
                getGroupRelaService();
                List<BasePojo> basePojoList = new List<BasePojo>();
                List<FAST.Common.Service.Pojo.Port> portList = this.groupRelaService.querySelectedPortList(c_group_code);
                if (null != portList && portList.Count > 0)
                {
                    foreach (FAST.Common.Service.Pojo.Port port in portList)
                    {
                        basePojoList.Add(port);
                    }
                }

                resPortL = resPortAll;
                resPortL.DataList = basePojoList;
                loadPortTree(resPortL, this.tbSelected);
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 获取群组服务类
        /// </summary>
        private void getGroupRelaService()
        {
            if (null == groupRelaService)
            {
                groupRelaService = ServiceFactory.createService<IPortGroupRelaService>();
            }

        }

        /// <summary>
        /// 获取群组A区服务类
        /// </summary>
        private void getGroupService()
        {
            if (null == groupService)
            {
                groupService = ServiceFactory.createService<IPortGroupService>();
            }

        }

        /// <summary>
        /// 以树形展现组合
        /// </summary>
        /// <param name="res">查询结果对象</param>
        /// <param name="loadDataTable">需要展示数据的table</param>
        public void loadPortTree(QueryRes res, Table loadDataTable)
        {
            new TableListLoader().loadTable(loadDataTable, res, false, false, ClsEnums.KTableDataShowMode.TreeMode, showColumnList);
            loadDataTable.Refresh();
        }


        /// <summary>
        /// 群组下拉框点击前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboGroup_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            e.Cancel = true;
            loadcboGroupData();
        }

        /// <summary>
        /// 加载下拉列表数据
        /// </summary>
        private void loadcboGroupData()
        {
            if (this.status != ClsEnums.StatusSetting.YssDel)
            {
                try
                {
                    QueryRes res = null;
                    this.cboGroup.Items.Clear();
                    Dictionary<string, string> paraDict = new Dictionary<string, string>();
                    List<BasePojo> portGroupList = new List<BasePojo>();
                    getGroupService();
                    res = groupService.getPortGroupListData(paraDict);
                    portGroupList = res.DataList;
                    this.cboGroup.DisplayName = "C_GROUP_NAME";
                    this.cboGroup.DisplayValue = "C_GROUP_CODE";
                    this.cboGroup.Parameter = "C_GROUP_NAME~C_GROUP_CODE";
                    this.cboGroup.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Value_Name;
                    foreach (YssProductInfo.Support.Ab.PortGroup.Pojo.PortGroup portgroupA in portGroupList)
                    {
                        this.cboGroup.Items.Add(new Yss.KRichEx.AutoFilter.Model.KTableEntity(portgroupA));
                    }
                }
                catch (Exception ex)
                {
                    throw new ClsBaseException(ex.Message);
                }
            }
        }

        /// <summary>
        /// 新增时返回要操作的一组数据
        /// </summary>
        /// <returns>要操作的一组数据</returns>
        public override ArrayList yssGetObjListMVC()
        {
            ArrayList pojoList = new ArrayList();
            List<FAST.Common.Service.Pojo.Port> portList = new List<FAST.Common.Service.Pojo.Port>();

            getSelectedData(this.tbSelected.Rows, portList);
            foreach (FAST.Common.Service.Pojo.Port port in portList)
            {
                PortGroupRela portGroup = new PortGroupRela();
                portGroup.C_GROUP_CODE = this.cboGroup.Value;
                portGroup.C_PORT_CODE = port.C_PORT_CODE;
                pojoList.Add(portGroup);
            }

            return pojoList;
        }

        /// <summary>
        /// 遍历需要保存的项目
        /// </summary>
        /// <param name="rows">遍历的集合</param>
        /// <param name="portList">数据集合</param>
        private void getSelectedData(RowCollection rows, List<FAST.Common.Service.Pojo.Port> portList)
        {
            try
            {
                foreach (Row row in rows)
                {
                    ////保存自身节点
                    if (row.Tag != null)
                    {
                        FAST.Common.Service.Pojo.Port port = row.Tag as FAST.Common.Service.Pojo.Port;
                        portList.Add(port);
                    }

                    if (row.SubRows.Count != 0)
                    {
                        getSelectedData(row.SubRows, portList);
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
                PortGroupRela portGroup = yssGetBaseSelTypeItemMVC() as PortGroupRela;
                if (null != portGroup)
                {
                    this.cboGroup.Value = portGroup.C_GROUP_CODE;
                    ////loadPortTreeL(portGroup.C_GROUP_CODE);
                    ////loadPortTreeR(portGroup.C_GROUP_CODE);
                }
                else
                {
                    loadPortTreeR("");
                }

            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }

        }


        /// <summary>
        /// 群组下拉框值改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboGroup_TextChanged(object sender, EventArgs e)
        {
            string c_group_code = this.cboGroup.Value;
            if (null != c_group_code && c_group_code.Trim().Length > 0)
            {
                loadPortTreeL(c_group_code);
                loadPortTreeR(c_group_code);
            }
        }

        /// <summary>
        /// 重写新增
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnNew_Click(object sender, EventArgs e)
        {
            base.btnNew_Click(sender, e);
            loadPortTreeL("");
            loadPortTreeR("");
        }

        /// <summary>
        /// 重写修改事件
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnEdit_Click(object sender, EventArgs e)
        {
            base.btnEdit_Click(sender, e);
            this.tbSelectable.ReadOnly = false;
            this.tbSelected.ReadOnly = false;
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
        /// 将选中项目从右侧移动到左侧 点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void rightToleftSelected_Click(object sender, EventArgs e)
        {
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

                //// 取消行的选择状态
                cancelCheckState(tempRow);

                this.tbSelected.Rows.Add(tempRow);
                setOwnTable(tempRow, tbSelected);
            }

            this.tbSelectable.Refresh();
            this.tbSelected.Refresh();
        }

        /// <summary>
        /// 将选中项目从左侧移动到右侧 点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void leftTorightSelected_Click(object sender, EventArgs e)
        {
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
                cancelCheckState(tempRow);

                this.tbSelectable.Rows.Insert(0, tempRow);
                setOwnTable(tempRow, tbSelectable);
            }

            this.tbSelectable.Refresh();
            this.tbSelected.Refresh();
        }

        /// <summary>
        /// 将所有项目从右侧移动到左侧 点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void rightToleftAll_Click(object sender, EventArgs e)
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

                //// 取消行的选择状态
                cancelCheckState(tempRow);

                this.tbSelected.Rows.Add(tempRow);
                setOwnTable(tempRow, tbSelected);
            }

            this.tbSelectable.Refresh();
            this.tbSelected.Refresh();
        }

        /// <summary>
        /// 将所有项目从左侧移动到右侧 点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void leftTorightAll_Click(object sender, EventArgs e)
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
                cancelCheckState(tempRow);

                this.tbSelectable.Rows.Add(tempRow);
                setOwnTable(tempRow, tbSelectable);
            }

            this.tbSelectable.Refresh();
            this.tbSelected.Refresh();
        }

        /// <summary>
        /// 获取组合数据
        /// </summary>
        /// <param name="dataClass">组合级别</param>
        /// <param name="dvportCode">层次</param>
        /// <param name="trcode">显示类型</param>
        /// <returns>查询结果</returns>
        public QueryRes loadPortData(string dataClass, string dvportCode, string trcode)
        {
            QueryRes res = null;
            YssProductInfo.Support.Ab.Port.Service.IPortDataService portSVC = null;
            try
            {
                portSVC = DataServiceFactory.createService<YssProductInfo.Support.Ab.Port.Service.IPortDataService>();
                res = portSVC.doPortFilterRes("true", dataClass, dvportCode, trcode);
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-100002", _formFun, ClsEnums.StatusSetting.YssBrow, ex));
            }

            return res;
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
        /// 更改控件的状态
        /// </summary>
        protected override void YssChangeControlState()
        {
            base.YssChangeControlState();

            if (status == ClsEnums.StatusSetting.YssBrow)
            {
                this.tbSelectable.ReadOnly = true;
                this.tbSelected.ReadOnly = true;
            }
        }

        /// <summary>
        /// 重写工具栏按钮控制方法
        /// </summary>
        protected override void YssInitTopButtonStat()
        {
            base.YssInitTopButtonStat();
            /*
             * modified by yll 20161116 移除删除按钮
             * BUG #143832 产品群组管理删除记录的时候默认删除排在第一条的记录
             */
            this.btnBar.setButtonUnVisibled(ClsButtonName.BtnDelete);
        }
    }
}


