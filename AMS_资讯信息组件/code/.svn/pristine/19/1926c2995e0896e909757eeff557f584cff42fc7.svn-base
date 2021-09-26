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

using Yss.KTable.Models;




using FAST.Core.BaseControl.Fun;



using System.Collections;
//using FAST.Common.Service.DataService;
using YssInformation.Support.Fun;
using YssInformation.Support.Bi.Market.Service;




namespace YssSecInformation.Sv.Form
{
    /// <summary>
    /// 功能简介：回购基本信息浏览界面，负责回购基本信息的显示和查询功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.16
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：lyh
    /// 修改日期：20101216
    /// 修改简介：实现类中的具体方法
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：lyh
    /// 修改日期：20110130
    /// 修改简介：加载a区市场信息
    ///  －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：caozhonghu
    /// 修改日期：2011.02.22
    /// 修改简介：将回购基本信息前台pojo类统一替换成证券基础信息公共pojo类：ClsSecurityInfo，同时修改对应的属性名
    ///  －－－－修改记录－－－－
    /// 当前版本：V4.5.0.5
    /// 修改人：yh
    /// 修改日期：2011.02.24
    /// 修改简介：调整为新的代码结构
    ///  －－－－修改记录－－－－
    /// 当前版本：V4.5.0.6
    /// 修改人：yhm
    /// 修改日期：2011.02.26
    /// 修改简介：调整为新的代码结构
    ///  －－－－修改记录－－－－
    /// 当前版本：V4.5.0.7
    /// 修改人：wuwenlan
    /// 修改日期：2011.03.2
    /// 修改简介：  对A区的加载
    ///             把A区的市场代码传输到set窗体
    ///             添加了对A区按市场查询
    /// </summary>
    public partial class Frm_SEC_BASE_HG_L : FrmBaseList
    {
        /// <summary>
        /// dataService
        /// </summary>
        private IMktDataService mktDataService = null;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_SEC_BASE_HG_L()
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
               //// leftDataFunCode = AssociaType.exchange.ToString();
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

        /////// <summary>
        /////// list界面加载A区数据，子类重写.
        /////// </summary>
        /////// <author>wuwenlan 2011.03.2.</author>
        /////// <returns>返回a区数据.</returns>
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
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写.
        /// </summary>
        /// <returns>返回查询条件.</returns>
        public override string yssInitQuery()
        {
            ////所有提供的参数项如下，只需要设置子类需要的项即可
            ////1 查询条件 按照证券品种表中证券属性值查询
            string cond = ""; // " and b.C_DA_CODE like 'HG%'";

            ////3 初始只加载列头，若需要则设为true，反之不需要设置此参数
            IsOnlyHeder = true;

            return cond;
        }

        /// <summary>
        /// 获取list查询条件区的查询条件.
        /// </summary>
        /// <returns>返回查询条件.</returns>
        public string yssGetListCond()
        {
            string cond = "";
            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
            quyStrUtil.addQuyCon("C_DA_CODE", "C_DA_CODE", "HG", ClsConstant.SQL_RA_HYPHEN_LIKE, ClsConstant._LIKE_MARCH_LEFT);
            //// 查询条件按照：上市代码、证券品种代码、市场代码、市场类型、上市日期查询
            if (this.txtSecMarketCode.Text.Trim().Length != 0)
            {
                ////cond = " and a.C_SEC_MKT_CODE like '" + txtSecMarketCode.Text.Trim() + "%'";
                quyStrUtil.addQuyCon("C_SEC_MKT_CODE", this.txtSecMarketCode.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            //// CL 20121120 STORY #3305 证券基本信息模块下的证券基本信息上市代码查询条件补充调整
            if (this.txtSecMarketCode.Text.Trim().Length != 0)
            {
                quyStrUtil.addQuyCon("C_SEC_CODE", this.txtSecMarketCode.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            if (this.txtSecMarketCode.Text.Trim().Length != 0)
            {
                quyStrUtil.addQuyCon("C_SEC_ISIN_CODE", this.txtSecMarketCode.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            if (this.txtSecName.Text.Trim().Length != 0)
            {
                ////cond += " and a.C_SEC_NAME like '" + this.txtSecName.Text.Trim() + "%'";
                quyStrUtil.addQuyCon("C_SEC_NAME", this.txtSecName.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            if (this.cboSecCategory.Value != "" && this.cboSecCategory.Value != null)
            {
                ////cond += " and  a.C_SEC_CODE like '%" + this.cboSecCategory.Value + "%'"; // 行情日期
                quyStrUtil.addQuyCon("C_SEC_VAR_CODE", this.cboSecCategory.Value, ClsConstant.SQL_RA_HYPHEN_LIKE);
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

            //// 查询条件按照：上市代码、证券品种代码、市场代码、市场类型、上市日期查询
            if (this.txtSecMarketCode.Text.Trim().Length != 0)
            {
                paraDict.Add("C_SEC_MKT_CODE", "%" + this.txtSecMarketCode.Text.Trim() + "%");
            }

            //// CL 20121120 STORY #3305 证券基本信息模块下的证券基本信息上市代码查询条件补充调整
            if (this.txtSecMarketCode.Text.Trim().Length != 0)
            {
                paraDict.Add("C_SEC_CODE", "%" + this.txtSecMarketCode.Text.Trim() + "%");
            }

            if (this.txtSecMarketCode.Text.Trim().Length != 0)
            {
                paraDict.Add("C_SEC_ISIN_CODE", "%" + this.txtSecMarketCode.Text.Trim() + "%");
            }

            if (this.txtSecName.Text.Trim().Length != 0)
            {
                paraDict.Add("C_SEC_NAME", "%" + this.txtSecName.Text.Trim() + "%");
            }

            if (this.cboSecCategory.Value != "" && this.cboSecCategory.Value != null)
            {
                paraDict.Add("C_SEC_VAR_CODE", "%" + this.cboSecCategory.Value + "%");
            }

            return paraDict;
        }

        /// <summary>
        /// load
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_BASE_HG_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            dataService = (IServiceBus)ServiceFactory.createService(serviceType);
        }

        /// <summary>
        /// 快捷区单击事件 wuwenlan.
        /// 主要为了控制点击根节点时，新增按钮不可以用.      
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
        ////       //// YssMessageBox.ShowDyanInformation("快捷区单击选中数据出现异常", ex.Message, "信息提示", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
        ////        YssMessageBox.ShowCommonInfo(_formFun.C_FUN_CODE);
        ////    }
        ////}
       
    }
}


