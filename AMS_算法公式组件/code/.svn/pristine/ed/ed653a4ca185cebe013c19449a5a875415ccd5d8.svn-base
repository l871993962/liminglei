﻿using System;
using System.Collections.Generic;
using System.Text;

using System.Text.RegularExpressions;
using FAST.Common.Service.Pojo.Base;

namespace YssVisAval.Pojo.AA
{

            // / <summary>
    /// 高级算法pojo类
    /// </summary>
    public class Cls_Advanecd_Algorithm : ClsBasePojo
    {

            // / <summary>
        /// 算法代码
        /// </summary>
        private string c_ALGO_CODE = null;

        /// <summary>
        /// 算法名称
        /// </summary>
        private string c_ALGO_NAME = null;

        /// <summary>
        /// 算法公式
        /// </summary>
        private string c_ALGO_FORMULA = null;

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = null;

        /// <summary>
        /// 原算法代码
        /// </summary>
        private string c_OLD_ALGO_CODE = null;

        /// <summary>
        /// 算法类型
        /// </summary>
        private string c_DV_ALGO_TYPE = null;

        /// <summary>
        /// 算法表达式
        /// </summary>
        private string c_ALGO_FORMULA_TRANSFORM = null;

        /// <summary>
        /// 算法表达式
        /// </summary>
        public string C_ALGO_FORMULA_TRANSFORM
        {
            get { return c_ALGO_FORMULA_TRANSFORM; }
            set { c_ALGO_FORMULA_TRANSFORM = value; }
        }

        /// <summary>
        /// 算法类型
        /// </summary>
        public string C_DV_ALGO_TYPE
        {
            get { return c_DV_ALGO_TYPE; }
            set { c_DV_ALGO_TYPE = value; }
        }
            
        /// <summary>
        /// 算法代码
        /// </summary>
        public string C_ALGO_CODE
        {
            get { return c_ALGO_CODE; }
            set { c_ALGO_CODE = value; }
        }

        /// <summary>
        /// 算法名称
        /// </summary>
        public string C_ALGO_NAME
        {
            get { return c_ALGO_NAME; }
            set { c_ALGO_NAME = value; }
        }

        /// <summary>
        /// 算法公式
        /// </summary>
        public string C_ALGO_FORMULA
        {
            get { return c_ALGO_FORMULA; }
            set { c_ALGO_FORMULA = value; }
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
        /// 原算法代码
        /// </summary>
        public string OLD_ALGO_CODE
        {
            get { return c_OLD_ALGO_CODE; }
            set { c_OLD_ALGO_CODE = value; }
        }

        /// <summary>
        /// 解析字符串，封装为实体类
        /// </summary>
        /// <param name="strRespStr">解析字符串</param>
        public override void listItemParse(string strRespStr)
        {
            string[] cfgAry = Regex.Split(strRespStr, "\t");
            this.c_ALGO_CODE = cfgAry[0];
            this.c_ALGO_NAME = cfgAry[1];
            this.c_ALGO_FORMULA = cfgAry[2];
            this.c_DESC = cfgAry[3];
            this.c_DV_ALGO_TYPE = cfgAry[4];
            this.c_ALGO_FORMULA_TRANSFORM = cfgAry[5];
            this.OLD_ALGO_CODE = cfgAry[0];
            this.parseComm(strRespStr);
            parseComm(strRespStr);

        }

        /// <summary>
        /// 将实体类封装为字符串，传到后台解析
        /// </summary>
        /// <returns>拼接字符串</returns>
        public override string buildAttrToStr()
        {
            StringBuilder buf = new StringBuilder();
            buf.Append(this.c_ALGO_CODE).Append("\t");
            buf.Append(this.c_ALGO_NAME).Append("\t");
            buf.Append(this.c_ALGO_FORMULA).Append("\t");
            buf.Append(this.c_DESC).Append("\t");
            buf.Append(this.c_OLD_ALGO_CODE).Append("\t");
            buf.Append(this.c_DV_ALGO_TYPE).Append("\t");
            buf.Append(this.c_ALGO_FORMULA_TRANSFORM).Append("\tnull");
            return buf.ToString();
        }

    }
}


