﻿////using YssBaseCls.Fun;
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



////namespace YssData.Service.Mp
namespace YssSecInformation.Support.Mp.SecMktMap.Service
{
    /// <summary>
    /// 场外行情数据服务接口
    /// author ll 20120925
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.sec.information.support.modules.mp.outmkt.controller.IOutMktController", ServiceUrl = "")]
    public interface IOutMktService : IServiceBus
    {
        /// <summary>
        /// 条件查询:场外行情
        /// </summary>
        /// <param name="quyCon">quyCon</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondition")]
        QueryRes selectByCondition(string quyCon);

       /// <summary>
       /// 获取收益类型
       /// </summary>
        /// <param name="secCode">secCode</param>
       /// <returns>sylx</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getSYLX")]
        string getSYLX(string secCode);

        /// <summary>
        /// 查询数据库中是否有重复数据
        /// </summary>
        /// <param name="secCode">secCode</param>
        /// <param name="dMkt">dMkt</param>
        /// <param name="mktCls">mktCls</param>
        /// <param name="plat">plat</param>
        /// <returns>secCode</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/checkDuplicate")]
        string checkDuplicate(string secCode, string dMkt, string mktCls, string plat);

    }
}


