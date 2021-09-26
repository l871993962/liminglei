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

using System.Collections;
using Yss.KTable.Models;
using FAST.Core.BaseControl.YssButtonBars;
using FAST.Core.BaseControl.Pojo;
using FAST.Core.BaseControl.YssButtonBars;
using YssInformation.Support.Bi.Market.Service;





namespace YssSecInformation.Sv.Form
{
    ///<summary>
    /// 功能简介：期权品种信息浏览界面，负责期权品种信息的显示和查询功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.30
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：chenyoulong
    /// 修改日期：20110106
    /// 修改简介：方法的具体实现
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：lyh
    /// 修改日期：20110130
    /// 修改简介：加载a区市场信息
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：yhm
    /// 修改日期：2011.02.26
    /// 修改简介：修改为新的代码结构
    ///－－－－修改记录－－－－
    /// 当前版本：V4.5.0.5
    /// 修改人：chenyoulong
    /// 修改日期：20110302
    /// 修改简介：
    /// 1、代码注释完善（包括方法作用注释，逻辑注释，类修改注释）
    /// 2、提示信息统一调整(前台用五个参数YssMessageBox.ShowDyanInformation("初始化窗体出错", ye.Message, "错误信息", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail)）
    /// 3、需求中内容没有实现功能的调整 
    /// 4、删除废弃代码
    /// 5、修改属性字段
    /// </summary>
    public partial class Frm_SEC_BASE_QQ_L : FrmBaseList
    {
        /// <summary>
        /// dataService
        /// </summary>
        private IMktDataService mktDataService = null;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_SEC_BASE_QQ_L()
        {
            InitializeComponent();
            bUseMVCService = true;
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
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写.
        /// </summary>
        /// <returns>返回查询tiaojian.</returns>
        public override string yssInitQuery()
        {
            ////所有提供的参数项如下，只需要设置子类需要的项即可

            ////1 查询条件
            string cond = ""; // " and b.C_DA_CODE like 'QQ%'";
            ////3 初始只加载列头，若需要则设为true，反之不需要设置此参数
            IsOnlyHeder = true;

            return cond;
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


        /////// <summary>
        /////// 获取list查询条件区的查询条件.
        /////// </summary>
        /////// <returns>返回查询条件.</returns>
        ////public string yssGetListCond()
        ////{
        ////    string cond = "";
        ////    ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
        ////    quyStrUtil.addQuyCon("C_DA_CODE", "C_DA_CODE", "QQ", ClsConstant.SQL_RA_HYPHEN_LIKE, ClsConstant._LIKE_MARCH_LEFT);
        ////    ////封装查询条件----期权代码
        ////    if (this.txtOptionCode.Text.Trim().Length != 0)
        ////    {
        ////        quyStrUtil.addQuyCon("C_SEC_MKT_CODE", this.txtOptionCode.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
        ////    }

        ////    //// CL 20121120 STORY #3305 证券基本信息模块下的证券基本信息上市代码查询条件补充调整
        ////    if (this.txtOptionCode.Text.Trim().Length != 0)
        ////    {
        ////        quyStrUtil.addQuyCon("C_SEC_CODE", this.txtOptionCode.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
        ////    }

        ////    if (this.txtOptionCode.Text.Trim().Length != 0)
        ////    {
        ////        quyStrUtil.addQuyCon("C_SEC_ISIN_CODE", this.txtOptionCode.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
        ////    }

        ////    ////封装查询条件----期权名称
        ////    if (this.txtOptionName.Text.Trim().Length != 0)
        ////    {
        ////        quyStrUtil.addQuyCon("C_SEC_NAME", this.txtOptionName.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
        ////    }

        ////    ////封装查询条件----期权品种
        ////    if (this.cboSecCategory.Value != null)
        ////    {
        ////        quyStrUtil.addQuyCon("C_SEC_VAR_CODE", cboSecCategory.Value, ClsConstant.SQL_RA_HYPHEN_LIKE);
        ////    }

        ////    string search = this.yssBuildLeftCheckRowsStr("exchange");  
        ////    quyStrUtil.addQuyCon("portCode", "C_MKT_CODE", search, "IN");
        ////    cond = quyStrUtil.getQuyStr("exchange");
        ////    return cond;
        ////}

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

            ////封装查询条件----期权代码
            if (this.txtOptionCode.Text.Trim().Length != 0)
            {
                paraDict.Add("C_SEC_MKT_CODE", "%" + this.txtOptionCode.Text.Trim() + "%");
            }

            //// CL 20121120 STORY #3305 证券基本信息模块下的证券基本信息上市代码查询条件补充调整
            if (this.txtOptionCode.Text.Trim().Length != 0)
            {
                paraDict.Add("C_SEC_CODE", "%" + this.txtOptionCode.Text.Trim() + "%");
            }

            if (this.txtOptionCode.Text.Trim().Length != 0)
            {
                paraDict.Add("C_SEC_ISIN_CODE", "%" + this.txtOptionCode.Text.Trim() + "%");
            }

            ////封装查询条件----期权名称
            if (this.txtOptionName.Text.Trim().Length != 0)
            {
                paraDict.Add("C_SEC_NAME", "%" + this.txtOptionName.Text.Trim() + "%");
            }

            ////封装查询条件----期权品种
            if (this.cboSecCategory.Value != null)
            {
                paraDict.Add("C_SEC_VAR_CODE", "%" + cboSecCategory.Value.Trim() + "%");
            }

            return paraDict;
        }

        /////// <summary>
        /////// list界面加载A区数据，子类重写.
        /////// </summary>
        /////// <author>chenyoulong 2011.03.2.</author>
        /////// <returns>返回a区数据.</returns>
        ////public override string yssGetLeftData()
        ////{   
        ////    ////edit by yh 2011.03.09修改数据来源为枚举类型,防止赋值出错
        ////    ClsEnums.DataSrc dataSrc = ClsEnums.DataSrc.SrcCache; // 数据来源是缓存
        ////    string funCode = "exchange";    // 交易市场
        ////    string headKeys = "C_MKT_NAME~!~C_MKT_CODE";   // 自定义列头

        ////    this.matchSearchStr = new string[2] { "C_MKT_NAME", "C_MKT_CODE" };  // 【搜索】功能匹配的属性

        ////    string result = null;
        ////    ////设定左侧数据的加载方式
        ////    YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;
        ////    ////调用由子类提供参数的查询方法
        ////    result = this.yssGetLeftData(dataSrc, funCode, null, headKeys, null);
        ////    return result;
        ////}

        /// <summary>
        /// 窗体load事件中控制新增按钮不可用.
        /// chenyoulong 20110302.
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        private void Frm_SEC_BASE_QQ_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            dataService = (IServiceBus)ServiceFactory.createService(serviceType);
        }


        /// <summary>
        /// 重写新增方法
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
        /// 保证金调整单击事件
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
                    if (btnSub.Text.Equals("保证金调整"))
                    {
                        if (this.tbMain.SelectedRows.Count >= 1)
                        {
                            Frm_SEC_BASE_QQBZJ_S bzj = new Frm_SEC_BASE_QQBZJ_S();
                            bzj.yssShowForm(this);
                        }

                    }
                    else
                    {
                        base.btnNew_Click(sender, e);
                    }

                }
                catch (System.Exception ex)
                {
                    throw new ClsBaseException(ex.Message);
                }
            }
        }

        /// <summary>
        /// 添加新增按钮的子按钮
        /// </summary>
        private void addSubButtons()
        {
            ////STORY #29071 【南方资本】理财业务-开放申赎业务点“新增”要先加载倒三角下的内容选择后才可以新增。
            ////张绍林-20160229
            ClsSubButtonInfo btnQiQuanPiZhong = new ClsSubButtonInfo();
            btnQiQuanPiZhong.Text = this.Text;
            btnQiQuanPiZhong.ClickEvent += new EventHandler(this.btnNewSub_Click);
            this.btnBar.addSubButton(ClsButtonName.BtnNew, btnQiQuanPiZhong);

            ClsSubButtonInfo btnTmp = new ClsSubButtonInfo();
            btnTmp.Text = "保证金调整";
            btnTmp.ClickEvent += new EventHandler(this.btnNewSub_Click);
            this.btnBar.addSubButton(ClsButtonName.BtnNew, btnTmp);
            btnBar.Refresh();
        }

    }
}


