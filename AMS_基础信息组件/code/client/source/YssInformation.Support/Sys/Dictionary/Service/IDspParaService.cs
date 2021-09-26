﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;

////namespace FAST.Platform.Dict.Service
////namespace YssInformation.Sys.Dictionary.Service
namespace YssInformation.Support.Sys.Dictionary.Service
{
    /// <summary>
    /// 核算参数接口
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.sys.dsppara.controller.IDspParaController", ServiceUrl = "")]
    public interface IDspParaService :IServiceBus
    {
    }
}
