using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service;
using FAST.Core.BaseControl;
using YssInformation.Support.Pojo;

namespace YssInformation.Support.Interface
{
    /// <summary>
    /// 核算元素各明细项目的加载
    /// </summary>
    public interface IDaeDetailLoader : IRelation, IDaeFactor
    {
        /// <summary>
        /// 设置参数
        /// </summary>
        /// <param name="clsDae">核算元素</param>
        /// <param name="parame">参数</param>
        /// <param name="cboDetail">明细的控件</param>
        void setParameter(Cls_DAE_ELEM clsDae, string parame, YssSelCombox cboDetail);
                
    }
}
