using System;
using System.Collections.Generic;
using System.ComponentModel;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Context;
using FAST.Core.Context.Events;
using FAST.Common.Service.Pojo;
using FAST.Core.Communication.Service;
using FAST.Core.Util;
using FAST.Core.Resource;
using FAST.Core.BaseControl.Fun;
using FAST.Core.CRUD.Interface;
using FAST.Core.BaseControl.Pojo;
using FAST.Core.Exceptions;
using Yss.KRichEx.AutoFilter.Model;
using YssSztTool.Service.Para;
using YssSztTool.Pojo.Para;
using YssInformation.Support.Bi.AssociationOrgan.Pojo;

namespace YssSztTool.Form.Para
{
    /// <summary>
    /// 电子对账参数设置
    /// 作为机构的子菜单: 
    /// </summary>
    public partial class Frm_DZ_PARA_L : FrmBaseListWithDetails, IFormDetailData
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
        /// 机构信息Service
        /// </summary>
        private IErOrgService erOrgService = null;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_DZ_PARA_L()
        {
            this.bUseMVCService = true;
            this.bUseMVCServiceLeft = true;
            InitializeComponent();
            this.loadDataWhenFormLoad = true;
            isLoadFirst = true;
            erOrgService = ServiceFactory.createService<IErOrgService>();
        }

        #region #作为子窗体
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
                ////业务判断，如果机构资质包含 托管人
                ////或者机构类型为 银行或者基金公司时查询
                ////Org org = mainData as Org;
                ////if (org.C_QUALIFICATION.Contains("托管人"))
                ////{
                ////    retValue = true;
                ////}
                ////else if (org.C_DV_ORG_TYPE.Equals("ORG_SYYH"))
                ////{
                ////    retValue = true;
                ////}
                ////else
                ////{
                ////    this.MainDataPojo = mainData;
                ////    this.tbMain.Rows.Clear();
                ////    this.tbMain.Refresh();
                ////}
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

            this.ShowLeftPanel = false;
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
                page = new ClsPageInation();
            }

            page.CurrPage = 1;
            page.PageCount = 0;

            bool validate = this.AllowReloadDetailData(mainData);
            if (validate)
            {
                this.AllowResetGeneParaAssemble = false;
                Org org = new Org();
                string orgJson = JsonUtil.toJson(mainData);
                org = (Org)JsonUtil.toObject(orgJson, org.GetType());
                this.MainDataPojo = org;
                this.geneParaAssemble.Clear();
                this.geneParaAssemble.Add("dataClass", "DzPara");
                this.geneParaAssemble.Add("C_TGH_CODE", org.C_ORG_CODE);
                ////this.geneParaAssemble.Add("C_CONTRACT_CODE", (mainData as YssPara.Pojo.Sv.SecBase).C_SEC_CODE);
                ////验证通过，开始装载数据
                this.btnSearch_Click(this.btnBar.getButton(ClsButtonName.BtnRefresh).Owner, EventArgs.Empty);
            }
        }

        /// <summary>
        /// 添加查询条件
        /// </summary>
        /// <param name="paraDict">查询条件目标集合</param>
        /// <returns>查询条件集合</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            ////托管行查询条件
            ////BUG #244888::电子对账参数设置界面选择机构名称点击查询报错
            if (this.cboTgh.Value != null && this.cboTgh.Value.Trim().Length > 0)
            {
                string tghCodes = this.cboTgh.Value.Trim();
                tghCodes = tghCodes.Replace("|", ",");
                ////foreach (ControlEntity entity in cboTgh.CheckedItems)
                ////{
                ////    ErOrg org = entity.DataEntity as ErOrg;
                ////    tghCodes += org.C_ORG_CODE + ",";
                ////}

                paraDict.Add("ARRAY_C_TGH_CODE", tghCodes);
            }

            return paraDict;
        }

        #endregion

        #endregion #子窗体

        #region #父窗体
        /// <summary>
        /// 装载明细窗体功能代码列表
        /// </summary>
        /// <returns>返回明细窗体功能代码列表</returns>
        protected override List<SysFun> LoadDetailFormFuns()
        {
            List<SysFun> sysFuns = new List<SysFun>();
            ////if (!ClsContext.sysMenuFunHash.ContainsKey("portorgan")) 
            ////{
            ////    SysFun newFun = new SysFun();
            ////    newFun.C_FUN_CODE = "portorgan";
            ////    newFun.C_FUN_NAME = "组合关联机构";
            ////    ClsContext.sysMenuFunHash.Add("portorgan", newFun);
            ////}

            SysFun fun = ClsContext.sysMenuFunHash["portrelation"] as SysFun;
            SysFun sysFun = fun.Clone() as SysFun;
            sysFun.C_FUN_CODE = "dzRelaOrgan";
            sysFun.C_FUN_NAME = "关联组合设置";
            sysFuns.Add(sysFun);
            if (!ClsContext.HtUserOperRight.ContainsKey(sysFun.C_FUN_CODE))
            {
                ClsContext.HtUserOperRight.Add(sysFun.C_FUN_CODE, ClsContext.HtUserOperRight["portrelation"]);
            }
            if (!ClsContext.HtFunRight.ContainsKey(sysFun.C_FUN_CODE))
            {
                ClsContext.HtFunRight.Add(sysFun.C_FUN_CODE, ClsContext.HtFunRight["portrelation"]);
            }

            if (!ClsContext.sysFunHash.ContainsKey(sysFun.C_FUN_CODE))
            {
                sysFun.YssAssocia = ClsClzCfgMgr.getAssociaParam(sysFun.C_FUN_CODE);
                ClsContext.sysFunHash.Add(sysFun.C_FUN_CODE, sysFun);
            }

            sysFun = new SysFun();
            sysFun.C_FUN_CODE = "dzRepCfg";
            sysFun.C_FUN_NAME = "电子对账报表配置";
            sysFuns.Add(sysFun);

            return sysFuns;
        }

        #endregion 父窗体

        /// <summary>
        /// 重写A区配置信息，走基类老旧模式。
        /// </summary>
        public override AreaAConfigInfo AreaAConfigInfo
        {
            get
            {
                base.AreaAConfigInfo.AreaType = AreaType.BaseDefault;
                return base.AreaAConfigInfo;
            }
        }

        /////// <summary>
        /////// list界面加载A区数据，子类重写.
        /////// </summary>
        /////// <returns>A区数据查询结果</returns>
        ////public override QueryRes yssGetLeftDataMVC()
        ////{
        ////    QueryRes res = null;
        ////    try
        ////    {
        ////        this.matchSearchStr = new string[2] { "C_ORG_NAME", "C_ORG_CODE" };
        ////        this.YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.ListMode;
        ////        Dictionary<string, string> paraDict = new Dictionary<string, string>();
        ////        paraDict.Add("dataClass", "Org");
        ////        ////paraDict.Add("ARRAY_C_DV_ORG_TYPE", "ORG_SYYH,");//// 加载托管行（商业银行）
        ////        paraDict.Add("ARRAY_C_QUALIFICATION", "TRUSTEE,TRUSTEE_SEC,TRUSTEE_MA");//// 加载托管行（商业银行）
        ////        res = orgService.queryByCondition(paraDict);
        ////        res.ListHeadList = reversalHeadList(res, "C_ORG_CODE", "C_ORG_NAME");
        ////    }
        ////    catch (Exception ex)
        ////    {
        ////        YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-100002", _formFun, ClsEnums.StatusSetting.YssBrow, ex));
        ////    }

        ////    return res;
        ////}

        /// <summary>
        /// 11 屏蔽右键菜单
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void tbLeftMain_ContextMenuOpening(object sender, CancelEventArgs e)
        {
            e.Cancel = true;
        }

        /// <summary>
        /// 加载托管行
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboTgh_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            e.IsCancel = true;
            if (this.cboTgh.Items.Count == 0)
            {
                List<ErOrg> orgList = erOrgService.getTrusteeOrgs();
                if (orgList != null && orgList.Count > 0)
                {
                    foreach (ErOrg org in orgList)
                    {
                        Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(org);
                        e.Collection.Add(entity);
                    }
                }
            }
            ////Dictionary<string, string> paraDict = new Dictionary<string, string>();
            ////paraDict.Add("dataClass", "Org");
            ////paraDict.Add("ARRAY_C_QUALIFICATION", "TRUSTEE,TRUSTEE_SEC,TRUSTEE_MA");//// 加载托管行（商业银行）
            ////////List<BasePojo> orgList = orgService.queryByCondition(paraDict).DataList;
            ////QueryRes res = orgService.queryByCondition(paraDict);
            ////List<BasePojo> orgList = new List<BasePojo>();
            ////orgList.AddRange(res.DataList);

            ////if (orgList != null && orgList.Count > 0)
            ////{
            ////    foreach (Org org in orgList)
            ////    {
            ////        if (org.AuditState == 1)
            ////        {
            ////            Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(org);
            ////            e.Collection.Add(entity);
            ////        }
            ////    }
            ////}

        }

        private void Frm_DZ_PARA_L_Load(object sender, EventArgs e)
        {
            ////STORY42660【中国银行】深证通伺服器要求采用热备模式
            ClsButtonInfo btnMrPara = new ClsButtonInfo();
            btnMrPara.Text = "伺服器";
            btnMrPara.Tooltip = "伺服器信息设置";
            btnMrPara.Name = "btnMrPara";
            btnMrPara.Image = FAST.Resource.Resource.btnEdit_L;
            btnMrPara.ClickEvent = btnMrPara_Click;
            btnBar.addButton(btnMrPara, 7);
        }

        /// <summary>
        ///  STORY42660【中国银行】深证通伺服器要求采用热备模式
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected void btnMrPara_Click(object sender, EventArgs e)
        {
            try
            {
                ////打开“伺服器信息设置”界面
                SysFun cls_FUN = ClsContext.sysMenuFunHash["erPara"] as SysFun;
                Yss.Controls.TabPage tabPage = clsInterface.OpenTabPageAndForm(ClsContext.MainFormTabControl, cls_FUN);
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }
    }
}
