using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Datastructure;

namespace YssProductInfo.Support.Plan.Pojo
{
    /// <summary>
    /// 核算业务字典pojo
    /// </summary>
    [NodeDesc(ParentNode = "C_DVA_ITEM_CODE_P", TreeNode = "C_DVA_ITEM_CODE")]
    public class DvaItem : BasePojo
    {
        /// <summary>
        /// 核算项目代码
        /// </summary>
        private string c_DVA_ITEM_CODE;

        /// <summary>
        /// 核算项目名称
        /// </summary>
        private string c_DVA_ITEM_NAME;

        /// <summary>
        /// 父级代码
        /// </summary>
        private string c_DVA_ITEM_CODE_P;

        /// <summary>
        /// 序号
        /// </summary>
        private int n_ORDER;

        /// <summary>
        /// 是否明细项 1:明细项 0:非明细项
        /// </summary>
        private int n_DETAIL;

        /// <summary>
        /// 属性: C_DVA_ITEM_CODE 
        /// </summary>
        [JsonProperty(PropertyName = "c_DVA_ITEM_CODE")]
        public string C_DVA_ITEM_CODE
        {
            set { c_DVA_ITEM_CODE = value; }

            get { return c_DVA_ITEM_CODE; }
        }

        /// <summary>
        /// 属性: C_DVA_ITEM_NAME 
        /// </summary>
        [JsonProperty(PropertyName = "c_DVA_ITEM_NAME")]
        public string C_DVA_ITEM_NAME
        {
            set { c_DVA_ITEM_NAME = value; }

            get { return c_DVA_ITEM_NAME; }
        }

        /// <summary>
        /// 属性: C_DVA_ITEM_CODE_P 
        /// </summary>
        [JsonProperty(PropertyName = "c_DVA_ITEM_CODE_P")]
        public string C_DVA_ITEM_CODE_P
        {
            set { c_DVA_ITEM_CODE_P = value; }

            get { return c_DVA_ITEM_CODE_P; }
        }

        /// <summary>
        /// 属性: N_ORDER 
        /// </summary>
        [JsonProperty(PropertyName = "n_ORDER")]
        public int N_ORDER
        {
            set { n_ORDER = value; }

            get { return n_ORDER; }
        }

        /// <summary>
        /// 属性: N_DETAIL 
        /// </summary>
        [JsonProperty(PropertyName = "n_DETAIL")]
        public int N_DETAIL
        {
            set { n_DETAIL = value; }

            get { return n_DETAIL; }
        }

    }
}
