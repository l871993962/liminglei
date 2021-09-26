using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;

using FAST.Core.Context;

using FAST.Platform.Right.Fun;
using FAST.Core.BaseControl.Fun;

using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;
using System;
using System.Collections;
using System.Collections.Generic;



using Yss.KTable.Models;
using Yss.KRichEx.AutoFilter.Model;
using Yss.KMessage;
using System.Windows.Forms;
using YssProductInfo.Support.Ab.AssetsTree.Pojo;
using YssProductInfo.Support.Ab.AssetsTree.Service;
using System.IO;
using System.Xml;

namespace YssProductInfo.Ab.AssetsTree.Form
{
    /// <summary>
    /// 功能简介：板块分类信息设置，负责证券发行信息的增删改查等功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.13
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：chenyoulong
    /// 修改日期：20110307
    /// 修改简介：根据板块设置需求的重新设计，重新对板块设计进行开发
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         返回对象错误直接抛出异常
    /// </summary>
    public partial class Frm_ASSETS_TREE_S_A : FrmBaseSet
    {
        /// <summary>
        /// add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
        /// List窗体
        /// </summary>
        private FrmBaseList frmBaseViewList_B;  

        /// <summary>
        /// add by wwt 2018-12-20 BUG #235488 产品树形结构审核或者反审核会出现报错
        /// </summary>
        private ArrayList pojoList = new ArrayList();

        /// <summary>
        /// 分类规则
        /// STORY #72829 资产结构新增仅包含“存续期+待发行”的组合 
        /// add by yangru 20190717
        /// </summary>
        private AssetsTree_A_Rule assetsTreeARule = null;

        /// <summary>
        /// add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
        /// 构造方法
        /// </summary>
        /// <param name="frmBaseList">frmBaseList</param>
        public Frm_ASSETS_TREE_S_A(FrmBaseList frmBaseList)
        {
            this.frmBaseViewList_B = frmBaseList;
            bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_ASSETS_TREE_S_A()
        {
            bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 初始化界面控件.
        /// </summary>
        public override void yssInitCtlAttr()
        {
            if (bUseMVCService)
            {
                intCtrlProcMVC();
            }
            else
            {
                intCtrlProcNormal();
            }
        }

        /// <summary>
        /// 初始化界面控件.
        /// </summary>
        private void intCtrlProcNormal()
        {
            AssetsTree_A clsAssetsTreeA = null;
            try
            {
                clsAssetsTreeA = this.frmBaseViewList.getSelectedRowTagMVC(clsAssetsTreeA) as AssetsTree_A;
                if (null != clsAssetsTreeA)
                {
                    this.txtSectorCode.Text = clsAssetsTreeA.C_TR_CODE;
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 初始化界面控件.
        /// </summary>
        private void intCtrlProcMVC()
        {
            AssetsTree_A clsAssetsTreeA = null;
            try
            {
                ////BUG #330373 产品信息中A区新增后查询报错；A区选中类型后点击查询，审核反审核按钮有效有的无效
                if (null == this.cboFlGZ.Value)
                {
                    this.cboFlGZ.ExpandButtonVisible = false;
                    this.cboAutoZr.Visible = false;
                    this.tbMain.Rows[5].Cells[0].Text = "";
                    this.tbMain.Rows[5].Cells[1].InnerControl = null;
                }

                if (null == this.frmBaseViewList)
                {
                    ////add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
                    if (null != this.frmBaseViewList_B.tbMain.SelectedRow)
                    {
                        ////新增同级节点
                        if (this.frmBaseViewList_B.OtherParams == "sameLevel")
                        {
                            ////上级结构代码
                            this.cboSector.Value = ((AssetsTree_B)this.frmBaseViewList_B.tbMain.SelectedRow.Tag).C_TR_CODE_R;
                        }
                        else if (this.frmBaseViewList_B.OtherParams == "upLevel")
                        {
                            this.cboSector.Value = ((AssetsTree_B)this.frmBaseViewList_B.tbMain.SelectedRow.Tag).C_TR_CODE;
                        }
                    }
                    else
                    {
                        clsAssetsTreeA = this.frmBaseViewList_B.getSelectedRowTagMVC(clsAssetsTreeA) as AssetsTree_A;
                        ////zhoushuhang 20180319  BUG195989产品树形结构界面A区选中一条数据，点击B区的新增下级节点，弹出的界面上上级结构没有自动加载
                        if (null != clsAssetsTreeA)
                        {
                            ////新增下级节点
                            if (this.frmBaseViewList_B.OtherParams == "upLevel")
                            {
                                this.cboSector.Value = clsAssetsTreeA.C_TR_CODE;
                            }
                        }
                    }

                    if (this.tbMain.Rows[4].Visible)
                    {
                        this.tbMain.Rows[4].Visible = false;
                        this.Height -= this.tbMain.Rows[4].Height;
                    }
                }
                else
                {
                    clsAssetsTreeA = this.frmBaseViewList.getSelectedRowTagMVC(clsAssetsTreeA) as AssetsTree_A;

                    if (null != clsAssetsTreeA)
                    {
                        this.txtSectorCode.Text = clsAssetsTreeA.C_TR_CODE;
                        this.txtSectorName.Text = clsAssetsTreeA.C_TR_NAME;
                        this.cboFlGZ.Value = clsAssetsTreeA.C_DV_TR;
                        this.cboCode.Value = clsAssetsTreeA.C_USER;
                        ////add by yangru 20190717 STORY #72829 资产结构新增仅包含“存续期+待发行”的组合 
                        this.cboAutoZr.Value = clsAssetsTreeA.C_AUTO_ZR_TYPE;
                        this.assetsTreeARule = clsAssetsTreeA.AsetsTreeARule;
                        if (clsAssetsTreeA.C_TR_CODE_P.Equals("[root]"))
                        {
                            this.cboSector.Text = "";
                        }
                        else
                        {
                            this.cboSector.Value = clsAssetsTreeA.C_TR_CODE_P; // 上级板块代码
                        }

                        ////add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
                        this.cboUndis.Value = clsAssetsTreeA.C_DV_UN_DIS; // 显示未分配
                    }
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }

            ////应对Bug8280 新增操作时清空控件
            if (status == ClsEnums.StatusSetting.YssAdd && null != this.frmBaseViewList)
            {
                clsInterface.yssClearTableCtlValue(tbMain); //// 调整了代码位置到ClsInterface类中 byleeyu 20120618
                this.cboSector.YssReadOnly = true;
            }

        }


        /// <summary>
        /// 封装界面元素为pojo对象.
        /// －－－－修改记录－－－－.
        /// 当前版本：V4.5.0.2.
        /// 修改人：chenyoulong.
        /// 修改日期：20110307.
        /// 修改简介：将界面元素封装成对象的具体体现.
        /// </summary>
        /// <returns>由界面元素组成的对象.</returns>
        public override AuditableParamPojo faceInfoToObjMVC()
        {
            YssMessageBox.currentForm = this;
            AssetsTree_A clsAssetsTreeA = null;
            try
            {
                clsAssetsTreeA = new AssetsTree_A();

                clsAssetsTreeA.C_TR_CODE = this.txtSectorCode.Text;
                clsAssetsTreeA.C_TR_NAME = this.txtSectorName.Text;
                clsAssetsTreeA.C_DV_TR = this.cboFlGZ.Value;
                clsAssetsTreeA.C_USER = this.cboCode.Value;
                ////BUG #362180 光大理财-产品树形结构无法修改私有用户为公用
                if (this.cboCode.Value == null)
                {
                    clsAssetsTreeA.C_USER = " ";
                }

                if (this.cboSector.Value != null && this.cboSector.Text != "[root]")
                {
                    clsAssetsTreeA.C_TR_CODE_P = this.cboSector.Value; // 上级代码
                    clsAssetsTreeA.C_TR_CODE_R = (this.cboSector.SelectedItem.DataEntity as AssetsTree_A).C_TR_CODE_R;
                }
                else
                {
                    clsAssetsTreeA.C_TR_CODE_P = "[root]"; // 上级代码
                    clsAssetsTreeA.C_TR_CODE_R = this.txtSectorCode.Text;
                }

                ////add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
                clsAssetsTreeA.C_DV_UN_DIS = this.cboUndis.Value;
                ////add by yangru 20190717 STORY #72829 资产结构新增仅包含“存续期+待发行”的组合 
                clsAssetsTreeA.C_AUTO_ZR_TYPE = this.cboAutoZr.Value;
                if ("TREE_TYPE_CUS".Equals(this.cboFlGZ.Value))
                {
                    if (this.assetsTreeARule == null || ((this.assetsTreeARule.C_CPSJWD == null || this.assetsTreeARule.C_CPSJWD == "") && (this.assetsTreeARule.C_ZCSXWD == null || this.assetsTreeARule.C_ZCSXWD == "")))
                    {
                        throw new ClsBaseException("产品树形规则不能为空！");
                    }

                    clsAssetsTreeA.AsetsTreeARule = this.assetsTreeARule;
                }
                ////BUG #235488 产品树形结构审核或者反审核会出现报错
                pojoList.Add(clsAssetsTreeA);
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return clsAssetsTreeA;

        }


        /// <summary>
        /// 获取list中选中记录，为界面元素赋值，显示数据.
        /// －－－－修改记录－－－－.
        /// 当前版本：V4.5.0.2.
        /// 修改人：chenyoulong.
        /// 修改日期：20110307.
        /// 修改简介：给界面元素赋值的具体实现.
        /// </summary>
        /// <param name="pojo">数据对象</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            try
            {
                if (null != this.frmBaseViewList_B && this.frmBaseViewList_B.tbLeftMain.SelectedRow != null)
                {
                    AssetsTree_A clsAssetsTreeA = (AssetsTree_A)this.frmBaseViewList_B.tbLeftMain.SelectedRow.Tag;
                    if (null != clsAssetsTreeA)
                    {
                        this.txtSectorCode.Text = clsAssetsTreeA.C_TR_CODE;
                        this.txtSectorName.Text = clsAssetsTreeA.C_TR_NAME;
                        this.cboFlGZ.Value = clsAssetsTreeA.C_DV_TR;
                        this.cboCode.Value = clsAssetsTreeA.C_USER;
                        ////add by yangru 20190717 STORY #72829 资产结构新增仅包含“存续期+待发行”的组合 
                        this.cboAutoZr.Value = clsAssetsTreeA.C_AUTO_ZR_TYPE;
                        this.assetsTreeARule = clsAssetsTreeA.AsetsTreeARule;
                        if (clsAssetsTreeA.C_TR_CODE_P.Equals("[root]"))
                        {
                            this.cboSector.Text = "";
                        }
                        else
                        {
                            this.cboSector.Value = clsAssetsTreeA.C_TR_CODE_P; // 上级板块代码
                        }

                        ////add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
                        this.cboUndis.Value = clsAssetsTreeA.C_DV_UN_DIS; // 显示未分配
                    }
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }

        }

        /// <summary>
        /// 窗体加载方法
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ASSETS_TREE_S_A_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            dataService = (IServiceBus)ServiceFactory.createService(serviceType);
        }

        /// <summary>
        /// 1
        /// </summary>
        /// <param name="sender">2</param>
        /// <param name="e">3</param>
        private void cboSector_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            this.cboSector.QueryType = "FatherNode";
        }

        /// <summary>
        /// 删除前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ASSETS_TREE_S_A_YssOnBeforeDelClick(object sender, YssBeforeOperEventArgs e)
        {
            ////string search = "";
            AssertTreeATreeView plate = null;
            if (this.frmBaseViewList.tbLeftMain.SelectedRow != null)
            {
                plate = (AssertTreeATreeView)this.frmBaseViewList.tbLeftMain.SelectedRow.Tag;
                ////当树的子分类仅含有未分配时，允许删除该树型结构  2017-03-15 zhujinyang STORY36179
                if (this.frmBaseViewList.tbLeftMain.SelectedRow.SubRows.Count > 1)
                {
                    _formFun.C_FUN_CODE = "pd_assetsTree_A";
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("002", _formFun, status));
                    e.IsCancel = true;
                }
                else if (this.frmBaseViewList.tbLeftMain.SelectedRow.SubRows.Count == 1)
                {
                    e.IsCancel = false;
                }
                else
                {
                    Dictionary<string, string> paraDict = new Dictionary<string, string>();
                    paraDict.Add("C_TR_CODE", plate.C_TR_CODE);

                    ClsAssocia asc = ClsClzCfgMgr.getAssociaParam("pd_assetsTree_A");
                    Type serviceType = ReflectBase.YssGetType(asc.ServiceDllName, asc.ServiceName);
                    IAssertTree_AService postService = ServiceFactory.createService(serviceType) as IAssertTree_AService;
                    string strbool = postService.getListData(paraDict);
                    if ("true".Equals(strbool))
                    {
                        _formFun.C_FUN_CODE = "pd_assetsTree_A";
                        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, status));
                        e.IsCancel = true;
                    }
                }
            }
        }

        /// <summary>
        /// set界面保存数据方法
        /// </summary>
        /// <param name="status">当前窗体的打开状态</param>
        /// <returns>保存数据后后台返回的操作结果信息</returns>
        public override string yssFormOperationMVC(ClsEnums.StatusSetting status)
        {
            ////获取PojoList
            string result = "";
            if (status == ClsEnums.StatusSetting.YssAudit || status == ClsEnums.StatusSetting.YssUnAudit)
            {
                ////BUG #235488 产品树形结构审核或者反审核会出现报错
                if (null != frmBaseViewList)
                {
                    Row row = frmBaseViewList.tbLeftMain.SelectedRow;
                pojoList.Add(toTreeA(row));
                getLeftList(row, pojoList);
                }
            }
            else
            {
                pojoList = yssGetDataObjMVC(pojoList);
            }

            if (pojoList == null)
            {
                pojoList = new ArrayList();
            }

            if (pojoList.Count > 0)
            {
                result = yssDoSetFormOperMVC(pojoList, status);

            }

            if (status == ClsEnums.StatusSetting.YssAdd && pojoList.Count > 0)
            {
                if (ClsRetInfoDealer.isJsonInfo(result) && result.Trim() != string.Empty)
                {
                    ClsRetInfo retInfo = ClsRetInfoDealer.getReturnInfo(result);
                    if (retInfo.operRes == "Success")
                    {
                        AssetsTree_A view = (AssetsTree_A)pojoList[0];
                        CheckItem checkItem = new CheckItem(view.C_TR_NAME);
                        checkItem.Name = view.C_TR_CODE;
                        checkItem.Checked = true;
                        this.WriteAssItemsToConfig(checkItem, "CustomAssType");
                    }
                }
            }

            return result;
        }

        /// <summary>
        /// 写入配置文件
        /// </summary>
        /// <param name="checkItem">下拉选项</param>
        /// <param name="configName">文件名称</param>
        private void WriteAssItemsToConfig(CheckItem checkItem, string configName)
        {
            if (!Directory.Exists(ClsConstant.FILE_CustomSettingPath))
            {
                Directory.CreateDirectory(ClsConstant.FILE_CustomSettingPath);
            }

            if (checkItem != null)
            {
                ClsXmlAdmin xmlAdmin = new ClsXmlAdmin(ClsConstant.FILE_CustomSettingPath + "\\" + configName + ".xml");
                xmlAdmin.AutoSave = false;
                XmlNode xmlNodeAssType = xmlAdmin.getNode("AssType");
                ////XmlNodeList xmlNodeList = xmlAdmin.GetNodeList("AssType");

                if (xmlNodeAssType == null)
                {
                    xmlAdmin.addNode(string.Empty, "AssType");
                    XmlNode xmlNode = xmlAdmin.addNode("AssType", "Item");
                    xmlAdmin.addAttr(xmlNode, "Code", checkItem.Name);
                    xmlAdmin.addAttr(xmlNode, "Name", checkItem.Text);
                    xmlAdmin.addAttr(xmlNode, "Visible", "true");

                }
                else
                {
                    XmlNode xmlNode = xmlAdmin.GetNode("AssType/Item", "Code", checkItem.Name);
                    if (xmlNode != null)
                    {           ////存在节点
                        string strbool = xmlAdmin.GetAttrValue(xmlNode, "Visible");

                        if (checkItem.Checked)
                        {
                            xmlAdmin.ModfiyAttrValue(xmlNode, "Visible", "true");
                        }
                        else
                        {
                            xmlAdmin.ModfiyAttrValue(xmlNode, "Visible", "false");
                        }

                    }
                    else
                    {       ////不存在节点,则新增节点
                        XmlNode xmlNodeadd = xmlAdmin.addNode("AssType", "Item");
                        xmlAdmin.addAttr(xmlNodeadd, "Code", checkItem.Name);
                        xmlAdmin.addAttr(xmlNodeadd, "Name", checkItem.Text);
                        xmlAdmin.addAttr(xmlNodeadd, "Visible", "true");
                    }

                }

                ////this.WriteTableColumnsFrozen(tab, xmlAdmin);
                xmlAdmin.SaveXmlDocument();
                xmlAdmin.Dispose();
                xmlAdmin = null;
            }
        }

        /// <summary>
        /// 获取左边被选择子对象
        /// </summary>
        /// <param name="row">row</param>
        /// <param name="pojoList">pojoList</param>
        /// <returns>ArrayList</returns>
        protected ArrayList getLeftList(Row row, ArrayList pojoList)
        {
            foreach (Row subRow in row.SubRows)
            {
                if (subRow.Checked)
                {
                    AssetsTree_A clsAssetsTreeA = toTreeA(subRow);
                    pojoList.Add(clsAssetsTreeA);
                }

                if (subRow.SubRows.Count > 0)
                {
                    this.getLeftList(subRow, pojoList);
                }

            }

            return pojoList;
        }

        /// <summary>
        /// sdd
        /// </summary>
        /// <param name="row">row</param>
        /// <returns>AssetsTree_A</returns>
        public AssetsTree_A toTreeA(Row row)
        {
            AssetsTree_A view = (AssetsTree_A)row.Tag;
            AssetsTree_A clsAssetsTreeA = new AssetsTree_A();
            clsAssetsTreeA = new AssetsTree_A();

            clsAssetsTreeA.C_TR_CODE = view.C_TR_CODE;
            clsAssetsTreeA.C_TR_NAME = view.C_TR_NAME;
            clsAssetsTreeA.C_DV_TR = view.C_DV_TR;
            clsAssetsTreeA.C_TR_CODE_P = view.C_TR_CODE_P;
            clsAssetsTreeA.C_TR_CODE_R = view.C_TR_CODE_R;
            clsAssetsTreeA.Id = view.Id;
            clsAssetsTreeA.Modifier = view.Modifier;
            clsAssetsTreeA.ModifyDate = view.ModifyDate;
            clsAssetsTreeA.OperUser = view.OperUser;
            clsAssetsTreeA.C_USER = view.C_USER;

            return clsAssetsTreeA;

        }


        /// <summary>
        /// 根据窗体状态执行相应的保存操作
        /// </summary>
        /// <param name="pojoList">操作的数据对象</param>
        /// <param name="status">窗体的打开状态</param>
        /// <returns>保存数据后后台返回的操作结果信息</returns>
        protected virtual string yssDoSetFormOperMVC(ArrayList pojoList, ClsEnums.StatusSetting status)
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

                    operResult = dataService.insert(pojoList);
                    break;
                case ClsEnums.StatusSetting.YssEdit:
                    foreach (object pojo in pojoList)
                    {
                        setEditOperPojoInfo((BasePojo)pojo);
                    }

                    operResult = dataService.updateById(pojoList);
                    break;
                case ClsEnums.StatusSetting.YssDel:
                    foreach (object pojo in pojoList)
                    {
                        setDelOperPojoInfo((BasePojo)pojo);
                    }

                    operResult = dataService.deleteById(pojoList);
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

            ////ClsRetInfo cri = (ClsRetInfo)JsonUtil.toObject(operResult, typeof(ClsRetInfo));
            ClsRetInfo retInfo = ClsRetInfoDealer.getReturnInfo(operResult);
            List<string> idList = retInfo.cidenList;

            if (idList.Count > 0)
            {
                ////edit by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
                ////((Frm_ASSETS_TREE_L)this.frmBaseViewList).Id = idList[0];
                if (null == this.frmBaseViewList)
                {
                    ((Frm_ASSETS_TREE_L)this.frmBaseViewList_B).Id = idList[0];
                }
                else
                {
                    ((Frm_ASSETS_TREE_L)this.frmBaseViewList).Id = idList[0];
                }
            }

            return operResult;
        }

        /// <summary>
        /// 功能：设置审核、反审核pojo的公共信息
        /// </summary>
        /// <param name="pojo">数据对象</param>
        /// <returns>设置公共信息后的数据对象</returns>
        protected AuditableParamPojo setAuditOperPojoInfo(AuditableParamPojo pojo)
        {
            ////pojo.Modifier = ClsContext.currentUser.C_USER_CODE;
            pojo.OperUser = FAST.Core.Context.ClsContext.currentUser.C_USER_CODE;


            return pojo;
        }

        /// <summary>
        /// 加载上级组合下拉框数据
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboSector_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("dataClass", "AssetsTree_A");

            IAssertTree_AService ctrlSVC = ServiceFactory.createService<IAssertTree_AService>();
            QueryRes res = ctrlSVC.getTreeViewData(paraDict);
            foreach (BasePojo assetTree in res.DataList)
            {
                if ((assetTree as AssetsTree_A).C_TR_CODE != this.txtSectorCode.Text)
                {
                    ////2017-02-06 zhujinyang  36179 嘉实基金-新建一个产品树形结构时，可自动生成 “未分配”结构，将没有分配到结构下的组合默认放到“未分配”下 
                    ////选择上级节点时，不允许出现未分配节点
                    if ((assetTree as AssetsTree_A).C_TR_NAME != "未分配")
                    {
                        KTableEntity entity = new KTableEntity(assetTree);
                        e.Collection.Add(entity);
                    }
                }
            }

            ////指定控件不要自动去加载数据
            e.IsCancel = true;
        }

        /// <summary>
        /// 重写 Add By lj 2015-02-13 产品树形结构修改时---结构代码不允许编辑
        /// </summary>
        public override void initControlStat()
        {
            base.initControlStat();
            if (status == ClsEnums.StatusSetting.YssEdit)
            {
                txtSectorCode.YssReadOnly = true;
            }
            else if (status == ClsEnums.StatusSetting.YssAdd)
            {
                txtSectorCode.YssReadOnly = false;
            }
            //// add by yangru 20190721 STORY #72829 资产结构新增仅包含“存续期+待发行”的组合 
            if (status == ClsEnums.StatusSetting.YssBrow)
            {
                this.cboFlGZ.YssReadOnly = true;
            }
            else
            {
                this.cboFlGZ.YssReadOnly = false;
            }
        }

        /// <summary>
        /// 保存树形结构数据前处理
        /// add by shijian 2016-11-29 BUG #145892 产品树形结构修改是数据异常系统闪退
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ASSETS_TREE_S_A_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        {
            ////edit by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
            if (null != this.frmBaseViewList)
            {
                //// 首先检查当前节点是否是新增节点
                string code = this.txtSectorCode.Value;
                AssetsTree_A node = getNodeByCode(code);
                if (node != null)
                {
                    string codep = this.cboSector.Value;

                    //// 父节点不是根节点，才处理
                    if (!string.IsNullOrEmpty(codep) && !string.IsNullOrEmpty(codep.Trim()))
                    {
                        //// 检查本次保存的数据节点的父节点是否为当前节点的（直接/间接）子节点
                        bool bflag = isParentNodeChildNodeOfCurrendNode(code, codep);
                        if (bflag)
                        {
                            YssMessageBox.ShowCommonInfos(TransferErrorMessageUtil.getTransferException("不能将上层结构设为当前节点的直接或间接子节点!"));
                            //// 非法操作，取消保存
                            e.IsCancel = true;
                        }
                    }

                }
            }

            //// 新增节点不检查
            ////add by zhoushuhang 2018-10-9 BUG206147节点code不能与组合代码相同
            string[] portArray = ClsContext.DataRightList.ToArray();
            int id = Array.IndexOf(portArray, this.txtSectorCode.Text);
            if (id != -1)
            {
                //// 资产树节点code不能和组合代码相同，code相同时，点击保存给予提示
                Yss.CommonLib.ShowMessage("代码已存在：已存在与之相同的组合代码！", "警告", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                //// 取消保存
                e.IsCancel = true;
            }
        }


        /// <summary>
        /// 重写A区保存点击事件
        /// add by zhujinyang 2017-02-06  STORY36179嘉实基金-新建一个产品树形结构时，可自动生成 “未分配”结构，将没有分配到结构下的组合默认放到“未分配”下
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnLeftSave_Click(object sender, EventArgs e)
        {
            string code = this.txtSectorName.Text;
            if (code == "未分配")
            {
                //// 禁止新增结构名称为“未分配”的节点
                new MessageDialog().Show("禁止新增“未分配”节点，请更换节点名称！", "失败", MessageBoxButtons.OK, MessageBoxIcon.Information);
                return;

            }

            if (status == ClsEnums.StatusSetting.YssAdd)
            {
                ////add by zhoushuhang 2018-06-15 BUG206147节点code不能与组合代码相同
                string[] portArray = ClsContext.DataRightList.ToArray();
                int id = Array.IndexOf(portArray, this.txtSectorCode.Text);
                if (id != -1)
                {
                    //// 资产树节点code不能和组合代码相同，code相同时，点击保存给予提示
                    Yss.CommonLib.ShowMessage("代码已存在：已存在与之相同的组合代码！", "警告", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                    return;
                }
            }

            base.btnLeftSave_Click(sender, e);
        }



        /// <summary>
        /// 根据节点代码获取节点
        /// add by shijian 2016-11-29 BUG #145892 产品树形结构修改是数据异常系统闪退
        /// </summary>
        /// <param name="code">节点代码</param>
        /// <returns>节点</returns>
        private AssetsTree_A getNodeByCode(string code)
        {
            Yss.KTable.Collections.RowCollection rows = this.frmBaseViewList.tbLeftMain.Rows;     
            rows = this.frmBaseViewList.tbLeftMain.GetAllRows(rows, true);
            int len = rows == null ? 0 : rows.Count;
            for (int i = 0; i < len; i++)
            {
                    AssetsTree_A view = (AssetsTree_A)rows[i].Tag;
                    if (code.Equals(view.C_TR_CODE))
                    {
                        return view;
                    }
             }

            return null;
        }


        /// <summary>
        /// 设置的父节点是否当前节点的子节点
        /// add by shijian 2016-11-29 BUG #145892 产品树形结构修改是数据异常系统闪退
        /// </summary>
        /// <param name="code">节点代码</param>
        /// <param name="codep">父节点代码</param>
        /// <returns>true、false</returns>
        private bool isParentNodeChildNodeOfCurrendNode(string code, string codep)
        {
            AssetsTree_A cnode = getNodeByCode(code);
            AssetsTree_A pnode = getNodeByCode(codep);

            if (cnode == null || pnode == null)
            {
                //// 当前节点为新增节点、或父节点为根节点
                return false; 
            }

            AssetsTree_A tmpnode = pnode;

            while (tmpnode != null)
            {
                if (tmpnode.C_TR_CODE_P.Equals(code))
                {
                    return true; 
                }

                tmpnode = getNodeByCode(tmpnode.C_TR_CODE_P);
            }


            return false;
        }

        /// <summary>
        /// 重写父类与数据库交互后的操作，包括刷新窗体的和消息提示
        /// </summary>
        /// <param name="operResult">操作后返回信息</param>
        protected override void operAfterSave(string operResult)
        {
            if (ClsRetInfoDealer.isJsonInfo(operResult) && operResult.Trim() != "")
            {
                ClsRetInfo retInfo = ClsRetInfoDealer.getReturnInfo(operResult);
                if (retInfo.operRes == "Success")
                {
                    /*Author:ChenLong Date:2013-11-19 Status:Modify Comment:定位当前保存的数据 保证set界面显示当前保存的数据*/
                    /* 将保存后返回的数据C_IDEN返回值标记下来 TODO若返回多个C_IDEN 暂时取第一个 */
                    if (retInfo.cidenList != null && retInfo.cidenList.Count > 0)
                    {
                        ////BUG #183327 款项类型设置界面，维护数据后完成后，光标自动跳转到第一行。张绍林-20171221
                        ModifiedRowIds.Clear();
                        ModifiedRowIds.AddRange(retInfo.cidenList);
                    }

                    if (null != frmBaseViewList_B)
                    {
                        if (!isLeftSetForm)
                        {
                            ////STORY #34372 优化系统数据维护后的查询。张绍林-20160926
                            if (this.UpdateListByDataId)
                            {
                                this.UpdateFrmListTableByDataId(retInfo);
                            }
                            else
                            {
                                frmBaseViewList_B.getMainListDataMVC(new BasePojo(), true);
                            }
                        }
                    }
                }

                ////派工单 #2456 估值_V1.300.7.0_UI自动化测试_自动化测试(274)【产品树形结构】
                YssMessageBox.currentForm = this;
                YssMessageBox.ShowCommonInfo(operResult);
                //// 逻辑有误，当基本操作成功后才刷新窗体的状态 byleeyu20130916 YSSUCO赢时胜2013年03月28日01_A
                if (retInfo.operRes == "Success")
                {
                    oldStatus = status;
                    status = ClsEnums.StatusSetting.YssBrow;
                    initControlStat();
                }
            }
        }

        /// <summary>
        /// 根据修改行的数据ID，来更新列表界面的数据。
        /// BUG #223374 添加子节点，报错 
        /// </summary>
        /// <param name="operResult">保存操作结果</param>
        private void UpdateFrmListTableByDataId(ClsRetInfo operResult)
        {
            if (frmBaseViewList_B != null && operResult.operRes == "Success")
            {
                this.frmBaseViewList_B.UpdateRowsByDataIds(ModifiedRowIds);
            }
        }

        /// <summary>
        /// 重写基类刷新主界面 BUG #223374 添加子节点，报错
        /// </summary>
        /// <param name="operResult">成功标识</param>
        public override void refreshTbMain(string operResult)
        {
            if (ClsRetInfoDealer.isJsonInfo(operResult))
            {
                ClsRetInfo info = ClsRetInfoDealer.getReturnInfo(operResult);
                operResult = info.operRes;
            }

            object selDataObj;
            //// edit by yh 2011.03.11 如果保存数据成功则刷新界面上的数据,并修改当前状态为浏览状态
            if (operResult == "Success")
            {
                if (frmBaseViewList_B != null)
                {
                    ////有FrmBaseList，走FrmBaseList
                    if (isLeftSetForm)
                    {
                        frmBaseViewList_B.IsShowLeftForm = true;
                    }

                    ////还原，部分界面不能刷新数据。张绍林-20170614
                    ////BUG #162095 【优化】计费计息-运营费用设置界面，新增运营费用设置，关联计费公式，点击保存后，计费公式对应的辅助参数不见了
                    if (bUseMVCService)
                    {
                        selDataObj = frmBaseViewList_B.yssGetSelTypeItemMVC();
                    }
                    else
                    {
                        selDataObj = frmBaseViewList_B.yssGetSelTypeItem();
                    }

                    //// 刷新数据后重新加载新的数据到界面上,以保持List与Set窗体选择数据的同步
                    if (selDataObj != null)
                    {
                        BasePojo pojo = this.yssGetBaseSelTypeItemMVC();
                        ////STORY #43750 Set界面、List界面数据联动优化，及Set界面Pojo固化功能 hp 20170726
                        if (this.EditedPojo != pojo)
                        {
                            this.EditedPojo = pojo;
                            showInfoMVC(pojo);
                        }
                    }

                    frmBaseViewList_B.IsShowLeftForm = false;
                    status = ClsEnums.StatusSetting.YssBrow;
                    initControlStat();
                }
                else
                {
                    ////没有FrmBaseList，走EditePojo
                    ////STORY #43750 Set界面、List界面数据联动优化，及Set界面Pojo固化功能。张绍林-20171123
                    ////保存完数据之后，修订EditePojo的ID
                    if (this.EditedPojo != null && this.ModifiedRowIds.Count == 1)
                    {
                        this.EditedPojo.Id = this.ModifiedRowIds[0];
                    }
                }
            }
        }

        /// <summary>
        /// 重写基类获取List中选择的一个项 BUG #223374 添加子节点，报错
        /// </summary>
        /// <returns>选中项对应的pojo对象</returns>
        public override BasePojo yssGetBaseSelTypeItemMVC()
        {
            if (frmBaseViewList_B == null)
            {
                return null;
            }

            Table operTable = null;

            if (isLeftSetForm)
            {
                operTable = frmBaseViewList_B.TableLeftMain;
            }
            else
            {
                operTable = frmBaseViewList_B.tbMain;
            }

            if (status == ClsEnums.StatusSetting.YssFilter)
            {
                return (BasePojo)frmBaseViewList_B.YssFilterTypeMVC;
            }
            else
            {
                return frmBaseViewList_B.yssGetSelTypeItemMVC(operTable);
            }
        }

        /// <summary>
        /// 分类规则选择“自定义”显示控件
        /// STORY #72829 资产结构新增仅包含“存续期+待发行”的组合
        /// add by yangru 20190721 
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboFlGZ_SelectedValueChanged(object sender, EventArgs e)
        {
            if ("TREE_TYPE_CUS".Equals(this.cboFlGZ.Value))
            {
                this.cboFlGZ.ExpandButtonVisible = true;
                this.cboAutoZr.Visible = true;
                this.tbMain.Rows[5].Cells[0].Text = "   自动转入：";
                this.tbMain.Rows[5].Cells[1].InnerControl = this.cboAutoZr;
                if (this.cboAutoZr.Value == null)
                {
                    this.cboAutoZr.Value = "TREE_AUTO_BKQ";
                }

            }
            else
            {
                this.cboFlGZ.ExpandButtonVisible = false;
                this.cboAutoZr.Visible = false;
                this.tbMain.Rows[5].Cells[0].Text = "";
                this.tbMain.Rows[5].Cells[1].InnerControl = null;
            }
        }

        /// <summary>
        /// 点击分类规则点击控件，弹出分类规则设置窗口
        /// STORY #72829 资产结构新增仅包含“存续期+待发行”的组合
        /// add by yangru 20190721
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboFlGZ_ExpandClick(object sender, EventArgs e)
        {
            if (this.cboFlGZ.SelectedItem != null)
            {
                Frm_ASSETS_TREE_S_A_RULE ruleForm = new Frm_ASSETS_TREE_S_A_RULE(this.assetsTreeARule);
                ruleForm.YssStatus = this.status;
                ruleForm.initControlStat();
                if (ruleForm.ShowDialog() == System.Windows.Forms.DialogResult.OK)
                {
                    this.assetsTreeARule = ruleForm.getAssetsTreeARule();
                }

                ruleForm.Dispose();
                ruleForm = null;
            }
        }
    }
}
