using System;
using System.Windows.Forms;
using Yss.KMessage;
using FAST.Core.CRUD.Form;
using FAST.Core.Context.Events;
using FAST.Core.BaseControl.Fun;
using FAST.Core.Exceptions;
using YssInformation.Support.Bi.Account.Service;
using YssInformation.Support.Bi.Account.Pojo;
using YssInformation.Support.Fun;
using FAST.Common.Service.Pojo;
using System.Collections.Generic;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Communication.Service;
using Yss.KRichEx.AutoFilter.Model;
using FAST.Core.Util;
using System.Text;
using Yss.KTable.Models;

namespace YssInformation.Bi.Account.Form
{
    /// <summary>
    /// 功能简介：公用账户信息设置界面处理
    /// 创建人：chenyoulong
    /// 创建日期：20121105
    /// 发布版本：v1.0.0.4
    /// </summary>
    public partial class Frm_FUND_ACC_CHECK_S : FrmBaseSet
    {
        /// <summary>
        /// 声明资金账户信息service对象
        /// </summary>
        private IFundAccService fundAccService = null;

        /// <summary>
        /// fundAcc
        /// </summary>
        public FundAcc fundAcc = null;

        /// <summary>
        /// isok
        /// </summary>
        public bool isok = false;

        /// <summary>
        /// frmFundAccS
        /// </summary>
        public Frm_FUND_ACC_S frmFundAccS = null;

        /// <summary>
        /// 窗体是否关闭提醒
        /// </summary>
        private bool winFlag = true;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_FUND_ACC_CHECK_S()
        {
            this.InitializeComponent();
            this.bUseMVCService = true;
        }

        /// <summary>
        /// 资金账户信息设置浏览界面装载事件
        /// 此处用于在窗体装载的时候初始化公用划款账户服务对象（IPubAccService）
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_FUND_ACC_CHECK_S_Load(object sender, EventArgs e)
        {
            this.Text = "产品账户信息确认";
            btnBar.setButtonVisable(ClsButtonName.BtnNew, false);
            btnBar.setButtonVisable(ClsButtonName.BtnEdit, false);
            btnBar.setButtonVisable(ClsButtonName.BtnDelete, false);
            btnBar.setButtonVisable(ClsButtonName.BtnCopy, false);
            btnBar.setButtonVisable(ClsButtonName.BtnAudit, false);
            btnBar.setButtonVisable(ClsButtonName.BtnUnAudit, false);
            btnBar.setButtonVisable(ClsButtonName.BtnRecall, false);
            btnBar.setButtonVisable(ClsButtonName.BtnPrevious, false);
            btnBar.setButtonVisable(ClsButtonName.BtnNext, false);
            btnBar.setButtonVisable(ClsButtonName.BtnSave, true);

            //// BUG #169932 南方基金-产品账户信息确认界面开户名称及开户行不支持全角输入及中文大括号
            this.txtOpenName.ForbiddenSBC = false;
            this.txtOpenAddr.ForbiddenSBC = false;
            this.justShowInfoMVC(this.fundAcc);
            this.changezt();
        }






        /// <summary>
        /// 展示对象到窗体
        /// </summary>
        /// <param name="pojo">pojo</param>
        public void justShowInfoMVC(FundAcc fundAcc)
        {
            try
            {
                this.cboCury.Value = fundAcc.C_DC_CODE;
                this.cboOrgan.Value = fundAcc.C_ORG_CODE;
                this.cboCa.Value = fundAcc.C_CA_CODE;
                this.cboOrgan.Value = fundAcc.C_ORG_CODE;
                ////STORY #35492 南方基金-产品账户信息中大额支付号与关联机构联动
                this.cboOrgan1.Value = fundAcc.C_HOLDER;
                this.dtpBegin.setDateTime(fundAcc.D_BEGIN);
                this.dtpEnd.setDateTime(fundAcc.D_END);

                this.txtAssCode.Text = fundAcc.C_ASS_CODE;
                ////20160219 zhaoguanchao STORY #28868 华泰证券-直销业务清算指令管理
                this.cboAccType.Value = fundAcc.C_ACCOUNT_TYPE;
                //// 增加开户省份赋值
                this.cboProvince.Value = fundAcc.C_PROVINCE;
                //// 增加开户城市赋值
                this.cboCity.Value = fundAcc.C_CITY;
                ////增加中间行赋值
                this.cboOrgan2.Value = fundAcc.C_INTER_ORG_CODE;
                ////BUG #173609 支付产品账户信息：点击新增，维护好信息后，点击保存，提示‘两次输入不一致’
                ////开户地址
                this.txtBankAddr.Text = fundAcc.C_BANK_ADDR;
                ////流水账户
                this.txtRunningAcc.Text = fundAcc.C_RUNNING_ACC;
                ////开户方式
                this.cboOpenModel.Value = fundAcc.C_OPEN_MODE;
                ////虚拟号
                this.txtCNX.Text = fundAcc.C_CNX;
                ////中行机构号
                this.txtBCOrgCode.Text = fundAcc.C_BC_ORG_CODE;
                ////中行联行号
                this.txtBCLinkNO.Text = fundAcc.C_BC_LINK_NO; 
                //// STORY #67960 【交银施罗德】_支付产品账户信息界面新增时，可以维护备注信息
                this.txtDesc.Text = fundAcc.C_DESC;

            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 改变界面控件的状态
        /// </summary>
        /// <param name="set">set</param>
        public void changezt()
        {
            this.txtAssCode.YssReadOnly = true;
            this.cboAccType.YssReadOnly = true;
            this.cboCury.YssReadOnly = true;
            this.cboOrgan.YssReadOnly = true;
            this.cboOrgan1.YssReadOnly = true;
            this.cboOrgan2.YssReadOnly = true;
            this.cboCa.YssReadOnly = true;
            //// bug ：点击保存确认时，set界面仍有一些控件可编辑
            this.cboProvince.YssReadOnly = true;
            this.cboCity.YssReadOnly = true;

            this.txtBankAddr.YssReadOnly = true; ////开户地址
            this.txtRunningAcc.YssReadOnly = true; ////流水账户
            this.cboOpenModel.YssReadOnly = true; ////开户方式
            this.txtCNX.YssReadOnly = true; ////虚拟号
            this.txtBCOrgCode.YssReadOnly = true; ////中行机构号
            this.txtBCLinkNO.YssReadOnly = true; ////中行联行号


            this.dtpBegin.ReadOnly = true;
            this.dtpEnd.ReadOnly = true;

        }

        /// <summary>
        /// 账户代码下拉框加载之前事件，用于处理账户代码加载
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboCa_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            if (null == this.cboCury.Value)
            {
                BaseInfoUtil.setCaMethod(sender, /*this.cboPort.Value*/ "");
            }
            else
            {
                ////edit by weijj 20140615 BUG #94994
                e.IsCancel = true;
                BaseInfoUtil.setCaItem(e, /*this.cboPort.Value*/ "", "");
            }

        }

        /// <summary>
        /// 获取界面布局、排版用的表格（用于自动化测试时，提取界面控件元素）。
        /// </summary>
        /// <returns>返回表格集</returns>
        protected override List<Table> GetLayoutTables()
        {
            List<Table> loTableList = new List<Table>();
            loTableList.Add(this.tbMain);
            return loTableList;
        }


        /// <summary>
        /// BUG #181159 【一体化账户】银行卡信息的省份、城市字段建议更改为下拉框，加载数据可从省份城市表中获取
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void cboProvince_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            e.Cancel = true;
            ////清空掉省份下拉框
            this.cboProvince.Items.Clear();

            QueryRes res = new QueryRes();

            Dictionary<string, string> dictPara = new Dictionary<string, string>();

            dictPara.Add("dataClass", "AreaCity");
            ////2代表省份
            dictPara.Add("C_LEVEL", "2");

            IAreaCityDataService service = ServiceFactory.createService<IAreaCityDataService>();

            res = service.queryByCondition(dictPara);

            foreach (BasePojo pojo in res.DataList)
            {
                this.cboProvince.Items.Add(new KTableEntity(pojo));
            }
        }

        /// <summary>
        /// BUG #181159 【一体化账户】银行卡信息的省份、城市字段建议更改为下拉框，加载数据可从省份城市表中获取
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void cboProvince_SelectedValueChanged(object sender, EventArgs e)
        {
            ////清空本条数据城市
            this.cboCity.Items.Clear();
            this.cboCity.Text = "";
            this.cboCity.Value = "";
        }

        /// <summary>
        /// BUG #181159 【一体化账户】银行卡信息的省份、城市字段建议更改为下拉框，加载数据可从省份城市表中获取
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void cboCity_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            this.cboCity.Items.Clear();

            e.Cancel = true;

            ////如果省份没有选中数据，城市下拉框不加载数据
            if (string.IsNullOrEmpty(this.cboProvince.Value))
            {
                return;
            }

            QueryRes res = new QueryRes();

            Dictionary<string, string> dictPara = new Dictionary<string, string>();

            dictPara.Add("dataClass", "AreaCity");
            ////3代表城市
            dictPara.Add("C_LEVEL", "3");
            dictPara.Add("C_PARENT_CODE", this.cboProvince.Value);

            IAreaCityDataService service = ServiceFactory.createService<IAreaCityDataService>();

            res = service.queryByCondition(dictPara);

            foreach (BasePojo pojo in res.DataList)
            {
                this.cboCity.Items.Add(new KTableEntity(pojo));
            }
        }






        /// STORY #40537 【加急】南方基金-增加划款指令账户信息不匹配时的系统提醒功能
        /// </summary>
        /// <param name="a"></param>
        /// <param name="b"></param>
        /// <returns></returns>
        private bool getInfo()
        {
            if (this.fundAcc.C_OPEN_ACC_NAME.Trim() == this.txtOpenName.Text.Trim()
               && this.fundAcc.C_OPEN_ACC_NO.Trim() == this.txtOpenNo.Text.Trim()
                && this.txtOpenAddr.Text.Trim() == this.fundAcc.C_OPEN_ADDR.Trim() &&
                this.txtPayNum.Text == this.fundAcc.C_PAY_CODE)
            {
                return true;
            }
            else
            {
                return false;
            }

        }

        /// <summary>
        /// 窗口关闭事件
        /// 
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Frm_FUND_ACC_CHECK_S_YssOnFrmClose(object sender, YssFormEventArgs e)
        {
            this.Close();
            this.Dispose();
            if (null != this.frmFundAccS)
            {
                //// 在检查页面关闭时使得frmFundAccS界面的保存按钮显示出来
                this.frmFundAccS.btnBar.setButtonVisable(ClsButtonName.BtnSave, true);
                this.frmFundAccS.changeZt(this.frmFundAccS);
            }
        }

        /// <summary>
        /// 把关闭方法修改放虚方法。在数据转移set窗体没有状态栏，不需要关闭状态栏中的窗体
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void FrmBaseSet_FormClosing(object sender, FormClosingEventArgs e)
        {
            try
            {
                bool iscls = false;
                if (this.winFlag)
                {
                    ////DialogResult result = YssMessageBox.ShowQuestion("是否确定关闭？", "提示");
                    DialogResult result = YssMessageBox.ShowQuestion("是否确定关闭？", "提示", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
                    if (result == DialogResult.None)
                    {
                        this.Close();
                        return;
                    }

                    if (result == DialogResult.Yes)
                    {
                        iscls = true;
                    }
                    else
                    {
                        e.Cancel = true;
                        return;
                    }
                }
                if (iscls)
                {
                    ////关闭Set界面时，先尝试激活主窗体。张绍林-20171121
                    if (Yss.CommonLib.MainForm != null)
                    {
                        Yss.CommonLib.MainForm.Activate();
                    }

                    //// 关闭窗体前，设置List窗体为活动窗体 
                    //// 防止Windows窗口被切换到其他地方了
                    //// add by leeyu 2010-12-17
                    if (frmBaseViewList != null && !frmBaseViewList.IsDisposed)
                    {
                        frmBaseViewList.Activate();
                    }

                    if (null != this.frmFundAccS)
                    {
                        //// 在检查页面关闭时使得frmFundAccS界面的信息确认按钮显示出来
                        this.frmFundAccS.btnBar.setButtonVisable("btnCheckAccInfoConfirm", true);
                        btnBar.setButtonEnabled("btnCheckAccInfoConfirm");
                        this.frmFundAccS.changeZt(this.frmFundAccS);
                    }
                }
                else
                {
                    return;
                }
            }
            catch (System.Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-110022", _formFun, status, ex));
            }
        }

        /// <summary>
        /// 重写保存方法，如果是从综合指令设置界面打开的，
        /// 则不显示审核反审核按钮，保存时数据为已审核，
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnSave_Click(object sender, EventArgs e)
        {
            StringBuilder stringBuilder = new StringBuilder("");
            if (!this.fundAcc.C_OPEN_ACC_NAME.Trim().Equals(this.txtOpenName.Text.Trim()))
            {
                stringBuilder.Append("[开户名称]两次输入不一致！" + "\r\n");
            }

            if (!this.fundAcc.C_OPEN_ACC_NO.Trim().Equals(this.txtOpenNo.Text.Trim()))
            {
                stringBuilder.Append("[开户账号]两次输入不一致！" + "\r\n");
            }

            if (!this.txtOpenAddr.Text.Trim().Equals(this.fundAcc.C_OPEN_ADDR.Trim()))
            {
                stringBuilder.Append("[开户行]两次输入不一致！" + "\r\n");
            }

            if (!this.txtPayNum.Text.Trim().Equals(this.fundAcc.C_PAY_CODE.Trim()))
            {
                stringBuilder.Append("[大额支付号]两次输入不一致！");
            }

            if (string.IsNullOrEmpty(stringBuilder.ToString()))
            {
                this.winFlag = false;
                this.frmFundAccS.btnBar.setButtonVisable("btnCheckAccInfoConfirm", false);
                this.frmFundAccS.saveInfo(sender, e);
                this.Close();
                this.Dispose();
            }
            else
            {
                new MessageDialog().Show(stringBuilder.ToString(), "系统提示", MessageBoxButtons.OK, MessageBoxIcon.Information);
                return;
            }
        }
    }
}
