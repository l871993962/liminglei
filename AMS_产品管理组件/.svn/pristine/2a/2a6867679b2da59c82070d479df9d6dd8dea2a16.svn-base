using FAST.Common.Service.Pojo;
using FAST.Common.Service.Services.Base;

using FAST.Common.Service.Pojo.Base;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Attributes;




namespace YssProductInfo.Support.Ab.PortGroup.Service
{
    /// <summary>
    /// 功能：群组管理服务类
    /// 作者：chenwenhai
    /// 时间：20140516
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.product.information.support.modules.pg.portgroup.controller.IPortGroupController", ServiceUrl = "")]
    public interface IPortGroupService : IServiceBus
    {
        /// <summary>
        /// 获取群组A区数据
        /// </summary>
        /// <param name="paraDict">查询条件参数对象</param>
        /// <returns>查询结果集</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPortGroupA")]
        QueryRes getPortGroupA(Dictionary<string, string> paraDict);

        /// <summary>
        /// 查询群组未关联的组合列表
        /// </summary>
        /// <returns>list</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/queryPortList")]
        List<FAST.Common.Service.Pojo.Port> queryPortList();

        /// <summary>
        /// 获取群组列表
        /// </summary>
        /// <param name="paraDict">查询条件参数对象</param>
        /// <returns>查询结果集</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPortGroupListData")]
        QueryRes getPortGroupListData(Dictionary<string, string> paraDict);

        /// <summary>
        /// 根据群组代码查询所有组合
        /// </summary>
        /// <param name="c_group_code">c_group_code</param>
        /// <returns>listPort</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/querySelectedPort")]
        List<FAST.Common.Service.Pojo.Port> querySelectedPort(string c_group_code);

        /// <summary>
        /// 检查群组代码是否在组合表中已经存在，
        /// </summary>
        /// <param name="groupCode">群组代码：groupCode</param>
        /// <returns>true:已经存在；false:不存在</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/checkGroupCode")]
        string checkGroupCode(string groupCode);

        /// <summary>
        /// 获取群组关联方案浏览群组 By Jinghehe 2014-6-3
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>群组数据</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPlanRelaPortGroupAdd")]
        QueryRes getPlanRelaPortGroupAdd(Dictionary<string, string> paraDict);

        /// <summary>
        /// 获取群组关联方案浏览群组 By Jinghehe 2014-6-3
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>群组数据</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPlanRelaPortGroupBrow")]
        QueryRes getPlanRelaPortGroupBrow(Dictionary<string, string> paraDict);

        /// <summary>
        /// 判断是否存在群组代码
        /// </summary>
        /// <param name="groupCode">groupCode</param>
        /// <param name="cIden">cIden</param>
        /// <returns>true:已经存在；false:不存在</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/checkGroupCode")]
        string checkGroupCode(string groupCode, string cIden);
    }
}




