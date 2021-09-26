////using YssBaseCls.Fun;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Util;
using FAST.Common.Service.Pojo;



using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
////using YssBaseCls.Pojo;

////namespace YssData.Pojo.Mp
namespace YssSecInformation.Support.Mp.SecMktMap.Pojo
{
    /// <summary>
    ///  功能描述：行情数据的pojo类，与后台的secMarketPriceBean 的相对应
    ///  ///  
    ///  作者：chenyoulong
    ///  
    ///  版本：v4.5.0.1
    ///  
    ///  修改：zhuangyuchen
    ///  版本:v4.5.0.2
    ///  修改内容：第二轮翻新
    ///  
    /// /// // －－－－修改记录－－－－
    /// 当前版本：V4.5.0.3
    /// 修改人：zhaungyuchen
    /// 修改日期：2011-2-22
    /// 修改简介：根据需求的二次更新，进行前台解析方法的调整
    /// 修改主要有以下几点：
    /// 1、根据新的表结构进行调整
    /// 2、注释完善
    /// 3、属性重命名
    /// 4:构建字符串的时候添加菜单id和表头到后台
    /// </summary>
    public class Cls_SEC_MKT : ClsBasePojo
    {
        /// <summary>
        /// 证券内码
        /// </summary>
        private string c_SEC_CODE = "";

        /// <summary>
        /// 行情来源
        /// </summary>
        private string c_DV_PLAT = "";

        /// <summary>
        /// 行情日期
        /// </summary>
        private string d_MKT = "";

        /// <summary>
        /// 行情分类
        /// </summary>
        private string c_MKT_CLS = "";

        /// <summary>
        /// 行情时间
        /// </summary>
        private string c_MKT_TIME = "";

        /// <summary>
        /// 最新价
        /// </summary>
        private string n_PRICE_NEW = "0";

        /// <summary>
        /// 买入价
        /// </summary>
        private string n_PRICE_BUY = "0";

        /// <summary>
        /// 卖出价
        /// </summary>
        private string n_PRICE_SELL = "0";

        /// <summary>
        /// 最高价
        /// </summary>
        private string n_PRICE_HIGH = "0";

        /// <summary>
        /// 最低价
        /// </summary>
        private string n_PRICE_LOW = "0";

        /// <summary>
        /// 收盘价
        /// </summary>
        private string n_PRICE_CLOSE = "0";

        /// <summary>
        ///  开盘价
        /// </summary>
        private string n_PRICE_OPEN = "0";

        /// <summary>
        /// 平均价
        /// </summary>
        private string n_PRICE_AVG = "0";

        /// <summary>
        /// 公告日期
        /// </summary>
        private string d_PUB = "";

        /// <summary>
        /// 描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        /// 修改前的证券内码
        /// </summary>
        private string oldC_SEC_CODE = "";

        /// <summary>
        /// 原行情来源
        /// </summary>
        private string oldC_DV_PLAT = "";

        /// <summary>
        /// 修改前的行情日期
        /// </summary>
        private string oldD_MKT = "";

        /// <summary>
        /// 调用类的标识
        /// </summary>
        private string beanId = "";

        /// <summary>
        /// 行情状态
        /// ADD BY WZH STORY #32313 停牌股票信息生成做成公共层面，不关联组合
        /// </summary>
        private string c_HQZT_CODE = "";

        /// <summary>
        /// 属性
        /// STORY #35353 （嘉实QD需求）原4.0需求：10354 【QDV4嘉实2011年11月09日01_A】估值检查步骤中，增加对除权股票的行情状态的判断
        /// </summary>
        private string c_MKT_STATUS = "";

        /// <summary>
        /// 属性
        /// STORY #35353 （嘉实QD需求）原4.0需求：10354 【QDV4嘉实2011年11月09日01_A】估值检查步骤中，增加对除权股票的行情状态的判断
        /// </summary>
        private string n_AMOUNT_TOTAL = "";

        /// <summary>
        /// 属性
        /// STORY #35353 （嘉实QD需求）原4.0需求：10354 【QDV4嘉实2011年11月09日01_A】估值检查步骤中，增加对除权股票的行情状态的判断
        /// </summary>
        private string n_PRICE_TOTAL = "";

        /// <summary>
        /// 投资组合
        /// STORY #35336 （嘉实QD需求）原4.0需求：26150 需求北京-[嘉实基金]QDII资产管理系统[高]2015928001（QDII汇率导入需求)
        /// </summary>
        private string c_PORT_CODE = "";

        /// <summary>
        /// 属性 
        /// STORY #35353 （嘉实QD需求）原4.0需求：10354 【QDV4嘉实2011年11月09日01_A】估值检查步骤中，增加对除权股票的行情状态的判断
        /// </summary>
        public string C_MKT_STATUS
        {
            set { c_MKT_STATUS = value; }

            get { return c_MKT_STATUS; }
        }

        /// <summary>
        /// 属性 
        /// STORY #35353 （嘉实QD需求）原4.0需求：10354 【QDV4嘉实2011年11月09日01_A】估值检查步骤中，增加对除权股票的行情状态的判断
        /// </summary>
        public string N_AMOUNT_TOTAL
        {
            set { n_AMOUNT_TOTAL = value; }

            get { return n_AMOUNT_TOTAL; }
        }

        /// <summary>
        /// 属性 
        /// STORY #35353 （嘉实QD需求）原4.0需求：10354 【QDV4嘉实2011年11月09日01_A】估值检查步骤中，增加对除权股票的行情状态的判断
        /// </summary>
        public string N_PRICE_TOTAL
        {
            set { n_PRICE_TOTAL = value; }

            get { return n_PRICE_TOTAL; }
        }

        /// <summary>
        /// 属性: 投资组合 
        /// STORY #35336 （嘉实QD需求）原4.0需求：26150 需求北京-[嘉实基金]QDII资产管理系统[高]2015928001（QDII汇率导入需求)
        /// </summary>
        public string C_PORT_CODE
        {
            set { c_PORT_CODE = value; }

            get { return c_PORT_CODE; }
        }

        /// <summary>
        /// 属性: 行情状态 
        /// ADD BY WZH STORY #32313 停牌股票信息生成做成公共层面，不关联组合
        /// </summary>
        public string C_HQZT_CODE
        {
            set { c_HQZT_CODE = value; }

            get { return c_HQZT_CODE; }
        }


        /// <summary>
        /// set,get方法
        /// </summary>
        public string C_SEC_CODE
        {
            get { return c_SEC_CODE; }
            set { c_SEC_CODE = value; }
        }

        /// <summary>
        /// set,get方法
        /// </summary>
        public string C_DV_PLAT
        {
            get { return c_DV_PLAT; }
            set { c_DV_PLAT = value; }
        }

        /// <summary>
        /// set,get方法
        /// </summary>
        public string OldC_DV_PLAT
        {
            get { return oldC_DV_PLAT; }
            set { oldC_DV_PLAT = value; }
        }

        /// <summary>
        /// set,get方法
        /// </summary>
        public string D_MKT
        {
            get { return d_MKT; }
            set { d_MKT = value; }
        }

        /// <summary>
        /// set,get方法
        /// </summary>
        public string C_MKT_CLS
        {
            get { return c_MKT_CLS; }
            set { c_MKT_CLS = value; }
        }

        /// <summary>
        /// set,get方法
        /// </summary>
        public string C_MKT_TIME
        {
            get { return c_MKT_TIME; }
            set { c_MKT_TIME = value; }
        }

        /// <summary>
        /// set,get方法
        /// </summary>
        public string N_PRICE_NEW
        {
            get { return n_PRICE_NEW; }
            set { n_PRICE_NEW = value; }
        }

        /// <summary>
        /// set,get方法
        /// </summary>
        public string N_PRICE_BUY
        {
            get { return n_PRICE_BUY; }
            set { n_PRICE_BUY = value; }
        }

        /// <summary>
        /// set,get方法
        /// </summary>
        public string N_PRICE_SELL
        {
            get { return n_PRICE_SELL; }
            set { n_PRICE_SELL = value; }
        }

        /// <summary>
        /// set,get方法
        /// </summary>
        public string N_PRICE_HIGH
        {
            get { return n_PRICE_HIGH; }
            set { n_PRICE_HIGH = value; }
        }

        /// <summary>
        /// set,get方法
        /// </summary>
        public string N_PRICE_LOW
        {
            get { return n_PRICE_LOW; }
            set { n_PRICE_LOW = value; }
        }

        /// <summary>
        /// set,get方法
        /// </summary>
        public string N_PRICE_CLOSE
        {
            get { return n_PRICE_CLOSE; }
            set { n_PRICE_CLOSE = value; }
        }

        /// <summary>
        /// set,get方法
        /// </summary>
        public string N_PRICE_OPEN
        {
            get { return n_PRICE_OPEN; }
            set { n_PRICE_OPEN = value; }
        }

        /// <summary>
        /// set,get方法
        /// </summary>
        public string N_PRICE_AVG
        {
            get { return n_PRICE_AVG; }
            set { n_PRICE_AVG = value; }
        }

        /// <summary>
        /// set,get方法
        /// </summary>
        public string D_PUB
        {
            get { return d_PUB; }
            set { d_PUB = value; }
        }

        /// <summary>
        /// set,get方法
        /// </summary>
        public string C_DESC
        {
            get { return c_DESC; }
            set { c_DESC = value; }
        }

        /// <summary>
        /// set,get方法
        /// </summary>
        public string OldC_SEC_CODE
        {
            get { return oldC_SEC_CODE; }
            set { oldC_SEC_CODE = value; }
        }

        /// <summary>
        /// set,get方法
        /// </summary>
        public string OldD_MKT
        {
            get { return oldD_MKT; }
            set { oldD_MKT = value; }
        }

        /// <summary>
        /// set,get方法
        /// </summary>
        public string BeanId
        {
            get { return beanId; }
            set { beanId = value; }
        }

        /// <summary>
        /// 解析list中字符串，被基类调用.
        /// </summary>
        /// <param name="strRespStr">解析字符串.</param>
        public override void listItemParse(string strRespStr)
        {
            parseStrToAttr(strRespStr);
        }


        /// <summary>
        /// 解析后台传来的字符串.
        /// </summary>
        /// <param name="strRespStr">解析字符串.</param>
        public void parseStrToAttr(string strRespStr)
        {
            string[] tmpAry = Regex.Split(strRespStr, "\t");
            this.c_SEC_CODE = tmpAry[0];
            this.c_DV_PLAT = tmpAry[1];
            this.d_MKT = tmpAry[2];
            this.c_MKT_CLS = tmpAry[3];
            this.c_MKT_TIME = tmpAry[4];
            this.n_PRICE_NEW = tmpAry[5];
            this.n_PRICE_BUY = tmpAry[6];
            this.n_PRICE_SELL = tmpAry[7];
            this.n_PRICE_HIGH = tmpAry[8];
            this.n_PRICE_LOW = tmpAry[9];
            this.n_PRICE_CLOSE = tmpAry[10];
            this.n_PRICE_OPEN = tmpAry[11];
            this.n_PRICE_AVG = tmpAry[12];
            this.d_PUB = tmpAry[13];
            this.c_DESC = tmpAry[14];
            parseComm(strRespStr);

        }


        /// <summary>
        /// 拼接字符串，传送到后台.
        /// </summary>
        /// <returns>返回拼接字符串.</returns>
        public override string buildAttrToStr()
        {
            StringBuilder buf = new StringBuilder();
            buf.Append(this.c_SEC_CODE.Trim()).Append("\t");
            buf.Append(this.c_DV_PLAT).Append("\t");
            buf.Append(this.d_MKT.Trim()).Append("\t");
            buf.Append(this.c_MKT_CLS.Trim()).Append("\t");
            buf.Append(this.c_MKT_TIME.Trim()).Append("\t");
            buf.Append(this.n_PRICE_NEW.Trim()).Append("\t");
            buf.Append(this.n_PRICE_BUY.Trim()).Append("\t");
            buf.Append(this.n_PRICE_SELL.Trim()).Append("\t");
            buf.Append(this.n_PRICE_HIGH.Trim()).Append("\t");
            buf.Append(this.n_PRICE_LOW.Trim()).Append("\t");
            buf.Append(this.n_PRICE_CLOSE.Trim()).Append("\t");
            buf.Append(this.n_PRICE_OPEN).Append("\t");
            buf.Append(this.n_PRICE_AVG.Trim()).Append("\t");
            buf.Append(this.d_PUB.Trim()).Append("\t");
            buf.Append(this.c_DESC.Trim()).Append("\t");
            buf.Append(this.oldC_SEC_CODE.Trim()).Append("\t");
            buf.Append(this.oldC_DV_PLAT).Append("\t");
            buf.Append(this.oldD_MKT.Trim()).Append("\tnull");
            return buf.ToString();
        }


        //// <summary>
        //// 执行增加操作时，向后台传送数据
        //// </summary>
        //// <returns></returns>
        ////public string addSecMarketPrice()
        ////{
        ////    string reStr = "";
        ////    try
        ////    {
        ////        if (m_sFilterType == null)
        ////        {
        ////            reStr = (string)YssCore.Fun.ClsFunction.SendStringToServers(builderRowStr(), FAST.Core.Context.ClsContext.ConnPath + "/ucoserver?cmd=" + this.BeanId + "&flag=add&showtype=listview1");
        ////        }
        ////        else
        ////        {
        ////            reStr = (string)YssCore.Fun.ClsFunction.SendStringToServers(builderRowStr() + "\r\t" + m_sFilterType.builderRowStr(), FAST.Core.Context.ClsContext.ConnPath + "/ucoserver?cmd=" + this.BeanId + "&flag=add&showtype=listview1");
        ////        }
        ////    }
        ////    catch (Exception ex)
        ////    {
        ////        throw new YssCore.BaseCls.ClsBaseException(ex.Message, ex);
        ////    }
        ////    return reStr;

        ////}


        //// <summary>
        //// 执行修改操作时，向后台传送数据
        //// </summary>
        //// <returns></returns>
        ////public string editSecMarketPrice()
        ////{
        ////    string reStr = "";
        ////    try
        ////    {
        ////        if (m_sFilterType == null)
        ////        {
        ////            reStr = (string)YssCore.Fun.ClsFunction.SendStringToServers(builderRowStr(), FAST.Core.Context.ClsContext.ConnPath + "/ucoserver?cmd=" + this.BeanId + "&flag=edit&showtype=listview1");
        ////        }
        ////        else
        ////        {
        ////            reStr = (string)YssCore.Fun.ClsFunction.SendStringToServers(builderRowStr() + "\r\t" + m_sFilterType.builderRowStr(), FAST.Core.Context.ClsContext.ConnPath + "/ucoserver?cmd=" + this.BeanId + "&flag=edit&showtype=listview1");
        ////        }
        ////    }
        ////    catch (Exception ex)
        ////    {
        ////        throw new YssCore.BaseCls.ClsBaseException(ex.Message, ex);
        ////    }
        ////    return reStr;
        ////}


        //// <summary>
        //// 执行删除操作时，向后台传送数据
        //// </summary>
        //// <returns></returns>
        ////public string delSecMarketPrice()
        ////{
        ////    string reStr = "";
        ////    try
        ////    {
        ////        if (m_sFilterType == null)
        ////        {
        ////            reStr = (string)YssCore.Fun.ClsFunction.SendStringToServers(builderRowStr(), FAST.Core.Context.ClsContext.ConnPath + "/ucoserver?cmd=" + this.BeanId + "&flag=del&showtype=listview1");
        ////        }
        ////        else
        ////        {
        ////            reStr = (string)YssCore.Fun.ClsFunction.SendStringToServers(builderRowStr() + "\r\t" + m_sFilterType.builderRowStr(), FAST.Core.Context.ClsContext.ConnPath + "/ucoserver?cmd=" + this.BeanId + "&flag=del&showtype=listview1");
        ////        }
        ////    }
        ////    catch (Exception ex)
        ////    {
        ////        throw new YssCore.BaseCls.ClsBaseException(ex.Message, ex);
        ////    }
        ////    return reStr;
        ////}


        //// <summary>
        //// 执行审核和反审核操作时，向后台传送数据
        //// </summary>
        //// <returns></returns>
        ////public string auditSecMarketPrice()
        ////{
        ////    string reStr = "";
        ////    try
        ////    {
        ////        if (m_sFilterType == null)
        ////        {
        ////            reStr = (string)YssCore.Fun.ClsFunction.SendStringToServers(builderRowStr(), FAST.Core.Context.ClsContext.ConnPath + "/ucoserver?cmd=" + this.BeanId + "&flag=audit&showtype=listview1");
        ////        }
        ////        else
        ////        {
        ////            reStr = (string)YssCore.Fun.ClsFunction.SendStringToServers(builderRowStr() + "\r\t" + m_sFilterType.builderRowStr(), FAST.Core.Context.ClsContext.ConnPath + "/ucoserver?cmd=" + this.BeanId + "&flag=audit&showtype=listview1");
        ////        }
        ////    }
        ////    catch (Exception ex)
        ////    {
        ////        throw new YssCore.BaseCls.ClsBaseException(ex.Message, ex);
        ////    }
        ////    return reStr;
        ////}


        //// <summary>
        //// 执行有条件查询操作时，向后台传送数据
        //// </summary>
        //// <returns></returns>
        ////public string filterSecMarketPrice()
        ////{
        ////    string reStr = "";
        ////    try
        ////    {
        ////        if (m_sFilterType == null)
        ////        {
        ////            reStr = (string)YssCore.Fun.ClsFunction.SendStringToServers(builderRowStr(), FAST.Core.Context.ClsContext.ConnPath + "/ucoserver?cmd=" + this.BeanId + "&flag=listview1&showtype=listview1");
        ////        }
        ////        else
        ////        {
        ////            reStr = (string)YssCore.Fun.ClsFunction.SendStringToServers(builderRowStr() + "\r\t" + m_sFilterType.builderRowStr(), FAST.Core.Context.ClsContext.ConnPath + "/ucoserver?cmd=" + this.BeanId + "&flag=listview1&showtype=listview1");
        ////        }
        ////    }
        ////    catch (Exception ex)
        ////    {
        ////        throw new YssCore.BaseCls.ClsBaseException(ex.Message, ex);
        ////    }
        ////    return reStr;
        ////}


    }
}


