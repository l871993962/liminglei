using FAST.Common.Service.Pojo;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Pojo.Base;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using YssProductInfo.Support.Ab.AssetsTree.Pojo;
using FAST.Common.Service.Attributes;


namespace YssProductInfo.Support.Ab.AssetsTree.Service
{
    /// <summary>
    /// 资产属性结构A区
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.product.information.support.modules.ab.assetsTree_a.controller.IAssetsTree_AController", ServiceUrl = "")]
    public interface IAssertTree_AService : IServiceBus
    {
        /// <summary>
        /// 条件查询
        /// </summary>
        /// <param name="quyCon">quyCon</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondtion")]
        QueryRes selectByCondtion(string quyCon);

        /// <summary>
        /// 获取树型数据结构
        /// </summary>
        /// <param name="paraDict">查询条件参数对象</param>
        /// <returns>查询结果集</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getTreeViewData")]
        QueryRes getTreeViewData(Dictionary<string, string> paraDict);

        /// <summary>
        /// 根据方案获取数据
        /// </summary>
        /// <param name="paraDict">查询条件参数对象</param>
        /// <returns>查询结果集</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getListData")]
        string getListData(Dictionary<string, string> paraDict);

        /// <summary>
        /// 获取树型数据结构
        /// </summary>
        /// <param name="paradict">查询条件参数对象</param>
        /// <returns>查询结果列表</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getTreeViewDataList")]
        List<AssertTreeATreeView> getTreeViewDataList(Dictionary<string, string> paradict);

        /// <summary>
        /// Author : ChenLong
        /// Date   : 2016-09-26
        /// Status : Add
        /// Comment: 获取轧差分组
        /// </summary>
        /// <returns>轧差分组集合</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getNettingGroup")]
        List<AssetsTree_A> getNettingGroup();
        
        /// <summary>
        /// Author : zhoushuhang
        /// Date   : 2018-03-16
        /// Status : Add
        /// Comment: 更新【产品树形结构】A区产品类型中的执行顺序
        /// </summary>
        /// <param name="string">A</param>
        /// <returns>dd</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/updateAssOrder")]
        string updateAssOrder(List<string> pojoList);

    }
}




