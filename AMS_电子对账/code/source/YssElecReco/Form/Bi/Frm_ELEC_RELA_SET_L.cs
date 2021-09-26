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
using FAST.Core.Util;
using YssInformation.Support.Sys.Dictionary.Service;
using FAST.Core.Communication.DataService;
using Yss.Controls;

namespace YssElecReco.Form.Bi
{
    /// <summary>
    /// 个性指标
    /// </summary>
    public partial class Frm_ELEC_RELA_SET_L : FrmBaseList
    {
        /// <summary>
        ///通用指标 
        /// </summary>
        private QueryRes pubLeftDateRes = null;

        /// <summary>
        /// 个性指标
        /// </summary>
        private QueryRes perLeftDateRes = null;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_ELEC_RELA_SET_L()
        {
            InitializeComponent();
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
        /// 加载窗体
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ELEC_RELA_SET_L_Load(object sender, EventArgs e)
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
                        new ClsInterface().OpenTabPageAndForm(tabCtlMain, sysFun);
                    }
                }
            }
            ////窗体加载完成后，设置将关闭按钮置为不可见
            setCloseButton(false);
            tabCtlMain.SelectedTab = tabCtlMain.TabPages[0];
        }

        /// <summary>
        ///  设置关闭按钮状态
        /// </summary>
        /// <param name="bVasible">bVasible</param>
        private void setCloseButton(bool bVasible)
        {
            if (tabCtlMain.TabPages.Count > 0)
            {
                for (int i = 0; i < tabCtlMain.TabPages.Count; i++)
                {
                    tabCtlMain.TabPages[i].TabCloseButtonVisible = false;
                }
            }
        }

        /// <summary>
        ///  获取需要添加的窗体funCode，可添加多个
        /// </summary>
        /// <returns>List</returns>
        private List<string> getFunCodeList()
        {
            List<string> funCodeList = new List<string>();
            funCodeList.Add("dzPubRela");
            funCodeList.Add("dzPerRela");
            return funCodeList;
        }

        /// <summary>
        /// list界面加载A区数据，子类重写.
        /// </summary>
        /// <author>yh 2011.02.28.</author>
        /// <returns>返回查询结果.</returns>
        public override QueryRes yssGetLeftDataMVC()
        {
            if (tabCtlMain != null && tabCtlMain.SelectedTab != null && tabCtlMain.SelectedTab != tabCtlMain.TabPages[0])
            {
                this.matchSearchStr = new string[2] { "C_PORT_CODE", "C_PORT_NAME" }; // 【搜索】功能匹配的属性
                if (perLeftDateRes == null)
                {
                    perLeftDateRes = loadPortData();
                }

                return perLeftDateRes;

            }
            else
            {
                if (pubLeftDateRes == null)
                {
                    string funCode = "dzcode"; //// 要获取数据的功能代码
                    string headKeys = "C_DZ_CODE~!~C_DZ_NAME"; //// 自定义列头,此时为词汇类型代码
                    //// 获取数据类型
                    string cacheType = "";
                    string result = null;
                    //// 设定左侧数据的加载方式
                    YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;

                    IDzTypeDataService iDzService = DataServiceFactory.createService<IDzTypeDataService>();
                    QueryRes res = iDzService.getDataListRes();
                    //// 保证A区列头名称在代码之前
                    res.ListHeadList = reversalHeadList(res, "C_DZ_CODE", "C_DZ_NAME");
                    pubLeftDateRes = res;
                }

                this.matchSearchStr = new string[2] { "C_DZ_CODE", "C_DZ_NAME" }; //// 【搜索】功能匹配的属性
                return pubLeftDateRes;
            }
        }

        /// <summary>
        /// tabCtlMain_SelectedIndexChanged
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void tabCtlMain_SelectedIndexChanged(object sender, TabPageEventArgs e)
        {
            this.tbLeftMain.Clear();
            yssLoadLeftData();
        }

        /// <summary>
        /// 查询
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        public override void btnSearch_Click(object sender, EventArgs e)
        {
            if (tabCtlMain.SelectedTab != null)
            {
                (tabCtlMain.SelectedTab.Controls[0] as FrmBaseList).btnSearch_Click(sender, e);
            }
        }
    }
}
