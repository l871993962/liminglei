using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;

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
////using YssPara.Service.Aa;
////using YssPara.Pojo.Aa;
////using YssBaseCls.Context;
using YssInformation.Support.Fun;
using YssSecInformation.Support.Aa.Etf.Pojo;
using YssSecInformation.Support.Aa.Etf.Service;
using YssProductInfo.Support.Context;







namespace YssSecInformation.Ac.Etfskep.Form
{
    /// <summary>
    /// ETF股票篮信息
    /// </summary>
    public partial class Frm_ETF_GP_BAKT_L : FrmBaseList
    {
        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_ETF_GP_BAKT_L()
        {
            bUseMVCServiceLeft = true;
            this.bUseMVCService = true;
            InitializeComponent();
            ////附件功能。何讯，20151208
            this.AutoLoadEnclosure = true;
        }

        /// <summary>
        /// 初始化参数
        /// </summary>
        /// <returns>初始化查询条件</returns>
        public override string yssInitQuery()
        {
            // 所有提供的参数项如下，只需要设置子类需要的项即可
            // 1 查询条件
            string cond = "";

            // 3 初始只加载列头，若需要则设为true，反之不需要设置此参数
            IsOnlyHeder = true;

            return cond;
        }

        /// <summary>
        /// 获取list查询条件区的查询条件
        /// </summary>
        /// <returns>list区查询条件</returns>
        public string yssGetListCond()
        {
            string cond = "";

            ////20120613通过组合基本信息指定字段获取资产代码
            string search = this.yssBuildLeftCheckRowsStr("pd_portfolio", "C_ASS_CODE");
            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();

            quyStrUtil.addQuyCon("C_TRADE_CODE", "C_TRADE_CODE", search, "IN");

            if (null != this.cbosec.Value)
            {
                quyStrUtil.addQuyCon("C_SEC_CODE", this.cbosec.Value, "=");
            }

            quyStrUtil.addQuyCon("D_TRADE", "D_TRADE", dtpTradeDate.getBeginDate.ToString("yyyy-MM-dd").Trim() + "," + dtpTradeDate.getEndDate.Date.ToString("yyyy-MM-dd").Trim(), "BETWEEN");
            cond = quyStrUtil.getQuyStr();
            return cond;
        }
        
        /// <summary>
        /// 获取list查询条件区的查询条件
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>paraDict</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            string search = this.yssBuildLeftCheckRowsStr("pd_portfolio");  // tanwenjie 2011.7.28 获取A区选中的行
            search = search.Replace("'", "");
            //// 根据组合代码查询交易代码
            List<BasePojo> etfList = this.queryEtf(search);
            if (null != etfList && etfList.Count != 0)
            {
                search = "";
                foreach (BasePojo pojo in etfList) 
                {
                    Etf etf = pojo as Etf;
                    search += "" + etf.C_TRADE_CODE + ",";
                }

                search = search.Substring(0, search.LastIndexOf(","));
                paraDict.Add("ARRAY_C_TRADE_CODE", search);
            }

            if (this.dtpTradeDate.getBeginDate != null)
            {
                paraDict.Add("D_START_DATE", this.dtpTradeDate.getBeginDate.ToString("yyyy-MM-dd"));
            }

            if (this.dtpTradeDate.getEndDate != null)
            {
                paraDict.Add("D_END_DATE", this.dtpTradeDate.getEndDate.ToString("yyyy-MM-dd"));
            }

            if (this.cbosec.Value != null)
            {
                paraDict.Add("C_SEC_CODE", this.cbosec.Value);
            }

            if (!string.IsNullOrEmpty(this.txtJJcode.Text))
            {
                paraDict.Add("C_TRADE_CODE", this.txtJJcode.Text);
            }

            return paraDict;
        }

        /// <summary>
        /// 根据组合代码查询ETF基本信息
        /// </summary>
        /// <param name="portCodes">portCodes</param>
        /// <returns>etf信息</returns>
        private List<BasePojo> queryEtf(string portCodes) 
        {
            List<BasePojo> etfList = null;
            try 
            {
                Type serviceType = ReflectBase.YssGetType("YssSecInformation.Support.dll", "YssSecInformation.Support.Aa.Etf.Service.IEtfService");
                IEtfService etfService = ServiceFactory.createService(serviceType) as IEtfService;
                QueryRes res = new QueryRes();
                Dictionary<string, string> paraDict = new Dictionary<string, string>();
                paraDict.Add("dataClass", PojoUtil<BasePojo>.getPojoShortName("YssSecInformation.Support.Aa.Etf.Pojo.Etf"));
                paraDict.Add("ARRAY_C_PORT_CODE", portCodes);
                res = etfService.queryByCondition(paraDict);
                etfList = res.DataList;
            }
            catch (Exception ye)
            {
                ClsBaseException.DiscardException(ye);
            }

            return etfList;
        }

        /////// <summary>
        /////// list界面加载A区数据，子类重写.
        /////// </summary>
        /////// <returns>返回查询结果.</returns>
        ////public override string yssGetLeftData()
        ////{
        ////    ////return getLeftPortData();
        ////    return getLeftPortData("CLS_ETF");  // MODIFIED BY ZHAOXIANLIN 20130525 STORY#3550
        ////}

        /// <summary>
        /// list界面加载A区数据，子类重写.
        /// </summary>
        /// <returns>返回查询结果.</returns>
        public override QueryRes yssGetLeftDataMVC()
        {
            leftDataFunCode = AssociaType.pd_portfolio.ToString();
            return loadPortData("CLS_ETF");
        }

        /// <summary>
        /// 显示组合信息
        /// add by zhaoxianlin 2013-05-25 story #3550
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void buttonItem2_Click(object sender, EventArgs e)
        {
            try
            {
                ////每次创建组合默认窗体的时候都需要重新给窗体赋值
                this.frmBaseDefPort = new FrmBaseDefPort(this);
                this.frmBaseDefPort.Text = "默认组合设置";
                this.frmBaseDefPort.Tag = dataAdmin.CurFUN.C_FUN_CODE;
                ////string result = null;
                ////result = getCommonUsedData("CLS_ETF");
                ////this.frmBaseDefPort.flag = true;
                ////this.frmBaseDefPort.result = result;
                this.frmBaseDefPort.ShowDialog();
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500002", _formFun, status, ex));
            }
        }

        /// <summary>
        /// 屏蔽双击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbMain_RowDoubleClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
        }
    }
}


