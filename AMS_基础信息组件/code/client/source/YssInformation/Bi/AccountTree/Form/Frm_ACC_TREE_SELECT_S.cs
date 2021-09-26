using System;
using System.Collections.Generic;
using System.Windows.Forms;
using Yss.KTable.Models;
using FAST.Core.BaseForm;
using FAST.Common.Service.Pojo;
using FAST.Core.Context;
using FAST.Core.Util;
using System.Drawing;

using FAST.Common.Service.Services;
using Yss.KNavigation;
using System.Text.RegularExpressions;
using System.Runtime.InteropServices;
using YssDevComponents.DotNetBar;
using YssDevComponents.DotNetBar.Controls;
using FAST.Common.Service.DataService;
using System.Collections;
using Yss.KTable.Enums;
using Yss;
using Yss.Controls;
using FAST.Core.CRUD.Form;
using FAST.Core.Communication.Service;
using FAST.Core.Exceptions;
using FAST.Core.Communication.DataService;
using YssInformation.Support.Bi.AccountTree.Service;
using System.Xml;
using YssInformation.Support.Bi.AccountTree.Pojo;
using FAST.Common.Service.Pojo.Base;

namespace YssInformation.Bi.AccountTree.Form
{
    /// <summary>
    /// 默认设置组合信息的界面
    /// </summary>
    public partial class Frm_ACC_TREE_SELECT_S : System.Windows.Forms.Form
    {
        /// <summary>
        /// 获取或设置账户树形结构类型。
        /// </summary>
        private string accountTreeDefault = "";

        /// <summary>
        /// accountTreeSelected
        /// </summary>
        public string accountTreeSelected = "";

        /// <summary>
        /// funCode
        /// </summary>
        public string funCode = "";

        /// <summary>
        /// ip
        /// </summary>
        private string ip = "";

        /// <summary>
        /// 定义服务
        /// </summary>
        private IAccountTreeAService accountTreeAService;

        /// <summary>
        /// accountTreeBService
        /// </summary>
        private IAccountTreeBService accountTreeBService;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_ACC_TREE_SELECT_S()
        {
            InitializeComponent();
        }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="assetType">组合类型</param>
        public Frm_ACC_TREE_SELECT_S(string assetType)
        {
            InitializeComponent();
            this.accountTreeDefault = accountTreeDefault;
        }

        /// <summary>
        /// 初始化服务
        /// </summary>
        protected void InitService()
        {
            accountTreeAService = ServiceFactory.createService<IAccountTreeAService>();
            accountTreeBService = ServiceFactory.createService<IAccountTreeBService>();
        }

        /// <summary>
        /// 加载窗体数据
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected void Frm_ACC_TREE_SELECT_S_Load(object sender, EventArgs e)
        {
            try
            {
                this.InitService();
                this.LoadTabPages();
            }
            catch (System.Exception ex)
            {
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 装载TabPage分页
        /// </summary>
        protected void LoadTabPages()
        {
            try
            {
                Dictionary<string, string> paraDict = new Dictionary<string, string>();
                ////add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
                paraDict.Add("C_NODE_CODE_P_ROOT", "[root]");
                paraDict.Add("DEFAULT_NODE", "true");
                QueryRes res = accountTreeAService.getTreeViewData(paraDict);
                if (res.DataList == null || res.DataList.Count == 0)
                {
                    return;
                }

                List<Yss.Controls.TabPage> tabPages = new List<Yss.Controls.TabPage>();
                foreach (BasePojo pojo in res.DataList)
                {
                    AccountTreeA accTrA = pojo as AccountTreeA;
                    Yss.Controls.TabPage newPage = this.CreateTabPage(accTrA.C_NODE_NAME);
                    newPage.Tag = accTrA.C_NODE_CODE;
                    tabPages.Add(newPage);
                }

                this.tabCtrl.TabPages.AddRange(tabPages.ToArray());

                foreach (Yss.Controls.TabPage tempPage in this.tabCtrl.TabPages)
                {
                    if (tempPage.Tag.ToString() == this.accountTreeDefault)
                    {
                        this.tabCtrl.SelectedTab = tempPage;
                        break;
                    }
                }
            }
            catch (System.Exception ex)
            {
                ClsBaseException.DiscardException(ex);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFormLoadErr(this.Text));
            }
        }

        /// <summary>
        /// 创建分页
        /// </summary>
        /// <param name="pageText">分页标题</param>
        /// <returns>返回创建好的分页</returns>
        protected Yss.Controls.TabPage CreateTabPage(string pageText)
        {
            Yss.Controls.TabPage newPage = new Yss.Controls.TabPage(pageText);

            Table newTable = this.CreateTable();
            newPage.Controls.Add(newTable);

            return newPage;
        }

        /// <summary>
        /// 创建Table
        /// </summary>
        /// <returns>返回创建好的Table</returns>
        protected Table CreateTable()
        {
            Table newTable = new Table();
            newTable.Border.BorderColor = System.Drawing.Color.DarkGray;
            newTable.Border.Left = true;
            newTable.ColumnHeight = 18;
            newTable.DefaultToolStripItems = Yss.KTable.Enums.SysToolStripItems.GardingMenu;
            newTable.FullRowSelect = false;
            newTable.GridLine = Yss.KTable.Enums.GridLines.Both;
            newTable.GridLineColor = System.Drawing.Color.LightSteelBlue;
            newTable.PlusMinusStyle = Yss.KTable.Enums.PlusMinusStyles.IconIndentLine;
            newTable.ShowCheckBox = false;
            newTable.Style = Yss.KTable.Enums.Style.Default;
            newTable.Dock = DockStyle.Fill;

            return newTable;
        }

        /// <summary>
        /// 分页改变事件
        /// </summary>
        /// <param name="sender">TabControl</param>
        /// <param name="e">事件参数</param>
        protected void tabCtrl_SelectedIndexChanged(object sender, Yss.Controls.TabPageEventArgs e)
        {
            Table tempTable = e.NewPage.Controls[0] as Table;
            if (tempTable.Rows.Count == 0)
            {
                this.LoadTableData(e.NewPage.Controls[0] as Table, e.NewPage.Tag.ToString());
            }
        }

        /// <summary>
        /// 列头
        /// </summary>
        /// <returns>列头</returns>
        public static List<ListHeadInfo> getAccTreesViewTableListHead()
        {
            List<ListHeadInfo> listHeadList = new List<ListHeadInfo>();

            ListHeadInfo listhead = new ListHeadInfo();
            listhead.Key = "C_OPEN_ACC_NAME";
            listhead.Align = "LEFT";
            listhead.Format = "";
            listhead.ShowConvert = "false";
            listhead.Text = "账户名称";
            listHeadList.Add(listhead);

            listhead = new ListHeadInfo();
            listhead.Key = "C_OPEN_ACC_NO";
            listhead.Align = "LEFT";
            listhead.Format = "";
            listhead.ShowConvert = "false";
            listhead.Text = "账号";
            listHeadList.Add(listhead);

            listhead = new ListHeadInfo();
            listhead.Key = "C_OPEN_ADDR";
            listhead.Align = "LEFT";
            listhead.Format = "";
            listhead.ShowConvert = "false";
            listhead.Text = "开户行";
            listHeadList.Add(listhead);

            listhead = new ListHeadInfo();
            listhead.Key = "C_ACCOUNT_TYPE";
            listhead.Align = "LEFT";
            listhead.Format = "";
            listhead.ShowConvert = "true";
            listhead.ServiceId = "IAccountTypeDataService";
            listhead.Text = "账户类型";
            listHeadList.Add(listhead);

            listhead = new ListHeadInfo();
            listhead.Key = "C_ORG_CODE";
            listhead.Align = "LEFT";
            listhead.Format = "";
            listhead.ShowConvert = "true";
            listhead.ServiceId = "IOrgDataService";
            listhead.Text = "托管人";
            listHeadList.Add(listhead);

            listhead = new ListHeadInfo();
            listhead.Key = "C_DC_CODE";
            listhead.Align = "LEFT";
            listhead.Format = "";
            listhead.ShowConvert = "true";
            listhead.ServiceId = "IDCDataService";
            listhead.Text = "币种";
            listHeadList.Add(listhead);
            return listHeadList;
        }

        /// <summary>
        /// 加载默认用户
        /// </summary>
        /// <param name="table">表</param>
        /// <param name="type">用户类型</param>
        public void LoadTableData(Table table, string type)
        {
            QueryRes res = null;
            try
            {
                Dictionary<string, string> paraDict = new Dictionary<string, string>();
                ////add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
                paraDict.Add("C_NODE_CODE_P", type);
                //// By Jinghehe 2014-3-3 根据产品状态进行过滤组合
                res = accountTreeBService.getTreeViewData(paraDict);
                res.ListHeadList = getAccTreesViewTableListHead();
                new TableListLoader().loadTable(table, res, false, false, ClsEnums.KTableDataShowMode.TreeMode);
            }
            catch (System.Exception ex)
            {
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 确定按钮事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected void btnOK_Click(object sender, EventArgs e)
        {
            try
            {
                this.accountTreeSelected = this.tabCtrl.SelectedTab.Tag.ToString();
                if (!this.chkBoxSpecial.Checked)
                {
                    this.funCode = "all";
                }
                
                updateConfig(funCode, accountTreeSelected);
                this.DialogResult = DialogResult.OK;
                ////关闭窗体
                this.Close();
            }
            catch (System.Exception ex)
            {
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// updateConfig
        /// </summary>
        /// <param name="funCode">funCode</param>
        /// <param name="accountTreeSelected">accountTreeSelected</param>
        public void updateConfig(string funCode, string accountTreeSelected)
        {
            string ip = ClsContext.Ip; 
            string userCode = ClsContext.CurrUserCode;
            string configPath = ClsConstant.FILE_CONFIG;
            bool hasUpdate = false;
            XmlDocument xd = new XmlDocument();
            xd.Load(configPath);
            XmlNodeList xmlNoteList = xd.GetElementsByTagName("AccountTreeConfig");
            foreach (XmlElement item in xmlNoteList)
            {
                if (item.GetAttribute("funCode") == funCode
                    && item.GetAttribute("ip") == ip
                    && item.GetAttribute("userCode") == userCode)
                {
                    item.SetAttribute("nodeCodeP", accountTreeSelected);
                    xd.Save(configPath);
                    hasUpdate = true;
                }
            }

            if (!hasUpdate)
            {
                xd.Load(configPath);
                XmlNodeList list = xd.GetElementsByTagName("AccountTreeConfigs");
                if (list == null || list.Count == 0)
                {
                    XmlNode ysstech = xd.SelectSingleNode("ysstech");
                    XmlElement xe = xd.CreateElement("AccountTreeConfigs"); ////创建一个<AccountTreeConfigs>节点   
                    ysstech.AppendChild(xe);
                    xd.Save(configPath);
                }

                list = xd.GetElementsByTagName("AccountTreeConfigs");
                XmlNode root = list[0]; ////查找<accountTreeConfigs>   
                XmlElement xe1 = xd.CreateElement("AccountTreeConfig"); ////创建一个<AccountTreeConfig>节点   
                xe1.SetAttribute("funCode", funCode);   ////设置该节点funCode属性   
                xe1.SetAttribute("ip", ip); ////设置该节点ip属性   
                xe1.SetAttribute("userCode", userCode); ////设置该节点userCode属性   
                xe1.SetAttribute("nodeCodeP", accountTreeSelected); ////设置该节点nodeCodeP属性   
                root.AppendChild(xe1);  ////添加到<accountTreeConfigs>节点中   
                xd.Save(configPath);
            }

        }

        /// <summary>
        /// 关闭按钮事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected void btnCancel_Click(object sender, EventArgs e)
        {
            ////关闭窗体
            this.Close();
        }

        /// <summary>
        /// 鼠标移动事件拖动窗体
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected void Frm_ACC_TREE_SELECT_S_MouseDown(object sender, MouseEventArgs e)
        {
            ControlDrager.MoveControl(this);
        }
    }
}
