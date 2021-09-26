using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.CRUD.Interface;
using FAST.Core.CRUD.Form;
using YssElecReco.pojo.Er;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using System.Collections;
using Yss.KTable.Models;

namespace YssElecReco.Form.Er
{
    /// <summary>
    /// 报表个性列设置
    /// </summary>
    public partial class Frm_DZ_RepColCfg_L : FrmBaseList, IFormDetailData
    {
        /// <summary>
        /// 获取或设置一个值，该值为主窗体传过来的数据。当该窗体为某一窗体的附属窗体时有效。
        /// </summary>
        private BasePojo _mainDataPojo;

        /// <summary>
        /// 获取或设置当前窗体是否已经被FrmBaseListWidthDetails窗体关联为子窗体
        /// </summary>
        private bool _hadBeenRelationed = false;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_DZ_RepColCfg_L()
        {
            InitializeComponent();
            this.bUseMVCService = true;
        }

        /// <summary>
        /// 获取或设置当前窗体是否已经被FrmBaseListWidthDetails窗体关联为子窗体。通过此属性可防止嵌套关联。
        /// </summary>
        public bool HadBeenRelationed
        {
            get
            {
                return _hadBeenRelationed;
            }

            set
            {
                _hadBeenRelationed = value;
            }
        }

        /// <summary>
        /// 获取或设置一个值，该值为主窗体传过来的数据。当该窗体为某一窗体的附属窗体时有效。
        /// </summary>
        public BasePojo MainDataPojo
        {
            get
            {
                return this._mainDataPojo;
            }

            set
            {
                if (this._mainDataPojo != value)
                {
                    this._mainDataPojo = value;
                }
            }
        }

        /// <summary>
        /// 报表设置不能单条处理
        /// 获取多项选择项的方法，此方法只适用于Checkbox的多选形式
        /// 
        /// MVC架构专用
        /// </summary>
        /// <returns>选择行的数据对象</returns>
        public override ArrayList getSelectTypeItemListAuditable()
        {
            ArrayList list = new ArrayList();
            if (tbMain.CheckedRows.Count != tbMain.Rows.Count)
            {
                if (YssMessageBox.ShowQuestion("只能操作所有列的个性设置！是否全选所有数据？", "提示") != System.Windows.Forms.DialogResult.Yes)
                {
                    return list;
                }
                else
                {
                    ////tbMain.CheckedRows.Clear();
                    ////tbMain.CheckedRows.AddRange(tbMain.Rows);
                    foreach (Row row in tbMain.Rows)
                    {
                        row.Checked = true;
                    }

                    tbMain.Refresh();
                }
            }
            
            
            foreach (Row row in tbMain.CheckedRows)
            {
                // add by yh 2011.03.13 增加在获取list界面选中行时，去掉分组行数据的判断
                if (row.IsGroup != true)
                {
                    if (row.Tag is BasePojo)
                    {
                        list.Add(convertToDataPojo((BasePojo)row.Tag));
                    }
                }
            }

            return list;
        }

        #region IFormDetailData 成员

        /// <summary>
        /// 验证是否需要重新装载数据
        /// </summary>
        /// <param name="mainData">主表传过来的数据</param>
        /// <returns>返回验证结果</returns>
        public bool AllowReloadDetailData(BasePojo mainData)
        {
            bool retValue = false;
            if (mainData != null && mainData != this.MainDataPojo)
            {
                retValue = true;
            }

            return retValue;
        }

        /// <summary>
        /// 明细窗体初始化
        /// </summary>
        /// <param name="parent">FrmBaseListWithDetails父容器</param>
        public void InitializeDetailForm(FrmBaseListWithDetails parent)
        {
            this.sDllName = _formFun.YssAssocia.SetDllName;
            this.sSetClassName = _formFun.YssAssocia.SetFormName;
            this.sPojoClassName = _formFun.YssAssocia.PojoClsName;
            this.sPojoDllName = (_formFun.YssAssocia.PojoDllName != null && _formFun.YssAssocia.PojoDllName.Length > 0) ? _formFun.YssAssocia.PojoDllName : ClsFunction.getDllName(_formFun.YssAssocia.PojoClsName);
            if (_formFun != null)
            {
                this.Text = _formFun.C_FUN_NAME;
            }

            this.ShowFilterPanel = false;
        }

        /// <summary>
        /// 装载数据
        /// </summary>
        /// <param name="mainData">主表传过来的Pojo</param>
        public void LoadDetailData(BasePojo mainData)
        {
            if (page == null)
            {
                page = new FAST.Common.Service.Pojo.ClsPageInation();
            }

            page.CurrPage = 1;
            page.PageCount = 0;

            bool validate = this.AllowReloadDetailData(mainData);
            if (validate)
            {
                this.AllowResetGeneParaAssemble = false;
                this.MainDataPojo = mainData;
                this.geneParaAssemble.Clear();
                this.geneParaAssemble.Add("dataClass", "DzRepColCfg");
                DzRepCfg repCfg = mainData as DzRepCfg;
                this.geneParaAssemble.Add("C_DZ_CODE", repCfg.C_DZ_CODE);
                this.geneParaAssemble.Add("C_REPORT_CODE", repCfg.C_REPORT_CODE);
                ////验证通过，开始装载数据
                this.btnSearch_Click(this.btnBar.getButton(FAST.Core.BaseControl.Fun.ClsButtonName.BtnRefresh).Owner, EventArgs.Empty);
            }
        }

        /// <summary>
        /// 重写基类
        /// </summary>
        /// <param name="paraDict">paradict</param>
        /// <returns>paradict</returns>
        protected override Dictionary<string, string> OnAfterGetParaAssemble(Dictionary<string, string> paraDict)
        {
            if (null != this.MainDataPojo)
            {
                DzRepCfg cfg = this.MainDataPojo as DzRepCfg;
                paraDict["C_REPORT_CODE"] = cfg.C_REPORT_CODE;
                paraDict["C_DZ_CODE"] = cfg.C_DZ_CODE;
            }

            if (!paraDict.ContainsKey("dataClass"))
            {
                paraDict.Add("dataClass", "DzRepColCfg");
            }

            return paraDict;
        }

        #endregion
    }
}
