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
using System.Collections;
using System.Collections.Generic;



using FAST.Core.BaseControl;


////using YssBaseCls.Pojo;
using YssInformation.Support.Pojo;
using YssSecInformation.Support.PlateSet.plateSub.Service;
using YssSecInformation.Support.PlateSet.plate.Pojo;
using YssSecInformation.Support.plate.Service;
using YssSecInformation.Support.PlateSet.plateSub.Pojo;
using YssSecInformation.Support.Pojo.Sec;

namespace YssSecInformation.PlateSet.plateSub.Form
{
    /// <summary>
    /// 板块信息设置B 区set窗体
    /// </summary>
    public partial class Frm_PLATE_IV_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private IPlateSubService plateSubService = null;

        /// <summary>
        /// 板块信息设置初始化方法
        /// </summary>
        public Frm_PLATE_IV_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 重写
        /// </summary>
        public override void initControlStat()
        {
            base.initControlStat();
            ////this.dtend.Enabled = false;
            
        }

        /// <summary>
        /// 初始化控件属性
        /// </summary>
        public override void yssInitCtlAttr()
        {
            this.cbojaoyi.YssReadOnly = true;
           

            if (this.frmBaseViewList.tbLeftMain.SelectedRow != null)
            {
                if (this.bUseMVCService == false)
                {
                Plate plate = (Plate)this.frmBaseViewList.tbLeftMain.SelectedRow.Tag;
                if (plate != null)
                {
                    this.cobplate.Value = plate.C_PLATE_CODE;
                    this.cobplate.Text = plate.C_PLATE_CODE + "_" + plate.C_PLATE_NAME;
                }
                }
                else
                {
                    PlateATreeView plate = (PlateATreeView)this.frmBaseViewList.tbLeftMain.SelectedRow.Tag;
                    if (plate != null)
                    {
                        this.cobplate.Value = plate.C_PLATE_CODE;
                        this.cobplate.Text = plate.C_PLATE_CODE + "_" + plate.C_PLATE_NAME;
                    }
                
                }

                this.dtend.setDateTime(Convert.ToDateTime("9998-12-31"));
            }
        }

        /// <summary>
        /// 根据证券内码得到货币代码和品种代码
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        private void selSec_SelectedValueChanged(object sender, EventArgs e)
        {
            try
            {
                if (((YssSelCombox)sender).SelectedItem != null)
                {
                    //// 当选择交易证券时，应取出交易证券中的币种信息和证券品种信息，并将他们赋值到对应的控件当中
                    SecBase secBase = ((YssSelCombox)sender).SelectedItem.DataEntity as SecBase;
                    if (secBase != null)
                    {
                        this.cbojaoyi.Value = secBase.C_MKT_CODE;
                    }
                }
            }
            catch (Exception ye)
            {
                ClsBaseException.DiscardException(ye);
                ////YssMessageBox.ShowDyanInformation("控件赋值出错", ye.Message, "信息提示", MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-110023", _formFun, status));
            }
        }

        /// <summary>
        /// 复制点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnBarBtnCopy_Clicked(object sender, EventArgs e)
        {
            this.cbojaoyi.YssReadOnly = true;
        }

        /// <summary>
        /// 修改点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnBarBtnEdit_Clicked(object sender, EventArgs e)
        {
            this.cbojaoyi.YssReadOnly = true;
        }

        /// <summary>
        /// 保存前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_PLATE_IV_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        {
            if (ClsFunction.sub(this.dtbegin.getBeginDate, this.dtend.getEndDate) > 0)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, status));
                e.IsCancel = true;
            }

            ////if (status == FAST.Core.Context.ClsEnums.StatusSetting.YssAdd || status == FAST.Core.Context.ClsEnums.StatusSetting.YssCopy
            ////   || status == FAST.Core.Context.ClsEnums.StatusSetting.YssEdit)
            ////{
            ////    ////验证的查询条件
            ////    string cond = "";
            ////    ////组装条件的Json字符串的对象 by tanhongpao 2012-6-19
            ////    ClsQuyStrUtil quryStr = new ClsQuyStrUtil();
            ////    quryStr.addQuyCon("C_PLATE_CODE", this.cobplate.Value, "=");
            ////    ////是修改状态的时候 传递排除自己的条件 不应该验证自己是不是存在交叉日期 by tanhongpao 2012-6-19
            ////    if (status == FAST.Core.Context.ClsEnums.StatusSetting.YssEdit)
            ////    {
            ////        ////获得修改的数据的开始日期和结束日期
            ////        if (this.bUseMVCService = true)
            ////        {
            ////            PlateSub clsplate = (PlateSub)this.yssGetBaseSelTypeItemMVC();
            ////        }
            ////        else 
            ////        {
            ////            Cls_PLATE_SUB clsplate = (Cls_PLATE_SUB)this.yssGetBaseSelTypeItem();
            ////        }

            ////        quryStr.addQuyCon("EDITBEGINDATE", this.dtbegin.getBeginDateStr, "!=");
            ////        quryStr.addQuyCon("EDITEND", this.dtend.getEndDateStr, "!=");
            ////    }

            ////    quryStr.addQuyCon("C_SEC_CODE", this.cbozq.Value, "=");
            ////    quryStr.addQuyCon("D_BEGIN", this.dtbegin.getBeginDateStr, "=");
            ////    quryStr.addQuyCon("D_END", this.dtend.getEndDateStr, "=");

            ////    cond = quryStr.getQuyStr();
            ////    string str = (string)this.dataAdmin.GetSpecValue(cond, "validateDate", _formFun.C_FUN_CODE);
            ////    ////string str = (string)this.dataAdmin.GetSpecValue("" + this.dtbegin.getBeginDateStr + "," + this.dtend.getEndDateStr + "," 
            ////    ////    + this.cbozq.Value + "," + this.cobplate.Value, "validateDate", _formFun.C_FUN_CODE);
            ////    string[] result = Regex.Split(str, "\t");
            ////    if (result[0].Equals("true"))
            ////    {
            ////        ////提示详细的交叉日期信息 by tanhongpao 2012-6-19
            ////        ClsRetInfo info = ClsRetInfoDealer.getExtWarn("002", _formFun, status);
            ////        info.setSpecStr("plateCode", this.cobplate.Text);
            ////        info.setSpecStr("secCode", this.cbozq.Text);
            ////        info.setSpecStr("beginDate", result[1]);
            ////        info.setSpecStr("endDate", result[2]);
            ////        YssMessageBox.ShowCommonInfo(info);
            ////        e.IsCancel = true;
            ////    }
            ////}
        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_PLATE_IV_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.plateSubService = ServiceFactory.createService(serviceType) as IPlateSubService;
            this.dataService = this.plateSubService;
        }

        /// <summary>
        /// 封装窗体到对象
        /// </summary>
        /// <returns>Pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            PlateSub clsplate = null;
            try
            {
                clsplate = new PlateSub();
                clsplate.C_PLATE_CODE = this.cobplate.Value;
                clsplate.C_SEC_CODE = this.cbozq.Value == null ? " " : this.cbozq.Value;
                clsplate.C_MKT_CODE = this.cbojaoyi.Value == null ? " " : this.cbojaoyi.Value;
                clsplate.C_Capital = this.textguben.Text == null ? "0" : Convert.ToString(Convert.ToDecimal(this.textguben.Text));
                clsplate.C_Cir_Capital = this.textliutong.Text == null ? "0" : Convert.ToString(Convert.ToDecimal(this.textliutong.Text));
                clsplate.D_BEGIN = this.dtbegin.getBeginDate;
                clsplate.D_END = this.dtend.getEndDate;
            }
            catch (Exception e)
            {
                ClsBaseException.DiscardException(e);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500043", _formFun, status));
            }

            return clsplate;
        }

        /// <summary>
        /// 展示对象到窗体
        /// </summary>
        /// <param name="pojo">Pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                PlateSub clsplate = (PlateSub)this.yssGetBaseSelTypeItemMVC();
                if (clsplate != null)
                {
                    this.cobplate.Value = clsplate.C_PLATE_CODE;
                    this.cbozq.Value = clsplate.C_SEC_CODE;
                    this.cbojaoyi.Value = clsplate.C_MKT_CODE;
                    if (!clsplate.C_Capital.Equals("null") && clsplate.C_Capital.Trim().Length > 0)
                    {
                        this.textguben.Text = clsplate.C_Capital;
                    }
                    else
                    {
                        this.textguben.Text = "";
                    }

                    if (!clsplate.C_Cir_Capital.Equals("null") && clsplate.C_Cir_Capital.Trim().Length > 0)
                    {
                        this.textliutong.Text = clsplate.C_Cir_Capital;
                    }
                    else
                    {
                        this.textliutong.Text = "";
                    }

                    if (clsplate.D_BEGIN != null)
                    {
                        this.dtbegin.setDateTime(clsplate.D_BEGIN);
                    }

                    if (clsplate.D_END != null)
                    {
                        this.dtend.setDateTime(clsplate.D_END);
                    }
                }

                this.cbojaoyi.YssReadOnly = true;
            }
            catch (Exception e)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500043", _formFun, status));
                ClsBaseException.DiscardException(e);
            }
        }

        /// <summary>
        /// 板块信息下拉框加载数据
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cobplate_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            IPlate_AService plateAService = ServiceFactory.createService<IPlate_AService>();
            List<Plate> plateList = plateAService.getPlateCategory();
            foreach (Plate plate in plateList)
            {
                Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(plate);
                e.Collection.Add(entity);
            }
        }
    }
}


