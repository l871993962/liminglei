using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.DataService;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.Attributes;

namespace YssSztTool.Service.Para
{
    /// <summary>
    /// 电子对账词汇service
    /// </summary>
    [ServiceAttribute(ServiceId = ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ifa.szt.tool.para.service.controller.IErVocDataController")]
    public interface IErVocDataService : IControlDataService, IKeyConvertDataService
    {
    }
}
