using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;

namespace YssVisAval.service
{
    /// <summary>
    /// 特殊关键字处理接口
    /// </summary>
    public interface IVisKeyWordParamService : IServiceBus
    {
        /// <summary>
        /// 根据keyword获取参数
        /// </summary>
        /// <param name="keyword">keyword</param>
        /// <returns>参数</returns>
        string getKeyWordParamValues(string keyword);

        /// <summary>
        /// 获得所有特殊关键字
        /// </summary>
        /// <returns>string</returns>
        string getAllKeyWord();
    }
}
