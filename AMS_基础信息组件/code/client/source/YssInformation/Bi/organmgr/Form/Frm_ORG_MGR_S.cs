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
using YssInformation.Support.Bi.organmgr.Service;
using YssInformation.Support.Bi.organmgr.Pojo;

namespace YssInformation.Bi.organmgr.Form
{
    /// <summary>
    /// 创建人：liuliang
    /// 创建时间：2012-05-24
    /// 功能简介：机构结算会员设置界面
    /// 创建版本:V4.5.0.1
    /// </summary>
    public partial class Frm_ORG_MGR_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private IOrgMgrService orgMgrService = null;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_ORG_MGR_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
            this.dtpStartDate.setDateTime(Convert.ToDateTime("1900-01-01"));
            this.dtpEndDate.setDateTime(Convert.ToDateTime("9998-12-31"));
        }

        /// <summary>
        /// 初始化界面控件
        /// </summary>
        public override void yssInitCtlAttr()
        {
            ////btnBar.Enabled = true;
           
        }

        /// <summary>
        /// 重写
        /// </summary>
        public override void initControlStat()
        {
            base.initControlStat();
            this.btnBar.Enabled = true;
            ////this.dtpEndDate.Enabled = false;
            if (status == ClsEnums.StatusSetting.YssAdd)
            {
                this.dtpEndDate.setDateTime(Convert.ToDateTime("9998-12-31"));
            }
        }

        /// <summary>
        /// 保存前进行数据检查
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ORG_MGR_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        {
            if (this.checkDate() == false)
            {
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
        /// 日期检查
        /// </summary>
        /// <returns>datePassBool</returns>
        private bool checkDate()
        {
            bool datePassBool = true;

            ////开始日期不能大于结束日期
            if (ClsFunction.sub(this.dtpStartDate.getBeginDate, this.dtpEndDate.getBeginDate) > 0)
            {
                datePassBool = false;
            }

            return datePassBool;
        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ORG_MGR_S_Load(object sender, EventArgs e)
        {
            Type serviceTpye = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.orgMgrService = ServiceFactory.createService(serviceTpye) as IOrgMgrService;
            this.dataService = this.orgMgrService;
        }

        /// <summary>
        /// 封装窗体数据到对象
        /// </summary>
        /// <returns>Pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            OrgMgr om = null;
            try
            {
                om = new OrgMgr();

                om.C_MBR_CODE = this.txtMgrCode.Value; ////会员号
                om.C_ORG_CODE = this.selOrgCode.Value; ////机构代码
                om.C_ORG_NAME = 0 == this.txtOrgName.Text.Trim().Length ? " " : this.txtOrgName.Value; ////开户行名称
                om.C_ACC_CODE = 0 == this.txtAccCode.Text.Trim().Length ? " " : this.txtAccCode.Value; ////现金账号
                om.C_CA_CODE = 0 == this.txtCaCode.Text.Trim().Length ? " " : this.txtCaCode.Value; ////账户代码
                om.C_CA_NAME = 0 == this.txtCaName.Text.Trim().Length ? " " : this.txtCaName.Value; ////账户名称
                om.D_BEGIN = "1900-01-01"; ////this.dtpStartDate.getBeginDateStr; ////开始日期 liuxiang 2016-5-20 STORY #28860 上交所跨市场ETF基金产品整体需求
                om.D_END = "9998-12-31"; ////this.dtpEndDate.getBeginDateStr; ////结束日期 liuxiang 2016-5-20 STORY #28860 上交所跨市场ETF基金产品整体需求
                om.C_BROKER_CODE = 0 == this.brokerCode.Text.Trim().Length ? " " : this.brokerCode.Value; ////TASK190987:新增“券商代码”,AddBy-Bohua.Gu-2016/02/26
            }
            catch (Exception ye)
            {
                YssMessageBox.ShowCommonInfo(ye.Message);
            }

            return om;
        }

        /// <summary>
        /// 展示对象属性到窗体
        /// </summary>
        /// <param name="pojo">Pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                OrgMgr om = (OrgMgr)this.yssGetBaseSelTypeItemMVC();
                if (null != om)
                {
                    this.txtMgrCode.Text = om.C_MBR_CODE; ////会员号
                    this.selOrgCode.Value = om.C_ORG_CODE; ////机构代码
                    this.txtOrgName.Text = om.C_ORG_NAME; ////开户行名称
                    this.txtAccCode.Text = om.C_ACC_CODE; ////现金账号
                    this.txtCaCode.Text = om.C_CA_CODE; ////账户代码
                    this.txtCaName.Text = om.C_CA_NAME; ////账户名称
                    this.dtpStartDate.setDateTime(Convert.ToDateTime(om.D_BEGIN)); ////开始日期
                    this.dtpEndDate.setDateTime(Convert.ToDateTime(om.D_END)); ////结束日期
                    this.brokerCode.Text = om.C_BROKER_CODE; ////TASK190987:新增“券商代码”,AddBy-Bohua.Gu-2016/02/26
                }
            }
            catch (Exception e)
            {
                ClsBaseException.DiscardException(e);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-110023", _formFun, status));
            }
        }
    }
}


