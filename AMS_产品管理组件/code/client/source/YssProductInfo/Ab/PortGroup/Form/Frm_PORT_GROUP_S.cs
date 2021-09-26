using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;

using FAST.Core.Communication.DataService;
using FAST.Common.Service.DataService.Base;
using FAST.Core.Exceptions;

using FAST.Core.Context;


using FAST.Platform.ElectronicSeal.Form;
using FAST.Core.BaseForm;
using FAST.Core.Enclosure.Form;

using FAST.Core.Bussiness.Form;

using FAST.Common.Service.Pojo.Base;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.BaseControl;
using Yss.KTable.Models;
using Yss.KTable.Collections;
using Yss.KRichEx.AutoFilter.Model;
using YssProductInfo.Support.Ab.PortGroup.Service;
using YssProductInfo.Support.Ab.PortGroup.Pojo;


namespace YssProductInfo.Ab.PortGroup.Form
{
    /// <summary>
    /// 11
    /// </summary>
    public partial class Frm_PORT_GROUP_S : GroupTextBoxPopup
    {
        /// <summary>
        /// list
        /// </summary>
        private Frm_PORT_GROUP_L frmList = null;

        /// <summary>
        /// service
        /// </summary>
        private IPortGroupRelaService groupRelaService = null;

        /// <summary>
        /// 11
        /// </summary>
        public Frm_PORT_GROUP_S()
        {
            InitializeComponent();
            Yss.Controls.TabControl tabCtrlMain = this.Controls["tabCtrlMain"] as Yss.Controls.TabControl;
            tabCtrlMain.TabPages.RemoveAt(1);
            this.ShowCheckBox = true;
            this.ShowColumnHeader = true;
            ////this.Parameter = "C_PORT_NAME_ST;组合简称~" + Parameter + "~C_ASS_CODE;资产代码~C_DAT_CLS;资产类别";
            this.TableTypes.AllowResizeColumn = true;
            groupRelaService = ServiceFactory.createService<IPortGroupRelaService>();
            ////禁止用户将此窗口最小化。张智坤-20160227
            this.MaximizeBox = false;
        }

        /// <summary>
        /// 设置list窗体
        /// </summary>
        public Frm_PORT_GROUP_L FrmList
        {
            set
            {
                this.frmList = value;
            }

            get
            {
                return frmList;
            }
        }

        /// <summary>
        /// 装载数据
        /// </summary>
        protected override void OnLoadData()
        {
            this.TypeItems.Clear();
            ////装载产品组合
            this.LoadTypeItems();
            ////liuxiang 2016-2-17 设置可见性为true,只有窗体可见时才会重新构建KTable控件数据 BUG #126514 产品群组设置加载组合问题
            this.Visible = true;
        }

        /// <summary>
        /// 装载产品组合
        /// </summary>
        protected override void LoadTypeItems()
        {
            ////IPortDataService portDataService = DataServiceFactory.createService<IPortDataService>();
            ////List<BasePojo> pojoList = portDataService.doPortFilter("true", "", "", "");
            ////List<string> portList = getPortCodeList(); 
            YssProductInfo.Support.Ab.PortGroup.Pojo.PortGroup portGroup = FrmList.tbLeftMain.SelectedRow.Tag as YssProductInfo.Support.Ab.PortGroup.Pojo.PortGroup;
            List<FAST.Common.Service.Pojo.Port> pojoList = groupRelaService.querySelectPortCode(portGroup.C_GROUP_CODE);
            foreach (FAST.Common.Service.Pojo.Port pojo in pojoList)
            {
                this.TypeItems.Add(new KTableEntity(pojo));
            }
        }

        /// <summary>
        /// 11
        /// </summary>
        /// <returns>11</returns>
        private List<string> getPortCodeList() 
        {
             List<string> list = new List<string>();

             foreach (Row row in frmList.tbMain.Rows)
             {
                 PortGroupRela portGroup = row.Tag as PortGroupRela;
                 list.Add(portGroup.C_PORT_CODE);
             }

             return list;
        }

    }
}


