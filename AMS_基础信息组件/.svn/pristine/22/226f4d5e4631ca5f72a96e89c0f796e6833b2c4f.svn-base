using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Dict.Pojo;
using FAST.Common.Service.Attributes;

namespace YssInformation.Support.Sys.PortBusinessRange.Service
{
    /// <summary>
    /// 业务类型关联组合数据接口
    /// </summary>
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.sys.portbusinessrange.controller.IPortBusinessRangeController", ServiceUrl = "")]
    public interface IPortBusinessRangeService : IServiceBus
    {   
        /// <summary>
        /// STORY #82160 【华宝基金】产品业务范围增加维护界面
        /// 获取业务类型数据
        /// </summary>
        /// <param name="type">type</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByType")]
        List<Vocabulary> getDataListByType(string type);

        /// <summary>
        /// STORY #82160 【华宝基金】产品业务范围增加维护界面
        /// 更新业务类型数据
        /// </summary>
        /// <param name="type">type</param>
        /// <param name="paraDict">paraDict</param>
        /// <returns>执行状态</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/updateDataList")]
        bool updateDataList([RequestBodyAttribute(ParameterName = "type")] string type, [RequestBodyAttribute(ParameterName = "paraMap")] Dictionary<string, string> paraDict);
    }
}
