using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Common.Service.Pojo.Base;
using FAST.Common.Service.Services.Base;
using Yss.KMessage;
using Yss.KTable.Models;
using System.Xml;
using System.Windows.Forms;
using System.Drawing;
using FAST.Core.Communication.Service;
using FAST.Core.Util;
using FAST.Core.Exceptions;
using FAST.Common.Service.DataService;
using FAST.Common.Service.Pojo;
using FAST.Core.Communication.DataService;
using FAST.Core.Context.Events;
using FAST.Core.BaseControl;
using YssInformation.Support.Bi.AssociationOrgan.Pojo;
using YssInformation.Support.Bi.Account.Service;
using YssInformation.Support.Bi.Account.Pojo;
//using YssPara.Service.Ab;
//using YssPara.Pojo.Bi;
//using YssPara.Service.Sv;

namespace YssInformation.Support.Fun
{
    public static class BaseInfoUtil
    {
        private static IFundAccService fundAccUnifyPayService = ServiceFactory.createService<IFundAccService>();


        /// <summary>
        /// 现金账户设置 下拉框方法设置 根据组合代码获取
        ///  修改人  liuping .
        /// 修改时间:2011-03-20         
        /// </summary>
        /// <param name="sender">请求对象</param>
        /// <param name="cPortCode">组合代码</param>
        public static void setCaMethod(object sender, string cPortCode)
        {
            YssSelCombox selBox = sender as YssSelCombox;
            FAST.Core.BaseControl.ControlMethodInfo controlMethodInfo = new FAST.Core.BaseControl.ControlMethodInfo();
            if (cPortCode == null || cPortCode.Trim().Equals(""))
            {
                cPortCode = "";
                controlMethodInfo.MethodName = "getDataList";
                controlMethodInfo.MethodParamValues = new string[0];
                selBox.QueryCond = null;
                controlMethodInfo.MethodParams = null;
            }
            else
            {
                controlMethodInfo.MethodName = "getDataListByTypes";
                controlMethodInfo.MethodParamValues = new string[] { cPortCode + "," };
            }

            selBox.MethodInfo = controlMethodInfo;
        }

        /// <summary>
        /// 分级组合下拉框点击之前事件，处理获取当前组合下的分级组合代码.
        ///  修改人  liuping .
        /// 修改时间:2011-03-20         
        /// </summary>
        /// <param name="e">请求对象</param>
        /// <param name="cPortCode">组合代码</param>
        /// <param name="cDcCode">币种代码</param>
        public static void setCaItem(YssBeforeOperEventArgs e, string cPortCode, string cDcCode)
        {
            if (cPortCode == null)
            {
                cPortCode = "";
            }

            if (cDcCode == null)
            {
                cDcCode = "";
            }

            ////去掉对估值的依赖
            //YssBaseCls.Service.ICADataService dataService = DataServiceFactory.createService<YssBaseCls.Service.ICADataService>();
            IFundAccService dataService = DataServiceFactory.createService<IFundAccService>();
            List<CashAcc> dataList = dataService.getCADataListByPortCode(cPortCode, cDcCode);

            if (null != dataList && dataList.Count > 0)
            {
                foreach (CashAcc cashAcc in dataList)
                {
                    Yss.KRichEx.AutoFilter.Model.KTableEntity entity = new Yss.KRichEx.AutoFilter.Model.KTableEntity(cashAcc);
                    e.Collection.Add(entity);
                }       
            }

        }

    }
}
