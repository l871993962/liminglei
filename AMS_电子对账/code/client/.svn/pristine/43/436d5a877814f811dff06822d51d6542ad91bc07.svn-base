using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using Yss.KTable.Models;
using System.Collections;
using System.Text.RegularExpressions;
using System.Reflection;
using YssElecReco.Pojo.Er;
using FAST.Core.Context.Events;
using FAST.Core.Exceptions;
using FAST.Core.BaseControl.Fun;
using FAST.Core.Util;
using FAST.Core.Context;

namespace YssElecReco.Form.Er
{
    /// <summary>
    /// 对账结果浏览界面
    /// </summary>
    public partial class Frm_ELEC_BBINFO_L
    {
        /// <summary>
        /// 用于存放估值表普通项数据
        /// </summary>
        private List<ErGzb> gzbList = new List<ErGzb>();

        /// <summary>
        /// 估值表total项数据
        /// </summary>
        private List<ErGzb> gzbTotalList = new List<ErGzb>();

        /// <summary>
        /// 估值表total_all项数据
        /// </summary>
        private List<ErGzb> gzbTotalAllList = new List<ErGzb>();

        //// ---------  科目表数据加载---------------------------------

        /// <summary>
        /// 加载电子对账科目数据方法
        /// </summary>
        /// <param name="table">table</param>
        /// <param name="basepojo">电子对账科目数据对象列表</param>
        private void loadKmDataToTable(Table table, List<ErKmb> basepojo)
        {
            table.Clear();

            // 初始化列头
            init_TbKmb_Columns(table);

            if (basepojo.Count > 0)
            {
                // 加载数据
                ////loadKmDetail(basepojo, table);

                loadKmDataToKTable(table);
            }
            
            table.Refresh();
        }

        /// <summary>
        /// 初始化电子对账科目数据加载列表列头
        /// </summary>
        /// <param name="table">table</param>
        private void init_TbKmb_Columns(Table table)
        {
            try
            {
                ArrayList c_Cols = new ArrayList();
                c_Cols.Add("科目代码,Facctcode");
                c_Cols.Add("科目名称,Facctname");
                c_Cols.Add("科目类别,Facctclassname");
                c_Cols.Add("余额方向,Fbaldcname");
                c_Cols.Add("开始日期,Fbdate");
                c_Cols.Add("结束日期,Fedate");

                Yss.KTable.Models.Column col = null;

                col = new CheckBoxColumn();
                col.DataPropertyName = "ShowRowCheckBoxColumn";
                table.Columns.Insert(0, col);

                col = new MarkColumn();
                col.DataPropertyName = "ShowRowIndexColumn";
                table.Columns.Insert(1, col);

                for (int i = 0; i < c_Cols.Count; i++)
                {
                    col = new Column();

                    string[] arrCol = Regex.Split(Convert.ToString(c_Cols[i]), ",");
                    col.DataPropertyName = arrCol[1];
                    col.Visible = true;

                    col.Width = 120;
                    col.Text = arrCol[0];
                    col.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Center;

                    table.Columns.Add(col);
                }

                table.ShowColumnHeader = true;
                table.FixedTopRows = 0;
                table.FixedLeftCols = 0;
                table.Refresh();

            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 加载一行数据到主区域-- 科目表
        /// </summary>
        /// <param name="xerKmb">xerKmb</param>
        /// <param name="table">table</param>
        private void loadKmRowData(ErKmb xerKmb, Table table)
        {
            Dictionary<string, Row> c_HtMain = null;

            if (null == table.Tag)
            {
                c_HtMain = new Dictionary<string, Row>();
            }
            else
            {
                c_HtMain = table.Tag as Dictionary<string, Row>;
            }

            Yss.KTable.Models.Row row = new Row();
            Yss.KTable.Models.Cell cell = null;

            ////if (erKmb.Facctcode == null || erKmb.Facctcode.Length == 0)
            ////{
            ////    return;
            ////}

            foreach (Yss.KTable.Models.Column col in table.Columns)
            {
                string c_Temps = "";

                if (col.DataPropertyName.Equals("ShowRowCheckBoxColumn") || col.DataPropertyName.Equals("ShowRowIndexColumn"))
                {
                    c_Temps = "";
                }
                else
                {
                    c_Temps = Convert.ToString(ReflectBase.getAttrValue(col.DataPropertyName, xerKmb));
                }

                cell = new Cell(c_Temps);

                // 实现科目代码列自适应
                if (col.DataPropertyName.Equals("Facctcode") || col.DataPropertyName.Equals("Facctname"))
                {
                    int n_Width = (int)clsInterface.charWidth(cell.Text, tbMain.Font.Name, tbMain.Font.Size, this) + 2;
                    col.Width = n_Width >= col.Width ? n_Width : col.Width;
                }

                row.Cells.Add(cell);

            }

            row.Tag = xerKmb;

            ////if (!c_HtMain.ContainsKey(erKmb.Facctcode))
            ////{
            ////    c_HtMain.Add(erKmb.Facctcode, row);
            ////}

            table.Tag = c_HtMain;
        }

        /// <summary>
        /// 加载数据到KTable中
        /// </summary>
        /// <param name="table">table</param>
        protected void loadKmDataToKTable(Table table)
        {
            Dictionary<string, Yss.KTable.Models.Row> c_HtRows = null;

            if (null == table.Tag)
            {
                return;
            }

            c_HtRows = table.Tag as Dictionary<string, Yss.KTable.Models.Row>;

            foreach (KeyValuePair<string, Yss.KTable.Models.Row> ider in c_HtRows)
            {
                // 在集合中判断父节点，如果查到则将本节点加到row的subrow中，查不到则把row添加到table中
                Row tempRow = ider.Value as Row;
                ErKmb xerKmb = (ErKmb)tempRow.Tag;

                // 如果存在表的父级节点
                ////if (c_HtRows.ContainsKey(erKmb.Facctparent))
                ////{
                ////    c_HtRows[erKmb.Facctparent].SubRows.Add(tempRow);


                ////    // 如果子行行数等于0加载相关的图片
                ////    if (tempRow.SubRows.Count == 0)
                ////    {
                ////        // 定义，如果KTABLE类型为NoIndentWithIcon则不显示图片
                ////        if (table.PlusMinusStyle != Yss.KTable.Enums.PlusMinusStyles.IconIndent)
                ////        {
                ////            tempRow.SmallImage = Resource.tab_Node_Defore;
                ////        }
                ////    }

                ////    // 定义，如果KTABLE类型为NoIndentWithIcon则不显示图片
                ////    if (table.PlusMinusStyle != Yss.KTable.Enums.PlusMinusStyles.IconIndent)
                ////    {
                ////        Row parentRow = c_HtRows[erKmb.Facctparent] as Row;
                ////        parentRow.SmallImage = Resource.tab_Close;
                ////        parentRow.ExpandImage = Resource.tab_Open;
                ////    }
                ////}
                ////else
                ////{
                ////    //// 对有非父级节点的处理
                ////    table.Rows.Add(tempRow);

                ////    ////setBoldRow(tempRow);

                ////    // 为父节点加上伸缩和展开时的两种图片
                ////    if (table.PlusMinusStyle != Yss.KTable.Enums.PlusMinusStyles.IconIndent)
                ////    {
                ////        tempRow.SmallImage = Resource.tab_Close;
                ////        tempRow.ExpandImage = Resource.tab_Open;
                ////    }
                ////}
            }

            table.Refresh();
        }

        //// ----------    余额表数据加载  -------------

        /// <summary>
        /// 分页控件分页改变时的事件
        /// added by lizuzheng 2013-03-13
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void tbControlMain_SelectedTabChanged(object sender, Yss.Controls.TabPageEventArgs e)
        {
            for (int i = 0; i < this.tabCtrlDataMain.TabPages.Count; i++)
            {
                tabCtrlDataMain.TabPages[i].Controls.Remove(this.tbMain);
            }

            tabCtrlDataMain.SelectedTab.Controls.Add(this.tbMain);
            btnSearch_Click(sender, new EventArgs());
        }

        /// <summary>
        /// 111
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        /// <param name="be">be</param>
        /// <param name="showRefreshFlag">showRefreshFlag</param>
        protected override void procAfterSelectMVC(object sender, Yss.KTable.Events.RowEventArgs e, YssBeforeOperEventArgs be, bool showRefreshFlag)
        {
            base.procAfterSelectMVC(sender, e, be, showRefreshFlag);
            if (tabCtrlDataMain.SelectedTab.Text.Equals("未生成"))
            {
                this.btnBar.setButtonDisabled(ClsButtonName.BtnCopy);
                this.btnBar.setButtonDisabled(ClsButtonName.BtnDelete);
            }
            else if (tabCtrlDataMain.SelectedTab.Text.Equals("已反馈"))
            {
                this.btnBar.setButtonDisabled(ClsButtonName.BtnCopy);
            }
            else if (tabCtrlDataMain.SelectedTab.Text.Equals("已生成"))
            {
                 btnBar.setButtonEnabled(ClsButtonName.BtnCopy, true);
            }

            this.btnBar.Refresh();
        }

        /// <summary>
        /// 加载电子对账余额数据方法
        /// </summary>
        /// <param name="table">table</param>
        /// <param name="basepojo">电子对账余额数据对象列表</param>
        private void loadYeDataToTable(Table table, List<ErYeb> basepojo)
        {
            table.Clear();
            table.Refresh();

            // 初始化列头
            init_TbYeb_Columns(table);

            if (basepojo.Count > 0)
            {
                // 加载数据
                loadYeDetail(basepojo, table);

                loadYeDataToKTable(table);
            }
            
            table.Refresh();
        }

        /// <summary>
        /// 初始化主区域的KTable控件
        /// </summary>
        /// <param name="table">table</param>
        protected void init_TbYeb_Columns(Table table)
        {
            table.Tag = new Dictionary<string, Yss.KTable.Models.Row>();

            ArrayList c_Cols = new ArrayList();
            c_Cols.Add("科目编码,Facctcode");
            ////c_Cols.Add("序号,N_Level");
            c_Cols.Add("科目名称,Facctname");
            c_Cols.Add("币种,Fcurcode");
            ////c_Cols.Add("借.或.贷,C_Way_Ini_Y");
            ////c_Cols.Add("数量,C_A_Ini_Y");
            ////c_Cols.Add("原币金额,C_M_Ini_Y");
            //// c_Cols.Add("本位币金额,C_PM_Ini_Y");
            ////c_Cols.Add("借.或.贷,C_Way_Ini");
            c_Cols.Add("数量,Fastartbal");
            c_Cols.Add("原币金额,Fstartbal");
            c_Cols.Add("本位币金额,Fbstartbal");
            c_Cols.Add("数量,Fadebit");
            c_Cols.Add("原币金额,Fdebit");
            c_Cols.Add("本位币金额,Fbdebit");
            c_Cols.Add("数量,Facredit");
            c_Cols.Add("原币金额,Fcredit");
            c_Cols.Add("本位币金额,Fbcredit");
            c_Cols.Add("数量,");
            c_Cols.Add("原币金额,");
            c_Cols.Add("本位币金额,Fjtotalamount");
            c_Cols.Add("数量,");
            c_Cols.Add("原币金额,");
            c_Cols.Add("本位币金额,Fdtotalamount");
            ////c_Cols.Add("借.或.贷,C_Way_Bal");
            c_Cols.Add("数量,Faendbal");
            c_Cols.Add("原币金额,Fendbal");
            c_Cols.Add("本位币金额,Fbendbal");

            Yss.KTable.Models.Column col = null;

            Yss.KTable.Models.Row r1 = new Row();
            Yss.KTable.Models.Row r2 = new Row();
            Yss.KTable.Models.Row r3 = new Row();
            Yss.KTable.Models.Cell c1 = null;
            Yss.KTable.Models.Cell c2 = null;
            Yss.KTable.Models.Cell c3 = null;

            for (int i = 0; i < c_Cols.Count; i++)
            {
                string[] arrCol = Regex.Split(Convert.ToString(c_Cols[i]), ",");
                col = new Column(arrCol[0].Replace(".", ""), arrCol[1]);
                col.DataPropertyName = arrCol[1];
                col.Visible = true;

                if (i == 0 || i == 1)
                {
                    //// 科目代码与名称
                    col.Width = 120;
                }
                else if (i == 2)
                {
                    //// 币种
                    col.Width = 100;
                }
                else if (i == 3 || i == 6 || i == 9 || i == 12 || i == 15 || i == 18)
                {
                    //// 数量
                    col.Width = 100;
                }
                else
                {
                    //// 金额线
                    col.Width = 120;
                }

                col.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Center;

                c1 = new Cell(arrCol[0].Replace(".", "\n"));
                c2 = new Cell(arrCol[0].Replace(".", "\n"));
                c3 = new Cell(arrCol[0].Replace(".", "\n"));

                if (i == 3 || i == 4 || i == 5)
                {
                    c1 = new Cell("期初余额");
                }
                else if (i == 6 || i == 7 || i == 8)
                {
                    c1 = new Cell("本期借方发生额");
                }
                else if (i == 9 || i == 10 || i == 11)
                {
                    c1 = new Cell("本期贷方发生额");
                }
                else if (i == 12 || i == 13 || i == 14)
                {
                    c1 = new Cell("累计借方发生额");
                }
                else if (i == 15 || i == 16 || i == 17)
                {
                    c1 = new Cell("累计贷方发生额");
                }
                else if (i == 18 || i == 19 || i == 20)
                {
                    c1 = new Cell("期末余额");
                }

                if (i == 4 || i == 5)
                {
                    c3 = new AmountCell();
                }
                else if (i == 7 || i == 8)
                {
                    c3 = new AmountCell();
                }
                else if (i == 10 || i == 11)
                {
                    c3 = new AmountCell();
                }
                else if (i == 13 || i == 14)
                {
                    c3 = new AmountCell();
                }
                else if (i == 16 || i == 17)
                {
                    c3 = new AmountCell();
                }
                else if (i == 19 || i == 20)
                {
                    c3 = new AmountCell();
                }

                table.Columns.Add(col);
                r1.Cells.Add(c1);
                r2.Cells.Add(c2);
                r3.Cells.Add(c3);
            }

            table.Rows.Add(r1);
            table.Rows.Add(r2);
            table.Rows.Add(r3);

            //// 合并行、列
            //// 科目编码
            table.Rows[0].Cells[0].RowSpan = 3;
            table.Rows[0].Cells[0].TextAlign = ContentAlignment.MiddleCenter;
            //////// 序号
            ////table.Rows[0].Cells[1].RowSpan = 3;
            ////table.Rows[0].Cells[1].TextAlign = ContentAlignment.MiddleCenter;
            //// 科目名称
            table.Rows[0].Cells[1].RowSpan = 3;
            table.Rows[0].Cells[1].TextAlign = ContentAlignment.MiddleCenter;
            //// 币种
            table.Rows[0].Cells[2].RowSpan = 3;
            table.Rows[0].Cells[2].TextAlign = ContentAlignment.MiddleCenter;

            //////// 年初 借与贷
            ////table.Rows[0].Cells[4].RowSpan = 3;
            ////table.Rows[0].Cells[4].TextAlign = ContentAlignment.MiddleCenter;
            ////////年初数
            ////table.Rows[0].Cells[5].ColSpan = 3;
            ////table.Rows[0].Cells[5].TextAlign = ContentAlignment.MiddleCenter;
            //////// 数量
            ////table.Rows[1].Cells[5].RowSpan = 2;
            ////table.Rows[1].Cells[5].TextAlign = ContentAlignment.MiddleCenter;
            ////table.Rows[1].Cells[6].TextAlign = ContentAlignment.MiddleCenter;
            ////table.Rows[1].Cells[7].TextAlign = ContentAlignment.MiddleCenter;

            //////// 期初 借与贷
            ////table.Rows[0].Cells[3].RowSpan = 3;
            ////table.Rows[0].Cells[3].TextAlign = ContentAlignment.MiddleCenter;
            //// 年初数
            table.Rows[0].Cells[3].ColSpan = 3;
            table.Rows[0].Cells[3].TextAlign = ContentAlignment.MiddleCenter;
            //// 数量
            table.Rows[1].Cells[3].RowSpan = 2;
            table.Rows[1].Cells[3].TextAlign = ContentAlignment.MiddleCenter;
            table.Rows[1].Cells[4].TextAlign = ContentAlignment.MiddleCenter;
            table.Rows[1].Cells[5].TextAlign = ContentAlignment.MiddleCenter;

            //// 本期借方
            table.Rows[0].Cells[6].ColSpan = 3;
            table.Rows[0].Cells[6].TextAlign = ContentAlignment.MiddleCenter;
            //// 数量
            table.Rows[1].Cells[6].RowSpan = 2;
            table.Rows[1].Cells[6].TextAlign = ContentAlignment.MiddleCenter;
            table.Rows[1].Cells[7].TextAlign = ContentAlignment.MiddleCenter;
            table.Rows[1].Cells[8].TextAlign = ContentAlignment.MiddleCenter;

            //// 本期贷方
            table.Rows[0].Cells[9].ColSpan = 3;
            table.Rows[0].Cells[9].TextAlign = ContentAlignment.MiddleCenter;
            //// 数量
            table.Rows[1].Cells[9].RowSpan = 2;
            table.Rows[1].Cells[9].TextAlign = ContentAlignment.MiddleCenter;
            table.Rows[1].Cells[10].TextAlign = ContentAlignment.MiddleCenter;
            table.Rows[1].Cells[11].TextAlign = ContentAlignment.MiddleCenter;

            //// 累计借方
            table.Rows[0].Cells[12].ColSpan = 3;
            table.Rows[0].Cells[12].TextAlign = ContentAlignment.MiddleCenter;
            //// 数量
            table.Rows[1].Cells[12].RowSpan = 2;
            table.Rows[1].Cells[12].TextAlign = ContentAlignment.MiddleCenter;
            table.Rows[1].Cells[13].TextAlign = ContentAlignment.MiddleCenter;
            table.Rows[1].Cells[14].TextAlign = ContentAlignment.MiddleCenter;

            //// 累计贷方
            table.Rows[0].Cells[15].ColSpan = 3;
            table.Rows[0].Cells[15].TextAlign = ContentAlignment.MiddleCenter;
            //// 数量
            table.Rows[1].Cells[15].RowSpan = 2;
            table.Rows[1].Cells[15].TextAlign = ContentAlignment.MiddleCenter;
            table.Rows[1].Cells[16].TextAlign = ContentAlignment.MiddleCenter;
            table.Rows[1].Cells[17].TextAlign = ContentAlignment.MiddleCenter;

            //////// 期末 借与贷
            ////table.Rows[0].Cells[24].RowSpan = 3;
            ////table.Rows[0].Cells[24].TextAlign = ContentAlignment.MiddleCenter;
            //// 累计借方
            table.Rows[0].Cells[18].ColSpan = 3;
            table.Rows[0].Cells[18].TextAlign = ContentAlignment.MiddleCenter;
            //// 数量
            table.Rows[1].Cells[18].RowSpan = 2;
            table.Rows[1].Cells[18].TextAlign = ContentAlignment.MiddleCenter;
            table.Rows[1].Cells[19].TextAlign = ContentAlignment.MiddleCenter;
            table.Rows[1].Cells[20].TextAlign = ContentAlignment.MiddleCenter;

            table.Rows[0].FullRowSelected = false;
            table.Rows[1].FullRowSelected = false;
            table.Rows[2].FullRowSelected = false;
            table.Rows[0].IsHeader = true;
            table.Rows[1].IsHeader = true;
            table.Rows[2].IsHeader = true;
            table.ShowColumnHeader = false;
            table.Rows[0].Height = 15;
            table.Rows[1].Height = 15;
            table.Rows[2].Height = 15;
            table.FixedTopRows = 3;
            table.FixedLeftCols = 3;

            table.Refresh();
        }

        /// <summary>
        /// 加载明细数据
        /// </summary>
        /// <param name="lErYebList">电子对账科目数据</param>
        /// <param name="table">table</param>
        protected void loadYeDetail(List<ErYeb> lErYebList, Table table)
        {
            foreach (ErYeb xErYeb in lErYebList)
            {
                loadRowBalData(xErYeb, table);
            }
        }

        /// <summary>
        /// 加载一行余额表数据到主区域
        /// </summary>
        /// <param name="xErYeb">xErYeb</param>
        /// <param name="table">table</param>
        private void loadRowBalData(ErYeb xErYeb, Table table)
        {
            Dictionary<string, Row> c_HtMain = null;

            if (null == table.Tag)
            {
                c_HtMain = new Dictionary<string, Row>();
            }
            else
            {
                c_HtMain = table.Tag as Dictionary<string, Row>;
            }

            Yss.KTable.Models.Row row = new Row();
            Yss.KTable.Models.Cell cell = null;

            ////if (erYeb.Facctcode == null || erYeb.Facctcode.Length == 0)
            ////{
            ////    return;
            ////}

            foreach (Yss.KTable.Models.Column col in table.Columns)
            {
                string c_Temps = "";

                if (col.DataPropertyName.Equals("ShowRowCheckBoxColumn") || col.DataPropertyName.Equals("ShowRowIndexColumn") || col.DataPropertyName.Equals(""))
                {
                    c_Temps = "";
                }
                else
                {
                    c_Temps = Convert.ToString(ReflectBase.getAttrValue(col.DataPropertyName, xErYeb));
                }

                if (col.Text.EndsWith("金额"))
                {
                    cell = new AmountCell();
                    (cell as AmountCell).Text = ClsFunction.formatNumber(ClsConstant.StringToNumber, c_Temps);
                }
                else if (col.Text.EndsWith("数量"))
                {
                    cell = new Cell(c_Temps);
                    cell.TextAlign = ContentAlignment.MiddleRight;
                }
                else
                {
                    cell = new Cell(c_Temps);
                }

                // 实现科目代码列自适应
                if (col.DataPropertyName.Equals("Facctcode") || col.DataPropertyName.Equals("Facctname"))
                {
                    int n_Width = (int)clsInterface.charWidth(cell.Text, tbMain.Font.Name, tbMain.Font.Size, this) + 2;
                    col.Width = n_Width >= col.Width ? n_Width : col.Width;
                }

                row.Cells.Add(cell);

            }

            row.Tag = xErYeb;

            ////if (!c_HtMain.ContainsKey(erYeb.Facctcode))
            ////{
            ////    c_HtMain.Add(erYeb.Facctcode, row);
            ////}

            table.Tag = c_HtMain;
        }

        /// <summary>
        ///  加载数据到KTable中
        /// </summary>
        /// <param name="table">table</param>
        protected void loadYeDataToKTable(Table table)
        {
            Dictionary<string, Yss.KTable.Models.Row> c_HtRows = null;

            if (null == table.Tag)
            {
                return;
            }

            c_HtRows = table.Tag as Dictionary<string, Yss.KTable.Models.Row>;

            ////foreach (KeyValuePair<string, Yss.KTable.Models.Row> ider in c_HtRows)
            ////{
            ////    // 在集合中判断父节点，如果查到则将本节点加到row的subrow中，查不到则把row添加到table中
            ////    Row tempRow = ider.Value as Row;
            ////    ErYeb erYeb = (ErYeb)tempRow.Tag;

                // 如果存在表的父级节点
            ////    if (c_HtRows.ContainsKey(erYeb.Facctparent))
            ////    {
            ////        c_HtRows[erYeb.Facctparent].SubRows.Add(tempRow);


            ////        // 如果子行行数等于0加载相关的图片
            ////        if (tempRow.SubRows.Count == 0)
            ////        {
            ////            // 定义，如果KTABLE类型为NoIndentWithIcon则不显示图片
            ////            if (table.PlusMinusStyle != Yss.KTable.Enums.PlusMinusStyles.IconIndent)
            ////            {
            ////                tempRow.SmallImage = Resource.tab_Node_Defore;
            ////            }
            ////        }

            ////        // 定义，如果KTABLE类型为NoIndentWithIcon则不显示图片
            ////        if (table.PlusMinusStyle != Yss.KTable.Enums.PlusMinusStyles.IconIndent)
            ////        {
            ////            Row parentRow = c_HtRows[erYeb.Facctparent] as Row;
            ////            parentRow.SmallImage = Resource.tab_Close;
            ////            parentRow.ExpandImage = Resource.tab_Open;
            ////        }
            ////    }
            ////    else
            ////    {
            ////        //// 对有非父级节点的处理
            ////        table.Rows.Add(tempRow);

            ////        ////setBoldRow(tempRow);

            ////        // 为父节点加上伸缩和展开时的两种图片
            ////        if (table.PlusMinusStyle != Yss.KTable.Enums.PlusMinusStyles.IconIndent)
            ////        {
            ////            tempRow.SmallImage = Resource.tab_Close;
            ////            tempRow.ExpandImage = Resource.tab_Open;
            ////        }
            ////    }
            ////}

            table.Refresh();
        }

        //// ----------------- 加载估值数据-----------------

        /// <summary>
        /// 加载电子对账估值表数据方法
        /// </summary>
        /// <param name="table">table</param>
        /// <param name="basepojo">电子对账估值数据对象列表</param>
        private void loadGzDataToTable(Table table, List<ErGzb> basepojo)
        {
            table.Clear();
            table.Refresh();

            // 初始化列头
            init_TbGzb_Columns(table);

            if (basepojo.Count > 0)
            {
                // 加载数据
                loadGzDetail(basepojo, table);

                loadGzDataToKTable(table);

            }
            
            table.Refresh();
        }

        /// <summary>
        /// 加载空行
        /// </summary>
        /// <param name="table">table</param>
        /// <returns>Row</returns>
        private Row init_Gzb_MidRow(Table table)
        {
            Row row = new Row();

            for (int i = 0; i < table.Columns.Count; i++)
            {
                Cell cell = new Cell();
                row.Cells.Add(cell);
            }

            return row;
        }

        /// <summary>
        /// 初始化主区域的KTable控件
        /// </summary>
        /// <param name="table">table</param>
        protected void init_TbGzb_Columns(Table table)
        {
            table.ShowColumnHeader = false;

            ArrayList c_Cols = new ArrayList();
            c_Cols.Add("科目编码,Fkmbm");
            c_Cols.Add("科目名称,Fkmmc");
            c_Cols.Add("数量,Fzqsl");
            c_Cols.Add("原币金额,");
            c_Cols.Add("本位币金额,Fzqcb");
            c_Cols.Add("成本占比,Fcb_jz_bl");
            c_Cols.Add("行情,Fhqjg");
            c_Cols.Add("原币金额,");
            c_Cols.Add("本位币金额,Fzqsz");
            c_Cols.Add("市值占比,Fsz_jz_bl");
            c_Cols.Add("原币金额,");
            c_Cols.Add("本位币金额,Fgz_zz");

            Yss.KTable.Models.Column col = null;

            Yss.KTable.Models.Row r1 = new Row();
            Yss.KTable.Models.Row r2 = new Row();
            Yss.KTable.Models.Row r3 = new Row();
            Yss.KTable.Models.Cell c1 = null;
            Yss.KTable.Models.Cell c2 = null;
            Yss.KTable.Models.Cell c3 = null;

            for (int i = 0; i < c_Cols.Count; i++)
            {
                string[] arrCol = Regex.Split(Convert.ToString(c_Cols[i]), ",");
                col = new Column(arrCol[0].Replace(".", ""), arrCol[1]);
                col.DataPropertyName = arrCol[1];
                col.Visible = true;

                if (i == 2 || i == 5 || i == 6 || i == 9)
                {
                    // 币种
                    col.Width = 100;
                }
                else
                {
                    // 金额线
                    col.Width = 120;
                }

                col.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Center;

                c1 = new Cell(arrCol[0].Replace(".", "\n"));
                c2 = new Cell(arrCol[0].Replace(".", "\n"));
                c3 = new Cell(arrCol[0].Replace(".", "\n"));

                if (i == 3 || i == 4)
                {
                    c1 = new Cell("成本");
                }
                else if (i == 7 || i == 8)
                {
                    c1 = new Cell("市值");
                }
                else if (i == 10 || i == 11)
                {
                    c1 = new Cell("估值增值");
                }

                if (i == 3 || i == 4 || i == 7 || i == 8 || i == 10 || i == 11)
                {
                    c3 = new AmountCell();
                }

                table.Columns.Add(col);
                r1.Cells.Add(c1);
                r2.Cells.Add(c2);
                r3.Cells.Add(c3);
            }

            table.Rows.Add(r1);
            table.Rows.Add(r2);
            table.Rows.Add(r3);

            //// 合并行、列
            //// 科目编码
            table.Rows[0].Cells[0].RowSpan = 3;
            table.Rows[0].Cells[0].TextAlign = ContentAlignment.MiddleCenter;
            //// 科目名称
            table.Rows[0].Cells[1].RowSpan = 3;
            table.Rows[0].Cells[1].TextAlign = ContentAlignment.MiddleCenter;
            //// 数量
            table.Rows[0].Cells[2].RowSpan = 3;
            table.Rows[0].Cells[2].TextAlign = ContentAlignment.MiddleCenter;

            //// 成本
            table.Rows[0].Cells[3].ColSpan = 2;
            table.Rows[0].Cells[3].TextAlign = ContentAlignment.MiddleCenter;
            table.Rows[1].Cells[3].TextAlign = ContentAlignment.MiddleCenter;
            table.Rows[1].Cells[4].TextAlign = ContentAlignment.MiddleCenter;

            //// 成本占比
            table.Rows[0].Cells[5].RowSpan = 3;
            table.Rows[0].Cells[5].TextAlign = ContentAlignment.MiddleCenter;
            //// 行情
            table.Rows[0].Cells[6].RowSpan = 3;
            table.Rows[0].Cells[6].TextAlign = ContentAlignment.MiddleCenter;

            //// 市值
            table.Rows[0].Cells[7].ColSpan = 2;
            table.Rows[0].Cells[7].TextAlign = ContentAlignment.MiddleCenter;
            table.Rows[1].Cells[7].TextAlign = ContentAlignment.MiddleCenter;
            table.Rows[1].Cells[8].TextAlign = ContentAlignment.MiddleCenter;

            //// 市值占比
            table.Rows[0].Cells[9].RowSpan = 3;
            table.Rows[0].Cells[9].TextAlign = ContentAlignment.MiddleCenter;

            //// 估值增值
            table.Rows[0].Cells[10].ColSpan = 2;
            table.Rows[0].Cells[10].TextAlign = ContentAlignment.MiddleCenter;
            table.Rows[1].Cells[10].TextAlign = ContentAlignment.MiddleCenter;
            table.Rows[1].Cells[11].TextAlign = ContentAlignment.MiddleCenter;

            table.Rows[0].FullRowSelected = false;
            table.Rows[1].FullRowSelected = false;
            table.Rows[2].FullRowSelected = false;
            table.Rows[0].IsHeader = true;
            table.Rows[1].IsHeader = true;
            table.Rows[2].IsHeader = true;
            table.ShowColumnHeader = false;
            table.Rows[0].Height = 15;
            table.Rows[1].Height = 15;
            table.Rows[2].Height = 15;
            table.FixedTopRows = 3;
            table.FixedLeftCols = 3;

            table.Refresh();
        }

        /// <summary>
        ///  加载数据到KTable中
        /// </summary>
        /// <param name="table">table</param>
        protected void loadGzDataToKTable(Table table)
        {
            Dictionary<string, Yss.KTable.Models.Row> c_HtRows = null;

            if (null == table.Tag)
            {
                return;
            }

            c_HtRows = table.Tag as Dictionary<string, Yss.KTable.Models.Row>;

            ////foreach (KeyValuePair<string, Yss.KTable.Models.Row> ider in c_HtRows)
            ////{
            ////    // 在集合中判断父节点，如果查到则将本节点加到row的subrow中，查不到则把row添加到table中
            ////    Row tempRow = ider.Value as Row;
            ////    ErGzb erGzb = (ErGzb)tempRow.Tag;

            ////    // 如果存在表的父级节点
            ////    if (c_HtRows.ContainsKey(erGzb.Facctparent))
            ////    {
            ////        c_HtRows[erGzb.Facctparent].SubRows.Add(tempRow);


            ////        // 如果子行行数等于0加载相关的图片
            ////        if (tempRow.SubRows.Count == 0)
            ////        {
            ////            // 定义，如果KTABLE类型为NoIndentWithIcon则不显示图片
            ////            if (table.PlusMinusStyle != Yss.KTable.Enums.PlusMinusStyles.IconIndent)
            ////            {
            ////                tempRow.SmallImage = Resource.tab_Node_Defore;
            ////            }
            ////        }

            ////        // 定义，如果KTABLE类型为NoIndentWithIcon则不显示图片
            ////        if (table.PlusMinusStyle != Yss.KTable.Enums.PlusMinusStyles.IconIndent)
            ////        {
            ////            Row parentRow = c_HtRows[erGzb.Facctparent] as Row;
            ////            parentRow.SmallImage = Resource.tab_Close;
            ////            parentRow.ExpandImage = Resource.tab_Open;
            ////        }
            ////    }
            ////    else
            ////    {
            ////        //// 对有非父级节点的处理
            ////        table.Rows.Add(tempRow);

            ////        ////setBoldRow(tempRow);

            ////        // 为父节点加上伸缩和展开时的两种图片
            ////        if (table.PlusMinusStyle != Yss.KTable.Enums.PlusMinusStyles.IconIndent)
            ////        {
            ////            tempRow.SmallImage = Resource.tab_Close;
            ////            tempRow.ExpandImage = Resource.tab_Open;
            ////        }
            ////    }
            ////}

            table.Refresh();
        }

        /// <summary>
        /// 加载明细数据
        /// </summary>
        /// <param name="lErGzbList">电子对账科目数据</param>
        /// <param name="table">table</param>
        protected void loadGzDetail(List<ErGzb> lErGzbList, Table table)
        {
            // 组装估值表数据
            assetData(lErGzbList);

            foreach (ErGzb xErGzb in gzbList)
            {
                loadRowGzData(xErGzb, table, true);
            }

            if (gzbTotalList.Count > 0)
            {
                if (null != table.Tag)
                {
                    Dictionary<string, Row> c_HtMain = table.Tag as Dictionary<string, Row>;
                    Row row = init_Gzb_MidRow(table);
                    row.Tag = new ErGzb();
                    c_HtMain.Add("TOTAL", row);
                }
                
                foreach (ErGzb xErGzb in gzbTotalList)
                {
                    loadRowGzData(xErGzb, table, false);
                }
            }

            if (gzbTotalAllList.Count > 0)
            {
                if (null != table.Tag)
                {
                    Dictionary<string, Row> c_HtMain = table.Tag as Dictionary<string, Row>;
                    Row row = init_Gzb_MidRow(table);
                    row.Tag = new ErGzb();
                    c_HtMain.Add("TOTAL_ALL", row);
                }

                foreach (ErGzb xerGzb in gzbTotalAllList)
                {
                    loadRowGzData(xerGzb, table, false);
                }
            }
        }

        /// <summary>
        /// 组装估值表数据
        /// </summary>
        /// <param name="lErGzbList">估值表对象列表</param>
        private void assetData(List<ErGzb> lErGzbList)
        {
            gzbList.Clear();
            gzbTotalList.Clear();
            gzbTotalAllList.Clear();

            foreach (ErGzb xerGzb in lErGzbList)
            {
                if (xerGzb.Navtype.Equals(" "))
                {
                    gzbList.Add(xerGzb);
                }
                else if (xerGzb.Navtype.Equals("TOTAL"))
                {
                    gzbTotalList.Add(xerGzb);
                }
                else if (xerGzb.Navtype.Equals("TOTAL_ALL"))
                {
                    gzbTotalAllList.Add(xerGzb);
                }
            }
        }

        /// <summary>
        /// 加载一行余额表数据到主区域
        /// </summary>
        /// <param name="xerGzb">单行的POJO类</param>
        /// <param name="table">table</param>
        /// <param name="bJex">是否加载金额线</param>
        private void loadRowGzData(ErGzb xerGzb, Table table, bool bJex)
        {
            Dictionary<string, Row> c_HtMain = null;

            if (null == table.Tag)
            {
                c_HtMain = new Dictionary<string, Row>();
            }
            else
            {
                c_HtMain = table.Tag as Dictionary<string, Row>;
            }

            Yss.KTable.Models.Row row = new Row();
            Yss.KTable.Models.Cell cell = null;

            if (xerGzb.C_KM_CODE == null || xerGzb.C_KM_CODE.Length == 0)
            {
                return;
            }

            foreach (Yss.KTable.Models.Column col in table.Columns)
            {
                string c_Temps = "";

                if (col.DataPropertyName.Equals("ShowRowCheckBoxColumn") || col.DataPropertyName.Equals("ShowRowIndexColumn") || col.DataPropertyName.Equals(""))
                {
                    c_Temps = "";
                }
                else if (!(col.DataPropertyName.Equals("Fkmbm") || col.DataPropertyName.Equals("Fkmmc")) && xerGzb.Navtype.Equals("TOTAL_ALL"))
                {
                    c_Temps = "";
                }
                else
                {
                    c_Temps = Convert.ToString(ReflectBase.getAttrValue(col.DataPropertyName, xerGzb));
                }

                if (col.Text.EndsWith("金额") && bJex == true)
                {
                    cell = new AmountCell();
                    (cell as AmountCell).Text = ClsFunction.formatNumber(ClsConstant.StringToNumber, c_Temps);
                }
                else if (col.Text.EndsWith("金额") && bJex == false)
                {
                    cell = new Cell(c_Temps);
                    cell.TextAlign = ContentAlignment.MiddleRight;
                }
                else if (col.DataPropertyName.Equals("Fzqsl") || col.DataPropertyName.Equals("Fcb_jz_bl") || col.DataPropertyName.Equals("Fhqjg") || col.DataPropertyName.Equals("Fsz_jz_bl") || (xerGzb.Navtype.Equals("TOTAL_ALL") && col.DataPropertyName.EndsWith("Fkmmc")))
                {
                    cell = new Cell(c_Temps);
                    cell.TextAlign = ContentAlignment.MiddleRight;
                }
                else
                {
                    cell = new Cell(c_Temps);
                }

                // 实现科目代码列自适应
                if (col.DataPropertyName.Equals("Fkmbm") || col.DataPropertyName.Equals("Fkmmc"))
                {
                    int n_Width = (int)clsInterface.charWidth(cell.Text, tbMain.Font.Name, tbMain.Font.Size, this) + 2;
                    col.Width = n_Width >= col.Width ? n_Width : col.Width;
                }

                row.Cells.Add(cell);

            }

            row.Tag = xerGzb;

            if (!c_HtMain.ContainsKey(xerGzb.C_KM_CODE))
            {
                c_HtMain.Add(xerGzb.C_KM_CODE, row);
            }

            table.Tag = c_HtMain;
        }
    }
}
