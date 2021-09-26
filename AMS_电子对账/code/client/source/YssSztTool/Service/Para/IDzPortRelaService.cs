using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Services.Base;
using System.Collections;
using FAST.Common.Service.Attributes;

namespace YssSztTool.Service.Para
{
    [ServiceAttribute(ServiceId = ServiceIdConstant.OSGI_BASEBUSINESS, ServiceController = "com.yss.ifa.szt.tool.para.service.controller.IDzPortRelaController")]
     public interface IDzPortRelaService : IServiceBus
    {
        [MethodAttribute] 
        List<string> queryPortCodesRelaOrgan(Dictionary<string, string> paraDict);

        [MethodAttribute]
        QueryRes queryPortRelaSubOrganPage(Dictionary<string, string> paraDict, PageInation page);
         /// <summary>
        /// 根据业务主键删除
         /// </summary>
         /// <param name="pojoList"></param>
         /// <returns></returns>
        [MethodAttribute]
        string delByYwId(ArrayList pojoList);
        /// <summary>
        /// 根据业务主键先删后增 
        /// </summary>
        /// <param name="pojoList"></param>
        /// <returns></returns>
        [MethodAttribute]
        string delInsert(ArrayList pojoList);
    }
}
