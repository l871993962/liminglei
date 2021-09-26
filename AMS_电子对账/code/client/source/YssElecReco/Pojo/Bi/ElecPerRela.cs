using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo.Base;
using Newtonsoft.Json;

namespace YssElecReco.Pojo.Bi
{
    /// <summary>
    /// 个性指标设置pojo
    /// </summary>
    public class ElecPerRela : AuditableParamPojo
    {
        /// <summary>
        /// 指标代码
        /// </summary>
        private string c_ZB_CODE = "";

        /// <summary>
        /// 指标名称
        /// </summary>
        private string c_ZB_NAME = "";

        /// <summary>
        /// 对账类型
        /// </summary>
        private string c_DZ_CODE = "";

        /// <summary>
        /// 组合
        /// </summary>
        private string c_PORT_CODE = "";

        /// <summary>
        /// 指标元素
        /// </summary>
        private string c_SEND_MODE = "";

        ///// <summary>
        ///// 指标元素值
        ///// </summary>
        //private string c_ZB_VALUE = "";

        /// <summary>
        /// 指标元素1
        /// </summary>
        private string c_ZB_ELEMENT1 = "";

        /// <summary>
        /// 指标元素值1
        /// </summary>
        private string c_ZB_VALUE1 = "";

        /// <summary>
        /// 指标元素2
        /// </summary>
        private string c_ZB_ELEMENT2 = "";

        /// <summary>
        /// 指标元素值2
        /// </summary>
        private string c_ZB_VALUE2 = "";

        /// <summary>
        /// 指标元素3
        /// </summary>
        private string c_ZB_ELEMENT3 = "";

        /// <summary>
        /// 指标元素值3
        /// </summary>
        private string c_ZB_VALUE3 = "";

        /// <summary>
        /// 指标元素4
        /// </summary>
        private string c_ZB_ELEMENT4 = "";

        /// <summary>
        /// 指标元素值4
        /// </summary>
        private string c_ZB_VALUE4 = "";

        /// <summary>
        /// 指标元素5
        /// </summary>
        private string c_ZB_ELEMENT5 = "";

        /// <summary>
        /// 指标元素值5
        /// </summary>
        private string c_ZB_VALUE5 = "";

        /// <summary>
        /// 指标元素6
        /// </summary>
        private string c_ZB_ELEMENT6 = "";

        /// <summary>
        /// 指标元素值6
        /// </summary>
        private string c_ZB_VALUE6 = "";

        /// <summary>
        /// 指标元素7
        /// </summary>
        private string c_ZB_ELEMENT7 = "";

        /// <summary>
        /// 指标元素值7
        /// </summary>
        private string c_ZB_VALUE7 = "";

        /// <summary>
        /// 指标元素8
        /// </summary>
        private string c_ZB_ELEMENT8 = "";

        /// <summary>
        /// 指标元素值8
        /// </summary>
        private string c_ZB_VALUE8 = "";

        /// <summary>
        /// 指标元素9
        /// </summary>
        private string c_ZB_ELEMENT9 = "";

        /// <summary>
        /// 指标元素值9
        /// </summary>
        private string c_ZB_VALUE9 = "";

        /// <summary>
        /// 指标元素10
        /// </summary>
        private string c_ZB_ELEMENT10 = "";

        /// <summary>
        /// 指标元素值10
        /// </summary>
        private string c_ZB_VALUE10 = "";

        /// <summary>
        /// 属性： C_ZB_ELEMENT1
        /// </summary>
        [JsonProperty(PropertyName = "c_ZB_ELEMENT1")]
        public string C_ZB_ELEMENT1
        {
            get { return c_ZB_ELEMENT1; }
            set { c_ZB_ELEMENT1 = value; }
        }

        /// <summary>
        /// 属性： C_ZB_VALUE1
        /// </summary>
        [JsonProperty(PropertyName = "c_ZB_VALUE1")]
        public string C_ZB_VALUE1
        {
            get { return c_ZB_VALUE1; }
            set { c_ZB_VALUE1 = value; }
        }

        /// <summary>
        /// 属性： C_ZB_ELEMENT2
        /// </summary>
        [JsonProperty(PropertyName = "c_ZB_ELEMENT2")]
        public string C_ZB_ELEMENT2
        {
            get { return c_ZB_ELEMENT2; }
            set { c_ZB_ELEMENT2 = value; }
        }

        /// <summary>
        /// 属性： C_ZB_VALUE2
        /// </summary>
        [JsonProperty(PropertyName = "c_ZB_VALUE2")]
        public string C_ZB_VALUE2
        {
            get { return c_ZB_VALUE2; }
            set { c_ZB_VALUE2 = value; }
        }

        /// <summary>
        /// 属性： C_ZB_ELEMENT3
        /// </summary>
        [JsonProperty(PropertyName = "c_ZB_ELEMENT3")]
        public string C_ZB_ELEMENT3
        {
            get { return c_ZB_ELEMENT3; }
            set { c_ZB_ELEMENT3 = value; }
        }

        /// <summary>
        /// 属性： C_ZB_VALUE1
        /// </summary>
        [JsonProperty(PropertyName = "c_ZB_VALUE3")]
        public string C_ZB_VALUE3
        {
            get { return c_ZB_VALUE3; }
            set { c_ZB_VALUE3 = value; }
        }

        /// <summary>
        /// 属性： C_ZB_ELEMENT1
        /// </summary>
        [JsonProperty(PropertyName = "c_ZB_ELEMENT4")]
        public string C_ZB_ELEMENT4
        {
            get { return c_ZB_ELEMENT4; }
            set { c_ZB_ELEMENT4 = value; }
        }

        /// <summary>
        /// 属性： C_ZB_VALUE1
        /// </summary>
        [JsonProperty(PropertyName = "c_ZB_VALUE4")]
        public string C_ZB_VALUE4
        {
            get { return c_ZB_VALUE4; }
            set { c_ZB_VALUE4 = value; }
        }

        /// <summary>
        /// 属性： C_ZB_ELEMENT1
        /// </summary>
        [JsonProperty(PropertyName = "c_ZB_ELEMENT5")]
        public string C_ZB_ELEMENT5
        {
            get { return c_ZB_ELEMENT5; }
            set { c_ZB_ELEMENT5 = value; }
        }

        /// <summary>
        /// 属性： C_ZB_VALUE5
        /// </summary>
        [JsonProperty(PropertyName = "c_ZB_VALUE5")]
        public string C_ZB_VALUE5
        {
            get { return c_ZB_VALUE5; }
            set { c_ZB_VALUE5 = value; }
        }

        /// <summary>
        /// 属性： C_ZB_ELEMENT6
        /// </summary>
        [JsonProperty(PropertyName = "c_ZB_ELEMENT6")]
        public string C_ZB_ELEMENT6
        {
            get { return c_ZB_ELEMENT6; }
            set { c_ZB_ELEMENT6 = value; }
        }

        /// <summary>
        /// 属性： C_ZB_VALUE6
        /// </summary>
        [JsonProperty(PropertyName = "c_ZB_VALUE6")]
        public string C_ZB_VALUE6
        {
            get { return c_ZB_VALUE6; }
            set { c_ZB_VALUE6 = value; }
        }

        /// <summary>
        /// 属性： C_ZB_ELEMENT7
        /// </summary>
        [JsonProperty(PropertyName = "c_ZB_ELEMENT7")]
        public string C_ZB_ELEMENT7
        {
            get { return c_ZB_ELEMENT7; }
            set { c_ZB_ELEMENT7 = value; }
        }

        /// <summary>
        /// 属性： C_ZB_VALUE7
        /// </summary>
        [JsonProperty(PropertyName = "c_ZB_VALUE7")]
        public string C_ZB_VALUE7
        {
            get { return c_ZB_VALUE7; }
            set { c_ZB_VALUE7 = value; }
        }

        /// <summary>
        /// 属性： C_ZB_ELEMENT8
        /// </summary>
        [JsonProperty(PropertyName = "c_ZB_ELEMENT8")]
        public string C_ZB_ELEMENT8
        {
            get { return c_ZB_ELEMENT8; }
            set { c_ZB_ELEMENT8 = value; }
        }

        /// <summary>
        /// 属性： C_ZB_VALUE8
        /// </summary>
        [JsonProperty(PropertyName = "c_ZB_VALUE8")]
        public string C_ZB_VALUE8
        {
            get { return c_ZB_VALUE8; }
            set { c_ZB_VALUE8 = value; }
        }

        /// <summary>
        /// 属性： C_ZB_ELEMENT1
        /// </summary>
        [JsonProperty(PropertyName = "c_ZB_ELEMENT9")]
        public string C_ZB_ELEMENT9
        {
            get { return c_ZB_ELEMENT9; }
            set { c_ZB_ELEMENT9 = value; }
        }

        /// <summary>
        /// 属性： C_ZB_VALUE1
        /// </summary>
        [JsonProperty(PropertyName = "c_ZB_VALUE9")]
        public string C_ZB_VALUE9
        {
            get { return c_ZB_VALUE9; }
            set { c_ZB_VALUE9 = value; }
        }

        /// <summary>
        /// 属性: C_ZB_CODE 
        /// </summary>
        [JsonProperty(PropertyName = "c_ZB_CODE")]
        public string C_ZB_CODE
        {
            set { c_ZB_CODE = value; }
            get { return c_ZB_CODE; }
        }

        /// <summary>
        /// 属性: C_ZB_NAME 
        /// </summary>
        [JsonProperty(PropertyName = "c_ZB_NAME")]
        public string C_ZB_NAME
        {
            set { c_ZB_NAME = value; }
            get { return c_ZB_NAME; }
        }

        /// <summary>
        /// 属性: C_DZ_TYPE 
        /// </summary>
        [JsonProperty(PropertyName = "c_DZ_CODE")]
        public string C_DZ_CODE
        {
            set { c_DZ_CODE = value; }
            get { return c_DZ_CODE; }
        }

        /// <summary>
        /// 属性: C_PORT_CODE
        /// </summary>
        [JsonProperty(PropertyName = "c_PORT_CODE")]
        public string C_PORT_CODE
        {
            set { c_PORT_CODE = value; }
            get { return c_PORT_CODE; }
        }

        /// <summary>
        /// 属性: C_SEND_MODE 
        /// </summary>
        [JsonProperty(PropertyName = "c_SEND_MODE")]
        public string C_SEND_MODE
        {
            set { c_SEND_MODE = value; }
            get { return c_SEND_MODE; }
        }
    }
}
