using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
 
using FAST.Core.Exceptions;

using FAST.Core.Context;

using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;

using FAST.Common.Service.Pojo.Base;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Collections;
using FAST.Core.BaseControl.Pojo;
using Yss.KRichEx.AutoFilter;
using Yss.KTable.Models;
using System.Text.RegularExpressions;
using YssDevComponents.DotNetBar;
using FAST.Core.BaseControl.Fun;
using YssProductInfo.Support.Ab.Port.Service;
using FAST.Core.CRUD.Interface;
using YssInformation.Support.Bi.AssociationOrgan.Pojo;
using YssInformation.Support.Bi.Account.Pojo;
using YssInformation.Support.Bi.Account.Service;
using Yss.KTable.Collections;
using FAST.Core.Service;
using FAST.Core.Service;

namespace YssProductInfo.Ab.Port.Form
{
    /// <summary>
    /// 功能简介：组合信息浏览界面，显示组合信息的数据和实现查询功能
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.07
    ///
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：lyh
    /// 修改日期：20101210
    /// 修改简介： 实现所有的方法
    /// 
    ///  －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：lyh
    /// 修改日期：20110127
    /// 修改简介： 加载a区数据
    /// 
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：lyh
    /// 修改日期：20110211
    /// 修改简介：生成自定义表头文件
    /// 
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.5
    /// 修改人：zhuagnyuchen
    /// 修改日期：2011-3-3
    /// 修改简介:左侧加载资产类型
    /// </summary>
    public partial class Frm_PORT_L : FrmBaseListWithDetails, IFormDetailData
    {
        /// <summary>
        /// 到期清算
        /// </summary>
        private const string BUTTON_TEXT_DQQS = "到期清算";

        /// <summary>
        /// 到期撤消
        /// </summary>
        private const string BUTTON_TEXT_DQCX = "到期撤销";

        /// <summary>
        /// 到期撤消
        /// </summary>
        private const string BUTTON_TEXT_QSCX = "清算撤销";

        /// <summary>
        /// 到期确认
        /// </summary>
        private const string BUTTON_TEXT_DQQR = "到期确认";

        /// <summary>
        /// 审批确认
        /// </summary>
        private const string BUTTON_TEXT_SPQR = "审批确认";

        /// <summary>
        /// 撤销审批确认
        /// </summary>
        private const string BUTTON_TEXT_CXSPQR = "撤销审批确认";

        /// <summary>
        /// 销售确认
        /// </summary>
        private const string BUTTON_TEXT_XSQR = "销售确认";

         /// <summary>
        /// 撤销销售确认
        /// </summary>
        private const string BUTTON_TEXT_CXXSQR = "撤销销售确认";

        /// <summary>
        /// 起息确认
        /// </summary>
        private const string BUTTON_TEXT_QXQR = "起息确认";

          /// <summary>
        /// 撤销起息确认
        /// </summary>
        private const string BUTTON_TEXT_CXQXQR = "撤销起息确认";

        /// <summary>
        /// 定义字符串
        /// </summary>
        public string StrMarketCode = ""; // 用于存放资产类型代码

        /// <summary>
        /// 产品状态service
        /// </summary>
        private ISwitchProductStateService spsService;

        /// <summary>
        /// 设置产品状态，默认存续期 
        /// -----------------改为默认加载产品库 edit by liuxiang  2013/11/5   BUG #82890 组合基本参数：list工具栏有误；增加产品库分类
        /// -----------------暂时以 PS1 代替产品库
        /// </summary>
        private FAST.Core.Context.ClsEnums.PD_STATUS status_PD = ClsEnums.PD_STATUS.PS1;

        /// <summary>
        /// 定义组合的服务
        /// </summary>
        private IPortService iPortService;

        /// <summary>
        /// 获取或设置一个值，该值为主窗体传过来的数据。当该窗体为某一窗体的附属窗体时有效。
        /// </summary>
        private BasePojo _mainDataPojo;

        /// <summary>
        /// 获取或设置当前窗体是否已经被FrmBaseListWidthDetails窗体关联为子窗体
        /// </summary>
        private bool _hadBeenRelationed = false;

        /////// <summary>
        /////// 分级组合数据服务
        /////// </summary>
        ////private IPortClsService iPortClsService;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_PORT_L()
        {
            bUseMVCService = true;
            InitializeComponent();
            ////实现附件功能。何讯，20151207
            this.AutoLoadEnclosure = true;
            //// add by xuhanbing 20161231 STORY #37280 募集期产品状态无法修改 存续期放到募集期之后
            this.tabCtrlDataMain.TabPages.Remove(this.tabPageDefault);
            //// this.tabCtrlDataMain.TabPages.Clear();
            //// this.tabCtrlDataMain.TabPages.Insert(0, this.tabPageDefault);
            this.addTabPages();
            //// 将产品库放置到最后
            this.tabCtrlDataMain.TabPages.Remove(this.tabPageCPK);
            this.tabCtrlDataMain.TabPages.Add(this.tabPageCPK);
            if (this.tabCtrlDataMain.TabPages.Contains(this.tabPageCPK))
            {
                this.tabCtrlDataMain.SelectedTab = this.tabPageCPK;
            }
            if (this.tabCtrlDataMain.TabPages.Contains(this.tabPageDefault))
            {
                this.tabCtrlDataMain.SelectedTab = this.tabPageDefault;
            }

        }

        /// <summary>
        /// 属性
        /// </summary>
        public FAST.Core.Context.ClsEnums.PD_STATUS Status_PD
        {
            get { return getPdStatus(); }
        }
       
        /////// <summary>
        /////// 根据需要初始化发送到后台的参数项，若无需要可不重写.
        /////// </summary>
        /////// <returns>返回查询条件</returns>
        ////public override string yssInitQuery()
        ////{
        ////    // 所有提供的参数项如下，只需要设置子类需要的项即可.
        ////    // 1 查询条件
        ////    string cond = "";

        ////    ////3 初始只加载列头，若需要则设为true，反之不需要设置此参数
        ////    this.IsOnlyHeder = true;

        ////    return cond;
        ////}

        /////// <summary>
        /////// 获取list查询条件区的查询条件.
        /////// </summary>
        /////// <returns>返回查询条件</returns>
        ////public string yssGetListCond()
        ////{
        ////    string cond = "";
        ////    try 
        ////    {
        ////        string search = this.yssBuildLeftCheckRowsStr("accType");  // tanwenjie 2011.7.28 获取A区选中的行
        ////        ////cond += " and a.C_DAT_CODE in (" + search + ")"; // tanwenjie 2011.8.15 查询条件出错了
        ////        ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
        ////        ////modified by zhaoxianlin 20130510 STORY #3659 关于资产类型改造需求--start
        ////       //// quyStrUtil.addQuyCon("portCode", "C_DAT_CODE", search, "IN"); 
        ////        quyStrUtil.addQuyCon("portCode", "C_DAT_CODE", search, "like"); 
        ////        // 该判断是当用户取消下拉框中的问框里的值得时候，
        ////        // 由于下拉框的value的值还是保存着，所以，即使用户将下拉框清空，还是会将value传到后台查询，
        ////        // 所以加上这一曾判断，当用户将下拉框中的文本框内容清楚的时候，不去判断value值
        ////        // zhuangyuchen 2011-3-13 
        ////        ////if (this.cboPortCode.Text.Length != 0)
        ////        ////{
        ////        ////    // 投资组合
        ////        ////    if (this.cboPortCode.Value != null && this.cboPortCode.Value.Length != 0)
        ////        ////    {
        ////        ////        ////cond += "and a.C_PORT_CODE = '" + this.cboPortCode.Value.Trim() + "'";
        ////        ////        quyStrUtil.addQuyCon("C_PORT_CODE", cboPortCode.Value, "="); 
        ////        ////    }
        ////        ////}
        ////        ////if (this.cboPortName.Text.Length != 0)
        ////        ////{
        ////        ////    if (this.cboPortName.Value != null && this.cboPortName.Value.Length != 0)
        ////        ////    {
        ////        ////        ////cond += "and a.C_PORT_NAME like '%" + this.cboPortName.Value.Trim() + "%'";
        ////        ////        quyStrUtil.addQuyCon("C_PORT_NAME", this.cboPortName.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
        ////        ////    }
        ////        ////}
        ////        // 该判断是当用户取消下拉框中的问框里的值得时候，
        ////        // 由于下拉框的value的值还是保存着，所以，即使用户将下拉框清空，还是会将value传到后台查询，
        ////        // 所以加上这一曾判断，当用户将下拉框中的文本框内容清楚的时候，不去判断value值
        ////        //// zhuangyuchen 2011-3-13 
        ////        ////if (this.cboCury.Text.Length != 0)
        ////        ////{
        ////        ////    // 币种
        ////        ////    if (this.cboCury.Value != null && this.cboCury.Value.Length != 0)
        ////        ////    {
        ////        ////        ////cond += "and a.C_DC_CODE = '" + this.cboCury.Value.Trim() + "'";
        ////        ////        quyStrUtil.addQuyCon("C_DC_CODE", cboCury.Value, "="); 
        ////        ////    }
        ////        ////}
        ////        ////modified by zhaoxianlin 20130510 STORY #3659 关于资产类型改造需求--end
        ////        cond = quyStrUtil.getQuyStr("accType"); 
        ////    }
        ////    catch (ClsBaseException ex)
        ////    {
        ////        ClsBaseException.DiscardException(ex);
        ////        ////YssMessageBox.ShowDyanInformation("list界面查询报错", ex.Message, MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
        ////        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500037", _formFun, status));
        ////    }

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

         
        ////    return cond;
        ////}

        /// <summary>
        /// 获取list查询条件区的查询条件.
        /// </summary>
        /// <param name="paraDict">查询条件集合</param>
        /// <returns>返回查询条件</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            string search = this.yssBuildLeftCheckRowsStr("accType");  // tanwenjie 2011.7.28 获取A区选中的行
            search = search.Replace("'", "");

            ////paraDict.Add("ARRAY_C_DAT_CODE", search);
            ////modified by zhaoxianlin 20130510 STORY #3659 关于资产类型改造需求--start
            if (this.txtPortCode.Text.Trim().Length != 0)
            {
                // 投资组合
                paraDict.Add("C_PORT_CODE", "%" + this.txtPortCode.Text.Trim() + "%");
            }

            if (this.txtPortName.Text.Trim().Length != 0)
            {
                // 组合名称
                paraDict.Add("C_PORT_NAME", "%" + this.txtPortName.Text + "%");

            }

            if (this.cboAssetType.Text.Trim().Length != 0)
            {
                if (this.cboAssetType.Value != null && this.cboAssetType.Value.Length != 0)
                {
                    paraDict.Add("C_DAT_CODE", cboAssetType.Value);
                }
            }
            //// add by liyanjun 20160518 STORY #30595 产品基本信息界面增加“资产代码”字段、“组合级别”下拉列表--默认值“单元层、组合层”，且可以选择过滤
            if (this.txtAssetCode.Text.Trim().Length != 0)
            {
                // 资产组合
                paraDict.Add("C_ASS_CODE", "%" + this.txtAssetCode.Text.Trim() + "%");
            }

            if (this.cboPortLever.Text.Trim().Length != 0)
            {
                if (this.cboPortLever.Value != null && this.cboPortLever.Value.Length != 0)
                {
                    paraDict.Add("C_DV_PORT_CODE", cboPortLever.Value);
                }
            }

            //// 增加产品状态查询条件 STORY #4346 组合基本信息－修改变更 byleeyu20130805
            getPdStatus();
            if (status_PD == ClsEnums.PD_STATUS.PS7)
            {
                paraDict.Add("ARRAY_DV_PROD_STATE_NOTIN", " ");
            }
            else if (status_PD == ClsEnums.PD_STATUS.PS4)
            {
                ////paraDict.Add("ARRAY_DV_PROD_STATE_NOTIN", (ClsEnums.PD_STATUS.PS5 + "," + ClsEnums.PD_STATUS.PS6));
                /**
                 * Modify By HouFangzheng
                 * Date 2015.4.15
                 * Comment 如果当前选择的是存续期分页，那么只加载存续期状态的产品
                 */
                paraDict.Add("C_DV_PROD_STATE_EQU", status_PD.ToString());
            }
            else
            {
                paraDict.Add("C_DV_PROD_STATE_EQU", status_PD.ToString());
            }

            //// 添加定向产品单元层组合过滤 added by xzl
            paraDict.Add("C_PORT_UNIT", "UNIT_LAYER");

            // 该判断是当用户取消下拉框中的问框里的值得时候，
            // 由于下拉框的value的值还是保存着，所以，即使用户将下拉框清空，还是会将value传到后台查询，
            // 所以加上这一曾判断，当用户将下拉框中的文本框内容清楚的时候，不去判断value值
            // zhuangyuchen 2011-3-13 

            ////if (this.cboPortCode.Text.Length != 0)
            ////{
            ////    // 投资组合
            ////    if (this.cboPortCode.Value != null && this.cboPortCode.Value.Length != 0)
            ////    {
            ////        paraDict.Add("C_PORT_CODE", cboPortCode.Value);
            ////    }

            ////}

            // 该判断是当用户取消下拉框中的问框里的值得时候，
            // 由于下拉框的value的值还是保存着，所以，即使用户将下拉框清空，还是会将value传到后台查询，
            // 所以加上这一曾判断，当用户将下拉框中的文本框内容清楚的时候，不去判断value值
            //// zhuangyuchen 2011-3-13 

            ////if (this.cboCury.Text.Length != 0)
            ////{
            ////    // 币种
            ////    if (this.cboCury.Value != null && this.cboCury.Value.Length != 0)
            ////    {
            ////        string curyQuery = "";

            ////        if (this.cboCury.Value.IndexOf("|") > 0)
            ////        {
            ////            string[] strAry = Regex.Split(this.cboCury.Value, "[|]");

            ////            foreach (string str in strAry)
            ////            {
            ////                curyQuery += str + ",";
            ////            }

            ////            if (curyQuery.Length > 1)
            ////            {
            ////                curyQuery = curyQuery.Substring(0, curyQuery.Length - 1);
            ////            }
            ////        }
            ////        else
            ////        {
            ////            curyQuery = cboCury.Value;
            ////        }

            ////        paraDict.Add("ARRAY_C_DC_CODE", curyQuery);
            ////    }
            ////}
            ////modified by zhaoxianlin 20130510 STORY #3659 关于资产类型改造需求--end
            return paraDict;
        }

        /////// <summary>
        /////// list界面加载A区数据，子类重写.
        /////// </summary>
        /////// <author>zhaungyuchen </author>
        /////// <returns>返回左侧列表查询结果</returns>
        ////public override string yssGetLeftData()
        ////{
        ////    string result = null;

        ////    ////edit by yh 2011.03.09修改数据来源为枚举类型,防止赋值出错
        ////    ClsEnums.DataSrc dataSrc = ClsEnums.DataSrc.SrcCache; // 数据来源是缓存
        ////    string funCode = "accType"; // 要获取数据的功能代码
        ////    string cond = ""; // 查询条件,此时为词汇类型代码
        ////    string headKeys = "C_DAT_NAME"; // 自定义列头,此时为词汇类型代码

        ////    this.matchSearchStr = new string[1] { "C_DAT_NAME" }; // 【搜索】功能匹配的属性

        ////    // 设定左侧数据的加载方式
        ////    YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;

        ////    // 调用由子类提供参数的查询方法
        ////    result = this.yssGetLeftData(dataSrc, funCode, cond, headKeys, null);

        ////    return result;
        ////}

        /// <summary>
        /// 窗体加载方法
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_PORT_L_Load(object sender, EventArgs e)
        {
            ////Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            ////dataService = (IServiceBus)ServiceFactory.createService(serviceType);
            iPortService = ServiceFactory.createService<IPortService>();
            dataService = iPortService;
            ////iPortClsService = ServiceFactory.createService<IPortClsService>();
            getPdStatus();
            setButtonVisable();
            AddCopyCreateButton();
        }

        /// <summary>
        /// 装载明细窗体功能代码列表
        /// </summary>
        /// <returns>返回明细窗体功能代码列表</returns>
        protected override List<SysFun> LoadDetailFormFuns()
        {
            List<SysFun> sysFuns = new List<SysFun>();
            SysFun newFun = new SysFun();
            ////newFun.C_FUN_CODE = "investFee";
            ////sysFuns.Add(newFun);

            ////newFun = new SysFun();
            ////newFun.C_FUN_CODE = "assetright";
            ////sysFuns.Add(newFun);

            ////newFun = new SysFun();
            ////newFun.C_FUN_CODE = "carryoverAccount";
            ////sysFuns.Add(newFun);

            ////newFun = new SysFun();
            ////newFun.C_FUN_CODE = "tasettle";
            ////sysFuns.Add(newFun);

            ////newFun = new SysFun();
            ////newFun.C_FUN_CODE = "settleRisk";
            ////sysFuns.Add(newFun);

            ////newFun = new SysFun();
            ////newFun.C_FUN_CODE = "clearParams";
            ////sysFuns.Add(newFun);

            //// modified by HeLiang 2017-07-20 组件拆分_下半区关联界面的功能还没有拆出来，脱离YSSUCO查询会报错，因此暂时先把注释掉
            ////newFun = new SysFun();
            ////newFun.C_FUN_CODE = "cashaccount";
            ////sysFuns.Add(newFun);
                        
            ////newFun = new SysFun();
            ////newFun.C_FUN_CODE = "accrdefe";
            ////sysFuns.Add(newFun);
            
            ////newFun = new SysFun();
            ////newFun.C_FUN_CODE = "clearingAmount";
            ////sysFuns.Add(newFun); 

            ////newFun = new SysFun();
            ////newFun.C_FUN_CODE = "paySettleSet";
            ////sysFuns.Add(newFun);

            ////newFun = new SysFun();
            ////newFun.C_FUN_CODE = "fundtransset";
            ////sysFuns.Add(newFun);

            ////newFun = new SysFun();
            ////newFun.C_FUN_CODE = "acctparams";
            ////sysFuns.Add(newFun);
            
            ////newFun = new SysFun();
            ////newFun.C_FUN_CODE = "securitiesvaluation";
            ////sysFuns.Add(newFun);
                        
            ////newFun = new SysFun();
            ////newFun.C_FUN_CODE = "etfparamset";
            ////sysFuns.Add(newFun);

            ////newFun = new SysFun();
            ////newFun.C_FUN_CODE = "etftransfersettings";
            ////sysFuns.Add(newFun);

            ////newFun = new SysFun();
            ////newFun.C_FUN_CODE = "etfstd";
            ////sysFuns.Add(newFun);

            ////newFun = new SysFun();
            ////newFun.C_FUN_CODE = "etfcashsett";
            ////sysFuns.Add(newFun);

            ////newFun = new SysFun();
            ////newFun.C_FUN_CODE = "productgrade";
            ////sysFuns.Add(newFun);

            ////newFun = new SysFun();
            ////newFun.C_FUN_CODE = "portclssub";
            ////sysFuns.Add(newFun);

            ////newFun = new SysFun();
            ////newFun.C_FUN_CODE = "moneyPorduct";
            ////sysFuns.Add(newFun);

            ////公共组件注释掉关联页面
           // loadPortRelaInfo(sysFuns);


            //// modified by HeLiang 2017-07-20 组件拆分_下半区关联界面的功能还没有拆出来，脱离YSSUCO查询会报错，因此暂时先把注释掉
            ////loadPortRelaInfo(sysFuns);

           
            //// modified by HeLiang 2017-06-22 STORY #42921 产品信息组件拆分开发
            //// FunCode使用新增的，避免获取映射关系错误
            ////newFun = new SysFun();
            ////newFun.C_FUN_CODE = "pd_fundAccInfo";
            ////sysFuns.Add(newFun);

            ////if (ClsContext.sysMenuFunHash.ContainsKey("pd_relaFund"))
            ////{
            ////    newFun = ClsContext.sysMenuFunHash["pd_relaFund"].Clone() as SysFun;
            ////    sysFuns.Add(newFun);
            ////}

            return sysFuns;
        }

        /// <summary>
        /// 重写数据加载方法
        /// STORY #82131 【交银施罗德】2个操作界面优化需求 
        /// </summary>
        /// <param name="res">res</param>
        protected override void loadListContentMVC(QueryRes res)
        {
            TableListLoader listLoader = new TableListLoader();
            listLoader.FunCode = this.YssFormMenu.C_FUN_CODE;
            listLoader.AutoSort = false;
            listLoader.AutoLoadEnclosure = this.AutoLoadEnclosure;
            listLoader.loadTable(tbMain, res, bShowRowCheckBoxColumn, bShowRowIndexColumn, YssMainKTableShowMode);

            ////读取用户自定义列配置信息
            this.ReadTableColumnsFromConfig(this.tbMain, this.YssFormMenu.C_FUN_CODE);
            ////读取分组列信息。
            this.ReadTableGroupColumnFromConfig(this.tbMain, this.YssFormMenu.C_FUN_CODE);

            ////读取用户自定义列宽信息。张绍林-20151201
            this.ReadColumnWidthFromConfig(this.tbMain, this.YssFormMenu.C_FUN_CODE);

            if (this.clsInterface == null)
            {
                this.clsInterface = new ClsInterface();
            }

            ////STORY #72474 内容区列表头增加排序记忆功能 hp 20190712
            ClsInterface.ReadTableSortColumn(this.tbMain, this.YssFormMenu);
        }

        /// <summary>
        /// 装载组合关联信息
        /// </summary>
        /// <param name="sysFuns">sysFuns</param>
        private void loadPortRelaInfo(List<SysFun> sysFuns)
        {
            SysFun newFun = new SysFun();
            newFun = ClsContext.sysMenuFunHash["tradeSeat"].Clone() as SysFun;
            newFun.C_FUN_CODE = "portRelatradeSeat";
            newFun.N_CHECK = ClsContext.sysMenuFunHash["portrelation"].N_CHECK;
            newFun.N_USER = ClsContext.sysMenuFunHash["portrelation"].N_USER;
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
                ClsContext.HtUserOperRight.Add(newFun.C_FUN_CODE, ClsContext.HtUserOperRight["portrelation"]);
            }

            if (!ClsContext.HtFunRight.ContainsKey(newFun.C_FUN_CODE))
            {
                ClsContext.HtFunRight.Add(newFun.C_FUN_CODE, ClsContext.HtFunRight["portrelation"]);
            }

            newFun = ClsContext.sysMenuFunHash["organ"].Clone() as SysFun;
            newFun.C_FUN_CODE = "portRelaOrgan";
            newFun.N_CHECK = ClsContext.sysMenuFunHash["portrelation"].N_CHECK;
            newFun.N_USER = ClsContext.sysMenuFunHash["portrelation"].N_USER;
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
                ClsContext.HtUserOperRight.Add(newFun.C_FUN_CODE, ClsContext.HtUserOperRight["portrelation"]);
            }

            if (!ClsContext.HtFunRight.ContainsKey(newFun.C_FUN_CODE))
            {
                ClsContext.HtFunRight.Add(newFun.C_FUN_CODE, ClsContext.HtFunRight["portrelation"]);
            }

            /**
             * 稠州银行bug，打开产品信息界面报错
             * Modify By HouFangzheng 2015.3.26
             */
            if (ClsContext.sysMenuFunHash.ContainsKey("investmanager"))
            {
                newFun = ClsContext.sysMenuFunHash["investmanager"].Clone() as SysFun;
                newFun.C_FUN_CODE = "portRelaInvMgr";
                newFun.N_CHECK = ClsContext.sysMenuFunHash["portrelation"].N_CHECK;
                newFun.N_USER = ClsContext.sysMenuFunHash["portrelation"].N_USER;
                newFun.YssAssocia = ClsClzCfgMgr.getAssociaParam(newFun.C_FUN_CODE);
                sysFuns.Add(newFun);
            }

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
                ClsContext.HtUserOperRight.Add(newFun.C_FUN_CODE, ClsContext.HtUserOperRight["portrelation"]);
            }

            if (!ClsContext.HtFunRight.ContainsKey(newFun.C_FUN_CODE))
            {
                ClsContext.HtFunRight.Add(newFun.C_FUN_CODE, ClsContext.HtFunRight["portrelation"]);
            }


            newFun = ClsContext.sysMenuFunHash["tradeAccount"].Clone() as SysFun;
            newFun.C_FUN_CODE = "portRelaShAcc";
            newFun.N_CHECK = ClsContext.sysMenuFunHash["portrelation"].N_CHECK;
            newFun.N_USER = ClsContext.sysMenuFunHash["portrelation"].N_USER;
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
                ClsContext.HtUserOperRight.Add(newFun.C_FUN_CODE, ClsContext.HtUserOperRight["portrelation"]);
            }

            if (!ClsContext.HtFunRight.ContainsKey(newFun.C_FUN_CODE))
            {
                ClsContext.HtFunRight.Add(newFun.C_FUN_CODE, ClsContext.HtFunRight["portrelation"]);
            }


            newFun = ClsContext.sysMenuFunHash["tradeAccount"].Clone() as SysFun;
            newFun.C_FUN_CODE = "portRelaClNum";
            newFun.C_FUN_NAME = "客户编号设置";
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
                ClsContext.HtUserOperRight.Add(newFun.C_FUN_CODE, ClsContext.HtUserOperRight["tradeAccount"]);
            }

            if (!ClsContext.HtFunRight.ContainsKey(newFun.C_FUN_CODE))
            {
                ClsContext.HtFunRight.Add(newFun.C_FUN_CODE, ClsContext.HtFunRight["tradeAccount"]);
            }

            /**
             * 稠州银行bug，打开产品信息界面报错
             * Modify By HouFangzheng 2015.3.26
             */
            if (ClsContext.sysMenuFunHash.ContainsKey("tradeOrg"))
            {
                newFun = ClsContext.sysMenuFunHash["tradeOrg"].Clone() as SysFun;
                newFun.C_FUN_CODE = "portRelatradeOrg";
                newFun.N_CHECK = ClsContext.sysMenuFunHash["portrelation"].N_CHECK;
                newFun.N_USER = ClsContext.sysMenuFunHash["portrelation"].N_USER;
                newFun.YssAssocia = ClsClzCfgMgr.getAssociaParam(newFun.C_FUN_CODE);
                newFun.C_FUN_NAME = "期货公司设置";
                sysFuns.Add(newFun);
            }

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
                ClsContext.HtUserOperRight.Add(newFun.C_FUN_CODE, ClsContext.HtUserOperRight["portrelation"]);
            }

            if (!ClsContext.HtFunRight.ContainsKey(newFun.C_FUN_CODE))
            {
                ClsContext.HtFunRight.Add(newFun.C_FUN_CODE, ClsContext.HtFunRight["portrelation"]);
            }

        }

        /// <summary>
        /// 分页控件的调整
        /// </summary>
        /// <param name="e">事件参数</param>
        protected override void OnTabCtrlDataMainPageChanged(Yss.Controls.TabPageEventArgs e)
        {
            if (this.YssFormMenu == null)
            {
                return;
            }

            btnSearch_Click(null, EventArgs.Empty);
            getPdStatus();
            setButtonStaAfterTbMainClickMVC(new BasePojo());
        }

        /// <summary>
        /// 获取产品类型
        /// </summary>
        /// <returns>产品类型</returns>
        private FAST.Core.Context.ClsEnums.PD_STATUS getPdStatus()
        {
            if (this.tabCtrlDataMain.SelectedTab == tabPageYDQ)
            {
                status_PD = ClsEnums.PD_STATUS.PS5;
            }
            else if (this.tabCtrlDataMain.SelectedTab == tabPageYQS)
            {
                status_PD = ClsEnums.PD_STATUS.PS6;
            }
            else if (this.tabCtrlDataMain.SelectedTab == tabPageDefault)
            {
                status_PD = ClsEnums.PD_STATUS.PS4;
            }
            else if (this.tabCtrlDataMain.SelectedTab == tabPageMJQ)
            {
                status_PD = ClsEnums.PD_STATUS.PS3;
            }
            else if (this.tabCtrlDataMain.SelectedTab == tabPageDFX)
            {
                status_PD = ClsEnums.PD_STATUS.PS2;
            }
            else if (this.tabCtrlDataMain.SelectedTab == tabPageCPK)
            {
                status_PD = ClsEnums.PD_STATUS.PS7;
            }
            else
            {
                status_PD = ClsEnums.PD_STATUS.PS1;
            }

            return status_PD;
        }

        /// <summary>
        /// 单击B区数据行后刷新按钮状态
        /// </summary>
        /// <param name="pojo">数据对象</param>
        protected override void setButtonStaAfterTbMainClickMVC(BasePojo pojo)
        {
            base.setButtonStaAfterTbMainClickMVC(pojo);
            getPdStatus();
            setButtonVisable();
        }

        /// <summary>
        /// KTable的单击事件
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>  
        /// <param name="be">操作后事件</param>
        /// <param name="showRefreshFlag">刷新标志</param>
        protected override void procAfterSelectMVC(object sender, Yss.KTable.Events.RowEventArgs e, YssBeforeOperEventArgs be, bool showRefreshFlag)
        {
            base.procAfterSelectMVC(sender, e, be, showRefreshFlag);
            setButtonVisable();
        }

        /// <summary>
        /// 设置按钮是否显示
        /// </summary>
        private void setButtonVisable()
        {
            this.btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnBrow, true);
            this.btnBar.setButtonEnabled(FAST.Core.BaseControl.Fun.ClsButtonName.BtnBrow, true);

            if (status_PD == ClsEnums.PD_STATUS.PS5)
            {
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnNew, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCopy, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnEdit, false);

                ////add by liuxiang 2013/8/29 bug9276
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnDelete, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnAudit, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnUnAudit, false);

                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, true);
                btnBar.setButtonEnabled(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, true);
                btnBar.setButtonEnabled(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, true);
                btnBar.setButtonVisable("btnCopyCreate", false);
                btnBar.setButtonText(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, BUTTON_TEXT_DQQS);
                btnBar.setButtonToolTip(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, BUTTON_TEXT_DQQS);
                btnBar.setButtonText(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, BUTTON_TEXT_DQCX);
                btnBar.setButtonToolTip(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, BUTTON_TEXT_DQCX);
            }
            else if (status_PD == ClsEnums.PD_STATUS.PS6)
            {
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, true);
                btnBar.setButtonEnabled(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, false);
                btnBar.setButtonEnabled(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, true);

                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnNew, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCopy, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnEdit, false);

                ////add by liuxiang 2013/8/29 bug9276
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnDelete, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnAudit, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnUnAudit, false);
                btnBar.setButtonVisable("btnCopyCreate", false);
                btnBar.setButtonText(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, BUTTON_TEXT_QSCX);
                btnBar.setButtonToolTip(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, BUTTON_TEXT_QSCX);
            }
            else if (status_PD == ClsEnums.PD_STATUS.PS4)
            {
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, true);
                ////btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, false);
                btnBar.setButtonEnabled(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, true);
                ////btnBar.setButtonEnabled(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, false);

                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnNew, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCopy, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnEdit, true);

                ////add by liuxiang 2013/8/29 bug9276
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnDelete, true);
                btnBar.setButtonVisable("btnCopyCreate", true);
                btnBar.setButtonEnabled("btnCopyCreate", false);
                if (_formFun != null && _formFun.N_CHECK == 1)
                {
                    btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnAudit, true);
                    btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnUnAudit, true);
                }

                btnBar.setButtonText(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, BUTTON_TEXT_DQQR);
                btnBar.setButtonToolTip(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, BUTTON_TEXT_DQQR);
                btnBar.setButtonText(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, BUTTON_TEXT_CXQXQR);
                btnBar.setButtonToolTip(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, BUTTON_TEXT_CXQXQR);
            }
            else if (status_PD == ClsEnums.PD_STATUS.PS3)
            {
                //// add by xuhanbing 20161231 STORY #37280 募集期产品状态无法修改 --募集期
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnNew, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCopy, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnEdit, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnUnAudit, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, true);
                btnBar.setButtonEnabled(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, true);
                btnBar.setButtonEnabled(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, true);
                if (_formFun != null && _formFun.N_CHECK == 1)
                {
                    btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnAudit, true);
                    btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnUnAudit, true);
                }

                btnBar.setButtonVisable("btnCopyCreate", true);
                btnBar.setButtonText(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, BUTTON_TEXT_QXQR);
                btnBar.setButtonToolTip(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, BUTTON_TEXT_QXQR);
                btnBar.setButtonText(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, BUTTON_TEXT_CXXSQR);
                btnBar.setButtonToolTip(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, BUTTON_TEXT_CXXSQR);
            }
            else if (status_PD == ClsEnums.PD_STATUS.PS2)
            {
                //// add by xuhanbing 20161231 STORY #37280 募集期产品状态无法修改 --待发行期
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnNew, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCopy, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnEdit, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnUnAudit, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, true);
                btnBar.setButtonEnabled(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, true);
                btnBar.setButtonEnabled(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, true);
                if (_formFun != null && _formFun.N_CHECK == 1)
                {
                    btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnAudit, true);
                    btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnUnAudit, true);
                }

                btnBar.setButtonVisable("btnCopyCreate", true);
                btnBar.setButtonText(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, BUTTON_TEXT_XSQR);
                btnBar.setButtonToolTip(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, BUTTON_TEXT_XSQR);
                btnBar.setButtonText(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, BUTTON_TEXT_CXSPQR);
                btnBar.setButtonToolTip(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, BUTTON_TEXT_CXSPQR);
            }
            else if (status_PD == ClsEnums.PD_STATUS.PS1)
            {
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, false);
                btnBar.setButtonEnabled(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, true);
                btnBar.setButtonEnabled(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, true);

                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnNew, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCopy, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnEdit, true);

                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnDelete, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnAudit, true);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnUnAudit, true);
                btnBar.setButtonVisable("btnCopyCreate", true);
                btnBar.setButtonText(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, BUTTON_TEXT_SPQR);
                btnBar.setButtonToolTip(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, BUTTON_TEXT_SPQR);
            }
            else
            {
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnOK, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCancel, false);

                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnNew, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnCopy, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnEdit, false);

                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnDelete, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnAudit, false);
                btnBar.setButtonVisable(FAST.Core.BaseControl.Fun.ClsButtonName.BtnUnAudit, false);
                btnBar.setButtonVisable("btnCopyCreate", false);
            }

            btnBar.Refresh();
        }

        /// <summary>
        /// 重写OK的单击事件处理
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnOK_Click(object sender, EventArgs e)
        {
            ArrayList lstPort = new ArrayList();
            getPdStatus();
            if (status_PD == ClsEnums.PD_STATUS.PS4)
            {
                //// 到期确认
                ////if (tbMain.CheckedRows.Count == 0 && tbMain.SelectedRow != null)
                ////{
                ////    Port cPort = tbMain.SelectedRow.Tag as Port;
                ////    cPort.C_DV_PROD_STATE = ClsEnums.PD_STATUS.PS5.ToString();
                ////    status = ClsEnums.StatusSetting.YssEdit;
                ////    this.yssShowFromSet();
                ////}
                ////else
                ////{
                foreach (Yss.KTable.Models.Row row in tbMain.CheckedRows)
                {
                    FAST.Common.Service.Pojo.Port cPort = row.Tag as FAST.Common.Service.Pojo.Port;
                    ////cPort.D_CLEAR = Convert.ToDateTime(cPort.D_CLOSE); by liyanjun 2016-10-17 BUG #141897
                    cPort.C_DV_PROD_STATE = ClsEnums.PD_STATUS.PS5.ToString();
                    lstPort.Add(cPort);
                }

                if (lstPort.Count > 0 && YssMessageBox.ShowQuestion("确定将所选组合执行到期确认操作吗？", "提示") == DialogResult.Yes)
                {
                    iPortService.operDQQR(lstPort);
                    getMainListDataMVC(new BasePojo(), true);
                }
                ////}
            }
            else if (status_PD == ClsEnums.PD_STATUS.PS5)
            {
                //// 到期清算                
                if (tbMain.SelectedRow != null)
                {
                    ////Port cPort = tbMain.SelectedRow.Tag as Port;
                    ////cPort.C_DV_PROD_STATE = ClsEnums.PD_STATUS.PS6.ToString();
                    ////status = ClsEnums.StatusSetting.YssEdit;
                    ////this.yssShowFromSet();
                    FAST.Common.Service.Pojo.Port cPort = tbMain.SelectedRow.Tag as FAST.Common.Service.Pojo.Port;
                    cPort.C_DV_PROD_STATE = ClsEnums.PD_STATUS.PS6.ToString();
                    status = ClsEnums.StatusSetting.YssEdit;
                    this.yssShowFromSet();
                }

                ////iPortService.operQSQR(lstPort);
            }
            else if (status_PD == ClsEnums.PD_STATUS.PS3)
            {
                //// 起息确认               
                if (tbMain.SelectedRow != null)
                {
                    FAST.Common.Service.Pojo.Port cPort = tbMain.SelectedRow.Tag as FAST.Common.Service.Pojo.Port;
                    cPort.C_DV_PROD_STATE = ClsEnums.PD_STATUS.PS4.ToString();
                    lstPort.Add(cPort);
                    iPortService.operDQQR(lstPort);
                    getMainListDataMVC(new BasePojo(), true);
                }
            }
            else if (status_PD == ClsEnums.PD_STATUS.PS2)
            {
                ////销售确认               
                if (tbMain.SelectedRow != null)
                {
                    FAST.Common.Service.Pojo.Port cPort = tbMain.SelectedRow.Tag as FAST.Common.Service.Pojo.Port;
                    cPort.C_DV_PROD_STATE = ClsEnums.PD_STATUS.PS3.ToString();
                    lstPort.Add(cPort);
                    iPortService.operDQQR(lstPort);
                    getMainListDataMVC(new BasePojo(), true);
                }
            }
            else if (status_PD == ClsEnums.PD_STATUS.PS1)
            {
                ////审批确认               
                if (tbMain.SelectedRow != null)
                {
                    FAST.Common.Service.Pojo.Port cPort = tbMain.SelectedRow.Tag as FAST.Common.Service.Pojo.Port;
                    cPort.C_DV_PROD_STATE = ClsEnums.PD_STATUS.PS2.ToString();
                    lstPort.Add(cPort);
                    iPortService.operDQQR(lstPort);
                    getMainListDataMVC(new BasePojo(), true);
                }
            }
        }

        /// <summary>
        /// 重写撤销事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnCancel_Click(object sender, EventArgs e)
        {
            ArrayList lstPort = new ArrayList();
            getPdStatus();
            if (status_PD == ClsEnums.PD_STATUS.PS5)
            {
                //// 到期撤消
                foreach (Yss.KTable.Models.Row row in tbMain.CheckedRows)
                {
                    FAST.Common.Service.Pojo.Port cPort = row.Tag as FAST.Common.Service.Pojo.Port;
                    cPort.C_DV_PROD_STATE = ClsEnums.PD_STATUS.PS4.ToString();
                    lstPort.Add(cPort);
                }

                if (lstPort.Count > 0 && YssMessageBox.ShowQuestion("组合已执行过到期处理，确定将产品状态变更为存续期吗？", "提示") == DialogResult.Yes)
                {
                    iPortService.operDQQR(lstPort);
                    getMainListDataMVC(new BasePojo(), true);
                }
            }
            else if (status_PD == ClsEnums.PD_STATUS.PS6)
            {
                //// 清算撤消
                foreach (Yss.KTable.Models.Row row in tbMain.CheckedRows)
                {
                    FAST.Common.Service.Pojo.Port cPort = row.Tag as FAST.Common.Service.Pojo.Port;
                    cPort.C_DV_PROD_STATE = ClsEnums.PD_STATUS.PS5.ToString();
                    lstPort.Add(cPort);
                }

                if (lstPort.Count > 0 && YssMessageBox.ShowQuestion("组合已执行过到期清算处理，确定将产品状态变更为已到期吗？", "提示") == DialogResult.Yes)
                {
                    iPortService.operDQQR(lstPort);
                    getMainListDataMVC(new BasePojo(), true);
                }

            }
            else if (status_PD == ClsEnums.PD_STATUS.PS4)
            {
                //// 撤消起息确认
                foreach (Yss.KTable.Models.Row row in tbMain.CheckedRows)
                {
                    FAST.Common.Service.Pojo.Port cPort = row.Tag as FAST.Common.Service.Pojo.Port;
                    cPort.C_DV_PROD_STATE = ClsEnums.PD_STATUS.PS3.ToString();
                    lstPort.Add(cPort);
                }

                if (lstPort.Count > 0 && YssMessageBox.ShowQuestion("组合处于存续期状态，确定将产品状态变更为募集期吗？", "提示") == DialogResult.Yes)
                {
                    iPortService.operDQQR(lstPort);
                    getMainListDataMVC(new BasePojo(), true);
                }

            }
            else if (status_PD == ClsEnums.PD_STATUS.PS3)
            {
                //// 撤消销售确认
                foreach (Yss.KTable.Models.Row row in tbMain.CheckedRows)
                {
                    FAST.Common.Service.Pojo.Port cPort = row.Tag as FAST.Common.Service.Pojo.Port;
                    cPort.C_DV_PROD_STATE = ClsEnums.PD_STATUS.PS2.ToString();
                    lstPort.Add(cPort);
                }

                if (lstPort.Count > 0 && YssMessageBox.ShowQuestion("组合处于募集期状态，确定将产品状态变更为待发行期吗？", "提示") == DialogResult.Yes)
                {
                    iPortService.operDQQR(lstPort);
                    getMainListDataMVC(new BasePojo(), true);
                }

            }
            else if (status_PD == ClsEnums.PD_STATUS.PS2)
            {
                //// 撤消销售确认
                foreach (Yss.KTable.Models.Row row in tbMain.CheckedRows)
                {
                    FAST.Common.Service.Pojo.Port cPort = row.Tag as FAST.Common.Service.Pojo.Port;
                    cPort.C_DV_PROD_STATE = ClsEnums.PD_STATUS.PS1.ToString();
                    lstPort.Add(cPort);
                }

                if (lstPort.Count > 0 && YssMessageBox.ShowQuestion("组合处于待发行期状态，确定将产品状态变更为草稿期吗？", "提示") == DialogResult.Yes)
                {
                    iPortService.operDQQR(lstPort);
                    getMainListDataMVC(new BasePojo(), true);
                }

            }
        }

        /// <summary>
        /// 为实现批量审核模块功能（有审核功能并且有自定义参数的 需要重写该方法）
        /// </summary>
        /// <returns>参数集合</returns>
        public override Dictionary<string, string> setExtraCondition()
        {
            this.btnBar.ShowRefreshStatus = true;
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            this.GetParaAssemble(paraDict);
            return paraDict;
        }

        /// <summary>
        /// 开始装载明细数据-这里定位明细数据窗体，主数据Pojo
        /// </summary>
        /// <param name="rowMain">当前主区域选中的行</param>
        /// <param name="frmDetail">明细数据窗体</param>
        /// <param name="mainData">主数据Pojo</param>
        /// <returns>返回初始化结果</returns>
        ////protected override bool BeginReloadDetailData(Row rowMain, out FrmBase frmDetail, out BasePojo mainData)
        ////{
        ////    frmDetail = null;
        ////    mainData = null;

        ////    if (rowMain != null && rowMain.Tag != null && rowMain.Tag is BasePojo)
        ////    {
        ////        mainData = rowMain.Tag as BasePojo;
        ////    }

        ////    if (this.tabCtrlDataDetail.SelectedTab != null && this.tabCtrlDataDetail.SelectedTab.Controls.Count > 0 && this.tabCtrlDataDetail.SelectedTab.Controls[0] is FrmBaseList)
        ////    {
        ////        YssProductInfo.Support.Ab.Port.Pojo.Port port = mainData as YssProductInfo.Support.Ab.Port.Pojo.Port;
        ////        //if (port != null)
        ////        //{
        ////            frmDetail = this.tabCtrlDataDetail.SelectedTab.Controls[0] as FrmBase;
        ////        //}
        ////    }

        ////    return true;
        ////}

        /// <summary>
        /// 增加复制创建按钮。
        /// </summary>
        private void AddCopyCreateButton()
        {
            ClsSubButtonInfo btnCopyCreate = new ClsSubButtonInfo();
            btnCopyCreate.Text = "复制创建";
            btnCopyCreate.ClickEvent += new EventHandler(this.btnCopyCreate_Click);
            this.btnBar.addSubButton(ClsButtonName.BtnCopy, btnCopyCreate);

            ////ClsButtonInfo btnCopyCreate = new ClsButtonInfo();
            ////btnCopyCreate.Name = "btnCopyCreate";
            ////btnCopyCreate.Text = "复制创建";
            ////btnCopyCreate.Tooltip = "复制创建";
            ////btnCopyCreate.Image = FAST.Resource.Resource.btnCopy_L;
            ////btnCopyCreate.ClickEvent += new System.EventHandler(this.btnCopyCreate_Click);
            ////this.btnBar.addButton(btnCopyCreate, 10);
            ////this.btnBar.setButtonEnabled(btnCopyCreate.Name, false);

        }

        /// <summary>
        /// 增加复制创建按钮事件。
        /// </summary>
        /// <param name="sender">ButtonItem按钮</param>
        /// <param name="e">事件参数</param>
        private void btnCopyCreate_Click(object sender, EventArgs e)
        {
            if (this.tbMain.SelectedRow != null && this.tbMain.SelectedRow.Tag != null)
            {
                ////modified by yll 20161130 STORY #31359 复制创建功能界面的组合数据不能审核
                if (this.frmBaseViewSet != null)
                {
                    this.frmBaseViewSet.Close();
                    this.frmBaseViewSet.Dispose();
                }

                FAST.Common.Service.Pojo.Port port = this.tbMain.SelectedRow.Tag as FAST.Common.Service.Pojo.Port;
                this.YssStatus = ClsEnums.StatusSetting.YssAdd;
                Frm_PORT_S frm_PORT_S = new Frm_PORT_S();
                this.frmBaseViewSet = frm_PORT_S;
                frm_PORT_S.YssFormMenu = this._formFun;
                frm_PORT_S.initControlStat();
                frm_PORT_S.init(true, port);
                frm_PORT_S.FormBaseListView = this;
                frm_PORT_S.Show(this);
            }
        }
        /////// <summary>
        /////// 行改变事件根据组合类型加载不同关联信息分页
        /////// </summary>
        /////// <param name="sender">sender</param>
        /////// <param name="e">e</param>
        ////protected override void tbMain_SelectionChanged(object sender, Yss.KTable.Events.RowEventArgs e)
        ////{
        ////    base.tbMain_SelectionChanged(sender, e);

        ////    Port clsPort = e.Row.Tag as Port;

        ////    if (clsPort != null)
        ////    {
        ////        Dictionary<string, string> visibleDict = new Dictionary<string, string>();
        ////        Dictionary<string, string> nonVisibleDict = new Dictionary<string, string>();

        ////        if (iPortClsService == null)
        ////        {
        ////            iPortClsService = ServiceFactory.createService<IPortClsService>();
        ////        }

        ////        List<PortCls> list = iPortClsService.getPortClsByPortCode(clsPort.C_PORT_CODE);

        ////        if (list == null || list.Count == 0)
        ////        {
        ////            nonVisibleDict.Add("productgrade", "productgrade");
        ////            nonVisibleDict.Add("portclssub", "portclssub");
        ////        }
        ////        else
        ////        {
        ////            visibleDict.Add("productgrade", "productgrade");
        ////            visibleDict.Add("portclssub", "portclssub");
        ////        }

        ////        if (clsPort.C_DAT_CLS.Equals("CLS_PT"))
        ////        {
        ////            nonVisibleDict.Add("etfparamset", "etfparamset");
        ////            nonVisibleDict.Add("etftransfersettings", "etftransfersettings");
        ////            nonVisibleDict.Add("etfstd", "etfstd");
        ////            nonVisibleDict.Add("etfcashsett", "etfcashsett");
        ////            nonVisibleDict.Add("moneyPorduct", "moneyPorduct");
        ////        }
        ////        else if (clsPort.C_DAT_CLS.Equals("CLS_ETF"))
        ////        {
        ////            visibleDict.Add("etfparamset", "etfparamset");
        ////            visibleDict.Add("etftransfersettings", "etftransfersettings");
        ////            visibleDict.Add("etfstd", "etfstd");
        ////            visibleDict.Add("etfcashsett", "etfcashsett");
        ////            nonVisibleDict.Add("moneyPorduct", "moneyPorduct");
        ////        }
        ////        else if (clsPort.C_DAT_CLS.Equals("CLS_HB"))
        ////        {
        ////            nonVisibleDict.Add("etfparamset", "etfparamset");
        ////            nonVisibleDict.Add("etftransfersettings", "etftransfersettings");
        ////            nonVisibleDict.Add("etfstd", "etfstd");
        ////            nonVisibleDict.Add("etfcashsett", "etfcashsett");
        ////            visibleDict.Add("moneyPorduct", "moneyPorduct");
        ////        }

        ////        setTabCtrlDataDetailVisuality(visibleDict, nonVisibleDict);
        ////    }
        ////}

        /////// <summary>
        /////// 设置关联分页可见状态
        /////// </summary>
        /////// <param name="visibleDict">可见分页</param>
        /////// <param name="nonVisibleDict">非可见分页</param>
        ////private void setTabCtrlDataDetailVisuality(Dictionary<string, string> visibleDict, Dictionary<string, string> nonVisibleDict)
        ////{
        ////    foreach (Yss.Controls.TabPage tabPage in this.tabCtrlDataDetail.TabPages)
        ////    { 
        ////        if (visibleDict.ContainsKey(tabPage.Name))
        ////        {
        ////            tabPage.Visible = true;
        ////        }
        ////        else if (nonVisibleDict.ContainsKey(tabPage.Name))
        ////        {
        ////            tabPage.Visible = false;
        ////        }
        ////    }
        ////}

        /// <summary>
        /// panjunfang add @20151207
        /// STORY #21887 产品视图浏览需求
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnBrow_Click(object sender, EventArgs e)
        {
            object selObj;
            if (bUseMVCService)
            {
                selObj = yssGetSelTypeItemMVC();
            }
            else
            {
                selObj = yssGetSelTypeItem();
            }

            if (selObj == null)
            {
                return;
            }

            this.YssOperation = ClsEnums.StatusSetting.YssBrow;

            //// modified by HeLiang 2017-06-14 STORY #42921 产品信息组件拆分开发.临时注释
            ////Frm_PORT_INFO_DETAIL_L frmPortDetail = new Frm_PORT_INFO_DETAIL_L();
            ////frmPortDetail.PortPojo = (FAST.Common.Service.Pojo.Port)selObj;
            ////frmPortDetail.FrmPortL = this;
            ////frmPortDetail.PortLSelectionMode = this.tbMain.SelectionMode;
            ////frmPortDetail.PortLSelectRowIdx = this.tbMain.SelectedRow.MarkIndex;
            ////frmPortDetail.fillDetailData();
            SysFun sysFun = new SysFun();
            sysFun.C_FUN_NAME = "产品参数总览";
            sysFun.C_FUN_CODE = "userPortDetail";
            sysFun.YssAssocia = ClsClzCfgMgr.getAssociaParam(sysFun.C_FUN_CODE);
            ////frmPortDetail.YssFormMenu = sysFun;

            ////(frmPortDetail as FrmBaseList).yssInitForm();
            ////frmPortDetail.Show(this);
            this.tbMain.SelectionMode = SelectionMode.None;
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
                retValue = true;
            }

            return retValue;
        }

        /// <summary>
        /// 明细窗体初始化(Fast1.2版本)
        /// </summary>
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
        /// 明细窗体初始化(Fast1.1版本，无传参)
        /// </summary>
        public void InitializeDetailForm()
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
        public void LoadDetailData(BasePojo mainData)
        {
            if (page == null)
            {
                page = new ClsPageInation();
            }

            page.CurrPage = 1;
            page.PageCount = 0;
            //// 初次加载即显示数据
            isLoadFirst = true;
            bool validate = this.AllowReloadDetailData(mainData);
            if (validate)
            {
                this.AllowResetGeneParaAssemble = false;
                this.MainDataPojo = mainData;
                this.geneParaAssemble.Clear();
                this.geneParaAssemble.Add("dataClass", "Port");

                ////if (this._mainDataPojo is CashAcc)
                ////{
                ////    this.geneParaAssemble.Add("C_CA_CODE", (mainData as CashAcc).C_CA_CODE);
                ////}
                if (this._mainDataPojo is FundAcc)
                {
                    this.geneParaAssemble.Add("C_OPEN_ACC_NO", (mainData as FundAcc).C_OPEN_ACC_NO);
                }

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
            if (!paraDict.ContainsKey("dataClass"))
            {
                paraDict.Add("dataClass", "Port");
            }

            if (this._mainDataPojo is FundAcc)
            {
                if (!paraDict.ContainsKey("C_OPEN_ACC_NO") || string.IsNullOrEmpty(paraDict["C_OPEN_ACC_NO"]))
                {
                    if (null != this.geneParaAssemble && this.geneParaAssemble.ContainsKey("C_OPEN_ACC_NO"))
                    {
                        paraDict.Remove("C_OPEN_ACC_NO");
                        paraDict.Add("C_OPEN_ACC_NO", this.geneParaAssemble["C_OPEN_ACC_NO"]);
                    }
                }
            }

            return paraDict;
        }

        /// <summary>
        /// 重写删除,STORY #35130 招商基金-删除账户信息时如果此账户已被用则需弹出提示框
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnDelete_Click(object sender, EventArgs e)
        {
            ////base.btnDelete_Click(sender, e);
            DateTime _operBeginTime;
            DateTime _operEndTime;
            bool delResult = operAdaptMVC(sender, e, ClsEnums.StatusSetting.YssDel);
            if (delResult)
            {
                proBar.Value = proBar.Minimum;
                proBar.Visible = false;
                proBar.Owner.Refresh();
                _operBeginTime = DateTime.Now;
                _operEndTime = DateTime.Now; 
                double time = Math.Round((_operEndTime - _operBeginTime).TotalSeconds, 4);
                SetQuerySpendTime(time.ToString());
            }

            if (delResult)
            {
                ////删除产品账户的关联关系
                IFundAccService fundAccService = ServiceFactory.createService<IFundAccService>();
                List<YssProductInfo.Support.Ab.Port.Pojo.Port> portList = this.getTbMainCheckRows();
                if (portList.Count != 0)
                {
                    string portCodes = "";
                    for (int i = 0; i < portList.Count; i++)
                    {
                        string prots = portList[i].C_PORT_CODE;
                        portCodes = prots + ",";
                    }

                    //// 删除关联关系表
                    fundAccService.deletePortsFundRela("", portCodes);
                }
            } 
            base.btnSearch_Click(sender, e);
        }

        /// <summary>
        /// 获取主界面所选行的列数
        /// </summary>
        /// <returns></returns>
        private List<YssProductInfo.Support.Ab.Port.Pojo.Port> getTbMainCheckRows()
        {
            List<YssProductInfo.Support.Ab.Port.Pojo.Port> list = new List<YssProductInfo.Support.Ab.Port.Pojo.Port>();
            //// STORY 35761  待确认、待复核和待审批的set界面增加经办确认、通过、返回功能
            RowCollection rows;
            rows = this.tbMain.CheckedRows;
            foreach (Row row in rows)
            {
                YssProductInfo.Support.Ab.Port.Pojo.Port portPojo = row.Tag as YssProductInfo.Support.Ab.Port.Pojo.Port;
                if (portPojo == null)
                {
                    continue;
                }
                list.Add(portPojo);
            }
            return list;
        }
		
        /// <summary>
        /// 根据选择的产品状态进行添加标签
        /// </summary>
        public void addTabPages()
        {
            spsService = ServiceFactory.createService<ISwitchProductStateService>();
            List<string> prodState = spsService.getProdState();
            List<string> tempState = new List<string>();

            for (int i = 0; i < prodState.Count; i++)
            {
                if (tempState.Contains(prodState[i]))
                {
                    continue;
                }

                if ("PS1".Equals(prodState[i]) && !this.tabCtrlDataMain.TabPages.Contains(this.tabPageCG))
                {
                    ////草稿 tab
                    this.tabCtrlDataMain.TabPages.Add(this.tabPageCG);
                }
                else if ("PS2".Equals(prodState[i]) && !this.tabCtrlDataMain.TabPages.Contains(this.tabPageDFX))
                {
                    ////待发行 tab
                    this.tabCtrlDataMain.TabPages.Add(this.tabPageDFX);
                }
                else if ("PS3".Equals(prodState[i]) && !this.tabCtrlDataMain.TabPages.Contains(this.tabPageMJQ))
                {
                    ////募集期 tab
                    this.tabCtrlDataMain.TabPages.Add(this.tabPageMJQ);
                }
                else if ("PS4".Equals(prodState[i]) && !this.tabCtrlDataMain.TabPages.Contains(this.tabPageDefault))
                {
                    ////存续期 tab
                    this.tabCtrlDataMain.TabPages.Add(this.tabPageDefault);
                }
                else if ("PS5".Equals(prodState[i]) && !this.tabCtrlDataMain.TabPages.Contains(this.tabPageYDQ))
                {
                    ////已到期 tab
                    this.tabCtrlDataMain.TabPages.Add(this.tabPageYDQ);
                }
                else if ("PS6".Equals(prodState[i]) && !this.tabCtrlDataMain.TabPages.Contains(this.tabPageYQS))
                {
                    ////已清算 tab
                    this.tabCtrlDataMain.TabPages.Add(this.tabPageYQS);
                }

                tempState.Add(prodState[i]);


            }
        }

        /// <summary>
        /// 获取业务数据的相关信息，供派生类覆写
        /// 现包含数据的组合代码、业务时间等信息
        /// </summary>
        /// <param name="data">业务数据</param>
        /// <returns>Dictionary</returns>
        protected override Dictionary<string, object> getInfosFromData(BasePojo data)
        {
            Dictionary<string, object> dic = new Dictionary<string, object>();

            if (data != null)
            {
                dic.Add("C_PORT_CODE", (data as FAST.Common.Service.Pojo.Port).C_PORT_CODE);
                dic.Add("C_DATA_TIME", (data as FAST.Common.Service.Pojo.Port).ModifyDate);
            }
            else if (this.tbMain.SelectedRow != null)
            {
                dic.Add("C_PORT_CODE", (this.tbMain.SelectedRow.Tag as FAST.Common.Service.Pojo.Port).C_PORT_CODE);
                dic.Add("C_DATA_TIME", (this.tbMain.SelectedRow.Tag as FAST.Common.Service.Pojo.Port).ModifyDate);
            }

            return dic;
        }
    }

}


