using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.Collections;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;
using FAST.Core.Util;
using FAST.Core.Communication.Service;
using FAST.Core.Context;
using FAST.Common.Service.Pojo;
using YssInformation.Support.Sys.ConvertDict.Zdorg.Service;
using YssInformation.Support.Sys.ConvertDict.Zdorg.Pojo;
using YssInformation.Support.Sys.ConvertDict.Zhzd.Service;

namespace YssInformation.Sys.ConvertDict.Zdorg.Form
{
    public partial class Frm_ZHZD_ORG_S : FrmBaseSet
    {
        /// <summary>
        /// 窗体服务对象
        /// </summary>
        private IZdOrgService zdOrgService = null;

        public Frm_ZHZD_ORG_S()
        {
            InitializeComponent();
            this.bUseMVCService = true;
        }

        /// <summary>
        /// 封装窗体数据到对象
        /// </summary>
        /// <returns>Pojo</returns>
        public override AuditableParamPojo faceInfoToObjMVC()
        {
            ZdCorpOrg zdCorpOrg = null;
            try
            {
                zdCorpOrg = new ZdCorpOrg();

                zdCorpOrg.C_GROUP_CODE = this.txtZdOrgCode.Text;
                zdCorpOrg.C_GROUP_NAME = this.txtZdOrgName.Text;
                zdCorpOrg.C_DV_SCENE = this.yssSelScene.Value;
                if (this.cboZdOrgCodeP.Value == null)
                {
                    zdCorpOrg.C_GROUP_CODE_P = "[root]";
                }
                else
                {
                    zdCorpOrg.C_GROUP_CODE_P = cboZdOrgCodeP.Value;
                }

            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return zdCorpOrg;
        }

        /// <summary>
        /// 展示对象属性到窗体
        /// </summary>
        /// <param name="pojo">Pojo</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            try
            {
                ZdCorpOrg zdCorpOrg = (ZdCorpOrg)this.frmBaseViewList.yssGetSelTypeItemMVC(frmBaseViewList.tbLeftMain);

                if (zdCorpOrg == null)
                {
                    return;
                }

                this.txtZdOrgCode.Text = zdCorpOrg.C_GROUP_CODE;
                this.txtZdOrgName.Text = zdCorpOrg.C_GROUP_NAME;
                this.yssSelScene.Value = zdCorpOrg.C_DV_SCENE;
                if (!zdCorpOrg.C_GROUP_CODE_P.Equals("[root]"))
                {
                    this.cboZdOrgCodeP.Value = zdCorpOrg.C_GROUP_CODE_P;
                }

            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /////// <summary>
        /////// 复写了基类，在加载set窗体时窗体状态为“新增”时调用，用于对新增数据作默认值设置。
        /////// 
        /////// </summary>
        ////public override void yssShowInfoAddForm()
        ////{
        ////    string result = "";
        ////    long rootCnt = 0;

        ////    if (status == ClsEnums.StatusSetting.YssAdd)
        ////    {
        ////        ////TODO chenlong(2014-04-19)
        ////        ////result = (string)dataAdmin.GetSpecValue("", "checkHasRoot");
        ////        dataAdmin.convertOperMenus();
        ////        IZdOrgService zdOrgService = ServiceFactory.createService<IZdOrgService>();
        ////        result = zdOrgService.checkHasRootNode();
        ////        if (!"Fault".Equals(result))
        ////        {
        ////            rootCnt = long.Parse(result);

        ////            if (rootCnt == 0)
        ////            {
        ////                this.cboZdOrgCodeP.Value = "[root]";
        ////                this.cboZdOrgCodeP.YssIsMust = false;
        ////            }
        ////        }
        ////    }

        ////}

        /// <summary>
        /// 窗体加载事件
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Frm_ZHZD_ORG_S_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.zdOrgService = ServiceFactory.createService(serviceType) as IZdOrgService;
            this.dataService = this.zdOrgService;
        }

        private void cboZdOrgCodeP_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            e.Cancel = true;

            if (this.status != ClsEnums.StatusSetting.YssDel)
            {
                try
                {
                    QueryRes res = null;
                    ////e.ShowDropDownForm = false;

                    this.cboZdOrgCodeP.Items.Clear();
                    Dictionary<string, string> paraDict = new Dictionary<string, string>();
                    List<BasePojo> zdOrgList = new List<BasePojo>();
                    Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
                    this.zdOrgService = ServiceFactory.createService(serviceType) as IZdOrgService;
                    res = this.zdOrgService.getTreeViewData(paraDict);
                    zdOrgList = res.DataList;
                    this.cboZdOrgCodeP.DisplayName = "C_GROUP_NAME";
                    this.cboZdOrgCodeP.DisplayValue = "C_GROUP_CODE";
                    this.cboZdOrgCodeP.Parameter = "C_GROUP_NAME~C_GROUP_CODE";
                    this.cboZdOrgCodeP.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Value_Name;
                    foreach (ZdOrgTreeView zdOrgTree in zdOrgList)
                    {
                        this.cboZdOrgCodeP.Items.Add(new Yss.KRichEx.AutoFilter.Model.KTableEntity(zdOrgTree));
                        ////if (zdOrgTree != null)
                        ////{
                        ////    this.cboZdOrgCodeP.Value = zdOrgTree.C_GROUP_CODE;
                        ////}
                    }
                }
                catch (Exception ex)
                {
                    throw new ClsBaseException(ex.Message);
                }

                ////Frm_STOCK_ASSET frm_STOCK_ASSET = new Frm_STOCK_ASSET();
                ////frm_STOCK_ASSET._fun = this._formFun;

                //////// 窗体加载完成后，根据证券代码查询库存
                ////if (frm_STOCK_ASSET.ShowDialog() == DialogResult.OK)
                ////{
                ////    this.stockObj = frm_STOCK_ASSET.tbMain.SelectedRow.Tag as BasePojo;
                ////    if (this.stockObj == null)
                ////    {
                ////        this.selSecurity.Value = "";
                ////    }
                ////    else
                ////    {
                ////        SecBase secbase = this.getSecBaseBySecCode(ReflectBase.getAttrValue("C_SEC_CODE", this.stockObj) as string);
                ////        this.selSecurity.DisplayName = "C_SEC_NAME";
                ////        this.selSecurity.DisplayValue = "C_SEC_CODE";
                ////        this.selSecurity.Parameter = "C_SEC_CODE";
                ////        this.selSecurity.TextFormat = Yss.KRichEx.AutoFilter.AutoTextBox.TextFormatType.Value_Name;
                ////        this.selSecurity.Items.Add(new Yss.KRichEx.AutoFilter.Model.KTableEntity(secbase));
                ////        if (secbase != null)
                ////        {
                ////            this.selSecurity.Value = secbase.C_SEC_CODE;
                ////        }
                ////    }
                ////}
            }
        }

        protected override void btnNew_Click(object sender, EventArgs e)
        {
            base.btnNew_Click(sender, e);
            this.cboZdOrgCodeP.Value = "";
            this.yssSelScene.Value = "";
        }

        /// <summary>
        /// 新增之前先判断此目录下有没有数据，如果有数据则不能新增子目录，如果没有数据就可以新增子目录
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnLeftSave_Click(object sender, System.EventArgs e)
        {
            if (status == ClsEnums.StatusSetting.YssAdd)
            {
                if (!isHaveData())
                {
                    base.btnLeftSave_Click(sender, e);
                }
                else
                {
                    YssMessageBox.currentForm = this;
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getExtWarn("001", _formFun, status));
                }
            }
            else
            {
                base.btnLeftSave_Click(sender, e);
            }
        }

        /// <summary>
        /// 判断此目录下是否有数据，有：true ;没有：false
        /// </summary>
        /// <returns>bool</returns>
        private bool isHaveData()
        {
            bool flag = false;
            IZhzdService zhzdService = ServiceFactory.createService<IZhzdService>();
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("C_GROUP_CODE", this.cboZdOrgCodeP.Value);
            paraDict.Remove("dataClass");
            paraDict.Add("dataClass", "Zhzd");
            QueryRes res = zhzdService.queryByCondition(paraDict);
            if (res.DataList.Count > 0)
            {
                flag = true;
            }
            return flag;
        }

    }
}
