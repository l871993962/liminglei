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
using FAST.Core.Bussiness.Form;
using FAST.Core.Context;
using FAST.Core.BaseControl.Fun;
using FAST.Core.Communication.BusiService;
using System.Threading;
using Yss.KRichEx.AutoFilter.Model;
using FAST.Core.Exceptions;
using Yss.KTable.Models;
using YssSyncData.Pojo.Base;
using FAST.Common.Service.Pojo;
using FAST.Core.BaseControl;

namespace YssSyncData.Form.Base
{
    /// <summary>
    /// 数据同步list界面
    /// </summary>
    public partial class Frm_SyncData_L : FrmBaseOper
    {
        /// <summary>
        /// 任务进度条
        /// </summary>
        private Yss.Tools.ProgressTool _progressTool;

        /// <summary>
        /// 数据同步服务
        /// </summary>
        private ISyncDataService syncDataService = null;

        /// <summary>
        /// 初始化
        /// </summary>
        public Frm_SyncData_L()
        {
            bUseMVCService = true;
            InitializeComponent();
            this._useByThread = true;
            receiveCustomLogBus = new receiveMegLog(getCustomLog);
        }

        /// <summary>
        /// 加载事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SyncData_L_Load(object sender, EventArgs e)
        {
            getInstanceService();
            this.btnBar.setButtonText(ClsButtonName.BtnGernerate, "同步");
            this.btnBar.setButtonToolTip(ClsButtonName.BtnGernerate, "同步");
            this.btnBar.setButtonText(ClsButtonName.BtnEdit, "忽略消息");
            this.btnBar.setButtonToolTip(ClsButtonName.BtnEdit, "忽略消息");
            YssMainKTableShowMode = ClsEnums.KTableDataShowMode.ListMode;
            bShowRowCheckBoxColumn = true;
            bShowRowIndexColumn = true;
            ShowPageInation = true;
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
        /// 将编辑时间改成忽略消息时间
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnEdit_Click(object sender, EventArgs e)
        {
            if (this.tbMain.CheckedRows.Count > 0)
            {
                ////不是“已接收”状态的消息不能忽略
                for (int i = this.tbMain.CheckedRows.Count - 1; i >= 0; i--)
                {
                    if (!((SyncData)this.tbMain.CheckedRows[i].Tag).C_DV_STATE.Equals("RECEIVED"))
                    {
                        this.tbMain.CheckedRows[i].Checked = false;
                    }
                }

                if (this.tbMain.CheckedRows.Count == 0)
                {
                    ShowCommonInfo("只能忽略[已接收]状态的数据");
                    return;
                }

                if (YssMessageBox.ShowQuestion("确定忽略已勾选的消息？") == DialogResult.Yes)
                {
                     try
                     {
                        btnBar.setButtonEnabled(ClsButtonName.BtnRefresh, false);
                        btnBar.setButtonEnabled(ClsButtonName.BtnEdit, false);
                        btnBar.setButtonEnabled(ClsButtonName.BtnGernerate, false);
                        this.tbFilter.ReadOnly = true;

                        if (this.syncDataService == null)
                        {
                            this.syncDataService = ServiceFactory.createService<ISyncDataService>();
                        }

                        List<SyncData> syncDatas = new List<SyncData>();
                        SyncData syncData = null;
                        foreach (Row row in this.tbMain.CheckedRows)
                        {
                            syncData = (SyncData)row.Tag;
                            syncDatas.Add(syncData);
                        }

                        string result = syncDataService.ignoreMessages(syncDatas);

                        this.btnSearch_Click(this.btnBar.getButton(FAST.Core.BaseControl.Fun.ClsButtonName.BtnRefresh).Owner, EventArgs.Empty);
                    }
                    catch (Exception ex)
                    {
                         throw new ClsBaseException(ex.Message, ex);
                    }
                    finally 
                    {
                        btnBar.setButtonEnabled(ClsButtonName.BtnRefresh, true);
                        btnBar.setButtonEnabled(ClsButtonName.BtnGernerate, true);
                        btnBar.setButtonEnabled(ClsButtonName.BtnEdit, true);
                        this.tbFilter.ReadOnly = false;
                    }
                }
            }
        }

        /// <summary>
        /// 生成按钮
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnBar_btnGernerate_Clicked(object sender, EventArgs e)
        {
            btnExecute_Click(sender, e);
        }

        /// <summary>
        /// 返回操作类型（用于校验组合权限）。
        /// </summary>
        /// <returns>返回操作类型，默认为“YssExecutor”</returns>
        protected override string GetOperType()
        {
            return "YssGenerate";
        }

        /// <summary>
        /// 在保存前检查界面元素的输入是否合法
        /// </summary>
        /// <returns>是否通过检查</returns>
        protected bool checkInput()
        {
            try
            {
                return clsInterface.checkControlsInput(tbFilter);
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message, e);
            }
        }

        /// <summary>
        /// 取消行点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbMain_RowDoubleClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            return;
        }

        /// <summary>
        /// 展示提示信息
        /// </summary>
        /// <param name="message">提示信息</param>
        private void ShowCommonInfo(string message)
        {
            YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo(message));
        }

        /// <summary>
        /// 设置级别结构
        /// </summary>
        /// <param name="structRecord">保存级别的结构</param>
        protected override void buildRecordStruct(ref string[] structRecord)
        {
            structRecord = new string[] { "DATE", "REPORT" };
        }

        /// <summary>
        /// 获取主区域选中的行/单元格对象
        /// </summary>
        /// <returns>返回选中行/单元格集合对象</returns>
        protected override Dictionary<string, string> getSelOperItemTags()
        {
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            SyncData syncData  = null;
            foreach (Row row in this.tbMain.CheckedRows)
            {
                syncData = (SyncData)row.Tag;
                if (!syncData.C_DV_STATE.Equals("RECEIVED"))
                {
                    continue;
                }

                paraDict.Add(syncData.Id, syncData.Id);
            }

            return paraDict;
        }

        /// <summary>
        /// 重置线程任务进度信息
        /// </summary>
        /// <param name="taskCount">线程任务总数</param>
        protected override void ResetProgressInfo(int taskCount)
        {
            taskCount = this.tbMain.CheckedRows.Count;
            base.ResetProgressInfo(taskCount);
        }

        /// <summary>
        /// 执行分段的业务数据处理
        /// </summary>
        /// <returns>返回执行状态</returns>
        public override string doSubSection()
        {
            string c_Result = "";
            bool isCancel = false;

            //// 1：执行前的一些处理
            beforeExecute(ref isCancel);
            if (!isCancel)
            {
                setOperServiceInstance();

                this.proBar.Visible = true;

                List<ThreadStart> threadStarts = new List<ThreadStart>();
                SyncData syncData = null;
                foreach (Row row in this.tbMain.CheckedRows)
                {
                    syncData = (SyncData)row.Tag;
                    Dictionary<string, string> dict = new Dictionary<string, string>();
                    dict.Add("C_DATA_ID", syncData.C_DATA_ID);  ////数据ID
                    dict.Add("C_DV_OPER_TYPE", syncData.C_DV_OPER_TYPE);    ////操作类型
                    dict.Add("C_SYSTEM_CODE", syncData.C_SYSTEM_CODE);  ////同步系统
                    dict.Add("C_DV_MODULE_CODE", syncData.C_DV_MODULE_CODE);    ////同步模块code
                    dict.Add("C_DV_MODULE_NAME", row.Cells[this.tbMain.Columns["C_DV_MODULE_CODE"].Index].Text);    ////同步模块name
                    dict.Add("C_FUN_CODE", "syncdata");
                    dict.Add("C_OPER_CODE", execOperCode);
                    dict.Add("C_RECEIVE_TIME", syncData.C_RECEIVE_TIME);
                    dict.Add("C_IDEN", syncData.Id);
                    threadStarts.Add(delegate { this.doMethod(dict); });
                }

                if (this.ThreadPool.ThreadCount > 0)
                {
                    ////如果线程已经启动，则将新线程附加到新的队列里
                    this.ThreadPool.JoinThread(threadStarts);
                }
                else
                {
                    ////如果尚未启动线程，则直接增加线程
                    foreach (ThreadStart threadStart in threadStarts)
                    {
                        this.ThreadPool.AddThread(threadStart);
                    }
                }
            }

            //// 3：执行后的一些处理
            afterExecute();
            return c_Result;
        }

        /// <summary>
        /// 执行前的处理
        /// </summary>
        /// <param name="isCancel">是否取消执行状态</param>
        protected override void beforeExecute(ref bool isCancel)
        {
            isCancel = !checkInput();

            ////不是“已接收”状态的消息不能同步
            SyncData syncData = null;
            for(int i = this.tbMain.CheckedRows.Count - 1; i>=0; i--)
            {
                syncData = (SyncData)this.tbMain.CheckedRows[i].Tag;
                if (!syncData.C_DV_STATE.Equals("RECEIVED"))
                {
                    this.tbMain.CheckedRows[i].Checked = false;
                }
            }

            if (this.tbMain.CheckedRows.Count == 0) 
            {
                isCancel = true;
            }

            ////if (!isCancel)
            ////{
            ////    if (this.tbMain.CheckedRows.Count > 200)
            ////    {
            ////        ClsRetInfo info = new ClsRetInfo();
            ////        info.infoGroup = ClsConstant.INFO_GRP_ATT;
            ////        info.infoTitle = "提示信息";
            ////        info.infoContent = "同步数据不能大于200条";
            ////        info.icon = MessageBoxIcon.Warning;
            ////        YssMessageBox.ShowCommonInfoText(info);
            ////        isCancel = true;
            ////        return;
            ////    }
            ////}
        }

        /// <summary>
        /// 线程启动时。供派生类重写
        /// </summary>
        protected override void OnThreadStarted()
        {
            base.OnThreadStarted();
            btnBar.setButtonEnabled(ClsButtonName.BtnRefresh, false);
            btnBar.setButtonEnabled(ClsButtonName.BtnEdit, false);
            btnBar.setButtonEnabled(ClsButtonName.BtnGernerate, false);
            this.tbFilter.ReadOnly = true;
        }

        /// <summary>
        /// 初始化业务服务
        /// </summary>
        protected override void setOperServiceInstance()
        {
            if (operSVC == null)
            {
                operSVC = BusiOperServiceFactory.createService<ISyncGenerateService>();
            }
        }

        /// <summary>
        /// 执行业务
        /// </summary>
        /// <param name="objArgs">参数</param>
        public override void doMethod(object objArgs)
        {
            try
            {
                string result = this.OperOnServer(operSVC, objArgs as Dictionary<string, string>);

                //// 将设置值的地方添加上锁，目的是多线程可能出现脏写
                lock (objLock)
                {
                    this.UpdateProgressStatus();
                }
            }
            catch (Exception ex)
            {
                ClsRetInfo clsInfo = new ClsRetInfo();
                clsInfo.infoContent = ex.Message;
                clsInfo.buttonGroup = System.Windows.Forms.MessageBoxButtons.OK;
                clsInfo.icon = System.Windows.Forms.MessageBoxIcon.Warning;
            }
        }

        /// <summary>
        /// 线程停止时,若汇总显示报表,则汇总查询结果
        /// </summary>
        protected override void OnThreadStoped()
        {
            try
            {
                ThreadStart executeHandler = delegate { this.ExecuteQuery(); };
                this.ShowStopQueryDialog(executeHandler);
            }
            catch (Exception e)
            {
                ClsLogger.Error(e);
            }
            finally
            {
                btnBar.setButtonEnabled(ClsButtonName.BtnRefresh, true);
                btnBar.setButtonEnabled(ClsButtonName.BtnGernerate, true);
                btnBar.setButtonEnabled(ClsButtonName.BtnEdit, true);
                this.tbFilter.ReadOnly = false;
            }
        }

        /// <summary>
        /// 执行查询
        /// </summary>
        private void ExecuteQuery()
        {
            try
            {
                Dictionary<string, string> paraDict = new Dictionary<string, string>();
                paraDict = OnGetParaAssemble(paraDict);
                PageInation pageIn = new PageInation();
                pageIn = (PageInation)JsonUtil.toObject(JsonUtil.toJson(page), pageIn.GetType());
                QueryRes res = getQueryResultMVC(paraDict, pageIn);
                loadListContentMVC(res);
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ex.Message, _formFun, status);
            }
            finally
            {
                if (this._progressTool != null)
                {
                    this.UpdateTaskProgress(1);
                }
            }
        }

        /// <summary>
        /// 增加终止查询按钮（主要用于处理查询报表数据过多时，终止查询任务）
        /// </summary>
        /// <param name="executeHandler">待执行的任务方法句柄</param>
        private void ShowStopQueryDialog(ThreadStart executeHandler)
        {
            if (this._progressTool == null || this._progressTool.IsDisposed)
            {
                this._progressTool = new Yss.Tools.ProgressTool();
            }

            this._progressTool.Text = "正在努力查询中，请稍候……";
            this._progressTool.TaskCount = 1;
            this._progressTool.SetExecuteHandler(executeHandler);
            this._progressTool.Start(this);
        }

        /// <summary>
        /// 更新任务刻度值。
        /// </summary>
        /// <param name="value">递增的刻度值</param>
        private void UpdateTaskProgress(int value)
        {
            if (this._progressTool != null && this._progressTool.IsDisposed == false)
            {
                if (this._progressTool.Value != value)
                {
                    this._progressTool.UpdateProgressValue(value);
                }
            }
        }

        /// <summary>
        /// 获取界面工具栏（用于自动化测试时，提取界面控件元素）。
        /// </summary>
        /// <returns>返回表格集</returns>
        protected  override YssButtonBar GetToolBar()
        {
            return this.btnBar;
        }

        /// <summary>
        /// 获取list查询条件区的查询条件
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>paraDict</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            paraDict.Add("dataClass", "SyncData");

            if (this.selSyncModule.CheckedItems.Count > 0)
            {
                StringBuilder builder = new StringBuilder();
                foreach (ControlEntity entity in this.selSyncModule.CheckedItems)
                {
                    builder.Append(entity.DisplayValue).Append(",");
                }

                string module = builder.ToString();
                if (module != null && module.EndsWith(","))
                {
                    module = module.Substring(0, module.Length - 1);
                    paraDict.Add("ARRAY_C_DV_MODULE_CODE", module);
                }
            }

            if (this.dtiReceive.DateBeginChecked && this.dtiReceive.getBeginDate != null)
            {
                paraDict.Add("D_START", this.dtiReceive.getBeginDate.ToString("yyyy-MM-dd") + " 00:00:00");
            }

            if (this.dtiReceive .DateEndChecked && this.dtiReceive.getEndDate != null)
            {
                paraDict.Add("D_END", this.dtiReceive.getEndDate.ToString("yyyy-MM-dd") + " 23:59:59");
            }

            if (this.selStatus.CheckedItems.Count > 0)
            {
                StringBuilder builder = new StringBuilder();
                foreach (ControlEntity entity in this.selStatus.CheckedItems)
                {
                   builder.Append(entity.DisplayValue).Append(",");
                }

                string status = builder.ToString();
                if (status != null && status.EndsWith(","))
                {
                    status = status.Substring(0, status.Length - 1);
                    paraDict.Add("ARRAY_C_DV_STATE", status);
                }
            }

            if (this.syncDataService == null)
            {
                this.syncDataService = ServiceFactory.createService<ISyncDataService>();
            }

            paraDict.Add("C_SYSTEM_CODE", syncDataService.getSystemCode());

            return paraDict;
        }
    }
}
