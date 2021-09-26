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
using YssInformation.Support.Bi.SourceRecord.Service;
using YssInformation.Support.Bi.SourceRecord.Pojo;





namespace YssInformation.Bi.SourceRecord.Form
{
    /// <summary>
    /// 功能简介：来源标识信息设置，负责来源表示信息的增删改查等功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.10
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：wuwenlan
    /// 修改日期：20101212
    /// 修改简介：
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：caozhonghu
    /// 修改日期：2011.02.18
    /// 修改简介：需求二次开发
    ///    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：wuwenlan
    /// 修改日期：2011.02.24
    /// 修改简介：实现把控件的属性值的赋值给对象
    ///           把对象的属性值赋给控件
    ///           －－－－修改记录－－－－
    /// 当前版本：V4.5.0.5
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         返回对象错误直接抛出异常
    ///         验证信息正确执行操作，否则屏蔽相应操作
    /// </summary>
    public partial class Frm_SRC_SIGN_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private ISrcSignService srcSignService = null;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_SRC_SIGN_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SRC_SIGN_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.srcSignService = ServiceFactory.createService(serviceType) as ISrcSignService;
            this.dataService = this.srcSignService;
        }

        /// <summary>
        /// 封装窗体数据到对象
        /// </summary>
        /// <returns>Pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            SrcSign clsSrcSign = null;
            try
            {
                clsSrcSign = new SrcSign();

                clsSrcSign.C_SRC_SIGN_CODE = this.txtSignCode.Text;
                clsSrcSign.C_SRC_SIGN_NAME = this.txtSignName.Text;

            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return clsSrcSign;
        }

        /// <summary>
        /// 展示对象属性到窗体
        /// </summary>
        /// <param name="pojo">Pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                SrcSign clsSrcSign = (SrcSign)yssGetBaseSelTypeItemMVC();
                if (null != clsSrcSign)
                {
                    this.txtSignCode.Text = clsSrcSign.C_SRC_SIGN_CODE;
                    this.txtSignName.Text = clsSrcSign.C_SRC_SIGN_NAME;
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }
    }
}


