using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.CRUD.Form;
using FAST.Core.Exceptions;
using FAST.Core.Communication.Service;
using YssElecReco.Pojo.Er;
using FAST.Common.Service.Dict.Service;
using YssElecReco.pojo.Er;
using FAST.Common.Service.Dict.Pojo;
using Yss.KTable.Models;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using YssElecReco.Service.Er;
using FAST.Core.Context.Events;
using FAST.Core.CRUD.Interface;
using Yss.KRichEx;
using System.Collections;
using FAST.Core.Context;

namespace YssElecReco.Form.Er
{
    /// <summary>
    /// 报表个性列
    /// </summary>
    public partial class Frm_DZ_RepColCfg_S : FrmBaseSet
    {
        /// <summary>
        /// ELEC_LRB_COL
        /// </summary>
        public const string LR_COL = "ELEC_LRB_COL";

        /// <summary>
        /// ELEC_ZCFZB_COL
        /// </summary>
        public const string ZCFZ_COL = "ELEC_ZCFZB_COL";

        /// <summary>
        /// ELEC_SYZQYB_COL
        /// </summary>
        public const string SYZQY_COL = "ELEC_SYZQYB_COL";

        /// <summary>
        /// ELEC_JZCBDB_COL
        /// </summary>
        public const string JZCBDB_COL = "ELEC_JZCBDB_COL";
        //STORY #65389电子对账前台与估值核算解耦
        /////// <summary>
        /////// 财务报表Service
        /////// </summary>
        ////private IReportTemplateService cwbbService = null;

        private IDzRepCfgService dzRepCfgService = null;
        /// <summary>
        /// elecCols
        /// </summary>
        private Dictionary<string, SortedDictionary<string, string>> elecCols = new Dictionary<string, SortedDictionary<string, string>>();

        /// <summary>
        /// allColDict
        /// </summary>
        private Dictionary<string, string> allColDict = new Dictionary<string, string>();
       
        /// <summary>
        /// 报表个性列
        /// </summary>
        public Frm_DZ_RepColCfg_S()
        {
            InitializeComponent();
            this.bUseMVCService = true;
            ////STORY #65389电子对账前台与估值核算解耦
            ////cwbbService = ServiceFactory.createService<IReportTemplateService>();
            dzRepCfgService = ServiceFactory.createService<IDzRepCfgService>();
            initColInfos();
            initColTableColumn();
        }

        /// <summary>
        /// 初始化列头
        /// </summary>
        private void initColTableColumn()
        { 
            Column c1 = new Column("序号");
            Column c2 = new Column("电子对账报表列");
            Column c3 = new Column("财务报表列");
            this.coltable.Columns.Add(c1);
            this.coltable.Columns.Add(c2);
            this.coltable.Columns.Add(c3);
        }

        /// <summary>
        /// 锁定特定的控件，禁止更改控件文本及相关属性。供派生类使用。
        /// </summary>
        protected override void OnLockSpecialControlState()
        {
            this.selCwbb.YssReadOnly = true;
            this.selDzbb.YssReadOnly = true;
        }

        /// <summary>
        /// 初始化控件
        /// </summary>
        private void initColInfos()
        {
            SortedDictionary<string, string> dtLrDict = new SortedDictionary<string, string>();
            SortedDictionary<string, string> dtSyzqyDict = new SortedDictionary<string, string>();
            SortedDictionary<string, string> dtZcfzDict = new SortedDictionary<string, string>();
            SortedDictionary<string, string> dtJzcbdbDict = new SortedDictionary<string, string>();
            elecCols.Add(LR_COL, dtLrDict);
            elecCols.Add(SYZQY_COL, dtSyzqyDict);
            elecCols.Add(ZCFZ_COL, dtZcfzDict);
            elecCols.Add(JZCBDB_COL, dtJzcbdbDict);
            string[] lDzTypes = { LR_COL, SYZQY_COL, ZCFZ_COL, JZCBDB_COL };
            foreach (string cDzType in lDzTypes)
            {
                Dictionary<string, string> paraDict = new Dictionary<string, string>();
                paraDict.Add("dataClass", "Vocabulary");
                paraDict.Add("C_DV_TYPE", cDzType);
                IVocabularyService vocService = ServiceFactory.createService<IVocabularyService>();
                QueryRes queryRes = vocService.getVocByType(paraDict);
                List<BasePojo> dataList = queryRes.DataList;
                if (dataList != null && dataList.Count > 0)
                {
                    foreach (Vocabulary pojo in dataList)
                    {
                        elecCols[cDzType].Add(pojo.C_DV_CODE, pojo.C_DV_NAME);
                        allColDict.Add(pojo.C_DV_CODE, pojo.C_DV_NAME);
                    }
                }
                
            }
        }

        /// <summary>
        /// 财务报表下拉框加载数据
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void selCwbb_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            try
            {
                e.IsCancel = true;
                if (this.selCwbb.Items.Count > 0)
                {
                    return;
                }
                ////STORY #65389电子对账前台与估值核算解耦
                ////List<BasePojo> list = cwbbService.getReportTemplateTreeView("").DataList;
                List<BasePojo> list = this.dzRepCfgService.getReportTemplateTreeView("").DataList;
                if (list != null && list.Count > 0)
                {
                    foreach (BasePojo pojo in list)
                    {
                        Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(pojo);
                        e.Collection.Add(entity);
                    }
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message, ex);
            }
        }

        /// <summary>
        /// 显示单条数据
        /// </summary>
        public override void yssShowInfoAddForm()
        {
           DzRepCfg rcpCfg = (this.FormBaseListView as IFormDetailData).MainDataPojo as DzRepCfg;
            ////财务报表的赋值要早于对账报表
           this.selCwbb.Value = rcpCfg.C_REPORT_CODE;
           this.selDzbb.Value = rcpCfg.C_DZ_CODE;
           loadColTable();
           ////this.coltable.Rows.AddRange(cretaeRow(rcpCfg.C_DZ_CODE));
        }

        /// <summary>
        /// 展示对象到窗体
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            try
            {
                DzRepCfg xDzRepColCfg = (this.FormBaseListView as IFormDetailData).MainDataPojo as DzRepCfg;
                ////DzRepColCfg dzRepColCfg = pojo as DzRepColCfg;
                ////财务报表的赋值要早于对账报表
                this.selCwbb.Value = xDzRepColCfg.C_REPORT_CODE;
                this.selDzbb.Value = xDzRepColCfg.C_DZ_CODE;
                loadColTable();
                ////this.coltable.Rows.AddRange(cretaeRow(dzRepColCfg));
                
            }
            catch (Exception ex)
            {
                throw TransferErrorMessageUtil.getTransferException(ex);
            }
        }

        /// <summary>
        /// 创建行
        /// </summary>
        /// <param name="pojos">pojos</param>
        /// <returns>Row[]</returns>
        private Row[] cretaeRow(List<DzRepColCfg> pojos)
        {
            List<Row> list = new List<Row>();
            if (pojos != null && pojos.Count > 0)
            {
                int i = 1;
                foreach (DzRepColCfg pojo in pojos)
                {
                    Row row = new Row();
                    row.Tag = pojo;
                    row.Cells.Add(new Cell(i + ""));
                    Cell elecCol = new Cell(allColDict[pojo.C_ELEC_COL]);
                    elecCol.Tag = pojo.C_ELEC_COL;
                    row.Cells.Add(elecCol);
                    IntegerInputEx intInput = new IntegerInputEx();
                    intInput.Prefix = "";
                    if (pojo.N_REPORT_COL != null && pojo.N_REPORT_COL.Length > 0)
                    {
                        intInput.Value = int.Parse(pojo.N_REPORT_COL);
                    }

                    Cell cell = new Cell(intInput);
                    cell.Tag = intInput;
                    row.Cells.Add(cell);
                    list.Add(row);
                    i++;
                }
            }

            return list.ToArray();
        }

        /// <summary>
        /// cretaeRow
        /// </summary>
        /// <param name="cDzType">cDzType</param>
        /// <returns>Row[]</returns>
        private Row[] cretaeRow(string cDzType)
        {
            List<Row> list = new List<Row>();
            string colType = "";
            ////{ LR_COL, SYZQY_COL, ZCFZ_COL };
            if ("1701".Equals(cDzType) || "1711".Equals(cDzType))
            { ////资产负债表
                colType = ZCFZ_COL;
            }
            else if ("1801".Equals(cDzType) || "1811".Equals(cDzType))
            { ////利润表
                colType = LR_COL;
            }
            else if ("1901".Equals(cDzType))
            { ////所有者权益（基金净值）变动表
                colType = SYZQY_COL;
            }
            else if ("1903".Equals(cDzType))
            { ////净资产变动表
                colType = JZCBDB_COL;
            }
            else
            {
                return list.ToArray();
            }

            int i = 1;
            SortedDictionary<string, string> dict = elecCols[colType];
            if (dict != null && dict.Count > 0)
            {
                foreach (KeyValuePair<string, string> kv in dict)
                {
                    Row row = new Row();
                    row.Tag = null;
                    row.Cells.Add(new Cell(i + ""));
                    Cell elecCell = new Cell(kv.Value);
                    elecCell.Tag = kv.Key;
                    row.Cells.Add(elecCell);
                    IntegerInputEx intInput = new IntegerInputEx();
                    intInput.Prefix = "";
                    Cell cell = new Cell(intInput);
                    cell.Tag = intInput;
                    row.Cells.Add(cell);
                    list.Add(row);
                    i++;
                }
            }

            return list.ToArray();
        }

        /// <summary>
        /// 新增时返回要操作的一组数据
        /// </summary>
        /// <returns>要操作的一组数据</returns>
        public override ArrayList yssGetObjListMVC()
        {
            ArrayList pojoList = new ArrayList();
            string cDzType = this.selDzbb.Value == null ? "" : this.selDzbb.Value;
            string cwbbCode = this.selCwbb.Value == null ? "" : this.selCwbb.Value;
            if (this.coltable.Rows.Count > 0)
            {
                foreach (Row row in this.coltable.Rows)
                {
                    DzRepColCfg xDzRepColCfg = row.Tag as DzRepColCfg;
                    if (xDzRepColCfg == null)
                    {
                        xDzRepColCfg = new DzRepColCfg();
                    }

                    xDzRepColCfg.C_DZ_CODE = cDzType;
                    xDzRepColCfg.C_REPORT_CODE = cwbbCode;
                    xDzRepColCfg.C_ELEC_COL = row.Cells[1].Tag as string;
                    xDzRepColCfg.N_REPORT_COL = (row.Cells[2].Tag as IntegerInputEx).Value.ToString();
                    pojoList.Add(xDzRepColCfg);
                }
            }

            return pojoList;
        }

        /// <summary>
        /// 加载数据
        /// </summary>
        private void loadColTable()
        {
            this.coltable.Rows.Clear(true);
            if (this.selDzbb.Value == null || this.selDzbb.Value.Trim().Length == 0)
            {
                return;
            }

            ////if (ClsEnums.StatusSetting.YssAdd.Equals(this.status))
            ////{
            ////    this.coltable.Rows.AddRange(cretaeRow(this.selDzbb.Value));
            ////}
            ////else 
            if (this.selCwbb.Value == null || this.selCwbb.Value.Trim().Length == 0)
            {
                this.coltable.Rows.AddRange(cretaeRow(this.selDzbb.Value));
            }
            else
            {
                List<DzRepColCfg> pojos = (this.dataService as IDzRepColCfgService).getDzRepColCfgs(this.selDzbb.Value, this.selCwbb.Value);
                if (pojos != null && pojos.Count > 0)
                {
                    this.coltable.Rows.AddRange(cretaeRow(pojos));
                }
                else
                {
                    this.coltable.Rows.AddRange(cretaeRow(this.selDzbb.Value));
                }
            }

            this.coltable.AutoWidth();
            this.coltable.FixedLeftCols = 3;
            this.coltable.Refresh();
        }

        ////private void selDzbb_SelectedValueChanged(object sender, EventArgs e)
        ////{
        ////    loadColTable();
        ////}

        ////protected override void btnSave_Click(object sender, System.EventArgs e)
        ////{
        ////    base.btnSave_Click(sender,e);
        ////    loadColTable();
        ////}
    }
}
