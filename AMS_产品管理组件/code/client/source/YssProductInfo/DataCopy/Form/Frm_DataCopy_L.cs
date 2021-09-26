using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Data;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.Bussiness.Form;
using FAST.Common.Service.Pojo.Base;
using Yss.KTable.Models;
using Yss.KTable.Events;
using System.Collections;
using FAST.Common.Service.Services.Base;
using Yss.KRichEx.AutoFilter.Model;
using FAST.Common.Service.Pojo;
using FAST.Core.Context;
using FAST.Core.Util;
using FAST.Platform.Right.Service;
using FAST.Core.Communication.Service;
using FAST.Core.BaseControl.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Core.BaseControl.Fun;
using FAST.Core.Resource;
using Yss.KTable.Collections;
using System.IO;
using System.Xml;
using FAST.Common.Service.Services;
using FAST.Core.Exceptions;
using FAST.Core.BaseControl;
using YssProductInfo.Support.DataCopy.Service;
using YssProductInfo.Support.DataCopy.Pojo;
using YssProductInfo.Support.Plan.Pojo;
using YssProductInfo.Plan.Form;
using YssProductInfo.Support.Fun;

namespace YssProductInfo.DataCopy.Form
{
    /// <summary>
    /// 数据复制
    /// </summary>
    /// A区走框架模式，BUG #103304 2014-11-10
    public partial class Frm_DataCopy_L : FrmBaseOper
    {
        /////// <summary>
        /////// 是否保存用户修改的数据项
        /////// </summary>
        ////private string isSave = "false";

        /// <summary>
        /// 组合Service
        /// </summary>
        private IRightPortService portService = null;

        /// <summary>
        /// 处理叶子list树的工具对象
        /// </summary>
        private ClsTreeLeafList ctll = null;

        /// <summary>
        /// 参照组合缓存
        /// </summary>
        private List<PortRight> portRight = null;

        /// <summary>
        /// 右键菜单可选项
        /// STORY60683需求上海-[中银国际]金融资产管理平台V4.5[高]20180814（产品复制创建产品参数复制综合需求）
        /// </summary>
        private ToolStripMenuItem typeFilterItem;

        /// <summary>
        /// 下拉列表
        /// STORY60683需求上海-[中银国际]金融资产管理平台V4.5[高]20180814（产品复制创建产品参数复制综合需求）
        /// </summary>
        private ToolStripDropDown typeFilterPanel;

        /// <summary>
        /// 用于右键“复制参数筛选”下拉列表中的table
        /// STORY60683需求上海-[中银国际]金融资产管理平台V4.5[高]20180814（产品复制创建产品参数复制综合需求）
        /// </summary>
        private Table filterTable;

        /// <summary>
        /// 用于备份右键“复制参数筛选”下拉列表中的table
        /// STORY60683需求上海-[中银国际]金融资产管理平台V4.5[高]20180814（产品复制创建产品参数复制综合需求）
        /// </summary>
        private Table cloneTable;

        /// <summary>
        /// 保存后台查询所有复制选项结果
        /// STORY60683需求上海-[中银国际]金融资产管理平台V4.5[高]20180814（产品复制创建产品参数复制综合需求）
        /// </summary>
        private List<BasePojo> dataList = null;

        /// <summary>
        /// 业务方案接口
        /// </summary>
        private IBusinessPlanService businessPlanService = null;

        /// <summary>
        /// 首选方案描述
        /// </summary>
        private string planDesc = ClsContext.currentUser.C_USER_CODE + ":复制参数首选方案";

        /// <summary>
        /// 当前用户
        /// </summary>
        private string user = "|" + ClsContext.currentUser.C_USER_CODE;

        /// <summary>
        /// 选方案包括的清算项
        /// </summary>
        private List<string> itemsInPlan = null;

        /// <summary>
        /// 标记filterTable是否开启单元格勾选状态改变事情
        /// STORY60683需求上海-[中银国际]金融资产管理平台V4.5[高]20180814（产品复制创建产品参数复制综合需求）
        /// </summary>
        private bool flag = false;

        /// <summary>
        /// 初始化
        /// </summary>
        public Frm_DataCopy_L()
        {
            this.bUseMVCServiceLeft = true;
            this.bUseMVCService = true;
            InitializeComponent();
            addSubBotten();
            this.btnBar.setUnVisible("btnPrompt");
            ////this.AreaAConfigInfo.AreaType = FAST.Core.Context.AreaType.BaseDefault;
            this.cboPort.BeforeDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(cboPort_BeforeDropDownClick);
            ////this.cboPort.YssOnBeforeLoadData += new FAST.Core.BaseControl.YssSelCombox.yssBeforeLoadData(this.cboPort_YssOnBeforeLoadData);
            isValidPortRightBySelf = true;
            //// add by zzk 20150519 此功能为单线程，进度条无意义，隐藏 BUG #112460 产品参数复制界面中，执行完成后，执行的进度条也一直为0% 
            this.barFormStatus.Visible = false;
        }

        /// <summary>
        /// 窗体Load事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected void Frm_DataCopy_Load(object sender, EventArgs e)
        {
            this.AddExeSubButton();
            this.AddPlanButton();
        }

        /// <summary>
        /// 添加默认的控件
        /// </summary>
        private void addSubBotten()
        {
            ////添加核对按钮 liuxiang 2018-11-5 BUG227054净值确认管理查询特别卡顿
            ClsButtonInfo btnCheck = new ClsButtonInfo();
            btnCheck.Image = new Bitmap(FAST.Resource.Resource.btnSearch, 24, 24);
            btnCheck.Name = ClsButtonName.BtnRecall;
            btnCheck.Text = "检查";
            btnCheck.Tooltip = "检查";
            btnCheck.ClickEvent += new EventHandler(btnCheck_Click);
            this.btnBarOper.addButton(btnCheck, 1);
        }

        /// <summary>
        /// 重写A区配置信息，走基类老旧模式。
        /// </summary>
        ////public override FAST.Core.Context.AreaAConfigInfo AreaAConfigInfo
        ////{
        ////    get
        ////    {
        ////        base.AreaAConfigInfo.AreaType = FAST.Core.Context.AreaType.BaseDefault;
        ////        return base.AreaAConfigInfo;
        ////    }
        ////}

        /// <summary>
        /// 初始化Service
        /// </summary>
        protected override void initServiceMVC()
        {
            base.initServiceMVC();
            portService = ServiceFactory.createService<IRightPortService>();
            ctll = new ClsTreeLeafList();
            businessPlanService = ServiceFactory.createService<IBusinessPlanService>();
        }

        /// <summary>
        /// 加载A区
        /// </summary>
        public override void yssLoadLeftData()
        {
            loadLeftPortInfo();
        }

        /// <summary>
        /// 1
        /// </summary>
        /// <param name="pojo">2</param>
        /// <param name="isQueryData">3</param>
        /// <returns>4</returns>
        public override QueryRes getMainListDataMVC(BasePojo pojo, bool isQueryData)
        {
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("dataClass", "CopyData");
            paraDict.Add("C_PORT_CODE_USER", this.cboPort.Value);
            paraDict.Add("C_PORT_CODE_POST", this.cboPort.Value);
            paraDict.Add("C_PORT_CODE_FUN", this.cboPort.Value);
            if (this.cboQxlb.Checked == true)
            {
                paraDict.Add("C_PORT_CODE_POST_SHOW", "true");
            }
            else
            {
                paraDict.Add("C_PORT_CODE_POST_SHOW", "false");
            }

            QueryRes res = ((IDataCopyService)this.dataService).queryByCondition(paraDict);
            ctll.drawCheckIconCell(tbMain, res.DataList, 5);
            if (dataList == null)
            {
                dataList = new List<BasePojo>();
                dataList.AddRange(res.DataList.ToArray());
            }
            ////ClsTreeLeafList ctll = new ClsTreeLeafList();
            ////List<BasePojo> dataList = res.DataList;
            List<BasePojo> bojoList = new List<BasePojo>();
            List<string> stringList = new List<string>();

            foreach (BasePojo basePojo in dataList)
            {
                CopyData copyData = (CopyData)basePojo;
                if (!stringList.Contains(basePojo.Id)
                    || !stringList.Contains(copyData.C_DATA_NAME))
                {
                    stringList.Add(basePojo.Id);
                    stringList.Add(copyData.C_DATA_NAME);
                    bojoList.Add(basePojo);
                }
            }

            cboPlanCode_BeforeDropDownClick(null, new Yss.KRichEx.AutoFilter.Events.DropDownEventArgs());
            BusinessPlan plan = null;
            foreach (ControlEntity ce in this.cboPlanCode.Items)
            {
                plan = ce.DataEntity as BusinessPlan;
                if (plan.C_DESC.Equals(planDesc))
                {
                    this.cboPlanCode.Value = plan.C_PLAN_CODE;
                    break;
                }
            }

            ////根据 用户常用数据选择单元格
            ////checkedCell();
            ReadCopyRowsFromConfig(this.tbMain, this.YssFormMenu.C_FUN_NAME);
            ////this.cloneTable = this.tbMain.Clone(false);
            ////ctll.drawCheckIconCell(this.cloneTable, res.DataList, 5);
            tbMain.AutoWidth();
            tbMain.Refresh();
            return new QueryRes();
        }

        /// <summary>
        /// 检查按钮点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected void btnCheck_Click(object sender, EventArgs e)
        {
            Frm_DataCopy_Check copyCheckForm = new Frm_DataCopy_Check();
            string search = this.yssBuildLeftCheckRowsStr("portfolio");  // tanwenjie 2011.7.28 获取A区选中的行
            search = search.Replace("'", ""); 
            copyCheckForm.PORTLIST = search;
            copyCheckForm.YssFormMenu = new SysFun();
            copyCheckForm.YssFormMenu.C_FUN_CODE = "dataCopyCheck";
            copyCheckForm.YssFormMenu.YssAssocia = ClsClzCfgMgr.getAssociaParam(copyCheckForm.YssFormMenu.C_FUN_CODE);
            copyCheckForm.yssInitForm();
            copyCheckForm.ShowDialog();
        }

        /// <summary>
        /// List窗体关闭事件
        /// </summary>
        /// <param name="sender">窗体</param>
        /// <param name="e">事件参数</param>
        private void frmBaseList_FormClosed(object sender, FormClosedEventArgs e)
        {
            this.ValidateSuspensionAreaA();
        }

        #region 加载A区部分
        /// <summary>
        /// 获取组织产品信息
        /// </summary>
        private void loadLeftPortInfo()
        {
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            QueryRes res = new QueryRes();
            leftDataFunCode = AssociaFAST.pd_portfolio.ToString();
            res.ListHeadList = getPortHeadListInfo();
            res.DataList = changePortListToBasePojoList(portService.getRightManagePortTree(paraDict));
            new TableListLoader().loadTable(tbLeftMain, res, false, false, ClsEnums.KTableDataShowMode.TreeMode);
            this.matchSearchStr = new string[2] { "C_PORT_NAME_ST", "C_PORT_CODE" };  // 【搜索】功能匹配的属性
        }

        /// <summary>
        /// 1
        /// </summary>
        /// <param name="list">2</param>
        /// <returns>3</returns>
        private List<BasePojo> changePortListToBasePojoList(List<PortRight> list)
        {
            List<BasePojo> retList = new List<BasePojo>();
            foreach (PortRight port in list)
            {
                retList.Add((BasePojo)port);
            }

            return retList;
        }

        /// <summary>
        /// 获取组织用户列头信息
        /// </summary>
        /// <returns>组织用户列头信息</returns>
        private List<ListHeadInfo> getPortHeadListInfo()
        {
            List<ListHeadInfo> headKeyList = new List<ListHeadInfo>();

            ListHeadInfo headKeyInfo = new ListHeadInfo();
            headKeyInfo.Key = "C_PORT_NAME_ST";
            headKeyInfo.Text = "名称";
            headKeyInfo.Align = "LEFT";
            headKeyInfo.Format = "";
            headKeyInfo.ShowConvert = "";

            headKeyList.Add(headKeyInfo);
            headKeyInfo = new ListHeadInfo();

            headKeyInfo.Key = "C_PORT_CODE";
            headKeyInfo.Text = "代码";
            headKeyInfo.Align = "LEFT";
            headKeyInfo.Format = "";
            headKeyInfo.ShowConvert = "";

            headKeyList.Add(headKeyInfo);

            return headKeyList;
        }

        /// <summary>
        /// 获取左侧KTable选择的多行属性，重写了此方法，不获取父节点代码
        /// </summary>
        /// <returns>list</returns>
        public override Dictionary<string, string> yssGetSelTypeItemLeftList()
        {
            Dictionary<string, string> list = new Dictionary<string, string>();
            if (tbLeftMain.ShowCheckBox)
            {
                ////循环所有选择行的数据
                ////Update By lj 2015-02-10 解决BUG #107546 产品参数复制界面，勾选了目标组合和参照组合，点击执行系统报错
                foreach (Yss.KTable.Models.Row row in this.TableLeftMain.CheckedRows)
                {
                    ////判断当前数据是否为组合数据，为组合数据判断当前类型是否为组合
                    if (row.Tag is Port)
                    {
                        if (!ClsConstant._ISPort.Equals(((Port)row.Tag).DATA_TYPE))
                        {
                            continue;
                        }

                        list.Add((row.Tag as Port).C_PORT_CODE, (row.Tag as Port).C_PORT_NAME_ST);
                    }
                    else if (row.Tag is PortRight)
                    {
                        list.Add((row.Tag as PortRight).C_PORT_CODE, (row.Tag as PortRight).C_PORT_NAME_ST);
                    } 
                }
            }
            else
            {
                ////判断当前数据是否为组合数据，为组合数据判断当前类型是否为组合
                foreach (Yss.KTable.Models.Row row in tbLeftMain.CheckedRows)
                {
                    if (row.Tag is PortRight)
                    {
                        if (!ClsConstant._ISPort.Equals(((PortRight)row.Tag).DATA_TYPE))
                        {
                            continue;
                        }

                        list.Add((row.Tag as PortRight).C_PORT_CODE, (row.Tag as PortRight).C_PORT_NAME_ST);
                    }
                }
            }

            return list;
        }

        /////// <summary>
        /////// 获取左侧KTable选择的多行属性，重写了此方法，不获取父节点代码
        /////// </summary>
        /////// <returns>list</returns>
        ////public List<BasePojo> yssGetSelTypeItemLeftListMVC()
        ////{
        ////    List<BasePojo> list = new List<BasePojo>();
        ////    if (tbLeftMain.ShowCheckBox)
        ////    {
        ////        ////循环所有选择行的数据
        ////        foreach (Yss.KTable.Models.Row row in tbLeftMain.CheckedRows)
        ////        {
        ////            ////判断当前数据是否为组合数据，为组合数据判断当前类型是否为组合
        ////            if (row.Tag is PortRight)
        ////            {
        ////                if (!ClsConstant._ISPort.Equals(((PortRight)row.Tag).DATA_TYPE))
        ////                {
        ////                    continue;
        ////                }
        ////            }
        ////            else
        ////            {
        ////                ////不熟组合数据获取最明细的节点数据
        ////                if (!(row.Tag is BasePojo && !row.HasChild))
        ////                {
        ////                    continue;
        ////                }
        ////            }

        ////            list.Add(row.Tag as BasePojo);
        ////        }
        ////    }
        ////    else
        ////    {
        ////        ////判断当前数据是否为组合数据，为组合数据判断当前类型是否为组合
        ////        foreach (Yss.KTable.Models.Row row in tbLeftMain.CheckedRows)
        ////        {
        ////            if (row.Tag is PortRight)
        ////            {
        ////                if (!ClsConstant._ISPort.Equals(((PortRight)row.Tag).DATA_TYPE))
        ////                {
        ////                    continue;
        ////                }
        ////            }
        ////            else
        ////            {
        ////                ////不熟组合数据获取最明细的节点数据
        ////                if (!(row.Tag is BasePojo && !row.HasChild))
        ////                {
        ////                    continue;
        ////                }
        ////            }

        ////            list.Add(row.Tag as BasePojo);
        ////        }
        ////    }

        ////    return list;
        ////}

        #endregion
        /// <summary>
        /// 1
        /// </summary>
        /// <returns>返回的参数</returns>
        private Dictionary<string, string> buildOperConds()
        {
            Dictionary<string, string> dict = new Dictionary<string, string>();
            ////dict.Add("isSave", isSave);
            dict.Add("C_OPER_CODE", execOperCode);
            dict.Add("C_PORT_CODE", this.cboPort.Value);
            dict.Add("PORT_CODE_LIST", getPortCodeList());
            dict.Add("DATA_SVC_LIST", getDataCodeList());
            dict.Add("C_FUN_CODE", _formFun.C_FUN_CODE); //// 添加功能菜单byleeyu20131230
            ////传给后台当前操作用户和岗位，权限赋值时则过滤当前用户和岗位权限，  BUG #103304 2014-11-09 LY
            dict.Add("CurrentUser", ClsContext.currentUser.C_USER_CODE);
            dict.Add("CurrentUserPost", ClsContext.currentUserPostCodes);
            return dict;
        }

        /// <summary>
        /// 判断数据是否合法
        /// </summary>
        /// <param name="arrStruct">arrStruct</param>
        /// <returns>arrStruct</returns>
        protected override bool checkParams(ref string[] arrStruct)
        {
            if (this.cboPort.Value == null || this.cboPort.Value.Trim().Length == 0)
            {
                ////没有选择参照组合
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, status));
                return false;
            }

            ////Dictionary<string, string> leftChecks = base.yssGetSelTypeItemLeftList();
            ////////List<BasePojo> portList = yssGetSelTypeItemLeftListMVC();
            ////if (leftChecks.Count == 0)
            ////{
            ////    ////没有选择目标组合
            ////    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("002", _formFun, status));
            ////    return false;
            ////}

            List<BasePojo> dataList = new List<BasePojo>();
            ////getCheckedItems(tbMain.Rows, dataList);
            ctll.getCheckedLeafCell(tbMain.Rows, dataList);
            if (dataList.Count == 0)
            {
                ////没有选择数据项
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("003", _formFun, status));
                return false;
            }            

            ////验证serviceid配置是否正确
            List<string> res = ((IDataCopyService)this.dataService).verify(buildOperConds());
            if (res != null && res.Count > 1)
            {
                ////ServiceCode配置错误 
                ClsRetInfo retInfo = ClsRetInfoDealer.getExtWarn("004", _formFun, status);
                StringBuilder sb = new StringBuilder();
                Hashtable ht = new Hashtable();
                foreach (CopyData data in dataList)
                {
                    for (int i = 0; i < res.Count; i++)
                    {
                        if (data.C_DATA_CODE.Equals(res[i]))
                        {
                            sb.Append(data.C_DATA_NAME).Append(",");
                        }
                    }
                }

                if (sb.Length > 1)
                {
                    sb.Remove(sb.Length - 1, 1);
                }

                ht["C_DATA_CODE"] = sb.ToString();
                retInfo.specStrHs = ht;
                YssMessageBox.ShowCommonInfo(retInfo);
                return false;
            }

            return true;
        }

        /// <summary>
        /// 获取左区域选定组合代码
        /// 格式：P1,P2...
        /// </summary>
        /// <returns>portCodes.ToString()</returns>
        protected override string getSelPortCodes()
        {
            StringBuilder portCodes = new StringBuilder(); // 组合代码
            ////List<BasePojo> aryPort = null;
            ////aryPort = yssGetSelTypeItemLeftListMVC();
            if (_leftPojos.Count == 0)
            {
                return "";
            }

            foreach (KeyValuePair<string, string> kv in _leftPojos)
            {
                ////PortRight port = (PortRight)aryPort[i];
                portCodes.Append(kv.Key).Append(",");
            }

            if (portCodes.Length > 1)
            {
                portCodes.Remove(portCodes.Length - 1, 1);
            }

            return portCodes.ToString();
        }

        /// <summary>
        /// 目标组合
        /// </summary>
        /// <returns>s</returns>
        private string getPortCodeList()
        {
            string dataCodeList = "";
            ////List<BasePojo> list = yssGetSelTypeItemLeftListMVC();
            ////Dictionary<string, string> leftChecks = base.yssGetSelTypeItemLeftList();
            Dictionary<string, string> leftChecks = yssGetSelTypeItemLeftList();
            foreach (KeyValuePair<string, string> kv in leftChecks)
            {
                ////如果和参照组合相同则过滤，不能复制相同组合的权限
                if (this.cboPort.Value != null && kv.Key.Equals(this.cboPort.Value))
                {
                    continue;
                }

                dataCodeList += kv.Key + "&";
            }

            return dataCodeList;
        }

        /// <summary>
        /// 获得传到后台的数据项代码以及service_code
        /// </summary>
        /// <returns>s</returns>
        private string getDataCodeList()
        {
            List<BasePojo> list = new List<BasePojo>();

            ctll.getCheckedLeafCell(tbMain.Rows, list);

            string dataCodeList = "";

            if (list.Count > 0)
            {
                foreach (BasePojo pojo in list)
                {
                    CopyData copyData = pojo as CopyData;
                    dataCodeList += copyData.C_DATA_CODE + "=" + copyData.C_SERVICE_CODE + "&";
                }
            }

            return dataCodeList;
        }

        /// <summary>
        /// 执行分段的业务数据处理
        /// </summary>
        /// <returns>返回执行状态</returns>
        public override string doSubSection()
        {
            string c_Result = "";
            
            bool isCancel = false;

            //// 1：执行前的一些处理
            beforeExecute(ref isCancel);

            if (!isCancel)
            {
                //// 2：将数据一次性发送到后台，后台日志通过UDP加载到前台
                ////c_Result = _clsBaseOperAdmin.doOper(ref c_Fun_Code, buildOperConds(ref c_Fun_Code));

                /*
                 * Author : ChenLong
                 * Date   : 2015-01-22
                 * Status : Add
                 * Comment: 验证组合数据权限
                */
                ////Dictionary<string, string> portCodeDic = base.yssGetSelTypeItemLeftList();
                Dictionary<string, string> portCodeDic = yssGetSelTypeItemLeftList();
                ////validPortDataOperRight(portCodeDic);
                if (portCodeDic != null && portCodeDic.Count > 0)
                {
                    StringBuilder portCodeBuilder = new StringBuilder(); 
                    foreach (string portCode in portCodeDic.Keys)
                    {
                        ////如果和参照组合相同则过滤，不能复制相同组合的权限
                        if (portCode.Equals(this.cboPort.Value))
                        {
                            ////edit by caoxingyun 华泰回归测试-产品参数复制，不能复制相同组合
                            if (portCodeDic.Count == 1)
                            {
                                ClsRetInfo info = new ClsRetInfo();
                                info.infoGroup = ClsConstant.INFO_GRP_ATT;
                                info.infoTitle = "提示信息";
                                info.infoContent = "不能复制所选组合与参照组合相等的数据！";
                                info.icon = MessageBoxIcon.Warning;
                                YssMessageBox.ShowCommonInfoText(info);
                                return null;
                            }
                            else
                            {
                                continue;
                            }

                        }

                        portCodeBuilder.Append(portCode).Append("&");
                    }

                    Dictionary<string, string> dict = new Dictionary<string, string>();
                    dict.Add("C_OPER_CODE", execOperCode);
                    dict.Add("C_PORT_CODE", this.cboPort.Value);
                    dict.Add("PORT_CODE_LIST", portCodeBuilder.ToString());
                    dict.Add("DATA_SVC_LIST", getDataCodeList());
                    dict.Add("C_FUN_CODE", _formFun.C_FUN_CODE);
                    dict.Add("CurrentUser", ClsContext.currentUser.C_USER_CODE);
                    dict.Add("CurrentUserPost", ClsContext.currentUserPostCodes);

                    List<BasePojo> selCellPojo = new List<BasePojo>();
                    ctll.getLeafPojo(selCellPojo, tbMain.Rows, false);
                    c_Result = ((IDataCopyService)this.dataService).exe(buildOperConds(), selCellPojo);

                    if (!"".Equals(c_Result))
                    {
                        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo(c_Result));
                    }
                }               

            }

            //// 3：执行后的一些处理
            afterExecute();

            return c_Result;
        }

        /////// <summary>
        /////// 判断是否取消操作
        /////// </summary>
        /////// <param name="isCancel"></param>
        ////protected override void beforeExecute(ref bool isCancel)
        ////{
        ////    isCancel = true;
        ////}

        /// <summary>
        /// 设置执行日志的结构
        /// </summary>
        /// <param name="structRecord">保存日志结构的数组</param>
        protected override void buildRecordStruct(ref string[] structRecord)
        {
            structRecord = new string[] { "PORT", "C_PORT_CODE", "C_DATA_CODE" };
        }

        /// <summary>
        /// 重写,byleeyu 20140418
        /// </summary>
        /// <returns>字典</returns>
        protected override Dictionary<string, string> getSelOperItemTags()
        {
            if (null == this.cboPort.Value)
            {
                return null;
            }

            Dictionary<string, string> dict = new Dictionary<string, string>();
            List<BasePojo> mainSelPojos = getSelOperItemTagsMVC();
            foreach (CopyData data in mainSelPojos)
            {
                if (!dict.ContainsKey(data.C_DATA_CODE_P))
                {
                    dict.Add(data.C_DATA_CODE_P, data.C_DATA_NAME);
                }
            }

            //// BUG #96927,add by caowei,2014-07-05
            string c_Value_Code = this.cboPort.Value == null ? "" : this.cboPort.Value;
            string c_Value_Name = this.cboPort.Name == null ? "" : this.cboPort.Name;
            if (!dict.ContainsKey(c_Value_Code))
            {
                dict.Add(c_Value_Code, c_Value_Name);
            }

            return dict;
        }

        /// <summary>
        /// 查询选中所有数据
        /// </summary>
        private void checkedCell()
        {
            List<string> dataCodeList = ((IDataCopyService)this.dataService).queryCustom();
            if (dataCodeList != null && dataCodeList.Count > 0)
            {
                ctll.checkedCheckIconCell(this.tbMain.Rows, dataCodeList, "C_DATA_CODE");
            }
        }

        /// <summary>
        /// 单元格checked属性改变事件
        /// 控制字节点联动
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbMain_CheckStateChange(object sender, CheckStateChangeEventArgs e)
        {
            Yss.KTable.Models.CheckBoxCell curCell = (Yss.KTable.Models.CheckBoxCell)sender;
            CheckStateChangedAll(curCell, true);
        }

        /// <summary>
        /// STORY60683需求上海-[中银国际]金融资产管理平台V4.5[高]20180814（产品复制创建产品参数复制综合需求）
        /// </summary>
        /// <param name="curCell">curCell</param>
        /// <param name="flag">flag</param>
        private void CheckStateChangedAll(CheckBoxCell curCell, bool flag)
        {
            int count = 0; ////判断是否子节点全部选中
            bool checkOrNo = curCell.Checked ? flag : !flag;
            Yss.KTable.Models.CheckBoxCell parentRowCell = null;
            //// 父子节点状态同步
            // 如果为父节点，则此节点下的所有子节点的勾选状态与其同步
            if (curCell.OwnRow.HasChild)
            {
                foreach (Row row in curCell.OwnRow.SubRows)
                {
                    if (row.HasChild)
                    {
                        foreach (Row lrow in row.SubRows)
                        {
                            if (lrow.HasChild)
                            {
                                foreach (Row llrow in lrow.SubRows)
                                {
                                    foreach (CheckBoxCell tmpCell in llrow.Cells)
                                    {
                                        tmpCell.Checked = checkOrNo;
                                    }
                                }
                            }
                            else
                            {
                                foreach (CheckBoxCell tmpCell in lrow.Cells)
                                {
                                    tmpCell.Checked = checkOrNo;
                                }
                            }
                        }
                    }
                    else
                    {
                        foreach (CheckBoxCell tmpCell in row.Cells)
                        {
                            tmpCell.Checked = checkOrNo;
                        }
                    }
                }
            }
            else
            {
                //// 循环子节点行记录
                foreach (Row row in curCell.OwnRow.ParentRow.SubRows)
                {
                    count += row.Cells.Count;
                    if (row.HasChild)
                    {
                        foreach (Row lrow in row.SubRows)
                        {
                            count += row.Cells.Count;
                            if (lrow.HasChild)
                            {
                                foreach (Row llrow in lrow.SubRows)
                                {
                                    count += row.Cells.Count;
                                    foreach (CheckBoxCell tmpCell in llrow.Cells)
                                    {
                                        // 如果状态相同，则子节点数量减1
                                        if (tmpCell.Checked.ToString().Equals(checkOrNo.ToString()))
                                        {
                                            count--;
                                        }
                                    }
                                }
                            }
                            else
                            {
                                foreach (CheckBoxCell tmpCell in lrow.Cells)
                                {
                                    // 如果状态相同，则子节点数量减1
                                    if (tmpCell.Checked.ToString().Equals(checkOrNo.ToString()))
                                    {
                                        count--;
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        foreach (CheckBoxCell tmpCell in row.Cells)
                        {
                            // 如果状态相同，则子节点数量减1
                            if (tmpCell.Checked.ToString().Equals(checkOrNo.ToString()))
                            {
                                count--;
                            }
                        }
                    }


                }

                // 如果最后计数器为0，则表示子节点的状态一致
                if (count == 0)
                {
                    parentRowCell = (CheckBoxCell)curCell.OwnRow.ParentRow.Cells[0];
                    parentRowCell.Checked = checkOrNo;
                }
            }
        }

        /// <summary>
        /// ////BUG #251614 产品复制界面，勾选父节点不能同步勾选所有子节点
        /// 子行单元格checked属性改变事件
        /// 控制子节点联动
        /// </summary>
        /// <param name="checkState">是否勾选</param>
        /// <param name="subRows">子行集合</param>
        private void tbMain_ChangeSubCheckState(bool checkState, RowCollection subRows)
        {
            foreach (Row row in subRows)
            {
                foreach (CheckBoxCell tmpCell in row.Cells)
                {
                    tmpCell.Checked = checkState;
                }

                if (row.HasChild)
                {
                    this.tbMain_ChangeSubCheckState(checkState, row.SubRows);
                }
            }
        }

        /////// <summary>
        /////// 获取选中的项参数
        /////// </summary>
        /////// <param name="arrStruct">日志的结构</param>
        /////// <param name="selPort">选中的组合信息</param>
        /////// <param name="selItems">选中的项目信息</param>
        ////protected override void getSelParams(ref string[] arrStruct, ref Dictionary<string, string> selPort, ref Dictionary<string, string> selItems)
        ////{
        ////    if (arrStruct == null)
        ////    {
        ////        return;
        ////    }

        ////    foreach (string c_Struct in arrStruct)
        ////    {
        ////        if (c_Struct == null || c_Struct.Length == 0 || c_Struct.Equals("PORT"))
        ////        {
        ////            continue;
        ////        }

        ////        if (c_Struct.Equals("C_PORT_CODE"))
        ////        {
        ////            selPort = new Dictionary<string, string>();
        ////            List<BasePojo> clsBasePojos = yssGetSelTypeItemLeftListMVC();

        ////            foreach (PortRight clsPort in clsBasePojos)
        ////            {
        ////                if (!selPort.ContainsKey(clsPort.C_PORT_CODE))
        ////                {
        ////                    selPort.Add(clsPort.C_PORT_CODE, clsPort.C_PORT_NAME_ST);
        ////                }
        ////            }
        ////        }
        ////        else if (!c_Struct.Equals("DATE"))
        ////        {
        ////            selItems = new Dictionary<string, string>();
        ////            List<BasePojo> mainSelPojos = getSelOperItemTagsMVC();

        ////            foreach (BasePojo clsPojo in mainSelPojos)
        ////            {
        ////                string c_ShowName = c_Struct.EndsWith("CODE") ? (c_Struct.Substring(0, c_Struct.Length - 4) + "NAME") : c_Struct;
        ////                if (c_Struct.Equals("C_PORT_CODE"))
        ////                {
        ////                    string c_Value_Code = this.cboPort.Value;
        ////                    string c_Value_Name = this.cboPort.Name;
        ////                    if (!selItems.ContainsKey(c_Value_Code))
        ////                    {
        ////                        selItems.Add(c_Value_Code, c_Value_Name);
        ////                    }
        ////                }
        ////                else if (c_Struct.Equals("C_DATA_CODE_P"))
        ////                {
        ////                    //// 获取CODE的值
        ////                    string c_Value_Code = FAST.Core.Util.ReflectBase.getAttrValue("C_DATA_CODE_P", clsPojo) as string;

        ////                    //// 获取CODE的值
        ////                    string c_Value_Name = FAST.Core.Util.ReflectBase.getAttrValue("C_DATA_NAME", clsPojo) as string;

        ////                    if (!selItems.ContainsKey(c_Value_Code))
        ////                    {
        ////                        selItems.Add(c_Value_Code, c_Value_Name);
        ////                    }
        ////                }
        ////                else
        ////                {
        ////                    //// 获取CODE的值
        ////                    string c_Value_Code = FAST.Core.Util.ReflectBase.getAttrValue(c_Struct, clsPojo) as string;

        ////                    //// 获取CODE的值
        ////                    string c_Value_Name = FAST.Core.Util.ReflectBase.getAttrValue(c_ShowName, clsPojo) as string;

        ////                    if (!selItems.ContainsKey(c_Value_Code))
        ////                    {
        ////                        selItems.Add(c_Value_Code, c_Value_Name);
        ////                    }
        ////                }

        ////            }
        ////        }
        ////    }
        ////}

        /// <summary>
        /// 获取主区域选中的行/单元格对象
        /// </summary>
        /// <returns>返回选中行/单元格集合对象</returns>
        protected List<BasePojo> getSelOperItemTagsMVC()
        {
            List<BasePojo> selCellPojo = new List<BasePojo>();
            try
            {
                ctll.getLeafPojo(selCellPojo, tbMain.Rows, true);
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }

            return selCellPojo;
        }

        /////// <summary>
        /////// 组合加载，不用根据岗位过滤组合
        /////// </summary>
        /////// <param name="sender">sender</param>
        /////// <param name="e">e</param>
        ////private void cboPort_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        ////{
        ////    e.IsCancel = true;
        ////    Dictionary<string, string> paraDict = new Dictionary<string, string>();
        ////    paraDict.Add("APPY_USER_REAL", ClsContext.currentUser.C_USER_CODE);
        ////    List<PortRight> list = portService.getRightManagePortTree(paraDict);
        ////    List<string> liststr = new List<string>();
        ////    foreach (PortRight pojo in list)
        ////    {
        ////        ////过滤A区所选择项
        ////        Dictionary<string, string>  listA = base.yssGetSelTypeItemLeftList();
        ////        if (listA.Keys.Contains(pojo.C_PORT_CODE))
        ////        {
        ////            continue;
        ////        }

        ////        if (!liststr.Contains(pojo.C_PORT_CODE))
        ////        {
        ////            KTableEntity entity = new KTableEntity(pojo);
        ////            liststr.Add(pojo.C_PORT_CODE);
        ////            e.Collection.Add(entity);
        ////        }
        ////    }

        ////}

        /// <summary>
        /// 组合加载，不用根据岗位过滤组合，不能针对同一组合进行复制
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboPort_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("APPY_USER_REAL", ClsContext.currentUser.C_USER_CODE);
            if (portRight == null || portRight.Count == 0)
            {
                portRight = portService.getRightManagePortTree(paraDict);
            }

            List<string> liststr = new List<string>();
            if (cboPort.Items.Count > 0)
            {
                cboPort.Items.Clear();
            }

            ////过滤A区所选择项，不能针对同一组合进行复制
            Dictionary<string, string> listA = base.yssGetSelTypeItemLeftList();
            foreach (PortRight pojo in portRight)
            {                
                if (listA.Keys.Contains(pojo.C_PORT_CODE))
                {
                    continue;
                }

                if (!liststr.Contains(pojo.C_PORT_CODE))
                {
                    KTableEntity entity = new KTableEntity(pojo);
                    liststr.Add(pojo.C_PORT_CODE);
                    cboPort.Items.Add(entity);
                }
            }
        }

        /// <summary>
        /// 参照组合值改变时重新加载权限管理事件
        /// add by huangjin 2016-9-23 STORY #28950 参数复制功能优化-复制权限和群组以及产品数
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboPort_SelectedValueChanged(object sender, EventArgs e)
        {
            tbMain.Clear();
            this.getMainListDataMVC(new BasePojo(), true);

        }

        //// <summary>
        /////// 自定义常用项
        /////// </summary>
        /////// <param name="sender">sender</param>
        /////// <param name="e">e</param>
        ////private void btnComUse2_Click(object sender, EventArgs e)
        ////{
        ////    try
        ////    {
        ////        ////每次创建组合默认窗体的时候都需要重新给窗体赋值
        ////        this.dataCopy_Com_S = new Frm_DataCopy_Com_S(this);
        ////        this.dataCopy_Com_S.Text = "默认复制数据项设置";
        ////        this.dataCopy_Com_S.Tag = dataAdmin.CurFUN.C_FUN_CODE;
        ////        this.dataCopy_Com_S.ShowDialog();
        ////    }
        ////    catch (Exception ex)
        ////    {
        ////        ////YssMessageBox.ShowDyanInformation("展示设置默认组合窗体信息错误！", ex.Message, "系统提示", MessageBoxIcon.Error, YssCore.Util.ClsEnums.ButtonType.Detail);
        ////        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500002", _formFun, status, ex));
        ////    }
        ////}

        /// <summary>
        /// 重新加载执行项目 liuxiang 2017-1-9 BUG147177汇集调尾处理界面缺少查询按钮
        /// </summary>
        /// <param name="sender">ButtonItem</param>
        /// <param name="e">EventArgs</param>
        public override void btnSearch_Click(object sender, EventArgs e)
        {
            this.tbMain.Clear();
            getMainListDataMVC(baseDataobject, loadDataWhenFormLoad);
        }

        #region

        /// <summary>
        /// 右键新增自定义选项：复制参数筛选
        /// STORY60683需求上海-[中银国际]金融资产管理平台V4.5[高]20180814（产品复制创建产品参数复制综合需求）
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void tbMain_ContextMenuOpening(object sender, CancelEventArgs e)
        {
            this.InitialTypeFilterItem();
        }

        /// <summary>
        /// 初始化选项
        /// STORY60683需求上海-[中银国际]金融资产管理平台V4.5[高]20180814（产品复制创建产品参数复制综合需求）
        /// </summary>
        private void InitialTypeFilterItem()
        {
            if (this.typeFilterItem == null)
            {
                this.typeFilterItem = new ToolStripMenuItem();
                this.typeFilterItem.Text = "复制参数筛选";
                this.typeFilterItem.Click += new EventHandler(TypeFilterItem_Click);
                ////加入到右键菜单中
                this.tbMain.AddContextMenuStripItem(this.typeFilterItem);
            }
        }

        /// <summary>
        /// 复制参数筛选点击事件
        /// STORY60683需求上海-[中银国际]金融资产管理平台V4.5[高]20180814（产品复制创建产品参数复制综合需求）
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void TypeFilterItem_Click(object sender, EventArgs e)
        {
            this.InitialTypeFilterPanel();
            this.typeFilterPanel.Show(Control.MousePosition);
        }

        /// <summary>
        /// 初始化筛选下拉列表
        /// STORY60683需求上海-[中银国际]金融资产管理平台V4.5[高]20180814（产品复制创建产品参数复制综合需求）
        /// </summary>
        private void InitialTypeFilterPanel()
        {
            if (this.typeFilterPanel == null)
            {
                this.typeFilterPanel = new ToolStripDropDown();
            }

            ToolStripControlHost panelHost = null;
            if (this.typeFilterPanel.Items.Count > 0 && this.typeFilterPanel.Items[0] != null && this.typeFilterPanel.Items[0] is ToolStripControlHost)
            {
                panelHost = this.typeFilterPanel.Items[0] as ToolStripControlHost;
            }

            if (panelHost != null && panelHost.Control != null)
            {
                panelHost.Control.Dispose();
            }

            this.typeFilterPanel.Items.Clear();
            if (this.filterTable == null || this.filterTable.Rows.Count == 0)
            {
                ////this.filterTable = this.cloneTable.Clone(true);
                this.filterTable = this.tbMain.Clone(false);
                ctll.drawCheckIconCell(this.filterTable, dataList, 5);
                initFilterTable(this.filterTable, this.YssFormMenu.C_FUN_NAME);
                filterTable.Dock = DockStyle.Fill;
                filterTable.CheckStateChanged += new Yss.KTable.Events.CheckStateChanged(filterTable_CheckStateChanged);
                filterTable.CellMouseClick += new CellEventHandler(filterTable_CellMouseClick);
            }
            ////IEnumerable<Row> typeRows = filterTable.GetAllRows(filterTable.Rows, true, false)
            ////                                      .Where(r => r.ParentRow != null && r.SubRows.Count == 0);
            ////if (typeRows != null && typeRows.Count() > 0)
            ////{
            ////    List<Row> typeRowsTemp = typeRows.ToList();
            ////    foreach (Row subRow in typeRowsTemp)
            ////    {
            ////        subRow.ParentRow.SubRows.Remove(subRow);
            ////    }
            ////}

            if (filterTable.Rows.Count > 0)
            {
                filterTable.AutoScroll = true;
                filterTable.Size = this.tbMain.Size;
                filterTable.Width = 800;
                panelHost = new ToolStripControlHost(filterTable)
                {
                    Dock = DockStyle.Fill,
                    ControlAlign = System.Drawing.ContentAlignment.MiddleCenter,
                    AutoSize = true
                };

                this.typeFilterPanel.Items.Add(panelHost);
                this.typeFilterPanel.MinimumSize = filterTable.Size;
            }
        }

        /// <summary>
        /// STORY60683需求上海-[中银国际]金融资产管理平台V4.5[高]20180814（产品复制创建产品参数复制综合需求）
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void filterTable_CellMouseClick(object sender, CellEventArgs e)
        {
            if (e.Cell != null)
            {
                CheckBoxCell checkCell = e.Cell as CheckBoxCell;
                flag = false;
                CheckStateChangedAll(checkCell, false);
                flag = true;
            }
        }

        /// <summary>
        /// 初始化复制参数筛选菜单选项
        /// STORY60683需求上海-[中银国际]金融资产管理平台V4.5[高]20180814（产品复制创建产品参数复制综合需求）
        /// </summary>
        /// <param name="tab">tab</param>
        /// <param name="configName">configName</param>
        private void initFilterTable(Table tab, string configName)
        {
            string path = ClsConstant.FILE_CustomSettingPath + "\\" + configName + ".xml";
            if (File.Exists(path))
            {
                ////读取配置文件
                ClsXmlAdmin xmlAdmin = new ClsXmlAdmin(path);
                ////增加"CopyRowsSetting"的结点用来标记复制选项筛选情况
                XmlNode nodeColumns = xmlAdmin.getNode("CopyRowsSetting");
                if (nodeColumns != null)
                {
                    string[] rowsSetting = nodeColumns.InnerText.Split(',');
                    IEnumerable<Row> typeRows = tab.GetAllRows(tab.Rows, true, true);
                    Dictionary<string, string> rowSettingMap = new Dictionary<string, string>();
                    for (int i = 0; i < rowsSetting.Length; i++)
                    {
                        string dataCode = Convert.ToString(rowsSetting[i].Trim(new char[] { '[', ']' }).Split(':')[1]);
                        rowSettingMap.Add(dataCode, rowsSetting[i]);
                    }

                    flag = false;

                    if (typeRows != null && typeRows.Count() > 0)
                    {
                        List<Row> typeRowsTemp = typeRows.ToList();
                        foreach (Row row in typeRowsTemp)
                        {
                            if (row.Cells.Count != 0)
                            {
                                foreach (CheckBoxCell tmpCell in row.Cells)
                                {
                                    if (tmpCell.Tag != null)
                                    {
                                        CopyData copyData = tmpCell.Tag as CopyData;
                                        string rowSetting = rowSettingMap[copyData.C_DATA_CODE];
                                        bool visible = Convert.ToBoolean(rowSetting.Trim(new char[] { '[', ']' }).Split(':')[2]);
                                        if (!visible)
                                        {
                                            tmpCell.Checked = true;
                                        }
                                        else
                                        {
                                            tmpCell.Checked = false;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    tab.Invalidate(false);

                    flag = true;
                }

                xmlAdmin.Dispose();
                xmlAdmin = null;
            }
        }

        /// <summary>
        /// 右键菜单“复制参数筛选”点击下拉列表中选择状态改变事件
        /// STORY60683需求上海-[中银国际]金融资产管理平台V4.5[高]20180814（产品复制创建产品参数复制综合需求）
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void filterTable_CheckStateChanged(object sender, CheckStateChangeEventArgs e)
        {
            if (flag)
            {
                if (e.CurrentCell != null)
                {
                    CheckBoxCell checkCell = e.CurrentCell as CheckBoxCell;
                    CopyData copyPojo = checkCell.Tag as CopyData;
                    if (checkCell.Checked)
                    {
                        CheckBoxCell locateCell = locateCheckCell(this.tbMain, copyPojo.C_DATA_CODE);
                        if (locateCell != null)
                        {
                            if (locateCell.OwnRow.Cells.Count == 1)
                            {
                                Row rootRow = getRootRow(locateCell.OwnRow);
                                if (rootRow != null)
                                {
                                    if (rootRow.HasParent)
                                    {
                                        rootRow.ParentRow.SubRows.Remove(rootRow);
                                    }
                                    else
                                    {
                                        this.tbMain.Rows.Remove(rootRow);
                                    }
                                }
                            }
                            else
                            {
                                locateCell.OwnRow.Cells.Remove(locateCell);
                            }

                            this.tbMain.Refresh();
                        }
                    }
                    else
                    {
                        if (checkCell.OwnRow.Cells.Count == 1)
                        {
                            Row rootRow = getRootRow(checkCell.OwnRow);
                            Row copyRow = rootRow.Clone(true) as Row;
                            copyCellInfos(rootRow, copyRow);
                            Row previousRow = getPreviousUnCheckedRow(rootRow);
                            int rowIndex = 0;
                            CheckBoxCell locateCell = null;
                            if (previousRow != null)
                            {
                                string dataCode = (previousRow.Cells[0].Tag as CopyData).C_DATA_CODE;
                                locateCell = locateCheckCell(this.tbMain, dataCode);
                                rowIndex = locateCell.OwnRow.Index;
                                if (locateCell.OwnRow.HasParent)
                                {
                                    locateCell.OwnRow.ParentRow.SubRows.Insert(rowIndex + 1, copyRow);
                                }
                                else
                                {
                                    if (null == locateCheckCell(this.tbMain, (copyRow.Cells[0].Tag as CopyData).C_DATA_CODE))
                                    {
                                        this.tbMain.Rows.Insert(rowIndex + 1, copyRow);
                                    }
                                }
                            }
                            else
                            {
                                if (rootRow.HasParent)
                                {
                                    string dataCode = (rootRow.ParentRow.Cells[0].Tag as CopyData).C_DATA_CODE;
                                    locateCell = locateCheckCell(this.tbMain, dataCode);
                                    locateCell.OwnRow.SubRows.Insert(0, copyRow);
                                }
                                else
                                {
                                    if (null == locateCheckCell(this.tbMain, (copyRow.Cells[0].Tag as CopyData).C_DATA_CODE))
                                    {
                                        this.tbMain.Rows.Insert(0, copyRow);
                                    }
                                }
                            }
                        }
                        else
                        {
                            Row row = checkCell.OwnRow;
                            if (row.HasParent)
                            {
                                CheckBoxCell copyCell = checkCell.Clone() as CheckBoxCell;
                                copyCell.Tag = checkCell.Tag;
                                Row parentRow = row.ParentRow;
                                string dataCode = (row.ParentRow.Cells[0].Tag as CopyData).C_DATA_CODE;
                                CheckBoxCell locateCell = locateCheckCell(this.tbMain, dataCode);
                                if (locateCell != null)
                                {
                                    bool flagInsert = false;
                                    foreach (Row lrow in locateCell.OwnRow.SubRows)
                                    {
                                        if (lrow.HasChild)
                                        {
                                            continue;
                                        }

                                        if (lrow.Cells.Count != this.tbMain.Columns.Count)
                                        {
                                            lrow.Cells.Insert(lrow.Cells.Count, copyCell);
                                            flagInsert = true;
                                            break;
                                        }
                                    }

                                    if (!flagInsert)
                                    {
                                        Row chirldRow = row.Clone(false) as Row;
                                        chirldRow.Cells.Clear();
                                        chirldRow.Cells.Insert(0, copyCell);
                                        locateCell.OwnRow.SubRows.Insert(locateCell.OwnRow.SubRows.Count, chirldRow);
                                    }
                                }
                                else
                                {
                                    Row lparentRow = parentRow.Clone(false, false) as Row;
                                    lparentRow.Cells[0].Tag = parentRow.Cells[0].Tag;
                                    Row lRow = row.Clone() as Row;
                                    lRow.Cells.Clear();
                                    lRow.Cells.Insert(0, copyCell);
                                    lparentRow.SubRows.Insert(0, lRow);
                                    dataCode = (parentRow.ParentRow.Cells[0].Tag as CopyData).C_DATA_CODE;
                                    CheckBoxCell plocateCell = locateCheckCell(this.tbMain, dataCode);
                                    if (plocateCell != null)
                                    {
                                        Row previousRow = getPreviousUnCheckedRow(parentRow);
                                        if (previousRow != null)
                                        {
                                            dataCode = (previousRow.Cells[0].Tag as CopyData).C_DATA_CODE;
                                            locateCell = locateCheckCell(this.tbMain, dataCode);
                                            int rowIndex = locateCell.OwnRow.Index;
                                            if (locateCell.OwnRow.HasParent)
                                            {
                                                locateCell.OwnRow.ParentRow.SubRows.Insert(rowIndex + 1, lparentRow);
                                            }
                                            else
                                            {
                                                this.tbMain.Rows.Insert(rowIndex + 1, lparentRow);
                                            }
                                        }
                                        else
                                        {
                                            plocateCell.OwnRow.SubRows.Insert(0, lparentRow);
                                        }
                                    }
                                    else
                                    {
                                        if (parentRow.ParentRow.HasParent)
                                        {
                                            Row llparentRow = parentRow.ParentRow.Clone(false, false) as Row;
                                            llparentRow.Cells[0].Tag = parentRow.ParentRow.Cells[0].Tag;
                                            llparentRow.SubRows.Insert(0, lparentRow);
                                            dataCode = (parentRow.ParentRow.ParentRow.Cells[0].Tag as CopyData).C_DATA_CODE;
                                            CheckBoxCell pplocateCell = locateCheckCell(this.tbMain, dataCode);
                                            if (pplocateCell != null)
                                            {
                                                Row previousRow = getPreviousUnCheckedRow(parentRow.ParentRow);
                                                if (previousRow != null)
                                                {
                                                    dataCode = (previousRow.Cells[0].Tag as CopyData).C_DATA_CODE;
                                                    locateCell = locateCheckCell(this.tbMain, dataCode);
                                                    int rowIndex = locateCell.OwnRow.Index;
                                                    if (locateCell.OwnRow.HasParent)
                                                    {
                                                        locateCell.OwnRow.ParentRow.SubRows.Insert(rowIndex + 1, llparentRow);
                                                    }
                                                    else
                                                    {
                                                        this.tbMain.Rows.Insert(rowIndex + 1, llparentRow);
                                                    }
                                                }
                                                else
                                                {
                                                    pplocateCell.OwnRow.SubRows.Insert(0, llparentRow);
                                                }
                                            }
                                            else
                                            {
                                                Row lllparentRow = parentRow.ParentRow.ParentRow.Clone(false, false) as Row;
                                                lllparentRow.Cells[0].Tag = parentRow.ParentRow.ParentRow.Cells[0].Tag;
                                                lllparentRow.SubRows.Insert(0, llparentRow);
                                                Row previousRow = getPreviousUnCheckedRow(parentRow.ParentRow.ParentRow);
                                                if (previousRow != null)
                                                {
                                                    dataCode = (previousRow.Cells[0].Tag as CopyData).C_DATA_CODE;
                                                    locateCell = locateCheckCell(this.tbMain, dataCode);
                                                    int rowIndex = locateCell.OwnRow.Index;
                                                    if (locateCell.OwnRow.HasParent)
                                                    {
                                                        locateCell.OwnRow.ParentRow.SubRows.Insert(rowIndex + 1, lllparentRow);
                                                    }
                                                    else
                                                    {
                                                        this.tbMain.Rows.Insert(rowIndex + 1, lllparentRow);
                                                    }
                                                }
                                                else
                                                {
                                                    this.tbMain.Rows.Insert(0, lllparentRow);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        this.tbMain.Refresh();
                    }

                    this.WriteCopyRowsToConfig(this.filterTable, this.YssFormMenu.C_FUN_NAME);
                }
            }
        }

        /// <summary>
        /// STORY60683需求上海-[中银国际]金融资产管理平台V4.5[高]20180814（产品复制创建产品参数复制综合需求）
        /// </summary>
        /// <param name="row">row</param>
        /// <returns>或者没有被勾选的上一级</returns>
        private Row getParentUnCheckedRow(Row row)
        {
            if (row.HasParent)
            {
                if ((row.ParentRow.Cells[0] as CheckBoxCell).Checked)
                {
                    return getParentUnCheckedRow(row.ParentRow);
                }
                else
                {
                    return row.ParentRow;
                }
            }
            else
            {
                return row;
            }
        }

        ////private CheckBoxCell getPreviousUnCheckedCell(CheckBoxCell currentCell)
        ////{
        ////    foreach (Row currentRow in currentCell.OwnRow.ParentRow.SubRows)
        ////    {
        ////        foreach (CheckBoxCell checkedCell in currentRow.Cells)
        ////        {

        ////        }
        ////    }
        ////}

        /// <summary>
        /// STORY60683需求上海-[中银国际]金融资产管理平台V4.5[高]20180814（产品复制创建产品参数复制综合需求）
        /// 因row在clone的时候cell属性中的tag会丢失，这里通过复制找回来
        /// </summary>
        /// <param name="row">row</param>
        /// <param name="copyRow">copyRow</param>
        private void copyCellInfos(Row row, Row copyRow)
        {
            for (int i = 0; i < row.Cells.Count; i++)
            {
                CheckBoxCell tmpCell = row.Cells[i] as CheckBoxCell;
                BasePojo pojo = tmpCell.Tag as BasePojo;
                copyRow.Cells[i].Tag = pojo;
            }

            if (row.SubRows != null && row.SubRows.Count > 0)
            {
                for (int i = 0; i < row.SubRows.Count; i++)
                {
                    copyCellInfos(row.SubRows[i], copyRow.SubRows[i]);
                }
            }
        }

        /// <summary>
        /// 获取当前行的上一个(同级行)未被勾选的行数据
        /// STORY60683需求上海-[中银国际]金融资产管理平台V4.5[高]20180814（产品复制创建产品参数复制综合需求）
        /// </summary>
        /// <param name="row">row</param>
        /// <returns>当前行的上一个(同级行)未被勾选的行数据</returns>
        private Row getPreviousUnCheckedRow(Row row)
        {
            if (row.PreviousSameLevelRow != null && (row.PreviousSameLevelRow.Cells[0] as CheckBoxCell).Checked)
            {
                return getPreviousUnCheckedRow(row.PreviousSameLevelRow);
            }
            else if (row.PreviousSameLevelRow != null)
            {
                return row.PreviousSameLevelRow;
            }
            else
            {
                return null;
            }
        }

        /// <summary>
        /// STORY60683需求上海-[中银国际]金融资产管理平台V4.5[高]20180814（产品复制创建产品参数复制综合需求）
        /// </summary>
        /// <param name="row">row</param>
        /// <returns>Row</returns>
        private Row getRootRow(Row row)
        {
            if (row.HasParent)
            {
                if (row.ParentRow.SubRows.Count == 1)
                {
                    return getRootRow(row.ParentRow);
                }
                else
                {
                    return row;
                }
            }
            else
            {
                return row;
            }
        }

        /// <summary>
        /// 定位tbMain需要隐藏的单元格
        /// STORY60683需求上海-[中银国际]金融资产管理平台V4.5[高]20180814（产品复制创建产品参数复制综合需求）
        /// </summary>
        /// <param name="tab">tab</param>
        /// <param name="dataCode">dataCode</param>
        /// <returns>需隐藏的单元格</returns>
        public CheckBoxCell locateCheckCell(Table tab, string dataCode)
        {
            CheckBoxCell checkCell = null;
            bool flag = false;
            IEnumerable<Row> typeRows = tab.GetAllRows(tab.Rows, true, true);
            if (typeRows != null && typeRows.Count() > 0)
            {
                List<Row> typeRowsTemp = typeRows.ToList();
                foreach (Row row in typeRowsTemp)
                {
                    foreach (CheckBoxCell tmpCell in row.Cells)
                    {
                        BasePojo pojo = tmpCell.Tag as BasePojo;
                        string nodeCode = ReflectBase.getAttrValue("C_DATA_CODE", pojo) as string;
                        if (dataCode.Equals(nodeCode))
                        {
                            checkCell = tmpCell;
                            flag = true;
                            break;
                        }
                    }

                    if (flag) 
                    { 
                        break; 
                    }
                }
            }

            return checkCell;
        }

        /// <summary>
        /// 将TableMain的列信息写入配置文件
        /// </summary>
        /// <param name="tab">目标Table</param>
        /// <param name="configName">配置文件的文件名。注意：不带文件扩展名</param>
        protected void WriteCopyRowsToConfig(Table tab, string configName)
        {
            if (!Directory.Exists(ClsConstant.FILE_CustomSettingPath))
            {
                Directory.CreateDirectory(ClsConstant.FILE_CustomSettingPath);
            }

            string rowsString = this.GetCopyRowsString(tab);
            ClsXmlAdmin xmlAdmin = new ClsXmlAdmin(ClsConstant.FILE_CustomSettingPath + "\\" + configName + ".xml");
            ////增加"CopyRowsSetting"的结点用来标记复制选项筛选情况
            XmlNode nodeColumns = xmlAdmin.getNode("CopyRowsSetting");
            if (nodeColumns == null)
            {
                //增加"CopyRowsSetting"的结点用来标记复制选项筛选情况
                xmlAdmin.addNode("", "CopyRowsSetting", rowsString);
            }
            else
            {
                xmlAdmin.ModfiyNodeText(nodeColumns, rowsString);
            }

            xmlAdmin.Dispose();
            xmlAdmin = null;
        }

        /// <summary>
        /// 从配置文件读取filterTable的Row配置信息，并根据配置信息调整Table行选项
        /// </summary>
        /// <param name="tab">目标Table</param>
        /// <param name="configName">配置文件的文件名。注意：不带文件扩展名</param>
        protected void ReadCopyRowsFromConfig(Table tab, string configName)
        {
            string path = ClsConstant.FILE_CustomSettingPath + "\\" + configName + ".xml";
            if (File.Exists(path))
            {
                ////读取配置文件
                ClsXmlAdmin xmlAdmin = new ClsXmlAdmin(path);
                ////增加"CopyRowsSetting"的结点用来标记复制选项筛选情况
                XmlNode nodeColumns = xmlAdmin.getNode("CopyRowsSetting");
                if (nodeColumns != null)
                {
                    string[] rowsSetting = nodeColumns.InnerText.Split(',');
                    Dictionary<string, string> rowSettingMap = new Dictionary<string, string>();
                    for (int i = 0; i < rowsSetting.Length; i++)
                    {
                        string dataCode = Convert.ToString(rowsSetting[i].Trim(new char[] { '[', ']' }).Split(':')[1]);
                        rowSettingMap.Add(dataCode, rowsSetting[i]);
                    }

                    IEnumerable<Row> typeRows = tab.GetAllRows(tab.Rows, true, true)
                        .Where(r => r.ParentRow != null && r.SubRows.Count == 0);
                    if (typeRows != null && typeRows.Count() > 0)
                    {
                        List<Row> typeRowsTemp = typeRows.ToList();
                        foreach (Row row in typeRowsTemp)
                        {
                            if (row.Cells.Count != 0)
                            {
                                int i = 0;
                                int count = row.Cells.Count;
                                List<CheckBoxCell> removedCells = new List<CheckBoxCell>();
                                foreach (CheckBoxCell tmpCell in row.Cells)
                                {
                                    if (tmpCell.Tag != null)
                                    {
                                        CopyData copyData = tmpCell.Tag as CopyData;
                                        bool visible = true;
                                        if (rowSettingMap.ContainsKey(copyData.C_DATA_CODE))
                                        {
                                            string rowSetting = rowSettingMap[copyData.C_DATA_CODE];
                                            visible = Convert.ToBoolean(rowSetting.Trim(new char[] { '[', ']' }).Split(':')[2]);
                                        }

                                        if (!visible)
                                        {
                                            removedCells.Add(tmpCell);
                                            i++;
                                        }
                                    }
                                }

                                if (i == count)
                                {
                                    removeRowData(row);
                                }
                                else
                                {
                                    foreach (CheckBoxCell tmpCell in removedCells)
                                    {
                                        row.Cells.Remove(tmpCell);
                                    }
                                }
                            }
                        }
                    }

                    tab.Invalidate(false);
                }

                xmlAdmin.Dispose();
                xmlAdmin = null;
            }
        }

        /// <summary>
        /// STORY60683需求上海-[中银国际]金融资产管理平台V4.5[高]20180814（产品复制创建产品参数复制综合需求）
        /// </summary>
        /// <param name="row">row</param>
        private void removeRowData(Row row)
        {
            if (row.HasParent)
            {
                if (row.ParentRow.SubRows.Count == 1)
                {
                    removeRowData(row.ParentRow);
                }
                else
                {
                    row.ParentRow.SubRows.Remove(row);
                }
            }
            else
            {
                this.tbMain.Rows.Remove(row);
            }
        }

        /// <summary>
        /// 拼接行选项可见性字符串
        /// STORY60683需求上海-[中银国际]金融资产管理平台V4.5[高]20180814（产品复制创建产品参数复制综合需求）
        /// </summary>
        /// <param name="tab">tab</param>
        /// <returns>返回字符串</returns>
        private string GetCopyRowsString(Table tab)
        {
            string copyRowsString = "";
            IEnumerable<Row> typeRows = tab.GetAllRows(filterTable.Rows, true, true);
            if (typeRows != null && typeRows.Count() > 0)
            {
                List<Row> typeRowsTemp = typeRows.ToList();
                foreach (Row row in typeRowsTemp)
                {
                    if (row.Cells.Count != 0)
                    {
                        foreach (CheckBoxCell tmpCell in row.Cells)
                        {
                            if (tmpCell.Tag != null)
                            {
                                CopyData copyData = tmpCell.Tag as CopyData;
                                copyRowsString += "[" + copyData.C_DATA_NAME + ":" + copyData.C_DATA_CODE + ":" +
                                    (tmpCell.Checked ? "false" : "true") + "],";
                            }
                        }
                    }
                }
            }

            if (!string.Empty.Equals(copyRowsString))
            {
                copyRowsString = copyRowsString.Substring(0, copyRowsString.Length - 1);
            }

            return copyRowsString;
        }

        #endregion

        #region 方案设置 STORY #83428 华夏基金—首选估值方案关联组合  baoqiaolin

        /// <summary>
        /// 增加 方案保存\方案删除 按钮
        /// </summary>
        protected void AddPlanButton()
        {
            if (ClsContext.HtUserOperRight["dataCopy"] != null)
            {
                List<string> rightList = (List<string>)ClsContext.HtUserOperRight["dataCopy"];
                ClsButtonInfo btnPlanSave = new ClsButtonInfo();
                btnPlanSave.Name = "btnPlanSave";
                btnPlanSave.Text = "方案保存";
                btnPlanSave.Tooltip = "方案保存";
                btnPlanSave.Image = FAST.Resource.Resource.btnSave_L;
                btnPlanSave.ClickEvent += new System.EventHandler(this.btnPlanSave_Click);
                this.btnBarOper.addButton(btnPlanSave, 7);
                this.btnBarOper.setButtonEnabled("btnPlanSave", rightList.Contains("SAVE_PLAN"));
                this.chkFirstPlan.Enabled = rightList.Contains("SAVE_PLAN");

                ClsButtonInfo btnPlanDel = new ClsButtonInfo();
                btnPlanDel.Name = "btnPlanDel";
                btnPlanDel.Text = "方案删除";
                btnPlanDel.Tooltip = "方案删除";
                btnPlanDel.Image = FAST.Resource.Resource.btnDel_L;
                btnPlanDel.ClickEvent += new System.EventHandler(this.btnPlanDel_Click);
                this.btnBarOper.addButton(btnPlanDel, 8);
                this.btnBarOper.setButtonEnabled("btnPlanDel", rightList.Contains("DEL_PLAN"));

                this.cboQxlb.Visible = rightList.Contains("autoRightSet");
            }
        }

        /// <summary>
        /// 方案下拉框点击事件
        /// </summary>
        /// <param name="sender">事件触发对象</param>
        /// <param name="e">事件参数</param>
        private void cboPlanCode_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            e.Cancel = true;
            List<BasePojo> formulaList = this.getAllPlanCode();
            this.fillPlanCodeToCombox(formulaList, this.cboPlanCode);
        }

        /// <summary>
        ///  向控件中填充数据
        /// </summary>
        /// <param name="dataList">dataList</param>
        /// <param name="combox">combox</param>
        private void fillPlanCodeToCombox(List<BasePojo> dataList, YssSelCombox combox)
        {
            try
            {
                combox.Items.Clear(); ////清空控件里面的所有的数据
                List<Yss.KRichEx.AutoFilter.Model.KTableEntity> lists = new List<Yss.KRichEx.AutoFilter.Model.KTableEntity>();
                Yss.KRichEx.AutoFilter.Model.KTableEntity entity = null;
                ////方案代码
                combox.DisplayValue = "C_PLAN_CODE";
                combox.DisplayName = "C_SELECT";
                combox.Parameter = "C_DOWN";

                if (null != dataList && dataList.Count > 0)
                {
                    for (int i = 0; i < dataList.Count; i++)
                    {
                        ////筛选出重复的方案代码
                        BusinessPlan businessPlan = (BusinessPlan)dataList[i];
                        string userText = businessPlan.C_USER_CODES;
                        string[] users = null;
                        if (userText != "" && userText.Contains("|"))
                        {
                            users = userText.Split('|');
                        }

                        if ((businessPlan.C_SIGN == "1" && (businessPlan.C_SHARE_LEVEL == "共享" || businessPlan.C_SHARE_LEVEL.Trim() == "")) || (businessPlan.C_SIGN == "1" && users != null && users.Length > 0 && users.Contains(ClsContext.currentUser.C_USER_CODE)))
                        {
                            BasePojo pojo = dataList[i];
                            BusinessPlan plan = (BusinessPlan)pojo;
                            if (plan.C_SHARE_LEVEL == "")
                            {
                                plan.C_DOWN = plan.C_PLAN_CODE + "_" + plan.C_PLAN_NAME;
                                plan.C_SELECT = plan.C_PLAN_NAME;
                            }
                            else
                            {
                                plan.C_DOWN = plan.C_PLAN_CODE + "_" + plan.C_PLAN_NAME + "_" + plan.C_SHARE_LEVEL;
                                plan.C_SELECT = plan.C_PLAN_NAME + "_" + plan.C_SHARE_LEVEL;
                            }

                            entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(plan);
                            ////循环list往控件里面塞数据
                            combox.Items.Add(entity);
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 方案值改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboPlanCode_SelectedValueChanged(object sender, EventArgs e)
        {
            try
            {
                chkFirstPlan.Enabled = true;
                if (this.cboPlanCode.SelectedItem != null)
                {
                    if (planDesc.Equals(((this.cboPlanCode.SelectedItem.DataEntity) as BusinessPlan).C_DESC.Trim()))
                    {
                        this.chkFirstPlan.Checked = true;
                    }
                    else
                    {
                        this.chkFirstPlan.Checked = false;
                    }
                }
                else
                {
                    this.chkFirstPlan.Checked = false;
                }

                if (itemsInPlan == null)
                {
                    itemsInPlan = new List<string>();
                }
                else
                {
                    itemsInPlan.Clear();
                }

                List<BasePojo> dataList = this.getAllPlanCode();
                List<string> itemList = new List<string>();
                foreach (BasePojo basePojo in dataList)
                {
                    if (((BusinessPlan)basePojo).C_PLAN_CODE.Equals(this.cboPlanCode.Value))
                    {
                        itemList.Add(((BusinessPlan)basePojo).C_ITEM_CODE);
                    }

                }

                ////清空List界面所勾选的核算项
                setCheckedAllLeafCell(tbMain.Rows, false);

                setCheckedLeafCell(tbMain.Rows, itemList);

            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }
        }
        
        /// <summary>
        /// 查询方案代码
        /// </summary>
        /// <returns>list</returns>
        public List<BasePojo> getAllPlanCode()
        {
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            List<BasePojo> dataListPlan = new List<BasePojo>();
            try
            {
                if (paraDict.ContainsKey("dataClass"))
                {
                    paraDict.Remove("dataClass");
                }

                paraDict.Add("dataClass", "BusinessPlan");
                paraDict.Add("BUSINESS_TYPE", ClsBizCons.COPY_PLAN);
                List<BasePojo> dataListTemp = businessPlanService.queryPlanPojo(paraDict).DataList;
                for (int i = 0; i < dataListTemp.Count; i++)
                {
                    BasePojo basePojo = dataListTemp[i];
                    BusinessPlan businessPlan = new BusinessPlan();
                    System.Reflection.PropertyInfo[] properties = typeof(BusinessPlan).GetProperties(System.Reflection.BindingFlags.Instance | System.Reflection.BindingFlags.Public);

                    foreach (System.Reflection.PropertyInfo item in properties)
                    {
                        if (null != basePojo.GetValue(item.Name))
                        {
                            businessPlan.SetValue(item.Name, basePojo.GetValue(item.Name));
                        }

                    }

                    dataListPlan.Add(businessPlan);
                }
            }
            catch (Exception ex)
            {
                ClsBaseException.DiscardException(ex);
            }

            return dataListPlan;
        }

        /// <summary>
        /// 估值方案保存事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnPlanSave_Click(object sender, EventArgs e)
        {
            List<DvaItem> itemList = new List<DvaItem>();
            ////将界面状态改为新增
            this.status = ClsEnums.StatusSetting.YssAdd;

            try
            {
                itemList = this.getCheckedItem();
                if (itemList.Count == 0)
                {
                    YssMessageBox.ShowQuestion("请选择目标数据！", "提示", MessageBoxButtons.OK);
                    return;
                }

                Frm_BUSINESS_PLAN frmBusiness = new Frm_BUSINESS_PLAN();
                frmBusiness.yssShowForm(this);
                frmBusiness.setPlanCode();
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        ///  估值方案删除click事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnPlanDel_Click(object sender, EventArgs e)
        {
            string planCode = this.cboPlanCode.Value;
            string reInfo = "";

            try
            {
                if (null == this.cboPlanCode.Value)
                {
                    YssMessageBox.ShowQuestion("请选择要删除的方案！", "提示", MessageBoxButtons.OK);
                    return;
                }

                ////BUG #120908 资产数据清算和资产数据核算中，方案删除时，没有弹框提醒。 guoguangyi 2015-10-30
                if (YssMessageBox.ShowQuestion("确定删除方案？", "删除提醒") != System.Windows.Forms.DialogResult.Yes)
                {
                    return;
                }

                reInfo = businessPlanService.deleteByPlanCode(ClsBizCons.COPY_PLAN, planCode);
                if (null != reInfo && reInfo.ToLower().Equals("success"))
                {
                    YssMessageBox.ShowQuestion("方案删除成功！", "提示", MessageBoxButtons.OK);
                }
                else
                {
                    YssMessageBox.ShowQuestion("方案删除失败！", "提示", MessageBoxButtons.OK);
                    return;
                }

                this.cboPlanCode.Value = "";
                tbMain.Refresh();
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// getCheckedItem
        /// </summary>
        /// <returns>返回处理结果</returns>
        public List<DvaItem> getCheckedItem()
        {
            List<DvaItem> itemList = new List<DvaItem>();
            DvaItem item = null;

            List<BasePojo> list = new List<BasePojo>();
            ctll.getCheckedLeafCell(tbMain.Rows, list);

            if (list.Count > 0)
            {
                foreach (BasePojo pojo in list)
                {
                    CopyData copyData = pojo as CopyData;
                    item = new DvaItem();
                    item.C_DVA_ITEM_CODE = copyData.C_DATA_CODE;
                    item.C_DVA_ITEM_CODE_P = copyData.C_DATA_CODE_P;
                    item.C_DVA_ITEM_NAME = copyData.C_DATA_NAME;
                    itemList.Add(item);
                }
            }

            return itemList;
        }

        /// <summary>
        /// 首选方案改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void chkFirstPlan_CheckedChanged(object sender, EventArgs e)
        {
            if (this.cboPlanCode.SelectedItem != null)
            {
                List<string> rightList = (List<string>)ClsContext.HtUserOperRight["dataCopy"];
                if (rightList.Contains("SAVE_PLAN"))
                {
                    if (chkFirstPlan.Checked)
                    {
                        BusinessPlan selectedPlan = getBusinessPlan();
                        if (selectedPlan.C_DESC.Equals(planDesc))
                        {
                            ////当为勾选时，本身就是首选则不更新方案
                            setCboPlanCode(selectedPlan);
                        }
                        else
                        {
                            ////制空所有方案
                            foreach (ControlEntity ce in this.cboPlanCode.Items)
                            {
                                BusinessPlan plan = ce.DataEntity as BusinessPlan;
                                if (plan.C_DESC.Equals(planDesc))
                                {
                                    plan.C_DESC = "";
                                    businessPlanService.updateById(plan);
                                }
                            }

                            ////当为勾选时，不是首选，则更新方案
                            selectedPlan.C_DESC = planDesc;
                            businessPlanService.updateById(selectedPlan);
                            setCboPlanCode(selectedPlan);
                        }
                    }
                    else
                    {
                        BusinessPlan selectedPlan = getBusinessPlan();
                        ////只有当选择是首选方案时，才需要去除勾选更新至后台，防止违反唯一约束
                        if (selectedPlan.C_DESC.Equals(planDesc))
                        {
                            ////制空所有方案
                            foreach (ControlEntity ce in this.cboPlanCode.Items)
                            {
                                BusinessPlan plan = ce.DataEntity as BusinessPlan;
                                if (plan.C_DESC.Equals(planDesc))
                                {
                                    plan.C_DESC = "";
                                    businessPlanService.updateById(plan);
                                }
                            }

                            ////当为去除勾选时，制空
                            selectedPlan.C_DESC = "";
                            setCboPlanCode(selectedPlan);
                        }
                    }
                }
            }
        }

        /// <summary>
        /// 获取实体(跨界面获取实体，清算核算联动，且互不影响)
        /// add by huyingzhao STORY #62315 需求一般：【日常做账】功能涉及【估值方案-设置为首选方案】
        /// 2019年10月31日12:01:36
        /// </summary>
        /// <returns>方案实体</returns>
        public BusinessPlan getBusinessPlan()
        {
            if (this.cboPlanCode.Text != null && this.cboPlanCode.SelectedItem != null)
            {
                return this.cboPlanCode.SelectedItem.DataEntity as BusinessPlan;
            }
            else
            {
                BusinessPlan plan = new BusinessPlan();
                plan.C_PLAN_CODE = "";
                plan.C_PLAN_NAME = "";
                plan.C_SHARE_LEVEL = "";
                plan.C_USER_CODES = "";
                return plan;
            }
        }

        /// <summary>
        /// 实体赋值给对话框(跨界面赋值，清算核算联动，且互不影响)
        /// </summary>
        /// <param name="plan">plan</param>
        public void setCboPlanCode(BusinessPlan plan)
        {
            ////plan.C_SHARE_LEVEL可为空，所以需要这样处理以合理显示
            if (plan.C_PLAN_CODE != "" && plan.C_PLAN_NAME != "" && plan.C_SHARE_LEVEL == "")
            {
                this.cboPlanCode.Text = plan.C_PLAN_CODE + "_" + plan.C_PLAN_NAME;
            }
            else if (plan.C_PLAN_CODE != "" && plan.C_PLAN_NAME != "" && plan.C_SHARE_LEVEL != "")
            {
                this.cboPlanCode.Text = plan.C_PLAN_CODE + "_" + plan.C_PLAN_NAME + "_" + plan.C_SHARE_LEVEL;
            }
            else
            {
                this.cboPlanCode.Text = "";
            }
        }

        /// <summary>
        /// 勾选框是否可用
        /// </summary>
        /// <param name="isEidt">isEidt</param>
        public void setCboFirstPlanEidt(bool isEidt)
        {
            chkFirstPlan.Enabled = isEidt;
        }

        /// <summary>
        /// 方案设置为可读改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void chkPlanReadOnly_CheckedChanged(object sender, EventArgs e)
        {
            if (this.cboPlanCode.SelectedItem != null && this.chkPlanReadOnly.Checked == true)
            {
                this.changeRowsReadOnly(true);
            }
            else
            {
                this.changeRowsReadOnly(false);
            }
        }

        /// <summary>
        /// 改变勾选框的可读状态
        /// </summary>
        /// <param name="status">status</param>
        private void changeRowsReadOnly(bool status)
        {
            foreach (Row row in this.tbMain.Rows)
            {
                row.CheckedReadOnly = status;
                foreach (Row subRow in row.SubRows)
                {
                    subRow.CheckedReadOnly = status;

                }
            }

            this.tbMain.Refresh();
        }

        /// <summary>
        /// 获取估值方案下拉框选中的方案名称和代码
        /// add by Yuntao Lau 2015.11.03 STORY #26731
        /// </summary>
        /// <returns>方案名称和代码</returns>
        public string[] getPlanCode()
        {
            string[] planCode = new string[2];
            if (this.cboPlanCode.Value != null && this.cboPlanCode.SelectedItem != null)
            {
                planCode[0] = this.cboPlanCode.SelectedItem.DisplayValue;
                planCode[1] = this.cboPlanCode.SelectedItem.DisplayName;
            }

            return planCode;
        }

        /// <summary>
        /// 所有节点全部勾选或者全部取消勾选
        /// </summary>
        /// <param name="tbRows">tbRows</param>
        /// <param name="isAllChecked">是否全部勾选，true 全部勾选，flase 全部取消勾选</param>
        public void setCheckedAllLeafCell(Yss.KTable.Collections.RowCollection tbRows, bool isAllChecked)
        {
            this.tbMain.CheckStateChanged -= this.tbMain_CheckStateChange;

            foreach (Yss.KTable.Models.Row row in tbRows)
            {
                this.SetRowCellsChecked(row, isAllChecked); ////只用设置每个根节点的选中状态，下面的节点会自动和根节点保持一致
            }

            this.tbMain.CheckStateChanged += this.tbMain_CheckStateChange;
        }

        /// <summary>
        /// 勾行的时候支持把行里面所有的单元格都勾上
        /// </summary>
        /// <param name="poRow">poRow</param>
        /// <param name="plChecked">plChecked</param>
        private void SetRowCellsChecked(Row poRow, bool plChecked)
        {
            if (poRow == null || poRow.Cells.Count == 0)
            {
                return;
            }

            IEnumerable<Cell> loChkCells = poRow.Cells.Where(c => c is CheckBoxCell);
            if (loChkCells != null && loChkCells.Count() > 0)
            {
                foreach (Cell loCell in loChkCells)
                {
                    (loCell as CheckBoxCell).Checked = plChecked;
                }
            }

            if (poRow.SubRows.Count > 0)
            {
                foreach (Row loRow in poRow.SubRows)
                {
                    this.SetRowCellsChecked(loRow, plChecked);
                }
            }

        }

        /// <summary>
        ///  根据方案设置选项的选中状态
        /// </summary>
        /// <param name="tbRows">Rows</param>
        /// <param name="list">list</param>
        public void setCheckedLeafCell(Yss.KTable.Collections.RowCollection tbRows, List<string> list)
        {
            foreach (Yss.KTable.Models.Row row in tbRows)
            {
                if (row.SubRows != null && row.SubRows.Count > 0)
                {
                    setCheckedLeafCell(row.SubRows, list);
                }
                else
                {
                    foreach (Yss.KTable.Models.CheckBoxCell tmpCell in row.Cells)
                    {
                        if (tmpCell.Tag is CopyData && list.Contains((tmpCell.Tag as CopyData).C_DATA_CODE))
                        {
                            CopyData pojo = tmpCell.Tag as CopyData;
                            tmpCell.Checked = true;
                        }
                    }
                }
            }
        }

        #endregion 方案设置 STORY #83428 华夏基金—首选估值方案关联组合  baoqiaolin

        /// <summary>
        /// 显示权限列表
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboQxlb_CheckedChanged(object sender, EventArgs e)
        {
            tbMain.Clear();
            this.getMainListDataMVC(new BasePojo(), true);
        }

        /// <summary>
        /// 全选按钮改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        public void cboIsAllCheck_CheckedChanged(object sender, EventArgs e)
        {
            setCheckedAllLeafCell(tbMain.Rows, cboIsAllCheck.Checked);
        }
    }
}
