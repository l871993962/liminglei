using FAST.Common.Service.Pojo.Base;
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
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using YssProductInfo.Support.Cp.PubAcc.Service;




namespace YssProductInfo.Cp.PubAcc.Form
{
    /// <summary>
    /// 公用账户信息设置浏览界面
    /// 创建人：chenyoulong
    /// 创建日期：20121106
    /// 发布版本：v1.0.0.4
    /// </summary>
    public partial class Frm_PUB_ACC_L : FrmBaseList
    {
        /// <summary>
        ///  声明公用账户信息服务对象
        /// </summary>
        private IPubAccService pubAccService = null;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_PUB_ACC_L()
        {
            InitializeComponent();
            this.bUseMVCService = true;
            ////实现附件功能。何讯，20151207
            this.AutoLoadEnclosure = true;
        }

        /// <summary>
        /// 公用账户信息设置浏览界面装载事件
        /// 此处用于在窗体装载的时候初始化公用划款账户服务对象（IPubAccService）
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_PUB_ACC_L_Load(object sender, EventArgs e)
        {
            ////Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            ////this.pubAccService = ServiceFactory.createService<IPubAccService>(); //// (serviceType) as IPubAccService;
            ////this.dataService = this.pubAccService;
        }

        /// <summary>
        /// 初始化服务
        /// </summary>
        protected override void initServiceMVC()
        {
            this.pubAccService = ServiceFactory.createService<IPubAccService>(); //// (serviceType) as IPubAccService;
            this.dataService = this.pubAccService;
        }

        /// <summary>
        /// 初始化查询条件
        /// </summary>
        /// <returns>初始化查询条件</returns>
        public override string yssInitQuery()
        {
            // 所有提供的参数项如下，只需要设置子类需要的项即可
            // 1 查询条件
            string cond = "";

            // 3 初始只加载列头，若需要则设为true，反之不需要设置此参数
            this.IsOnlyHeder = true;

            return cond;
        }

        /// <summary>
        /// 封装条件对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            if (this.txtOpenName.Text.Trim().Length != 0)
            {
                paraDict.Add("C_OPEN_ACC_NAME", "%" + this.txtOpenName.Text + "%");
            }

            if (this.cboCury.Value != null && this.cboCury.Value.Trim().Length != 0)
            {
                paraDict.Add("C_DC_CODE", this.cboCury.Value);
            }

            return paraDict;
        }
    }
}


