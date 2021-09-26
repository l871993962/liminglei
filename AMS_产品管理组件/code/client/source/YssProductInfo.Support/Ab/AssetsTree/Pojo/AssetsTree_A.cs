using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;
using FAST.Core.Context;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Datastructure;

namespace YssProductInfo.Support.Ab.AssetsTree.Pojo
{
    /// <summary>
    /// 资产属性结构A区pojo类
    /// </summary>
    [NodeDesc(ParentNode = "C_TR_CODE_P", TreeNode = "C_TR_CODE")]
    public class AssetsTree_A : AuditableParamPojo
    {
        /// <summary>
        /// 结构代码
        /// </summary>
        private string c_TR_CODE = "";

        /// <summary>
        /// 结构名称
        /// </summary>
        private string c_TR_NAME = "";

        /// <summary>
        /// 上级结构
        /// </summary>
        private string c_TR_CODE_P = "";

        /// <summary>
        /// 分类规则
        /// </summary>
        private string c_DV_TR = "";

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 顶级节点代码
        /// </summary>
        private string c_TR_CODE_R = "";

        /// <summary>
        /// 用户
        /// </summary>
        private string c_USER = "";

        /// <summary>
        /// add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
        /// 显示未分配
        /// </summary>
        private string c_DV_UN_DIS = "";

        /// <summary>
        /// add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
        /// 执行顺序
        /// </summary>
        private int n_ORDER = 0;

        /// <summary>
        /// 自动转入类型
        /// STORY #72829 资产结构新增仅包含“存续期+待发行”的组合 
        /// add by yangru 20190717
        /// </summary>
        private string c_AUTO_ZR_TYPE = "";

        /// <summary>
        /// 分类规则
        /// STORY #72829 资产结构新增仅包含“存续期+待发行”的组合 
        /// add by yangru 20190717
        /// </summary>
        private AssetsTree_A_Rule assetsTreeARule = null;

        /// <summary>
        /// 属性: 结构代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_TR_CODE")]
        public string C_TR_CODE
        {
            set { c_TR_CODE = value; }

            get { return c_TR_CODE; }
        }

        /// <summary>
        /// 属性: 结构名称 
        /// </summary>
        [JsonProperty(PropertyName = "c_TR_NAME")]
        public string C_TR_NAME
        {
            set { c_TR_NAME = value; }

            get { return c_TR_NAME; }
        }

        /// <summary>
        /// 属性: 上级结构 
        /// </summary>
        [JsonProperty(PropertyName = "c_TR_CODE_P")]
        public string C_TR_CODE_P
        {
            set { c_TR_CODE_P = value; }

            get { return c_TR_CODE_P; }
        }

        /// <summary>
        /// 属性: 分类规则 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_TR")]
        public string C_DV_TR
        {
            set { c_DV_TR = value; }

            get { return c_DV_TR; }
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
        /// 属性: 顶级节点代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_TR_CODE_R")]
        public string C_TR_CODE_R
        {
            set { c_TR_CODE_R = value; }

            get { return c_TR_CODE_R; }
        }

        /// <summary>
        /// 属性: 用户 
        /// </summary>
        [JsonProperty(PropertyName = "c_USER")]
        public string C_USER
        {
            set { c_USER = value; }

            get { return c_USER; }
        }

        /// <summary>
        /// add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
        /// 属性: 显示未分配 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_UN_DIS")]
        public string C_DV_UN_DIS
        {
            set { c_DV_UN_DIS = value; }

            get { return c_DV_UN_DIS; }
        }

        /// <summary>
        /// add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
        /// 属性: 执行顺序
        /// </summary>
        [JsonProperty(PropertyName = "n_ORDER")]
        public int N_ORDER
        {
            set { n_ORDER = value; }

            get { return n_ORDER; }
        }

        /// <summary>
        /// 属性: 自动转入类型
        /// </summary>
        [JsonProperty(PropertyName = "c_AUTO_ZR_TYPE")]
        public string C_AUTO_ZR_TYPE
        {
            set { c_AUTO_ZR_TYPE = value; }

            get { return c_AUTO_ZR_TYPE; }
        }

        /// <summary>
        /// 属性: 分类规则 
        /// </summary>
        [JsonProperty(PropertyName = "assetsTreeARule")]
        public AssetsTree_A_Rule AsetsTreeARule
        {
            set { assetsTreeARule = value; }

            get { return assetsTreeARule; }
        }

    }
}

