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
using YssInformation.Support.Fun;
////using YssBaseCls.Fun;






namespace YssInformation.Bi.organmgr.Form
{
    /// <summary>
    /// 创建人：liuliang
    /// 创建时间：2012-05-24
    /// 功能简介：机构结算会员LIST界面
    /// 创建版本:V4.5.0.1
    /// </summary>
    public partial class Frm_ORG_MGR_L : FrmBaseList
    {
        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_ORG_MGR_L()
        {
            this.bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写.
        /// </summary>
        ///  <returns>the cond. </returns>
        public override string yssInitQuery()
        {
            //// 所有提供的参数项如下，只需要设置子类需要的项即可
            //// 1 查询条件
            string cond = "";
            //// 3 初始只加载列头，若需要则设为true，反之不需要设置此参数
            IsOnlyHeder = true;
            return cond;
        }

        /// <summary>
        /// 获取List查询区的 查询条件
        /// </summary>
        /// <returns>cond</returns>
        public string yssGetListCond()
        {
            string cond = "";
            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();

            if (null != this.txtMbrCode.Value && this.txtMbrCode.Value.Trim().Length != 0)
            {
                ////会员号
                quyStrUtil.addQuyCon("C_MBR_CODE", this.txtMbrCode.Value, ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            if (null != this.selOrgName.Value && this.selOrgName.Value.Trim().Length != 0)
            {
                ////开户行名称
                quyStrUtil.addQuyCon("C_ORG_CODE", this.selOrgName.Value, "=");
            }

            cond = quyStrUtil.getQuyStr(_formFun.C_FUN_CODE);
            return cond;
        }

        /// <summary>
        /// 封装条件对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            if (null != this.txtMbrCode.Value && this.txtMbrCode.Value.Trim().Length != 0)
            {
                ////会员号
                paraDict.Add("C_MBR_CODE", "%" + this.txtMbrCode.Value + "%");
            }

            if (null != this.selOrgName.Value && this.selOrgName.Value.Trim().Length != 0)
            {
                ////开户行名称
                paraDict.Add("C_ORG_CODE", this.selOrgName.Value);
            }

            //// eidtbyliyongjun 20161122BUG #143781 产品关联信息：结算会员可以不可以关联多笔券商代码
            if (null != this.txtMbrCode.Value && this.txtMbrCode.Value.Trim().Length != 0)
            {
                ////券商编号
                paraDict.Add("C_BROKER_CODE", "%" + this.txtMbrCode.Value + "%");
            }

            return paraDict;
        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ORG_MGR_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.dataService = ServiceFactory.createService(serviceType) as IServiceBus;
        }
    }
}


