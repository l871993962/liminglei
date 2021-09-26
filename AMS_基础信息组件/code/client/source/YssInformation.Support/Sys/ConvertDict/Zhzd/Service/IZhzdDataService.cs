using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.DataService;
using FAST.Common.Service.Attributes;

namespace YssInformation.Support.Sys.ConvertDict.Zhzd.Service
{
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.sys.convertdict.zhzd.controller.IZhzdDataController", ServiceUrl = "")]
    public interface IZhzdDataService : IControlDataService, IKeyConvertDataService
    {


        /**
         * 根据场景类型查找转换字典，并把 源值 和 转换值 存到Map中
         * @param type 场景类型
         * @return 封装了 源值和转换值的Map<源值,转换值>
         */
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getKeyConvertMap")]
        Dictionary<string, string> getKeyConvertMap(string type);

        /**
        * 根据应用场景，源值获取转换值
        * @param srcCode 源值
        * @param sceneType 场景
        * @return
        */
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/specificDictConvert")]
        String specificDictConvert(string srcCode, string sceneType);

    }
}
