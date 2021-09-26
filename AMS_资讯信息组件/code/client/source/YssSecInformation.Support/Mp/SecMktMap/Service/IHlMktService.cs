////using YssBaseCls.Fun;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Attributes;


////namespace YssData.Service.Mp
namespace YssSecInformation.Support.Mp.SecMktMap.Service
{
    /// <summary>
    /// 汇率行情数据服务接口
    /// author ll 20120925
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.sec.information.support.modules.mp.hlmkt.controller.IHlMktController", ServiceUrl = "")]
    public interface IHlMktService : IServiceBus
    {
        /// <summary>
        /// 条件查询
        /// </summary>
        /// <param name="quyCon">quyCon</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondition")]
        QueryRes selectByCondition(string quyCon);

        /// <summary>
        /// 根据组合、核算日期、交易币种加载货币和汇率信息 
        /// STORY #3705核算综合管理新增界面自动加载汇率 Added By xzl
        /// </summary>
        /// <param name="port">组合代码</param>
        /// <param name="accDate">核算日期</param>
        /// <param name="tradeCury">交易货币</param>
        /// <returns>汇率</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/queryErByCondition")]
        string queryErByCondition(string port, string accDate, string tradeCury);

    }
}


