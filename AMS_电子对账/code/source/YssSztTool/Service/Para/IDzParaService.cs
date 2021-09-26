using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;


namespace YssSztTool.Service.Para
{
    /// <summary>
    /// 电子对账参数设置服务类
    /// </summary>
    [ServiceAttribute(ServiceId = ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ifa.szt.tool.para.service.controller.IDzParaController")]
    public interface IDzParaService : IServiceBus
    {
        /// <summary>
        /// STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
        /// 将密钥加密
        /// </summary>
        /// <param name="encryptStr"></param>
        /// <returns></returns>
        [MethodAttribute]
        string encryptData(string encryptStr);
        /// <summary>
        /// STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
        /// 将密钥解密
        /// </summary>
        /// <param name="encryptStr"></param>
        /// <returns></returns>
        [MethodAttribute]
        string decryptData(string encryptStr);
    }
}
