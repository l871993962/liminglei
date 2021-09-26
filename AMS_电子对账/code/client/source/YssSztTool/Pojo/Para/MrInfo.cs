using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;

namespace YssSztTool.Pojo.Para
{
    /// <summary>
    /// STORY42784中国银行_深证通伺服器要求采用热备模式
    /// STORY42660【中国银行】深证通伺服器要求采用热备模式
    /// DESC: 伺服器参数实体
    /// CREATED BY: wulongxing
    /// CREATED TIME: 2017-06-12
    /// </summary>
    public class MrInfo : BasePojo
    {
        /// <summary>
        /// 序号
        /// </summary>
        [JsonProperty(PropertyName = "n_Order")]
        public int N_Order { get; set; }
        /// <summary>
        /// 参数代码
        /// </summary>
        [JsonProperty(PropertyName = "c_Para_Code")]
        public string C_Para_Code { get; set; }

        /// <summary>
        /// 主备标识
        /// </summary>
        [JsonProperty(PropertyName = "c_Dv_Switch_Mark")]
        public string C_Dv_Switch_Mark { get; set; }
       
        /// <summary>
        /// 伺服器IP
        /// </summary>
        [JsonProperty(PropertyName = "c_Mr_Ip")]
        public string C_Mr_Ip { get; set; }
        /// <summary>
        /// 伺服器端口
        /// </summary>
        [JsonProperty(PropertyName = "c_Mr_Port")]
        public string C_Mr_Port { get; set; }

    }
}
