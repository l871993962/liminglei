using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.CRUD.Form;
using FAST.Core.Context;
using FAST.Core.Util;
using FAST.Core.BaseControl.Pojo;
using Yss.KRichEx.AutoFilter.Events;
using YssInformation.Support.Sys.PortBusinessRange.Service;
using Yss.KRichEx.AutoFilter.Model;
using FAST.Core.Communication.Service;
using FAST.Common.Service.Dict.Pojo;

namespace YssInformation.Sys.PortBusinessRange.Form
{
    /// <summary>
    /// Frm_PortBusinessRange_L
    /// </summary>
    public partial class Frm_PortBusinessRange_L : FrmBaseList
    {
        /// <summary>
        /// 构造器
        /// </summary>
        public Frm_PortBusinessRange_L()
        {
            this.bUseMVCService = true;
            this.bUseMVCServiceLeft = true;
            InitializeComponent();
        }

        /// <summary>
        /// 重写A区配置信息，A区不显示复选框。
        /// </summary>
        public override AreaAConfigInfo AreaAConfigInfo
        {
            get
            {
                AreaAConfigInfo areaAConfigInfo = new AreaAConfigInfo();
                areaAConfigInfo.ShowCheckBox = true;
                areaAConfigInfo.ShowPageGroups = false;
                areaAConfigInfo.ShowPortInCommonUse = false;
                return areaAConfigInfo;
            }
        }

        /// <summary>
        /// 重写窗体load事件，添加设置按钮
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void Frm_PortBusinessRange_Load(object sender, EventArgs e)
        {
            this.addButton();
        }

        /// <summary>
        /// 添加设置按钮，STORY #82160 【华宝基金】产品业务范围增加维护界面
        /// </summary>
        private void addButton()
        {
            ClsButtonInfo btnConfigSet = new ClsButtonInfo();
            btnConfigSet.Name = "btnConfigSetNew";
            btnConfigSet.Text = "设置";
            btnConfigSet.Tooltip = "设置";
            btnConfigSet.Image = FAST.Resource.Resource.btnConfigSet;
            btnConfigSet.ClickEvent += new System.EventHandler(this.btnConfigSetNew_Click);
            this.btnBar.addButton(btnConfigSet, 20);
            this.btnBar.setButtonEnabled(btnConfigSet.Name, true);
        }

        /// <summary>
        /// 设置按钮点击事件
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void btnConfigSetNew_Click(object sender, EventArgs e)
        {
            Frm_PortBusinessTypeSet_S form = new Frm_PortBusinessTypeSet_S();
            form.FormBaseListView = this;
            form.initForm(this);
            form.showInfoMVC(null);
            form.ShowDialog();
        }

        /// <summary>
        /// 业务类型下拉框取值
        /// </summary>
        /// <param name="sender">sender</param>
        /// <param name="e">e</param>
        private void selBusinessType_BeforeDropDownClick(object sender, DropDownEventArgs e)
        {
            e.Cancel = true;
            this.selBusinessType.Items.Clear();
            IPortBusinessRangeService portBusinessService = ServiceFactory.createService<IPortBusinessRangeService>();
            List<Vocabulary> businessTypeVoc = portBusinessService.getDataListByType("AO_AUTO_BUSINESS");
            foreach (Vocabulary voc in businessTypeVoc)
            {
                KTableEntity entity = new KTableEntity(voc.C_DV_NAME, voc.C_DV_CODE);
                this.selBusinessType.Items.Add(entity);
            }
        }

        /// <summary>
        /// OnGetParaAssemble
        /// </summary>
        /// <param name="paraDict">paraDict</param>
        /// <returns>Dictionary</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {
            if (null != this.selBusinessType.Value && this.selBusinessType.Value.Trim().Length != 0)
            {
                paraDict.Add("ARRAY_C_BUSINESS_CODE", selBusinessType.Value.Replace("|", ","));
            }

            string search = yssBuildLeftCheckRowsStr("portfolio");
            search = search.Replace("'", "");
            if (!string.IsNullOrEmpty(search) && !"".Equals(search))
            {
                paraDict.Add("ARRAY_C_PORT_CODE", search);
            }
            
            return paraDict;
        }
    }
}
