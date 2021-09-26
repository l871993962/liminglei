using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
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
using YssInformation.Support.Bi.Holidays_A.Service;
using YssInformation.Support.Bi.Holidays_A.Pojo;

namespace YssInformation.Bi.Holidays_A.Form
{
    /// <summary>
    /// 功能简介：节假日群信息设置，负责节假日群信息的增删改查等功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.13
    ///   －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         返回对象错误直接抛出异常
    /// </summary>
    public partial class Frm_HDAY_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private IHdayGroupService hdayGroupService = null;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_HDAY_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
            this.YssOnBeforeDelClick += new BeforeDelClick(Frm_HDAY_S_YssOnBeforeDelClick);
        }

        /////// <summary>
        /////// 初始化界面控件.
        /////// </summary>
        ////public override void yssInitCtlAttr()
        ////{
        ////    try
        ////    {
        ////        HdayGroup hday = null;
        ////        //// 如果set窗体的状态是新增状态，在点击新增之前事件中已经得到板块代码
        ////        //// 然后将赋值给set窗体窗体中的板块代码
        ////        //// 获取快捷区单选的选中行的对象
        ////        hday = this.frmBaseViewList.getSelectedRowTagMVC(hday) as HdayGroup;
        ////        if (hday != null)
        ////        {
        ////            this.txtHolidaysCode.Text = hday.C_HDAY_CODE;
        ////            this.txtHolidaysName.Text = hday.C_HDAY_NAME;
        ////        }
        ////    }
        ////    catch (Exception ye)
        ////    {
        ////        ClsBaseException.DiscardException(ye);
        ////    }
        ////}

        /// <summary>
        /// 封装窗体数据到对象
        /// </summary>
        /// <returns>Pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            HdayGroup hday = null;
            try
            {
                hday = new HdayGroup();

                hday.C_HDAY_CODE = this.txtHolidaysCode.Text;
                hday.C_HDAY_NAME = this.txtHolidaysName.Text;
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return hday;
        }

        /// <summary>
        /// 展示对象属性到窗体
        /// </summary>
        /// <param name="pojo">Pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                HdayGroup hday = (HdayGroup)this.frmBaseViewList.yssGetSelTypeItemMVC(frmBaseViewList.tbLeftMain);

                if (hday == null)
                {
                    return;
                }

                ////将所有的值赋给窗体控件，使其显示
                this.txtHolidaysCode.Text = hday.C_HDAY_CODE;
                this.txtHolidaysName.Text = hday.C_HDAY_NAME;

            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_HDAY_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.hdayGroupService = ServiceFactory.createService(serviceType) as IHdayGroupService;
            this.dataService = this.hdayGroupService;
        }

        /// <summary>
        /// sender
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_HDAY_S_YssOnBeforeDelClick(object sender, YssBeforeOperEventArgs e)
        {
            ////判断依赖数据是否为空
            if (this.frmBaseViewList.tbMain.Rows.Count > 0)
            {
                YssMessageBox.currentForm = this;
                e.IsCancel = true;
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, status));
            }
        }
    }
}


