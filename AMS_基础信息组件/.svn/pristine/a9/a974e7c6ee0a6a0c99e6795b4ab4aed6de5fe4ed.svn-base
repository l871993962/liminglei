﻿using FAST.Core.Util;
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



namespace YssInformation.Support.Bi.TaselNet.Service
{
    /// <summary>
    /// 销售网点信息数据服务接口
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.bi.salesnet.controller.ISalesNetController", ServiceUrl = "")]
    public interface ISalesNetService : IServiceBus
    {
        /// <summary>
        /// 条件查询
        /// </summary>
        /// <param name="quyCon">quyCon</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondition")]
        QueryRes selectByCondition(string quyCon);

        /// <summary>
        /// 查询产品计划录入收益率时加载的销售网点，只包含分行和村镇银行
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>result</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/queryPdNet")]
        List<BasePojo> queryPdNet(Dictionary<string, string> paraDict);
    }
}




