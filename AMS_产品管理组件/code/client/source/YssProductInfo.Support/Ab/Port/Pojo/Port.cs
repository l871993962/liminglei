using FAST.Common.Service.Pojo;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Collections;
using FAST.Common.Service.Datastructure;

namespace YssProductInfo.Support.Ab.Port.Pojo
{
    /// <summary>
    /// 产品信息组件_组合基本信息pojo类
    /// 
    /// 调整让YssProductInfo.Support.Ab.Port.Pojo.Port继承于FAST.Common.Service.Pojo.Port
    /// 为了让后面整合时A区切换成YssProductInfo.Support.Ab.Port.Pojo.Port而未拆分的功能可以兼容
    /// 现拆分出去的功能暂时还是用FAST.Common.Service.Pojo.Port
    /// 
    /// added by HeLiang 2017-06-28
    /// STORY #42921 产品信息组件拆分开发
    /// </summary>
    public class Port : FAST.Common.Service.Pojo.Port
    {
    }
}
