﻿using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Pojo.Base;
using System.Collections.Generic;
using System.Collections;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Attributes;



namespace YssProductInfo.Support.Ab.Port.Service
{
    /// <summary>
    /// 组合基本参数数据服务接口
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.product.information.support.modules.ab.port.controller.IPortController", ServiceUrl = "")]
    public interface IPortService : IServiceBus
    {
        /// <summary>
        /// 条件查询
        /// </summary>
        /// <param name="quyCon">quyCon</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondition")]
        QueryRes selectByCondition(string quyCon);


        /// <summary>
        /// 获取组合关联方案新增组合
        /// </summary>
        /// <param name="paraMap">paraMap</param>
        /// <returns>QueryRes</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPlanRelaPortAdd")]
        QueryRes getPlanRelaPortAdd(Dictionary<string, string> paraMap);


        /// <summary>
        /// 获取组合关联方案浏览组合
        /// </summary>
        /// <param name="paraMap">paraMap</param>
        /// <returns>QueryRes</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPlanRelaPortBrow")]
        QueryRes getPlanRelaPortBrow(Dictionary<string, string> paraMap);

        /// <summary>
        /// 特殊结果查询
        /// 使用到该接口的接口:IAssertTreeService
        /// </summary>
        /// <param name="conds">查询条件对象</param>
        /// <param name="headKeys">自定义列头</param>
        /// <param name="queryType">查询类型</param>
        /// <param name="page">分页信息</param>
        /// <param name="menuId">菜单代码</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getListViewData")]
        QueryRes getListViewData(string conds, string headKeys, string queryType, string page, string menuId);

        /// <summary>
        /// 参数设置界面查询可用组合列表
        /// </summary>
        /// <param name="paraDict">参数集合</param>
        /// <returns>可用组合</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getParamSetPortList")]
        List<FAST.Common.Service.Pojo.Port> getParamSetPortList(Dictionary<string, string> paraDict);

        /// <summary>
        /// 获取 参数 组合
        /// </summary>
        /// <param name="paraMap">paraMap</param>
        /// <returns>QueryRes</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDspPort")]
        QueryRes getDspPort(Dictionary<string, string> paraMap);


        /// <summary>
        /// 查询单条记录,清算综合参数SET界面浏览时
        /// </summary>
        /// <param name="paraDict">单条记录的主键:组合代码,参数代码,开始时间,结束时间</param>
        /// <returns>查询结果</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/queryDataByBrow")]
        QueryRes queryDataByBrow(Dictionary<string, string> paraDict);

        /// <summary>
        /// 获取树型数据结构
        /// 创建人：tangshifeng 日期：2013-04-10
        /// </summary>
        /// <param name="paraDict">查询条件参数对象</param>
        /// <returns>查询结果集</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getTreeViewData")]
        QueryRes getTreeViewData(Dictionary<string, string> paraDict);

        /// <summary>
        /// BUG #378219 【深国投信托】【0611.0729】部分界面权限优化
        /// 获取树型数据结构
        /// </summary>
        /// <param name="isDataRight">isDataRight</param>
        /// <param name="paraDict">paraDict</param>
        /// <returns>查询结果集</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getTreeViewDataRight")]
        QueryRes getTreeViewDataRight(string isDataRight, Dictionary<string, string> paraDict);

        /// <summary>
        /// 单元层投资组合数据（列表结构）
        /// 创建人：tangshifeng 日期：2013-04-17
        /// </summary>
        /// <param name="paraDict">查询条件参数对象</param>
        /// <returns>结果集</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getUnitPortData")]
        QueryRes getUnitPortData(Dictionary<string, string> paraDict);

        /// <summary>
        /// 获取树型数据结构
        /// </summary>
        /// <param name="paraMap">paraMap</param>
        /// <returns>查询结果集</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAssetTreeView")]
        QueryRes getAssetTreeView(Dictionary<string, string> paraMap);

        /// <summary>
        /// 到期确认
        /// </summary>
        /// <param name="lstPort">组合列表</param>
        /// <returns>执行状态</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/operDQQR")]
        string operDQQR(ArrayList lstPort);

        /// <summary>
        /// 到期取消
        /// </summary>
        /// <param name="lstPort">组合列表</param>
        /// <returns>执行状态</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/operDQQX")]
        string operDQQX(ArrayList lstPort);

        /// <summary>
        /// 清算确认
        /// </summary>
        /// <param name="lstPort">组合列表</param>
        /// <returns>执行状态</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/operQSQR")]
        string operQSQR(ArrayList lstPort);

        /// <summary>
        /// 清算取消
        /// </summary>
        /// <param name="lstPort">组合列表</param>
        /// <returns>执行状态</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/operQSQX")]
        string operQSQX(ArrayList lstPort);

        /// <summary>
        /// 用于组合基本参数展示查询
        /// 不展示新增定向基本信息时生成的单元层组合
        /// </summary>
        /// <param name="paraMap">参数</param>
        /// <param name="page">page</param>
        /// <returns>res</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/queryWithoutUnit")]
        QueryRes queryWithoutUnit([RequestBodyAttribute(ParameterName = "paraMap")] Dictionary<string, object> paraMap, [RequestBodyAttribute(ParameterName = "page")] PageInation page);

        /// <summary>
        /// 获取树型数据结构有NodeCode节点
        /// 创建人：chenwenhai 日期：2014-01-24
        /// </summary>
        /// <param name="paraDict">查询条件参数对象</param>
        /// <returns>查询结果集</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPortTreeWithNode")]
        QueryRes getPortTreeWithNode(Dictionary<string, string> paraDict);

        /// <summary>
        /// 获取和目标组合资产代码重复的组合列表
        /// liuxiang 2015-7-16 BUG #115824 [紧急][招商证券]资产代码修改的问题
        /// </summary>
        /// <param name="portCode">组合代码</param>
        /// <param name="assCode">资产代码</param>
        /// <returns>组合列表</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getTheSameAssCodeList")]
        List<FAST.Common.Service.Pojo.Port> getTheSameAssCodeList(string portCode, string assCode);

        /// <summary>
        /// 通过参数代码查询综合参数值
        /// </summary>
        /// <param name="paramCode">paramCode</param>
        /// <returns>paramValues</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/queryByParaCode")]
        string queryByParaCode(string paramCode);

        /// <summary>
        /// 根据业务类型查找没有关联此业务的组合
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>res</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getBusinessRangePortAdd")]
        QueryRes getBusinessRangePortAdd(Dictionary<string, string> paraDict);

        /// <summary>
        /// STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
        /// 根据业务类型查找没有关联此业务的组合
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>res</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAutomaticSetPortAdd")]
        QueryRes getAutomaticSetPortAdd(Dictionary<string, string> paraDict);

        /// <summary>
        /// 根据组合代码查找组合
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>res</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getBusinessRangePortBrow")]
        QueryRes getBusinessRangePortBrow(Dictionary<string, string> paraDict);

        /// <summary>
        /// 删除产品关联信息
        /// </summary>
        /// <param name="portCodes">组合列表</param>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/delPortRela")]
        void delPortRela(string[] portCodes);

        /// <summary>
        /// 根据组合和币种查询账户
        /// </summary>
        /// <param name="portCode">portCode</param>
        /// <param name="dcCode">dcCode</param>
        /// <returns>string</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getCacodeByAccountType")]
        string getCacodeByAccountType(string portCode, string dcCode);

        /// <summary>
        /// 根据组合和币种查询账户
        /// </summary>
        /// <param name="openName">openName</param>
        /// <param name="openNo">openNo</param>
        /// <param name="openAddr">openAddr</param>
        /// <param name="dcCode">dcCode</param>
        /// <returns>string</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getCacodeByAccountType1")]
        string getCacodeByAccountType1(string openName, string openNo, string openAddr, string dcCode);
    }
}




