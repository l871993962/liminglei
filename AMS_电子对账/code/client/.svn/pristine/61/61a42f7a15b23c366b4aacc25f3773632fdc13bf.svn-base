using System;
using System.Collections.Generic;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Pojo;
using FAST.Core.Communication.Service;
using FAST.Core.Util;
using FAST.Core.BaseControl.Fun;
using FAST.Core.Context;
using FAST.Core.Exceptions;
using FAST.Core.CRUD.Interface;
using FAST.Core.Context.Events;
using System.Collections;
using YssSztTool.Pojo.Para;
using System.Windows.Forms;

namespace YssSztTool.Form.Para
{
    /// <summary>
    /// 电子对账参数设置
    /// </summary>
    public partial class Frm_DZ_PARA_ORG_L : FrmBaseList, IFormDetailData
    {
        /// <summary>
        /// 获取或设置一个值，该值为主窗体传过来的数据。当该窗体为某一窗体的附属窗体时有效。
        /// </summary>
        private BasePojo _mainDataPojo;

        ////private IPortRelaService relaService = null;

        /// <summary>
        /// 获取或设置当前窗体是否已经被FrmBaseListWidthDetails窗体关联为子窗体
        /// </summary>
        private bool _hadBeenRelationed = false;

        /// <summary>
        /// 构造方法
        /// </summary>
        public Frm_DZ_PARA_ORG_L()
        {
            InitializeComponent();
            dataAdmin = new ClsBaseDataAdmin();
            isLoadFirst = true; ////首次打开加载数据
            ////this.dataService = ServiceFactory.createService<IPortRelaService>();
            this.YssMainKTableShowMode = ClsEnums.KTableDataShowMode.ListMode;
            ////relaService = ServiceFactory.createService<IPortRelaService>();
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
        /// 验证是否需要重新装载数据
        /// </summary>
        /// <param name="mainData">主表传过来的数据</param>
        /// <returns>返回验证结果</returns>
        public bool AllowReloadDetailData(BasePojo mainData)
        {
            bool retValue = true;
            if (mainData == null) 
            {
                return false;
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

             this.dataAdmin.C_OperMenu_Code = _formFun.C_FUN_CODE;
             _formFun.C_FUN_CODE = "dzRelaOrgan";
            this.bUseMVCService = true;
            this.ShowLeftPanel = false;
            this.ShowFilterPanel = false;
        }

        /// <summary>
        /// 装载数据
        /// </summary>
        /// <param name="mainData">主表传过来的Pojo</param>
        public void LoadDetailData(BasePojo mainData)
        {
            bool validate = this.AllowReloadDetailData(mainData);
            if (validate)
            {
                this.AllowResetGeneParaAssemble = false;
                this.MainDataPojo = mainData;
                this.geneParaAssemble.Clear();
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
            if (null != this.MainDataPojo)
            {
                DzPara dzPara = this.MainDataPojo as DzPara;
                paraDict.Add("dataClass", "DzRelaOrgan");
                if ("1".Equals(dzPara.C_Has_Branch))
                {
                    paraDict.Add("C_RELA_CODE_P", dzPara.C_TGH_CODE);
                }
                else
                {
                    paraDict.Add("C_RELA_CODE", dzPara.C_TGH_CODE);
                }

                if (dzPara.C_MANAGE_CODE != null && dzPara.C_MANAGE_CODE.Trim().Length > 0)
                {
                    paraDict.Add("C_MANAGE_CODE", dzPara.C_MANAGE_CODE);
                }
            }

            return paraDict;

        }

        #region 复写基类的按钮事件，防止提示信息中出现删除线
        /// <summary>
        /// 重写添加事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnNew_Click(object sender, EventArgs e)
        {
            try
            {
                if (!ClsContext.sysFunHash.ContainsKey(dataAdmin.C_OperMenu_Code))
                {
                    setFunHash(dataAdmin.C_OperMenu_Code);
                }

                base.btnNew_Click(sender, e);
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }
        }
        //STORY65389电子对账前台与估值核算解耦
        ////private BasePojo convertToPortRela(BasePojo basePojo) 
        ////{
        ////    PortRela portRela = new PortRela();
        ////    portRela.C_PORT_CODE = (basePojo as DzRelaOrgan).C_PORT_CODE;
        ////    portRela.C_DV_TYPE_CODE = (basePojo as DzRelaOrgan).C_DV_TYPE_CODE;
        ////    portRela.C_RELA_CODE = (basePojo as DzRelaOrgan).C_ORG_CODE;
        ////    portRela.Id = (basePojo as DzRelaOrgan).Id;
        ////    portRela.AuditDate = (basePojo as DzRelaOrgan).AuditDate;
        ////    portRela.AuditState = (basePojo as DzRelaOrgan).AuditState;
        ////    return portRela;
        ////}

        public override bool operAdaptMVC(object sender, EventArgs e, ClsEnums.StatusSetting operSta)
        {
            bool operFlag = false;
            try
            {
                status = operSta;
                ArrayList dataList = null; // 获取当前选中行的数组 yh 2011-03-13
                ArrayList tempList = new ArrayList();
                dataList = getSelectTypeItemListAuditable();
                if (dataList == null || dataList.Count == 0)
                {
                    return operFlag;
                }

                if (!checkBeforeOperMVC(sender, e, dataList, operSta))
                {
                    return operFlag;
                }

                //this._operBeginTime = DateTime.Now;
                string res = "";
                Dictionary<string, string> portCodeNameDic = new Dictionary<string, string>();

                switch (operSta)
                {
                    case ClsEnums.StatusSetting.YssDel:

                        ////start Orlando 2012-8-31 add 
                        ////datalist可能存在已审核数据，需要剔除掉
                        ////同更应用于审核与反审核操作                      
                        for (int i = 0; i < dataList.Count; i++)
                        {
                            BasePojo pojo = (BasePojo)dataList[i];
                            //STORY65389电子对账前台与估值核算解耦
                            ////pojo = convertToPortRela(pojo);
                            ////增加过滤,开始自行复合机制时,不可以删除审核状态为1的记录. added by ll 20121012
                            if (ClsContext.sysFunHash[_formFun.C_FUN_CODE].N_CHECK == 1)
                            {
                                if (pojo is AuditableParamPojo && (pojo as AuditableParamPojo).AuditState == 1)
                                {
                                    dataList.RemoveAt(i);
                                    i--;
                                }
                            }

                            if (pojo is ParamPojo)
                            {
                                ((ParamPojo)pojo).Modifier = ClsContext.currentUser.C_USER_CODE;
                            }
                            tempList.Add(pojo);
                        }

                        /* 
                         * Author : ChenLong
                         * Date   : 2014-12-21
                         * Status : Add
                         * Comment: 验证是否有组合数据删除权限
                         *  */
                        dataList = validPortDataOperRight(dataList, ClsEnums.StatusSetting.YssDel.ToString(), "没有组合数据删除权限!");
                        ////校验组合数据锁定权限。张绍林-20150916
                        int size = dataList.Count;
                        dataList = this.ValidatePortLockAuthority(dataList);
                        if (size != dataList.Count)
                        {
                            YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("执行删除操作时包含已经净值确认的数据，这些数据将不会被删除!"));
                        }

                        if (dataList.Count == 0)
                        {
                            return operFlag;
                        }

                        ////end Orlando 2012-8-31 add
                        ////STORY65389电子对账前台与估值核算解耦
                        ////res = relaService.deleteById(tempList);
                        res = this.dataService.deleteById(tempList);
                        break;
                    case ClsEnums.StatusSetting.YssAudit:
                        ////////start Orlando 2012-8-31 add 
                        ////////datalist可能存在已审核数据，需要剔除掉                  
                        for (int i = 0; i < dataList.Count; i++)
                        {
                            BasePojo pojo = (BasePojo)dataList[i];
                            //STORY65389电子对账前台与估值核算解耦
                            //pojo = convertToPortRela(pojo);
                            if (pojo is AuditableParamPojo && (pojo as AuditableParamPojo).AuditState == 1)
                            {
                                dataList.RemoveAt(i);
                                i--;
                            }
                            tempList.Add(pojo);
                        }

                        /* 
                        * Author : ChenLong
                        * Date   : 2014-12-21
                        * Status : Add
                        * Comment: 验证是否有组合数据审核权限
                        *  */
                        dataList = validPortDataOperRight(dataList, ClsEnums.StatusSetting.YssAudit.ToString(), " 没有组合数据审核权限!");
                        //// 校验组合数据锁定权限 liuxiang 2015-9-17
                        size = dataList.Count;
                        dataList = this.ValidatePortLockAuthority(dataList);
                        if (size != dataList.Count)
                        {
                            YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("执行审核操作时包含已经净值确认的数据，这些数据将不会被审核!"));
                        }

                        if (dataList.Count == 0)
                        {
                            return operFlag;
                        }

                        ////end Orlando 2012-8-31 add

                        dataList = DataFunction.setOperator(dataList);
                        dataList = DataFunction.setAuditStateByOperState(dataList, status);
                        ////STORY65389电子对账前台与估值核算解耦
                        ////res = relaService.auditById(tempList);
                        res = this.dataService.auditById(tempList);
                        break;
                    case ClsEnums.StatusSetting.YssUnAudit:

                        ////start Orlando 2012-8-31 add 
                        ////datalist可能存在未审核数据，需要剔除掉                    
                        for (int i = 0; i < dataList.Count; i++)
                        {
                            BasePojo pojo = (BasePojo)dataList[i];
                            //STORY65389电子对账前台与估值核算解耦
                            //pojo = convertToPortRela(pojo);
                            if (pojo is AuditableParamPojo &&　(pojo as AuditableParamPojo).AuditState == 0)
                            {
                                dataList.RemoveAt(i);
                                i--;
                            }
                            tempList.Add(pojo);
                        }

                        /* 
                         * Author : ChenLong
                         * Date   : 2014-12-21
                         * Status : Add
                         * Comment: 验证是否有组合数据反审核权限
                         *  */
                        dataList = validPortDataOperRight(dataList, ClsEnums.StatusSetting.YssUnAudit.ToString(), " 没有组合数据反审核权限!");
                        //// 校验组合数据锁定权限 liuxiang 2015-9-17
                        size = dataList.Count;
                        dataList = this.ValidatePortLockAuthority(dataList);
                        if (size != dataList.Count)
                        {
                            YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getFixedInfo("执行反审核操作时包含已经净值确认的数据，这些数据将不会被反审核!"));
                        }

                        if (dataList.Count == 0)
                        {
                            return operFlag;
                        }

                        ////end Orlando 2012-8-31 add

                        dataList = DataFunction.setOperator(dataList);
                        dataList = DataFunction.setAuditStateByOperState(dataList, status);
                        ////STORY65389电子对账前台与估值核算解耦
                        ////res = relaService.unAuditById(tempList);
                        res = this.dataService.unAuditById(tempList);
                        break;
                    case ClsEnums.StatusSetting.YssUnBind:
                        //新增解除关联功能，功能类似于删除

                        for (int i = 0; i < dataList.Count; i++)
                        {
                            BasePojo pojo = (BasePojo)dataList[i];
                            //STORY65389电子对账前台与估值核算解耦
                            //pojo = convertToPortRela(pojo);
                            ////增加过滤,开始自行复合机制时,不可以删除审核状态为1的记录
                            if (ClsContext.sysFunHash[_formFun.C_FUN_CODE].N_CHECK == 1)
                            {
                                if (pojo is AuditableParamPojo && (pojo as AuditableParamPojo).AuditState == 1)
                                {
                                    dataList.RemoveAt(i);
                                    i--;
                                }
                            }

                            if (pojo is ParamPojo)
                            {
                                ((ParamPojo)pojo).Modifier = ClsContext.currentUser.C_USER_CODE;
                            }
                        }
                        if (dataList.Count == 0)
                        {
                            return operFlag;
                        }
                        ////STORY65389电子对账前台与估值核算解耦
                        ////res = relaService.deleteById(tempList);
                        res = this.dataService.deleteById(tempList);
                        break;
                }

                //this._operEndTime = DateTime.Now;

                string operResult = "Success";

                if (ClsRetInfoDealer.isJsonInfo(res))
                {
                    ClsRetInfo retInfo = new ClsRetInfo();
                    retInfo = (ClsRetInfo)ClsRetInfoDealer.getReturnInfo(res);
                    setDataCheckInfoObject(retInfo, dataList);
                    YssMessageBox.ShowCommonInfo(retInfo);
                    ////YssMessageBox.ShowCommonInfo(res);
                    operResult = retInfo.operRes;
                    if ("Success" == operResult)
                    {
                        /*
                         * Author ; ChenLong
                         * Date   : 2016-01-19
                         * Status : Add
                         * Comment: 修改的行数据ID
                         */
                        this.ModifiedRowIds = retInfo.cidenList;
                        BasePojo baseDataClass = (BasePojo)ReflectBase.getInstance(_formFun.YssAssocia.CommonDataClassDll, _formFun.YssAssocia.CommonDataClass);
                        getMainListDataMVC(baseDataClass, true);
                        operFlag = true;
                    }
                }
                else
                {
                    operResult = res;
                    if ("Success" == operResult)
                    {
                        BasePojo baseDataClass = (BasePojo)ReflectBase.getInstance(_formFun.YssAssocia.CommonDataClassDll, _formFun.YssAssocia.CommonDataClass);
                        getMainListDataMVC(baseDataClass, true);
                        operFlag = true;
                    }
                    else
                    {
                        MessageBox.Show(res);
                    }

                }

                ////业务启动流程逻辑 Add 2014-11-21 by lj
                ////if ("Success" == operResult)
                ////{
                ////    yssBusStartProcess(dataList);
                ////}

                doAfterOperEvent(sender, e, status);

            }
            catch (System.Exception ex)
            {
                throw ex;
                ////YssMessageBox.ShowCommonInfo(ex.Message);
            }

            ////funButLabel:
            return operFlag;
        }

        /// <summary>
        /// 重写 复制事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnCopy_Click(object sender, EventArgs e)
        {
            try
            {
                if (!ClsContext.sysFunHash.ContainsKey(dataAdmin.C_OperMenu_Code))
                {
                    setFunHash(dataAdmin.C_OperMenu_Code);
                }

                base.btnCopy_Click(sender, e);
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }
        }

        /// <summary>
        /// 重写修改时间
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnEdit_Click(object sender, EventArgs e)
        {
            try
            {
                if (!ClsContext.sysFunHash.ContainsKey(dataAdmin.C_OperMenu_Code))
                {
                    setFunHash(dataAdmin.C_OperMenu_Code);
                }


                base.btnEdit_Click(sender, e);
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }

        }

        /// <summary>
        /// 重写删除事件 by tanhongpao 2012-7-19
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnDelete_Click(object sender, EventArgs e)
        {
            try
            {
                if (!ClsContext.sysFunHash.ContainsKey(dataAdmin.C_OperMenu_Code))
                {
                    setFunHash(dataAdmin.C_OperMenu_Code);
                }

                base.btnDelete_Click(sender, e);
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }
            finally
            {
                removeFunHash(dataAdmin.C_OperMenu_Code);
            }
        }

        /// <summary>
        /// 重写审核事件 by tanhongpao 2012-7-19
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnAudit_Click(object sender, EventArgs e)
        {
            try
            {
                if (!ClsContext.sysFunHash.ContainsKey(dataAdmin.C_OperMenu_Code))
                {
                    setFunHash(dataAdmin.C_OperMenu_Code);
                }

                base.btnAudit_Click(sender, e);
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }
            finally
            {
                removeFunHash(dataAdmin.C_OperMenu_Code);
            }
        }

        /// <summary>
        /// 反审核事件 by tanhongpao 2012-7-19
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        protected override void btnUnAudit_Click(object sender, EventArgs e)
        {
            try
            {
                if (!ClsContext.sysFunHash.ContainsKey(dataAdmin.C_OperMenu_Code))
                {
                    setFunHash(dataAdmin.C_OperMenu_Code);
                }

                base.btnUnAudit_Click(sender, e);
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ex.Message);
            }
            finally
            {
                removeFunHash(dataAdmin.C_OperMenu_Code);
            }

        }

        /// <summary>
        /// 向sysFunHash中临时添加功能对象
        /// 
        /// Update By tanhongpao 2012-7-19
        /// </summary>
        /// <param name="funcode">功能代码</param>
        private void setFunHash(string funcode)
        {
            try
            {
                IBaseFun fun = new SysFun();
                fun.C_FUN_CODE = "portorgan";
                fun.C_FUN_NAME = "关联机构";
                fun.C_FUN_CODE_P = "portrelation";
              
                if (!"RoleManage".Equals(funcode))
                {
                    if (!ClsContext.sysFunHash.ContainsKey(funcode))
                    {
                        ClsContext.sysFunHash.Add(funcode, fun);
                    }
                }

            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }
        }

        /// <summary>
        /// 将临时功能对象从sysFunHash中移除
        /// </summary>
        /// <param name="funcode">功能代码</param>
        private void removeFunHash(string funcode)
        {
            try
            {
                if (!"portrelation".Equals(funcode))
                {
                    ClsContext.sysFunHash.Remove(funcode);
                }
            }
            catch (Exception e)
            {
                YssMessageBox.ShowCommonInfo(e.Message);
            }
        }

        #endregion

        private void Frm_DZ_PARA_ORG_L_Load(object sender, EventArgs e)
        {
            this.btnBar.setButtonVisable(ClsButtonName.BtnCopy, false);
            this.btnBar.setButtonVisable(ClsButtonName.BtnEdit, false);
        }

        protected override void setButtonStaAfterTbMainClickMVC(BasePojo pojo)
        {
            this.btnBar.setButtonVisable(ClsButtonName.BtnCopy, false);
            this.btnBar.setButtonVisable(ClsButtonName.BtnEdit, false);
        }
    }
}
