using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using YssSztTool.Pojo.Para;
using FAST.Common.Service.Attributes;

namespace YssSztTool.Service.Para
{
    [ServiceAttribute(ServiceId = "osgi-elecreco", ServiceController = "com.yss.uco.elecreco.er.org.controller.IErOrgServiceController")]
    public interface IErOrgService : IServiceBus
    {
        [MethodAttribute]
        List<ErOrg> getTrusteeOrgs();

        [MethodAttribute] 
        List<ErOrg> getManagerOrgs();
    }
}
