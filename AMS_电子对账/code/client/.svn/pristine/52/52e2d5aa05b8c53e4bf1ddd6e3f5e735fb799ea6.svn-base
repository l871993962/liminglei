using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Linq;
using System.Reflection;
using System.Windows.Forms;

using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;

namespace YssElecReco.pojo.Er
{
    /// <summary>
    /// 所有者权益表
    /// </summary>
    public class ErSyzqyb : BasePojo
    {
        /// <summary>
        /// 报文序号
        /// </summary>
        private string c_SN = "";

        /// <summary>
        /// 资产代码
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
        /// 公司代码
        /// </summary>
        private string c_DEPT_CODE = "";

        /// <summary>
        /// 证书代码
        /// </summary>
        private string c_CERT_ID = "";

        /// <summary>
        /// 指标代码
        /// </summary>
        private string c_INDEX_CODE = "";

        /// <summary>
        /// 指标名称
        /// </summary>
        private string c_INDEX_NAME = "";

        /// <summary>
        /// 本期实收基金
        /// </summary>
        private decimal n_THIS_NAV = 0;

        /// <summary>
        /// 本期未分配利润
        /// </summary>
        private decimal n_THIS_UNPROFIT = 0;

        /// <summary>
        /// 本期所有者权益合计
        /// </summary>
        private decimal n_THIS_INTERESTS = 0;

        /// <summary>
        /// 上期实收基金
        /// </summary>
        private decimal n_LAST_NAV = 0;

        /// <summary>
        /// 上期未分配利润
        /// </summary>
        private decimal n_LAST_UNPROFIT = 0;

        /// <summary>
        /// 上期所有者权益
        /// </summary>
        private decimal n_LAST_INTERESTS = 0;

        /// <summary>
        /// 报文序号
        /// </summary>
        [JsonProperty(PropertyName = "c_SN")]
        public string C_SN
        {
            get { return c_SN; }
            set { c_SN = value; }
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
        /// 对账类型
        /// </summary>
        [JsonProperty(PropertyName = "c_FILE_TYPE")]
        public string C_FILE_TYPE
        {
            set { c_FILE_TYPE = value; }
            get { return c_FILE_TYPE; }
        }

        /// <summary>
        /// 报表类型
        /// </summary>
        [JsonProperty(PropertyName = "c_RPT_TYPE")]
        public string C_RPT_TYPE
        {
            set { c_RPT_TYPE = value; }
            get { return c_RPT_TYPE; }
        }

        /// <summary>
        /// 开始日期
        /// </summary>
        [JsonProperty(PropertyName = "d_START_DATE")]
        public string D_START_DATE
        {
            get { return d_START_DATE; }
            set { d_START_DATE = value; }
        }

        /// <summary>
        /// 结束日期
        /// </summary>
        [JsonProperty(PropertyName = "d_END_DATE")]
        public string D_END_DATE
        {
            get { return d_END_DATE; }
            set { d_END_DATE = value; }
        }

        /// <summary>
        /// 公司代码
        /// </summary>
        [JsonProperty(PropertyName = "c_DEPT_CODE")]
        public string C_DEPT_CODE
        {
            get { return c_DEPT_CODE; }
            set { c_DEPT_CODE = value; }
        }

        /// <summary>
        /// 证书ID
        /// </summary>
        [JsonProperty(PropertyName = "c_CERT_ID")]
        public string C_CERT_ID
        {
            get { return c_CERT_ID; }
            set { c_CERT_ID = value; }
        }

        /// <summary>
        /// 指标代码
        /// </summary>
        [JsonProperty(PropertyName = "c_INDEX_CODE")]
        public string C_INDEX_CODE
        {
            get { return c_INDEX_CODE; }
            set { c_INDEX_CODE = value; }
        }

        /// <summary>
        /// 指标名称
        /// </summary>
        [JsonProperty(PropertyName = "c_INDEX_NAME")]
        public string C_INDEX_NAME
        {
            get { return c_INDEX_NAME; }
            set { c_INDEX_NAME = value; }
        }

        /// <summary>
        /// 本期实收基金
        /// </summary>
        [JsonProperty(PropertyName = "n_THIS_NAV")]
        public decimal N_THIS_NAV
        {
            get { return n_THIS_NAV; }
            set { n_THIS_NAV = value; }
        }

        /// <summary>
        /// 本期未分配利润
        /// </summary>
        [JsonProperty(PropertyName = "n_THIS_UNPROFIT")]
        public decimal N_THIS_UNPROFIT
        {
            get { return n_THIS_UNPROFIT; }
            set { n_THIS_UNPROFIT = value; }
        }

        /// <summary>
        /// 本期所有者权益合计
        /// </summary>
        [JsonProperty(PropertyName = "n_THIS_INTERESTS")]
        public decimal N_THIS_INTERESTS
        {
            get { return n_THIS_INTERESTS; }
            set { n_THIS_INTERESTS = value; }
        }

        /// <summary>
        /// 上期实收基金
        /// </summary>
        [JsonProperty(PropertyName = "n_LAST_NAV")]
        public decimal N_LAST_NAV
        {
            get { return n_LAST_NAV; }
            set { n_LAST_NAV = value; }
        }

        /// <summary>
        /// 上期未分配利润
        /// </summary>
        [JsonProperty(PropertyName = "n_LAST_UNPROFIT")]
        public decimal N_LAST_UNPROFIT
        {
            get { return n_LAST_UNPROFIT; }
            set { n_LAST_UNPROFIT = value; }
        }

        /// <summary>
        /// 上期所有者权益
        /// </summary>
        [JsonProperty(PropertyName = "n_LAST_INTERESTS")]
        public decimal N_LAST_INTERESTS
        {
            get { return n_LAST_INTERESTS; }
            set { n_LAST_INTERESTS = value; }
        }
    }
}
