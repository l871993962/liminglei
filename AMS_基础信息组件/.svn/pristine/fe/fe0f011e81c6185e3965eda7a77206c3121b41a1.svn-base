using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Interface;
using FAST.Common.Service.Pojo;

namespace YssSyncData.Resource
{
    /// <summary>
    /// 业务系统系统框架菜单配置资源采集实现类
    /// </summary>
    public class BusinessSystemMenuConfigResource : IMenuConfigResource
    {
        /// <summary>
        /// 获取功能菜单配置资源信息（如果没有则返回null）。
        /// </summary>
        /// <returns>返回配置资源信息</returns>
        public MenuConfigResourceInfo GetResourceInfo()
        {
            MenuConfigResourceInfo resourceInfo = new MenuConfigResourceInfo();
            resourceInfo.DllName = "YssSyncData.dll";
            resourceInfo.ResourceSpace = "YssSyncData.Resource.systemConfig.xml";

            return resourceInfo;
        }

        /// <summary>
        /// 获取系统工作面板配置信息集（如果没有则返回null）。
        /// </summary>
        /// <returns>返回工作面板配置信息</returns>
        public List<WorkSpaceConfigInfo> GetWorkSpaceConfigList()
        {
            return null;
        }

        #region IMenuConfigResource 成员


        public List<ParameterConfigInfo> GetSystemParameterConfigList()
        {
            List<ParameterConfigInfo> loParamConfigLst = new List<ParameterConfigInfo>();

            ParameterConfigInfo loExportConfig = new ParameterConfigInfo();
            loExportConfig.ConfigType = ParameterConfigType.Business;
            loExportConfig.NeedCheckAuthority = false;
            loExportConfig.ClassName = "YssSyncData.Form.Base.Frm_SyncData_S";
            loExportConfig.DllName = "YssSyncData.dll";
            loExportConfig.NodeName = "SyncFuncode";
            loExportConfig.NodeTitle = "同步模块配置";
            loParamConfigLst.Add(loExportConfig);

            return loParamConfigLst;
        }

        #endregion
    }
}
