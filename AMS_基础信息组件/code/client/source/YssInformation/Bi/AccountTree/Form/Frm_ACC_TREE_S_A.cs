using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.CRUD.Form;
using YssInformation.Support.Bi.AccountTree.Pojo;
using YssInformation.Support.Bi.AccountTree.Service;
using FAST.Core.Communication.Service;
using FAST.Common.Service.Pojo;
using FAST.Core.Util;
using FAST.Common.Service.Pojo.Base;
using Yss.KMessage;
using Yss.KTable.Models;

namespace YssInformation.Bi.AccountTree.Form
{
    /// <summary>
    /// Frm_ACC_TREE_S_A
    /// </summary>
    public partial class Frm_ACC_TREE_S_A : FrmBaseSet
    {
        /// <summary>
        /// AccountTreeA
        /// </summary>
        private AccountTreeA accountTreeASelected = null;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_ACC_TREE_S_A()
        {
            InitializeComponent();
            this.bUseMVCService = true;
        }

        /// <summary>
        /// 初始化界面控件.
        /// </summary>
        public override void yssInitCtlAttr()
        {
        }

        /// <summary>
        /// 展示对象到界面
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            try
            {
                AccountTreeA accTreePojo = yssGetBaseSelTypeItemMVC() as AccountTreeA;
                if (accTreePojo == null)
                {
                    accTreePojo = accountTreeASelected;
                }

                this.txtNodeCode.Text = accTreePojo.C_NODE_CODE;
                this.txtNodeName.Text = accTreePojo.C_NODE_NAME;
                if ("[root]".Equals(accTreePojo.C_NODE_CODE_P))
                {
                    this.cboNodeCodeP.Value = "";
                }
                else
                {
                    this.cboNodeCodeP.Value = accTreePojo.C_NODE_CODE_P;
                }

                this.cboPostCode.Value = accTreePojo.C_POST_CODE;
            }
            catch (System.Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }

        }

        /// <summary>
        /// 封装界面属性到对象
        /// </summary>
        /// <returns>pojo</returns>
        public override AuditableParamPojo faceInfoToObjMVC()
        {
            AccountTreeA accTreePojo = new AccountTreeA();
            try
            {
                accTreePojo.C_NODE_CODE = this.txtNodeCode.Text;
                accTreePojo.C_NODE_NAME = this.txtNodeName.Text;
                if (string.IsNullOrEmpty(this.cboNodeCodeP.Value))
                {
                    accTreePojo.C_NODE_CODE_P = "[root]";
                }
                else
                {
                    accTreePojo.C_NODE_CODE_P = this.cboNodeCodeP.Value;
                }

                accTreePojo.C_POST_CODE = this.cboPostCode.Value;
            }
            catch (System.Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }

            return accTreePojo;
        }

        /// <summary>
        /// 获取界面布局、排版用的表格（用于自动化测试时，提取界面控件元素）。
        /// </summary>
        /// <returns>返回表格集</returns>
        protected override List<Table> GetLayoutTables()
        {
            List<Table> loTableList = new List<Table>();
            loTableList.Add(this.tbMain);
            return loTableList;
        }

        /// <summary>
        /// btnEdit_Click
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnEdit_Click(object sender, EventArgs e)
        {
            base.btnEdit_Click(sender, e);
            this.txtNodeCode.YssReadOnly = true;
        }

        /// <summary>
        /// btnDelete_Click
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnDelete_Click(object sender, EventArgs e)
        {
            IAccountTreeBService accTreeBService = ServiceFactory.createService<IAccountTreeBService>();
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("ARRAY_C_NODE_CODE_P", this.txtNodeCode.Text);
            paraDict.Add("dataClass", "AccountTreeB");
            QueryRes queryRes = accTreeBService.queryByCondition(paraDict);
            if (queryRes.DataList != null && queryRes.DataList.Count > 0)
            {
                new MessageDialog().Show("该账户树形结构已存在数据，不能被删除，如果需要删除，请先删除该账户树形下的数据！", "提示", MessageBoxButtons.OK);
                return;
            }

            base.btnDelete_Click(sender, e);
        }

        /// <summary>
        /// btnSave_Click
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnSave_Click(object sender, EventArgs e)
        {
            base.btnSave_Click(sender, e);
        }
    }
}
