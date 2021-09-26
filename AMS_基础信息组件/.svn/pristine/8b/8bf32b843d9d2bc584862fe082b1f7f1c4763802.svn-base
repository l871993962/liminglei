using System.Collections.Generic;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.DataService;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;

////namespace YssBaseCls.Service
namespace YssInformation.Support.Sys.Dictionary.Service
{
    /// <summary>
    /// 核算元素服务接口
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.sys.daeelem.controller.IDaeElemController", ServiceUrl = "")]
    public interface IDaeElemService : IServiceBus
    {
        /// <summary>
        /// 条件查询 过滤显示核算元素
        /// </summary>
        /// <param name="paraMap">paraMap</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDaeCodesByCondition")]
        List<string> getDaeCodesByCondition(Dictionary<string, string> paraMap);
        
        /// <summary>
        /// 根据证券品种code判断是否含有子类没有 从而得出数据是否是明细 By Jinghehe 2013-12-20
        /// </summary>
        /// <param name="code">code</param>
        /// <returns>string</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/isDetailByCode")]
        string isDetailByCode(string code);
    }

}