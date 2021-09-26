﻿using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Communication.Service;
using FAST.Core.Util;
using FAST.Core.Exceptions;
using YssInformation.Support.Bi.AssociationOrgan.Service;
using YssInformation.Support.Bi.AssociationOrgan.Pojo;

namespace YssElecReco.Form.Er.Reverse
{
    public partial class Frm_ELEC_REVE_ASSMAP_L : FrmBaseList
    {
        private IOrgService orgService = null;
        public Frm_ELEC_REVE_ASSMAP_L()
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
                paraDict.Add("ARRAY_C_PORT_CODE_OR_NULL", search);

                if (this.cboDZMS.Value != null && !"".Equals(this.cboDZMS.Value))
                {
                    paraDict.Add("C_DV_DZ_MODE", this.cboDZMS.Value);
                }

                if (this.cboDzType.Value != null && !"".Equals(this.cboDzType.Value))
                {
                    paraDict.Add("C_FILE_TYPE", this.cboDzType.Value);
                }

                if (this.cboTgh.Value != null && !"".Equals(this.cboTgh.Value))
                {
                    paraDict.Add("C_TGH_CODE", this.cboTgh.Value);
                }
                
                if (this.txtTGHZCDM.Text != null && !"".Equals(this.txtTGHZCDM.Text.Trim()))
                {
                    paraDict.Add("C_PORT_CODE_OUT", "%" + this.txtTGHZCDM.Text.Trim() + "%");
                }
               
            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500037", _formFun, status));
                ClsBaseException.DiscardException(ex);
            }

            return paraDict;
        }

    }
}
