using System;
using System.Collections.Generic;
using System.IO;
using System.Windows.Forms;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Communication.Service;
using FAST.Core.Context;
using FAST.Core.CRUD.Form;
using FAST.Core.CRUD.Interface;
using FAST.Core.Util;
using YssElecReco.Fun;
using YssElecReco.Pojo.Er;
using YssElecReco.Service.Er;
using System.Text;
using Yss.KTable.Models;
using FAST.Core.Cache;
using YssInformation.Support.Bi.AssociationOrgan.Pojo;
using YssInformation.Support.Bi.AssociationOrgan.Cache;

namespace YssElecReco.Form.Er
{
    /// <summary>
    /// 功能简介：电子对账详细信息界面
    /// 创建版本：betaV1.0.0.19
    /// 创建人： wulongxing
    /// 创建日期： 2018.01.08
    /// </summary>
    public partial class Frm_ELEC_DETAIL_L : FrmBaseListWithDetails, IFormDetailData
    {
        /// <summary>
        /// 报文日志
        /// </summary>
        private IErRptLogService iErRptLogService = null;

        /// <summary>
        /// 声明service对象
        /// </summary>
        private IErBbInfoService iErBbInfoService = null;

        /// <summary>
        /// 对账结果service
        /// </summary>
        private IErResultService iErResultService = null;

        /// <summary>
        /// 估值表service
        /// </summary>
        private IErGzbService iGzService;

        /// <summary>
        /// 双估值表service
        /// </summary>
        private IErDblgzbService iDblgzService;

        /// <summary>
        /// 科目表service
        /// </summary>
        private IErKmbService iKmService;

        /// <summary>
        /// 余额表service
        /// </summary>
        private IErYebService iYeService;

        /// <summary>
        /// 利润表service
        /// </summary>
        private IErLrbService iLrService;

        /// <summary>
        /// 所有者权益表service
        /// </summary>
        private IErSyzqybService iSyzqyService;

        /// <summary>
        /// 资产负债表service
        /// </summary>
        private IErZcfzbService iZcfzService;

        /// <summary>
        /// 净资产变动表service
        /// </summary>
        private IErJzcbdbService iJzcbdbService;

        /// <summary>
        /// 主要指标方案表service
        /// </summary>
        private IErResviewService iResviewService;

        /// <summary>
        /// STORY #41248 【南方基金】电子对账反馈结果中的差异数据下拉框扩展 新增显示级别下拉框
        /// 显示级别词汇字典
        /// </summary>
        private Dictionary<string, string> levelDict = new Dictionary<string, string>();

        /// <summary>
        /// 获取或设置一个值，该值为主窗体传过来的数据。当该窗体为某一窗体的附属窗体时有效。
        /// </summary>
        private BasePojo _mainDataPojo;

        /// <summary>
        /// 获取或设置当前窗体是否已经被FrmBaseListWidthDetails窗体关联为子窗体
        /// </summary>
        private bool _hadBeenRelationed = false;

        /// <summary>
        /// 父级窗体
        /// </summary>
        private FrmBaseListWithDetails parentForm;

        /// <summary>
        /// 是否加载反馈结果明细页
        /// </summary>
        private bool showPageResult = false;

        /// <summary>
        /// 是否显示差异行数
        /// </summary>
        private Yss.Controls.ToolStripLabel labStatuInfo;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_ELEC_DETAIL_L()
        {
            bUseMVCService = true;
            bUseMVCServiceLeft = true;
            InitializeComponent();
            ////STORY #51518 南方基金-“电子对账详细信息”只显示存在差异的数据    复选框记忆功能，默认为未勾选
            this.chkCopySameData.Checked = Convert.ToBoolean(IniFileOperator.ReadIniData("COPYOWNDATA", "CHECK_STATE", "False", Application.StartupPath + "/config/app.ini"));
            iErBbInfoService = ServiceFactory.createService<IErBbInfoService>();
            iErResultService = ServiceFactory.createService<IErResultService>();
            iSyzqyService = ServiceFactory.createService<IErSyzqybService>();
            iZcfzService = ServiceFactory.createService<IErZcfzbService>();
            iJzcbdbService = ServiceFactory.createService<IErJzcbdbService>();
            iLrService = ServiceFactory.createService<IErLrbService>();
            iYeService = ServiceFactory.createService<IErYebService>();
            iKmService = ServiceFactory.createService<IErKmbService>();
            iGzService = ServiceFactory.createService<IErGzbService>();
            iDblgzService = ServiceFactory.createService<IErDblgzbService>();
            iResviewService = ServiceFactory.createService<IErResviewService>();
            iErRptLogService = ServiceFactory.createService<IErRptLogService>();
            ///显示差异行数定义
            labStatuInfo = new Yss.Controls.ToolStripLabel();
            labStatuInfo.AutoSize = true;
            labStatuInfo.ControlAlign = System.Drawing.ContentAlignment.MiddleLeft;
            labStatuInfo.ForeColor = System.Drawing.Color.Red;
            this.barFormStatus.Items.Clear();
            this.barFormStatus.Items.Add(labStatuInfo);
            this.barFormStatus.Items.Add(this.proBar);
        }

        /// <summary>
        /// 是否加载反馈结果明细页
        /// </summary>
        public bool ShowPageResult
        {
            get
            {
                return this.showPageResult;
            }

            set
            {
                this.showPageResult = value;
            }
        }

        /// <summary>
        /// 获取父窗体
        /// </summary>
        public FrmBaseListWithDetails ParentForm
        {
            get
            {
                return this.parentForm;
            }

            set
            {
                this.parentForm = value;
            }
        }

        /// <summary>
        /// 主窗体数据
        /// </summary>
        public BasePojo MainDataPojo
        {
            get
            {
                return this._mainDataPojo;
            }

            set
            {
                if (this._mainDataPojo != value)
                {
                    this._mainDataPojo = value;
                }
            }
        }

        /// <summary>
        /// 获取或设置当前窗体是否已经被FrmBaseListWidthDetails窗体关联为子窗体
        /// </summary>
        public bool HadBeenRelationed
        {
            get
            {
                return _hadBeenRelationed;
            }

            set
            {
                _hadBeenRelationed = value;
            }
        }

        /// <summary>
        /// 窗体加载事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ELEC_DETAIL_L_Load(object sender, EventArgs e)
        {
            ////放到构造方法中
            /*iErBbInfoService = ServiceFactory.createService<IErBbInfoService>();
            iErResultService = ServiceFactory.createService<IErResultService>();
            syzqyService = ServiceFactory.createService<IErSyzqybService>();
            zcfzService = ServiceFactory.createService<IErZcfzbService>();
            lrService = ServiceFactory.createService<IErLrbService>();
            yeService = ServiceFactory.createService<IErYebService>();
            kmService = ServiceFactory.createService<IErKmbService>();
            gzService = ServiceFactory.createService<IErGzbService>();*/
        }

        #region IFormDetailData 成员

        /// <summary>
        /// 是否允许加载明细数据
        /// </summary>
        /// <param name="mainData">主窗体数据</param>
        /// <returns>是否允许加载明细数据</returns>
        public bool AllowReloadDetailData(BasePojo mainData)
        {
            bool retValue = false;
            if (mainData != null && mainData != this.MainDataPojo)
            {
                retValue = true;
            }

            return retValue;
        }

        /// <summary>
        /// STORY #50374 电子对账功能优化 (#5 #4 #3 #2 #1 )
        /// 刷新前处理   设置 “报文信息，文件类型，报表类型，开始日期，结束日期” 默认不显示
        /// </summary>
        /// <param name="info">电子对账信息</param>
        private void OnAfterLoadDetailData(ErBbInfo info) 
        {
            for (int i = 0; i < this.tbMain.Columns.Count; i++)
            {
                if ("报表类型".Equals(this.tbMain.Columns[i].Text) || "文件类型".Equals(this.tbMain.Columns[i].Text) 
                    || "报文序号".Equals(this.tbMain.Columns[i].Text))
                {
                    this.tbMain.Columns[i].Visible = false;
                }

                if ("开始日期".Equals(this.tbMain.Columns[i].Text) || "结束日期".Equals(this.tbMain.Columns[i].Text))
                {
                    if ("1701".Equals(info.C_FILE_TYPE) || "1801".Equals(info.C_FILE_TYPE) || "1711".Equals(info.C_FILE_TYPE) || "1811".Equals(info.C_FILE_TYPE) || "1901".Equals(info.C_FILE_TYPE))
                    {
                        this.tbMain.Columns[i].Visible = true;
                    }
                    else
                    {
                        this.tbMain.Columns[i].Visible = false;
                    }
                    
                }
            }
        }

        /// <summary>
        /// 初始化明细窗体
        /// </summary>
        /// <param name="parent">父窗体</param>
        public void InitializeDetailForm(FrmBaseListWithDetails parent)
        {
            this.sDllName = _formFun.YssAssocia.SetDllName;
            this.sSetClassName = _formFun.YssAssocia.SetFormName;
            this.sPojoClassName = _formFun.YssAssocia.PojoClsName;
            this.sPojoDllName = (_formFun.YssAssocia.PojoDllName != null && _formFun.YssAssocia.PojoDllName.Length > 0) ? _formFun.YssAssocia.PojoDllName : ClsFunction.getDllName(_formFun.YssAssocia.PojoClsName);
            if (_formFun != null)
            {
                this.Text = _formFun.C_FUN_NAME;
            }

            this.ShowLeftPanel = false;
            this.ShowFilterPanel = false;
            ParentForm = parent;
        }

        /// <summary>
        /// 加载明细数据
        /// </summary>
        /// <param name="mainData">主窗体数据</param>
        public void LoadDetailData(BasePojo mainData)
        {
            if (ShowPageResult)
            {
                this.tabCtrlDataMain.TabPages["tabPageResult", true].Visible = true;
            }
            else
            {
                this.tabCtrlDataMain.TabPages["tabPageResult", true].Visible = false;
            }

            if (mainData != null)
            {
                if (mainData is ErBbInfo)
                {
                    MainDataPojo = mainData;
                    ErBbInfo info = (ErBbInfo)mainData;
                    if (!"托管行数据".Equals(this.tabCtrlDataMain.SelectedTab.Text))
                    {
                        loadData(info);
                    }
                    else {
                        this.tbMain.Clear();
                    }
                }
            }
        }

        #endregion IFormDetailData 成员

        /// <summary>
        /// TabConrol主数据选择分页改变事件
        /// </summary>
        /// <param name="e">事件参数</param>
        protected override void OnTabCtrlDataMainPageChanged(Yss.Controls.TabPageEventArgs e)
        {
            if ("反馈结果明细".Equals(this.tabCtrlDataMain.SelectedTab.Text))
            {
                this.ShowFilterPanel = true;
            }
            else
            {
                this.ShowFilterPanel = false;
            }

            this.tbMain.Clear();
            LoadDetailData(MainDataPojo);
        }

        /// <summary>
        /// 加载数据
        /// </summary>
        /// <param name="info">电子对账数据信息</param>
        private void loadData(ErBbInfo info)
        {
            ElecEnums.ElecShowType showType = ElecEnums.ElecShowType.CHECK_ACT;
            this.tbMain.ShowColumnHeader = true; ////将表格显示列头属性设置为true；后面会将其设置为false
            this.tbMain.FixedTopRows = 0;
            if ("1001".Equals(info.C_FILE_TYPE))
            {
                showType = ElecEnums.ElecShowType.CHECK_STO;
            }
            else if ("1013".Equals(info.C_FILE_TYPE))
            {
                showType = ElecEnums.ElecShowType.CHECK_DBLGZ;
            }
            else if ("1011".Equals(info.C_FILE_TYPE))
            {
                showType = ElecEnums.ElecShowType.CHECK_ACT;
            }
            else if ("1031".Equals(info.C_FILE_TYPE))
            {
                showType = ElecEnums.ElecShowType.CHECK_KM;
            }
            else if ("1701".Equals(info.C_FILE_TYPE) || "1711".Equals(info.C_FILE_TYPE))
            {
                showType = ElecEnums.ElecShowType.CHECK_ZCFZ;
            }
            else if ("1801".Equals(info.C_FILE_TYPE) || "1811".Equals(info.C_FILE_TYPE))
            {
                showType = ElecEnums.ElecShowType.CHECK_LR;
            }
            else if ("1901".Equals(info.C_FILE_TYPE))
            {
                showType = ElecEnums.ElecShowType.CHECK_SYZQY;
            }
            else if ("1903".Equals(info.C_FILE_TYPE))
            {
                showType = ElecEnums.ElecShowType.CHECK_JZCBD;
            }

            if ("对账数据明细".Equals(this.tabCtrlDataMain.SelectedTab.Text))
            {
                loadDetailData(info, showType);
            }
            else if ("发送数据明细".Equals(this.tabCtrlDataMain.SelectedTab.Text))
            {
                loadXmlData(info);
            }
            else if ("反馈结果明细".Equals(this.tabCtrlDataMain.SelectedTab.Text))
            {
                loadResultData(info, showType);
            }
            else if ("处理状态图".Equals(this.tabCtrlDataMain.SelectedTab.Text))
            {
                loadStateData(info);
            }
            else if ("接收数据明细".Equals(this.tabCtrlDataMain.SelectedTab.Text))
            {
                loadRptLog(info);
            }
            else if ("托管行数据".Equals(this.tabCtrlDataMain.SelectedTab.Text))
            {
                loadResultData(info, showType);
            }
        }

        private void loadRptLog(ErBbInfo info)
        {
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("dataClass", "ErRptLog");
            paraDict.Add("C_SN", info.C_SN);
            paraDict.Add("C_DV_LOG_TYPE", "ER_RECEIVE_LOG");//界面上该标签页只展示接收的数据
            QueryRes res = this.iErRptLogService.queryByCondition(paraDict);
            TableListLoader tableLoader = new TableListLoader();
            tableLoader.loadTable(tbMain, res, true, true, ClsEnums.KTableDataShowMode.ListMode);

        }

        /// <summary>
        /// 发送数据明细  发送xml报文
        /// </summary>
        /// <param name="info">电子对账数据信息</param>
        private void loadXmlData(ErBbInfo info)
        {
            ////清除缓存
            labStatuInfo.Text = "";
            ////tbMain.Controls.Clear();
            ////WebBrowser dataBrowser = new WebBrowser();
            ////dataBrowser.Dock = System.Windows.Forms.DockStyle.Fill;
            ////dataBrowser.Name = "dataBrowser";
            ////tbMain.Controls.Add(dataBrowser);
            string content = iErBbInfoService.getXmlFile(info);
            ////复制文件
            string tmpFileName = ClsConstant.PATH_TEMP + "tmpEr.xml";

            if (!Directory.Exists(ClsConstant.PATH_TEMP))
            {
                Directory.CreateDirectory(ClsConstant.PATH_TEMP);
            }

            ////if (!File.Exists(tmpFileName))
            ////{
            ////    File.Create(tmpFileName);
            ////}

            ////StreamWriter sTmp = new StreamWriter(tmpFileName);
            ////sTmp.Write(content);//你想要写入的文本
            ////sTmp.Flush();
            ////sTmp.Close();
            ////sTmp.Dispose();
            ////BUG259974电子对账管理界面反馈结果分页查看下半区“发送数据明细”时报错
            using (FileStream sTmp = File.Create(tmpFileName))
            {
                byte[] data = new UTF8Encoding(true).GetBytes(content);
                sTmp.Write(data, 0, data.Length);
                sTmp.Flush();
            }

            xmlWebBrowser.Navigate(tmpFileName);
            xmlWebBrowser.Refresh();
            ////dataBrowser.Navigate(tmpFileName);
            ////dataBrowser.Visible = true;
        }

        /// <summary>
        /// 加载明细数据
        /// </summary>
        /// <param name="info">电子对账管理信息</param>
        /// <param name="showType">对账类型</param>
        private void loadDetailData(ErBbInfo info, ElecEnums.ElecShowType showType)
        {
            Dictionary<string, string> paraDict = new Dictionary<string, string>();

            paraDict.Add("C_SN", info.C_SN);
            paraDict.Add("C_ASS_CODE", info.C_ASS_CODE);
            TableListLoader tableLoader = new TableListLoader();
            tableLoader.AutoSort = false; ////自动排序设置为fasle；
            if (showType == ElecEnums.ElecShowType.CHECK_STO)
            {
                paraDict.Add("dataClass", "ErYeb");
                QueryRes res = iYeService.queryByCondition(paraDict);
                ErYebTableListLoader yebTabListLoader = new ErYebTableListLoader();
                yebTabListLoader.AutoSort = false;
                yebTabListLoader.loadTable(tbMain, res, true, true, ClsEnums.KTableDataShowMode.TreeMode);
                tbMain.CollapseAll();
                tbMain.Refresh();
            }
            else if (showType == ElecEnums.ElecShowType.CHECK_ACT)
            {
                paraDict.Add("dataClass", "ErGzb");
                QueryRes res = iGzService.queryByCondition(paraDict);
                tableLoader.loadTable(tbMain, res, true, true, ClsEnums.KTableDataShowMode.TreeMode);
                tbMain.CollapseAll();
                tbMain.Refresh();
            }
            else if (showType == ElecEnums.ElecShowType.CHECK_DBLGZ)
            {
                paraDict.Add("dataClass", "ErDblgzb");
                QueryRes res = iDblgzService.queryByCondition(paraDict);
                tableLoader.loadTable(tbMain, res, true, true, ClsEnums.KTableDataShowMode.TreeMode);
                tbMain.CollapseAll();
                tbMain.Refresh();
            }
            else if (showType == ElecEnums.ElecShowType.CHECK_KM)
            {
                paraDict.Add("dataClass", "ErKmb");
                QueryRes res = iKmService.queryByCondition(paraDict);
                tableLoader.loadTable(tbMain, res, true, true, ClsEnums.KTableDataShowMode.TreeMode);
                tbMain.CollapseAll();
                tbMain.Refresh();
            }
            else if (showType == ElecEnums.ElecShowType.CHECK_ZCFZ)
            {
                paraDict.Add("dataClass", "ErZcfzb");
                paraDict.Add("C_RPT_TYPE", info.C_RPT_TYPE);
                QueryRes res = iZcfzService.queryByCondition(paraDict);
                tableLoader.loadTable(tbMain, res, true, true, ClsEnums.KTableDataShowMode.ListMode);
            }
            else if (showType == ElecEnums.ElecShowType.CHECK_LR)
            {
                paraDict.Add("dataClass", "ErLrb");
                paraDict.Add("C_RPT_TYPE", info.C_RPT_TYPE);
                QueryRes res = iLrService.queryByCondition(paraDict);
                tableLoader.loadTable(tbMain, res, true, true, ClsEnums.KTableDataShowMode.ListMode);
            }
            else if (showType == ElecEnums.ElecShowType.CHECK_SYZQY)
            {
                paraDict.Add("dataClass", "ErSyzqyb");
                paraDict.Add("C_RPT_TYPE", info.C_RPT_TYPE);
                QueryRes res = iSyzqyService.queryByCondition(paraDict);
                tableLoader.loadTable(tbMain, res, true, true, ClsEnums.KTableDataShowMode.ListMode);
            }
            else if (showType == ElecEnums.ElecShowType.CHECK_JZCBD)
            {
                paraDict.Add("dataClass", "ErJzcbdb");
                paraDict.Add("C_RPT_TYPE", info.C_RPT_TYPE);
                QueryRes res = iJzcbdbService.queryByCondition(paraDict);
                tableLoader.loadTable(tbMain, res, true, true, ClsEnums.KTableDataShowMode.ListMode);
            }
            ////电子对账之前不显示差异行数
            if (info.C_STATE.Equals("ER_IDENTICAL") || info.C_STATE.Equals("ER_ACCECPED"))
            {
                labStatuInfo.Text = iErBbInfoService.queryNumberOfRows(info);
            }
            else
            {
                labStatuInfo.Text = "";
            }
            this.OnAfterLoadDetailData(info);
        }

        /// <summary>
        /// 加载对账结果信息
        /// </summary>
        /// <param name="info">电子对账管理信息</param>
        /// <param name="showType">对账类型</param>
        private void loadResultData(ErBbInfo info, ElecEnums.ElecShowType showType)
        {
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("dataClass", "ErResultQuery");
            //// paraDict.Add("SHOW_TYPE", this.cboResultType.Value);
            if (this.cboResultType.Value == null)
            {
                this.cboResultType.Value = "CY_DATA";
            }
            ////STORY #41248 【南方基金】电子对账反馈结果中的差异数据下拉框扩展 增加主要指标下拉框
            if (this.cboXszyzb.Value != null && !this.cboXszyzb.Value.Trim().Equals(""))
            {
                string planCode = this.cboXszyzb.Value;
                List<string> list = iResviewService.queryItemCodesByPlanCode(planCode);
                paraDict.Add("ARRAY_C_KM_CODE", string.Join(",", list.ToArray()));
            }

            if ("托管行数据".Equals(this.tabCtrlDataMain.SelectedTab.Text))
            {
                paraDict.Add("SHOW_TYPE", "ALL_DATA");
            }
            else {
                paraDict.Add("SHOW_TYPE", this.cboResultType.Value);
            }

            paraDict.Add("C_SN", info.C_SN);
            paraDict.Add("C_ASS_CODE", info.C_ASS_CODE);
            paraDict.Add("C_CHECK_FLAG", (info.C_RPT_TYPE == "01" ? "" : info.C_RPT_TYPE + "_") + info.C_FILE_TYPE);
            paraDict.Add("D_DATE", info.D_DATE);
            paraDict.Add("C_RPT_TYPE", info.C_RPT_TYPE);
            QueryRes res = this.iErResultService.queryByCondition(paraDict);

            if ("托管行数据".Equals(this.tabCtrlDataMain.SelectedTab.Text))
            {
                ElecBBInfoUtil.init_Tgh_Table_Column(tbMain, false, true, showType);
            }
            else {
                ElecBBInfoUtil.init_Table_Column(tbMain, false, true, showType);
            }
            

            if (null != res && null != res.DataList && res.DataList.Count > 0)
            {
                if ("托管行数据".Equals(this.tabCtrlDataMain.SelectedTab.Text))
                {
                    new ElecBBInfoUtil().drawTghData(tbMain, res.DataList, "ALL_DATA", true);
                }
                else
                {
                    new ElecBBInfoUtil().drawResultData(tbMain, res.DataList, this.cboResultType.Value, this.chkCopySameData.Checked);
                }
      
                ////ElecBBInfoUtil.drawResultData(tbMain, res.DataList);
            }

            labStatuInfo.Text = iErBbInfoService.queryNumberOfRows(info);
        }

        /// <summary>
        /// 加载处理状态图
        /// </summary>
        /// <param name="info">电子对账管理信息</param>
        private void loadStateData(ErBbInfo info)
        {
            ////不显示差异行数
            labStatuInfo.Text = "";
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("C_SN", info.C_SN); ////唯一标识
            paraDict.Add("C_ASS_CODE", info.C_ASS_CODE); ////产品
            if (info.C_STATE.Equals("ER_SEND"))
            {
                this.label3.BackColor = System.Drawing.SystemColors.Highlight;
                this.label6.BackColor = System.Drawing.SystemColors.ActiveBorder;
                this.label16.BackColor = System.Drawing.SystemColors.ActiveBorder;
                this.label5.BackColor = System.Drawing.SystemColors.ActiveBorder;
                this.label4.BackColor = System.Drawing.SystemColors.ActiveBorder;
                paraDict.Add("ARRAY_C_STATE", "ER_SEND"); ////状态
            }
            else if (info.C_STATE.Equals("ER_SENDED"))
            {
                this.label3.BackColor = System.Drawing.SystemColors.Highlight;
                this.label6.BackColor = System.Drawing.SystemColors.Highlight;
                this.label16.BackColor = System.Drawing.SystemColors.ActiveBorder;
                this.label5.BackColor = System.Drawing.SystemColors.ActiveBorder;
                this.label4.BackColor = System.Drawing.SystemColors.ActiveBorder;
                paraDict.Add("ARRAY_C_STATE", "ER_SEND,ER_SENDED"); ////状态
            }
            else if (info.C_STATE.Equals("ER_SENDED_SECCUSS") || info.C_STATE.Equals("ER_SENDED_FAIL"))
            {
                this.label3.BackColor = System.Drawing.SystemColors.Highlight;
                this.label6.BackColor = System.Drawing.SystemColors.Highlight;
                this.label16.BackColor = System.Drawing.SystemColors.Highlight;
                this.label5.BackColor = System.Drawing.SystemColors.ActiveBorder;
                this.label4.BackColor = System.Drawing.SystemColors.ActiveBorder;
                paraDict.Add("ARRAY_C_STATE", "ER_SEND,ER_SENDED,ER_SENDED_SECCUSS,ER_SENDED_FAIL"); ////状态
            }
            else if (info.C_STATE.Equals("ER_ACCECPED"))
            {
                this.label3.BackColor = System.Drawing.SystemColors.Highlight;
                this.label6.BackColor = System.Drawing.SystemColors.Highlight;
                this.label16.BackColor = System.Drawing.SystemColors.Highlight;
                this.label5.BackColor = System.Drawing.SystemColors.Highlight;
                this.label4.BackColor = System.Drawing.SystemColors.Highlight;
                paraDict.Add("ARRAY_C_STATE", "ER_SEND,ER_SENDED,ER_SENDED_SECCUSS,ER_SENDED_FAIL,ER_ACCECPED"); ////状态
            }
            else if (info.C_STATE.Equals("ER_IDENTICAL"))
            {
                this.label3.BackColor = System.Drawing.SystemColors.Highlight;
                this.label6.BackColor = System.Drawing.SystemColors.Highlight;
                this.label16.BackColor = System.Drawing.SystemColors.Highlight;
                this.label5.BackColor = System.Drawing.SystemColors.Highlight;
                this.label4.BackColor = System.Drawing.SystemColors.ActiveBorder;
                paraDict.Add("ARRAY_C_STATE", "ER_SEND,ER_SENDED,ER_SENDED_SECCUSS,ER_SENDED_FAIL,ER_IDENTICAL"); ////状态
            }

            this.showStateInfoDetail(info, paraDict);
        }

        /// <summary>
        /// 显示状态详细信息
        /// </summary>
        /// <param name="info">info</param>
        /// <param name="paraDict">参数字典</param>
        private void showStateInfoDetail(ErBbInfo info, Dictionary<string, string> paraDict) 
        {
            ////BUG267803电子对账管理界面下半区处理状态图分页框体展示有误
            ////BUG267801电子对账管理界面下半区处理状态图分页数据展示有误
            clearState();
            IErStepStateService iErStepStateService = ServiceFactory.createService<IErStepStateService>();
            List<BasePojo> stateList = iErStepStateService.queryListByTypes(paraDict);
            if (null == stateList || stateList.Count <= 0)
            {
                return;
            }

            foreach (BasePojo basePojo in stateList)
            {
                ErStepState stepState = basePojo as ErStepState;
                if (stepState.C_STATE.Equals("ER_SEND"))
                {
                    this.labelEx2.Text += stepState.C_STEP_DATE + "  " + info.OperUser + stepState.C_ERR + "\n";
                }
                else if ((basePojo as ErStepState).C_STATE.Equals("ER_SENDED"))
                {
                    this.labelEx3.Text += stepState.C_STEP_DATE + "  " + info.OperUser + stepState.C_ERR + "\n";
                }
                else if ((basePojo as ErStepState).C_STATE.Equals("ER_SENDED_SECCUSS") || (basePojo as ErStepState).C_STATE.Equals("ER_SENDED_FAIL"))
                {
                    this.labelEx4.Text += stepState.C_STEP_DATE + "  " + info.OperUser + stepState.C_ERR + "\n";
                }
                else if ((basePojo as ErStepState).C_STATE.Equals("ER_ACCECPED"))
                {
                    this.labelEx5.Text += stepState.C_STEP_DATE + "  " + info.OperUser + stepState.C_ERR + "\n";
                    this.labelEx6.Text += stepState.C_STEP_DATE + "  " + info.OperUser + stepState.C_ERR + "\n";
                }
                else if ((basePojo as ErStepState).C_STATE.Equals("ER_IDENTICAL"))
                {
                    this.labelEx5.Text += stepState.C_STEP_DATE + "  " + info.OperUser + stepState.C_ERR + "\n";
                }
            }
        }

        /// <summary>
        /// 初始化控件值
        /// </summary>
        private void clearState()
        {
            this.labelEx2.Text = "";
            this.labelEx3.Text = "";
            this.labelEx4.Text = "";
            this.labelEx5.Text = "";
            this.labelEx6.Text = "";
        }

        /// <summary>
        /// 搜索按钮
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        public override void btnSearch_Click(object sender, EventArgs e)
        {
            base.btnSearch_Click(sender, e);
            if (this.parentForm.tbMain.SelectedRow != null)
            {
                object obj = this.parentForm.tbMain.SelectedRow.Tag;
                if (null != obj && obj.GetType() == typeof(ErBbInfo))
                {
                    this.loadData((obj as ErBbInfo));
                }
            }
        }

        /// <summary>
        /// 设置主要指标方案
        /// </summary>
        /// <param name="sender">事件触发者</param>
        /// <param name="e">事件</param>
        private void btnSzzbfa_Click(object sender, EventArgs e)
        {
            Frm_Elec_Plan_S frm_Elec_Plan_S = new Frm_Elec_Plan_S();
            string code = this.cboXszyzb.Value;
            if (this.cboXszyzb.Value == null || this.cboXszyzb.Value.Trim().Equals(""))
            {
                this.status = ClsEnums.StatusSetting.YssAdd;
            }
            else
            {
                this.status = ClsEnums.StatusSetting.YssBrow;
                frm_Elec_Plan_S.Code = code;
                string[] array = this.cboXszyzb.Text.Split('_');
                if (array.Length < 2)
                {
                    frm_Elec_Plan_S.Name = this.cboXszyzb.Text;
                }
                else
                {
                    frm_Elec_Plan_S.Name = array[1];
                }
            }
            
            frm_Elec_Plan_S.yssShowForm(this);
       
        }

        /// <summary>
        /// 显示级别下拉框改变事情
        /// </summary>
        /// <param name="sender">事件触发者</param>
        /// <param name="e">事件</param>
        private void cboXsjb_SelectedValueChanged(object sender, EventArgs e)
        {
            if (null != this.cboXsjb.Value)
            {
                int level = Convert.ToInt32(this.cboXsjb.Value.ToString());
                this.tbMain.ShowGradingLevel(level);
                updateLevelDict();
            }
        }

        /// <summary>
        /// 显示级别加载数据源
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboXsjb_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            e.Cancel = true;
            ////if (this.cboXsjb.Items.Count > 0)
            ////{
            ////    return;
            ////}
            setItems();
        }

        /// <summary>
        /// 设置显示级别下拉框
        /// </summary>
        private void setItems()
        {
            this.cboXsjb.Items.Clear();
            this.cboXsjb.Value = "";
            if (this.tbMain.Rows.Count > this.tbMain.FixedTopRows && this.cboXsjb.Items.Count == 0)
            {
                int level = this.tbMain.GetGradingLevel(this.tbMain.Rows, 1);
                for (int i = 1; i <= level; i++)
                {
                    Yss.KRichEx.AutoFilter.Model.KTableEntity tableEntity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(i.ToString() + "级", i.ToString());
                    this.cboXsjb.Items.Add(tableEntity);
                }

                if (levelDict.ContainsKey(this.tbMain.ExportName.ToString()))
                {
                    string lv = "";
                    levelDict.TryGetValue(this.tbMain.ExportName.ToString(), out lv);
                    this.cboXsjb.Value = lv;
                }
                else
                {
                    this.cboXsjb.Value = level.ToString();
                }
            }
        }

        /// <summary>
        /// 更新级别字典LevelDict
        /// </summary>
        private void updateLevelDict()
        {
            if (levelDict.ContainsKey(this.tbMain.ExportName.ToString()))
            {
                levelDict.Remove(this.tbMain.ExportName.ToString());
                levelDict.Add(this.tbMain.ExportName.ToString(), this.cboXsjb.Value.ToString());
            }
            else
            {
                levelDict.Add(this.tbMain.ExportName.ToString(), this.cboXsjb.Value.ToString());
            }
        }

        /// <summary>
        /// ////STORY #51518 南方基金-“电子对账详细信息”只显示存在差异的数据
        /// 复选框勾选事件
        /// </summary>
        /// <param name="sender">事件源</param>
        /// <param name="e">事件</param>
        private void chkCopySameData_CheckedChanged(object sender, EventArgs e)
        {
            string check_exe = this.chkCopySameData.Checked.ToString();
            IniFileOperator.WriteIniData("COPYOWNDATA", "CHECK_STATE", check_exe, Application.StartupPath + "/config/app.ini");
        }

        /// <summary>
        /// 屏蔽行双击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbMain_RowDoubleClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            if ("接收数据明细".Equals(this.tabCtrlDataMain.SelectedTab.Text))
            {
                ////Row row = sender as Row;
                ////ErRptLog log = row.Tag as ErRptLog;
                ////string info = this.iErRptLogService.queryLogById(log.Id);
                Frm_ELEC_RPT_LOG_S logFrm = new Frm_ELEC_RPT_LOG_S();
                logFrm.yssShowForm(this);
            }
        }

        /// <summary>
        /// 重写ResetTableMainExportSetting，用于重置导出文件名。
        /// </summary>
        /// <param name="table">待导出数据的Table</param>
        /// <returns>返回导出配置信息</returns>
        protected override Yss.FileProcessor.ExportSetting ResetTableMainExportSetting(Table table)
        {
            string dtDate = " ";
            Table table1 = this.tabCtrlDataMain.SelectedTab.Controls[0] as Table;  ////防止导出失败
            if (table1 != null)
            {
                table = table1;
                this.tbMain = table;
            }

            ErBbInfo pojo = this.parentForm.tbMain.SelectedRow.Tag as ErBbInfo;
            LicenseFile license = ClsContext.licFile as LicenseFile;
            string tghCode = "";
            string tghName = "";
            Yss.FileProcessor.ExportSetting exportSetting = base.ResetTableMainExportSetting(table);
            if (string.IsNullOrEmpty(exportSetting.ExportSubTitle))
            {
                exportSetting.ExportSubTitle = (license.C_User_Name == "" ? "深圳市赢时胜信息技术股份有限公司" : license.C_User_Name) + "_专用表";
            }

            if (string.IsNullOrEmpty(exportSetting.ExportDescription))
            {
                exportSetting.ExportDescription = "日期：" + dtDate + " 至 " + dtDate;
            }

            if (string.IsNullOrEmpty(exportSetting.ExportName))
            {
                exportSetting.ExportName = "电子对账详细信息_" + this._tabCtrlMain.SelectedTab.Text + "_" + DateTime.Now.ToString();
            }

            if ((exportSetting.ExportSubTitle != null && (exportSetting.ExportSubTitle.Contains("[托管行名称]") || exportSetting.ExportSubTitle.Contains("[托管行代码]")))
                || (exportSetting.ExportName != null && (exportSetting.ExportName.Contains("[托管行名称]") || exportSetting.ExportName.Contains("[托管行代码]"))))
            {
                Dictionary<string, string> map = this.iErResultService.getPortRelaOrgData(pojo.C_PORT_CODE);
                if (map.Count > 0)
                {
                    tghCode = map[pojo.C_PORT_CODE];
                }
                OrgCache orgCache = CacheFactory.CreateCache(CacheGroup.ORG) as OrgCache;
                Org org = orgCache.getOrgInfoDataByOrgCode(tghCode) as Org;
                if (org != null)
                {
                    tghName = org.C_ORG_NAME;
                }

            }
            ////exportSetting.ExportName = exportSetting.ExportName + "(" + dtDate + "至" + dtDate + ")";

            ////文件名转义
            exportSetting.ExportNameTranslate.Add("托管行代码", tghCode);

            exportSetting.ExportNameTranslate.Add("托管行名称", tghName);

            ////////副标题转义
            exportSetting.ExportSubTitleTranslate.Add("托管行代码", tghCode);
            exportSetting.ExportSubTitleTranslate.Add("托管行名称", tghName);

            ////标题转义
            exportSetting.ExportTitleTranslate.Add("托管行代码", tghCode);
            exportSetting.ExportTitleTranslate.Add("托管行名称", tghName);
            ////exportSetting.ExportTitleTranslate.Add("日期", DateTime.Now.ToString());

            ////exportSetting.ExportDescription = "日期：" + dtDate + " 至 " + dtDate;

            //exportSetting.ExportSubTitle = (license.C_User_Name == "" ? "深圳市赢时胜信息技术股份有限公司" : license.C_User_Name) + "_专用表";
            //exportSetting.ExportDescription = "日期：" + dtDate + " 至 " + dtDate;

            //////这里为Table设置，是为了防止用户点击导出设置时名字不能带日期
            ///////添加时间防止，重复导出过多提示 BUG 114827 weijj 20150703
            //exportSetting.ExportName = "电子对账详细信息_" + this._tabCtrlMain.SelectedTab.Text + "_" + DateTime.Now.ToString();
            exportSetting.ExportTitle = exportSetting.ExportTitle;
            exportSetting.ExportSubTitle = exportSetting.ExportSubTitle;
            exportSetting.ExportDescription = exportSetting.ExportDescription;
            return exportSetting;
        }

    }
}