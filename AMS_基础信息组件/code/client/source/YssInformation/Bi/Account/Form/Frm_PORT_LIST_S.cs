using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
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

namespace YssInformation.Bi.Account.Form
{
    /// <summary>
    /// STORY #90197 银行账户信息界面新增账户时自动绑定组合
    /// </summary>
    public partial class Frm_PORT_LIST_S : FrmTabItemSet
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
        /// 保存银行账户类型
        /// </summary>
        private string faType = "";

        /// <summary>
        /// 支付综合参数值：托管户关联多个产品
        /// </summary>
        private string ufpCode = "";

        /// <summary>
        /// 组合
        /// </summary>
        private string protList = "";

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_PORT_LIST_S()
        {
            InitializeComponent();
            this.bUseMVCService = true;
            this.status = ClsEnums.StatusSetting.YssAdd;
        }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="faType">faType</param>
        public Frm_PORT_LIST_S(string faType)
        {
            InitializeComponent();
            this.faType = faType;
            this.bUseMVCService = true;
            this.status = ClsEnums.StatusSetting.YssAdd;
        }

        /// <summary>
        /// 构造函数
        /// </summary>
        /// <param name="faType">faType</param>
        /// <param name="ufpCode">ufpCode</param>
        public Frm_PORT_LIST_S(string faType, string ufpCode)
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
        private void Frm_FUND_ACC_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.fundAccService = ServiceFactory.createService(serviceType) as IFundAccService;
            this.dataService = this.fundAccService;
            this.btnBar.setButtonDisabled("btnRecall");
            ////setCboFundAcc();
            loadTableList();
        }

        /// <summary>
        /// 初始化控件
        /// </summary>
        public override void yssInitCtlAttr()
        {
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
                

                if (!string.IsNullOrEmpty(this.cboAssetType.Value))
                {
                    paraDict.Add("C_DAT_CODE", cboAssetType.Value);
                }

                paraDict.Add("dataClass", "Port");
                IPortService portService = ServiceFactory.createService<IPortService>();
                queryRes = portService.queryByCondition(paraDict);
                ////IPortRelaCashAccountService relaService = ServiceFactory.createService<IPortRelaCashAccountService>();
                ////dataService = relaService;
                ////queryRes = relaService.queryPortRelaCashAccount(paraDict);
                if ("CPZH_TGH".Equals(this.faType))
                {
                    if ("1".Equals(ufpCode))
                    {
                        this.tableListLoader.loadTable(table1, queryRes, true, true, ClsEnums.KTableDataShowMode.ListMode, showSpecColumns);
                    }
                    else
                    {
                        this.tableListLoader.loadTable(table1, queryRes, false, true, ClsEnums.KTableDataShowMode.ListMode, showSpecColumns);
                    }
                }
                else
                {
                    this.tableListLoader.loadTable(table1, queryRes, true, true, ClsEnums.KTableDataShowMode.ListMode, showSpecColumns);
                }

                table1.Refresh();
            }
            catch (Exception ye)
            {
                ClsBaseException.DiscardException(ye);
                ////YssMessageBox.ShowDyanInformation("初始化界面报错", ye.Message, "信息提示", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-100007", _formFun, status));
            }

        }

        /// <summary>
        /// 产品资产类型改变时重新加载table页面
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
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
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnSave_Click(object sender, EventArgs e)
        {
            ////保存关联关系
            PortRela portRela = new PortRela();
            StringBuilder portCode = new StringBuilder();
            string portCodes = "";
            if (table1.CheckedRows.Count > 0)
            {
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

           
            if (portCodes != null && this.faType == "CPZH_TGH")
            {
                if (fundAccService == null)
                {
                    fundAccService = ServiceFactory.createService<IFundAccService>();
                }

                Dictionary<string, string> dict = new Dictionary<string, string>();
                dict.Add("C_PORT_CODE", portCodes);             ////组合
                dict.Add("ARRAY_ACCOUNT_TYPE", "CPZH_TGH");     ////账户类型（托管户）
                dict.Add("dataClass", "FundAcc");               //// 获取产品账户信息
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

            this.protList = portCodes;

           //// fundAccService.savePortFundRela(portCodes, this.selectedFundAcc.Id, this.selectedFundAcc.C_ACCOUNT_TYPE);
           ////new MessageDialog().Show("保存银行账户和产品关联关系成功", "提示", MessageBoxButtons.OK);
            this.Dispose();
        }
    }
}
