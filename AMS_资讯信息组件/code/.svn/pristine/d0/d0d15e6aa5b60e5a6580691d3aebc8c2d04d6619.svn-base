﻿using FAST.Core.Util;
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


namespace YssSecInformation.Support.PlateSet.plateSub.Service
{
    /// <summary>
    /// 版块信息B区
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.sec.information.support.modules.plateset.platesub.controller.IPlateSubController", ServiceUrl = "")]
    public interface IPlateSubService : IServiceBus
    {
        /// <summary>
        /// 条件查询
        /// </summary>
        /// <param name="quyCon">quyCon</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondition")]
        QueryRes selectByCondition(string quyCon);

        /////// <summary>
        /////// 条件查询,带基本表之外的字段结果集
        /////// </summary>
        /////// <param name="paraDict">paraDict</param>
        /////// <param name="page">page</param>
        /////// <param name="menuId">menuId</param>
        /////// <returns>查询结果对象</returns>
        ////QueryRes selectByConditionExtend(Dictionary<string, string> paraDict, PageInation page, string menuId);
    }
}




