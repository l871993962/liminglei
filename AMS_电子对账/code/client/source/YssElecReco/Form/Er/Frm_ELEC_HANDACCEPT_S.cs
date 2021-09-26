using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;
using YssElecReco.Pojo.Er;
using YssElecReco.Service.Er;
using FAST.Core.Util;
using FAST.Core.Communication.Service;
using Yss.KRichEx.AutoFilter.Model;
using FAST.Core.Context;
using YssElecReco.Fun;
////using YssDevComponents.DotNetBar;

namespace YssElecReco.Form.Er
{
    /// <summary>
    /// 手工对账一致
    /// </summary>
    public partial class Frm_ELEC_HANDACCEPT_S : FrmBaseSet
    {
        /// <summary>
        /// 声明service对象
        /// </summary>
        private IErBbInfoService iErBbInfoService = null;

        /// <summary>
        /// tabPage
        /// </summary>
        private string tabPage;

        /// <summary>
        /// List
        /// </summary>
        private List<ErBbInfo> dataList;

        /// <summary>
        /// STORY #103840 电子对账功能优化
        /// </summary>
        private bool dbCheckIsAuth;

        /// <summary>
        /// setDbCheckIsAuth
        /// </summary>
        /// <param name="dbCheckIsAuth">dbCheckIsAuth</param>
        public void setDbCheckIsAuth(bool dbCheckIsAuth)
        {
            this.dbCheckIsAuth = dbCheckIsAuth;
        }

        /// <summary>
        /// 构造方法
        /// </summary>
        /// <param name="tabPage">tabPage</param>
        /// <param name="list">list</param>
        public Frm_ELEC_HANDACCEPT_S(string tabPage, List<ErBbInfo> list)
        {
            this.tabPage = tabPage;
            this.dataList = list;
            InitializeComponent();
            this.bUseMVCService = true;
        }

        /// <summary>
        /// TabPage
        /// </summary>
        public string TabPage
        {
            get
            {
                return tabPage;
            }

            set
            {
                value = tabPage;
            }
        }

        /// <summary>
        /// 数据list
        /// </summary>
        public List<ErBbInfo> DataList
        {
            get
            {
                return dataList;
            }

            set
            {
                value = dataList;
            }
        }

        /// <summary>
        /// 设置按钮组的状态
        /// </summary>
        public void setBtnStatus()
        {
            this.btnBar.setAllButtonVisibled(false);
            this.btnBar.setButtonVisibled("btnSave");
            this.btnBar.setButtonVisibled("btnClose");
            this.btnBar.setButtonVisibled("btnHelp");
            ////反馈一致界面显示修改按钮，状态为浏览状态
            if (this.tabPage.Equals(ElecConstant.FKS_FLAG))
            {
                this.btnBar.setButtonVisibled("btnEdit");
                this.btnBar.setButtonEnabled("btnEdit");
                this.btnBar.setButtonDisabled("btnSave");
            }
        }
       
        /// <summary>
        /// 窗体加载
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ELEC_SGYY_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.iErBbInfoService = ServiceFactory.createService(serviceType) as IErBbInfoService;
            this.dataService = this.iErBbInfoService;
            setBtnStatus();
            this.setComStatus();
        }

        /// <summary>
        /// 设置组件的状态和默认值
        /// </summary>
        private void setComStatus()
        {
            this.dzDate.Value = DateTime.Now;
            ////反馈不一致中和总览页面只能修改手工原因和说明
            if (ElecConstant.FKF_FLAG.Equals(this.tabPage) || ElecConstant.ZL_FLAG.Equals(this.tabPage))
            {
                this.cboRepType.YssReadOnly = true;
                this.cboDzType.YssReadOnly = true;
                this.dzDate.YssReadOnly = true;
                ////只读状态去掉必输校检
                this.cboRepType.YssIsMust = false;
                this.cboDzType.YssIsMust = false;
                this.dzDate.YssIsMust = false;
                setValueFromPojos(this.dataList);
                ////this.dzDate.Hide();
                ////this.labelDzDate.Hide();
            }
            ////反馈一致中修改浏览状态，组件默认不可用
            if (ElecConstant.FKS_FLAG.Equals(this.tabPage))
            {
                setValueFromPojos(this.dataList);
            }
        }

        /// <summary>
        /// 用列表的第一个Pojo为组件赋值
        /// </summary>
        /// <param name="list">list</param>
        private void setValueFromPojos(List<ErBbInfo> list)
        { 
            if (list == null || list.Count == 0)
            {
                return;
            }

            ErBbInfo pojo = list[0];
            this.cboRepType.Value = pojo.C_RPT_TYPE;
            this.cboDzType.Value = pojo.C_FILE_TYPE;
            this.dzDate.Value = Convert.ToDateTime(pojo.D_DATE);
            Yss.KRichEx.AutoFilter.Collections.ControlEntityCollection items = this.cboSgyy.Items;
            this.cboSgyy.Value = pojo.C_DV_RESULT;
            this.summaryTxt.Text = pojo.C_SUMMARY;

        }

        /// <summary>
        /// 手工原因下拉框
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboSgyy_setValue(object sender, FAST.Core.Context.Events.YssBeforeOperEventArgs e)
        {
            e.IsCancel = true;
            if (this.cboSgyy.Items.Count == 0)
            {
                this.cboSgyy.DisplayName = "C_DV_NAME";
                this.cboSgyy.DisplayValue = "C_DV_CODE";
                this.cboSgyy.Parameter = "C_DV_NAME";
                this.cboSgyy.SortColumn = "C_DV_CODE";
                ////STORY #67173【加急】【鹏华基金】为方便自动化流程管理，增加电子对账结果处理界面 总览页加载所有原因
                ////对账不一致页与总览页设置手工原因下拉框
                if (ElecConstant.FKF_FLAG.Equals(this.tabPage))
                {
                    this.cboSgyy.Items.Add(new KTableEntity("对账一致，映射有问题", "DZDZMS_YSCW"));
                    this.cboSgyy.Items.Add(new KTableEntity("我方按托管行结果调整", "DZDZMS_YTGHWZTZ"));
                    this.cboSgyy.Items.Add(new KTableEntity("托管行按我方结果调整", "DZDZMS_YWFWZTZ"));

                }
                else if (ElecConstant.QTDZ_FLAG.Equals(this.tabPage))
                { ////其他对账页设置手工原因下拉框
                    this.cboSgyy.Items.Add(new KTableEntity("电话对账", "QTDZMS_DHDZ"));
                    this.cboSgyy.Items.Add(new KTableEntity("传真对账", "QTDZMS_CZDZ"));
                    this.cboSgyy.Items.Add(new KTableEntity("其他对账", "QTDZMS_QTDZ"));
                }
                else
                {
                    this.cboSgyy.Items.Add(new KTableEntity("对账一致，映射有问题", "DZDZMS_YSCW"));
                    this.cboSgyy.Items.Add(new KTableEntity("我方按托管行结果调整", "DZDZMS_YTGHWZTZ"));
                    this.cboSgyy.Items.Add(new KTableEntity("托管行按我方结果调整", "DZDZMS_YWFWZTZ"));
                    this.cboSgyy.Items.Add(new KTableEntity("电话对账", "QTDZMS_DHDZ"));
                    this.cboSgyy.Items.Add(new KTableEntity("传真对账", "QTDZMS_CZDZ"));
                    this.cboSgyy.Items.Add(new KTableEntity("其他对账", "QTDZMS_QTDZ"));
                }

               
            }
        }

        /// <summary>
        /// 报表类型
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboRepType_setValue(object sender, FAST.Core.Context.Events.YssBeforeOperEventArgs e)
        {
            e.IsCancel = true;
            if (this.cboRepType.Items.Count == 0)
            {
                this.cboRepType.DisplayName = "C_DV_NAME";
                this.cboRepType.DisplayValue = "C_DV_CODE";
                this.cboRepType.Parameter = "C_DV_NAME";
                this.cboRepType.SortColumn = "C_DV_CODE";
                this.cboRepType.Items.Add(new KTableEntity("日报", "01"));
                this.cboRepType.Items.Add(new KTableEntity("月报", "03"));
                this.cboRepType.Items.Add(new KTableEntity("季报", "04"));
                this.cboRepType.Items.Add(new KTableEntity("半年报", "05"));
                this.cboRepType.Items.Add(new KTableEntity("年报", "06"));
            }
        }

        /// <summary>
        /// 修改
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnEdit_Click(object sender, EventArgs e)
        {
            this.btnBar.setButtonEnabled("btnSave");
            this.btnBar.setButtonDisabled("btnEdit");
            ////解除只读模式
            this.tbMain.ReadOnly = false;
            this.stBarBottom.StatuType = "修改(&Edit)";
            ////对账一致中只可以修改说明信息，其他组件不可用
            if (ElecConstant.FKS_FLAG.Equals(this.tabPage))
            {
                this.cboRepType.YssReadOnly = true;
                this.cboDzType.YssReadOnly = true;
                this.dzDate.YssReadOnly = true;
                this.cboSgyy.YssReadOnly = true;
                ////只读状态去掉必输校检
                this.cboRepType.YssIsMust = false;
                this.cboDzType.YssIsMust = false;
                this.dzDate.YssIsMust = false;
                this.cboSgyy.YssIsMust = false;
                setValueFromPojos(this.dataList);
            }
        }

        /// <summary>
        /// 数据的保存事件
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnSave_Click(object sender, System.EventArgs e)
        {
            Yss.KMessage.MessageDialog messS = new Yss.KMessage.MessageDialog();
            if (this.dataList == null || this.dataList.Count == 0)
            {
                messS.Show("请选择一条数据！", "警告", MessageBoxButtons.OK);
                this.Close();
                frmBaseViewList.btnSearch_Click(null, null);
                return;
            }
            ////必输项校检
            if (!checkInput())
            {
                return;
            }

            try
            {
                switch (this.tabPage)
                {
                    case ElecConstant.FKS_FLAG:
                        this.saveSgyyForFKS(this.dataList);
                        break;
                    case ElecConstant.FKF_FLAG:
                        this.saveSgyyForFKF(this.dataList);
                        break;
                    case ElecConstant.QTDZ_FLAG:
                        this.saveSgyyForBDZ(this.dataList);
                        break;
                    case ElecConstant.ZL_FLAG:
                        ////总览页和反馈不一致页保存方法相同
                        this.saveSgyyForZL(this.dataList);
                        break;
                    default:
                        break;
                }

                messS.Show("操作成功！", "消息", MessageBoxButtons.OK);
                this.Close();
                frmBaseViewList.btnSearch_Click(null, null);
            }
            catch (Exception ex)
            {
                Yss.KMessage.MessageDialog mess = new Yss.KMessage.MessageDialog();
                mess.Show(ex.Message, "警告", MessageBoxButtons.OK);
            }
            
            
        }

        /// <summary>
        /// 反馈一致中
        /// 保存说明的方法
        /// </summary>
        /// <param name="dataList">dataList</param>
        private void saveSgyyForFKS(List<ErBbInfo> dataList)
        {
            foreach (ErBbInfo info in dataList)
            {
                info.Modifier = ClsContext.currentUser.C_USER_CODE;
                info.ModifyDate = DateTime.Now.ToString("yyyyMMdd HH:mm:ss");
                info.C_SUMMARY = this.summaryTxt.Text;
            }

            string res = iErBbInfoService.acceptBbInfo(dataList);
        }


        /// <summary>
        /// 反馈不一致中
        /// 保存手工原因及说明的方法
        /// </summary>
        /// <param name="dataList">dataList</param>
        private void saveSgyyForFKF(List<ErBbInfo> dataList)
        {
            foreach (ErBbInfo info in dataList)
            {
                /*if (!"ER_IDENTICAL".Equals(info.C_STATE))
                {
                    Yss.KMessage.MessageDialog mess = new Yss.KMessage.MessageDialog();
                    mess.Show("只能操作状态为【对账不一致】的数据！", "提示", MessageBoxButtons.OK);
                    return;
                }*/
                info.C_STATE = "ER_ACCECPED";
                string msgInfo = "手工对账一致";
                if (dbCheckIsAuth)
                {
                    msgInfo = "手工对账一致(未审核)";
                }
                info.C_ERR_INFO = msgInfo;
                info.Modifier = ClsContext.currentUser.C_USER_CODE;
                info.ModifyDate = DateTime.Now.ToString("yyyyMMdd HH:mm:ss");
                info.C_DV_RESULT = this.cboSgyy.Value;
                info.C_SUMMARY = this.summaryTxt.Text;

            }

            string res = iErBbInfoService.acceptBbInfo(dataList);
        }

        /// <summary>
        /// 其他对账中
        /// 保存手工原因及说明的方法
        /// </summary>
        /// <param name="dataList">dataList</param>
        private void saveSgyyForBDZ(List<ErBbInfo> dataList)
        {
            foreach (ErBbInfo info in dataList)
            {
                // C_SN,C_DV_ER_WAY,C_ASS_CODE,C_FILE_TYPE,C_RPT_TYPE,C_STATE,N_CHECK_STATE,D_DATE,C_ERR_INFO,C_DV_RESULT,C_SUMMARY 
                info.D_DATE = this.dzDate.Text;
                info.C_RPT_TYPE = this.cboRepType.Value;
                info.C_FILE_TYPE = this.cboDzType.Value;
                info.C_STATE = "ER_ACCECPED";
                string msgInfo = "手工对账一致";
                if (dbCheckIsAuth)
                {
                    msgInfo = "手工对账一致(未审核)";
                }
                info.C_ERR_INFO = msgInfo;
                info.Modifier = ClsContext.currentUser.C_USER_CODE;
                info.ModifyDate = DateTime.Now.ToString("yyyyMMdd HH:mm:ss");
                info.C_DV_RESULT = this.cboSgyy.Value;
                info.C_SUMMARY = this.summaryTxt.Text;
            }

            string res = iErBbInfoService.acceptBbInfoForQTDZ(dataList);
            
            
        }

        /// <summary>
        /// 其他对账中
        /// 保存手工原因及说明的方法
        /// </summary>
        /// <param name="dataList">dataList</param>
        private void saveSgyyForZL(List<ErBbInfo> dataList)
        {
            foreach (ErBbInfo info in dataList)
            {
                info.C_STATE = "ER_ACCECPED";
                string msgInfo = "手工对账一致";
                if (dbCheckIsAuth)
                {
                    msgInfo = "手工对账一致(未审核)";
                }
                info.C_ERR_INFO = msgInfo;
                info.Modifier = ClsContext.currentUser.C_USER_CODE;
                info.ModifyDate = DateTime.Now.ToString("yyyyMMdd HH:mm:ss");
                info.C_DV_RESULT = this.cboSgyy.Value;
                info.C_SUMMARY = this.summaryTxt.Text;
            }

            string res = iErBbInfoService.acceptBbInfoForQTDZ(dataList);
        }


    }
}
