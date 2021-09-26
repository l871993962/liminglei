using System;
using System.Collections.Generic;
using YssElecReco.Service.Bi;
using YssElecReco.Pojo.Bi;
using Yss.KRichEx.AutoFilter.Model;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Context.Events;
using FAST.Core.Communication.Service;
using FAST.Core.Util;
using FAST.Core.Exceptions;
using FAST.Core.Context;
using FAST.Core.Communication.DataService;
using YssProductInfo.Support.Aa.PortCls.Service;
using YssProductInfo.Support.Aa.PortCls.Pojo;
using System.Collections;
using YssSztTool.Service.Para;
using YssSztTool.Pojo.Para;
using YssElecReco.Service.Er;
using Yss.KMessage;
using System.Windows.Forms;

namespace YssElecReco.Form.Bi
{
    /// <summary>
    /// 电子对账指标关联设置
    /// </summary>
    public partial class Frm_ELEC_RELA_PUB_S : FrmBaseSet
    {
        /// <summary>
        /// 机构信息Service
        /// </summary>
        private IErOrgService orgService = null;

        /// <summary>
        /// 声明电子划款信息服务对象
        /// </summary>
        private IElecRelaService elecRelaService = null;
        //STORY #65389电子对账前台与估值核算解耦
        /////// <summary>
        /////// 财务报表Service
        /////// </summary>
        ////private IReportTemplateService cwbbService = null;

        private IDzRepCfgService dzRepCfgService = null;
        
        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_ELEC_RELA_PUB_S()
        {
            InitializeComponent();
            this.bUseMVCService = true;
            this.cboQuota.Visible = false;
            //STORY #65389电子对账前台与估值核算解耦
            ////cwbbService = ServiceFactory.createService<IReportTemplateService>();
            dzRepCfgService = ServiceFactory.createService<IDzRepCfgService>();
            orgService = ServiceFactory.createService<IErOrgService>();
        }

        /// <summary>
        /// 窗体加载
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ELEC_RELA_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.elecRelaService = ServiceFactory.createService(serviceType) as IElecRelaService;
            this.dataService = this.elecRelaService;
        }

        /// <summary>
        /// 获取数据
        /// </summary>
        /// <returns>ArrayList</returns>
        public override ArrayList yssGetObjListMVC()
        {
            ArrayList pojoList = new ArrayList();
            if (this.selCwbb.Value == null || "".Equals(this.selCwbb.Value.Trim()))
            {
                pojoList.Add(transToObj());
                return pojoList;
            }

            if (this.selCwbb.Value.Contains("|"))
            {
                foreach (string s in this.selCwbb.Value.Split(new char[1] { '|' }))
                {
                    ElecRela rela = transToObj();
                    rela.C_REPORT_CODE = s;
                    pojoList.Add(rela);
                }
            }
            else
            {
                ElecRela rela = transToObj();
                rela.C_REPORT_CODE = this.selCwbb.Value;
                pojoList.Add(rela);
            }

            return pojoList;
        }

        /// <summary>
        /// 转换pojo
        /// </summary>
        /// <returns>ElecRela</returns>
        public ElecRela transToObj()
        {
            ElecRela elecrela = null;
            try
            {
                elecrela = new ElecRela();

                elecrela.C_ZB_CODE = this.txtZbCode.Text;
                elecrela.C_ZB_NAME = this.txtZbName.Text;
                elecrela.C_DZ_CODE = this.cboDzType.Value;
                ////by sunhe BUG #121361 电子对账指标关联设置不能修改分级组合等控件值为空
                elecrela.C_PORT_CODE_CLS = this.cboClsPort.Value == null ? " " : this.cboClsPort.Value;
                elecrela.C_DV_ZB_CODE = this.cboQuota.Value == null ? " " : this.cboQuota.Value;
                elecrela.C_ORG_CODE = this.cboOrg.Value == null ? " " : this.cboOrg.Value;
                elecrela.C_DV_PORT_CLS_TYPE = this.cboClassType.Value == null ? " " : this.cboClassType.Value;
                elecrela.C_DV_PORT_CLS_LEVEL = this.cboClassLevel.Value == null ? " " : this.cboClassLevel.Value;
                elecrela.C_DV_PORT_CLS = this.cboClassGrade.Value == null ? " " : this.cboClassGrade.Value;
                string rowVaule = this.txtZdh.Text;
                string pattern = @"^\d*$";
                bool b = System.Text.RegularExpressions.Regex.IsMatch(rowVaule, pattern);
                if (b)
                {
                    elecrela.C_ROW = this.txtZdh.Text;
                }
                else { 
                MessageDialog msg = new MessageDialog();
                msg.Show("【选中行】必须为整数数字!", "提示信息", MessageBoxButtons.OK);
                return null;

                }
            
                ////财务报表名称不入库，置空
                elecrela.C_REPORT_NAME = null;
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return elecrela;
        }

        /// <summary>
        /// 展示对象到窗体
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            try
            {
                ElecRela elecrela = (ElecRela)this.yssGetBaseSelTypeItemMVC();

                if (elecrela == null)
                {
                    return;
                }

                this.txtZbCode.Text = elecrela.C_ZB_CODE;
                this.txtZbName.Text = elecrela.C_ZB_NAME;
                this.cboDzType.Value = elecrela.C_DZ_CODE;
                this.txtZdh.Text = elecrela.C_ROW;
                this.cboClassType.Value = elecrela.C_DV_PORT_CLS_TYPE;
                ////STORY47037嘉实基金电子对账管理查看托管人反馈的信息时，分级指标名称需要展示至具体的分级代码 增加分级级别
                this.cboClassGrade.Value = elecrela.C_DV_PORT_CLS;
                this.cboClassLevel.Value = elecrela.C_DV_PORT_CLS_LEVEL;
                this.cboClsPort.Value = elecrela.C_PORT_CODE_CLS;
                ////this.cboQuota.Value = elecrela.C_DV_ZB_CODE;
                this.cboOrg.Value = elecrela.C_ORG_CODE;
                ////STORY53570嘉实基金-电子对账-月报 产品分类配置 增加财务报表
                if ("1011".Equals(elecrela.C_DZ_CODE))
                { ////估值表
                    this.cwbbCell.Text = "   指标项名称：";
                    this.cwbbCell.InnerControl = this.cboQuota;
                    this.cboQuota.Visible = true;
                    this.cboQuota.Value = elecrela.C_DV_ZB_CODE;
                    this.selCwbb.Value = "";
                    this.selCwbb.Visible = false;
                }
                else if ("1013".Equals(elecrela.C_DZ_CODE))
                { ////双估值表
                    this.cwbbCell.Text = "   指标项名称：";
                    this.cwbbCell.InnerControl = this.cboQuota;
                    this.cboQuota.Visible = true;
                    this.cboQuota.Value = elecrela.C_DV_ZB_CODE;
                    this.selCwbb.Value = "";
                    this.selCwbb.Visible = false;
                }
                else if ("1001".Equals(elecrela.C_DZ_CODE) || "1031".Equals(elecrela.C_DZ_CODE))
                { ////余额表 科目表
                    this.Height = 245;
                    this.row7.Visible = false;
                    this.row8.Visible = false;
                    this.row9.Visible = false;
                    this.cboQuota.Visible = false;
                    this.cboQuota.Value = "";
                    this.selCwbb.Visible = false;
                    this.selCwbb.Value = "";
                    this.cwbbCell.Text = "";
                    this.cwbbCell.InnerControl = null;
                }
                else
                {
                    this.cwbbCell.Text = "   财务报表：";
                    this.cwbbCell.InnerControl = this.selCwbb;
                    this.selCwbb.Visible = true;
                    this.selCwbb.Value = elecrela.C_REPORT_CODE;
                    this.cboQuota.Value = "";
                    this.cboQuota.Visible = false;
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// ee
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboClassGrade_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            this.cboClassGrade.MethodInfo.MethodName = "getDataListByTypes";
            string gradeValue = "";
            if (cboClassGrade.QueryCond != null && cboClassGrade.QueryCond.Trim().Length > 0)
            {
                gradeValue = cboClassGrade.QueryCond;
            }

            this.cboClassGrade.MethodInfo.MethodParamValues = new string[] { gradeValue + "," };
        }

        /// <summary>
        /// 分级级别
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboClassLevel_SelectedValueChanged(object sender, EventArgs e)
        {
            cboClsPort.Value = null;
            cboClsPort.Items.Clear();
        }

        /// <summary>
        /// 级联
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboClassLevel_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            this.cboClassLevel.MethodInfo.MethodName = "getDataListByTypes";
            string gradeValue = "";
            if (cboClassLevel.QueryCond != null && cboClassLevel.QueryCond.Trim().Length > 0)
            {
                gradeValue = cboClassLevel.QueryCond;
            }

            this.cboClassLevel.MethodInfo.MethodParamValues = new string[] { gradeValue + "," };
        }

        /// <summary>
        /// 分级产品值改变事件.
        /// 修改了bug 下拉框取值错误.
        /// zhuangyuchen .
        /// 2011-3-20.
        /// </summary>
        /// <param name="sender">发送对象</param>
        /// <param name="e">事件对象</param>
        private void cboClassType_SelectedValueChanged(object sender, EventArgs e)
        {
            cboClsPort.Items.Clear();
            cboClsPort.Value = null;
            if (this.cboClassType.Value != null)
            {
                //// 获取选中的分级类型代码
                string clsType = this.cboClassType.Value;
                this.cboClassLevel.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
                this.cboClassLevel.Value = null;
                //// 当分级类型为费率类型时，分级级别加载申购赎回份额，销售费率份额
                if (clsType.Equals("PCT_FEE"))
                {
                    this.cboClassLevel.QueryCond = "PORT_CLS_FEE_TYPE";
                }
                else if (clsType.Equals("PCT_FEE_BOC"))
                {
                    ////RQFII类型分级
                    this.cboClassLevel.QueryCond = "PORT_CLS_FEE_BOC";
                }
                else if (clsType.Equals("PCT_CREAT"))
                {
                    //// 当分级类型为创新类型是，分级界别加载普通级别和优先级别
                    this.cboClassLevel.QueryCond = "PORT_CLS_CREAT_TYPE";
                }
                else if (clsType.Equals("PCT_CUR"))
                {
                    ////币种类型
                    this.cboClassLevel.QueryCond = "PORT_CLS_CUR";
                }
                else if (clsType.Equals("PCT_CHHK"))
                {
                    ////中港类型
                    this.cboClassLevel.QueryCond = "PORT_CLS_CHHK_TYPE";
                }
            }
        }

        /// <summary>
        /// sender
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboClsPort_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            e.IsCancel = true;
            List<BasePojo> dataList = null;
            cboClsPort.Items.Clear();
            ////BUG #214108::嘉实基金-电子对账-估值表指标配置问题
            ////if (this.cboClassGrade.Value == null || this.cboClassGrade.Value.Trim().Length == 0)
            ////{
            ////    IClsPortDataService iClsPortDataService = DataServiceFactory.createService<IClsPortDataService>();
            ////    dataList = iClsPortDataService.getDataList();
            ////}
            ////else
            ////{
            ////    IPortClsService iPortClsService = ServiceFactory.createService<IPortClsService>();
            ////    Dictionary<string, string> dict = new Dictionary<string, string>();
            ////    dict.Add("dataClass", "PortCls");
            ////    dict.Add("C_DV_PORT_CLS_TYPE", this.cboClassType.Value);
            ////    dict.Add("C_DV_PORT_CLS_LEVEL", this.cboClassGrade.Value);
            ////    dataList = iPortClsService.queryByCondition(dict).DataList;
            ////}
            IPortClsService iPortClsService = ServiceFactory.createService<IPortClsService>();
            Dictionary<string, string> dict = new Dictionary<string, string>();
            dict.Add("dataClass", "PortCls");
            if (this.cboClassLevel.Value != null && this.cboClassLevel.Value.Trim().Length != 0)
            {
                dict.Add("C_DV_PORT_CLS_LEVEL", this.cboClassLevel.Value);
            }

            if (this.cboClassType.Value != null && this.cboClassType.Value.Trim().Length != 0)
            {
                dict.Add("C_DV_PORT_CLS_TYPE", this.cboClassType.Value);
            }

            dataList = iPortClsService.queryByCondition(dict).DataList;
            if (null != dataList && dataList.Count > 0)
            {
                foreach (BasePojo pojo in dataList)
                {
                    KTableEntity entity = new KTableEntity((PortCls)pojo);
                    ////判断用户是否为当前用户 过滤用户不能为自己赋权限
                    if (entity != null)
                    {
                        if (this.cboClassGrade.Value != null && this.cboClassGrade.Value.Trim().Length != 0)
                        {
                            if (this.cboClassGrade.Value.Equals((pojo as PortCls).C_DV_PORT_CLS))
                            {
                                cboClsPort.Items.Add(entity);
                            }
                        }
                        else
                        {
                            cboClsPort.Items.Add(entity);
                        }
                        
                    }
                }
            }
        }

        /// <summary>
        /// 分级级别修改事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboClassGrade_SelectedValueChanged(object sender, EventArgs e)
        {
            //cboClsPort.Text = " ";
            cboClsPort.Value = null;
            cboClsPort.Items.Clear();
        }
        //STORY #65389电子对账前台与估值核算解耦
        /////// <summary>
        /////// 指标项加载
        /////// </summary>
        /////// <param name="sender">sender</param>
        /////// <param name="e">e</param>
        ////private void cboQuota_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        ////{
        ////    e.IsCancel = true;
        ////    IIndexManageService manageService = ServiceFactory.createService<IIndexManageService>();
        ////    Dictionary<string, string> paraDict = new Dictionary<string, string>();
        ////    paraDict.Add("dataClass", "IndexManage");
        ////    List<BasePojo> dataList = manageService.getAllIndex().DataList;
        ////    Yss.KRichEx.AutoFilter.Model.KTableEntity entity = null;
        ////    List<string> keyCodeList = new List<string>();
        ////    if (null != dataList && dataList.Count > 0)
        ////    {
        ////        foreach (IndexManage sysIndex in dataList)
        ////        {
        ////            ////wlx 20171223 BUG185490产品指标配置的c_key_code和估值表的不一致
        ////            if (keyCodeList.Contains(sysIndex.C_KEY_CODE))
        ////            {
        ////                continue;
        ////            }
        ////            keyCodeList.Add(sysIndex.C_KEY_CODE);
        ////            entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(sysIndex);
        ////            e.Collection.Add(entity);
        ////        }
        ////    }
        ////}

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
                //STORY #65389电子对账前台与估值核算解耦
                ////List<BasePojo> list = cwbbService.getReportTemplateTreeView("").DataList;
                List<BasePojo> list = dzRepCfgService.getReportTemplateTreeView("").DataList;
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
        /// 对账类型改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboDzType_SelectedValueChanged(object sender, EventArgs e)
        {
            ////BUG265408对账指标关联set界面删除对账类型栏位数据时报错
            if (this.cboDzType.Value == null)
            {
                return;
            }
            //估值表
            if ("1011".Equals(this.cboDzType.Value.Trim()))
           {
               this.Height = 303;
               this.row7.Visible = true;
               this.row8.Visible = true;
               this.row9.Visible = true;
               this.cwbbLabel.Text = "   指标项名称：";
               this.cwbbCell.InnerControl = this.cboQuota;
               this.selCwbb.Visible = false;
               this.selCwbb.Value = "";
           }
            else if ("1013".Equals(this.cboDzType.Value.Trim()))
            {
                this.Height = 303;
                this.row7.Visible = true;
                this.row8.Visible = true;
                this.row9.Visible = true;
                this.cwbbLabel.Text = "   指标项名称：";
                this.cwbbCell.InnerControl = this.cboQuota;
                this.selCwbb.Visible = false;
                this.selCwbb.Value = "";
            }
            else if ("1001".Equals(this.cboDzType.Value.Trim()) || "1031".Equals(this.cboDzType.Value.Trim()))
            { ////余额表,科目表
               this.Height = 245;
               this.row7.Visible = false;
               this.row8.Visible = false;
               this.row9.Visible = false;
               this.cboQuota.Visible = false;
               this.cboQuota.Value = "";
               this.selCwbb.Visible = false;
               this.selCwbb.Value = "";
               ////this.cwbbLabel.Text = "";
               ////this.cwbbCell.InnerControl = null;
           }
            else 
            {
                this.Height = 303;
                this.row7.Visible = true;
                this.row8.Visible = true;
                this.row9.Visible = true;
                this.cwbbLabel.Text = "   财务报表：";
                this.cwbbCell.InnerControl = this.selCwbb;
                this.cboQuota.Visible = false;
                this.cboQuota.Value = "";
            }
        }

        /// <summary>
        /// 数据加载前事件
        /// BUG266840对账指标关联--set界面中托管行增加条件过滤（对应版本0.3）
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboOrg_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            if (this.cboOrg.Items.Count == 0)
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

            e.IsCancel = true;
        }
    }
}
