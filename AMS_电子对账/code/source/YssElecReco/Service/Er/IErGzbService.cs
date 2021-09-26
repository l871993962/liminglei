using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using YssElecReco.Pojo.Er;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;
using YssElecReco.Context;

namespace YssElecReco.Service.Er
{
    /// <summary>
    /// 浏览电子对账信息service
    /// chenyoulong 20121226
    /// v1.0.0.7
    /// </summary>
    [ServiceAttribute(ServiceId = ElecrecoServiceIdConstant.OSGI_ELECRECO, ServiceController = "com.yss.uco.elecreco.er.ergzb.controller.IErGzbServiceController")]
    public interface IErGzbService : IServiceBus
    {
        /// <summary>
        /// 根据添加查询电子对账估值信息
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>返回估值列表</returns>
        [MethodAttribute]
        List<ErGzb> getGzData(Dictionary<string, string> paraDict);

        /// <summary>
        /// 获取电子对账关联指标代码
        /// edit by qiantao STORY #83025 产品估值参数控制实收资本小数位 
        /// </summary>
        /// <param name="dzCode">对账类型</param>
        /// <param name="zbCode">指标代码</param>
        /// <returns></returns>
        [MethodAttribute]
        List<String> getRealIndexCode(string dzCode, string zbCode);

        /// <summary>
        /// 格式化实收资本数量
        /// </summary>
        /// <param name="ports">组合</param>
        /// <param name="formatData">实收资本数量</param>
        /// <returns>格式化后的实收资本数量</returns>
        [MethodAttribute]
        Dictionary<string, string> formatedData(string ports, Dictionary<string, string> formatData);
    }
}
