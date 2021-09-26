using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.CRUD.Form;
using YssElecReco.Pojo.Er.Reverse;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;
using YssElecReco.Service.Er.Reverse;
using FAST.Core.Communication.Service;
using FAST.Core.Context;
using FAST.Core.BaseControl.Fun;
using FAST.Common.Service.Services.Base;
using System.Collections;
using FAST.Core.Util;
using FAST.Core.Context.Events;
using YssInformation.Support.Bi.AssociationOrgan.Service;
using YssInformation.Support.Bi.AssociationOrgan.Pojo;

namespace YssElecReco.Form.Er.Reverse
{
    public partial class Frm_ELEC_REVE_KM_OUT_S : FrmBaseSet
    {
        private Frm_ELEC_REVE_KMMAP_S frmSet;
        private IOrgService orgService = null;
        private IAssMapService assMapService = null;
        public Frm_ELEC_REVE_KM_OUT_S()
        {
            bUseMVCService = true;
            InitializeComponent();
            status = ClsEnums.StatusSetting.YssAdd;
            assMapService = ServiceFactory.createService<IAssMapService>();
            orgService = ServiceFactory.createService<IOrgService>();
        }

        public Frm_ELEC_REVE_KMMAP_S FrmSet
        {
            get { return this.frmSet; }
            set { this.frmSet = value; }
        }

        /// <summary>
        /// 初始化服务,用于子类重写
        /// </summary>
        protected override void initServiceMVC()
        {
            if (dataService == null)
            {
                dataService = (IServiceBus)ServiceFactory.createService<IErKmbOutService>();
            }
        }

        private List<BasePojo> getSelectPojos()
        { 
            if(this.frmSet == null)
            {
                return null;
            }
            return this.frmSet.getOutKmsSelectItems();
        }

        /// <summary>
        /// 窗体显示
        /// </summary>
        /// <param name="frm">list窗体</param>
        public void yssShowForm()
        {
            List<BasePojo> list = getSelectPojos();
            if (list != null&&list.Count>0)
            {
                status = ClsEnums.StatusSetting.YssBrow;
            }
            else
            {
                status = ClsEnums.StatusSetting.YssAdd;
            }
            
            this.ShowInTaskbar = false;
            this.Visible = false;

            // 先设置窗体控件的属性
            initControlStat();
            // 再打开窗体
            this.Show();

        }

        /// <summary>
        /// 初始化窗体控件状态
        /// </summary>
        public override void initControlStat()
        {
            // 修改最上面按钮的状态
            YssInitTopButtonStat();

            if (clsInterface == null)
            {
                clsInterface = new ClsInterface();
            }

            // 修改最下面状态栏的状态
            setStatusBar();

            // 数据安全管理列表窗体按钮机制 xuqiji 2011-01-26
            ////listSystemControl();

            // 更改控件/数据控件的状态 by leeyu 20111101
            YssChangeControlState();

            // 修改控件的状态
            ////m_BaseAdmin.initCtls(tbMain, status);
            clsInterface.setControlsStatus(tbMain, status);

            //this.LockSpecialControlState();
        }

        /// <summary>
        /// 控制顶部按钮状态
        /// 这里只进行逻辑控制
        /// </summary>
        protected override void YssInitTopButtonStat()
        {
            btnBar.setButtonByFormMode(getFormModeByStatus());
            btnBar.setButtonUnVisibled(ClsButtonName.BtnNext);
            btnBar.setButtonUnVisibled(ClsButtonName.BtnPrevious);
            btnBar.setButtonUnVisibled(ClsButtonName.BtnAudit);
            btnBar.setButtonUnVisibled(ClsButtonName.BtnUnAudit);
            switch (status)
            {
                case ClsEnums.StatusSetting.YssAdd:
                    btnBar.setButtonEnabled("btnSave", true);
                    break;
                case ClsEnums.StatusSetting.YssBrow:
                    btnBar.setButtonEnabled("btnEdit", true);
                    btnBar.setButtonEnabled("btnCopy", true);
                    break;
                case ClsEnums.StatusSetting.YssEdit:
                    btnBar.setButtonEnabled("btnSave", true);
                    btnBar.setButtonEnabled("btnDelete", true);
                    btnBar.setButtonEnabled("btnCancel", true);
                    btnBar.setButtonEnabled(ClsButtonName.BtnRecall);
                    break;
                case ClsEnums.StatusSetting.YssCopy:
                    btnBar.setButtonEnabled(ClsButtonName.BtnRecall);
                    btnBar.setButtonEnabled("btnSave", true);
                    btnBar.setButtonEnabled("btnCancel", true);
                    break;
            }
            btnBar.Refresh();
        }


        /// <summary>
        /// 显示单条数据，参数为set界面数据对应的pojo对象
        /// </summary>
        /// <param name="pojo">显示数据对应的pojo对象</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            try
            {
                if(pojo == null)
                {
                    List<BasePojo> pojos = getSelectPojos();
                    if (pojos != null && pojos.Count > 0)
                    {
                        pojo = pojos[0];
                    }else{
                        return;
                    }
                }
                ErKmbOut km = pojo as ErKmbOut;
                this.cboTZZH.Value = km.C_ASS_CODE;
                this.cboTgh.Value = km.C_TGH_CODE;
                this.txtKmCodeOut.Text = km.C_KM_CODE;
                this.txtKmNameOut.Text = km.C_KM_NAME;
                this.cboKmCls.Value = km.C_DV_KM_CLS;
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }

        }

        private List<BasePojo> getPortCodeOutByPortCodeOut(string portCode, string tghCode)
        {
            Dictionary<string, string> dict = new Dictionary<string, string>();
            dict.Add("dataClass", "AssMap");
            dict.Add("C_PORT_CODE_OUT", portCode);
            dict.Add("C_TGH_CODE", tghCode);
            dict.Add("C_FILE_TYPE", "1031");
            return assMapService.queryByCondition(dict).DataList;
        }

        private List<BasePojo> getPortCodeOut(string portCode, string tghCode)
        {
            Dictionary<string, string> dict = new Dictionary<string, string>();
            dict.Add("dataClass","AssMap");
            dict.Add("ARRAY_C_PORT_CODE", portCode);
            dict.Add("C_TGH_CODE", tghCode);
            dict.Add("C_FILE_TYPE_OR_NULL", "1031");
            return assMapService.queryByCondition(dict).DataList;
        }

        /// <summary>
        /// 将界面控件录入信息封装成pojo对象
        /// </summary>
        /// <returns>封装的pojo对象</returns>
        public override BasePojo faceInfoToBaseObjMVC()
        {
            try
            {

                ErKmbOut pm = new ErKmbOut();
                pm.C_ASS_CODE = this.cboTZZH.Value == null ? "" : this.cboTZZH.Value;
                pm.C_TGH_CODE = this.cboTgh.Value == null ? "" : this.cboTgh.Value;
                List<BasePojo> list = getPortCodeOut(pm.C_ASS_CODE, pm.C_TGH_CODE);
                if (list.Count > 0)
                {
                    pm.C_ASS_CODE = (list[0] as AssMap).C_PORT_CODE_OUT;
                    pm.C_KM_CODE = this.txtKmCodeOut.Text == null ? "" : this.txtKmCodeOut.Text.Trim();
                    pm.C_KM_NAME = this.txtKmNameOut.Text == null ? "" : this.txtKmNameOut.Text.Trim();
                    pm.C_DV_KM_CLS = this.cboKmCls.Value == null ? "" : this.cboKmCls.Value;
                    return pm;
                }
                else
                {
                    throw new Exception("该组合和该托管行没有进行资产映射！");
                }
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }

        }

        /// <summary>
        /// 托管银行数据加载前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboTgh_YssOnBeforeLoadData(object sender, FAST.Core.Context.Events.YssBeforeOperEventArgs e)
        {
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("dataClass", "Org");
            paraDict.Add("ARRAY_C_QUALIFICATION", "TRUSTEE,TRUSTEE_SEC");//// 加载托管行（商业银行）
            List<BasePojo> orgList = orgService.queryByCondition(paraDict).DataList;

            if (orgList != null && orgList.Count > 0)
            {
                foreach (Org org in orgList)
                {
                    Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(org);
                    e.Collection.Add(entity);
                }
            }

            ////指定控件不要自动去加载数据
            e.IsCancel = true;
        }

        /// <summary>
        /// 数据的保存事件
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件信息</param>
        protected override void btnSave_Click(object sender, System.EventArgs e)
        {
            YssMessageBox.currentForm = this;

            string operResult = "";
            DateTime begin = DateTime.Now;
            try
            {
                //if (frmBaseViewList != null)
                //{
                //    ////注销，系统已能实现Set界面保存时的数据联动，放开SelectionChanged事件，是为了是上下结构窗体数据能够联动。
                //    ////未发现有其它异常、影响。张绍林-20150601
                //    ////frmBaseViewList.tbMain.SelectionChanged -= frmBaseViewList.TableMain_SelectionChanged;
                //    _modifiedRowIds = null;
                //    if ((status == ClsEnums.StatusSetting.YssEdit || status == ClsEnums.StatusSetting.YssDel || status == ClsEnums.StatusSetting.YssAudit || status == ClsEnums.StatusSetting.YssUnAudit) && frmBaseViewList.tbMain.SelectedRow != null)
                //    {
                //        ////记录当前编辑的数据ID信息。
                //        if (frmBaseViewList.tbMain.SelectedRow.Tag != null && frmBaseViewList.tbMain.SelectedRow.Tag is BasePojo)
                //        {
                //            _modifiedRowIds = new List<string>();
                //            _modifiedRowIds.Add((frmBaseViewList.tbMain.SelectedRow.Tag as BasePojo).Id);
                //        }
                //    }
                //}

                YssBeforeOperEventArgs ye = new YssBeforeOperEventArgs(false);
                
                if (!ye.IsCancel)
                {
                    ////新增、修改操作需要作输入项校验
                    if (status == ClsEnums.StatusSetting.YssAdd || status == ClsEnums.StatusSetting.YssCopy || status == ClsEnums.StatusSetting.YssEdit)
                    {
                        if (!checkInput())
                        {
                            return;
                        }
                    }

                    ////校验组合锁定权限
                    if (this.ValidatePortLockAuthority() == false)
                    {
                        return;
                    }

                    operResult = yssFormOperation(status);

                    ////业务启动流程逻辑 Add 2014-11-21 by lj
                    yssBusStartProcess(operResult);

                    //// 经分析应先执行operAfterSave方法再执行refreshTbMain方法byleeyu20130727
                    operAfterSave(operResult);

                    if (this.frmSet != null)
                    {
                        this.frmSet.loadOutKmTable();
                    }
                    //status = ClsEnums.StatusSetting.YssBrow;
                    //initControlStat();
                }
            }
            catch (System.Exception ex)
            {
                YssMessageBox.ShowCommonInfos(TransferErrorMessageUtil.getTransferException(ex));
            }
            finally
            {
                YssMessageBox.currentForm = null;
            }
        }
        /// <summary>
        /// 新增时返回要操作的一组数据
        /// </summary>
        /// <returns>要操作的一组数据</returns>
        public override ArrayList yssGetObjListMVC()
        {
            ArrayList pojoList = new ArrayList();
            pojoList.Add(faceInfoToBaseObjMVC());
            return pojoList;
        }
        /// <summary>
        /// 显示单条数据
        /// </summary>
        public override void yssShowInfoAddForm()
        {
            try
            {
                this.cboTZZH.Value = this.frmSet.getCPZHValue();
                this.cboKmCls.Value = this.frmSet.getKmCls();
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }
        }
    }
}
