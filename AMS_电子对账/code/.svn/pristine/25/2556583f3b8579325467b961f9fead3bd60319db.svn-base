using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;

namespace YssElecReco.Service.Er
{
    /// <summary>
    /// 深证通日志service
    /// </summary>
    [ServiceAttribute(ServiceId = ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ifa.szt.tool.rptlog.controller.IErRptLogController")]
    public interface IErRptLogService : IServiceBus
    {
        /// <summary>
        /// 根据id查询报文数据
        /// </summary>
        /// <param name="id">id</param>
        /// <returns>报文数据</returns>
        [MethodAttribute]
        string queryLogById(string id);
    }
}
