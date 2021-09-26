using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
//using YssBaseCls.Interface;
//using YssBaseCls.Fun;
using FAST.Core.CRUD.Form;
using FAST.Core.Context.Events;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Context;
using FAST.Core.Exceptions;
using FAST.Core.Util;
using FAST.Core.Communication.Service;
using FAST.Common.Service.Services;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Interface;
using YssInformation.Support.Bi.AssociationOrgan.Pojo;
using FAST.Core.BaseForm;
using YssInformation.Support.Bi.Account.Service;
using YssInformation.Support.Bi.Account.Pojo;
using System.Collections;
using Yss.KTable.Models;
using Yss.KMessage;
using YssProductInfo.Support.Ab.Port.Service;
using YssInformation.Support.Bi.AssociationOrgan.Service;
using Yss.KRichEx.AutoFilter.Model;
//using YssPara.Pojo.Bi;

namespace YssProductInfo.Ab.Port.Form
{
    /// <summary>
    /// 功能简介：公用账户信息设置界面处理
    /// 创建人：chenyoulong
    /// 创建日期：20121105
    /// 发布版本：v1.0.0.4
    /// </summary>
    public partial class Frm_FUND_ACC_RELA_S : FrmTabItemSet
    {
        /// <summary>
        /// 声明资金账户信息service对象
        /// </summary>
        private IFundAccService fundAccService = null;

        /// <summary>
        /// 解析器
        /// </summary>
        private TableListLoader tableListLoader = null;

        /// <summary>
        /// 主界面选择的账户
        /// </summary>
        public FundAcc selectedFundAcc = null;

        /// <summary>
        /// 保存银行账户类型
        /// </summary>
        private string faType = "";

        /// <summary>
        /// 支付综合参数值：托管户关联多个产品
        /// </summary>
        private string ufpCode = "";

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_FUND_ACC_RELA_S()
        {
            InitializeComponent();
            this.bUseMVCService = true;
            this.status = ClsEnums.StatusSetting.YssAdd;
        }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="faType">faType</param>
        public Frm_FUND_ACC_RELA_S(string faType)
        {
            InitializeComponent();
            this.faType = faType;
            this.bUseMVCService = true;
            this.status = ClsEnums.StatusSetting.YssAdd;
        }

        /// <summary>
        ///  构造函数
        /// </summary>
        /// <param name="faType">faType</param>
        /// <param name="ufpCode">ufpCode</param>
        public Frm_FUND_ACC_RELA_S(string faType, string ufpCode)
        {
            InitializeComponent();
            this.faType = faType;
            this.ufpCode = ufpCode;
            this.bUseMVCService = true;
            this.status = ClsEnums.StatusSetting.YssAdd;
        }
        /// <summary>
        /// 资金账户信息设置浏览界面装载事件
        /// 此处用于在窗体装载的时候初始化公用划款账户服务对象（IPubAccService）
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_FUND_ACC_RELA_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.fundAccService = ServiceFactory.createService(serviceType) as IFundAccService;
            this.dataService = this.fundAccService;
            this.btnBar.setButtonDisabled("btnRecall");
            setCboFundAcc();
            loadTableList();
        }

        /// <summary>
        /// 初始化控件
        /// </summary>
        public override void yssInitCtlAttr()
        {

        }

        /// <summary>
        /// 设置账户信息控件数据
        /// </summary>
        private void setCboFundAcc()
        {
            this.txtFundAcc.YssReadOnly = true;
            this.txtFundAcc.Text = selectedFundAcc.C_OPEN_ACC_NAME;
        }

        /// <summary>
        /// 获取界面布局、排版用的表格（用于自动化测试时，提取界面控件元素）。
        /// </summary>
        /// <returns>返回表格集</returns>
        protected override List<Table> GetLayoutTables()
        {
            List<Table> loTableList = new List<Table>();
            loTableList.Add(this.table1);
            loTableList.Add(this.tbMain);
            return loTableList;
        }

        /// <summary>
        /// 加载table产品界面
        /// </summary>
        private void loadTableList()
        {
            QueryRes queryRes = null;
            Dictionary<string, string> paraDict = null;
            ArrayList showSpecColumns = null;

            try
            {
                if (tableListLoader == null)
                {
                    tableListLoader = new TableListLoader();
                }

                paraDict = new Dictionary<string, string>();
                showSpecColumns = new ArrayList();
                showSpecColumns.Add("C_PORT_CODE");
                showSpecColumns.Add("C_PORT_NAME_ST");
                if (this.selectedFundAcc != null)
                {
                    paraDict.Add("C_RELA_CODE_FALSE", (selectedFundAcc.Id));
                }

                if (!string.IsNullOrEmpty(this.cboAssetType.Value))
                {
                    paraDict.Add("C_DAT_CODE", cboAssetType.Value);
                }

                if (!string.IsNullOrEmpty(this.cptghtype.Value))
                {
                    paraDict.Add("C_DV_TYPE_CODE", cptghtype.Value);
                }

                paraDict.Add("dataClass", "Port");
                IPortService portService = ServiceFactory.createService<IPortService>();
                queryRes = portService.queryByCondition(paraDict);
                ////IPortRelaCashAccountService relaService = ServiceFactory.createService<IPortRelaCashAccountService>();
                ////dataService = relaService;
                ////queryRes = relaService.queryPortRelaCashAccount(paraDict);
                ////if ("CPZH_TGH".Equals(this.faType))
                ////{
                ////    if ("1".Equals(ufpCode))
                ////    {
                ////        this.tableListLoader.loadTable(table1, queryRes, true, true, ClsEnums.KTableDataShowMode.ListMode, showSpecColumns);
                ////    }
                ////    else
                ////    {
                ////        this.tableListLoader.loadTable(table1, queryRes, false, true, ClsEnums.KTableDataShowMode.ListMode, showSpecColumns);
                ////    }
                ////}
                ////else
                ////{
                    this.tableListLoader.loadTable(table1, queryRes, true, true, ClsEnums.KTableDataShowMode.ListMode, showSpecColumns);
                ////}
                table1.Refresh();
            }
            catch (Exception ye)
            {
                ClsBaseException.DiscardException(ye);
                ////YssMessageBox.ShowDyanInformation("初始化界面报错", ye.Message, "信息提示", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-100007", _formFun, status));
            }

        }

        ////private void cboFundAccBeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        ////{
        ////    e.Cancel = true;
        ////    if (e.Items.Count > 0)
        ////    {
        ////        return;
        ////    }

        ////    List<BasePojo> dataList = null;
        ////    Dictionary<string, string> paraDict = new Dictionary<string, string>();
        ////    IFundAccUnifyPayService fundService = ServiceFactory.createService<IFundAccUnifyPayService>();
        ////    if (this.selectedFundAcc != null && !string.IsNullOrEmpty(selectedFundAcc.Id))
        ////    {
        ////paraDict.Add("ids", selectedFundAcc.Id);
        ////paraDict.Add("dataClass", "FundAcc");
        ////QueryRes res = fundAccService.queryByIds(paraDict);
        ////        dataList = qs.DataList;

        ////        this.cboFundAcc.Items.Clear();
        ////        ////初始化下拉框列、Value、Text的显示成员字段
        ////        ////this.InitComboxParameter(combox, comBoxType);
        ////        Dictionary<string, string> existItems = new Dictionary<string, string>();
        ////        if (dataList != null)
        ////        {
        ////            foreach (BasePojo pojo in dataList)
        ////            {
        ////                this.cboFundAcc.Items.Add(new Yss.KRichEx.AutoFilter.Model.KTableEntity(pojo));
        ////            }
        ////        }
        ////    }
        ////}

        /// <summary>
        /// 产品资产类型改变时重新加载table页面
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void cboAssetType_SelectedValueChanged(object sender, EventArgs e)
        {
            this.loadTableList();
        }

        /// <summary>
        /// 初始化控件状态
        /// </summary>
        public override void initControlStat()
        {
            base.initControlStat();
        }

        /// <summary>
        /// 重构保存方法
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected override void btnSave_Click(object sender, EventArgs e)
        {
            ////保存关联关系
            PortRela portRela = new PortRela();
            StringBuilder portCode = new StringBuilder();
            string portCodes = "";
            if (table1.CheckedRows.Count > 0)
            {

                if (this.selectedFundAcc.C_ACCOUNT_TYPE == "CPZH_TGH")
                {
                    string ports = fundAccService.getPortsByRelaCode(this.selectedFundAcc.Id);
                    if ((!string.IsNullOrEmpty(ports) || table1.CheckedRows.Count > 1) )
                    {
                        DialogResult dr = new MessageDialog().Show("托管户关联多个组合，是否继续保存！", "提示", MessageBoxButtons.YesNo);
                        if (dr == DialogResult.No)
                        {
                            return;
                        }

                    }
                }

                foreach (Row row in this.table1.CheckedRows)
                {
                    portCode.Append((row.Tag as BasePojo).GetValue("C_PORT_CODE").ToString() + ",");

                }
            }
            else if (this.table1.SelectedRow != null)
            {
                portCode.Append((this.table1.SelectedRow.Tag as BasePojo).GetValue("C_PORT_CODE").ToString());

            }
            else
            {
                new MessageDialog().Show("请选择产品", "提示", MessageBoxButtons.OK);
                return;
            }
            portCodes = portCode.ToString();
            if (!string.IsNullOrEmpty(portCodes) && portCodes.Contains(','))
            {
                portCodes = portCodes.Substring(0, portCodes.Length - 1);
            }

            ////portRela.C_PORT_CODE = portCodes;
            ////portRela.C_RELA_CODE = this.selectedFundAcc.Id;
            ////portRela.C_RELA_TYPE = this.selectedFundAcc.C_ACCOUNT_TYPE;

            ////STORY #55433 【招商基金】同一个组合维护多个托管户时需要对托管户设置默认账户或增加提醒弹框功能
            ////组合选择不为空，且账户类型为托管户，保存时才提醒
            if (portCodes != null && this.selectedFundAcc.C_ACCOUNT_TYPE == "CPZH_TGH")
            {
                if (fundAccService == null)
                {
                    fundAccService = ServiceFactory.createService<IFundAccService>();
                }
                Dictionary<string, string> dict = new Dictionary<string, string>();
                dict.Add("C_PORT_CODE", portCodes);////组合
                dict.Add("ARRAY_ACCOUNT_TYPE", "CPZH_TGH");////账户类型（托管户）
                //// 获取产品账户信息
                dict.Add("dataClass", "FundAcc");
                QueryRes res = fundAccService.queryByCondition(dict);
                if (res != null && res.DataList != null && res.DataList.Count > 0)
                {
                    DialogResult dr = new MessageDialog().Show("该组合已经维护过托管户信息，请确认是否继续！", "提示", MessageBoxButtons.YesNo);
                    if (dr == DialogResult.No)
                    {
                        return;
                    }
                }
            }


            fundAccService.savePortFundRela(portCodes, this.selectedFundAcc.Id, this.selectedFundAcc.C_ACCOUNT_TYPE);
            new MessageDialog().Show("保存银行账户和产品关联关系成功", "提示", MessageBoxButtons.OK);
            this.Dispose();
        }

        /// <summary>
        /// 下拉加载数据托管人数据
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void cptghtype_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            e.Cancel = true;
            e.Items.Clear();
            IOrgService orgService = ServiceFactory.createService<IOrgService>();
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("dataClass", "Org");
            paraDict.Add("ARRAY_C_PORT_CODE", " ");
            paraDict.Add("C_DV_TYPE_CODE", "TRUSTEE");

            QueryRes res = orgService.getPortRelaOrg(paraDict);
           //// List<Org> orgList = res.DataList;

            ////this.cboTdChannel.KTableTree = false;
            if (res != null && res.DataList.Count > 0)
            {
                foreach (BasePojo pojo in res.DataList)
                {
                    Org org = pojo as Org;
                    KTableEntity entity = new KTableEntity(org);
                    cptghtype.Items.Add(entity);

                }
            }
        }

        /// <summary>
        /// 值改变事件
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void cptghtype_SelectedValueChanged(object sender, EventArgs e)
        {
            this.loadTableList();
        }
    }
}
