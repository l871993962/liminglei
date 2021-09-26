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
using YssElecReco.Pojo.Er;
using FAST.Core.Exceptions;
using YssElecReco.Service.Er;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context;
using Yss.KTable.Models;
using Yss.KTable.Collections;
using YssElecReco.Fun;
using FAST.Core.BaseControl.Fun;
using YssInformation.Support.Bi.AssociationOrgan.Service;
using YssInformation.Support.Bi.AssociationOrgan.Pojo;

namespace YssElecReco.Form.Er
{
    public partial class Frm_ELEC_SPLIT_RULE_S : FrmBaseSet
    {
        private IErSplitRelaService relaService = null;
        private IOrgService orgService = null;
        private IErSplitRuleService erSplitRuleService = null;
        private Dictionary<string, ErSplitRule> addRules = new Dictionary<string, ErSplitRule>();
        private Dictionary<string, ErSplitRule> removeRules = new Dictionary<string, ErSplitRule>();
        private string relaId = "";
        private List<ListHeadInfo> tghKmHeadInfos = null;
        private List<ListHeadInfo> portKmHeadInfos = null;
        private bool showChkColumn = true;
        private bool showMkColumn = true;
        /// <summary>
        ///  key：科目代码 value：组合科目的行
        /// </summary>
        private Dictionary<string, Row> portTableDict = null;
        public Frm_ELEC_SPLIT_RULE_S()
        {
            bUseMVCService = true;
            InitializeComponent();
            orgService = ServiceFactory.createService<IOrgService>();
            relaService = ServiceFactory.createService<IErSplitRelaService>();
            erSplitRuleService = ServiceFactory.createService<IErSplitRuleService>();
        }

        private void Frm_ELEC_SPLIT_RULE_S_Load(object sender, EventArgs e)
        {
            //先加载数据，再初始化组件
            initData();
            initComponents();
        }

        private void initComponents()
        {
            this.cboSplitCode.YssReadOnly = true;
            this.cboTZZH.YssReadOnly = true;
            this.cboTgh.YssReadOnly = true;
            //initBtn();
        }

        protected override void YssInitTopButtonStat()
        {
            this.btnBar.setAllButtonVisibled(false);
            this.btnBar.setAllButtonEnabled(false);
            this.btnBar.setButtonVisibled(ClsButtonName.BtnSave);
            this.btnBar.setButtonVisibled(ClsButtonName.BtnClose);
            this.btnBar.setButtonEnabled(ClsButtonName.BtnSave);
            this.btnBar.setButtonEnabled(ClsButtonName.BtnClose);
        }

        private void initData()
        {
            this.tghKmHeadInfos = erSplitRuleService.getTghKmTableHeadKeys();
            this.portKmHeadInfos = erSplitRuleService.getPortKmTableHeadKeys();
        }

        /// <summary>
        /// 显示单条数据(新增时设置默认值)
        /// </summary>
        public override void yssShowInfoAddForm()
        {
            if (this.frmBaseViewList != null)
            {
                Frm_ELEC_SPLIT_RULE_L frm = this.frmBaseViewList as Frm_ELEC_SPLIT_RULE_L;
                if (frm.MainDataPojo != null)
                {
                    ErSplitRela rela = frm.MainDataPojo as ErSplitRela;
                    this.cboSplitCode.Value = rela.C_SPLIT_CODE;
                    this.cboTgh.Value = rela.C_TGH_CODE;
                    this.cboTZZH.Value = rela.C_PORT_CODE;
                    this.relaId = rela.Id;
                    loadTghKm();
                    loadPortKm();
                }
            }
        }

        /// <summary>
        /// 显示单条数据，参数为set界面数据对应的pojo对象
        /// </summary>
        /// <param name="pojo">显示数据对应的pojo对象</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            try
            {
                if (this.frmBaseViewList != null)
                {
                    Frm_ELEC_SPLIT_RULE_L frm = this.frmBaseViewList as Frm_ELEC_SPLIT_RULE_L;
                    if(frm.MainDataPojo != null)
                    {
                        ErSplitRela rela = frm.MainDataPojo as ErSplitRela;
                        this.cboSplitCode.Value = rela.C_SPLIT_CODE;
                        this.cboTgh.Value = rela.C_TGH_CODE;
                        this.cboTZZH.Value = rela.C_PORT_CODE;
                        this.relaId = rela.Id;
                        loadTghKm();
                        loadPortKm();
                    }
                }
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }

        }

        /// <summary>
        /// 托管银行数据加载前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboTgh_YssOnBeforeLoadData(object sender, FAST.Core.Context.Events.YssBeforeOperEventArgs e)
        {
            if (this.cboTgh.Items.Count <= 0)
            {
                Dictionary<string, string> paraDict = new Dictionary<string, string>();
                paraDict.Add("dataClass", "Org");
                paraDict.Add("ARRAY_C_QUALIFICATION", "TRUSTEE,TRUSTEE_SEC");//// 加载托管行（商业银行）
                List<BasePojo> orgList = orgService.queryByCondition(paraDict).DataList;

                if (orgList != null && orgList.Count > 0)
                {
                    foreach (Org org in orgList)
                    {
                        Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(org);
                        e.Collection.Add(entity);
                    }
                }
            }
            e.IsCancel = true;
        }

        private void tailTextBoxLeft_TailClick(object sender, EventArgs e)
        {
            try
            {
                string[] matchSearchStr = new string[2] { "C_KM_CODE", "C_KM_NAME" };
                Yss.CommonLib.FilterTable(this.tablePort.Rows, this.tailTextBoxLeft.Text, matchSearchStr);
            }
            catch (System.Exception ex)
            {
                ////YssMessageBox.ShowDyanInformation("快捷区搜索数据出现异常", ex.Message, "系统提示", MessageBoxIcon.Error, FAST.Core.Context.Util.ClsEnums.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-110010", _formFun, status, ex));
            }
        }

        private void tailTextBoxRight_TailClick(object sender, EventArgs e)
        {
            try
            {
                string[] matchSearchStr = new string[2] { "C_KM_CODE", "C_KM_NAME" };
                Yss.CommonLib.FilterTable(this.tableTgh.Rows, this.tailTextBoxRight.Text, matchSearchStr);
            }
            catch (System.Exception ex)
            {
                ////YssMessageBox.ShowDyanInformation("快捷区搜索数据出现异常", ex.Message, "系统提示", MessageBoxIcon.Error, FAST.Core.Context.Util.ClsEnums.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-110010", _formFun, status, ex));
            }
        }
        /// <summary>
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void cboTZZH_SelectedValueChanged(object sender, EventArgs e)
        {
           
            
        }

        private void loadTghKm()
        {
            //Console.WriteLine("开始加载托管行"+DateTime.Now.ToString());
            if (this.frmBaseViewList != null)
            {
                Frm_ELEC_SPLIT_RULE_L frm = this.frmBaseViewList as Frm_ELEC_SPLIT_RULE_L;
                if (frm.MainDataPojo != null)
                {
                    ErSplitRela rela = frm.MainDataPojo as ErSplitRela;
                    SplitRuleTableListLoader tableLoader = new SplitRuleTableListLoader();
                    //Console.WriteLine("开始查询托管行" + DateTime.Now.ToString());
                    QueryRes res = erSplitRuleService.showRelaDetailKmInfo(this.relaId);
                    //Console.WriteLine("结束查询托管行" + DateTime.Now.ToString());
                    tableLoader.setTreeViewTable(this.tableTgh, res, this.showChkColumn, this.showMkColumn, ClsEnums.KTableDataShowMode.TreeMode);
                    
                }
            }
            //Console.WriteLine("结束加载托管行" + DateTime.Now.ToString());
        }

        private void loadPortKm()
        {
            //Console.WriteLine("开始加载组合" + DateTime.Now.ToString());
            if (this.cboTZZH.Value != null && !"".Equals(this.cboTZZH.Value))
            {
                this.portTableDict = null;
                SplitRuleTableListLoader tableLoader = new SplitRuleTableListLoader();
                //过滤掉已经添加到托管科目还未保存的数据
                if (this.addRules != null && this.addRules.Count > 0)
                {
                    tableLoader.setHaveAddData(this.addRules);
                }
                //Console.WriteLine("开始查询组合" + DateTime.Now.ToString());
                QueryRes res = erSplitRuleService.showUnSplitDetailKmInfo(this.cboTZZH.Value, this.yssQueryDate.getBeginDateStr);
                //Console.WriteLine("结束查询组合" + DateTime.Now.ToString());
                tableLoader.setTreeViewTable(this.tablePort, res, this.showChkColumn, this.showMkColumn, ClsEnums.KTableDataShowMode.TreeMode);
                this.portTableDict = tableLoader.getLoadData();
            }
            //Console.WriteLine("结束加载组合" + DateTime.Now.ToString());
        }


        /// <summary>
        /// 数据的保存事件
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnSave_Click(object sender, System.EventArgs e)
        {
            string oper = "新增";
            if (ClsEnums.StatusSetting.YssAdd.Equals(this.status))
            {
                oper = "新增";
            }
            else if (ClsEnums.StatusSetting.YssEdit.Equals(this.status))
            {
                oper = "修改";
            }
            List<ErSplitRule> removes = new List<ErSplitRule>();
            List<ErSplitRule> adds = new List<ErSplitRule>();
            foreach(ErSplitRule rule in removeRules.Values)
            {
                removes.Add(rule);
            }
            foreach (ErSplitRule rule in this.addRules.Values)
            {
                rule.AuditState = 1;
                adds.Add(rule);
            }
            if (removes.Count > 0 || adds.Count > 0)
            {
                string result = this.erSplitRuleService.updateRelaDetailKmInfo(adds, removes);
                this.removeRules.Clear();
                this.addRules.Clear();
                if (result.Equals("true"))
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo(oper + "托管科目成功！"));
                }
                else
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo(oper + "托管科目失败！"));
                }
            }
            else
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo(oper + "托管科目成功！"));
            }
            loadPortKm();
            loadTghKm();
           // this.frmBaseViewList.btnSearch_Click(this.frmBaseViewList.btnBar.getButton(ClsButtonName.BtnRefresh).Owner, EventArgs.Empty);
            this.frmBaseViewList.btnSearch_Click(null, EventArgs.Empty);
            //initBtn();
        }

        private Row createPortRow(ErKmb km)
        {
            Row row = new Row();
            row.Tag = km;
            if (this.portKmHeadInfos != null && this.portKmHeadInfos.Count > 0)
            {
                if (this.showChkColumn)
                {
                    row.Cells.Add(new Cell());
                }
                if (this.showMkColumn)
                {
                    row.Cells.Add(new Cell());
                }
                foreach (ListHeadInfo head in this.portKmHeadInfos)
                {
                    if ("C_KM_CODE".Equals(head.Key))
                    {
                        row.Cells.Add(new Cell(km.C_KM_CODE));
                    }
                    else if ("C_KM_NAME".Equals(head.Key))
                    {
                        row.Cells.Add(new Cell(km.C_KM_NAME));
                    }
                    else
                    {
                        row.Cells.Add(new Cell());
                    }
                }
                row.ForeColor = Color.Red;
            }
            return row;
        }

        private Row createTghRow(ErKmb km)
        { 
            Row row = new Row();
            row.Tag = km;
            if(this.tghKmHeadInfos != null && this.tghKmHeadInfos.Count > 0)
            {
                if(this.showChkColumn)
                {
                    row.Cells.Add(new Cell());
                }
                if(this.showMkColumn)
                {
                    row.Cells.Add(new Cell());
                }
                foreach(ListHeadInfo head in this.tghKmHeadInfos)
                {
                    if ("C_KM_CODE".Equals(head.Key))
                    {
                        row.Cells.Add(new Cell(km.C_KM_CODE));
                    }
                    else if ("C_KM_NAME".Equals(head.Key))
                    {
                        row.Cells.Add(new Cell(km.C_KM_NAME));
                    }else
                    {
                        row.Cells.Add(new Cell());
                    }
                }
                row.ForeColor = Color.Red;
            }
            return row;
        }

        /// <summary>
        /// 添加拆分规则
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnAddItem_Click(object sender, EventArgs e)
        {
            ErSplitRule rule = null;
            ErKmb km = null;
            if (this.tablePort.CheckedRows.Count > 0)
            {
                List<Row> rows = this.tablePort.CheckedRows.ToList();
                foreach (Row row in rows)
                {
                    km = row.Tag as ErKmb;
                    //只添加明细科目
                    if (km != null && 1 == km.N_DETAIL)
                    {
                        this.tableTgh.Rows.Add(createTghRow(km));
                        rule = new ErSplitRule();
                        rule.C_IDEN_RELA = this.relaId;
                        rule.C_KM_CODE = km.C_KM_CODE;
                        rule.C_KM_NAME = km.C_KM_NAME;
                        //保存添加的数据
                        if (this.removeRules.ContainsKey(km.C_KM_CODE))
                        {
                            this.removeRules.Remove(km.C_KM_CODE);
                        }else
                        {
                            this.addRules.Add(km.C_KM_CODE, rule);
                        }
                        //从组合科目中移除
                        if (row.ParentRow != null && row.ParentRow.SubRows != null && row.ParentRow.SubRows.Count > 0)
                        {
                            row.ParentRow.SubRows.Remove(row);
                        }else
                        {
                            this.tablePort.Rows.Remove(row);
                        }
                        if (this.portTableDict.ContainsKey(km.C_KM_CODE))
                        {
                            this.portTableDict.Remove(km.C_KM_CODE);
                        }
                    }

                }
                this.tablePort.AutoWidth();
                this.tablePort.Refresh();
                this.tableTgh.AutoWidth();
                this.tableTgh.Refresh();
            }else
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("请选择要添加的科目！"));
                return;
            }
        }
        /// <summary>
        /// 移除拆分规则
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnRemoveItem_Click(object sender, EventArgs e)
        {
            ErSplitRule rule = null;
            ErKmb km = null;
            string codeP = "";
            if (this.tableTgh.CheckedRows.Count > 0)
            {
                List<Row> rows = this.tableTgh.CheckedRows.ToList();
                foreach (Row row in rows)
                {
                    km = row.Tag as ErKmb;
                    //只添加明细科目
                    if (km != null)
                    {
                        rule = new ErSplitRule();
                        rule.C_IDEN_RELA = this.relaId;
                        rule.C_KM_CODE = km.C_KM_CODE;
                        rule.C_KM_NAME = km.C_KM_NAME;
                        if (km.C_KM_CODE != null && km.C_KM_CODE.Contains("."))
                        {
                            codeP = km.C_KM_CODE.Substring(0, km.C_KM_CODE.LastIndexOf('.'));
                        }
                        //保存移除的数据
                        if (this.addRules.ContainsKey(km.C_KM_CODE))
                        {
                            this.addRules.Remove(km.C_KM_CODE);
                        }
                        else
                        {
                            this.removeRules.Add(km.C_KM_CODE, rule);
                        }
                        //从托管科目中移除
                        this.tableTgh.Rows.Remove(row);
                        Row newRow = this.createPortRow(km);
                        //添加到组合科目
                        if (codeP != null && codeP.Trim().Length > 0 && this.portTableDict.ContainsKey(codeP))
                        {
                            this.portTableDict[codeP].SubRows.Add(newRow);
                        }else//根节点
                        {
                            this.tablePort.Rows.Add(newRow);
                        }
                        if (!this.portTableDict.ContainsKey(km.C_KM_CODE))
                        {
                            this.portTableDict.Add(km.C_KM_CODE, newRow);
                        }
                    }

                }
                this.tablePort.AutoWidth();
                this.tablePort.Refresh();
                this.tableTgh.AutoWidth();
                this.tableTgh.Refresh();
            }
            else
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("请选择要添加的科目！"));
                return;
            }
        }

        private void cboSplitCode_YssOnBeforeLoadData(object sender, FAST.Core.Context.Events.YssBeforeOperEventArgs e)
        {
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("dataClass", "ErSplitRela");
            if (null != this.cboTZZH.Value && !"".Equals(this.cboTZZH.Value))
            {
                paraDict.Add("ARRAY_C_PORT_CODE", this.cboTZZH.Value);
            }
            if (null != this.cboTgh.Value && !"".Equals(this.cboTgh.Value))
            {
                paraDict.Add("C_TGH_CODE", this.cboTgh.Value);
            }
            QueryRes res = this.relaService.queryByCondition(paraDict);
            if (res != null)
            {
                List<BasePojo> list = res.DataList;
                if (list != null && list.Count > 0)
                {
                    foreach (ErSplitRela rela in list)
                    {
                        if (rela.C_SPLIT_CODE != null && !"".Equals(rela.C_SPLIT_CODE))
                        {
                            this.cboSplitCode.Items.Add(new Yss.KRichEx.AutoFilter.Model.KTableEntity(rela.C_SPLIT_CODE, rela.C_SPLIT_CODE));
                        }
                    }

                }
            }
            e.IsCancel = true;
        }
        /// <summary>
        /// 改变日期重新加载组合科目
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void yssQueryDate_FirstdateTimeInputValueChanged(object sender, EventArgs e)
        {
            loadPortKm();
        }

    }
}
