using FAST.Core.Util;
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
using FAST.Core.BaseControl.Fun;
using System.Collections;
using YssInformation.Support.Fun;
using YssInformation.Support.Bi.Market.Service;



namespace YssSecInformation.Sv.Form
{
    /// <summary>
    /// 功能简介：外汇交易品种浏览界面，负责外汇交易品种数据的显示和查询功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2011.01.07
    ///
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：chenyoulong
    /// 修改日期：20110110
    /// 修改简介：方法的具体实现 
    ///
    /// -----------修改记录----------
    /// 当前版本：v4.5.0.3
    /// 修改人：chenyoulong
    /// 修改日期：2011.02.16
    /// 修改简介：根据需求的二次更新，进行模块的二次开发
    /// 修改主要有以下几点：
    /// 1、根据新的表结构进行调整
    /// 2、注释完善
    /// 3、提示信息调整
    /// 4、需求中细节点的控制完善
    /// 5、列头调整
    /// 6.获取币种信息的下拉控件修改成从下拉框取值
    /// 
    /// 作者：lyh 
    ///  
    ///  版本：v4.5.0.4
    ///  
    ///  修改内容：调整新需求
    ///  
    ///  修改日期：2011.3.2
    /// </summary>
    public partial class Frm_SEC_BASE_WH_L : FrmBaseList
    {
        /// <summary>
        /// dataService
        /// </summary>
        private IMktDataService mktDataService = null;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_SEC_BASE_WH_L()
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

        /// <summary>
        /// 初始化
        /// </summary>
        protected override void initServiceMVC()
        {
            base.initServiceMVC();
            mktDataService = DataServiceFactory.createService<IMktDataService>();

        }

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

        ///////<summary>
        /////// list界面加载A区数据，子类重写.
        /////// </summary>
        /////// <author>wuwenlan 2011.03.6.</author>
        /////// <returns>the result</returns>
        ////public override string yssGetLeftData()
        ////{
        ////    // edit by yh 2011.03.09修改数据来源为枚举类型,防止赋值出错
        ////    ClsEnums.DataSrc dataSrc = ClsEnums.DataSrc.SrcCache; // 数据来源是缓存
        ////    string funCode = "exchange"; // 交易市场
        ////    string headKeys = "C_MKT_NAME~!~C_MKT_CODE"; // 自定义列头

        ////    this.matchSearchStr = new string[2] { "C_MKT_NAME", "C_MKT_CODE" }; // 【搜索】功能匹配的属性

        ////    string result = null;

        ////    // 设定左侧数据的加载方式
        ////    YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;

        ////    // 调用由子类提供参数的查询方法
        ////    result = this.yssGetLeftData(dataSrc, funCode, null, headKeys, null);
        ////    return result;
        ////}

        /// <summary>
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写.
        /// </summary>
        /// <returns>the result.</returns>
        public override string yssInitQuery()
        {
            // 所有提供的参数项如下，只需要设置子类需要的项即可
            // 1 查询条件
            string cond = ""; // " and b.C_DA_CODE like 'WH%'";

            // 3 初始只加载列头，若需要则设为true，反之不需要设置此参数
            this.IsOnlyHeder = true;

            return cond;
        }

        /// <summary>
        /// 获取list查询条件区的查询条件.
        /// </summary>
        /// <returns>the result.</returns>
        public string yssGetListCond()
        {
            string cond = "";
            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
            quyStrUtil.addQuyCon("C_DA_CODE", "C_DA_CODE", "WH", ClsConstant.SQL_RA_HYPHEN_LIKE, ClsConstant._LIKE_MARCH_LEFT);
            if (this.txtSecCode.Text.Trim().Length != 0)
            {
                ////cond = " and a.C_SEC_MKT_CODE like  '" + this.txtSecCode.Text + "%'";
                quyStrUtil.addQuyCon("C_SEC_MKT_CODE", this.txtSecCode.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            if (this.cboCuryPair.Value != "" && this.cboCuryPair.Value != null)
            {
                ////cond += " and a.C_SEC_CODE_TRG = '" + this.cboCuryPair.Value.Trim() + "'";
                quyStrUtil.addQuyCon("C_SEC_CODE_TRG", this.cboCuryPair.Value, ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            // tanwenjie 2011.7.27 新增查询字段
            if (this.txtSecName.Value != null && this.txtSecName.Value.Trim().Length > 0)
            {
                ////cond += " and a.C_SEC_NAME like '%" + this.txtSecName.Value.Trim() + "%'";
                quyStrUtil.addQuyCon("C_SEC_NAME", this.txtSecName.Value, ClsConstant.SQL_RA_HYPHEN_LIKE);
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

            if (this.txtSecCode.Text.Trim().Length != 0)
            {
                paraDict.Add("C_SEC_MKT_CODE", "%" + this.txtSecCode.Text.Trim() + "%");
            }

            if (this.cboCuryPair.Value != "" && this.cboCuryPair.Value != null)
            {
                paraDict.Add("C_SEC_CODE_TRG", "%" + this.cboCuryPair.Value.Trim() + "%");
            }

            if (this.txtSecName.Value != null && this.txtSecName.Value.Trim().Length > 0)
            {
                paraDict.Add("C_SEC_NAME", "%" + this.txtSecName.Value + "%");
            }

            return paraDict;
        }

        /// <summary>
        /// load
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_BASE_WH_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            dataService = (IServiceBus)ServiceFactory.createService(serviceType);
        }

        /// <summary>
        /// 添加点击行对新增按钮的控制.      
        /// 修改人：liuliang (弃用此方法)20120423 不在通过A区控制B区的按钮状态
        /// </summary>
        /// <param name="sender">请求对象</param>
        /// <param name="e">事件对象</param>
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
        ////       //// YssMessageBox.ShowDyanInformation(ex.Message, ex.StackTrace, "点击行事件操作发生错误", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
        ////        YssMessageBox.ShowCommonInfo(_formFun.C_FUN_CODE);
        ////    }
        ////}
    }
}


