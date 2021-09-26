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









namespace YssInformation.Bi.SourceRecord.Form
{   
    ///<summary>
    /// 功能简介：来源标识信息浏览界面，负责来源表示信息的显示和查询功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.10
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：
    /// 修改日期：
    /// 修改简介：
    ///  －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：caozhonghu
    /// 修改日期：2011.02.28
    /// 修改简介：需求二次开发
    /// ---修改记录---
    /// 修改人:wuwenlan  20110224
    /// 修改描述：
    ///         重新写list里面的方法     
    /// </summary>
    public partial class Frm_SRC_SIGN_L : FrmBaseList
    {
        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_SRC_SIGN_L()
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
        /// 获取list查询条件区的查询条件.
        /// </summary>
        /// <returns>the cond. </returns>
        public string yssGetListCond()
        {
            string cond = "";
            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
            if (this.txtSignCode.Text.Trim().Length != 0)
            {
                ////cond = " and a.C_SRC_SIGN_CODE like '%" + this.txtSignCode.Text.Trim() + "%'";  // liuping  2011-03-13  支持模糊查询
                quyStrUtil.addQuyCon("C_SRC_SIGN_CODE", txtSignCode.Text.Trim(), ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            if (this.txtSignName.Text != "")
            {
                ////cond += " and a.C_SRC_SIGN_NAME like '%" + this.txtSignName.Text.Trim() + "%'";
                quyStrUtil.addQuyCon("C_SRC_SIGN_NAME", txtSignName.Text.Trim(), ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            cond = quyStrUtil.getQuyStr(this._formFun.C_FUN_CODE);

            return cond;
        }

        /// <summary>
        /// 封装条件对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            if (this.txtSignCode.Text.Trim().Length != 0)
            {
                paraDict.Add("C_SRC_SIGN_CODE", "%" + txtSignCode.Text.Trim() + "%");
            }

            if (this.txtSignName.Text.Trim().Length > 0)
            {
                paraDict.Add("C_SRC_SIGN_NAME", "%" + txtSignName.Text.Trim() + "%");
            }

            return paraDict;
        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SRC_SIGN_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.dataService = ServiceFactory.createService(serviceType) as IServiceBus;
        }

        /// <summary>
        /// 加载A区方法
        /// </summary>
        public override void yssLoadLeftData()
        {
            ////Orlando 2012-11-22 add 不走基类方法，不用加载A区
        }
    }
}


