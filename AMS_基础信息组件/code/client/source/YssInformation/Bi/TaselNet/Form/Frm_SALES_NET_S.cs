using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;
using System;


using FAST.Common.Service.Dict.Pojo;
using YssInformation.Support.Bi.TaselNet.Service;
using YssInformation.Support.Bi.TaselNet.Pojo;

namespace YssInformation.Bi.TaselNet.Form
{   
    /// <summary>
    /// 功能简介：TA销售网点信息设置，负责TA销售网点信息的增删改查等功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.10
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：yh
    /// 修改日期：2010.12.12
    /// 修改简介：实现方法
    ///－－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：wuwenlan
    /// 修改日期：2011.02.16
    /// 修改简介：修改网点类型词汇控件
    ///  －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         返回对象错误直接抛出异常
    /// </summary>
    public partial class Frm_SALES_NET_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private ISalesNetService salesNetService = null;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_SALES_NET_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 添加对销售网点类型的控制.
        /// </summary>
        public override void yssInitCtlAttr()
        {
            Vocabulary clsPubVocabulary = null;
            this.cboTANetType.Value = "DX"; // liuping   2011-03-31     BUG #1612 销售网点设置BUG
            try
            {
                ////如果set窗体的状态是新增状态，在点击新增之前事件中已经得到词汇代码
                ////然后将赋值给set窗体窗体中的网点类型
                clsPubVocabulary = this.frmBaseViewList.getSelectedRowTagMVC(clsPubVocabulary) as Vocabulary;
                if (clsPubVocabulary != null)
                {
                    this.cboTANetType.Value = clsPubVocabulary.C_DV_CODE; // 对网点类型进行赋值
                }

            }
            catch (ClsBaseException e)
            {
                throw new ClsBaseException(e.Message);
            }

        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SALES_NET_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.salesNetService = ServiceFactory.createService(serviceType) as ISalesNetService;
            this.dataService = this.salesNetService;
        }

        /// <summary>
        /// 封装窗体数据到对象
        /// </summary>
        /// <returns>Pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            SalesNet dlstaNet = null;
            try
            {
                dlstaNet = new SalesNet();

                dlstaNet.C_NET_CODE = this.txtTANetCode.Text.Trim();
                dlstaNet.C_NET_NAME = this.txtTANetName.Text.Trim();
                dlstaNet.C_DV_NET_TYPE = this.cboTANetType.Value;
                dlstaNet.C_NET_SOURCE = string.IsNullOrEmpty(this.cboTANetSource.Value) ? "" : this.cboTANetSource.Value;
            }
            catch (Exception ye)
            {
                ClsBaseException.DiscardException(ye);
                ////YssMessageBox.ShowDyanInformation("封装界面元素为对象出错", ye.Message, "错误信息", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonWarn("WRN-000009", _formFun, status));
            }

            return dlstaNet;
        }

        /// <summary>
        /// 展示对象属性到窗体
        /// </summary>
        /// <param name="pojo">Pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            SalesNet dlstaNet = (SalesNet)this.yssGetBaseSelTypeItemMVC();
            try
            {
                if (null != dlstaNet)
                {
                    this.txtTANetCode.Text = dlstaNet.C_NET_CODE;
                    this.txtTANetName.Text = dlstaNet.C_NET_NAME;
                    this.cboTANetType.Value = dlstaNet.C_DV_NET_TYPE;
                    this.cboTANetSource.Value = dlstaNet.C_NET_SOURCE;
                }

            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }
    }
}


