using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Core.Exceptions;


using FAST.Core.Context;

using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;



using FAST.Core.BaseControl.Fun;
using Yss.KTable.Models;
using YssInformation.Support.Bi.Ies.Pojo;


namespace YssInformation.Bi.Ies.Form
{
    /// <summary>
    /// 收支代码list窗体
    /// </summary>
    public partial class Frm_IE_L : FrmBaseList
    {
        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_IE_L()
        {
            InitializeComponent();
            this.bUseMVCService = true;
        }

        /// <summary>
        /// 封装条件对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            if (this.txtFeeCode.Text.Trim().Length > 0)
            {
                paraDict.Add("C_FEE_CODE", "%" + this.txtFeeCode.Text.Trim() + "%");
            }

            if (this.txtFeeName.Text.Trim().Length > 0)
            {
                paraDict.Add("C_FEE_NAME", "%" + this.txtFeeName.Text.Trim() + "%");
            }

            return paraDict;
        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_IE_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.dataService = ServiceFactory.createService(serviceType) as IServiceBus;
        }

        /// <summary>
        /// 行单击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbMain_RowClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            base.tbMain_RowClicked(sender, e);
            if (null == tbMain.SelectedRow)
            {
                return;
            }

            Ie ie = this.tbMain.SelectedRow.Tag as Ie;
            if (ie == null)
            {
                return;
            }
            else
            {
                if (ie.C_SRC_MARK.Equals("S"))
                {
                    btnBar.setButtonEnabled(ClsButtonName.BtnDelete, false);
                    btnBar.setButtonEnabled(ClsButtonName.BtnAudit, false);
                    btnBar.setButtonEnabled(ClsButtonName.BtnUnAudit, false);
                }
            }
        }

        /// <summary>
        /// 复写删除事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnDelete_Click(object sender, EventArgs e)
        {
            if (isCheckedS())
            {
                ////提示内容:操作数据中包含系统数据，操作无效!
                ////YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarnStr("001", this._formFun, status));
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, status));
                return;
            }

            base.btnDelete_Click(sender, e);
        }

        /// <summary>
        /// 复写审核事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnAudit_Click(object sender, EventArgs e)
        {
            if (isCheckedS())
            {
                ////提示内容:操作数据中包含系统数据，操作无效!
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, status));
                return;
            }

            base.btnAudit_Click(sender, e);
        }

        /// <summary>
        /// 复写反审核事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnUnAudit_Click(object sender, EventArgs e)
        {
            if (isCheckedS())
            {
                ////提示内容:操作数据中包含系统数据，操作无效!
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, status));
                return;
            }

            base.btnUnAudit_Click(sender, e);
        }

        /// <summary>
        /// 勾选的数据是否包含系统数据
        /// </summary>
        /// <returns>是否包含</returns>
        private bool isCheckedS()
        {
            bool flag = false;

            foreach (Row row in tbMain.CheckedRows)
            {
                Ie pojo = row.Tag as Ie;
                if (pojo != null && pojo.C_SRC_MARK.ToUpper().Equals("S"))
                {
                    flag = true;
                }
            }

            return flag;
        }
    }
}


