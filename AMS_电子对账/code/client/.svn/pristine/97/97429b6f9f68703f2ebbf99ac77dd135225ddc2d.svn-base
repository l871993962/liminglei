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
using FAST.Platform.DataIntegration.Exp.Service;
using FAST.Core.BaseControl;
using FAST.Platform.DataIntegration.Exp.Pojos;
using YssElecReco.pojo.Er;
using FAST.Core.Context;

namespace YssElecReco.Form.Er
{
    /// <summary>
    /// STORY #36615 【紧急】南方基金-系统需要支持存在多个财务报表的电子对账
    /// 对账报表配置 zhanghualin 2016-12-09
    /// </summary>
    public partial class Frm_DZ_TransRepCfg_L : FrmBaseList, IFormDetailData
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
        public Frm_DZ_TransRepCfg_L()
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
        /// 添加查询条件
        /// </summary>
        /// <param name="paraDict">查询条件目标集合</param>
        /// <returns>查询条件集合</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            if (!paraDict.ContainsKey("dataClass"))
            {
                paraDict.Add("dataClass", "DzTransRepCfg");
            }

            if (this.selOrgName.Value != null && this.selOrgName.Value.Trim().Length != 0)
            {
                string val = this.selOrgName.Value;
                paraDict.Add("C_ORG_CODE", val);
            }

            if (this.selTransCode.Value != null && this.selTransCode.Value.Trim().Length != 0)
            {
                string val = this.selTransCode.Value;
                paraDict.Add("C_TRANS_CODE", val);
            }

            if (this.cboTpl.Value != null && this.cboTpl.Value.Trim().Length != 0)
            {
                string val = this.cboTpl.Value;
                paraDict.Add("C_TPL_CODE", val);
            }

            if (this.grpPort.Value != null && this.grpPort.Value.Trim().Length != 0)
            {
                string val = this.grpPort.Value;
                paraDict.Add("C_PORT_CODE", val);
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
        /// 模板配置下拉框加载数据
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboTpl_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            e.Cancel = true;
            this.getTplateByCondition();
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
                    paraDict.Add("ARRAY_FILE_TYPE", code);
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
                this.geneParaAssemble.Add("dataClass", "DzTransRepCfg");
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
                paraDict.Add("dataClass", "DzTransRepCfg");
            }

            return paraDict;
        }

        #endregion
    }
}
