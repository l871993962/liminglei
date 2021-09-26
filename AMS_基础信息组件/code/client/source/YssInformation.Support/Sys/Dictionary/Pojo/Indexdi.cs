using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo.Base;
using Newtonsoft.Json;

////namespace FAST.Platform.Dict.Pojo
////namespace YssInformation.Sys.Dictionary.Pojo
namespace YssInformation.Support.Sys.Dictionary.Pojo
{
    /// <summary>
    /// 合规指标类型字典 POJO
    /// </summary>
    public class Indexdi : BasePojo
    {
        /// <summary>
        /// c_INDEX_CODE
        /// </summary>
        private string c_INDEX_CODE;

        /// <summary>
        /// c_INDEX_NAME
        /// </summary>
        private string c_INDEX_NAME;

        /// <summary>
        /// c_DATA_SOURCE
        /// </summary>
        private string c_DATA_SOURCE;

        /// <summary>
        /// c_DATA_TYPE
        /// </summary>
        private string c_DATA_TYPE;

        /// <summary>
        /// n_STATE
        /// </summary>
        private string n_STATE;

        /// <summary>
        /// n_ORDER
        /// </summary>
        private string n_ORDER;

        /// <summary>
        /// n_NAV_TYPE
        /// </summary>
        private string c_NAV_TYPE;

        /// <summary>
        /// n_DETAIL
        /// </summary>
        private string n_DETAIL;

        /// <summary>
        /// c_KEY_CODE
        /// </summary>
        private string c_KEY_CODE;

        /// <summary>
        /// c_KEY_NAME
        /// </summary>
        private string c_KEY_NAME;

        /// <summary>
        /// c_IS_SYS
        /// </summary>
        private string c_IS_SYS;

        /// <summary>
        /// c_TRU
        /// </summary>
        private string c_TRU;

        /// <summary>
        /// c_MODE
        /// </summary>
        private string c_MODE;

        /// <summary>
        /// c_RET
        /// </summary>
        private string c_RET;

        /// <summary>
        /// 属性: C_INDEX_CODE 
        /// </summary>
        [JsonProperty(PropertyName = "c_INDEX_CODE")]
        public string C_INDEX_CODE
        {
            set { c_INDEX_CODE = value; }

            get { return c_INDEX_CODE; }
        }

        /// <summary>
        /// 属性: C_INDEX_NAME 
        /// </summary>
        [JsonProperty(PropertyName = "c_INDEX_NAME")]
        public string C_INDEX_NAME
        {
            set { c_INDEX_NAME = value; }

            get { return c_INDEX_NAME; }
        }

        /// <summary>
        /// 属性: C_DATA_SOURCE 
        /// </summary>
        [JsonProperty(PropertyName = "c_DATA_SOURCE")]
        public string C_DATA_SOURCE
        {
            set { c_DATA_SOURCE = value; }

            get { return c_DATA_SOURCE; }
        }

        /// <summary>
        /// 属性: C_DATA_TYPE 
        /// </summary>
        [JsonProperty(PropertyName = "c_DATA_TYPE")]
        public string C_DATA_TYPE
        {
            set { c_DATA_TYPE = value; }

            get { return c_DATA_TYPE; }
        }

        /// <summary>
        /// 属性: N_STATE 
        /// </summary>
        [JsonProperty(PropertyName = "n_STATE")]
        public string N_STATE
        {
            set { n_STATE = value; }

            get { return n_STATE; }
        }

        /// <summary>
        /// 属性: N_ORDER 
        /// </summary>
        [JsonProperty(PropertyName = "n_ORDER")]
        public string N_ORDER
        {
            set { n_ORDER = value; }

            get { return n_ORDER; }
        }

        /// <summary>
        /// 属性: N_NAV_TYPE 
        /// </summary>
        [JsonProperty(PropertyName = "c_NAV_TYPE")]
        public string C_NAV_TYPE
        {
            set { c_NAV_TYPE = value; }

            get { return c_NAV_TYPE; }
        }

        /// <summary>
        /// 属性: N_DETAIL 
        /// </summary>
        [JsonProperty(PropertyName = "n_DETAIL")]
        public string N_DETAIL
        {
            set { n_DETAIL = value; }

            get { return n_DETAIL; }
        }

        /// <summary>
        /// 属性: C_KEY_CODE 
        /// </summary>
        [JsonProperty(PropertyName = "c_KEY_CODE")]
        public string C_KEY_CODE
        {
            set { c_KEY_CODE = value; }

            get { return c_KEY_CODE; }
        }

        /// <summary>
        /// 属性: C_KEY_NAME 
        /// </summary>
        [JsonProperty(PropertyName = "c_KEY_NAME")]
        public string C_KEY_NAME
        {
            set { c_KEY_NAME = value; }

            get { return c_KEY_NAME; }
        }

        /// <summary>
        /// 属性: C_IS_SYS 
        /// </summary>
        [JsonProperty(PropertyName = "c_IS_SYS")]
        public string C_IS_SYS
        {
            set { c_IS_SYS = value; }

            get { return c_IS_SYS; }
        }

        /// <summary>
        /// 属性: C_TRU 
        /// </summary>
        [JsonProperty(PropertyName = "c_TRU")]
        public string C_TRU
        {
            set { c_TRU = value; }

            get { return c_TRU; }
        }

        /// <summary>
        /// 属性: C_MODE 
        /// </summary>
        [JsonProperty(PropertyName = "c_MODE")]
        public string C_MODE
        {
            set { c_MODE = value; }

            get { return c_MODE; }
        }

        /// <summary>
        /// 属性: C_RET 
        /// </summary>
        [JsonProperty(PropertyName = "c_RET")]
        public string C_RET
        {
            set { c_RET = value; }

            get { return c_RET; }
        }

    }
}
