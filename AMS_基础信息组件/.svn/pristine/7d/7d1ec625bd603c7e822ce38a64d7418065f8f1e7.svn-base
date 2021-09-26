using FAST.Common.Service.Pojo;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Pojo.Base;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Attributes;

namespace YssInformation.Support.Bi.Holidays.Service
{
    /// <summary>
    /// 节假日群信息数据服务接口
    /// </summary>
    [NewServiceAttribute(ServiceType.NEW_SVC)]
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.bi.hday.controller.IHdayController", ServiceUrl = "")]
    public interface IHdayService : IServiceBus
    {
        /// <summary>
        /// 条件查询
        /// </summary>
        /// <param name="quyCon">quyCon</param>
        /// <returns>查询结果对象</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/selectByCondition")]
        QueryRes selectByCondition(string quyCon);

        /// <summary>
        /// 根据节假日代码获得年份
        /// </summary>
        /// <param name="code">节假日代码</param>
        /// <returns>年份</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAllYear")]
        List<string> getAllYear(string code);

        /// <summary>
        /// 获得节假日
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>list</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAllHoiday")]
        List<string> getAllHoiday(Dictionary<string, string> paraDict);

        /// <summary>
        /// 判断是否已经有审核数据了
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>paraDict</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getSameHoiday")]
        string getSameHoiday(Dictionary<string, string> paraDict);



        /// <summary>
        /// 如果是节假日就顺延到下一个工作日
        /// 如果date是工作日则返回date,如果date是节假日则返回下一个工作日。add by chenwenhai 20140514
        /// </summary>
        /// <param name="specifiedDate">specifiedDate</param>
        /// <param name="offset">offset</param>
        /// <param name="port">mktCode</param>
        /// <returns>date</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getWorkday")]
        string getWorkday(string specifiedDate, string offset, string mktCode);

        /// <summary>
        /// 根据节假日获取日期
        /// </summary>
        /// <param name="date">参照日期</param>
        /// <param name="offset">间隔</param>
        /// <param name="hdayCode">节假日代码</param>
        /// <param name="hdayType">“W”工作日；“D”自然日；“DW”自然日递延至工作日</param>
        /// <returns>日期</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDay")]
        string getDay(string date, string offset, string hdayCode, string hdayType);

        /// <summary>
        /// 获取已审核节假日代码/名称
        /// </summary>
        /// <returns>res</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getHdayMap")]
        Dictionary<string, string> getHdayMap();
    }
}




