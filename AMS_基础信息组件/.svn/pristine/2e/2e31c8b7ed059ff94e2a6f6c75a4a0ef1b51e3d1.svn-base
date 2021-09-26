using System.Collections.Generic;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;

namespace YssInformation.Support.Sys.Dictionary.Service
{
    /// <summary>
    /// 现金流标记字典数据服务接口
    /// 
    /// Created By hs 2016-08-16D
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.sys.cashflow.controller.ICashFlowController", ServiceUrl = "")]
    public interface ICashFlowService : IControlDataService
    {
        /// <summary>
        /// 取得现金流标记代码
        /// add by hs 2016-08-16
        /// </summary>
        /// <returns>现金流标记代码</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getCashFlowCode")]
        List<BasePojo> getCashFlowCode();
    }
}
