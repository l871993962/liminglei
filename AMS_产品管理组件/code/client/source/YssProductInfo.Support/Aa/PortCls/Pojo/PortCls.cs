using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace YssProductInfo.Support.Aa.PortCls.Pojo
{
    /// <summary>
    /// 分级产品信息Pojo类
    /// </summary>
    public class PortCls : AuditableParamPojo
    {
        /// <summary>
        /// 组合代码
        /// </summary>
        private string c_PORT_CODE = "";

        /// <summary>
        /// 分级组合代码
        /// </summary>
        private string c_PORT_CLS_CODE = "";

        /// <summary>
        /// 上级分级组合
        /// </summary>
        private string c_PORT_CLS_CODE_P = "";

        /// <summary>
        /// 分级组合名称
        /// </summary>
        private string c_PORT_CLS_NAME = "";

        /// <summary>
        /// 分级类型
        /// </summary>
        private string c_DV_PORT_CLS_TYPE = "";

        /// <summary>
        /// 分级级别
        /// </summary>
        private string c_DV_PORT_CLS_LEVEL = "";

        /// <summary>
        /// 计算公式
        /// </summary>
        private string c_ALGO_CODE = "";

        /// <summary>
        /// 收益分配
        /// </summary>
        private string c_DV_INC_DIS = "";

        /// <summary>
        ///  描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 轧差
        /// </summary>
        private string c_DV_NETTING = "";

        /// <summary>
        ///  成立日期
        /// </summary>
        private DateTime d_TO_LIST;

        /// <summary>
        /// 终止日期
        /// </summary>
        private DateTime d_OFF_LIST;

        /// <summary>
        /// 添加级别类型字段 liuliang 20120508
        /// 级别类型
        /// </summary>
        private string c_DV_PORT_CLS = "";

        /// <summary>
        /// 分级币种
        /// </summary>
        private string c_DC_CODE = "";

        /// <summary>
        /// 年化收益率
        /// add by tangshifeng
        /// 20130613
        /// </summary>
        private decimal n_YEAR_INCOME = decimal.Zero;

        /// <summary>
        /// 收益率公式
        /// add by tangshifeng
        /// 20130613
        /// </summary>
        private string c_ALGO_CODE_I = "";

        /// <summary>
        /// 收益率类型
        /// add by meipeng
        /// 20131224
        /// </summary>
        private string c_INCOME_TYPE = "";

        /// <summary>
        /// 比率公式
        /// add by meip
        /// 20140120
        /// </summary>
        private string c_FORMULA_CODE = "";

        /// <summary>
        /// 清盘日期
        /// add by wjj STORY #22055
        /// 20150411
        /// </summary>
        private DateTime d_LIQUID_DATE;

        /// <summary>
        /// 信用评级
        /// add by lujianhao 20180705 STORY #51721 光大证券-监管类信息完善
        /// </summary>
        private string c_XYPJ;

        /// <summary>
        /// 期货公司
        /// </summary>
        private string c_DV_QHGS;

        /// <summary>
        /// 分级编码
        /// </summary>
        private string c_FJBM;

        /// <summary>
        /// 属性: 组合代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_PORT_CODE")]
        public string C_PORT_CODE
        {
            set { c_PORT_CODE = value; }

            get { return c_PORT_CODE; }
        }

        /// <summary>
        /// 属性: 分级组合代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_PORT_CLS_CODE")]
        public string C_PORT_CLS_CODE
        {
            set { c_PORT_CLS_CODE = value; }

            get { return c_PORT_CLS_CODE; }
        }

        /// <summary>
        /// 属性: 上级分级组合 
        /// </summary>
        [JsonProperty(PropertyName = "c_PORT_CLS_CODE_P")]
        public string C_PORT_CLS_CODE_P
        {
            set { c_PORT_CLS_CODE_P = value; }

            get { return c_PORT_CLS_CODE_P; }
        }

        /// <summary>
        /// 属性: 分级组合名称 
        /// </summary>
        [JsonProperty(PropertyName = "c_PORT_CLS_NAME")]
        public string C_PORT_CLS_NAME
        {
            set { c_PORT_CLS_NAME = value; }

            get { return c_PORT_CLS_NAME; }
        }

        /// <summary>
        /// 属性: 分级类型 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_PORT_CLS_TYPE")]
        public string C_DV_PORT_CLS_TYPE
        {
            set { c_DV_PORT_CLS_TYPE = value; }

            get { return c_DV_PORT_CLS_TYPE; }
        }

        /// <summary>
        /// 属性: 分级级别 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_PORT_CLS_LEVEL")]
        public string C_DV_PORT_CLS_LEVEL
        {
            set { c_DV_PORT_CLS_LEVEL = value; }

            get { return c_DV_PORT_CLS_LEVEL; }
        }

        /// <summary>
        /// 属性: 计算公式 
        /// </summary>
        [JsonProperty(PropertyName = "c_ALGO_CODE")]
        public string C_ALGO_CODE
        {
            set { c_ALGO_CODE = value; }

            get { return c_ALGO_CODE; }
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
        /// 属性: 成立日期 
        /// </summary>
        [JsonProperty(PropertyName = "d_TO_LIST")]
        public DateTime D_TO_LIST
        {
            set { d_TO_LIST = value; }

            get { return d_TO_LIST; }
        }

        /// <summary>
        /// 属性: 终止日期 
        /// </summary>
        [JsonProperty(PropertyName = "d_OFF_LIST")]
        public DateTime D_OFF_LIST
        {
            set { d_OFF_LIST = value; }

            get { return d_OFF_LIST; }
        }

        /// <summary>
        /// 属性: 级别类型 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_PORT_CLS")]
        public string C_DV_PORT_CLS
        {
            set { c_DV_PORT_CLS = value; }

            get { return c_DV_PORT_CLS; }
        }

        /// <summary>
        /// 属性: 分级币种 
        /// </summary>
        [JsonProperty(PropertyName = "c_DC_CODE")]
        public string C_DC_CODE
        {
            set { c_DC_CODE = value; }

            get { return c_DC_CODE; }
        }

        /// <summary>
        /// 属性: 轧差
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_NETTING")]
        public string C_DV_NETTING
        {
            set { c_DV_NETTING = value; }

            get { return c_DV_NETTING; }
        }

        /// <summary>
        /// 属性: 收益分配
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_INC_DIS")]
        public string C_DV_INC_DIS
        {
            set { c_DV_INC_DIS = value; }

            get { return c_DV_INC_DIS; }
        }

        /// <summary>
        /// 属性: 年化收益率
        /// </summary>
        [JsonProperty(PropertyName = "n_YEAR_INCOME")]
        public decimal N_YEAR_INCOME
        {
            set { n_YEAR_INCOME = value; }

            get { return n_YEAR_INCOME; }
        }

        /// <summary>
        /// 属性: 收益率公式
        /// </summary>
        [JsonProperty(PropertyName = "c_ALGO_CODE_I")]
        public string C_ALGO_CODE_I
        {
            set { c_ALGO_CODE_I = value; }

            get { return c_ALGO_CODE_I; }
        }

        /// <summary>
        /// 属性: 收益率类型
        /// </summary>
        [JsonProperty(PropertyName = "c_INCOME_TYPE")]
        public string C_INCOME_TYPE
        {
            set { c_INCOME_TYPE = value; }

            get { return c_INCOME_TYPE; }
        }

        /// <summary>
        /// 属性: 比率公式
        /// </summary>
        [JsonProperty(PropertyName = "c_FORMULA_CODE")]
        public string C_FORMULA_CODE
        {
            set { c_FORMULA_CODE = value; }

            get { return c_FORMULA_CODE; }
        }

        /// <summary>
        /// 属性: 清盘日期 
        /// </summary>
        [JsonProperty(PropertyName = "d_LIQUID_DATE")]
        public DateTime D_LIQUID_DATE
        {
            set { d_LIQUID_DATE = value; }

            get { return d_LIQUID_DATE; }
        }

        /// <summary>
        /// 属性: 信用评级 
        /// </summary>
        [JsonProperty(PropertyName = "c_XYPJ")]
        public string C_XYPJ
        {
            set { c_XYPJ = value; }

            get { return c_XYPJ; }
        }

        /// <summary>
        /// 属性: 期货公司
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_QHGS")]
        public string C_DV_QHGS
        {
            set { c_DV_QHGS = value; }

            get { return c_DV_QHGS; }
        }

        /// <summary>
        /// 属性: 分级编码
        /// </summary>
        [JsonProperty(PropertyName = "c_FJBM")]
        public string C_FJBM
        {
            set { c_FJBM = value; }

            get { return c_FJBM; }
        }
    }
}


