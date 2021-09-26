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

using FAST.Common.Service.Pojo.Base;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;

using System.Collections;



using System.Text.RegularExpressions;
using YssProductInfo.Support.Aa.PortCls.Service;


using FAST.Common.Service.Services;
using FAST.Core.BaseControl.Pojo;
using FAST.Core.BaseControl.Fun;
using YssProductInfo.Support.Aa.PortCls.Pojo;
using FAST.Core.Context.Events;
using YssInformation.Support.Fun;
using FAST.Core.CRUD.Interface;
using FAST.Common.Service.DataService;
using FAST.Core.Communication.DataService;
using Yss.KMessage;




namespace YssProductInfo.Aa.PortCls.Form
{
    /// <summary>
    /// 功能简介：分级产品参数浏览界面，负责分级产品参数数据的显示和查询功能的实现 
    /// 创建版本：V4.5.0.1
    /// 创建人： yh
    /// 创建日期： 2011.01.07
    ///
    /// －－－－修改记录－－－－
    /// 当前版本：V4.5.0.2
    /// 修改人：庄宇臣  
    /// 修改日期：2011-1-14
    /// 修改简介：实现功能方法
    /// 
    /// 
    ///   －－－－修改记录－－－－
    /// 当前版本：V4.5.0.4
    /// 修改人：zhuangyuchen
    /// 修改日期： 2011.02.22
    /// 修改简介：  
    /// 1：增加传到后台去的列头和窗体菜单
    /// 2：出错提示信息的修改 
    /// 3： 删除以前的旧代码
    /// 4：修改POJO类为公共类
    /// 5：修改了由于POJO类更改后的属性
    /// 6:添加加载左侧数据区域的方法
    /// 7:下拉框在数据在控件中配置，初始化的时候删除
    /// 8：根据需求调整控件，投资组合控件改成弹出式窗体,下拉框采用yssselCombox
    /// 9：调整控件后去掉一些查询条件代码
    /// 
    ///  ///   －－－－修改记录－－－－
    /// 当前版本：V4.5.0.5
    /// 修改人：zhuangyuchen
    /// 修改日期： 2011.02.26
    /// 修改简介：  
    /// 1：基类改变，修改代码
    /// 2：去掉原来的方法，重写基类方法.
    /// </summary>
    public partial class Frm_PORT_CLS_L : FrmBaseList, IFormDetailData
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
        /// 构造方法
        /// </summary>
        public Frm_PORT_CLS_L()
        {
            InitializeComponent();
            this.bUseMVCService = true;
            AddCopyCreateButton();
        }

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

        /// <summary>
        /// 增加复制创建按钮。
        /// </summary>
        private void AddCopyCreateButton()
        {
            ClsSubButtonInfo btnCopyCreate = new ClsSubButtonInfo();
            btnCopyCreate.Text = "复制创建";
            btnCopyCreate.ClickEvent += new EventHandler(this.btnCopyCreate_Click);
            this.btnBar.addSubButton(ClsButtonName.BtnCopy, btnCopyCreate);

        }

        /// <summary>
        /// 增加复制创建按钮事件。
        /// </summary>
        /// <param name="sender">ButtonItem按钮</param>
        /// <param name="e">事件参数</param>
        private void btnCopyCreate_Click(object sender, EventArgs e)
        {
            if (this.tbMain.SelectedRow != null && this.tbMain.SelectedRow.Tag != null)
            {
                ////modified by yll 20161130 STORY #31359 复制创建功能界面的组合数据不能审核
                if (this.frmBaseViewSet != null)
                {
                    this.frmBaseViewSet.Close();
                    this.frmBaseViewSet.Dispose();
                }

                YssProductInfo.Support.Aa.PortCls.Pojo.PortCls portcls = this.tbMain.SelectedRow.Tag as YssProductInfo.Support.Aa.PortCls.Pojo.PortCls;
                this.YssStatus = ClsEnums.StatusSetting.YssAdd;
                Frm_PORT_CLS_S frm_PORT_CLS_S = new Frm_PORT_CLS_S();
                this.frmBaseViewSet = frm_PORT_CLS_S;
                frm_PORT_CLS_S.YssFormMenu = this._formFun;
                frm_PORT_CLS_S.initControlStat();
                frm_PORT_CLS_S.init(true, portcls);
                frm_PORT_CLS_S.FormBaseListView = this;
                frm_PORT_CLS_S.Show(this);
            }
        }

        /// <summary>
        /// 根据需要初始化发送到后台的参数项，若无需要可不重写.
        /// </summary>
        /// <returns>获取list查询条件区的查询条件.</returns>
        public override string yssInitQuery()
        {
            // 所有提供的参数项如下，只需要设置子类需要的项即可
            // 1 查询条件
            string cond = "";

            // 3 初始只加载列头，若需要则设为true，反之不需要设置此参数
            this.IsOnlyHeder = true;

            return cond;
        }

        /// <summary>
        /// 获取list查询条件区的查询条件.
        /// </summary>
        /// <returns>返回查询条件</returns>
        public string yssGetListCond()
        {
            ////string cond = "";
            ////// 判断A区是否有选中行
            ////// 如果选中了左边的节点，得到节点的值
            ////////if (this.tbLeftMain.SelectedRow != null)
            ////////{
            ////////    Vocabulary voc = (Vocabulary)clsInterface.selectedRow(this.tbLeftMain);
            ////////    if (null != voc)
            ////////    {
            ////////        if (null != voc)
            ////////        {
            ////////            // 销售类型
            ////////            cond += " and  a.C_DV_PORT_CLS_TYPE = '" + voc.C_DV_CODE + "'";
            ////////        }
            ////////    }
            ////////}
            ////string search = this.yssBuildLeftCheckRowsStr("pubvocabulary");  // tanwenjie 2011.7.28 获取A区选中的行
            ////cond += " and a.C_DV_PORT_CLS_TYPE in (" + search + ")";

            ////if (this.cboPort.Value != null && this.cboPort.Value.ToString().Length > 0)
            ////{
            ////    // 投资组合的代码
            ////    cond += " and a.C_PORT_CODE  = '" + this.cboPort.Value + "'";
            ////}

            ////if (this.txtClassCode.Text.Trim().Length != 0 && this.txtClassCode.Text.Trim() != "")
            ////{
            ////    // 分级组合
            ////    cond += " and a.C_PORT_CLS_CODE ='" + this.txtClassCode.Text + "'";
            ////}

            ////cond += " and a.D_TO_LIST between " + "to_date( '" + this.dtpBeginDate.getBeginDate.ToString("yyyy-MM-dd").Trim() + "','yyyy-MM-dd')" + " and " + "to_date('" + this.dtpBeginDate.getEndDate.Date.ToString("yyyy-MM-dd").Trim() + "','yyyy-MM-dd')"; // 开始日期

            ////return cond;


            string cond = "";

            ////string search = this.yssBuildLeftCheckRowsStr("pubvocabulary"); // tanwenjie 2011.7.28 获取A区选中的行 
            string search = this.yssBuildLeftCheckRowsStr("portfolio");

            ClsQuyStrUtil quyStrUtil = new ClsQuyStrUtil();
            ////quyStrUtil.addQuyCon("portCode", "C_DV_PORT_CLS_TYPE", search, "IN");
            quyStrUtil.addQuyCon("portCode", "C_PORT_CODE", search, "IN");

            ////if (this.cboPort.Value != null && this.cboPort.Value.ToString().Length > 0)
            ////{
            ////    quyStrUtil.addQuyCon("C_PORT_CODE", this.cboPort.Value, "=");
            ////}

            ////if (this.txtClassCode.Text.Trim().Length != 0 && this.txtClassCode.Text.Trim() != "")
            ////{
            ////    quyStrUtil.addQuyCon("C_PORT_CLS_CODE", this.txtClassCode, "=");
            ////}

            if (this.selClsPort.Value != null && this.selClsPort.Value.ToString().Length > 0)
            {
                quyStrUtil.addQuyCon("C_PORT_CLS_CODE", this.selClsPort.Value, "=");
            }

            if (this.selClsType.Value != null && this.selClsType.Value.ToString().Length > 0) 
            {
                quyStrUtil.addQuyCon("C_DV_PORT_CLS_TYPE", this.selClsType.Value, "=");
            }

            cond = quyStrUtil.getQuyStr("pubvocabulary");
            return cond;
        }

        /// <summary>
        /// 封装条件对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            string search = this.yssBuildLeftCheckRowsStr("pd_portfolio");
            search = Regex.Replace(search, "'", "");
            paraDict.Add("ARRAY_C_PORT_CODE", search);

            if (this.selClsPort.Value != null && this.selClsPort.Value.ToString().Length > 0)
            {
                paraDict.Add("C_PORT_CLS_CODE", this.selClsPort.Value);
            }

            if (this.selClsType.Value != null && this.selClsType.Value.ToString().Length > 0)
            {
                paraDict.Add("C_DV_PORT_CLS_TYPE", this.selClsType.Value);
            }

            ////STORY #25082 【紧急】[广发证券]分级产品参数界面增加以到期日期判定条件的搜索 
            ////add by xhb 添加到期日期的查询
            if (this.dtpExpiryDate.Checked) 
            {
                paraDict.Add("ExpiryDateBegin", this.dtpExpiryDate.getBeginDateStr);
                if (this.dtpExpiryDate.YssShowSecond)
                {
                    paraDict.Add("ExpiryDateEnd", this.dtpExpiryDate.getEndDateStr);
                }
            }
           
            ////STORY #27362 add by xhb 添加成交日期的查询
            if (this.dtpTradeDate.Checked) 
            {
                paraDict.Add("TradeDateBegin", this.dtpTradeDate.getBeginDateStr);
                if (this.dtpExpiryDate.YssShowSecond)
                {
                    paraDict.Add("TradeDateEnd", this.dtpTradeDate.getEndDateStr);
                }
            }

            return paraDict;
        }

        /// <summary>
        /// 窗体LOAD事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_PORT_CLS_L_Load(object sender, EventArgs e)
        {
            Type type = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            dataService = ServiceFactory.createService(type) as IPortClsService;
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
        public void LoadDetailData(FAST.Common.Service.Pojo.Base.BasePojo mainData)
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
                this.MainDataPojo = mainData;
                this.geneParaAssemble.Clear();
                this.geneParaAssemble.Add("dataClass", "PortCls");
                this.geneParaAssemble.Add("ARRAY_C_PORT_CODE", (mainData as FAST.Common.Service.Pojo.Port).C_PORT_CODE);

                ////验证通过，开始装载数据
                this.btnSearch_Click(this.btnBar.getButton(FAST.Core.BaseControl.Fun.ClsButtonName.BtnRefresh).Owner, EventArgs.Empty);
            }
        }

        /// <summary>
        /// 重写基类
        /// </summary>
        /// <param name="paraDict">paradict</param>
        /// <returns>paradict</returns>
        protected override Dictionary<string, string> OnAfterGetParaAssemble(Dictionary<string, string> paraDict)
        {
            if (!paraDict.ContainsKey("dataClass"))
            {
                paraDict.Add("dataClass", "PortCls");
            }

            if (!paraDict.ContainsKey("ARRAY_C_PORT_CODE") || string.IsNullOrEmpty(paraDict["ARRAY_C_PORT_CODE"]))
            {
                if (null != this.geneParaAssemble && this.geneParaAssemble.ContainsKey("ARRAY_C_PORT_CODE"))
                {
                    paraDict.Remove("ARRAY_C_PORT_CODE");
                    paraDict.Add("ARRAY_C_PORT_CODE", this.geneParaAssemble["ARRAY_C_PORT_CODE"]);
                }
            }

            return paraDict;
        }

        #endregion


        /// <summary>
        /// 根据A区所选组合加载分级组合，如果不选，则默认加载全部的分级组合
        /// add by xhb 20160128  BUG #126111 
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void selClsPort_YssOnbeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            try
            {
                if (this.TableLeftMain.CheckedRows != null && this.TableLeftMain.CheckedRows.Count > 0)
                {
                    this.selClsPort.MethodInfo.MethodName = "getDataListByPorts";
                    string search = this.yssBuildLeftCheckRowsStr("portfolio");
                    this.selClsPort.MethodInfo.MethodParamValues = new string[] { search + "," };
                }
                else 
                {
                    this.selClsPort.MethodInfo.MethodName = null;
                    this.selClsPort.MethodInfo.MethodParamValues = null;
                }
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// override by lixiang@ysstech.com STORY #55372 分级产品设置界面产品权限控制可配置化
        /// </summary>
        /// <param name="dataList"></param>
        /// <param name="operType"></param>
        /// <param name="message"></param>
        /// <returns></returns>
        public override ArrayList validPortDataOperRight(ArrayList dataList, string operType, string message)
        {
            // add by lixiang@ysstech.com STORY #55372 分级产品设置界面产品权限控制可配置化
            IPortOperValidService portOperValidService = ServiceFactory.createService<IPortOperValidService>();
            string needFilterRight = portOperValidService.isNeedFilterRight("port");

            if ("true".Equals(needFilterRight))
            {
                return base.validPortDataOperRight(dataList, operType, message);
            }
            else
            {
                return dataList;
            }
        }

        /// <summary>
        /// 重写A区配置信息，走基类老旧模式。（可以自定义A区数据）
        /// </summary>
        public override AreaAConfigInfo AreaAConfigInfo
        {
            get
            {
                base.AreaAConfigInfo.AreaType = AreaType.BaseDefault;
                return base.AreaAConfigInfo;
            }
        }

        public override QueryRes yssGetLeftDataMVC()
        {
            QueryRes res = null;
            IPortDataService _portDataService = DataServiceFactory.createService<IPortDataService>();
            res = _portDataService.doPortFilterRes("false", "", "", "TAQS");
            IAccTypeDataService accTypeDataService = DataServiceFactory.createService<IAccTypeDataService>();
            Dictionary<string, string> diction = accTypeDataService.getKeyConvertMap();
            res.ShowConvertAssemble = new Dictionary<string, Dictionary<string, string>>();
            res.ShowConvertAssemble.Add("C_DAT_CLS", diction);
            return res;
        }

        /// <summary>
        /// 被调用处理加载A区数据的方法
        /// <author>yh 2011.02.28</author>
        /// </summary>
        protected override void loadLeftDataProcMVC()
        {
            QueryRes res = null;
            ArrayList showColumnList = null;
            TableListLoader tableLoader = null;
            try
            {
                res = this.yssGetLeftDataMVC();
                tableLoader = new TableListLoader();
                showColumnList = this.getLeftTableShowColumn();
                if (showColumnList.Count > 0)
                {
                    tableLoader.loadTable(tbLeftMain, res, false, false, ClsEnums.KTableDataShowMode.TreeMode, showColumnList);
                }
                else
                {
                    tableLoader.loadTable(tbLeftMain, res, false, false, ClsEnums.KTableDataShowMode.TreeMode);
                }

            }
            catch (ServiceException ex)
            {
                ClsRetInfo retInfo = new ClsRetInfo();
                retInfo.infoTitle = "服务调用失败";
                retInfo.infoContent = ex.Message;
                if (null != ex.InnerException)
                {
                    //// 添加对serviceException异常的处理 byleeyu20130902  需求号:#4515 
                    string s = ex.InnerException.Message;
                    if (s.IndexOf(ClsConstant._JSONRetInfoTitle) > -1)
                    {
                        s = s.Substring(s.IndexOf(ClsConstant._JSONRetInfoTitle));
                        ClsRetInfo retInfotemp = ClsRetInfoDealer.getReturnInfo(s);
                        retInfo.detailInfo = retInfotemp.detailInfo;
                    }
                    else
                    {
                        retInfo.detailInfo = ex.InnerException.Message;
                    }
                }

                MessageDialog msgDailog = new MessageDialog();
                msgDailog.Show(retInfo.infoContent, retInfo.infoTitle, retInfo.buttonGroup, retInfo.icon, retInfo.detailInfo);
            }
            catch (System.Exception ex)
            {
                //// YssMessageBox.ShowDyanInformation("加载快捷区数据出现异常", ex.Message, "系统提示", MessageBoxIcon.Error, FAST.Core.Context.Util.ClsEnums.ButtonType.Detail);
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-110013", _formFun, status, ex));
            }
        }

        /// <summary>
        /// 1
        /// </summary>
        /// <returns>2</returns>
        protected override ArrayList getLeftTableShowColumn()
        {
            ArrayList showColumnList = new ArrayList();
            showColumnList.Add("C_PORT_NAME_ST");
            showColumnList.Add("C_PORT_CODE");
            showColumnList.Add("C_PORT_NAME");
            showColumnList.Add("C_DAT_CLS");
            base.matchSearchStr = new string[5] { "C_PORT_NAME_ST", "C_PORT_CODE", "C_PORT_NAME", "C_PORT_CODE_P", "C_DAT_CLS" };     // 【搜索】功能匹配的属性
            return showColumnList;
        }

    }
}