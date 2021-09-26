using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Communication.Service;
using FAST.Core.Context;
using FAST.Core.CRUD.Form;
using FAST.Core.Exceptions;
using FAST.Core.Resource;
using FAST.Core.Util;
using Yss.KTable.Collections;
using Yss.KTable.Models;
using YssElecReco.Fun;
using YssElecReco.Pojo.Er;
using YssElecReco.Pojo.Er.Reverse;
using YssElecReco.Service.Er.Reverse;

namespace YssElecReco.Form.Er.Reverse
{
    public partial class Frm_ELEC_REVE_KMMAP_S : FrmBaseSet
    {
        private bool isDateChange = false;
        /// <summary>
        /// 托管行转换
        /// </summary>
        Dictionary<string, string> orgShowConvDict = null;
        /// <summary>
        /// key为选择的内部科目代码
        /// </summary>
        private Dictionary<string, KmRowRecord> rowRecords = new Dictionary<string, KmRowRecord>(); 
        //是否过滤已经映射的科目
        //private bool isFliter = null;
        private IKmRelaRecordService kmRelaRecordService = null;
        private IIgnoreItemService ignoreItemService = null;
        private IAssMapService assMapService = null;
        //资产类选项卡为选中状态
        private string kmCls = "KC_ZC";
        public Frm_ELEC_REVE_KMMAP_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
            initInnerOutTableHeader();
            setDefaultComStatuc();
            kmRelaRecordService = ServiceFactory.createService<IKmRelaRecordService>();
            ignoreItemService = ServiceFactory.createService<IIgnoreItemService>();
            assMapService = ServiceFactory.createService<IAssMapService>();
        }

        public string getCPZHValue()
        {
            return this.cboCPZH.Value;
        }

        public string getKmCls()
        {
            return this.kmCls;
        }

        /// <summary>
        /// 设置组件默认状态
        /// </summary>
        private void setDefaultComStatuc()
        {
            //默认勾选
            this.checkFliter.Checked = true;
            removeKmClsButtonsBackColor();
            this.btnZC.BackColor = Color.Gold;
            kmCls = "KC_ZC";
        }
        /// <summary>
        /// 将所有的科目类别选项卡背景设为白色
        /// </summary>
        private void removeKmClsButtonsBackColor()
        {
            this.btnFZ.BackColor = Color.White;
            this.btnZC.BackColor = Color.White;
            this.btnGT.BackColor = Color.White;
            this.btnQY.BackColor = Color.White;
            this.btnSY.BackColor = Color.White;
        }

        /// <summary>
        /// 内部科目搜索按钮
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void tailTextBoxInner_TailClick(object sender, EventArgs e)
        {
            try
            {
                string[] matchSearchStr = new string[2] { "C_KM_CODE", "C_KM_NAME" };
                Yss.CommonLib.FilterTable(this.tableInner.Rows, this.tailTextBoxInner.Text, matchSearchStr);
            }
            catch (System.Exception ex)
            {
                ////YssMessageBox.ShowDyanInformation("快捷区搜索数据出现异常", ex.Message, "系统提示", MessageBoxIcon.Error, FAST.Core.Context.Util.ClsEnums.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-110010", _formFun, status, ex));
            }
        }
        /// <summary>
        /// 外部科目搜索按钮
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void tailTextBoxOut_TailClick(object sender, EventArgs e)
        {
            try
            {
                string[] matchSearchStr = new string[2] { "C_KM_CODE", "C_KM_NAME" };
                Yss.CommonLib.FilterTable(this.tableOut.Rows, this.tailTextBoxOut.Text, matchSearchStr);
            }
            catch (System.Exception ex)
            {
                ////YssMessageBox.ShowDyanInformation("快捷区搜索数据出现异常", ex.Message, "系统提示", MessageBoxIcon.Error, FAST.Core.Context.Util.ClsEnums.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-110010", _formFun, status, ex));
            }
        }
        /// <summary>
        /// 新增按钮
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnKmAdd_Click(object sender, EventArgs e)
        {
            Row row = new Row();
            //复选框
            row.Cells.Add(new Cell());
            Cell cell = null;
            Yss.KRichEx.YssTextBox txt = null;
            foreach (ListHeadInfo head in getInnerOutTableHeadInfos())
            {
                cell = new Cell();
                txt = new Yss.KRichEx.YssTextBox();
                txt.YssIsMust = true;
                cell.InnerControl = txt;
                cell.Tag = txt;
                row.Cells.Add(cell);
            }
            this.tableInOut.Rows.Insert(0,row);
            this.tableInOut.Refresh();
        }
        /// <summary>
        /// 保存按钮
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnKmSave_Click(object sender, EventArgs e)
        {
            //6、点击“”按钮时，【内外科目映射】列表中的映射数据保存数据库，保存后清空列表。
            //7、当用户选择的外部科目信息已进行过映射操作，点击保存时提示用户“外部科目：科目代码XX、科目代码XX，已进行过匹配，请重新选择！”。 
            saveMapRelaRecord();
        }
        /// <summary>
        /// 资产类
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnZC_Click(object sender, EventArgs e)
        {
            this.kmCls = "KC_ZC";
            removeKmClsButtonsBackColor();
            this.btnZC.BackColor = Color.Gold;
            loadKmTable();
        }
        /// <summary>
        /// 负债类
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnFZ_Click(object sender, EventArgs e)
        {
            this.kmCls = "KC_FZ";
            removeKmClsButtonsBackColor();
            this.btnFZ.BackColor = Color.Gold;
            loadKmTable();
        }
        /// <summary>
        /// 共同类
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnGT_Click(object sender, EventArgs e)
        {
            this.kmCls = "KC_GT";
            removeKmClsButtonsBackColor();
            this.btnGT.BackColor = Color.Gold;
            loadKmTable();
        }
        /// <summary>
        /// 权益类
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnQY_Click(object sender, EventArgs e)
        {
            this.kmCls = "KC_QY";
            removeKmClsButtonsBackColor();
            this.btnQY.BackColor = Color.Gold;
            loadKmTable();
        }
        /// <summary>
        /// 损益类
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnSY_Click(object sender, EventArgs e)
        {
            this.kmCls = "KC_SY";
            removeKmClsButtonsBackColor();
            this.btnSY.BackColor = Color.Gold;
            loadKmTable();
        }


        private void checkFliter_CheckedChanged(object sender, EventArgs e)
        {
            //if(this.checkFliter.Checked)//勾选时，重新加载表
            //{
                loadKmTable();
                if (null != this.tailTextBoxInner.Text && "".Equals(this.tailTextBoxInner.Text.Trim()))
                {
                    tailTextBoxInner_TailClick(null,null);
                }
                if (null != this.tailTextBoxOut.Text && "".Equals(this.tailTextBoxOut.Text.Trim()))
                {
                    tailTextBoxOut_TailClick(null, null);
                }
            //}
        }
        /// <summary>
        /// 获取添加的映射关系
        /// </summary>
        private ArrayList getKmRelaRecordsFromInnerOutTable()
        {
            ArrayList list = new ArrayList();
            KmRelaRecord krr = null;
            foreach(Row row in this.tableInOut.Rows)
            {
                krr = row.Tag as KmRelaRecord;
                list.Add(krr);
            }
            return list;
        }

        /// <summary>
        /// 加载内部科目
        /// </summary>
        /// <param name="isFliter">是否过滤已经映射过的科目</param>
        private void loadInnerKmTable(bool isFliter, Dictionary<string, KmMap> dict, string busDate)
        {
            ReveDzTableListLoader tableLoader = new ReveDzTableListLoader();
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            string portCode = "";
            string mapType = "";
            if (null != this.cboCPZH.Value)
            {
                portCode = this.cboCPZH.Value;
            }
            if(null != this.cboYSFW.Value)
            {
                mapType = this.cboYSFW.Value;
            }
            paraDict.Add("C_DV_MAP_SCOPE", mapType);
            paraDict.Add("IS_FLITER", isFliter.ToString());
            paraDict.Add("dataClass", "ErKmb");
            paraDict.Add("C_PORT_CODE", portCode);
            paraDict.Add("C_DV_KM_CLS", this.kmCls);
            paraDict.Add("D_BUS_DATE", busDate);
            QueryRes res = kmRelaRecordService.queryInnerKm(paraDict);
            tableLoader.loadListTable(this.tableInner, res, true, true, dict, ReveElecDVCons.REVE_KMFW_INNER);
            //if(isFliter)
            //{
            //    tableLoader.loadListTable(this.tableInner, res, true, true, dict, ReveElecDVCons.REVE_KMFW_INNER);
            //}else
            //{
            //    tableLoader.loadTable(this.tableInner, res, true, true, ClsEnums.KTableDataShowMode.ListMode);
            //}
            
        }
        /// <summary>
        /// 获取托管行(C_TGH_CODE)的serviceid
        /// </summary>
        /// <param name="heads"></param>
        /// <returns></returns>
        private string getOrgServiceId(List<ListHeadInfo> heads)
        {
            foreach(ListHeadInfo header in heads)
            {
                if ("C_TGH_CODE".Equals(header.Key))
                {
                    return header.ServiceId;
                }
            }
            return null;
        }

        /// <summary>
        /// 加载外部科目
        /// </summary>
        /// <param name="isFliter">是否过滤已经映射过的科目</param>
        private void loadOutKmTable(bool isFliter,Dictionary<string,KmMap> dict)
        {
            ReveDzTableListLoader tableLoader = new ReveDzTableListLoader();
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            string portCode = "";
            string mapType = "";
            if (null != this.cboCPZH.Value)
            {
                portCode = this.cboCPZH.Value;
            }
            if (null != this.cboYSFW.Value)
            {
                mapType = this.cboYSFW.Value;
            }
            paraDict.Add("C_DV_MAP_SCOPE", mapType);
            paraDict.Add("IS_FLITER",isFliter.ToString());
            paraDict.Add("dataClass", "ErKmbOut");
            paraDict.Add("C_PORT_CODE", portCode);
            paraDict.Add("C_DV_KM_CLS", this.kmCls);
            QueryRes res = kmRelaRecordService.queryOutKm(paraDict);
            string orgServiceId = getOrgServiceId(res.ListHeadList);
            if(null != orgServiceId)
            {
                if (res.ShowConvertAssemble.ContainsKey(orgServiceId))
                {
                    orgShowConvDict = res.ShowConvertAssemble[orgServiceId];
                }
                
            }

            tableLoader.loadListTable(this.tableOut, res, true, true, dict, ReveElecDVCons.REVE_KMFW_OUT);
            //if (isFliter)
            //{
            //    tableLoader.loadListTable(this.tableOut, res, true, true, dict, ReveElecDVCons.REVE_KMFW_OUT);
            //}else
            //{
            //    tableLoader.loadTable(this.tableOut, res, true, true, ClsEnums.KTableDataShowMode.ListMode);
            //}
            
        }
        /// <summary>
        /// 加载内部科目表和外部科目表数据
        /// </summary>
        private void loadKmTable()
        {
            Dictionary<string, KmMap> innerKms = new Dictionary<string, KmMap>();
            Dictionary<string, KmMap> outKms = new Dictionary<string, KmMap>();
            //未选择组合时，不加载数据
            if(this.cboCPZH.Value != null&&!"".Equals(this.cboCPZH.Value))
            {
                foreach(KmRelaRecord krr in getKmRelaRecordsFromInnerOutTable())
                {
                    foreach(KmMap map in krr.LIST_KM_INNER)
                    {
                        innerKms.Add(map.C_KM_CODE,map);
                    }
                    foreach(KmMap map in krr.LIST_KM_OUT)
                    {
                        outKms.Add(map.C_KM_CODE,map);
                    }
                }
                if(this.cboYSFW.Value != null&&!"".Equals(this.cboYSFW.Value))
                {
                    if (ReveElecDVCons.REVE_YSFW_TGFYS.Equals(this.cboYSFW.Value))
                    {
                        List<string> tghCodes = assMapService.getTghCodesByPortCode(this.cboCPZH.Value);
                        if (tghCodes.Count > 1)
                        {
                            YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("多个托管行的组合请设置到产品映射！"));
                            this.tableInner.Clear();
                            this.tableOut.Clear();
                            return;
                        }
                    }
                    //Console.WriteLine("加载OUT开始：" + DateTime.Now.ToString());
                    loadOutKmTable(this.checkFliter.Checked, outKms);
                    //Console.WriteLine("加载OUT结束：" + DateTime.Now.ToString());
                    loadInnerKmTable(this.checkFliter.Checked, innerKms, this.dateBus.Value.ToString("yyyy-MM-dd"));
                    //Console.WriteLine("加载结束：" + DateTime.Now.ToString());
                    //ThreadStart threadStart1 = delegate { loadOutKmTable(this.checkFliter.Checked, outKms); };
                    //ThreadStart threadStart2 = delegate { loadInnerKmTable(this.checkFliter.Checked, innerKms); };
                    //Thread t1 = new Thread(new ThreadStart(threadStart1));
                    //Thread t2 = new Thread(new ThreadStart(threadStart2));
                    //t1.Start();
                    //t2.Start();
                    //loadOutKmTable(this.checkFliter.Checked,outKms);
                    //loadInnerKmTable(this.checkFliter.Checked, innerKms);
                }else//映射范围为空时，不过滤数据，过滤数据时依赖映射范围
                {
                    loadOutKmTable(false, outKms);
                    loadInnerKmTable(false, innerKms, this.dateBus.Value.ToString("yyyy-MM-dd"));
                    //ThreadStart threadStart1 = delegate { loadOutKmTable(false, outKms); };
                    //ThreadStart threadStart2 = delegate { loadInnerKmTable(false, innerKms); };
                    //Thread t1 = new Thread(new ThreadStart(threadStart1));
                    //Thread t2 = new Thread(new ThreadStart(threadStart2));
                    //t1.Start();
                    //t2.Start();
                }
            }
        }
        /// <summary>
        /// 供子set窗体调用
        /// </summary>
        public void loadOutKmTable()
        {
            //Dictionary<string, KmMap> innerKms = new Dictionary<string, KmMap>();
            Dictionary<string, KmMap> outKms = new Dictionary<string, KmMap>();
            //未选择组合时，不加载数据
            if (this.cboCPZH.Value != null && !"".Equals(this.cboCPZH.Value))
            {
                foreach (KmRelaRecord krr in getKmRelaRecordsFromInnerOutTable())
                {
                    //foreach (KmMap map in krr.LIST_KM_INNER)
                    //{
                    //    innerKms.Add(map.C_KM_CODE, map);
                    //}
                    foreach (KmMap map in krr.LIST_KM_OUT)
                    {
                        outKms.Add(map.C_KM_CODE, map);
                    }
                }
                if (this.cboYSFW.Value != null && !"".Equals(this.cboYSFW.Value))
                {
                    loadOutKmTable(this.checkFliter.Checked, outKms);
                    //loadInnerKmTable(this.checkFliter.Checked, innerKms);
                }
                else//映射范围为空时，不过滤数据，过滤数据时依赖映射范围
                {
                    loadOutKmTable(false, outKms);
                    //loadInnerKmTable(false, innerKms);
                }
            }
        }

        private void cboCPZH_SelectedValueChanged(object sender, EventArgs e)
        {
            if(this.tableInOut.Rows.Count>0)
            {
                if (YssMessageBox.ShowQuestion("变更加载条件会清空映射关系！是否保存后清空？", "提示") == System.Windows.Forms.DialogResult.Yes)
                {
                    saveMapRelaRecord();
                    this.tableInOut.ClearRows();
                    this.tableInOut.Refresh();
                }
                else
                {
                    this.tableInOut.ClearRows();
                    this.tableInOut.Refresh();
                }
            }
            loadKmTable();
        }

        private List<Row> getSelectInnerKms()
        {
            return this.tableInner.CheckedRows.ToList();
        }

        private List<Row> getSelectOutKms()
        {
            return this.tableOut.CheckedRows.ToList();
        }
        /// <summary>
        /// 供子set窗体调用
        /// </summary>
        /// <returns></returns>
        public List<BasePojo> getOutKmsSelectItems()
        {
            List<BasePojo> list = new List<BasePojo>();
            ErKmbOut km = null;
            foreach (Row row in getSelectOutKms())
            {
                km = row.Tag as ErKmbOut;
                if(this.cboCPZH != null)
                {
                    km.C_ASS_CODE = this.cboCPZH.Value;
                }
                list.Add(km);
            }
            return list;
        }

        private List<Row> getSelectInnerOutKms()
        {
            return this.tableInOut.CheckedRows.ToList();
        }

        private ArrayList getInnerOutTableHeadInfos()
        { 
            ArrayList infos = new ArrayList();
            ListHeadInfo head = new ListHeadInfo();
            head.Key = "C_KM_CODE";
            head.Text = "内部科目";
            infos.Add(head);
            head = new ListHeadInfo();
            head.Key = "C_KM_CODE_OUT";
            head.Text = "外部科目";
            infos.Add(head);
            head = new ListHeadInfo();
            head.Key = "C_TGH_CODE";
            head.Text = "托管行";
            infos.Add(head);
            return infos;
        }

        private void initInnerOutTableHeader()
        {
            
            //添加复选框
            Column col = new CheckBoxColumn();
            col.DataPropertyName = "ShowRowCheckBoxColumn";
            tableInOut.Columns.Insert(0, col);
            ArrayList headKeys = getInnerOutTableHeadInfos();
            foreach(ListHeadInfo headKey in headKeys)
            {
                col = new Column();
                col.AutoWidth = true;
                col.Text = headKey.Text;
                col.DataPropertyName = headKey.Key;
                //col.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Center;
                //col.CellTextAlign = ContentAlignment.MiddleCenter;
                this.tableInOut.Columns.Add(col);
            }
            //this.tableInOut.AutoColumnWidth = true;
            //this.tableInOut.AutoWidth();
        }

        private List<KmMap> getOutKmMap(List<Row> outRows)
        {
            List<KmMap> list = new List<KmMap>();
            ErKmbOut kmb = null;
            foreach (Row row in outRows)
            {
                kmb = row.Tag as ErKmbOut;
                KmMap km = new KmMap();
                km.C_DV_KM_CLS = this.kmCls;
                km.C_DV_KM_SCOPE = ReveElecDVCons.REVE_KMFW_OUT;
                km.C_KM_CODE = kmb.C_KM_CODE;
                km.C_KM_NAME = kmb.C_KM_NAME;
                km.C_PORT_CODE = this.cboCPZH.Value;
                km.C_TGH_CODE = kmb.C_TGH_CODE;
                list.Add(km);
            }
            return list;
        }

        private List<KmMap> getInnerKmMap(List<Row> innerRows)
        {
            List<KmMap> list = new List<KmMap>();
            ErKmb kmb = null;
            foreach(Row row in innerRows)
            {
                kmb = row.Tag as ErKmb;
                KmMap km = new KmMap();
                km.C_DV_KM_CLS = this.kmCls;
                km.C_DV_KM_SCOPE = ReveElecDVCons.REVE_KMFW_INNER;
                km.C_KM_CODE = kmb.C_KM_CODE;
                km.C_KM_NAME = kmb.C_KM_NAME;
                km.C_PORT_CODE = this.cboCPZH.Value;
                list.Add(km);
            }
            return list;
        }

        private KmRela getKmRela()
        {
            KmRela km = new KmRela();
            km.C_DV_KM_CLS = this.kmCls;
            km.C_DV_MAP_SCOPE = this.cboYSFW.Value;
            //km.C_PORT_CODE = this.cboCPZH.Value;
            //this.setAddOperPojoInfo((BasePojo)km);
            return km;
        }

        /// <summary>
        /// 将映射关系添加到内外科目映射表中
        /// </summary>
        /// <param name="mapRow"></param>
        private void addRowToInnerOutKmTable(List<Row> innerRows, List<Row> outRows)
        {
            Row mapRow = new Row();//在内外科目映射中添加一行
            int rowHeightDefault = 23;//每行的高度，默认23
            KmRelaRecord krr = new KmRelaRecord();
            List<KmMap> inMaps = getInnerKmMap(innerRows);
            List<KmMap> outMaps = getOutKmMap(outRows);
            KmRela kr = getKmRela();
            if(ReveElecDVCons.REVE_YSFW_TGFYS.Equals(this.cboYSFW.Value))
            {
                kr.C_TGH_CODE = outMaps[0].C_TGH_CODE;
            }
            else if (ReveElecDVCons.REVE_YSFW_CPYS.Equals(this.cboYSFW.Value))
            {
                kr.C_PORT_CODE = this.cboCPZH.Value;
            }
            krr.KmRela = kr;
            krr.LIST_KM_INNER = inMaps;
            krr.LIST_KM_OUT = outMaps;
            mapRow.Tag = krr;
            mapRow.Cells.Add(new Cell());//复选框
            ErKmb innerKm = null;
            ErKmbOut outKm = null;
            //string tgh = "";
            //添加内部科目
            //Cell cell = null;
            //ListCell innerCell = new ListCell();
            //ListCell outCell = new ListCell();
            //ListCell outTghCell = new ListCell();
            Cell innerCell = new Cell();
            Cell outCell = new Cell();
            Cell outTghCell = new Cell();
            //ListCell testCell = new ListCell();
            StringBuilder innerCellSb = new StringBuilder();
            StringBuilder outCellSb = new StringBuilder();
            StringBuilder outTghCellSb = new StringBuilder();
            //ArrayList indexs = new ArrayList();
            foreach (Row innerRow in innerRows)
            {
                
                innerKm = innerRow.Tag as ErKmb;
                //testCell.ListItems.Add(new ListItem(innerKm.C_KM_CODE));
                //从内部科目移除选中行,先记录再删除
                this.tableInner.Rows.Remove(innerRow);
                //indexs.Add(innerRow.Index);
                //添加到内部科目代码单元格中
                innerCellSb.Append(innerKm.C_KM_CODE);
                innerCellSb.Append(ReveElecDVCons.LINE_SEPARATOR);
                //innerCell.ListItems.Add(new ListItem(innerKm.C_KM_CODE));
            }
            
            //在外部科目删除选中行
            //添加外部科目
            foreach (Row outRow in outRows)
            {
                
                outKm = outRow.Tag as ErKmbOut;
                //从外部科目移除选中行
                this.tableOut.Rows.Remove(outRow);
                //indexs.Add(outRow.Index);
                outCellSb.Append(outKm.C_KM_CODE);
                outCellSb.Append(ReveElecDVCons.LINE_SEPARATOR);
                //托管行放置托管行名称
                string tghName = outKm.C_TGH_CODE;
                if (this.orgShowConvDict != null && this.orgShowConvDict.ContainsKey(outKm.C_TGH_CODE))
                {
                    tghName = this.orgShowConvDict[tghName];
                }
                //outCell.ListItems.Add(new ListItem(outKm.C_KM_CODE));
                //outTghCell.ListItems.Add(new ListItem(tghName));
                outTghCellSb.Append(tghName);
                outTghCellSb.Append(ReveElecDVCons.LINE_SEPARATOR);
            }
            //removeRowsFromTable(this.tableOut, indexs);
            innerCell.Text = removeLastStr(innerCellSb, ReveElecDVCons.LINE_SEPARATOR);
            outCell.Text = removeLastStr(outCellSb, ReveElecDVCons.LINE_SEPARATOR);
            outTghCell.Text = removeLastStr(outTghCellSb, ReveElecDVCons.LINE_SEPARATOR);
            //innerCell.
            //mapRow.Cells.Add(innerCell);
            //innerCell.TextAlign = ContentAlignment.MiddleCenter;
            //outCell.TextAlign = ContentAlignment.MiddleCenter;
            //outTghCell.TextAlign = ContentAlignment.MiddleCenter;
            //if(innerCell.ListItems.Count>outCell.ListItems.Count)
            //{
            //    outCell.Paint();
            //}else
            //{
            
            //}
            mapRow.Cells.Add(innerCell);
            mapRow.Cells.Add(outCell);
            mapRow.Cells.Add(outTghCell);
            mapRow.PrintRowBorder = true;

            //不允许自动换行
            mapRow.WordWrap = false;
            //设置行高
            int rowHeight = rowHeightDefault;
            //if(outRows.Count>innerRows.Count)
            //{
            //    rowHeight = rowHeightDefault*(outRows.Count);
            //}else
            //{
            //    rowHeight = rowHeightDefault * (innerRows.Count);
            //}
            //if (rowHeight>rowHeightDefault)
            //{
            //    mapRow.Height = rowHeight;
            //}else
            //{
            //    mapRow.Height = rowHeightDefault;
            //}

            //记录选择的内外科目中的行，方便还原
            saveRowRecord(innerRows, outRows, this.cboYSFW.Value);
            this.tableInOut.Rows.Add(mapRow);
            this.tableInOut.AutoWidth();
            this.tableInOut.Refresh();
            this.tableInner.Refresh();
            this.tableOut.Refresh();
        }

        

        /// <summary>
        /// 移除行记录
        /// </summary>
        /// <param name="key"></param>
        /// <returns></returns>
        private KmRowRecord removeRowRecord(List<KmMap> list,string mapScope)
        {
            KmRowRecord krr = null;
            StringBuilder sb = new StringBuilder();
            foreach (KmMap map in list)
            {
                sb.Append(map.C_KM_CODE);
            }
            sb.Append(mapScope);
            krr = this.rowRecords[sb.ToString()];
            this.rowRecords.Remove(sb.ToString());
            return krr;
        }
       
        /// <summary>
        /// 记录选择的内外科目中的行，方便还原
        /// </summary>
        /// <param name="innerRows"></param>
        /// <param name="outRows"></param>
        /// <param name="mapScope"></param>
        private void saveRowRecord(List<Row> innerRows, List<Row> outRows,string mapScope)
        {
            KmRowRecord kmRows = new KmRowRecord();
            kmRows.InnerRows = innerRows;
            kmRows.OutRows = outRows;
            string key = "";
            ErKmb km = null;
            foreach(Row row in innerRows)
            {
                km = row.Tag as ErKmb;
                key += km.C_KM_CODE;
            }
            key += mapScope;
            this.rowRecords.Add(key,kmRows);
        }

        /// <summary>
        /// 如果存在已经映射过的科目返回详细信息，否则返回null
        /// </summary>
        /// <param name="innerKms"></param>
        /// <param name="outKms"></param>
        /// <returns></returns>
        private string checkIsMapped(List<Row> innerKms, List<Row> outKms)
        {
            ErKmb km = null;
            ErKmbOut kmOut = null;
            Dictionary<string, string> dict = new Dictionary<string, string>();
            dict.Add("dataClass", "KmRelaRecord");
            if (ReveElecDVCons.REVE_YSFW_GGYS.Equals(this.cboYSFW.Value))//公共
            {
                dict.Add("C_DV_MAP_SCOPE", ReveElecDVCons.REVE_YSFW_GGYS);
            }
            else if (ReveElecDVCons.REVE_YSFW_TGFYS.Equals(this.cboYSFW.Value))//托管行
            {
                kmOut = outKms[0].Tag as ErKmbOut;
                dict.Add("C_DV_MAP_SCOPE", ReveElecDVCons.REVE_YSFW_TGFYS);
                dict.Add("ARRAY_C_TGH_CODE", kmOut.C_TGH_CODE);
            }
            else if (ReveElecDVCons.REVE_YSFW_CPYS.Equals(this.cboYSFW.Value))//产品
            {
                dict.Add("C_DV_MAP_SCOPE", ReveElecDVCons.REVE_YSFW_CPYS);
                dict.Add("ARRAY_C_PORT_CODE", this.cboCPZH.Value);
            }
            foreach (Row row in innerKms)
            {
                km = row.Tag as ErKmb;
                if (dict.ContainsKey("C_KM_CODE"))
                {
                    dict.Remove("C_KM_CODE");
                }
                if (dict.ContainsKey("C_KM_CODE_OUT"))
                {
                    dict.Remove("C_KM_CODE_OUT");
                }
                dict.Add("C_KM_CODE", km.C_KM_CODE);
                List<KmRelaRecord> list = this.kmRelaRecordService.queryIsMappingKm(dict);
                if (list != null && list.Count > 0)
                {
                    return "内部科目代码：" + km.C_KM_CODE + "已经设置过映射关系！";
                }
            }
            foreach (Row row in outKms)
            {
                kmOut = row.Tag as ErKmbOut;
                if (dict.ContainsKey("C_KM_CODE"))
                {
                    dict.Remove("C_KM_CODE");
                }
                if (dict.ContainsKey("C_KM_CODE_OUT"))
                {
                    dict.Remove("C_KM_CODE_OUT");
                }
                dict.Add("C_KM_CODE_OUT", kmOut.C_KM_CODE);
                List<KmRelaRecord> list = this.kmRelaRecordService.queryIsMappingKm(dict);
                if (list != null && list.Count > 0)
                {
                    return "外部科目代码：" + kmOut.C_KM_CODE + "已经设置过映射关系！";
                }
            }

            return null;
        }

        /// <summary>
        /// 如果存在忽略的科目，返回详细信息，否则返回null
        /// </summary>
        /// <param name="innerKms"></param>
        /// <param name="outKms"></param>
        /// <returns></returns>
        private string checkIsIgnore(List<Row> innerKms, List<Row> outKms)
        {
            string tgh = "";
            ErKmb km = null;
            ErKmbOut kmOut = null;
            StringBuilder sb = new StringBuilder();
            foreach(Row row in innerKms)
            {
                km = row.Tag as ErKmb;
                sb.Append(km.C_KM_CODE);
                sb.Append(",");
            }
            foreach (Row row in outKms)
            {
                
                kmOut = row.Tag as ErKmbOut;
                sb.Append(kmOut.C_KM_CODE);
                sb.Append(",");
                tgh = kmOut.C_TGH_CODE;
            }
            Dictionary<string, string> dict = new Dictionary<string, string>();
            dict.Add("dataClass", "IgnoreItem");
            dict.Add("ARRAY_C_ROW_FLAG", removeLastStr(sb,","));//科目代码
            if(ReveElecDVCons.REVE_YSFW_GGYS.Equals(this.cboYSFW.Value))//公共
            {
                dict.Add("IGNORE_TYPE",ReveElecDVCons.IGNORE_TYPE_GG);
            }
            else if (ReveElecDVCons.REVE_YSFW_TGFYS.Equals(this.cboYSFW.Value))//托管行
            {
                dict.Add("IGNORE_TYPE", ReveElecDVCons.IGNORE_TYPE_TGH);
                dict.Add("C_TGH_CODE", tgh);
            }
            else if (ReveElecDVCons.REVE_YSFW_CPYS.Equals(this.cboYSFW.Value))//产品
            {
                dict.Add("IGNORE_TYPE", ReveElecDVCons.IGNORE_TYPE_CP);
                dict.Add("ARRAY_C_PORT_CODE", this.cboCPZH.Value);
            }
            QueryRes res = ignoreItemService.queryByCondition(dict);
            List<BasePojo> list = res.DataList;
            if (list.Count > 0)
            {
                StringBuilder result = new StringBuilder();
                StringBuilder sbInner = new StringBuilder();
                StringBuilder sbOut = new StringBuilder();
                //内部科目：科目代码XX、科目代码XX，外部科目：科目代码XX、科目代码XX已忽略，请检查后再试！
                foreach(IgnoreItem item in list)
                {
                    if(ReveElecDVCons.IGNORE_SCOPE_INNER.Equals(item.C_DV_IGNORE_SCOPE))
                    {
                        sbInner.Append("科目代码").Append(item.C_ROW_FLAG).Append("、");
                    }else if(ReveElecDVCons.IGNORE_SCOPE_OUT.Equals(item.C_DV_IGNORE_SCOPE))
                    {
                        sbOut.Append("科目代码").Append(item.C_ROW_FLAG).Append("、");
                    }
                }
                if(sbInner.Length>0)
                {
                    result.Append("内部科目：").Append(removeLastStr(sbInner, "、")).Append(",");
                }
                if(sbOut.Length>0)
                {
                    result.Append("外部科目：").Append(removeLastStr(sbOut, "、")).Append(",");
                }
                if(result.Length>0)
                {
                    result.Append("已忽略，请检查后再试！");
                    return result.ToString();
                }
                
            }
            return null;
        }
        /// <summary>
        /// 移除最后出现的字符
        /// </summary>
        /// <param name="sb"></param>
        /// <returns></returns>
        private string removeLastStr(StringBuilder sb,string str)
        {
            string s = sb.ToString();
            if (s.EndsWith(str))
            {
                s = s.Remove(s.LastIndexOf(str));
            }
            return s;
        }
        /// <summary>
        /// 如果选择的不是同一个托管行，返回错误信息，否则返回null
        /// </summary>
        /// <returns></returns>
        private string checkTgh(List<Row> outRows)
        {
            string tgh = null;
            ErKmbOut km = null;
            foreach(Row row in outRows)
            {
                km = row.Tag as ErKmbOut;
                if (null == km.C_TGH_CODE || "".Equals(km.C_TGH_CODE.Trim()))
                {
                    return "托管行不能为空！";
                }
                if(tgh==null)
                {
                    tgh = km.C_TGH_CODE.Trim();
                }
                if(!tgh.Equals(km.C_TGH_CODE.Trim()))
                {
                    return "选择的外部科目不属于同一托管行！";
                }
            }
            return null;
        }

        /// <summary>
        /// 添加映射关系
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnAddItem_Click(object sender, EventArgs e)
        {
            List<Row> innerRows = getSelectInnerKms();
            List<Row> outRows = getSelectOutKms();
            //当未选择内部科目或外部科目信息，点击该按钮，提示用户“请同时选择相应的内部与外部科目信息。”
            //点击该按钮时，如果存在科目信息已进行整行忽略，提示用户“内部科目：科目代码XX、科目代码XX，外部科目：科目代码XX、科目代码XX已忽略，请检查后再试！”；
            if (innerRows.Count == 0 || outRows.Count == 0)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("请同时选择相应的内部与外部科目信息！"));
                //YssMessageBox.ShowQuestion("请同时选择相应的内部与外部科目信息！");
               //YssMessageBox.ShowCommonInfo("请同时选择相应的内部与外部科目信息！");
                return ;
            }
            string message = null;
            //映射范围为托管行时，外部科目的托管行必须一致
            if(ReveElecDVCons.REVE_YSFW_TGFYS.Equals(this.cboYSFW.Value))
            {
                message = checkTgh(outRows);
                if (message != null && !"".Equals(message.Trim()))
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo(message));
                    //YssMessageBox.ShowCommonInfo("内部科目：科目代码XX、科目代码XX，外部科目：科目代码XX、科目代码XX已忽略，请检查后再试！");
                    //YssMessageBox.ShowCommonInfo(message);
                 return ;
                }
            }
            message = checkIsIgnore(innerRows, outRows);
            if (message != null && !"".Equals(message.Trim()))
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo(message));
                //YssMessageBox.ShowCommonInfo("内部科目：科目代码XX、科目代码XX，外部科目：科目代码XX、科目代码XX已忽略，请检查后再试！");
                //YssMessageBox.ShowCommonInfo(message);
                return ;
            }

            message = checkIsMapped(innerRows, outRows);
            if (message != null && !"".Equals(message.Trim()))
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo(message));
                return;
            }
            //添加到科目映射表中，并从内部科目和外部科目中移除选中行
            addRowToInnerOutKmTable(innerRows,outRows);
        }
        /// <summary>
        /// 插入行到指定的表中，插入位置为row.Index
        /// </summary>
        /// <param name="table"></param>
        /// <param name="rows"></param>
        private void insertRows(Table table, List<Row> rows)
        { 
            foreach(Row row in rows)
            {
                //取消选中状态
                row.Checked = false;
                if (row.Index>0)
                {
                    //按照原来的序号插入
                    table.Rows.Insert(row.Index,row);
                }else
                {
                    table.Rows.Insert(0, row);
                }
                
                //table.Rows.Add(row);
            }
        }

        private void btnRemoveItem_Click(object sender, EventArgs e)
        {
            List<Row> innerOutKms = getSelectInnerOutKms();
            //RowCollection innerOutKms = getSelectInnerOutKms();
            //当未选择内外科目对应关系信息，点击时，提示用户“请选择需要删除的科目对应关系信息！”；
            if (innerOutKms.Count == 0)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("请选择需要删除的科目对应关系信息！"));
                //YssMessageBox.ShowCommonInfo("请选择需要删除的科目对应关系信息！");
                return;
            }
            //ArrayList indexs = new ArrayList();
            foreach (Row row in innerOutKms)
            {
                //row = innerOutKms[i];

                KmRelaRecord krr = row.Tag as KmRelaRecord;
                KmRowRecord kmRows = removeRowRecord(krr.LIST_KM_INNER, krr.KmRela.C_DV_MAP_SCOPE);
                insertRows(this.tableInner, kmRows.InnerRows);
                insertRows(this.tableOut, kmRows.OutRows);


                this.tableInOut.Rows.Remove(row);
                
            }
            //removeRowsFromTable(this.tableInOut, indexs);
            this.tableInOut.Refresh();
            this.tableInner.Refresh();
            this.tableOut.Refresh();
        }

        private void btnKmOutSet_Click(object sender, EventArgs e)
        {
            Frm_ELEC_REVE_KM_OUT_S frm = new Frm_ELEC_REVE_KM_OUT_S();
            frm.FrmSet = this;

            SysFun cls_FUN = new SysFun();

            cls_FUN.YssAssocia = ClsClzCfgMgr.getAssociaParam("reveDzKmMap");
            //// 这里手动赋值，否则当获取提示信息时通过cls_FUN.C_FUN_CODE
            //// 时会出现异常YssCore.Info.ClsRetInfoDealer.getFunCode方法
            cls_FUN.C_FUN_CODE = cls_FUN.YssAssocia.FunCode;
            cls_FUN.C_FUN_NAME = cls_FUN.YssAssocia.FunName;

            //frm.ShowInTaskbar = false;

            frm.YssFormMenu = cls_FUN;

            //frm_ELEC_BBINFO_S.yssInitForm();
            frm.yssShowForm();
        }

        private void saveMapRelaRecord()
        {
            ArrayList list = getKmRelaRecordsFromInnerOutTable();
            if(list==null||list.Count==0)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("请添加映射关系！"));
                return;
            }
            foreach (object pojo in list)
            {
                this.setAddOperPojoInfo((BasePojo)pojo);
            }
            string operResult = kmRelaRecordService.insert(list);
            //operAfterSave(operResult);
            if (ClsRetInfoDealer.isJsonInfo(operResult) && operResult.Trim() != "")
            {
                ClsRetInfo retInfo = ClsRetInfoDealer.getReturnInfo(operResult);
                if (retInfo.operRes == "Success")
                {
                    /////*Author:ChenLong Date:2013-11-19 Status:Modify Comment:定位当前保存的数据 保证set界面显示当前保存的数据*/
                    /////* 将保存后返回的数据C_IDEN返回值标记下来 TODO若返回多个C_IDEN 暂时取第一个 */
                    ////if (retInfo.cidenList != null && retInfo.cidenList.Count > 0)
                    ////{
                    ////    _modifiedRowIds = retInfo.cidenList;
                    ////}

                    if (null != frmBaseViewList)
                    {
                        if (!isLeftSetForm)
                        {
                            frmBaseViewList.getMainListDataMVC(new BasePojo(), true);
                        }
                    }

                }
            }
            YssMessageBox.ShowCommonInfo(operResult);
            this.tableInOut.Rows.Clear();
            this.tableInOut.Refresh();
            this.rowRecords.Clear();
            //loadKmTable();
        }

        private void cboYSFW_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.tableInOut.Rows.Count > 0)
            {
                if (YssMessageBox.ShowQuestion("变更加载条件会清空映射关系！是否保存后清空？", "提示") == System.Windows.Forms.DialogResult.Yes)
                {
                    saveMapRelaRecord();
                    this.tableInOut.ClearRows();
                    this.tableInOut.Refresh();
                }
                else
                {
                    this.tableInOut.ClearRows();
                    this.tableInOut.Refresh();
                }
            }
            loadKmTable();
        }

        /// <summary>
        /// 显示单条数据
        /// </summary>
        public override void yssShowInfoAddForm()
        {
            try
            {
                if (this.frmBaseViewList is Frm_ELEC_REVE_PUBLIC_KMMAP_L)
                {
                    this.cboYSFW.Value = ReveElecDVCons.REVE_YSFW_GGYS;
                }else if (this.frmBaseViewList is Frm_ELEC_REVE_TGH_KMMAP_L)
                {
                    this.cboYSFW.Value = ReveElecDVCons.REVE_YSFW_TGFYS;
                }
                else if (this.frmBaseViewList is Frm_ELEC_REVE_PORT_KMMAP_L)
                {
                    this.cboYSFW.Value = ReveElecDVCons.REVE_YSFW_CPYS;
                }
                
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }
        }

        /// <summary>
        /// 鼠标移开事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void dateBus_OnMouseLeave(object sender, EventArgs e)
        {
            ////捕获这次日期改变
            if (this.isDateChange)
            {
                this.isDateChange = false;
                loadKmTable();
            }
        }

        /// <summary>
        /// 日期改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void dateBus_OnValueChanged(object sender, EventArgs e)
        {
            ////标记日期改变
            this.isDateChange = true;
        }
        
    }
}
