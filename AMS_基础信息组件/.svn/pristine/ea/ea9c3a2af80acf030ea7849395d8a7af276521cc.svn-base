using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

using FAST.Core.CRUD.Form;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.BaseControl.Fun;
using FAST.Core.Exceptions;
using YssInformation.Support.Sys.ConvertDict.Zdorg.Pojo;
using YssInformation.Support.Sys.ConvertDict.Zhzd.Pojo;



namespace YssInformation.Sys.ConvertDict.Zhzd.Form
{
    public partial class Frm_SWITCH_DICT_S : FrmBaseSet
    {
        public Frm_SWITCH_DICT_S()
        {
            InitializeComponent();
            this.bUseMVCService = true;
        }

        /// <summary>
        /// 封装窗体数据到对象
        /// </summary>
        /// <returns>Pojo</returns>
        public override AuditableParamPojo faceInfoToObjMVC()
        {
            YssInformation.Support.Sys.ConvertDict.Zhzd.Pojo.Zhzd zhzd = null;
            ZdCorpOrg zdCorpOrg = (ZdCorpOrg)this.frmBaseViewList.yssGetSelTypeItemMVC(frmBaseViewList.tbLeftMain);
            try
            {
                zhzd = new YssInformation.Support.Sys.ConvertDict.Zhzd.Pojo.Zhzd();

                zhzd.C_GROUP_CODE = zdCorpOrg.C_GROUP_CODE;
                zhzd.C_S_CODE = this.txtC_S_Code.Text;
                zhzd.C_T_CODE = this.txtC_T_Code.Text;
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return zhzd;
        }

        /// <summary>
        /// 展示对象属性到窗体
        /// </summary>
        /// <param name="pojo">Pojo</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            try
            {
                ////ZdCorpOrg zdCorpOrg = (ZdCorpOrg)this.frmBaseViewList.yssGetSelTypeItemMVC(frmBaseViewList.tbLeftMain);
                YssInformation.Support.Sys.ConvertDict.Zhzd.Pojo.Zhzd zhzd = (YssInformation.Support.Sys.ConvertDict.Zhzd.Pojo.Zhzd)this.frmBaseViewList.yssGetSelTypeItemMVC(frmBaseViewList.tbMain);
                if (zhzd == null)
                {
                    return;
                }

                this.txtC_S_Code.Text = zhzd.C_S_CODE;
                this.txtC_T_Code.Text = zhzd.C_T_CODE;
                //// 控制按钮状态
                if (frmBaseViewList.tbLeftMain.SelectedRow.SubRows.Count != 0)
                {
                    btnBar.setButtonEnabled(ClsButtonName.BtnNew, false);
                    btnBar.setButtonEnabled(ClsButtonName.BtnEdit, false);
                    btnBar.setButtonEnabled(ClsButtonName.BtnCopy, false);
                    btnBar.setButtonEnabled(ClsButtonName.BtnDelete, false);
                }
                else
                {
                    btnBar.setButtonEnabled(ClsButtonName.BtnNew, true);
                    btnBar.setButtonEnabled(ClsButtonName.BtnEdit, true);
                    btnBar.setButtonEnabled(ClsButtonName.BtnCopy, true);
                    btnBar.setButtonEnabled(ClsButtonName.BtnDelete, true);
                }

            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

    }
}
