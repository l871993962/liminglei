using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using YssElecReco.Pojo.Er;
using YssElecReco.Context;
using FAST.Common.Service.Attributes;

namespace YssElecReco.Service.Er
{
    /// <summary>
    /// 报表个性列
    /// </summary>
    [ServiceAttribute(ServiceId = ElecrecoServiceIdConstant.OSGI_ELECRECO, ServiceController = "com.yss.uco.elecreco.er.repcolcfg.controller.IDzRepColCfgServiceController")]
    public interface IDzRepColCfgService : IServiceBus  
    {
        /// <summary>
        /// 获取设置的个性列
        /// </summary>
        /// <param name="cDzType">cDzType</param>
        /// <param name="reportCode">reportCode</param>
        /// <returns>List</returns>
        [MethodAttribute]
        List<DzRepColCfg> getDzRepColCfgs(string cDzType, string reportCode);

        /// <summary>
        /// 是否已经配置
        /// </summary>
        /// <param name="reportCode">reportCode</param>
        /// <returns>bool</returns>
        [MethodAttribute]
        bool isHaveCfg(string reportCode);
    }
}