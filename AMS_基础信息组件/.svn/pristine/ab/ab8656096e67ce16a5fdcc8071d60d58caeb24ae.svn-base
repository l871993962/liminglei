using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using YssInformation.Support.Sys.Dictionary.Pojo;
using FAST.Common.Service.Attributes;
////using FAST.Common.Service.Pojo;

////namespace FAST.Common.Service.Services
////namespace YssInformation.Sys.Dictionary.Service
namespace YssInformation.Support.Sys.Dictionary.Service
{
    /// <summary>
    /// 核算元素服务接口
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.sys.dai.controller.IDaiDictController", ServiceUrl = "")]
    public interface IDaiDictService : IServiceBus
    {
        /// <summary>
        /// 用于科目体系加载，从系统缓存中取数
        /// STORY #26934 byleeyu20151105
        /// </summary>
        /// <returns>核算项目列表</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/queryByCacheForKm")]
        List<DAI> queryByCacheForKm();
    }

}