using FAST.Common.Service.Services.Base;
using System.Collections.Generic;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Attributes;


////namespace FAST.Platform.Dict.Service
////namespace YssInformation.Sys.Dictionary.Service
namespace YssInformation.Support.Sys.Dictionary.Service
{
    /// <summary>
    /// 销售/交易方式字典接口
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.sys.dttdmode.controller.IDttdModeController", ServiceUrl = "")]
    public interface IDttdModeService : IServiceBus
    {
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getTdModeBusi")]
        QueryRes getTdModeBusi();
    }
}
