using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;

using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;


namespace YssVisAval.Pojo.If
{
    /// <summary>
    /// 执行方案-项设置
    /// </summary>
    public class Cls_Pret_Plan_Sub : ClsBaseTreeNode
    {
        /// <summary>
        /// 方案代码
        /// </summary>
        private string c_PLAN_CODE = "";       

        /// <summary>
        /// 方案类型
        /// </summary>
        private string c_PLAN_TYPE = "";

        /// <summary>
        /// 核算项代码
        /// </summary>        
        private string c_DVA_ITEM_CODE = "";  

        /// <summary>
        /// 执行序号
        /// </summary>
        private string n_ORDER = "";

        /// <summary>
        /// 方案代码
        /// </summary>
        public string C_PLAN_CODE
        {
            get { return c_PLAN_CODE; }
            set { c_PLAN_CODE = value; }
        }       

        /// <summary>
        /// 方案类型
        /// </summary>
        public string C_PLAN_TYPE
        {
            get { return c_PLAN_TYPE; }
            set { c_PLAN_TYPE = value; }
        }

        /// <summary>
        /// 核算项代码
        /// </summary>
        public string C_DVA_ITEM_CODE
        {
            get { return c_DVA_ITEM_CODE; }
            set { c_DVA_ITEM_CODE = value; }
        }     
     
        /// <summary>
        /// 执行序号
        /// </summary>
        public string N_ORDER
        {
            get { return n_ORDER; }
            set { n_ORDER = value; }
        }

        /// <summary>
        /// 解析list中字符串，被基类调用
        /// </summary>
        /// <param name="strRespStr">要解析的字符串</param>
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
        public void parseStrToAttr(string strResprStr)
        {
            string[] tmpAry = Regex.Split(strResprStr, "\t");
            this.c_PLAN_CODE = tmpAry[0];   
            this.c_PLAN_TYPE = tmpAry[1];
            this.c_DVA_ITEM_CODE = tmpAry[2];
            this.n_ORDER = tmpAry[3];      
            parseComm(strResprStr);

            // 这里赋基类几个参数值          
            this.NodeCode = tmpAry[0];
            this.ParentCode = tmpAry[1];
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
            buf.Append(this.c_PLAN_TYPE.Trim()).Append("\t");        
            buf.Append(this.c_DVA_ITEM_CODE.Trim()).Append("\t");
            buf.Append(this.n_ORDER.Trim()).Append("\tnull");
            return buf.ToString();
        }
    }
}


