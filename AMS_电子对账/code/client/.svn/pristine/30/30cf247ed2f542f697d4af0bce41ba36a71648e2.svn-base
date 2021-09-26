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
using YssElecReco.Pojo.Er.Reverse;
using YssElecReco.Service.Er.Reverse;
using FAST.Core.Communication.Service;
using FAST.Common.Service.Pojo;
using FAST.Core.Exceptions;
using FAST.Common.Service.Services.Base;
using YssElecReco.Fun;
using Yss.KTable.Models;
using FAST.Core.CRUD.Interface;

namespace YssElecReco.Form.Er.Reverse
{
    public partial class Frm_ELEC_REVE_MESSAGE_DETAIL_L : FrmBaseList, IFormDetailData
    {
        private string dzMode = "";
        //目前只有估值，和科目
        private IErKmbOutService kmbService = null;
        private IErGzbOutService gzbService = null;
        private IAssMapService assMapService = null;
        public Frm_ELEC_REVE_MESSAGE_DETAIL_L()
        {
            this.bUseMVCService = true;
            InitializeComponent();
            kmbService = ServiceFactory.createService<IErKmbOutService>();
            gzbService = ServiceFactory.createService<IErGzbOutService>();
            assMapService = ServiceFactory.createService<IAssMapService>();
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
            if (mainData != null)
            {
                this.tbMain.Clear();
                ErReveInfo info = mainData as ErReveInfo;
                //目前只有科目表，估值表，余额表
                if (ReveElecDVCons.DZ_TYPE_GZB.Equals(info.C_FILE_TYPE) || ReveElecDVCons.DZ_TYPE_KMB.Equals(info.C_FILE_TYPE) || ReveElecDVCons.DZ_TYPE_YEB.Equals(info.C_FILE_TYPE))
                {
                    string dzMode = assMapService.getDzMode(info.C_PORT_CODE, info.C_FILE_TYPE);
                    this.dzMode = dzMode;
                    if (ReveElecDVCons.DZMO_QT.Equals(dzMode))//其他模式加载原始表数据，深圳通模式加载报文
                    {
                        retValue = true;
                    }
                }
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
                    //loadData(erInfo);
                    this.btnSearch_Click(this.btnBar.getButton(FAST.Core.BaseControl.Fun.ClsButtonName.BtnRefresh).Owner,EventArgs.Empty);
                }
            }
        }

        #endregion IFormDetailData 成员

        /// <summary>
        /// 初始化服务
        /// </summary>
        protected override void initServiceMVC()
        {
            BasePojo obj = this.MainDataPojo;
            if (null != obj && obj.GetType() == typeof(ErReveInfo))
            {
                ErReveInfo erInfo = obj as ErReveInfo;
                if (ReveElecDVCons.DZ_TYPE_GZB.Equals(erInfo.C_FILE_TYPE))//估值表
                {
                    this.dataService = ServiceFactory.createService<IErGzbOutService>() as IServiceBus;
                    
                }
                else if (ReveElecDVCons.DZ_TYPE_KMB.Equals(erInfo.C_FILE_TYPE))//科目表
                {
                    this.dataService = ServiceFactory.createService<IErKmbOutService>() as IServiceBus;

                }
                else if (ReveElecDVCons.DZ_TYPE_YEB.Equals(erInfo.C_FILE_TYPE))//余额表
                {
                    this.dataService = ServiceFactory.createService<IErYebOutService>() as IServiceBus;

                }
                else
                {
                    this.dataService = null;
                }
            }
        }

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
                    ErReveInfo erInfo = obj as ErReveInfo;
                    if (ReveElecDVCons.DZ_TYPE_GZB.Equals(erInfo.C_FILE_TYPE))//估值表
                    {
                        //dict.Clear();
                        paraDict.Add("dataClass", "ErGzbOut");
                        paraDict.Add("C_PORT_CODE", erInfo.C_PORT_CODE);
                        paraDict.Add("D_GZ_DATE", erInfo.D_DATE);
                        //return gzbService.queryByCondition(dict, pageIns);
                    }
                    else if (ReveElecDVCons.DZ_TYPE_KMB.Equals(erInfo.C_FILE_TYPE))//科目表
                    {
                        //dict.Clear();
                        paraDict.Add("dataClass", "ErKmbOut");
                        paraDict.Add("C_PORT_CODE", erInfo.C_PORT_CODE);
                        //return kmbService.queryByCondition(dict, pageIns);
                    }
                    else if (ReveElecDVCons.DZ_TYPE_YEB.Equals(erInfo.C_FILE_TYPE))//余额表
                    {
                        //dict.Clear();
                        paraDict.Add("dataClass", "ErYebOut");
                        paraDict.Add("C_PORT_CODE", erInfo.C_PORT_CODE);
                        paraDict.Add("D_GZ_DATE", erInfo.D_DATE);
                        //return kmbService.queryByCondition(dict, pageIns);
                    }
                }
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500037", _formFun, status));
                ClsBaseException.DiscardException(ex);
            }

            return paraDict;
        }

        private QueryRes getResByMainPojo(ErReveInfo erInfo)
        {
            Dictionary<string, string> dict = new Dictionary<string, string>();
            if (ReveElecDVCons.DZ_TYPE_GZB.Equals(erInfo.C_FILE_TYPE))//估值表
            {
                dict.Clear();
                dict.Add("dataClass", "ErGzbOut");
                dict.Add("C_PORT_CODE", erInfo.C_PORT_CODE);
                dict.Add("D_GZ_DATE", erInfo.D_DATE);
                return gzbService.queryByCondition(dict);
            }
            else if (ReveElecDVCons.DZ_TYPE_KMB.Equals(erInfo.C_FILE_TYPE))//科目表
            {
                dict.Clear();
                dict.Add("dataClass", "ErKmbOut");
                dict.Add("C_PORT_CODE", erInfo.C_PORT_CODE);
                return kmbService.queryByCondition(dict);
            }
            else if (ReveElecDVCons.DZ_TYPE_YEB.Equals(erInfo.C_FILE_TYPE))//余额表
            {
                dict.Clear();
                dict.Add("dataClass", "ErYebOut");
                dict.Add("C_PORT_CODE", erInfo.C_PORT_CODE);
                dict.Add("D_GZ_DATE", erInfo.D_DATE);
                return kmbService.queryByCondition(dict);
            }
            else
            {
                return new QueryRes();
            }
        }

        //protected  void loadTableData(ErReveInfo erInfo)
        //{
        //    QueryRes res = getResByMainPojo(erInfo);
        //    TableListLoader listLoader = new TableListLoader();
        //    listLoader.FunCode = this.YssFormMenu.C_FUN_CODE;
        //    listLoader.AutoLoadEnclosure = this.AutoLoadEnclosure;
        //    listLoader.loadTable(this.tbMain, res, bShowRowCheckBoxColumn, bShowRowIndexColumn, YssMainKTableShowMode);

        //    //读取用户自定义列配置信息
        //    this.ReadTableColumnsFromConfig(this.tbMain, this.YssFormMenu.C_FUN_NAME);

        //    ////读取分组列信息。张绍林-20151124
        //    this.ReadTableGroupColumnFromConfig(this.tbMain, this.YssFormMenu.C_FUN_NAME);

        //    ////读取用户自定义列宽信息。张绍林-20151201
        //    this.ReadColumnWidthFromConfig(this.tbMain, this.YssFormMenu.C_FUN_NAME);
        //}
        
        //protected void loadData(ErReveInfo erInfo)
        //{
        //    string dzMode = assMapService.getDzMode(erInfo.C_PORT_CODE,erInfo.C_FILE_TYPE);
        //    if("".Equals(dzMode))//深证通模式显示报文
        //    {
        //        //
        //    }else//其他模式直接显示数据库中的数据
        //    {
        //        loadTableData(erInfo);
        //    }
            
        //}
        
        /// <summary>
        /// 功能：获取查询结果以供子类复写
        /// </summary>
        /// <param name="paraDict">参数集合</param>
        /// <param name="pageIns">分页信息</param>
        /// <returns>返回结果</returns>
        protected override QueryRes getQueryResultMVC(Dictionary<string, string> paraDict, PageInation pageIns)
        {
            BasePojo obj = this.MainDataPojo;
            if (null != obj && obj.GetType() == typeof(ErReveInfo))
            {
                ErReveInfo erInfo = obj as ErReveInfo;

                if (ReveElecDVCons.DZMO_QT.Equals(this.dzMode))//其他模式直接显示数据库中的数据
                {
                    initServiceMVC();
                    if (dataService == null)
                    {
                        return null;
                    }else
                    {
                        return dataService.queryByCondition(paraDict, pageIns);
                    }
                    
                    //return getResByMainPojo(erInfo,paraDict, pageIns);
                } else//深证通模式显示报文
                {
                    return null;
                }
            }
            return null;
            //return dataService.queryByCondition(paraDict, pageIns);
        }

        /// <summary>
        /// 获取数据总数量
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>总数量</returns>
        protected override int getDataTotal(Dictionary<string, string> paraDict)
        {
            BasePojo obj = this.MainDataPojo;
            if (null != obj && obj.GetType() == typeof(ErReveInfo))
            {
                ErReveInfo erInfo = obj as ErReveInfo;
                string dzMode = assMapService.getDzMode(erInfo.C_PORT_CODE, erInfo.C_FILE_TYPE);
                if ("".Equals(dzMode))//深证通模式显示报文
                {
                    return 0;
                }
                else//其他模式直接显示数据库中的数据
                {
                    initServiceMVC();
                    if(this.dataService == null)
                    {
                        return 0;
                    }else
                    {
                        string number = dataService.queryDataTotal(paraDict);
                        int total = Convert.ToInt32(number);
                        return total;
                    }
                    
                }
            }
            return 0;
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

        /// <summary>
        /// 刷新按钮点击后触发事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ELEC_REVE_MESSAGE_DETAIL_L_YssOnAfterRefreshClick(object sender, EventArgs e)
        {
            foreach (Column column in tbMain.Columns)
            {
                if (column.DataPropertyName.Equals("C_TGH_CODE"))
                {
                    tbMain.GroupBy(column);
                    break;
                }
            }
        }
        

    }
}
