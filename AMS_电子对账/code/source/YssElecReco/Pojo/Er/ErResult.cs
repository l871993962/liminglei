using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Datastructure;

namespace YssElecReco.Pojo.Er
{
    /// <summary>
    /// 电子对账结果实体对象
    /// </summary>
    [NodeDesc(ParentNode = "C_B_KM_CODE_P", TreeNode = "C_B_KM_CODE")]//STORY41248【南方基金】电子对账反馈结果中的差异数据下拉框扩展-添加父节点
    public class ErResult : BasePojo
    {
        /// <summary>
        /// 本方科目父节点      STORY41248【南方基金】电子对账反馈结果中的差异数据下拉框扩展-添加父节点
        /// </summary>
        private string c_B_KM_CODE_P = "";

        /// <summary>
        /// 文件类型
        /// </summary>
        private string c_FILE_TYPE = "";

        /// <summary>
        /// 组合代码
        /// </summary>
        private string c_ASS_CODE = "";

        /// <summary>
        /// 报表类型
        /// </summary>
        private string c_RPT_TYPE = "";

        /// <summary>
        /// 开始日期
        /// </summary>
        private string d_START_DATE = "";

        /// <summary>
        /// 结束日期
        /// </summary>
        private string d_END_DATE = "";

        /// <summary>
        /// 对帐结果
        /// </summary>
        private string c_RESULT = "";

        /// <summary>
        /// 关联流水号
        /// </summary>
        private string c_REF_NO = "";

        /// <summary>
        /// 附言
        /// </summary>
        private string c_NOTE = "";

        /// <summary>
        /// 处理人
        /// </summary>
        private string c_DEALER = "";

        /// <summary>
        /// 时间
        /// </summary>
        private string d_TIME = "";

        /// <summary>
        /// 报文序号
        /// </summary>
        private string c_SN = "";

        /// <summary>
        /// 本方科目
        /// </summary>
        private string c_B_KM_CODE = "";

        /// <summary>
        /// 对方科目
        /// </summary>
        private string c_D_KM_CODE = "";

        /// <summary>
        /// 本方金额
        /// </summary>
        private decimal n_B_JE = decimal.Zero;

        /// <summary>
        /// 对方金额
        /// </summary>
        private decimal n_D_JE = decimal.Zero;

        /// <summary>
        /// 本方数量
        /// </summary>
        private decimal n_B_SL = decimal.Zero;

        /// <summary>
        /// 对方数量
        /// </summary>
        private decimal n_D_SL = decimal.Zero;

        /// <summary>
        /// 托管行金额三
        /// </summary>
        private decimal n_B_JE3 = decimal.Zero;

        /// <summary>
        /// 基金公司金额三
        /// </summary>
        private decimal n_D_JE3 = decimal.Zero;

        /// <summary>
        /// 托管行金额四
        /// </summary>
        private decimal n_B_JE4 = decimal.Zero;

        /// <summary>
        /// 基金公司金额四
        /// </summary>
        private decimal n_D_JE4 = decimal.Zero;

        /// <summary>
        /// 托管行金额五
        /// </summary>
        private decimal n_B_JE5 = decimal.Zero;

        /// <summary>
        /// 基金公司金额五
        /// </summary>
        private decimal n_D_JE5 = decimal.Zero;

        /// <summary>
        /// 接收时间
        /// </summary>
        private string c_JS_TIME = "";

        /// <summary>
        /// 本方备用1
        /// </summary>
        private string c_B_BY1 = "";

        /// <summary>
        /// 对方备用1
        /// </summary>
        private string c_D_BY1 = "";

        /// <summary>
        /// 本方金额二
        /// </summary>
        private decimal n_B_JET = decimal.Zero;

        /// <summary>
        /// 对方金额二
        /// </summary>
        private decimal n_D_JET = decimal.Zero;

        /// <summary>
        /// 本方金额一
        /// </summary>
        private decimal n_B_JEO = decimal.Zero;

        /// <summary>
        /// 对方金额一
        /// </summary>
        private decimal n_D_JEO = decimal.Zero;

        /// <summary>
        /// 对比类型
        /// </summary>
        private string c_CHECK_FLAG = "";

        /// <summary>
        /// 本方金额三
        /// </summary>
        private decimal n_B_JETH = decimal.Zero;

        /// <summary>
        /// 对方金额三
        /// </summary>
        private decimal n_D_JETH = decimal.Zero;

        /// <summary>
        /// 本方金额四
        /// </summary>
        private decimal n_B_JEF = decimal.Zero;

        /// <summary>
        /// 对方金额四
        /// </summary>
        private decimal n_D_JEF = decimal.Zero;

        /// <summary>
        /// 本方金额五
        /// </summary>
        private decimal n_B_JEFI = decimal.Zero;

        /// <summary>
        /// 对方金额五
        /// </summary>
        private decimal n_D_JEFI = decimal.Zero;

        /// <summary>
        /// 属性: 本方金额三
        /// </summary>
        [JsonProperty(PropertyName = "n_B_JETH")]
        public decimal N_B_JETH
        {
            get { return n_B_JETH; }
            set { n_B_JETH = value; }
        }

        

        /// <summary>
        /// 属性: 对方金额三
        /// </summary>
        [JsonProperty(PropertyName = "n_D_JETH")]
        public decimal N_D_JETH
        {
            get { return n_D_JETH; }
            set { n_D_JETH = value; }
        }

        

        /// <summary>
        /// 属性: 本方金额四
        /// </summary>
        [JsonProperty(PropertyName = "n_B_JEF")]
        public decimal N_B_JEF
        {
            get { return n_B_JEF; }
            set { n_B_JEF = value; }
        }

        

        /// <summary>
        /// 属性: 对方金额四
        /// </summary>
        [JsonProperty(PropertyName = "n_D_JEF")]
        public decimal N_D_JEF
        {
            get { return n_D_JEF; }
            set { n_D_JEF = value; }
        }

        

        /// <summary>
        /// 属性: 本方金额五
        /// </summary>
        [JsonProperty(PropertyName = "n_B_JEFI")]
        public decimal N_B_JEFI
        {
            get { return n_B_JEFI; }
            set { n_B_JEFI = value; }
        }

        

        /// <summary>
        /// 属性: 对方金额五
        /// </summary>
        [JsonProperty(PropertyName = "n_D_JEFI")]
        public decimal N_D_JEFI
        {
            get { return n_D_JEFI; }
            set { n_D_JEFI = value; }
        }

        /// <summary>
        /// 属性: 文件类型
        /// </summary>
        [JsonProperty(PropertyName = "c_FILE_TYPE")]
        public string C_FILE_TYPE
        {
            set { c_FILE_TYPE = value; }
            get { return c_FILE_TYPE; }
        }

        /// <summary>
        /// 属性: 组合代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_ASS_CODE")]
        public string C_ASS_CODE
        {
            set { c_ASS_CODE = value; }
            get { return c_ASS_CODE; }
        }

        /// <summary>
        /// 属性: 报表类型 
        /// </summary>
        [JsonProperty(PropertyName = "c_RPT_TYPE")]
        public string C_RPT_TYPE
        {
            set { c_RPT_TYPE = value; }
            get { return c_RPT_TYPE; }
        }

        /// <summary>
        /// 属性: 开始日期 
        /// </summary>
        [JsonProperty(PropertyName = "d_START_DATE")]
        public string D_START_DATE
        {
            set { d_START_DATE = value; }
            get { return d_START_DATE; }
        }

        /// <summary>
        /// 属性: 结束日期 
        /// </summary>
        [JsonProperty(PropertyName = "d_END_DATE")]
        public string D_END_DATE
        {
            set { d_END_DATE = value; }
            get { return d_END_DATE; }
        }

        /// <summary>
        /// 属性: 对账结果 
        /// </summary>
        [JsonProperty(PropertyName = "c_RESULT")]
        public string C_RESULT
        {
            set { c_RESULT = value; }
            get { return c_RESULT; }
        }

        /// <summary>
        /// 属性: 关联流水号 
        /// </summary>
        [JsonProperty(PropertyName = "c_REF_NO")]
        public string C_REF_NO
        {
            set { c_REF_NO = value; }
            get { return c_REF_NO; }
        }

        /// <summary>
        /// 属性: 附言 
        /// </summary>
        [JsonProperty(PropertyName = "c_NOTE")]
        public string C_NOTE
        {
            set { c_NOTE = value; }
            get { return c_NOTE; }
        }

        /// <summary>
        /// 属性: 处理人 
        /// </summary>
        [JsonProperty(PropertyName = "c_DEALER")]
        public string C_DEALER
        {
            set { c_DEALER = value; }
            get { return c_DEALER; }
        }

        /// <summary>
        /// 属性: 时间 
        /// </summary>
        [JsonProperty(PropertyName = "d_TIME")]
        public string D_TIME
        {
            set { d_TIME = value; }
            get { return d_TIME; }
        }

        /// <summary>
        /// 属性: 报文序号 
        /// </summary>
        [JsonProperty(PropertyName = "c_SN")]
        public string C_SN
        {
            set { c_SN = value; }
            get { return c_SN; }
        }

        /// <summary>
        /// 属性: 本方科目 
        /// </summary>
        [JsonProperty(PropertyName = "c_B_KM_CODE")]
        public string C_B_KM_CODE
        {
            set { c_B_KM_CODE = value; }
            get { return c_B_KM_CODE; }
        }

        /// <summary>
        /// 属性: 本方科目父节点
        /// </summary>
        [JsonProperty(PropertyName = "c_B_KM_CODE_P")]
        public string C_B_KM_CODE_P
        {
            set { c_B_KM_CODE_P = value; }
            get { return c_B_KM_CODE_P; }
        }

        /// <summary>
        /// 属性: 对方科目 
        /// </summary>
        [JsonProperty(PropertyName = "c_D_KM_CODE")]
        public string C_D_KM_CODE
        {
            set { c_D_KM_CODE = value; }
            get { return c_D_KM_CODE; }
        }

        /// <summary>
        /// 属性: 本方金额 
        /// </summary>
        [JsonProperty(PropertyName = "n_B_JE")]
        public decimal N_B_JE
        {
            set { n_B_JE = value; }
            get { return n_B_JE; }
        }

        /// <summary>
        /// 属性: 对方金额 
        /// </summary>
        [JsonProperty(PropertyName = "n_D_JE")]
        public decimal N_D_JE
        {
            set { n_D_JE = value; }
            get { return n_D_JE; }
        }

        /// <summary>
        /// 属性: 本方数量 
        /// </summary>
        [JsonProperty(PropertyName = "n_B_SL")]
        public decimal N_B_SL
        {
            set { n_B_SL = value; }
            get { return n_B_SL; }
        }

        /// <summary>
        /// 属性: 对方数量 
        /// </summary>
        [JsonProperty(PropertyName = "n_D_SL")]
        public decimal N_D_SL
        {
            set { n_D_SL = value; }
            get { return n_D_SL; }
        }

        /// <summary>
        /// 属性: 托管行金额三 
        /// </summary>
        [JsonProperty(PropertyName = "n_B_JE3")]
        public decimal N_B_JE3
        {
            set { n_B_JE3 = value; }
            get { return n_B_JE3; }
        }

        /// <summary>
        /// 属性: 基金公司金额三 
        /// </summary>
        [JsonProperty(PropertyName = "n_D_JE3")]
        public decimal N_D_JE3
        {
            set { n_D_JE3 = value; }
            get { return n_D_JE3; }
        }

        /// <summary>
        /// 属性: 托管行金额四 
        /// </summary>
        [JsonProperty(PropertyName = "n_B_JE4")]
        public decimal N_B_JE4
        {
            set { n_B_JE4 = value; }
            get { return n_B_JE4; }
        }

        /// <summary>
        /// 属性: 基金公司金额四 
        /// </summary>
        [JsonProperty(PropertyName = "n_D_JE4")]
        public decimal N_D_JE4
        {
            set { n_D_JE4 = value; }
            get { return n_D_JE4; }
        }

        /// <summary>
        /// 属性: 托管行金额五 
        /// </summary>
        [JsonProperty(PropertyName = "n_B_JE5")]
        public decimal N_B_JE5
        {
            set { n_B_JE5 = value; }
            get { return n_B_JE5; }
        }

        /// <summary>
        /// 属性: 基金公司金额五 
        /// </summary>
        [JsonProperty(PropertyName = "n_D_JE5")]
        public decimal N_D_JE5
        {
            set { n_D_JE5 = value; }
            get { return n_D_JE5; }
        }

        /// <summary>
        /// 属性: 接收时间 
        /// </summary>
        [JsonProperty(PropertyName = "c_JS_TIME")]
        public string C_JS_TIME
        {
            set { c_JS_TIME = value; }
            get { return c_JS_TIME; }
        }

        /// <summary>
        /// 属性: 本方备用1 
        /// </summary>
        [JsonProperty(PropertyName = "c_B_BY1")]
        public string C_B_BY1
        {
            set { c_B_BY1 = value; }
            get { return c_B_BY1; }
        }

        /// <summary>
        /// 属性: 对方备用1 
        /// </summary>
        [JsonProperty(PropertyName = "c_D_BY1")]
        public string C_D_BY1
        {
            set { c_D_BY1 = value; }
            get { return c_D_BY1; }
        }

        /// <summary>
        /// 属性: 本方金额2 
        /// </summary>
        [JsonProperty(PropertyName = "n_B_JET")]
        public decimal N_B_JET
        {
            set { n_B_JET = value; }
            get { return n_B_JET; }
        }

        /// <summary>
        /// 属性: 对方金额2 
        /// </summary>
        [JsonProperty(PropertyName = "n_D_JET")]
        public decimal N_D_JET
        {
            set { n_D_JET = value; }
            get { return n_D_JET; }
        }

        /// <summary>
        /// 属性: 本方金额1 
        /// </summary>
        [JsonProperty(PropertyName = "n_B_JEO")]
        public decimal N_B_JEO
        {
            set { n_B_JEO = value; }
            get { return n_B_JEO; }
        }

        /// <summary>
        /// 属性: 对方金额1 
        /// </summary>
        [JsonProperty(PropertyName = "n_D_JEO")]
        public decimal N_D_JEO
        {
            set { n_D_JEO = value; }
            get { return n_D_JEO; }
        }

        /// <summary>
        /// 属性: 对比类型 
        /// </summary>
        [JsonProperty(PropertyName = "c_CHECK_FLAG")]
        public string C_CHECK_FLAG
        {
            set { c_CHECK_FLAG = value; }
            get { return c_CHECK_FLAG; }
        }
    }
}
