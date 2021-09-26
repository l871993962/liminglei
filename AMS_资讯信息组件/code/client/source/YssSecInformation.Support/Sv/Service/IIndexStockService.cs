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



namespace YssSecInformation.Support.Sv.Service
{
    /// <summary>
    /// ///  chenbo
    /// 2017-06-22
    /// #42948 资讯信息管理组件化拆分
    /// ----------------------------------
    /// 指数成分券信息服务类
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.sec.information.support.modules.sv.indexstock.controller.IIndexStockController", ServiceUrl = "")]
    public interface IIndexStockService : IServiceBus
    {
        /// <summary>
        /// 获得未选择的股票信息
        /// </summary>
        /// <param name="c_Index_Code">指数代码</param>
        /// <param name="d_Begin">启用日期</param>
        /// <returns>未选择股票信息列表</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getUnSelectedSecBase")]
        QueryRes getUnSelectedSecBase(string c_Index_Code, string d_Begin);

        /// <summary>
        /// 获得已选择的股票信息
        /// </summary>
        /// <param name="c_Index_Code">指数代码</param>
        /// <param name="d_Begin">启用日期</param>
        /// <returns>已选择股票信息列表</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getSelectedSecBase")]
        QueryRes getSelectedSecBase(string c_Index_Code, string d_Begin);

        /// <summary>
        /// 获得最近未选择的股票信息
        /// </summary>
        /// <param name="c_Index_Code">指数代码</param>
        /// <param name="d_Begin">启用日期</param>
        /// <returns>未选择股票信息列表</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getLastUnSelectedSecBase")]
        QueryRes getLastUnSelectedSecBase(string c_Index_Code, string d_Begin);

        /// <summary>
        /// 获得最近已选择的股票信息
        /// </summary>
        /// <param name="c_Index_Code">指数代码</param>
        /// <param name="d_Begin">启用日期</param>
        /// <returns>已选择股票信息列表</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getLastSelectedSecBase")]
        QueryRes getLastSelectedSecBase(string c_Index_Code, string d_Begin);
    }
}




