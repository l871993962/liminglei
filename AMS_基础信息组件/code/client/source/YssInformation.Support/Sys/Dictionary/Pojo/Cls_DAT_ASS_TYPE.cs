using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using FAST.Common.Service.Pojo.Base;

namespace YssInformation.Support.Sys.Dictionary.Pojo
{
    /// <summary>
    /// .
    /// </summary>
    public class Cls_DAT_ASS_TYPE : ClsBaseTreeNode
    {
        /// <summary>
        /// .
        /// </summary>
        private string c_dat_code = "";

        /// <summary>
        /// .
        /// </summary>
        private string n_order = "";

        /// <summary>
        /// .
        /// </summary>
        private string c_dat_name = "";

        /// <summary>
        /// 父级代码
        /// </summary>
        private string c_DAT_CODE_P = "";

        /// <summary>
        /// 父级代码
        /// </summary>
        public string C_DAT_CODE_P
        {
            get { return c_DAT_CODE_P; }
            set { c_DAT_CODE_P = value; }
        }

        /// <summary>
        /// .
        /// </summary>
        public string C_DAT_CODE
        {
            get { return c_dat_code; }
            set { c_dat_code = value; }
        }

        /// <summary>
        /// .
        /// </summary>
        public string C_DAT_NAME
        {
            get { return c_dat_name; }
            set { c_dat_name = value; }
        }

        /// <summary>
        /// .
        /// </summary>
        public string N_ORDER
        {
            get { return n_order; }
            set { n_order = value; }
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

            this.c_dat_code = aryPojo[0];
            this.c_dat_name = aryPojo[1];
            this.n_order = aryPojo[2];
            this.c_DAT_CODE_P = aryPojo[3];
            this.NodeCode = aryPojo[0];
            this.ParentCode = aryPojo[3];
            this.parseComm(strPojo);
        }

        /// <summary>
        /// 树形结构解析方法
        /// </summary>
        /// <param name="strRespStr">解析的字符串</param>
        public override void treeNodeParse(string strRespStr)
        {
            parseStrToAttr(strRespStr);
        }
    }
}
