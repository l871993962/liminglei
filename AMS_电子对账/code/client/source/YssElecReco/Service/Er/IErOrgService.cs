using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using YssElecReco.Pojo.Er;
using FAST.Common.Service.Services.Base;

namespace YssElecReco.Service.Er
{
    /// <summary>
    /// 电子对账机构管理
    /// </summary>
    public interface IErOrgService : IServiceBus
    {
        /// <summary>
        /// 获取托管机构
        /// </summary>
        /// <returns>托管机构</returns>
        List<ErOrg> getTrusteeOrgs();
        
        /// <summary>
        /// 获取管理机构
        /// </summary>
        /// <returns>管理机构</returns>
        List<ErOrg> getManagerOrgs();
    }
}
