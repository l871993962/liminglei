using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.ElecSeal.Form;
using FAST.Platform.ElectronicSeal.Pojo;
using FAST.Common.Service.Pojo;

namespace YssProductInfo.Cp.Fax.Form.Form
{
    /// <summary>
    /// 组合关联签章文件List界面
    /// ClassName:  FrmPortSealRela_L 
    /// Author:     leijianhua@ysstech.com
    /// CreateDate: 2017/8/20 11:25:10
    /// Copyright:  (c) 2001-2017, 深圳赢时胜 All Rights Reserved.
    /// </summary>
    public partial class FrmPortSealRela_L : FrmBaseSealRela_L
    {
        /// <summary>
        /// 构造函数
        /// </summary>
        public FrmPortSealRela_L()
        {
            InitializeComponent();
        }

        /// <summary>
        /// 目标类型是PortSealRelaInfo
        /// </summary>
        /// <returns>Type</returns>
        public override Type getTargetType()
        {
            return typeof(PortSealRelaInfo);
        }

        /// <summary>
        /// 扩展列头信息，将组合信息放置到功能代码之后
        /// </summary>
        /// <param name="baseListHeadList">baseListHeadList</param>
        /// <returns>扩展后的列头信息</returns>
        protected override List<FAST.Common.Service.Pojo.ListHeadInfo> getListHeadList(List<FAST.Common.Service.Pojo.ListHeadInfo> baseListHeadList)
        {
            int index = 0;
            ListHeadInfo funInfo = null;
            for (int i = 0; i < baseListHeadList.Count; i++)
            {
                ListHeadInfo listInfo = baseListHeadList[i];
                if (listInfo.Key == "C_FUN_CODE")
                {
                    index = i + 1;
                    funInfo = listInfo;
                    break;
                }
            }

            List<ListHeadInfo> endList = baseListHeadList.GetRange(index, baseListHeadList.Count - index);
            baseListHeadList.RemoveRange(index, baseListHeadList.Count - index);
            ////if (funInfo != null)
            ////{
            ////    baseListHeadList.Remove(funInfo);
            ////}

            ListHeadInfo info = new ListHeadInfo();
            info.Key = "C_PORT_CODE";
            info.Text = "投资组合";
            info.Align = "L";
            info.ServiceId = "IPortDataService";
            baseListHeadList.Add(info);

            ListHeadInfo info2 = new ListHeadInfo();
            info2.Key = "C_ASSETS_CODE";
            info2.Text = "资产种类";
            info2.Align = "L";
            info2.ServiceId = "IVocDataService";
            baseListHeadList.Add(info2);
            baseListHeadList.AddRange(endList);

            return baseListHeadList;
        }

        /// <summary>
        /// 双击事件
        /// </summary>
        /// <param name="sender">1</param>
        /// <param name="e">1</param>
        protected override void tbMain_RowDoubleClicked(object sender, Yss.KTable.Events.RowEventArgs e)
        {
            try
            {
                this.sDllName = "YssProductInfo.dll";
                this.sSetClassName = "YssProductInfo.Cp.Fax.Form.Form.FrmPortSealRela_S";
                base.tbMain_RowDoubleClicked(sender, e);
            }
            catch (Exception ex)
            {
                FAST.Core.Util.YssMessageBox.ShowCommonInfo(
                    FAST.Core.Util.ClsRetInfoDealer.getCommonError("ERR-110001", this._formFun, this.status, ex));
            }
        }

        /// <summary>
        /// 修改事件
        /// </summary>
        /// <param name="sender">1</param>
        /// <param name="e">1</param>
        protected override void btnEdit_Click(object sender, EventArgs e)
        {
            try
            {
                this.sDllName = "YssProductInfo.dll";
                this.sSetClassName = "YssProductInfo.Cp.Fax.Form.Form.FrmPortSealRela_S";
                base.btnEdit_Click(sender, e);
            }
            catch (Exception ex)
            {
                FAST.Core.Util.YssMessageBox.ShowCommonInfo(ex.Message, this._formFun, this.status);
            }
        }

        /// <summary>
        /// getExtParaDict
        /// </summary>
        /// <returns>Dictionary</returns>
        public override Dictionary<string, string> getExtParaDict()
        {
            Dictionary<string, string> paraDict = new Dictionary<string, string>();
            string search = this.yssBuildLeftCheckRowsStr("portfolio");
            search = search.Replace("'", "");
            paraDict.Add("ARRAY_C_PORT_CODE", search);
            return paraDict;
        }
    }
}
