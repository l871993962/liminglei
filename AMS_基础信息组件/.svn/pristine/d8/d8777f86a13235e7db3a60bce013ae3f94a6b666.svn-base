
using System.Collections.Generic;
using FAST.Common.Service.DataService;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;

////namespace YssBaseCls.Service
////namespace YssInformation.Sys.Dictionary.Service
namespace YssInformation.Support.Sys.Dictionary.Service
{
    /// <summary>
    /// 证券品种数据源服务接口
    /// 
    /// 
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.sys.secvar.controller.ISecVarDataController", ServiceUrl = "")]
    public interface ISecVarDataService : IControlDataService, IKeyConvertDataService
    {
        /// <summary>
        /// 取得所有数据（只包含代码和名称）
        /// add by liuxiang 2014-6-10
        /// </summary>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getShortDataMap")]
        Dictionary<string, string> getShortDataMap();
    }
}
