////using YssBaseCls.Fun;
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



namespace YssSecInformation.Support.Mp.SecEq.Service
{
    /// <summary>
    /// /// /// chenbo
    /// 2017-06-22
    /// #42948 资讯信息管理组件化拆分
    /// ----------------------------------
    /// 资产权益信息表T_D_MP_SEC_EQU:数据服务接口
    /// author ll 20120928
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.sec.information.support.modules.mp.secequ.controller.ISecEquController", ServiceUrl = "")]
    public interface ISecEquService : IServiceBus
    {
        /// <summary>
        /// 条件查询:证券送配,对价派息
        /// </summary>
        /// <param name="quyCon">quyCon</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondition")]
        QueryRes selectByCondition(string quyCon);

        /////// <summary>
        ///////证券发行的通用查询方法
        /////// </summary>
        /////// <param name="paraDict">paraDict</param>
        /////// <param name="page">page</param>
        /////// <param name="menuId">menuId</param>
        /////// <returns>查询结果对象</returns>
        ////QueryRes selSecPub(Dictionary<string, string> paraDict, PageInation page, string menuId);

        /////// <summary>
        /////// 证券流通通用查询的方法
        /////// </summary>
        /////// <param name="paraDict">paraDict</param>
        /////// <param name="page">page</param>
        /////// <param name="menuId">menuId</param>
        /////// <returns>查询结果对象</returns>
        ////QueryRes selSecLt(Dictionary<string, string> paraDict, PageInation page, string menuId);

    }
}


