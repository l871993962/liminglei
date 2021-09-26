using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Core.Util;
using System.Collections;
using Yss.KTable.Models;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using YssElecReco.Pojo.Para;

namespace YssElecReco.Fun
{
    /// <summary>
    /// 电子对账综合参数，表加载器
    /// </summary>
    class ErDspTableListLoader : TableListLoader
    {
        /// <summary>
        /// 按照Pojo和列信息创建行；
        /// STORY #78425 客户端通用kstar控件加载类方法对table特定列支持用颜色列显示功能 hp 20190829
        /// </summary>
        /// <param name="pojo">Pojo</param>
        /// <param name="dictHeader">列信息</param>
        /// <param name="showConvAssemble">转换字典</param>
        /// <param name="showSpecColumn">需要展示的列</param>
        /// <param name="plConvertByServiceId">是否按ServiceID获取转换字典(true:按ServiceID获取转换字典，false：按ListHeadInfo.Key获取转换字典)</param>
        /// <returns>创建的行</returns>
        protected override Row CreateRow(
            BasePojo pojo,
            Dictionary<string, ListHeadInfo> dictHeader,
            Dictionary<string, Dictionary<string, string>> showConvAssemble,
            ArrayList showSpecColumn,
            bool plConvertByServiceId)
        {
            string keyConvServiceId = string.Empty;
            Dictionary<string, string> showConvDict;
            object cellValueObj = null;
            string cellValue = string.Empty;
            int auditState = 0;

            Row row = new Row();
            row.Tag = pojo;

            if (this.showCheckColumn)
            {
                row.Cells.Add(new Cell());
            }

            if (this.showMarkColumn)
            {
                row.Cells.Add(new Cell());
            }

            string headKeyCode;
            string formatStr;

            //BUG #216132 【国泰基金】 组合关联邮件策略 新增数据时发生异常。张绍林-20180823
            for (int liHeadIndex = 0; liHeadIndex < showSpecColumn.Count; liHeadIndex++)
            {
                string sHeadKey = showSpecColumn[liHeadIndex] as string;
                if (dictHeader.ContainsKey(sHeadKey))
                {
                    ListHeadInfo headKey = dictHeader[sHeadKey];
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

                    cellValueObj = ReflectBase.getAttrValue(headKeyCode, pojo, false);

                    if (cellValueObj == null)
                    {
                        cellValue = string.Empty;
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
                                cellValue = string.Empty;
                            }
                            else
                            {
                                cellValue = tempDate.ToString(formatStr);
                            }
                        }
                        else
                        {
                            cellValue = cellValueObj.ToString();
                        }
                    }

                    if (showConvAssemble != null && showConvAssemble.Count > 0 && !"false".Equals(headKey.ShowConvert) && isConvert(headKeyCode, pojo))
                    {
                        if (plConvertByServiceId)
                        {
                            keyConvServiceId = headKey.ServiceId;
                        }
                        else
                        {
                            keyConvServiceId = headKey.Key;
                        }

                        if (keyConvServiceId == null)
                        {
                            keyConvServiceId = string.Empty;
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
                                    cellValue = TranslateMultiValues(cellValue, showConvDict);
                                }
                            }
                        }
                    }

                    if ("null".Equals(cellValue))
                    {
                        cellValue = string.Empty;
                    }

                    row.Cells.Add(new Cell(cellValue));
                }

                if (pojo is AuditableParamPojo)
                {
                    object auditValueObj = ReflectBase.getAttrValue("AuditState", pojo, false);

                    if (auditValueObj == null)
                    {
                        auditState = 0;
                    }
                    else
                    {
                        auditState = int.Parse(auditValueObj.ToString());
                    }

                    this.setRowBackColor(auditState, row); // 设置行颜色只按有审核状态的来设置
                }
            }

            return row;
        }


        /// <summary>
        /// 根据Pojo创建行
        /// </summary>
        /// <param name="pojo">BasePojo</param>
        /// <param name="headKeys">表头信息</param>
        /// <param name="showConvAssemble">翻译信息</param>
        /// <returns>返回创建好的行</returns>
        public override Row CreateRow(
            BasePojo pojo, List<ListHeadInfo> headKeys, Dictionary<string, Dictionary<string, string>> showConvAssemble)
        {
            object cellValueObj = null;
            string cellValue = string.Empty;
            string keyConvServiceId = string.Empty;
            int auditState = 0;
            Dictionary<string, string> showConvDict;

            Row row = new Row();
            row.Tag = pojo;
            if (this.showCheckColumn)
            {
                row.Cells.Add(new Cell());
            }

            if (this.showMarkColumn)
            {
                row.Cells.Add(new Cell());
            }

            string headKeyCode;
            string formatStr;

            //BUG #216132 【国泰基金】 组合关联邮件策略 新增数据时发生异常。张绍林-20180823
            for (int liIndex = 0; liIndex < headKeys.Count; liIndex++)
            {
                ListHeadInfo headKey = headKeys[liIndex];
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

                cellValueObj = ReflectBase.getAttrValue(headKeyCode, pojo, false);

                if (cellValueObj == null)
                {
                    cellValue = string.Empty;
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
                            cellValue = string.Empty;
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
                }

                if (showConvAssemble != null && !"false".Equals(headKey.ShowConvert) && isConvert(headKeyCode, pojo))
                {
                    keyConvServiceId = headKey.ServiceId;

                    if (keyConvServiceId == null)
                    {
                        keyConvServiceId = string.Empty;
                    }

                    if (showConvAssemble.ContainsKey(keyConvServiceId))
                    {
                        showConvDict = showConvAssemble[keyConvServiceId];
                        if (showConvDict != null)
                        {
                            //增加dictType判断 TASK #556469 TASK #549017 STORY #62170 T_S_DICT T_S_DV_FASTVOC整合 新的数据字典，支持传入code和type进行字典数
                            //by yanyi 20190102
                            if (headKey.DictType != null && !string.Empty.Equals(headKey.DictType))
                            {
                                object dictTypeValue = ReflectBase.getAttrValue(headKey.DictType, pojo, false);
                                string contactStr = string.Empty;
                                string contactStrShowConv = string.Empty;
                                if (dictTypeValue != null && !string.Empty.Equals(dictTypeValue))
                                {
                                    contactStr = dictTypeValue.ToString() + "_" + cellValueObj.ToString();
                                }
                                else
                                {
                                    //值为null时，判断默认值
                                    string dictTypeDefaultValue = getDictTypeDefaultValue(
                                        headKeys, headKey.DictType);
                                    if (dictTypeDefaultValue != null)
                                    {
                                        contactStr = dictTypeDefaultValue + "_" + cellValueObj.ToString();
                                    }
                                }

                                if (showConvDict.ContainsKey(contactStr))
                                {
                                    contactStrShowConv = showConvDict[contactStr];
                                }

                                //如果没有在装换列表中匹配到，则将原值放入cell
                                if (string.Empty.Equals(contactStrShowConv))
                                {
                                    cellValue = cellValueObj.ToString();
                                }
                                else
                                {
                                    cellValue = contactStrShowConv;
                                }
                            }
                            else
                            {
                                if (showConvDict.ContainsKey(cellValue))
                                {
                                    cellValue = showConvDict[cellValue];
                                }
                                else if (cellValue != null && cellValue.Contains("|"))
                                {
                                    cellValue = TranslateMultiValues(cellValue, showConvDict);
                                }
                            }
                        }
                    }
                }

                if ("null".Equals(cellValue))
                {
                    cellValue = string.Empty;
                }

                row.Cells.Add(new Cell(cellValue));
            }

            if (pojo is AuditableParamPojo)
            {
                object auditValueObj = ReflectBase.getAttrValue("AuditState", pojo, false);

                if (auditValueObj == null)
                {
                    auditState = 0;
                }
                else
                {
                    auditState = int.Parse(auditValueObj.ToString());
                }

                this.setRowBackColor(auditState, row); // 设置行颜色只按有审核状态的来设置
            }

            ////插入附件单元格
            this.AddEnclosureButtonCell(row, pojo);

            return row;
        }

        /// <summary>
        /// 是否根据词汇字典转换值
        /// </summary>
        /// <param name="headKeyCode">属性</param>
        /// <param name="pojo">值</param>
        /// <returns></returns>
        private bool isConvert(string headKeyCode, BasePojo pojo)
        {
            bool isConvert = true;
            if (pojo is ErDspManager)
            {
                string value = (pojo as ErDspManager).C_DSP_VALUE_TYPE;
                if (headKeyCode.Equals("C_DV_PARAMS_VALUE") && !"VOC".Equals(value))
                {
                    isConvert = false;
                }
            }
            
            return isConvert;
        }

        /// <summary>
        /// 翻译多值数据
        /// </summary>
        /// <param name="cellValue">待翻译的值</param>
        /// <param name="showConvDict">词典</param>
        /// <returns>返回翻译好的值</returns>
        private string TranslateMultiValues(string cellValue, Dictionary<string, string> showConvDict)
        {
            ////支持翻译多值的下拉框数据。张绍林-20161111
            string tempCellValue = string.Empty;
            string[] keyArray = cellValue.Split(new[] { "|" }, StringSplitOptions.RemoveEmptyEntries);
            if (keyArray != null)
            {
                foreach (string key in keyArray)
                {
                    if (showConvDict.ContainsKey(key))
                    {
                        tempCellValue += (showConvDict[key] + "|");
                    }
                }

                tempCellValue = tempCellValue.TrimEnd('|');
            }

            if (tempCellValue != string.Empty)
            {
                cellValue = tempCellValue;
            }

            return cellValue;
        }

        /// <summary>
        /// TASK #556469 TASK #549017 STORY #62170 T_S_DICT T_S_DV_FASTVOC整合 新的数据字典，支持传入code和type进行字典数
        /// 获取对应key的dictType默认值
        /// </summary>
        /// <param name="headKeys">字段信息集合</param>
        /// <param name="dictType">数据字典类型</param>
        /// <returns>对应key的dictType默认值</returns>
        private string getDictTypeDefaultValue(List<ListHeadInfo> headKeys, string dictType)
        {
            foreach (ListHeadInfo headKey in headKeys)
            {
                if (dictType.Equals(headKey.Key))
                {
                    return headKey.DefaultDictTypeValue;
                }
            }

            return null;
        }


    }
}
