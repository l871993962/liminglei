using System;
using System.Collections.Generic;
using System.Drawing;
using System.Threading;
using FAST.Common.Service.Interface;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.BaseControl;
using FAST.Core.BaseControl.Pojo;
using FAST.Core.Bussiness.Form;
using FAST.Core.Communication.BusiService;
using FAST.Core.Communication.Service;
using FAST.Core.Context;
using FAST.Core.Context.Events;
using FAST.Core.CRUD.Form;
using FAST.Core.Util;
using Yss.KTable.Models;
using YssElecReco.Pojo.Er;
using YssElecReco.Service.Er;
using YssElecReco.Fun;

namespace YssElecReco.Form.Er
{
    /// <summary>
    /// 功能简介：电子对账数据生成界面
    /// 创建版本：betaV1.0.0.19
    /// 创建人： fangjialiang
    /// 创建日期： 2013.01.21
    /// </summary>
    public partial class Frm_ELEC_GENE_L : FrmBaseOper, I_Workflow
    {
        /// <summary>
        /// 父窗体
        /// </summary>
        private FrmBaseList frmBaseList;

        /// <summary>
        /// 任务调度
        /// </summary>
        private bool bTask = false;

        /// <summary>
        /// 主框中A区被选中行对应的Port
        /// </summary>
        private List<Port> checkedPortList = new List<Port>();

        /// <summary>
        /// 分段组合的最大数
        /// 每maxPort个组合分一段
        /// </summary>
        private int maxPort = 50;

        /// <summary>
        /// 执行时间控件
        /// </summary>
        private YssDateTimeInterval dateTimeInterval = null;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_ELEC_GENE_L()
        {
            leftDataFunCode = YssProductInfo.Support.Context.AssociaType.pd_portfolio.ToString();
            bUseMVCService = true;
            bUseMVCServiceLeft = true;
            logInfoType = ClsEnums.InfoType._BUSNESSLOG_DZ;
            receiveCustomLogBus = new receiveMegLog(getCustomLog);
            InitializeComponent();
            yssDateTime.DynamicSetSecondDateYearStep = 1;
            this._useByThread = true;

            //// add by liuxiang 2015-7-15
            //// BUG #115387 [紧急][招商证券]部分权限设置无效
            ////if (!ClsContext.HtUserOperRight.ContainsKey("elecGene")) 
            ////{
            ////    List<string> operationList = new List<string>();
            ////    operationList.Add("DEL");
            ////    operationList.Add("EXE");
            ////    YssBaseCls.Fun.ClsAuthParaUtil authParaUtil = authParaUtil = new YssBaseCls.Fun.ClsAuthParaUtil();
            ////    operationList = authParaUtil.btnCodeStandardChange(operationList);
            ////    ClsContext.HtUserOperRight.Add("elecGene", operationList);
            ////}

            isHasPortCode = true;
            this._useByThread = true;
        }

        /// <summary>
        /// 执行时间控件
        /// </summary>
        public YssDateTimeInterval DateTimeInterval
        {
            set 
            { 
                dateTimeInterval = value;
                this.yssDateTime.setDateTime(dateTimeInterval.getBeginDate);
                if (dateTimeInterval.YssShowSecond)
                {
                    this.yssDateTime.SetDateTimeInput2(dateTimeInterval.getEndDate);
                    this.yssDateTime.YssShowSecond = dateTimeInterval.YssShowSecond;
                }
            
            }

            get
            { 
                return dateTimeInterval; 
            }
        }

        /// <summary>
        /// 展示时被勾选的组合
        /// </summary>
        public List<Port> CheckedPortList
        {
            set { checkedPortList = value; }
            get { return checkedPortList; }
        }

        

        ////STORY #50945 FrmBaseList基类，更换默认A区Bar（barPort）工具条为ToolStrip，并收回barPort访问权限。（使用公共A区，此段代码无效）张绍林20171229
        /////// <summary>
        /////// 已勾选组合个数
        /////// </summary>
        ////public void setCount(int chkCount)
        ////{
        ////    this.chkBoxCheckedRowsCount.Text = Convert.ToString(chkCount);
        ////}

        

        /// <summary>
        /// list菜单
        /// </summary>
        /// <param name="frmBaseList">frmBaseList</param>
        public void setList(FrmBaseList frmBaseList)
        {
            this.frmBaseList = frmBaseList;
        }

        /// <summary>
        /// 窗体加载事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ELEC_GENE_L_Load(object sender, EventArgs e)
        {
            ////重复的冗余代码删除-张绍林-20150525
            ////base.FrmBase_Load(sender, e);
            //// 初始化日期控件
            if (this.DateTimeInterval != null)
            {
                this.yssDateTime.setDateTime(this.DateTimeInterval.getBeginDate);
                if (this.DateTimeInterval.YssShowSecond)
                {
                    this.yssDateTime.SetDateTimeInput2(this.DateTimeInterval.getEndDate);
                    this.yssDateTime.YssShowSecond = this.DateTimeInterval.YssShowSecond;
                }
            }

            this.btnBarOper.setAllButtonEnabled(true);
            ////loadLeftDataProcMVC();
            ////this.baseDataobject = new ElecGroupRela();
            ////////this.bUseMVCService = false;
            ////this.yssInitForm();
            ////getMainListDataMVC(baseDataobject, true);
        }


        /////// <summary>
        /////// 重写加载A区组合方法
        /////// </summary>
        ////protected override void loadLeftDataProcMVC()
        ////{
        ////    base.loadLeftDataProcMVC();
        ////    //////// 加载A区信息
        ////    ////new TableListLoader().loadTable(tbLeftMain, loadPortData(), false, false, ClsEnums.KTableDataShowMode.TreeMode, getLeftTableShowColumn());
        ////    ////this.tbLeftMain.CheckChildWidthParent = false;
        ////    this.tbLeftMain.CheckParentWidthChild = false;
        ////    //// 勾选指定组合
        ////    if (checkedPortList.Count > 0 && tbLeftMain.Rows.Count > 0)
        ////    {
        ////        string portCodes = "";
        ////        foreach (Port_A port in checkedPortList)
        ////        {
        ////            portCodes += port.C_PORT_CODE + ";";
        ////        }

        ////        foreach (Row row in tbLeftMain.Rows)
        ////        {
        ////            Port_A port = row.Tag as Port_A;
                    
        ////            if (portCodes.Contains(port.C_PORT_CODE))
        ////            {
        ////                row.Checked = true;
        ////            }
        ////            checkSubRow(row, portCodes);
        ////        }
        ////    }

        ////    ////this.tbLeftMain.CheckChildWidthParent = false;
        ////    this.tbLeftMain.CheckParentWidthChild = true;
        ////}

        /////// <summary>
        /////// 选中指定行
        /////// </summary>
        /////// <param name="port">port</param>
        ////private void checkSubRow(Row row,string portCodes)
        ////{
        ////    if (row.SubRows != null && row.SubRows.Count > 0) 
        ////    {
        ////        foreach (Row row1 in row.SubRows)
        ////        {
        ////            Port_A port = row1.Tag as Port_A;

        ////            if (portCodes.Contains(port.C_PORT_CODE))
        ////            {
        ////                row1.Checked = true;
        ////            }
        ////            checkSubRow(row1, portCodes);
        ////        }
        ////    }
        ////}

        /////// <summary>
        /////// 子行的循环
        /////// </summary>
        /////// <param name="row">row</param>
        /////// <param name="port">port</param>
        ////private void checkSubRowByCheckedPort(Row row, Port_A port)
        ////{
        ////    if (!isChecked(row))
        ////    {
        ////        row.Checked = false;
        ////    }

        ////    if (port.C_PORT_CODE.Equals(((Port_A)row.Tag as Port_A).C_PORT_CODE))
        ////    {
        ////        row.Checked = true;
        ////        return;
        ////    }
        ////    if (row.SubRows.Count > 0)
        ////    {
        ////        foreach (Row subRow in row.SubRows)
        ////        {
        ////            this.checkSubRowByCheckedPort(subRow, port);
        ////        }
        ////    }
        ////}

        /////// <summary>
        /////// 判断是否包含该行
        /////// </summary>
        /////// <returns></returns>
        ////private bool isChecked(Row row)
        ////{
        ////    string portCode = ((Port_A)row.Tag as Port_A).C_PORT_CODE;
        ////    foreach (Port_A port in checkedPortList)
        ////    {
        ////        if (port.C_PORT_CODE.Equals(portCode))
        ////        {

        ////            return true;
        ////        }
        ////    }

        ////    return false;
        ////}

        /// <summary>
        /// 任务的行数
        /// </summary>
        /// <returns>count</returns>
        public int getTaskCount()
        {
            return 1;
        }

        /// <summary>
        /// 初始化
        /// </summary>
        /// <param name="lstSelPort">选中的组合</param>
        /// <param name="lstSelItems">选中的项目</param>
        public void setInit(Dictionary<string, string> lstSelPort, Dictionary<string, string> lstSelItems)
        {
            _leftPojos = lstSelPort;
            _selItems = lstSelItems;

            if (frmOperMes == null)
            {
                bTask = true;
                this.FrmBase_Load(this, System.EventArgs.Empty);
            }
        }

        /// <summary>
        /// 一键式的执行
        /// </summary>
        /// <param name="idfCode">来源</param>
        /// <param name="funRelaId">功能关联id</param>
        /// <returns>返回执行结果</returns>
        public string doWorkflow(string idfCode, string funRelaId)
        {
            unchecked
            {
                try
                {
                    this.planCode = idfCode;
                    this.funRelaId = funRelaId;
                    doExecute(); ////调用基类方法执行业务 byleeyu 20120627

                    return "Success";
                }
                catch (Exception)
                {
                    return "Fail";

                }
            }
        }

        /// <summary>
        /// .
        /// </summary>
        /// <returns>字符串</returns>
        public string doWorkflowAndShowForm()
        {
            throw new NotImplementedException();
        }

        /// <summary>
        /// B区加载数据的方法
        /// </summary>
        /// <param name="pojo">pojo</param>
        /// <param name="bQueryData">bQueryData</param>
        /// <returns>3</returns>
        public override QueryRes getMainListDataMVC(BasePojo pojo, bool bQueryData)
        {
            QueryRes res = new QueryRes();
            IElecGeneService svc = ServiceFactory.createService<IElecGeneService>();
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("dataClass", "ElecGroupRela");
            res = svc.queryByCondition(paraDict);
            ElecClsTreeLeafList ctl = new ElecClsTreeLeafList();
            ctl.drawCheckIconCell_New(tbMain, res.DataList, 5);
            List<string> list = new List<string>();
            list.Add("1011"); ////招商证券默认只勾选估值表
            list.Add("1001"); ////bug123582 by weijj 20151206
            ctl.checkedCheckIconCell(tbMain.Rows, list, "C_ELEC_CODE");
            return new QueryRes();
        }


        /// <summary>
        /// 解析和加载主区域数据的方法，因清算项的加载很特殊，故重写父类的加载方法
        /// </summary>
        /// <param name="res">res</param>
        private void loadMainTable(QueryRes res)
        {
            Yss.KTable.Models.Column col = null; ////KTable 列对象
            Yss.KTable.Models.Row row = null; ////行
            Yss.KTable.Models.CheckBoxCell cell = null; ////单元格 
            Yss.KTable.Models.Row childRow = null; ////行
            int count = 0; ////记录加载列数
            Dictionary<string, List<ElecGroupRela>> showItem = new Dictionary<string, List<ElecGroupRela>>();

            Dictionary<string, CheckBoxCell> dictCells = new Dictionary<string, CheckBoxCell>();

            List<BasePojo> dataList = res.DataList;
            //// 加载列头
            for (int i = 0; i < 5; i++)
            {
                col = new Yss.KTable.Models.Column();
                col.Text = "";
                if (i == 0)
                {
                    col.Width = 170;
                }
                else
                {
                    col.Width = 120;
                }

                tbMain.Columns.Add(col);
            }

            ElecGroupRela elecGroupRela = null;
            for (int i = 0; i < dataList.Count; i++)
            {
                ////Regex.Split(acctItemAry[i], "\t")，数据组成：0：代码，1：名称，2：父节点代码

                elecGroupRela = new ElecGroupRela();
                elecGroupRela = dataList[i] as ElecGroupRela;

                // 如果为父节点，直接创建行
                if (elecGroupRela.C_PARENT_CODE.Equals("[root]"))
                {
                    if (showItem.ContainsKey(elecGroupRela.C_ELEC_CODE))
                    {
                        List<ElecGroupRela> temp = showItem[elecGroupRela.C_ELEC_CODE] as List<ElecGroupRela>;
                        temp.Add(elecGroupRela);
                        showItem.Remove(elecGroupRela.C_ELEC_CODE);
                        showItem.Add(elecGroupRela.C_ELEC_CODE, temp);
                    }
                    else
                    {
                        List<ElecGroupRela> temp = new List<ElecGroupRela>();
                        temp.Add(elecGroupRela);
                        showItem.Add(elecGroupRela.C_ELEC_CODE, temp);
                    }
                }
                else
                {
                    if (showItem.ContainsKey(elecGroupRela.C_PARENT_CODE))
                    {
                        List<ElecGroupRela> temp = showItem[elecGroupRela.C_PARENT_CODE] as List<ElecGroupRela>;
                        temp.Add(elecGroupRela);
                        showItem.Remove(elecGroupRela.C_PARENT_CODE);
                        showItem.Add(elecGroupRela.C_PARENT_CODE, temp);
                    }
                    else
                    {
                        List<ElecGroupRela> temp = new List<ElecGroupRela>();
                        temp.Add(elecGroupRela);
                        showItem.Add(elecGroupRela.C_PARENT_CODE, temp);
                    }
                }
            }

            foreach (string skey in showItem.Keys)
            {
                List<ElecGroupRela> list = showItem[skey] as List<ElecGroupRela>;
                if (list.Count > 1)
                {
                    row = new Yss.KTable.Models.Row(); ////创建行
                    int rowNum = 0;
                    foreach (ElecGroupRela rowElecGroupRela in list)
                    {
                        // 如果为父节点，直接创建行
                        if (rowElecGroupRela.C_PARENT_CODE.Equals("[root]"))
                        {
                            if (row != null)
                            {
                                this.tbMain.Rows.Add(row);
                            }

                            cell = new Yss.KTable.Models.CheckBoxCell();
                            cell.Text = rowElecGroupRela.C_ELEC_NAME;
                            cell.Font = new Font(tbMain.Font, FontStyle.Bold);
                            row.Cells.Add(cell); ////加入行单元格
                            row.Tag = elecGroupRela;

                            dictCells.Add(rowElecGroupRela.C_ELEC_CODE, cell);
                        }
                        else
                        {
                            rowNum++;
                            //// 非父节点，每行5个核算项
                            if (count % 5 == 0)
                            {
                                // 计数为0时创建行
                                childRow = new Yss.KTable.Models.Row();
                            }

                            cell = new Yss.KTable.Models.CheckBoxCell();
                            cell.Text = rowElecGroupRela.C_ELEC_NAME;
                            cell.Tag = rowElecGroupRela;
                            childRow.Cells.Add(cell); ////行记录中增加单元格
                            dictCells.Add(rowElecGroupRela.C_ELEC_CODE, cell);
                            count++;

                            // 当加到第5个核算项时，整行加入到父节点的subrow下，同时清空子节点行
                            if (count != 0 && count % 5 == 0)
                            {
                                count = 0;
                                row.SubRows.Add(childRow);
                            }
                            else if (rowNum == list.Count - 1)
                            {
                                count = 0;
                                row.SubRows.Add(childRow);
                                childRow = null;
                            }
                        }

                    }
                }
            }

            tbMain.Tag = dictCells;
            tbMain.AutoWidth();
            tbMain.Refresh();
        }

        /// <summary>
        /// 单元格checked属性改变事件
        /// 控制字节点联动
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void tbMain_CheckStateChanged(object sender, Yss.KTable.Events.CheckStateChangeEventArgs e)
        {
            int count = 0; ////判断是否子节点全部选中
            Yss.KTable.Models.CheckBoxCell curCell = (Yss.KTable.Models.CheckBoxCell)sender;
            Yss.KTable.Models.CheckBoxCell parentRowCell = null;

            //// 父子节点状态同步
            // 如果为父节点，则此节点下的所有子节点的勾选状态与其同步
            if (curCell.OwnRow.HasChild)
            {
                foreach (Row row in curCell.OwnRow.SubRows)
                {
                    foreach (CheckBoxCell tmpCell in row.Cells)
                    {
                        tmpCell.Checked = curCell.Checked;
                    }
                }
            }
            else
            {
                //// 循环子节点行记录
                foreach (Row row in curCell.OwnRow.ParentRow.SubRows)
                {
                    count += row.Cells.Count;
                    foreach (CheckBoxCell tmpCell in row.Cells)
                    {
                        // 如果状态相同，则子节点数量减1
                        if (tmpCell.Checked.ToString().Equals(curCell.Checked.ToString()))
                        {
                            count--;
                        }
                    }
                }

                // 如果最后计数器为0，则表示子节点的状态一致
                if (count == 0)
                {
                    parentRowCell = (CheckBoxCell)curCell.OwnRow.ParentRow.Cells[0];
                    parentRowCell.Checked = curCell.Checked;
                }
            }
        }

        /// <summary>
        /// 获得分段后的组合
        /// </summary>
        /// <returns>1</returns>
        private List<string> getPortList() 
        {
            List<string> list = new List<string>();
            //// 实现多线程处理：一个组合一个线程byleeyu20130811
            ////StringBuilder buf = new StringBuilder();
            ////分别记录，防止漏掉组合
            ////int i = 0;
            ////int j = 0;
            foreach (KeyValuePair<string, string> pojo in this._leftPojos)
            {
                list.Add(pojo.Key);
                ////i++;
                ////j++;
                ////if (i < maxPort)
                ////{
                ////    buf.Append(pojo.Key);
                ////    if (j < _leftPojos.Count)
                ////    {
                ////        buf.Append(",");
                ////    }
                ////    else
                ////    {
                ////        list.Add(buf.ToString());
                ////    }
                ////}
                ////else
                ////{
                ////    buf.Append(pojo.Key);
                ////    list.Add(buf.ToString());
                ////    buf.Remove(0, buf.Length);
                ////    buf.Length = 0;
                ////    i = 0;
                ////}
            }

            return list;
        }

        /////// <summary>
        /////// 根据默认信息设置主区域的数据
        /////// </summary>
        ////private void setCfgData()
        ////{
            ////try
            ////{
            ////    string[] strCFG = null; // 接口数组
            ////    ElecGroupRela strGroupRela = null; // 可口关联对象
            ////    Dictionary<string, Yss.KTable.Models.CheckIconCell> tbMainCells;
            ////    //// 获取数据上传的数据
            ////    object obj = this.dataAdmin.GetSpecValue("", "getCfgInfo", "daogroupsetRela");
            ////    if (null == obj)
            ////    {
            ////        return;
            ////    }

            ////    if (tbMain.Tag == null)
            ////    {
            ////        return;
            ////    }

            ////    tbMainCells = tbMain.Tag as Dictionary<string, Yss.KTable.Models.CheckIconCell>;

            ////    strCFG = Regex.Split(obj.ToString(), "\r\t"); // 获取接口数组
            ////    ////循环接口数据，把数据解析成对象进行处理
            ////    foreach (string str in strCFG)
            ////    {
            ////        if (str.Length == 0)
            ////        {
            ////            continue;
            ////        }

            ////        strGroupRela = new Cls_IC_GROUP_RELA(); // 接口分组
            ////        strGroupRela.listParse(str); // 解析数据

            ////        if (tbMainCells.ContainsKey(strGroupRela.NodeCode))
            ////        {
            ////            tbMainCells[strGroupRela.NodeCode].Checked = true;
            ////        }
            ////    }
            ////}
            ////catch (Exception ex)
            ////{
            ////    ////YssMessageBox.ShowDyanInformation("获取所有的接口信息错误错误！", ex.Message, MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
            ////    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500060", _formFun, status));
            ////    ClsBaseException.DiscardException(ex);
            ////}
        ////}

        /////// <summary>
        /////// 根据当前用户岗位权限设置左区域的数据选中状态
        /////// </summary>
        ////private void setLeftCheck()
        ////{
        ////    Dictionary<string, Yss.KTable.Models.Row> leftTags = null;
        ////    if (tbLeftMain.Tag == null)
        ////    {
        ////        return;
        ////    }

        ////    leftTags = tbLeftMain.Tag as Dictionary<string, Yss.KTable.Models.Row>;

        ////    string[] arrPort = Regex.Split(ClsContext.DataRightString, ",");
        ////    foreach (string c_Port in arrPort)
        ////    {
        ////        if (leftTags.ContainsKey(c_Port))
        ////        {
        ////            leftTags[c_Port].Checked = true;
        ////        }
        ////    }
        ////}

        /// <summary>
        /// 虚方法，防止异常
        /// </summary>
        protected override void initServiceMVC()
        {
        }

        /// <summary>
        /// 获取处理服务实例
        /// </summary>
        protected override void setOperServiceInstance()
        {
            if (operSVC == null)
            {
                if (bTask)
                {
                    operSVC = BusiOperServiceFactory.createService<IElecTaskService>();
                }
                else
                {
                    operSVC = BusiOperServiceFactory.createService<IElecTaskService>();
                }
            }
        }
        
        #region 日志处理

        /// <summary>
        /// 设置级别结构
        /// </summary>
        /// <param name="structRecord">保存级别的结构</param>
        protected override void buildRecordStruct(ref string[] structRecord)
        {
            //// structRecord = new string[] { "DATE", "PORT", "C_CFG_CODE" };
            //// structRecord = new string[] { "PORT", "DATE", "C_CFG_CODE" };
            structRecord = new string[] { "PORT", "DATE", "C_ELEC_CODE" };
        }


        #endregion

        #region 执行清算处理

        /// <summary>
        /// 执行
        /// </summary>
        /// <returns>string</returns>
        public override string doSubSection()
        {
            string c_Result = "";
            bool bCancel = false;

            //// 1：执行前的一些处理
            beforeExecute(ref bCancel);

            if (!bCancel)
            {
                setOperServiceInstance();

                List<ThreadStart> threadStarts = new List<ThreadStart>();
                ////第一批次线程（处理公共接口），这里要先执行完之后，才能执行下面批次的线程。
                foreach (KeyValuePair<string, string> kv in this._selItems)
                {
                    foreach (string ports in getPortList())
                    {
                        //// kv.Key = 报表类型_电子对账类型
                        //// 估值表、科目表、余额表生成每个业务日期日报
                        //// 资产负债表、利润表、所有者权益变动表根据报表类型执行月、季、半年、年末和业务截止日期的电子对账生成
                        if (kv.Key.IndexOf("_") < 0 || (kv.Key.IndexOf("_") > -1 && checkDateByReport(kv.Key.Substring(0, 2))))
                        {
                            Dictionary<string, string> pdict = new Dictionary<string, string>();
                            pdict.Add("ARRAY_C_PORT_CODE", ports);
                            pdict.Add("ARRAY_C_DZ_CODE", kv.Key);
                            pdict.Add("D_START_DATE", this.d_OperCurr.ToString("yyyy-MM-dd"));
                            pdict.Add("D_END_DATE", this.d_OperCurr.ToString("yyyy-MM-dd"));
                            pdict.Add("C_FUN_CODE", this._formFun.C_FUN_CODE); ////BUG19529821.6版本回归测试bug 1.产生电子对账页面业务历史日志查询为空
                            pdict.Add("C_PORT_CODE", ports);
                            pdict.Add("C_OPER_CODE", execOperCode);
                            pdict.Add("OPER_TYPE", "GENE");
                            pdict.Add("C_IDF_CODE", this.planCode);
                            pdict.Add("C_RELA_ID", this.funRelaId);
                            /**20150720 added by liubo.STORY #24163 #26344任务调度日志修改成前台可查看
                               本次操作关联的调度方案的执行编号*/
                            pdict.Add("C_DISPATCH_ID", c_Dispatch_ID);

                            threadStarts.Add(delegate { this.doMethod(pdict); });
                        }
                    }
                }

                if (this.ThreadPool.ThreadCount > 0)
                {
                    ////如果线程已经启动，则将新线程附加到新的队列里
                    this.ThreadPool.JoinThread(threadStarts);
                }
                else
                {
                    ////如果尚未启动线程，则直接增加线程
                    foreach (ThreadStart tempThreadStart in threadStarts)
                    {
                        this.ThreadPool.AddThread(tempThreadStart);
                    }
                }

                ////threadStarts = new List<ThreadStart>();
                ////////注意：这里是在附加第二批次的线程（处理组合接口），此批次的线程会等到上面批次执行完之后才去执行。
                ////foreach (KeyValuePair<string, string> kv in this._selItems)
                ////{
                ////    Dictionary<string, string> pdict = new Dictionary<string, string>();
                ////    pdict.Add("ARRAY_C_PORT_CODE", this.getSelPortCodes());
                ////    pdict.Add("ARRAY_C_DZ_CODE", kv.Key);
                ////    pdict.Add("D_START_DATE", this.d_OperCurr.ToString("yyyy-MM-dd"));
                ////    pdict.Add("D_END_DATE", this.d_OperCurr.ToString("yyyy-MM-dd"));
                ////    pdict.Add("C_OPER_CODE", execOperCode);

                ////    threadStarts.Add(delegate { this.doMethod(pdict); });
                ////}

                ////string elecCode = "";
                ////foreach (ElecGroupRela ic in this._ics)
                ////{
                ////    elecCode += ic.C_ELEC_CODE + ",";
                ////}

                ////Dictionary<string, string> pdict = new Dictionary<string, string>();
                ////pdict.Add("ARRAY_C_PORT_CODE", this._ports);
                ////pdict.Add("ARRAY_C_DZ_CODE", elecCode);
                ////pdict.Add("D_START_DATE", this.d_OperCurr.ToString("yyyy-MM-dd"));
                ////pdict.Add("D_END_DATE", this.d_OperCurr.ToString("yyyy-MM-dd"));
                ////pdict.Add("C_OPER_CODE", execOperCode);

                ////threadStarts.Add(delegate { this.doMethod(pdict); });

                ////if (this.ThreadPool.ThreadCount > 0)
                ////{
                ////    ////如果线程已经启动，则将新线程附加到新的队列里
                ////    this.ThreadPool.JoinThread(threadStarts);
                ////}
                ////else
                ////{
                ////    ////如果尚未启动线程，则直接增加线程
                ////    foreach (ThreadStart loThreadStart in threadStarts)
                ////    {
                ////        this.ThreadPool.AddThread(loThreadStart);
                ////    }
                ////}
            }

            //// 3：执行后的一些处理
            afterExecute();

            return c_Result;
        }

        /// <summary>
        /// 执行前的判断
        /// </summary>
        /// <param name="rptType">rptType</param>
        /// <returns>bool</returns>
        public bool checkDateByReport(string rptType)
        {
            string sDate = this.d_OperCurr.ToString("yyyyMMdd");
            string month = sDate.Substring(4, 2);
            int num = (this.d_OperCurr.Month - 1) / 3;
            //// 日期控件最后一天所有报表类型都会执行生成
            if (this.d_OperCurr == this.yssDateTime.getEndDate)
            {
                return true;
            }

            if (rptType == "03")
            {
                //// 月报
                if (month.Equals("02"))
                {
                    if ((this.d_OperCurr.Year % 100 == 0 && this.d_OperCurr.Year % 400 == 0)
                        || (this.d_OperCurr.Year % 100 != 0 && this.d_OperCurr.Year % 4 == 0))
                    {
                       //// 闰月
                       if (sDate.Substring(0, 6) + "29" == sDate)
                       {
                           return true;
                       }
                    } 
                    else 
                    {
                        if (sDate.Substring(0, 6) + "28" == sDate)
                        {
                            return true;
                        }
                    } 
                }
                else if (month.Equals("04") || month.Equals("06") || month.Equals("09") || month.Equals("11"))
                {
                    //// 小月
                    if (sDate.Substring(0, 6) + "30" == sDate)
                    {
                        return true;
                    }
                }
                else
                {
                    //// 大月
                    if (sDate.Substring(0, 6) + "31" == sDate)
                    {
                        return true;
                    }
                }
            }
            else if (rptType == "04")
            {
                //// 季报
                string quarter = "";
                if (num == 0)
                {
                    quarter = "03";
                }
                else if (num == 1)
                {
                    quarter = "06";
                }
                else if (num == 2)
                {
                    quarter = "09";
                }
                else
                {
                    quarter = "12";
                }

                if (((num == 1 || num == 2) && sDate.Substring(0, 4) + quarter + "30" == sDate)
                    || ((num == 0 || num == 3) && sDate.Substring(0, 4) + quarter + "31" == sDate)) 
                {
                    return true;
                }
            }
            else if (rptType == "05")
            {
                //// 半年报
                if (sDate == sDate.Substring(0, 4) + "0630" || sDate == sDate.Substring(0, 4) + "1231")
                {
                    return true;
                }
            } 
            else
            { //// 年报
                if (sDate == sDate.Substring(0, 4) + "1231")
                {
                    return true;
                }
            }

            return false;
        }

        /// <summary>
        /// 重置线程任务信息-重写
        /// </summary>
        /// <param name="taskCount">预计任务总数</param>
        protected override void ResetProgressInfo(int taskCount)
        {
            ////taskCount = this.doSubSectionOperation() * (this._comms.Count + this._ics.Count);
            taskCount = this.doSubSectionOperation() * this._selItems.Count * getPortList().Count;
            this.ResetTaskInfo(taskCount);
        }

        /// <summary>
        /// 获取主区域的选中的数据
        /// </summary>
        /// <returns>保存选中行的POJO集合</returns>
        protected override Dictionary<string, string> getSelOperItemTags()
        {
            Dictionary<string, string> lstPojos = new Dictionary<string, string>();
            foreach (Row row in tbMain.Rows)
            {
                foreach (Row subRow in row.SubRows)
                {
                    ////base {Yss.KTable.Models.Cell} = {Yss.KTable.Models.CheckBoxCell}
                    foreach (Cell cell in subRow.Cells)
                    {
                        CheckBoxCell cbc = cell as CheckBoxCell;
                        if (cbc.Checked)
                        {
                            ElecGroupRela elec = cell.Tag as ElecGroupRela;
                            lstPojos.Add(elec.C_ELEC_CODE, elec.C_ELEC_NAME);
                        }
                    }
                }
            }

            return lstPojos;
        }

        /////// <summary>
        ///////  重写基类方法，在这里处理本地Pojo
        /////// </summary>
        /////// <returns>返回基类Pojo列表</returns>
        ////public override List<BasePojo> yssGetSelTypeItemLeftList()
        ////{
        ////    List<BasePojo> retPojos = base.yssGetSelTypeItemLeftList();

        ////    this._ports = "";
        ////    foreach (Port_A tempPort in retPojos)
        ////    {
        ////        this._ports += tempPort.C_PORT_CODE + ",";
        ////    }

        ////    this._ports = _ports.TrimEnd(',');
        ////    ////this._comms = this.getCommonInterface();
        ////    this._ics = this.getPortInterface();

        ////    return retPojos;
        ////}

        /// <summary>
        /// 获取选中的公共接口
        /// </summary>
        /// <returns>公共接口集合</returns>
        ////private List<ElecGroupRela> getCommonInterface()
        ////{
        ////    List<ElecGroupRela> comms = new List<ElecGroupRela>();
        ////    ElecGroupRela elecGroupRela = null;
        ////    List<BasePojo> lstItems = getSelOperItemTags();
        ////    foreach (object obj in lstItems)
        ////    {
        ////        elecGroupRela = obj as ElecGroupRela;
        ////        if (elecGroupRela.C_DV_DIF_CODE == "PUB_MP")
        ////        {
        ////            comms.Add(clsIcGroupRela);
        ////        }
        ////    }

        ////    return comms;
        ////}

        /////// <summary>
        /////// 获取组合接口
        /////// </summary>
        /////// <returns>组合接口集合</returns>
        ////private List<ElecGroupRela> getPortInterface()
        ////{
        ////    List<ElecGroupRela> ics = new List<ElecGroupRela>();
        ////    ElecGroupRela elecGroupRela = null;
        ////    List<BasePojo> lstItems = getSelOperItemTags();
        ////    foreach (object obj in lstItems)
        ////    {
        ////        elecGroupRela = obj as ElecGroupRela;
        ////        ics.Add(elecGroupRela);
        ////    }

        ////    return ics;
        ////}

        /// <summary>
        /// 设置参数
        /// </summary>
        /// <param name="paraDict">参数集合</param>
        protected override void setParaDict(Dictionary<string, string> paraDict)
        {
            string itemStr = "";
            ////ElecGroupRela elecGroupRela = null;
            ////List<BasePojo> lstItems = (List<BasePojo>)getSelOperItemTags();
            foreach (KeyValuePair<string, string> obj in _selItems)
            {
                ////elecGroupRela = obj as ElecGroupRela;
                itemStr += obj.Key + ",";
            }

            if (itemStr.EndsWith(","))
            {
                itemStr = itemStr.Remove(itemStr.Length - 1);
            }
            
            paraDict.Add("C_PORT_CODE", getSelPortCodes());
            paraDict.Add("C_OPER_ITEM", itemStr);
            paraDict.Add("D_START", yssDateTime.getBeginDate.ToString("yyyy-MM-dd"));
            paraDict.Add("D_END", yssDateTime.getEndDate.ToString("yyyy-MM-dd"));
            paraDict.Add("C_OPER_CODE", execOperCode);
            paraDict.Add("C_RELA_ID", funRelaId); // 功能关联id
        }
        #endregion
        /// <summary>
        /// 资产清算不需要查询，避免快捷键操作报错
        /// </summary>
        /// <param name="sender">ButtonItem</param>
        /// <param name="e">EventArgs</param>
        public override void btnSearch_Click(object sender, EventArgs e)
        {
            //// 重新加载执行项目 liuxiang 2017-1-6 BUG147177汇集调尾处理界面缺少查询按钮
            this.tbMain.Clear();
            getMainListDataMVC(baseDataobject, loadDataWhenFormLoad);
        }

        /// <summary>
        /// 窗体关闭是执行
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ELEC_GENE_L_YssOnFrmClose(object sender, YssFormEventArgs e)
        {
            ClsButtonInfo buttonInfo = btnBarOper.getButton("btnStop");
            e.isCancel = false;
            if (buttonInfo != null && buttonInfo.Enabled)
            {
                Yss.KMessage.MessageDialog msgDialog = new Yss.KMessage.MessageDialog();
                System.Windows.Forms.DialogResult diaResult = msgDialog.Show("您要终止任务吗？", "提示", System.Windows.Forms.MessageBoxButtons.YesNo, System.Windows.Forms.MessageBoxIcon.Question);
                if (diaResult == System.Windows.Forms.DialogResult.Yes)
                {
                    this.StopThreadPool();
                }
                else 
                {
                    e.isCancel = true;
                }
            }

            ////if (this.frmBaseList != null && !e.isCancel)
            ////{
            ////    this.frmBaseList.btnSearch_Click(null, null);
            ////}
        }

        #region I_Workflow 成员

        /// <summary>
        /// 初始化
        /// </summary>
        /// <param name="lstSelPort">lstSelPort</param>
        /// <param name="lstSelItems">lstSelItems</param>
        public void setInit(List<string> lstSelPort, List<string> lstSelItems)
        {
            throw new NotImplementedException();
        }

        #endregion
    }
}
