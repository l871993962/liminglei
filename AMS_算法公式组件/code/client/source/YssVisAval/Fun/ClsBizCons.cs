using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace YssVisAval.Fun
{
    /// <summary>
    /// 业务常量
    /// </summary>
    public class ClsBizCons : FAST.Core.Context.ClsConstant
    {
        /// <summary>
        /// 函数类型
        /// </summary>
        public static readonly string FUNC_TYPE = "FUNC_TYPE";

        /// <summary>
        /// 关键字类型
        /// </summary>
        public static readonly string KEY_TYPE = "KEY_TYPE";

        /// <summary>
        /// 后台验证表达式错误的标示
        /// </summary>
        public static readonly string CheckResult = "false";
    }
}
