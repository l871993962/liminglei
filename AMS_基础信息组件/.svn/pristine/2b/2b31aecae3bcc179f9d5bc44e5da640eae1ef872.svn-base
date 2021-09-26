using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Dict.Pojo;
using FAST.Common.Service.Attributes;
using FAST.Common.Service.Pojo;
using YssInformation.Support.Sys.automaticSet.Pojo;
using FAST.Platform.DataIntegration.Imp.Pojos;
using FAST.Common.Service.Pojo.Base;

namespace YssInformation.Support.Sys.automaticSet.Service
{
    /// <summary>
    /// 自动化业务设置数据接口【外部渠道组合路径设置】、【估值指标】
    /// </summary>
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.sys.automaticSet.controller.IAutomaticSetPathController", ServiceUrl = "")]
    public interface IAutomaticSetPathService : IServiceBus
    {
        /// <summary>
        /// STORY #105821 【东证资管】券商结算模式增加动态判断的接口
        /// 获取产品业务分类
        /// </summary>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAllProductType")]
        List<Vocabulary> getAllProductType();

        /// <summary>
        /// STORY #105821 【东证资管】券商结算模式增加动态判断的接口
        /// 根据条件查询数据
        /// </summary>
        /// <param name="paraMap">查询条件参数集合</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/queryDataList")]
        QueryRes queryDataList([RequestBodyAttribute(ParameterName = "paraMap")] Dictionary<string, string> param);

        /// <summary>
        /// STORY #105821 【东证资管】券商结算模式增加动态判断的接口
        /// 加载产品业务分类、接口代码
        /// </summary>
        /// <param name="type">type</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getInterfaceClass")]
        List<Vocabulary> getInterfaceClass();

        /// <summary>
        /// STORY #105821 【东证资管】券商结算模式增加动态判断的接口
        /// 更新产品业务分类、接口代码
        /// </summary>
        /// <param name="type">type</param>
        /// <param name="paraDict">dataList</param>
        /// <returns>执行状态</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/updateDataList")]
        bool updateDataList([RequestBodyAttribute(ParameterName = "paraMap")]  List<Dictionary<string, string>> dataList);

        /// <summary>
        /// STORY #105821 【东证资管】券商结算模式增加动态判断的接口
        /// 获取产品业务分类
        /// </summary>
        /// <param name="type">type</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getProductType")]
        List<AutomaticSetPojo> getProductType();

        /// <summary>
        /// STORY #105821 【东证资管】券商结算模式增加动态判断的接口
        /// 通过接口分类获取对应的接口信息
        /// </summary>
        /// <param name="productName"></param>
        /// <returns></returns>
        List<ImpCfgGroup> getInterfaceData(List<string> productName);

        /// <summary>
        /// STORY #105821 【东证资管】券商结算模式增加动态判断的接口
        /// 新增
        /// </summary>
        /// <param name="type">type</param>
        /// <param name="paraDict">dataList</param>
        /// <returns>执行状态</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/saveDataList")]
        bool saveDataList(List<string> proList, List<Dictionary<string, string>> dataList);

        /// <summary>
        /// STORY #105821 【东证资管】券商结算模式增加动态判断的接口
        /// 删除
        /// </summary>
        /// <param name="automaticSetPathPojo"></param>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/deleteById")]
        string deleteById(List<AutomaticSetPojo> list);

        /// <summary>
        /// STORY #105821 【东证资管】券商结算模式增加动态判断的接口
        /// 审核
        /// </summary>
        /// <param name="automaticSetPathPojo"></param>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/auditById/list")]
        string auditById(List<AutomaticSetPojo> pojoLost);

        /// <summary>
        /// STORY #105821 【东证资管】券商结算模式增加动态判断的接口
        /// 反审核
        /// </summary>
        /// <param name="automaticSetPathPojo"></param>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/unAuditById/list")]
        string unAuditById(List<AutomaticSetPojo> pojoLost);

        /// <summary>
        /// STORY #105821 【东证资管】券商结算模式增加动态判断的接口
        /// 复制
        /// </summary>
        /// <param name="data"></param>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/copy")]
        bool copy(Dictionary<string, string> data);

        /// <summary>
        /// STORY #106974 【海富通】【自动化业务设置】新增【估值指标】分页 （关联需求STORY #106396 ）
        /// 获取所有的估值指标
        /// </summary>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAllIndex")]
        List<AutomaticSetPojo> getAllIndex();

        /// <summary>
        /// STORY #106974 【海富通】【自动化业务设置】新增【估值指标】分页 （关联需求STORY #106396 ）
        /// 新增
        /// </summary>
        /// <param name="dataList"></param>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/saveList")]
        bool saveList(List<Dictionary<string, string>> dataList);

        /// <summary>
        /// STORY #107189 【东证资管】自动化业务设置-外部渠道组合路径设置，组合维护的路径能同步或复制给其他组合。
        /// 查询所有可参照的组合
        /// </summary>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getRePortCodeList")]
        List<AutomaticSetPojo> getRePortCodeList();

        /// <summary>
        /// STORY #107189 【东证资管】自动化业务设置-外部渠道组合路径设置，组合维护的路径能同步或复制给其他组合。
        /// 通过组合代码和产品业务分类查询对应存的接口信息
        /// </summary>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/queryByCodeAndName")]
        List<BasePojo> queryByCodeAndName(string portCode, List<string> productList);
    }
}
