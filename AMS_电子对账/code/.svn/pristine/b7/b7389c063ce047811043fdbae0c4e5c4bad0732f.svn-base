using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.Attributes;
using YssElecReco.Context;

namespace YssElecReco.Service.Er
{
    /// <summary>
    /// 主要指标方案生成服务-STORY #41248 【南方基金】电子对账反馈结果中的差异数据下拉框扩展
    /// </summary>
    [ServiceAttribute(ServiceId = ElecrecoServiceIdConstant.OSGI_ELECRECO, ServiceController = "com.yss.uco.elecreco.er.erresview.controller.IErResviewServiceController")]
    public interface IErResviewService : IServiceBus, IControlDataService
    {
        /// <summary>
        /// 根据方案代码获取主要指标代码
        /// </summary>
        /// <param name="code">code</param>
        /// <returns>List</returns>
        [MethodAttribute]
        List<string> queryItemCodesByPlanCode(string code);

        /// <summary>
        /// 根据方案代码删除数据
        /// </summary>
        /// <param name="code">code</param>
        [MethodAttribute]
        void deleteByPlanCode(string code);
    }
}
