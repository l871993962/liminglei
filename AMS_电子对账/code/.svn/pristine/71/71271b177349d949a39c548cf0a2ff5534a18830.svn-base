using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.CRUD.Form;
using YssElecReco.Pojo.Er;
using FAST.Core.Exceptions;
using FAST.Core.Communication.Service;
using FAST.Common.Service.Pojo.Base;
using YssInformation.Support.Bi.AssociationOrgan.Service;
using YssInformation.Support.Bi.AssociationOrgan.Pojo;

namespace YssElecReco.Form.Er
{
    public partial class Frm_ELEC_SPLIT_RELA_S : FrmBaseSet
    {
        private IOrgService orgService = null;
        public Frm_ELEC_SPLIT_RELA_S()
        {
            bUseMVCService = true;
            InitializeComponent();
            orgService = ServiceFactory.createService<IOrgService>();
        }

        /// <summary>
        /// 显示单条数据(新增时设置默认值)
        /// </summary>
        public override void yssShowInfoAddForm()
        {
            this.yssStartDate.setDateTime(Convert.ToDateTime("1990-1-1"));
            this.yssEndDate.setDateTime(Convert.ToDateTime("9998-12-31"));
        }

        /// <summary>
        /// 显示单条数据，参数为set界面数据对应的pojo对象
        /// </summary>
        /// <param name="pojo">显示数据对应的pojo对象</param>
        public override void showInfoMVC(BasePojo pojo)
        {
            try
            {
                if(pojo != null)
                {
                    ErSplitRela rela = pojo as ErSplitRela;
                    this.txtSplitCode.Text = rela.C_SPLIT_CODE;
                    this.cboTgh.Value = rela.C_TGH_CODE;
                    this.cboTZZH.Value = rela.C_PORT_CODE;
                    this.yssStartDate.setDateTime(Convert.ToDateTime(rela.D_START_DATE));
                    this.yssEndDate.setDateTime(Convert.ToDateTime(rela.D_END_DATE));
                }

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
            ErSplitRela rela = new ErSplitRela();
            try
            {
                rela.C_PORT_CODE = this.cboTZZH.Value;
                rela.C_SPLIT_CODE = this.txtSplitCode.Text;
                rela.C_TGH_CODE = this.cboTgh.Value;
                rela.D_START_DATE = this.yssStartDate.getBeginDateStr;
                rela.D_END_DATE = this.yssEndDate.getBeginDateStr;
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }
            return rela;
        }

        /// <summary>
        /// 托管银行数据加载前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboTgh_YssOnBeforeLoadData(object sender, FAST.Core.Context.Events.YssBeforeOperEventArgs e)
        {
            if (this.cboTgh.Items.Count <= 0)
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
            }
            e.IsCancel = true;
        }
    }
}
