using System.Collections.Generic;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.DataService;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;


////namespace YssBaseCls.Service
////namespace YssInformation.Sys.Dictionary.Service
namespace YssInformation.Support.Sys.Dictionary.Service
{
    /// <summary>
    /// 交易属性字典数据服务接口
    /// 
    /// Created By XiaoZhilong 2013-4-26
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.sys.dtatdattr.controller.IDtaTdAttrDataController", ServiceUrl = "")]
    public interface IDtaTdAttrDataService : IControlDataService, IKeyConvertDataService
    {
        /// <summary>
        /// 取得所有数据（只包含代码和名称）
        /// add by liuxiang 2014-6-10
        /// </summary>
        /// <returns>包含代码和名称的键值对</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getShortDataMap")]
        Dictionary<string, string> getShortDataMap();

        /// <summary>
        /// 通过指定codes获取数据   STORY39265商品期权业务 add by xuyuanhao 2017-3-30
        /// </summary>
        /// <param name="codes">codes</param>
        /// <returns>pojo集合</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByCodes")]
        List<BasePojo> getDataListByCodes(string[] codes);
    }
}
