using FAST.Common.Service.Pojo.Base;
using Newtonsoft.Json;

////namespace FAST.Platform.Dict.Pojo
////namespace YssInformation.Sys.Dictionary.Pojo
namespace YssInformation.Support.Sys.Dictionary.Pojo
{
    /// <summary>
    /// 资产类型pojo类
    /// </summary>
    public class AccType : BasePojo
    {
        /// <summary>
        /// 资产类型代码
        /// </summary>    
        private string c_DAT_CODE;

        /// <summary>
        /// 资产类型名称
        /// </summary>
        private string c_DAT_NAME;

        /// <summary>
        /// 编号
        /// </summary>
        private int n_ORDER;

        /// <summary>
        /// 资产类型代码P
        /// </summary>
        private string c_DAT_CODE_P;

        /// <summary>
        /// 资产类型（分类）
        /// </summary>
        private string c_DAT_TYPE;

        /// <summary>
        /// 属性：资产类型代码
        /// </summary>
        [JsonProperty(PropertyName = "c_DAT_CODE")]
        public string C_DAT_CODE
        {
            set { c_DAT_CODE = value; }

            get { return c_DAT_CODE; }
        }

        /// <summary>
        /// 属性：资产类型名称
        /// </summary>
        [JsonProperty(PropertyName = "c_DAT_NAME")]
        public string C_DAT_NAME
        {
            set { c_DAT_NAME = value; }

            get { return c_DAT_NAME; }
        }

        /// <summary>
        /// 属性：编号
        /// </summary>
        [JsonProperty(PropertyName = "n_ORDER")]
        public int N_ORDER
        {
            set { n_ORDER = value; }

            get { return n_ORDER; }
        }

        /// <summary>
        /// 属性：资产类型代码P
        /// </summary>
        [JsonProperty(PropertyName = "c_DAT_CODE_P")]
        public string C_DAT_CODE_P
        {
            set { c_DAT_CODE_P = value; }

            get { return c_DAT_CODE_P; }
        }


        /// <summary>
        /// 属性：资产类型代码P
        /// </summary>
        [JsonProperty(PropertyName = "c_DAT_TYPE")]
        public string C_DAT_TYPE
        {
            set { c_DAT_TYPE = value; }

            get { return c_DAT_TYPE; }
        }
    }
}
