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
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Core.BaseControl.Pojo;
using Yss.KTable.Collections;
using Yss.KTable.Models;
using FAST.Common.Service.Pojo;
using FAST.Core.Context;

namespace YssProductInfo.Aa.PortCls.Form.Ab.Port.Form
{
    public partial class Frm_PORT_CT_L : FrmBaseList
    {

        private BasePojo prdInfo = null;

        public BasePojo PrdInfo
        {
            get { return prdInfo; }
            set { prdInfo = value; }
        }

        public Frm_PORT_CT_L()
        {
            InitializeComponent();
            base.baseDataobject = (BasePojo)ReflectBase.getInstance("YssProductInfo.Support.dll", "YssProductInfo.Support.Ab.Port.Pojo.Port");
        }

        /// <summary>
        /// 重写基类的onload方法
        /// </summary>
        /// <param name="e">e</param>
        protected override void OnLoad(EventArgs e)
        {
            base.OnLoad(e);
            this.btnBar.setButtonVisibled(ClsButtonName.BtnNew);
            this.btnBar.setButtonVisibled(ClsButtonName.BtnEdit);
            this.btnBar.setButtonVisibled(ClsButtonName.BtnCopy);
            this.btnBar.setButtonVisibled(ClsButtonName.BtnDelete);
            this.btnBar.setButtonVisibled(ClsButtonName.BtnUnAudit);
            this.btnBar.setButtonVisibled(ClsButtonName.BtnAudit);
            addRelaButton();

        }

        /// <summary>
        /// 添加关联按钮
        /// </summary>
        private void addRelaButton()
        {
            ClsButtonInfo btnChooseInfo = new ClsButtonInfo();
            btnChooseInfo.Name = "btnChoosePrd";
            btnChooseInfo.Text = "选择";
            btnChooseInfo.Tooltip = "选择";
            btnChooseInfo.Image = new Bitmap(FAST.Resource.Resource.btnChoose_L, 24, 24);
            btnChooseInfo.ClickEvent = btnChoose_Click;
            this.btnBar.addButton(btnChooseInfo, 0);
            this.btnBar.setButtonEnabled("btnChooseInfo", true);

        }

        private void btnChoose_Click(object sender, EventArgs e)
        {

            if (prdInfo == null)
            {
                bool hadSelected = false;
                foreach (Row row in tbMain.CheckedRows)
                {
                    if (row.Tag is AuditableParamPojo)
                    {
                        AuditableParamPojo pojo = row.Tag as AuditableParamPojo;
                        if (pojo.AuditState == 1)
                        {
                            hadSelected = true;
                            break;
                        }
                    }
                }

                if (hadSelected == false)
                {
                    Yss.CommonLib.ShowMessage("请勾选一条已审核的数据");
                    return;
                }

                this.DoFrmBaseListSelect(tbMain.CheckedRows);
                this.Close();
            }
        }


        /// <summary>
        /// 针对List界面勾选数据，进行回填。
        /// </summary>
        /// <param name="checkedRows">被勾选的行</param>
        private void DoFrmBaseListSelect(RowCollection checkedRows)
        {
            ////BUG #135844 证券还现金账户穿透功能优化。
            for (int index = 0; index < checkedRows.Count; index++)
            {
                Row tempRow = checkedRows[index];
                if (tempRow.Tag != null && tempRow.Tag is BasePojo)
                {
                    //判断是否为已审核的AuditableParamPojo
                    if (tempRow.Tag is AuditableParamPojo && (tempRow.Tag as AuditableParamPojo).AuditState == 0)
                    {
                        continue;
                    }

                    BasePojo tempPojo = tempRow.Tag as BasePojo;
                    ////仅为最后一条勾选的数据赋值
                    bool selectValue = (index == checkedRows.Count - 1 ? true : false);

                    PrdInfo = tempPojo;
                }
            }
        }

        /// <summary>
        /// 获取list查询条件区的查询条件.
        /// </summary>
        /// <param name="paraDict">查询条件集合</param>
        /// <returns>返回查询条件</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            if (this.txtPortCode.Text.Trim().Length != 0)
            {
                paraDict.Add("C_PORT_CODE", "%" + this.txtPortCode.Text.Trim() + "%");
            }
            if (this.txtPortName.Text.Trim().Length != 0)
            {
                paraDict.Add("C_PORT_NAME", "%" + this.txtPortName.Text + "%");

            }
            if (this.cboAssetType.Text.Trim().Length != 0)
            {
                if (this.cboAssetType.Value != null && this.cboAssetType.Value.Length != 0)
                {
                    paraDict.Add("C_DAT_CODE", cboAssetType.Value);
                }
            }
            if (this.txtAssetCode.Text.Trim().Length != 0)
            {
                // 资产组合
                paraDict.Add("C_ASS_CODE", "%" + this.txtAssetCode.Text.Trim() + "%");
            }
            if (this.cboPortLever.Text.Trim().Length != 0)
            {
                if (this.cboPortLever.Value != null && this.cboPortLever.Value.Length != 0)
                {
                    paraDict.Add("C_DV_PORT_CODE", cboPortLever.Value);
                }
            }
            //默认查询产品库中数据
            paraDict.Add("ARRAY_DV_PROD_STATE_NOTIN", " ");
            paraDict.Add("C_PORT_UNIT", "UNIT_LAYER");
            paraDict.Add("N_CHECK_STATE", "SearchAudit");
            return paraDict;
        }
    }
}
