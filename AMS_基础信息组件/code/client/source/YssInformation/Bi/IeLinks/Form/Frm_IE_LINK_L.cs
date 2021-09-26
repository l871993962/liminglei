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



using Yss.KTable.Models;
using YssInformation.Support.Bi.IeLinks.Pojo;
using YssInformation.Support.Sys.Dictionary.Service;

namespace YssInformation.Bi.IeLinks.Form
{
    /// <summary>
    /// 收支链接list窗体
    /// </summary>
    public partial class Frm_IE_LINK_L : FrmBaseList
    {
        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_IE_LINK_L()
        {
            InitializeComponent();
            this.bUseMVCService = true;
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

        /////// <summary>
        /////// list界面加载A区数据，子类重写.
        /////// </summary>
        /////// <author>yh 2011.02.28.</author>
        /////// <returns>返回查询结果.</returns>
        ////public override string yssGetLeftData()
        ////{
        ////    ClsEnums.DataSrc dataSrc = ClsEnums.DataSrc.SrcCache; // 数据来源是缓存
        ////    string funCode = "IeItem"; // 要获取数据的功能代码
        ////    string cond = ""; // 查询条件,此时为词汇类型代码
        ////    string headKeys = "C_IE_NAME"; // 自定义列头,此时为词汇类型代码
        ////    // 获取数据类型
        ////    string cacheType = "CacheType";
        ////    this.matchSearchStr = new string[1] { "C_IE_NAME" }; // 【搜索】功能匹配的属性

        ////    string result = null;
        ////    //// 设定左侧数据的加载方式
        ////    YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.ListMode;

        ////    //// 调用由子类提供参数的查询方法
        ////    result = this.yssGetLeftData(dataSrc, funCode, cond, headKeys, cacheType);

        ////    return result;
        ////}

        /// <summary>
        /// 获取列头对象列表
        /// </summary>
        /// <returns>A区列头集合</returns>
        private List<ListHeadInfo> getLeftTableListHead()
        {
            List<ListHeadInfo> listHeadList = new List<ListHeadInfo>();

            ListHeadInfo listhead = new ListHeadInfo();
            listhead.Key = "C_IE_NAME";
            listhead.Text = "C_IE_NAME";
            listhead.Align = "LEFT";
            listhead.Format = "";
            listhead.ShowConvert = "false";

            listHeadList.Add(listhead);

            return listHeadList;
        }

        /// <summary>
        /// 加载A区数据
        /// </summary>
        public override void yssLoadLeftData()
        {
            IIeItemDataService ieitemDataService = DataServiceFactory.createService<IIeItemDataService>();
            QueryRes res = new QueryRes();
            res.DataList = ieitemDataService.getDataList();
            res.ListHeadList = getLeftTableListHead();
            leftDataFunCode = "base_IeItem";
            TableListLoader tableLoader = new TableListLoader();
            this.YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.ListMode;
            tableLoader.loadTable(tbLeftMain, res, false, false, ClsEnums.KTableDataShowMode.ListMode);
            this.matchSearchStr = new string[1] { "C_IE_NAME" };     // 【搜索】功能匹配的属性
        }

        /// <summary>
        /// 封装条件对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            if (this.txtFeeCode.Text.Trim().Length > 0)
            {
                paraDict.Add("C_FEE_CODE", "%" + this.txtFeeCode.Text.Trim() + "%");
            }

            string search = this.yssBuildLeftCheckRowsStr("base_IeItem");
            search = search.Replace("'", "");
            paraDict.Add("ARRAY_C_IE_CODE", search);


            return paraDict;
        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_IE_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.dataService = ServiceFactory.createService(serviceType) as IServiceBus;
        }

        /// <summary>
        /// 行单击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbMain_RowClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            base.tbMain_RowClicked(sender, e);
            if (null == tbMain.SelectedRow)
            {
                return;
            }

            IeLink link = this.tbMain.SelectedRow.Tag as IeLink;
            if (link == null)
            {
                return;
            }
            else 
            {
                if (link.C_SRC_MARK.Equals("S")) 
                {
                    btnBar.setButtonEnabled(ClsButtonName.BtnDelete, false);
                    btnBar.setButtonEnabled(ClsButtonName.BtnAudit, false);
                    btnBar.setButtonEnabled(ClsButtonName.BtnUnAudit, false);
                }
            }
        }

        /// <summary>
        /// 复写删除事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnDelete_Click(object sender, EventArgs e)
        {
            if (isCheckedS())
            {
                ////提示内容:操作数据中包含系统数据，操作无效!
                ////YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarnStr("001", this._formFun, status));
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, status));
                return;
            }

            base.btnDelete_Click(sender, e);
        }

        /// <summary>
        /// 复写审核事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnAudit_Click(object sender, EventArgs e)
        {
            if (isCheckedS())
            {
                ////提示内容:操作数据中包含系统数据，操作无效!
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, status));
                return;
            }

            base.btnAudit_Click(sender, e);
        }

        /// <summary>
        /// 复写反审核事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnUnAudit_Click(object sender, EventArgs e)
        {
            if (isCheckedS())
            {
                ////提示内容:操作数据中包含系统数据，操作无效!
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, status));
                return;
            }

            base.btnUnAudit_Click(sender, e);
        }

        /// <summary>
        /// 勾选的数据是否包含系统数据
        /// </summary>
        /// <returns>是否包含</returns>
        private bool isCheckedS()
        {
            bool flag = false;

            foreach (Row row in tbMain.CheckedRows)
            {
                IeLink pojo = row.Tag as IeLink;
                if (pojo != null && pojo.C_SRC_MARK.ToUpper().Equals("S"))
                {
                    flag = true;
                }
            }

            return flag;
        }
    }
}


