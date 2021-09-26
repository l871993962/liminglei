using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Collections;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;
using YssElecReco.Context;

namespace YssElecReco.Service.Para
{
    [ServiceAttribute(ServiceId = ElecrecoServiceIdConstant.OSGI_ELECRECO, ServiceController = "com.yss.uco.elecreco.support.controller.IErDspManagerServiceController")]
    public interface IErDspManagerService : IServiceBus 
    {
        [MethodAttribute]
        string upadteParam(ArrayList list);
    }
}
