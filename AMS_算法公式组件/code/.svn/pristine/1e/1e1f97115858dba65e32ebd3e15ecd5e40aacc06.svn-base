using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.CRUD.Form;
using FAST.Core.Context;
using FAST.Core.BaseControl;
using FAST.Common.Service.Pojo.Base;
using YssVisAval.Pojo.AA;
using FAST.Core.Exceptions;
using YssVisAval.Pojo.SelfAlgorithm;
using FAST.Common.Service.Pojo;
using YssVisAval.service;
using FAST.Core.Communication.Service;
using FAST.Core.Util;
using System.Collections;
using FAST.Core.BaseControl.Fun;

namespace YssVisAval.Form.Algo
{
    /// <summary>
    /// A区set界面
    /// </summary>
    public partial class Frm_ADVANCED_ALGORITHM_S : FrmBaseSet
    {
        /// <summary>
        /// 接口
        /// </summary>
        private IVisAdvAlgoService service = null;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_ADVANCED_ALGORITHM_S()
        {
            service = (IVisAdvAlgoService)ServiceFactory.createService<IVisAdvAlgoService>();
            this.bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 重写控件状态
        /// </summary>
        protected override void YssChangeControlState()
        {
            if (status == ClsEnums.StatusSetting.YssAdd)
            {
                clsInterface.yssClearTableCtlValue(tbMain);
            }

            ////btnBar.setButtonEnabled("btnUnAudit", false);

        ////btnBar.setButtonEnabled("btnAudit", false);

        ////btnBar.setButtonEnabled("btnDelete", false);

        ////btnBar.setButtonEnabled("btnEdit", false);

        ////btnBar.setButtonEnabled("btnNew", false);
            btnBar.setButtonEnabled("btnCopy", false);
            btnBar.setButtonEnabled(ClsButtonName.BtnPrevious, false);
            btnBar.setButtonEnabled(ClsButtonName.BtnNext, false);

        //// this.btnEdit_Click()
        }

        /// <summary>
        /// 修改
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnEdit_Click(object sender, EventArgs e)
        {
            base.btnEdit_Click(sender, e);
            this.cALGONAME.YssReadOnly = false;
            this.cALGOCODE.YssReadOnly = false;
            ((Frm_ADVANCED_ALGORITHM_L)this.frmBaseViewList).Set_page_edit_before_code = this.cALGOCODE.Text.ToString();
        }

        /// <summary>
        /// 参照算法弹窗事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void czAlgo_ExpandClick(object sender, EventArgs e)
        {
            Frm_ADVANCED_ALGORITHM_L_CZ frm = new Frm_ADVANCED_ALGORITHM_L_CZ(this.czAlgo);
            SysFun sysFun = new SysFun();
            sysFun.C_FUN_CODE = "AdvancedAlgorithm_A";
            sysFun.C_FUN_NAME = "算法参照";
            frm.setMenuFun(sysFun);
            frm.ShowDialog(this);
        }

        /// <summary>
        /// 封装界面元素为pojo对象
        /// </summary>
        /// <returns>由界面元素组成的对象</returns>
        public override AuditableParamPojo faceInfoToObjMVC()
        {
            AdvAlgo clsAlgo = null;
            try
            {
                clsAlgo = new AdvAlgo();

                if ("".Equals(this.cALGOCODE.Text) || null == this.cALGOCODE.Text)
                {
                    ClsRetInfo c = new ClsRetInfo();
                    c.icon = MessageBoxIcon.Warning;
                    c.infoContent = "算法代码不能为空！";
                    c.detailInfo = "算法代码不能为空！";
                    YssMessageBox.ShowCommonInfoText(c);
                    return null;
                }

                if ("".Equals(this.cALGONAME.Text) || null == this.cALGONAME.Text)
                {
                    ClsRetInfo c = new ClsRetInfo();
                    c.icon = MessageBoxIcon.Warning;
                    c.infoContent = "算法名称不能为空！";
                    c.detailInfo = "算法名称不能为空！";
                    YssMessageBox.ShowCommonInfoText(c);
                    return null;
                }

                if ("".Equals(this.selfCboAlgoType.Value) || null == this.selfCboAlgoType.Value)
                {
                    ClsRetInfo c = new ClsRetInfo();
                    c.icon = MessageBoxIcon.Warning;
                    c.infoContent = "算法类型不能为空！";
                    c.detailInfo = "算法类型不能为空！";
                    YssMessageBox.ShowCommonInfoText(c);
                    return null;
                }

                clsAlgo.C_ALGO_CODE = this.cALGOCODE.Text;
                clsAlgo.C_ALGO_NAME = this.cALGONAME.Text;
                clsAlgo.C_DV_ALGO_TYPE = this.selfCboAlgoType.Value;
                clsAlgo.C_ALGO_FORMULA_TRANSFORM = " ";
                clsAlgo.C_ALGO_UPDATE_TARGET = "set";
                this.cALGOCODE.YssReadOnly = true;
                this.cALGONAME.YssReadOnly = true;

                AlgoSetParam p = new AlgoSetParam();

        ////p.AlgoCode = this.cALGONAME.Text;
                p.AlgoCode = this.cALGOCODE.Text;
                p.C_AlgoCode = this.czAlgo.Value;

                ////参考算法, 如果有参考算法，则到数据库查询被参考的算法的信息，赋值到新算法中，
                if (!"".Equals(this.czAlgo.Value) && null != this.czAlgo.Value)
                {
                    AdvAlgo advalgo = (AdvAlgo)this.service.getAlgoByCode(this.czAlgo.Value);
                    clsAlgo.C_ALGO_FORMULA = advalgo.C_ALGO_FORMULA;
                    clsAlgo.C_ALGO_FORMULA_ZH = advalgo.C_ALGO_FORMULA_ZH;
                }

                ((Frm_ADVANCED_ALGORITHM_L)this.frmBaseViewList).AlgoSetParam = p;

            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return clsAlgo;
        }

        /// <summary>
        /// 1
        /// </summary>
        /// <param name="pojo">2</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            if (this.frmBaseViewList.tbLeftMain.SelectedRow != null)
            {
                AdvAlgo algo = this.frmBaseViewList.tbLeftMain.SelectedRow.Tag as AdvAlgo;
                AdvAlgo a = (AdvAlgo)this.service.getAlgoByCode(algo.C_ALGO_CODE);
                if (null != a)
                {
                    this.cALGOCODE.Text = a.C_ALGO_CODE;
                    this.cALGONAME.Text = a.C_ALGO_NAME;
                    this.selfCboAlgoType.Value = a.C_DV_ALGO_TYPE;
                    this.cALGONAME.YssReadOnly = true;
                    this.cALGOCODE.YssReadOnly = true;
                }
            else
            {
                    return;
                }
                
            }
        }

         /// <summary>
        /// A区数据的保存事件
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnLeftSave_Click(object sender, System.EventArgs e)
        {
            base.btnLeftSave_Click(sender, e);
            bool textBoxStatus = this.selfCboAlgoType.YssReadOnly;
            this.cALGOCODE.YssReadOnly = textBoxStatus;
            this.cALGONAME.YssReadOnly = textBoxStatus;
        }


    }
}
