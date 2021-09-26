using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using FAST.Common.Service.Pojo.Base;

namespace YssInformation.Support.Pojo
{
    /// <summary>
    /// 核算元素字典对应的pojo.
    /// wuwenlan 20110427.
    /// </summary>
    public class Cls_DAE_ELEM : ClsBasePojo
    {
        /// <summary>
        /// 核算元素代码
        /// </summary>
        private string c_DAE_CODE = "";

        /// <summary>
        /// 核算元素名称
        /// </summary>
        private string c_DAE_NAME = "";

        /// <summary>
        /// 数据来源字符串.
        /// </summary>
        private string c_DS_TYPE = "";

        /// <summary>
        ///元素代码.
        /// </summary>
        public string C_DAE_CODE
        {
            get { return c_DAE_CODE; }
            set { c_DAE_CODE = value; }
        }

        /// <summary>
        /// 元素名称.
        /// </summary>
        public string C_DAE_NAME
        {
            get { return c_DAE_NAME; }
            set { c_DAE_NAME = value; }
        }

        /// <summary>
        ///  数据来源字符串.
        /// </summary>
        public string C_DS_TYPE
        {
            get { return c_DS_TYPE; }
            set { c_DS_TYPE = value; }
        }

        /// <summary>
        /// 解析字符串来给属性付值
        /// </summary>
        /// <param name="strRespStr"> 由对象的属性以"\t"为间隔拼接成的字符串</param>
        public override void listItemParse(string strRespStr)
        {
            parseStrToAttr(strRespStr);
        }

        /// <summary>
        /// 解析字符串来给属性付值
        /// </summary>
        /// <param name="strPojo"> 由对象的属性以"\t"为间隔拼接成的字符串</param>
        public void parseStrToAttr(string strPojo)
        {
            ////拆分字符串，获取对象的每个属性
            string[] aryPojo = Regex.Split(strPojo, "\t");

            this.c_DAE_CODE = aryPojo[0];
            this.c_DAE_NAME = aryPojo[1];
            this.c_DS_TYPE = aryPojo[2];
            this.parseComm(strPojo);
        }
    }
}
