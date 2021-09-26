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
using FAST.Platform.DataIntegration.Exp.Pojos;
using FAST.Core.BaseControl;
using FAST.Platform.DataIntegration.Exp.Service;

namespace YssElecReco.Form.Er
{
    /// <summary>
    /// STORY #36615 【紧急】南方基金-系统需要支持存在多个财务报表的电子对账
    /// 对账报表配置 zhanghualin 2016-12-09
    /// </summary>
    public partial class Frm_DZ_TransRepCfg_S : FrmBaseSet
    {

        private IDzTransRepCfgService DzTransRepCfgService = null;

        /// <summary>
        /// 机构信息Service
        /// </summary>
        private IErOrgService orgService = null;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_DZ_TransRepCfg_S()
        {
            InitializeComponent();
            this.bUseMVCService = true;
            DzTransRepCfgService = ServiceFactory.createService<IDzTransRepCfgService>();
            orgService = ServiceFactory.createService<IErOrgService>();
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
        /// 模板配置下拉框加载数据
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboTpl_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            e.Cancel = true;
            IFormDetailData frmDetailData = this.frmBaseViewList as IFormDetailData;
            List<BasePojo> bp = new List<BasePojo>();
            if (frmDetailData.MainDataPojo != null && frmDetailData.MainDataPojo is ClsBasicDefine)
            {
                bp = getNameBycode((frmDetailData.MainDataPojo as ClsBasicDefine).ReportCode);
            }
            else
            {
                DzTransRepCfg tplateRela = this.yssGetBaseSelTypeItemMVC() as DzTransRepCfg;
                if (tplateRela != null && status == ClsEnums.StatusSetting.YssBrow)
                {
                    bp = getNameBycode(tplateRela.C_TPL_CODE);
                }
                else
                {
                    this.getTplateByCondition();
                }
            }

            assetData(bp, "ClsBasicDefine", this.cboTpl, "cboTpl");
        }

        /// <summary>
        /// 根据文档code来获取文档name
        /// </summary>
        /// <param name="code">code</param>
        /// <returns>List</returns>
        private List<BasePojo> getNameBycode(string code)
        {
            List<BasePojo> dataList = new List<BasePojo>();
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            try
            {
                if (paraDict.ContainsKey("dataClass"))
                {
                    paraDict.Remove("dataClass");
                }

                if (!paraDict.ContainsKey("C_REP_CODE"))
                {
                    paraDict.Add("C_REP_CODE", code);
                }

                if (true)
                {
                    // 获取文档模板信息
                    paraDict.Add("dataClass", "ClsBasicDefine");

                    // add by yh 2013-5-21 如果先调用了加载公用账户，会多出c_dv_oppo_rela的key
                    paraDict.Remove("C_DV_OPPO_RELA");

                    //Type serviceType = ReflectBase.YssGetType("YssDataIntegration.exe", "YssDataIntegration.Exp.Service.IBasicDefineService");
                    //IBasicDefineService fundService = ServiceFactory.createService(serviceType) as IBasicDefineService;
                    IBasicDefineService fundService = ServiceFactory.createService<IBasicDefineService>();
                    //// BUG #170905 前台代码中QueryRes对象相关调用问题调整 
                    //// 先使用变量获取QueryRes，然后再获取DataList
                    QueryRes res = fundService.queryByCondition(paraDict);
                    if (res.DataList != null && res.DataList.Count != 0)
                    {
                        dataList.AddRange(res.DataList);
                    }

                }

            }
            catch (Exception ex)
            {
                ClsBaseException.DiscardException(ex);
            }

            return dataList;
        }

        /// <summary>
        /// 将账户信息封装到指定下拉框中
        /// </summary>
        /// <param name="dataList">dataList</param>
        /// <param name="accType">accType</param>
        /// <param name="combox">combox</param>
        ///  <param name="comBoxType">comBoxType</param>
        private void assetData(List<BasePojo> dataList, string accType, YssSelCombox combox, string comBoxType)
        {
            try
            {
                List<Yss.KRichEx.AutoFilter.Model.KTableEntity> lists = new List<Yss.KRichEx.AutoFilter.Model.KTableEntity>();
                Yss.KRichEx.AutoFilter.Model.KTableEntity entity = null;

                if (null != dataList && dataList.Count > 0)
                {
                    for (int i = 0; i < dataList.Count; i++)
                    {
                        BasePojo pojo = dataList[i];
                        entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity((ClsBasicDefine)pojo);
                        lists.Add(entity);

                    }
                }

                //循环list往控件里面塞数据
                for (int i = 0; i < lists.Count; i++)
                {
                    combox.Items.Add((Yss.KRichEx.AutoFilter.Model.KTableEntity)lists[i]); // 循环list把对象放到控件中
                }
            }
            catch (Exception ex)
            {
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 加载模板代码
        /// BUG #124340 支付指令模板匹配新增的数据匹配不到目标名称
        /// </summary>
        private void getTplateByCondition()
        {
            try
            {
                List<BasePojo> basicDefineList = new List<BasePojo>();
                Yss.KRichEx.AutoFilter.Model.KTableEntity entity = null;
                this.cboTpl.Items.Clear(); // 清空控件里面的所有的数据
                Dictionary<string, string> paraDict = new Dictionary<string, string>();
                paraDict.Add("C_REP_STATUS", "TEMP_USABLE");
                if (this.selTransCode.Value != null && this.selTransCode.Value.Trim().Length != 0)
                {
                    paraDict.Add("ARRAY_FILE_TYPE", this.selTransCode.Value);
                }

                paraDict.Add("dataClass", "ClsBasicDefine");

                IBasicDefineService basicDefineService = null;

                if (null == basicDefineService)
                {
                    basicDefineService = ServiceFactory.createService<IBasicDefineService>();
                }

                QueryRes res = basicDefineService.queryByCondition(paraDict);
                if (res.DataList != null && res.DataList.Count != 0)
                {
                    basicDefineList = res.DataList;
                }


                if (null != basicDefineList && basicDefineList.Count > 0)
                {
                    foreach (BasePojo basicDefine in basicDefineList)
                    {
                        entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(basicDefine);
                        this.cboTpl.Items.Add(entity);
                    }
                }
            }
            catch (Exception ex)
            {
                ClsBaseException.DiscardException(ex);
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
                DzTransRepCfg xDzTransRepCfg = (DzTransRepCfg)yssGetBaseSelTypeItemMVC();
                if (xDzTransRepCfg != null)
                {
                    ////基本信息
                    this.selOrgName.Value = xDzTransRepCfg.C_ORG_CODE;
                    this.selTransCode.Value = xDzTransRepCfg.C_TRANS_CODE;
                    this.cboTpl.Value = xDzTransRepCfg.C_TPL_CODE;
                    this.grpPort.Value = xDzTransRepCfg.C_PORT_CODE;
                    ////STORY60117【鹏华基金】电子对账报表配置增加资产类型控制
                    this.cboAssType.Value = xDzTransRepCfg.C_DAT_CODE;
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
            DzTransRepCfg xDzTransRepCfg = new DzTransRepCfg();
            try
            {
                ////基本信息
                xDzTransRepCfg.C_ORG_CODE = this.selOrgName.Value;
                xDzTransRepCfg.C_ORG_NAME = this.selOrgName.Value;
                xDzTransRepCfg.C_TRANS_CODE = this.selTransCode.Value;
                xDzTransRepCfg.C_TRANS_NAME = this.selTransCode.Value;
                xDzTransRepCfg.C_TPL_CODE = this.cboTpl.Value;
                xDzTransRepCfg.C_TPL_NAME = this.cboTpl.Text;
                xDzTransRepCfg.C_PORT_CODE = this.grpPort.Value == null ? " " : this.grpPort.Value;
                xDzTransRepCfg.C_PORT_NAME = this.grpPort.Value == null ? " " : this.grpPort.Value;
                ////STORY60117【鹏华基金】电子对账报表配置增加资产类型控制
                xDzTransRepCfg.C_DAT_CODE = this.cboAssType.Value == null ? "" : this.cboAssType.Value;
            }
            catch (Exception ex)
            {
                throw TransferErrorMessageUtil.getTransferException(ex);
            }

            return xDzTransRepCfg;
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
                DzTransRepCfg cfg = (DzTransRepCfg)pojo;
                ////STORY #27662 华泰证券-流程审批模式优化 组合代码可选
                if (cfg.C_PORT_CODE != null && cfg.C_PORT_CODE.Contains("|"))
                {
                    Dictionary<string, string> isExistPorts = new Dictionary<string, string>();
                    string isExistPortStrs = "";
                    Dictionary<string, string> paraMap = new Dictionary<string, string>();
                    paraMap.Add("C_ORG_CODE", cfg.C_ORG_CODE);
                    paraMap.Add("C_DZ_CODE", cfg.C_TRANS_CODE);
                    ////paraMap.Add("C_REPORT_CODE", cfg.C_REPORT_CODE);
                    paraMap.Add("ARRAY_PORT_CODE", cfg.C_PORT_CODE.Replace("|", ","));
                    ////paraMap.Add("C_DAT_CODE", cfg.C_DAT_CODE);
                    paraMap.Add("dataClass", "DzTransRepCfg");
                    QueryRes res = DzTransRepCfgService.queryByCondition(paraMap);
                    if (res != null && res.DataList != null && res.DataList.Count > 0)
                    {
                        DzTransRepCfg cfgPojoTmp = null;
                        foreach (BasePojo basePojo in res.DataList)
                        {
                            cfgPojoTmp = basePojo as DzTransRepCfg;
                            if (cfg.C_PORT_CODE.Contains(cfgPojoTmp.C_PORT_CODE))
                            {
                                if (!isExistPorts.ContainsKey(cfgPojoTmp.C_PORT_CODE))
                                {
                                    isExistPorts.Add(cfgPojoTmp.C_PORT_CODE, "");
                                    isExistPortStrs = isExistPortStrs + cfgPojoTmp.C_PORT_CODE + ",";
                                }
                            }
                        }
                    }

                    if (null != isExistPorts && isExistPorts.Count > 0)
                    {
                        string pcMsgDetail = "投资组合代码：[" + isExistPortStrs + "],报表名：[" + this.selTransCode.Text + "]已存在，请正确设置！";
                        new MessageDialog().Show("本次选择的投资组合的报表已经存在，请确认！", "警告", MessageBoxButtons.OK, MessageBoxIcon.Warning, pcMsgDetail);
                        return pcMsgDetail;
                    }

                    ////拆分组合代码，生成多个pojo
                    pojoList.Clear();
                    string[] portCodeAry = cfg.C_PORT_CODE.Split('|');
                    DzTransRepCfg tmpSet = new DzTransRepCfg();
                    foreach (string portCode in portCodeAry)
                    {
                        tmpSet = (DzTransRepCfg)cfg.Clone();
                        tmpSet.C_PORT_CODE = portCode;
                        if (!isExistPorts.ContainsKey(portCode))
                        {
                            pojoList.Add(tmpSet);
                        }
                    }
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
