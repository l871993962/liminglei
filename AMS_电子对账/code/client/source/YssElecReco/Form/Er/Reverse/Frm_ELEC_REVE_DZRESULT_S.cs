using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.CRUD.Form;
using FAST.Core.BaseControl.Fun;
using FAST.Core.Communication.Service;
using YssElecReco.Service.Er.Reverse;
using FAST.Core.Util;
using YssElecReco.Pojo.Er.Reverse;

namespace YssElecReco.Form.Er.Reverse
{
    public partial class Frm_ELEC_REVE_DZRESULT_S : FrmBaseSet
    {
        private Frm_ELEC_REVE_INFO_L frmList = null; 
        private List<ErReveInfo> list = null;
        private IErReveInfoService infoService = null;
        public Frm_ELEC_REVE_DZRESULT_S(List<ErReveInfo> list,Frm_ELEC_REVE_INFO_L frm)
        {
            InitializeComponent();
            initComponent();
            this.list = list;
            this.frmList = frm;
            infoService = ServiceFactory.createService<IErReveInfoService>();
        }

        public Frm_ELEC_REVE_DZRESULT_S()
        {
            InitializeComponent();
            initComponent();
            infoService = ServiceFactory.createService<IErReveInfoService>();
        }

        private void initComponent()
        {
            this.btnBar.setAllButtonEnabled(false);
            this.btnBar.setAllButtonVisibled(false);
            this.btnBar.setButtonEnabled(ClsButtonName.BtnClose);
            this.btnBar.setButtonEnabled(ClsButtonName.BtnSave);
            this.btnBar.setButtonVisibled(ClsButtonName.BtnClose);
            this.btnBar.setButtonVisibled(ClsButtonName.BtnSave);
        }

        /// <summary>
        /// 数据的保存事件
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnSave_Click(object sender, System.EventArgs e)
        {
            if (!checkInput())
            {
                return;
            }
            string s = this.infoService.editDzResult(this.list,this.cboDzResult.Value,this.txtXgsm.Text);
            if (s.Equals("success"))
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("修改成功！"));
            }
            else
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("修改失败！"));
            }
            this.Close();
            this.frmList.btnSearch_Click(this.frmList.btnBar.getButton(FAST.Core.BaseControl.Fun.ClsButtonName.BtnRefresh).Owner, EventArgs.Empty);
        }

    }
}
