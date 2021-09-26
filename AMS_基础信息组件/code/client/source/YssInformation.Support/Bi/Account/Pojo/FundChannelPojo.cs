using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo.Base;
using Newtonsoft.Json;

namespace YssRegTransManage.TaQs.Parameter.Pojo
{
    public class FundChannelPojo : AuditableParamPojo
    {
        //渠道编码
        private string c_Channel_Num;

        //渠道名称
        private string c_Channel_Name;

        //备注
        private string c_Desc;




        [JsonProperty(PropertyName = "c_Channel_Num")]
        public string C_Channel_Num
        {
            get { return c_Channel_Num; }
            set { c_Channel_Num = value; }
        }

        [JsonProperty(PropertyName = "c_Channel_Name")]
        public string C_Channel_Name
        {
            get { return c_Channel_Name; }
            set { c_Channel_Name = value; }
        }

        [JsonProperty(PropertyName = "c_Desc")]
        public string C_Desc
        {
            get { return c_Desc; }
            set { c_Desc = value; }
        }
    }
}
