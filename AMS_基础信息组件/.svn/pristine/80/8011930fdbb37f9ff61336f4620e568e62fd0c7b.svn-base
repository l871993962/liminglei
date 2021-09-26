using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
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
using System.Collections;
using System.Collections.Generic;


using Yss.KRichEx.AutoFilter.Model;
using Yss.KTable.Models;
using FAST.Common.Service.DataService;
using System.Text;
using YssProductInfo.Support.Ab.Port.Service;
using YssProductInfo.Support.Ab.PortGroup.Service;
using YssInformation.Support.Sys.PortBusinessRange.Service;
using YssProductInfo.Support.Ab.PortGroup.Pojo;
using YssInformation.Sys.PortBusinessRange.Form;
using YssInformation.Support.Sys.PortBusinessRange.Pojo;
using System.Windows.Forms;
using Yss.KRichEx.AutoFilter.Events;
using FAST.Common.Service.Dict.Pojo;
using FAST.Core.CRUD.Form;

namespace YssInformation.Sys.PortBusinessRange.Form
{
    /// <summary>
    /// 组合关联方案的设置窗体
    /// 框架迁移:ll 2012-11-20
    /// </summary>
    public partial class Frm_PortBusinessRange_S : FrmTabItemSet
    {
        /// <summary>
        /// 业务关联组合接口
        /// </summary>
        private IPortBusinessRangeService portBusinessRangeService = null;

        /// <summary>
        /// 组合接口
        /// </summary>
        private IPortService portService = null;

        /// <summary>
        /// 关联类型
        /// </summary>
        private string businessType = null;

        /// <summary>
        /// 解析类
        /// </summary>
        private TableListLoader tableListLoader = null;

        /// <summary>
        /// 保存数据时的参数
        /// </summary>
        private Dictionary<string, string> paramSaveDict = null;

        /// <summary>
        /// 当前资产结构的类型。
        /// </summary>
        private string _assetType = string.Empty;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_PortBusinessRange_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
            tableListLoader = new TableListLoader();
            //// 初始化table 列头
            initTabHead();
            this.tabControl.TabPages.Remove(this.tabGroupPage);
        }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="businessType">类型</param>
        public Frm_PortBusinessRange_S(string businessType)
        {
            this.businessType = businessType;
            this.bUseMVCService = true;
            InitializeComponent();
            tableListLoader = new TableListLoader();
            this.tabControl.TabPages.Remove(this.tabGroupPage);
        }

        /// <summary>
        /// 初始化控件默认值   
        /// </summary>
        public override void yssInitCtlAttr()
        {
            try
            {
                if (frmBaseViewList != null)
                {
                    Row row = ((Frm_PortBusinessRange_L)frmBaseViewList).tbLeftMain.SelectedRow;
                    if (row != null)
                    {
                        string businessType = (string)row.Tag;
                        if (string.IsNullOrEmpty(businessType))
                        {
                            this.selBusinessType.Value = businessType;
                        }
                    }
                }
            }
            catch (Exception ye)
            {
                ClsBaseException.DiscardException(ye);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500040", _formFun, status));
            }
        }

        /// <summary>
        /// 向后台获取数据，刷新数据
        /// </summary>
        /// <param name="queryType">queryType</param>
        private void getData(string queryType)
        {
            QueryRes queryRes = null;
            Dictionary<string, string> paraDict = null;
            Dictionary<string, string> paraGroupDict = null;
            ArrayList showColumnList = null;
            try
            {
                getPortInstanceService();
                paraDict = new Dictionary<string, string>();
                paraGroupDict = new Dictionary<string, string>();
                ////根据用户和岗位获取用户当前所拥有的组合
                string strPort = "";
                string curUserCode = ClsContext.currentUser.C_USER_CODE;
                string curPostCodes = ClsContext.currentUserPostCodes;
                IRightManageDataService rightSVC = DataServiceFactory.createService<IRightManageDataService>();
                List<string> dataRightList = ClsContext.DataRightList;

                if (dataRightList == null)
                {
                    dataRightList = new List<string>();
                }

                foreach (string portcode in dataRightList)
                {
                    strPort += portcode + ",";
                }

                paraDict.Add("dataClass", "Port");
                if ("new".Equals(queryType) || "edit".Equals(queryType) || "copy".Equals(queryType))
                {
                    if (null == this.selBusinessType.Value)
                    {
                        return;
                    }

                    paraDict.Add("C_BUSINESS_CODE", this.selBusinessType.Value);
                    paraDict.Add("C_PORT_CODE", strPort);
                    queryRes = this.portService.getBusinessRangePortAdd(paraDict);
                    this.AddTableContextMenu(this.tabPort);
                }
                else if ("brow".Equals(queryType))
                {
                    paraDict.Add("C_PORT_CODE", ((PortBusinessRangePojo)yssGetBaseSelTypeItemMVC()).C_PORT_CODE);
                    queryRes = this.portService.getBusinessRangePortBrow(paraDict);
                }
                
                ////要显示的列
                showColumnList = new ArrayList();
                showColumnList.Add("C_PORT_CODE");
                showColumnList.Add("C_PORT_NAME");
                this.tableListLoader.setTreeViewTable(this.tabPort, queryRes, true, true, ClsEnums.KTableDataShowMode.TreeMode, showColumnList);
            }
            catch (Exception ex)
            {
                ClsBaseException.DiscardException(ex);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("004", _formFun, status));
            }
        }

        /// <summary>
        /// 增加Table用户菜单右键
        /// </summary>
        /// <param name="tabSource">Table</param>
        private void AddTableContextMenu(Table tabSource)
        {
            if (tabSource.ContextMenuStrip.Items.ContainsKey("AssetTreeTSMI"))
            {
                tabSource.ContextMenuStrip.Items.RemoveByKey("AssetTreeTSMI");
            }

            if (tabSource.DefaultToolStripItems == Yss.KTable.Enums.SysToolStripItems.GardingMenu)
            {
                tabSource.DefaultToolStripItems = Yss.KTable.Enums.SysToolStripItems.GardingMenu | Yss.KTable.Enums.SysToolStripItems.Custom;
            }

            PCPortProduct portProduct = AreaAConfigInfo.SuspensionAreaA.GetPortControl(AreaType.Port) as PCPortProduct;
            if (portProduct != null)
            {
                Dictionary<string, object> loCustomerAssetInfo = new Dictionary<string, object>();
                foreach (var dict in portProduct.GetCustomerAssetInfo())
                {
                    loCustomerAssetInfo.Add(dict.Key, dict.Value);
                }
                
                ////添加产品树的数据结构及子项
                ToolStripMenuItem loTSMIAssetTreeItem = portProduct.GetAssetTreeMenuItem(loCustomerAssetInfo, this.TSMIAssetTreeItem_Click);
                if (loTSMIAssetTreeItem != null)
                {
                    loTSMIAssetTreeItem.Text = "数据结构";
                    loTSMIAssetTreeItem.Name = "AssetTreeTSMI";
                    this._assetType = "ASS";

                    if (loTSMIAssetTreeItem.DropDownItems.Count > 0)
                    {
                        for (int liIndex = 0; liIndex < loTSMIAssetTreeItem.DropDownItems.Count; liIndex++)
                        {
                            ToolStripMenuItem loItem = loTSMIAssetTreeItem.DropDownItems[liIndex] as ToolStripMenuItem;
                            if (loItem != null)
                            {
                                loItem.Checked = loItem.Name == this._assetType;
                            }
                        }
                    }

                    tabSource.AddContextMenuStripItem(loTSMIAssetTreeItem);
                }
            }
        }

        /// <summary>
        /// 切换产品树形结构
        /// </summary>
        /// <param name="sender">菜单</param>
        /// <param name="e">参数</param>
        private void TSMIAssetTreeItem_Click(object sender, EventArgs e)
        {
            ToolStripMenuItem loClickedItem = sender as ToolStripMenuItem;
            string assetType = loClickedItem.Name;
            if (this._assetType != assetType)
            {
                this._assetType = assetType;
                if (loClickedItem != null && loClickedItem.OwnerItem != null)
                {
                    ToolStripItemCollection loItems = (loClickedItem.OwnerItem as ToolStripDropDownItem).DropDownItems;
                    foreach (ToolStripMenuItem loItem in loItems)
                    {
                        if (loItem != null)
                        {
                            loItem.Checked = loItem == loClickedItem;
                        }
                    }
                }

                FAST.Common.Service.DataService.IPortDataService portDataService = ServiceFactory.createService<FAST.Common.Service.DataService.IPortDataService>();
                List<BasePojo> prots = portDataService.doPortFilter("false", "", "", this._assetType.Equals("ASS") ? "" : this._assetType);
                string strPort = "";
                foreach (BasePojo pojo in prots)
                {
                    Port port = pojo as Port;
                    string portCode = port.C_PORT_CODE;
                    if (portCode == this._assetType && "[root]" == port.C_PORT_CODE_P)
                    {
                        continue;
                    }

                    strPort += portCode + ",";
                }

                Dictionary<string, string> paraDict = new Dictionary<string, string>();
                paraDict.Add("dataClass", "Port");
                paraDict.Add("C_PORT_CODE", strPort);
                paraDict.Add("C_BUSINESS_CODE", this.selBusinessType.Value);
                QueryRes queryRes = this.portService.getBusinessRangePortAdd(paraDict);
                ArrayList showColumnList = new ArrayList();
                showColumnList.Add("C_PORT_CODE");
                showColumnList.Add("C_PORT_NAME");
                this.tableListLoader.loadTable(this.tabPort, queryRes, true, true, ClsEnums.KTableDataShowMode.TreeMode, showColumnList);
            }
        }

        /// <summary>
        /// 加载组合信息
        /// </summary>
        /// <param name="lstPort">组合列表</param>
        private void loadPortData(List<string> lstPort)
        {
            QueryRes queryRes = null;
            Dictionary<string, string> paraDict = null;
            ArrayList showColumnList = null;
            try
            {
                getPortInstanceService();
                paraDict = new Dictionary<string, string>();
                string strPort = "";
                string curUserCode = ClsContext.currentUser.C_USER_CODE;
                string curPostCodes = ClsContext.currentUserPostCodes;
                IRightManageDataService rightSVC = DataServiceFactory.createService<IRightManageDataService>();
                List<string> dataRightList = rightSVC.getUserDataRight(curUserCode, curPostCodes);
                if (dataRightList == null)
                {
                    dataRightList = new List<string>();
                }

                foreach (string portcode in dataRightList)
                {
                    strPort += portcode + ",";
                }

                paraDict.Add("dataClass", "Port");
                paraDict.Add("C_PORT_CODE", strPort);
                paraDict.Add("C_BUSINESS_CODE", this.selBusinessType.Value);
                queryRes = this.portService.getBusinessRangePortAdd(paraDict);
                showColumnList = new ArrayList();
                showColumnList.Add("C_PORT_CODE");
                showColumnList.Add("C_PORT_NAME");
                this.tableListLoader.loadTable(this.tabPort, queryRes, true, true, ClsEnums.KTableDataShowMode.TreeMode, showColumnList);
                Dictionary<string, Yss.KTable.Models.Row> dict = tabPort.Tag as Dictionary<string, Yss.KTable.Models.Row>;
                foreach (string cPort in lstPort)
                {
                    if (dict != null && dict.ContainsKey(cPort))
                    {
                        dict[cPort].Checked = true;
                    }
                }
            }
            catch (Exception ex)
            {
                ClsBaseException.DiscardException(ex);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("004", _formFun, status));
            }
        }

        /// <summary>
        /// 创建组合接口实例
        /// </summary>
        private void getPortInstanceService()
        {
            try
            {
                ClsAssocia asc = ClsClzCfgMgr.getAssociaParam("pd_portfolio");
                if (null == this.portService)
                {
                    Type serviceType = ReflectBase.YssGetType(asc.ServiceDllName, asc.ServiceName);
                    this.portService = ServiceFactory.createService(serviceType) as IPortService;
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.ToString());
            }
        }

        /// <summary>
        /// 保存之前对数据进行
        /// 验证
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_PortBusinessRange_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        {
            Dictionary<string, string> paramDict = new Dictionary<string, string>();

            ////组合项为空时警告提示
            if (0 == this.tabPort.CheckedRows.Count || string.IsNullOrEmpty(this.selBusinessType.Value))
            {
                sendRemind("请选择业务类型和组合");
                return;
            }

            paramDict.Add("C_BUSINESS_CODE", this.selBusinessType.Value);
            string ports = "";
            int countAssType = 0;
            foreach (Row row in this.tabPort.CheckedRows)
            {
                Port tempPort = row.Tag as Port;
                if (row.HasChild)
                {
                    countAssType++;
                    continue;
                }

                ports = ports + tempPort.C_PORT_CODE + ",";
            }

            if (countAssType == this.tabPort.CheckedRows.Count)
            {
                sendRemind("请选择组合");
                return;
            }

            ports = ports.Substring(0, ports.Length - 1);
            paramDict.Add("C_PORT_CODE", ports);
            paramSaveDict = paramDict;
        }

        /// <summary>
        /// 提醒
        /// BUG #364849 产品业务范围界面新增母帐组合失败
        /// </summary>
        /// <param name="errorMess">自定义提示信息</param>
        private void sendRemind(string errorMess)
        {
            YssMessageBox.currentForm = this;
            ClsRetInfo inf = new ClsRetInfo();
            inf.icon = MessageBoxIcon.Warning;
            inf.infoTitle = "警告";
            inf.infoCode = "WARN-006";
            inf.infoContent = errorMess;
            YssMessageBox.ShowCommonInfoText(inf);
        }

        /// <summary>
        /// 设置组合可用
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_PortBusinessRange_S_YssOnBeforeEditClick(object sender, YssBeforeOperEventArgs e)
        {
            this.tabPort.Enabled = true;
            getData("edit");
        }

        /// <summary>
        /// 设置组合可用
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_PortBusinessRange_S_YssOnBeforeNewClick(object sender, YssBeforeOperEventArgs e)
        {
            this.tabPort.Enabled = true;
        }

        /// <summary>
        /// 设置组合可用
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_PortBusinessRange_S_YssOnBeforeCopyClick(object sender, YssBeforeOperEventArgs e)
        {
            this.tabPort.Enabled = true;
            getData("copy");
        }

        /// <summary>
        /// 调整控件的状态
        ///   Added by ll 20120621
        /// </summary>
         protected override void YssChangeControlState()
         {
             if (status == ClsEnums.StatusSetting.YssAdd)
             {
                 clsInterface.yssClearTableCtlValue(tbMain);
                 clsInterface.yssClearTableCtlValue(tabPort);
                 tabPort.Rows.Clear(true);
                 tabPort.Refresh();
             }
         }

        /// <summary>
        /// 重写
        /// </summary>
        public override void initControlStat()
        {
            base.initControlStat();
            ////设置搜索面板在浏览时候下的状态---浏览状态
            this.txtSearch.YssReadOnly = (status == ClsEnums.StatusSetting.YssBrow);
        }

        /// <summary>
        /// From Load Event
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_PortBusinessRange_S_Load(object sender, EventArgs e)
        {
            this.getInstanceService();
        }

        /// <summary>
        /// 创建组合关联方案服务接口实例
        /// </summary>
        private void getInstanceService()
        {
            if (null == this.portBusinessRangeService)
            {
                Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
                this.portBusinessRangeService = ServiceFactory.createService(serviceType) as IPortBusinessRangeService;
                this.dataService = this.portBusinessRangeService;
            }
        }

        /// <summary>
        /// 初始化服务
        /// </summary>
        protected override void initServiceMVC()
        {
            getInstanceService();
        }

        /// <summary>
        /// 展示对象到窗体
        /// </summary>
        /// <param name="pojo">Pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            PortBusinessRangePojo portBusinessRange = null;
            try
            {
                portBusinessRange = yssGetBaseSelTypeItemMVC() as PortBusinessRangePojo;
                if (null != portBusinessRange)
                {
                    this.selBusinessType.IsFocused = false;
                    this.selBusinessType.Value = portBusinessRange.C_BUSINESS_CODE;
                    getData("brow");

                    ////showInfo每条只显示一条信息,所以此处写死为默认勾选第一条
                    if (tabPort.Rows.Count != 0)
                    {
                        tabPort.Rows[0].Checked = true;
                    }
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 封装一组数据
        /// </summary>
        /// <returns>List</returns>
        public override ArrayList yssGetObjListMVC()
        {
            ArrayList pojoList = new ArrayList(); // 创建list列表;
            PortBusinessRangePojo portBusinessRange = null;
            Port port = null;
            try
            {
                ////循环获取所有选中的数据
                foreach (Row row in this.tabPort.CheckedRows)
                {
                    port = row.Tag as Port;
                    ////验证当前数据是否含有组合组合信息，有选中的组合信息添加组合信息
                    if (ClsConstant._ISPort.Equals(port.DATA_TYPE))
                    {
                        portBusinessRange = new PortBusinessRangePojo();
                        portBusinessRange.C_PORT_CODE = port.C_PORT_CODE;
                        portBusinessRange.C_BUSINESS_CODE = this.selBusinessType.Value;
                        pojoList.Add(portBusinessRange);
                    }
                }

            }
            catch (Exception ex)
            {
                ClsBaseException.DiscardException(ex);
                ClsRetInfo info = ClsRetInfoDealer.getCommonError("ERR-000001", _formFun, status);
                info.setSpecStr("dataObject", this.Text);
                info.setSpecStr("operType", "保存");
                YssMessageBox.ShowCommonInfo(info);
            }

            return pojoList;
        }

        /// <summary>
        /// 获取组合列头信息 By Jinghehe 2014-6-4
        /// </summary>
        /// <returns>ListHeadInfo</returns>
        private List<ListHeadInfo> getPortHeadInfo()
        {
            List<ListHeadInfo> infoList = new List<ListHeadInfo>();
            ListHeadInfo headInfo = new ListHeadInfo();
            headInfo.Key = "C_PORT_NAME_ST";
            headInfo.Text = "组合名称";
            headInfo.Format = "";
            headInfo.Align = "left";
            headInfo.ShowConvert = "false";

            infoList.Add(headInfo);

            headInfo = new ListHeadInfo();
            headInfo.Key = "C_PORT_CODE";
            headInfo.Text = "组合代码";
            headInfo.Format = "";
            headInfo.Align = "left";
            headInfo.ShowConvert = "false";
            infoList.Add(headInfo);
            return infoList;
        }

        /// <summary>
        /// 初始化table 列头信息 By Jinghehe 2014-6-4
        /// </summary>
        private void initTabHead()
        {
            List<BasePojo> dataList = new List<BasePojo>();
            //// 组合列头
            QueryRes portRes = new QueryRes();
            portRes.ListHeadList = this.getPortHeadInfo();
            portRes.DataList = dataList;
            //// 组合列头
            tableListLoader.loadTable(this.tabPort, portRes, true, true, ClsEnums.KTableDataShowMode.TreeMode);
        }

        /// <summary>
        /// add by chenchen 2016.8.15 STORY #30361 中信证券-产品模糊查询功能需求
        /// 描述：在产品方案管理中增加模糊查询，根据输入的内容筛选数据，列表中应该只列出符合条件的数据
        /// 逻辑说明：在检索文本框输入条件，再点击[查询]（或回车）后，组合/群组列表自动按此条件进行模糊查询：
        /// 过滤结果为 组合代码/组合名称（群组分页为群组代码/群组名称）包含检索文本框内容的的数据。
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void txtSearch_TailClick(object sender, EventArgs e)
        {
            if (status == ClsEnums.StatusSetting.YssEdit || status == ClsEnums.StatusSetting.YssCopy)
            {
                getData("edit"); 
            }

            Table tagTab = this.tabPort;
            Yss.CommonLib.FilterTable(tagTab.Rows, this.txtSearch.Text, new int[] { 2, 3 });
        }

        /// <summary>
        /// 业务类型下拉框取值
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void selBusinessType_BeforeDropDownClick(object sender, DropDownEventArgs e)
        {
            e.Cancel = true;
            this.selBusinessType.Items.Clear();
            IPortBusinessRangeService portBusinessService = ServiceFactory.createService<IPortBusinessRangeService>();
            List<Vocabulary> businessTypeVoc = portBusinessService.getDataListByType("AO_AUTO_BUSINESS");
            foreach (Vocabulary voc in businessTypeVoc)
            {
                KTableEntity entity = new KTableEntity(voc.C_DV_NAME, voc.C_DV_CODE);
                this.selBusinessType.Items.Add(entity);
            }
        }

        /// <summary>
        /// s
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void selBusinessType_SelectedValueChanged(object sender, EventArgs e)
        {
            if (status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssCopy)
            {
                getData("new");
            }
        }

        /// <summary>
        /// 选择状态改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void checkState_Changed(object sender, EventArgs e)
        {
            int selectCount = 0;
            foreach (Row row in this.tabPort.CheckedRows)
            {
                if (row.SubRows.Count == 0)
                {
                    selectCount++;

                }
            }

            YssMessageBox.currentForm = this;
            ClsRetInfo inf = new ClsRetInfo();
            inf.icon = MessageBoxIcon.None;
            inf.infoTitle = "";
            inf.infoContent = "已选择 " + selectCount;
            YssMessageBox.ShowCommonInfoText(inf);
        }
    }
}
