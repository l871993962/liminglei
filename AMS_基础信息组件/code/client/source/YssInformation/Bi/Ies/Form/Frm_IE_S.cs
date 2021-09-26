using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
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
using YssInformation.Support.Bi.Ies.Service;
using YssInformation.Support.Bi.Ies.Pojo;
namespace YssInformation.Bi.Ies.Form
{
    /// <summary>
    /// 收支代码set窗体
    /// </summary>
    public partial class Frm_IE_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private IIeService service = null;

        /// <summary>
        /// 款项类型服务
        /// </summary>
        ////因为此处调用的是支付平台里的服务，故暂时注释掉
        ////private IHkTypeService typeService = null;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_IE_S()
        {
            InitializeComponent();
            this.bUseMVCService = true;
        }

        /// <summary>
        /// 窗体LOAD 事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_IE_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.service = ServiceFactory.createService(serviceType) as IIeService;
            this.dataService = this.service;
            ////因为此处调用的是支付平台里的服务，故暂时注释掉
            ////this.typeService = ServiceFactory.createService<IHkTypeService>();
        }

        /// <summary>
        /// 控制顶部按钮的状态
        /// </summary>
        protected override void YssInitTopButtonStat()
        {
            base.YssInitTopButtonStat();
            Ie ie = (Ie)this.yssGetBaseSelTypeItemMVC();
            if (ie == null)
            {
                return;
            }

            if (ie.C_SRC_MARK.Equals("S"))
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
            Ie ie = null;
            try
            {
                ie = new Ie();
                ie.C_FEE_CODE = this.txtFeeCode.Text;
                ie.C_FEE_NAME = this.txtFeeName.Text;
                ie.C_SRC_MARK = "E";
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }
            //// 同时将code、name插入款项类型表
            if (status == FAST.Core.Context.ClsEnums.StatusSetting.YssAdd)
            {
                ////因为此处调用的是支付平台里的服务，故暂时注释掉
                ////string ss = typeService.insertHkType(ie.C_FEE_CODE, ie.C_FEE_NAME);
            }

            return ie;
        }

        /// <summary>
        /// 展示对象到窗体
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                Ie ie = (Ie)this.yssGetBaseSelTypeItemMVC();
                if (ie == null)
                {
                    return;
                }

                // 将所有的值赋给窗体控件，使其显示
                this.txtFeeCode.Text = ie.C_FEE_CODE;
                this.txtFeeName.Text = ie.C_FEE_NAME;
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }
    }
}


