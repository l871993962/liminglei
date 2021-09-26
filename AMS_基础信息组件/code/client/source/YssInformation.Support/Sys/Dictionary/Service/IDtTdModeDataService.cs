
using System.Collections.Generic;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.DataService;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;


////namespace YssBaseCls.Service
////namespace YssInformation.Sys.Dictionary.Service
namespace YssInformation.Support.Sys.Dictionary.Service
{
    /// <summary>
    /// 交易方式和销售方式字典数据服务接口
    /// 
    /// Created By XiaoZhilong 2013-4-26
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.sys.dttdmode.controller.IDtTdModeDataController", ServiceUrl = "")]
    public interface IDtTdModeDataService : IControlDataService, IKeyConvertDataService
    {
        List<BasePojo> getTreeDataList();
        
        /// <summary>
        /// 树形结构展示交易方式
        /// 
        /// Added By xzl
        /// </summary>
        /// <param name="types">交易类型</param>
        /// <returns>list</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getTreeDataByTypes")]
        List<BasePojo> getTreeDataByTypes(string[] types);

        /// <summary>
        /// 取得所有数据（只包含代码和名称）
        /// add by liuxiang 2014-6-10
        /// </summary>
        /// <param name="c_BUSI_TYPE">业务类型</param>
        /// <returns>包含代码和名称的键值对</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getShortDataMap")]
        Dictionary<string, string> getShortDataMap(string c_BUSI_TYPE);


        /// <summary>
        /// 加载分组拆分 交易方式 
        /// By Jinghehe 2015-11-08
        /// </summary>
        /// <returns>交易方式</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getTreeDataListForRule")]
        List<BasePojo> getTreeDataListForRule();

        /// <summary>
        /// 加载分组拆分 获取交易方式中的父节点
        /// </summary>
        /// <param name="c_BUSI_TYPE">父节点业务</param>
        /// <returns>交易方式</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataByCodeForRule")]
        BasePojo getDataByCodeForRule(string c_BUSI_TYPE);
        /// <summary>
        /// 恒生交易数据业务分类服务接口
        /// and by liyanjun 2016-2-20 STORY #28608 【广发证券】在分组恒生交易数据文件接口添加控制
        /// </summary>
        /// <param name="cfgCode">cfgCode</param>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getTreeDataByCfgCode")]
        List<BasePojo> getTreeDataByCfgCode(string[] cfgCodes);

        /// <summary>
        /// 通过词汇分类加载对应值
        /// add by wangtangyao 20160815 STORY #28887 保险资产证券清算款、其他应收款科目处理
        /// </summary>
        /// <param name="cfgCode">type</param>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getSQKDataListByTypes")]
        List<BasePojo> getSQKDataListByTypes(string[] type);

        /// <summary>
        /// 通过词汇分类获取转换后的销售方式对象
        /// add by wangtangyao 20160815 STORY #28887 保险资产证券清算款、其他应收款科目处理
        /// </summary>
        /// <param name="cfgCode">type</param>
        /// <returns></returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getSQKDataByCode")]
        List<BasePojo> getSQKDataByCode(string[] type);

        /// <summary>
        /// 通过功能代码，获取对应模块的交易方式
        /// add by yuanyafeng 20180911 STORY #61545 【紧急】太平保险-附件管理优化（二期）
        /// </summary>
        /// <param name="funCode">功能代码</param>
        /// <returns>交易方式</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByFun")]
        List<BasePojo> getDataListByFun(string funCode);
    }
}
