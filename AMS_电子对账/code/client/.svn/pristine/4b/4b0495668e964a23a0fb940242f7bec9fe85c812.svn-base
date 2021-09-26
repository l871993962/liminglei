using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Yss.KTable.Models;
using YssElecReco.Pojo.Er;
using Yss.KTable.Enums;
using System.Collections;
using System.Drawing;
using FAST.Core.Util;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Pojo;
using FAST.Core.Exceptions;
using YssElecReco.Service.Er;
using FAST.Core.Communication.Service;
using YssElecReco.Form.Er;

namespace YssElecReco.Fun
{
    /// <summary>
    /// 自定义加载器 只复写了list形式的加载
    /// </summary>
    public class YebHdTableListLoader : TableListLoader
    {
        /// <summary>
        /// 1
        /// </summary>
        /// <param name="tbMain">2</param>
        /// <param name="res">3</param>
        /// <param name="showChkColumn">4</param>
        /// <param name="showMkColumn">5</param>
        public new void loadListTable(Table tbMain, QueryRes res, bool showChkColumn, bool showMkColumn)
        {
            try
            {
                showCheckColumn = showChkColumn;
                showMarkColumn = showMkColumn;

                tbMain.Clear(); // 清除table的所有数据和信息
                tbMain.FixedLeftCols = 0;   // 设定左侧固定列数为0

                setCheckColumn(tbMain);
                setMarkColumn(tbMain);

                setTableListHead(tbMain, res.ListHeadList);
                setTableListData(tbMain, res.ListHeadList, res.DataList, res.ShowConvertAssemble);

                tbMain.AutoWidth();
                tbMain.Refresh();
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message, ex.InnerException);
            }
        }

        ///
        /// <summary>
        /// 有意隐藏；
        /// 手动设置列头
        /// </summary>
        /// <param name="tbMain">2</param>
        /// <param name="headKeys">3</param>
        protected new void setTableListHead(Table tbMain, List<ListHeadInfo> headKeys)
        {
            ////修改列头
            updateColumnHeader(tbMain);
            ////int colCount = 0;
            ////foreach (ListHeadInfo headKey in headKeys)
            ////{
            ////    Column col = new Column();
            ////    col.Text = headKey.Text;
            ////    col.DataPropertyName = headKey.Key;
            ////    ////临时解决办法，将审核状态列强制指定为string类型。后期要调整Pojo的列名，或者是使用列词典作翻译。
            ////    if (col.DataPropertyName == "N_CHECK_STATE")
            ////    {
            ////        col.DataType = ColumnDataType.String;
            ////    }

            ////    col.ColumnTextAlign = Yss.KTable.Enums.TextAlign.Center;
            ////    tbMain.Columns.Add(col);

            ////    if (headKey.Align == null)
            ////    {
            ////        headKey.Align = "L";
            ////    }

            ////    setCellTextAlign(col, headKey.Align.Trim());

            ////    colCount++;
            ////}

        }

        /// <summary>
        /// 1
        /// </summary>
        /// <param name="tbMain">2</param>
        /// <param name="headKeys">3</param>
        /// <param name="dataList">4</param>
        /// <param name="showConvAssemble">5</param>
        protected override void setTableListData(Table tbMain, List<ListHeadInfo> headKeys, List<BasePojo> dataList, Dictionary<string, Dictionary<string, string>> showConvAssemble)
        {
            Dictionary<string, string> showConvDict;
            object cellValueObj = null;
            string cellValue = "";
            int auditState = 0;
            string keyConvServiceId = "";
            //// edit by qiantao STORY #83025 产品估值参数控制实收资本小数位 
            IErGzbService erGzService = ServiceFactory.createService<IErGzbService>();
            string c_port_code = ((Frm_ELEC_YE_S)(tbMain.ParentForm)).C_PORT_CODE;
            Dictionary<string, string> formatData_STARTBAL = new Dictionary<string, string>();
            Dictionary<string, string> formatData_DEBIT = new Dictionary<string, string>();
            Dictionary<string, string> formatData_CREDIT = new Dictionary<string, string>();
            Dictionary<string, string> formatData_ENDBAL = new Dictionary<string, string>();
            Dictionary<string, string> resultData_STARTBAL = new Dictionary<string, string>();
            Dictionary<string, string> resultData_DEBIT = new Dictionary<string, string>();
            Dictionary<string, string> resultData_CREDIT = new Dictionary<string, string>();
            Dictionary<string, string> resultData_ENDBAL = new Dictionary<string, string>();

            foreach (BasePojo pojo in dataList)
            {
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
                //// edit by qiantao STORY #83025 产品估值参数控制实收资本小数位 
                string kmCode = ReflectBase.getAttrValue("C_KM_CODE", pojo) as string;
                object n_AMOUNT_STARTBAL = ReflectBase.getAttrValue("N_AMOUNT_STARTBAL", pojo);
                object n_AMOUNT_DEBIT = ReflectBase.getAttrValue("N_AMOUNT_DEBIT", pojo);
                object n_AMOUNT_CREDIT = ReflectBase.getAttrValue("N_AMOUNT_CREDIT", pojo);
                object n_AMOUNT_ENDBAL = ReflectBase.getAttrValue("N_AMOUNT_ENDBAL", pojo);

                if (!string.IsNullOrEmpty(kmCode) && kmCode.StartsWith("4001"))
                {
                    string dataId = pojo.Id + "-" + c_port_code;
                    formatData_STARTBAL.Add(dataId, null != n_AMOUNT_STARTBAL ? n_AMOUNT_STARTBAL.ToString() : "0.00");
                    formatData_DEBIT.Add(dataId, null != n_AMOUNT_DEBIT ? n_AMOUNT_DEBIT.ToString() : "0.00");
                    formatData_CREDIT.Add(dataId, null != n_AMOUNT_CREDIT ? n_AMOUNT_CREDIT.ToString() : "0.00");
                    formatData_ENDBAL.Add(dataId, null != n_AMOUNT_ENDBAL ? n_AMOUNT_ENDBAL.ToString() : "0.00");
                }

                foreach (ListHeadInfo headKey in headKeys)
                {
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
                    else if (hiddenColumn(headKey))
                    {
                        continue;
                    }


                    cellValueObj = ReflectBase.getAttrValue(headKeyCode, pojo);

                    if (cellValueObj == null)
                    {
                        cellValue = "";
                    }
                    else
                    {
                        if (cellValueObj is DateTime)
                        {
                            cellValue = ((DateTime)cellValueObj).ToString(formatStr);
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
                    }

                    if (!"false".Equals(headKey.ShowConvert))
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
                            }
                        }


                    }

                    if ("null".Equals(cellValue))
                    {
                        cellValue = "";
                    }

                    row.Cells.Add(new Cell(cellValue));
                }

                if (pojo is AuditableParamPojo)
                {
                    object auditValueObj = ReflectBase.getAttrValue("AuditState", pojo);

                    if (auditValueObj == null)
                    {
                        auditState = 0;
                    }
                    else
                    {
                        auditState = int.Parse(auditValueObj.ToString());
                    }

                    setRowBackColor(auditState, row); //// 设置行颜色只按有审核状态的来设置
                }

                /////可以复写该方法自定义改变行的字体颜色
                updateRowColor(pojo, row);

                row.Tag = pojo;
                tbMain.Rows.Add(row);
            }

            //// edit by qiantao STORY #83025 产品估值参数控制实收资本小数位 
            resultData_STARTBAL = erGzService.formatedData(c_port_code, formatData_STARTBAL);
            resultData_DEBIT = erGzService.formatedData(c_port_code, formatData_DEBIT);
            resultData_CREDIT = erGzService.formatedData(c_port_code, formatData_CREDIT);
            resultData_ENDBAL = erGzService.formatedData(c_port_code, formatData_ENDBAL);

            foreach (Yss.KTable.Models.Row row in tbMain.Rows)
            {
                ErYeb erYeb = (ErYeb)row.Tag;
                if (null != erYeb && erYeb.C_KM_CODE.StartsWith("4001"))
                {
                    foreach (Cell cell in row.Cells)
                    {
                        if (cell.RelColumn.DataPropertyName.Equals("N_AMOUNT_STARTBAL") && null != resultData_STARTBAL)
                        {
                            cell.Text = ClsFunction.toNumber(resultData_STARTBAL.FirstOrDefault(q => q.Key.Equals(erYeb.Id)).Value);
                        }
                        if (cell.RelColumn.DataPropertyName.Equals("N_AMOUNT_DEBIT") && null != resultData_DEBIT)
                        {
                            cell.Text = ClsFunction.toNumber(resultData_DEBIT.FirstOrDefault(q => q.Key.Equals(erYeb.Id)).Value);
                        }
                        if (cell.RelColumn.DataPropertyName.Equals("N_AMOUNT_CREDIT") && null != resultData_CREDIT)
                        {
                            cell.Text = ClsFunction.toNumber(resultData_CREDIT.FirstOrDefault(q => q.Key.Equals(erYeb.Id)).Value);
                        }
                        if (cell.RelColumn.DataPropertyName.Equals("N_AMOUNT_ENDBAL") && null != resultData_ENDBAL)
                        {
                            cell.Text = ClsFunction.toNumber(resultData_ENDBAL.FirstOrDefault(q => q.Key.Equals(erYeb.Id)).Value);
                        }

                    }
                }
            }
        }

        /// <summary>
        /// 修改行的颜色
        /// </summary>
        /// <param name="pojo">pojo</param>
        /// <param name="row">row</param>
        protected virtual void updateRowColor(BasePojo pojo, Yss.KTable.Models.Row row)
        {
            ////CwHd erYehd = pojo as CwHd;
            ////if (erYehd.N_AMOUNT_DIFF != 0 || erYehd.N_PORT_MONEY_DIFF != 0 || erYehd.N_ORIG_MONEY_DIFF != 0 || erYehd.C_KM_CODE.Trim().Length == 0 || erYehd.C_KM_CODE2.Trim().Length == 0)
            ////{
            ////    setRowBackColor(0, row);
            ////}
        }

        /// <summary>
        /// 修改行的颜色
        /// </summary>
        /// <param name="headKey">1</param>
        /// <returns>bool</returns>
        protected virtual bool hiddenColumn(ListHeadInfo headKey)
        {
            if (headKey.Key.StartsWith("N_AMOUNT"))
            {
                return true;
            }

            return false;
        }

        /// <summary>
        /// 修改列头
        /// </summary>
        /// <param name="tbMain">tbMain</param>
        protected virtual void updateColumnHeader(Table tbMain)
        {
            Cell cell = null;
            ////tbMain.Tag = new Dictionary<string, Yss.KTable.Models.Row>();ContentAlignment.MiddleCenter;
            Row row = new Row();
            cell = new Cell("投资组合");
            cell.TextAlign = ContentAlignment.MiddleCenter;
            row.Cells.Add(cell);

            cell = new Cell("科目代码");
            cell.TextAlign = ContentAlignment.MiddleCenter;
            row.Cells.Add(cell);
            cell = new Cell("科目名称");
            cell.TextAlign = ContentAlignment.MiddleCenter;
            row.Cells.Add(cell);
            ////cell = new Cell("币种");
            ////cell.TextAlign = ContentAlignment.MiddleCenter;
            ////row.Cells.Add(cell);
            ////cell = new Cell("数量");
            ////cell.TextAlign = ContentAlignment.MiddleCenter;
            ////row.Cells.Add(cell);
            ////row.Cells.Add(new Cell());
            ////row.Cells.Add(new Cell());
            cell = new Cell("原始金额");
            cell.TextAlign = ContentAlignment.MiddleCenter;
            row.Cells.Add(cell);
            row.Cells.Add(new Cell());
            row.Cells.Add(new Cell());
            cell = new Cell("本币金额");
            cell.TextAlign = ContentAlignment.MiddleCenter;
            row.Cells.Add(cell);
            row.Cells.Add(new Cell());
            row.Cells.Add(new Cell());

            row.IsHeader = true;
            row.FullRowSelected = false;
            row.Height = 18;
            tbMain.Rows.Add(row);


            row = new Row();
            row.Cells.Add(new Cell());
            row.Cells.Add(new Cell());
            row.Cells.Add(new Cell());
            ////row.Cells.Add(new Cell());
            cell = new Cell("v4.5");
            cell.TextAlign = ContentAlignment.MiddleCenter;
            row.Cells.Add(cell);
            cell = new Cell("v2.5");
            cell.TextAlign = ContentAlignment.MiddleCenter;
            row.Cells.Add(cell);
            cell = new Cell("差额");
            cell.TextAlign = ContentAlignment.MiddleCenter;
            row.Cells.Add(cell);
            cell = new Cell("v4.5");
            cell.TextAlign = ContentAlignment.MiddleCenter;
            row.Cells.Add(cell);
            cell = new Cell("v2.5");
            cell.TextAlign = ContentAlignment.MiddleCenter;
            row.Cells.Add(cell);
            cell = new Cell("差额");
            cell.TextAlign = ContentAlignment.MiddleCenter;
            row.Cells.Add(cell);
            ////cell = new Cell("v4.5");
            ////cell.TextAlign = ContentAlignment.MiddleCenter;
            ////row.Cells.Add(cell);
            ////cell = new Cell("v2.5");
            ////cell.TextAlign = ContentAlignment.MiddleCenter;
            ////row.Cells.Add(cell);
            ////cell = new Cell("差额");
            ////cell.TextAlign = ContentAlignment.MiddleCenter;
            ////row.Cells.Add(cell);
            row.IsHeader = true;
            row.FullRowSelected = false;
            row.Height = 18;
            tbMain.Rows.Add(row);


            tbMain.Rows[0].Cells[0].RowSpan = 2;
            tbMain.Rows[0].Cells[1].RowSpan = 2;
            tbMain.Rows[0].Cells[2].RowSpan = 2;
            tbMain.Rows[0].Cells[3].ColSpan = 3;
            ////tbMain.Rows[0].Cells[3].RelColumn.Visible = false;
            tbMain.Rows[0].Cells[6].ColSpan = 3;
            ////tbMain.Rows[0].Cells[9].ColSpan = 3;

            tbMain.ShowColumnHeader = false;
        }

    }
}
