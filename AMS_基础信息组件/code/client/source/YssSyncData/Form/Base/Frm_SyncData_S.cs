using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.CRUD.Form;
using YssSyncData.Service.Base;
using FAST.Core.Util;
using FAST.Core.Communication.Service;
using YssSyncData.Pojo.Base;
using FAST.Core.CRUD.Interface;
using FAST.Core.CRUD.Pojo;
using FAST.Core.BaseControl.Pojo;
using FAST.Core.Context;
using Yss.KTable.Models;
using Yss.KRichEx.AutoFilter.Model;
using FAST.Core.Exceptions;
using FAST.Core.Context.Events;

namespace YssSyncData.Form.Base
{
    /// <summary>
    /// 数据同步设置界面
    /// </summary>
    public partial class Frm_SyncData_S : FrmBaseSet, IParameterConfig
    {
        /// <summary>
        /// 数据同步服务
        /// </summary>
        private ISyncDataService syncDataService = null;

        /// <summary>
        /// 数据保存状态
        /// </summary>
        private bool _IsSaveSuccessed = false;

        /// <summary>
        /// 初始化
        /// </summary>
        public Frm_SyncData_S()
        {
            InitializeComponent();
            this.bUseMVCService = true;
        }

        /// <summary>
        /// 加载事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SyncData_S_Load(object sender, EventArgs e)
        {
            getInstanceService();
        }

        /// <summary>
        /// 创建窗体接口实例
        /// </summary>
        private void getInstanceService()
        {
            if (null == this.syncDataService)
            {
                Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
                this.syncDataService = ServiceFactory.createService(serviceType) as ISyncDataService;
                this.dataService = this.syncDataService;
            }
        }

        /// <summary>
        /// 初始化控件状态
        /// </summary>
        public override void initControlStat()
        {
            base.initControlStat();

            btnBar.setButtonEnabled("btnHelp", true);
            btnBar.setButtonEnabled("btnClose", true);
            btnBar.setButtonUnVisibled("btnNew");
            btnBar.setButtonUnVisibled("btnCopy");
            btnBar.setButtonUnVisibled("btnPrevious");
            btnBar.setButtonUnVisibled("btnNext");
            btnBar.setButtonUnVisibled("btnDelete");
            btnBar.setButtonUnVisibled("btnRecall");
            btnBar.setButtonUnVisibled("btnAudit");
            btnBar.setButtonUnVisibled("btnUnAudit");

            if (status == ClsEnums.StatusSetting.YssBrow)
            {
                btnBar.setButtonEnabled("btnEdit", true);
                btnBar.setButtonEnabled("btnSave", false);
            }
            else if (status == ClsEnums.StatusSetting.YssEdit)
            {
                btnBar.setButtonEnabled("btnEdit", false);
                btnBar.setButtonEnabled("btnSave", true);
            }

        }

        /// <summary>
        /// 获取list中选中记录，为界面元素赋值，显示数据
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
        }


        /// <summary>
        /// 加载同步功能配置
        /// </summary>
        /// <param name="syncModules">syncModules</param>
        public void showInfoMVC(List<SyncModule> syncModules)
        {
            this.rTBFuncodeCfg.Table.Clear();
            BuildBillingInfoHeader();
            foreach (SyncModule syncModule in syncModules)
            {
                Row row = new Row();
                row.Cells.Add(new Cell());
                row.Cells.Add(new Cell());

                Cell cell = new Cell();
                cell.Key = syncModule.C_MODULE_CODE;
                cell.Text = syncModule.C_MODULE_NAME;
                row.Cells.Add(cell);
                if (syncModule.C_CONFIGURED.Equals("TRUE"))
                {
                    row.Checked = true;
                }

                this.rTBFuncodeCfg.Table.Rows.Add(row);
            }

            this.rTBFuncodeCfg.Table.Refresh();
            this.rTBFuncodeCfg.ReadOnly = true;
        }

        /// <summary>
        /// 构建列头
        /// </summary>
        private void BuildBillingInfoHeader()
        {
            Table table = this.rTBFuncodeCfg.Table;

            Column column = new CheckBoxColumn();
            column.DataPropertyName = "ShowRowCheckBoxColumn";
            column.Width = 20;
            table.Columns.Add(column);

            Column column1 = new MarkColumn();
            column1.DataPropertyName = "ShowRowIndexColumn";
            column1.Text = "序号";
            column1.Width = 30;
            table.Columns.Add(column1);

            Column column2 = new Column();
            column2.DataPropertyName = "C_MODULE_CODE";
            column2.Text = "同步模块";
            column2.Width = this.rTBFuncodeCfg.Width - 48;
            column2.CellTextAlign = ContentAlignment.MiddleCenter;
            table.Columns.Add(column2);

            table.FixedLeftCols = 2;
            table.AllowColumnDrag = false;
            table.AllowResizeColumn = true;
            table.AutoColumnWidth = true;
            table.DefaultToolStripItems = Yss.KTable.Enums.SysToolStripItems.None;
            table.Refresh();
        }

        /// <summary>
        /// 封装一条交易数据对象，要把右侧的费用列表的值存放到交易数据中
        /// </summary>
        /// <returns>ClsBasePojo</returns>
        public override FAST.Common.Service.Pojo.Base.BasePojo faceInfoToBaseObjMVC()
        {
            return null;
        }


        /// <summary>
        /// 封装一条交易数据对象，要把右侧的费用列表的值存放到交易数据中
        /// </summary>
        /// <returns>List<SyncModule></returns>
        public List<SyncModule> faceInfo()
        {
            Table table = this.rTBFuncodeCfg.Table;
            List<SyncModule> syncModules  = new List<SyncModule>();
            if (table.CheckedRows.Count > 0)
            {
                foreach (Row row in table.CheckedRows)
                {
                    SyncModule syncModule = new SyncModule();
                    syncModule.C_MODULE_CODE = row.Cells[table.Columns["C_MODULE_CODE"].Index].Key;
                    syncModule.C_CONFIGURED = "TRUE";
                    syncModules.Add(syncModule);
                }
            }

            return syncModules;
        }

        /// <summary>
        /// 重写编辑按钮
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnEdit_Click(object sender, EventArgs e)
        {
            this.rTBFuncodeCfg.ReadOnly = false;
            status = ClsEnums.StatusSetting.YssEdit;
            this.initControlStat();
        }

        /// <summary>
        /// 重写保存
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected override void btnSave_Click(object sender, System.EventArgs e)
        {
            YssMessageBox.currentForm = this;

            string operResult = "";
            bool isDataSave;
            DateTime begin = DateTime.Now;
            try
            {
                this.ModifiedRowIds.Clear();

                YssBeforeOperEventArgs ye = new YssBeforeOperEventArgs(false);
                isDataSave = isSaveOper();
                if (isDataSave)
                {
                    ////if (YssOnBeforeSaveClick != null)
                    ////{
                    ////    YssOnBeforeSaveClick(sender, ye);
                    ////    if (ye.IsCancel == true)
                    ////    {
                    ////        return;
                    ////    }
                    ////}
                }


                if (!ye.IsCancel)
                {
                    ////新增、修改操作需要作输入项校验
                    if (status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssCopy || status == ClsEnums.StatusSetting.YssEdit)
                    {
                        if (!checkInput())
                        {
                            return;
                        }
                    }

                    ////校验组合锁定权限
                    if (this.ValidatePortLockAuthority() == false)
                    {
                        return;
                    }

                    ////获取配置信息
                    List<SyncModule> saveSyncModules = faceInfo();
                    if (this.syncDataService == null)
                    {
                        this.syncDataService = ServiceFactory.createService<ISyncDataService>();
                    }

                    operResult = this.syncDataService.saveSyncModule(saveSyncModules);

                    ////刷新界面
                    List<SyncModule> syncModules = this.syncDataService.queryAllFuncodeCfg();
                    this.YssStatus = ClsEnums.StatusSetting.YssBrow;
                    this.initForm(null);
                    this.initControlStat();
                    this.showInfoMVC(syncModules);
                }


                if (isDataSave)
                {
                    ////if (YssOnAfterSaveClick != null)
                    ////{
                    ////    YssOnAfterSaveClick(sender, ye);
                    ////}
                }
            }
            catch (System.Exception ex)
            {
                YssMessageBox.ShowCommonInfos(TransferErrorMessageUtil.getTransferException(ex));
            }
            finally
            {
                YssMessageBox.currentForm = null;

                if (frmBaseViewList != null)
                {
                    DateTime end = DateTime.Now;
                    double time = Math.Round((end - begin).TotalSeconds, 4);
                    frmBaseViewList.SetQuerySpendTime(time.ToString());
                }
            }
        }

        #region IParameterConfig 成员

        /// <summary>
        /// 获取正在编辑的节点项。
        /// </summary>
        public ConfigNode EditNode
        {
            get
            {
                return null;
            }
        }

        /// <summary>
        /// 获取一个值，该值指示当前页面的父容器是否需要展示保存按钮。
        /// </summary>
        public bool SaveButtonVisible
        {
            get { return false; }
        }

        /// <summary>
        /// 保存当前节点（当前只保存到数据库）
        /// </summary>
        /// <returns>是否成功保存</returns>
        public bool SaveConfigInfo()
        {
            bool llResult = false;
            FAST.Core.BaseControl.Pojo.ClsButtonInfo loSaveBtn = this.btnBar.getButton("btnSave");
            if (loSaveBtn.Enabled)
            {
                this.btnSave_Click(loSaveBtn.Owner, EventArgs.Empty);
                llResult = this._IsSaveSuccessed;
            }
            else
            {
                llResult = true;
            }

            return llResult;
        }

        /// <summary>
        /// 展示信息（当前不支持展示EditNode）
        /// </summary>
        /// <param name="poConfigNode">ConfigNode</param>
        public void ShowConfigInfo(ConfigNode poConfigNode)
        {
            this.YssFormMenu = ClsContext.sysMenuFunHash["syncdata"];
            this.AutoSize = true;
            this.FormBorderStyle = FormBorderStyle.None;
            this.ShowInTaskbar = false;
            this.Dock = DockStyle.Fill;
            this.TopLevel = false;
            FrmParameterConfigBox loParentFrm = this.ParentForm as FrmParameterConfigBox;
            if (loParentFrm != null)
            {
                if (this.syncDataService == null)
                {
                    this.syncDataService = ServiceFactory.createService<ISyncDataService>();
                }

                List<SyncModule> syncModules = this.syncDataService.queryAllFuncodeCfg();
                this.YssStatus = ClsEnums.StatusSetting.YssBrow;
                this.initForm(null);
                this.initControlStat();
                this.showInfoMVC(syncModules);
            }

            this.Show();
        }

        #endregion
    }
}
