using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;

namespace YssVisAval.Pojo.AA
{
    /// <summary>
    /// 公式配置中的函数pojo
    /// </summary>
    public class ClsFunc
    {
        /// <summary>
        /// 函数代码
        /// </summary>
        private string c_FUNC_CODE = null;

        /// <summary>
        /// 函数名称
        /// </summary>
        private string c_FUNC_NAME = null;

        /// <summary>
        /// 函数类型
        /// </summary>
        private string c_DV_FUNC_TYPE = null;

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = null;

        /// <summary>
        /// 参数列表
        /// </summary>
        private List<Cls_FUNC_PARA> paraList = null;

        /// <summary>
        /// 函数在前台的展示
        /// </summary>
        private string c_FUNC_SHOW = null;

        /// <summary>
        /// 函数在前台的展示
        /// </summary>
        public string C_FUNC_SHOW
        {
            get { return c_FUNC_SHOW; }
            set { c_FUNC_SHOW = value; }
        }

        /// <summary>
        /// 函数代码
        /// </summary>
        public string C_FUNC_CODE
        {
            set
            {
                c_FUNC_CODE = value;
            }

            get
            {
                return c_FUNC_CODE;
            }
        }

        /// <summary>
        /// 函数名字
        /// </summary>
        public string C_FUNC_NAME
        {
            set
            {
                c_FUNC_NAME = value;
            }

            get
            {
                return c_FUNC_NAME;
            }
        }

        /// <summary>
        /// 函数类型
        /// </summary>
        public string C_DV_FUNC_TYPE
        {
            set
            {
                c_DV_FUNC_TYPE = value;
            }

            get
            {
                return c_DV_FUNC_TYPE;
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
        /// 函数参数数组
        /// </summary>
        public List<Cls_FUNC_PARA> ParaList
        {
            set
            {
                paraList = value;
            }

            get
            {
                return paraList;
            }
        }

        /// <summary>
        /// 解析后台传递过来的字符串
        /// </summary>
        /// <param name="respStr">respStr</param>
        public void parseStr(string respStr)
        {
            string[] strPara = null;
            if (null == paraList)
            {
                paraList = new List<Cls_FUNC_PARA>(); 
            }

            Cls_FUNC_PARA clsPara = new Cls_FUNC_PARA();

        //// 将后台传来的字符串封装成实体类
            string[] tmpAry = Regex.Split(respStr, "-");
            c_FUNC_CODE = tmpAry[0];
            c_FUNC_NAME = tmpAry[1];
            c_DV_FUNC_TYPE = tmpAry[2];
            c_DESC = tmpAry[3];
            c_FUNC_SHOW = tmpAry[5];
            strPara = Regex.Split(tmpAry[4], ",");
            foreach (string str in strPara)
            {
                clsPara = new Cls_FUNC_PARA();
                clsPara.parseStr(str);
                paraList.Add(clsPara);
            }
           
        }


       

       

        
    }
}


