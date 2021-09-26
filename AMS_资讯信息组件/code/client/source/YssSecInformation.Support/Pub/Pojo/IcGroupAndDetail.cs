//using YssBaseCls.Fun;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;

using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;
using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;
//using YssBaseCls.Pojo;
using FAST.Common.Service.Datastructure;


namespace YssSecInformation.Support.Pub.Pojo
{
    /// <summary>
    ///chenbo 20170819 
    ///TASK #332232 公共信息处理界面的系统初始化接口拆分
    /// ---------------------------------------------------
    /// 接口组关联接口对应的pojo类
    /// </summary>
    [NodeDesc(TreeNode = "C_GROUP_CODE", ParentNode = "C_GROUP_CODE_P")]
    public class IcGroupAndDetail : BasePojo
    {
        /// <summary>
        /// 接口组代码
        /// </summary>
        private string c_GROUP_CODE = "";

        /// <summary>
        /// 接口组名称
        /// </summary>
        private string c_GROUP_NAME = "";

        /// <summary>
        /// 上级接口组代码
        /// </summary>
        private string c_GROUP_CODE_P = "";

        /// <summary>
        /// 来源标志
        /// </summary>
        private string c_IDF_FLAG = "";

        /// <summary>
        /// 序号
        /// </summary>
        private string c_ORDER_BY = "";

        /// <summary>
        /// 是否明细
        /// </summary>
        private string c_IS_DETAIL = "";


        /// <summary>
        /// 接口组代码
        /// </summary>
        public string C_GROUP_CODE
        {
            get { return c_GROUP_CODE; }
            set { c_GROUP_CODE = value; }
        }

        /// <summary>
        /// 接口组名称
        /// </summary>
        public string C_GROUP_NAME
        {
            get { return c_GROUP_NAME; }
            set { c_GROUP_NAME = value; }
        }

        /// <summary>
        /// 上级接口组代码
        /// </summary>
        public string C_GROUP_CODE_P
        {
            get { return c_GROUP_CODE_P; }
            set { c_GROUP_CODE_P = value; }
        }

        /// <summary>
        /// 来源标志
        /// </summary>
        public string C_IDF_FLAG
        {
            get { return c_IDF_FLAG; }
            set { c_IDF_FLAG = value; }
        }

        /// <summary>
        /// 序号
        /// </summary>
        public string C_ORDER_BY
        {
            get { return c_ORDER_BY; }
            set { c_ORDER_BY = value; }
        }

        /// <summary>
        /// 是否明细
        /// </summary>
        public string C_IS_DETAIL
        {
            get { return c_IS_DETAIL; }
            set { c_IS_DETAIL = value; }
        }
    }
}
