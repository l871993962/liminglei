using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.Context.Events;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Interface;
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



////using YssBaseCls.Fun;
////using YssData.Pojo;
////using FAST.Core.BaseControl.Fun;
using YssInformation.Support.Fun;
using YssSecInformation.Support.Interface;
using YssSecInformation.Support.Mp.SecMktMap.Service;
////using YssData.Service.Mp;
////using YssBaseCls.Interface;



////using YssPara.Pojo.Bi;
////using YssPojos.Data.Mp;
////using YssPojos.Para.Bi;

////namespace YssData.Form.Mp
namespace YssSecInformation.Mp.SecMktMap.Form
{
    /// <summary>
    /// 功能简介：汇率行情浏览界面，负责汇率行情的显示和查询功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2010.12.21
    ///
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：wuwenlan
    /// 修改日期：20101223
    /// 修改简介：实现具体功能
    /// 
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：lyh
    /// 修改日期：2011.01.30
    /// 修改简介：加载a区市场信息
    /// 
    
    ///   －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：zhuangyuchen
    /// 修改日期： 2011.02.20
    /// 修改简介：  
    /// 1：增加传到后台去的列头和窗体菜单
    /// 2：增加不同类的标识和分类传到后台去
    /// 3：出错提示信息的修改 
    /// 4： 删除以前的旧代码
    /// 5：修改POJO类为公共类
    /// 6：修改了由于POJO类更改后的属性
    /// 7:增加点击左侧数据区的记录时候，获取其中的数据库的分类标志，传到后台
    /// 8:证券品种表在控件中配置，初始化的时候删除
    /// 9：根据需求删除控件
    ///10：增加左侧数据区的界面操作代码
    ///11:增加列头和菜单ID
    ///
    ///  ///   －－－－修改记录－－－－
    /// 当前版本：V4.5.0.5
    /// 修改人：zhuangyuchen
    /// 修改日期： 2011.02.20
    /// 修改简介：  
    /// 1：根据重新修改的基类重写时间和查询方法
    ///
    /// 
    ///  ///  ///   /// ///   －－－－修改记录－－－－
    /// 当前版本：V4.5.0.
    /// 修改人：zhuangyuchen
    /// 修改日期： 2011.03-2
    /// 修改简介：  
    /// 修改基类更改后的代码
    ///加载A区数据，根据A区选中的值，显示数据
    /// 
    /// </summary>
    public partial class Frm_SEC_MKT_HL_L : FrmBaseList
    {
        /// <summary>
        /// 窗体数据服务对象
        /// </summary>
        private IHlMktService marketValueService = null;

        /// <summary>
        /// 增加一个标志属性传到set窗体，根据这个标志，确定set窗体是否可用
        /// </summary>
        private string c_market_code = "";

        
        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_SEC_MKT_HL_L()
        {
            this.bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 增加一个标志属性传到set窗体，根据这个标志，确定set窗体是否可用
        /// </summary>
        public string C_MARKET_CODE
        {
            get { return c_market_code; }
            set { c_market_code = value; }
        }


        /// <summary>
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写
        /// </summary>
        /// <returns>初始化查询条件</returns>
        public override string yssInitQuery()
        {
            // 所有提供的参数项如下，只需要设置子类需要的项即可
            // 1 查询条件
            string cond = ""; // " and a.C_MKT_CLS = 'ER'";  // 查询分类为汇率的数据
   
            // 3 初始只加载列头，若需要则设为true，反之不需要设置此参数
            this.IsOnlyHeder = true;

            return cond;
        }

        /// <summary>
        /// 获取list查询条件区的查询条件
        /// </summary>
        /// <returns>list查询条件</returns>
        public string yssGetListCond()
        {
            string cond = "";
            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
            quyStrUtil.addQuyCon("C_MKT_CLS", "ER", "=");
            if (this.tbLeftMain.SelectedRow != null)
            {
                // 如果选中的是跟节点，就传市场类型 // zhuangyuchen  2011-3-3
                if (((I_BaseMkt)tbLeftMain.SelectedRow.Tag).MKT_CODE_P.Equals("[root]"))
                {
                    ////cond += "and b.C_DV_MKT_TYPE = '" + ((I_BaseMkt)tbLeftMain.SelectedRow.Tag).C_DV_MKT_TYPE + "'";
                    quyStrUtil.addQuyCon("C_DV_MKT_TYPE", ((I_BaseMkt)tbLeftMain.SelectedRow.Tag).C_DV_MKT_TYPE, "=");

                }
                else 
                {
                    ////cond += "and b.C_MKT_CODE = '" + ((I_BaseMkt)tbLeftMain.SelectedRow.Tag).C_MKT_CODE + "'";  // 如果是子节点，就传组合代码
                    quyStrUtil.addQuyCon("C_MKT_CODE", ((I_BaseMkt)tbLeftMain.SelectedRow.Tag).C_MKT_CODE, "=");
                }

            }

            if (this.cboCury.Value != null && this.cboCury.Value.Trim().Length > 0)
            {
                ////cond += " and a.C_SEC_CODE = '" + this.cboCury.Value + "'";  // 货币对
                quyStrUtil.addQuyCon("C_SEC_CODE", this.cboCury.Value, "=");
            }

            ////cond += " and a.D_MKT between " + "to_date( '" + this.dtpMktDate.getBeginDate.ToString("yyyy-MM-dd").Trim() + "','yyyy-MM-dd')" + " and " + "to_date('" + this.dtpMktDate.getEndDate.Date.ToString("yyyy-MM-dd").Trim() + "','yyyy-MM-dd')";    // 行情日期
            quyStrUtil.addQuyCon("dExr", "D_MKT", dtpMktDate.getBeginDate.ToString("yyyy-MM-dd").Trim() + "," + dtpMktDate.getEndDate.Date.ToString("yyyy-MM-dd").Trim(), "BETWEEN");
            cond = quyStrUtil.getQuyStr(this._formFun.C_FUN_CODE);
            return cond;
        }


        /// <summary>
        /// list界面加载A区数据，子类重写
        /// </summary>
        /// <author>zhuagnyuchen</author>
        /// 调用公共方法加载左侧数据区的市场信息
        /// 需求调整，不需要加载A区
        public override void yssLoadLeftData()
        {
        }

        /// <summary>
        /// 点击行的时候控制新增按钮是否可用
        /// zhuangyuchen  2011-3-2
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        protected override void tbLeftMain_RowClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            base.tbLeftMain_RowClicked(sender, e);

            try
            {
                // 判断当前选中是否为明细节点，为明细节点新增按钮可用，否则不可用
                if (this.tbLeftMain.SelectedRow != null && this.tbLeftMain.SelectedRow.SubRows.Count > 0)
                {
                    ////this.btnNew.Enabled = false;
                    btnBar.setButtonEnabled(ClsButtonName.BtnNew, false);
                }
                else
                {
                    ////this.btnNew.Enabled = true;
                    btnBar.setButtonEnabled(ClsButtonName.BtnNew, true);
                }
            }
            catch (Exception ex)
            {
                ////YssMessageBox.ShowDyanInformation(ex.Message, ex.StackTrace, "点击行事件操作发生错误", MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-110028", _formFun, status));
                ClsBaseException.DiscardException(ex);
            }
        }

        /// <summary>
        /// 窗体load事件中控制新增按钮不可用
        /// zhuangyuchen 2011-3-2
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        private void Frm_SEC_MKT_HL_L_Load(object sender, EventArgs e)
        {
            try
            {
                getServiceInstance();

                this.cboPort.Value = null;

                // 判断当前选中是否为明细节点，为明细节点新增按钮可用，否则不可用
                if (this.tbLeftMain.SelectedRow != null && this.tbLeftMain.SelectedRow.SubRows.Count > 0)
                {
                    ////this.btnNew.Enabled = false;
                    btnBar.setButtonEnabled(ClsButtonName.BtnNew, false);
                }
            }
            catch (Exception ex)
            {
               //// YssMessageBox.ShowDyanInformation(ex.Message, ex.StackTrace, "加载窗体操作开始之前发生错误", MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500036", _formFun, status));
                ClsBaseException.DiscardException(ex);
            }

        }

       
        /// <summary>
        /// 新增之前的事件，得到左边数据区的市场代码，传到set窗体，新增的时候，在该市场代码下增加
        /// </summary>
        /// <param name="sender">引发事件的对象</param>
        /// <param name="e">事件类</param>
        private void Frm_SEC_MKT_HL_L_YssOnBeforeNewClick(object sender, YssBeforeOperEventArgs e)
        {
            ClsInterface inter = new ClsInterface();
            try
            {
                // 如果不选择左边的数据区域，直接点击查询，就跳出来
                if (this.tbLeftMain.SelectedRow != null)
                {
                    foreach (Yss.KTable.Models.Row c in this.tbLeftMain.Rows)
                    {
                        this.C_MARKET_CODE = ((I_BaseMkt)inter.getSelectRow(c)).C_MKT_CODE;   // 得到选中的组合对象
                    }
                }
            }
            catch (Exception ex)
            {
                ////YssMessageBox.ShowDyanInformation(ex.Message, ex.StackTrace, "新增操作开始之前发生错误", MessageBoxIcon.Error, FAST.Core.Context.ClsConstant.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500061", _formFun, status));
                ClsBaseException.DiscardException(ex);
            }
        }

        /////// <summary>
        /////// 重写基类查询方法
        /////// </summary>
        /////// <param name="paraDict">paraDict</param>
        /////// <param name="page">page</param>
        /////// <returns>结果对象</returns>
        ////protected override QueryRes getQueryResultMVC(Dictionary<string, string> paraDict, PageInation page)
        ////{
        ////    QueryRes queryRes = new QueryRes();
        ////    this.getServiceInstance();
        ////    queryRes = this.marketValueService.selRatePriceValue(paraDict, page, _formFun.C_FUN_CODE);
        ////    return queryRes;
        ////}

        /// <summary>
        /// 创建窗体服务
        /// </summary>
        private void getServiceInstance()
        {
            if (null == this.dataService)
            {
                Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
                this.marketValueService = ServiceFactory.createService(serviceType) as IHlMktService;
                this.dataService = this.marketValueService;
            }
        }

        /// <summary>
        /// 封装前台条件到对象
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>paraDict</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            paraDict.Add("C_MKT_CLS", "ER");
            if (this.tbLeftMain.SelectedRow != null)
            {
                // 如果选中的是跟节点，就传市场类型 // zhuangyuchen  2011-3-3
                if (((I_BaseMkt)tbLeftMain.SelectedRow.Tag).MKT_CODE_P.Equals("[root]"))
                {
                    paraDict.Add("C_DV_MKT_TYPE", ((I_BaseMkt)tbLeftMain.SelectedRow.Tag).C_DV_MKT_TYPE);
                }
                else
                {
                    paraDict.Add("C_MKT_CODE", ((I_BaseMkt)tbLeftMain.SelectedRow.Tag).C_MKT_CODE);
                }

            }

            if (this.cboCury.Value != null && this.cboCury.Value.Trim().Length > 0)
            {
                paraDict.Add("C_SEC_CODE", this.cboCury.Value);
            }

            paraDict.Add("D_BEGIN", dtpMktDate.getBeginDate.ToString("yyyy-MM-dd").Trim());
            paraDict.Add("D_END", dtpMktDate.getEndDate.Date.ToString("yyyy-MM-dd").Trim());

            ////STORY #35336 （嘉实QD需求）原4.0需求：26150 需求北京-[嘉实基金]QDII资产管理系统[高]2015928001（QDII汇率导入需求) 添加组合关联
            if (this.cboPort.Value != null && this.cboPort.Value.Trim().Length > 0)
            {
                //// 非根节点才传入后台做条件查询
                if (!"[root]".Equals(this.cboPort.SelectedItem.ParentNodeID))
                {
                    paraDict.Add("C_PORT_CODE", this.cboPort.Value);
                }
            }

            return paraDict;

        }

        /// <summary>
        /// 设置参数
        /// </summary>
        /// <returns>参数集合</returns>
        public override Dictionary<string, string> setExtraCondition()
        {
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("C_MKT_CLS", "ER");
            return paraDict;
        }
    }
}


