using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.CRUD.Form;
using FAST.Core.Communication.Service;
using YssElecReco.Service.Bi;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using Yss.KTable.Models;
using YssElecReco.Pojo.Bi;
using FAST.Core.Util;
using FAST.Core.Exceptions;
using FAST.Core.CRUD.Interface;
using FAST.Core.BaseControl.Fun;
using System.Collections;

namespace YssElecReco.Form.Bi
{
    /// <summary>
    /// 个性指标
    /// </summary>
    public partial class Frm_ELEC_RELA_PER_L : FrmBaseList, IFormDetailData
    {
        /// <summary>
        /// 个性指标service
        /// </summary>
        private IElecPerRelaService service = null;

        /// <summary>
        /// 获取或设置一个值，该值为主窗体传过来的数据。当该窗体为某一窗体的附属窗体时有效。
        /// </summary>
        private BasePojo _mainDataPojo;

        /// <summary>
        /// 获取或设置当前窗体是否已经被FrmBaseListWidthDetails窗体关联为子窗体
        /// </summary>
        private bool _hadBeenRelationed = false;

        ////private bool isDetailForm = false;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_ELEC_RELA_PER_L()
        {
            InitializeComponent();
            this.bUseMVCService = true;
            this.ShowFilterPanel = true;
        }

        /// <summary>
        /// 获取或设置一个值，该值为主窗体传过来的数据。当该窗体为某一窗体的附属窗体时有效。
        /// </summary>
        public BasePojo MainDataPojo
        {
            get
            {
                return this._mainDataPojo;
            }

            set
            {
                if (this._mainDataPojo != value)
                {
                    this._mainDataPojo = value;
                }
            }
        }

        /// <summary>
        /// 获取或设置当前窗体是否已经被FrmBaseListWidthDetails窗体关联为子窗体。通过此属性可防止嵌套关联。
        /// </summary>
        public bool HadBeenRelationed
        {
            get
            {
                return _hadBeenRelationed;
            }

            set
            {
                _hadBeenRelationed = value;
            }
        }

        /// <summary>
        /// 窗体加载
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ELEC_RELA_PER_L_Load(object sender, EventArgs e)
        {
            service = ServiceFactory.createService<IElecPerRelaService>();
            isLoadFirst = true; ////首次打开加载数据
            ////BUG218422【对账指标关联】导致系统未响应
            ////this.ShowLeftPanel = false;
            this.setDefaultValue();
            this.YssOnBeforeRefreshClickMVC += new BeforeRefreshClickMVC(Frm_ELEC_RELA_PER_L_YssOnBeforeRefreshClickMVC);
            this.btnBar.IsSaveBtnEnabeld = true;
        }

        /// <summary>
        /// 查询前将所有单元格Innercontrol置为空
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ELEC_RELA_PER_L_YssOnBeforeRefreshClickMVC(object sender, FAST.Core.Context.Events.YssBeforeOperEventArgsMVC e)
        {
            setInnerControlNull();
        }

        /// <summary>
        /// 将表格的所有innerControl置为null，防止被回收
        /// </summary>
        private void setInnerControlNull()
        {
            for (int i = 0; i < this.tbMain.Rows.Count; i++)
            {
                for (int j = 0; j < this.tbMain.Rows[i].Cells.Count; j++)
                {
                    if (this.tbMain.Rows[i].Cells[j].InnerControl != null)
                    {
                        this.tbMain.Rows[i].Cells[j].InnerControl = null;
                    }
                }
            }
        }

        /// <summary>
        /// 下拉框默认值设置
        /// </summary>
        private void setDefaultValue()
        {
            this.cboIsSend.Value = "1"; ////默认为是
            this.cboZB1.Value = "SEND_ORGIN_VAL"; ////指标项默认选择发送
            this.cboZB2.Value = "SEND_ORGIN_VAL";
            this.cboZB3.Value = "SEND_ORGIN_VAL";
            this.cboZB4.Value = "SEND_ORGIN_VAL";
            this.cboZB5.Value = "SEND_ORGIN_VAL";
            this.cboZB6.Value = "SEND_ORGIN_VAL";
            this.cboZB7.Value = "SEND_ORGIN_VAL";
            this.cboZB8.Value = "SEND_ORGIN_VAL";
            this.cboZB9.Value = "SEND_ORGIN_VAL";
        }

        //public bool IsDetailForm
        //{
        //    get
        //    {
        //        return this.isDetailForm;
        //    }

        //    set
        //    {
        //        if (this.isDetailForm != value)
        //        {
        //            this.isDetailForm = value;
        //        }
        //    }
        //}


        /// <summary>
        /// 验证是否需要重新装载数据
        /// </summary>
        /// <param name="mainData">主表传过来的数据</param>
        /// <returns>返回验证结果</returns>
        public bool AllowReloadDetailData(BasePojo mainData)
        {
            bool retValue = false;
            if (mainData != null && mainData != this.MainDataPojo)
            {
                this.MainDataPojo = mainData;
                retValue = true;
            }

            //明细窗体忽略验证
            if (mainData != null && this.ParentForm is Frm_ELEC_RELA_PUB_L)
            {
                retValue = true;
            }

            return retValue;
        }

        /// <summary>
        /// 明细窗体初始化
        /// </summary>
        /// <param name="parent">FrmBaseListWithDetails父容器</param>
        public void InitializeDetailForm(FrmBaseListWithDetails parent)
        {
            this.sDllName = _formFun.YssAssocia.SetDllName;
            this.sSetClassName = _formFun.YssAssocia.SetFormName;
            this.sPojoClassName = _formFun.YssAssocia.PojoClsName;
            this.sPojoDllName = (_formFun.YssAssocia.PojoDllName != null && _formFun.YssAssocia.PojoDllName.Length > 0) ? _formFun.YssAssocia.PojoDllName : ClsFunction.getDllName(_formFun.YssAssocia.PojoClsName);
            if (_formFun != null)
            {
                this.Text = _formFun.C_FUN_NAME;
            }

            this.ShowLeftPanel = false;
            ////BUG212076指标关联设置下半页个性指标数据展示有误
            this.ShowFilterPanel = false;
        }

        /// <summary>
        /// 装载数据
        /// </summary>
        /// <param name="mainData">主表传过来的Pojo</param>
        public void LoadDetailData(BasePojo mainData)
        {
            //关联的list界面设置为明细窗体
            //(this.frmBaseViewSet as Frm_ELEC_RELA_PER_S).IsDetailForm = true;
            if (page == null)
            {
                page = new ClsPageInation();
            }

            page.CurrPage = 1;
            page.PageCount = 0;

            bool validate = this.AllowReloadDetailData(mainData);
            if (validate)
            {
                this.AllowResetGeneParaAssemble = false;
                this.geneParaAssemble.Clear();
                this.geneParaAssemble.Add("dataClass", "ElecPerRela");
                this.geneParaAssemble.Add("C_ZB_CODE", (mainData as ElecRela).C_ZB_CODE);
                this.geneParaAssemble.Add("C_ZB_NAME", (mainData as ElecRela).C_ZB_NAME);
                this.geneParaAssemble.Add("ARRAY_C_DZ_CODE", (mainData as ElecRela).C_DZ_CODE);
                ////验证通过，开始装载数据
                this.btnSearch_Click(this.btnBar.getButton(ClsButtonName.BtnRefresh).Owner, EventArgs.Empty);

            }
        }

        /// <summary>m
        /// 封装条件对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            //BUG212076指标关联设置下半页个性指标数据展示有误
            //作为公共指标的子窗体时
            if (this.MainDataPojo != null && this.ParentForm is Frm_ELEC_RELA_PUB_L)
            {
                paraDict.Add("dataClass", "ElecPerRela");
                paraDict.Add("C_ZB_CODE", (this.MainDataPojo as ElecRela).C_ZB_CODE);
                paraDict.Add("C_ZB_NAME", (this.MainDataPojo as ElecRela).C_ZB_NAME);
                paraDict.Add("ARRAY_C_DZ_CODE", (this.MainDataPojo as ElecRela).C_DZ_CODE);
            }
            else
            { ////做主窗体时
                if (this.txtZBCode.Text.Trim().Length > 0)
                {
                    paraDict.Add("C_ZB_CODE", "%" + this.txtZBCode.Text.Trim() + "%");
                }

                if (this.txtZbName.Text.Trim().Length > 0)
                {
                    paraDict.Add("C_ZB_NAME", "%" + this.txtZbName.Text.Trim() + "%");
                }

                if (this.cboDzType.Text.Trim().Length > 0)
                {
                    paraDict.Add("ARRAY_C_DZ_CODE", this.cboDzType.Value.Trim());
                }
                ////BUG218422【对账指标关联】导致系统未响应
                ////if (this.ParentForm != null && this.ParentForm is FrmBaseList)
                ////{
                    string search = this.yssBuildLeftCheckRowsStr("portfolio");
                    search = search.Replace("'", "");
                    paraDict.Add("ARRAY_C_PORT_CODE", search);
                ////}
            }

            return paraDict;
        }

        /// <summary>
        /// 重写保存事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnSave_Click(object sender, EventArgs e)
        {
            //保存前先将表格的innerControl置为null
            setInnerControlNull();
            ArrayList pojoList = new ArrayList();
            for (int i = 0; i < this.tbMain.CheckedRows.Count; i++)
            {
                pojoList.Add((BasePojo)tbMain.CheckedRows[i].Tag);
            }

            if (pojoList.Count <= 0)
            {
                Yss.KMessage.MessageDialog frm = new Yss.KMessage.MessageDialog();
                frm.Show("您未勾选需要保存的指标信息，请勾选！");
                return;
            }

            service.updateById(pojoList);
            if (this.ParentForm is Frm_ELEC_RELA_PUB_L)
            {
                this.LoadDetailData(this._mainDataPojo);
            }
            else
            {
                this.btnSearch_Click(sender, e);
            }
        }

        /// <summary>
        /// 审核前将InnerControl 置空
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnAudit_Click(object sender, EventArgs e)
        {
            setInnerControlNull();
            base.btnAudit_Click(sender, e);
        }

        /// <summary>
        /// 反审核前将InnerControl 置空
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnUnAudit_Click(object sender, EventArgs e)
        {
            setInnerControlNull();
            base.btnUnAudit_Click(sender, e);
        }

        /// <summary>
        /// 是否发送下拉框
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboIsSend_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.cboIsSend.Tag != null && cboIsSend.Tag is Cell)
            {
                Cell tempCell = cboIsSend.Tag as Cell;
                (tempCell.OwnRow.Tag as ElecPerRela).C_SEND_MODE = cboIsSend.Value;
                tempCell.Text = cboIsSend.Text;

                if (this.tbMain.CheckedRows.Count > 0)
                {
                    if (this.tbMain.CheckedRows.Count == 1 && this.tbMain.CheckedRows[0] == tempCell.OwnRow)
                    {
                        return;
                    }

                    ////如果选中多行并且对账类型不一致，就提示
                    if (!check())
                    {
                        Yss.KMessage.MessageDialog frm = new Yss.KMessage.MessageDialog();
                        frm.Show("您勾选的指标<对账类型>不一致，只修改当前操作的指标！");
                        return;
                    }

                    if (YssMessageBox.ShowQuestion("是否将此操作同步到其他已选纪录？", "系统提示", MessageBoxButtons.YesNo) == DialogResult.Yes)
                    {
                        foreach (Row row in this.tbMain.CheckedRows)
                        {
                            if (row.IsGroup)
                            {
                                continue;
                            }

                            row.Cells[tbMain.Columns["C_SEND_MODE"].Index].Text = cboIsSend.Text;
                            row.Cells[tbMain.Columns["C_SEND_MODE"].Index].Text = cboIsSend.Text;
                            (row.Tag as ElecPerRela).C_SEND_MODE = cboIsSend.Value;
                        }
                    }

                    this.Focus();
                }
            }
            ////setInterControlNull();
        }

        /// <summary>
        /// 判断选中行是否是同一对账类型
        /// </summary>
        /// <returns>bool</returns>
        private bool check()
        {
            List<string> list = new List<string>();
            for (int i = 0; i < this.tbMain.CheckedRows.Count; i++)
            {
                string cDzCode = (this.tbMain.CheckedRows[i].Tag as ElecPerRela).C_DZ_CODE;
                if (list.Count == 0)
                {
                    list.Add(cDzCode);
                }

                if (!list.Contains(cDzCode))
                {
                    return false;
                }

            }

            return true;
        }

        /// <summary>
        /// 指标下拉框
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboZB1_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.cboZB1.Tag != null && cboZB1.Tag is Cell)
            {
                Cell tempCell = cboZB1.Tag as Cell;
                (tempCell.OwnRow.Tag as ElecPerRela).C_ZB_VALUE1 = cboZB1.Value;
                tempCell.Text = cboZB1.Text;

                if (this.tbMain.CheckedRows.Count > 0)
                {
                    if (this.tbMain.CheckedRows.Count == 1 && this.tbMain.CheckedRows[0] == tempCell.OwnRow)
                    {
                        return;
                    }

                    ////如果选中多行并且对账类型不一致，就提示
                    if (!check())
                    {
                        Yss.KMessage.MessageDialog frm = new Yss.KMessage.MessageDialog();
                        frm.Show("您勾选的指标<对账类型>不一致，只修改当前操作的指标！");
                        return;
                    }

                    if (YssMessageBox.ShowQuestion("是否将此操作同步到其他已选纪录？", "系统提示", MessageBoxButtons.YesNo) == DialogResult.Yes)
                    {
                        foreach (Row row in this.tbMain.CheckedRows)
                        {
                            if (row.IsGroup)
                            {
                                continue;
                            }

                            row.Cells[tbMain.Columns["C_ZB_VALUE1"].Index].Text = cboZB1.Text;
                            (row.Tag as ElecPerRela).C_ZB_VALUE1 = cboZB1.Value;
                        }
                    }

                    this.Focus();
                }
            }
        }

        /// <summary>
        /// 指标下拉框
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboZB2_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.cboZB2.Tag != null && cboZB2.Tag is Cell)
            {
                Cell tempCell = cboZB2.Tag as Cell;
                (tempCell.OwnRow.Tag as ElecPerRela).C_ZB_VALUE2 = cboZB2.Value;
                tempCell.Text = cboZB2.Text;

                if (this.tbMain.CheckedRows.Count > 0)
                {
                    if (this.tbMain.CheckedRows.Count == 1 && this.tbMain.CheckedRows[0] == tempCell.OwnRow)
                    {
                        return;
                    }

                    ////如果选中多行并且对账类型不一致，就提示
                    if (!check())
                    {
                        Yss.KMessage.MessageDialog frm = new Yss.KMessage.MessageDialog();
                        frm.Show("您勾选的指标<对账类型>不一致，只修改当前操作的指标！");
                        return;
                    }

                    if (YssMessageBox.ShowQuestion("是否将此操作同步到其他已选纪录？", "系统提示", MessageBoxButtons.YesNo) == DialogResult.Yes)
                    {
                        foreach (Row row in this.tbMain.CheckedRows)
                        {
                            if (row.IsGroup)
                            {
                                continue;
                            }

                            row.Cells[tbMain.Columns["C_ZB_VALUE2"].Index].Text = cboZB2.Text;
                            (row.Tag as ElecPerRela).C_ZB_VALUE2 = cboZB2.Value;
                        }
                    }

                    this.Focus();
                }
            }
        }

        /// <summary>
        /// 指标下拉框
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboZB3_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.cboZB3.Tag != null && cboZB3.Tag is Cell)
            {
                Cell tempCell = cboZB3.Tag as Cell;
                (tempCell.OwnRow.Tag as ElecPerRela).C_ZB_VALUE3 = cboZB3.Value;
                tempCell.Text = cboZB3.Text;

                if (this.tbMain.CheckedRows.Count > 0)
                {
                    if (this.tbMain.CheckedRows.Count == 1 && this.tbMain.CheckedRows[0] == tempCell.OwnRow)
                    {
                        return;
                    }

                    ////如果选中多行并且对账类型不一致，就提示
                    if (!check())
                    {
                        Yss.KMessage.MessageDialog frm = new Yss.KMessage.MessageDialog();
                        frm.Show("您勾选的指标<对账类型>不一致，只修改当前操作的指标！");
                        return;
                    }

                    if (YssMessageBox.ShowQuestion("是否将此操作同步到其他已选纪录？", "系统提示", MessageBoxButtons.YesNo) == DialogResult.Yes)
                    {
                        foreach (Row row in this.tbMain.CheckedRows)
                        {
                            if (row.IsGroup)
                            {
                                continue;
                            }

                            row.Cells[tbMain.Columns["C_ZB_VALUE3"].Index].Text = cboZB3.Text;
                            (row.Tag as ElecPerRela).C_ZB_VALUE3 = cboZB3.Value;
                        }
                    }

                    this.Focus();
                }
            }
        }

        /// <summary>
        /// 指标下拉框
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboZB4_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.cboZB4.Tag != null && cboZB4.Tag is Cell)
            {
                Cell tempCell = cboZB4.Tag as Cell;
                (tempCell.OwnRow.Tag as ElecPerRela).C_ZB_VALUE4 = cboZB4.Value;
                tempCell.Text = cboZB4.Text;

                if (this.tbMain.CheckedRows.Count > 0)
                {
                    if (this.tbMain.CheckedRows.Count == 1 && this.tbMain.CheckedRows[0] == tempCell.OwnRow)
                    {
                        return;
                    }

                    ////如果选中多行并且对账类型不一致，就提示
                    if (!check())
                    {
                        Yss.KMessage.MessageDialog frm = new Yss.KMessage.MessageDialog();
                        frm.Show("您勾选的指标<对账类型>不一致，只修改当前操作的指标！");
                        return;
                    }

                    if (YssMessageBox.ShowQuestion("是否将此操作同步到其他已选纪录？", "系统提示", MessageBoxButtons.YesNo) == DialogResult.Yes)
                    {
                        foreach (Row row in this.tbMain.CheckedRows)
                        {
                            if (row.IsGroup)
                            {
                                continue;
                            }

                            row.Cells[tbMain.Columns["C_ZB_VALUE4"].Index].Text = cboZB4.Text;
                            (row.Tag as ElecPerRela).C_ZB_VALUE4 = cboZB4.Value;
                        }
                    }

                    this.Focus();
                }
            }
        }

        /// <summary>
        /// 指标下拉框
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboZB5_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.cboZB5.Tag != null && cboZB5.Tag is Cell)
            {
                Cell tempCell = cboZB5.Tag as Cell;
                (tempCell.OwnRow.Tag as ElecPerRela).C_ZB_VALUE5 = cboZB5.Value;
                tempCell.Text = cboZB5.Text;

                if (this.tbMain.CheckedRows.Count > 0)
                {
                    if (this.tbMain.CheckedRows.Count == 1 && this.tbMain.CheckedRows[0] == tempCell.OwnRow)
                    {
                        return;
                    }

                    ////如果选中多行并且对账类型不一致，就提示
                    if (!check())
                    {
                        Yss.KMessage.MessageDialog frm = new Yss.KMessage.MessageDialog();
                        frm.Show("您勾选的指标<对账类型>不一致，只修改当前操作的指标！");
                        return;
                    }

                    if (YssMessageBox.ShowQuestion("是否将此操作同步到其他已选纪录？", "系统提示", MessageBoxButtons.YesNo) == DialogResult.Yes)
                    {
                        foreach (Row row in this.tbMain.CheckedRows)
                        {
                            if (row.IsGroup)
                            {
                                continue;
                            }

                            row.Cells[tbMain.Columns["C_ZB_VALUE5"].Index].Text = cboZB5.Text;
                            (row.Tag as ElecPerRela).C_ZB_VALUE5 = cboZB5.Value;
                        }
                    }

                    this.Focus();
                }
            }
        }

        /// <summary>
        /// 指标下拉框
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboZB6_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.cboZB6.Tag != null && cboZB6.Tag is Cell)
            {
                Cell tempCell = cboZB6.Tag as Cell;
                (tempCell.OwnRow.Tag as ElecPerRela).C_ZB_VALUE6 = cboZB6.Value;
                tempCell.Text = cboZB6.Text;

                if (this.tbMain.CheckedRows.Count > 0)
                {
                    if (this.tbMain.CheckedRows.Count == 1 && this.tbMain.CheckedRows[0] == tempCell.OwnRow)
                    {
                        return;
                    }

                    ////如果选中多行并且对账类型不一致，就提示
                    if (!check())
                    {
                        Yss.KMessage.MessageDialog frm = new Yss.KMessage.MessageDialog();
                        frm.Show("您勾选的指标<对账类型>不一致，只修改当前操作的指标！");
                        return;
                    }

                    if (YssMessageBox.ShowQuestion("是否将此操作同步到其他已选纪录？", "系统提示", MessageBoxButtons.YesNo) == DialogResult.Yes)
                    {
                        foreach (Row row in this.tbMain.CheckedRows)
                        {
                            if (row.IsGroup)
                            {
                                continue;
                            }

                            row.Cells[tbMain.Columns["C_ZB_VALUE6"].Index].Text = cboZB6.Text;
                            (row.Tag as ElecPerRela).C_ZB_VALUE6 = cboZB6.Value;
                        }
                    }

                    this.Focus();
                }
            }
        }

        /// <summary>
        /// 指标下拉框
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboZB7_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.cboZB7.Tag != null && cboZB7.Tag is Cell)
            {
                Cell tempCell = cboZB7.Tag as Cell;
                (tempCell.OwnRow.Tag as ElecPerRela).C_ZB_VALUE7 = cboZB7.Value;
                tempCell.Text = cboZB7.Text;

                if (this.tbMain.CheckedRows.Count > 0)
                {
                    if (this.tbMain.CheckedRows.Count == 1 && this.tbMain.CheckedRows[0] == tempCell.OwnRow)
                    {
                        return;
                    }

                    ////如果选中多行并且对账类型不一致，就提示
                    if (!check())
                    {
                        Yss.KMessage.MessageDialog frm = new Yss.KMessage.MessageDialog();
                        frm.Show("您勾选的指标<对账类型>不一致，只修改当前操作的指标！");
                        return;
                    }

                    if (YssMessageBox.ShowQuestion("是否将此操作同步到其他已选纪录？", "系统提示", MessageBoxButtons.YesNo) == DialogResult.Yes)
                    {
                        foreach (Row row in this.tbMain.CheckedRows)
                        {
                            if (row.IsGroup)
                            {
                                continue;
                            } 

                            row.Cells[tbMain.Columns["C_ZB_VALUE7"].Index].Text = cboZB7.Text;
                            (row.Tag as ElecPerRela).C_ZB_VALUE7 = cboZB7.Value;
                        }
                    }

                    this.Focus();
                }
            }
        }

        /// <summary>
        /// 指标下拉框
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboZB8_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.cboZB8.Tag != null && cboZB8.Tag is Cell)
            {
                Cell tempCell = cboZB8.Tag as Cell;
                (tempCell.OwnRow.Tag as ElecPerRela).C_ZB_VALUE8 = cboZB8.Value;
                tempCell.Text = cboZB8.Text;

                if (this.tbMain.CheckedRows.Count > 0)
                {
                    if (this.tbMain.CheckedRows.Count == 1 && this.tbMain.CheckedRows[0] == tempCell.OwnRow)
                    {
                        return;
                    }

                    ////如果选中多行并且对账类型不一致，就提示
                    if (!check())
                    {
                        Yss.KMessage.MessageDialog frm = new Yss.KMessage.MessageDialog();
                        frm.Show("您勾选的指标<对账类型>不一致，只修改当前操作的指标！");
                        return;
                    }

                    if (YssMessageBox.ShowQuestion("是否将此操作同步到其他已选纪录？", "系统提示", MessageBoxButtons.YesNo) == DialogResult.Yes)
                    {
                        foreach (Row row in this.tbMain.CheckedRows)
                        {
                            if (row.IsGroup)
                            {
                                continue;
                            }
                                
                            row.Cells[tbMain.Columns["C_ZB_VALUE8"].Index].Text = cboZB8.Text;
                            (row.Tag as ElecPerRela).C_ZB_VALUE8 = cboZB8.Value;
                        }
                    }

                    this.Focus();
                }
            }
        }

        /// <summary>
        /// 指标下拉框
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboZB9_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.cboZB9.Tag != null && cboZB9.Tag is Cell)
            {
                Cell tempCell = cboZB9.Tag as Cell;
                (tempCell.OwnRow.Tag as ElecPerRela).C_ZB_VALUE9 = cboZB9.Value;
                tempCell.Text = cboZB9.Text;

                if (this.tbMain.CheckedRows.Count > 0)
                {
                    if (this.tbMain.CheckedRows.Count == 1 && this.tbMain.CheckedRows[0] == tempCell.OwnRow)
                    {
                        return;
                    }

                    ////如果选中多行并且对账类型不一致，就提示
                    if (!check())
                    {
                        Yss.KMessage.MessageDialog frm = new Yss.KMessage.MessageDialog();
                        frm.Show("您勾选的指标<对账类型>不一致，只修改当前操作的指标！");
                        return;
                    }

                    if (YssMessageBox.ShowQuestion("是否将此操作同步到其他已选纪录？", "系统提示", MessageBoxButtons.YesNo) == DialogResult.Yes)
                    {
                        foreach (Row row in this.tbMain.CheckedRows)
                        {
                            if (row.IsGroup)
                            {
                                continue;
                            }

                            row.Cells[tbMain.Columns["C_ZB_VALUE9"].Index].Text = cboZB9.Text;
                            (row.Tag as ElecPerRela).C_ZB_VALUE9 = cboZB9.Value;
                        }
                    }

                    this.Focus();
                }
            }
        }

        /// <summary>
        /// 单元格事件--用于动态加载编辑控件
        /// </summary>
        /// <param name="sender">Table</param>
        /// <param name="e">事件参数</param>
        private void tbMain_CellMouseClick(object sender, Yss.KTable.Events.CellEventArgs e)
        {
            if (tbMain.ReadOnly == true)
            {
                return;
            }

            try
            {
                e.Cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowOnClick;

                if (e.Cell.RelColumn.DataPropertyName == "C_SEND_MODE")
                {
                    if (this.cboIsSend.Tag is Cell)
                    {
                        Cell tempCell = this.cboIsSend.Tag as Cell;
                        if (tempCell != e.Cell)
                        {
                            tempCell.InnerControl = null;
                        }
                    }

                    e.Cell.InnerControl = this.cboIsSend;
                    e.Cell.InnerControl.Tag = e.Cell;
                    this.cboIsSend.SelectedValueChanged -= this.cboIsSend_SelectedValueChanged;
                    this.cboIsSend.Value = (e.Row.Tag as ElecPerRela).C_SEND_MODE;
                    this.cboIsSend.SelectedValueChanged += this.cboIsSend_SelectedValueChanged;
                }
                else if (e.Cell.RelColumn.DataPropertyName == "C_ZB_VALUE1")
                {
                    if (this.cboZB1.Tag is Cell)
                    {
                        Cell tempCell = this.cboZB1.Tag as Cell;
                        if (tempCell != e.Cell)
                        {
                            tempCell.InnerControl = null;
                        }
                    }

                    e.Cell.InnerControl = this.cboZB1;
                    e.Cell.InnerControl.Tag = e.Cell;
                    this.cboZB1.SelectedValueChanged -= this.cboZB1_SelectedValueChanged;
                    this.cboZB1.Value = (e.Row.Tag as ElecPerRela).C_ZB_VALUE1;
                    this.cboZB1.SelectedValueChanged += this.cboZB1_SelectedValueChanged;
                }
                else if (e.Cell.RelColumn.DataPropertyName == "C_ZB_VALUE2")
                {
                    if (this.cboZB2.Tag is Cell)
                    {
                        Cell tempCell = this.cboZB2.Tag as Cell;
                        if (tempCell != e.Cell)
                        {
                            tempCell.InnerControl = null;
                        }
                    }

                    e.Cell.InnerControl = this.cboZB2;
                    e.Cell.InnerControl.Tag = e.Cell;
                    this.cboZB2.SelectedValueChanged -= this.cboZB2_SelectedValueChanged;
                    this.cboZB2.Value = (e.Row.Tag as ElecPerRela).C_ZB_VALUE2;
                    this.cboZB2.SelectedValueChanged += this.cboZB2_SelectedValueChanged;
                }
                else if (e.Cell.RelColumn.DataPropertyName == "C_ZB_VALUE3")
                {
                    if (this.cboZB3.Tag is Cell)
                    {
                        Cell tempCell = this.cboZB3.Tag as Cell;
                        if (tempCell != e.Cell)
                        {
                            tempCell.InnerControl = null;
                        }
                    }

                    e.Cell.InnerControl = this.cboZB3;
                    e.Cell.InnerControl.Tag = e.Cell;
                    this.cboZB3.SelectedValueChanged -= this.cboZB3_SelectedValueChanged;
                    this.cboZB3.Value = (e.Row.Tag as ElecPerRela).C_ZB_VALUE3;
                    this.cboZB3.SelectedValueChanged += this.cboZB3_SelectedValueChanged;
                }
                else if (e.Cell.RelColumn.DataPropertyName == "C_ZB_VALUE4")
                {
                    if (this.cboZB4.Tag is Cell)
                    {
                        Cell tempCell = this.cboZB4.Tag as Cell;
                        if (tempCell != e.Cell)
                        {
                            tempCell.InnerControl = null;
                        }
                    }

                    e.Cell.InnerControl = this.cboZB4;
                    e.Cell.InnerControl.Tag = e.Cell;
                    this.cboZB4.SelectedValueChanged -= this.cboZB4_SelectedValueChanged;
                    this.cboZB4.Value = (e.Row.Tag as ElecPerRela).C_ZB_VALUE4;
                    this.cboZB4.SelectedValueChanged += this.cboZB4_SelectedValueChanged;
                }
                else if (e.Cell.RelColumn.DataPropertyName == "C_ZB_VALUE5")
                { ////add by zzk 20150227 深交所揭示净值文件 传送席位
                    if (this.cboZB5.Tag is Cell)
                    {
                        Cell tempCell = this.cboZB5.Tag as Cell;
                        if (tempCell != e.Cell)
                        {
                            tempCell.InnerControl = null;
                        }
                    }

                    e.Cell.InnerControl = this.cboZB5;
                    e.Cell.InnerControl.Tag = e.Cell;
                    this.cboZB5.SelectedValueChanged -= this.cboZB5_SelectedValueChanged;
                    this.cboZB5.Value = (e.Row.Tag as ElecPerRela).C_ZB_VALUE5;
                    this.cboZB5.SelectedValueChanged += this.cboZB5_SelectedValueChanged;
                }
                else if (e.Cell.RelColumn.DataPropertyName == "C_ZB_VALUE6")
                {
                    if (this.cboZB6.Tag is Cell)
                    {
                        Cell tempCell = this.cboZB6.Tag as Cell;
                        if (tempCell != e.Cell)
                        {
                            tempCell.InnerControl = null;
                        }
                    }

                    e.Cell.InnerControl = this.cboZB6;
                    e.Cell.InnerControl.Tag = e.Cell;
                    this.cboZB6.SelectedValueChanged -= this.cboZB6_SelectedValueChanged;
                    this.cboZB6.Value = (e.Row.Tag as ElecPerRela).C_ZB_VALUE6;
                    this.cboZB6.SelectedValueChanged += this.cboZB6_SelectedValueChanged;
                }
                else if (e.Cell.RelColumn.DataPropertyName == "C_ZB_VALUE7")
                {
                    if (this.cboZB7.Tag is Cell)
                    {
                        Cell tempCell = this.cboZB7.Tag as Cell;
                        if (tempCell != e.Cell)
                        {
                            tempCell.InnerControl = null;
                        }
                    }

                    e.Cell.InnerControl = this.cboZB7;
                    e.Cell.InnerControl.Tag = e.Cell;
                    this.cboZB7.SelectedValueChanged -= this.cboZB7_SelectedValueChanged;
                    this.cboZB7.Value = (e.Row.Tag as ElecPerRela).C_ZB_VALUE7;
                    this.cboZB7.SelectedValueChanged += this.cboZB7_SelectedValueChanged;
                }
                else if (e.Cell.RelColumn.DataPropertyName == "C_ZB_VALUE8")
                {
                    if (this.cboZB8.Tag is Cell)
                    {
                        Cell tempCell = this.cboZB8.Tag as Cell;
                        if (tempCell != e.Cell)
                        {
                            tempCell.InnerControl = null;
                        }
                    }

                    e.Cell.InnerControl = this.cboZB8;
                    e.Cell.InnerControl.Tag = e.Cell;
                    this.cboZB8.SelectedValueChanged -= this.cboZB8_SelectedValueChanged;
                    this.cboZB8.Value = (e.Row.Tag as ElecPerRela).C_ZB_VALUE8;
                    this.cboZB8.SelectedValueChanged += this.cboZB8_SelectedValueChanged;
                }
                else if (e.Cell.RelColumn.DataPropertyName == "C_ZB_VALUE9")
                {
                    if (this.cboZB9.Tag is Cell)
                    {
                        Cell tempCell = this.cboZB9.Tag as Cell;
                        if (tempCell != e.Cell)
                        {
                            tempCell.InnerControl = null;
                        }
                    }

                    e.Cell.InnerControl = this.cboZB9;
                    e.Cell.InnerControl.Tag = e.Cell;
                    this.cboZB9.SelectedValueChanged += this.cboZB9_SelectedValueChanged;
                    this.cboZB9.Value = (e.Row.Tag as ElecPerRela).C_ZB_VALUE9;
                    this.cboZB9.SelectedValueChanged += this.cboZB9_SelectedValueChanged;
                }
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 新增后点击后事件处理
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ELEC_RELA_PER_L_YssOnAfterNewClick(object sender, EventArgs e)
        {
            if (_mainDataPojo == null)
            {
                (this.frmBaseViewSet as Frm_ELEC_RELA_PER_S).IsDetailForm = false;
                return;
            }

            if (this.ParentForm is Frm_ELEC_RELA_PUB_L)
            {
                (this.frmBaseViewSet as Frm_ELEC_RELA_PER_S).IsDetailForm = true;
                (this.frmBaseViewSet as Frm_ELEC_RELA_PER_S).ZBId = _mainDataPojo == null ? "" : (_mainDataPojo as ElecRela).Id;
            }
            else
            {
                (this.frmBaseViewSet as Frm_ELEC_RELA_PER_S).IsDetailForm = false;
                (this.frmBaseViewSet as Frm_ELEC_RELA_PER_S).ZBId = "";
            }

            (this.frmBaseViewSet as Frm_ELEC_RELA_PER_S).setZBCodeState();
        }

        /// <summary>
        /// 指标名称改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void txtZbName_TextChanged(object sender, EventArgs e)
        {
            //首先将selCombox中元素清空
            (sender as FAST.Core.BaseControl.YssSelCombox).Items.Clear();
            string cZbName = (sender as FAST.Core.BaseControl.YssSelCombox).Text;
            List<string> paraList = new List<string>();
            if (!string.IsNullOrEmpty(cZbName))
            {
                string queryCon = queryCon = "%" + cZbName + "%";
                paraList.Add(queryCon);
            }

            IElecRelaService service = ServiceFactory.createService<IElecRelaService>();
            List<BasePojo> lDzRelaList = service.getDataListByName(paraList);
            if (null != lDzRelaList && lDzRelaList.Count > 0)
            {
                foreach (BasePojo pojo in lDzRelaList)
                {
                    if (pojo is ElecRela)
                    {
                        Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(pojo as ElecRela);
                        (sender as FAST.Core.BaseControl.YssSelCombox).Items.Add(entity);
                    }
                }
            }
        }
    }
}
