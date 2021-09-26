using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using System.Text.RegularExpressions;
using FAST.Common.Service.Pojo.Base;

namespace YssInformation.Support.Sys.ConvertDict.Zdorg.Pojo
{
    public class Cls_ZD_ORG : ClsBaseTreeNode
    {
        /// <summary>
        /// 组织架构代码
        /// </summary>
        private string c_GROUP_CODE = "";

        /// <summary>
        /// 组织架构名称
        /// </summary>
        private string c_GROUP_NAME = "";

        /// <summary>
        /// 父级节点
        /// </summary>
        private string c_GROUP_CODE_P = "[root]";

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 旧的组织架构代码
        /// </summary>
        private string c_sOldCorpOrgCode = "";

        /// <summary>
        /// 数据类型
        /// </summary>
        private string c_DATA_TYPE = "";

        /// <summary>
        /// 数据类型
        /// </summary>
        public string C_DATA_TYPE
        {
            get { return c_DATA_TYPE; }
            set { c_DATA_TYPE = value; }
        }

        /// <summary>
        /// 组织架构代码get set方法
        /// </summary>
        public string C_GROUP_CODE
        {
            get { return c_GROUP_CODE; }
            set { c_GROUP_CODE = value; }
        }

        /// <summary>
        /// 组织架构名称get set方法
        /// </summary>
        public string C_GROUP_NAME
        {
            get { return c_GROUP_NAME; }
            set { c_GROUP_NAME = value; }
        }

        /// <summary>
        /// 父级节点get set方法
        /// </summary>
        public string C_GROUP_CODE_P
        {
            get { return c_GROUP_CODE_P; }
            set { c_GROUP_CODE_P = value; }
        }

        /// <summary>
        /// 描述get set方法
        /// </summary>
        public string C_DESC
        {
            get { return c_DESC; }
            set { c_DESC = value; }
        }

        /// <summary>
        /// 旧的组织架构代码get set方法
        /// </summary>
        public string OldCorpOrgCode
        {
            get { return c_sOldCorpOrgCode; }
            set { c_sOldCorpOrgCode = value; }
        }

        ////public override string builderRowStr()
        ////{
        ////    StringBuilder buf = new StringBuilder();
        ////    buf.Append(this.c_GROUP_CODE.Trim()).Append("\t");
        ////    buf.Append(this.c_GROUP_NAME.Trim()).Append("\t");
        ////    buf.Append(this.c_GROUP_CODE_P.Trim()).Append("\t");
        ////    buf.Append(this.c_DESC.Trim()).Append("\t");
        ////    buf.Append(this.m_sOldCorpOrgCode).Append("\t");
        ////    buf.Append(base.CheckStateId).Append("\tnull");

        ////    return buf.ToString();
        ////}

        /// <summary>
        /// 把对象的属性以\t为间隔拼接成字符串
        /// </summary>
        /// <returns>拼接好的字符串</returns>
        public override string buildAttrToStr()
        {
            StringBuilder buf = new StringBuilder();
            buf.Append(this.c_GROUP_CODE.Trim()).Append("\t");
            buf.Append(this.c_GROUP_NAME.Trim()).Append("\t");
            buf.Append(this.c_GROUP_CODE_P.Trim()).Append("\t");
            buf.Append(this.c_DESC.Trim()).Append("\t");
            buf.Append(this.c_sOldCorpOrgCode).Append("\tnull");

            return buf.ToString();
        }

        /// <summary>
        /// 拆分字符串,给属性赋值
        /// </summary>
        /// <param name="strResp">字符串数据</param>
        public override void treeNodeParse(string strResp)
        {
            string[] tmpAry = Regex.Split(strResp, "\t");
            this.c_GROUP_CODE = tmpAry[0];
            this.c_GROUP_NAME = tmpAry[1];
            this.c_GROUP_CODE_P = tmpAry[2];
            this.c_DESC = tmpAry[3];
            this.c_DATA_TYPE = tmpAry[4];
            this.OldCorpOrgCode = tmpAry[0];

            this.ParentCode = c_GROUP_CODE_P;
            this.NodeCode = c_GROUP_CODE;

            // 解析公共字段
            this.parseComm(strResp);
        }

        /// <summary>
        /// 解析字符串
        /// </summary>
        /// <param name="strResp">数据</param>
        public override void listItemParse(string strResp)
        {
            treeNodeParse(strResp);
        }
    }
}
