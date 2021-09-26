﻿using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Platform.Menu.Pojo;
using FAST.Platform.Logger.Pojo;
using FAST.Platform.Safe.Pojo;
using FAST.Platform.Talk.Pojo;

using FAST.Platform.Safe.Fun;
using FAST.Common.Service.Interface;
using FAST.Core.Communication.DataService;
using FAST.Common.Service.DataService.Base;
using FAST.Core.Exceptions;
using FAST.Core.Context;

using FAST.Platform.Right.Fun;
using FAST.Core.BaseControl.Fun;
using FAST.Core.Bussiness.Pojo;

using FAST.Core.Resource;


using FAST.Platform.Menu.Service;
using FAST.Platform.Logger.Service;
using FAST.Platform.DataCopy.Service;
using FAST.Platform.Safe.Service;
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
using System.Collections;
using Yss.KRichEx.AutoFilter;
using Yss.KTable.Models;
using System.Text.RegularExpressions;
using Yss.KRichEx.AutoFilter.Model;
using FAST.Core.BaseControl;
using FAST.Platform.DataCopy.Pojo;
using FAST.Common.Service.DataService;
using Yss.Controls;
using YssProductInfo.Support.Ab.Port.Service;
using YssProductInfo.Support.PortPlan.Service;
using YssProductInfo.Support.Ab.PortGroup.Service;
using YssProductInfo.Support.PortPlan.Pojo;
using YssInformation.Support.Fun;

namespace YssProductInfo.Ab.Port.Form
{
    /// <summary>
    /// 功能简介：投资经理信息设置界面，显示数据和实现数据的增加，删除，修改，审核，反审核功能
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.07
    ///
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：lyh
    /// 修改日期：20101210
    /// 修改简介： 实现所有的方法
    ///  －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         返回对象错误直接抛出异常
    ///         
    /// －－－－修改记录－－－－
    /// 当前版本：V1.20.4.4
    /// 修改人：Yuntao Lau
    /// 修改日期：2015.09.22
    /// 修改简介:1.添加【全选】按钮，将界面分成两个半区，下半区加载继承的内容将放在界面的右半区;
    ///          2.勾选list界面中的某个记录，点击【复制创建】按钮，在界面中继承的内容默认全选;
    ///          3.修复继承出现的问题，被继承者某些继承内容的信息，新建的组合未能继承。
    /// </summary>
    public partial class Frm_PORT_S : FrmBaseSet
    {
        /// <summary>
        /// 定义组合的列表类
        /// </summary>
        private Frm_PORT_L frmPortL = null;

        /// <summary>
        /// 定义服务
        /// </summary>
        private IPortService iPortService = null;

        /// <summary>
        /// 是否复制创建状态
        /// </summary>
        private bool isCreateCopy = false;

        /// <summary>
        /// 首选方案描述
        /// add by zhd 2016-09-25
        /// STORY33239【南方基金】复制建仓参照组合能默认勾选继承内容即增加保存按钮
        /// </summary>
        private string planDesc = "组合首选方案";

        /// <summary>
        /// 方案
        /// add by zhd 2016-09-25
        /// STORY33239【南方基金】复制建仓参照组合能默认勾选继承内容即增加保存按钮
        /// </summary>
        private IPortPlanService portPlanService = null;

        /// <summary>
        /// 显示标识，控制ShowInfoMVC方法走另一支线 add by lixiang@ysstech.com
        /// STORY49196 TA清算数据清算参数校验界面需要添加相关审核的需求
        /// </summary>
        public bool showInfoMvc = false;
        /// <summary>
        /// add by lixiang@ysstech.com
        /// STORY49196 TA清算数据清算参数校验界面需要添加相关审核的需求
        /// </summary>
        public BasePojo basePojo = null;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_PORT_S()
        {
            bUseMVCService = true;
            InitializeComponent();
            if (this.isCreateCopy != true)
            {
                this.Size = new Size(499, 377);
                this.pnlRight.Hide();
                this.splitRight.Hide();
            }

            ////add by zhd 2016-09-25
            ////STORY33239【南方基金】复制建仓参照组合能默认勾选继承内容即增加保存按钮
            navigateItemMain.ContextMenuLoading += new ContextMenuLoadingEventHandler(this.NavigateItem_ContextMenuLoading);
            navigateItemMain.ContextMenuItemClick += new Yss.KNavigation.ContextMenuEventHandler(this.NavigateItem_ContextMenuItemClick);
            portPlanService = ServiceFactory.createService<IPortPlanService>();
        }
 
        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_PORT_S(FrmBaseSet frm, BasePojo basePojo)
        {
            bUseMVCService = true;
            InitializeComponent();
            if (this.isCreateCopy != true)
            {
                this.Size = new Size(499, 377);
                this.pnlRight.Hide();
                this.splitRight.Hide();
            }

            if (basePojo != null)
            {
                this.basePojo = basePojo;
            }

            ////add by zhd 2016-09-25
            ////STORY33239【南方基金】复制建仓参照组合能默认勾选继承内容即增加保存按钮
            navigateItemMain.ContextMenuLoading += new ContextMenuLoadingEventHandler(this.NavigateItem_ContextMenuLoading);
            navigateItemMain.ContextMenuItemClick += new Yss.KNavigation.ContextMenuEventHandler(this.NavigateItem_ContextMenuItemClick);
            portPlanService = ServiceFactory.createService<IPortPlanService>();
        }

        /// <summary>
        /// 重写审核方法 
        /// add by lixiang@ysstech.com
        /// STORY49196 TA清算数据清算参数校验界面需要添加相关审核的需求
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected override void btnAudit_Click(object sender, EventArgs e)
        {
            if (this.basePojo != null)
            {
                IPortService portService = ServiceFactory.createService<IPortService>();
                ArrayList list = new ArrayList();
                list.Add(this.basePojo);
                String result = portService.auditById(list);
                this.operAfterSave(result);
                this.btnBar.setAllButtonEnabled(false);
            }
            else
            {
                base.btnAudit_Click(sender, e);
            }
        }

        /// <summary>
        /// 设置参数
        /// </summary>
        /// <param name="isDedail">显示复制创建控件</param>
        /// <param name="port">参照组合</param>
        public void init(bool isDedail, FAST.Common.Service.Pojo.Port port)
        {
            ////this.isCreateCopy = isDedail;
            ////this.panelEx1.Visible = isDedail;
            this.Size = new Size(749, 377);
            this.pnlRight.Show();
            this.splitRight.Show();
            //// 对窗体控件一一赋值
            this.dtpExpirationDate.setDateTime(DateTime.Parse(port.D_CLOSE));
            this.dtpInceptionDate.setDateTime(DateTime.Parse(port.D_BUILD));
            this.dtpClear.setDateTime(port.D_CLEAR);
            this.cboAssetType.Value = port.C_DAT_CODE;
            this.cboAssetSort.Value = port.C_DAT_CLS;   
            this.cboCurrency.Value = port.C_DC_CODE;
            this.cboHolidays.Value = port.C_HDAY_CODE;
            this.cboPortLever.Value = port.C_DV_PORT_CODE;
            this.cboPort.Value = port.C_PORT_CODE_P;
            this.cboProdState.Items.Clear();
            this.cboProdState.IsFocused = false;
            this.cboProdState.Value = port.C_DV_PROD_STATE;
            this.cboPortCode.Value = port.C_PORT_CODE; 
            //// modified by liyanjun 2016-10-17 BUG #141897 【招商证券】产品信息里到期日和清盘日小于业务日期，业务日期一样能够进行账务处理
            ////dtpClear.yssEnabled = false;
            ////dtpClear.setRange(DateTime.Parse(port.D_CLOSE), Convert.ToDateTime("9998-12-31"));
            this.dtpExpirationDate.setDateTime(DateTime.Parse(port.D_CLOSE));
            ////edit by huangjin 2016-9-22 STORY #28950 参数复制功能优化-复制权限和群组以及产品数
            ////createTbRightMain(portCode);
        }

        /// <summary>
        /// 创建复制创建tbRightMain
        /// liuxiang 2015-12-17 STORY #26079 华泰证券：估值4.5_新组合自动添加到TA导出文件报表分组
        /// </summary>
        /// <param name="portCode">参照组合代码</param>
        private void createTbRightMain(string portCode)
        {
            IDataCopyService iDataCopyService = ServiceFactory.createService<IDataCopyService>();
            QueryRes res = iDataCopyService.queryCreateCopy(portCode);
            if (res != null)
            {
                ListHeadInfo listHeadInfo = new ListHeadInfo();
                listHeadInfo.Key = "C_DATA_NAME";
                res.ListHeadList = new List<ListHeadInfo>();
                res.ListHeadList.Add(listHeadInfo);
            }

            new TableListLoader().loadTable(tbRightMain, res, false, false, ClsEnums.KTableDataShowMode.TreeMode);
            ////chkCheckAll.Checked = true;
            ////add by zhd 2016-09-27
            ////STORY33239【南方基金】复制建仓参照组合能默认勾选继承内容即增加保存按钮
            loadSXFA();
            this.tbRightMain.Refresh();
        }

        /////// <summary>
        /////// 创建复制创建分页
        /////// </summary>
        ////private void createNewTable()
        ////{
        ////    IDataCopyService iDataCopyService = ServiceFactory.createService<IDataCopyService>();
        ////    QueryRes res = iDataCopyService.queryCreateCopy();
        ////    List<BasePojo> dataList = res.DataList;

        ////    if (null != dataList && dataList.Count > 0)
        ////    {
        ////        Dictionary<string, Row> treeRowDict = new Dictionary<string, Row>();
        ////        Row row = null;
        ////        CheckBoxCell cell = null;

        ////        foreach (BasePojo pojo in dataList)
        ////        {
        ////            CopyData copyData = pojo as CopyData;
        ////            if (copyData.C_DATA_CODE_P == "[root]")
        ////            {
        ////                Yss.Controls.TabPage tabPage = new Yss.Controls.TabPage();
        ////                tabPage.Tag = copyData;
        ////                tabPage.Text = copyData.C_DATA_NAME;
        ////                Table newTable = this.table2.Clone(true);
        ////                newTable.Rows.Clear();
        ////                int i = 0;

        ////                foreach (BasePojo pojo1 in dataList)
        ////                {
        ////                    if ((pojo1 as CopyData).C_DATA_CODE_P == copyData.C_DATA_CODE)
        ////                    {
        ////                        if (i == 0)
        ////                        {
        ////                            row = new Row();
        ////                            newTable.Rows.Add(row);
        ////                        }
        ////                        else if (i == 4)
        ////                        {
        ////                            row = new Row();
        ////                            newTable.Rows.Add(row);
        ////                            i = 0;
        ////                        }

        ////                        i++;

        ////                        ////科目代码
        ////                        cell = new CheckBoxCell();
        ////                        cell.Tag = pojo1;
        ////                        cell.Font = new Font("宋体", 9, FontStyle.Regular);
        ////                        cell.Text = (pojo1 as CopyData).C_DATA_NAME;
        ////                        row.Cells.Add(cell);

        ////                    }
        ////                }

        ////                this.table2 = newTable;
        ////                this.table2.Visible = true;
        ////                tabPage.Controls.Add(table2);
        ////                this.tabControl.TabPages.Add(tabPage);
        ////            }

        ////        }
        ////    }
        ////}

        /// <summary>
        /// 初始化界面控件
        /// </summary>
        public override void yssInitCtlAttr()
        {
            try
            {
                ////this.dtpInceptionDate.setDateTime(DateTime.Now);
                ////this.dtpExpirationDate.setDateTime(Convert.ToDateTime("9998-12-31"));
                ////this.cboAssetType.Value = "ASS_ZQTZJJ";    ////liuping 2011-03-30  BUG #1566 组合基本参数BUG,【资产类型】的默认值为：证券投资基金

                ////Cls_DAT_ASS_TYPE accType = null;

                // 由于下拉列表更改，下拉列表数据在控件间中配置，删除在初始的时候配置分级列表
                // 如果set窗体的状态是新增状态，在点击新增之前事件中已经得到组合代码
                // 然后将赋值给set窗体窗体中的组合代码
                // 获取快捷区单选的选中行的对象
                ////accType = this.frmBaseViewList.getSelectedRowTagMVC(accType) as Cls_DAT_ASS_TYPE;
                ////if (accType != null)
                ////{
                ////    this.cboAssetType.Value = accType.C_DAT_CODE; // 资产类型代码
                ////}

                ////注释掉，此类固化的配置，请写入Designer文件。张绍林-20151118
                ////cboAssetType.MethodInfo = new FAST.Core.BaseControl.ControlMethodInfo();
                ////cboAssetType.MethodInfo.MethodName = "getDataListByTypes";
                ////cboAssetType.MethodInfo.MethodParamValues = new string[] { "ASS," };

                ////cboAssetSort.MethodInfo = new FAST.Core.BaseControl.ControlMethodInfo();
                ////cboAssetType.MethodInfo.MethodName = "getDataListByTypes";
                ////cboAssetSort.MethodInfo.MethodParamValues = new string[] { "CLS," };

                frmPortL = frmBaseViewList as Frm_PORT_L;
                setButtonVisable();
                setCboProdStateCond();

                if (status == ClsEnums.StatusSetting.YssAdd)
                {
                    this.dtpExpirationDate.setDateTime(Convert.ToDateTime("9998-12-31"));
                    this.dtpClear.setDateTime(Convert.ToDateTime("9998-12-31"));
                }

            }
            catch (Exception ex)
            {
                ClsBaseException.DiscardException(ex);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFormLoadErr(this.Text));
            }
        }


        /// <summary>
        /// 高级查询
        /// </summary>
        /// <returns>返回查询结果</returns>
        public override string yssFilterOperation()
        {
            string strRe = "";
            try
            {
            }
            catch (Exception ex)
            {
                ClsBaseException.DiscardException(ex);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500041", _formFun, status));
            }

            return strRe;
        }

        /// <summary>
        /// 根据窗体状态执行相应的保存操作
        /// </summary>
        /// <param name="pojoList">操作的数据对象</param>
        /// <param name="status">窗体的打开状态</param>
        /// <returns>保存数据后后台返回的操作结果信息</returns>
        protected override string yssDoSetFormOperMVC(ArrayList pojoList, ClsEnums.StatusSetting status)
        {
            if (cboProdState.Value != null)
            {
                FAST.Core.Context.ClsEnums.PD_STATUS statusPd = (ClsEnums.PD_STATUS)Enum.Parse(typeof(ClsEnums.PD_STATUS), cboProdState.Value);
                if (statusPd == ClsEnums.PD_STATUS.PS6)
                {
                    if (status == ClsEnums.StatusSetting.YssEdit)
                    {
                        foreach (object pojo in pojoList)
                        {
                            setEditOperPojoInfo((BasePojo)pojo);
                        }
                    }

                    return iPortService.operQSQR(pojoList);
                }
                else
                {
                    return base.yssDoSetFormOperMVC(pojoList, status);
                }
            }
            else
            {
                return base.yssDoSetFormOperMVC(pojoList, status);
            }
        }

        /// <summary>
        /// 产品状态下拉框的数据加载事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboProdState_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            setCboProdStateCond();
        }

        /// <summary>
        /// 设置产品状态控件的值
        /// </summary>
        private void setCboProdStateCond()
        {
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo = new FAST.Core.BaseControl.ControlMethodInfo();
            controlMethodInfo.MethodName = "getDataListByKeys";
            if (frmPortL != null && frmPortL.Status_PD == ClsEnums.PD_STATUS.PS5)
            {
                controlMethodInfo.MethodParamValues = new string[] { "PS5,PS6," };
            }
            else if (frmPortL != null && frmPortL.Status_PD == ClsEnums.PD_STATUS.PS6)
            {
                controlMethodInfo.MethodParamValues = new string[] { "PS5,PS6," };
            }
            else
            {
                controlMethodInfo.MethodParamValues = new string[] { "PS1,PS2,PS3,PS4" };
            }

            this.cboProdState.MethodInfo = controlMethodInfo;
            //// add by xuhanbing 20161231 STORY #37280 募集期产品状态无法修改 新增默认当前状态
            if (status == ClsEnums.StatusSetting.YssAdd && frmPortL != null)
            {
                this.cboProdState.Value = frmPortL.Status_PD.ToString();
            }
        }


        /// <summary>
        /// 设置按钮是否显示
        /// </summary>
        private void setButtonVisable()
        {
            if (frmPortL != null && frmPortL.Status_PD == ClsEnums.PD_STATUS.PS5)
            {
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnNew, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCopy, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnEdit, false);

                ////add by liuxiang 2013/8/29 bug9276
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnDelete, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnAudit, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnUnAudit, false);
            }
            else if (frmPortL != null && frmPortL.Status_PD == ClsEnums.PD_STATUS.PS6)
            {
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnNew, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCopy, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnEdit, false);

                ////add by liuxiang 2013/8/29 bug9276
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnDelete, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnAudit, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnUnAudit, false);
            }
            else if (frmPortL != null && frmPortL.Status_PD == ClsEnums.PD_STATUS.PS4)
            {
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnNew, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCopy, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnEdit, true);

                ////add by liuxiang 2013/8/29 bug9276
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnDelete, true);
                if (_formFun.N_CHECK == 1)
                {
                    btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnAudit, true);
                    btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnUnAudit, true);
                }
            }
            else if (frmPortL != null && frmPortL.Status_PD == ClsEnums.PD_STATUS.PS1)
            {
                ////edit by liuxiang  2013/11/5   BUG #82890 组合基本参数：list工具栏有误；增加产品库分类
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnNew, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCopy, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnEdit, true);

                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnDelete, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnAudit, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnUnAudit, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnSave, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnRecall, false);
            }
            else if (isCreateCopy)
            {
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnNew, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCopy, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnEdit, true);

                ////add by liuxiang 2013/8/29 bug9276
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnDelete, true);
                if (_formFun.N_CHECK == 1)
                {
                    btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnAudit, true);
                    btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnUnAudit, true);
                }
            }
        }

        /////// <summary>
        /////// 封装界面元素为pojo对象
        /////// 修改人zhuangyuchen 
        /////// 修改日期2011-4-14
        /////// bug单号：1674
        /////// </summary>
        /////// <returns>由界面元素组成的对象</returns>
        ////public override ClsBasePojo yssFaceInfoToObj()
        ////{
        ////    PortAssTree port = null;
        ////    try
        ////    {
        ////        port = new PortAssTree();

        ////        // 判断list选中界面是否有选中数据，在修改时获取原数据的值
        ////        if (null != this.yssGetBaseSelTypeItem())
        ////        {
        ////            port.OldC_PORT_CODE = ((PortAssTree)this.yssGetBaseSelTypeItem()).C_PORT_CODE;
        ////        }

        ////        port.C_PORT_CODE = this.txtPortCode.Text;
        ////        port.C_PORT_NAME = this.txtPortCNName.Text;
        ////        port.C_DAT_CODE = this.cboAssetType.Value;
        ////        port.C_PORT_NAME_EN = this.txtPortENName.Text;
        ////        port.C_PORT_NAME_ST = this.txtPortShortName.Text; // 组合简称
        ////        port.C_PORT_CODE_P = this.cboPort.Value;
        ////        port.C_DV_PORT_CODE = this.cboPortLever.Value;
        ////        port.C_HDAY_CODE = this.cboHolidays.Value;
        ////        port.C_ASS_CODE = this.txtAssetCode.Text;
        ////        if (this.dtpExpirationDate.getBeginDateStr.Equals("0001-01-01"))
        ////        {
        ////            ////  throw new ClsBaseException("请输入终止日期");
        ////            throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("001", _formFun, ClsEnums.StatusSetting.YssSave));
        ////        }
        ////        else
        ////        {
        ////            port.D_CLOSE = this.dtpExpirationDate.getBeginDateStr;
        ////        }

        ////        if (this.dtpInceptionDate.getBeginDateStr.Equals("0001-01-01"))
        ////        {
        ////            //// throw new ClsBaseException("请输入开始日期");
        ////            throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("002", _formFun, ClsEnums.StatusSetting.YssSave));
        ////        }
        ////        else
        ////        {
        ////            port.D_BUILD = this.dtpInceptionDate.getBeginDateStr;
        ////        }

        ////        port.C_DC_CODE = this.cboCurrency.Value;

        ////    }
        ////    catch (Exception ye)
        ////    {
        ////        throw new ClsBaseException(ye.Message);
        ////    }

        ////    return port;
        ////}

        /// <summary>
        /// 封装界面元素为pojo对象
        /// 修改人zhuangyuchen 
        /// 修改日期2011-4-14
        /// bug单号：1674
        /// </summary>
        /// <returns>由界面元素组成的对象</returns>
        public override AuditableParamPojo faceInfoToObjMVC()
        {
            FAST.Common.Service.Pojo.Port port = null;
            try
            {
                port = new FAST.Common.Service.Pojo.Port();

                port.C_PORT_CODE = this.txtPortCode.Text;
                port.C_PORT_NAME = this.txtPortCNName.Text;
                port.C_DAT_CODE = this.cboAssetType.Value;
                port.C_DAT_CLS = this.cboAssetSort.Value;       ////add by zhaoxianlin 20130508 STORY #3659 关于资产类型改造需求
                port.C_PORT_NAME_EN = this.txtPortENName.Text;
                port.C_PORT_NAME_ST = this.txtPortShortName.Text; // 组合简称
                //// add by caowei bug#7483 关于上级组合的bug
                if (this.cboPort.Value != null)               
                {
                    port.C_PORT_CODE_P = this.cboPort.Value;
                }
                else
                {
                    port.C_PORT_CODE_P = " ";
                }
               
                port.C_DV_PORT_CODE = this.cboPortLever.Value;
                port.C_HDAY_CODE = this.cboHolidays.Value;
                //// port.C_ASS_CODE = this.txtAssetCode.Text;
                ////modified by dingshalu  2016-7-14 BUG #134121 中信证券-资产代码中有空格存在时导致TA申赎数据读取不进来
                port.C_ASS_CODE = (this.txtAssetCode.Text).Trim();             
                
                if (this.dtpExpirationDate.getBeginDateStr.Equals("0001-01-01"))
                {
                    ////  throw new ClsBaseException("请输入终止日期");
                    ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("001", _formFun, ClsEnums.StatusSetting.YssSave));
                    TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                    transferErrorMessage.MESSAGESLINK = new List<string>();
                    /* 组装提示信息对象ErrorMessage */
                    string errorMess = ClsRetInfoDealer.getExtWarns("001", _formFun, ClsEnums.StatusSetting.YssSave);
                    transferErrorMessage.MESSAGESLINK.Add(errorMess);
                    throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                }
                else
                {
                    port.D_CLOSE = this.dtpExpirationDate.getBeginDateStr;
                }

                if (this.dtpInceptionDate.getBeginDateStr.Equals("0001-01-01"))
                {
                    //// throw new ClsBaseException("请输入开始日期");
                    ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("002", _formFun, ClsEnums.StatusSetting.YssSave));
                    TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                    transferErrorMessage.MESSAGESLINK = new List<string>();
                    /* 组装提示信息对象ErrorMessage */
                    string errorMess = ClsRetInfoDealer.getExtWarns("002", _formFun, ClsEnums.StatusSetting.YssSave);
                    transferErrorMessage.MESSAGESLINK.Add(errorMess);
                    throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                }
                else
                {
                    port.D_BUILD = this.dtpInceptionDate.getBeginDateStr;
                }

                port.C_DC_CODE = this.cboCurrency.Value;

                ////产品状态
                port.C_DV_PROD_STATE = this.cboProdState.Value == null ? " " : this.cboProdState.Value;
                //// 到期清算日期byleeyu20130807
                port.D_CLEAR = this.dtpClear.getBeginDate;

            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return port;
        }


        /////// <summary>
        /////// 获取list中选中记录，为界面元素赋值，显示数据
        /////// </summary>
        ////public override void yssShowInfo()
        ////{
        ////    try
        ////    {
        ////        PortAssTree port = (PortAssTree)this.yssGetBaseSelTypeItem();
        ////        if (port == null)
        ////        {
        ////            return;
        ////        }

        ////        // 对窗体控件一一赋值
        ////        this.txtPortCode.Text = port.C_PORT_CODE;
        ////        this.txtPortCNName.Text = port.C_PORT_NAME;
        ////        if (!"null".Equals(port.C_PORT_NAME_EN))
        ////        {
        ////            this.txtPortENName.Text = port.C_PORT_NAME_EN;
        ////        }
                
        ////        this.txtPortShortName.Text = port.C_PORT_NAME_ST;
        ////        this.dtpExpirationDate.setDateTime(DateTime.Parse(port.D_CLOSE));
        ////        this.dtpInceptionDate.setDateTime(DateTime.Parse(port.D_BUILD));
        ////        this.cboAssetType.Value = port.C_DAT_CODE;
        ////        this.cboCurrency.Value = port.C_DC_CODE;
        ////        this.cboHolidays.Value = port.C_HDAY_CODE;
        ////        if (!"null".Equals(port.C_PORT_CODE_P))
        ////        {
        ////            this.cboPort.Value = port.C_PORT_CODE_P;
        ////        }
                
        ////        this.cboPortLever.Value = port.C_DV_PORT_CODE;
               
        ////        this.txtAssetCode.Text = port.C_ASS_CODE;
        ////    }
        ////    catch (Exception ex)
        ////    {
        ////        throw new ClsBaseException(ex.Message);
        ////    }
        ////}

        /// <summary>
        /// 获取list中选中记录，为界面元素赋值，显示数据
        /// </summary>
        /// <param name="pojo">数据对象</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            if (this.status == ClsEnums.StatusSetting.YssAdd)
            {
                return;
            }

            try
            {
                if (this.showInfoMvc == true)
                {
                    FAST.Common.Service.Pojo.Port port = (FAST.Common.Service.Pojo.Port)pojo;
                    if (port == null)
                    {
                        return;
                    }

                    //// By Jinghehe 2014-9-14 bug 100953
                    ////ctlCboClear();
                    //// 对窗体控件一一赋值
                    this.txtPortCode.Text = port.C_PORT_CODE;
                    this.txtPortCNName.Text = port.C_PORT_NAME;
                    if (!"null".Equals(port.C_PORT_NAME_EN))
                    {
                        this.txtPortENName.Text = port.C_PORT_NAME_EN;
                    }

                    this.txtPortShortName.Text = port.C_PORT_NAME_ST;
                    this.dtpExpirationDate.setDateTime(DateTime.Parse(port.D_CLOSE));
                    this.dtpInceptionDate.setDateTime(DateTime.Parse(port.D_BUILD));
                    this.dtpClear.setDateTime(port.D_CLEAR);
                    this.cboAssetType.Value = port.C_DAT_CODE;
                    this.cboAssetSort.Value = port.C_DAT_CLS;   ////add by zhaoxianlin 20130508 STORY #3659 关于资产类型改造需求
                    this.cboCurrency.Value = port.C_DC_CODE;
                    this.cboHolidays.Value = port.C_HDAY_CODE;
                    ////if (!"null".Equals(port.C_PORT_CODE_P) && port.C_PORT_CODE_P.Trim().Length != 0)
                    ////{

                    ////}

                    this.cboPortLever.Value = port.C_DV_PORT_CODE;
                    this.cboPort.Value = port.C_PORT_CODE_P;

                    this.txtAssetCode.Text = port.C_ASS_CODE;
                    ////产品状态
                    this.cboProdState.Items.Clear();
                    this.cboProdState.IsFocused = false;
                    this.cboProdState.Value = port.C_DV_PROD_STATE;
                }
                else
                {
                    FAST.Common.Service.Pojo.Port port = (FAST.Common.Service.Pojo.Port)this.yssGetBaseSelTypeItemMVC();
                    if (port == null)
                    {
                        return;
                    }

                    //// By Jinghehe 2014-9-14 bug 100953
                    ////ctlCboClear();
                    //// 对窗体控件一一赋值
                    this.txtPortCode.Text = port.C_PORT_CODE;
                    this.txtPortCNName.Text = port.C_PORT_NAME;
                    if (!"null".Equals(port.C_PORT_NAME_EN))
                    {
                        this.txtPortENName.Text = port.C_PORT_NAME_EN;
                    }

                    this.txtPortShortName.Text = port.C_PORT_NAME_ST;
                    this.dtpExpirationDate.setDateTime(DateTime.Parse(port.D_CLOSE));
                    this.dtpInceptionDate.setDateTime(DateTime.Parse(port.D_BUILD));
                    this.dtpClear.setDateTime(port.D_CLEAR);
                    this.cboAssetType.Value = port.C_DAT_CODE;
                    this.cboAssetSort.Value = port.C_DAT_CLS;   ////add by zhaoxianlin 20130508 STORY #3659 关于资产类型改造需求
                    this.cboCurrency.Value = port.C_DC_CODE;
                    this.cboHolidays.Value = port.C_HDAY_CODE;
                    ////if (!"null".Equals(port.C_PORT_CODE_P) && port.C_PORT_CODE_P.Trim().Length != 0)
                    ////{

                    ////}

                    this.cboPortLever.Value = port.C_DV_PORT_CODE;
                    this.cboPort.Value = port.C_PORT_CODE_P;

                    this.txtAssetCode.Text = port.C_ASS_CODE;
                    ////产品状态
                    this.cboProdState.Items.Clear();
                    this.cboProdState.IsFocused = false;
                    this.cboProdState.Value = port.C_DV_PROD_STATE;
                }
                
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }


        /// <summary>
        /// 判断终止日期是否大于或等于成立日期  add by caozhonghu 2011.3.14
        /// </summary>
        /// <returns>返回验证结果</returns>
        private bool validateDateInput()
        {
            bool isPass = true;
            ////刘良 2012-2-4-9 两个日期格式不对
            if (ClsFunction.sub(this.dtpExpirationDate.getEndDate, this.dtpInceptionDate.getEndDate) < 0)
            {
                isPass = false;
            }

            return isPass;
        }


        /// <summary>
        /// 保存操作前的事件处理，进行业务规则校验
        /// </summary>
        /// <param name="sender">请求对象</param>
        /// <param name="e">事件对象</param>
        private void Frm_PORT_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        {
            if (!validateDateInput())
            {
                e.IsCancel = true; // tanwenjie 2011-6.29 阻止进入保存操作 
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarns("003", _formFun, status));
            }

            if (validatePortCode())
            {
                e.IsCancel = true; // 屏蔽掉执行保存事件

                ////YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarns("009", _formFun, status));
                Yss.CommonLib.ShowMessage("组合代码已经在群组中存在，请检查！");
                ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("001", _formFun, ClsEnums.StatusSetting.YssSave));
            }

            //// bug 82197 By jinghehe 2013-10-28
            ////if (this.dtpClear.getBeginDate.CompareTo(this.dtpExpirationDate.getBeginDate) == -1)
            ////{
            ////    this.dtpClear.setDateTime(this.dtpExpirationDate.getBeginDate);
            ////}

            ////liuxiang 2015-7-16 BUG #115824 [紧急][招商证券]资产代码修改的问题
            if (status == ClsEnums.StatusSetting.YssEdit || status == ClsEnums.StatusSetting.YssCopy || status == ClsEnums.StatusSetting.YssAdd)
            {
                if (iPortService == null)
                {
                    iPortService = ServiceFactory.createService<IPortService>();
                }

                List<FAST.Common.Service.Pojo.Port> list = iPortService.getTheSameAssCodeList(this.txtPortCode.Text, this.txtAssetCode.Text);
                if (list != null && list.Count > 0)
                {
                    YssMessageBox.currentForm = null; 
                    if (YssMessageBox.ShowQuestion("资产代码【" + list[0].C_ASS_CODE + "】与组合【" + list[0].C_PORT_CODE + "】的资产代码重复，是否保存？", "警告") != DialogResult.Yes)
                    {
                        e.IsCancel = true;
                    }
                }
            }
        }

        /// <summary>
        /// 1.检查组合代码是否在群组中已经存在，如果存在则不能保存 
        /// add by chenwenhai 20140605
        /// </summary>
        /// <returns>bool</returns>
        private bool validatePortCode()
        {
            string groupCode = this.txtPortCode.Text;
            bool isHasData = false;
            YssProductInfo.Support.Ab.PortGroup.Service.IPortGroupDataService portGroupDataService = DataServiceFactory.createService<YssProductInfo.Support.Ab.PortGroup.Service.IPortGroupDataService>();
            if ("true".Equals(portGroupDataService.checkPortCode(groupCode)))
            {
                isHasData = true;
            }

            return isHasData;
        }

        /// <summary>
        /// 数据改变时候对
        /// 组合控件进行控制
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboPortLever_SelectedValueChanged(object sender, EventArgs e)
        {
            ////modified by yhm 20160825 BUG #138265 【产品基本信息】复制创建功能界面的组合数据不能审核
            if (status == ClsEnums.StatusSetting.YssBrow)
            {
                return;
            }

            string ctlValue = "";
            try
            {
                this.cboPort.Items.Clear();
                ctlValue = cboPortLever.Value;
                //// Cause: BUG7143
                //// 这里加上空值处理比较保险
                //// Update by Huxingtao    2013-2-25
                if (ctlValue != null)
                {
                    this.cboPort.Text = "";
                    this.cboPort.Value = null;
                    ////若为“组合层”，上级组合只可加载“计划层”组合；
                    if ("PORT_LAYER".Equals(this.cboPortLever.Value.ToString()))
                    {
                        this.cboPort.YssReadOnly = false;
                    }
                    else if ("UNIT_LAYER".Equals(this.cboPortLever.Value.ToString()))
                    {
                        ////　若为“单元层”，上级组合只可加载“组合层”组合；
                        this.cboPort.YssReadOnly = false;
                    }
                    else
                    {
                        ////若为“组合层”，上级组合只可加载“计划层”组合；
                        this.cboPort.Value = null;
                        this.cboPort.YssReadOnly = true;
                        this.cboPort.Text = "";
                        ////this.cboPort.Items.Clear();
                    }
                }

                ////ctlCboClear();
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 窗体加载方法
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_PORT_S_Load(object sender, EventArgs e)
        {
            if (status == ClsEnums.StatusSetting.YssBrow)
            {
                return;
            }

            string portLevel = "";
            try
            {
                portLevel = cboPortLever.Value;
                if (portLevel != null)
                {
                    ////若为“组合层”，上级组合只可加载“计划层”组合；
                    if ("PORT_LAYER".Equals(this.cboPortLever.Value.ToString()))
                    {
                        this.cboPort.YssReadOnly = false;
                    }
                    else if ("UNIT_LAYER".Equals(this.cboPortLever.Value.ToString()))
                    {
                        ////　若为“单元层”，上级组合只可加载“组合层”组合；
                        this.cboPort.YssReadOnly = false;
                    }
                    else
                    {
                        ////若为“组合层”，上级组合只可加载“计划层”组合；
                        this.cboPort.Value = null;
                        this.cboPort.YssReadOnly = true;
                        this.cboPort.Text = "";
                    }
                }
                else 
                {
                    this.cboPort.Value = null;
                    this.cboPort.YssReadOnly = true;
                    this.cboPort.Text = "";
                }

                ////ctlCboClear();
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }

            ////Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            ////dataService = (IServiceBus)ServiceFactory.createService(serviceType);
            iPortService = ServiceFactory.createService<IPortService>();
            dataService = iPortService;
        }

        /// <summary>
        /// 重写状态控制
        /// </summary>
        public override void initControlStat()
        {
            base.initControlStat();
            string portLevel = "";
            
            if (status != ClsEnums.StatusSetting.YssBrow)
            {
                portLevel = cboPortLever.Value;

                if (portLevel != null)
                {
                    if (("PORT_LAYER".Equals(this.cboPortLever.Value.ToString()) || "UNIT_LAYER".Equals(this.cboPortLever.Value.ToString())))
                    {
                        this.cboPort.YssReadOnly = false;
                    }
                    else
                    {
                        this.cboPort.YssReadOnly = true;
                    }
                }
                else 
                {
                    this.cboPort.YssReadOnly = true;
                }
                
            }

            ////ctlCboClear();
            setButtonVisable();

            ////BUG #115824 [紧急][招商证券]资产代码修改的问题
            if (status == ClsEnums.StatusSetting.YssEdit)
            {
                this.txtPortCode.YssReadOnly = true;
            }
        }

        /// <summary>
        /// 在保存前检查界面元素的输入是否合法
        /// </summary>
        /// <returns>是否通过检查</returns>
        protected override bool checkInput()
        {
            bool result = base.checkInput();
            if (isCreateCopy && result)
            {
                result = this.clsInterface.checkControlsInput(this.tbRightFilter);
            }

            return result;    
        }

        /// <summary>
        /// set界面保存数据方法
        /// </summary>
        /// <param name="status">当前窗体的打开状态</param>
        /// <returns>保存数据后后台返回的操作结果信息</returns>
        public override string yssFormOperation(ClsEnums.StatusSetting status)
        {
            string operResult = base.yssFormOperation(status);
            ClsRetInfo retInfo = ClsRetInfoDealer.getReturnInfo(operResult);

            ////STORY # 31359 复制参数出现重复数据
            //// BUG #150573 复制创建组合勾选运营费用设置，运营费用设置数量翻倍 zhanghualin 2017-01-19
            if (retInfo.operRes == "Success" && (status == ClsEnums.StatusSetting.YssAdd|| status == ClsEnums.StatusSetting.YssCopy))
            {
               createCopyExecute();
            }

            return operResult;
        }

        /// <summary>
        /// 创建复制执行方法
        /// </summary>
        /// <returns>返回的参数</returns>
        private string createCopyExecute()
        {
            List<BasePojo> list = new List<BasePojo>();
            ClsTreeLeafList ctll = new ClsTreeLeafList();

            if (this.cboPortCode.Value == null || this.cboPortCode.Value.Trim().Length == 0)
            {
                return " ";
            }

            if (this.txtPortCode.Text == null || this.txtPortCode.Text.Trim().Length == 0)
            {
                return " ";
            }

            ////foreach (Yss.Controls.TabPage tempPage in tabControl.TabPages)
            ////{
            ////    if (tempPage.Controls.Count > 0)
            ////    {
            ////        if (tempPage.Controls[0] is Table)
            ////        {
            ////            Table tempTable = tempPage.Controls[0] as Table;
            ////            ctll.getCheckedLeafCell(tempTable.Rows, list);
            ////        }
            ////    }
            ////}

            ////if (list.Count < 1)
            ////{
            ////    return " ";
            ////}

            string c_Result = " ";
            IDataCopyService iDataCopyService = ServiceFactory.createService<IDataCopyService>();
            c_Result = iDataCopyService.exe(buildOperConds());


            return c_Result;
        }

        /// <summary>
        /// 1111
        /// </summary>
        /// <returns>返回的参数</returns>
        private Dictionary<string, string> buildOperConds()
        {
            Dictionary<string, string> dict = new Dictionary<string, string>();
            dict.Add("C_OPER_CODE", "");
            dict.Add("C_PORT_CODE", this.cboPortCode.Value);
            dict.Add("PORT_CODE_LIST", txtPortCode.Text);
            dict.Add("DATA_SVC_LIST", getDataCodeList());
            dict.Add("C_FUN_CODE", _formFun.C_FUN_CODE); //// 添加功能菜单byleeyu20131230
            ////传给后台当前操作用户和岗位，权限赋值时则过滤当前用户和岗位权限，  BUG #103304 2014-11-09 LY
            ////dict.Add("CurrentUser", ClsContext.currentUser.C_USER_CODE);
            ////dict.Add("CurrentUserPost", ClsContext.currentUserPostCodes);
            return dict;
        }

        /////// <summary>
        /////// 获取选中的所以复选项
        /////// </summary>
        /////// <returns>s</returns>
        ////private string getDataCodeList()
        ////{
        ////    List<BasePojo> list = new List<BasePojo>();
        ////    YssSysMgr.Fun.ClsTreeLeafList ctll = new YssSysMgr.Fun.ClsTreeLeafList();

        ////    foreach (Yss.Controls.TabPage tempPage in tabControl.TabPages)
        ////    {
        ////        if (tempPage.Controls.Count > 0)
        ////        {
        ////            if (tempPage.Controls[0] is Table)
        ////            {
        ////                Table tempTable = tempPage.Controls[0] as Table;
        ////                ctll.getCheckedLeafCell(tempTable.Rows, list);
        ////            }
        ////        }
        ////    }

        ////    string dataCodeList = "";

        ////    if (list.Count > 0)
        ////    {
        ////        foreach (BasePojo pojo in list)
        ////        {
        ////            CopyData copyData = pojo as CopyData;
        ////            dataCodeList += copyData.C_DATA_CODE + "=" + copyData.C_SERVICE_CODE + "&";
        ////        }
        ////    }

        ////    return dataCodeList;
        ////}

        /// <summary>
        /// 获取选中的复选项
        /// </summary>
        /// <returns>s</returns>
        private string getDataCodeList()
        {
            string dataCodeList = "";
            foreach (Row row in tbRightMain.CheckedRows)
            {
                // modify by yewenke 2016-03-11 复制产品出错
                // modified by yhm 20160815 C_DV_STATE值为0时表示非根节点，仅最明细节点进行复制
                if (row.Tag != null && !string.Equals((row.Tag as CopyData).C_DATA_CODE_P.ToLower(), "[root]") && !string.Equals((row.Tag as CopyData).C_DV_STATE, "0"))
                {
                    CopyData copyData = row.Tag as CopyData;
                    dataCodeList += copyData.C_DATA_CODE + "=" + copyData.C_SERVICE_CODE + "&";
                }
            }

            return dataCodeList;
        }

        /// <summary>
        /// 上级组合
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboPort_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            string portlevel = "";
            try
            {
                e.IsCancel = true;
                portlevel = cboPortLever.Value;
                if (portlevel != null)
                {
                    if (null != this.cboPortLever.Value && "UNIT_LAYER".Equals(this.cboPortLever.Value.ToString()))
                    {
                        ////　若为“单元层”，上级组合只可加载“组合层”组合；
                        if (status != ClsEnums.StatusSetting.YssBrow)
                        {
                            this.cboPort.YssReadOnly = false;
                        }

                        YssProductInfo.Support.Ab.Port.Service.IPortDataService portService = DataServiceFactory.createService<YssProductInfo.Support.Ab.Port.Service.IPortDataService>();
                        ////modified by liyanjun 20140819 bug99452  
                        List<BasePojo> list = portService.getPortListByDvPortCode("PORT_LAYER");
                        foreach (BasePojo pojo in list)
                        {
                            KTableEntity entity = new KTableEntity((FAST.Common.Service.Pojo.Port)pojo);
                            e.Collection.Add(entity);
                        }
                    }
                    else if (null != this.cboPortLever.Value && "PORT_LAYER".Equals(this.cboPortLever.Value.ToString()))
                    {
                        ////若为“组合层”，上级组合只可加载“计划层”组合；
                        if (status != ClsEnums.StatusSetting.YssBrow)
                        {
                            this.cboPort.YssReadOnly = false;
                        }

                        YssProductInfo.Support.Ab.Port.Service.IPortDataService portService = DataServiceFactory.createService<YssProductInfo.Support.Ab.Port.Service.IPortDataService>();
                        List<BasePojo> list = portService.getPortListByDvPortCode("PLAN_LAYER");
                        foreach (BasePojo pojo in list)
                        {
                            KTableEntity entity = new KTableEntity((FAST.Common.Service.Pojo.Port)pojo);
                            e.Collection.Add(entity);
                        }
                    }
                    else
                    {
                        ////若为“计划层”，不加载上级组合；
                        this.cboPort.Value = null;
                        this.cboPort.YssReadOnly = true;
                        this.cboPort.Text = "";
                    }
                }
                else
                {
                    ////若为“计划层”，不加载上级组合；
                    this.cboPort.Value = null;
                    this.cboPort.YssReadOnly = true;
                    this.cboPort.Text = "";
                }

            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /////// <summary>
        /////// 上级组合点击下时
        /////// </summary>
        /////// <param name="sender">sender</param>
        /////// <param name="e">e</param>
        ////private void cboPort_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        ////{
        ////    string portlevel = "";
        ////    try
        ////    {
        ////        portlevel = cboPortLever.Value;

        ////        if (portlevel != null)
        ////        {
        ////            if (null != this.cboPortLever.Value && "UNIT_LAYER".Equals(this.cboPortLever.Value.ToString()))
        ////            {
        ////                ////　若为“单元层”，上级组合只可加载“组合层”组合；
        ////                if (status != ClsEnums.StatusSetting.YssBrow)
        ////                {
        ////                    this.cboPort.YssReadOnly = false;
        ////                }

        ////                this.cboPort.MethodInfo.MethodName = "getPortListByDvPortCode";
        ////                this.cboPort.MethodInfo.MethodParamValues = new string[] { "PORT_LAYER" };
        ////            }
        ////            else if (null != this.cboPortLever.Value && "PORT_LAYER".Equals(this.cboPortLever.Value.ToString()))
        ////            {
        ////                ////若为“组合层”，上级组合只可加载“计划层”组合；
        ////                if (status != ClsEnums.StatusSetting.YssBrow)
        ////                {
        ////                    this.cboPort.YssReadOnly = false;
        ////                }

        ////                this.cboPort.MethodInfo.MethodName = "getPortListByDvPortCode";
        ////                this.cboPort.MethodInfo.MethodParamValues = new string[] { "PLAN_LAYER" };
        ////            }
        ////            else
        ////            {
        ////                ////若为“计划层”，不加载上级组合；
        ////                this.cboPort.Value = null;
        ////                this.cboPort.YssReadOnly = true;
        ////                this.cboPort.Text = "";
        ////            }
        ////        }
        ////        else
        ////        {
        ////            ////若为“计划层”，不加载上级组合；
        ////            this.cboPort.Value = null;
        ////            this.cboPort.YssReadOnly = true;
        ////            this.cboPort.Text = "";
        ////        }

        ////    }
        ////    catch (Exception ex)
        ////    {
        ////        throw new ClsBaseException(ex.Message);
        ////    }
        ////}

        /////// <summary>
        /////// 上级组合点击下时
        /////// </summary>
        /////// <param name="sender">sender</param>
        /////// <param name="e">e</param>
        ////private void cboPort_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.BeforeMouseClickEventArgs e)
        ////{
        ////    string portlevel = "";
        ////    try
        ////    {
        ////        portlevel = cboPortLever.Value;

        ////        if (portlevel != null)
        ////        {
        ////            ////若为“组合层”，上级组合只可加载“计划层”组合；
        ////            if (null != this.cboPortLever.Value && "PORT_LAYER".Equals(this.cboPortLever.Value.ToString()))
        ////            {
        ////                if (status != ClsEnums.StatusSetting.YssBrow)
        ////                {
        ////                    this.cboPort.YssReadOnly = false;
        ////                }

        ////                this.cboPort.QueryType = "getPortDateComBox";
        ////                this.cboPort.YssDataSrc = ClsEnums.DataSrc.SrcDB;
        ////                this.cboPort.QueryCond = "PLAN_LAYER" + "\t" + " a.C_PORT_CODE in (SELECT * FROM THE (SELECT CAST(f_str2list('" + ClsContext.DataRightString;
        ////            }
        ////            else if (null != this.cboPortLever.Value && "UNIT_LAYER".Equals(this.cboPortLever.Value.ToString()))
        ////            {
        ////                ////　若为“单元层”，上级组合只可加载“组合层”组合；
        ////                if (status != ClsEnums.StatusSetting.YssBrow)
        ////                {
        ////                    this.cboPort.YssReadOnly = false;
        ////                }

        ////                this.cboPort.QueryType = "getPortDateComBox";
        ////                this.cboPort.YssDataSrc = ClsEnums.DataSrc.SrcDB;
        ////                this.cboPort.QueryCond = "PORT_LAYER" + "\t" + " a.C_PORT_CODE in (SELECT * FROM THE (SELECT CAST(f_str2list('" + ClsContext.DataRightString;
        ////            }
        ////            else
        ////            {
        ////                ////若为“组合层”，上级组合只可加载“计划层”组合；
        ////                this.cboPort.Value = null;
        ////                this.cboPort.YssReadOnly = true;
        ////                this.cboPort.Text = "";
        ////            }
        ////        }
        ////        else
        ////        {
        ////            ////若为“组合层”，上级组合只可加载“计划层”组合；
        ////            this.cboPort.Value = null;
        ////            this.cboPort.YssReadOnly = true;
        ////            this.cboPort.Text = "";
        ////        }

        ////    }
        ////    catch (Exception ex)
        ////    {
        ////        throw new ClsBaseException(ex.Message);
        ////    }
        ////}

        #region 产品状态
        /// <summary>
        /// 产品状态下拉控件事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboProdState_SelectedValueChanged(object sender, EventArgs e)
        {
            ////ctlCboClear();            
        }

        ////modified by liyanjun 2016-10-17 BUG #141897 【招商证券】产品信息里到期日和清盘日小于业务日期，业务日期一样能够进行账务处理
        /////// <summary>
        /////// 控制清算日期方法
        /////// </summary>
        ////private void ctlCboClear()
        ////{
        ////    if (isCreateCopy)
        ////    {
        ////        return;
        ////    }

        ////    dtpClear.yssEnabled = false;
        ////    if (cboProdState.Value != null)
        ////    {
        ////        //// By Jinghehe 2014-9-14 bug 100953
        ////        Port port = (Port)this.yssGetBaseSelTypeItemMVC();

        ////        if (status != ClsEnums.StatusSetting.YssAdd)
        ////        {
        ////            dtpClear.setRange(DateTime.Parse(port.D_CLOSE), Convert.ToDateTime("9998-12-31"));
        ////            this.dtpExpirationDate.setDateTime(DateTime.Parse(port.D_CLOSE));
        ////        }

        ////        YssResources.Fun.ClsEnums.PD_STATUS statusPd = (ClsEnums.PD_STATUS)Enum.Parse(typeof(ClsEnums.PD_STATUS), cboProdState.Value);
        ////        if (statusPd == ClsEnums.PD_STATUS.PS5)
        ////        {
        ////            dtpClear.setDateTime(dtpExpirationDate.getBeginDate);
        ////            clsInterface.setControlsStatus(tbMain, ClsEnums.StatusSetting.YssBrow);
        ////            this.cboPort.YssReadOnly = true;
        ////        }
        ////        else if (statusPd == ClsEnums.PD_STATUS.PS6)
        ////        {
        ////            clsInterface.setControlsStatus(tbMain, ClsEnums.StatusSetting.YssBrow);
        ////            this.cboPort.YssReadOnly = true;
        ////            dtpClear.yssEnabled = status == ClsEnums.StatusSetting.YssBrow ? false : true;
        ////            dtpClear.setRange(dtpExpirationDate.getBeginDate, Convert.ToDateTime("9998-12-31"));
        ////        }
        ////        else
        ////        {
        ////            dtpClear.setDateTime(dtpExpirationDate.getBeginDate);
        ////            dtpClear.yssEnabled = false;
        ////            clsInterface.setControlsStatus(tbMain, status);
        ////        }
        ////    }
        ////    else
        ////    {
        ////        clsInterface.setControlsStatus(tbMain, status);
        ////        dtpClear.yssEnabled = false;
        ////    }
        ////}

        #endregion

        /// <summary>
        /// 分割条收缩状态改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void splitRight_ExpandedChanged(object sender, ExpandedEventArgs e)
        {
            if (e.NewExpandedValue == true)
            {
                this.Size = new Size(749, 377);
            }
            else
            {
                this.Size = new Size(500, 377);
            }
        }

        /// <summary>
        /// 勾选Table
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void chkCheckAll_CheckedChanged(object sender, YssDevComponents.DotNetBar.CheckBoxChangeEventArgs e)
        {
            foreach (Row row in tbRightMain.Rows)
            {
                row.Checked = chkCheckAll.Checked;
            }

            this.chkBoxCheckedRowsCount.Text = tbRightMain.CheckedRows.Count.ToString();
        }

        /// <summary>
        /// 仅显示勾选行复选框——勾选事件
        /// </summary>
        /// <param name="sender">chkBoxCheckedRowsCount</param>
        /// <param name="e">事件参数</param>
        private void chkBoxCheckedRowsCount_CheckedChanged(object sender, YssDevComponents.DotNetBar.CheckBoxChangeEventArgs e)
        {
            Table tempTable = this.tbRightMain;
            Yss.CommonLib.RedisplayTableCheckedRows(tempTable.Rows, chkBoxCheckedRowsCount.Checked);
        }

        /// <summary>
        /// 行选择状态改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void tbRightMain_CheckStateChanged(object sender, Yss.KTable.Events.CheckStateChangeEventArgs e)
        {
            this.chkBoxCheckedRowsCount.Text = tbRightMain.CheckedRows.Count.ToString();
        }

        /// <summary>
        /// panjunfang add 20151215 
        /// 增加对List界面的判断，不一定是Set对应的List
        /// 相关需求：STORY #21887 产品视图浏览需求
        /// </summary>
        /// <param name="operResult">保存数据后后台返回的操作结果信息</param>
        protected override void operAfterSave(string operResult)
        {
            if (this.HostList != null)
            {
                ////从浏览界面修改组合设置后，重新刷新{产品基本信息List界面}
                int rowIdx = this.frmBaseViewList.tbMain.SelectedRow.MarkIndex;
                this.ModifiedRowIds.Clear();
                this.ModifiedRowIds.Add((this.frmBaseViewList.tbMain.SelectedRow.Tag as BasePojo).Id);
                base.operAfterSave(operResult);
                this.frmBaseViewList.tbMain.CancelSelectedRow();
                this.frmBaseViewList.findSelectedRowForSet(this.frmBaseViewList.tbMain.Rows, this.ModifiedRowIds);
                this.frmBaseViewList.tbMain.ShowSelectedRow(this.frmBaseViewList.tbMain.SelectedRow);
                ////重新更新浏览界面的组合数据
                //// modified by HeLiang 2017-06-14 STORY #42921 产品信息组件拆分开发.临时注释
                ////Frm_PORT_INFO_DETAIL_L tempParent = ((Frm_PORT_INFO_DETAIL_L)this.HostList);
                ////tempParent.PortPojo = (FAST.Common.Service.Pojo.Port)this.frmBaseViewList.tbMain.SelectedRow.Tag;
                ////tempParent.fillDetailData();
            }
            else
            {
                base.operAfterSave(operResult);
            }
        }

        /// <summary>
        /// panjunfang add 20151215 
        /// 增加对List界面的判断，不一定是Set对应的List
        /// 相关需求：STORY #21887 产品视图浏览需求
        /// </summary>
        /// <param name="operResult">保存数据后后台返回的操作结果信息</param>
        public override void refreshTbMain(string operResult)
        {
            base.refreshTbMain(operResult);
            if (this.HostList != null)
            {
                ////如果不是Set对应的List界面就不允许操作{上移}、{下移}、{新增}、{复制}、{删除}
                this.btnBar.setButtonEnabled(FAST.Core.BaseControl.Fun.ClsButtonName.BtnPrevious, false);
                this.btnBar.setButtonEnabled(FAST.Core.BaseControl.Fun.ClsButtonName.BtnNext, false);
                this.btnBar.setButtonEnabled(FAST.Core.BaseControl.Fun.ClsButtonName.BtnNew, false);
                this.btnBar.setButtonEnabled(FAST.Core.BaseControl.Fun.ClsButtonName.BtnDelete, false);
                this.btnBar.setButtonEnabled(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCopy, false);
            }
        }

        /// <summary>
        /// 导航栏分页设置按钮上下文菜单装载事件。
        /// add by zhd 2016-09-25
        ////STORY33239【南方基金】复制建仓参照组合能默认勾选继承内容即增加保存按钮
        /// </summary>
        /// <param name="sender">导航栏分页NavigateItem</param>
        /// <param name="e">事件参数</param>
        private void NavigateItem_ContextMenuLoading(object sender, ContextMenuLoadingEventArgs e)
        {
            e.Items.Clear();
            this.navigateItemMain.SetButtonToolTip = "方案设置";

            e.Items.Add("方案保存");
            e.Items.Add("方案删除");

        }

        /// <summary>
        /// 导航栏分页设置按钮上下文菜单装载事件。
        /// add by zhd 2016-09-25
        ////STORY33239【南方基金】复制建仓参照组合能默认勾选继承内容即增加保存按钮
        /// </summary>
        /// <param name="sender">导航栏分页NavigateItem</param>
        /// <param name="e">事件参数</param>
        private void NavigateItem_ContextMenuItemClick(object sender, Yss.KNavigation.ContextMenuEventArgs e)
        {
            if (e.ClickedItem.Text == "方案保存")
            {
                planSave();
            }
            else if (e.ClickedItem.Text == "方案删除")
            {
                planDel();
            }
        }

        /// <summary>
        /// 方案保存事件
        /// add by zhd 2016-09-25
        /// STORY33239【南方基金】复制建仓参照组合能默认勾选继承内容即增加保存按钮
        /// </summary>
        private void planSave()
        {
            List<CopyData> itemList = new List<CopyData>();
            ////将界面状态改为新增
            this.status = ClsEnums.StatusSetting.YssAdd;

            try
            {
                itemList = this.getCheckedItem();
                if (itemList.Count == 0)
                {
                    YssMessageBox.ShowQuestion("请勾选数据项！");
                    return;
                }
                //// modified by HeLiang 2017-06-14 STORY #42921 产品信息组件拆分开发.临时注释
                ////Frm_PORT_PLAN frmPortPlan = new Frm_PORT_PLAN();
                ////frmPortPlan.Activate();
                ////frmPortPlan.Visible = true;
                ////frmPortPlan.initControlStat();
                ////frmPortPlan.setPlanCode();
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 方案删除事件
        /// add by zhd 2016-09-25
        /// STORY33239【南方基金】复制建仓参照组合能默认勾选继承内容即增加保存按钮
        /// </summary>
        private void planDel()
        {
            string planCode = this.cboFA.Value;
            string reinfo = null;

            try
            {
                if (null == this.cboFA.Value)
                {
                    YssMessageBox.ShowQuestion("请选择要删除的方案！");
                    return;
                }

                if (YssMessageBox.ShowQuestion("确定删除方案？", "删除提醒") != DialogResult.Yes)
                {
                    return;
                }

                reinfo = portPlanService.deleteByPlanCode(ClsBizCons.PORT_PLAN, planCode);
                if (null != reinfo && reinfo.Equals("success"))
                {
                    YssMessageBox.ShowQuestion("方案删除成功！");
                }
                else
                {
                    YssMessageBox.ShowQuestion("方案删除失败！");
                    return;
                }

                this.cboFA.Value = "";
                this.tbRightMain.Refresh();
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 获取自定义模式主界面选中的项目
        /// add by zhd 2016-09-25
        /// STORY33239【南方基金】复制建仓参照组合能默认勾选继承内容即增加保存按钮
        /// </summary>
        /// <returns>itemList</returns>
        public List<CopyData> getCheckedItem()
        {
            List<CopyData> itemList = new List<CopyData>();
            foreach (Row checkedRow in this.tbRightMain.CheckedRows)
            {
                if (!string.Equals((checkedRow.Tag as CopyData).C_DATA_CODE_P, "[root]"))
                {
                    itemList.Add(checkedRow.Tag as CopyData);
                }
            }

            return itemList;
        }

        /// <summary>
        /// 首选方案改变事件
        /// add by zhd 2016-09-27
        /// STORY33239【南方基金】复制建仓参照组合能默认勾选继承内容即增加保存按钮
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void checkSX_CheckedChanged(object sender, EventArgs e)
        {
            if (this.cboFA.SelectedItem != null)
            {
                ArrayList list = new ArrayList();
                if (checkSX.Checked)
                {
                    PortPlan plan = null;
                    PortPlan selectedPlan = this.cboFA.SelectedItem.DataEntity as PortPlan;
                    if (!selectedPlan.C_DESC.Equals(planDesc))
                    {
                        ////这里可能修改2项 
                        foreach (ControlEntity ce in this.cboFA.Items)
                        {
                            plan = ce.DataEntity as PortPlan;
                            if (plan.C_DESC.Equals(planDesc))
                            {
                                plan.C_DESC = "";
                                list.Add(plan);
                            }
                        }

                        selectedPlan.C_DESC = planDesc;
                        list.Add(selectedPlan);
                    }

                }
                else
                {
                    PortPlan plan = this.cboFA.SelectedItem.DataEntity as PortPlan;
                    if (plan.C_DESC.Equals(planDesc))
                    {
                        plan.C_DESC = "";
                        list.Add(plan);
                    }
                }

                ////更新后台
                if (list.Count > 0)
                {
                    portPlanService.updateById(list);
                }
            }

        }

        /// <summary>
        /// 加载首选方案
        /// add by zhd 2016-09-27
        /// STORY33239【南方基金】复制建仓参照组合能默认勾选继承内容即增加保存按钮
        /// </summary>
        public void loadSXFA()
        {
            cboFA_BeforeDropDownClick(null, new Yss.KRichEx.AutoFilter.Events.DropDownEventArgs());
            PortPlan plan = null;
            foreach (ControlEntity ce in this.cboFA.Items)
            {
                plan = ce.DataEntity as PortPlan;
                if (plan.C_DESC.Equals(planDesc))
                {
                    this.cboFA.Value = plan.C_PLAN_CODE;
                    break;
                }
            }
        }

        /// <summary>
        /// 方案改变前事件
        /// add by zhd 2016-09-25
        /// STORY33239【南方基金】复制建仓参照组合能默认勾选继承内容即增加保存按钮
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboFA_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            e.Cancel = true;

            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            List<BasePojo> formulaList = this.getPlanCodeByCondition(paraDict);
            this.fillPlanCodeToCombox(formulaList, this.cboFA);
        }

        /// <summary>
        /// 方案改变事件
        /// add by zhd 2016-09-25
        /// STORY33239【南方基金】复制建仓参照组合能默认勾选继承内容即增加保存按钮
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboFA_SelectedValueChanged(object sender, EventArgs e)
        {
            List<BasePojo> dataList = new List<BasePojo>();
            List<string> itemList = new List<string>();
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            string planCode = "";
            if (this.cboFA.SelectedItem != null)
            {
                PortPlan plan = this.cboFA.SelectedItem.DataEntity as PortPlan;
                if (plan.C_DESC.Trim().Equals(planDesc))
                {
                    this.checkSX.Checked = true;
                }
                else
                {
                    this.checkSX.Checked = false;
                }
            }
            else
            {
                this.checkSX.Checked = false;
            }

            try
            {
                dataList = this.getPlanCodeByCondition(paraDict);
                planCode = this.cboFA.Value;

                foreach (BasePojo basePojo in dataList)
                {
                    PortPlan portPlan = (PortPlan)basePojo;
                    if (portPlan.C_PLAN_CODE.Equals(planCode))
                    {
                        itemList.Add(portPlan.C_ITEM_CODE);
                    }
                }

                bool flagAll = true;
                chkCheckAll.Checked = false;

                ////清空界面所勾选的项
                foreach (Row row in this.tbRightMain.Rows)
                {
                    bool flagRow = true;
                    row.Checked = false;
                    foreach (Row subRow in row.SubRows)
                    {
                        bool flagSub = true;
                        subRow.Checked = false;
                        foreach (Row subRow2 in subRow.SubRows)
                        {
                            subRow2.Checked = false;
                            if (itemList.Contains((subRow2.Tag as CopyData).C_DATA_CODE))
                            {
                                subRow2.Checked = true;

                            }
                            else
                            {
                                flagSub = false;
                                flagRow = false;
                                flagAll = false;
                            }
                        }

                        if (subRow.SubRows.Count == 0)
                        {
                            if (itemList.Contains((subRow.Tag as CopyData).C_DATA_CODE))
                            {
                                subRow.Checked = true;
                            }
                            else
                            {
                                flagRow = false;
                                flagAll = false;
                            }
                        }
                        else
                        {
                            if (flagSub)
                            {
                                subRow.Checked = true;
                            }
                        }

                    }

                    if (flagRow)
                    {
                        row.Checked = true;
                    }
                }

                if (flagAll)
                {
                    chkCheckAll.Checked = true;
                }
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }

        }

        /// <summary>
        /// 获取方案下拉框选中的方案名称和代码
        /// add by zhd 2016-09-27
        /// STORY33239【南方基金】复制建仓参照组合能默认勾选继承内容即增加保存按钮
        /// </summary>
        /// <returns>方案名称和代码</returns>
        public string[] getPlanCode()
        {
            string[] planCode = new string[2];
            if (this.cboFA.Value != null && this.cboFA.SelectedItem != null)
            {
                planCode[0] = this.cboFA.SelectedItem.DisplayValue;
                planCode[1] = this.cboFA.SelectedItem.DisplayName;
            }

            return planCode;
        }

        /// <summary>
        /// 查询方案代码
        /// add by zhd 2016-09-25
        /// STORY33239【南方基金】复制建仓参照组合能默认勾选继承内容即增加保存按钮
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>list</returns>
        public List<BasePojo> getPlanCodeByCondition(Dictionary<string, string> paraDict)
        {
            List<BasePojo> dataList = null;
            try
            {
                if (paraDict.ContainsKey("dataClass"))
                {
                    paraDict.Remove("dataClass");
                }

                paraDict.Add("dataClass", "PortPlan");
                paraDict.Add("BUSINESS_TYPE", ClsBizCons.PORT_PLAN);
                dataList = portPlanService.queryPlanPojo(paraDict).DataList;
            }
            catch (Exception ex)
            {
                ClsBaseException.DiscardException(ex);
            }

            return dataList;
        }

        /// <summary>
        ///  向控件中填充数据
        ///  add by zhd 2016-09-25
        /// STORY33239【南方基金】复制建仓参照组合能默认勾选继承内容即增加保存按钮
        /// </summary>
        /// <param name="dataList">dataList</param>
        /// <param name="combox">combox</param>
        private void fillPlanCodeToCombox(List<BasePojo> dataList, YssSelCombox combox)
        {
            try
            {
                combox.Items.Clear(); // 清空控件里面的所有的数据
                List<Yss.KRichEx.AutoFilter.Model.KTableEntity> lists = new List<Yss.KRichEx.AutoFilter.Model.KTableEntity>();
                Yss.KRichEx.AutoFilter.Model.KTableEntity entity = null;
                ////方案代码
                combox.DisplayValue = "C_PLAN_CODE";
                combox.DisplayName = "C_PLAN_NAME";
                combox.Parameter = "C_PLAN_CODE~C_PLAN_NAME";

                if (null != dataList && dataList.Count > 0)
                {
                    ////用于排查重复项
                    List<string> planCodeList = new List<string>();

                    for (int i = 0; i < dataList.Count; i++)
                    {
                        ////筛选出重复的方案代码
                        PortPlan portPlan = (PortPlan)dataList[i];
                        if (!planCodeList.Contains(portPlan.C_PLAN_CODE))
                        {
                            BasePojo pojo = dataList[i];
                            entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity((PortPlan)pojo);
                            lists.Add(entity);
                            planCodeList.Add(portPlan.C_PLAN_CODE);
                        }

                    }

                    ////循环list往控件里面塞数据
                    for (int i = 0; i < lists.Count; i++)
                    {
                        combox.Items.Add((Yss.KRichEx.AutoFilter.Model.KTableEntity)lists[i]); // 循环list把对象放到控件中
                    }
                }
            }
            catch (Exception ex)
            {
                ClsBaseException.DiscardException(ex);
            }
        }


        /// <summary>
        /// 参照组合值改变时创建复制创建事件
        /// 目的是为了刷新权限设置
        /// add by huangjin 2016-9-23 STORY #28950 参数复制功能优化-复制权限和群组以及产品数
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboPortCode_SelectedValueChanged(object sender, EventArgs e)
        {
            string portCode = this.cboPortCode.Value;
            if (portCode != null)
            {
                createTbRightMain(portCode);
            }
            foreach (Row row in tbRightMain.Rows)
            {
                row.Checked = chkCheckAll.Checked;
            }

            this.chkBoxCheckedRowsCount.Text = tbRightMain.CheckedRows.Count.ToString();
        }
    }
}




