using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using FAST.Common.Service.Pojo.Base;

namespace YssElecReco.Pojo.Er.Reverse
{
    /// <summary>
    /// 忽略设置
    /// </summary>
    public class IgnoreItem : AuditableParamPojo  
    {
        ///<summary>
        ///对账类型
        ///</summary>
        private string c_FILE_TYPE = "";

        ///<summary>
        ///产品组合
        ///</summary>
        private string c_PORT_CODE = "";

        ///<summary>
        ///托管机构
        ///</summary>
        private string c_TGH_CODE = "";

        ///<summary>
        ///忽略类型(HL_ROW:行忽略;HL_COL:列忽略;HL_CELL:单元格忽略)
        ///</summary>
        private string c_DV_IGNORE_TYPE = "";

        ///<summary>
        ///忽略方向(IGNORE_SCOPE_INNER：本方;IGNORE_SCOPE_OUT:对方)
        ///</summary>
        private string c_DV_IGNORE_SCOPE = "";

        ///<summary>
        ///应用下级科目(SUB_SUIT_YES:是；SUB_SUIT_NO:否)
        ///</summary>
        private string c_DV_SUB_SUIT = "";

        ///<summary>
        ///列标识
        ///</summary>
        private string c_COL_FLAG = "";

        ///<summary>
        ///行标识
        ///</summary>
        private string c_ROW_FLAG = "";

        ///<summary>
        ///C_FILE_TYPE
        ///</summary>
        [JsonProperty(PropertyName = "c_FILE_TYPE")]
        public string C_FILE_TYPE
        {
            set { c_FILE_TYPE = value; }
            get { return c_FILE_TYPE; }
        }

        ///<summary>
        ///C_PORT_CODE
        ///</summary>
        [JsonProperty(PropertyName = "c_PORT_CODE")]
        public string C_PORT_CODE
        {
            set { c_PORT_CODE = value; }
            get { return c_PORT_CODE; }
        }

        ///<summary>
        ///C_TGH_CODE
        ///</summary>
        [JsonProperty(PropertyName = "c_TGH_CODE")]
        public string C_TGH_CODE
        {
            set { c_TGH_CODE = value; }
            get { return c_TGH_CODE; }
        }

        ///<summary>
        ///C_DV_IGNORE_TYPE
        ///</summary>
        [JsonProperty(PropertyName = "c_DV_IGNORE_TYPE")]
        public string C_DV_IGNORE_TYPE
        {
            set { c_DV_IGNORE_TYPE = value; }
            get { return c_DV_IGNORE_TYPE; }
        }

        ///<summary>
        ///C_DV_IGNORE_SCOPE
        ///</summary>
        [JsonProperty(PropertyName = "c_DV_IGNORE_SCOPE")]
        public string C_DV_IGNORE_SCOPE
        {
            set { c_DV_IGNORE_SCOPE = value; }
            get { return c_DV_IGNORE_SCOPE; }
        }

        ///<summary>
        ///C_DV_SUB_SUIT
        ///</summary>
        [JsonProperty(PropertyName = "c_DV_SUB_SUIT")]
        public string C_DV_SUB_SUIT
        {
            set { c_DV_SUB_SUIT = value; }
            get { return c_DV_SUB_SUIT; }
        }

        ///<summary>
        ///C_COL_FLAG
        ///</summary>
        [JsonProperty(PropertyName = "c_COL_FLAG")]
        public string C_COL_FLAG
        {
            set { c_COL_FLAG = value; }
            get { return c_COL_FLAG; }
        }

        ///<summary>
        ///C_ROW_FLAG
        ///</summary>
        [JsonProperty(PropertyName = "c_ROW_FLAG")]
        public string C_ROW_FLAG
        {
            set { c_ROW_FLAG = value; }
            get { return c_ROW_FLAG; }
        }
    }
}