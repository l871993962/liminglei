using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
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
using System;
using System.Text.RegularExpressions;






using System.Collections.Generic;
using System.IO;

using System.Drawing;
using System.Text;
using Yss.KRichEx.AutoFilter.Model;
using Yss.KRichEx.AutoFilter.Collections;

using System.Windows.Forms;
using FAST.Common.Service.Dict.Pojo;
////using YssBaseCls.Fun;
using FAST.Common.Service.DataService;
using FAST.Common.Service.Services;
using YssInformation.Support.Bi.AssociationOrgan.Service;
using YssInformation.Support.Bi.AssociationOrgan.Pojo;
using YssInformation.Support.Fun;
using Yss.KTable.Models;
using System.Collections;
using Yss.KTable.Events;
using Yss.KRichEx;
using Yss.KTable.Collections;


namespace YssInformation.Bi.AssociationOrgan.Form
{
    /// <summary>
    /// FrmOrganSet 的摘要说明。
    /// 作用：机构设置，负责机构的增删改等功能
    ///  作者：lyh
    ///  版本：v4.5.0.1
    ///  修改内容：窗体重新绘制，功能方法实现
    ///  修改日期：2010.12.3
    ///  －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：caozhonghu
    /// 修改日期：20110216
    /// 修改简介：需求二次开发 
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：lyh
    /// 修改日期：20110226
    /// 修改简介：调整代码新结构
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         返回对象错误直接抛出异常
    /// </summary>
    public partial class Frm_ORG_S : FrmBaseSet
    {
        /// <summary>
        /// 关联的类型
        /// </summary>
        public string openType = "";

        /// <summary>
        /// 管理人
        /// </summary>
        private readonly string MANAGER = "MANAGER";

        /// <summary>
        /// 次管理人
        /// </summary>
        private readonly string MANAGER_SEC = "MANAGER_SEC";

        /// <summary>
        /// 托管人
        /// </summary>
        private readonly string TRUSTEE = "TRUSTEE";

        /// <summary>
        /// 次托管人
        /// </summary>
        private readonly string TRUSTEE_SEC = "TRUSTEE_SEC";

        /// <summary>
        /// 担保人
        /// </summary>
        private readonly string WARRANTOR = "WARRANTOR";

        /// <summary>
        /// 投资顾问
        /// </summary>
        private readonly string INVEST_ADVISER = "INVEST_ADVISER";

        /// <summary>
        /// 信托人
        /// </summary>
        private readonly string TRUSTEE_XT = "TRUSTEE_XT";

        /// <summary>
        /// 销售渠道
        /// </summary>
        private readonly string SALES_CHANNELS = "SALES_CHANNELS";

        /// <summary>
        /// 结算会员
        /// </summary>
        private readonly string CLEARING_MEMBER = "CLEARING_MEMBER";

        /// <summary>
        /// 保险委托
        /// </summary>
        private readonly string BX_CLIENT = "BX_CLIENT";

        /// <summary>
        /// 第3方委托
        /// </summary>
        private readonly string TRD_CLIENT = "TRD_CLIENT";

        /// <summary>
        /// 委托人
        /// </summary>
        private readonly string CONSIGNER = "CONSIGNER";

        /// <summary>
        /// 受托人
        /// </summary>
        private readonly string DEPOSITARY = "DEPOSITARY";

        /// <summary>
        /// 20160330 added by liubo.STORY #29359 【中行】交易对手方、评级机构的维护
        /// 发行人
        /// </summary>
        private readonly string ISSUER = "ISSUER";

        /// <summary>
        /// 20160330 added by liubo.STORY #29359 【中行】交易对手方、评级机构的维护
        /// 对手方
        /// </summary>
        private readonly string COUNTERPARTY = "COUNTERPARTY";

        /// <summary>
        /// 20160727 added by heliang.STORY #30646 【云平台】万联证券关联机构设置增加外包服务机构类型
        /// </summary>
        private readonly string ORG_WBFWJG = "ORG_WBFWJG";

        /// <summary>
        /// Author : ChenLong
        /// Date   : 2016-11-22
        /// Status : Add
        /// Comment: 电子对账
        /// </summary>
        private readonly string ELEC_RECONCILIATION = "ELEC_RECONCILIATION";
        
        /// <summary>
        /// 20161210 added by mzy BUG #147121 【云平台万联证券】主体信息设置，资质为外包服务机构的，无法关联到组合 
        /// 主托管人
        /// </summary>
        private readonly string TRUSTEE_MA = "TRUSTEE_MA";

        /// <summary>
        /// 营销机构
        /// 20170113 added by HeLiang STORY #37662 光大银行新增自定义资产情况报表
        /// </summary>
        private readonly string MARKETING = "MARKETING";

        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private IOrgService orgService = null;

        /// <summary>
        /// logo服务端文件名
        /// </summary>
        private string logFileName = null;

        /// <summary>
        /// 后台图片存放路径
        /// </summary>
        private string path = "%YSS_APP_PATH%/yssuco/image/";

        /// <summary>
        /// 主体资质中的发行人是否勾选
        /// </summary>
        private bool issuerChecked = false;

        /// <summary>
        /// 主体资质中的对手方是否勾选
        /// </summary>
        private bool counterpartyChecked = false;

        
        /// <summary>
        /// 主题信息 BY Jinghehe 2017-9-8 供穿透进行展示
        /// STORY # 38071 综合指令界面不允许修改或者新增大额支付号
        /// </summary>
        private Org tempOrg = null;

        /// <summary>
        /// 显示标识，控制ShowInfoMVC方法走另一支线 add by lixiang@ysstech.com
        /// STORY49196 TA清算数据清算参数校验界面需要添加相关审核的需求
        /// </summary>
        private bool showInfoMvc = false;

        /// <summary>
        /// add by lixiang@ysstech.com
        /// STORY49196 TA清算数据清算参数校验界面需要添加相关审核的需求
        /// </summary>
        private BasePojo basePojo = null;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_ORG_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
            quyStrUtil.addQuyCon("C_DV_TYPE", "ORG_TYPE", ClsConstant.SQL_RA_HYPHEN_EQUAL);
            cboOrganType.QueryCond = quyStrUtil.getQuyStr();
            //// 显示备注字段 add by Yuntao Lau 2015.09.09 STORY #25594
            ////add by zhoushuhang 20170519 BUG #160288 点击资本币种下拉框报错     备注不显示,显示为主体资质
            this.ShowDescription = false;
            this.sumQualification.ShowColumnHeader = false;
            this.rtLinkMan.Table.AllowResizeColumn = true;
            this.rtLinkMan.Table.AllowColumnDrag = false;
            this.rtLinkMan.Table.SelectionMode = System.Windows.Forms.SelectionMode.One;
            this.rtLinkMan.Table.DefaultToolStripItems = Yss.KTable.Enums.SysToolStripItems.None;
            this.rtLinkMan.AfterRowAdded += new RowEventHandler(rtLinkMan_AfterRowAdded);
            this.rtLinkMan.BeforeRowRemoved += new RowEventHandler(rtLinkMan_BeforeRowRemoved);
        }

        /// <summary>
        /// 构造方法
        /// </summary>
        /// <param name="frm">frm</param>
        /// <param name="basePojo">basePojo</param>
        public Frm_ORG_S(FrmBaseSet frm, BasePojo basePojo)
        {
            this.bUseMVCService = true;
            InitializeComponent();
            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
            quyStrUtil.addQuyCon("C_DV_TYPE", "ORG_TYPE", ClsConstant.SQL_RA_HYPHEN_EQUAL);
            cboOrganType.QueryCond = quyStrUtil.getQuyStr();
            //// 显示备注字段 add by Yuntao Lau 2015.09.09 STORY #25594
            ////add by zhoushuhang 20170519 BUG #160288 点击资本币种下拉框报错     备注不显示,显示为主体资质
            this.ShowDescription = false;
            this.sumQualification.ShowColumnHeader = false;
            this.rtLinkMan.Table.AllowResizeColumn = true;
            this.rtLinkMan.Table.AllowColumnDrag = false;
            this.rtLinkMan.Table.SelectionMode = System.Windows.Forms.SelectionMode.One;
            this.rtLinkMan.Table.DefaultToolStripItems = Yss.KTable.Enums.SysToolStripItems.None;
            this.rtLinkMan.AfterRowAdded += new RowEventHandler(rtLinkMan_AfterRowAdded);
            this.rtLinkMan.BeforeRowRemoved += new RowEventHandler(rtLinkMan_BeforeRowRemoved);

            if (basePojo != null)
            {
                this.basePojo = basePojo;
            }
        }

        /// <summary>
        /// 主题信息 BY Jinghehe 2017-9-8 供穿透进行展示
        /// STORY # 38071 综合指令界面不允许修改或者新增大额支付号
        /// </summary>
        public Org TempOrg
        {
            get
            {
                return tempOrg;
            }

            set
            {
                tempOrg = value;
                this.status = ClsEnums.StatusSetting.YssBrow;
            }
        }

        /// <summary>
        /// 重写审核方法 
        /// add by lixiang@ysstech.com
        /// STORY49196 TA清算数据清算参数校验界面需要添加相关审核的需求
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnAudit_Click(object sender, EventArgs e)
        {
            if (this.basePojo != null)
            {
                IOrgService orgService = ServiceFactory.createService<IOrgService>();
                ArrayList list = new ArrayList();
                list.Add(this.basePojo);
                string result = orgService.auditById(list);
                this.operAfterSave(result);
                this.btnBar.setAllButtonEnabled(false);
            }
            else
            {
                base.btnAudit_Click(sender, e);
            }
        }

        /// <summary>
        /// 初始化方法.
        /// </summary>
        public override void yssInitCtlAttr()
        {
            ////liuping   2011-03-13  设置界面获取A区机构类型代码
            Vocabulary voc = null;
            if (this.frmBaseViewList != null)
            {
                voc = this.frmBaseViewList.getSelectedRowTagMVC(voc) as Vocabulary;
            }

            if (voc != null)
            {
                this.cboOrganType.Value = voc.C_DV_CODE;
            }

            this.rowAttr.Visible = false;

            initCheckBoxes();

            initLinkManInfo();
        }

        /// <summary>
        /// 变更控件状态
        /// </summary>
        protected override void YssChangeControlState()
        {
            if (status != ClsEnums.StatusSetting.YssBrow && rtLinkMan.Table.Rows.Count == 0)
            {
                addNewRow(null, 1);
            }

            clsInterface.setControlsStatus(rtLinkMan.Table, status);
            rtLinkMan.ToolBoxEnabled = status != ClsEnums.StatusSetting.YssBrow;
            rtLinkMan.Table.ReadOnly = status == ClsEnums.StatusSetting.YssBrow;
        }

        /// <summary>
        /// 初始化联系人信息
        /// </summary>
        private void initLinkManInfo()
        {
            if (rtLinkMan.Table.Columns.Count != 5)
            {
                rtLinkMan.Table.Columns.Clear();
                Column column = new Column("联系人", "C_LINK_MAN");
                column.CellTextAlign = ContentAlignment.MiddleCenter;
                column.Width = 100;
                rtLinkMan.Table.Columns.Add(column);
                column = new Column("岗位", "C_POST_NAME");
                column.CellTextAlign = ContentAlignment.MiddleCenter;
                column.Width = 120;
                rtLinkMan.Table.Columns.Add(column);
                column = new Column("联系电话", "C_LINK_TEL");
                column.CellTextAlign = ContentAlignment.MiddleCenter;
                column.Width = 110;
                rtLinkMan.Table.Columns.Add(column);
                column = new Column("手机号码", "C_MO_TEL");
                column.CellTextAlign = ContentAlignment.MiddleCenter;
                column.Width = 110;
                rtLinkMan.Table.Columns.Add(column);
                column = new Column("电子邮箱", "C_EMAIL");
                column.CellTextAlign = ContentAlignment.MiddleCenter;
                column.Width = rtLinkMan.Table.Width - 440;
                rtLinkMan.Table.Columns.Add(column);
            }
        }

        /// <summary>
        /// 行新增后事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void rtLinkMan_AfterRowAdded(object sender, RowEventArgs e)
        {
            addNewRow(e.Row, 1);
            if (rtLinkMan.Table.Rows.Count == 0)
            {
                rtLinkMan.Table.Rows.Add(e.Row);
            }
        }

        /// <summary>
        /// 添加N行
        /// </summary>
        /// <param name="row">行</param>
        /// <param name="count">行数</param>
        private void addNewRow(Row row, int count)
        {
            for (int i = 0; i < count; i++)
            {
                Cell cell0 = null;
                Cell cell1 = null;
                Cell cell2 = null;
                Cell cell3 = null;
                Cell cell4 = null;

                if (row == null)
                {
                    row = new Row();
                    cell0 = new Cell();
                    row.Cells.Add(cell0);
                    cell1 = new Cell();
                    row.Cells.Add(cell1);
                    cell2 = new Cell();
                    row.Cells.Add(cell2);
                    cell3 = new Cell();
                    row.Cells.Add(cell3);
                    cell4 = new Cell();
                    row.Cells.Add(cell4);
                    rtLinkMan.Table.Rows.Add(row);
                }
                else
                {
                    cell0 = row.Cells[0];
                    cell1 = row.Cells[1];
                    cell2 = row.Cells[2];
                    cell3 = row.Cells[3];
                    cell4 = row.Cells[4];
                }

                YssTextBox txtBox = new YssTextBox();
                txtBox.YssCaption = "联系人";
                txtBox.YssLength = 50;
                txtBox.Height = 22;
                txtBox.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
                txtBox.ReadOnly = status == ClsEnums.StatusSetting.YssBrow;
                cell0.InnerControl = txtBox;
                txtBox.Text = cell0.Key;

                txtBox = new YssTextBox();
                txtBox.YssCaption = "岗位";
                txtBox.YssLength = 50;
                txtBox.Height = 22;
                txtBox.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
                txtBox.ReadOnly = status == ClsEnums.StatusSetting.YssBrow;
                cell1.InnerControl = txtBox;
                txtBox.Text = cell1.Key;

                txtBox = new YssTextBox();
                txtBox.YssCaption = "联系电话";
                txtBox.YssLength = 18;
                txtBox.Height = 22;
                txtBox.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
                txtBox.ReadOnly = status == ClsEnums.StatusSetting.YssBrow;
                cell2.InnerControl = txtBox;
                txtBox.Text = cell2.Key;

                txtBox = new YssTextBox();
                txtBox.YssCaption = "手机号码";
                txtBox.YssLength = 11;
                txtBox.Height = 22;
                txtBox.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
                txtBox.ReadOnly = status == ClsEnums.StatusSetting.YssBrow;
                cell3.InnerControl = txtBox;
                txtBox.Text = cell3.Key;

                txtBox = new YssTextBox();
                txtBox.YssCaption = "电子邮箱";
                txtBox.YssLength = 50;
                txtBox.Height = 22;
                txtBox.TextAlign = System.Windows.Forms.HorizontalAlignment.Center;
                txtBox.ReadOnly = status == ClsEnums.StatusSetting.YssBrow;
                cell4.InnerControl = txtBox;
                txtBox.Text = cell4.Key;

                row.Height = 22;
            }

            rtLinkMan.Table.GridLine = Yss.KTable.Enums.GridLines.Columns;
            rtLinkMan.Table.Refresh();
        }

        /// <summary>
        /// 行删除事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void rtLinkMan_BeforeRowRemoved(object sender, RowEventArgs e)
        {
            foreach (Cell cell in e.Row.Cells)
            {
                if (cell.InnerControl != null)
                {
                    Control c = cell.InnerControl;
                    c.Dispose();
                    cell.InnerControl = null;
                }
            }
        }

        /// <summary>
        /// 填充联系人信息表格
        /// </summary>
        /// <param name="orgCode">orgCode</param>
        private void fillLinkManTable(string orgCode)
        {
            if (orgCode.Equals("") || dataService == null)
            {
                return;
            }

            rtLinkMan.Table.Rows.Clear();
            List<Org> orgList = ((IOrgService)dataService).getOrgLinkManList(orgCode);
            foreach (Org org in orgList)
            {
                Row row = new Row();
                Cell cell = new Cell();
                cell.Key = org.C_LINK_MAN;
                row.Cells.Add(cell);
                cell = new Cell();
                cell.Key = org.C_POST_NAME;
                row.Cells.Add(cell);
                cell = new Cell();
                cell.Key = org.C_LINK_TEL;
                row.Cells.Add(cell);
                cell = new Cell();
                cell.Key = org.C_MO_TEL;
                row.Cells.Add(cell);
                cell = new Cell();
                cell.Key = org.C_EMAIL;
                row.Cells.Add(cell);
                rtLinkMan.Table.Rows.Add(row);
                addNewRow(row, 1);
            }
        }

        /// <summary>
        /// 获取联系人信息表格第一行信息
        /// </summary>
        /// <param name="infoType">infoType</param>
        /// <returns>Text</returns>
        private string getLinkManInfo(string infoType)
        {
            foreach (Row row in rtLinkMan.Table.Rows)
            {
                string text0 = row.Cells[0].InnerControl.Text;
                string text1 = row.Cells[1].InnerControl.Text;
                string text2 = row.Cells[2].InnerControl.Text;
                string text3 = row.Cells[3].InnerControl.Text;
                string text4 = row.Cells[4].InnerControl.Text;
                string check = text0 + text1 + text2 + text3 + text4;

                if ("".Equals(check.Trim()))
                {
                    continue;
                }

                if (infoType.Equals("C_LINK_MAN"))
                {
                    return text0;
                }
                else if (infoType.Equals("C_POST_NAME"))
                {
                    return text1;
                }
                else if (infoType.Equals("C_LINK_TEL"))
                {
                    return text2;
                }
                else if (infoType.Equals("C_MO_TEL"))
                {
                    return text3;
                }
                else if (infoType.Equals("C_EMAIL"))
                {
                    return text4;
                }
            }

            return "";
        }

        /// <summary>
        ///窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ORG_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.orgService = ServiceFactory.createService(serviceType) as IOrgService;
            this.dataService = this.orgService;

            /**20160401 added by liubo.STORY #29359 【中行】交易对手方、评级机构的维护
             增加这两个page选中的语句，是因为直接显示tabPage1或者tabPage2的时候，page右侧的CELL会显示不出来，原因不明。
             * 而显示tabPage3之后，再显示另外两个page，就没有问题了。所以这里先写死page显示的逻辑*/
            tbDetails.SelectedTab = tabPage3;
            tbDetails.SelectedTab = tabPage1;
        }

        /// <summary>
        /// 封装窗体元素到对象
        /// </summary>
        /// <returns>Pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            ////将窗体的控件一一赋值给实体类的各个属性
            Org organ = new Org();
            try
            {
                organ.C_ORG_CODE = this.txtOrganCode.Text;
                organ.C_ORG_INNER_CODE = this.txtOrganInnerCode.Text;
                organ.C_ORG_NAME = this.txtOrganName.Text;
                organ.C_ORG_NAME_CN = this.txtCNName.Text;
                organ.C_ORG_NAME_ST = this.txtShortCorpCode.Text;
                organ.C_ORG_CODE_P = this.cboParentCorp.Value == null ? "" : this.cboParentCorp.Value;
                organ.C_CORP_REP = this.txtCorpRepresen.Text;
                organ.C_DC_CODE = this.cboCapitalCury.Value;
                organ.C_DESC = this.txtDesc.Text; //// edit by dingyan 2017.06.20 STORY #43366 歌斐资产-关联机构设置联系信息 

                if (status == ClsEnums.StatusSetting.YssAdd)
                {
                    if (this.picLogo.ImageLocation != null && this.picLogo.ImageLocation.Trim().Length > 0)
                    {
                        organ.C_LOGO_NAME = path + logFileName;
                    }
                }
                else if (status == ClsEnums.StatusSetting.YssEdit)
                {
                    if (this.picLogo.ImageLocation != null && this.picLogo.ImageLocation.Trim().Length > 0)
                    {
                        Org organ1 = (Org)this.yssGetBaseSelTypeItemMVC();
                        if (!this.picLogo.ImageLocation.EndsWith(organ1.C_LOGO_NAME) || organ1.C_LOGO_NAME.Trim().Length == 0)
                        {
                            organ.C_LOGO_NAME = path + logFileName;
                        }
                    }
                    else
                    {
                        organ.C_LOGO_NAME = "";
                    }
                }
                else
                {
                    ////不会上传文件
                    if (this.picLogo.ImageLocation != null && this.picLogo.ImageLocation.Trim().Length > 0)
                    {
                        string fullFileName = this.picLogo.ImageLocation;
                        organ.C_LOGO_NAME = fullFileName.Substring(fullFileName.LastIndexOf("\\"));
                    }
                }

                if (!string.IsNullOrEmpty(this.txtPayNum.Text))
                {
                    organ.C_PAY_CODE = this.txtPayNum.Text.Replace(" ", "");
                }

                organ.C_BANK_CODE = this.txtBankNum.Text;

                if (this.txtRegCapital.Text.Trim().Length > 0)
                {
                    organ.N_REG_CAP = Convert.ToDouble(this.txtRegCapital.Value);
                }
                else
                {
                    organ.N_REG_CAP = 0;
                }

                organ.C_REG_ADDR = this.txtRegAddr.Text;
                organ.C_OFFIC_ADDR = this.txtOfficAddr.Text;
                organ.C_DV_ORG_TYPE = this.cboOrganType.Value;
                organ.C_CORP_CODE = this.txtCorpCode.Text;


                if (getLinkManInfo("C_LINK_MAN").Trim().Length > 0)
                {
                    organ.C_LINK_MAN = getLinkManInfo("C_LINK_MAN");
                }
                else
                {
                    organ.C_LINK_MAN = " ";
                }

                organ.C_POST_NAME = getLinkManInfo("C_POST_NAME");
                organ.C_LINK_TEL = getLinkManInfo("C_LINK_TEL");
                organ.C_MO_TEL = getLinkManInfo("C_MO_TEL");
                organ.C_EMAIL = getLinkManInfo("C_EMAIL");
                organ.C_REG_POST = this.txtRegPostcode.Text;
                organ.C_OFFIC_POST = this.txtOffPostcode.Text;
                organ.C_WWW_ADDR = this.txtWwwAddr.Text; // add by Yuntao Lau
                organ.C_FAX_TEL = this.txtFaxTel.Text;   // 2015.09.15 STORY #25681

                //// 机构资质
                ////20170804 chenbo STORY #44886 【基础信息组件】 机构信息设置主体资质属性系统存储机制优化  start
                string[] qualification = getQualificationChecked();
                StringBuilder buff = new StringBuilder();
                Dictionary<string, string> dict = new Dictionary<string, string>();
                foreach (string str in qualification)
                {
                    buff.Append(str).Append(",");
                    dict.Add(str, str);
                }

                if (dict.ContainsKey(MANAGER))
                {                   
                    organ.C_DV_MANAGER = MANAGER;

                }

                //// STORY #32976 嘉实基金-管理人信息区分实际管理人和名义管理人 add by shijian 2016-09-26
                if (dict.ContainsKey(MANAGER_SEC))
                {
                    organ.C_DV_MANAGER_SEC = MANAGER_SEC;
                }

                if (dict.ContainsKey(TRUSTEE))
                {
                    organ.C_DV_TRUSTEE = TRUSTEE;
                }

                if (dict.ContainsKey(TRUSTEE_SEC))
                {
                    organ.C_DV_TRUSTEE_SEC = TRUSTEE_SEC;
                }

                if (dict.ContainsKey(WARRANTOR))
                {
                    organ.C_DV_WARRANTOR = WARRANTOR;
                }

                if (dict.ContainsKey(INVEST_ADVISER))
                {
                    organ.C_DV_INVEST_ADVISER = INVEST_ADVISER;
                }

                if (dict.ContainsKey(TRUSTEE_XT))
                {
                    organ.C_DV_TRUSTEE_XT = TRUSTEE_XT;
                }

                if (dict.ContainsKey(SALES_CHANNELS))
                {
                    organ.C_DV_SALES_CHANNELS = SALES_CHANNELS;
                }

                if (dict.ContainsKey(CLEARING_MEMBER))
                {
                    organ.C_DV_CLEARING_MEMBER = CLEARING_MEMBER;
                }

                if (dict.ContainsKey(BX_CLIENT))
                {
                    organ.C_DV_BX_CLIENT = BX_CLIENT;
                }

                if (dict.ContainsKey(TRD_CLIENT))
                {
                    organ.C_DV_TRD_CLIENT = TRD_CLIENT;
                }

                if (dict.ContainsKey(DEPOSITARY))
                {
                    organ.C_DV_DEPOSITARY = DEPOSITARY;
                }

                ////add by zzk 20151014 STORY #25788 关联机构设置模块中机构资质新增委托人选项 
                if (dict.ContainsKey(CONSIGNER))
                {
                    organ.C_DV_CONSIGNER = CONSIGNER;
                }

                //// 20160330 added by liubo.STORY #29359 【中行】交易对手方、评级机构的维护
                //// 发行人
                if (dict.ContainsKey(ISSUER))
                {
                    organ.C_DV_ISSUER = ISSUER;
                }

                //// 20160330 added by liubo.STORY #29359 【中行】交易对手方、评级机构的维护
                //// 对手方
                if (dict.ContainsKey(COUNTERPARTY))
                {
                    organ.C_DV_COUNTERPARTY = COUNTERPARTY;
                }

                ////20160727 added by heliang.STORY #30646 【云平台】万联证券关联机构设置增加外包服务机构类型
                ////外包服务机构
                if (dict.ContainsKey(ORG_WBFWJG))
                {
                    organ.C_DV_WBFWJG = ORG_WBFWJG;
                }

                /*
                 * Author : ChenLong
                 * Date   : 2016-11-22
                 * Status : Add
                 * Comment: 电子对账
                 */
                if (dict.ContainsKey(ELEC_RECONCILIATION))
                {
                    organ.C_ELEC_RECONCILIATION = ELEC_RECONCILIATION;
                }


                ////20161210 added by mzy BUG #147121 【云平台万联证券】主体信息设置，资质为外包服务机构的，无法关联到组合 
                ////主托管人
                if (dict.ContainsKey(TRUSTEE_MA))
                {
                    organ.C_DV_TRUSTEE_MA = TRUSTEE_MA;
                }

                ////20170113 added by HELiang STORY #37662 光大银行新增自定义资产情况报表
                ////营销机构
                if (dict.ContainsKey(MARKETING))
                {
                    organ.C_DV_MARKETING = MARKETING;
                }

                if (null != buff && buff.Length > 1)
                {
                    buff.Remove(buff.Length - 2, 2);
                    organ.C_DV_SUM = buff.ToString();
                }

                ////20170804 chenbo STORY #44886 【基础信息组件】 机构信息设置主体资质属性系统存储机制优化  end

                ////organ.C_PLACE_SETTLEMENT = this.txtPlace.Text;
                ////organ.C_CLEAR_ACCOUNT = this.txtClearAccount.Text;
                ////organ.C_CLEARER_ID = this.txtClearerID.Text;
                ////organ.C_CLEARER_NAME = this.txtClearerName.Text;
                ////organ.C_CLEARER_ID_TYPE = this.txtClearerIDType.Text;
                ////organ.C_BROKER_ID = this.txtBrokerID.Text;
                ////organ.C_BROKER_NAME = this.txtBrokerName.Text;
                ////organ.C_BROKER_ID_TYPE = this.txtBrokerIDType.Text;
                //////// add by wsm 2016-5-10  STORY30235 【招商基金母公司】[QDII]TradeBlotter_EXP TradeBlotter招行导出接口
                ////organ.C_BROKER_ACCOUNT = this.txtBrokerAccount.Text;
                //// 机构属性 liuxiang 2015-9-6 STORY #22255 保监会报表需要细化银行属性
                organ.C_DV_ORG_ATTR = string.IsNullOrEmpty(this.cboAttr.Value) ? " " : this.cboAttr.Value;

                /**20160411 added by liubo.STORY #29359 【中行】交易对手方、评级机构的维护
                 当主体资质勾选了发行人或对手方时，将行业类别的值封装到POJO中。其他情况下默认给个空值*/
                if ((organ.C_DV_COUNTERPARTY != null && organ.C_DV_COUNTERPARTY.Trim().Length > 0) || (organ.C_DV_ISSUER != null && organ.C_DV_ISSUER.Trim().Length > 0) || organ.C_DV_SUM.Contains("ISSUER") || organ.C_DV_SUM.Contains("COUNTERPARTY"))
                {
                    organ.C_PLATE_CODE = this.txtIndustryType.Value == null ? " " : this.txtIndustryType.Value;
                }
                else
                {
                    organ.C_PLATE_CODE = " ";
                }

                organ.C_DESC = this.txtDesc.Text; //// STORY #43366 歌斐资产-关联机构设置联系信息 
                organ.D_FOUND_TIME = this.DataFoundTime.getBeginDate.ToString("yyyy-MM-dd"); //// 成立时间 edit by Meiyuan 2016.02.25 STORY #28264 机构基本信息 字段优化,合并
                //// add by xuyuanhao 2017.02.13 STORY37444针对中登FISP平台改造主动信息模块
                organ.D_CARD_VAL_DUR = this.dtpCorpCardEff.getBeginDate.ToString("yyyy-MM-dd"); ////法人证件有效期开始日期
                organ.D_IVT_CARD_VALDUR = this.dtpEffCard.getBeginDate.ToString("yyyy-MM-dd");  ////投资人证件有效期开始日期
                organ.D_CARD_VAL_DUR_END = this.dtpCorpCardEff.getEndDate.ToString("yyyy-MM-dd"); ////法人证件有效期结束日期
                organ.D_IVT_CARD_VALDUR_END = this.dtpEffCard.getEndDate.ToString("yyyy-MM-dd"); ////投资人证件有效期结束日期
                organ.C_REP_CARD_CODE = this.txtCorpRepresenCardCode.Text;  ////法人代表证件代码
                organ.C_ADMIN_CODE = this.txtCastCode.Text;  //// 投管人代码
                organ.C_ADMIN_NAME = this.txtCastName.Text;      ////投管人名称
                organ.C_ADMIN_NATURE = this.cboCastAttr.Value;   ////投管人性质
                organ.C_IVT_CARD_TYPE = this.cboInvestorCardType.Value;    ////投资人证件类型
                organ.C_IVT_CARD_NO = this.txtInvestorCardNum.Text;     ////投资人证件号码
                organ.C_DV_REPCARD_TYPE = this.cboCorpRepresenCardType.Value;  ////法人代表证件类型


                organ.C_TG_ACCOUNT_CODE = this.textTGAccountCode.Text; ////托管账户标号 add by HuangJin 2016.10.19 STORY #34371 关联机构设置其他信息增加托管账户编号需求
                ////add by zhoushuhang 20170519 BUG #160288 点击资本币种下拉框报错
                organ.C_ORG_ENCODE = this.txtShortCorpEnCode.Text;
                organ.C_ISRElATED = this.cboIsRelated.Value;  ////是否为关联方 add by lujianhao 20180705 STORY #51721 光大证券-监管类信息完善
                //// 联系人信息 STORY #81326 【华宝兴业】关联结构设置 中联系信息维护调整
                string linkManList = "";
                foreach (Row row in rtLinkMan.Table.Rows)
                {
                    string linkMan = row.Cells[0].InnerControl.Text.Trim();
                    string postName = row.Cells[1].InnerControl.Text.Trim();
                    string linkTel = row.Cells[2].InnerControl.Text.Trim();
                    string mobTel = row.Cells[3].InnerControl.Text.Trim();
                    string email = row.Cells[4].InnerControl.Text.Trim();
                    linkManList += linkMan + " α" + postName + " α" + linkTel + " α" + mobTel + " α" + email + " β";
                }

                organ.C_LINKMAN_LIST = linkManList;
            }
            catch (Exception ye)
            {
                ClsBaseException.DiscardException(ye);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500043", _formFun, status));
            }

            return organ;
        }

        /// <summary>
        /// 展示对象到窗体
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                //// 主题信息 BY Jinghehe 2017-9-8 供穿透进行展示
                //// STORY # 38071 综合指令界面不允许修改或者新增大额支付号
                Org organ = tempOrg;
                if (null == organ)
                {
                    organ = (Org)this.yssGetBaseSelTypeItemMVC();
                }

                //// add by lixiang@ysstech.com
                //// STORY49196 TA清算数据清算参数校验界面需要添加相关审核的需求
                if (this.showInfoMvc == true)
                {
                    organ = (Org)pojo;
                }

                if (null != organ)
                {
                    ////对窗体控件一一赋值
                    this.txtOrganCode.Text = organ.C_ORG_CODE;
                    this.txtOrganName.Text = organ.C_ORG_NAME;
                    this.txtCNName.Text = organ.C_ORG_NAME_CN;
                    this.txtShortCorpCode.Text = organ.C_ORG_NAME_ST;
                    this.cboParentCorp.Value = organ.C_ORG_CODE_P;
                    this.txtCorpRepresen.Text = organ.C_CORP_REP;
                    this.cboCapitalCury.Value = organ.C_DC_CODE;
                    this.txtRegCapital.Text = organ.N_REG_CAP.ToString();
                    this.txtRegAddr.Text = organ.C_REG_ADDR;
                    this.txtOfficAddr.Text = organ.C_OFFIC_ADDR;
                    this.cboOrganType.Value = organ.C_DV_ORG_TYPE;
                    this.txtCorpCode.Text = organ.C_CORP_CODE;
                    ////this.txtContact.Text = organ.C_LINK_MAN;
                    ////this.txtContactPhone.Text = organ.C_LINK_TEL;
                    ////this.txtMobileNO.Text = organ.C_MO_TEL;
                    ////this.txtEmailAddr.Text = organ.C_EMAIL;
                    this.txtOffPostcode.Text = organ.C_OFFIC_POST;
                    this.txtRegPostcode.Text = organ.C_REG_POST;
                    this.txtWwwAddr.Text = organ.C_WWW_ADDR; //// add by Yuntao Lau
                    this.txtFaxTel.Text = organ.C_FAX_TEL;   //// 2015.09.15 STORY #25681
                    this.textTGAccountCode.Text = organ.C_TG_ACCOUNT_CODE;  ////托管账户标号 add by HuangJin 2016.10.19 STORY #34371 关联机构设置其他信息增加托管账户编号需求

                    //// add by xuyuanhao 2017.02.13 STORY37444针对中登FISP平台改造主动信息模块

                    this.txtCorpRepresenCardCode.Text = organ.C_REP_CARD_CODE;  ////法人代表证件代码
                    this.cboCorpRepresenCardType.Value = organ.C_DV_REPCARD_TYPE;  ////法人代表证件类型
                    this.txtCastCode.Text = organ.C_ADMIN_CODE;  //// 投管人代码
                    this.txtCastName.Text = organ.C_ADMIN_NAME;      ////投管人名称
                    this.cboCastAttr.Value = organ.C_ADMIN_NATURE;   ////投管人性质
                    this.cboInvestorCardType.Value = organ.C_IVT_CARD_TYPE;    ////投资人证件类型
                    this.txtInvestorCardNum.Text = organ.C_IVT_CARD_NO;     ////投资人证件号码
                    this.txtOrganInnerCode.Text = organ.C_ORG_INNER_CODE;  //// 深圳通编码（机构内码）
                    if (!string.IsNullOrEmpty(organ.D_FOUND_TIME))
                    {
                        this.DataFoundTime.setDateTime(Convert.ToDateTime(organ.D_FOUND_TIME));
                    }
                    else
                    {
                        this.DataFoundTime.setDateTime(Convert.ToDateTime("1900-01-01"));
                    }
                    ////add by xuyuanhao 2017.02.13 STORY37444针对中登FISP平台改造主动信息模块
                    if (!string.IsNullOrEmpty(organ.D_CARD_VAL_DUR))
                    {
                        this.dtpCorpCardEff.setDateTime(Convert.ToDateTime(organ.D_CARD_VAL_DUR), Convert.ToDateTime(organ.D_CARD_VAL_DUR_END)); ////法人证件有效日期
                        ////this.dtpCorpCardEff.getEndDate.setDateTime(Convert.ToDateTime(organ.D_CARD_VAL_DUR_END));     ////法人证件有效日期结束日期                     
                    }
                    else
                    {
                        this.dtpCorpCardEff.setDateTime(Convert.ToDateTime("1900-01-01"));
                    }
                    ////add by xuyuanhao 2017.02.13 STORY37444针对中登FISP平台改造主动信息模块
                    if (!string.IsNullOrEmpty(organ.D_IVT_CARD_VALDUR))
                    {
                        this.dtpEffCard.setDateTime(Convert.ToDateTime(organ.D_IVT_CARD_VALDUR), Convert.ToDateTime(organ.D_IVT_CARD_VALDUR_END));      ////投资人证件有效期
                        ////this.dtpEffCard.getEndDate.setDateTime(Convert.ToDateTime(organ.D_IVT_CARD_VALDUR_END));      ////投资人证件有效期结束日期
                    }
                    else
                    {
                        this.dtpEffCard.setDateTime(Convert.ToDateTime("1900-01-01"));
                    }

                    if (organ.C_LOGO_NAME != null && organ.C_LOGO_NAME.Trim().Length > 0)
                    {
                        ////添加http url htt
                        string image = ((IOrgService)dataService).queryImage(organ.C_LOGO_NAME);
                        if (image.Trim().Length > 0)
                        {
                            byte[] bytes = Convert.FromBase64String(image);
                            MemoryStream ms = new MemoryStream(bytes);
                            this.picLogo.Image = Image.FromStream(ms);
                        }
                    }

                    this.txtPayNum.Text = organ.C_PAY_CODE;
                    ////zhoushuhang 20170519 BUG #160288 点击资本币种下拉框报错
                    this.textTGAccountCode.Text = organ.C_TG_ACCOUNT_CODE; ////托管账户编号
                    this.txtShortCorpEnCode.Text = organ.C_ORG_ENCODE; ////主体编码
                    this.txtBankNum.Text = organ.C_BANK_CODE;

                    //// STORY #43366 歌斐资产-关联机构设置联系信息 
                    this.txtDesc.Text = organ.C_DESC;
                    //// 机构资质
                    System.Text.StringBuilder builder = new System.Text.StringBuilder();

                    if (organ.C_DV_MANAGER != null && organ.C_DV_MANAGER.Trim().Length > 0)
                    {
                        builder.Append(MANAGER).Append(",");
                    }

                    //// STORY #32976 嘉实基金-管理人信息区分实际管理人和名义管理人 add by shijian 2016-09-26
                    if (organ.C_DV_MANAGER_SEC != null && organ.C_DV_MANAGER_SEC.Trim().Length > 0)
                    {
                        builder.Append(MANAGER_SEC).Append(",");
                    }

                    if (organ.C_DV_TRUSTEE != null && organ.C_DV_TRUSTEE.Trim().Length > 0)
                    {
                        builder.Append(TRUSTEE).Append(",");
                    }

                    if (organ.C_DV_TRUSTEE_SEC != null && organ.C_DV_TRUSTEE_SEC.Trim().Length > 0)
                    {
                        builder.Append(TRUSTEE_SEC).Append(",");
                    }

                    if (organ.C_DV_WARRANTOR != null && organ.C_DV_WARRANTOR.Trim().Length > 0)
                    {
                        builder.Append(WARRANTOR).Append(",");
                    }

                    if (organ.C_DV_INVEST_ADVISER != null && organ.C_DV_INVEST_ADVISER.Trim().Length > 0)
                    {
                        builder.Append(INVEST_ADVISER).Append(",");
                    }

                    if (organ.C_DV_TRUSTEE_XT != null && organ.C_DV_TRUSTEE_XT.Trim().Length > 0)
                    {
                        builder.Append(TRUSTEE_XT).Append(",");
                    }

                    if (organ.C_DV_SALES_CHANNELS != null && organ.C_DV_SALES_CHANNELS.Trim().Length > 0)
                    {
                        builder.Append(SALES_CHANNELS).Append(",");
                    }

                    if (organ.C_DV_CLEARING_MEMBER != null && organ.C_DV_CLEARING_MEMBER.Trim().Length > 0)
                    {
                        builder.Append(CLEARING_MEMBER).Append(",");
                    }

                    if (organ.C_DV_BX_CLIENT != null && organ.C_DV_BX_CLIENT.Trim().Length > 0)
                    {
                        builder.Append(BX_CLIENT).Append(",");
                    }

                    if (organ.C_DV_TRD_CLIENT != null && organ.C_DV_TRD_CLIENT.Trim().Length > 0)
                    {
                        builder.Append(TRD_CLIENT).Append(",");
                    }

                    if (organ.C_DV_DEPOSITARY != null && organ.C_DV_DEPOSITARY.Trim().Length > 0)
                    {
                        builder.Append(DEPOSITARY).Append(",");
                    }

                    ////add by zzk 20151014 STORY #25788 关联机构设置模块中机构资质新增委托人选项 
                    if (organ.C_DV_CONSIGNER != null && organ.C_DV_CONSIGNER.Trim().Length > 0)
                    {
                        builder.Append(CONSIGNER).Append(",");
                    }

                    //// 20160330 added by liubo.STORY #29359 【中行】交易对手方、评级机构的维护
                    //// 发行人
                    if (organ.C_DV_ISSUER != null && organ.C_DV_ISSUER.Trim().Length > 0)
                    {
                        builder.Append(ISSUER).Append(",");
                    }

                    //// 20160330 added by liubo.STORY #29359 【中行】交易对手方、评级机构的维护
                    //// 对手方
                    if (organ.C_DV_COUNTERPARTY != null && organ.C_DV_COUNTERPARTY.Trim().Length > 0)
                    {
                        builder.Append(COUNTERPARTY).Append(",");
                    }

                    ////20160727 added by heliang.STORY #30646 【云平台】万联证券关联机构设置增加外包服务机构类型
                    ////外包服务机构
                    if (organ.C_DV_WBFWJG != null && organ.C_DV_WBFWJG.Trim().Length > 0)
                    {
                        builder.Append(ORG_WBFWJG).Append(",");
                    }

                    /*
                     * Author : ChenLong
                     * Date   : 2016-11-22
                     * Status : Add
                     * Comment: 电子对账
                     */
                    if (organ.C_ELEC_RECONCILIATION != null && organ.C_ELEC_RECONCILIATION.Trim().Length > 0)
                    {
                        builder.Append(ELEC_RECONCILIATION).Append(",");
                    }

                    ////20161210 added by mzy BUG #147121 【云平台万联证券】主体信息设置，资质为外包服务机构的，无法关联到组合 
                    ////主托管人
                    if (organ.C_DV_TRUSTEE_MA != null && organ.C_DV_TRUSTEE_MA.Trim().Length > 0)
                    {
                        builder.Append(TRUSTEE_MA).Append(",");
                    }

                    ////20170113 added by HeLiang STORY #37662 光大银行新增自定义资产情况报表
                    ////营销机构
                    if (organ.C_DV_MARKETING != null && organ.C_DV_MARKETING.Trim().Length > 0)
                    {
                        builder.Append(MARKETING).Append(",");
                    }

                    ////20170804 chenbo STORY #44886 【基础信息组件】 机构信息设置主体资质属性系统存储机制优化
                    if (organ.C_DV_SUM != null && organ.C_DV_SUM.Trim().Length > 0)
                    {
                        builder.Remove(0, builder.Length);
                        builder.Append(organ.C_DV_SUM).Append(",");
                    }

                    if (builder.Length > 0)
                    {
                        builder.Remove(builder.Length - 1, 1);
                        setQualificationCheckBoxesChecked(builder.ToString());
                        builder = null;
                    }
                    else
                    {
                        List<CheckBoxCell> st = this.getCheckBoxCell();
                        sumQualification.ReadOnly = false;
                        foreach (CheckBoxCell cell in st)
                        {
                            cell.Checked = false;
                        }

                        if (status == ClsEnums.StatusSetting.YssBrow)
                        {
                            sumQualification.ReadOnly = true;
                        }
                    }

                    if ((organ.C_DV_COUNTERPARTY != null && organ.C_DV_COUNTERPARTY.Trim().Length > 0) || (organ.C_DV_ISSUER != null && organ.C_DV_ISSUER.Trim().Length > 0) || organ.C_DV_SUM.Contains("ISSUER") || organ.C_DV_SUM.Contains("COUNTERPARTY"))
                    {
                        celIndustryCat.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowAlways;
                        celIndustryCatName.Text = "行业类别：";
                        ////属性设置为非必输 By Jinghehe 2017-8-11 
                        txtIndustryType.YssIsMust = false;
                        this.txtIndustryType.Value = organ.C_PLATE_CODE;
                    }
                    else
                    {
                        celIndustryCat.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                        celIndustryCatName.Text = "";
                        //// 隐藏了，属性设置为非必输 By Jinghehe 2017-8-11 
                        txtIndustryType.YssIsMust = false;
                    }

                    ////this.txtPlace.Text = organ.C_PLACE_SETTLEMENT;
                    ////this.txtClearAccount.Text = organ.C_CLEAR_ACCOUNT;
                    ////this.txtClearerID.Text = organ.C_CLEARER_ID;
                    ////this.txtClearerName.Text = organ.C_CLEARER_NAME;
                    ////this.txtClearerIDType.Text = organ.C_CLEARER_ID_TYPE;
                    ////this.txtBrokerID.Text = organ.C_BROKER_ID;
                    ////this.txtBrokerName.Text = organ.C_BROKER_NAME;
                    ////this.txtBrokerIDType.Text = organ.C_BROKER_ID_TYPE;
                    //////// add by wsm 2016-5-10  STORY30235 【招商基金母公司】[QDII]TradeBlotter_EXP TradeBlotter招行导出接口
                    ////this.txtBrokerAccount.Text = organ.C_BROKER_ACCOUNT;
                    //// 机构属性 liuxiang 2015-9-6 STORY #22255 保监会报表需要细化银行属性
                    this.cboAttr.Value = organ.C_DV_ORG_ATTR;
                    /**20160411 added by liubo.STORY #29359 【中行】交易对手方、评级机构的维护
                     行业类别*/
                    ////this.txtIndustryType.Value = organ.C_INDUSTRY_TYPE;
                    this.cboIsRelated.Value = organ.C_ISRElATED;  ////是否为关联方 add by lujianhao 20180705 STORY #51721 光大证券-监管类信息完善
                    ////对联系人信息表格一一赋值
                    if (status == ClsEnums.StatusSetting.YssBrow && rtLinkMan.Table.Rows.Count == 0)
                    {
                        fillLinkManTable(organ.C_ORG_CODE);
                    }
                    else
                    {
                        ////去掉无效的空行
                        RowCollection rows = new RowCollection();
                        rows.AddRange(rtLinkMan.Table.Rows);
                        foreach (Row row in rows)
                        {
                            string text0 = row.Cells[0].InnerControl.Text;
                            string text1 = row.Cells[1].InnerControl.Text;
                            string text2 = row.Cells[2].InnerControl.Text;
                            string text3 = row.Cells[3].InnerControl.Text;
                            string text4 = row.Cells[4].InnerControl.Text;
                            string check = text0 + text1 + text2 + text3 + text4;

                            if ("".Equals(check.Trim()))
                            {
                                foreach (Cell cell in row.Cells)
                                {
                                    if (cell.InnerControl != null)
                                    {
                                        Control c = cell.InnerControl;
                                        c.Dispose();
                                        cell.InnerControl = null;
                                    }
                                }

                                row.Clear(true);
                                rtLinkMan.Table.Rows.Remove(row, true);
                                rtLinkMan.Table.Refresh();
                            }
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// YssOnAfterSaveClick
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ORG_S_YssOnAfterSaveClick(object sender, YssBeforeOperEventArgs e)
        {
            if (this.frmBaseViewList != null)
            {
                (this.frmBaseViewList as Frm_ORG_L).Refresh();
            }
        }

        /// <summary>
        /// 验证信息
        /// </summary>
        /// <returns>true</returns>
        protected override bool checkInput()
        {
            bool check = base.checkInput();
            if (!check)
            {
                return check;
            }
            else
            {
                //// 验证邮箱
                foreach (Row row in rtLinkMan.Table.Rows)
                {
                    string txtEmailAddr = row.Cells[4].InnerControl.Text.Trim();
                    bool checkEmail = ClsFunction.IsEmail(txtEmailAddr) || txtEmailAddr.Length == 0;
                    if (!checkEmail)
                    {
                        ////YssMessageBox.ShowInfo("E-MAIL格式不正确，请重新输入！", "系统提示");
                        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("007", _formFun, ClsEnums.StatusSetting.YssSave));
                        return false;
                    }
                }

                //// 验证网址 add by Yuntao Lau 2015.09.15 STORY #25681
                bool checkUrl = ClsFunction.IsUrl(this.txtWwwAddr.Text) || txtWwwAddr.Text.Length == 0;
                if (!checkUrl)
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("008", _formFun, ClsEnums.StatusSetting.YssSave));
                    return false;
                }

                //// 验证传真号码 add by Yuntao Lau 2015.09.15 STORY #25681
                bool checkFaxTel = ClsFunction.IsFaxTel(this.txtFaxTel.Text) || txtFaxTel.Text.Length == 0;
                if (!checkFaxTel)
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("009", _formFun, ClsEnums.StatusSetting.YssSave));
                    return false;
                }

                /**Start 20160411 added by liubo.STORY #29359 【中行】交易对手方、评级机构的维护
                 对table2里面的必输控件的管控(目前主要是行业分类这个控件)*/
                if (!clsInterface.checkControlsInput(table2))
                {
                    return false;
                }

                ////zhoushuhang 2017-2-24 BUG #152955 【针对中登FISP平台改造主动信息模块】关联机构设置模块投管人信息标签页中部分内容位置偏移
                if (!clsInterface.checkControlsInput(table1))
                {
                    return false;
                }

                ////zhoushuhang 2017-2-24 BUG #152955 【针对中登FISP平台改造主动信息模块】关联机构设置模块投管人信息标签页中部分内容位置偏移
                if (!clsInterface.checkControlsInput(table4))
                {
                    return false;
                }

                /**End 20160411 added by liubo.STORY #29359 【中行】交易对手方、评级机构的维护*/

                /////如果新增上传图片
                if (status == ClsEnums.StatusSetting.YssAdd)
                {
                    if (this.picLogo.ImageLocation != null && this.picLogo.ImageLocation.Trim().Length > 0)
                    {
                        string fullFileName = this.picLogo.ImageLocation;
                        string fileName = System.Guid.NewGuid() + fullFileName.Substring(fullFileName.LastIndexOf("."));
                        if (!UpLoad(fileName, fullFileName))
                        {
                            ////上传logo图片失败
                        }
                        else
                        {
                            this.logFileName = fileName;
                        }
                    }
                }
                else if (status == ClsEnums.StatusSetting.YssEdit)
                {
                    if (this.picLogo.ImageLocation != null && this.picLogo.ImageLocation.Trim().Length > 0)
                    {
                        Org organ = (Org)this.yssGetBaseSelTypeItemMVC();
                        if (!this.picLogo.ImageLocation.EndsWith(organ.C_LOGO_NAME) || organ.C_LOGO_NAME.Trim().Length == 0)
                        {
                            string fullFileName = this.picLogo.ImageLocation;
                            string fileName = System.Guid.NewGuid() + fullFileName.Substring(fullFileName.LastIndexOf("."));
                            if (!UpLoad(fileName, fullFileName))
                            {
                                ////上传logo图片失败
                            }
                            else
                            {
                                this.logFileName = fileName;
                            }
                        }
                    }
                }

                return true;
            }
        }

        /// <summary>
        /// 111
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnLogo_Click(object sender, EventArgs e)
        {
            ClsInterface clsInt = new ClsInterface();
            ////ITemplateService templateService = ServiceFactory.createService<ITemplateService>();
            /* 失败的文件名集合 */
            Dictionary<string, string> failedFiles = new Dictionary<string, string>();
            /* 支持多个压缩文件上传 */
            string[] filesPath = clsInt.filesDialog("选择图片", "图片文件(*.png)|*.png", FAST.Core.Context.ClsConstant.GLOBALPATH);
            ////string filePath = clsInt.fileDialog("选择导入文件", "ZIP文件(*.ZIP)|*.zip", FAST.Core.Context.ClsConstant.GLOBALPATH);
            if (filesPath != null && filesPath.Length > 0)
            {
                string fullFileName = filesPath[0];
                this.picLogo.ImageLocation = fullFileName;
            }
        }

        /// <summary>
        /// 清空图片
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnClearLogo_Click(object sender, EventArgs e)
        {
            this.picLogo.ImageLocation = "";
            this.logFileName = "";
        }

        /// <summary>
        /// 上传文件的方法
        /// </summary>
        /// <param name="serverFileName">服务端文件名</param>
        /// <param name="fullFileName">文件名称</param>
        /// <returns>上传成功返回真值</returns>
        private bool UpLoad(string serverFileName, string fullFileName)
        {
            ITemplateService templateService = ServiceFactory.createService<ITemplateService>();
            bool bResult = false;
            FileStream fs = File.OpenRead(fullFileName);
            try
            {
                int length = 1024 * 1024;
                int block = 0, surplus = 0;
                surplus = (int)fs.Length;
                length = (surplus > length ? length : surplus);
                byte[] buf = new byte[length];
                int index = 0;
                while ((block = fs.Read(buf, 0, length)) > 0)
                {
                    FileTrans fileTrans = new FileTrans();
                    fileTrans.B_FileBytes = buf;
                    fileTrans.C_FileName = serverFileName;
                    fileTrans.N_Index = index++;
                    string sResult = templateService.upload("yssuco/image", fileTrans);
                }

                bResult = true;
            }
            catch (Exception e)
            {
                bResult = false;
            }
            finally
            {
                if (fs != null)
                {
                    fs.Close();
                    fs.Dispose();
                }
            }

            return bResult;
        }

        /// <summary>
        /// 过滤上级机构，不加载自己
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboParentCorp_YssOnAfterLoadData(object sender, YssBeforeOperEventArgs e)
        {
            ////BUG #353027 主题信息设置菜单数据加载问题
            ////if (this.cboParentCorp.Items != null)
            ////{
            ////    int index = -1;

            ////    for (int i = 0; i < e.Collection.Count; i++)
            ////    {
            ////        KTableEntity kte = e.Collection[i] as KTableEntity;
            ////        Org ob = kte.DataEntity as Org;
            ////        if (ob.C_ORG_CODE.Equals(this.txtOrganCode.Text))
            ////        {
            ////            index = i;
            ////            break;
            ////        }
            ////    }

            ////    if (index > -1)
            ////    {
            ////        e.Collection.RemoveAt(index);
            ////    }
            ////}
        }

        /// <summary>
        /// 机构类型改变时,控制机构属性的可见性
        /// liuxiang 2015-9-6 STORY #22255 保监会报表需要细化银行属性
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboOrganType_SelectedValueChanged(object sender, EventArgs e)
        {
            if ("ORG_SYYH" == this.cboOrganType.Value)
            {
                this.rowAttr.Visible = true;
            }
            else
            {
                this.rowAttr.Visible = false;
                this.cboAttr.Value = "";
            }

            this.tbMain.Refresh();
        }

        /// <summary>
        /// 初始化“实体资质”的CHECKBOX
        /// 20170804 chenbo STORY #44886 【基础信息组件】 机构信息设置主体资质属性系统存储机制优化 
        /// </summary>
        private void initCheckBoxes()
        {
            if (status == ClsEnums.StatusSetting.YssAdd && (sumQualification.Columns.Count != 0 || sumQualification.Rows.Count != 0))
            {
                List<CheckBoxCell> st = this.getCheckBoxCell();
                foreach (CheckBoxCell cell in st)
                {
                    if (cell.Text == "发行人" || cell.Text == "对手方")
                    {
                        celIndustryCat.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                        celIndustryCatName.Text = "";
                        //// 隐藏了，属性设置为非必输 By Jinghehe 2017-8-11 
                        txtIndustryType.YssIsMust = false;
                    }

                    cell.Checked = false;
                }
            }
            else
            {
                IVocDataService assSVC = DataServiceFactory.createService<IVocDataService>();
                List<BasePojo> dataList = assSVC.getDataListByTypes(new string[] { "RELA_ORG_TYPE" });
                for (int i = 1; i <= 4; i++)
                {
                    Column col = new Column();
                    this.sumQualification.Columns.Add(col);
                }

                Row row = null;
                int sum = 1;
                foreach (BasePojo basePojo in dataList)
                {
                    if (sum % 4 == 1)
                    {
                        row = new Row();
                        row.Height = 30;
                    }

                    Vocabulary tempVoc = basePojo as Vocabulary;
                    CheckBoxCell cell = new CheckBoxCell();
                    cell.Checked = false;
                    cell.Tag = tempVoc;
                    cell.Text = tempVoc.C_DV_NAME;
                    ////cell. += new EventHandler(cbkOrgType_CheckedChanged);
                    row.Cells.Add(cell);

                    if (sum % 4 == 1)
                    {
                        this.sumQualification.Rows.Add(row);
                    }

                    sum++;
                }

                sumQualification.AutoWidth();

            }

        }

        /// <summary>
        /// 获取选中的实体资质的checkbox的值
        /// 20170804 chenbo STORY #44886 【基础信息组件】 机构信息设置主体资质属性系统存储机制优化 
        /// </summary>
        /// <returns>sReturn</returns>
        private string[] getQualificationChecked()
        {
            string[] sReturn = null;
            string sCheckedItems = "";
            foreach (Row row in sumQualification.Rows)
            {
                foreach (CheckBoxCell cell in row.Cells)
                {
                    if (cell.Checked == true)
                    {
                        Vocabulary tempVoc = cell.Tag as Vocabulary;

                        sCheckedItems += tempVoc.C_DV_CODE + ",";
                    }
                }
            }

            sReturn = Regex.Split(sCheckedItems.Trim(), ",");

            return sReturn;
        }

        /// <summary>
        /// 从sumQualification中取出所有CheckBoxCell的方法
        /// 20170804 chenbo STORY #44886 【基础信息组件】 机构信息设置主体资质属性系统存储机制优化 
        /// </summary>
        /// <returns>st</returns>
        private List<CheckBoxCell> getCheckBoxCell()
        {
            List<CheckBoxCell> st = new List<CheckBoxCell>();
            foreach (Row row in sumQualification.Rows)
            {
                foreach (CheckBoxCell cell in row.Cells)
                {
                    st.Add(cell);
                }
            }

            return st;
        }

        /// <summary>
        /// 根据设置的实体资质，控制checkbox的选中情况
        /// 20170804 chenbo STORY #44886 【基础信息组件】 机构信息设置主体资质属性系统存储机制优化 
        /// </summary>
        /// <param name="sQualications">sQualications</param>
        private void setQualificationCheckBoxesChecked(string sQualications)
        {
            string[] sCheckedList = Regex.Split(sQualications, ",");

            List<CheckBoxCell> st = this.getCheckBoxCell();
            sumQualification.ReadOnly = false;
            foreach (CheckBoxCell cell in st)
            {
                cell.Checked = false;
            }

            for (int i = 0; i < sCheckedList.Length; i++)
            {
                foreach (CheckBoxCell cell in st)
                {
                    Vocabulary tempVoc = cell.Tag as Vocabulary;

                    if (tempVoc.C_DV_CODE.Equals(sCheckedList[i]))
                    {
                        cell.Checked = true;
                    }
                }
            }

            if (status == ClsEnums.StatusSetting.YssBrow)
            {
                sumQualification.ReadOnly = true;
            }
        }
        

        /// <summary>
        /// 重写父类方法，对新加的几个容器的可读状态进行管控
        /// </summary>
        public override void initControlStat()
        {
            base.initControlStat();

            ////this.sumQualification.Enabled = (status == ClsEnums.StatusSetting.YssBrow ? false : true);
            this.table2.ReadOnly = (status == ClsEnums.StatusSetting.YssBrow ? true : false);
            this.table3.ReadOnly = (status == ClsEnums.StatusSetting.YssBrow ? true : false);
            this.table4.ReadOnly = (status == ClsEnums.StatusSetting.YssBrow ? true : false);
            ////zhoushuhang 2017-2-24 BUG #152955 【针对中登FISP平台改造主动信息模块】关联机构设置模块投管人信息标签页中部分内容位置偏移
            this.table1.ReadOnly = (status == ClsEnums.StatusSetting.YssBrow ? true : false);
            this.sumQualification.ReadOnly = (status == ClsEnums.StatusSetting.YssBrow ? true : false);

            //// tabpage需要切换下
            tbDetails.SelectedTab = tabPage3;
            tbDetails.SelectedTab = tabPage1;

            //// BUG #170672 优化【机构基本信息】主体代码修改时应该不允许修改，未审核数据应该优排序，日期和注册资本建议默认值为空
            if (status == ClsEnums.StatusSetting.YssEdit)
            {
                this.txtOrganCode.YssReadOnly = true;
            }
        }

       // /// <summary>
       // /// 行业类别控件加载数据
       // /// </summary>
       // /// <param name="sender">sender</param>
       // /// <param name="e">e</param>
       // /// 基础组件不引用资讯组件，故注释掉  马向峰 20170628 #42948 资讯信息管理组件化拆分
       // //private void cboIndustryCat_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
       // //{
       //     //IPlate_AService plateAService = ServiceFactory.createService<IPlate_AService>();
       //     ////zhoushuhang 20170519 BUG #160288 点击资本币种下拉框报错  加载版块信息设置中版块代码为SAC的数据
       //     //List<Plate> plateList = plateAService.getSacPlateCategory();
       //    // foreach (Plate plate in plateList)
       //    // {
       //        // Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(plate);
       //        // e.Collection.Add(entity);
       //     //}
       //// }

        /// <summary>
        /// 主体资质的所有checkBox的CheckedChanged事件。
        /// 当勾选的checkbox为发行人或者对手方的时候，基本信息最下面一行的cell需要显示信息。
        /// 其他情况下均不显示
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cbkOrgType_CheckedChanged(object sender, EventArgs e)
        {          
            CheckBoxCell cell = sender as CheckBoxCell;
            Vocabulary voc = cell.Tag as Vocabulary;
            if (voc != null)
            {
                if (voc.C_DV_CODE.Equals("ISSUER"))
                {
                    this.issuerChecked = cell.Checked;
                }
                else if (voc.C_DV_CODE.Equals("COUNTERPARTY"))
                {
                    this.counterpartyChecked = cell.Checked;
                }
            }

            if (this.issuerChecked || this.counterpartyChecked)
            {
                celIndustryCat.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowAlways;
                celIndustryCatName.Text = "行业类别：";
                ////属性设置为非必输 By Jinghehe 2017-8-11 
                txtIndustryType.YssIsMust = false;
                ////zhoushuhang 20170519 BUG #160288 点击资本币种下拉框报错  
                celIndustryCat.InnerControl = this.txtIndustryType;
                this.txtIndustryType.Visible = false;
            }
            else
            {
                celIndustryCat.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                celIndustryCatName.Text = "";
                //// 隐藏了，属性设置为非必输 By Jinghehe 2017-8-11 
                txtIndustryType.YssIsMust = false;
            }

            //// By Jinghehe 2017-8-3 控件初始化后，进行校验一下 所绑定应用是否已经启动
            //// 没有启动 控件就只读 不可编辑 
            ControlsLayoutLoader.ValidateServiceComponent(this.Controls);
        }

        /// <summary>
        /// 用于改变投管人性质的时候 证件类型
        /// STORY37444针对中登FISP平台改造主动信息模块  add by xuyuanhao 2017-2-14
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboCastAttr_SelectedValueChanged(object sender, EventArgs e)
        {
            if ("ZTSX_JG".Equals(this.cboCastAttr.Value))
            {
                this.cboInvestorCardType.MethodInfo.MethodName = "getDataListByTypes";
                this.cboInvestorCardType.MethodInfo.MethodParamValues = new string[] { "TZRJG," };
                this.cboInvestorCardType.QueryCond = "TZRJG";
                this.cboInvestorCardType.Refresh();

            }
            else
            {
                this.cboInvestorCardType.MethodInfo.MethodName = "getDataListByTypes";
                this.cboInvestorCardType.MethodInfo.MethodParamValues = new string[] { "TZRGR," };
                this.cboInvestorCardType.QueryCond = "TZRGR";
                this.cboInvestorCardType.Refresh();

            }
        }

        /// <summary>
        /// 20170804 chenbo  STORY #44886  机构信息设置主体资质属性系统存储机制优化
        /// 当对主体资质中的CheckBoxCell进行改变时触发
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void sumQualification_CellMouseClick(object sender, Yss.KTable.Events.CellEventArgs e)
        {
             CheckBoxCell cell = null;
             if (sender is Yss.KTable.Models.CheckBoxCell)
             {
                 cell = (CheckBoxCell)sender;
                 if ("发行人".Equals(cell.Text) || "对手方".Equals(cell.Text))
                 {
                     this.cbkOrgType_CheckedChanged(sender, e);
                 }
             }
        }

        /// <summary>
        /// STORY #68804 维护账户信息的账号、开户行名称、开户行不允许增加空格输入空格自动去掉。业务机构维护大额支付号不允许增加空格，输入空格自动去除。
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void txtPayNum_LostFocus(object sender, EventArgs e)
        {
            if ((status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssEdit
                || status == ClsEnums.StatusSetting.YssCopy))
            {
                this.txtPayNum.Text = this.txtPayNum.Text.Replace(" ", "");
            }
        }
    }
}
