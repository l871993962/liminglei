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
    public class ErKmb : BasePojo
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
        /// 科目代码
        /// </summary>
        private string c_KM_CODE = "";

        /// <summary>
        /// 科目名称
        /// </summary>
        private string c_KM_NAME = "";

        /// <summary>
        /// 科目级别
        /// </summary>
        private string c_KM_LEVEL = "";

        /// <summary>
        /// 上级科目代码
        /// </summary>
        private string c_KM_CODE_P = "";

        /// <summary>
        /// 日期
        /// </summary>
        private string d_DATE = "";

        /// <summary>
        /// 科目类别
        /// </summary>
        private string c_DV_KM_CLS = "";

        /// <summary>
        /// 方向
        /// </summary>
        private string c_DV_ER_WAY = "";

        /// <summary>
        /// 余额方向
        /// </summary>
        private string c_DV_JD_WAY = "";

        /// <summary>
        /// 是否明细科目
        /// </summary>
        private int n_DETAIL = 0;

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
        /// 属性: 资金代码 
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
        /// 属性: 科目名称 
        /// </summary>
        [JsonProperty(PropertyName = "c_KM_NAME")]
        public string C_KM_NAME
        {
            set { c_KM_NAME = value; }
            get { return c_KM_NAME; }
        }

        /// <summary>
        /// 属性: 科目级别 
        /// </summary>
        [JsonProperty(PropertyName = "c_KM_LEVEL")]
        public string C_KM_LEVEL
        {
            set { c_KM_LEVEL = value; }
            get { return c_KM_LEVEL; }
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
        /// 属性: 日期 
        /// </summary>
        [JsonProperty(PropertyName = "d_DATE")]
        public string D_DATE
        {
            set { d_DATE = value; }
            get { return d_DATE; }
        }

        /// <summary>
        /// 属性: 科目类别 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_KM_CLS")]
        public string C_DV_KM_CLS
        {
            set { c_DV_KM_CLS = value; }
            get { return c_DV_KM_CLS; }
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

        /// <summary>
        /// 属性: 余额方向 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_JD_WAY")]
        public string C_DV_JD_WAY
        {
            set { c_DV_JD_WAY = value; }
            get { return c_DV_JD_WAY; }
        }

        /// <summary>
        /// 属性: 是否明细科目 
        /// </summary>
        [JsonProperty(PropertyName = "n_DETAIL")]
        public int N_DETAIL
        {
            set { n_DETAIL = value; }
            get { return n_DETAIL; }
        }
    }
}
