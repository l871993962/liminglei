using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Pojo;

namespace YssProductInfo.Support.PortPlan.Service
{
    /// <summary>
    /// 组合方案接口
    /// add by zhd 2016-09-26
    /// STORY33239【南方基金】复制建仓参照组合能默认勾选继承内容即增加保存按钮
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    public interface IPortPlanService : IServiceBus
    {
        /// <summary>
        /// 根据自定义条件查询数据
        /// </summary>
        /// <param name="paraMap">查询条件参数集合</param>
        /// <returns>查询结果对象</returns>
        QueryRes queryPlanPojo(Dictionary<string, string> paraMap);

        /// <summary>
        /// 根据方案代码来删除方案
        /// </summary>
        /// <param name="planCode">planCode</param>
        /// <returns>string</returns>
        string deleteByPlanCode(string planCode);

        /// <summary>
        /// 根据方案代码来删除方案
        /// </summary>
        /// <param name="planType">planType</param>
        /// <param name="planCode">planCode</param>
        /// <returns>string</returns>
        string deleteByPlanCode(string planType, string planCode);
    }
}
