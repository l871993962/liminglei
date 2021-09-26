using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Pojo.Base;
using System.Collections;
using FAST.Common.Service.Attributes;
using YssElecReco.Context;

namespace YssElecReco.Service.Er.Reverse
{
    [ServiceAttribute(ServiceId = ElecrecoServiceIdConstant.OSGI_ELECRECO, ServiceController = "com.yss.uco.elecreco.er.reverse.portrela.controller.IElecPortRelaServiceController")]
    public interface IElecPortRelaService : IServiceBus
    {
        [MethodAttribute]
        List<BasePojo> queryPortRelaOrgan(Dictionary<string, string> paraMap);
    }
}
