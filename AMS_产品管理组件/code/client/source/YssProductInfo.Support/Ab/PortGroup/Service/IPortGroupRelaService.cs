using FAST.Common.Service.Pojo;
using FAST.Common.Service.Services.Base;

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
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.product.information.support.modules.pg.portgrouprela.controller.IPortGroupRelaController", ServiceUrl = "")]
    public interface IPortGroupRelaService : IServiceBus
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
        /// <param name="c_group_code">群组代码</param>
        /// <returns>list</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/querySelectablePortList")]
        List<FAST.Common.Service.Pojo.Port> querySelectablePortList(string c_group_code);

        /// <summary>
        /// 根据群组代码查询所有组合
        /// </summary>
        /// <param name="c_group_code">c_group_code</param>
        /// <returns>listPort</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/querySelectedPortList")]
        List<FAST.Common.Service.Pojo.Port> querySelectedPortList(string c_group_code);

        /// <summary>
        /// 根据群组代码查询所有组合
        /// </summary>
        /// <param name="c_group_code">c_group_code</param>
        /// <returns>listPort</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/querySelectPortList")]
        List<FAST.Common.Service.Pojo.Port> querySelectPortList(string c_group_code);

        /// <summary>
        /// 根据群组代码查询所有组合代码
        /// </summary>
        /// <param name="c_group_code">c_group_code</param>
        /// <returns>listPortCode</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/querySelectedPortCode")]
        List<string> querySelectedPortCode(string c_group_code);

        /// <summary>
        /// 查询不在群组下的组合
        /// </summary>
        /// <param name="c_group_code">c_group_code</param>
        /// <returns>1</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/querySelectPortCode")]
        List<FAST.Common.Service.Pojo.Port> querySelectPortCode(string c_group_code);
    }
}




