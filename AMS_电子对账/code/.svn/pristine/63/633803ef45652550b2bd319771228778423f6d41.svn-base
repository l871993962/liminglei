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
using FAST.Common.Service.Pojo;
using FAST.Core.Util;
using FAST.Core.Exceptions;
using FAST.Common.Service.Pojo.Base;
using YssInformation.Support.Bi.AssociationOrgan.Service;
using YssInformation.Support.Bi.AssociationOrgan.Pojo;

namespace YssElecReco.Form.Er
{
    public partial class Frm_ELEC_SPLIT_RELA_L : FrmBaseListWithDetails
    {
        private IOrgService orgService = null;
        public Frm_ELEC_SPLIT_RELA_L()
        {
            this.bUseMVCService = true;
            InitializeComponent();
            orgService = ServiceFactory.createService<IOrgService>();
        }

        /// <summary>
        /// 装载明细窗体功能代码列表
        /// </summary>
        /// <returns>返回明细窗体功能代码列表</returns>
        protected override List<SysFun> LoadDetailFormFuns()
        {
            List<SysFun> sysFuns = new List<SysFun>();
            SysFun newFun1 = new SysFun();
            newFun1.C_FUN_CODE = "erSplitRule";
            newFun1.C_FUN_NAME = "";
            sysFuns.Add(newFun1);
            return sysFuns;
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
                paraDict.Add("ARRAY_C_PORT_CODE", search);
                if (this.cboTgh.Value != null && !"".Equals(this.cboTgh.Value))
                {
                    paraDict.Add("C_TGH_CODE", this.cboTgh.Value);
                }
                if (this.cboShowType.Value != null && !"".Equals(this.cboShowType.Value))
                {
                    paraDict.Add("C_SHOW_TYPE", this.cboShowType.Value);
                }
                if (this.txtSplitCode.Text != null && !"".Equals(this.txtSplitCode.Text.Trim()))
                {
                    paraDict.Add("C_SPLIT_CODE", "%"+this.txtSplitCode.Text.Trim()+"%");
                }
                if (this.yssStartDate.getBeginDateStr != null && !"".Equals(this.yssStartDate.getBeginDateStr))
                {
                    paraDict.Add("D_START_DATE", this.yssStartDate.getBeginDateStr);
                    paraDict.Add("D_END_DATE", this.yssStartDate.getBeginDateStr);
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

        /// <summary>
        /// 显示数据数据加载前事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void cboShowType_YssOnBeforeLoadData(object sender, FAST.Core.Context.Events.YssBeforeOperEventArgs e)
        {
            if (this.cboShowType.Items.Count <= 0)
            {
                cboShowType.Items.Clear();
                Yss.KRichEx.AutoFilter.Model.KTableEntity item1 = new Yss.KRichEx.AutoFilter.Model.KTableEntity("全部", "ALL");
                Yss.KRichEx.AutoFilter.Model.KTableEntity item2 = new Yss.KRichEx.AutoFilter.Model.KTableEntity("忽略", "IGNORE");
                Yss.KRichEx.AutoFilter.Model.KTableEntity item3 = new Yss.KRichEx.AutoFilter.Model.KTableEntity("拆分", "SPLIT");
                this.cboShowType.Items.Add(item1);
                this.cboShowType.Items.Add(item2);
                this.cboShowType.Items.Add(item3);
            }
            e.IsCancel = true;
        }
    }
}
