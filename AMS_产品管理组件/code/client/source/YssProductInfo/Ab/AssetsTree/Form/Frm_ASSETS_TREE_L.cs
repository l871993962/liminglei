﻿/// <summary>
//-----------------------------------------------------------------------
// <copyright file="Frm_ASSETS_TREE_L.cs" company="yss">
//     Company copyright tag.
// </copyright>
//-----------------------------------------------------------------------
/// <summary>
namespace YssProductInfo.Ab.AssetsTree.Form
{
    using System;
    using System.Collections;
    using System.Collections.Generic;
    using System.Drawing;
    using System.Windows.Forms;
    using FAST.Common.Service.Datastructure;
    using FAST.Common.Service.Pojo;
    using FAST.Common.Service.Pojo.Base;
    using FAST.Common.Service.Services;
    using FAST.Common.Service.Services.Base;
    using FAST.Core.BaseControl.Fun;
    using FAST.Core.BaseControl.Pojo;
    using FAST.Core.Communication.DataService;
    using FAST.Core.Communication.Service;
    using FAST.Core.Context;
    using FAST.Core.CRUD.Form;
    using FAST.Core.Exceptions;
    using FAST.Core.Resource;
    using FAST.Core.Util;
    using Yss.Controls;
    using Yss.KMessage;
    using Yss.KTable.Collections;
    using Yss.KTable.Enums;
    using Yss.KTable.Models;
    using FAST.Core.BaseControl.YssButtonBars;
    using YssInformation.Support.Fun;
    using YssProductInfo.Support.Ab.AssetsTree.Pojo;
    using YssProductInfo.Support.Ab.AssetsTree.Service;
    using FAST.Core.Context.Events;
    using System.IO;
    using System.Xml;
    using FAST.Common.Service.DataService;

    /// <summary>
    /// 功能简介：投资经理信息浏览界面，负责投资经理信息的显示和查询功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.06
    ///
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：chenyoulong
    /// 修改日期：20101206
    /// 修改简介：方法功能的具体实现
    /// 
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：lyh
    /// 修改日期：20110128
    /// 修改简介：隐藏a区
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：wuwenlan
    /// 修改日期：20110215
    /// 修改简介：添加回收站机制和自定义表头
    /// 去掉多余的帅选条件
    /// 
    ///－－－－修改记录－－－－
    /// 当前版本：V4.5.0.5
    /// 修改人：chenyoulong
    /// 修改日期：20110228
    /// 修改简介：
    /// 1、代码注释完善（包括方法作用注释，逻辑注释，类修改注释）
    /// 2、提示信息统一调整(前台用五个参数YssMessageBox.ShowDyanInformation("初始化窗体出错", ye.Message, "错误信息", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail)）
    /// 3、需求中内容没有实现功能的调整 
    /// </summary>
    public partial class Frm_ASSETS_TREE_L : FrmBaseList
    {
        /// <summary>
        /// A区数据服务对象
        /// </summary>
        private IAssertTree_AService assetTreeAService;

        /// <summary>
        /// add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
        /// B区数据服务对象
        /// </summary>
        private IAssertTreeService assetTreeBService;

        /// <summary>
        /// 是否显示复选框
        /// </summary>
        public bool showCheckColumn = false;

        /// <summary>
        /// 是否显示行号
        /// </summary>
        public bool showMarkColumn = false;

        /// <summary>
        /// pojo id
        /// </summary>
        private string id = "";

        /// <summary>
        /// boolAssItem_Click
        /// </summary>
        private bool boolAssItem_Click = false;


        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_ASSETS_TREE_L()
        {
            InitializeComponent();
            hasLeftSetForm = true;
            bUseMVCServiceLeft = true;
            bUseMVCService = true;
            ////add by zhoushuhang 2018-03-15 STORY49928产品树形结构界面优化
            this.tbLeftMain.ShowCheckBox = false;
            this.YssMainKTableShowMode = FAST.Core.Context.ClsEnums.KTableDataShowMode.TreeMode;
            this.ShowRowCheckBoxColumn = true;
            this.ShowRowIndexColumn = true;
            this.tbMain.ReadOnly = false;
            ////BUG #286534 【紧急】【招商财富300】0930版本产品树形结构分页显示有问题
            this.ShowPageInation = false;
            this.addSubButtons();
        }

        /// <summary>
        /// pojo  id
        /// </summary>
        public string Id
        {
            get { return id; }
            set { id = value; }
        }


        /// <summary>
        /// 重写A区配置信息，走基类老旧模式。
        /// </summary>
        public override AreaAConfigInfo AreaAConfigInfo
        {
            get
            {
                base.AreaAConfigInfo.AreaType = AreaType.BaseDefault;
                return base.AreaAConfigInfo;
            }
        }

        /// <summary>
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写.
        /// ------- 修改记录----------.
        /// 当前版本：V4.5.0.1.
        /// 修改人：chenyoulong.
        /// 修改日期：2011.02.28.
        /// 修改简介：注释完善，修改异常提示信息.
        /// </summary>
        /// <returns>返回初始查询条件</returns>
        public override string yssInitQuery()
        {
            // 所有提供的参数项如下，只需要设置子类需要的项即可
            // 1 查询条件
            string cond = string.Empty;
              
            ////3 初始只加载列头，若需要则设为true，反之不需要设置此参数
            this.IsOnlyHeder = true;

            return cond;
        }

        /// <summary>
        /// 重写行的双击事件
        /// 根据双击后的数据条件加载相关数据到中间主界面控件中
        /// 2017-02-06 zhujinyang 36179 双击未分配节点时，不允许弹出弹窗
        /// </summary>
        /// <param name="sender">控件对象</param>
        /// <param name="e">事件信息</param>
        protected override void tbLeftMain_RowDoubleClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            if ("ASS,ZCTG,ZCGL,ZCWT,ZCZL,TGZC,GLZC,NSPL,ZCMXLX,CPZT".Contains(((AssetsTree_A)(sender as Yss.KTable.Models.Row).Tag).C_TR_CODE))
            {
                return;
            }

            if ((sender as Yss.KTable.Models.Row).Cells[0].Text == "未分配")
            {
                hasLeftSetForm = false;
            }
            else 
            {
                hasLeftSetForm = true;
            }

            if (hasLeftSetForm)
            {
                showLeftFormSet(ClsEnums.StatusSetting.YssBrow);
            }
            else
            {
                return;
            }
        }

        /// <summary>
        /// 获取list查询条件区的查询条件.
        /// </summary>
        /// <returns>返回带条件查询条件</returns>
        public string yssGetListCond()
        {
            string cond = string.Empty;
            ////获取方案代码和方案类型
            if (this.tbLeftMain.SelectedRow != null)
            {
                ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
                //// modified by HeLiang 2017-06-20 单元测试临时修改
                quyStrUtil.addQuyCon("C_TR_CODE", this.yssBuildLeftCheckRowsStr("pd_assetsTree_A"), ClsConstant.SQL_RA_HYPHEN_IN);
                cond = quyStrUtil.getQuyStr("assetsTree"); 
            }
            else
            {
                cond = "onlyHeader";
            }

            return cond;

        }

        /// <summary>
        /// 封装条件对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            //// modified by HeLiang 2017-06-20 单元测试临时修改
            ////string search = this.yssBuildLeftCheckRowsStr("pd_assetsTree_A");
            ////查询“未分配”时，多传一个值  2017-02-06 zhujinyang STORY36179
            ////string test = this.yssBuildLeftCheckRowsStr("pd_assetsTree_A", "C_TR_NAME");
            ////add by zhoushuhang 2018-03-15 STORY49928产品树形结构界面优化
            string search = string.Empty;
            string test = string.Empty;
            if (this.TableLeftMain.SelectedRow != null)
            {
                AssetsTree_A pojo = this.TableLeftMain.SelectedRow.Tag as AssetsTree_A;
                search = pojo.C_TR_CODE;
                test = pojo.C_TR_NAME;
                if (!string.IsNullOrEmpty(pojo.C_DV_UN_DIS) && "1".Equals(pojo.C_DV_UN_DIS))
                {
                    paraDict.Add("test", search);
                    paraDict.Add("C_TR_CODE", search);
                    //// edit by zhoushuhang 20180320 BUG196104产品树形结构测试问题汇总
                    paraDict.Add("ARRAY_C_PORT_CODE_RIGHT", ClsContext.DataRightString);
                }
            }

            ////if ("未分配".Equals(test))
            ////{
            ////    paraDict.Add("test", search);
            ////    search = Regex.Replace(search, "'", "");
            ////    paraDict.Add("C_TR_CODE", search);
            ////}
            ////else 
            ////{
            ////    search = Regex.Replace(search, "'", "");
            ////    paraDict.Add("ARRAY_C_TR_CODE", search);
                
            ////}

            paraDict.Add("C_TR_CODE_R", search);
            ////add by zhoushuhang 2018-03-05 STORY49928产品树形结构界面优化
            if (this.cboPort.Value != null && this.cboPort.Value.Trim().Length > 0)
            {
                paraDict.Add("ARRAY_C_PORT_CODE", this.cboPort.Value.Replace("|", ","));
            }
            //// BUG #220125 树形结构查询不方便，增加一个基于用户的查询条件 add by liushifa 
            if (this.cboUser.Value != null && this.cboUser.Value.Trim().Length > 0)
            {
                paraDict.Add("ARRAY_C_TR_CODE", this.cboUser.Value.Replace("|", ","));
            }

            return paraDict;
        }

        /// <summary>
        /// list界面加载A区数据，子类重写.
        /// </summary>
        /// <returns>查询数据对象</returns>
        public override QueryRes yssGetLeftDataMVC()
        {
            this.YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;
            QueryRes res = null;
            //// modified by HeLiang 2017-06-20 单元测试临时修改
            leftFormFunCode = "pd_assetsTree_A";
            leftDataFunCode = "pd_assetsTree_A";
            if (this.assetTreeAService == null) 
            {
                ClsAssocia asc = new ClsAssocia();
                asc = ClsClzCfgMgr.getAssociaParam("pd_assetsTree_A");

                Type serviceTypeA = ReflectBase.YssGetType(asc.ServiceDllName, asc.ServiceName);
                this.assetTreeAService = (IAssertTree_AService)ServiceFactory.createService(serviceTypeA);
            }

            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            ////add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
            paraDict.Add("C_TR_CODE_P", "[root]");
            res = this.assetTreeAService.getTreeViewData(paraDict);
            this.matchSearchStr = new string[] { "C_TR_CODE", "C_TR_CODE_P", "C_TR_NAME" }; // 【搜索】功能匹配的属性
            return res;
        }

        /// <summary>
        /// 增加Table用户菜单右键
        /// </summary>
        /// <param name="tabSource">Table</param>
        private void AddTableContextMenu(Table tabSource)
        {
            if (tabSource == null || tabSource.ContextMenuStrip.Items.ContainsKey("ShowHideAss"))
            {
                return;
            }

            if (tabSource.DefaultToolStripItems == SysToolStripItems.None)
            {
                tabSource.DefaultToolStripItems = SysToolStripItems.Custom;
            }

            ToolStripMenuItem assItem = new ToolStripMenuItem("显示隐藏");
            assItem.Name = "ShowHideAss";
            assItem.Tag = tabSource;
            assItem.Click += new EventHandler(AssItem_Click);
            tabSource.AddContextMenuStripItem(assItem);
        }

        /// <summary>
        /// Table自定义右键菜单事件。
        /// </summary>
        /// <param name="sender">ToolStripMenuItem</param>
        /// <param name="e">EventArgs</param>
        private void AssItem_Click(object sender, EventArgs e)
        {
            boolAssItem_Click = true;
            QueryRes res = this.yssGetLeftDataMVC();
            CheckItemCollection checkItems = new CheckItemCollection();
            CheckItem checkItem = null;
            foreach (BasePojo pojo in res.DataList)
            {
                AssertTreeATreeView view = pojo as AssertTreeATreeView;
                checkItem = new CheckItem(view.C_TR_NAME);
                checkItem.Name = view.C_TR_CODE;
                checkItems.Add(checkItem);
                checkItem.CheckedChanged += new EventHandler(CheckItem_CheckedChanged);
            }

            this.ReadAssItemFromConfig(checkItems, null, "CustomAssType");
            this.TableLeftMain.ShowCheckItems(checkItems);

            boolAssItem_Click = false;

        }
        
        /// <summary>
        /// 读取配置文件
        /// </summary>
        /// <param name="checkItems">下拉选项</param>
        /// <param name="res">显示数据集</param>
        /// <param name="configName">配置文件名称</param>
        public void ReadAssItemFromConfig(CheckItemCollection checkItems, QueryRes res, string configName)
        {
            string strFile = ClsConstant.FILE_CustomSettingPath + "\\CustomAssType.xml";
            if (File.Exists(strFile) == false)
            {
                return;
            }

            List<BasePojo> list = new List<BasePojo>();
            try
            {
                ClsXmlAdmin clsXmlAdmin = new ClsXmlAdmin();
                clsXmlAdmin.loadFile(strFile);
                XmlNode xmlNodeAssList = clsXmlAdmin.getNode("AssType");
               //// XmlNodeList xmlNodeList = xmlAdmin.GetNodeList("AssType");
                if (checkItems != null && checkItems.Count > 0)
                {
                    foreach (XmlNode xmlNode in xmlNodeAssList.ChildNodes)
                    {
                        string strAssCode = xmlNode.Attributes["Code"].Value;
                        string strbool = xmlNode.Attributes["Visible"].Value;
                        foreach (CheckItem item in checkItems)
                        {
                            if (strAssCode.Equals(item.Name))
                            {
                                if ("true".Equals(strbool))
                                {
                                    item.Checked = true;
                                }

                                break;
                            }
                        }
                    }
                }

                if (res != null && res.DataList != null && res.DataList.Count > 0)
                {
                    foreach (XmlNode xmlNode in xmlNodeAssList.ChildNodes)
                    {
                        string strAssCode = xmlNode.Attributes["Code"].Value;
                        string strbool = xmlNode.Attributes["Visible"].Value;
                        foreach (BasePojo item in res.DataList)
                        {
                            AssertTreeATreeView view = item as AssertTreeATreeView;
                            if (strAssCode.Equals(view.C_TR_CODE))
                            {
                                if ("true".Equals(strbool))
                                {
                                    list.Add(view);
                                }

                                break;
                            }
                        }
                    }

                    res.DataList.Clear();
                    res.DataList.AddRange(list);
                }

                clsXmlAdmin.Dispose();
                clsXmlAdmin = null;
            }
            catch (Exception ex)
            {
                ClsLogger.Error(ex);
            }
        }

        /// <summary>
        /// 第一次默认全选写入配置文件
        /// </summary>
        /// <param name="res">下拉选项</param>
        /// <param name="configName">文件名称</param>
        private void FirstWriteAssItemsToConfig(QueryRes res, string configName)
        {
            ////没有配置文件的时候 默认配置全选
            if (!Directory.Exists(ClsConstant.FILE_CustomSettingPath))
            {
                Directory.CreateDirectory(ClsConstant.FILE_CustomSettingPath);
            }

            string path = ClsConstant.FILE_CustomSettingPath + "\\" + configName + ".xml";
            if (!File.Exists(path))
            {
                if (res != null && res.DataList != null && res.DataList.Count > 0)
                {
                    ClsXmlAdmin xmlAdmin = new ClsXmlAdmin(ClsConstant.FILE_CustomSettingPath + "\\" + configName + ".xml");
                    xmlAdmin.AutoSave = false;
                    foreach (BasePojo item in res.DataList)
                    {
                        AssertTreeATreeView view = item as AssertTreeATreeView;
                        XmlNode xmlNodeAssType = xmlAdmin.getNode("AssType");
                        if (xmlNodeAssType == null)
                        {
                            xmlAdmin.addNode(string.Empty, "AssType");
                            XmlNode xmlNode = xmlAdmin.addNode("AssType", "Item");
                            xmlAdmin.addAttr(xmlNode, "Code", view.C_TR_CODE);
                            xmlAdmin.addAttr(xmlNode, "Name", view.C_TR_NAME);
                            xmlAdmin.addAttr(xmlNode, "Visible", "true");

                        }
                        else
                        {
                            ////不存在节点,则新增节点
                            XmlNode xmlNodeadd = xmlAdmin.addNode("AssType", "Item");
                            xmlAdmin.addAttr(xmlNodeadd, "Code", view.C_TR_CODE);
                            xmlAdmin.addAttr(xmlNodeadd, "Name", view.C_TR_NAME);
                            xmlAdmin.addAttr(xmlNodeadd, "Visible", "true");

                        }

                    }

                    xmlAdmin.SaveXmlDocument();
                    xmlAdmin.Dispose();
                    xmlAdmin = null;
                }
            }

        }
    


       /// <summary>
       /// 写入配置文件
       /// </summary>
       /// <param name="checkItem">下拉选项</param>
       /// <param name="configName">文件名称</param>
        private void WriteAssItemsToConfig(CheckItem checkItem, string configName)
        {
            if (!Directory.Exists(ClsConstant.FILE_CustomSettingPath))
            {
                Directory.CreateDirectory(ClsConstant.FILE_CustomSettingPath);
            }

            if (checkItem != null)
            {
                ClsXmlAdmin xmlAdmin = new ClsXmlAdmin(ClsConstant.FILE_CustomSettingPath + "\\" + configName + ".xml");
                xmlAdmin.AutoSave = false;
                XmlNode xmlNodeAssType = xmlAdmin.getNode("AssType");
                ////XmlNodeList xmlNodeList = xmlAdmin.GetNodeList("AssType");

                if (xmlNodeAssType == null)
                {
                    xmlAdmin.addNode(string.Empty, "AssType");
                    XmlNode xmlNode = xmlAdmin.addNode("AssType", "Item");
                    xmlAdmin.addAttr(xmlNode, "Code", checkItem.Name);
                    xmlAdmin.addAttr(xmlNode, "Name", checkItem.Text);
                    xmlAdmin.addAttr(xmlNode, "Visible", "true");

                }
                else
                {
                    XmlNode xmlNode = xmlAdmin.GetNode("AssType/Item", "Code", checkItem.Name);
                    if (xmlNode != null)
                    {           ////存在节点
                        string strbool = xmlAdmin.GetAttrValue(xmlNode, "Visible");

                        if (checkItem.Checked)
                        {
                            xmlAdmin.ModfiyAttrValue(xmlNode, "Visible", "true");
                        }
                        else
                        {
                            xmlAdmin.ModfiyAttrValue(xmlNode, "Visible", "false");
                        }

                    }
                    else
                    {       ////不存在节点,则新增节点
                        XmlNode xmlNodeadd = xmlAdmin.addNode("AssType", "Item");
                        xmlAdmin.addAttr(xmlNodeadd, "Code", checkItem.Name);
                        xmlAdmin.addAttr(xmlNodeadd, "Name", checkItem.Text);
                        xmlAdmin.addAttr(xmlNodeadd, "Visible", "true");
                    }

                }

                ////this.WriteTableColumnsFrozen(tab, xmlAdmin);
                xmlAdmin.SaveXmlDocument();
                xmlAdmin.Dispose();
                xmlAdmin = null;
            }
        }

        /// <summary>
        /// CheckItem_CheckedChanged
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void CheckItem_CheckedChanged(object sender, EventArgs e)
        {
            if (!boolAssItem_Click)
            {
                CheckItem checkItem = sender as CheckItem;
                this.WriteAssItemsToConfig(checkItem, "CustomAssType");
                this.loadLeftDataProcMVC();
            }
        }

        /// <summary>
        /// 窗体加载方法
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ASSETS_TREE_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            dataService = (IServiceBus)ServiceFactory.createService(serviceType);

            ClsAssocia asc = new ClsAssocia();
            //// modified by HeLiang 2017-06-20 单元测试临时修改
            asc = ClsClzCfgMgr.getAssociaParam("pd_assetsTree_A");

            Type serviceTypeA = ReflectBase.YssGetType(asc.ServiceDllName, asc.ServiceName);
            this.assetTreeAService = (IAssertTree_AService)ServiceFactory.createService(serviceTypeA);
            ////add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
            this.assetTreeBService = DataServiceFactory.createService<IAssertTreeService>();
            ////add by zhoushuhang 2108-03-19 BUG195989产品树形结构界面A区选中一条数据，点击B区的新增下级节点，弹出的界面上上级结构没有自动加载
            if (this.btnBar.getButton(ClsButtonName.BtnNew) != null)
            {
            ButtonItem btnNew = this.btnBar.getButton(ClsButtonName.BtnNew).Owner;
            btnNew.DropDownOpening += new System.EventHandler(this.btnNew_ExpandChange);
            }
        }

        /// <summary>
        /// 重写tbLeftMain的勾选行为--A区勾选“未分配”数据时，只能勾选当前数据，其他数据不能勾选
        /// 2017-02-06 zhujinyang STORY36179
        /// </summary>
        /// <param name="sender">事件触发对象</param>
        /// <param name="e">事件参数</param>
        protected override void tbLeftMain_CheckStateChanged(object sender, Yss.KTable.Events.CheckStateChangeEventArgs e)
        {
            try
            {
                ////限制，当勾选“未分配”的数据，取消之前所有的数据，仅勾选当前选中的“未分配”数据
                this.tbLeftMain.CheckStateChanged -= this.tbLeftMain_CheckStateChanged;
                if (e.Checked && e.CurrentRow != null && e.CurrentRow.Tag is AssertTreeATreeView)
                {
                    AssertTreeATreeView pojo = e.CurrentRow.Tag as AssertTreeATreeView;
                    if (pojo.C_TR_NAME == "未分配")
                    {
                        this.tbLeftMain.CancelCheckedRows();
                        e.CurrentRow.Checked = e.Checked;
                    }
                    else
                    {
                        for (int index = 0; index < this.tbLeftMain.CheckedRows.Count; index++)
                        {
                            Row tempRow = this.tbLeftMain.CheckedRows[index];
                            if (tempRow != null && tempRow.Tag is AssertTreeATreeView)
                            {
                                pojo = tempRow.Tag as AssertTreeATreeView;
                                if (pojo.C_TR_NAME == "未分配")
                                {
                                    tempRow.Checked = false;
                                }
                            }
                        }
                    }
                }

                base.tbLeftMain_CheckStateChanged(sender, e);
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
            finally
            {
                this.tbLeftMain.CheckStateChanged += this.tbLeftMain_CheckStateChanged;
            }
        }

        /// <summary>
        /// 重写A区的全勾选行为--全选时只能勾选已分配的数据
        /// 2017-02-06 zhujinyang STORY36179
        /// </summary>
        /// <param name="sender">CheckBox</param>
        /// <param name="e">事件参数</param>
        protected override void CheckLeftAll_CheckedChanged(object sender, EventArgs e)
        {
            try
            {
                this.tbLeftMain.CheckStateChanged -= this.tbLeftMain_CheckStateChanged;
                ////STORY #50945 FrmBaseList基类，更换默认A区Bar（barPort）工具条为ToolStrip，并收回barPort访问权限。张绍林20171229
                ToolStripCheckBox checkBox = sender as ToolStripCheckBox;
                if (checkBox.Checked)
                {
                    this.tbLeftMain.CheckParentWidthChild = false;
                    this.tbLeftMain.CancelCheckedRows();
                    RowCollection allRows = this.tbLeftMain.GetAllRows(this.tbLeftMain.Rows, true, false);
                    for (int index = 0; index < allRows.Count; index++)
                    {
                        Row tempRow = allRows[index];
                        if (tempRow != null && tempRow.Tag is AssertTreeATreeView)
                        {
                            AssertTreeATreeView pojo = tempRow.Tag as AssertTreeATreeView;
                            if (pojo.C_TR_NAME != "未分配")
                            {
                                tempRow.Checked = true;
                            }
                        }
                    }
                }
                else
                {
                    this.tbLeftMain.CheckParentWidthChild = true;
                    base.CheckLeftAll_CheckedChanged(sender, e);
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
            finally
            {
                this.tbLeftMain.CheckParentWidthChild = true;
                this.tbLeftMain.CheckStateChanged += this.tbLeftMain_CheckStateChanged;
            }
        }

        /// <summary>
        /// A区窗体操作后，根据操作前的选中行号，选中A区对应的行
        /// </summary>
        public override void selectRowAfterLeftSetFormSave()
        {
            ////base.selectRowAfterLeftSetFormSave();
            ////新增A区数据时，避免A区选空行。
            if (tbLeftMain.Rows.Count > 0)
            {
                if (null != this.id && !"".Equals(this.id) && this.id.Trim().Length > 0)
                {
                    ////根据ID获取代码
                    IAssertTreeService assertTreeService = (IAssertTreeService)ServiceFactory.createService<IAssertTreeService>();
                    string code = assertTreeService.getCodeByCId(this.id);
                    getRowByCode(code);
                }
                ////tbLeftMain.Rows[selectedRowNum].Selected = true;
                ////选中操作行有问题,删除行时会出现数组越界,暂定选中第一行
                ////tbLeftMain.Rows[0].Selected = true;
            }
            else
            {
                return;
            }
        }

        /// <summary>
        /// 通过代码找到A区对应的行
        /// </summary>
        /// <param name="code">代码</param>
        private void getRowByCode(string code)
        {
            RowCollection rc = this.tbLeftMain.Rows;

            for (int i = 0; i < rc.Count; i++)
            {
                if (rc[i].SubRows.Count > 0)
                {
                    RowCollection rs = rc[i].SubRows;
                    for (int j = 0; j < rs.Count; j++)
                    {
                        AssertTreeATreeView aa = (AssertTreeATreeView)rs[j].Tag;
                        if ("Root".Equals(aa.C_TR_CODE))
                        {
                            continue;
                        }

                        //// edit by sunyanlin 20180301  21.6-FAST2.0功能测试BUG   产品树形结构A区在删除时原逻辑会出现空指针异常，增加非空判断
                        if (null != code && code.Equals(aa.C_TR_CODE))
                        {
                            rs[j].Selected = true;
                            tbLeftMain.Rows[0].Selected = false;
                            return;
                            ////tbLeftMain.Rows[j].Selected = true;
                        }

                    }
                }
                else
                {
                    ////派工单 #2064 估值_V1.300.7.0_UI自动化测试_自动化测试(264)
                    if (null != code && code.Equals(((AssetsTree_A)(rc[i] as Yss.KTable.Models.Row).Tag).C_TR_CODE))
                    {
                        rc[i].Selected = true;
                        tbLeftMain.Rows[0].Selected = false;
                        return;
                    }

                }

            }
        }

        /// <summary>
        /// Author : ZhouShuHang
        /// Date   : 2018-03-07
        /// Status : Add
        /// STORY49928产品树形结构界面优化
        /// Comment: STORY49928产品树形结构界面优化
        /// </summary>
        private void addSubButtons()
        {
            string[] arySetSubName = { "新增同级节点", "新增下级节点", "新增组合" };
            this.setSubButtons(arySetSubName);
        }

        /// <summary>
        /// Author : ZhouShuHang
        /// Date   : 2018-03-07
        /// Status : Add
        /// STORY49928产品树形结构界面优化
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnNew_Click(object sender, EventArgs e)
        {
            if (yssGetSelTypeItemMVC(tbMain) == null && yssGetSelTypeItemMVC(tbLeftMain) == null)
            {
                //// 判断A区是否已选中了方案
                Yss.CommonLib.ShowMessage("请选择产品树形结构！", "警告", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            this.setBtnNewSubItem();
            ButtonItem btnNew = this.btnBar.getButton(ClsButtonName.BtnNew).Owner;
            btnNew.Expanded = !btnNew.Expanded;
            btnNew.Refresh();
        }

        /// <summary>
        /// Author : ZhouShuHang
        /// Date   : 2018-03-07
        /// Status : Add
        /// STORY49928产品树形结构界面优化
        /// 添加新增按钮动态添加子按钮
        /// </summary>
        /// <param name="arySetSubName">arySetSubName</param>
        private void setSubButtons(string[] arySetSubName)
        {
            foreach (string text in arySetSubName)
            {
                ClsSubButtonInfo btnTmp = new ClsSubButtonInfo();
                btnTmp.Text = text;
                if (text.Equals("新增组合"))
                {
                    btnTmp.ClickEvent += new EventHandler(this.btnPort_Click);
                }
                else
                {
                    btnTmp.ClickEvent += new EventHandler(this.btnNewSub_Click);
                }

                this.btnBar.addSubButton(ClsButtonName.BtnNew, btnTmp);
            }
        }

        /// <summary> 
        /// Author : ZhouShuHang
        /// Date   : 2018-03-07
        /// Status : Add
        /// STORY49928产品树形结构界面优化
        /// 新增子按钮单击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected void btnNewSub_Click(object sender, EventArgs e)
        {
            if (yssGetSelTypeItemMVC(tbMain) == null && yssGetSelTypeItemMVC(tbLeftMain) == null)
            {
                //// 判断A区是否已选中了方案
                Yss.CommonLib.ShowMessage("请选择产品树形结构！", "警告", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            ButtonItem btnSub = null;
            if (null != sender)
            {
                try
                {
                    btnSub = sender as ButtonItem;
                    if (btnSub.Text.Equals("新增同级节点"))
                    {
                        this.OtherParams = "sameLevel";
                    }
                    else if (btnSub.Text.Equals("新增下级节点"))
                    {
                        this.OtherParams = "upLevel";
                    }

                    Frm_ASSETS_TREE_S_A frm_ASSETS_TREE_S_A = new Frm_ASSETS_TREE_S_A(this);
                    IBaseFun newFun = ClsContext.sysFunHash["assetsTree_A"];
                    frm_ASSETS_TREE_S_A.YssFormMenu = newFun;
                    frm_ASSETS_TREE_S_A.yssInitCtlAttr();
                    frm_ASSETS_TREE_S_A.initControlStat();
                    ////设置set界面 BUG #223374 添加子节点，报错 suixin 2018-10-12
                    this.frmBaseViewSet = frm_ASSETS_TREE_S_A;
                    frm_ASSETS_TREE_S_A.Show(this);
                }
                catch (System.Exception ex)
                {
                    throw new ClsBaseException(ex.Message);
                }
            }
        }

        /// <summary>
        /// Author : ZhouShuHang
        /// Date   : 2018-03-07
        /// Status : Add
        /// STORY49928产品树形结构界面优化
        /// 点击[新增组合]事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnPort_Click(object sender, EventArgs e)
        {
            if (yssGetSelTypeItemMVC(tbMain) == null && yssGetSelTypeItemMVC(tbLeftMain) == null)
            {
                //// 判断A区是否已选中了方案
                Yss.CommonLib.ShowMessage("请选择产品树形结构！", "警告", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            base.btnNew_Click(sender, e);
        }

        /// <summary>
        /// add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
        /// 重写加载数据表格内容事件
        /// </summary>
        /// <param name="res">查询结果对象</param>
        protected override void loadListContentMVC(QueryRes res)
        {
            if (this.YssMainKTableShowMode == ClsEnums.KTableDataShowMode.TreeMode)
            {
                this.setTreeViewTable(tbMain, res, ShowRowCheckBoxColumn, ShowRowIndexColumn, ClsEnums.KTableDataShowMode.TreeMode);
            }
            else
            {
                base.loadListContentMVC(res);
            }
        }

        /// <summary>
        /// BUG #223374 添加子节点，报错 suixin 2018-10-12 
        /// </summary>
        /// <param name="res">1</param>
        public override void refreshTableListMVC(QueryRes res)
        {
            base.refreshTableListMVC(res);
            ////工单 #3238 估值_V1.300.7.0_UI2.0自动化测试_自动化测试(221)【产品树形结构】
            ////非默认Set界面，置空以免影响默认流程
            ////switch (this.OtherParams)
            ////{
            ////    case "sameLevel":
            ////    case "upLevel":
            ////        this.frmBaseViewSet = null;
            ////        this.frmBaseViewSet.IsDisposed = false;
            ////        break;
            ////}

        }

        /// <summary>
        /// 单击B区数据行后刷新按钮状态
        /// BUG #312772 产品树形结构界面不能反审核，不能新增
        /// </summary>
        /// <param name="pojo">数据对象</param>
        protected override void setButtonStaAfterTbMainClickMVC(BasePojo pojo)
        {
            ////初始化按钮状态
            ////this.btnBar.setButtonVisable(ClsButtonName.BtnNew, true);
            ////this.btnBar.setButtonVisable(ClsButtonName.BtnEdit, true);
            ////this.btnBar.setButtonVisable(ClsButtonName.BtnCopy, true);
            ////this.btnBar.setButtonVisable(ClsButtonName.BtnDelete, true);
            ////this.btnBar.setButtonVisable(ClsButtonName.BtnAudit, true);
            ////this.btnBar.setButtonVisable(ClsButtonName.BtnUnAudit, true);

            base.setButtonStaAfterTbMainClickMVC(pojo);
            ////BUG #312772 产品树形结构界面不能反审核，不能新增
            Row selectedRow = this.tbLeftMain.SelectedRow;

            if (selectedRow == null || "ASS,ZCTG,ZCGL,ZCWT,ZCZL,TGZC,GLZC,NSPL,ZCMXLX,CPZT".Contains(((AssertTreeATreeView)selectedRow.Tag).C_TR_CODE))
            {
                this.btnBar.setButtonVisable(ClsButtonName.BtnNew, false);
                this.btnBar.setButtonVisable(ClsButtonName.BtnEdit, false);
                this.btnBar.setButtonVisable(ClsButtonName.BtnCopy, false);
                this.btnBar.setButtonVisable(ClsButtonName.BtnDelete, false);
                this.btnBar.setButtonVisable(ClsButtonName.BtnAudit, false);
                this.btnBar.setButtonVisable(ClsButtonName.BtnUnAudit, false);
            }
            else
            {
                ////BUG #330373 产品信息中A区新增后查询报错；A区选中类型后点击查询，审核反审核按钮有效有的无效
                AssetsTree_A assetsTree_A = selectedRow.Tag as AssetsTree_A;
                string rId = (this.tbMain.SelectedRow.Tag as AssetsTree_B).Id;
                ////A区设置了显示未分配且ID为空的数据为未分配数据，未分配数据并未在树形结构表中存在，不能进行修改操作
                if (!string.IsNullOrEmpty(assetsTree_A.C_DV_UN_DIS) && "1".Equals(assetsTree_A.C_DV_UN_DIS) && string.IsNullOrEmpty(rId))
                {
                    this.btnBar.setButtonVisable(ClsButtonName.BtnNew, false);
                    this.btnBar.setButtonVisable(ClsButtonName.BtnEdit, false);
                    this.btnBar.setButtonVisable(ClsButtonName.BtnCopy, false);
                    this.btnBar.setButtonVisable(ClsButtonName.BtnDelete, false);
                    this.btnBar.setButtonVisable(ClsButtonName.BtnAudit, false);
                    this.btnBar.setButtonVisable(ClsButtonName.BtnUnAudit, false);
                }
                else if (assetsTree_A.C_AUTO_ZR_TYPE.Equals("TREE_AUTO_DTZH"))
                {
                    this.btnBar.setButtonVisable(ClsButtonName.BtnNew, true);
                    this.btnBar.setButtonVisable(ClsButtonName.BtnEdit, true);
                    this.btnBar.setButtonVisable(ClsButtonName.BtnCopy, true);
                    this.btnBar.setButtonVisable(ClsButtonName.BtnDelete, true);
                    this.btnBar.setButtonVisable(ClsButtonName.BtnAudit, false);
                    this.btnBar.setButtonVisable(ClsButtonName.BtnUnAudit, false);
                }
                else
                {
                    ////初始化按钮状态
                    this.btnBar.setButtonVisable(ClsButtonName.BtnNew, true);
                    this.btnBar.setButtonVisable(ClsButtonName.BtnEdit, true);
                    this.btnBar.setButtonVisable(ClsButtonName.BtnCopy, true);
                    this.btnBar.setButtonVisable(ClsButtonName.BtnDelete, true);
                    this.btnBar.setButtonVisable(ClsButtonName.BtnAudit, true);
                    this.btnBar.setButtonVisable(ClsButtonName.BtnUnAudit, true);
                }
            }
        }

        /// <summary>
        /// add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
        /// 刷新
        /// </summary>
        public void refreshTbMain()
        {
            if (tbMain.Rows.Count > 0)
            {
                if (this.tbLeftMain.SelectedRow != null)
                {
                    if (((AssetsTree_A)tbLeftMain.SelectedRow.Tag).C_AUTO_ZR_TYPE.Equals("TREE_AUTO_DTZH"))
                    {
                        btnBar.setButtonEnabled(ClsButtonName.BtnAudit, false);
                        btnBar.setButtonEnabled(ClsButtonName.BtnUnAudit, false);
                    }
                    else 
                    {
                        ////STORY #67195 人保资产-4.5测试系统中产品建账环节：产品树信息建议设置为账户基本信息【3其它需求-079】
                        if ("ASS,ZCTG,ZCGL,ZCWT,ZCZL,TGZC,GLZC,NSPL,ZCMXLX,CPZT".Contains(((AssertTreeATreeView)this.tbLeftMain.SelectedRow.Tag).C_TR_CODE))
                        {
                            this.btnBar.setButtonVisable(ClsButtonName.BtnNew, false);
                            this.btnBar.setButtonVisable(ClsButtonName.BtnEdit, false);
                            this.btnBar.setButtonVisable(ClsButtonName.BtnCopy, false);
                            this.btnBar.setButtonVisable(ClsButtonName.BtnDelete, false);
                            this.btnBar.setButtonVisable(ClsButtonName.BtnAudit, false);
                            this.btnBar.setButtonVisable(ClsButtonName.BtnUnAudit, false);
                        }
                        else
                        {
                            this.btnBar.setButtonVisable(ClsButtonName.BtnNew, true);
                            this.btnBar.setButtonVisable(ClsButtonName.BtnEdit, true);
                            this.btnBar.setButtonVisable(ClsButtonName.BtnCopy, true);
                            this.btnBar.setButtonVisable(ClsButtonName.BtnDelete, true);
                            this.btnBar.setButtonVisable(ClsButtonName.BtnAudit, true);
                            this.btnBar.setButtonVisable(ClsButtonName.BtnUnAudit, true);
                        }
                    }
                }
                else
                {
                    this.btnBar.setButtonVisable(ClsButtonName.BtnNew, true);
                    this.btnBar.setButtonVisable(ClsButtonName.BtnEdit, true);
                    this.btnBar.setButtonVisable(ClsButtonName.BtnCopy, true);
                    this.btnBar.setButtonVisable(ClsButtonName.BtnDelete, true);
                    this.btnBar.setButtonVisable(ClsButtonName.BtnAudit, true);
                    this.btnBar.setButtonVisable(ClsButtonName.BtnUnAudit, true);
                }

                ArrayList indexList = new ArrayList();
                foreach (Column column in tbMain.Columns)
                {
                    ////组合代码
                    if ("C_PORT_CODE".Equals(column.DataPropertyName))
                    {
                        indexList.Add(column.Index);
                    }
                }

                Dictionary<string, string> diction = new Dictionary<string, string>();
                Dictionary<string, string> diction_status = new Dictionary<string, string>();
                if (this.tbLeftMain.SelectedRow  != null)
                {
                AssertTreeATreeView selectTreeA = (AssertTreeATreeView)this.tbLeftMain.SelectedRow.Tag;
                    if (null != selectTreeA && ("1".Equals(selectTreeA.C_DV_UN_DIS) || "TREE_AUTO_DTZH".Equals(selectTreeA.C_AUTO_ZR_TYPE)))
                {
                    IAccTypeDataService accTypeDataService = DataServiceFactory.createService<IAccTypeDataService>();
                    diction = accTypeDataService.getKeyConvertMap();
                        IVocDataService vocDataService = DataServiceFactory.createService<IVocDataService>();
                        diction_status = vocDataService.getKeyConvertMap();
                    }
                }

                int node = tbMain.Columns["C_TR_CODE"].Index; ////所属节点名称
                int state = tbMain.Columns["N_CHECK_STATE"].Index; ////审核状态
                int modifier = this.tbMain.Columns["C_UPDATE_BY"].Index; ////制作人
                int checkUser = tbMain.Columns["C_CHECK_BY"].Index; ////审核人
                foreach (Row row in tbMain.Rows)
                {
                    if (row.HasChild)
                    {
                        if ((row.Tag as AssetsTree_B).IsParent == 1)
                        {
                            if (indexList.Count > 0)
                            {
                                foreach (int portIndex in indexList)
                                {
                                    row.Cells[portIndex].Text = string.Empty;
                                }

                                row.Cells[state].Text = string.Empty;
                            }
                        }

                        if (row.SubRows.Count > 0)
                        {
                            this.resetRowInfo(row, indexList, state, modifier, checkUser, node, diction, diction_status);
                        }
                    }
                }

                //// edit by zhoushuhang 20180321 BUG196104产品树形结构测试问题汇总
                //// 只新增了顶级父节点并无子节点，查询应该无数据。
                if (tbMain.Rows.Count == 1)
                {
                    if (!tbMain.Rows[0].HasChild)
                    {
                        tbMain.Rows.Clear();
                    }
                }
            }

            tbMain.AutoWidth();
            tbMain.Refresh();
        }

        /// <summary>
        /// add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
        /// 重置行文本信息
        /// </summary>
        /// <param name="row">row</param>
        /// <param name="list">list</param>
        /// <param name="state">审核状态</param>
        /// <param name="modifier">制作人</param>
        /// <param name="checkUser">审核人</param>
        /// <param name="node">所属节点名称</param>
        /// <param name="diction">资产类型建值对集合</param>
        /// <param name="diction_status">diction_status</param>
        private void resetRowInfo(Row row, ArrayList list, int state, int modifier, int checkUser, int node, Dictionary<string, string> diction, Dictionary<string, string> diction_status)
        {
            foreach (Row subRow in row.SubRows)
            {
                if ((subRow.Tag as AssetsTree_B).IsParent == 1)
                {
                    if (list.Count > 0)
                    {
                        foreach (int portIndex in list)
                        {
                            subRow.Cells[portIndex].Text = "";
                        }
                    }

                    subRow.Cells[state].Text = "";
                }

                if (!string.IsNullOrEmpty(subRow.Cells[node].Text))
                {
                    if (subRow.Cells[node].Text.Contains("且"))
                    {
                        subRow.Cells[node].Text = subRow.Cells[node].Text.Substring(0, subRow.Cells[node].Text.IndexOf("且"));
                    }
                }

                if (diction.Count != 0)
                {
                    if (diction.ContainsKey(subRow.Cells[node].Text))
                    {
                        subRow.Cells[node].Text = diction[subRow.Cells[node].Text];
                    }
                }

                if (diction_status.Count != 0)
                {
                    if (diction_status.ContainsKey(subRow.Cells[node].Text))
                    {
                        subRow.Cells[node].Text = diction_status[subRow.Cells[node].Text];
                    }
                }

                if (subRow.HasChild)
                {
                    if (subRow.SubRows.Count > 0)
                    {
                        this.resetRowInfo(subRow, list, state, modifier, checkUser, node, diction, diction_status);
                    }
                }

            }
        }

        /// <summary>
        /// add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
        /// 行拖动前事件
        /// 主要做拖动数据检查
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void tbMain_BeforeRowDragged(object sender, Yss.KTable.Events.RowDragEventArgs e)
        {
            ////add by zhoushuhang 2018-06-13 BUG206147节点code不能与组合代码相同
            Row hotRow = null;
            if (null != e.HotRow)
            {
                hotRow = e.HotRow;
            }
            else if (e.HotRow == null && e.DragToChild && null != e.LastVisibleRow)
            {
                hotRow = e.LastVisibleRow;
            }
            else
            {
                e.Cancel = true;
                return;
            }

            ////最顶级父级节点不支持可拖动
            if (hotRow == null || e.DragRow.ParentRow == null)
            {
                e.Cancel = true;
                return;
            }

            ////获取当前的焦点控件
            Control tempFocusedCtrl = Yss.CommonLib.GetFocusedControl(this);
            ////待拖入位置对象
            AssetsTree_B tree_B = hotRow.Tag as AssetsTree_B;
            ////获取所属行对象
            AssetsTree_B dragTree = e.DragRow.Tag as AssetsTree_B;
            ////1.待拖入位置为父级。不能将拖入的组合数据变更为同级
            if (tree_B.IsParent == 1 && dragTree.IsParent == 0 && !e.DragToChild)
            {
                e.Cancel = true;
                return;
            }
            ////2.父节点不能往子节点拖动
            if (tree_B.IsParent == 0 && dragTree.IsParent == 1 && e.DragToChild)
            {
                e.Cancel = true;
                return;
            }

            ////3.拖动位置未父节点，往未分配里面拖入子行，则不可以拖动
            if (tree_B.IsParent == 1 && dragTree.IsParent == 1 && e.DragToChild && this.existsUnDis(hotRow))
            {
                e.Cancel = true;
                return;
            }

            ////4.拖动位置未父节点，往未分配里面组合数据拖入同行，则不可以拖动
            if (tree_B.IsParent == 0 && dragTree.IsParent == 1 && !e.DragToChild && this.existsUnDis(hotRow))
            {
                e.Cancel = true;
                return;
            }

            ////5.不能单独拖做为新一级父节点
            if (null == hotRow.ParentRow && !e.DragToChild)
            {
                e.Cancel = true;
                return;
            }

            ////6.未分配下的节点。不能拖入至其他子节点
            if (dragTree.IsParent == 1 && this.existsUnDis(e.DragRow))
            {
                e.Cancel = true;
                return;
            }

            ////拖动行为明细组合数据
            if (null != hotRow && !e.DragRow.HasChild && dragTree.IsParent == 0)
            {
                int portIndex = tbMain.Columns["C_PORT_CODE"].Index; ////组合代码
                if (dragTree.IsParent == 0)
                {
                    ////7、子节点不能向另一子节点拖入子行。
                    if (e.DragToChild && tree_B.IsParent == 0)
                    {
                        e.Cancel = true;
                        return;
                    }

                    ////8.往未分配父级节点拖动子节点。不是同一资产类型不可拖动
                    ////往未分配父级节点拖动同级节点。不可拖动
                    if (!e.DragToChild && tree_B.IsParent == 1)
                    {
                        e.Cancel = true;
                        return;
                    }

                    ////判断待拖入的父级节点是否存在【未分配】节点，若存在，不同资产类型不可拖入
                    if (this.existsUnDis(hotRow))
                    {
                        ////未分配节点下可以拖入
                        if (!"未分配".Equals(hotRow.Cells[2].Text))
                        {
                            ////待拖入和拖入数据对应的组合代码是否是同一资产类型
                            string result = this.assetTreeBService.isSameAssetType(tree_B.C_PORT_CODE, dragTree.C_PORT_CODE);
                            ////9.不同资产类型，不可拖入
                            if ("false".Equals(result))
                            {
                                e.Cancel = true;
                                return;
                            }
                        }
                        
                    }
                }
            }
        }

        /// <summary>
        /// add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
        /// 行拖动后。更新数据事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void tbMain_AfterRowDragged(object sender, Yss.KTable.Events.RowDragEventArgs e)
        {
            ////add by zhoushuhang 2018-06-13 BUG206147节点code不能与组合代码相同
            Row hotRow = null;
            if (null != e.HotRow)
            {
                hotRow = e.HotRow;
            }
            else if (e.HotRow == null && e.DragToChild && null != e.LastVisibleRow)
            {
                hotRow = e.LastVisibleRow;
            }
            else
            {
                e.Cancel = true;
                return;
            }

            ////待拖入位置对象
            AssetsTree_B tree_B = hotRow.Tag as AssetsTree_B;

            ////获取所属行对象
            AssetsTree_B dragTree = e.DragRow.Tag as AssetsTree_B;

            ////获取顶级节点
            string trCodeR = this.getTopTrCodeR(hotRow);

            ////1、拖动行为明细组合数据。变更父级节点
            if (null != hotRow && !e.DragRow.HasChild && dragTree.IsParent == 0)
            {
                int portIndex = tbMain.Columns["C_PORT_CODE"].Index; ////组合代码
                ////组合节点相互拖动  或者  组合节点向父级节点拖入子行
                if (dragTree.IsParent == 0)
                {
                    ////判断待拖入的父级节点是否存在【未分配】节点，若存在，不同资产类型不可拖入
                    if (this.existsUnDis(hotRow))
                    {
                        ////未分配节点下可以拖入
                        if ("未分配".Equals(hotRow.Cells[2].Text))
                        {
                            ////如果往未分配节点下拖动，则删除原有节点数据。
                            this.assetTreeBService.updateOrdelete(dragTree.Id, string.Empty, "0", "DELETE", trCodeR);
                            return;
                        }
                        else
                        {
                            ////待拖入和拖入数据对应的组合代码是否是同一资产类型
                            string result = this.assetTreeBService.isSameAssetType(tree_B.C_PORT_CODE, dragTree.C_PORT_CODE);
                            if ("true".Equals(result))
                            {
                                ////如果往未分配节点下拖动，则删除原有节点数据。
                                this.assetTreeBService.updateOrdelete(dragTree.Id, string.Empty, "0", "DELETE", trCodeR);
                                return;
                            }
                        }
                       
                    }
                    else
                    {
                        if (!string.Empty.Equals(dragTree.Id))
                        {
                            ////如果待拖入位置与选择所属行，结构级别相同，则更新数据的父级节点
                            this.assetTreeBService.updateOrdelete(dragTree.Id, tree_B.C_TR_CODE, "0", "UPDATE", trCodeR);
                            return;
                        }
                    }
                }
            }

            ////2.拖动父级节点，无子节点。待拖入位置为父节点并且拖入子行
            if (null != hotRow && !e.DragRow.HasChild && e.DragToChild)
            {
                this.assetTreeBService.updateOrdelete(dragTree.Id, tree_B.C_TR_CODE, "1", "UPDATE", trCodeR);
                return;
            }
            ////2.拖动父级节点，无子节点。待拖入位置为父节点并且拖入同行
            if (null != hotRow && !e.DragRow.HasChild && !e.DragToChild)
            {
                string nodeCode = ((AssetsTree_B)hotRow.ParentRow.Tag).C_TR_CODE;
                this.assetTreeBService.updateOrdelete(dragTree.Id, nodeCode, "1", "UPDATE", trCodeR);
                return;
            }
            ////2.拖动父级节点，父节点以及子行一起拖动。待拖入位置为父节点并且拖入子行
            if (e.DragRow.HasChild && e.DragToChild && tree_B.IsParent == 1)
            {
                this.assetTreeBService.updateOrdelete(dragTree.Id, tree_B.C_TR_CODE, "1", "UPDATE", trCodeR);
                return;
            }
            ////2.拖动父级节点，父节点以及子行一起拖动。待拖入位置为父节点并且拖入子行
            if (e.DragRow.HasChild && !e.DragToChild && tree_B.IsParent == 1)
            {
                string nodeCode = ((AssetsTree_B)hotRow.ParentRow.Tag).C_TR_CODE;
                this.assetTreeBService.updateOrdelete(dragTree.Id, nodeCode, "1", "UPDATE", trCodeR);
                return;
            }
            ////3.拖动父级节点，父节点以及子行一起拖动。待拖入位置为子节点并且拖入同行
            if (e.DragRow.HasChild && !e.DragToChild && tree_B.IsParent == 0)
            {
                string nodeCode = ((AssetsTree_B)hotRow.ParentRow.Tag).C_TR_CODE;
                this.assetTreeBService.updateOrdelete(dragTree.Id, nodeCode, "1", "UPDATE", trCodeR);
                return;
            }

            ////4.由未分配节点拖入至不存在未分配节点的结构
            if (!this.existsUnDis(hotRow) && this.existsUnDis(e.DragRow))
            {
                AssetsTree_B pojo = dragTree;
                ////拖入子行并且待拖入位置的行为父节点
                if (e.DragToChild)
                {
                    pojo.C_TR_CODE = tree_B.C_TR_CODE;
                    if ("[root]".Equals(tree_B.C_TR_CODE_R))
                    {
                        pojo.C_TR_CODE_R = tree_B.C_TR_CODE;
                    }
                    else
                    {
                        pojo.C_TR_CODE_R = tree_B.C_TR_CODE_R;
                    }
                }
                else if (!e.DragToChild && !hotRow.HasChild)
                {
                    ////拖入同级并且待拖入位置的行不存在子节点
                    AssetsTree_B parentTree = hotRow.ParentRow.Tag as AssetsTree_B;
                    pojo.C_TR_CODE = parentTree.C_TR_CODE;
                    if ("[root]".Equals(parentTree.C_TR_CODE_R))
                    {
                        pojo.C_TR_CODE_R = parentTree.C_TR_CODE;
                    }
                    else
                    {
                        pojo.C_TR_CODE_R = parentTree.C_TR_CODE_R;
                    }
                }

                ////设置pojo的公共信息
                this.setAddOperPojoInfo((BasePojo)pojo);
                ////插入一笔新的组合数据
                this.assetTreeBService.insert(pojo);
                return;
            }

            ////5.所属行节点不在未分配节点下。待拖入位置为父节点拖入子行
            if (tree_B.IsParent == 1 && dragTree.IsParent == 0 && e.DragToChild && !this.existsUnDis(e.DragRow))
            {
                ////如果待拖入位置与选择所属行，结构级别相同，则更新数据的父级节点
                this.assetTreeBService.updateOrdelete(dragTree.Id, tree_B.C_TR_CODE, "0", "UPDATE", trCodeR);
                return;
            }
        }

        /// <summary>
        /// 获取顶级节点
        /// </summary>
        /// <param name="hotRow">row</param>
        /// <returns>trCodeR</returns>
        private string getTopTrCodeR(Row hotRow)
        {
            if (hotRow.ParentRow == null && hotRow.Tag != null)
            {
                return ((AssetsTree_B)hotRow.Tag).C_TR_CODE;
            }

            string nodeCode = "";
            if (hotRow.ParentRow != null && hotRow.ParentRow.Tag != null)
            {
                nodeCode = ((AssetsTree_B)hotRow.ParentRow.Tag).C_TR_CODE;
            }

            if (hotRow.ParentRow.HasParent)
            {
                return this.getTopTrCodeR(hotRow.ParentRow);
            }
            else
            {
                return nodeCode;
            }
        }

        /// <summary>
        /// add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
        /// 拖动行数据时，按行的级别，过滤行的子集数据
        /// </summary>
        /// <param name="row">row</param>
        /// <returns>是否移至未分配数据</returns>
        private bool existsUnDis(Row row)
        {
            bool exists = false;
            int node = tbMain.Columns["C_TR_CODE"].Index; ////所属节点名称 
            if (row.Cells[node].Text == "未分配")
            {
                return true;
            }

            if (row.ParentRow != null)
            {
                if (row.ParentRow.Cells[node].Text == "未分配")
                {
                    exists = true;
                }
                else
                {
                    exists = this.existsUnDis(row.ParentRow);
                }

                if (exists)
                {
                    return true;
                }
            }

            return exists;
        }

        /// <summary>
        /// Author : ZhouShuHang
        /// Date   : 2017-03-13
        /// Status : Add
        /// Comment：STORY49928产品树形结构界面优化
        /// 设置修改pojo的公共信息
        /// </summary>
        /// <param name="pojo">数据对象</param>
        /// <returns>设置公共信息后的数据对象</returns>
        private BasePojo setAddOperPojoInfo(BasePojo pojo)
        {
            if (pojo is ParamPojo)
            {
                ((ParamPojo)pojo).Modifier = ClsContext.currentUser.C_USER_CODE;
            }

            if (pojo is AuditableParamPojo)
            {
                if (!string.IsNullOrEmpty(pojo.Id))
                {
                    ((AuditableParamPojo)pojo).AuditState = 1;
                }
                else
                {
                    ((AuditableParamPojo)pojo).AuditState = (_formFun.N_CHECK == 1) ? 0 : 1;
                }
                //// 当未开启审核状态时，这时是需要将审核人，审核时间填到POJO中去的byleeyu20130719
                if (_formFun.N_CHECK <= 0)
                {
                    ((AuditableParamPojo)pojo).OperUser = ClsContext.currentUser.C_USER_CODE;
                    ((AuditableParamPojo)pojo).AuditDate = ClsFunction.formatDateTime("yyyyMMdd HH:mm:ss", DateTime.Now.ToString());
                }
            }

            return pojo;
        }

        /// <summary>
        /// add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
        /// 自定义行拖动事件
        /// </summary>
        /// <param name="sender">Table</param>
        /// <param name="e">事件参数</param>
        private void tbLeftMain_BeforeRowDragged(object sender, Yss.KTable.Events.RowDragEventArgs e)
        {
            ////如果是从B区拖动至A区，则不能拖入
            if (null != e.DragRow.Tag && e.DragRow.Tag is AssetsTree_B)
            {
                e.Cancel = true;
                return;
            }

            if (e.HotRow != null && e.DragToChild)
            {
                ////不能向窗体节点继续拖入子节点
                e.Cancel = true;
                return;
            }
        }

        /// <summary>
        /// add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
        /// 拖放操作完成时发生
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void tbLeftMain_DragDrop(object sender, DragEventArgs e)
        {
            ////创建list列表
            List<string> codeList = new List<string>();
            foreach (Row row in tbLeftMain.Rows)
            {
                codeList.Add((row.Tag as AssetsTree_A).C_TR_CODE);
            }

            this.assetTreeAService.updateAssOrder(codeList);
        }

        /// <summary>
        /// add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
        /// 功能描述：加载树型列表
        /// </summary>
        /// <param name="tbMain">序时簿对象</param>
        /// <param name="res">查询结果对象</param>
        /// <param name="showChkColumn">是否显示复选框</param>
        /// <param name="showMkColumn">是否显示行号</param>
        /// <param name="showMode">显示模式</param>
        public void setTreeViewTable(Table tbMain, QueryRes res, bool showChkColumn, bool showMkColumn, ClsEnums.KTableDataShowMode showMode)
        {
            try
            {
                this.showCheckColumn = showChkColumn;
                this.showMarkColumn = showMkColumn;

                tbMain.Clear(); // 清除table的所有数据和信息
                tbMain.FixedLeftCols = 0;   // 设定左侧固定列数为0

                this.setCheckColumn(tbMain);
                this.setMarkColumn(tbMain);

                this.setTableListHead(tbMain, res.ListHeadList);
                this.setTableListData(tbMain, res.ListHeadList, res.DataList, res.ShowConvertAssemble, showMode);

                tbMain.AutoWidth();
                tbMain.Refresh();
            }
            catch (System.Exception ex)
            {
                ////异常注释完善。张绍林-20151027
                throw new ClsBaseException("表格数据装载错误：" + ex.Message, ex.InnerException);
            }
        }

        /// <summary>
        /// add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
        /// 1
        /// </summary>
        /// <param name="tbMain">tbMain</param>
        protected void setCheckColumn(Table tbMain)
        {
            if (this.showCheckColumn)
            {
                Column col = new CheckBoxColumn();
                col.DataPropertyName = "ShowRowCheckBoxColumn";
                tbMain.Columns.Insert(0, col);
                tbMain.FixedLeftCols += 1;
            }
        }

        /// <summary>
        /// add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
        /// 1
        /// </summary>
        /// <param name="tbMain">tbMain</param>
        protected void setMarkColumn(Table tbMain)
        {
            if (this.showMarkColumn)
            {
                Column col = new MarkColumn();
                col.DataPropertyName = "ShowRowIndexColumn";
                tbMain.Columns.Insert(tbMain.FixedLeftCols, col);
                tbMain.FixedLeftCols += 1;
            }
        }

        /// <summary>
        /// add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
        /// 装载列头
        /// </summary>
        /// <param name="tbMain">目标表格</param>
        /// <param name="headKeys">列头信息</param>
        private void setTableListHead(Table tbMain, List<ListHeadInfo> headKeys)
        {
            int colCount = 0;
            foreach (ListHeadInfo headKey in headKeys)
            {
                Column col = new Column();
                col.Text = headKey.Text;
                col.DataPropertyName = headKey.Key;
                ////临时解决办法，将审核状态列强制指定为string类型。后期要调整Pojo的列名，或者是使用列词典作翻译。
                if (col.DataPropertyName == "N_CHECK_STATE")
                {
                    col.DataType = ColumnDataType.String;
                }

                col.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Center;
                tbMain.Columns.Add(col);

                if (headKey.Align == null)
                {
                    headKey.Align = "L";
                }

                this.setCellTextAlign(col, headKey.Align.Trim());
                colCount++;
            }

            ////STORY #35551 申万宏源证券—树形结构组合代码按数字由小到大排序。张绍林-20161107
            ////读取组合代码数据类型
            if (tbMain.Columns["C_PORT_CODE"] != null)
            {
                ////tbMain.Columns["C_PORT_CODE"].DataType = new ClsInterface().ReadPortCodeDataType();
            }
        }

        /// <summary>
        /// add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
        /// 1
        /// </summary>
        /// <param name="curCol">列</param>
        /// <param name="colAlign">对齐方式</param>
        protected void setCellTextAlign(Column curCol, string colAlign)
        {
            // 设置列的对齐方式
            if (colAlign.Trim().ToString() == "L")
            {
                curCol.CellTextAlign = ContentAlignment.MiddleLeft;
            }
            else if (colAlign.Trim().ToString() == "R")
            {
                curCol.CellTextAlign = ContentAlignment.MiddleRight;
            }
            else if (colAlign.Trim().ToString() == "C")
            {
                curCol.CellTextAlign = ContentAlignment.MiddleCenter;
            }
            else
            {
                curCol.CellTextAlign = ContentAlignment.MiddleLeft;
            }
        }

        /// <summary>
        /// add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
        /// 设置表格行数据
        /// </summary>
        /// <param name="tbMain">表格</param>
        /// <param name="headKeys">列头信息</param>
        /// <param name="dataList">数据信息</param>
        /// <param name="showConvAssemble">翻译信息</param>
        /// <param name="formMode">数据展示模式</param>
        protected void setTableListData(Table tbMain, List<ListHeadInfo> headKeys, List<BasePojo> dataList, Dictionary<string, Dictionary<string, string>> showConvAssemble, ClsEnums.KTableDataShowMode formMode)
        {
            Dictionary<string, Row> htView = new Dictionary<string, Row>();

            foreach (BasePojo pojo in dataList)
            {
                Row row = this.CreateRow(pojo, headKeys, showConvAssemble);
                if (formMode == ClsEnums.KTableDataShowMode.TreeMode)
                {
                    this.setHtViewExtendsPojo(htView, pojo, row);
                }
            }

            if (formMode == ClsEnums.KTableDataShowMode.TreeMode)
            {
                this.loadTreeViewExtendsPojo(tbMain, htView);
            }

            //// 将视图放入到tabMain.Tag中，便于其他地方使用byleeyu20121201
            tbMain.Tag = htView;
            if (tbMain.Name.Equals("tbMain"))
            {
                ////刷新B区tbMain数据
                this.refreshTbMain();
            }
        }

        /// <summary>
        /// add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
        /// 根据Pojo创建行
        /// </summary>
        /// <param name="pojo">BasePojo</param>
        /// <param name="headKeys">表头信息</param>
        /// <param name="showConvAssemble">翻译信息</param>
        /// <returns>返回创建好的行</returns>
        public virtual Row CreateRow(BasePojo pojo, List<ListHeadInfo> headKeys, Dictionary<string, Dictionary<string, string>> showConvAssemble)
        {
            object cellValueObj = null;
            string cellValue = string.Empty;
            string keyConvServiceId = string.Empty;
            int auditState = 0;
            Dictionary<string, string> showConvDict;

            Row row = new Row();
            row.Tag = pojo;
            if (this.showCheckColumn)
            {
                row.Cells.Add(new Cell());
            }

            if (this.showMarkColumn)
            {
                row.Cells.Add(new Cell());
            }

            string headKeyCode;
            string formatStr;
            foreach (ListHeadInfo headKey in headKeys)
            {
                headKeyCode = headKey.Key;
                formatStr = headKey.Format;
                if (headKeyCode.Equals("N_CHECK_STATE"))
                {
                    headKeyCode = "AuditState";
                }
                else if (headKeyCode.Equals("C_CHECK_TIME"))
                {
                    headKeyCode = "AuditDate";
                }
                else if (headKeyCode.Equals("C_CHECK_BY"))
                {
                    headKeyCode = "OperUser";
                }
                else if (headKeyCode.Equals("C_UPDATE_TIME"))
                {
                    headKeyCode = "ModifyDate";
                }
                else if (headKeyCode.Equals("C_UPDATE_BY"))
                {
                    headKeyCode = "Modifier";
                }

                cellValueObj = ReflectBase.getAttrValue(headKeyCode, pojo, false);

                if (cellValueObj == null)
                {
                    cellValue = string.Empty;
                }
                else
                {
                    if (cellValueObj is DateTime)
                    {
                        DateTime tempDate = Convert.ToDateTime(cellValueObj);
                        if (tempDate.Date == new DateTime(1, 1, 1))
                        {
                            ////日期部分为1-1-1时，说明后台丢过来的是空日期，要做特殊处理-张绍林-20170104
                            ////BUG #149120 【集成测试】【数据清算管理】日期字段为空时前台显示为0001-01-01
                            cellValue = string.Empty;
                        }
                        else
                        {
                            cellValue = tempDate.ToString(formatStr);
                        }
                    }
                    else if (cellValueObj is List<string>)
                    {
                        ////BUG #159415 发件策略中选择组合保存之后，查询显示时，组合变成了代码 雷建华 20170512
                        ////将List<string>类型的数据用"|"分隔
                        List<string> valueList = cellValueObj as List<string>;
                        if (valueList != null && valueList.Count > 0)
                        {
                            cellValue = string.Join("|", valueList.ToArray());
                        }
                    }
                    else
                    {
                        cellValue = cellValueObj.ToString();
                        //// 因ListHeader.xml中只有数值与日期才格式化，有格式化的都认为数值型
                        if (formatStr != null && formatStr.Trim().Length > 0)
                        {
                            cellValue = ClsFunction.formatNumber(formatStr, cellValue);
                        }
                    }
                }

                if (showConvAssemble != null && !"false".Equals(headKey.ShowConvert))
                {
                    keyConvServiceId = headKey.ServiceId;

                    if (keyConvServiceId == null)
                    {
                        keyConvServiceId = string.Empty;
                    }

                    if (showConvAssemble.ContainsKey(keyConvServiceId))
                    {
                        showConvDict = showConvAssemble[keyConvServiceId];
                        if (showConvDict != null)
                        {
                            if (showConvDict.ContainsKey(cellValue))
                            {
                                cellValue = showConvDict[cellValue];
                            }
                            else if (cellValue != null && cellValue.Contains("|"))
                            {
                                cellValue = this.TranslateMultiValues(cellValue, showConvDict);
                            }
                        }
                    }


                }

                if ("null".Equals(cellValue))
                {
                    cellValue = string.Empty;
                }

                row.Cells.Add(new Cell(cellValue));
            }

            if (pojo is AuditableParamPojo)
            {
                object auditValueObj = ReflectBase.getAttrValue("AuditState", pojo, false);

                if (auditValueObj == null)
                {
                    auditState = 0;
                }
                else
                {
                    auditState = int.Parse(auditValueObj.ToString());
                }

                this.setRowBackColor(auditState, row); // 设置行颜色只按有审核状态的来设置
            }

            return row;
        }

        /// <summary>
        /// add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
        /// 翻译多值数据
        /// </summary>
        /// <param name="cellValue">待翻译的值</param>
        /// <param name="showConvDict">词典</param>
        /// <returns>返回翻译好的值</returns>
        private string TranslateMultiValues(string cellValue, Dictionary<string, string> showConvDict)
        {
            ////支持翻译多值的下拉框数据。张绍林-20161111
            string tempCellValue = string.Empty;
            string[] keyArray = cellValue.Split(new string[] { "|" }, StringSplitOptions.RemoveEmptyEntries);
            if (keyArray != null)
            {
                foreach (string key in keyArray)
                {
                    if (showConvDict.ContainsKey(key))
                    {
                        tempCellValue += showConvDict[key] + "|";
                    }
                }

                tempCellValue = tempCellValue.TrimEnd('|');
            }

            if (tempCellValue != string.Empty)
            {
                cellValue = tempCellValue;
            }

            return cellValue;
        }

        /// <summary>
        /// add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
        /// 设置数据行的背景色
        /// </summary>
        /// <param name="checkState">当前记录的审核状态</param>
        /// <param name="curRow">当前行对象</param>
        private void setRowBackColor(int checkState, Row curRow)
        {
            // 根据审核状态设置行的前景色
            if (checkState == 0)
            {
                curRow.ForeColor = ClsConstant.ColorUnAudit;
            }
            else
            {
                curRow.ForeColor = ClsConstant.ColorAudit;
            }
        }

        /// <summary>
        /// add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
        /// 1
        /// </summary>
        /// <param name="htView">htView</param>
        /// <param name="basePojo">basePojo</param>
        /// <param name="row">row</param>
        protected void setHtViewExtendsPojo(Dictionary<string, Row> htView, BasePojo basePojo, Row row)
        {
            string treeNode = string.Empty;
            if (TreeViewUtils.isAvailablePojoTreeViewAttribute(basePojo.GetType()))
            {
                NodeDesc nodeDesc = basePojo.GetType().GetCustomAttributes(typeof(NodeDesc), true)[0] as NodeDesc;
                treeNode = nodeDesc.TreeNode;
            }
            else if (TreeViewUtils.isAvailableTreeViewAttributes(basePojo.GetType()))
            {
                treeNode = TreeViewUtils.getNodePropName(basePojo.GetType(), TreeViewUtils._TreeNode);
            }
            else
            {
                treeNode = "NodeCode";
            }

            string nodeCode = ReflectBase.getAttrValue(treeNode, basePojo, false) as string;
            //// 根据节点代码判断是否有重复数据
            if (!htView.ContainsKey(nodeCode))
            {
                htView.Add(nodeCode, row);
            }
        }

        /// <summary>
        /// add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
        /// 1
        /// </summary>
        /// <param name="tbMain">3</param>
        /// <param name="htView">2</param>
        protected void loadTreeViewExtendsPojo(Table tbMain, Dictionary<string, Row> htView)
        {
            string nodeCode = string.Empty;
            string parentCode = string.Empty;
            string parentPropName = string.Empty;
            string nodePropName = string.Empty;
            ////B区数据需要字典排序，A区数据不能按照字典排序。因为A区需要记录拖动位置
            if (tbMain.Name.Equals("tbMain"))
            {
                TableListLoader tableLoader = new TableListLoader();
                tableLoader.showCheckColumn = this.showCheckColumn;
                tableLoader.showMarkColumn = this.showMarkColumn;
                tableLoader.DictionarySort(htView);
            }

            foreach (KeyValuePair<string, Row> ider in htView)
            {
                // 在集合中判断父节点，如果查到则将本节点加到row的subrow中，查不到则把row添加到table中
                Row tempRow = ider.Value as Row;
                BasePojo pojo = (BasePojo)tempRow.Tag;

                nodeCode = ider.Key;

                if (TreeViewUtils.isAvailablePojoTreeViewAttribute(pojo.GetType()))
                {
                    NodeDesc nodeDesc = pojo.GetType().GetCustomAttributes(typeof(NodeDesc), true)[0] as NodeDesc;
                    parentPropName = nodeDesc.ParentNode;
                    nodePropName = nodeDesc.TreeNode;
                }
                else if (TreeViewUtils.isAvailableTreeViewAttributes(pojo.GetType()))
                {
                    parentPropName = TreeViewUtils.getNodePropName(pojo.GetType(), TreeViewUtils._ParentNode);
                    nodePropName = TreeViewUtils.getNodePropName(pojo.GetType(), TreeViewUtils._TreeNode);
                }
                else
                {
                    parentPropName = "ParentCode";
                    nodePropName = "NodeCode";
                }

                nodeCode = ReflectBase.getAttrValue(nodePropName, pojo) as string;
                parentCode = ReflectBase.getAttrValue(parentPropName, pojo) as string;

                // 如果存在表的父级节点，且父节点不是本身
                if (parentCode != null && htView.ContainsKey(parentCode) && nodeCode != parentCode)
                {
                    htView[parentCode].SubRows.Add(tempRow);
                }
                else
                {
                    ////ReflectBase.setAttrValue("N_Level", pojo, 1); // 一级父节点默认级别为1 by leeyu 20111102
                    //// 对有非父级节点的处理
                    tbMain.Rows.Add(tempRow);
                }
            }

            this.UpdateRowImage(tbMain);
        }

        /// <summary>
        ///  add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
        /// 更新行图标
        /// </summary>
        /// <param name="table">待更新的表</param>
        private void UpdateRowImage(Table table)
        {
            table.RowImageExpand = FAST.Resource.Resource.folder_open;
            table.RowImageCollapse = FAST.Resource.Resource.folder;
            table.RowImageChild = FAST.Resource.Resource.leaf;
        }

        /// <summary>
        ///  add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
        /// 被调用处理加载A区数据的方法
        /// </summary>
        protected override void loadLeftDataProcMVC()
        {
            AssetsTree_A pojo = null;
            if (this.TableLeftMain.SelectedRow != null)
            {
                pojo = this.TableLeftMain.SelectedRow.Tag as AssetsTree_A;
            }

            QueryRes res = this.yssGetLeftDataMVC();
            this.FirstWriteAssItemsToConfig(res, "CustomAssType");
            this.ReadAssItemFromConfig(null, res, "CustomAssType");
            this.setTreeViewTable(tbLeftMain, res, false, false, YssLeftKTableShowMode);
            if (res.DataList.Count > 0)
            {
                if (pojo != null)
                {
                    RowCollection rowsColl = tbLeftMain.GetAllRows(this.tbLeftMain.Rows, false, true);
                    for (int i = 0; i < rowsColl.Count; i++)
                    {
                        AssetsTree_A treepojo = rowsColl[i].Tag as AssetsTree_A;
                        if (treepojo.C_TR_CODE.Equals(pojo.C_TR_CODE))
                        {
                            rowsColl[i].Selected = true;
                            break;
                        }
                    }
                }
                else
                {
                    tbLeftMain.Rows[0].Selected = true;
                }
            }

            this.AddTableContextMenu(tbLeftMain);
        }

        /// <summary>
        /// 重写反审核信息，更新A区数据状态(根节点)
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnUnAudit_Click(object sender, EventArgs e)
        {
            if (this.tbMain.CheckedRows.Count > 0)
            {
                base.btnUnAudit_Click(sender, e);
                this.loadLeftDataProcMVC();
            }
        }

        /// <summary>
        /// zhoushuhang 20180319 BUG195989产品树形结构界面A区选中一条数据，点击B区的新增下级节点，弹出的界面上上级结构没有自动加载
        /// 新增扩展按钮事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnNew_ExpandChange(object sender, EventArgs e)
        {
            this.setBtnNewSubItem();
        }

        /// <summary>
        /// zhoushuhang 20180319  BUG195989产品树形结构界面A区选中一条数据，点击B区的新增下级节点，弹出的界面上上级结构没有自动加载
        /// 设置新增子按钮数据
        /// </summary>
        private void setBtnNewSubItem()
        {
            if (this.tbMain.SelectedRow != null)
            {
                ////清空新增按钮下的子按钮
                this.btnBar.clearButtonSubItem(ClsButtonName.BtnNew);
                AssetsTree_B tree_B = this.tbMain.SelectedRow.Tag as AssetsTree_B;
                ////1、当前选中为根节点（即树形结构）时，只可选择“新增下级节点”和“新增组合”；
                if (!this.tbMain.SelectedRow.HasParent && this.tbMain.SelectedRow.HasChild)
                {
                    string[] arySetSubName = { "新增下级节点", "新增组合" };
                    this.setSubButtons(arySetSubName);
                }
                else if (this.tbMain.SelectedRow.HasParent && !this.tbMain.SelectedRow.HasChild && tree_B.IsParent == 0)
                {
                    ////2、鼠标当前选中为组合，只可选择“新增组合”。
                    string[] arySetSubName = { "新增组合" };
                    this.setSubButtons(arySetSubName);
                }
                else
                {
                    ////3鼠标当前选中为子节点，可选择“新增同级节点”、“新增下级节点”和“新增组合”
                    string[] arySetSubName = { "新增同级节点", "新增下级节点", "新增组合" };
                    this.setSubButtons(arySetSubName);
                }
            }
        }

        /// <summary>
        /// 加载用户信息
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboUser_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            ClsAssocia asc = new ClsAssocia();
            asc = ClsClzCfgMgr.getAssociaParam("assetsTree_A");
            this.cboUser.Items.Clear();
            Type serviceTypeA = ReflectBase.YssGetType(asc.ServiceDllName, asc.ServiceName);
            IAssertTree_AService assetTreeAService = (IAssertTree_AService)ServiceFactory.createService(serviceTypeA);
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            QueryRes res = assetTreeAService.getTreeViewData(paraDict);
            foreach (BasePojo basePojo in res.DataList)
            {
                Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(((AssetsTree_A)basePojo));
                cboUser.Items.Add(entity);
            }

            e.IsCancel = true;

        }

        /// <summary>
        /// BUG #241253 产品树形结构审核按钮无效
        /// add by xuhanbing 20190125
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnAudit_Click(object sender, EventArgs e)
        {
            if (this.TableLeftMain.SelectedRow != null)
            {
                AssetsTree_A pojo = this.TableLeftMain.SelectedRow.Tag as AssetsTree_A;
                if (this.tbMain.CheckedRows.Count > 0)
                {
                    ////勾选的节点,id如果为空，说明并未在库中存在，只是加载显示出来未分配的
                    string rId = (this.tbMain.CheckedRows[0].Tag as AssetsTree_B).Id;
                    if (!string.IsNullOrEmpty(pojo.C_DV_UN_DIS) && "1".Equals(pojo.C_DV_UN_DIS)
                        && string.IsNullOrEmpty(rId))
                    {
                        new MessageDialog().Show("未分配节点上的组合,不能审核,请先将组合分配到上级节点！", "失败", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        return;
                    }

                    base.btnAudit_Click(sender, e);
                    this.loadLeftDataProcMVC();
                }
            }
        }
    }
}
