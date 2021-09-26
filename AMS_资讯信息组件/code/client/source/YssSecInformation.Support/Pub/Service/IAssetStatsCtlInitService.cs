using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Platform.Post.Service;
using FAST.Platform.Menu.Service;
using FAST.Platform.Logger.Service;
using FAST.Platform.Safe.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Bussiness.Service;
using FAST.Common.Service.Services.Base;

namespace YssSecInformation.Support.Pub.Service
{
    /// <summary>
    /// 业务常量
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    public interface IAssetStatsCtlInitService : IBusiOperService
    {
    }
}
