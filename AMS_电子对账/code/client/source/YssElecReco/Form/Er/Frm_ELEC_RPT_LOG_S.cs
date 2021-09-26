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
using YssElecReco.Pojo.Er;
using FAST.Core.Communication.Service;
using YssElecReco.Service.Er;
using System.IO;
using System.Xml;

namespace YssElecReco.Form.Er
{
    /// <summary>
    /// 接收数据明细
    /// </summary>
    public partial class Frm_ELEC_RPT_LOG_S : FrmBaseSet
    {
        /// <summary>
        /// 报文日志
        /// </summary>
        private IErRptLogService iErRptLogService = null;

        /// <summary>
        /// 构造方法
        /// </summary>
        /// <param name="id">报文记录ID</param>
        public Frm_ELEC_RPT_LOG_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();
            iErRptLogService = ServiceFactory.createService<IErRptLogService>();
            this.dataService = iErRptLogService;
        }

        /// <summary>
        /// 显示单条数据，参数为set界面数据对应的pojo对象    add by yh 2011.04.07  向set界面填充数据的pojo对象由基类提供
        /// </summary>
        /// <param name="pojo">显示数据对应的pojo对象</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            if (pojo != null && pojo is ErRptLog)
            {
                ErRptLog log = pojo as ErRptLog;
                string info = this.iErRptLogService.queryLogById(log.Id);
                this.webBrowser.DocumentText = info;
            }
        }

        /// <summary>
        /// 初始化窗体
        /// </summary>
        /// <param name="sender">触发对象</param>
        /// <param name="e">触发事件</param>
        private void Frm_ELEC_RPT_LOG_S_Load(object sender, EventArgs e)
        {
            this.Text = "接收数据明细";
            this.webBrowser.IsWebBrowserContextMenuEnabled = false;
        }

    }
}
