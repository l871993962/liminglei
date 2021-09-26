﻿using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;

using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;
using FAST.Core.Bussiness.Pojo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using YssInformation.Support.Pojo;
using FAST.Common.Service.Attributes;
////using YssBaseCls.Pojo;

namespace YssInformation.Support.Bi.Market.Service
{
    /// <summary>
    /// 交易市场设置数据服务接口
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.bi.mkt.controller.IMktController", ServiceUrl = "")]
    public interface IMktService : IServiceBus
    {
        /// <summary>
        /// 条件查询
        /// </summary>
        /// <param name="quyCon">quyCon</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondition")]
        QueryRes selectByCondition(string quyCon);

        /// <summary>
        /// 条件查询,带基本表之外的字段结果集
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <param name="page">page</param>
        /// <param name="menuId">menuId</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByConditionExtend")]
        QueryRes selectByConditionExtend([RequestBodyAttribute(ParameterName = "paraMap")] Dictionary<string, string> paraDict, [RequestBodyAttribute(ParameterName = "page")] PageInation page, [RequestBodyAttribute(ParameterName = "c_USER_CODE")] string queryMenuID);

        /// <summary>
        /// 市场信息
        /// </summary>
        /// <returns>市场信息</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAllMkt")]
        List<MarketVoc> getAllMkt();

        /// <summary>
        /// 判断所属市场状态
        /// </summary>
        ///<param name="mktCode">所属市场</param>
        /// <returns>市场信息</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getCheckStatus")]
        string getCheckStatus(string mktCode);

        /// <summary>
        /// 判断交易市场代码是否与清算机构重复
        /// add by guohui 20161219 STORY36399【招商基金】【紧急】股票、债券、基金等交易流水界面增加“交易市场”字段供TB导出接口取对应的清算信息
        /// </summary>
        ///<param name="mktCode">所属市场</param>
        /// <returns>市场信息</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/compareQsjg")]
        string compareQsjg(string mktCode);

    }
}




