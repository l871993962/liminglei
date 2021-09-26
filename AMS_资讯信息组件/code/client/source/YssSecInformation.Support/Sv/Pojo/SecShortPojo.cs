using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Newtonsoft.Json;
using Yss.KRichEx.AutoFilter.Model;
using FAST.Common.Service.Pojo.Base;

namespace YssSecInformation.Support.Sv.Pojo
{
    public class SecShortPojo : ShortPojo, IShortPojo
    {
        #region IShortPojo 成员

        /// <summary>
        /// 显示名称
        /// </summary>
        public string DisplayName
        {
            get
            {
                return this.C_SEC_NAME;
            }
            set
            {
                this.C_SEC_NAME = value;
            }
        }

        /// <summary>
        /// 显示名称对应的值
        /// </summary>
        public string DisplayValue
        {
            get
            {
                return this.C_SEC_CODE;
            }
            set
            {
                this.C_SEC_CODE = value;
            }
        }

        /// <summary>
        /// 树形节点ID
        /// </summary>
        public string NodeId
        {
            get
            {
                return null;
            }
            set
            {
            }
        }

        /// <summary>
        /// 父节点ID
        /// </summary>
        public string ParentNodeId
        {
            get
            {
                return null;
            }
            set
            {
            }
        }

        #endregion

        /// <summary>
        /// 证券代码
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_CODE")]
        public string C_SEC_CODE
        {
            get;
            set;
        }

        /// <summary>
        /// 证券名称
        /// </summary>
        [JsonProperty(PropertyName = "C_SEC_NAME")]
        public string C_SEC_NAME
        {
            get;
            set;
        }

        /// <summary>
        /// 证券上市代码
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_MKT_CODE")]
        public string C_SEC_MKT_CODE
        {
            get;
            set;
        }

        /// <summary>
        /// 交易市场代码
        /// </summary>
        [JsonProperty(PropertyName = "c_MKT_CODE")]
        public string C_MKT_CODE
        {
            get;
            set;
        }

        /// <summary>
        /// 证券品种代码
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_VAR_CODE")]
        public string C_SEC_VAR_CODE
        {
            get;
            set;
        }

        /// <summary>
        /// 证券币种
        /// </summary>
        [JsonProperty(PropertyName = "c_DC_CODE")]
        public string C_DC_CODE
        {
            get;
            set;
        }

    }
}
