using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using YssElecReco.Pojo.Er;
using FAST.Common.Service.Attributes;
using YssElecReco.Context;

namespace YssElecReco.Service.Er
{
    /// <summary>
    /// 浏览电子对账信息service
    /// chenyoulong 20121226
    /// v1.0.0.7
    /// </summary>
    [ServiceAttribute(ServiceId = ElecrecoServiceIdConstant.OSGI_ELECRECO, ServiceController = "com.yss.uco.elecreco.support.controller.IErBbInfoServiceController")]
    public interface IErBbInfoService : IServiceBus
    {
        /// <summary>
        /// 修改电子对账信息审核状态、发送状态
        /// </summary>
        /// <param name="bbInfoList">bbInfoList</param>
        /// <param name="status">status</param>
        /// <returns>string</returns>
        [MethodAttribute]
        string updateBbInfo(List<ErBbInfo> bbInfoList, string status);

        /// <summary>
        /// 修改电子对账信息审核状态、发送状态
        /// </summary>
        /// <param name="bbInfoList">bbInfoList</param>
        /// <returns>string</returns>
        [MethodAttribute]
        string deleteBbInfo(List<ErBbInfo> bbInfoList);

        /// <summary>
        /// 根据业务主键
        /// </summary>
        /// <param name="xErBbInfo">xErBbInfo</param>
        /// <returns>xml数据</returns>
        [MethodAttribute]
        string getXmlFile(ErBbInfo xErBbInfo);

        /// <summary>
        /// 发送数据
        /// </summary>
        ///<param name="bbInfoList">发送数据</param>
        /// <returns>结果</returns>
        [MethodAttribute]
        string sendBbInfo(List<ErBbInfo> bbInfoList);


        /// <summary>
        /// 发送数据
        /// </summary>
        ///<param name="bbInfoList">发送数据</param>
        /// <returns>string</returns>
        [MethodAttribute]
        string witeBbInfo(List<ErBbInfo> bbInfoList);

        /// <summary>
        /// 根据物理主键获得对象
        /// </summary>
        /// <param name="id">id</param>
        /// <returns>ErBbInfo</returns>
        [MethodAttribute]
        ErBbInfo getBbInfoById(string id);

        /// <summary>
        /// 重启对账管理
        /// </summary>
        /// <returns>1</returns>
        [MethodAttribute]
        string reStartDzMgr();

        /// <summary>
        /// STORY31217【招商财富】电子对账管理已反馈新增一个人工可以修改处理状态的功能
        /// 修改数据为对账一致
        /// </summary>
        ///<param name="bbInfoList">发送数据</param>
        /// <returns>string</returns>
        [MethodAttribute]
        string acceptBbInfo(List<ErBbInfo> bbInfoList);

        /// <summary>
        /// STORY54447反馈不一致结果人工对账一致处理需填写原因与说明信息 
        /// 新增其他对账中的对账一致方法
        /// </summary>
        ///<param name="bbInfoList">发送数据</param>
        /// <returns>string</returns>
        [MethodAttribute]
        string acceptBbInfoForQTDZ(List<ErBbInfo> bbInfoList);


        
        /// <summary>
        /// 操作不对账组合
        /// </summary>
        /// <param name="bbInfoList">bbInfoList</param>
        /// <param name="operType">operType</param>
        /// <returns>string</returns>
        [MethodAttribute]
        string UnPortOper(List<ErBbInfo> bbInfoList, string operType);
        /// <summary>
        /// STORY #65389电子对账前台与估值核算解耦
        /// </summary>
        /// <param name="pojos"></param>
        /// <param name="execOperCode"></param>
        /// <param name="isCheckExe"></param>
        /// <returns></returns>
        [MethodAttribute]
        string lockEconfirm(List<ErBbInfo> bbInfoList, string execOperCode, string isCheckExe, string executeType);


        /// <summary>
        /// wSTORY #77811 要可以撤销手工设置的对帐一致
        /// 修改或删除对账一致数据
        /// </summary>
        ///<param name="bbInfoList">发送数据</param>
        /// <returns>string</returns>
        [MethodAttribute]
        string unAcceptClick(List<ErBbInfo> bbInfoList);
        /// <summary>
        /// wSTORY #77811 要可以撤销手工设置的对帐一致
        /// 查询差异行数
        /// </summary>
        /// <param name="bbInfoList"></param>
        /// <returns></returns>
        [MethodAttribute]
        string queryNumberOfRows(ErBbInfo erBbInfo);

    }
}
