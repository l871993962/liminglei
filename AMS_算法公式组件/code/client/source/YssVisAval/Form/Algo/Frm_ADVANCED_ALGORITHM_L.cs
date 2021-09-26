using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.CRUD.Form;
using FAST.Core.Context;
using YssVisAval.service;
using FAST.Common.Service.Pojo;
using FAST.Core.Communication.Service;
using YssVisAval.Pojo.AA;
using FAST.Core.Util;
using FAST.Common.Service.Pojo.Base;
using Yss.KTable.Models;
using Yss.Controls;
using Yss.KRichEx;
using YssVisAval.Pojo.SelfAlgorithm;
using FAST.Core.BaseControl;
using FAST.Common.Service.Dict.Pojo;
using Yss.KTable.Collections;
using FAST.Core.BaseControl.Pojo;
using Yss.KRichEx.AutoFilter.Collections;
using System.Collections;

using FAST.Core.Exceptions;
using FAST.Core.Resource;
using System.Threading;
using FAST.Core.BaseControl.Fun;
using System.Text.RegularExpressions;
using YssVisAval.Fun;

namespace YssVisAval.Form.Algo
{
    /// <summary>
    /// 最新算法界面
    /// </summary>
    public partial class Frm_ADVANCED_ALGORITHM_L : FrmBaseList
    {
        /// <summary>
        /// 接口
        /// </summary>
        private IVisAdvAlgoService service = null;

        /// <summary>
        /// 特殊关键字处理接口
        /// </summary>
        private IVisKeyWordParamService keyWordService = null;

        /// <summary>
        /// 是否终止查询动作
        /// </summary>
        private bool _stopQuery = false;

        /// <summary>
        /// 任务进度条
        /// </summary>
        private Yss.Tools.ProgressTool _progressTool;

        /// <summary>
        /// 算法设置界面参数pojo，负责将set界面参数带到list界面
        /// </summary>
        private AlgoSetParam algoSetParam = null;

        /// <summary>
        /// 算法设置界面修改保存前的算法代码
        /// </summary>
        private string set_page_edit_before_code = null;



        /// <summary>
        /// 存放标签页内的文本域控件,key-value  控件名称-控件对象
        /// </summary>
        private Dictionary<string, Control> controlDic = new Dictionary<string, Control>();

        /// <summary>
        /// 保存规则
        /// </summary>
        private Dictionary<string, bool> rules = new Dictionary<string, bool>();

        /// <summary>
        /// 运算符集合
        /// </summary>
        private string[] operatorArr = new string[] { " + ", "-", "*", "/", "%" };

        /// <summary>
        /// 记录已审核A区行
        /// </summary>
        private List<AdvAlgo> auditList = new List<AdvAlgo>();

        /// <summary>
        /// 记录反审核A区对象
        /// </summary>
        private List<AdvAlgo> unauditList = new List<AdvAlgo>();

        /// <summary>
        /// 记录所有参数，
        /// string:函数code
        /// string:参数code
        /// List 参数对象集合
        /// </summary>
        private Dictionary<string, Dictionary<string, Dictionary<string, string>>> paramDict = new Dictionary<string, Dictionary<string, Dictionary<string, string>>>();

        /// <summary>
        /// 英文算法
        /// </summary>
        private string algo_EN = "";

        /// <summary>
        /// 中文算法
        /// </summary>
        private string algo_CH = "";

        /// <summary>
        /// 是否允许插入数据
        /// </summary>
        private bool allowInsert = true;

        /// <summary>
        /// 要删除的行
        /// </summary>
        private List<string> deleteRow = new List<string>();

        /// <summary>
        /// 记录算法类型
        /// </summary>
        private string algo_type = "";

        /// <summary>
        /// 创建该页面自己的状态，不手动控制系统提供的状态
        /// </summary>
        private string algo_status = ClsEnums.StatusSetting.YssBrow.ToString();

        /// <summary>
        /// 记录修改的标签页code，只保存一个，多个标签页只允许存在一个修改状态存在
        /// </summary>
        private string modify_algo_only_one = "";

        /// <summary>
        /// 英文算法头部的标识
        /// </summary>
        private int signCount = 0;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_ADVANCED_ALGORITHM_L()
        {
            InitializeComponent();
            this.ShowDetailNewPage = true;

        ////STORY #50945 FrmBaseList基类，更换默认A区Bar（barPort）工具条为ToolStrip，并收回barPort访问权限。张绍林20171229
            this.ShowPortSet = true;
            this.ShowPlanSet = true;
            this.ShowFilterPanel = true;
            this.pnlFilter.Height = 1;
            bUseMVCServiceLeft = true;
            bUseMVCService = true;
            this.YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;
            this.ShowPageInation = false;

            service = (IVisAdvAlgoService)ServiceFactory.createService<IVisAdvAlgoService>();
            keyWordService = (IVisKeyWordParamService)ServiceFactory.createService<IVisKeyWordParamService>();
            this.dataService = service;

            this.hasLeftSetForm = true;
            this.leftFormFunCode = "AdvancedAlgorithm";
            this.leftDataFunCode = "AdvancedAlgorithm";
            addSaveButton();
            removeOldSaveButton();
            removeUncheckFormButtons();
        }

        /// <summary>
        /// 重写A区配置信息，走基类老旧模式。
        /// </summary>
        public override AreaAConfigInfo AreaAConfigInfo
        {
            get
            {
                base.AreaAConfigInfo.AreaType = AreaType.BaseDefault;
                return base.AreaAConfigInfo;
            }
        }

        /// <summary>
        /// 算法设置界面参数pojo，负责将set界面参数带到list界面
        /// </summary>
        public AlgoSetParam AlgoSetParam
        {
            get { return algoSetParam; }
            set { algoSetParam = value; }
        }

        /// <summary>
        /// 算法设置界面修改保存前的算法代码
        /// </summary>
        public string Set_page_edit_before_code
        {
            get { return set_page_edit_before_code; }
            set { set_page_edit_before_code = value; }
        }

        /// <summary>
        /// 注册标签页事件
        /// </summary>
        private void registEventTabMain()
        {
            this._tabCtrlMain.TabPageClosed -= new TabPageEventHandler(_tabCtrlMain_TabPageClosed);
            this._tabCtrlMain.BeforeTabPageClosed -= new TabPageChangeEventHandler(_tabCtrlMain_BeforeTabPageClosed);
            this._tabCtrlMain.TabPageClosed += new TabPageEventHandler(_tabCtrlMain_TabPageClosed);
            this._tabCtrlMain.BeforeTabPageClosed += new TabPageChangeEventHandler(_tabCtrlMain_BeforeTabPageClosed);
        }

        /// <summary>
        /// 删除原有的save按钮
        /// </summary>
        private void removeOldSaveButton()
        {
            // // 获取所有按钮
            Dictionary<string, List<string>> dic = btnBar.getAllButtonNames();
            foreach (string name in dic.Keys)
            {
                ClsButtonInfo bInfo = btnBar.getButton(name);
                if (name == ClsButtonName.BtnSave)
                {
                    btnBar.removeButton(bInfo);
                }
            }
        }


        /// <summary>
        /// 添加保存按钮
        /// </summary>
        private void addSaveButton()
        {
            ClsButtonInfo btnSave = new ClsButtonInfo();
            btnSave.Name = "btnSave1";
            btnSave.Text = "保存";
            btnSave.Tooltip = "保存";
            btnSave.Image = FAST.Resource.Resource.btnSave_L;
            btnSave.ClickEvent += new System.EventHandler(this.btnSave1_Click);
            this.btnBar.addButton(btnSave, 9);
            this.btnBar.setButtonVisable("btnSave1", true);
            this.btnBar.setButtonEnabled("btnSave1", true);
        }

        /// <summary>
        /// 统计预览后英文算法头部有几个注释（参数）
        /// </summary>
        /// <param name="msg">预览后的英文算法</param>
        private void countSign(string msg) 
        {
            char[] charArr = msg.ToCharArray();
            for (int i = 0; i < charArr.Length; i++)
            {
                if ("#".Equals(charArr[i].ToString()))
                {
                    signCount = signCount + 1;
                }
            }
        }

        /// <summary>
        /// 保存方法
        /// </summary>
        /// <param name="e">事件</param>
        private void savealgo(EventArgs e) 
        {
            if (this.status.ToString() == this.algo_status)
            {
                return;
             }

        //// 获取A区当前选择项，从controlDic中取出当前函数文本域对象
            ////Row row_left = this.tbLeftMain.SelectedRow;

        ////if (null == row_left)
            ////{

            // //    return;

        ////}

            ////AdvAlgo algo = (AdvAlgo)row_left.Tag;
            if (null == this._tabCtrlMain || null == this._tabCtrlMain.SelectedTab)
            {
                return;
            }

            AdvAlgo algo = (AdvAlgo)this._tabCtrlMain.SelectedTab.Tag;
            YssTextBox control = (YssTextBox)controlDic["test_main_algo_top_" + algo.C_ALGO_CODE];
            Yss.Controls.RichTextBox lowerCon = (Yss.Controls.RichTextBox)controlDic["test_main_algo_below_" + algo.C_ALGO_CODE];

            string aogo_en = getPreviewFullMsg();
            countSign(aogo_en);
            if (Equals(aogo_en, "false"))
            {
                return;
            }

            string aogo_en_msg = aogo_en.Replace("\r\n", "#\r\n");

        ////检测算法是否合法
            if (!check_Formula(aogo_en_msg))
            {
                if (e is TabPageChangeEventArgs)
                {
                    ((TabPageChangeEventArgs)e).Cancel = true;
                }

                return;
            }


            AdvAlgo a = new AdvAlgo();
            a.C_ALGO_IS_NEW = "True";

        ////检查新旧算法
            string iSNew = this.service.isNewAlgo(algo.C_ALGO_CODE);
            if (Equals(iSNew, "False"))
            {
                a.C_ALGO_IS_NEW = "False";
            }

            a.Id = algo.Id;
            a.C_ALGO_CODE = algo.C_ALGO_CODE;
            if (Equals(a.C_ALGO_IS_NEW, "False"))
            {
                a.C_ALGO_FORMULA = control.Text;
            }
            else
            {
                a.C_ALGO_FORMULA = aogo_en;
                a.C_ALGO_FORMULA_ZH = control.Text;
            }

            if (Equals(iSNew, "False"))
            {
                a.C_DESC = lowerCon.Text;
            }
            else 
            {
                a.C_DESC = lowerCon.Rtf;
            }
            
            a.C_ALGO_UPDATE_TARGET = "list";
            ArrayList list = new ArrayList();
            list.Add(a);
            this.service.updateById(list);

        //// 保存结束后设置当前标签页为不可修改
            Row left_row = this.tbLeftMain.SelectedRow;
            if (null != left_row)
            {
                AdvAlgo aa = (AdvAlgo)left_row.Tag;
                YssTextBox ytb = (YssTextBox)controlDic["test_main_algo_top_" + aa.C_ALGO_CODE];
                Yss.Controls.RichTextBox rtb = (Yss.Controls.RichTextBox)controlDic["test_main_algo_below_" + aa.C_ALGO_CODE];
                ytb.YssReadOnly = true;
                ytb.BackColor = Color.White;

        ////rtb.Enabled = false;
                rtb.ReadOnly = true;
                rtb.BackColor = Color.White;

        //// 保存完毕后修改按钮属性
                //// 获取所有按钮
                string algoStatus = this.service.checkAlgoStatus(aa.C_ALGO_CODE);
                Dictionary<string, List<string>> dic = btnBar.getAllButtonNames();

                foreach (string name in dic.Keys)
                {
                    ClsButtonInfo bInfo = btnBar.getButton(name);
                    bInfo.Enabled = true;

                    ////if (name == "btnSave1")
                    ////{

            // //    ClsButtonInfo btnInfo = btnBar.getButton(name);

        ////    btnInfo.Enabled = false;

        ////}

                    if (name == ClsButtonName.BtnUnAudit || name == "btnSave1")
                    {
                        ClsButtonInfo btnInfo = btnBar.getButton(name);
                        btnInfo.Enabled = false;
                    }


                }
            }

            this.modify_algo_only_one = "";
        }

        /// <summary>
        /// 保存按钮事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnSave1_Click(object sender, EventArgs e)
        {
            savealgo(e);
            this.algo_status = ClsEnums.StatusSetting.YssBrow.ToString();
        }

        /// <summary>
        /// 检查新旧算法
        /// </summary>
        /// <returns>True 为新函数，否则为旧函数</returns>
        public string isNewAlgo()
        {
            // //AdvAlgo algo = (AdvAlgo)this.tbLeftMain.SelectedRow.Tag;
            Row row_left = this.tbLeftMain.SelectedRow;
            if (null == row_left)
            {
                return "False";
            }

            AdvAlgo algo = (AdvAlgo)row_left.Tag;

        ////True 为新函数，否则为旧函数
            string flag = this.service.isNewAlgo(algo.C_ALGO_CODE);
            return flag;
        }

        /// <summary>
        /// 验证算法是否合法
        /// </summary>
        /// <param name="msg">算法信息</param>
        /// <returns>是否保存</returns>
        private bool check_Formula(string msg) 
        {
            bool flag = true;
            try
            {
                if (0 == msg.Trim().Length)
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("003", _formFun, status));
                    return false;
                }

                string retStr = this.service.checkFormula(isNewAlgo(), msg);
                Dictionary<string, string> err_dic = (Dictionary<string, string>)JsonUtil.toObject(retStr, typeof(Dictionary<string, string>));
                Dictionary<string, string>.KeyCollection keys = (Dictionary<string, string>.KeyCollection)err_dic.Keys;
                string key = "";
                foreach (string k in keys)
                {
                    key = k;
                }

                if (ClsBizCons.CheckResult.Equals(key))
                {
                    ClsRetInfo retInfo = new ClsRetInfo();
                    retInfo.infoType = ClsConstant.INFO_TP_WARN;
                    retInfo.infoTitle = "警告";

        ////错误的行号
                    ////string err_line_num = err_dic[key].Substring(9, 1);
                    string[] err_line_num_arr = err_dic[key].Split("'".ToCharArray()[0]);
                    string err_line_num = err_line_num_arr[1];
                    string line_err_msg = "";

        //// 根据错误的行号选中对应的行
                    Row leftRow = this.tbLeftMain.SelectedRow;
                    if (null != leftRow)
                    {
                        AdvAlgo algoleft = (AdvAlgo)leftRow.Tag;
                        YssTextBox control_center = (YssTextBox)controlDic["test_main_algo_top_" + algoleft.C_ALGO_CODE];
                        int num = int.Parse(err_line_num);

        ////得到报错行的首个字符索引
                        int err_line_first_char_index = control_center.GetFirstCharIndexFromLine(num - 1 - signCount);
                        int err_line_end_index = control_center.Text.IndexOf("\r\n", err_line_first_char_index);
                        line_err_msg = err_line_num_arr[0] + (num - 1 - signCount) + err_line_num_arr[2];

        ////string select_err_msg = control_center.Text.Substring(err_line_first_char_index, err_line_end_index - err_line_first_char_index);
                        control_center.Select(err_line_first_char_index, err_line_end_index - err_line_first_char_index);
                        signCount = 0;
                    }

                    ////后台错误信息，形如：map.put("false", "算法检测异常：第 " + lineNum + " 行配置有误！$错误信息：\r\n" + msg.trim());
                    string[] err_arr = err_dic[key].Substring(0, err_dic[key].Length - 1).Split('$');
                    retInfo.infoContent = line_err_msg;
                    retInfo.detailInfo = err_arr[1];
                    YssMessageBox.ShowCommonInfoText(retInfo);
                    flag = false;
                }
            else
            {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, status));

                }
            }
            catch (Exception ex)
            {
                flag = false;
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtError("001", _formFun, status));
                ClsBaseException.DiscardException(ex);
            }

            return flag;
        }

        /// <summary>
        /// 加载A区树
        /// </summary>
        public override void yssLoadLeftData()
        {
            load_A();
        }

        /// <summary>
        /// 加载A区
        /// </summary>
        private void load_A()
        {
            string leftTreePojoJson = this.service.getAllDataTree_A();
            List<AdvAlgo> algos = (List<AdvAlgo>)JsonUtil.toObject(leftTreePojoJson, typeof(List<AdvAlgo>));
            QueryRes res = new QueryRes();
            ListHeadInfo listHead = new ListHeadInfo();
            listHead.Key = "C_ALGO_NAME";
            listHead.Text = "算法名称";
            List<ListHeadInfo> list = new List<ListHeadInfo>();
            list.Add(listHead);

            res.DataList = convertPojo(algos);
            res.ListHeadList = list;
            TableListLoader tableListLoader = new TableListLoader();
            this.matchSearchStr = new string[] { "C_ALGO_CODE", "C_ALGO_NAME" }; // 【搜索】功能匹配的属性
            //// 默认不排序
            tableListLoader.AutoSort = false;
            tableListLoader.loadTable(this.tbLeftMain, res, false, false, ClsEnums.KTableDataShowMode.TreeMode);
        }

        /// <summary>
        /// A区窗体操作后，根据操作前的选中行号，选中A区对应的行
        /// </summary>
        public override void selectRowAfterLeftSetFormSave()
        {
            this.tbLeftMain.SelectionMode = SelectionMode.One;
            if (algoSetParam != null)
            {
                RowCollection rows = this.tbLeftMain.Rows;
                for (int i = 0; i < rows.Count; i++)
                {
                    if (rows[i].SubRows.Count > 0)
                    {
                        RowCollection rs = rows[i].SubRows;
                        for (int j = 0; j < rs.Count; j++)
                        {
                            AdvAlgo aaa = (AdvAlgo)rs[j].Tag;
                            if (Equals(aaa.C_ALGO_CODE, algoSetParam.AlgoCode))
                            {
                                rs[j].Selected = true;
                                rs[j].Checked = true;

        //// this.status = ClsEnums.StatusSetting.YssAdd;
                                this.algo_status = ClsEnums.StatusSetting.YssAdd.ToString();
                                
                                //// 清除标签页集合内修改前的标签（set界面修改code和名称）
                                if (!"".Equals(this.set_page_edit_before_code) && null != this._tabCtrlMain && this._tabCtrlMain.TabPages.Count > 0)
                                {
                                    TabPageCollection tpc = this._tabCtrlMain.TabPages;
                                    Yss.Controls.TabPage tp = null;
                                    bool flag = false;
                                    foreach (Yss.Controls.TabPage tabPage in tpc)
                                    {
                                        AdvAlgo adv = (AdvAlgo)tabPage.Tag;
                                        if (adv.C_ALGO_CODE.Equals(set_page_edit_before_code))
                                        {
                                            flag = true;
                                            tp = tabPage;
                                        }
                                    }

                                    if (flag)
                                    {
                                        this._tabCtrlMain.TabPages.Remove(tp);
                                    }

                                    ////set_page_edit_before_code = "";
                                }

                                openPage();
                             }

        ////CellCollection cells = rs[j].Cells;

        ////if (Equals(cells[0].Text, algoSetParam.AlgoCode))
                            ////{

            // //    rs[j].Selected = true;

        ////    this.status = ClsEnums.StatusSetting.YssAdd;

        ////    //// 手动触发事件
                            ////    ////tbLeftMain_DoubleClick(rs[j], new EventArgs());

        ////    openPage();

        ////}

                        }
                    }

                }
            }

            algoSetParam = null;
        }

        /// <summary>
        /// pojo转换为BasePojo
        /// </summary>
        /// <param name="algos">1</param>
        /// <returns>12</returns>
        private List<BasePojo> convertPojo(List<AdvAlgo> algos)
        {
            List<BasePojo> datas = new List<BasePojo>();
            if (algos != null)
            {
                foreach (AdvAlgo api in algos)
                {
                    BasePojo pojo = (BasePojo)api;
                    datas.Add(pojo);
                }    
            }

            return datas;
        }

        /// <summary>
        /// A区双击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void tbLeftMain_DoubleClick(object sender, EventArgs e)
        {
            // //if (hasLeftSetForm)
            ////{

            // //AdvAlgo algo = (AdvAlgo)this.tbLeftMain.SelectedRow.Tag;

        ////    Row srow = this.tbLeftMain.SelectedRow;

        ////    AdvAlgo algo = (AdvAlgo)srow.Tag;

        ////    if (null != srow && srow.HasChild)
            ////    {

            // //        return;

        ////    }

            ////    showLeftFormSet(ClsEnums.StatusSetting.YssBrow);

        //// }

        ////else
            ////{

            // //    return;

        ////}
            this.hasLeftSetForm = true;

            if (hasLeftSetForm)
            {
                Row srow = this.tbLeftMain.SelectedRow;

        ////if (null != srow && "Root".Equals(((AdvAlgo)srow.Tag).C_DV_ALGO_TYPE))
                ////{

            // //    return;

        ////}
                if (null == srow)
                {
                    return;
                }

                AdvAlgo algo = (AdvAlgo)srow.Tag;
                if (algo != null)
                {
                    if (algo.C_DV_ALGO_TYPE.Equals("Root"))
                    {
                        this.hasLeftSetForm = false;
                        return;
                    }
                }

                showLeftFormSet(ClsEnums.StatusSetting.YssBrow);
            }
            else
            {
                return;
            }

            ////AdvAlgo algo = (AdvAlgo)this.tbLeftMain.SelectedRow.Tag;

        ////Row srow = this.tbLeftMain.SelectedRow;

        ////if (null != srow && srow.HasChild)
            ////{

            // //    return;

        ////}


            //////// 判断选中的算法是否是已审核，若已审核，则状态改为浏览状态
            ////string algo_status = this.service.checkAlgoStatus(algo.C_ALGO_CODE);

        ////if (Equals(algo_status, "1"))
            ////{

            // //    this.status = ClsEnums.StatusSetting.YssBrow;

        ////}

            ////if (Equals(isNewAlgo(), "False"))
            ////{

            // //    rightSplitter.Enabled = false;

        ////} 
            ////else
            ////{

            // //    rightSplitter.Enabled = true;

        ////}

            ////if (this.rightSplitter.Expanded == true)
            ////{

            // //    this.rightSplitter.Expanded = false;

        ////}

            ////if (!this.ShowDetailNewPage || this.Visible == false)
            ////{

            // //    return;

        ////}

            //// this.CreateTabControlMain();

            ////Row row = this.TableLeftMain.SelectedRow;

            ////if (this.CheckNewPage(row, false))
            ////{

            // //    Yss.Controls.TabPage tabItem = this.CreateTabItem(row.Cells[0].Text, algo.C_ALGO_CODE);

        ////}

            ////string s = this.status.ToString();

            ////if (this.status != ClsEnums.StatusSetting.YssEdit && this.status != ClsEnums.StatusSetting.YssAdd)
            ////{

            // //    this.status = ClsEnums.StatusSetting.YssBrow;

        ////}
        }

        /// <summary>
        /// 根据打开界面的状态控制按钮的状态
        /// </summary>
        private void contrlBtnStatus() 
        {
            // //AdvAlgo algo = (AdvAlgo)this.tbLeftMain.SelectedRow.Tag;
            Row row_left = this.tbLeftMain.SelectedRow;
            if (null == row_left)
            {
                return;
            }

            AdvAlgo algo = (AdvAlgo)row_left.Tag;
            string algoStatus = this.service.checkAlgoStatus(algo.C_ALGO_CODE);

            Dictionary<string, List<string>> dic = btnBar.getAllButtonNames();
            foreach (string name in dic.Keys)
        {
            // // 初始化按钮为可用状态
                ClsButtonInfo binfo = btnBar.getButton(name);
                binfo.Enabled = true;
                
                ////已审核：只保留反审核按钮
                if (Equals(algoStatus, "1"))
                {
                    if (name == ClsButtonName.BtnEdit || name == "btnSave1" || name == ClsButtonName.BtnDelete || name == ClsButtonName.BtnAudit)
                    {
                        binfo.Enabled = false;
                        continue;
                    }
                }

                ////未审核：禁用  反审核，保存
                if (Equals(algoStatus, "0"))
                {
                    if (name == ClsButtonName.BtnUnAudit)
                    {
                        binfo.Enabled = false;
                        continue;
                    }

                    //// 当状态为添加或者编辑的时候   不禁用保存按钮
                    if (name == "btnSave1")
                    {
                        if (this.algo_status == ClsEnums.StatusSetting.YssAdd.ToString() || this.algo_status == ClsEnums.StatusSetting.YssEdit.ToString())
                        {
                            binfo.Enabled = true;
                        } 
                        else
                        {
                            binfo.Enabled = false;
                        }
                        
                        continue;
                    }
                }
            }

            if (this.algo_status == ClsEnums.StatusSetting.YssBrow.ToString())
            {
            }
        }

        /// <summary>
        /// 重写创建标签页
        /// </summary>
        /// <param name="tabTitle">tabTitle</param>
        /// <param name="code">code</param>
        /// <returns>TabPage</returns>
        protected Yss.Controls.TabPage CreateTabItem(string tabTitle, string code)
        {
            Yss.Controls.TabPage tabPage = new Yss.Controls.TabPage();
            tabPage.Text = tabTitle;

        ////tabPage.Tag = this.tbLeftMain.SelectedRow;

        //// 根据算法代码查出完整的算法信息
            AdvAlgo advAlgo = (AdvAlgo)this.service.getAlgoByCode(code);
            tabPage.Tag = advAlgo;
            tabPage.Controls.Add(createCenterMainPanel(code));
            this._tabCtrlMain.TabPages.Add(tabPage);
            this.pnlHost.Dock = DockStyle.Fill;
            return tabPage;
        }

        /// <summary>
        /// 显示各个panel的边框
        /// </summary>
        /// <param name="panel">panel</param>
        public void showBorder(PanelEx panel)
        {
            panel.Border.Bottom = true;
            panel.Border.Right = true;
            panel.Border.Top = true;
            panel.Border.Left = true;
        }

        /// <summary>
        /// 导航条搜索栏
        /// </summary>
        /// <returns>PanelEx</returns>
        public PanelEx createRightTopSearch()
        {
            PanelEx rightTopSearch = new PanelEx();
            rightTopSearch.Dock = DockStyle.Top;
            Table t = new Table();
            t.Dock = DockStyle.Fill;
            t.ShowColumnHeader = true;
            
            Column column = new Column();
            column.Width = 450;
            column.Text = "函数选择";
            column.ColumnSort = Yss.KTable.Enums.ColumnSortType.None;
            Row r1 = new Row();
            Cell cell = new Cell();
            TailTextBox rightSearch = new TailTextBox();
            rightSearch.TailText = "搜索";
            cell.InnerControl = rightSearch;
            r1.Cells.Add(cell);
            t.Columns.Add(column);
            t.Rows.Add(r1);
            rightTopSearch.Controls.Add(t);
            return rightTopSearch;
        }

        /// <summary>
        /// 创建B区中间的panel
        /// </summary>
        /// <param name="code">code</param>
        /// <returns>PanelEx</returns>
        public PanelEx createCenterPanel(string code)
        {            
            AdvAlgo a = null;

        //// 是否有参考算法，方便往集合内添加控件名称，翻译保存的时候通过控件名，取出控件信息
            bool hasCKAlgo = false;

            if (ClsEnums.StatusSetting.YssAdd.ToString() == this.algo_status || this.algo_status == ClsEnums.StatusSetting.YssEdit.ToString())
            {
                this.rightSplitter.Expanded = true;
            }

            a = (AdvAlgo)this.service.getAlgoByCode(code);

        ////a = (AdvAlgo)this._tabCtrlMain.SelectedTab.Tag;

            PanelEx centerPanel = new PanelEx();
            centerPanel.Dock = DockStyle.Fill;

        ////PanelEx topPanel = createTopPanel(a, hasCKAlgo);
            PanelEx topPanel = createTopPanel(a);

            PanelEx centPanel = new PanelEx();
            showBorder(centPanel);
            centPanel.Dock = DockStyle.Bottom;
            centPanel.Height = 20;
            Table centerTable = new Table();
            centerTable.ShowColumnHeader = false;
            centerTable.Dock = DockStyle.Fill;
            Column c1 = new Column();
            c1.Width = 100;
            Column c2 = new Column();
            centerTable.Columns.Add(c1);
            centerTable.Columns.Add(c2);
            
            Row rd = new Row();
            Cell cd = new Cell();
            cd.Text = "算法描述：";
            rd.Cells.Add(cd);
            centerTable.Rows.Add(rd);
            centPanel.Controls.Add(centerTable);

            ////PanelEx belowPanel = createBelowPanel(a, hasCKAlgo);
            PanelEx belowPanel = createBelowPanel(a);
            
            centerPanel.Controls.Add(topPanel);
            centerPanel.Controls.Add(centPanel);
            centerPanel.Controls.Add(belowPanel);

            showBorder(centerPanel);

            //// 根据打开算法状态控制按钮
            contrlBtnStatus();

            return centerPanel;
        }

        /// <summary>
        /// 添加底部panel
        /// </summary>
        /// <param name="a">算法对象</param>
        /// <returns>PanelEx</returns>
        private PanelEx createBelowPanel(AdvAlgo a)
        {
            PanelEx belowPanel = new PanelEx();
            showBorder(belowPanel);
            belowPanel.Dock = DockStyle.Bottom;
            belowPanel.Height = 200;

            Yss.Controls.RichTextBox selfAlgoDesc = new Yss.Controls.RichTextBox();
            selfAlgoDesc.ToolBoxVisible = true;
            System.Drawing.Size size = new System.Drawing.Size();
            size.Height = 1500;
            size.Width = 2000;
            selfAlgoDesc.MaxImageSize = size;
            selfAlgoDesc.MaxImageLength = 1024;

        ////if (hasCKAlgo)
            ////{

            // //    AdvAlgo ao = (AdvAlgo)tbLeftMain.SelectedRow.Tag;

        ////    selfAlgoDesc.Name = "test_main_algo_below_" + ao.C_ALGO_CODE;

        ////} 
            ////else
            ////{
                selfAlgoDesc.Name = "test_main_algo_below_" + a.C_ALGO_CODE;

        ////}
            
            selfAlgoDesc.MaxLength = 30000;
            selfAlgoDesc.Multiline = true;
            selfAlgoDesc.Dock = DockStyle.Fill;
            
            if (null != a)
            {
                string iSNew = this.service.isNewAlgo(a.C_ALGO_CODE);
                if (Equals(iSNew, "True"))
                {
                    selfAlgoDesc.Rtf = a.C_DESC;
                }
            else
            {
            // // 设置旧算法  描述  工具栏 不可见  防止客户在旧算法中上传图片
                    selfAlgoDesc.ToolBoxVisible = false;
                    selfAlgoDesc.Text = a.C_DESC;
                }
                
            }

            closeControlWhenBrowse(selfAlgoDesc);

        //// 保存组件信息
            if (controlDic.ContainsKey(selfAlgoDesc.Name))
            {
                controlDic[selfAlgoDesc.Name] = selfAlgoDesc;
            }
            else
            {
                controlDic.Add(selfAlgoDesc.Name, selfAlgoDesc);
            }

            Table belowPanelTable = new Table();
            belowPanelTable.Dock = DockStyle.Fill;
            belowPanelTable.Columns.Add(new Column());
            Row row2 = createRow2(selfAlgoDesc);
            belowPanelTable.Rows.Add(row2);
            belowPanel.Controls.Add(belowPanelTable);
            return belowPanel;
        }

        /// <summary>
        /// 当为浏览状态下的时候修改控件为禁止交互状态
        /// </summary>
        /// <param name="con">控件</param>
        private void closeControlWhenBrowse(Control con)
        {
            if (this.algo_status == ClsEnums.StatusSetting.YssBrow.ToString() || this.algo_status == ClsEnums.StatusSetting.YssAudit.ToString() || this.algo_status == ClsEnums.StatusSetting.YssAdd.ToString())
        {
            // //con.Enabled = false;
                if (con is YssTextBox)
                {
                    ((YssTextBox)con).YssReadOnly = true;
                    ((YssTextBox)con).BackColor = Color.White;
                }
            else
            {
                    ((Yss.Controls.RichTextBox)con).ReadOnly = true;
                    ((Yss.Controls.RichTextBox)con).BackColor = Color.White;
                }
            }
        }

        /// <summary>
        /// 添加顶部panel
        /// </summary>
        /// <param name="a">算法对象</param>
        /// <returns>PanelEx</returns>
        private PanelEx createTopPanel(AdvAlgo a)
        {
            // // 添加三个panel
            PanelEx topPanel = new PanelEx();
            topPanel.Dock = DockStyle.Fill;

            Table topPanelTable = new Table();
            topPanelTable.Dock = DockStyle.Fill;
            topPanelTable.Columns.Add(new Column());

            YssTextBox selfAlgoText = new YssTextBox();

        //// 添加文本域click事件  匿名事件
            selfAlgoText.MouseClick += delegate(object sender, MouseEventArgs e)
            {
                selfAlgoText_SelectFun(selfAlgoText);
            };
            selfAlgoText.KeyDown += new KeyEventHandler(selfAlgoText_KeyDown);
            selfAlgoText.YssIsMust = true;
            selfAlgoText.ScrollBars = ScrollBars.Vertical;

        ////if (hasCKAlgo)
            ////{

            // //    AdvAlgo ao = (AdvAlgo)this.tbLeftMain.SelectedRow.Tag;

        ////    selfAlgoText.Name = "test_main_algo_top_" + ao.C_ALGO_CODE;

        ////} 
            ////else
            ////{
                selfAlgoText.Name = "test_main_algo_top_" + a.C_ALGO_CODE;

        ////}
            
            selfAlgoText.Multiline = true;
            selfAlgoText.MaxLength = 30000;
            selfAlgoText.YssLength = 20000;
            selfAlgoText.Dock = DockStyle.Fill;
            if (null != a)
            {
                if (a.C_ALGO_FORMULA_ZH != null && a.C_ALGO_FORMULA_ZH != "")
                {
                    if (Equals(a.C_ALGO_FORMULA_ZH, "null"))
                    {
                        selfAlgoText.Text = "";
                    } 
                    else
                    {
                        selfAlgoText.Text = a.C_ALGO_FORMULA_ZH;
                    }
                } 
                else
                {
                    selfAlgoText.Text = a.C_ALGO_FORMULA;
                }
            }

            closeControlWhenBrowse(selfAlgoText);

            if (this.algo_status == ClsEnums.StatusSetting.YssBrow.ToString())
            {
                selfAlgoText.YssIsMust = false;
                selfAlgoText.BackColor = Color.White;

        ////selfAlgoText.YssReadOnly = true;
            }

            //// 将该组件添加到全局域内
            if (controlDic.ContainsKey(selfAlgoText.Name))
            {
                controlDic[selfAlgoText.Name] = selfAlgoText;
            }
            else
            {
                controlDic.Add(selfAlgoText.Name, selfAlgoText);
            }

            Row row = createRow(selfAlgoText);
            topPanelTable.Rows.Add(row);
            topPanel.Controls.Add(topPanelTable);
            return topPanel;
        }

        /// <summary>
        /// 键盘按下事件
        /// </summary>
        /// <param name="sender">键盘</param>
        /// <param name="e">e</param>
        private void selfAlgoText_KeyDown(object sender, KeyEventArgs e)
        {
            Row row = this.tbLeftMain.SelectedRow;
            if (null != row)
            {
                AdvAlgo algo = row.Tag as AdvAlgo;
                if (!controlDic.ContainsKey("test_main_algo_top_" + algo.C_ALGO_CODE))
                {
                    return;
                }

                YssTextBox textbox = (YssTextBox)controlDic["test_main_algo_top_" + algo.C_ALGO_CODE];
                string textContent = textbox.Text;
                int first_char_index = textbox.GetFirstCharIndexOfCurrentLine();
                int currentPointIndex = textbox.SelectionStart;

        ////回车键 值为 13
                if (e.KeyValue == 13)
                {
                    if (textContent.Trim().Length > 0)
                    {
                        if (currentPointIndex > first_char_index)
                        {
                            string sign_str = textContent.Substring(first_char_index, currentPointIndex - first_char_index);
                            if (sign_str.Contains(':'))
                            {
                                return;
                            }

                            if (sign_str.Trim().StartsWith("if") || sign_str.Trim().StartsWith("else"))
                            {
                                string prefix_str = textContent.Substring(0, currentPointIndex);
                                string end_str = textContent.Substring(currentPointIndex);
                                textbox.Text = prefix_str + ":" + end_str;
                                textbox.SelectionStart = prefix_str.Length + 1;
                                return;
                            }
            else
            {
                                string prefix_str = textContent.Substring(0, currentPointIndex);
                                string end_str = textContent.Substring(currentPointIndex);
                                if (!"".Equals(sign_str.Trim()) && !sign_str.Contains(';'))
                                {
                                    textbox.Text = prefix_str + ";" + end_str;
                                    textbox.SelectionStart = prefix_str.Length + 1;
                                    return;
                                }
                                
                            }
                        }
                    }
                }
                
            }
            
        }

        /// <summary>
        /// 创建行
        /// </summary>
        /// <param name="textBox">textBox</param>
        /// <returns>Row</returns>
        public Row createRow(YssTextBox textBox)
        {
            Row r = new Row();
            Cell cell = new Cell();
            cell.InnerControl = textBox;
            r.Cells.Add(cell);
            return r;
        }

        /// <summary>
        /// 创建行
        /// </summary>
        /// <param name="textBox">textBox</param>
        /// <returns>Row</returns>
        public Row createRow2(Yss.Controls.RichTextBox textBox)
        {
            Row r = new Row();
            Cell cell = new Cell();
            cell.InnerControl = textBox;
            r.Cells.Add(cell);
            return r;
        }

        /// <summary>
        /// 创建标签页内的panel
        /// </summary>
        /// <param name="code">code</param>
        /// <returns>PanelEx</returns>
        public PanelEx createCenterMainPanel(string code)
        {
            // //B区主panel            
            PanelEx rightMainPanel = new PanelEx();
            showBorder(rightMainPanel);
            PanelEx centerPanel = createCenterPanel(code);
            
            rightMainPanel.Dock = DockStyle.Fill;

            if (ClsEnums.StatusSetting.YssAdd.ToString() == this.algo_status || this.algo_status == ClsEnums.StatusSetting.YssEdit.ToString())
            {
                this.algo_status = ClsEnums.StatusSetting.YssBrow.ToString();
            }

            rightMainPanel.Controls.Add(centerPanel);
            return rightMainPanel;

        }

        /// <summary>
        /// 功能描述：复写基本窗体加载事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void FrmBase_Load(object sender, EventArgs e)
        {
            // //隐藏右侧panel（函数块）
            this.rightSplitter.Expanded = false;

        ////loadRightTree();
        }

        /// <summary>
        /// 控制右侧函数说明的属性
        /// </summary>
        /// <param name="str">str</param>
        public void funDect(string str)
        {
            if (null != str)
            {
                str = str.Replace("\t", "");
                str = str.Replace("\\r\\n", "\r\n");
            }

            this.yssTextBox1.YssReadOnly = true;
            this.yssTextBox1.Multiline = true;
            this.yssTextBox1.Text = str;
        }

        /// <summary>
        /// 加载右侧函数树
        /// </summary>
        /// <param name="code">算法类型</param>
        public void loadRightTree(string code)
        {
            // //加载右侧函数树  -->  初始化默认不加载，当点击A区的时候加载
            string dataJson = this.service.getTreeData(code);
            List<AlgoAPI> algoAPIs = (List<AlgoAPI>)JsonUtil.toObject(dataJson, typeof(List<AlgoAPI>));
            QueryRes res = new QueryRes();
            ListHeadInfo listHead = new ListHeadInfo();
            listHead.Key = "AlgoText";
            listHead.Text = "函数名称";
            List<ListHeadInfo> list = new List<ListHeadInfo>();
            list.Add(listHead);

        //// 当插件没有部署的时候不加载右侧树
            if (null == algoAPIs)
            {
                return;
            }

            res.DataList = convertPojo2(algoAPIs);
            res.ListHeadList = list;
            TableListLoader tableListLoader = new TableListLoader();
            tableListLoader.AutoSort = false;
            
            tableListLoader.loadTable(this.rightTreeTable, res, false, false, ClsEnums.KTableDataShowMode.TreeMode);
            rightTreeTable.CollapseAll();
        }

        /// <summary>
        /// pojo转换为BasePojo
        /// </summary>
        /// <param name="algoAPIs">1</param>
        /// <returns>12</returns>
        private List<BasePojo> convertPojo2(List<AlgoAPI> algoAPIs)
        {
            List<BasePojo> datas = new List<BasePojo>();
            foreach (AlgoAPI api in algoAPIs)
            {
                BasePojo pojo = (BasePojo)api;
                datas.Add(pojo);
            }

            return datas;
        }

        /// <summary>
        /// 右侧树选中行事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void rightTreeTable_SelectionChanged(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            // //AlgoAPI algoAPI = (AlgoAPI)this.rightTreeTable.SelectedRow.Tag;

        //////// 添加函数说明
            ////funDect(algoAPI.AlgoDesc);

        //////// 获取参数集合
            ////string paramsJson = this.service.getDataAPIByCode(algoAPI.AlgoCode);

        ////DataAPI dataAPI = (DataAPI)JsonUtil.toObject(paramsJson, typeof(DataAPI));

        ////////画参数列表
            ////creataParamTable(dataAPI);
            Row row = this.rightTreeTable.SelectedRow;
            if (null == row)
            {
                return;
            }

            AlgoAPI algoAPI = (AlgoAPI)row.Tag;

            if (null == row)
            {
                return;
            }

            if (Equals(algoAPI.AlgoParent, "Root"))
            {
                return;
            }

            //// 添加函数说明
            funDect(algoAPI.AlgoDesc);

        //// 获取参数集合
            string paramsJson = this.service.getDataAPIByCode(algoAPI.AlgoCode);
            DataAPI dataAPI = (DataAPI)JsonUtil.toObject(paramsJson, typeof(DataAPI));

        ////画参数列表
            creataParamTable(dataAPI);
        }

        /// <summary>
        /// 根据参数画参数列表
        /// </summary>
        /// <param name="dataAPI">dataAPI</param>
        public void creataParamTable(DataAPI dataAPI)
        {
            // // 清除行数据
            this.rightTableParams.RemoveCellsControl();
            this.rightTableParams.ClearRows();

        //// this.rightTableParams.Clear();

            // 添加一空行
            // this.rightTableParams.Rows.Add(new Row());
            List<AlgoAPIParam> algoAPIParams = dataAPI.ParamAPIs;
            foreach (AlgoAPIParam param in algoAPIParams)
            {
                try
                {
                    // 如果Isdefault为FALSE 则动态生成页面 
                    if (param.Isdefault == false)
                       {
                        // 特殊关键字处理
                        if (isSpecialKeyWord(param))
                        {
                            specialKeyWordDeal(param);
                            continue;
                        }

                        creataRowInsertData(param);
                    }
                    else
                    {
                        // isdefault=true 将value的值填充到文本框 不可编辑
                        Row rowPara = new Row();
                        Cell cp1 = new Cell();
                        Cell cp2 = new Cell();
                        cp2.Key = param.Code;
                        Cell cp3 = new Cell();
                        Cell cp4 = new Cell();

                        cp2.Text = param.Name;
                        YssTextBox b = new YssTextBox();
                        b.YssReadOnly = true;
                        b.Text = param.ParamValue;
                        cp3.InnerControl = b;

                        rowPara.Cells.AddRange(new Cell[] { cp1, cp2, cp3, cp4 });

                        this.rightTableParams.Rows.Add(rowPara);
                    }
                }
                catch (Exception ex)
                {
                }

            }

            this.rightTableParams.Refresh();
        }

        /// <summary>
        /// 判断是否为特殊关键字
        /// </summary>
        /// <param name="param">AlgoAPIParam</param>
        /// <returns>bool</returns>
        private bool isSpecialKeyWord(AlgoAPIParam param)
        {
            if (param.KeyWord != null && param.KeyWord != "")
        {
            // // 获得所有特殊关键字
                string keyJson = this.keyWordService.getAllKeyWord();
                List<string> keyList = (List<string>)JsonUtil.toObject(keyJson, typeof(List<string>));

                if (null != keyList && keyList.Contains(param.KeyWord))
                {
                    return true;
                }

                return false;
            }
            else
            {
                return false;
            }

        }

        /// <summary>
        /// 增加对特别关键字处理
        /// </summary>
        /// <param name="param">AlgoAPIParam</param>
        private void specialKeyWordDeal(AlgoAPIParam param)
        {
            Row rowPara = new Row();
            Cell cp1 = new Cell();
            Cell cp2 = new Cell();
            cp2.Key = param.Code;
            cp2.Text = param.Name;
            Cell cp3 = new Cell();
            Cell cp4 = new Cell();
            string keyWord = param.KeyWord;
            string keyJson = this.keyWordService.getAllKeyWord();
            List<string> keyList = (List<string>)JsonUtil.toObject(keyJson, typeof(List<string>));
            Dictionary<string, string> paramDic = null;
            foreach (string key in keyList)
            {
                if (Equals(keyWord, key))
                {
                    string valJson = this.keyWordService.getKeyWordParamValues(keyWord);
                    paramDic = (Dictionary<string, string>)JsonUtil.toObject(valJson, typeof(Dictionary<string, string>));
                    break;
                }
            }

            if (paramDic == null)
            {
                return;
            }

            List<Vocabulary> vocs = new List<Vocabulary>();
            foreach (string str in paramDic.Keys)
            {
                Vocabulary voc = new Vocabulary();
                voc.C_DV_CODE = str;
                voc.C_DV_NAME = paramDic[str];
                vocs.Add(voc);
            }

            YssSelCombox selCombox = new YssSelCombox();
            selCombox.YssIsMust = true;
            selCombox.Items.Clear();
            selCombox.Parameter = "C_DV_NAME";
            selCombox.DisplayValue = "C_DV_CODE";
            selCombox.DisplayName = "C_DV_NAME";
            selCombox.SortColumn = "C_DV_NAME";

            foreach (Vocabulary voc in vocs)
            {
                Yss.KRichEx.AutoFilter.Model.KTableEntity enty = new Yss.KRichEx.AutoFilter.Model.KTableEntity(voc);
                selCombox.Items.Add(enty);
            }

            selCombox.IsRefresh = false;

            cp3.InnerControl = selCombox;

            rowPara.Cells.AddRange(new Cell[] { cp1, cp2, cp3, cp4 });

            this.rightTableParams.Rows.Add(rowPara);
        }

        /// <summary>
        /// 创建行并添加数据
        /// </summary>
        /// <param name="param">param</param>
        public void creataRowInsertData(AlgoAPIParam param)
        {
            // // 根据参数数量创建行和单元格  第一个单元格  空  从第二个开始添加数据
            Row rowPara = new Row();
            Cell cp1 = new Cell();
            Cell cp2 = new Cell();
            cp2.Key = param.Code;
            Cell cp3 = new Cell();
            Cell cp4 = new Cell();

            ////往单元格添加数据
            //// 判断keyword，如果keyword有值则查询数据库得到相应的控件信息，否则添加控件为输入框
            string keyword = param.KeyWord;
            if (keyword != null && keyword != "")
            {  
                ////注释部分是因先去除多个keyword的可能
                //// 判断是否有多个keyword
                ////if (keyword.Contains(','))
                ////{
                if (param.Hasdetails == true)
                {
                    cp2.Text = param.Name;

        ////string[] keywords = keyword.Split(',');

                    Dictionary<string, string> controlMsg = null;

        ////List<Vocabulary> vocs = new List<Vocabulary>();

        ////for (int i = 0; i < keywords.Length; i++)
                    ////{

            // // 查询控件信息
                    ////controlMsg = this.service.getParasByCode(keywords[i]);
                    controlMsg = this.service.getParasByCode(keyword);
                    Vocabulary voc = new Vocabulary();
                    voc.C_DV_CODE = controlMsg["C_PARA_CODE"];
                    voc.C_DV_NAME = controlMsg["C_PARA_NAME"];

        ////    vocs.Add(voc);

        ////}

                    YssSelCombox selCombox = new YssSelCombox();
                    selCombox.YssIsMust = true;
                    selCombox.Items.Clear();
                    selCombox.Parameter = "C_DV_NAME";
                    selCombox.DisplayValue = "C_DV_CODE";
                    selCombox.DisplayName = "C_DV_NAME";
                    selCombox.SortColumn = "C_DV_NAME";

                    ////foreach (Vocabulary voc in vocs)
                    ////{
                    Yss.KRichEx.AutoFilter.Model.KTableEntity enty = new Yss.KRichEx.AutoFilter.Model.KTableEntity(voc);
                    selCombox.Items.Add(enty);
                    selCombox.Items.Add(enty);

        ////}
                    selCombox.Value = controlMsg["C_PARA_CODE"];
                    selCombox.IsRefresh = false;

                    cp3.InnerControl = selCombox;

                    ////匿名事件  当第一个下拉框选择后触发该事件
                    ////selCombox.SelectedValueChanged += delegate(object sender, EventArgs e)
                   //// {

            // // 获取选中的value(code)
                        string keyw = selCombox.SelectedItem.DisplayValue;

                        Control control = createControlAndIntoParam(keyw);
                        cp4.InnerControl = control;

        ////};

                } 
                else
                {
                    cp2.Text = param.Name;
                    Control selCombox = createControlAndIntoParam(keyword);
                    if (selCombox is YssSelCombox)
                    {
                        ((YssSelCombox)selCombox).YssIsMust = true;
                    }

                    cp3.InnerControl = selCombox;
                 }

        //// 根据keyword到数据库查询控件信息

            }
            else
            {
                // isdefault=false and source有值 文本框不可编辑 函数参数显示name的值
                if (null != param.Source && param.Source != "")
                {
                    cp2.Text = param.Name;
                    YssTextBox textBox = new YssTextBox();
                    textBox.YssReadOnly = true;
                    textBox.Text = param.Source;
                    cp3.InnerControl = textBox;
                }
                else
                {
                    // isdefault=false 但是source没有值 文本框可以编辑,添加文本框
                    cp2.Text = param.Name;
                    YssTextBox textBox = new YssTextBox();
                    textBox.YssIsMust = true;
                    cp3.InnerControl = textBox;
                }
            }

            rowPara.Cells.AddRange(new Cell[] { cp1, cp2, cp3, cp4 });
            
            this.rightTableParams.Rows.Add(rowPara);
        }

        /// <summary>
        /// 封装控件属性
        /// </summary>
        /// <param name="box">控件</param>
        /// <param name="ctlAttrs">属性</param>
        private void packControl(ImprovedTextBox box, string[] ctlAttrs) 
        {
            for (int i = 0; i < ctlAttrs.Length; i++)
            {
                string[] arrs = ctlAttrs[i].Split('=');
                if (Equals(arrs[0].Trim(), "YssCaption"))
                {
                    box.YssCaption = arrs[1];
                    continue;
                }

                if (Equals(arrs[0].Trim(), "Text"))
                {
                    box.Text = arrs[1];
                    continue;
                }

                if (Equals(arrs[0].Trim(), "TextAlign"))
                {
                    if (Equals(arrs[1].Trim(), "Right"))
                    {
                        box.TextAlign = HorizontalAlignment.Right;
                    }

                    continue;
                }

                if (Equals(arrs[0].Trim(), "YssKiloDelimiter"))
                {
                    if (Equals(arrs[1].Trim(), "true"))
                    {
                        box.YssKiloDelimiter = true;
                    }

                    continue;
                }

                if (Equals(arrs[0].Trim(), "YssLength"))
                {
                    box.YssLength = Convert.ToInt32(arrs[1]);
                    continue;
                }

                if (Equals(arrs[0].Trim(), "YssNumeric"))
                {
                    box.YssNumeric = arrs[1];
                    continue;
                }

                if (Equals(arrs[0].Trim(), "YssIsMust"))
                {
                    if (Equals(arrs[1].Trim(), "true"))
                    {
                        box.YssIsMust = true;
                    }

                    continue;
                    
                }

            }
        }

        /// <summary>
        /// 创建下拉框控件并设置属性
        /// </summary>
        /// <param name="keyWord">keyWord</param>
        /// <returns>Control</returns>
        private Control createControlAndIntoParam(string keyWord)
        {
            // // 查询控件信息
            ////IAdvAlgoService service = ServiceFactory.createService<IAdvAlgoService>();
            Dictionary<string, string> controlMsg = this.service.getParasByCode(keyWord);

        ////关键字来自配置keyparam_sql.xml，keyparam.xml
            string c_DS_ATTR = null;
            string c_CTL_ATTR = null;
            string c_PARA_NAME = null;
            string c_DV_CTL_TYPE = null;
            if (controlMsg.ContainsKey("C_DS_ATTR"))
            {
                c_DS_ATTR = controlMsg["C_DS_ATTR"];
            }

            if (controlMsg.ContainsKey("C_CTL_ATTR"))
            {
                c_CTL_ATTR = controlMsg["C_CTL_ATTR"];
            }

            if (controlMsg.ContainsKey("C_PARA_NAME"))
            {
                c_PARA_NAME = controlMsg["C_PARA_NAME"];
            }

            if (controlMsg.ContainsKey("C_DV_CTL_TYPE"))
            {
                c_DV_CTL_TYPE = controlMsg["C_DV_CTL_TYPE"];
            }
            

            if (null == controlMsg || controlMsg.Count > 0)
            {
                if (Equals(c_DV_CTL_TYPE, "TEXTBOX"))
                {
                    if (null != c_CTL_ATTR && c_CTL_ATTR.Contains("Sufix"))
                    {
                        c_CTL_ATTR = c_CTL_ATTR.Replace("\n", "$");
                        string[] ctlAttrs = c_CTL_ATTR.Split('$');
                        ImprovedTextBox box = new ImprovedTextBox();
                        box.YssIsMust = true;
                        packControl(box, ctlAttrs);
                        box.Sufix = "%";
                        return box;
                    }
            else
            {
                        c_CTL_ATTR = c_CTL_ATTR.Replace("\n", "$");
                        string[] ctlAttrs = c_CTL_ATTR.Split('$');
                        ImprovedTextBox box = new ImprovedTextBox();
                        box.YssIsMust = true;
                        packControl(box, ctlAttrs);
                        return box;
                    }
                } 
                else
                {
                    YssSelCombox selCombox = new YssSelCombox();

        ////selCombox.YssIsMust = true;

        ////|| null == c_PARA_NAME
                    if (null == c_DS_ATTR || null == c_CTL_ATTR)
                    {
                        List<Vocabulary> vocs = new List<Vocabulary>();

        ////这里手动添加下拉框值
                        foreach (string paramkey in controlMsg.Keys)
                        {
                            Vocabulary voc = new Vocabulary();
                            voc.C_DV_CODE = paramkey;
                            voc.C_DV_NAME = controlMsg[paramkey];
                            vocs.Add(voc);
                        }

                        selCombox.Parameter = "C_DV_NAME";
                        selCombox.DisplayValue = "C_DV_CODE";
                        selCombox.DisplayName = "C_DV_NAME";
                        selCombox.SortColumn = "C_DV_NAME";
                        foreach (Vocabulary voc in vocs)
                        {
                            Yss.KRichEx.AutoFilter.Model.KTableEntity enty = new Yss.KRichEx.AutoFilter.Model.KTableEntity(voc);
                            selCombox.Items.Add(enty);
                        }

                        selCombox.IsRefresh = false;
                        return selCombox;
                    }
                    else
                    {
                         // 解析并设置控件参数
                        c_DS_ATTR = c_DS_ATTR.Replace("\n", "$");
                        string[] dAttrs = c_DS_ATTR.Split('$');
                        c_CTL_ATTR = c_CTL_ATTR.Replace("\n", "$");
                        string[] ctlAttrs = c_CTL_ATTR.Split('$');

                        ControlMethodInfo controlMethodInfo = new ControlMethodInfo();
                        for (int i = 0; i < dAttrs.Length; i++)
                        {
                            if (dAttrs[i].Contains("fun="))
                            { 
                                string funCode = dAttrs[i].Substring(dAttrs[i].IndexOf("=") + 1).Trim();

                                selCombox.YssAssociaType = Associa.Generate(funCode);
                            }
                            else if (dAttrs[i].Contains("MethodName="))
                            {
                                if (dAttrs[i].Contains(','))
                                {
                                    dAttrs[i] = dAttrs[i].Replace(",", "");
                                }

                                controlMethodInfo.MethodName = dAttrs[i].Substring(dAttrs[i].IndexOf("=") + 1).Trim();
                            }
                            else if (dAttrs[i].Contains("MethodParamValues="))
                            {
                                if (Equals(dAttrs[i].Substring(dAttrs[i].IndexOf("=") + 1), ""))
                                {
                                    controlMethodInfo.MethodParamValues = null;
                                }
                                else
                                {
                                    string str_values = dAttrs[i].Substring(dAttrs[i].IndexOf("=") + 1);
                                    string method_param_values = str_values.EndsWith(",") == true ? str_values : str_values + ",";
                                    controlMethodInfo.MethodParamValues = new string[] { method_param_values };
                                }
                            }
                           
                        }

                        selCombox.MethodInfo = controlMethodInfo;

                        for (int i = 0; i < ctlAttrs.Length; i++)
                        {
                            if (ctlAttrs[i].Contains("DisplayName="))
                            {
                                selCombox.DisplayName = ctlAttrs[i].Substring(ctlAttrs[i].IndexOf("=") + 1).Trim();
                            }
                else if (ctlAttrs[i].Contains("DisplayValue="))
                            {
                                selCombox.DisplayValue = ctlAttrs[i].Substring(ctlAttrs[i].IndexOf("=") + 1).Trim();
                            }
                else if (ctlAttrs[i].Contains("Parameter="))
                            {
                                selCombox.Parameter = ctlAttrs[i].Substring(ctlAttrs[i].IndexOf("=") + 1).Trim();
                            }
                        }

                        return selCombox;
                    }
                }

            }

            return null;
        }

        /// <summary>
        /// 右侧树双击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void rightTreeTable_MouseDoubleClick(object sender, MouseEventArgs e)
        {
            insertData2FunAir();

        //// 每次插入后，都进行一次转译工作；转译的同时会把相关下拉框数据初始化到缓存中，当单击选中该函数的时候，值会赋值到相应的控件内
            previewFun();
        }

        /// <summary>
        /// 遍历行并往算法区域插入函数
        /// </summary>
        private void insertData2FunAir()
        {
            // // 修改bug  在浏览状态下可插入函数
            if (this.algo_status == ClsEnums.StatusSetting.YssBrow.ToString())
            {
                return;
            }

            ////if (null == this.tbLeftMain.SelectedRow)
            ////{

            // //    return;

        //// }

        ////////插入函数前先判断是否是旧算法，旧算法不支持插入函数
            ////////AdvAlgo aa = (AdvAlgo)this.tbLeftMain.SelectedRow.Tag;

        ////Row row_left = this.tbLeftMain.SelectedRow;

        ////if (null == row_left)
            ////{

            // //    return;

        ////}

            ////AdvAlgo aa = (AdvAlgo)row_left.Tag;
            if (null == this._tabCtrlMain || null == this._tabCtrlMain.SelectedTab)
            {
                return;
            }

            AdvAlgo aa = (AdvAlgo)this._tabCtrlMain.SelectedTab.Tag;
            string iSAllowInsert = isNewAlgo();
            if (Equals(iSAllowInsert, "False"))
            {
                ClsRetInfo c = new ClsRetInfo();
                c.infoType = ClsConstant.INFO_TP_WARN;
                c.infoContent = "该算法不支持此种插入函数的方式！";
                c.detailInfo = "该算法不支持此种插入函数的方式！";
                YssMessageBox.ShowCommonInfoText(c);
                return;
            }

            Row rowSel = this.rightTreeTable.SelectedRow;
            if (null == rowSel)
            {
                return;
            }

            AlgoAPI algoapi = (AlgoAPI)rowSel.Tag;
            if (Equals(algoapi.AlgoParent, "Root"))
            {
                return;
            }

            if (!this.allowInsert)
            {
                return;
             }

        //// 获取选中行
            AlgoAPI algoAPI = (AlgoAPI)this.rightTreeTable.SelectedRow.Tag;

            //// 根据选中的行去后台查询规则
            ////isdefault=true 将value的值填充到文本框 不可编辑
            ////isdefault=false and source有值 文本框不可编辑 函数参数显示name的值
            ////isdefault=false 但是source没有值 文本框可以编辑
            Dictionary<string, bool> rule = this.service.getRuleByFunCode(algoAPI.AlgoCode);

            rules = rule;

            ////遍历参数表
            RowCollection rows = this.rightTableParams.Rows;

            StringBuilder paramName = new StringBuilder();

            for (int i = 0; i < rows.Count; i++)
        {
            // // 取到行，然后遍历单元格获取数据
                Row row = rows[i];
                CellCollection cells = row.Cells;
                for (int j = 1; j < cells.Count; j++)
                {
                    Cell cell = cells[j];
                    if (cell.Text != null && cell.Text != "")
        {
            // // 处理：isdefault=false and source有值 文本框不可编辑 函数参数显示name的值
                        if (cell.Key != null && cell.Key != "" && rule[cell.Key] == true)
                        {
                            paramName.Append(cell.Text).Append(",");
                            break;
                        }
                     }

        ////---------------------------------------------------------------------------------------
                    ////一行一个控件
                    if (cell.InnerControl != null && (j + 1) < cells.Count && cells[j + 1].InnerControl == null)
                    {
                        paramName.Append(cell.InnerControl.Text).Append(",");

                        //// 获取该参数的code
                        string paramcode = cells[j - 1].Key;
                        string flag = this.service.hasKeyWord(paramcode);
                    }

                    ////两个下拉框，进行处理===>Dictionary<string, List<AlgoAPIParam>>  key存放第一个下拉框的值   value存放第二个下拉框的text  value
                    ////一行两个控件
                    if (j == (cells.Count - 1) && cell.InnerControl != null)
        {
            // // 当两个下拉框只选第一个的时候，获取第一个下拉框的值，若第二个有值则显示第二个
                         if (null == cell.Text || "" == cell.Text || Equals("", cell.Text))
                        {
                            paramName.Append(cells[j - 1].Text).Append(",");
                        } 
                        else
                        {
                            paramName.Append(cell.Text).Append(",");
                        }
                        
                    }
                }
            }

            //// 处理paramName格式并返回符合格式的函数字符串  函数2(组合，日期)
            string funParam = formatParamStr(paramName.ToString(), algoAPI.AlgoText);

        //// 获取A区选中的行得到相应的文本域对象
            AdvAlgo algo = (AdvAlgo)this.tbLeftMain.SelectedRow.Tag;

            YssTextBox control = (YssTextBox)controlDic["test_main_algo_top_" + algo.C_ALGO_CODE];

        ////验证当前有没有选中函数并且选中的函数是否和现在操作的函数是同一个函数
            //// 根据当前行数据解析出各个函数   SelectionStart当前光标位置
            ////替换功能

            //// 当前光标到该行首个字符间没有分号
            ////if ("".Equals(control.Text))
            ////{

            // //    return;

        ////}

            string current_line_str = control.Text.Substring(control.GetFirstCharIndexOfCurrentLine(), control.SelectionStart - control.GetFirstCharIndexOfCurrentLine());
            if (control.SelectionStart < control.Text.Length && current_line_str.Length > 0 && !current_line_str.Contains(';') && !current_line_str.Contains('+') && !current_line_str.Contains('-') && !current_line_str.Contains('*') && !current_line_str.Contains('/') && !current_line_str.Contains('%'))
            {
                string funJson = this.service.getFunList(control.Text);
                List<string> funList = (List<string>)JsonUtil.toObject(funJson, typeof(List<string>));

                //// 当前光标位置
                int currentPointLocation = control.SelectionStart;

                int funStartLength = 0;
                int funEndLength = 0;
                int count = 0;
                if (null != funList)
                {
                    for (int i = 0; i < funList.Count; i++)
                    {
                        string funstr = funList[i];

                        int index3 = control.Text.IndexOf(funstr, count);

        ////函数开始位置
                        funStartLength = 0 + index3;

        ////函数结束位置
                        funEndLength = funstr.Length + funStartLength;

        ////光标在函数内部
                        if (funStartLength <= currentPointLocation && currentPointLocation <= funEndLength)
                        {
                            break;
                        }
            else
            {
                            count = funstr.Length + index3;
                        }
                    }

                    if (funStartLength != 0 && funEndLength != 0)
                    {
                        string funEnd = control.Text.Substring(funEndLength, control.Text.Length - funEndLength);

        ////string funEnd = control.Text.Substring(currentPointLocation, control.Text.Length - currentPointLocation);

        ////string funStart = control.Text.Substring(0, currentPointLocation);
                        string funStart = control.Text.Substring(0, funStartLength);
                        if (hasNullParam(funParam))
                        {
                        }
            else
            {
                            control.Text = funStart + funParam + funEnd;
                        }

                        ////control.SelectionStart = currentPointLocation + funParam.Length;
                        control.SelectionStart = funStart.Length + funParam.Length;
                        control.Focus();
                        return;
                    }
                }
            }


            insertStates(control, funParam);
           
        }

        /// <summary>
        /// 插入数据
        /// </summary>
        /// <param name="control">文本控件</param>
        /// <param name="funParam">当前函数</param>
        private void insertStates(YssTextBox control, string funParam)
        {
            // 插入前判断光标前最后一个\r\n之间是否有字符，如果有字符，判断光标前是否有运算符存在，如果有运算符存在，则插入函数
            // 如果没有运算符存在，替换改函数；如果没有字符，追加：【变量=函数;】
            string controlText = control.Text;
            int currtPointIndex = control.SelectionStart;
            Row right_sel_row = this.rightTreeTable.SelectedRow;
            if (null == right_sel_row)
            {
                return;
            }

            AlgoAPI right_algo_api = (AlgoAPI)right_sel_row.Tag;

        // 当前算法域内没有数据
            if (null == controlText || Equals("", controlText))
            {
                if (hasNullParam(funParam))
                {
                }
            else
            {
                    funParam = right_algo_api.AlgoCode + " = " + funParam;
                }
                
            }
            else
            {
                 // 找到光标前的字符串，从该串内找到最后一个\r\n的位置，然后再截取出该位置到光标之间的串
                string sun_controlText = controlText.Substring(0, currtPointIndex);
                int lastTagIndex = sun_controlText.LastIndexOf("\r\n") + 2;

                 // 目标字符串   ""   b = f1()  b =  b
                if (lastTagIndex >= 2 && currtPointIndex - lastTagIndex >= 0)
                {
                    string targetStr = controlText.Substring(lastTagIndex, currtPointIndex - lastTagIndex);
                    if (targetStr.Contains(';'))
                    {
                        funParam = "\r\n" + right_algo_api.AlgoCode + " = " + funParam;
                    } 
                    else
                    {
                        if (targetStr.Trim().Length <= 0)
                        {
                            funParam = right_algo_api.AlgoCode + " = " + funParam;
                        }
                        else
                        {
                             // //拿targetStr到后台解析，返回函数集合，从targetStr中拿到最后一个函数的索引，判断索引+函数长度 --> 当前光标位置有没有运算符
                            // 若没有运算符，则替换该函数；若返回的函数集合为空，判断该串内有没有=，如果有等号，则替换等号到光标之间的子串
                            string jsonFunList = this.service.getFunList(targetStr.Trim());
                            if (null != jsonFunList && !Equals("", jsonFunList))
                            {
                                List<string> fun_list = (List<string>)JsonUtil.toObject(jsonFunList, typeof(List<string>));
                                string last_fun = fun_list[fun_list.Count - 1];
                                int last_fun_index = targetStr.IndexOf(last_fun);
                                int targetstr_index = controlText.IndexOf(targetStr);

                                int current_line_first_char_index = control.GetFirstCharIndexOfCurrentLine();
                                string tstr = targetStr.Substring(last_fun_index, currtPointIndex - last_fun_index - current_line_first_char_index);
                                if (tstr.Trim().Length >= 0)
                                {
                                    bool has_oper = true;
                                    for (int i = 0; i < operatorArr.Length; i++)
                                    {
                                        if (tstr.Contains(operatorArr[i]))
                                        {
                                            has_oper = false;
                                            break;
                                        }
                                     }

                                    // 没有运算符，替换该函数
                                    if (has_oper)
                                    {
                                        if (hasNullParam(funParam))
                                        {
                                        }
                                        else
                                        {
                                            string header_str = controlText.Substring(0, current_line_first_char_index + last_fun_index);
                                            string end_str = controlText.Substring(currtPointIndex, controlText.Length - currtPointIndex);

                                            control.Text = header_str + funParam + end_str;
                                        }
                                       
                                        control.SelectionStart = currtPointIndex;
                                        control.Focus();
                                        return;
                                    }
                                }
                            }
                            else
                            {
                                if (targetStr.Contains("="))
                                {
                                    string[] oparr = targetStr.Split('=');
                                    if (oparr[1].Trim().Length > 0)
                                    {
                                        bool has_oper = true;
                                        for (int i = 0; i < operatorArr.Length; i++)
                                        {
                                            if (oparr[1].Contains(operatorArr[i]))
                                            {
                                                has_oper = false;
                                                break;
                                            }
                                        }

                                        if (has_oper)
                                        {
                                            if (hasNullParam(funParam))
                                            {
                                            }
                                            else
                                            {
                                                string refun = targetStr.Replace(oparr[1], funParam);
                                                controlText.Replace(targetStr, refun);
                                                control.Text = controlText;
                                            }
                                            
                                            control.SelectionStart = currtPointIndex;
                                            control.Focus();
                                            return;
                                        }
                                    }
                                    else
                                    {
                                    }
                                }
                                else
                                {
                                     // 如果没有等号，则替换掉上个\r\n到光标之间的串
                                    string first_str = controlText.Substring(0, currtPointIndex);
                                    int num = first_str.LastIndexOf("\r\n");
                                    string ss = controlText.Substring(0, num + 2);
                                    string end_str = controlText.Substring(currtPointIndex);
                                    if (hasNullParam(funParam))
                                    {
                                    } 
                                    else
                                    {
                                        control.Text = ss + right_algo_api.AlgoCode + " = " + funParam + end_str;
                                    }
                                    
                                    control.SelectionStart = currtPointIndex;
                                    control.Focus();
                                    return;
                                }
                            }

                        }
                    }
                }
                else
                {
                    // 第一行
                    string targetStr = controlText.Substring(0, currtPointIndex);
                    if (targetStr.Contains(';'))
                    {
                        funParam = "\r\n" + right_algo_api.AlgoCode + " = " + funParam;
                    } 
                    else
                    {
                         // 拿targetStr到后台解析，返回函数集合，从targetStr中拿到最后一个函数的索引，判断索引+函数长度 --> 当前光标位置有没有运算符
                        // 若没有运算符，则替换该函数；若返回的函数集合为空，判断该串内有没有=，如果有等号，则替换等号到光标之间的子串
                        string jsonFunList = this.service.getFunList(targetStr.Trim());
                        if (null != jsonFunList && !Equals("", jsonFunList))
                        {
                            List<string> fun_list = (List<string>)JsonUtil.toObject(jsonFunList, typeof(List<string>));
                            string last_fun = fun_list[fun_list.Count - 1];
                            int last_fun_index = targetStr.IndexOf(last_fun);
                            string tstr = targetStr.Substring(last_fun_index + last_fun.Length, currtPointIndex - last_fun_index - last_fun.Length);
                            if (tstr.Trim().Length >= 0)
                            {
                                bool has_oper = true;
                                for (int i = 0; i < operatorArr.Length; i++)
                                {
                                    if (tstr.Contains(operatorArr[i]))
                                    {
                                        has_oper = false;
                                        break;
                                    }
                                 }

        //// 没有运算符，替换该函数
                                if (has_oper)
                                {
                                    int lfun_index = targetStr.IndexOf(last_fun);
                                    string refun = targetStr.Replace(last_fun, funParam);
                                    if (hasNullParam(refun))
                                    {
                                    }
            else
            {
                                        controlText.Replace(targetStr, refun);
                                     }

        ////controlText.Replace(targetStr, refun);
                                    control.Text = refun;
                                    control.SelectionStart = currtPointIndex;
                                    control.Focus();
                                    return;
                                }
                            }
                        }
            else
            {
                            if (targetStr.Contains("="))
                            {
                                string[] oparr = targetStr.Split('=');
                                if (oparr[1].Trim().Length > 0)
                                {
                                    bool has_oper = true;
                                    for (int i = 0; i < operatorArr.Length; i++)
                                    {
                                        if (oparr[1].Contains(operatorArr[i]))
                                        {
                                            has_oper = false;
                                            break;
                                        }
                                    }

                                    if (has_oper)
                                    {
                                        string refun = targetStr.Replace(oparr[1], funParam);
                                        if (hasNullParam(refun))
                                        {
                                        } 
                                        else
                                        {
                                            controlText.Replace(targetStr, refun);
                                         }

                                        control.Text = controlText;
                                        control.SelectionStart = currtPointIndex;
                                        control.Focus();
                                        return;
                                    }
                                }
                                else
                                {
                                }
                                }
                                else
                                {
                                // 如果没有等号，则替换掉上个\r\n到光标之间的串
                                string first_str = controlText.Substring(0, currtPointIndex);
                                int num = first_str.IndexOf("\r\n");
                                string ss = controlText.Substring(0, num + 2);
                                string end_str = controlText.Substring(currtPointIndex);
                                string first_str_fun = "";
                                if (hasNullParam(funParam))
                                {
                                }
                                else
                                {
                                    first_str_fun = right_algo_api.AlgoCode + " = " + funParam;
                                    control.Text = ss + first_str_fun + end_str;
                                }

                                control.SelectionStart = currtPointIndex + first_str_fun.Length + 1;
                                control.Focus();
                                return;
                            }
                        }
                    }

                    
                }
           }

            //// 如果当前行有逗号，则再进行插入的时候换行插入
            ////int first_char_index = control.GetFirstCharIndexOfCurrentLine();

        ////if (currtPointIndex > first_char_index)
            ////{

            // //    string sign_str = controlText.Substring(first_char_index, currtPointIndex - first_char_index);

        ////    if (sign_str.Contains(';'))
            ////    {

            // //        funParam = "\r\n" + right_algo_api.AlgoCode + " = " + funParam;

        ////     }

        ////}
            
            
            //// 正常添加数据到文本域
            int pos = control.SelectionStart;
            int length = control.Text.Length;
            if (pos < length || pos == 0)
            {
                if (hasNullParam(funParam))
                {
                }
            else
            {
                    control.Text = control.Text.Insert(pos, funParam);
                }
                
            }
            else
            {
                if (hasNullParam(funParam))
                {
                }
            else
            {
                    control.Text += funParam;
                }
                
            }

            control.SelectionStart = currtPointIndex + funParam.Length;
            control.Focus();
        }

        /// <summary>
        /// 判断插入的函数内部是否有空参数
        /// </summary>
        /// <param name="funParam">函数字符串</param>
        /// <returns>是否有空参数</returns>
        private bool hasNullParam(string funParam)
        {
            bool has_null_param = false;
            Regex reg = new Regex(@"\(([^)]*)\)");
            Match m = reg.Match(funParam);
            if (m.Success)
            {
                string ps = m.Result("$1");
                string[] psarr = ps.Split(',');
                for (int i = 0; i < psarr.Length; i++)
                {
                    if ("".Equals(psarr[i]))
                    {
                        has_null_param = true;
                    }
                    
                }
            }

            return has_null_param;
        }



        /// <summary>
        /// 格式化函数参数，返回符合格式的函数字符串：函数2(组合，日期)
        /// </summary>
        /// <param name="paramName">参数字符串</param>
        /// <param name="funName">函数名</param>
        /// <returns>符合规范的函数串</returns>
        public string formatParamStr(string paramName, string funName)
        {
            // //1.去掉paramName内最后一个","
            paramName = paramName.Substring(0, paramName.Length - 1);
            return funName + "(" + paramName + ")";
        }

        /// <summary>
        /// 打开list界面标签
        /// </summary>
        private void openPage()
        {
            // //AdvAlgo algo = (AdvAlgo)this.tbLeftMain.SelectedRow.Tag;
            Row srow = this.tbLeftMain.SelectedRow;
            
            if (null != srow && srow.HasChild)
            {
                return;
            }

            if (null == srow)
            {
                return;
            }

            AdvAlgo algo = (AdvAlgo)srow.Tag;

            //// 判断选中的算法是否是已审核，若已审核，则状态改为浏览状态
            string algo_status = this.service.checkAlgoStatus(algo.C_ALGO_CODE);
            if (Equals(algo_status, "1"))
            {
                this.algo_status = ClsEnums.StatusSetting.YssBrow.ToString();
            }

            string iSNew = isNewAlgo();
            if (Equals(iSNew, "False"))
            {
                rightSplitter.Enabled = false;
                this.rightSplitter.Expanded = false;
            }
            else
            {
                rightSplitter.Enabled = true;
                if (this.rightSplitter.Expanded != true)
                {
                    this.rightSplitter.Expanded = true;
                }
            }

            if (!this.ShowDetailNewPage || this.Visible == false)
            {
                return;
            }

            //// this.CreateTabControlMain();

            Row row = this.TableLeftMain.SelectedRow;

            if (this.CheckNewPage(row, false))
            {
                Yss.Controls.TabPage tabItem = this.CreateTabItem(row.Cells[0].Text, algo.C_ALGO_CODE);
            }

            string s = this.algo_status.ToString();

            if (this.algo_status != ClsEnums.StatusSetting.YssEdit.ToString() && this.algo_status != ClsEnums.StatusSetting.YssAdd.ToString())
            {
                this.algo_status = ClsEnums.StatusSetting.YssBrow.ToString();
            }
        }

        /// <summary>
        /// 单击B区数据行后刷新按钮状态
        /// </summary>
        /// <param name="pojo">数据对象</param>
        protected override void setButtonStaAfterTbMainClickMVC(BasePojo pojo)
        {
            base.setButtonStaAfterTbMainClickMVC(pojo);
        }

        /// <summary>
        /// 左侧树单击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void tbLeftMain_Click(object sender, EventArgs e)
        {
            // // 修改审核界面按钮状态
            modifyBtnStatus();
            
            //// 单击打开界面  封装方法
            openPage();
            Row srow = this.tbLeftMain.SelectedRow;
            if (null != srow && srow.HasChild)
            {
                return;
            }

            if (null == srow)
            {
                return;
            }

            AdvAlgo algo = (AdvAlgo)srow.Tag;

        //// 控制数据安全问题，在未开启审核按钮的时候  可以修改算法，在开启审核机制的时候需要反审核进行修改
            setButtonStaAfterTbMainClickMVC(algo);
            if (null == this._tabCtrlMain)
            {
                return;
            }

            TabPageCollection tpc = this._tabCtrlMain.TabPages;
            for (int i = 0; i < tpc.Count; i++)
        {
            // //Row row = (Row)tpc[i].Tag;

        ////if (null == row.Tag)
                ////{

            // //    return;

        ////}

                ////AdvAlgo aa = (AdvAlgo)row.Tag;
                AdvAlgo aa = (AdvAlgo)tpc[i].Tag;
                if (algo.C_ALGO_CODE.Equals(aa.C_ALGO_CODE))
                {
                    int page_index = tpc[i].Index;
                    this._tabCtrlMain.SelectedTab = this._tabCtrlMain.TabPages[page_index];
                }
            }
           
        }

        /// <summary>
        /// 提供方法给子类覆写
        /// 移除未审核界面所有按钮
        /// </summary>
        public void removeUncheckFormButtons()
        {
            Dictionary<string, List<string>> dic = btnBar.getAllButtonNames();
            foreach (string name in dic.Keys)
            {
                if (name == ClsButtonName.BtnCopy)
                {
                    removeCurrentButton(ClsButtonName.BtnCopy);
                    continue;
                }

                if (name == ClsButtonName.ToolExport)
                {
                    List<string> list = dic[name];
                    if (list.Count > 0)
                    {
                        foreach (string subName in list)
                        {
                            removeCurrentButton(subName);
                        }
                    }


                    removeCurrentButton(name);
                    continue;
                }

                if (name == ClsButtonName.ToolPreview)
                {
                    List<string> list = dic[name];
                    if (list.Count > 0)
                    {
                        foreach (string subName in list)
                        {
                            removeCurrentButton(subName);
                        }
                    }


                    removeCurrentButton(name);
                    continue;
                }
            }
        }

        /// <summary>
        /// 移除按钮
        /// </summary>
        /// <param name="buttonName">按钮名称</param>
        private void removeCurrentButton(string buttonName)
        {
            ClsButtonInfo clsButtonInfo = btnBar.getButton(buttonName);
            if (clsButtonInfo != null)
            {
                btnBar.removeButton(clsButtonInfo);
            }
        }

        /// <summary>
        /// 修改审核界面按钮状态
        /// </summary>
        private void modifyBtnStatus()
        {
            // //if (this.algo_status == ClsEnums.StatusSetting.YssEdit.ToString())
            ////{

            // //    return;

        ////}

            if (null == this.tbLeftMain.SelectedRow)
            {
                return;
            }

            ////AdvAlgo algo = (AdvAlgo)this.tbLeftMain.SelectedRow.Tag;
            Row row_left = this.tbLeftMain.SelectedRow;
            if (null == row_left)
            {
                return;
            }

            AdvAlgo algo = (AdvAlgo)row_left.Tag;
            if (null == algo || algo.C_ALGO_CODE == "" || null == algo.C_ALGO_CODE)
            {
                return;
            }

            ////if (this.modify_algo_only_one.Equals(algo.C_ALGO_CODE))
            ////{

            // //    return;

        ////}

            //// 获取所有按钮
            Dictionary<string, List<string>> dic = btnBar.getAllButtonNames();

            //// 如果满足该条件，重置审核栏按钮，只保留一个保存按钮可用
            if (this.modify_algo_only_one.Equals(algo.C_ALGO_CODE))
            {
                foreach (string name in dic.Keys)
                {
                    ClsButtonInfo bInfo = btnBar.getButton(name);
                    if (name == ClsButtonName.BtnUnAudit || name == ClsButtonName.BtnAudit || name == ClsButtonName.BtnDelete || name == ClsButtonName.BtnEdit)
                    {
                        ClsButtonInfo btnInfo = btnBar.getButton(name);
                        btnInfo.Enabled = false;
                    }
            else
            {
                        bInfo.Enabled = true;
                    }
                   
                }

                return;
            }

            string algoStatus = this.service.checkAlgoStatus(algo.C_ALGO_CODE);

            foreach (string name in dic.Keys)
            {
                ClsButtonInfo bInfo = btnBar.getButton(name);
                bInfo.Enabled = true;

                ////未审核；
                if (Equals(algoStatus, "0"))
                {
                    if (name == ClsButtonName.BtnUnAudit || name == "btnSave1")
                    {
                        ClsButtonInfo btnInfo = btnBar.getButton(name);
                        btnInfo.Enabled = false;
                    }


                    //// 单击A区  控制修改按钮  不可用（有标签页存在，可用；否则不可用）
                    if (name == ClsButtonName.BtnEdit)
                    {
                        if (null != this._tabCtrlMain && this._tabCtrlMain.TabPages.Count > 0)
                        {
                        }
            else
            {
                            ClsButtonInfo btnInfo = btnBar.getButton(name);
                            btnInfo.Enabled = false;
                        }
                    }
                 }

        ////已审核；
                if (Equals(algoStatus, "1"))
                {
                    if (name == ClsButtonName.BtnEdit || name == ClsButtonName.BtnDelete || name == ClsButtonName.BtnAudit || name == "btnSave1")
                    {
                        ClsButtonInfo btnInfo = btnBar.getButton(name);
                        btnInfo.Enabled = false;
                    }

                }

            }
        }
       
        /// <summary>
        /// 添加参考算法信息
        /// </summary>
        /// <param name="tabItem">标签页</param>
        public void addCKAlgo(Yss.Controls.TabPage tabItem)
        {
            if (algoSetParam != null && algoSetParam.C_AlgoCode != null && algoSetParam.C_AlgoCode != "")
            {
                AdvAlgo a = (AdvAlgo)this.service.getAlgoByCode(algoSetParam.C_AlgoCode);

                controlDic["text_main_algo_top" + a.C_ALGO_CODE].Text = a.C_ALGO_FORMULA;
                controlDic["test_main_algo_below_" + a.C_ALGO_CODE].Text = a.C_DESC;
                
            }
        }

         /// <summary>
        /// 修改
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnEdit_Click(object sender, EventArgs e)
        {
            Row row_left = this.tbLeftMain.SelectedRow;
            if (null == row_left)
            {
                return;
            }

            this.allowInsert = true;
            AdvAlgo algo = (AdvAlgo)row_left.Tag;

        //// 记录修改状态的标签页，多个标签页只允许存在一个为修改状态
            this.modify_algo_only_one = algo.C_ALGO_CODE;

            string iSNew = this.service.isNewAlgo(algo.C_ALGO_CODE);

        ////旧算法不允许使用右侧参数，故展开线不可用
            //// 单击修改按钮，如果右侧栏已经隐藏且不可用，则改变其状态
            if (!this.rightSplitter.Expanded && !this.rightSplitter.Enabled)
            {
                string iSNewA = isNewAlgo();
                if (Equals(iSNewA, "False"))
                {
                    rightSplitter.Enabled = false;
                    this.rightSplitter.Expanded = false;
                }
            else
            {
                    this.rightSplitter.Expanded = true;
                    this.rightSplitter.Enabled = true;
                }
                
            }

            this.algo_status = ClsEnums.StatusSetting.YssEdit.ToString();
            if (controlDic.ContainsKey("test_main_algo_top_" + algo.C_ALGO_CODE))
            {
                modifyControlStatus();
            } 
            else
            {
                Row row = this.tbLeftMain.SelectedRow;
                openPage();
                modifyBtnStatus();
            }

            contrlBtnStatus();

            //// 获取所有按钮
            Dictionary<string, List<string>> dic = btnBar.getAllButtonNames();

            foreach (string name in dic.Keys)
            {
                ClsButtonInfo bInfo = btnBar.getButton(name);
                bInfo.Enabled = true;

                if (this.algo_status == ClsEnums.StatusSetting.YssEdit.ToString() || this.algo_status == ClsEnums.StatusSetting.YssAdd.ToString())
                {
                    if (name == ClsButtonName.BtnEdit || name == ClsButtonName.BtnAudit || name == ClsButtonName.BtnDelete || name == ClsButtonName.BtnUnAudit)
                    {
                        ClsButtonInfo btnInfo = btnBar.getButton(name);
                        btnInfo.Enabled = false;
                    }
                }

            }
        }

        /// <summary>
        /// 改变控件状态,修改
        /// </summary>
        private void modifyControlStatus()
        {
            Row row_left = this.tbLeftMain.SelectedRow;
            if (null == row_left)
            {
                return;
            }

            AdvAlgo algo = (AdvAlgo)row_left.Tag;
            YssTextBox c1 = (YssTextBox)controlDic["test_main_algo_top_" + algo.C_ALGO_CODE];
            Yss.Controls.RichTextBox c2 = (Yss.Controls.RichTextBox)controlDic["test_main_algo_below_" + algo.C_ALGO_CODE];

            if (c1.IsDisposed)
            {
                return;
            }

            if (null != c1)
            {
                c1.YssReadOnly = false;
                c1.Enabled = true;
                c1.YssIsMust = true;

        ////c2.Enabled = true;
                c2.ReadOnly = false;
            }
            
            ////打开右侧隐藏部分
            if (Equals(isNewAlgo(), "False"))
            {
                this.rightSplitter.Expanded = false;
            }
            else
            {
                this.rightSplitter.Expanded = true;
            }
            
        }



       /// <summary>
        /// 算法代码区域鼠标单击事件触发函数
       /// </summary>
       /// <param name="selfTxtAlgo">控件</param>
        private void selfAlgoText_SelectFun(YssTextBox selfTxtAlgo)
        {
            // //if (this.status == ClsEnums.StatusSetting.YssBrow)
            ////{

            // //    return;

        ////}

            string funStr = getFunStr(selfTxtAlgo);
            if (null == funStr)
            {
                return;
            }

            string funCode = analysisFunStr(funStr, selfTxtAlgo);
        }

        /// <summary>
        /// 分析函数字符串并返回鼠标所在函数的code
        /// </summary>
        /// <param name="funStr">函数字符串</param>
        /// <param name="selfTxtAlgo">控件</param>
        /// <returns>函数code</returns>
        private string analysisFunStr(string funStr, YssTextBox selfTxtAlgo)
        {
            string currtFun = funStr;

            string funName = "";

            //// 获取函数名
            if (currtFun == null)
            {
                return currtFun;
            }
            else
            {
                funName = currtFun.Split('(')[0];
           }

            if (null == funName || funName == "")
            {
                return "";
            }

            ////检测该函数是否为新算法中的函数，如果为新算法的函数，则放行，否则return
            string flag = this.service.checkFunName(funName);
            if (Equals(flag, "False"))
            {
                return "";
            }

            ////遍历函数树，根据函数名找出对应的函数行并手动触发单选事件，然后对参数进行赋值
            loopFunTree(funName);
            loopParamTableInsertVal(currtFun);


            return funName;
        }

        /// <summary>
        /// 遍历参数表，解析当前函数并给参数表赋值
        /// </summary>
        /// <param name="currtFun">currtFun</param>
        private void loopParamTableInsertVal(string currtFun)
        {
            if (null == currtFun || currtFun == "")
            {
                return;
            }

            ////解析函数各个参数
            string[] arr_params = parsePram(currtFun);

            ////遍历参数表
            RowCollection rows = this.rightTableParams.Rows;
            if (arr_params.Length != rows.Count)
            {
            // //MessageBox.Show("请确定参数的个数！");
                return;
            }

            for (int i = 0; i < rows.Count; i++)
            {
            // // 取到行，然后遍历单元格获取数据
                Row row = rows[i];
                CellCollection cells = row.Cells;
                for (int j = 1; j < cells.Count; j++)
                {
                    Cell cell = cells[j];
                    if (cell.Text != null && cell.Text != "")
                {
                    // // 处理：isdefault=false and source有值 文本框不可编辑 函数参数显示name的值
                        if (cell.Key != null && cell.Key != "" && rules.ContainsKey(cell.Key) && rules[cell.Key] == true)
                {
                    // //关键字，不予赋值
                            break;
                        }
                    }

                    if (cell.InnerControl != null && cell.InnerControl is YssTextBox)
                    {
                        cell.InnerControl.Text = arr_params[i];
                    }

                    if (cell.InnerControl != null && cell.InnerControl is ImprovedTextBox)
                    {
                        cell.InnerControl.Text = arr_params[i];
                    }

                    if (cell.InnerControl != null && cell.InnerControl is YssSelCombox)
                     {
                        // // 根据参数到后台查询，查出该参数的关键字，根据关键字查出该参数是否有明细，如果有明细，则有两个下拉框，否则一个下拉框
                        string param_has_detail = this.service.paramHasDetail(arr_params[i]);
                        if (Equals("True", param_has_detail))
                        {
                            if ((j + 1 == cells.Count - 1) && null != cells[j + 1].InnerControl && cells[j + 1].InnerControl is YssSelCombox)
                            {
                                string param_code = this.service.getParamCodeByParamName(arr_params[i]);
                                ((YssSelCombox)cells[j + 1].InnerControl).Value = param_code;
                            }
                        } 
                        else
                        {
                            // 根据参数名查出参数code
                            string param_code = this.service.getParamCodeByParamName(arr_params[i]);
                            if (!Equals("", param_code))
                            {
                                ((YssSelCombox)cell.InnerControl).Value = param_code;
                            }
                            
                        }
                        
                    }
                }
            }

        }

        /// <summary>
        /// 解析函数，返回参数数组
        /// </summary>
        /// <param name="currtFun">函数</param>
        /// <returns>参数数组</returns>
        private string[] parsePram(string currtFun)
        {
            string[] arrTemp = currtFun.Split(')');
            string[] arr = arrTemp[0].Split('(');
            string[] arrParam = arr[1].Split(',');
            return arrParam;
        }

        /// <summary>
        /// 遍历函数树，根据函数名找出对应的函数行并手动触发单选事件
        /// </summary>
        /// <param name="funName">函数名</param>
        private void loopFunTree(string funName)
        {
            RowCollection rows = this.rightTreeTable.Rows;
            foreach (Row row in rows)
            {
                if (row.SubRows != null)
                {
                    RowCollection rc = row.SubRows;
                    foreach (Row r in rc)
                    {
                        Cell cell = r.Cells[0];
                        if (cell != null && cell.Text != null && cell.Text != "" && Equals(cell.Text, funName.Trim()))
        {
            // //触发rightTreeTable_SelectionChanged事件  重绘参数表
                            ////rightTreeTable_SelectionChanged(r, new Yss.KTable.Events.RowEventArgs(r));
                            r.Selected = true;
                            rightTreeTable_Click(r, new EventArgs());
                        }
                    }
                }
            }
        }

        /// <summary>
        /// 分析函数字符串
        /// </summary>
        /// <param name="selfTxtAlgo">控件</param>
        /// <returns>string</returns>
        private string getFunStr(YssTextBox selfTxtAlgo)
        {
            string finalFunStr = "";

        //// 根据当前行数据解析出各个函数   SelectionStart当前光标位置
            string funJson = this.service.getFunList(selfTxtAlgo.Text);
            List<string> funList = (List<string>)JsonUtil.toObject(funJson, typeof(List<string>));

            //// 当前光标位置
            int currentPointLocation = selfTxtAlgo.SelectionStart;

            int count = 0;
            if (null == funList || Equals("", funList))
            {
                return "";
            }

            for (int i = 0; i < funList.Count; i++)
            {
                string funstr = funList[i];

                int index3 = selfTxtAlgo.Text.IndexOf(funstr, count);

        ////函数开始位置
                int funStartLength = 0 + index3;

        ////函数结束位置
                int funEndLength = funstr.Length + funStartLength;

        ////光标在函数内部
                if (funStartLength <= currentPointLocation && currentPointLocation <= funEndLength)
                {
                    finalFunStr = funstr;
                    break;
                }
            else
            {
                    count = funstr.Length + index3;
                }


             }

        ////}

            return finalFunStr;
        }

        /// <summary>
        /// 函数树单击事件
        /// </summary>
        /// <param name="sender">当前行</param>
        /// <param name="e">事件</param>
        private void rightTreeTable_Click(object sender, EventArgs e)
        {
            // //Row row = this.rightTreeTable.SelectedRow;

        ////if (null == row)
            ////{

            // //    return;

        ////}

            ////AlgoAPI algoAPI = (AlgoAPI)row.Tag;

            ////if (null == row)
            ////{

            // //    return;

        ////}

            ////if (Equals(algoAPI.AlgoParent, "Root"))
            ////{

            // //    return;

        ////}

            //////// 添加函数说明
            ////funDect(algoAPI.AlgoDesc);

        //////// 获取参数集合
            ////string paramsJson = this.service.getDataAPIByCode(algoAPI.AlgoCode);

        ////DataAPI dataAPI = (DataAPI)JsonUtil.toObject(paramsJson, typeof(DataAPI));

        ////////画参数列表
            ////creataParamTable(dataAPI);
        }

        /// <summary>
        /// 预览事件
        /// </summary>
        /// <param name="sender">按钮</param>
        /// <param name="e">事件</param>
        private void previewBnt_Click(object sender, EventArgs e)
        {
        }

        /// <summary>
        /// 得到预览后算法英文
        /// </summary>
        /// <returns>string</returns>
        private string getPreviewFullMsg()
        {
            return previewFun();
        }

        /// <summary>
        /// 预览
        /// </summary>
        /// <returns>翻译后的算法</returns>
        private string previewFun()
        {
            Row r = this.tbLeftMain.SelectedRow;
            if (null == r)
            {
                return "";
            }

            AdvAlgo algo = (AdvAlgo)r.Tag;
            YssTextBox box = null;
            if (controlDic.ContainsKey("test_main_algo_top_" + algo.C_ALGO_CODE))
            {
                box = (YssTextBox)controlDic["test_main_algo_top_" + algo.C_ALGO_CODE];
            }

            if (null == box || Equals("", box.Text))
            {
                return "";
            }
            
            string msg = this.service.preview(box.Text);

            if (null != msg && msg != "")
            {
                if (msg.Contains("FalseFun"))
                {
                    string[] warnMsg = msg.Split('$');
                    ClsRetInfo cri = new ClsRetInfo();
                    cri.infoType = ClsConstant.INFO_TP_WARN;
                    cri.detailInfo = "[" + warnMsg[1] + "]函数的参数不能为空！";
                    cri.infoContent = "函数的参数不能为空！";
                    YssMessageBox.ShowCommonInfoText(cri);
                    return "false";
                }
                
            }

            return msg;
        }

        /// <summary>
        /// 检查算法中函数的有效性，若函数包含空参数，则不通过
        /// </summary>
        private void check_fun()
        {
        }

        /// <summary>
        /// 获取多项选择项的方法，此方法只适用于Checkbox的多选形式
        /// 删除
        /// MVC架构专用
        /// </summary>
        /// <returns>选择行的数据对象</returns>
        public override ArrayList getSelectTypeItemListAuditable()
        {
            ArrayList list = new ArrayList();
            RowCollection rows = this.tbLeftMain.CheckedRows;
            if (null == rows)
            {
                return null;
            }

            for (int i = 0; i < rows.Count; i++)
            {
                AdvAlgo algo = (AdvAlgo)rows[i].Tag;

        //// this.deleteRow.Add(rows[i]);
                if (!this.deleteRow.Contains(algo.C_ALGO_CODE))
                {
                    this.deleteRow.Add(algo.C_ALGO_CODE);
                }
                
                list.Add(algo);
            }

            return list;
        }

        /// <summary>
        /// 删除
        /// xuqiji 2011-01-20 删除代码，防止单条操作出现对象被释放错误
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnDelete_Click(object sender, EventArgs e)
        {
            try
            {
                bool delResult = operAdaptMVC(sender, e, ClsEnums.StatusSetting.YssDel);
                if (delResult)
                {
                    clearDeletePage();
                    proBar.Value = proBar.Minimum;
                    proBar.Visible = false;
                    proBar.Owner.Refresh();

        ////由于父类时间是私有的，故重写后注释显示时间条
                    ////double time = Math.Round((base._operEndTime - base._operBeginTime).TotalSeconds, 4);

        ////SetQuerySpendTime(time.ToString());
                }
                
                this.algo_status = ClsEnums.StatusSetting.YssBrow.ToString();
                
            }
            catch (System.Exception ex)
            {
                YssMessageBox.ShowCommonInfos(TransferErrorMessageUtil.getTransferException(ex));
             }

        ////base.btnDelete_Click(sender, e);

        ////clearDeletePage();

        //// this.algo_status = ClsEnums.StatusSetting.YssBrow.ToString();
           
        }

        /// <summary>
        /// 通过算法代码找到A区对应的行
        /// </summary>
        /// <param name="code">算法代码</param>
        /// <returns>A区对应的行</returns>
        private Row getRowByAlgoCode(string code) 
        {
            RowCollection rc = this.tbLeftMain.Rows;

            for (int i = 0; i < rc.Count; i++)
            {
                if (rc[i].SubRows.Count > 0)
                {
                    RowCollection rs = rc[i].SubRows;
                    for (int j = 0; j < rs.Count; j++)
                    {
                        AdvAlgo aa = (AdvAlgo)rs[j].Tag;
                        if ("Root".Equals(aa.C_ALGO_NAME))
                        {
                            continue;
                        }

                        if (code.Equals(aa.C_ALGO_CODE))
                        {
                            return rs[j];
                        }

                    }
                }

            }

            return null;
        }

        /// <summary>
        ///  刷新算法界面信息
        /// </summary>
        private void refreshAlgoList()
        {
            if (null == this._tabCtrlMain)
            {
                return;
            }

            Yss.Controls.TabPage page = this._tabCtrlMain.SelectedTab;
            if (null == page)
            {
                return;
            }

            if (null != page.Tag)
            {
                AdvAlgo algo = (AdvAlgo)page.Tag;
                if (null == algo)
                {
                    return;
                }

                YssTextBox con = null;
                Yss.Controls.RichTextBox rtb = null;
                if (null != controlDic && controlDic.ContainsKey("test_main_algo_top_" + algo.C_ALGO_CODE))
                {
                    con = (YssTextBox)controlDic["test_main_algo_top_" + algo.C_ALGO_CODE];
                    rtb = (Yss.Controls.RichTextBox)controlDic["test_main_algo_below_" + algo.C_ALGO_CODE];
                    string flag = this.service.isNewAlgo(algo.C_ALGO_CODE);
                    if (con.YssReadOnly != false)
                    {
                        if ("False".Equals(flag))
                        {
                            con.Text = algo.C_ALGO_FORMULA;
                            rtb.Text = algo.C_DESC;
                        }
            else
            {
                            con.Text = algo.C_ALGO_FORMULA_ZH;
                            rtb.Rtf = algo.C_DESC;
                        }
                    }

                    this.preview_btn.Text = "预览";
                    this.confirm_btn.Text = "确定";
                }
            }
        }

        /// <summary>
        /// 标签页切换事件
        /// </summary>
        /// <param name="e">TabPageChangeEventArgs</param>
        protected override void TabCtrlMain_SelectedIndexChanged(Yss.Controls.TabPageEventArgs e)
        {
            if (e.NewPage != null && e.NewPage.Controls.Count > 0)
            {
                AdvAlgo advAlgo = e.NewPage.Tag as AdvAlgo;

                 // 通过算法代码找到A区对应的行
                Row tagRow = getRowByAlgoCode(advAlgo.C_ALGO_CODE);

                this.tbLeftMain.SelectionChanged -= this.tbLeftMain_SelectionChanged;
                if (null != this.tbLeftMain.SelectedRow)
                {
                    this.tbLeftMain.SelectedRow.Selected = false;
                 }

                tagRow.Selected = true;
                this.tbLeftMain.SelectionChanged += this.tbLeftMain_SelectionChanged;
                registEventTabMain();

            }

            this.DetailSelectedPageChanged();

            int index = this._tabCtrlMain.SelectedIndex;
            
            Yss.Controls.TabPage page = this._tabCtrlMain.SelectedTab;
            if (null != page.Tag)
            {
                refreshAlgoList();

        ////Row row = (Row)page.Tag;

        ////AdvAlgo algo = (AdvAlgo)row.Tag;
                AdvAlgo algo = (AdvAlgo)page.Tag;
                if (null == algo)
                {
                    return;
                }

                YssTextBox con = null;
                Yss.Controls.RichTextBox rtb = null;
                if (null != controlDic && controlDic.ContainsKey("test_main_algo_top_" + algo.C_ALGO_CODE))
                {
                    con = (YssTextBox)controlDic["test_main_algo_top_" + algo.C_ALGO_CODE];
                    con.AutoTooltip = false;
                    rtb = (Yss.Controls.RichTextBox)controlDic["test_main_algo_below_" + algo.C_ALGO_CODE];
                }


                if (algo.C_ALGO_CODE.Equals(this.modify_algo_only_one) && null != con && null != rtb)
                {
                    this.algo_status = ClsEnums.StatusSetting.YssEdit.ToString();
                    con.YssReadOnly = false;
                    rtb.ReadOnly = false;
                }
            else
            {
                    if (null != controlDic && controlDic.ContainsKey("test_main_algo_top_" + algo.C_ALGO_CODE) && null != con && null != rtb)
        {
            // //con = (YssTextBox)controlDic["test_main_algo_top_" + algo.C_ALGO_CODE];

        ////rtb = (Yss.Controls.RichTextBox)controlDic["test_main_algo_below_" + algo.C_ALGO_CODE];

                        if (this.algo_status == ClsEnums.StatusSetting.YssBrow.ToString())
                        {
                            con.YssReadOnly = true;

        ////rtb.Enabled = false;
                            rtb.ReadOnly = true;
                        }

                        if (this.algo_status == ClsEnums.StatusSetting.YssEdit.ToString() || this.algo_status == ClsEnums.StatusSetting.YssAdd.ToString())
                        {
                            con.YssReadOnly = true;

                            ////con.Enabled = false;

        ////rtb.Enabled = false;
                            rtb.ReadOnly = true;
                            this.algo_status = ClsEnums.StatusSetting.YssBrow.ToString();

                        }


                        if (!con.Enabled)
                        {
                            this.rightSplitter.Expanded = false;
                            this.rightSplitter.Enabled = false;
                        }
            else
            {
                            string iSNewA = isNewAlgo();
                            if (Equals(iSNewA, "False"))
                            {
                                rightSplitter.Enabled = false;
                                this.rightSplitter.Expanded = false;
                            }
            else
            {
                                this.rightSplitter.Expanded = true;
                                this.rightSplitter.Enabled = true;
                            }
                            
                        }
                    }
                }

                //// 修改审核界面按钮状态
                modifyBtnStatus();

        ////////遍历左侧树，比较code，若code相同则选中
                ////loopAPITree(algo.C_ALGO_NAME);

                //// 页面切换刷新右侧函数树区域（函数树，函数说明，参数表）内容
                ////AdvAlgo aa = (AdvAlgo)this.service.getAlgoByCode(algo.C_ALGO_CODE);
                if (null != this.algo_type && this.algo_type.Length > 0)
        {
            // // 如果当前要展示的函数树和上次展示的一样，则不再进行刷新，否则，刷新
                    if (this.algo_type.Equals(algo.C_DV_ALGO_TYPE))
                    {
                        return;
                    }
            else
            {
                        loadRightTree(algo.C_DV_ALGO_TYPE);
                        this.algo_type = algo.C_DV_ALGO_TYPE;

        //// 清空函数说明和参数表
                        this.yssTextBox1.Text = "";
                        this.rightTableParams.ClearRows();
                        this.rightTreeTable.Refresh();
                    }
                }
            else
            {
                    loadRightTree(algo.C_DV_ALGO_TYPE);
                    this.algo_type = algo.C_DV_ALGO_TYPE;
                    this.rightTreeTable.Refresh();
                }

            }
           
        }

        /// <summary>
        /// 关闭标签页前事件
        /// 判断该标签是否为编辑状态，若为编辑状态则提示是否保存
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        public void _tabCtrlMain_BeforeTabPageClosed(object sender, TabPageChangeEventArgs e)
        {
            Row row = this.tbLeftMain.SelectedRow;
            if (null == row)
            {
                return;
            }

            AdvAlgo algo = (AdvAlgo)row.Tag;
            if (!"".Equals(this.modify_algo_only_one) && this.modify_algo_only_one.Equals(algo.C_ALGO_CODE))
        {
            // // 当算法区域为空的时候  不允许保存
                if (null != controlDic && controlDic.ContainsKey("test_main_algo_top_" + algo.C_ALGO_CODE) && ((YssTextBox)controlDic["test_main_algo_top_" + algo.C_ALGO_CODE]).Text.ToString().Length <= 0)
                {
                    return;
                }

                ClsRetInfo cri = new ClsRetInfo();

        ////cri.infoGroup = "Fixed";
                cri.infoTitle = "提示信息";
                cri.infoContent = "是否要保存算法信息？";
                cri.icon = MessageBoxIcon.Warning;
                cri.buttonGroup = MessageBoxButtons.YesNo;
                cri.funCode = "AdvancedAlgorithm";
                DialogResult dr = YssMessageBox.ShowCommonInfoText(cri);
                if (dr == DialogResult.Yes)
        {
            // // 当算法区域为空的时候  不允许保存
                    ////if (null != controlDic && controlDic.ContainsKey("test_main_algo_top_" + algo.C_ALGO_CODE) && ((YssTextBox)controlDic["test_main_algo_top_" + algo.C_ALGO_CODE]).Text.ToString().Length <= 0)
                    ////{

            // //    return;

        ////}

                    savealgo(e);
                }
            else
            {
                    return;
                }

                ////if (YssMessageBox.ShowCommonInfoText(cri) == DialogResult.Cancel)
                ////{

            // //    return;

        ////}
            }
        }

        /// <summary>
        /// 若标签页全部关闭后，重置审核按钮状态
        /// </summary>
        private void nonePageSetBtnStatus()
        {
            if (this._tabCtrlMain.TabPages.Count <= 0)
            {
                modifyBtnStatus();
            }
        }

        /// <summary>
        /// 标签页关闭事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        public void _tabCtrlMain_TabPageClosed(object sender, TabPageEventArgs e)
        {
            // //Row row = this.tbLeftMain.SelectedRow;

        ////if (null == row)
            ////{

            // //    return;

        ////}

            ////AdvAlgo algo = (AdvAlgo)row.Tag;

        ////if (algo.C_ALGO_CODE.Equals(this.modify_algo_only_one))
            ////{

            // //    this.modify_algo_only_one = "";

        ////    this.algo_status = ClsEnums.StatusSetting.YssBrow.ToString();

        ////}
            this.modify_algo_only_one = "";
            this.algo_status = ClsEnums.StatusSetting.YssBrow.ToString();
            modifyBtnStatus();
        }

        /// <summary>
        /// 遍历函数树，根据函数名找出对应的函数行并手动触发单选事件
        /// </summary>
        /// <param name="funName">函数名</param>
        private void loopAPITree(string funName)
        {
            RowCollection rows = this.tbLeftMain.Rows;
            foreach (Row row in rows)
            {
                if (row.SubRows != null)
                {
                    RowCollection rc = row.SubRows;
                    foreach (Row r in rc)
                    {
                        Cell cell = r.Cells[0];
                        if (cell != null && cell.Text != null && cell.Text != "" && Equals(cell.Text, funName.Trim()))
                        {
                            r.Selected = true;
                        }
                    }
                }
            }
        }

        /// <summary>
        /// 查询
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        public override void btnSearch_Click(object sender, EventArgs e)
        {
            // // 查询  清除之前记录已修改的控件信息
            this.modify_algo_only_one = "";
            this.ShowInNewPage();
            this.tbLeftMain_Click(sender, new EventArgs());

        ////优化查询当前标签页与A区算法不对应bug
            if (null != this._tabCtrlMain)
            {
                int current_page_index = this._tabCtrlMain.TabPages.Count;
                if (current_page_index > 0)
                {
                    this._tabCtrlMain.SelectedTab = this._tabCtrlMain.TabPages[current_page_index - 1];
                }
                
            }
            
        }

        /// <summary>
        /// 计算被选中的算法有几个根节点
        /// </summary>
        /// <returns>count</returns>
        private int countRootItem()
        {
            int count = 0;
            RowCollection rc = this.tbLeftMain.CheckedRows;
            if (null == rc)
            {
                return count;
            }

            for (int i = 0; i < rc.Count; i++)
            {
                Row row = rc[i];
                if (row.HasChild)
                {
                    ++count;
                }
            }

            return count;
        }

         /// <summary>
        /// 在新页面中展示数据
        /// </summary>
        protected override void ShowInNewPage()
        {
            try
            {
                ClearTabPages();
                if (this.tbLeftMain.CheckedRows.Count < 1)
                {
                    return;
                }

                if (!this.ShowDetailNewPage)
                {
                    return;
                }

                int taskCount = this.tbLeftMain.CheckedRows.Count - countRootItem();
                if (taskCount > 200)
                {
                    ClsRetInfo info = new ClsRetInfo();
                    info.infoGroup = ClsConstant.INFO_GRP_ATT;
                    info.infoTitle = "提示信息";
                    info.infoContent = "查询算法总数不能大于200个";
                    info.icon = MessageBoxIcon.Warning;
                    YssMessageBox.ShowCommonInfoText(info);
                    return;
                }

                this.CreateTabControlMain();

        ////注册标签页关闭事件
                ////registEventTabMain();
                ThreadStart executeHandler = delegate { this.ExecuteQuery(); };
                this.ShowStopQueryDialog(taskCount, executeHandler);
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }
            finally
            {
                if (this._progressTool != null)
                {
                    this.UpdateTaskProgress(this._progressTool.TaskCount);
                }

                
            }
        }

        /// <summary>
        /// 执行查询
        /// </summary>
        private void ExecuteQuery()
        {
            try
            {
                foreach (Row row in this.tbLeftMain.CheckedRows)
                {
                    if (this._stopQuery)
        {
            // //停止查询任务
                        break;
                    }

                    if (row.HasChild)
                    {
                        this.UpdateTaskProgress(this._progressTool.Value);
                        continue;
                    }

                    YssVisAval.Pojo.AA.AdvAlgo pojo = row.Tag as YssVisAval.Pojo.AA.AdvAlgo;
                    if (this.CheckNewPage(row, false))
                    {
                        addInvoke(row, pojo);
                    }

                    this.UpdateTaskProgress(this._progressTool.Value + 1);

                }
            }
            catch (Exception ex)
            {
                ClsLogger.Error(ex);
            }
        }

        /// <summary>
        /// 测试
        /// </summary>
        /// <param name="row">row</param>
        /// <param name="pojo">pojo</param>
        private void addInvoke(Row row, AdvAlgo pojo)
        {
            if (InvokeRequired)
        {
            // //使用委托来处理，以防引发多线程安全问题
                Invoke(new Action(() => addInvoke(row, pojo)));
                return;
            }

            Yss.Controls.TabPage tabItem = this.CreateTabItem(row.Cells[0].Text, pojo.C_ALGO_CODE);

            //// 根据算法代码查出完整的算法信息
            ////AdvAlgo advAlgo = (AdvAlgo)this.service.getAlgoByCode(pojo.C_ALGO_CODE);

            ////tabItem.Tag = row;

        ////tabItem.Tag = advAlgo;
        }

        /// <summary>
        /// 清空左侧没有勾选的组合的TabPage
        /// </summary>
        private void ClearTabPages()
        {
            if (_tabCtrlMain == null || _tabCtrlMain.TabPages.Count == 0)
            {
                return;
            }

            var tabPages = new List<Yss.Controls.TabPage>();
            foreach (Yss.Controls.TabPage page in _tabCtrlMain.TabPages)
            {
                if (!tabPages.Contains(page))
                {
                    tabPages.Add(page);
                }
            }

            foreach (Yss.Controls.TabPage page in tabPages)
            {
                var row = page.Tag as Row;
                if (row == null || !TableLeftMain.CheckedRows.Contains(row))
                {
                    _tabCtrlMain.TabPages.Remove(page);
                }
            }
        }

        /// <summary>
        /// 更新任务刻度值
        /// </summary>
        /// <param name="value">递增的刻度值</param>
        private void UpdateTaskProgress(int value)
        {
            if (this._progressTool != null && this._progressTool.IsDisposed == false)
        {
            // // 处理余额表查询报错问题
                if (this._progressTool.Value != value)
                {
                    this._progressTool.UpdateProgressValue(value);
                }
            }
        }

        /// <summary>
        /// 终止查询按钮（主要用于处理查询报表数据过多时，终止查询任务）
        /// </summary>
        /// <param name="taskCount">任务总个数</param>
        /// <param name="executeHandler">待执行的任务方法句柄</param>
        private void ShowStopQueryDialog(int taskCount, ThreadStart executeHandler)
        {
            if (this._progressTool == null || this._progressTool.IsDisposed)
            {
                this._progressTool = new Yss.Tools.ProgressTool();
                this._progressTool.Text = "正在努力查询中，请稍候……";
                this._progressTool.TaskStoped += new EventHandler(this.progressTool_TaskStoped);
            }

            this._stopQuery = false;
            this._progressTool.TaskCount = taskCount;
            this._progressTool.SetExecuteHandler(executeHandler);
            this._progressTool.Start(this);
        }

        /// <summary>
        /// 查询任务终止事件
        /// </summary>
        /// <param name="sender">ProgressTool</param>
        /// <param name="e">事件参数</param>
        private void progressTool_TaskStoped(object sender, EventArgs e)
        {
            this._stopQuery = true;
        }

        /// <summary>
        /// 查询函数
        /// </summary>
        /// <param name="sender">按钮</param>
        /// <param name="e">事件</param>
        private void selfTxtSearch_TailClick(object sender, EventArgs e)
        {
            // // this.matchSearchStr = new string[] { "C_ALGO_CODE", "C_ALGO_NAME" }; // 【搜索】功能匹配的属性
            Yss.CommonLib.FilterTable(this.rightTreeTable.Rows, this.selfTxtSearch.Text, new string[] { "AlgoCode", "AlgoText" });
        }

        /// <summary>
        /// 功能：获取B区列表数据
        /// </summary>
        /// <param name="pojo">数据对象</param>
        /// <param name="iSQueryData">是否传输数据</param>
        /// <returns>1</returns>
        public override QueryRes getMainListDataMVC(BasePojo pojo, bool iSQueryData)
        {
            load_A();

        ////clearDeletePage();
            return null;
        }

        /// <summary>
        /// A区删除行后，同时删除对应的标签页
        /// </summary>
        private void clearDeletePage()
        {
            if (null == deleteRow)
            {
                return;
            }

            if (null == _tabCtrlMain)
            {
                return;
            }

            TabPageCollection conllect = _tabCtrlMain.TabPages;
            if (null == _tabCtrlMain.TabPages || _tabCtrlMain.TabPages.Count <= 0)
            {
                return;
            }

            List<Yss.Controls.TabPage> pages = new List<Yss.Controls.TabPage>();
            for (int i = 0; i < _tabCtrlMain.TabPages.Count; i++)
            {
                Yss.Controls.TabPage page = _tabCtrlMain.TabPages[i];
                AdvAlgo advAlgo = page.Tag as AdvAlgo;
                for (int j = 0; j < deleteRow.Count; j++)
                {
                    if (this.deleteRow[j].Equals(advAlgo.C_ALGO_CODE))
                    {
                        pages.Add(page);

        ////_tabCtrlMain.TabPages.Remove(page);
                    }
                }
                
            }

            foreach (Yss.Controls.TabPage p in pages)
            {
                _tabCtrlMain.TabPages.Remove(p);
            }

            pages.Clear();
            this.deleteRow.Clear();
            this.algo_status = ClsEnums.StatusSetting.YssBrow.ToString();
        }

        /// <summary>
        /// 刷新A区域数据，加载数据
        /// </summary>
        /// <param name="res">显示数据</param>
        public override void refreshTableListMVC(QueryRes res)
        {
        }

        /// <summary>
        /// 确定事件
        /// </summary>
        /// <param name="sender">按钮</param>
        /// <param name="e">事件</param>
        private void confirm_btn_Click(object sender, EventArgs e)
        {
            insertData2FunAir();

        //// 每次插入后，都进行一次转译工作；转译的同时会把相关下拉框数据初始化到缓存中，当单击选中该函数的时候，值会赋值到相应的控件内
            previewFun();
        }

        /// <summary>
        /// 预览事件
        /// </summary>
        /// <param name="sender">按钮</param>
        /// <param name="e">事件</param>
        private void preview_btn_Click(object sender, EventArgs e)
        {
            if (this.algo_status == ClsEnums.StatusSetting.YssBrow.ToString())
            {
                return;
            }

            ////Row row_left = this.tbLeftMain.SelectedRow;

        ////if (null == row_left)
            ////{

            // //    return;

        ////}
            
            ////AdvAlgo algo = (AdvAlgo)row_left.Tag;
            if (null == this._tabCtrlMain || null == this._tabCtrlMain.SelectedTab)
            {
                return;
            }

            AdvAlgo algo = (AdvAlgo)this._tabCtrlMain.SelectedTab.Tag;
            YssTextBox box = (YssTextBox)controlDic["test_main_algo_top_" + algo.C_ALGO_CODE];

            string fun_EN = getPreviewFullMsg();
            if (Equals(fun_EN, "false"))
            {
                return;
            }

            Button button = this.preview_btn;
            string butText = button.Text;
            if (butText.Equals("预览"))
            {
                this.algo_CH = box.Text;
                this.allowInsert = false;
                button.Text = "撤销";
                box.Text = fun_EN;

                box.YssReadOnly = true;

        //// 设置保存按钮  不可编辑
                btn_save_unedit();
            }
            else
            {
                this.preview_btn.Text = "预览";
                box.Text = this.algo_CH;
                box.SelectionStart = this.algo_CH.Length;
                this.allowInsert = true;
                box.YssReadOnly = false;

        //// 设置保存按钮可编辑
                btn_save_edit();
            }
        }

        /// <summary>
        /// 设置保存按钮不可用
        /// </summary>
        private void btn_save_unedit()
        {
            // // 获取所有按钮
            Dictionary<string, List<string>> dic = btnBar.getAllButtonNames();

            foreach (string name in dic.Keys)
            {
                ClsButtonInfo bInfo = btnBar.getButton(name);
                bInfo.Enabled = true;
                ClsButtonInfo btnInfo = btnBar.getButton(name);
                btnInfo.Enabled = false;

            }
        }

        /// <summary>
        /// 设置保存按钮可用
        /// </summary>
        private void btn_save_edit()
        {
            // // 获取所有按钮
            Dictionary<string, List<string>> dic = btnBar.getAllButtonNames();

            foreach (string name in dic.Keys)
            {
                ClsButtonInfo bInfo = btnBar.getButton(name);
                bInfo.Enabled = true;
                if (name != "btnSave1")
                {
                    ClsButtonInfo btnInfo = btnBar.getButton(name);
                    btnInfo.Enabled = false;
                }

            }
        }

        /// <summary>
        /// 为实现批量审核模块功能（有审核功能并且有自定义参数的 需要重写该方法）
        /// </summary>
        /// <returns>参数集合</returns>
        public override Dictionary<string, string> setExtraCondition()
        {
            Dictionary<string, string> para_Dict = new Dictionary<string, string>();
            this.GetParaAssemble(para_Dict);
            return para_Dict;
        }

        /// <summary>
        /// 重写反审核事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnUnAudit_Click(object sender, EventArgs e)
        {
            RowCollection rc = this.tbLeftMain.CheckedRows;
            if (rc.Count <= 0)
            {
                return;
             }

        //// this.tbLeftMain.SelectedRows;
            Row r = rc[0];
            for (int i = 0; i < rc.Count; i++)
            {
                unauditList.Add((AdvAlgo)rc[i].Tag);
            }

            base.btnUnAudit_Click(sender, e);

            if (unauditList.Count > 0)
        {
            // // 根据行对象 反选A区信息  多个反审核，默认选中第一条
                if (unauditList.Count > 1)
                {
                    unauditList.Clear();
                    return;
                }

                select_A_Row(unauditList[0]);
                unauditList.Clear();
            }
        }

        /// <summary>
        /// 重写审核
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnAudit_Click(object sender, EventArgs e)
        {
            RowCollection rc = this.tbLeftMain.CheckedRows;
            if (rc.Count <= 0)
            {
                return;
            }

            for (int i = 0; i < rc.Count; i++)
            {
                auditList.Add((AdvAlgo)rc[i].Tag);
            }

            base.btnAudit_Click(sender, e);

            if (auditList.Count > 0)
        {
            // // 根据行对象 反选A区信息  多个审核，默认选中第一条
                if (auditList.Count > 1)
                {
                    auditList.Clear();
                    return;
                }

                select_A_Row(auditList[0]);
                auditList.Clear();
            }
        }

        /// <summary>
        /// 根据审核和反审核前的选中行,审核和反审核后进行反选对应的行
        /// </summary>
        /// <param name="a">审核和反审核前选中的行</param>
        private void select_A_Row(AdvAlgo a)
        {
            RowCollection rc = this.tbLeftMain.Rows;
            if (null == rc || rc.Count <= 0)
            {
                return;
            }

            for (int i = 0; i < rc.Count; i++)
            {
                if (rc[i].SubRows.Count > 0)
                {
                    RowCollection rs = rc[i].SubRows;
                    for (int j = 0; j < rs.Count; j++)
                    {
                        AdvAlgo aa = (AdvAlgo)rs[j].Tag;
                        if ("Root".Equals(aa.C_ALGO_NAME))
                        {
                            continue;
                        }

                        if (a.C_ALGO_CODE.Equals(aa.C_ALGO_CODE))
                        {
                            rs[j].Checked = true;
                            rs[j].Selected = true;
                            tbLeftMain_Click(rs[j], new EventArgs());
                            this.algo_status = ClsEnums.StatusSetting.YssBrow.ToString();
                        }

                    }
                }

            }

        }

        /// <summary>
        /// KTable A单选模式下选择
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void tbLeftMain_SelectionChanged(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            base.tbLeftMain_SelectionChanged(sender, e);
            if (this.algo_status == ClsEnums.StatusSetting.YssEdit.ToString() || this.algo_status == ClsEnums.StatusSetting.YssAdd.ToString())
            {
                this.algo_status = ClsEnums.StatusSetting.YssBrow.ToString();
            }
            
            AdvAlgo algo = (AdvAlgo)this.tbLeftMain.SelectedRow.Tag;
            if (null != controlDic && controlDic.ContainsKey("test_main_algo_top_" + algo.C_ALGO_CODE))
            {
                YssTextBox con = (YssTextBox)controlDic["test_main_algo_top_" + algo.C_ALGO_CODE];
                Yss.Controls.RichTextBox rtb = (Yss.Controls.RichTextBox)controlDic["test_main_algo_below_" + algo.C_ALGO_CODE];

                if (this.algo_status == ClsEnums.StatusSetting.YssBrow.ToString())
                {
                    con.YssReadOnly = true;
                    rtb.ReadOnly = true;
                }
            }
        }

        /// <summary>
        /// 行双击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">sender</param>
        protected override void tbLeftMain_RowDoubleClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
        }

    }
}
