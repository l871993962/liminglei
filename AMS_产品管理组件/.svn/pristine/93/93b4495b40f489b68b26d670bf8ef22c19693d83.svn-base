using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Attributes;

namespace YssProductInfo.Support.Ab.PortGroup.Service
{
    /// <summary>
    /// 功能：A区群组 服务
    /// 时间：20140526
    /// 作者：chenwenhai
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.product.information.support.modules.pg.portgroup.controller.IPortGroupDataController", ServiceUrl = "")]
    public interface IPortGroupDataService : IControlDataService, IDataService
    {
        /// <summary>
        /// 获取群组A区数据
        /// </summary>
        /// <param name="paraDict">查询条件参数对象</param>
        /// <returns>查询结果集</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPortGroupA")]
        QueryRes getPortGroupA(Dictionary<string, string> paraDict);

        /// <summary>
        /// 根据群组代码查询所有组合代码
        /// </summary>
        /// <param name="c_group_code">c_group_code</param>
        /// <returns>listPortCode</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/querySelectedPortCode")]
        List<string> querySelectedPortCode(string c_group_code);

        /// <summary>
        /// 根据群组代码查询所有组合代码 返回对象QueryRes
        /// </summary>
        /// <param name="c_group_code">c_group_code</param>
        /// <returns>listPortCode</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/querySelectedPort")]
        QueryRes querySelectedPort(string c_group_code);

        /// <summary>
        /// 查询A区群组数据，以树形展示
        /// </summary>
        /// <returns>res</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getGroupDataTree")]
        QueryRes getGroupDataTree();

        /// <summary>
        /// 检查组合代码是否在群组中已经存在
        /// </summary>
        /// <param name="portCode">portCode</param>
        /// <returns>true:已经存在；false:不存在</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/checkPortCode")]
        string checkPortCode(string portCode);

        /// <summary>
        /// 获取当前登录用户下有权限的群组
        /// add by shijian 2018-10-22 STORY #62048 新增加的组合自动关联自动化估值方案
        /// </summary>
        /// <returns>群组列表</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAuthorityGroup")]
        List<BasePojo> getAuthorityGroup();
    }
}
