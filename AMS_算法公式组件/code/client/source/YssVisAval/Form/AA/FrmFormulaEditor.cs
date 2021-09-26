using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Platform.Menu.Pojo;
using FAST.Platform.Logger.Pojo;
using FAST.Platform.Safe.Pojo;
using FAST.Platform.Talk.Pojo;

using FAST.Core.Exceptions;


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
using YssDevComponents.DotNetBar;
using YssVisAval.Pojo.AA;


using System.Text.RegularExpressions;
using FAST.Core.Context;
using Yss.KTable.Models;

using YssDevComponents.DotNetBar.Controls;


using YssVisAval.service;
using YssVisAval.Fun;



namespace YssVisAval.Form.AA
{
    /// <summary>
    /// 公式编辑器窗体
    /// </summary>
    public partial class FrmFormulaEditor : FrmBase
    {
        /// <summary>
        /// 高级算法的set窗体
        /// </summary>
        private Frm_ADVANCED_ALGORITHM_S frmAlgoSet = null;

        /// <summary>
        /// 操作类型
        /// </summary>
        private string operType = null;

        /// <summary>
        /// 服务
        /// </summary>
        private IVisAdvAlgoService service = null;
        
        /// <summary>
        /// 构造方法
        /// </summary>
        public FrmFormulaEditor()
        {
            InitializeComponent();
        }

        /// <summary>
        /// 构造方法
        /// </summary>
        /// <param name="form">高级算法的set窗体</param>
        public FrmFormulaEditor(Frm_ADVANCED_ALGORITHM_S form)
        {
            InitializeComponent();
            frmAlgoSet = form;
            service = frmAlgoSet.getService();
        }

        /// <summary>
        /// 用于在子窗体中实现的窗体加载功能
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void FrmBase_Load(object sender, EventArgs e)
        {
            loadFuncKeyWords();
        }

        /// <summary>
        /// 加载A区的数据
        /// </summary>
        private void loadFuncKeyWords() 
        {
            // ClsBaseDataAdmin dateAdmin = new ClsBaseDataAdmin();
            try
            {
                // 向后台取数据
                // object obj = dateAdmin.GetSpecValue("", "getAllDate", "AdvancedAlgorithm");
                object obj = this.service.getAllDate();
                if (null != obj)
                {
                    loadLeftMai(obj.ToString());
                    this.tbFuncKeyWords.Refresh();
                }
            }
            catch (Exception ex)
            {
                SysFun clsbaseFun = new SysFun();
                clsbaseFun.C_FUN_CODE = "AdvancedAlgorithm";
                clsbaseFun.YssAssocia = ClsClzCfgMgr.getAssociaParam(clsbaseFun.C_FUN_CODE);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtError("009", clsbaseFun, status));
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 加载左边区域的数据
        /// </summary>
        /// <param name="str">需要加载的数据</param>
        private void loadLeftMai(string str)
        {
            // / 显示模式为树形结构时，存放key值为节点代码，value值为Row对象的数据，防止重复的数据
            Dictionary<string, Yss.KTable.Models.Row> htView = new Dictionary<string, Yss.KTable.Models.Row>();
            Row row = null; // 行
            Cell cell = null; // 列
            Cls_KEYWORDSFUNC clsKeyWordFunc = null;
            try
            {
                // 清空所有的行的数据
                this.tbFuncKeyWords.Rows.Clear();

                // 添加函数的父行
                row = new Row();
                cell = new Cell();
                cell.Text = "函数";
                cell.TextAlign = ContentAlignment.MiddleLeft; // 设置text显示的位置
                row.Cells.Add(cell);
                htView.Add(ClsBizCons.FUNC_TYPE, row);
                this.tbFuncKeyWords.Rows.Add(row);

        //// delete by zhaoxianlin 20130506 STORY #3351 算法公式的优化--start
                //// 添加关键字的父行
                //// row = new Row();

        //// cell = new Cell();

        //// cell.Text = "关键字";

        //// cell.TextAlign = ContentAlignment.MiddleLeft; // 设置text显示的位置
                //// row.Cells.Add(cell);

        //// htView.Add(ClsBizCons.KEY_TYPE, row);

        //// this.tbFuncKeyWords.Rows.Add(row);

        //// delete by zhaoxianlin 20130506 STORY #3351 算法公式的优化--end
                string[] strArray = Regex.Split(str, ClsConstant.YSS_PASSAGESPLITMARK);
                foreach (string strDate in strArray)
                {
                    clsKeyWordFunc = new Cls_KEYWORDSFUNC();
                    clsKeyWordFunc.parseRowStr(strDate);
                    row = new Row();
                    cell = new Cell();
                    row.Tag = clsKeyWordFunc;
                    cell.Text = clsKeyWordFunc.C_NAME;
                    cell.TextAlign = ContentAlignment.MiddleLeft;
                    row.Cells.Add(cell);

        //// 定义，如果KTABLE类型为NoIndentWithIcon则不显示图片
                    htView[clsKeyWordFunc.C_TYPE].SubRows.Add(row);
                    
                }
            }
            catch (Exception ex)
            {
                SysFun clsbaseFun = new SysFun();
                clsbaseFun.C_FUN_CODE = "AdvancedAlgorithm";
                clsbaseFun.YssAssocia = ClsClzCfgMgr.getAssociaParam(clsbaseFun.C_FUN_CODE);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtError("009", clsbaseFun, status));
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 关闭窗体
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        /// <summary>
        /// 行单击事件
        /// 向后台查询数据
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void tbFuncKeyWords_RowClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            Row row = null; // 行事件
            Cls_KEYWORDSFUNC clsKeyWordsFunc = null;

        //// ClsBaseDataAdmin dateAdmin = new ClsBaseDataAdmin();
            ClsQuyStrUtil quyStrUtil = null;
            object obj = null;
            try
            {
                row = sender as Row;
                this.txtDesc.Text = ""; // 清空描述的数据
                //// 如果选中的是父节点直接返回
                if (null == row.Tag)
                {
                    return;
                }

                this.tbShowInfo.Rows.Clear(); // 清空行
                clsKeyWordsFunc = row.Tag as Cls_KEYWORDSFUNC;
                quyStrUtil = new ClsQuyStrUtil();
                if (ClsBizCons.FUNC_TYPE.Equals(clsKeyWordsFunc.C_TYPE))
                {
                    operType = ClsBizCons.FUNC_TYPE;
                    quyStrUtil.addQuyCon("C_DV_FUNC_TYPE", clsKeyWordsFunc.C_CODE, ClsConstant.SQL_RA_HYPHEN_EQUAL);

        ////obj = dateAdmin.GetSpecValue(quyStrUtil.getQuyStr(), "getFunc", "AdvancedAlgorithm"); // 获取对应的算法
                    obj = this.service.getFunc(clsKeyWordsFunc.C_CODE);
                }
            else
            {
                    operType = ClsBizCons.KEY_TYPE;
                    quyStrUtil.addQuyCon("C_DV_KEY_TYPE", clsKeyWordsFunc.C_CODE, ClsConstant.SQL_RA_HYPHEN_EQUAL);

        ////obj = dateAdmin.GetSpecValue(quyStrUtil.getQuyStr(), "getKeyWords", "AdvancedAlgorithm"); // 获取对应的算法
                    obj = this.service.getKeyWords(clsKeyWordsFunc.C_CODE);

                }

                //// 如果当前为空直接返回
                if (null == obj || obj.ToString().Trim().Length == 0)
                {
                    this.tbShowInfo.Refresh();
                    return;
                }

                ////加载B区的数据
                loadRiightMain(obj.ToString());
                this.tbShowInfo.Refresh();

            }
            catch (Exception ex)
            {
                SysFun clsbaseFun = new SysFun();
                clsbaseFun.C_FUN_CODE = "AdvancedAlgorithm";
                clsbaseFun.YssAssocia = ClsClzCfgMgr.getAssociaParam(clsbaseFun.C_FUN_CODE);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtError("008", clsbaseFun, status));
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 加载B区的数据
        /// </summary>
        /// <param name="str">str</param>
        private void loadRiightMain(string str)
        {
            // // 如果当前数据为函数加载函数数据否则
            ////加载关键字数据
            if (ClsBizCons.FUNC_TYPE.Equals(operType))
            {
                loadFuncTable(str);
            }
            else
            {
                loadKeyWordsTable(str);
            }
        }

        /// <summary>
        /// 加载函数的table
        /// </summary>
        /// <param name="str">str</param>
        private void loadFuncTable(string str)
        {
            string[] showDate = null; // 展示的数据
            ClsFunc clsFunc = null; // 函数
            Row row = null; // 行
            Cell cell = null; // 列
            try
            {
                showDate = Regex.Split(str, "\r\f"); // 获取数据对象
                //// 如果没有数据直接返回
                if (null == showDate || 0 == showDate.Length)
                {
                    return;
                 }

        ////循环加载数据
                foreach (string strDate in showDate)
                {
                    clsFunc = new ClsFunc();
                    clsFunc.parseStr(strDate);
                    row = new Row();
                    cell = new Cell();
                    cell.TextAlign = ContentAlignment.MiddleLeft;
                    cell.Text = clsFunc.C_FUNC_CODE;
                    row.Cells.Add(cell);
                    cell = new Cell();
                    cell.TextAlign = ContentAlignment.MiddleLeft;
                    cell.Text = clsFunc.C_FUNC_NAME;
                    row.Cells.Add(cell);
                    row.Tag = clsFunc;
                    this.tbShowInfo.Rows.Add(row);
                }
            }
            catch (Exception ex)
            {
                SysFun clsbaseFun = new SysFun();
                clsbaseFun.C_FUN_CODE = "AdvancedAlgorithm";
                clsbaseFun.YssAssocia = ClsClzCfgMgr.getAssociaParam(clsbaseFun.C_FUN_CODE);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtError("007", clsbaseFun, status));
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 加载关键字的table
        /// </summary>
        /// <param name="str">str</param>
        private void loadKeyWordsTable(string str)
        {
            string[] showDate = null; // 展示的数据
            ClsKeyWord clsKeyWord = null; // 函数
            Row row = null; // 行
            Cell cell = null; // 列
            try
            {
                showDate = Regex.Split(str, "\r\f"); // 获取数据对象
                //// 如果没有数据直接返回
                if (null == showDate || 0 == showDate.Length)
                {
                    return;
                 }

        ////循环加载数据
                foreach (string strDate in showDate)
                {
                    clsKeyWord = new ClsKeyWord();
                    clsKeyWord.parseStr(strDate);
                    row = new Row();
                    cell = new Cell();
                    cell.TextAlign = ContentAlignment.MiddleLeft;
                    cell.Text = clsKeyWord.KeyWordCode;
                    row.Cells.Add(cell);
                    cell = new Cell();
                    cell.TextAlign = ContentAlignment.MiddleLeft;
                    cell.Text = clsKeyWord.KeyWordName;
                    row.Cells.Add(cell);
                    row.Tag = clsKeyWord;
                    this.tbShowInfo.Rows.Add(row);
                }
            }
            catch (Exception ex)
            {
                SysFun clsbaseFun = new SysFun();
                clsbaseFun.C_FUN_CODE = "AdvancedAlgorithm";
                clsbaseFun.YssAssocia = ClsClzCfgMgr.getAssociaParam(clsbaseFun.C_FUN_CODE);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtError("006", clsbaseFun, status));
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 行单击事件，把数据显示在描述框中
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void tbShowInfo_RowClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            try
            {
                if (ClsBizCons.KEY_TYPE.Equals(operType))
                {
                    this.txtDesc.Text = (((Row)sender).Tag as ClsKeyWord).KeyWordDesc;
                }
                else 
                {
                    initFuncDesc(((Row)sender).Tag as ClsFunc); // 加载描述
                }
            }
            catch (Exception ex)
            {
                SysFun clsbaseFun = new SysFun();
                clsbaseFun.C_FUN_CODE = "AdvancedAlgorithm";
                clsbaseFun.YssAssocia = ClsClzCfgMgr.getAssociaParam(clsbaseFun.C_FUN_CODE);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtError("005", clsbaseFun, status));
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 加载函数的描述
        /// </summary>
        /// <param name="clsFunc">函数对象</param>
        private void initFuncDesc(ClsFunc clsFunc) 
        {
            try
            {
                this.txtDesc.Text = clsFunc.C_DESC + "\r\n";
                foreach (Cls_FUNC_PARA clspara in clsFunc.ParaList)
                {
                    this.txtDesc.Text += "参数：" + clspara.C_PARA_NAME + "\r\n" + "类型：" + clspara.C_DV_PARA_TYPE + "\r\n";
                }
            }
            catch (Exception ex)
            {
                SysFun clsbaseFun = new SysFun();
                clsbaseFun.C_FUN_CODE = "AdvancedAlgorithm";
                clsbaseFun.YssAssocia = ClsClzCfgMgr.getAssociaParam(clsbaseFun.C_FUN_CODE);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtError("004", clsbaseFun, status));
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 把选中的数据赋值给set窗体的
        /// 算法控件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnPSDSure_Click(object sender, EventArgs e)
        {
            // // 如果操作类型和B区选中的数据都不为空的时候把数据赋值给set界面
            if (null != operType && null != this.tbShowInfo.SelectedRow)
            {
                if (ClsBizCons.KEY_TYPE.Equals(operType))
                {
                    ClsKeyWord clsKeyWord = this.tbShowInfo.SelectedRow.Tag as ClsKeyWord;
                    frmAlgoSet.txtAlgo.Text = frmAlgoSet.txtAlgo.Text.Insert(frmAlgoSet.txtAlgoSelectionStart, clsKeyWord.KeyWordShow);
                }
            else
            {
                    ClsFunc clsFunc = this.tbShowInfo.SelectedRow.Tag as ClsFunc;
                    frmAlgoSet.txtAlgo.Text = frmAlgoSet.txtAlgo.Text.Insert(frmAlgoSet.txtAlgoSelectionStart, clsFunc.C_FUNC_SHOW);
                }
            }

            this.Close();
        }

        ///add by zhaoxianlin 20130510 BUG7776#3351
        /// <summary>
        /// 行单击事件，把数据显示在描述框中
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void tbShowInfo_SelectedRowDoubleClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            // // 如果操作类型和B区选中的数据都不为空的时候把数据赋值给set界面
            if (null != operType && null != this.tbShowInfo.SelectedRow)
            {
                if (ClsBizCons.KEY_TYPE.Equals(operType))
                {
                    ClsKeyWord clsKeyWord = this.tbShowInfo.SelectedRow.Tag as ClsKeyWord;
                    frmAlgoSet.txtAlgo.Text = frmAlgoSet.txtAlgo.Text.Insert(frmAlgoSet.txtAlgoSelectionStart, clsKeyWord.KeyWordShow);
                }
            else
            {
                    ClsFunc clsFunc = this.tbShowInfo.SelectedRow.Tag as ClsFunc;
                    frmAlgoSet.txtAlgo.Text = frmAlgoSet.txtAlgo.Text.Insert(frmAlgoSet.txtAlgoSelectionStart, clsFunc.C_FUNC_SHOW);
                }
            }

            this.Close();
        }

        /// <summary>
        /// 内部内主要为了处理A区的
        /// 数据的加载对象
        /// </summary>
        private class Cls_KEYWORDSFUNC 
        {
            /// <summary>
            /// 代码
            /// </summary>
            private string c_CODE = "";

            /// <summary>
            /// 名称
            /// </summary>
            private string c_NAME = "";

            /// <summary>
            /// 数据类型
            /// </summary>
            private string c_TYPE = "";

            /// <summary>
            /// 数据类型
            /// </summary>
            public string C_TYPE
            {
                get { return c_TYPE; }
                set { c_TYPE = value; }
            }
            
            /// <summary>
            /// 名称
            /// </summary>
            public string C_NAME
            {
                get { return c_NAME; }
                set { c_NAME = value; }
            }

            /// <summary>
            /// 代码
            /// </summary>
            public string C_CODE
            {
                get { return c_CODE; }
                set { c_CODE = value; }
            }

            /// <summary>
            /// 将字符串解析，并复制给实体类
            /// </summary>
            /// <param name="strResprStr">要解析的字符串</param>
            public void parseRowStr(string strResprStr)
            {
                string[] tmpAry = Regex.Split(strResprStr, "\t");
                this.c_CODE = tmpAry[0];
                this.c_NAME = tmpAry[1];
                this.c_TYPE = tmpAry[2];
            }
        }
    }
}


