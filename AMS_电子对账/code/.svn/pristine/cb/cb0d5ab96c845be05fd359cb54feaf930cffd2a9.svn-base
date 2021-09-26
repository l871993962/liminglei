using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Drawing;
using System.Linq;
using System.Reflection;
using System.Windows.Forms;
using YssElecReco.pojo.Er;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;
using YssElecReco.Context;

namespace YssElecReco.Service.Er
{
    /// <summary>
    /// 浏览电子对账信息service
    /// </summary>
    [ServiceAttribute(ServiceId = ElecrecoServiceIdConstant.OSGI_ELECRECO, ServiceController = "com.yss.uco.elecreco.er.ersyzqyb.controller.IErSyzqybServiceController")]
    public interface IErSyzqybService : IServiceBus
    {
        /// <summary>
        /// 根据添加查询电子对账利润信息
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>返回利润列表</returns>
        [MethodAttribute]
        List<ErSyzqyb> getSyzqyData(Dictionary<string, string> paraDict);
    }
}
