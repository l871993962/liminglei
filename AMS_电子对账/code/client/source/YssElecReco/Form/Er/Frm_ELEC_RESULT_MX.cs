using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;
using FAST.Core.Context;
using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;
using FAST.Core.Bussiness.Form;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using YssElecReco.Service.Er;
using YssElecReco.Pojo.Er;
using System.Text.RegularExpressions;

namespace YssElecReco.Form.Er
{
    /// <summary>
    /// 对账结果浏览界面
    /// </summary>
    public partial class Frm_ELEC_RESULT_MX : FrmBaseSet
    {
        /// <summary>
        /// 声明service对象
        /// </summary>
        private IErResultService resultService = null;
        
        /// <summary>
        /// 构造函数
        /// </summary>
        public Frm_ELEC_RESULT_MX()
        {
            InitializeComponent();
            this.bUseMVCService = true;
        }

        /// <summary>
        /// 电子对账明细结果浏览界面
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_ELEC_RESULT_MX_Load(object sender, EventArgs e)
        {
            Type serviceType = ReflectBase.YssGetType(_formFun.YssAssocia.ServiceDllName, _formFun.YssAssocia.ServiceName);
            this.resultService = ServiceFactory.createService(serviceType) as IErResultService;
            this.dataService = this.resultService;
        }

        /// <summary>
        /// 重写设置界面工具栏按钮状态控制基类
        /// </summary>
        protected override void YssInitTopButtonStat()
        {
            return;
        }

        /// <summary>
        /// 展示对象到窗体
        /// </summary>
        /// <param name="pojo">pojo</param>
        public override void showInfoMVC(FAST.Common.Service.Pojo.Base.BasePojo pojo)
        {
            try
            {
                ErResult result = (ErResult)this.yssGetBaseSelTypeItemMVC();
                StringBuilder resbuf = new StringBuilder();
                if (result == null)
                {
                    return;
                }

                resbuf.Append("******************** 详细对账结果 ********************").Append("<br>");
                resbuf.Append("比对结果： ").Append(parseResult(result.C_RESULT));
                this.webBrowser.DocumentText = resbuf.ToString();
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }
        }

        /// <summary>
        /// 解析对账结果
        /// </summary>
        /// <param name="result">result</param>
        /// <returns></returns>
        private string parseResult(string result)
        {
            string showResult = "";
            
            try
            {
                if (result.Trim().Length > 0 && result.Trim().Equals("一致"))
                {
                    showResult = result;
                }
                else
                {
                    Regex r = new Regex("\\[([^\\[]+?)]"); // 定义一个Regex对象实例
                    MatchCollection mc = r.Matches(result);

                    if (mc.Count > 0)
                    {
                        for (int i = 0; i < mc.Count; i++)
                        {
                            showResult += mc[i].Groups[1].Value + "<br>";
                        }

                        if (showResult.Length > 0)
                        {
                            showResult = showResult.Substring(0, showResult.Length - 4);
                        }
                    }
                }
                
            }
            catch (Exception ex)
            {
                throw new ClsBaseException(ex.Message);
            }

            return showResult;

        }
    }
}
