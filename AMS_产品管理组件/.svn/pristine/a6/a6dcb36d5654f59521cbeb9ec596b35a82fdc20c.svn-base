using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Core.CRUD.Form;
using FAST.Common.Service.Services.Base;
using FAST.Core.Communication.Service;
using FAST.Platform.Workflow.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Communication.DataService;
using FAST.Common.Service.DataService.Base;
using FAST.Core.Exceptions;
using FAST.Core.Context;
using FAST.Platform.Right.Fun;
using FAST.Core.BaseControl.Fun;
using FAST.Core.Resource;
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
using System.Collections;
using Yss.KTable.Models;
using YssProductInfo.Support.Ab.Product_Info.Pojo;
using FAST.Core.CRUD.Interface;
using FAST.Common.Service.DataService;

namespace YssProductInfo.Ab.ProductInFo.Form
{
    public partial class Frm_PORT_MESS_L : FrmBaseList
    {
        public Frm_PORT_MESS_L()
        {
            InitializeComponent();
            this.bUseMVCService = true;
            this.ShowFilterPanel = true;
        }

        private void tbFilter_SelectionChanged(object sender, Yss.KTable.Events.RowEventArgs e)
        {

        }



        /// <summary>
        /// 封装条件对象
        /// </summary>
        /// <param name="paraDict">条件对象</param>
        /// <returns>条件对象</returns>
        protected override Dictionary<string, string> OnGetParaAssemble(Dictionary<string, string> paraDict)
        {

            ////STORY #33096 【招商基金】【QDII】紧急-彭博证券信息优化
            string search = this.yssBuildLeftCheckRowsStr("portfolio");  // tanwenjie 2011.7.28 获取A区选中的行
            search = search.Replace("'", "");

            if (search != "")
            {
                paraDict.Add("ARRAY_C_PORT_CODE", search);
            }

            paraDict.Add("D_BEGIN", this.dtBusinessDate.getBeginDate.ToString("yyyy-MM-dd").Trim());

            if (this.txtPortCode.Text.Trim().Length != 0)
            {
                paraDict.Add("C_PORT_CODE", "%" + this.txtPortCode.Text.Trim() + "%");
            }

           
            ///paraDict.Add("D_SOLDBACK_END", this.dtpSoldBackDate.getEndDate.Date.ToString("yyyy-MM-dd").Trim());

            return paraDict;
        }

    }
}
