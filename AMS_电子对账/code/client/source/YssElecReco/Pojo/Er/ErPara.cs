using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;

namespace YssElecReco.pojo.Er
{
    /// <summary>
    /// STORY42784中国银行_深证通伺服器要求采用热备模式
    /// STORY42660【中国银行】深证通伺服器要求采用热备模式
    /// DESC: 深圳通参数设置实体
    /// CREATED BY: wulongxing
    /// CREATED TIME: 2017-06-12
    /// </summary>
    public class ErPara : AuditableParamPojo
    {
        /// <summary>
        /// 参数代码
        /// </summary>
        [JsonProperty(PropertyName = "c_Para_Code")]
        public string C_Para_Code { get; set; }

        /// <summary>
        /// 参数名称
        /// </summary>
        [JsonProperty(PropertyName = "c_Para_Name")]
        public string C_Para_Name { get; set; }

        /// <summary>
        /// 源用户
        /// </summary>
        [JsonProperty(PropertyName = "c_Src_UserId")]
        public string C_Src_UserId { get; set; }

        /// <summary>
        /// 源应用
        /// </summary>
        [JsonProperty(PropertyName = "c_Src_AppId")]
        public string C_Src_AppId { get; set; }

        /// <summary>
        /// 发送密码
        /// </summary>
        [JsonProperty(PropertyName = "c_Pkg_Password")]
        public string C_Pkg_Password { get; set; }

        /// <summary>
        /// 公司代码
        /// </summary>
        [JsonProperty(PropertyName = "c_Dept_Code")]
        public string C_Dept_Code { get; set; }

        /// <summary>
        /// 证书ID
        /// </summary>
        [JsonProperty(PropertyName = "c_Cert_Id")]
        public string C_Cert_Id { get; set; }
        
        /// <summary>
        /// 伺服器配置信息
        /// </summary>
        [JsonProperty(PropertyName = "mrInfoList")]
        public List<MrInfo> MrInfoList { get; set; }

    }
}
