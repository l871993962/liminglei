using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Yss.KTable.Models;
using System.Collections;
using System.Text.RegularExpressions;
using System.Drawing;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Pojo;
using FAST.Core.Util;
using Yss.KTable.Enums;

namespace YssElecReco.Fun
{
    /// <summary>
    /// 对账余额表
    /// </summary>
  public class ErYebTableListLoader : TableListLoader
    {
        /////// <summary>
        /////// 修改列头
        /////// </summary>
        /////// <param name="tbMain">tbMain</param>
        ////protected override void updateColumnHeader(Yss.KTable.Models.Table tbMain)
        ////{
        ////    init_Table_Column(tbMain,this.showCheckColumn,this.showMarkColumn);
        ////    tbMain.Columns.RemoveAt(1);
        ////}

        /////// <summary>
        /////// 修改行的颜色
        /////// </summary>
        /////// <param name="pojo"></param>
        ////protected override void updateRowColor(BasePojo pojo, Yss.KTable.Models.Row row)
        ////{
        ////    ////CwHd erYehd = pojo as CwHd;
        ////    ////if (erYehd.N_AMOUNT_DIFF != 0 || erYehd.N_PORT_MONEY_DIFF != 0 || erYehd.N_ORIG_MONEY_DIFF != 0 || erYehd.C_KM_CODE.Trim().Length == 0 || erYehd.C_KM_CODE2.Trim().Length == 0)
        ////    ////{
        ////    ////    setRowBackColor(0, row);
        ////    ////}
        ////}

        /////// <summary>
        /////// 
        /////// </summary>
        /////// <param name="headKey"></param>
        /////// <returns></returns>
        ////protected override bool hiddenColumn(ListHeadInfo headKey)
        ////{
        ////    if (headKey.Key.Equals("C_DV_ER_WAY"))
        ////    {
        ////        return true;
        ////    }

        ////    return false;
        ////}


        /////// <summary>
        /////// 初始化主区域的KTable控件(包含对账信息)
        /////// </summary>
        /////// <param name="table">对应的表格</param>
        /////// <param name="isShowCheckColumn">是否显示复选框标志</param>
        /////// <param name="isShowRowIndexColumn">是否显示序号标志</param>
        /////// <param name="elecType">核对表类型</param>
        /////// <returns>控制列头数</returns>
        ////public int init_Table_Column(Table table, bool isShowCheckColumn, bool isShowRowIndexColumn)
        ////{
        ////    table.Tag = new Dictionary<string, Yss.KTable.Models.Row>();
        ////    int extraCount = 0;
        ////    ArrayList c_Cols = new ArrayList();

        ////    if (isShowCheckColumn)
        ////    {
        ////        c_Cols.Add(",ShowRowCheckBoxColumn");
        ////        extraCount++;
        ////    }
        ////    if (isShowRowIndexColumn)
        ////    {
        ////        c_Cols.Add(",ShowRowIndexColumn");
        ////        extraCount++;
        ////    }
        ////        //// 科目代码
        ////    ////c_Cols.Add("报文序号,C_SN");
        ////    //// 币种代码
        ////    ////c_Cols.Add("文件类型,C_FILE_TYPE");
        ////        //// 科目代码
        ////    ////c_Cols.Add("基金代码,C_ASS_CODE");
        ////    //// 币种代码
        ////    ////c_Cols.Add("开始日期,D_START_DATE");
        ////        //// 科目代码
        ////    ////c_Cols.Add("结束日期,D_END_DATE");
        ////    //// 科目代码
        ////    c_Cols.Add("科目代码,C_KM_CODE");
        ////    //// 科目名称
        ////    //STORY55542汇添富项目，电子对账余额表（银行间代码转换规则）明细科目，科目名称没有显示出来。
        ////    c_Cols.Add("科目名称,C_KM_NAME");
        ////    //// 币种代码
        ////    c_Cols.Add("币种代码,C_DC_CODE");
        ////    //// 金额
        ////    c_Cols.Add("原币,N_ORIG_STARTBAL");
        ////    c_Cols.Add("本位币,N_PORT_STARTBAL");
        ////    c_Cols.Add("数量,N_AMOUNT_STARTBAL");

        ////    c_Cols.Add("原币,N_ORIG_DEBIT");
        ////    c_Cols.Add("本位币,N_PORT_DEBIT");
        ////    c_Cols.Add("数量,N_AMOUNT_DEBIT");

        ////    c_Cols.Add("原币,N_ORIG_CREDIT");
        ////    c_Cols.Add("本位币,N_PORT_CREDIT");
        ////    c_Cols.Add("数量,N_AMOUNT_CREDIT");

        ////    c_Cols.Add("原币,N_ORIG_ENDBAL");
        ////    c_Cols.Add("本位币,N_PORT_ENDBAL");
        ////    c_Cols.Add("数量,N_AMOUNT_ENDBAL");

        ////    c_Cols.Add("是否最明细科目,N_DETAIL");
        ////    c_Cols.Add("借方,N_J_TOLTAL_AMOUNT");
        ////    c_Cols.Add("贷方,N_D_TOLTAL_AMOUNT");
        ////    Column col = null;

        ////    Row r1 = new Row();
        ////    Row r2 = new Row();
        ////    Cell c1 = null;
        ////    Cell c2 = null;
        ////    int index = 0;
        ////    for (int i = 0; i < c_Cols.Count; i++)
        ////    {
        ////        string[] arrCol = Regex.Split(Convert.ToString(c_Cols[i]), ",");
        ////        c1 = new Cell(arrCol[0]);
        ////        c2 = new Cell(arrCol[0]);
        ////        col = new Column(arrCol[0], arrCol[1]);
        ////        col.DataPropertyName = arrCol[1];
        ////        col.Visible = true;

        ////        col.Width = 100;

        ////        col.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Center;

        ////        if (i == extraCount + 2 + index || i == extraCount + 3 + index || i == extraCount + 4 + index)
        ////        {
        ////            c1 = new Cell("期初余额");
        ////        }
        ////        else if (i == extraCount + 5 + index || i == extraCount + 6 + index || i == extraCount + 7 + index)
        ////        {
        ////            c1 = new Cell("本期借方发生额");
        ////        }
        ////        else if (i == extraCount + 8 + index || i == extraCount + 9 + index || i == extraCount + 10 + index)
        ////        {
        ////            c1 = new Cell("本期贷方发生额");
        ////        }
        ////        else if (i == extraCount + 11 + index || i == extraCount + 12 + index || i == extraCount + 13 + index)
        ////        {
        ////            c1 = new Cell("期末余额");
        ////        }
        ////        else if (i == extraCount + 14 + index)
        ////        {
        ////            c1 = new Cell("是否最明细科目");
        ////        }
        ////        else if (i == extraCount + 15 + index || i == extraCount + 16 + index)
        ////        {
        ////            c1 = new Cell("累计发生额");
        ////        }

        ////        r1.Cells.Add(c1);
        ////        r2.Cells.Add(c2);
        ////        table.Columns.Add(col);
        ////    }

        ////    table.Rows.Add(r1);
        ////    table.Rows.Add(r2);

        ////    //合并行、列
        ////    //标记列
        ////    if (isShowCheckColumn && extraCount == 1)
        ////    {
        ////        table.Rows[0].Cells[0].RowSpan = 2;
        ////        table.Rows[0].Cells[0].TextAlign = ContentAlignment.MiddleCenter;
        ////    }
        ////    else if (isShowRowIndexColumn && extraCount == 1)
        ////    {
        ////        table.Rows[0].Cells[0].RowSpan = 2;
        ////        table.Rows[0].Cells[0].TextAlign = ContentAlignment.MiddleCenter;
        ////    }
        ////    else if (extraCount == 2)
        ////    {
        ////        table.Rows[0].Cells[0].RowSpan = 2;
        ////        table.Rows[0].Cells[0].TextAlign = ContentAlignment.MiddleCenter;
        ////        table.Rows[0].Cells[1].RowSpan = 2;
        ////        table.Rows[0].Cells[1].TextAlign = ContentAlignment.MiddleCenter;
        ////    }

        ////    //// 列合并 start
        ////    table.Rows[0].Cells[extraCount].RowSpan = 2;
        ////    table.Rows[0].Cells[extraCount].TextAlign = ContentAlignment.MiddleCenter;
        ////    table.Rows[0].Cells[extraCount + 1].RowSpan = 2;
        ////    table.Rows[0].Cells[extraCount + 1].TextAlign = ContentAlignment.MiddleCenter;

        ////    //// 列合并 start
        ////    ////table.Rows[0].Cells[extraCount + 2].RowSpan = 2;
        ////    ////table.Rows[0].Cells[extraCount + 2].TextAlign = ContentAlignment.MiddleCenter;
        ////    ////table.Rows[0].Cells[extraCount + 3].RowSpan = 2;
        ////    ////table.Rows[0].Cells[extraCount + 3].TextAlign = ContentAlignment.MiddleCenter;
        ////    //////// 列合并 start
        ////    ////table.Rows[0].Cells[extraCount + 4].RowSpan = 2;
        ////    ////table.Rows[0].Cells[extraCount + 4].TextAlign = ContentAlignment.MiddleCenter;
        ////    ////table.Rows[0].Cells[extraCount + 5].RowSpan = 2;
        ////    ////table.Rows[0].Cells[extraCount + 5].TextAlign = ContentAlignment.MiddleCenter;
        ////    ////table.Rows[0].Cells[extraCount + 6].RowSpan = 2;
        ////    ////table.Rows[0].Cells[extraCount + 6].TextAlign = ContentAlignment.MiddleCenter;

        ////    table.Rows[0].Cells[extraCount + 2 + index].ColSpan = 3;
        ////    table.Rows[0].Cells[extraCount + 2 + index].TextAlign = ContentAlignment.MiddleCenter;
        ////    //// 
        ////    table.Rows[0].Cells[extraCount + 5 + index].ColSpan = 3;
        ////    table.Rows[0].Cells[extraCount + 5 + index].TextAlign = ContentAlignment.MiddleCenter;
        ////    //// 
        ////    table.Rows[0].Cells[extraCount + 8 + index].ColSpan = 3;
        ////    table.Rows[0].Cells[extraCount + 8 + index].TextAlign = ContentAlignment.MiddleCenter;
        ////    //// 
        ////    table.Rows[0].Cells[extraCount + 11 + index].ColSpan = 3;
        ////    table.Rows[0].Cells[extraCount + 11 + index].TextAlign = ContentAlignment.MiddleCenter;

        ////    table.Rows[0].Cells[extraCount + 14 + index].RowSpan = 2;
        ////    table.Rows[0].Cells[extraCount + 14 + index].TextAlign = ContentAlignment.MiddleCenter;

        ////    table.Rows[0].Cells[extraCount + 15 + index].ColSpan = 2;
        ////    table.Rows[0].Cells[extraCount + 15 + index].TextAlign = ContentAlignment.MiddleCenter;

        ////    for (int i = 0; i < table.Rows[1].Cells.Count; i++)
        ////    {
        ////        table.Rows[1].Cells[i].TextAlign = ContentAlignment.MiddleCenter;
        ////    }
        ////    table.FixedTopRows = 2;
        ////    //// 列头行不能整行选中
        ////    table.Rows[0].FullRowSelected = false;
        ////    table.Rows[1].FullRowSelected = false;

        ////    table.Rows[0].IsHeader = true;
        ////    table.Rows[1].IsHeader = true;

        ////    table.ShowColumnHeader = false;

        ////    table.Rows[0].Height = 18;
        ////    table.Rows[1].Height = 18;

        ////    table.AutoColumnWidth = true;

        ////    return extraCount;
        ////}

        /// <summary>
        /// 重写装载列头方法
        /// </summary>
        /// <param name="tbMain">目标表格</param>
        /// <param name="headKeys">列头信息</param>
        protected override void setTableListHead(Table tbMain, List<ListHeadInfo> headKeys)
        {
            int colCount = 0;
            Cell cell0 = null;
            Cell cell1 = null;
            Row row0 = new Row();
            Row row1 = new Row();

            row0.FullRowSelected = false;
            row1.FullRowSelected = false;
            row0.IsHeader = true;
            row1.IsHeader = true;
            row0.Height = 18;
            row1.Height = 18;
            tbMain.Rows.Add(row0);
            tbMain.Rows.Add(row1);
            tbMain.FixedTopRows = 2;
            tbMain.ShowColumnHeader = false;

            int i = 0;
            if (showCheckColumn)
            {
                cell0 = new Cell();
                cell1 = new Cell();
                ////cell0.RowSpan = 2;
                cell0.TextAlign = ContentAlignment.MiddleCenter;
                row0.Cells.Add(cell0);
                row1.Cells.Add(cell1);
                row0.Cells[0].RowSpan = 2;
                i++;
            }

            if (showMarkColumn)
            {
                cell0 = new Cell();
                cell1 = new Cell();
                ////cell0.RowSpan = 2;
                cell0.TextAlign = ContentAlignment.MiddleCenter;
                row0.Cells.Add(cell0);
                row1.Cells.Add(cell1);
                row0.Cells[i].RowSpan = 2;
            }


            string colFlag = null;
            Cell merCell = null;
            foreach (ListHeadInfo headKey in headKeys)
            {
                if (headKey.Text.Contains("[") && headKey.Text.Contains("]"))
                {
                    string temp = headKey.Text.Substring(headKey.Text.LastIndexOf("[") + 1);
                    temp = temp.Remove(temp.LastIndexOf("]"));
                    cell0 = new Cell(temp);
                    cell1 = new Cell(headKey.Text.Remove(headKey.Text.LastIndexOf("[")));
                    
                    if (colFlag == null || !colFlag.Equals(temp))
                    { ////非一个父单元格
                        colFlag = temp;
                        merCell = cell0;
                    }
                    else
                    { ////合并
                        merCell.ColSpan = merCell.ColSpan + 1;
                        merCell.TextAlign = ContentAlignment.MiddleCenter;
                    }

                    row0.Cells.Add(cell0);
                    row1.Cells.Add(cell1);
                }
                else
                {
                    cell0 = new Cell(headKey.Text);
                    cell1 = new Cell(headKey.Text);
                    ////cell0.RowSpan = 2;
                    cell0.TextAlign = ContentAlignment.MiddleCenter;
                    row0.Cells.Add(cell0);
                    row1.Cells.Add(cell1);
                    cell0.RowSpan = 2;
                }

                cell0.TextAlign = ContentAlignment.MiddleCenter;
                cell1.TextAlign = ContentAlignment.MiddleCenter;

                Column col = new Column();
                col.Text = headKey.Text;
                col.DataPropertyName = headKey.Key;
                ////临时解决办法，将审核状态列强制指定为string类型。后期要调整Pojo的列名，或者是使用列词典作翻译。
                if (col.DataPropertyName == "N_CHECK_STATE")
                {
                    col.DataType = ColumnDataType.String;
                }

                col.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Center;
                tbMain.Columns.Add(col);

                if (headKey.Align == null)
                {
                    headKey.Align = "L";
                }

                setCellTextAlign(col, headKey.Align.Trim());
                colCount++;
            }

            tbMain.AutoColumnWidth = true;
        }

    }
}
