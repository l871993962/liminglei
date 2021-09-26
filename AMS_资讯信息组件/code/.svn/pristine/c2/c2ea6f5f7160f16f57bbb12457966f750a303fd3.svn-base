﻿using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;
using FAST.Core.Context;
using FAST.Common.Service.Attributes;
using System.Collections.Generic;

namespace YssSecInformation.Support.Sv.Service
{
    /// <summary>
    /// 计费证券信息数据服务接口
    /// 
    /// Create By HeLiang 2016-09-05
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.sec.information.support.modules.sv.secbasejf.controller.ISecBaseJfController", ServiceUrl = "")]
    public interface ISecBaseJfService : IServiceBus
    {
        /// <summary>
        /// 条件查询
        /// </summary>
        /// <param name="quyCon">1</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondition")]
        QueryRes selectByCondition(string quyCon);

        /// <summary>
        /// 组合关联计费证券信息SET界面查询
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>QueryRes</returns>
        ////QueryRes getPortRelaChargingSec(Dictionary<string, string> paraDict);

        /// <summary>
        /// 嵌套窗体查询计费证券信息
        /// </summary>
        /// <param name="paraDict">查询条件</param>
        /// <param name="pageIns">分页信息</param>
        /// <returns>QueryRes</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/queryRelaChargingSec")]
        QueryRes queryRelaChargingSec(Dictionary<string, string> paraDict, PageInation pageIns);

        /// <summary>
        /// 嵌套窗体【选择】窗体查询计费证券信息
        /// </summary>
        /// <param name="paraDict">查询条件</param>
        /// <param name="pageIns">分页信息</param>
        /// <returns>QueryRes</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/queryPortRelaChargingSec")]
        QueryRes queryPortRelaChargingSec(Dictionary<string, string> paraDict, PageInation pageIns);
    }
}
