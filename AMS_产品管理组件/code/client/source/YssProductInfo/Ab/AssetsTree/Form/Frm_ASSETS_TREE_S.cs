/// <summary>
//-----------------------------------------------------------------------
// <copyright file="Frm_ASSETS_TREE_S.cs" company="yss">
//     Company copyright tag.
// </copyright>
//-----------------------------------------------------------------------
/// <summary>
namespace YssProductInfo.Ab.AssetsTree.Form
{
    using System;
    using System.Collections;
    using System.Collections.Generic;
    using System.Windows.Forms;
    using FAST.Common.Service.Pojo;
    using FAST.Common.Service.Pojo.Base;
    using FAST.Core.BaseControl;
    using FAST.Core.Communication.Service;
    using FAST.Core.Context;
    using FAST.Core.Context.Events;
    using FAST.Core.CRUD.Form;
    using FAST.Core.Exceptions;
    using FAST.Core.Util;
    using Yss.KMessage;
    using Yss.KRichEx.AutoFilter.Model;
    using Yss.KTable.Collections;
    using Yss.KTable.Models;
    using YssProductInfo.Support.Ab.AssetsTree.Pojo;
    using YssProductInfo.Support.Ab.AssetsTree.Service;
    using YssProductInfo.Support.Ab.Port.Service;

    /// <summary>
    /// 组合关联set窗体
    /// </summary>
    public partial class Frm_ASSETS_TREE_S : FrmBaseSet
    {
        /// <summary>
        /// add by zhoushuhang 2018-06-13 BUG206147节点code不能与组合代码相同
        /// B区数据服务对象
        /// </summary>
        private IAssertTreeService assetTreeBService;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_ASSETS_TREE_S()
        {
            bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 初始化控件默认值   
        /// </summary>
        public override void yssInitCtlAttr()
        {
            try
            {
                ////edit by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
                if (null != this.frmBaseViewList.tbMain.SelectedRow)
                {
                    this.cboSector.SelectedValueChanged -= new EventHandler(this.cboSector_SelectedValueChanged);
                    if (bUseMVCService) 
                    {
                        ////this.cboSector.Value = ((AssertTreeATreeView)this.frmBaseViewList.tbMain.SelectedRow.Tag).C_TR_CODE;
                        this.cboSector.Value = ((AssetsTree_B)this.frmBaseViewList.tbMain.SelectedRow.Tag).C_TR_CODE;
                    }
                    
                    this.cboSector.SelectedValueChanged += new EventHandler(this.cboSector_SelectedValueChanged);
                }

                if (null == this.frmBaseViewList.tbMain.SelectedRow && null != this.frmBaseViewList.tbLeftMain.SelectedRow)
                {
                    this.cboSector.SelectedValueChanged -= new EventHandler(this.cboSector_SelectedValueChanged);
                    if (bUseMVCService)
                    {
                        this.cboSector.Value = ((AssertTreeATreeView)this.frmBaseViewList.tbLeftMain.SelectedRow.Tag).C_TR_CODE;
                    }
                    
                    this.cboSector.SelectedValueChanged += new EventHandler(this.cboSector_SelectedValueChanged);
                }
                
                if (status == ClsEnums.StatusSetting.YssEdit || status == ClsEnums.StatusSetting.YssBrow)
                {
                    this.cell5.Text = "";
                    this.cell6.InnerControl = null;
                    getData("portRelaPlanBrow"); // 根据条件获取数据
                    if (table1.Rows.Count > 0) 
                    {
                        foreach (Row tablerow in table1.Rows) 
                        {
                            this.checkTreeView(tablerow);
                        }
                    }

                    table1.Refresh();
                    
                }
                else
                {
                    this.cell5.Text = "产品状态：";
                    this.cell6.InnerControl = this.cboProdState;
                    getData("assetsTreeNew"); // 根据条件获取数据
                }
            }
            catch (Exception ye)
            {
                ClsBaseException.DiscardException(ye);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500040", _formFun, status));
            }

        }

        /// <summary>
        /// 递归树型结构，并将每个节点选中
        /// </summary>
        /// <param name="tablerow">行对象</param>
        private void checkTreeView(Row tablerow) 
        {
            RowCollection subRows = tablerow.SubRows;

            if (subRows.Count > 0)
            {
                foreach (Row tablesubrow in subRows)
                {
                    this.checkTreeView(tablesubrow);
                }
            }
            else 
            {
                tablerow.Checked = true;
            }
            
        }

        /// <summary> 
        /// 向后台获取数据，刷新数据
        /// </summary>
        /// <param name="queryType">queryType</param>
        private void getData(string queryType)
        {
            IPortService portSvc = ServiceFactory.createService<IPortService>();
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            try
            {
                string sCond = string.Empty;
                //// ADD BY ZXL 20150129 BUG #103345 S20242 加载树的时候前台更新缓存
                ////ClsSysAuthRight authRight = new ClsSysAuthRight();
                ////authRight.setDataRight();
                //// edit by zhoushuhang 20180320 BUG196104产品树形结构测试问题汇总（获取用户权限下的组合）
                string strPort = ClsContext.DataRightString;
                if ("portRelaPlanBrow".Equals(queryType))
                {
                    paraDict.Add("C_PORT_CODE", ((AssetsTree_B)this.frmBaseViewList.tbMain.SelectedRow.Tag).C_PORT_CODE);
                    paraDict.Add("status", string.Empty);
                }
                else
                {
                    //// 添加筛选条件 产品状态 huangkaixuan 20191011 STORY #73485 产品树形结构模块新增产品信息
                    if (null != this.cboProdState.Value)
                    {
                        paraDict.Add("C_PORT_STATE", this.cboProdState.Value);
                    }

                    ////如果结构代码为空，只获取列头
                    ////否则获取结构代码未关联的组合数据
                    if (null == this.cboSector.Value)
                    {
                        sCond = string.Empty;
                    }
                    else
                    {
                        ////paraDict.Add("C_TR_CODE_R", ((AssertTreeATreeView)this.cboSector.SelectedItem.DataEntity).C_TR_CODE);
                        paraDict.Add("C_PORT_CODE", strPort); //// edit by zhoushuhang 20180320 BUG196104产品树形结构测试问题汇总（获取用户权限下的组合）
                        ////STORY22896【紧急】树形结构支持将同一个产品分给不同的用户-xhb
                        paraDict.Add("C_TR_CODE_NEW", ((AssertTreeATreeView)this.cboSector.SelectedItem.DataEntity).C_TR_CODE);
                    }
                }

                QueryRes res = new QueryRes();
                res = portSvc.getAssetTreeView(paraDict);
                res.ListHeadList = this.getHeadInfo();   ////ADD BY ZXL 20130829
                
                new TableListLoader().loadTable(table1, res, true, true, ClsEnums.KTableDataShowMode.TreeMode);

            }
            catch (Exception ex)
            {
                ClsBaseException.DiscardException(ex);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("004", _formFun, status));
            }
        }


        /// <summary>
        /// 获取用户列头信息
        /// ADD BY ZXL 20130829
        /// </summary>
        /// <returns>ListHeadInfo</returns>
        protected List<ListHeadInfo> getHeadInfo()
        {
            List<ListHeadInfo> infoList = new List<ListHeadInfo>();
            ListHeadInfo headInfo = new ListHeadInfo();
            headInfo.Key = "C_PORT_NAME_ST";
            headInfo.Text = "组合简称";
            headInfo.Format = string.Empty;
            headInfo.Align = "left";
            headInfo.ShowConvert = "false";

            infoList.Add(headInfo);

            headInfo = new ListHeadInfo();
            headInfo.Key = "C_PORT_CODE";
            headInfo.Text = "组合代码";
            headInfo.Format = string.Empty;
            headInfo.Align = "left";
            headInfo.ShowConvert = "false";

            infoList.Add(headInfo);

            headInfo = new ListHeadInfo();
            headInfo.Key = "C_ASS_CODE";
            headInfo.Text = "资产代码";
            headInfo.Format = string.Empty;
            headInfo.Align = "left";
            headInfo.ShowConvert = "false";

            infoList.Add(headInfo);

            return infoList;
        }

        /// <summary>
        /// 下拉点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboPlanCode_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            string relaType = ((Frm_ASSETS_TREE_L)frmBaseViewList).navigateItemMain.Tag as string;
            ////string relaType = ((Frm_ASSETS_TREE_L)frmBaseViewList).navigateExpandablePanelShow.Tag as string;
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            IAssertTree_AService assSVC = ServiceFactory.createService<IAssertTree_AService>();
            QueryRes res = assSVC.getTreeViewData(paraDict);
            foreach (BasePojo plan in res.DataList)
            {
                KTableEntity entity = new KTableEntity(plan);
                e.Collection.Add(entity);
            }

            ////指定控件不要自动去加载数据
            e.IsCancel = true;
        }

        /// <summary>
        /// 数据改变的时候
        /// 刷新组合数据
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboSector_SelectedValueChanged(object sender, EventArgs e)
        {
            //// && status != ClsEnums.StatusSetting.YssEdit
            /*
             * BUG: #139331 
             * by chenwen
             */
            if (status != ClsEnums.StatusSetting.YssBrow && status != ClsEnums.StatusSetting.YssEdit) 
            {
                this.getData("assetsTreeNew");
            }
        }

        /// <summary>
        /// 新增时返回要操作的一组数据
        /// </summary>
        /// <returns>要操作的一组数据</returns>
        public override ArrayList yssGetObjListMVC()
        {
            ArrayList pojoList = new ArrayList(); // 创建list列表;
            AssetsTree_B assTreeB = new AssetsTree_B();
            Port clsPort = null;
            try
            {
                ////循环获取所有选中的数据
                foreach (Row row in this.table1.CheckedRows)
                {
                    clsPort = row.Tag as Port;
                    ////验证当前数据是否含有组合组合信息，有选中的组合信息添加组合信息
                    if (ClsConstant._ISPort.Equals(clsPort.DATA_TYPE))
                    {
                        assTreeB = new AssetsTree_B();
                        assTreeB.C_TR_CODE = this.cboSector.Value;
                        assTreeB.C_TR_CODE_R = ((AssetsTree_A)this.cboSector.SelectedItem.DataEntity).C_TR_CODE_R;
                        assTreeB.C_PORT_CODE = clsPort.C_PORT_CODE;

                        assTreeB = (AssetsTree_B)JsonUtil.toObject(JsonUtil.toJson(assTreeB), assTreeB.GetType());
                        pojoList.Add(assTreeB);
                    }
                }
            }
            catch (Exception ex)
            {
                ClsRetInfo info = ClsRetInfoDealer.getCommonError("ERR-000001", _formFun, status);
                info.setSpecStr("dataObject", this.Text);
                info.setSpecStr("operType", "保存");
                YssMessageBox.ShowCommonInfo(info);
                ClsBaseException.DiscardException(ex);
            }

            return pojoList;
        }
       
        
        /// <summary>
        /// chongxie
        /// </summary>
        /// <param name="pojoList">11</param>
        ///  <param name="status">11</param>
        /// <returns>11</returns>
        protected override string yssDoSetFormOperMVC(ArrayList pojoList, ClsEnums.StatusSetting status)
        {
            string operResult = string.Empty;
            ////BUG #236484 [ 产品树形结构 ]新增组合点击保存,系统就卡住了 edit by xuhanbing 20190116
            ////查询提出来，放在setAddOperPojoInfo去循环无意义，与pojo无关
            IAssertTreeService ctrlSVC = ServiceFactory.createService<IAssertTreeService>();
            string id = ctrlSVC.getUserId(this.cboSector.Value);
           switch (status)
           {
               case ClsEnums.StatusSetting.YssAdd:
                   foreach (object pojo in pojoList)
                   {
                       this.setAddOperPojoInfo((BasePojo)pojo);
                   }

                    if (pojoList.Count > 1000)
                    {
                        object[] objArr = new object[] { pojoList, "insert" };
                        BackgroundWork.RunWork(this, this.DoStandardWork, objArr, "正在保存数据，请稍候！");
                    }
                    else 
                    {
                   operResult = dataService.insert(pojoList);
                    }

                   break;
               case ClsEnums.StatusSetting.YssCopy:
                   foreach (object pojo in pojoList)
                   {
                       this.setAddOperPojoInfo((BasePojo)pojo);
                   }

                    if (pojoList.Count > 1000)
                    {
                        object[] objArr = new object[] { pojoList, "insert" };
                        BackgroundWork.RunWork(this, this.DoStandardWork, objArr, "正在保存数据，请稍候！");
                    }
                    else
                    {
                   operResult = dataService.insert(pojoList);
                    }

                   break;
               case ClsEnums.StatusSetting.YssEdit:
                   foreach (object pojo in pojoList)
                   {
                       this.setEditOperPojoInfo((BasePojo)pojo);
                   }

                    if (pojoList.Count > 1000)
                    {
                        object[] objArr = new object[] { pojoList, "update" };
                        BackgroundWork.RunWork(this, this.DoStandardWork, objArr, "正在更新数据，请稍候！");
                    }
                    else
                    {
                        operResult = dataService.updateById(pojoList);
                    }

                   break;
               default:
                    ////工单 #3238 估值_V1.300.7.0_UI2.0自动化测试_自动化测试(221)【产品树形结构】
                    operResult = base.yssDoSetFormOperMVC(pojoList, status);
                   break;
            }

           return operResult;
        }

        /// <summary>
        /// 功能：设置新增pojo的公共信息
        /// </summary>
        /// <param name="pojo">数据对象</param>
        /// <param name="id">id</param>
        /// <returns>设置公共信息后的数据对象</returns>
        protected BasePojo setAddOperPojoInfo(BasePojo pojo, string id)
        {
            ////IAssertTreeService ctrlSVC = ServiceFactory.createService<IAssertTreeService>();
            ////string id = ctrlSVC.getUserId(this.cboSector.Value);
            if (pojo is ParamPojo)
            {
                ((ParamPojo)pojo).Modifier = ClsContext.currentUser.C_USER_CODE;
            }
            
            if (pojo is AuditableParamPojo)
            {
                if (!string.IsNullOrEmpty(id))
                {
                    ((AuditableParamPojo)pojo).AuditState = 1;
                }
                else
                {
                    ((AuditableParamPojo)pojo).AuditState = (_formFun.N_CHECK == 1) ? 0 : 1;
                }
                //// 当未开启审核状态时，这时是需要将审核人，审核时间填到POJO中去的byleeyu20130719
                if (_formFun.N_CHECK <= 0)
                {
                    ((AuditableParamPojo)pojo).OperUser = ClsContext.currentUser.C_USER_CODE;
                    ((AuditableParamPojo)pojo).AuditDate = ClsFunction.formatDateTime("yyyyMMdd HH:mm:ss", DateTime.Now.ToString());
                }
            }

            return pojo;
        }

        /// <summary>
        /// 功能：设置修改pojo的公共信息
        /// </summary>
        /// <param name="pojo">数据对象</param>
        /// <param name="id">id</param>
        /// <returns>设置公共信息后的数据对象</returns>
        protected BasePojo setEditOperPojoInfo(BasePojo pojo, string id)
        {
            ////IAssertTreeService ctrlSVC = ServiceFactory.createService<IAssertTreeService>();
            ////string id = ctrlSVC.getUserId(this.cboSector.Value);
            BasePojo selObj = null;
            if (frmBaseViewList != null)
            {
                frmBaseViewList.IsShowLeftForm = isLeftSetForm;
                selObj = frmBaseViewList.yssGetSelTypeItemMVC();
                frmBaseViewList.IsShowLeftForm = false;
            }

            if ((pojo.Id == null || pojo.Id == string.Empty) && selObj != null)
            {
                pojo.Id = selObj != null ? selObj.Id : string.Empty; // 这里先判断pojo为null的情况byleeyu20140126
            }

            if (pojo is ParamPojo)
            {
                ((ParamPojo)pojo).Modifier = ClsContext.currentUser.C_USER_CODE;
            }

            if (pojo is AuditableParamPojo && selObj != null && selObj is AuditableParamPojo)
            {
                if (!string.IsNullOrEmpty(id))
                {
                    ((AuditableParamPojo)pojo).AuditState = 1;
                }
                else
                {
                    ((AuditableParamPojo)pojo).AuditState = ((AuditableParamPojo)selObj).AuditState;
                }

                ((AuditableParamPojo)pojo).OperUser = ((AuditableParamPojo)selObj).OperUser;

                //// 当未开启审核状态时，这时是需要将审核人，审核时间填到POJO中去的byleeyu20130719
                if (_formFun.N_CHECK <= 0)
                {
                    ((AuditableParamPojo)pojo).OperUser = ClsContext.currentUser.C_USER_CODE;
                    ((AuditableParamPojo)pojo).AuditDate = ClsFunction.formatDateTime("yyyyMMdd HH:mm:ss", DateTime.Now.ToString());
                }
            }

            return pojo;

        }

        /// <summary>
        /// 获取list中选中记录，为界面元素赋值，显示数据.
        /// -------- 添加记录-----.
        /// 添加时间：20110226.
        /// 添加人：lyh.
        /// 添加简介：对窗体界面元素赋值.
        /// </summary>
        /// <param name="pojo">数据对象</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            try
            {
                ////STORY #67195 人保资产-4.5测试系统中产品建账环节：产品树信息建议设置为账户基本信息【3其它需求-079】
                Row selectedRow = this.frmBaseViewList.tbLeftMain.SelectedRow;
                if (selectedRow == null || "ASS,ZCTG,ZCGL,ZCWT,ZCZL,TGZC,GLZC,NSPL,ZCMXLX,CPZT".Contains(((AssertTreeATreeView)selectedRow.Tag).C_TR_CODE))
                {
                    this.Close();
                    return;
                }

                if (frmBaseViewList.tbMain.SelectedRow != null) 
                {
                    this.cboSector.Value = ((AssetsTree_B)this.frmBaseViewList.tbMain.SelectedRow.Tag).C_TR_CODE;
                }

                if (status == ClsEnums.StatusSetting.YssBrow || status == ClsEnums.StatusSetting.YssCopy || status == ClsEnums.StatusSetting.YssEdit)
                {
                    this.cell5.Text = "";
                    this.cell6.InnerControl = null;
                    getData("portRelaPlanBrow");

                    if (table1.Rows.Count > 0)
                    {
                        foreach (Row tablerow in table1.Rows)
                        {
                            this.checkTreeView(tablerow);
                        }
                    }

                    table1.Refresh();
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 1
        /// </summary>
        /// <param name="sender">2</param>
        /// <param name="e">3</param>
        private void Frm_ASSETS_TREE_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        {
            this.checkPortCheckedIsNull();
        }

        /// <summary>
        /// 验证组合勾选是否为空
        /// </summary>
        private void checkPortCheckedIsNull()
        {
            if (0 == this.table1.CheckedRows.Count)
            {
                TransferErrorMessage transferErrorMessage = new TransferErrorMessage();
                transferErrorMessage.MESSAGESLINK = new List<string>();
                /* 组装提示信息对象ErrorMessage */
                string errorMess = ClsRetInfoDealer.getExtWarns("001", _formFun, ClsEnums.StatusSetting.YssSave);
                transferErrorMessage.MESSAGESLINK.Add(errorMess);
                throw new TransferException(JsonUtil.toJson(transferErrorMessage));
                ////throw new ClsBaseException(ClsRetInfoDealer.getExtWarnStr("001", _formFun, ClsEnums.StatusSetting.YssSave));
            }
        }

        /// <summary>
        /// 数据的保存事件
        /// </summary>
        /// 2017-02-06 zhujinyang STORY36179 节点下新增或修改组合时不能保存在‘未分配’节点下，需弹窗提示
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnSave_Click(object sender, System.EventArgs e)
        {
            if (this.cboSector.Text == "未分配")
            {         
                new MessageDialog().Show("请将组合分配到其他节点！", "失败", MessageBoxButtons.OK, MessageBoxIcon.Information);
                return;

            }

            ////add by zhoushuhang 2018-06-13 BUG206147节点code不能与组合代码相同
            if (null == this.assetTreeBService)
            {
                this.assetTreeBService = ServiceFactory.createService<IAssertTreeService>();
            }

            string code = ((AssertTreeATreeView)this.cboSector.SelectedItem.DataEntity).C_TR_CODE_R;
            Dictionary<string, string> dataMap = this.assetTreeBService.quertAllNodeCode(code);
            if (this.table1.CheckedRows.Count > 0)
            {
                foreach (Row row in this.table1.CheckedRows)
                {
                    string portCode = ((FAST.Common.Service.Pojo.Port)row.Tag).C_PORT_CODE;
                    if (dataMap.ContainsKey(portCode))
                    {
                        //// 资产树节点code不能和组合代码相同，code相同时，点击保存给予提示
                        Yss.CommonLib.ShowMessage("代码已存在：已存在与之相同的组合代码！", "警告", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                        return;
                    }
                }
            }

            base.btnSave_Click(sender, e);
        }

        /// <summary>
        /// BUG #236484 [ 产品树形结构 ]新增组合点击保存,系统就卡住了
        /// add by xuhanbing 2019016
        /// </summary>
        /// <param name="e">工作任务参数</param>
        private void DoStandardWork(WorkEventArgs e)
        {
            object[] objArr = e.Argument as object[];
            ArrayList pojoList = objArr[0] as ArrayList;
            string operType = objArr[1] as string;
            if ("insert".Equals(operType)) 
            {
                dataService.insert(pojoList); 
            }

            if ("update".Equals(operType))
            {
                dataService.insert(pojoList);
            }
            
        }

        /// <summary>
        /// BUG #241253 产品树形结构审核按钮无效
        /// add by xuhanbing 20190125
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnAudit_Click(object sender, EventArgs e)
        {
            if (this.cboSector.Text == "未分配")
            {
                new MessageDialog().Show("请将组合分配到其他节点！", "失败", MessageBoxButtons.OK, MessageBoxIcon.Information);
                return;

            }

            base.btnAudit_Click(sender, e);
        }

        /// <summary>
        /// 获取界面布局、排版用的表格（用于自动化测试时，提取界面控件元素）。
        /// </summary>
        /// <returns>返回表格集</returns>
        protected override List<Table> GetLayoutTables()
        {
            List<Table> tableList = new List<Table>();
            tableList.Add(this.tbMain);
            tableList.Add(this.table1);
            return tableList;
        }

        /// <summary>
        /// 产品状态下拉控件事件  
        /// huangkaixuan 20191011 STORY #73485 产品树形结构模块新增产品信息
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboProdState_SelectedValueChanged(object sender, EventArgs e)
        {
            if (status != ClsEnums.StatusSetting.YssBrow)
            {
                getData("assetsTreeNew");
            }
        }
    }
}
