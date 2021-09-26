using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using Yss.KTable.Models;
using YssElecReco.Service.Er;
using FAST.Core.Bussiness.Form;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Pojo;
using FAST.Core.BaseControl.Fun;
using FAST.Core.Context;
using FAST.Core.Util;
using FAST.Core.Communication.Service;
using FAST.Core.Communication.BusiService;
using FAST.Core.Exceptions;
using YssInformation.Support.Sys.Dictionary.Pojo;

namespace YssElecReco.Form.Er
{
    /// <summary>
    /// 产生电子对账核对数据
    /// </summary>
    public partial class Frm_ELEC_RECO_L : FrmBaseOper
    {
        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_ELEC_RECO_L()
        {
            InitializeComponent();
            logInfoType = ClsEnums.InfoType._BUSNESSLOG_DZ;
            receiveCustomLogBus = new receiveMegLog(getCustomLog);
            this.bUseMVCService = true;
        }

        /// <summary>
        /// 产生电子对账数据执行窗体加载事件
        /// 在此初始化
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ELEC_RECO_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.dataService = ServiceFactory.createService(serviceType) as IErDataService;
            loadMainTbData();
        }

        /// <summary>
        /// 重写主区域加载方法，屏蔽此方法
        /// </summary>
        /// <param name="pojo">pojo</param>
        /// <param name="bQueryData">bQueryData</param>
        /// <returns>QueryRes</returns>
        public override QueryRes getMainListDataMVC(BasePojo pojo, bool bQueryData)
        {
            return null;
        }


        /// <summary>
        /// 加载划款类型字典数据到主区域
        /// </summary>
        private void loadMainTbData()
        {
            try
            {
                List<DzType> lDzTypeList = ((IErDataService)this.dataService).getDzType();

                if (null != lDzTypeList && lDzTypeList.Count > 0)
                {
                    Yss.KTable.Models.Column col = null; ////KTable 列对象
                    Yss.KTable.Models.Row row = null; ////行
                    Yss.KTable.Models.CheckBoxCell cell = null; ////单元格 
                    Yss.KTable.Models.Row childRow = null; ////行
                    int count = 0; ////记录加载列数
                    Dictionary<string, List<DzType>> showItem = new Dictionary<string, List<DzType>>();

                    Dictionary<string, Yss.KTable.Models.CheckBoxCell> dictCells = new Dictionary<string, CheckBoxCell>();

                    // 加载列头
                    for (int i = 0; i < 5; i++)
                    {
                        col = new Yss.KTable.Models.Column();
                        col.Text = "";
                        if (i == 0)
                        {
                            col.Width = 150;
                        }
                        else
                        {
                            col.Width = 120;
                        }

                        tbMain.Columns.Add(col);
                    }

                    foreach (DzType xdzType in lDzTypeList)
                    {
                        // 如果为父节点，直接创建行
                        if (xdzType.C_DZ_CODE_P.Equals("[root]"))
                        {
                            if (showItem.ContainsKey(xdzType.C_DZ_CODE))
                            {
                                List<DzType> temp = showItem[xdzType.C_DZ_CODE] as List<DzType>;
                                temp.Add(xdzType);
                                showItem.Remove(xdzType.C_DZ_CODE);
                                showItem.Add(xdzType.C_DZ_CODE, temp);
                            }
                            else
                            {
                                List<DzType> temp = new List<DzType>();
                                temp.Add(xdzType);
                                showItem.Add(xdzType.C_DZ_CODE, temp);
                            }
                        }
                        else
                        {
                            if (showItem.ContainsKey(xdzType.C_DZ_CODE_P))
                            {
                                List<DzType> temp = showItem[xdzType.C_DZ_CODE_P] as List<DzType>;
                                temp.Add(xdzType);
                                showItem.Remove(xdzType.C_DZ_CODE_P);
                                showItem.Add(xdzType.C_DZ_CODE_P, temp);
                            }
                            else
                            {
                                List<DzType> temp = new List<DzType>();
                                temp.Add(xdzType);
                                showItem.Add(xdzType.C_DZ_CODE_P, temp);
                            }
                        }
                    }

                    foreach (string skey in showItem.Keys)
                    {
                        List<DzType> list = showItem[skey] as List<DzType>;

                        row = new Yss.KTable.Models.Row(); ////创建行
                        int rowNum = 0;
                        foreach (DzType clsDzType in list)
                        {
                            // 如果为父节点，直接创建行
                            if (clsDzType.C_DZ_CODE_P.Equals("[root]"))
                            {
                                if (row != null)
                                {
                                    this.tbMain.Rows.Add(row);
                                }

                                cell = new Yss.KTable.Models.CheckBoxCell();
                                cell.Text = clsDzType.C_DZ_NAME;
                                cell.Font = new Font(tbMain.Font, FontStyle.Bold);
                                row.Cells.Add(cell); ////加入行单元格
                                row.Tag = clsDzType;

                                dictCells.Add(clsDzType.C_DZ_CODE, cell);
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
                                cell.Text = clsDzType.C_DZ_NAME;
                                cell.Tag = clsDzType;
                                childRow.Cells.Add(cell); ////行记录中增加单元格
                                dictCells.Add(clsDzType.C_DZ_CODE, cell);
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

                    tbMain.Tag = dictCells;
                    tbMain.AutoWidth();
                    tbMain.Refresh();

                }
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }
        }

        /// <summary>
        /// 重写此方法byleeyu20140419
        /// </summary>
        /// <returns>字典</returns>
        protected override Dictionary<string, string> getSelOperItemTags()
        {
            Dictionary<string, string> dict = new Dictionary<string, string>();
            foreach (Yss.KTable.Models.Row row in tbMain.Rows)
            {
                foreach (Row subRow in row.SubRows)
                {
                    foreach (CheckBoxCell cell in subRow.Cells)
                    {
                        if (cell.Checked)
                        {
                            DzType dztype = cell.Tag as DzType;
                            dict.Add(dztype.C_DZ_CODE, dztype.C_DZ_NAME);
                        }
                    }
                }
            }

            return dict;
        }

        /// <summary>
        /// 设置级别结构
        /// </summary>
        /// <param name="structRecord">保存级别的结构</param>
        protected override void buildRecordStruct(ref string[] structRecord)
        {
            structRecord = new string[] { "PORT", "DATE", "C_DZ_CODE" };
        }

        /// <summary>
        /// 对主区域复选框勾选状态的控制，保证父节点和子节点勾选状态的相对统一，父节点的勾选状态决定其子节点的勾选状态，所有子节点的统一状态影响父节点的勾选状态
        /// </summary>
        /// <param name="sender">table</param>
        /// <param name="e">复选框状态改变事件</param>
        private void tbMain_CheckStateChanged(object sender, Yss.KTable.Events.CheckStateChangeEventArgs e)
        {
            int count = 0; ////判断是否子节点全部选中
            Yss.KTable.Models.CheckBoxCell cell = (Yss.KTable.Models.CheckBoxCell)sender;
            Yss.KTable.Models.CheckBoxCell parentRowCell = null;

            // 如果为父节点，则此节点下的所有子节点的勾选状态与其同步
            if (cell.OwnRow.HasChild)
            {
                foreach (Yss.KTable.Models.Row row in cell.OwnRow.SubRows)
                {
                    foreach (Yss.KTable.Models.CheckBoxCell tmpCell in row.Cells)
                    {
                        tmpCell.Checked = cell.Checked;
                    }
                }
            }
            else
            {
                // 循环子节点行记录
                foreach (Yss.KTable.Models.Row row in cell.OwnRow.ParentRow.SubRows)
                {
                    count += row.Cells.Count;
                    foreach (Yss.KTable.Models.CheckBoxCell tmpCell in row.Cells)
                    {
                        // 如果状态相同，则子节点数量减1
                        if (tmpCell.Checked.ToString().Equals(cell.Checked.ToString()))
                        {
                            count--;
                        }
                    }
                }

                // 如果最后计数器为0，则表示子节点的状态一致
                if (count == 0)
                {
                    parentRowCell = (Yss.KTable.Models.CheckBoxCell)cell.OwnRow.ParentRow.Cells[0];
                    parentRowCell.Checked = cell.Checked;
                }
            }
        }

        /// <summary>
        /// 准备处理用参数集合
        /// </summary>
        /// <param name="paraDict">处理用参数集合</param>
        protected override void setParaDict(Dictionary<string, string> paraDict)
        {
            string itemStr = "";
            DzType xdzType = null;
 
            string search = getSelPortCodes();
            List<DzType> lstItems = getSelOperItems();

            foreach (object obj in lstItems)
            {
                xdzType = obj as DzType;
                itemStr += xdzType.C_DZ_CODE + ",";
            }

            // 移除最后一个逗号
            if (itemStr.EndsWith(","))
            {
                itemStr = itemStr.Remove(itemStr.Length - 1);
            }

            paraDict.Add("ARRAY_C_DZ_CODE", itemStr);

            paraDict.Add("ARRAY_C_PORT_CODE", search);

            if (this.yssDateTime.getBeginDate != null)
            {
                paraDict.Add("D_START_DATE", d_OperCurr.ToString("yyyy-MM-dd"));
            }

            if (this.yssDateTime.getEndDate != null)
            {
                paraDict.Add("D_END_DATE", d_OperCurr.ToString("yyyy-MM-dd"));
            }

            paraDict.Add("C_OPER_CODE", execOperCode);
        }

        /// <summary>
        /// 获取主区域数据
        /// </summary>
        /// <returns>List</returns>
        private List<DzType> getSelOperItems()
        {
            List<DzType> selCellPojo = new List<DzType>();
            try
            {
                foreach (Yss.KTable.Models.Row row in tbMain.Rows)
                {
                    foreach (Yss.KTable.Models.Row subRow in row.SubRows)
                    {
                        foreach (Yss.KTable.Models.CheckBoxCell selCell in subRow.Cells)
                        {
                            if (selCell.Checked)
                            {
                                selCellPojo.Add(selCell.Tag as DzType);
                            }
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }

            return selCellPojo;
        }

        /// <summary>
        /// 获取日终处理服务
        /// </summary>
        protected override void setOperServiceInstance()
        {
            operSVC = BusiOperServiceFactory.createService<IErDataControlService>();
        }

        /// <summary>
        /// 方法重写 add by liuxiang 2014-2-12 按钮状态控制、执行进度显示
        /// </summary>
        /// <returns>string</returns>
        public override string doSubSection()
        {
            string result;
            try
            {
                btnBarOper.setButtonEnabled(ClsButtonName.BtnExecute, false);
                btnBarOper.setButtonEnabled(ClsButtonName.BtnDelete, true);
                result = base.doSubSection();
                this.proBar.Value = ((n_DoingSubSection_ID + 1) * this._leftPojos.Count) - 1;
                this.UpdateProgressStatus();

                if (this.proBar.Value == this.proBar.Maximum)
                {
                    btnBarOper.setButtonEnabled(ClsButtonName.BtnExecute, true);
                    UpdateProgressStatusByStoped();
                }
            }
            catch (Exception e)
            {
                btnBarOper.setButtonEnabled(ClsButtonName.BtnExecute, true);
                throw e;
            }

            return result;
        }

        /// <summary>
        /// 查询
        /// 重新加载执行项目 liuxiang 2017-1-9 BUG147177汇集调尾处理界面缺少查询按钮
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        public override void btnSearch_Click(object sender, EventArgs e)
        {
            this.tbMain.Clear();
            loadMainTbData();
        }
    }
}
