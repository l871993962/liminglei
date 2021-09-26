using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.DataService;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;

namespace YssInformation.Support.Bi.Holidays.Service
{
    /// <summary>
    /// 节假日群数据服务接口
    /// 
    /// Created By XiaoZhilong 2013-4-26
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.bi.hdaygroup.controller.IHDayDataController", ServiceUrl = "")]
    public interface IHDayDataService : IControlDataService, IKeyConvertDataService
    {
    }
}
