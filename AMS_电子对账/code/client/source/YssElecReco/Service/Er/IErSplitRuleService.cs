using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using YssElecReco.Pojo.Er;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Attributes;
using YssElecReco.Context;

namespace YssElecReco.Service.Er
{
    [ServiceAttribute(ServiceId = ElecrecoServiceIdConstant.OSGI_ELECRECO, ServiceController = "com.yss.uco.elecreco.er.spilt.rule.controller.IErSplitRuleServiceController")]
	public interface IErSplitRuleService : IServiceBus  
	{
        /// <summary>
        /// 更新映射规则，界面保存按钮调用的入口
        /// </summary>
        /// <param name="addRules"></param>
        /// <param name="removeRules"></param>
        /// <returns></returns>
        [MethodAttribute]
        string updateRelaDetailKmInfo(List<ErSplitRule> addRules, List<ErSplitRule> removeRules);
        /// <summary>
        /// 显示该组合在该日期没有设置拆分规则的明细科目
        /// </summary>
        /// <param name="portCode"></param>
        /// <param name="date"></param>
        /// <returns></returns>
        [MethodAttribute] 
        QueryRes showUnSplitDetailKmInfo(string portCode, string date);
        /// <summary>
        /// 
        /// </summary>
        /// <param name="rela"></param>
        /// <returns></returns>
        [MethodAttribute] 
        QueryRes showRelaDetailKmInfo(string id);
        /// <summary>
        /// 获取该拆分映射更新对应的拆分规则
        /// </summary>
        /// <param name="rela"></param>
        /// <returns></returns>
         ////List<ErSplitRule> getSplitRulesBySplitRela(ErSplitRela rela);
        /// <summary>
         /// 获取托管科目列头
        /// </summary>
        /// <returns></returns>
        [MethodAttribute] 
        List<ListHeadInfo> getTghKmTableHeadKeys();
         /// <summary>
         /// 获取组合科目列头
         /// </summary>
         /// <returns></returns>
        [MethodAttribute] 
        List<ListHeadInfo> getPortKmTableHeadKeys();
	}
}