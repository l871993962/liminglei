using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Datastructure;
using FAST.Common.Service.Pojo.Base;

namespace YssProductInfo.Support.Ab.Product_Info.Pojo
{
    /// <summary>
    /// 组合产品属性Pojo
    /// Add By ： 
    /// Date ：20210317
    /// </summary>
    public class ProductInfoQuery : AuditableParamPojo
    {

        /// <summary>
        /// 组合代码
        /// </summary>
        private string c_PORT_CODE = "";

        /// <summary>
        /// 资产代码
        /// </summary>
        private string c_ASS_CODE = "";

        /// <summary>
        /// 组合名称
        /// </summary>
        private string c_PORT_NAME = "";

        /// <summary>
        /// 成立日期
        /// </summary>
        private DateTime d_BUILD;

        /// <summary>
        /// 到期日期
        /// </summary>
        private DateTime d_CLOSE;

        /// <summary>
        /// 清算日期
        /// </summary>
        private DateTime d_CLEAR;

        /// <summary>
        /// 关账日期
        /// </summary>
        private DateTime d_COLSE_ACC;

        /// <summary>
        /// 资产类型
        /// </summary>
        private string c_DAT_CODE = "";

        /// <summary>
        /// 资产类别
        /// </summary>
        private string c_DAT_CLS = "";

        /// <summary>
        /// 状态
        /// </summary>
        private string n_CHECK_STATE = "";


        /// <summary>
        /// 托管银行
        /// </summary>
        private string c_ORG_CODE = "";

        /// <summary>
        /// 托管银行Code
        /// </summary>
        private String c_ORG_NAME = "";

        /// <summary>
        /// 托管行账户
        /// </summary>
        private string c_OPEN_ACC_NO = "";

        /// <summary>
        /// 托管行
        /// </summary>
        private string c_OPEN_ACC_NAME = "";


        /// <summary>
        /// 开户行
        /// </summary>
        private string c_OPEN_ADDR = "";

        /// <summary>
        /// 计息利率
        /// </summary>
        private string c_PARA_VALUE = "";

        /// <summary>
        /// 委托方式
        /// </summary>
        private string c_ENTRUST_WAY = "";
        /// <summary>
        /// O32账套号
        /// </summary>
        private string c_COVER_ACCOUNTS = "";

        /// <summary>
        /// T+N估值属性
        /// </summary>
        private string n_T_DAYS = "";

        /// <summary>
        /// 产品属性_保监会
        /// </summary>
        private string c_CPSX_BJH = "";

        /// <summary>
        /// 客户性质
        /// </summary>
        private string c_KHXZ = "";

        
        /// <summary>
        /// 管理费率
        /// </summary>
        private string n_GLF_RATE = "";

        /// <summary>
        /// 管理费费率年天数
        /// </summary>
        private string c_GLF_YEAR_DATE = "";

        /// <summary>
        /// 管理费支付频率
        /// </summary>
        private string c_GLF_FFPL = "";
        /// <summary>
        /// 管理费备注
        /// </summary>
        private string c_GLF_DESC = "";

        /// <summary>
          /// 托管费率
        /// </summary>
        private string n_TGF_RATE = "";

        /// <summary>
        /// 托管费费率年天数
        /// </summary>
        private string c_TGF_YEAR_DATE = "";
        /// <summary>
        /// 托管费支付频率
        /// </summary>
        private string c_TGF_FFPL= "";

        /// <summary>
        /// 托管费备注
        /// </summary>
        private string c_TGF_DESC = "";

        /// <summary>
        /// 指数使用费率
        /// </summary>
        private string n_ZSSY_RATE = "";

        /// <summary>
        /// 指数使用费费率年天数
        /// </summary>
        private string c_ZSSY_YEAR_DATE = "";

        /// <summary>
        /// 是否计提业绩报酬
        /// </summary>
        private string c_IS_YJBCF = "";

        /// <summary>
        /// 业绩报酬计提频率
        /// </summary>
        private string c_ZSSY_FFPL= "";

        /// <summary>
        /// 业绩报酬备注
        /// </summary>
        private string c_ZSSY_DESC = "";

        /// <summary>
         /// 资产净值
         /// </summary>
        private string n_PORT_COST = "";

        /// <summary>
        /// 投资品种
        /// </summary>
        private string c_SEC_VAR_NAME= "";

        /// <summary>
        /// 账套负责人
        /// </summary>
        private string c_TR_NAME = "";

        /// <summary>
        /// TA净值披露日历
        /// </summary>
        private string c_HDAY_CODE = "";

        /// <summary>
        /// 组合代码
        /// </summary>
        [JsonProperty(PropertyName = "c_PORT_CODE")]
        public string C_PORT_CODE
        {
            set { c_PORT_CODE = value; }

            get { return c_PORT_CODE; }
        }

        /// <summary>
        /// 资产代码
        /// </summary>
        [JsonProperty(PropertyName = "c_ASS_CODE")]
        public string C_ASS_CODE
        {
            set { c_ASS_CODE = value; }

            get { return c_ASS_CODE; }
        }

        /// <summary>
        /// 组合名称
        /// </summary>
        [JsonProperty(PropertyName = "c_PORT_NAME")]
        public string C_PORT_NAME
        {
            set { c_PORT_NAME = value; }

            get { return c_PORT_NAME; }
        }

        /// <summary>
        /// 成立日期
        /// </summary>
        [JsonProperty(PropertyName = "d_BUILD")]
        public DateTime D_BUILD
        {
            set { d_BUILD = value; }

            get { return d_BUILD; }
        }

        /// <summary>
        /// 到期日期
        /// </summary>
        [JsonProperty(PropertyName = "d_CLOSE")]
        public DateTime D_CLOSE
        {
            set { d_CLOSE = value; }

            get { return d_CLOSE; }
        }

        /// <summary>
        /// 清算日期
        /// </summary>
        [JsonProperty(PropertyName = "d_CLEAR")]
        public DateTime D_CLEAR
        {
            set { d_CLEAR = value; }

            get { return d_CLEAR; }
        }

        /// <summary>
        /// 关账日期
        /// </summary>
        [JsonProperty(PropertyName = "d_COLSE_ACC")]
        public DateTime D_COLSE_ACC
        {
            set { d_COLSE_ACC = value; }

            get { return d_COLSE_ACC; }
        }

        /// <summary>
        /// 资产类型
        /// </summary>
        [JsonProperty(PropertyName = "c_DAT_CODE")]
        public string C_DAT_CODE
        {
            set { c_DAT_CODE = value; }

            get { return c_DAT_CODE; }
        }


        /// <summary>
        /// 资产类别
        /// </summary>
        [JsonProperty(PropertyName = "c_DAT_CLS")]
        public string C_DAT_CLS
        {
            set { c_DAT_CLS = value; }

            get { return c_DAT_CLS; }
        }

        /// <summary>
        /// 状态
        /// </summary>
        [JsonProperty(PropertyName = "n_CHECK_STATE")]
        public string N_CHECK_STATE
        {
            set { n_CHECK_STATE = value; }

            get { return n_CHECK_STATE; }
        }

        /// <summary>
        /// 托管银行
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_CODE")]
        public string C_ORG_CODE
        {
            set { c_ORG_CODE = value; }

            get { return c_ORG_CODE; }
        }

        /// <summary>
        /// 托管行账户
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_NAME")]
        public string C_ORG_NAME
        {
            set { c_ORG_NAME = value; }

            get { return c_ORG_NAME; }
        }

        /// <summary>
        /// 托管行账户
        /// </summary>
        [JsonProperty(PropertyName = "c_OPEN_ACC_NO")]
        public string C_OPEN_ACC_NO
        {
            set { c_OPEN_ACC_NO = value; }

            get { return c_OPEN_ACC_NO; }
        }

        /// <summary>
        /// 托管行
        /// </summary>
        [JsonProperty(PropertyName = "c_OPEN_ACC_NAME")]
        public string C_OPEN_ACC_NAME
        {
            set { c_OPEN_ACC_NAME = value; }

            get { return c_OPEN_ACC_NAME; }
        }


        /// <summary>
        /// 开户行
        /// </summary>
        [JsonProperty(PropertyName = "c_OPEN_ADDR")]
        public string C_OPEN_ADDR
        {
            set { c_OPEN_ADDR = value; }

            get { return c_OPEN_ADDR; }
        }

        /// <summary>
        /// 计息利率
        /// </summary>
        [JsonProperty(PropertyName = "c_PARA_VALUE")]
        public string C_PARA_VALUE
        {
            set { c_PARA_VALUE = value; }

            get { return c_PARA_VALUE; }
        }

        /// <summary>
        /// 委托方式
        /// </summary>
        [JsonProperty(PropertyName = "c_ENTRUST_WAY")]
        public string C_ENTRUST_WAY
        {
            set { c_ENTRUST_WAY = value; }

            get { return c_ENTRUST_WAY; }
        }

        /// <summary>
        /// O32账套号
        /// </summary>
        [JsonProperty(PropertyName = "c_COVER_ACCOUNTS")]
        public string C_COVER_ACCOUNTS
        {
            set { c_COVER_ACCOUNTS = value; }

            get { return c_COVER_ACCOUNTS; }
        }

        /// <summary>
        /// T+N估值属性
        /// </summary>
        [JsonProperty(PropertyName = "n_T_DAYS")]
        public string N_T_DAYS
        {
            set { n_T_DAYS = value; }

            get { return n_T_DAYS; }
        }

        /// <summary>
        /// 产品属性_保监会
        /// </summary>
        [JsonProperty(PropertyName = "c_CPSX_BJH")]
        public string C_CPSX_BJH
        {
            set { c_CPSX_BJH = value; }

            get { return c_CPSX_BJH; }
        }

        /// <summary>
        /// 客户性质
        /// </summary>
        [JsonProperty(PropertyName = "c_KHXZ")]
        public string C_KHXZ
        {
            set { c_KHXZ = value; }

            get { return c_KHXZ; }
        }

 /// <summary>
        /// 管理费率
        /// </summary>
        [JsonProperty(PropertyName = "n_GLF_RATE")]
        public string N_GLF_RATE
        {
            set { n_GLF_RATE = value; }
            get { return n_GLF_RATE; }
        }

        /// <summary>
        /// 管理费费率年天数
        /// </summary>
        [JsonProperty(PropertyName = "c_GLF_YEAR_DATE")]
        public string C_GLF_YEAR_DATE
        {
            set { c_GLF_YEAR_DATE = value; }

            get { return c_GLF_YEAR_DATE; }
        }

        /// <summary>
        /// 管理费支付频率
        /// </summary>
        [JsonProperty(PropertyName = "c_GLF_FFPL")]
        public string C_GLF_FFPL
        {
            set { c_GLF_FFPL= value; }

            get { return c_GLF_FFPL; }
        }

        /// <summary>
        /// 管理费备注
        /// </summary>
        [JsonProperty(PropertyName = "c_GLF_DESC")]
        public string C_GLF_DESC
        {
            set { c_GLF_DESC = value; }

            get { return c_GLF_DESC; }
        }

        /// <summary>
        /// 托管费率
        /// </summary>
        [JsonProperty(PropertyName = "n_TGF_RATE")]
        public string N_TGF_RATE
        {
            set { n_TGF_RATE = value; }
            get { return n_TGF_RATE; }
        }

        /// <summary>
        /// 托管费费率年天数
        /// </summary>
        [JsonProperty(PropertyName = "c_TGF_YEAR_DATE")]
        public string C_TGF_YEAR_DATE
        {
            set { c_TGF_YEAR_DATE = value; }

            get { return c_TGF_YEAR_DATE; }
        }

        /// <summary>
        /// 托管费支付频率
        /// </summary>
        [JsonProperty(PropertyName = "c_TGF_FFPL")]
        public string C_TGF_FFPL
        {
            set { c_TGF_FFPL= value; }

            get { return c_TGF_FFPL; }
        }

        /// <summary>
        /// 托管费备注
        /// </summary>
        [JsonProperty(PropertyName = "c_TGF_DESC")]
        public string C_TGF_DESC
        {
            set { c_TGF_DESC = value; }

            get { return c_TGF_DESC; }
        }

        /// <summary>
        /// 指数使用费率
        /// </summary>
        [JsonProperty(PropertyName = "n_ZSSY_RATE")]
        public string N_ZSSY_RATE
        {
            set { n_ZSSY_RATE = value; }
            get { return n_ZSSY_RATE; }
        }

        /// <summary>
        /// 指数使用费费率年天数
        /// </summary>
        [JsonProperty(PropertyName = "c_ZSSY_YEAR_DATE")]
        public string C_ZSSY_YEAR_DATE
        {
            set { c_ZSSY_YEAR_DATE = value; }

            get { return c_ZSSY_YEAR_DATE; }
        }

        /// <summary>
        /// 是否计提业绩报酬
        /// </summary>
        [JsonProperty(PropertyName = "c_IS_YJBCF")]
        public string C_IS_YJBCF
        {
            set { c_IS_YJBCF = value; }

            get { return c_IS_YJBCF; }
        }

        /// <summary>
        /// 业绩报酬计提频率
        /// </summary>
        [JsonProperty(PropertyName = "c_ZSSY_FFPL")]
        public string C_ZSSY_FFPL
        {
            set { c_ZSSY_FFPL= value; }

            get { return c_ZSSY_FFPL; }
        }

        /// <summary>
        /// 业绩报酬备注
        /// </summary>
        [JsonProperty(PropertyName = "c_ZSSY_DESC")]
        public string C_ZSSY_DESC
        {
            set { c_ZSSY_DESC = value; }

            get { return c_ZSSY_DESC; }
        }

        /// <summary>
        /// 资产净值
        /// </summary>
        [JsonProperty(PropertyName = "n_PORT_COST")]
        public string N_PORT_COST
        {
            set { n_PORT_COST = value; }
            get { return n_PORT_COST; }
        }

        /// <summary>
        /// 投资品种
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_VAR_NAME")]
        public string C_SEC_VAR_NAME
        {
            set { c_SEC_VAR_NAME = value; }

            get { return c_SEC_VAR_NAME; }
        }

        /// <summary>
        /// 账套负责人
        /// </summary>


        /// <summary>
        /// TA净值披露日历
        /// </summary>
        [JsonProperty(PropertyName = "c_HDAY_CODE")]
        public string C_HDAY_CODE
        {
            set { c_HDAY_CODE = value; }

            get { return c_HDAY_CODE; }
        }

        /// <summary>
        /// 账套负责人
        /// </summary>
        [JsonProperty(PropertyName = "c_TR_NAME")]
        public string C_TR_NAME
        {
            set { c_TR_NAME = value; }

            get { return c_TR_NAME; }
        }
        

    }
}

