using System;
using System.Collections.Generic;
using FAST.Common.Service.Dict.Pojo;
using FAST.Core.Communication.Service;
using FAST.Core.Context;
using FAST.Core.Context.Events;
using FAST.Core.CRUD.Form;
using FAST.Core.Exceptions;
using FAST.Core.Util;
////using YssBaseCls.Pojo;
using YssInformation.Support.Bi.Market.Service;
using YssInformation.Support.Bi.Market.Pojo;
using YssInformation.Support.Pojo;


namespace YssInformation.Bi.Market.Form
{
    ///<summary>
    /// FrmExchange 的摘要说明。
    /// 作用：交易所信息设置，负责交易所信息的增删改查等功能 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.02
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：yh
    /// 修改日期： 2010.12.02
    /// 修改简介： 实现方法
    ///－－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：zhuangyuchen
    /// 修改日期： 2010.12.02
    /// 修改简介：
    /// 1:增加回收站开启关闭机制
    /// 2:删除添加修改等操作的操作成功信息
    /// 3：添加注释
    /// 4：增加所属机构的下拉框信息绑定
    /// 5：增加对窗体控件的控制
    /// 6：修改出错的提示信息
    /// 7:修改了由于POJO类更改后的属性
    /// 8：增加传到后台去的列头和窗体菜单
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：lyh
    /// 修改日期： 2011.02.26
    /// 修改简介： 调整代码新结构
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.5
    /// 修改人：wuwenlan 
    /// 修改日期： 2011-3-14
    /// 修改简介：
    ///         返回对象错误直接抛出异常
    /// </summary>
    public partial class Frm_MKT_S : FrmBaseSet
    {
        /// <summary>
        /// 本模块所需的功能按钮的声明
        /// </summary>
        private List<string> btnLis = null;

        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private IMktService mktService = null;

        /// <summary>
        /// 定义属性，得到前台传过来的市场类型
        /// </summary>
        private Vocabulary clsVoc = null;

        /// <summary>
        /// 构造方法.
        /// </summary>
        public Frm_MKT_S()
        {
            this.bUseMVCService = true;
            InitializeComponent();

            string cond = ""; // 查询条件,此时为词汇类型代码
         }

        /// <summary>
        /// 定义属性，得到前台传过来的市场类型
        /// </summary>
        public Vocabulary Voc
        {
            get { return clsVoc; }
            set { clsVoc = value; }
        }

        /// <summary>
        /// 窗体显示之前的load事件.
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        private void FrmExchange_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.mktService = ServiceFactory.createService(serviceType) as IMktService;
            this.dataService = this.mktService;

            //// 调用设定结转日最大值的方法
            ////setControlSettleDay();
            //// 将list界面左侧数据区的投资组合数据赋值给set界面 zhuangyuchen
            ////if (this.Voc != null)
            ////{
            ////    //// 如果是二级市场，在修改前将“新增，删除，审核，反审核按钮禁用
            ////    if (this.Voc.C_DV_CODE.Equals("FTM"))
            ////    {
            ////        btnBar.setButtonEnabled(ClsButtonName.BtnNew);
            ////        ////btnBar.setButtonEnabled(ClsButtonName.BtnDelete,false);
            ////        btnBar.setButtonEnabled(ClsButtonName.BtnCopy);
            ////        ////this.btnNew.Enabled = false; // 新增按钮变灰
            ////        ////this.btnDelete.Enabled = false; // 删除按钮变灰
            ////        ////this.btnCopy.Enabled = false; // 复制按钮变灰


            ////        ////this.cboOrgCode.YssReadOnly = true; // 当用户点击修改时候，如果二级市场，所属机构不可用
            ////        ////this.cboOrgCode.KeepDesignValue = true;
            ////        this.txtExchangeName.YssReadOnly = true; // 市场名称不可用
            ////        this.txtExchangeName.KeepDesignValue = true;
            ////    }
            ////}

            // 声明本模块的所需的功能按钮，并将所声明的按钮放入到btnBar中
            ////this.getBtnLis();
            ////btnBar.FunRightList = btnLis;

        }

        /// <summary>
        /// 初始化界面控件.
        /// </summary>
        public override void yssInitCtlAttr()
        {
            //// 不管是一级还是二级市场，所属交易所均不可用
            ////Voc = this.frmBaseViewList.getSelectedRowTagMVC(Voc) as Vocabulary;
            //////// 将list界面左侧数据区的投资组合数据赋值给set界面 zhuangyuchen

            ////if (this.Voc != null)
            ////{
            ////    ////如果是一级市场否则是二级市场
            ////    if (this.Voc.C_DV_CODE.Equals("OTC"))
            ////    {
            ////        this.cboExchange.Text = " "; // 所属交易所的值默认为空                 
            ////    }
            ////    else
            ////    {
            ////        ////this.cboOrgCode.YssReadOnly = true; // 当用户点击修改时候，如果二级市场，所属机构不可用 
            ////        ////this.cboOrgCode.KeepDesignValue = true;
            ////        ////this.cboOrgCode.Text = " ";
            ////        this.txtExchangeName.YssReadOnly = true; // 市场名称不可用
            ////        this.txtExchangeName.KeepDesignValue = true;

            ////        ////更改相应的必填项提示为不提示
            ////        this.cboExchange.YssIsMust = false;
            ////        this.cboHolidays.YssIsMust = false;
            ////        ////this.cboOrgCode.YssIsMust = false;
            ////        ////this.txtExchangeCode.YssIsMust = false;
            ////        this.txtExchangeName.YssIsMust = false;

            ////    }
            ////}

        }

        /// <summary>
        /// 重写数据的保存事件
        /// edit by guohui 重写收支结转业务产生的数据的保存方法 STORY32540【深国投】收支结转业务自动结转后要带出银转证凭证的需求  
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnSave_Click(object sender, System.EventArgs e)
        {
            this.stBarBottom.StatuInfo = null;
            string a = "0";

            string mkt = this.txtExchangeCode.Value;
            IMktService iMktService = ServiceFactory.createService<IMktService>();
            a = iMktService.compareQsjg(mkt);

            if ("1".Equals(a))
            {
                this.stBarBottom.StatuInfo = "交易市场代码与清算机构代码重复，请重新输入交易市场代码！";
                return;
            }

            base.btnSave_Click(sender, e);
        }

        /// <summary>
        /// 子类覆写初始化方法
        /// </summary>
        public override void initControlStat()
        {
            base.initControlStat();
            if (status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssEdit || status == ClsEnums.StatusSetting.YssCopy)
            {
                this.cboArea.YssReadOnly = false;
                this.txtExchangeCode.YssReadOnly = false;
                this.txtExchangeName.YssReadOnly = false;
                if (status == ClsEnums.StatusSetting.YssAdd)
                {
                    this.txtExchangeCode.Text = "";
                    this.txtExchangeName.Text = "";
                    this.cboArea.Value = null;
                }
            }
            else
            {
                this.cboArea.YssReadOnly = true;
                this.txtExchangeCode.YssReadOnly = true;
                this.txtExchangeName.YssReadOnly = true;
            }
            
            ////this.txtExchangeCode.YssReadOnly = false;
            ////this.txtExchangeName.YssReadOnly = false;
        }

        /// <summary>
        /// liuping   2011-03-17   检测结转天数不为空.
        /// </summary>
        private void checkSettleDays()
        {
            //// bug 82824 by Jinghehe 2013-11-04
            if (this.iniSettleDays.Value.ToString() == null || this.iniSettleDays.Value < 0)
            {
                ////throw new ClsBaseException("请输入结转天数");
                throw new Exception(ClsRetInfoDealer.getExtWarns("006", _formFun, status));
            }
        }

        /// <summary>
        /// 保存之前判断数据输入的正确性. 
        /// liuping  2011-03-17.
        /// </summary>
        /// <param name="sender">sender.</param>
        /// <param name="e">e.</param>
        private void Frm_MKT_S_YssOnBeforeSaveClick(object sender, YssBeforeOperEventArgs e)
        {
            e.IsCancel = true; // 屏蔽保存数据的方法
            checkSettleDays(); // liupigng   2011-03-17   检测结转天数不为空
            e.IsCancel = false; // 不屏蔽保存数据的方法
            MktExtend exchange = (MktExtend)yssGetBaseSelTypeItemMVC();
            if (status == ClsEnums.StatusSetting.YssEdit && this.cboExchange.Value == exchange.C_MKT_CODE)
            {
                return;
            }

            IMktService mktService = ServiceFactory.createService<IMktService>();
            ////add by zzk 20150202 此方法为保存前触发，还未判断输入项的合法性，此处需对市场代码进行检测，则需先判断市场代码输入项的合法性 BUG #107328 交易市场设置，新增直接保存提示信息不正确
            if (this.cboExchange.Text.Trim() == "")
            {
                return;
            }
            else
            {
                string mktCode = mktService.getCheckStatus(this.cboExchange.Value.ToString());

                if (mktCode == "unAudit")
                {
                    string mess = ClsRetInfoDealer.getExtWarns("007", _formFun, status);
                    throw TransferErrorMessageUtil.getTransferException(mess);

                }
                else if (mktCode == "exist")
                {
                    string mess = ClsRetInfoDealer.getExtWarns("008", _formFun, status);
                    throw TransferErrorMessageUtil.getTransferException(mess);
                }
            }

        }

        /////// <summary>
        ///////    BUG #1474 交易市场设置BUG.
        ///////    向所属机构中添加 CFETC 中国外汇交易中心. 
        ///////    liuping   2011-03-17     刷新后加载.
        /////// </summary>
        /////// <param name="sender">sender.</param>
        /////// <param name="e">e.</param>
        ////private void cboOrgCode_YssOnAfterLoadData(object sender, YssBeforeOperEventArgs e)
        ////{
        ////    if (this.cboOrgCode != null)
        ////    {
        ////        string strData = "CFETC\t中国外汇交易中心\t\t\t\t\t\t0\t\t\t\t\t\t\t\t\t\t\t\t1\t\t\t\t\t\t\t";
        ////        Cls_ORG clsTypeItem = new Cls_ORG();
        ////        clsTypeItem.listItemParse(strData);

        ////        bool flag = false;
        ////        var s = this.cboOrgCode.Items.GetEnumerator();
        ////        while (s.MoveNext())
        ////        {
        ////            var st = s.Current;
        ////            var ss = (Cls_ORG)st.DataEntity;
        ////            if (ss.C_ORG_CODE == "CFETC")
        ////            {
        ////                flag = false;
        ////                break;
        ////            }
        ////            else
        ////            {
        ////                flag = true;
        ////                continue;
        ////            }
        ////        }

        ////        if (this.cboOrgCode.Items.Count == 0)
        ////        {
        ////            flag = true;
        ////        }

        ////        if (flag)
        ////        {
        ////            e.Pojos = new List<IBasePojo>();
        ////            e.Pojos.Add(clsTypeItem);
        ////        }
        ////    }
        ////}


        /// <summary>
        /// 封装窗体数据到对象
        /// </summary>
        /// <returns>Pojo</returns>
        public override FAST.Common.Service.Pojo.Base.AuditableParamPojo faceInfoToObjMVC()
        {
            Mkt exchange = null;
            try
            {
                exchange = new Mkt();

                exchange.C_MKT_NO = this.txtExchangeCode.Text; // 赋值给市场代码
                exchange.C_MKT_NAME = this.txtExchangeName.Text; // 赋值给市场名称
                exchange.C_DE_CODE = this.cboExchange.Value; // 赋值给所属交易所
                exchange.C_MKT_NAME_ST = this.textMktdesc.Text;
                exchange.C_MKT_NAME_EN = this.textenglishName.Text;
                exchange.C_SWIFT_CODE = this.textSWIFTCODE.Text;
                exchange.C_HDAY_CODE = null == cboHolidays.Value ? " " : cboHolidays.Value;
                exchange.C_AREA_CODE = this.cboArea.Value; // 赋值给所属地区
                exchange.N_SETT_DAYS = this.iniSettleDays.Value.ToString(); // 赋值给结转天数     liuping 20110317  BUG #1474 交易市场设置BUG
                exchange.C_FIX_CODE = this.fixCodeTxt.Text;
                if (this.cboExchange.Value != null && !this.cboExchange.Value.Equals("null"))
                {
                    exchange.C_DV_MKT_TYPE = ((MarketVoc)this.cboExchange.SelectedItem.DataEntity).C_DV_MKT_TYPE;
                    exchange.C_MKT_CODE = ((MarketVoc)this.cboExchange.SelectedItem.DataEntity).C_MKTVOC_CODE;

                }
            }
            catch (Exception ye)
            {
                throw new ClsBaseException(ye.Message);
            }

            return exchange;
        }

        /// <summary>
        /// 展示对象属性到窗体
        /// </summary>
        /// <param name="pojo">Pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                MktExtend exchange = (MktExtend)yssGetBaseSelTypeItemMVC();
                if (exchange == null)
                {
                    return;
                }

                this.txtExchangeCode.Text = exchange.C_MKT_NO; // 赋值给市场代码
                this.txtExchangeName.Text = exchange.C_MKT_NAME; // 赋值给市场名称
                this.cboExchange.Value = exchange.C_MKT_CODE; // 赋值给所属交易所

                if (!exchange.C_MKT_NAME_EN.Equals("null"))
                {
                    this.textenglishName.Text = exchange.C_MKT_NAME_EN;
                }

                if (!exchange.C_MKT_NAME_ST.Equals("null"))
                {
                    this.textMktdesc.Text = exchange.C_MKT_NAME_ST;
                }

                if (!exchange.C_SWIFT_CODE.Equals("null"))
                {
                    this.textSWIFTCODE.Text = exchange.C_SWIFT_CODE;
                }

                
                this.cboHolidays.Value = exchange.C_HDAY_CODE; // 赋值给节假日群
                this.cboArea.Value = exchange.C_AREA_CODE; // 赋值给所属地区
                this.iniSettleDays.Text = exchange.N_SETT_DAYS == "" ? "0" : exchange.N_SETT_DAYS; // 赋值给结转天数
                this.fixCodeTxt.Text = exchange.C_FIX_CODE == "" ? " " : exchange.C_FIX_CODE;  // FIX CODE
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }
        }

        /////// <summary>
        /////// 加载地区数据
        /////// </summary>
        /////// <param name="sender">sender</param>
        /////// <param name="e">e</param>
        ////private void cboArea_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        ////{
        ////    ////IAreaService areaService = ServiceFactory.createService<IAreaService>();
        ////    ////QueryRes res = areaService.getAllAreasByType();
        ////    ////List<BasePojo> areaList = res.DataList;
        ////    ////foreach (Area area in areaList)
        ////    ////{
        ////    ////    Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(area);
        ////    ////    entity.DataEntity = area;
        ////    ////    entity.DisplayName = area.C_AREA_CODE;
        ////    ////    entity.DisplayValue = area.C_AREA_NAME;
        ////    ////    e.Collection.Add(entity);
        ////    ////}

        ////    ////////指定控件不要自动去加载数据
        ////    ////e.IsCancel = true;
        ////}

        /// <summary>
        /// 加载市场信息
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboExchange_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            IMktService mktService = ServiceFactory.createService<IMktService>();
            List<MarketVoc> mktList = mktService.getAllMkt();
            foreach (MarketVoc mktvoc in mktList)
            {
                Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(mktvoc);
                e.Collection.Add(entity);
            }

            ////指定控件不要自动去加载数据
            e.IsCancel = true;
        }

        /////// <summary>
        /////// 定义本模块需要使用到的功能按钮
        /////// </summary>
        ////private void getBtnLis()
        ////{
        ////    btnLis = new List<string>();
        ////    btnLis.Add(ClsButtonName.BtnEdit);
        ////    btnLis.Add(ClsButtonName.BtnAudit);
        ////    btnLis.Add(ClsButtonName.BtnUnAudit);
        ////    btnLis.Add(ClsButtonName.BtnSave);
        ////    btnLis.Add(ClsButtonName.BtnRecall);
        ////    btnLis.Add(ClsButtonName.BtnPrevious);
        ////    btnLis.Add(ClsButtonName.BtnNext);
        ////    btnLis.Add(ClsButtonName.BtnClose);
        ////    btnLis.Add(ClsButtonName.BtnHelp);
        ////}
    }
}


