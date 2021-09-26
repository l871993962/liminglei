
using System.Collections.Generic;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.DataService;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;

 
namespace YssProductInfo.Support.Ab.Port.Service
{
    /// <summary>
    /// 组合信息数据服务接口
    /// 
    /// Created By XiaoZhilong 2013-4-26
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.product.information.support.modules.ab.port.controller.IPortDataController", ServiceUrl = "")]
    public interface IPortDataService : IControlDataService, ITreeViewDataService, IKeyConvertDataService
    {
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getRightManagePortList")]
        List<YssProductInfo.Support.Ab.Port.Pojo.Port> getRightManagePortList(Dictionary<string, string> paraDict);

        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getTreeViewListByCondion")]
        List<YssProductInfo.Support.Ab.Port.Pojo.Port> getTreeViewListByCondion(Dictionary<string, string> paraDict);

        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getTreeViewResByCondion")]
        QueryRes getTreeViewResByCondion(Dictionary<string, string> paraDict);

        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDefaultPort")]
        QueryRes getDefaultPort(string ports, string cTrCode);

        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getTreeViewList")]
        List<BasePojo> getTreeViewList();

        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/doPortFilter")]
        List<BasePojo> doPortFilter();

        ////QueryRes doPortFilterRes();

        ////List<BasePojo> doPortFilter(string isDataRight, string datClass);

        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/doPortFilterRes1")]
        QueryRes doPortFilterRes(string isDataRight, string datClass);

        ////List<BasePojo> doPortFilter(string isDataRight, string datClass, string dvPortCode);

        ////QueryRes doPortFilterRes(string isDataRight, string datClass, string dvPortCode);

        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/doPortFilter3")]
        List<BasePojo> doPortFilter(string isDataRight, string datClass, string dvPortCode, string trCode);

        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/doPortFilterRes3")]
        QueryRes doPortFilterRes(string isDataRight, string datClass, string dvPortCode, string trCode);

        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPortListByDatClass")]
        List<BasePojo> getPortListByDatClass(string datClass);

        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPortListByUserAndPost")]
        List<BasePojo> getPortListByUserAndPost(string userCode, string postCode);

        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPortResByUserAndPost")]
        QueryRes getPortResByUserAndPost(string userCode, string postCode);

        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPortListByDvPortCode")]
        List<BasePojo> getPortListByDvPortCode(string dvPortCode);

        /// <summary>
        /// 查找下级组合数量
        /// </summary>
        /// <param name="portCodeP">上级组合代码</param>
        /// <returns>下级组合数量</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPortCodePSubCount")]
        string getPortCodePSubCount(string portCodeP);

        /// <summary>
        /// 询下面存在子组合的组合信息 By Jinghehe 2014-7-28 根据用户和用户岗位来加载数据
        /// </summary>
        /// <param name="userCode">userCode</param>
        /// <param name="postCode">postCode</param>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/queryParentPortTreeViewData")]
        QueryRes queryParentPortTreeViewData(string userCode, string postCode);

        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/queryProductORPort")]
        QueryRes queryProductORPort();

        /// <summary>
        /// 查询组合的子级
        /// </summary>
        /// <param name="keys">母组合代码</param>
        /// <returns>组合对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getTreePortDataByCodes")]
        List<BasePojo> getTreePortDataByCodes(string[] keys);

        /// <summary>
        /// 查询所有群组和组合
        /// </summary>
        /// <returns>查询结果集</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAllGroupAndPort")]
        List<BasePojo> getAllGroupAndPort();
		
		/// <summary>
        /// 根据权限过滤
        /// </summary>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getZzDataList")]
        List<BasePojo> getZzDataList();
		
		/// <summary>
        /// Author : ChenLong
        /// Date   : 2015-01-13
        /// Status : Add
        /// Comment: 过滤用户有增删改权限的岗位下的组合
        /// </summary>
        /// <returns>过滤得到有岗位下角色有增删改权限的组合集合</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getFilterPortDataForOperRight")]
        List<BasePojo> getFilterPortDataForOperRight(string menuId);

        /// <summary>
        /// 绑定组合与岗位
        /// </summary>
        /// <param name="userCode">用户代码</param>
        /// <param name="portCodes">岗位代码</param>
        /// <returns>组合岗位集合 key:组合 value:岗位（岗位a|岗位b）</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getBindPortAndPost")]
        Dictionary<string, string> getBindPortAndPost(string userCode, string portCodes);

        /// <summary>
        /// Author : ChenLong
        /// Date   : 2015-01-28
        /// Status : Add
        /// Comment: 过滤获得有权限的组合
        /// </summary>
        /// <param name="userCode">用户代码</param>
        /// <param name="postCode">岗位代码</param>
        /// <param name="menuId">功能代码</param>
        /// <returns>组合数据集合</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/queryParentPortTreeViewData")]
        QueryRes queryParentPortTreeViewData(string userCode, string postCode, string menuId);

        /// <summary>
        /// Author : liyanjun
        /// Date   : 2016-08-3
        /// Status : Add  STORY #32402 【招商证券】证券行情映射“映射组合”权限开放
        /// Comment: 获取所有组合信息
        /// </summary>
        /// <returns>组合数据集合</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAllDataList")]
        List<BasePojo> getAllDataList();

        /// <summary>
        /// 查询子组合
        /// </summary>
        /// <param name="protPCode">主组合代码</param>
        /// <returns>组合数据集合</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getUnitLayerPort")]
        List<BasePojo> getUnitLayerPort(string[] protPCode);

        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByCodes")]
        List<BasePojo> getDataListByCodes(string codes);
    }
}
