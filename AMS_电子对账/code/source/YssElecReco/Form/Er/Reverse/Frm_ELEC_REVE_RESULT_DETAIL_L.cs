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
using YssElecReco.Service.Er;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using YssElecReco.Service.Er.Reverse;
using YssElecReco.Pojo.Er.Reverse;
using YssElecReco.Pojo.Er;
using YssElecReco.Fun;
using FAST.Core.Exceptions;
using FAST.Common.Service.Pojo;
using Yss.KTable.Models;
using FAST.Core.Context;
using FAST.Core.CRUD.Interface;

namespace YssElecReco.Form.Er.Reverse
{
    public partial class Frm_ELEC_REVE_RESULT_DETAIL_L : FrmBaseList, IFormDetailData
    {
        private string dzType = "";
        public Frm_ELEC_REVE_RESULT_DETAIL_L()
        {
            this.bUseMVCService = true;
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

        public bool AllowReloadDetailData(BasePojo mainData)
        {
            bool retValue = false;
            if (mainData != null && mainData != this.MainDataPojo)
            {
                retValue = true;
            }

            return retValue;
        }



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
            this.ShowFilterPanel = false;
        }

        /// <summary>
        /// 装载数据
        /// </summary>
        /// <param name="mainData">主表传过来的pojo</param>
        public void LoadDetailData(BasePojo mainData)
        {
            bool isLoad = this.AllowReloadDetailData(mainData);
            if (isLoad)
            {
                if (mainData is ErReveInfo)
                {
                    MainDataPojo = mainData;
                    ErReveInfo erInfo = (ErReveInfo)mainData;
                    if (ReveElecDVCons.DZ_TYPE_GZB.Equals(erInfo.C_FILE_TYPE))
                    {
                        this.dzType = erInfo.C_FILE_TYPE;
                        this.YssFormMenu.C_FUN_NAME = "估值表对账结果明细";
                        this.Text = this.YssFormMenu.C_FUN_NAME;

                    }
                    else if (ReveElecDVCons.DZ_TYPE_YEB.Equals(erInfo.C_FILE_TYPE))
                    {
                        this.dzType = erInfo.C_FILE_TYPE;
                        this.YssFormMenu.C_FUN_NAME = "余额表对账结果明细";
                        this.Text = this.YssFormMenu.C_FUN_NAME;
                    }
                    else
                    {
                        this.dzType = erInfo.C_FILE_TYPE;
                        this.YssFormMenu.C_FUN_NAME = "对账结果明细";
                        this.Text = this.YssFormMenu.C_FUN_NAME;
                    }
                    //loadData(erInfo);
                    this.btnSearch_Click(this.btnBar.getButton(FAST.Core.BaseControl.Fun.ClsButtonName.BtnRefresh).Owner, EventArgs.Empty);
                }
            }
        }

        #endregion IFormDetailData 成员

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
                if (null != obj && obj.GetType() == typeof(ErReveInfo))
                {
                    paraDict.Add("dataClass", "ReveResult");
                    ErReveInfo erInfo = obj as ErReveInfo;
                    paraDict.Add("C_SN", erInfo.C_SN);
                    paraDict.Add("C_FILE_TYPE", erInfo.C_FILE_TYPE);
                }
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500037", _formFun, status));
                ClsBaseException.DiscardException(ex);
            }

            return paraDict;
        }
        /// <summary>
        /// 加载数据表格内容
        /// BUG187342【深国投】增值税台账明细表有多页，导出所有页，客户端会闪退。张绍林-20180209
        /// </summary>
        /// <param name="res">查询结果对象</param>
        /// <param name="tableSource">待装载数据的表格</param>
        protected override void loadListContentMVC(QueryRes res, Table tableSource)
        {
            if (ReveElecDVCons.DZ_TYPE_GZB.Equals(this.dzType) || ReveElecDVCons.DZ_TYPE_YEB.Equals(this.dzType))
            {
                YssMainKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;
            }else
            {
                YssMainKTableShowMode = ClsEnums.KTableDataShowMode.ListMode;
            }
            ReveDzResultTableListLoader listLoader = new ReveDzResultTableListLoader();
            listLoader.FunCode = this.YssFormMenu.C_FUN_CODE;
            listLoader.AutoLoadEnclosure = this.AutoLoadEnclosure;
            listLoader.loadTable(tableSource, res, bShowRowCheckBoxColumn, bShowRowIndexColumn, YssMainKTableShowMode);

            ////读取用户自定义列配置信息
            this.ReadTableColumnsFromConfig(tableSource, this.YssFormMenu.C_FUN_NAME);

            ////读取分组列信息。张绍林-20151124
            this.ReadTableGroupColumnFromConfig(tableSource, this.YssFormMenu.C_FUN_NAME);

            ////读取用户自定义列宽信息。张绍林-20151201
            this.ReadColumnWidthFromConfig(tableSource, this.YssFormMenu.C_FUN_NAME);
        }

        /// <summary>
        /// 重写行双击事件，屏蔽行双击
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbMain_RowDoubleClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            return;
        }
    }
}
