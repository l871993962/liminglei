using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;

using FAST.Core.Exceptions;

using FAST.Core.Context;

using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;

using FAST.Common.Service.Pojo.Base;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;





using FAST.Common.Service.Services;
using YssInformation.Support.Fun;
using FAST.Core.CRUD.Interface;


namespace YssProductInfo.Ab.AttributeCls.Form
{
    /// <summary>
    /// 描述：组合产品属性List界面
    /// 创建人：zhengguiyu
    /// 创建时间：20140322
    /// </summary>
    public partial class Frm_PORT_PD_ATTRIBUTE_L : FrmBaseList, IFormDetailData
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
        /// 构造方法
        /// </summary>
        public Frm_PORT_PD_ATTRIBUTE_L()
        {
            this.bUseMVCService = true;
            InitializeComponent();
            this.cboInvestCode.Value = ClsConstant.YSS_ALL;
            this.cboAssetsCode.Value = ClsConstant.YSS_ALL;
            this.tbLeftMain.ShowCheckBox = true;
            /*
             * Author : ChenLong
             * Date   : 2015-03-02
             * Status : Add
             * Comment: 群组是否验证标识
             */
            isValidGroupPortCode = false;
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
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写.
        /// </summary>
        /// <returns>初始查询条件</returns>
        public override string yssInitQuery()
        {
            string cond = "";
            this.IsOnlyHeder = true;
            return cond;
        }

        /// <summary>
        /// 获取list查询条件区的查询条件.
        /// </summary>
        /// <returns>返回查询条件</returns>
        public string yssGetListCond()
        {
            string cond = "";
            string search = this.yssBuildLeftCheckRowsStr("portfolio", "C_PORT_CODE");

            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
            quyStrUtil.addQuyCon("portCode", "C_PORT_CODE", search, "IN");

            if (this.cboInvestCode.Value != null && this.cboInvestCode.Value.Trim().Length > 0 && !this.cboInvestCode.Value.Equals("-9999"))
            {
                quyStrUtil.addQuyCon("investCode", "C_INVEST_CODE", cboInvestCode.Value, "=");
            }

            if (this.cboAssetsCode.Value != null && this.cboAssetsCode.Value.Trim().Length > 0 && !this.cboAssetsCode.Value.Equals("-9999"))
            {
                quyStrUtil.addQuyCon("assetsCode", "C_ASSETS_CODE", cboInvestCode.Value, "=");
            }

            cond = quyStrUtil.getQuyStr("portfolio");
            return cond;
        }

         /// <summary>
        /// 封装条件对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            string search = this.yssBuildLeftCheckRowsStr("portfolio");
            search = search.Replace("'", "");
            paraDict.Add("ARRAY_C_PORT_CODE", search);

            if (this.cboInvestCode.Value != null && this.cboInvestCode.Value.Trim().Length > 0 && !this.cboInvestCode.Value.Equals("-9999"))
            {
                paraDict.Add("C_INVEST_CODE", cboInvestCode.Value);
            }

            if (this.cboAssetsCode.Value != null && this.cboAssetsCode.Value.Trim().Length > 0 && !this.cboAssetsCode.Value.Equals("-9999"))
            {
                paraDict.Add("C_ASSETS_CODE", cboAssetsCode.Value);
            }

            return paraDict;
        }

        /// <summary>
        /// 窗体LOAD事件
        /// 实例化service
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ASS_EQU_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.dataService = ServiceFactory.createService(serviceType) as IServiceBus;
        }

        /// <summary>
        /// 重写复制事件，清空set界面的数据
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnCopy_Click(object sender, EventArgs e)
        {
            base.btnCopy_Click(sender, e);
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
                this.geneParaAssemble.Add("dataClass", "InveFee");
                this.geneParaAssemble.Add("ARRAY_C_PORT_CODE", (mainData as FAST.Common.Service.Pojo.Port).C_PORT_CODE);

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
            if (!paraDict.ContainsKey("dataClass"))
            {
                paraDict.Add("dataClass", "PortPdAttribute");
            }

            if (!paraDict.ContainsKey("ARRAY_C_PORT_CODE") || string.IsNullOrEmpty(paraDict["ARRAY_C_PORT_CODE"]))
            {
                if (null != this.geneParaAssemble && this.geneParaAssemble.ContainsKey("ARRAY_C_PORT_CODE"))
                {
                    paraDict.Remove("ARRAY_C_PORT_CODE");
                    paraDict.Add("ARRAY_C_PORT_CODE", this.geneParaAssemble["ARRAY_C_PORT_CODE"]);
                }
            }

            return paraDict;
        }

        #endregion
    }
}


