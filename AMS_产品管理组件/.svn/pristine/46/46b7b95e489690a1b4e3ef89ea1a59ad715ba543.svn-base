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
                clsAssetsTreeA = this.frmBaseViewList.getSelectedRowTagMVC(clsAssetsTreeA) as AssetsTree_A;
                if (null != clsAssetsTreeA)
                {
                    this.txtSectorCode.Text = clsAssetsTreeA.C_TR_CODE;
                    this.txtSectorName.Text = clsAssetsTreeA.C_TR_NAME;
                    this.cboFlGZ.Value = clsAssetsTreeA.C_DV_TR;
                    if (clsAssetsTreeA.C_TR_CODE_P.Equals("[root]"))
                    {
                        this.cboSector.Text = "";
                    }
                    else
                    {
                        this.cboSector.Value = clsAssetsTreeA.C_TR_CODE_P; // 上级板块代码
                    }
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }

            ////应对Bug8280 新增操作时清空控件
            if (status == ClsEnums.StatusSetting.YssAdd)
            {
                clsInterface.yssClearTableCtlValue(tbMain); //// 调整了代码位置到ClsInterface类中 byleeyu 20120618
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
            AssetsTree_A clsAssetsTreeA = null;
            try
            {
                clsAssetsTreeA = new AssetsTree_A();

                clsAssetsTreeA.C_TR_CODE = this.txtSectorCode.Text;
                clsAssetsTreeA.C_TR_NAME = this.txtSectorName.Text;
                clsAssetsTreeA.C_DV_TR = this.cboFlGZ.Value;
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
                if (this.frmBaseViewList.tbLeftMain.SelectedRow != null)
                {
                    AssetsTree_A clsAssetsTreeA = (AssetsTree_A)this.frmBaseViewList.tbLeftMain.SelectedRow.Tag;
                    if (null != clsAssetsTreeA)
                    {
                        this.txtSectorCode.Text = clsAssetsTreeA.C_TR_CODE;
                        this.txtSectorName.Text = clsAssetsTreeA.C_TR_NAME;
                        this.cboFlGZ.Value = clsAssetsTreeA.C_DV_TR;
                        if (clsAssetsTreeA.C_TR_CODE_P.Equals("[root]"))
                        {
                            this.cboSector.Text = "";
                        }
                        else
                        {
                            this.cboSector.Value = clsAssetsTreeA.C_TR_CODE_P; // 上级板块代码
                        }
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
            string search = "";
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
            ArrayList pojoList = null;
            if (status == ClsEnums.StatusSetting.YssAudit || status == ClsEnums.StatusSetting.YssUnAudit)
            {
                pojoList = new ArrayList();
                Row row = this.frmBaseViewList.tbLeftMain.SelectedRow;
                pojoList.Add(toTreeA(row));
                getLeftList(row, pojoList);
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

            return result;
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
        }

        /// <summary>
        /// 保存树形结构数据前处理
        /// add by shijian 2016-11-29 BUG #145892 产品树形结构修改是数据异常系统闪退
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ASSETS_TREE_S_A_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
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

            //// 新增节点不检查
               
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

    }
}
