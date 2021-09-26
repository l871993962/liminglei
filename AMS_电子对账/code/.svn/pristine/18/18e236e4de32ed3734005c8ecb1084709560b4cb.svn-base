using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Pojo;
using FAST.Core.Context;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Communication.DataService;
using FAST.Core.Util;
using FAST.Common.Service.DataService;
using FAST.Common.Service.Dict.Service;
using FAST.Core.Communication.Service;
using YssInformation.Support.Bi.AssociationOrgan.Service;

namespace YssElecReco.Form.Er.Reverse
{
    public partial class Frm_ELEC_REVE_KMMAP_L : FrmBaseList
    {
        public Frm_ELEC_REVE_KMMAP_L()
        {
            InitializeComponent();
        }
        private QueryRes pubLeftDateRes = null;
        private QueryRes tghLeftDateRes = null;
        private QueryRes portLeftDateRes = null;
        /// <summary>
        /// 加载窗体
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ELEC_REVE_KMMAP_L_Load(object sender, EventArgs e)
        {
            List<string> funCodeList = this.getFunCodeList();
            if (null != funCodeList || funCodeList.Count > 0)
            {
                foreach (string funCode in funCodeList)
                {
                    SysFun sysFun = null;
                    if (ClsContext.sysMenuFunHash.ContainsKey(funCode))
                    {
                        sysFun = ClsContext.sysMenuFunHash[funCode] as SysFun;
                    }
                    if (sysFun != null)
                    {
                        this.opTabCtl(tabCtlMain, sysFun);
                    }
                }
            }
            ////窗体加载完成后，设置将关闭按钮置为不可见
            setCloseButton(false);
            tabCtlMain.SelectedTab = tabCtlMain.Tabs[0];
            this.navigateItemMain.Text = "科目类型";
        }

        /// <summary>
        ///  设置关闭按钮状态
        /// </summary>
        /// <param name="isVasible"></param>
        private void setCloseButton(bool isVasible)
        {
            if (tabCtlMain.Tabs.Count > 0)
            {
                for (int i = 0; i < tabCtlMain.Tabs.Count; i++)
                {
                    tabCtlMain.Tabs[i].CloseButtonVisible = false;
                }
            }
        }

        /// <summary>
        ///  获取需要添加的窗体funCode，可添加多个
        /// </summary>
        /// <returns></returns>
        private List<string> getFunCodeList()
        {
            List<string> funCodeList = new List<string>();
            funCodeList.Add("reveDzPublicKmMap");
            funCodeList.Add("reveDzTghKmMap");
            funCodeList.Add("reveDzPortKmMap");
            return funCodeList;
        }

        /// <summary>
        ///  打开窗体
        /// </summary>
        /// <param name="tabCtlMain">主界面</param>
        /// <param name="menu">funCode</param>
        private void opTabCtl(YssDevComponents.DotNetBar.TabControl tabCtlMain, IBaseFun menu)
        {
            if (menu.YssAssocia != null && menu.YssAssocia.ListDllName != "" && menu.YssAssocia.ListFormName != "")
            {
                // 通过反射动态地实例化一个对象
                object objForm = ReflectBase.YssCreateInstance(menu.YssAssocia.ListDllName, menu.YssAssocia.ListFormName);
                if (objForm != null)
                {
                    ((FrmBaseList)objForm).ShowLeftPanel = false;
                    //// 将菜单赋值给进来
                    ((FrmBaseList)objForm).YssFormMenu = menu;
                    //// 获取要展示的窗体
                    try
                    {
                        clsInterface.AddMainPageForm(((FrmBaseList)objForm), tabCtlMain, menu);
                    }
                    catch (Exception ex)
                    {
                    }

                    // 判断对象是否为FrmBaseList的子类
                    if (objForm is FrmBaseList)
                    {
                        ((FrmBaseList)objForm).setBatchParam(tbLeftMain, menu.C_FUN_CODE, menu.C_FUN_NAME);
                        ((FrmBaseList)objForm).yssInitForm();
                        ((FrmBaseList)objForm).Tag = tabCtlMain;
                        ((FrmBaseList)objForm).ShowFilterPanel = true;
                    }
                }
                else
                {
                    YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("模块缺失或被移除"));
                }

            }
        }

        /// <summary>
        /// 重写A区配置信息，走基类老旧模式。
        /// </summary>
        public override AreaAConfigInfo AreaAConfigInfo
        {
            get
            {
                //if (tabCtlMain != null && tabCtlMain.SelectedTab != null)
                //{
                //    SysFun fun = tabCtlMain.SelectedTab.Tag as SysFun;
                //    if ("reveDzPortKmMap".Equals(fun.C_FUN_CODE))
                //    {
                //        base.AreaAConfigInfo.AreaType = AreaType.Port;
                //        return base.AreaAConfigInfo;
                //    }
                //}
                base.AreaAConfigInfo.AreaType = AreaType.BaseDefault;
                return base.AreaAConfigInfo;
            }
        }

        /// <summary>
        /// list界面加载A区数据，子类重写.
        /// </summary>
        /// <author>yh 2011.02.28.</author>
        /// <returns>返回查询结果.</returns>
        public override QueryRes yssGetLeftDataMVC()
        {
            if (tabCtlMain != null && tabCtlMain.SelectedTab != null)
            {
                SysFun fun = tabCtlMain.SelectedTab.Tag as SysFun;
                return getLeftData(fun.C_FUN_CODE);
            }
            else
            {
                return getLeftData("reveDzPublicKmMap");
            }
            //if (tabCtlMain != null && tabCtlMain.SelectedTab != null && tabCtlMain.SelectedTab != tabCtlMain.Tabs[0])
            //{
            //    this.matchSearchStr = new string[2] { "C_PORT_CODE", "C_PORT_NAME" }; // 【搜索】功能匹配的属性
            //    if (tghLeftDateRes == null)
            //    {
            //        tghLeftDateRes = loadPortData();
            //    }
            //    return tghLeftDateRes;

            //}
            //else
            //{
            //    if(pubLeftDateRes == null){
            //        string funCode = "dzcode"; // 要获取数据的功能代码
            //        string headKeys = "C_DZ_CODE~!~C_DZ_NAME"; // 自定义列头,此时为词汇类型代码
            //        // 获取数据类型
            //        string cacheType = "";
            //        string result = null;
            //        //// 设定左侧数据的加载方式
            //        YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;

            //        IDzTypeDataService dzService = DataServiceFactory.createService<IDzTypeDataService>();
            //        QueryRes res = dzService.getDataListRes();
            //        //// 保证A区列头名称在代码之前
            //        res.ListHeadList = reversalHeadList(res, "C_DZ_CODE", "C_DZ_NAME");
            //        pubLeftDateRes = res;
            //    }
            //    this.matchSearchStr = new string[2] { "C_DZ_CODE", "C_DZ_NAME" }; // 【搜索】功能匹配的属性
            //    return pubLeftDateRes;
            //}
        }

        private void tabCtlMain_SelectedTabChanged(object sender, YssDevComponents.DotNetBar.TabStripTabChangedEventArgs e)
        {
            this.tbLeftMain.Clear();
            ////this.chkCheckAll.Checked = false;
            ////this.chkBoxCheckedRowsCount.Checked = false;
            ////this.chkBoxCheckedRowsCount.Text = "0";
            yssLoadLeftData();
        }

        private QueryRes getLeftData(String funCode)
        {
            
            if ("reveDzPublicKmMap".Equals(funCode))
            {
                this.navigateItemMain.Text = "科目类型";
                //// 设定左侧数据的加载方式
                //采用list加载，tree加载会出错
                YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.ListMode;
                if (pubLeftDateRes == null)
                {
                    Dictionary<string, string> paraDict = new Dictionary<string, string>();
                    paraDict.Add("dataClass", "Vocabulary");
                    paraDict.Add("C_DV_TYPE", "KM_CLS");
                    IVocabularyService vocabularyService = ServiceFactory.createService<IVocabularyService>();
                    //QueryRes res = vocabularyService.getQueryResByTypes(new string[] {"KM_CLS"});
                    QueryRes res = vocabularyService.getVocByType(paraDict);
                    // 保证A区列头名称在代码之前
                    res.ListHeadList = reversalHeadList(res, "C_DV_CODE", "C_DV_NAME");
                    pubLeftDateRes = res;
                }
                this.matchSearchStr = new string[2] { "C_DV_CODE", "C_DV_NAME" }; // 【搜索】功能匹配的属性
                return pubLeftDateRes;
            }
            else if ("reveDzTghKmMap".Equals(funCode))
            {
                this.navigateItemMain.Text = "托管机构";
                //// 设定左侧数据的加载方式
                YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;
                if (tghLeftDateRes == null)
                {
                    //// 设定左侧数据的加载方式
                    //YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;
                    Dictionary<string, string> paraDict = new Dictionary<string, string>();
                    paraDict.Add("dataClass", "Org");
                    paraDict.Add("ARRAY_C_QUALIFICATION", "TRUSTEE,TRUSTEE_SEC");//// 加载托管行（商业银行）
                    IOrgService orgDataService = DataServiceFactory.createService<IOrgService>();
                    QueryRes res = orgDataService.queryByCondition(paraDict);
                    //// 保证A区列头名称在代码之前
                    res.ListHeadList = reversalHeadList(res, "C_ORG_CODE", "C_ORG_NAME");
                    tghLeftDateRes = res;
                }
                this.matchSearchStr = new string[2] { "C_ORG_CODE", "C_ORG_NAME" }; // 【搜索】功能匹配的属性
                return tghLeftDateRes;
            }else{
                this.navigateItemMain.Text = "产品组合";
                //// 设定左侧数据的加载方式
                YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;
                
                if (portLeftDateRes == null)
                {
                    portLeftDateRes = loadPortData();
                    // 保证A区列头名称在代码之前
                    portLeftDateRes.ListHeadList = reversalHeadList(portLeftDateRes, "C_PORT_CODE", "C_PORT_NAME_ST");
                }
                this.matchSearchStr = new string[3] { "C_PORT_CODE", "C_PORT_NAME", "C_PORT_NAME_ST" }; // 【搜索】功能匹配的属性
                return portLeftDateRes;
            }
        }
        
    }
}