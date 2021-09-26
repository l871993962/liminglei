using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Pojo.Base;
using YssInformation.Support.Bi.Account.Pojo;
using System.Collections;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Attributes;

namespace YssInformation.Support.Bi.Account.Service
{
    /// <summary>
    /// 资金账户信息service
    /// </summary>
    [ServiceAttribute(ServiceId = YssInformation.Support.common.ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ams.base.information.support.bi.account.controller.IFundAccController", ServiceUrl = "")]
    [NewServiceAttribute(ServiceType.NEW_SVC)] 
    public interface IFundAccService : IServiceBus
    {
        /// <summary>
        /// 更新账户使用标记为已使用
        /// </summary>
        /// <param name="accountNo">账户代码</param>
        /// <returns>"fail"-失败 "success"-成功</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/updateFundAccUnifyPay")]
        string updateFundAccUnifyPay(string accountNo);

        /// <summary>
        /// 根据账户类型获取账户信息
        /// </summary>
        /// <param name="types">账户类型</param>
        /// <returns>账户信息</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByAccTypes")]
        List<BasePojo> getDataListByAccTypes(string[] types);

        /// <summary>
        /// ss
        /// </summary>
        /// <param name="types">d</param>
        /// <returns>s</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByAccTypes2")]
        List<BasePojo> getDataListByAccTypes2(string[] types);

        /// <summary>
        /// 判断是否存在指定账户类型的账户
        /// </summary>
        /// <param name="type">账户类型</param>
        /// <returns>False-不存在 Success-存在</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/isAccOfAccTypeExit")]
        string isAccOfAccTypeExit(string type);

        /// <summary>
        /// 20160608 add by zhaoguanchao STORY #31665 清算指令支持手工录入
        /// 查询指定账户类型及开户地址的账户
        /// </summary>
        /// <param name="types">账户类型</param>
        /// <param name="accAddr">开户地址</param>
        /// <returns>账户信息</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getFundAccByType")]
        List<BasePojo> getFundAccByType([RequestBodyAttribute(ParameterName = "types")] string[] types, [RequestBodyAttribute(ParameterName = "accAddr")] string accAddr);

        /// <summary>
        /// STORY #35492 南方基金-产品账户信息中大额支付号与关联机构联动
        /// 根据id更新机构信息的大额支付号和联行行号
        /// </summary>
        /// <param name="cPAYCODE">大额支付号</param>
        /// <param name="cBANKCODE">联行号</param>
        /// <param name="id">c_iden</param>
        /// <returns>Fail-失败 Success-成功</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/updateOrgInfo")]
        string updateOrgInfo(string cPAYCODE, string cBANKCODE, string id);

        /// <summary>
        /// STORY #42242 歌斐支付平台-能在同一个界面查询所有账户的余额以及发生额
        /// 通过账户查
        /// </summary>
        /// <param name="accNo">账户</param>
        /// <returns>id</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getFundAccByAccNo")]
        FundAcc getFundAccByAccNo(string accNo);

        /// <summary>
        /// STORY #42242 歌斐支付平台-能在同一个界面查询所有账户的余额以及发生额
        /// 通过id查
        /// </summary>
        /// <param name="id">id</param>
        /// <returns>FundAcc</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getFundAccById")]
        FundAcc getFundAccById(string id);

        /// <summary>
        /// STORY #41401 产品信息-产品账户设置，批量关联账户 
        /// </summary>
        /// <param name="pojo">账户信息</param>
        /// <returns>该条账户信息的iden</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getIdAfterSave")]
        string getIdAfterSave(FundAcc pojo);

        /// <summary>
        /// STORY #41401 产品信息-产品账户设置，批量关联账户 
        /// </summary>
        /// <param name="portCodes">组合</param>
        /// <param name="fundAccID">id</param>
        /// <param name="accountType">账户类型</param>
        /// <returns>保存结果</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/savePortFundRela")]
        bool savePortFundRela(string portCodes, string fundAccID, string accountType);

        /// <summary>
        /// STORY #89090 变更托管人及交易席位，可以维护旧流水的截止日期以及新流水的启用日期
        /// </summary>
        /// <param name="paraMap">paraMap</param>
        /// <returns>保存结果</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/savePortFundRelaWithDate")]
        bool savePortFundRelaWithDate(Dictionary<string, string> paraMap);

        /// <summary>
        /// STORY #41401 产品信息-产品账户设置，批量关联账户
        /// </summary>
        /// <param name="relaCode">关联code</param>
        /// <param name="port">组合代码</param>
        /// <returns>删除结果</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/deletePortFundRela")]
        bool deletePortFundRela(string relaCode, string port);

        /// <summary>
        /// STORY #41401 产品信息-产品账户设置，批量关联账户
        /// </summary>
        /// <param name="relaCode">关联code</param>
        /// <param name="port">组合代码</param>
        /// <returns>删除结果</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/deletePortsFundRela")]
        bool deletePortsFundRela(string relaCode, string port);

        /// <summary>
        /// STORY #41401 产品信息-产品账户设置，批量关联账户
        /// </summary>
        /// <param name="id">账户信息id</param>
        /// <returns>账户关联表里的组合</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getPortsByRelaCode1")]
        string getPortsByRelaCode(string id);

        /// <summary>
        /// STORY #41401 产品信息-产品账户设置，批量关联账户
        /// </summary>
        /// <returns>""</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/deleteUnusePortRela")]
        bool deleteUnusePortRela();

        /// <summary>
        /// 根据组合代码获取数据
        /// </summary>
        /// <param name="portCode">portCode</param>
        /// <returns>资金账号信息</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByPort")]
        List<FundAcc> getDataListByPort(string portCode);

        /// <summary>
        /// 插入账户信息，以及机构关联关系
        /// </summary>
        /// <param name="orgCode">orgCode</param>
        /// <param name="list">list</param>
        /// <returns>string</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/insert")]
        string insert([RequestBodyAttribute(ParameterName = "orgCode")] string orgCode, [RequestBodyAttribute(ParameterName = "pojoList")] List<FundAcc> list);

        /// <summary>
        /// ss
        /// </summary>
        /// <param name="portCode">s</param>
        /// <param name="dcCode">s</param>
        /// <returns>s</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getCADataListByPortCode")]
        List<CashAcc> getCADataListByPortCode(string portCode, string dcCode);

        /// <summary>
        /// 方法迁移到账户service  by zhouning 20171107
        /// STORY 35130 招商基金-删除账户信息时如果此账户已被用则需弹出提示框
        /// 查询自动划款指令设置是否使用某账号
        /// </summary>
        /// <param name="c_open_addr">开户地址</param>
        /// <param name="c_open_acc_no">账号</param>
        /// <param name="c_open_acc_name">开户名称</param>
        /// <returns>"true"-存在 "false"-不存在</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/queryZfbyPort")]
        string queryZfbyPort(string c_open_addr, string c_open_acc_no, string c_open_acc_name);


        /// <summary>
        /// add by ouyanghuiqiang@ysstech.com 20171115
        /// STORY #47132 清算指令A区账户体系调整 
        /// STORY #47517 直连发送平台增加基金代码、基金名称的筛选条件 
        /// 获取TAQS账户体系下的A区账户信息
        /// </summary>
        /// <returns>账户信息</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAccListForTAQS")]
        List<BasePojo> getAccListForTAQS();

        /// <summary>
        /// add by ouyanghuiqiang@ysstech.com 20171115
        /// STORY #47132 清算指令A区账户体系调整 
        /// STORY #47517 直连发送平台增加基金代码、基金名称的筛选条件 
        /// 获取TAQS账户体系下的账户类型词典的翻译信息
        /// </summary>
        /// <returns>账户类型词典的翻译</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAccTypeDictName")]
        List<string> getAccTypeDictName();

        /// <summary>
        /// add by ouyanghuiqiang@ysstech.com 20171115
        /// STORY #47132 清算指令A区账户体系调整 
        /// STORY #47517 直连发送平台增加基金代码、基金名称的筛选条件 
        /// 根据勾选的A区TAQS体系下的账户代码列表和界面选择的产品列表来获取合适的卡号列表
        /// </summary>
        /// <param name="paraMap">paraMap</param>
        /// <returns>List</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getOpenAccNoListByActAndPort")]
        List<string> getOpenAccNoListByActAndPort(Dictionary<string, string> paraMap);

        /// <summary>
        /// add by zhangfengjun@ysstech.com 20171115
        /// 根据勾选的ID进行删除关联关系
        /// </summary>
        /// <param name="realId">realId</param>
        /// <returns>Boolean</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/deleteByRealId")]
        bool deleteByRealId(string[] realId);

        /// <summary>
        /// add by zhangfengjun@ysstech.com 20180112
        /// BUG #187667 支付参数“账户是否二次录入”参数值的维护需优化
        /// </summary>
        /// <returns>string</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/readProperty")]
        string readProperty();

        /// <summary>
        /// s
        /// </summary>
        /// <param name="checkInfo">s</param>
        /// <returns>s</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/updateProperty")]
        string updateProperty(string checkInfo);

        /// <summary>
        /// 缓存账户集合
        /// </summary>
        /// <returns>List</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getFundAccListByCache")]
        List<FundAcc> getFundAccListByCache();

        /// <summary>
        /// 是否显示A区，当返回"false"时才隐藏A区，否则显示
        /// BUG #194507 账户信息设置屏蔽A区设置
        /// </summary>
        /// <returns>"false" or other value</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/showAreaA")]
        string showAreaA();

        /// <summary>
        /// 获取账户类型以及所属人对应资质
        /// </summary>
        /// <param name="paraMap">paraMap</param>
        /// <returns>Dictionary</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAccountTypeOrgAptitudeChannelByMap")]
        Dictionary<string, string> getAccountTypeOrgAptitudeChannelByMap(Dictionary<string, string> paraMap);


        /// <summary>
        /// 账户字符串中，去除中间的款项类型
        /// </summary>
        /// <param name="search">search</param>
        /// <returns>search</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAccByRmHkType")]
        string getAccByRmHkType(string search);

        /// <summary>
        /// 获取账户和开户地址的树形机构
        /// </summary>
        /// <returns>List</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getFundAccNoAndAddrList")]
        List<BasePojo> getFundAccNoAndAddrList();

        /// <summary>
        /// 加载账户类型为托管户、副托管户的账户且关联的组合的【组合级别】为‘单元层’的账户
        /// </summary>
        /// <param name="paraMap">paraMap</param>
        /// <returns>ArrayList</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAllFundAccByType")]
        ArrayList getAllFundAccByType(Dictionary<string, string> paraMap);

        /// <summary>
        /// s
        /// ss
        /// </summary>
        /// <param name="list">list</param>
        /// <returns>s</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getFundAccByNoAddrName")]
        Dictionary<string, string> getFundAccByNoAddrName(List<FundAcc> list);

        /// <summary>
        /// 查询账户属性结构的账户数据
        /// </summary>
        /// <param name="paraMap">paraMap</param>
        /// <returns>QueryRes</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAccInAccountTreeView")]
        QueryRes getAccInAccountTreeView(Dictionary<string, string> paraMap);

        /// <summary>
        /// getFundAccByInfo
        /// </summary>
        /// <param name="paraMap">paraMap</param>
        /// <returns>FundAcc</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getFundAccByInfo1")]
        FundAcc getFundAccByInfo(Dictionary<string, object> paraMap);

        /// <summary>
        /// getFundAccByInfo
        /// </summary>
        /// <param name="paraMap">paraMap</param>
        /// <returns>FundAcc</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getFundAccByInfo")]
        FundAcc getFundAccByInfo(Dictionary<string, string> paraMap);

        /// <summary>
        /// 保存组合关联信息
        /// </summary>
        /// <param name="pojo">pojo</param>
        /// <param name="portCode">portCode</param>
        /// <returns>string</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/saveRelaInfo")]
        string saveRelaInfo([RequestBodyAttribute(ParameterName = "pojo")] FundAcc pojo, [RequestBodyAttribute(ParameterName = "portCode")] string portCode);

        /// <summary>
        /// 根据账户信息查询实体对象，用于返回物理主键
        /// </summary>
        /// <param name="pojo">pojo</param>
        /// <returns>fundAcc</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getFundAccByAcc")]
        FundAcc getFundAccByAcc(FundAcc pojo);

        /// <summary>
        /// 根据账户信息修改大额支付号
        /// </summary>
        /// <param name="dict">map</param>
        /// <returns>字符串</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/updatePaycodeByAcc")]
        string updatePaycodeByAcc(Dictionary<string, string> dict);

        /// <summary>
        /// 拼接路径
        /// </summary>
        /// <param name="tempFileName">map</param>
        /// <returns>string</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/fileDown")]
        string fileDown(string tempFileName);

        /// <summary>
        /// 根据组合代码，账户id获取关联信息中的开始时间，结束时间
        /// </summary>
        /// <param name="portCode">portCode</param>
        /// <param name="fundAccId">fundAccId</param>
        /// <returns>string</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getTimeByRelaPort")]
        string getTimeByRelaPort(string portCode, string fundAccId);

        /// <summary>
        /// 根据组合代码，账户id更新关联信息中的开始时间，结束时间
        /// </summary>
        /// <param name="paraMap">paraMap</param>
        /// <returns>string</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/updateTimeByRelaPort")]
        string updateTimeByRelaPort(Dictionary<string, string> paraMap);

        /// <summary>
        /// 批量删除
        /// </summary>
        /// <param name="serverFilePathList">filePathList</param>
        /// <returns>string</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/delFile")]
        string delFile(List<string> serverFilePathList);

        /// <summary>
        /// 批量下载
        /// </summary>
        /// <param name="fileServicePathList">fileList</param>
        /// <returns>Dictionary</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/filesDownLoad")]
        Dictionary<string, string> filesDownLoad(List<string> fileServicePathList);

        /// <summary>
        ///   STORY #72919 【国泰基金】支付产品账户信息界面新增上传附件功能，增加每个账户可以上传附件，并且支持附件下载 
        /// </summary>
        /// <param name="delFileList">delFileList</param>
        /// <param name="addFileList">addFileList</param>
        /// <returns>string</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/updateFileMsg")]
        string updateFileMsg([RequestBodyAttribute(ParameterName = "delFileList")] List<DataEnclosure> delFileList, [RequestBodyAttribute(ParameterName = "addFileList")] List<DataEnclosure> addFileList);

        /// <summary>
        /// 获取组合的托管户账户信息
        /// </summary>
        /// <param name="portCodeList">portCodeList</param>
        /// <returns>Dictionary</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getDataListByPortlist")]
        Dictionary<string, List<FundAcc>> getDataListByPortlist(string[] portCodeList);

        /// <summary>
        /// 根据开户账号和开户行查询数据
        /// </summary>
        /// <param name="openNo">开户账号</param>
        /// <param name="openAddr">开户行</param>
        /// <param name="iden">iden</param>
        /// <returns>'true'：存在，false:不存在</returns>
        [MethodAttribute(Method = RequestType.POST, MethodUrl = "/getAccListByOpenNoAndOpenAddr")]
        string getAccListByOpenNoAndOpenAddr(string openNo, string openAddr, string iden);

    }
}
