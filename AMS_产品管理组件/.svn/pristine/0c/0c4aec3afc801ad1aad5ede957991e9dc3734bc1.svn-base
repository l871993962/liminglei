using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;

using FAST.Core.Exceptions;

using FAST.Core.Context;


using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;

using FAST.Common.Service.Pojo.Base;
using System;


using YssProductInfo.Support.Cp.PubAcc.Pojo;
using YssProductInfo.Support.Cp.PubAcc.Service;


namespace YssProductInfo.Cp.PubAcc.Form
{
    /// <summary>
    /// 功能简介：公用账户信息设置界面处理
    /// 创建人：chenyoulong
    /// 创建日期：20121105
    /// 发布版本：v1.0.0.4
    /// </summary>
    public partial class Frm_PUB_ACC_S : FrmBaseSet
    {
        /// <summary>
        ///  声明公用账户信息服务对象
        /// </summary>
        private IPubAccService pubAccService = null;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_PUB_ACC_S()
        {
            InitializeComponent();
            this.bUseMVCService = true;
        }

        /// <summary>
        /// 公用账户信息设置浏览界面装载事件
        /// 此处用于在窗体装载的时候初始化公用划款账户服务对象（IPubAccService）
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_PUB_ACC_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.pubAccService = ServiceFactory.createService(serviceType) as IPubAccService;
            this.dataService = this.pubAccService;
        }

        /// <summary>
        /// 封装窗体数据到对象
        /// </summary>
        /// <returns>Pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            YssProductInfo.Support.Cp.PubAcc.Pojo.PubAcc pubAcc = null;
            try
            {
                pubAcc = new YssProductInfo.Support.Cp.PubAcc.Pojo.PubAcc();

                pubAcc.C_DC_CODE = this.cboCury.Value;
                pubAcc.C_DV_OPPO_RELA = this.cboIsOppo.Value;
                pubAcc.C_OPEN_ACC_NAME = this.txtOpenName.Text;
                pubAcc.C_OPEN_ACC_NO = this.txtOpenNo.Text;
                pubAcc.C_OPEN_ADDR = this.txtOpenAddr.Text;
                pubAcc.C_SYS_CODE = this.txtSysNum.Text;
                pubAcc.C_USAGE = this.txtPayUse.Text;
                pubAcc.C_DESC = this.txtDesc.Text;

            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return pubAcc;
        }

        /// <summary>
        /// 展示对象到窗体
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                YssProductInfo.Support.Cp.PubAcc.Pojo.PubAcc pubAcc = (YssProductInfo.Support.Cp.PubAcc.Pojo.PubAcc)this.yssGetBaseSelTypeItemMVC();
                if (pubAcc == null)
                {
                    return;
                }

                this.cboCury.Value = pubAcc.C_DC_CODE;
                this.cboIsOppo.Value = pubAcc.C_DV_OPPO_RELA;
                this.txtOpenName.Text = pubAcc.C_OPEN_ACC_NAME;
                this.txtOpenNo.Text = pubAcc.C_OPEN_ACC_NO;
                this.txtOpenAddr.Text = pubAcc.C_OPEN_ADDR;
                this.txtSysNum.Text = pubAcc.C_SYS_CODE;
                this.txtPayUse.Text = pubAcc.C_USAGE;
                this.txtDesc.Text = pubAcc.C_DESC;
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }
    }
}


