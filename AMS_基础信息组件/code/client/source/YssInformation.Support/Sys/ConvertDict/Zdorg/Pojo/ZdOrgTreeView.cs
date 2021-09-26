using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Newtonsoft.Json;

namespace YssInformation.Support.Sys.ConvertDict.Zdorg.Pojo
{
    public class ZdOrgTreeView : ZdCorpOrg
    {
        /// <summary>
        /// 树型层级
        /// </summary>
        private int n_Level = 1;

        /// <summary>
        /// 节点代码
        /// </summary>
        private string nodeCode = "";

        /// <summary>
        /// 父节点代码
        /// </summary>
        private string parentCode = "";

        /// <summary>
        /// 树型层级
        /// </summary>
        [JsonProperty(PropertyName = "n_Level")]
        public int N_Level
        {
            get { return n_Level; }
            set { n_Level = value; }
        }

        /// <summary>
        /// 节点代码
        /// </summary>
        [JsonProperty(PropertyName = "nodeCode")]
        public string NodeCode
        {
            get { return nodeCode; }
            set { nodeCode = value; }
        }

        /// <summary>
        /// 父节点代码
        /// </summary>
        [JsonProperty(PropertyName = "parentCode")]
        public string ParentCode
        {
            get { return parentCode; }
            set { parentCode = value; }
        }
    }
}
