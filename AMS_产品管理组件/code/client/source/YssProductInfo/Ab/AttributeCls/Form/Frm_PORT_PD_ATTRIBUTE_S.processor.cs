using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;

using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using System.Collections;
using Yss.KTable.Models;
using Yss.KTable.Collections;
using FAST.Common.Service.Services;
using YssProductInfo.Support.Ab.AttributeCls.Pojo;
using YssProductInfo.Support.Ab.AttributeCls.Service;

namespace YssProductInfo.Ab.AttributeCls.Form
{
    /// <summary>
    /// 产品属性窗体处理类
    /// </summary>
    public partial class Frm_PORT_PD_ATTRIBUTE_S : FrmBaseSet
    {
        /// <summary>
        /// 将数据封装到POJO对象
        /// </summary>
        /// <returns>列表</returns>
        public override ArrayList yssGetObjListMVC()
        {
            ArrayList list = null;
            RowCollection rowCol = null;
            try
            {
                list = new ArrayList();
                rowCol = tbMain.CheckedRows;
                if (rowCol == null || rowCol.Count == 0)
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonWarn("WRN-000042", _formFun, status));
                    ////BUG #124958 edit by xhb 20160107 未选择状态下，返回null
                    return null;
                }

                //// add by liuxiang 2015-01-12 保存前将当前单元格控件值赋给单元格，并将控件从单元格移除
                if (this.tbMain.SelectedRow != null)
                {
                    foreach (Cell cell in this.tbMain.SelectedRow.Cells)
                    {
                        if (cell.InnerControl != null && cell.Selected)
                        {
                            string cellText = cell.InnerControl.Text;

                            if (cell.InnerControl is FAST.Core.BaseControl.YssSelCombox)
                            {
                                string comBoxValue = "";
                                ////存储下拉框的Value值。
                                comBoxValue = (cell.InnerControl as FAST.Core.BaseControl.YssSelCombox).Value;
                                cell.Key = comBoxValue;
                            }
                            else if (cell.InnerControl is Yss.KRichEx.YssTextBox)
                            {
                                string txtValue = (cell.InnerControl as Yss.KRichEx.YssTextBox).Text;
                                cell.Key = txtValue;
                            }

                            cell.InnerControl.Tag = null;
                            cell.InnerControl = null;
                            cell.Text = cellText;
                        }
                    }
                }

                foreach (Yss.KTable.Models.Row row in rowCol)
                {
                    PortPdAttribute portPdAttribute = new PortPdAttribute();
                    ////投资组合
                    portPdAttribute.C_PORT_CODE = row.Cells[colPortCode.Index].Text;
                    ////资产类型
                    portPdAttribute.C_DAT_CODE = row.Cells[colAssetsTypeCode.Index].Text;
                    ////运作类型
                    portPdAttribute.C_OPER_TYPE = row.Cells[colOperType.Index].Key != null ? row.Cells[colOperType.Index].Key : " ";
                    ////明细资产类型
                    portPdAttribute.C_DAT_MXCODE = row.Cells[colMxAssetsType.Index].Key != null ? row.Cells[colMxAssetsType.Index].Key : " ";
                    ////募集对象
                    portPdAttribute.C_COLLECT_CODE = row.Cells[colCollectCode.Index].Key != null ? row.Cells[colCollectCode.Index].Key : " ";
                    ////客户类型
                    portPdAttribute.C_CLIENT_TYPE = row.Cells[colClientType.Index].Key != null ? row.Cells[colClientType.Index].Key : " ";
                    ////投资对象
                    portPdAttribute.C_INVEST_CODE = row.Cells[colInvestCode.Index].Key != null ? row.Cells[colInvestCode.Index].Key : " ";
                    ////资产种类
                    portPdAttribute.C_ASSETS_CODE = row.Cells[colAssetsCode.Index].Key != null ? row.Cells[colAssetsCode.Index].Key : " ";
                    ////证券结算模式
                    portPdAttribute.C_SETT_MODE = row.Cells[colSettMode.Index].Key != null ? row.Cells[colSettMode.Index].Key : " ";
                    ////组合类别
                    portPdAttribute.C_PORT_TYPE = row.Cells[colPortType.Index].Key != null ? row.Cells[colPortType.Index].Key : " ";
                    ////产品编号
                    portPdAttribute.C_PRODUCT_NUM = row.Cells[colPdNum.Index].Key;
                    ////短编码
                    ////add by guohui 20161027  STORY34547【南方基金】保险资产报表需要区分产品编码(使用协会统一编码）与短码////
                    portPdAttribute.C_SHORT_NUM = row.Cells[colShortNum.Index].Key;
                    ////合同名称
                    portPdAttribute.C_CONTRACT_NAME = row.Cells[colContractName.Index].Key;
                    ////次托托管账户  add by wsm 2016-5-7 STORY #30235【招商基金母公司】[QDII]TradeBlotter_EXP TradeBlotter招行导出接口
                    portPdAttribute.C_CTTG_ACCOUNT = row.Cells[colttAccount.Index].Key;
                    ////证监会简称 add by wgl  STORY #36888 【南方基金】【紧急】产品信息多加一列证监会简称字段
                    portPdAttribute.C_ZJHJC = row.Cells[zjhjc.Index].Key;
                    ////描述
                    portPdAttribute.C_DESC = row.Cells[colDesc.Index].Key;

                    if (status.Equals(ClsEnums.StatusSetting.YssEdit))
                    {
                        portPdAttribute.Id = row.Cells[iden.Index].Text;
                    }

                    list.Add(portPdAttribute);
                }
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }

            return list;
        }

        /// <summary>
        /// 窗体的显示
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            List<string> portCodeList = null;
            Dictionary<string, string> portAssTypeDict = null;
            Dictionary<string, string> portNameDict = null;

            try
            {
                ////BUG #131480 无法访问已释放资源，时有时无的出现。
                ////清理行之前，先移走所有的InnerControl，以防InnerControl被同步销毁。张绍林-20160525
                this.tbMain.RemoveCellsControl();
                this.tbMain.Rows.Clear(true);
                portCodeList = new List<string>();
                portAssTypeDict = new Dictionary<string, string>();
                portNameDict = new Dictionary<string, string>();

                ////if (frmBaseViewList != null && frmBaseViewList.getSelectTypeItemList().Count > 0)
                if (frmBaseViewList != null)
                {
                    ////获取List界面选中数据
                    List<BasePojo> pojoList = frmBaseViewList.getSelectTypeItemList();

                    if (0 == pojoList.Count)
                    {
                        pojoList.Add((BasePojo)frmBaseViewList.tbMain.SelectedRow.Tag);
                    }

                    ////获取所选中数据的组合代码
                    portCodeList = this.getPortCodeList(pojoList);
                    ////获取这些组合代码的资产类型代码
                    portAssTypeDict = this.getAssCodeByPortCode(portCodeList);

                    portNameDict = this.getPortNameDict(portCodeList);

                    foreach (BasePojo basePojo in pojoList)
                    {
                        PortPdAttribute portPdAttribute = basePojo as PortPdAttribute;
                        if (status.Equals(ClsEnums.StatusSetting.YssEdit))
                        {
                            if (_formFun.N_CHECK == 1) 
                            {
                                if (portPdAttribute.AuditState == 1)
                                {
                                    return;
                                }
                            }
                        }

                        Row tempRow = new Row();
                        tempRow.Height = 22;
                        ////add by zzk 20150929 ADD前处理，ADD后无法更改 BUG #119902 问题1
                        tempRow.Checked = true;

                        this.tbMain.Rows.Add(tempRow);
                        LoadTbMainList(tempRow);

                        foreach (Column tempColumn in this.tbMain.Columns)
                        {
                            ////投资组合（代码）
                            int index = colPortCode.Index;
                            if (portNameDict.ContainsKey(portPdAttribute.C_PORT_CODE))
                            {
                                tempRow.Cells[index].Text = portPdAttribute.C_PORT_CODE;
                            }

                            ////投资组合（名称）
                            index = colPortName.Index;
                            if (portNameDict.ContainsKey(portPdAttribute.C_PORT_CODE))
                            {
                                tempRow.Cells[index].Text = portNameDict[portPdAttribute.C_PORT_CODE];
                            }

                            ////id
                            index = iden.Index;
                            tempRow.Cells[index].Text = portPdAttribute.Id;

                            ////资产类型（名称）
                            index = colAssetsType.Index;
                            if (colAssetsType.CellDictionaries.ContainsKey(portPdAttribute.C_DAT_CODE))
                            {
                                tempRow.Cells[index].Text = colAssetsType.CellDictionaries[portPdAttribute.C_DAT_CODE];
                            }

                            ////资产类型（代码）
                            index = colAssetsTypeCode.Index;
                            tempRow.Cells[index].Text = portPdAttribute.C_DAT_CODE;

                            ////明细资产类型
                            index = colMxAssetsType.Index;
                            if (colMxAssetsType.CellDictionaries.ContainsKey(portPdAttribute.C_DAT_MXCODE))
                            {
                                tempRow.Cells[index].Text = colMxAssetsType.CellDictionaries[portPdAttribute.C_DAT_MXCODE];
                                tempRow.Cells[index].Key = portPdAttribute.C_DAT_MXCODE;
                            }

                            ////运作类型
                            index = colOperType.Index;
                            if (colInvestCode.CellDictionaries.ContainsKey(portPdAttribute.C_OPER_TYPE))
                            {
                                tempRow.Cells[index].Text = colInvestCode.CellDictionaries[portPdAttribute.C_OPER_TYPE];
                                tempRow.Cells[index].Key = portPdAttribute.C_OPER_TYPE;
                            }

                            ////募集对象
                            index = colCollectCode.Index;
                            if (colInvestCode.CellDictionaries.ContainsKey(portPdAttribute.C_COLLECT_CODE))
                            {
                                tempRow.Cells[index].Text = colInvestCode.CellDictionaries[portPdAttribute.C_COLLECT_CODE];
                                tempRow.Cells[index].Key = portPdAttribute.C_COLLECT_CODE;
                            }

                            ////客户类型
                            index = colClientType.Index;
                            if (colInvestCode.CellDictionaries.ContainsKey(portPdAttribute.C_CLIENT_TYPE))
                            {
                                tempRow.Cells[index].Text = colInvestCode.CellDictionaries[portPdAttribute.C_CLIENT_TYPE];
                                tempRow.Cells[index].Key = portPdAttribute.C_CLIENT_TYPE;
                            }

                            ////投资对象
                            index = colInvestCode.Index;
                            if (colInvestCode.CellDictionaries.ContainsKey(portPdAttribute.C_INVEST_CODE))
                            {
                                tempRow.Cells[index].Text = colInvestCode.CellDictionaries[portPdAttribute.C_INVEST_CODE];
                                tempRow.Cells[index].Key = portPdAttribute.C_INVEST_CODE;
                            }

                            ////资产种类
                            index = colAssetsCode.Index;
                            if (colInvestCode.CellDictionaries.ContainsKey(portPdAttribute.C_ASSETS_CODE))
                            {
                                tempRow.Cells[index].Text = colInvestCode.CellDictionaries[portPdAttribute.C_ASSETS_CODE];
                                tempRow.Cells[index].Key = portPdAttribute.C_ASSETS_CODE;
                            }

                            ////证券结算模式
                            index = colSettMode.Index;
                            if (colInvestCode.CellDictionaries.ContainsKey(portPdAttribute.C_SETT_MODE))
                            {
                                tempRow.Cells[index].Text = colInvestCode.CellDictionaries[portPdAttribute.C_SETT_MODE];
                                tempRow.Cells[index].Key = portPdAttribute.C_SETT_MODE;
                            }

                            ////组合类别
                            index = colPortType.Index;
                            if (colInvestCode.CellDictionaries.ContainsKey(portPdAttribute.C_PORT_TYPE))
                            {
                                tempRow.Cells[index].Text = colInvestCode.CellDictionaries[portPdAttribute.C_PORT_TYPE];
                                tempRow.Cells[index].Key = portPdAttribute.C_PORT_TYPE;
                            }

                            ////产品编号
                            index = colPdNum.Index;
                            tempRow.Cells[index].Text = portPdAttribute.C_PRODUCT_NUM;
                            tempRow.Cells[index].Key = portPdAttribute.C_PRODUCT_NUM;

                            ////短编码
                            ////add by guohui 20161026  STORY34547【南方基金】保险资产报表需要区分产品编码(使用协会统一编码）与短码////
                            index = colShortNum.Index;
                            tempRow.Cells[index].Text = portPdAttribute.C_SHORT_NUM;
                            tempRow.Cells[index].Key = portPdAttribute.C_SHORT_NUM;

                            ////合同名称
                            index = colContractName.Index;
                            tempRow.Cells[index].Text = portPdAttribute.C_CONTRACT_NAME;
                            tempRow.Cells[index].Key = portPdAttribute.C_CONTRACT_NAME;

                            ////次托托管账户 add by wsm 2016-5-7 STORY #30235【招商基金母公司】[QDII]TradeBlotter_EXP TradeBlotter招行导出接口
                            index = colttAccount.Index;
                            tempRow.Cells[index].Text = portPdAttribute.C_CTTG_ACCOUNT;
                            tempRow.Cells[index].Key = portPdAttribute.C_CTTG_ACCOUNT;

                            ////描述
                            index = colDesc.Index;
                            tempRow.Cells[index].Text = portPdAttribute.C_DESC;
                            tempRow.Cells[index].Key = portPdAttribute.C_DESC;
                        }
                        ////edt by zzk 20150929 ADD前处理，ADD后无法更改
                        ////tempRow.Checked = true;
                    }
                }
            }
            catch (ClsBaseException ex) 
            {
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 获取组合代码
        /// </summary>
        /// <param name="pojoList">pojoList</param>
        /// <returns>List</returns>
        private List<string> getPortCodeList(List<BasePojo> pojoList)
        {
            List<string> portCodeList = null;

            try
            {
                portCodeList = new List<string>();

                if (null != pojoList)
                {
                    portCodeList = new List<string>();
                    foreach (BasePojo pojo in pojoList)
                    {
                        PortPdAttribute ppa = pojo as PortPdAttribute;
                        portCodeList.Add(ppa.C_PORT_CODE);
                    }
                }
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }

            return portCodeList;
        }

        /// <summary>
        /// 获取资产类型
        /// </summary>
        /// <returns>dict</returns>
        public Dictionary<string, string> getAssTypeDict()
        {
            Dictionary<string, string> assTypeDict = null;

            try
            {
                assTypeDict = new Dictionary<string, string>();
                assTypeDict = ((YssProductInfo.Support.Ab.AttributeCls.Service.IPortPdAttributeService)dataService).getAssetDict();
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }

            return assTypeDict;
        }

        /// <summary>
        ///  获取词汇字典
        /// </summary>
        /// <returns>vocabularyDict</returns>
        public Dictionary<string, string> getVocabularyDict()
        {
            Dictionary<string, string> vocabularyDict = null;

            try
            {
                vocabularyDict = new Dictionary<string, string>();
                vocabularyDict = ((YssProductInfo.Support.Ab.AttributeCls.Service.IPortPdAttributeService)dataService).getVocabularyDict();
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }

            return vocabularyDict;
        }

        /// <summary>
        ///  根据组合代码获取组合信息
        /// </summary>
        /// <param name="portCodeList">portCodeList</param>
        /// <returns>BasePojo</returns>
        public Dictionary<string, string> getAssCodeByPortCode(List<string> portCodeList)
        {
            StringBuilder buffer = null;
            Dictionary<string, string> assCodeDict = null;
            Dictionary<string, string> paraDict = null;

            try
            {
                buffer = new StringBuilder();
                assCodeDict = new Dictionary<string, string>();
                paraDict = new Dictionary<string, string>();
                foreach (string portCode in portCodeList)
                {
                    buffer.Append(portCode);
                    buffer.Append(",");
                }

                string arrayPort = (buffer.ToString()).Substring(0, (buffer.ToString()).Length - 1);
                paraDict.Add("ARRAY_C_PORT_CODE", arrayPort);

                assCodeDict = ((YssProductInfo.Support.Ab.AttributeCls.Service.IPortPdAttributeService)dataService).getPortPojoList(paraDict);
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }

            return assCodeDict;
        }

        /// <summary>
        /// 获取组合名称
        /// </summary>
        /// <param name="portCodeList">portCodeList</param>
        /// <returns>dict</returns>
        private Dictionary<string, string> getPortNameDict(List<string> portCodeList)
        {
            StringBuilder buffer = null;
            Dictionary<string, string> portNameDict = null;
            Dictionary<string, string> paraDict = null;
            try
            {
                buffer = new StringBuilder();
                portNameDict = new Dictionary<string, string>();
                paraDict = new Dictionary<string, string>();
                foreach (string portCode in portCodeList)
                {
                    buffer.Append(portCode);
                    buffer.Append(",");
                }

                string arrayPort = (buffer.ToString()).Substring(0, (buffer.ToString()).Length - 1);
                paraDict.Add("ARRAY_C_PORT_CODE", arrayPort);

                portNameDict = ((YssProductInfo.Support.Ab.AttributeCls.Service.IPortPdAttributeService)dataService).getPortNameDict(paraDict);
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }

            return portNameDict;
        }

        /// <summary>
        /// 将数据封装到POJO对象 BUG #119902 海通证券20.4.7版本功能测试bug 问题1 保存后在当前界面进行“修改”、“复制”、“删除”报错
        /// </summary>
        /// <returns>Pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            RowCollection rowCol = null;
            PortPdAttribute portPdAttribute = new PortPdAttribute();
            try
            {
                rowCol = tbMain.CheckedRows;
                if (rowCol == null || rowCol.Count == 0)
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonWarn("WRN-000042", _formFun, status));
                    ////BUG #124958 edit by xhb 20160107 未选择状态下，返回null
                    return null;
                }

                if (this.tbMain.SelectedRow != null)
                {
                    foreach (Cell cell in this.tbMain.SelectedRow.Cells)
                    {
                        if (cell.InnerControl != null && cell.Selected)
                        {
                            string cellText = cell.InnerControl.Text;

                            if (cell.InnerControl is FAST.Core.BaseControl.YssSelCombox)
                            {
                                string comBoxValue = "";
                                ////存储下拉框的Value值。
                                comBoxValue = (cell.InnerControl as FAST.Core.BaseControl.YssSelCombox).Value;
                                cell.Key = comBoxValue;
                            }
                            else if (cell.InnerControl is Yss.KRichEx.YssTextBox)
                            {
                                string txtValue = (cell.InnerControl as Yss.KRichEx.YssTextBox).Text;
                                cell.Key = txtValue;
                            }

                            cell.InnerControl.Tag = null;
                            cell.InnerControl = null;
                            cell.Text = cellText;
                        }
                    }
                }

                foreach (Yss.KTable.Models.Row row in rowCol)
                {
                    ////PortPdAttribute portPdAttribute = new PortPdAttribute();
                    ////投资组合
                    portPdAttribute.C_PORT_CODE = row.Cells[colPortCode.Index].Text;
                    ////资产类型
                    portPdAttribute.C_DAT_CODE = row.Cells[colAssetsTypeCode.Index].Text;
                    ////明细资产类型
                    portPdAttribute.C_DAT_MXCODE = row.Cells[colMxAssetsType.Index].Key != null ? row.Cells[colMxAssetsType.Index].Key : " ";
                    ////运作类型
                    portPdAttribute.C_OPER_TYPE = row.Cells[colOperType.Index].Key != null ? row.Cells[colOperType.Index].Key : " ";
                    ////募集对象
                    portPdAttribute.C_COLLECT_CODE = row.Cells[colCollectCode.Index].Key != null ? row.Cells[colCollectCode.Index].Key : " ";
                    ////客户类型
                    portPdAttribute.C_CLIENT_TYPE = row.Cells[colClientType.Index].Key != null ? row.Cells[colClientType.Index].Key : " ";
                    ////投资对象
                    portPdAttribute.C_INVEST_CODE = row.Cells[colInvestCode.Index].Key != null ? row.Cells[colInvestCode.Index].Key : " ";
                    ////资产种类
                    portPdAttribute.C_ASSETS_CODE = row.Cells[colAssetsCode.Index].Key != null ? row.Cells[colAssetsCode.Index].Key : " ";
                    ////证券结算模式
                    portPdAttribute.C_SETT_MODE = row.Cells[colSettMode.Index].Key != null ? row.Cells[colSettMode.Index].Key : " ";
                    ////组合类别
                    portPdAttribute.C_PORT_TYPE = row.Cells[colPortType.Index].Key != null ? row.Cells[colPortType.Index].Key : " ";
                    ////产品编号
                    portPdAttribute.C_PRODUCT_NUM = row.Cells[colPdNum.Index].Key;
                    ////合同名称
                    portPdAttribute.C_CONTRACT_NAME = row.Cells[colContractName.Index].Key;
                    ////描述
                    portPdAttribute.C_DESC = row.Cells[colDesc.Index].Key;

                    if (status.Equals(ClsEnums.StatusSetting.YssEdit))
                    {
                        portPdAttribute.Id = row.Cells[iden.Index].Text;
                    }

                    return portPdAttribute;
                }
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }

            return portPdAttribute;
        }
    }
}


