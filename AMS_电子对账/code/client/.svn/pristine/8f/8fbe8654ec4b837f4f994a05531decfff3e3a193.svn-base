using System;
using System.Collections.Generic;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.CRUD.Interface;
using FAST.Core.Communication.Service;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.BaseControl.Fun;
using YssSztTool.Service.Para;
using YssSztTool.Pojo.Para;

namespace YssSztTool.Form.Para
{
    /// <summary>
    /// STORY42784中国银行_深证通伺服器要求采用热备模式
    /// STORY42660【中国银行】深证通伺服器要求采用热备模式
    /// DESC: 深圳通参数设置窗体
    /// CREATED BY: wulongxing
    /// CREATED TIME: 2017-06-12
    /// </summary>
    public partial class Frm_Mr_Info_L : FrmBaseList, IFormDetailData
    {
        public Frm_Mr_Info_L()
        {
            InitializeComponent();
            bUseMVCService = true;
            ShowLeftPanel = false;
            isLoadFirst = true;
            this.dataService = ServiceFactory.createService<IMrInfoService>();
        }

        /// <summary>
        /// 获取或设置一个值，该值为主窗体传过来的数据。当该窗体为某一窗体的附属窗体时有效。
        /// </summary>
        private BasePojo _mainDataPojo;

        /// <summary>
        /// 获取或设置当前窗体是否已经被FrmBaseListWidthDetails窗体关联为子窗体
        /// </summary>
        private bool _hadBeenRelationed = false;
        #region IFormDetailData 成员

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
        /// 验证是否需要重新装载数据
        /// </summary>
        /// <param name="mainData">主表传过来的数据</param>
        /// <returns>返回验证结果</returns>
        public bool AllowReloadDetailData(BasePojo mainData)
        {
            bool retValue = true;
            if (mainData == null)
            {
                return false;
            }

            return retValue;
        }

        /// <summary>
        /// 明细窗体初始化
        /// </summary>
        public void InitializeDetailForm(FrmBaseListWithDetails parent)
        {
            this.sDllName = _formFun.YssAssocia.SetDllName;
            this.sSetClassName = _formFun.YssAssocia.SetFormName;
            this.sPojoClassName = _formFun.YssAssocia.PojoClsName;
            this.sPojoDllName = (_formFun.YssAssocia.PojoDllName != null && _formFun.YssAssocia.PojoDllName.Length > 0) ? _formFun.YssAssocia.PojoDllName : ClsFunction.getDllName(_formFun.YssAssocia.PojoClsName);
            if (_formFun != null)
            {
                this.Text = _formFun.C_FUN_NAME;
            }
            this.bUseMVCService = true;
            this.ShowLeftPanel = false;
            this.ShowFilterPanel = false;
        }



        /// <summary>
        /// 装载数据
        /// </summary>
        /// <param name="mainData">主表传过来的Pojo</param>
        public void LoadDetailData(BasePojo mainData)
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
                this.AllowResetGeneParaAssemble = false;
                this.MainDataPojo = mainData;

                ErPara erPara = this.MainDataPojo as ErPara;
                this.geneParaAssemble.Clear();
                this.geneParaAssemble.Add("dataClass", "MrInfo");
                this.geneParaAssemble.Add("C_PARA_CODE", erPara.C_Para_Code);
                ////验证通过，开始装载数据
                this.btnSearch_Click(this.btnBar.getButton(ClsButtonName.BtnRefresh).Owner, EventArgs.Empty);
            }
        }

        #endregion

        /// <summary>
        /// 重写基类
        /// </summary>
        /// <param name="paraDict">paradict</param>
        /// <returns>paradict</returns>
        protected override Dictionary<string, string> OnAfterGetParaAssemble(Dictionary<string, string> paraDict)
        {
            if (null != this.MainDataPojo)
            {
                if (paraDict.ContainsKey("C_PARA_CODE"))
                {
                    paraDict.Remove("C_PARA_CODE");
                }

                ErPara erPara = this.MainDataPojo as ErPara;
                paraDict["C_PARA_CODE"] = erPara.C_Para_Code;

                if (!paraDict.ContainsKey("dataClass"))
                {
                    paraDict["dataClass"] = "MrInfo";
                }
            }

            return paraDict;
        }
        protected override void tbMain_SelectionChanged(object sender, Yss.KTable.Events.RowEventArgs e)
        {
        }
    }
}
