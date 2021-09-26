using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Attributes;


namespace YssSecInformation.Support.Mp.SecEq.Service
{
    /// <summary>
    ///  chenbo
    /// 2017-06-22
    /// #42948 资讯信息管理组件化拆分
    /// ----------------------------------
    /// 证券代码转换功能选项服务接口
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.sec.information.support.modules.mp.secTransferPara.controller.ISecTransferParaController", ServiceUrl = "")]
    public interface ISecTransferParaService : IServiceBus
    {
        /// <summary>
        /// 功能选项修改参数
        /// </summary>
        /// <param name="pojoList">参数列表</param>
        /// <returns>修改结果</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/updateConds")]
        string updateConds(List<BasePojo> pojoList);
    }
}
