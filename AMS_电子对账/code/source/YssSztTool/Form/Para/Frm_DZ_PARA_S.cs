using System;
using System.Collections.Generic;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Communication.Service;
using FAST.Core.Context;
using FAST.Core.Context.Events;
using FAST.Core.CRUD.Form;
using FAST.Core.Exceptions;
using FAST.Common.Service.Pojo;
using YssSztTool.Pojo.Para;
using YssSztTool.Service.Para;
using YssInformation.Support.Bi.AssociationOrgan.Pojo;

namespace YssSztTool.Form.Para
{
    /// <summary>
    /// 电子对账参数设置
    /// </summary>
    public partial class Frm_DZ_PARA_S : FrmBaseSet
    {
        /// <summary>
        /// 机构信息Service
        /// </summary>
        private IErOrgService orgService = null;
        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_DZ_PARA_S()
        {
            bUseMVCService = true;
            InitializeComponent();
            orgService = ServiceFactory.createService<IErOrgService>();
        }

        /// <summary>
        /// 显示单条数据(新增时设置默认值)
        /// STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
        /// </summary>
        public override void yssShowInfoAddForm()
        {
            ////默认压缩BASE64
            this.cboSecretType.Value = "ELEC_ST_BASE64";
            ////默认GBK
            this.cboCharset.Value = "GBK";
        }

        /// <summary>
        /// STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
        /// </summary>
        /// <param name="sender">发送对象</param>
        /// <param name="e">事件对象</param>
        private void cboSecretType_SelectedValueChanged(object sender, EventArgs e)
        {
            if ("EL_AES_ECB_CS5P".Equals(this.cboSecretType.Value))
            {
                this.txtSecretKey.YssIsMust = true;
            }
            else
            {
                this.txtSecretKey.YssIsMust = false;
            }
        }

        /// <summary>
        /// 显示单条数据，参数为set界面数据对应的pojo对象
        /// </summary>
        /// <param name="pojo">显示数据对应的pojo对象</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            try
            {
                DzPara dzPara = this.frmBaseViewList.tbMain.SelectedRow.Tag as DzPara;
                ////this.cboAssCode.Value = dzPara.C_ASS_CODE;
                this.cboBank.Value = dzPara.C_TGH_CODE;
                this.cboBusType.Value = dzPara.C_BUS_TYPE;
                this.cboCommType.Value = dzPara.C_COMM_TYPE;
                ////STORY24576 电子对账工行仍然没有支持-新增字段
                this.cboDZMode.Value = dzPara.C_DZ_MODE;
                this.txtDeptCode.Text = dzPara.C_DEPT_CODE;
                this.txtCertId.Text = dzPara.C_CERT_ID;
                this.txtUser.Text = dzPara.C_TARGET_USER;
                this.txtTargetLogo.Text = dzPara.C_TARGET_APP_LOGO;
                this.cboGzbMode.Value = dzPara.C_GZB_MODE;
                ////STORY #35703 估值表自检以及自动生成发送电子对账
                ////edit by liyanjun 2017-1-3 BUG #149241 华泰资管 自动发送电子对账数据相关BUG
                this.txtHighTime.Value = String.IsNullOrEmpty(dzPara.C_High_Time) ? 0 : Convert.ToInt16(dzPara.C_High_Time);
                this.txtInterval.Text = dzPara.C_Interval;
				////STORY42660【中国银行】深证通伺服器要求采用热备模式
                this.cboErPara.Value = dzPara.C_ErPara_Code;
                ////STORY #49489 光大证券-产品关联电子对账设置优化
                this.cboBranchBank.Value = dzPara.C_Has_Branch;
                //STORY55269【富国基金】支持电子对账参数设置支持多管理人
                this.cboManagerOrg.Value = dzPara.C_MANAGE_CODE;
                ////STORY58495【海通证券】电子对账的科目名称新增字段
                this.txtKMNameLH.Text = dzPara.C_KM_NAME_LENGTH;
                ////STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
                this.cboSecretType.Value = dzPara.C_DV_SECRETTYPE;
                this.cboCharset.Value = dzPara.C_DV_CHARSET;
                this.txtSecretKey.Text = (this.dataService as IDzParaService).decryptData(dzPara.C_SECRETKEY);
                this.cboLicOrg.Value = "OLD_LIC".Equals(dzPara.C_DV_LICORG) ? "" : dzPara.C_DV_LICORG;
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }
            
        }

        /// <summary>
        /// 数据的保存事件
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnSave_Click(object sender, System.EventArgs e)
        {
            DzPara pojo = faceInfoToBaseObjMVC () as DzPara;
            Dictionary<string, string> paraDict = new Dictionary<string,string>();
            paraDict.Add("dataClass", "DzPara");
            paraDict.Add("C_TGH_CODE", pojo.C_TGH_CODE);
            paraDict.Add("C_BUS_TYPE", pojo.C_BUS_TYPE);
            //BUG #212634 【中银国际】_电子对账参数设置_在没有其他参数的情况下，新增数据报错问题
            if (frmBaseViewList.tbMain.SelectedRow != null && frmBaseViewList.tbMain.SelectedRow.Tag != null && frmBaseViewList.tbMain.SelectedRow.Tag is BasePojo
                && status != ClsEnums.StatusSetting.YssCopy)
            {
                paraDict.Add("C_NO_ID", (this.frmBaseViewList.tbMain.SelectedRow.Tag as DzPara).Id);
            }
            if (pojo.C_MANAGE_CODE == null || "".Equals(pojo.C_MANAGE_CODE.Trim()))
            {
                paraDict.Add("C_MANAGE_CODE_NULL", "1");
            }else
            {
                paraDict.Add("C_MANAGE_CODE", pojo.C_MANAGE_CODE);
            }
            if (dataService.queryByCondition(paraDict).DataList.Count != 0)
            {
                Yss.CommonLib.ShowMessage("已经存在托管机构，业务类型，管理人相同的数据！");
               //FAST.Core.Util.YssMessageBox.ShowCommonInfo("已经存在托管机构，业务类型，管理人相同的数据！"); 
            }else
            {
                base.btnSave_Click(sender, e);
            }
        }

        /// <summary>
        /// 将界面控件录入信息封装成pojo对象
        /// </summary>
        /// <returns>封装的pojo对象</returns>
        public override BasePojo faceInfoToBaseObjMVC ()
        {
            try
            {
                DzPara dzPara = new DzPara();
                ////STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
                dzPara.C_SECRETKEY = (this.dataService as IDzParaService).encryptData(this.txtSecretKey.Text);
                dzPara.C_DV_CHARSET = this.cboCharset.Value;
                dzPara.C_DV_SECRETTYPE = this.cboSecretType.Value;
                ////dzPara.C_ASS_CODE = (this.cboAssCode.Value == null ? " " : this.cboAssCode.Value);
                dzPara.C_BUS_TYPE = this.cboBusType.Value;
                dzPara.C_COMM_TYPE = this.cboCommType.Value;
                ////STORY24576 电子对账工行仍然没有支持-新增字段
                dzPara.C_DZ_MODE = this.cboDZMode.Value;
                dzPara.C_DEPT_CODE = this.txtDeptCode.Text;
                dzPara.C_CERT_ID = this.txtCertId.Text;
                dzPara.C_TARGET_APP_LOGO = this.txtTargetLogo.Text;
                dzPara.C_TARGET_USER = this.txtUser.Text;
                dzPara.C_TGH_CODE = this.cboBank.Value;
                dzPara.C_TGH_NAME = this.cboBank.Text;
                dzPara.C_GZB_MODE = this.cboGzbMode.Value;
                ////STORY #35703 估值表自检以及自动生成发送电子对账
                dzPara.C_High_Time = this.txtHighTime.Value.ToString();
                dzPara.C_Interval = this.txtInterval.Text;
			    ////STORY42660【中国银行】深证通伺服器要求采用热备模式
                dzPara.C_ErPara_Code = this.cboErPara.Value;
                ////STORY #49489 光大证券-产品关联电子对账设置优化
                dzPara.C_Has_Branch = this.cboBranchBank.Value;
                //STORY #55269 【富国基金】支持电子对账参数设置支持多管理人
                dzPara.C_MANAGE_CODE = this.cboManagerOrg.Value == null ? "" : this.cboManagerOrg.Value;
                dzPara.C_DV_LICORG = this.cboLicOrg.Value == null ? "" : this.cboLicOrg.Value;
                ////STORY24576 电子对账工行仍然没有支持-新增字段
                //若不为空 填写为数字 且 不超过200
                string kmNameLength = this.txtKMNameLH.Text;
                if (kmNameLength != null && kmNameLength.Length != 0 && kmNameLength != "")
                {
                    int nameLength = int.Parse(kmNameLength);
                    if (nameLength < 0 || nameLength > 200)
                    {
                        throw new ClsBaseException("科目名称长度范围在0至200,或不填");
                    }
                    else if (nameLength == 0)
                    {
                        dzPara.C_KM_NAME_LENGTH = "";
                    }
                    else
                    {
                        dzPara.C_KM_NAME_LENGTH = this.txtKMNameLH.Text;
                    }
                }
                else
                {
                    dzPara.C_KM_NAME_LENGTH = this.txtKMNameLH.Text;
                }
                return dzPara;
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }
        }

        /// <summary>
        /// 用于窗体加载或新增状态时对控件进行参数初始化
        /// </summary>
        public override void yssInitCtlAttr()
        {
            base.yssInitCtlAttr();

            if (ClsEnums.StatusSetting.YssAdd == status)
            {
                //// 业务类型默认为电子对账
                this.cboBusType.Value = "BUSI_DZ";
                //// 连接类型默认为 深证通
                this.cboCommType.Value = "SZT";
            }
        }
        
        /// <summary>
        /// 数据加载前事件
        /// BUG230634电子对账界面卡顿严重
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboBank_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            if(this.cboBank.Items.Count == 0)
            {
                List<ErOrg> orgList = orgService.getTrusteeOrgs();
                if (orgList != null && orgList.Count > 0)
                {
                    foreach (ErOrg org in orgList)
                    {
                        Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(org);
                        e.Collection.Add(entity);
                    }
                }
            }
            ////Dictionary<string, string> paraDict = new Dictionary<string, string>();
            ////paraDict.Add("dataClass", "Org");
            ////paraDict.Add("ARRAY_C_QUALIFICATION", "TRUSTEE,TRUSTEE_SEC,TRUSTEE_MA");//// 加载托管行（商业银行）
            ////////List<BasePojo> orgList = orgService.queryByCondition(paraDict).DataList;
            ////QueryRes res = orgService.queryByCondition(paraDict);
            ////List<BasePojo> orgList = new List<BasePojo>();
            ////orgList.AddRange(res.DataList);

            ////if (orgList != null && orgList.Count > 0)
            ////{
            ////    foreach (Org org in orgList)
            ////    {
            ////        if (org.AuditState == 1)//BUG #195294 电子对账功能优化测试bug  5.电子对账参数设置新增时机构名称数量有误
            ////        {
            ////            Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(org);
            ////            e.Collection.Add(entity);
            ////        }
            ////    }
            ////}
            ////指定控件不要自动去加载数据
            e.IsCancel = true;
        }

        /////// <summary>
        /////// 手动加载组合,值为资产代码
        /////// </summary>
        /////// <param name="sender">sender</param>
        /////// <param name="e">e</param>
        ////private void cboAssCode_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        ////{
        ////    e.IsCancel = true;
        ////    IPortDataService svc = DataServiceFactory.createService<IPortDataService>();

        ////    List<BasePojo> pojoList = svc.getDataList();
        ////    ////this.cboAssCode.DisplayValue = "C_ASS_CODE";
        ////    this.cboAssCode.DisplayValue = "C_PORT_CODE";
        ////    this.cboAssCode.NodeID = "C_PORT_CODE";
        ////    this.cboAssCode.Parameter = "C_PORT_CODE~C_PORT_NAME_ST";
        ////    ////this.cboAssCode.Parameter = "C_ASS_CODE~C_PORT_NAME_ST";
        ////    ////过滤 资产代码与父级的资产代码一样时 删除。
        ////    Dictionary<string, BasePojo> dict = new Dictionary<string, BasePojo>();

        ////    if (pojoList != null && pojoList.Count > 0)
        ////    {
        ////        foreach (BasePojo pojo in pojoList)
        ////        {
        ////            Port port = pojo as Port;
        ////            if (dict.ContainsKey(port.C_ASS_CODE))
        ////            {
        ////                Port dictPort = dict[port.C_ASS_CODE] as Port;
        ////                if (dictPort.C_PORT_CODE_P.Equals(port.C_PORT_CODE))
        ////                {
        ////                    dict[port.C_ASS_CODE] = pojo;
        ////                }
        ////            }
        ////            else
        ////            {
        ////                dict.Add(port.C_ASS_CODE, pojo);
        ////            }
        ////        }

        ////        e.Pojos = dict.Values.ToList<BasePojo>();
        ////    }
        ////}

        /// <summary>
        /// 如果关联机构界面，则自动给机构赋值
        /// </summary>
        public override void initControlStat()
        {
            base.initControlStat();
            if (this.FormBaseListView != null)
            {
                Frm_DZ_PARA_L frmList = this.FormBaseListView as Frm_DZ_PARA_L;
                Org org = frmList.MainDataPojo as Org;
                if (org != null)
                {
                    this.cboBank.Value = org.C_ORG_CODE;
                    this.cboBank.YssReadOnly = true;
                }
            }
        }
		/// <summary>
        /// 将界面控件录入信息封装成pojo对象
        /// add by xhb dzPara改变为AuditableParamPojo，封装时改变方法
        /// STORY #27716 
        /// </summary>
        /// <returns>封装的pojo对象</returns>
        public override AuditableParamPojo faceInfoToObjMVC()
        {
            try
            {
                DzPara dzPara = new DzPara();
                ////STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
                dzPara.C_SECRETKEY = (this.dataService as IDzParaService).encryptData(this.txtSecretKey.Text);
                dzPara.C_DV_CHARSET = this.cboCharset.Value;
                dzPara.C_DV_SECRETTYPE = this.cboSecretType.Value;
                dzPara.C_BUS_TYPE = this.cboBusType.Value;
                dzPara.C_COMM_TYPE = this.cboCommType.Value;
                dzPara.C_DEPT_CODE = this.txtDeptCode.Text;
                dzPara.C_CERT_ID = this.txtCertId.Text;
                dzPara.C_TARGET_APP_LOGO = this.txtTargetLogo.Text;
                dzPara.C_TARGET_USER = this.txtUser.Text;
                dzPara.C_TGH_CODE = this.cboBank.Value;
                dzPara.C_TGH_NAME = this.cboBank.Text;
                dzPara.C_GZB_MODE = this.cboGzbMode.Value;
                ////STORY24576 电子对账工行仍然没有支持-新增字段
                dzPara.C_DZ_MODE = this.cboDZMode.Value;
                ////STORY #35703 估值表自检以及自动生成发送电子对账
                dzPara.C_High_Time = this.txtHighTime.Value.ToString();
                dzPara.C_Interval = this.txtInterval.Text;
				////STORY42660【中国银行】深证通伺服器要求采用热备模式
                dzPara.C_ErPara_Code = this.cboErPara.Value;
                ////STORY #49489 光大证券-产品关联电子对账设置优化
                dzPara.C_Has_Branch = this.cboBranchBank.Value;
                //STORY #55269 【富国基金】支持电子对账参数设置支持多管理人
                dzPara.C_MANAGE_CODE = this.cboManagerOrg.Value == null ? "" : this.cboManagerOrg.Value;
                dzPara.C_DV_LICORG = this.cboLicOrg.Value == null ? "" : this.cboLicOrg.Value;
                ////STORY24576 电子对账工行仍然没有支持-新增字段
                //若不为空 填写为数字 且 不超过200
                string kmNameLength = this.txtKMNameLH.Text;
                if (kmNameLength != null && kmNameLength.Length != 0 && kmNameLength != "")
                {
                    int nameLength = int.Parse(kmNameLength);
                    if (nameLength < 0 || nameLength > 200)
                    {
                        throw new ClsBaseException("科目名称长度范围在0至200,或不填");
                    }
                    else if (nameLength == 0)
                    {
                        dzPara.C_KM_NAME_LENGTH = "";
                    }
                    else
                    {
                        dzPara.C_KM_NAME_LENGTH = this.txtKMNameLH.Text;
                    }
                }
                else
                {
                    dzPara.C_KM_NAME_LENGTH = this.txtKMNameLH.Text;
                }
                return dzPara;
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }
        }
        /// <summary>
        /// 管理人数据加载前事件
        /// STORY #55269 【富国基金】支持电子对账参数设置支持多管理人
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboManagerOrg_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            ////Dictionary<string, string> paraDict = new Dictionary<string, string>();
            ////paraDict.Add("dataClass", "Org");
            ////////paraDict.Add("ARRAY_C_DV_ORG_TYPE", "ORG_SYYH,");
            ////paraDict.Add("ARRAY_C_QUALIFICATION", "MANAGER");//// 加载”主体资质”包含“管理人”的机构信息
            ////List<BasePojo> orgList = orgService.queryByCondition(paraDict).DataList;

            ////if (orgList != null && orgList.Count > 0)
            ////{
            ////    foreach (Org org in orgList)
            ////    {
            ////        Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(org);
            ////        e.Collection.Add(entity);
            ////    }
            ////}

            if (this.cboManagerOrg.Items.Count == 0)
            {
                List<ErOrg> orgList = orgService.getManagerOrgs();
                if (orgList != null && orgList.Count > 0)
                {
                    foreach (ErOrg org in orgList)
                    {
                        Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(org);
                        e.Collection.Add(entity);
                    }
                }
            }

            ////指定控件不要自动去加载数据
            e.IsCancel = true;
        }

        /// <summary>
        /// 业务类型为电子对账时，许可信息必输
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboBusType_SelectedValueChanged(object sender, EventArgs e)
        {
            if ("BUSI_DZ".Equals(this.cboBusType.Value))
            {
                this.cboLicOrg.YssIsMust = true;
            }
            else
            {
                this.cboLicOrg.YssIsMust = false;
            }
        }
    }
}
