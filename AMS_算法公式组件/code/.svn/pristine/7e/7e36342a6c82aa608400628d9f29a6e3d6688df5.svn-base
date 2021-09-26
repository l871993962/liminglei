using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;


using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;


using FAST.Core.Context;
using YssVisAval.Fun;

namespace YssVisAval.Form.AA
{
     /// <summary>
    /// 高级算法list窗体
    /// </summary>
    public partial class Frm_ADVANCED_ALGORITHM_L : FrmBaseList
    {
        /// <summary>
        /// 窗体构造方法
        /// </summary>
        public Frm_ADVANCED_ALGORITHM_L()
        {
            this.bUseMVCService = true;
            InitializeComponent();

            // 债券交易业务实现附件功能。何讯，20151208
            this.AutoLoadEnclosure = true;
        }

        /// <summary>
        /// 不加载A区数据的方法
        /// <author>yh 2011.02.28</author>
        /// </summary>
        public override void yssLoadLeftData()
        {
        }


        /// <summary>
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写
        /// </summary>
        /// <returns>初始化查询条件</returns>
        public override string yssInitQuery()
        {
            // 所有提供的参数项如下，只需要设置子类需要的项即可
            // 1 查询条件
            string cond = ""; // " and a.C_TD_TYPE ='CWSS'";

            // 3 初始只加载列头，若需要则设为true，反之不需要设置此参数
            this.IsOnlyHeder = true;

            return cond;
        }

        /// <summary>
        /// 拼接向后台发送的字符串
        /// </summary>
        /// <returns>查询条件</returns>
        public string yssGetListCond()
        {
            string cond = "";

            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
            if (this.cboAlgoType.Value != null)
            {
                quyStrUtil.addQuyCon("C_DV_ALGO_TYPE", this.cboAlgoType.Value, ClsConstant.SQL_RA_HYPHEN_EQUAL);
            }

            cond = quyStrUtil.getQuyStr(dataAdmin.CurFUN.C_FUN_CODE);
            return cond;
        }
        
        /// <summary>
        /// 封装条件对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            if (this.cboAlgoType.Value != null)
            {
                paraDict.Add("C_DV_ALGO_TYPE", this.cboAlgoType.Value);
            }

            return paraDict;
        }

        
    }
}


