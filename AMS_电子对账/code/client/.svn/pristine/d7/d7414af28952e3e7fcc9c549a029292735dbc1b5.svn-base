using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Communication.Service;
using YssElecReco.Pojo.Er.Reverse;
using FAST.Core.Exceptions;
using FAST.Common.Service.Pojo;
using YssElecReco.Service.Er.Reverse;
using YssElecReco.Pojo.Bi;
using YssInformation.Support.Bi.AssociationOrgan.Service;
using YssInformation.Support.Bi.AssociationOrgan.Pojo;

namespace YssElecReco.Form.Er.Reverse
{
    public partial class Frm_ELEC_REVE_ZBMAP_S : FrmBaseSet
    {
        private IElecTghRelaService tghRelaService = null;
        private IElecPortRelaService portRelaService = null;
        private IOrgService orgService = null;
        public Frm_ELEC_REVE_ZBMAP_S()
        {
            bUseMVCService = true;
            InitializeComponent();
            orgService = ServiceFactory.createService<IOrgService>();
            portRelaService = ServiceFactory.createService<IElecPortRelaService>();
            tghRelaService = ServiceFactory.createService<IElecTghRelaService>();
        }

        /// <summary>
        /// 显示单条数据，参数为set界面数据对应的pojo对象
        /// </summary>
        /// <param name="pojo">显示数据对应的pojo对象</param>
        public override void showInfoMVC(BasePojo pojos)
        {
            try
            {
                ZbMap pojo = this.frmBaseViewList.tbMain.SelectedRow.Tag as ZbMap;
                this.cboDzType.Value = pojo.C_FILE_TYPE;
                this.cboTgh.Value = pojo.C_TGH_CODE;
                this.cboTZZH.Value = pojo.C_PORT_CODE;
                this.cboZbCode.Value = pojo.C_ZB_CODE;
                this.txtInnerZBName.Text = pojo.C_ZB_NAME;
                this.txtOutZbCode.Text = pojo.C_ZB_CODE_OUT;
                this.txtOutZBName.Text = pojo.C_ZB_NAME_OUT;
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }

        }

        /// <summary>
        /// 将界面控件录入信息封装成pojo对象
        /// </summary>
        /// <returns>封装的pojo对象</returns>
        public override AuditableParamPojo faceInfoToObjMVC()
        {
            try
            {
                ZbMap pojo = new ZbMap();
                pojo.C_PORT_CODE = this.cboTZZH.Value == null ? "" : this.cboTZZH.Value;
                pojo.C_FILE_TYPE = this.cboDzType.Value == null ? "" : this.cboDzType.Value;
                pojo.C_TGH_CODE = this.cboTgh.Value == null ? "" : this.cboTgh.Value;
                pojo.C_ZB_CODE = this.cboZbCode.Value == null ? "" : this.cboZbCode.Value;
                pojo.C_ZB_NAME = this.txtInnerZBName.Text == null ? "" : this.txtInnerZBName.Text.Trim();
                pojo.C_ZB_CODE_OUT = this.txtOutZbCode.Text == null ? "" : this.txtOutZbCode.Text.Trim();
                pojo.C_ZB_NAME_OUT = this.txtOutZBName.Text == null ? "" : this.txtOutZBName.Text.Trim();
                return pojo;
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }
        }

        /// <summary>
        /// 托管银行数据加载前事件
        /// 组合和托管行相互级联
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboTgh_YssOnBeforeLoadData(object sender, FAST.Core.Context.Events.YssBeforeOperEventArgs e)
        {
            this.cboTZZH.Items.Clear();
            List<BasePojo> orgList = null;
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            paraDict.Add("dataClass", "Org");
            if (null != this.cboTZZH.Value && !"".Equals(this.cboTZZH.Value.Trim()))
            {
                paraDict.Add("ARRAY_C_PORT_CODE", this.cboTZZH.Value);
                paraDict.Add("ARRAY_C_DV_TYPE_CODE", "TRUSTEE,TRUSTEE_SEC");//// 加载托管行（商业银行）
                orgList = tghRelaService.queryOrganByPort(paraDict);

            }
            else
            {
                paraDict.Add("ARRAY_C_QUALIFICATION", "TRUSTEE,TRUSTEE_SEC");//// 加载托管行（商业银行）
                orgList = orgService.queryByCondition(paraDict).DataList;
            }
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
        /// 组合和托管行相互级联
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void cboTZZH_YssOnBeforeLoadData(object sender, FAST.Core.Context.Events.YssBeforeOperEventArgs e)
        {

            this.cboTgh.Items.Clear();
            List<BasePojo> portList = null;

            if (null != this.cboTgh.Value && !"".Equals(this.cboTgh.Value.Trim()))
            {
                Dictionary<string, string> paraDict = new Dictionary<string, string>();
                paraDict.Add("C_RELA_CODE", this.cboTgh.Value);
                paraDict.Add("dataClass", "Port");
                paraDict.Add("C_RELA_TYPE", "RELA_ORG");
                paraDict.Add("ARRAY_C_DV_TYPE_CODE", "TRUSTEE,TRUSTEE_SEC");//// 加载托管行（商业银行）
                portList = portRelaService.queryPortRelaOrgan(paraDict);
                e.IsCancel = true;
            }
            else
            {
                e.IsCancel = false;
                return;
            }


            if (portList != null && portList.Count > 0)
            {
                foreach (Port port in portList)
                {
                    Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(port);
                    e.Collection.Add(entity);
                }
            }


        }

        private void cboZbCode_SelectedValueChanged(object sender, EventArgs e)
        {
            if(this.cboZbCode.Text != null)
            {
                this.txtInnerZBName.Text = this.cboZbCode.Text;
            }
        }

        /// <summary>
        /// 指标项与对账类型级联
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboZbCode_YssOnBeforeLoadData(object sender, FAST.Core.Context.Events.YssBeforeOperEventArgs e)
        {
            if(this.cboDzType.Value != null)
            {
                if(this.cboZbCode.Items.Count<=0)
                {
                    Dictionary<string, ElecRela> dict = (this.dataService as IZbMapService).getZbItems(this.cboDzType.Value);

                    foreach (KeyValuePair<string, ElecRela> kv in dict)
                    {
                        Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(kv.Value);
                        e.Collection.Add(entity);
                     }
                }
            }
            ////指定控件不要自动去加载数据
            e.IsCancel = true;
        }

        private void cboDzType_SelectedValueChanged(object sender, EventArgs e)
        {
            this.cboZbCode.Items.Clear();
        }

    }
}
