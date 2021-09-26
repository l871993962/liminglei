using FAST.Common.Service.Pojo.Base;
using Newtonsoft.Json;

////namespace FAST.Platform.Dict.Pojo
////namespace YssInformation.Sys.Dictionary.Pojo
namespace YssInformation.Support.Sys.Dictionary.Pojo
{
    /// <summary>
    /// 表达式字典pojo
    /// </summary>
    public class DeExp : BasePojo
    {
        /// <summary>
        /// 表达式代码
        /// </summary>
        private string c_EXP_CODE;

        /// <summary>
        /// 表达式名称
        /// </summary>
        private string c_EXP_NAME;

        /// <summary>
        /// 表达式类型
        /// </summary>
        private string c_DV_EXP_TYPE;

        /// <summary>
        /// 表达式内容
        /// </summary>
        private string c_VALUE;

        /// <summary>
        /// 属性: c_EXP_CODE 
        /// </summary>
        [JsonProperty(PropertyName = "c_EXP_CODE")]
        public string C_EXP_CODE
        {
            set { c_EXP_CODE = value; }

            get { return c_EXP_CODE; }
        }

        /// <summary>
        /// 属性: C_EXP_NAME 
        /// </summary>
        [JsonProperty(PropertyName = "c_EXP_NAME")]
        public string C_EXP_NAME
        {
            set { c_EXP_NAME = value; }

            get { return c_EXP_NAME; }
        }

        /// <summary>
        /// 属性: C_DV_EXP_TYPE 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_EXP_TYPE")]
        public string C_DV_EXP_TYPE
        {
            set { c_DV_EXP_TYPE = value; }

            get { return c_DV_EXP_TYPE; }
        }

        /// <summary>
        /// 属性: C_VALUE 
        /// </summary>
        [JsonProperty(PropertyName = "c_VALUE")]
        public string C_VALUE
        {
            set { c_VALUE = value; }

            get { return c_VALUE; }
        }

    }
}
