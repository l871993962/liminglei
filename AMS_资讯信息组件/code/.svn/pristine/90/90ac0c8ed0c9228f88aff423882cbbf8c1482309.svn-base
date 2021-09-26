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
using FAST.Common.Service.Attributes;



namespace YssSecInformation.Support.Mp.Hggthq.Service
{
    /// <summary>
    /// 回购收益行情接口
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.sec.information.support.modules.mp.hggthq.controller.ICounterRateController", ServiceUrl = "")]
    public interface ICounterRateService : IServiceBus
    {
        /// <summary>
        /// 根据公共回购收益行情变更对应证券利率
        /// </summary>
        /// <param name="mktDate">行情日期</param>
        /// <param name="duration">回购期限</param>
        /// <param name="rate">变更利率</param>
        /// <returns>count</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/updateSecRate")]
        int updateSecRate([RequestBodyAttribute(ParameterName = "mktDate")] DateTime mktDate, [RequestBodyAttribute(ParameterName = "duration")] int duration, [RequestBodyAttribute(ParameterName = "rate")] double rate);
    }
}


