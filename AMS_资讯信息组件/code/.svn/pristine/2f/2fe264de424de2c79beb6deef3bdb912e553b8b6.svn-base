using System;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Services;
using FAST.Core.BaseControl;
using FAST.Core.Communication.Service;
using FAST.Core.Context;
using FAST.Core.CRUD.Form;
using FAST.Core.Exceptions;
using FAST.Core.Util;
using YssSecInformation.Support.Mp.SecEq.Service;
using YssSecInformation.Support.Mp.SecEq.Pojo;
using YssSecInformation.Support.Pojo.Sec;
using YssInformation.Support.Fun;
using FAST.Core.CRUD.Interface;
////using YssSecInformation.Support.Fun;

namespace YssSecInformation.Mp.SecEq.Form
{   
    /// <summary>
    /// chenbo
    /// 2017-06-24
    /// #42948 资讯信息管理组件化拆分
    /// ------------------------------- 
    /// 功能简介：证券代码转换SET，负责证券代码转换的增删改查等功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： gh
    /// 创建日期： 20161024
    /// </summary>
    public partial class Frm_SEC_TRANSFER_S : FrmBaseSet
    {
        /// <summary>
        /// 证券代码转换数据服务对象
        /// </summary>
        private ISecTransferService secTransferService = null;

        /// <summary>
        /// set所从属的list窗体
        /// </summary>
        private Frm_SEC_TRANSFER_L myList = null;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_SEC_TRANSFER_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 初始化set窗体
        /// </summary>
        /// <param name="frm">list</param>
        public override void initForm(FrmBaseList frm)
        {
            base.initForm(frm);
            this.bUseMVCService = true;
            this.myList = frm as Frm_SEC_TRANSFER_L;
            this.cboChanType.YssReadOnly = true;
            this.cboExchange.YssReadOnly = true;

            this.isShowJyqd(this.myList.c_PR_TYPE.Equals("P"));
        }

        /// <summary>
        /// 是否显示交易渠道数据
        /// </summary>
        /// <param name="isVisible">是否显示</param>
        private void isShowJyqd(bool isVisible)
        {
            if (!isVisible)
            {
                this.tbMain.Rows[2].Cells[0].Text = "   交易渠道：";
                this.tbMain.Rows[2].Cells[1].InnerControl = this.cboTdChannel;
                this.tbMain.Rows[2].Cells[3].Text = "渠道类型：";
                this.tbMain.Rows[2].Cells[4].InnerControl = this.cboChanType;
                this.cboChanType.YssReadOnly = true;
                this.tbMain.Rows[3].Cells[0].Text = "   渠道名称：";
                this.tbMain.Rows[3].Cells[3].Text = "交易市场：";
                this.tbMain.Rows[3].Cells[4].InnerControl = this.cboExchange;
                this.cboExchange.YssReadOnly = true;
                this.tbMain.Rows[4].Visible = isVisible;
            }
        }

        /// <summary>
        ///  窗体load事件
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        private void Frm_SEC_TRANSFER_S_Load(object sender, System.EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.secTransferService = ServiceFactory.createService<ISecTransferService>();
            this.dataService = this.secTransferService;
        }

        //// tbMain.Rows来控制该控件只读状态 zhanghualin 2016-12-09
        /////// <summary>
        /////// 复制
        /////// </summary>
        /////// <param name="sender">控件</param>
        /////// <param name="e">事件信息</param>
        ////protected override void btnCopy_Click(object sender, EventArgs e)
        ////{
        ////    base.btnCopy_Click(sender, e);
        ////    this.yssTextPubCode.YssReadOnly = false;
        ////    initControlStat();
        ////}

        /// <summary>
        /// 修改
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnEdit_Click(object sender, EventArgs e)
        {
            base.btnEdit_Click(sender, e);
            this.cboChanType.YssReadOnly = true;
            initControlStat();
        }

        /////// <summary>
        /////// 重写保存事件
        /////// </summary>
        /////// <param name="sender">控件</param>
        /////// <param name="e">事件信息</param>
        ////protected override void btnSave_Click(object sender, System.EventArgs e)
        ////{
        ////    base.btnSave_Click(sender, e);

        ////    this.yssTextPubCode.YssReadOnly = true;
        ////    initControlStat();
        ////}

        /// <summary>
        /// 证券内码值改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void selSec_SelectedValueChanged(object sender, EventArgs e)
        {
            try
            {
                if (((YssSelCombox)sender).Value != null)
                {
                    // 当选择交易证券时，应取出交易证券中的证券品种信息等，并将他们赋值到对应的控件当中
                    SecBase secBase = ((SecBase)((YssSelCombox)sender).SelectedItem.DataEntity);
                    if (secBase != null)
                    {
                        this.txtIsinCode.Text = secBase.C_SEC_ISIN_CODE;
                        this.txtSecMarketCode.Text = secBase.C_SEC_MKT_CODE;
                        this.yssTextSecName.Text = secBase.C_SEC_NAME;
                        this.cboSecCategory.Value = secBase.C_SEC_VAR_CODE;
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
        /// 交易渠道值改变事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboTdChannel_SelectedValueChanged(object sender, EventArgs e)
        {
            try
            {
                if (((YssSelCombox)sender).Value != null)
                {
                    // 当选择交易渠道时，应取出交易渠道信息
                    TdChan aaa = ((TdChan)((YssSelCombox)sender).SelectedItem.DataEntity);
                    if (aaa != null)
                    {
                        this.cboChanType.Value = aaa.C_DV_CHAN_TYPE;
                        this.txtSecMarketCode.Text = aaa.C_TD_CHAN_NAME;
                        this.cboExchange.Value = aaa.C_MKT_CODE;
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
        /// 展示对象属性到窗体
        /// </summary>
        /// <param name="pojo">Pojo</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            SecTransfer secTransfer = null;
            try
            {
                if (pojo == null || string.IsNullOrEmpty(pojo.Id))
                {
                    secTransfer = this.yssGetBaseSelTypeItemMVC() as SecTransfer;
                }
                else
                {
                    secTransfer = (SecTransfer)pojo;
                }

                if (null != secTransfer)
                {
                    ////判断是交易证券保存界面还是交易渠道
                    if (this.myList.c_PR_TYPE.Equals("P"))
                    {
                        this.selSec.Value = secTransfer.C_SEC_CODE;
                        this.cboTransferRule.Value = secTransfer.C_TRANSFER_CODE;
                        this.yssTextPubCode.Text = secTransfer.C_PUB_CODE;
                    }
                    else
                    {
                        this.cboTdChannel.Value = secTransfer.C_SEC_CODE;
                        this.cboTransferRule.Value = secTransfer.C_TRANSFER_CODE;
                        this.yssTextPubCode.Text = secTransfer.C_PUB_CODE;
                    }

                    //// tbMain.Rows来控制该控件只读状态 zhanghualin 2016-12-09
                    ////if (status == YssResources.Fun.ClsEnums.StatusSetting.YssBrow)
                    ////{
                    ////    this.yssTextPubCode.YssReadOnly = true;
                    ////}
                }

            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }
        }

        /// <summary>
        /// 封装窗体数据到对象
        /// </summary>
        /// <returns>Pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            SecTransfer secTransfer = null;
            try
            {
                secTransfer = new SecTransfer();

                ////判断是交易证券保存界面还是交易渠道
                if (this.myList.c_PR_TYPE.Equals("P"))
                {
                    secTransfer.C_SEC_CODE = this.selSec.Value;
                    secTransfer.C_TRANSFER_CODE = this.cboTransferRule.Value;
                    secTransfer.C_PUB_CODE = this.yssTextPubCode.Text;
                    secTransfer.C_TYPE = "P";
                }
                else
                {
                    secTransfer.C_SEC_CODE = this.cboTdChannel.Value;
                    secTransfer.C_TRANSFER_CODE = this.cboTransferRule.Value;
                    secTransfer.C_PUB_CODE = this.yssTextPubCode.Text;
                    secTransfer.C_TYPE = "R";
                }

                if (status == ClsEnums.StatusSetting.YssEdit)
                {
                    SecTransfer oldSecTransfer = this.yssGetBaseSelTypeItemMVC() as SecTransfer;
                    if (null != oldSecTransfer)
                    {
                        if (oldSecTransfer.C_DATA_IDF.Equals(ClsBizCons.Z))
                        {
                            secTransfer.C_DATA_IDF = ClsBizCons.Z_H;
                        }
                        else if (oldSecTransfer.C_DATA_IDF.Equals(ClsBizCons.S))
                        {
                            secTransfer.C_DATA_IDF = ClsBizCons.S_H;
                        }
                        else
                        {
                            secTransfer.C_DATA_IDF = oldSecTransfer.C_DATA_IDF;
                        }
                    }
                    else
                    {
                        secTransfer.C_DATA_IDF = ClsBizCons.H;
                    }
                }
                else if (status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssCopy)
                {
                    secTransfer.C_DATA_IDF = ClsBizCons.H;
                }

            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return secTransfer;
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
                ////STORY38131【南方基金】【紧急】社保理事会要求交易席位科目是6位，不足6位前面补0  by guohui 20170208
                if (frmDetailData.MainDataPojo != null && frmDetailData.MainDataPojo is SecBase)
                {
                    this.selSec.YssReadOnly = true;
                    this.selSec.Value = (frmDetailData.MainDataPojo as SecBase).C_SEC_CODE;
                }

                if (frmDetailData.MainDataPojo != null && frmDetailData.MainDataPojo is YssSecInformation.Support.Mp.SecEq.Pojo.TdChan)
                {
                    this.cboTdChannel.YssReadOnly = true;
                    this.cboTdChannel.Value = (frmDetailData.MainDataPojo as YssSecInformation.Support.Mp.SecEq.Pojo.TdChan).C_TD_CHAN_CODE;
                }
            }
        }

    }
}
