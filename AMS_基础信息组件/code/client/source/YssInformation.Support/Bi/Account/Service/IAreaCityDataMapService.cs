using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.DataService;
using FAST.Common.Service.Services.Base;

namespace YssInformation.Support.Bi.Account.Service
{
    /// <summary>
    /// 省份、城市转换
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    public interface IAreaCityDataMapService : IControlDataService, IKeyConvertDataService
    {
    }
}
