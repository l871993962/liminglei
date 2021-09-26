using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Interface;
using FAST.Common.Service.Pojo;

namespace YssProductInfo.Resource
{
    /// <summary>
    /// 【产品信息组件】系统框架菜单配置资源采集实现类
    ///  菜单配置资源拆分到各自的项目中，以保证项目的独立性
    /// 
    /// added by HeLiang 2017-07-04 STORY #42921 产品信息组件拆分开发
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
            resourceInfo.DllName = "YssProductInfo.dll";
            resourceInfo.ResourceSpace = "YssProductInfo.Resource.systemConfig.xml";

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
        /// 获取系统工作面板配置信息集（如果没有则返回null）。
        /// </summary>
        /// <returns>返回工作面板配置信息</returns>
        public List<ParameterConfigInfo> GetSystemParameterConfigList()
        {
            return null;
        }

        /// <summary>
        /// 获取客户端分布式程序集资源清单。
        /// STORY #49785 分布式-FAST前台框架运行时应支持多版本共存，适应业务系统使用不同版本FAST框架的要求。20181220
        /// </summary>
        /// <returns>返回客户端分布式程序集资源清单</returns>
        public DistributedConfigInfo GetDistributedConfigInfo()
        {
            DistributedConfigInfo loConfigInfo = new DistributedConfigInfo();
            loConfigInfo.AssembliesType = "PRODUCTINFO";
            loConfigInfo.AssembliesDescription = "产品管理组件";

            loConfigInfo.Items.Add(new DistributedItem("YssProductInfo.dll", "产品管理组件"));
            loConfigInfo.Items.Add(new DistributedItem("YssProductInfo.Support.dll", "产品管理服务组件"));

            return loConfigInfo;
        }
    }
}
