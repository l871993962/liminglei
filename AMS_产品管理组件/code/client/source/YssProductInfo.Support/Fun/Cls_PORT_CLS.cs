using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Core.Context;
using FAST.Core.BaseControl;
using YssProductInfo.Support.Context;
using YssInformation.Support.Pojo;
using YssInformation.Support.Fun;
 
namespace YssProductInfo.Support.Fun
{
    /// <summary>
    /// 组合分级
    /// </summary>
    sealed internal class Cls_PORT_CLS : ClsBaseDaeDeail
    {
        /// <summary>
        /// 设置参数
        /// </summary>
        /// <param name="currDetail">下拉框</param>
        /// <returns>返回类型</returns>
        protected override Associa setAssociaType(FAST.Core.BaseControl.YssSelCombox currDetail)
        {
            FAST.Core.BaseControl.ControlMethodInfo controlMethod = new FAST.Core.BaseControl.ControlMethodInfo();
            controlMethod.MethodName = "getDataList";
            currDetail.MethodInfo = controlMethod;
            currDetail.QueryByValues = false;
            return AssociaType.pd_productgrade;
        }

        /// <summary>
        /// 查询条件
        /// </summary>
        /// <param name="currDetail">当前控件</param>
        /// <param name="parameter">参数</param>
        /// <returns>参数</returns>
        protected override string buildConds(FAST.Core.BaseControl.YssSelCombox currDetail, string parameter)
        {            
            if (collection != null && collection.ContainsKey(Cons.NAME_PORT))
            {
                currDetail.QueryType = ClsConstant.CacheType;

                ////liuxiang 2015-9-9 BUG #118815 收支结转业务窗体加载出错
                //// 控件是普通下拉框时
                if (collection[Cons.NAME_PORT] is FAST.Core.BaseControl.YssSelCombox)
                {
                    currDetail.QueryCond = (collection[Cons.NAME_PORT] as FAST.Core.BaseControl.YssSelCombox).Value;
                }
                else if (collection[Cons.NAME_PORT] is GroupTextBox)
                {
                    //// 控件是组合/群组控件时
                    currDetail.QueryCond = (collection[Cons.NAME_PORT] as GroupTextBox).Value;
                }

                FAST.Core.BaseControl.ControlMethodInfo controlMethod = new FAST.Core.BaseControl.ControlMethodInfo();
                controlMethod.MethodName = "getDataListByTypes";
                controlMethod.MethodParamValues = new string[] { currDetail.QueryCond + "," };
                currDetail.MethodInfo = controlMethod;
            }

            return currDetail.QueryCond;
        }
    }
}
