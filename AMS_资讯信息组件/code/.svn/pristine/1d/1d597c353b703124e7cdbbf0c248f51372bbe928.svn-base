﻿using FAST.Core.Util;
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
using FAST.Common.Service.Attributes;


namespace YssSecInformation.Support.Sv.Service
{
    /// <summary>
    /// 期货保证金信息调整服务类
    /// add by chenwenhai 20140705
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.sec.information.support.modules.sv.base.controller.ISecBaseQhbzjController", ServiceUrl = "")]
    public interface ISecBaseQhbzjService : IServiceBus
    {
        /// <summary>
        /// 查询期货保证金信息
        /// </summary>
        /// <param name="paraMap">查询条件</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/querybzj")]
        QueryRes querybzj(Dictionary<string, string> paraMap);
    }
}




