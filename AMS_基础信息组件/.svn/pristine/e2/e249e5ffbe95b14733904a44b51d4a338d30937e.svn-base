using System.Collections.Generic;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Pojo.Base;

////namespace YssBaseCls.Service
////namespace YssInformation.Sys.Dictionary.Service
namespace YssInformation.Support.Sys.Dictionary.Service
{
    /// <summary> 
    /// 核算元素服务接口
    /// add by Yuntao Lau 2015.12.01
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    public interface IDaeElemQueryDataService : IServiceBus
    {
        /// <summary>
        /// 条件查询 过滤显示核算元素
        /// </summary>
        /// <param name="paraMap">paraMap</param>
        /// <returns>查询结果对象</returns>
        List<BasePojo> getDetailDataByCondition(Dictionary<string, string> paraMap);
    }
}
