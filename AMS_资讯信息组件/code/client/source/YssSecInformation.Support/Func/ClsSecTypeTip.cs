using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using FAST.Core.Communication.Service;
using FAST.Core.Util;
using YssSecInformation.Support.Sv.Service;

namespace YssSecInformation.Support.Func
{
    /// <summary>
    /// 检查该证券目前存在持仓共用类
    /// </summary>
    public class ClsSecTypeTip
    {
        /// <summary>
        /// * Title: STORY #27843 资讯信息调整增加提示功能
        /// * Author: chenchen
        /// * Status: Add
        /// * Date: 2016.8.25
        /// * Purpose:手工调整债券基本信息、理财产品信息等中的证券品种时，需核对持仓类型与调整后类型是否一致，不一致给予提示，让用户确认是否需要调整证券品种类型
        /// * Description:如果手工调整了证券品种，检查该证券有持仓的情况下提示是否修改证券品种类型，如果该券没有持仓，则直接返回。
        /// </summary>
        /// <param name="secCode">证券内码</param>
        /// <returns>result：true确认修改 false：不修改</returns>
        public bool checkSecHold(string secCode)
        {
            bool result = true;
            ISecBaseZqService iSecBaseZqService = ServiceFactory.createService<ISecBaseZqService>();
            string secTypeTip = iSecBaseZqService.secTypeTip(secCode);
            if ("true".Equals(secTypeTip))
            {
                if (YssMessageBox.ShowQuestion("该证券目前存在持仓，修改证券品种会影响库存，是否确认修改证券品种类型?", "系统提示", MessageBoxButtons.YesNo, MessageBoxIcon.Question) == DialogResult.Yes)
                {
                    result = true;
                }
                else
                {
                    result = false;
                }
            }

            return result;
        }
    }
}
