using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using YssElecReco.Pojo.Er;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;
using YssElecReco.Context;

namespace YssElecReco.Service.Er
{
    /// <summary>
    /// 浏览电子对账信息service
    /// chenyoulong 20121226
    /// v1.0.0.7
    /// </summary>
    [ServiceAttribute(ServiceId = ElecrecoServiceIdConstant.OSGI_ELECRECO, ServiceController = "com.yss.uco.elecreco.er.eryeb.controller.IErYebServiceController")]
    public interface IErYebService : IServiceBus
    {
        /// <summary>
        /// 根据添加查询电子对账余额信息
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>返回余额列表</returns>
        [MethodAttribute]
        List<ErYeb> getYeData(Dictionary<string, string> paraDict);
    }
}
