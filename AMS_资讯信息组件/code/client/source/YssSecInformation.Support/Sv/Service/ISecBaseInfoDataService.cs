using System.Collections.Generic;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.DataService;
using FAST.Common.Service.Services;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Cache;
using FAST.Common.Service.Pojo;
using YssSecInformation.Support.Pojo;
using YssSecInformation.Support.Pojo.Sec;
using FAST.Common.Service.Services.Base;
using YssSecInformation.Support.Sv.Pojo;
using FAST.Common.Service.Attributes;


namespace YssSecInformation.Support.Sv.Service
{
    /// <summary>
    /// 证券基本信息数据源服务接口
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [CacheAttribute(CacheGroup.SECBASE)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.sec.information.support.modules.sv.base.controller.ISecBaseInfoDataController", ServiceUrl = "")]
    public interface ISecBaseInfoDataService : IControlDataService, IKeyConvertDataService, IDataServiceForCache
    {
        /// <summary>
        /// Add by zhaijiajia 20161126 合并需求：STORY #35438 【南方基金】【紧急】社保资产企业债公司债中期票据匹配到同一个企业公司债债的科目需求 
        /// </summary>
        /// <param name="types">types</param>
        /// <returns>list</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByTypesAndMktNew")]
        List<BasePojo> getDataListByTypesAndMktNew(string[] types);

        /// <summary>
        /// text
        /// </summary>
        /// <param name="types">types</param>
        /// <returns>list</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByTypesAndMkt")]
        List<BasePojo> getDataListByTypesAndMkt(string[] types);

        /// <summary>
        /// 根据核算元素查数据
        /// </summary>
        /// <param name="parameter">参数</param>
        /// <returns>数据列表</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByDaes")]
        List<BasePojo> getDataListByDaes(string parameter);

        /// <summary>
        /// text
        /// </summary>
        /// <param name="cSecCode">cSecCode</param>
        /// <returns>basePojo</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getSecBaseInfoDataBySecCode")]
        BasePojo getSecBaseInfoDataBySecCode(string cSecCode);

        ////List<BasePojo> getShortDataList(string[] types);

        /// <summary>
        /// test
        /// </summary>
        /// <param name="types">types</param>
        /// <param name="like">like</param>
        /// <param name="page">page</param>
        /// <returns>pojo</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondition")]
        ShortDataListPackage<SecShortPojo> getShortDataList([RequestBodyAttribute(ParameterName = "types")] string[] types, [RequestBodyAttribute(ParameterName = "like")] string like, [RequestBodyAttribute(ParameterName = "page")] PageInation page);

        /// <summary>
        /// 根据证券品种和到期日期查询证券信息
        /// </summary>
        /// <param name="types">证券品种</param>
        /// <param name="dateStr">到期日期</param>
        /// <returns>证券信息列表</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByTypesAndDate")]
        List<BasePojo> getDataListByTypesAndDate([RequestBodyAttribute(ParameterName = "types")] string[] types, [RequestBodyAttribute(ParameterName = "dateStr")] string dateStr);

        /// <summary>
        /// 取得所有数据（只包含代码和名称）
        /// add by liuxiang 2014-6-10
        /// </summary>
        /// <returns>包含代码和名称的键值对</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getShortDataMap")]
        Dictionary<string, string> getShortDataMap();

        /// <summary>
        /// 把所有的指数信息封装成证券基本信息，供下拉框选择 
        /// BY Jinghehe 2014-8-4
        /// </summary>
        /// <returns>list</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAllIndexDataList")]
        List<BasePojo> getAllIndexDataList();


        /// <summary>
        /// 获取证券信息(根据上市代码和市场代码)  
        /// by xhb 2015-12-16
        /// </summary>
        /// <param name="secMktCode">上市代码</param>
        /// <param name="mktCode">市场代码</param>
        /// <returns>pojo</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataBySecMktCodeAndMktCode")]
        BasePojo getDataBySecMktCodeAndMktCode(string secMktCode, string mktCode);

        /// <summary>
        /// 根据分页信息查询数据
        /// </summary>
        /// <param name="timestamp">timestamp</param>
        /// <param name="page">page</param>
        /// <returns>CacheData</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/updateByTimestampPage")]
        CacheData updateByTimestampPage(string timestamp, PageInation page);

        /// <summary>
        /// 查询数据量
        /// </summary>
        /// <param name="timestamp">timestamp</param>
        /// <returns>数量</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getUpdateByTimestampCount")]
        string getUpdateByTimestampCount(string timestamp);

        /// <summary>
        /// add by zhd 2016-09-20
        /// STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
        /// 得到所有实际所属证券为空的证券
        /// </summary>
        /// <param name="types">types</param>
        /// <returns>list</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListBySjsszq")]
        List<BasePojo> getDataListBySjsszq(string[] types);

        /// <summary>
        /// 检查某证券是否存在持仓 
        /// add by liyanjun 2016-8-29 STORY31079 修改咨询信息品种有持仓的情况下给予提醒
        /// </summary>
        /// <param name="secCode">证券代码</param>
        /// <returns>false/ture</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/isExistsStock")]
        string isExistsStock(string secCode);

        /// <summary>
        /// 根据IDS获取证券数据(用于缓存更新)
        /// </summary>
        /// <param name="ids">ids</param>
        /// <returns>缓存数据</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/updateByIds")]
        CacheData updateByIds(string ids);

        /// <summary>
        /// BUG #268715 【融通基金】-各个界面债券选择控件，债券信息加载不出来，右下角刷新缓存也不行，需要再次反审核债券再审核债券品种信息
        /// 通过从后台缓存中拿
        /// </summary>
        /// <param name="types">types</param>
        /// <param name="paraValue">paraValue</param>
        /// <returns>list</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByTypes2")]
        List<BasePojo> getDataListByTypes([RequestBodyAttribute(ParameterName = "types")] string[] types, [RequestBodyAttribute(ParameterName = "paraValue")] string paraValue);

        /// <summary>
        /// STORY #67431 【YSS】估值核算系统缓存刷新增加前台干预入口
        /// </summary>
        /// <param name="codes">codes</param>
        /// <returns>List</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/UpdateDifferent")]
        List<string> UpdateDifferent(string codes);

        /// <summary>
        /// STORY #67431 【YSS】估值核算系统缓存刷新增加前台干预入口
        /// </summary>
        /// <param name="p">p</param>
        /// <returns>CacheData</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/updateByCodes")]
        CacheData updateByCodes(string p);
    }
}
