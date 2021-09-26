using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Datastructure;

namespace YssElecReco.Pojo.Er
{
    /// <summary>
    /// 电子对账结果实体对象
    /// </summary>
    [NodeDesc(ParentNode = "C_KM_CODE_P", TreeNode = "C_KM_CODE")]
    public class ErGzb : BasePojo
    {
        /// <summary>
        /// 报文序号
        /// </summary>
        private string c_SN = "";

        /// <summary>
        /// 资金代码
        /// </summary>
        private string c_ASS_CODE = "";

        /// <summary>
        /// 文件类型
        /// </summary>
        private string c_FILE_TYPE = "";

        /// <summary>
        /// 报表类型
        /// </summary>
        private string c_RPT_TYPE = "";

        /// <summary>
        /// 开始日期
        /// </summary>
        private string d_START_DATE = "";

        /// <summary>
        /// 结束日期
        /// </summary>
        private string d_END_DATE = "";

        /// <summary>
        /// 科目编码
        /// </summary>
        private string c_KM_CODE = "";

        /// <summary>
        /// 上级科目代码
        /// </summary>
        private string c_KM_CODE_P = "";

        /// <summary>
        /// 科目名称
        /// </summary>
        private string c_KM_NAME = "";

        /// <summary>
        /// 行情价格
        /// </summary>
        private decimal n_VA_PRICE = 0;

       /// <summary>
        /// 行情标志
       /// </summary>
        private int n_QUOT_LOGO = 1;

        /// <summary>
        /// 证券数量
        /// </summary>
        private decimal n_AMOUNT = 0;

        /// <summary>
        /// 原币证券成本
        /// </summary>
        private decimal n_ORIG_COST = 0;

        /// <summary>
        /// 原币证券市值
        /// </summary>
        private decimal n_ORIG_MV = 0;

        /// <summary>
        /// 原币估值增值
        /// </summary>
        private decimal n_ORIG_IV = 0;

        /// <summary>
        /// 证券成本
        /// </summary>
        private decimal n_PORT_COST = 0;

        /// <summary>
        /// 证券市值
        /// </summary>
        private decimal n_PORT_MV = 0;

        /// <summary>
        /// 估值增值
        /// </summary>
        private decimal n_PORT_IV = 0;

        /// <summary>
        /// 成本占净值比
        /// </summary>
        private string c_CB_JZ_BL = "0";

        /// <summary>
        /// 市值占净值比
        /// </summary>
        private string c_SZ_JZ_BL = "0";

        /// <summary>
        /// 是否明细数据
        /// </summary>
        private int n_DETAIL = 0;

        /// <summary>
        /// 方向
        /// </summary>
        private string c_DV_ER_WAY = "";

        /// <summary>
        /// 普通数据 为“”，TOTAL 项为“TOTAL”，TOTAL_ALL 项为“TOTAL_ALL”
        /// </summary>
        private string navtype = "";

        /// <summary>
        /// 普通数据 为“”，TOTAL 项为“TOTAL”，TOTAL_ALL 项为“TOTAL_ALL”
        /// </summary>
        [JsonProperty(PropertyName = "navtype")]
        public string Navtype
        {
            get { return navtype; }
            set { navtype = value; }
        }

        /// <summary>
        /// 属性: 报文批号
        /// </summary>
        [JsonProperty(PropertyName = "c_SN")]
        public string C_SN
        {
            set { c_SN = value; }
            get { return c_SN; }
        }

        /// <summary>
        /// 属性: 资产代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_ASS_CODE")]
        public string C_ASS_CODE
        {
            set { c_ASS_CODE = value; }
            get { return c_ASS_CODE; }
        }

        /// <summary>
        /// 属性: 文件类型 
        /// </summary>
        [JsonProperty(PropertyName = "c_FILE_TYPE")]
        public string C_FILE_TYPE
        {
            set { c_FILE_TYPE = value; }
            get { return c_FILE_TYPE; }
        }

        /// <summary>
        /// 属性: 报表类型
        /// </summary>
        [JsonProperty(PropertyName = "c_RPT_TYPE")]
        public string C_RPT_TYPE
        {
            set { c_RPT_TYPE = value; }
            get { return c_RPT_TYPE; }
        }

        /// <summary>
        /// 属性: 开始日期 
        /// </summary>
        [JsonProperty(PropertyName = "d_START_DATE")]
        public string D_START_DATE
        {
            set { d_START_DATE = value; }
            get { return d_START_DATE; }
        }

        /// <summary>
        /// 属性: 结束日期 
        /// </summary>
        [JsonProperty(PropertyName = "d_END_DATE")]
        public string D_END_DATE
        {
            set { d_END_DATE = value; }
            get { return d_END_DATE; }
        }

        /// <summary>
        /// 属性: 科目代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_KM_CODE")]
        public string C_KM_CODE
        {
            set { c_KM_CODE = value; }
            get { return c_KM_CODE; }
        }

        /// <summary>
        /// 属性: 上级科目代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_KM_CODE_P")]
        public string C_KM_CODE_P
        {
            set { c_KM_CODE_P = value; }
            get { return c_KM_CODE_P; }
        }

        /// <summary>
        /// 属性: 科目名称 
        /// </summary>
        [JsonProperty(PropertyName = "c_KM_NAME")]
        public string C_KM_NAME
        {
            set { c_KM_NAME = value; }
            get { return c_KM_NAME; }
        }

        /// <summary>
        /// 属性: 行情价格 
        /// </summary>
        [JsonProperty(PropertyName = "n_VA_PRICE")]
        public decimal N_VA_PRICE
        {
            set { n_VA_PRICE = value; }
            get { return n_VA_PRICE; }
        }

        /// <summary>
        /// 属性: 行情标志 
        /// </summary>
        [JsonProperty(PropertyName = "n_QUOT_LOGO")]
        public int N_QUOT_LOGO
        {
            set { n_QUOT_LOGO = value; }
            get { return n_QUOT_LOGO; }
        }

        /// <summary>
        /// 属性: 证券数量 
        /// </summary>
        [JsonProperty(PropertyName = "n_AMOUNT")]
        public decimal N_AMOUNT
        {
            set { n_AMOUNT = value; }
            get { return n_AMOUNT; }
        }

        /// <summary>
        /// 属性: 原币证券成本
        /// </summary>
        [JsonProperty(PropertyName = "n_ORIG_COST")]
        public decimal N_ORIG_COST
        {
            set { n_ORIG_COST = value; }
            get { return n_ORIG_COST; }
        }

        /// <summary>
        /// 属性: 原币证券市值
        /// </summary>
        [JsonProperty(PropertyName = "n_ORIG_MV")]
        public decimal N_ORIG_MV
        {
            set { n_ORIG_MV = value; }
            get { return n_ORIG_MV; }
        }

        /// <summary>
        /// 属性: 原币估值增值
        /// </summary>
        [JsonProperty(PropertyName = "n_ORIG_IV")]
        public decimal N_ORIG_IV
        {
            set { n_ORIG_IV = value; }
            get { return n_ORIG_IV; }
        }

        /// <summary>
        /// 属性: 证券成本
        /// </summary>
        [JsonProperty(PropertyName = "n_PORT_COST")]
        public decimal N_PORT_COST
        {
            set { n_PORT_COST = value; }
            get { return n_PORT_COST; }
        }

        /// <summary>
        /// 属性: 证券市值 
        /// </summary>
        [JsonProperty(PropertyName = "n_PORT_MV")]
        public decimal N_PORT_MV
        {
            set { n_PORT_MV = value; }
            get { return n_PORT_MV; }
        }

        /// <summary>
        /// 属性: 估值增值 
        /// </summary>
        [JsonProperty(PropertyName = "n_PORT_IV")]
        public decimal N_PORT_IV
        {
            set { n_PORT_IV = value; }
            get { return n_PORT_IV; }
        }

        /// <summary>
        /// 属性: 成本占净值比 
        /// </summary>
        [JsonProperty(PropertyName = "c_CB_JZ_BL")]
        public string C_CB_JZ_BL
        {
            set { c_CB_JZ_BL = value; }
            get { return c_CB_JZ_BL; }
        }

        /// <summary>
        /// 属性: 市值占净值比 
        /// </summary>
        [JsonProperty(PropertyName = "c_SZ_JZ_BL")]
        public string C_SZ_JZ_BL
        {
            set { c_SZ_JZ_BL = value; }
            get { return c_SZ_JZ_BL; }
        }

        /// <summary>
        /// 属性: 是否明细数据 
        /// </summary>
        [JsonProperty(PropertyName = "n_DETAIL")]
        public int N_DETAIL
        {
            set { n_DETAIL = value; }
            get { return n_DETAIL; }
        }

        /// <summary>
        /// 属性: 方向 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_ER_WAY")]
        public string C_DV_ER_WAY
        {
            set { c_DV_ER_WAY = value; }
            get { return c_DV_ER_WAY; }
        }
    }
}
