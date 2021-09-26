using System.Collections.Generic;
using YssElecReco.Pojo.Er;
using YssElecReco.Service.Er;
using FAST.Core.Communication.Service;
using FAST.Common.Service.Pojo;
using YssElecReco.Fun;


namespace YssElecReco.Form.Er
{
    /// <summary>
    /// 余额表
    /// </summary>
    public partial class Frm_ELEC_YE_S : Frm_ELEC_BBINFO_S
    {   
        /// <summary>
        /// service
        /// </summary>
        private IErYebService iErYebService;

        /// <summary>
        /// 余额报表
        /// </summary>
        public Frm_ELEC_YE_S()
        {
            ////this.bbName = "对账余额表";
            InitializeComponent();
            this.bShowRowCheckBoxColumn = false;
            this.bShowRowIndexColumn = true;
        }

        /// <summary>
        /// 初始化Service
        /// </summary>
        protected override void initServiceMVC()
        {
            base.initServiceMVC();
            iErYebService = ServiceFactory.createService<IErYebService>();
            this.dataService = iErYebService;
        }

        /// <summary>
        /// 加载表数据
        /// </summary>
        protected override void loadBBTab()
        {
            ////加载列头
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            ErBbInfo info = this.parentList.tbMain.SelectedRow.Tag as ErBbInfo;
            paraDict.Add("dataClass", "ErYeb");
            paraDict.Add("C_SN", info.C_SN);
            paraDict.Add("C_ASS_CODE", info.C_ASS_CODE);
            QueryRes res = iErYebService.queryByCondition(paraDict);
            new ErYebTableListLoader().loadListTable(tbMain, res, bShowRowCheckBoxColumn, bShowRowIndexColumn);
        }

        /// <summary>
        /// 行单击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbMain_RowClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            for (int i = 0; i < 2; i++)
            {
                this.tbMain.Rows[i].Selected = false;
            }
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
