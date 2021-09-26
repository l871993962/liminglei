using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;

using FAST.Core.Context;

using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;
using System.Collections.Generic;


////using YssBaseCls.Interface;
using YssSecInformation.Support.Mp.Hggthq.Pojo;


using System.Windows.Forms;
using FAST.Common.Service.Services;
using FAST.Core.CRUD.Interface;

namespace YssSecInformation.Mp.Hggthqsec.Form
{
    /// <summary>
    /// 回购收益行情List
    /// </summary>
    public partial class Frm_SEC_LL_GTSEC_L : FrmBaseList, IFormDetailData
    {
        /// <summary>
        /// 获取或设置一个值，该值为主窗体传过来的数据。当该窗体为某一窗体的附属窗体时有效。
        /// </summary>
        private BasePojo _mainDataPojo;

        /// <summary>
        /// 用于控制证券回购收益行情操作权限
        /// </summary>
        private BasePojo markPojo;

        /// <summary>
        /// 获取或设置当前窗体是否已经被FrmBaseListWidthDetails窗体关联为子窗体
        /// </summary>
        private bool _hadBeenRelationed = false;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_SEC_LL_GTSEC_L()
        {
            InitializeComponent();
            this.bUseMVCService = true;
            this.isLoadFirst = true;
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
        /// 查询条件封装
        /// </summary>
        /// <param name="paraDict">para</param>
        /// <returns>paraMap</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            if (null != this.selSecurity.Value) 
            {
                paraDict.Add("C_SEC_CODE", this.selSecurity.Value);
            }

            if (!paraDict.ContainsKey("dataClass"))
            {
                paraDict.Add("dataClass", "CounterRate");
            }

            if (null == markPojo)
            {
                paraDict.Add("C_SEC_CODE", "--");
            }
            else 
            {
                paraDict.Add("D_MKT", ((CounterRate)MainDataPojo).D_MKT.ToString("yyyy-MM-dd"));
                paraDict.Add("N_DURATION", ((CounterRate)MainDataPojo).N_DURATION.ToString());
            }

            paraDict.Add("C_IS_PUBLIC", "0");

            return paraDict;
        }

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
                markPojo = mainData;
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
            ////this.ShowFilterPanel = false;
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
                this.AllowResetGeneParaAssemble = false;
                this.MainDataPojo = mainData;
                this.geneParaAssemble.Clear();
                this.geneParaAssemble.Add("dataClass", "CounterRate");
                this.geneParaAssemble.Add("D_MKT", ((CounterRate)MainDataPojo).D_MKT.ToString("yyyy-MM-dd"));
                this.geneParaAssemble.Add("N_DURATION", ((CounterRate)MainDataPojo).N_DURATION.ToString());
                this.geneParaAssemble.Add("C_BIZ_TYPE", ((CounterRate)MainDataPojo).C_BIZ_TYPE.ToString());
                this.geneParaAssemble.Add("C_IS_PUBLIC", "0");

                ////验证通过，开始装载数据
                this.btnSearch_Click(this.btnBar.getButton(FAST.Core.BaseControl.Fun.ClsButtonName.BtnRefresh).Owner, System.EventArgs.Empty);
                this.geneParaAssemble.Clear();
            }
        }

        /// <summary>
        /// 新增前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_LL_GTSEC_L_YssOnBeforeNewClick(object sender, YssBeforeOperEventArgs e)
        {
            if (null == _mainDataPojo) 
            {
                e.IsCancel = true;
                ClsRetInfo retInfo = new ClsRetInfo();
                retInfo.icon = MessageBoxIcon.Warning;
                retInfo.infoContent = "新增前请选择回购收益行情数据！";
                YssMessageBox.ShowCommonInfoText(retInfo);
            }
        }

        /// <summary>
        /// 查询后事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_LL_GTSEC_L_YssOnAfterRefreshClick(object sender, System.EventArgs e)
        {
            if (this.tbMain.Rows.Count == 0 && null == markPojo)
            {
                this.btnBar.setAllButtonEnabled(false);
                markPojo = null;
            }
            else 
            {
                this.btnBar.setAllButtonEnabled(true);
            }
        }

        /// <summary>
        /// 查询前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_LL_GTSEC_L_YssOnBeforeRefreshClickMVC(object sender, YssBeforeOperEventArgsMVC e)
        {
            ////geneParaAssemble.Clear();
        }


    }
}


