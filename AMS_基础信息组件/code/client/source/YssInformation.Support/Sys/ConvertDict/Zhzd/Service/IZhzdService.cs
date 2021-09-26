using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;


namespace YssInformation.Support.Sys.ConvertDict.Zhzd.Service
{
    /// <summary>
    /// Title：转换字典服务类
    /// Author：陈文海
    /// Time：2014-01-21
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.sys.convertdict.zhzd.controller.IZhzdController", ServiceUrl = "")]
    public interface IZhzdService : IServiceBus
    {
    }
}
