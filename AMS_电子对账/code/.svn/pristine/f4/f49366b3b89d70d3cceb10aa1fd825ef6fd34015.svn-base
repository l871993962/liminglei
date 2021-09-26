using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo.Base;
using Newtonsoft.Json;
using FAST.Common.Service.Datastructure;

namespace YssElecReco.Pojo.Er
{
    /// <summary>
    /// 电子对账结果实体对象
    /// </summary>
    [NodeDesc(ParentNode = "C_KM_CODE_P", TreeNode = "C_KM_CODE")]
    public class ErYeb : BasePojo
    {
        /// <summary>
        /// 报文序号
        /// </summary>
        private string c_SN = "";

        /// <summary>
        /// 资金代码
        /// </summary>
        private string c_ASS_CODE = "";

        /// <summary>
        /// 文件类型
        /// </summary>
        private string c_FILE_TYPE = "";

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
        /// 科目代码
        /// </summary>
        private string c_KM_CODE = "";

        /// <summary>
        /// 科目名称
        /// STORY55542汇添富项目，电子对账余额表（银行间代码转换规则）明细科目，科目名称没有显示出来。
        /// </summary>
        private string c_KM_NAME = "";

        /// <summary>
        /// 上级科目代码
        /// </summary>
        private string c_KM_CODE_P = "";

        /// <summary>
        /// 币种代码
        /// </summary>
        private string c_DC_CODE = "";

        /// <summary>
        /// 期初余额(原币)
        /// </summary>
        private decimal n_ORIG_STARTBAL = decimal.Zero;

        /// <summary>
        /// 本期借方发生额(原币)
        /// </summary>
        private decimal n_ORIG_DEBIT = decimal.Zero;

        /// <summary>
        /// 本期贷方发生额(原币)
        /// </summary>
        private decimal n_ORIG_CREDIT = decimal.Zero;

        /// <summary>
        /// 期末余额(原币)
        /// </summary>
        private decimal n_ORIG_ENDBAL = decimal.Zero;

        /// <summary>
        /// 期初余额(本位币)
        /// </summary>
        private decimal n_PORT_STARTBAL = decimal.Zero;

        /// <summary>
        /// 本期借方发生额(本位币)
        /// </summary>
        private decimal n_PORT_DEBIT = decimal.Zero;

        /// <summary>
        /// 本期贷方发生额(本位币)
        /// </summary>
        private decimal n_PORT_CREDIT = decimal.Zero;

        /// <summary>
        /// 期末余额(本位币)
        /// </summary>
        private decimal n_PORT_ENDBAL = decimal.Zero;

        /// <summary>
        /// 期初余额(数量)
        /// </summary>
        private decimal n_AMOUNT_STARTBAL = decimal.Zero;

        /// <summary>
        /// 本期借方发生额(数量)
        /// </summary>
        private decimal n_AMOUNT_DEBIT = decimal.Zero;

        /// <summary>
        /// 本期贷方发生额(数量)
        /// </summary>
        private decimal n_AMOUNT_CREDIT = decimal.Zero;

        /// <summary>
        /// 期末余额(数量)
        /// </summary>
        private decimal n_AMOUNT_ENDBAL = decimal.Zero;

        /// <summary>
        /// 是否最明细科目
        /// </summary>
        private int n_DETAIL = 0;

        /// <summary>
        /// 借方累计发生额
        /// </summary>
        private decimal n_J_TOLTAL_AMOUNT = decimal.Zero;

        /// <summary>
        /// 贷方累计发生额
        /// </summary>
        private decimal n_D_TOLTAL_AMOUNT = decimal.Zero;
        
        /// <summary>
        /// 方向
        /// </summary>
        private string c_DV_ER_WAY = "";

        /// <summary>
        /// 属性: 报文批号 
        /// </summary>
        [JsonProperty(PropertyName = "c_SN")]
        public string C_SN
        {
            set { c_SN = value; }
            get { return c_SN; }
        }

        /// <summary>
        /// 属性: 资金代码
        /// </summary>
        [JsonProperty(PropertyName = "c_ASS_CODE")]
        public string C_ASS_CODE
        {
            set { c_ASS_CODE = value; }
            get { return c_ASS_CODE; }
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
        /// 属性: 科目代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_KM_CODE")]
        public string C_KM_CODE
        {
            set { c_KM_CODE = value; }
            get { return c_KM_CODE; }
        }

        /// <summary>
        /// 属性: 科目代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_KM_NAME")]
        public string C_KM_NAME
        {
            set { c_KM_NAME = value; }
            get { return c_KM_NAME; }
        }

        /// <summary>
        /// 属性: 上级科目代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_KM_CODE_P")]
        public string C_KM_CODE_P
        {
            set { c_KM_CODE_P = value; }
            get { return c_KM_CODE_P; }
        }

        /// <summary>
        /// 属性: 币种代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_DC_CODE")]
        public string C_DC_CODE
        {
            set { c_DC_CODE = value; }
            get { return c_DC_CODE; }
        }

        /// <summary>
        /// 属性: 期初余额(原币) 
        /// </summary>
        [JsonProperty(PropertyName = "n_ORIG_STARTBAL")]
        public decimal N_ORIG_STARTBAL
        {
            set { n_ORIG_STARTBAL = value; }
            get { return n_ORIG_STARTBAL; }
        }

        /// <summary>
        /// 属性: 本期借方发生额(原币) 
        /// </summary>
        [JsonProperty(PropertyName = "n_ORIG_DEBIT")]
        public decimal N_ORIG_DEBIT
        {
            set { n_ORIG_DEBIT = value; }
            get { return n_ORIG_DEBIT; }
        }

        /// <summary>
        /// 属性: 本期贷方发生额(原币) 
        /// </summary>
        [JsonProperty(PropertyName = "n_ORIG_CREDIT")]
        public decimal N_ORIG_CREDIT
        {
            set { n_ORIG_CREDIT = value; }
            get { return n_ORIG_CREDIT; }
        }

        /// <summary>
        /// 属性: 期末余额(原币) 
        /// </summary>
        [JsonProperty(PropertyName = "n_ORIG_ENDBAL")]
        public decimal N_ORIG_ENDBAL
        {
            set { n_ORIG_ENDBAL = value; }
            get { return n_ORIG_ENDBAL; }
        }

        /// <summary>
        /// 属性: 期初余额(本位币) 
        /// </summary>
        [JsonProperty(PropertyName = "n_PORT_STARTBAL")]
        public decimal N_PORT_STARTBAL
        {
            set { n_PORT_STARTBAL = value; }
            get { return n_PORT_STARTBAL; }
        }

        /// <summary>
        /// 属性: 本期借方发生额(本位币) 
        /// </summary>
        [JsonProperty(PropertyName = "n_PORT_DEBIT")]
        public decimal N_PORT_DEBIT
        {
            set { n_PORT_DEBIT = value; }
            get { return n_PORT_DEBIT; }
        }

        /// <summary>
        /// 属性: 本期贷方发生额(本位币) 
        /// </summary>
        [JsonProperty(PropertyName = "n_PORT_CREDIT")]
        public decimal N_PORT_CREDIT
        {
            set { n_PORT_CREDIT = value; }
            get { return n_PORT_CREDIT; }
        }

        /// <summary>
        /// 属性: 期末余额(本位币) 
        /// </summary>
        [JsonProperty(PropertyName = "n_PORT_ENDBAL")]
        public decimal N_PORT_ENDBAL
        {
            set { n_PORT_ENDBAL = value; }
            get { return n_PORT_ENDBAL; }
        }

        /// <summary>
        /// 属性: 期初余额(数量) 
        /// </summary>
        [JsonProperty(PropertyName = "n_AMOUNT_STARTBAL")]
        public decimal N_AMOUNT_STARTBAL
        {
            set { n_AMOUNT_STARTBAL = value; }
            get { return n_AMOUNT_STARTBAL; }
        }

        /// <summary>
        /// 属性: 本期借方发生额(数量) 
        /// </summary>
        [JsonProperty(PropertyName = "n_AMOUNT_DEBIT")]
        public decimal N_AMOUNT_DEBIT
        {
            set { n_AMOUNT_DEBIT = value; }
            get { return n_AMOUNT_DEBIT; }
        }

        /// <summary>
        /// 属性: 本期贷方发生额(数量) 
        /// </summary>
        [JsonProperty(PropertyName = "n_AMOUNT_CREDIT")]
        public decimal N_AMOUNT_CREDIT
        {
            set { n_AMOUNT_CREDIT = value; }
            get { return n_AMOUNT_CREDIT; }
        }

        /// <summary>
        /// 属性: 期末余额(数量)
        /// </summary>
        [JsonProperty(PropertyName = "n_AMOUNT_ENDBAL")]
        public decimal N_AMOUNT_ENDBAL
        {
            set { n_AMOUNT_ENDBAL = value; }
            get { return n_AMOUNT_ENDBAL; }
        }

        /// <summary>
        /// 属性: 是否最明细科目
        /// </summary>
        [JsonProperty(PropertyName = "n_DETAIL")]
        public int N_DETAIL
        {
            set { n_DETAIL = value; }
            get { return n_DETAIL; }
        }

        /// <summary>
        /// 属性: 借方累计发生额
        /// </summary>
        [JsonProperty(PropertyName = "n_J_TOLTAL_AMOUNT")]
        public decimal N_J_TOLTAL_AMOUNT
        {
            set { n_J_TOLTAL_AMOUNT = value; }
            get { return n_J_TOLTAL_AMOUNT; }
        }

        /// <summary>
        /// 属性: 贷方累计发生额 
        /// </summary>
        [JsonProperty(PropertyName = "n_D_TOLTAL_AMOUNT")]
        public decimal N_D_TOLTAL_AMOUNT
        {
            set { n_D_TOLTAL_AMOUNT = value; }
            get { return n_D_TOLTAL_AMOUNT; }
        }

        /// <summary>
        /// 属性: 方向
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_ER_WAY")]
        public string C_DV_ER_WAY
        {
            set { c_DV_ER_WAY = value; }
            get { return c_DV_ER_WAY; }
        }
    }
}
