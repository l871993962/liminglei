using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;



namespace YssProductInfo.Support.Ab.Port.Service
{
    /// <summary>
    /// 产品关联账户服务
    /// </summary>
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.product.information.support.modules.ab.port.controller.IPortFundRelaController", ServiceUrl = "")]
    public interface IPortFundRelaService : IServiceBus
    {
        /// <summary>
        /// 根据组合代码和账户主键删除产品关联账户关联表数据
        /// </summary>
        /// <param name="portCodes">组合代码</param>
        /// <param name="fundAccId">账户数据主键ID</param>
        /// <returns>删除结果</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/deletePortFundRela")]
        string deletePortFundRela(string portCodes, string fundAccId);
    }
}
