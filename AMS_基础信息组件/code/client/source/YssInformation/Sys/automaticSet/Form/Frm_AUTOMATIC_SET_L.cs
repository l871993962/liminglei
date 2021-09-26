using System;
using System.Collections.Generic;
using System.Text;
using System.Windows.Forms;
using FAST.Common.Service.Dict.Pojo;
using FAST.Common.Service.Pojo;
using FAST.Core.BaseControl.Pojo;
using FAST.Core.Communication.Service;
using FAST.Core.Context;
using FAST.Core.CRUD.Form;
using FAST.Core.Util;
using FAST.Platform.User.Service;
using Yss.Controls;
using Yss.KMessage;
using Yss.KRichEx.AutoFilter.Events;
using Yss.KRichEx.AutoFilter.Model;
using Yss.KTable.Collections;
using Yss.KTable.Events;
using Yss.KTable.Models;
using YssInformation.Support.Sys.automaticSet.Pojo;
using YssInformation.Support.Sys.automaticSet.Service;
using FAST.Common.Service.Services.Base;
using System.Collections;

namespace YssInformation.Sys.automaticSet.Form
{
    /// <summary>
    /// Frm_AUTOMATIC_SET_L
    /// </summary>
    public partial class Frm_AUTOMATIC_SET_L : FrmBaseList
    {
        /// <summary>
        /// 当前分页
        /// </summary>
        public string currPage = "外部券商";

        /// <summary>
        /// set窗体
        /// </summary>
        private Frm_AUTOMATIC_SET_S frmSet = null;

        /// <summary>
        /// 解析类
        /// </summary>
        private TableListLoader tableListLoader = null;

        /// <summary>
        /// paraDictCon
        /// </summary>
        private Dictionary<string, string> paraDictCon = new Dictionary<string, string>();

        /// <summary>
        /// 构造器
        /// </summary>
        public Frm_AUTOMATIC_SET_L()
        {
            this.bUseMVCService = true;
            this.bUseMVCServiceLeft = true;
            tableListLoader = new TableListLoader();
            InitializeComponent();
            this.tbFilter.Rows[1].Cells[4].Text = "";
            this.tbFilter.Rows[1].Cells[5].InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
        }

        /// <summary>
        /// 重写A区配置信息
        /// </summary>
        public override AreaAConfigInfo AreaAConfigInfo
        {
            get
            {
                AreaAConfigInfo areaAConfigInfo = new AreaAConfigInfo();
                areaAConfigInfo.ShowCheckBox = true;
                areaAConfigInfo.ShowPageGroups = false;
                areaAConfigInfo.ShowPortInCommonUse = false;
                return areaAConfigInfo;
            }
        }

        /// <summary>
        /// 重写窗体load事件，添加业务类型设置按钮
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_AUTOMATIC_SET_Load(object sender, EventArgs e)
        {
            this.addButton();
            this.addInterfaceButton();
        }

        /// <summary>
        /// 添加业务类型设置按钮
        /// </summary>
        private void addButton()
        {
            ClsButtonInfo btnConfigSet = new ClsButtonInfo();
            btnConfigSet.Name = "btnConfigSetNew";
            btnConfigSet.Text = "业务类型设置";
            btnConfigSet.Tooltip = "业务类型设置";
            btnConfigSet.Image = FAST.Resource.Resource.btnConfigSet;
            btnConfigSet.ClickEvent += new System.EventHandler(this.btnConfigSetNew_Click);
            this.btnBar.addButton(btnConfigSet, 20);
            this.btnBar.setButtonEnabled(btnConfigSet.Name, true);
        }

        /// <summary>
        /// 业务类型设置按钮点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnConfigSetNew_Click(object sender, EventArgs e)
        {
            Frm_AUTOMATIC_SET_TYPE_S form = null;
            if ("外部渠道组合路径设置".Equals(currPage))
            {
                form = new Frm_AUTOMATIC_SET_TYPE_S("WBQD_TYPE");
            }
            else
            {
                form = new Frm_AUTOMATIC_SET_TYPE_S("WBQS_TYPE");
            }
            form.FormBaseListView = this;
            form.initForm(this);
            form.showInfoMVC(null);
            form.ShowDialog();
        }

        /// <summary>
        /// 添加接口分类设置按钮
        /// </summary>
        private void addInterfaceButton()
        {
            ClsButtonInfo btnConfigSet = new ClsButtonInfo();
            btnConfigSet.Name = "btnInterfaceSetNew";
            btnConfigSet.Text = "接口分类设置";
            btnConfigSet.Tooltip = "接口分类设置";
            btnConfigSet.Image = FAST.Resource.Resource.btnConfigSet;
            btnConfigSet.ClickEvent += new System.EventHandler(this.btnInterfaceSetNew_Click);
            this.btnBar.addButton(btnConfigSet, 21);
            this.btnBar.setButtonEnabled(btnConfigSet.Name, false);
        }

        /// <summary>
        /// 接口分类设置按钮点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnInterfaceSetNew_Click(object sender, EventArgs e)
        {
            Frm_AUTOMATIC_SET_INTE_TYPE_S form = new Frm_AUTOMATIC_SET_INTE_TYPE_S();
            form.FormBaseListView = this;
            form.initForm(this);
            form.showInfoMVC(null);
            form.ShowDialog();
        }

        /// <summary>
        /// 明细业务类型下拉框取值
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void selBusinessCode_BeforeDropDownClick(object sender, DropDownEventArgs e)
        {
            e.Cancel = true;
            if (this.selBusinessCode.Items.Count > 0)
            {
                return;
            }

            if ("外部券商".Equals(currPage))
            {
                IAutomaticSetService automaticSetService = ServiceFactory.createService<IAutomaticSetService>();
                List<Vocabulary> businessTypeVoc = automaticSetService.getDataListByType("WBQS_TYPE");
                foreach (Vocabulary voc in businessTypeVoc)
                {
                    KTableEntity entity = new KTableEntity(voc.C_DV_CODE + "_" + voc.C_DV_NAME, voc.C_DV_CODE);
                    this.selBusinessCode.Items.Add(entity);
                }
            }
            else if ("责任会计".Equals(currPage))
            {
                IUserService userService = ServiceFactory.createService<IUserService>();
                List<User> userList = userService.queryAllUsers();
                foreach (User user in userList)
                {
                    KTableEntity entity = new KTableEntity(user.C_USER_CODE + "_" + user.C_USER_NAME, user.C_USER_CODE);
                    this.selBusinessCode.Items.Add(entity);
                }
            }
            else if ("外部渠道组合路径设置".Equals(currPage))
            {
                IAutomaticSetService automaticSetService = ServiceFactory.createService<IAutomaticSetService>();
                List<Vocabulary> businessTypeVoc = automaticSetService.getDataListByType("WBQD_TYPE");
                foreach (Vocabulary voc in businessTypeVoc)
                {
                    KTableEntity entity = new KTableEntity(voc.C_DV_CODE + "_" + voc.C_DV_NAME, voc.C_DV_CODE);
                    this.selBusinessCode.Items.Add(entity);
                }
            }
            else if ("估值指标".Equals(currPage))
            {
                IAutomaticSetPathService service = ServiceFactory.createService<IAutomaticSetPathService>();
                List<AutomaticSetPojo> list = service.getAllIndex();
                foreach (AutomaticSetPojo pojo in list)
                {
                    KTableEntity entity = new KTableEntity(pojo.C_INDEX_NAME + " " + pojo.C_INDEX_ORDER, pojo.C_INDEX_CODE);
                    this.selBusinessCode.Items.Add(entity);
                }
            }
        }

        /// <summary>
        /// 产品业务分类下拉框取值
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void selProductCode_BeforeDropDownClick(object sender, DropDownEventArgs e)
        {
            e.Cancel = true;
            if (this.selProductCode.Items.Count > 0)
            {
                return;
            }

            if ("外部渠道组合路径设置".Equals(currPage))
            {
                IAutomaticSetPathService automaticSetPathService = ServiceFactory.createService<IAutomaticSetPathService>();
                List<Vocabulary> productTypeVoc = automaticSetPathService.getAllProductType();
                int i = 0;
                foreach (Vocabulary voc in productTypeVoc)
                {
                    i++;
                    voc.C_DV_CODE = i.ToString();
                    KTableEntity entity = new KTableEntity(voc.C_DV_NAME, voc.C_DV_CODE);
                    this.selProductCode.Items.Add(entity);
                }
            }
        }

        /// <summary>
        /// OnGetParaAssemble
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>Dictionary</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            if ("外部券商".Equals(currPage))
            {
                paraDict.Add("ARRAY_C_BUSINESS_TYPE_CODE", "WBQS_TYPE");
            }
            else if ("责任会计".Equals(currPage))
            {
                paraDict.Add("ARRAY_C_BUSINESS_TYPE_CODE", "ZRKJ_TYPE");
            }
            if (null != this.selBusinessCode.Value && this.selBusinessCode.Value.Trim().Length != 0)
            {
                paraDict.Add("ARRAY_C_BUSINESS_CODE", selBusinessCode.Value.Replace("|", ","));
            }
            string search = yssBuildLeftCheckRowsStr("portfolio");
            search = search.Replace("'", "");
            if (!string.IsNullOrEmpty(search) && !"".Equals(search))
            {
                paraDict.Add("ARRAY_C_PORT_CODE", search);
            }
            else
            {
                if ("责任会计".Equals(currPage) && this.selBusinessCode.CheckedItems.Count == 0)
                {
                    StringBuilder buf = new StringBuilder();
                    List<Port> portList = new List<Port>();
                    portList.AddRange(getLeftPort(this.TableLeftMain.Rows));
                    foreach (Port port in portList)
                    {
                        string portCode = port.C_PORT_CODE;
                        buf.Append(portCode).Append(',');
                    }
                    if (buf.Length > 0)
                    {
                        buf.Remove(buf.Length - 1, 1);
                        paraDict.Add("ALL_C_PORT_CODE", buf.ToString());
                    }
                }
            }
            
            return paraDict;
        }

        /// <summary>
        /// tabPageClick
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void tabPageClick(object sender, Yss.Controls.TabPageEventArgs e)
        {
            string newPage = e.NewPage.Text;
            if (!currPage.Equals(newPage))
            {
                this.selBusinessCode.CheckedItems.Clear();
                this.selBusinessCode.Items.Clear();
                this.selBusinessCode.Value = "";
                this.selBusinessCode.Text = "";
                this.tbMain.Clear();
                currPage = newPage;
            }
            
            if ("外部券商".Equals(newPage))
            {
                this.btnBar.setButtonEnabled("btnConfigSetNew", true);
                this.btnBar.setButtonEnabled("btnInterfaceSetNew", false);
                this.tbFilter.Rows[1].Cells[4].Text = "";
                this.tbFilter.Rows[1].Cells[5].InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                tabPageWbqs.Controls.Add(this.tbMain);
                this.cell2.Text = "明细业务类型：";
            }
            else if ("责任会计".Equals(newPage))
            {
                this.btnBar.setButtonEnabled("btnConfigSetNew", false);
                this.btnBar.setButtonEnabled("btnInterfaceSetNew", false);
                this.tbFilter.Rows[1].Cells[4].Text = "";
                this.tbFilter.Rows[1].Cells[5].InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                tabPageZrkj.Controls.Add(this.tbMain);
                this.cell2.Text = "责任会计：";
            }
            else if ("外部渠道组合路径设置".Equals(newPage))
            {
                this.btnBar.setButtonEnabled("btnConfigSetNew", true);
                this.btnBar.setButtonEnabled("btnInterfaceSetNew", true);
                tabPageQsmszhljsz.Controls.Add(this.tbMain);
                this.tbFilter.Rows[1].Cells[4].Text = "产品业务分类：";
                this.tbFilter.Rows[1].Cells[5].InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.ShowAlways;
                this.cell2.Text = "明细业务类型：";
            }
            else if ("估值指标".Equals(newPage))
            {
                this.btnBar.setButtonEnabled("btnConfigSetNew", false);
                this.btnBar.setButtonEnabled("btnInterfaceSetNew", false);
                this.tbFilter.Rows[1].Cells[4].Text = "";
                this.tbFilter.Rows[1].Cells[5].InnerControlView = Yss.KTable.Enums.InnerControlViewStyle.HideAlways;
                tabPageGzzb.Controls.Add(this.tbMain);
                this.cell2.Text = "估值指标：";
            }
        }
          
        /// <summary>
        /// 新增
        /// </summary>   
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnNew_Click(object sender, EventArgs e)
        {
            if (this.frmSet != null)
            {
                this.frmSet.Close();
                this.frmSet.Dispose();
            }

            if (this.frmSet == null || this.frmSet.IsDisposed)
            {
                if (!"外部渠道组合路径设置".Equals(currPage))
                {
                    this.status = ClsEnums.StatusSetting.YssAdd;
                    this.frmSet = new Frm_AUTOMATIC_SET_S();
                    frmSet.FormBaseListView = this;
                    frmSet.initForm(this);
                    frmSet.ShowDialog();
                    this.btnSearch_Click(sender, e);
                }
                else if ("外部渠道组合路径设置".Equals(currPage))
                {
                    this.status = ClsEnums.StatusSetting.YssAdd;
                    Frm_AUTOMATIC_SET_INSERT_S form = new Frm_AUTOMATIC_SET_INSERT_S();
                    form.FormBaseListView = this;
                    form.initForm(this);
                    form.ShowDialog();
                    this.btnSearch_Click(sender, e);
                }
            }          
        }

        /// <summary>
        /// 查询
        /// </summary>   
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        public override void btnSearch_Click(object sender, EventArgs e)
        {
            if ("外部渠道组合路径设置".Equals(currPage) || "估值指标".Equals(currPage))
            {
                IAutomaticSetPathService automaticSetPathService = ServiceFactory.createService<IAutomaticSetPathService>();
                ///查询参数
                Dictionary<string, string> paraDict = new Dictionary<string, string>();
                paraDict.Add("dataClass", "AutomaticSetPathPojo");

                if (this.btnBar.ShowRefreshStatus)
                {
                    paraDict.Remove("CHECK_STATE");
                    string lcCheckedButton = this.btnBar.GetCheckedSearchButtonName();
                    if (lcCheckedButton == "btnSearchUnAudit")
                    {
                        paraDict.Add("CHECK_STATE", "0");
                    }
                    else if (lcCheckedButton == "btnSearchAudit")
                    {
                        paraDict.Add("CHECK_STATE", "1");
                    }
                }

                System.Windows.Forms.ToolStripDropDownItem pojo = sender as ToolStripDropDownItem;
                string checkStatus = pojo.DropDown.OwnerItem.Text;

                //通过查询按钮获取对应的审核状态
                if (!string.IsNullOrEmpty(checkStatus) && !"查询".Equals(checkStatus))
                {
                    paraDict.Remove("CHECK_STATE");
                    if ("已审数据".Equals(checkStatus))
                    {
                        paraDict.Add("CHECK_STATE", "1");
                    }
                    else if ("未审数据".Equals(checkStatus))
                    {
                        paraDict.Add("CHECK_STATE", "0");
                    }
                }

                ///下拉框条件参数
                if ("外部渠道组合路径设置".Equals(currPage))
                {
                    if (null != this.selBusinessCode.Value && this.selBusinessCode.Value.Trim().Length != 0)
                    {
                        paraDict.Add("ARRAY_C_CHANEL_CODE", selBusinessCode.Value.Replace("|", ","));
                    }
                    if (null != this.selProductCode.Text && this.selProductCode.Text.Trim().Length != 0)
                    {
                        paraDict.Add("ARRAY_C_PRODUCT_CODE", selProductCode.Text.Replace("|", ","));
                    }
                    paraDict.Add("C_BUSINESS_TYPE_CODE", " ");
                }
                else if ("估值指标".Equals(currPage))
                {
                    if (null != this.selBusinessCode.Value && this.selBusinessCode.Value.Trim().Length != 0)
                    {
                        paraDict.Add("ARRAY_C_INDEX_CODE", selBusinessCode.Value.Replace("|", ",")); 
                    }
                    paraDict.Add("C_BUSINESS_TYPE_CODE", "GZZB");
                }
                string search = yssBuildLeftCheckRowsStr("portfolio");
                search = search.Replace("'", "");
                if (!string.IsNullOrEmpty(search) && !"".Equals(search))
                {
                    paraDict.Add("ARRAY_C_PORT_CODE", search);
                }

                paraDictCon = paraDict;

                PageInation pageIn = new PageInation();
                pageIn = (PageInation)JsonUtil.toObject(JsonUtil.toJson(this.page), pageIn.GetType());
                QueryRes result = automaticSetPathService.queryByCondition(paraDict, pageIn);
                this.tableListLoader.loadListTable(this.tbMain, result, true, true);
                this.setPageInationMVC(result.PageInfo);
                this.page.CurrPage = result.PageInfo.CurrPage;
                this.page.PageCount = result.PageInfo.PageCount;
                this.page.PageSize = result.PageInfo.PageSize;

                if (sender is ToolStripDropdownButton)
                {
                    ToolStripDropdownButton item = sender as ToolStripDropdownButton;
                    string itemName = item.Name;


                    //BUG #221465 【平安资产】债券基本信息查询下拉选项保存问题。张绍林-20180926
                    if (itemName.Equals("btnRefresh"))
                    {
                        itemName = this.btnBar.GetCheckedSearchButtonName();
                    }
                    this.btnBar.setRefreshBtnChecked(itemName);
                    paraDict = this.getCheckStatusQueryCon(paraDict);
                }
            }
            else
            {
                base.btnSearch_Click(sender, e);
            }
        }

        /// <summary>
        /// 功能：获取B区列表数据
        /// </summary>
        /// <param name="paraDict">查询条件集合</param>
        /// <param name="isQueryData">是否传输数据</param>
        /// <returns>1</returns>
        public override QueryRes getMainListDataMVC(Dictionary<string, string> paraDict, bool isQueryData)
        {
            if ("外部渠道组合路径设置".Equals(currPage) || "估值指标".Equals(currPage))
            {
                IAutomaticSetPathService automaticSetPathService = ServiceFactory.createService<IAutomaticSetPathService>();
                PageInation pageIn = new PageInation();
                pageIn = (PageInation)JsonUtil.toObject(JsonUtil.toJson(this.page), pageIn.GetType());
                QueryRes result = automaticSetPathService.queryByCondition(paraDictCon, pageIn);
                this.tableListLoader.loadListTable(this.tbMain, result, true, true);
                this.setPageInationMVC(result.PageInfo);
                this.page.CurrPage = result.PageInfo.CurrPage;
                this.page.PageCount = result.PageInfo.PageCount;
                this.page.PageSize = result.PageInfo.PageSize;
                return result;
            }
            else
            {
                return base.getMainListDataMVC(paraDict, isQueryData);
            }
        }

        /// <summary>
        /// 获取数据总数量
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>总数量</returns>
        protected override int getDataTotal(Dictionary<string, string> paraDict)
        {
            if ("外部渠道组合路径设置".Equals(currPage) || "估值指标".Equals(currPage))
            {
                int total = 0;

                try
                {
                    IAutomaticSetPathService automaticSetPathService = ServiceFactory.createService<IAutomaticSetPathService>();
                    string number = automaticSetPathService.queryDataTotal(paraDict);
                    total = Convert.ToInt32(number);
                }
                catch (Exception ex)
                {
                    ClsLogger.Error(ex);
                }

                return total;
            }
            else
            {
                return base.getDataTotal(paraDict);
            }
        }

        /// <summary>
        /// 修改
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnEdit_Click(object sender, EventArgs e)
        {
            if ("责任会计".Equals(currPage))
            {
                string zrkj = "";
                RowCollection checkedRows = this.tbMain.CheckedRows;
                if (checkedRows.Count == 0)
                {
                    Row selectedRow = this.tbMain.SelectedRow;
                    AutomaticSetPojo automaticSetPojo = selectedRow.Tag as AutomaticSetPojo;
                    if (automaticSetPojo == null || "".Equals(automaticSetPojo.C_BUSINESS_CODE.Trim()))
                    {
                        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("责任会计为空，不允许修改"));
                        return;
                    }
                }

                foreach (Row checkedRow in checkedRows)
                {
                    AutomaticSetPojo automaticSetPojo = checkedRow.Tag as AutomaticSetPojo;
                    if (automaticSetPojo == null)
                    {
                        continue;
                    }

                    if ("".Equals(automaticSetPojo.C_BUSINESS_CODE.Trim()))
                    {
                        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("责任会计为空，不允许修改"));
                        return;
                    }

                    if ("".Equals(zrkj))
                    {
                        zrkj = automaticSetPojo.C_BUSINESS_CODE.Trim();
                    }
                    else if (!zrkj.Equals(automaticSetPojo.C_BUSINESS_CODE.Trim()))
                    {
                        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("您当前勾选的多个组合责任会计不一致，不允许批量修改"));
                        return;
                    }

                    if (1 == automaticSetPojo.AuditState)
                    {
                        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("勾选的数据均为未审核才能修改"));
                        return;
                    }
                }
                base.btnEdit_Click(sender, e);          
            }
            else if ("外部渠道组合路径设置".Equals(currPage))
            {
                Row selectedRow = this.tbMain.SelectedRow;
                if (selectedRow == null)
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("请先选择一条数据"));
                    return;
                }
                AutomaticSetPojo automaticSetPojo = selectedRow.Tag as AutomaticSetPojo;
                if (automaticSetPojo == null)
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("外部渠道组合路径设置为空，不允许修改"));
                    return;
                }
                if (1 == automaticSetPojo.AuditState)
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("勾选的数据均为未审核才能修改"));
                    return;
                }

                //跳转到保存页面修改数据
                this.status = ClsEnums.StatusSetting.YssEdit;
                Frm_AUTOMATIC_SET_INSERT_S form = new Frm_AUTOMATIC_SET_INSERT_S();
                form.FormBaseListView = this;
                form.initForm(this);
                form.showInfoMVC(automaticSetPojo);
                form.ShowDialog();
                this.btnSearch_Click(sender, e);
            }
            else
            {
                base.btnEdit_Click(sender, e);
            }
        
        }

        /// <summary>
        /// 复制
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnCopy_Click(object sender, EventArgs e)
        {
            Row selectedRow = this.tbMain.SelectedRow;
            if (selectedRow == null)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("请先选择一条数据"));
                return;
            }

            AutomaticSetPojo automaticSetPojo = selectedRow.Tag as AutomaticSetPojo;
            if ("责任会计".Equals(currPage))
            {
                if (automaticSetPojo == null || "".Equals(automaticSetPojo.C_BUSINESS_CODE.Trim()))
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("责任会计为空，不允许复制"));
                    return;
                }
                base.btnCopy_Click(sender, e);
            }
            else if ("外部渠道组合路径设置".Equals(currPage))
            {
                if (automaticSetPojo == null)
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("外部渠道组合路径设置为空，不允许复制"));
                    return;
                }

                //跳转到保存页面修改数据
                this.status = ClsEnums.StatusSetting.YssCopy;
                Frm_AUTOMATIC_SET_INSERT_S form = new Frm_AUTOMATIC_SET_INSERT_S();
                form.FormBaseListView = this;
                form.initForm(this);
                automaticSetPojo.Id = "";
                form.showInfoMVC(automaticSetPojo);
                form.ShowDialog();
                this.btnSearch_Click(sender, e);
            }
            else 
            {
                base.btnCopy_Click(sender, e);
            }         
        }

        /// <summary>
        /// 删除
        /// xuqiji 2011-01-20 删除代码，防止单条操作出现对象被释放错误
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnDelete_Click(object sender, EventArgs e)
        {
            Row selectedRow = this.tbMain.SelectedRow;
            if (selectedRow == null)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("请先选择一条数据"));
                return;
            }
            AutomaticSetPojo automaticSetPojo = selectedRow.Tag as AutomaticSetPojo;
            if ("责任会计".Equals(currPage))
            {
                if (automaticSetPojo == null || "".Equals(automaticSetPojo.C_BUSINESS_CODE.Trim()))
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("责任会计为空，不允许删除"));
                    return;
                }
                base.btnDelete_Click(sender, e);
            }
            else if ("外部渠道组合路径设置".Equals(currPage) || "估值指标".Equals(currPage))
            {
                MessageDialog dlg = new MessageDialog();

                //为空提示
                RowCollection checkedRows = this.tbMain.CheckedRows;
                if (checkedRows.Count == 0)
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("请勾选一条数据删除"));
                    return;
                }

                if (automaticSetPojo == null && "外部渠道组合路径设置".Equals(currPage))
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("外部渠道组合路径设置为空，不允许删除"));
                    return;
                }

                if (automaticSetPojo == null && "估值指标".Equals(currPage))
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("估值指标为空，不允许删除"));
                    return;
                }  

                try
                {
                    if (DialogResult.No == YssMessageBox.ShowQuestion("是否要将选中的记录执行[删除]操作？"))
                    {
                        return;
                    }

                    List<AutomaticSetPojo> list = new List<AutomaticSetPojo>();
                    DialogResult res = new DialogResult();
                    //发现有审核数据要弹窗的计数器
                    int count = 0;
                    //批量删除
                    foreach (Row checkedRow in checkedRows)
                    {
                        AutomaticSetPojo pojo = checkedRow.Tag as AutomaticSetPojo;
                        if (pojo == null)
                        {
                            continue;
                        }

                        if (1 == pojo.AuditState && count == 0)
                        {
                            res = YssMessageBox.ShowQuestion("在执行[删除]操作中含有已审核数据，是否继续操作？如果继续，已审核数据不会被删除。");
                            if (DialogResult.No == res)
                            {
                                return;
                            }
                            else
                            {
                                count ++;
                                continue;
                            }
                        }
                        //只删除未审核的数据
                        if (0 == pojo.AuditState)
                        {
                            list.Add(pojo);
                        }
                    }

                    if (list.Count > 0)
                    {
                        //执行删除
                        IAutomaticSetPathService automaticSetPathService = ServiceFactory.createService<IAutomaticSetPathService>();
                        string result = automaticSetPathService.deleteById(list);
                        dlg.Show("删除成功", "提示", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    }
                }
                catch (Exception ex)
                {
                    dlg.Show(ex.Message, "删除失败", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
                //重新查询
                this.btnSearch_Click(sender, e);
            }
            else
            {
                base.btnDelete_Click(sender, e);
            }
           
        }

        /// <summary>
        /// 审核
        /// xuqiji 2011-01-20 删除代码，防止单条操作出现对象被释放错误
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnAudit_Click(object sender, EventArgs e)
        {
            Row selectedRow = this.tbMain.SelectedRow;
            if (selectedRow == null)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("请先选择一条数据"));
                return;
            }
            AutomaticSetPojo automaticSetPojo = selectedRow.Tag as AutomaticSetPojo;
            if ("责任会计".Equals(currPage))
            {
                if (automaticSetPojo == null || "".Equals(automaticSetPojo.C_BUSINESS_CODE.Trim()))
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("责任会计为空，不允许审核"));
                    return;
                }

                base.btnAudit_Click(sender, e);
            }
            else if ("外部渠道组合路径设置".Equals(currPage) || "估值指标".Equals(currPage))
            {
                MessageDialog dlg = new MessageDialog();
                if (automaticSetPojo == null && "外部渠道组合路径设置".Equals(currPage))
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("外部渠道组合路径设置为空，不允许审核"));
                    return;
                }

                if (automaticSetPojo == null && "估值指标".Equals(currPage))
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("估值指标为空，不允许审核"));
                    return;
                }

                //审核操作   
                try
                {
                    RowCollection checkedRows = this.tbMain.CheckedRows;

                    if (checkedRows.Count == 0)
                    {
                        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("请勾选一条数据审核"));
                        return;
                    }

                    if (DialogResult.No == YssMessageBox.ShowQuestion("是否要将选中的记录执行[审核]操作？"))
                    {
                        return;
                    }

                    IAutomaticSetPathService automaticSetPathService = ServiceFactory.createService<IAutomaticSetPathService>();

                    List<AutomaticSetPojo> pojoList = new List<AutomaticSetPojo>();
                    //审核数据计数器
                    int count = 0;
                    foreach (Row checkedRow in checkedRows)
                    {
                        AutomaticSetPojo pojo = checkedRow.Tag as AutomaticSetPojo;

                        if (0 == pojo.AuditState)
                        {
                            pojo.AuditState = 1;
                            pojo.OperUser = ClsContext.currentUser.C_USER_CODE;
                            pojoList.Add(pojo);
                            count ++;
                        }
                    }
                    string result = automaticSetPathService.auditById(pojoList);

                    if (count > 0)
                    {
                        dlg.Show("[自动化业务设置][审核]操作成功！", "提示", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    }
                }
                catch (Exception ex)
                {
                    dlg.Show(ex.Message, "审核失败", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
                finally
                {
                    this.btnSearch_Click(sender, e);
                }
            }
            else
            {
                base.btnAudit_Click(sender, e);
            }
        }


        /// <summary>
        /// 重写反审核
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        protected override void btnUnAudit_Click(object sender, EventArgs e)
        {
            Row selectedRow = this.tbMain.SelectedRow;
            if (selectedRow == null)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("请先选择一条数据"));
                return;
            }
            AutomaticSetPojo automaticSetPojo = selectedRow.Tag as AutomaticSetPojo;

            if ("外部渠道组合路径设置".Equals(currPage) || "估值指标".Equals(currPage))
            {
                MessageDialog dlg = new MessageDialog();
                if (automaticSetPojo == null && "外部渠道组合路径设置".Equals(currPage))
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("外部渠道组合路径设置为空，不允许反审核"));
                    return;
                }

                if (automaticSetPojo == null && "估值指标".Equals(currPage))
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("估值指标设置为空，不允许反审核"));
                    return;
                }

                //反审核操作   
                try
                {
                    IAutomaticSetPathService automaticSetPathService = ServiceFactory.createService<IAutomaticSetPathService>();

                    RowCollection checkedRows = this.tbMain.CheckedRows;
                    if (checkedRows.Count == 0)
                    {
                        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("请先勾选一条数据"));
                        return;
                    }

                    if (DialogResult.No == YssMessageBox.ShowQuestion("是否要将选中的记录执行[反审核]操作？"))
                    {
                        return;
                    }

                    List<AutomaticSetPojo> pojoList = new List<AutomaticSetPojo>();
                    //反审核数据计数器
                    int count = 0;
                    foreach (Row checkedRow in checkedRows)
                    {
                        automaticSetPojo = checkedRow.Tag as AutomaticSetPojo;

                        if (1 == automaticSetPojo.AuditState)
                        {
                            automaticSetPojo.AuditState = 0;
                            pojoList.Add(automaticSetPojo);
                            count ++;
                        }
                    }

                    string result = automaticSetPathService.unAuditById(pojoList);

                    if (count > 0)
                    {
                        dlg.Show("[自动化业务设置][反审核]操作成功！", "提示", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
                    }
                }
                catch (Exception ex)
                {
                    dlg.Show(ex.Message, "反审核失败", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
                finally
                {
                    this.btnSearch_Click(sender, e);
                }
            }
            else
            {
                base.btnUnAudit_Click(sender, e);
            }
        }

        /// <summary>
        /// 获取A区组合
        /// </summary>
        /// <param name="row"></param>
        /// <returns></returns>
        private List<Port> getLeftPort(RowCollection rows)
        {
            List<Port> portList = new List<Port>();
            foreach (Row row in rows)
            {
                Port port = row.Tag as Port;
                if (row.SubRows.Count == 0)
                {
                    portList.Add(port);
                }
                else
                {
                    if ("PORT_LAYER".Equals(port.C_DV_PORT_CODE))
                    {
                        portList.Add(port);
                    }
                    portList.AddRange(getLeftPort(row.SubRows));
                }
            }
            return portList;
        }

        /// <summary>
        /// KTable 双击事件，仅用于派生类的扩展。
        /// 数据浏览
        /// </summary>
        /// <param name="sender">当前双击的行</param>
        /// <param name="e">参数</param>
        protected override void tbMain_RowDoubleClicked(object sender, RowEventArgs e)
        {
            Row clickedRow = sender as Row;
            AutomaticSetPojo automaticSetPojo = clickedRow.Tag as AutomaticSetPojo;
            if (automaticSetPojo == null || "".Equals(automaticSetPojo.C_BUSINESS_CODE.Trim()))
            {
                return;
            }

            base.tbMain_RowDoubleClicked(sender, e);
        }
    }
}
