using FAST.Core.Util;
using FAST.Common.Service.Pojo;

using FAST.Core.Exceptions;

using FAST.Core.Context;


////using YssBaseCls.Pojo;

using FAST.Common.Service.Pojo.Base;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Newtonsoft.Json;
using System.Collections;


namespace YssSecInformation.Support.Ac.Etfskep.Pojo
{
    /// <summary>
    /// 股票篮
    /// </summary>
    public class EtfSkep : BasePojo, IEnclosure
    {
        /// <summary>
        /// 附件列表
        /// </summary>
        private List<DataEnclosure> _dataEnclosureList;

        /// <summary>
        /// 业务日期
        /// </summary>
        private string d_TRADE = "";

        /// <summary>
        /// ETF基金代码（二级代码）
        /// </summary>
        private string c_TRADE_CODE = "";

        /// <summary>
        /// 证券代码
        /// </summary>
        private string c_SEC_CODE = "";

        /// <summary>
        /// 证券简称
        /// </summary>
        private string c_SEC_NAME = "";

        /// <summary>
        /// 证券数量
        /// </summary>
        private string n_AMOUNT = "";

        /// <summary>
        /// 替代标志(上交所：表示该成份产品是否允许用现金进行替代。
        /// </summary>
        private string c_REP_MARK = "";

        /// <summary>
        /// 溢价比例
        /// </summary>
        private string n_RATIO = "";

        /// <summary>
        /// 币种代码
        /// </summary>
        private string c_DC_CODE = "";

        /// <summary>
        /// 申购替代金额
        /// </summary>
        private string n_SUB_MONEY = "";

        /// <summary>
        /// 赎回替代金额
        /// </summary>
        private string n_REDEM_MONEY = "";

        /// <summary>
        /// 成分股市场
        /// </summary>
        private string c_MKT_CODE = "";

        /// <summary>
        /// 替代币种
        /// </summary>
        private string c_DC_CODE_REPL = "";

        /// <summary>
        /// 属性: 业务日期 
        /// </summary>
        [JsonProperty(PropertyName = "d_TRADE")]
        public string D_TRADE
        {
            set { d_TRADE = value; }

            get { return d_TRADE; }
        }

        /// <summary>
        /// 属性: ETF基金代码（二级代码） 
        /// </summary>
        [JsonProperty(PropertyName = "c_TRADE_CODE")]
        public string C_TRADE_CODE
        {
            set { c_TRADE_CODE = value; }

            get { return c_TRADE_CODE; }
        }

        /// <summary>
        /// 属性: 证券代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_CODE")]
        public string C_SEC_CODE
        {
            set { c_SEC_CODE = value; }

            get { return c_SEC_CODE; }
        }

        /// <summary>
        /// 属性: 证券简称 
        /// </summary>
        [JsonProperty(PropertyName = "c_SEC_NAME")]
        public string C_SEC_NAME
        {
            set { c_SEC_NAME = value; }

            get { return c_SEC_NAME; }
        }

        /// <summary>
        /// 属性: 证券数量 
        /// </summary>
        [JsonProperty(PropertyName = "n_AMOUNT")]
        public string N_AMOUNT
        {
            set { n_AMOUNT = value; }

            get { return n_AMOUNT; }
        }

        /// <summary>
        /// 属性: 替代标志(上交所：表示该成份产品是否允许用现金进行替代。 
        /// </summary>
        [JsonProperty(PropertyName = "c_REP_MARK")]
        public string C_REP_MARK
        {
            set { c_REP_MARK = value; }

            get { return c_REP_MARK; }
        }

        /// <summary>
        /// 属性: 溢价比例 
        /// </summary>
        [JsonProperty(PropertyName = "n_RATIO")]
        public string N_RATIO
        {
            set { n_RATIO = value; }

            get { return n_RATIO; }
        }

        /// <summary>
        /// 属性: 币种代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_DC_CODE")]
        public string C_DC_CODE
        {
            set { c_DC_CODE = value; }

            get { return c_DC_CODE; }
        }

        /// <summary>
        /// 属性: 申购替代金额 
        /// </summary>
        [JsonProperty(PropertyName = "n_SUB_MONEY")]
        public string N_SUB_MONEY
        {
            set { n_SUB_MONEY = value; }

            get { return n_SUB_MONEY; }
        }

        /// <summary>
        /// 属性: 赎回替代金额 
        /// </summary>
        [JsonProperty(PropertyName = "n_REDEM_MONEY")]
        public string N_REDEM_MONEY
        {
            set { n_REDEM_MONEY = value; }

            get { return n_REDEM_MONEY; }
        }

        /// <summary>
        /// 属性: 成分股市场 
        /// </summary>
        [JsonProperty(PropertyName = "c_MKT_CODE")]
        public string C_MKT_CODE
        {
            set { c_MKT_CODE = value; }

            get { return c_MKT_CODE; }
        }

        /// <summary>
        /// 属性: 替代币种 
        /// </summary>
        [JsonProperty(PropertyName = "c_DC_CODE_REPL")]
        public string C_DC_CODE_REPL
        {
            set { c_DC_CODE_REPL = value; }

            get { return c_DC_CODE_REPL; }
        }

        #region IEnclosure 成员。实现附件功能。何讯，20151208

        /// <summary>
        /// 插入数据附件信息。
        /// </summary>
        /// <param name="dataEnclosure">附件信息</param>
        public void AddEnclosure(DataEnclosure dataEnclosure)
        {
            if (this._dataEnclosureList == null)
            {
                this._dataEnclosureList = new List<DataEnclosure>();
            }

            this._dataEnclosureList.Add(dataEnclosure);
        }

        /// <summary>
        /// 获取附件列表。
        /// </summary>
        /// <returns>返回列表</returns>
        public List<DataEnclosure> GetEnclosureList()
        {
            if (this._dataEnclosureList == null)
            {
                this._dataEnclosureList = new List<DataEnclosure>();
            }

            return this._dataEnclosureList;
        }

        /// <summary>
        /// 插入数据附件信息。
        /// </summary>
        /// <param name="dataEnclosureList">附件集</param>
        public void AddEnclosure(ArrayList dataEnclosureList)
        {
            if (this._dataEnclosureList == null)
            {
                this._dataEnclosureList = new List<DataEnclosure>();
            }

            foreach (DataEnclosure dataEnclosure in dataEnclosureList)
            {
                this._dataEnclosureList.Add(dataEnclosure);
            }
        }

        /// <summary>
        /// 删除附件信息
        /// </summary>
        /// <param name="dataEnclosure">附件信息</param>
        public void RemoveEnclosure(DataEnclosure dataEnclosure)
        {
            if (this._dataEnclosureList == null)
            {
                this._dataEnclosureList = new List<DataEnclosure>();
            }

            this._dataEnclosureList.Remove(dataEnclosure);
        }
        #endregion

    }
}


