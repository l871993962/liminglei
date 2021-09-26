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
using YssInformation.Support.Fun;
using YssInformation.Support.Bi.Market.Service;

namespace YssSecInformation.Sv.Form
{
    /// <summary>
    /// 功能简介：计费证券信息浏览界面，负责证券计费信息的显示和查询功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： HeLiang
    /// 创建日期： 2016.09.05
    /// </summary>
    public partial class Frm_SEC_BASE_JF_L : FrmBaseList
    {
        /// <summary>
        /// 计费证券服务
        /// </summary>
        ////private ISecBaseJfService secBaseJfService = null;

        /// <summary>
        /// 交易市场服务
        /// </summary>
        private IMktDataService mktDataService = null;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_SEC_BASE_JF_L()
        {
            InitializeComponent();
            this.bUseMVCService = true;
            ////实现附件功能
            this.AutoLoadEnclosure = true;
        }

        /// <summary>
        /// 重写A区配置信息，走基类老旧模式 —— A区显示交易市场
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
        /// 初始化服务
        /// </summary>
        protected override void initServiceMVC()
        {
            base.initServiceMVC();
            mktDataService = DataServiceFactory.createService<IMktDataService>();
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
                // 修改数据来源为枚举类型,防止赋值出错
                leftDataFunCode = YssInformation.Support.Context.AssociaType.base_exchange.ToString();
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
                ////YssMessageBox.ShowDyanInformation("加载左侧交易市场信息报错", ex.Message, MessageBoxIcon.Error, YssResources.Fun.ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getAareaLoadErr(ex.Message));
            }
        }

        /// <summary>
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写.
        /// </summary>
        /// <returns>返回查询条件.</returns>
        public override string yssInitQuery()
        {
            //// 所有提供的参数项如下，只需要设置子类需要的项即可
            //// 1 查询条件    按照证券品种表中证券属性值查询
            string cond = "";
            //// 2 初始只加载列头，若需要则设为true，反之不需要设置此参数
            IsOnlyHeder = true;
            return cond;
        }

        /// <summary>
        /// 获取list查询条件区的查询条件.
        /// </summary>
        /// <returns>返回查询条件.</returns>
        public string yssGetListCond()
        {
            string cond = "";
            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
            try
            {
                string search = this.yssBuildLeftCheckRowsStr("base_exchange");  // 获取A区选中的行            
                quyStrUtil.addQuyCon("portCode", "C_MKT_CODE", search, "IN");

                //// 查询条件按照：交易证券、投资组合
                if (this.selSecurity.Value != null)
                {
                    quyStrUtil.addQuyCon("C_SEC_CODE", this.selSecurity.Value.Trim(), ClsConstant.SQL_RA_HYPHEN_EQUAL);
                }

                if (this.cboPortCode.Value != null)
                {
                    quyStrUtil.addQuyCon("C_PORT_CODE", this.cboPortCode.Value.Trim(), ClsConstant.SQL_RA_HYPHEN_EQUAL);
                }

                cond = quyStrUtil.getQuyStr("base_exchange");
            }
            catch (Exception e)
            {
                ClsBaseException.DiscardException(e);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500037", _formFun, status));
            }

            return cond;
        }

        /// <summary>
        /// 封装条件对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {            
            string search = this.yssBuildLeftCheckRowsStr("base_exchange");
            search = search.Replace("'", "");
            if (search != "")
            {
                paraDict.Add("ARRAY_C_MKT_CODE", search);
            }
            //// 查询条件按照：交易证券、投资组合
            if (this.selSecurity.Value != null && this.selSecurity.Value.Trim().Length > 0)
            {
                paraDict.Add("C_SEC_CODE", this.selSecurity.Value);
            }

            if (this.cboPortCode.Value != null && this.cboPortCode.Value.Trim().Length > 0)
            {
                paraDict.Add("C_PORT_CODE", this.cboPortCode.Value);
            }

            return paraDict;
        }

        /// <summary>
        /// 计费证券信息窗体的加载事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_BASE_JF_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            dataService = (IServiceBus)ServiceFactory.createService(serviceType);
        }
        

    }
}
