using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;

using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Interface;
using FAST.Core.Exceptions;


using FAST.Core.Context;

using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;
using System;
using System.Collections.Generic;
using System.Collections;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Common.Service.Services;
using YssSecInformation.Support.Mp.SecEq.Service;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Mp.SecEq.Pojo;
using FAST.Core.CRUD.Interface;
using YssSecInformation.Support.Sv.Pojo;

namespace YssSecInformation.Mp.SecEq.Form
{
    /// <summary>
    /// chenbo
    /// 2017-06-24
    /// #42948 资讯信息管理组件化拆分
    /// 作用：本类是为了实现证券配对信息浏览与设置
    ///  
    ///  作者：liyanjun
    /// </summary>
    public partial class Frm_SEC_EQU_PD_S : FrmBaseSet
    {
        /// <summary>
        /// 权益信息数据服务对象
        /// </summary>
        private ISecEquPdService secEquPdService = null;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_SEC_EQU_PD_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 封装界面元素为pojo对象
        /// </summary>
        /// <returns>由界面元素组成的对象</returns>
        public override ClsBasePojo yssFaceInfoToObj()
        {
            Cls_SEC_EQU clsSecCirculate = null;
            I_SecBase sec = cboSec.SelectedItem.DataEntity as I_SecBase;
            try
            {
                clsSecCirculate = new Cls_SEC_EQU();
                clsSecCirculate.C_SEC_CODE = this.cboSec.Value;
                clsSecCirculate.C_MKT_CODE = sec.C_MKT_CODE;
                clsSecCirculate.C_SEC_CODE_TAG = this.cboSecTag.Value;
                clsSecCirculate.C_EQU_CLS = "PD";
                clsSecCirculate.C_DATA_IDF = "H";

            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return clsSecCirculate;
        }

        /// <summary>
        /// 锁定特殊控件的状态
        /// </summary>
        protected override void OnLockSpecialControlState()
        {
            base.OnLockSpecialControlState();

            if (this.frmBaseViewList != null && this.frmBaseViewList is IFormDetailData)
            {
                IFormDetailData frmDetailData = this.frmBaseViewList as IFormDetailData;
                if (frmDetailData.MainDataPojo != null)
                {
                    this.cboSec.YssReadOnly = true;
                    this.cboSec.Value = (frmDetailData.MainDataPojo as SecBase).C_SEC_CODE;
                }
            }
        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_EQU_PD_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.secEquPdService = ServiceFactory.createService(serviceType) as ISecEquPdService;
            this.dataService = this.secEquPdService;
        }

        /// <summary>
        /// 封装窗体数据到对象
        /// </summary>
        /// <returns>Pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            SecEqu clsSecCirculate = null;
            SecBase sec = cboSec.SelectedItem.DataEntity as SecBase;
            try
            {
                clsSecCirculate = new SecEqu();
                clsSecCirculate.C_SEC_CODE = this.cboSec.Value;
                clsSecCirculate.C_SEC_CODE_TAG = this.cboSecTag.Value;
                clsSecCirculate.C_MKT_CODE = sec.C_MKT_CODE;
                clsSecCirculate.C_EQU_CLS = "PD";
                clsSecCirculate.C_DATA_IDF = "H";

            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return clsSecCirculate;
        }

        /// <summary>
        /// 展示对象属性到窗体
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                SecEqu clsSecCirculate = (SecEqu)this.yssGetBaseSelTypeItemMVC();  // 从基类缓存中获取数据
                if (clsSecCirculate == null)
                {
                    return;
                }

                this.cboSec.Value = clsSecCirculate.C_SEC_CODE;
                this.cboSecTag.Value = clsSecCirculate.C_SEC_CODE_TAG;
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }
    }

}


