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
using YssSecInformation.Support.Pojo.Sec;
using FAST.Common.Service.Attributes;


namespace YssSecInformation.Support.Sv.Service
{
    /// <summary>
    /// 债券基本信息数据服务对象
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.para.sv.secbase.controller.ISecBaseZqController", ServiceUrl = "")]
    public interface ISecBaseZqService : IServiceBus
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
        /// * Title: STORY #27843 资讯信息调整增加提示功能
        /// * Author: chenchen
        /// * Status: Add
        /// * Date: 2016.8.25
        /// * Description:根据证券内码检查是否有持仓信息。
        /// </summary>
        /// <param name="seccode">证券内码</param>
        /// <returns>检查是否有持仓</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/secTypeTip")]
        string secTypeTip(string seccode);

        /// <summary>
        /// STORY32744【南方基金】【v2.5需求】南方基金：社保资产证券代码新规则 add by liuyanni 20161029
        /// 根据转换规则生成债券的披露代码
        /// </summary>
        /// <param name="secList">secList</param>
        /// <param name="zhgz">转换规则</param>
        /// <returns>string</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/transSecToPlCode")]
        string transSecToPlCode([RequestBodyAttribute(ParameterName = "secList")] List<SecBase> secList, [RequestBodyAttribute(ParameterName = "zhgz")] string zhgz);

        /// <summary>
        /// STORY38131【南方基金】【紧急】社保理事会要求交易席位科目是6位，不足6位前面补0  by guohui 20170207
        /// 判断转换规则是否开启
        /// </summary>
        /// <param name="zhgz">转换规则</param>
        /// <param name="type">应用条件</param>
        /// <returns>string</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/ruleIsOpen")]
        string ruleIsOpen(string zhgz, string type);
    }
}




