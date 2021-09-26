using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo.Base;
using Newtonsoft.Json;

namespace YssVisAval.Pojo.SelfAlgorithm
{
    /// <summary>
    /// 算法优化界面 返回值
    /// 20170710 马向峰
    /// </summary>
   public class AlgoAPIReturn : BasePojo
    {
        /// <summary>
        /// 返回值名称
        /// </summary>
        private string desc;

        /// <summary>
        /// 返回值类型
        /// </summary>
        private string returnType;

        /// <summary>
        /// 属性: 返回值描述
        /// </summary>
        [JsonProperty(PropertyName = "desc")]
        public string Desc
        {
            set { desc = value; }

            get { return desc; }
        }

        /// <summary>
        /// 属性: 返回值类型
        /// </summary>
        [JsonProperty(PropertyName = "returnType")]
        public string ReturnType
        {
            set { returnType = value; }

            get { return returnType; }
        }

    }
}
