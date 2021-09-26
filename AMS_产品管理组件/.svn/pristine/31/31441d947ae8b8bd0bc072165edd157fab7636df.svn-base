using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Attributes;

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;


namespace YssProductInfo.Support.Ab.AssetsTree.Service
{
    /// <summary>
    /// 资产属性结构
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.product.information.support.modules.ab.assetstree_b.controller.IAssetsTree_BController", ServiceUrl = "")]
    public interface IAssertTreeService : IServiceBus
    {
        /// <summary>
        /// 条件查询
        /// </summary>
        /// <param name="quyCon">quyCon</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondition")]
        QueryRes selectByCondition(string quyCon);

        /// <summary>
        /// 值改变时获取数据
        /// </summary>
        /// <param name="changedValue">changedValue</param>
        /// <returns>获取的字符串</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getData")]
        string getData(string changedValue);

        /// <summary>
        /// 获取set界面的资产信息
        /// </summary>
        /// <param name="paraDict">参数集合</param>
        /// <returns>查询结果</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAssetTreeView")]
        QueryRes getAssetTreeView(Dictionary<string, string> paraDict);
        /// <summary>
        /// add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
        /// 判断拖入行节点与选择行节点的组合信息是否为同一资产类型
        /// </summary>
        /// <param name="portCode">portCode</param>
        /// <param name="dragPortCode">dragPortCode</param>
        /// <returns>是否</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/isSameAssetType")]
        string isSameAssetType(string portCode, String dragPortCode);

        /// <summary>
        /// add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
        /// 删除或者更新组合信息对应树形结构节点
        /// </summary>
        /// <param name="id">行ID</param>
        /// <param name="trCode">结构代码</param>
        /// <param name="isParent">更新行是否为父级节点行</param>
        /// <param name="type">执行类型：DELETE更新，DELETE删除</param>
        /// <returns>执行条数</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/updateOrdelete")]
        int updateOrdelete(string id, string trCode, string isParent, string type);

		/// <summary>
        /// add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
        /// 删除或者更新组合信息对应树形结构节点
        /// </summary>
        /// <param name="id">行ID</param>
        /// <param name="trCode">结构代码</param>
        /// <param name="isParent">更新行是否为父级节点行</param>
        /// <param name="type">执行类型：DELETE更新，DELETE删除</param>
        /// <param name="trCodeR">顶级节点</param>
        /// <returns>执行条数</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/updateOrdelete1")]
        int updateOrdelete(string id, string trCode, string isParent, string type, string trCodeR);

        /// <summary>
        /// add by zhoushuhang 2018-06-13 BUG206147节点code不能与组合代码相同
        /// 查询顶级根节点下的所有子节点
        /// </summary>
        /// <param name="topParentCode">最顶级节点代码</param>
        /// <returns>数据集合</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/quertAllNodeCode")]
        Dictionary<string, string> quertAllNodeCode(string topParentCode);
		
		/// <summary>
        /// 获取set界面的资产信息
        /// </summary>
        /// <param name="quyCon">quyCon</param>
        /// <returns>查询结果</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getUserId")]
        string getUserId(string quyCon);

        /// <summary>
        /// 根据c_iden获取code
        /// </summary>
        /// <param name="id">c_iden</param>
        /// <returns>code</returns>
        [MethodAttribute]
        string getCodeByCId(string id);
    }
}




