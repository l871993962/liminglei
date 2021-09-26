using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

using FAST.Core.BaseForm;

namespace YssElecReco.Form.Er
{
    /// <summary>
    /// 部署窗体
    /// </summary>
    public partial class Frm_DeployMess_S : FrmBase
    {
        /// <summary>
        /// result
        /// </summary>
        private DialogResult result = DialogResult.None;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_DeployMess_S()
        {
            InitializeComponent();
        }

        /// <summary>
        /// Result
        /// </summary>
        public DialogResult Result
        {
            get { return result; }
            set { result = value; }
        }
        
        /// <summary>
        /// btnOK_Click
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnOK_Click(object sender, EventArgs e)
        {
            if (table1.CheckedRows.Count == 0)
            {
               System.Windows.Forms.MessageBox.Show("请选择要部署接口！");
                return;
            }

            result = DialogResult.Yes;
            this.Close();
        }

        /// <summary>
        /// btnCancel_Click
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnCancel_Click(object sender, EventArgs e)
        {
            result = DialogResult.Cancel;
            this.Close();
        }

        
    }
}
