using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using YssElecReco.Pojo.Er.Reverse;
using FAST.Common.Service.Attributes;
using YssElecReco.Context;

namespace YssElecReco.Service.Er.Reverse
{
    [ServiceAttribute(ServiceId = ElecrecoServiceIdConstant.OSGI_ELECRECO, ServiceController = "com.yss.uco.elecreco.er.reverse.compare.controller.IReveDzServiceController")]
    public interface IReveDzService
    {
        [MethodAttribute]
        string compareErDataOper(List<ErReveInfo> infos);
    }
}
