﻿using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
////using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;

using FAST.Common.Service.Attributes;
using FAST.Core.Context;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;



////namespace YssPara.Service.Bi
////namespace YssInformation.Sys.Dictionary.Service
namespace YssInformation.Support.Sys.Dictionary.Service
{
    /// <summary>
    /// 证券品种信息数据服务接口
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.sys.secvar.controller.ISecVarController", ServiceUrl = "")]
    public interface ISecVarService : IServiceBus
    {
        /// <summary>
        /// 条件查询
        /// </summary>
        /// <param name="quyCon">quyCon</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondition")]
        QueryRes selectByCondition(string quyCon);

        /// <summary>
        /// 条件查询,带证券属性表字段
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <param name="page">page</param>
        /// <param name="menuId">menuId</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectWithSecAttr")]
        QueryRes selectWithSecAttr([RequestBodyAttribute(ParameterName = "paraMap")] Dictionary<string, string> paraDict, [RequestBodyAttribute(ParameterName = "page")] PageInation page, [RequestBodyAttribute(ParameterName = "menuId")] string queryMenuId);

         /// <summary>
        /// 查询监控资产
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/queryIdxCtrlSec")]
        QueryRes queryIdxCtrlSec(Dictionary<string, string> paraDict);        

        /// <summary>
        /// 根据交易证券获取对应的品种信息
        /// </summary>
        /// <param name="seccode">seccode</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/queryVarcodeByCode")]
        QueryRes queryVarcodeByCode(string seccode);
    }
}




