
using System.Collections.Generic;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;

namespace YssInformation.Support.Bi.IeLinks.Service
{
    /// <summary>
    /// 收支项目字典数据服务接口
    /// 
    ///  Created By XiaoZhilong 2013-4-26
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.bi.ieLink.controller.IIeLinkDataController", ServiceUrl = "")]
    public interface IIeLinkDataService : IControlDataService
    {
        /// <summary>
        /// getDataListByCodes
        /// </summary>
        /// <param name="codes">codes</param>
        /// <returns>s</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByCodes")]
        List<BasePojo> getDataListByCodes(string[] codes);
		
        //// add by weijj 根据父级费用取子级费用
        /// <summary>
        /// 根据父级费用取子级费用
        /// </summary>
        /// <param name="codes">codes</param>
        /// <returns>list</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByParentCode")]
        List<BasePojo> getDataListByParentCode(string[] codes);
        
        //// add by liyanjun 2016-2-7 BUG #126592 科目体系界面的费用代码选项数据重复
        /// <summary>
        /// 科目体系界面的费用代码选项数据重复
        /// </summary>
        /// <returns>s</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getFeeDataList")]
        List<BasePojo> getFeeDataList();

        /// <summary>
        /// 根据核算项目获取费用
        /// add by Yuntao Lau 2015.01.18 STORY #26478
        /// </summary>
        /// <param name="daiCode">核算项目</param>
        /// <returns>费用代码</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByDaiCode")]
        List<BasePojo> getDataListByDaiCode(string[] daiCode); 
    }
}
