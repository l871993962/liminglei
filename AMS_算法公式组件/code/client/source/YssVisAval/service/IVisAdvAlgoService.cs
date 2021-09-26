using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Exceptions;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using System;
using System.Collections.Generic;
using System.Text;

using YssVisAval.Pojo.AA;
using YssVisAval.Pojo.SelfAlgorithm;

namespace YssVisAval.service
{
    /// <summary> 
    /// 高级算法服务接口
    /// </summary>
    public interface IVisAdvAlgoService : IServiceBus
    {
        /// <summary>
        /// ddd
        /// </summary>
        /// <param name="customize">customize</param>
        /// <returns>dd</returns>
        string getRealExpression(string customize);
        
        /// <summary>
        /// ddd
        /// </summary>
        /// <param name="funType">funType</param>
        /// <returns>dd</returns>
        string getFunc(string funType);
        
        /// <summary>
        /// ddd
        /// </summary>
        /// <param name="iSNew">判断是否是新算法</param>
        /// <param name="targetStr">targetStr</param>
        /// <returns>dd</returns>
        string checkFormula(string iSNew, string targetStr);
        
        /// <summary>
        /// ddd
        /// </summary>
        /// <param name="keyType">keyType</param>
        /// <returns>ddd</returns>
        string getKeyWords(string keyType);

        /// <summary>
        /// ddd
        /// </summary>
        /// <returns>dd</returns>
        string getAllDate();

        /// <summary>
        /// 取得所有参数
        /// </summary>
        /// <returns>参数列表</returns>
        List<Cls_Parameter> getAllParameters();

        /// <summary>
        /// 取得树列表
        /// </summary>
        /// <param name="code">算法类型</param>
        /// <returns>函数列表</returns>
        string getTreeData(string code);

        /// <summary>
        /// 根据code加载参数和返回值
        /// </summary>
        /// <param name="code">1</param>
        /// <returns>2</returns>
        string getDataAPIByCode(string code);

        /// <summary>
        /// 根据参数的code加载控件
        /// </summary>
        /// <param name="code">关键字</param>
        /// <returns>下拉框信息</returns>
        Dictionary<string, string> getParasByCode(string code);

        /// <summary>
        /// 根据算法代码查找所有算法信息及映射关系
        /// </summary>
        /// <param name="algoCode">algoCode</param>
        /// <returns>Dictionary</returns>
        Dictionary<string, string> getAlgoMsg(string algoCode);

        /// <summary>
        /// 根据算法代码查出所有算法映射关系信息
        /// </summary>
        /// <param name="code">algoCode</param>
        /// <returns>string</returns>
        string getAlgoRTF(string code);

        /// <summary>
        /// 找到最大标记
        /// </summary>
        /// <param name="code">code</param>
        /// <returns>string</returns>
        string getMaxSign(string code);

        /// <summary>
        /// 获取A去树结构
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>QueryRes</returns>
        QueryRes getTreeViewData(Dictionary<string, string> paraDict);

        /// <summary>
        /// 获取A区树所有数据
        /// </summary>
        /// <returns>json</returns>
        string getAllDataTree_A();

        /// <summary>
        /// 参考算法数据
        /// </summary>
        /// <returns>string</returns>
        string getAlgos();

        /// <summary>
        /// 根据code获取算法描述
        /// </summary>
        /// <param name="code">code</param>
        /// <returns>s</returns>
        string getAlgoDesc(string code);

        /// <summary>
        /// 根据算法代码查找算法信息
        /// </summary>
        /// <param name="code">code</param>
        /// <returns>BasePojo</returns>
        BasePojo getAlgoByCode(string code);

        /// <summary>
        /// 根据函数代码到后台查询规则
        /// </summary>
        /// <param name="code">函数代码</param>
        /// <returns>规则集合</returns>
        Dictionary<string, bool> getRuleByFunCode(string code);

        /// <summary>
        /// 根据正则表达式解析合法的函数表达式
        /// </summary>
        /// <param name="fun">表达式串</param>
        /// <returns>函数集合</returns>
        List<string> FunRegex(string fun);
        
        /// <summary>
        /// 预览
        /// </summary>
        /// <param name="msg">中文算法</param>
        /// <returns>翻译后文本</returns>
        string preview(string msg);

        /// <summary>
        /// 验证新旧算法
        /// </summary>
        /// <param name="code">code</param>
        /// <returns>string</returns>
        string isNewAlgo(string code);

        /// <summary>
        /// 根据关键字获取保留位数下拉框信息
        /// </summary>
        /// <param name="keyword">keyword</param>
        /// <returns>string</returns>
        string getWSMsg(string keyword);

        /// <summary>
        /// 根据算法代码检查该参数是否由关键字，该关键字是否来自t_s_param
        /// </summary>
        /// <param name="code">算法代码</param>
        /// <returns>True or False</returns>
        string hasKeyWord(string code);

        /// <summary>
        /// 根据算法代码查找算法状态（是否被审核）
        /// </summary>
        /// <param name="code">算法代码</param>
        /// <returns>状态 0 或者 1</returns>
        string checkAlgoStatus(string code);

        /// <summary>
        /// 检测该函数是否为新算法中的函数
        /// </summary>
        /// <param name="funName">函数名</param>
        /// <returns>True,False</returns>
        string checkFunName(string funName);

        /// <summary>
        /// 解析当前行数据，找出各个函数字符串
        /// </summary>
        /// <param name="funData">行数据</param>
        /// <returns>函数集合</returns>
        string getFunList(string funData);

        /// <summary>
        /// 根据参数名，判断该参数是否有明细
        /// </summary>
        /// <param name="paramName">参数中文名</param>
        /// <returns>是否有明细</returns>
        string paramHasDetail(string paramName);

        /// <summary>
        /// 根据参数中文名获取参数code
        /// </summary>
        /// <param name="paramName">参数中文名</param>
        /// <returns>参数code</returns>
        string getParamCodeByParamName(string paramName);

        
        /// <summary>
        /// 获取所有的公式
        /// </summary>
        /// <returns>公式对象集合</returns> 
        List<AdvAlgo> getAllAchivmentAlgo();

        /// <summary>
        /// 根据公式代码获取公式详细数据
        /// </summary>
        /// <param name="code">公式代码</param>
        /// <returns>公式对象</returns>
        AdvAlgo getAchivmentAlgoByCode(string code);

    }
}


