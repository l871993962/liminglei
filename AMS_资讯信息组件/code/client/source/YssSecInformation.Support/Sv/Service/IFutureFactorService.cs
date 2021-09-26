﻿using FAST.Common.Service.Pojo.Base;
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



namespace YssSecInformation.Support.Sv.Service
{
    /// <summary>
    /// 国债期货债券转换因子服务接口
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.sec.information.support.modules.sv.base.controller.IFutureFactorController", ServiceUrl = "")]
    public interface IFutureFactorService : IServiceBus
    {
    }
}




