using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Datastructure;

namespace YssInformation.Support.Bi.AccountTree.Pojo
{
    /// <summary>
    /// 组合关联信息pojo类
    /// </summary>
    [NodeDesc(ParentNode = "C_NODE_CODE_P", TreeNode = "C_NODE_CODE")]
    public class AccountTreeB : AuditableParamPojo
    {
        /// <summary>
        /// 节点代码
        /// </summary>
        private string c_NODE_CODE = "";

        /// <summary>
        /// 关联账户id
        /// </summary>
        private string c_IDEN_RELA = "";

        /// <summary>
        /// 父级节点
        /// </summary>
        private string c_NODE_CODE_P = "";

        /// <summary>
        /// 关联账户信息账户名称（不保存入数据库）
        /// </summary>
        private string c_OPEN_ACC_NAME = "";

        /// <summary>
        /// 关联账户信息账户账号（不保存入数据库）
        /// </summary>
        private string c_OPEN_ACC_NO = "";

        /// <summary>
        /// 关联账户信息账户开户地址（不保存入数据库）
        /// </summary>
        private string c_OPEN_ADDR = "";

        /// <summary>
        /// 关联账户信息账户类型（不保存入数据库）
        /// </summary>
        private string c_ACCOUNT_TYPE = "";

        /// <summary>
        /// 关联账户信息账户币种（不保存入数据库）
        /// </summary>
        private string c_DC_CODE = "";

        /// <summary>
        /// 关联账户信息账户开户机构（不保存入数据库）
        /// </summary>
        private string c_ORG_CODE = "";

        /// <summary>
        /// 属性: 节点代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_NODE_CODE")]
        public string C_NODE_CODE
        {
            set { c_NODE_CODE = value; }

            get { return c_NODE_CODE; }
        }

        /// <summary>
        /// 属性: 关联账户id 
        /// </summary>
        [JsonProperty(PropertyName = "c_IDEN_RELA")]
        public string C_IDEN_RELA
        {
            set { c_IDEN_RELA = value; }

            get { return c_IDEN_RELA; }
        }

        /// <summary>
        /// 属性:父级节点
        /// </summary>
        [JsonProperty(PropertyName = "c_NODE_CODE_P")]
        public string C_NODE_CODE_P
        {
            set { c_NODE_CODE_P = value; }

            get { return c_NODE_CODE_P; }
        }

        /// <summary>
        /// 属性: 关联账户信息账户名称（不保存入数据库）
        /// </summary>
        [JsonProperty(PropertyName = "c_OPEN_ACC_NAME")]
        public string C_OPEN_ACC_NAME
        {
            set { c_OPEN_ACC_NAME = value; }

            get { return c_OPEN_ACC_NAME; }
        }

        /// <summary>
        /// 属性: 关联账户信息账户账号（不保存入数据库）
        /// </summary>
        [JsonProperty(PropertyName = "c_OPEN_ACC_NO")]
        public string C_OPEN_ACC_NO
        {
            set { c_OPEN_ACC_NO = value; }

            get { return c_OPEN_ACC_NO; }
        }

        /// <summary>
        /// 属性: 关联账户信息账户开户地址（不保存入数据库）
        /// </summary>
        [JsonProperty(PropertyName = "c_OPEN_ADDR")]
        public string C_OPEN_ADDR
        {
            set { c_OPEN_ADDR = value; }

            get { return c_OPEN_ADDR; }
        }

        /// <summary>
        /// 属性: 关联账户信息账户类型（不保存入数据库）
        /// </summary>
        [JsonProperty(PropertyName = "c_ACCOUNT_TYPE")]
        public string C_ACCOUNT_TYPE
        {
            set { c_ACCOUNT_TYPE = value; }

            get { return c_ACCOUNT_TYPE; }
        }

        /// <summary>
        /// 属性: 关联账户信息账户币种（不保存入数据库）
        /// </summary>
        [JsonProperty(PropertyName = "c_DC_CODE")]
        public string C_DC_CODE
        {
            set { c_DC_CODE = value; }

            get { return c_DC_CODE; }
        }

        /// <summary>
        /// 属性: 关联账户信息账户开户机构（不保存入数据库）
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_CODE")]
        public string C_ORG_CODE
        {
            set { c_ORG_CODE = value; }

            get { return c_ORG_CODE; }
        }

       
    }
}
