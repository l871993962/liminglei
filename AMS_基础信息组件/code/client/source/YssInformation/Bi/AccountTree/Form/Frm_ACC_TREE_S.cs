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
using YssInformation.Support.Bi.AccountTree.Pojo;
using YssInformation.Support.Bi.AccountTree.Service;
using Yss.KTable.Collections;

namespace YssInformation.Bi.AccountTree.Form
{
    /// <summary>
    /// Frm_ACC_TREE_S
    /// </summary>
    public partial class Frm_ACC_TREE_S : FrmTabItemSet
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
        /// 主界面选择的产品
        /// </summary>
        private AccountTreeB selectedAccTrB = null;

        /// <summary>
        /// 构建函数
        /// </summary>
        public Frm_ACC_TREE_S()
        {
            InitializeComponent();
            this.bUseMVCService = true;
            if (frmBaseViewList != null && frmBaseViewList.tbMain.SelectedRow != null)
            {
                selectedAccTrB = ((AccountTreeB)this.frmBaseViewList.tbMain.SelectedRow.Tag);
            }
        }

        /// <summary>
        /// 资金账户信息设置浏览界面装载事件
        /// 此处用于在窗体装载的时候初始化公用划款账户服务对象（IPubAccService）
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ACC_TREE_S_Load(object sender, EventArgs e)
        {           
        }

        /// <summary>
        /// 初始化控件
        /// </summary>
        public override void yssInitCtlAttr()
        {
            this.cboNodeCodeP.SelectedValueChanged += new EventHandler(cboNodeCodeP_SelectedValueChanged);
        }


        /// <summary>
        /// 初始化控件状态
        /// </summary>
        public override void initControlStat()
        {
            base.initControlStat();
        }

        /// <summary> 
        /// 向后台获取数据，刷新数据
        /// </summary>
        /// <param name="queryType">queryType</param>
        private void getData(string id)
        {
            IFundAccService svc = ServiceFactory.createService<IFundAccService>();
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            try
            {
                if (!string.IsNullOrEmpty(id))
                {
                    paraDict.Add("ARRAY_C_IDEN", id);
                }
                else
                {
                    paraDict.Add("ARRAY_C_NODE_CODE_P_FALSE", this.cboNodeCodeP.Value);
                    paraDict.Add("N_STATE", "1");
                }

                QueryRes res = new QueryRes();
                res = svc.getAccInAccountTreeView(paraDict);

                res.ListHeadList = getHeadInfo();   ////ADD BY ZXL 20130829

                new TableListLoader().loadTable(table1, res, true, true, ClsEnums.KTableDataShowMode.ListMode);

            }
            catch (Exception ex)
            {
                ClsBaseException.DiscardException(ex);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("004", _formFun, status));
            }
        }

        /// <summary>
        /// 获取用户列头信息
        /// ADD BY ZXL 20130829
        /// </summary>
        /// <returns>ListHeadInfo</returns>
        protected List<ListHeadInfo> getHeadInfo()
        {
            List<ListHeadInfo> infoList = new List<ListHeadInfo>();
            ListHeadInfo headInfo = new ListHeadInfo();
            headInfo.Key = "C_OPEN_ACC_NAME";
            headInfo.Text = "账户名称";
            headInfo.Format = "";
            headInfo.Align = "left";
            headInfo.ShowConvert = "false";

            infoList.Add(headInfo);

            headInfo = new ListHeadInfo();
            headInfo.Key = "C_OPEN_ACC_NO";
            headInfo.Text = "账号";
            headInfo.Format = "";
            headInfo.Align = "left";
            headInfo.ShowConvert = "false";

            infoList.Add(headInfo);

            headInfo = new ListHeadInfo();
            headInfo.Key = "C_OPEN_ADDR";
            headInfo.Text = "资产代码";
            headInfo.Format = "";
            headInfo.Align = "left";
            headInfo.ShowConvert = "false";

            infoList.Add(headInfo);


            headInfo = new ListHeadInfo();
            headInfo.Key = "C_ACCOUNT_TYPE";
            headInfo.Text = "账户类型";
            headInfo.Format = "";
            headInfo.Align = "left";
            headInfo.ShowConvert = "true";
            headInfo.ServiceId = "IAccountTypeDataService";

            infoList.Add(headInfo);


            headInfo = new ListHeadInfo();
            headInfo.Key = "C_DC_CODE";
            headInfo.Text = "币种";
            headInfo.Format = "";
            headInfo.Align = "left";
            headInfo.ShowConvert = "true";
            headInfo.ServiceId = "IDCDataService";

            infoList.Add(headInfo);

            return infoList;
        }

        /// <summary>
        /// 新增时返回要操作的一组数据
        /// </summary>
        /// <returns>要操作的一组数据</returns>
        public override ArrayList yssGetObjListMVC()
        {
            ArrayList pojoList = new ArrayList();   //// 创建list列表;
            AccountTreeB accTreeB = null;
            FundAcc fundAcc = null;
            try
            {
                ////循环获取所有选中的数据
                foreach (Row row in this.table1.CheckedRows)
                {
                    fundAcc = row.Tag as FundAcc;
                    accTreeB = new AccountTreeB();
                    accTreeB.C_NODE_CODE_P = this.cboNodeCodeP.Value;
                    accTreeB.C_IDEN_RELA = fundAcc.Id;

                    accTreeB = (AccountTreeB)JsonUtil.toObject(JsonUtil.toJson(accTreeB), accTreeB.GetType());
                    pojoList.Add(accTreeB);
                }
            }
            catch (Exception ex)
            {
                ClsRetInfo info = ClsRetInfoDealer.getCommonError("ERR-000001", _formFun, status);
                info.setSpecStr("dataObject", this.Text);
                info.setSpecStr("operType", "保存");
                YssMessageBox.ShowCommonInfo(info);
                ClsBaseException.DiscardException(ex);
            }

            return pojoList;
        }

        /// <summary>
        /// chongxie
        /// </summary>
        /// <param name="pojoList">11</param>
        ///  <param name="status">11</param>
        /// <returns>11</returns>
        protected override string yssDoSetFormOperMVC(ArrayList pojoList, ClsEnums.StatusSetting status)
        {
            string operResult = "";
            switch (status)
            {
                case ClsEnums.StatusSetting.YssAdd:
                    foreach (object pojo in pojoList)
                    {
                        setAddOperPojoInfo((BasePojo)pojo);
                    }

                    operResult = dataService.insert(pojoList);
                    break;
                case ClsEnums.StatusSetting.YssCopy:
                    foreach (object pojo in pojoList)
                    {
                        setAddOperPojoInfo((BasePojo)pojo);
                    }

                    operResult = dataService.insert(pojoList);
                    break;
                case ClsEnums.StatusSetting.YssEdit:
                    foreach (object pojo in pojoList)
                    {
                        setEditOperPojoInfo((BasePojo)pojo);
                    }

                    operResult = dataService.updateById(pojoList);
                    break;
                default:
                    operResult = base.yssDoSetFormOperMVC(pojoList, status);
                    break;
            }

            return operResult;
        }

        /// <summary>
        /// 功能：设置新增pojo的公共信息
        /// </summary>
        /// <param name="pojo">数据对象</param>
        /// <returns>设置公共信息后的数据对象</returns>
        protected new BasePojo setAddOperPojoInfo(BasePojo pojo)
        {
            if (pojo is ParamPojo)
            {
                ((ParamPojo)pojo).Modifier = ClsContext.currentUser.C_USER_CODE;
                ((ParamPojo)pojo).ModifyDate = ClsFunction.formatDateTime("yyyyMMdd HH:mm:ss", DateTime.Now.ToString());
            }

            if (pojo is AuditableParamPojo)
            {
                ((AuditableParamPojo)pojo).AuditState = (_formFun.N_CHECK == 1) ? 0 : 1;
                
                //// 当未开启审核状态时，这时是需要将审核人，审核时间填到POJO中去的byleeyu20130719
                if (_formFun.N_CHECK <= 0)
                {
                    ((AuditableParamPojo)pojo).OperUser = ClsContext.currentUser.C_USER_CODE;
                    ((AuditableParamPojo)pojo).AuditDate = ClsFunction.formatDateTime("yyyyMMdd HH:mm:ss", DateTime.Now.ToString());
                }
            }

            return pojo;
        }

        /// <summary>
        /// 功能：设置修改pojo的公共信息
        /// </summary>
        /// <param name="pojo">数据对象</param>
        /// <returns>设置公共信息后的数据对象</returns>
        protected new BasePojo setEditOperPojoInfo(BasePojo pojo)
        {
            BasePojo selObj = null;
            if (frmBaseViewList != null)
            {
                frmBaseViewList.IsShowLeftForm = isLeftSetForm;
                selObj = frmBaseViewList.yssGetSelTypeItemMVC();
                frmBaseViewList.IsShowLeftForm = false;
            }

            if ((pojo.Id == null || pojo.Id == "") && selObj != null)
            {
                pojo.Id = selObj.Id;     //// 这里先判断pojo为null的情况byleeyu20140126
            }


            if (pojo is ParamPojo)
            {
                ((ParamPojo)pojo).Modifier = ClsContext.currentUser.C_USER_CODE;
                ((ParamPojo)pojo).ModifyDate = ClsFunction.formatDateTime("yyyyMMdd HH:mm:ss", DateTime.Now.ToString());
            }

            if (pojo is AuditableParamPojo && selObj != null && selObj is AuditableParamPojo)
            {
                
                ((AuditableParamPojo)pojo).AuditState = ((AuditableParamPojo)selObj).AuditState;

                ((AuditableParamPojo)pojo).OperUser = ((AuditableParamPojo)selObj).OperUser;

                //// 当未开启审核状态时，这时是需要将审核人，审核时间填到POJO中去的byleeyu20130719
                if (_formFun.N_CHECK <= 0)
                {
                    ((AuditableParamPojo)pojo).OperUser = ClsContext.currentUser.C_USER_CODE;
                    ((AuditableParamPojo)pojo).AuditDate = ClsFunction.formatDateTime("yyyyMMdd HH:mm:ss", DateTime.Now.ToString());
                }
            }

            return pojo;
        }

        /// <summary>
        /// 获取list中选中记录，为界面元素赋值，显示数据.
        /// -------- 添加记录-----.
        /// 添加时间：20110226.
        /// 添加人：lyh.
        /// 添加简介：对窗体界面元素赋值.
        /// </summary>
        /// <param name="pojo">数据对象</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            try
            {
                if (frmBaseViewList != null && frmBaseViewList.tbMain.SelectedRow != null)
                {
                    selectedAccTrB = ((AccountTreeB)this.frmBaseViewList.tbMain.SelectedRow.Tag);
                }

                if (selectedAccTrB != null)
                {
                    this.cboNodeCodeP.Value = selectedAccTrB.C_NODE_CODE_P;
                    if (status == ClsEnums.StatusSetting.YssBrow || status == ClsEnums.StatusSetting.YssCopy || status == ClsEnums.StatusSetting.YssEdit)
                    {
                        getData(selectedAccTrB.C_IDEN_RELA);

                        if (table1.Rows.Count > 0)
                        {
                            foreach (Row tablerow in table1.Rows)
                            {
                                checkTreeView(tablerow);
                            }
                        }

                        table1.Refresh();
                    }
                }

               
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 递归树型结构，并将每个节点选中
        /// </summary>
        /// <param name="tablerow">行对象</param>
        private void checkTreeView(Row tablerow)
        {
            RowCollection subRows = tablerow.SubRows;

            if (subRows.Count > 0)
            {
                foreach (Row tablesubrow in subRows)
                {
                    checkTreeView(tablesubrow);
                }
            }
            else
            {
                tablerow.Checked = true;
            }

        }

        /// <summary>
        /// 重构保存方法
        /// 添加关联关系至T_P_AB_PORT_ACC_RELA表中
        /// </summary>
        /// <param name="sender"></param>                
        /// <param name="e"></param>
        protected override void btnSave_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrEmpty(this.cboNodeCodeP.Value) || this.table1.CheckedRows.Count < 1)
            {
                new MessageDialog().Show("账户树形节点和账户信息不能为空！", "失败", MessageBoxButtons.OK, MessageBoxIcon.Information);
                return;
            }

            base.btnSave_Click(sender, e);

        }

        /// <summary>
        /// 数据改变的时候
        /// 刷新账户数据
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboNodeCodeP_SelectedValueChanged(object sender, EventArgs e)
        {
            if (status != ClsEnums.StatusSetting.YssBrow && status != ClsEnums.StatusSetting.YssEdit)
            {
                getData("");
            }
        }

        /// <summary>
        /// 获取界面布局、排版用的表格（用于自动化测试时，提取界面控件元素）。
        /// </summary>
        /// <returns>返回表格集</returns>
        protected override List<Table> GetLayoutTables()
        {
            List<Table> loTableList = new List<Table>();
            loTableList.Add(this.tbMain);
            loTableList.Add(this.table1);
            return loTableList;
        }
    }
}
