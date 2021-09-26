using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;
using FAST.Host.Main.Fun;

namespace YssElecRecoStar
{
    /// <summary>
    /// 程序启动项
    /// </summary>
    public static class Program
    {
        /// <summary>
        /// 应用程序的主入口点。
        /// </summary>
        /// <param name="args">args</param>
        [STAThread]
        public static void Main(string[] args)
        {
            Entrance.RunApplication(args);
        }
    }
}
