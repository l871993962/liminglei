using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Platform.Menu.Pojo;
using FAST.Platform.Logger.Pojo;
using FAST.Platform.Safe.Pojo;
using FAST.Platform.Talk.Pojo;

using FAST.Common.Service.Pojo.Base;
using FAST.Core.Communication.DataService;
using FAST.Common.Service.DataService.Base;
using FAST.Core.Exceptions;

using FAST.Core.Context;
using FAST.Core.Bussiness.Pojo;

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
using System.Text.RegularExpressions;
using System.Collections;
using YssDevComponents.DotNetBar;

using Yss.KRichEx;




using FAST.Core.BaseControl.Fun;
using FAST.Common.Service.DataService;
using YssInformation.Support.Bi.Holidays_A.Service;
using YssInformation.Support.Bi.Holidays.Service;
using YssInformation.Support.Bi.Holidays_A.Pojo;
using Yss.KMessage;





namespace YssInformation.Bi.Holidays_A.Form
{
    /// <summary>
    /// 功能简介：节假日群信息浏览界面，负责节假日群信息的显示和节假日群下属节假日的设置
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.13
    ///
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：lyh
    /// 修改日期：20101214
    /// 修改简介：实现类中的方法
    /// 
    /// /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：lyh
    /// 修改日期：20110126
    /// 修改简介：实现a区数据显示
    /// </summary>
    public partial class Frm_HDAY_L : FrmBaseList
    {
        /// <summary>
        /// 窗体A区数据服务对方
        /// </summary>
        private IHdayGroupService hdayGroupService = null;

        /// <summary>
        /// 数据服务对象
        /// </summary>
        private YssInformation.Support.Bi.Holidays.Service.IHDayDataService svc = null;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_HDAY_L()
        {
            this.hasLeftSetForm = true;
            this.bUseMVCServiceLeft = true;
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
        /// 初始化service
        /// </summary>
        protected override void initServiceMVC()
        {
            base.initServiceMVC();
            this.svc = DataServiceFactory.createService<YssInformation.Support.Bi.Holidays.Service.IHDayDataService>();
            this.hdayGroupService = ServiceFactory.createService<IHdayGroupService>();
            ////zhoushuhang 2017-1-6 BUG #149526 BUG单-主干大版本功能测试第二轮BUG汇总1-估值2
            dataService = ServiceFactory.createService<IHdayService>();
        }

        /// <summary>
        /// 点击生成按钮事件.
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        protected override void btnGernerate_Click(object sender, EventArgs e)
        {
            sSetClassName = "YssInformation.Bi.Holidays.Form.Frm_HDAY_CHILD_S";
            btnNew_Click(sender, e);
            sSetClassName = "YssInformation.Bi.Holidays.Form.Frm_HDAY_CHILD1_S";
        }

        /// <summary>
        /// 控件点击改变之后事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void rbdList_CheckedChanged(object sender, EventArgs e)
        {
            bool bol = this.rbdList.Checked; // 获取当前的选中状态
            ////当前选中的状态为列表展示
            if (bol)
            {
                this.pnlCalendar.Visible = false; // 隐藏视图控件
                this.yearCalendar1.Visible = false; // 隐藏视图控件
                this.ShowPageInation = true;
                ////setPageInationMVC(genePage);
                this.tbMain.Visible = true; // 展示列表界面
                this.proBar.Owner.Refresh();
            }
            else
            {
                this.pnlCalendar.Visible = true; // 显示视图控件
                this.yearCalendar1.Visible = true; // 显示视图控件
                this.tbMain.Visible = false; // 隐藏列表界面
                ////判断当前年份控件有没有输入数据
                if (null == this.cboYear.Value && null == this.tbMain.SelectedRow)
                {
                    this.rbdList.Checked = true;
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("020", _formFun, status));
                    return;
                }

                this.ShowPageInation = false;
                inityearCalendar(); // 更新视图控件
            }
        }

        /// <summary>
        /// 对控件进行赋值
        /// </summary>
        public void inityearCalendar()
        {
            if (this.tbLeftMain.SelectedRow == null)
            {
                new MessageDialog().Show("请先选择节假日信息！", "提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            else {
                string year = ""; // 节假日年份
                try
                {
                    ////判断下拉控件是否有值，如果有值获取对应的年份，否则取选中
                    ////行的年份
                    if (null != this.cboYear.Value)
                    {
                        year = this.cboYear.Value; // 获取下拉控件的值
                    }
                    else
                    {
                        year = (this.tbMain.SelectedRow.Tag as HdayGen).N_YEAR; // 获取list界面选中的年份
                    }

                    if (!"".Equals(year))
                    {
                        this.yearCalendar1.Year = Convert.ToInt32(year);
                    }

                    Dictionary<string, string> paraDict = new Dictionary<string, string>();
                    paraDict.Add("C_HDAY_CODE", ((HdayGroup)this.tbLeftMain.SelectedRow.Tag).C_HDAY_CODE);
                    paraDict.Add("N_YEAR", year);
                    //// liuxiang 2017-1-10 BUG149418节假日群设置 视图模式 需区分已审核和未审核
                    ////List<string> resList = ((IHdayService)this.dataService).getAllHoiday(paraDict);

                    ////if (null == resList || resList.Count == 0)
                    ////{
                    ////    return;
                    ////}

                    ////List<DateTime> dates = new List<DateTime>();
                    ////foreach (string s in resList)
                    ////{
                    ////    dates.Add(Convert.ToDateTime(s));
                    ////}

                    ////yearCalendar1.Values = dates.ToArray();
                    paraDict.Add("dataClass", "HdayGen");
                    if (btnBar.getButtonChecked("btnSearchUnAudit"))
                    {
                        paraDict.Add("N_CHECK_STATE", "SearchUnAudit");
                    }
                    else if (btnBar.getButtonChecked("btnSearchAudit"))
                    {
                        paraDict.Add("N_CHECK_STATE", "SearchAudit");
                    }

                    QueryRes res = getQueryResultMVC(paraDict);
                    if (res != null && res.DataList.Count > 0)
                    {
                        yearCalendar1.ClearBackColor();
                        DateTime[] dates = new DateTime[res.DataList.Count];
                        int index = 0;
                        //// 设置日期背景色,已审核:浅灰色,未审核:黄色
                        foreach (BasePojo basePojo in res.DataList)
                        {
                            HdayGen hday = basePojo as HdayGen;
                            DateTime date = Convert.ToDateTime(hday.D_HDAY);
                            dates[index++] = date;
                            if (hday.AuditState == 1)
                            {
                                yearCalendar1.SetBackColor(date, System.Drawing.Color.LightGray);
                            }
                            else
                            {
                                yearCalendar1.SetBackColor(date, System.Drawing.Color.Yellow);
                            }
                        }

                        yearCalendar1.Values = dates;
                    }
                }
                catch (ClsBaseException ce)
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtError("021", _formFun, status));
                    ClsBaseException.DiscardException(ce);
                }
            }
           
        }

        /// <summary>
        /// 重写查询方法，增加刷新列表视图
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        public override void btnSearch_Click(object sender, EventArgs e) 
        {
            if (this.rbdWiew.Checked)
            {
                ////如果当前没有数据
                if (null == this.cboYear.Value)
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("020", _formFun, status));
                    return;
                }
            }

            base.btnSearch_Click(sender, e);
            if (this.rbdWiew.Checked == true)
            {
                this.inityearCalendar();
            }
        }

        /// <summary>
        /// 封装条件对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            string str = (null == this.tbLeftMain.SelectedRow ? "null" : ((HdayGroup)this.tbLeftMain.SelectedRow.Tag).C_HDAY_CODE);
            paraDict.Add("C_HDAY_CODE", str);
            if (this.cboYear.Value != null && this.cboYear.Value.Trim().Length > 0)
            {
                paraDict.Add("N_YEAR", this.cboYear.Value);
            }

            paraDict["dataClass"] = "HdayGen";
            return paraDict;
        }

        /// <summary>
        /// list界面刷新之后处理事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_HDAY_L_YssOnAfterRefreshListViewMVC(object sender, EventArgs e)
        {
            if (null == this.tbMain.SelectedRow)
            {
                return;
            }

            inityearCalendar(); // 更新视图控件
        }

        /// <summary>
        /// 点击A区之前的操作
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_HDAY_L_YssOnBeforeLeftRowDoubleClick(object sender, YssBeforeOperEventArgs e)
        {
            setSelectRowYear();
        }

        /// <summary>
        /// 选择行
        /// </summary>
        public void setSelectRowYear()
        {
            try
            {   
                ////如果A区没有数据直接返回
                if (null == this.tbLeftMain.SelectedRow)
                {
                    this.cboYear.Items.Clear(); // 清空控件里面的数据
                    return;
                }

               reLoadyear();
               if (this.cboYear.Items.Count > 0)
               {
                   this.cboYear.Value = this.cboYear.Items[0].DisplayName;
               }
               else 
               {
                   this.cboYear.Value = "";
                   this.cboYear.Invalidate();
               }
                
            }
            catch (ClsBaseException ce)
            {
                ClsBaseException.DiscardException(ce);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtError("022", _formFun, status));
            }

        }

        /// <summary>
        /// 加载控件
        /// </summary>
        public void reLoadyear()
        {
            this.cboYear.Items.Clear(); // 清空控件里面的数据
            List<Yss.KRichEx.AutoFilter.Model.KTableEntity> list = new List<Yss.KRichEx.AutoFilter.Model.KTableEntity>();
            Yss.KRichEx.AutoFilter.Model.KTableEntity entity = null;
            List<string> resList = null;
            if (this.tbLeftMain.SelectedRow != null)
            {
                string code = ((HdayGroup)this.tbLeftMain.SelectedRow.Tag).C_HDAY_CODE;
                ////zhoushuhang 2017-1-6 BUG #149526 BUG单-主干大版本功能测试第二轮BUG汇总1-估值2
                IHdayService hdayservice = ServiceFactory.createService<IHdayService>();
                resList = hdayservice.getAllYear(code);
                ////resList = ((IHdayService)this.dataService).getAllYear(code);
            }

            if (null == resList || resList.Count == 0)
            {
                return;
            }

            foreach (string s in resList)
            {
                entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(s, s);
                list.Add(entity);
            }
            ////循环list往控件里面塞数据
            for (int i = 0; i < list.Count; i++)
            {
                this.cboYear.Items.Add((Yss.KRichEx.AutoFilter.Model.KTableEntity)list[i]); // 循环list把对象放到控件中
            }

            list = null;
        }

        /// <summary>
        /// list界面加载A区数据，子类重写.
        /// </summary>
        /// <returns>查询数据对象</returns>
        public override QueryRes yssGetLeftDataMVC()
        {
            this.YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.ListMode;
            leftFormFunCode = "base_holidays_A";
            leftDataFunCode = "base_holidays_A";
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            QueryRes res = hdayGroupService.getTreeViewData(paraDict);
            //// 保证A区列头名称在代码之前
            res.ListHeadList = reversalHeadList(res, "C_HDAY_CODE", "C_HDAY_NAME");
            //// bug 9345 caowei 2013/9/2 A区没有搜索功能
            this.matchSearchStr = new string[2] { "C_HDAY_NAME", "C_HDAY_CODE" };  // 【搜索】功能匹配的属性 
            return res;
        }

        /// <summary>
        /// 加载数据表格内容
        /// bug 81473 column 的dataType 类型错误，By Jinghehe 2013-10-18
        /// </summary>
        /// <param name="res">查询结果对象</param>
        protected override void loadListContentMVC(QueryRes res)
        {
            base.loadListContentMVC(res);
            this.tbMain.Columns["D_WEAK"].DataType = Yss.KTable.Enums.ColumnDataType.String;
        }

        /// <summary>
        /// 控件按钮点击前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboYear_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            e.Cancel = true;
        }


        /// <summary>
        /// A区窗体操作后，根据操作前的选中行号，选中A区对应的行
        /// </summary>
        public override void selectRowAfterLeftSetFormSave()
        {
            ////新增A区数据时，避免A区选空行。
            if (tbLeftMain.Rows.Count > 0)
            {
                if (this.frmLeftViewSet.ModifiedRowIds != null && this.frmLeftViewSet.ModifiedRowIds.Count > 0)
                {
                    string id = this.frmLeftViewSet.ModifiedRowIds[0];
                    Yss.KTable.Models.Row row = this.tbLeftMain.Rows[0];
                    do
                    {
                        if (id.Equals((row.Tag as BasePojo).Id))
                        {
                            row.Selected = true;
                            break;
                        }

                        row = row.NextRow;
                    }
                    while (row != null);
                }

                if (this.tbLeftMain.SelectedRow == null)
                {
                    tbLeftMain.Rows[0].Selected = true;
                }
            }
        }

        #region 旧方法 20130607
        /////// <summary>
        /////// 查询,设置选中行的Year  旧方法替换
        /////// </summary>
        ////public void setSelectRowYear()
        ////{
        ////    ////当还没有数据时 给空NULL枚举避免加载数据 by leeyu 2012-6-19
        ////    cboYear.YssAssociaType = AssociaType.NULL;
        ////    Vocabulary clsSelComboxItem = null;
        ////    List<Yss.KRichEx.AutoFilter.Model.KTableEntity> list = new List<Yss.KRichEx.AutoFilter.Model.KTableEntity>();
        ////    Yss.KRichEx.AutoFilter.Model.KTableEntity entity = null;
        ////    try
        ////    {
        ////        this.cboYear.Items.Clear(); // 清空控件里面的数据
        ////        ////如果A区没有数据直接返回
        ////        if (null == this.tbLeftMain.SelectedRow)
        ////        {
        ////            return;
        ////        }

        ////        ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();

        ////        ////获取A区的节假日群          
        ////        if (this.bUseMVCService == false)
        ////        {
        ////            string str = (null == this.tbLeftMain.SelectedRow ? "null" : ((Cls_HDAY_GROUP)this.tbLeftMain.SelectedRow.Tag).C_HDAY_CODE);
        ////            quyStrUtil.addQuyCon("C_HDAY_CODE", str, ClsConstant.SQL_RA_HYPHEN_EQUAL);
        ////        }
        ////        else
        ////        {
        ////            string str = (null == this.tbLeftMain.SelectedRow ? "null" : ((HdayGroup)this.tbLeftMain.SelectedRow.Tag).C_HDAY_CODE);
        ////            quyStrUtil.addQuyCon("C_HDAY_CODE", str, ClsConstant.SQL_RA_HYPHEN_EQUAL);
        ////        }
        ////        object obj = dataAdmin.GetSpecValue(quyStrUtil.getQuyStr(this._formFun.C_FUN_CODE), "getAllYear"); // 后台查询的数据对象

        ////        Dictionary<string, string> paraDict = new Dictionary<string, string>();
        ////        paraDict.Add("C_HDAY_CODE", ((Cls_HDAY_GROUP)this.tbLeftMain.SelectedRow.Tag).C_HDAY_CODE);




        ////        string[] strData = Regex.Split(obj.ToString(), "\t"); // 获取后台查询数据
        ////        cboYear.YssAssociaType = AssociaType.pubvocabulary;
        ////        foreach (string strdata in strData)
        ////        {
        ////            clsSelComboxItem = new Vocabulary();
        ////            clsSelComboxItem.C_DV_NAME = strdata;
        ////            clsSelComboxItem.C_DV_CODE = strdata;
        ////            entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(clsSelComboxItem);
        ////            list.Add(entity);
        ////        }


        ////        ////循环list往控件里面塞数据
        ////        for (int i = 0; i < list.Count; i++)
        ////        {
        ////            this.cboYear.Items.Add((Yss.KRichEx.AutoFilter.Model.KTableEntity)list[i]); // 循环list把对象放到控件中
        ////        }
        ////        list = null;


        ////        this.cboYear.Value = strData[0];
        ////    }
        ////    catch (ClsBaseException ce)
        ////    {
        ////        ClsBaseException.DiscardException(ce);
        ////        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtError("022", _formFun, status));
        ////    }
        ////}
        /**
        /// <summary>
        /// 窗体加载事件.
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        private void Frm_HDAY_L_Load(object sender, EventArgs e)
        {

            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.dataService = ServiceFactory.createService(serviceType) as IServiceBus;

            ClsAssocia asc = new ClsAssocia();
            asc = ClsClzCfgMgr.getAssociaParam("holidays_A");
            Type typeA = ReflectBase.YssGetType(asc.ServiceDllName, asc.ServiceName);
            this.hdayGroupService = ServiceFactory.createService(typeA) as IHdayGroupService;

            tbMain.RowClicked += new Yss.KTable.Events.RowClick(tbMain_RowClicked);
        }
         /// <summary>
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写.
        /// </summary>
        ///  <returns>the cond. </returns>
        public override string yssInitQuery()
        {
            //// 所有提供的参数项如下，只需要设置子类需要的项即可
            //// 1 查询条件
            string cond = "";
            //// 3 初始只加载列头，若需要则设为true，反之不需要设置此参数
            IsOnlyHeder = true;
            return cond;
        }
         * /// <summary>
        /// 获取list查询条件区的查询条件.
        /// </summary>
        /// <returns>the cond. </returns>
        public string yssGetListCond()
        {
            string cond = "";
            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
            string str = (null == this.tbLeftMain.SelectedRow ? "null" : ((Cls_HDAY_GROUP)this.tbLeftMain.SelectedRow.Tag).C_HDAY_CODE);
            ////获取A区的节假日群
            quyStrUtil.addQuyCon("C_HDAY_CODE", str, ClsConstant.SQL_RA_HYPHEN_EQUAL);


            if (this.cboYear.Value != null)
            {
                quyStrUtil.addQuyCon("N_YEAR", this.cboYear.Value, "=");
            }

            cond = quyStrUtil.getQuyStr(this._formFun.C_FUN_CODE);

            return cond;
        }
         * 
        /// <summary>
        /// list界面加载A区数据，子类重写.
        /// </summary>
        /// <author>lyh 2011.03.02.</author>
        /// <returns>返回a区数据</returns>
        public override string yssGetLeftData()
        {
            string result = null;
            try
            {
                //// edit by yh 2011.03.09修改数据来源为枚举类型,防止赋值出错
                ClsEnums.DataSrc dataSrc = ClsEnums.DataSrc.SrcDB; // 数据来源是缓存
                ////设置A区的数据
                this.leftFormFunCode = "holidays_A";
                hasLeftSetForm = true;
                string funCode = "holidays_A"; // 组合名称
                string headKeys = "C_HDAY_NAME~!~C_HDAY_CODE"; // 自定义列头
                string cond = "MKT_TYPE"; // 查询条件,此时为词汇类型代码
                // 获取数据类型
                string cacheType = "CacheType";
                this.matchSearchStr = new string[1] { "C_HDAY_CODE" }; // 【搜索】功能匹配的属性

                //// 设定左侧数据的加载方式
                YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.ListMode;
                //// 调用由子类提供参数的查询方法
                result = this.yssGetLeftData(dataSrc, funCode, cond, headKeys, cacheType);
            }
            catch (ClsBaseException ce)
            {
                ClsBaseException.DiscardException(ce);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtError("001", _formFun, status));
            }

            return result;
        }
         * /// <summary>
        /// 对控件进行赋值
        /// </summary>
        public void inityearCalendar()
        {
            string year = ""; // 节假日年份
            try
            {
                yearCalendar1.Reset(); // 重置控件
                ////判断下拉控件是否有值，如果有值获取对应的年份，否则取选中
                ////行的年份
                if (null != this.cboYear.Value)
                {
                    year = this.cboYear.Value; // 获取下拉控件的值
                }
                else
                {
                    if (this.bUseMVCService == false)
                    {
                        year = (this.tbMain.SelectedRow.Tag as Cls_HDAY).N_YEAR; // 获取list界面选中的年份
                    }
                    else
                    {
                        year = (this.tbMain.SelectedRow.Tag as HdayGen).N_YEAR; // 获取list界面选中的年份
                    }
                }

                ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();

                string str = (null == this.tbLeftMain.SelectedRow ? "null" : ((HdayGroup)this.tbLeftMain.SelectedRow.Tag).C_HDAY_CODE);
                ////获取A区的节假日群
                quyStrUtil.addQuyCon("C_HDAY_CODE", str, ClsConstant.SQL_RA_HYPHEN_EQUAL);
                quyStrUtil.addQuyCon("N_YEAR", year, "=");
                //// 设置当前年份
                this.yearCalendar1.Year = Convert.ToInt32(year);
                object obj = dataAdmin.GetSpecValue(quyStrUtil.getQuyStr(this._formFun.C_FUN_CODE), "getAllHoiday"); // 后台查询的数据对象
                ////如果为空值，直接返回
                if (null == obj)
                {
                    return;
                }

                string[] aryStr = Regex.Split(obj.ToString(), ClsConstant.YSS_ITEMSPLITMARK1);
                for (int i = 0; i < aryStr.Length; i++)
                {
                    DateTime time = Convert.ToDateTime(aryStr[i]);
                    yearCalendar1.AddSelectedDate(time);
                }
            }
            catch (ClsBaseException ce)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtError("021", _formFun, status));
                ClsBaseException.DiscardException(ce);
            }
        }
         * 
         * **/
        #endregion;

        /// <summary>
        /// 为实现批量审核模块功能（有审核功能并且有自定义参数的 需要重写该方法）
        /// </summary>
        /// <returns>参数集合</returns>
        public override Dictionary<string, string> setExtraCondition()
        {
            this.btnBar.ShowRefreshStatus = true;
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            //this.GetParaAssemble(paraDict);
            return paraDict;
        }
    }
}


