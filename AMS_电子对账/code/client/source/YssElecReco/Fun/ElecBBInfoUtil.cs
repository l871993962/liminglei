using System;
using System.Collections;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using Yss.KTable.Models;
using YssElecReco.Pojo.Er;
using FAST.Core.Context;
using FAST.Common.Service.Pojo;

namespace YssElecReco.Fun
{
    /// <summary>
    /// 电子对账管理工具类
    /// </summary>
    public class ElecBBInfoUtil : YebHdTableListLoader
    {
        /// <summary>
        /// 修改列头
        /// </summary>
        /// <param name="tbMain">tbMain</param>
        protected override void updateColumnHeader(Yss.KTable.Models.Table tbMain)
        {
            ////init_Table_Column(tbMain, false, false);
        }

        /// <summary>
        /// 初始化主区域的KTable控件(包含对账信息)
        /// </summary>
        /// <param name="table">对应的表格</param>
        /// <param name="bShowCheckColumn">是否显示复选框标志</param>
        /// <param name="bShowRowIndexColumn">是否显示序号标志</param>
        /// <param name="elecType">核对表类型</param>
        /// <returns>控制列头数</returns>
        public static int init_Tgh_Table_Column(Table table, bool bShowCheckColumn, bool bShowRowIndexColumn, ElecEnums.ElecShowType elecType)
        {
            table.Clear();
            table.Tag = new Dictionary<string, Yss.KTable.Models.Row>();

            int extraCount = 0;
            ArrayList c_Cols = new ArrayList();

            if (bShowCheckColumn)
            {
                c_Cols.Add(",ShowRowCheckBoxColumn");
                extraCount++;
            }

            if (bShowRowIndexColumn)
            {
                c_Cols.Add(",ShowRowIndexColumn");
                extraCount++;
            }

            if (elecType == ElecEnums.ElecShowType.CHECK_ACT || elecType == ElecEnums.ElecShowType.CHECK_DBLGZ || elecType == ElecEnums.ElecShowType.CHECK_STO)
            {
                c_Cols.Add("科目代码,C_D_KM_CODE");
                c_Cols.Add("科目名称,C_D_KM_NAME");
                //// 数量
                c_Cols.Add("数量,N_D_SL");
                //// 金额
                c_Cols.Add("金额,N_D_JE");
                //// 借方发生额/成本/期末值/本年数/本期所有者权益合计
                //// 贷方发生额/估值增值/上期实收基金
                if (elecType == ElecEnums.ElecShowType.CHECK_STO)
                {
                    c_Cols.Add("借方发生额,N_D_JEO");
                    c_Cols.Add("贷方发生额,N_D_JET");
                }
                else if (elecType == ElecEnums.ElecShowType.CHECK_ACT || elecType == ElecEnums.ElecShowType.CHECK_DBLGZ)
                {
                    c_Cols.Add("成本,N_D_JEO");
                    c_Cols.Add("估值增值,N_D_JET");
                }
            }
            else
            {
                c_Cols.Add("指标代码,C_D_KM_CODE");
                c_Cols.Add("指标名称,C_D_KM_NAME");

                ////BUG257400【富国基金】【运维】电子对账资产负债表列名不对应问题
                if (elecType == ElecEnums.ElecShowType.CHECK_ZCFZ)
                {
                    c_Cols.Add("期初值,N_D_JE");
                }
                else if (elecType == ElecEnums.ElecShowType.CHECK_LR)
                {
                    c_Cols.Add("本期数,N_D_JE");
                }
                else if (elecType == ElecEnums.ElecShowType.CHECK_SYZQY)
                {
                    c_Cols.Add("本期实收基金,N_D_JE");
                }
                else if (elecType == ElecEnums.ElecShowType.CHECK_JZCBD)
                {
                    c_Cols.Add("本期数,N_D_JE");
                }
                else
                {
                    c_Cols.Add("金额,N_D_JE");
                }

                if (elecType == ElecEnums.ElecShowType.CHECK_ZCFZ)
                {
                    c_Cols.Add("期末值,N_D_JEO");
                }
                else if (elecType == ElecEnums.ElecShowType.CHECK_JZCBD)
                {
                    c_Cols.Add("本年数,N_D_JEO");
                }
                else if (elecType == ElecEnums.ElecShowType.CHECK_LR)
                {
                    c_Cols.Add("本年数,N_D_JEO");

                }
                else if (elecType == ElecEnums.ElecShowType.CHECK_SYZQY)
                {
                    //// 借方发生额/成本/期末值/本年数/本期所有者权益合计
                    c_Cols.Add("本期所有者权益合计,N_D_JEO");
                }

                if (elecType == ElecEnums.ElecShowType.CHECK_SYZQY)
                {
                    //// 贷方发生额/估值增值/上期实收基金
                    c_Cols.Add("上期实收基金,N_D_JET");

                    //// 上期未分配利润
                    c_Cols.Add("上期未分配利润,N_D_JETH");

                    //// 上期所有者权益
                    c_Cols.Add("上期所有者权益,N_D_JEF");

                    //// 本期未分配利润
                    c_Cols.Add("本期未分配利润,N_D_JEFI");
                }
            }

            Column col = null;

            Row r1 = new Row();
            Cell c1 = null;

            for (int i = 0; i < c_Cols.Count; i++)
            {
                string[] arrCol = Regex.Split(Convert.ToString(c_Cols[i]), ",");
                c1 = new Cell(arrCol[0]);

                if ("ShowRowCheckBoxColumn".Equals(arrCol[1]))
                {
                    col = new CheckBoxColumn();
                    col.DataPropertyName = arrCol[1];
                    table.FixedLeftCols += 1;
                }
                else if ("ShowRowIndexColumn".Equals(arrCol[1]))
                {
                    col = new MarkColumn();
                    col.DataPropertyName = arrCol[1];
                    table.FixedLeftCols += 1;
                }
                else
                {
                    col = new Column(arrCol[0]);
                    col.DataPropertyName = arrCol[1];
                    col.Visible = true;

                    col.Width = 120;

                    col.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Center;
                }

                r1.Cells.Add(c1);
                //r2.Cells.Add(c2);
                table.Columns.Add(col);
            }

            table.Rows.Add(r1);

            for (int i = 0; i < table.Rows[0].Cells.Count; i++)
            {
                table.Rows[0].Cells[i].TextAlign = ContentAlignment.MiddleCenter;
            }

            //// 列头行不能整行选中
            table.Rows[0].FullRowSelected = false;

            //// 前2行为列头
            table.Rows[0].IsHeader = true;

            table.ShowColumnHeader = false;

            table.Rows[0].Height = 18;

            table.FixedTopRows = 1;
            table.FixedLeftCols = extraCount + 2;
            table.AutoColumnWidth = true;
            ////table.DragColumn = true;
            table.Refresh();

            return extraCount;
        }

        /// <summary>
        /// 初始化主区域的KTable控件(包含对账信息)
        /// </summary>
        /// <param name="table">对应的表格</param>
        /// <param name="bShowCheckColumn">是否显示复选框标志</param>
        /// <param name="bShowRowIndexColumn">是否显示序号标志</param>
        /// <param name="elecType">核对表类型</param>
        /// <returns>控制列头数</returns>
        public static int init_Table_Column(Table table, bool bShowCheckColumn, bool bShowRowIndexColumn, ElecEnums.ElecShowType elecType)
        {
            table.Clear();
            table.Tag = new Dictionary<string, Yss.KTable.Models.Row>();

            int extraCount = 0;
            ArrayList c_Cols = new ArrayList();

            if (bShowCheckColumn)
            {
                c_Cols.Add(",ShowRowCheckBoxColumn");
                extraCount++;
            }

            if (bShowRowIndexColumn)
            {
                c_Cols.Add(",ShowRowIndexColumn");
                extraCount++;
            }

            //// 科目代码（指标代码）
            c_Cols.Add("本方,C_B_KM_CODE");
            c_Cols.Add("对方,C_D_KM_CODE");
            //// 科目名称（指标名称）
            c_Cols.Add("本方,C_B_KM_NAME");
            c_Cols.Add("对方,C_D_KM_NAME");
            if (elecType == ElecEnums.ElecShowType.CHECK_ACT || elecType == ElecEnums.ElecShowType.CHECK_DBLGZ || elecType == ElecEnums.ElecShowType.CHECK_STO)
            {
                //// 数量
                c_Cols.Add("本方,N_B_SL");
                c_Cols.Add("对方,N_D_SL");
                c_Cols.Add("差额,N_D_SL_CE");
            }

            //// 金额
            c_Cols.Add("本方,N_B_JE");
            c_Cols.Add("对方,N_D_JE");
            c_Cols.Add("差额,N_D_JE_CE");

            //// 借方发生额/成本/期末值/本年数/本期所有者权益合计
            c_Cols.Add("本方,N_B_JEO");
            c_Cols.Add("对方,N_D_JEO");
            c_Cols.Add("差额,N_D_JEO_CE");

            if (elecType == ElecEnums.ElecShowType.CHECK_ACT || elecType == ElecEnums.ElecShowType.CHECK_DBLGZ || elecType == ElecEnums.ElecShowType.CHECK_STO || elecType == ElecEnums.ElecShowType.CHECK_SYZQY)
            {
                //// 贷方发生额/估值增值/上期实收基金
                c_Cols.Add("本方,N_B_JET");
                c_Cols.Add("对方,N_D_JET");
                c_Cols.Add("差额,N_D_JET_CE");
            }

            if (elecType == ElecEnums.ElecShowType.CHECK_SYZQY)
            {
                //// 上期未分配利润
                c_Cols.Add("本方,N_B_JETH");
                c_Cols.Add("对方,N_D_JETH");
                c_Cols.Add("差额,N_D_JETH_CE");

                //// 上期所有者权益
                c_Cols.Add("本方,N_B_JEF");
                c_Cols.Add("对方,N_D_JEF");
                c_Cols.Add("差额,N_D_JEF_CE");

                //// 本期未分配利润
                c_Cols.Add("本方,N_B_JEFI");
                c_Cols.Add("对方,N_D_JEFI");
                c_Cols.Add("差额,N_D_JEFI_CE");
            }
            ////wlx 20160818 BUG137452显示出对账差异 在前台显示对账结果字段
            c_Cols.Add("核对结果,C_RESULT");

            Column col = null;

            Row r1 = new Row();
            Row r2 = new Row();
            Cell c1 = null;
            Cell c2 = null;

            for (int i = 0; i < c_Cols.Count; i++)
            {
                string[] arrCol = Regex.Split(Convert.ToString(c_Cols[i]), ",");
                c1 = new Cell(arrCol[0]);
                c2 = new Cell(arrCol[0]);

                if ("ShowRowCheckBoxColumn".Equals(arrCol[1]))
                {
                    col = new CheckBoxColumn();
                    col.DataPropertyName = arrCol[1];
                    table.FixedLeftCols += 1;
                }
                else if ("ShowRowIndexColumn".Equals(arrCol[1]))
                {
                    col = new MarkColumn();
                    col.DataPropertyName = arrCol[1];
                    table.FixedLeftCols += 1;
                }
                else
                {
                    col = new Column(arrCol[0], arrCol[1]);
                    col.DataPropertyName = arrCol[1];
                    col.Visible = true;

                    col.Width = 120;

                    col.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Center;

                    if (elecType == ElecEnums.ElecShowType.CHECK_ACT || elecType == ElecEnums.ElecShowType.CHECK_DBLGZ || elecType == ElecEnums.ElecShowType.CHECK_STO)
                    {
                        //// 第一行7个需要合并的单元格
                        if (i == extraCount || i == extraCount + 1)
                        {
                            c1 = new Cell("科目代码");
                        }
                        else if (i == extraCount + 2 || i == extraCount + 3)
                        {
                            c1 = new Cell("科目名称");
                        }
                        else if (i == extraCount + 4 || i == extraCount + 5 || i == extraCount + 6)
                        {
                            c1 = new Cell("数量");
                        }
                        else if (i == extraCount + 8 || i == extraCount + 7 || i == extraCount + 9)
                        {
                            c1 = new Cell("金额");
                        }
                        else if (i == extraCount + 10 || i == extraCount + 11 || i == extraCount + 12)
                        {
                            if (elecType == ElecEnums.ElecShowType.CHECK_STO)
                            {
                                c1 = new Cell("借方发生额");
                            }
                            else if (elecType == ElecEnums.ElecShowType.CHECK_ACT || elecType == ElecEnums.ElecShowType.CHECK_DBLGZ)
                            {
                                c1 = new Cell("成本");
                            }
                        }
                        else if (i == extraCount + 13 || i == extraCount + 14 || i == extraCount + 15)
                        {
                            if (elecType == ElecEnums.ElecShowType.CHECK_STO)
                            {
                                c1 = new Cell("贷方发生额");
                            }
                            else if (elecType == ElecEnums.ElecShowType.CHECK_ACT || elecType == ElecEnums.ElecShowType.CHECK_DBLGZ)
                            {
                                c1 = new Cell("估值增值");
                            }
                        }
                    }
                    else
                    {
                        if (i == extraCount || i == extraCount + 1)
                        {
                            c1 = new Cell("指标代码");
                        }
                        else if (i == extraCount + 2 || i == extraCount + 3)
                        {
                            c1 = new Cell("指标名称");
                        }
                        else if (i == extraCount + 4 || i == extraCount + 5 || i == extraCount + 6)
                        {
                            ////BUG257400【富国基金】【运维】电子对账资产负债表列名不对应问题
                            if (elecType == ElecEnums.ElecShowType.CHECK_ZCFZ)
                            {
                                c1 = new Cell("期初值");
                            }
                            else if (elecType == ElecEnums.ElecShowType.CHECK_LR)
                            {
                                c1 = new Cell("本期数");
                            }
                            else if (elecType == ElecEnums.ElecShowType.CHECK_SYZQY)
                            {
                                c1 = new Cell("本期实收基金");
                            }
                            else if (elecType == ElecEnums.ElecShowType.CHECK_JZCBD)
                            {
                                c1 = new Cell("本期数");
                            }
                            else
                            {
                                c1 = new Cell("金额");
                            }
                        }
                        else if (i == extraCount + 7 || i == extraCount + 8 || i == extraCount + 9)
                        {
                            if (elecType == ElecEnums.ElecShowType.CHECK_ZCFZ)
                            {
                                c1 = new Cell("期末值");
                            }
                            else if (elecType == ElecEnums.ElecShowType.CHECK_JZCBD)
                            {
                                c1 = new Cell("本年数");
                            }
                            else if (elecType == ElecEnums.ElecShowType.CHECK_LR)
                            {
                                c1 = new Cell("本年数");
                            }
                            else if (elecType == ElecEnums.ElecShowType.CHECK_SYZQY)
                            {
                                c1 = new Cell("本期所有者权益合计");
                            }
                        }

                        if (elecType == ElecEnums.ElecShowType.CHECK_SYZQY)
                        {
                            if (i == extraCount + 10 || i == extraCount + 11 || i == extraCount + 12)
                            {
                                c1 = new Cell("上期实收基金");
                            }
                            else if (i == extraCount + 13 || i == extraCount + 14 || i == extraCount + 15)
                            {
                                c1 = new Cell("上期未分配利润");
                            }
                            else if (i == extraCount + 16 || i == extraCount + 17 || i == extraCount + 18)
                            {
                                c1 = new Cell("上期所有者权益");
                            }
                            else if (i == extraCount + 19 || i == extraCount + 20 || i == extraCount + 21)
                            {
                                c1 = new Cell("本期未分配利润");
                            }
                        }
                    }
                }

                r1.Cells.Add(c1);
                r2.Cells.Add(c2);
                table.Columns.Add(col);
            }

            table.Rows.Add(r1);
            table.Rows.Add(r2);

            ////合并行、列
            ////标记列
            if (bShowCheckColumn && extraCount == 1)
            {
                table.Rows[0].Cells[0].RowSpan = 2;
                table.Rows[0].Cells[0].TextAlign = ContentAlignment.MiddleCenter;
            }
            else if (bShowRowIndexColumn && extraCount == 1)
            {
                table.Rows[0].Cells[0].RowSpan = 2;
                table.Rows[0].Cells[0].TextAlign = ContentAlignment.MiddleCenter;
            }
            else if (extraCount == 2)
            {
                table.Rows[0].Cells[0].RowSpan = 2;
                table.Rows[0].Cells[0].TextAlign = ContentAlignment.MiddleCenter;
                table.Rows[0].Cells[1].RowSpan = 2;
                table.Rows[0].Cells[1].TextAlign = ContentAlignment.MiddleCenter;
            }
            ////wlx 20160818 BUG137452显示出对账差异 在前台显示对账结果字段
            table.Rows[0].Cells[table.Columns.Count - 1].RowSpan = 2;
            table.Rows[0].Cells[table.Columns.Count - 1].TextAlign = ContentAlignment.MiddleCenter;

            //// 列合并 start

            if (elecType == ElecEnums.ElecShowType.CHECK_ACT || elecType == ElecEnums.ElecShowType.CHECK_DBLGZ || elecType == ElecEnums.ElecShowType.CHECK_STO)
            {
                //// 科目代码
                table.Rows[0].Cells[extraCount].ColSpan = 2;
                table.Rows[0].Cells[extraCount].TextAlign = ContentAlignment.MiddleCenter;
                //// 科目名称
                table.Rows[0].Cells[extraCount + 2].ColSpan = 2;
                table.Rows[0].Cells[extraCount + 2].TextAlign = ContentAlignment.MiddleCenter;
                //// 数量
                table.Rows[0].Cells[extraCount + 4].ColSpan = 3;
                table.Rows[0].Cells[extraCount + 4].TextAlign = ContentAlignment.MiddleCenter;
                //// 金额
                table.Rows[0].Cells[extraCount + 7].ColSpan = 3;
                table.Rows[0].Cells[extraCount + 7].TextAlign = ContentAlignment.MiddleCenter;
                //// 借方发生额(成本)
                table.Rows[0].Cells[extraCount + 10].ColSpan = 3;
                table.Rows[0].Cells[extraCount + 10].TextAlign = ContentAlignment.MiddleCenter;
                //// 贷方发生额(估值增值)
                table.Rows[0].Cells[extraCount + 13].ColSpan = 3;
                table.Rows[0].Cells[extraCount + 13].TextAlign = ContentAlignment.MiddleCenter;
            }
            else
            {
                //// 指标代码
                table.Rows[0].Cells[extraCount].ColSpan = 2;
                table.Rows[0].Cells[extraCount].TextAlign = ContentAlignment.MiddleCenter;
                //// 指标名称
                table.Rows[0].Cells[extraCount + 2].ColSpan = 2;
                table.Rows[0].Cells[extraCount + 2].TextAlign = ContentAlignment.MiddleCenter;
                //// 金额
                table.Rows[0].Cells[extraCount + 4].ColSpan = 3;
                table.Rows[0].Cells[extraCount + 4].TextAlign = ContentAlignment.MiddleCenter;
                //// 期末值/本年数/本期所有者权益合计
                table.Rows[0].Cells[extraCount + 7].ColSpan = 3;
                table.Rows[0].Cells[extraCount + 7].TextAlign = ContentAlignment.MiddleCenter;
                if (elecType == ElecEnums.ElecShowType.CHECK_SYZQY)
                {
                    //// 上期实收基金
                    table.Rows[0].Cells[extraCount + 10].ColSpan = 3;
                    table.Rows[0].Cells[extraCount + 10].TextAlign = ContentAlignment.MiddleCenter;
                    //// 上期未分配利润
                    table.Rows[0].Cells[extraCount + 13].ColSpan = 3;
                    table.Rows[0].Cells[extraCount + 13].TextAlign = ContentAlignment.MiddleCenter;
                    //// 上期所有者权益
                    table.Rows[0].Cells[extraCount + 16].ColSpan = 3;
                    table.Rows[0].Cells[extraCount + 16].TextAlign = ContentAlignment.MiddleCenter;
                    //// 本期未分配利润
                    table.Rows[0].Cells[extraCount + 19].ColSpan = 3;
                    table.Rows[0].Cells[extraCount + 19].TextAlign = ContentAlignment.MiddleCenter;
                }
            }

            for (int i = 0; i < table.Rows[1].Cells.Count; i++)
            {
                table.Rows[1].Cells[i].TextAlign = ContentAlignment.MiddleCenter;
            }

            //// 列头行不能整行选中
            table.Rows[0].FullRowSelected = false;
            table.Rows[1].FullRowSelected = false;

            //// 前四行为列头
            table.Rows[0].IsHeader = true;
            table.Rows[1].IsHeader = true;

            table.ShowColumnHeader = false;

            table.Rows[0].Height = 18;
            table.Rows[1].Height = 18;

            table.FixedTopRows = 2;
            table.FixedLeftCols = extraCount + 4;
            table.AutoColumnWidth = true;
            ////table.DragColumn = true;
            table.Refresh();

            return extraCount;
        }


        /// <summary>
        /// 加载数据    STORY #41248 【南方基金】电子对账反馈结果中的差异数据下拉框扩展 修改为树形
        /// </summary>
        /// <param name="table">table</param>
        /// <param name="pojoList">pojoList</param>
        /// <param name="resultType">resultType</param>
        /// <param name="copySameData">copySameData</param>
        public void drawTghData(Table table, List<BasePojo> pojoList, string resultType, bool copySameData)
        {
            object cellValueObj = null;
            string cellValue = "";
            ////BUG206982【鹏华基金】电子对账月报反馈8条不一致，但点开详细只有一条不一致
            ////SortedDictionary<string, Row> htView = new SortedDictionary<string, Row>();
            SortedDictionary<string, List<Row>> htView = new SortedDictionary<string, List<Row>>();
            if (null != pojoList && pojoList.Count > 0)
            {
                ////设置父节点
                ResetNodeAndParentNodePropertyName(pojoList, ClsEnums.KTableDataShowMode.TreeMode);
                foreach (ErResult xerResult in pojoList)
                {
                    Row row = new Row();
                    row.Tag = xerResult;

                    //// 对应数量或金额的单元格
                    Cell cell_D_SL = null;
                    Cell cell_D_JE = null;
                    Cell cell_D_JEO = null;
                    Cell cell_D_JET = null;
                    Cell cell_D_JETH = null;
                    Cell cell_D_JEF = null;
                    Cell cell_D_JEFI = null;

                    foreach (Column column in table.Columns)
                    {
                        Cell cell = null;

                        if (column.DataPropertyName.Equals("ShowRowCheckBoxColumn")
                            || column.DataPropertyName.Equals("ShowRowIndexColumn"))
                        {
                            cellValueObj = null;
                        }
                        else
                        {
                            ////cellValueObj = ReflectBase.getAttrValue(column.DataPropertyName, erResult);
                            ////STORY #51518 南方基金-“电子对账详细信息”只显示存在差异的数据  填充表格前数据处理
                            if (resultType.Equals("ALL_DATA") && copySameData == false &&
                            ("0".Equals(xerResult.C_RESULT) || (null != xerResult.C_RESULT && xerResult.C_RESULT.Contains("一致") && !xerResult.C_RESULT.Contains("不一致")))
                              && checkColumn(column))
                            {
                                cellValueObj = null;
                            }
                            else
                            {
                                cellValueObj = ReflectBase.getAttrValue(column.DataPropertyName, xerResult);
                            }
                        }

                        if (cellValueObj == null)
                        {
                            cellValue = "";
                            cell = new Cell(cellValue);
                        }
                        else
                        {
                            cell = new Cell();

                            if (cellValueObj is DateTime)
                            {
                                cellValue = ((DateTime)cellValueObj).ToString("yyyy-MM-dd");
                            }
                            else if (cellValueObj is decimal)
                            {
                                cellValue = ((decimal)cellValueObj).ToString("#,##0.00########");
                                cell.TextAlign = ContentAlignment.MiddleRight;
                            }
                            else
                            {
                                cellValue = cellValueObj.ToString();
                            }

                            if ("null".Equals(cellValue))
                            {
                                cellValue = "";
                            }

                            cell.Text = cellValue;

                            //// 给各单元格赋值

                            if ("N_D_SL".Equals(column.DataPropertyName))
                            {
                                cell_D_SL = cell;
                            }
                            else if ("N_D_JE".Equals(column.DataPropertyName))
                            {
                                cell_D_JE = cell;
                            }
                            else if ("N_D_JEO".Equals(column.DataPropertyName))
                            {
                                cell_D_JEO = cell;
                            }
                            else if ("N_D_JET".Equals(column.DataPropertyName))
                            {
                                cell_D_JET = cell;
                            }
                            else if ("N_D_JETH".Equals(column.DataPropertyName))
                            {
                                cell_D_JETH = cell;
                            }
                            else if ("N_D_JEF".Equals(column.DataPropertyName))
                            {
                                cell_D_JEF = cell;
                            }
                            else if ("N_D_JEFI".Equals(column.DataPropertyName))
                            {
                                cell_D_JEFI = cell;
                            }
                        }

                        row.Cells.Add(cell);
                    }

                    ////核对结果一致
                    bool result = ("0".Equals(xerResult.C_RESULT) || (null != xerResult.C_RESULT && xerResult.C_RESULT.Contains("一致") && !xerResult.C_RESULT.Contains("不一致")));
                    //// 更改单元格的颜色
                    ////modifyCellColor(cell_B_SL, cell_D_SL, cell_D_SL_CE, cell_B_JE, cell_D_JE, cell_D_JE_CE, cell_B_JEO, cell_D_JEO, cell_D_JEO_CE, cell_B_JET, cell_D_JET, cell_D_JET_CE, cell_B_JETH, cell_D_JETH, cell_D_JETH_CE, cell_B_JEF, cell_D_JEF, cell_D_JEF_CE, cell_B_JEFI, cell_D_JEFI, cell_D_JEFI_CE, result);
                    setHtViewExtendsPojo(htView, xerResult, row);
                    ////table.Rows.Add(row);
                }
                ////table.AutoWidth();
                loadTreeViewExtendsPojo(table, htView);
                table.Refresh();
            }
        }

        /// <summary>
        /// 加载数据    STORY #41248 【南方基金】电子对账反馈结果中的差异数据下拉框扩展 修改为树形
        /// </summary>
        /// <param name="table">table</param>
        /// <param name="pojoList">pojoList</param>
        /// <param name="resultType">resultType</param>
        /// <param name="copySameData">copySameData</param>
        public void drawResultData(Table table, List<BasePojo> pojoList, string resultType, bool copySameData)
        {
            object cellValueObj = null;
            string cellValue = "";
            ////BUG206982【鹏华基金】电子对账月报反馈8条不一致，但点开详细只有一条不一致
            ////SortedDictionary<string, Row> htView = new SortedDictionary<string, Row>();
            SortedDictionary<string, List<Row>> htView = new SortedDictionary<string, List<Row>>();
            if (null != pojoList && pojoList.Count > 0)
            {
                ////设置父节点
                ResetNodeAndParentNodePropertyName(pojoList, ClsEnums.KTableDataShowMode.TreeMode);
                foreach (ErResult xerResult in pojoList)
                {
                    Row row = new Row();
                    row.Tag = xerResult;

                    //// 对应数量或金额的单元格
                    Cell cell_B_SL = null;
                    Cell cell_D_SL = null;
                    Cell cell_D_SL_CE = null;
                    Cell cell_B_JE = null;
                    Cell cell_D_JE = null;
                    Cell cell_D_JE_CE = null;
                    Cell cell_B_JEO = null;
                    Cell cell_D_JEO = null;
                    Cell cell_D_JEO_CE = null;
                    Cell cell_B_JET = null;
                    Cell cell_D_JET = null;
                    Cell cell_D_JET_CE = null;
                    Cell cell_B_JETH = null;
                    Cell cell_D_JETH = null;
                    Cell cell_D_JETH_CE = null;
                    Cell cell_B_JEF = null;
                    Cell cell_D_JEF = null;
                    Cell cell_D_JEF_CE = null;
                    Cell cell_B_JEFI = null;
                    Cell cell_D_JEFI = null;
                    Cell cell_D_JEFI_CE = null;

                    foreach (Column column in table.Columns)
                    {
                        Cell cell = null;

                        if (column.DataPropertyName.Equals("ShowRowCheckBoxColumn")
                            || column.DataPropertyName.Equals("ShowRowIndexColumn"))
                        {
                            cellValueObj = null;
                        }
                        else
                        {
                            if (column.DataPropertyName.EndsWith("_CE"))
                            {
                                /*string columnName = column.DataPropertyName.Replace("_CE", "");
                                string columnName0 = columnName.Replace("_D_", "_B_");
                                cellValueObj = Convert.ToDecimal(ReflectBase.getAttrValue(columnName0, erResult)) - Convert.ToDecimal(ReflectBase.getAttrValue(columnName, erResult));*/
                                if (resultType.Equals("ALL_DATA") && copySameData == false &&
                                    ("0".Equals(xerResult.C_RESULT) || (null != xerResult.C_RESULT && xerResult.C_RESULT.Contains("一致") && !xerResult.C_RESULT.Contains("不一致"))))
                                {
                                    cellValueObj = null;
                                }
                                else
                                {
                                    string columnName = column.DataPropertyName.Replace("_CE", "");
                                    string columnName0 = columnName.Replace("_D_", "_B_");
                                    cellValueObj = Convert.ToDecimal(ReflectBase.getAttrValue(columnName0, xerResult)) - Convert.ToDecimal(ReflectBase.getAttrValue(columnName, xerResult));
                                }
                            }
                            else
                            {
                                ////cellValueObj = ReflectBase.getAttrValue(column.DataPropertyName, erResult);
                                ////STORY #51518 南方基金-“电子对账详细信息”只显示存在差异的数据  填充表格前数据处理
                                if (resultType.Equals("ALL_DATA") && copySameData == false &&
                                ("0".Equals(xerResult.C_RESULT) || (null != xerResult.C_RESULT && xerResult.C_RESULT.Contains("一致") && !xerResult.C_RESULT.Contains("不一致")))
                                  && checkColumn(column))
                                {
                                    cellValueObj = null;
                                }
                                else
                                {
                                    cellValueObj = ReflectBase.getAttrValue(column.DataPropertyName, xerResult);
                                }
                            }
                        }

                        if (cellValueObj == null)
                        {
                            cellValue = "";
                            cell = new Cell(cellValue);
                        }
                        else
                        {
                            cell = new Cell();

                            if (cellValueObj is DateTime)
                            {
                                cellValue = ((DateTime)cellValueObj).ToString("yyyy-MM-dd");
                            }
                            else if (cellValueObj is decimal)
                            {
                                cellValue = ((decimal)cellValueObj).ToString("#,##0.00########");
                                cell.TextAlign = ContentAlignment.MiddleRight;
                            }
                            else
                            {
                                cellValue = cellValueObj.ToString();
                            }

                            if ("null".Equals(cellValue))
                            {
                                cellValue = "";
                            }

                            cell.Text = cellValue;

                            //// 给各单元格赋值
                            if ("N_B_SL".Equals(column.DataPropertyName))
                            {
                                cell_B_SL = cell;
                            }
                            else if ("N_D_SL".Equals(column.DataPropertyName))
                            {
                                cell_D_SL = cell;
                            }
                            else if ("N_D_SL_CE".Equals(column.DataPropertyName))
                            {
                                cell_D_SL_CE = cell;
                            }
                            else if ("N_B_JE".Equals(column.DataPropertyName))
                            {
                                cell_B_JE = cell;
                            }
                            else if ("N_D_JE".Equals(column.DataPropertyName))
                            {
                                cell_D_JE = cell;
                            }
                            else if ("N_D_JE_CE".Equals(column.DataPropertyName))
                            {
                                cell_D_JE_CE = cell;
                            }
                            else if ("N_B_JEO".Equals(column.DataPropertyName))
                            {
                                cell_B_JEO = cell;
                            }
                            else if ("N_D_JEO".Equals(column.DataPropertyName))
                            {
                                cell_D_JEO = cell;
                            }
                            else if ("N_D_JEO_CE".Equals(column.DataPropertyName))
                            {
                                cell_D_JEO_CE = cell;
                            }
                            else if ("N_B_JET".Equals(column.DataPropertyName))
                            {
                                cell_B_JET = cell;
                            }
                            else if ("N_D_JET".Equals(column.DataPropertyName))
                            {
                                cell_D_JET = cell;
                            }
                            else if ("N_D_JET_CE".Equals(column.DataPropertyName))
                            {
                                cell_D_JET_CE = cell;
                            }
                            else if ("N_B_JETH".Equals(column.DataPropertyName))
                            {
                                cell_B_JETH = cell;
                            }
                            else if ("N_D_JETH".Equals(column.DataPropertyName))
                            {
                                cell_D_JETH = cell;
                            }
                            else if ("N_D_JETH_CE".Equals(column.DataPropertyName))
                            {
                                cell_D_JETH_CE = cell;
                            }
                            else if ("N_B_JEF".Equals(column.DataPropertyName))
                            {
                                cell_B_JEF = cell;
                            }
                            else if ("N_D_JEF".Equals(column.DataPropertyName))
                            {
                                cell_D_JEF = cell;
                            }
                            else if ("N_D_JEF_CE".Equals(column.DataPropertyName))
                            {
                                cell_D_JEF_CE = cell;
                            }
                            else if ("N_B_JEFI".Equals(column.DataPropertyName))
                            {
                                cell_B_JEFI = cell;
                            }
                            else if ("N_D_JEFI".Equals(column.DataPropertyName))
                            {
                                cell_D_JEFI = cell;
                            }
                            else if ("N_D_JEFI_CE".Equals(column.DataPropertyName))
                            {
                                cell_D_JEFI_CE = cell;
                            }
                        }

                        row.Cells.Add(cell);
                    }

                    ////核对结果一致
                    bool result = ("0".Equals(xerResult.C_RESULT) || (null != xerResult.C_RESULT && xerResult.C_RESULT.Contains("一致") && !xerResult.C_RESULT.Contains("不一致")));
                    //// 更改单元格的颜色
                    modifyCellColor(cell_B_SL, cell_D_SL, cell_D_SL_CE, cell_B_JE, cell_D_JE, cell_D_JE_CE, cell_B_JEO, cell_D_JEO, cell_D_JEO_CE, cell_B_JET, cell_D_JET, cell_D_JET_CE, cell_B_JETH, cell_D_JETH, cell_D_JETH_CE, cell_B_JEF, cell_D_JEF, cell_D_JEF_CE, cell_B_JEFI, cell_D_JEFI, cell_D_JEFI_CE, result);
                    setHtViewExtendsPojo(htView, xerResult, row);
                    ////table.Rows.Add(row);
                }
                ////table.AutoWidth();
                loadTreeViewExtendsPojo(table, htView);
                table.Refresh();
            }
        }

        /// <summary>
        /// 树形展示
        /// </summary>
        /// <param name="htView">htView</param>
        /// <param name="basePojo">basePojo</param>
        /// <param name="row">row</param>
        protected void setHtViewExtendsPojo(SortedDictionary<string, List<Row>> htView, BasePojo basePojo, Row row)
        {
            string nodeCode = ReflectBase.getAttrValue(this._nodeName, basePojo, false) as string;
            ////BUG206982【鹏华基金】电子对账月报反馈8条不一致，但点开详细只有一条不一致
            ////不进行去重处理，确保将所有数据都加载出来
            ////托管行返回数据有可能存在多条本方科目相同的情况
            ////如果存在多个父级节点，默认挂载在第一个父节点下面
            //// 根据节点代码判断是否有重复数据
            if (!htView.ContainsKey(nodeCode))
            {
                List<Row> rows = new List<Row>();
                rows.Add(row);
                htView.Add(nodeCode, rows);
            }
            else
            {
                htView[nodeCode].Add(row);
            }

        }

        /// <summary>
        /// 是否是不合法的父亲节点
        /// </summary>
        /// <param name="nodeName">nodeName</param>
        /// <returns>是/否</returns>
        private static bool isFalseParentNode(string nodeName)
        {
            if (nodeName == null)
            {
                return true;
            }

            if ("".Equals(nodeName.Trim()))
            {
                return true;
            }

            string pattern = "^[0-9]";
            Regex rgx = new Regex(pattern);
            if (rgx.IsMatch(nodeName.Trim()))
            {
                return false;
            }

            return true;
        }

        /// <summary>
        /// 加载树形
        /// </summary>
        /// <param name="tbMain">tbMain</param>
        /// <param name="htView">htView</param>
        protected void loadTreeViewExtendsPojo(Table tbMain, SortedDictionary<string, List<Row>> htView)
        {
            string nodeCode = "";
            string parentCode = "";


            foreach (KeyValuePair<string, List<Row>> ider in htView)
            {
                //// 在集合中判断父节点，如果查到则将本节点加到row的subrow中，查不到则把row添加到table中
                ////Row tempRows = ider.Value;
                List<Row> tempRows = ider.Value;
                
                ////开始遍历每个节点对应的行数
                foreach (Row tempRow in tempRows)
                {
                    BasePojo pojo = (BasePojo)tempRow.Tag;

                    nodeCode = ider.Key;

                    ////edit by gongjinzhi 当父节点为空时会报错
                    ////parentCode = ReflectBase.getAttrValue(this._parentNodeName, pojo, false).ToString() as string;
                    object objParentCode = ReflectBase.getAttrValue(this._parentNodeName, pojo, false);
                    if (objParentCode != null)
                    {
                        parentCode = objParentCode.ToString();
                    }
                    else
                    {
                        parentCode = null;
                    }

                    ////BUG206982【鹏华基金】电子对账月报反馈8条不一致，但点开详细只有一条不一致
                    ////非正常父节点全部挂载到根目录
                    ////只有以数字开头的节点才认为是节点，其他情况都挂载到根目录
                    if (isFalseParentNode(parentCode))
                    {
                        ////非正常父节点全部挂载到根目录
                        tbMain.Rows.Add(tempRow);

                    }
                    else
                    {
                        //// 如果存在表的父级节点，且父节点不是本身
                        ////BUG206982【鹏华基金】电子对账月报反馈8条不一致，但点开详细只有一条不一致
                        ////加载到第一个父节点下
                        if (parentCode != null && htView.ContainsKey(parentCode) && nodeCode != parentCode)
                        {
                            ////防止空指针报错
                            if (htView[parentCode] == null || htView[parentCode].Count == 0)
                            {
                                ////添加到根目录
                                tbMain.Rows.Add(tempRow);
                            }
                            else
                            {
                                ////添加到第一个父节点下
                                htView[parentCode][0].SubRows.Add(tempRow);
                            }
                        }
                        else
                        { ////没有父节点
                            ////ReflectBase.setAttrValue("N_Level", pojo, 1); 一级父节点默认级别为1 by leeyu 20111102
                            //// 对有非父级节点的处理
                            tbMain.Rows.Add(tempRow);
                        }

                    }

                } ////结束遍历每个节点对应的行数
                
            }

            this.UpdateRowImage(tbMain);
        }

        /// <summary>
        ///  检查单元格列是否为对列
        /// </summary>
        /// <param name="column">column</param>
        /// <returns>是/否</returns>
        public static bool checkColumn(Column column)
        {
            //// 给各单元格赋值
            if ("N_D_SL".Equals(column.DataPropertyName) || "N_D_JE".Equals(column.DataPropertyName)
                || "N_D_JEO".Equals(column.DataPropertyName) || "N_D_JET".Equals(column.DataPropertyName)
                 || "N_D_JETH".Equals(column.DataPropertyName) || "N_D_JEF".Equals(column.DataPropertyName)
                 || "N_D_JEFI".Equals(column.DataPropertyName) || "C_D_KM_CODE".Equals(column.DataPropertyName)
                || "C_D_KM_NAME".Equals(column.DataPropertyName))
            {
                return true;
            }

            return false;

        }

        /// <summary>
        /// 如果核对结果不一致则修改表格单元格颜色
        /// cell_D_SL_CE, cell_D_JE_CE, cell_B_JEO, cell_D_JEO, cell_D_JEO_CE, cell_B_JET, cell_D_JET, cell_D_JET_CE
        /// </summary>
        /// <param name="cell_B_SL">本方数量单元</param>
        /// <param name="cell_D_SL">对方数量单元</param>
        /// <param name="cell_D_SL_CE">数量差额</param>
        /// <param name="cell_B_JE">本方金额单元</param>
        /// <param name="cell_D_JE">对方金额单元</param>
        /// <param name="cell_D_JE_CE">差额</param>
        /// <param name="cell_B_JEO">本方借方发生额(成本)</param>
        /// <param name="cell_D_JEO">对方借方发生额(成本)</param>
        /// <param name="cell_D_JEO_CE">差额</param>
        /// <param name="cell_B_JET">本方借贷发生额(估值增值)</param>
        /// <param name="cell_D_JET">对方借贷发生额(估值增值)</param>
        /// <param name="cell_D_JET_CE">差额</param>
        /// <param name="cell_B_JETH">本方上期未分配利润</param>
        /// <param name="cell_D_JETH">对方上期未分配利润</param>
        /// <param name="cell_D_JETH_CE">差额</param>
        /// <param name="cell_B_JEF">本方上期所有者权益</param>
        /// <param name="cell_D_JEF">对方上期所有者权益</param>
        /// <param name="cell_D_JEF_CE">差额</param>
        /// <param name="cell_B_JEFI">本方本期未分配利润</param>
        /// <param name="cell_D_JEFI">对方本期未分配利润</param>
        /// <param name="cell_D_JEFI_CE">差额</param>
        /// <param name="result">是否一致</param>
        public static void modifyCellColor(Cell cell_B_SL, Cell cell_D_SL, Cell cell_D_SL_CE, Cell cell_B_JE, Cell cell_D_JE, Cell cell_D_JE_CE, Cell cell_B_JEO, Cell cell_D_JEO, Cell cell_D_JEO_CE, Cell cell_B_JET, Cell cell_D_JET, Cell cell_D_JET_CE, Cell cell_B_JETH, Cell cell_D_JETH, Cell cell_D_JETH_CE, Cell cell_B_JEF, Cell cell_D_JEF, Cell cell_D_JEF_CE, Cell cell_B_JEFI, Cell cell_D_JEFI, Cell cell_D_JEFI_CE, bool result)
        {
            try
            {
                ////不一致才进来
                if (!result)
                {
                    if (cell_B_SL != null && Convert.ToDecimal(cell_B_SL.Text.Trim().Length == 0 ? "0" : cell_B_SL.Text.Trim()) != Convert.ToDecimal(cell_D_SL.Text.Trim().Length == 0 ? "0" : cell_D_SL.Text.Trim()))
                    {
                        cell_B_SL.ForeColor = Color.Red;
                        cell_D_SL.ForeColor = Color.Red;
                        cell_D_SL_CE.ForeColor = Color.Red;
                    }

                    if (cell_B_JE != null && Convert.ToDecimal(cell_B_JE.Text.Trim().Length == 0 ? "0" : cell_B_JE.Text.Trim()) != Convert.ToDecimal(cell_D_JE.Text.Trim().Length == 0 ? "0" : cell_D_JE.Text.Trim()))
                    {
                        cell_B_JE.ForeColor = Color.Red;
                        cell_D_JE.ForeColor = Color.Red;
                        cell_D_JE_CE.ForeColor = Color.Red;
                    }

                    if (cell_B_JEO != null && Convert.ToDecimal(cell_B_JEO.Text.Trim().Length == 0 ? "0" : cell_B_JEO.Text.Trim()) != Convert.ToDecimal(cell_D_JEO.Text.Trim().Length == 0 ? "0" : cell_D_JEO.Text.Trim()))
                    {
                        cell_B_JEO.ForeColor = Color.Red;
                        cell_D_JEO.ForeColor = Color.Red;
                        cell_D_JEO_CE.ForeColor = Color.Red;
                    }

                    if (cell_B_JET != null && Convert.ToDecimal(cell_B_JET.Text.Trim().Length == 0 ? "0" : cell_B_JET.Text.Trim()) != Convert.ToDecimal(cell_D_JET.Text.Trim().Length == 0 ? "0" : cell_D_JET.Text.Trim()))
                    {
                        cell_B_JET.ForeColor = Color.Red;
                        cell_D_JET.ForeColor = Color.Red;
                        cell_D_JET_CE.ForeColor = Color.Red;
                    }

                    if (cell_B_JETH != null && Convert.ToDecimal(cell_B_JETH.Text.Trim().Length == 0 ? "0" : cell_B_JETH.Text.Trim()) != Convert.ToDecimal(cell_D_JETH.Text.Trim().Length == 0 ? "0" : cell_D_JETH.Text.Trim()))
                    {
                        cell_B_JETH.ForeColor = Color.Red;
                        cell_D_JETH.ForeColor = Color.Red;
                        cell_D_JETH_CE.ForeColor = Color.Red;
                    }

                    if (cell_B_JEF != null && Convert.ToDecimal(cell_B_JEF.Text.Trim().Length == 0 ? "0" : cell_B_JEF.Text.Trim()) != Convert.ToDecimal(cell_D_JEF.Text.Trim().Length == 0 ? "0" : cell_D_JEF.Text.Trim()))
                    {
                        cell_B_JEF.ForeColor = Color.Red;
                        cell_D_JEF.ForeColor = Color.Red;
                        cell_D_JEF_CE.ForeColor = Color.Red;
                    }

                    if (cell_B_JEFI != null && Convert.ToDecimal(cell_B_JEFI.Text.Trim().Length == 0 ? "0" : cell_B_JEFI.Text.Trim()) != Convert.ToDecimal(cell_D_JEFI.Text.Trim().Length == 0 ? "0" : cell_D_JEFI.Text.Trim()))
                    {
                        cell_B_JEFI.ForeColor = Color.Red;
                        cell_D_JEFI.ForeColor = Color.Red;
                        cell_D_JEFI_CE.ForeColor = Color.Red;
                    }
                }
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }
        }


        /// <summary>
        /// 加急STORY #96252 对账结果明细需支持导出
        /// </summary>
        /// <param name="table">对应的表格</param>
        /// <param name="bShowCheckColumn">是否显示复选框标志</param>
        /// <param name="bShowRowIndexColumn">是否显示序号标志</param>
        /// <param name="elecType">核对表类型</param>
        /// <returns>控制列头数</returns>
        public static int init_BatchMx_Table_Column(Table table, bool bShowCheckColumn, bool bShowRowIndexColumn, ElecEnums.ElecShowType elecType)
        {
            table.Clear();
            table.Tag = new Dictionary<string, Yss.KTable.Models.Row>();

            int extraCount = 0;
            ArrayList c_Cols = new ArrayList();

            if (bShowCheckColumn)
            {
                c_Cols.Add(",ShowRowCheckBoxColumn");
                extraCount++;
            }

            if (bShowRowIndexColumn)
            {
                c_Cols.Add(",ShowRowIndexColumn");
                extraCount++;
            }

            c_Cols.Add("组合代码,C_PORT_CODE");
            c_Cols.Add("组合简称,C_PORT_NAME_ST");
            c_Cols.Add("日期,D_TIME");

            //// 科目代码（指标代码）
            c_Cols.Add("本方,C_B_KM_CODE");
            c_Cols.Add("对方,C_D_KM_CODE");
            //// 科目名称（指标名称）
            c_Cols.Add("本方,C_B_KM_NAME");
            c_Cols.Add("对方,C_D_KM_NAME");
            if (elecType == ElecEnums.ElecShowType.CHECK_ACT || elecType == ElecEnums.ElecShowType.CHECK_DBLGZ || elecType == ElecEnums.ElecShowType.CHECK_STO)
            {
                //// 数量
                c_Cols.Add("本方,N_B_SL");
                c_Cols.Add("对方,N_D_SL");
                c_Cols.Add("差额,N_D_SL_CE");
            }

            //// 金额
            c_Cols.Add("本方,N_B_JE");
            c_Cols.Add("对方,N_D_JE");
            c_Cols.Add("差额,N_D_JE_CE");

            //// 借方发生额/成本/期末值/本年数/本期所有者权益合计
            c_Cols.Add("本方,N_B_JEO");
            c_Cols.Add("对方,N_D_JEO");
            c_Cols.Add("差额,N_D_JEO_CE");

            if (elecType == ElecEnums.ElecShowType.CHECK_ACT || elecType == ElecEnums.ElecShowType.CHECK_DBLGZ || elecType == ElecEnums.ElecShowType.CHECK_STO || elecType == ElecEnums.ElecShowType.CHECK_SYZQY)
            {
                //// 贷方发生额/估值增值/上期实收基金
                c_Cols.Add("本方,N_B_JET");
                c_Cols.Add("对方,N_D_JET");
                c_Cols.Add("差额,N_D_JET_CE");
            }
 
            ////wlx 20160818 BUG137452显示出对账差异 在前台显示对账结果字段
            c_Cols.Add("核对结果,C_RESULT");

            Column col = null;

            Row r1 = new Row();
            Row r2 = new Row();
            Cell c1 = null;
            Cell c2 = null;

            for (int i = 0; i < c_Cols.Count; i++)
            {
                string[] arrCol = Regex.Split(Convert.ToString(c_Cols[i]), ",");
                c1 = new Cell(arrCol[0]);
                c2 = new Cell(arrCol[0]);

                if ("ShowRowCheckBoxColumn".Equals(arrCol[1]))
                {
                    col = new CheckBoxColumn();
                    col.DataPropertyName = arrCol[1];
                    table.FixedLeftCols += 1;
                }
                else if ("ShowRowIndexColumn".Equals(arrCol[1]))
                {
                    col = new MarkColumn();
                    col.DataPropertyName = arrCol[1];
                    table.FixedLeftCols += 1;
                }
                else
                {
                    col = new Column(arrCol[0], arrCol[1]);
                    col.DataPropertyName = arrCol[1];
                    col.Visible = true;

                    col.Width = 120;

                    col.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Center;

                    if (elecType == ElecEnums.ElecShowType.CHECK_ACT || elecType == ElecEnums.ElecShowType.CHECK_DBLGZ || elecType == ElecEnums.ElecShowType.CHECK_STO)
                    {
                        //// 第一行7个需要合并的单元格
                       if (i == extraCount)
                        {
                            c1 = new Cell("组合代码");
                            col.Width = 100;
                        }
                        else if (i == extraCount + 1)
                        {
                            c1 = new Cell("组合简称");
                        }
                        else if (i == extraCount + 2)
                       {
                           c1 = new Cell("日期");
                           col.Width = 70;
                        }
                          
                       else if (i == extraCount+3 || i == extraCount + 4)
                        {
                            c1 = new Cell("科目代码");
                        }
                        else if (i == extraCount + 5 || i == extraCount + 6)
                        {
                            c1 = new Cell("科目名称");
                        }
                        else if (i == extraCount + 7 || i == extraCount + 8 || i == extraCount + 9)
                        {
                            c1 = new Cell("数量");
                        }
                        else if (i == extraCount + 10 || i == extraCount + 11 || i == extraCount + 12)
                        {
                            c1 = new Cell("金额");
                        }
                       else if (i == extraCount + 13 || i == extraCount + 14 || i == extraCount + 15)
                       {
                           c1 = new Cell("成本");
                       }
                       else if (i == extraCount + 16 || i == extraCount + 17 || i == extraCount + 18)
                       {
                           c1 = new Cell("估值增值");
                       }
                    }
                    
                }

                r1.Cells.Add(c1);
                r2.Cells.Add(c2);
                table.Columns.Add(col);
            }

            table.Rows.Add(r1);
            table.Rows.Add(r2);

            ////合并行、列
            ////标记列
            if (bShowCheckColumn && extraCount == 1)
            {
                table.Rows[0].Cells[0].RowSpan = 2;
                table.Rows[0].Cells[0].TextAlign = ContentAlignment.MiddleCenter;
            }
            else if (bShowRowIndexColumn && extraCount == 1)
            {
                table.Rows[0].Cells[0].RowSpan = 2;
                table.Rows[0].Cells[0].TextAlign = ContentAlignment.MiddleCenter;

                table.Rows[0].Cells[1].RowSpan = 2;
                table.Rows[0].Cells[1].TextAlign = ContentAlignment.MiddleCenter;

                table.Rows[0].Cells[2].RowSpan = 2;
                table.Rows[0].Cells[2].TextAlign = ContentAlignment.MiddleCenter;

                table.Rows[0].Cells[3].RowSpan = 2;
                table.Rows[0].Cells[3].TextAlign = ContentAlignment.MiddleCenter;
            }
            else if (extraCount == 2)
            {
                table.Rows[0].Cells[0].RowSpan = 2;
                table.Rows[0].Cells[0].TextAlign = ContentAlignment.MiddleCenter;
                table.Rows[0].Cells[1].RowSpan = 2;
                table.Rows[0].Cells[1].TextAlign = ContentAlignment.MiddleCenter;
            }
            ////wlx 20160818 BUG137452显示出对账差异 在前台显示对账结果字段
            table.Rows[0].Cells[table.Columns.Count - 1].RowSpan = 2;
            table.Rows[0].Cells[table.Columns.Count - 1].TextAlign = ContentAlignment.MiddleCenter;

            //// 列合并 start

            if (elecType == ElecEnums.ElecShowType.CHECK_ACT || elecType == ElecEnums.ElecShowType.CHECK_DBLGZ || elecType == ElecEnums.ElecShowType.CHECK_STO)
            {
                //// 科目代码
                table.Rows[0].Cells[extraCount + 3].ColSpan = 2;
                table.Rows[0].Cells[extraCount+3].TextAlign = ContentAlignment.MiddleCenter;
                //// 科目名称
                table.Rows[0].Cells[extraCount + 5].ColSpan = 2;
                table.Rows[0].Cells[extraCount + 5].TextAlign = ContentAlignment.MiddleCenter;
                //// 数量
                table.Rows[0].Cells[extraCount + 7].ColSpan = 3;
                table.Rows[0].Cells[extraCount + 7].TextAlign = ContentAlignment.MiddleCenter;
                //// 金额
                table.Rows[0].Cells[extraCount + 10].ColSpan = 3;
                table.Rows[0].Cells[extraCount + 10].TextAlign = ContentAlignment.MiddleCenter;
                //// 借方发生额(成本)
                table.Rows[0].Cells[extraCount + 13].ColSpan = 3;
                table.Rows[0].Cells[extraCount + 13].TextAlign = ContentAlignment.MiddleCenter;
                //// 贷方发生额(估值增值)
                table.Rows[0].Cells[extraCount + 16].ColSpan = 3;
                table.Rows[0].Cells[extraCount + 16].TextAlign = ContentAlignment.MiddleCenter;
            }

            for (int i = 0; i < table.Rows[1].Cells.Count; i++)
            {
                table.Rows[1].Cells[i].TextAlign = ContentAlignment.MiddleCenter;
            }

            //// 列头行不能整行选中
            table.Rows[0].FullRowSelected = false;
            table.Rows[1].FullRowSelected = false;

            //// 前四行为列头
            table.Rows[0].IsHeader = true;
            table.Rows[1].IsHeader = true;

            table.ShowColumnHeader = false;

            table.Rows[0].Height = 18;
            table.Rows[1].Height = 18;

            table.FixedTopRows = 5;
            table.FixedLeftCols = extraCount + 7;
            table.AutoColumnWidth = true;
            ////table.DragColumn = true;
            table.Refresh();

            return extraCount;
        }

        /// <summary>
        /// 加急STORY #96252 对账结果明细需支持导出
        /// </summary>
        /// <param name="table">table</param>
        /// <param name="pojoList">pojoList</param>
        /// <param name="resultType">resultType</param>
        /// <param name="copySameData">copySameData</param>
        public void drawBatchMxData(Table table, List<BasePojo> pojoList, string resultType, bool copySameData, List<Port> portList)
        {
            object cellValueObj = null;
            string cellValue = "";
            ////BUG206982【鹏华基金】电子对账月报反馈8条不一致，但点开详细只有一条不一致
            ////SortedDictionary<string, Row> htView = new SortedDictionary<string, Row>();
            SortedDictionary<string, List<Row>> htView = new SortedDictionary<string, List<Row>>();
            if (null != pojoList && pojoList.Count > 0)
            {
                ////设置父节点
                ResetNodeAndParentNodePropertyName(pojoList, ClsEnums.KTableDataShowMode.TreeMode);
                foreach (ErResult xerResult in pojoList)
                {
                    Row row = new Row();
                    row.Tag = xerResult;

                    //// 对应数量或金额的单元格
                    Cell cell_B_SL = null;
                    Cell cell_D_SL = null;
                    Cell cell_D_SL_CE = null;
                    Cell cell_B_JE = null;
                    Cell cell_D_JE = null;
                    Cell cell_D_JE_CE = null;
                    Cell cell_B_JEO = null;
                    Cell cell_D_JEO = null;
                    Cell cell_D_JEO_CE = null;
                    Cell cell_B_JET = null;
                    Cell cell_D_JET = null;
                    Cell cell_D_JET_CE = null;
                    Cell cell_B_JETH = null;
                    Cell cell_D_JETH = null;
                    Cell cell_D_JETH_CE = null;
                    Cell cell_B_JEF = null;
                    Cell cell_D_JEF = null;
                    Cell cell_D_JEF_CE = null;
                    Cell cell_B_JEFI = null;
                    Cell cell_D_JEFI = null;
                    Cell cell_D_JEFI_CE = null;

                    foreach (Column column in table.Columns)
                    {
                        Cell cell = null;

                        if (column.DataPropertyName.Equals("ShowRowCheckBoxColumn")
                            || column.DataPropertyName.Equals("ShowRowIndexColumn"))
                        {
                            cellValueObj = null;
                        }
                        else
                        {
                            if (column.DataPropertyName.EndsWith("_CE"))
                            {
                                if (resultType.Equals("ALL_DATA") && copySameData == false &&
                                    ("0".Equals(xerResult.C_RESULT) || (null != xerResult.C_RESULT && xerResult.C_RESULT.Contains("一致") && !xerResult.C_RESULT.Contains("不一致"))))
                                {
                                    cellValueObj = null;
                                }
                                else
                                {
                                    string columnName = column.DataPropertyName.Replace("_CE", "");
                                    string columnName0 = columnName.Replace("_D_", "_B_");
                                    cellValueObj = Convert.ToDecimal(ReflectBase.getAttrValue(columnName0, xerResult)) - Convert.ToDecimal(ReflectBase.getAttrValue(columnName, xerResult));
                                }
                            }
                            else
                            {
                                ////cellValueObj = ReflectBase.getAttrValue(column.DataPropertyName, erResult);
                                ////STORY #51518 南方基金-“电子对账详细信息”只显示存在差异的数据  填充表格前数据处理
                                if (resultType.Equals("ALL_DATA") && copySameData == false &&
                                ("0".Equals(xerResult.C_RESULT) || (null != xerResult.C_RESULT && xerResult.C_RESULT.Contains("一致") && !xerResult.C_RESULT.Contains("不一致")))
                                  && checkColumn(column))
                                {
                                    cellValueObj = null;
                                }
                                else
                                {
                                    if ("C_PORT_CODE".Equals(column.DataPropertyName))
                                    {
                                        cellValueObj = xerResult.C_ASS_CODE;
                                    }
                                    else if ("C_PORT_NAME_ST".Equals(column.DataPropertyName))
                                    {
                                        foreach (Port p in portList) {
                                            if (xerResult.C_ASS_CODE.Equals(p.C_PORT_CODE)) {
                                                cellValueObj = p.C_PORT_NAME_ST;
                                            }
                                        }
                                
                                    }
                                    else
                                    {
                                        cellValueObj = ReflectBase.getAttrValue(column.DataPropertyName, xerResult);
                                    }
                                }
                            }
                        }

                        if (cellValueObj == null)
                        {
                            cellValue = "";
                            cell = new Cell(cellValue);
                        }
                        else
                        {
                            cell = new Cell();

                            if (cellValueObj is DateTime)
                            {
                                cellValue = ((DateTime)cellValueObj).ToString("yyyy-MM-dd");
                            }
                            else if (cellValueObj is decimal)
                            {
                                cellValue = ((decimal)cellValueObj).ToString("#,##0.00########");
                                cell.TextAlign = ContentAlignment.MiddleRight;
                            }
                            else
                            {
                                cellValue = cellValueObj.ToString();
                            }

                            if ("null".Equals(cellValue))
                            {
                                cellValue = "";
                            }

                            cell.Text = cellValue;

                            //// 给各单元格赋值
                            if ("N_B_SL".Equals(column.DataPropertyName))
                            {
                                cell_B_SL = cell;
                            }
                            else if ("N_D_SL".Equals(column.DataPropertyName))
                            {
                                cell_D_SL = cell;
                            }
                            else if ("N_D_SL_CE".Equals(column.DataPropertyName))
                            {
                                cell_D_SL_CE = cell;
                            }
                            else if ("N_B_JE".Equals(column.DataPropertyName))
                            {
                                cell_B_JE = cell;
                            }
                            else if ("N_D_JE".Equals(column.DataPropertyName))
                            {
                                cell_D_JE = cell;
                            }
                            else if ("N_D_JE_CE".Equals(column.DataPropertyName))
                            {
                                cell_D_JE_CE = cell;
                            }
                            else if ("N_B_JEO".Equals(column.DataPropertyName))
                            {
                                cell_B_JEO = cell;
                            }
                            else if ("N_D_JEO".Equals(column.DataPropertyName))
                            {
                                cell_D_JEO = cell;
                            }
                            else if ("N_D_JEO_CE".Equals(column.DataPropertyName))
                            {
                                cell_D_JEO_CE = cell;
                            }
                            else if ("N_B_JET".Equals(column.DataPropertyName))
                            {
                                cell_B_JET = cell;
                            }
                            else if ("N_D_JET".Equals(column.DataPropertyName))
                            {
                                cell_D_JET = cell;
                            }
                            else if ("N_D_JET_CE".Equals(column.DataPropertyName))
                            {
                                cell_D_JET_CE = cell;
                            }
                            else if ("N_B_JETH".Equals(column.DataPropertyName))
                            {
                                cell_B_JETH = cell;
                            }
                            else if ("N_D_JETH".Equals(column.DataPropertyName))
                            {
                                cell_D_JETH = cell;
                            }
                            else if ("N_D_JETH_CE".Equals(column.DataPropertyName))
                            {
                                cell_D_JETH_CE = cell;
                            }
                            else if ("N_B_JEF".Equals(column.DataPropertyName))
                            {
                                cell_B_JEF = cell;
                            }
                            else if ("N_D_JEF".Equals(column.DataPropertyName))
                            {
                                cell_D_JEF = cell;
                            }
                            else if ("N_D_JEF_CE".Equals(column.DataPropertyName))
                            {
                                cell_D_JEF_CE = cell;
                            }
                            else if ("N_B_JEFI".Equals(column.DataPropertyName))
                            {
                                cell_B_JEFI = cell;
                            }
                            else if ("N_D_JEFI".Equals(column.DataPropertyName))
                            {
                                cell_D_JEFI = cell;
                            }
                            else if ("N_D_JEFI_CE".Equals(column.DataPropertyName))
                            {
                                cell_D_JEFI_CE = cell;
                            }
                        }

                        row.Cells.Add(cell);
                    }

                    ////核对结果一致
                    bool result = ("0".Equals(xerResult.C_RESULT) || (null != xerResult.C_RESULT && xerResult.C_RESULT.Contains("一致") && !xerResult.C_RESULT.Contains("不一致")));
                    //// 更改单元格的颜色
                    modifyCellColor(cell_B_SL, cell_D_SL, cell_D_SL_CE, cell_B_JE, cell_D_JE, cell_D_JE_CE, cell_B_JEO, cell_D_JEO, cell_D_JEO_CE, cell_B_JET, cell_D_JET, cell_D_JET_CE, cell_B_JETH, cell_D_JETH, cell_D_JETH_CE, cell_B_JEF, cell_D_JEF, cell_D_JEF_CE, cell_B_JEFI, cell_D_JEFI, cell_D_JEFI_CE, result);
                    setHtViewExtendsPojo(htView, xerResult, row);
                    ////table.Rows.Add(row);
                }
                ////table.AutoWidth();
                loadTreeViewExtendsPojo(table, htView);
                table.Refresh();
            }
        }
        
    }
}
