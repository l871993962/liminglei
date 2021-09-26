using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;

using FAST.Core.Communication.DataService;
using FAST.Common.Service.DataService.Base;
using FAST.Core.Exceptions;

using FAST.Core.Context;

using FAST.Core.Resource;


using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;

using FAST.Common.Service.Pojo.Base;
using System;
using System.Collections.Generic;







using YssProductInfo.Support.Aa.PortCls.Pojo;
using YssProductInfo.Support.Aa.PortCls.Service;

using System.Text.RegularExpressions;
using FAST.Common.Service.Dict.Pojo;
using YssProductInfo.Support.DataCopy.Pojo;
using FAST.Common.Service.Services;

using System.Drawing;
using YssProductInfo.Support.DataCopy.Service;
using Yss.KTable.Models;
using Yss.Controls;
using Yss.KMessage;
using System.Windows.Forms;
using FAST.Core.CRUD.Interface;
using YssProductInfo.Ab.Port.Form;



namespace YssProductInfo.Aa.PortCls.Form
{
    /// <summary>
    /// 功能简介：分级产品参数设置，负责分级产品参数数据的增删改查等功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2011.01.07
    ///
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：庄宇臣
    /// 修改日期：2011-1-14
    /// 修改简介：实现功能
    /// 
    /// 
    /// /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：zhuangyuchen
    /// 修改日期： 2011-2-22
    /// 修改简介：
    /// 1:增加回收站开启关闭机制
    /// 2:删除添加修改等操作的操作成功信息
    /// 3：添加注释
    /// 4：删除初始化加载下拉框的代码，改成在控件的属性中设置
    /// 6：修改出错的提示信息
    /// 7:去掉增删改查成功的提示信息，由基类来提供
    /// 8：增加传到后台去的列头和窗体菜单
    /// 
    ///  /// /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：zhuangyuchen
    /// 修改日期： 2011-2-22
    /// 修改简介：
    /// 1:修改由于基类的更该导致代码的更改
    /// 2：去掉增删改查的赋值方法，有基类来提供
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         返回对象错误直接抛出异常
    ///         验证信息正确执行操作，否则屏蔽相应操作
    /// 
    ///</summary>
    public partial class Frm_PORT_CLS_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private IPortClsService portClsService = null;

        /// <summary>
        /// 是否复制创建状态
        /// </summary>
        private bool isCreateCopy = false;

        /// <summary>
        /// 构造函数.
        /// </summary>
        public Frm_PORT_CLS_S()
        {
            InitializeComponent();
            this.bUseMVCService = true;

            if (this.isCreateCopy != true)
            {
                this.Size = new Size(499, this.Size.Height);
                this.pnlRight.Hide();
                this.splitRight.Hide();
            }

            //this.cboPort.AfterLoadPortData += new EventHandler(cboport_afterLoadPortData);
            //// BUG #115507 A区投资组合选中背景色记录，可带到Set界面，并没有在所有的界面进行实现 liyongjun
            //// setPortSelCombox(this.cboPort);

        }

        /// <summary>
        /// ss
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboport_afterLoadPortData(object sender, EventArgs e) 
        {
            ////BUG #202350 针对GroupTextBox 组件，去除权限控制之后的数据加载。没有考虑GroupTextBox已经有值的情况，导致数据加载重复
            if (status == ClsEnums.StatusSetting.YssAdd)
            {
                this.cboPortP.Value = null;
                this.dtpExpiryDate.setDateTime(Convert.ToDateTime("9998-12-31"));
                this.dtpLiquid.setDateTime(Convert.ToDateTime("9998-12-31"));
                if (this.frmBaseViewList.TableLeftMain.Visible == true && this.frmBaseViewList.TableLeftMain.SelectedRow != null)
                {
                    FAST.Common.Service.Pojo.Port port = this.frmBaseViewList.TableLeftMain.SelectedRow.Tag as FAST.Common.Service.Pojo.Port;

                    if (port != null)
                    {
                        this.cboPort.Text = port.C_PORT_CODE;
                    }
                }
            }

            setPortClsState();
        }

        /// <summary>
        /// ss
        /// </summary>
        /// <param name="e">e</param>
        protected override void OnLoad(EventArgs e)
        {
            base.OnLoad(e);

            if (status == ClsEnums.StatusSetting.YssAdd)
            {
                this.cboPortP.Value = null;
                this.dtpExpiryDate.setDateTime(Convert.ToDateTime("9998-12-31"));
                this.dtpLiquid.setDateTime(Convert.ToDateTime("9998-12-31"));
                if (this.frmBaseViewList.TableLeftMain.Visible == true && this.frmBaseViewList.TableLeftMain.SelectedRow != null)
                {
                    FAST.Common.Service.Pojo.Port port = this.frmBaseViewList.TableLeftMain.SelectedRow.Tag as FAST.Common.Service.Pojo.Port;

                    if (port != null)
                    {
                        this.cboPort.Text = port.C_PORT_CODE;
                    }

                }

            }

            setPortClsState();
        }

        /// <summary>
        /// 设置参数
        /// </summary>
        /// <param name="isDedail">显示复制创建控件</param>
        /// <param name="portcls">参照组合</param>
        public void init(bool isDedail, YssProductInfo.Support.Aa.PortCls.Pojo.PortCls portcls)
        {
            this.Size = new Size(749, this.Size.Height);
            this.pnlRight.Show();
            this.splitRight.Show();
            //// 对窗体控件一一赋值
            this.cboPort.Text = portcls.C_PORT_CODE;
            //// 上级分级组合 
            if ("[root]".Equals(portcls.C_PORT_CLS_CODE_P))
            {
                this.cboPortP.Value = "";
            }
            else
            {
                this.cboPortP.Value = portcls.C_PORT_CLS_CODE_P;
            }

            this.cboClassType.Value = portcls.C_DV_PORT_CLS_TYPE;
            this.cboClassGrade.Value = portcls.C_DV_PORT_CLS_LEVEL;
            this.selDtCode.Value = portcls.C_DC_CODE;
            this.isNetting.Value = portcls.C_DV_NETTING;
            this.selDvPortCls.Value = portcls.C_DV_PORT_CLS;
            this.selSyfp.Value = portcls.C_DV_INC_DIS;
            this.cboNetFormula.Value = portcls.C_ALGO_CODE;

            this.SelRateType.Value = portcls.C_INCOME_TYPE;
            this.txtYield.Text = (portcls.N_YEAR_INCOME * 100).ToString();
            this.cboRateFormula.Value = portcls.C_ALGO_CODE_I;

            this.dtpFoundDate.setDateTime(Convert.ToDateTime(portcls.D_TO_LIST.ToString()));
            this.dtpExpiryDate.setDateTime(Convert.ToDateTime(portcls.D_OFF_LIST.ToString()));
            this.dtpLiquid.setDateTime(Convert.ToDateTime(portcls.D_LIQUID_DATE.ToString()));

            this.cboPortCode.Value = portcls.C_PORT_CLS_CODE;

            createTbRightMain();
        }

        /// <summary>
        /// 创建复制创建tbRightMain
        /// </summary>
        private void createTbRightMain()
        {
            IDataCopyService iDataCopyService = ServiceFactory.createService<IDataCopyService>();
            QueryRes res = iDataCopyService.queryPortClsCreateCopy();
            if (res != null)
            {
                ListHeadInfo listHeadInfo = new ListHeadInfo();
                listHeadInfo.Key = "C_DATA_NAME";
                res.ListHeadList = new List<ListHeadInfo>();
                res.ListHeadList.Add(listHeadInfo);
            }

            new TableListLoader().loadTable(tbRightMain, res, false, false, ClsEnums.KTableDataShowMode.TreeMode);
            chkCheckAll.Checked = true;
            this.tbRightMain.Refresh();
        }

        /// <summary>
        /// 保存复制创建相关参数所需的条件
        /// </summary>
        /// <returns>返回的参数</returns>
        private Dictionary<string, string> buildOperConds()
        {
            Dictionary<string, string> dict = new Dictionary<string, string>();
            dict.Add("C_OPER_CODE", "COPY_C_PORT_CLS_CODE=" + this.cboPortCode.Value);
            dict.Add("PORT_CODE_LIST", this.txtClsPortCode.Value);
            dict.Add("C_PORT_CODE", this.cboPort.Text);
            dict.Add("DATA_SVC_LIST", getDataCodeList());
            dict.Add("C_FUN_CODE", _formFun.C_FUN_CODE); //// 添加功能菜单byleeyu20131230
            return dict;
        }

        /// <summary>
        /// 获取选中的复选项
        /// </summary>
        /// <returns>s</returns>
        private string getDataCodeList()
        {
            string dataCodeList = "";
            foreach (Row row in tbRightMain.CheckedRows)
            {
                //// modify by yewenke 2016-03-11 复制产品出错
                //// modified by yhm 20160815 C_DV_STATE值为0时表示非根节点，仅最明细节点进行复制
                if (row.Tag != null && !string.Equals((row.Tag as CopyData).C_DATA_CODE_P.ToLower(), "[root]") && !string.Equals((row.Tag as CopyData).C_DV_STATE, "0"))
                {
                    CopyData copyData = row.Tag as CopyData;
                    dataCodeList += copyData.C_DATA_CODE + "=" + copyData.C_SERVICE_CODE + "&";
                }
            }

            return dataCodeList;
        }

        /// <summary>
        /// 分割条收缩状态改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void splitRight_ExpandedChanged(object sender, ExpandedEventArgs e)
        {
            if (e.NewExpandedValue == true)
            {
                this.Size = new Size(749, this.Size.Height);
            }
            else
            {
                this.Size = new Size(500, this.Size.Height);
            }
        }

        /// <summary>
        /// 勾选Table
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void chkCheckAll_CheckedChanged(object sender, YssDevComponents.DotNetBar.CheckBoxChangeEventArgs e)
        {
            foreach (Row row in tbRightMain.Rows)
            {
                row.Checked = chkCheckAll.Checked;
            }

            this.chkBoxCheckedRowsCount.Text = tbRightMain.CheckedRows.Count.ToString();
        }

        /// <summary>
        /// 仅显示勾选行复选框——勾选事件
        /// </summary>
        /// <param name="sender">chkBoxCheckedRowsCount</param>
        /// <param name="e">事件参数</param>
        private void chkBoxCheckedRowsCount_CheckedChanged(object sender, YssDevComponents.DotNetBar.CheckBoxChangeEventArgs e)
        {
            Table tempTable = this.tbRightMain;
            Yss.CommonLib.RedisplayTableCheckedRows(tempTable.Rows, chkBoxCheckedRowsCount.Checked);
        }

        /// <summary>
        /// 行选择状态改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void tbRightMain_CheckStateChanged(object sender, Yss.KTable.Events.CheckStateChangeEventArgs e)
        {
            this.chkBoxCheckedRowsCount.Text = tbRightMain.CheckedRows.Count.ToString();
        }

        /// <summary>
        /// 在保存前检查界面元素的输入是否合法
        /// </summary>
        /// <returns>是否通过检查</returns>
        protected override bool checkInput()
        {
            bool result = base.checkInput();
            if (isCreateCopy && result)
            {
                result = this.clsInterface.checkControlsInput(this.tbRightFilter);
            }

            ////(合并代码) add by Yuntao Lau 2015.12.19 BUG #123015
            if (checkDate())
            {
                MessageDialog msgDialog = new MessageDialog();
                if (msgDialog.Show("分级组合" + this.txtClsPortCode.Value + "日期段有重叠，是否保存?", "系统提示", MessageBoxButtons.OKCancel, MessageBoxIcon.Question) != DialogResult.OK)
                {
                    return false;
                }
            }

            if (checkDateQSRQ())
            {
                ////开始日期和结束日期确定的存续时间段有重复日期
                TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                transferErrorMessage.MESSAGESLINK = new List<string>();
                /* 组装提示信息对象ErrorMessage */
                string errorMess = ClsRetInfoDealer.getExtWarns("005", _formFun, ClsEnums.StatusSetting.YssSave);
                transferErrorMessage.MESSAGESLINK.Add(errorMess);
                throw new TransferException(JsonUtil.toJson(transferErrorMessage));
            }

            
            return result;
        }

        /// <summary>
        /// set界面保存数据方法
        /// </summary>
        /// <param name="status">当前窗体的打开状态</param>
        /// <returns>保存数据后后台返回的操作结果信息</returns>
        public override string yssFormOperation(ClsEnums.StatusSetting status)
        {
            string operResult = base.yssFormOperation(status);
            ClsRetInfo retInfo = ClsRetInfoDealer.getReturnInfo(operResult);

            ////STORY # 31359 复制参数出现重复数据
            if (retInfo.operRes == "Success" && status == ClsEnums.StatusSetting.YssAdd)
            {
               createCopyExecute();
            }

            return operResult;
        }

        /// <summary>
        /// 创建复制执行方法
        /// </summary>
        /// <returns>返回的参数</returns>
        private string createCopyExecute()
        {
            List<BasePojo> list = new List<BasePojo>();
            ClsTreeLeafList ctll = new ClsTreeLeafList();

            if (this.cboPortCode.Value == null || this.cboPortCode.Value.Trim().Length == 0)
            {
                return " ";
            }

            if (this.cboPort.Text == null || this.cboPort.Text.Trim().Length == 0)
            {
                return " ";
            }


            string c_Result = " ";
            IDataCopyService iDataCopyService = ServiceFactory.createService<IDataCopyService>();
            c_Result = iDataCopyService.exe(buildOperConds());


            return c_Result;
        }

        /// <summary>
        /// 控件初始化方法.
        /// </summary>
        public override void yssInitCtlAttr()
        {
            if (this.frmBaseViewList == null)
            {
                return;
            }

            //// 设置控件显示时间为当前时间
            Vocabulary voc = null;

            //// 选中快捷区数据时，把快捷区的选中类型复制给控件
            voc = this.frmBaseViewList.getSelectedRowTagMVC(voc) as Vocabulary;
            if (voc != null)
            {
                // edit by yh 2011.03.11 修改词汇对象的名称.
                this.cboClassType.Value = voc.C_DV_CODE;
            }

            //// 设置收益率类型默认为固定
            this.SelRateType.Value = "SYLLX_GD";

            ////edit by weijj 20140415 STORY #16372 分级产品参数给默认值
            if (status == ClsEnums.StatusSetting.YssAdd)
            {
                this.cboClassType.Value = "PCT_CREAT"; ////创新类型
                this.cboClassGrade.Value = "PCLC_FIRST"; ////优先级别
                ////this.selDtCode.Value = "CNY"; ////人民币
                this.isNetting.Value = "0"; ////否
                this.selDvPortCls.Value = "PORT_A"; ////A级
                this.selSyfp.Value = "SYFP_BF";  ////全分配
            }
        }

        /// <summary>
        /// 分级产品值改变事件.
        /// 修改了bug 下拉框取值错误.
        /// zhuangyuchen .
        /// 2011-3-20.
        /// </summary>
        /// <param name="sender">发送对象</param>
        /// <param name="e">事件对象</param>
        private void cboClassType_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.cboClassType.Value != null)
            {
                //// 获取选中的分级类型代码
                string clsType = this.cboClassType.Value;
                this.cboClassGrade.YssAssociaType = FAST.Core.Context.AssociaFAST.pubvocabulary;
                this.cboClassGrade.Value = null;
                //// 当分级类型为费率类型时，分级级别加载申购赎回份额，销售费率份额
                if (clsType.Equals("PCT_FEE"))
                {
                    this.cboClassGrade.QueryCond = "PORT_CLS_FEE_TYPE";
                }
                else if (clsType.Equals("PCT_FEE_BOC"))
                {
                    ////RQFII类型分级
                    this.cboClassGrade.QueryCond = "PORT_CLS_FEE_BOC";
                }
                else if (clsType.Equals("PCT_CREAT"))
                {
                    //// 当分级类型为创新类型是，分级界别加载普通级别和优先级别
                    this.cboClassGrade.QueryCond = "PORT_CLS_CREAT_TYPE";
                }
                else if (clsType.Equals("PCT_CUR"))
                {
                    ////币种类型
                    this.cboClassGrade.QueryCond = "PORT_CLS_CUR";
                }
            }
        }

        /// <summary>
        /// 收益率类型改变事件.
        /// 当【收益率类型】为“固定”时，【年化收益率】为可用状态，用户可进行输入年化收益率；
        /// 当【收益率类型】为“浮动”时，【年化收益率】加载比率公式，数据来源自【比率公式设置】，用户手工选择输入；
        /// meipeng
        /// 2014-1-17
        /// </summary>
        /// <param name="sender">发送对象</param>
        /// <param name="e">事件对象</param>
        private void selRateType_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.SelRateType.Value != null && this.SelRateType.Value != "")
            {
                this.tbMain.Rows[11].Cells[4].InnerControl = null;
                //// 获取选中的收益率类型代码
                string rateType = this.SelRateType.Value;
                //// 当收益率类型为固定时
                if (rateType.Equals("SYLLX_GD"))
                {
                    this.tbMain.Rows[11].Cells[3].Text = "年化收益率：";
                    this.tbMain.Rows[11].Cells[4].InnerControl = this.txtYield;
                    txtYield.Sufix = "%";
                    this.txtYield.YssCaption = "年化收益率";
                    this.txtYield.YssIsMust = false;

                    if (this.status == ClsEnums.StatusSetting.YssBrow)
                    {
                        this.txtYield.YssReadOnly = true;
                    }
                    else
                    {
                        this.txtYield.YssReadOnly = false;
                    }
                }
                else if (rateType.Equals("SYLLX_FD"))
                {
                    //// 当收益率类型为浮动时
                    this.tbMain.Rows[11].Cells[3].Text = "比率公式：";
                    this.tbMain.Rows[11].Cells[4].InnerControl = this.selRatioFormula;
                    this.selRatioFormula.YssCaption = "比率公式";
                    this.selRatioFormula.DisplayStyle = Yss.KRichEx.AutoFilter.DisplayStyle.Modal;
                    this.selRatioFormula.YssIsMust = false;
                    if (this.status == ClsEnums.StatusSetting.YssBrow)
                    {
                        this.selRatioFormula.YssReadOnly = true;
                    }
                    else
                    {
                        this.selRatioFormula.YssReadOnly = false;
                    }

                }
            }

            //// By Jinghehe 2017-8-3 控件初始化后，进行校验一下 所绑定应用是否已经启动
            //// 没有启动 控件就只读 不可编辑 
            ControlsLayoutLoader.ValidateServiceComponent(this.Controls);
            tbMain.Refresh();
        }

        //// edit by Yuntao Lau 2015.12.19 BUG #123015
        /////// <summary>
        /////// 保存之前先验证输入数据是否符合要求.
        /////// wuwenlan 20110314.
        /////// </summary>
        /////// <param name="sender">请求对象</param>
        /////// <param name="e">事件对象</param>
        ////private void Frm_PORT_CLS_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        ////{
        ////    ////由于下拉框未实现，用t固定值来代替
        ////    //  判断如果输入的终止日期小于起始日期，就抛出一个异常
        ////    // 屏蔽掉执行保存事件
        ////    e.IsCancel = true;

        ////    ////验证终止日期和起始日期                     liuping  2011-03-31  BUG #1623 分级产品参数BUG
        ////    ////checkDate();

        ////    ////检查日期交叉
        ////    checkDateAcorss();

        ////    // 不屏蔽掉执行保存按钮事件
        ////    e.IsCancel = false;
        ////}

        /// <summary>
        /// 窗体加载时间.
        /// </summary>
        /// <param name="sender">请求对象</param>
        /// <param name="e">事件对象</param>
        private void Frm_PORT_CLS_S_Load(object sender, EventArgs e)
        {
            Type type = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.portClsService = (IPortClsService)ServiceFactory.createService(type);
            dataService = this.portClsService;
        }

        /// <summary>
        /// 投资组合值改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboPort_SelectedValueChanged(object sender, EventArgs e)
        {
            setPortClsState();
            cboPortP.Value = null;
            this.cboPortP.IsFocused = false;
        }

        /// <summary>
        /// 变更分级组合的状态
        /// </summary>
        private void setPortClsState()
        {
            this.cboPortP.YssReadOnly = true;
            YssProductInfo.Support.Aa.PortCls.Service.IClsPortDataService iClsPortDataService = DataServiceFactory.createService<YssProductInfo.Support.Aa.PortCls.Service.IClsPortDataService>();
            if (cboPort.Text != null)
            {
                string portCode = this.cboPort.Text.ToString();  //// 得到投资组合的代码
                List<BasePojo> list = iClsPortDataService.getDataListByTypes(new string[] { cboPort.Text });
                if (list != null && list.Count > 0)
                {
                    this.cboPortP.YssReadOnly = status == ClsEnums.StatusSetting.YssBrow ? true : false;
                }
            }
        }

        //// edit by Yuntao Lau 2015.12.19 BUG #123015
        /////// <summary>
        /////// 检查日期交叉
        /////// </summary>
        ////private void checkDateAcorss()
        ////{
        ////    if (!isCheckDateAcross()) 
        ////    {
        ////        return;
        ////    }

        ////    string obj = null; ////时间交叉变量

        ////    ////检查日期是否和库存数据存在日期交叉
        ////    ClsQuyStrUtil quyStrUtil = null;
        ////    ////获取当前后台的查询条件
        ////    quyStrUtil = new ClsQuyStrUtil();
        ////    quyStrUtil.addQuyCon("C_PORT_CLS_CODE", this.txtClsPortCode.Value, "="); ////分级组合

        ////    if (this.cboPort.Value != null)
        ////    {
        ////        quyStrUtil.addQuyCon("C_PORT_CODE", this.cboPort.Value.Trim(), "="); ////组合
        ////    }

        ////    quyStrUtil.addQuyCon("D_TO_LIST", this.dtpFoundDate.getBeginDateStr, "="); ////开始日期
        ////    quyStrUtil.addQuyCon("D_OFF_LIST", this.dtpExpiryDate.getBeginDateStr, "="); ////结束日期
        ////    obj = (string)this.frmBaseViewList.DataAdmin.GetSpecValue(quyStrUtil.getQuyStr(), "dateAcross");
        ////    if ("true".Equals(obj))
        ////    {
        ////        ////开始日期和结束日期确定的存续时间段有重复日期
        ////        TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
        ////        transferErrorMessage.MESSAGESLINK = new List<string>();
        ////        /* 组装提示信息对象ErrorMessage */
        ////        string errorMess = ClsRetInfoDealer.getExtWarns("002", _formFun, ClsEnums.StatusSetting.YssSave);
        ////        transferErrorMessage.MESSAGESLINK.Add(errorMess);
        ////        throw new TransferException(JsonUtil.toJson(transferErrorMessage));
        ////        ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("002", _formFun, ClsEnums.StatusSetting.YssSave));
        ////    }
        ////}

        //// edit by Yuntao Lau 2015.12.19 BUG #123015
        /////// <summary>
        /////// 检测是否需要作日期交叉检验
        /////// </summary>
        /////// <returns>检验标志符</returns>
        ////private bool isCheckDateAcross() 
        ////{
        ////    bool checkFlag = false;
        ////    DateTime toList = new DateTime();
        ////    DateTime offList = new DateTime();
        ////    if (status == ClsEnums.StatusSetting.YssEdit)
        ////    {
        ////        PortCls pc = (PortCls)this.yssGetBaseSelTypeItemMVC();
        ////        toList = pc.D_TO_LIST;
        ////        if (toList == null) 
        ////        {
        ////            toList = new DateTime();
        ////        }

        ////        offList = pc.D_OFF_LIST;
        ////        if (offList == null) 
        ////        {
        ////            offList = new DateTime();
        ////        }

        ////        if ((pc.C_PORT_CLS_CODE.Equals(this.txtClsPortCode.Value) && pc.C_PORT_CODE.Equals(this.cboPort.Value)) && (this.dtpFoundDate.getBeginDateStr.CompareTo(Convert.ToString(toList)) >= 0 && this.dtpFoundDate.getBeginDateStr.CompareTo(Convert.ToString(offList)) <= 0) && (this.dtpExpiryDate.getBeginDateStr.CompareTo(Convert.ToString(toList)) >= 0 && this.dtpExpiryDate.getBeginDateStr.CompareTo(Convert.ToString(offList)) <= 0))
        ////        {
        ////            return true;
        ////        }

        ////    }

        ////    return checkFlag;
        ////}

        /// <summary>
        /// 封装窗体控件到对象
        /// </summary>
        /// <returns>封装的对象</returns>
        public override AuditableParamPojo faceInfoToObjMVC()
        {
            YssProductInfo.Support.Aa.PortCls.Pojo.PortCls gradeProduce = new YssProductInfo.Support.Aa.PortCls.Pojo.PortCls();
            try
            {
                if (this.dtpFoundDate.getBeginDate.CompareTo(this.dtpExpiryDate.getBeginDate) > 0)
                {
                    ////throw new ClsBaseException("上市日期应该小于起始日期");
                    TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                    transferErrorMessage.MESSAGESLINK = new List<string>();
                    /* 组装提示信息对象ErrorMessage */
                    string errorMess = ClsRetInfoDealer.getExtWarns("003", _formFun, ClsEnums.StatusSetting.YssSave);
                    transferErrorMessage.MESSAGESLINK.Add(errorMess);
                    throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                    ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("003", _formFun, ClsEnums.StatusSetting.YssSave));
                }
                else if (this.dtpExpiryDate.getBeginDate.CompareTo(this.dtpLiquid.getBeginDate) > 0)
                {
                    ////结束日期小于等于 清盘日期
                    TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                    transferErrorMessage.MESSAGESLINK = new List<string>();
                    string errorMess = ClsRetInfoDealer.getExtWarns("004", _formFun, ClsEnums.StatusSetting.YssSave);
                    transferErrorMessage.MESSAGESLINK.Add(errorMess);
                    throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                }

                //// 组合代码
                gradeProduce.C_PORT_CODE = (string)this.cboPort.Text;

                //// 分级组合代码
                gradeProduce.C_PORT_CLS_CODE = this.txtClsPortCode.Text;

                //// 分级的名称
                gradeProduce.C_PORT_CLS_NAME = (string)this.txtClsPortName.Text;

                //// 分级类型    
                gradeProduce.C_DV_PORT_CLS_TYPE = (string)this.cboClassType.Value;

                //// 分级的级别
                gradeProduce.C_DV_PORT_CLS_LEVEL = (string)this.cboClassGrade.Value;

                ////级别类型
                gradeProduce.C_DV_PORT_CLS = this.selDvPortCls.Value; ////添加级别类型字段 liuliang 20120508

                //// 成立的日期
                gradeProduce.D_TO_LIST = this.dtpFoundDate.getBeginDate;

                //// 终止日期
                gradeProduce.D_OFF_LIST = this.dtpExpiryDate.getBeginDate;

                //// 清算日期 add by zzk 20150521 BUG #112720 分级产品参数设置界面清算日期无法保存 
                gradeProduce.D_LIQUID_DATE = this.dtpLiquid.getBeginDate;

                //// 币种代码
                gradeProduce.C_DC_CODE = this.selDtCode.Value;

                //// 算法公式
                gradeProduce.C_ALGO_CODE = (null == (string)this.cboNetFormula.Value ? " " : (string)this.cboNetFormula.Value);

                //// 上级分级组合
                if (null == this.cboPortP.Value || this.cboPortP.Value.Trim().Length == 0)
                {
                    gradeProduce.C_PORT_CLS_CODE_P = "[root]";
                }
                else
                {
                    gradeProduce.C_PORT_CLS_CODE_P = (string)this.cboPortP.Value;
                }

                //// 轧差 
                gradeProduce.C_DV_NETTING = (string)this.isNetting.Value;

                //// 收益分配
                gradeProduce.C_DV_INC_DIS = (string)this.selSyfp.Value;

                //// 收益率类型 add by meip 20131225
                gradeProduce.C_INCOME_TYPE = (string)this.SelRateType.Value;
                //// 当收益率类型为固定时
                if (gradeProduce.C_INCOME_TYPE.Equals("SYLLX_GD"))
                {
                    //// 年化收益率 add by tangshifeng 20130613
                    gradeProduce.N_YEAR_INCOME = decimal.Parse(this.txtYield.Text) / 100;      // 年化收益率
                }
                else if (gradeProduce.C_INCOME_TYPE.Equals("SYLLX_FD"))
                {
                    //// 比率公式 add by meip 20140120
                    gradeProduce.C_FORMULA_CODE = (null == (string)this.selRatioFormula.Value ? " " : (string)this.selRatioFormula.Value);
                }
                //// 收益率公式 add by tangshifeng 20130613
                gradeProduce.C_ALGO_CODE_I = (null == (string)this.cboRateFormula.Value ? " " : (string)this.cboRateFormula.Value);     //// 收益率公式

                ////信用评级 add by lujianhao 20180705 STORY #51721 光大证券-监管类信息完善
                gradeProduce.C_XYPJ = this.cboXyPj.Value;

            }
            catch (Exception ye)
            {
                if (ye is TransferException)
                {
                    throw ye;
                }
                else
                {
                    throw new ClsBaseException(ye.Message);
                }
            }

            return gradeProduce;
        }

        /// <summary>
        /// 展示对象属性到控件
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            if (this.status == ClsEnums.StatusSetting.YssAdd)
            {
                return;
            }

            YssProductInfo.Support.Aa.PortCls.Pojo.PortCls classPort = (YssProductInfo.Support.Aa.PortCls.Pojo.PortCls)this.yssGetBaseSelTypeItemMVC();
            try
            {
                if (classPort == null)
                {
                    return;
                }

                //// 组合代码
                this.cboPort.Text = classPort.C_PORT_CODE;

                //// 分级组合代码
                this.txtClsPortCode.Text = classPort.C_PORT_CLS_CODE;

                //// 分级组合名称
                this.txtClsPortName.Text = classPort.C_PORT_CLS_NAME;

                //// 分级组合类型
                this.cboClassType.Value = classPort.C_DV_PORT_CLS_TYPE;

                //// 分级级别
                this.cboClassGrade.Value = classPort.C_DV_PORT_CLS_LEVEL;

                ////级别类型
                this.selDvPortCls.Value = classPort.C_DV_PORT_CLS; ////添加级别类型字段 liuliang 20120508

                //// 起始日期
                this.dtpFoundDate.setDateTime(Convert.ToDateTime(classPort.D_TO_LIST.ToString()));

                //// 终止日期
                this.dtpExpiryDate.setDateTime(Convert.ToDateTime(classPort.D_OFF_LIST.ToString()));

                //// 分级币种
                this.selDtCode.Value = classPort.C_DC_CODE;

                //// 算法公式
                this.cboNetFormula.Value = classPort.C_ALGO_CODE;

                this.cboPortP.IsFocused = false;

                //// 上级分级组合 
                if ("[root]".Equals(classPort.C_PORT_CLS_CODE_P))
                {
                    this.cboPortP.Value = "";
                }
                else
                {
                    this.cboPortP.Value = classPort.C_PORT_CLS_CODE_P;
                }

                //// 轧差
                this.isNetting.Value = classPort.C_DV_NETTING;

                //// 收益分配
                this.selSyfp.Value = classPort.C_DV_INC_DIS;

                //// 年化收益率 add by tangshifeng 20130613
                this.txtYield.Text = (classPort.N_YEAR_INCOME * 100).ToString();       //// 年化收益率

                //// 收益率公式 add by tangshifeng 20130613
                this.cboRateFormula.Value = classPort.C_ALGO_CODE_I;       //// 收益率公式

                //// 收益率类型 add by meipeng 20131225
                if (null != classPort.C_INCOME_TYPE && 0 != classPort.C_INCOME_TYPE.Trim().Length)
                {
                    ////this.SelRateType.Items.Clear();
                    this.SelRateType.Refresh();
                    this.SelRateType.Value = classPort.C_INCOME_TYPE;
                }

                ////信用评级 add by lujianhao 20180705 STORY #51721 光大证券-监管类信息完善
                this.cboXyPj.Value = classPort.C_XYPJ;
                //// 比率公式 add by meip 20140120
                this.selRatioFormula.Value = classPort.C_FORMULA_CODE;

                if (classPort.D_LIQUID_DATE != null)
                {
                    //// 清盘日期 
                    this.dtpLiquid.setDateTime(Convert.ToDateTime(classPort.D_LIQUID_DATE.ToString()));
                }

                this.tbMain.Refresh();
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 公式文本为空，则清空控件值属性
        /// </summary>
        /// <param name="sender">1</param>
        /// <param name="e">2</param>
        private void cboNetFormula_TextChanged(object sender, EventArgs e)
        {
            if ("".Equals(cboNetFormula.Text))
            {
                cboNetFormula.Value = "";
            }
        }

        /// <summary>
        /// 上级分级组合
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboPortP_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo = new FAST.Core.BaseControl.ControlMethodInfo();
            controlMethodInfo.MethodName = "getDataListByTypes";
            string cPortValue = "";
            if (this.cboPort.Text != null)
            {
                ////this.cboPortP.QueryCond = this.cboPort.Value;
                cPortValue = cboPort.Text;
            }

            controlMethodInfo.MethodParamValues = new string[] { cPortValue + "," };
            cboPortP.MethodInfo = controlMethodInfo;
        }

        /// <summary>
        /// 分级类别
        /// </summary>
        /// <param name="sender"> d </param>
        /// <param name="e">d</param>
        ////private void cboClassGrade_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        ////{

        ////}

        private void cboClassGrade_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            this.cboClassGrade.MethodInfo.MethodName = "getDataListByTypes";
            string gradeValue = "";
            if (cboClassGrade.QueryCond != null && cboClassGrade.QueryCond.Trim().Length > 0)
            {
                gradeValue = cboClassGrade.QueryCond;
            }

            this.cboClassGrade.MethodInfo.MethodParamValues = new string[] { gradeValue + "," };
        }

        /// <summary>
        /// 比率公式加载数据前触发
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void selRatioFormula_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            ////e.IsCancel = true;

            ////////据比例公式code去重 获取所有比例公式 By Jinghehe  2014-4-17
            ////IRatioFormulaService ratioFormulaService = ServiceFactory.createService<IRatioFormulaService>();
            ////List<BasePojo> dataList = ratioFormulaService.getAllFormulaDistCode().DataList;
            ////Yss.KRichEx.AutoFilter.Model.KTableEntity entity = null;

            ////if (null != dataList && dataList.Count > 0)
            ////{
            ////    foreach (RatioFormula ratioFormula in dataList)
            ////    {
            ////        entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(ratioFormula);
            ////        e.Collection.Add(entity);
            ////    }
            ////}
        }

        /// <summary>
        /// 锁定特殊控件的状态
        /// </summary>
        protected override void OnLockSpecialControlState()
        {
            base.OnLockSpecialControlState();

            ////List界面被关联内嵌至其他界面时，组合不能被更改
            if (this.frmBaseViewList != null && this.frmBaseViewList is IFormDetailData)
            {
                IFormDetailData frmDetailData = this.frmBaseViewList as IFormDetailData;
                if (frmDetailData.MainDataPojo != null)
                {
                    this.cboPort.YssReadOnly = true;
                    this.cboPort.Text = (frmDetailData.MainDataPojo as FAST.Common.Service.Pojo.Port).C_PORT_CODE;
                }
            }
        }

        /// <summary>
        /// By Jinghehe 需求 18890 关于分级公式，运营费用公式增加直接设置参数功能 
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void formula_ExpandClick(object sender, EventArgs e)
        {
            //////// 传递至估值参数查询参数列表
            ////Dictionary<string, object> paraMap = new Dictionary<string, object>();
            //////// 所选算法公式参数列表
            ////List<string> valueList = new List<string>();
            //////// 产品估值参数menuId
            ////string paramFunCode = "indexmanage";
            ////string algoValue = (sender as FAST.Core.BaseControl.YssSelCombox).Value;
            ////if (null != algoValue)
            ////{
            ////    IAlgoDataService dService = DataServiceFactory.createService<IAlgoDataService>();
            ////    AdvAlgo algo = dService.getDataByCode(algoValue) as AdvAlgo;
            ////    Regex paraRegex = new Regex("\"SV[^\"]*\"| \"AO[^\"]*\"| \"A0[^\"]*\"| \"C0[^\"]*\"| \"HB[^\"]*\"| \"FZ[^\"]*\"| \"LJ[^\"]*\"| \"FXZBJ[^\"]*\" | \"YJJZ[^\"]*\"");

            ////    MatchCollection all = paraRegex.Matches(algo.C_ALGO_FORMULA);
            ////    foreach (Match match in all)
            ////    {
            ////        valueList.Add(match.Value.Replace("\"", ""));
            ////    }
            ////}

            ////paraMap.Add("ARRAY_C_PORT_CODE", string.IsNullOrEmpty(cboPort.Value) ? "" : cboPort.Value);
            ////////add by liyanjun 20140813
            ////paraMap.Add("ARRAY_C_PORT_CLS_CODE", string.IsNullOrEmpty(txtClsPortCode.Text) ? "" : txtClsPortCode.Text);
            ////paraMap.Add("paraCodes", valueList);

            ////try
            ////{
            ////    Frm_CO_PARAMS_S paramSet = new Frm_CO_PARAMS_S();
            ////    paramSet.paraMap = paraMap;
            ////    paramSet.isFromIndex = true;
            ////    paramSet.YssSetStatus = this.YssSetStatus; ////modefiy by liyanjun 20140813
            ////    if (FAST.Core.Context.ClsContext.sysFunHash.ContainsKey(paramFunCode))
            ////    {
            ////        paramSet.YssFormMenu = FAST.Core.Context.ClsContext.sysFunHash[paramFunCode];
            ////    }
            ////    else
            ////    {
            ////        paramSet.YssFormMenu = new SysFun();
            ////        paramSet.YssFormMenu.C_FUN_CODE = paramFunCode;
            ////    }

            ////    paramSet.YssFormMenu.YssAssocia = ClsClzCfgMgr.getAssociaParam(paramFunCode);

            ////    paramSet.initControlStat();
            ////    paramSet.ShowDialog();
            ////}
            ////catch (Exception ex)
            ////{
            ////    YssMessageBox.ShowCommonInfo(ex.Message);
            ////}
        }

        /// <summary>
        /// 参照组合 -- 加载当前组合已设置过的分级记录
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboPortCode_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            try
            {
                if (this.cboPort.Text != null && this.cboPort.Text.Trim().Length > 0)
                {
                    string cPortCode = this.cboPort.Text;
                    this.cboPortCode.MethodInfo.MethodName = "getDataListByTypes";
                    this.cboPortCode.MethodInfo.MethodParamValues = new string[] { cPortCode + "," };
                }

            }
            catch (Exception ye)
            {
                ////YssMessageBox.ShowDyanInformation("通过交易账户控制组合错误", ye.Message, "信息提示", MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtError("002", _formFun, status));
                ClsBaseException.DiscardException(ye);
            }
           
        }

        /// <summary>
        /// 检查分级组合日期段是否有重叠
        /// add by Yuntao Lau 2015.12.19 BUG #123015
        /// </summary>
        /// <returns>是否通过检查</returns>
        private bool checkDate()
        {
            bool isOverLap = false;
            if (!string.IsNullOrEmpty(this.cboPort.Text) && !string.IsNullOrEmpty(this.txtClsPortCode.Value))
            {
                Dictionary<string, string> paraDict = new Dictionary<string, string>();
                paraDict.Add("portCode", this.cboPort.Text);
                paraDict.Add("portCodeCls", this.txtClsPortCode.Value);
                paraDict.Add("foundDate", this.dtpFoundDate.getBeginDateStr);
                paraDict.Add("expiryDate", this.dtpExpiryDate.getBeginDateStr);
                if (this.status == ClsEnums.StatusSetting.YssEdit && null != this.frmBaseViewList.tbMain.SelectedRow)
                {
                    paraDict.Add("ID", (this.frmBaseViewList.tbMain.SelectedRow.Tag as YssProductInfo.Support.Aa.PortCls.Pojo.PortCls).Id);
                }

                string str = portClsService.checkDate(paraDict);
                if ("true".Equals(str))
                {
                    isOverLap = true;
                }
            }

            return isOverLap;
        }
        
        /// <summary>
        /// 检查分级组合日期段是否有重叠
        /// add by xuhanbing 2016.12.7 
        /// STORY #35787 海通资管 赢财升鑫产品的 每年基准收益率参数优化
        /// </summary>
        /// <returns>是否通过检查</returns>
        private bool checkDateQSRQ()
        {
            bool isOverLap = false;
            if (!string.IsNullOrEmpty(this.cboPort.Text) && !string.IsNullOrEmpty(this.txtClsPortCode.Value))
            {
                Dictionary<string, string> paraDict = new Dictionary<string, string>();
                paraDict.Add("portCode", this.cboPort.Text);
                paraDict.Add("portCodeCls", this.txtClsPortCode.Value);
                paraDict.Add("foundDate", this.dtpFoundDate.getBeginDateStr);
                paraDict.Add("expiryDate", this.dtpExpiryDate.getBeginDateStr);
                if (this.status == ClsEnums.StatusSetting.YssEdit && null != this.frmBaseViewList.tbMain.SelectedRow)
                {
                    paraDict.Add("ID", (this.frmBaseViewList.tbMain.SelectedRow.Tag as YssProductInfo.Support.Aa.PortCls.Pojo.PortCls).Id);
                }

                string str = portClsService.checkDateQSRQ(paraDict);
                if ("true".Equals(str))
                {
                    isOverLap = true;
                }
            }

            return isOverLap;
        }

        /// <summary>
        /// s
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboPort_TailClick(object sender, EventArgs e)
        {
            if (status == ClsEnums.StatusSetting.YssBrow)
            {
                return;
            }

            SysFun tempFun = ClsContext.sysMenuFunHash["pd_portct"] as SysFun;
            if (tempFun.YssAssocia == null)
            {
                Yss.CommonLib.ShowMessage("功能[" + tempFun.C_FUN_NAME + "]未上线，请联系赢时胜工程师！");
                return;
            }

            Frm_PORT_CT_L frmPort = new ClsInterface().CreateForm(tempFun) as Frm_PORT_CT_L;
            frmPort.ShowDialog(ClsContext.FrmMain);
            if (frmPort.PrdInfo != null)
            {
                Port port = frmPort.PrdInfo as Port;
                this.cboPort.Text = port.C_PORT_CODE;
                setPortClsState();
            }
        }

        /// <summary>
        /// s
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboPort_TextChanged(object sender, EventArgs e)
        {
            setPortClsState();
            cboPortP.Value = null;
            this.cboPortP.IsFocused = false;
        }

        /// <summary>
        /// ss
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnSave_Click(object sender, EventArgs e)
        {
            base.btnSave_Click(sender, e);
            this.cboPortP.YssReadOnly = true;
        }
    }
}


