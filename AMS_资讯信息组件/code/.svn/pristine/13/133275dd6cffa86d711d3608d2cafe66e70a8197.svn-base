using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Attributes;

namespace YssSecInformation.Support.Mp.SecEq.Service
{
    /// <summary>
    /// ///  chenbo
    /// 2017-06-22
    /// #42948 资讯信息管理组件化拆分
    /// ----------------------------------
    /// 证券代码转换 数据服务接口
    /// author gh 20161024
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.sec.information.support.modules.mp.secTransfer.controller.ISecTransferController", ServiceUrl = "")]
    public interface ISecTransferService : IServiceBus
    {
        /// <summary>
        /// 条件查询:证券代码转换
        /// </summary>
        /// <param name="quyCon">quyCon</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondition")]
        QueryRes selectByCondition(string quyCon);

        /// <summary>
        /// <para>根据组合代码及日期,后台查询参数“转换规则”,获取对应的证券代码转换表</para>
        /// <para>STORY #36252 【南方基金】【紧急】社保组合估值表导出要也按照社保理事会债券转换规则进行转换导出 生成确认单</para>
        /// </summary>
        /// <param name="para">包含C_RELA_TYPE和D_BEGIN的参数对象</param>
        /// <returns>证券代码转换表</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getSecTranMapByCondition")]
        Dictionary<string, string> getSecTranMapByCondition(Dictionary<string, string> para);

        /// <summary>
        /// <para>根据组合代码和查询日期及参数代码,获取对应的组合自定义参数</para>
        /// <para>STORY38131【南方基金】【紧急】社保理事会要求交易席位科目是6位，不足6位前面补0</para>
        /// </summary>
        /// <param name="portCode">组合代码</param>
        /// <param name="dateStr">核算日期</param>
        /// <param name="dspCode">参数代码</param>
        /// <returns>证券代码转换规则</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getParamValue")]
        string getParamValue(string portCode, string dateStr, string dspCode);
    }
}
