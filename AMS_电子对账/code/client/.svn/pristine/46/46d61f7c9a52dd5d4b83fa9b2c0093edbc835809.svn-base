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
using FAST.Core.Exceptions;
using YssElecReco.Pojo.Er.Reverse;
using YssInformation.Support.Bi.AssociationOrgan.Service;
using YssInformation.Support.Bi.AssociationOrgan.Pojo;

namespace YssElecReco.Form.Er.Reverse
{
    public partial class Frm_ELEC_REVE_ASSMAP_S : FrmBaseSet
    {
        private IOrgService orgService = null;
        public Frm_ELEC_REVE_ASSMAP_S()
        {
            bUseMVCService = true;
            InitializeComponent();
            orgService = ServiceFactory.createService<IOrgService>();
        }

        /// <summary>
        /// 显示单条数据，参数为set界面数据对应的pojo对象
        /// </summary>
        /// <param name="pojo">显示数据对应的pojo对象</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            try
            {
                AssMap pm = this.frmBaseViewList.tbMain.SelectedRow.Tag as AssMap;
                this.cboDzType.Value = pm.C_FILE_TYPE;
                this.cboTgh.Value = pm.C_TGH_CODE;
                this.cboTZZH.Value = pm.C_PORT_CODE;
                this.txtTGHZCDM.Text = pm.C_PORT_CODE_OUT;
                this.cboDZMS.Value = pm.C_DV_DZ_MODE;
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
                AssMap pm = new AssMap();
                pm.C_PORT_CODE = this.cboTZZH.Value == null ? "" : this.cboTZZH.Value;
                pm.C_FILE_TYPE = this.cboDzType.Value == null ? "" : this.cboDzType.Value;
                pm.C_TGH_CODE = this.cboTgh.Value == null ? "" : this.cboTgh.Value;
                pm.C_PORT_CODE_OUT = this.txtTGHZCDM.Text == null ? "" : this.txtTGHZCDM.Text.Trim();
                pm.C_DV_DZ_MODE = this.cboDZMS.Value == null ? "" : this.cboDZMS.Value;
                return pm;
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

        

    }

}
