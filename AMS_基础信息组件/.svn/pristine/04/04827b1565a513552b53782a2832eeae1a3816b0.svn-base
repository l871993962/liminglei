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



namespace YssInformation.Support.Bi.organmgr.Service
{
    /// <summary>
    /// 结构结算会员数据服务接口
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.bi.orgmgr.controller.IOrgMgrController", ServiceUrl = "")]
    public interface IOrgMgrService : IServiceBus
    {
        /// <summary>
        /// 条件查询
        /// </summary>
        /// <param name="quyCon">quyCon</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondition")]
        QueryRes selectByCondition(string quyCon);

        /// <summary>
        /// Author : ChenLong
        /// Date   : 2016-05-10
        /// Status : Add
        /// Comment: 获取所有结算会员代码
        /// </summary>
        /// <returns>结算会员代码集合</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAllMBRCodes")]
        List<string> getAllMBRCodes();

        /// <summary>
        /// 查询组合关联结算会员  liuxiang 2016-5-20 STORY #28860 上交所跨市场ETF基金产品整体需求
        /// </summary>
        /// <param name="paraDict">查询条件</param>
        /// <returns>查询结果</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPortRelaMember")]
        QueryRes getPortRelaMember(Dictionary<string, string> paraDict);
    }
}




