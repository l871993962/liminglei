using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using YssElecReco.Pojo.Er;
using Yss.KTable.Models;
using System.Text.RegularExpressions;
using YssElecReco.Service.Er;
using FAST.Core.Communication.Service;
using FAST.Common.Service.Pojo;
using YssElecReco.Fun;

namespace YssElecReco.Form.Er
{
    /// <summary>
    /// Frm_ELEC_GZ_S
    /// </summary>
    public partial class Frm_ELEC_GZ_S : Frm_ELEC_BBINFO_S
    {
         /// <summary>
        /// service
        /// </summary>
        private IErGzbService iErGzbService;

        /// <summary>
        /// 估值报表
        /// </summary>
        public Frm_ELEC_GZ_S()
        {
            ////this.bbName = "对账估值表";
            InitializeComponent();
            this.bShowRowCheckBoxColumn = false;
        }

        /// <summary>
        /// 初始化service
        /// </summary>
        protected override void initServiceMVC()
        {
            base.initServiceMVC();
            iErGzbService = ServiceFactory.createService<IErGzbService>();
            this.dataService = iErGzbService;
        }

        /// <summary>
        /// 加载报表
        /// </summary>
        protected override void loadBBTab()
        {
            if (tbMain.Columns.Count == 0)
            {
                ////查询数据
                Dictionary<string, string> paraDict = new Dictionary<string, string>();
                ErBbInfo info = this.parentList.tbMain.SelectedRow.Tag as ErBbInfo;
                paraDict.Add("dataClass", "ErGzb");
                paraDict.Add("C_SN", info.C_SN);
                paraDict.Add("C_ASS_CODE", info.C_ASS_CODE);
                QueryRes res = iErGzbService.queryByCondition(paraDict);
                new GzbHdTableListLoader().loadListTable(tbMain, res, ShowRowCheckBoxColumn, ShowRowIndexColumn);
            }
        }

        /// <summary>
        /// 行单击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbMain_RowClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            ////for (int i = 0; i < 3; i++)
            ////{
            ////    this.tbMain.Rows[i].Selected = false;
            ////}
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
