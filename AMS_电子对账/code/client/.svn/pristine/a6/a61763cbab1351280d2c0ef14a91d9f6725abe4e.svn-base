using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using YssElecReco.pojo.Er;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Context;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.BaseControl.Fun;
using FAST.Core.CRUD.Interface;

namespace YssElecReco.Form.Er
{
    /// <summary>
    /// Frm_ELEC_TMPL_RELA_L
    /// </summary>
    public partial class Frm_ELEC_TMPL_RELA_L : FrmBaseList, IFormDetailData
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
        /// 电子对账模板关联
        /// </summary>
        public Frm_ELEC_TMPL_RELA_L()
        {
            this.bUseMVCService = true;
            InitializeComponent();
            isLoadFirst = true;
        }

        /// <summary>
        /// 重写获取公共A区配置信息。
        /// </summary>
        public override AreaAConfigInfo AreaAConfigInfo
        {
            get
            {
                base.AreaAConfigInfo.ShowPageGroups = false;
                base.AreaAConfigInfo.ShowPortInCommonUse = false;
                return base.AreaAConfigInfo;
            }
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
        /// 封装查询条件
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>Dictionary</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            if (!string.IsNullOrEmpty(this.cboDzType.Value))
            {
                paraDict.Add("ARRAY_C_TMPL_TYPE", this.cboDzType.Value.Replace("|", ","));
            }

            if (null == this._mainDataPojo)
            {
                //// 组合
                paraDict.Add("ARRAY_C_PORT_CODE", this.yssBuildLeftCheckRowsStr("portfolio").Replace("'", ""));
            }

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
            this.sDllName = _formFun.YssAssocia.SetDllName;
            this.sSetClassName = _formFun.YssAssocia.SetFormName;
            this.sPojoClassName = _formFun.YssAssocia.PojoClsName;
            this.sPojoDllName = (_formFun.YssAssocia.PojoDllName != null && _formFun.YssAssocia.PojoDllName.Length > 0) ? _formFun.YssAssocia.PojoDllName : ClsFunction.getDllName(_formFun.YssAssocia.PojoClsName);
            if (_formFun != null)
            {
                this.Text = _formFun.C_FUN_NAME;
            }

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
                this.geneParaAssemble.Clear();
                this.geneParaAssemble.Add("dataClass", "DzTmplRela");
                this.geneParaAssemble.Add("C_TMPL_CODE", (mainData as DzTemplate).C_TMPL_CODE);

                ////验证通过，开始装载数据
                this.btnSearch_Click(this.btnBar.getButton(ClsButtonName.BtnRefresh).Owner, EventArgs.Empty);
            }
        }

        /// <summary>
        /// 重写基类
        /// </summary>
        /// <param name="paraDict">paradict</param>
        /// <returns>paradict</returns>
        protected override Dictionary<string, string> OnAfterGetParaAssemble(Dictionary<string, string> paraDict)
        {
            if (!paraDict.ContainsKey("dataClass"))
            {
                paraDict.Add("dataClass", "DzTmplRela");
            }

            if (!paraDict.ContainsKey("C_TMPL_CODE") || string.IsNullOrEmpty(paraDict["C_TMPL_CODE"]))
            {
                if (null != this.geneParaAssemble && this.geneParaAssemble.ContainsKey("C_TMPL_CODE"))
                {
                    paraDict.Remove("C_TMPL_CODE");
                    paraDict.Add("C_TMPL_CODE", this.geneParaAssemble["C_TMPL_CODE"]);

                }
            }

            return paraDict;
        }

        #endregion
    }
}
