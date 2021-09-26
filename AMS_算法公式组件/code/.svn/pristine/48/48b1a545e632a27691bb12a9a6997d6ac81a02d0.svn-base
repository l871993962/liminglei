using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo.Base;
using Newtonsoft.Json;

namespace YssVisAval.Pojo.SelfAlgorithm
{
    /// <summary>
    /// 参数和返回值封装类
    /// </summary>
   public class DataAPI : BasePojo
    {
        /// <summary>
       /// 参数集合
       /// </summary>
        private List<AlgoAPIParam> paramAPIs = new List<AlgoAPIParam>();

        /// <summary>
        /// 返回值
        /// </summary>
        private AlgoAPIReturn returnAPI;

       /// <summary>
       /// 参数集合
       /// </summary>
        [JsonProperty(PropertyName = "paramAPIs")]
        public List<AlgoAPIParam> ParamAPIs
        {
            get { return paramAPIs; }
            set { paramAPIs = value; }
        }



       /// <summary>
       /// 返回值
       /// </summary>
       [JsonProperty(PropertyName = "returnAPI")]
       public AlgoAPIReturn ReturnAPI
       {
           get { return returnAPI; }
           set { returnAPI = value; }
       }


    }
}
