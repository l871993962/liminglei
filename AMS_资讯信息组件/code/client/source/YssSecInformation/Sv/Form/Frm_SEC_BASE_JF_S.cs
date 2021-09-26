using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
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
using FAST.Core.BaseControl;
using YssSecInformation.Support.Sv.Service;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Sv.Pojo;

namespace YssSecInformation.Sv.Form
{
    /// <summary>
    /// 功能简介：计费证券信息设置，负责计费证券信息的增删改查等功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： HeLiang
    /// 创建日期： 2016.09.05
    /// </summary>
    public partial class Frm_SEC_BASE_JF_S : FrmBaseSet
    {
        /// <summary>
        /// 定义服务
        /// </summary>
        private ISecBaseJfService secBaseJfService = null;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_SEC_BASE_JF_S()
        {
            InitializeComponent();
            this.bUseMVCService = true;
        }

        /// <summary>
        /// 封装界面元素为pojo对象
        /// </summary>
        /// <returns>由界面元素组成的对象</returns>
        public override AuditableParamPojo faceInfoToObjMVC()
        {
            SecBaseJf sec = null;
            try
            {
                sec = new SecBaseJf();
                sec.C_SEC_CODE = this.cboSecCode.Value; // 证券内码
                sec.C_SEC_MKT_CODE = string.IsNullOrEmpty(this.txtSecMarketCode.Value) ? "" : this.txtSecMarketCode.Value; // 上市代码
                sec.C_SEC_VAR_CODE = string.IsNullOrEmpty(this.cboSecCategory.Value) ? "" : this.cboSecCategory.Value; // 品种类型
                sec.C_SEC_NAME = string.IsNullOrEmpty(this.txtSecName.Value) ? "" : this.txtSecName.Value; // 证券名称
                sec.C_SFJT = this.cboChargingSec.Value; // 计费证券
            }
            catch (Exception ex)
            {
                if (ex is TransferException)
                {
                    throw ex;
                }
                else
                {
                    throw new ClsBaseException(ex.Message);
                }
            }

            return sec;
        }

        /// <summary>
        /// 获取list中选中记录，为界面元素赋值，显示数据
        /// </summary>
        /// <param name="pojo">数据对象</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            try
            {
                SecBaseJf sec = (SecBaseJf)yssGetBaseSelTypeItemMVC();

                this.cboSecCode.Value = sec.C_SEC_CODE; // 证券内码
                if (!string.IsNullOrEmpty(sec.C_SEC_MKT_CODE))
                {
                    this.txtSecMarketCode.Text = sec.C_SEC_MKT_CODE; // 上市代码
                }
                else
                {
                    this.txtSecMarketCode.Text = " ";
                }

                if (!string.IsNullOrEmpty(sec.C_SEC_VAR_CODE))
                {
                    this.cboSecCategory.Value = sec.C_SEC_VAR_CODE; // 品种类型
                }
                else
                {
                    this.cboSecCategory.Text = " ";
                }

                if (!string.IsNullOrEmpty(sec.C_SEC_NAME))
                {
                    this.txtSecName.Text = sec.C_SEC_NAME; // 证券名称
                }
                else
                {
                    this.txtSecName.Text = " ";
                }

                this.cboChargingSec.Value = sec.C_SFJT; // 计费证券
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 计费证券设置窗体加载事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_BASE_JF_S_Load(object sender, EventArgs e)
        {
            Type dataServiceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            secBaseJfService = (ISecBaseJfService)ServiceFactory.createService(dataServiceType);
            dataService = secBaseJfService;
        }

        /// <summary>
        /// 根据证券内码得到上市代码、品种类型和证券名称
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        private void cboSecCode_SelectedValueChanged(object sender, EventArgs e)
        {
            try
            {
                if (((YssSelCombox)sender).Value != null)
                {
                    // 当选择交易证券时，应取出交易证券中的币种信息和证券品种信息，并将他们赋值到对应的控件当中
                    SecBase secBase = ((SecBase)((YssSelCombox)sender).SelectedItem.DataEntity);
                    if (secBase != null)
                    {
                        this.txtSecMarketCode.Text = secBase.C_SEC_MKT_CODE; // 上市代码
                        this.cboSecCategory.Value = secBase.C_SEC_VAR_CODE; // 品种类型
                        this.txtSecName.Text = secBase.C_SEC_NAME; // 证券名称
                    }
                }
            }
            catch (Exception ye)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-110023", _formFun, status));
                ClsBaseException.DiscardException(ye);
            }
        }

    }
}
