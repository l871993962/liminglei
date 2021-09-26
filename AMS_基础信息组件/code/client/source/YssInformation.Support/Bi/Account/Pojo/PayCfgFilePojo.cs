using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;

namespace YssInformation.Support.Bi.Account.Pojo
{
    public class PayCfgFilePojo
    {
        //接口代码
        private string fcusCode;

        [JsonProperty(PropertyName = "fcusCode")]
        public string FcusCode
        {
            get { return fcusCode; }
            set { fcusCode = value; }
        }
        //接口名称
        private string fcusName;

        [JsonProperty(PropertyName = "fcusName")]
        public string FcusName
        {
            get { return fcusName; }
            set { fcusName = value; }
        }
        //银行编码
        private string fbankCode;

        [JsonProperty(PropertyName = "fbankCode")]
        public string FbankCode
        {
            get { return fbankCode; }
            set { fbankCode = value; }
        }
        //划款接口
        private string fcusType;


        [JsonProperty(PropertyName = "fcusType")]
        public string FcusType
        {
            get { return fcusType; }
            set { fcusType = value; }
        }
        //指令类型
        private string finfTypes;

        [JsonProperty(PropertyName = "finfTypes")]
        public string FinfTypes
        {
            get { return finfTypes; }
            set { finfTypes = value; }
        }
        //数据源
        private string fsql;

        [JsonProperty(PropertyName = "fsql")]
        public string Fsql
        {
            get { return fsql; }
            set { fsql = value; }
        }
        //描述
        private string fdesc;

        [JsonProperty(PropertyName = "fdesc")]
        public string Fdesc
        {
            get { return fdesc; }
            set { fdesc = value; }
        }
        //接口说明
        private string fexplain;


        [JsonProperty(PropertyName = "fexplain")]
        public string Fexplain
        {
            get { return fexplain; }
            set { fexplain = value; }
        }

    }
}
