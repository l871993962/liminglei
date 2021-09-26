using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Yss.KTable.Models;



using System.Collections;

using System.Drawing;
using Yss.KTable.Enums;
using FAST.Core.Util;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Pojo;
using YssElecReco.Service.Er;
using FAST.Core.Communication.Service;
using YssElecReco.Form.Er;
using YssElecReco.Pojo.Er;

namespace YssElecReco.Fun
{
    /// <summary>
    /// 自定义加载器
    /// </summary>
    public class GzbHdTableListLoader : TableListLoader
    {
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
            List<String> indexCode = new List<string>();
            Dictionary<string, string> formatData = new Dictionary<string, string>();
            Dictionary<string, string> resultData = new Dictionary<string, string>();
            IErGzbService erGzService = ServiceFactory.createService<IErGzbService>();
            indexCode = erGzService.getRealIndexCode("1011", "SSZB");
            string c_port_code = "";
            if (tbMain.ParentForm is Frm_ELEC_GZ_S)
            {
                c_port_code = ((Frm_ELEC_GZ_S)(tbMain.ParentForm)).C_PORT_CODE;
            }else if (tbMain.ParentForm is Frm_ELEC_DBL_GZ_S)
            {
                c_port_code = ((Frm_ELEC_DBL_GZ_S)(tbMain.ParentForm)).C_PORT_CODE;
            }

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
                object n_amount = ReflectBase.getAttrValue("N_AMOUNT", pojo);

                bool isContainKmCode = indexCode.Contains(kmCode);

                if (!string.IsNullOrEmpty(kmCode) && (kmCode.StartsWith("4001") || isContainKmCode))
                {
                    string dataId = pojo.Id + "-" + c_port_code;
                    formatData.Add(dataId, null != n_amount ? n_amount.ToString() : "0.00");
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
                    else if (headKeyCode.Equals("C_CB_JZ_BL") || headKeyCode.Equals("C_SZ_JZ_BL"))
                    {
                        cellValue += "%";
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

                    setRowBackColor(auditState, row); // 设置行颜色只按有审核状态的来设置
                }


                ////setRowBackColor(auditState, row);

                row.Tag = pojo;
                tbMain.Rows.Add(row);
            }

            //// edit by qiantao STORY #83025 产品估值参数控制实收资本小数位 
            resultData = erGzService.formatedData(c_port_code, formatData);

            if (null != resultData && resultData.Count() > 0)
            {
                foreach (Yss.KTable.Models.Row row in tbMain.Rows)
                {
                    ErGzb erGzb = (ErGzb)row.Tag;
                    if (null != erGzb && (erGzb.C_KM_CODE.StartsWith("4001") || indexCode.Contains(erGzb.C_KM_CODE)))
                    {
                        if (resultData.ContainsKey(erGzb.Id))
                        {
                            foreach (Cell cell in row.Cells)
                            {
                                if (cell.RelColumn.DataPropertyName.Equals("N_AMOUNT"))
                                {
                                    cell.Text = ClsFunction.toNumber(resultData.FirstOrDefault(q => q.Key.Equals(erGzb.Id)).Value);
                                }
                            }
                        }
                    }
                }
            }

        }

    }
}
