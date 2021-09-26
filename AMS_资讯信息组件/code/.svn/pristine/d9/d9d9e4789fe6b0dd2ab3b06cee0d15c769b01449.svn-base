using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;



using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

namespace YssSecInformation.Support.Sv.Pojo
{
    /// <summary>
    /// 每百元利息
    /// </summary>
    public class Cls_SEC_ZQLX : ClsBasePojo
    {
        /// <summary>
        /// 证券代码
        /// </summary>
        private string c_SEC_CODE = "";

        /// <summary>
        /// 计息日期
        /// </summary>
        private string d_INCOME = "";

        /// <summary>
        /// 报价方式
        /// </summary>
        private string c_DV_QUT_MOD = "";

        /// <summary>
        /// 计息天数
        /// </summary>
        private string n_INCOME_DAYS = "";

        /// <summary>
        /// 票面利率
        /// </summary>
        private string n_COUP_RATE = "";

        /// <summary>
        /// 剩余本金
        /// </summary>
        private string n_REM_COR = "";

        /// <summary>
        /// 税前计息利息
        /// </summary>
        private string n_INCOME_PT = "";

        /// <summary>
        /// 税后计息利息
        /// </summary>
        private string n_INCOME_AT = "";

        /// <summary>
        /// 税后付息利息
        /// </summary>
        private string n_INCOME_PT_DUE = "";

        /// <summary>
        /// 税后付息利息
        /// </summary>
        private string n_INCOME_AT_DUE = "";

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 老的证券代码
        /// </summary>
        private string oldC_SEC_CODE = "";

        /// <summary>
        /// 计息日期
        /// </summary>
        private string oldD_INCOME = "";

        /// <summary>
        /// 老的计息日
        /// </summary>
        public string OldD_INCOME
        {
            get { return oldD_INCOME; }
            set { oldD_INCOME = value; }
        }


        /// <summary>
        /// 老的证券代码
        /// </summary>
        public string OldC_SEC_CODE
        {
            get { return oldC_SEC_CODE; }
            set { oldC_SEC_CODE = value; }
        }


        /// <summary>
        /// 描述
        /// </summary>
        public string C_DESC
        {
            get { return c_DESC; }
            set { c_DESC = value; }
        }

        /// <summary>
        /// 税后付息利息
        /// </summary>
        public string N_INCOME_AT_DUE
        {
            get { return n_INCOME_AT_DUE; }
            set { n_INCOME_AT_DUE = value; }
        }

        /// <summary>
        /// 税后计息利息
        /// </summary>
        public string N_INCOME_PT_DUE
        {
            get { return n_INCOME_PT_DUE; }
            set { n_INCOME_PT_DUE = value; }
        }

        /// <summary>
        /// 税后计息利息
        /// </summary>
        public string N_INCOME_AT
        {
            get { return n_INCOME_AT; }
            set { n_INCOME_AT = value; }
        }

        /// <summary>
        /// 税前计息利息
        /// </summary>
        public string N_INCOME_PT
        {
            get { return n_INCOME_PT; }
            set { n_INCOME_PT = value; }
        }

        /// <summary>
        /// 剩余本金
        /// </summary>
        public string N_REM_COR
        {
            get { return n_REM_COR; }
            set { n_REM_COR = value; }
        }

        /// <summary>
        /// 票面利率
        /// </summary>
        public string N_COUP_RATE
        {
            get { return n_COUP_RATE; }
            set { n_COUP_RATE = value; }
        }

        /// <summary>
        /// 计息天数
        /// </summary>
        public string N_INCOME_DAYS
        {
            get { return n_INCOME_DAYS; }
            set { n_INCOME_DAYS = value; }
        }

        /// <summary>
        /// 报价方式
        /// </summary>
        public string C_DV_QUT_MOD
        {
            get { return c_DV_QUT_MOD; }
            set { c_DV_QUT_MOD = value; }
        }

        /// <summary>
        /// 计息日期
        /// </summary>
        public string D_INCOME
        {
            get { return d_INCOME; }
            set { d_INCOME = value; }
        }

        /// <summary>
        /// 证券代码
        /// </summary>
        public string C_SEC_CODE
        {
            get { return c_SEC_CODE; }
            set { c_SEC_CODE = value; }
        }

        /// <summary>
        /// 解析list中字符串，被基类调用.
        /// </summary>
        /// <param name="strRespStr">解析字符串.</param>
        public override void listItemParse(string strRespStr)
        {
            parseStrToAttr(strRespStr);
        }

        /// <summary>
        /// 解析字符串
        /// </summary>
        /// <param name="strPojo">被解析的字符串</param>
        private void parseStrToAttr(string strPojo) 
        {
            string[] temArr = Regex.Split(strPojo, "\t");
            c_SEC_CODE = temArr[0];
            d_INCOME = temArr[1];
            c_DV_QUT_MOD = temArr[2];
            n_INCOME_DAYS = temArr[3];
            n_COUP_RATE = temArr[4];
            n_REM_COR = temArr[5];
            n_INCOME_PT = temArr[6];
            n_INCOME_AT = temArr[7];
            n_INCOME_PT_DUE = temArr[8];
            n_INCOME_AT_DUE = temArr[9];
            c_DESC = temArr[10];
            oldC_SEC_CODE = temArr[0];
            oldD_INCOME = temArr[1];
            parseComm(strPojo);
        }

        /// <summary>
        /// 拼接字符串，传送到后台.
        /// </summary>
        /// <returns>返回拼接字符串.</returns>
        public override string buildAttrToStr()
        {
            StringBuilder buf = new StringBuilder();
            buf.Append(c_SEC_CODE).Append("\t");
            buf.Append(d_INCOME).Append("\t");
            buf.Append(c_DV_QUT_MOD).Append("\t");
            buf.Append(n_INCOME_DAYS).Append("\t");
            buf.Append(n_COUP_RATE).Append("\t");
            buf.Append(n_REM_COR).Append("\t");
            buf.Append(n_INCOME_PT).Append("\t");
            buf.Append(n_INCOME_AT).Append("\t");
            buf.Append(N_INCOME_PT_DUE).Append("\t");
            buf.Append(N_INCOME_AT_DUE).Append("\t");
            buf.Append(c_DESC).Append("\t");
            buf.Append(oldC_SEC_CODE).Append("\t");
            buf.Append(oldD_INCOME).Append("\tnull");
            return buf.ToString();
        }
    }
}


