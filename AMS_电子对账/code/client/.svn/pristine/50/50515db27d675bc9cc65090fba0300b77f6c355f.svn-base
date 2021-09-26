using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Core.Util;
using YssElecReco.Pojo.Er.Reverse;
using YssElecReco.Pojo.Er;
using Yss.KTable.Models;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;
using FAST.Core.Context;
using System.Collections;
using System.Text.RegularExpressions;
using System.Drawing;

namespace YssElecReco.Fun
{
    public class ReveDzTableListLoader : TableListLoader
    {
        /// <summary>
        /// 供科目映射设置界面调用，加载表格数据并且过滤掉已经添加过映射关系的科目
        /// </summary>
        /// <param name="tbMain"></param>
        /// <param name="res"></param>
        /// <param name="showChkColumn"></param>
        /// <param name="showMkColumn"></param>
        /// <param name="km">不需要加载的科目</param>
        public void loadListTable(Table tbMain, QueryRes res, bool showChkColumn, bool showMkColumn, Dictionary<string, KmMap> km, string kmScope)
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
                //setTableListData(tbMain, res.ListHeadList, res.DataList, res.ShowConvertAssemble);
                if (ReveElecDVCons.REVE_KMFW_INNER.Equals(kmScope))
                {
                    setInnerKmTableListData(tbMain, res.ListHeadList, res.DataList, res.ShowConvertAssemble, km);
                }
                else if (ReveElecDVCons.REVE_KMFW_OUT.Equals(kmScope))
                {
                    setOutKmTableListData(tbMain, res.ListHeadList, res.DataList, res.ShowConvertAssemble, km);
                }
                else
                {
                    base.setTableListData(tbMain, res.ListHeadList, res.DataList, res.ShowConvertAssemble);
                }
                tbMain.AutoWidth();
                tbMain.Refresh();
            }
            catch (System.Exception ex)
            {
                ////异常注释完善。张绍林-20151027
                throw new ClsBaseException("表格数据装载错误：" + ex.Message, ex.InnerException);
            }

        }

        /// <summary>
        /// 设置表格行数据
        /// </summary>
        /// <param name="tbMain">表格</param>
        /// <param name="headKeys">列头信息</param>
        /// <param name="dataList">数据信息</param>
        /// <param name="showConvAssemble">翻译信息</param>
        protected void setInnerKmTableListData(Table tbMain, List<ListHeadInfo> headKeys, List<BasePojo> dataList, Dictionary<string, Dictionary<string, string>> showConvAssemble, Dictionary<string,KmMap> kms)
        {
            if (this.AutoLoadEnclosure)
            {
                this.LoadDataEnclosure(dataList);
            }
            if (kms == null)
            {
                kms = new Dictionary<string, KmMap>();
            }
            ////STORY #34372 优化系统数据维护后的查询。张绍林-20160926
            foreach (BasePojo pojo in dataList)
            {
                Row row = this.CreateRow(pojo, headKeys, showConvAssemble);
                if(!kms.ContainsKey((pojo as ErKmb).C_KM_CODE))
                {
                    tbMain.Rows.Add(row);
                }
                
            }
        }

        /// <summary>
        /// 设置表格行数据
        /// </summary>
        /// <param name="tbMain"></param>
        /// <param name="headKeys"></param>
        /// <param name="dataList"></param>
        /// <param name="showConvAssemble"></param>
        /// <param name="kms">已添加到映射表中的科目</param>
        protected void setOutKmTableListData(Table tbMain, List<ListHeadInfo> headKeys, List<BasePojo> dataList, Dictionary<string, Dictionary<string, string>> showConvAssemble, Dictionary<string, KmMap> kms)
        {
            if (this.AutoLoadEnclosure)
            {
                this.LoadDataEnclosure(dataList);
            }
            if(kms == null)
            {
                kms = new Dictionary<string, KmMap>();
            }

            ////STORY #34372 优化系统数据维护后的查询。张绍林-20160926
            foreach (BasePojo pojo in dataList)
            {
                Row row = this.CreateRow(pojo, headKeys, showConvAssemble);
                if (!kms.ContainsKey((pojo as ErKmbOut).C_KM_CODE))
                {
                    tbMain.Rows.Add(row);
                }

            }
        }

        /// <summary>
        /// 设置表格行数据,重写父类，供公共科目映射，托管行科目映射，产品科目映射的List界面使用
        /// </summary>
        /// <param name="tbMain">表格</param>
        /// <param name="headKeys">列头信息</param>
        /// <param name="dataList">数据信息</param>
        /// <param name="showConvAssemble">翻译信息</param>
        protected override void setTableListData(Table tbMain, List<ListHeadInfo> headKeys, List<BasePojo> dataList, Dictionary<string, Dictionary<string, string>> showConvAssemble)
        {
            if (this.AutoLoadEnclosure)
            {
                this.LoadDataEnclosure(dataList);
            }
            ////STORY #34372 优化系统数据维护后的查询。张绍林-20160926
            foreach (BasePojo pojo in dataList)
            {
                Row row = this.CreateRowContainsMergerCells(pojo, headKeys, showConvAssemble);
                row.WordWrap = false;
                tbMain.Rows.Add(row);
            }
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
        /// 根据Pojo创建行
        /// </summary>
        /// <param name="pojo">BasePojo</param>
        /// <param name="headKeys">表头信息</param>
        /// <param name="showConvAssemble">翻译信息</param>
        /// <returns>返回创建好的行</returns>
        public Row CreateRowContainsMergerCells(BasePojo pojo, List<ListHeadInfo> headKeys, Dictionary<string, Dictionary<string, string>> showConvAssemble)
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

                if(headKeyCode.EndsWith(ReveElecDVCons.KM_SUFFIX_OUT))
                {
                    headKeyCode = removeLastStr(headKeyCode, ReveElecDVCons.KM_SUFFIX_OUT);
                    cellValueObj = (pojo as KmRelaRecord).LIST_KM_OUT;
                }else if(headKeyCode.EndsWith(ReveElecDVCons.KM_SUFFIX_INNER))
                {
                    headKeyCode = removeLastStr(headKeyCode, ReveElecDVCons.KM_SUFFIX_INNER);
                    cellValueObj = (pojo as KmRelaRecord).LIST_KM_INNER;
                }else
                {
                    cellValueObj = ReflectBase.getAttrValue(headKeyCode, (pojo as KmRelaRecord).KmRela, false);
                }

                if (cellValueObj == null)
                {
                    cellValue = "";
                }else if (cellValueObj is List<KmMap>)
                {
                    cellValue = "";
                    List<KmMap> list = cellValueObj as List<KmMap>;
                    foreach(KmMap km in list)
                    {
                       cellValue += ReflectBase.getAttrValue(headKeyCode, km, false); 
                       cellValue+=ReveElecDVCons.LINE_SEPARATOR;
                    }
                    cellValue = removeLastStr(cellValue,ReveElecDVCons.LINE_SEPARATOR);
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
                                cellValue = this.TranslateMultiLineValues(cellValue, showConvDict,ReveElecDVCons.LINE_SEPARATOR);
                            }
                        }
                    }


                }

                if ("null".Equals(cellValue))
                {
                    cellValue = "";
                }

                row.Cells.Add(new Cell(cellValue));
            }//结束for

            if (pojo is AuditableParamPojo)
            {
                object auditValueObj = ReflectBase.getAttrValue("AuditState", (pojo as KmRelaRecord).KmRela, false);

                if (auditValueObj == null)
                {
                    auditState = 0;
                }
                else
                {
                    auditState = int.Parse(auditValueObj.ToString());
                }

                setRowBackColor(auditState, row); // 设置行颜色只按有审核状态的来设置
            }

            ////插入附件单元格
            this.AddEnclosureButtonCell(row, pojo);
            return row;
        }

        /// <summary>
        /// 翻译多值数据
        /// </summary>
        /// <param name="cellValue">待翻译的值</param>
        /// <param name="showConvDict">词典</param>
        /// <returns>返回翻译好的值</returns>
        private string TranslateMultiLineValues(string cellValue, Dictionary<string, string> showConvDict,string str)
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

        
       
    }
}
