﻿using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using YssSecInformation.Support.PlateSet.plate.Pojo;
using FAST.Common.Service.Attributes;


namespace YssSecInformation.Support.plate.Service
{
    /// <summary>
    /// 版块信息数据服务接口
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.sec.information.support.modules.plateset.plate.controller.IPlate_AController", ServiceUrl = "")]
    public interface IPlate_AService : IServiceBus
    {
        /// <summary>
        /// 条件查询
        /// </summary>
        /// <param name="quyCon">quyCon</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondition")]
        QueryRes selectByCondition(string quyCon);

        /// <summary>
        /// 获取树型数据结构
        /// </summary>
        /// <param name="paraDict">查询条件参数对象</param>
        /// <returns>查询结果集</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getTreeViewData")]
        QueryRes getTreeViewData(Dictionary<string, string> paraDict);

        /// <summary>
        /// 板块信息
        /// </summary>
        /// <returns>板块信息</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPlateCategory")]
        List<Plate> getPlateCategory();

        /// <summary>
        /// 查询是否有子节点
        /// </summary>
        /// <param name="data">类型代码组</param>
        /// <returns>是否</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getSUBData")]
        string getSUBData(string data);

        /// <summary>
        /// Author : ZhouShuHang
        /// Date   : 2017-05-19
        /// Status : Add
        /// Comment: BUG #160288 点击资本币种下拉框报错
        /// 加载版块信息设置中版块代码为SAC的数据
        /// </summary>
        /// <returns>行业类别(板块分类)对象集合</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getSacPlateCategory")]
        List<Plate> getSacPlateCategory();
    }
}




