using System;
using System.Collections.Generic;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;



namespace YssProductInfo.Support.Ab.Port.Service
{
    /// <summary>
    /// 组合的默认设置信息数据服务接口
    /// 
    /// Created By XiaoZhilong 2013-4-26
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.product.information.support.modules.aa.portcustom.controller.IPortCustomDataController", ServiceUrl = "")]
    public interface IPortCustomDataService : IDataService
    {
        /// <summary>
        /// 通过没有菜单数组获取对应的默认组合信息
        /// </summary>
        /// <param name="keyArray">键值</param>
        /// <returns>返回对应的组合信息</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPortCodeByArray")]
        string getPortCodeByArray(string[] keyArray);

        /// <summary>
        /// 通过菜单获取对应的默认组合信息
        /// </summary>
        /// <param name="code">键值</param>
        /// <returns>返回对应的组合信息</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPortCodeByMenuid")]
        string getPortCodeByMenuid(string code);

        /// <summary>
        /// 通过没有菜单获取对应的默认组合信息
        /// </summary>
        /// <param name="code">键值</param>
        /// <returns>返回对应的组合信息</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPortCode")]
        string getPortCode(string code);

        /// <summary>
        /// getShowType
        /// </summary>
        /// <param name="codeMap">codeMap</param>
        /// <returns>s</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getShowType")]
        string getShowType(Dictionary<string, string> codeMap);
    }
}
