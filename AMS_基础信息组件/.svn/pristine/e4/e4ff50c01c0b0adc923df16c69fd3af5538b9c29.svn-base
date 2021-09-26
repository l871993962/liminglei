using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
////using YssBaseCls.Pojo;
using FAST.Common.Service.Pojo.Base;

////namespace YssBaseCls.Pojo
namespace YssInformation.Support.Pojo
{
    /// <summary>
    /// 词汇信息项字典
    /// </summary>
    public class MarketVoc : ClsBasePojo
    {
        /// <summary>
        /// 词汇类型
        /// </summary>
        private string c_DV_MKT_TYPE = "";

        /// <summary>
        /// 词汇代码
        /// </summary>
        private string c_MKTVOC_CODE = "";

        /// <summary>
        /// 词汇名称
        /// </summary>
        private string c_MKTVOC_NAME = "";

      

        /// <summary>
        /// 词汇类型
        /// </summary>
        public string C_DV_MKT_TYPE
        {
            get { return c_DV_MKT_TYPE; }
            set { c_DV_MKT_TYPE = value; }
        }

        /// <summary>
        /// 词汇代码
        /// </summary>
        public string C_MKTVOC_CODE
        {
            get { return c_MKTVOC_CODE; }
            set { c_MKTVOC_CODE = value; }
        }

        /// <summary>
        /// 词汇名称
        /// </summary>
        public string C_MKTVOC_NAME
        {
            get { return c_MKTVOC_NAME; }
            set { c_MKTVOC_NAME = value; }
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
            this.c_MKTVOC_CODE = aryPojo[0];
            this.c_MKTVOC_NAME = aryPojo[1];
            this.c_DV_MKT_TYPE = aryPojo[2];
            
            
        }

        /////// <summary>
        /////// 重写基类树形结构的解析方法
        /////// </summary>
        /////// <param name="respStr">respStr</param>
        ////public override void treeNodeParse(string respStr)
        ////{
        ////    parseStrToAttr(respStr);
        ////}

        /// <summary>
        /// 拼接词汇信息
        /// </summary>
        /// <returns>返回数据串</returns>
        public override string buildAttrToStr()
        {
            StringBuilder buf = new StringBuilder();
            buf.Append(c_MKTVOC_CODE).Append("\t");
            buf.Append(c_MKTVOC_NAME).Append("\t");
            buf.Append(c_DV_MKT_TYPE).Append("\tnull");
            return buf.ToString();   
        }
    }
}
