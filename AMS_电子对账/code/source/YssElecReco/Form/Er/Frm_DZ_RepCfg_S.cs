using System;
using System.Collections.Generic;
using YssElecReco.pojo.Er;
using FAST.Core.CRUD.Form;
using FAST.Core.Communication.Service;
using FAST.Core.Context.Events;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;
using FAST.Common.Service.Services;
using FAST.Core.CRUD.Interface;
using YssSztTool.Pojo.Para;
using YssElecReco.Service.Er;
using YssSztTool.Service.Para;
using System.Collections;
using FAST.Core.Context;
using FAST.Common.Service.Pojo;
using Yss.KMessage;
using System.Windows.Forms;
using Yss.KRichEx.AutoFilter.Model;

namespace YssElecReco.Form.Er
{
    /// <summary>
    /// STORY #36615 【紧急】南方基金-系统需要支持存在多个财务报表的电子对账
    /// 对账报表配置 zhanghualin 2016-12-09
    /// </summary>
    public partial class Frm_DZ_RepCfg_S : FrmBaseSet
    {
        //STORY #65389电子对账前台与估值核算解耦
        /////// <summary>
        /////// 财务报表Service
        /////// </summary>
        ////private IReportTemplateService cwbbService = null;

        private IDzRepCfgService dzRepCfgService = null;

        /// <summary>
        /// 机构信息Service
        /// </summary>
        private IErOrgService orgService = null;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_DZ_RepCfg_S()
        {
            InitializeComponent();
            this.bUseMVCService = true;
            ////STORY #65389电子对账前台与估值核算解耦
            ////cwbbService = ServiceFactory.createService<IReportTemplateService>();
            dzRepCfgService = ServiceFactory.createService<IDzRepCfgService>();
            orgService = ServiceFactory.createService<IErOrgService>();
        }

        /// <summary>
        /// 报表类型
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboRptType_setValue(object sender, FAST.Core.Context.Events.YssBeforeOperEventArgs e)
        {
            e.IsCancel = true;
            if (this.cboRptType.Items.Count == 0)
            {
                this.cboRptType.DisplayName = "C_DV_NAME";
                this.cboRptType.DisplayValue = "C_DV_CODE";
                this.cboRptType.Parameter = "C_DV_NAME";
                this.cboRptType.SortColumn = "C_DV_CODE";
                this.cboRptType.Items.Add(new KTableEntity("月报", "REPORT_MONTH"));
                this.cboRptType.Items.Add(new KTableEntity("季报", "REPORT_SEASON"));
                this.cboRptType.Items.Add(new KTableEntity("半年报", "REPORT_HALF_YEAR"));
                this.cboRptType.Items.Add(new KTableEntity("年报", "REPORT_YEAR"));
            }

        }

        /// <summary>
        /// 组合值改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void grpPort_SelectedValueChanged(object sender, EventArgs e)
        {
            if (!string.IsNullOrEmpty(this.grpPort.Value))
            {
                this.cboAssType.YssReadOnly = true;
            }
            else
            {
                //// 如果是浏览状态，置灰;否则不置灰
                if (status != ClsEnums.StatusSetting.YssBrow)
                {
                    this.cboAssType.YssReadOnly = false;
                }
                else
                {
                    this.cboAssType.YssReadOnly = true;
                }
            }
        }

        /// <summary>
        /// 资产类型改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboAssType_SelectedValueChanged(object sender, EventArgs e) 
        {
            if (!string.IsNullOrEmpty(this.cboAssType.Value))
            {
                this.grpPort.YssReadOnly = true;
            }
            else
            {
                //// 如果是浏览状态，置灰;否则不置灰
                if (status != ClsEnums.StatusSetting.YssBrow)
                {
                    this.grpPort.YssReadOnly = false;
                }
                else
                {
                    this.grpPort.YssReadOnly = true;
                }
            }
        }

        /// <summary>
        /// 初始化窗体控件状态
        /// </summary>
        public override void initControlStat()
        {
            base.initControlStat();
            if (status == ClsEnums.StatusSetting.YssEdit)
            {
                this.cboRptType.ShowCheckBox = false;
            }
            else
            {
                this.cboRptType.ShowCheckBox = true;
            }

            ////BUG #382255 【300.7版本】电子对账报表配置界面set窗口复制维护到组合的数据时，资产类型没有置灰
            if (!string.IsNullOrEmpty(this.grpPort.Value) && string.IsNullOrEmpty(this.cboAssType.Value))
            {
                this.cboAssType.YssReadOnly = true;
            }
            else if (string.IsNullOrEmpty(this.grpPort.Value) && !string.IsNullOrEmpty(this.cboAssType.Value))
            {
                this.grpPort.YssReadOnly = true;
            }
        }

        /// <summary>
        /// 机构名称数据加载前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void selOrgName_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            ////指定控件不要自动去加载数据
            e.IsCancel = true;
            ////Dictionary<string, string> paraDict = new Dictionary<string, string>();
            ////paraDict.Add("dataClass", "Org");
            ////////paraDict.Add("ARRAY_C_DV_ORG_TYPE", "ORG_SYYH,");
            ////paraDict.Add("ARRAY_C_QUALIFICATION", "TRUSTEE,TRUSTEE_SEC,TRUSTEE_MA");//// 加载托管行（商业银行）,参照对账参数界面
            ////List<BasePojo> orgList = orgService.queryByCondition(paraDict).DataList;
            ////if (orgList != null && orgList.Count > 0)
            ////{
            ////    foreach (Org org in orgList)
            ////    {
            ////        if (org.AuditState == 1)
            ////        {
            ////            Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(org);
            ////            e.Collection.Add(entity);
            ////        }
            ////    }
            ////}
            if (this.selOrgName.Items.Count == 0)
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
        }

        /// <summary>
        /// 财务报表下拉框加载数据
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void selCwbb_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            try
            {
                e.IsCancel = true;
                if (this.selCwbb.Items.Count > 0)
                {
                    return;
                }

                ////STORY #65389电子对账前台与估值核算解耦
                ////List<BasePojo> list = cwbbService.getReportTemplateTreeView("").DataList;
                List<BasePojo> list = this.dzRepCfgService.getReportTemplateTreeView("").DataList;
                if (list != null && list.Count > 0)
                {
                    foreach (BasePojo pojo in list)
                    {
                        Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(pojo);
                        e.Collection.Add(entity);
                    }
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message, ex);
            }
        }

        /// <summary>
        /// 展示对象到窗体
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            try
            {
                DzRepCfg xDzRepCfg = (DzRepCfg)yssGetBaseSelTypeItemMVC();
                if (xDzRepCfg != null)
                {
                    ////基本信息
                    this.selOrgName.Value = xDzRepCfg.C_ORG_NAME;
                    this.selDzbb.Value = xDzRepCfg.C_DZ_CODE;
                    this.selCwbb.Value = xDzRepCfg.C_REPORT_CODE;
                    this.grpPort.Value = xDzRepCfg.C_PORT_CODE;
                    ////STORY60117【鹏华基金】电子对账报表配置增加资产类型控制
                    this.cboAssType.Value = xDzRepCfg.C_DAT_CODE;
                    ////STORY #85122 【鹏华基金】社保和养老基金的利润表（年报）电子对账发送 
                    this.cboRptType.Value = xDzRepCfg.C_RPT_TYPE;
                }
            }
            catch (Exception ex)
            {
                throw TransferErrorMessageUtil.getTransferException(ex);
            }
        }

        /// <summary>
        /// 封装窗体数据
        /// </summary>
        /// <returns>Pojo</returns>
        public override AuditableParamPojo faceInfoToObjMVC()
        {
            DzRepCfg xDzRepCfg = new DzRepCfg();
            try
            {
                ////基本信息
                xDzRepCfg.C_ORG_CODE = this.selOrgName.Value;
                xDzRepCfg.C_ORG_NAME = this.selOrgName.Value;
                xDzRepCfg.C_DZ_CODE = this.selDzbb.Value;
                xDzRepCfg.C_DZ_NAME = this.selDzbb.Value;
                xDzRepCfg.C_REPORT_CODE = this.selCwbb.Value;
                xDzRepCfg.C_REPORT_NAME = this.selCwbb.Text;
                xDzRepCfg.C_PORT_CODE = this.grpPort.Value == null ? " " : this.grpPort.Value;
                xDzRepCfg.C_PORT_NAME = this.grpPort.Value == null ? " " : this.grpPort.Value;
                ////STORY60117【鹏华基金】电子对账报表配置增加资产类型控制
                xDzRepCfg.C_DAT_CODE = this.cboAssType.Value == null ? "" : this.cboAssType.Value;
                ////STORY #85122 【鹏华基金】社保和养老基金的利润表（年报）电子对账发送 
                xDzRepCfg.C_RPT_TYPE = this.cboRptType.Value == null ? "" : this.cboRptType.Value;
            }
            catch (Exception ex)
            {
                throw TransferErrorMessageUtil.getTransferException(ex);
            }

            return xDzRepCfg;
        }

        /// <summary>
        /// 保存时根据组合代码拆成多个pojo
        /// </summary>
        /// <param name="status">status</param>
        /// <returns>string</returns>
        public override string yssFormOperationMVC(ClsEnums.StatusSetting status)
        {
            string result = "";
            ArrayList pojoList = null;
            pojoList = yssGetDataObjMVC(pojoList);

            if (pojoList == null)
            {
                pojoList = new ArrayList();
            }

            if (pojoList.Count > 0)
            {
                BasePojo pojo = (BasePojo)pojoList[0];
                DzRepCfg cfg = (DzRepCfg)pojo;
                ////STORY #27662 华泰证券-流程审批模式优化 组合代码可选
                if (cfg.C_PORT_CODE != null && cfg.C_PORT_CODE.Contains("|"))
                {
                    Dictionary<string, string> isExistPorts = new Dictionary<string, string>();
                    string isExistPortStrs = "";
                    Dictionary<string, string> paraMap = new Dictionary<string, string>();
                    paraMap.Add("C_ORG_CODE", cfg.C_ORG_CODE);
                    paraMap.Add("C_DZ_CODE", cfg.C_DZ_CODE);
                    ////paraMap.Add("C_REPORT_CODE", cfg.C_REPORT_CODE);
                    paraMap.Add("ARRAY_PORT_CODE", cfg.C_PORT_CODE.Replace("|", ","));
                    ////paraMap.Add("C_DAT_CODE", cfg.C_DAT_CODE);
                    paraMap.Add("dataClass", "DzRepCfg");
                    QueryRes res = dzRepCfgService.queryByCondition(paraMap);
                    if (res != null && res.DataList != null && res.DataList.Count > 0)
                    {
                        DzRepCfg cfgPojoTmp = null;
                        foreach (BasePojo basePojo in res.DataList)
                        {
                            cfgPojoTmp = basePojo as DzRepCfg;
                            if (cfg.C_PORT_CODE.Contains(cfgPojoTmp.C_PORT_CODE))
                            {
                                if (!isExistPorts.ContainsKey(cfgPojoTmp.C_PORT_CODE))
                                {
                                    isExistPorts.Add(cfgPojoTmp.C_PORT_CODE,"");
                                    isExistPortStrs = isExistPortStrs + cfgPojoTmp.C_PORT_CODE + ",";
                                }
                            }
                        }
                    }

                    if (null != isExistPorts && isExistPorts.Count > 0)
                    {
                        string pcMsgDetail = "投资组合代码：[" + isExistPortStrs + "],报表名：[" + this.selDzbb.Text + "]已存在，请正确设置！";
                        new MessageDialog().Show("本次选择的投资组合的报表已经存在，请确认！", "警告", MessageBoxButtons.OK, MessageBoxIcon.Warning, pcMsgDetail);
                        return pcMsgDetail;
                    }

                    ////拆分组合代码，生成多个pojo
                    pojoList.Clear();
                    string[] portCodeAry = cfg.C_PORT_CODE.Split('|');
                    DzRepCfg tmpSet = new DzRepCfg();
                    foreach (string portCode in portCodeAry)
                    {
                        tmpSet = (DzRepCfg)cfg.Clone();
                        tmpSet.C_PORT_CODE = portCode;
                        if (!isExistPorts.ContainsKey(portCode))
                        {
                            pojoList.Add(tmpSet);
                        }
                    }
                }

                ////STORY #85122 【鹏华基金】社保和养老基金的利润表（年报）电子对账发送 
                if (cfg.C_RPT_TYPE != null && cfg.C_RPT_TYPE.Contains("|"))
                {
                    Dictionary<string, string> isExistRptType = new Dictionary<string, string>();
                    string isExistPortStrs = "";
                    Dictionary<string, string> paraMap = new Dictionary<string, string>();
                    paraMap.Add("C_ORG_CODE", cfg.C_ORG_CODE);
                    paraMap.Add("C_DZ_CODE", cfg.C_DZ_CODE);
                    paraMap.Add("ARRAY_RPT_TYPE", cfg.C_RPT_TYPE.Replace("|", ","));
                    paraMap.Add("dataClass", "DzRepCfg");
                    QueryRes res = dzRepCfgService.queryByCondition(paraMap);
                    if (res != null && res.DataList != null && res.DataList.Count > 0)
                    {
                        DzRepCfg cfgPojoTmp = null;
                        foreach (BasePojo basePojo in res.DataList)
                        {
                            cfgPojoTmp = basePojo as DzRepCfg;
                            if (cfg.C_RPT_TYPE.Contains(cfgPojoTmp.C_RPT_TYPE))
                            {
                                if (!isExistRptType.ContainsKey(cfgPojoTmp.C_RPT_TYPE))
                                {
                                    isExistRptType.Add(cfgPojoTmp.C_RPT_TYPE, "");
                                    isExistPortStrs = isExistPortStrs + cfgPojoTmp.C_RPT_TYPE + ",";
                                }
                            }
                        }
                    }

                    if (null != isExistRptType && isExistRptType.Count > 0)
                    {
                        string pcMsgDetail = "报表类型代码：[" + isExistPortStrs + "],报表名：[" + this.selDzbb.Text + "]已存在，请正确设置！";
                        new MessageDialog().Show("本次选择报表类型的报表已经存在，请确认！", "警告", MessageBoxButtons.OK, MessageBoxIcon.Warning, pcMsgDetail);
                        return pcMsgDetail;
                    }

                    ////拆分报表类型，生成多个pojo
                    ArrayList pojoListNew = new ArrayList();
                    string[] rptTypeAry = cfg.C_RPT_TYPE.Split('|');
                    DzRepCfg tmpSet = new DzRepCfg();
                    foreach (string rptType in rptTypeAry)
                    {
                        foreach (BasePojo basePojo in pojoList)
                        {
                            tmpSet = (DzRepCfg)basePojo.Clone();
                            tmpSet.C_RPT_TYPE = rptType;
                            if (!isExistRptType.ContainsKey(rptType))
                            {
                                pojoListNew.Add(tmpSet);
                            }
                        }
                    }

                    pojoList.Clear();
                    pojoList.AddRange(pojoListNew);
                }

                result = yssDoSetFormOperMVC(pojoList, status);
            }

            return result;
        }

        /// <summary>
        /// 锁定特殊控件的状态
        /// </summary>
        protected override void OnLockSpecialControlState()
        {
            base.OnLockSpecialControlState();
            ////List界面被关联内嵌至其他界面时，机构不能被更改
            if (this.frmBaseViewList != null && this.frmBaseViewList is IFormDetailData)
            {
                IFormDetailData frmDetailData = this.frmBaseViewList as IFormDetailData;
                if (frmDetailData.MainDataPojo != null)
                {
                    this.selOrgName.YssReadOnly = true;
                    if (this.status == FAST.Core.Context.ClsEnums.StatusSetting.YssAdd)
                    {
                        this.selOrgName.Value = ((DzPara)frmDetailData.MainDataPojo).C_TGH_CODE;
                    }
                }
            }
        }
    }
}
