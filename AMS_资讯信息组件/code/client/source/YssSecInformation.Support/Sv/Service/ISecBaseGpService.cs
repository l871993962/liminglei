﻿﻿using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;
using FAST.Common.Service.Attributes;

using System.Collections.Generic;

namespace YssSecInformation.Support.Sv.Service
{
    /// <summary>
    /// 股票基本信息数据服务接口
    /// 
    /// Create By Huxingtao 2012-08-12
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.sec.information.support.modules.sv.base.controller.ISecBaseGpController", ServiceUrl = "")]
    public interface ISecBaseGpService : IServiceBus
    {
        /// <summary>
        /// 条件查询
        /// </summary>
        /// <param name="quyCon">1</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondition")]
        QueryRes selectByCondition(string quyCon);
      
    }
}




