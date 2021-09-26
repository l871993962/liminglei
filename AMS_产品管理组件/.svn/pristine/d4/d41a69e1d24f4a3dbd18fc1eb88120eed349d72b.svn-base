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
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using YssProductInfo.Support.Ab.PortGroup.Service;

namespace YssProductInfo.Ab.PortGroup.Form
{
    /// <summary>
    /// 群组管理设置界面
    /// chenwenhai
    /// 20140516
    /// </summary>
    public partial class Frm_PORT_GROUP_A_S : FrmBaseSet
    {
        /// <summary>
        /// 群组A区服务类
        /// </summary>
        private IPortGroupService groupService = null;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_PORT_GROUP_A_S()
        {
            InitializeComponent();
            this.bUseMVCService = true;
            ////this.dataService = ServiceFactory.createService(IPortGroupAService);
        }


        /// <summary>
        /// 封装窗体数据到对象
        /// </summary>
        /// <returns>Pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            YssProductInfo.Support.Ab.PortGroup.Pojo.PortGroup portgroup = null;
            try
            {
                portgroup = new YssProductInfo.Support.Ab.PortGroup.Pojo.PortGroup();

                portgroup.C_GROUP_CODE = this.txtPortGroupCode.Text;
                portgroup.C_GROUP_NAME = this.txtPortGroupName.Text;

            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return portgroup;
        }

        /// <summary>
        /// 展示对象属性到窗体
        /// </summary>
        /// <param name="pojo">Pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                YssProductInfo.Support.Ab.PortGroup.Pojo.PortGroup portgroup = (YssProductInfo.Support.Ab.PortGroup.Pojo.PortGroup)this.frmBaseViewList.yssGetSelTypeItemMVC(frmBaseViewList.tbLeftMain);

                if (portgroup == null)
                {
                    return;
                }

                this.txtPortGroupCode.Text = portgroup.C_GROUP_CODE;
                this.txtPortGroupName.Text = portgroup.C_GROUP_NAME;

            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 保存前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_PORT_GROUP_A_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        {
            //// 1.检查群组代码是否在组合中已经存在，如果存在则不能保存
            string groupCode = this.txtPortGroupCode.Text;
            string isHasData = "false";
            getGroupService();

            isHasData = groupService.checkGroupCode(groupCode);
            YssMessageBox.currentForm = this;
            if ("true".Equals(isHasData))
            {
                e.IsCancel = true; // 屏蔽掉执行保存事件
                ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("001", _formFun, ClsEnums.StatusSetting.YssSave));
                TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                transferErrorMessage.MESSAGESLINK = new List<string>();
                /* 组装提示信息对象ErrorMessage */
                string errorMess = ClsRetInfoDealer.getExtWarns("001", _formFun, ClsEnums.StatusSetting.YssSave);
                transferErrorMessage.MESSAGESLINK.Add(errorMess);
                throw new TransferException(JsonUtil.toJson(transferErrorMessage));
            }
        }

        /// <summary>
        /// 获取群组A区服务类
        /// </summary>
        private void getGroupService()
        {
            if (null == groupService)
            {
                groupService = ServiceFactory.createService<IPortGroupService>();
            }

        }
    }
}


