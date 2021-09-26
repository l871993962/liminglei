using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Attributes;
using YssElecReco.Context;

namespace YssElecReco.Service.Er
{
    /// <summary>
    /// STORY #36615 【紧急】南方基金-系统需要支持存在多个财务报表的电子对账
    /// 对账报表配置服务类 zhanghualin 2016-12-09
    /// </summary>
    [ServiceAttribute(ServiceId = ElecrecoServiceIdConstant.OSGI_ELECRECO, ServiceController = "com.yss.uco.elecreco.support.controller.IDzRepCfgServiceController")]
    public interface IDzRepCfgService : IServiceBus 
    {
        [MethodAttribute]
        QueryRes getReportTemplateTreeView(string ports);
    }
}