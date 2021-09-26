using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.BaseForm;
using System.Collections;
using YssInformation.Support.Bi.Account.Service;
using FAST.Core.Util;
using FAST.Common.Service.DataService;
using FAST.Core.Context;
using FAST.Core.Communication.Service;
using FAST.Core.Exceptions;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Pojo;
using YssInformation.Support.Bi.Account.Pojo;
using Yss.KTable.Models;
using FAST.Common.Service.Pojo.Base;
using Yss.KMessage;


namespace YssInformation.Bi.Account.Form
{
    /// <summary>
    /// Frm_PORT_FUND_RELA_S
    /// </summary>
    public partial class Frm_PORT_FUND_RELA_S : FrmTabItemSet
    {
        /// <summary>
        /// 声明资金账户信息service对象
        /// </summary>
        private IFundAccService fundAccService = null;

        /// <summary>
        /// 声明资金账户信息service对象
        /// </summary>
        private IPortDataService PortDataService = null;

        /// <summary>
        /// 解析器
        /// </summary>
        private TableListLoader tableListLoader = null;

        /// <summary>
        /// 主界面选择的产品
        /// </summary>
        public YssProductInfo.Support.Ab.Port.Pojo.Port selectedPort = null;

        /// <summary>
        /// Frm_PORT_FUND_RELA_S
        /// </summary>
        public Frm_PORT_FUND_RELA_S()
        {
            InitializeComponent();
            this.bUseMVCService = true;
            this.status = ClsEnums.StatusSetting.YssAdd;
        }

        /// <summary>
        /// 资金账户信息设置浏览界面装载事件
        /// 此处用于在窗体装载的时候初始化公用划款账户服务对象（IPubAccService）
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_PORT_FUND_RELA_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.PortDataService = ServiceFactory.createService(serviceType) as IPortDataService;
            this.dataService = (IServiceBus)this.PortDataService;
            this.btnBar.setButtonDisabled("btnRecall");
            setCboPort();
            loadTableList();
            if (status == ClsEnums.StatusSetting.YssAdd && selectedPort != null)
            {
                getPortBuild();
            } 
        }

        /// <summary>
        /// 初始化控件
        /// </summary>
        public override void yssInitCtlAttr()
        {
        }

        /// <summary>
        /// 设置产品信息控件数据
        /// </summary>
        private void setCboPort()
        {
            this.txtPort.YssReadOnly = true;
            this.txtPort.Text = selectedPort.C_PORT_CODE;
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
                showSpecColumns.Add("C_OPEN_ADDR");
                showSpecColumns.Add("C_OPEN_ACC_NO");
                showSpecColumns.Add("C_OPEN_ACC_NAME");
                paraDict.Add("C_ACCOUNT_TYPE_FALSE", "CPZH_TGH");
                if (!string.IsNullOrEmpty(this.cboAssetType.Value))
                {
                    paraDict.Add("ARRAY_ACCOUNT_TYPE", cboAssetType.Value);
                }

                paraDict.Add("dataClass", "FundAcc");
                fundAccService = ServiceFactory.createService<IFundAccService>();
                queryRes = fundAccService.queryByCondition(paraDict);
                this.tableListLoader.loadTable(table1, queryRes, true, true, ClsEnums.KTableDataShowMode.ListMode, showSpecColumns);

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
        /// 产品账户设置新增时，为开始、结束时间赋值
        /// </summary>
        private void getPortBuild()
        {
            string d_BUILD = "";
            if (selectedPort.D_BUILD != null)
            {
                d_BUILD = selectedPort.D_BUILD;
                if (d_BUILD == null || d_BUILD.Trim().Equals(""))
                {
                    this.yssDateStart.setDateTime(Convert.ToDateTime("1900-01-01"));
                }
                else
                {
                    this.yssDateStart.setDateTime(Convert.ToDateTime(d_BUILD));
                }
            }
            else
            {
                this.yssDateStart.setDateTime(Convert.ToDateTime("1900-01-01"));
            }

            this.yssDateEnd.setDateTime(Convert.ToDateTime("9998-12-31"));
        }

        /// <summary>
        /// 重构保存方法
        /// 添加关联关系至T_P_AB_PORT_ACC_RELA表中
        /// </summary>
        /// <param name="sender">sender</param>                
        /// <param name="e">e</param>
        protected override void btnSave_Click(object sender, EventArgs e)
        {
            ////保存关联关系
            PortRela portRela = new PortRela();
            Dictionary<string, string> paraDict = null;
            if (table1.SelectedRows != null && table1.CheckedRows.Count > 0)
            {
                string portCode = this.txtPort.Text;
                foreach (Row row in this.table1.CheckedRows)
                {
                    paraDict = new Dictionary<string, string>();        
                    string c_rela_code  = (row.Tag as BasePojo).GetValue("Id").ToString();
                    string c_accout_type = (row.Tag as BasePojo).GetValue("C_ACCOUNT_TYPE").ToString();
                    string d_start = this.yssDateStart.getBeginDateStr;
                    string d_end = this.yssDateEnd.getBeginDateStr;
                    paraDict.Add("portCode", portCode);
                    paraDict.Add("id", c_rela_code);
                    paraDict.Add("c_accout_type", c_accout_type);
                    paraDict.Add("d_start", d_start);
                    paraDict.Add("d_end", d_end);
                    fundAccService.savePortFundRelaWithDate(paraDict);
                }

                new MessageDialog().Show("保存银行账户和产品关联关系成功", "提示", MessageBoxButtons.OK);
                this.Dispose();
            }
            else
            {
                new MessageDialog().Show("请选择产品", "提示", MessageBoxButtons.OK);
                return;
            }

        }
    }
}
