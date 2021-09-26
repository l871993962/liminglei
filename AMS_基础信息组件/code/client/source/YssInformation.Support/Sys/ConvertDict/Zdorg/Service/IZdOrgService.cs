using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;


namespace YssInformation.Support.Sys.ConvertDict.Zdorg.Service
{
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.sys.convertdict.zdorg.controller.IZdOrgController", ServiceUrl = "")]
    public interface IZdOrgService : IServiceBus
    {
        /// <summary>
        /// 获取树型数据结构
        /// </summary>
        /// <param name="paraDict">查询条件参数对象</param>
        /// <returns>查询结果集</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getTreeViewData")]
        QueryRes getTreeViewData(Dictionary<string, string> paraDict);

        /// <summary>
        /// Root
        /// </summary>
        /// <returns>Root</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/checkHasRootNode")]
        string checkHasRootNode();

        /// <summary>
        /// 此目录下是否有数据
        /// </summary>
        /// <returns>有：true ;没有：false</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/isHaveData")]
        bool isHaveData();
    }
}
