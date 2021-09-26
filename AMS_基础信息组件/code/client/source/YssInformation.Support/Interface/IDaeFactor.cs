using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Interface;

namespace YssInformation.Support.Interface
{
    /// <summary>
    /// 设置DAE关联信息
    /// </summary>
    public interface IDaeFactor
    {
        /// <summary>
        /// 设置DAE关联参数
        /// </summary>
        I_DAE_FIELD DaeFactor { set; get; }

        /// <summary>
        /// 设置AuxDAE关联参数
        /// add by Yuntao Lau 2015.12.01 STORY #26998
        /// </summary>
        I_KMAux AuxDaeFactor { set; get; }
    }
}
