using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;

using FAST.Core.Communication.DataService;
using FAST.Core.Exceptions;

using FAST.Core.Context;

using FAST.Core.BaseControl.Fun;

using FAST.Core.Resource;



using System;
using System.Collections.Generic;



using FAST.Core.BaseControl.YssButtonBars;
using FAST.Core.BaseControl.Pojo;
using Yss.KTable.Models;
using System.Collections;
using YssInformation.Support.Fun;
using YssSecInformation.Support.Pojo.Sec;
using YssInformation.Support.Bi.Market.Service;



namespace YssSecInformation.Sv.Form
{
    /// <summary>
    /// 功能简介：综合证券信息浏览界面，负责证券信息的显示和查询功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2011.02.18
    ///
    ///  －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：wuwenlan
    /// 修改日期：2011.03.2
    /// 修改简介：  对A区的加载
    ///             把A区的市场代码传输到set窗体
    ///             添加了对A区按市场查询
    /// </summary>
    public partial class Frm_SEC_BASE_ZHZQ_L : FrmBaseList
    {
        /// <summary>
        /// 定义一个窗体,这个窗体需要弹出本窗体 chenzhong 2011-5-23
        /// </summary>
        public FrmBaseList upperForm;

        /// <summary>
        /// dataService
        /// </summary>
        private IMktDataService mktDataService = null;

        /// <summary>
        /// 窗体类型,标识不同的SET窗体
        /// </summary>
        private string frmType = "GP";

        /// <summary>
        /// 要管理的SET证券界面种类
        /// </summary>
        private string[] arySetForms = { "股票品种", "债券品种", "权证品种", "理财品种", "期货品种", "期权品种", "远期品种", "回购品种", "存款品种", "利率品种", "拆借品种" };

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_SEC_BASE_ZHZQ_L()
        {
            InitializeComponent();

            ////Orlando 2012-8-31 add
            this.bUseMVCService = true;

            addSubButtons();
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

        /// <summary>
        /// 初始化
        /// </summary>
        protected override void initServiceMVC()
        {
            base.initServiceMVC();
            mktDataService = DataServiceFactory.createService<IMktDataService>();

        }


        /// <summary>
        /// 添加新增按钮的子按钮
        /// </summary>
        private void addSubButtons()
        {
           foreach (string s in this.arySetForms)
           {
               ClsSubButtonInfo btnTmp = new ClsSubButtonInfo();
               btnTmp.Text = s;
               btnTmp.ClickEvent += new EventHandler(this.btnNewSub_Click);
               this.btnBar.addSubButton(ClsButtonName.BtnNew, btnTmp);
           }
        }

        /// <summary>
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写.
        /// </summary>
        /// <returns>返回查询条件.</returns>
        public override string yssInitQuery()
        {
            ////所有提供的参数项如下，只需要设置子类需要的项即可
            ////1 查询条件
            string cond = "";

            ////3 初始只加载列头，若需要则设为true，反之不需要设置此参数
            this.IsOnlyHeder = true;
            ////tanwenjie 2011.6.8
            // 封装投资组合查询条件
            ////if (this.tbLeftMain.SelectedRow != null)
            ////{
            ////    Cls_MKT port = (Cls_MKT)this.tbLeftMain.SelectedRow.Tag;

            ////    // 如果是跟节点则根据资产类型代码查询
            ////    if (port.ParentCode.Equals("[root]"))
            ////    {
            ////        cond = " and c.c_dv_mkt_type = '" + ((Cls_MKT)this.tbLeftMain.SelectedRow.Tag).C_DV_MKT_TYPE + "'";

            ////    }
            ////    else
            ////    {
            ////        // 如果是子节点则根据组合代码查询
            ////        cond += "and a.C_MKT_CODE = '" + port.C_MKT_CODE + "'";
            ////    }
            ////}
            ////else
            ////{
            ////    cond = "";
            ////}
            
            return cond;
        }

        ///////<summary>
        /////// list界面加载A区数据，子类重写.
        /////// </summary>
        /////// <author>wuwenlan 2011.03.2.</author>
        /////// <returns>返回a区加载的数据.</returns>
        ////public override string yssGetLeftData()
        ////{
        ////    ////edit by yh 2011.03.09修改数据来源为枚举类型,防止赋值出错
        ////    ClsEnums.DataSrc dataSrc = ClsEnums.DataSrc.SrcCache; // 数据来源是缓存
        ////    string funCode = "exchange"; // 交易市场
        ////    string headKeys = "C_MKT_NAME~!~C_MKT_CODE"; // 自定义列头

        ////    this.matchSearchStr = new string[2] { "C_MKT_NAME", "C_MKT_CODE" }; // 【搜索】功能匹配的属性

        ////    string result = null;

        ////    ////设定左侧数据的加载方式
        ////    YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;

        ////    ////调用由子类提供参数的查询方法
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
                leftDataFunCode = YssInformation.Support.Context.AssociaType.base_exchange.ToString();
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
        /// 获取list查询条件区的查询条件.
        /// </summary>
        /// <returns>返回查询条件</returns>
        public string yssGetListCond()
        {
            string cond = "";
            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
            ////quyStrUtil.addQuyCon("C_DA_CODE", "C_DA_CODE", "ZHZQ", ClsConstant.SQL_RA_HYPHEN_LIKE, ClsConstant._LIKE_MARCH_LEFT);
            ////查询条件按照：上市代码、证券名称、证券品种代码、市场代码、市场类型查询
            if (this.txtSecMktCode.Text.Trim().Length != 0)
            {
                ////cond = " and a.C_SEC_MKT_CODE like '%" + txtSecMktCode.Text.Trim() + "%'";
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
                ////cond += " and a.C_SEC_NAME like '%" + txtSecName.Text.Trim() + "%'";
                quyStrUtil.addQuyCon("C_SEC_NAME", this.txtSecName.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            if (this.cboSecVar.Value != null)
            {
                ////cond += " and a.C_SEC_VAR_CODE like '" + cboSecVar.Value + "%'";
                quyStrUtil.addQuyCon("C_SEC_VAR_CODE", this.cboSecVar.Value, ClsConstant.SQL_RA_HYPHEN_LIKE);
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
            ////STORY #33096 【招商基金】【QDII】紧急-彭博证券信息优化
            string search = this.yssBuildLeftCheckRowsStr("exchange");
            search = search.Replace("'", "");
            if (search != "")
            {
                paraDict.Add("ARRAY_C_MKT_CODE", search);
            }

            if (this.txtSecMktCode.Text.Trim().Length != 0)
            {
                paraDict.Add("C_SEC_MKT_CODE", this.txtSecMktCode.Text.Trim() + "%");
            }

            //// CL 20121120 STORY #3305 证券基本信息模块下的证券基本信息上市代码查询条件补充调整
            if (this.txtSecMktCode.Text.Trim().Length != 0)
            {
                paraDict.Add("C_SEC_CODE", this.txtSecMktCode.Text.Trim() + "%");
            }

            if (this.txtSecMktCode.Text.Trim().Length != 0)
            {
                paraDict.Add("C_SEC_ISIN_CODE", this.txtSecMktCode.Text.Trim() + "%");
            }

            if (this.txtSecName.Text.Trim().Length != 0)
            {
                paraDict.Add("C_SEC_NAME", "%" + this.txtSecName.Text.Trim() + "%");
            }

            if (this.cboSecVar.Value != null)
            {
                paraDict.Add("C_SEC_VAR_CODE", this.cboSecVar.Value + "%");
            }

            return paraDict;
        }

        /// <summary>
        /// 快捷区单击事件 wuwenlan.
        /// 主要为了控制点击根节点时，新增按钮不可以用.      
        /// 修改人：liuliang (弃用此方法)20120423 不在通过A区控制B区的按钮状态
        /// </summary>
        /// <param name="sender">请求对象</param>
        /// <param name="e">事件对象</param>
        ////private void tbLeftMain_RowClicked_1(object sender, Yss.KTable.Events.RowEventArgs e)
        ////{
        ////    try
        ////    {
        ////        ////判断当前选中是否为明细节点，为明细节点新增按钮可用，否则不可用
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
        ////       //// YssMessageBox.ShowDyanInformation("快捷区单击选中数据出现异常", ex.Message, "信息提示", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
        ////        YssMessageBox.ShowCommonInfo(_formFun.C_FUN_CODE);
        ////    }
        ////}

        /// <summary>
        /// 窗体load事件中控制新增按钮不可用.
        /// wuwenlan 20110302.
        /// </summary>
        /// <param name="sender">请求对象</param>
        /// <param name="e">事件对象</param>
        private void FrmSEC_BASE_ZHZQ_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            dataService = (IServiceBus)ServiceFactory.createService(serviceType);

            try
            {
                ////判断当前选中是否为明细节点，为明细节点新增按钮可用，否则不可用
                if (this.tbLeftMain.SelectedRow != null && this.tbLeftMain.SelectedRow.SubRows.Count > 0)
                {
                    ////this.btnNew.Enabled = false;
                    btnBar.setButtonEnabled(ClsButtonName.BtnNew, false);
                }

            }
            catch (Exception ex)
            {
                ClsBaseException.DiscardException(ex);
                ////YssMessageBox.ShowDyanInformation("【" + this.Text + "】窗体加载出现异常", ex.Message, "信息提示", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFormLoadErr(this.Text));
            }
        }

        /// <summary> 
        /// 子按钮单击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected void btnNewSub_Click(object sender, EventArgs e)
        {
            ButtonItem btnSub = null;
            if (null != sender)
            {
                try
                {
                    btnSub = sender as ButtonItem;
                    setFormSetAssocia(getFrmType(btnSub.Text));
                    base.btnNew_Click(sender, e);
                }
                catch (System.Exception ex)
                {
                    throw new ClsBaseException(ex.Message);
                }
            }
        }

        /// <summary>
        /// 新增时默认弹出股票设置界面
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnNew_Click(object sender, EventArgs e)
        {
            ////STORY #29071 【南方资本】理财业务-开放申赎业务点“新增”要先加载倒三角下的内容选择后才可以新增。
            ////张绍林-20160229
           ButtonItem btnNew = this.btnBar.getButton(ClsButtonName.BtnNew).Owner;
            btnNew.Expanded = !btnNew.Expanded;
        }

        /// <summary>
        /// 行选中事件重写,根据行数据设置不同的SET界面(解决上一条,下一条的问题)
        /// 行状态改变事件里写窗体打开,关闭很耗资源而且耗时.但此处的SET界面不固定,有十多个,为了降低
        /// 耦合,不在那些界面中去控制上一条下一条的问题,此处暂这样写.
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbMain_SelectionChanged(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            ClsAssocia asc = null;
            Row selectedRow = null;
            SecBase selectedPojo = null;
            string rowFrmType = "";
            try
            {
                selectedRow = sender as Row;

                if (selectedRow.Selected == true)
                {
                    selectedPojo = selectedRow.Tag as SecBase;
                    rowFrmType = this.getFrmType(selectedPojo.C_SEC_VAR_CODE);
                    ////窗体类型没有改变
                    if (this.frmType.Equals(rowFrmType))
                    {
                        base.tbMain_SelectionChanged(sender, e);
                    }
                    else
                    {
                        setFormSetAssocia(rowFrmType);
                        asc = this.setFrmSetAssocia();
                        //// 如SET窗体界面存在,则关闭当前SET,创建新SET界面
                        if (frmBaseViewSet != null && !frmBaseViewSet.IsDisposed)
                        {
                            this.status = ClsEnums.StatusSetting.YssBrow;
                            frmBaseViewSet.Dispose();
                            frmBaseViewSet.Close();
                            frmBaseViewSet = ReflectBase.getInstance(asc.SetDllName, asc.SetFormName) as FrmBaseSet;                            
                            frmBaseViewSet.yssShowForm(this);
                        }
                    }
                }
            }
            catch (System.Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 设置关联SET界面的关联信息
        /// </summary>
        /// <param name="rowFrmType">改变SET窗体类型的标识</param>
        private void setFormSetAssocia(string rowFrmType)
        {
            ClsAssocia asc = null;
            try
            {
                this.frmType = rowFrmType;
                asc = this.setFrmSetAssocia();
                this.sSetClassName = asc.SetFormName;
                if (ClsContext.sysFunHash.ContainsKey(asc.FunCode))
                {
                    this.YssFormMenu = ClsContext.sysMenuFunHash[asc.FunCode];
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 双击时根据数据打开不同的SET窗体
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbMain_RowDoubleClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            Row selectedRow = null;
            SecBase selectedPojo = null;
            string rowFrmType = "";
            try
            {
                selectedRow = sender as Row;
                selectedPojo = selectedRow.Tag as SecBase;
                rowFrmType = this.getFrmType(selectedPojo.C_SEC_VAR_CODE);
                ////窗体类型没有改变则更新SET界面的关联信息
                if (!this.frmType.Equals(rowFrmType))
                {
                    this.setFormSetAssocia(rowFrmType);
                }

                base.tbMain_RowDoubleClicked(sender, e);
            }
            catch (System.Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 获取SET界面关联对象
        /// </summary>
        /// <returns>SET界面关联对象</returns>
        private ClsAssocia setFrmSetAssocia()
        {
            ClsAssocia asc = null;
            if (this.frmType.Equals("GP"))
            {
                asc = ClsClzCfgMgr.getAssociaParam("sv_stockinfo");
            }
            else if (this.frmType.Equals("ZQ"))
            {
                asc = ClsClzCfgMgr.getAssociaParam("sv_fixinterest");
            }
            else if (this.frmType.Equals("QZ"))
            {
                asc = ClsClzCfgMgr.getAssociaParam("sv_certificate");
            }
            else if (this.frmType.Equals("LC"))
            {
                asc = ClsClzCfgMgr.getAssociaParam("sv_financial");
            }
            else if (this.frmType.Equals("QH"))
            {
                asc = ClsClzCfgMgr.getAssociaParam("sv_future");
            }
            else if (this.frmType.Equals("QQ"))
            {
                asc = ClsClzCfgMgr.getAssociaParam("sv_option");
            }
            else if (this.frmType.Equals("YQ"))
            {
                asc = ClsClzCfgMgr.getAssociaParam("sv_forward");
            }
            else if (this.frmType.Equals("HG"))
            {
                asc = ClsClzCfgMgr.getAssociaParam("sv_purchase");
            }
            else if (this.frmType.Equals("CK"))
            {
                asc = ClsClzCfgMgr.getAssociaParam("sv_depositInfo");
            }
            else if (this.frmType.Equals("WH"))
            {
                asc = ClsClzCfgMgr.getAssociaParam("sv_forextradecat");
            }
            else if (this.frmType.Equals("LL"))
            {
                asc = ClsClzCfgMgr.getAssociaParam("sv_interestRate");
            }
            else if (this.frmType.Equals("CJ"))
            {
                asc = ClsClzCfgMgr.getAssociaParam("sv_lending");
            }
            else
            {
                asc = ClsClzCfgMgr.getAssociaParam("sv_stockinfo");
            }

            return asc;
        }

        /// <summary>
        /// 通过多种入口设置窗体类型
        /// </summary>
        /// <param name="str">标识</param>
        /// <returns>SET窗体标识类型</returns>
        private string getFrmType(string str)
        {
            string strType = "";
            if (str.Trim().Length != 0)
            {
                if (str.Equals("股票品种") || str.Equals("新增") || str.StartsWith("GP"))
                {
                    strType = "GP";
                }
                else if (str.Equals("债券品种") || str.StartsWith("ZQ"))
                {
                    strType = "ZQ";
                }
                else if (str.Equals("权证品种") || str.StartsWith("QZ"))
                {
                    strType = "QZ";
                }
                else if (str.Equals("理财品种") || str.StartsWith("LC") || str.StartsWith("JJ"))
                {
                    strType = "LC";
                }
                else if (str.Equals("期货品种") || str.StartsWith("QH"))
                {
                    strType = "QH";
                }
                else if (str.Equals("期权品种") || str.StartsWith("QQ"))
                {
                    strType = "QQ";
                }
                else if (str.Equals("远期品种") || str.StartsWith("YQ"))
                {
                    strType = "YQ";
                }
                else if (str.Equals("回购品种") || str.StartsWith("HG"))
                {
                    strType = "HG";
                }
                else if (str.Equals("存款品种") || str.StartsWith("CK"))
                {
                    strType = "CK";
                }
                else if (str.Equals("外汇品种") || str.StartsWith("WH"))
                {
                    strType = "WH";
                }
                else if (str.Equals("利率品种") || str.StartsWith("LL"))
                {
                    strType = "LL";
                }
                else if (str.Equals("拆借品种") || str.StartsWith("CJ"))
                {
                    strType = "CJ";
                }
                else
                {
                    strType = "GP";
                }
            }

            return strType;
        }

    }

}


