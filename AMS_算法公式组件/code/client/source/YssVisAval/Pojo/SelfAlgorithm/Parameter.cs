using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo.Base;
using Newtonsoft.Json;

namespace YssVisAval.Pojo.SelfAlgorithm
{
    /// <summary>
    /// 运营费用参数Pojo
    /// </summary>
   public class Parameter : BasePojo
    {
        /// <summary>
        /// 关联ID
        /// </summary>
        private string c_IDEN_RELA = "";

        /// <summary>
        /// 控件代码
        /// </summary>
        private string c_PARA_CODE = "";

        /// <summary>
        /// 参数值
        /// </summary>
        private string c_PARA_VALUE = "";

        /// <summary>
        /// 参数对象(JSON字符串)
        /// </summary>
        private string c_CTL_EXTEND = "";

        /// <summary>
        /// 启用日期
        /// liuxiang 2015-8-31 STORY #21427 运营费用计提20.3版本基础上优化需求
        /// </summary>
        private DateTime d_BEGIN;

        /// <summary>
        /// 关联ID
        /// </summary>
        [JsonProperty(PropertyName = "c_IDEN_RELA")]
        public string C_IDEN_RELA
        {
            get { return c_IDEN_RELA; }
            set { c_IDEN_RELA = value; }
        }

        /// <summary>
        /// 控件代码
        /// </summary>
        [JsonProperty(PropertyName = "c_PARA_CODE")]
        public string C_PARA_CODE
        {
            get { return c_PARA_CODE; }
            set { c_PARA_CODE = value; }
        }

        /// <summary>
        /// 参数值
        /// </summary>
        [JsonProperty(PropertyName = "c_PARA_VALUE")]
        public string C_PARA_VALUE
        {
            get { return c_PARA_VALUE; }
            set { c_PARA_VALUE = value; }
        }

        /// <summary>
        /// 参数对象(JSON字符串)
        /// </summary>
        [JsonProperty(PropertyName = "c_CTL_EXTEND")]
        public string C_CTL_EXTEND
        {
            get { return c_CTL_EXTEND; }
            set { c_CTL_EXTEND = value; }
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
    }
    }

