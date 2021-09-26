using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Core.Context;

namespace YssSztTool.Context
{
    /// <summary>
    /// AssociaType：Associa功能菜单代码管理类。
    /// 注意：
    /// 1、方法名必须和参数名一致，区分大小写。
    /// 2、方法名、参数名，与功能菜单XML配置文件中的FunCode一致。
    /// </summary>;
    public static class AssociaType
    {
        /// <summary>
        /// 深证通伺服器服务
        /// </summary>
        public static Associa erPara = Associa.Generate("erPara");
        /// <summary>
        /// STORY #72464电子对账关于许可控制的功能模块改造 新增电子对账词汇字典下拉框
        /// </summary>
        public static Associa base_ervoc = Associa.Generate("base_ervoc");


    }
}
