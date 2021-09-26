using FAST.Common.Service.DataService.Base;
using System;
using FAST.Common.Service.Pojo.Base;
using System.Collections.Generic;
using FAST.Common.Service.DataService;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Services;
using FAST.Common.Service.Pojo;
using FAST.Core.Cache;
using FAST.Common.Service.Attributes;

namespace YssInformation.Support.Bi.AssociationOrgan.Service
{
    /// <summary>
    /// 机构数据服务接口
    /// 
    /// Created By XiaoZhilong 2013-4-26
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [CacheAttribute(CacheGroup.ORG)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.bi.org.controller.IOrgDataController", ServiceUrl = "")]
    public interface IOrgDataService : IControlDataService, IKeyConvertDataService, IDataServiceForCache
    {
        /// <summary>
        /// 定义下拉框的树形排序
        /// Created By yaohongwei 2016-4-26
        /// </summary>
        /// <param name="param"></param>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListTree")]
        List<BasePojo> getDataListTree(String[] param);
        
        /// <summary>
        /// 查询所有银行总行
        /// </summary>
        /// <returns>符合条件的数据</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAllBankHead")]
        List<BasePojo> getAllBankHead();

        /// <summary>
        /// 根据总行查银行支行
        /// </summary>
        /// <param name="types">符合条件的数据</param>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getBankBranchByHead")]
        List<BasePojo> getBankBranchByHead(string[] param);
		
		/// <summary>
        /// add by shijian 2016-10-12 STORY #35056 嘉实基金--成交清算日报表--增加名义管理人等字段
        /// 按查询条件查询数据
        /// </summary>
        /// <param name="condition">查询条件</param>
        /// <returns>符合条件的数据</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByCondition")]
        List<BasePojo> getDataListByCondition(String condition);

        /// <summary>
        /// 按机构资质查询机构数据
        /// </summary>
        /// <param name="types"></param>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByAptitude")]
        List<BasePojo> getDataListByAptitude(string[] types);

        /// <summary>
        /// 按机构资质查询机构数据
        /// </summary>
        /// <param name="types"></param>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByAptitude2")]
        List<BasePojo> getDataListByAptitude2(string[] types);
        /// <summary>
        /// 按机构类型查询顶级机构数据
        /// </summary>
        /// <param name="types"></param>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getParentListByTypes")]
        List<BasePojo> getParentListByTypes(string[] types);

    ////    Dictionary<string, string> getKeyConvertMap(List<string> keys);

        /// <summary>
        /// 获取更新条数
        /// </summary>
        /// <param name="lcDateTime">时间</param>
        /// <returns>符合条件的数据数</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getUpdateByTimestampCount")]
        string getUpdateByTimestampCount(string lcDateTime);

        /// <summary>
        /// 根据时间分页查询
        /// </summary>
        /// <param name="timestamp">时间</param>
        /// <param name="page">分页信息</param>
        /// <returns>缓存</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/updateByTimestampPage")]
        CacheData updateByTimestampPage(string timestamp, PageInation page);

        /// <summary>
        /// 按主体资质查询机构数据
        /// </summary>
        /// <param name="types"></param>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByZtzz")]
        List<BasePojo> getDataListByZtzz(string[] types);

        /// <summary>
        ///  
        /// </summary>
        /// <param name="ids">ids</param>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/updateByIds")]
        CacheData updateByIds(String ids);
    }
}
