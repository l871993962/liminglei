using FAST.Common.Service.Pojo;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Pojo.Base;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Attributes;



namespace YssProductInfo.Support.Ab.AttributeCls.Service
{
    /// <summary>
    /// 组合产品属性接口
    /// Add By ：zhengguiyu
    /// Date ：20140322
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.product.information.support.modules.ab.portPdAttribute.controller.IPortPdAttributeController", ServiceUrl = "")]
    public interface IPortPdAttributeService : IServiceBus
    {
        /// <summary>
        /// 获取词汇字典
        /// </summary>
        /// <returns>dict</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getVocabularyDict")]
        Dictionary<string, string> getVocabularyDict();

        /// <summary>
        /// 获取资产类型字典
        /// </summary>
        /// <returns>dict</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAssetDict")]
        Dictionary<string, string> getAssetDict(); 

        /// <summary>
        /// 根据组合代码，获取资产类型
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>dict</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPortPojoList")]
        Dictionary<string, string> getPortPojoList(Dictionary<string, string> paraDict);

        /// <summary>
        /// 根据组合代码，获取组合名称
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>dict</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPortNameDict")]
        Dictionary<string, string> getPortNameDict(Dictionary<string, string> paraDict);
    }
}




