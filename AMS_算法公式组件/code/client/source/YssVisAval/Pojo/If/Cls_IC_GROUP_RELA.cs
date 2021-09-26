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
    /// 接口组关联接口对应的pojo类
     /// </summary>
    public class Cls_IC_GROUP_RELA : ClsBaseTreeNode
    {
         /// <summary>
        /// 接口组代码
         /// </summary>
        private string c_GROUP_CODE = "";
      
        /// <summary>
        /// 接口代码
        /// </summary>
        private string c_CFG_CODE = "";

        /// <summary>
        /// 路径
        /// </summary>
        private string c_PATH = "";

        /// <summary>
        /// 节假日群
        /// </summary>
        private string c_HDAY_CODE = "";
       
        /// <summary>
        /// 日期调整
        /// </summary>
        private string n_ADJUST = "";

        /// <summary>
        /// 接口名称
        /// </summary>
        private string c_CFG_NAME = "";

        /// <summary>
        /// 文件路径类型,Z-默认路径，H-临时路径
        /// </summary>
        private string c_Path_type = "Z";

        /// <summary>
        /// 临时路径
        /// </summary>
        private string c_Path_Temp = "";

        /// <summary>
        /// 临时路径
        /// </summary>
        public string C_Path_Temp
        {
            get { return c_Path_Temp; }
            set { c_Path_Temp = value; }
        }

        /// <summary>
        /// 文件路径类型,Z-默认路径，H-临时路径
        /// </summary>
        public string C_Path_type
        {
            get { return c_Path_type; }
            set { c_Path_type = value; }
        }

        /// <summary>
        /// 接口名称
        /// </summary>
        public string C_CFG_NAME
        {
            get { return c_CFG_NAME; }
            set { c_CFG_NAME = value; }
        }

        /// <summary>
        /// 日期调整
        /// </summary>
        public string N_ADJUST
        {
            get { return n_ADJUST; }
            set { n_ADJUST = value; }
        }

        /// <summary>
        /// 接口组代码
        /// </summary>
        public string C_GROUP_CODE
        {
            get { return c_GROUP_CODE; }
            set { c_GROUP_CODE = value; }
        }

        /// <summary>
        /// 接口代码
        /// </summary>
        public string C_CFG_CODE
        {
            get { return c_CFG_CODE; }
            set { c_CFG_CODE = value; }
        }

        /// <summary>
        /// 路径
        /// </summary>
        public string C_PATH
        {
            get { return c_PATH; }
            set { c_PATH = value; }
        }

        /// <summary>
        /// 节假日群
        /// </summary>
        public string C_HDAY_CODE
        {
            get { return c_HDAY_CODE; }
            set { c_HDAY_CODE = value; }
        }

        /// <summary>
        /// 解析字符串，封装为实体类
        /// </summary>
        /// <param name="strRespStr">解析字符串</param>
        public override void treeNodeParse(string strRespStr)
        {
            string[] cfgAry = Regex.Split(strRespStr, "\t");

            this.C_GROUP_CODE = cfgAry[0];
            this.C_CFG_CODE = cfgAry[1];
            this.C_PATH = cfgAry[2];
            this.C_HDAY_CODE = cfgAry[3];
            this.N_ADJUST = cfgAry[4];
            this.C_CFG_NAME = cfgAry[5];
            this.ParentCode = cfgAry[0];
            this.NodeCode = cfgAry[1];
            this.c_Path_Temp = cfgAry[2];
            parseComm(strRespStr);

        }

        /// <summary>
        /// 解析字符串，封装为实体类
        /// </summary>
        /// <param name="strRespStr">解析字符串</param>
        public void listParse(string strRespStr)
        {
            string[] cfgAry = Regex.Split(strRespStr, "\t");

            this.C_GROUP_CODE = cfgAry[0];
            this.C_CFG_CODE = cfgAry[1];
            this.C_PATH = cfgAry[2];
            this.C_HDAY_CODE = cfgAry[3];
            this.N_ADJUST = cfgAry[4];
            this.C_CFG_NAME = cfgAry[5];
            this.c_Path_Temp = cfgAry[2];
            this.NodeCode = cfgAry[1];
            this.ParentCode = cfgAry[0];

        }

        /// <summary>
        /// 将实体类封装为字符串，传到后台解析
        /// </summary>
        /// <returns>拼接字符串</returns>
        public override string buildAttrToStr()
        {
            StringBuilder buf = new StringBuilder();
            buf.Append(this.C_GROUP_CODE).Append("\t");
            buf.Append(this.C_CFG_CODE).Append("\t");
            buf.Append(this.C_PATH).Append("\t");
            buf.Append(this.C_HDAY_CODE).Append("\t");
            buf.Append(this.N_ADJUST).Append("\tnull");
            return buf.ToString();
        }
    }
}




