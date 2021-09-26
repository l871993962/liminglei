using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo.Base;
using Newtonsoft.Json;

namespace YssSecInformation.Support.Sv.Pojo
{
    /// <summary>
    /// 理财付息周期信息
    /// add by guohui 20160909
    /// </summary>
    public class SecBaseLcfxzq : BasePojo
    {
        /// <summary>
        /// 销售数据的ID
        /// </summary>
        private string c_IDEN_RELA = null;

        /// <summary>
        /// 证券代码
        /// </summary>
        private string c_SEC_CODE = "";

        /// <summary>
        /// 起息日期  add by gh 20161021 BUG #140945 
        /// </summary>
        private DateTime d_AI_BEGIN;

        /// <summary>
        /// 截息日期
        /// </summary>
        private DateTime d_AI_END;

        /// <summary>
        /// 应计利息
        /// </summary>
        private decimal n_FV_IR = decimal.Zero;

        /// <summary>
        /// 销售数据的ID  add by gh 20161021 BUG #140945 
        /// </summary>
        [JsonProperty(PropertyName = "c_IDEN_RELA")]
        public string C_IDEN_RELA
        {
            get { return c_IDEN_RELA; }
            set { c_IDEN_RELA = value; }
        }

        /// <summary>
        /// 证券代码
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_CODE")]
        public string C_SEC_CODE
        {
            get { return c_SEC_CODE; }
            set { c_SEC_CODE = value; }
        }

        /// <summary>
        /// 起息日期
        /// </summary>
        [JsonProperty(PropertyName = "d_AI_BEGIN")]
        public DateTime D_AI_BEGIN
        {
            get { return d_AI_BEGIN; }
            set { d_AI_BEGIN = value; }
        }

        /// <summary>
        /// 截息日期
        /// </summary>
        [JsonProperty(PropertyName = "d_AI_END")]
        public DateTime D_AI_END
        {
            get { return d_AI_END; }
            set { d_AI_END = value; }
        }

        /// <summary>
        /// 应计利息
        /// </summary>
        [JsonProperty(PropertyName = "n_FV_IR")]
        public decimal N_FV_IR
        {
            get { return n_FV_IR; }
            set { n_FV_IR = value; }
        }
    }
}
