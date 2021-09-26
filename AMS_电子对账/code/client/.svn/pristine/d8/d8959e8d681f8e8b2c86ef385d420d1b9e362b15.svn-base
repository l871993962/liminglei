using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;
using YssElecReco.Context;


namespace YssElecReco.Service.Bi
{
    /// <summary>
    /// 电子对账指标关联
    /// chenyoulong 20121213
    /// v1.0.0.6
    /// </summary>
    [ServiceAttribute(ServiceId = ElecrecoServiceIdConstant.OSGI_ELECRECO, ServiceController = "com.yss.uco.elecreco.bi.elecrela.controller.IElecRelaServiceController")]
    public interface IElecRelaService : IServiceBus
    {
        /// <summary>
        /// 获取所有数据
        /// </summary>
        /// <returns>List</returns>
        [MethodAttribute]
        List<FAST.Common.Service.Pojo.Base.BasePojo> getDataList();

        /// <summary>
        /// 根据指标名称获取数据
        /// </summary>
        /// <param name="paraList">paraList</param>
        /// <returns>List</returns>
        [MethodAttribute]
        List<FAST.Common.Service.Pojo.Base.BasePojo> getDataListByName(List<string> paraList);
    }
}
