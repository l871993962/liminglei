using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;
using YssElecReco.Context;

namespace YssElecReco.Service.Er.Reverse
{
    [ServiceAttribute(ServiceId = ElecrecoServiceIdConstant.OSGI_ELECRECO, ServiceController = "com.yss.uco.elecreco.er.reverse.map.assmap.controller.IAssMapServiceController")]
	public interface IAssMapService : IServiceBus  
	{
        [MethodAttribute]
        string getDzMode(string portCode, string fileType);
        [MethodAttribute]
        List<string> getTghCodesByPortCode(string portCode);
	}
}