using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using YssSyncData.Pojo.Base;

namespace YssSyncData.Service.Base
{
    /// <summary>
    /// 数据同步服务
    /// </summary>
    public interface ISyncDataService : IServiceBus
    {
        /// <summary>
        /// 获取系统应用代码
        /// </summary>
        /// <returns>String</returns>
        string getSystemCode();

        /// <summary>
        ///  前台触发：数据同步
        /// </summary>
        /// <param name="syncDatas">syncDatas</param>
        /// <returns>String</returns>
        string syncData(List<SyncData> syncDatas);
	
        /// <summary>
        /// 前台触发：忽略消息（只有已接收状态下的消息才可忽略）
        /// </summary>
        /// <param name="syncDatas">syncDatas</param>
        /// <returns>String</returns>
        string ignoreMessages(List<SyncData> syncDatas);
        /// <summary>
        /// 保存数据同步模块设置
        /// </summary>
        /// <param name="syncModule">syncModule</param>
        /// <returns>String</returns>
        string saveSyncModule(List<SyncModule> syncModule);

        /// <summary>
        /// 获取同步模块配置信息
        /// </summary>
        /// <returns>配置模块</returns>
        List<SyncModule> queryAllFuncodeCfg();

    }  
}


