using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;

namespace YssElecReco.Pojo.Er
{
    /// <summary>
    /// 电子对账结果实体对象
    /// </summary>
    public class ErBbInfo : AuditableParamPojo
    {
        /// <summary>
        /// 报文序号
        /// </summary>
        private string c_SN = "";

        /// <summary>
        /// 手工原因
        /// </summary>
        private string c_DV_RESULT = "";

        /// <summary>
        /// 说明
        /// </summary>
        private string c_SUMMARY = "";

        /// <summary>
        /// 日期
        /// </summary>
        private string c_DV_ER_WAY = "";

        /// <summary>
        /// 发送状态
        /// </summary>
        private string c_STATE = "";

        /// <summary>
        /// 文件类型
        /// </summary>
        private string c_FILE_TYPE = "";

        /// <summary>
        /// 组合代码
        /// </summary>
        private string c_PORT_CODE = "";

        /// <summary>
        /// 报表类型
        /// </summary>
        private string c_RPT_TYPE = "";

        /// <summary>
        /// 基金代码
        /// </summary>
        private string c_ASS_CODE = "";

        /// <summary>
        /// 对账日期
        /// </summary>
        private string d_DATE = "";

        /// <summary>
        /// 错误信息
        /// </summary>
        private string c_ERR_INFO = "";

        /// <summary>
        /// 托管机构
        /// </summary>
        private string c_TGH_CODE = "";

        /// <summary>
        /// 拆分代码
        /// </summary>
        private string c_SPLIT_CODE = "";

        /// <summary>
        /// 净值审核
        /// </summary>
        private int n_JZAUDIT_STATE = 0;

        /// <summary>
        /// 托管行处理人
        /// </summary>
        private string c_DEALER = "";

        /// <summary>
        /// 托管行处理人
        /// </summary>
        [JsonProperty(PropertyName = "c_DEALER")]
        public string C_DEALER
        {
            set { c_DEALER = value; }
            get { return c_DEALER; }
        }

        /// <summary>
        /// 净值审核状态
        /// </summary>
        [JsonProperty(PropertyName = "n_JZAUDIT_STATE")]
        public int N_JZAUDIT_STATE
        {
            get { return n_JZAUDIT_STATE; }
            set { n_JZAUDIT_STATE = value; }
        }

        /// <summary>
        /// 属性: 托管机构
        /// </summary>
        [JsonProperty(PropertyName = "c_TGH_CODE")]
        public string C_TGH_CODE
        {
            set { c_TGH_CODE = value; }
            get { return c_TGH_CODE; }
        }

        /// <summary>
        /// 属性: 拆分代码
        /// </summary>
        [JsonProperty(PropertyName = "c_SPLIT_CODE")]
        public string C_SPLIT_CODE
        {
            set { c_SPLIT_CODE = value; }
            get { return c_SPLIT_CODE; }
        }
		
        /// <summary>
        /// 属性: C_DV_RESULT 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_RESULT")]
        public string C_DV_RESULT
        {
            set { c_DV_RESULT = value; }
            get { return c_DV_RESULT; }
        }

        /// <summary>
        /// 属性: C_SUMMARY 
        /// </summary>
        [JsonProperty(PropertyName = "c_SUMMARY")]
        public string C_SUMMARY
        {
            set { c_SUMMARY = value; }
            get { return c_SUMMARY; }
        }


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
        /// 属性: C_ERRINFO 
        /// </summary>
        [JsonProperty(PropertyName = "c_ERR_INFO")]
        public string C_ERR_INFO
        {
            set { c_ERR_INFO = value; }
            get { return c_ERR_INFO; }
        }

        /// <summary>
        /// 属性: d_DATE 
        /// </summary>
        [JsonProperty(PropertyName = "d_DATE")]
        public string D_DATE
        {
            set { d_DATE = value; }
            get { return d_DATE; }
        }

        /// <summary>
        /// 属性: c_SN 
        /// </summary>
        [JsonProperty(PropertyName = "c_SN")]
        public string C_SN
        {
            set { c_SN = value; }
            get { return c_SN; }
        }

        /// <summary>
        /// 属性: c_DV_ER_WAY
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_ER_WAY")]
        public string C_DV_ER_WAY
        {
            set { c_DV_ER_WAY = value; }
            get { return c_DV_ER_WAY; }
        }

        /// <summary>
        /// 属性: c_STATE
        /// </summary>
        [JsonProperty(PropertyName = "c_STATE")]
        public string C_STATE
        {
            set { c_STATE = value; }
            get { return c_STATE; }
        }

        /// <summary>
        /// 属性: c_FILE_TYPE
        /// </summary>
        [JsonProperty(PropertyName = "c_FILE_TYPE")]
        public string C_FILE_TYPE
        {
            set { c_FILE_TYPE = value; }
            get { return c_FILE_TYPE; }
        }

        /// <summary>
        /// 属性: c_RPT_TYPE
        /// </summary>
        [JsonProperty(PropertyName = "c_RPT_TYPE")]
        public string C_RPT_TYPE
        {
            set { c_RPT_TYPE = value; }
            get { return c_RPT_TYPE; }
        }

        /// <summary>
        /// 属性: c_PORT_CODE
        /// </summary>
        [JsonProperty(PropertyName = "c_ASS_CODE")]
        public string C_ASS_CODE
        {
            set { c_ASS_CODE = value; }
            get { return c_ASS_CODE; }
        }

        /// <summary>
        /// 属性：净值确认编号
        /// </summary>
        [JsonProperty(PropertyName = "c_CONFIRM_ID")]
        public string C_CONFIRM_ID { get; set; }

        /// <summary>
        /// 属性：净值确认执行状态
        /// </summary>
        [JsonProperty(PropertyName = "c_CONFIRM_EXECUTE")]
        public string C_CONFIRM_EXECUTE { get; set; }

        /// <summary>
        /// STORY58759嘉实基金-电子对账-电子对账管理界面净值确认按钮修改
        /// 属性：净值锁定执行状态
        /// </summary>
        [JsonProperty(PropertyName = "c_LOCK_EXECUTE")]
        public String C_LOCK_EXECUTE { get; set; }
    }
}
