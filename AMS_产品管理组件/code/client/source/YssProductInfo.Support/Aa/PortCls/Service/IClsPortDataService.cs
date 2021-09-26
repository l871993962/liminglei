
using System.Collections.Generic;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.DataService;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Attributes;
using FAST.Common.Service.Services.Base;



namespace YssProductInfo.Support.Aa.PortCls.Service
{
    /// <summary>
    /// 分级组合数据源服务接口
    /// 
    /// Created By XiaoZhilong 2013-4-26
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.product.information.support.modules.aa.portcls.controller.IClsPortDataController", ServiceUrl = "")]
    public interface IClsPortDataService : IControlDataService, IKeyConvertDataService
    {
        /// <summary>
        /// getPortClsCode 获取分级基金代码
        /// </summary>
        /// <param name="code">code</param>
        /// <returns>code</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPortClsCode")]
        string getPortClsCode(string code);

        /// <summary>
        /// 取得所有数据（只包含代码和名称）
        /// add by liuxiang 2014-6-10
        /// </summary>
        /// <param name="portCode">组合代码</param>
        /// <returns>包含代码和名称的键值对</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getShortDataMap")]
        Dictionary<string, string> getShortDataMap(string portCode);

        /// <summary>
        /// 取得所选组合对应的分级组合代码
        /// </summary>
        /// <param name="ports">ports</param>
        /// <returns>分级组合</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByPorts")]
        List<BasePojo> getDataListByPorts(string[] ports);

        /// <summary>
        /// getDataListByPorts
        /// </summary>
        /// <param name="types">types</param>
        /// <param name="clsPort">clsPort</param>
        /// <returns>ss</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByPorts")]
        List<BasePojo> getDataListByPorts([RequestBodyAttribute(ParameterName = "types")] string[] types, [RequestBodyAttribute(ParameterName = "clsPort")] string[] clsPort);

    }
}
