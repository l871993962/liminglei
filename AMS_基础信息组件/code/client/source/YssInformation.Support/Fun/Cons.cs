using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace YssInformation.Support.Fun
{
    /// <summary>
    /// 定义财务的公共常量
    /// </summary>
    public class Cons
    {
        /// <summary>
        /// 加载核算元素的KTable控件名称
        /// </summary>
        public const string NAME_DAE = "tableDae";
        /// <summary>
        /// 加载科目辅助元素的KTable控件名称
        /// add by Yuntao Lau 2015.11.23 STORY #26998
        /// </summary>
        public const string NAME_AUX_DAE = "tableAuxDae";

        /// <summary>
        /// 组合下拉控件名称
        /// </summary>
        public const string NAME_PORT = "cboPort";

        /// <summary>
        /// 业务日期
        /// </summary>
        public const string NAME_DATE = "dtpVchDate";

        /// <summary>
        /// 科目类别名称的控件名称
        /// </summary>
        public const string NAME_KM_CLS = "cbo_KM_CLS";

        /// <summary>
        /// 科目的POJO类的名称，用于在非科目设置的界面加载科目关联的信息
        /// </summary>
        public const string NAME_KM = "KM_Pojo";

        /// <summary>
        /// 不核算的任意值
        /// </summary>
        public const string DAE_NA = "[NA]";

        /// <summary>
        /// 定义核算项目：普通科目
        /// STORY #26934 byleeyu20151105
        /// </summary>
        public const string DAI_PTKM = "PTKM";

        /// <summary>
        /// 标记是否进行核算元素过滤显示 需求 4068
        /// </summary>
        public const string ISFILTER_DAE = "filterDae";

        /// <summary>
        /// 标记 凭证界面加载核算元素模式 By Jinghehe 2014-2-11
        /// </summary>
        public const string VCH_LOAD_DAE = "VCH_LOAD_DAE";

        /// <summary>
        /// 标记科目体系加载科目辅助元素模式
        /// add by Yuntao Lau 2015.12.01 STORY #26998
        /// </summary>
        public const string KM_LOAD_DAE = "KM_LOAD_DAE";

        /// <summary>
        /// 标记加载科目辅助元素模式
        /// add by Yuntao Lau 2015.12.01 STORY #26998
        /// </summary>
        public const string LOAD_AUX_DAE = "LOAD_AUX_DAE";

        /// <summary>
        /// 标记 凭证界面加载核算元素模式 保存的set界面对象 By Jinghehe 2014-2-17
        /// </summary>
        public const string SET_FORM = "SET_FORM";

        /// <summary>
        /// 标记 凭证界面科目余额  By Jinghehe 2014-5-19
        /// </summary>
        public const string NAME_BAL = "txtBalMoney";
    }
}
