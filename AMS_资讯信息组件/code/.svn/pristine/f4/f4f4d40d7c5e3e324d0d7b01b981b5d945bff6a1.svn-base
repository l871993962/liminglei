using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Exceptions;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using YssSecInformation.Support.Mp.PreStock.Pojo;
using FAST.Common.Service.Attributes;


namespace YssSecInformation.Support.Mp.PreStock.Service
{
    /// <summary>
    /// 
    /// Add by liyongjun
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.sec.information.support.modules.mp.preStockInterest.controller.IPreStockInterestController", ServiceUrl = "")]
    public interface IPreStockInterestService : IServiceBus
    {
        /// <summary>
        /// 初始化单只证券的历史付息及每百元利息
        /// </summary>
        /// <param name="pre">pre</param>
        /// <returns>void</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/singleSecInitFi")]
        string singleSecInitFi(PreStockInterest pre);

        /// <summary>
        /// 初始化多只股票的优先股历史付息及每百元利息
        /// </summary>
        /// <param name="preList">preList</param>
        /// <returns>void</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/multiplePreInitFi")]
        string multiplePreInitFi(List<PreStockInterest> preList);
    }
}


