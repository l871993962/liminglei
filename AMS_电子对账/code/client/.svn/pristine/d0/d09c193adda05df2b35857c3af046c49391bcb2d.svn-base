using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.CRUD.Form;
using FAST.Core.Util;
using FAST.Core.Exceptions;
using YssElecReco.Fun;
using FAST.Common.Service.Pojo;
using Yss.KTable.Models;
using YssElecReco.Pojo.Er.Reverse;
using System.Collections;

namespace YssElecReco.Form.Er.Reverse
{
    public partial class Frm_ELEC_REVE_TGH_KMMAP_L : FrmBaseList
    {
        public Frm_ELEC_REVE_TGH_KMMAP_L()
        {
            this.bUseMVCService = true;
            InitializeComponent();
        }

        /// <summary>
        /// 获取多项选择项的方法，此方法只适用于Checkbox的多选形式
        /// 
        /// MVC架构专用
        /// </summary>
        /// <returns>选择行的数据对象</returns>
        public override ArrayList getSelectTypeItemListAuditable()
        {
            ArrayList list = new ArrayList();
            //// 使用循环获取curView中选中项目，并添加到数组中
            //// 当启用checkbox功能时，获取选中行的处理
            if (bShowRowCheckBoxColumn)
            {
                foreach (Row row in tbMain.CheckedRows)
                {
                    // add by yh 2011.03.13 增加在获取list界面选中行时，去掉分组行数据的判断
                    if (row.IsGroup != true)
                    {
                        if (row.Tag is KmRelaRecord)
                        {
                            KmRelaRecord krr = row.Tag as KmRelaRecord;
                            list.Add(convertToDataPojo(krr));
                        }
                    }
                }
            }
            else
            {  // 当未启用checkBox功能时，获取选中行的处理

                foreach (Row row in tbMain.SelectedRows)
                {
                    if (row.IsGroup != true)
                    {
                        if (row.Tag is KmRelaRecord)
                        {
                            KmRelaRecord krr = row.Tag as KmRelaRecord;
                            list.Add(convertToDataPojo(krr));
                        }
                    }
                }
            }

            return list;
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
                string search = this.yssBuildLeftCheckRowsStr("organ");
                search = search.Replace("'", "");
                //if (null != search && !"".Equals(search.Trim()))
                //{
                //    paraDict.Add("ARRAY_C_TGH_CODE", search);
                //}
                paraDict.Add("ARRAY_C_TGH_CODE", search);
                paraDict.Add("C_DV_MAP_SCOPE", "REVE_YSFW_TGFYS");
                if (this.txtInnerKmCode.Text != null && !"".Equals(this.txtInnerKmCode.Text.Trim()))
                {
                    paraDict.Add("C_KM_CODE", "%" + this.txtInnerKmCode.Text.Trim() + "%");
                }

                if (this.txtInnerKmName.Text != null && !"".Equals(this.txtInnerKmName.Text.Trim()))
                {
                    paraDict.Add("C_KM_NAME", "%" + this.txtInnerKmName.Text.Trim() + "%");
                }

                if (this.txtOutKmCode.Text != null && !"".Equals(this.txtOutKmCode.Text.Trim()))
                {
                    paraDict.Add("C_KM_CODE_OUT", "%" + this.txtOutKmCode.Text.Trim() + "%");
                }

                if (this.txtOutKmName.Text != null && !"".Equals(this.txtOutKmName.Text.Trim()))
                {
                    paraDict.Add("C_KM_NAME_OUT", "%" + this.txtOutKmName.Text.Trim() + "%");
                }
                if (this.cboKmCls.Value != null && !"".Equals(this.cboKmCls.Value))
                {
                    paraDict.Add("C_DV_KM_CLS", this.cboKmCls.Value);
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
        /// 加载数据表格内容
        /// BUG187342【深国投】增值税台账明细表有多页，导出所有页，客户端会闪退。张绍林-20180209
        /// </summary>
        /// <param name="res">查询结果对象</param>
        /// <param name="tableSource">待装载数据的表格</param>
        protected override void loadListContentMVC(QueryRes res, Table tableSource)
        {
            ReveDzTableListLoader listLoader = new ReveDzTableListLoader();
            listLoader.FunCode = this.YssFormMenu.C_FUN_CODE;
            listLoader.AutoLoadEnclosure = this.AutoLoadEnclosure;
            listLoader.loadTable(tableSource, res, bShowRowCheckBoxColumn, bShowRowIndexColumn, YssMainKTableShowMode);

            ////读取用户自定义列配置信息
            this.ReadTableColumnsFromConfig(tableSource, this.YssFormMenu.C_FUN_NAME);

            ////读取分组列信息。张绍林-20151124
            this.ReadTableGroupColumnFromConfig(tableSource, this.YssFormMenu.C_FUN_NAME);

            ////读取用户自定义列宽信息。张绍林-20151201
            this.ReadColumnWidthFromConfig(tableSource, this.YssFormMenu.C_FUN_NAME);
        }
    }
}
