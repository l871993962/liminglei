using System;
using System.Collections;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Windows.Forms;
using FAST.Common.Service.Dict.Pojo;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.BaseControl;
using FAST.Core.BaseControl.Fun;
using FAST.Core.Communication.Service;
using FAST.Core.Context;
using FAST.Core.Context.Events;
using FAST.Core.CRUD.Form;
using FAST.Core.CRUD.Interface;
using FAST.Core.Util;
using Yss.KRichEx.AutoFilter.Model;
using Yss.KTable.Models;
using YssElecReco.Fun;
using YssElecReco.Pojo.Er;
using YssElecReco.Service.Er;
using System.Text;
using YssInformation.Support.Bi.AssociationOrgan.Cache;
using YssInformation.Support.Bi.AssociationOrgan.Pojo;
using FAST.Core.Cache;

namespace YssElecReco.Form.Er
{
    /// <summary>
    /// 电子对账管理set界面
    /// </summary>
    public partial class Frm_ELEC_BATCHMX_S : FrmBaseList, IFormDetailData
    {
        /// <summary>
        /// 定义父窗体
        /// </summary>
        protected FrmBaseList parentList = null;

        /// <summary>
        /// 报表
        /// </summary>
        protected string infoName = "估值表明细数据";

        /// <summary>
        /// 对账结果
        /// </summary>
        protected Yss.Controls.TabPage resultItem = null;

        /// <summary>
        /// 声明service对象
        /// </summary>
        private IErResultService iErResultService = null;

        /// <summary>
        /// 声明service对象
        /// </summary>
        private IErBbInfoService infoService = null;

        /// <summary>
        /// 隐藏的列定义
        /// </summary>
        protected ArrayList col_Hidden = new ArrayList();

        /// <summary>
        /// 1
        /// </summary>
        private YssSelCombox cboResultType = new YssSelCombox();

        /// <summary>
        /// 获取或设置一个值，该值为主窗体传过来的数据。当该窗体为某一窗体的附属窗体时有效。
        /// </summary>
        private BasePojo _mainDataPojo;

        /// <summary>
        /// 获取或设置当前窗体是否已经被FrmBaseListWidthDetails窗体关联为子窗体
        /// </summary>
        private bool _hadBeenRelationed = false;

        /// <summary>
        /// 界面选中的
        /// </summary>
        List<ErBbInfo> dataList = null;

        /// <summary>
        /// 产品
        /// </summary>
        List<Port> portList = null;

        /// <summary>
        /// dataList
        /// </summary>
        public List<ErBbInfo> DataList
        {
            set { dataList = value; }
        }


        /// <summary>
        /// dataList
        /// </summary>
        public List<Port> PortList
        {
            set { portList = value; }
        }

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_ELEC_BATCHMX_S()
        {
            InitializeComponent();
            bUseMVCService = true;
            loadDataWhenFormLoad = false;
            ShowDetailNewPage = false;
            ShowPageInation = false;
        }

        /// <summary>
        /// 获取或设置一个值，该值为主窗体传过来的数据。当该窗体为某一窗体的附属窗体时有效。
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
        /// 设置父窗体
        /// </summary>
        /// <param name="parentList">父窗体</param>
        public void setParentList(FrmBaseList parentList)
        {
            this.parentList = parentList;
        }

        /// <summary>
        /// 自定义插入变量右键菜单项
        /// </summary>
        /// <returns>loItemParent</returns>
        private ToolStripMenuItem GetToolStripItems()
        {
            // 自定义
            ToolStripMenuItem itemParent = new ToolStripMenuItem("复制");
            itemParent.Click += new EventHandler(loItemParent_Click);

            return itemParent;
        }

        /// <summary>
        /// 点击插入变量items，打开变量设置界面
        /// </summary>
        /// <param name="sender">触发对象</param>
        /// <param name="e">事件</param>
        private void loItemParent_Click(object sender, EventArgs e)
        {
        }

        /// <summary>
        /// 初始化service
        /// </summary>
        protected override void initServiceMVC()
        {
            iErResultService = ServiceFactory.createService<IErResultService>();
            infoService = ServiceFactory.createService<IErBbInfoService>();
        }

        /// <summary>
        /// 初始化 创建4个标签页 报表，数据文件，对账结果,托管行数据
        /// </summary>
        private void initKTable()
        {
            btnBar.setButtonEnabled(ClsButtonName.ToolExport, true);
            btnBar.setButtonEnabled(ClsButtonName.BtnExportAllPages, true);
            btnBar.setButtonEnabled(ClsButtonName.BtnExportSetting, true);
            ////ErBbInfo info = this.parentList.tbMain.SelectedRow.Tag as ErBbInfo;
            initResultTab("估值表明细数据");
            loadResultTab();

        }
        
        /// <summary>
        /// 由子类复写加载数据
        /// </summary>
        protected virtual void loadBBTab()
        {
        }

        /// <summary>
        /// 对账结果
        /// </summary>
        /// <param name="name">Page标题</param>
        protected virtual void initResultTab(string name)
        {
            if (this.cboResultType.Items.Count > 0)
            {
                return;
            }

            ////resultItem = CreateTabItem("对账结果");
            resultItem = this.CreateTabItem(name);
            Label lable = new Label();
            lable.Location = new System.Drawing.Point(10, 10);
            lable.Size = new System.Drawing.Size(70, 21);
            lable.AutoSize = false;
            lable.BorderStyle = BorderStyle.None;
            lable.Text = "结果类型：";
            lable.BackColor = this._tabCtrlMain.BackColor;

            this.cboResultType.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboResultType.Border.Bottom = true;
            this.cboResultType.Border.Left = true;
            this.cboResultType.Border.Right = true;
            this.cboResultType.Border.Top = true;
            this.cboResultType.ClassName = "YssControls.YssSelCombox";
            this.cboResultType.DisplayName = "C_DV_NAME";
            this.cboResultType.DisplayValue = "C_DV_CODE";
            this.cboResultType.DllName = "YssControls.dll";
            this.cboResultType.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDown;
            this.cboResultType.FilterCond = "";
            this.cboResultType.IsFillDecimal = false;
            this.cboResultType.IsFocused = false;
            ////this.cboResultType.IsManage = false;
            this.cboResultType.IsRefresh = false;
            this.cboResultType.KTableTree = false;
            this.cboResultType.Location = new System.Drawing.Point(90, 7);
            this.cboResultType.Margin = new System.Windows.Forms.Padding(0);
            this.cboResultType.Name = "this.cboResultType";
            this.cboResultType.NodeID = "C_DV_CODE";
            this.cboResultType.Padding = new System.Windows.Forms.Padding(1, 3, 1, 3);
            this.cboResultType.Parameter = "C_DV_NAME";
            this.cboResultType.ParaNodeID = "";
            this.cboResultType.PasswordChar = '\0';
            this.cboResultType.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconOnly;
            this.cboResultType.PrefixForeColor = System.Drawing.SystemColors.ControlText;
            this.cboResultType.QueryByValues = false;
            this.cboResultType.QueryCond = "";
            this.cboResultType.QueryType = "";
            this.cboResultType.RequestEveryTime = false;
            this.cboResultType.ShowCheckBox = false;
            this.cboResultType.ShowColumnHeader = false;
            this.cboResultType.ShowItemsCount = true;
            this.cboResultType.ShowRefresh = false;
            this.cboResultType.Size = new System.Drawing.Size(119, 21);
            this.cboResultType.SortColumn = "C_DZ_NAME";
            this.cboResultType.SufixForeColor = System.Drawing.SystemColors.ControlText;
            this.cboResultType.TabIndex = 1;
            this.cboResultType.TextAlign = System.Windows.Forms.HorizontalAlignment.Left;
            this.cboResultType.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
            this.cboResultType.TipText = "";
            this.cboResultType.TriggerTextLength = 1;
            this.cboResultType.UseErrorTip = true;
            this.cboResultType.YssAssociaType = YssInformation.Support.Context.AssociaType.NULL;
            this.cboResultType.YssCaption = "结果类型";
            this.cboResultType.YssIsMust = false;
            this.cboResultType.YssLength = 20;
            this.cboResultType.YssNumeric = "";
            this.cboResultType.YssReadOnly = false;
            this.cboResultType.YssShowButton = true;
            this.cboResultType.Value = "ALL_DATA";
            this.cboResultType.Text = "所有数据";
            this.cboResultType.YssOnBeforeLoadData += new YssSelCombox.yssBeforeLoadData(this.cboResultType_YssOnBeforeLoadData);
            this.cboResultType.Location = new Point(75, 5);

            Table resultTable = this.tbMain.Clone() as Table;
            resultTable.Clear();
            resultTable.Location = new Point(0, 31);
            resultTable.Size = new Size(resultItem.Width, resultItem.Height - 31);
            resultTable.GridLine = Yss.KTable.Enums.GridLines.Both;
            resultTable.Anchor = AnchorStyles.Left | AnchorStyles.Right | AnchorStyles.Bottom | AnchorStyles.Top;
            resultTable.AutoScroll = true;
            resultItem.Controls.Add(resultTable);
            resultItem.Controls.Add(cboResultType);
            resultItem.Controls.Add(lable);
        }

        /// <summary>
        /// 初始化控件
        /// </summary>
        private void initConrol()
        {
        }

        /// <summary>
        /// 结果类型下拉框点击前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboResultType_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            e.IsCancel = true;
            YssSelCombox cboResultType = sender as YssSelCombox;
            if (cboResultType.Items.Count == 0)
            {
                cboResultType.Items.Clear();
                cboResultType.Parameter = "C_DV_NAME";
                cboResultType.DisplayName = "C_DV_NAME";
                cboResultType.DisplayValue = "C_DV_CODE";
                cboResultType.YssAssociaType = YssInformation.Support.Context.AssociaType.NULL;
                //// 构造词汇对象
                Vocabulary vocabulary = new Vocabulary();
                vocabulary.C_DV_CODE = "ALL_DATA";
                vocabulary.C_DV_NAME = "所有数据";
                KTableEntity tbEntity = new KTableEntity(vocabulary);
                cboResultType.Items.Add(tbEntity);

                vocabulary = new Vocabulary();
                vocabulary.C_DV_CODE = "CY_DATA";
                vocabulary.C_DV_NAME = "差异数据";
                tbEntity = new KTableEntity(vocabulary);
                cboResultType.Items.Add(tbEntity);
            }
        }

        /// <summary>
        /// 加载数据
        /// </summary>
        protected virtual void loadResultTab()
        {
            try
            {
                ////ErBbInfo info = this.parentList.tbMain.SelectedRow.Tag as ErBbInfo;
                List<BasePojo> pojoList = new List<BasePojo>();
                foreach (ErBbInfo info in dataList)
                {
                    Dictionary<string, string> paraDict = new Dictionary<string, string>();
                    paraDict.Add("dataClass", "ErResultQuery");
                    if (this.cboResultType.Value == null)
                    {
                        this.cboResultType.Value = "CY_DATA";
                    }

                    paraDict.Add("SHOW_TYPE", this.cboResultType.Value);
                    paraDict.Add("C_SN", info.C_SN);
                    paraDict.Add("C_ASS_CODE", info.C_ASS_CODE);
                    paraDict.Add("C_CHECK_FLAG", (info.C_RPT_TYPE == "01" ? "" : info.C_RPT_TYPE + "_") + info.C_FILE_TYPE);
                    paraDict.Add("D_DATE", info.D_DATE);
                    paraDict.Add("C_RPT_TYPE", info.C_RPT_TYPE);
                    ////paraDict.Add("C_RPT_TYPE", info.C_RPT_TYPE);
                    QueryRes res = this.iErResultService.queryByCondition(paraDict);
                    if (null != res && null != res.DataList)
                    {
                        List<BasePojo> list = res.DataList;
                        foreach (ErResult p in list)
                        {
                            if (string.IsNullOrEmpty(p.C_ASS_CODE))
                            {
                                p.C_ASS_CODE = info.C_ASS_CODE;
                            }
                            if (string.IsNullOrEmpty(p.D_TIME))
                            {
                                p.D_TIME = info.D_DATE;
                            }
                        }
                        pojoList.AddRange(list);
                    }
                }

                Table table = null;
                if (resultItem != null && resultItem == this._tabCtrlMain.SelectedTab)
                {
                     table = this.resultItem.Controls[0] as Table;
                }

                ElecEnums.ElecShowType showType = ElecEnums.ElecShowType.CHECK_ACT;


                ElecBBInfoUtil.init_BatchMx_Table_Column(table, false, true, showType);
                //getLeftCheckedPort


                if (pojoList.Count > 0)
                {
                    ////List<Port> portList = this.parentList.getLeftCheckedPort();
                    new ElecBBInfoUtil().drawBatchMxData(table, pojoList, this.cboResultType.Value, true, portList);
                }

                tbMain.Location = new Point(0, 31);
                tbMain.Size = new Size(resultItem.Width, resultItem.Height - 31);

                tbMain.Anchor = AnchorStyles.Left | AnchorStyles.Right | AnchorStyles.Bottom | AnchorStyles.Top;
                tbMain.Dock = table.Dock;
                tbMain = table;
                tbMain.Visible = true;
                ////STORY #77811 电子对账详细信息显示差异行数
                Yss.Controls.ToolStripLabel labStatuInfo = new Yss.Controls.ToolStripLabel();
                labStatuInfo.AutoSize = true;
                labStatuInfo.ControlAlign = System.Drawing.ContentAlignment.MiddleLeft;
                labStatuInfo.ForeColor = Color.Red;
                labStatuInfo.Text = "";
                    ////infoService.queryNumberOfRows(info);
                this.barFormStatus.Items.Clear();
                this.barFormStatus.Items.Add(labStatuInfo);
                this.barFormStatus.Items.Add(this.proBar);
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }
        }

        /// <summary>
        /// 窗体Load事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected virtual void Frm_ELEC_BATCHMX_S_Load(object sender, EventArgs e)
        {
            if (DesignMode)
            {
                return;
            }

            yssInitCtlAttr();
        }

        /// <summary>
        /// 功能：获取查询结果以供子类复写
        /// </summary>
        /// <param name="paraDict">参数集合</param>
        /// <param name="pageIns">分页信息</param>
        /// <returns>返回结果</returns>
        protected override QueryRes getQueryResultMVC(Dictionary<string, string> paraDict, PageInation pageIns)
        {
            if (resultItem != null && (resultItem == this._tabCtrlMain.SelectedTab))
            {
                loadResultTab();
            }

            return null;
        }

        /// <summary>
        /// 功能：获取查询结果以供子类复写
        /// </summary>
        /// <param name="paraDict">参数集合</param>
        /// <returns>返回结果</returns>
        protected override QueryRes getQueryResultMVC(Dictionary<string, string> paraDict)
        {
            return getQueryResultMVC(paraDict, null);
        }

        /// <summary>
        /// 初始化窗体与控件
        /// </summary>
        public override void yssInitCtlAttr()
        {
            initHiddenCols(ref col_Hidden);
            this.ReadTableColumnsFromConfig(tbMain, this.Text);
            tbMain.BackColor = ClsConstant.ColorBackGround;
            ////tbMain.AlternatingRowColor = ClsConstant.ColorInterlacing;
            this.ShowDetailNewPage = false;
            initKTable();
        }

        /// <summary>
        /// TabCtrl SelectionChanged
        /// </summary>
        /// <param name="e">TabPageChangeEventArgs</param>
        protected override void TabCtrlMain_SelectedIndexChanged(Yss.Controls.TabPageEventArgs e)
        {
            if (e.NewPage != null && e.NewPage.Controls.Count > 0)
            {
                if (e.NewPage == this.resultItem)
                {
                    btnBar.setAllButtonEnabled(true);
                    loadResultTab();
                }

            }

        }

        /// <summary>
        /// 屏蔽掉单击事件
        /// </summary>
        /// <param name="sender">事件源</param>
        /// <param name="e">参数</param>
        protected virtual void Frm_ELEC_BATCHMX_S_YssOnBeforeRowClick(object sender, YssBeforeOperEventArgs e)
        {
            e.IsCancel = true;
        }

        /// <summary>
        /// 屏蔽掉双击事件
        /// </summary>
        /// <param name="sender">事件源</param>
        /// <param name="e">参数</param>
        protected virtual void Frm_ELEC_BATCHMX_S_YssOnBeforeBrowClick(object sender, YssBeforeOperEventArgs e)
        {
            e.IsCancel = true;
        }

        /// <summary>
        /// 初始化隐然的列信息
        /// </summary>
        /// <param name="col_Hidden">集合</param>
        protected virtual void initHiddenCols(ref ArrayList col_Hidden)
        {
            col_Hidden.Clear();
        }

        /// <summary>
        /// 控制全屏/恢复FrmBaseList方法
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnBar_FullScreenClick(object sender, EventArgs e)
        {
            if (this.WindowState == FormWindowState.Maximized)
            {
                btnBar.setButtonImage(ClsButtonName.BtnFullScreen, FAST.Resource.Resource.btnFullScreen_L);
                btnBar.setButtonText(ClsButtonName.BtnFullScreen, "全屏");
                btnBar.setButtonToolTip(ClsButtonName.BtnFullScreen, "全屏");
                this.WindowState = FormWindowState.Normal;
            }
            else
            {
                btnBar.setButtonImage(ClsButtonName.BtnFullScreen, FAST.Resource.Resource.btnNormalScreen_L);
                btnBar.setButtonText(ClsButtonName.BtnFullScreen, "恢复");
                btnBar.setButtonToolTip(ClsButtonName.BtnFullScreen, "恢复");
                this.WindowState = FormWindowState.Maximized;
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
            Table table1 = this._tabCtrlMain.SelectedTab.Controls[0] as Table;  ////防止导出失败
            if (table1 != null)
            {
                table = table1;
                this.tbMain = table;
            }
            string fileType = "双估值表";
            ErBbInfo pojo = this.dataList[0];
            if ("1011".Equals(pojo.C_FILE_TYPE)) {
                fileType = "估值表";
            } 
            Yss.FileProcessor.ExportSetting exportSetting = base.ResetTableMainExportSetting(table);
            ////if (string.IsNullOrEmpty(exportSetting.ExportSubTitle))
            ////{
            ////    exportSetting.ExportSubTitle = (license.C_User_Name == "" ? "深圳市赢时胜信息技术股份有限公司" : license.C_User_Name) + "_专用表";
            ////}

            ////if (string.IsNullOrEmpty(exportSetting.ExportDescription)) {
            ////    exportSetting.ExportDescription = "日期：" + dtDate + " 至 " + dtDate;
            ////}

            ////if (string.IsNullOrEmpty(exportSetting.ExportName))
            ////{
            ////    exportSetting.ExportName = "电子对账详细信息_" + this._tabCtrlMain.SelectedTab.Text + "_" + DateTime.Now.ToString();
            ////}

            ////if ((exportSetting.ExportSubTitle != null && (exportSetting.ExportSubTitle.Contains("[托管行名称]") || exportSetting.ExportSubTitle.Contains("[托管行代码]")))
            ////    || (exportSetting.ExportName != null && (exportSetting.ExportName.Contains("[托管行名称]") || exportSetting.ExportName.Contains("[托管行代码]"))))
            ////{
            ////    Dictionary<string, string> map = this.iErResultService.getPortRelaOrgData(pojo.C_PORT_CODE);
            ////    if (map.Count > 0)
            ////    {
            ////        tghCode = map[pojo.C_PORT_CODE];
            ////    }
            ////    OrgCache orgCache = CacheFactory.CreateCache(CacheGroup.ORG) as OrgCache;
            ////    Org org = orgCache.getOrgInfoDataByOrgCode(tghCode) as Org;
            ////    if (org != null)
            ////    {
            ////        tghName = org.C_ORG_NAME;
            ////    }

            ////}
            ////exportSetting.ExportName = exportSetting.ExportName + "(" + dtDate + "至" + dtDate + ")";

            ////文件名转义
            ////exportSetting.ExportNameTranslate.Add("托管行代码", tghCode);

            ////exportSetting.ExportNameTranslate.Add("托管行名称", tghName);

            ////////////副标题转义
            ////exportSetting.ExportSubTitleTranslate.Add("托管行代码", tghCode);
            ////exportSetting.ExportSubTitleTranslate.Add("托管行名称", tghName);

            ////////标题转义
            ////exportSetting.ExportTitleTranslate.Add("托管行代码", tghCode);
            ////exportSetting.ExportTitleTranslate.Add("托管行名称", tghName);
            ////exportSetting.ExportTitleTranslate.Add("日期", DateTime.Now.ToString());

            ////exportSetting.ExportDescription = "日期：" + dtDate + " 至 " + dtDate;

            //exportSetting.ExportSubTitle = (license.C_User_Name == "" ? "深圳市赢时胜信息技术股份有限公司" : license.C_User_Name) + "_专用表";
            //exportSetting.ExportDescription = "日期：" + dtDate + " 至 " + dtDate;

            //////这里为Table设置，是为了防止用户点击导出设置时名字不能带日期
            ///////添加时间防止，重复导出过多提示 BUG 114827 weijj 20150703
            string nowTime = DateTime.Now.ToShortDateString().ToString();    // 2008-9-4
            exportSetting.ExportName = "电子对账管理_批量导出_" + fileType + "_" + nowTime;
            exportSetting.ExportTitle = "电子对账管理_批量导出_" + fileType + "_" + nowTime;
            exportSetting.ExportSubTitle = exportSetting.ExportSubTitle;
            exportSetting.ExportDescription = exportSetting.ExportDescription;
            return exportSetting;
        }

        /// <summary>
        /// 获取A区结果集信息，对象为勾选的记录
        /// </summary>
        /// <returns>List</returns>
        public List<Port> getLeftCheckedPort()
        {
            List<Port> list = new List<Port>();

            if (TableLeftMain.Rows.Count > 0)
            {
                //// 父级行
                foreach (Row row in TableLeftMain.Rows)
                {
                    this.getLeftChildCheckedPort(list, row);
                }
            }

            return list;
        }

        /// <summary>
        /// 添加被选中子行到列表中
        /// </summary>
        /// <param name="list">list</param>
        /// <param name="row">row</param>
        private void getLeftChildCheckedPort(List<Port> list, Row row)
        {
            if (row.SubRows.Count > 0)
            {
                if (row.Checked)
                {
                    list.Add(row.Tag as Port);
                }

                foreach (Row subRow in row.SubRows)
                {
                    this.getLeftChildCheckedPort(list, subRow);
                }
            }
            else
            {
                if (row.Checked)
                {
                    list.Add(row.Tag as Port);
                }
            }
        }

        #region IFormDetailData 成员

        /// <summary>
        /// 允许加载明细数据
        /// </summary>
        /// <param name="mainData">主窗体传过来的数据</param>
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
        }

        /// <summary>
        /// 加载明细数据
        /// </summary>
        /// <param name="mainData">主窗体数据</param>
        public void LoadDetailData(BasePojo mainData)
        {
            throw new NotImplementedException();
        }

        #endregion IFormDetailData 成员
    }
}