using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo.Base;
using Newtonsoft.Json;
using FAST.Common.Service.Datastructure;

namespace YssInformation.Support.Bi.Account.Pojo
{
    /// <summary>
    /// 资金账户信息设置实体类
    /// </summary>
    [NodeDesc(TreeNode = "C_OPEN_ACC_NO", ParentNode = "C_ACCOUNT_TYPE")]
    public class FundAcc : AuditableParamPojo
    {
        /// <summary>
        /// 投资组合
        /// </summary>
        private string c_PORT_CODE = "";

        /// <summary>
        /// 开户名称
        /// </summary>
        private string c_OPEN_ACC_NAME = "";

        /// <summary>
        /// 账户代码
        /// </summary>
        private string c_CA_CODE = "";

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
        /// 机构代码/开户机构
        /// </summary>
        private string c_ORG_CODE = "";

        /// <summary>
        /// 资产代码
        /// </summary>
        private string c_ASS_CODE = "";

        /// <summary>
        /// 机构代码/所有人
        /// </summary>
        private string c_HOLDER = "";

        /// <summary>
        /// 划款用途
        /// </summary>
        private string c_USAGE = "";

        /// <summary>
        /// 划款备注
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 开户日期
        /// </summary>
        private DateTime d_BEGIN = new DateTime();

        /// <summary>
        /// 关户日期
        /// </summary>
        private DateTime d_END = new DateTime();

        /// <summary>
        /// 账号是否是已使用
        /// </summary>
        private string c_HAVEUSED = "";

        /// <summary>
        /// 账户类型
        /// </summary>
        private string c_ACCOUNT_TYPE = "";

        /// <summary>
        /// 开户省份
        /// STORY #41321 支付产品账户信息增加省份和城市字段
        /// </summary>
        private string c_PROVINCE = "";

        /// <summary>
        /// 开户城市
        /// STORY #41321 支付产品账户信息增加省份和城市字段
        /// </summary>
        private string c_CITY = "";

        /// <summary>
        /// 中间行
        /// STORY #34439 【招商基金】【紧急】QDII：划款指令模板设计 
        /// </summary>
        private string c_INTER_ORG_CODE = "";

        /// <summary>
        /// 大额支付号 BUG #157433 清算指令界面报错 STORY #39954 支付产品账户信息list界面增加大额支付号列
        /// </summary>
        private string c_PAY_CODE = "";

        /// <summary>
        /// 开户地址
        /// zhouning  清算一体化
        /// </summary>
        private string c_BANK_ADDR = "";

        /// <summary>
        /// 流水账户
        /// zhouning  清算一体化
        /// </summary>
        private string c_RUNNING_ACC = "";

        /// <summary>
        /// 开户方式
        /// zhouning  清算一体化
        /// </summary>
        private string c_OPEN_MODE = "";

        /// <summary>
        /// 虚拟号
        /// zhouning  清算一体化
        /// </summary>
        private string c_CNX = "";

        /// <summary>
        /// 中行机构号
        /// zhouning  清算一体化
        /// </summary>
        private string c_BC_ORG_CODE = "";

        /// <summary>
        /// 中行联行号
        /// zhouning  清算一体化
        /// </summary>
        private string c_BC_LINK_NO = "";

        /// <summary>
        /// 银行国际统一码,保留，不提供界面录入点
        /// zhouning  清算一体化
        /// </summary>
        private string c_SWIFT_CODE = "";

        /// <summary>
        /// 币种名称
        /// </summary>
        private string c_DC_NAME = "";

        /// <summary>
        /// 流水账号
        /// </summary>
        private string c_FLOW_ACC_NO = "";

        /// <summary>
        /// 附件条数
        /// </summary>
        private int n_FILE_COUNT = 0;

        /// <summary>
        /// STORY #78064 【汇添富prop直连】对接支付网关优化 
        /// 划款密钥
        /// </summary>
        private string c_PAYMENT_KEY = "";

        /// <summary>
        /// 开户行简称
        /// </summary>
        private string c_OPEN_JC = "";

        /// <summary>
        /// 组合账户代码
        /// </summary>
        private string c_CA_COMBO_CODE = "";

        /// <summary>
        /// c_SHOW_VALUE
        /// </summary>
        private string c_SHOW_VALUE = "";

        /// <summary>
        /// c_DISCARD_STATUS
        /// </summary>
        private string c_DISCARD_STATUS = "";

        /// <summary>
        /// 属性: 开户行简称
        /// </summary>
        [JsonProperty(PropertyName = "c_OPEN_JC")]
        public string C_OPEN_JC
        {
            get { return c_OPEN_JC; }
            set { c_OPEN_JC = value; }
        }


        /// <summary>
        /// 属性: 组合账户代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_CA_COMBO_CODE")]
        public string C_CA_COMBO_CODE
        {
            get { return c_CA_COMBO_CODE; }
            set { c_CA_COMBO_CODE = value; }
        }

        /// <summary>
        /// 附件条数
        /// </summary>
        [JsonProperty(PropertyName = "n_FILE_COUNT")]
        public int N_FILE_COUNT
        {
            set { n_FILE_COUNT = value; }

            get { return n_FILE_COUNT; }
        }

        /// <summary>
        /// 流水账号
        /// </summary>
        [JsonProperty(PropertyName = "c_FLOW_ACC_NO")]
        public string C_FLOW_ACC_NO
        {
            set { c_FLOW_ACC_NO = value; }

            get { return c_FLOW_ACC_NO; }
        }

        /// <summary>
        /// 账户状态
        /// </summary>
        [JsonProperty(PropertyName = "c_DISCARD_STATUS")]
        public string C_DISCARD_STATUS
        {
            set { c_DISCARD_STATUS = value; }

            get { return c_DISCARD_STATUS; }
        }

        /// <summary>
        /// 大额支付号 BUG #157433 清算指令界面报错 STORY #39954 支付产品账户信息list界面增加大额支付号列
        /// </summary>
        [JsonProperty(PropertyName = "c_PAY_CODE")]
        public string C_PAY_CODE
        {
            set { c_PAY_CODE = value; }

            get { return c_PAY_CODE; }
        }

        /// <summary>
        /// 开户省份
        /// STORY #41321 支付产品账户信息增加省份和城市字段
        /// </summary>
        [JsonProperty(PropertyName = "c_PROVINCE")]
        public string C_PROVINCE
        {
            set { c_PROVINCE = value; }

            get { return c_PROVINCE; }
        }

        /// <summary>
        /// 开户城市
        /// STORY #41321 支付产品账户信息增加省份和城市字段
        /// </summary>
        [JsonProperty(PropertyName = "c_CITY")]
        public string C_CITY
        {
            set { c_CITY = value; }

            get { return c_CITY; }
        }

        /// <summary>
        /// 属性: 账户类型 
        /// </summary>
        [JsonProperty(PropertyName = "c_ACCOUNT_TYPE")]
        public string C_ACCOUNT_TYPE
        {
            set { c_ACCOUNT_TYPE = value; }

            get { return c_ACCOUNT_TYPE; }
        }


        /// <summary>
        /// 属性：C_PORT_CODE
        /// </summary>
        [JsonProperty(PropertyName = "c_PORT_CODE")]
        public string C_PORT_CODE
        {
            get { return c_PORT_CODE; }
            set { c_PORT_CODE = value; }
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
        /// 属性: C_ASS_CODE 
        /// </summary>
        [JsonProperty(PropertyName = "c_ASS_CODE")]
        public string C_ASS_CODE
        {
            set { c_ASS_CODE = value; }
            get { return c_ASS_CODE; }
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
        /// 属性: c_ORG_CODE 
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_CODE")]
        public string C_ORG_CODE
        {
            get { return c_ORG_CODE; }
            set { c_ORG_CODE = value; }
        }


        /// <summary>
        /// 属性: c_ORG_CODE 
        /// </summary>
        [JsonProperty(PropertyName = "c_HOLDER")]
        public string C_HOLDER
        {
            get { return c_HOLDER; }
            set { c_HOLDER = value; }
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
        /// 属性: C_CA_CODE 
        /// </summary>
        [JsonProperty(PropertyName = "c_CA_CODE")]
        public string C_CA_CODE
        {
            set { c_CA_CODE = value; }
            get { return c_CA_CODE; }
        }

        /// <summary>
        /// 属性: D_BEGIN 
        /// </summary>
        [JsonProperty(PropertyName = "d_BEGIN")]
        public DateTime D_BEGIN
        {
            set { d_BEGIN = value; }
            get { return d_BEGIN; }
        }

        /// <summary>
        /// 属性: D_END 
        /// </summary>
        [JsonProperty(PropertyName = "d_END")]
        public DateTime D_END
        {
            set { d_END = value; }
            get { return d_END; }
        }

        /// <summary>
        /// 属性: C_HAVEUSED 
        /// </summary>
        [JsonProperty(PropertyName = "c_HAVEUSED")]
        public string C_HAVEUSED
        {
            set { c_HAVEUSED = value; }

            get { return c_HAVEUSED; }
        }

        /// <summary>
        /// 属性: C_INTER_ORG_CODE
        /// STORY #34439 【招商基金】【紧急】QDII：划款指令模板设计 
        /// </summary>
        [JsonProperty(PropertyName = "c_INTER_ORG_CODE")]
        public string C_INTER_ORG_CODE
        {
            set { c_INTER_ORG_CODE = value; }
            get { return c_INTER_ORG_CODE; }
        }

        /// <summary>
        /// 属性:开户地址
        /// </summary>
        [JsonProperty(PropertyName = "c_BANK_ADDR")]
        public string C_BANK_ADDR
        {
            set { c_BANK_ADDR = value; }

            get { return c_BANK_ADDR; }
        }

        /// <summary>
        /// 属性:流水账户
        /// </summary>
        [JsonProperty(PropertyName = "c_RUNNING_ACC")]
        public string C_RUNNING_ACC
        {
            set { c_RUNNING_ACC = value; }

            get { return c_RUNNING_ACC; }
        }

        /// <summary>
        /// 属性:开户方式
        /// </summary>
        [JsonProperty(PropertyName = "c_OPEN_MODE")]
        public string C_OPEN_MODE
        {
            set { c_OPEN_MODE = value; }

            get { return c_OPEN_MODE; }
        }

        /// <summary>
        /// 属性:虚拟号
        /// </summary>
        [JsonProperty(PropertyName = "c_CNX")]
        public string C_CNX
        {
            set { c_CNX = value; }

            get { return c_CNX; }
        }

        /// <summary>
        /// 属性:中行机构号
        /// </summary>
        [JsonProperty(PropertyName = "c_BC_ORG_CODE")]
        public string C_BC_ORG_CODE
        {
            set { c_BC_ORG_CODE = value; }

            get { return c_BC_ORG_CODE; }
        }

        /// <summary>
        /// 属性:中行联行号
        /// </summary>
        [JsonProperty(PropertyName = "c_BC_LINK_NO")]
        public string C_BC_LINK_NO
        {
            set { c_BC_LINK_NO = value; }

            get { return c_BC_LINK_NO; }
        }

        /// <summary>
        /// 属性:中行联行号
        /// </summary>
        [JsonProperty(PropertyName = "c_SWIFT_CODE")]
        public string C_SWIFT_CODE
        {
            set { c_SWIFT_CODE = value; }

            get { return c_SWIFT_CODE; }
        }

        /// <summary>
        /// 属性: C_DC_NAME
        /// 币种名称 
        /// </summary>
        [JsonProperty(PropertyName = "c_DC_NAME")]
        public string C_DC_NAME
        {
            set { c_DC_NAME = value; }
            get { return c_DC_NAME; }
        }

        /// <summary>
        /// BUG #336899 【300.7主干】综合指令界面手工新增指令后，修改付方账户账号信息后，重新手工新增指令付方账户会有原付方和修改后付方2个付方账户，且复制原指令付方账户不会重新选择修改后的账户信息与联系人
        /// 综合指令的账户分隔符改了
        /// </summary>
        public string C_SHOW_ORD_VALUE
        {
            get { return this.C_OPEN_ACC_NAME + "!@#$%" + this.C_OPEN_ADDR + "!@#$%" + this.C_OPEN_ACC_NO + "!@#$%" + this.C_DC_CODE; }
        }

        /// <summary>
        ///  解决下拉框重复账号展示
        /// </summary>
        public string C_SHOW_CMB_VALUE
        {
            get { return this.C_OPEN_ACC_NAME + "#" + this.C_OPEN_ADDR + "#" + this.C_OPEN_ACC_NO + "#" + this.C_DC_CODE; }
        }

        /// <summary>
        ///  解决下拉框重复账号展示
        /// </summary>
        public string C_SHOW_VALUE
        {
            set { c_SHOW_VALUE = value; }
            get { return this.C_OPEN_ACC_NO + "#" + this.C_OPEN_ACC_NAME; }
        }

        /// <summary>
        /// 解决tbMain 重复账号树形结构展示
        /// </summary>
        public string C_OPNEN_CODE_TBMAIN
        {
            get;
            set;
        }
        
        /// <summary>
        /// 属性：C_PAYMENT_KEY
        /// </summary>
        [JsonProperty(PropertyName = "c_PAYMENT_KEY")]
        public string C_PAYMENT_KEY
        {
            set { c_PAYMENT_KEY = value; }
            get { return c_PAYMENT_KEY; }
        }
    }
}
