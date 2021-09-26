using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using FAST.Core.Util;
using Yss.KTable.Models;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using YssElecReco.Pojo.Bi;

namespace YssElecReco.Fun
{
    /// <summary>
    /// 对账指标自定义表加载
    /// </summary>
    public class ElecRelaTableListLoader : TableListLoader
    {
        /// <summary>
        /// 数据字典
        /// </summary>
        private Dictionary<string, Row> dataDict = new Dictionary<string, Row>();

        /// <summary>
        /// defaultCheckedRows
        /// </summary>
        private List<string> defaultCheckedRows = new List<string>(); 

        /// <summary>
        /// DefaultCheckedRows
        /// </summary>
        public List<string> DefaultCheckedRows
        {
            get { return this.defaultCheckedRows; }
            set { this.defaultCheckedRows = value; }
        }

        /// <summary>
        /// 设置表格行数据,根据指标代码去重
        /// </summary>
        /// <param name="tbMain">表格</param>
        /// <param name="headKeys">列头信息</param>
        /// <param name="dataList">数据信息</param>
        /// <param name="showConvAssemble">翻译信息</param>
        protected override void setTableListData(Table tbMain, List<ListHeadInfo> headKeys, List<BasePojo> dataList, Dictionary<string, Dictionary<string, string>> showConvAssemble)
        {
            if (this.AutoLoadEnclosure)
            {
                this.LoadDataEnclosure(dataList);
            }

            HashSet<string> set = new HashSet<string>(this.defaultCheckedRows);
            ////STORY #34372 优化系统数据维护后的查询。张绍林-20160926
            foreach (BasePojo pojo in dataList)
            {
                string code = ((ElecRela)pojo).C_ZB_CODE;
                if (!dataDict.ContainsKey(code))
                {
                    Row row = this.CreateRow(pojo, headKeys, showConvAssemble);
                    if (set.Contains(code))
                    {
                        row.Checked = true;
                    }

                    tbMain.Rows.Add(row);
                    dataDict.Add(code, row);
                }
            }
        }


    }
}
