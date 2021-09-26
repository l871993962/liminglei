using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using FAST.Common.Service.Pojo.Base;

namespace YssVisAval.Pojo
{
    /// <summary>
    ///  创建简介：估值核算执行方案实体类
    ///  创建人：leeyu
    ///  创建时间：20110601
    ///  创建版本：V4.5.0.1
    /// </summary>
    public class Cls_EXEC_PLAN : ClsBaseTreeNode
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
        ///  描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 修改前的方案代码
        /// </summary>
        private string c_OLD_PLAN_CODE = "";

        /// <summary>
        /// 修改前的方案类型
        /// </summary>
        private string c_OLD_PLAN_TYPE = ""; 
        
        /// <summary>
        /// 方案类型(核算执行：AO_EXEC；核算级别：AO_LEVEL；核算参数：AO_PARA)
        /// add by chenzhong 2011-05-13
        /// </summary>
        private string c_PLAN_TYPE = "";

        /// <summary>
        /// 父级代码
        /// </summary>
        private string c_PLAN_Code_P = "";

        /// <summary>
        /// 类型
        /// </summary>
        private string dATA_TYPE = "";

        /// <summary>
        /// 方案类型(核算执行：AO_EXEC；核算级别：AO_LEVEL；核算参数：AO_PARA) 
        /// add by chenzhong 2011-05-13
        /// </summary>
        public string C_PLAN_TYPE
        {
            get { return c_PLAN_TYPE; }
            set { c_PLAN_TYPE = value; }
        
        }        

        /// <summary>
        /// 方案代码
        /// </summary>
        public string OLD_C_PLAN_CODE
        {
            get 
                {
                    return c_OLD_PLAN_CODE; 
                }
            
            set 
                { 
                    c_OLD_PLAN_CODE = value; 
                }
        }

        /// <summary>
        /// 方案类型
        /// </summary>
        public string OLD_C_PLAN_TYPE
        {
            get
            {
                return c_OLD_PLAN_TYPE;
            }

            set
            {
                c_OLD_PLAN_TYPE = value;
            }
        }

        /// <summary>
        /// 方案代码
        /// </summary>
        public string C_PLAN_CODE
        {
            set
            {
                c_PLAN_CODE = value;
            }

            get
            {
                return c_PLAN_CODE;
            }
        }

        /// <summary>
        /// 方案名称
        /// </summary>
        public string C_PLAN_NAME
        {
            set
            {
                c_PLAN_NAME = value;
            }

            get
            {
                return c_PLAN_NAME;
            }
        }

        /// <summary>
        /// 描述
        /// </summary>
        public string C_DESC
        {
            set
            {
                c_DESC = value;
            }

            get
            {
                return c_DESC;
            }
        }

        /// <summary>
        /// 方案代码
        /// </summary>
        public string C_PLAN_CODE_P 
        {
            set 
            {
                c_PLAN_Code_P = value;
            }

            get 
            {
                return c_PLAN_Code_P;
            }
        }

        /// <summary>
        /// 类型
        /// </summary>
        public string DATA_TYPE
        {
            set
            {
                dATA_TYPE = value;
            }

            get
            {
                return dATA_TYPE;
            }
        }

        /// <summary>
        /// 解析list中字符串，被基类调用
        /// </summary>
        /// <param name="c_RespStr">待解析的单行字符串数据</param>
        public override void treeNodeParse(string c_RespStr)
        {
            parseStrToAttr(c_RespStr);
        }


        /// <summary>
        /// 解析后台传来的字符串
        /// </summary>
        /// <param name="strPojo">待解析的单行字符串数据</param>
        public void parseStrToAttr(string strPojo)
        {
            string[] tmpAry = System.Text.RegularExpressions.Regex.Split(strPojo, "\t");
            this.c_PLAN_CODE = tmpAry[0];
            this.c_PLAN_NAME = tmpAry[1];
            this.NodeCode = tmpAry[0];
            this.c_PLAN_TYPE = tmpAry[3];
            this.ParentCode = tmpAry[2];
            this.c_DESC = tmpAry[4];
            this.dATA_TYPE = tmpAry[5];
            this.KeyValue = c_PLAN_CODE;
            this.c_PLAN_Code_P = tmpAry[2];
            this.c_OLD_PLAN_CODE = tmpAry[0];
            this.c_OLD_PLAN_TYPE = tmpAry[3];
            this.parseRecLog(strPojo);
        }


        /// <summary>
        /// 拼接字符串，传送到后台
        /// </summary>
        /// <returns>返回一行完整的带格式的字符串数据</returns>
        public override string buildAttrToStr()
        {
            StringBuilder buf = new StringBuilder();
            buf.Append(this.c_PLAN_CODE).Append("\t");
            buf.Append(this.c_PLAN_NAME).Append("\t");
            buf.Append(this.c_DESC).Append("\t");
            buf.Append(this.C_PLAN_TYPE).Append("\t");
            buf.Append(this.c_OLD_PLAN_CODE).Append("\t");
            buf.Append(this.c_OLD_PLAN_TYPE).Append("\t");
            buf.Append(this.CheckStateId).Append("\tnull");
            return buf.ToString();
        }

        /// <summary>
        /// 解析list中字符串，被基类调用
        /// </summary>
        /// <param name="strRespStr">要解析的字符串</param>
        public override void listItemParse(string strRespStr)
        {
            parseStrToAttr(strRespStr);
        }
    }
}
