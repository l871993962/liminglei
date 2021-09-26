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



namespace YssSecInformation.Support.Sv.Service
{
    /// <summary>
    /// 债券每日利息数据服务接口
    /// author ll 20120928
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.sec.information.support.modules.sv.fiincome.controller.IFiIncomeController", ServiceUrl = "")]
    public interface IFiIncomeService : IServiceBus
    {
        /// <summary>
        /// 条件查询:证券行情,存款利率
        /// </summary>
        /// <param name="quyCon">quyCon</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondition")]
        QueryRes selectByCondition(string quyCon);

        /// <summary>
        /// 根据每百元利息界面点击计算税前利息按钮计算方法
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>string</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/calcBeforeTaxAndDue")]
        string calcBeforeTaxAndDue(Dictionary<string, string> paraDict);

    }
}


