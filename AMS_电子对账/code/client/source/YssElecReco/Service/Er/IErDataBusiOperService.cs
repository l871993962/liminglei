using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Bussiness.Service;

namespace YssElecReco.Service.Er
{
    /// <summary>
    /// IErDataBusiOperService
    /// </summary>
    public interface IErDataBusiOperService : IBusiOperService
    {
        /// <summary>
        /// 产生电子对账数据
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>string</returns>
        string genErDataOper(Dictionary<string, string> paraDict);
    }
}
