using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Datastructure;
using Newtonsoft.Json;

namespace YssInformation.Support.Sys.Dictionary.Pojo
{
    /// <summary>
    /// 证券交易渠道
    /// </summary>
    [NodeDesc(ParentNode = "C_DV_CHAN_TYPE", TreeNode = "C_TD_CHAN_CODE")]
    public class TdChan : AuditableParamPojo
    {
        /// <summary>
        ///   交易渠道代码
        /// </summary>
        private string c_TD_CHAN_CODE = "";

        /// <summary>
        ///  交易渠道名称
        /// </summary>
        private string c_TD_CHAN_NAME = "";

        /// <summary>
        /// 机构代码
        /// </summary>
        private string c_ORG_CODE = "";

        /// <summary>
        /// 交易所代码
        /// </summary>
        private string c_MKT_CODE = "";

        /// <summary>
        /// 渠道类型
        /// </summary>
        private string c_DV_CHAN_TYPE = "";

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 租用日期
        /// </summary>
        private DateTime d_START;

        /// <summary>
        /// 退租日期
        /// </summary>
        private DateTime d_END;

        /// <summary>
        ///  关联席位开始日期
        /// </summary>
        private DateTime d_RELA_START;

        /// <summary>
        /// 关联席位结束日期
        /// </summary>
        private DateTime d_RELA_END;

        /// <summary>
        /// BIC_CODE  STORY #82297 嘉实基金-RQFII ETF组合识别为沪股、深股渠道的交易数据报表和台账需求
        /// </summary>
        private string c_BIC_CODE;

        /// <summary>
        /// 属性: BIC_CODE STORY #82297 嘉实基金-RQFII ETF组合识别为沪股、深股渠道的交易数据报表和台账需求
        /// </summary>
        [JsonProperty(PropertyName = "c_BIC_CODE")]
        public string C_BIC_CODE
        {
            set { c_BIC_CODE = value; }

            get { return c_BIC_CODE; }
        }


        /// <summary>
        /// 属性: 交易渠道代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_TD_CHAN_CODE")]
        public string C_TD_CHAN_CODE
        {
            set { c_TD_CHAN_CODE = value; }

            get { return c_TD_CHAN_CODE; }
        }

        /// <summary>
        /// 属性: 交易渠道名称 
        /// </summary>
        [JsonProperty(PropertyName = "c_TD_CHAN_NAME")]
        public string C_TD_CHAN_NAME
        {
            set { c_TD_CHAN_NAME = value; }

            get { return c_TD_CHAN_NAME; }
        }

        /// <summary>
        /// 属性: 机构代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_CODE")]
        public string C_ORG_CODE
        {
            set { c_ORG_CODE = value; }

            get { return c_ORG_CODE; }
        }

        /// <summary>
        /// 属性: 交易所代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_MKT_CODE")]
        public string C_MKT_CODE
        {
            set { c_MKT_CODE = value; }

            get { return c_MKT_CODE; }
        }

        /// <summary>
        /// 属性: 渠道类型 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_CHAN_TYPE")]
        public string C_DV_CHAN_TYPE
        {
            set { c_DV_CHAN_TYPE = value; }

            get { return c_DV_CHAN_TYPE; }
        }

        /// <summary>
        /// 属性: 描述 
        /// </summary>
        [JsonProperty(PropertyName = "c_DESC")]
        public string C_DESC
        {
            set { c_DESC = value; }

            get { return c_DESC; }
        }

        /// <summary>
        /// 属性: 租用日期 
        /// </summary>
        [JsonProperty(PropertyName = "d_START")]
        public DateTime D_START
        {
            set { d_START = value; }

            get { return d_START; }
        }

        /// <summary>
        /// 属性: 退租日期 
        /// </summary>
        [JsonProperty(PropertyName = "d_END")]
        public DateTime D_END
        {
            set { d_END = value; }

            get { return d_END; }
        }

        /// <summary>
        /// 属性: 关联席位开始日期 
        /// </summary>
        [JsonProperty(PropertyName = "d_RELA_START")]
        public DateTime D_RELA_START
        {
            set { d_RELA_START = value; }

            get { return d_RELA_START; }
        }

        /// <summary>
        /// 属性: 关联席位结束日期 
        /// </summary>
        [JsonProperty(PropertyName = "d_RELA_END")]
        public DateTime D_RELA_END
        {
            set { d_RELA_END = value; }

            get { return d_RELA_END; }
        }
    }
}
