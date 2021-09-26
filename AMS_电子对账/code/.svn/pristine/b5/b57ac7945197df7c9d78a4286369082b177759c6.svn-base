using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Pojo;


namespace YssElecReco.Fun
{
    /// <summary>
    /// 自定义表加载
    /// </summary>
  public class ErGzbTableListLoader : YebHdTableListLoader
    {
        /// <summary>
        /// 修改列头
        /// </summary>
        /// <param name="tbMain">tbMain</param>
        protected override void updateColumnHeader(Yss.KTable.Models.Table tbMain)
        {
        }

        /// <summary>
        /// 修改行的颜色
        /// </summary>
        /// <param name="pojo">pojo</param>
        /// <param name="row">row</param>
        protected override void updateRowColor(BasePojo pojo, Yss.KTable.Models.Row row)
        {
            ////CwHd erYehd = pojo as CwHd;
            ////if (erYehd.N_AMOUNT_DIFF != 0 || erYehd.N_PORT_MONEY_DIFF != 0 || erYehd.N_ORIG_MONEY_DIFF != 0 || erYehd.C_KM_CODE.Trim().Length == 0 || erYehd.C_KM_CODE2.Trim().Length == 0)
            ////{
            ////    setRowBackColor(0, row);
            ////}
        }

        /// <summary>
        /// 是否隐藏列
        /// </summary>
        /// <param name="headKey">列头</param>
        /// <returns>是/否</returns>
        protected override bool hiddenColumn(ListHeadInfo headKey)
        {
            if (headKey.Key.Equals("C_SN") || headKey.Key.Equals("C_ASS_CODE") || headKey.Key.Equals("C_FILE_TYPE") || headKey.Key.Equals("C_RPT_TYPE") || headKey.Key.Equals("D_START_DATE") || headKey.Key.Equals("D_END_DATE") || headKey.Key.Equals("C_DV_ER_WAY"))
            {
                return true;
            }

            return false;
        }
    }
}
