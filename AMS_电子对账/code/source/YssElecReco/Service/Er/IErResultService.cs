using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;
using YssElecReco.Context;

namespace YssElecReco.Service.Er
{
    /// <summary>
    /// 产生电子对账核对数据service
    /// chenyoulong 20121213
    /// v1.0.0.6
    /// </summary>
    [ServiceAttribute(ServiceId = ElecrecoServiceIdConstant.OSGI_ELECRECO, ServiceController = "com.yss.uco.elecreco.er.erresult.controller.IErResultServiceController")]
    public interface IErResultService : IServiceBus
    {
        /// <summary>
        /// 获取未核对前的原始数据
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>QueryRes</returns>
        [MethodAttribute]
        QueryRes queryOrigDataByCondition(Dictionary<string, string> paraDict);

        Dictionary<string, string> getPortRelaOrgData(String portcodes);
    }
}
