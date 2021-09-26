using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo.Base;
using Newtonsoft.Json;

namespace YssInformation.Support.Bi.Account.Pojo
{
   
    /// <summary>
    /// 公共产品信息pojo
    /// </summary>
    public class BaseProductPojo : AuditableParamPojo
    {

        /// <summary>
        ///产品代码
        ///</summary>
        private string c_Prd_Code;
        /// <summary>
        ///资产代码
        ///</summary>
        private string c_Asset_Code;
        /// <summary>
        ///产品名称
        ///</summary>
        private string c_Prd_Name;
        /// <summary>
        ///产品英文名称
        ///</summary>
        private string c_English_Name;
        /// <summary>
        ///产品简称
        ///</summary>
        private string c_Short_Name;
        /// <summary>
        ///产品状态
        ///</summary>
        private string c_Prd_Status;
        /// <summary>
        ///产品类别
        ///</summary>
        private string c_Prd_Category;
        /// <summary>
        ///资产类别
        ///</summary>
        private string c_Asset_Type;
        /// <summary>
        ///产品成立日
        ///</summary>
        private DateTime d_Estab_Date;
        /// <summary>
        ///产品到期日
        ///</summary>
        private DateTime d_Close_Date;
        /// <summary>
        ///上级产品代码
        ///</summary>
        private string c_Super_Prd_Code;

        /// <summary>
        /// 产品群组代码
        /// </summary>
        private string c_Group_Code;

        /// <summary>
        /// 产品群组启用日期 
        /// </summary>
        private DateTime d_Start_Date;

        /// <summary>
        /// 帮助字段
        /// </summary>
        private string c_Prd_Util;

        /// <summary>
        /// 币种
        /// </summary>
        private string c_Dc_Code;

        [JsonProperty(PropertyName = "c_Prd_Util")]
        public string C_Prd_Util
        {
            get { return c_Prd_Util; }
            set { c_Prd_Util = value; }
        }

        [JsonProperty(PropertyName = "c_Prd_Code")]
        public string C_Prd_Code
        {
            get { return c_Prd_Code; }
            set { c_Prd_Code = value; }
        }
        [JsonProperty(PropertyName = "c_Asset_Code")]
        public string C_Asset_Code
        {
            get { return c_Asset_Code; }
            set { c_Asset_Code = value; }
        }
        [JsonProperty(PropertyName = "c_Prd_Name")]
        public string C_Prd_Name
        {
            get { return c_Prd_Name; }
            set { c_Prd_Name = value; }
        }
        [JsonProperty(PropertyName = "c_English_Name")]
        public string C_English_Name
        {
            get { return c_English_Name; }
            set { c_English_Name = value; }
        }
        [JsonProperty(PropertyName = "c_Short_Name")]
        public string C_Short_Name
        {
            get { return c_Short_Name; }
            set { c_Short_Name = value; }
        }
        [JsonProperty(PropertyName = "c_Prd_Status")]
        public string C_Prd_Status
        {
            get { return c_Prd_Status; }
            set { c_Prd_Status = value; }
        }
        [JsonProperty(PropertyName = "c_Prd_Category")]
        public string C_Prd_Category
        {
            get { return c_Prd_Category; }
            set { c_Prd_Category = value; }
        }
        [JsonProperty(PropertyName = "c_Asset_Type")]
        public string C_Asset_Type
        {
            get { return c_Asset_Type; }
            set { c_Asset_Type = value; }
        }
        [JsonProperty(PropertyName = "d_Estab_Date")]
        public System.DateTime D_Estab_Date
        {
            get { return d_Estab_Date; }
            set { d_Estab_Date = value; }
        }
        [JsonProperty(PropertyName = "d_Close_Date")]
        public System.DateTime D_Close_Date
        {
            get { return d_Close_Date; }
            set { d_Close_Date = value; }
        }
        [JsonProperty(PropertyName = "c_Super_Prd_Code")]
        public string C_Super_Prd_Code
        {
            get { return c_Super_Prd_Code; }
            set { c_Super_Prd_Code = value; }
        }


        [JsonProperty(PropertyName = "c_Group_Code")]
        public string C_Group_Code
        {
            get { return c_Group_Code; }
            set { c_Group_Code = value; }
        }


        [JsonProperty(PropertyName = "d_Start_Date")]
        public System.DateTime D_Start_Date
        {
            get { return d_Start_Date; }
            set { d_Start_Date = value; }
        }

        /// <summary>
        /// 产品币种
        /// </summary>
        [JsonProperty(PropertyName = "c_Dc_Code")]
        public string C_Dc_Code
        {
            get { return c_Dc_Code; }
            set { c_Dc_Code = value; }
        }
    }
    
}
