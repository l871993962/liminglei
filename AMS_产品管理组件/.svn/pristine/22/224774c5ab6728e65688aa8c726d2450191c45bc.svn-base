﻿using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Core.Exceptions;
using FAST.Core.Context;
using FAST.Platform.Right.Fun;
using FAST.Core.BaseControl.Fun;
using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;
using FAST.Core.Bussiness.Form;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Services;
using FAST.Core.CRUD.Interface;
using FAST.Core.BaseControl.Pojo;

using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using Yss.KTable.Models;
using System.Collections;
using YssInformation.Support.Bi.Account.Pojo;
using YssInformation.Support.Bi.Account.Service;
using Yss.KMessage;

namespace YssProductInfo.Ab.Port.Form
{
    /// <summary>
    /// 功能简介：产品基本信息关联浏览界面，此界面用于其他界面下半区关联产品基本信息
    /// 创建版本：V5.1.0.1
    /// 创建人： HeLiang
    /// 创建日期： 2017.11.06
    /// </summary>
    public partial class Frm_PORT_RELA_L : FrmBaseList, IFormDetailData
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
        /// 存放组合关联信息
        /// </summary>
        ////private Dictionary<string, PortRela> relaPojoDict = new Dictionary<string, PortRela>();

        /// <summary>
        /// 组合关联的接口
        /// </summary>
        ////private IPortRelaService portRelaService = null;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_PORT_RELA_L()
        {
            bUseMVCService = true;
            InitializeComponent();
            ShowLeftPanel = false;
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
        /// 存放组合关联信息
        /// </summary>
        ////public Dictionary<string, PortRela> RelaPojoDict
        ////{
        ////    get { return relaPojoDict; }
        ////    set { relaPojoDict = value; }
        ////}

        /// <summary>
        /// 添加关联信息按钮
        /// </summary>
        private void addRelaButton()
        {
            ClsButtonInfo btnRela = new ClsButtonInfo();
            btnRela.Image = new Bitmap(FAST.Resource.Resource.btnItemSet, 24, 24);
            btnRela.Name = "btnRela";
            btnRela.Text = "选择";
            btnRela.Tooltip = "选择";
            btnRela.ClickEvent = btnRela_Click;
            this.btnBar.addButton(btnRela, 0);
            this.btnBar.setButtonEnabled("btnRela", true);

            List<string> list = ((List<string>)ClsContext.HtUserOperRight[_formFun.C_FUN_CODE]);

            //// BUG #137690 点击"产品基本信息分页"，显示系统异常，未将对象引用设置到对象的实例。 
            //// 增加list非空判断 
            if (list != null && !list.Contains("btnRela"))
            {
                list.Add("btnRela");
            }

            List<string> lstFun = ((List<string>)ClsContext.HtFunRight[_formFun.C_FUN_CODE]);
            if (lstFun != null && !lstFun.Contains("btnRela"))
            {
                lstFun.Add("btnRela");
            }
        }

        /// <summary> 
        /// 关联信息单击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected void btnRela_Click(object sender, EventArgs e)
        {
            if (this.MainDataPojo != null && this.MainDataPojo is FundAcc)
            {
                Frm_FUND_ACC_RELA_S frmSet = new Frm_FUND_ACC_RELA_S();
                SysFun fun = new SysFun();
                fun = (SysFun)ClsContext.sysFunHash["base_fundAccInfo"];
                frmSet.YssFormMenu = fun;
                frmSet.selectedFundAcc = MainDataPojo as FundAcc;
                frmSet.MinimizeBox = false;
                frmSet.initControlStat();
                frmSet.ShowDialog();
                base.btnSearch_Click(sender, e);
            }
            ////FrmBaseSet frmSet = new Frm_PORT_RELA_INFO_S();
            ////if ("portRelatradeOrg" == this.YssFormMenu.C_FUN_CODE.ToString())
            ////{
            ////    SysFun fun = new SysFun();
            ////    fun.C_FUN_CODE = "portTradeOrg";
            ////    fun.C_FUN_NAME = "期货公司设置";
            ////    fun.C_FUN_CODE_P = "portrelation";
            ////    if (!FAST.Core.Context.ClsContext.sysFunHash.ContainsKey(fun.C_FUN_CODE))
            ////    {
            ////        FAST.Core.Context.ClsContext.sysFunHash.Add(fun.C_FUN_CODE, fun);
            ////    }

            ////    this.dataAdmin = new ClsBaseDataAdmin();
            ////    ClsEnums.StatusSetting operation = YssOperation;
            ////    YssOperation = ClsEnums.StatusSetting.YssAdd;
            ////    this.frmBaseViewSet = frmSet;
            ////    frmSet.initForm(this);
            ////    dataAdmin.CurFUN = FAST.Core.Context.ClsContext.sysFunHash["portrelation"];
            ////    dataAdmin.C_OperMenu_Code = "portTradeOrg";
            ////    frmSet.ShowInTaskbar = false;
            ////    frmSet.Show(this);
            ////    frmSet.initControlStat();
            ////    YssOperation = operation;
            ////}
            ////else
            ////{
            ////    SysFun fun = new SysFun();
            ////    fun.C_FUN_CODE = "portTradeSeat";
            ////    fun.C_FUN_NAME = "证券交易席位";
            ////    fun.C_FUN_CODE_P = "portrelation";
            ////    if (!FAST.Core.Context.ClsContext.sysFunHash.ContainsKey(fun.C_FUN_CODE))
            ////    {
            ////        FAST.Core.Context.ClsContext.sysFunHash.Add(fun.C_FUN_CODE, fun);
            ////    }

            ////    this.dataAdmin = new ClsBaseDataAdmin();
            ////    ClsEnums.StatusSetting operation = YssOperation;
            ////    YssOperation = ClsEnums.StatusSetting.YssAdd;
            ////    this.frmBaseViewSet = frmSet;
            ////    frmSet.initForm(this);
            ////    dataAdmin.CurFUN = FAST.Core.Context.ClsContext.sysFunHash["portrelation"];
            ////    dataAdmin.C_OperMenu_Code = "portTradeSeat";
            ////    frmSet.ShowInTaskbar = false;
            ////    frmSet.Show(this);
            ////    frmSet.initControlStat();
            ////    YssOperation = operation;
            ////}
        }

        /// <summary>
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写
        /// ------- 修改记录----------
        /// 当前版本：V4.5.0.1
        /// 修改人：lyh
        /// 修改日期：2011.02.26
        /// 修改简介：初始查询方法的具体实现
        /// </summary>
        /// <returns>返回查询条件</returns>
        public override string yssInitQuery()
        {
            string cond = "";

            // 所有提供的参数项如下，只需要设置子类需要的项即可
            // 1 查询条件
            // 3 初始只加载列头，若需要则设为true，反之不需要设置此参数
            this.IsOnlyHeder = true;
            return cond;
        }

        /// <summary>
        /// 获取list查询条件区的查询条件
        /// ------- 修改记录----------
        /// 当前版本：V4.5.0.2
        /// 修改人：chenyoulong
        /// 修改日期：2011.02.25
        /// 修改简介：带条件的的查询方法的具体实现
        /// </summary>
        /// <returns>返回查询条件</returns>
        public string yssGetListCond()
        {
            string cond = "";
            return cond;
        }

        /// <summary>
        /// 窗体加载方法
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_PORT_RELA_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            dataService = (IServiceBus)ServiceFactory.createService(serviceType);
            //// 初始化【选择】按钮
            addRelaButton();
            ////portRelaService = ServiceFactory.createService<IPortRelaService>();
        }

         /// <summary>
        /// 重写单击B区数据行后刷新按钮状态
        /// </summary>
        /// <param name="pojo">数据对象</param>
        protected override void setButtonStaAfterTbMainClickMVC(BasePojo pojo)
        {
            if (this.MainDataPojo !=null && this.MainDataPojo is FundAcc)
            {
                if (this.btnBar.getButton(ClsButtonName.BtnNew) != null)
                {
                    this.btnBar.setButtonVisable(ClsButtonName.BtnNew, false);
                }

                if (this.btnBar.getButton(ClsButtonName.BtnEdit) != null)
                {
                    this.btnBar.setButtonVisable(ClsButtonName.BtnEdit, false);
                }

                if (this.btnBar.getButton(ClsButtonName.BtnCopy) != null)
                {
                    this.btnBar.setButtonVisable(ClsButtonName.BtnCopy, false);
                }
            }
        }

        /// <summary>
        /// 功能：获取查询结果以供子类复写
        /// </summary>
        /// <param name="paraDict">参数集合</param>
        /// <param name="pageIns">分页信息</param>
        /// <returns>返回结果</returns>
        protected override QueryRes getQueryResultMVC(Dictionary<string, string> paraDict, PageInation pageIns)
        {
            if (dataService == null)
            {
                initServiceMVC();
            }

            QueryRes res = null;

            //paraDict.Add("fundAccId", MainDataPojo.GetValue("Id").ToString());
            getParaDictByMainPojo(paraDict);

            res = dataService.queryByCondition(paraDict, pageIns);
            ////if ("portRelatradeOrg" == this.YssFormMenu.C_FUN_CODE.ToString())
            ////{
            ////    res = ((ITdChanService)dataService).queryPortRelaTdOrg(paraDict, pageIns);
            ////}
            ////else
            ////{
            ////    res = ((ITdChanService)dataService).queryPortRelaTdChan(paraDict, pageIns);

            ////}

            ////relaPojoDict.Clear();
            ////List<BasePojo> list = new List<BasePojo>();
            ////for (int i = 0; i < res.DataList.Count; i++)
            ////{
            ////    list.Add(res.DataList[i]);
            ////    TdChan tdchan = res.DataList[i + 1] as TdChan;
            ////    PortRelaTradeSeat portRela = new PortRelaTradeSeat();
            ////    portRela.AuditDate = tdchan.AuditDate;
            ////    portRela.AuditState = tdchan.AuditState;
            ////    portRela.C_DESC = tdchan.C_DESC;
            ////    portRela.C_CA_CODE = tdchan.C_DESC;
            ////    portRela.C_DV_TYPE_CODE = tdchan.C_DV_CHAN_TYPE;
            ////    portRela.C_PORT_CODE = tdchan.C_ORG_CODE;
            ////    portRela.C_RELA_CODE = tdchan.C_TD_CHAN_CODE;
            ////    portRela.C_RELA_TYPE = tdchan.C_MKT_CODE;
            ////    portRela.Id = tdchan.Id;
            ////    portRela.Modifier = tdchan.Modifier;
            ////    portRela.ModifyDate = tdchan.ModifyDate;
            ////    portRela.C_TD_CHAN_CODE = tdchan.C_TD_CHAN_CODE;
            ////    portRela.C_MKT_CODE = (res.DataList[i] as TdChan).C_MKT_CODE;
            ////    portRela.C_TD_CHAN_NAME = (res.DataList[i] as TdChan).C_TD_CHAN_NAME;
            ////    portRela.C_DV_CHAN_TYPE = (res.DataList[i] as TdChan).C_DV_CHAN_TYPE;
            ////    if (relaPojoDict.ContainsKey(res.DataList[i].Id))
            ////    {
            ////        i++;
            ////        continue;
            ////    }

            ////    relaPojoDict.Add(res.DataList[i].Id, portRela);
            ////    i++;
            ////}

            ////////add by liuxiang 2014/7/22 关联信息界面操作后list界面刷新后重定位
            ////if (frmBaseViewSet != null && frmBaseViewSet.ModifiedRowIds != null && frmBaseViewSet.ModifiedRowIds.Count > 0 && frmBaseViewSet.IsDisposed == false)
            ////{
            ////    if (frmBaseViewSet is Frm_PORT_RELA_INFO_S)
            ////    {
            ////        string modifiedId = frmBaseViewSet.ModifiedRowIds[0];

            ////        foreach (KeyValuePair<string, PortRela> keyValue in relaPojoDict)
            ////        {
            ////            if (keyValue.Value.Id.Equals(modifiedId))
            ////            {
            ////                frmBaseViewSet.ModifiedRowIds.Insert(0, keyValue.Key);
            ////                break;
            ////            }
            ////        }
            ////    }
            ////}

            ////res.DataList = list;
            return res;
        }

        private void getParaDictByMainPojo(Dictionary<string, string> paraDict)
        {
            if (this.MainDataPojo is FundAcc)
            {
                paraDict.Remove("C_RELA_CODE");
                paraDict.Add("C_RELA_CODE", MainDataPojo.Id);
            }
        }

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
                this.geneParaAssemble.Clear();
                this.geneParaAssemble.Add("dataClass", "Port");
                ////this.geneParaAssemble.Add("ARRAY_C_PORT_CODE", (mainData as Port).C_PORT_CODE);

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
            ////if (!paraDict.ContainsKey("dataClass"))
            ////{
            ////    paraDict.Add("dataClass", "TdChan");
            ////}

            ////if (!paraDict.ContainsKey("ARRAY_C_PORT_CODE") || string.IsNullOrEmpty(paraDict["ARRAY_C_PORT_CODE"]))
            ////{
            ////    if (null != this.geneParaAssemble && this.geneParaAssemble.ContainsKey("ARRAY_C_PORT_CODE"))
            ////    {
            ////        paraDict.Remove("ARRAY_C_PORT_CODE");
            ////        paraDict.Add("ARRAY_C_PORT_CODE", this.geneParaAssemble["ARRAY_C_PORT_CODE"]);
            ////    }
            ////}

            return paraDict;
        }

        #endregion 

        /// <summary>
        /// 删除、审核、反审核的操作转发器
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        /// <param name="operSta">操作</param>
        /// <returns>操作是否成功</returns>
        public override bool operAdaptMVC(object sender, EventArgs e, ClsEnums.StatusSetting operSta)
        {
            bool operFlag = false;
            try
            {
                status = operSta;
                ArrayList dataList = null; // 获取当前选中行的数组 yh 2011-03-13
                dataList = getSelectTypeItemListAuditable();
                if (dataList == null || dataList.Count == 0)
                {
                    return operFlag;
                }

                if (!checkBeforeOperMVC(sender, e, dataList, operSta))
                {
                    return operFlag;
                }

                string res = "";

                switch (operSta)
                {
                    case ClsEnums.StatusSetting.YssDel:

                        ////start Orlando 2012-8-31 add 
                        ////datalist可能存在已审核数据，需要剔除掉
                        ////同更应用于审核与反审核操作                      
                        for (int i = 0; i < dataList.Count; i++)
                        {
                            BasePojo pojo = (BasePojo)dataList[i];
                            ////增加过滤,开始自行复合机制时,不可以删除审核状态为1的记录. added by ll 20121012
                            if (ClsContext.sysFunHash[_formFun.C_FUN_CODE].N_CHECK == 1)
                            {
                                if (pojo is AuditableParamPojo && (pojo as AuditableParamPojo).AuditState == 1)
                                {
                                    dataList.RemoveAt(i);
                                    i--;
                                }
                            }
                        }

                        if (dataList.Count == 0)
                        {
                            return operFlag;
                        }

                        //// 关联信息数据
                        ArrayList relaDataList = new ArrayList();
                        for (int i = 0; i < dataList.Count; i++)
                        {
                            ////PortRela pojo = relaPojoDict[((BasePojo)dataList[i]).Id];
                            ////PortRela relaPojo = new PortRela();
                            ////relaPojo.C_CA_CODE = pojo.C_CA_CODE;
                            ////relaPojo.C_DESC = pojo.C_DESC;
                            ////relaPojo.C_DV_TYPE_CODE = pojo.C_DV_TYPE_CODE;
                            ////relaPojo.C_PORT_CODE = pojo.C_PORT_CODE;
                            ////relaPojo.C_RELA_CODE = pojo.C_RELA_CODE;
                            ////relaPojo.C_RELA_TYPE = pojo.C_RELA_TYPE;
                            ////relaPojo.AuditDate = pojo.AuditDate;
                            ////relaPojo.AuditState = pojo.AuditState;
                            ////relaPojo.Modifier = pojo.Modifier;
                            ////relaPojo.ModifyDate = pojo.ModifyDate;
                            ////relaPojo.Id = pojo.Id;
                            ////relaDataList.Add(relaPojo);
                        }

                        ////res = portRelaService.deleteById(relaDataList);
                        break;
                    case ClsEnums.StatusSetting.YssAudit:
                        ////////start Orlando 2012-8-31 add 
                        ////////datalist可能存在已审核数据，需要剔除掉                  
                        for (int i = 0; i < dataList.Count; i++)
                        {
                            BasePojo pojo = (BasePojo)dataList[i];
                            if ((pojo as AuditableParamPojo).AuditState == 1)
                            {
                                dataList.RemoveAt(i);
                                i--;
                            }
                        }

                        if (dataList.Count == 0)
                        {
                            return operFlag;
                        }

                        ////end Orlando 2012-8-31 add
                        dataList = DataFunction.setOperator(dataList);
                        dataList = DataFunction.setAuditStateByOperState(dataList, status);
                        //// 关联信息数据
                        relaDataList = new ArrayList();
                        for (int i = 0; i < dataList.Count; i++)
                        {
                            ////PortRela pojo = relaPojoDict[((BasePojo)dataList[i]).Id];
                            ////PortRela relaPojo = new PortRela();
                            ////relaPojo.AuditDate = pojo.AuditDate;
                            ////relaPojo.AuditState = pojo.AuditState;
                            ////relaPojo.Modifier = pojo.Modifier;
                            ////relaPojo.ModifyDate = pojo.ModifyDate;
                            ////relaPojo.Id = pojo.Id;
                            ////relaDataList.Add(relaPojo);
                        }

                        relaDataList = DataFunction.setOperator(relaDataList);
                        relaDataList = DataFunction.setAuditStateByOperState(relaDataList, status);

                        res = dataService.auditById(dataList);

                        break;
                    case ClsEnums.StatusSetting.YssUnAudit:

                        ////start Orlando 2012-8-31 add 
                        ////datalist可能存在未审核数据，需要剔除掉                    
                        for (int i = 0; i < dataList.Count; i++)
                        {
                            BasePojo pojo = (BasePojo)dataList[i];
                            if ((pojo as AuditableParamPojo).AuditState == 0)
                            {
                                dataList.RemoveAt(i);
                                i--;
                            }
                        }

                        if (dataList.Count == 0)
                        {
                            return operFlag;
                        }

                        dataList = DataFunction.setOperator(dataList);
                        dataList = DataFunction.setAuditStateByOperState(dataList, status);

                        //// 关联信息数据
                        relaDataList = new ArrayList();
                        for (int i = 0; i < dataList.Count; i++)
                        {
                            ////PortRela pojo = relaPojoDict[((BasePojo)dataList[i]).Id];
                            ////PortRela relaPojo = new PortRela();
                            ////relaPojo.AuditDate = pojo.AuditDate;
                            ////relaPojo.AuditState = pojo.AuditState;
                            ////relaPojo.Modifier = pojo.Modifier;
                            ////relaPojo.ModifyDate = pojo.ModifyDate;
                            ////relaPojo.Id = pojo.Id;
                            ////relaDataList.Add(relaPojo);
                        }

                        relaDataList = DataFunction.setOperator(relaDataList);
                        relaDataList = DataFunction.setAuditStateByOperState(relaDataList, status);

                        res = dataService.unAuditById(dataList);
                        break;
                }

                if (ClsRetInfoDealer.isJsonInfo(res))
                {
                    ClsRetInfo retInfo = new ClsRetInfo();
                    retInfo = (ClsRetInfo)ClsRetInfoDealer.getReturnInfo(res);
                    setDataCheckInfoObject(retInfo, dataList);
                    YssMessageBox.ShowCommonInfo(retInfo);

                    if ("Success" == retInfo.operRes)
                    {
                        ////STORY #34739 【南方基金】 反审核或审核数据后，要重新定位到对应的操作数据上
                        ////记录修改的行，以便重定位。张绍林-20161130
                        this.ModifiedRowIds = retInfo.cidenList;
                        BasePojo baseDataClass = (BasePojo)ReflectBase.getInstance(_formFun.YssAssocia.CommonDataClassDll, _formFun.YssAssocia.CommonDataClass);
                        getMainListDataMVC(baseDataClass, true);
                        operFlag = true;
                    }
                }
                else
                {
                    if ("Success" == res)
                    {
                        BasePojo baseDataClass = (BasePojo)ReflectBase.getInstance(_formFun.YssAssocia.CommonDataClassDll, _formFun.YssAssocia.CommonDataClass);
                        getMainListDataMVC(baseDataClass, true);
                        operFlag = true;
                    }
                    else
                    {
                        MessageBox.Show(res);
                    }

                }

                doAfterOperEvent(sender, e, status);

            }
            catch (ServiceException ex)
            {
                operFlag = false;
                throw new Exception(ex.InnerException.Message);
            }
            catch (Exception ex)
            {
                operFlag = false;
                throw new Exception(ex.Message);
            }

            return operFlag;
        }


        protected override void btnDelete_Click(object sender, EventArgs e)
        {
            if (_mainDataPojo != null && _mainDataPojo is FundAcc)
            {
                string portCodes = "";
                foreach (Row row in tbMain.SelectedRows)
                {
                    if (row.IsGroup != true)
                    {
                        if (row.Tag is BasePojo)
                        {
                            string portCode = (row.Tag as BasePojo).GetValue("C_PORT_CODE").ToString();
                            portCodes += portCode + ",";
                        }
                    }
                }

                if (string.IsNullOrEmpty(portCodes))
                {
                    return;
                }

                 DialogResult dr = new MessageDialog().Show("是否删除账户和产品的关联关系？", "提示", MessageBoxButtons.YesNo);
                 if (dr == DialogResult.No)
                 {
                     return;
                 }

                ////删除产品账户的关联关系
                IFundAccService fundAccService = ServiceFactory.createService<IFundAccService>();
                bool result= fundAccService.deletePortsFundRela(_mainDataPojo.Id, portCodes);
                
                base.btnSearch_Click(sender, e);
            }
            else
            {
                base.btnDelete_Click(sender, e);
            }
        }
        //BUG #180904 产品账户关联查询报错
        private void tbMain_RowDoubleClicked_1(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            return;
        }
    }
}




