using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using YssElecReco.pojo.Er;
using YssElecReco.Service.Er;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Context.Events;
using FAST.Common.Service.Services;
using FAST.Core.Communication.Service;
using FAST.Core.Context;
using FAST.Core.CRUD.Interface;



namespace YssElecReco.Form.Er
{
    /// <summary>
    /// 电子对账模板关联
    /// </summary>
    public partial class Frm_ELEC_TMPL_RELA_S : FrmBaseSet
    {
        /// <summary>
        /// 对账模板服务
        /// </summary>
        private IDzTemplateService _templateService = null;

        /// <summary>
        /// 电子对账模板关联
        /// </summary>
        public Frm_ELEC_TMPL_RELA_S()
        {
            InitializeComponent();
            ////setPortSelCombox(this.cboPort); edit by Yuntao Lau 2015-08-14 BUG #117468
            bUseMVCService = true;
        }

        /// <summary>
        /// 对账模板服务
        /// </summary>
        private IDzTemplateService TemplateService
        {
            get
            {
                if (_templateService == null)
                {
                    _templateService = ServiceFactory.createService<IDzTemplateService>();
                }

                return _templateService;
            }
        }

        /// <summary>
        /// 将界面控件录入信息封装成pojo对象
        /// </summary>
        /// <returns>封装的pojo对象</returns>
        public override AuditableParamPojo faceInfoToObjMVC()
        {
            DzTmplRela relaInfo = null;
            try
            {
                relaInfo = new DzTmplRela();
                relaInfo.C_PORT_CODE = this.cboPort.Value;
                relaInfo.C_TMPL_CODE = this.cboDzTmpl.Value;
                relaInfo.C_TMPL_TYPE = this.cboDzType.Value;
            }
            catch (Exception e)
            {
            }

            return relaInfo;
        }

        /// <summary>
        /// 将界面控件录入信息封装成pojo对象
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            try
            {
                DzTmplRela relaInfo = this.yssGetBaseSelTypeItemMVC() as DzTmplRela;
                this.cboDzType.Value = relaInfo.C_TMPL_TYPE;
                this.cboDzTmpl.Value = relaInfo.C_TMPL_CODE;
                this.cboPort.Value = relaInfo.C_PORT_CODE;
            }
            catch (Exception e)
            {
            }
        }

        /// <summary>
        /// 锁定特定的控件，禁止更改控件文本及相关属性。
        /// </summary>
        protected override void OnLockSpecialControlState()
        {
            base.OnLockSpecialControlState();

            ////List界面被关联内嵌至其他界面时，对账类型、对账模板不能被更改
            if (this.frmBaseViewList != null && this.frmBaseViewList is IFormDetailData)
            {
                IFormDetailData frmDetailData = this.frmBaseViewList as IFormDetailData;
                if (frmDetailData.MainDataPojo != null && (frmDetailData.MainDataPojo is DzTemplate))
                {
                    this.cboDzType.YssReadOnly = true;
                    this.cboDzType.Value = (frmDetailData.MainDataPojo as DzTemplate).C_TMPL_TYPE;
                    this.cboDzTmpl.YssReadOnly = true;
                    this.cboDzTmpl.Value = (frmDetailData.MainDataPojo as DzTemplate).C_TMPL_CODE;
                }
            }
        }

        /// <summary>
        /// 控件数据加载前事件
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件</param>
        private void cboDzTmpl_YssOnBeforeLoadData(object sender, YssBeforeOperEventArgs e)
        {
            e.IsCancel = true;
            if (this.cboDzTmpl.Items != null && this.cboDzTmpl.Items.Count > 0)
            {
                return;
            }
            else
            {
                Dictionary<string, string> paraDict = new Dictionary<string, string>();
                paraDict.Add("dataClass", "DzTemplate");
                paraDict.Add("C_TMPL_TYPE", string.IsNullOrEmpty(this.cboDzType.Value) ? " " : this.cboDzType.Value);
                paraDict.Add("C_DV_TMPL_STATUS", "TEMP_USABLE");
                List<BasePojo> templateList = this.TemplateService.queryByCondition(paraDict).DataList;

                if (templateList != null && templateList.Count > 0)
                {
                    foreach (BasePojo pojo in templateList)
                    {
                        Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(pojo);
                        e.Collection.Add(entity);
                        this.cboDzTmpl.Items.Add(entity);
                    }
                }
            }
        }

        /// <summary>
        /// 对账类型改变重新加载模板
        /// </summary>
        /// <param name="sender">控件</param>
        /// <param name="e">事件</param>
        private void cboDzType_SelectedValueChanged(object sender, EventArgs e)
        {
            if (this.cboDzTmpl.Items != null && this.cboDzTmpl.Items.Count > 0)
            {
                this.cboDzTmpl.Value = null;
                this.cboDzTmpl.Items.Clear();
            }
        }

        /// <summary>
        ///  初始化窗体控件状态
        /// </summary>
        public override void initControlStat()
        {
            base.initControlStat();
            if (status == ClsEnums.StatusSetting.YssAdd)
            {
                this.cboDzType.Value = "1011";
            }
        }
    }
}
