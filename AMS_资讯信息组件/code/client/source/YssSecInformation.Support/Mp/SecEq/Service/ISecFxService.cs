////using YssBaseCls.Fun;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;

using FAST.Common.Service.Attributes;
using FAST.Core.Context;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;



namespace YssSecInformation.Support.Mp.SecEq.Service
{
    /// <summary>
    /// chenbo
    /// 2017-06-22
    /// #42948 资讯信息管理组件化拆分
    /// ----------------------------------
    /// 证券发行 数据服务接口
    /// author ll 20120925
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.sec.information.support.modules.mp.secequ.controller.ISecFxController", ServiceUrl = "")]
    public interface ISecFxService : IServiceBus
    {
        /// <summary>
        /// 条件查询:证券发行
        /// </summary>
        /// <param name="quyCon">quyCon</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondition")]
        QueryRes selectByCondition(string quyCon);

    }
}


