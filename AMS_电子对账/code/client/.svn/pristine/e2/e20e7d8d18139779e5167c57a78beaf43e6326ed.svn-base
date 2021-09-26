using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using YssElecReco.Service.Er;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Core.Communication.Service;
using FAST.Core.Exceptions;

namespace YssElecReco.Form.Er
{
    /// <summary>
    /// 对账结果浏览界面
    /// </summary>
    public partial class Frm_ELEC_RESULT_L : FrmBaseList
    {
        /// <summary>
        /// 声明service对象
        /// </summary>
        private IErResultService resultService = null;

        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_ELEC_RESULT_L()
        {
            InitializeComponent();
            this.bUseMVCService = true;
        }

        /// <summary>
        /// 对账结果浏览界面
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ELEC_RESULT_L_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.resultService = ServiceFactory.createService(serviceType) as IErResultService;
            this.dataService = this.resultService;
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

                ////if (!this.dtpDzDate.getBeginDateStr.Equals("0001-01-01"))
                ////{
                ////    paraDict.Add("D_TRADE_START", this.dtpDzDate.getBeginDateStr);
                ////}

                ////if (this.dtpDzDate.YssShowSecond && !this.dtpDzDate.getEndDateStr.Equals("0001-01-01"))
                ////{
                ////    paraDict.Add("D_TRADE_END", this.dtpDzDate.getEndDateStr);
                ////}

                paraDict.Add("D_TRADE_START", this.dtpDzDate.getBeginDateStr);

                if (this.dtpDzDate.YssShowSecond)
                {
                    paraDict.Add("D_TRADE_END", this.dtpDzDate.getEndDateStr);
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
        /// 重写选中list区数据，工具栏按钮状态改变事件
        /// </summary>
        /// <param name="pojo">pojo</param>
        protected override void setButtonStaAfterTbMainClickMVC(BasePojo pojo)
        {
            return;
        }


    }
}
