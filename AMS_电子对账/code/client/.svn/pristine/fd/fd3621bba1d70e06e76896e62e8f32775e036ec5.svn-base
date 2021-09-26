using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using Yss.KTable.Models;
using FAST.Core.CRUD.Form;
using YssElecReco.Service.Para;
using FAST.Common.Service.Pojo.Base;
using System.Collections;
using FAST.Core.Util;
using FAST.Core.Exceptions;
using System.Windows.Forms;
using Yss.KTable.Collections;
using FAST.Core.Context;
using FAST.Core.BaseControl;
using YssElecReco.Pojo.Para;
using Yss.KRichEx;
using FAST.Common.Service.Pojo;
using YssElecReco.Context;
using FAST.Core.Context.Events;
using FAST.Core.Resource;
using FAST.Core.BaseControl.Fun;
using FAST.Core.BaseControl.Pojo;
using YssElecReco.Fun;

namespace YssElecReco.Form.Para
{
    public partial class Frm_ELEC_DSP_PARA_L : FrmBaseList
    {
        private YssSelCombox _cboPortCls;
        private YssSelCombox _cboValue;
        private YssDateTimeInterval _dtpValue;
        private YssTextBox _txtValue;
        private IntegerInputEx _intValue;

        private ClsRetInfo retInfo = null;
        /// <summary>
        /// 控件销毁重建
        /// </summary>
        private YssSelCombox CboPortCls
        {
            get
            {
                if (this._cboPortCls == null || this._cboPortCls.IsDisposed)
                {
                    initPortCls();
                }
                return this._cboPortCls;
            }
        }
        /// <summary>
        /// 控件销毁重建
        /// </summary>
        private YssDateTimeInterval DtpValue
        {
            get {
                if (this._dtpValue == null || this._dtpValue.IsDisposed)
                {
                    this._dtpValue = new YssDateTimeInterval();
                    this._dtpValue.FirstdateTimeInputValueChanged += new EventHandler(this.innerControl_ValueChanged);
                }
                return this._dtpValue;
            }
        }
        /// <summary>
        /// 控件销毁重建
        /// </summary>
        private YssTextBox TxtValue
        {
            get
            {
                if (this._txtValue == null || this._txtValue.IsDisposed)
                {
                    this._txtValue = new YssTextBox();
                    this._txtValue.TextChanged += new EventHandler(this.innerControl_ValueChanged);
                }
                return this._txtValue;
            }
        }
        /// <summary>
        /// 控件销毁重建
        /// </summary>
        private IntegerInputEx IntValue
        {
            get
            {
                if (this._intValue == null || this._intValue.IsDisposed)
                {
                    this._intValue = new IntegerInputEx();
                    this._intValue.Prefix = "";
                    this._intValue.Sufix = "";
                    this._intValue.ValueChanged += new EventHandler(this.innerControl_ValueChanged);
                }
                return this._intValue;
            }
        }
        /// <summary>
        /// 控件销毁重建
        /// </summary>
        private YssSelCombox CboValue
        {
            get
            {
                if (this._cboValue == null || this._cboValue.IsDisposed)
                {
                    this._cboValue = new YssSelCombox();
                    this._cboValue.SelectedValueChanged += new EventHandler(this.innerControl_ValueChanged);
                }
                return this._cboValue;
            }
        }

        public Frm_ELEC_DSP_PARA_L()
        {
            InitializeComponent();
            bUseMVCService = true;
            bUseMVCServiceLeft = true;
            //群组是否验证标识
            isValidGroupPortCode = false;
            initComponent();
        }

        /// <summary>
        /// STORY #103238 富国基金-电子对账综合参数界面查询条件增加参数名称 
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboParaName_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            e.Cancel = true;
            this.cboParaName.Items.Clear();
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("dataClass", "ErDspManager");
            try
            {
                string search = this.yssBuildLeftCheckRowsStr("portfolio");
                search = search.Replace("'", "");
                if (search.Contains(','))
                {
                    search = search.Split(',')[0];
                }
                paraDict.Add("ARRAY_C_PORT_CODE", search);
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500037", _formFun, status));
                ClsBaseException.DiscardException(ex);
            }

            QueryRes res = (dataService as IErDspManagerService).queryByCondition(paraDict);
            if (null != res && res.DataList != null)
            {
                List<BasePojo> dataList = res.DataList;
                List<string> codeList = new List<string>();
                foreach (BasePojo pojo in dataList)
                {
                    ErDspManager p = pojo as ErDspManager;
                    if (!codeList.Contains(p.C_DSP_CODE))
                    {
                        codeList.Add(p.C_DSP_CODE);
                        cboParaName.Items.Add(new Yss.KRichEx.AutoFilter.Model.KTableEntity(pojo));
                    }
                }
            }
        }

        public void initComponent()
        {
            ////添加保存按钮
            addSaveButton();
            this.btnBar.setButtonDisabled("SaveButton");
        }

        private void addSaveButton()
        {
            Dictionary<string, List<string>> dic = btnBar.getAllButtonNames();
            
            if (!dic.Keys.Contains("SaveButton"))
            {
                ClsButtonInfo button = new ClsButtonInfo();
                button.Name = "SaveButton";
                button.Tooltip = "保存";
                button.Text = "保存";
                button.Enabled = false;
                button.Image = FAST.Resource.Resource.btnSave_L;
                button.ClickEvent += new System.EventHandler(this.btnSaveButton_Click);
                this.btnBar.addButton(button, 10);
                this.btnBar.setButtonEnabled(button.Name, true);
            }

        }


        public void initPortCls()
        {
            ////this._cboPortCls = new YssSelCombox();
            ////this._cboPortCls.Border.Bottom = true;
            ////this._cboPortCls.Border.Left = true;
            ////this._cboPortCls.Border.Right = true;
            ////this._cboPortCls.Border.Top = true;
            ////this._cboPortCls.ShowCheckBox = false;
            ////this._cboPortCls.DisplayName = "C_PORT_CLS_NAME";
            ////this._cboPortCls.DisplayValue = "C_PORT_CLS_CODE";
            ////this._cboPortCls.Name = "_cboPortCls";
            ////this._cboPortCls.NodeID = "C_PORT_CLS_CODE";
            ////this._cboPortCls.Parameter = "C_PORT_CLS_CODE~C_PORT_CLS_NAME";
            ////this._cboPortCls.ParaNodeID = "C_PORT_CLS_CODE_P";
            ////this._cboPortCls.SortColumn = "C_PORT_CLS_NAME";
            ////this._cboPortCls.YssAssociaType = Associa.Generate("productgrade");
            ////this._cboPortCls.YssOnBeforeLoadData += new FAST.Core.BaseControl.YssSelCombox.yssBeforeLoadData(this.cboPortCls_YssOnBeforeLoadData);
            ////this._cboPortCls.SelectedValueChanged += new EventHandler(this.innerControl_ValueChanged);

            this._cboPortCls = new YssSelCombox();
            this._cboPortCls.Border.Bottom = true;
            this._cboPortCls.Border.Left = true;
            this._cboPortCls.Border.Right = true;
            this._cboPortCls.Border.Top = true;
            this._cboPortCls.ShowCheckBox = false;
            this._cboPortCls.DisplayName = "C_PORT_CLS_NAME";
            this._cboPortCls.DisplayValue = "C_PORT_CLS_CODE";
            this._cboPortCls.Name = "_cboPortCls";
            this._cboPortCls.NodeID = "C_PORT_CLS_CODE";
            this._cboPortCls.Parameter = "C_PORT_CLS_CODE~C_PORT_CLS_NAME";
            this._cboPortCls.ParaNodeID = "C_PORT_CLS_CODE_P";
            this._cboPortCls.SortColumn = "C_PORT_CLS_NAME";
            this._cboPortCls.YssAssociaType = Associa.Generate("productgrade");
            this._cboPortCls.YssOnBeforeLoadData += new FAST.Core.BaseControl.YssSelCombox.yssBeforeLoadData(this.cboPortCls_YssOnBeforeLoadData);
            this._cboPortCls.SelectedValueChanged += new System.EventHandler(this.innerControl_ValueChanged);
        }

        /// <summary>
        /// 分级组合加载前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboPortCls_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            ////FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo = new FAST.Core.BaseControl.ControlMethodInfo();
            ////controlMethodInfo.MethodName = "getDataListByTypes";
            ////////获取当前行的组合代码
            ////string portCode = ((tbMain.SelectedRow.Tag as Row).Tag as ErDspManager).C_PORT_CODE;
            ////controlMethodInfo.MethodParamValues = new string[] { portCode + "," };
            ////this.CboPortCls.MethodInfo = controlMethodInfo;


            ////如果当前状态为修改状态。则分级组合不以复选框的形式展示。
            ////if (isEdit)
            ////{
            ////    this.CboPortCls.ShowCheckBox = false;
            ////}
            ////else
            ////{
            ////    this.CboPortCls.ShowCheckBox = true;
            ////}

            string cPortCode = "";
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo = new FAST.Core.BaseControl.ControlMethodInfo();
            controlMethodInfo.MethodName = "getDataListByTypes";
            ////获取当前行的组合代码
            string portCode = ((tbMain.SelectedRow.Tag as Row).Tag as ErDspManager).C_PORT_CODE;
            cPortCode = CboPortCls.Value == null ? portCode : System.Text.RegularExpressions.Regex.Split(CboPortCls.Value, "[|]")[0];
            controlMethodInfo.MethodParamValues = new string[] { cPortCode + "," };
            this.CboPortCls.MethodInfo = controlMethodInfo;
        }

        /// <summary>
        /// Comment: 设置控件参数
        /// </summary>
        /// <param name="clsparams">POJO类</param>
        private void setControlParam(ErDspManager clsparams)
        {
            string associaType = clsparams.C_DS_TPYE;
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo = new FAST.Core.BaseControl.ControlMethodInfo();
            if (clsparams.C_DSP_VALUE_TYPE.Equals("VOC"))
            {
                this.CboValue.Items.Clear();
                this.CboValue.IsFocused = false;
                this.CboValue.Visible = false;
                this.CboValue.ParaNodeID = "";
                this.CboValue.NodeID = "";
                this.CboValue.KTableTree = false;
                this.CboValue.YssAssociaType = Associa.Generate(associaType);
                ClsAssocia associa = ClsClzCfgMgr.getAssociaParam(CboValue.YssAssociaType);
                this.CboValue.DisplayName = associa.Sel_DisplayName;
                this.CboValue.DisplayValue = associa.Sel_DisplayValue;
                this.CboValue.Parameter = associa.Sel_DisplayColumns;
                this.CboValue.DisplayStyle = Yss.KRichEx.AutoFilter.DisplayStyle.DropDown;
                this.CboValue.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
                this.CboValue.YssCaption = "参数值";
                this.CboValue.QueryByValues = false;

                if (!"".Equals(clsparams.C_DV_TYPE))
                {
                    controlMethodInfo.MethodName = "getDataListByTypes";
                    this.CboValue.KTableTree = false;
                    this.CboValue.ShowCheckBox = false;
                    this.CboValue.NodeID = "";
                    this.CboValue.ParaNodeID = "";
                    controlMethodInfo.MethodParamValues = new string[] { clsparams.C_DV_TYPE + "," };
                }
                else
                {
                    controlMethodInfo.MethodName = "getDataList";
                    this.CboValue.MethodInfo.MethodParamValues = null;
                    controlMethodInfo.MethodParamValues = null;
                    this.CboValue.NodeID = "";
                    this.CboValue.ParaNodeID = "";
                    this.CboValue.KTableTree = false;
                    this.CboValue.ShowCheckBox = false;
                }
                CboValue.MethodInfo = controlMethodInfo;
            }

        }

        /// <summary>
        /// 重写AreaAConfigInfo，限定群组查询直接查找群组代码。
        /// </summary>
        public override AreaAConfigInfo AreaAConfigInfo
        {
            get
            {
                base.AreaAConfigInfo.QueryDataThrowGroupPort = false;
                return base.AreaAConfigInfo;
            }
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
                string search = this.yssBuildLeftCheckRowsStr("portfolio");
                search = search.Replace("'", "");
                paraDict.Add("ARRAY_C_PORT_CODE", search);

                if (string.IsNullOrEmpty(search))
                {
                    search = " ";
                }
                paraDict.Add("ARRAY_PORT_CODE", search);

                if (this.AreaAConfigInfo.IsGroupPort)
                {
                    paraDict.Add("C_DV_PARAM_TYPE", "GROUP_PARAM_CUSTOM");
                }

                if (!string.IsNullOrEmpty(this.cboParaName.Value))
                {
                    string dspCode = this.cboParaName.Value.Replace('|', ',');
                    paraDict.Add("ARRAY_C_DSP_CODE", dspCode);
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
        /// 重写修改按钮
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected override void btnEdit_Click(object sender, EventArgs e)
        {
            ClsButtonInfo saveBtn = this.btnBar.getButton("SaveButton");
            if (saveBtn.Enabled || this.tbMain.Rows.Count <= 0)
            {
                return;
            }
            this.btnBar.setButtonEnabled("SaveButton", true);
            this.btnBar.setButtonEnabled(ClsButtonName.BtnEdit, false);
            addColumnForModify();
        }

        /// <summary>
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnSaveButton_Click(object sender, EventArgs e)
        {
            this.btnBar.setButtonDisabled("SaveButton");
            ////this.isEdit = false;
            ////用于接收操作结果
            string operResult = "";
            try
            {
                ArrayList list = getList();
                if (list == null && this.retInfo != null)
                {
                    YssMessageBox.ShowCommonInfoText(this.retInfo);
                    this.btnBar.setButtonEnabled("SaveButton", true);
                    this.btnSearch_Click(sender, e);
                    return;
                }
                operResult = (dataService as IErDspManagerService).upadteParam(list);
                ////根据结果。弹出成功信息界面。
                YssMessageBox.ShowCommonInfo(operResult);
                ClsRetInfo retInfo = ClsRetInfoDealer.getReturnInfo(operResult);
                this.btnBar.setButtonEnabled(ClsButtonName.BtnEdit, true);
                ////this.btnSearch_Click(sender, e);
                this.btnSearch_Click(this.btnBar.getButton(FAST.Core.BaseControl.Fun.ClsButtonName.BtnRefresh).Owner, EventArgs.Empty);
            }
            catch (Exception ex)
            {
                this.btnBar.setButtonEnabled("SaveButton", true);
                YssMessageBox.ShowCommonInfos(TransferErrorMessageUtil.getTransferException(ex));
            }
            
        }

        private ArrayList getList()
        {
            retInfo = null;
            ArrayList list = new ArrayList();
            List<string> lstPortCls = null;
            RowCollection rows = tbMain.CheckedRows;
            ErDspManager param = null;
            int index;
            foreach (Row row in rows)
            {
                if (!(row.Tag is Row))
                {
                    continue;
                }
                Row old = row.Tag as Row;
                param = old.Tag as ErDspManager;

                if (null != this.tbMain.Columns["C_DV_PARAMS_VALUE"])
                {
                    index = this.tbMain.Columns["C_DV_PARAMS_VALUE"].Index;
                    if (string.IsNullOrEmpty(row.Cells[index].Text))
                    {
                        retInfo = new ClsRetInfo();
                        retInfo.icon = MessageBoxIcon.Warning;
                        retInfo.infoContent = param.C_DSP_NAME + " 参数值为空！";
                        retInfo.detailInfo = retInfo.infoContent;
                        retInfo.infoTitle = "参数值错误";
                        return null;
                    }
                    if (!string.IsNullOrEmpty(row.Cells[index].Key))
                    {
                        ////param.C_DV_PARAMS_VALUE = row.Cells[index].Key;
                        param.C_DV_PARAMS_VALUE = string.IsNullOrEmpty(row.Cells[index].Key) ? param.C_DV_PARAMS_VALUE : row.Cells[index].Key;
                    }
                    else {
                        row.Cells[index].Key = "";
                    }
                }

                if (null != this.tbMain.Columns["D_BEGIN"])
                {
                    index = this.tbMain.Columns["D_BEGIN"].Index;
                    if (string.IsNullOrEmpty(row.Cells[index].Text))
                    {
                        retInfo = new ClsRetInfo();
                        retInfo.icon = MessageBoxIcon.Warning;
                        retInfo.infoContent = param.C_DSP_NAME + " 开始日期为空！";
                        retInfo.detailInfo = retInfo.infoContent;
                        retInfo.infoTitle = "日期错误";
                        return null;
                    }
                    if (!string.IsNullOrEmpty(row.Cells[index].Key))
                    {
                        param.D_BEGIN = Convert.ToDateTime(row.Cells[index].Key);
                    }
                    else
                    {
                        row.Cells[index].Key = param.D_BEGIN.ToString("yyyy-MM-dd");
                    }
                }

                if (null != this.tbMain.Columns["D_END"])
                {
                    index = this.tbMain.Columns["D_END"].Index;
                    if (string.IsNullOrEmpty(row.Cells[index].Text))
                    {
                        retInfo = new ClsRetInfo();
                        retInfo.icon = MessageBoxIcon.Warning;
                        retInfo.infoContent = param.C_DSP_NAME + " 结束日期为空！";
                        retInfo.detailInfo = retInfo.infoContent;
                        retInfo.infoTitle = "日期错误";
                        return null;
                    }
                    if (!string.IsNullOrEmpty(row.Cells[index].Key))
                    {
                        param.D_END = Convert.ToDateTime(row.Cells[index].Key);
                    }
                    else
                    {
                        row.Cells[index].Key = param.D_END.ToString("yyyy-MM-dd");
                    }
                }

                DateTime startDate = Convert.ToDateTime(row.Cells[this.tbMain.Columns["D_BEGIN"].Index].Text);
                DateTime endDate = Convert.ToDateTime(row.Cells[this.tbMain.Columns["D_END"].Index].Text);
                if (startDate > endDate)
                {
                    retInfo = new ClsRetInfo();
                    retInfo.infoTitle = "日期错误";
                    retInfo.infoContent = "开始日期不能大于结束日期";
                    retInfo.detailInfo = "开始日期不能大于结束日期";
                    retInfo.infoType = ClsConstant.INFO_TP_WARN;
                    return null;
                }
                param.Modifier = ClsContext.currentUser.C_USER_CODE;
                param.ModifyDate = ClsFunction.formatDateTime("yyyyMMdd HH:mm:ss", DateTime.Now.ToString());
                if (this.AreaAConfigInfo.IsGroupPort)
                {
                    param.C_DV_PARAM_TYPE = "GROUP_PARAM_CUSTOM";
                }
                else if (string.IsNullOrEmpty(param.C_PORT_CODE.Trim()))
                {
                    param.C_DV_PARAM_TYPE = "COMM_PARAM_CUSTOM";
                }
                else
                {
                    param.C_DV_PARAM_TYPE = "PORT_PARAM_CUSTOM";
                }
                if (string.IsNullOrEmpty(param.Id))//新增时
                {
                    param.AuditState = (_formFun.N_CHECK == 1) ? 0 : 1; // 当功能窗体启用审核机制时新增状态为0
                    //// 当未开启审核状态时，这时是需要将审核人，审核时间填到POJO中去的byleeyu20130719
                    if (_formFun.N_CHECK <= 0)
                    {
                        param.OperUser = ClsContext.currentUser.C_USER_CODE;
                        param.AuditDate = ClsFunction.formatDateTime("yyyyMMdd HH:mm:ss", DateTime.Now.ToString());
                    }
                }


                if (null != this.tbMain.Columns["C_PORT_CLS_CODE"])
                {
                    index = this.tbMain.Columns["C_PORT_CLS_CODE"].Index;
                    if (!string.IsNullOrEmpty(row.Cells[index].Text))
                    {
                        ////param.C_PORT_CLS_CODE = row.Cells[index].Key;
                        param.C_PORT_CLS_CODE = string.IsNullOrEmpty(row.Cells[index].Key) ? param.C_PORT_CLS_CODE : row.Cells[index].Key;
                    }
                    else
                    {
                        row.Cells[index].Key = "";
                    }
                }

                list.Add(param);
             
                ////}
            }
            if (list.Count == 0)
            {
                retInfo = new ClsRetInfo();
                retInfo.infoTitle = "无数据";
                retInfo.infoContent = "未选择要保存的数据！";
                retInfo.detailInfo = "未选择要保存的数据！";
                retInfo.infoType = ClsConstant.INFO_TP_WARN;
                return null;
            }
      
            return list;
        }


        /// <summary>
        /// 点击修改按钮的时候往list界面添加一列（新增按钮）
        /// </summary>
        private void addColumnForModify()
        {
            Column column = new Column();
            column.DataPropertyName = "addBtn";
            column.Width = 40;
            tbMain.Columns.Insert(2, column);
            
            if (tbMain.Rows.Count > 0)
            {
                Row tempRow = tbMain.Rows[0];
                while (tempRow != null)
                {
                    if ((!tempRow.IsGroup) && tempRow.Tag is ErDspManager)
                    {
                        ImageCell img = new ImageCell();
                        img.Image = FAST.Resource.Resource.TablePlus_Rich;
                        img.Name = "新增";
                        tempRow.Cells.Insert(2, img);
                    }

                    tempRow = tempRow.NextRow;
                }
            }
        }

        /// <summary>
        /// 列表删除按钮点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnCellParamsDel_Click(object sender, Yss.KTable.Events.CellEventArgs e)
        {
            Row row = e.Row;
            if (row.Tag is Row)
            {
                (row.Tag as Row).Key = "";
            }
            if (row.ParentRow == null)
            {
                this.tbMain.Rows.Remove(row);
            }
            else
            {
                e.Row.ParentRow.SubRows.Remove(row);
            }
            tbMain.Refresh();
        }

        /// <summary>
        /// 列表新增按钮点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnCellParams_Click(object sender, Yss.KTable.Events.CellEventArgs e)
        {
            Row oldRow = e.Row;
            int index = this.tbMain.Columns["addBtn"].Index;
            ////自定义参数新增按钮只点击一次
            ////BUG293542【国海证券】修改参数上交所佣金明细汇总计算方式报错 edit by dingxukun 20191219 添加行单元格和列索引的判断，避免在分组查询情况下修改报错
            if (!"模板参数".Equals(oldRow.Cells[this.tbMain.Columns["C_DV_PARAM_TYPE"].Index].Text)
                && oldRow.NextRow != null && oldRow.NextRow.Cells.Count > this.tbMain.Columns["C_DV_PARAM_TYPE"].Index
                && !"模板参数".Equals(oldRow.NextRow.Cells[this.tbMain.Columns["C_DV_PARAM_TYPE"].Index].Text)
                && oldRow.NextRow.Cells.Count > this.tbMain.Columns["addBtn"].Index
                && "删除".Equals(oldRow.NextRow.Cells[this.tbMain.Columns["addBtn"].Index].Name)
                && oldRow.Cells.Count > this.tbMain.Columns["C_DSP_NAME"].Index && oldRow.NextRow.Cells.Count > this.tbMain.Columns["C_DSP_NAME"].Index
                && oldRow.Cells[this.tbMain.Columns["C_DSP_NAME"].Index].Text.Equals(oldRow.NextRow.Cells[this.tbMain.Columns["C_DSP_NAME"].Index].Text))
            {
                return;
            }

            ////群组自定义参数不允许点击新增，避免覆盖已有数据,操作风险。组合自定义参数不调整，可能通过该方式复制不同分级组合参数设置。
            if (oldRow.Cells[this.tbMain.Columns["C_DV_PARAM_TYPE"].Index].Text.Contains("群组自定义参数"))
            {
                YssMessageBox.currentForm = this;
                ClsRetInfo inf = new ClsRetInfo();
                inf.icon = MessageBoxIcon.Warning;
                inf.infoTitle = "警告";
                inf.infoCode = "014";
                inf.infoContent = "群组自定义参数，不允许修改，请先反审核或删除！";
                YssMessageBox.ShowCommonInfoText(inf);
                return;
            }

            Row newRow = oldRow.Clone() as Row;
            newRow.ForeColor = ClsConstant.ColorUnAudit;
            //// edit by  mazhongyuan 20180522 BUG #201284 【综合参数优化】参数问题汇总
            ////newRow.Tag = oldRow.Tag;
            ////newRow.Tag = (oldRow.Tag as BasePojo).Clone();
            newRow.Tag = oldRow.Clone();
            ImageCell imgCell = newRow.Cells[index] as ImageCell;
            if (imgCell != null)
            {
                imgCell.Image = FAST.Resource.Resource.TableMinus_Rich;
                imgCell.Name = "删除";
            }

            ////设置参数类别为  组合自定义参数
            if (null != this.tbMain.Columns["C_DV_PARAM_TYPE"])
            {
                int colIndex = this.tbMain.Columns["C_DV_PARAM_TYPE"].Index;

                ////调整群组和组合自定义参数显示文字信息
                if (AreaAConfigInfo.IsGroupPort == false)
                {
                    newRow.Cells[colIndex].Text = "组合自定义参数";
                }
                else
                {
                    newRow.Cells[colIndex].Text = "群组自定义参数";
                }
            }

            for (int i = 3; i < newRow.Cells.Count; i++)
            {
                newRow.Cells[i].ForeColor = ClsConstant.ColorUnAudit;
            }

            newRow.Checked = true;
            newRow.ForeColor = ClsConstant.ColorUnAudit;
            ////newRow.Tag = oldRow;
            oldRow.Key = "update";
            ////ErDspManager param = (newRow.Tag as Row).Tag as ErDspManager;
            ////if (param.Id == null || "".Equals(param.Id.Trim()))
            ////{
            ////    param.C_IS_ADD = "true";
            ////}
            ////else
            ////{
            ////    param.C_IS_ADD = "false";
            ////}

            ////param.AuditState = 0;
            ////////edit by zhoushuhang 20180417 BUG197257清算综合参数界面新增组合自定义参数两条记录除开始日期和结束日期不同，例：开始日期大于结束日期并存在时间段交叉也能成功保存数据
            ////////param.Id = "";
            ////newRow.Tag = param;

            //////// 当界面按分组展示时,要在父行中添加新行 liuxiang 2018-05-31 BUG204069【鹏华基金】21.6测试问题            
            if (oldRow.ParentRow == null)
            {
                tbMain.Rows.Insert(oldRow.Index + 1, newRow);
            }
            else
            {
                oldRow.ParentRow.SubRows.Insert(oldRow.Index + 1, newRow);
            }

            tbMain.Refresh();
            ////设置行字体颜色
            ////newRow.ForeColor = ClsConstant.ColorUnAudit;
        }
        /// <summary>
        /// Comment: 单元格编辑事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void tbMain_CellMouseClick(object sender, Yss.KTable.Events.CellEventArgs e)
        {
            if (!this.btnBar.getButton("SaveButton").Enabled)
            {
                return;
            }
            if (e.Row.IsGroup)
            {
                return;
            }
            string cellName = e.Cell.Name;
            if (e.Cell != null && e.Cell is ImageCell)
            {
                if ("新增".Equals(cellName))
                {
                    this.btnCellParams_Click(sender, e);
                    return;
                }

                if ("删除".Equals(cellName))
                {
                    this.btnCellParamsDel_Click(sender, e);
                    return;
                }
            }
            if (!(e.Row.Tag is Row))
            {
                return;
            }
            ErDspManager param = (e.Row.Tag as Row).Tag as ErDspManager;
            Column column = e.Cell.RelColumn;
            string columnName = column.DataPropertyName;
            if (columnName == "D_BEGIN")
            {
                clearHisControl(this.DtpValue.Tag);
                this.DtpValue.Tag = e.Cell;
                e.Cell.InnerControl = this.DtpValue;
                if(string.IsNullOrEmpty(e.Cell.Key))//第一次赋初始值
                {
                    this.DtpValue.setDateTime(DateTime.Now);
                }else
                {
                    this.DtpValue.setDateTime(Convert.ToDateTime(e.Cell.Key));
                }
            }
            else if (columnName == "D_END")
            {
                clearHisControl(this.DtpValue.Tag);
                this.DtpValue.Tag = e.Cell;
                e.Cell.InnerControl = this.DtpValue;
                if (string.IsNullOrEmpty(e.Cell.Key))//第一次赋初始值
                {
                    this.DtpValue.setDateTime(DateTime.Now);
                }
                else
                {
                    this.DtpValue.setDateTime(Convert.ToDateTime(e.Cell.Key));
                }
                
            }
            else if (columnName == "C_PORT_CLS_CODE")
            {
                clearHisControl(this.CboPortCls.Tag);
                e.Cell.InnerControl = this.CboPortCls;
                this.CboPortCls.Tag = e.Cell;
                e.Cell.Key = string.IsNullOrEmpty(param.C_PORT_CODE) ? e.Cell.Key : param.C_PORT_CLS_CODE;
                if (!string.IsNullOrEmpty(e.Cell.Key))
                {
                    this.CboPortCls.Value = e.Cell.Key;
                }
                else
                {
                    this.CboPortCls.Value = "";
                }
            }
            else if (columnName == "C_DV_PARAMS_VALUE")
            {
                if ("DATE".Equals(param.C_DSP_VALUE_TYPE))
                {
                    clearHisControl(this.DtpValue.Tag);
                    e.Cell.InnerControl = this.DtpValue;
                    e.Cell.InnerControl.Tag = e.Cell;
                    ////e.Cell.Key = this.txtParamsValue.Text;
                    e.Cell.Key = string.IsNullOrEmpty(param.C_DV_PARAMS_VALUE) ? e.Cell.Key : param.C_DV_PARAMS_VALUE;
                    if (string.IsNullOrEmpty(e.Cell.Key))//第一次赋初始值
                    {
                        this.DtpValue.setDateTime(DateTime.Now);
                    }
                    else
                    {
                        this.DtpValue.setDateTime(Convert.ToDateTime(e.Cell.Key));
                    }
                }
                else if ("VOC".Equals(param.C_DSP_VALUE_TYPE))
                {
                    clearHisControl(this.CboValue.Tag);
                    setControlParam(param);
                    e.Cell.InnerControl = this.CboValue;
                    e.Cell.InnerControl.Tag = e.Cell;
                    e.Cell.Key = string.IsNullOrEmpty(param.C_DV_PARAMS_VALUE) ? e.Cell.Key : param.C_DV_PARAMS_VALUE;
                    this.CboValue.Value = string.IsNullOrEmpty(e.Cell.Key) ? param.C_DV_PARAMS_VALUE : e.Cell.Key;
                }
                else if ("NUMBER".Equals(param.C_DSP_VALUE_TYPE))
                {
                    clearHisControl(this.IntValue.Tag);
                    e.Cell.InnerControl = this.IntValue;
                    e.Cell.InnerControl.Tag = e.Cell;
                    e.Cell.Key = string.IsNullOrEmpty(param.C_DV_PARAMS_VALUE) ? e.Cell.Key : param.C_DV_PARAMS_VALUE;
                    this.IntValue.Value = string.IsNullOrEmpty(e.Cell.Key) ? int.Parse(param.C_DV_PLAT_VALUE) : int.Parse(e.Cell.Key);
                }
                else
                {
                    clearHisControl(this.TxtValue.Tag);
                    e.Cell.InnerControl = this.TxtValue;
                    e.Cell.InnerControl.Tag = e.Cell;
                    e.Cell.Key = string.IsNullOrEmpty(param.C_DV_PARAMS_VALUE) ? e.Cell.Key : param.C_DV_PARAMS_VALUE;
                    this.TxtValue.Text = string.IsNullOrEmpty(e.Cell.Key) ? param.C_DV_PLAT_VALUE : e.Cell.Key;
                }
            }
        }
        /// <summary>
        /// 移除单元格绑定的控件
        /// </summary>
        /// <param name="obj"></param>
        private void clearHisControl(object obj)
        {
            if (obj != null && obj is Cell)
            {
                (obj as Cell).InnerControl = null;
            }
        }
        /// <summary>
        /// 单元格控件值改变事件
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void innerControl_ValueChanged(object sender, EventArgs e)
        {
            Control control = sender as Control;
            Cell cell = control.Tag as Cell;
            if (sender is DateTimeInputEx)
            {
                cell = (((sender as DateTimeInputEx).Parent) as YssDateTimeInterval).Tag as Cell;
                DateTime date = (((sender as DateTimeInputEx).Parent) as YssDateTimeInterval).getBeginDate;
                if(cell != null)
                {
                    cell.Key = date.ToString("yyyy-MM-dd");
                    cell.Text = cell.Key;
                }
            }
            else if(sender is YssTextBox)
            {
                if (cell != null)
                {
                    cell.Key = (sender as YssTextBox).Text;
                    cell.Text = (sender as YssTextBox).Text;
                }
            }
            else if (sender is YssSelCombox)
            {
                if (cell != null)
                {
                    cell.Key = (sender as YssSelCombox).Value;
                    cell.Text = (sender as YssSelCombox).Text;
                }
            }
            else if (sender is IntegerInputEx)
            {
                if (cell != null)
                {
                    cell.Key = (sender as IntegerInputEx).Value + "";
                    cell.Text = (sender as IntegerInputEx).Value + "";
                }
            }
            
        }
        /// <summary>
        /// 屏蔽行双击事件
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected override void tbMain_RowDoubleClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        { 
        
        }
        /// <summary>
        /// 行单击事件。新增的行不触发
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected override void tbMain_RowClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        { 
            if(e.Row.Tag is Row)
            {
                return;
            }
            ////base.tbMain_RowClicked(sender,e);
        }
        /// <summary>
        /// 保证修改和保存按钮不能同时使用
        /// </summary>
        /// <param name="pojo"></param>
        protected override void setButtonStaAfterTbMainClickMVC(BasePojo pojo)
        {
            base.setButtonStaAfterTbMainClickMVC(pojo);
            if (this.btnBar.getButton("SaveButton").Enabled)
            {
                this.btnBar.setButtonEnabled(ClsButtonName.BtnEdit, false);
            }
            else
            {
                this.btnBar.setButtonEnabled(ClsButtonName.BtnEdit, true);
            }
        }
        /// <summary>
        /// 初始化按钮状态
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Frm_ELEC_DSP_PARA_L_Load(object sender, EventArgs e)
        {
            this.btnBar.setButtonEnabled(ClsButtonName.BtnEdit, true);
            this.btnBar.setButtonEnabled("SaveButton", false);
        }

        /// <summary>
        /// 获取多项选择项的方法，此方法只适用于Checkbox的多选形式
        /// 不能删除审核模板参数
        /// MVC架构专用
        /// </summary>
        /// <returns>选择行的数据对象</returns>
        public override ArrayList getSelectTypeItemListAuditable()
        {
            ArrayList list = new ArrayList();
            //// 使用循环获取curView中选中项目，并添加到数组中
            //// 当启用checkbox功能时，获取选中行的处理
            if (bShowRowCheckBoxColumn || tbMain.ShowCheckBox)
            {
                foreach (Row row in tbMain.CheckedRows)
                {
                    // add by yh 2011.03.13 增加在获取list界面选中行时，去掉分组行数据的判断
                    if (row.IsGroup != true)
                    {
                        if (row.Tag is BasePojo)
                        {
                            if (!"TEMPLATE_PARAME".Equals((row.Tag as ErDspManager).C_DV_PARAM_TYPE))
                            {
                                list.Add(convertToDataPojo((BasePojo)row.Tag));
                            }
                        }
                    }
                }
            }
            else
            {  // 当未启用checkBox功能时，获取选中行的处理

                foreach (Row row in tbMain.SelectedRows)
                {
                    if (row.IsGroup != true)
                    {
                        if (row.Tag is BasePojo)
                        {
                            if (!"TEMPLATE_PARAME".Equals((row.Tag as ErDspManager).C_DV_PARAM_TYPE))
                            {
                                list.Add(convertToDataPojo((BasePojo)row.Tag));
                            }
                        }
                    }
                }
            }

            return list;
        }

        /// <summary>
        /// 查询
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        public override void btnSearch_Click(object sender, EventArgs e)
        {
            this.btnBar.setButtonEnabled(ClsButtonName.BtnEdit, true);
            this.btnBar.setButtonEnabled("SaveButton", false);
            base.btnSearch_Click(sender, e);
        }

        /// <summary>
        /// Comment: 单元格选择改变事件--用于动态去除编辑控件
        /// </summary>
        /// <param name="sender">Table</param>
        /// <param name="e">事件参数</param>
        private void tbMain_SelectedCellChanged(object sender, Yss.KTable.Events.CellEventArgs e)
        {
            Control con = e.Cell.InnerControl;
            if (!e.Cell.Selected && con != null)
            {
                Cell cell = e.Cell;
                if (con is YssDateTimeInterval)
                {
                    DateTime date = (con as YssDateTimeInterval).getBeginDate;
                    cell.Key = date.ToString("yyyy-MM-dd");
                    cell.Text = cell.Key;
                }
                else if (con is YssTextBox)
                {
                    cell.Key = (con as YssTextBox).Text;
                    cell.Text = (con as YssTextBox).Text;
                }
                else if (con is YssSelCombox)
                {
                    cell.Key = (con as YssSelCombox).Value;
                    cell.Text = (con as YssSelCombox).Text;
                }
                else if (con is IntegerInputEx)
                {
                    cell.Key = (con as IntegerInputEx).Value + "";
                    cell.Text = (con as IntegerInputEx).Value + "";
                }
                cell.InnerControl = null;
            }
                
        }

        /// <summary>
        /// 加载数据表格内容
        /// BUG187342【深国投】增值税台账明细表有多页，导出所有页，客户端会闪退。张绍林-20180209
        /// </summary>
        /// <param name="res">查询结果对象</param>
        /// <param name="tableSource">待装载数据的表格</param>
        protected override void loadListContentMVC(QueryRes res, Table tableSource)
        {
            ErDspTableListLoader listLoader = new ErDspTableListLoader();

            //BUG #252061 【国海证券】理财交易业务下面对价派息业务维护流水反审核的流水不会靠前导致维护的不能从维护界面审核。张绍林-20190412
            listLoader.AutoSort = this.ListLoaderAutoSort;
            listLoader.FunCode = this.YssFormMenu.C_FUN_CODE;
            listLoader.AutoLoadEnclosure = this.AutoLoadEnclosure;
            listLoader.loadTable(tableSource, res, this.bShowRowCheckBoxColumn, this.bShowRowIndexColumn, this.YssMainKTableShowMode);
            this.OnProcessTableByConfig(tableSource);
        }

    }
}
