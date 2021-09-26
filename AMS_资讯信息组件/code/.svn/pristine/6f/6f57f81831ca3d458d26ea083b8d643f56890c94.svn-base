using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Interface;
using FAST.Core.Exceptions;

using FAST.Core.Context;

using FAST.Platform.Right.Fun;
using FAST.Core.BaseControl.Fun;
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
using YssInformation.Support.Fun;


using YssSecInformation.Support.Mp.FwMkt.Pojo;
using FAST.Core.BaseControl.Fun;

using YssSecInformation.Support.Mp.FwMkt.Service;

////using YssPara.Pojo.Bi;
////using YssPojos.Data.Mp;
////using YssPojos.Para.Bi;

namespace YssSecInformation.Mp.FwMkt.Form
{
    /// <summary>
    /// 远期外汇行情
    /// </summary>
    public partial class Frm_SEC_MKT_FQ_L : FrmBaseList
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private IFwMktValueService mktService = null;

        /// <summary>
        /// 增加一个标志属性传到set窗体，根据这个标志，确定set窗体是否可用
        /// </summary>
        private string c_market_code = "";

        
        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_SEC_MKT_FQ_L()
        {
            this.bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 增加一个标志属性传到set窗体，根据这个标志，确定set窗体是否可用
        /// </summary>
        public string C_MARKET_CODE
        {
            get { return c_market_code; }
            set { c_market_code = value; }
        }


        /// <summary>
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写
        /// </summary>
        /// <returns>初始化查询条件</returns>
        public override string yssInitQuery()
        {
            // 所有提供的参数项如下，只需要设置子类需要的项即可
            // 1 查询条件
            string cond = ""; // " and a.C_MKT_CLS = 'ER'";  // 查询分类为汇率的数据 
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
            quyStrUtil.addQuyCon("C_MKT_CLS", "FW", "=");
            if (this.cboCury.Value != null) 
            {
                quyStrUtil.addQuyCon("C_SEC_CODE", this.cboCury.Value, "=");
            }

            quyStrUtil.addQuyCon("D_MKT", this.dtpMktDate.getBeginDate.ToString("yyyy-MM-dd").Trim(), ">=");
            quyStrUtil.addQuyCon("D_MKTS", this.dtpMktDate.getEndDate.ToString("yyyy-MM-dd").Trim(), "<=");
            
            cond = quyStrUtil.getQuyStr(this._formFun.C_FUN_CODE);
            return cond;
        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_MKT_FQ_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.mktService = ServiceFactory.createService(serviceType) as IFwMktValueService;
            this.dataService = this.mktService;
        }

        /// <summary>
        /// 封装条件到对象
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            paraDict.Add("C_MKT_CLS", "FW");
            if (this.cboCury.Value != null)
            {
                paraDict.Add("C_SEC_CODE", this.cboCury.Value);
            }

            paraDict.Add("D_BEGIN", this.dtpMktDate.getBeginDate.ToString("yyyy-MM-dd").Trim());
            paraDict.Add("D_END", this.dtpMktDate.getEndDate.ToString("yyyy-MM-dd").Trim());

            return paraDict;
        }

        /// <summary>
        /// 设置参数
        /// </summary>
        /// <returns>参数集合</returns>
        public override Dictionary<string, string> setExtraCondition()
        {
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("C_MKT_CLS", "FW");
            return paraDict;
        }
    }
}


