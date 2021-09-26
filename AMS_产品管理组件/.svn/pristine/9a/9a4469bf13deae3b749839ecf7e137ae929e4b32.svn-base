using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.CRUD.Form;
using FAST.Core.Exceptions;
using FAST.Core.BaseControl.Fun;
using FAST.Core.Communication.Service;
using FAST.Common.Service.Services;
using FAST.Core.Util;
using FAST.Core.Context;
using FAST.Common.Service.Pojo;
using FAST.Core.Resource;
using Yss.KMessage;
using Yss.KRichEx.AutoFilter.Model;
using Yss.KTable.Models;
using YssProductInfo.DataCopy.Form;
using YssProductInfo.Support.Plan.Pojo;
using YssProductInfo.Support.Fun;

namespace YssProductInfo.Plan.Form
{
    /// <summary>
    /// 业务方案
    /// </summary>
    public partial class Frm_BUSINESS_PLAN : FrmBaseSet
    {
        /// <summary>
        /// 功能代码
        /// </summary>
        private const string FUN_CODE = "businessPlan";

        /// <summary>
        /// 组合代码
        /// </summary>
        public string portCodes = "";

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_BUSINESS_PLAN()
        {
            bUseMVCService = true;
            this.InitializeComponent();
        }

        /// <summary>
        /// 封装界面信息
        /// </summary>
        /// <returns>pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            BusinessPlan businessPlan = null;
            try
            {
                businessPlan = new BusinessPlan();
                businessPlan.C_PLAN_CODE = this.txtPlanCode.Value;
                businessPlan.C_PLAN_NAME = this.txtPlanName.Value;
                businessPlan.C_SHARE_LEVEL = this.txtShareLevel.Text;
                businessPlan.C_USER_CODES = oneUsers(getUsers());
                businessPlan.ItemList = getCheckedActItems();
                this.getBusinessType(businessPlan);
                businessPlan.C_ITEM_CODE = " ";
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }

            return businessPlan;
        }

        /// <summary>
        /// 获取list界面所勾选的Item 
        /// </summary>
        /// <returns>list界面所勾选的Item </returns>
        public List<DvaItem> getCheckedActItems()
        {
            List<DvaItem> itemList = new List<DvaItem>();
            ////导出方案 
            if (this.frmBaseViewList is Frm_DataCopy_L)
            {
                itemList = ((Frm_DataCopy_L)this.frmBaseViewList).getCheckedItem();
            }

            return itemList;
        }

        /// <summary>
        /// 获取list界面信息从而来确定方案类型
        /// 如若后期进行扩展可在此处添加
        /// modified by liyanjun 2016-12-21 STORY35703 估值表自检以及自动生成发送电子对账
        /// </summary>
        /// <param name="businessPlan">businessPlan</param>
        public void getBusinessType(BusinessPlan businessPlan)
        {
            if (this.frmBaseViewList is Frm_DataCopy_L)
            {
                businessPlan.C_PLAN_TYPE = ClsBizCons.COPY_PLAN;
            }
        }

        /// <summary>
        /// 窗体load事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void FrmBase_Load(object sender, EventArgs e)
        {
            this.dataService = ServiceFactory.createService<IBusinessPlanService>();
            base.FrmBase_Load(sender, e);
        }

        /// <summary>
        /// 展示对象到窗体
        /// </summary>
        /// <param name="pojo">Pojo</param>
        ////public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo) 
        ////{
        ////    initServiceMVC();
        ////    setPlanCode();
 
        ////}

        /// <summary>
        /// 初始化按钮菜单，此set界面只有保存跟修改按钮
        /// </summary>
        protected override void YssInitTopButtonStat()
        {
            base.YssInitTopButtonStat();            
            this.btnBar.setButtonVisable(ClsButtonName.BtnNext, false);
            this.btnBar.setButtonVisable(ClsButtonName.BtnPrevious, false);
            this.btnBar.setButtonVisable(ClsButtonName.BtnDelete, false);
            this.btnBar.setButtonVisable(ClsButtonName.BtnRecall, false);
            this.btnBar.setButtonVisable(ClsButtonName.BtnEdit, false);
            ////this.btnBar.setButtonEnabled(ClsButtonName.BtnEdit);
            this.btnBar.setButtonVisable(ClsButtonName.BtnSave, true);
            //// MODIFIED BY ZXL 20150819 BUG #117942 
            this.btnBar.setButtonEnabled(ClsButtonName.BtnSave, true, true);
            this.btnBar.setButtonVisable(ClsButtonName.BtnNew, false);
            this.btnBar.setButtonVisable(ClsButtonName.BtnCopy, false);
        }

        /// <summary>
        /// 重写保存事件
        /// edit by Yuntao Lau 2015.11.03 STORY #26731
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnSave_Click(object sender, EventArgs e)
        {
            string planCode = this.txtPlanCode.Text.Trim();
            if (planCode != "")
            {
                string[] users = getUsers();
                this.status = ClsEnums.StatusSetting.YssAdd;
                IBusinessPlanService businessPlanService = ServiceFactory.createService<IBusinessPlanService>();
                if (users != null && users.Contains("InputError!"))
                {
                    MessageDialog msgDialog = new MessageDialog();
                    msgDialog.RelationForm = this.FindForm();
                    msgDialog.ShowIcon = false;
                    msgDialog.Show("当前用户值非法，无法保存");
                    return;
                }

                if (this.frmBaseViewList is Frm_DataCopy_L)
                {
                    businessPlanService.deleteByPlanCode(ClsBizCons.COPY_PLAN, planCode);
                }

                this.getClsAssocia();
                Yss.KTable.Models.Table table = this.tbMain;
                base.btnSave_Click(sender, e);
            }
        }

        /// <summary>
        /// 保存成功，禁止List界面刷新数据
        /// </summary>
        /// <param name="operResult">成功标识</param>
        protected override void operAfterSave(string operResult)
        {
            string sViewStr = "";
            if (ClsRetInfoDealer.isJsonInfo(operResult) && operResult.Trim() != "")
            {
                ClsRetInfo retInfo = ClsRetInfoDealer.getReturnInfo(operResult);
                if (retInfo.operRes == "Success")
                {
                    if (null != frmBaseViewList && !bUseMVCService)
                    {
                        // frmBaseViewList.yssGetListCond() 合过来反而是一个已废弃的方法 2016-09-09 zhanghualin STORY #22607
                        sViewStr = frmBaseViewList.DataAdmin.getListViewData(frmBaseViewList.InitCond /* + frmBaseViewList.yssGetListCond()*/);
                    }
                    else
                    {
                        BusinessPlan plan = getBusinessPlan();
                        if (plan != null)
                        {
                            plan.C_PLAN_CODE = this.txtPlanCode.Text;
                            plan.C_PLAN_NAME = this.txtPlanName.Text;
                            string shareLevel = this.txtShareLevel.Text;
                            ////add by huyingzhao STORY #62315 需求一般：【日常做账】功能涉及【估值方案-设置为首选方案】
                            ////暂时只有两个选项，不用数据库转换，直接逻辑处理
                            if (shareLevel != null)
                            {
                                if (shareLevel.Equals("PUBLIC"))
                                {
                                    shareLevel = "共享";
                                }
                                else if (shareLevel.Equals("PRIVATE"))
                                {
                                    shareLevel = "私有";
                                }
                            }
                            else
                            {
                                shareLevel = "";
                            }

                            plan.C_SHARE_LEVEL = shareLevel;
                            plan.C_USER_CODES = this.txtUsers.Text;

                            if (this.frmBaseViewList is Frm_DataCopy_L)
                            {
                                Frm_DataCopy_L frm = ((Frm_DataCopy_L)this.frmBaseViewList);
                                frm.setCboPlanCode(plan);
                                frm.setCboFirstPlanEidt(false);
                            }

                            this.btnBar.setButtonEnabled(ClsButtonName.BtnSave, false, true);
                            this.btnBar.setButtonVisable(ClsButtonName.BtnEdit, true);
                            this.btnBar.setButtonEnabled(ClsButtonName.BtnEdit, true, true);
                            this.txtShareLevel.YssReadOnly = true;
                            this.txtPlanCode.YssReadOnly = true;
                            this.txtPlanName.YssReadOnly = true;
                            this.txtUsers.YssReadOnly = true;
                        }

                    }
                }


                YssMessageBox.ShowCommonInfo(operResult);
                if (retInfo.operRes == "Success")
                {
                    status = ClsEnums.StatusSetting.YssBrow;
                    initControlStat();
                }
            }
        }

        /// <summary>
        /// 保存成功，禁止List界面刷新数据
        /// </summary>
        /// <param name="operResult">成功标识</param>
        public override void refreshTbMain(string operResult)
        {
        }

        /// <summary>
        /// 重写修改按钮
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnEdit_Click(object sender, EventArgs e)
        {
            this.status = ClsEnums.StatusSetting.YssEdit;
            this.btnBar.setButtonEnabled(ClsButtonName.BtnSave, true, true);
            txtShareLevel.YssReadOnly = false;
            txtPlanCode.YssReadOnly = false;
            txtPlanName.YssReadOnly = false;
            this.txtUsers.YssReadOnly = false;
        }

        /// <summary>
        /// 重新定义set界面的窗体菜单信息
        /// </summary>
        public void getClsAssocia()
        {
            if (FAST.Core.Context.ClsContext.sysFunHash.ContainsKey(FUN_CODE))
            {
                this.YssFormMenu = FAST.Core.Context.ClsContext.sysFunHash[FUN_CODE];
            }
            else
            {
                this.YssFormMenu = new SysFun();
                this.YssFormMenu.C_FUN_CODE = FUN_CODE;
            }

            this.YssFormMenu.YssAssocia = ClsClzCfgMgr.getAssociaParam(FUN_CODE);
        }

        /// <summary>
        /// 设置方案名称和方案代码
        /// </summary>
        public void setPlanCode()
        {
            BusinessPlan plan = getBusinessPlan();
            if (plan != null)
            {
                this.txtPlanCode.Text = plan.C_PLAN_CODE;
                this.txtPlanName.Text = plan.C_PLAN_NAME;
                this.txtShareLevel.Text = plan.C_SHARE_LEVEL;
                if (plan.C_SHARE_LEVEL == "" || plan.C_SHARE_LEVEL == "共享")
                {
                    this.txtUsers.YssReadOnly = true;
                    this.txtUsers.Enabled = false;
                    this.txtUsers.Text = "";
                    this.txtUsers.YssIsMust = false;
                }
                else if (plan.C_SHARE_LEVEL == "私有")
                {
                    this.txtUsers.YssReadOnly = false;
                    this.txtUsers.Enabled = true;
                    this.txtUsers.YssIsMust = true;
                    this.txtUsers.Text = plan.C_USER_CODES;
                }
            }

        }

        /// <summary>
        /// 获取匹配的业务方案pojo
        /// </summary>
        /// <returns>BusinessPlan</returns>
        public BusinessPlan getBusinessPlan()
        {
            BusinessPlan plan = null;
            if (this.frmBaseViewList is Frm_DataCopy_L)
            {
                plan = ((Frm_DataCopy_L)this.frmBaseViewList).getBusinessPlan();
            }

            return plan;
        }

        /// <summary>
        /// 用户处理
        /// </summary>
        /// <returns>users</returns>
        private string[] getUsers()
        {
            string userText = this.txtUsers.Text.Trim();
            string[] users = null;
            if (userText != "")
            {
                if (userText.Contains("|"))
                {
                    users = userText.Split('|');
                }
                else if (!userText.Contains("|"))
                {
                    ////取单个用户
                    users = new string[1];
                    users[0] = userText;
                }
            }
            else
            {
                users = new string[1];
                users[0] = ClsContext.currentUser.C_USER_CODE;
            }

            return users;
        }

        /// <summary>
        /// 单个用户处理
        /// add by huyingzhao 2019年11月1日17:09:07
        /// </summary>
        /// <param name="users">users</param>
        /// <returns>user</returns>
        private string oneUsers(string[] users)
        {
            string userText = this.txtUsers.Text.Trim();
            if (users != null && users.Length == 1 && !userText.Contains("|"))
            {
                userText = userText + "|";
            }

            return userText;
        }

        #region 方案事件

        /// <summary>
        /// 用户点击下拉框前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param> 
        private void txtUsers_BeforeDropDownClick(object sender, EventArgs e)
        {
            string[] users = getUsers();
            if (users == null || users.Contains("InputError!"))
            {
                return;
            }

            foreach (ControlEntity controlEntity in this.txtUsers.Items)
            {
                Row row = controlEntity.Row;
                FAST.Common.Service.Pojo.User user = (FAST.Common.Service.Pojo.User)controlEntity.DataEntity;
                if (users.Contains(user.C_USER_CODE))
                {
                    row.Checked = true;
                }

                if (user.C_USER_CODE == ClsContext.currentUser.C_USER_CODE)
                {
                    row.Checked = true;
                    row.CheckedReadOnly = true;
                }

            }
        }

         
        /// <summary>
        /// 范围下拉框后选中事件
        /// add by huyingzhao 2019年11月1日17:09:07
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void txtShareLevel_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.txtShareLevel.Text.Trim() == "" || this.txtShareLevel.Text == "共享")
            {
                this.txtUsers.YssReadOnly = true;
                this.txtUsers.Enabled = false;
                this.txtUsers.Text = "";
                this.txtUsers.YssIsMust = false;
            }
            else if (this.txtShareLevel.Text == "私有")
            {
                BusinessPlan plan = getBusinessPlan();
                this.txtUsers.YssReadOnly = false;
                this.txtUsers.Enabled = true;
                this.txtUsers.YssIsMust = true;
                if (null != plan)
                {
                    this.txtUsers.Value = plan.C_USER_CODES;
                    txtUsers_BeforeDropDownClick(null, null); 
                }

            }
        }
        #endregion
    }
}