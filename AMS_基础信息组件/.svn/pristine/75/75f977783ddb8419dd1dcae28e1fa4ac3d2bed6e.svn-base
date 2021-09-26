using System.Collections.Generic;
using FAST.Common.Service.Attributes;
using FAST.Common.Service.Dict.Pojo;
using FAST.Common.Service.Services.Base;

namespace YssInformation.Support.Sys.automaticSet.Service
{
    /// <summary>
    /// 自动化业务设置数据接口
    /// </summary>
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.sys.automaticSet.controller.IAutomaticSetController", ServiceUrl = "")]
    public interface IAutomaticSetService : IServiceBus
    {
        /// <summary>
        /// STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
        /// 获取业务类型数据
        /// </summary>
        /// <param name="type">type</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByType")]
        List<Vocabulary> getDataListByType(string type);

        /// <summary>
        /// STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
        /// 更新业务类型数据
        /// </summary>
        /// <param name="type">type</param>
        /// <param name="paraDict">paraDict</param>
        /// <returns>执行状态</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/updateDataList")]
        bool updateDataList([RequestBodyAttribute(ParameterName = "type")] string type, [RequestBodyAttribute(ParameterName = "paraMap")] Dictionary<string, string> paraDict);
    }
}
