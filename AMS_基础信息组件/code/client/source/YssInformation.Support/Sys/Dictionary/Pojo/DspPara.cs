using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;

////namespace FAST.Platform.Dict.Pojo
////namespace YssInformation.Sys.Dictionary.Pojo
namespace YssInformation.Support.Sys.Dictionary.Pojo
{
    /// <summary>
    /// 核算参数字典
    /// </summary>
    public class DspPara : BasePojo
    {
        /// <summary>
        /// 参数代码
        /// </summary>
        private string c_DSP_CODE;

        /// <summary>
        /// 参数名称
        /// </summary>
        private string c_DSP_NAME;

        /// <summary>
        /// 分类
        /// </summary>
        private string c_DSP_CLASS;

        /// <summary>
        /// 参数类型代码
        /// </summary>
        private string c_DSP_GROUP_CODE;

        /// <summary>
        /// 参数类型
        /// </summary>
        private string c_DSP_VALUE_TYPE;

        /// <summary>
        /// 词汇类型
        /// </summary>
        private string c_DV_TYPE;

        /// <summary>
        /// 参数分组代码
        /// </summary>
        private string c_DSG_CODE;

        /// <summary>
        /// 参数分组代码
        /// </summary>
        private string c_DSG_NAME;

        private string c_DV_PLAT_VALUE;

        private string c_DESC;

        /// <summary>
        /// 参数类型名称
        /// </summary>
        private string c_DSP_GROUP_NAME;

        /// <summary>
        /// 资产类型
        /// </summary>
        private string c_DAT_CODE;

        /// <summary>
        /// 缓存来源
        /// </summary>
        private string c_DS_TPYE;

        /// <summary>
        /// 属性: C_DSP_CODE 
        /// </summary>
        [JsonProperty(PropertyName = "c_DSP_CODE")]
        public string C_DSP_CODE
        {
            set { c_DSP_CODE = value; }

            get { return c_DSP_CODE; }
        }

        /// <summary>
        /// 属性: C_DSP_NAME 
        /// </summary>
        [JsonProperty(PropertyName = "c_DSP_NAME")]
        public string C_DSP_NAME
        {
            set { c_DSP_NAME = value; }

            get { return c_DSP_NAME; }
        }

        /// <summary>
        /// 属性: C_DSP_CLASS 
        /// </summary>
        [JsonProperty(PropertyName = "c_DSP_CLASS")]
        public string C_DSP_CLASS
        {
            set { c_DSP_CLASS = value; }

            get { return c_DSP_CLASS; }
        }

        /// <summary>
        /// 属性: C_DSP_GROUP_CODE 
        /// </summary>
        [JsonProperty(PropertyName = "c_DSP_GROUP_CODE")]
        public string C_DSP_GROUP_CODE
        {
            set { c_DSP_GROUP_CODE = value; }

            get { return c_DSP_GROUP_CODE; }
        }

        /// <summary>
        /// 属性: C_DSP_VALUE_TYPE 
        /// </summary>
        [JsonProperty(PropertyName = "c_DSP_VALUE_TYPE")]
        public string C_DSP_VALUE_TYPE
        {
            set { c_DSP_VALUE_TYPE = value; }

            get { return c_DSP_VALUE_TYPE; }
        }

        /// <summary>
        /// 属性: C_DV_TYPE 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_TYPE")]
        public string C_DV_TYPE
        {
            set { c_DV_TYPE = value; }

            get { return c_DV_TYPE; }
        }

        /// <summary>
        /// 属性: C_DSG_CODE 
        /// </summary>
        [JsonProperty(PropertyName = "c_DSG_CODE")]
        public string C_DSG_CODE
        {
            set { c_DSG_CODE = value; }

            get { return c_DSG_CODE; }
        }

        /// <summary>
        /// 属性: C_DSG_NAME 
        /// </summary>
        [JsonProperty(PropertyName = "c_DSG_NAME")]
        public string C_DSG_NAME
        {
            set { c_DSG_NAME = value; }

            get { return c_DSG_NAME; }
        }

        /// <summary>
        /// 属性: C_DV_PLAT_VALUE 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_PLAT_VALUE")]
        public string C_DV_PLAT_VALUE
        {
            set { c_DV_PLAT_VALUE = value; }

            get { return c_DV_PLAT_VALUE; }
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

        /// <summary>
        /// 属性: C_DSP_GROUP_NAME 
        /// </summary>
        [JsonProperty(PropertyName = "c_DSP_GROUP_NAME")]
        public string C_DSP_GROUP_NAME
        {
            set { c_DSP_GROUP_NAME = value; }

            get { return c_DSP_GROUP_NAME; }
        }

        /// <summary>
        /// 属性: C_DAT_CODE 
        /// </summary>
        [JsonProperty(PropertyName = "c_DAT_CODE")]
        public string C_DAT_CODE
        {
            set { c_DAT_CODE = value; }

            get { return c_DAT_CODE; }
        }

        /// <summary>
        /// 属性: C_DS_TPYE 
        /// </summary>
        [JsonProperty(PropertyName = "c_DS_TPYE")]
        public string C_DS_TPYE
        {
            set { c_DS_TPYE = value; }

            get { return c_DS_TPYE; }
        }

    }
}
