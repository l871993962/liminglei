using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;



namespace YssProductInfo.Support.Cp.PubAcc.Service
{
    /// <summary>
    /// 公用账户信息设置接口
    /// chenyoulong 20121106
    /// v1.0.0.4
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.product.information.support.modules.cp.pubacc.controller.IPubAccController", ServiceUrl = "")]
    public interface IPubAccService : IServiceBus
    {
    }
}
