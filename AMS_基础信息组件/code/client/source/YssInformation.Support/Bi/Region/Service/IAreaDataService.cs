using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.DataService;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;

namespace YssInformation.Support.Bi.Region.Service
{
    /// <summary>
    /// 地区信息设置数据源服务接口
    /// 
    /// created by lihaizhi 2013.05.13
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.bi.region.controller.IAreaDataController", ServiceUrl = "")]
    public interface IAreaDataService : IControlDataService, IKeyConvertDataService
    {
        /// <summary>
        /// 加载地区
        /// </summary>
        /// <returns>1</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAllAreas")]
        List<BasePojo> getAllAreas();
    }
}
