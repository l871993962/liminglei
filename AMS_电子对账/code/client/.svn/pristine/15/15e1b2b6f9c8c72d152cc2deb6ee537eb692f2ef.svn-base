using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;

namespace YssElecReco.pojo.Er
{
    /// <summary>
    /// DESC: 对账参数设置实体
    /// VSEION: betaV1.19.2
    /// CREATED BY: liuxiang
    /// CREATED TIME: 2014-03-17
    /// </summary>
    ////public class DzPara : ParamPojo
    public class DzPara : AuditableParamPojo
    {
        /// <summary>
        /// 基金代码
        /// </summary>
        private string c_ASS_CODE = " ";

        /// <summary>
        /// 基金公司
        /// </summary>
        private string c_DEPT_CODE = "";

        /// <summary>
        /// 证书ID
        /// </summary>
        private string c_CERT_ID = "";

        /// <summary>
        /// 托管行代码
        /// </summary>
        private string c_TGH_CODE = "";

        /// <summary>
        /// 托管行名称
        /// </summary>
        private string c_TGH_NAME = "";
        
        /// <summary>
        /// 管理人
        /// STORY55269【富国基金】支持电子对账参数设置支持多管理人
        /// </summary>
        private string c_MANAGE_CODE = "";

        /// <summary>
        /// 业务类型
        /// </summary>
        private string c_BUS_TYPE = "";

        /// <summary>
        /// 连接类型
        /// </summary>
        private string c_COMM_TYPE = "";

        /// <summary>
        /// 目标用户
        /// </summary>
        private string c_TARGET_USER = "";

        /// <summary>
        /// 目标用户标识
        /// </summary>
        private string c_TARGET_APP_LOGO = "";

        /// <summary>
        ///  wlx 20160907 STORY34149【广发证券】【紧急】申请修改电子对账支持多用户的需求
        /// 源用户
        /// </summary>
        private string c_SRC_USER = "";

        /// <summary>
        /// 源应用标识
        /// </summary>
        private string c_SRC_APP_LOGO = "";

        /// <summary>
        /// 估值表时候发送 估值增值科目
        /// </summary>
        private string c_GZB_MODE = "";

        /// <summary>
        /// 包发送密码
        /// </summary>
        private string c_PKG_SecretCode = "";

        /// <summary>
        /// 对账模式
        /// </summary>
        private string c_DZ_MODE = "";

        /// <summary>
        /// 伺服器IP
        /// </summary>
        private string c_MR_IP = "";

        /// <summary>
        /// 伺服器端口
        /// </summary>
        private string c_MR_PORT = "";

        /// <summary>
        /// 是否应用分机构
        /// </summary>
        private string c_Has_Branch = "";

        /// <summary>
        /// 科目名称长度 
        /// STORY #58495 需求单-【海通证券】电子对账的科目名称需要优化
        /// author:cls
        /// </summary>
        private string c_KM_NAME_LENGTH = "";

        /// <summary>
        /// STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置 管理人秘钥
        /// </summary>
        private string c_SECRETKEY = "";

        /// <summary>
        /// STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置 加密类型
        /// </summary>
        private string c_DV_SECRETTYPE = "";

        /// <summary>
        /// STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置 报文编码
        /// </summary>
        private string c_DV_CHARSET = "";

        /// <summary>
        /// 重发最高次数
        /// </summary>
        private string c_High_Time = "";

        /// <summary>
        /// 每次间隔时间
        /// </summary>
        private string c_Interval = "";

        /// <summary>
        /// STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置 管理人秘钥
        /// </summary>
        [JsonProperty(PropertyName = "c_SECRETKEY")]
        public string C_SECRETKEY
        {
            get { return c_SECRETKEY; }
            set { c_SECRETKEY = value; }
        }

        /// <summary>
        /// STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置 加密类型
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_SECRETTYPE")]
        public string C_DV_SECRETTYPE
        {
            get { return c_DV_SECRETTYPE; }
            set { c_DV_SECRETTYPE = value; }
        }

        /// <summary>
        /// STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置 报文编码
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_CHARSET")]
        public string C_DV_CHARSET
        {
            get { return c_DV_CHARSET; }
            set { c_DV_CHARSET = value; }
        }

        /// <summary>
        ///  STORY #49489 光大证券-产品关联电子对账设置优化
        /// 是否应用分机构
        /// </summary>
        [JsonProperty(PropertyName = "c_Has_Branch")]
        public string C_Has_Branch
        {
            get { return c_Has_Branch; }
            set { c_Has_Branch = value; }
        }

        /// <summary>
        /// STORY42660【中国银行】深证通伺服器要求采用热备模式
        /// 深圳通参数代码
        /// </summary>
        [JsonProperty(PropertyName = "c_ErPara_Code")]
        public string C_ErPara_Code { get; set; }

        /// <summary>
        /// 伺服器IP
        /// </summary>
        [JsonProperty(PropertyName = "c_MR_IP")]
        public string C_MR_IP
        {
            get { return c_MR_IP; }
            set { c_MR_IP = value; }
        }

        /// <summary>
        /// 管理人
        /// </summary>
        [JsonProperty(PropertyName = "c_MANAGE_CODE")]
        public string C_MANAGE_CODE
        {
            get { return c_MANAGE_CODE; }
            set { c_MANAGE_CODE = value; }
        }

        /// <summary>
        /// 伺服器端口
        /// </summary>
        [JsonProperty(PropertyName = "c_MR_PORT")]
        public string C_MR_PORT
        {
            get { return c_MR_PORT; }
            set { c_MR_PORT = value; }
        }

        /// <summary>
        /// 重发最高次数
        /// </summary>
        [JsonProperty(PropertyName = "c_High_Time")]
        public string C_High_Time
        {
            get { return c_High_Time; }
            set { c_High_Time = value; }
        }

        /// <summary>
        /// 每次间隔时间
        /// </summary>
        [JsonProperty(PropertyName = "c_Interval")]
        public string C_Interval
        {
            get { return c_Interval; }
            set { c_Interval = value; }
        }

        /// <summary>
        /// 对账模式
        /// </summary>
        [JsonProperty(PropertyName = "c_DZ_MODE")]
        public string C_DZ_MODE
        {
            get { return c_DZ_MODE; }
            set { c_DZ_MODE = value; }
        }

        /// <summary>
        /// 估值表时候发送 估值增值科目
        /// </summary>
        [JsonProperty(PropertyName = "c_GZB_MODE")]
        public string C_GZB_MODE
        {
            get { return c_GZB_MODE; }
            set { c_GZB_MODE = value; }
        }

        /// <summary>
        /// 基金代码
        /// </summary>
        [JsonProperty(PropertyName = "c_ASS_CODE")]
        public string C_ASS_CODE
        {
            get { return c_ASS_CODE; }
            set { c_ASS_CODE = value; }
        }

        /// <summary>
        /// 基金公司
        /// </summary>
        [JsonProperty(PropertyName = "c_DEPT_CODE")]
        public string C_DEPT_CODE
        {
            get { return c_DEPT_CODE; }
            set { c_DEPT_CODE = value; }
        }

        /// <summary>
        /// 证书ID
        /// </summary>
        [JsonProperty(PropertyName = "c_CERT_ID")]
        public string C_CERT_ID
        {
            get { return c_CERT_ID; }
            set { c_CERT_ID = value; }
        }

        /// <summary>
        /// 托管行代码
        /// </summary>
        [JsonProperty(PropertyName = "c_TGH_CODE")]
        public string C_TGH_CODE
        {
            get { return c_TGH_CODE; }
            set { c_TGH_CODE = value; }
        }

        /// <summary>
        /// 托管行名称
        /// </summary>
        [JsonProperty(PropertyName = "c_TGH_NAME")]
        public string C_TGH_NAME
        {
            get { return c_TGH_NAME; }
            set { c_TGH_NAME = value; }
        }

        /// <summary>
        /// 业务类型
        /// </summary>
        [JsonProperty(PropertyName = "c_BUS_TYPE")]
        public string C_BUS_TYPE
        {
            get { return c_BUS_TYPE; }
            set { c_BUS_TYPE = value; }
        }

        /// <summary>
        /// 连接类型
        /// </summary>
        [JsonProperty(PropertyName = "c_COMM_TYPE")]
        public string C_COMM_TYPE
        {
            get { return c_COMM_TYPE; }
            set { c_COMM_TYPE = value; }
        }

        /// <summary>
        /// 目标用户
        /// </summary>
        [JsonProperty(PropertyName = "c_TARGET_USER")]
        public string C_TARGET_USER
        {
            get { return c_TARGET_USER; }
            set { c_TARGET_USER = value; }
        }

        /// <summary>
        /// 目标用户标识
        /// </summary>
        [JsonProperty(PropertyName = "c_TARGET_APP_LOGO")]
        public string C_TARGET_APP_LOGO
        {
            get { return c_TARGET_APP_LOGO; }
            set { c_TARGET_APP_LOGO = value; }
        }

        /// <summary>
        /// 源应用标识
        /// </summary>
        [JsonProperty(PropertyName = "c_SRC_APP_LOGO")]
        public string C_SRC_APP_LOGO
        {
            get { return c_SRC_APP_LOGO; }
            set { c_SRC_APP_LOGO = value; }
        }

        /// <summary>
        /// 包发送密码
        /// </summary>
        [JsonProperty(PropertyName = "c_PKG_PASSWORD")]
        public string C_PKG_PASSWORD
        {
            get { return c_PKG_SecretCode; }
            set { c_PKG_SecretCode = value; }
        }

        /// <summary>
        /// 源用户
        /// </summary>
        [JsonProperty(PropertyName = "c_SRC_USER")]
        public string C_SRC_USER
        {
            get { return c_SRC_USER; }
            set { c_SRC_USER = value; }
        }

        /// <summary>
        /// 科目名称长度 
        /// STORY #58495 需求单-【海通证券】电子对账的科目名称需要优化
        /// author:cls
        /// </summary>
        [JsonProperty(PropertyName = "c_KM_NAME_LENGTH")]
        public string C_KM_NAME_LENGTH
        {
            get { return c_KM_NAME_LENGTH; }
            set { c_KM_NAME_LENGTH = value; }
        }
       
    }
}
