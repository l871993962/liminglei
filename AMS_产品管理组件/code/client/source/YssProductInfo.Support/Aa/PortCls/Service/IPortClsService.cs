using FAST.Common.Service.Pojo;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Pojo.Base;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using YssProductInfo.Support.Aa.PortCls.Pojo;
using FAST.Common.Service.Attributes;


namespace YssProductInfo.Support.Aa.PortCls.Service
{
    /// <summary>
    /// 分级产品信息数据服务接口
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.product.information.support.modules.aa.portcls.controller.IPortClsController", ServiceUrl = "")]
    public interface IPortClsService : IServiceBus
    {
        /// <summary>
        /// 条件查询
        /// </summary>
        /// <param name="quyCon">quyCon</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondition")]
        QueryRes selectByCondition(string quyCon);

        /// <summary>
        /// 根据组合code 和分级组合类型 获得对应的分级组合code By Jinghehe 2014-1-7
        /// </summary>
        /// <param name="portCode">portCode</param>
        /// <param name="portClsType">portClsType</param>
        /// <returns>portClsCode</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPortClsCode")]
        string getPortClsCode(string portCode, string portClsType);

        /// <summary>
        /// 根据用户权限查找分级组合
        /// </summary>
        /// <param name="userCode">用户代码</param>
        /// <returns>数据集合</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPortClsByUser")]
        List<YssProductInfo.Support.Aa.PortCls.Pojo.PortCls> getPortClsByUser(string userCode);

        /// <summary>
        /// 根据组合代码查找分级组合
        /// </summary>
        /// <param name="portCode">用户代码</param>
        /// <returns>数据集合</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPortClsByPortCode")]
        List<YssProductInfo.Support.Aa.PortCls.Pojo.PortCls> getPortClsByPortCode(string portCode);

        /// <summary>
        /// 校验日期是否重叠
        /// add by Yuntao Lau 2015.12.19 BUG #123015
        /// </summary>
        /// <param name="paraDict">条件</param>
        /// <returns>日期是否重叠</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/checkDate")]
        string checkDate(Dictionary<string, string> paraDict);


        /// <summary>
        /// 校验日期是否重叠
        /// add by xuhanbing 2016.12.7 
        /// STORY #35787 海通资管 赢财升鑫产品的 每年基准收益率参数优化
        /// </summary>
        /// <param name="paraDict">条件</param>
        /// <returns>日期是否重叠</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/checkDateQSRQ")]
        string checkDateQSRQ(Dictionary<string, string> paraDict);
    }
}




