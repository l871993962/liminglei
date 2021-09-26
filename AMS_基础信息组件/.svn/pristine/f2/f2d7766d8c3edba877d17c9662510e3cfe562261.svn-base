﻿using FAST.Core.Util;
using FAST.Common.Service.Pojo;
using FAST.Common.Service.Pojo.Base;
using FAST.Core.Exceptions;


using FAST.Core.Context;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using Newtonsoft.Json;
using FAST.Common.Service.Datastructure;

namespace YssInformation.Support.Bi.AssociationOrgan.Pojo
{
    /// <summary>
    /// 机构信息
    /// </summary>
    ////[NodeDesc(ParentNode = "C_DV_ORG_TYPE", TreeNode = "C_ORG_CODE")]
    [NodeDesc(ParentNode = "C_ORG_CODE_P", TreeNode = "C_ORG_CODE")]
    public class Org : AuditableParamPojo
    {
        /// <summary>
        ///  机构代码
        /// </summary>
        private string c_ORG_CODE = "";

        /// <summary>
        ///  机构内码(深圳通报文码)
        ///  STORY #43111 【海通证券】 关联机构设置新增机构内码字段，区分现有的机构代码字段  edit by sunyanlin 2017-07-04
        /// </summary>
        private string c_ORG_INNER_CODE = "";

        /// <summary>
        /// 机构名称
        /// </summary>
        private string c_ORG_NAME = "";

        /// <summary>
        ///  中文名称
        /// </summary>
        private string c_ORG_NAME_CN = "";

        /// <summary>
        ///  机构简称
        /// </summary>
        private string c_ORG_NAME_ST = "";

        /// <summary>
        ///  母公司
        /// </summary>
        private string c_ORG_CODE_P = "";

        /// <summary>
        ///  法人代表
        /// </summary>
        private string c_CORP_REP = "";

        /// <summary>
        /// 资本币种
        /// </summary>
        private string c_DC_CODE = "";

        /// <summary>
        ///  注册资本
        /// </summary>
        private double n_REG_CAP = 0;

        /// <summary>
        ///  注册地址
        /// </summary>
        private string c_REG_ADDR = "";

        /// <summary>
        ///  办公地址
        /// </summary>
        private string c_OFFIC_ADDR = "";

        /// <summary>
        ///   机构类型
        /// </summary>
        private string c_DV_ORG_TYPE = "";

        /// <summary>
        ///   公司代码
        /// </summary>
        private string c_CORP_CODE = "";

        /// <summary>
        ///  联系人
        /// </summary>
        private string c_LINK_MAN = "";

        /// <summary>
        /// 联系电话
        /// </summary>
        private string c_LINK_TEL = "";

        /// <summary>
        ///  手机号码
        /// </summary>
        private string c_MO_TEL = "";

        /// <summary>
        ///  电子邮箱
        /// </summary>
        private string c_EMAIL = "";

        /// <summary>
        ///   注册邮编
        /// </summary>
        private string c_REG_POST = "";

        /// <summary>
        ///   机构描述
        /// </summary>
        private string c_DESC = "";

        /// <summary>
        ///   办公邮编
        /// </summary>
        private string c_OFFIC_POST = "";

        ///<summary>
        ///  国际互联网地址
        ///  add by Yuntao Lau 2015.09.15 STORY #25681
        /// </summary>
        private string c_WWW_ADDR = "";

        ///<summary>
        ///  传真号码
        ///  add by Yuntao Lau 2015.09.15 STORY #25681
        /// </summary>
        private string c_FAX_TEL = "";

        /// <summary>
        ///  市场代码
        /// </summary>
        private string c_MKT_CODE = "";

        /// <summary>
        /// 管理人
        /// </summary>
        private string c_DV_MANAGER = "";

        /// <summary>
        /// 次管理人
        /// </summary>
        private string c_DV_MANAGER_SEC = "";

        /// <summary>
        /// 托管人
        /// </summary>
        private string c_DV_TRUSTEE = "";

        /// <summary>
        /// 次托管人
        /// </summary>
        private string c_DV_TRUSTEE_SEC = "";

        /// <summary>
        /// 担保人
        /// </summary>
        private string c_DV_WARRANTOR = "";

        /// <summary>
        /// 投资顾问
        /// </summary>
        private string c_DV_INVEST_ADVISER = "";

        /// <summary>
        /// 信托人
        /// </summary>
        private string c_DV_TRUSTEE_XT = "";

        /// <summary>
        /// 销售渠道
        /// </summary>
        private string c_DV_SALES_CHANNELS = "";

        /// <summary>
        /// 结算会员
        /// </summary>
        private string c_DV_CLEARING_MEMBER = "";

        /// <summary>
        /// 机构资质
        /// </summary>
        private string c_QUALIFICATION = "";

        /// <summary>
        /// c_PLACE_SETTLEMENT
        /// </summary>
        private string c_PLACE_SETTLEMENT = "";

        /// <summary>
        /// c_CLEAT_ACCOUNT
        /// </summary>
        private string c_CLEAR_ACCOUNT = "";

        /// <summary>
        /// c_BROKER_ID
        /// </summary>
        private string c_BROKER_ID = "";

        /// <summary>
        /// c_BROKER_NAME
        /// </summary>
        private string c_BROKER_NAME = "";

        /// <summary>
        /// c_BROKER_ID_TYPE
        /// </summary>
        private string c_BROKER_ID_TYPE = "";

        /// <summary>
        /// c_CLEAR_ID
        /// </summary>
        private string c_CLEARER_ID = "";

        /// <summary>
        /// c_CLEAR_NAME
        /// </summary>
        private string c_CLEARER_NAME = "";

        /// <summary>
        /// c_CLEAR_ID_TYPE
        /// </summary>
        private string c_CLEARER_ID_TYPE = "";

        /// <summary>
        /// 第3方委托
        /// </summary>
        private string c_DV_TRD_CLIENT = "";


        /// <summary>
        /// 委托人
        /// </summary>
        private string c_DV_CONSIGNER = "";

        /// <summary>
        /// 保险委托
        /// </summary>
        private string c_DV_BX_CLIENT = "";

        /// <summary>
        /// 受托人
        /// </summary>
        private string c_DV_DEPOSITARY = "";

        /// <summary>
        /// 机构logo
        /// </summary>
        private string c_LOGO_NAME = "";

        /// <summary>
        /// 大额支付号
        /// </summary>
        private string c_PAY_CODE = "";

        /// <summary>
        /// 联行号
        /// </summary>
        private string c_BANK_CODE = "";

        /// <summary>
        /// 单位电话
        /// STORY #48731 长江养老电子发票需求-委托人信息接口导入 add by qinxinglin 2018-1-11
        /// </summary>
        private string c_CORP_TEL = "";

        /// <summary>
        /// 开票类型
        /// STORY #48731 长江养老电子发票需求-委托人信息接口导入 add by qinxinglin 2018-1-11
        /// </summary>
        private string c_DV_INOVIC_TYPE = "";

        /// <summary>
        /// 机构属性
        /// liuxiang 2015-9-6 STORY #22255 保监会报表需要细化银行属性
        /// </summary>
        private string c_DV_ORG_ATTR = "";

        /// <summary>
        ///  成立时间
        ///  2016.2.25添加，by：MeiYuan
        ///  STORY #28264 机构基本信息 字段优化
        /// </summary>
        private string d_FOUND_TIME = "";

        /// <summary>
        ///  机构账户标号
        ///  2016.10.19添加，by：HuangJin
        ///  STORY #34371 关联机构设置其他信息增加托管账户编号需求
        /// </summary>
        private string c_TG_ACCOUNT_CODE = "";

        /// <summary>
        ///  发行人
        ///  @author wangyaokang STORY #29358 【中行】产品创设及发行（产品要素改造）
        /// </summary>
        private string c_DV_ISSUER = "";

        /// <summary>
        /// 20160330 added by liubo.STORY #29359 【中行】交易对手方、评级机构的维护
        /// 对手方
        /// </summary>
        private string c_DV_COUNTERPARTY = "";

        /// <summary>
        /// 20160411 added by liubo.STORY #29359 【中行】交易对手方、评级机构的维护
        /// 行业类别
        /// By Jinghehe 2017-8-11 原字段修改成  C_PLATE_CODE 前台list 界面可以代码转名称，否则无法进行转换 
        /// </summary>
        private string c_PLATE_CODE = "";

        /// <summary>
        /// c_BROKER_ACCOUNT 2016-5-10  addby wsm STORY30235 【招商基金母公司】[QDII]TradeBlotter_EXP TradeBlotter招行导出接口
        /// </summary>
        private string c_BROKER_ACCOUNT = "";

        /// <summary>
        /// 20160727 added by heliang.STORY #30646 【云平台】万联证券关联机构设置增加外包服务机构类型
        /// 外包服务机构
        /// </summary>
        private string c_DV_WBFWJG = "";

        /// <summary>
        /// Author : ChenLong
        /// Date   : 2016-11-22
        /// Status : Add
        /// Comment: 电子对账
        /// </summary>
        private string c_ELEC_RECONCILIATION;

        /// <summary>
        /// 20170213 added by xuyuanhao.STORY #37444 针对中登FISP平台改造主动信息模块
        /// 法人代表证件代码
        /// </summary>
        private string c_REP_CARD_CODE = "";

        /// <summary>
        /// 20170213 added by xuyuanhao.STORY #37444 针对中登FISP平台改造主动信息模块
        /// 法人代表证件类型
        /// </summary>
        private string c_DV_REPCARD_TYPE = "";

        /// <summary>
        /// 20170213 added by xuyuanhao.STORY #37444 针对中登FISP平台改造主动信息模块
        /// 法人证件有效期
        /// </summary>
        private string d_CARD_VAL_DUR = "";

        /// <summary>
        /// 20170213 added by xuyuanhao.STORY #37444 针对中登FISP平台改造主动信息模块
        /// 法人证件有效期结束日期
        /// </summary>
        private string d_CARD_VAL_DUR_END = "";


        /// <summary>
        /// 20170213 added by xuyuanhao.STORY #37444 针对中登FISP平台改造主动信息模块
        /// 投管人代码
        /// </summary>
        private string c_ADMIN_CODE = "";

        /// <summary>
        /// 20170213 added by xuyuanhao.STORY #37444 针对中登FISP平台改造主动信息模块
        /// 投管人名称
        /// </summary>
        private string c_ADMIN_NAME = "";

        /// <summary>
        /// 20170213 added by xuyuanhao.STORY #37444 针对中登FISP平台改造主动信息模块
        /// 投管人性质
        /// </summary>
        private string c_ADMIN_NATURE = "";

        /// <summary>
        /// 20170213 added by xuyuanhao.STORY #37444 针对中登FISP平台改造主动信息模块
        /// 投资人证件类型
        /// </summary>
        private string c_IVT_CARD_TYPE = "";

        /// <summary>
        /// 20170213 added by xuyuanhao.STORY #37444 针对中登FISP平台改造主动信息模块
        /// 投资人证件号码
        /// </summary>
        private string c_IVT_CARD_NO = "";

        /// <summary>
        /// 20170213 added by xuyuanhao.STORY #37444 针对中登FISP平台改造主动信息模块
        /// 投资人证件号码有效期
        /// </summary>
        private string d_IVT_CARD_VALDUR = "";

        /// <summary>
        /// 20170213 added by xuyuanhao.STORY #37444 针对中登FISP平台改造主动信息模块
        /// 投资人证件号码有效期结束日期
        /// </summary>
        private string d_IVT_CARD_VALDUR_END = "";




        /// <summary>
        /// 20161210 added by mzy BUG #147121 【云平台万联证券】主体信息设置，资质为外包服务机构的，无法关联到组合 
        /// 主托管人
        /// </summary>
        private string c_DV_TRUSTEE_MA = "";

        /// <summary>
        /// 营销机构
        /// 20170113 added by HeLiang STORY #37662 光大银行新增自定义资产情况报表
        /// </summary>
        private string c_DV_MARKETING = "";

        /// <summary>
        /// STORY #108912 【大家资产】估值核算20210723---证券基本信息中新增融资人，担保人字段
        /// </summary>
        private string c_DV_RZR = "";

        /// <summary>
        /// add by zhoushuhang 20170519 BUG #160288 点击资本币种下拉框报错    
        /// 主体编码
        /// </summary>
        private string c_ORG_ENCODE = "";

        /// <summary>
        /// add by chenbo 20170802 STORY #44886 机构信息设置 -- 主体资质的系统存储优化    
        /// 主体资质汇总
        /// </summary>
        private string c_DV_SUM = "";

        /// <summary>
        /// STORY #43971 需求上海-[长江养老]金融资产管理平台V4.5[高]20170705（关联机构设置界面增加“纳税人识别号”文本框）
        /// 纳税人识别号
        /// </summary>
        private string c_TAXPAYER_NO = "";

        /// <summary>
        /// STORY #43971 需求上海-[长江养老]金融资产管理平台V4.5[高]20170705（关联机构设置界面增加“纳税人识别号”文本框）
        /// 纳税人类型
        /// </summary>
        private string c_DV_TAXPAYER_TYPE = "";

        /// <summary>
        /// STORY #43971 需求上海-[长江养老]金融资产管理平台V4.5[高]20170705（关联机构设置界面增加“纳税人识别号”文本框）
        /// 银行账户
        /// </summary>
        private string c_BANK_ACC = "";

        /// <summary>
        /// STORY #43971 需求上海-[长江养老]金融资产管理平台V4.5[高]20170705（关联机构设置界面增加“纳税人识别号”文本框）
        /// 开户银行
        /// </summary>
        private string c_DEPOSIT_BANK = "";

        /// <summary>
        /// STORY #43971 需求上海-[长江养老]金融资产管理平台V4.5[高]20170705（关联机构设置界面增加“纳税人识别号”文本框）
        /// 主管税务机关
        /// </summary>
        private string c_TAX_AUTHORITIES = "";

        /// <summary>
        /// 主体性质（默认为“金融机构”）
        /// STORY49170【招商证券】【营改增】银行间交易需要区分对手方
        /// liuxiang 2017-12-22
        /// </summary>
        private string c_DV_FIN_ORG = "JRJG";

        /// <summary>
        /// 岗位
        /// STORY #81326 【华宝兴业】关联结构设置 中联系信息维护调整
        /// </summary>
        private string c_POST_NAME = "";

        /// <summary>
        /// 联系人信息
        /// STORY #81326 【华宝兴业】关联结构设置 中联系信息维护调整
        /// </summary>
        private string c_LINKMAN_LIST = "";

        /// <summary>
        /// 备注
        /// add by guoguangyi 20171024 STORY #46387 【安信基金场外交易】主体信息界面增加备注字段
        /// </summary>
        private string c_REMARK = "";

        /// <summary>
        /// STORY #63833 关联机构设置中新增财汇机构代码和财汇机构简称字段 
        ///财汇机构代码
        /// add by neil 20191021 
        /// </summary>
        private string c_CH_ORG_CODE = "";

        /// <summary>
        ///  STORY #63833 关联机构设置中新增财汇机构代码和财汇机构简称字段 
        ///财汇机构名称
        /// add by neil 20171024
        /// </summary>
        private string c_CH_ORG_NAME = "";

        /// <summary>
        ///  关联席位开始日期
        /// </summary>
        private DateTime d_RELA_START;

        /// <summary>
        /// 关联席位结束日期
        /// </summary>
        private DateTime d_RELA_END;

        /// <summary>
        /// 是否为关联方
        /// STORY #51721 光大证券-监管类信息完善
        /// add by lujianhao 20180705 
        /// </summary>
        private string c_ISRElATED = "";

        /// <summary>
        /// 彭博代码
        /// STORY #103374 【中欧基金】【版本V1.300.7.0.20210228.0323】导给彭博的净值f5888inav.inc、现金余额f5888icsh.inc接口文件及TA申赎f5888icsh.inc文件
        /// </summary>
        private string c_PB_CODE = "";

        /// <summary>
        /// 彭博代码
        /// STORY #103374 【中欧基金】【版本V1.300.7.0.20210228.0323】导给彭博的净值f5888inav.inc、现金余额f5888icsh.inc接口文件及TA申赎f5888icsh.inc文件
        /// </summary>
        [JsonProperty(PropertyName = "c_PB_CODE")]
        public string C_PB_CODE
        {
            set { c_PB_CODE = value; }

            get { return c_PB_CODE; }
        }

        /// <summary>
        /// 深圳通编码（机构内码）
        /// add by zhangfengjun -- STORY #43111 【海通证券】 关联机构设置新增机构内码字段，区分现有的机构代码字段 
        /// 属性: c_ORG_INNER_CODE 
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_INNER_CODE")]
        public string C_ORG_INNER_CODE
        {
            set { c_ORG_INNER_CODE = value; }

            get { return c_ORG_INNER_CODE; }
        }
        /// <summary>
        /// 财汇机构代码
        /// </summary>
        [JsonProperty(PropertyName = "c_CH_ORG_CODE")]
        public string C_CH_ORG_CODE
        {
            set { c_CH_ORG_CODE = value; }

            get { return c_CH_ORG_CODE; }
        }

        /// <summary>
        /// 财汇机构名称
        /// </summary>
        [JsonProperty(PropertyName = "c_CH_ORG_NAME")]
        public string C_CH_ORG_NAME
        {
            set { c_CH_ORG_NAME = value; }

            get { return c_CH_ORG_NAME; }
        }

        /// <summary>
        /// 属性: c_DV_SUM 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_SUM")]
        public string C_DV_SUM
        {
            set { c_DV_SUM = value; }

            get { return c_DV_SUM; }
        }

        /// <summary>
        /// 属性: c_BROKER_ACCOUNT 
        /// </summary>
        [JsonProperty(PropertyName = "c_BROKER_ACCOUNT")]
        public string C_BROKER_ACCOUNT
        {
            set { c_BROKER_ACCOUNT = value; }

            get { return c_BROKER_ACCOUNT; }
        }

        /// <summary>
        /// 属性: 机构代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_CODE")]
        public string C_ORG_CODE
        {
            set { c_ORG_CODE = value; }

            get { return c_ORG_CODE; }
        }

        /// <summary>
        /// 属性: 机构名称 
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_NAME")]
        public string C_ORG_NAME
        {
            set { c_ORG_NAME = value; }

            get { return c_ORG_NAME; }
        }

        /// <summary>
        /// 属性: 中文名称 
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_NAME_CN")]
        public string C_ORG_NAME_CN
        {
            set { c_ORG_NAME_CN = value; }

            get { return c_ORG_NAME_CN; }
        }

        /// <summary>
        /// 属性: 机构简称 
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_NAME_ST")]
        public string C_ORG_NAME_ST
        {
            set { c_ORG_NAME_ST = value; }

            get { return c_ORG_NAME_ST; }
        }

        /// <summary>
        /// 属性: 母公司 
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_CODE_P")]
        public string C_ORG_CODE_P
        {
            set { c_ORG_CODE_P = value; }

            get { return c_ORG_CODE_P; }
        }

        /// <summary>
        /// 属性: 法人代表 
        /// </summary>
        [JsonProperty(PropertyName = "c_CORP_REP")]
        public string C_CORP_REP
        {
            set { c_CORP_REP = value; }

            get { return c_CORP_REP; }
        }

        /// <summary>
        /// 属性: 资本币种 
        /// </summary>
        [JsonProperty(PropertyName = "c_DC_CODE")]
        public string C_DC_CODE
        {
            set { c_DC_CODE = value; }

            get { return c_DC_CODE; }
        }

        /// <summary>
        /// 属性: 注册资本 
        /// </summary>
        [JsonProperty(PropertyName = "n_REG_CAP")]
        public double N_REG_CAP
        {
            set { n_REG_CAP = value; }

            get { return n_REG_CAP; }
        }

        /// <summary>
        /// 属性: 注册地址 
        /// </summary>
        [JsonProperty(PropertyName = "c_REG_ADDR")]
        public string C_REG_ADDR
        {
            set { c_REG_ADDR = value; }

            get { return c_REG_ADDR; }
        }

        /// <summary>
        /// 属性: 办公地址 
        /// </summary>
        [JsonProperty(PropertyName = "c_OFFIC_ADDR")]
        public string C_OFFIC_ADDR
        {
            set { c_OFFIC_ADDR = value; }

            get { return c_OFFIC_ADDR; }
        }

        /// <summary>
        /// 属性: 机构类型 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_ORG_TYPE")]
        public string C_DV_ORG_TYPE
        {
            set { c_DV_ORG_TYPE = value; }

            get { return c_DV_ORG_TYPE; }
        }

        /// <summary>
        /// 属性: 公司代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_CORP_CODE")]
        public string C_CORP_CODE
        {
            set { c_CORP_CODE = value; }

            get { return c_CORP_CODE; }
        }

        /// <summary>
        /// 属性: 联系人 
        /// </summary>
        [JsonProperty(PropertyName = "c_LINK_MAN")]
        public string C_LINK_MAN
        {
            set { c_LINK_MAN = value; }

            get { return c_LINK_MAN; }
        }

        /// <summary>
        /// 属性: 联系电话 
        /// </summary>
        [JsonProperty(PropertyName = "c_LINK_TEL")]
        public string C_LINK_TEL
        {
            set { c_LINK_TEL = value; }

            get { return c_LINK_TEL; }
        }

        /// <summary>
        /// 属性: 手机号码 
        /// </summary>
        [JsonProperty(PropertyName = "c_MO_TEL")]
        public string C_MO_TEL
        {
            set { c_MO_TEL = value; }

            get { return c_MO_TEL; }
        }

        /// <summary>
        /// 属性: 电子邮箱 
        /// </summary>
        [JsonProperty(PropertyName = "c_EMAIL")]
        public string C_EMAIL
        {
            set { c_EMAIL = value; }

            get { return c_EMAIL; }
        }

        /// <summary>
        /// 属性: 注册邮编 
        /// </summary>
        [JsonProperty(PropertyName = "c_REG_POST")]
        public string C_REG_POST
        {
            set { c_REG_POST = value; }

            get { return c_REG_POST; }
        }

        /// <summary>
        /// 属性: 机构描述 
        /// </summary>
        [JsonProperty(PropertyName = "c_DESC")]
        public string C_DESC
        {
            set { c_DESC = value; }

            get { return c_DESC; }
        }

        /// <summary>
        /// 属性: 办公邮编 
        /// </summary>
        [JsonProperty(PropertyName = "c_OFFIC_POST")]
        public string C_OFFIC_POST
        {
            set { c_OFFIC_POST = value; }

            get { return c_OFFIC_POST; }
        }

        ///<summary>
        /// 属性：国际互联网址
        /// add by Yuntao Lau 2015.09.15 STORY #25681
        /// </summary>
        [JsonProperty(PropertyName = "c_WWW_ADDR")]
        public string C_WWW_ADDR
        {
            set { c_WWW_ADDR = value; }

            get { return c_WWW_ADDR; }
        }

        ///<summary>
        /// 属性：传真号码
        /// add by Yuntao Lau 2015.09.15 STORY #25681
        /// </summary>
        [JsonProperty(PropertyName = "c_FAX_TEL")]
        public string C_FAX_TEL
        {
            set { c_FAX_TEL = value; }

            get { return c_FAX_TEL; }
        }

        /// <summary>
        /// 属性: 市场代码 
        /// </summary>
        [JsonProperty(PropertyName = "c_MKT_CODE")]
        public string C_MKT_CODE
        {
            set { c_MKT_CODE = value; }

            get { return c_MKT_CODE; }
        }

        /// <summary>
        /// 管理人
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_MANAGER")]
        public string C_DV_MANAGER
        {
            get { return c_DV_MANAGER; }
            set { c_DV_MANAGER = value; }
        }

        /// <summary>
        /// 次管理人
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_MANAGER_SEC")]
        public string C_DV_MANAGER_SEC
        {
            get { return c_DV_MANAGER_SEC; }
            set { c_DV_MANAGER_SEC = value; }
        }

        /// <summary>
        /// 托管人
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_TRUSTEE")]
        public string C_DV_TRUSTEE
        {
            get { return c_DV_TRUSTEE; }
            set { c_DV_TRUSTEE = value; }
        }

        /// <summary>
        /// 次托管人
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_TRUSTEE_SEC")]
        public string C_DV_TRUSTEE_SEC
        {
            get { return c_DV_TRUSTEE_SEC; }
            set { c_DV_TRUSTEE_SEC = value; }
        }

        /// <summary>
        /// 担保人
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_WARRANTOR")]
        public string C_DV_WARRANTOR
        {
            get { return c_DV_WARRANTOR; }
            set { c_DV_WARRANTOR = value; }
        }

        /// <summary>
        /// 投资顾问
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_INVEST_ADVISER")]
        public string C_DV_INVEST_ADVISER
        {
            get { return c_DV_INVEST_ADVISER; }
            set { c_DV_INVEST_ADVISER = value; }
        }

        /// <summary>
        /// 信托人
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_TRUSTEE_XT")]
        public string C_DV_TRUSTEE_XT
        {
            get { return c_DV_TRUSTEE_XT; }
            set { c_DV_TRUSTEE_XT = value; }
        }

        /// <summary>
        /// 销售渠道
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_SALES_CHANNELS")]
        public string C_DV_SALES_CHANNELS
        {
            get { return c_DV_SALES_CHANNELS; }
            set { c_DV_SALES_CHANNELS = value; }
        }

        /// <summary>
        /// 结算会员
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_CLEARING_MEMBER")]
        public string C_DV_CLEARING_MEMBER
        {
            get { return c_DV_CLEARING_MEMBER; }
            set { c_DV_CLEARING_MEMBER = value; }
        }

        /// <summary>
        /// 属性: 机构资质 
        /// </summary>
        [JsonProperty(PropertyName = "c_QUALIFICATION")]
        public string C_QUALIFICATION
        {
            get { return c_QUALIFICATION; }
            set { c_QUALIFICATION = value; }
        }

        /// <summary>
        /// c_PLACE_SETTLEMENT
        /// </summary>
        [JsonProperty(PropertyName = "c_PLACE_SETTLEMENT")]
        public string C_PLACE_SETTLEMENT
        {
            get { return c_PLACE_SETTLEMENT; }
            set { c_PLACE_SETTLEMENT = value; }
        }

        /// <summary>
        /// c_CLEAT_ACCOUNT
        /// </summary>
        [JsonProperty(PropertyName = "c_CLEAR_ACCOUNT")]
        public string C_CLEAR_ACCOUNT
        {
            get { return c_CLEAR_ACCOUNT; }
            set { c_CLEAR_ACCOUNT = value; }
        }

        /// <summary>
        /// c_BROKER_ID
        /// </summary>
        [JsonProperty(PropertyName = "c_BROKER_ID")]
        public string C_BROKER_ID
        {
            get { return c_BROKER_ID; }
            set { c_BROKER_ID = value; }
        }

        /// <summary>
        /// c_BROKER_NAME
        /// </summary>
        [JsonProperty(PropertyName = "c_BROKER_NAME")]
        public string C_BROKER_NAME
        {
            get { return c_BROKER_NAME; }
            set { c_BROKER_NAME = value; }
        }

        /// <summary>
        /// c_BROKER_ID_TYPE
        /// </summary>
        [JsonProperty(PropertyName = "c_BROKER_ID_TYPE")]
        public string C_BROKER_ID_TYPE
        {
            get { return c_BROKER_ID_TYPE; }
            set { c_BROKER_ID_TYPE = value; }
        }

        /// <summary>
        /// c_CLEAR_ID
        /// </summary>
        [JsonProperty(PropertyName = "c_CLEARER_ID")]
        public string C_CLEARER_ID
        {
            get { return c_CLEARER_ID; }
            set { c_CLEARER_ID = value; }
        }

        /// <summary>
        /// c_CLEAR_NAME
        /// </summary>
        [JsonProperty(PropertyName = "c_CLEARER_NAME")]
        public string C_CLEARER_NAME
        {
            get { return c_CLEARER_NAME; }
            set { c_CLEARER_NAME = value; }
        }

        /// <summary>
        /// c_CLEARER_ID_TYPE
        /// </summary>
        [JsonProperty(PropertyName = "c_CLEARER_ID_TYPE")]
        public string C_CLEARER_ID_TYPE
        {
            get { return c_CLEARER_ID_TYPE; }
            set { c_CLEARER_ID_TYPE = value; }
        }


        /// <summary>
        /// 保险委托
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_BX_CLIENT")]
        public string C_DV_BX_CLIENT
        {
            get { return c_DV_BX_CLIENT; }
            set { c_DV_BX_CLIENT = value; }
        }

        /// <summary>
        /// 第3方委托
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_TRD_CLIENT")]
        public string C_DV_TRD_CLIENT
        {
            get { return c_DV_TRD_CLIENT; }
            set { c_DV_TRD_CLIENT = value; }
        }

        /// <summary>
        /// 委托人
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_CONSIGNER")]
        public string C_DV_CONSIGNER
        {
            get { return c_DV_CONSIGNER; }
            set { c_DV_CONSIGNER = value; }
        }

        /// <summary>
        /// 受托人
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_DEPOSITARY")]
        public string C_DV_DEPOSITARY
        {
            get { return c_DV_DEPOSITARY; }
            set { c_DV_DEPOSITARY = value; }
        }

        /// <summary>
        /// 属性: 机构logo
        /// </summary>
        [JsonProperty(PropertyName = "c_LOGO_NAME")]
        public string C_LOGO_NAME
        {
            set { c_LOGO_NAME = value; }

            get { return c_LOGO_NAME; }
        }

        /// <summary>
        /// 属性:  大额支付号
        /// </summary>
        [JsonProperty(PropertyName = "c_PAY_CODE")]
        public string C_PAY_CODE
        {
            set { c_PAY_CODE = value; }

            get { return c_PAY_CODE; }
        }

        /// <summary>
        /// 属性: 联行号
        /// </summary>
        [JsonProperty(PropertyName = "c_BANK_CODE")]
        public string C_BANK_CODE
        {
            set { c_BANK_CODE = value; }

            get { return c_BANK_CODE; }
        }

        /// <summary>
        /// 机构属性
        /// liuxiang 2015-9-6 STORY #22255 保监会报表需要细化银行属性
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_ORG_ATTR")]
        public string C_DV_ORG_ATTR
        {
            get { return c_DV_ORG_ATTR; }
            set { c_DV_ORG_ATTR = value; }
        }

        /// <summary>
        /// 属性: 成立时间
        /// 2016.2.25添加，by：MeiYuan
        /// STORY #28264 机构基本信息 字段优化
        /// </summary>
        [JsonProperty(PropertyName = "d_FOUND_TIME")]
        public string D_FOUND_TIME
        {
            set { d_FOUND_TIME = value; }

            get { return d_FOUND_TIME; }
        }

        /// <summary>
        /// 属性: 发行人
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_ISSUER")]
        public string C_DV_ISSUER
        {
            set { c_DV_ISSUER = value; }

            get { return c_DV_ISSUER; }
        }

        /// <summary>
        /// 属性: 对手方
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_COUNTERPARTY")]
        public string C_DV_COUNTERPARTY
        {
            get { return c_DV_COUNTERPARTY; }
            set { c_DV_COUNTERPARTY = value; }
        }

        /// <summary>
        /// 属性: 行业类别
        /// </summary>
        [JsonProperty(PropertyName = "c_PLATE_CODE")]
        public string C_PLATE_CODE
        {
            get { return c_PLATE_CODE; }
            set { c_PLATE_CODE = value; }
        }

        /// <summary>
        /// 属性: 外包服务机构
        /// 20160727 added by HeLiang
        /// STORY #30646 【云平台】万联证券关联机构设置增加外包服务机构类型
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_WBFWJG")]
        public string C_DV_WBFWJG
        {
            get { return c_DV_WBFWJG; }
            set { c_DV_WBFWJG = value; }
        }

        /// <summary>
        /// 属性: 机构账户标号
        /// 2016.10.19添加，by：HuangJin
        /// STORY #34371 关联机构设置其他信息增加托管账户编号需求
        /// </summary>
        [JsonProperty(PropertyName = "c_TG_ACCOUNT_CODE")]
        public string C_TG_ACCOUNT_CODE
        {
            set { c_TG_ACCOUNT_CODE = value; }

            get { return c_TG_ACCOUNT_CODE; }
        }

        /// <summary>
        /// 20170213 added by xuyuanhao.STORY #37444 针对中登FISP平台改造主动信息模块
        /// 投管人代码
        /// </summary>
        [JsonProperty(PropertyName = "c_ADMIN_CODE")]
        public string C_ADMIN_CODE
        {
            get { return c_ADMIN_CODE; }
            set { c_ADMIN_CODE = value; }
        }

        /// <summary>
        /// 20170213 added by xuyuanhao.STORY #37444 针对中登FISP平台改造主动信息模块
        /// 投管人名称
        /// </summary>
        [JsonProperty(PropertyName = "c_ADMIN_NAME")]
        public string C_ADMIN_NAME
        {
            get { return c_ADMIN_NAME; }
            set { c_ADMIN_NAME = value; }
        }


        /// <summary>
        /// 20170213 added by xuyuanhao.STORY #37444 针对中登FISP平台改造主动信息模块
        /// 投资人证件类型
        /// </summary>
        [JsonProperty(PropertyName = "c_IVT_CARD_TYPE")]
        public string C_IVT_CARD_TYPE
        {
            get { return c_IVT_CARD_TYPE; }
            set { c_IVT_CARD_TYPE = value; }
        }

        /// <summary>
        /// 20170213 added by xuyuanhao.STORY #37444 针对中登FISP平台改造主动信息模块
        /// 投资人证件号码
        /// </summary>
        [JsonProperty(PropertyName = "c_IVT_CARD_NO")]
        public string C_IVT_CARD_NO
        {
            get { return c_IVT_CARD_NO; }
            set { c_IVT_CARD_NO = value; }
        }

        /// <summary>
        /// 20170213 added by xuyuanhao.STORY #37444 针对中登FISP平台改造主动信息模块
        /// 投资人证件号码有效期
        /// </summary>
        [JsonProperty(PropertyName = "d_IVT_CARD_VALDUR")]
        public string D_IVT_CARD_VALDUR
        {
            get { return d_IVT_CARD_VALDUR; }
            set { d_IVT_CARD_VALDUR = value; }
        }

        /// <summary>
        /// 20170213 added by xuyuanhao.STORY #37444 针对中登FISP平台改造主动信息模块
        /// 投管人性质
        /// </summary>
        [JsonProperty(PropertyName = "c_ADMIN_NATURE")]
        public string C_ADMIN_NATURE
        {
            get { return c_ADMIN_NATURE; }
            set { c_ADMIN_NATURE = value; }
        }

        /// <summary>
        /// 20170213 added by xuyuanhao.STORY #37444 针对中登FISP平台改造主动信息模块
        /// 法人代表证件代码
        /// </summary>
        [JsonProperty(PropertyName = "c_REP_CARD_CODE")]
        public string C_REP_CARD_CODE
        {
            get { return c_REP_CARD_CODE; }
            set { c_REP_CARD_CODE = value; }
        }

        /// <summary>
        /// 20170213 added by xuyuanhao.STORY #37444 针对中登FISP平台改造主动信息模块
        /// 法人代表证件类型
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_REPCARD_TYPE")]
        public string C_DV_REPCARD_TYPE
        {
            get { return c_DV_REPCARD_TYPE; }
            set { c_DV_REPCARD_TYPE = value; }
        }

        /// <summary>
        /// 20170213 added by xuyuanhao.STORY #37444 针对中登FISP平台改造主动信息模块
        /// 法人证件有效期
        /// </summary>
        [JsonProperty(PropertyName = "d_CARD_VAL_DUR")]
        public string D_CARD_VAL_DUR
        {
            get { return d_CARD_VAL_DUR; }
            set { d_CARD_VAL_DUR = value; }
        }

        /// <summary>
        /// 20170213 added by xuyuanhao.STORY #37444 针对中登FISP平台改造主动信息模块
        /// 法人证件有效期结束日期
        /// </summary>
        [JsonProperty(PropertyName = "d_CARD_VAL_DUR_END")]
        public string D_CARD_VAL_DUR_END
        {
            get { return d_CARD_VAL_DUR_END; }
            set { d_CARD_VAL_DUR_END = value; }
        }

        /// <summary>
        /// 20170213 added by xuyuanhao.STORY #37444 针对中登FISP平台改造主动信息模块
        /// 投资人证件号码有效期结束日期
        /// </summary>
        [JsonProperty(PropertyName = "d_IVT_CARD_VALDUR_END")]
        public string D_IVT_CARD_VALDUR_END
        {
            get { return d_IVT_CARD_VALDUR_END; }
            set { d_IVT_CARD_VALDUR_END = value; }
        }

        /// <summary>
        /// 电子对账
        /// </summary>
        [JsonProperty(PropertyName = "c_ELEC_RECONCILIATION")]
        public string C_ELEC_RECONCILIATION
        {
            get { return c_ELEC_RECONCILIATION; }
            set { c_ELEC_RECONCILIATION = value; }
        }

        /// <summary>
        /// 属性: 主托管人
        /// 20161210 added by mzy BUG #147121 【云平台万联证券】主体信息设置，资质为外包服务机构的，无法关联到组合 
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_TRUSTEE_MA")]
        public string C_DV_TRUSTEE_MA
        {
            get { return c_DV_TRUSTEE_MA; }
            set { c_DV_TRUSTEE_MA = value; }
        }

        /// <summary>
        /// 属性: 营销机构
        /// 20170113 added by HeLiang STORY #37662 光大银行新增自定义资产情况报表
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_MARKETING")]
        public string C_DV_MARKETING
        {
            get { return c_DV_MARKETING; }
            set { c_DV_MARKETING = value; }
        }

        /// <summary>
        /// STORY #108912 【大家资产】估值核算20210723---证券基本信息中新增融资人，担保人字段
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_RZR")]
        public string C_DV_RZR
        {
            get { return c_DV_RZR; }
            set { c_DV_RZR = value; }
        }

        /// <summary>
        /// 属性: 主体编码
        /// </summary>
        [JsonProperty(PropertyName = "c_ORG_ENCODE")]
        public string C_ORG_ENCODE
        {
            set { c_ORG_ENCODE = value; }

            get { return c_ORG_ENCODE; }
        }

        /// <summary>
        /// 属性: 纳税人识别号
        /// </summary>
        [JsonProperty(PropertyName = "c_TAXPAYER_NO")]
        public string C_TAXPAYER_NO
        {
            get { return c_TAXPAYER_NO; }
            set { c_TAXPAYER_NO = value; }
        }

        /// <summary>
        /// 属性: 纳税人类型
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_TAXPAYER_TYPE")]
        public string C_DV_TAXPAYER_TYPE
        {
            get { return c_DV_TAXPAYER_TYPE; }
            set { c_DV_TAXPAYER_TYPE = value; }
        }

        /// <summary>
        /// 属性: 银行账户
        /// </summary>
        [JsonProperty(PropertyName = "c_BANK_ACC")]
        public string C_BANK_ACC
        {
            get { return c_BANK_ACC; }
            set { c_BANK_ACC = value; }
        }

        /// <summary>
        /// 属性: 开户银行
        /// </summary>
        [JsonProperty(PropertyName = "c_DEPOSIT_BANK")]
        public string C_DEPOSIT_BANK
        {
            get { return c_DEPOSIT_BANK; }
            set { c_DEPOSIT_BANK = value; }
        }

        /// <summary>
        /// 属性: 主管税务机关
        /// </summary>
        [JsonProperty(PropertyName = "c_TAX_AUTHORITIES")]
        public string C_TAX_AUTHORITIES
        {
            get { return c_TAX_AUTHORITIES; }
            set { c_TAX_AUTHORITIES = value; }
        }

        /// <summary>
        /// 属性: 主管税务机关
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_FIN_ORG")]
        public string C_DV_FIN_ORG
        {
            get { return c_DV_FIN_ORG; }
            set { c_DV_FIN_ORG = value; }
        }

        /// 属性: 岗位
        /// </summary>
        [JsonProperty(PropertyName = "c_POST_NAME")]
        public string C_POST_NAME
        {
            get { return c_POST_NAME; }
            set { c_POST_NAME = value; }
        }

        /// <summary>
        /// 属性: 联系人信息
        /// </summary>
        [JsonProperty(PropertyName = "c_LINKMAN_LIST")]
        public string C_LINKMAN_LIST
        {
            get { return c_LINKMAN_LIST; }
            set { c_LINKMAN_LIST = value; }
        }

        /// <summary>
        /// 属性: 备注
        /// </summary>
        [JsonProperty(PropertyName = "c_REMARK")]
        public string C_REMARK
        {
            set { c_REMARK = value; }
            get { return c_REMARK; }
        }


        /// <summary>
        /// 属性: 单位电话
        /// </summary>
        [JsonProperty(PropertyName = "c_CORP_TEL")]
        public string C_CORP_TEL
        {
            get { return c_CORP_TEL; }
            set { c_CORP_TEL = value; }
        }

        /// <summary>
        /// 属性: 开票类型
        /// </summary>
        [JsonProperty(PropertyName = "c_DV_INOVIC_TYPE")]
        public string C_DV_INOVIC_TYPE
        {
            get { return c_DV_INOVIC_TYPE; }
            set { c_DV_INOVIC_TYPE = value; }
        }

        /// <summary>
        /// 属性: 关联机构开始日期 
        /// </summary>
        [JsonProperty(PropertyName = "d_RELA_START")]
        public DateTime D_RELA_START
        {
            set { d_RELA_START = value; }

            get { return d_RELA_START; }
        }

        /// <summary>
        /// 属性: 关联机构结束日期 
        /// </summary>
        [JsonProperty(PropertyName = "d_RELA_END")]
        public DateTime D_RELA_END
        {
            set { d_RELA_END = value; }

            get { return d_RELA_END; }
        }

        /// <summary>
        /// 属性: c_ISRElATED 
        /// </summary>
        [JsonProperty(PropertyName = "c_ISRElATED")]
        public string C_ISRElATED
        {
            set { c_ISRElATED = value; }
            get { return c_ISRElATED; }
        }
    }
}