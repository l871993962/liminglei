﻿using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Newtonsoft.Json;

namespace YssSecInformation.Support.Sv.Pojo
{
    /// <summary>
    /// 债券历史付息pojo类
    /// </summary>
    public class FiPay : EnclosurePojo
    {
        /// <summary>
        /// 债券代码
        /// </summary>
        private string c_SEC_CODE = "";

        /// <summary>
        /// 调息日期
        /// </summary>
        private string d_ADJ = "";

        /// <summary>
        ///  票面利率
        /// </summary>
        private decimal n_COUP_RATE = 0;

        /// <summary>
        ///  剩余本金
        /// </summary>
        private decimal n_REPAY_AMOUNT = 0;

        /// <summary>
        ///  偿还数量
        /// </summary>
        private decimal n_REM_COR = 0;

        /// <summary>
        ///  本次起息日
        /// </summary>
        private string d_BEGIN = "";

        /// <summary>
        /// 本次截息日
        /// </summary>
        private string d_END = "";

        /// <summary>
        ///  描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 周期新利率 
        /// </summary>
        private string c_DV_BOOL_TYPE = "";

        /// <summary>
        /// 交易市场
        /// </summary>
        private string c_MKT_CODE = "";

        
        /// <summary>
        /// 数据来源
        ///edit tdf BUG #115588 [紧急]浮动利率债券没有清算出财汇资讯数据 20150715
        /// </summary>
        private string c_DATA_IDF = "";

        /// <summary>
        /// 是否期内还本
        /// </summary>
        private decimal n_QNHB = 0;

        /// <summary>
        /// 期内还本日期
        /// </summary>
        private string d_QNHB = "";


        /// <summary>
        /// 属性: 债券代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_CODE")]
        public string C_SEC_CODE
        {
            set { c_SEC_CODE = value; }

            get { return c_SEC_CODE; }
        }

        /// <summary>
        /// 属性: 调息日期 
        /// </summary>
        [JsonProperty(PropertyName = "d_ADJ")]
        public string D_ADJ
        {
            set { d_ADJ = value; }

            get { return d_ADJ; }
        }

        /// <summary>
        /// 属性: 票面利率 
        /// </summary>
        [JsonProperty(PropertyName = "n_COUP_RATE")]
        public decimal N_COUP_RATE
        {
            set { n_COUP_RATE = value; }

            get { return n_COUP_RATE; }
        }

        /// <summary>
        /// 属性: 剩余本金 
        /// </summary>
        [JsonProperty(PropertyName = "n_REM_COR")]
        public decimal N_REM_COR
        {
            set { n_REM_COR = value; }

            get { return n_REM_COR; }
        }

        /// <summary>
        /// 属性: 偿还数量
        /// </summary>
        [JsonProperty(PropertyName = "n_REPAY_AMOUNT")]
        public decimal N_REPAY_AMOUNT
        {
            set { n_REPAY_AMOUNT = value; }

            get { return n_REPAY_AMOUNT; }
        }

        /// <summary>
        /// 属性: 本次起息日 
        /// </summary>
        [JsonProperty(PropertyName = "d_BEGIN")]
        public string D_BEGIN
        {
            set { d_BEGIN = value; }

            get { return d_BEGIN; }
        }

        /// <summary>
        /// 属性: 本次截息日 
        /// </summary>
        [JsonProperty(PropertyName = "d_END")]
        public string D_END
        {
            set { d_END = value; }

            get { return d_END; }
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
        /// 属性: 周期新利率 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_BOOL_TYPE")]
        public string C_DV_BOOL_TYPE
        {
            set { c_DV_BOOL_TYPE = value; }

            get { return c_DV_BOOL_TYPE; }
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
        /// 属性：数据来源
        /// edit tdf BUG #115588 [紧急]浮动利率债券没有清算出财汇资讯数据 20150715
        /// </summary>
        [JsonProperty(PropertyName = "c_DATA_IDF")]
        public string C_DATA_IDF
        {
            get { return c_DATA_IDF; }
            set { c_DATA_IDF = value; }
        }

        /// <summary>
        /// 属性：是否期内还本
        /// </summary>
        [JsonProperty(PropertyName = "n_QNHB")]
        public decimal N_QNHB
        {
            get { return n_QNHB; }
            set { n_QNHB = value; }
        }

        /// <summary>
        /// 属性：期内还本日期
        /// </summary>
        [JsonProperty(PropertyName = "d_QNHB")]
        public string D_QNHB
        {
            get { return d_QNHB; }
            set { d_QNHB = value; }
        }
    }
}
