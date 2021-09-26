using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.DataService;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;

namespace YssInformation.Support.Bi.Account.Service
{
    /// <summary>
    /// 资金账户数据服务
    /// </summary>
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.bi.account.controller.IFundAccDataController", ServiceUrl = "")]
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    public interface IFundAccDataService : IControlDataService, IKeyConvertDataService
    {
        /// <summary>
        /// getDataListByOrg
        /// </summary>
        /// <param name="orgCode">orgCode</param>
        /// <returns>s</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByOrg")]
        List<BasePojo> getDataListByOrg(string orgCode);

        /// <summary>
        /// getDataListByPort
        /// </summary>
        /// <param name="portCode">portCode</param>
        /// <returns>s</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByPort")]
        List<BasePojo> getDataListByPort(string portCode);

        /// <summary>
        /// getDataListByAccTypes
        /// </summary>
        /// <param name="types">types</param>
        /// <returns>a</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByAccTypes")]
        List<BasePojo> getDataListByAccTypes(string[] types);

        /// <summary>
        /// getAllDataByPort
        /// </summary>
        /// <param name="portCode">portCode</param>
        /// <returns>s</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAllDataByPort")]
        List<BasePojo> getAllDataByPort(string portCode);

        /// <summary>
        /// getAccNameAndCaCode
        /// </summary>
        /// <returns>s</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAccNameAndCaCode")]
        List<BasePojo> getAccNameAndCaCode();
    }
}
