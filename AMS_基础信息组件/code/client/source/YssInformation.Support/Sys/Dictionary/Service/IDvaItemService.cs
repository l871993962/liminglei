using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Attributes;

////namespace FAST.Platform.Dict.Service
////namespace YssInformation.Sys.Dictionary.Service
namespace YssInformation.Support.Sys.Dictionary.Service
{
    /// <summary>
    /// 核算业务字典接口
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.sys.dvaitem.controller.IDvaItemController", ServiceUrl = "")]
    public interface IDvaItemService : IServiceBus
    {

        /// <summary>
        /// 获取业务类型树型数据结构
        /// </summary>
        /// <param name="paraDict">查询条件参数对象</param>
        /// <returns>查询结果集</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDvaItemsTreeData")]
        QueryRes getDvaItemsTreeData(Dictionary<string, string> paraDict);
    }
}
