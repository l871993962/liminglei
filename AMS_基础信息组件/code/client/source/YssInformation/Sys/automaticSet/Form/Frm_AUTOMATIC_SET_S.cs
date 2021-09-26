using System;
using System.Collections;
using System.Collections.Generic;
using System.Windows.Forms;
using FAST.Common.Service.DataService;
using FAST.Common.Service.Dict.Pojo;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.BaseForm;
using FAST.Core.Communication.DataService;
using FAST.Core.Communication.Service;
using FAST.Core.Context;
using FAST.Core.Context.Events;
using FAST.Core.Exceptions;
using FAST.Core.Resource;
using FAST.Core.Util;
using FAST.Platform.User.Service;
using Yss.KRichEx.AutoFilter.Events;
using Yss.KRichEx.AutoFilter.Model;
using Yss.KTable.Collections;
using Yss.KTable.Models;
using YssInformation.Support.Sys.automaticSet.Pojo;
using YssInformation.Support.Sys.automaticSet.Service;
using YssProductInfo.Support.Ab.Port.Service;
using Yss.KMessage;
using FAST.Core.BaseControl.Fun;

namespace YssInformation.Sys.automaticSet.Form
{
    /// <summary>
    /// 组合关联自动化业务类型的设置窗体
    /// </summary>
    public partial class Frm_AUTOMATIC_SET_S : FrmTabItemSet
    {
        /// <summary>
        /// 产品组合数据集
        /// </summary>
        private static QueryRes portQueryRes = null;
        /// <summary>
        /// 自动化业务类型接口
        /// </summary>
        private IAutomaticSetService automaticSetService = null;

        /// <summary>
        /// 业务类型服务接口
        /// </summary>
        private IAutomaticSetPathService automaticSetPathService;

        /// <summary>
        /// 组合接口
        /// </summary>
        private IPortService portService = null;

        /// <summary>
        /// 业务类型
        /// </summary>
        private string businessType = null;

        /// <summary>
        /// 明细类型
        /// </summary>
        private string businessCode = null;

        /// <summary>
        /// 所属估值表日期
        /// </summary>
        private string vaTime = null;

        /// <summary>
        /// 解析类
        /// </summary>
        private TableListLoader tableListLoader = null;

        /// <summary>
        /// 保存数据时的参数
        /// </summary>
        private Dictionary<string, string> paramSaveDict = null;

        /// <summary>
        /// 当前分页
        /// </summary>
        private string currPage = "";

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_AUTOMATIC_SET_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
            this.selBusinessType.Value = "WBQS_TYPE";
            tableListLoader = new TableListLoader();
            //// 初始化table 列头
            initTabHead();
            this.tabControl.TabPages.Remove(this.tabGroupPage);
        }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="businessType">类型</param>
        public Frm_AUTOMATIC_SET_S(string businessType)
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
                    Row row = ((Frm_AUTOMATIC_SET_L)frmBaseViewList).tbLeftMain.SelectedRow;
                    if (row != null)
                    {
                        string businessType = (string)row.Tag;
                        if (string.IsNullOrEmpty(businessType))
                        {
                            this.selBusinessType.Value = businessType;
                        }
                    }

                    currPage = ((Frm_AUTOMATIC_SET_L)frmBaseViewList).currPage;
                }

                //由于调整新增按钮时候，页面会双闪的问题，现在只要是点新增的时候默认会置灰，故在此做统一处理打开保存按钮
                if (status == ClsEnums.StatusSetting.YssAdd)
                {
                    this.btnBar.setButtonEnabled(ClsButtonName.BtnSave);
                }

                this.selBusinessType.Items.Clear();
                this.selBusinessCode.Text = "";

                if ("外部券商".Equals(currPage))
                {
                    KTableEntity entity = new KTableEntity("外部券商", "WBQS_TYPE");
                    this.selBusinessType.Items.Add(entity);
                    this.selBusinessType.Value = "WBQS_TYPE";
                    this.selBusinessCode.ShowCheckBox = false;
                    this.cell9.Text = "  明细类型：";
                    this.cell13.Text = "";
                    this.cell14.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                }
                else if ("责任会计".Equals(currPage))
                {
                    KTableEntity entity = new KTableEntity("责任会计", "ZRKJ_TYPE");
                    this.selBusinessType.Items.Add(entity);
                    this.selBusinessType.Value = "ZRKJ_TYPE";
                    this.selBusinessCode.ShowCheckBox = true;
                    this.cell9.Text = "  责任会计：";

                    if (status == ClsEnums.StatusSetting.YssAdd)
                    {
                        RowCollection checkedRows = ((Frm_AUTOMATIC_SET_L)frmBaseViewList).tbMain.CheckedRows;
                        if (checkedRows.Count > 0)
                        {
                            loadCheckedTabPort(checkedRows);
                        }
                    }
                    this.cell13.Text = "";
                    this.cell14.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                }
                else if ("估值指标".Equals(currPage))
                {
                    KTableEntity entity = new KTableEntity("估值指标", "GZZB_TYPE");
                    this.selBusinessType.Items.Add(entity);
                    this.selBusinessType.Value = "GZZB_TYPE";
                    this.selBusinessCode.ShowCheckBox = true;
                    this.cell9.Text = "  估值指标：";

                    //初始化所属估值表日期默认值
                    if (string.IsNullOrEmpty(this.selVaTime.Text))
                    {
                        this.vaTime = "JDJZRQ";
                        this.selVaTime.Value = this.vaTime;
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
        /// 加载勾选的组合
        /// </summary>
        /// <param name="checkedRows">checkedRows</param>
        private void loadCheckedTabPort(RowCollection checkedRows)
        {
            string strPort = "";
            foreach (Row checkedRow in checkedRows)
            {
                AutomaticSetPojo automaticSetPojo = checkedRow.Tag as AutomaticSetPojo;
                if (null != automaticSetPojo)
                {
                    strPort += automaticSetPojo.C_PORT_CODE + ",";
                }
            }

            getPortInstanceService();
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("dataClass", "Port");
            paraDict.Add("C_BUSINESS_TYPE_CODE", this.selBusinessType.Value);
            paraDict.Add("C_BUSINESS_CODE", "");
            paraDict.Add("C_PORT_CODE", strPort);
            QueryRes queryRes = this.portService.getAutomaticSetPortAdd(paraDict);
            ////要显示的列
            ArrayList showColumnList = new ArrayList();
            showColumnList.Add("C_PORT_CODE");
            showColumnList.Add("C_PORT_NAME");
            this.tableListLoader.setTreeViewTable(this.tabPort, queryRes, true, true, ClsEnums.KTableDataShowMode.TreeMode, showColumnList);
            foreach (Row row in tabPort.Rows)
            {
                row.Checked = true;
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
                    if (null == this.selBusinessCode.Value)
                    {
                        return;
                    }
                    paraDict.Add("C_BUSINESS_TYPE_CODE", this.selBusinessType.Value);
                    paraDict.Add("C_BUSINESS_CODE", this.selBusinessCode.Value.Replace("|", ","));
                    paraDict.Add("C_PORT_CODE", strPort);
                    queryRes = this.portService.getAutomaticSetPortAdd(paraDict);
                }
                else if ("brow".Equals(queryType))
                {
                    paraDict.Add("C_PORT_CODE", ((AutomaticSetPojo)yssGetBaseSelTypeItemMVC()).C_PORT_CODE);
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
        /// 估值指标tab，加载所有组合
        /// </summary>
        /// <param name="queryCode">queryCode</param>
        private void getportCodeData(string queryCode)
        {
            try
            {
                ArrayList showColumnList = new ArrayList();
                QueryRes queryRes = new QueryRes();
                ClsAssocia asc = ClsClzCfgMgr.getAssociaParam("pd_portfolio");
                Type serviceType = ReflectBase.YssGetType(asc.ServiceDllName, asc.ServiceName);
                this.portService = ServiceFactory.createService(serviceType) as YssProductInfo.Support.Ab.Port.Service.IPortService;

                if ("GZZB".Equals(queryCode))
                {
                    if (portQueryRes == null)
                    {
                        List<string> dataRightList = ClsContext.DataRightList;
                        if (dataRightList == null)
                        {
                            dataRightList = new List<string>();
                        }
                        ////根据用户和岗位获取用户当前所拥有的组合
                        string strPort = "";
                        foreach (string portcode in dataRightList)
                        {
                            strPort += portcode + ",";
                        }

                        Dictionary<string, string> paraDict = new Dictionary<string, string>();
                        paraDict.Add("dataClass", "Port");
                        paraDict.Add("C_BUSINESS_CODE", "");
                        paraDict.Add("C_PORT_CODE", strPort);
                        portQueryRes = this.portService.getBusinessRangePortAdd(paraDict);
                    }
                    queryRes = portQueryRes;
                }
                ////要显示的列
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
        /// 保存之前对数据进行验证
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_AUTOMATIC_SET_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        {
            Dictionary<string, string> paramDict = new Dictionary<string, string>();

            ////组合项为空时警告提示
            if (0 == this.tabPort.CheckedRows.Count || string.IsNullOrEmpty(this.selBusinessType.Value) || string.IsNullOrEmpty(this.selBusinessCode.Value))
            {
                if ("外部券商".Equals(currPage))
                {
                    sendRemind("请选择明细类型和组合");
                    return;
                }
                else if ("责任会计".Equals(currPage))
                {
                    sendRemind("请选择责任会计和组合");
                    return;
                }
                else if ("估值指标".Equals(currPage))
                {
                    sendRemind("请选择估值指标和组合");
                    return;
                }
            }

            paramDict.Add("C_BUSINESS_CODE", this.selBusinessCode.Value.Replace("|", ","));
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
        private void Frm_AUTOMATIC_SET_S_YssOnBeforeEditClick(object sender, YssBeforeOperEventArgs e)
        {
            this.tabPort.Enabled = true;

            if ("责任会计".Equals(currPage))
            {
                this.selBusinessCode.ShowCheckBox = false;
                RowCollection checkedRows = ((Frm_AUTOMATIC_SET_L)frmBaseViewList).tbMain.CheckedRows;
                if (checkedRows.Count > 0)
                {
                    loadCheckedTabPort(checkedRows);
                    return;
                }
            }

            getData("edit");
        }

        /// <summary>
        /// 修改
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnEdit_Click(object sender, EventArgs e)
        {
            if ("责任会计".Equals(currPage))
            {
                Row selectedRow = ((Frm_AUTOMATIC_SET_L)frmBaseViewList).tbMain.SelectedRow;
                AutomaticSetPojo automaticSetPojo = selectedRow.Tag as AutomaticSetPojo;
                if (automaticSetPojo == null || "".Equals(automaticSetPojo.C_BUSINESS_CODE.Trim()))
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("责任会计为空，不允许修改"));
                    return;
                }

                string zrkj = "";
                RowCollection checkedRows = ((Frm_AUTOMATIC_SET_L)frmBaseViewList).tbMain.CheckedRows;
                foreach (Row checkedRow in checkedRows)
                {
                    automaticSetPojo = checkedRow.Tag as AutomaticSetPojo;
                    if (automaticSetPojo == null)
                    {
                        continue;
                    }

                    if ("".Equals(automaticSetPojo.C_BUSINESS_CODE.Trim()))
                    {
                        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("责任会计为空，不允许修改"));
                        return;
                    }

                    if ("".Equals(zrkj))
                    {
                        zrkj = automaticSetPojo.C_BUSINESS_CODE.Trim();
                    }
                    else if (!zrkj.Equals(automaticSetPojo.C_BUSINESS_CODE.Trim()))
                    {
                        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("您当前勾选的多个组合责任会计不一致，不允许批量修改"));
                        return;
                    }

                    if (1 == automaticSetPojo.AuditState)
                    {
                        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("勾选的数据均为未审核才能修改"));
                        return;
                    }
                }
            }

            base.btnEdit_Click(sender, e);
        }

        /// <summary>
        /// 复制
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnCopy_Click(object sender, EventArgs e)
        {
            if ("责任会计".Equals(currPage))
            {
                Row selectedRow = ((Frm_AUTOMATIC_SET_L)frmBaseViewList).tbMain.SelectedRow;
                AutomaticSetPojo automaticSetPojo = selectedRow.Tag as AutomaticSetPojo;
                if (automaticSetPojo == null || "".Equals(automaticSetPojo.C_BUSINESS_CODE.Trim()))
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("责任会计为空，不允许复制"));
                    return;
                }
            }

            base.btnCopy_Click(sender, e);
        }

        /// <summary>
        /// 删除
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnDelete_Click(object sender, EventArgs e)
        {
            if ("责任会计".Equals(currPage))
            {
                Row selectedRow = ((Frm_AUTOMATIC_SET_L)frmBaseViewList).tbMain.SelectedRow;
                AutomaticSetPojo automaticSetPojo = selectedRow.Tag as AutomaticSetPojo;
                if (automaticSetPojo == null || "".Equals(automaticSetPojo.C_BUSINESS_CODE.Trim()))
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("责任会计为空，不允许删除"));
                    return;
                }
            }

            base.btnDelete_Click(sender, e);
        }

        /// <summary>
        /// 审核
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnAudit_Click(object sender, EventArgs e)
        {
            if ("责任会计".Equals(currPage))
            {
                Row selectedRow = ((Frm_AUTOMATIC_SET_L)frmBaseViewList).tbMain.SelectedRow;
                AutomaticSetPojo automaticSetPojo = selectedRow.Tag as AutomaticSetPojo;
                if (automaticSetPojo == null || "".Equals(automaticSetPojo.C_BUSINESS_CODE.Trim()))
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("责任会计为空，不允许审核"));
                    return;
                }
            }

            base.btnAudit_Click(sender, e);
        }

        /// <summary>
        /// 设置组合可用
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_AUTOMATIC_SET_S_YssOnBeforeNewClick(object sender, YssBeforeOperEventArgs e)
        {
            this.tabPort.Enabled = true;
        }

        /// <summary>
        /// 设置组合可用
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_AUTOMATIC_SET_S_YssOnBeforeCopyClick(object sender, YssBeforeOperEventArgs e)
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
        private void Frm_AUTOMATIC_SET_S_Load(object sender, EventArgs e)
        {
            this.getInstanceService();
        }

        /// <summary>
        /// 创建组合关联方案服务接口实例
        /// </summary>
        private void getInstanceService()
        {
            if (null == this.automaticSetService)
            {
                Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
                this.automaticSetService = ServiceFactory.createService(serviceType) as IAutomaticSetService;
                this.dataService = this.automaticSetService;
            }

            if (null == this.automaticSetPathService)
            {
                automaticSetPathService = ServiceFactory.createService<IAutomaticSetPathService>();
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
            AutomaticSetPojo automaticSetPojo = null;
            try
            {
                if ("责任会计".Equals(currPage) || "外部券商".Equals(currPage))
                {
                    if ("责任会计".Equals(currPage) && status == ClsEnums.StatusSetting.YssEdit)
                    {
                        this.selBusinessCode.ShowCheckBox = false;
                        RowCollection checkedRows = ((Frm_AUTOMATIC_SET_L)frmBaseViewList).tbMain.CheckedRows;
                        if (checkedRows.Count > 0)
                        {
                            string strPort = "";
                            foreach (Row checkedRow in checkedRows)
                            {
                                automaticSetPojo = checkedRow.Tag as AutomaticSetPojo;
                                if (null != automaticSetPojo)
                                {
                                    strPort += automaticSetPojo.C_PORT_CODE + ",";

                                    if (null == this.selBusinessCode.Value || "".Equals(this.selBusinessCode.Value))
                                    {
                                        if (!"".Equals(automaticSetPojo.C_BUSINESS_TYPE_CODE))
                                        {
                                            this.selBusinessType.Value = automaticSetPojo.C_BUSINESS_TYPE_CODE;
                                        }
                                        this.selBusinessCode.Value = automaticSetPojo.C_BUSINESS_CODE.Trim().Split('_')[0];
                                    }
                                }
                            }

                            getPortInstanceService();
                            Dictionary<string, string> paraDict = new Dictionary<string, string>();
                            paraDict.Add("dataClass", "Port");
                            paraDict.Add("C_BUSINESS_TYPE_CODE", this.selBusinessType.Value);
                            paraDict.Add("C_BUSINESS_CODE", "");
                            paraDict.Add("C_PORT_CODE", strPort);
                            QueryRes queryRes = this.portService.getAutomaticSetPortAdd(paraDict);
                            ////要显示的列
                            ArrayList showColumnList = new ArrayList();
                            showColumnList.Add("C_PORT_CODE");
                            showColumnList.Add("C_PORT_NAME");
                            this.tableListLoader.setTreeViewTable(this.tabPort, queryRes, true, true, ClsEnums.KTableDataShowMode.TreeMode, showColumnList);
                            foreach (Row row in tabPort.Rows)
                            {
                                row.Checked = true;
                            }

                            return;
                        }
                    }

                    automaticSetPojo = yssGetBaseSelTypeItemMVC() as AutomaticSetPojo;
                    if (null != automaticSetPojo)
                    {
                        if (!"".Equals(automaticSetPojo.C_BUSINESS_TYPE_CODE))
                        {
                            this.selBusinessType.Value = automaticSetPojo.C_BUSINESS_TYPE_CODE;
                        }
                        this.selBusinessCode.Value = automaticSetPojo.C_BUSINESS_CODE.Trim().Split('_')[0];
                        getData("brow");

                        ////showInfo每条只显示一条信息,所以此处写死为默认勾选第一条
                        if (tabPort.Rows.Count != 0)
                        {
                            tabPort.Rows[0].Checked = true;
                        }
                    }
                }
                else if ("估值指标".Equals(currPage))
                {
                    this.selBusinessCode.ShowCheckBox = true;
                    Row selectRow = ((Frm_AUTOMATIC_SET_L)frmBaseViewList).tbMain.SelectedRow;
                    AutomaticSetPojo data = selectRow.Tag as AutomaticSetPojo;
                    //估值指标的text显示
                    this.selBusinessCode.Text = data.C_INDEX_NAME + " " + data.C_INDEX_ORDER;
                    this.businessCode = data.C_INDEX_CODE;
                    this.selBusinessCode.Value = this.businessCode;
                    this.selBusinessCode.NodeID = data.Id; //将id暂存NodeID中，方便后面修改前，删除之前的数据，再新增

                    //所属估值表日期初始值
                    if ("节点基准日期和上一工作日".Equals(data.C_VA_TIME))
                    {
                        this.vaTime = "JDJZRQHSYGZR";
                        this.selVaTime.Value = this.vaTime;
                    }
                    //先加载所有组合、再匹配设置勾选组合
                    getportCodeData("GZZB");
                    foreach (Row row in this.tabPort.Rows)
                    {
                        //先匹配父节点
                        Port pport = row.Tag as Port;

                        if (pport.C_PORT_CODE.Equals(data.C_PORT_CODE) && "PORT_TYPE".Equals(pport.DATA_TYPE) && row.SubRows.Count == 0)
                        {
                            row.Checked = true;
                            break;
                        }

                        //再匹配父节点下面的子节点(由于SubRows取的只是下一级的所有组合代码，现在暂时和需求沟通是只支持三级以内的组合代码默认勾选)
                        foreach (Row subRow in row.SubRows)
                        {
                            Port port = subRow.Tag as Port;

                            if (port.C_PORT_CODE.Equals(data.C_PORT_CODE) && "PORT_TYPE".Equals(port.DATA_TYPE) && subRow.SubRows.Count == 0)
                            {
                                subRow.Checked = true;
                                break;
                            }
                            else if (subRow.SubRows.Count > 0)
                            {
                                foreach (Row rrow in subRow.SubRows)
                                {
                                    Port ports = rrow.Tag as Port;
                                    if (ports.C_PORT_CODE.Equals(data.C_PORT_CODE) && "PORT_TYPE".Equals(port.DATA_TYPE) && rrow.SubRows.Count == 0)
                                    {
                                        rrow.Checked = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    this.tabPort.Refresh();
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
            AutomaticSetPojo automaticSetPojo = null;
            Port port = null;
            try
            {
                ////获取勾选数据的ID
                Dictionary<string, string> checkedId = new Dictionary<string, string>();
                if ("责任会计".Equals(currPage) && status == ClsEnums.StatusSetting.YssEdit)
                {
                    RowCollection checkedRows = ((Frm_AUTOMATIC_SET_L)frmBaseViewList).tbMain.CheckedRows;
                    foreach (Row checkedRow in checkedRows)
                    {
                        automaticSetPojo = checkedRow.Tag as AutomaticSetPojo;
                        if (null != automaticSetPojo)
                        {
                            checkedId.Add(automaticSetPojo.C_PORT_CODE, automaticSetPojo.Id);
                        }
                    }
                }

                ////数据去重
                HashSet<string> pojoSet = new HashSet<string>();
                ////循环获取所有选中的数据
                foreach (Row row in this.tabPort.CheckedRows)
                {
                    port = row.Tag as Port;
                    ////验证当前数据是否含有组合组合信息，有选中的组合信息添加组合信息
                    if (ClsConstant._ISPort.Equals(port.DATA_TYPE))
                    {
                        string[] businessCodes = this.selBusinessCode.Value.Split('|');
                        foreach (string businessCode in businessCodes)
                        {
                            string key = businessCode + "#" + port.C_PORT_CODE;
                            if (pojoSet.Contains(key))
                            {
                                continue;
                            }
                            else
                            {
                                pojoSet.Add(key);
                            }

                            automaticSetPojo = new AutomaticSetPojo();
                            automaticSetPojo.C_PORT_CODE = port.C_PORT_CODE;
                            automaticSetPojo.C_BUSINESS_TYPE_CODE = this.selBusinessType.Value;
                            automaticSetPojo.C_BUSINESS_CODE = businessCode;

                            if ("责任会计".Equals(currPage) && status == ClsEnums.StatusSetting.YssEdit)
                            {
                                if (checkedId.ContainsKey(automaticSetPojo.C_PORT_CODE))
                                {
                                    automaticSetPojo.Id = checkedId[automaticSetPojo.C_PORT_CODE];
                                }
                            }

                            pojoList.Add(automaticSetPojo);
                        }
                    }
                }

                pojoSet.Clear();
                checkedId.Clear();
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
            if (e.Items.Count > 0)
            {
                return;
            }

            KTableEntity entity = new KTableEntity("外部券商", "WBQS_TYPE");
            this.selBusinessType.Items.Add(entity);
        }

        /// <summary>
        /// s
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void selBusinessType_SelectedValueChanged(object sender, EventArgs e)
        {
            this.businessType = this.selBusinessType.Value;
        }

        /// <summary>
        /// 明细类型下拉框取值
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void selBusinessCode_BeforeDropDownClick(object sender, DropDownEventArgs e)
        {
            if (this.businessType == null)
            {
                this.businessType = "WBQS_TYPE";
                this.selBusinessType.Value = this.businessType;
            }

            e.Cancel = true;
            this.selBusinessCode.Items.Clear();

            if ("外部券商".Equals(currPage))
            {
                IAutomaticSetService automaticSetService = ServiceFactory.createService<IAutomaticSetService>();
                List<Vocabulary> businessTypeVoc = automaticSetService.getDataListByType(this.businessType);
                foreach (Vocabulary voc in businessTypeVoc)
                {
                    KTableEntity entity = new KTableEntity(voc.C_DV_CODE + "_" + voc.C_DV_NAME, voc.C_DV_CODE);
                    this.selBusinessCode.Items.Add(entity);
                }
            }
            else if ("责任会计".Equals(currPage))
            {
                IUserService userService = ServiceFactory.createService<IUserService>();
                List<User> userList = userService.queryAllUsers();
                foreach (User user in userList)
                {
                    KTableEntity entity = new KTableEntity(user.C_USER_CODE + "_" + user.C_USER_NAME, user.C_USER_CODE);
                    this.selBusinessCode.Items.Add(entity);
                }
            }
            else if ("估值指标".Equals(currPage))
            {
                IAutomaticSetPathService service = ServiceFactory.createService<IAutomaticSetPathService>();
                List<AutomaticSetPojo> list = service.getAllIndex();
                foreach (AutomaticSetPojo pojo in list)
                {
                    KTableEntity entity = new KTableEntity(pojo.C_INDEX_NAME + " " + pojo.C_INDEX_ORDER, pojo.C_INDEX_CODE);
                    this.selBusinessCode.Items.Add(entity);
                }
            }
        }

        /// <summary>
        /// 所属估值表日期下拉框取值
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void selVaTime_BeforeDropDownClick(object sender, DropDownEventArgs e)
        {
            e.Cancel = true;
            if (e.Items.Count > 0)
            {
                return;
            }
            List<Vocabulary> list = new List<Vocabulary>();
            Vocabulary voc = new Vocabulary();
            voc.C_DV_CODE = "JDJZRQ";
            voc.C_DV_NAME = "节点基准日期";
            list.Add(voc);
            Vocabulary voc2 = new Vocabulary();
            voc2.C_DV_CODE = "JDJZRQHSYGZR";
            voc2.C_DV_NAME = "节点基准日期和上一工作日";
            list.Add(voc2);
            foreach (Vocabulary pojo in list)
            {
                KTableEntity entity = new KTableEntity(pojo.C_DV_NAME, pojo.C_DV_CODE);
                this.selVaTime.Items.Add(entity);
            }  
        }

        /// <summary>
        /// 所属估值表日期下拉框改变值
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void selVaTime_SelectedValueChanged(object sender, EventArgs e)
        {
            this.vaTime = this.selVaTime.Value;
        }
        
        /// <summary>
        /// 明细类型下拉框改变值
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void selBusinessCode_SelectedValueChanged(object sender, EventArgs e)
        {
            RowCollection checkedRows = ((Frm_AUTOMATIC_SET_L)frmBaseViewList).tbMain.CheckedRows;
            if ("责任会计".Equals(currPage) && checkedRows.Count > 0)
            {
                string strPort = "";
                foreach (Row checkedRow in checkedRows)
                {
                    AutomaticSetPojo automaticSetPojo = checkedRow.Tag as AutomaticSetPojo;
                    if (null != automaticSetPojo)
                    {
                        strPort += automaticSetPojo.C_PORT_CODE + ",";
                    }
                }

                getPortInstanceService();
                Dictionary<string, string> paraDict = new Dictionary<string, string>();
                paraDict.Add("dataClass", "Port");
                paraDict.Add("C_BUSINESS_TYPE_CODE", this.selBusinessType.Value == null ? "ZRKJ_TYPE" : this.selBusinessType.Value);
                if (null == this.selBusinessCode.Value)
                {
                    paraDict.Add("C_BUSINESS_CODE", "");
                }
                else
                {
                    paraDict.Add("C_BUSINESS_CODE", this.selBusinessCode.Value.Replace("|", ","));
                }
                paraDict.Add("C_PORT_CODE", strPort);
                QueryRes queryRes = this.portService.getAutomaticSetPortAdd(paraDict);
                ////要显示的列
                ArrayList showColumnList = new ArrayList();
                showColumnList.Add("C_PORT_CODE");
                showColumnList.Add("C_PORT_NAME");
                this.tableListLoader.setTreeViewTable(this.tabPort, queryRes, true, true, ClsEnums.KTableDataShowMode.TreeMode, showColumnList);
                foreach (Row row in tabPort.Rows)
                {
                    row.Checked = true;
                }
            }
            else
            {
                //BUG #374279 【自动化业务设置】-【估值指标】分页大批量审核、反审核数据导致客户端长时间无响应
                if ("估值指标".Equals(currPage))
                {
                    if (status == ClsEnums.StatusSetting.YssAdd)
                    {
                        getportCodeData("GZZB");
                    }
                }
                else
                {
                    if (status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssCopy)
                    {
                        getData("new");
                    }
                }
            }

            this.businessCode = this.selBusinessCode.Value;
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

        /// <summary>
        /// 保存按钮点击事件
        /// </summary>
        /// <param name="sender">事件源</param>
        /// <param name="e">事件参数</param>
        protected override void btnSave_Click(object sender, EventArgs e)
        {
            if ("估值指标".Equals(currPage))
            {
                MessageDialog dlg = new MessageDialog();
                try
                {
                    List<Dictionary<string, string>> paraList = new List<Dictionary<string, string>>();
                    //估值指标
                    if (string.IsNullOrEmpty(this.selBusinessCode.Text))
                    {
                        throw new Exception("估值指标不能为空");
                    }
                    //所属估值表日期
                    if (string.IsNullOrEmpty(this.selVaTime.Text))
                    {
                        throw new Exception("所属估值表日期不能为空");
                    }

                    if (!string.IsNullOrEmpty(this.selBusinessCode.Text) && !string.IsNullOrEmpty(this.selBusinessCode.Value))
                    {
                        string[] values = this.selBusinessCode.Text.Split('|');
                        //遍历获取每一个指标对应的指标名称和指标编号
                        if (values != null && values.Length > 0)
                        {
                            for (int i = 0; i < values.Length; i++)
                            {
                                Dictionary<string, string> paraDict = new Dictionary<string, string>();

                                //根据索引获取对应的指标代码
                                string[] codes = this.selBusinessCode.Value.Split('|');

                                //指标代码
                                paraDict.Add("C_INDEX_CODE", codes[i]);
                                //所属估值表日期
                                paraDict.Add("C_VA_TIME", this.selVaTime.Value);                             
                                //业务类型
                                paraDict.Add("C_BUSINESS_TYPE_CODE", "GZZB");
                                RowCollection checkedRows = this.tabPort.CheckedRows;
                                //组合代码
                                if (checkedRows.Count == 0)
                                {
                                    throw new Exception("请选择对应的组合代码");
                                }
                                //遍历加载组合代码、组合名称
                                foreach (Row checkedRow in checkedRows)
                                {
                                    Port port = checkedRow.Tag as Port;

                                    if (null != port)
                                    {
                                        //不是组合类型的，直接跳过，不添加
                                        if (!"PORT_TYPE".Equals(port.DATA_TYPE))
                                        {
                                            continue;
                                        }

                                        //组合代码
                                        if (paraDict.ContainsKey("C_PORT_CODE"))
                                        {
                                            paraDict["C_PORT_CODE"] = port.C_PORT_CODE;
                                        }
                                        else
                                        {
                                            paraDict.Add("C_PORT_CODE", port.C_PORT_CODE);
                                        }
                                    }
                                    paraList.Add((new Dictionary<string, string>(paraDict)));
                                }
                            }
                        }
                    }

                    if (paraList.Count == 0)
                    {
                        return;
                    }

                    //保存前，先删除原数据 (true则是修改，先删除原数据，false则是新增)
                    if (!string.IsNullOrEmpty(this.selBusinessCode.NodeID) && status == ClsEnums.StatusSetting.YssEdit)
                    {
                        List<AutomaticSetPojo> list = new List<AutomaticSetPojo>();
                        AutomaticSetPojo automaticSetPojo = new AutomaticSetPojo();
                        automaticSetPojo.Id = this.selBusinessCode.NodeID;
                        list.Add(automaticSetPojo);
                        automaticSetPathService.deleteById(list);
                        this.selBusinessCode.NodeID = "";
                    }
                    bool ret = automaticSetPathService.saveList(paraList);
                    if (ret)
                    {
                        dlg.Show("保存成功", "提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
                        //重新查询
                        ((Frm_AUTOMATIC_SET_L)frmBaseViewList).btnSearch_Click(sender, e);
                    }
                    else
                    {
                        dlg.Show("保存失败", "提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
                    }
                }
                catch (Exception ex)
                {
                    dlg.Show(ex.Message, "错误", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
            else
            {
                base.btnSave_Click(sender, e);
            }
        }
    }
}
