﻿using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using YssInformation.Support.Sys.DcCury.Service;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;

namespace YssInformation.Support.Sys.DcCury.Service
{
    /// <summary>
    /// 币种信息数据服务接口
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.sys.convertdict.zhzd.controller.IZhzdController", ServiceUrl = "")]
    public interface IDcCuryQuyService : IDcCuryService
    {
    }
}




