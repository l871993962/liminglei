using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Attributes;
using YssElecReco.Context;

namespace YssElecReco.Service.Er.Reverse
{
    [ServiceAttribute(ServiceId = ElecrecoServiceIdConstant.OSGI_ELECRECO, ServiceController = "com.yss.uco.elecreco.er.reverse.portrela.controller.IElecTghRelaServiceController")]
    public interface IElecTghRelaService : IServiceBus
    {
        [MethodAttribute]
        List<BasePojo> queryOrganByPort(Dictionary<string, string> paraMap);
    }
}
