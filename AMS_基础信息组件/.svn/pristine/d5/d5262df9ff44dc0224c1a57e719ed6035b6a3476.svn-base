using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Core.Exceptions;

using FAST.Core.Context;

using FAST.Core.Resource;

using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

using System.Collections;
using Yss.KTable.Models;










namespace YssInformation.Bi.TaselNet.Form
{
    /// <summary>
    /// 功能简介：TA销售网点信息浏览界面，负责TA销售网点信息的显示和查询功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.10
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：yh
    /// 修改日期：2010.12.12
    /// 修改简介：实现方法
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：wuwenlan
    /// 修改日期：2011.02.16
    /// 修改简介：添加了自定义表头字段
    /// 回收站机制和pojo类的属性变化了
    /// 对应的进行修改
    /// </summary>
    public partial class Frm_SALES_NET_L : FrmBaseListWithDetails
    {
        /// <summary>
        /// 用于存放所选中的机构代码
        /// </summary>
        private string strTANet = "";

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_SALES_NET_L()
        {
            this.bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 初始化查询模块控件.
        /// </summary>
        /// <returns>返回查询条件.</returns>
        public override string yssInitQuery()
        {
            //// 所有提供的参数项如下，只需要设置子类需要的项即可
            //// 1 查询条件
            string cond = "";

            //// 3 初始只加载列头，若需要则设为true，反之不需要设置此参数
            IsOnlyHeder = true;

            return cond;
        }

        /////// <summary>
        /////// list界面加载A区数据，子类重写.
        /////// </summary>
        /////// <author>wuwenlan 2011.03.2.</author>
        /////// <returns>返回a区数据.</returns>
        ////public override string yssGetLeftData()
        ////{
        ////    string result = null;
        ////    //// edit by yh 2011.03.09修改数据来源为枚举类型,防止赋值出错
        ////    ClsEnums.DataSrc dataSrc = ClsEnums.DataSrc.SrcCache; // 数据来源是缓存
        ////    string funCode = "pubvocabulary"; // 要获取数据的功能代码
        ////    string cond = "NET_TYPE"; // 查询条件,此时为词汇类型代码
        ////    string headKeys = "C_DV_NAME"; // 自定义列头,此时为词汇类型代码
        ////    // 获取数据类型
        ////    string cacheType = "CacheType";
        ////    this.matchSearchStr = new string[1] { "C_DV_NAME" }; // 【搜索】功能匹配的属性

        ////    //// 设定左侧数据的加载方式
        ////    YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.ListMode;
        ////    //// 调用由子类提供参数的查询方法
        ////    result = this.yssGetLeftData(dataSrc, funCode, cond, headKeys, cacheType);
        ////    return result;
        ////}

        /////// <summary>
        /////// 获取list查询条件区的查询条件.
        /////// </summary>
        /////// <returns>返回查询数据.</returns>
        ////public string yssGetListCond()
        ////{
        ////    string cond = "";

        ////    ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();

        ////    //// 根据网点代码进行查询
        ////    if (this.txtTANetCode.Text.Trim() != "")
        ////    {
        ////        ////cond = " and a.C_NET_CODE like '%" + txtTANetCode.Text.Trim() + "'";  // liuping  2011-03-28  BUG #1232 【产品销售网点】建议查询条件【网点代码】为模糊查询
        ////        quyStrUtil.addQuyCon("C_NET_CODE", txtTANetCode.Text.Trim(), ClsConstant.SQL_RA_HYPHEN_LIKE);
        ////    }

        ////    //// 根据网点名字进行查询
        ////    if (this.txtTANetName.Text.Trim() != "")
        ////    {
        ////        ////cond += " and a.C_NET_NAME like '%" + txtTANetName.Text.Trim() + "%'";
        ////        quyStrUtil.addQuyCon("C_NET_NAME", txtTANetName.Text.Trim(), ClsConstant.SQL_RA_HYPHEN_LIKE);
        ////    }

        ////    string search = this.yssBuildLeftCheckRowsStr("pubvocabulary");  // tanwenjie 2011.7.28 获取A区选中的行
        ////    ////cond += " and a.C_DV_NET_TYPE in (" + search + ")";
        ////    quyStrUtil.addQuyCon("portCode", "C_DV_NET_TYPE", search, "IN");
        ////    cond = quyStrUtil.getQuyStr("pubvocabulary");

        ////    return cond;
        ////}

        /// <summary>
        /// 封装条件对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            ////string search = this.yssBuildLeftCheckRowsStr("pubvocabulary");
            ////search = search.Replace("'", "");
            ////paraDict.Add("ARRAY_C_DV_NET_TYPE", search);

            //// 根据网点代码进行查询
            if (this.txtTANetCode.Text.Trim().Length > 0)
            {
                paraDict.Add("C_NET_CODE", "%" + txtTANetCode.Text.Trim() + "%");
            }

            //// 根据网点名字进行查询
            if (this.txtTANetName.Text.Trim().Length > 0)
            {
                paraDict.Add("C_NET_NAME", "%" + txtTANetName.Text.Trim() + "%");
            }

            //// 根据网点类型进行查询
            if (this.cboNetType.Value != null && this.cboNetType.Value.Trim().Length > 0)
            {
                if (!"ALL_XSWD".Equals(this.cboNetType.Value))
                {
                    paraDict.Add("C_DV_NET_TYPE", this.cboNetType.Value);
                }
               
            }

            return paraDict;
        }

        /// <summary>
        /// 重写数据加载方法
        /// STORY #82131 【交银施罗德】2个操作界面优化需求 
        /// </summary>
        /// <param name="res">res</param>
        protected override void loadListContentMVC(QueryRes res)
        {
            TableListLoader listLoader = new TableListLoader();
            listLoader.FunCode = this.YssFormMenu.C_FUN_CODE;
            listLoader.AutoSort = false;
            listLoader.AutoLoadEnclosure = this.AutoLoadEnclosure;
            listLoader.loadTable(tbMain, res, bShowRowCheckBoxColumn, bShowRowIndexColumn, YssMainKTableShowMode);

            ////读取用户自定义列配置信息
            this.ReadTableColumnsFromConfig(this.tbMain, this.YssFormMenu.C_FUN_CODE);
            ////读取分组列信息。
            this.ReadTableGroupColumnFromConfig(this.tbMain, this.YssFormMenu.C_FUN_CODE);

            ////读取用户自定义列宽信息。张绍林-20151201
            this.ReadColumnWidthFromConfig(this.tbMain, this.YssFormMenu.C_FUN_CODE);

            if (this.clsInterface == null)
            {
                this.clsInterface = new ClsInterface();
            }

            ////STORY #72474 内容区列表头增加排序记忆功能 hp 20190712
            ClsInterface.ReadTableSortColumn(this.tbMain, this.YssFormMenu);
        }


        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SALES_NET_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.dataService = ServiceFactory.createService(serviceType) as IServiceBus;
        }

        /// <summary>
        /// 装载明细窗体功能代码列表
        /// </summary>
        /// <returns>返回明细窗体功能代码列表</returns>
        ////protected override List<SysFun> LoadDetailFormFuns()
        ////{
        ////    List<SysFun> sysFuns = new List<SysFun>();
        ////    SysFun newFun = new SysFun();
        ////    newFun.C_FUN_CODE = "tasettle";
        ////    sysFuns.Add(newFun);
        ////    return sysFuns;
        ////}
    }
}


