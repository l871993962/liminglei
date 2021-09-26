using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;

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
using System.Text.RegularExpressions;
using Yss.KTable.Collections;
using YssProductInfo.Support.Ab.AssetsTree.Service;
using YssProductInfo.Support.Ab.AssetsTree.Pojo;
using YssInformation.Support.Fun;
using Yss.Controls;


namespace YssProductInfo.Ab.AssetsTree.Form
{
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
        /// 构造方法.
        /// </summary>
        public Frm_ASSETS_TREE_L()
        {
            InitializeComponent();
            hasLeftSetForm = true;
            bUseMVCServiceLeft = true;
            bUseMVCService = true;
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
            string cond = "";
              
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
            string cond = "";
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
            string search = this.yssBuildLeftCheckRowsStr("pd_assetsTree_A");
            ////查询“未分配”时，多传一个值  2017-02-06 zhujinyang STORY36179
            string test = this.yssBuildLeftCheckRowsStr("pd_assetsTree_A", "C_TR_NAME");
            if ("未分配".Equals(test))
            {
                paraDict.Add("test", search);
                search = Regex.Replace(search, "'", "");
                paraDict.Add("C_TR_CODE", search);
            }
            else 
            {
                search = Regex.Replace(search, "'", "");
                paraDict.Add("ARRAY_C_TR_CODE", search);
                
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
            if (assetTreeAService == null) 
            {
                ClsAssocia asc = new ClsAssocia();
                asc = ClsClzCfgMgr.getAssociaParam("pd_assetsTree_A");

                Type serviceTypeA = ReflectBase.YssGetType(asc.ServiceDllName, asc.ServiceName);
                assetTreeAService = (IAssertTree_AService)ServiceFactory.createService(serviceTypeA);
            }

            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            res = assetTreeAService.getTreeViewData(paraDict);
            this.matchSearchStr = new string[] { "C_TR_CODE", "C_TR_CODE_P", "C_TR_NAME" }; // 【搜索】功能匹配的属性
            return res;
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
            assetTreeAService = (IAssertTree_AService)ServiceFactory.createService(serviceTypeA);
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
    }
}
