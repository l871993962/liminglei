using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
////
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
using System.Collections;
using Yss.KTable.Models;



////using FAST.Core.BaseControl.Fun;
////using YssData.Service.Mp;





////using YssBaseCls.Fun;
////using FAST.Common.Service.DataService;
using YssInformation.Support.Fun;
using YssInformation.Support.Context;
using YssSecInformation.Support.Mp.SecMktMap.Service;
using YssInformation.Support.Bi.Market.Service;
////using YssBaseCls.Context;

////using YssPojos.Data.Mp;
////using YssPojos.Para.Bi;

////namespace YssData.Form.Mp
namespace YssSecInformation.Mp.SecMktMap.Form
{
    /// <summary>
    /// FrmStreetPriceList 的摘要说明。
    /// 作用：本类是为了实现场外行情数据的加载和浏览
    ///  
    ///  作者：chenyoulong
    ///  
    ///  版本：v4.5.0.1
    ///  
    ///  添加内容：窗体绘制，功能方法实现
    ///  
    ///  添加日期：2010.12.18
    ///  
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：lyh
    /// 修改日期：2011.01.30
    /// 修改简介：加载a区市场信息
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：wuwenlan
    /// 修改日期：2011.02.17
    /// 修改简介：pojo的属性变化了
    /// 添加了获取表头事件
    /// 添加了参数id
    /// 查询添加进行修改
    /// 
    ///   /// ///   －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：zhuangyuchen
    /// 修改日期： 2011.02.25
    /// 修改简介：  
    /// 修改基类更改后的代码
    /// 1：包名，类名修改
    /// 2：重写查询方法，去掉旧的方法
    /// </summary>
    public partial class Frm_SEC_MKT_CW_L : FrmBaseList
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private IOutMktService outMarketService = null;

       /// <summary>
       /// 构造函数
       /// </summary>
        public Frm_SEC_MKT_CW_L()
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
        /// 初始化查询模块控件
        /// </summary>
        public override void yssInitCtlAttr()
        {
            try
            {
                ////初始化日期控件的值取当前系统时间  tanghongpao 查询界面的日期控件默认为当前日期
                this.dtpPubDate.setDateTime(DateTime.Now);
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message, ye);
            }
        }

        /// <summary>
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写
        /// </summary>
        /// <returns >初始化查询条件</returns>
        public override string yssInitQuery()
        {
            // 所有提供的参数项如下，只需要设置子类需要的项即可
            // 1 查询条件
            string cond = ""; // " and a.C_MKT_CLS like 'OU'";   // 查询分类为场外的数据

            // 3 初始只加载列头，若需要则设为true，反之不需要设置此参数
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
            //// add by yh 2012-08-27 增加类型为场外货币的类型
            quyStrUtil.addQuyCon("C_MKT_CLS", "OU,OU_HB", "IN");
            ////if (this.tbLeftMain.SelectedRow != null)
            ////{
            ////    // 如果选中的是跟节点，就传市场类型 // zhuangyuchen  2011-3-3
            ////    if (((I_BaseMkt)tbLeftMain.SelectedRow.Tag).MKT_CODE_P.Equals("[root]"))
            ////    {
            ////        cond += "and b.C_DV_MKT_TYPE = '" + ((I_BaseMkt)tbLeftMain.SelectedRow.Tag).C_DV_MKT_TYPE + "'";

            ////    }
            ////    else 
            ////    {
            ////        cond += "and b.C_MKT_CODE = '" + ((I_BaseMkt)tbLeftMain.SelectedRow.Tag).C_MKT_CODE + "'";    // 如果是子节点，就传组合代码
            ////    }

            ////}

            string search = this.yssBuildLeftCheckRowsStr("exchange");  // tanwenjie 2011.7.28 获取A区选中的行
            ////cond += "and b.c_mkt_code in (" + search + ")";
            quyStrUtil.addQuyCon("C_MKT_CODE", "C_MKT_CODE", search, "IN");
            if (this.selSecurity.Value != null && this.selSecurity.Value.Trim().Length > 0)
            {
                ////cond = " and a.C_SEC_CODE = '" + this.selSecurity.Value + "'";  // 交易证券
                quyStrUtil.addQuyCon("C_SEC_CODE", this.selSecurity.Value, "=");
            }

            ////cond += " and a.D_MKT between " + "to_date( '" + this.dtpPubDate.getBeginDate.ToString("yyyy-MM-dd").Trim() + "','yyyy-MM-dd')" + " and " + "to_date('" + this.dtpPubDate.getEndDate.Date.ToString("yyyy-MM-dd").Trim() + "','yyyy-MM-dd')";     // 公告日期
            quyStrUtil.addQuyCon("dExr", "D_MKT", dtpPubDate.getBeginDate.ToString("yyyy-MM-dd").Trim() + "," + dtpPubDate.getEndDate.Date.ToString("yyyy-MM-dd").Trim(), "BETWEEN");
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
        ////    string funCode = "exchange";      // 交易市场
        ////    string headKeys = "C_MKT_NAME~!~C_MKT_CODE";    // 自定义列头

        ////    matchSearchStr = new string[2] { "C_MKT_NAME", "C_MKT_CODE" };     // 【搜索】功能匹配的属性

        ////    string result = null;

        ////    // 设定左侧数据的加载方式
        ////    YssLeftKTableShowMode = FAST.Core.Context.ClsEnums.KTableDataShowMode.TreeMode;

        ////    // 调用由子类提供参数的查询方法
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
                IMktDataService mktDataService = DataServiceFactory.createService<IMktDataService>();
                //// edit by yh 2011.03.09修改数据来源为枚举类型,防止赋值出错
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
        /// 封装条件为对象
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>paraDict</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            //// add by yh 2012-08-27 增加类型为场外货币的类型
            paraDict.Add("ARRAY_C_MKT_CLS", "OU,OU_HB");
            string search = this.yssBuildLeftCheckRowsStr("exchange");
            search = search.Replace("'", "");
            paraDict.Add("ARRAY_C_MKT_CODE", search);
            if (this.selSecurity.Value != null && this.selSecurity.Value.Trim().Length > 0)
            {
                paraDict.Add("ARRAY_C_SEC_CODE", this.selSecurity.Value.Replace("|", ","));
            }

            if (this.cboHqzt.Value != null && this.cboHqzt.Value.Trim().Length > 0)
            {
                paraDict.Add("ARRAY_C_HQZT_CODE", this.cboHqzt.Value.Replace("|", ",")); 
            }

            paraDict.Add("D_BEGIN", this.dtpPubDate.getBeginDate.ToString("yyyy-MM-dd").Trim());
            paraDict.Add("D_END", dtpPubDate.getEndDate.Date.ToString("yyyy-MM-dd").Trim());

            return paraDict;
        }

        /////// <summary>
        /////// 重写基类查询方法
        /////// </summary>
        /////// <param name="paraDict">paraDict</param>
        /////// <param name="page">page</param>
        /////// <returns>结果对象</returns>
        ////protected override QueryRes getQueryResultMVC(Dictionary<string, string> paraDict, PageInation page)
        ////{
        ////    QueryRes queryRes = new QueryRes();
        ////    getServiceInstance();
        ////    queryRes = this.marketValueService.selOutMarketValue(paraDict, page, _formFun.C_FUN_CODE);
        ////    return queryRes;
        ////}

        /// <summary>
        /// 创建窗体服务
        /// </summary>
        private void getServiceInstance()
        {
            if (null == this.dataService)
            {
                Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
                this.outMarketService = ServiceFactory.createService(serviceType) as IOutMktService;
                this.dataService = this.outMarketService;
            }
        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_MKT_CW_L_Load(object sender, EventArgs e)
        {
            this.getServiceInstance(); 
        }

        /// <summary>
        /// 设置参数
        /// </summary>
        /// <returns>参数集合</returns>
        public override Dictionary<string, string> setExtraCondition()
        {
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("ARRAY_C_MKT_CLS", "OU,OU_HB");
            return paraDict;
        }

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
        ////private void Frm_SEC_MKT_CW_L_Load(object sender, EventArgs e)
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


    }
}


