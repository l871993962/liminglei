using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.CRUD.Form;
using FAST.Core.Communication.Service;
using FAST.Common.Service.Pojo;
using YssProductInfo.Support.DataCopy.Service;

namespace YssProductInfo.DataCopy.Form
{
    /// <summary>
    /// STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况
    /// add by zhaijiajia 20190222
    /// 产品参数复制-检查
    /// </summary>
    public partial class Frm_DataCopy_Check : FrmBaseList
    {
        /// <summary>
        /// A区所选中的组合
        /// </summary>
        private string portlist = "";

        /// <summary>
        /// 功能选项服务
        /// </summary>
        private IDataCopyService checkService = null;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_DataCopy_Check()
        {
            this.bUseMVCService = true;
            bUseMVCServiceLeft = true;
            InitializeComponent();
            this.isLoadFirst = true;
            this.ShowLeftPanel = false;
            this.ShowPageInation = true;
            this.ShowRowCheckBoxColumn = false;
        }

        /// <summary>
        /// 主界面传过来的组合
        /// </summary>
        public string PORTLIST
        {
            set
            {
                portlist = value;
            }
        }

        /// <summary>
        /// 初始化服务
        /// </summary>
        protected virtual void initServiceMVC()
        {
            if (checkService == null)
            {
                checkService = ServiceFactory.createService<IDataCopyService>();
            }
        }

        /// <summary>
        /// 获取主界面查询条件区的查询条件
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>paraDict</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            paraDict.Add("ARRAY_C_PORT_CODE", portlist);
            return paraDict;
        }

        /// <summary>
        /// 功能：获取查询结果以供子类复写
        /// </summary>
        /// <param name="paraDict">参数集合</param>
        /// <param name="pageIns">分页信息</param>
        /// <returns>返回结果</returns>
        protected override QueryRes getQueryResultMVC(Dictionary<string, string> paraDict, PageInation pageIns)
        {
            if (checkService == null)
            {
                initServiceMVC();
            }

            return checkService.queryCopyCheckData(paraDict, pageIns);
        }

        /// <summary>
        /// 获取数据总数量
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>总数量</returns>
        protected override int getDataTotal(Dictionary<string, string> paraDict)
        {
            if (checkService == null)
            {
                initServiceMVC();
            }

            string number = checkService.getCopyCheckDataTotal(paraDict);
            int total = Convert.ToInt32(number);
            return total;
        }
    }
}
