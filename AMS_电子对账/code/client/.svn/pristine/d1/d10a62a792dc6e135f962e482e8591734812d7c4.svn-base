using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Core.Util;
using Yss.KTable.Models;
using YssElecReco.Pojo.Er;

namespace YssElecReco.Fun
{
    class SplitRuleTableListLoader : TableListLoader
    {
        private Dictionary<string, ErSplitRule> haveLoadData = null;
        /// <summary>
        /// 表格需要加载的数据
        /// </summary>
        private Dictionary<string, Row> tableData = null;
        /// <summary>
        /// 1
        /// </summary>
        /// <param name="tbMain">3</param>
        /// <param name="htView">2</param>
        protected override void loadTreeViewExtendsPojo(Table tbMain, Dictionary<string, Row> htView)
        {
            if(this.haveLoadData != null && this.haveLoadData.Count > 0)
            {
                //移除已经添加过的数据
                foreach (string key in haveLoadData.Keys)
                {
                    if (htView.ContainsKey(key))
                    {
                        htView.Remove(key);
                    }
                }
            }
            this.tableData = htView;
            base.loadTreeViewExtendsPojo(tbMain, htView);
        }
        /// <summary>
        /// 返回表格加载的数据
        /// </summary>
        /// <returns></returns>
        public Dictionary<string, Row> getLoadData()
        {
            return this.tableData;
        }

        public void setHaveAddData(Dictionary<string, ErSplitRule> haveAddData)
        {
            this.haveLoadData = haveAddData;
        }
    }
}
