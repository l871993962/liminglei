using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;

using FAST.Common.Service.Interface;
using FAST.Core.Bussiness.Service;

using FAST.Core.Exceptions;

using FAST.Core.Context;
using FAST.Core.Bussiness.Pojo;

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

using FAST.Platform.DataIntegration.Imp.Service;

using FAST.Platform.DataIntegration.Imp.Pojos;

using FAST.Core.BaseControl.Pojo;
using FAST.Core.BaseControl.Fun;
using FAST.Core.BaseControl.YssButtonBars;

using Yss.KTable.Models;
using System.Threading;

using System.Text.RegularExpressions;


using System.Collections;

using FAST.Platform.DataIntegration.Imp.Funs;

using System.Security.Cryptography;
using Yss.KTable.Collections;
using Yss.KTable.Events;
//using YssBaseCls.Pojo;
//using YssDayf.service.Act;

//using YssDayf.Pojo.Act;
//using YssDayf.Pojo.Clr;
//using YssDayf.service.Oper;
//using YssDayf.Form.Plan;
//using YssDayf.Pojo.Plan;
//using YssDayf.service.Plan;
using FAST.Core.BaseControl;
//using YssBaseCls.Fun;


//using YssPara.Service.Bi;
using FAST.Core.Communication.BusiService;
using Yss.KRichEx.AutoFilter.Model;
using System.Xml;
using System.IO;
using FAST.Platform.CP.Mail.Fun;
using YssSecInformation.Support.Pub.Pojo;
using YssSecInformation.Support.Pub.Service;
using YssInformation.Support.Sys.Dictionary.Pojo;
using YssDevComponents.DotNetBar;
using ButtonItem = FAST.Core.BaseControl.YssButtonBars.ButtonItem; 
/*
 * 开发人员 开发日期    开发说明
 * 张智坤   20150803    创建
 */

namespace YssSecInformation.Pub.Form
{
    /// <summary>
    ///chenbo 20170819 
    ///TASK #332232 公共信息处理界面的系统初始化接口拆分
    /// ---------------------------------------------------
    /// 公共信息处理类
    /// 
    /// ------修改记录------
    /// 修改人：Yuntao Lau
    /// 修改时间：2016.02.02
    /// 修改内容：1.调整页面布局，新增操作人、操作时间、相关参数三列。
    ///           2.调整操作日志加载方式，取值方式由从前台缓存变为从后台数据库取值。
    ///           3.增加接口相关参数管理，通过组合代码和接口代码查询相关参数。
    ///           4.优化代码。
    /// </summary>
    public partial class Frm_PubClr : FrmBaseOper, I_Workflow
    {
        /// <summary>
        /// 分组类型-public公共-unpublic非公共
        /// </summary>
        private const string groupType = "public";

        ///// <summary>
        ///// 接口类型-1公共-0非公共
        ///// </summary>
        private const string detailType = "1";

        /// <summary>
        /// 初始化信息
        /// </summary>
        private List<string> initList = null;

        ///// <summary>
        ///// 公共接口信息
        ///// </summary>
        //private List<string> publicList = null;

        /// <summary>
        /// 定义锁
        /// </summary>
        private object objLock = new object();

        /// <summary>
        /// 创建分组
        /// </summary>
        private ToolStripMenuItem tsmItemSetAdd = null;

        ///// <summary>
        ///// 添加接口
        ///// </summary>
        //private ToolStripMenuItem tsmItemCfgAdd = null;

        /// <summary>
        /// 删除分组
        /// </summary>
        private ToolStripMenuItem tsmItemDelete = null;

        /// <summary>
        /// 选中/设置的项目
        /// </summary>
        private Dictionary<string, string> dictSelItem = null;

        ////BUG #138125 公共处理AMAC行业指数历史行情时，若有手工数据提示后不勾选重新生成清算报错 且自动转手工数据没有提示信息 
        ////added by mzy 20161119
        /// <summary>
        /// 业务检查窗体
        /// liuxiang 2015-10-28
        /// STORY #22070 重复清算行情文件时，能提示系统中已经有自动转手工的数据
        /// </summary>
        //private Frm_INFO_PREPROCE frm_Preproce = null;

        ////BUG #138125 公共处理AMAC行业指数历史行情时，若有手工数据提示后不勾选重新生成清算报错 且自动转手工数据没有提示信息 
        ////added by mzy 20161119
        /// <summary>
        ///清算预处理服务类
        /// liuxiang 2015-10-28
        /// STORY #22070 重复清算行情文件时，能提示系统中已经有自动转手工的数据
        /// </summary>
        //private IClearPreprocessService preprocessService = null;

        // 定义为设置模式
        private bool bIsSettingMode = false;

        private Dictionary<string, Cell> dictCells = null;

        //IIcGroupService icGroupService = ServiceFactory.createService<IIcGroupService>();

        /////// <summary>
        /////// Access操作类
        /////// </summary>
        ////private AccessOperator _accessOperator;

        /// <summary>
        /// 首选方案描述
        /// </summary>
        private string planDesc = "清算首选方案";

        /// <summary>
        /// 11
        /// </summary>
        //private IBusinessPlanService businessPlanService = null;

        /// <summary>
        /// zhoushuhang 20170417 STORY #27418 [优化]清算核算类参数设置优化
        /// 刷新业务操作日志的委托
        /// </summary>
        private delegate void UpdateTableLog(bool flag);

        /// <summary>
        /// 更新行状态执行结果的委托
        /// </summary>
        /// <param name="cell">当前操作行的单元格</param>
        /// <param name="status">状态</param>
        private delegate void SetRunStatusCtl(Cell cell, string status);

        /// <summary>
        /// zhoushuhang 20170417 STORY #27418 [优化]清算核算类参数设置优化
        /// 获取公共信息处理接口代码
        /// </summary>
        private List<string> groupDetailCodeList = new List<string>();

        public Frm_PubClr()
        {
            InitializeComponent();
            this.ShowLeftPanel = false;
            this.bUseMVCService = true;
            this._useByThread = true;
            receiveCustomLogBus = new receiveMegLog(getCustomLog);     
            //if (!this.DesignMode)
            //{
            //    addButton();
            //    tbMain.ContextMenuOpening += new CancelEventHandler(tbMain_ContextMenuOpening);
            //    tsmItemSetAdd = new ToolStripMenuItem("创建分组");
            //    tsmItemSetAdd.Click += new EventHandler(tsmItemSetAdd_Click);
            //    tbMain.AddContextMenuStripItem(tsmItemSetAdd);
            //    //tsmItemCfgAdd = new ToolStripMenuItem("添加接口");
            //    //tsmItemCfgAdd.Click += new EventHandler(tsmItemCfgAdd_Click);
            //    //tbMain.AddContextMenuStripItem(tsmItemCfgAdd);
            //    tsmItemDelete = new ToolStripMenuItem("删除分组");
            //    tsmItemDelete.Click += new EventHandler(tsmItemDelete_Click);
            //    tbMain.AddContextMenuStripItem(tsmItemDelete);
            //    tsmItemDelete.Visible = false;
            //}

            //添加分级菜单事件 20160929 add by chenyoucai STORY27418
            this.tbMain.GradingLevelChanged += new GradingLevelEventHandler(tbMain_GradingLevelChanged);

            /*
             * Author : ChenLong
             * Date   : 2016-01-15
             * Status : Add
             * Comment: 显示监控日志信息
             */
            IsShowMonitorLogTable = true;
        }

        /// <summary>
        /// Author : ChenLong
        /// Date   : 2016-01-15
        /// Status : Add
        /// Comment: 重写窗体加载事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void FrmBaseList_Load(object sender, EventArgs e)
        {
            base.FrmBaseList_Load(sender, e);
            /*
             * Author : ChenLong 
             * Date   : 2016-01-15
             * Status : Add
             * Comment: 增加执行子按钮
             */
            base.AddExeSubButton();
        }

        /// <summary>
        /// 分级菜单事件
        /// 20160929 add by chenyoucai STORY27418
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void tbMain_GradingLevelChanged(object sender, GradingLevelEventArgs e)
        {
            String gradingLevel = e.GradingLevel.ToString();

            if (!Directory.Exists(ClsConstant.FILE_CustomSettingPath))
            {
                Directory.CreateDirectory(ClsConstant.FILE_CustomSettingPath);
            }

            ClsXmlAdmin xmlAdmin = new ClsXmlAdmin(ClsConstant.FILE_CustomSettingPath + "\\" + this.YssFormMenu.C_FUN_NAME + ".xml");
            XmlNode node = xmlAdmin.getNode("GradingLevel");
            if (node == null)
            {
                xmlAdmin.addNode("", "GradingLevel", gradingLevel);
            }
            else
            {
                xmlAdmin.ModfiyNodeText(node, gradingLevel);
            }

            xmlAdmin.Dispose();
            xmlAdmin = null;
        }

        /// <summary>
        /// 添加装载文件按钮
        /// </summary>
        private void addButton()
        {
            FAST.Core.BaseControl.Pojo.ClsButtonInfo btnRefresh = new FAST.Core.BaseControl.Pojo.ClsButtonInfo();
            btnRefresh.Image = FAST.Resource.Resource.btnFilter_L;
            btnRefresh.Name = "btnRefresh";
            btnRefresh.Text = "刷新";
            btnRefresh.Tooltip = "刷新操作日志";
            btnRefresh.ClickEvent += new EventHandler(btnRefresh_Click);
           //// btnRefresh.ItemAlignment = YssDevComponents.DotNetBar.eItemAlignment.Far;
            this.btnBarOper.addButton(btnRefresh);

            //ClsButtonInfo btnPlanSave = new ClsButtonInfo();
            //btnPlanSave.Name = "btnPlanSave";
            //btnPlanSave.Text = "方案保存";
            //btnPlanSave.Tooltip = "方案保存";
            //btnPlanSave.Image = FAST.Resource.Resource.btnSave_L;
            //btnPlanSave.ClickEvent += new System.EventHandler(this.btnPlanSave_Click);
            //this.btnBarOper.addButton(btnPlanSave, 6);

            //ClsButtonInfo btnPlanDel = new ClsButtonInfo();
            //btnPlanDel.Name = "btnPlanDel";
            //btnPlanDel.Text = "方案删除";
            //btnPlanDel.Tooltip = "方案删除";
            //btnPlanDel.Image = FAST.Resource.Resource.btnDel_L;
            //btnPlanDel.ClickEvent += new System.EventHandler(this.btnPlanDel_Click);
            //this.btnBarOper.addButton(btnPlanDel, 7);

            List<string> list = new List<string>();
            list.Add(ClsButtonName.BtnExecute);
            list.Add(ClsButtonName.BtnClose);
            list.Add(ClsButtonName.BtnHelp);
            this.btnBarOper.FunRightList = list;

            //// add by liuxiang 2015-7-15
            //// BUG #115387 [紧急][招商证券]部分权限设置无效
            List<string> userList = null;
            if (ClsContext.HtUserOperRight.ContainsKey("dataImportImpl"))
            {
                userList = (List<string>)ClsContext.HtUserOperRight["dataImportImpl"];
            }

            if (userList == null || !userList.Contains(ClsButtonName.BtnExecute))
            {
                userList = list;
                list = new List<string>();
                list.AddRange(userList);
                list.Remove(ClsButtonName.BtnExecute);
            }

            this.btnBarOper.UserOperList = list;
            this.btnBarOper.setUnVisible(ClsButtonName.BtnDelete);
        }

        /// <summary>
        /// 设置为设置模式
        /// </summary>
        public void setSettingMode()
        {
            this.bIsSettingMode = true;

            this.pnlParams.Visible = false; // 隐藏条件面板
            this.panelTop.Visible = false; // 隐藏
            this.expandableSplitterX1.Visible = false; // 隐藏日志分栏
            this.pnlLog.Visible = false; // 隐藏日志面板
            this._useByThread = false;
            this.panelEx1.Visible = true;
            this.btnBar.Visible = true;
            if (frmOperMes != null)
            {
                frmOperMes.Visible = false;
            }
        }

        /// <summary>
        /// 设置选中的设置项目
        /// </summary>
        /// <param name="dictSelItem">选中的项目字典</param>
        /// <param name="codes">选中的组合代码</param>
        public void setSelItemDict(ref Dictionary<string, string> dictSelItem, ref List<string> codes)
        {
            this.dictSelItem = dictSelItem;
        }

        /// <summary>
        /// 加载并显示日志窗体
        /// </summary>
        /// <param name="frmLog">日志窗体</param>
        protected override void showLoadLogForm(FrmOperMes frmLog)
        {
            if (bIsSettingMode)
            {
                if (frmLog != null)
                {
                    frmLog.Visible = false;
                }
                pnlLog.Visible = false;
                setSettingModeOperBotten();
                return;
            }

            base.showLoadLogForm(frmLog);
        }


        protected override void btnOK_Click(object sender, EventArgs e)
        {
            dictSelItem.Clear();
            foreach (Row row in tbMain.CheckedRows)
            {
                IcGroupAndDetail icItem = row.Tag as IcGroupAndDetail;
                string key = icItem.C_GROUP_CODE_P + ";" + icItem.C_GROUP_CODE;
                if (!dictSelItem.ContainsKey(key))
                {
                    dictSelItem.Add(key, icItem.C_GROUP_NAME);
                }
            }

            base.btnOK_Click(sender, e);
        }

        /// <summary>
        /// 重写窗体加载事件-这里重置线程池最大任务并发数
        /// </summary>
        /// <param name="e">EventArgs</param>
        protected override void OnLoad(EventArgs e)
        {
            base.OnLoad(e);
            this.frmOperMes.ShowPretreatPort = true;
        }

        /// <summary>
        /// 设置设置模式下的按钮的菜单
        /// </summary>
        private void setSettingModeOperBotten()
        {
            if (!this.DesignMode)
            {
                List<string> list = new List<string>();
                list.Add(ClsButtonName.BtnHelp);
                list.Add(ClsButtonName.BtnFullScreen);
                list.Add(ClsButtonName.BtnOK);
                this.btnBar.UserOperList = list;
                this.btnBar.FunRightList = list;
            }
        }

        /// <summary>
        /// 虚方法，防止异常
        /// </summary>
        protected override void initServiceMVC()
        {
            //businessPlanService = ServiceFactory.createService<IBusinessPlanService>();
            //////BUG #138125 公共处理AMAC行业指数历史行情时，若有手工数据提示后不勾选重新生成清算报错 且自动转手工数据没有提示信息 
            //////added by mzy 20161119
            ////// liuxiang 2015-10-28  STORY #22070 重复清算行情文件时，能提示系统中已经有自动转手工的数据
            //if (this.preprocessService == null)
            //{
            //    this.preprocessService = ServiceFactory.createService<IClearPreprocessService>();
            //}
        }


        /// <summary>
        /// 行改变前事件-什么都不要做，张绍林-20150529。
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbMain_BeforeSelectionChanged(object sender, Yss.KTable.Events.RowSelectChangeEventArgs e)
        {
        }

        /// <summary>
        /// 屏蔽tbMain_RowDoubleClicked，这里不需要任何的操作
        /// </summary>
        /// <param name="sender">Table</param>
        /// <param name="e">RowEventArgs</param>
        protected override void tbMain_RowDoubleClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
        }

        /// <summary>
        /// 右键事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void tbMain_ContextMenuOpening(object sender, CancelEventArgs e)
        {
            e.Cancel = false;
            tsmItemDelete.Visible = false;
            if (this.tbMain.SelectedRow != null)
            {
                /*
                 * Author : ChenLong
                 * Date   : 2017-05-12
                 * Status : Modify
                 * Comment:STORY #42091接口加载优化 控制右击菜单选项
                 */
                IcGroupAndDetail item = this.tbMain.SelectedRow.Tag as IcGroupAndDetail;
                if (item.C_IDF_FLAG == "USEED" && item.C_IS_DETAIL == "0")
                {
                    tsmItemDelete.Visible = true;
                }
            }

            tsmItemSetAdd.Visible = true;
            //tsmItemCfgAdd.Visible = true;
        }

        /// <summary>
        /// 接口组界面保存后事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        //private void frm_ImpGroup_S_YssOnAfterSaveClick(object sender, YssBeforeOperEventArgs e)
        //{
        //    this.tbMain.Clear();
        //    getMainListDataMVC(new BasePojo(), true);
        //}

        /// <summary>
        /// 接口组设置界面删除后事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        //private void frm_ImpGroup_S_YssOnAfterDelClick(object sender, EventArgs e)
        //{
        //    this.tbMain.Clear();
        //    this.getMainListDataMVC(new BasePojo(), true);
        //}

        /// <summary>
        /// 设置接口组的事件
        /// </summary>
        /// <param name="sender">ButtonCell</param>
        /// <param name="e">事件参数</param>
        //private void btnCellSettingGroup_Click(object sender, CellEventArgs e)
        //{
        //    try
        //    {
        //        ////先将光标换成等待，并且Table只读-张绍林-20150520
        //        this.Cursor = Cursors.WaitCursor;
        //        this.tbMain.ReadOnly = true;

        //        Frm_IcGroup_S frmIcGroupS = new Frm_IcGroup_S();
        //        frmIcGroupS.YssOnAfterSaveClick += new FrmBaseSet.AfterSaveClick(frm_ImpGroup_S_YssOnAfterSaveClick);
        //        frmIcGroupS.YssOnAfterDelClick += new FrmBaseSet.AfterDelClick(frm_ImpGroup_S_YssOnAfterDelClick);
        //        frmIcGroupS.YssFormMenu = _formFun;
        //        frmIcGroupS.YssStatus = ClsEnums.StatusSetting.YssBrow;
        //        frmIcGroupS.initControlStat();
        //        IcGroupAndDetail icItem = e.Row.Tag as IcGroupAndDetail;
        //        frmIcGroupS.init(icItem.C_GROUP_CODE, icItem.C_GROUP_NAME, icItem.C_ORDER_BY, icItem.C_GROUP_CODE_P);
        //        frmIcGroupS.ShowDialog(this);
        //    }
        //    catch (Exception ex)
        //    {
        //        YssMessageBox.ShowCommonInfo(ex.Message);
        //    }
        //    finally
        //    {
        //        ////最后将光标换成默认光标，并且Table可写。注意一定要将此步放在finally内执行，防止中间步骤异常后光标无法恢复。
        //        ////张绍林-20100520
        //        this.Cursor = Cursors.Default;
        //        this.tbMain.ReadOnly = false;
        //    }
        //}

        /// <summary>
        /// 设置接口的事件
        /// </summary>
        /// <param name="sender">ButtonCell</param>
        /// <param name="e">事件参数</param>
        //private void btnCellSetting_Click(object sender, CellEventArgs e)
        //{
        //    try
        //    {
        //        ////先将光标换成等待，并且Table只读-张绍林-20150520
        //        this.Cursor = Cursors.WaitCursor;
        //        this.tbMain.ReadOnly = true;

        //        Frm_IcDetail_S frmIcDetailS = new Frm_IcDetail_S();
        //        frmIcDetailS.YssOnAfterSaveClick += new FrmBaseSet.AfterSaveClick(frm_ImpGroup_S_YssOnAfterSaveClick);
        //        frmIcDetailS.YssOnAfterDelClick += new FrmBaseSet.AfterDelClick(frm_ImpGroup_S_YssOnAfterDelClick);
        //        frmIcDetailS.YssFormMenu = _formFun;
        //        frmIcDetailS.FormStatue = ClsEnums.StatusSetting.YssEdit;
        //        frmIcDetailS.YssStatus = ClsEnums.StatusSetting.YssBrow;
        //        IcGroupAndDetail icItem = e.Row.Tag as IcGroupAndDetail;
        //        frmIcDetailS.init(icItem.C_GROUP_CODE_P, icItem.C_GROUP_CODE);
        //        frmIcDetailS.initControlStat();
        //        frmIcDetailS.ShowDialog(this);
        //    }
        //    catch (Exception ex)
        //    {
        //        YssMessageBox.ShowCommonInfo(ex.Message);
        //    }
        //    finally
        //    {
        //        ////最后将光标换成默认光标，并且Table可写。注意一定要将此步放在finally内执行，防止中间步骤异常后光标无法恢复。
        //        ////张绍林-20100520
        //        this.Cursor = Cursors.Default;
        //        this.tbMain.ReadOnly = false;
        //    }
        //}


        /// <summary>
        /// 删除接口组的事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        //private void tsmItemDelete_Click(object sender, EventArgs e)
        //{
        //    try
        //    {
        //        IcGroupAndDetail pojo = null;
        //        if (this.tbMain.SelectedRow != null)
        //        {
        //            pojo = this.tbMain.SelectedRow.Tag as IcGroupAndDetail;
        //            if (YssMessageBox.ShowQuestion("确定删除[" + pojo.C_GROUP_NAME + "]接口组吗？", "系统提示") == DialogResult.Yes)
        //            {

        //                icGroupService.deleteByGroup(pojo.C_GROUP_CODE, pojo.C_GROUP_CODE_P);
        //                this.tbMain.ClearRows();
        //                getMainListDataMVC(new BasePojo(), true);
        //            }
        //        }
        //    }
        //    catch (Exception ex)
        //    {
        //        YssMessageBox.ShowCommonInfo(ex.Message);
        //    }
        //}

        /// <summary>
        /// 设置接口组的事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        //private void tsmItemSetAdd_Click(object sender, EventArgs e)
        //{
        //    try
        //    {
        //        ////先将光标换成等待，并且Table只读-张绍林-20150520
        //        this.Cursor = Cursors.WaitCursor;
        //        this.tbMain.ReadOnly = true;

        //        Frm_IcGroup_S frmIcGroupS = new Frm_IcGroup_S();
        //        frmIcGroupS.YssOnAfterSaveClick += new FrmBaseSet.AfterSaveClick(frm_ImpGroup_S_YssOnAfterSaveClick);
        //        frmIcGroupS.YssOnAfterDelClick += new FrmBaseSet.AfterDelClick(frm_ImpGroup_S_YssOnAfterDelClick);
        //        frmIcGroupS.YssFormMenu = _formFun;
        //        frmIcGroupS.YssStatus = ClsEnums.StatusSetting.YssAdd;
        //        frmIcGroupS.FormBaseListView = this;
        //        frmIcGroupS.initControlStat();
        //        if (this.tbMain.SelectedRow != null)
        //        {
        //            IcGroupAndDetail icItem = this.tbMain.SelectedRow.Tag as IcGroupAndDetail;
        //            if (icItem.C_IS_DETAIL == "1")
        //            {
        //                frmIcGroupS.init("", "", "", icItem.C_GROUP_CODE_P);
        //            }
        //            else
        //            {
        //                frmIcGroupS.init(icItem.C_GROUP_CODE, icItem.C_GROUP_NAME, "", icItem.C_GROUP_CODE_P);
        //            }
        //        }
        //        this.status = ClsEnums.StatusSetting.YssAdd;
        //        frmIcGroupS.ShowDialog(this);
        //    }
        //    catch (Exception ex)
        //    {
        //        YssMessageBox.ShowCommonInfo(ex.Message);
        //    }
        //    finally
        //    {
        //        ////最后将光标换成默认光标，并且Table可写。注意一定要将此步放在finally内执行，防止中间步骤异常后光标无法恢复。
        //        ////张绍林-20100520
        //        this.Cursor = Cursors.Default;
        //        this.tbMain.ReadOnly = false;
        //    }
        //}

        /// <summary>
        /// 添加接口事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        //private void tsmItemCfgAdd_Click(object sender, EventArgs e)
        //{
        //    try
        //    {
        //        ////先将光标换成等待，并且Table只读-张绍林-20150520
        //        this.Cursor = Cursors.WaitCursor;
        //        this.tbMain.ReadOnly = true;

        //        Frm_IcDetail_S frmIcDetailS = new Frm_IcDetail_S();
        //        frmIcDetailS.YssOnAfterSaveClick += new FrmBaseSet.AfterSaveClick(frm_ImpGroup_S_YssOnAfterSaveClick);
        //        frmIcDetailS.YssOnAfterDelClick += new FrmBaseSet.AfterDelClick(frm_ImpGroup_S_YssOnAfterDelClick);
        //        frmIcDetailS.YssFormMenu = _formFun;
        //        ////frmIcDetailS.FormBaseListView = this;
        //        if (sender is ToolStripDropDownItem)
        //        {
        //            if (this.tbMain.SelectedRow != null)
        //            {
        //                IcGroupAndDetail icItem = this.tbMain.SelectedRow.Tag as IcGroupAndDetail;
        //                frmIcDetailS.init(icItem.C_GROUP_CODE_P, icItem.C_GROUP_CODE);
        //            }

        //            frmIcDetailS.FormStatue = ClsEnums.StatusSetting.YssAdd;
        //            frmIcDetailS.YssStatus = ClsEnums.StatusSetting.YssBrow;
        //            frmIcDetailS.FormBaseListView = this;
        //            frmIcDetailS.initControlStat();
        //            this.status = ClsEnums.StatusSetting.YssBrow;
        //            frmIcDetailS.ShowDialog(this);
        //        }
        //        else
        //        {
        //            frmIcDetailS.FormStatue = ClsEnums.StatusSetting.YssEdit;
        //            frmIcDetailS.YssStatus = ClsEnums.StatusSetting.YssBrow;
        //            Row rowPojo = (Row)(((System.Windows.Forms.Control)sender).Tag);
        //            IcGroupAndDetail icItem = rowPojo.Tag as IcGroupAndDetail;
        //            frmIcDetailS.init(icItem.C_GROUP_CODE_P, icItem.C_GROUP_CODE);
        //            frmIcDetailS.FormBaseListView = this;
        //            frmIcDetailS.initControlStat();
        //            frmIcDetailS.ShowDialog(this);
        //        }
        //    }
        //    catch (Exception ex)
        //    {
        //        YssMessageBox.ShowCommonInfo(ex.Message);
        //    }
        //    finally
        //    {
        //        ////最后将光标换成默认光标，并且Table可写。注意一定要将此步放在finally内执行，防止中间步骤异常后光标无法恢复。
        //        ////张绍林-20100520
        //        this.Cursor = Cursors.Default;
        //        this.tbMain.ReadOnly = false;
        //    }
        //}

        ////BUG #138125 公共处理AMAC行业指数历史行情时，若有手工数据提示后不勾选重新生成清算报错 且自动转手工数据没有提示信息 
        ////added by mzy 20161119
        /// <summary>
        /// 执行前处理(检查自动转手工/手工数据)
        /// liuxiang 2015-10-28  STORY #22070 重复清算行情文件时，能提示系统中已经有自动转手工的数据
        /// </summary>
        //protected override void OnBeforeExcute()
        //{
        //    base.OnBeforeExcute();
        //    if (this.publicList != null && this.publicList.Count > 0)
        //    {
        //        string d_BEGIN = this.yssDateTime.getBeginDate.ToString("yyyy-MM-dd");
        //        string d_END = this.yssDateTime.getEndDate.ToString("yyyy-MM-dd");
        //        string c_ARRAY_CFG_CODE = string.Join(",", this.publicList.ToArray());

        //        ////liuxiang 2016-5-24 BUG #131213 【紧急】【招商财富】调度方案执行公共信息处理时失败报异常错误
        //        initServiceMVC();
        //        List<BasePojo> pojoList = preprocessService.queryData(d_BEGIN, d_END, c_ARRAY_CFG_CODE);

        //        if (this.frm_Preproce != null)
        //        {
        //            frm_Preproce.Dispose();
        //            frm_Preproce = null;
        //        }

        //        if (pojoList != null && pojoList.Count != 0)
        //        {
        //            frm_Preproce = new Frm_INFO_PREPROCE(pojoList);
        //            frm_Preproce.ShowDialog(this);
        //        }
        //    }
        //}

        #region I_Workflow 成员
        /// <summary>
        /// 初始化
        /// </summary>
        /// <param name="lstSelPort">组合</param>
        /// <param name="lstSelItems">项目</param>
        public void setInit(Dictionary<string, string> lstSelPort, Dictionary<string, string> lstSelItems)
        {
            base.FrmBase_Load(this, System.EventArgs.Empty);
            _leftPojos = lstSelPort;
            _selItems = lstSelItems;
            //this.publicList = new List<string>();
            this.initList = new List<string>();

            IDayfSysInitService sysInitSVC = ServiceFactory.createService<IDayfSysInitService>();
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("dataClass", PojoUtil<BasePojo>.getPojoShortName(_formFun.YssAssocia.CommonDataClass));
            paraDict = this.GetParaAssemble(paraDict);
            QueryRes res = sysInitSVC.queryData(paraDict);
            List<string> sys = new List<string>();
            foreach (BasePojo pojo in res.DataList)
            {
                sys.Add(((SysInitItemBean)pojo).C_DV_ITEM_CODE);
            }

            foreach (KeyValuePair<string, string> pair in lstSelItems)
            {
                if (sys.Contains(pair.Key))
                {
                    this.initList.Add(pair.Key);
                }
                //else
                //{
                //    this.publicList.Add(pair.Key);
                //}

            }

        }

        /// <summary>
        /// 执行
        /// </summary>
        /// <param name="idfCode">来源</param>
        /// <param name="funRelaId">功能关联id</param>
        /// <returns>执行状态</returns>
        public string doWorkflow(string idfCode, string funRelaId)
        {
            unchecked
            {
                try
                {
                    this.planCode = idfCode;
                    this.funRelaId = funRelaId;
                    doExecute();
                    return "Success";
                }
                catch (Exception)
                {
                    return "Fail";
                }
            }
        }

        /// <summary>
        /// getTaskCount
        /// </summary>
        /// <returns>count</returns>
        public int getTaskCount()
        {
            return doSubSectionOperation() * _selItems.Count;
        }

        /// <summary>
        /// doWorkflowAndShowForm
        /// </summary>
        /// <returns>执行状态</returns>
        public string doWorkflowAndShowForm()
        {
            throw new System.NotImplementedException();
        }

        /// <summary>
        /// 重置线程任务进度信息 By Jinghehe 2014-4-4 bug 91730
        /// </summary>
        /// <param name="taskCount">线程任务总数</param>
        protected override void ResetProgressInfo(int taskCount)
        {
            base.ResetProgressInfo(doSubSectionOperation() * _selItems.Count);
        }

        #endregion

        /// <summary>
        /// 获取主区域的选中的数据
        /// 方法改造byleeyu20140419
        /// </summary>
        /// <returns>保存选中行的POJO集合</returns>
        protected override Dictionary<string, string> getSelOperItemTags()
        {
            _selItems = new Dictionary<string, string>();
            getCheckedRows();
            return _selItems;
        }

        private void getCheckedRows()
        {
            initList = new List<string>();
            //publicList = new List<string>();
            //this.planCode = "publicinfoexecute";
            foreach (Yss.KTable.Models.Row row in tbMain.Rows)
            {
                IcGroupAndDetail initGroupRela = (IcGroupAndDetail)row.Tag;
                if (initGroupRela.C_GROUP_CODE_P.Equals("SYSINIT"))
                {
                    getCheckedSubRows(row, "SYSINIT");
                }
                //else
                //{
                //    getCheckedSubRows(row, "PUBLIC");
                //}
            }
        }

        private void getCheckedSubRows(Row row, string type)
        {
            if (row.SubRows.Count > 0)
            {
                foreach (Row subRow in row.SubRows)
                {
                    getCheckedSubRows(subRow, type);
                }
            }
            else
            {
                //if ("PUBLIC" == type)
                //{
                //    if (row.Checked)
                //    {
                //        IcGroupAndDetail publicGroupRela = (IcGroupAndDetail)row.Tag;
                //        if (publicGroupRela.C_IS_DETAIL == "1")
                //        {
                //            ////zzk 20160217 增加明细项判断
                //            publicList.Add(publicGroupRela.C_GROUP_CODE);
                //            _selItems.Add(publicGroupRela.C_GROUP_CODE, publicGroupRela.C_GROUP_NAME);
                //        }
                //    }
                //}
                //else
                //{
                    if (row.Checked)
                    {
                        IcGroupAndDetail initGroupRela = (IcGroupAndDetail)row.Tag;
                        initList.Add(initGroupRela.C_GROUP_CODE);
                        _selItems.Add(initGroupRela.C_GROUP_CODE, initGroupRela.C_GROUP_NAME);
                    }
                //}

            }
        } 

        /// <summary>
        /// 检查参数项是否合法
        /// </summary>
        /// <param name="arrStruct">生成日志的结构</param>
        /// <returns>返回检测的状态，通过返回真</returns>
        protected override bool checkParams(ref string[] arrStruct)
        {
            return true;
        }

        public void doSysInitMethod(Dictionary<string, string> dic)
        {           
            IAssetStatsCtlInitService assetStatsCtlInitService = BusiOperServiceFactory.createService<IAssetStatsCtlInitService>();
            this.OperOnServer(assetStatsCtlInitService, dic);

            lock (objLock)
            {
                this.UpdateProgressStatus();
            }
        }

        //private void doPublicInterfaceMethod(Dictionary<string, string> dic)
        //{
        //    IAssetClearCtlService assetClearCtlService = BusiOperServiceFactory.createService<IAssetClearCtlService>();
        //    this.OperOnServer(assetClearCtlService, dic);
        //    lock (objLock)
        //    {
        //        this.UpdateProgressStatus();
        //    }
        //}

        private Dictionary<string, string> reBuildPara()
        {
            Dictionary<string, string> dic = new Dictionary<string, string>();
            string initItem = "";
            string initBondPerHundInter = "";
            string initBondEqu = "";
            ////STORY #21836 初始化证券权益信息和财汇权益信息功能调整
            ////如果2个接口都勾选了，先处理[初始化债券每百元利息]再处理[初始化证券权益信息]。
            foreach (string initCode in initList)
            {
                if (initCode == "initBondPerHundInter")
                {
                    initBondPerHundInter = initCode;
                }
                else if (initCode == "initBondEqu")
                {
                    initBondEqu = initCode;
                }
                else
                {
                    initItem += initCode + ",";
                }
            }

                initItem += initBondPerHundInter + ",";
                initItem += initBondEqu + ",";

            // 移除最后一个逗号
            if (initItem.EndsWith(","))
            {
                initItem = initItem.Remove(initItem.Length - 1);
            }

            dic.Add("C_ITEM_CODE", initItem);
            dic.Add("D_BEGIN_DATE", this.d_OperCurr.ToString("yyyy-MM-dd"));
            dic.Add("D_END_DATE", this.d_OperCurr.ToString("yyyy-MM-dd"));
            dic.Add("C_FUN_CODE", _formFun.C_FUN_CODE);
            dic.Add("C_IDF_CODE", this.planCode);
            dic.Add("C_OPER_CODE", execOperCode);
            dic.Add("C_RELA_ID", funRelaId);

            return dic;
        }

        /// <summary>
        /// 1
        /// </summary>
        /// <param name="paraDict">2</param>
        /// <returns>3</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            paraDict.Add("C_DV_TYPE", "INIT_ITEMS");
            return paraDict;
        }

        /// <summary>
        /// 构建日志显示的结构
        /// </summary>
        /// <param name="structRecord">保存日志显示的结构</param>
        protected override void buildRecordStruct(ref string[] structRecord)
        {
            structRecord = new string[] { "DATE", "C_GROUP_CODE" };
        }

        /// <summary>
        /// 执行方法
        /// </summary>
        /// <returns>返回执行结果</returns>
        public override string doSubSection()
        {
            string c_Result = "";
            bool isCancel = false;
            beforeExecute(ref isCancel);
            Dictionary<string, string> dict = new Dictionary<string, string>();

            if (!isCancel)
            {
                ////只有有勾选系统初始化接口下的2个接口。 默认先处理系统初始化接口下的2个接口，
                List<ThreadStart> ithreadStarts = new List<ThreadStart>();
                if (initList != null && initList.Count > 0)
                {
                    Dictionary<string, string> initDic = reBuildPara();
                    ithreadStarts.Add(delegate { doSysInitMethod(initDic); });
                }

                if (this.ThreadPool.ThreadCount > 0)
                {
                    this.ThreadPool.JoinThread(ithreadStarts);
                }
                else
                {
                    foreach (ThreadStart loThreadStart in ithreadStarts)
                    {
                        this.ThreadPool.AddThread(loThreadStart);
                    }
                }

                //List<ThreadStart> phreadStarts = new List<ThreadStart>();
                //foreach (string icode in this.publicList)
                //{
                //    Dictionary<string, string> pdict = new Dictionary<string, string>();
                //    pdict.Add("C_PORT_CODE", getSelPortCodes());
                //    pdict.Add("C_OPER_ITEM", icode);
                //    pdict.Add("D_START", this.d_OperCurr.ToString("yyyy-MM-dd"));
                //    pdict.Add("D_END", this.d_OperCurr.ToString("yyyy-MM-dd"));
                //    pdict.Add("C_OPER_CODE", execOperCode);
                //    pdict.Add("C_FUN_CODE", _formFun.C_FUN_CODE);
                //    pdict.Add("C_IDF_CODE", this.planCode);
                //    pdict.Add("C_RELA_ID", this.funRelaId);
                //    phreadStarts.Add(delegate { doPublicInterfaceMethod(pdict); });
                //}

                //if (this.ThreadPool.ThreadCount > 0)
                //{
                //    ////如果线程已经启动，则将新线程附加到新的队列里
                //    this.ThreadPool.JoinThread(phreadStarts);
                //}
                //else
                //{
                //    ////如果尚未启动线程，则直接增加线程
                //    foreach (ThreadStart loThreadStart in phreadStarts)
                //    {
                //        this.ThreadPool.AddThread(loThreadStart);
                //    }
                //}

            }

            afterExecute();

            return c_Result;
        }

        /// <summary>
        /// 获取B区
        /// </summary>
        /// <param name="pojo">pojo</param>
        /// <param name="isQueryData">isQueryData</param>
        /// <returns>res</returns>
        public override QueryRes getMainListDataMVC(BasePojo pojo, bool isQueryData)
        {
            QueryRes res = new QueryRes();
            IDayfSysInitService sysInitSVC = ServiceFactory.createService<IDayfSysInitService>();
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("dataClass", PojoUtil<BasePojo>.getPojoShortName(_formFun.YssAssocia.CommonDataClass));
            paraDict = this.GetParaAssemble(paraDict);

            if (ShowPageInation)
            {
                PageInation pageIn = new PageInation();
                pageIn = (PageInation)JsonUtil.toObject(JsonUtil.toJson(page), pageIn.GetType());
                res = getQueryResultMVC(paraDict, pageIn);
            }
            else
            {
                //IAssetClearCtlService assetClearCtlService = BusiOperServiceFactory.createService<IAssetClearCtlService>();
                //QueryRes publicCINTRes = assetClearCtlService.getClearInterfaceByIdf(detailType, "USEED");
                QueryRes publicCINTRes = null;
                ////获取有相关参数的业项集合 add by Yuntao Lau 2016.02.25
                //List<string> hasParaItemCodes = ServiceFactory.createService<ICoParamService>().getItemParaRelaInfo();
                List<string> hasParaItemCodes = null;
                loadMainTable(publicCINTRes, hasParaItemCodes);
            }

            ////zzk 20160216 BUG #126547 公共信息处理和资产数据清算的【设为首选方案】功能不能使用 
            //cboPlanCode_BeforeDropDownClick(null, new Yss.KRichEx.AutoFilter.Events.DropDownEventArgs());
            //BusinessPlan plan = null;
            //foreach (ControlEntity ce in this.cboPlanCode.Items)
            //{
            //    plan = ce.DataEntity as BusinessPlan;
            //    if (plan.C_DESC.Equals("公共清算首选方案"))
            //    {
            //        this.cboPlanCode.Value = plan.C_PLAN_CODE;
            //        break;
            //    }
            //}

            return new QueryRes();
        }


        /// <summary>
        /// 创建Table
        /// </summary>
        /// <param name="res">res</param>
        /// <param name="hasParaItemCodes">有相关参数的业项集合</param>
        private void loadMainTable(QueryRes res, List<string> hasParaItemCodes)
        {
            ////zhoushuhang 20170417 STORY #27418 [优化]清算核算类参数设置优化
            this.groupDetailCodeList.Clear();
            Yss.KTable.Models.Row row = null;
            Yss.KTable.Models.Cell cell = null;
            //Dictionary<string, List<IcGroupAndDetail>> showItem = new Dictionary<string, List<IcGroupAndDetail>>();

            ////Dictionary<string, Cell> dictCells = new Dictionary<string, Cell>();
            dictCells = new Dictionary<string, Cell>();
            //List<BasePojo> dataList = res.DataList;

            // 加载列头
            initListHeader();

            IcGroupAndDetail icGroupAndDetail = null;

            //Row publicRow = new Row();
            //IcGroupAndDetail publicGroupRela = new IcGroupAndDetail();
            //publicGroupRela.C_GROUP_CODE = "public";
            //publicGroupRela.C_GROUP_NAME = "公共接口";
            //publicGroupRela.C_GROUP_CODE_P = "[root]";

            //cell = new Cell();
            //publicRow.Cells.Add(cell);
            //cell = new Yss.KTable.Models.Cell();
            //cell.Text = publicGroupRela.C_GROUP_CODE;
            //cell.Font = new Font(tbMain.Font, FontStyle.Bold);
            //publicRow.Cells.Add(cell);
            //cell = new Yss.KTable.Models.Cell();
            //cell.Text = publicGroupRela.C_GROUP_NAME;
            //cell.Font = new Font(tbMain.Font, FontStyle.Bold);
            //publicRow.Cells.Add(cell);
            //publicRow.Tag = publicGroupRela;
            //dictCells.Add(publicGroupRela.C_GROUP_CODE, cell);
            //this.tbMain.Rows.Add(publicRow);


            Row sysInitRow = new Row();
            IcGroupAndDetail sysInitgroupRela = new IcGroupAndDetail();
            sysInitgroupRela.C_GROUP_CODE = "init";
            sysInitgroupRela.C_GROUP_NAME = "系统初始化";
            sysInitgroupRela.C_GROUP_CODE_P = "SYSINIT";

            cell = new Cell();
            sysInitRow.Cells.Add(cell);
            cell = new Yss.KTable.Models.Cell();
            cell.Text = sysInitgroupRela.C_GROUP_CODE;
            cell.Font = new Font(tbMain.Font, FontStyle.Bold);
            sysInitRow.Cells.Add(cell);
            cell = new Yss.KTable.Models.Cell();
            cell.Text = sysInitgroupRela.C_GROUP_NAME;
            cell.Font = new Font(tbMain.Font, FontStyle.Bold);
            sysInitRow.Cells.Add(cell);
            sysInitRow.Tag = sysInitgroupRela;
            dictCells.Add(sysInitgroupRela.C_GROUP_CODE, cell);
            this.tbMain.Rows.Insert(0, sysInitRow); // 把系统初始化放在最前面byleeyu20140928

            ////加载系统初始化下的接口
            QueryRes sysRes = new QueryRes();
            IDayfSysInitService sysInitSVC = ServiceFactory.createService<IDayfSysInitService>();
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("dataClass", PojoUtil<BasePojo>.getPojoShortName(_formFun.YssAssocia.CommonDataClass));
            paraDict = this.GetParaAssemble(paraDict);
            sysRes = sysInitSVC.queryData(paraDict);
            for (int j = 0; j < sysRes.DataList.Count; j++)
            {
                SysInitItemBean initItemBean = (SysInitItemBean)sysRes.DataList[j];
                icGroupAndDetail = new IcGroupAndDetail();
                icGroupAndDetail.C_GROUP_CODE = initItemBean.C_DV_ITEM_CODE;
                icGroupAndDetail.C_GROUP_NAME = initItemBean.C_DV_ITEM_NAME;
                icGroupAndDetail.C_GROUP_CODE_P = "SYSINIT";
                icGroupAndDetail.C_IS_DETAIL = "1";

                ////zhoushuhang 20170417 STORY #27418 [优化]清算核算类参数设置优化 追加接口代码至集合中。
                this.groupDetailCodeList.Add(initItemBean.C_DV_ITEM_CODE);

                row = new Yss.KTable.Models.Row();

                if (row != null)
                {
                    sysInitRow.SubRows.Add(row);
                }

                cell = new Cell();
                row.Cells.Add(cell);
                cell = new Yss.KTable.Models.Cell();
                cell.Text = icGroupAndDetail.C_GROUP_CODE;
                row.Cells.Add(cell);
                cell = new Yss.KTable.Models.Cell();
                cell.Text = icGroupAndDetail.C_GROUP_NAME;
                row.Cells.Add(cell);
                row.Tag = icGroupAndDetail;
                dictCells.Add(icGroupAndDetail.C_GROUP_CODE, cell);

                ////状态
                ColorCell cellStatus = new ColorCell();
                ////add by zhoushuhang 20170418 STORY #27418 [优化]清算核算类参数设置优化
                cellStatus.ColorCellShape = Yss.KTable.Enums.ColorCellShape.Circle;
                cellStatus.Color = Color.Transparent;
                row.Cells.Add(cellStatus);

                ////操作人
                cell = new Cell("");
                cell.TextAlign = ContentAlignment.MiddleCenter;
                row.Cells.Add(cell);

                ////操作时间
                cell = new Cell("");
                cell.TextAlign = ContentAlignment.MiddleCenter;
                row.Cells.Add(cell);

                //if (hasParaItemCodes.Contains(icGroupAndDetail.C_GROUP_CODE))
                //{
                //    ////相关参数
                //    ButtonCell btnCell = new ButtonCell("相关参数");
                //    btnCell.ToolTip = "设置相关参数";
                //    btnCell.Click += new CellEventHandler(this.btnCellParams_Click);
                //    btnCell.TextAlign = ContentAlignment.MiddleCenter;
                //    btnCell.Tag = row;
                //    row.Cells.Add(btnCell);
                //}                
            }

            ////加载公共接口下的项
            //for (int i = 0; i < dataList.Count; i++)
            //{
            //    icGroupAndDetail = new IcGroupAndDetail();
            //    icGroupAndDetail = dataList[i] as IcGroupAndDetail;
            //    if (icGroupAndDetail.C_GROUP_CODE_P == "public")
            //    {
            //        ////zhoushuhang 20170417 STORY #27418 [优化]清算核算类参数设置优化 追加接口代码至集合中。
            //        this.groupDetailCodeList.Add(icGroupAndDetail.C_GROUP_CODE);
            //        row = new Yss.KTable.Models.Row();

            //        if (row != null)
            //        {
            //            publicRow.SubRows.Add(row);
            //        }

            //        cell = new Cell();
            //        row.Cells.Add(cell);
            //        cell = new Yss.KTable.Models.Cell();
            //        cell.Text = icGroupAndDetail.C_GROUP_CODE;
            //        cell.Font = new Font(tbMain.Font, FontStyle.Bold);
            //        row.Cells.Add(cell);
            //        cell = new Yss.KTable.Models.Cell();
            //        cell.Text = icGroupAndDetail.C_GROUP_NAME;
            //        cell.Font = new Font(tbMain.Font, FontStyle.Bold);
            //        row.Cells.Add(cell);
            //        row.Tag = icGroupAndDetail;
            //        dictCells.Add(icGroupAndDetail.C_GROUP_CODE, cell);

            //        k(dataList, hasParaItemCodes, row);

            //    }
            //}            

            //IAssetClearCtlService assetClearCtlService = BusiOperServiceFactory.createService<IAssetClearCtlService>();
            //QueryRes queryRes = assetClearCtlService.getUngroupedClearInterfaceByIdf(detailType);
            ///*
            // * Author : ChenLong
            // * Date   : 2017-05-11
            // * Status : Add
            // * Comment: STORY #42091接口加载优化
            // * 优先加载自定义分组，再加载默认分组
            // */
            ///* 分组数据集合 */
            //Dictionary<string, IcGroupAndDetail> groups = new Dictionary<string, IcGroupAndDetail>(); //分组节点集合
            ///* 明细数据集合 */
            //Dictionary<string, List<IcGroupAndDetail>> groupDetails = new Dictionary<string, List<IcGroupAndDetail>>();
            //foreach (IcGroupAndDetail clsIcGronp in queryRes.DataList)
            //{
            //    /* 标识为0的为分组数据否则为明细数据 */
            //    if (clsIcGronp.C_IS_DETAIL == "0")
            //    {
            //        if (!groups.ContainsKey(clsIcGronp.C_GROUP_CODE))
            //        {
            //            groups.Add(clsIcGronp.C_GROUP_CODE, clsIcGronp);
            //        }
            //    }
            //    else
            //    {
            //        List<IcGroupAndDetail> list = null;
            //        if (groupDetails.ContainsKey(clsIcGronp.C_GROUP_CODE_P))
            //        {
            //            list = groupDetails[clsIcGronp.C_GROUP_CODE_P];
            //            list.Add(clsIcGronp);
            //        }
            //        else
            //        {
            //            list = new List<IcGroupAndDetail>();
            //            list.Add(clsIcGronp);
            //            groupDetails.Add(clsIcGronp.C_GROUP_CODE_P, list);
            //        }
					
            //        ////zhoushuhang 20170417 STORY #27418 [优化]清算核算类参数设置优化 追加接口代码至集合中。
            //        this.groupDetailCodeList.Add(clsIcGronp.C_GROUP_CODE);
            //    }
            //}

            ///* 先组装分组数据，在组装明细数据 */
            //foreach (string groupCode in groups.Keys)
            //{
            //    IcGroupAndDetail clsIcGronp = groups[groupCode];
            //    Row groupRow = new Row();
            //    Cell groupCell = new Cell();
            //    groupRow.Cells.Add(groupCell);
            //    groupCell = new Yss.KTable.Models.Cell();
            //    groupCell.Text = clsIcGronp.C_GROUP_CODE;
            //    groupCell.Font = new Font(tbMain.Font, FontStyle.Bold);
            //    groupRow.Cells.Add(groupCell);
            //    groupCell = new Yss.KTable.Models.Cell();
            //    groupCell.Text = clsIcGronp.C_GROUP_NAME;
            //    groupCell.Font = new Font(tbMain.Font, FontStyle.Bold);
            //    groupRow.Cells.Add(groupCell);
            //    groupRow.Tag = clsIcGronp;
            //    List<IcGroupAndDetail> details = groupDetails[groupCode];
            //    foreach (IcGroupAndDetail detail in details)
            //    {
            //        Row subRow = new Row();
            //        cell = new Cell();
            //        subRow.Cells.Add(cell);
            //        cell = new Yss.KTable.Models.Cell();
            //        cell.Text = detail.C_GROUP_CODE;
            //        subRow.Cells.Add(cell);
            //        cell = new Yss.KTable.Models.Cell();
            //        cell.Text = detail.C_GROUP_NAME;
            //        subRow.Cells.Add(cell);
            //        subRow.Tag = detail;
            //        dictCells.Add(detail.C_GROUP_CODE, cell);

            //        ////状态
            //        ColorCell cellStatus = new ColorCell();
            //        cellStatus.Color = Color.Transparent;
            //        ////add by zhoushuhang 20170418 STORY #27418 [优化]清算核算类参数设置优化
            //        cellStatus.ColorCellShape = Yss.KTable.Enums.ColorCellShape.Circle;
            //        subRow.Cells.Add(cellStatus);

            //        ////操作人
            //        cell = new Cell("");
            //        cell.TextAlign = ContentAlignment.MiddleCenter;
            //        subRow.Cells.Add(cell);

            //        ////操作时间
            //        cell = new Cell("");
            //        cell.TextAlign = ContentAlignment.MiddleCenter;
            //        subRow.Cells.Add(cell);

            //        if (hasParaItemCodes.Contains(detail.C_GROUP_CODE))
            //        {
            //            ////相关参数
            //            ButtonCell btnCell = new ButtonCell("相关参数");
            //            btnCell.ToolTip = "设置相关参数";
            //            btnCell.Click += new CellEventHandler(this.btnCellParams_Click);
            //            btnCell.TextAlign = ContentAlignment.MiddleCenter;
            //            btnCell.Tag = subRow;
            //            subRow.Cells.Add(btnCell);
            //        }

            //        groupRow.SubRows.Add(subRow);
            //    }

            //    publicRow.SubRows.Add(groupRow);
            //}            

            tbMain.Tag = dictCells;
            ////读取用户自定义列配置信息
            this.ReadTableColumnsFromConfig(this.tbMain, this.YssFormMenu.C_FUN_NAME);

            //设置Table显示级别 20160929 add by chenyoucai STORY27418
            ReadTableGradingLevel();

            ////zhoushuhang 20170417 STORY #27418 [优化]清算核算类参数设置优化 界面打开则立即刷新状态信息
            tbMain_StatusChanged();
        }

        /// <summary>
        /// 设置Table显示级别
        /// 20160929 add by chenyoucai STORY27418
        /// </summary>
        private void ReadTableGradingLevel()
        {
            string path = ClsConstant.FILE_CustomSettingPath + "\\" + this.YssFormMenu.C_FUN_NAME + ".xml";
            if (File.Exists(path))
            {
                ////读取配置文件
                ClsXmlAdmin xmlAdmin = new ClsXmlAdmin(path);
                XmlNode node = xmlAdmin.getNode("GradingLevel");
                if (node != null)
                {
                    string gradingLevel = node.InnerText;
                    //判断是否为int
                    if (Regex.IsMatch(gradingLevel, @"^[+-]?\d*$"))
                    {
                        this.tbMain.ShowGradingLevel(Convert.ToInt32(gradingLevel));
                    }
                }
                xmlAdmin.Dispose();
                xmlAdmin = null;
            }
        }

        /// <summary>
        /// 加载公共接口
        /// </summary>
        /// <param name="dataList">dataList</param>
        /// <param name="hasParaItemCodes">hasParaItemCodes</param>
        /// <param name="rowP"></param>
        //private void LoadSubPublic(List<BasePojo> dataList, List<string> hasParaItemCodes, Row rowP)
        //{
        //    Yss.KTable.Models.Row row = null;
        //    Yss.KTable.Models.Cell cell = null;
        //    Yss.KTable.Models.Row childRow = null;
        //    string codeP = (rowP.Tag as IcGroupAndDetail).C_GROUP_CODE;
        //    for (int i = 0; i < dataList.Count; i++)
        //    {
        //        IcGroupAndDetail icGroupAndDetail = new IcGroupAndDetail();
        //        icGroupAndDetail = dataList[i] as IcGroupAndDetail;
        //        if (icGroupAndDetail.C_GROUP_CODE_P == codeP)
        //        {
        //            if (icGroupAndDetail.C_IS_DETAIL == "1")
        //            {
        //                childRow = new Yss.KTable.Models.Row();
        //                cell = new Cell();
        //                childRow.Cells.Add(cell);
        //                cell = new Yss.KTable.Models.Cell();
        //                cell.Text = icGroupAndDetail.C_GROUP_CODE;
        //                childRow.Cells.Add(cell);
        //                cell = new Yss.KTable.Models.Cell();
        //                cell.Text = icGroupAndDetail.C_GROUP_NAME;
        //                childRow.Tag = icGroupAndDetail;
        //                childRow.Cells.Add(cell);
        //                dictCells.Add(icGroupAndDetail.C_GROUP_CODE, cell);

        //                ////状态
        //                ColorCell cellStatus = new ColorCell();
        //                cellStatus.Color = Color.Transparent;
        //                ////add by zhoushuhang 20170418 STORY #27418 [优化]清算核算类参数设置优化
        //                cellStatus.ColorCellShape = Yss.KTable.Enums.ColorCellShape.Circle;
        //                childRow.Cells.Add(cellStatus);

        //                ////操作人
        //                cell = new Cell("");
        //                cell.TextAlign = ContentAlignment.MiddleCenter;
        //                childRow.Cells.Add(cell);

        //                ////操作时间
        //                cell = new Cell("");
        //                cell.TextAlign = ContentAlignment.MiddleCenter;
        //                childRow.Cells.Add(cell);

        //                if (hasParaItemCodes.Contains(icGroupAndDetail.C_GROUP_CODE))
        //                {
        //                    ////相关参数
        //                    ButtonCell btnCell = new ButtonCell("相关参数");
        //                    btnCell.ToolTip = "设置相关参数";
        //                    btnCell.Click += new CellEventHandler(this.btnCellParams_Click);
        //                    btnCell.TextAlign = ContentAlignment.MiddleCenter;
        //                    btnCell.Tag = childRow;
        //                    childRow.Cells.Add(btnCell);
        //                }

        //                rowP.SubRows.Add(childRow);

        //                ////Panel panel = new Panel();
        //                ////LabelX label = new LabelX();
        //                ////label.BackColor = Color.Transparent;
        //                ////label.Size = new Size(40, 16);
        //                ////label.Location = new Point(10, 2);
        //                ////panel.Controls.Add(label);
        //                ////Yss.Controls.ImageButton button = new Yss.Controls.ImageButton();
        //                ////button.BackColor = Color.Transparent;
        //                ////button.BackColorAuxiliary = Color.Transparent;
        //                ////button.Dock = DockStyle.None;
        //                ////button.Border.BorderColor = Color.Empty;
        //                ////button.ShowInnerBorder = false;
        //                ////button.BackgroundImageLocation = new Point(1, 1);
        //                ////button.Size = new Size(32, 16);
        //                ////button.Location = new Point(55, 2);
        //                ////button.Click += new System.EventHandler(StateRecord_Click);
        //                ////button.Font = new Font(button.Font, FontStyle.Underline);
        //                ////button.ForeColor = Color.Blue;
        //                ////button.Cursor = Cursors.Hand;
        //                ////button.Text = "状态";
        //                ////button.ToopTip = "状态";
        //                ////button.Tag = childRow;
        //                ////panel.Controls.Add(button);

        //                ////Cell stateCell = new Cell();
        //                ////stateCell.InnerControl = panel;
        //                ////stateCell.InnerControlAlign = Yss.KTable.Enums.CellInnerControlAlign.Fill;
        //                ////childRow.Cells.Add(stateCell);

        //                ////rowP.SubRows.Add(childRow);
        //            }
        //            else
        //            {
        //                row = new Yss.KTable.Models.Row();

        //                if (row != null)
        //                {
        //                    rowP.SubRows.Add(row);
        //                }

        //                cell = new Cell();
        //                row.Cells.Add(cell);
        //                cell = new Yss.KTable.Models.Cell();
        //                cell.Text = icGroupAndDetail.C_GROUP_CODE;
        //                cell.Font = new Font(tbMain.Font, FontStyle.Bold);
        //                row.Cells.Add(cell);
        //                cell = new Yss.KTable.Models.Cell();
        //                cell.Text = icGroupAndDetail.C_GROUP_NAME;
        //                cell.Font = new Font(tbMain.Font, FontStyle.Bold);
        //                row.Cells.Add(cell);
        //                row.Tag = icGroupAndDetail;
        //                dictCells.Add(icGroupAndDetail.C_GROUP_CODE, cell);

        //                LoadSubPublic(dataList, hasParaItemCodes, row);
        //            }

        //        }
        //    }
        //}

        /// <summary>
        /// 增加设置按钮的列头
        /// </summary>
        private void initListHeader()
        {
            tbMain.Columns.Clear();
            Yss.KTable.Models.Column col = null;
            col = new CheckBoxColumn();
            tbMain.Columns.Add(col); //0
            col = new Column("接口代码", "C_GROUP_CODE");
            col.Width = 200;
            col.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Center;
            tbMain.Columns.Add(col);//1
            col = new Column("接口名称", "C_GROUP_NAME");
            //// zhangzhikun  2016/2/17 BUG #126594 公共信息处理界面的分级菜单选择二级时【接口名称】默认长度不能完全显示接口名 
            col.Width = 320;
            col.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Center;
            tbMain.Columns.Add(col);//2
            col = new Column("状态", "Status");
            col.Width = 50;
            col.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Center;
            tbMain.Columns.Add(col);//3
            col = new Column("操作人", "Executor");
            col.Width = 80;
            col.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Center;
            tbMain.Columns.Add(col);//4
            col = new Column("操作时间", "ExecuteDate");
            col.Width = 160;
            col.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Center;
            tbMain.Columns.Add(col);//5
            col = new Column("相关参数", "Params");
            col.Width = 100;
            col.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Center;
            tbMain.Columns.Add(col);//6

            tbMain.ColumnHeight = 21;
            tbMain.ColumnSort = false; //// 不排序
        }

        /// <summary>
        /// 此方法只用于基类的扩展，tbMain.SelectionChanged的真实绑定事件
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void tbMain_SelectionChanged(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            base.tbLeftMain_SelectionChanged(sender, e);

            int cellIndex = tbMain.Columns["Status"].Index;
            this.tbMain.RelayoutRows();
        }

        private void StateRecord_Click(object sender, EventArgs e)
        {
            //Button button = sender as Button;
            //Row row = button.Tag as Row;
            ////IcGroupAndDetail clsIcGronp = row.Cells[2].Tag as IcGroupAndDetail;
            //if (clsIcGronp == null)
            //{
            //    clsIcGronp = row.Tag as IcGroupAndDetail;
            //}
            //chenbo 暂时注释
            //Frm_SYS_INT_StateLog_L form = new Frm_SYS_INT_StateLog_L();
            //form.YssFormMenu = FAST.Core.Context.ClsContext.sysMenuFunHash["runDetailLogQuery"];
            //form.setSearchCondition(clsIcGronp.C_GROUP_CODE, yssDateTime);
            //form.yssInitForm();
            //form.Show();

        }

        /// <summary>
        /// STORY #21836 初始化证券权益信息和财汇权益信息功能调整
        /// 勾选[财汇权益信息]接口时，自动勾选上[初始化证券权益信息]
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        //protected override void tbMain_CheckStateChange(object sender, Yss.KTable.Events.CheckStateChangeEventArgs e)
        //{
        //    base.tbMain_CheckStateChange(sender, e);

        //    Row row = sender as Row;
        //    if (row != null && row.Cells[2].Text == "财汇权益信息")
        //    {
        //        foreach (Row row1 in this.tbMain.Rows)
        //        {
        //            if (row1 != null && row1.Tag != null && (row1.Tag as IcGroupAndDetail).C_GROUP_CODE == "init")
        //            {
        //                foreach (Row subRow in row1.SubRows)
        //                {
        //                    if (subRow != null && subRow.Tag != null && (subRow.Tag as IcGroupAndDetail).C_GROUP_CODE == "initBondEqu")
        //                    {
        //                        if (row.Checked == true)
        //                        {
        //                            subRow.Checked = true;
        //                        }
        //                    }
        //                }
        //            }
        //        }
        //    }
        //}

        #region 清算方案

        /// <summary>
        /// 估值方案保存click事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        //private void btnPlanSave_Click(object sender, EventArgs e)
        //{
        //    List<DvaItem> itemList = new List<DvaItem>();
        //    ////将界面状态改为新增
        //    this.status = ClsEnums.StatusSetting.YssAdd;

        //    try
        //    {
        //        itemList = this.getCheckedItem();

        //        Frm_BUSINESS_PLAN tempForm = new Frm_BUSINESS_PLAN();
        //        tempForm.yssShowForm(this);
        //    }
        //    catch (ClsBaseException ex)
        //    {
        //        ClsBaseException.DiscardException(ex);
        //    }
        //}

        /// <summary>
        ///  方案删除click事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        //private void btnPlanDel_Click(object sender, EventArgs e)
        //{
        //    string planCode = this.cboPlanCode.Value;
        //    string reInfo = "";

        //    try
        //    {
        //        if (null == this.cboPlanCode.Value)
        //        {
        //            YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("003", _formFun, status));
        //            return;
        //        }

        //        reInfo = businessPlanService.deleteByPlanCode(ClsBizCons.PUBCLEAR_PLAN, planCode);
        //        if (null != reInfo && reInfo.Equals("success"))
        //        {
        //            YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtInfo("001", _formFun, status));
        //        }
        //        else
        //        {
        //            YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtError("003", _formFun, status));
        //            return;
        //        }

        //        this.cboPlanCode.Value = "";
        //        tbMain.Refresh();
        //    }
        //    catch (ClsBaseException ex)
        //    {
        //        ClsBaseException.DiscardException(ex);
        //    }
        //}

        /// <summary>
        /// 获取自定义模式主界面选中的核算项目
        /// </summary>
        /// <returns>itemList</returns>
        public List<DvaItem> getCheckedItem()
        {
            List<DvaItem> itemList = new List<DvaItem>();
            DvaItem item = null;
            foreach (Yss.KTable.Models.Row row in tbMain.Rows)
            {
                foreach (Yss.KTable.Models.Row subRow in row.SubRows)
                {
                    if (subRow != null && subRow.Tag != null && (subRow.Tag as IcGroupAndDetail).C_GROUP_CODE_P == "SYSINIT")
                    {
                        if (subRow.Checked)
                        {
                            IcGroupAndDetail real = subRow.Tag as IcGroupAndDetail;
                            item = new DvaItem();
                            item.C_DVA_ITEM_CODE = real.C_GROUP_CODE;
                            item.C_DVA_ITEM_CODE_P = real.C_GROUP_CODE_P;
                            item.C_DVA_ITEM_NAME = real.C_GROUP_NAME;
                            itemList.Add(item);
                        }
                    }
                    else
                    {
                        foreach (Yss.KTable.Models.Row subsubRow in subRow.SubRows)
                        {
                            if (subsubRow.Checked && (subsubRow.Tag as IcGroupAndDetail).C_IS_DETAIL == "1")
                            {
                                IcGroupAndDetail real = subsubRow.Tag as IcGroupAndDetail;
                                item = new DvaItem();
                                item.C_DVA_ITEM_CODE = real.C_GROUP_CODE;
                                item.C_DVA_ITEM_CODE_P = real.C_GROUP_CODE_P;
                                item.C_DVA_ITEM_NAME = real.C_GROUP_NAME;
                                itemList.Add(item);
                            }
                            if (subsubRow.SubRows.Count > 0)
                            {
                                foreach (Yss.KTable.Models.Row subsubRow2 in subsubRow.SubRows)
                                {
                                    if (subsubRow2.Checked)
                                    {
                                        IcGroupAndDetail real = subsubRow.Tag as IcGroupAndDetail;
                                        item = new DvaItem();
                                        item.C_DVA_ITEM_CODE = real.C_GROUP_CODE;
                                        item.C_DVA_ITEM_CODE_P = real.C_GROUP_CODE_P;
                                        item.C_DVA_ITEM_NAME = real.C_GROUP_NAME;
                                        itemList.Add(item);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return itemList;
        }

        /// <summary>
        /// 首选方案改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        //private void cboPlanCode_SelectedValueChanged(object sender, EventArgs e)
        //{

        //    List<BasePojo> dataList = new List<BasePojo>();
        //    List<string> itemList = new List<string>();
        //    Dictionary<string, string> paraDict = new Dictionary<string, string>();
        //    string planCode = "";
        //    if (this.cboPlanCode.SelectedItem != null)
        //    {
        //        BusinessPlan plan = this.cboPlanCode.SelectedItem.DataEntity as BusinessPlan;
        //        if (plan.C_DESC.Trim().Equals("公共清算首选方案"))  ////zzk 20160216 BUG #126547 公共信息处理和资产数据清算的【设为首选方案】功能不能使用 
        //        {
        //            this.cboFirstPlan.Checked = true;
        //        }
        //        else
        //        {
        //            this.cboFirstPlan.Checked = false;
        //        }
        //    }
        //    else
        //    {
        //        this.cboFirstPlan.Checked = false;
        //    }

        //    try
        //    {
        //        dataList = this.getPlanCodeByCondition(paraDict);
        //        planCode = this.cboPlanCode.Value;

        //        foreach (BasePojo basePojo in dataList)
        //        {
        //            BusinessPlan businessPlan = (BusinessPlan)basePojo;
        //            if (businessPlan.C_PLAN_CODE.Equals(planCode))
        //            {
        //                itemList.Add(businessPlan.C_ITEM_CODE);
        //            }
        //        }

        //        ////清空List界面所勾选的核算项
        //        foreach (Yss.KTable.Models.Row row in tbMain.Rows)
        //        {
        //            foreach (Yss.KTable.Models.Row subRow in row.SubRows)
        //            {
        //                subRow.Checked = false;
        //                foreach (Yss.KTable.Models.Row subsubRow in subRow.SubRows)
        //                {
        //                    subsubRow.Checked = false;
        //                }
        //            }
        //        }

        //        IcGroupAndDetail real = null;
        //        foreach (Yss.KTable.Models.Row row in tbMain.Rows)
        //        {
        //            foreach (Yss.KTable.Models.Row subRow in row.SubRows)
        //            {
        //                if (subRow != null && subRow.Tag != null && (subRow.Tag as IcGroupAndDetail).C_GROUP_CODE_P == "SYSINIT")
        //                {
        //                    real = subRow.Tag as IcGroupAndDetail;
        //                    foreach (string code in itemList)
        //                    {
        //                        if (real.C_GROUP_CODE == code)
        //                        {
        //                            subRow.Checked = true;
        //                        }
        //                    }
        //                }
        //                else
        //                {
        //                    foreach (Yss.KTable.Models.Row subsubRow in subRow.SubRows)
        //                    {
        //                        real = subsubRow.Tag as IcGroupAndDetail;
        //                        foreach (string code in itemList)
        //                        {
        //                            if (real.C_GROUP_CODE == code)
        //                            {
        //                                subsubRow.Checked = true;
        //                            }
        //                        }
        //                        if (subsubRow.SubRows.Count > 0)
        //                        {
        //                            foreach (Yss.KTable.Models.Row subsubRow2 in subsubRow.SubRows)
        //                            {
        //                                real = subsubRow2.Tag as IcGroupAndDetail;
        //                                foreach (string code in itemList)
        //                                {
        //                                    if (real.C_GROUP_CODE == code)
        //                                    {
        //                                        subsubRow2.Checked = true;
        //                                    }
        //                                }
        //                            }
        //                        }
        //                    }
        //                }
        //            }
        //        }
        //    }
        //    catch (ClsBaseException ex)
        //    {
        //        ClsBaseException.DiscardException(ex);
        //    }

        //}

        /// <summary>
        /// 首选方案改变事件 zzk 20160216 BUG #126547 公共信息处理和资产数据清算的【设为首选方案】功能不能使用 
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        //private void cboFirstPlan_CheckedChanged(object sender, EventArgs e)
        //{
        //    if (this.cboPlanCode.SelectedItem != null)
        //    {
        //        ArrayList list = new ArrayList();
        //        if (cboFirstPlan.Checked)
        //        {
        //            BusinessPlan plan = null;
        //            BusinessPlan selectedPlan = this.cboPlanCode.SelectedItem.DataEntity as BusinessPlan;
        //            if (!selectedPlan.C_DESC.Equals("公共清算首选方案"))
        //            {
        //                ////这里可能修改2项 
        //                foreach (ControlEntity ce in this.cboPlanCode.Items)
        //                {
        //                    plan = ce.DataEntity as BusinessPlan;
        //                    if (plan.C_DESC.Equals("公共清算首选方案"))
        //                    {
        //                        plan.C_DESC = "";
        //                        list.Add(plan);
        //                    }
        //                }

        //                selectedPlan.C_DESC = "公共清算首选方案";
        //                list.Add(selectedPlan);
        //            }

        //        }
        //        else
        //        {
        //            BusinessPlan plan = this.cboPlanCode.SelectedItem.DataEntity as BusinessPlan;
        //            if (plan.C_DESC.Equals("公共清算首选方案"))
        //            {
        //                plan.C_DESC = "";
        //                list.Add(plan);
        //            }
        //        }

        //        ////更新后台
        //        if (list.Count > 0)
        //        {
        //            businessPlanService.updateById(list);
        //        }
        //    }
        //    else
        //    {
        //        ////自定义模式

        //    }
        //}

        /// <summary>
        /// 查询方案代码
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>list</returns>
        //public List<BasePojo> getPlanCodeByCondition(Dictionary<string, string> paraDict)
        //{
        //    List<BasePojo> dataList = null;
        //    try
        //    {
        //        if (paraDict.ContainsKey("dataClass"))
        //        {
        //            paraDict.Remove("dataClass");
        //        }

        //        paraDict.Add("dataClass", "BusinessPlan");
        //        paraDict.Add("BUSINESS_TYPE", ClsBizCons.PUBCLEAR_PLAN);
        //        dataList = businessPlanService.queryPlanPojo(paraDict).DataList;
        //    }
        //    catch (Exception ex)
        //    {
        //        ClsBaseException.DiscardException(ex);
        //    }

        //    return dataList;
        //}

        //private void cboPlanCode_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        //{
        //    e.Cancel = true;
        //    ////添加估值方案保存添加
        //    ////Add By ：zhengguiyu
        //    ////20140319
        //    Dictionary<string, string> paraDict = new Dictionary<string, string>();
        //    List<BasePojo> formulaList = this.getPlanCodeByCondition(paraDict);
        //    this.fillPlanCodeToCombox(formulaList, this.cboPlanCode);
        //}


        /// <summary>
        ///  向控件中填充数据
        /// </summary>
        /// <param name="dataList">dataList</param>
        /// <param name="combox">combox</param>
        //private void fillPlanCodeToCombox(List<BasePojo> dataList, YssSelCombox combox)
        //{
        //    try
        //    {
        //        combox.Items.Clear(); // 清空控件里面的所有的数据
        //        List<Yss.KRichEx.AutoFilter.Model.KTableEntity> lists = new List<Yss.KRichEx.AutoFilter.Model.KTableEntity>();
        //        Yss.KRichEx.AutoFilter.Model.KTableEntity entity = null;
        //        // 方案代码
        //        combox.DisplayValue = "C_PLAN_CODE";
        //        combox.DisplayName = "C_PLAN_NAME";
        //        combox.Parameter = "C_PLAN_CODE~C_PLAN_NAME";

        //        if (null != dataList && dataList.Count > 0)
        //        {
        //            ////用于排查重复项
        //            List<string> planCodeList = new List<string>();

        //            for (int i = 0; i < dataList.Count; i++)
        //            {
        //                ////筛选出重复的方案代码
        //                BusinessPlan businessPlan = (BusinessPlan)dataList[i];
        //                if (!planCodeList.Contains(businessPlan.C_PLAN_CODE))
        //                {
        //                    BasePojo pojo = dataList[i];
        //                    entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity((BusinessPlan)pojo);
        //                    lists.Add(entity);
        //                    planCodeList.Add(businessPlan.C_PLAN_CODE);
        //                }

        //            }

        //            ////循环list往控件里面塞数据
        //            for (int i = 0; i < lists.Count; i++)
        //            {
        //                combox.Items.Add((Yss.KRichEx.AutoFilter.Model.KTableEntity)lists[i]); // 循环list把对象放到控件中
        //            }
        //        }
        //    }
        //    catch (Exception ex)
        //    {
        //        ClsBaseException.DiscardException(ex);
        //    }
        //}

        #endregion;

        #region 操作日志相关方法 add by Yuntao Lau 2016.02.02 STORY #27418
        /// <summary>
        /// 相关参数按钮点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        //private void btnCellParams_Click(object sender, EventArgs e)
        //{
        //    Frm_PARAMS_S frm = new Frm_PARAMS_S();
        //    frm.YssFormMenu = FAST.Core.Context.ClsContext.sysFunHash["indexmanage"];
        //    frm.YssStatus = ClsEnums.StatusSetting.YssBrow;
        //    frm.setSearchCondition(this.yssBuildLeftCheckRowsStr("portfolio").Replace("'", ","), (((sender as ButtonCell).Tag as Row).Tag as IcGroupAndDetail).C_GROUP_CODE);
        //    frm.initControlStat();
        //    frm.ShowDialog();
        //}

        /// <summary>
        /// 刷新按钮点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnRefresh_Click(object sender, EventArgs e)
        {
            Thread t = new Thread(delegate()
            {
                updateTabLog(true);
            });
            t.Start();
        }

        /// <summary>
        /// zhoushuhang 2017-03-29 STORY #27418 [优化]清算核算类参数设置优化
        /// 刷新业务操作日志
        /// </summary>
        /// <param name="flag">参数值</param>
        private void updateTabLog(bool flag)
        {
            ////zhoushuhang 20170726 任务调度不需要刷新业务操作日志这一块。所以这个地方追加区分条件
            if (this._formFun != null && "sv_sysInit".Equals(this._formFun.C_FUN_CODE) && "publicOper".Equals(this._formFun.C_FUN_CODE_P))
            {
                if (this.tbMain.InvokeRequired)
                {
                    UpdateTableLog updateLog = new UpdateTableLog(updateTabLog);
                    this.tbMain.Invoke(updateLog, new object[] { flag });
                }
                else
                {
                    ////zhoushuhang 2017-03-29 STORY #27418 [优化]清算核算类参数设置优化
                    IBizRunLogService runLogService = ServiceFactory.createService<IBizRunLogService>();
                    Dictionary<string, string> paraDict = null;
                    Dictionary<string, string> rowDict = null;
                    paraDict = new Dictionary<string, string>();
                    paraDict.Add("C_FUN_CODE", this._formFun.C_FUN_CODE);
                    paraDict.Add("D_TRADE_START", this.yssDateTime.getBeginDateStr);
                    paraDict.Add("D_TRADE_END", this.yssDateTime.getEndDateStr);
                    rowDict = runLogService.queryRecentResult(paraDict, this.groupDetailCodeList);            

                    ////回填操作状态
                    setStatus(rowDict);
                }
            }
        }

        /// <summary>
        /// 递归获取明细行
        /// </summary>
        /// <param name="row">row</param>
        /// <param name="detailRows">detailRows</param>
        private void getDetailRows(Row row, List<Row> detailRows)
        {
            foreach (Row tempRow in row.SubRows)
            {
                if (tempRow.SubRows.Count > 0)
                {
                    getDetailRows(tempRow, detailRows);
                }
                else
                {
                    detailRows.Add(tempRow);
                }
            }
        }

        /// <summary>
        /// 设置执行状态
        /// </summary>
        /// <param name="cell">cell</param>
        /// <param name="status">status</param>
        private void setRunStatusCtl(Cell cell, string status)
        {
            if (this.tbMain.InvokeRequired)
            {
                SetRunStatusCtl statusCtl = new SetRunStatusCtl(setRunStatusCtl);
                object[] obj = new object[] { cell, status };
                this.tbMain.Invoke(statusCtl, obj);
            }
            else
            {
                if (string.Equals(status, "Success"))
                {
                    ((ColorCell)cell).Color = Color.Green;
                    ((ColorCell)cell).ToolTip = "执行成功";
                }
                else if (string.Equals(status, "Fail"))
                {
                    ((ColorCell)cell).Color = Color.Red;
                    ((ColorCell)cell).ToolTip = "执行失败";
                }
                else if (string.Equals(status, "Warn"))
                {
                    ((ColorCell)cell).Color = Color.Yellow;
                    ((ColorCell)cell).ToolTip = "警告";
                }
            }
        }

        /// <summary>
        /// zhoushuhang 20170417 STORY #27418 [优化]清算核算类参数设置优化
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void tbMain_StatusChanged()
        {
            ////先将单元格里面的值至空值
            setCellNull();
            ////放入线程、加入委托
            Thread t = new Thread(delegate()
            {
                updateTabLog(false);
            });
            t.Start();
        }

        /// <summary>
        /// zhoushuhang 2017-03-29 STORY #27418 [优化]清算核算类参数设置优化
        /// 回填执行状态
        /// </summary>
        /// <param name="rowDict">回填主区域行对象结合</param>
        private void setStatus(Dictionary<string, string> rowDict)
        {
            ClsRecordPojo record = null;
            //// modified by HeLiang 2017-06-05 初始化数据库功能测试BUG修改
            //// 从【调度方案处理】执行“公共信息处理”和“资产清算”结束后this.tbMain.Columns.Count是等于0的，没有值，直接取索引号会报空指针异常，因此添加一层判断
            if (this.tbMain.Columns.Count != 0)
            {
                ////获取状态、操作人、操作时间索引号
                int indexStatus = this.tbMain.Columns["Status"].Index;
                int indexExecutor = this.tbMain.Columns["Executor"].Index;
                int indexExecuteDate = this.tbMain.Columns["ExecuteDate"].Index;

                ////获取所有行
                RowCollection allRows = this.tbMain.GetAllRows(this.tbMain.Rows, true, true);
                for (int rowIndex = 0; rowIndex < allRows.Count; rowIndex++)
                {
                    Row tempRow = allRows[rowIndex];
                    IcGroupAndDetail groupDetail = tempRow.Tag as IcGroupAndDetail;
                    if (!"1".Equals(groupDetail.C_IS_DETAIL))
                    {
                        continue;
                    }

                    if (rowDict.Count > 0)
                    {
                        ////如果后台查询的结果集合键值包含清算接口代码。则将状态回填至单元格内。
                        if (!rowDict.Keys.Contains(groupDetail.C_GROUP_CODE))
                        {
                            continue;
                        }

                        record = new ClsRecordPojo();
                        record.listItemParse(rowDict[groupDetail.C_GROUP_CODE]);
                        setRunStatusCtl(tempRow.Cells[indexStatus], Convert.ToString(record.DoingOperType));
                        tempRow.Cells[indexExecutor].Text = record.C_UPDATE_BY;
                        tempRow.Cells[indexExecuteDate].Text = record.C_END_TIME;
                        tempRow.Cells[indexExecuteDate].ToolTip = "业务日期：" + record.D_TRADE.ToShortDateString();
                    }
                    else
                    {
                        ((ColorCell)tempRow.Cells[indexStatus]).Color = Color.Transparent;
                        tempRow.Cells[indexStatus].ToolTip = "当日无操作";
                        tempRow.Cells[indexExecutor].Text = "";
                        tempRow.Cells[indexExecuteDate].Text = "";
                    }
                }
            }
        }

        /// <summary>
        /// zhoushuhang 2017-03-29 STORY #27418 [优化]清算核算类参数设置优化
        /// 获取选择执行的业务代码
        /// </summary>
        /// <returns>拼接的业务代码字符串</returns>
        private string getItemCode()
        {
            //// 定义接收字符串缓冲池
            StringBuilder buffer = new StringBuilder();
            ////获取选择行。后面只处理选择行
            foreach (Row row in tbMain.CheckedRows)
            {
                if (row.SubRows.Count == 0)
                {
                    ////获取选择行的接口代码
                    string itemCode = (row.Tag as IcGroupAndDetail).C_GROUP_CODE;
                    buffer.Append(itemCode).Append(','); ////将接口代码拼接
                }
            }

            // 返回选中行的公共信息处理接口代码
            string c_itemCode = "";
            ////将缓冲池数据转换成字符串格式
            c_itemCode = buffer.ToString();
            if (c_itemCode != null && c_itemCode.EndsWith(","))
            {
                //// 去掉最后一个逗号
                c_itemCode = c_itemCode.Substring(0, c_itemCode.Length - 1);
            }

            // 如果长度为0，返回'';
            if (c_itemCode.Length == 0)
            {
                c_itemCode = "''";
            }

            return c_itemCode;
        }

        /// <summary>
        /// zhoushuhang 2017-04-26 STORY #27418 [优化]清算核算类参数设置优化
        /// 设置单元格空值
        /// </summary>
        private void setCellNull()
        {
            int indexStatus = this.tbMain.Columns["Status"].Index;
            int indexExecutor = this.tbMain.Columns["Executor"].Index;
            int indexExecuteDate = this.tbMain.Columns["ExecuteDate"].Index;
            ////A区组合更换焦点。首先先将单元格里面的值至空值
            RowCollection allRows = this.tbMain.GetAllRows(this.tbMain.Rows, true, true);
            for (int rowIndex = 0; rowIndex < allRows.Count; rowIndex++)
            {
                Row tempRow = allRows[rowIndex];
                IcGroupAndDetail groupDetail = tempRow.Tag as IcGroupAndDetail;
                if (!"1".Equals(groupDetail.C_IS_DETAIL))
                {
                    continue;
                }

                ((ColorCell)tempRow.Cells[indexStatus]).Color = Color.Transparent;
                tempRow.Cells[indexStatus].ToolTip = "";
                tempRow.Cells[indexExecutor].Text = "";
                tempRow.Cells[indexExecuteDate].Text = "";
            }
        }

        /// <summary>
        /// zhoushuhang 2017-04-26 STORY #27418 [优化]清算核算类参数设置优化
        /// 第一个时间控件值改变事件。刷新接口最近一日的操作状态
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void yssDateTime_FirstdateTimeInputValueChanged(object sender, EventArgs e)
        {
            ////zhoushuhang 20170519 BUG #160469 日常运营（任务调度）执行 清算、核算、公共信息处理报错。界面直接卡死
            if (this._formFun != null && "sysInit".Equals(this._formFun.C_FUN_CODE))
            {
                ////先将单元格里面的值至空值
                setCellNull();
                ////放入线程、加入委托
                Thread t = new Thread(delegate()
                {
                    updateTabLog(false);
                });
                t.Start();
            }
        }

        /// <summary>
        /// zhoushuhang 2017-04-26 STORY #27418 [优化]清算核算类参数设置优化
        /// 第二个时间控件值改变事件。刷新接口最近一日的操作状态
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void yssDateTime_SeconddateTimeInputValueChanged(object sender, EventArgs e)
        {
            ////zhoushuhang 20170519 BUG #160469 日常运营（任务调度）执行 清算、核算、公共信息处理报错。界面直接卡死
            if (this._formFun != null && "sysInit".Equals(this._formFun.C_FUN_CODE))
            {
                ////先将单元格里面的值至空值
                setCellNull();
                ////放入线程、加入委托
                Thread t = new Thread(delegate()
                {
                    updateTabLog(false);
                });
                t.Start();
            }
        }

        /// <summary>
        /// 任务线程停止事件
        /// jiangjin-20160729-将监控调整到业务处理之后执行       
        /// </summary>
        protected override void OnThreadStoped()
        {
            ButtonItem buttonItem = getCheckedButton();
            //// Fixed by huangsq 20180816 解决对象为null导致系统崩溃
            if (buttonItem != null)
            {
                string name = buttonItem.Name;
                if (name == "monitorAndBusinessButton")
                {
                    this.btnMonitor_Click(buttonItem, null);
                }
            }

            btnRefresh_Click(null, null);
        }

        /// <summary>
        /// 重写查询按钮事件，修复右键 还原默认列信息 报错
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        public override QueryRes getMainListDataMVC(Dictionary<string, string> paraDict, bool isQueryData)
        {
            this.tbMain.Rows.Clear();
            return this.getMainListDataMVC(new BasePojo(), isQueryData);
        }

        #endregion

        #region 邮件相关 STORY #37731 邮件功能整合 by leijianhua 20160109
        /// <summary>
        /// 导出到邮件
        /// </summary>
        /// <param name="isAfterMonitor">是否是监控之后</param>
        /// <param name="tempTable">导出的table</param>
        //public override void ExportToMail(bool isAfterMonitor, Table tempTable)
        //{
        //    ExportMailFun fun = new ExportMailFun(this);
        //    fun.sendMail(isAfterMonitor, tempTable);
        //}

        /// <summary>
        /// 邮件策略点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        //public override void btnSetMailPolicy_Click(object sender, EventArgs e)
        //{
        //    ExportMailFun fun = new ExportMailFun(this);
        //    fun.btnSetMailPolicy_Click();
        //}
        #endregion

        ////STORY #50779 日终业务工具栏调整，查询按钮固化在工具栏右侧，界面缩小后依旧可见。注销，张绍林-20171227
        /////// <summary>
        /////// 不添加查询按钮
        /////// liuxiang 2017-1-9 BUG147177汇集调尾处理界面缺少查询按钮
        /////// </summary>
        ////protected override void AddSearchButton()
        ////{
        ////}        

        /// <summary>
        /// 业务执行按钮名称
        /// </summary>
        /// <returns>名称</returns>
        protected override string GetBusinessButton()
        {
            return "处理";
        }

        /// <summary>
        /// 监控业务执行按钮名称
        /// </summary>
        /// <returns>名称</returns>
        protected override string getMonitorAndBusinessButton()
        {
            return "监控并处理";
        }

        /// <summary>
        /// Author : ChenLong
        /// Date   : 2016-03-18
        /// Status : Add
        /// Comment: 公共指标没有组合
        /// </summary>
        /// <returns></returns>
        protected override Dictionary<string, string> GetMonitorPortInfo()
        {
            Dictionary<string, string> portCodes = new Dictionary<string, string>();
            portCodes.Add("NA", "NA");
            return portCodes;
        }

        /// <summary>
        /// Author : ChenLong
        /// Date   : 2016-03-18
        /// Status : Add
        /// Comment: 公共处理界面业务项取值和其他执行界面不同
        /// </summary>
        /// <param name="rows">行</param>
        /// <param name="bizCodes">业务代码集合</param>
        /// <param name="bizCodeProperty">代码属性</param>
        /// <param name="bizNameProperty">名称属性</param>
        public override void GetMonitorBizInfo(RowCollection rows, Dictionary<string, string> bizCodes, string bizCodeProperty, string bizNameProperty)
        {
            foreach (Row row in rows)
            {
                if (row.SubRows.Count > 0)
                {
                    continue;
                }

                if (row.Checked)
                {
                    IcGroupAndDetail pojo = (IcGroupAndDetail)row.Tag;
                    string bizCode = pojo.GetValue(bizCodeProperty).ToString();
                    if (string.IsNullOrEmpty(bizCode) == false)
                    {
                        string bizName = pojo.GetValue(bizNameProperty).ToString();
                        bizCodes.Add(bizCode, bizName);
                    }
                }
            }
        }

    
       
        /// <summary>
        /// Author : ChenLong
        /// Date   : 2016-01-13
        /// Status : Add
        /// Comment: 执行事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected void btnClick(object sender, EventArgs e)
        {
            ButtonItem buttonItem = getCheckedButton();
            ////【南方基金】日常运营界面点击执行报错 liuyanni 20160825
            if (buttonItem == null)
            {
                return;
            }
            string name = buttonItem.Name;

            if (name == "monitorButton")
            {
                this.btnMonitor_Click(buttonItem, e);
            }
            else if (name == "monitorAndBusinessButton")
            {
                /*jiangjin-20160729-将监控调整到业务处理之后执行
                  计时器无法在线程中开启，所以在线程启动前现将其开启*/
                base.btnExecute_Click(buttonItem, e);
            }
            else
            {
                base.btnExecute_Click(buttonItem, e);
            }
        }

        /// <summary>
        /// 覆写执行事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnExecute_Click(object sender, EventArgs e)
        {
            btnClick(sender, e);
        }

        /// <summary>
        /// 获取监控日期段起始时间。
        /// </summary>
        protected override string MonitorDateBegin
        {
            get
            {
                return this.yssDateTime.getBeginDateStr;
            }
        }

        /// <summary>
        /// 获取监控日期段结束时间。
        /// </summary>
        protected override string MonitorDateEnd
        {
            get
            {
                return this.yssDateTime.getEndDateStr;
            }
        }

        /// <summary>
        /// 获取业务代码属性名。
        /// </summary>
        protected override string BizCodeProperty
        {
            get { return "C_GROUP_CODE"; }
        }

        /// <summary>
        /// 获取业务名称属性名。
        /// </summary>
        protected override string BizNameProperty
        {
            get { return "C_GROUP_NAME"; }
        }
    }
}
