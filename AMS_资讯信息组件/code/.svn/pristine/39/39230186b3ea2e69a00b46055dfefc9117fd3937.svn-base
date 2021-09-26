using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;
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

using YssSecInformation.Sv.Form;

using FAST.Core.BaseControl.Fun;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Sv.Pojo;

namespace YssSecInformation.Sv.Form
{
    /// <summary>
    /// chenbo
    /// 2017-06-22
    /// #42948 资讯信息管理组件化拆分
    /// 指数成分券信息list界面
    /// add by liuxaing 2014/8/11
    /// </summary>
    public partial class Frm_INDEX_STOCK_L : FrmBaseList
    {
        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_INDEX_STOCK_L()
        {
            this.ShowFilterPanel = true;
            this.bUseMVCService = true;
            InitializeComponent();
            ////实现附件功能。何讯，20151207
            this.AutoLoadEnclosure = true;
        }

        /// <summary>
        /// 重写基类的行单击事件
        /// </summary>
        /// <param name="sender">事件对象</param>
        /// <param name="e">事件类型</param>
        protected override void tbMain_RowClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            try
            {
                base.tbMain_RowClicked(sender, e);

                if (this.tbMain.SelectedRow == null || this.tbMain.SelectedRow.Tag == null)
                {
                    return;
                }

                IndexStock indexStock = tbMain.SelectedRow.Tag as IndexStock;

                selectRow(indexStock);

                if (frmBaseViewSet != null && !frmBaseViewSet.IsDisposed)
                {
                    frmBaseViewSet.initControlStat();
                    frmBaseViewSet.showInfoMVC(indexStock);
                }

            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }
        }

        /// <summary>
        /// 选中框状态变更 事件 
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbMain_CheckStateChange(object sender, Yss.KTable.Events.CheckStateChangeEventArgs e)
        {
            if (e.CurrentRow != null && e.CurrentRow.Tag is IndexStock)
            {
                bool bCheck = e.CurrentRow.Checked;
                IndexStock indexStock = e.CurrentRow.Tag as IndexStock;
                foreach (Yss.KTable.Models.Row row in tbMain.Rows)
                {
                    IndexStock pojo = row.Tag as IndexStock;
                    //// 判断条件为 指数代码,启用日期
                    if (pojo != null && pojo.C_INDEX_CODE.Equals(indexStock.C_INDEX_CODE) && pojo.D_BEGIN.CompareTo(indexStock.D_BEGIN) == 0)
                    {
                        row.Checked = bCheck;
                    }
                }
            }
        }

        /// <summary>
        /// 选定一条记录时，默认选中相同指数代码、相同启用日期的所有记录
        /// </summary>
        /// <param name="indexStock">当前选定的记录</param>
        public void selectRow(IndexStock indexStock)
        {
            this.tbMain.SelectionChanged -= this.TableMain_SelectionChanged;
            foreach (Yss.KTable.Models.Row row in tbMain.Rows)
            {
                row.Selected = false;
                IndexStock pojo = row.Tag as IndexStock;
                //// 判断条件为 指数代码,启用日期
                if (pojo != null && pojo.C_INDEX_CODE.Equals(indexStock.C_INDEX_CODE) && pojo.D_BEGIN.CompareTo(indexStock.D_BEGIN) == 0)
                {
                    row.Selected = true;
                }
            }

            if (tbMain.SelectedRows.Count > 0 && tbMain.SelectedRows[0].Tag is FAST.Common.Service.Pojo.Base.AuditableParamPojo)
            {
                int nCheckState = (tbMain.SelectedRows[0].Tag as FAST.Common.Service.Pojo.Base.AuditableParamPojo).AuditState;

                btnBar.setButtonEnabled(ClsButtonName.BtnCopy, true);
                btnBar.setButtonEnabled(ClsButtonName.BtnEdit, nCheckState == 0 ? true : false);
                btnBar.setButtonEnabled(ClsButtonName.BtnDelete, nCheckState == 0 ? true : false);

                btnBar.setButtonEnabled(ClsButtonName.BtnAudit, nCheckState == 0 ? true : false);
                btnBar.setButtonEnabled(ClsButtonName.BtnUnAudit, nCheckState == 1 ? true : false);

                if (_formFun.N_CHECK <= 0)
                {
                    btnBar.setButtonEnabled(ClsButtonName.BtnCopy, true);
                    btnBar.setButtonEnabled(ClsButtonName.BtnEdit, true);
                    btnBar.setButtonEnabled(ClsButtonName.BtnDelete, true);
                }
            }

            this.tbMain.SelectionChanged += this.TableMain_SelectionChanged;
        }

        /// <summary>
        /// 屏蔽此事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbMain_BeforeSelectionChanged(object sender, Yss.KTable.Events.RowSelectChangeEventArgs e)
        {
        }

        /// <summary>
        /// 封装查询条件到对象
        /// </summary>
        /// <param name="paraDict">paradict</param>
        /// <returns>e</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            if (!string.IsNullOrEmpty(this.cboIndexCode.Value))
            {
                paraDict.Add("ARRAY_INDEX_CODE", this.cboIndexCode.Value.Replace('|', ','));
            }

            if (!"".Equals(this.txtSecCode.Text))
            {
                paraDict.Add("C_SEC_CODE", "%" + this.txtSecCode.Text + "%");
            }

            paraDict.Add("D_START", this.dtpbeginDate.getBeginDateStr);
            paraDict.Add("D_END", this.dtpbeginDate.getEndDateStr);

            return paraDict;
        }
    }
}


