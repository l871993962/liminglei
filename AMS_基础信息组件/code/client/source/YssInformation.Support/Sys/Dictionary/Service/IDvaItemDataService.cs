
using System.Collections.Generic;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.DataService;
////using YssBaseCls.Pojo;
using FAST.Common.Service.Services.Base;
using YssInformation.Support.Sys.Dictionary.Pojo;
using FAST.Common.Service.Attributes;

////namespace YssBaseCls.Service
////namespace YssInformation.Sys.Dictionary.Service
namespace YssInformation.Support.Sys.Dictionary.Service
{
    /// <summary>
    /// 核算业务项数据服务接口
    /// 
    /// Created By XiaoZhilong 2013-4-26
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.sys.dvaitem.controller.IDvaItemDataController", ServiceUrl = "")]
    public interface IDvaItemDataService : IControlDataService, IKeyConvertDataService
    {
        /// <summary>
        /// 资产估值界面获取核算项目列表
        /// 
        /// </summary>
        /// <returns>核算项目列表</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getTreeViewForDayfItems")]
        List<DvaItem> getTreeViewForDayfItems();
    }
}
