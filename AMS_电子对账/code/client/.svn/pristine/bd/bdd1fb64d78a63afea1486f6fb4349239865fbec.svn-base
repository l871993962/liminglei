using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.BaseForm;
using FAST.Platform.User.Service;
using FAST.Core.Util;
using Yss.KRichEx.AutoFilter.Model;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
using FAST.Platform.User.Pojo;
using FAST.Core.Communication.Service;
using YssSztTool.Pojo.Para;
using YssSztTool.Service.Para;

namespace YssSztTool.Form.Para
{
    public partial class Frm_MR_CHECK_S : FrmBase
    {
        private ErTask task = null;

        public const string TASK_CODE = "ER_MR_CHECK";

        private IErTaskService taskService = null;

        private ICorpOrgService corpService = null;

        public Frm_MR_CHECK_S()
        {
            InitializeComponent();
            this.MaximizeBox = false;
            taskService = ServiceFactory.createService<IErTaskService>();
            corpService = ServiceFactory.createService<ICorpOrgService>();
        }

        public void init()
        {
            task = taskService.getErTaskByCode(Frm_MR_CHECK_S.TASK_CODE);
            if (task == null)
            {
                this.task = new ErTask();
                task.C_DV_TASK_CODE = Frm_MR_CHECK_S.TASK_CODE;
            }
            if(task != null)
            {
                this.InputInterval.Value = (int)task.N_RUN_INTERVAL;
                this.cboUser.Value = task.C_CALL_USER.Replace(",","|");
            }
        }

        private ErTask getTask()
        {
            task.C_CALL_USER = this.cboUser.Value == null ? "" : this.cboUser.Value.Trim().Replace("|", ",");
            task.N_RUN_INTERVAL = this.InputInterval.Value;
            return task;
        }

        /// <summary>
        /// 用户下拉框
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboUser_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            e.IsCancel = true;
            try
            {
                if (this.cboUser.Items.Count == 0)
                {
                    Dictionary<string, string> paraDict = new Dictionary<string, string>();
                    List<CorpUser> userList = corpService.getOrgUserList();
                    if (userList != null && userList.Count > 0)
                    {
                        foreach (CorpUser pojo in userList)
                        {
                            KTableEntity entity = new KTableEntity(pojo);
                            this.cboUser.Items.Add(entity);
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }
        }

        /// <summary>
        /// 保存按钮事件
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnSave_Click(object sender, EventArgs e)
        {
            ErTask task = getTask();
            if(task != null)
            {
                string operResult = taskService.updateTaskByCode(task);
                YssMessageBox.ShowCommonInfo(operResult);
            }
        }
        /// <summary>
        /// 取消按钮事件
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void btnCancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
