using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;

using FAST.Common.Service.Attributes;
using FAST.Core.Context;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;



namespace YssSecInformation.Support.Mp.FwMkt.Service
{
    /// <summary>
    /// 远期外汇数据服务接口
    /// author ll 20120925
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.sec.information.support.modules.mp.FwMktValue.controller.IFwMktValueController", ServiceUrl = "")]
    public interface IFwMktValueService : IServiceBus
    {
        /// <summary>
        /// 条件查询
        /// </summary>
        /// <param name="quyCon">quyCon</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondition")]
        QueryRes selectByCondition(string quyCon);
    }
}


