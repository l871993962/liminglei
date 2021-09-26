using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Interface;

namespace YssSztTool.Resource
{
    /// <summary>
    /// 业务系统系统框架菜单配置资源采集实现类
    /// </summary>
    public class BusinessSystemMenuConfigResource : IMenuConfigResource, IDistributedConfig
    {
        /// <summary>
        /// 获取功能菜单配置资源信息（如果没有则返回null）。
        /// </summary>
        /// <returns>返回配置资源信息</returns>
        public MenuConfigResourceInfo GetResourceInfo()
        {
            MenuConfigResourceInfo resourceInfo = new MenuConfigResourceInfo();
            resourceInfo.DllName = "YssSztTool.dll";
            resourceInfo.ResourceSpace = "YssSztTool.Resource.systemConfig.xml";

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

        /// <summary>
        /// 获取系统参数配置组件信息集（如果没有则返回null）。
        /// STORY #43719 新增通用设置功能。
        /// </summary>
        /// <returns>返回系统参数配置组件信息集</returns>
        public List<ParameterConfigInfo> GetSystemParameterConfigList()
        {
            return null;
        }

        #region IDistributedConfig 成员

        public DistributedConfigInfo GetDistributedConfigInfo()
        {
            DistributedConfigInfo loConfigInfo = new DistributedConfigInfo();
            loConfigInfo.AssembliesType = "YssSztTool";
            loConfigInfo.AssembliesDescription = "深证通Tool";

            loConfigInfo.Items.Add(new DistributedItem("YssSztTool.dll", "深证通Tool组件"));

            return loConfigInfo;
        }

        #endregion
    }
}
