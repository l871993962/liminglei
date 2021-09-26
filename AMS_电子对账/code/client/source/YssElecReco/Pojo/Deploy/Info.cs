using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Serialization;

namespace YssElecReco.pojo.Deploy
{
    /// <summary>
    /// 模板基础信息
    /// </summary>
    public class Info
    {
        /// <summary>
        /// 模板代码
        /// </summary>
        private string code = "";

        /// <summary>
        /// 模板名称
        /// </summary>
        private string name = "";

        /// <summary>
        /// 模板类型
        /// </summary>
        private string type = "";

        /// <summary>
        /// 描述信息
        /// </summary>
        private string desc = "";

        /// <summary>
        /// 版本信息
        /// </summary>
        private string version = "";

        /// <summary>
        /// 模板代码
        /// </summary>
        [XmlElement("code")]
        public string Code
        {
            get { return code; }
            set { code = value; }
        }

        /// <summary>
        /// 模板名称
        /// </summary>
        [XmlElement("name")]
        public string Name
        {
            get { return name; }
            set { name = value; }
        }

        /// <summary>
        /// 模板类型
        /// </summary>
        [XmlElement("type")]
        public string Type
        {
            get { return type; }
            set { type = value; }
        }

        /// <summary>
        /// 描述信息
        /// </summary>
        [XmlElement("desc")]
        public string Desc
        {
            get { return desc; }
            set { desc = value; }
        }

        /// <summary>
        /// 版本信息
        /// </summary>
        [XmlElement("version")]
        public string Version
        {
            get { return version; }
            set { version = value; }
        }
    }
}
