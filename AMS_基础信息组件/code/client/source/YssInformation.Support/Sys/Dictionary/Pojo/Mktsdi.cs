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
    public class Mktsdi : BasePojo
    {
        /// <summary>
        /// 地区代码
        /// </summary>
        private string c_MKT_CODE;

        /// <summary>
        /// 地区名称
        /// </summary>
        private string c_MKT_NAME;

        /// <summary>
        /// 市场类型
        /// </summary>
        private string c_DV_MKT_TYPE;

        /// <summary>
        /// 属性: C_MKT_CODE 
        /// </summary>
        [JsonProperty(PropertyName = "c_MKT_CODE")]
        public string C_MKT_CODE
        {
            set { c_MKT_CODE = value; }

            get { return c_MKT_CODE; }
        }

        /// <summary>
        /// 属性: C_MKT_NAME 
        /// </summary>
        [JsonProperty(PropertyName = "c_MKT_NAME")]
        public string C_MKT_NAME
        {
            set { c_MKT_NAME = value; }

            get { return c_MKT_NAME; }
        }

        /// <summary>
        /// 属性: C_DV_MKT_TYPE 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_MKT_TYPE")]
        public string C_DV_MKT_TYPE
        {
            set { c_DV_MKT_TYPE = value; }

            get { return c_DV_MKT_TYPE; }
        }
    }
}
