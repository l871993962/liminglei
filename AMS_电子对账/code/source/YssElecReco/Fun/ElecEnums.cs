using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace YssElecReco.Fun
{
    /// <summary>
    /// 枚举类
    /// </summary>
    public class ElecEnums
    {
        /// <summary>
        /// 对账类型枚举
        /// </summary>
        public enum ElecShowType
        {
            /// <summary>
            /// 余额表
            /// </summary>
            CHECK_STO,

            /// <summary>
            /// 核算表
            /// </summary>
            CHECK_ACT,

            /// <summary>
            /// 双估值表
            /// </summary>
            CHECK_DBLGZ,

            /// <summary>
            /// 净值表
            /// </summary>
            CHECK_AST,

            /// <summary>
            /// 科目表
            /// </summary>
            CHECK_KM,

            /// <summary>
            /// 所有者权益（基金净值）变动表
            /// </summary>
            CHECK_SYZQY,

            /// <summary>
            /// 利润表
            /// </summary>
            CHECK_LR,

            /// <summary>
            /// 资产负债表
            /// </summary>
            CHECK_ZCFZ,

            /// <summary>
            /// 净资产变动表
            /// </summary>
            CHECK_JZCBD
        }

        /// <summary>
        /// 对账结果类型枚举
        /// </summary>
        public enum ElecResultType
        {
            /// <summary>
            /// 余额表
            /// </summary>
            RESULT_STO,

            /// <summary>
            /// 核算表
            /// </summary>
            RESULT_ACT,

            /// <summary>
            /// 双估值表
            /// </summary>
            CHECK_DBLGZ,

            /// <summary>
            /// 净值表
            /// </summary>
            RESULT_AST,

            /// <summary>
            /// 科目表
            /// </summary>
            RESULT_KM,

            /// <summary>
            /// 所有者权益（基金净值）变动表
            /// </summary>
            RESULT_SYZQY,

            /// <summary>
            /// 利润表
            /// </summary>
            RESULT_LR,

            /// <summary>
            /// 资产负债表
            /// </summary>
            RESULT_ZCFZ,

            /// <summary>
            /// 净资产变动表
            /// </summary>
            RESULT_JZCBD
        }
    }
}
