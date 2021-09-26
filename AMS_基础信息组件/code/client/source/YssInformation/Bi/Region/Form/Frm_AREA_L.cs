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



using FAST.Core.BaseControl.Fun;
using YssInformation.Support.Bi.Region.Pojo;

namespace YssInformation.Bi.Region.Form
{
    /// <summary>
    /// FrmAreaTree 的摘要说明。
    /// 作用：地区树形加载，负责地区信息的显示
    ///  作者：lyh
    ///  版本：v4.5.0.1
    ///  修改内容：窗体重新绘制，功能方法实现
    ///  修改日期：2010.12.04
    ///  作者：lyh 
    ///  版本：v4.5.0.2
    ///  修改内容：隐藏A区
    ///  修改日期：2010.12.04
    ///   作者：wuwenlan
    ///  版本：v4.5.0.3
    ///  修改内容：实现自定义列头
    ///  和回收站机制数据的添加
    ///  修改日期：2011.02.14
    /// 作者：LYH
    ///  版本：v4.5.0.4
    ///  修改内容：调整代码新结构
    ///  修改日期：2011.02.26
    /// </summary>
    public partial class Frm_AREA_L : FrmBaseList
    {
        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_AREA_L()
        {
            this.bUseMVCService = true;
            
            InitializeComponent();
            ////YssMainKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode; // 树形显示标示
        }

        /////// <summary>
        /////// 根据需要初始化发送到后台的参数项，若无需要可不重写.
        /////// </summary>
        /////// <returns>返回查询条件.</returns>
        ////public override string yssInitQuery()
        ////{
        ////    // 所有提供的参数项如下，只需要设置子类需要的项即可
        ////    // 1 查询条件
        ////    string cond = "";

        ////    // 3 初始只加载列头，若需要则设为true，反之不需要设置此参数
        ////    IsOnlyHeder = true;

        ////    return cond;
        ////}

        /////// <summary>
        /////// 获取list查询条件区的查询条件
        /////// </summary>
        /////// <returns>返回空.</returns>
        ////public string yssGetListCond()
        ////{
        ////    string cond = "";
        ////    ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
        ////    cond = quyStrUtil.getQuyStr(this._formFun.C_FUN_CODE);
        ////    return cond;
        ////}

        /// <summary>
        /// BUG #1621 地区信息设置BUG  根节点不能删除.
        /// 添加人：liuping.
        /// 添加时间：2011-03-31 .
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbMain_RowClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            if (tbMain.SelectedRow == null)
            { 
                return; 
            }

            if (tbMain.SelectedRow.Tag == null)
            { 
                return; 
            }

            ////by weijj 2014721 数据不能审核 所属地区
            base.tbMain_RowClicked(sender, e);
            ////if (this.bUseMVCService)
            ////{
            ////                    
            ////    ////if (((Area)tbMain.SelectedRow.Tag).C_AREA_CODE_P.Equals("[root]"))
            ////    ////{
            ////        ////this.btnUnAudit.Enabled = false;
            ////        btnBar.setButtonEnabled(ClsButtonName.BtnUnAudit, true);
            ////    ////}

            ////}
            ////else
            ////{
            ////    ////if (((Area)tbMain.SelectedRow.Tag).C_AREA_CODE_P.Equals("[root]"))
            ////    ////{
            ////        ////this.btnUnAudit.Enabled = false;
            ////        btnBar.setButtonEnabled(ClsButtonName.BtnUnAudit, true);
            ////    ////}
            ////}

        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_AREA_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.dataService = ServiceFactory.createService(serviceType) as IServiceBus;
        }
    }
}


