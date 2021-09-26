﻿using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Communication.DataService;
using FAST.Common.Service.DataService.Base;
using FAST.Core.Exceptions;
using FAST.Core.Context;

using FAST.Platform.Right.Fun;
using FAST.Core.BaseControl.Fun;

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

using Yss.KTable.Models;




using FAST.Core.BaseControl.Fun;



using System.Collections;

using FAST.Core.BaseControl.Pojo;
using FAST.Common.Service.Services;
//using FAST.Common.Service.DataService;
using YssInformation.Support.Bi.AssociationOrgan.Pojo;
using YssSecInformation.Support.Pojo.Sec;
using YssInformation.Support.Fun;
using YssInformation.Support.Context;
using YssSecInformation.Support.Sv.Service;
using YssInformation.Support.Bi.Market.Service;
using FAST.Core.CRUD.Interface;





namespace YssSecInformation.Sv.Form
{
   ///<summary>
    /// 功能简介：理财产品信息浏览界面，负责理财产品信息的显示和查询功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.16
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：chenyoulong
    /// 修改日期：20101216
    /// 修改简介：方法的具体实现
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：lyh
    /// 修改日期：20110130
    /// 修改简介：加载a区市场信息
    ///   －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：zhuangyuchen
    /// 修改日期： 2011.02.20
    /// 修改简介：  
    /// 1：增加传到后台去的列头和窗体菜单
    /// 2：增加不同类的标识和分类传到后台去
    /// 3：出错提示信息的修改 
    /// 4： 删除以前的旧代码
    /// 5：修改POJO类为公共类
    /// 6：修改了由于POJO类更改后的属性
    /// 7:增加点击左侧数据区的记录时候，获取其中的数据库的分类标志，传到后台
    /// 8:证券品种表在控件中配置，初始化的时候删除
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：yh
    /// 修改日期：2011.02.24
    /// 修改简介：修改为新的代码结构
    /// /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.5
    /// 修改人：wuwenlan
    /// 修改日期：2011.03.2
    /// 修改简介：  对A区的加载
    ///             把A区的市场代码传输到set窗体
    ///             添加了对A区按市场查询
    /// 
    /// </summary>
    public partial class Frm_SEC_BASE_LC_L : FrmBaseListWithDetails, IFormDetailData
    {
        /// <summary>
        /// 获取或设置一个值，该值为主窗体传过来的数据。当该窗体为某一窗体的附属窗体时有效。
        /// </summary>
        private BasePojo _mainDataPojo;

        /// <summary>
        /// 获取或设置当前窗体是否已经被FrmBaseListWidthDetails窗体关联为子窗体
        /// </summary>
        private bool _hadBeenRelationed = false;

        /// <summary>
        /// dataService
        /// </summary>
        private IMktDataService mktDataService = null;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_SEC_BASE_LC_L()
        {
            InitializeComponent();
            bUseMVCService = true;
            ////实现附件功能。何讯，20151207
            this.AutoLoadEnclosure = true;
        }

        /// <summary>
        /// 重写A区配置信息，走基类老旧模式。
        /// </summary>
        public override AreaAConfigInfo AreaAConfigInfo
        {
            get
            {
                base.AreaAConfigInfo.AreaType = AreaType.BaseDefault;
                return base.AreaAConfigInfo;
            }
        }

        #region IFormDetailData 成员
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
        /// 验证是否需要重新装载数据
        /// </summary>
        /// <param name="mainData">主表传过来的数据</param>
        /// <returns>返回验证结果</returns>
        public bool AllowReloadDetailData(BasePojo mainData)
        {
            bool retValue = false;
            if (mainData != null && mainData != this.MainDataPojo)
            {
                ////业务判断，如果机构资质包含 保险委托|第3方委托 
                ////或者机构类型为 银行或者基金公司时查询
                Org org = mainData as Org;
                ////if (org.C_QUALIFICATION.Contains("委托"))
                ////{
                ////    retValue = true;
                ////}
                ////else if (org.C_DV_ORG_TYPE.Equals("ORG_JJGS") || org.C_DV_ORG_TYPE.Equals("ORG_SYYH"))
                ////{
                ////    retValue = true;
                ////}
                ////else 
                ////{
                ////    this.MainDataPojo = mainData;
                ////    this.tbMain.Rows.Clear();
                ////    this.tbMain.Refresh();
                ////}
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

            this.ShowLeftPanel = false;
            this.ShowFilterPanel = false;
        }

        /// <summary>
        /// 装载数据
        /// </summary>
        /// <param name="mainData">主表传过来的Pojo</param>
        public void LoadDetailData(FAST.Common.Service.Pojo.Base.BasePojo mainData)
        {
            if (page == null)
            {
                page = new ClsPageInation();
            }

            page.CurrPage = 1;
            page.PageCount = 0;

            bool validate = this.AllowReloadDetailData(mainData);
            if (validate)
            {
                this.AllowResetGeneParaAssemble = false;
                this.MainDataPojo = mainData;
                Org org = this.MainDataPojo as Org;
                this.geneParaAssemble.Clear();
                this.geneParaAssemble.Add("dataClass", "SecBase");
                this.geneParaAssemble.Add("C_ORG_CODE", org.C_ORG_CODE);
                ////this.geneParaAssemble.Add("C_CONTRACT_CODE", (mainData as YssPara.Pojo.Sv.SecBase).C_SEC_CODE);
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
                if (paraDict.ContainsKey("C_ORG_CODE"))
                {
                    paraDict.Remove("C_ORG_CODE");
                }

                Org org = this.MainDataPojo as Org;
                paraDict["C_ORG_CODE"] = org.C_ORG_CODE;
            }

            if (paraDict.ContainsKey("ARRAY_C_MKT_CODE") && null != paraDict["ARRAY_C_MKT_CODE"] && !"".Equals(paraDict["ARRAY_C_MKT_CODE"]))
            {
                paraDict["dataClass"] = "SecBase";
            }

            return paraDict;
        }

        #endregion

        /// <summary>
        /// 装载明细窗体功能代码列表
        /// </summary>
        /// <returns>返回明细窗体功能代码列表</returns>
        protected override List<SysFun> LoadDetailFormFuns()
        {
            List<SysFun> sysFuns = new List<SysFun>();
            SysFun newFun = new SysFun();
            ////update 马向峰 20170629 装载明细窗体不再在代码中体现，放在systemConfig.xml中配置
            //newFun.C_FUN_CODE = "bondpayinterest";
            //sysFuns.Add(newFun);

           // newFun = new SysFun();
           // newFun.C_FUN_CODE = "secmbylx";
           // sysFuns.Add(newFun);

            //// 证券流通 add by chenwenhai 20140725 STORY #17962 证券基本信息界面关联公司行为信息
            //newFun = new SysFun();
            //newFun.C_FUN_CODE = "securitycirculate";
           // sysFuns.Add(newFun);

            ////对价派息 add by chenwenhai 20140725 STORY #17962 证券基本信息界面关联公司行为信息
            //newFun = new SysFun();
            //newFun.C_FUN_CODE = "dividend";
            //sysFuns.Add(newFun);

            ////证券送配信息 add by chenwenhai 20140725 STORY #17962 证券基本信息界面关联公司行为信息
            //newFun = new SysFun();
            //newFun.C_FUN_CODE = "secdist";
           // sysFuns.Add(newFun);

            ////证券发行信息 add by chenwenhai 20140725 STORY #17962 证券基本信息界面关联公司行为信息
            //newFun = new SysFun();
           // newFun.C_FUN_CODE = "securityPublish";
           // sysFuns.Add(newFun);

            //// 证券行情映射 add by zxl 20141025 招商现场
           // newFun = new SysFun();
           // newFun.C_FUN_CODE = "portSecMap";
           // sysFuns.Add(newFun);
            
            //// 计费证券信息 added by Heliang.20160906.STORY #31596 运营费用-支持资产净值扣不计费证券需求
            //// modified by HeLiang 2017-07-20 组件拆分_下半区“计费证券信息”的关联界面（portRelaChargingSec）还没有拆出来，查询会报错，因此暂时先把注释掉
            ////loadPortRelaInfo(sysFuns);

            return sysFuns;
        }

        /// <summary>
        /// 初始化
        /// </summary>
        protected override void initServiceMVC()
        {
            base.initServiceMVC();
            mktDataService = DataServiceFactory.createService<IMktDataService>();

        }

        /// <summary>
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写.
        /// </summary>
        /// <returns>返回查询条件.</returns>
        public override string yssInitQuery()
        {
            //// 所有提供的参数项如下，只需要设置子类需要的项即可
            //// 1 查询条件
            string cond = ""; // "and b.C_DA_CODE like 'LC%'";

            //// 3 初始只加载列头，若需要则设为true，反之不需要设置此参数
            IsOnlyHeder = true;

            return cond;
        }

        /// <summary>
        /// 获取list查询条件区的查询条件.
        /// </summary>
        /// <returns>the cond</returns>
        public string yssGetListCond()
        {
            string cond = "";
            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
            quyStrUtil.addQuyCon("C_DA_CODE", "C_DA_CODE", "LC", ClsConstant.SQL_RA_HYPHEN_LIKE, ClsConstant._LIKE_MARCH_LEFT);
            ////josn对象冲突必须使用不同名称
            quyStrUtil.addQuyCon("C_DA_CODE", "C_DA_CODE", "JJ", ClsConstant.SQL_RA_HYPHEN_LIKE, ClsConstant._LIKE_MARCH_LEFT + ClsConstant._HYPHEN_OR);
            if (this.txtSecMktCode.Text.Trim().Length != 0)
            {
                ////cond = " and a.C_SEC_MKT_CODE like '" + txtSecMktCode.Text.Trim() + "%'";
                quyStrUtil.addQuyCon("C_SEC_MKT_CODE", this.txtSecMktCode.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            //// CL 20121120 STORY #3305 证券基本信息模块下的证券基本信息上市代码查询条件补充调整
            if (this.txtSecMktCode.Text.Trim().Length != 0)
            {
                quyStrUtil.addQuyCon("C_SEC_CODE", this.txtSecMktCode.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            if (this.txtSecMktCode.Text.Trim().Length != 0)
            {
                quyStrUtil.addQuyCon("C_SEC_ISIN_CODE", this.txtSecMktCode.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            if (this.txtSecName.Text.Trim().Length != 0)
            {
                ////cond += " and a.C_SEC_NAME like '" + txtSecName.Text.Trim() + "%'";
                quyStrUtil.addQuyCon("C_SEC_NAME", this.txtSecName.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            if (this.cboSecVar.Value != null)
            {
                ////cond += " and a.C_SEC_VAR_CODE like '" + this.cboSecVar.Value + "%'";
                quyStrUtil.addQuyCon("C_SEC_VAR_CODE", this.cboSecVar.Value, "=");
            }

            string search = this.yssBuildLeftCheckRowsStr("exchange");  // tanwenjie 2011.7.28 获取A区选中的行
            ////cond += "and a.C_MKT_CODE in (" + search + ")";
            quyStrUtil.addQuyCon("portCode", "C_MKT_CODE", search, "IN");
            cond = quyStrUtil.getQuyStr("exchange");
            return cond;
        }

        /// <summary>
        /// 封装条件对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            //// edit by liuxiang 2015/2/4 BUG #107516 关联机构:其中关联界面产品基本信息新增或修改之后界面不会刷新
            if (_mainDataPojo == null)
            {
                string search = this.yssBuildLeftCheckRowsStr("exchange");
                search = search.Replace("'", "");
                if (search != "")
                {
                    paraDict.Add("ARRAY_C_MKT_CODE", search);
                }
            }
            ////paraDict.Add("C_DA_CODE", "%LC%");

            if (this.txtSecMktCode.Text.Trim().Length != 0)
            {
                paraDict.Add("C_SEC_MKT_CODE", "%" + this.txtSecMktCode.Text.Trim() + "%");
            }

            //// CL 20121120 STORY #3305 证券基本信息模块下的证券基本信息上市代码查询条件补充调整
            if (this.txtSecMktCode.Text.Trim().Length != 0)
            {
                paraDict.Add("C_SEC_CODE", "%" + this.txtSecMktCode.Text.Trim() + "%");
            }

            if (this.txtSecMktCode.Text.Trim().Length != 0)
            {
                paraDict.Add("C_SEC_ISIN_CODE", "%" + this.txtSecMktCode.Text.Trim() + "%");
            }

            if (this.txtSecName.Text.Trim().Length != 0)
            {
                paraDict.Add("C_SEC_NAME", "%" + this.txtSecName.Text.Trim() + "%");
            }

            if (this.cboSecVar.Value != null && this.cboSecVar.Value.Trim().Length > 0)
            {
                paraDict.Add("C_SEC_VAR_CODE", "%" + this.cboSecVar.Value + "%");
            }

            return paraDict;
        }

        /////// <summary>
        /////// list界面加载A区数据，子类重写.
        /////// </summary>
        /////// <author>wuwenlan 2011.03.2.</author>
        /////// <returns>the result.</returns>
        ////public override string yssGetLeftData()
        ////{
        ////    //// edit by yh 2011.03.09修改数据来源为枚举类型,防止赋值出错
        ////    ClsEnums.DataSrc dataSrc = ClsEnums.DataSrc.SrcCache; // 数据来源是缓存
        ////    string funCode = "exchange"; // 交易市场
        ////    string headKeys = "C_MKT_NAME~!~C_MKT_CODE"; // 自定义列头

        ////    this.matchSearchStr = new string[2] { "C_MKT_NAME", "C_MKT_CODE" }; // 【搜索】功能匹配的属性

        ////    string result = null;
        ////    //// 设定左侧数据的加载方式
        ////    YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;
        ////    //// 调用由子类提供参数的查询方法
        ////    result = this.yssGetLeftData(dataSrc, funCode, null, headKeys, null);
        ////    return result;
        ////}

        /// <summary>
        /// 加载左侧控件数据
        /// </summary>
        public override void yssLoadLeftData()
        {
            QueryRes res = null;
            ArrayList showColumnList = new ArrayList();
            try
            {
                // edit by yh 2011.03.09修改数据来源为枚举类型,防止赋值出错
                ////leftDataFunCode = AssociaType.exchange.ToString();
                leftDataFunCode = AssociaType.base_exchange.ToString();
                matchSearchStr = new string[2] { "C_MKT_NAME", "C_MKT_CODE" };     // 【搜索】功能匹配的属性
                res = mktDataService.getDataListRes();
                showColumnList.Add("C_MKT_NAME");
                showColumnList.Add("C_MKT_CODE");
                //// 设定左侧数据的加载方式
                YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;
                new TableListLoader().loadTable(tbLeftMain, res, false, false, ClsEnums.KTableDataShowMode.TreeMode, showColumnList);

            }
            catch (Exception ex)
            {
                ////YssMessageBox.ShowDyanInformation("加载左侧交易市场信息报错", ex.Message, MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getAareaLoadErr(ex.Message));
            }
        }

        /// <summary>
        /// load
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_BASE_LC_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            dataService = (IServiceBus)ServiceFactory.createService(serviceType);

            if (!isBatchAudit)
            {
                this.addGreButton();
            }  
        }

        /// <summary>
        /// 点击行的时候控制新增按钮是否可用.
        /// wuwenlan 20110302.      
        /// 修改人：liuliang (弃用此方法)20120423 不在通过A区控制B区的按钮状态
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        ////private void tbLeftMain_RowClicked_1(object sender, Yss.KTable.Events.RowEventArgs e)
        ////{
        ////    try
        ////    {
        ////        //// 判断当前选中是否为明细节点，为明细节点新增按钮可用，否则不可用
        ////        if (this.tbLeftMain.SelectedRow != null && this.tbLeftMain.SelectedRow.SubRows.Count > 0)
        ////        {
        ////            ////this.btnNew.Enabled = false;
        ////            btnBar.setButtonEnabled(ClsButtonName.BtnNew, false);
        ////        }
        ////        else 
        ////        {
        ////            ////this.btnNew.Enabled = true;
        ////            btnBar.setButtonEnabled(ClsButtonName.BtnNew, true);
        ////        }
        ////    }
        ////    catch (Exception ex)
        ////    {
        ////        ////YssMessageBox.ShowDyanInformation("点解Aqu操作出现异常", ex.Message, "信息提示", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
        ////        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500042", _formFun, status));
        ////    }
        ////}  

        /// <summary>
        /// 添加生成按钮
        /// </summary>
        private void addGreButton()
        {
            ClsButtonInfo btnGenerate = new ClsButtonInfo();
            btnGenerate.Name = "btnGenerate";
            btnGenerate.Text = "生成";
            btnGenerate.Tooltip = "生成";
            btnGenerate.Image = FAST.Resource.Resource.btnGernerate_L;
            btnGenerate.ClickEvent += new System.EventHandler(this.btnGenerate_Click);
            this.btnBar.addButton(btnGenerate, 16);
            this.btnBar.setButtonEnabled(btnGenerate.Name, true);
        }

        /// <summary>
        /// 添加生成按钮点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnGenerate_Click(object sender, EventArgs e)
        {
            List<SecBase> secList = new List<SecBase>();
            ISecBaseLcService iSecBaseLcService = (ISecBaseLcService)dataService;
            try
            {
                if (this.tbMain.CheckedRows.Count == 0)
                {
                    return;
                }

                foreach (Row row in this.tbMain.CheckedRows)
                {
                    SecBase sec = row.Tag as SecBase;
                    if (null == sec)
                    {
                        break;
                    }

                    if (sec.C_DV_PI_MOD.Equals("NETWORTH"))
                    {
                        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonInfo("INF-100026", _formFun.C_FUN_CODE, status));
                        return;
                    }

                    secList.Add(sec);
                }

                iSecBaseLcService.multipleSecInitFi(secList);
                ////MessageBox.Show("选中的理财产品所对应的历史付息信息与每日利息数据已重新生成!", "提示");
                this.LabStatuInfo.Text = "选中的理财产品所对应的历史付息信息与每日利息数据已重新生成!";
                this.LabStatuInfo.ForeColor = System.Drawing.Color.Red;
                this.LabStatuInfo.Width = 400;
                 this.LabStatuInfo.Owner.Refresh();
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 20150303 yuyongjiang
        /// 复写审核按钮 审核同时初始化付息及每百元利息
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnAudit_Click(object sender, EventArgs e)
        {
            List<SecBase> secList = new List<SecBase>();
            ISecBaseLcService iSecBaseLcService = (ISecBaseLcService)dataService;
            try
            {
                if (this.tbMain.CheckedRows.Count == 0)
                {
                    return;
                }

                foreach (Row row in this.tbMain.CheckedRows)
                {
                    SecBase sec = row.Tag as SecBase;
                    if (null == sec)
                    {
                        break;
                    }

                    if (!sec.C_DV_PI_MOD.Equals("NETWORTH"))
                    {
                        secList.Add(sec);
                    }

                    
                }

                base.btnAudit_Click(sender, e);
                if (secList.Count > 0)
                {
                    iSecBaseLcService.multipleSecInitFi(secList);
                    this.LabStatuInfo.Text = "选中的理财产品所对应的历史付息信息与每日利息数据已重新生成!";
                    this.LabStatuInfo.ForeColor = System.Drawing.Color.Red;
                     this.LabStatuInfo.Owner.Refresh();
                }
                   
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 装载组合关联信息
        /// added by HeLiang.2016-09-05.STORY #31596 运营费用-支持资产净值扣不计费证券需求
        /// </summary>
        /// <param name="sysFuns">sysFuns</param>
        private void loadPortRelaInfo(List<SysFun> sysFuns)
        {
            SysFun newFun = new SysFun();
            newFun = ClsContext.sysMenuFunHash["sv_chargingSec"].Clone() as SysFun;
            newFun.C_FUN_CODE = "portRelaChargingSec";
            // modified by HeLiang 2017-06-29 资讯组件拆分代码调整，应使用【计费证券信息】的FunCode
            ////newFun.N_CHECK = ClsContext.sysMenuFunHash["chargingSec"].N_CHECK;
            ////newFun.N_USER = ClsContext.sysMenuFunHash["chargingSec"].N_USER;
            newFun.YssAssocia = ClsClzCfgMgr.getAssociaParam(newFun.C_FUN_CODE);
            sysFuns.Add(newFun);
            if (!ClsContext.sysMenuFunHash.ContainsKey(newFun.C_FUN_CODE))
            {
                ClsContext.sysMenuFunHash.Add(newFun.C_FUN_CODE, newFun);
            }

            if (!ClsContext.sysFunHash.ContainsKey(newFun.C_FUN_CODE))
            {
                ClsContext.sysFunHash.Add(newFun.C_FUN_CODE, newFun);
            }

            if (!ClsContext.HtUserOperRight.ContainsKey(newFun.C_FUN_CODE))
            {
                ClsContext.HtUserOperRight.Add(newFun.C_FUN_CODE, ClsContext.HtUserOperRight["sv_chargingSec"]);
            }

            if (!ClsContext.HtFunRight.ContainsKey(newFun.C_FUN_CODE))
            {
                ClsContext.HtFunRight.Add(newFun.C_FUN_CODE, ClsContext.HtFunRight["sv_chargingSec"]);
            }
        }

    }
}


