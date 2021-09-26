using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Services.Base;
using YssElecReco.Pojo.Er.Reverse;
using FAST.Common.Service.Attributes;
using YssElecReco.Context;

namespace YssElecReco.Service.Er.Reverse
{
    [ServiceAttribute(ServiceId = ElecrecoServiceIdConstant.OSGI_ELECRECO, ServiceController = "com.yss.uco.elecreco.er.reverse.map.kmrela.controller.IKmRelaRecordServiceController")]
    public interface IKmRelaRecordService : IServiceBus
    {
        [MethodAttribute]
        QueryRes queryInnerKm(Dictionary<string, string> paraDict);
        [MethodAttribute]
        QueryRes queryOutKm(Dictionary<string, string> paraDict);
        [MethodAttribute]
        List<KmRelaRecord> queryIsMappingKm(Dictionary<string, string> paraDict);
    }
}
