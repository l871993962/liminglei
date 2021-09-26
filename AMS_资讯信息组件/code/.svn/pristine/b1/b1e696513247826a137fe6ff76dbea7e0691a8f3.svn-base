using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;




using FAST.Core.Resource;
using System;
using System.Text;
using System.Text.RegularExpressions;
using System.Collections;

namespace YssSecInformation.Support.Sv.Pojo
{
    /// <summary>
    /// ///  chenbo
    /// 2017-06-22
    /// #42948 资讯信息管理组件化拆分
    /// ----------------------------------
    /// Cls_SEC_EQU 的摘要说明。
    /// 作用：证券权益信息实体类
    ///  
    ///  作者：xuqiji 
    ///  
    ///  版本：v4.5.0.1
    ///  
    /// </summary>
    public class Cls_SEC_EQU : ClsBasePojo
    {
        /// <summary>
        /// 自动ID
        /// </summary>
        private string id_D_MP_SEC_EQU = "";

        /// <summary>
        /// 数据标识(DJ-对价派息；SP-证券送配；LT-证券流通；FX-证券发行)
        /// </summary>
        private string c_EQU_CLS = "";

        /// <summary>
        /// 数据来源(H-手动；Z-自动)
        /// </summary>
        private string c_DATA_IDF = "";

        /// <summary>
        /// 证券代码
        /// </summary>
        private string c_SEC_CODE = "";

        /// <summary>
        /// 标的证券
        /// </summary>
        private string c_SEC_CODE_TAG = "";

        /// <summary>
        /// 权益类型
        /// </summary>
        private string c_DS_CODE = " ";

        /// <summary>
        /// 折算类型
        /// </summary>
        private string c_ZS_CODE = " ";

        /// <summary>
        /// 税前权益比例
        /// </summary>
        private string n_EQU_RATIO_PT = "";

        /// <summary>
        /// 税后权益比例
        /// </summary>
        private string n_EQU_RATIO_AT = "";

        /// <summary>
        /// 配售价格
        /// </summary>
        private string n_PRICE_PLAC = "";

        /// <summary>
        /// 分红币种
        /// </summary>
        private string c_DC_CODE = " ";

        /// <summary>
        /// 锁定期限
        /// </summary>
        private string c_DV_VAR_DUR = " ";

        /// <summary>
        /// 发行方式/分红类型
        /// </summary>
        private string c_DV_CODE = " ";

        /// <summary>
        /// 登记日期
        /// </summary>
        private string d_REG = "9998-12-31";

        /// <summary>
        /// 缴款截止日
        /// </summary>
        private string d_FINAL = "9998-12-31";

        /// <summary>
        /// 除权日期
        /// </summary>
        private string d_EXR = "9998-12-31";

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 原主键ID
        /// </summary>
        private string strOldID = "";

        /// <summary>
        /// 交易属性
        /// </summary>
        private string c_DTA_CODE = " ";

        /// <summary>
        /// 交易市场
        /// </summary>
        private string c_MKT_CODE = " ";

        /// <summary>
        /// 空的构造方法.
        /// </summary>
        public Cls_SEC_EQU()
        {
        }

        #region 属性定义

        /// <summary>
        /// set ,get 方法
        /// </summary>
        public string ID_D_MP_SEC_EQU
        {
            get { return id_D_MP_SEC_EQU; }
            set { id_D_MP_SEC_EQU = value; }
        }

        /// <summary>
        /// set ,get 方法
        /// </summary>
        public string C_EQU_CLS
        {
            get { return c_EQU_CLS; }
            set { c_EQU_CLS = value; }
        }

        /// <summary>
        /// set ,get 方法
        /// </summary>
        public string C_DATA_IDF
        {
            get { return c_DATA_IDF; }
            set { c_DATA_IDF = value; }
        }

        /// <summary>
        /// set ,get 方法
        /// </summary>
        public string C_SEC_CODE
        {
            get { return c_SEC_CODE; }
            set { c_SEC_CODE = value; }
        }

        /// <summary>
        /// set ,get 方法
        /// </summary>
        public string C_SEC_CODE_TAG
        {
            get { return c_SEC_CODE_TAG; }
            set { c_SEC_CODE_TAG = value; }
        }

        /// <summary>
        /// set ,get 方法
        /// </summary>
        public string C_DS_CODE
        {
            get { return c_DS_CODE; }
            set { c_DS_CODE = value; }
        }

        /// <summary>
        /// set ,get 方法
        /// </summary>
        public string C_ZS_CODE
        {
            get { return c_ZS_CODE; }
            set { c_ZS_CODE = value; }
        }

        /// <summary>
        /// set ,get 方法
        /// </summary>
        public string N_EQU_RATIO_PT
        {
            get { return n_EQU_RATIO_PT; }
            set { n_EQU_RATIO_PT = value; }
        }

        /// <summary>
        /// set ,get 方法
        /// </summary>
        public string N_EQU_RATIO_AT
        {
            get { return n_EQU_RATIO_AT; }
            set { n_EQU_RATIO_AT = value; }
        }

        /// <summary>
        /// set ,get 方法
        /// </summary>
        public string N_PRICE_PLAC
        {
            get { return n_PRICE_PLAC; }
            set { n_PRICE_PLAC = value; }
        }

        /// <summary>
        /// set ,get 方法
        /// </summary>
        public string C_DC_CODE
        {
            get { return c_DC_CODE; }
            set { c_DC_CODE = value; }
        }

        /// <summary>
        /// set ,get 方法
        /// </summary>
        public string C_DV_VAR_DUR
        {
            get { return c_DV_VAR_DUR; }
            set { c_DV_VAR_DUR = value; }
        }

        /// <summary>
        /// set ,get 方法
        /// </summary>
        public string C_DV_CODE
        {
            get { return c_DV_CODE; }
            set { c_DV_CODE = value; }
        }

        /// <summary>
        /// set ,get 方法
        /// </summary>
        public string D_REG
        {
            get { return d_REG; }
            set { d_REG = value; }
        }

        /// <summary>
        /// set ,get 方法
        /// </summary>
        public string D_FINAL
        {
            get { return d_FINAL; }
            set { d_FINAL = value; }
        }

        /// <summary>
        /// set ,get 方法
        /// </summary>
        public string D_EXR
        {
            get { return d_EXR; }
            set { d_EXR = value; }
        }

        /// <summary>
        /// set ,get 方法
        /// </summary>
        public string C_DESC
        {
            get { return c_DESC; }
            set { c_DESC = value; }
        }

        /// <summary>
        /// set ,get 方法
        /// </summary>
        public string OldID
        {
            get { return strOldID; }
            set { strOldID = value; }
        }

        /// <summary>
        /// set ,get 方法
        /// </summary>
        public string C_DTA_CODE
        {
            set
            {
                c_DTA_CODE = value;
            }

            get
            {
                return c_DTA_CODE;
            }
        }

        /// <summary>
        /// 交易市场
        /// </summary>
        public string C_MKT_CODE
        {
            get { return c_MKT_CODE; }
            set { c_MKT_CODE = value; }
        }      
  


        #endregion
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

            this.ID_D_MP_SEC_EQU = strPojos[0];
            this.C_DATA_IDF = strPojos[1];
            this.C_SEC_CODE = strPojos[2];
            this.C_SEC_CODE_TAG = strPojos[3];
            this.C_DS_CODE = strPojos[4];
            this.N_EQU_RATIO_PT = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.NumberToString, strPojos[5]);
            this.N_EQU_RATIO_AT = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.NumberToString, strPojos[6]);

            this.N_PRICE_PLAC = ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.NumberToString, strPojos[7]);

            this.C_DC_CODE = strPojos[8];
            this.C_DV_VAR_DUR = strPojos[9];
            this.C_DV_CODE = strPojos[10];
            this.D_REG = strPojos[11];
            this.D_FINAL = strPojos[12];
            this.D_EXR = strPojos[13];

            this.C_DESC = strPojos[14];

            this.OldID = strPojos[0];

            this.C_DTA_CODE = strPojos[15];
            this.C_MKT_CODE = strPojos[16];
            this.C_ZS_CODE = strPojos[17];
            parseComm(strPojo); // 解析公共字段
        }




        /// <summary>.
        /// 把对象的属性以\t为间隔拼接成字符串.
        /// </summary>
        /// <returns>返回拼接字符串.</returns>
        public override string buildAttrToStr()
        {
            StringBuilder buff = new StringBuilder();

            buff.Append(this.ID_D_MP_SEC_EQU).Append("\t");
            buff.Append(this.C_DATA_IDF).Append("\t");
            buff.Append(this.C_SEC_CODE).Append("\t");
            buff.Append(this.C_SEC_CODE_TAG).Append("\t");
            buff.Append(this.C_DS_CODE).Append("\t");
            buff.Append(ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, (this.n_EQU_RATIO_PT.Trim().Length > 0 ? this.n_EQU_RATIO_PT.Trim() : "0"))).Append("\t");
            buff.Append(ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, (this.n_EQU_RATIO_AT.Trim().Length > 0 ? this.n_EQU_RATIO_AT.Trim() : "0"))).Append("\t");
            buff.Append(ClsFunction.formatNumber(FAST.Core.Context.ClsConstant.StringToNumber, this.N_PRICE_PLAC)).Append("\t");
            buff.Append(this.C_DC_CODE).Append("\t");
            buff.Append(this.C_DV_VAR_DUR).Append("\t");
            buff.Append(this.C_DV_CODE).Append("\t");
            buff.Append(this.D_REG).Append("\t");
            buff.Append(this.D_FINAL).Append("\t");
            buff.Append(this.D_EXR).Append("\t");
            buff.Append(this.C_DESC).Append("\t");

            buff.Append(this.OldID).Append("\t");
            buff.Append(this.C_DTA_CODE).Append("\t");
            buff.Append(this.C_MKT_CODE).Append("\tnull");
            buff.Append(this.C_ZS_CODE).Append("\t");
            return buff.ToString();
        }



        /// <summary>
        /// 解析数组  权益信息需要添加批量审核的功能 .
        /// </summary>
        /// <param name="array">需要解析的字符串</param>
        /// <returns>返回拼接字符串.</returns>
        public string parseArray(ArrayList array)
        {
            string strStr = "";
            ////System.Text.StringBuilder builder = new StringBuilder();
            ////IEnumerator enums = array.GetEnumerator();
            ////ClsDividend data = null;
            ////while (enums.MoveNext())
            ////{
            ////    data = (ClsDividend)enums.Current;
            ////    builder.Append(data.builderRowStr()).Append("\f\f\f\f");
            ////}
            ////if (builder.Length > 4)
            ////{
            ////    reStr = builder.ToString().Substring(0, builder.Length - 4);
            ////    reStr = "\f\n\f\n\f\n" + reStr;
            ////}
            return strStr;
        }



        /////// <summary>
        /////// 批量处理数据后台交互方法 权益信息需要添加批量审核的功能 .
        /////// </summary>
        /////// <param name="array">需要解析的数组</param>
        /////// <returns>返回查询的字符串.</returns>
        ////private string MultauditTradeSub(ArrayList array)
        ////{
        ////    string strStr = "";
        ////    strStr = (string)YssCore.Fun.ClsFunction.SendStringToServers(builderRowStr() + parseArray(array), FAST.Core.Context.ClsContext.ConnPath + "/ucoserver?cmd=dividend&flag=opervalue&showtype=multauditTradeSub");
        ////    return strStr;
        ////}

    }
}


