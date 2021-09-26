using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Communication.DataService;
using FAST.Common.Service.DataService.Base;
using FAST.Core.Exceptions;
using FAST.Core.Context;

using FAST.Platform.Right.Fun;
using FAST.Core.BaseControl.Fun;

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




using FAST.Core.BaseControl.Fun;





using FAST.Core.BaseControl.Pojo;
using System.Threading;
////using FAST.Common.Service.DataService;
using YssSecInformation.Support.Pojo.Sec;
using YssSecInformation.Support.Sv.Service;
using YssInformation.Support.Fun;
using YssInformation.Support.Bi.Market.Service;



namespace YssSecInformation.Sv.Form
{
    ///<summary>
    /// 功能简介：债券基本信息浏览界面，负责债券基本信息的显示和查询功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.30
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：lyh
    /// 修改日期：2011.01.30
    /// 修改简介：加载a区市场信息
    ///    ///   －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：zhuangyuchen
    /// 修改日期： 2011.02.21
    /// 修改简介：  
    /// 1：增加传到后台去的列头和窗体菜单
    /// 2：增加不同类的标识和分类传到后台去
    /// 3：出错提示信息的修改 
    /// 4： 删除以前的旧代码
    /// 5:证券品种表在控件中配置，初始化的时候删除
    /// 6：添加注释
    /// 7：查询方法注释添加简单逻辑描述
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：yhm
    /// 修改日期：2011.02.26
    /// 修改简介：修改为新的代码结构
    ///  －－－－修改记录－－－－
    /// 当前版本：V4.5.0.5
    /// 修改人：wuwenlan
    /// 修改日期：2011.03.2
    /// 修改简介：  对A区的加载
    ///             把A区的市场代码传输到set窗体
    ///             添加了对A区按市场查询
    /// </summary>
    public partial class Frm_SEC_BASE_ZQ_L : FrmBaseListWithDetails
    {
        /// <summary>
        /// dataService
        /// </summary>
        private IMktDataService mktDataService = null;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_SEC_BASE_ZQ_L()
        {
            InitializeComponent();
            addSubButtonsOfGernerate();
            bUseMVCService = true;
            ////实现附件功能。何讯，20151207
            this.AutoLoadEnclosure = true;
        }

        /// <summary>
        /// 获取A区配置信息。重写，设置A区走基类老模式。
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
        /// 为生产按钮新增选项：按社保理事会债券代码转换规则
        /// </summary>
        private void addSubButtonsOfGernerate()
        {
            ClsSubButtonInfo btnSB = new ClsSubButtonInfo();
            btnSB.Text = "社保理事会债券代码转换规则";
            btnSB.ClickEvent += new EventHandler(btnSB_Click);
            this.btnBar.addSubButton(ClsButtonName.BtnGernerate, btnSB);
        }

        /// <summary>
        /// 装载明细窗体功能代码列表
        /// </summary>
        /// <returns>返回明细窗体功能代码列表</returns>
        protected override List<SysFun> LoadDetailFormFuns()
        {
            List<SysFun> sysFuns = new List<SysFun>();
            SysFun newFun = new SysFun();
            ////update 马向峰 20170629 装载明细窗体不再在代码中体现，放在systemConfig.xml中配置
          ////  newFun.C_FUN_CODE = "bondpayinterest";
          // // sysFuns.Add(newFun);

          ////  newFun = new SysFun();
          ////  newFun.C_FUN_CODE = "secmbylx";
          // // sysFuns.Add(newFun);

            //// 证券流通 add by chenwenhai 20140725 STORY #17962 证券基本信息界面关联公司行为信息
           //// newFun = new SysFun();
           //// newFun.C_FUN_CODE = "securitycirculate";
           //// sysFuns.Add(newFun);

            ////对价派息 add by chenwenhai 20140725 STORY #17962 证券基本信息界面关联公司行为信息
           //// newFun = new SysFun();
           //// newFun.C_FUN_CODE = "dividend";
           //// sysFuns.Add(newFun);

            ////证券送配信息 add by chenwenhai 20140725 STORY #17962 证券基本信息界面关联公司行为信息
            ////newFun = new SysFun();
            ////newFun.C_FUN_CODE = "secdist";
            ////sysFuns.Add(newFun);

            ////证券发行信息 add by chenwenhai 20140725 STORY #17962 证券基本信息界面关联公司行为信息
           // //newFun = new SysFun();
           //// newFun.C_FUN_CODE = "securityPublish";
           //// sysFuns.Add(newFun);

            ////证券预发行信息 add by chenwenhai 20140725 STORY #17962 证券基本信息界面关联公司行为信息
           //// newFun = new SysFun();
           //// newFun.C_FUN_CODE = "securityPrePublish";
           //// sysFuns.Add(newFun);

            ////证券回售基本信息 add by caowei STORY #18932 债券增加含权信息需求
           //// newFun = new SysFun();
           //// newFun.C_FUN_CODE = "secSoldBack";
           //// sysFuns.Add(newFun);

            ////证券代码转换 add by guohui STORY32744【南方基金】【v2.5需求】南方基金：社保资产证券代码新规则
           //// newFun = new SysFun();
           //// newFun.C_FUN_CODE = "secTransfer";
            ////sysFuns.Add(newFun);

            //// 计费证券信息 added by Heliang.20160906.STORY #31596 运营费用-支持资产净值扣不计费证券需求
            //// modified by HeLiang 2017-07-20 组件拆分_下半区“计费证券信息”的关联界面（portRelaChargingSec）还没有拆出来，查询会报错，因此暂时先把注释掉
            ////loadPortRelaInfo(sysFuns);

            return sysFuns;
        }

        /// <summary>
        /// 初始化
        /// </summary>
        protected override void initServiceMVC()
        {
            base.initServiceMVC();
            mktDataService = DataServiceFactory.createService<IMktDataService>();

        }


        /// <summary>
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写.
        /// </summary>
        /// <returns> the cond. </returns>
        public override string yssInitQuery()
        {
            //// 所有提供的参数项如下，只需要设置子类需要的项即可
            //// 1 查询条件
            string cond = ""; // " and b.C_DA_CODE like 'ZQ%'";

            //// 3 初始只加载列头，若需要则设为true，反之不需要设置此参数
            IsOnlyHeder = true;

            return cond;
        }

        /////// <summary>
        /////// list界面加载A区数据，子类重写.
        /////// </summary>
        /////// <author>wuwenlan 2011.03.2</author>
        /////// <returns>the result.</returns>
        ////public override string yssGetLeftData()
        ////{
        ////    //// edit by yh 2011.03.09修改数据来源为枚举类型,防止赋值出错
        ////    ClsEnums.DataSrc dataSrc = ClsEnums.DataSrc.SrcCache;    // 数据来源是缓存
        ////    string funCode = "exchange"; // 交易市场
        ////    string headKeys = "C_MKT_NAME~!~C_MKT_CODE"; // 自定义列头

        ////    this.matchSearchStr = new string[2] { "C_MKT_NAME", "C_MKT_CODE" };  // 【搜索】功能匹配的属性

        ////    string result = null;
        ////    //// 设定左侧数据的加载方式
        ////    YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;
        ////    //// 调用由子类提供参数的查询方法
        ////    result = this.yssGetLeftData(dataSrc, funCode, null, headKeys, null);
        ////    return result;
        ////}

        /// <summary>
        /// 加载左侧控件数据
        /// </summary>
        public override void yssLoadLeftData()
        {
            QueryRes res = null;
            ArrayList showColumnList = new ArrayList();
            try
            {
                // edit by yh 2011.03.09修改数据来源为枚举类型,防止赋值出错
                ////leftDataFunCode = AssociaType.exchange.ToString();
                leftDataFunCode = YssInformation.Support.Context.AssociaType.base_exchange.ToString();
                matchSearchStr = new string[2] { "C_MKT_NAME", "C_MKT_CODE" };     // 【搜索】功能匹配的属性
                res = mktDataService.getDataListRes();
                showColumnList.Add("C_MKT_NAME");
                showColumnList.Add("C_MKT_CODE");
                //// 设定左侧数据的加载方式
                YssLeftKTableShowMode = ClsEnums.KTableDataShowMode.TreeMode;
                new TableListLoader().loadTable(tbLeftMain, res, false, false, ClsEnums.KTableDataShowMode.TreeMode, showColumnList);

            }
            catch (Exception ex)
            {
                ////YssMessageBox.ShowDyanInformation("加载左侧交易市场信息报错", ex.Message, MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getAareaLoadErr(ex.Message));
            }
        }


        /// <summary>
        /// 获取list查询条件区的查询条件.
        /// </summary>
        /// <returns>返回查询条件.</returns>
        public string yssGetListCond()
        {
            string cond = "";
            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
            quyStrUtil.addQuyCon("C_DA_CODE", "C_DA_CODE", "ZQ", ClsConstant.SQL_RA_HYPHEN_LIKE, ClsConstant._LIKE_MARCH_LEFT);
            if (this.txtSecMarketCode.Text.Trim().Length != 0)
            {
                ////cond = " and a.C_SEC_MKT_CODE like '" + txtSecMarketCode.Text.Trim() + "%'";
                quyStrUtil.addQuyCon("C_SEC_MKT_CODE", this.txtSecMarketCode.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            //// CL 20121120 STORY #3305 证券基本信息模块下的证券基本信息上市代码查询条件补充调整
            if (this.txtSecMarketCode.Text.Trim().Length != 0)
            {
                quyStrUtil.addQuyCon("C_SEC_CODE", this.txtSecMarketCode.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            if (this.txtSecMarketCode.Text.Trim().Length != 0)
            {
                quyStrUtil.addQuyCon("C_SEC_ISIN_CODE", this.txtSecMarketCode.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            if (this.txtBondName.Text.Trim().Length != 0)
            {
                ////cond += " and a.C_SEC_NAME like '" + txtBondName.Text.Trim() + "%'";
                quyStrUtil.addQuyCon("C_SEC_NAME", this.txtBondName.Text, ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            if (this.cboSecCategory.Value != null)
            {
                ////cond += " and a.C_SEC_VAR_CODE like '" + cboSecCategory.Value + "%'";
                quyStrUtil.addQuyCon("C_SEC_VAR_CODE", this.cboSecCategory.Value, ClsConstant.SQL_RA_HYPHEN_LIKE);
            }

            string search = this.yssBuildLeftCheckRowsStr("exchange");  // tanwenjie 2011.7.28 获取A区选中的行
            ////cond += "and a.C_MKT_CODE in (" + search + ")";
            quyStrUtil.addQuyCon("portCode", "C_MKT_CODE", search, "IN");
            cond = quyStrUtil.getQuyStr("exchange");
            return cond;
        }

        /// <summary>
        /// 封装条件对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            ////STORY #33096 【招商基金】【QDII】紧急-彭博证券信息优化
            string search = this.yssBuildLeftCheckRowsStr("exchange");
            search = search.Replace("'", "");
            if (search != "")
            {
                paraDict.Add("ARRAY_C_MKT_CODE", search);
            }

            if (this.txtSecMarketCode.Text.Trim().Length != 0)
            {
                paraDict.Add("C_SEC_MKT_CODE", "%" + this.txtSecMarketCode.Text.Trim() + "%");
            }

            //// CL 20121120 STORY #3305 证券基本信息模块下的证券基本信息上市代码查询条件补充调整
            if (this.txtSecMarketCode.Text.Trim().Length != 0)
            {
                paraDict.Add("C_SEC_CODE", "%" + this.txtSecMarketCode.Text.Trim() + "%");
            }

            if (this.txtSecMarketCode.Text.Trim().Length != 0)
            {
                paraDict.Add("C_SEC_ISIN_CODE", "%" + this.txtSecMarketCode.Text.Trim() + "%");
            }

            if (this.txtBondName.Text.Trim().Length != 0)
            {
                paraDict.Add("C_SEC_NAME", "%" + this.txtBondName.Text.Trim() + "%");
            }

            if (this.cboSecCategory.Value != null)
            {
                paraDict.Add("C_SEC_VAR_CODE", "%" + this.cboSecCategory.Value + "%");
            }

            ////合并代码： STORY #97948 富国基金-债券基本信息界面新增制作时间和审核时间的查询条件选项
            if (this.dptCreateDate.Checked)
            {
                paraDict.Add("D_CREATE_BEGIN", this.dptCreateDate.getBeginDateStr);
                if (this.dptCreateDate.YssShowSecond)
                {
                    paraDict.Add("D_CREATE_END", this.dptCreateDate.getEndDateStr);
                }
            }

            if (this.dptCheckDate.Checked)
            {
                paraDict.Add("C_CHECK_BEGIN", dptCheckDate.getBeginDateStr);
                if (this.dptCheckDate.YssShowSecond)
                {
                    paraDict.Add("C_CHECK_END", dptCheckDate.getEndDateStr);
                }
            }

            return paraDict;
        }

        /// <summary>
        /// load
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_SEC_BASE_ZQ_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            dataService = (IServiceBus)ServiceFactory.createService(serviceType);
           
            ////edit tdf BUG #115588 [紧急]浮动利率债券没有清算出财汇资讯数据 20150715
            ////if (!isBatchAudit)
           //// {
                
                ////this.addGreButton();
          ////  }   
        }

        /// <summary>
        /// 点击行的时候控制新增按钮是否可用.
        /// wuwenlan 20110302.
        /// 修改人：liuliang (弃用此方法)20120423 不在通过A区控制B区的按钮状态
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        ////private void tbLeftMain_RowClicked_1(object sender, Yss.KTable.Events.RowEventArgs e)
        ////{
        ////    try
        ////    {
        ////        //// 判断当前选中是否为明细节点，为明细节点新增按钮可用，否则不可用
        ////        if (this.tbLeftMain.SelectedRow != null && this.tbLeftMain.SelectedRow.SubRows.Count > 0)
        ////        {
        ////            ////this.btnNew.Enabled = false;
        ////            btnBar.setButtonEnabled(ClsButtonName.BtnNew, false);
        ////        }
        ////        else
        ////        {
        ////            ////this.btnNew.Enabled = true;
        ////            btnBar.setButtonEnabled(ClsButtonName.BtnNew, true);

        ////        }
        ////    }
        ////    catch (Exception ex)
        ////    {
        ////      ////  YssMessageBox.ShowDyanInformation(ex.Message, ex.StackTrace, "点击行事件操作发生错误", MessageBoxIcon.Error, ClsConstant.ButtonType.Detail);
        ////        YssMessageBox.ShowCommonInfo(_formFun.C_FUN_CODE);
        ////    }
        ////}
        ////edit tdf BUG #115588 [紧急]浮动利率债券没有清算出财汇资讯数据 20150715
        /////// <summary>
        /////// 添加生成按钮
        /////// </summary>
        ////private void addGreButton()
        ////{
        ////    ClsButtonInfo btnGenerate = new ClsButtonInfo();
        ////    btnGenerate.Name = ClsButtonName.BtnGernerate;
        ////    btnGenerate.Text = "生成";
        ////    btnGenerate.Tooltip = "生成";
        ////    btnGenerate.Image = FAST.Resource.Resource.btnGernerate_L;
        ////    btnGenerate.ClickEvent += new System.EventHandler(this.btnGenerate_Click);
        ////    this.btnBar.addButton(btnGenerate, 16);
        ////    this.btnBar.setButtonEnabled(btnGenerate.Name, true, false);
        

        /// <summary>
        /// 添加生成按钮点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnGernerate_Click(object sender, EventArgs e)
        {
            //// 开线程初始化债券 modified by xzl
            Thread bondInitThread = new System.Threading.Thread(delegate()
            {
                bondInitProcess(""); 
            });

            bondInitThread.SetApartmentState(ApartmentState.STA);

            bondInitThread.Start();
        }

        /// <summary>
        /// 添加生成按钮点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected void btnSB_Click(object sender, EventArgs e)
        {
            //// 开线程初始化债券 modified by xzl
            Thread bondInitThread = new System.Threading.Thread(delegate()
            {
                bondInitProcess("TRAN_SBLSH"); //// 根据社保理事会规则转换证券内码为披露代码
            });

            bondInitThread.SetApartmentState(ApartmentState.STA);

            bondInitThread.Start();
        }

        /// <summary>
        /// 复写审核按钮 弹出对应是否重新初始化付息及每百元利息确认提示信息
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnAudit_Click(object sender, EventArgs e)
        {
            List<SecBase> secList = new List<SecBase>();
            ISecBaseZqService iSecBaseLcService = (ISecBaseZqService)dataService;
            if (this.tbMain.CheckedRows.Count == 0)
            {
                return;
            }

            foreach (Row row in this.tbMain.CheckedRows)
            {
                SecBase sec = row.Tag as SecBase;
                if (null == sec)
                {
                    break;

                }

                secList.Add(sec);

            }

            base.btnAudit_Click(sender, e);
            if (secList.Count > 0)
            {
                iSecBaseLcService.multipleSecInitFi(secList);
                this.LabStatuInfo.Text = "历史付息信息与债券每日利息数据已重新生成!";
                this.LabStatuInfo.ForeColor = System.Drawing.Color.Red;
                 this.LabStatuInfo.Owner.Refresh();
                ////MessageBox.Show("历史付息信息与债券每日利息数据【已重新生成】!", "提示");
            }
        }

        /// <summary>
        /// 11
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void tbMain_SelectionChanged(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            this.LabStatuInfo.Text = "";
            base.tbMain_SelectionChanged(sender, e);
        }

        /// <summary>
        /// 开启线程，独立处理债券初始化过程，防止界面卡死
        /// added by xzl
        /// </summary>
        /// <param name="zhgz">转换规则</param>
        private void bondInitProcess(string zhgz) 
        {
            List<SecBase> secList = new List<SecBase>();
            ISecBaseZqService iSecbaseZqService = (ISecBaseZqService)dataService;

            try
            {
                if (this.tbMain.CheckedRows.Count == 0)
                {
                    return;
                }

                foreach (Row row in this.tbMain.CheckedRows)
                {
                    SecBase sec = row.Tag as SecBase;
                    if (null == sec)
                    {
                        break;
                    }

                    secList.Add(sec);
                }
                //// 开始处理后禁用生成按钮
                this.btnBar.setButtonDisabled(ClsButtonName.BtnGernerate);
                this.btnBar.setButtonText(ClsButtonName.BtnGernerate, "正在生成...");
                if (zhgz.Length == 0)
                { //// 直接点击“生成”按钮时，只生成债券利息付息和每百元
                    iSecbaseZqService.multipleSecInitFi(secList);
                    this.LabStatuInfo.Text = "选中债券的历史付息信息和债券每日利息数据已重新生成!";
                }
                else
                { //// 点击“生成”的下拉选择按钮时，只生成披露代码
                    ////STORY38131【南方基金】【紧急】社保理事会要求交易席位科目是6位，不足6位前面补0  by guohui 20170207
                    string b = "社保理事会债券代码转换规则";
                    string zhgz1 = "SBLSHGZ";
                    if ("TRAN_SBLSH".Equals(zhgz))
                    {
                        b = "社保理事会债券代码转换规则";
                        zhgz1 = "SBLSHGZ";
                    }

                    string istrue = "1";
                    istrue = iSecbaseZqService.ruleIsOpen(zhgz1, "%SBLSHGZ_SEC%");

                    if ("0".Equals(istrue))
                    {
                        this.LabStatuInfo.Text = "[" + b + "]转换规则没有开启，请先在【证券代码转换】模块开启该转换规则！";
                    }
                    else
                    {
                        iSecbaseZqService.transSecToPlCode(secList, zhgz);
                        this.LabStatuInfo.Text = "选中债券的披露代码已重新生成!";
                    }
                }

                ////MessageBox.Show("选中债券的历史付息信息与债券每日利息数据已重新生成!", "提示");
                ////this.label1.Text = "选中债券的历史付息信息与债券每日利息数据已重新生成!";
                this.LabStatuInfo.ForeColor = System.Drawing.Color.Red;
                this.LabStatuInfo.Width = 600;
                 this.LabStatuInfo.Owner.Refresh();
                //// 恢复生成按钮功能
                this.btnBar.setButtonEnabled(ClsButtonName.BtnGernerate, true, false);
                this.btnBar.setButtonText(ClsButtonName.BtnGernerate, "生成");
            }
            catch (ClsBaseException ex)
            {
                ClsBaseException.DiscardException(ex);
            }
            finally
            {
                //// 恢复生成按钮功能
                this.btnBar.setButtonEnabled(ClsButtonName.BtnGernerate, true);
                this.btnBar.setButtonText(ClsButtonName.BtnGernerate, "生成");
            }
        }

        /// <summary>
        /// 装载组合关联信息
        /// added by HeLiang.2016-09-05.STORY #31596 运营费用-支持资产净值扣不计费证券需求
        /// </summary>
        /// <param name="sysFuns">sysFuns</param>
        private void loadPortRelaInfo(List<SysFun> sysFuns)
        {
            SysFun newFun = new SysFun();
            newFun = ClsContext.sysMenuFunHash["sv_chargingSec"].Clone() as SysFun;
            newFun.C_FUN_CODE = "portRelaChargingSec";
            //// modified by HeLiang 2017-06-29 资讯组件拆分代码调整，应使用【计费证券信息】的FunCode
            ////newFun.N_CHECK = ClsContext.sysMenuFunHash["chargingSec"].N_CHECK;
            ////newFun.N_USER = ClsContext.sysMenuFunHash["chargingSec"].N_USER;
            newFun.YssAssocia = ClsClzCfgMgr.getAssociaParam(newFun.C_FUN_CODE);
            sysFuns.Add(newFun);
            if (!ClsContext.sysMenuFunHash.ContainsKey(newFun.C_FUN_CODE))
            {
                ClsContext.sysMenuFunHash.Add(newFun.C_FUN_CODE, newFun);
            }

            if (!ClsContext.sysFunHash.ContainsKey(newFun.C_FUN_CODE))
            {
                ClsContext.sysFunHash.Add(newFun.C_FUN_CODE, newFun);
            }

            if (!ClsContext.HtUserOperRight.ContainsKey(newFun.C_FUN_CODE))
            {
                ClsContext.HtUserOperRight.Add(newFun.C_FUN_CODE, ClsContext.HtUserOperRight["sv_chargingSec"]);
            }

            if (!ClsContext.HtFunRight.ContainsKey(newFun.C_FUN_CODE))
            {
                ClsContext.HtFunRight.Add(newFun.C_FUN_CODE, ClsContext.HtFunRight["sv_chargingSec"]);
            }
        }

    }
}


