using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using YssElecReco.Service.Er;
using FAST.Core.Communication.Service;
using Yss.KTable.Models;
using YssElecReco.Pojo.Er;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;

namespace YssElecReco.Form.Er
{
    /// <summary>
    /// 净资产变动表
    /// </summary>
    public partial class Frm_ELEC_JZCBDB_S : Frm_ELEC_BBINFO_S
    {
        /// <summary>
        /// service
        /// </summary>
        private IErJzcbdbService iErJzcbdbService;

        /// <summary>
        /// Frm_ELEC_JZCBDB_S
        /// </summary>
        public Frm_ELEC_JZCBDB_S()
        {
            InitializeComponent();
            this.bShowRowCheckBoxColumn = false;
        }

        /// <summary>
        /// 初始化service
        /// </summary>
        protected override void initServiceMVC()
        {
            base.initServiceMVC();
            iErJzcbdbService = ServiceFactory.createService<IErJzcbdbService>();
            this.dataService = iErJzcbdbService;
        }

        /// <summary>
        /// 加载报表
        /// </summary>
        protected override void loadBBTab()
        {
            if (tbMain.Columns.Count == 0)
            {
                ////查询数据
                Row row = parentList.tbMain.SelectedRow;
                ErBbInfo info = row.Tag as ErBbInfo;
                Dictionary<string, string> paraDict = new Dictionary<string, string>();
                paraDict.Add("dataClass", "ErJzcbdb");
                paraDict.Add("C_SN", info.C_SN);
                paraDict.Add("C_ASS_CODE", info.C_ASS_CODE);
                paraDict.Add("C_RPT_TYPE", info.C_RPT_TYPE);
                QueryRes res = iErJzcbdbService.queryByCondition(paraDict);
                new TableListLoader().loadListTable(tbMain, res, ShowRowCheckBoxColumn, ShowRowIndexColumn);
            }
        }

        /// <summary>
        /// 行单击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbMain_RowClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
        }

        /// <summary>
        /// 屏蔽行双击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbMain_RowDoubleClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
        }
    }

}
