using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Core.BaseControl.Fun;
using YssElecReco.Pojo.Er;
using FAST.Core.Exceptions;
using FAST.Core.CRUD.Interface;

namespace YssElecReco.Form.Er
{
    public partial class Frm_ELEC_SPLIT_RULE_L : FrmBaseList, IFormDetailData
    {
        public Frm_ELEC_SPLIT_RULE_L()
        {
            this.bUseMVCService = true;
            bUseMVCServiceLeft = true;
            InitializeComponent();
        }

        #region IFormDetailData 成员
        /// <summary>
        /// 获取或设置一个值，该值为主窗体传过来的数据。当该窗体为某一窗体的附属窗体时有效。
        /// </summary>
        private BasePojo _mainDataPojo;

        /// <summary>
        /// 获取或设置当前窗体是否已经被FrmBaseListWidthDetails窗体关联为子窗体
        /// </summary>
        private bool _hadBeenRelationed = false;
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

            this.ShowLeftPanel = false;
            this.ShowFilterPanel = true;
        }

        /// <summary>
        /// 装载数据
        /// </summary>
        /// <param name="mainData">主表传过来的Pojo</param>
        public void LoadDetailData(BasePojo mainData)
        {
            bool validate = this.AllowReloadDetailData(mainData);
            if (validate)
            {
                this.AllowResetGeneParaAssemble = false;
                this.MainDataPojo = mainData;
                //this.geneParaAssemble.Clear();
                //this.geneParaAssemble.Add("C_IDEN_RELA", (mainData as ErSplitRela).Id);
                //this.geneParaAssemble.Add("dataClass", "ErSplitRule");
                //if (this.txtKmCode.Text != null && !"".Equals(this.txtKmCode.Text.Trim()))
                //{
                //    this.geneParaAssemble.Add("C_KM_CODE", "%" + this.txtKmCode.Text.Trim() + "%");
                //}
                this.btnSearch_Click(this.btnBar.getButton(ClsButtonName.BtnRefresh).Owner, EventArgs.Empty);
            }
        }
        #endregion

        /// <summary>
        /// 封装前台查询条件
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>paraDict</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            try
            {
                BasePojo obj = this.MainDataPojo;
                if (null != obj && obj.GetType() == typeof(ErSplitRela))
                {
                    ErSplitRela info = obj as ErSplitRela;
                    paraDict.Add("C_IDEN_RELA", info.Id);
                    paraDict.Add("dataClass", "ErSplitRule");
                }
                if (this.txtKmCode.Text != null && !"".Equals(this.txtKmCode.Text.Trim()))
                {
                    paraDict.Add("C_KM_CODE", "%" + this.txtKmCode.Text.Trim()+"%");
                }
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500037", _formFun, status));
                ClsBaseException.DiscardException(ex);
            }

            return paraDict;
        }

        public override void btnSearch_Click(object sender, EventArgs e)
        { 
            if(this.geneParaAssemble != null)
            {
                this.geneParaAssemble.Clear();
            }
            base.btnSearch_Click(sender,e);
        }

        protected override void tbMain_SelectionChanged(object sender, Yss.KTable.Events.RowEventArgs e)
        { 
            //屏蔽掉该段逻辑
        }
        
    }
}
