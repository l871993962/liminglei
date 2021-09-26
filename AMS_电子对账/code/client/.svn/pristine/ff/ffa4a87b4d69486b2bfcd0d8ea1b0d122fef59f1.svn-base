using System;
using System.Collections.Generic;
using System.Drawing;
using System.Windows.Forms;
using Yss.KTable.Models;
using YssElecReco.Pojo.Er;
using YssElecReco.Service.Er;
using Yss.KRichEx.AutoFilter.Model;
using Yss.FileProcessor;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
using FAST.Core.BaseControl.Fun;
using FAST.Core.Communication.Service;
using FAST.Core.Util;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Context;
using FAST.Core.Communication.BusiService;
using FAST.Common.Service.Dict.Pojo;
using FAST.Core.Exceptions;
using FAST.Core.Resource;
using FAST.Core.BaseControl.Pojo;
using YssInformation.Support.Context;
using FAST.Core.BaseForm;
using Yss.KTable.Collections;
using YssElecReco.Fun;
using Yss.KTable.Enums;
using System.Reflection;
using FAST.Core.BaseControl.YssButtonBars;
using Yss.KMessage;
using YssElecReco.Service.Para;
using YssElecReco.Pojo.Para;

namespace YssElecReco.Form.Er
{
    /// <summary>
    /// 对账结果浏览界面
    /// </summary>
    public partial class Frm_ELEC_BBINFO_L : FrmBaseListWithDetails
    {
        /// <summary>
        /// 声明service对象
        /// </summary>
        private IErBbInfoService iBbInfoService = null;

        /// <summary>
        /// 声明电子对账科目数据列表
        /// </summary>
        private List<ErKmb> lErKmbList = null;

        /// <summary>
        /// xml文件类型
        /// </summary>
        private Frm_ELEC_BBINFO_S frm_ELEC_BBINFO_S = null;

        /// <summary>
        /// BUG233219 华泰证券电子对账管理下方子界面会显示，导致界面太小
        /// 对账详细信息是否显示
        /// </summary>
        private string cShowSplitDetail = "False";

        /// <summary>
        /// STORY #67173【加急】【鹏华基金】为方便自动化流程管理，增加电子对账结果处理界面
        /// 总览界面是否显示未生成数据
        /// </summary>
        private string cShowUnGene = "False";
        /// <summary>
        /// STORY73481【鹏华基金】电子对账管理界面对账类型需要有默认值
        /// </summary>
        private string cfilterDzType = "";

        /// <summary>
        /// STORY #83642 【华宝基金】二期电子对账管理记忆功能
        /// </summary>
        private string cDataShowValue = "LAST_DATA_GENE";

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_ELEC_BBINFO_L()
        {
            InitializeComponent();
            this.bUseMVCService = true;
            _allowTableMainRepeatClick = true;
            this.AddAstStatExportButton();
            cShowUnGene = IniFileOperator.ReadIniData("ELEC", "ShowUnGeneData", "False", System.Windows.Forms.Application.StartupPath + @"\config\app.ini");
            cShowSplitDetail = IniFileOperator.ReadIniData("ELEC", "ShowSplitDetail", "True", System.Windows.Forms.Application.StartupPath + @"\config\app.ini");
            cfilterDzType = IniFileOperator.ReadIniData("ELEC", "filterDzType", "", System.Windows.Forms.Application.StartupPath + @"\config\app.ini");
            cDataShowValue = IniFileOperator.ReadIniData("ELEC", "DataShowValue", "", System.Windows.Forms.Application.StartupPath + @"\config\app.ini");
            this.pnlDetails.Visible = false;
            (this.UnGenecheckBox.Tag as Cell).InnerControlView = InnerControlViewStyle.HideAlways;
        }

        /// <summary>
        /// 装载明细窗体功能代码列表
        /// </summary>
        /// <returns>返回明细窗体功能代码列表</returns>
        protected override List<SysFun> LoadDetailFormFuns()
        {
            List<SysFun> sysFuns = new List<SysFun>();
            SysFun newFun = new SysFun();
            newFun.C_FUN_CODE = "erDetail";
            newFun.C_FUN_NAME = "";
            sysFuns.Add(newFun);
            return sysFuns;
        }

        /// <summary>
        /// tbMain行选中事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">事件参数</param>
        protected override void tbMain_SelectionChanged(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            base.tbMain_SelectionChanged(sender, e);
            Row r = e.Row;
            if(null != r && r.Tag != null)
            {
                ErBbInfo info = (ErBbInfo)r.Tag;
                if ("锁定".Equals(info.C_CONFIRM_EXECUTE) || "LOCK".Equals(info.C_CONFIRM_EXECUTE))
                {
                    this.btnBar.setButtonEnabled("btnJzAudit");
                }
                else
                {
                    this.btnBar.setButtonDisabled("btnJzAudit");
                }
            }
        }

        /// <summary>
        /// TabConrol主数据选择分页改变事件
        /// </summary>
        /// <param name="e">事件参数</param>
        protected override void OnTabCtrlDataMainPageChanged(Yss.Controls.TabPageEventArgs e)
        {
            btnSearch_Click(null, EventArgs.Empty);
            (this.UnGenecheckBox.Tag as Cell).InnerControlView = InnerControlViewStyle.HideAlways;
            if (this.tabCtrlDataMain.SelectedTab == this.tabPageDefault || this.tabCtrlDataMain.SelectedTab == this.tabPageZL || this.tabCtrlDataMain.SelectedTab == this.tabPageBDZ)
            {
                this.pnlDetails.Visible = false;
                if (this.tabCtrlDataMain.SelectedTab == this.tabPageZL)
                {
                    (this.UnGenecheckBox.Tag as Cell).InnerControlView = InnerControlViewStyle.ShowAlways;
                    this.UnGenecheckBox.Checked = "True".Equals(this.cShowUnGene) ? true : false;
                }
            }
            else
            {
                //// BUG233219 华泰证券电子对账管理下方子界面会显示，导致界面太小
                if ("False".Equals(cShowSplitDetail))
                {
                    this.pnlDetails.Visible = false;
                }
                else
                {
                    this.pnlDetails.Visible = true;
                }
            }
        }

        /// <summary>
        /// 开始装载明细数据-这里定位明细数据窗体，主数据Pojo
        /// </summary>
        /// <param name="rowMain">当前主区域选中的行</param>
        /// <param name="frmDetail">明细数据窗体</param>
        /// <param name="mainData">主数据Pojo</param>
        /// <returns>返回初始化结果</returns>
        protected override bool BeginReloadDetailData(Row rowMain, out FrmBase frmDetail, out BasePojo mainData)
        {
            bool result = base.BeginReloadDetailData(rowMain, out frmDetail, out mainData);
            if (frmDetail != null && frmDetail is Frm_ELEC_DETAIL_L)
            {
                if (this.tabPageFKF == this.tabCtrlDataMain.SelectedTab || this.tabPageFKS == this.tabCtrlDataMain.SelectedTab)
                {
                    ((Frm_ELEC_DETAIL_L)frmDetail).ShowPageResult = true;
                }
                else
                {
                    ((Frm_ELEC_DETAIL_L)frmDetail).ShowPageResult = false;
                }

                if (this.tabCtrlDataMain.SelectedTab == this.tabPageDefault || this.tabCtrlDataMain.SelectedTab == this.tabPageZL || this.tabCtrlDataMain.SelectedTab == this.tabPageBDZ)
                {
                    return false;
                }
            }

            return result;
        }

        /// <summary>
        /// 封装前台查询条件
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>paraDict</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            try
            {
                string search = this.yssBuildLeftCheckRowsStr("portfolio");
                search = search.Replace("'", "");
                paraDict.Add("ARRAY_C_PORT_CODE", search);
                ////STORY #67173【加急】【鹏华基金】为方便自动化流程管理，增加电子对账结果处理界面
                if (this.cboDzType.Value != null)
                {
                    string sdzType = this.cboDzType.Value.Replace("|", ",");
                    paraDict.Add("ARRAY_C_DZ_CODE", sdzType);
                }

                paraDict.Add("D_TRADE_START", this.dtpDzDate.getBeginDateStr);

                if (this.dtpDzDate.YssShowSecond)
                {
                    paraDict.Add("D_TRADE_END", this.dtpDzDate.getEndDateStr);
                }

                ////STORY57791【鹏华基金】电子对账管理优化需求  根据生成C_IDEN查询最后一次生成的数据
                if (this.cboShowData.Value != null && this.cboShowData.Value.Equals("LAST_DATA_GENE"))
                {
                    paraDict.Add("C_DV_SHOW", "1");
                }

                ////STORY57791【鹏华基金】电子对账管理优化需求  根据修改时间查询最后一次收到托管行反馈的数据
                if (this.cboShowData.Value != null && this.cboShowData.Value.Equals("LAST_DATA_RECE")
                    && this.tabPageFKF == this.tabCtrlDataMain.SelectedTab)
                { ////并且是反馈结果分页
                    paraDict.Add("C_DV_SHOW_RECE", "1");
                }

                if (this.tabPageDefault == this.tabCtrlDataMain.SelectedTab)
                {
                    paraDict.Add("ARRAY_C_STATE", "ER_UNGENE");
                }
                else if (this.tabPageYSC == this.tabCtrlDataMain.SelectedTab)
                {
                    paraDict.Add("ARRAY_C_STATE", "ER_SEND");
                }
                else if (this.tabPageFSZ == this.tabCtrlDataMain.SelectedTab)
                {
                    paraDict.Add("ARRAY_C_STATE", "ER_SENDED,ER_SENDED_FAIL");
                }
                else if (this.tabPageFSS == this.tabCtrlDataMain.SelectedTab)
                {
                    paraDict.Add("ARRAY_C_STATE", "ER_SENDED_SECCUSS");
                }
                else if (this.tabPageFKF == this.tabCtrlDataMain.SelectedTab)
                {
                    paraDict.Add("ARRAY_C_STATE", "ER_IDENTICAL,ER_ACCECPED");
                    if (this.cboSgyy.Value != null)
                    {
                        paraDict.Add("C_DV_RESULT", this.cboSgyy.Value);
                    }
                    ////BUG #229572 目前21.6版本缺少 处理状态的查询条件 从21.5又把改状态改回来了  就针对总览界面  以及反馈结果界面
                    if (this.cboStatus.Value != null)
                    {
                        if ("ER_IDENTICAL".Equals(this.cboStatus.Value) || "ER_ACCECPED".Equals(this.cboStatus.Value))
                        {
                            paraDict["ARRAY_C_STATE"] = this.cboStatus.Value;
                        }
                    }

                }
                else if (this.tabPageFKS == this.tabCtrlDataMain.SelectedTab)
                {
                    paraDict.Add("ARRAY_C_STATE", "ER_ACCECPED");
                    ////STORY54447反馈不一致结果人工对账一致处理需填写原因与说明信息
                    ////添加原因条件
                    if (this.cboSgyy.Value != null)
                    {
                        paraDict.Add("C_DV_RESULT", this.cboSgyy.Value);
                    }
                }
                else if (this.tabPageBDZ == this.tabCtrlDataMain.SelectedTab)
                {
                    paraDict.Add("ARRAY_C_STATE", "ER_BDZ");
                }
                else if (this.tabPageZL == this.tabCtrlDataMain.SelectedTab)
                { ////BUG #229572 目前21.6版本缺少 处理状态的查询条件 从21.5又把改状态改回来了  就针对总览界面  以及反馈结果界面
                    if (this.cboStatus.Value != null)
                    {
                        paraDict.Add("ARRAY_C_STATE", this.cboStatus.Value);
                    }

                    if (this.UnGenecheckBox.Checked)
                    {
                        paraDict.Add("ShowUnGeneData", "true");
                    }

                }

            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500037", _formFun, status));
                ClsBaseException.DiscardException(ex);
            }

            return paraDict;
        }

        /// <summary>
        /// 浏览电子对账信息界面窗体load事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ELEC_BBINFO_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.iBbInfoService = ServiceFactory.createService(serviceType) as IErBbInfoService;
            this.dataService = this.iBbInfoService;

            btnBar.setButtonText(ClsButtonName.BtnCopy, "发送");
            btnBar.setButtonToolTip(ClsButtonName.BtnCopy, "发送");

            ////btnBar.setButtonText(ClsButtonName.btnConfirm, "净值确认");
            ////btnBar.setButtonToolTip(ClsButtonName.btnConfirm, "净值确认");
            ////btnBar.setButtonImage(ClsButtonName.btnConfirm, FAST.Resource.Resource.confirm);

            /*
            ClsButtonInfo btnLock = new ClsButtonInfo();
            btnLock.Text = "净值锁定";
            btnLock.Tooltip = "净值锁定";
            btnLock.Name = "btnLock";
            btnLock.Image = FAST.Resource.Resource.Main_btnUnLock;
            btnLock.ClickEvent += new EventHandler(this.btnEconfirm_Click);
            btnBar.addButton(btnLock, 15);
            */

            ClsButtonInfo btnJzConfirm = new ClsButtonInfo();
            btnJzConfirm.Text = "净值确认";
            btnJzConfirm.Tooltip = "净值确认";
            btnJzConfirm.Name = "btnJzConfirm";
            btnJzConfirm.Image = FAST.Resource.Resource.confirm;
            btnJzConfirm.ClickEvent += new EventHandler(this.btnEconfirm_Click);
            btnBar.addButton(btnJzConfirm, 16);

            ////STORY #104480 净值确认管理优化整合需求对电子对账管理的影响评估优化
            ClsButtonInfo btnJzAudit = new ClsButtonInfo();
            btnJzAudit.Text = "净值审核";
            btnJzAudit.Tooltip = "净值审核";
            btnJzAudit.Name = "btnJzAudit";
            btnJzAudit.Image = FAST.Resource.Resource.Pass;
            btnJzAudit.ClickEvent += new EventHandler(this.btnJzAudit_Click);
            btnBar.addButton(btnJzAudit, 17);

            ClsButtonInfo btnNoER = new ClsButtonInfo();
            ////STORY54447反馈不一致结果人工对账一致处理需填写原因与说明信息
            ////【未生成】标签中，“不对账”功能按钮，名称改为“其他对账”
            ////btnNoER.Text = "不对账";
            btnNoER.Text = "其他对账";
            btnNoER.Tooltip = "设置组合不对账";
            btnNoER.Name = "btnNoER";
            btnNoER.Image = FAST.Resource.Resource.btnUnBind_L;
            btnNoER.ClickEvent = btnNoER_Click;
            btnBar.addButton(btnNoER, 7);

            ClsButtonInfo btnUnNoER = new ClsButtonInfo();
            btnUnNoER.Text = "解除限制";
            btnUnNoER.Tooltip = "解除组合不对账限制";
            btnUnNoER.Name = "btnUnNoER";
            btnUnNoER.Image = FAST.Resource.Resource.agencySet_L;
            btnUnNoER.ClickEvent = btnUNNoER_Click;
            btnBar.addButton(btnUnNoER, 8);

            ////wlx 20160830 STORY31217【招商财富】电子对账管理已反馈新增一个人工可以修改处理状态的功能
            ////if (btnBar.UserOperList != null && btnBar.UserOperList.Contains(ClsButtonName.BtnEdit))
            ////{
            ClsButtonInfo btnAccept = new ClsButtonInfo();
            btnAccept.Text = "对账一致";
            btnAccept.Tooltip = "手工对账一致";
            btnAccept.Name = "btnAccept";
            btnAccept.Image = FAST.Resource.Resource.btnEdit_L;
            btnAccept.ClickEvent = btnAccept_Click;
            btnBar.addButton(btnAccept, 6);
            ClsButtonInfo btnEdit = new ClsButtonInfo();
            btnEdit.Name = ClsButtonName.BtnEdit;
            ////对账一致的使用的修改权限，但修改按钮暂时没有用，所以隐藏
            btnBar.removeButton(btnEdit);
            ////}

            ////STORY #77811 要可以撤销手工设置的对帐一致 
            ClsButtonInfo btnUnAccept = new ClsButtonInfo();
            btnUnAccept.Text = "撤销手工一致";
            btnUnAccept.Tooltip = "撤销手工设置的对账一致";
            btnUnAccept.Name = "btnUnAccept";
            btnUnAccept.Image = FAST.Resource.Resource.btnUnAudit_L;
            //// btnUnAccept.Image = FAST.Resource.Resource.btnCancel_Click;
            btnUnAccept.ClickEvent = btnUnAccept_Click;
            btnBar.addButton(btnUnAccept, 8);

            ////STORY #103840 电子对账功能优化
            ClsButtonInfo btnDbCheck = new ClsButtonInfo();
            btnDbCheck.Text = "手工对账审核";
            btnDbCheck.Tooltip = "手工对账审核";
            btnDbCheck.Name = "btnDbCheck";
            btnDbCheck.Image = FAST.Resource.Resource.audit_OK;
            btnDbCheck.ClickEvent = btnDbCheck_Click;
            btnBar.addButton(btnDbCheck, 9);

            ////加急STORY #96252 对账结果明细需支持导出
            ClsButtonInfo btnBatchMxData = new ClsButtonInfo();
            btnBatchMxData.Text = "批量明细数据";
            btnBatchMxData.Tooltip = "批量明细数据";
            btnBatchMxData.Name = "btnBatchMxData";
            btnBatchMxData.Image = FAST.Resource.Resource.btnChoose_L;
            //// btnUnAccept.Image = FAST.Resource.Resource.btnCancel_Click;
            btnBatchMxData.ClickEvent = btnBatchMxData_Click;
            btnBar.addButton(btnBatchMxData, 10);

            cboShowData_YssOnBeforeLoadData(this.cboShowData, new YssBeforeOperEventArgs());
            this.cboDzType.Value = cfilterDzType;
            this.cboShowData.Value = cDataShowValue;

            //STORY #103840 电子对账功能优化 按钮权限管理
            List<string> lstUserOperFun = ((List<string>)ClsContext.HtUserOperRight["dzBbInfo"]);
            if (null != lstUserOperFun && lstUserOperFun.Contains("DBCHK"))
            {
                this.btnBar.setButtonVisable("btnDbCheck", true);
                this.btnBar.setButtonEnabled("btnDbCheck");
            }
            else
            {
                this.btnBar.setButtonVisable("btnDbCheck", false);
                this.btnBar.setButtonDisabled("btnDbCheck");
            }

            ////STORY #104480 净值确认管理优化整合需求对电子对账管理的影响评估优化
            lstUserOperFun = ((List<string>)ClsContext.HtUserOperRight["eConfirm"]);
            if (null != lstUserOperFun && (lstUserOperFun.Contains("CHK") || lstUserOperFun.Contains("btnAudit")))
            {
                this.btnBar.setButtonVisable("btnJzAudit", true);
                this.btnBar.setButtonEnabled("btnJzAudit");
            }
            else
            {
                this.btnBar.setButtonVisable("btnJzAudit", false);
                this.btnBar.setButtonDisabled("btnJzAudit");
            }

        }

         /// <summary>
        /// 手工对账审核
        /// STORY #103840 电子对账功能优化
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnDbCheck_Click(object sender, EventArgs e)
        {
            List<ErBbInfo> dataList = yssGetSelItemList();
            if (dataList == null || dataList.Count == 0)
            {
                //未选择数据时，给出提示
                Yss.KMessage.MessageDialog messS = new Yss.KMessage.MessageDialog();
                messS.Show("请先选择数据！", "警告", MessageBoxButtons.OK);
                return;
            }

            string userCode = ClsContext.currentUser.C_USER_CODE;
            foreach (ErBbInfo info in dataList)
            {
                if (userCode.Equals(info.Modifier))
                {
                    ////给出提示
                    Yss.KMessage.MessageDialog messS = new Yss.KMessage.MessageDialog();
                    messS.Show("对账一致操作人和审核人不能为同一人,不允许审核！", "警告", MessageBoxButtons.OK);
                    return;
                }
            }

            List<ErBbInfo> bbInfoDataList = new List<ErBbInfo>();
            foreach (ErBbInfo info in dataList)
            {
                if (!string.IsNullOrEmpty(info.C_ERR_INFO) && info.C_ERR_INFO.Contains("未审核"))
                {
                    info.C_ERR_INFO = "手工对账一致";
                    info.Modifier = ClsContext.currentUser.C_USER_CODE;
                    info.ModifyDate = DateTime.Now.ToString("yyyyMMdd HH:mm:ss");
                    bbInfoDataList.Add(info);
                }

            }

            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            IErBbInfoService iErBbInfoService = ServiceFactory.createService(serviceType) as IErBbInfoService;
            iErBbInfoService.acceptBbInfo(bbInfoDataList);
            ////刷新界面数据
            this.btnSearch_Click(null, null);
        }

        /// <summary>
        /// 对账一致
        /// wlx 20160830 STORY31217【招商财富】电子对账管理已反馈新增一个人工可以修改处理状态的功能
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnAccept_Click(object sender, EventArgs e)
        {
            //this.btnBar.setButtonDisabled("btnAccept");
            Frm_ELEC_HANDACCEPT_S frm_Rgyy_S = null;
            bool dbCheckIsAuth = this.btnBar.getButton("btnDbCheck").Owner.Visible;
            List<ErBbInfo> dataList = yssGetSelItemList();
            if (dataList == null || dataList.Count == 0)
            {
                //未选择数据时，给出提示
                Yss.KMessage.MessageDialog messS = new Yss.KMessage.MessageDialog();
                messS.Show("请先选择数据！", "警告", MessageBoxButtons.OK);
                return;
            }

            foreach (ErBbInfo info in dataList)
            {
                if ("已确认".Equals(info.C_CONFIRM_EXECUTE) || "锁定".Equals(info.C_CONFIRM_EXECUTE)
                    || "LOCK".Equals(info.C_CONFIRM_EXECUTE))
                {
                    Yss.CommonLib.ShowMessage("有已经净值确认的数据!不能手工对账一致！");
                    return;
                }
            }

            if (this.tabPageFKF == this.tabCtrlDataMain.SelectedTab)
            {
                status = ClsEnums.StatusSetting.YssEdit;
                frm_Rgyy_S = new Frm_ELEC_HANDACCEPT_S(ElecConstant.FKF_FLAG, dataList);
                frm_Rgyy_S.setDbCheckIsAuth(dbCheckIsAuth);
                frm_Rgyy_S.yssShowForm(this);
            }
            else if (this.tabPageBDZ == this.tabCtrlDataMain.SelectedTab)
            {
                status = ClsEnums.StatusSetting.YssAdd;
                frm_Rgyy_S = new Frm_ELEC_HANDACCEPT_S(ElecConstant.QTDZ_FLAG, dataList);
                frm_Rgyy_S.setDbCheckIsAuth(dbCheckIsAuth);
                frm_Rgyy_S.yssShowForm(this);
            }
            else if (this.tabPageZL == this.tabCtrlDataMain.SelectedTab)
            {
                //STORY #67173【加急】【鹏华基金】为方便自动化流程管理，增加电子对账结果处理界面，任何状态都可以对账一致
                ////foreach (ErBbInfo info in dataList)
                ////{
                ////    if (!"ER_IDENTICAL".Equals(info.C_STATE))
                ////    {
                ////        Yss.KMessage.MessageDialog mess = new Yss.KMessage.MessageDialog();
                ////        mess.Show("只能操作状态为【对账不一致】的数据！", "提示", MessageBoxButtons.OK);
                ////        return;
                ////    }

                ////}
                status = ClsEnums.StatusSetting.YssEdit;
                frm_Rgyy_S = new Frm_ELEC_HANDACCEPT_S(ElecConstant.ZL_FLAG, dataList);
                frm_Rgyy_S.setDbCheckIsAuth(dbCheckIsAuth);
                frm_Rgyy_S.yssShowForm(this);
            }

            /* 该操作放到Frm_ELEC_HANDACCEPT_S界面
            try
            {
              foreach (ErBbInfo info in dataList)
             {
                 if (!"ER_IDENTICAL".Equals(info.C_STATE))
                 {
                     Yss.KMessage.MessageDialog mess = new Yss.KMessage.MessageDialog();
                     mess.Show("只能操作状态为【对账不一致】的数据！", "提示", MessageBoxButtons.OK);
                     return;
                 }
                 else
                 {
                     info.C_STATE = "ER_ACCECPED";
                     info.C_ERR_INFO = "手工对账一致";
                     info.Modifier = ClsContext.currentUser.C_USER_CODE;
                     info.ModifyDate = DateTime.Now.ToString("yyyyMMdd HH:mm:ss");
                 }
             }
               

             string res = bbInfoService.acceptBbInfo(dataList);
                  
        }
        catch (Exception ex)
        {
            Yss.KMessage.MessageDialog mess = new Yss.KMessage.MessageDialog();
            mess.Show(ex.Message, "警告", MessageBoxButtons.OK);
        }
        finally
        {
            ////刷新界面数据
            this.btnSearch_Click(null, null);
            ////更新按钮状态，放在刷新操作后处理
            ////this.btnBar.setButtonEnabled("btnAccept");
        }*/
        }

        /// <summary>
        /// 批量明细数据
        /// STORY #96252 对账结果明细需支持导出
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnBatchMxData_Click(object sender, EventArgs e)
        {
            List<ErBbInfo> dataList = yssGetSelItemList();
            List<Port> portList = this.getLeftCheckedPort();
            foreach (ErBbInfo p in dataList)
            {
                if (!"1011".Equals(p.C_FILE_TYPE) && !"1013".Equals(p.C_FILE_TYPE))
                {
                    Yss.KMessage.MessageDialog messS = new Yss.KMessage.MessageDialog();
                    messS.Show("请注意，勾选数据中有不是【 文件类型=“估值表”或“双估值表” 】的其他数据，请重新选择！", "警告", MessageBoxButtons.OK);
                    return;
                }
            }

            ////ErBbInfo bbInfo3 = row.Tag as ErBbInfo;
            ////
            Frm_ELEC_BATCHMX_S set = new Frm_ELEC_BATCHMX_S();
            set.DataList = dataList;
            set.PortList = portList;
            set.setParentList(this);
            SysFun cls_FUN = new SysFun();
            cls_FUN.YssAssocia = ClsClzCfgMgr.getAssociaParam("erDetail");
            //// 这里手动赋值，否则当获取提示信息时通过cls_FUN.C_FUN_CODE
            //// 时会出现异常YssCore.Info.ClsRetInfoDealer.getFunCode方法
            cls_FUN.C_FUN_CODE = cls_FUN.YssAssocia.FunCode;
            ////cls_FUN.C_FUN_NAME = cls_FUN.YssAssocia.FunName;
            cls_FUN.C_FUN_NAME = "电子对账详细信息";
            set.ShowInTaskbar = false;

            set.YssFormMenu = cls_FUN;

            set.yssInitForm();

            set.Show(this);
            set.btnBar.setAllButtonEnabled(true);
            ////wlx 20160731 BUG135535太平年金-电子对账导出按钮点击无效
            set.btnBar.setButtonEnabled(ClsButtonName.ToolExport, true, true);
            set.btnBar.setButtonVisable(ClsButtonName.BtnExportAllPages, false);
            set.btnBar.setButtonEnabled(ClsButtonName.BtnExportSetting, true, true);
        }

        /// <summary>
        /// 解除对账一致
        /// wSTORY #77811 要可以撤销手工设置的对帐一致
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnUnAccept_Click(object sender, EventArgs e)
        {
            //this.btnBar.setButtonDisabled("btnAccept");
            Frm_ELEC_HANDACCEPT_S frm_Rgyy_S = null;
            List<ErBbInfo> dataList = yssGetSelItemList();
            if (dataList == null || dataList.Count == 0)
            {
                //未选择数据时，给出提示
                Yss.KMessage.MessageDialog messS = new Yss.KMessage.MessageDialog();
                messS.Show("请先选择数据！", "警告", MessageBoxButtons.OK);
                return;
            }
            foreach (ErBbInfo erBbinfo in dataList)
            {
                if (!erBbinfo.C_ERR_INFO.Contains("手工对账一致") || erBbinfo.C_DV_RESULT.Equals(""))
                {
                    //没有手工对账给出提示
                    Yss.KMessage.MessageDialog messS = new Yss.KMessage.MessageDialog();
                    messS.Show("所选择的组合未设置手工对账一致，不可撤回！", "警告", MessageBoxButtons.OK);
                    return;
                }

                if ("已确认".Equals(erBbinfo.C_CONFIRM_EXECUTE) || "锁定".Equals(erBbinfo.C_CONFIRM_EXECUTE)
                    || "LOCK".Equals(erBbinfo.C_CONFIRM_EXECUTE))
                {
                    Yss.CommonLib.ShowMessage("有已经净值确认的数据!不能撤销手工对账一致！");
                    return;
                }

                erBbinfo.OperUser = ClsContext.currentUser.C_USER_CODE;
                erBbinfo.AuditDate = ClsFunction.formatDateTime("yyyyMMdd HH:mm:ss", DateTime.Now.ToString());

            }
            try
            {
                iBbInfoService.unAcceptClick(dataList);
            }
            catch (Exception ex)
            {
                Yss.KMessage.MessageDialog mess = new Yss.KMessage.MessageDialog();
                mess.Show(ex.Message, "警告", MessageBoxButtons.OK);
            }
            finally
            {
                ////刷新界面数据
                this.btnSearch_Click(null, null);
            }
        }

        /// <summary>
        /// 设置组合不对账
        /// oyk 20180307 STORY #50374 电子对账功能优化 (#5 #4 #3 #2 #1 )
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnNoER_Click(object sender, EventArgs e)
        {
            List<ErBbInfo> dataList = yssGetSelItemList();
            if (dataList == null || dataList.Count == 0)
            {
                return;
            }

            try
            {
                string res = iBbInfoService.UnPortOper(dataList, "add");
            }
            catch (Exception ex)
            {
                Yss.KMessage.MessageDialog mess = new Yss.KMessage.MessageDialog();
                mess.Show(ex.Message, "警告", MessageBoxButtons.OK);
            }
            finally
            {
                ////刷新界面数据
                this.btnSearch_Click(null, null);
            }
        }

        /// <summary>
        /// 解除组合不对账限制
        /// oyk 20180307 STORY #50374 电子对账功能优化 (#5 #4 #3 #2 #1 )
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnUNNoER_Click(object sender, EventArgs e)
        {
            List<ErBbInfo> dataList = yssGetSelItemList();
            if (dataList == null || dataList.Count == 0)
            {
                return;
            }

            try
            {
                string res = iBbInfoService.UnPortOper(dataList, "remove");
            }
            catch (Exception ex)
            {
                Yss.KMessage.MessageDialog mess = new Yss.KMessage.MessageDialog();
                mess.Show(ex.Message, "警告", MessageBoxButtons.OK);
            }
            finally
            {
                ////刷新界面数据
                this.btnSearch_Click(null, null);
            }
        }

        /// <summary>
        /// 发送事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnCopy_Click(object sender, EventArgs e)
        {
            List<ErBbInfo> dataList = null;
            List<ErBbInfo> sendList = new List<ErBbInfo>();
            dataList = yssGetSelItemList();
            if (dataList == null || dataList.Count == 0)
            {
                return;
            }

            this.btnBar.setButtonDisabled(ClsButtonName.BtnCopy);
            bool bAlert = false;
            foreach (ErBbInfo info in dataList)
            {
                if (info.AuditState == 0 && !bAlert)
                {
                    ////选择的数据中包含未审核的数据，未审核数据不会发送
                    ClsRetInfo retInfo = new ClsRetInfo();
                    retInfo = ClsRetInfoDealer.getExtWarn("003", _formFun, ClsEnums.StatusSetting.YssSend);
                    YssMessageBox.ShowCommonInfo(retInfo);
                    bAlert = true;
                }
                else if (info.AuditState == 1)
                {   ////BUG1768511550电子对账问题 3.选中未审核的数据，进行发送，提示未发送，但是系统能够发送成功
                    sendList.Add(info);
                }
            }

            if (sendList.Count == 0)
            {
                return;
            }

            string res = iBbInfoService.sendBbInfo(sendList);

            if ("Success" == res)
            {
                ClsRetInfo retInfo = new ClsRetInfo();
                retInfo = ClsRetInfoDealer.getCommonInfo("INF-100002", _formFun.C_FUN_CODE, ClsEnums.StatusSetting.YssSend);
                this.tbMain.Refresh();
            }
            else if ("Fault" == res)
            {
                ////MessageBox.Show(res);
            }
            else if ("" == res)
            {
                ////伺服器没有连通，稍后发送
                ClsRetInfo retInfo = new ClsRetInfo();
                retInfo = ClsRetInfoDealer.getExtWarn("004", _formFun, ClsEnums.StatusSetting.YssSend);
                YssMessageBox.ShowCommonInfo(retInfo);
            }

            ////刷新界面数据
            this.btnSearch_Click(null, null);
            ////更新按钮状态，放在刷新后操作
            this.btnBar.setButtonEnabled(ClsButtonName.BtnCopy);
        }

        /// <summary>
        /// 重写行双击事件，用于根据选中的对账信息，
        /// 加载相应的对账数据
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbMain_RowDoubleClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            try
            {
                Row row = sender as Row;

                if (null != row && !row.IsGroup)
                {
                    ErBbInfo xbbInfo = row.Tag as ErBbInfo;
                    if (xbbInfo.Id.Trim().Length == 0)
                    {
                        return;
                    }

                    /////双击打开时，有可能当前行的数据已经被改变了，导致弹出来的明细数据与行显示不一致
                    /////从后台进行查询 当前行对象判断是否改变了 状态，如果改变了状态，刷新当前行的单元格数据
                    ErBbInfo xbbInfo2 = iBbInfoService.getBbInfoById(xbbInfo.Id);

                    if (xbbInfo2.C_ERR_INFO.Equals(xbbInfo.C_ERR_INFO) && xbbInfo2.C_STATE.EndsWith(xbbInfo.C_STATE))
                    {
                    }
                    else
                    {
                        xbbInfo2.C_PORT_CODE = xbbInfo.C_PORT_CODE;
                        row.Tag = xbbInfo2;
                        Dictionary<string, string> dict = new Dictionary<string, string>();
                        dict.Add("ER_SENDED", "已发送");
                        dict.Add("ER_SENDED_SECCUSS", "发送成功");
                        dict.Add("ER_SENDED_FAIL", "发送失败");
                        dict.Add("ER_ACCECPED", "对账一致");
                        dict.Add("ER_SEND", "已生成");
                        dict.Add("ER_IDENTICAL", "对账不一致");
                        foreach (Cell cell in row.Cells)
                        {
                            if (cell.RelColumn.Text.Equals("提示信息"))
                            {
                                cell.Text = xbbInfo2.C_ERR_INFO;
                            }
                            else if (cell.RelColumn.Text.Equals("处理状态"))
                            {
                                cell.Text = dict[xbbInfo2.C_STATE];
                            }
                        }

                        ////刷新单元格
                        tbMain.Refresh();

                    }

                    /////end

                    if (frm_ELEC_BBINFO_S == null || frm_ELEC_BBINFO_S.IsDisposed)
                    {
                        if (xbbInfo.C_FILE_TYPE.Equals("1001"))
                        {
                            frm_ELEC_BBINFO_S = new Frm_ELEC_YE_S();
                        }
                        else if (xbbInfo.C_FILE_TYPE.Equals("1011"))
                        {
                            frm_ELEC_BBINFO_S = new Frm_ELEC_GZ_S();
                        }
                        else if (xbbInfo.C_FILE_TYPE.Equals("1013"))
                        {
                            frm_ELEC_BBINFO_S = new Frm_ELEC_DBL_GZ_S();
                        }
                        else if (xbbInfo.C_FILE_TYPE.Equals("1031"))
                        {
                            frm_ELEC_BBINFO_S = new Frm_ELEC_KM_S();
                        }
                        else if (xbbInfo.C_FILE_TYPE.Equals("1701") || xbbInfo.C_FILE_TYPE.Equals("1711"))
                        {
                            frm_ELEC_BBINFO_S = new Frm_ELEC_ZCFZ_S();
                        }
                        else if (xbbInfo.C_FILE_TYPE.Equals("1801") || xbbInfo.C_FILE_TYPE.Equals("1811"))
                        {
                            frm_ELEC_BBINFO_S = new Frm_ELEC_LR_S();
                        }
                        else if (xbbInfo.C_FILE_TYPE.Equals("1901"))
                        {
                            frm_ELEC_BBINFO_S = new Frm_ELEC_SYZQY_S();
                        }
                        else if (xbbInfo.C_FILE_TYPE.Equals("1903"))
                        {
                            frm_ELEC_BBINFO_S = new Frm_ELEC_JZCBDB_S();
                        }
                        else if (xbbInfo.C_FILE_TYPE.Equals("A001"))
                        {
                            return;
                        }

                        //// edit by qiantao STORY #83025 产品估值参数控制实收资本小数位 
                        ErBbInfo bbInfo3 = row.Tag as ErBbInfo;
                        frm_ELEC_BBINFO_S.C_PORT_CODE = null != bbInfo3 ? bbInfo3.C_PORT_CODE : "";

                        frm_ELEC_BBINFO_S.setParentList(this);

                        SysFun cls_FUN = new SysFun();
                        cls_FUN.YssAssocia = ClsClzCfgMgr.getAssociaParam("erDetail");
                        //// 这里手动赋值，否则当获取提示信息时通过cls_FUN.C_FUN_CODE
                        //// 时会出现异常YssCore.Info.ClsRetInfoDealer.getFunCode方法
                        cls_FUN.C_FUN_CODE = cls_FUN.YssAssocia.FunCode;
                        ////cls_FUN.C_FUN_NAME = cls_FUN.YssAssocia.FunName;
                        cls_FUN.C_FUN_NAME = "电子对账详细信息";
                        frm_ELEC_BBINFO_S.ShowInTaskbar = false;

                        frm_ELEC_BBINFO_S.YssFormMenu = cls_FUN;

                        frm_ELEC_BBINFO_S.yssInitForm();

                        frm_ELEC_BBINFO_S.Show(this);
                        frm_ELEC_BBINFO_S.btnBar.setAllButtonEnabled(true);
                        ////wlx 20160731 BUG135535太平年金-电子对账导出按钮点击无效
                        frm_ELEC_BBINFO_S.btnBar.setButtonEnabled(ClsButtonName.ToolExport, true, true);
                        frm_ELEC_BBINFO_S.btnBar.setButtonVisable(ClsButtonName.BtnExportAllPages, false);
                        frm_ELEC_BBINFO_S.btnBar.setButtonEnabled(ClsButtonName.BtnExportSetting, true, true);
                    }
                }
                else if (row.IsGroup)
                {
                    if (row.ShowChild)
                    {
                        this.tbMain.CollapseAll(row);
                    }
                    else
                    {
                        this.tbMain.ExpandAll(row);
                    }

                    this.tbMain.Refresh();
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        ///  重写修改按钮点击事件，借用做“生成”按钮
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnGernerate_Click(object sender, EventArgs e)
        {
            try
            {
                ////装载“产生电子对账”界面
                SysFun cls_FUN = ClsContext.sysMenuFunHash["elecGene"] as SysFun;
                Yss.Controls.TabPage tabPage = clsInterface.OpenTabPageAndForm(ClsContext.MainFormTabControl, cls_FUN);
                Frm_ELEC_GENE_L frmList = tabPage.Controls[0] as Frm_ELEC_GENE_L;
                if (frmList != null)
                {
                    frmList.DateTimeInterval = this.dtpDzDate;
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 获取A区结果集信息，对象为勾选的记录
        /// </summary>
        /// <returns>List</returns>
        public List<Port> getLeftCheckedPort()
        {
            List<Port> list = new List<Port>();

            if (TableLeftMain.Rows.Count > 0)
            {
                //// 父级行
                foreach (Row row in TableLeftMain.Rows)
                {
                    this.getLeftChildCheckedPort(list, row);
                }
            }

            return list;
        }

        /// <summary>
        /// 添加被选中子行到列表中
        /// </summary>
        /// <param name="list">list</param>
        /// <param name="row">row</param>
        private void getLeftChildCheckedPort(List<Port> list, Row row)
        {
            if (row.SubRows.Count > 0)
            {
                if (row.Checked)
                {
                    list.Add(row.Tag as Port);
                }

                foreach (Row subRow in row.SubRows)
                {
                    this.getLeftChildCheckedPort(list, subRow);
                }
            }
            else
            {
                if (row.Checked)
                {
                    list.Add(row.Tag as Port);
                }
            }
        }


        /// <summary>
        /// 获取多项选择项的方法，此方法只适用于Checkbox的多选形式
        /// </summary>
        /// <returns>pojo类集合</returns>
        private List<ErBbInfo> yssGetSelItemList()
        {
            List<ErBbInfo> list = new List<ErBbInfo>();

            // 使用循环获取curView中选中项目，并添加到数组中
            // 当启用checkbox功能时，获取选中行的处理
            if (bShowRowCheckBoxColumn)
            {
                foreach (Yss.KTable.Models.Row row in tbMain.CheckedRows)
                {
                    // add by yh 2011.03.13 增加在获取list界面选中行时，去掉分组行数据的判断
                    if (row.IsGroup != true)
                    {
                        if (row.Tag is ErBbInfo)
                        {
                            list.Add((ErBbInfo)row.Tag);
                        }
                    }
                }
            }
            else
            {  // 当未启用checkBox功能时，获取选中行的处理

                foreach (Yss.KTable.Models.Row row in tbMain.SelectedRows)
                {
                    if (row.IsGroup != true)
                    {
                        if (row.Tag is ErBbInfo)
                        {
                            list.Add((ErBbInfo)row.Tag);
                        }
                    }
                }
            }

            return list;
        }

        /// <summary>
        /// 刷新按钮点击后触发事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ELEC_BBINFO_L_YssOnAfterRefreshClick(object sender, EventArgs e)
        {
            setButtonIsEnable();
        }

        /// <summary>
        /// 重写父类组合代码属性名称
        /// </summary>
        /// <param name="sender">11</param>
        /// <param name="e">11</param>
        private void cboShowData_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            e.IsCancel = true;
            if (this.cboShowData.Items == null || this.cboShowData.Items.Count == 0)
            {
                cboShowData.Parameter = "C_DV_NAME";
                cboShowData.DisplayName = "C_DV_NAME";
                cboShowData.DisplayValue = "C_DV_CODE";
                cboShowData.YssAssociaType = AssociaType.NULL;
                //// 构造词汇对象
                Vocabulary vocabulary = new Vocabulary();
                vocabulary.C_DV_CODE = "ALL_DATA";
                vocabulary.C_DV_NAME = "所有数据";
                KTableEntity tbEntity = new KTableEntity(vocabulary);
                cboShowData.Items.Add(tbEntity);
                ////STORY57791【鹏华基金】电子对账管理优化需求 数据显示增加“最后一次接收”，取最后一次收到托管行反馈的数据。将“最后一次”改成最后一次发送”
                vocabulary = new Vocabulary();
                vocabulary.C_DV_CODE = "LAST_DATA_GENE";
                vocabulary.C_DV_NAME = "最后一次生成";
                tbEntity = new KTableEntity(vocabulary);
                cboShowData.Items.Add(tbEntity);

                vocabulary = new Vocabulary();
                vocabulary.C_DV_CODE = "LAST_DATA_RECE";
                vocabulary.C_DV_NAME = "最后一次接收";
                tbEntity = new KTableEntity(vocabulary);
                cboShowData.Items.Add(tbEntity);

                cboShowData.Value = "LAST_DATA_GENE";
            }
        }

        /// <summary>
        /// 净值确认事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnEconfirm_Click(object sender, EventArgs e)
        {
            ////HashSet<string> set = new HashSet<string>();
            try
            {
                string executeType = "";
                string type = "";
                if (sender != null && sender is ButtonItem)
                {
                    ButtonItem tempButton = sender as ButtonItem;
                    ////edit by shijian 2018-06-25 STORY #53132 【嘉实基金】估值4.5系统【高】在估值系统“净值确认管理”项目下，增加估值表的确认功能
                    if (tempButton.Name.Equals("btnLock"))
                    {
                        executeType = "LOCK";
                        type = "净值锁定";
                    }
                    if (tempButton.Name.Equals("btnJzConfirm"))
                    {
                        executeType = "ECONFIRM";
                        type = "净值确认";
                    }
                }
                ////String portCodes = "";
                List<ErBbInfo> dataList = null;
                List<ErBbInfo> eConfirmDataList = new List<ErBbInfo>(); ;
                dataList = yssGetSelItemList();
                List<ErBbInfo> dzbyzInfo = new List<ErBbInfo>();
                bool isLockDzbyz = true;
                if (dataList == null || dataList.Count == 0)
                {
                    Yss.KMessage.MessageDialog messS = new Yss.KMessage.MessageDialog();
                    messS.Show("请勾选数据！", "警告", MessageBoxButtons.OK);
                    return;
                }

                ////btnEconfirm.Enabled = false;
                ////生成唯一识别号
                String execOperCode = Convert.ToString(DateTime.Now.ToFileTime());

                ////查询公共参数
                IErDspManagerService erDspManagerService = BusiOperServiceFactory.createService<IErDspManagerService>();
                Dictionary<string, string> paraDict = new Dictionary<string, string>();
                paraDict.Add("dataClass", "ErDspManager");
                paraDict.Add("ARRAY_C_PORT_CODE", " ");
                paraDict.Add("ARRAY_PORT_CODE", " ");
                paraDict.Add("ARRAY_C_DSP_CODE", "DZ_BB_DZDZ_CONFIRMTIP");
                QueryRes res = erDspManagerService.queryByCondition(paraDict);
                bool confirmTip = true;
                if (null != res && res.DataList != null)
                {
                    List<BasePojo> erDspManagerList = res.DataList;
                    List<string> codeList = new List<string>();
                    foreach (BasePojo erDspManagerPojo in erDspManagerList)
                    {
                        ErDspManager erDspManager = erDspManagerPojo as ErDspManager;
                        if ("0".Equals(erDspManager.C_DV_PARAMS_VALUE) && erDspManager.AuditState == 1)
                        {
                            confirmTip = false;
                        }
                    }
                }

                if (confirmTip)
                {
                    if (dataList.FindAll(t => t.C_STATE != "ER_ACCECPED").Count > 0 &&
                    YssMessageBox.ShowQuestion("数据中包含未对账一致的数据，对账不一致的数据是否" + type + "？", "提示") == DialogResult.No)
                    {
                        isLockDzbyz = false;
                    }
                }

                //循环遍历对账不一致对象
                foreach (ErBbInfo info in dataList)
                {
                    if (info.C_STATE != "ER_ACCECPED" && !isLockDzbyz)
                    {
                        continue;
                    }

                    ////BUG #379186 【招商基金】【V1.300.7.0.20210331.0713】电子对账界面确认净值报错
                    if (!"1011".Equals(info.C_FILE_TYPE))
                    {
                        continue;
                    }

                    /*if (!"已锁定".Equals(info.C_LOCK_EXECUTE) && "ECONFIRM".Equals(executeType))
                    {
                        Yss.CommonLib.ShowMessage("有未锁定的数据!请先锁定该数据，再进行净值确认");
                        return;
                    }*/

                    if (info.C_ERR_INFO.Contains("未审核"))
                    {
                        Yss.KMessage.MessageDialog messS = new Yss.KMessage.MessageDialog();
                        messS.Show("所选择的组合手工核对包含未审核数据，请先审核！", "警告", MessageBoxButtons.OK);
                        return;
                    }

                    ////电子对账此处执行净值确认都视为未锁定的情况来处理
                    ////拼装净值锁定实体
                    ////EConfirm econfirm = new EConfirm();
                    ////econfirm.Id = info.C_CONFIRM_ID;
                    ////econfirm.C_PORT_CODE = info.C_PORT_CODE;
                    ////econfirm.D_BIZ_DATE = info.D_DATE;
                    //////STORY58759嘉实基金-电子对账-电子对账管理界面净值确认按钮修改
                    ////econfirm.C_EXECUTE = "LOCK";
                    ////econfirm.C_BIZ_CLS = "eConfirm";
                    ////econfirm.C_BIZ_ITEM = "eConfirm";
                    ////econfirm.Modifier = ClsContext.currentUser.C_USER_CODE;

                    ////eConfirmDataList = eConfirmDataList ?? new List<BasePojo>();
                    ////eConfirmDataList.Add(econfirm);
                    ////if (!set.Contains(info.C_PORT_CODE + "_" + info.D_DATE))//净值是组合维度
                    ////{
                        ////set.Add(info.C_PORT_CODE + "_" + info.D_DATE);
                        info.C_CONFIRM_EXECUTE = executeType;
                        eConfirmDataList.Add(info);
                    ////}
                }



                if (eConfirmDataList != null && eConfirmDataList.Count > 0)
                {
                    ////STORY #65389电子对账前台与估值核算解耦
                    ////IEConfirmService eConfirmSvc = BusiOperServiceFactory.createService<IEConfirmService>();
                    ////liuxiang 2015-9-15 STORY #21737 估值表已锁定功能优化
                    string isCheckExe = Convert.ToBoolean(IniFileOperator.ReadIniData("NAVCHECKITEM", "CHECK_EXE", "False", System.Windows.Forms.Application.StartupPath + "/config/app.ini")) ? "1" : "0";
                    ////String res = eConfirmSvc.updateById(eConfirmDataList, execOperCode, isCheckExe, "LOCK");
                    string econfirmLog = iBbInfoService.lockEconfirm(eConfirmDataList, execOperCode, isCheckExe, executeType);
                    if (!string.IsNullOrEmpty(econfirmLog))
                    {
                        new MessageDialog().Show("净值表确认失败！", "失败信息", MessageBoxButtons.OK, MessageBoxIcon.Error, econfirmLog);
                    }
                    ////刷新界面数据
                    this.btnSearch_Click(null, null);
                }
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfos(ex);
            }
        }

        /// <summary>
        /// STORY #104480 净值确认管理优化整合需求对电子对账管理的影响评估优化
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnJzAudit_Click(object sender, EventArgs e)
        {
            List<ErBbInfo> dataList  = yssGetSelItemList();
            if (dataList == null || dataList.Count == 0)
            {
                Yss.KMessage.MessageDialog messS = new Yss.KMessage.MessageDialog();
                messS.Show("请勾选数据！", "警告", MessageBoxButtons.OK);
                return;
            }

            //循环遍历对账不一致对象
            List<ErBbInfo> bbInfoList = new List<ErBbInfo>();
            List<ErBbInfo> bbInfoUnConfirmList = new List<ErBbInfo>();
            foreach (ErBbInfo info in dataList)
            {
                ////BUG #379186 【招商基金】【V1.300.7.0.20210331.0713】电子对账界面确认净值报错
                if (!"1011".Equals(info.C_FILE_TYPE))
                {
                    continue;
                }

                if ("锁定".Equals(info.C_CONFIRM_EXECUTE) || "LOCK".Equals(info.C_CONFIRM_EXECUTE))
                {
                    bbInfoList.Add(info);
                }
                else 
                {
                    bbInfoUnConfirmList.Add(info);
                }
            }

            if (null != bbInfoUnConfirmList && bbInfoUnConfirmList.Count > 0)
            {
                MessageDialog msg = new MessageDialog();
                msg.Show("选中数据存在未净值确认的数据，已过滤未净值确认数据！", "提示", MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }

            if (null != bbInfoList && bbInfoList.Count > 0)
            {
                string execOperCode = Convert.ToString(DateTime.Now.ToFileTime());
                string isCheckExe = Convert.ToBoolean(IniFileOperator.ReadIniData("NAVCHECKITEM", "CHECK_EXE", "False", System.Windows.Forms.Application.StartupPath + "/config/app.ini")) ? "1" : "0";
                string result = iBbInfoService.lockEconfirm(bbInfoList, execOperCode, isCheckExe, "AUDIT");
                if (!string.IsNullOrEmpty(result) && result.Length > 1)
                {
                    MessageDialog msg = new MessageDialog();
                    msg.Show("以下组合因为检查项审核不通过：" + result, "提示", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                }
            }

            ////刷新界面数据
            this.btnSearch_Click(null, null);
        }

        /// <summary>
        /// 增加导出估值报表按钮。
        /// </summary>
        private void AddAstStatExportButton()
        {
            if (this.DesignMode == false)
            {
                ButtonItem btnBatchExport = new ButtonItem("btnBatchExport", "导出估值报表");
                btnBatchExport.Click += new EventHandler(this.btnAstStatExport_Click);
                this.btnBar.AddSubButtonItem(ClsButtonName.ToolExport, btnBatchExport, 2);
            }
        }

        /// <summary>
        /// 导出估值报表按钮，按钮事件。
        /// </summary>
        /// <param name="sender">ButtonItem按钮</param>
        /// <param name="e">事件参数</param>
        private void btnAstStatExport_Click(object sender, EventArgs e)
        {
            if (this.tbMain.CheckedRows.Count > 0)
            {
                ////先获取A区的组合集
                Dictionary<string, Port> leftPorts = new Dictionary<string, Port>();
                Yss.KTable.Collections.RowCollection rows = this.TableLeftMain.GetAllRows(this.TableLeftMain.Rows, true);
                foreach (Row tempRow in rows)
                {
                    Port port = tempRow.Tag as Port;
                    leftPorts.Add(port.C_PORT_CODE, port);
                }

                ////再根据B区勾选的数据，到A区采集组合数据并导出
                SysFun fun = ClsContext.sysMenuFunHash["FrAststat"] as SysFun;
                ////STORY #65389电子对账前台与估值核算解耦
                object astStatExport = ReflectBase.YssCreateInstance("YssDayf.Support.dll", "YssDayf.Fun.AstStatExport", new SysFun[] { fun });
                //object data = service.GetType().GetMethod("queryProcessListBySysFunAndOperType").Invoke(service, new object[] { funCode, operType });
                ////AstStatExport astStatExport = new AstStatExport(fun);
                List<string> portKey = new List<string>();
                int cellIndex = this.tbMain.Columns["C_FILE_TYPE"].Index;
                bool hasReport = false;
                foreach (Row tempRow in this.tbMain.CheckedRows)
                {
                    ErBbInfo pojo = tempRow.Tag as ErBbInfo;
                    if (pojo == null)
                    {
                        continue;
                    }

                    if (tempRow.Cells[cellIndex].Text == "估值表")
                    {
                        ////注意排查相同组合重复日期的数据
                        if (leftPorts.ContainsKey(pojo.C_PORT_CODE) && portKey.Contains(pojo.C_PORT_CODE + pojo.D_DATE) == false)
                        {
                            hasReport = true;
                            portKey.Add(pojo.C_PORT_CODE + pojo.D_DATE);
                            Port port = leftPorts[pojo.C_PORT_CODE];
                            ////STORY #65389电子对账前台与估值核算解耦
                            ////astStatExport.Export(port, Convert.ToDateTime(pojo.D_DATE));
                            ////有重载方法，必须制定参数类型
                            MethodInfo loMethodInfo = astStatExport.GetType().GetMethod("Export", new Type[] { typeof(Port), typeof(DateTime) });
                            loMethodInfo.Invoke(astStatExport, new object[] { port, Convert.ToDateTime(pojo.D_DATE) });
                        }
                    }
                }

                if (hasReport == false)
                {
                    Yss.CommonLib.ShowMessage("请至少勾选一条估值表数据……");
                }
            }
            else
            {
                Yss.CommonLib.ShowMessage("请至少勾选一条估值表数据……");
            }
        }


        /// <summary>
        /// 重写ResetTableMainExportSetting，用于重置导出文件名。
        /// </summary>
        /// <param name="table">待导出数据的Table</param>
        /// <returns>返回导出配置信息</returns>
        protected override Yss.FileProcessor.ExportSetting ResetTableMainExportSetting(Table table)
        {
            /////添加时间防止，重复导出过多提示 BUG 114827 weijj 20150703
            ExportSetting ss = base.ResetTableMainExportSetting(table);
            ss.ExportName += "_" + DateTime.Now.ToString();
            table.ExportName += ss.ExportName;
            ////STORY #47356 需求单-在估值系统增加“协会代码”参数方便系统导出估值表名称与公示信息一致 hp 20180309
            table.ExportSetting.ExportName = table.ExportName;
            return ss;
        }

        /// <summary>
        ///   STORY #50374 电子对账功能优化 (#5 #4 #3 #2 #1 )
        ///   
        /// 设按钮状态是否可用
        /// </summary>
        private void setButtonIsEnable()
        {
            if (this.tabCtrlDataMain.SelectedTab == this.tabPageDefault)
            {
                this.btnBar.setButtonEnabled(ClsButtonName.BtnGernerate);
                this.btnBar.setButtonDisabled(ClsButtonName.BtnCopy);
                this.btnBar.setButtonDisabled("btnAccept");
                this.btnBar.setButtonDisabled("btnDbCheck");
                this.btnBar.setButtonDisabled("btnUnAccept");
                ////this.btnEconfirm.Enabled = false;
                this.btnBar.setButtonEnabled("btnNoER");
                this.btnBar.setButtonDisabled("btnUnNoER");
                this.btnBar.setButtonDisabled("btnBatchMxData");
                this.btnBar.setButtonDisabled("btnJzConfirm");
                this.btnBar.setButtonDisabled("btnLock");
                this.btnBar.setButtonDisabled("btnJzAudit");
            }
            else if (this.tabCtrlDataMain.SelectedTab == this.tabPageYSC)
            {
                this.btnBar.setButtonDisabled(ClsButtonName.BtnGernerate);
                this.btnBar.setButtonEnabled(ClsButtonName.BtnCopy);
                this.btnBar.setButtonDisabled("btnAccept");
                this.btnBar.setButtonDisabled("btnDbCheck");
                this.btnBar.setButtonDisabled("btnUnAccept");
                ////this.btnEconfirm.Enabled = false;
                this.btnBar.setButtonDisabled("btnNoER");
                this.btnBar.setButtonDisabled("btnUnNoER");
                this.btnBar.setButtonDisabled("btnBatchMxData");
                this.btnBar.setButtonDisabled("btnJzConfirm");
                this.btnBar.setButtonDisabled("btnLock");
                this.btnBar.setButtonDisabled("btnJzAudit");
            }
            else if (this.tabCtrlDataMain.SelectedTab == this.tabPageFSZ)
            {
                this.btnBar.setButtonDisabled(ClsButtonName.BtnGernerate);
                this.btnBar.setButtonEnabled(ClsButtonName.BtnCopy);
                this.btnBar.setButtonDisabled("btnAccept");
                this.btnBar.setButtonDisabled("btnDbCheck");
                this.btnBar.setButtonDisabled("btnUnAccept");
                ////this.btnEconfirm.Enabled = false;
                this.btnBar.setButtonDisabled("btnNoER");
                this.btnBar.setButtonDisabled("btnUnNoER");
                this.btnBar.setButtonDisabled("btnBatchMxData");
                this.btnBar.setButtonDisabled("btnJzConfirm");
                this.btnBar.setButtonDisabled("btnLock");
                this.btnBar.setButtonDisabled("btnJzAudit");
            }
            else if (this.tabCtrlDataMain.SelectedTab == this.tabPageFSS)
            {
                this.btnBar.setButtonDisabled(ClsButtonName.BtnGernerate);
                this.btnBar.setButtonDisabled(ClsButtonName.BtnCopy);
                this.btnBar.setButtonDisabled("btnAccept");
                this.btnBar.setButtonDisabled("btnUnAccept");
                this.btnBar.setButtonDisabled("btnDbCheck");
                ////this.btnEconfirm.Enabled = false;
                this.btnBar.setButtonDisabled("btnNoER");
                this.btnBar.setButtonDisabled("btnUnNoER");
                this.btnBar.setButtonDisabled("btnBatchMxData");
                this.btnBar.setButtonDisabled("btnJzConfirm");
                this.btnBar.setButtonDisabled("btnLock");
                this.btnBar.setButtonDisabled("btnJzAudit");

            }
            else if (this.tabCtrlDataMain.SelectedTab == this.tabPageFKF)
            {
                this.btnBar.setButtonDisabled(ClsButtonName.BtnGernerate);
                this.btnBar.setButtonDisabled(ClsButtonName.BtnCopy);
                this.btnBar.setButtonEnabled("btnBatchMxData");
                this.btnBar.setButtonEnabled("btnAccept");
                this.btnBar.setButtonEnabled("btnDbCheck");
                this.btnBar.setButtonEnabled("btnUnAccept");
                this.btnBar.setButtonEnabled("btnJzConfirm");
                this.btnBar.setButtonEnabled("btnLock");
                ////this.btnEconfirm.Enabled = true; ////BUG221016【招商基金】电子对账管理反馈结果界面的净值确认无法使用
                this.btnBar.setButtonDisabled("btnNoER");
                this.btnBar.setButtonDisabled("btnUnNoER");
                this.btnBar.setButtonEnabled("btnJzAudit");
            }
            else if (this.tabCtrlDataMain.SelectedTab == this.tabPageFKS)
            {
                this.btnBar.setButtonDisabled(ClsButtonName.BtnGernerate);
                this.btnBar.setButtonDisabled(ClsButtonName.BtnCopy);
                this.btnBar.setButtonDisabled("btnAccept");
                this.btnBar.setButtonDisabled("btnDbCheck");
                this.btnBar.setButtonDisabled("btnUnAccept");
                this.btnBar.setButtonEnabled("btnJzConfirm");
                this.btnBar.setButtonEnabled("btnLock");
                ////this.btnEconfirm.Enabled = true;
                this.btnBar.setButtonDisabled("btnNoER");
                this.btnBar.setButtonDisabled("btnUnNoER");
                this.btnBar.setButtonDisabled("btnBatchMxData");
                this.btnBar.setButtonEnabled("btnJzAudit");
            }
            else if (this.tabCtrlDataMain.SelectedTab == this.tabPageZL)
            {
                this.btnBar.setButtonEnabled(ClsButtonName.BtnGernerate);
                this.btnBar.setButtonEnabled(ClsButtonName.BtnCopy);
                this.btnBar.setButtonEnabled("btnAccept");
                this.btnBar.setButtonEnabled("btnDbCheck");
                this.btnBar.setButtonEnabled("btnUnAccept");
                this.btnBar.setButtonEnabled("btnJzConfirm");
                this.btnBar.setButtonEnabled("btnJzConfirm");
                this.btnBar.setButtonEnabled("btnLock");
                ////this.btnEconfirm.Enabled = true;
                this.btnBar.setButtonEnabled("btnNoER");
                this.btnBar.setButtonEnabled("btnUnNoER");
                this.btnBar.setButtonDisabled("btnBatchMxData");
                this.btnBar.setButtonEnabled("btnJzAudit");
            }
            else if (this.tabCtrlDataMain.SelectedTab == this.tabPageBDZ)
            {
                this.btnBar.setButtonDisabled(ClsButtonName.BtnGernerate);
                this.btnBar.setButtonDisabled(ClsButtonName.BtnCopy);
                ////STORY54447反馈不一致结果人工对账一致处理需填写原因与说明信息
                ////其他对账新增对账一致功能
                ////this.btnBar.setButtonDisabled("btnAccept");
                this.btnBar.setButtonEnabled("btnAccept");
                this.btnBar.setButtonEnabled("btnDbCheck");
                this.btnBar.setButtonEnabled("btnUnAccept");
                ////this.btnEconfirm.Enabled = false;
                this.btnBar.setButtonDisabled("btnNoER");
                this.btnBar.setButtonEnabled("btnUnNoER");
                this.btnBar.setButtonDisabled("btnBatchMxData");
                this.btnBar.setButtonDisabled("btnJzConfirm");
                this.btnBar.setButtonDisabled("btnLock");
                this.btnBar.setButtonEnabled("btnJzAudit");
            }


        }

        /// <summary>
        /// 行点击
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbMain_RowClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            base.tbMain_RowClicked(sender, e);
            ////单击事件处理完重新处理各个按钮的可以状态
            setButtonIsEnable();
        }


        /// <summary>
        /// 对账一致界面
        /// 当提示信息为“手工一致”
        /// 可修改“说明”信息
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected void tbMain_CellMouseClick(object sender, Yss.KTable.Events.CellEventArgs e)
        {
            ////在对账一致界面才可以触发
            if (this.tabCtrlDataMain.SelectedTab != this.tabPageFKF && this.tabCtrlDataMain.SelectedTab != this.tabPageZL)
            {
                return;
            }
            ////当提示信息为“手工一致”才可以触发
            Cell cell = e.Cell;
            if (!(cell.Tag is string))
            {
                return;
            }

            string flag = (string)cell.Tag;
            if (!ElecConstant.CELL_HAVE_EVENT.Equals(flag))
            {
                return;
            }

            Row row = e.Row;
            if (row.IsGroup != true)
            {
                if (row.Tag is ErBbInfo)
                {
                    List<ErBbInfo> dataList = new List<ErBbInfo>();
                    dataList.Add((ErBbInfo)row.Tag);
                    Frm_ELEC_HANDACCEPT_S frm_Rgyy_S = new Frm_ELEC_HANDACCEPT_S(ElecConstant.FKS_FLAG, dataList);

                    this.status = ClsEnums.StatusSetting.YssBrow;
                    frm_Rgyy_S.yssShowForm(this);
                }
            }
        }

        /// <summary>
        /// load data
        /// </summary>
        /// <param name="res">res</param>
        protected override void loadListContentMVC(QueryRes res)
        {
            base.loadListContentMVC(res);

            if (this.tabCtrlDataMain.SelectedTab != this.tabPageFKF && this.tabCtrlDataMain.SelectedTab != this.tabPageZL)
            {
                return;
            }

            RowCollection rows = this.tbMain.Rows;
            if (rows.Count == 0)
            {
                return;
            }

            for (int i = 0; i < rows.Count; i++)
            {
                Row row = rows[i];
                int nIndex = this.tbMain.Columns["C_ERR_INFO"].Index;
                if (row.Cells.Count < nIndex)
                {
                    continue;
                }

                Cell xCell = row.Cells[nIndex];
                if (string.IsNullOrEmpty(xCell.Text) || !xCell.Text.Contains(ElecConstant.SGDZYZ))
                {
                    continue;
                }
                ////tsCell.ForeColor = Color.Blue;
                int nIndexResult = this.tbMain.Columns["C_DV_RESULT"].Index;
                if (row.Cells.Count < nIndexResult)
                {
                    continue;
                }

                Cell cell1 = row.Cells[nIndexResult];
                cell1.ForeColor = Color.Blue;
                ////标记该单元格有事件
                cell1.Tag = ElecConstant.CELL_HAVE_EVENT;
            }
        }


        /// <summary>
        /// 搜索
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        public override void btnSearch_Click(object sender, EventArgs e)
        {
            base.btnSearch_Click(sender, e);
            setColoumDateType();
            IniFileOperator.WriteIniData("ELEC", "filterDzType", this.cboDzType.Value, System.Windows.Forms.Application.StartupPath + @"\config\app.ini");
            IniFileOperator.WriteIniData("ELEC", "DataShowValue", this.cboShowData.Value, System.Windows.Forms.Application.StartupPath + @"\config\app.ini");
        }

        /// <summary>
        /// BUG #230669 【招商基金】电子对账反馈结果提示信息无法排序    cls 2018-11-24
        /// 界面找到提示信息的列，将其dataType 改为数值型
        /// </summary>
        private void setColoumDateType()
        {
            ColumnCollection colums = this.tbMain.Columns;
            foreach (Column col in colums)
            {
                //提示信息
                if ("C_ERR_INFO".Equals(col.DataPropertyName))
                {
                    col.DataType = ColumnDataType.Int;
                }

            }
        }


        /// <summary>
        ///  BUG233219 华泰证券电子对账管理下方子界面会显示，导致界面太小
        ///  下半区是否显示
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void pnlDetails_VisibleChanged(object sender, EventArgs e)
        {
            //// BUG233219 华泰证券电子对账管理下方子界面会显示，导致界面太小
            //// 对账详细信息是否显示
            if (this.tabCtrlDataMain.SelectedTab == this.tabPageDefault || this.tabCtrlDataMain.SelectedTab == this.tabPageZL || this.tabCtrlDataMain.SelectedTab == this.tabPageBDZ)
            {
            }
            else
            {
                if (this.pnlDetails.Visible)
                {
                    cShowSplitDetail = "True";
                    IniFileOperator.WriteIniData("ELEC", "ShowSplitDetail", "True", System.Windows.Forms.Application.StartupPath + @"\config\app.ini");
                }
                else
                {
                    cShowSplitDetail = "False";
                    IniFileOperator.WriteIniData("ELEC", "ShowSplitDetail", "False", System.Windows.Forms.Application.StartupPath + @"\config\app.ini");

                }
            }
        }

        /// <summary>
        /// STORY #67173【加急】【鹏华基金】为方便自动化流程管理，增加电子对账结果处理界面
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void UnGenecheckBox_CheckedChanged(object sender, EventArgs e)
        {
            if (this.UnGenecheckBox.Checked)
            {
                this.cShowUnGene = "True";
                IniFileOperator.WriteIniData("ELEC", "ShowUnGeneData", "True", System.Windows.Forms.Application.StartupPath + @"\config\app.ini");
            }
            else
            {
                this.cShowUnGene = "False";
                IniFileOperator.WriteIniData("ELEC", "ShowUnGeneData", "False", System.Windows.Forms.Application.StartupPath + @"\config\app.ini");
            }
        }

        /// <summary>
        /// STORY #75255 【鹏华基金】产品估值指标面板对账指标需要做到穿透
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected override void FrmBase_Load(object sender, EventArgs e)
        {
            base.FrmBase_Load(sender, e);
            //STORY #102768 【东证资管】电子对账当前任务节点显示电子对账结果，并且打开后显示电子对账详细信息 
            if (this.workflowDynamicFormProps.ContainsKey("KJDP_DZDZ_STATUS"))
            {
                this.cboStatus.Value = this.workflowDynamicFormProps["KJDP_DZDZ_STATUS"];
            }

            ////界面穿透信息
            if (this.workflowDynamicFormProps.ContainsKey("D_BEGIN") && this.workflowDynamicFormProps.ContainsKey("ARRAY_C_PORT_CODE"))
            {
                ////修改界面对账日期
                string dtpDate = this.workflowDynamicFormProps["D_BEGIN"];
                this.dtpDzDate.setDateTime(DateTime.Parse(dtpDate), DateTime.Parse(dtpDate));
                ////修改界面勾选组合
                this.TableLeftMain.CancelCheckedRows();
                Yss.CommonLib.RedisplayTableCheckedRows(this.TableLeftMain.Rows, false);
                string portCodes = this.workflowDynamicFormProps["ARRAY_C_PORT_CODE"];
                Yss.KTable.Collections.RowCollection rows = this.TableLeftMain.GetAllRows(this.TableLeftMain.Rows, true);
                foreach (Row row in rows)
                {
                    if (portCodes.Contains("," + ((Port)row.Tag).C_PORT_CODE + ","))
                    {
                        row.Checked = true;
                    }
                }
                Yss.CommonLib.RedisplayTableCheckedRows(this.TableLeftMain.Rows, true);
                ////默认展示反馈结果Tab页
                this.tabCtrlDataMain.SelectedTab = this.tabPageFKF;
                ////查询最后一次生成数据
                this.cboShowData.Value = "LAST_DATA_GENE";
                Dictionary<string, string> paraDict = OnGetParaAssemble(new Dictionary<string, string>());
                this.btnSearch_Click(paraDict, null);
            }
        }
    }
}
