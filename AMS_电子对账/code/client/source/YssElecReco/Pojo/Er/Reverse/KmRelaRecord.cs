using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;

namespace YssElecReco.Pojo.Er.Reverse
{
    /// <summary>
    /// 科目关联
    /// </summary>
    public class KmRelaRecord : AuditableParamPojo  
    {
        /// <summary>
        /// 外部科目
        /// </summary>
        private List<KmMap> list_KM_OUT = null;

        /// <summary>
        /// 内部科目
        /// </summary>
        private List<KmMap> list_KM_INNER = null;

        /// <summary>
        /// 映射关系
        /// </summary>
        private KmRela kmRela = null;



        ///<summary>
        /// 外部科目
        ///</summary>
        [JsonProperty(PropertyName = "list_KM_OUT")]
        public List<KmMap> LIST_KM_OUT
        {
            set { list_KM_OUT = value; }
            get { return list_KM_OUT; }
        }

        ///<summary>
        ///内部科目
        ///</summary>
        [JsonProperty(PropertyName = "list_KM_INNER")]
        public List<KmMap> LIST_KM_INNER
        {
            set { list_KM_INNER = value; }
            get { return list_KM_INNER; }
        }

        ///<summary>
        ///映射关系
        ///</summary>
        [JsonProperty(PropertyName = "kmRela")]
        public KmRela KmRela
        {
            set { kmRela = value; }
            get { return kmRela; }
        }
    }
}