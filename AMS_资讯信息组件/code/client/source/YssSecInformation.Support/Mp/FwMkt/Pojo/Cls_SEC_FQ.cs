using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;



using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
////using YssBaseCls.Pojo;
using System.Text.RegularExpressions;

namespace YssSecInformation.Support.Mp.FwMkt.Pojo
{
    /// <summary>
    /// 远期外汇行情
    /// </summary>
    public class Cls_SEC_FQ : ClsBasePojo
    {
        /// <summary>
        /// 货币对
        /// </summary>
        private string c_SEC_CODE = "";

        /// <summary>
        /// 行情日期
        /// </summary>
        private string d_MKT = "";

        /// <summary>
        /// 行情分类
        /// </summary>
        private string c_MKT_CLS = "";

        /// <summary>
        /// 品种期限
        /// </summary>
        private string c_DV_VAR_DUR = "";

        /// <summary>
        /// 行情时间
        /// </summary>
        private string c_MKT_TIME = "";

        /// <summary>
        /// 即期日期
        /// </summary>
        private string d_SPOT = "";

        /// <summary>
        /// 远期日期
        /// </summary>
        private string d_FW = "";

        /// <summary>
        /// 买入价
        /// </summary>
        private string n_PRICE_BUY = "";

        /// <summary>
        /// 卖出价
        /// </summary>
        private string n_PRICE_SELL = "";

        /// <summary>
        /// 买入点数
        /// </summary>
        private string n_POINT_BUY = "";

        /// <summary>
        /// 卖出点数
        /// </summary>
        private string n_POINT_SELL = "";

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 数据来源
        /// </summary>
        private string c_DATA_IDF = "";

        /// <summary>
        /// 老的币种代码
        /// </summary>
        private string oldC_SEC_CODE = "";

        /// <summary>
        /// 行情日期
        /// </summary>
        private string oldD_MKT = "";

        /// <summary>
        /// 行情分类
        /// </summary>
        private string oldC_MKT_CLS = "";

        /// <summary>
        /// 品种期限
        /// </summary>
        private string oldC_DV_VAR_DUR = "";

        /// <summary>
        /// 货币对
        /// </summary>
        public string C_SEC_CODE
        {
            set { c_SEC_CODE = value; }
            get { return c_SEC_CODE; }
        }

        /// <summary>
        /// 行情日期
        /// </summary>
        public string D_MKT
        {
            set { d_MKT = value; }
            get { return d_MKT; }
        }

        /// <summary>
        /// 行情分类
        /// </summary>
        public string C_MKT_CLS
        {
            set { c_MKT_CLS = value; }
            get { return c_MKT_CLS; }
        }

        /// <summary>
        /// 品种期限
        /// </summary>
        public string C_DV_VAR_DUR
        {
            set { c_DV_VAR_DUR = value; }
            get { return c_DV_VAR_DUR; }
        }

        /// <summary>
        /// 行情时间
        /// </summary>
        public string C_MKT_TIME
        {
            set { c_MKT_TIME = value; }
            get { return c_MKT_TIME; }
        }

        /// <summary>
        /// 即期日期
        /// </summary>
        public string D_SPOT
        {
            set { d_SPOT = value; }
            get { return d_SPOT; }
        }

        /// <summary>
        /// 远期日期
        /// </summary>
        public string D_FW
        {
            set { d_FW = value; }
            get { return d_FW; }
        }

        /// <summary>
        /// 买入价
        /// </summary>
        public string N_PRICE_BUY
        {
            set { n_PRICE_BUY = value; }
            get { return n_PRICE_BUY; }
        }

        /// <summary>
        /// 卖出价
        /// </summary>
        public string N_PRICE_SELL
        {
            set { n_PRICE_SELL = value; }
            get { return n_PRICE_SELL; }
        }

        /// <summary>
        /// 买入点数
        /// </summary>
        public string N_POINT_BUY
        {
            set { n_POINT_BUY = value; }
            get { return n_POINT_BUY; }
        }

        /// <summary>
        /// 卖出点数
        /// </summary>
        public string N_POINT_SELL
        {
            set { n_POINT_SELL = value; }
            get { return n_POINT_SELL; }
        }

        /// <summary>
        /// 描述
        /// </summary>
        public string C_DESC
        {
            set { c_DESC = value; }
            get { return c_DESC; }
        }

        /// <summary>
        /// 数据来源
        /// </summary>
        public string C_DATA_IDF
        {
            set { c_DATA_IDF = value; }
            get { return c_DATA_IDF; }
        }

        /// <summary>
        /// 币种代码
        /// </summary>
        public string OLDC_SEC_CODE
        {
            set { oldC_SEC_CODE = value; }
            get { return oldC_SEC_CODE; }
        }

        /// <summary>
        /// 行情日期
        /// </summary>
        public string OLDD_MKT
        {
            set { oldD_MKT = value; }
            get { return oldD_MKT; }
        }

        /// <summary>
        /// 行情分类
        /// </summary>
        public string OLDC_MKT_CLS
        {
            set { oldC_MKT_CLS = value; }
            get { return oldC_MKT_CLS; }
        }

        /// <summary>
        /// 品种期限
        /// </summary>
        public string OLDC_DV_VAR_DUR
        {
            set { oldC_DV_VAR_DUR = value; }
            get { return oldC_DV_VAR_DUR; }
        }

        /// <summary>
        /// 解析字符串.
        /// </summary>
        /// <param name="strRespStr">解析的字符串</param>
        public override void listItemParse(string strRespStr)
        {
            parseStrToAttr(strRespStr);
        }

        /// <summary>
        /// 解析字符串来给属性付值.
        /// </summary>
        /// <param name="strPojo"> 由对象的属性以"\t"为间隔拼接成的字符串.</param>
        public void parseStrToAttr(string strPojo)
        {
            // 拆分字符串，获取对象的每个属性
            string[] strPojos = Regex.Split(strPojo, "\t");
            this.c_SEC_CODE = strPojos[0];
            this.d_MKT = strPojos[1];
            this.c_MKT_CLS = strPojos[2];
            this.c_DV_VAR_DUR = strPojos[3];
            this.c_MKT_TIME = strPojos[4];
            this.d_SPOT = strPojos[5];
            this.d_FW = strPojos[6];
            this.n_PRICE_BUY = strPojos[7];
            this.n_PRICE_SELL = strPojos[8];
            this.n_POINT_BUY = strPojos[9];
            this.n_POINT_SELL = strPojos[10];
            this.c_DESC = strPojos[11];
            this.c_DATA_IDF = strPojos[12];
            this.oldC_SEC_CODE = strPojos[0];
            this.oldD_MKT = strPojos[1];
            this.oldC_MKT_CLS = strPojos[2];
            this.oldC_DV_VAR_DUR = strPojos[3];
            parseComm(strPojo);
        }

        /// <summary>.
        /// 把对象的属性以\t为间隔拼接成字符串.
        /// </summary>
        /// <returns>返回拼接字符串.</returns>
        public override string buildAttrToStr()
        {
            StringBuilder buff = new StringBuilder();
            buff.Append(this.c_SEC_CODE).Append("\t");
            buff.Append(this.d_MKT).Append("\t");
            buff.Append(this.c_MKT_CLS).Append("\t");
            buff.Append(this.c_DV_VAR_DUR).Append("\t");
            buff.Append(this.c_MKT_TIME).Append("\t");
            buff.Append(this.d_SPOT).Append("\t");
            buff.Append(this.d_FW).Append("\t");
            buff.Append(this.n_PRICE_BUY).Append("\t");
            buff.Append(this.n_PRICE_SELL).Append("\t");
            buff.Append(this.n_POINT_BUY).Append("\t");
            buff.Append(this.n_POINT_SELL).Append("\t");
            buff.Append(this.c_DESC).Append("\t");
            buff.Append(this.c_DATA_IDF).Append("\t");
            buff.Append(this.oldC_SEC_CODE).Append("\t");
            buff.Append(this.oldD_MKT).Append("\t");
            buff.Append(this.oldC_MKT_CLS).Append("\t");
            buff.Append(this.oldC_DV_VAR_DUR).Append("\tnull");

            return buff.ToString();
        }
    }
}


