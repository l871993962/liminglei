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
using YssSecInformation.Support.Pojo.Sec;


namespace YssSecInformation.Support.Sv.Service
{
    /// <summary>
    /// 理财基本信息数据服务对象
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.sec.information.support.modules.sv.base.controller.ISecBaseLcController", ServiceUrl = "")]
    public interface ISecBaseLcService : IServiceBus
    {
        /// <summary>
        /// 条件查询
        /// </summary>
        /// <param name="quyCon">quyCon</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondition")]
        QueryRes selectByCondition(string quyCon);

        /// <summary>
        /// 初始化单只证券的历史付息及每百元利息
        /// </summary>
        /// <param name="seccode">seccode</param>
        /// <returns>void</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/singleSecInitFi")]
        string singleSecInitFi(string seccode);

        /// <summary>
        /// 初始化多只证券的历史付息及每百元利息
        /// </summary>
        /// <param name="secList">secList</param>
        /// <returns>void</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/multipleSecInitFi")]
        string multipleSecInitFi(List<SecBase> secList);

        /// <summary>
        /// 查询理财产品信息
        /// </summary>
        /// <param name="paraMap">查询参数</param>
        /// <returns>结果集</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getSecBasesByCondition")]
        List<BasePojo> getSecBasesByCondition(Dictionary<string, string> paraMap);
    }
}




