using System;
using System.Collections.Generic;
using FAST.Common.Service.Pojo;
using FAST.Core.BaseControl.Fun;
using FAST.Core.Communication.Service;
using FAST.Core.Context;
using FAST.Core.CRUD.Form;
using FAST.Core.Util;
////using YssBaseCls.Fun;
using YssInformation.Support.Bi.Region.Service;
using YssInformation.Support.Bi.Market.Service;
using YssInformation.Support.Fun;

namespace YssInformation.Bi.Market.Form
{
    ///<summary>
    /// FrmExchangeList 的摘要说明。
    /// 作用：交易所信息list类，负责交易所信息的显示和操作
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.02
    ///
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：yh
    /// 修改日期： 2010.12.02
    /// 修改简介： 实现方法
    /// 
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：lyh
    /// 修改日期： 2011.02.11
    /// 修改简介： 实现表头自定义
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：zhuangyuchen
    /// 修改日期： 2011.02.12
    /// 修改简介：
    /// 1：增加传到set窗体的市场类型的属性
    /// 2：根据市场类型进行窗体按钮的控制
    /// 3：增加市场名称的查询条件
    /// 4：增加传到后台去的列头和窗体菜单
    /// 5：出错提示信息的修改
    /// 6：修改由于POJO类更改后的属性
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.5
    /// 修改人：lyh
    /// 修改日期： 2011.02.26
    /// 修改简介：调整代码新结构
    ///</summary>
    public partial class Frm_MKT_L : FrmBaseList
    {
        /// <summary>
        /// 本模块所需的功能按钮的声明
        /// </summary>
        private List<string> btnLis = null;

        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private IMktService mktService = null;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_MKT_L()
        {
            this.bUseMVCService = true;
            InitializeComponent();
            //// zhuangyuchen 2011-2-15  B区显示结构设为树形结构
            ////YssMainKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;
            this.ShowRowCheckBoxColumn = true;
            this.ShowRowIndexColumn = true;
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
        /// <returns>返回查询条件.</returns>
        public override string yssInitQuery()
        {
            //// 所有提供的参数项如下，只需要设置子类需要的项即可
            //// 1 查询条件
            string cond = "";

            //// 3 初始只加载列头，若需要则设为true，反之不需要设置此参数
            IsOnlyHeder = true;

            return cond;
        }

        /// <summary>
        /// 获取list查询条件区的查询条件.
        /// 修改人  庄宇臣
        /// 修改时间 2011-4-13
        /// bug单号：1665
        /// 后台查询字符串更改过了，更改前台查询条件
        /// </summary>
        /// <returns>返回查询条件.</returns>
        public string yssGetListCond()
        {
            string cond = "";
            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
            //// 判断用户是否输入市场代码作为筛选条件
            if (this.txtExchange.Text.Trim().Length != 0)
            {
                ////cond = cond + " and c.C_MKT_CODE = '" + this.txtExchange.Text + "'";
                quyStrUtil.addQuyCon("C_MKT_CODE", this.txtExchange.Text, "=");
            }

            if (this.txtExchangeName.Text != null && this.txtExchangeName.Text.Trim().Length != 0)
            {
                ////cond = cond + " and c.C_MKT_NAME like '%" + this.txtExchangeName.Text + "%'";
                quyStrUtil.addQuyCon("C_MKT_NAME", this.txtExchangeName.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            //////// 判断用户是否输入结算日期作为筛选条件
            ////if (this.cboArea.Value != null && this.cboArea.Value.Trim().Length != 0)
            ////{
            ////    ////cond = cond + " and c.C_ORG_CODE like '%" + this.dtpArea.Value + "%'"; // 结转天数
            ////    quyStrUtil.addQuyCon("C_AREA_CODE", this.cboArea.Value, ClsConstant.SQL_RA_HYPHEN_LIKE);
            ////}

            ////if (tbLeftMain.SelectedRow != null)
            ////{
            ////    cond = cond + " and c.C_DV_MKT_TYPE ='" + ((Vocabulary)tbLeftMain.SelectedRow.Tag).C_DV_CODE + "'";
            ////}

            string search = this.yssBuildLeftCheckRowsStr("pubvocabulary");  // tanwenjie 2011.7.28 获取A区选中的行
            ////cond += "and c.C_DV_MKT_TYPE in (" + search + ")";
            quyStrUtil.addQuyCon("C_DV_MKT_TYPE", "C_DV_MKT_TYPE", search, "IN");
            cond = quyStrUtil.getQuyStr(this._formFun.C_FUN_CODE);
            return cond;            
        }

        /// <summary>
        /// 封装条件对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            if (this.txtExchange.Text.Trim().Length != 0)
            {
                paraDict.Add("C_MKT_NO", "%" + this.txtExchange.Text.Trim() + "%");
            }

            if (this.txtExchangeName.Text.Trim().Length != 0)
            {
                paraDict.Add("C_MKT_NAME", "%" + this.txtExchangeName.Text.Trim() + "%");
            }

            //////// 判断用户是否输入结算日期作为筛选条件
            ////if (this.cboArea.Value != null && this.cboArea.Value.Trim().Length != 0)
            ////{
            ////    paraDict.Add("C_AREA_CODE", "%" + this.cboArea.Value + "%");
            ////}

            string search = this.yssBuildLeftCheckRowsStr("base_area");
            search = search.Replace("'", "");
            ////paraDict.Add("ARRAY_C_DV_MKT_TYPE", search);
            paraDict.Add("ARRAY_C_AREA_CODE", search);
            return paraDict;
        }

        /// <summary>
        /// 初始化控件.
        /// </summary>
        ////public override void yssInitCtlAttr()
        ////{
        ////    try
        ////    {
        ////        //// 判断是否是闰年 
        ////        if (DateTime.IsLeapYear(Convert.ToInt32(DateTime.Now.Year.ToString())))
        ////        {
        ////            //// 如果是闰年 设定结转天数最大值为366
        ////            ////this.iniSettleDays.MaxValue = 366;
        ////        }
        ////        else
        ////        {
        ////            //// 如果不是闰年 设定结转天数最大值为365
        ////            ////this.iniSettleDays.MaxValue = 365;
        ////        }

        ////    }
        ////    catch (ClsBaseException ce)
        ////    {
        ////        YssMessageBox.ShowDyanInformation("判断是否闰年信息出错！", ce.Message, "信息提示", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
        ////    }

        ////}

        /////// <summary>
        /////// list界面加载A区数据，子类重写.
        /////// </summary>
        /////// <author>lyh 2011.03.02.</author>
        /////// <returns>the result.</returns>
        ////public override string yssGetLeftData()
        ////{
        ////    string result = null;
        ////    try
        ////    {
        ////        //////// edit by yh 2011.03.09修改数据来源为枚举类型,防止赋值出错
        ////        ////ClsEnums.DataSrc dataSrc = ClsEnums.DataSrc.SrcDB; // 数据来源是缓存
        ////        ////string funCode = "pubvocabulary"; // 组合名称
        ////        ////string headKeys = "C_DV_NAME"; // 自定义列头
        ////        ////string cond = "MKT_TYPE"; // 查询条件,此时为词汇类型代码
        ////        ////ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
        ////        ////quyStrUtil.addQuyCon("C_DV_TYPE", "MKT_TYPE", ClsConstant.SQL_RA_HYPHEN_EQUAL);
        ////        ////cond = quyStrUtil.getQuyStr();
        ////        //////// 获取数据类型
        ////        ////string cacheType = "CacheType";
        ////        ////this.matchSearchStr = new string[1] { "C_DV_NAME" }; // 【搜索】功能匹配的属性

        ////        //// edit by yh 2011.03.09修改数据来源为枚举类型,防止赋值出错
        ////        ClsEnums.DataSrc dataSrc = ClsEnums.DataSrc.SrcDB; // 数据来源是缓存
        ////        string funCode = "area"; // 组合名称
        ////        string headKeys = "C_AREA_NAME~!~C_AREA_CODE"; // 自定义列头
        ////        string cond = "all,N_CHECK_STATE =1 "; // 查询条件,此时为词汇类型代码
        ////        ////ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
        ////        ////////quyStrUtil.addQuyCon("C_DV_TYPE", "MKT_TYPE", ClsConstant.SQL_RA_HYPHEN_EQUAL);
        ////        ////cond = quyStrUtil.getQuyStr();
        ////        //// 获取数据类型
              
        ////        this.matchSearchStr = new string[1] { "C_AREA_NAME" }; // 【搜索】功能匹配的属性
                

        ////        //// 设定左侧数据的加载方式
        ////        YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;
        ////        //// 调用由子类提供参数的查询方法
        ////        result = this.yssGetLeftData(dataSrc, funCode, cond, headKeys, "");
        ////    }
        ////    catch (ClsBaseException ce)
        ////    {
        ////        ClsBaseException.DiscardException(ce);
        ////       //// YssMessageBox.ShowDyanInformation("加载市场类型数据出错", ce.Message, "信息提示", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
        ////        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-110018", _formFun, status));
        ////    }

        ////    return result;
        ////}

        /// <summary>
        /// 获取列头对象列表
        /// </summary>
        /// <returns>A区列头集合</returns>
        private List<ListHeadInfo> getLeftTableListHead()
        {
            List<ListHeadInfo> listHeadList = new List<ListHeadInfo>();

            ListHeadInfo listhead1 = new ListHeadInfo();
            listhead1.Key = "C_AREA_CODE";
            listhead1.Text = "C_AREA_CODE";
            listhead1.Align = "LEFT";
            listhead1.Format = "";
            listhead1.ShowConvert = "";

            ListHeadInfo listhead2 = new ListHeadInfo();
            listhead2.Key = "C_AREA_NAME";
            listhead2.Text = "C_AREA_NAME";
            listhead2.Align = "LEFT";
            listhead2.Format = "";
            listhead2.ShowConvert = "";

            listHeadList.Add(listhead2);
            listHeadList.Add(listhead1);           

            return listHeadList;
        }

        /// <summary>
        /// 加载A区数据
        /// </summary>
        public override void yssLoadLeftData()
        {
            IAreaService areaService = ServiceFactory.createService<IAreaService>();
            QueryRes res = areaService.getAllAreasByType();
            res.ListHeadList = getLeftTableListHead();
            leftDataFunCode = "base_area";
            TableListLoader tableLoader = new TableListLoader();
            this.YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;
            tableLoader.loadTable(tbLeftMain, res, false, false, ClsEnums.KTableDataShowMode.TreeMode);
            this.matchSearchStr = new string[1] { "C_AREA_NAME" }; // 【搜索】功能匹配的属性
        }

        /// <summary>
        /// 点击左侧数据区的时候控制按钮.
        /// zhuangyuchen  2011-3-2.
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        ////private void tbLeftMain_RowClicked_1(object sender, Yss.KTable.Events.RowEventArgs e)
        ////{
        ////    try
        ////    {
        ////        if (this.tbLeftMain.SelectedRow != null)
        ////        {
        ////            Vocabulary clsPubVocabulary = (Vocabulary)tbLeftMain.SelectedRow.Tag;
        ////            if (clsPubVocabulary.C_DV_CODE.Equals("FTM"))
        ////            {
        ////                //// 如果是二级市场，新增，复制，删除不可用
        ////                btnBar.setButtonEnabled(ClsButtonName.BtnNew, false);
        ////                btnBar.setButtonEnabled(ClsButtonName.BtnDelete, false);
        ////                btnBar.setButtonEnabled(ClsButtonName.BtnEdit, false);
        ////                ////this.btnNew.Enabled = false;
        ////                ////this.btnDelete.Enabled = false;
        ////                ////this.btnEdit.Enabled = false;
        ////            }
        ////            else
        ////            {
        ////                ////this.btnNew.Enabled = true;
        ////                btnBar.setButtonEnabled(ClsButtonName.BtnNew, false);
        ////            }

        ////        }
        ////    }
        ////    catch (Exception ex)
        ////    {
        ////        ////  YssMessageBox.ShowDyanInformation(ex.Message, ex.StackTrace, "点击行事件操作发生错误", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
        ////        YssMessageBox.ShowCommonInfo(_formFun.C_FUN_CODE);
        ////    }
        ////}

        /////// <summary>
        /////// 不能删除机构为中国外汇交易中心交易市场信息.
        /////// 添加：liuping.
        /////// 添加时间：2011-03-18.
        /////// </summary>
        ////private void cheekOrgCode()
        ////{
        ////    if (this.tbMain.SelectedRow != null)
        ////    {
        ////        if (this.bUseMVCService = true)
        ////        {
        ////            MktExtend mkt = (MktExtend)this.tbMain.SelectedRow.Tag;
        ////            if (mkt.C_ORG_CODE != null && mkt.C_ORG_CODE.Length > 0 && mkt.C_ORG_CODE == "CFETC")
        ////            {
        ////                ////throw new ClsBaseException("不能删除机构为中国外汇交易中心交易市场信息");
        ////                throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("001", _formFun, ClsEnums.StatusSetting.YssSave));
        ////            }
        ////        }
        ////        else
        ////        {
        ////            Mkt mkt = (Mkt)this.tbMain.SelectedRow.Tag;
        ////            if (mkt.C_ORG_CODE != null && mkt.C_ORG_CODE.Length > 0 && mkt.C_ORG_CODE == "CFETC")
        ////            {
        ////                ////throw new ClsBaseException("不能删除机构为中国外汇交易中心交易市场信息");
        ////                throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("001", _formFun, ClsEnums.StatusSetting.YssSave));
        ////            }
        ////        }

        ////    }
        ////}

        /////// <summary>
        /////// 删除数据前验证数据是否正确.
        /////// 添加人：liuping.
        /////// 添加时间：2011-03-18.
        /////// </summary>
        /////// <param name="sender">sender.</param>
        /////// <param name="e">e.</param>
        ////private void Frm_MKT_L_YssOnBeforeDelClick(object sender, YssBeforeOperEventArgs e)
        ////{
        ////    e.IsCancel = true; // 屏蔽删除数据的方法
        ////    ////cheekOrgCode(); // liuping  2011-03-18   不能删除机构为中国外汇交易中心交易市场信息
        ////    e.IsCancel = false; // 不屏蔽删除数据的方法
        ////}

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_MKT_L_Load(object sender, EventArgs e)
        {
            getServiceInstance();

            // 声明本模块的所需的功能按钮，并将所声明的按钮放入到btnBar中
            ////this.getBtnLis();
            ////btnBar.FunRightList = btnLis;
        }

        /// <summary>
        /// 定义本模块需要使用到的功能按钮
        /// </summary>
        private void getBtnLis()
        {
            btnLis = new List<string>();
            btnLis.Add(ClsButtonName.BtnNew);
            btnLis.Add(ClsButtonName.BtnCopy);
            btnLis.Add(ClsButtonName.BtnDelete);
            btnLis.Add(ClsButtonName.BtnEdit);
            btnLis.Add(ClsButtonName.BtnAudit);
            btnLis.Add(ClsButtonName.BtnUnAudit);
            btnLis.Add(ClsButtonName.ToolExport);
            btnLis.Add(ClsButtonName.ToolPreview);
            btnLis.Add(ClsButtonName.BtnFullScreen);
            btnLis.Add(ClsButtonName.BtnHelp);
            btnLis.Add(ClsButtonName.BtnRefresh);
        }

        /// <summary>
        /// 获取数据服务对象实体
        /// </summary>
        private void getServiceInstance() 
        {
            if (mktService == null) 
            {
                Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
                this.mktService = ServiceFactory.createService(serviceType) as IMktService;
                this.dataService = this.mktService;
            }
        }

        /// <summary>
        /// 重写基类查询方法
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <param name="page">page</param>
        /// <returns>结果对象</returns>
        protected override QueryRes getQueryResultMVC(Dictionary<string, string> paraDict, PageInation page)
        {
            QueryRes queryRes = new QueryRes();
            getServiceInstance();
            queryRes = this.mktService.selectByConditionExtend(paraDict, page, _formFun.C_FUN_CODE);
            return queryRes;
        }


        /// <summary>
        /// list窗体加载是的事件
        /// 将新增按钮设为false
        /// zhaungyuchen 2011-4-13
        /// bug单号 1665
        /// 根据需求，按钮有左边市场代码控制，初始的时候不可用
        /// 修改人：liuliang 20120424  不通过A区控制B区的按钮状态
        /// </summary>
        /// <param name="sender">事件对象</param>
        /// <param name="e">事件类型</param>
        ////private void Frm_MKT_L_Load(object sender, EventArgs e)
        ////{
        ////    ////this.btnNew.Enabled = false;
        ////    btnBar.setButtonEnabled(ClsButtonName.BtnNew, false);
        ////}

        /////// <summary>
        /////// 二级市场无法修改，一级市场走基类控制
        /////// </summary>
        /////// <param name="sender">sender</param>
        /////// <param name="e">e</param>
        ////private void tbMain_RowClicked_1(object sender, Yss.KTable.Events.RowEventArgs e)
        ////{
        ////    try
        ////    {
        ////        if (this.tbLeftMain.SelectedRow != null)
        ////        {
        ////            Cls_MKT mkt = (Cls_MKT)this.tbMain.SelectedRow.Tag;
        ////            if (mkt.C_DV_MKT_TYPE == "FTM")
        ////            {
        ////                //// 如果是二级市场，新增，复制，删除不可用
        ////                ////this.btnNew.Enabled = false;
        ////                ////this.btnDelete.Enabled = false;

        ////                btnBar.setButtonEnabled(ClsButtonName.BtnNew, false);
        ////                btnBar.setButtonEnabled(ClsButtonName.BtnDelete, false);
        ////            }
        ////            else
        ////            {
        ////                ////this.btnNew.Enabled = true;
        ////                btnBar.setButtonEnabled(ClsButtonName.BtnNew, true);
        ////            }
        ////        }
        ////    }
        ////    catch (Exception ex)
        ////    {
        ////        ////  YssMessageBox.ShowDyanInformation(ex.Message, ex.StackTrace, "点击行事件操作发生错误", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
        ////        YssMessageBox.ShowCommonInfo(_formFun.C_FUN_CODE);
        ////    }
        ////}
    }
}


