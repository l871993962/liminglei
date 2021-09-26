using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.DataService;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;

namespace YssInformation.Support.Bi.AccountTree.Service
{
    /// <summary>
    /// 账户树形结构A区DataService
    /// </summary>
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.bi.accountTreeA.controller.IAccountTreeADataController", ServiceUrl = "")]
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    public interface IAccountTreeADataService : IControlDataService, IKeyConvertDataService
    {
    }
}
