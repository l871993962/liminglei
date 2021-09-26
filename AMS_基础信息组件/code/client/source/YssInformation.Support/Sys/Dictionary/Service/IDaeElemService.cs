using System.Collections.Generic;
using FAST.Common.Service.DataService.Base;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.DataService;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Attributes;

////namespace YssBaseCls.Service
namespace YssInformation.Support.Sys.Dictionary.Service
{
    /// <summary>
    /// ����Ԫ�ط���ӿ�
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.sys.daeelem.controller.IDaeElemController", ServiceUrl = "")]
    public interface IDaeElemService : IServiceBus
    {
        /// <summary>
        /// ������ѯ ������ʾ����Ԫ��
        /// </summary>
        /// <param name="paraMap">paraMap</param>
        /// <returns>��ѯ�������</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDaeCodesByCondition")]
        List<string> getDaeCodesByCondition(Dictionary<string, string> paraMap);
        
        /// <summary>
        /// ����֤ȯƷ��code�ж��Ƿ�������û�� �Ӷ��ó������Ƿ�����ϸ By Jinghehe 2013-12-20
        /// </summary>
        /// <param name="code">code</param>
        /// <returns>string</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/isDetailByCode")]
        string isDetailByCode(string code);
    }

}