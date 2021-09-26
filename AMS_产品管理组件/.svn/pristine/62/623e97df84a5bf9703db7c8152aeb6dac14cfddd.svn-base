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
        /// 构造函数
        /// </summary>
        public Frm_FUND_ACC_RELA_S()
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
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void setCboFundAcc()
        {
            this.txtFundAcc.YssReadOnly = true;
            this.txtFundAcc.Text = selectedFundAcc.C_OPEN_ACC_NAME;
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
                
                paraDict.Add("dataClass", "Port");
                IPortService portService = ServiceFactory.createService<IPortService>();
                queryRes = portService.queryByCondition(paraDict);
                ////IPortRelaCashAccountService relaService = ServiceFactory.createService<IPortRelaCashAccountService>();
                ////dataService = relaService;
                ////queryRes = relaService.queryPortRelaCashAccount(paraDict);
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
            if (table1.SelectedRows != null && table1.CheckedRows.Count > 0)
            {
                string portCodes = "";
                foreach (Row row in this.table1.CheckedRows)
                {
                    string portCode = (row.Tag as BasePojo).GetValue("C_PORT_CODE").ToString();
                    portCodes += portCode + ",";
                }

                if (!string.IsNullOrEmpty(portCodes) && portCodes.Contains(','))
                {
                    portCodes = portCodes.Substring(0, portCodes.Length - 1);
                }

                ////portRela.C_PORT_CODE = portCodes;
                ////portRela.C_RELA_CODE = this.selectedFundAcc.Id;
                ////portRela.C_RELA_TYPE = this.selectedFundAcc.C_ACCOUNT_TYPE;
                fundAccService.savePortFundRela(portCodes, this.selectedFundAcc.Id, this.selectedFundAcc.C_ACCOUNT_TYPE);
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
