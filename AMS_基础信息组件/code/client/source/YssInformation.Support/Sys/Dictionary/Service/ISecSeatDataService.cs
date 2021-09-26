using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.DataService;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Pojo;

namespace YssInformation.Support.Sys.Dictionary.Service
{
    /// <summary>
    /// 证券交易渠道数据源服务接口
    /// 
    /// 
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.bi.tdchan.controller.ISecSeatDataController", ServiceUrl = "")]
    public interface ISecSeatDataService : IControlDataService, IKeyConvertDataService
    {
        /// <summary>
        /// getTdChanDataList
        /// </summary>
        /// <returns>list</returns>
        [MethodAttribute]
        List<BasePojo> getTdChanDataList();

        /// <summary>
        /// getTdChanDataListRes
        /// </summary>
        /// <returns>QueryRes</returns>
        [MethodAttribute]
        QueryRes getTdChanDataListRes();

        /// <summary>
        /// 取得所有数据（只包含代码和名称）
        /// add by liuxiang 2014-6-10
        /// </summary>
        /// <returns>Dictionary</returns>
        [MethodAttribute]
        Dictionary<string, string> getShortDataMap();

        /// <summary>
        /// By Jinghehe 2015-9-29 
        /// 
        /// 获取所有渠道数据，包括ALL 构造的数据
        /// </summary>
        /// <param name="types">types</param>
        /// <returns>list</returns>
        [MethodAttribute]
        List<BasePojo> getDataListByComm(string[] types);

        /// <summary>
        /// zhoushuhang 2016-4-7 在ETF补票日期界面中增加补票席位。相应的在接口类里面增加取关联席位的方法
        /// 组合关联席位
        /// </summary>
        /// <param name="portCode">组合代码</param>
        /// <returns>List</returns>
        [MethodAttribute]
        List<BasePojo> queryPortRelaTradeSeat(string portCode);

        /// <summary>
        /// BUG #172866 【加急】【南方基金】支持场外申赎业务流水的EXCEL格式的导入--存在问题汇总
        ///  edit by zouyuan 20170914 根据选择的组合信息，加载关联的交易渠道信息，以及所有机构信息
        /// </summary>
        /// <param name="portCodes">组合代码</param>
        /// <returns>List</returns>
        [MethodAttribute]
        List<BasePojo> getDataListByPort(string[] portCodes);

        /// <summary>
        /// 取得组合关联数据
        /// </summary>
        /// <param name="orgCodes">orgCodes</param>
        /// <returns>returns</returns>
        [MethodAttribute]
        List<BasePojo> getDataListByPorts(string[] orgCodes);

       
    }
}
