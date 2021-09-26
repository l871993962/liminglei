using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo.Base;
using Newtonsoft.Json;

namespace YssElecReco.Pojo.Er
{
    /// <summary>
    /// 状态图
    /// </summary>
    public class ErStepState : AuditableParamPojo
    {
        /// <summary>
        /// 报文序号
        /// </summary>
        private string c_SN = "";

        /// <summary>
        /// 发送状态
        /// </summary>
        private string c_STATE = "";

        /// <summary>
        /// 文件类型
        /// </summary>
        private string c_FILE_TYPE = "";

        /// <summary>
        /// 报表类型
        /// </summary>
        private string c_RPT_TYPE = "";

        /// <summary>
        /// 基金代码
        /// </summary>
        private string c_ASS_CODE = "";

        /// <summary>
        /// 对账日期
        /// </summary>
        private string d_DATE = "";

        /// <summary>
        /// 状态历史日期
        /// </summary>
        private string c_STEP_DATE = "";

        /// <summary>
        /// 状态历史日期
        /// </summary>
        private string c_ERR = "";

        /// <summary>
        /// 属性: c_ERR 
        /// </summary>
        [JsonProperty(PropertyName = "c_ERR")]
        public string C_ERR
        {
            set { c_ERR = value; }
            get { return c_ERR; }
        }

        /// <summary>
        /// 属性: c_STEP_DATE 
        /// </summary>
        [JsonProperty(PropertyName = "c_STEP_DATE")]
        public string C_STEP_DATE
        {
            set { c_STEP_DATE = value; }
            get { return c_STEP_DATE; }
        }

        /// <summary>
        /// 属性: d_DATE 
        /// </summary>
        [JsonProperty(PropertyName = "d_DATE")]
        public string D_DATE
        {
            set { d_DATE = value; }
            get { return d_DATE; }
        }

        /// <summary>
        /// 属性: c_SN 
        /// </summary>
        [JsonProperty(PropertyName = "c_SN")]
        public string C_SN
        {
            set { c_SN = value; }
            get { return c_SN; }
        }

        /// <summary>
        /// 属性: c_STATE
        /// </summary>
        [JsonProperty(PropertyName = "c_STATE")]
        public string C_STATE
        {
            set { c_STATE = value; }
            get { return c_STATE; }
        }

        /// <summary>
        /// 属性: c_FILE_TYPE
        /// </summary>
        [JsonProperty(PropertyName = "c_FILE_TYPE")]
        public string C_FILE_TYPE
        {
            set { c_FILE_TYPE = value; }
            get { return c_FILE_TYPE; }
        }

        /// <summary>
        /// 属性: c_RPT_TYPE
        /// </summary>
        [JsonProperty(PropertyName = "c_RPT_TYPE")]
        public string C_RPT_TYPE
        {
            set { c_RPT_TYPE = value; }
            get { return c_RPT_TYPE; }
        }

        /// <summary>
        /// 属性: c_PORT_CODE
        /// </summary>
        [JsonProperty(PropertyName = "c_ASS_CODE")]
        public string C_ASS_CODE
        {
            set { c_ASS_CODE = value; }
            get { return c_ASS_CODE; }
        }
    }
}
