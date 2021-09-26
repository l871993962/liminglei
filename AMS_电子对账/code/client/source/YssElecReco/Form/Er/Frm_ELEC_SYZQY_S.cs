﻿using System.Collections.Generic;
using Yss.KTable.Models;
using YssElecReco.Pojo.Er;
using YssElecReco.Service.Er;
using FAST.Core.Communication.Service;
using FAST.Common.Service.Pojo;
using FAST.Core.Util;


namespace YssElecReco.Form.Er
{
    /// <summary>
    /// 所有者权益
    /// </summary>
    public partial class Frm_ELEC_SYZQY_S : Frm_ELEC_BBINFO_S
    {
        /// <summary>
        /// 加载service
        /// </summary>
        private IErSyzqybService iErSyzqybService;

        /// <summary>
        /// 所有者权益（基金净值）变动表
        /// </summary>
        public Frm_ELEC_SYZQY_S()
        {
            this.bShowRowCheckBoxColumn = false;
            ////this.bbName = "对账所有者权益（基金净值）变动表";
            InitializeComponent();
        }

        /// <summary>
        /// 加载service
        /// </summary>
        protected override void initServiceMVC()
        {
            base.initServiceMVC();
            iErSyzqybService = ServiceFactory.createService<IErSyzqybService>();
            this.dataService = iErSyzqybService;
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
                paraDict.Add("dataClass", "ErSyzqyb");
                paraDict.Add("C_SN", info.C_SN);
                paraDict.Add("C_ASS_CODE", info.C_ASS_CODE);
                paraDict.Add("C_RPT_TYPE", info.C_RPT_TYPE);
                QueryRes res = iErSyzqybService.queryByCondition(paraDict);
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
