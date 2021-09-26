
using System.Collections.Generic;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.DataService;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;

////namespace YssBaseCls.Service
////namespace YssInformation.Sys.Dictionary.Service
namespace YssInformation.Support.Sys.Dictionary.Service
{
    /// <summary>
    /// 收支项目字典数据服务接口
    /// 
    /// Created By XiaoZhilong 2013-4-26
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.sys.ieItem.controller.IIeItemDataController", ServiceUrl = "")]
    public interface IIeItemDataService : IControlDataService, IKeyConvertDataService
    {
        /// <summary>
        /// 根据收支项目类型获取收支项目
        /// liuxiang 2015-8-14 STORY #24240 太平资产收支结转功能优化
        /// </summary>
        /// <param name="types">收支项目类型</param>
        /// <returns>收支项目列表</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByIeTypes")]
        List<BasePojo> getDataListByIeTypes(string[] types);
    }
}
