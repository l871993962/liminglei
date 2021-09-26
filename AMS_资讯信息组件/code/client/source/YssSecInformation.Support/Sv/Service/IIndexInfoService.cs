﻿using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Attributes;


namespace YssSecInformation.Support.Sv.Service
{
    /// <summary>
    /// chenbo
    /// 2017-06-22
    /// #42948 资讯信息管理组件化拆分
    /// 指数基本信息服务
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.sec.information.support.modules.sv.indexinfo.controller.IIndexInfoController", ServiceUrl = "")]
    public interface IIndexInfoService : IServiceBus
    {
        /// <summary>
        /// 查询组合关联指数信息
        /// </summary>
        /// <param name="paraDict">查询条件</param>
        /// <returns>查询结果</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPortRelaIndex")]
        QueryRes getPortRelaIndex(Dictionary<string, string> paraDict);
    }
}




