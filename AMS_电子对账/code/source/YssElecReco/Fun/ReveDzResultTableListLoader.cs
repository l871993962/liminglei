using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using Yss.KTable.Models;
using FAST.Common.Service.Pojo.Base;
using Yss.KTable.Enums;
using YssElecReco.Pojo.Er.Reverse;
using System.Drawing;
using FAST.Core.Context;

namespace YssElecReco.Fun
{
    class ReveDzResultTableListLoader : TableListLoader
    {
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
                //cell0.RowSpan = 2;
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
                //cell0.RowSpan = 2;
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
                    
                    if (colFlag == null || !colFlag.Equals(temp))//非一个父单元格
                    {
                        colFlag = temp;
                        merCell = cell0;
                    }else
                    { //合并
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
                    //cell0.RowSpan = 2;
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

        /// <summary>
        /// 根据Pojo创建行
        /// </summary>
        /// <param name="pojo">BasePojo</param>
        /// <param name="headKeys">表头信息</param>
        /// <param name="showConvAssemble">翻译信息</param>
        /// <returns>返回创建好的行</returns>
        public override Row CreateRow(BasePojo pojo, List<ListHeadInfo> headKeys, Dictionary<string, Dictionary<string, string>> showConvAssemble)
        {
            object cellValueObj = null;
            string cellValue = "";
            string keyConvServiceId = "";
            int auditState = 0;
            Dictionary<string, string> showConvDict;

            Row row = new Row();
            row.Tag = pojo;
            if (showCheckColumn)
            {
                row.Cells.Add(new Cell());
            }

            if (showMarkColumn)
            {
                row.Cells.Add(new Cell());
            }

            string headKeyCode;
            string formatStr;
            foreach (ListHeadInfo headKey in headKeys)
            {
                object cellIgnoreFlag = null;
                headKeyCode = headKey.Key;
                formatStr = headKey.Format;
                if (headKeyCode.Equals("N_CHECK_STATE"))
                {
                    headKeyCode = "AuditState";
                }
                else if (headKeyCode.Equals("C_CHECK_TIME"))
                {
                    headKeyCode = "AuditDate";
                }
                else if (headKeyCode.Equals("C_CHECK_BY"))
                {
                    headKeyCode = "OperUser";
                }
                else if (headKeyCode.Equals("C_UPDATE_TIME"))
                {
                    headKeyCode = "ModifyDate";
                }
                else if (headKeyCode.Equals("C_UPDATE_BY"))
                {
                    headKeyCode = "Modifier";
                }

                if (headKeyCode.EndsWith(ReveElecDVCons.KM_SUFFIX_OUT))
                {
                    headKeyCode = removeLastStr(headKeyCode, ReveElecDVCons.KM_SUFFIX_OUT);
                    cellValueObj = (pojo as ReveResult).LIST_RESRELA_OUT;
                }
                else if (headKeyCode.EndsWith(ReveElecDVCons.KM_SUFFIX_INNER))
                {
                    headKeyCode = removeLastStr(headKeyCode, ReveElecDVCons.KM_SUFFIX_INNER);
                    cellValueObj = (pojo as ReveResult).LIST_RESRELA_INNER;
                }
                else
                {
                    cellValueObj = ReflectBase.getAttrValue(headKeyCode, pojo, false);
                }

                if (cellValueObj == null)
                {
                    cellValue = "";
                }
                else if (cellValueObj is List<ResRela>)
                {
                    cellValue = "";
                    List<ResRela> list = cellValueObj as List<ResRela>;
                    foreach (ResRela km in list)
                    {
                        string singleCell = "";
                        cellIgnoreFlag = ReflectBase.getAttrValue("C_IGNORE_FLAG", km, false);
                        object temp = ReflectBase.getAttrValue(headKeyCode, km, false);
                        if (temp != null)
                        {
                            singleCell = temp.ToString();
                        }
                        if (cellIgnoreFlag != null && cellIgnoreFlag.ToString().ToUpper().Contains(headKeyCode.ToUpper()))
                        {
                            singleCell = "忽略[" + singleCell + "]";
                        }
                        cellValue += singleCell;
                        cellValue += ReveElecDVCons.LINE_SEPARATOR;
                    }
                    cellValue = removeLastStr(cellValue, ReveElecDVCons.LINE_SEPARATOR);
                }
                else
                {
                    if (cellValueObj is DateTime)
                    {
                        DateTime tempDate = Convert.ToDateTime(cellValueObj);
                        if (tempDate.Date == new DateTime(1, 1, 1))
                        {
                            ////日期部分为1-1-1时，说明后台丢过来的是空日期，要做特殊处理-张绍林-20170104
                            ////BUG #149120 【集成测试】【数据清算管理】日期字段为空时前台显示为0001-01-01
                            cellValue = "";
                        }
                        else
                        {
                            cellValue = tempDate.ToString(formatStr);
                        }
                    }
                    else if (cellValueObj is List<string>)
                    {
                        ////BUG #159415 发件策略中选择组合保存之后，查询显示时，组合变成了代码 雷建华 20170512
                        ////将List<string>类型的数据用"|"分隔
                        List<string> valueList = cellValueObj as List<string>;
                        if (valueList != null && valueList.Count > 0)
                        {
                            cellValue = string.Join("|", valueList.ToArray());
                        }
                    }
                    else
                    {
                        cellValue = cellValueObj.ToString();
                        //// 因ListHeader.xml中只有数值与日期才格式化，有格式化的都认为数值型
                        if (formatStr != null && formatStr.Trim().Length > 0)
                        {
                            cellValue = ClsFunction.formatNumber(formatStr, cellValue);
                        }
                    }

                    ////cellValue = cellValueObj.ToString();
                }//属性过滤

                if (showConvAssemble != null && !"false".Equals(headKey.ShowConvert))
                {
                    keyConvServiceId = headKey.ServiceId;

                    if (keyConvServiceId == null)
                    {
                        keyConvServiceId = "";
                    }

                    if (showConvAssemble.ContainsKey(keyConvServiceId))
                    {
                        showConvDict = showConvAssemble[keyConvServiceId];
                        if (showConvDict != null)
                        {
                            if (showConvDict.ContainsKey(cellValue))
                            {
                                cellValue = showConvDict[cellValue];
                            }
                            else if (cellValue != null && cellValue.Contains("|"))
                            {
                                cellValue = TranslateMultiLineValues(cellValue, showConvDict, "|");
                            }
                            else if (cellValue != null && cellValue.Contains(ReveElecDVCons.LINE_SEPARATOR))
                            {
                                cellValue = this.TranslateMultiLineValues(cellValue, showConvDict, ReveElecDVCons.LINE_SEPARATOR);
                            }
                        }
                    }


                }

                if ("null".Equals(cellValue))
                {
                    cellValue = "";
                }
                Cell cell = new Cell(cellValue);
                row.Cells.Add(cell);
            }//结束for
            setRowBackColorByDzResult(pojo, row);
            //if (pojo is AuditableParamPojo)
            //{
            //    object auditValueObj = ReflectBase.getAttrValue("AuditState", pojo, false);

            //    if (auditValueObj == null)
            //    {
            //        auditState = 0;
            //    }
            //    else
            //    {
            //        auditState = int.Parse(auditValueObj.ToString());
            //    }

            //    setRowBackColor(auditState, row); // 设置行颜色只按有审核状态的来设置
            //}

            ////插入附件单元格
            this.AddEnclosureButtonCell(row, pojo);
            //避免对多个科目造成影响，此处禁止换行
            //多对多科目换行展示
            row.WordWrap = false;
            return row;
        }

        /// <summary>
        /// 设置数据行的背景色,根据对账结果
        /// </summary>
        /// <param name="checkState">当前记录的审核状态</param>
        /// <param name="curRow">当前行对象</param>
        protected void setRowBackColorByDzResult(BasePojo pojo, Row curRow)
        {
            object valueObj = ReflectBase.getAttrValue("C_DV_DZ_RESULT", pojo, false);
            if (valueObj != null && ReveElecDVCons.RDZ_RESULT_DIFF.Equals(valueObj.ToString()))
            {
                curRow.ForeColor = Color.Red;
            }
        }

        /// <summary>
        /// 翻译多值数据
        /// </summary>
        /// <param name="cellValue">待翻译的值</param>
        /// <param name="showConvDict">词典</param>
        /// <returns>返回翻译好的值</returns>
        private string TranslateMultiLineValues(string cellValue, Dictionary<string, string> showConvDict, string str)
        {
            ////支持翻译多值的下拉框数据。张绍林-20161111
            string tempCellValue = "";
            string[] keyArray = cellValue.Split(new string[] { str }, StringSplitOptions.RemoveEmptyEntries);
            if (keyArray != null)
            {
                foreach (string key in keyArray)
                {
                    if (showConvDict.ContainsKey(key))
                    {
                        tempCellValue += (showConvDict[key] + str);
                    }
                }

                tempCellValue = removeLastStr(tempCellValue, str);
            }

            if (tempCellValue != "")
            {
                cellValue = tempCellValue;
            }

            return cellValue;
        }

        /// <summary>
        /// 移除最后出现的字符
        /// </summary>
        /// <param name="sb"></param>
        /// <returns></returns>
        private string removeLastStr(string s, string str)
        {
            if (s.EndsWith(str))
            {
                s = s.Remove(s.LastIndexOf(str));
            }
            return s;
        }

        /// <summary>
        /// 设置表格行数据(树形)
        /// </summary>
        /// <param name="tbMain">表格</param>
        /// <param name="headKeys">列头信息</param>
        /// <param name="dataList">数据信息</param>
        /// <param name="showConvAssemble">翻译信息</param>
        /// <param name="formMode">数据展示模式</param>
        protected override void setTableListData(Table tbMain, List<ListHeadInfo> headKeys, List<BasePojo> dataList, Dictionary<string, Dictionary<string, string>> showConvAssemble, ClsEnums.KTableDataShowMode formMode)
        {
            if (this.AutoLoadEnclosure)
            {
                this.LoadDataEnclosure(dataList);
            }
            List<Row> noBfCode = new List<Row>();
            List<Row> zbRow = new List<Row>();
            Dictionary<string, Row> htView = new Dictionary<string, Row>();
            List<string> sort = new List<string>();
            foreach (BasePojo pojo in dataList)
            {
                Row row = this.CreateRow(pojo, headKeys, showConvAssemble);
                if (formMode == ClsEnums.KTableDataShowMode.TreeMode)
                {
                    setHtViewExtendsPojo(noBfCode, htView, pojo, row, sort);
                }
            }

            if (formMode == ClsEnums.KTableDataShowMode.TreeMode)
            {
                zbRow = loadTreeViewExtendsPojo(tbMain, htView, sort);
            }
            tbMain.Rows.AddRange(zbRow.ToArray());
            tbMain.Rows.AddRange(noBfCode.ToArray());
            this.UpdateRowImage(tbMain);
            //// 将视图放入到tabMain.Tag中，便于其他地方使用byleeyu20121201
            tbMain.Tag = dataList;
        }

        private string getBfKmCode(List<ResRela> relas)
        {
            StringBuilder sb = new StringBuilder();
            if (relas == null || relas.Count == 0)
            {
                return null;
            }
            foreach (ResRela rela in relas)
            {
                sb.Append(rela.C_KM_CODE);
                sb.Append(",");
            }
            return removeLastStr(sb.ToString(), ",");
        }

        /// <summary>
        /// 1
        /// </summary>
        /// <param name="htView">2</param>
        /// <param name="basePojo">3</param>
        /// <param name="row">3</param>
        protected void setHtViewExtendsPojo(List<Row> noBfCode, Dictionary<string, Row> htView, BasePojo basePojo, Row row, List<string> sort)
        {
            string nodeCode = getBfKmCode((basePojo as ReveResult).LIST_RESRELA_INNER);
            if(nodeCode == null || nodeCode.Trim().Length == 0)
            {
                noBfCode.Add(row);
                return;
            }
            //// 根据节点代码判断是否有重复数据
            if (!htView.ContainsKey(nodeCode))
            {
                htView.Add(nodeCode, row);
                sort.Add(nodeCode);
            }
        }

        /// <summary>
        /// 1
        /// </summary>
        /// <param name="tbMain">3</param>
        /// <param name="htView">2</param>
        protected List<Row> loadTreeViewExtendsPojo(Table tbMain, Dictionary<string, Row> htView, List<string> sort)
        {
            List<Row> zbRows = new List<Row>();
            ////string nodeCode = "";
            string parentCode = "";

            this.DictionarySort(htView);
            foreach (string nodeCode in sort)
            ////foreach (KeyValuePair<string, Row> ider in htView)
            {
                
                // 在集合中判断父节点，如果查到则将本节点加到row的subrow中，查不到则把row添加到table中
                //Row tempRow = ider.Value as Row;
                Row tempRow = htView[nodeCode];
                BasePojo pojo = (BasePojo)tempRow.Tag;

                if (nodeCode.StartsWith("0"))
                {
                    zbRows.Add(tempRow);
                }
                else
                {
                    //nodeCode = ider.Key;
                    if (nodeCode.Contains("."))
                    {
                        parentCode = nodeCode.Substring(0, nodeCode.LastIndexOf("."));
                    }
                    else
                    {
                        parentCode = null;
                    }

                    // 如果存在表的父级节点，且父节点不是本身
                    if (parentCode != null && htView.ContainsKey(parentCode) && nodeCode != parentCode)
                    {
                        htView[parentCode].SubRows.Add(tempRow);
                    }
                    else
                    {
                        ////ReflectBase.setAttrValue("N_Level", pojo, 1); // 一级父节点默认级别为1 by leeyu 20111102
                        //// 对有非父级节点的处理
                        tbMain.Rows.Add(tempRow);
                    }
                }
               
                
            }
            return zbRows;
        }
        
    }
}
