using System.Collections.Generic;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.DataService;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;

namespace YssInformation.Support.Sys.DcCury.Service
{
    /// <summary>
    /// 币种数据源服务接口
    /// 
    /// Created By XiaoZhilong 2013-4-26
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.sys.dccury.controller.IDCDataController", ServiceUrl = "")]
    public interface IDCDataService : IControlDataService, IKeyConvertDataService
    {
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPortCurruncyList")]
        List<BasePojo> getPortCurruncyList();
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPortCurruncyListRes")]
        QueryRes getPortCurruncyListRes();

        /// <summary>
        /// 获取国际货币列表
        /// </summary>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getCURRList")]
        List<BasePojo> getCURRList();


        /// <summary>
        /// 获取节日群组列表
        /// </summary>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getHoliDayGroupsList")]
        List<BasePojo> getHoliDayGroupsList();


        /// <summary>
        /// 获取产品群组列表
        /// </summary>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPrdGroupList")]
        List<BasePojo> getPrdGroupList();

        /// <summary>
        /// 获取近期年份（暂取今明年）
        /// </summary>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getNearYearsList")]
        List<BasePojo> getNearYearsList(string number);
    }

   

}
