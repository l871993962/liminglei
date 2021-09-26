using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.DataService;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;

namespace YssInformation.Support.Bi.DacType.Service
{
    /// <summary>
    /// 账户类型数据服务service
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.bi.dactype.controller.IAccountTypeDataController", ServiceUrl = "")]
    public interface IAccountTypeDataService : IControlDataService, IKeyConvertDataService
    {
        
    }
}
