using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Pojo.Base;
using YssInformation.Support.Bi.Account.Pojo;
using System.Collections;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Attributes;


namespace YssInformation.Support.Bi.AccountTree.Service
{
    /// <summary>
    /// 账户树形结构B区service
    /// </summary>
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.bi.accountTreeB.controller.IAccountTreeBController", ServiceUrl = "")]
    [NewServiceAttribute(ServiceType.NEW_SVC)] 
    public interface IAccountTreeBService : IServiceBus
    {
        /// <summary>
        /// getTreeViewData
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>ss</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getTreeViewData")]
        QueryRes getTreeViewData(Dictionary<string, string> paraDict);
    }
}
