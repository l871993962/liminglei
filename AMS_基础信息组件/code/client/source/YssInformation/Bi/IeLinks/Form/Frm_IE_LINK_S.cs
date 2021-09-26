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


using Yss.KRichEx.AutoFilter.Model;
using Yss.KRichEx.AutoFilter.Collections;
using YssInformation.Support.Bi.IeLinks.Service;
using YssInformation.Support.Bi.IeLinks.Pojo;

namespace YssInformation.Bi.IeLinks.Form
{
    /// <summary>
    /// 收支链接set
    /// </summary>
    public partial class Frm_IE_LINK_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private IIeLinkService linkService = null;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_IE_LINK_S()
        {
            InitializeComponent();
            this.bUseMVCService = true;
        }

        /// <summary>
        /// 窗体LOAD 事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_IE_LINK_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.linkService = ServiceFactory.createService(serviceType) as IIeLinkService;
            this.dataService = this.linkService;
        }

        /// <summary>
        /// 控制顶部按钮的状态
        /// </summary>
        protected override void YssInitTopButtonStat()
        {
            base.YssInitTopButtonStat();
            IeLink link = (IeLink)this.yssGetBaseSelTypeItemMVC();
            if (link == null)
            {
                return;
            }

            if (link.C_SRC_MARK.Equals("S"))
            {
                btnBar.setButtonEnabled(ClsButtonName.BtnDelete, false);
                btnBar.setButtonEnabled(ClsButtonName.BtnAudit, false);
                btnBar.setButtonEnabled(ClsButtonName.BtnUnAudit, false);
            }

        }


        /// <summary>
        /// 封装窗体数据到对象
        /// </summary>
        /// <returns>Pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            IeLink link = null;
            try
            {
                link = new IeLink();
                link.C_FEE_CODE = this.cboFee.Value;
                link.C_IE_CODE = this.cboIeItem.Value;
                link.C_FEE_CODE_P = this.cboFeeP.Value == null ? "[root]" : this.cboFeeP.Value;
                link.C_SRC_MARK = "E";
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return link;
        }

        /// <summary>
        /// 展示对象到窗体
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                IeLink link = (IeLink)this.yssGetBaseSelTypeItemMVC();
                if (link == null)
                {
                    return;
                }

                // 将所有的值赋给窗体控件，使其显示
                this.cboFee.Value = link.C_FEE_CODE;
                this.cboIeItem.Value = link.C_IE_CODE;
                this.cboFeeP.Value = link.C_FEE_CODE_P;
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 值改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboFee_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.cboFeeP.Value != null && this.cboFee.Value != null)
            {
                if (this.cboFeeP.Value.Equals(this.cboFee.Value))
                {
                    this.cboFeeP.Value = "";
                    this.cboFeeP.Text = "";
                }
            }
        }

        /// <summary>
        /// 值改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboFeeP_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.cboFeeP.Value != null && this.cboFee.Value != null)
            {
                if (this.cboFee.Value.Equals(this.cboFeeP.Value))
                {
                    this.cboFee.Value = "";
                    this.cboFee.Text = "";
                }
            }
        }

        /// <summary>
        /// 手动加载数据，只加载没有父级的费用
        /// </summary>
        /// <param name="sender">0</param>
        /// <param name="e">0</param>
        private void cboFeeP_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            IIeLinkDataService feeService = DataServiceFactory.createService<IIeLinkDataService>();
            List<BasePojo> pojoList = feeService.getDataListByParentCode(new string[] { "[root]" });

            foreach (BasePojo basePojo in pojoList)
            {
                Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(((IeLink)basePojo));
                e.Collection.Add(entity);
            }

            e.IsCancel = true;
        }

        /// <summary>
        /// 费用按钮点击之后执行
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboFee_AfterDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            ////if (cboFeeP.Value != null && cboFeeP.Value.Trim().Length > 0)
            ////{
            ////    ControlEntityCollection collect = new ControlEntityCollection();
            ////    foreach (ControlEntity entity in this.cboFee.Items)
            ////    {
            ////        if (entity.DisplayValue != null && !entity.DisplayValue.StartsWith(cboFeeP.Value))
            ////        {
            ////            collect.Add(entity);
            ////        }
            ////    }

            ////    foreach (ControlEntity entity in collect)
            ////    {
            ////        cboFee.Items.Remove(entity);
            ////    }
            ////}
        }

    }
}


