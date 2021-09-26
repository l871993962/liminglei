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
using YssInformation.Support.Bi.AssociationOrgan.Pojo;
using FAST.Common.Service.Attributes;



namespace YssInformation.Support.Bi.AssociationOrgan.Service
{
    /// <summary>
    /// 结构基本信息设置数据服务接口
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.bi.org.controller.IOrgController", ServiceUrl = "")]
    public interface IOrgService : IServiceBus
    {
        /////// <summary>
        /////// 条件查询,带基本表之外的字段结果集
        /////// </summary>
        /////// <param name="paraDict">paraDict</param>
        /////// <param name="page">page</param>
        /////// <param name="menuId">menuId</param>
        /////// <returns>查询结果对象</returns>
        ////QueryRes selectByConditionExtend(Dictionary<string, string> paraDict, PageInation page, string menuId);

        /// <summary>
        /// 组合关联机构SET界面查询
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>QueryRes</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPortRelaOrg")]
        QueryRes getPortRelaOrg(Dictionary<string, string> paraDict);

        /// <summary>
        /// 组织字典信息
        /// </summary>
        /// <returns>组织字典信息</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getOrgVoc")]
        List<BasePojo> getOrgVoc();

        /// <summary>
        /// 嵌套窗体查询关联机构信息
        /// </summary>
        /// <param name="paraDict">查询条件</param>
        /// <param name="page">分页信息</param>
        /// <returns>QueryRes</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/queryPortRelaOrg")]
        QueryRes queryPortRelaOrg([RequestBodyAttribute(ParameterName = "paraMap")] Dictionary<string, string> paraDict, [RequestBodyAttribute(ParameterName = "page")] PageInation page);

        /// <summary>
        /// 图片路径
        /// </summary>
        /// <param name="imagePath">图片路径</param>
        /// <returns>1</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/queryImage")]
        string queryImage(string imagePath);

        /// <summary>
        /// Author : ChenLong
        /// Date   : 2016-09-22
        /// Status : Add
        /// Comment: 获取委托人组织机构
        /// </summary>
        /// <returns>机构集合</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getOrgByConsignerType")]
        List<Org> getOrgByConsignerType();

        /// <summary>
        /// 根据id更新数据
        /// </summary>
        /// <param name="ids">id集合</param>
        /// <returns>缓存对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/updateByIds")]
        CacheData updateByIds(string ids);

        /// <summary>
        /// 获取机构关联联系人信息
        /// STORY #81326 【华宝兴业】关联结构设置 中联系信息维护调整
        /// </summary>
        /// <param name="orgCode"></param>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getOrgLinkManList")]
        List<Org> getOrgLinkManList(string orgCode);
    }
}




