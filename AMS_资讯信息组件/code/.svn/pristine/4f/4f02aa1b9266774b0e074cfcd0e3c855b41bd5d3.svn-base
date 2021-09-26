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


using System.Collections;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Sv.Pojo;

namespace YssSecInformation.Support.Sv.Service
{
    /// <summary>
    /// 债券历史付息数据服务接口
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.sec.information.support.modules.sv.fipay.controller.IFiPayController", ServiceUrl = "")]
    public interface IFiPayService : IServiceBus
    {
        /// <summary>
        /// 条件查询
        /// </summary>
        /// <param name="quyCon">quyCon</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondition")]
        QueryRes selectByCondition(string quyCon);

        /// <summary>
        /// 数据校验
        /// </summary>
        /// <param name="pojoList">pojoList</param>
        ///<returns>校验结果</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/checkDeleteData")]
        string checkDeleteData(ArrayList pojoList);

        /// <summary>
        /// 单只历史付息重新生成该期间的每日利息数据
        /// </summary>
        /// <param name="fipay">fipay</param>
        /// <returns>void</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/singleSecFiPayInit")]
        string singleSecFiPayInit(FiPay fipay);

        /// <summary>
        /// 多只历史付息重新生成对应期间的每日利息数据
        /// </summary>
        /// <param name="fipayList">fipayList</param>
        /// <returns>void</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/multipleFiPayInit")]
        string multipleFiPayInit(List<FiPay> fipayList);
    }
}




