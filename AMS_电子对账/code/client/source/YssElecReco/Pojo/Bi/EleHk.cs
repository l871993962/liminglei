using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;

namespace YssElecReco.Pojo.Bi
{
    /// <summary>
    /// 电子划款信息设置实体bean
    /// 创建人：陈尤龙
    /// 日期：20121103
    /// 版本号：V1.0.04
    /// </summary>
    public class EleHk : AuditableParamPojo
    {
        /// <summary>
        /// 机构代码
        /// </summary>
        private string c_ORG_CODE = "";

        /// <summary>
        /// 划款类型
        /// </summary>
        private string c_PAY_TYPE = "";

        /// <summary>
        /// 划款代码
        /// </summary>
        private string c_PAY_CODE = "";

        /// <summary>
        /// 报文类型
        /// </summary>
        private string c_OPEN_ACC_NO = "";

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 属性: C_ORG_CODE 
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_CODE")]
        public string C_ORG_CODE
        {
            set { c_ORG_CODE = value; }
            get { return c_ORG_CODE; }
        }

        /// <summary>
        /// 属性: C_PAY_TYPE 
        /// </summary>
        [JsonProperty(PropertyName = "c_PAY_TYPE")]
        public string C_PAY_TYPE
        {
            set { c_PAY_TYPE = value; }
            get { return c_PAY_TYPE; }
        }

        /// <summary>
        /// 属性: C_PAY_CODE 
        /// </summary>
        [JsonProperty(PropertyName = "c_PAY_CODE")]
        public string C_PAY_CODE
        {
            set { c_PAY_CODE = value; }
            get { return c_PAY_CODE; }
        }

        /// <summary>
        /// 属性: C_OPEN_ACC_NO 
        /// </summary>
        [JsonProperty(PropertyName = "c_OPEN_ACC_NO")]
        public string C_OPEN_ACC_NO
        {
            set { c_OPEN_ACC_NO = value; }
            get { return c_OPEN_ACC_NO; }
        }

        /// <summary>
        /// 属性: C_DESC 
        /// </summary>
        [JsonProperty(PropertyName = "c_DESC")]
        public string C_DESC
        {
            set { c_DESC = value; }
            get { return c_DESC; }
        }


    }
}
