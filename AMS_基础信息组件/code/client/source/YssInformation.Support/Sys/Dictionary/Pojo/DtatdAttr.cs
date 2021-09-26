using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;

////namespace FAST.Platform.Dict.Pojo
////namespace YssInformation.Sys.Dictionary.Pojo
namespace YssInformation.Support.Sys.Dictionary.Pojo
{
    /// <summary>
    /// 交易属性字典pojo
    /// </summary>
    public class DtatdAttr : BasePojo
    {
        /// <summary>
        /// C_DTA_CODE
        /// </summary>
        private string c_DTA_CODE;

        /// <summary>
        /// C_DTA_NAME 
        /// </summary>
        private string c_DTA_NAME;

        /// <summary>
        /// c_BUSI_TYPE
        /// </summary>
        private string c_BUSI_TYPE;

        /// <summary>
        /// n_ORDER
        /// </summary>
        private int n_ORDER;

        /// <summary>
        /// 属性: C_DTA_CODE 
        /// </summary>
        [JsonProperty(PropertyName = "c_DTA_CODE")]
        public string C_DTA_CODE
        {
            set { c_DTA_CODE = value; }

            get { return c_DTA_CODE; }
        }

        /// <summary>
        /// 属性: C_DTA_NAME 
        /// </summary>
        [JsonProperty(PropertyName = "c_DTA_NAME")]
        public string C_DTA_NAME
        {
            set { c_DTA_NAME = value; }

            get { return c_DTA_NAME; }
        }

        /// <summary>
        /// 属性: C_BUSI_TYPE 
        /// </summary>
        [JsonProperty(PropertyName = "c_BUSI_TYPE")]
        public string C_BUSI_TYPE
        {
            set { c_BUSI_TYPE = value; }

            get { return c_BUSI_TYPE; }
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


    }
}
