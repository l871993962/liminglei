using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using YssElecReco.Service.Bi;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Pojo;
using FAST.Core.Context;
using FAST.Core.Util;
using FAST.Core.Communication.Service;
using FAST.Core.Communication.DataService;
using FAST.Core.CRUD.Interface;
using FAST.Common.Service.Pojo.Base;
using YssElecReco.Pojo.Bi;
using YssElecReco.Service.Er;

namespace YssElecReco.Form.Bi
{
    /// <summary>
    /// 电子对账指标关联--公共指标
    /// </summary>
    public partial class Frm_ELEC_RELA_PUB_L : FrmBaseListWithDetails
    {
        /// <summary>
        /// 声明电子划款信息服务对象
        /// </summary>
        private IElecRelaService elecRelaService = null;

        /// <summary>
        /// 获取或设置一个值，该值为主窗体传过来的数据。当该窗体为某一窗体的附属窗体时有效。
        /// </summary>
        private BasePojo _mainDataPojo;

        /// <summary>
        /// 获取或设置当前窗体是否已经被FrmBaseListWidthDetails窗体关联为子窗体
        /// </summary>
        private bool _hadBeenRelationed = false;

        /// <summary>
        ///  电子对账
        /// </summary>
        public Frm_ELEC_RELA_PUB_L()
        {
            InitializeComponent();
            this.bUseMVCServiceLeft = true;
            this.bUseMVCService = true;
            ////BUG218422【对账指标关联】导致系统未响应
            //ShowLeftPanel = false;
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
        /// list界面加载A区数据，子类重写.
        /// BUG217623【产生电子对账】生成余额表和估值表导致系统崩溃
        /// </summary>
        /// <author>yh 2011.02.28.</author>
        /// <returns>返回查询结果.</returns>
        public override QueryRes yssGetLeftDataMVC()
        {
            string funCode = "dzcode"; // 要获取数据的功能代码
            string headKeys = "C_DZ_CODE~!~C_DZ_NAME"; // 自定义列头,此时为词汇类型代码
            // 获取数据类型
            string cacheType = "";
            string result = null;
            //// 设定左侧数据的加载方式
            YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;
            IErDzTypeDataService iDzTypeDataService = DataServiceFactory.createService<IErDzTypeDataService>();
            QueryRes res = iDzTypeDataService.getDataListRes();
            //// 保证A区列头名称在代码之前
            res.ListHeadList = reversalHeadList(res, "C_DZ_CODE", "C_DZ_NAME");
            this.matchSearchStr = new string[2] { "C_DZ_CODE", "C_DZ_NAME" }; // 【搜索】功能匹配的属性
            return res;
        }

        /// <summary>
        /// 重载
        /// </summary>
        /// <returns>List</returns>
        protected override List<SysFun> LoadDetailFormFuns()
        {
            List<SysFun> funList = new List<SysFun>();
            ////BUG201589对账指标关联页面打开报错并且没有指标关联设置界面
            if (ClsContext.sysMenuFunHash.ContainsKey("dzPerRela"))
            {
                SysFun fun = ClsContext.sysMenuFunHash["dzPerRela"] as SysFun;
                funList.Add(fun);
            }

            return funList;
        }

        /// <summary>
        /// 电子对账指标关联浏览窗体加载事件
        /// 用于初始化IElecRelaService 对象
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ELEC_RELA_PUB_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.elecRelaService = ServiceFactory.createService(serviceType) as IElecRelaService;
            this.dataService = this.elecRelaService;
        }

        /// <summary>
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写.
        /// </summary>
        ///  <returns>返回查询条件.</returns>
        public override string yssInitQuery()
        {
            ////所有提供的参数项如下，只需要设置子类需要的项即可
            //// 1 查询条件
            string cond = "";

            //// 3 初始只加载列头，若需要则设为true，反之不需要设置此参数
            IsOnlyHeder = true;

            return cond;
        }

        /// <summary>
        /// 封装条件对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            if (this.txtZbCode.Text.Trim().Length > 0)
            {
                paraDict.Add("C_ZB_CODE", "%" + this.txtZbCode.Text.Trim() + "%");
            }

            if (this.txtZbName.Text.Trim().Length > 0)
            {
                paraDict.Add("C_ZB_NAME", "%" + this.txtZbName.Text.Trim() + "%");
            }
            ////BUG218422【对账指标关联】导致系统未响应
            ////if (this.ParentForm != null && this.ParentForm is FrmBaseList)
            ////{
                string search = this.yssBuildLeftCheckRowsStr("dzcode");
                search = search.Replace("'", "");
                paraDict.Add("ARRAY_C_DZ_CODE", search);
            ////}
            return paraDict;
        }

        /// <summary>
        /// 指标名称改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void txtZbName_TextChanged(object sender, EventArgs e)
        {
            //首先将selCombox中元素清空
            (sender as FAST.Core.BaseControl.YssSelCombox).Items.Clear();
            string cZbName = (sender as FAST.Core.BaseControl.YssSelCombox).Text;
            List<string> paraList = new List<string>();
            if (!string.IsNullOrEmpty(cZbName))
            {
                string queryCon = queryCon = "%" + cZbName + "%";
                paraList.Add(queryCon);
            }

            List<BasePojo> lDzRelaList = elecRelaService.getDataListByName(paraList);
            if (null != lDzRelaList && lDzRelaList.Count > 0)
            {
                foreach (BasePojo pojo in lDzRelaList)
                {
                    if (pojo is ElecRela)
                    {
                        Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(pojo as ElecRela);
                        (sender as FAST.Core.BaseControl.YssSelCombox).Items.Add(entity);
                    }
                }
            }
        }

    }
}