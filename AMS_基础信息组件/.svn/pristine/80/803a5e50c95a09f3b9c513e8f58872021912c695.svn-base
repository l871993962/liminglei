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



using FAST.Core.BaseControl.Fun;

using YssInformation.Support.Bi.Region.Pojo;
using YssInformation.Support.Bi.Region.Service;



namespace YssInformation.Bi.Region.Form
{
    /// <summary>
    /// FrmArea 的摘要说明。
    /// 作用：地区信息设置，负责地区信息的增删改等功能
    ///  作者：lyh
    ///  版本：v4.5.0.1
    ///  修改内容：窗体重新绘制，功能方法实现
    ///  修改日期：2010.12.04
    ///    作者：wuwenlan
    ///  版本：v4.5.0.2
    ///  修改内容：窗体重新绘制，功能方法实现
    ///  修改日期：2011.02.14
    ///  －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         返回对象错误直接抛出异常
    /// </summary>
    public partial class Frm_AREA_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private IAreaService areaService = null;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_AREA_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 初始化方法.
        /// </summary>
        public override void yssInitCtlAttr()
        {
            // liuping   2011-03-13  设置界面获取选择list中的所属地区代码
            if (this.bUseMVCService == false)
            {
                ////Cls_AREA area = null;
                ////area = (Cls_AREA)yssGetBaseSelTypeItem();

                ////if (area != null)
                ////{
                ////    this.cboParentRegion.Value = area.C_AREA_CODE_P;
                ////}
            }
            else
            {
               Area area = this.yssGetBaseSelTypeItemMVC() as Area;

               if (area != null)
               {
                   this.cboParentRegion.Value = area.C_AREA_CODE_P;
               }
            }

        }

        /// <summary>
        /// add by wuwenlan 添加鼠标离开控制事件.
        /// 当前输入地区代码满足长度为时3不做任何操作.
        /// 否则自动在前面补0.
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        private void txtAreaCode_Leave(object sender, EventArgs e)
        {
            try
            {
                ////增加状态判断 zhuangyuchen 2011-4-21
                if (status != ClsEnums.StatusSetting.YssBrow)
                {
                    // 定义个变量存储初始时候地区代码
                    string areaCode = ((Yss.KRichEx.YssTextBox)sender).Text.Trim();
                    ////判断地区代码是否满足3为不满足自动补0
                    if (((Yss.KRichEx.YssTextBox)sender).Text.Trim().Length < 3)
                    {
                        //// 根据输入的地区代码长度来加0
                        for (int i = 0; i < (3 - ((Yss.KRichEx.YssTextBox)sender).Text.Trim().Length); i++)
                        {
                            areaCode = "0" + areaCode; // 不满足情况下加0
                        }

                    }

                    ((Yss.KRichEx.YssTextBox)sender).Text = areaCode; // 把加0后的数据赋值地区代码的text

                }

            }
            catch (ClsBaseException ye)
            {
                ClsBaseException.DiscardException(ye);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, ClsEnums.StatusSetting.YssSave));

            }
        }

        /// <summary>
        /// 保存前判断地区代码是否在数据库中
        /// </summary>
        /// <param name="sender">事件对象</param>
        /// <param name="e">事件类型</param>
        private void Frm_AREA_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        {
            //////// 判断地区代码是否在数据库中存在
            ////if (status != ClsEnums.StatusSetting.YssEdit && status != ClsEnums.StatusSetting.YssDel)
            if (status != ClsEnums.StatusSetting.YssDel)
            {
                string areaCode = this.txtAreaCode.Text;
                if (areaCode.Trim().Length < 3)
                {
                    ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("008", _formFun, ClsEnums.StatusSetting.YssSave));
                    TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                    transferErrorMessage.MESSAGESLINK = new List<string>();
                    /* 组装提示信息对象ErrorMessage */
                    string errorMess = ClsRetInfoDealer.getExtWarns("008", _formFun, ClsEnums.StatusSetting.YssSave);
                    transferErrorMessage.MESSAGESLINK.Add(errorMess);
                    throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                }
                ////else
                ////{
                ////string flag = (string)frmBaseViewList.DataAdmin.GetSpecValue(areaCode, "area");

                ////if (flag.Equals("true"))
                ////{
                ////    //// 焦点重新定位
                ////    this.txtAreaCode.Focus();
                ////    ////throw new ClsBaseException("输入地区代码不能重复");
                ////    throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("002", _formFun, ClsEnums.StatusSetting.YssSave));
                ////}

                ////}

            }

        }

        /// <summary>
        /// text控件值改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void txtAreaCode_TextChanged(object sender, EventArgs e)
        {
            if (CheckChinese(this.txtAreaCode.Text.ToString()))
            {
                this.txtAreaCode.Text = "";
            }

            if (this.txtAreaCode.Text.Trim().Length == 0)
            {
                this.txtAreaCode.Text = "";
            }
        }

        /// <summary>
        /// 验证是否是中文汉字
        /// </summary>
        /// <param name="str">str</param>
        /// <returns>bool</returns>
        private bool CheckChinese(string str)
        {
            return System.Text.RegularExpressions.Regex.IsMatch(str, @"^[\u4e00-\u9fa5]{1,}$");
        }

        /// <summary>
        /// 窗体LOAD 事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_AREA_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.areaService = ServiceFactory.createService(serviceType) as IAreaService;
            this.dataService = this.areaService;
        }

        /// <summary>
        /// 封装窗体数据到对象
        /// </summary>
        /// <returns>Pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            Area area = null;
            try
            {
                area = new Area();

                area.C_AREA_CODE = this.txtAreaCode.Text;
                area.C_AREA_NAME = this.txtAreaName.Text;
                area.C_AREA_CODE_P = this.cboParentRegion.Value;
                area.C_AREA_NAME_EN = this.textareaenglishname.Text;
                area.C_AREA_CODE_EN = this.txtenC.Value;
                area.C_AREA_SEQ = this.txtbh.Value;
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return area;
        }

        /// <summary>
        /// 展示对象到窗体
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                Area area = (Area)this.yssGetBaseSelTypeItemMVC();
                if (area == null)
                {
                    return;
                }

                // 将所有的值赋给窗体控件，使其显示
                this.txtAreaCode.Text = area.C_AREA_CODE;
                this.txtAreaName.Text = area.C_AREA_NAME;
                ////由于数据库该字段允许为null在取值时可能字段的值为'null'为防止在页面显示时的错误信息 by tanhongpao 2012-6-15
                this.textareaenglishname.Text = area.C_AREA_NAME_EN.Trim().Equals("null") ? " " : area.C_AREA_NAME_EN;
                if (!area.C_AREA_CODE_P.Equals("[root]"))
                {
                    this.cboParentRegion.Value = area.C_AREA_CODE_P;
                }
                else
                {
                    this.cboParentRegion.Text = "";
                    btnBar.setButtonDisabled(ClsButtonName.BtnUnAudit);
                    ////this.btnUnAudit.Enabled = false;
                }
                ////由于数据库该字段允许为null在取值时可能字段的值为'null'为防止在页面显示时的错误信息 by tanhongpao 2012-6-15
                this.txtenC.Text = area.C_AREA_CODE_EN.Trim().Equals("null") ? " " : area.C_AREA_CODE_EN;
                ////由于数据库该字段允许为null在取值时可能字段的值为'null'为防止在页面显示时的错误信息 by tanhongpao 2012-6-15
                this.txtbh.Text = area.C_AREA_SEQ.Trim().Equals("null") ? " " : area.C_AREA_SEQ;
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 装载所属地区数据
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboParentRegion_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            IAreaService areaService = ServiceFactory.createService<IAreaService>();
            List<Area> areaList = areaService.getAllTopAreas();
            foreach (Area area in areaList)
            {
                Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(area);
                e.Collection.Add(entity);
            }

            ////指定控件不要自动去加载数据
            e.IsCancel = true;
        }

    }
}


