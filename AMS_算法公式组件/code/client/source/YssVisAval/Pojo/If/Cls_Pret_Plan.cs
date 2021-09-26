using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;




using FAST.Core.Bussiness.Pojo;
using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;


namespace YssVisAval.Pojo.If
{
    /// <summary>
    /// 执行方案设置
    /// </summary>
    public class Cls_Pret_Plan : Cls_EXEC_PLAN
    {
        /// <summary>
        /// 方案代码
        /// </summary>
        private string c_PLAN_CODE = "";

        /// <summary>
        /// 方案名称
        /// </summary>
        private string c_PLAN_NAME = "";

        /// <summary>
        /// 方案类型
        /// </summary>
        private string c_PLAN_TYPE = "";

        /// <summary>
        /// 父级方案代码
        /// </summary>
        private string c_PLAN_CODE_P = "";

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 执行序号
        /// </summary>
        private string c_ORDER = "";

        /// <summary>
        /// 父节点
        /// </summary>
        private string paraentCode = "";

        /// <summary>
        /// 原来的方案代码
        /// </summary>
        private string strtOlsPlanC0de = "";

        /// <summary>
        /// 父节点
        /// </summary>
        public string FParaentCode
        {
            get { return paraentCode; }
            set { paraentCode = value; }
        }

        /// <summary>
        /// 原来的方案代码
        /// </summary>
        public string SOlsPlanC0de
        {
            get { return strtOlsPlanC0de; }
            set { strtOlsPlanC0de = value; }
        }

        /// <summary>
        /// 方案代码
        /// </summary>
        public new string C_PLAN_CODE
        {
            get { return c_PLAN_CODE; }
            set { c_PLAN_CODE = value; }
        }

        /// <summary>
        /// 方案名称
        /// </summary>
        public new string C_PLAN_NAME
        {
            get { return c_PLAN_NAME; }
            set { c_PLAN_NAME = value; }
        }

        /// <summary>
        /// 方案类型
        /// </summary>
        public new string C_PLAN_TYPE
        {
            get { return c_PLAN_TYPE; }
            set { c_PLAN_TYPE = value; }
        }

        /// <summary>
        /// 父级方案代码
        /// </summary>
        public new string C_PLAN_CODE_P
        {
            get { return c_PLAN_CODE_P; }
            set { c_PLAN_CODE_P = value; }
        }

        /// <summary>
        /// 描述
        /// </summary>
        public new string C_DESC
        {
            get { return c_DESC; }
            set { c_DESC = value; }
        }

        /// <summary>
        /// 执行序号
        /// </summary>
        public string C_ORDER
        {
            get { return c_ORDER; }
            set { c_ORDER = value; }
        }

        /// <summary>
        /// 解析list中字符串，被基类调用
        /// </summary>
        /// <param name="strRespStr"> 要解析的字符串</param>
        public override void listItemParse(string strRespStr)
        {
            parseStrToAttr(strRespStr);
        }

        /// <summary>
        /// 解析后台传来的字符串
        /// －－－－修改记录－－－－
        /// 当前版本：V4.5.0.4
        /// 修改人：chenyoulong
        /// 修改日期：20110307
        /// 修改简介： 解析后台传来的字符串方法的具体实现
        /// </summary>
        /// <param name="strResprStr">要解析的字符串</param>
        public new void parseStrToAttr(string strResprStr)
        {
            string[] tmpAry = Regex.Split(strResprStr, "\t");
            this.c_PLAN_CODE = tmpAry[0];
            this.c_PLAN_NAME = tmpAry[1];
            this.c_PLAN_TYPE = tmpAry[2];
            this.c_PLAN_CODE_P = tmpAry[3];            
            this.c_ORDER = tmpAry[4];
            this.c_DESC = tmpAry[5];
            this.paraentCode = tmpAry[6];
            
            parseComm(strResprStr);

            // 这里赋基类几个参数值          
            this.NodeCode = tmpAry[0];
            this.ParentCode = tmpAry[6];
        }

        /// <summary>
        /// 树形结构解析方法
        /// </summary>
        /// <param name="strRespStr">要解析的字符串</param>
        public override void treeNodeParse(string strRespStr)
        {
            parseStrToAttr(strRespStr);
        }

        /// <summary>
        /// 拼接字符串，传送到后台
        /// </summary>
        /// <returns>拼接好的字符串</returns>
        public override string buildAttrToStr()
        {
            StringBuilder buf = new StringBuilder();

            buf.Append(this.c_PLAN_CODE.Trim()).Append("\t");
            buf.Append(this.c_PLAN_NAME.Trim()).Append("\t");
            buf.Append(this.c_PLAN_TYPE.Trim()).Append("\t");
            buf.Append(this.c_PLAN_CODE_P.Trim()).Append("\t");
            buf.Append(this.c_ORDER.Trim()).Append("\t");
            buf.Append(this.c_DESC.Trim()).Append("\t");
            buf.Append(this.strtOlsPlanC0de.Trim()).Append("\tnull");
            return buf.ToString();
        }

    }
}


