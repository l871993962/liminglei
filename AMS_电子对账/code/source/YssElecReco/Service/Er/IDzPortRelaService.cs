using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Services.Base;

namespace YssElecReco.Service.Er
{
    /// <summary>
    /// 组合关联关系
    /// </summary>
     public interface IDzPortRelaService : IServiceBus
    {
         /// <summary>
        /// queryPortRelaOrganPage
         /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <param name="page">page</param>
        /// <returns>QueryRes</returns>
        QueryRes queryPortRelaOrganPage(Dictionary<string, string> paraDict, PageInation page);

         /// <summary>
        /// queryPortRelaSubOrganPage
         /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <param name="page">page</param>
        /// <returns>QueryRes</returns>
        QueryRes queryPortRelaSubOrganPage(Dictionary<string, string> paraDict, PageInation page);
    }
}
