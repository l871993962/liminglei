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
////using YssData.Service.Mp;
////using FAST.Common.Service.DataService;
using YssInformation.Support.Fun;
using YssInformation.Support.Context;
using YssInformation.Support.Bi.Market.Pojo;
using YssSecInformation.Support.Mp.SecMktMap.Service;
using YssInformation.Support.Bi.Market.Service;
////using YssPara.Pojo.Bi;
////using YssBaseCls.Context;
////using YssInformation.Bi.Market.Pojo;





////using YssPara.Pojo.Bi;
////using YssPojos.Para.Bi;

////namespace YssData.Form.Mp
namespace YssSecInformation.Mp.SecMktMap.Form
{
    /// <summary>
    /// FrmSecMKTPriceList 的摘要说明。
    /// 作用：本类是为了实现证券行情数据的加载和浏览
    ///  
    ///  作者：chenyoulong
    ///  
    ///  版本：v4.5.0.1
    ///  
    ///  添加内容：窗体绘制，功能方法实现
    ///  
    ///  添加日期：2010.12.17
    ///  
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：lyh
    /// 修改日期：2011.01.30
    /// 修改简介：加载a区市场信息
    /// 
    /// 当前版本：V4.5.0.3
    /// 修改人：zhuangyuchen
    /// 修改日期：2011-2-11
    /// 修改简介：第二轮需求翻新
    ///
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：lyh
    /// 修改日期：2011.02.12
    /// 修改简介：生成自定义表头
    /// 
    /// ///   －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：zhuangyuchen
    /// 修改日期： 2011.02.21
    /// 修改简介：  
    /// 1：增加传到后台去的列头和窗体菜单
    /// 2：增加不同类的标识和分类传到后台去
    /// 3：出错提示信息的修改 
    /// 4： 删除以前的旧代码
    /// 5：修改POJO类为公共类
    /// 6：修改了由于POJO类更改后的属性
    /// 7:增加点击左侧数据区的记录时候，获取其中的数据库的分类标志，传到后台
    /// 8:证券品种表在控件中配置，初始化的时候删除
    /// 9:修改加载显示左侧数据区的代码
    /// 
    ///   /// ///   －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：zhuangyuchen
    /// 修改日期： 2011.02.25
    /// 修改简介：  
    /// 修改基类更改后的代码
    /// 1：包名，类名修改
    /// 2：重写查询方法，去掉旧的方法
    /// 
    ///  ///   /// ///   －－－－修改记录－－－－
    /// 当前版本：V4.5.0.
    /// 修改人：zhuangyuchen
    /// 修改日期： 2011.03-2
    /// 修改简介：  
    /// 修改基类更改后的代码
    ///加载A区数据，根据A区选中的值，显示数据
    /// 
    /// </summary>
    public partial class Frm_SEC_MKT_ZQ_L : FrmBaseList
    {
        /// <summary>
        /// 增加一个标志属性传到set窗体，根据这个标志，确定set窗体ADD按钮是否可用
        /// </summary>
        private bool flag = true;

        
        /// <summary>
        /// 将左边区域选中的市场代码传到set窗体，增加证券市场信息的时候的根据市场类型来增加
        /// </summary>
        private string my_martCode = "";

        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private ISecMktService marketValueService = null;
        
        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_SEC_MKT_ZQ_L()
        {
            this.bUseMVCService = true;
            InitializeComponent();
            //// add by weijj BUG #82067 只查询1年的数据
            this.dtpMKTPriceDate.FirstdateTimeInputValueChanged += new EventHandler(dtpMktDate_FirstdateTimeInputValueChanged);
            this.dtpMKTPriceDate.SeconddateTimeInputValueChanged += new EventHandler(dtpMktDate_SeconddateTimeInputValueChanged);
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
        /// 增加一个标志属性传到set窗体，根据这个标志，确定set窗体ADD按钮是否可用
        /// </summary>
        public bool Flag
        {
            get { return flag; }
            set { flag = value; }
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
        /// 初始化查询模块控件
        /// </summary>
        public override void yssInitCtlAttr()
        {
            ////this.dtpMKTPriceDate.yssInitDateTime = Convert.ToDateTime(DateTime.Now);
        }

        /// <summary>
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写
        /// </summary>
        /// <returns>初始化查询条件</returns>
        public override string yssInitQuery()
        {
            // 所有提供的参数项如下，只需要设置子类需要的项即可
            // 1 查询条件
            string cond = ""; // " and a.C_MKT_CLS like 'IN'";  // 查询分类为汇率的数据

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
            quyStrUtil.addQuyCon("C_MKT_CLS", "IN", "=");
            ////if (this.tbLeftMain.SelectedRow != null)
            ////{
            ////    // 如果选中的是跟节点，就传市场类型 // zhuangyuchen  2011-3-3
            ////    if (((I_BaseMkt)tbLeftMain.SelectedRow.Tag).MKT_CODE_P.Equals("[root]"))
            ////    {
            ////        cond += "and b.C_DV_MKT_TYPE = '" + ((I_BaseMkt)tbLeftMain.SelectedRow.Tag).C_DV_MKT_TYPE + "'";

            ////    }
            ////    else 
            ////    {
            ////        cond += "and b.C_MKT_CODE = '" + ((I_BaseMkt)tbLeftMain.SelectedRow.Tag).C_MKT_CODE + "'";   // 如果是子节点，就传组合代码
            ////    }

            ////}

            string search = this.yssBuildLeftCheckRowsStr("exchange");  // tanwenjie 2011.7.28 获取A区选中的行
            ////cond += "and b.c_mkt_code in (" + search + ")";
            quyStrUtil.addQuyCon("C_MKT_CODE", "C_MKT_CODE", search, "IN");

            if (this.selSecurity.Value != null && this.selSecurity.Value.Trim().Length > 0)
            {
                ////cond += " and a.C_SEC_CODE = '" + this.selSecurity.Value + "'";  // 交易证券
                quyStrUtil.addQuyCon("C_SEC_CODE", this.selSecurity.Value, "=");
            }

            ////cond += " and a.D_MKT between " + "to_date( '" + this.dtpMKTPriceDate.getBeginDate.ToString("yyyy-MM-dd").Trim() + "','yyyy-MM-dd')" + " and " + "to_date('" + this.dtpMKTPriceDate.getEndDate.Date.ToString("yyyy-MM-dd").Trim() + "','yyyy-MM-dd')";  // 行情日期
            quyStrUtil.addQuyCon("dExr", "D_MKT", dtpMKTPriceDate.getBeginDate.ToString("yyyy-MM-dd").Trim() + "," + dtpMKTPriceDate.getEndDate.Date.ToString("yyyy-MM-dd").Trim(), "BETWEEN");
            cond = quyStrUtil.getQuyStr("exchange");
            return cond;
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
        ////private void Frm_SEC_MKT_ZQ_L_Load(object sender, EventArgs e)
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
        ////         YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500036", _formFun, status));
        ////    }

        ////}

     
        /// <summary>
        /// 新增之前的事件，得到左边数据区的市场代码，传到set窗体，新增的时候，在该市场代码下增加
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        private void Frm_SEC_MKT_ZQ_L_YssOnBeforeNewClick(object sender, YssBeforeOperEventArgs e)
        {
            ClsInterface inter = new ClsInterface();
            try
            {
                // 如果不选择左边的数据区域，直接点击查询，就跳出来
                if (this.tbLeftMain.SelectedRow != null)  
                {
                    foreach (Yss.KTable.Models.Row c in this.tbLeftMain.Rows)
                    {
                        this.C_MARKET_CODE = ((MktExtend)inter.getSelectRow(c)).C_MKT_CODE;  // 得到选中的组合对象
                    }
                }
            }
            catch (Exception ex)
            {
                ////YssMessageBox.ShowDyanInformation(ex.Message, ex.StackTrace, "新增操作开始之前发生错误", MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500061", _formFun, status));
                ClsBaseException.DiscardException(ex);
            }

        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_MKT_ZQ_L_Load(object sender, EventArgs e)
        {
            this.getServiceInstance(); 
        }

        /// <summary>
        /// 获取服务对象
        /// </summary>
        private void getServiceInstance()
        {
            if (null == this.dataService)
            {
                Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
                this.marketValueService = ServiceFactory.createService(serviceType) as ISecMktService;
                this.dataService = this.marketValueService;
            }
        }

        /// <summary>
        /// 封装前台条件对象
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>paraDict</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            paraDict.Add("C_MKT_CLS", "IN");

            string search = this.yssBuildLeftCheckRowsStr("exchange");
            search = search.Replace("'", "");
            paraDict.Add("ARRAY_C_MKT_CODE", search);

            if (this.selSecurity.Value != null && this.selSecurity.Value.Trim().Length > 0)
            {
                paraDict.Add("ARRAY_C_SEC_CODE", this.selSecurity.Value.Replace("|", ","));
            }

            if (this.cboHqzt.Value != null && this.cboHqzt.Value.Trim().Length > 0)
            {
                paraDict.Add("ARRAY_C_HQZT_CODE", this.cboHqzt.Value.Replace("|", ",")); // ADD BY WZH STORY #32313 停牌股票信息生成做成公共层面，不关联组合
            }

            paraDict.Add("D_BEGIN", dtpMKTPriceDate.getBeginDate.ToString("yyyy-MM-dd").Trim());
            paraDict.Add("D_END", dtpMKTPriceDate.getEndDate.Date.ToString("yyyy-MM-dd").Trim());

            return paraDict;
        }

        /// <summary>
        /// 设置
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void dtpMktDate_FirstdateTimeInputValueChanged(object sender, EventArgs e)
        {
            DateTime date = this.dtpMKTPriceDate.getBeginDate.AddYears(1);
            if (DateTime.Compare(dtpMKTPriceDate.getEndDate, date) > 0)
            {
                dtpMKTPriceDate.SetDateTimeInput2(date);
            }
        }

        /// <summary>
        /// 设置
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void dtpMktDate_SeconddateTimeInputValueChanged(object sender, EventArgs e)
        {
            DateTime date = this.dtpMKTPriceDate.getEndDate.AddYears(-1);
            if (DateTime.Compare(dtpMKTPriceDate.getBeginDate, date) < 0)
            {
                dtpMKTPriceDate.setDateTime(date);
            }
        }

        /// <summary>
        /// 设置参数
        /// </summary>
        /// <returns>参数集合</returns>
        public override Dictionary<string, string> setExtraCondition()
        {
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("C_MKT_CLS", "IN");           
            return paraDict;
        }
    }
}


