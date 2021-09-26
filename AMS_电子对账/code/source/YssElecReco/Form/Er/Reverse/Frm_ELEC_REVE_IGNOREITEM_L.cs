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
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Core.Exceptions;
using YssElecReco.Fun;
using YssInformation.Support.Bi.AssociationOrgan.Service;
using YssInformation.Support.Bi.AssociationOrgan.Pojo;

namespace YssElecReco.Form.Er.Reverse
{
    public partial class Frm_ELEC_REVE_IGNOREITEM_L : FrmBaseList
    {
        private IOrgService orgService = null;
        public Frm_ELEC_REVE_IGNOREITEM_L()
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
                //忽略类型
                if (this.cboIgnoreType.Value != null && !"".Equals(this.cboIgnoreType.Value))
                {
                    paraDict.Add("C_DV_IGNORE_TYPE", this.cboIgnoreType.Value);
                }
                //行标识
                if (this.txtRowFlag.Text != null && !"".Equals(this.txtRowFlag.Text.Trim()))
                {
                    paraDict.Add("C_ROW_FLAG", "%"+this.txtRowFlag.Text.Trim()+"%");
                }
                //列标识
                if (this.cboColFlag.Value != null && !"".Equals(this.cboColFlag.Value))
                {
                    paraDict.Add("C_COL_FLAG", this.cboColFlag.Value);
                }

            }
            catch (Exception ex)
            {
                YssMessageBox.ShowCommonInfo(ClsRetInfoDealer.getCommonError("ERR-500037", _formFun, status));
                ClsBaseException.DiscardException(ex);
            }

            return paraDict;
        }

        private void cboColFlag_BeforeDropDownClick(object sender, Yss.KRichEx.AutoFilter.Events.DropDownEventArgs e)
        {
            this.cboColFlag.Items.Clear();
            this.cboColFlag.MethodInfo.MethodName = "getDataListByTypes";
            string gradeValue = "";
            string dzType = this.cboDzType.Value; 
            //STORY58653阳光资产--反向对账业务
            //级联对账类型下拉框
            if(dzType != null && !"".Equals(dzType.Trim()))
            {
                ////目前只有估值表,余额表
                if(dzType.Equals(ReveElecDVCons.DZ_TYPE_GZB))
                {
                    gradeValue = "REVE_GZ_HDX";
                }
                else if (dzType.Equals(ReveElecDVCons.DZ_TYPE_YEB))
                {
                    gradeValue = "REVE_YE_HDX";
                }
            }else
            {
                gradeValue = "REVE_GZ_HDX,REVE_YE_HDX";//目前只有估值表,余额表
            }
            
            this.cboColFlag.MethodInfo.MethodParamValues = new string[] { gradeValue + "," };
        }
        
    }
}
