using System.Text.RegularExpressions;
using Newtonsoft.Json;
using FAST.Common.Service.Datastructure;
using FAST.Common.Service.Pojo.Base;

namespace YssInformation.Support.Sys.Dictionary.Pojo
{
    /// <summary>
    /// 
    ///  作用：划款指令类型字典
    ///  
    ///  作者：陈尤龙
    ///  
    ///  日期：2012-11-03
    ///  
    ///  版本：v1.0.0.4
    ///  
    /// </summary>
    [NodeDesc(ParentNode = "C_DZ_CODE_P", TreeNode = "C_DZ_CODE")]
    public class DzType : BasePojo
    {
        /// <summary>
        /// 划款指令代码
        /// </summary>
        private string c_DZ_CODE = "";

        /// <summary>
        /// 划款指令名称
        /// </summary>
        private string c_DZ_NAME = "";

        /// <summary>
        /// 划款类型父级代码
        /// </summary>
        private string c_DZ_CODE_P = "";

        /// <summary>
        /// 划款类型父级代码
        /// </summary>
        [JsonProperty(PropertyName = "c_DZ_CODE_P")]
        public string C_DZ_CODE_P
        {
            get { return c_DZ_CODE_P; }
            set { c_DZ_CODE_P = value; }
        }

        /// <summary>
        /// 属性: C_DZ_CODE 
        /// </summary>
        [JsonProperty(PropertyName = "c_DZ_CODE")]
        public string C_DZ_CODE
        {
            set { c_DZ_CODE = value; }
            get { return c_DZ_CODE; }
        }

        /// <summary>
        /// 属性: C_DZ_NAME 
        /// </summary>
        [JsonProperty(PropertyName = "c_DZ_NAME")]
        public string C_DZ_NAME
        {
            set { c_DZ_NAME = value; }
            get { return c_DZ_NAME; }
        }
    }
}
