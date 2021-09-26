using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using YssSztTool.Pojo.Para;
using FAST.Common.Service.Attributes;

namespace YssSztTool.Service.Para
{
    [ServiceAttribute(ServiceId = "osgi-elecreco", ServiceController = "com.yss.uco.elecreco.support.controller.IErTaskServiceController")]
    public interface IErTaskService : IServiceBus  
	{
        [MethodAttribute]
        ErTask getErTaskByCode(string taskCode);

        [MethodAttribute]
        string updateTaskByCode(ErTask task);
	}
}