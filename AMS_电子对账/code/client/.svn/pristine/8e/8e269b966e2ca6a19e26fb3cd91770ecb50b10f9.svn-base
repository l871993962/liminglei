using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using YssElecReco.pojo.Er;
using FAST.Common.Service.Services.Base;
using FAST.Common.Service.Pojo.Base;
using FAST.Platform.DataIntegration.Pojo;
using FAST.Common.Service.Attributes;
using YssElecReco.Context;

namespace YssElecReco.Service.Er
{
    /// <summary>
    /// 电子对账模板服务类
    /// </summary>
    [ServiceAttribute(ServiceId = ElecrecoServiceIdConstant.OSGI_ELECRECO, ServiceController = "com.yss.uco.elecreco.er.template.controller.IDzTemplateServiceController")]
    public interface IDzTemplateService : IServiceBus
    {
        /// <summary>
        /// 查询组合关联的模板
        /// </summary>
        /// <param name="typeCode">模板代码</param>
        /// <param name="portCode">组合代码</param>
        /// <returns>对账模板</returns>
        [MethodAttribute]
        DzTemplate getTemplateByTypeCodeAndPortCode(string typeCode, string portCode);

        /// <summary>
        /// 更新模板状态
        /// </summary>
        /// <param name="basePojoList">模板集合</param>
        /// <returns>更新结果</returns>
        [MethodAttribute]
        string updateStatus(List<BasePojo> basePojoList);

        /// <summary>
        /// 模板部署
        /// </summary>
        /// <param name="zipFiles">文件</param>
        /// <returns>部署结果</returns>
        [MethodAttribute]
        string deploy(string zipFiles);

        /// <summary>
        /// 模板卸载
        /// </summary>
        /// <param name="templateList">模板列表</param>
        /// <returns>卸载结果</returns>
        [MethodAttribute]
        string unDeploy(List<BasePojo> templateList);

        /// <summary>
        /// 模板下载
        /// </summary>
        /// <param name="basePojo">模板列表</param>
        /// <returns>结果</returns>
        [MethodAttribute]
        string downLoad(BasePojo basePojo);

        /// <summary>
        /// 查询已部署的模板
        /// </summary>
        /// <returns>模板集合</returns>
        [MethodAttribute]
        List<string> getDeployTemplate();

        /// <summary>
        /// 文件上传
        /// </summary>
        /// <param name="fileTrans">文件流传输的相关参数</param>
        /// <returns>string</returns>
        [MethodAttribute]
        string upload(FileStreamParam fileTrans);
    }
}
