using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

////namespace YssBaseCls.Interface
namespace YssInformation.Support.Interface
{
    /// <summary>
    /// 证券代码接口
    /// </summary>
    public interface I_SecVar
    {
        /// <summary>
        /// 证券品种代码
        /// </summary>
        string C_SEC_VAR_CODE { get; set; }

        /// <summary>
        /// 证券品种名称
        /// </summary>
        string C_SEC_VAR_NAME { get; set; }

        /// <summary>
        ///  证券属性代码
        /// </summary>
        string C_DA_CODE { get; set; }

        /// <summary>
        /// 品种属性的父级代码
        /// </summary>
        string C_DA_CODE_P { get; set; }
    }
}
