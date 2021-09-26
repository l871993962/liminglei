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
using FAST.Core.Communication.Service;
using YssElecReco.Fun;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;
using FAST.Common.Service.DataService;
using FAST.Common.Service.Pojo;
using YssElecReco.Service.Er.Reverse;
using System.Collections;
using YssInformation.Support.Bi.AssociationOrgan.Service;
using YssInformation.Support.Bi.AssociationOrgan.Pojo;

namespace YssElecReco.Form.Er.Reverse
{
    public partial class Frm_ELEC_REVE_IGNOREITEM_S : FrmBaseSet
    {
        private IElecTghRelaService tghRelaService = null;
        private IElecPortRelaService portRelaService = null;
        private IOrgService orgService = null;
        public Frm_ELEC_REVE_IGNOREITEM_S()
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
                IgnoreItem pojo = this.frmBaseViewList.tbMain.SelectedRow.Tag as IgnoreItem;
                this.cboDzType.Value = pojo.C_FILE_TYPE;
                this.cboTgh.Value = pojo.C_TGH_CODE;
                this.cboTZZH.Value = pojo.C_PORT_CODE;
                this.cboIgnoreScope.Value = pojo.C_DV_IGNORE_SCOPE;
                this.cboIgnoreType.Value = pojo.C_DV_IGNORE_TYPE;
                this.cboSubSuit.Value = pojo.C_DV_SUB_SUIT;
                this.cboColFlag.Value = pojo.C_COL_FLAG;
                this.txtRowFlag.Text = pojo.C_ROW_FLAG;
            }
            catch (Exception e)
            {
                throw new ClsBaseException(e.Message);
            }

        }

        /// <summary>
        /// 显示单条数据
        /// </summary>
        public override void yssShowInfoAddForm()
        {
            try
            {
                //适用下级，默认为否，不适用
                this.cboSubSuit.Value = ReveElecDVCons.IGNORE_SUB_SUIT_NO;

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
                IgnoreItem pojo = new IgnoreItem();
                pojo.C_PORT_CODE = this.cboTZZH.Value == null ? "" : this.cboTZZH.Value;
                pojo.C_FILE_TYPE = this.cboDzType.Value == null ? "" : this.cboDzType.Value;
                pojo.C_TGH_CODE = this.cboTgh.Value == null ? "" : this.cboTgh.Value;
                pojo.C_DV_SUB_SUIT = this.cboSubSuit.Value == null ? "" : this.cboSubSuit.Value;
                pojo.C_DV_IGNORE_SCOPE = this.cboIgnoreScope.Value == null ? "" : this.cboIgnoreScope.Value;
                pojo.C_DV_IGNORE_TYPE = this.cboIgnoreType.Value == null ? "" : this.cboIgnoreType.Value;
                pojo.C_ROW_FLAG = this.txtRowFlag.Text == null ? "" : this.txtRowFlag.Text.Trim();
                pojo.C_COL_FLAG = this.cboColFlag.Value == null ? "" : this.cboColFlag.Value;
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
        /// 忽略类型与行标识，列标识的级联
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void cboIgnoreType_SelectedValueChanged(object sender, EventArgs e)
        {
          
            if(null != this.cboIgnoreType.Value&&!"".Equals(this.cboIgnoreType.Value.Trim()))
            {
                //行标识在忽略类型=“行忽略”和“单元格忽略”时为必输项
                //列标识在忽略类型=“列忽略”和“单元格忽略”时为必输项
                //单元格忽略
                if(ReveElecDVCons.IGNORE_TYPE_HL_CELL.Equals(this.cboIgnoreType.Value))
                {
                    this.cboColFlag.YssIsMust = true;
                    this.txtRowFlag.YssIsMust = true;
                }
                //行忽略
                if (ReveElecDVCons.IGNORE_TYPE_HL_ROW.Equals(this.cboIgnoreType.Value))
                {
                    this.cboColFlag.YssIsMust = false;
                    this.txtRowFlag.YssIsMust = true;
                }
                //列忽略
                if (ReveElecDVCons.IGNORE_TYPE_HL_COL.Equals(this.cboIgnoreType.Value))
                {
                    this.cboColFlag.YssIsMust = true;
                    this.txtRowFlag.YssIsMust = false;
                }
            }
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

        private void cboColFlag_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            this.cboColFlag.MethodInfo.MethodName = "getDataListByTypes";
            string gradeValue = "";
            if (ReveElecDVCons.DZ_TYPE_GZB.Equals(this.cboDzType.Value))//估值表
            {
                gradeValue = "REVE_GZ_HDX";
            }
            else if (ReveElecDVCons.DZ_TYPE_YEB.Equals(this.cboDzType.Value))//余额表
            {
                gradeValue = "REVE_YE_HDX";
            }

            this.cboColFlag.MethodInfo.MethodParamValues = new string[] { gradeValue + "," };
        }

        private void cboDzType_SelectedValueChanged(object sender, EventArgs e)
        {
            this.cboColFlag.Items.Clear();
        }
    }
}
