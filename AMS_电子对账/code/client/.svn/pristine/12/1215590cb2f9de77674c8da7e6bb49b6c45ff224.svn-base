using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using YssElecReco.Pojo.Er;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Attributes;
using YssElecReco.Context;

namespace YssElecReco.Service.Er
{
    /// <summary>
    /// 11
    /// </summary>
    [ServiceAttribute(ServiceId = ElecrecoServiceIdConstant.OSGI_ELECRECO, ServiceController = "com.yss.uco.elecreco.support.controller.IErStepStateServiceController")]
    public interface IErStepStateService : IServiceBus
    {
        /// <summary>
        /// 获取数据
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>List</returns>
        [MethodAttribute]
        List<BasePojo> queryListByTypes(Dictionary<string, string> paraDict);
    }
}
