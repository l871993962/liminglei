using System;
using System.Collections.Generic;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo;

namespace YssProductInfo.Support.Cp.PubAcc.Pojo
{
    /// <summary>
    /// 公用账户信息实体类
    /// </summary>
    public class PubAcc : EnclosurePojo
    {
        /// <summary>
        ///  交易关联方
        /// </summary>
        private string c_DV_OPPO_RELA = "";

        /// <summary>
        /// 开户名称
        /// </summary>
        private string c_OPEN_ACC_NAME = "";

        /// <summary>
        /// 货币代码
        /// </summary>
        private string c_DC_CODE = "";

        /// <summary>
        /// 开发地址
        /// </summary>
        private string c_OPEN_ADDR = "";

        /// <summary>
        /// 开户账号
        /// </summary>
        private string c_OPEN_ACC_NO = "";

        /// <summary>
        /// 支付系统号
        /// </summary>
        private string c_SYS_CODE = "";

        /// <summary>
        /// 划款用途
        /// </summary>
        private string c_USAGE = "";

        /// <summary>
        /// 划款备注
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 属性: C_DV_OPPO_RELA 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_OPPO_RELA")]
        public string C_DV_OPPO_RELA
        {
            set { c_DV_OPPO_RELA = value; }
            get { return c_DV_OPPO_RELA; }
        }

        /// <summary>
        /// 属性: C_OPEN_ACC_NAME 
        /// </summary>
        [JsonProperty(PropertyName = "c_OPEN_ACC_NAME")]
        public string C_OPEN_ACC_NAME
        {
            set { c_OPEN_ACC_NAME = value; }
            get { return c_OPEN_ACC_NAME; }
        }

        /// <summary>
        /// 属性: C_DC_CODE 
        /// </summary>
        [JsonProperty(PropertyName = "c_DC_CODE")]
        public string C_DC_CODE
        {
            set { c_DC_CODE = value; }
            get { return c_DC_CODE; }
        }

        /// <summary>
        /// 属性: C_OPEN_ADDR 
        /// </summary>
        [JsonProperty(PropertyName = "c_OPEN_ADDR")]
        public string C_OPEN_ADDR
        {
            set { c_OPEN_ADDR = value; }
            get { return c_OPEN_ADDR; }
        }

        /// <summary>
        /// 属性: C_OPEN_ACC_NO 
        /// </summary>
        [JsonProperty(PropertyName = "c_OPEN_ACC_NO")]
        public string C_OPEN_ACC_NO
        {
            set { c_OPEN_ACC_NO = value; }
            get { return c_OPEN_ACC_NO; }
        }

        /// <summary>
        /// 属性: C_SYS_CODE 
        /// </summary>
        [JsonProperty(PropertyName = "c_SYS_CODE")]
        public string C_SYS_CODE
        {
            set { c_SYS_CODE = value; }
            get { return c_SYS_CODE; }
        }

        /// <summary>
        /// 属性: C_USAGE 
        /// </summary>
        [JsonProperty(PropertyName = "c_USAGE")]
        public string C_USAGE
        {
            set { c_USAGE = value; }
            get { return c_USAGE; }
        }

        /// <summary>
        /// 属性: C_DESC 
        /// </summary>
        [JsonProperty(PropertyName = "c_DESC")]
        public string C_DESC
        {
            set { c_DESC = value; }
            get { return c_DESC; }
        }

        ////add by chenchen 2016.4.18 STORY #30203 （紧急）划款指令管理中收付款人中账户信息加载有误。
        //// 获取公用账户的主键：货币代码_开户帐号_开户名称
        /// <summary>
        /// 业务主键
        /// </summary>
        public string C_PRIMARY_KEY
        {
            get
            {
                StringBuilder builder = new StringBuilder();
                builder.Append(C_DC_CODE).Append("_");
                builder.Append(C_OPEN_ACC_NO).Append("_");
                builder.Append(C_OPEN_ACC_NAME);
                string key = builder.ToString();
                builder = null;
                return key;
            }
        }
    }
}
