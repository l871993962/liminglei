using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Interface;
using FAST.Common.Service.Pojo.Base;
////using YssBaseCls.Interface;
using FAST.Core.Communication.DataService;
using FAST.Common.Service.DataService.Base;
using FAST.Core.Exceptions;

using FAST.Core.Context;

using FAST.Platform.Right.Fun;
using FAST.Core.BaseControl.Fun;

using FAST.Core.Resource;


using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;



using System.Collections;
using Yss.KRichEx;
using Yss.KTable.Models;
using FAST.Core.BaseControl;
using FAST.Common.Service.DataService;
using YssSecInformation.Support.Mp.SecEq.Service;
using YssSecInformation.Support.Mp.SecEq.Pojo;


namespace YssSecInformation.Mp.SecEq.Form
{
    /// <summary>
    /// chenbo
    /// 2017-06-24
    /// #42948 资讯信息管理组件化拆分
    /// ------------------------------- 
    /// 停牌股票功能选项界面
    /// </summary>
    public partial class Frm_SUSPEND_PARA : FrmBase
    {
        /// <summary>
        /// 定义功能菜单对象
        /// </summary>
        private IBaseFun _fun;

        /// <summary>
        /// 功能选项服务
        /// </summary>
        private ISuspendedCondService condService = null;

        /// <summary>
        /// Color对应的QueryRes
        /// </summary>
        private QueryRes queryRes = null;

        /// <summary>
        /// 当前单元格
        /// </summary>
        private Cell currentCell = null;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_SUSPEND_PARA()
        {
            InitializeComponent();
        }

        /// <summary>
        /// 取消按钮点击事件，
        /// 当点击取消时，该界面关闭
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnOk_Click(object sender, EventArgs e)
        {
            ////进行更新操作
            try
            {
                initCurrentCell(currentCell);
                string operResult = condService.updateConds(yssGetObjListMVC());
                ////operAfterSave(operResult);
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtInfo("004", this._fun, status));
            }
            finally
            {
                //// 设置后关闭窗体
                this.Close();
            }
            
        }

        /// <summary>
        /// 确定按钮点击事件
        /// 当用户操作完成时，点击该按钮，进行确认，并关闭界面
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnQuit_Click(object sender, EventArgs e)
        {
            this.DialogResult = DialogResult.OK;
        }

        /// <summary>
        /// 封装窗体数据
        /// </summary>
        /// <returns>ArrayList</returns>
        public List<BasePojo> yssGetObjListMVC()
        {
            List<BasePojo> dataList = new List<BasePojo>();
            SuspendedCond cond = null;
            foreach (Row row in this.tbMain.Rows)
            {
                cond = (SuspendedCond)row.Tag;
                foreach (Cell cell in row.Cells)
                {
                    if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE"))
                    {
                        ////cond.C_ITEM_VALUE = null == cell.Tag ? cell.Text : cell.Tag.ToString();
                        if (cell.Text.Equals("调日指数估值价格"))
                        {
                            cond.C_ITEM_VALUE = "AJUST_DAY_MP";
                        }
                        else if (cell.Text.Equals("最近指数估值价格"))
                        {
                            cond.C_ITEM_VALUE = "RECENT_MP";
                        }
                        else if (cell.Text.Equals("停牌日指数估值价格"))
                        {
                            cond.C_ITEM_VALUE = "SUSPEND_MP";
                        }
                        else
                        {
                            cond.C_ITEM_VALUE = cell.Text;
                        }

                        cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                    }

                    if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_NAME"))
                    {
                        ////cond.C_ITEM_VALUE = null == cell.Tag ? cell.Text : cell.Tag.ToString();
                        if (cell.Text.Equals("停牌价格市值占资产净值比"))
                        {
                            cond.C_ITEM_CODE = "TPJGSZ_DRZCJZ_RATE";
                            cond.C_ITEM_NAME = "停牌价格市值占资产净值比";
                        }
                        else if (cell.Text.Equals("停牌价格市值汇总占资产净值比"))
                        {
                            cond.C_ITEM_CODE = "TPJGSZHZ_DRZCJZ_RATE";
                            cond.C_ITEM_NAME = "停牌价格市值汇总占资产净值比";
                        }

                        cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                    }

                    ////if (cell.RelColumn.DataPropertyName.Equals("C_LOGICAL_JUDGMENT") && (((SuspendedCond)cell.OwnRow.Tag).Id.Equals("8") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("9") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("10") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("11") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("12") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("13") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("14") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("15") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("16")))
                    //// add by zhd 2016-12-21
                    //// STORY36994【南方基金】停牌股票指数收益法逻辑变更
                    if (cell.RelColumn.DataPropertyName.Equals("C_LOGICAL_JUDGMENT") && (((SuspendedCond)cell.OwnRow.Tag).Id.Equals("9") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("10") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("11") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("12") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("2") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("13") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("14") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("15") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("16")))
                    {
                        ////cond.C_ITEM_VALUE = null == cell.Tag ? cell.Text : cell.Tag.ToString();
                        if (cell.Text.Equals("并且"))
                        {
                            cond.C_LOGICAL_JUDGMENT = "LJ_AND";
                        }
                        else if (cell.Text.Equals("或者"))
                        {
                            cond.C_LOGICAL_JUDGMENT = "LJ_OR";
                        }
                        else if (cell.Text.Equals("不启用"))
                        {
                            cond.C_LOGICAL_JUDGMENT = "LJ_NOT_ENABLED";
                        }
                        else
                        {
                            cond.C_LOGICAL_JUDGMENT = cell.Text;
                        }

                        cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                    }

                    if (cell.RelColumn.DataPropertyName.Equals("C_VALUE_TYPE"))
                    {
                        ////cond.C_ITEM_VALUE = null == cell.Tag ? cell.Text : cell.Tag.ToString();
                        if (cell.Text.Equals("工作日"))
                        {
                            cond.C_VALUE_TYPE = "VT_WORKDAY";
                        }
                        else if (cell.Text.Equals("自然日"))
                        {
                            cond.C_VALUE_TYPE = "VT_NATURAL";
                        }                        
                        else
                        {
                            cond.C_VALUE_TYPE = cell.Text;
                        }

                        cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                    }

                    ////editbyliyongjun 2016-08-07 增加过滤新股信息
                    ////if (cond.C_ITEM_CODE.Equals("FILTER_NEW_INFO"))
                    if (cond.C_ITEM_CODE.Equals("GLXGXX"))
                    {
                        if (cell.Text.Equals("是"))
                        {
                            cond.C_ITEM_VALUE = "1";
                        }
                        else if (cell.Text.Equals("否"))
                        {
                            cond.C_ITEM_VALUE = "0";
                        }
                    }

                    if (cond.C_ITEM_CODE.Equals("SGGZRTZGDGPMRTZ"))
                    {
                        if (cell.Text.Equals("是"))
                        {
                            cond.C_ITEM_VALUE = "1";
                        }
                        else if (cell.Text.Equals("否"))
                        {
                            cond.C_ITEM_VALUE = "0";
                        }
                    }

                    if (cond.C_ITEM_CODE.Equals("SFATPZHJYRZCJZ"))
                    {
                        if (cell.Text.Equals("是"))
                        {
                            cond.C_ITEM_VALUE = "1";
                        }
                        else if (cell.Text.Equals("否"))
                        {
                            cond.C_ITEM_VALUE = "0";
                        }
                    }

                    if (cond.C_ITEM_CODE.Equals("JXSJRXZQMZTJDGP"))
                    {
                        if (cell.Text.Equals("是"))
                        {
                            cond.C_ITEM_VALUE = "1";
                        }
                        else if (cell.Text.Equals("否"))
                        {
                            cond.C_ITEM_VALUE = "0";
                        }
                    }

                    if (cond.C_ITEM_CODE.Equals("HQTZZGGHQ"))
                    {
                        if (cell.Text.Equals("是"))
                        {
                            cond.C_ITEM_VALUE = "1";
                        }
                        else if (cell.Text.Equals("否"))
                        {
                            cond.C_ITEM_VALUE = "0";
                        }
                    }

                    if (cond.C_ITEM_CODE.Equals("GZJGSFKL_FPQJSG"))
                    {
                        if (cell.Text.Equals("是"))
                        {
                            cond.C_ITEM_VALUE = "1";
                        }
                        else if (cell.Text.Equals("否"))
                        {
                            cond.C_ITEM_VALUE = "0";
                        }
                    }

                    if (cond.C_ITEM_CODE.Equals("JRGZJGXYTPJ"))
                    {
                        if (cell.Text.Equals("是"))
                        {
                            cond.C_ITEM_VALUE = "1";
                        }
                        else if (cell.Text.Equals("否"))
                        {
                            cond.C_ITEM_VALUE = "0";
                        }
                    }

                    ////BUG #144030 股票停牌新增报错
                    if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("1"))
                    {
                        if (cell.Text.Equals("是"))
                        {
                            cond.C_ITEM_VALUE = "1";
                        }
                        else if (cell.Text.Equals("否"))
                        {
                            cond.C_ITEM_VALUE = "0";
                        } 
                    }
                }
                
                if (cond.C_ITEM_CODE.Equals("SUSPENDED_EXCEED_DAY"))
                {
                    cond.C_ITEM_VALUE = cond.C_ITEM_VALUE.Trim().Length == 0 ? "1" : cond.C_ITEM_VALUE;
                }
                else if (cond.C_ITEM_CODE.Equals("ZHYGTPR_T"))
                {
                    cond.C_ITEM_VALUE = cond.C_ITEM_VALUE.Trim().Length == 0 ? "1" : cond.C_ITEM_VALUE;
                }
                else if (cond.C_ITEM_CODE.Equals("ASSET_VALUE_RATE"))
                {
                    cond.C_ITEM_VALUE = cond.C_ITEM_VALUE.Trim().Length == 0 ? "0" : cond.C_ITEM_VALUE;
                }
                else if (cond.C_ITEM_CODE.Equals("DAILY_FLUCTUATE_RATE"))
                {
                    cond.C_ITEM_VALUE = cond.C_ITEM_VALUE.Trim().Length == 0 ? "0" : cond.C_ITEM_VALUE;
                }
                else if (cond.C_ITEM_CODE.Equals("INDEX_TOTAL_RANGE"))
                {
                    cond.C_ITEM_VALUE = cond.C_ITEM_VALUE.Trim().Length == 0 ? "0" : cond.C_ITEM_VALUE;
                }
                else if (cond.C_ITEM_CODE.Equals("FPGPDRZFQGZJ_SFJTZ"))
                {
                    cond.C_ITEM_VALUE = cond.C_ITEM_VALUE.Trim().Length == 0 ? "0" : cond.C_ITEM_VALUE;
                }
                else if (cond.C_ITEM_CODE.Equals("FPGPDRDFQGZJ_SFJTZ"))
                {
                    cond.C_ITEM_VALUE = cond.C_ITEM_VALUE.Trim().Length == 0 ? "0" : cond.C_ITEM_VALUE;
                }
                else if (cond.C_ITEM_CODE.Equals("TPJGSZ_DRZCJZ_RATE"))
                {
                    cond.C_ITEM_VALUE = cond.C_ITEM_VALUE.Trim().Length == 0 ? "0" : cond.C_ITEM_VALUE;
                }
                else if (cond.C_ITEM_CODE.Equals("TPJGSZHZ_DRZCJZ_RATE"))
                {
                    cond.C_ITEM_VALUE = cond.C_ITEM_VALUE.Trim().Length == 0 ? "0" : cond.C_ITEM_VALUE;
                }
                else if (cond.C_ITEM_CODE.Equals("ZRSZZZCJZB"))
                {
                    cond.C_ITEM_VALUE = cond.C_ITEM_VALUE.Trim().Length == 0 ? "0" : cond.C_ITEM_VALUE;
                }

                dataList.Add(cond);
            }

            return dataList;
        }

        /// <summary>
        /// 初始化信息
        /// </summary>
        public void initColumnHeader()
        {
            Dictionary<string, string> paraDict = new Dictionary<string, string>();

            // 获取配置规则数据实体
            paraDict.Add("dataClass", "SuspendedCond");
            ////paraDict.Add("N_STATE", "2");

            queryRes = condService.queryByCondition(paraDict);

            ArrayList showSpecColumns = new ArrayList();
            showSpecColumns.Add("Id");
            showSpecColumns.Add("C_LOGICAL_JUDGMENT");
            showSpecColumns.Add("C_ITEM_NAME");
            showSpecColumns.Add("C_ITEM_VALUE");
            showSpecColumns.Add("C_VALUE_TYPE");

            TableListLoader tableLoadList = new TableListLoader();
            tableLoadList.loadTable(this.tbMain, queryRes, false, false, ClsEnums.KTableDataShowMode.ListMode, showSpecColumns);
            
            int pos = 0;
            //// 解决词汇和数值冲突问题
            foreach (Row row in this.tbMain.Rows)
            {
                SuspendedCond cond = row.Tag as SuspendedCond;
                foreach (Cell cell in row.Cells)
                {
                    if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE"))
                    {
                        if (cond.Id != "1")
                        {
                            cell.Text = cond.C_ITEM_VALUE;
                        }

                        ////editbyliyongjun 2016-08-07 增加过滤新股信息
                        ////if (cond.Id == "6")
                        //// if (cond.Id == "2" || cond.Id == "3" || cond.Id == "4" || cond.Id == "5" || cond.Id == "6" || cond.Id == "7" || cond.Id == "17")
                        //// add by zhd 2016-12-21
                        //// STORY36994【南方基金】停牌股票指数收益法逻辑变更
                        if (cond.Id == "3" || cond.Id == "4" || cond.Id == "5" || cond.Id == "6" || cond.Id == "7" || cond.Id == "8" || cond.Id == "17")
                        {
                            if (cond.C_ITEM_VALUE == "0")
                            {
                                cell.Text = "否";
                            }
                            else if (cond.C_ITEM_VALUE == "1")
                            {
                                cell.Text = "是";
                            }
                                
                        }
                    }
                    ////if (cell.RelColumn.DataPropertyName.Equals("C_LOGICAL_JUDGMENT"))
                    ////{
                    ////    if (cond.Id == "1")
                    ////    {
                    ////        cell.Text = cond.C_LOGICAL_JUDGMENT;
                    ////    }
                    ////}
                }
            }

            this.tbMain.Columns[0].Width = 40;
            this.tbMain.Columns[1].Width = 80;
            this.tbMain.Columns[2].Width = 160;
            this.tbMain.Columns[3].Width = 140;
            this.tbMain.Columns[4].Width = 80;
            this.tbMain.Columns[0].CellTextAlign = ContentAlignment.MiddleCenter;
            this.tbMain.Rows[0].Cells[3].TextAlign = ContentAlignment.MiddleCenter;
                
            this.tbMain.Refresh();
        }

        /// <summary>
        /// 窗体加载方法
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SUSPEND_PARA_Load(object sender, EventArgs e)
        {
            condService = ServiceFactory.createService<ISuspendedCondService>();
            initColumnHeader();
        }

        /// <summary>
        /// 双击事件
        /// editbyliyongjun 2016-08-07 增加过滤新股信息
        /// 由于选项界面加载的数据是根据数据库控制的，这里的((SuspendedCond)cell.OwnRow.Tag).Id直接取得是数据库的ID，所以在后续开发过程中，注意对数据库中ID值的控制
        /// </summary>
        /// <param name="sender">s</param>
        /// <param name="e">e</param>
        private void tbMain_CellMouseDoubleClick(object sender, Yss.KTable.Events.CellEventArgs e)
        {
            if (sender != null && sender is Cell)
            {
                Cell cell = sender as Cell;
                ////editbyliyongjun 2016-08-07停牌股票超过天数
                ////if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("11"))
                //// add by zhd 2016-12-21
                //// STORY36994【南方基金】停牌股票指数收益法逻辑变更
                if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("12"))
                {
                    ImprovedTextBox itb = new ImprovedTextBox();
                    itb.KeyPress += new KeyPressEventHandler(this.TextBox_KeyPress);
                    itb.Text = cell.Text;
                    ////控制输入的长度
                    itb.YssLength = 22;
                    ////itb.YssNumeric = "18, 4";
                    itb.TextAlign = HorizontalAlignment.Right;
                    cell.InnerControl = itb;
                    ////给当前单元格变量赋值
                    currentCell = cell;
                }

                ////editbyliyongjun 2016-08-07采用估值行情价格
                if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("1"))
                {
                    YssSelCombox cboValue = new YssSelCombox();
                    cboValue.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
                    cboValue.QueryType = ClsConstant.CacheType;
                    cboValue.QueryCond = "SUSPENDED_COND";
                    cboValue.DisplayName = "C_DV_NAME";
                    cboValue.DisplayValue = "C_DV_CODE";
                    cboValue.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
                    cboValue.YssIsMust = false;

                    FAST.Core.BaseControl.ControlMethodInfo controlmethod = new FAST.Core.BaseControl.ControlMethodInfo();
                    controlmethod.MethodName = "getDataListByTypes";
                    controlmethod.MethodParamValues = new string[] { "SUSPENDED_COND," };
                    cboValue.MethodInfo = controlmethod;

                    cell.InnerControl = cboValue;
                    ////给当前单元格变量赋值
                    currentCell = cell;

                    if (cell.Text.Equals("调日指数估值价格"))
                    {
                        cboValue.Value = "AJUST_DAY_MP";
                    }
                    else if (cell.Text.Equals("最近指数估值价格"))
                    {
                        cboValue.Value = "RECENT_MP";
                    }
                    else if (cell.Text.Equals("停牌日指数估值价格"))
                    {
                        cboValue.Value = "SUSPEND_MP";
                    }
                }

                ////////editbyliyongjun 2016-08-07采用估值行情价格
                ////if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("2"))
                ////{
                ////        YssSelCombox cboValue = new YssSelCombox();
                ////        cboValue.YssAssociaType = AssociaType.pubvocabulary;
                ////        cboValue.QueryType = ClsConstant.CacheType;
                ////        cboValue.QueryCond = "SUSPENDED_COND";
                ////        cboValue.DisplayName = "C_DV_NAME";
                ////        cboValue.DisplayValue = "C_DV_CODE";
                ////        cboValue.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
                ////        cboValue.YssIsMust = false;

                ////        FAST.Core.BaseControl.ControlMethodInfo controlmethod = new ControlMethodInfo();
                ////        controlmethod.MethodName = "getDataListByTypes";
                ////        controlmethod.MethodParamValues = new string[] { "SUSPENDED_COND," };
                ////        cboValue.MethodInfo = controlmethod;

                ////        cell.InnerControl = cboValue;
                ////        ////给当前单元格变量赋值
                ////        currentCell = cell;

                ////        if (cell.Text.Equals("调日指数估值价格"))
                ////        {
                ////            cboValue.Value = "AJUST_DAY_MP";
                ////        }
                ////        else if (cell.Text.Equals("最近指数估值价格"))
                ////        {
                ////            cboValue.Value = "RECENT_MP";
                ////        }
                ////}

                ////editbyliyongjun 2016-08-07最近资产净值占比
                ////if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("12"))
                //// add by zhd 2016-12-21
                //// STORY36994【南方基金】停牌股票指数收益法逻辑变更
                if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("2"))
                {
                    ImprovedTextBox itb = new ImprovedTextBox();
                    itb.KeyPress += new KeyPressEventHandler(this.TextBox_KeyPress);
                    itb.Text = cell.Text;
                    ////控制输入的长度
                    itb.YssLength = 22;
                    ////itb.YssNumeric = "18, 4";
                    itb.TextAlign = HorizontalAlignment.Right;
                    cell.InnerControl = itb;
                    ////给当前单元格变量赋值
                    currentCell = cell;
                }

                ////add by zzk 20150731 STORY #24919 [紧急][招商证券]指数收益法波动比例条件缺失 
                if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("13"))
                {
                    ImprovedTextBox itb = new ImprovedTextBox();
                    itb.KeyPress += new KeyPressEventHandler(this.TextBox_KeyPress);
                    itb.Text = cell.Text;
                    ////控制输入的长度
                    itb.YssLength = 22;
                    ////itb.YssNumeric = "18, 4";
                    itb.TextAlign = HorizontalAlignment.Right;
                    cell.InnerControl = itb;
                    ////给当前单元格变量赋值
                    currentCell = cell;
                }

                ////editbyliyongjun 2016-08-07行业指数累计涨跌幅
                if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("14"))
                {
                    ImprovedTextBox itb = new ImprovedTextBox();
                    itb.KeyPress += new KeyPressEventHandler(this.TextBox_KeyPress);
                    itb.Text = cell.Text;
                    ////控制输入的长度
                    itb.YssLength = 22;
                    ////itb.YssNumeric = "18, 4";
                    itb.TextAlign = HorizontalAlignment.Right;
                    cell.InnerControl = itb;
                    ////给当前单元格变量赋值
                    currentCell = cell;
                }

                ////if (cell.RelColumn.DataPropertyName.Equals("C_LOGICAL_JUDGMENT") && (((SuspendedCond)cell.OwnRow.Tag).Id.Equals("8") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("9") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("10") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("11") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("12") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("13") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("14") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("15") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("16")))
                //// add by zhd 2016-12-21
                //// STORY36994【南方基金】停牌股票指数收益法逻辑变更
                if (cell.RelColumn.DataPropertyName.Equals("C_LOGICAL_JUDGMENT") && (((SuspendedCond)cell.OwnRow.Tag).Id.Equals("9") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("10") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("11") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("12") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("2") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("13") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("14") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("15") || ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("16")))
                {
                    YssSelCombox cboValue = new YssSelCombox();
                    cboValue.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
                    cboValue.QueryType = ClsConstant.CacheType;
                    cboValue.QueryCond = "LJ";
                    cboValue.DisplayName = "C_DV_NAME";
                    cboValue.DisplayValue = "C_DV_CODE";
                    cboValue.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
                    cboValue.YssIsMust = false;

                    FAST.Core.BaseControl.ControlMethodInfo controlmethod = new ControlMethodInfo();
                    controlmethod.MethodName = "getDataListByTypes";
                    controlmethod.MethodParamValues = new string[] { "LJ," };
                    cboValue.MethodInfo = controlmethod;

                    cell.InnerControl = cboValue;
                    ////给当前单元格变量赋值
                    currentCell = cell;

                    if (cell.Text.Equals("并且"))
                    {
                        cboValue.Value = "LJ_AND";
                    }
                    else if (cell.Text.Equals("或者"))
                    {
                        cboValue.Value = "LJ_OR";
                    }
                    else if (cell.Text.Equals("不启用"))
                    {
                        cboValue.Value = "LJ_NOT_ENABLED";
                    }
                }

                ////editbyliyongjun 2016-08-07停牌股票超过天数
                ////if (cell.RelColumn.DataPropertyName.Equals("C_VALUE_TYPE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("11"))
                //// add by zhd 2016-12-21
                //// STORY36994【南方基金】停牌股票指数收益法逻辑变更
                if (cell.RelColumn.DataPropertyName.Equals("C_VALUE_TYPE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("12"))
                {
                    YssSelCombox cboValue = new YssSelCombox();
                    cboValue.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
                    cboValue.QueryType = ClsConstant.CacheType;
                    cboValue.QueryCond = "VALUE_TYPE";
                    cboValue.DisplayName = "C_DV_NAME";
                    cboValue.DisplayValue = "C_DV_CODE";
                    cboValue.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
                    cboValue.YssIsMust = false;

                    FAST.Core.BaseControl.ControlMethodInfo controlmethod = new ControlMethodInfo();
                    controlmethod.MethodName = "getDataListByTypes";
                    controlmethod.MethodParamValues = new string[] { "VALUE_TYPE," };
                    cboValue.MethodInfo = controlmethod;

                    cell.InnerControl = cboValue;
                    ////给当前单元格变量赋值
                    currentCell = cell;

                    if (cell.Text.Equals("工作日"))
                    {
                        cboValue.Value = "VT_WORKDAY";
                    }
                    else if (cell.Text.Equals("自然日"))
                    {
                        cboValue.Value = "VT_NATURAL";
                    }
                   
                }

                //// add by zhd 2016-11-29
                //// STORY32313停牌股票信息生成做成公共层面，不关联组合
                //// 最近一个停牌日>=T-
                ////if (cell.RelColumn.DataPropertyName.Equals("C_VALUE_TYPE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("10"))
                //// add by zhd 2016-12-21
                //// STORY36994【南方基金】停牌股票指数收益法逻辑变更
                if (cell.RelColumn.DataPropertyName.Equals("C_VALUE_TYPE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("11"))
                {
                    YssSelCombox cboValue = new YssSelCombox();
                    cboValue.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
                    cboValue.QueryType = ClsConstant.CacheType;
                    cboValue.QueryCond = "VALUE_TYPE";
                    cboValue.DisplayName = "C_DV_NAME";
                    cboValue.DisplayValue = "C_DV_CODE";
                    cboValue.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
                    cboValue.YssIsMust = false;

                    FAST.Core.BaseControl.ControlMethodInfo controlmethod = new FAST.Core.BaseControl.ControlMethodInfo();
                    controlmethod.MethodName = "getDataListByTypes";
                    controlmethod.MethodParamValues = new string[] { "VALUE_TYPE," };
                    cboValue.MethodInfo = controlmethod;

                    cell.InnerControl = cboValue;
                    ////给当前单元格变量赋值
                    currentCell = cell;

                    if (cell.Text.Equals("工作日"))
                    {
                        cboValue.Value = "VT_WORKDAY";
                    }
                    else if (cell.Text.Equals("自然日"))
                    {
                        cboValue.Value = "VT_NATURAL";
                    }

                }

                if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_NAME") && (((SuspendedCond)cell.OwnRow.Tag).C_ITEM_CODE.Equals("TPJGSZ_DRZCJZ_RATE") || ((SuspendedCond)cell.OwnRow.Tag).C_ITEM_CODE.Equals("TPJGSZHZ_DRZCJZ_RATE")))
                {
                    YssSelCombox cboValue = new YssSelCombox();
                    cboValue.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
                    cboValue.QueryType = ClsConstant.CacheType;
                    cboValue.QueryCond = "SUSPENED_COND_JZB";
                    cboValue.DisplayName = "C_DV_NAME";
                    cboValue.DisplayValue = "C_DV_CODE";
                    cboValue.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
                    cboValue.YssIsMust = false;

                    FAST.Core.BaseControl.ControlMethodInfo controlmethod = new FAST.Core.BaseControl.ControlMethodInfo();
                    controlmethod.MethodName = "getDataListByTypes";
                    controlmethod.MethodParamValues = new string[] { "SUSPENED_COND_JZB," };
                    cboValue.MethodInfo = controlmethod;

                    cell.InnerControl = cboValue;
                    ////给当前单元格变量赋值
                    currentCell = cell;

                    if (cell.Text.Equals("停牌价格市值占资产净值比"))
                    {
                        cboValue.Value = "TPJGSZ_DRZCJZ_RATE";
                    }
                    else if (cell.Text.Equals("停牌价格市值汇总占资产净值比"))
                    {
                        cboValue.Value = "TPJGSZHZ_DRZCJZ_RATE";
                    }

                }

                //// 过滤新股信息
                ////if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("2"))
                //// add by zhd 2016-12-21
                //// STORY36994【南方基金】停牌股票指数收益法逻辑变更
                if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("3"))
                {
                    YssSelCombox cboValue = new YssSelCombox();
                    cboValue.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
                    cboValue.QueryType = ClsConstant.CacheType;
                    cboValue.QueryCond = "BOOL_TYPE";
                    cboValue.DisplayName = "C_DV_NAME";
                    cboValue.DisplayValue = "C_DV_CODE";
                    cboValue.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
                    cboValue.YssIsMust = false;

                    FAST.Core.BaseControl.ControlMethodInfo controlmethod = new ControlMethodInfo();
                    controlmethod.MethodName = "getDataListByTypes";
                    controlmethod.MethodParamValues = new string[] { "BOOL_TYPE," };
                    cboValue.MethodInfo = controlmethod;

                    cell.InnerControl = cboValue;
                    ////给当前单元格变量赋值
                    currentCell = cell;

                    if (cell.Text.Equals("是"))
                    {
                        cboValue.Value = "1";
                    }
                    else if (cell.Text.Equals("否"))
                    {
                        cboValue.Value = "0";
                    }
                }

                ////if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("3"))
                //// add by zhd 2016-12-21
                //// STORY36994【南方基金】停牌股票指数收益法逻辑变更
                if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("4"))
                {
                    YssSelCombox cboValue = new YssSelCombox();
                    cboValue.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
                    cboValue.QueryType = ClsConstant.CacheType;
                    cboValue.QueryCond = "BOOL_TYPE";
                    cboValue.DisplayName = "C_DV_NAME";
                    cboValue.DisplayValue = "C_DV_CODE";
                    cboValue.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
                    cboValue.YssIsMust = false;

                    FAST.Core.BaseControl.ControlMethodInfo controlmethod = new FAST.Core.BaseControl.ControlMethodInfo();
                    controlmethod.MethodName = "getDataListByTypes";
                    controlmethod.MethodParamValues = new string[] { "BOOL_TYPE," };
                    cboValue.MethodInfo = controlmethod;

                    cell.InnerControl = cboValue;
                    ////给当前单元格变量赋值
                    currentCell = cell;

                    if (cell.Text.Equals("是"))
                    {
                        cboValue.Value = "1";
                    }
                    else if (cell.Text.Equals("否"))
                    {
                        cboValue.Value = "0";
                    }
                }

                ////if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("4"))
                //// add by zhd 2016-12-21
                //// STORY36994【南方基金】停牌股票指数收益法逻辑变更
                if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("5"))
                {
                    YssSelCombox cboValue = new YssSelCombox();
                    cboValue.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
                    cboValue.QueryType = ClsConstant.CacheType;
                    cboValue.QueryCond = "BOOL_TYPE";
                    cboValue.DisplayName = "C_DV_NAME";
                    cboValue.DisplayValue = "C_DV_CODE";
                    cboValue.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
                    cboValue.YssIsMust = false;

                    FAST.Core.BaseControl.ControlMethodInfo controlmethod = new FAST.Core.BaseControl.ControlMethodInfo();
                    controlmethod.MethodName = "getDataListByTypes";
                    controlmethod.MethodParamValues = new string[] { "BOOL_TYPE," };
                    cboValue.MethodInfo = controlmethod;

                    cell.InnerControl = cboValue;
                    ////给当前单元格变量赋值
                    currentCell = cell;

                    if (cell.Text.Equals("是"))
                    {
                        cboValue.Value = "1";
                    }
                    else if (cell.Text.Equals("否"))
                    {
                        cboValue.Value = "0";
                    }
                }

                ////if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("5"))
                //// add by zhd 2016-12-21
                //// STORY36994【南方基金】停牌股票指数收益法逻辑变更
                if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("6"))
                {
                    YssSelCombox cboValue = new YssSelCombox();
                    cboValue.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
                    cboValue.QueryType = ClsConstant.CacheType;
                    cboValue.QueryCond = "BOOL_TYPE";
                    cboValue.DisplayName = "C_DV_NAME";
                    cboValue.DisplayValue = "C_DV_CODE";
                    cboValue.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
                    cboValue.YssIsMust = false;

                    FAST.Core.BaseControl.ControlMethodInfo controlmethod = new FAST.Core.BaseControl.ControlMethodInfo();
                    controlmethod.MethodName = "getDataListByTypes";
                    controlmethod.MethodParamValues = new string[] { "BOOL_TYPE," };
                    cboValue.MethodInfo = controlmethod;

                    cell.InnerControl = cboValue;
                    ////给当前单元格变量赋值
                    currentCell = cell;

                    if (cell.Text.Equals("是"))
                    {
                        cboValue.Value = "1";
                    }
                    else if (cell.Text.Equals("否"))
                    {
                        cboValue.Value = "0";
                    }
                }

                ////if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("6"))
                //// add by zhd 2016-12-21
                //// STORY36994【南方基金】停牌股票指数收益法逻辑变更
                if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("7"))
                {
                    YssSelCombox cboValue = new YssSelCombox();
                    cboValue.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
                    cboValue.QueryType = ClsConstant.CacheType;
                    cboValue.QueryCond = "BOOL_TYPE";
                    cboValue.DisplayName = "C_DV_NAME";
                    cboValue.DisplayValue = "C_DV_CODE";
                    cboValue.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
                    cboValue.YssIsMust = false;

                    FAST.Core.BaseControl.ControlMethodInfo controlmethod = new FAST.Core.BaseControl.ControlMethodInfo();
                    controlmethod.MethodName = "getDataListByTypes";
                    controlmethod.MethodParamValues = new string[] { "BOOL_TYPE," };
                    cboValue.MethodInfo = controlmethod;

                    cell.InnerControl = cboValue;
                    ////给当前单元格变量赋值
                    currentCell = cell;

                    if (cell.Text.Equals("是"))
                    {
                        cboValue.Value = "1";
                    }
                    else if (cell.Text.Equals("否"))
                    {
                        cboValue.Value = "0";
                    }
                }

                ////if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("7"))
                //// add by zhd 2016-12-21
                //// STORY36994【南方基金】停牌股票指数收益法逻辑变更
                if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("8"))
                {
                    YssSelCombox cboValue = new YssSelCombox();
                    cboValue.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
                    cboValue.QueryType = ClsConstant.CacheType;
                    cboValue.QueryCond = "BOOL_TYPE";
                    cboValue.DisplayName = "C_DV_NAME";
                    cboValue.DisplayValue = "C_DV_CODE";
                    cboValue.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
                    cboValue.YssIsMust = false;

                    FAST.Core.BaseControl.ControlMethodInfo controlmethod = new FAST.Core.BaseControl.ControlMethodInfo();
                    controlmethod.MethodName = "getDataListByTypes";
                    controlmethod.MethodParamValues = new string[] { "BOOL_TYPE," };
                    cboValue.MethodInfo = controlmethod;

                    cell.InnerControl = cboValue;
                    ////给当前单元格变量赋值
                    currentCell = cell;

                    if (cell.Text.Equals("是"))
                    {
                        cboValue.Value = "1";
                    }
                    else if (cell.Text.Equals("否"))
                    {
                        cboValue.Value = "0";
                    }
                }

                if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("17"))
                {
                    YssSelCombox cboValue = new YssSelCombox();
                    cboValue.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
                    cboValue.QueryType = ClsConstant.CacheType;
                    cboValue.QueryCond = "BOOL_TYPE";
                    cboValue.DisplayName = "C_DV_NAME";
                    cboValue.DisplayValue = "C_DV_CODE";
                    cboValue.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Name;
                    cboValue.YssIsMust = false;

                    FAST.Core.BaseControl.ControlMethodInfo controlmethod = new FAST.Core.BaseControl.ControlMethodInfo();
                    controlmethod.MethodName = "getDataListByTypes";
                    controlmethod.MethodParamValues = new string[] { "BOOL_TYPE," };
                    cboValue.MethodInfo = controlmethod;

                    cell.InnerControl = cboValue;
                    ////给当前单元格变量赋值
                    currentCell = cell;

                    if (cell.Text.Equals("是"))
                    {
                        cboValue.Value = "1";
                    }
                    else if (cell.Text.Equals("否"))
                    {
                        cboValue.Value = "0";
                    }
                }

                ////if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("8"))
                //// add by zhd 2016-12-21
                //// STORY36994【南方基金】停牌股票指数收益法逻辑变更
                if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("9"))
                {
                    ImprovedTextBox itb = new ImprovedTextBox();
                    itb.KeyPress += new KeyPressEventHandler(this.TextBox_KeyPress);
                    itb.Text = cell.Text;
                    ////控制输入的长度
                    itb.YssLength = 22;
                    ////itb.YssNumeric = "18, 4";
                    itb.TextAlign = HorizontalAlignment.Right;
                    cell.InnerControl = itb;
                    ////给当前单元格变量赋值
                    currentCell = cell;
                }

                ////if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("9"))
                //// add by zhd 2016-12-21
                //// STORY36994【南方基金】停牌股票指数收益法逻辑变更
                if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("10"))
                {
                    ImprovedTextBox itb = new ImprovedTextBox();
                    itb.KeyPress += new KeyPressEventHandler(this.TextBox_KeyPress);
                    itb.Text = cell.Text;
                    ////控制输入的长度
                    itb.YssLength = 22;
                    ////itb.YssNumeric = "18, 4";
                    itb.TextAlign = HorizontalAlignment.Right;
                    cell.InnerControl = itb;
                    ////给当前单元格变量赋值
                    currentCell = cell;
                }

                ////if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("10"))
                //// add by zhd 2016-12-21
                //// STORY36994【南方基金】停牌股票指数收益法逻辑变更
                if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("11"))
                {
                    ImprovedTextBox itb = new ImprovedTextBox();
                    itb.KeyPress += new KeyPressEventHandler(this.TextBox_KeyPress);
                    itb.Text = cell.Text;
                    ////控制输入的长度
                    itb.YssLength = 22;
                    ////itb.YssNumeric = "18, 4";
                    itb.TextAlign = HorizontalAlignment.Right;
                    cell.InnerControl = itb;
                    ////给当前单元格变量赋值
                    currentCell = cell;
                }

                if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("15"))
                {
                    ImprovedTextBox itb = new ImprovedTextBox();
                    itb.KeyPress += new KeyPressEventHandler(this.TextBox_KeyPress);
                    itb.Text = cell.Text;
                    ////控制输入的长度
                    itb.YssLength = 22;
                    ////itb.YssNumeric = "18, 4";
                    itb.TextAlign = HorizontalAlignment.Right;
                    cell.InnerControl = itb;
                    ////给当前单元格变量赋值
                    currentCell = cell;
                }

                if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("16"))
                {
                    ImprovedTextBox itb = new ImprovedTextBox();
                    itb.KeyPress += new KeyPressEventHandler(this.TextBox_KeyPress);
                    itb.Text = cell.Text;
                    ////控制输入的长度
                    itb.YssLength = 22;
                    ////itb.YssNumeric = "18, 4";
                    itb.TextAlign = HorizontalAlignment.Right;
                    cell.InnerControl = itb;
                    ////给当前单元格变量赋值
                    currentCell = cell;
                }

            }
        }

        /// <summary>
        /// 页面点击事件
        ///BUG #144030 股票停牌新增报错
        /// </summary>
        /// <param name="sender">s</param>
        /// <param name="e">e</param>
        private void tbMain_Click(object sender, EventArgs e)
        {
            hideSelCombox();
        }
        

        /// <summary>
        /// 设置不能数据负数
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void TextBox_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (e.KeyChar == '-')
            {
                e.Handled = true;
            }
        }

        /// <summary>
        /// 规则设置列表界面初始化
        /// </summary>
        /// <param name="cell">cell</param>
        private void initCurrentCell(Cell cell)
        {
            try
            {
                if (null == cell)
                {
                    return;
                }

                if (null == cell.InnerControl)
                {
                    return;
                }

                if (null == cell.RelColumn)
                {
                    return;
                }

                if (null == cell.OwnRow)
                {
                    return;
                }

                Column currentCol = cell.RelColumn;

                if (currentCol.DataPropertyName.Equals("C_ITEM_VALUE") && !((SuspendedCond)cell.OwnRow.Tag).Id.Equals("1"))
                {
                    ImprovedTextBox itb = cell.InnerControl as ImprovedTextBox;
                    cell.Text = itb.Text;
                    cell.TextAlign = System.Drawing.ContentAlignment.MiddleRight;
                    itb.Dock = DockStyle.None;
                    itb.Visible = true;
                    itb.Enabled = true;
                    cell.CellEditStatus = true;
                    cell.Selected = true;
                  
                }
                else if (currentCol.DataPropertyName.Equals("C_ITEM_VALUE"))
                {
                    YssSelCombox comBox = cell.InnerControl as YssSelCombox;
                    IVocDataService vocSvc = DataServiceFactory.createService<IVocDataService>();
                    cell.Text = vocSvc.getKeyConvertMap()[comBox.Value];
                    cell.Tag = comBox.Value;
                }
                else if (currentCol.DataPropertyName.Equals("C_ITEM_NAME"))
                {
                    YssSelCombox comBox = cell.InnerControl as YssSelCombox;
                    cell.Text = comBox.Text;
                    cell.Tag = comBox.Value;
                }

                if (currentCol.DataPropertyName.Equals("C_LOGICAL_JUDGMENT") && !((SuspendedCond)cell.OwnRow.Tag).Id.Equals("1"))
                {
                    YssSelCombox comBox = cell.InnerControl as YssSelCombox;
                    IVocDataService vocSvc = DataServiceFactory.createService<IVocDataService>();
                    cell.Text = vocSvc.getKeyConvertMap()[comBox.Value];
                    cell.Tag = comBox.Value;
                }

                ////if (currentCol.DataPropertyName.Equals("C_VALUE_TYPE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("2"))
                //// add by zhd 2016-12-21
                //// STORY36994【南方基金】停牌股票指数收益法逻辑变更
                if (currentCol.DataPropertyName.Equals("C_VALUE_TYPE") && ((SuspendedCond)cell.OwnRow.Tag).Id.Equals("3"))
                {
                    YssSelCombox comBox = cell.InnerControl as YssSelCombox;
                    IVocDataService vocSvc = DataServiceFactory.createService<IVocDataService>();
                    cell.Text = vocSvc.getKeyConvertMap()[comBox.Value];
                    cell.Tag = comBox.Value;
                }
               
                this.tbMain.AutoColumnWidth = true;
                ////cell.InnerControl.Dispose();
                ////cell.InnerControl = null;
                this.tbMain.Refresh();

            }
            catch (Exception ex)
            {
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 隐藏控件
        /// </summary>
        private void hideSelCombox()
        {
            foreach (Row row in tbMain.Rows)
            {
                if (row.Cells.Count > 1 && row.Cells[1].InnerControl != null)
                {
                    if (row.Cells[1].InnerControl is YssSelCombox)
                    {
                        YssSelCombox com = row.Cells[1].InnerControl as YssSelCombox;
                        com.Visible = false;
                        row.Cells[1].InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                        if (com.SelectedItem != null)
                        {
                            row.Cells[1].Text = com.SelectedItem.DisplayName;
                        }
                    }
                }
                
                if (row.Cells.Count > 2 && row.Cells[3].InnerControl != null)
                {
                    if (row.Cells[3].InnerControl is FAST.Core.BaseControl.YssSelCombox)
                    {
                        FAST.Core.BaseControl.YssSelCombox com = row.Cells[3].InnerControl as FAST.Core.BaseControl.YssSelCombox;
                        com.Visible = false;
                        row.Cells[3].InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                        if (com.SelectedItem != null)
                        {
                            row.Cells[3].Text = com.SelectedItem.DisplayName;
                        }
                    }
                    else if (row.Cells[3].InnerControl is Yss.KRichEx.YssTextBox)
                    {
                        Yss.KRichEx.YssTextBox box = row.Cells[3].InnerControl as Yss.KRichEx.YssTextBox;
                        box.Visible = false;
                        row.Cells[3].Text = box.Text;
                        row.Cells[3].InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                    }
                    else if (row.Cells[3].InnerControl is Yss.KRichEx.ImprovedTextBox)
                    {
                        Yss.KRichEx.ImprovedTextBox box = row.Cells[3].InnerControl as Yss.KRichEx.ImprovedTextBox;
                        box.Visible = false;
                        row.Cells[3].Text = box.Text;
                        row.Cells[3].InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                    }
                }

                if (row.Cells.Count > 2 && row.Cells[1].InnerControl != null)
                {
                    if (row.Cells[1].InnerControl is FAST.Core.BaseControl.YssSelCombox)
                    {
                        FAST.Core.BaseControl.YssSelCombox com = row.Cells[1].InnerControl as FAST.Core.BaseControl.YssSelCombox;
                        com.Visible = false;
                        row.Cells[1].InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                        if (com.SelectedItem != null)
                        {
                            row.Cells[1].Text = com.SelectedItem.DisplayName;
                        }
                    }
                 }

                if (row.Cells.Count > 2 && row.Cells[2].InnerControl != null)
                {
                    if (row.Cells[2].InnerControl is YssSelCombox)
                    {
                        YssSelCombox com = row.Cells[2].InnerControl as YssSelCombox;
                        com.Visible = false;
                        row.Cells[2].InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                        if (com.SelectedItem != null)
                        {
                            row.Cells[2].Text = com.SelectedItem.DisplayName;
                        }
                    }
                }

                if (row.Cells.Count > 2 && row.Cells[4].InnerControl != null)
                {
                    if (row.Cells[4].InnerControl is FAST.Core.BaseControl.YssSelCombox)
                    {
                        FAST.Core.BaseControl.YssSelCombox com = row.Cells[4].InnerControl as FAST.Core.BaseControl.YssSelCombox;
                        com.Visible = false;
                        row.Cells[4].InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                        if (com.SelectedItem != null)
                        {
                            row.Cells[4].Text = com.SelectedItem.DisplayName;
                        }
                    }
                }
            }
        }

        /// <summary>
        /// 单击事件
        /// </summary>
        /// <param name="sender">s</param>
        /// <param name="e">e</param>
        private void tbMain_CellMouseClick(object sender, Yss.KTable.Events.CellEventArgs e)
        {
            if (sender != null && sender is Cell)
            {
                Cell cell = sender as Cell;
                hideSelCombox();
                /////如果选择 的是可编辑列 
                if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_VALUE") || cell.RelColumn.DataPropertyName.Equals("C_ITEM_NAME"))
                {
                    this.tbMain_CellMouseDoubleClick(sender, null);
                    cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowAlways;
                    tbMain.Refresh();
                    ////cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowAlways;
                    ////this.tbMain.Refresh();
                }

                if (cell.RelColumn.DataPropertyName.Equals("C_LOGICAL_JUDGMENT"))
                {
                    this.tbMain_CellMouseDoubleClick(sender, null);
                    cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowAlways;
                    tbMain.Refresh();
                    ////cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowAlways;
                    ////this.tbMain.Refresh();
                }

                if (cell.RelColumn.DataPropertyName.Equals("C_VALUE_TYPE"))
                {
                    this.tbMain_CellMouseDoubleClick(sender, null);
                    cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowAlways;
                    tbMain.Refresh();
                    ////cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowAlways;
                    ////this.tbMain.Refresh();
                }

                if (cell.RelColumn.DataPropertyName.Equals("C_ITEM_NAME"))
                {
                    this.tbMain_CellMouseDoubleClick(sender, null);
                    cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowAlways;
                    tbMain.Refresh();
                    ////cell.InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowAlways;
                    ////this.tbMain.Refresh();
                }
              
                ////currentCell.CellEditStatus = true;
                ////currentCell.Text = "100";
            }
        }
    }
}


