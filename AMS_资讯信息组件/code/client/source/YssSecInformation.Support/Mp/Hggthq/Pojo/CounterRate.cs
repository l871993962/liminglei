using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Newtonsoft.Json;

namespace YssSecInformation.Support.Mp.Hggthq.Pojo
{
    /// <summary>
    /// 回购收益行情
    /// </summary>
    public class CounterRate : AuditableParamPojo
    {
        /// <summary>
        /// 行情日期
        /// </summary>
        private DateTime d_MKT;

        /// <summary>
        /// 启用日期
        /// </summary>
        private DateTime d_BEGIN;

        /// <summary>
        /// 停用日期
        /// </summary>
        private DateTime d_END;

        /// <summary>
        /// 回购期限
        /// </summary>
        private int n_DURATION = 0;

        /// <summary>
        /// 回购利率
        /// </summary>
        private double n_RATE = 0;

        /// <summary>
        /// 证券代码
        /// </summary>
        private string c_SEC_CODE = "";

        /// <summary>
        /// 公共标志
        /// </summary>
        private string c_IS_PUBLIC = "";

        /// <summary>
        /// 业务类型
        /// </summary>
        private string c_BIZ_TYPE = "";

        /// <summary>
        /// 停用日期
        /// </summary>
        [JsonProperty(PropertyName = "d_MKT")]
        public DateTime D_MKT
        {
            set { d_MKT = value; }

            get { return d_MKT; }
        }

        /// <summary>
        /// 启用日期
        /// </summary>
        [JsonProperty(PropertyName = "d_BEGIN")]
        public DateTime D_BEGIN
        {
            set { d_BEGIN = value; }

            get { return d_BEGIN; }
        }

        /// <summary>
        /// 停用日期
        /// </summary>
        [JsonProperty(PropertyName = "d_END")]
        public DateTime D_END
        {
            set { d_END = value; }

            get { return d_END; }
        }

        /// <summary>
        /// 回购期限
        /// </summary>
        [JsonProperty(PropertyName = "n_DURATION")]
        public int N_DURATION
        {
            set { n_DURATION = value; }

            get { return n_DURATION; }
        }

        /// <summary>
        /// 回购利率
        /// </summary>
        [JsonProperty(PropertyName = "n_RATE")]
        public double N_RATE
        {
            set { n_RATE = value; }

            get { return n_RATE; }
        }

        /// <summary>
        /// 证券代码
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_CODE")]
        public string C_SEC_CODE
        {
            set { c_SEC_CODE = value; }

            get { return c_SEC_CODE; }
        }

        /// <summary>
        /// 公共标志
        /// </summary>
        [JsonProperty(PropertyName = "c_IS_PUBLIC")]
        public string C_IS_PUBLIC
        {
            set { c_IS_PUBLIC = value; }

            get { return c_IS_PUBLIC; }
        }

        /// <summary>
        /// 业务类型
        /// </summary>
        [JsonProperty(PropertyName = "c_BIZ_TYPE")]
        public string C_BIZ_TYPE
        {
            set { c_BIZ_TYPE = value; }

            get { return c_BIZ_TYPE; }
        }


    }
}


