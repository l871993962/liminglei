////using YssBaseCls.Fun;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;

using FAST.Common.Service.Attributes;
using FAST.Core.Context;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;



namespace YssInformation.Support.Sys.Dictionary.Service
{
    /// <summary>
    /// 费用关联service接口
    /// </summary>
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.sys.feeRelation.controller.IFeeRelationController", ServiceUrl = "")]
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    public interface IFeeRelationService : IServiceBus
    {
        /// <summary>
        /// 条件查询
        /// </summary>
        /// <param name="quyCon">quyCon</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondition")]
        QueryRes selectByCondition(string quyCon);

        /// <summary>
        /// 资产交易-查询费用列表信息
        /// </summary>
        /// <param name="paraMap">paraMap</param>
        /// <returns>QueryRes</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/queryRealDataByCondition")]
        QueryRes queryRealDataByCondition(Dictionary<string, string> paraMap);


        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/queryByConditionFee")]
        QueryRes queryByConditionFee(Dictionary<string, string> paraMap); 
    }

}
