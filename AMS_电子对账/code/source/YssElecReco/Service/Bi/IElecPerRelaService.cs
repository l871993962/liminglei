using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;
using YssElecReco.Context;

namespace YssElecReco.Service.Bi
{
    /// <summary>
    /// 电子对账指标个性化关联
    /// </summary>
    [ServiceAttribute(ServiceId = ElecrecoServiceIdConstant.OSGI_ELECRECO, ServiceController = "com.yss.uco.elecreco.bi.elecrela.controller.IElecPerRelaServiceController")]    
    public interface IElecPerRelaService : IServiceBus
    {
    }
}
