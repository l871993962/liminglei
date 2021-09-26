using YssBaseCls.Fun;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;

using FAST.Core.Resource;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Core.Context;
using System.Text.RegularExpressions;

namespace YssAval.Pojo.AA
{
    /// <summary>
    /// 参数
    /// </summary>
    public class Cls_PARA
    {
        /// <summary>
        /// 函数代码
        /// </summary>
        private string c_FUNC_CODE = null;

        /// <summary>
        /// 参数代码
        /// </summary>
        private string c_PARA_CODE = null;

        /// <summary>
        /// 参数名称
        /// </summary>
        private string c_PARA_NAME = null;

        /// <summary>
        /// 参数类型
        /// </summary>
        private string c_DV_PARA_TYPE = null;

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = null;

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
        /// 参数代码
        /// </summary>
        public string C_PARA_CODE
        {
            set
            {
                c_PARA_CODE = value;
            }

            get
            {
                return c_PARA_CODE;
            }
        }

        /// <summary>
        /// 参数名字
        /// </summary>
        public string C_PARA_NAME
        {
            set
            {
                c_PARA_NAME = value;
            }

            get
            {
                return c_PARA_NAME;
            }
        }

        /// <summary>
        /// 参数类型
        /// </summary>
        public string C_DV_PARA_TYPE
        {
            set
            {
                c_DV_PARA_TYPE = value;
            }

            get
            {
                return c_DV_PARA_TYPE;
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
        /// 解析后台传递过来的字符串
        /// </summary>
        /// <param name="respStr">respStr</param>
        public void parseStr(string respStr)
        {
            // 将后台传来的字符串封装成实体类
            string[] tmpAry = Regex.Split(respStr, "\t");
            c_FUNC_CODE = tmpAry[0];
            c_PARA_CODE = tmpAry[1];
            c_PARA_NAME = tmpAry[2];
            c_DV_PARA_TYPE = tmpAry[3];
            c_DESC = tmpAry[4];
        }


    }
}
