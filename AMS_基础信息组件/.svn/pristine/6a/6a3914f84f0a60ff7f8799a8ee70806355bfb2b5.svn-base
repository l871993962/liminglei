
using System.Collections.Generic;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.DataService;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;

namespace YssInformation.Support.Bi.Market.Service
{
    /// <summary>
    /// 交易市场信息数据服务接口
    /// 
    /// Created By XiaoZhilong 2013-4-26
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.bi.mkt.controller.IMktDataController", ServiceUrl = "")]
    public interface IMktDataService : IControlDataService, IKeyConvertDataService
    {
        /// <summary>
        /// 取得所有数据（只包含代码和名称）
        /// add by liuxiang 2014-6-10
        /// </summary>
        /// <returns>包含代码和名称的键值对</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getShortDataMap")]
        Dictionary<string, string> getShortDataMap();

        /// <summary>
        /// 根据市场代码取值
        /// add by  dingshalu 2015-08-05 STORY #19553 最低备付金调整
        /// </summary>
        /// <param name="keys">可根据多个市场代码取值</param>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAllDataSqlByKeys")]
        List<BasePojo> getAllDataSqlByKeys(string[] keys);

        /// <summary>
        /// 获取清算机构
        /// add by  guohui 2015-12-13 STORY36399【招商基金】【紧急】股票、债券、基金等交易流水界面增加“交易市场”字段供TB导出接口取对应的清算信息
        /// </summary>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListAux")]
        List<BasePojo> getDataListAux();
    }
}
