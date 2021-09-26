using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.CRUD.Form;
using FAST.Core.Communication.Service;
using FAST.Core.Util;
using FAST.Core.Exceptions;
using FAST.Common.Service.Pojo.Base;
using YssElecReco.Service.Er.Reverse;
using YssElecReco.Pojo.Bi;
using YssInformation.Support.Bi.AssociationOrgan.Service;
using YssInformation.Support.Bi.AssociationOrgan.Pojo;

namespace YssElecReco.Form.Er.Reverse
{
    public partial class Frm_ELEC_REVE_ZBMAP_L : FrmBaseList
    {
        private IOrgService orgService = null;
        public Frm_ELEC_REVE_ZBMAP_L()
        {
            this.bUseMVCService = true;
            InitializeComponent();
            orgService = ServiceFactory.createService<IOrgService>();
        }

        /// <summary>
        /// 托管银行数据加载前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboTgh_YssOnBeforeLoadData(object sender, FAST.Core.Context.Events.YssBeforeOperEventArgs e)
        {
            e.IsCancel = true;
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

        /// <summary>
        /// 封装前台查询条件
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>paraDict</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            try
            {
                string search = this.yssBuildLeftCheckRowsStr("portfolio");
                search = search.Replace("'", "");
                //搜索条件为空，查找出所有数据
                if (null != search && !"".Equals(search.Trim()))
                {
                    paraDict.Add("ARRAY_C_PORT_CODE_OR_NULL", search);
                }

                //托管机构
                if (this.cboTgh.Value != null && !"".Equals(this.cboTgh.Value))
                {
                    paraDict.Add("ARRAY_C_TGH_CODE_OR_NULL", this.cboTgh.Value);
                }
                //对账类型
                if (this.cboDzType.Value != null && !"".Equals(this.cboDzType.Value))
                {
                    paraDict.Add("C_FILE_TYPE", this.cboDzType.Value);
                }
               
                //内部指标代码
                if (this.cboZbCode.Value != null && !"".Equals(this.cboZbCode.Value))
                {
                    paraDict.Add("C_ZB_CODE", this.cboZbCode.Value);
                }
                //外部指标代码
                if (this.txtOutZBCode.Text != null && !"".Equals(this.txtOutZBCode.Text.Trim()))
                {
                    paraDict.Add("C_ZB_CODE_OUT", "%" + this.txtOutZBCode.Text.Trim() + "%");
                }

            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500037", _formFun, status));
                ClsBaseException.DiscardException(ex);
            }
            return paraDict;
        }

        /// <summary>
        /// 指标项与对账类型级联
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboZbCode_YssOnBeforeLoadData(object sender, FAST.Core.Context.Events.YssBeforeOperEventArgs e)
        {
            if (this.cboZbCode.Items.Count <= 0)
            {
                Dictionary<string, ElecRela> dict = (this.dataService as IZbMapService).getZbItems("1011");//目前只有估值表

                foreach (KeyValuePair<string, ElecRela> kv in dict)
                {
                    Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(kv.Value);
                    e.Collection.Add(entity);
                }
            }
            ////指定控件不要自动去加载数据
            e.IsCancel = true;
        }

    }
}
