using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.CRUD.Form;
using FAST.Core.Context;
using FAST.Common.Service.Pojo;
////using YssBaseCls.Fun;
using FAST.Core.Communication.Service;
using FAST.Core.Util;
using FAST.Core.Resource;
using FAST.Core.BaseControl.Fun;
using YssInformation.Support.Sys.ConvertDict.Zhzd.Service;
using YssInformation.Support.Sys.ConvertDict.Zdorg.Service;
using YssInformation.Support.Sys.ConvertDict.Zdorg.Pojo;
using YssInformation.Support.Fun;

namespace YssInformation.Sys.ConvertDict.Zhzd.Form
{
    public partial class Frm_SWITCH_DICT_L : FrmBaseList
    {
        /// <summary>
        /// 窗体A区服务对象
        /// </summary>
        private IZdOrgService zdOrgService = null;

        /// <summary>
        /// 窗体B区服务对象
        /// </summary>
        private IZhzdService zhzdService = null;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_SWITCH_DICT_L()
        {
            InitializeComponent();
            hasLeftSetForm = true;

            bUseMVCServiceLeft = true;
            bUseMVCService = true;
            this.YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;
            ////this.YssOnAfterLoadLeftViewMVC += new AfterRefreshLeftViewMVC(YssOnAfterLoadLeftViewMVC); 
            ////this.YssOnAfterLoadLeftViewMVC += new AfterRefreshLeftViewMVC(Frm_Execute_YssOnAfterLoadLeftViewMVC);
        }

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

        /// <summary>
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写
        /// </summary>
        /// <returns>查询条件</returns>
        public override string yssInitQuery()
        {
            // 1 查询条件
            string cond = "";

            // 3 初始只加载列头，
            this.IsOnlyHeder = true;
            return cond;
        }

        /// <summary>
        /// 封装条件对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.zhzdService = ServiceFactory.createService(serviceType) as IZhzdService;
            this.dataService = this.zhzdService;
            if (null != this.tbLeftMain.SelectedRow)
            {
                ZdCorpOrg zdCorpOrg = (ZdCorpOrg)this.tbLeftMain.SelectedRow.Tag;
                ////CorpOrg corpOrg = (CorpOrg)this.tbLeftMain.SelectedRow.Tag;
                paraDict.Add("C_GROUP_CODE", zdCorpOrg.C_GROUP_CODE);
                paraDict.Add("C_GROUP_CODE_P", zdCorpOrg.C_GROUP_CODE_P);
                ////paraDict.Remove("dataClass");
                ////paraDict.Add("dataClass", "Zhzd");
            }
            else
            {
                //// edit by liuxing 2014-04-24 第一次加载时不显示B区
                paraDict.Add("C_GROUP_CODE", "");
                paraDict.Add("C_GROUP_CODE_P", "");
            }

            return paraDict;
        }

        /// <summary>
        /// list界面加载A区数据，子类重写.
        /// </summary>
        /// <returns>查询数据对象</returns>
        public override QueryRes yssGetLeftDataMVC()
        {
            QueryRes res = null;
            //// 【搜索】功能匹配的属性
            this.matchSearchStr = new string[2] { "C_GROUP_CODE", "C_GROUP_NAME" };
            ////此处因测试需要注释  测试完毕后  放开  马向峰 base_zdorg
            ////leftFormFunCode = "zdorg";
            ////leftDataFunCode = "zdorg";
            leftFormFunCode = "base_zdorg";
            leftDataFunCode = "base_zdorg";
            if (zdOrgService == null)
            {
                ClsAssocia asc = new ClsAssocia();
                ////此处因测试需要注释  测试完毕后  放开  马向峰 base_zdorg
                ////asc = ClsClzCfgMgr.getAssociaParam("zdorg");
                asc = ClsClzCfgMgr.getAssociaParam("base_zdorg");

                Type serviceTypeA = ReflectBase.YssGetType(asc.ServiceDllName, asc.ServiceName);
                zdOrgService = (IZdOrgService)ServiceFactory.createService(serviceTypeA);
            }

            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            res = zdOrgService.getTreeViewData(paraDict);
            //// 保证A区列头名称在代码之前
            res.ListHeadList = reversalHeadList(res, "C_GROUP_CODE", "C_GROUP_NAME");
            ////this.txtSearch.Tag = this.buildMatchTableMVC(null, null);
            return res;
        }

        /// <summary>
        /// 获取list查询条件区的查询条件
        /// </summary>
        /// <returns>查询条件</returns>
        public string yssGetListCond()
        {
            string cond = "";
            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();

            // 拼接父节点和子节点到后台进行查询
            if (this.tbLeftMain.SelectedRow != null)
            {
                ////cond = ((Cls_CORP_ORG)this.tbLeftMain.SelectedRow.Tag).C_CORP_ORG_CODE_P + "~!~" + ((Cls_CORP_ORG)this.tbLeftMain.SelectedRow.Tag).C_CORP_ORG_CODE;
                quyStrUtil.addQuyCon("C_GROUP_CODE_P", ((Cls_ZD_ORG)this.tbLeftMain.SelectedRow.Tag).C_GROUP_CODE_P, "");
                quyStrUtil.addQuyCon("C_GROUP_CODE", ((Cls_ZD_ORG)this.tbLeftMain.SelectedRow.Tag).C_GROUP_CODE, "");
            }

            cond = quyStrUtil.getQuyStr();
            return cond;
        }

        /// <summary>
        /// 重写行单击事件 如果是父节点不能新增,修改，删除，复制
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbLeftMain_RowClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            base.tbLeftMain_RowClicked(sender,e);
            changeBtnBarStatue();
        }

        private void tbMain_RowClicked_1(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            changeBtnBarStatue();
        }

        /// <summary>
        /// 改变按钮状态
        /// </summary>
        private void changeBtnBarStatue()
        {
            if (this.tbLeftMain.SelectedRow.SubRows.Count != 0)
            {
                btnBar.setButtonEnabled(ClsButtonName.BtnNew, false);
                btnBar.setButtonEnabled(ClsButtonName.BtnEdit, false);
                btnBar.setButtonEnabled(ClsButtonName.BtnCopy, false);
                btnBar.setButtonEnabled(ClsButtonName.BtnDelete, false);
                if (null != frmBaseViewSet)
                {
                    frmBaseViewSet.btnBar.setButtonEnabled(ClsButtonName.BtnNew, false);
                    frmBaseViewSet.btnBar.setButtonEnabled(ClsButtonName.BtnEdit, false);
                    frmBaseViewSet.btnBar.setButtonEnabled(ClsButtonName.BtnCopy, false);
                    frmBaseViewSet.btnBar.setButtonEnabled(ClsButtonName.BtnDelete, false);
                }
            }
            else
            {
                btnBar.setButtonEnabled(ClsButtonName.BtnNew, true);
                btnBar.setButtonEnabled(ClsButtonName.BtnEdit, true);
                btnBar.setButtonEnabled(ClsButtonName.BtnCopy, true);
                btnBar.setButtonEnabled(ClsButtonName.BtnDelete, true);
                if (null != frmBaseViewSet)
                {
                    frmBaseViewSet.btnBar.setButtonEnabled(ClsButtonName.BtnNew, true);
                    frmBaseViewSet.btnBar.setButtonEnabled(ClsButtonName.BtnEdit, true);
                    frmBaseViewSet.btnBar.setButtonEnabled(ClsButtonName.BtnCopy, true);
                    frmBaseViewSet.btnBar.setButtonEnabled(ClsButtonName.BtnDelete, true);
                }
            }
        }

    }
}
