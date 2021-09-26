using System.Collections.Generic;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Pojo;
using FAST.Core.Context;
using System;
using FAST.Core.BaseControl.Pojo;

namespace YssSztTool.Form.Para
{
    /// <summary>
    /// STORY42784中国银行_深证通伺服器要求采用热备模式
    /// STORY42660【中国银行】深证通伺服器要求采用热备模式
    /// DESC: 深圳通参数设置窗体
    /// CREATED BY: wulongxing
    /// CREATED TIME: 2017-06-12
    /// </summary>
    public partial class Frm_Er_Para_L : FrmBaseListWithDetails
    {
        public Frm_Er_Para_L()
        {
            InitializeComponent();
            bUseMVCService = true;
            ShowLeftPanel = false;
            isLoadFirst = true;
        }

        #region #父窗体
        /// <summary>
        /// 装载明细窗体功能代码列表
        /// </summary>
        /// <returns>返回明细窗体功能代码列表</returns>
        protected override List<SysFun> LoadDetailFormFuns()
        {
            List<SysFun> sysFuns = new List<SysFun>();
            if (ClsContext.sysMenuFunHash.ContainsKey("mrInfo"))
            {
                SysFun sysFun = ClsContext.sysMenuFunHash["mrInfo"] as SysFun;
                sysFuns.Add(sysFun);
                if (!ClsContext.HtUserOperRight.ContainsKey(sysFun.C_FUN_CODE))
                {
                    ClsContext.HtUserOperRight.Add(sysFun.C_FUN_CODE, ClsContext.HtUserOperRight["mrInfo"]);
                }
                if (!ClsContext.HtFunRight.ContainsKey(sysFun.C_FUN_CODE))
                {
                    ClsContext.HtFunRight.Add(sysFun.C_FUN_CODE, ClsContext.HtFunRight["mrInfo"]);
                }

                if (!ClsContext.sysFunHash.ContainsKey(sysFun.C_FUN_CODE))
                {
                    ClsContext.sysFunHash.Add(sysFun.C_FUN_CODE, sysFun);
                }
            }
            return sysFuns;
        }

        #endregion 父窗体

        private void Frm_Er_Para_L_Load(object sender, System.EventArgs e)
        {
            ClsButtonInfo btnCheck = new ClsButtonInfo();
            btnCheck.Text = "连接检测设置";
            btnCheck.Tooltip = "连接检测设置";
            btnCheck.Name = "btnCheck";
            btnCheck.Image = FAST.Resource.Resource.SetExpand_Hot;
            btnCheck.ClickEvent = btnCheck_Click;
            btnBar.addButton(btnCheck,10);
            ////分布式先屏蔽此版本
            ////YssDevComponents.DotNetBar.ButtonItem btnCheck = new YssDevComponents.DotNetBar.ButtonItem();
            ////btnCheck.BeginGroup = true;
            ////btnCheck.ButtonStyle = YssDevComponents.DotNetBar.eButtonStyle.ImageAndText;
            ////btnCheck.Image = FAST.Resource.Resource.SetExpand_Hot;
            ////btnCheck.Name = "btnCheck";
            ////btnCheck.Text = "连接检测设置";
            ////btnCheck.Tooltip = "连接检测设置";
            ////btnCheck.Click += new System.EventHandler(this.btnCheck_Click);
            ////btnBar.addButton(btnCheck, 10);
        }

        private void btnCheck_Click(object sender, EventArgs e)
        {
            Frm_MR_CHECK_S checkSet = new Frm_MR_CHECK_S();
            checkSet.init();
            checkSet.ShowDialog();
        }
        
    }
}
