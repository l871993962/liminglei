using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;

using FAST.Core.Exceptions;
using FAST.Core.Context;

using FAST.Platform.Right.Fun;
using FAST.Core.BaseControl.Fun;

using FAST.Core.Resource;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;

using FAST.Common.Service.Pojo.Base;
using System;
using System.Collections;
using System.Collections.Generic;




using Yss.KRichEx.AutoFilter.Model;
using Yss.KTable.Models;
using Yss.KTable.Collections;
using Yss.KMessage;
using System.Windows.Forms;
using YssProductInfo.Support.Ab.AssetsTree.Pojo;
using YssProductInfo.Support.Ab.Port.Service;
using YssProductInfo.Support.Ab.AssetsTree.Service;

namespace YssProductInfo.Ab.AssetsTree.Form
{
    /// <summary>
    /// 组合关联set窗体
    /// </summary>
    public partial class Frm_ASSETS_TREE_S : FrmBaseSet
    {
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
                if (null != this.frmBaseViewList.tbLeftMain.SelectedRow)
                {
                    this.cboSector.SelectedValueChanged -= new EventHandler(cboSector_SelectedValueChanged);
                    if (bUseMVCService) 
                    {
                        this.cboSector.Value = ((AssertTreeATreeView)this.frmBaseViewList.tbLeftMain.SelectedRow.Tag).C_TR_CODE;
                    }
                    
                    this.cboSector.SelectedValueChanged += new EventHandler(cboSector_SelectedValueChanged);
                }
                
                if (status == ClsEnums.StatusSetting.YssEdit || status == ClsEnums.StatusSetting.YssBrow)
                {
                    getData("portRelaPlanBrow"); // 根据条件获取数据
                    if (table1.Rows.Count > 0) 
                    {
                        foreach (Row tablerow in table1.Rows) 
                        {
                            checkTreeView(tablerow);
                        }
                    }

                    table1.Refresh();
                    
                }
                else
                {
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
                    checkTreeView(tablesubrow);
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
                string sCond = "";
                //// ADD BY ZXL 20150129 BUG #103345 S20242 加载树的时候前台更新缓存
                ////ClsSysAuthRight authRight = new ClsSysAuthRight();
                ////authRight.setDataRight();
                ////string strPort = ClsContext.DataRightString;
                if ("portRelaPlanBrow".Equals(queryType))
                {
                    paraDict.Add("C_PORT_CODE", ((AssetsTree_B)this.frmBaseViewList.tbMain.SelectedRow.Tag).C_PORT_CODE);
                    paraDict.Add("status", "");
                }
                else
                {
                    ////如果结构代码为空，只获取列头
                    ////否则获取结构代码未关联的组合数据
                    if (null == this.cboSector.Value)
                    {
                        sCond = "";
                    }
                    else
                    {
                        ////paraDict.Add("C_TR_CODE_R", ((AssertTreeATreeView)this.cboSector.SelectedItem.DataEntity).C_TR_CODE);
                        ////paraDict.Add("C_PORT_CODE", strPort);
                        ////STORY22896【紧急】树形结构支持将同一个产品分给不同的用户-xhb
                        paraDict.Add("C_TR_CODE_NEW", ((AssertTreeATreeView)this.cboSector.SelectedItem.DataEntity).C_TR_CODE);
                    }
                }

                QueryRes res = new QueryRes();
                res = portSvc.getAssetTreeView(paraDict);
                res.ListHeadList = getHeadInfo();   ////ADD BY ZXL 20130829
                
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
            headInfo.Format = "";
            headInfo.Align = "left";
            headInfo.ShowConvert = "false";

            infoList.Add(headInfo);

            headInfo = new ListHeadInfo();
            headInfo.Key = "C_PORT_CODE";
            headInfo.Text = "组合代码";
            headInfo.Format = "";
            headInfo.Align = "left";
            headInfo.ShowConvert = "false";

            infoList.Add(headInfo);

            headInfo = new ListHeadInfo();
            headInfo.Key = "C_ASS_CODE";
            headInfo.Text = "资产代码";
            headInfo.Format = "";
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
                getData("assetsTreeNew");
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
            FAST.Common.Service.Pojo.Port clsPort = null;
            try
            {
                ////循环获取所有选中的数据
                foreach (Row row in this.table1.CheckedRows)
                {
                    clsPort = row.Tag as FAST.Common.Service.Pojo.Port;
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
                if (frmBaseViewList.tbMain.SelectedRow != null) 
                {
                    this.cboSector.Value = ((AssetsTree_B)this.frmBaseViewList.tbMain.SelectedRow.Tag).C_TR_CODE;
                }

                if (status == ClsEnums.StatusSetting.YssBrow || status == ClsEnums.StatusSetting.YssCopy || status == ClsEnums.StatusSetting.YssEdit)
                {
                    getData("portRelaPlanBrow");

                    if (table1.Rows.Count > 0)
                    {
                        foreach (Row tablerow in table1.Rows)
                        {
                            checkTreeView(tablerow);
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
            checkPortCheckedIsNull();
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

            base.btnSave_Click(sender, e);
        }
    }
}
