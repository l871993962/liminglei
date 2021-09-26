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
using YssSecInformation.Support.Context;
using YssInformation.Support.Fun;
using YssInformation.Support.Bi.Market.Service;
////using YssBaseCls.Fun;
////using YssBaseCls.Context;









namespace YssSecInformation.Sv.Form
{
    /// <summary>
    /// 功能简介：股票基本信息浏览界面，负责股票信息的显示和查询功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.15
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：lyh
    /// 修改日期：2011.01.30
    /// 修改简介：加载a区市场信息
    /// /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：zhuangyuchen
    /// 修改日期： 2011.02.18
    /// 修改简介：
    /// 1：去掉查询界面的交易币种的查询条件
    /// 2：证券品种下拉框的的值改为从内存数据中去取
    /// 3：增加传到后台去的列头和窗体菜单
    /// 4：增加不同类的标识和分类传到后台去
    /// 4：出错提示信息的修改 
    /// 5： 删除以前的旧代码
    /// 6：修改POJO类为公共类
    /// 7：修改了由于POJO类更改后的属性
    /// 8:增加点击左侧数据区的记录时候，获取其中的数据库的分类标志，传到后台
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：yh
    /// 修改日期：2011.02.24
    /// 修改简介：修改为新的代码结构
    ///  －－－－修改记录－－－－
    /// 当前版本：V4.5.0.5
    /// 修改人：wuwenlan
    /// 修改日期：2011.03.2
    /// 修改简介：  对A区的加载
    ///             把A区的市场代码传输到set窗体
    ///             添加了对A区按市场查询
    /// </summary>
    public partial class Frm_SEC_BASE_GP_L : FrmBaseListWithDetails
    {
        /// <summary>
        /// dataService
        /// </summary>
        private IMktDataService mktDataService = null;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_SEC_BASE_GP_L()
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
        /// 装载明细窗体功能代码列表
        /// </summary>
        /// <returns>返回明细窗体功能代码列表</returns>
        protected override List<SysFun> LoadDetailFormFuns()
        {
            List<SysFun> sysFuns = new List<SysFun>();
            ////update 马向峰 20170629 装载明细窗体不再在代码中体现，放在systemConfig.xml中配置
            //// 证券流通 add by chenwenhai 20140725 STORY #17962 证券基本信息界面关联公司行为信息
           // SysFun newFun = new SysFun();
           // newFun.C_FUN_CODE = "securitycirculate";
           // sysFuns.Add(newFun);

            ////对价派息 add by chenwenhai 20140725 STORY #17962 证券基本信息界面关联公司行为信息
           // newFun = new SysFun();
           // newFun.C_FUN_CODE = "dividend";
           // sysFuns.Add(newFun);

            ////证券送配信息 add by chenwenhai 20140725 STORY #17962 证券基本信息界面关联公司行为信息
           // newFun = new SysFun();
           // newFun.C_FUN_CODE = "secdist";
           // sysFuns.Add(newFun);

            ////证券发行信息 add by chenwenhai 20140725 STORY #17962 证券基本信息界面关联公司行为信息
           // newFun = new SysFun();
           // newFun.C_FUN_CODE = "securityPublish";
           // sysFuns.Add(newFun);

            ////证券转让类型 add by caowei 20150408 STORY #21959 新三板协议转让的股票按成本估值的需求
           // newFun = new SysFun();
           // newFun.C_FUN_CODE = "secTrans";
            //sysFuns.Add(newFun);

            ////优先股计息信息 add by liyongjun 20151216 STORY18596优先股业务
           // newFun = new SysFun();
           // newFun.C_FUN_CODE = "preStockInterest";
           // sysFuns.Add(newFun);

            ////优先股计息信息 add by liyongjun 20151218 STORY18596优先股业务
           // newFun = new SysFun();
           // newFun.C_FUN_CODE = "bondpayinterest";
           // sysFuns.Add(newFun);

            ////优先股计息信息 add by liyongjun 20151218 STORY18596优先股业务
            //newFun = new SysFun();
           // newFun.C_FUN_CODE = "secmbylx";
           // sysFuns.Add(newFun);

            //// 计费证券信息 added by Heliang.20160906.STORY #31596 运营费用-支持资产净值扣不计费证券需求
            //// modified by HeLiang 2017-07-20 组件拆分_下半区“计费证券信息”的关联界面（portRelaChargingSec）还没有拆出来，查询会报错，因此暂时先把注释掉
            ////loadPortRelaInfo(sysFuns);

            return sysFuns;
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

        /////// <summary>
        /////// list界面加载A区数据，子类重写
        /////// </summary>
        /////// <author>wuwenlan 2011.03.2</author>
        /////// <returns>返回查询条件.</returns>
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
            ////1 查询条件    按照证券品种表中证券属性值查询
            string cond = ""; // " and b.C_DA_CODE like 'GP%'";
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
            quyStrUtil.addQuyCon("C_DA_CODE", "C_DA_CODE", "GP", ClsConstant.SQL_RA_HYPHEN_LIKE, ClsConstant._LIKE_MARCH_LEFT);
            quyStrUtil.addQuyCon("C_DA_CODE", "C_DA_CODE", "JJ", ClsConstant.SQL_RA_HYPHEN_LIKE, ClsConstant._LIKE_MARCH_LEFT + ClsConstant._HYPHEN_OR);

            ////查询条件按照：上市代码、股票名称、证券品种代码、市场代码、市场类型查询
            if (this.txtSecMktCode.Text.Trim().Length != 0)
            {
                ////cond = " and a.C_SEC_MKT_CODE like '" + this.txtSecMktCode.Text.Trim() + "%'";
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
                ////cond += " and a.C_SEC_NAME like '" + this.txtSecName.Text.Trim() + "%'";
                quyStrUtil.addQuyCon("C_SEC_NAME", this.txtSecName.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            if (this.cboSecVar.Value != null)
            {
                ////cond += " and a.C_SEC_VAR_CODE like  '" + this.cboSecVar.Value + "%'";
                quyStrUtil.addQuyCon("C_SEC_VAR_CODE", this.cboSecVar.Value, ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            string search = this.yssBuildLeftCheckRowsStr("exchange"); // tanwenjie 2011.7.28 获取A区选中的行
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

            ////查询条件按照：上市代码、股票名称、证券品种代码、市场代码、市场类型查询
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

        /// <summary>
        /// 快捷区单击事件 wuwenlan
        /// 主要为了控制点击根节点时，新增按钮不可以用
        /// 修改人：(弃用此方法)liuliang 20120423 不在通过A区控制B区的按钮状态
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
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
        ////        ////YssMessageBox.ShowDyanInformation("快捷区单击选中数据出现异常", ex.Message, "信息提示", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
        ////        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getAareaSelLoadErr(_formFun.C_FUN_CODE));
                
        ////    }
        ////}

        /// <summary>
        /// 点击行的时候控制新增按钮是否可用.
        /// wuwenlan 20110302.
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        private void Frm_SEC_BASE_GP_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            dataService = (IServiceBus)ServiceFactory.createService(serviceType);

            try
            {
                //// 注释 下行 by weijj bug82786 T1.股票基本信息：打开LIST界面点击查询，默认只查询已审核数据
                ////btnBar.setButtonChecked(ClsButtonName.BtnSearchAudit);
            }
            catch (Exception ex)
            {
                ClsBaseException.DiscardException(ex);
                ////YssMessageBox.ShowDyanInformation("【" + this.Text + "】窗体加载出现异常", ex.Message, "信息提示", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFormLoadErr(this.Text));
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


