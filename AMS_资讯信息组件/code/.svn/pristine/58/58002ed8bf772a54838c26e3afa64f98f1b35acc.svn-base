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
using FAST.Common.Service.Services;
using YssSecInformation.Support.Sv.Service;
using YssInformation.Support.Fun;
using YssInformation.Support.Context;
using YssSecInformation.Support.Pojo.Sec;
using YssInformation.Support.Bi.Market.Service;
using FAST.Core.CRUD.Interface;

namespace YssSecInformation.Sv.Form
{
    /// <summary>
    /// 债券每日利息
    /// </summary>
    public partial class Frm_SEC_MRLX_ZQ_L : FrmBaseList, IFormDetailData
    {
        /// <summary>
        /// 获取或设置一个值，该值为主窗体传过来的数据。当该窗体为某一窗体的附属窗体时有效。
        /// </summary>
        private BasePojo _mainDataPojo;

        /// <summary>
        /// 获取或设置当前窗体是否已经被FrmBaseListWidthDetails窗体关联为子窗体
        /// </summary>
        private bool _hadBeenRelationed = false;

        /// <summary>
        /// 债券每日利息数据服务接口
        /// </summary>
        private IFiIncomeService myService = null;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_SEC_MRLX_ZQ_L()
        {
            this.bUseMVCService = true;
            InitializeComponent();
            ////实现附件功能。何讯，20151208
            this.AutoLoadEnclosure = true;
        }

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
            string search = this.yssBuildLeftCheckRowsStr("base_exchange");
            if (this.cboSec.Value != null && this.cboSec.Value.Trim().Length > 0)
            {
                quyStrUtil.addQuyCon("C_SEC_CODE", this.cboSec.Value, "=");
            }

            quyStrUtil.addQuyCon("C_MKT_CODE", search, "in");
            quyStrUtil.addQuyCon("D_INCOMES", this.dtpjx.getBeginDate.ToString("yyyy-MM-dd").Trim(), "=");
            quyStrUtil.addQuyCon("D_INCOME", this.dtpjx.getEndDate.ToString("yyyy-MM-dd").Trim(), "=");
            cond = quyStrUtil.getQuyStr("base_exchange");
            return cond;
        }

        /////// <summary>
        /////// list界面加载A区数据，子类重写.
        /////// </summary>
        /////// <author>wuwenlan 2011.03.2</author>
        /////// <returns>the result.</returns>
        ////public override string yssGetLeftData()
        ////{
        ////    //// edit by yh 2011.03.09修改数据来源为枚举类型,防止赋值出错
        ////    ClsEnums.DataSrc dataSrc = ClsEnums.DataSrc.SrcCache;    // 数据来源是缓存
        ////    string funCode = "exchange"; // 交易市场
        ////    string headKeys = "C_MKT_NAME~!~C_MKT_CODE"; // 自定义列头

        ////    this.matchSearchStr = new string[2] { "C_MKT_NAME", "C_MKT_CODE" };  // 【搜索】功能匹配的属性

        ////    string result = null;
        ////    //// 设定左侧数据的加载方式
        ////    YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;
        ////    //// 调用由子类提供参数的查询方法
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
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_MRLX_ZQ_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.myService = ServiceFactory.createService(serviceType) as IFiIncomeService;
            this.dataService = this.myService;
        }

        /// <summary>
        /// 封装查询条件到对象
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>paraDict</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            string search = this.yssBuildLeftCheckRowsStr("base_exchange");
            search = search.Replace("'", "");
            paraDict.Add("ARRAY_C_MKT_CODE", search);
            ////  STORY #22949 [招商证券]每百元国债利率可按日期段查询
            if ((this.cboSec.Value != null && this.cboSec.Value.Trim().Length > 0) || _mainDataPojo != null)
            {
                if (_mainDataPojo != null)
                {
                    paraDict.Add("C_SEC_CODE", (_mainDataPojo as SecBase).C_SEC_CODE);
                }
                else
                {
                paraDict.Add("ARRAY_C_SEC_CODE", this.cboSec.Value.Replace("|", ","));
                }
            }

            paraDict.Add("D_BEGIN", this.dtpjx.getBeginDate.ToString("yyyy-MM-dd").Trim());
            paraDict.Add("D_END", this.dtpjx.getEndDate.ToString("yyyy-MM-dd").Trim());

            return paraDict;
        }

        #region IFormDetailData 成员

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
        /// 明细窗体初始化
        /// </summary>
        /// <param name="parent">FrmBaseListWithDetails父容器</param>
        public void InitializeDetailForm(FrmBaseListWithDetails parent)
        {
            ////  STORY #22949 [招商证券]每百元国债利率可按日期段查询
            ////  wangtangyao 2015-06-06
            this.tbFilter.Columns[1].Width = 230;
            this.tbFilter.Rows[1].Cells[3].Text = "";
            this.tbFilter.Rows[1].Cells[4].InnerControl = null;
            this.tbFilter.Rows[1].Cells[0].Text = "计息日期：";
            this.tbFilter.Rows[1].Cells[1].InnerControl = null;
            this.tbFilter.Rows[1].Cells[1].InnerControl = this.dtpjx;
            this.sDllName = _formFun.YssAssocia.SetDllName;
            this.sSetClassName = _formFun.YssAssocia.SetFormName;
            this.sPojoClassName = _formFun.YssAssocia.PojoClsName;
            this.sPojoDllName = (_formFun.YssAssocia.PojoDllName != null && _formFun.YssAssocia.PojoDllName.Length > 0) ? _formFun.YssAssocia.PojoDllName : ClsFunction.getDllName(_formFun.YssAssocia.PojoClsName);
            if (_formFun != null)
            {
                this.Text = _formFun.C_FUN_NAME;
            }

            this.ShowLeftPanel = false;
            ////this.ShowFilterPanel = true;
        }

        /// <summary>
        /// 装载数据
        /// </summary>
        /// <param name="mainData">主表传过来的Pojo</param>
        public void LoadDetailData(FAST.Common.Service.Pojo.Base.BasePojo mainData)
        {
            if (page == null)
            {
                page = new ClsPageInation();
            }

            page.CurrPage = 1;
            page.PageCount = 0;

            bool validate = this.AllowReloadDetailData(mainData);
            if (validate)
            {
                ////this.AllowResetGeneParaAssemble = false;
                this.MainDataPojo = mainData;
                this.geneParaAssemble.Clear();
                this.geneParaAssemble.Add("dataClass", "FiIncome");
                this.geneParaAssemble.Add("C_SEC_CODE", (mainData as SecBase).C_SEC_CODE);
                ////  STORY #22949 [招商证券]每百元国债利率可按日期段查询
                ////  wangtangyao 2015-06-06
                this.geneParaAssemble.Add("D_BEGIN", this.dtpjx.getBeginDate.ToString("yyyy-MM-dd").Trim());
                this.geneParaAssemble.Add("D_END", this.dtpjx.getEndDate.ToString("yyyy-MM-dd").Trim());

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
            if (null != this.MainDataPojo)
            {
                ////  STORY #22949 [招商证券]每百元国债利率可按日期段查询
                ////  wangtangyao 2015-06-06
                ////if (paraDict.ContainsKey("D_BEGIN"))
                ////{
                ////    paraDict.Remove("D_BEGIN");
                ////}

                ////if (paraDict.ContainsKey("D_END"))
                ////{
                ////    paraDict.Remove("D_END");
                ////}

                if (paraDict.ContainsKey("ARRAY_C_MKT_CODE"))
                {
                    paraDict.Remove("ARRAY_C_MKT_CODE");
                }
            }

            if (!paraDict.ContainsKey("dataClass"))
            {
                paraDict.Add("dataClass", "FiIncome");
            }

            if (!paraDict.ContainsKey("C_SEC_CODE") || string.IsNullOrEmpty(paraDict["C_SEC_CODE"]))
            {
                if (null != this.geneParaAssemble && this.geneParaAssemble.ContainsKey("C_SEC_CODE"))
                {
                    paraDict.Remove("C_SEC_CODE");
                    paraDict.Add("C_SEC_CODE", this.geneParaAssemble["C_SEC_CODE"]);
                }
            }

            return paraDict;
        }

        #endregion
    }
}


