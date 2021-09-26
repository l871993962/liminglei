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

using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;
using System;
using System.Collections.Generic;
using YssSecInformation.Support.plate.Service;
using YssSecInformation.Support.PlateSet.plate.Pojo;






namespace YssSecInformation.PlateSet.plate.Form
{
    /// <summary>
    /// 功能简介：板块分类信息设置，负责证券发行信息的增删改查等功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.13
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：chenyoulong
    /// 修改日期：20110307
    /// 修改简介：根据板块设置需求的重新设计，重新对板块设计进行开发
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         返回对象错误直接抛出异常
    /// </summary>
    public partial class Frm_PLATE_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private IPlate_AService plateSerivce = null;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_PLATE_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 初始化界面控件.
        /// </summary>
        public override void yssInitCtlAttr()
        {
            try
            {
                if (this.bUseMVCService == false)
                {
                    Plate plate = null;

                    //// 如果set窗体的状态是新增状态，在点击新增之前事件中已经得到板块代码
                    //// 然后将赋值给set窗体窗体中的板块代码
                    //// 获取快捷区单选的选中行的对象
                    plate = this.frmBaseViewList.getSelectedRowTagMVC(plate) as Plate;
                    if (plate != null)
                    {
                        this.cboSector.Value = plate.C_PLATE_CODE; // 板块代码
                    }
                }
                else
                {
                    if (status == ClsEnums.StatusSetting.YssAdd)
                    {
                        PlateATreeView plate = null;
                        plate = this.frmBaseViewList.getSelectedRowTagMVC(plate) as PlateATreeView;
                        if (plate != null)
                        {
                            this.cboSector.Value = plate.C_PLATE_CODE;
                        }
                    }
                }
            }
            catch (Exception ye)
            {
                ClsBaseException.DiscardException(ye);
                ////YssMessageBox.ShowDyanInformation("初始化界面报错", ye.Message, "信息提示", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
            }
        }

        /// <summary>
        /// 点击修改按钮后触发的事件.
        /// 控制板块代码不可编辑.
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        private void Frm_PLATE_S_YssOnAfterEditClick(object sender, EventArgs e)
        {
            this.txtSectorCode.YssReadOnly = true;
        }

        /// <summary>
        /// 删除前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_PLATE_S_YssOnBeforeDelClick(object sender, YssBeforeOperEventArgs e)
        {
            if (this.bUseMVCService == false)
            {
                string search = "";
                Plate plate = null;
                ////ArrayList<> = (Frm_PLATE_L)this.frmBaseViewList.yssGetSelTypeItemLeftList();
                if (this.frmBaseViewList.tbLeftMain.SelectedRow != null)
                {
                    plate = (Plate)this.frmBaseViewList.tbLeftMain.SelectedRow.Tag;

                    if (this.frmBaseViewList.tbLeftMain.SelectedRow.SubRows.Count > 0)
                    {
                        _formFun.C_FUN_CODE = "sv_sectorChild_A";
                        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("002", _formFun, status));
                        e.IsCancel = true;
                    }
                    else
                    {
                        search = "'" + plate.C_PLATE_CODE + "'";
                        string result = (string)this.dataAdmin.GetSpecValue(search, "dataExists", "sv_sectorChild_A");
                        if (result.Equals("false"))
                        {
                            _formFun.C_FUN_CODE = "sv_sectorChild_A";
                            YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, status));
                            e.IsCancel = true;
                        }
                    }
                }
            }
            else
            {
                string search = "";
                Plate plate = null;
                ////ArrayList<> = (Frm_PLATE_L)this.frmBaseViewList.yssGetSelTypeItemLeftList();
                if (this.frmBaseViewList.tbLeftMain.SelectedRow != null)
                {
                    plate = (Plate)this.frmBaseViewList.tbLeftMain.SelectedRow.Tag;

                    if (this.frmBaseViewList.tbLeftMain.SelectedRow.SubRows.Count > 0)
                    {
                        _formFun.C_FUN_CODE = "sv_sectorChild_A";
                        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("002", _formFun, status));
                        e.IsCancel = true;
                    }
                    else
                    {
                        IPlate_AService plateAService = ServiceFactory.createService<IPlate_AService>();
                        search = plate.C_PLATE_CODE;
                        string result = (string)plateAService.getSUBData(search);
                        if (result.Equals("false"))
                        {
                            _formFun.C_FUN_CODE = "sv_sectorChild_A";
                            YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, status));
                            e.IsCancel = true;
                        }
                    }
                }
            
            }
            
        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_PLATE_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.plateSerivce = ServiceFactory.createService(serviceType) as IPlate_AService;
            this.dataService = this.plateSerivce;
        }

        /// <summary>
        /// 封装窗体数据到对象
        /// </summary>
        /// <returns>Pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            Plate plate = null;
            try
            {
                plate = new Plate();

                plate.C_PLATE_CODE = this.txtSectorCode.Text; // 板块代码
                plate.C_PLATE_NAME = this.txtSectorName.Text; // 板块名称
                plate.C_PLATE_TYPE = this.cboPlateType.Value; // 板块分类标准
                plate.C_INDEX_CODE = this.cboIndexCode.Value != null ? this.cboIndexCode.Value : ""; // 行业指数代码

                ////plate.D_BEGIN = this.dteBegin.getBeginDate;

                ////plate.D_END = this.dteEnd.getEndDate;
                
                //// Cause: BUG7166
                //// 上级板块信息在板块信息表中不能为空,把删除和更新状态也加上空值处理以防保存时抛出字段为空异常.
                //// Update By Huxingtao    2013-2-27
                if (status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssEdit || status == ClsEnums.StatusSetting.YssDel)
                {
                    if (null != this.cboSector.Value && this.cboSector.Value.Trim().Length > 0)
                    {
                        plate.C_PLATE_CODE_P = this.cboSector.Value;
                    }
                    else
                    {
                        plate.C_PLATE_CODE_P = "[root]";
                    }
                }
                else 
                {
                    plate.C_PLATE_CODE_P = this.cboSector.Value;
                }
                
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return plate;
        }

        /// <summary>
        /// 展示对象属性到窗体
        /// </summary>
        /// <param name="pojo">Pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                if (this.frmBaseViewList.tbLeftMain.SelectedRow != null)
                {
                    Plate plate = (Plate)this.frmBaseViewList.tbLeftMain.SelectedRow.Tag;
                    if (null != plate)
                    {
                        this.txtSectorCode.Text = plate.C_PLATE_CODE; // 板块代码
                        this.txtSectorName.Text = plate.C_PLATE_NAME; // 板块名称
                        this.cboPlateType.Value = plate.C_PLATE_TYPE;
                        this.cboIndexCode.Value = plate.C_INDEX_CODE != null ? plate.C_INDEX_CODE : "";
                        ////this.dteBegin.setDateTime(Convert.ToDateTime(plate.D_BEGIN));
                        ////this.dteEnd.setDateTime(Convert.ToDateTime(plate.D_END));

                        if (plate.C_PLATE_CODE_P.Equals("[root]"))
                        {
                            this.cboSector.Text = ""; // 上级板块代码
                        }
                        else
                        {
                            this.cboSector.Value = plate.C_PLATE_CODE_P; // 上级板块代码
                        }

                        ////this.txtSectorCode.YssReadOnly = true;
                    }
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 上级板块信息下拉框加载数据
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboSector_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            IPlate_AService plateAService = ServiceFactory.createService<IPlate_AService>();
            List<Plate> plateList = plateAService.getPlateCategory();
            foreach (Plate plate in plateList)
            {
                Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(plate);
                e.Collection.Add(entity);
            }
        }

        /// <summary>
        /// 新增根节点前验证
        /// </summary>
        /// <param name="sender">d</param>
        /// <param name="e">d</param>
        private void Frm_PLATE_S_YssOnAfterCopyClick(object sender, EventArgs e)
        {
            this.cboSector.YssReadOnly = false;
            if (this.frmBaseViewList.tbLeftMain.SelectedRow != null)
            {
                if (this.frmBaseViewList.tbLeftMain.SelectedRow.ParentRow == null)
                {
                    this.cboSector.Value = "[root]";
                }
            }
        }

        /// <summary>
        /// d
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_PLATE_S_YssOnAfterNewClick(object sender, EventArgs e)
        {
            this.cboSector.Text = "";
            this.cboSector.YssReadOnly = false;
        }

        /// <summary>
        /// 行业指数代码加载前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboIndexCode_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            this.cboIndexCode.DisplayValue = "C_INDEX_CODE";
        }

    }
}


