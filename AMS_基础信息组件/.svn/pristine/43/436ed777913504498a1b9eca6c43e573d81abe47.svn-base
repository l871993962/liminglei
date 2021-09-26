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



namespace YssInformation.Support.Bi.Holidays_A.Service
{
    /// <summary>
    /// 节假日群设置A区
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.bi.hdaygroup.controller.IHdayGroupController", ServiceUrl = "")]
    public interface IHdayGroupService : IServiceBus
    {
        /// <summary>
        /// 条件查询
        /// </summary>
        /// <param name="quyCon">quyCon</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondition")]
        QueryRes selectByCondition(string quyCon);

        /// <summary>
        /// 获取树型数据结构
        /// </summary>
        /// <param name="paraDict">查询条件参数对象</param>
        /// <returns>查询结果集</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getTreeViewData")]
        QueryRes getTreeViewData(Dictionary<string, string> paraDict);
    }
}




