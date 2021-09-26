using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using YssElecReco.pojo.Er;
using FAST.Common.Service.Services.Base;

namespace YssElecReco.Service.Er
{
    /// <summary>
    /// STORY42784中国银行_深证通伺服器要求采用热备模式
    /// STORY42660【中国银行】深证通伺服器要求采用热备模式
    /// DESC: 深圳通参数设置服务类
    /// CREATED BY: wulongxing
    /// CREATED TIME: 2017-06-12
    /// </summary>
    public interface IErParaService : IServiceBus
    {
        /// <summary>
        /// 根据参数代码获取伺服器配置信息
        /// </summary>
        /// <param name="c_Para_Code">c_Para_Code</param>
        /// <returns>返回主备机伺器信息</returns>
        List<MrInfo> queryMrInfos(string c_Para_Code);
    }
}
