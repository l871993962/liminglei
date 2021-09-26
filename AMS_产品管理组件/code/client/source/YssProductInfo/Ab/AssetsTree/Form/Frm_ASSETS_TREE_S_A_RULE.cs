using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using Yss.KRichEx.AutoFilter.Collections;
using Yss.KRichEx.AutoFilter.Model;
using Yss.KTable.Models;
using System.Text.RegularExpressions;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Context;
using FAST.Core.BaseControl.Fun;
using FAST.Common.Service.DataService;
using FAST.Core.Communication.DataService;
using FAST.Core.Util;
using FAST.Common.Service.Dict.Pojo;
using FAST.Common.Service.Services;
using YssInformation.Support.Sys.Dictionary.Pojo;
using YssProductInfo.Support.Ab.AssetsTree.Pojo;

namespace YssProductInfo.Ab.AssetsTree.Form
{
    /// <summary>
    /// 分类规则
    /// STORY #72829 资产结构新增仅包含“存续期+待发行”的组合 
    /// add by yangru 20190717
    /// </summary>
    public partial class Frm_ASSETS_TREE_S_A_RULE : FrmBaseSet
    {
        /// <summary>
        /// 分类规则
        /// </summary>
        private AssetsTree_A_Rule assetsTreeARule = null;

        /// <summary>
        /// 初始化
        /// </summary>
        /// <param name="assetsTreeARule">1</param>
        public Frm_ASSETS_TREE_S_A_RULE(AssetsTree_A_Rule assetsTreeARule)
        {
            bUseMVCService = true;
            this.assetsTreeARule = assetsTreeARule;
            InitializeComponent();
            if (!this.DesignMode)
            {
                initRights();
            }
        }

        /// <summary>
        /// 返回保存的值
        /// </summary>
        /// <returns>1</returns>
        public AssetsTree_A_Rule getAssetsTreeARule()
        {
            return assetsTreeARule;
        }

        /// <summary>
        /// 初始化窗体控件状态
        /// </summary>
        public override void initControlStat()
        {
            if (this._formFun == null)
            {
                this._formFun = ClsContext.sysMenuFunHash["assetsTree_A_rule"];
            }

            base.initControlStat();
        }

        /// <summary>
        /// 初始化权限
        /// </summary>
        private void initRights()
        {
            List<string> list = new List<string>();
            list.Add(ClsButtonName.BtnEdit);
            list.Add(ClsButtonName.BtnSave);
            list.Add(ClsButtonName.BtnHelp);
            list.Add(ClsButtonName.BtnClose);

            this.btnBar.UserOperList = list;
            this.btnBar.FunRightList = list;
        }

        /// <summary>
        /// 初始化方法.
        /// </summary>
        public override void yssInitCtlAttr()
        {
            this.cboCpsjwdFlcj.YssReadOnly = (status == FAST.Core.Context.ClsEnums.StatusSetting.YssBrow ? true : false);
            this.cboZcsxwdFlcj.YssReadOnly = (status == FAST.Core.Context.ClsEnums.StatusSetting.YssBrow ? true : false);
            initCheckBoxes();
        }

        /// <summary>
        /// 初始化“产品时间维度”及“资产属性维度"的CHECKBOX
        /// </summary>
        private void initCheckBoxes()
        {
            IVocDataService assSVC = DataServiceFactory.createService<IVocDataService>();
            List<BasePojo> statusDataList = assSVC.getDataListByTypes(new string[] { "PD_STATUS" });
            int iCount = 1;
            Yss.KTable.Models.CheckItem checkItemCpsjwd = null;
            foreach (BasePojo basePojo in statusDataList)
            {
                Vocabulary tempVoc = basePojo as Vocabulary;

                checkItemCpsjwd = new Yss.KTable.Models.CheckItem();
                checkItemCpsjwd.Text = tempVoc.C_DV_NAME;
                checkItemCpsjwd.Tag = tempVoc;
                checkItemCpsjwd.Name = tempVoc.C_DV_CODE;
                this.sjwdListCell.CheckItems.Add(checkItemCpsjwd);
                iCount++;


            }

            IAccTypeDataService assTypeSVC = DataServiceFactory.createService<IAccTypeDataService>();
            List<BasePojo> assTypeDataList = assTypeSVC.getDataListByTypes(new string[] { "ASS" });
            int iCount1 = 1;
            Yss.KTable.Models.CheckItem checkItemCpsxwd = null;
            foreach (BasePojo basePojo in assTypeDataList)
            {
                DatAssType tempAcc = basePojo as DatAssType;

                checkItemCpsxwd = new Yss.KTable.Models.CheckItem();
                checkItemCpsxwd.Text = tempAcc.C_DAT_NAME;
                checkItemCpsxwd.Tag = tempAcc;
                checkItemCpsxwd.Name = tempAcc.C_DAT_CODE;
                this.sxwdListCell.CheckItems.Add(checkItemCpsxwd);
                iCount1++;
            }
        }

        /// <summary>
        /// 分类层级
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboCpsjwdFlcj_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            if (this.cboCpsjwdFlcj.Items.Count == 0)
            {
                this.cboCpsjwdFlcj.Items.AddRange(QueryFjcjData());
            }
        }

        /// <summary>
        /// 分类层级
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboZcsxwdFlcj_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            if (this.cboZcsxwdFlcj.Items.Count == 0)
            {
                this.cboZcsxwdFlcj.Items.AddRange(QueryFjcjData());
            }
        }

        /// <summary>
        /// 加载数据值后
        /// </summary>
        /// <param name="sender">1</param>
        /// <param name="e">1</param>
        private void cboCpsjwdFlcj_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.cboCpsjwdFlcj.Value == this.cboCpsjwdFlcj.Value)
            {
                if (this.cboCpsjwdFlcj.Value == "1")
                {
                    this.cboZcsxwdFlcj.Value = "2";
                }

                if (this.cboCpsjwdFlcj.Value == "2")
                {
                    this.cboZcsxwdFlcj.Value = "1";
                }

            }
        }

        /// <summary>
        /// 加载数据值后
        /// </summary>
        /// <param name="sender">1</param>
        /// <param name="e">1</param>
        private void cboZcsxwdFlcj_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.cboCpsjwdFlcj.Value == this.cboZcsxwdFlcj.Value)
            {
                if (this.cboZcsxwdFlcj.Value == "1")
                {
                    this.cboCpsjwdFlcj.Value = "2";
                }

                if (this.cboZcsxwdFlcj.Value == "2")
                {
                    this.cboCpsjwdFlcj.Value = "1";
                }

            }
        }

        /// <summary>
        /// 构建分类层级下拉框数据
        /// </summary>
        /// <returns>数据集合</returns>
        private ControlEntityCollection QueryFjcjData()
        {
            ControlEntityCollection collection = new ControlEntityCollection();
            Yss.KRichEx.AutoFilter.Model.KTableEntity entity1 = new Yss.KRichEx.AutoFilter.Model.KTableEntity("1级", "1");
            collection.Add(entity1);
            Yss.KRichEx.AutoFilter.Model.KTableEntity entity2 = new Yss.KRichEx.AutoFilter.Model.KTableEntity("2级", "2");
            collection.Add(entity2);
            return collection;
        }

        /// <summary>
        /// 窗体加载
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ASSETS_TREE_S_A_RULE_Load(object sender, EventArgs e)
        {
            if (this.cboCpsjwdFlcj.Value == null && this.cboZcsxwdFlcj.Value == null)
            {
                this.cboCpsjwdFlcj.Value = "1";
                this.cboZcsxwdFlcj.Value = "2";

            }
            
            this.setAssetsTreeARuleData();
        }

        /// <summary>
        /// 保存事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnSave_Click(object sender, EventArgs e)
        {
            try
            {
                YssMessageBox.currentForm = this;
                if (assetsTreeARule == null)
                {
                    assetsTreeARule = new AssetsTree_A_Rule();
                }

                ////BUG #324987【长江养老】V1.300.7.0.20200430.0708 给产品树形结构新增组合时报错系统异常
                string cpsjwd = getCpsjwdChecked();
                string zcsxwd = getZcsxwdChecked();

                if (string.IsNullOrEmpty(cpsjwd) || string.IsNullOrEmpty(zcsxwd))
                {
                    Yss.CommonLib.ShowMessage("产品时间维度或产品属性维度没有选择具体的选项！");
                    return;
                }

                assetsTreeARule.C_CPSJWD_FLCJ = this.cboCpsjwdFlcj.Value;
                assetsTreeARule.C_ZCSXWD_FLCJ = this.cboZcsxwdFlcj.Value;
                assetsTreeARule.C_CPSJWD = cpsjwd;
                assetsTreeARule.C_ZCSXWD = zcsxwd;

                if (!checkInput())
                {
                    return;
                }

                this.DialogResult = DialogResult.OK;
                this.Close();
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-000001", _formFun, status));
            }
        }

        /// <summary>
        /// 展示对象到窗体
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            this.setAssetsTreeARuleData();
        }

        /// <summary>
        /// 给当前界面赋值
        /// </summary>
        private void setAssetsTreeARuleData()
        {
            if (this.assetsTreeARule != null)
            {
                this.cboCpsjwdFlcj.Value = this.assetsTreeARule.C_CPSJWD_FLCJ;
                this.cboZcsxwdFlcj.Value = this.assetsTreeARule.C_ZCSXWD_FLCJ;
                if (assetsTreeARule.C_CPSJWD != null)
                {
                    this.setCpsjwdChecked(assetsTreeARule.C_CPSJWD);
                }

                if (assetsTreeARule.C_ZCSXWD != null)
                {
                    this.setZcsxwdChecked(assetsTreeARule.C_ZCSXWD);
                }
            }

        }

        /// <summary>
        /// 获取勾选的产品时间维度字符串
        /// </summary>
        /// <returns>1</returns>
        private string getCpsjwdChecked()
        {
            string sCheckedItems = "";
            for (int i = 0; i < this.sjwdListCell.CheckItems.Count; i++)
            {
                CheckItem checkItem = sjwdListCell.CheckItems[i] as CheckItem;

                if (checkItem.Checked == true)
                {
                    Vocabulary tempVoc = checkItem.Tag as Vocabulary;

                    sCheckedItems += tempVoc.C_DV_CODE + ",";
                }
            }

            if (sCheckedItems.Contains(","))
            {
                sCheckedItems = sCheckedItems.Substring(0, sCheckedItems.LastIndexOf(","));
            }
            
            return sCheckedItems;
        }

        /// <summary>
        /// 获取勾选的资产属性维度字符串
        /// </summary>
        /// <returns>1</returns>
        private string getZcsxwdChecked()
        {
            string sCheckedItems = "";
            for (int i = 0; i < this.sxwdListCell.CheckItems.Count; i++)
            {
                CheckItem checkItem = sxwdListCell.CheckItems[i] as CheckItem;

                if (checkItem.Checked == true)
                {
                    DatAssType tempAcc = checkItem.Tag as DatAssType;

                    sCheckedItems += tempAcc.C_DAT_CODE + ",";
                }
            }

            if (sCheckedItems.Contains(","))
            {
                sCheckedItems = sCheckedItems.Substring(0, sCheckedItems.LastIndexOf(","));
            }
            
            return sCheckedItems;
        }

        /// <summary>
        /// 根据查询出的数据显示产品时间维度的勾选
        /// </summary>
        /// <param name="cpsjwdStr">1</param>
        private void setCpsjwdChecked(string cpsjwdStr)
        {
            string[] sCheckedList = Regex.Split(cpsjwdStr, ",");
            bool cellReadOnly = this.sjwdListCell.OwnRow.OwnTable.ReadOnly;
            this.sjwdListCell.OwnRow.OwnTable.ReadOnly = false;
            for (int i = 0; i < sCheckedList.Length; i++)
            {
                for (int iCount = 0; iCount < this.sjwdListCell.CheckItems.Count; iCount++)
                {
                    CheckItem checkItem = this.sjwdListCell.CheckItems[iCount] as CheckItem;

                    Vocabulary tempVoc = checkItem.Tag as Vocabulary;

                    if (tempVoc.C_DV_CODE.Equals(sCheckedList[i]))
                    {
                        checkItem.Checked = true;
                        break;
                    }
                }
            }

            this.sjwdListCell.OwnRow.OwnTable.ReadOnly = cellReadOnly;
        }

        /// <summary>
        /// 根据查询出的数据显示资产属性维度的勾选
        /// </summary>
        /// <param name="zcsxwdStr">1</param>
        private void setZcsxwdChecked(string zcsxwdStr)
        {
            string[] sCheckedList = Regex.Split(zcsxwdStr, ",");
            bool cellReadOnly = this.sxwdListCell.OwnRow.OwnTable.ReadOnly;
            this.sxwdListCell.OwnRow.OwnTable.ReadOnly = false;

            for (int i = 0; i < sCheckedList.Length; i++)
            {
                for (int iCount = 0; iCount < this.sxwdListCell.CheckItems.Count; iCount++)
                {
                    CheckItem checkItem = this.sxwdListCell.CheckItems[iCount] as CheckItem;

                    DatAssType tempAcc = checkItem.Tag as DatAssType;

                    if (tempAcc.C_DAT_CODE.Equals(sCheckedList[i]))
                    {
                        checkItem.Checked = true;
                        break;
                    }
                }
            }

            this.sxwdListCell.OwnRow.OwnTable.ReadOnly = cellReadOnly;
        }


    }
}
