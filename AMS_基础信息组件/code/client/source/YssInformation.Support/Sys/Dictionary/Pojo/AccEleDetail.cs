using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;

////namespace YssBaseCls.Pojo
namespace YssInformation.Support.Sys.Dictionary.Pojo
{
    /// <summary> 
    /// 核算项目树节点描述
    /// add by Yuntao Lau 2015.12.01
    /// </summary>
    public class AccEleDetail : BasePojo
    {
        /// <summary>
        /// 核算项目代码
        /// </summary>
        private string c_DAI_CODE = "";

        /// <summary>
        /// 核算元素代码
        /// </summary>
        private string c_DAE_CODE = "";

        /// <summary>
        /// 大类
        /// </summary>
        private string c_DAE_CODE_SUB = "";

        /// <summary>
        /// 核算元素代码
        /// </summary>
        private string c_DAE_CODE1 = "";

        /// <summary>
        /// 核算元素代码
        /// </summary>
        private string c_DAE_CODE2 = "";

        /// <summary>
        /// 核算元素代码
        /// </summary>
        private string c_DAE_CODE3 = "";

        /// <summary>
        /// 核算元素代码
        /// </summary>
        private string c_DAE_CODE4 = "";

        /// <summary>
        /// 核算元素代码
        /// </summary>
        private string c_DAE_CODE5 = "";

        /// <summary>
        /// 核算元素代码
        /// </summary>
        private string c_DAE_CODE6 = "";

        /// <summary>
        /// 核算元素代码
        /// </summary>
        private string c_DAE_CODE7 = "";

        /// <summary>
        /// 核算元素代码
        /// </summary>
        private string c_DAE_CODE8 = "";

        /// <summary>
        /// 加载方式
        /// </summary>
        private string c_LOAD_MODE = "";

        /// <summary>
        /// 属性: 核算项目代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_DAI_CODE")]
        public string C_DAI_CODE
        {
            set { c_DAI_CODE = value; }

            get { return c_DAI_CODE; }
        }

        /// <summary>
        /// 属性: 核算元素代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_DAE_CODE")]
        public string C_DAE_CODE
        {
            set { c_DAE_CODE = value; }

            get { return c_DAE_CODE; }
        }

        /// <summary>
        /// 属性: 大类
        /// </summary>
        [JsonProperty(PropertyName = "c_DAE_CODE_SUB")]
        public string C_DAE_CODE_SUB
        {
            set { c_DAE_CODE_SUB = value; }

            get { return c_DAE_CODE_SUB; }
        }

        /// <summary>
        /// 属性: 核算元素代码
        /// </summary>
        [JsonProperty(PropertyName = "c_DAE_CODE1")]
        public string C_DAE_CODE1
        {
            set { c_DAE_CODE1 = value; }

            get { return c_DAE_CODE1; }
        }

        /// <summary>
        /// 属性: 核算元素代码
        /// </summary>
        [JsonProperty(PropertyName = "c_DAE_CODE2")]
        public string C_DAE_CODE2
        {
            set { c_DAE_CODE2 = value; }

            get { return c_DAE_CODE2; }
        }

        /// <summary>
        /// 属性: 核算元素代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_DAE_CODE3")]
        public string C_DAE_CODE3
        {
            set { c_DAE_CODE3 = value; }

            get { return c_DAE_CODE3; }
        }

        /// <summary>
        /// 属性: 核算元素代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_DAE_CODE4")]
        public string C_DAE_CODE4
        {
            set { c_DAE_CODE4 = value; }

            get { return c_DAE_CODE4; }
        }

        /// <summary>
        /// 属性: 核算元素代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_DAE_CODE5")]
        public string C_DAE_CODE5
        {
            set { c_DAE_CODE5 = value; }

            get { return c_DAE_CODE5; }
        }

        /// <summary>
        /// 属性: 核算元素代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_DAE_CODE6")]
        public string C_DAI_CODE6
        {
            set { c_DAE_CODE6 = value; }

            get { return c_DAE_CODE6; }
        }

        /// <summary>
        /// 属性: 核算元素代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_DAE_CODE7")]
        public string C_DAI_CODE7
        {
            set { c_DAE_CODE7 = value; }

            get { return c_DAE_CODE7; }
        }

        /// <summary>
        /// 属性: 核算项目代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_DAI_CODE8")]
        public string C_DAI_CODE8
        {
            set { c_DAE_CODE8 = value; }

            get { return c_DAE_CODE8; }
        }

        /// <summary>
        /// 属性: 加载方式 
        /// </summary>
        [JsonProperty(PropertyName = "c_LOAD_MODE")]
        public string C_LOAD_MODE
        {
            set { c_LOAD_MODE = value; }

            get { return c_LOAD_MODE; }
        }
    }
}
