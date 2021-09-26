using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using YssInformation.Support.Sys.Dictionary.Pojo;
using FAST.Common.Service.Attributes;
using YssElecReco.Context;

namespace YssElecReco.Service.Er
{
    /// <summary>
    /// 产生电子对账核对数据service
    /// chenyoulong 20121213
    /// v1.0.0.6
    /// </summary>
    [ServiceAttribute(ServiceId = ElecrecoServiceIdConstant.OSGI_ELECRECO, ServiceController = "com.yss.uco.elecreco.er.erdata.controller.IErDataServiceController")]
    public interface IErDataService : IServiceBus
    {
        /// <summary>
        /// 获取对账类型数据
        /// </summary>
        /// <returns>对账类型字典list</returns>
        [MethodAttribute]
        List<DzType> getDzType();
    }
}
