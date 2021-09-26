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
    public partial class Frm_ELEC_BBINFO_S : FrmBaseList, IFormDetailData
    {
        /// <summary>
        /// 定义父窗体
        /// </summary>
        protected FrmBaseList parentList = null;

        /// <summary>
        /// 报表
        /// </summary>
        protected string infoName = "已生成";

        /// <summary>
        /// 报表
        /// </summary>
        protected Yss.Controls.TabPage infoItem = null;

        /// <summary>
        /// 数据
        /// </summary>
        protected Yss.Controls.TabPage dataItem = null;

        /// <summary>
        /// 对账结果
        /// </summary>
        protected Yss.Controls.TabPage resultItem = null;

        /// <summary>
        /// 对账结果
        /// </summary>
        QueryRes res = null;

        /// <summary>
        /// 错误原因
        /// </summary>
        protected Yss.Controls.TabPage errItem = null;

        /// <summary>
        /// 托管行数据
        /// </summary>
        protected Yss.Controls.TabPage tghItem = null;

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

        /////// <summary>
        /////// 定义服务类
        /////// </summary>
        ////private IBalService clsIBalService = null;

        /// <summary>
        /// 1
        /// </summary>
        private YssSelCombox cboResultType = new YssSelCombox();

        /// <summary>
        /// 显示级别
        /// </summary>
        private YssSelCombox cboXsjb = new YssSelCombox();

        /// <summary>
        /// 对账一致时，复制数据
        /// </summary>
        private Yss.Controls.CheckBox chkCopySameData = new Yss.Controls.CheckBox();

        //STORY #41248 【南方基金】电子对账反馈结果中的差异数据下拉框扩展 新增显示级别下拉框
        /// <summary>
        /// 显示级别词汇字典
        /// </summary>
        private Dictionary<string, string> LevelDict = new Dictionary<string, string>();

        /// <summary>
        /// 获取或设置一个值，该值为主窗体传过来的数据。当该窗体为某一窗体的附属窗体时有效。
        /// </summary>
        private BasePojo _mainDataPojo;

        /// <summary>
        /// 获取或设置当前窗体是否已经被FrmBaseListWidthDetails窗体关联为子窗体
        /// </summary>
        private bool _hadBeenRelationed = false;

        /// <summary>
        /// 组合代码
        /// edit by qiantao STORY #83025 产品估值参数控制实收资本小数位 
        /// </summary>
        private string c_PORT_CODE = "";

        /// <summary>
        /// 组合代码
        /// edit by qiantao STORY #83025 产品估值参数控制实收资本小数位 
        /// </summary>
        public string C_PORT_CODE
        {
            get { return c_PORT_CODE; }
            set { c_PORT_CODE = value; }
        }

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_ELEC_BBINFO_S()
        {
            InitializeComponent();
            bUseMVCService = true;
            loadDataWhenFormLoad = false;
            ShowDetailNewPage = false;
            ShowPageInation = false;
            //STORY #51518 南方基金-“电子对账详细信息”只显示存在差异的数据    复选框记忆功能，默认为未勾选
            this.chkCopySameData.Checked = Convert.ToBoolean(IniFileOperator.ReadIniData("COPYOWNDATA", "CHECK_STATE", "False", Application.StartupPath + "/config/app.ini"));
     
            ////this.btnBar.getButton(ClsButtonName.BtnFullScreen).Owner.Click += new EventHandler(btnBar_FullScreenClick);
            ////this.YssOnBeforeRowClick += new BeforeRowClick(Frm_ELEC_BBINFO_S_YssOnBeforeRowClick);
            ////this.YssOnBeforeBrowClick += new BeforeBrowClick(Frm_ELEC_BBINFO_S_YssOnBeforeBrowClick);
            ////this.Load += new EventHandler(Frm_ELEC_BBINFO_S_Load);
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
            ErBbInfo info = this.parentList.tbMain.SelectedRow.Tag as ErBbInfo;
            initBBTab();

            if (!info.C_STATE.Equals("ER_SEND"))
            {
                ////发送标签
                string name = "发送成功";
                if (info.C_STATE.Equals("ER_SENDED_FAIL"))
                {
                    name = "发送失败";
                }
                else if (info.C_STATE.Equals("ER_SENDED"))
                {
                    name = "已发送";
                }

                initDataTab(name);
            }

            initTghTab();
            ////loadResultTab();

            ////判断如果是已接收对账结果，显示对账结果
            if (info.C_STATE.Equals("ER_ACCECPED"))
            {
                initResultTab("对账一致");
                loadResultTab();
            }
            else if (info.C_STATE.Equals("ER_IDENTICAL"))
            {
                initResultTab("对账不一致");
                loadResultTab();
            }
            else if (info.C_STATE.Equals("ER_SEND"))
            {
                this._tabCtrlMain.SelectedTab = this.infoItem;
                loadBBTab();
                tbMain.Dock = DockStyle.Fill;
                tbMain.Visible = true;
            }
            else
            {
                this._tabCtrlMain.SelectedTab = this.dataItem;
                ////loadDataTab();
            }
        }

        /// <summary>
        /// 数据文件
        /// </summary>
        protected virtual void initErrTab()
        {
            WebBrowser dataBrowser = new WebBrowser();
            dataBrowser.Dock = System.Windows.Forms.DockStyle.Fill;
            dataBrowser.TabIndex = 1;
            dataBrowser.Name = "dataBrowser";
            errItem = this.CreateTabItem("失败原因");
            errItem.TabCloseButtonVisibleWithParent = false;
            errItem.TabCloseButtonVisible = false;
            errItem.Controls.Add(dataBrowser);
        }

        /// <summary>
        /// 加载数据
        /// </summary>
        protected virtual void loadErrTab()
        {
            ErBbInfo info = this.parentList.tbMain.SelectedRow.Tag as ErBbInfo;
            WebBrowser dataBrowser = this.errItem.Controls[0] as WebBrowser;

            if (dataBrowser.DocumentText == null || dataBrowser.DocumentText.Trim().Length == 0)
            {
                dataBrowser.Dock = System.Windows.Forms.DockStyle.Fill;
                dataBrowser.TabIndex = 1;
                dataBrowser.DocumentText = info.C_ERR_INFO;
                dataBrowser.Visible = true;
            }
        }

        /////// <summary>
        /////// 加载数据
        /////// </summary>
        ////protected virtual void loadTghTab()
        ////{
        ////    ErBbInfo info = this.parentList.tbMain.SelectedRow.Tag as ErBbInfo;
        ////    WebBrowser dataBrowser = this.tghItem.Controls[0] as WebBrowser;

        ////    if (dataBrowser.DocumentText == null || dataBrowser.DocumentText.Trim().Length == 0)
        ////    {
        ////        dataBrowser.Dock = System.Windows.Forms.DockStyle.Fill;
        ////        dataBrowser.TabIndex = 1;
        ////        dataBrowser.DocumentText = info.C_ERR_INFO;
        ////        dataBrowser.Visible = true;
        ////    }
        ////}

        /// <summary>
        /// 数据文件
        /// </summary>
        /// <param name="name">标题名</param>
        protected virtual void initDataTab(string name)
        {
            Table dataTable = new Table();
            dataTable.Dock = System.Windows.Forms.DockStyle.Fill;
            WebBrowser dataBrowser = new WebBrowser();
            dataBrowser.Dock = System.Windows.Forms.DockStyle.Fill;
            dataBrowser.Size = new System.Drawing.Size(493, 326);
            dataBrowser.TabIndex = 1;
            dataBrowser.Name = "dataBrowser";
            dataTable.Controls.Add(dataBrowser);
            ////dataItem = this.CreateTabItem("报文数据");
            ////发送成功或者发送失败
            dataItem = this.CreateTabItem(name);
            dataItem.TabCloseButtonVisibleWithParent = false;
            dataItem.TabCloseButtonVisible = false;
            dataItem.Controls.Add(dataTable);
        }

        /// <summary>
        /// 加载数据
        /// </summary>
        protected virtual void loadDataTab()
        {
            WebBrowser dataBrowser = this.dataItem.Controls[0].Controls[2] as WebBrowser;
            if (dataBrowser.Url == null || dataBrowser.Url.ToString().Trim().Length == 0)
            {
                dataBrowser.Dock = System.Windows.Forms.DockStyle.Fill;
                dataBrowser.Size = new System.Drawing.Size(493, 326);
                dataBrowser.TabIndex = 1;
                ErBbInfo info = this.parentList.tbMain.SelectedRow.Tag as ErBbInfo;
                string content = infoService.getXmlFile(info);
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

                dataBrowser.Navigate(tmpFileName);
                dataBrowser.Visible = true;
            }
        }

        /// <summary>
        /// 报表
        /// </summary>
        protected virtual void initBBTab()
        {
            Table table = this.tbMain;
            infoItem = this.CreateTabItem(infoName);
            infoItem.TabCloseButtonVisibleWithParent = true;
            infoItem.TabCloseButtonVisible = true;
            infoItem.Controls.Add(table);

            ////bbTable.ContextMenu.MenuItems;
        }

        /// <summary>
        /// 报表
        /// </summary>
        protected void initTghTab()
        {
            Table table = this.tbMain.Clone() as Table;
            tghItem = this.CreateTabItem("托管行数据");
            tghItem.TabCloseButtonVisibleWithParent = false;
            tghItem.TabCloseButtonVisible = false;
            tghItem.Controls.Add(table);
            ElecBBInfoUtil.init_Tgh_Table_Column(table, false, true, ElecEnums.ElecShowType.CHECK_ACT);

            ////tghItem.Visible = true;
            ////bbTable.ContextMenu.MenuItems;
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

            Label levelLable = new Label();
            levelLable.Location = new System.Drawing.Point(205, 10);
            levelLable.Size = new System.Drawing.Size(70, 21);
            levelLable.AutoSize = false;
            levelLable.BorderStyle = BorderStyle.None;
            levelLable.Text = "显示级别：";
            levelLable.BackColor = this._tabCtrlMain.BackColor;
            this.cboXsjb.Border.BorderColor = System.Drawing.Color.FromArgb(((int)(((byte)(181)))), ((int)(((byte)(184)))), ((int)(((byte)(201)))));
            this.cboXsjb.Border.Bottom = true;
            this.cboXsjb.Border.Left = true;
            this.cboXsjb.Border.Right = true;
            this.cboXsjb.Border.Top = true;
            this.cboXsjb.FilterCond = "";
            this.cboXsjb.Location = new System.Drawing.Point(275, 5);
            this.cboXsjb.Margin = new System.Windows.Forms.Padding(0);
            this.cboXsjb.MethodInfo = null;
            this.cboXsjb.Name = "cboXsjb";
            this.cboXsjb.QueryCond = "";
            this.cboXsjb.QueryType = "";
            this.cboXsjb.Size = new System.Drawing.Size(119, 21);
            this.cboXsjb.TabIndex = 2;
            this.cboXsjb.UseCustomerParameter = false;
            this.cboXsjb.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
            this.cboXsjb.BeforeDropDownClick += new Yss.KRichEx.AutoFilter.Events.DropDownEventHandler(this.cboXsjb_BeforeDropDownClick);
            this.cboXsjb.SelectedValueChanged += new System.EventHandler(this.cboXsjb_SelectedValueChanged);

            this.chkCopySameData.AutoSize = false;
            this.chkCopySameData.Location = new System.Drawing.Point(400, 5);
            this.chkCopySameData.Name = "chkCopySameData";
            this.chkCopySameData.Size = new System.Drawing.Size(199, 22);
            this.chkCopySameData.TabIndex = 3;
            this.chkCopySameData.Text = "对账一致时自动填写对方数据";
            this.chkCopySameData.CheckedChanged += new System.EventHandler(this.chkCopySameData_CheckedChanged);


            Table resultTable = this.tbMain.Clone() as Table;
            resultTable.Clear();
            resultTable.Location = new Point(0, 31);
            resultTable.Size = new Size(resultItem.Width, resultItem.Height - 31);
            resultTable.GridLine = Yss.KTable.Enums.GridLines.Both;
            resultTable.Anchor = AnchorStyles.Left | AnchorStyles.Right | AnchorStyles.Bottom | AnchorStyles.Top;
            resultTable.AutoScroll = true;
            resultItem.Controls.Add(resultTable);
            resultItem.Controls.Add(cboResultType);
            resultItem.Controls.Add(cboXsjb);
            resultItem.Controls.Add(chkCopySameData);
            resultItem.Controls.Add(lable);
            resultItem.Controls.Add(levelLable);
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
                ////cboResultType_YssOnBeforeLoadData(this.cboResultType,new YssBeforeOperEventArgs());
                ErBbInfo info = this.parentList.tbMain.SelectedRow.Tag as ErBbInfo;
                Dictionary<string, string> paraDict = new Dictionary<string, string>();
                paraDict.Add("dataClass", "ErResultQuery");

                if (resultItem != null && resultItem == this._tabCtrlMain.SelectedTab)
                {
                    if (this.cboResultType.Value == null)
                    {
                        this.cboResultType.Value = "CY_DATA";
                    }

                    paraDict.Add("SHOW_TYPE", this.cboResultType.Value);
                }
                else
                {
                    paraDict.Add("SHOW_TYPE", "ALL_DATA");
                }

                paraDict.Add("C_SN", info.C_SN);
                paraDict.Add("C_ASS_CODE", info.C_ASS_CODE);
                paraDict.Add("C_CHECK_FLAG", (info.C_RPT_TYPE == "01" ? "" : info.C_RPT_TYPE + "_") + info.C_FILE_TYPE);
                paraDict.Add("D_DATE", info.D_DATE);
                paraDict.Add("C_RPT_TYPE", info.C_RPT_TYPE);
                ////paraDict.Add("C_RPT_TYPE", info.C_RPT_TYPE);
                res = this.iErResultService.queryByCondition(paraDict);

                Table table = null;
                if (resultItem != null && resultItem == this._tabCtrlMain.SelectedTab)
                {
                     table = this.resultItem.Controls[0] as Table;
                }
                else {
                     table = this.tghItem.Controls[0] as Table;
                }
                ElecEnums.ElecShowType showType = ElecEnums.ElecShowType.CHECK_ACT;
                if (info.C_FILE_TYPE.Equals("1031"))
                {
                    showType = ElecEnums.ElecShowType.CHECK_KM;
                }
                else if (info.C_FILE_TYPE.Equals("1001"))
                {
                    showType = ElecEnums.ElecShowType.CHECK_STO;
                }
                else if (info.C_FILE_TYPE.Equals("1013"))
                {
                    showType = ElecEnums.ElecShowType.CHECK_DBLGZ;
                }
                else if (info.C_FILE_TYPE.Equals("1701") || info.C_FILE_TYPE.Equals("1711"))
                {
                    showType = ElecEnums.ElecShowType.CHECK_ZCFZ;
                }
                else if (info.C_FILE_TYPE.Equals("1801") || info.C_FILE_TYPE.Equals("1811"))
                {
                    showType = ElecEnums.ElecShowType.CHECK_LR;
                }
                else if (info.C_FILE_TYPE.Equals("1901"))
                {
                    showType = ElecEnums.ElecShowType.CHECK_SYZQY;
                }
                else if (info.C_FILE_TYPE.Equals("1903"))
                {
                    showType = ElecEnums.ElecShowType.CHECK_JZCBD;
                }

                if (resultItem != null && resultItem == this._tabCtrlMain.SelectedTab)
                {
                    ElecBBInfoUtil.init_Table_Column(table, false, true, showType);
                }
                else
                {
                    ElecBBInfoUtil.init_Tgh_Table_Column(table, false, true, showType);
                }
                

                if (null != res && null != res.DataList && res.DataList.Count > 0)
                {
                    ////STORY #41248 【南方基金】电子对账反馈结果中的差异数据下拉框扩展 修改为树形
                    
                    if (resultItem != null && resultItem == this._tabCtrlMain.SelectedTab)
                    {
                        new ElecBBInfoUtil().drawResultData(table, res.DataList, this.cboResultType.Value, true);
                    }
                    else
                    {
                        new ElecBBInfoUtil().drawTghData(table, res.DataList, "ALL_DATA", true);
                    }
                    ////ElecBBInfoUtil.drawResultData(table, res.DataList);
                }
                ////by weijj bug120728 20151112
                if (resultItem != null && resultItem == this._tabCtrlMain.SelectedTab)
                {
                    tbMain.Location = new Point(0, 31);
                    tbMain.Size = new Size(resultItem.Width, resultItem.Height - 31);
                }
                else if (tghItem != null && tghItem == this._tabCtrlMain.SelectedTab)
                {
                    tbMain.Location = new Point(0, 0);
                    tbMain.Size = new Size(tghItem.Width, tghItem.Height);
                }
           
                tbMain.Anchor = AnchorStyles.Left | AnchorStyles.Right | AnchorStyles.Bottom | AnchorStyles.Top;
                tbMain.Dock = table.Dock;
                tbMain = table;
                tbMain.Visible = true;
                ////STORY #77811 电子对账详细信息显示差异行数
                Yss.Controls.ToolStripLabel labStatuInfo = new Yss.Controls.ToolStripLabel();
                labStatuInfo.AutoSize = true;
                labStatuInfo.ControlAlign = System.Drawing.ContentAlignment.MiddleLeft;
                labStatuInfo.ForeColor = Color.Red;
                labStatuInfo.Text = infoService.queryNumberOfRows(info);
                this.barFormStatus.Items.Clear();
                this.barFormStatus.Items.Add(labStatuInfo);
                this.barFormStatus.Items.Add(this.proBar);
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }
        }

        ///// <summary>
        ///// 加载数据
        ///// </summary>
        //protected virtual void loadTghTab()
        //{
        //    try
        //    {
        //        ////cboResultType_YssOnBeforeLoadData(this.cboResultType,new YssBeforeOperEventArgs());
        //        ErBbInfo info = this.parentList.tbMain.SelectedRow.Tag as ErBbInfo;
        //        //Dictionary<string, string> paraDict = new Dictionary<string, string>();
        //        //paraDict.Add("dataClass", "ErResultQuery");
        //        ////if (this.cboResultType.Value == null)
        //        ////{
        //        ////    this.cboResultType.Value = "CY_DATA";
        //        ////}

        //        ////paraDict.Add("SHOW_TYPE", this.cboResultType.Value);
        //        //paraDict.Add("C_SN", info.C_SN);
        //        //paraDict.Add("C_ASS_CODE", info.C_ASS_CODE);
        //        //paraDict.Add("C_CHECK_FLAG", (info.C_RPT_TYPE == "01" ? "" : info.C_RPT_TYPE + "_") + info.C_FILE_TYPE);
        //        //paraDict.Add("D_DATE", info.D_DATE);
        //        //paraDict.Add("C_RPT_TYPE", info.C_RPT_TYPE);
        //        //////paraDict.Add("C_RPT_TYPE", info.C_RPT_TYPE);
        //        //QueryRes res = this.iErResultService.queryByCondition(paraDict);

        //        Table table = this.tghItem.Controls[0] as Table;
        //        ElecEnums.ElecShowType showType = ElecEnums.ElecShowType.CHECK_ACT;
        //        if (info.C_FILE_TYPE.Equals("1031"))
        //        {
        //            showType = ElecEnums.ElecShowType.CHECK_KM;
        //        }
        //        else if (info.C_FILE_TYPE.Equals("1001"))
        //        {
        //            showType = ElecEnums.ElecShowType.CHECK_STO;
        //        }
        //        else if (info.C_FILE_TYPE.Equals("1013"))
        //        {
        //            showType = ElecEnums.ElecShowType.CHECK_DBLGZ;
        //        }
        //        else if (info.C_FILE_TYPE.Equals("1701"))
        //        {
        //            showType = ElecEnums.ElecShowType.CHECK_ZCFZ;
        //        }
        //        else if (info.C_FILE_TYPE.Equals("1801"))
        //        {
        //            showType = ElecEnums.ElecShowType.CHECK_LR;
        //        }
        //        else if (info.C_FILE_TYPE.Equals("1901"))
        //        {
        //            showType = ElecEnums.ElecShowType.CHECK_SYZQY;
        //        }
        //        else if (info.C_FILE_TYPE.Equals("1903"))
        //        {
        //            showType = ElecEnums.ElecShowType.CHECK_JZCBD;
        //        }

        //        ElecBBInfoUtil.init_Tgh_Table_Column(table, false, true, showType);

        //        if (null != res && null != res.DataList && res.DataList.Count > 0)
        //        {
        //            ////STORY #41248 【南方基金】电子对账反馈结果中的差异数据下拉框扩展 修改为树形
        //            new ElecBBInfoUtil().drawTghData(table, res.DataList, this.cboResultType.Value, true);
        //            ////ElecBBInfoUtil.drawResultData(table, res.DataList);
        //        }
        //        ////by weijj bug120728 20151112
        //        tbMain.Location = new Point(0, 0);
        //        tbMain.Size = new Size(resultItem.Width, resultItem.Height);
        //        tbMain.Anchor = AnchorStyles.Left | AnchorStyles.Right | AnchorStyles.Bottom | AnchorStyles.Top;
        //        tbMain.Dock = table.Dock;
        //        tbMain = table;
        //        tbMain.Visible = true;
        //        ////STORY #77811 电子对账详细信息显示差异行数
        //        Yss.Controls.ToolStripLabel labStatuInfo = new Yss.Controls.ToolStripLabel();
        //        labStatuInfo.AutoSize = true;
        //        labStatuInfo.ControlAlign = System.Drawing.ContentAlignment.MiddleLeft;
        //        labStatuInfo.ForeColor = Color.Red;
        //        labStatuInfo.Text = infoService.queryNumberOfRows(info);
        //        this.barFormStatus.Items.Clear();
        //        this.barFormStatus.Items.Add(labStatuInfo);
        //        this.barFormStatus.Items.Add(this.proBar);
        //    }
        //    catch (Exception ex)
        //    {
        //        YssMessageBox.ShowCommonInfo(ex.Message);
        //    }
        //}


        /// <summary>
        /// 窗体Load事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected virtual void Frm_ELEC_BBINFO_S_Load(object sender, EventArgs e)
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
            if (resultItem != null && (resultItem == this._tabCtrlMain.SelectedTab || tghItem == this._tabCtrlMain.SelectedTab))
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
                if (e.NewPage != this.dataItem)
                {
                    tbMain = e.NewPage.Controls[0] as Yss.KTable.Models.Table;
                    tbMain.Visible = true;
                    tbMain.Dock = DockStyle.Fill;
                }

                if (e.NewPage == this.resultItem)
                {
                    btnBar.setAllButtonEnabled(true);
                    loadResultTab();
                }
                else if (e.NewPage == this.dataItem)
                {
                    loadDataTab();
                    btnBar.setButtonDisabled(ClsButtonName.ToolExport);
                    btnBar.Refresh();
                }
                else if (e.NewPage == this.infoItem)
                {
                    btnBar.setAllButtonEnabled(true);
                    loadBBTab();
                }
                else if (e.NewPage == this.errItem)
                {
                    loadErrTab();
                }
                else if (e.NewPage == this.tghItem)
                {
                    loadResultTab();
                }
            }

            ////this.DetailSelectedPageChanged();
        }

        /// <summary>
        /// 屏蔽掉单击事件
        /// </summary>
        /// <param name="sender">事件源</param>
        /// <param name="e">参数</param>
        protected virtual void Frm_ELEC_BBINFO_S_YssOnBeforeRowClick(object sender, YssBeforeOperEventArgs e)
        {
            e.IsCancel = true;
        }

        /// <summary>
        /// 屏蔽掉双击事件
        /// </summary>
        /// <param name="sender">事件源</param>
        /// <param name="e">参数</param>
        protected virtual void Frm_ELEC_BBINFO_S_YssOnBeforeBrowClick(object sender, YssBeforeOperEventArgs e)
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
        /// 加载明细数据
        /// </summary>
        /// <param name="lstBalStr">明细数据字符串</param>
        ////protected virtual void loadDetail(List<string> lstBalStr)
        ////{
        ////    ////string[] arrSumStr = Regex.Split(c_Detail_Str, ClsConstant.YSS_LINESPLITMARK);
        ////    foreach (string balStr in lstBalStr)
        ////    {
        ////        if (balStr.Length == 0)
        ////        {
        ////            continue;
        ////        }

        ////        Balance cls_Bal = new Balance();
        ////        BalanceTools.Parse(balStr, cls_Bal);

        ////        loadRowData(cls_Bal);
        ////    }
        ////}

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

        /////// <summary>
        /////// 加载按科目类别汇总的数据
        /////// </summary>
        /////// <param name="lstBalStr">汇总的数据字符串列表</param>
        ////protected virtual void loadSumCls(List<string> lstBalStr)
        ////{
        ////    loadSum(c_SumCls_Str);
        ////}

        /////// <summary>
        /////// 加载按借贷方向汇总的数据
        /////// </summary>
        /////// <param name="lstBalStr">汇总的数据字符串列表</param>
        ////protected virtual void loadSum(List<string> lstBalStr)
        ////{
        ////    ////string[] arrSumStr = Regex.Split(c_Sum_Str, ClsConstant.YSS_LINESPLITMARK);
        ////    foreach (string balStr in lstBalStr)
        ////    {
        ////        if (balStr.Length == 0)
        ////        {
        ////            continue;
        ////        }

        ////        Cls_BM_BAL cls_Bal = new Cls_BM_BAL();
        ////        cls_Bal.treeNodeParse(balStr);

        ////        cls_Bal.C_KM_CODE = "";

        ////        loadRowData(cls_Bal);
        ////    }
        ////}

        /////// <summary>
        /////// 加载数据到KTable中
        /////// </summary>
        ////protected virtual void loadDataToKTable()
        ////{
        ////}

        /////// <summary>
        /////// 加载一行数据到主区域
        /////// </summary>
        /////// <param name="cls_Bal">单行的POJO类</param>
        ////protected virtual void loadRowData(Balance cls_Bal)
        ////{
        ////}

        ////#region 行的显示与收缩功能

        /////// <summary>
        /////// 行的显示与收缩功能
        /////// </summary>
        /////// <param name="n_Level">级别号</param>
        ////private void setRow_ShowOrShrink(int n_Level)
        ////{
        ////    if (n_Level < 1)
        ////    {
        ////        return;
        ////    }

        ////    Dictionary<string, Yss.KTable.Models.Row> c_Coll = tbMain.Tag as Dictionary<string, Yss.KTable.Models.Row>;

        ////    foreach (Yss.KTable.Models.Row row in c_Coll.Values)
        ////    {
        ////        ClsBaseTreeNode cls_Bal = row.Tag as ClsBaseTreeNode;
        ////        if (cls_Bal.N_Level >= n_Level)
        ////        {
        ////            tbMain.CollapseAll(row); // 收起
        ////        }
        ////        else
        ////        {
        ////            tbMain.ExpandAll(row); // 展开
        ////        }
        ////    }

        ////    tbMain.Refresh();

        ////}

        ////#endregion

        /////// <summary>
        /////// 级别下拉事件
        /////// </summary>
        /////// <param name="sender">事件源</param>
        /////// <param name="e">参数</param>
        ////protected virtual void cboLevel_SelectedValueChanged(object sender, EventArgs e)
        ////{
        ////    ////clsInterface.yssShowLevel(tbMain, Convert.ToInt32(cboLevel.Value));
        ////}

        /////// <summary>
        /////// 范围的下拉事件-因为范围的下拉选项不是从后台采集的，所以这里要屏蔽掉
        /////// </summary>
        /////// <param name="sender">下拉框</param>
        /////// <param name="e">事件参数</param>
        ////private void cboDisArea_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        ////{
        ////    e.Cancel = true;
        ////}

        /////// <summary>
        /////// 范围的下拉事件
        /////// </summary>
        /////// <param name="sender">事件源</param>
        /////// <param name="e">参数</param>
        ////protected virtual void cboDisArea_TextChanged(object sender, EventArgs e)
        ////{
        ////    string c_Temp = "";
        ////    try
        ////    {
        ////        if (cboDisArea.Value == null)
        ////        {
        ////            return;
        ////        }

        ////        c_Temp = cboDisArea.Value as string;

        ////        Dictionary<string, Yss.KTable.Models.Row> c_Coll = tbMain.Tag as Dictionary<string, Yss.KTable.Models.Row>;

        ////        foreach (Yss.KTable.Models.Row row in c_Coll.Values)
        ////        {
        ////            Balance cls_Bal = row.Tag as Balance;
        ////            bool bPm_Ini = true;
        ////            bool bPm_Ini_Y = true;
        ////            bool bPm_Bal = true;
        ////            row.Visible = true;
        ////            if (c_Temp.IndexOf("show_Ini_B") > -1)
        ////            {
        ////                bPm_Ini = ClsFunction.toDouble(Regex.Split(cls_Bal.C_PM_Ini, ";")[0]) == 0 ? false : true;
        ////            }

        ////            if (c_Temp.IndexOf("show_Ini_Y") > -1)
        ////            {
        ////                bPm_Ini_Y = ClsFunction.toDouble(Regex.Split(cls_Bal.C_PM_Ini_Y, ";")[0]) == 0 ? false : true;
        ////            }

        ////            if (c_Temp.IndexOf("show_Bal") > -1)
        ////            {
        ////                bPm_Bal = ClsFunction.toDouble(Regex.Split(cls_Bal.C_PM_Bal, ";")[0]) == 0 ? false : true;
        ////            }

        ////            row.Visible = ((c_Temp.IndexOf("show_Ini_B") > -1) ? bPm_Ini : false)
        ////                || ((c_Temp.IndexOf("show_Ini_Y") > -1) ? bPm_Ini_Y : false)
        ////                || ((c_Temp.IndexOf("show_Bal") > -1) ? bPm_Bal : false)
        ////                || (c_Temp.Trim().Length == 0 ? true : false);
        ////        }

        ////        tbMain.Refresh();
        ////    }
        ////    catch (Exception ex)
        ////    {
        ////        ////YssMessageBox.ShowError(ex.Message);
        ////        YssMessageBox.ShowCommonInfo(ex.Message);
        ////    }
        ////}

        /////// <summary>
        /////// 初始化日期控件
        /////// </summary>
        ////private void initDate()
        ////{
        ////    DateTime now = DateTime.Now;
        ////    this.dtDate.setDateTime(new DateTime(now.Year, now.Month, 1), now);
        ////}

        /////// <summary>
        /////// 科目的下拉事件
        /////// </summary>
        /////// <param name="sender">事件源</param>
        /////// <param name="e">参数</param>
        ////protected virtual void txtKMEncoding_TextChanged(object sender, EventArgs e)
        ////{
        ////    try
        ////    {
        ////        Dictionary<string, Yss.KTable.Models.Row> c_Coll = tbMain.Tag as Dictionary<string, Yss.KTable.Models.Row>;

        ////        foreach (Yss.KTable.Models.Row row in c_Coll.Values)
        ////        {
        ////            Bal.Pojo.Balance cls_Bal = row.Tag as YssFina.Report.Bal.Pojo.Balance;

        ////            row.Selected = false;
        ////            if (cls_Bal.C_KM_Code.ToUpper().StartsWith(txtKMEncoding.Text.ToUpper()))
        ////            {
        ////                //// 注：改为此方式时必须保证科目代码按父节点下挂子节点的方式才能实现
        ////                row.Selected = true;
        ////                tbMain.ShowSelectedRow(row);
        ////                return;
        ////            }
        ////        }

        ////        tbMain.Refresh();
        ////    }
        ////    catch (Exception ex)
        ////    {
        ////        ////YssCore.Util.YssMessageBox.ShowError(ex.Message);
        ////        YssMessageBox.ShowCommonInfo(ex.Message);
        ////    }
        ////}

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

            ErBbInfo pojo = this.parentList.tbMain.SelectedRow.Tag as ErBbInfo;
            LicenseFile license = ClsContext.licFile as LicenseFile;
            string tghCode = "";
            string tghName = "";
            Yss.FileProcessor.ExportSetting exportSetting = base.ResetTableMainExportSetting(table);
            if (string.IsNullOrEmpty(exportSetting.ExportSubTitle))
            {
                exportSetting.ExportSubTitle = (license.C_User_Name == "" ? "深圳市赢时胜信息技术股份有限公司" : license.C_User_Name) + "_专用表";
            }

            if (string.IsNullOrEmpty(exportSetting.ExportDescription)) {
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

        private void cboXsjb_SelectedValueChanged(object sender, EventArgs e)
        {
            if (null != this.cboXsjb.Value)
            {
                int Level = Convert.ToInt32(this.cboXsjb.Value.ToString());
                this.tbMain.ShowGradingLevel(Level);
                updateLevelDict();
            }
        }

        private void cboXsjb_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            e.Cancel = true;
            if (this.cboXsjb.Items.Count > 0)
            {
                return;
            }
            setItems();
        }

        private void setItems()
        {
            this.cboXsjb.Items.Clear();
            this.cboXsjb.Value = "";
            if (this.tbMain.Rows.Count > this.tbMain.FixedTopRows && this.cboXsjb.Items.Count == 0)
            {
                int Level = this.tbMain.GetGradingLevel(this.tbMain.Rows, 1);
                for (int i = 1; i <= Level; i++)
                {
                    Yss.KRichEx.AutoFilter.Model.KTableEntity kTableEntity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(i.ToString() + "级", i.ToString());
                    this.cboXsjb.Items.Add(kTableEntity);
                }
                if (LevelDict.ContainsKey(this.tbMain.ExportName.ToString()))
                {
                    string lv = "";
                    LevelDict.TryGetValue(this.tbMain.ExportName.ToString(), out lv);
                    this.cboXsjb.Value = lv;
                }
                else
                {
                    this.cboXsjb.Value = Level.ToString();
                }
            }
        }

        /// <summary>
        /// 更新级别字典LevelDict
        /// </summary>
        private void updateLevelDict()
        {
            if (LevelDict.ContainsKey(this.tbMain.ExportName.ToString()))
            {
                LevelDict.Remove(this.tbMain.ExportName.ToString());
                LevelDict.Add(this.tbMain.ExportName.ToString(), this.cboXsjb.Value.ToString());
            }
            else
            {
                LevelDict.Add(this.tbMain.ExportName.ToString(), this.cboXsjb.Value.ToString());
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