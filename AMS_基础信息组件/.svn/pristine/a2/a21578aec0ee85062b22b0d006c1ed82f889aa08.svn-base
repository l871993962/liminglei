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


using YssInformation.Support.Bi.Region.Pojo;

namespace YssInformation.Support.Bi.Region.Service
{
    /// <summary>
    /// 地区信息设置数据服务接口
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.bi.region.controller.IAreaController", ServiceUrl = "")]
    public interface IAreaService : IServiceBus
    {
        /// <summary>
        /// 条件查询
        /// </summary>
        /// <param name="quyCon">quyCon</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondition")]
        QueryRes selectByCondition(string quyCon);

        /// <summary>
        /// 所有地区信息
        /// </summary>
        /// <returns>所有地区信息</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAllAreasByType")]
        QueryRes getAllAreasByType();

        /// <summary>
        /// 所有地区最上层所属地区
        /// </summary>
        /// <returns>所有地区最上层所属地区</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAllTopAreas")]
        List<Area> getAllTopAreas();
    }
}




