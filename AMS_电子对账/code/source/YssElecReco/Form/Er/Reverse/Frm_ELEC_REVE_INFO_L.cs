using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Pojo;
using FAST.Core.BaseControl.Pojo;
using System.Collections;
using FAST.Core.Util;
using FAST.Core.Exceptions;
using YssElecReco.Service.Er.Reverse;
using YssElecReco.Pojo.Er.Reverse;
using Yss.KTable.Models;
using FAST.Core.Communication.Service;
using YssElecReco.Fun;

namespace YssElecReco.Form.Er.Reverse
{
    public partial class Frm_ELEC_REVE_INFO_L : FrmBaseListWithDetails
    {
        private IReveDzService reveDzService = null;
        private IErReveInfoService infoService = null;
        private IErKmbOutService kmbService = null;
        private IErGzbOutService gzbService = null;
        private IAssMapService assMapService = null;
        private List<ClsButtonInfo> customBtns = new List<ClsButtonInfo>();
        public Frm_ELEC_REVE_INFO_L()
        {
            this.bUseMVCService = true;
            InitializeComponent();
            infoService = ServiceFactory.createService<IErReveInfoService>();
            reveDzService = ServiceFactory.createService<IReveDzService>();
        }

        private void addCustomButton()
        {
            if(customBtns.Count>0)
            {
                return;
            }
            ClsButtonInfo btnFkjg = new ClsButtonInfo();
            btnFkjg.Text = "反馈结果";
            btnFkjg.Tooltip = "反馈结果";
            btnFkjg.Name = "btnFkjg";
            btnFkjg.Image = FAST.Resource.Resource.btnExport_L;
            btnFkjg.ClickEvent = btnFkjg_Click;
            btnBar.addButton(btnFkjg, 0);
            customBtns.Add(btnFkjg);

            ClsButtonInfo btnXgdzjg = new ClsButtonInfo();
            btnXgdzjg.Text = "修改对账结果";
            btnXgdzjg.Tooltip = "修改对账结果";
            btnXgdzjg.Name = "btnXgdzjg";
            btnXgdzjg.Image = FAST.Resource.Resource.btnEdit_L;
            btnXgdzjg.ClickEvent = btnXgdzjg_Click;
            btnBar.addButton(btnXgdzjg, 1);
            customBtns.Add(btnXgdzjg);

            ClsButtonInfo btnRgdz = new ClsButtonInfo();
            btnRgdz.Text = "人工对账";
            btnRgdz.Tooltip = "人工对账";
            btnRgdz.Name = "btnRgdz";
            btnRgdz.Image = FAST.Resource.Resource.btnEliminate_L;
            btnRgdz.ClickEvent = btnRgdz_Click;
            btnBar.addButton(btnRgdz, 2);
            customBtns.Add(btnRgdz);

            ClsButtonInfo btnSd = new ClsButtonInfo();
            btnSd.Text = "锁定";
            btnSd.Tooltip = "锁定";
            btnSd.Name = "btnSd";
            btnSd.Image = FAST.Resource.Resource.btnLocked;
            btnSd.ClickEvent = btnSd_Click;
            btnBar.addButton(btnSd, 3);
            customBtns.Add(btnSd);

            ClsButtonInfo btnUnSd = new ClsButtonInfo();
            btnUnSd.Text = "解锁";
            btnUnSd.Tooltip = "解锁";
            btnUnSd.Name = "btnUnSd";
            btnUnSd.Image = FAST.Resource.Resource.FreeZhzl;
            btnUnSd.ClickEvent = btnUnSd_Click;
            btnBar.addButton(btnUnSd, 4);
            customBtns.Add(btnUnSd);

            
        }

        /// <summary>
        /// 反馈结果
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected void btnFkjg_Click(object sender, EventArgs e)
        {

            YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("暂未开通！"));
        }

        /// <summary>
        /// 修改对账结果
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected void btnXgdzjg_Click(object sender, EventArgs e)
        {
            List<ErReveInfo> list = getSelectRowPojo();
            if (list == null || list.Count <= 0)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("请勾选一条记录！"));
                return;
            }
            foreach (ErReveInfo info in list)
            {
                if (!ReveElecDVCons.INFO_RLS_SDZT_BSD.Equals(info.C_DV_LOCK_STATE))
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("只能修改未锁定的数据！"));
                    return;
                }
            }
            Frm_ELEC_REVE_DZRESULT_S frm = new Frm_ELEC_REVE_DZRESULT_S(list,this);
            frm.Show();
        }

        private List<ErReveInfo> getSelectRowPojo()
        {
            List<ErReveInfo> list = new List<ErReveInfo>();
            if (this.tbMain.CheckedRows.Count<=0)
            {
                return list;
            }
            ErReveInfo info = null;
            foreach(Row row in this.tbMain.CheckedRows)
            {
                info = row.Tag as ErReveInfo;
                list.Add(info);
            }
            return list;
        }
        /// <summary>
        /// 人工对账
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected void btnRgdz_Click(object sender, EventArgs e)
        {
            List<ErReveInfo> list = getSelectRowPojo();
            if(list==null||list.Count<=0)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("请勾选一条记录！"));
                return;
            }
            foreach (ErReveInfo info in list)
            {
                if (ReveElecDVCons.INFO_RLS_SDZT_SD.Equals(info.C_DV_LOCK_STATE))
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("已锁定的数据，不能再次对账！"));
                    return;
                }
            }
            string s = reveDzService.compareErDataOper(list);
            YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo(s));
            this.btnSearch_Click(this.btnBar.getButton(FAST.Core.BaseControl.Fun.ClsButtonName.BtnRefresh).Owner, EventArgs.Empty);
        }

        /// <summary>
        /// 锁定
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected void btnSd_Click(object sender, EventArgs e)
        {
            List<ErReveInfo> list = getSelectRowPojo();
            if (list == null || list.Count <= 0)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("请勾选一条记录！"));
                return;
            }
            foreach(ErReveInfo info in list)
            {
                if(!(ReveElecDVCons.RDZ_RESULT_SAME.Equals(info.C_DV_DZ_RESULT) || ReveElecDVCons.RDZ_RESULT_DIFF.Equals(info.C_DV_DZ_RESULT)))
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("只能锁定已经对过账的数据！"));
                    return;
                }
            }
            string s = infoService.sdDzResult(list);
            if(s.Equals("success"))
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("锁定成功！"));
            }else
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("锁定失败！"));
            }
            this.btnSearch_Click(this.btnBar.getButton(FAST.Core.BaseControl.Fun.ClsButtonName.BtnRefresh).Owner, EventArgs.Empty);
        }

        /// <summary>
        /// 解锁
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected void btnUnSd_Click(object sender, EventArgs e)
        {
            List<ErReveInfo> list = getSelectRowPojo();
            if (list == null || list.Count <= 0)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("请勾选一条记录！"));
                return;
            }
            foreach (ErReveInfo info in list)
            {
                if (!ReveElecDVCons.INFO_RLS_SDZT_SD.Equals(info.C_DV_LOCK_STATE))
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("只能解锁已经锁定的数据！"));
                    return;
                }
            }
            string s = infoService.unSdDzResult(list);
            
            if (s.Equals("success"))
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("解除锁定成功！"));
            }
            else
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("解除锁定失败！"));
            }
            this.btnSearch_Click(this.btnBar.getButton(FAST.Core.BaseControl.Fun.ClsButtonName.BtnRefresh).Owner, EventArgs.Empty);
        }

        /// <summary>
        /// 装载明细窗体功能代码列表
        /// </summary>
        /// <returns>返回明细窗体功能代码列表</returns>
        protected override List<SysFun> LoadDetailFormFuns()
        {
            List<SysFun> sysFuns = new List<SysFun>();
            SysFun newFun1 = new SysFun();
            newFun1.C_FUN_CODE = "reveDzResultDetail";
            newFun1.C_FUN_NAME = "";
            SysFun newFun2 = new SysFun();
            newFun2.C_FUN_CODE = "reveDzMessageDetail";
            sysFuns.Add(newFun1);
            sysFuns.Add(newFun2);
            return sysFuns;
        }
        /// <summary>
        /// 反馈状态加载前事件
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void cboFkState_YssOnBeforeLoadData(object sender, FAST.Core.Context.Events.YssBeforeOperEventArgs e)
        {
            e.IsCancel = true;
            if (this.cboFkState.Items.Count<=0)
            {
                Yss.KRichEx.AutoFilter.Model.KTableEntity item1 = new Yss.KRichEx.AutoFilter.Model.KTableEntity("已反馈","FK_YFK");
                Yss.KRichEx.AutoFilter.Model.KTableEntity item2 = new Yss.KRichEx.AutoFilter.Model.KTableEntity("未反馈","FK_WFK");
                this.cboFkState.Items.Add(item1);
                this.cboFkState.Items.Add(item2);
            }
        }

        private void Frm_ELEC_REVE_INFO_L_Load(object sender, EventArgs e)
        {
            addCustomButton();
        }

        /// <summary>
        /// 封装前台查询条件
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>paraDict</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            try
            {
                paraDict.Add("dataClass", "ErReveInfo");
                string search = this.yssBuildLeftCheckRowsStr("portfolio");
                search = search.Replace("'", "");
                paraDict.Add("ARRAY_C_PORT_CODE", search);

                paraDict.Add("D_TRADE_START", this.dtpDzDate.getBeginDateStr);

                if (this.dtpDzDate.YssShowSecond)
                {
                    paraDict.Add("D_TRADE_END", this.dtpDzDate.getEndDateStr);
                }

                if (this.cboDzType.Value != null && !"".Equals(this.cboDzType.Value))
                {
                    paraDict.Add("C_FILE_TYPE", this.cboDzType.Value);
                }
                if (this.cboDzResult.Value != null && !"".Equals(this.cboDzResult.Value))
                {
                    paraDict.Add("C_DV_DZ_RESULT", this.cboDzResult.Value);
                }
                if (this.cboFkState.Value != null && !"".Equals(this.cboFkState.Value))
                {
                    paraDict.Add("C_FK_STATE", this.cboFkState.Value);
                }



            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500037", _formFun, status));
                ClsBaseException.DiscardException(ex);
            }

            return paraDict;
        }

        /// <summary>
        /// 重写行双击事件，屏蔽行双击
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbMain_RowDoubleClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            return;
        }

    }
}
