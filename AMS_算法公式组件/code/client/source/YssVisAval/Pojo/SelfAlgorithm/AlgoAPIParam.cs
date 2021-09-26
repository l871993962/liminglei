using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo.Base;
using Newtonsoft.Json;

namespace YssVisAval.Pojo.SelfAlgorithm
{
    /// <summary>
    /// 算法界面优化 参数pojo
    /// 20170710 马向峰
    /// </summary>
   public class AlgoAPIParam : BasePojo
    {
        /// <summary>
        /// 参数名称
        /// </summary>
        private string name;

        /// <summary>
        /// 参数值
        /// </summary>
        private string paramValue;

       /// <summary>
       /// 默认是否显示
       /// </summary>
        private bool isdefault;

       /// <summary>
       /// 关键字
       /// </summary>
        private string keyWord;

        /// <summary>
        /// 常量的值
        /// </summary>
        private string source;

       /// <summary>
       /// 对应code
       /// </summary>
        private string code;

       /// <summary>
       /// 是否有明细
       /// </summary>
        private bool hasdetails;

        /// <summary>
        /// 是否有明细
        /// </summary>
        public bool Hasdetails
        {
            get { return hasdetails; }
            set { hasdetails = value; }
        }

        /// <summary>
        /// 对应code
        /// </summary>
        public string Code
        {
            get { return code; }
            set { code = value; }
        }

       /// <summary>
       /// 常量的值
       /// </summary>
        [JsonProperty(PropertyName = "source")]
        public string Source
        {
            get { return source; }
            set { source = value; }
        }

       /// <summary>
       /// 关键字
       /// </summary>
        [JsonProperty(PropertyName = "keyWord")]
        public string KeyWord
        {
            get { return keyWord; }
            set { keyWord = value; }
        }


       /// <summary>
       /// 默认是否显示
       /// </summary>
        [JsonProperty(PropertyName = "isdefault")]
        public bool Isdefault
        {
            get { return isdefault; }
            set { isdefault = value; }
        }

        /// <summary>
        /// 属性: 参数名称
        /// </summary>
        [JsonProperty(PropertyName = "name")]
        public string Name
        {
            set { name = value; }

            get { return name; }
        }

        /// <summary>
        /// 属性: 参数值
        /// </summary>
        [JsonProperty(PropertyName = "paramValue")]
        public string ParamValue
        {
            set { paramValue = value; }

            get { return paramValue; }
        }

    }
}
