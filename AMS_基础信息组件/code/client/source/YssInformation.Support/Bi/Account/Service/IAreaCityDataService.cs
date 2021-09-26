using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.DataService;
using YssInformation.Support.common;
using FAST.Common.Service.Attributes;

namespace YssInformation.Support.Bi.Account.Service
{
    /// <summary>
    /// 区域信息服务接口,主要用于省份、城市下拉框
    /// </summary>
    ///
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.bi.account.controller.IAreaCityDataController", ServiceUrl = "")]
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    public interface IAreaCityDataService : IServiceBus
    {

    }
}
