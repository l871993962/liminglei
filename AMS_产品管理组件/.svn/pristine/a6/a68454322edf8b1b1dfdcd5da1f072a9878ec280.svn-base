using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;



namespace YssProductInfo.Support.Ab.Port.Service
{
    /// <summary>
    /// 用于实现列表界面Ａ区组合的加载，用于Port_A POJO类
    /// 可实现组合的快速加载
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.product.information.support.modules.ab.port.controller.IPortADataController", ServiceUrl = "")]
    public interface IPortADataService : IDataService
    {
        /// <summary>
        /// 查询Ａ区数据
        /// </summary>
        /// <param name="isDataRight">isDataRight</param>
        /// <param name="datClass">datClass</param>
        /// <param name="dvPortCode">dvPortCode</param>
        /// <param name="trCode">trCode</param>
        /// <returns>res</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/doPortFilterRes")]
        QueryRes doPortFilterRes(string isDataRight, string datClass, string dvPortCode, string trCode);

        /// <summary>
        /// 收藏为常用组合
        /// </summary>
        /// <param name="portA"></param>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/saveToOftenUsePort")]
        string saveToOftenUsePort(List<BasePojo> basePojoList);

        /// <summary>
        /// 从常用组合中删除
        /// </summary>
        /// <param name="portA"></param>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/deleteOftenUsePort")]
        string deleteOftenUsePort(List<BasePojo> basePojoList);

        /// <summary>
        /// 获取常用产品列表
        /// </summary>
        /// <returns>res</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getOftenUsePortList")]
        List<string> getOftenUsePortList();

        /// <summary>
        /// doPortFilterRes
        /// </summary>
        /// <param name="isDataRight">isDataRight</param>
        /// <param name="datClass">datClass</param>
        /// <param name="dvPortCode">dvPortCode</param>
        /// <param name="trCode">trCode</param>
        /// <param name="menuId">menuId</param>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/doPortFilterRes1")]
        QueryRes doPortFilterRes(string isDataRight, string datClass, string dvPortCode, string trCode, string menuId);
    }
}
