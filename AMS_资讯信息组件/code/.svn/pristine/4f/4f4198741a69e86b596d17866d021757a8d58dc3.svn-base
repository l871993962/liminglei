using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
using FAST.Core.CRUD.Form;
////
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Interface;
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
using System.Collections;



////using YssBaseCls.Fun;
////using YssData.Pojo;
////using FAST.Core.BaseControl.Fun;



////using YssData.Service.Mp;
using FAST.Common.Service.Services;
////using FAST.Common.Service.DataService;
using YssInformation.Support.Fun;
using YssInformation.Support.Pojo;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Mp.SecMktMap.Service;
using YssInformation.Support.Bi.Market.Service;
using FAST.Core.CRUD.Interface;
////using YssBaseCls.Pojo;


////using YssPara.Pojo.Bi;
////using YssPojos.Data.Mp;
////using YssPojos.Para.Bi;

////namespace YssData.Form.Mp
namespace YssSecInformation.Mp.SecMktMap.Form
{
    /// <summary>
    /// 功能简介：存款利率数据浏览界面，负责存款利率数据的显示和查询功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2011.01.25
    /// 
    /// ///   －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
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
    /// 9：根据需求删除控件
    ///10：增加左侧数据区的界面操作代码
    ///
    /// ///   －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：zhuangyuchen
    /// 修改日期： 2011.02.20
    /// 修改简介：  
    ///根据新的基类修改代码，重写查询条件
    /// </summary>
    public partial class Frm_SEC_MKT_LL_L : FrmBaseList, IFormDetailData
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
        /// 窗体数据服务对象
        /// </summary>
        private ICkMktService myService = null;

        /// <summary>
        /// 将左边区域选中的市场代码传到set窗体，增加证券市场信息的时候的根据市场类型来增加
        /// </summary>
        private string my_martCode = "";

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_SEC_MKT_LL_L()
        {
            this.bUseMVCService = true;
            InitializeComponent();
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
        /// 将左边区域选中的市场代码传到set窗体，增加证券市场信息的时候的根据市场类型来增加
        /// </summary>
        public string C_MARKET_CODE
        {
            get { return my_martCode; }
            set { my_martCode = value; }
        }

        /// <summary>
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写
        /// </summary>
        /// <returns>初始化查询条件</returns>
        public override string yssInitQuery()
        {
            // 所有提供的参数项如下，只需要设置子类需要的项即可
            // 1 查询条件
            string cond = "";

            // 3初始只加载列头，若需要则设为true，反之不需要设置此参数
            this.IsOnlyHeder = true;

            return cond;
        }

        /// <summary>
        /// 获取list查询条件区的查询条件
        /// </summary>
        /// <returns>list查询条件</returns>
        public string yssGetListCond()
        {
            string cond = "";
            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
            quyStrUtil.addQuyCon("C_MKT_CLS", "DP", "=");
            ////if (this.tbLeftMain.SelectedRow != null)
            ////{
            ////    // 如果选中的是跟节点，就传市场类型 // zhuangyuchen  2011-3-3
            ////    if (((I_BaseMkt)tbLeftMain.SelectedRow.Tag).MKT_CODE_P.Equals("[root]"))
            ////    {
            ////        cond += "and b.C_DV_MKT_TYPE = '" + ((I_BaseMkt)tbLeftMain.SelectedRow.Tag).C_DV_MKT_TYPE + "'";

            ////    }
            ////    else 
            ////    {
            ////        cond += "and b.C_MKT_CODE = '" + ((I_BaseMkt)tbLeftMain.SelectedRow.Tag).C_MKT_CODE + "'"; // 如果是子节点，就传组合代码
            ////    }

            ////}

            string search = this.yssBuildLeftCheckRowsStr("exchange");  // tanwenjie 2011.7.28 获取A区选中的行
            ////cond += "and b.c_mkt_code in (" + search + ")";
            quyStrUtil.addQuyCon("C_MKT_CODE", "C_MKT_CODE", search, "IN");
            if (this.selSecurity.Value != null && this.selSecurity.Value.Trim().Length > 0)
            {
                ////cond += " and a.C_SEC_CODE = '" + this.selSecurity.Value + "' ";  // 利率品种
                quyStrUtil.addQuyCon("C_SEC_CODE", this.selSecurity.Value, "=");
            }
          
            ////cond += " and a.D_MKT between " + "to_date( '" + this.dtpAdjustDate.getBeginDate.ToString("yyyy-MM-dd").Trim() + "','yyyy-MM-dd')" + " and " + "to_date('" + this.dtpAdjustDate.getEndDate.Date.ToString("yyyy-MM-dd").Trim() + "','yyyy-MM-dd')";      // 行情日期
            quyStrUtil.addQuyCon("dExr", "D_MKT", dtpAdjustDate.getBeginDate.ToString("yyyy-MM-dd").Trim() + "," + dtpAdjustDate.getEndDate.Date.ToString("yyyy-MM-dd").Trim(), "BETWEEN");
            cond = quyStrUtil.getQuyStr("exchange");
            return cond;
        }

        /////// <summary>
        /////// list界面加载A区数据，子类重写
        /////// </summary>
        /////// <author>zhuagnyuchen</author>
        /////// 调用公共方法加载左侧数据区的市场信息
        /////// <returns>list查询条件</returns>
        ////public override string yssGetLeftData()
        ////{
        ////    // edit by yh 2011.03.09修改数据来源为枚举类型,防止赋值出错
        ////    FAST.Core.Context.ClsEnums.DataSrc dataSrc = FAST.Core.Context.ClsEnums.DataSrc.SrcCache;    // 数据来源是缓存
        ////    string funCode = "exchange";    // 交易市场
        ////    string headKeys = "C_MKT_NAME~!~C_MKT_CODE";   // 自定义列头

        ////    matchSearchStr = new string[2] { "C_MKT_NAME", "C_MKT_CODE" };     // 【搜索】功能匹配的属性

        ////    string result = null;

        ////    // 设定左侧数据的加载方式
        ////    YssLeftKTableShowMode = FAST.Core.Context.ClsEnums.KTableDataShowMode.TreeMode;

        ////    // 调用由子类提供参数的查询方法
        ////    result = this.yssGetLeftData(dataSrc, funCode, null, headKeys, null);
        ////    return result;
        ////}

        /// <summary>
        /// 列头
        /// </summary>
        /// <returns>列头</returns>
        private List<ListHeadInfo> getLeftTableListHead()
        {
            List<ListHeadInfo> listHeadList = new List<ListHeadInfo>();

            ListHeadInfo listhead1 = new ListHeadInfo();
            listhead1.Key = "C_MKT_CODE";
            listhead1.Align = "LEFT";
            listhead1.Format = "";
            listhead1.ShowConvert = "";
            

            ListHeadInfo listhead2 = new ListHeadInfo();
            listhead2.Key = "C_MKT_NAME";
            listhead2.Align = "LEFT";
            listhead2.Format = "";
            listhead2.ShowConvert = "";
            listHeadList.Add(listhead2);
            listHeadList.Add(listhead1);

            return listHeadList;
        }

        /// <summary>
        /// A区加载数据 子类重写.
        /// </summary>
        public override void yssLoadLeftData()
        {
            QueryRes res = new QueryRes();
            IMktDataService mktDataService = DataServiceFactory.createService<IMktDataService>();
            res.DataList = mktDataService.getDataList();
            res.ListHeadList = getLeftTableListHead();
            leftDataFunCode = "exchange";
            TableListLoader tableLoader = new TableListLoader();
            this.YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;
            tableLoader.loadTable(tbLeftMain, res, false, false, ClsEnums.KTableDataShowMode.TreeMode);
            this.matchSearchStr = new string[2] { "C_MKT_NAME", "C_MKT_CODE" };  // 【搜索】功能匹配的属性 
        }

        /// <summary>
        /// 新增之前的事件，得到左边数据区的市场代码，传到set窗体，新增的时候，在该市场代码下增加
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        private void Frm_SEC_MKT_LL_L_YssOnBeforeBrowClick(object sender, YssBeforeOperEventArgs e)
        {
            ////ClsInterface inter = new ClsInterface();
            ////try
            ////{
            ////    // 如果不选择左边的数据区域，直接点击查询，就跳出来
            ////    if (this.tbLeftMain.SelectedRow != null)
            ////    {
            ////        foreach (Yss.KTable.Models.Row c in this.tbLeftMain.Rows)
            ////        {
            ////            this.C_MARKET_CODE = ((I_BaseMkt)inter.getSelectRow(c)).C_MKT_CODE;    // 得到选中的组合对象
            ////        }
            ////    }
            ////}
            ////catch (Exception ex)
            ////{
            ////    //// YssMessageBox.ShowDyanInformation(ex.Message, ex.StackTrace, "新增操作开始之前发生错误", MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
            ////    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500061", _formFun, status));
            ////    ClsBaseException.DiscardException(ex);
            ////}

        }

        /// <summary>
        /// 封装条件到对象
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>paraDict</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            paraDict.Add("C_MKT_CLS", "DP");

            string search = this.yssBuildLeftCheckRowsStr("exchange");
            search = search.Replace("'", "");
            paraDict.Add("ARRAY_C_MKT_CODE", search);
            if (this.selSecurity.Value != null && this.selSecurity.Value.Trim().Length > 0)
            {
                paraDict.Add("ARRAY_C_SEC_CODE", this.selSecurity.Value.Replace("|", ","));
            }

            paraDict.Add("D_BEGIN", dtpAdjustDate.getBeginDate.ToString("yyyy-MM-dd").Trim());
            paraDict.Add("D_END", dtpAdjustDate.getEndDate.Date.ToString("yyyy-MM-dd").Trim());

            return paraDict;
        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_MKT_LL_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.myService = ServiceFactory.createService(serviceType) as ICkMktService;
            this.dataService = this.myService;
        }

        /// <summary>
        /// 设置参数
        /// </summary>
        /// <returns>参数集合</returns>
        public override Dictionary<string, string> setExtraCondition()
        {
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("C_MKT_CLS", "DP");
            return paraDict;
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

                this.geneParaAssemble.Add("dataClass", "SecMkt");
                this.geneParaAssemble.Add("C_MKT_CLS", "DP");
                this.geneParaAssemble.Add("C_SEC_CODE", (mainData as SecBase).C_SEC_CODE);

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
                if (paraDict.ContainsKey("D_BEGIN"))
                {
                    paraDict.Remove("D_BEGIN");
                }

                if (paraDict.ContainsKey("D_END"))
                {
                    paraDict.Remove("D_END");
                }

                if (paraDict.ContainsKey("ARRAY_C_MKT_CODE"))
                {
                    paraDict.Remove("ARRAY_C_MKT_CODE");
                }

                if (paraDict.ContainsKey("C_MKT_CLS"))
                {
                    paraDict.Remove("C_MKT_CLS");
                }

                if (paraDict.ContainsKey("dataClass"))
                {
                    paraDict.Remove("dataClass");
                }


                paraDict.Add("C_MKT_CLS", "DP");
                paraDict.Add("dataClass", "SecMkt");
            }

            if (!paraDict.ContainsKey("C_SEC_CODE") || string.IsNullOrEmpty(paraDict["C_SEC_CODE"]))
            {
                if (null != this.geneParaAssemble && this.geneParaAssemble.ContainsKey("C_SEC_CODE"))
                {
                    paraDict.Add("C_SEC_CODE", this.geneParaAssemble["C_SEC_CODE"]);
                }
            }

            return paraDict;
        }


        #endregion

        #region oldcode
        /// <summary>
        /// 点击行的时候控制新增按钮是否可用
        /// zhuangyuchen  2011-3-2      
        /// 修改人：liuliang (弃用此方法)20120423 不在通过A区控制B区的按钮状态
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        ////private void tbLeftMain_RowClicked_1(object sender, Yss.KTable.Events.RowEventArgs e)
        ////{
        ////    try
        ////    {
        ////        // 判断当前选中是否为明细节点，为明细节点新增按钮可用，否则不可用
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
        ////        ////YssMessageBox.ShowDyanInformation(ex.Message, ex.StackTrace, "点击行事件操作发生错误", MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
        ////        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500067", _formFun, status));
        ////    }
        ////}

      
       
       
        /// <summary>
        /// 窗体load事件中控制新增按钮不可用
        /// zhuangyuchen 2011-3-2      
        /// 修改人：liuliang (弃用此方法)20120423 不在通过A区控制B区的按钮状态
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        ////private void Frm_SEC_MKT_LL_L_Load(object sender, EventArgs e)
        ////{
        ////    try
        ////    {
        ////        // 判断当前选中是否为明细节点，为明细节点新增按钮可用，否则不可用
        ////        if (this.tbLeftMain.SelectedRow != null && this.tbLeftMain.SelectedRow.SubRows.Count > 0)
        ////        {
        ////            ////this.btnNew.Enabled = false;
        ////            btnBar.setButtonEnabled(ClsButtonName.BtnNew, false);
        ////        }
        ////    }
        ////    catch (Exception ex)
        ////    {
        ////        ////YssMessageBox.ShowDyanInformation(ex.Message, ex.StackTrace, "加载窗体操作开始之前发生错误", MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
        ////        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500036", _formFun, status));
        ////    }

        ////}

        #endregion

 
    }
}


