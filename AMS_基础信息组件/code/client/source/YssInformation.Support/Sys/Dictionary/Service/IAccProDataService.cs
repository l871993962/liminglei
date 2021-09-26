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
    /// 核算项目数据源服务接口
    /// 
    /// Created By XiaoZhilong 2013-4-26
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.sys.dai.controller.IAccProController", ServiceUrl = "")]
    public interface IAccProDataService : IControlDataService, IKeyConvertDataService
    {
        /// <summary>
        /// 根据科目类别获取核算项目
        /// </summary>
        /// <param name="kmCls">科目类别</param>
        /// <returns>核算元素列表</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAccProDataByKmCls")]
        List<BasePojo> getAccProDataByKmCls(string[] kmCls);
    }
}
