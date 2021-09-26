using System;
using System.Collections.Generic;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services;
using FAST.Core.Communication.Service;
using FAST.Core.Context.Events;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;
using FAST.Core.Util;
using FAST.Core.CRUD.Interface;
using FAST.Common.Service.Pojo;
using YssSztTool.Pojo.Para;
using YssElecReco.Service.Er;
using YssSztTool.Service.Para;
using Yss.KRichEx.AutoFilter.Model;

namespace YssElecReco.Form.Er
{
    /// <summary>
    /// STORY #36615 【紧急】南方基金-系统需要支持存在多个财务报表的电子对账
    /// 对账报表配置 zhanghualin 2016-12-09
    /// </summary>
    public partial class Frm_DZ_RepCfg_L : FrmBaseListWithDetails, IFormDetailData
    {
        /// <summary>
        /// 获取或设置一个值，该值为主窗体传过来的数据。当该窗体为某一窗体的附属窗体时有效。
        /// </summary>
        private BasePojo _mainDataPojo;

        /// <summary>
        /// 获取或设置当前窗体是否已经被FrmBaseListWidthDetails窗体关联为子窗体
        /// </summary>
        private bool _hadBeenRelationed = false;

        //STORY #65389电子对账前台与估值核算解耦
        /////// <summary>
        /////// 财务报表Service
        /////// </summary>
        ////private IReportTemplateService cwbbService = null;

        /// <summary>
        /// 机构信息Service
        /// </summary>
        private IErOrgService orgService = null;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_DZ_RepCfg_L()
        {
            this.bUseMVCService = true;
            this.bShowRowCheckBoxColumn = true;
            this.bShowRowIndexColumn = true;
            InitializeComponent();
            this.isLoadFirst = true;
            //STORY #65389电子对账前台与估值核算解耦
            ////cwbbService = ServiceFactory.createService<IReportTemplateService>();
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
        /// 获取或设置当前窗体是否已经被FrmBaseListWidthDetails窗体关联为子窗体。通过此属性可防止嵌套关联。
        /// </summary>
        public bool HadBeenRelationed
        {
            get
            {
                return _hadBeenRelationed;
            }

            set
            {
                _hadBeenRelationed = value;
            }
        }

        /// <summary>
        /// 获取或设置一个值，该值为主窗体传过来的数据。当该窗体为某一窗体的附属窗体时有效。
        /// </summary>
        public BasePojo MainDataPojo
        {
            get
            {
                return this._mainDataPojo;
            }

            set
            {
                if (this._mainDataPojo != value)
                {
                    this._mainDataPojo = value;
                }
            }
        }

        /// <summary>
        /// 装载明细窗体功能代码列表
        /// </summary>
        /// <returns>返回明细窗体功能代码列表</returns>
        protected override List<SysFun> LoadDetailFormFuns()
        {
            List<SysFun> sysFuns = new List<SysFun>();
            SysFun newFun = new SysFun();
            newFun.C_FUN_CODE = "dzRepColCfg";
            newFun.C_FUN_NAME = "";
            sysFuns.Add(newFun);
            return sysFuns;
        }

        /// <summary>
        /// 添加查询条件
        /// </summary>
        /// <param name="paraDict">查询条件目标集合</param>
        /// <returns>查询条件集合</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            if (!paraDict.ContainsKey("dataClass"))
            {
                paraDict.Add("dataClass", "DzRepCfg");
            }

            if (this.selOrgName.Value != null && this.selOrgName.Value.Trim().Length != 0)
            {
                string val = this.selOrgName.Value;
                paraDict.Add("ARRAY_ORG_CODE", val.Replace("|", ","));
            }

            if (this.selDzbb.Value != null && this.selDzbb.Value.Trim().Length != 0)
            {
                string val = this.selDzbb.Value;
                paraDict.Add("ARRAY_DZ_CODE", val.Replace("|", ","));
            }

            if (this.selCwbb.Value != null && this.selCwbb.Value.Trim().Length != 0)
            {
                string val = this.selCwbb.Value;
                paraDict.Add("ARRAY_REPORT_CODE", val.Replace("|", ","));
            }

            if (this.grpPort.Value != null && this.grpPort.Value.Trim().Length != 0)
            {
                string val = this.grpPort.Value;
                paraDict.Add("ARRAY_PORT_CODE", val.Replace("|", ","));
            }

            if (this.cboRptType.Value != null && this.cboRptType.Value.Trim().Length != 0)
            {
                string val = this.cboRptType.Value.Replace("|", ",");
                paraDict.Add("ARRAY_RPT_TYPE", val);
            }

            return paraDict;
        }

        #region 控件加载数据事件

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
                //STORY #65389电子对账前台与估值核算解耦
                ////List<BasePojo> list = cwbbService.getReportTemplateTreeView("").DataList;
                List<BasePojo> list = (this.dataService as IDzRepCfgService).getReportTemplateTreeView("").DataList;
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

        #endregion

        #region IFormDetailData 成员

        /// <summary>
        /// 验证是否需要重新装载数据
        /// </summary>
        /// <param name="mainData">主表传过来的数据</param>
        /// <returns>返回验证结果</returns>
        public bool AllowReloadDetailData(BasePojo mainData)
        {
            bool retValue = false;
            if (mainData != null && mainData != this.MainDataPojo)
            {
                retValue = true;
            }

            return retValue;
        }

        /// <summary>
        /// 明细窗体初始化
        /// </summary>
        /// <param name="parent">FrmBaseListWithDetails父容器</param>
        public void InitializeDetailForm(FrmBaseListWithDetails parent)
        {
            this.sDllName = _formFun.YssAssocia.SetDllName;
            this.sSetClassName = _formFun.YssAssocia.SetFormName;
            this.sPojoClassName = _formFun.YssAssocia.PojoClsName;
            this.sPojoDllName = (_formFun.YssAssocia.PojoDllName != null && _formFun.YssAssocia.PojoDllName.Length > 0) ? _formFun.YssAssocia.PojoDllName : ClsFunction.getDllName(_formFun.YssAssocia.PojoClsName);
            if (_formFun != null)
            {
                this.Text = _formFun.C_FUN_NAME;
            }

            this.ShowFilterPanel = false;
        }

        /// <summary>
        /// 装载数据
        /// </summary>
        /// <param name="mainData">主表传过来的Pojo</param>
        public void LoadDetailData(BasePojo mainData)
        {
            if (page == null)
            {
                page = new FAST.Common.Service.Pojo.ClsPageInation();
            }

            page.CurrPage = 1;
            page.PageCount = 0;

            bool validate = this.AllowReloadDetailData(mainData);
            if (validate)
            {
                this.AllowResetGeneParaAssemble = false;
                this.MainDataPojo = mainData;
                this.geneParaAssemble.Clear();
                this.geneParaAssemble.Add("dataClass", "DzRepCfg");
                this.geneParaAssemble.Add("C_ORG_CODE", (mainData as DzPara).C_TGH_CODE);

                ////验证通过，开始装载数据
                this.btnSearch_Click(this.btnBar.getButton(FAST.Core.BaseControl.Fun.ClsButtonName.BtnRefresh).Owner, EventArgs.Empty);
            }
        }

        /// <summary>
        /// 重写基类
        /// </summary>
        /// <param name="paraDict">paradict</param>
        /// <returns>paradict</returns>
        protected override Dictionary<string, string> OnAfterGetParaAssemble(Dictionary<string, string> paraDict)
        {
            if (null != this.MainDataPojo)
            {
                DzPara org = this.MainDataPojo as DzPara;
                paraDict["C_ORG_CODE"] = org.C_TGH_CODE;
            }

            if (!paraDict.ContainsKey("dataClass"))
            {
                paraDict.Add("dataClass", "DzRepCfg");
            }

            return paraDict;
        }

        #endregion
    } 
}
