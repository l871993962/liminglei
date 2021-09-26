using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo;

namespace YssElecReco.pojo.Er
{
    /// <summary>
    /// 对账模板
    /// </summary>
    public class DzTemplate : ParamPojo
    {
        /// <summary>
        /// 模板代码
        /// </summary>
        private string c_TMPL_CODE;

        /// <summary>
        /// 模板名称
        /// </summary>
        private string c_TMPL_NAME;

        /// <summary>
        /// 模板路径
        /// </summary>
        private string c_TMPL_PATH;

        /// <summary>
        /// 模板类型
        /// </summary>
        private string c_TMPL_TYPE;

        /// <summary>
        /// 版本号
        /// </summary>
        private string c_VERSION;

        /// <summary>
        /// 模板状态
        /// </summary>
        private string c_DV_TMPL_STATUS;

        /// <summary>
        /// 描述信息
        /// </summary>
        private string c_DESC;

        /// <summary>
        /// 模板代码
        /// </summary>
        [JsonProperty(PropertyName = "c_TMPL_CODE")]
        public string C_TMPL_CODE
        {
            get { return c_TMPL_CODE; }
            set { c_TMPL_CODE = value; }
        }

        /// <summary>
        ///  模板名称
        /// </summary>
        [JsonProperty(PropertyName = "c_TMPL_NAME")]
        public string C_TMPL_NAME
        {
            get { return c_TMPL_NAME; }
            set { c_TMPL_NAME = value; }
        }

        /// <summary>
        ///  模板路径
        /// </summary>
        [JsonProperty(PropertyName = "c_TMPL_PATH")]
        public string C_TMPL_PATH
        {
            get { return c_TMPL_PATH; }
            set { c_TMPL_PATH = value; }
        }

        /// <summary>
        ///  模板类型
        /// </summary>
        [JsonProperty(PropertyName = "c_TMPL_TYPE")]
        public string C_TMPL_TYPE
        {
            get { return c_TMPL_TYPE; }
            set { c_TMPL_TYPE = value; }
        }

        /// <summary>
        ///  版本号
        /// </summary>
        [JsonProperty(PropertyName = "c_VERSION")]
        public string C_VERSION
        {
            get { return c_VERSION; }
            set { c_VERSION = value; }
        }

        /// <summary>
        ///  模板状态
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_TMPL_STATUS")]
        public string C_DV_TMPL_STATUS
        {
            get { return c_DV_TMPL_STATUS; }
            set { c_DV_TMPL_STATUS = value; }
        }

        /// <summary>
        ///  描述信息
        /// </summary>
        [JsonProperty(PropertyName = "c_DESC")]
        public string C_DESC
        {
            get { return c_DESC; }
            set { c_DESC = value; }
        }
    }
}
