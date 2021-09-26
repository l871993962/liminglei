using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Attributes;



namespace YssProductInfo.Support.DataCopy.Service
{
    /// <summary>
    /// 数据复制服务
    /// </summary>
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.product.information.support.modules.dataCopy.controller.IDataCopyController", ServiceUrl = "")]
    public interface IDataCopyService : IServiceBus
    {
        /// <summary>
        /// 执行
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <param name="selCellPojo">selCellPojo</param>
        /// <returns>true</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/exe")]
        string exe([RequestBodyAttribute(ParameterName = "map")] Dictionary<string, string> paraDict, [RequestBodyAttribute(ParameterName = "list")] List<BasePojo> selCellPojo);

        /// <summary>
        /// 执行
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>true</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/exe1")]
        string exe(Dictionary<string, string> paraDict);

        /// <summary>
        /// 验证配置以及选择的数据是否可以执行
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>paraDict</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/verify")]
        List<string> verify(Dictionary<string, string> paraDict);

        /// <summary>
        /// 获得当前用户的常用数据
        /// </summary>
        /// <returns>paraDict</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/queryCustom")]
        List<string> queryCustom();

        /// <summary>
        /// {产品基本信息}中复制创建功能 获取初始数据
        /// </summary>
        /// <returns>QueryRes</returns>
        /// <param name="portCode">参照组合代码</param>
        ////edit by huangjin 2016-9-22 STORY #28950 参数复制功能优化-复制权限和群组以及产品数
        ////参照组合改变时需要往后台传参数
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/queryCreateCopy")]
        QueryRes queryCreateCopy(string portCode);

        /// <summary>
        /// {产品分级参数}中复制创建功能 获取初始数据
        /// </summary>
        /// <returns>QueryRes</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/queryPortClsCreateCopy")]
        QueryRes queryPortClsCreateCopy();

        /// <summary>
        /// 查询数据总数
        /// STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况
        /// </summary>
        /// <param name="paraDict">参数</param>
        /// <returns>数据总数</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getCopyCheckDataTotal")]
        string getCopyCheckDataTotal(Dictionary<string, string> paraDict);

        /// <summary>
        /// STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况
        /// 查询产品参数复制-检查
        /// </summary>
        /// <param name="paraMap">参数</param>
        /// <param name="page">分页</param>
        /// <returns>产品参数复制</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/queryCopyCheckData")]
        QueryRes queryCopyCheckData(Dictionary<string, string> paraMap, PageInation page);
    }
}
