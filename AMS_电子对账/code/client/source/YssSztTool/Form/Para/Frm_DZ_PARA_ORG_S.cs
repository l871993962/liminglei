using System;
using System.Collections.Generic;
using System.Text;
using System.Collections;
using Yss.KTable.Models;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Context;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Dict.Pojo;
using FAST.Core.Communication.Service;
using FAST.Core.Exceptions;
using YssProductInfo.Support.Ab.Port.Service;
using YssSztTool.Pojo.Para;
using Yss.KRichEx.AutoFilter.Model;
using YssSztTool.Service.Para;
using FAST.Core.BaseControl.Fun;


namespace YssSztTool.Form.Para
{
    public partial class Frm_DZ_PARA_ORG_S : FrmBaseSet
    {
        /////// <summary>
        /////// 组合关联的接口
        /////// </summary>
        ////private IPortRelaService portRelaService = null;

        /////// <summary>
        /////// 关联机构的接口
        /////// </summary>
        ////IPortRelaOrganServcie relaService = null;

        private IDzPortRelaService dzPortRelaService = null;

        /// <summary>
        /// 解析器
        /// </summary>
        private TableListLoader tableListLoader = null;

        /// <summary>
        /// 组合数据服务
        /// </summary>
        protected IPortService portService = null;

        /// <summary>
        /// 所有权限组合
        /// </summary>
        private List<BasePojo> rightPortList = null;

        public Frm_DZ_PARA_ORG_S()
        {
            bUseMVCService = true;
            InitializeComponent();
            clsInterface = new ClsInterface();
        }

        /// <summary>
        /// 初始化
        /// </summary>
        protected override void initServiceMVC()
        {
            base.initServiceMVC();
            ////relaService = ServiceFactory.createService<IPortRelaOrganServcie>();
            ////portRelaService = ServiceFactory.createService<IPortRelaService>();
            portService = ServiceFactory.createService<IPortService>();
            dzPortRelaService = ServiceFactory.createService<IDzPortRelaService>();
            this.dataService = dzPortRelaService;
        }

        /// <summary>
        /// 加载完之后，加载组合
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void FrmBase_Load(object sender, EventArgs e)
        {
            base.FrmBase_Load(sender, e);
            loadPortData();
        }

        /// <summary>
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写.
        /// </summary>
        /// <returns>返回查询条件</returns>
        private Dictionary<string, string> yssGetCond()
        {
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("C_TGH_CODE", this.cboTgh.Value);
            if (null != this.cboOrgnType.Value && this.cboOrgnType.Value.Trim().Length != 0)
            {
                paraDict.Add("C_DV_TYPE_CODE", this.cboOrgnType.Value);
            }
            paraDict.Add("dataClass", "Org");
            return paraDict;
        }

        /// <summary>
        /// 封装数据
        /// </summary>
        /// <returns>ArrayList</returns>
        public override ArrayList yssGetObjListMVC()
        {
            ArrayList pojoList = new ArrayList();
            //PortRela portRela = null;
            try
            {
                foreach (Row row in this.table1.Rows)
                {
                    getPortRela(row, pojoList);
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }

            return pojoList;
        }

        /// <summary>
        /// 行
        /// </summary>
        /// <param name="row">row</param>
        /// <param name="pojoList">pojoList</param>
        private void getPortRela(Row row, ArrayList pojoList)
        {
            if (row.SubRows != null && row.SubRows.Count > 0)
            {
                foreach (Row subRow in row.SubRows)
                {
                    getPortRela(subRow, pojoList);
                }
            }

            Port port = row.Tag as Port;
            if (port.DATA_TYPE.Equals(ClsConstant._ISPort) && row.Checked)
            {
                DzRelaOrgan portRela = new DzRelaOrgan();
                portRela.C_PORT_CODE = ((Port)row.Tag).C_PORT_CODE;
                portRela.C_DV_TYPE_CODE = this.cboOrgnType.Value;
                portRela.C_RELA_TYPE = "RELA_ORG";
                portRela.C_RELA_CODE = this.cboTgh.Value;
                portRela.Modifier = ClsContext.currentUser.C_USER_CODE;
                portRela.ModifyDate = DateTime.Now.ToString("yyyyMMdd HH:mm:ss");

                ////portRela.AuditState = 1;
                ////portRela.AuditDate = DateTime.Now.ToString("yyyyMMdd HH:mm:ss");
                pojoList.Add(portRela);
            }
        }

        /// <summary>
        /// 点击修改状态之后对控件
        /// 的状态进行修改
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_PORT_RELA_S_YssOnAfterEditClick(object sender, EventArgs e)
        {
            ////this.cboPort.YssReadOnly = true;
            this.cboTgh.YssReadOnly = true;
        }

        /// <summary>
        /// 数据发生改变向后台查询数据
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboOrgnType_SelectedValueChanged(object sender, EventArgs e)
        {
            try
            {
                if (status != ClsEnums.StatusSetting.YssCopy)
                {
                    if (null != this.cboTgh.Value)
                    {
                        ////loadData();
                    }
                }

            }
            catch (Exception ye)
            {
                ClsBaseException.DiscardException(ye);
                //// YssMessageBox.ShowDyanInformation("初始化界面报错", ye.Message, "信息提示", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-100007", _formFun, status));
            }
        }

        /// <summary>
        /// 重写
        /// </summary>
        public override void initControlStat()
        {
            base.initControlStat();
            DzRelaOrgan portRela = this.frmBaseViewList.yssGetSelTypeItemMVC() as DzRelaOrgan;
            this.btnBar.setButtonVisable(ClsButtonName.BtnCopy, false);
            this.btnBar.setButtonVisable(ClsButtonName.BtnEdit, false);
            //PortRela portRela =  as PortRela;
            if (portRela == null)
            {
                Frm_DZ_PARA_ORG_L frmList = frmBaseViewList as Frm_DZ_PARA_ORG_L;
                DzPara basePojo = frmList.MainDataPojo as DzPara;
                this.cboTgh.Value = basePojo.C_TGH_CODE;
                this.cboOrgnType.Value = "TRUSTEE";
            }
            else
            {
                this.cboTgh.Value = portRela.C_ORG_CODE;
                this.cboOrgnType.Value = portRela.C_DV_TYPE_CODE;
            }

            if (status == ClsEnums.StatusSetting.YssCopy)
            {
                this.cboTgh.YssReadOnly = true;
                ////this.cboOrgnType.YssReadOnly = false;
            }
            else if (status == ClsEnums.StatusSetting.YssEdit)
            {
                this.cboTgh.YssReadOnly = true;
                ////this.cboOrgnType.YssReadOnly = true;
            }
            else if (status == ClsEnums.StatusSetting.YssAdd)
            {
                this.cboTgh.YssReadOnly = true;
                ////this.cboOrgnType.YssReadOnly = true;
            }

        }

        /// <summary>
        /// 加载组合数据
        /// </summary>
        private void loadPortData()
        {
            QueryRes res = null;
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            try
            {
                if (tableListLoader == null)
                {
                    tableListLoader = new TableListLoader();
                }

                if (portService == null)
                {
                    return;
                }
                /////第1步 查询组合树
                //// 第2步 查询已经关联的组合
                ////判断状态，如果是新增，组合树种过滤所有已经关联的组合，1个组合不能关联多个托管行
                ////如果是修改，显示整个组合树，勾选已经关联的组合，后台先删再增
                ////如果是复制，已经关联的组合选择中，托管行可以修改，改变后组合树要修改，跟新增一样过滤
                ////新增修改 托管行不可修改，托管资质显示 只有当前托管行的资质。复制，都可以修改，也是先删再增
                res = new QueryRes();
                res.ListHeadList = getHeadInfo();
                ////res.DataList = portService.getDataList();
                ////BUG #129884 电子对账参数新增产品参数报错（自主开发）
                ////portService转换失败，换用IPortService edit by xuhanbing 2016-4-22
                ////BUG #170429 嘉实基金电子对账-电子对账参数设置，新增关联组合时初始化窗体失败 wangxinlei 20170824
                res.DataList = new List<BasePojo>();
                QueryRes queryTreeData = portService.getTreeViewDataRight("true", paraDict);
                res.DataList.AddRange(queryTreeData.DataList);

                /////根据托管行获取 获取已经设置了参数的组合
                string ports = null;
                if (this.cboTgh.Value != null)
                {
                    paraDict.Add("dataClass", "PortRelaOrgan");
                    paraDict.Add("C_RELA_CODE", this.cboTgh.Value);
                    /////bug114329 weijj 20150618 
                    ////STORY65389电子对账前台与估值核算解耦
                    ////PageInation page = new PageInation();
                    ////page.CurrPage = 1;
                    ////page.PageSize = 99999;
                    ////page.IsUsePage = true;
                    ////List<BasePojo> list = relaService.queryPortRelaOrgan(paraDict).DataList;
                    ////List<BasePojo> list = relaService.queryPortRelaOrganPage(paraDict, page).DataList;
                    ////BUG #170429 嘉实基金电子对账-电子对账参数设置，新增关联组合时初始化窗体失败 wangxinlei 20170824
                    ////STORY65389电子对账前台与估值核算解耦
                    ////QueryRes tempResult = relaService.queryPortRelaOrganPage(paraDict, page);
                    ////List<BasePojo> list = tempResult.DataList;
                    StringBuilder buf = new StringBuilder();
                    List<string> portlist = dzPortRelaService.queryPortCodesRelaOrgan(paraDict);
                    foreach (string portCode in portlist)
                    {
                        buf.Append(portCode).Append(",");
                    }

                    ports = buf.ToString();
                    List<BasePojo> list = new List<BasePojo>();
                    ////list.Clear();
                    //STORY #55269 【富国基金】支持电子对账参数设置支持多管理人
                    Frm_DZ_PARA_ORG_L frmList = frmBaseViewList as Frm_DZ_PARA_ORG_L;
                    DzPara basePojo = frmList.MainDataPojo as DzPara;
                    string manager = basePojo.C_MANAGE_CODE;
                    List<string> managers = null;
                    if (manager != null && !"".Equals(manager.Trim()))
                    {
                        Dictionary<string, string> paraManager = new Dictionary<string, string>();
                        paraManager["C_RELA_CODE"] = manager;
                        paraManager["C_DV_TYPE_CODE"] = "MANAGER";
                        paraManager["dataClass"] = "PortRelaOrgan";
                        //paraManager["ARRAY_C_PORT_CODE"] = portCodes;
                        managers = dzPortRelaService.queryPortCodesRelaOrgan(paraManager);
                    }

                    if (status == ClsEnums.StatusSetting.YssAdd)
                    {
                        //STORY #55269 【富国基金】支持电子对账参数设置支持多管理人
                        HashSet<string> setManager = new HashSet<string>();
                        if (manager != null && !"".Equals(manager.Trim()))
                        {
                            foreach (string portCode in managers)
                            {
                                setManager.Add(portCode);
                            }
                        }
                        foreach (BasePojo pojo in res.DataList)
                        {
                            if (!ports.Contains(((Port)pojo).C_PORT_CODE))
                            {
                                //STORY #55269 【富国基金】支持电子对账参数设置支持多管理人
                                if (manager != null && !"".Equals(manager.Trim()))
                                {
                                    if (setManager.Contains(((Port)pojo).C_PORT_CODE))
                                    {
                                        list.Add(pojo);
                                    }
                                    else if ("[root]".Equals(((Port)pojo).C_PORT_CODE_P))
                                    {
                                        list.Add(pojo);
                                    }
                                }
                                else
                                {
                                    list.Add(pojo);
                                }
                            }
                        }
                        res.DataList = list;
                    }
                }

                tableListLoader.loadTable(table1, res, true, true, ClsEnums.KTableDataShowMode.TreeMode);

                if (this.cboTgh.Value != null)
                {
                    if (status == ClsEnums.StatusSetting.YssEdit || status == ClsEnums.StatusSetting.YssCopy)
                    {
                        foreach (Row row in table1.Rows)
                        {
                            checkRow(ports, row);
                        }
                    }
                    else if (status == ClsEnums.StatusSetting.YssBrow)
                    {
                        table1.ReadOnly = false;
                        foreach (Row row in table1.Rows)
                        {
                            checkRow(ports, row);
                        }
                        table1.ReadOnly = true;
                    }
                }

                table1.Refresh();
            }
            catch (Exception ye)
            {
                ClsBaseException.DiscardException(ye);
                ////YssMessageBox.ShowDyanInformation("初始化界面报错", ye.Message, "信息提示", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-100007", _formFun, status));
            }
        }

        /// <summary>
        /// 选中行
        /// </summary>
        /// <param name="row">row</param>
        public void checkRow(string ports, Row row)
        {
            if (row.SubRows.Count > 0)
            {
                foreach (Row subRow in row.SubRows)
                {
                    checkRow(ports, subRow);
                }
            }

            Port port = row.Tag as Port;
            if (ports.Contains(port.C_PORT_CODE))
            {
                row.Checked = true;
            }
        }

        /// <summary>
        /// 获取用户列头信息
        /// </summary>
        /// <returns>ListHeadInfo</returns>
        protected List<ListHeadInfo> getHeadInfo()
        {
            List<ListHeadInfo> infoList = new List<ListHeadInfo>();
            ListHeadInfo headInfo = new ListHeadInfo();
            headInfo.Key = "C_PORT_NAME_ST";
            headInfo.Text = "名称";
            headInfo.Format = "";
            headInfo.Align = "left";
            headInfo.ShowConvert = "false";

            infoList.Add(headInfo);

            headInfo = new ListHeadInfo();
            headInfo.Key = "C_PORT_CODE";
            headInfo.Text = "代码";
            headInfo.Format = "";
            headInfo.Align = "left";
            headInfo.ShowConvert = "false";

            infoList.Add(headInfo);

            return infoList;
        }

        /// <summary>
        /// 展示数据
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(BasePojo pojo)
        {

            ////loadData();
        }

        /// <summary>
        /// 过滤不可选择的数据
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">sender</param>
        private void cboOrgnType_AfterDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            if (this.cboOrgnType.Items.Count > 0)
            {
                string tgzz = ""; ////托管资质
                ////查询托管行的资质，只能选当前托管行有的托管人资质
                if (this.cboTgh.SelectedItem != null)
                {
                    YssInformation.Support.Bi.AssociationOrgan.Pojo.Org org = this.cboTgh.SelectedItem.DataEntity as YssInformation.Support.Bi.AssociationOrgan.Pojo.Org;
                    tgzz += org.C_DV_TRUSTEE + "," + org.C_DV_TRUSTEE_SEC;
                }

                List<ControlEntity> dataList = new List<ControlEntity>();

                foreach (ControlEntity ce in cboOrgnType.Items)
                {
                    Vocabulary voc = ce.DataEntity as Vocabulary;
                    if (!tgzz.Contains(voc.C_DV_CODE))
                    {
                        dataList.Add(ce);
                    }
                }

                foreach (ControlEntity ce in dataList)
                {
                    cboOrgnType.Items.Remove(ce);
                }

                this.cboOrgnType.Refresh();
            }
        }

        /// <summary>
        /// 调整控件的状态
        ///    Added by ll 20120621
        /// </summary>
        protected override void YssChangeControlState()
        {
            clsInterface.setControlsStatus(tbMain, status);
            clsInterface.setControlsStatus(table1, status);
            if (status != ClsEnums.StatusSetting.YssBrow)
            {
                table1.Clear();
                loadPortData();
            }

            this.cboTgh.YssReadOnly = true;
        }


        /// <summary>
        /// 根据窗体状态执行相应的保存操作
        /// </summary>
        /// <param name="pojoList">操作的数据对象</param>
        /// <param name="status">窗体的打开状态</param>
        /// <returns>保存数据后后台返回的操作结果信息</returns>
        protected override string yssDoSetFormOperMVC(ArrayList pojoList, ClsEnums.StatusSetting status)
        {
            string operResult = "";
            switch (status)
            {
                case ClsEnums.StatusSetting.YssAdd:
                    foreach (object pojo in pojoList)
                    {
                        setAddOperPojoInfo((BasePojo)pojo);
                    }

                    operResult = dataService.insert(pojoList);
                    break;
                case ClsEnums.StatusSetting.YssCopy:
                    foreach (object pojo in pojoList)
                    {
                        setAddOperPojoInfo((BasePojo)pojo);
                    }

                    operResult = dzPortRelaService.delInsert(pojoList);
                    break;
                case ClsEnums.StatusSetting.YssEdit:
                    foreach (object pojo in pojoList)
                    {
                        setEditOperPojoInfo((BasePojo)pojo);
                    }

                    operResult = dzPortRelaService.delInsert(pojoList);
                    break;
                case ClsEnums.StatusSetting.YssDel:
                    foreach (object pojo in pojoList)
                    {
                        setDelOperPojoInfo((BasePojo)pojo);
                    }

                    operResult = dzPortRelaService.delByYwId(pojoList);
                    break;
                case ClsEnums.StatusSetting.YssAudit:
                    foreach (object pojo in pojoList)
                    {
                        setAuditOperPojoInfo((AuditableParamPojo)pojo);

                    }

                    DataFunction.setAuditStateByOperState(pojoList, status);
                    operResult = dataService.auditById(pojoList);
                    break;
                case ClsEnums.StatusSetting.YssUnAudit:
                    foreach (object pojo in pojoList)
                    {
                        setAuditOperPojoInfo((AuditableParamPojo)pojo);
                    }

                    DataFunction.setAuditStateByOperState(pojoList, status);
                    operResult = dataService.unAuditById(pojoList);
                    break;
                case ClsEnums.StatusSetting.YssBrow:
                    break;
            }

            return operResult;
        }


    }
}
