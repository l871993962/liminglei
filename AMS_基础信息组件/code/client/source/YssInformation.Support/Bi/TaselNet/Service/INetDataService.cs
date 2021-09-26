using System.Collections.Generic;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.DataService;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;

namespace YssInformation.Support.Bi.TaselNet.Service
{
    /// <summary>
    /// 产品销售网点数据服务接口
    /// 
    /// Created By XiaoZhilong 2013-4-26
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.bi.salesnet.controller.INetDataController", ServiceUrl = "")]
    public interface INetDataService : IControlDataService, IKeyConvertDataService
    {
        /// <summary>
        /// 取得所有数据（只包含代码和名称）
        /// add by liuxiang 2014-6-10
        /// </summary>
        /// <returns>包含代码和名称的键值对</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getShortDataMap")]
        Dictionary<string, string> getShortDataMap();
    }
}
