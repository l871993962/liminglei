using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using YssElecReco.Pojo.Er.Reverse;
using FAST.Common.Service.Attributes;
using YssElecReco.Context;

namespace YssElecReco.Service.Er.Reverse
{
    [ServiceAttribute(ServiceId = ElecrecoServiceIdConstant.OSGI_ELECRECO, ServiceController = "com.yss.uco.elecreco.er.reverse.manager.info.controller.IErReveInfoServiceController")]
	public interface IErReveInfoService : IServiceBus  
	{
        [MethodAttribute]
        string unSdDzResult(List<ErReveInfo> list);
        [MethodAttribute]
        string sdDzResult(List<ErReveInfo> list);
        [MethodAttribute]
        string editDzResult(List<ErReveInfo> list, string dzResult,string xgsm);
	}
}