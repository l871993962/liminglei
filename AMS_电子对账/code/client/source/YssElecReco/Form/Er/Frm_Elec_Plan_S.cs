using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.CRUD.Form;
using YssElecReco.Service.Bi;
using FAST.Core.Communication.Service;
using YssElecReco.Pojo.Er;
using FAST.Core.Context;
using YssElecReco.Service.Er;
using FAST.Core.Exceptions;
using FAST.Common.Service.Pojo;
using FAST.Core.Util;
using YssElecReco.Pojo.Bi;
using System.Collections;
using YssElecReco.Fun;

namespace YssElecReco.Form.Er
{
    /// <summary>
    /// STORY #41248 【南方基金】电子对账反馈结果中的差异数据下拉框扩展 新增该窗体
    /// </summary>
    public partial class Frm_Elec_Plan_S : FrmBaseSet
    {
        /// <summary>
        /// IElecRelaService
        /// </summary>
        private IElecRelaService relaService = null;

        /// <summary>
        /// IErResviewService
        /// </summary>
        private IErResviewService resviewService = null;

        /// <summary>
        /// 父页面获取的方案代码
        /// </summary>
        private string code = "";

        /// <summary>
        /// 父页面获取的方案名称
        /// </summary>
        private string name = "";

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_Elec_Plan_S()
        {
            InitializeComponent();
            this.bUseMVCService = true;
        }

        /// <summary>
        /// 父页面获取的方案代码
        /// </summary>
        public string Code
        { 
            get { return this.code; }
            set { this.code = value; }
        }

        /// <summary>
        /// 父页面获取的方案名称
        /// </summary>
        public string Name
        {
            get { return this.name; }
            set { this.name = value; }
        }

        /// <summary>
        /// 初始化状态
        /// </summary>
        private void initStatus()
        {
            this.btnBar.setButtonVisable("btnPrevious", false);
            this.btnBar.setButtonVisable("btnNext", false);
            this.btnBar.setButtonVisable("btnRecall", false);
            if (status == ClsEnums.StatusSetting.YssAdd)
            {
                initSaveStatus();
            }
            else
            {
                initBrowStatus();
            }
        }

        /// <summary>
        /// 获取下拉框选中记录，为界面元素赋值，显示数据
        /// </summary>
        private void initComValue()
        {
            ElecRelaTableListLoader tableLoader = new ElecRelaTableListLoader();
            if (status == ClsEnums.StatusSetting.YssBrow)
            {
                this.codeTextBox.Text = this.code;
                this.nameTextBox.Text = this.name;
                List<string> list = resviewService.queryItemCodesByPlanCode(code);
                tableLoader.DefaultCheckedRows = list;
            }

            tableLoader.AutoSort = false;
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("ARRAY_C_DZ_CODE", "1011");
            paraDict.Add("dataClass", "ElecRela");
            QueryRes res = relaService.queryByCondition(paraDict);
            tableLoader.loadTable(this.tableList, res, true, true, ClsEnums.KTableDataShowMode.ListMode);
            
        }

        /// <summary>
        /// 初始化保存状态
        /// </summary>
        private void initSaveStatus()
        {
            this.btnBar.setButtonVisable("btnSave", true);
            this.btnBar.setButtonEnabled("btnSave");
        }

        /// <summary>
        /// 初始化浏览状态
        /// </summary>
        private void initBrowStatus()
        {
            this.tablefangan.ReadOnly = true;
            this.tableList.ReadOnly = true;
            this.btnBar.setButtonVisable("btnDelete", true);
            this.btnBar.setButtonEnabled("btnDelete");
        }

        /// <summary>
        /// 加载主要指标列表
        /// </summary>
        /// <param name="tableLoader">tableLoader</param>
        private void initItemTableList(ElecRelaTableListLoader tableLoader)
        {
            tableLoader.AutoSort = true;
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("ARRAY_C_DZ_CODE", "1011");
            paraDict.Add("dataClass", "ElecRela");
            QueryRes res = relaService.queryByCondition(paraDict);
            tableLoader.loadTable(this.tableList, res, true, true, ClsEnums.KTableDataShowMode.ListMode);
        }

        /// <summary>
        /// 窗体加载
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_Elec_Plan_S_Load(object sender, EventArgs e)
        {
            ////初始化组件状态
            initStatus();
            ////为界面组件赋值
            initComValue();
        }

        /// <summary>
        /// 设置默认值
        /// </summary>
        /// <param name="resview">resview</param>
        /// <returns>ErResview</returns>
        private ErResview setPojoDefaultValue(ErResview resview)
        {
            resview.C_PLAN_TYPE = "01";
            resview.C_PLAN_CODE = this.codeTextBox.Text;
            resview.C_PLAN_NAME = this.nameTextBox.Text;
            resview.Modifier = ClsContext.currentUser.C_USER_CODE;
            resview.ModifyDate = DateTime.Now.ToString("yyyyMMdd HH:mm:ss");
            resview.OperUser = ClsContext.currentUser.C_USER_CODE;
            return resview;
        }

        /// <summary>
        /// 将控件的值保存到Pojos
        /// </summary>
        ///  <param name="itemList">itemList</param>
        /// <returns>ArrayList</returns>
        private ArrayList getPojos(List<ElecRela> itemList)
        {
            ArrayList pojos = new ArrayList();
            ////主要指标代码不为空时，将指标代码放到方案中
            foreach (ElecRela rela in itemList)
            {
                ErResview resview = new ErResview();
                setPojoDefaultValue(resview);
                resview.C_ITEM_CODE = rela.C_ZB_CODE;
                pojos.Add(resview);
            }

            return pojos;
        }

        /// <summary>
        /// 获取指标项中选择的行
        /// </summary>
        /// <returns>List</returns>
        private List<ElecRela> getItems()
        {
            List<ElecRela> list = new List<ElecRela>();
            if (tableList.CheckedRows.Count != 0)
            {
                foreach (Yss.KTable.Models.Row row in tableList.CheckedRows)
                {
                    // add by yh 2011.03.13 增加在获取list界面选中行时，去掉分组行数据的判断
                    if (row.IsGroup != true)
                    {
                        if (row.Tag is ElecRela)
                        {
                            list.Add((ElecRela)row.Tag);
                        }
                    }
                }
            }
            else
            {
                foreach (Yss.KTable.Models.Row row in this.tableList.SelectedRows)
                {
                    if (row.IsGroup != true)
                    {
                        if (row.Tag is ElecRela)
                        {
                            list.Add((ElecRela)row.Tag);
                        }
                    }
                }
            }

            
            return list;
        }

        /// <summary>
        /// 初始化服务
        /// </summary>
        protected override void initServiceMVC()
        {
            if (dataService == null)
            {
                relaService = ServiceFactory.createService<IElecRelaService>();
                resviewService = ServiceFactory.createService<IErResviewService>();
                this.dataService = resviewService;
            }
        }

        /// <summary>
        /// 在保存前检查界面元素的输入是否合法
        /// </summary>
        /// <returns>是否通过检查</returns>
        protected override bool checkInput()
        {
            return clsInterface.checkControlsInput(this.tablefangan);
        }

        /// <summary>
        /// 数据的保存事件
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnSave_Click(object sender, System.EventArgs e)
        {
            ////必输项校检
            if (!checkInput())
            {
                return;
            }
            ////
            ////获取选中的主要指标
            List<ElecRela> itemList = getItems();
            ////主要指标代码为空时，给出提示信息
            if (itemList == null || itemList.Count == 0)
            {
                Yss.KMessage.MessageDialog messI = new Yss.KMessage.MessageDialog();
                messI.Show("请选择主要指标！", "消息", MessageBoxButtons.OK);
                return;
            }
            ////将组件的值放到pojo列表中
            ArrayList pojos = getPojos(itemList);
            if (pojos == null || pojos.Count == 0)
            {
                Yss.KMessage.MessageDialog messS = new Yss.KMessage.MessageDialog();
                messS.Show("操作失败！未获取到方案信息！", "消息", MessageBoxButtons.OK);
                return;
            }

            List<string> list = resviewService.queryItemCodesByPlanCode(this.codeTextBox.Text);
            if (list != null && list.Count != 0)
            {
                Yss.KMessage.MessageDialog messG = new Yss.KMessage.MessageDialog();
                messG.Show("方案代码已存在！", "消息", MessageBoxButtons.OK);
                return;
            }

            ////保存方案
            dataService.insert(pojos);
            Yss.KMessage.MessageDialog mess = new Yss.KMessage.MessageDialog();
            mess.Show("操作成功！", "消息", MessageBoxButtons.OK);
            this.Close();
        }

        

        /// <summary>
        /// 删除
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnDelete_Click(object sender, EventArgs e)
        {
            if ("".Equals(this.code.Trim()))
            {
                Yss.KMessage.MessageDialog messS = new Yss.KMessage.MessageDialog();
                messS.Show("操作失败！未获取到方案信息！", "消息", MessageBoxButtons.OK);
                return;
            }

            resviewService.deleteByPlanCode(this.code);
            Yss.KMessage.MessageDialog mess = new Yss.KMessage.MessageDialog();
            mess.Show("操作成功！", "消息", MessageBoxButtons.OK);
            this.Close();
        }

    }
}
