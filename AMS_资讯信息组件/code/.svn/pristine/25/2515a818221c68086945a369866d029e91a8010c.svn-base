////using YssBaseCls.Fun;
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

namespace YssSecInformation.Support.Mp.SecEq.Pojo
{
    /// <summary>
    /// /// chenbo
    /// 2017-06-22
    /// #42948 资讯信息管理组件化拆分
    /// ----------------------------------
    /// 资产权益信息Pojo类
    /// added by ll 20120928
    /// </summary>
    public class SecEqu : EnclosurePojo
    {
        /// <summary>
        /// 数据标识(DJ-对价派息；SP-证券送配；LT-证券流通；FX-证券发行)
        /// </summary>
        private string c_EQU_CLS = "";

        /// <summary>
        /// 数据来源(H-手动；Z-自动)
        /// </summary>
        private string c_DATA_IDF = "";

        /// <summary>
        /// 证券代码
        /// </summary>
        private string c_SEC_CODE = "";

        /// <summary>
        /// 标的证券
        /// </summary>
        private string c_SEC_CODE_TAG = "";

        /// <summary>
        /// 临时证券
        /// </summary>
        private string c_SEC_CODE_TMP = "";     

        /// <summary>
        /// 权益类型
        /// </summary>
        private string c_DS_CODE = " ";

        /// <summary>
        /// 税前权益比例
        /// </summary>
        private string n_EQU_RATIO_PT = "0";

        /// <summary>
        /// 税后权益比例
        /// </summary>
        private string n_EQU_RATIO_AT = "0";

        /// <summary>
        /// 配售价格
        /// </summary>
        private string n_PRICE_PLAC = "0";

        /// <summary>
        /// 分红币种
        /// </summary>
        private string c_DC_CODE = " ";

        /// <summary>
        /// 折算类型
        /// </summary>
        private string c_ZS_CODE = " ";

        /// <summary>
        /// 锁定期限
        /// </summary>
        private string c_DV_VAR_DUR = " ";

        /// <summary>
        /// 发行方式/分红类型
        /// </summary>
        private string c_DV_CODE = " ";

        /// <summary>
        /// 登记日期
        /// </summary>
        private string d_REG = "9998-12-31";

        /// <summary>
        /// 缴款截止日
        /// </summary>
        private string d_FINAL = "9998-12-31";

        /// <summary>
        /// 除权日期
        /// </summary>
        private string d_EXR = "9998-12-31";

        /// <summary>
        /// 交易属性
        /// </summary>
        private string c_DTA_CODE = " ";

        /// <summary>
        /// 交易市场
        /// </summary>
        private string c_MKT_CODE = " ";

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 属性: 数据标识 
        /// </summary>
        [JsonProperty(PropertyName = "c_EQU_CLS")]
        public string C_EQU_CLS
        {
            set { c_EQU_CLS = value; }
            get { return c_EQU_CLS; }
        }

        /// <summary>
        /// 属性: 数据来源
        /// </summary>
        [JsonProperty(PropertyName = "c_DATA_IDF")]
        public string C_DATA_IDF
        {
            set { c_DATA_IDF = value; }

            get { return c_DATA_IDF; }
        }

        /// <summary>
        /// 属性: 证券代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_CODE")]
        public string C_SEC_CODE
        {
            set { c_SEC_CODE = value; }

            get { return c_SEC_CODE; }
        }

        /// <summary>
        /// 属性: 标的证券 
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_CODE_TAG")]
        public string C_SEC_CODE_TAG
        {
            set { c_SEC_CODE_TAG = value; }

            get { return c_SEC_CODE_TAG; }
        }

        /// <summary>
        /// 属性: 临时证券 
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_CODE_TMP")]
        public string C_SEC_CODE_TMP
        {
            set { c_SEC_CODE_TMP = value; }

            get { return c_SEC_CODE_TMP; }
        }

        /// <summary>
        /// 属性: 权益类型 
        /// </summary>
        [JsonProperty(PropertyName = "c_DS_CODE")]
        public string C_DS_CODE
        {
            set { c_DS_CODE = value; }

            get { return c_DS_CODE; }
        }

        /// <summary>
        /// 属性: 税前权益比例 
        /// </summary>
        [JsonProperty(PropertyName = "n_EQU_RATIO_PT")]
        public string N_EQU_RATIO_PT
        {
            set { n_EQU_RATIO_PT = value; }

            get { return n_EQU_RATIO_PT; }
        }

        /// <summary>
        /// 属性: 税后权益比例 
        /// </summary>
        [JsonProperty(PropertyName = "n_EQU_RATIO_AT")]
        public string N_EQU_RATIO_AT
        {
            set { n_EQU_RATIO_AT = value; }

            get { return n_EQU_RATIO_AT; }
        }

        /// <summary>
        /// 属性: 配售价格 
        /// </summary>
        [JsonProperty(PropertyName = "n_PRICE_PLAC")]
        public string N_PRICE_PLAC
        {
            set { n_PRICE_PLAC = value; }

            get { return n_PRICE_PLAC; }
        }

        /// <summary>
        /// 属性: 分红币种 
        /// </summary>
        [JsonProperty(PropertyName = "c_DC_CODE")]
        public string C_DC_CODE
        {
            set { c_DC_CODE = value; }

            get { return c_DC_CODE; }
        }

        /// <summary>
        /// 属性:折算类型 
        /// </summary>
        [JsonProperty(PropertyName = "c_ZS_CODE")]
        public string C_ZS_CODE
        {
            set { c_ZS_CODE = value; }

            get { return c_ZS_CODE; }
        }

        /// <summary>
        /// 属性: 锁定期限 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_VAR_DUR")]
        public string C_DV_VAR_DUR
        {
            set { c_DV_VAR_DUR = value; }

            get { return c_DV_VAR_DUR; }
        }

        /// <summary>
        /// 属性: 发行方式/分红类型 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_CODE")]
        public string C_DV_CODE
        {
            set { c_DV_CODE = value; }

            get { return c_DV_CODE; }
        }

        /// <summary>
        /// 属性: 登记日期 
        /// </summary>
        [JsonProperty(PropertyName = "d_REG")]
        public string D_REG
        {
            set { d_REG = value; }

            get { return d_REG; }
        }

        /// <summary>
        /// 属性: 缴款截止日 
        /// </summary>
        [JsonProperty(PropertyName = "d_FINAL")]
        public string D_FINAL
        {
            set { d_FINAL = value; }

            get { return d_FINAL; }
        }

        /// <summary>
        /// 属性: 除权日期 
        /// </summary>
        [JsonProperty(PropertyName = "d_EXR")]
        public string D_EXR
        {
            set { d_EXR = value; }

            get { return d_EXR; }
        }

        /// <summary>
        /// 属性: 交易属性 
        /// </summary>
        [JsonProperty(PropertyName = "c_DTA_CODE")]
        public string C_DTA_CODE
        {
            set { c_DTA_CODE = value; }

            get { return c_DTA_CODE; }
        }

        /// <summary>
        /// 属性: 交易市场 
        /// </summary>
        [JsonProperty(PropertyName = "c_MKT_CODE")]
        public string C_MKT_CODE
        {
            set { c_MKT_CODE = value; }

            get { return c_MKT_CODE; }
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


    }
}


