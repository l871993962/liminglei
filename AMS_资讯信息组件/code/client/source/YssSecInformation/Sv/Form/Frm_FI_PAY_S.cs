using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;

using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;
using System;
using System.Collections;




using FAST.Core.BaseControl;
using System.Windows.Forms;


using System.Collections.Generic;
using FAST.Common.Service.Services;
using Yss.KTable.Enums;
using YssSecInformation.Support.Sv.Service;
using YssSecInformation.Support.Pojo.Sec;
using YssInformation.Support.Bi.Market.Pojo;
using YssInformation.Support.Fun;
using FAST.Core.CRUD.Interface;
using YssSecInformation.Support.Sv.Pojo;


namespace YssSecInformation.Sv.Form
{
    /// <summary>
    /// 功能简介：债券付息信息设置，负责债券付息信息的增删改查等功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.31
    ///
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：lyh
    /// 修改日期：2011.1.6
    /// 修改简介：实现具体方法
    /// ---修改记录---
    /// 当前版本：v4.5.03
    /// 修改人：wuwenlan 20110223
    /// 修改描述：
    ///          pojo类的属性发生变化
    ///          对新增，修改，控件赋初值的方法进行修改
    ///          去掉审核，反审核，修改，删除，添加成功的提示
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：返回对象错误直接抛出异常
    /// </summary>
    public partial class Frm_FI_PAY_S : FrmBaseSet
    {
        /// <summary>
        /// 根据交易证券获取交易市场
        /// </summary>
        ////private string mktCode = "";

        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private IFiPayService service;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_FI_PAY_S()
        {
            InitializeComponent();
            bUseMVCService = true;
        }

        /// <summary>
        /// 封装界面元素为pojo对象
        /// </summary>
        /// <returns>由界面元素组成的对象</returns>
        public override AuditableParamPojo faceInfoToObjMVC()
        {
            FiPay fi = null;
            try
            {
                checkDateInput();
                fi = new FiPay();
  
                fi.C_SEC_CODE = this.selSecurity.Value;
                fi.D_ADJ = this.dtpResetInterestDate.getBeginDate.ToString("yyyy-MM-dd");
                fi.N_COUP_RATE = decimal.Parse(this.txtCouponRate.Text);
                fi.N_REM_COR = decimal.Parse(this.txtRemainMoney.Text);
                fi.N_REPAY_AMOUNT = decimal.Parse(this.txtReturnAmount.Text) / 100;  // 偿还数量
                fi.C_DV_BOOL_TYPE = this.cboNewRate.Value;
                fi.D_BEGIN = this.dtpCurValueDate.getBeginDateStr;
                fi.D_END = this.dtpCurStopValueDate.getBeginDateStr;
                fi.N_QNHB = decimal.Parse(this.cboIsQnhb.Value);         // 期内还本
                fi.D_QNHB = this.dtpQnhb.getBeginDateStr;
                ////检索hashtable里面是否有这个值 如没有检索到 InputErr
                if (selSecurity.SelectedItem != null)
                {
                    fi.C_MKT_CODE = (selSecurity.SelectedItem.DataEntity as SecBase).C_MKT_CODE;
                }
                ////edit tdf BUG #115588 [紧急]浮动利率债券没有清算出财汇资讯数据 20150715
                if (status == ClsEnums.StatusSetting.YssEdit)
                {
                    FiPay oldPay = this.yssGetBaseSelTypeItemMVC() as FiPay;
                    if (null != oldPay)
                    {
                        //////自动的需要改为自动转手动
                        //// ADD BY LIYANJUN 2016-3-3 BUG #127263 债券历史付息信息更新有误
                        //// 添加来源为库存的数据，修改后为库存转手工
                        if (oldPay.C_DATA_IDF.Equals(ClsBizCons.Z))
                        {
                            fi.C_DATA_IDF = ClsBizCons.Z_H;
                        }
                        else if (oldPay.C_DATA_IDF.Equals(ClsBizCons.S))
                        {
                            fi.C_DATA_IDF = ClsBizCons.S_H;
                        }
                        else
                        {
                            fi.C_DATA_IDF = oldPay.C_DATA_IDF;
                        }
                    }
                    else
                    {
                        fi.C_DATA_IDF = ClsBizCons.H;
                    }
                }
                else if (status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssCopy)
                {
                    fi.C_DATA_IDF = ClsBizCons.H;
                }

            }
            catch (Exception ye)
            {
                YssMessageBox.ShowCommonInfo(ye.Message);
            }

            return fi;
        }

        /// <summary>
        /// 获取list中选中记录，为界面元素赋值，显示数据
        /// </summary>
        /// <param name="pojo">数据对象</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            try
            {
                FiPay fi = (FiPay)this.yssGetBaseSelTypeItemMVC();
                if (null != fi)
                {
                    ////this.selSecurity.Text = fi.C_SEC_CODE;
                    this.selSecurity.Value = fi.C_SEC_CODE; // 证券内码
                   
                    this.txtCouponRate.Text = Convert.ToString(fi.N_COUP_RATE); // 票面利率
                    this.txtRemainMoney.Text = Convert.ToString(fi.N_REM_COR);  // 剩余本金
                    this.txtReturnAmount.Text = Convert.ToString(fi.N_REPAY_AMOUNT * 100);  // 偿还数量
                    if (!string.IsNullOrEmpty(fi.D_ADJ))
                    {
                        this.dtpResetInterestDate.setDateTime(Convert.ToDateTime(fi.D_ADJ)); // 调息日期
                    }

                    if (!string.IsNullOrEmpty(fi.D_BEGIN))
                    {
                        this.dtpCurValueDate.setDateTime(Convert.ToDateTime(fi.D_BEGIN)); // 本次起息日
                    }

                    if (!string.IsNullOrEmpty(fi.D_END))
                    {
                        this.dtpCurStopValueDate.setDateTime(Convert.ToDateTime(fi.D_END)); // 本次截息日
                    }

                    this.cboNewRate.Value = fi.C_DV_BOOL_TYPE;

                    this.cboIsQnhb.Value = Convert.ToString(fi.N_QNHB);  // 期内还本

                    if (!string.IsNullOrEmpty(fi.D_QNHB))
                    {
                        this.dtpQnhb.setDateTime(Convert.ToDateTime(fi.D_QNHB));
                    }
                }
            }
            catch (Exception e)
            {
                ClsBaseException.DiscardException(e);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-110023", _formFun, status));
            }
        }

        /// <summary>
        /// 初始化界面控件
        /// </summary>
        public override void yssInitCtlAttr()
        {
            try
            {
                ////this.dtpResetInterestDate.getBeginDate = DateTime.Now;
                ////this.dtpCurStopValueDate.getBeginDate = DateTime.Now;
                this.cboNewRate.Value = "0"; // 默认不选中新利率
                //// tanwenjie 2011.6.12
                if (this.frmBaseViewList.tbLeftMain.SelectedRow != null && this.frmBaseViewList.tbLeftMain.SelectedRow.SubRows.Count == 0)
                {
                    this.selSecurity.Value = ((MktExtend)this.frmBaseViewList.tbLeftMain.SelectedRow.Tag).C_MKT_NAME + " " + ((MktExtend)this.frmBaseViewList.tbLeftMain.SelectedRow.Tag).C_MKT_CODE;
                    if (this.frmBaseViewList.tbMain.Rows.Count > 0)
                    {
                        for (int i = 0; i < this.frmBaseViewList.tbMain.Rows.Count; i++)
                        {
                            if (this.frmBaseViewList.tbMain.Rows[i].SubRows.Count > 0)
                            {
                                this.dtpCurValueDate.setDateTime(ClsFunction.addDate((Convert.ToDateTime(((FiPay)this.frmBaseViewList.tbMain.Rows[i].SubRows[0].Tag).D_END)), 1, ClsEnums.DateType.Day));
                                ////this.dtpCurValueDate.setDateTime(ClsFunction.addDate((Convert.ToDateTime(((Cls_FI_PAY)this.frmBaseViewList.tbMain.Rows[i].SubRows[0].Tag).D_END)), 1, ClsEnums.DateType.Day));
                            }
                        }
                    }
                    else
                    {
                        this.dtpCurValueDate.setDateTime(DateTime.Now);
                    }

                }
                else if (this.frmBaseViewList.tbLeftMain.SelectedRow == null)
                {
                    this.dtpCurValueDate.setDateTime(DateTime.Now);
                }
            }
            catch (Exception e)
            {
                ClsBaseException.DiscardException(e);
                ////YssMessageBox.ShowDyanInformation("初始化控件出错", e.Message, "错误信息", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-110017", _formFun, status));
            }
        }

        /// <summary>
        /// 验证时间控件输入日期的合法性
        /// </summary>
        public void checkDateInput()
        {
            if (ClsFunction.sub(this.dtpCurValueDate.getBeginDate, this.dtpCurStopValueDate.getBeginDate) > 0)
            {
                //// throw new ClsBaseException("【本期起息日】应小于【本期截息日】");
                ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("001", _formFun, ClsEnums.StatusSetting.YssSave));
                TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                transferErrorMessage.MESSAGESLINK = new List<string>();
                /* 组装提示信息对象ErrorMessage */
                string errorMess = ClsRetInfoDealer.getExtWarns("001", _formFun, ClsEnums.StatusSetting.YssSave);
                transferErrorMessage.MESSAGESLINK.Add(errorMess);
                throw new TransferException(JsonUtil.toJson(transferErrorMessage));
            }

            if (ClsFunction.sub(this.dtpResetInterestDate.getBeginDate, this.dtpCurValueDate.getBeginDate) < 0 ||
                (ClsFunction.sub(this.dtpResetInterestDate.getBeginDate, this.dtpCurStopValueDate.getBeginDate) > 0))
            {
                //// throw new ClsBaseException("【调息日】应大于等于本期起息日并且小于等于本期截息日
                ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("007", _formFun, ClsEnums.StatusSetting.YssSave));
                TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                transferErrorMessage.MESSAGESLINK = new List<string>();
                /* 组装提示信息对象ErrorMessage */
                string errorMess = ClsRetInfoDealer.getExtWarns("007", _formFun, ClsEnums.StatusSetting.YssSave);
                transferErrorMessage.MESSAGESLINK.Add(errorMess);
                throw new TransferException(JsonUtil.toJson(transferErrorMessage));
            }
        }

        /// <summary>
        /// load
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_FI_PAY_S_Load(object sender, EventArgs e)
        {
            Type dataServiceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            service = (IFiPayService)ServiceFactory.createService(dataServiceType);
            dataService = service;
        }

        /// <summary>
        /// 保存之前输入校验
        /// 保存前验证时间空间输入日期的合法性
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_FI_PAY_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        {
            this.checkDateInput();
            ////if (ClsFunction.sub(this.dtpCurValueDate.getBeginDate, this.dtpResetInterestDate.getBeginDate) > 0)
            ////{
            ////    FrmMessageDialog form = new FrmMessageDialog();
            ////    form.Show("【调息日】应大于等于【本期起息日】！", "警告", MessageBoxIcon.Warning, ClsEnums.ButtonType.OKCancel);
            ////    e.IsCancel = true;
            ////}
        }

        /// <summary>
        /// 根据选择证券信息自动带出浏览信息
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void selSecurity_SelectedValueChanged(object sender, EventArgs e)
        {
            try
            {
                // 当选择交易证券时，应取出交易证券中的币种信息和证券品种信息，并将他们赋值到对应的控件当中
                if (((YssSelCombox)sender).SelectedItem == null)
                {
                    return;
                }

                if (this.selSecurity.Value != null)
                {
                    // 当选择交易证券时，应取出交易证券中的币种信息和证券品种信息，并将他们赋值到对应的控件当中
                    SecBase sec = ((YssSelCombox)sender).SelectedItem.DataEntity as SecBase;
                    if (sec != null)
                    {
                        ////(合并代码 STORY24925) modify by dingshalu 20160406 STORY #28335 保险资管业务-非标（债权、信托）支持存款计息模式
                        ////债券品种
                        //// modified by HeLiang.2016-11-03.BUG #144173 【南方资本】【紧急】银行间债券双击债券历史付息和新增债券历史付息时报错
                        ////// 修改逻辑：导入的债券信息有的历史数据中，证券品种为“债券品种”（即C_SEC_VAR_CODE = 'ZQ'），虽然不允许设置这种品种，但是对导入的历史数据也得支持显示
                        //////                    C_SEC_VAR_CODE只有两个字母，故用Substring截取前三位的时候会报错
                        if (sec.C_SEC_VAR_CODE.Substring(0, 2).Equals("ZQ"))
                        {
                            this.cell4.Text = "票面利率：";
                        }

                        if (sec.C_SEC_VAR_CODE.Substring(0, 2).Equals("LC"))
                        {
                            if (sec.C_DV_RTA.Equals("RTA_INC"))
                            {
                                this.cell4.Text = "应计利息：";
                            }

                            if (sec.C_DV_RTA.Equals("RTA_FIX"))
                            {
                                this.cell4.Text = "计息利率：";
                            }

                            if (sec.C_DV_RTA.Equals("RTA_RATE"))
                            {
                                this.cell4.Text = "利  差：";
                            }

                            if (sec.C_DV_RTA.Equals("RTA_RATE_FD") || sec.C_DV_RTA.Equals("RTA_RATE_ZF"))
                            {
                                this.tbMain.Controls.Remove(this.improvedTextBoxLC);
                                this.cell4.Text = "基准利率：";
                            }

                        }

                        this.cboSecCategory.Value = sec.C_SEC_VAR_CODE;  // 证券品种
                        this.cboMarket.Value = sec.C_MKT_CODE; // 交易市场
                        this.cboCury.Value = sec.C_DC_CODE; // 交易币种
                        this.cboPriceMode.Value = sec.C_DV_QUT_MOD; // 报价方式
                        this.cboCountMode.Value = sec.C_DV_AI_MOD; // 计息方式
                        this.cboCountFormula.Value = sec.C_DV_AI_EXPR; // 计息公式
                        this.cboPayFrequency.Value = sec.C_DV_VAR_DUR; // 付息频率
                        this.cboPayFormula.Value = sec.C_DV_PI_MOD; // 付息公式

                        if (!"".Equals(sec.D_AI_BEGIN))
                        {
                            this.dtpCountBeginDate.setDateTime(Convert.ToDateTime(sec.D_AI_BEGIN)); // 起息日
                        }

                        if (!"".Equals(sec.D_AI_END))
                        {
                            this.dtpCountEndDate.setDateTime(Convert.ToDateTime(sec.D_AI_END)); // 截息日 
                        }
                    }
                }
            }
            catch (Exception ye)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-110023", _formFun, status));
                ClsBaseException.DiscardException(ye);
            }
        }

        /// <summary>
        /// CL 20121207 子类覆写初始化方法
        /// </summary>
        public override void initControlStat()
        {
            base.initControlStat();
            //// 任何情况下，控件都是只读
            this.cboSecCategory.YssReadOnly = true;
            this.cboMarket.YssReadOnly = true;
            this.cboCury.YssReadOnly = true;
            this.cboPriceMode.YssReadOnly = true;
            this.cboCountMode.YssReadOnly = true;
            this.cboCountFormula.YssReadOnly = true;
            this.cboPayFrequency.YssReadOnly = true;
            this.cboPayFormula.YssReadOnly = true;
            this.dtpCountBeginDate.Enabled = false;
            this.dtpCountEndDate.Enabled = false;
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
                    this.selSecurity.YssReadOnly = true;
                    this.selSecurity.Value = (frmDetailData.MainDataPojo as SecBase).C_SEC_CODE;
                }
            }
        }

        /// <summary>
        /// 重写保存按钮的点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnSave_Click(object sender, EventArgs e)
        {
            base.btnSave_Click(sender, e);
            //// 判断系统当前是否开启审核机制
            if (this._formFun.N_CHECK == 0)
            {
                if (YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonHint("HNT-000025", _formFun, status)) == DialogResult.Yes)
                {
                    FiPay fi = (FiPay)this.yssGetBaseSelTypeItemMVC();
                    service.singleSecFiPayInit(fi);
                    ////MessageBox.Show("该付息期间每日利息数据已重新生成!", "提示");
                    this.stBarBottom.StatuInfo = "该付息期间每日利息数据已重新生成!";
                }
                else
                {
                    ////MessageBox.Show("请手动生成数据", "提示");
                    this.stBarBottom.StatuInfo = "请手动生成数据!";
                }
            }
        }

        /// <summary>
        /// 重写审核按钮点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnAudit_Click(object sender, EventArgs e)
        {
            base.btnAudit_Click(sender, e);
            if (this.stBarBottom.StatuInfo.Equals("[债券历史付息][审核]操作成功!"))
            {
                if (YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonHint("HNT-000025", _formFun, status)) == DialogResult.Yes)
                {
                    FiPay fi = (FiPay)this.yssGetBaseSelTypeItemMVC();
                    service.singleSecFiPayInit(fi);
                    ////MessageBox.Show("该付息期间每日利息数据已重新生成!", "提示");
                    this.stBarBottom.StatuInfo = "该付息期间每日利息数据已重新生成!";
                }
                else
                {
                    ////MessageBox.Show("请手动生成数据", "提示");
                    this.stBarBottom.StatuInfo = "请手动生成数据!";
                }
            }
        }

        /// <summary>
        /// 是否为期内还本
        /// </summary>
        /// <param name="sender">s</param>
        /// <param name="e">e</param>
        private void cboIsQnhb_SelectedValueChanged(object sender, EventArgs e)
        {
            if (cboIsQnhb.Value.Equals("1"))
            {
                this.cell61.InnerControlView = InnerControlViewStyle.ShowAlways;
                this.cell60.Text = "期内还本日期：";
            }
            else
            {
                this.cell61.InnerControlView = InnerControlViewStyle.HideAlways;
                this.cell60.Text = "";
            }
        }
    }
}
