using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using YssSztTool.Pojo.Para;
using FAST.Common.Service.Attributes;

namespace YssSztTool.Service.Para
{
    /// <summary>
    /// STORY42784中国银行_深证通伺服器要求采用热备模式
    /// STORY42660【中国银行】深证通伺服器要求采用热备模式
    /// DESC: 深圳通参数设置服务类
    /// CREATED BY: wulongxing
    /// CREATED TIME: 2017-06-12
    /// </summary>
    [ServiceAttribute(ServiceId = ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ifa.szt.tool.para.service.controller.IErParaController")]
    public interface IErParaService : IServiceBus
    {
        /**
	 * @Description: 根据参数代码获取伺服器配置信息
	 * @param c_Para_Code 参数代码
	 * @return  返回主备机伺服器信息
	 * @author wulongxing 
	 * @date 2017年6月20日 下午4:12:59
	 */
        [MethodAttribute]
        List<MrInfo> queryMrInfos(String c_Para_Code);
    }
}
