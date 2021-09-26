using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;

using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;
using System.Collections.Generic;



namespace YssSecInformation.Mp.Hggthq.Form
{
    /// <summary>
    /// 回购收益行情List
    /// </summary>
    public partial class Frm_SEC_LL_GT_L : FrmBaseListWithDetails
    {
        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_SEC_LL_GT_L()
        {
            InitializeComponent();
            this.bUseMVCService = true;
        }

        /// <summary>
        /// 查询条件封装
        /// </summary>
        /// <param name="paraDict">para</param>
        /// <returns>paraMap</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            if (null != this.cbodur.Value) 
            {
                paraDict.Add("N_DURATION", this.cbodur.Value);
            }

            paraDict.Add("D_BEGIN", dtpMKTPriceDate.getBeginDateStr);
            paraDict.Add("D_END", dtpMKTPriceDate.getEndDateStr);
            paraDict.Add("C_IS_PUBLIC", "1");

            return paraDict;
        }

        /// <summary>
        /// 装载明细窗体功能代码列表
        /// </summary>
        /// <returns>返回明细窗体功能代码列表</returns>
        protected override List<SysFun> LoadDetailFormFuns()
        {
            List<SysFun> sysFuns = new List<SysFun>();

            //// 证券回购收益行情
            SysFun newFun = new SysFun();
            newFun.C_FUN_CODE = "sv_counterratesec";
            sysFuns.Add(newFun);

            return sysFuns;
        }
    }
}


