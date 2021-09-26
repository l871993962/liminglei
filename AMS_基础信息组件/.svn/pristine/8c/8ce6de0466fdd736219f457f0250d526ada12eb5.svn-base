package com.yss.ams.base.information.modules.bi.org.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * 机构  操作数据库SQL生成类
 * @author 马向峰  拆分  20170531
 *
 */
public class OrgSqlBuilder implements SQLBuilder {

	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" select count(*) as CNT from T_P_BI_ORG a ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" where ").append(valueFieldbuf);
			}
			buf.append("   order by a.N_CHECK_STATE asc,c_update_time desc,c_check_time desc,c_iden   ");
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return retSql;
	}

	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" select a.C_ORG_CODE from T_P_BI_ORG a ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" where ").append(valueFieldbuf);
			}
			buf.append("   order by a.N_CHECK_STATE     ");
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return retSql;
	}

	public String buildDeleteSql(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildInsertSql(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildSelectSql(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildUpdateSql(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbnameresolver,
			String s) {
		// TODO Auto-generated method stub
		return dbnameresolver.getColumnName(OrgColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(OrgTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(OrgTableName.userInfo);
	}

	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_ORG_CODE")) {
				valueFieldbuf.append(" a.C_ORG_CODE like ? AND ");
			} else if (fieldedName.equals(("C_ORG_NAME"))) {
				valueFieldbuf.append("  a.C_ORG_NAME like ? AND ");
			} else if (fieldedName.equals("C_ORG_NAME_ST")) {
				valueFieldbuf.append(" C_ORG_NAME_ST like ?  AND ");
			} else if (fieldedName.equals("ARRAY_C_DV_ORG_TYPE")) {
				valueFieldbuf
						.append(" a.C_DV_ORG_TYPE IN (SELECT * FROM TABLE(?)) AND ");
			} else if(fieldedName.equals("C_DV_TRUSTEE")){
				valueFieldbuf.append(" a.C_DV_TRUSTEE = ? AND ");
			} else if(fieldedName.equals("C_DV_CONSIGNER")){
				valueFieldbuf.append(" a.C_DV_CONSIGNER = ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append("a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("ARRAY_C_IDEN")){
				valueFieldbuf.append("a.C_IDEN IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("N_STATE")){
				/**
				 * 产品生命周期系统查询客户端状态为-1的数据，(-1为生命周期系统存储的数据)
				 * add by qkw 2017-12-18
				 */
				valueFieldbuf.append("a.N_CHECK_STATE = ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				valueFieldbuf.append("a.C_DEL_TIME <= ? AND ");
			} else if (fieldedName.equals("ARRAY_C_QUALIFICATION")){
				valueFieldbuf.append(" (a.C_DV_MANAGER IN (SELECT * FROM TABLE(?)) OR ");
				valueFieldbuf.append(" a.C_DV_MANAGER_SEC IN (SELECT * FROM TABLE(?)) OR ");
				valueFieldbuf.append(" a.C_DV_TRUSTEE IN (SELECT * FROM TABLE(?)) OR ");
				valueFieldbuf.append(" a.C_DV_TRUSTEE_SEC IN (SELECT * FROM TABLE(?)) OR ");
				valueFieldbuf.append(" a.C_DV_WARRANTOR IN (SELECT * FROM TABLE(?)) OR ");
				valueFieldbuf.append(" a.C_DV_INVEST_ADVISER IN (SELECT * FROM TABLE(?)) OR ");
				valueFieldbuf.append(" a.C_DV_TRUSTEE_XT IN (SELECT * FROM TABLE(?)) OR ");
				valueFieldbuf.append(" a.C_DV_SALES_CHANNELS IN (SELECT * FROM TABLE(?)) OR ");
				valueFieldbuf.append(" a.C_DV_DEPOSITARY IN (SELECT * FROM TABLE(?)) OR ");
				valueFieldbuf.append(" a.C_DV_CONSIGNER IN (SELECT * FROM TABLE(?)) OR "); // add by zzk 20151014 STORY #25788
				valueFieldbuf.append(" a.C_DV_ISSUER IN (SELECT * FROM TABLE(?)) OR "); // add by wangyaokang STORY #29358 【中行】产品创设及发行（产品要素改造）
				valueFieldbuf.append(" a.C_DV_COUNTERPARTY IN (SELECT * FROM TABLE(?)) OR "); // 20160330 added by liubo.STORY #29359 【中行】交易对手方、评级机构的维护.对手方
				
				valueFieldbuf.append(" a.C_DV_WBFWJG IN (SELECT * FROM TABLE(?)) OR "); //20160727 added by heliang.STORY #30646 【云平台】万联证券关联机构设置增加外包服务机构类型
				valueFieldbuf.append(" a.C_DV_TRUSTEE_MA IN (SELECT * FROM TABLE(?)) OR "); //20161210 added by mzyBUG #147121 【云平台万联证券】主体信息设置，资质为外包服务机构的，无法关联到组合
				valueFieldbuf.append(" a.C_DV_BX_CLIENT IN (SELECT * FROM TABLE(?)) OR "); //20161210 added by mzyBUG #147121 【云平台万联证券】主体信息设置，资质为外包服务机构的，无法关联到组合 
				valueFieldbuf.append(" a.C_DV_TRD_CLIENT IN (SELECT * FROM TABLE(?)) OR "); //20161210 added by mzyBUG #147121 【云平台万联证券】主体信息设置，资质为外包服务机构的，无法关联到组合
				valueFieldbuf.append(" a.C_DV_MARKETING IN (SELECT * FROM TABLE(?)) OR "); //20170113 added by HeLiang STORY #37662 光大银行新增自定义资产情况报表
				/*
				 * Author : ChenLong
				 * Date   : 2016-11-22
				 * Status : Add
				 * Comment: 电子对账
				 * */
				valueFieldbuf.append(" a.c_elec_reconciliation in (select * from table(?)) or");
				valueFieldbuf.append(" a.C_DV_CLEARING_MEMBER IN (SELECT * FROM TABLE(?)) or");
				
				valueFieldbuf.append(" PKG_FUN_ISCONTAIN.ISCONTAIN(a.C_DV_SUM,?,',',',') =1 ) AND ");
			} else if (fieldedName.equals(("ARRAY_C_PORT_CODE"))) {
				valueFieldbuf.append(" 1 = 0 AND ");
			} 
			/// 注释代码，废弃，查询条件不是这样书写 BY Jinghehe 2017-9-9 
			// ta清算 费用结算查询直销代销机构 by lixiang@ysstech.com
//			else if(fieldedName.equals("ZXDX")){
//				valueFieldbuf.append(" a.n_check_state=1 and (a.C_DV_SUM like '%AGENCY_SALES%' or a.C_DV_SUM like '%DIRECT_SALES%') and ");
//			}
		}
		
		//BUG #189208 【机构信息】-机构界面查询条件未对子类机构进行开放
		//valueFieldbuf.append( " TRIM(A.C_ORG_CODE_P) IS NULL ");
		valueFieldbuf.append( "  1 = 1  ");
	}

	public String getOrgExtendColumnName(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(OrgExtendColumnName.valueOf(name));
	}

	public String getPortRelaOrgSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT C.* ");
		buf.append("   FROM (SELECT c_org_code, c_org_name,  case when c_dv_org_type = 'ORG_JJGS' then '基金公司' ");
		buf.append("   when c_dv_org_type = 'ORG_BXGS' then '保险公司'  when c_dv_org_type = 'ORG_CWGS' then '财务公司' ");
		buf.append("   when c_dv_org_type = 'ORG_JYS_QH' then '期货交易所' ");
	    buf.append("   when c_dv_org_type = 'ORG_JYS_ZQ' then '证券交易所' ");
		buf.append("   when c_dv_org_type = 'ORG_QHGS' then '期货公司（经纪公司）' ");
		buf.append("   when c_dv_org_type = 'ORG_QT' then '其他企业单位' ");
		buf.append("   when c_dv_org_type = 'ORG_SB' then '全国社保基金' ");
		buf.append("   when c_dv_org_type = 'ORG_SYYH' then '商业银行' ");
		buf.append("   when c_dv_org_type = 'ORG_XTGS' then '信托公司' ");
		buf.append("   when c_dv_org_type = 'ORG_ZCGL' then '资产管理公司（保险）' ");
		buf.append("   when c_dv_org_type = 'ORG_ZQDJJG' then '证券登记机构' ");
		buf.append("   when c_dv_org_type = 'ORG_ZQGS' then '证券公司' end c_dv_org_type, ");                        
		buf.append("                c_org_name_st,  ");//add by Sunhe STORY #28914  20160222
		buf.append("                c_org_name_cn,  c_mkt_code, nvl(trim(C_ORG_CODE_P),C_DV_ORG_TYPE) C_ORG_CODE_P,  n_reg_cap,  c_dc_code,  ");
		buf.append("                c_corp_rep,  c_corp_code,  c_link_man,  c_link_tel,   c_mo_tel,  ");
		buf.append("                c_email,  c_reg_addr, c_offic_addr,  c_reg_post,  c_offic_post,  ");
		buf.append("                c_desc,  n_check_state,  c_update_by,  c_update_time,  ");
		buf.append("                c_dv_manager, C_DV_MANAGER_SEC, c_dv_trustee,  c_dv_trustee_sec,  c_dv_warrantor,  ");
		buf.append("                c_dv_invest_adviser,  c_dv_trustee_xt,  c_dv_sales_channels,  c_dv_clearing_member,  ");
		buf.append("                C_PLACE_SETTLEMENT,  C_CLEAR_ACCOUNT,  C_BROKER_ID,  C_BROKER_NAME,  ");
		buf.append("                C_BROKER_ID_TYPE,  C_CLEARER_ID,  C_CLEARER_NAME,  C_CLEARER_ID_TYPE,  ");
		buf.append("                c_check_by,  c_check_time,  c_iden , C_BANK_CODE,c_dv_bx_client,C_DV_TRD_CLIENT, C_DV_DEPOSITARY, C_LOGO_NAME ,C_PAY_CODE,A.C_DV_ORG_ATTR,C_DV_CONSIGNER, ");//liuxiang 2015-9-6 STORY #22255 保监会报表需要细化银行属性 //zzk 20151014 STORY #25788
		buf.append("                C_WWW_ADDR, C_FAX_TEL, "); //add by Yuntao Lau 2015.09.15 STORY #25681
		buf.append("                C_DV_ISSUER, D_FOUND_TIME, "); //add by wangyaokang STORY #29358 【中行】产品创设及发行（产品要素改造）
		buf.append("                C_BROKER_ACCOUNT , ");//add by  2016-5-10   wsm STORY30235 【招商基金母公司】[QDII]TradeBlotter_EXP TradeBlotter招行导出接口
		buf.append("                c_DV_WBFWJG, "); //20160727 added by heliang.STORY #30646 【云平台】万联证券关联机构设置增加外包服务机构类型
		buf.append("                C_DV_TRUSTEE_MA, "); //20161210 added by mzy BUG #147121 【云平台万联证券】主体信息设置，资质为外包服务机构的，无法关联到组合
		buf.append("                C_DV_MARKETING, "); //20170113 added by HeLiang STORY #37662 光大银行新增自定义资产情况报表
		/*
		 * Author : ChenLong
		 * Date   : 2016-11-22
		 * Status : Add
		 * Comment: 电子对账
		 * */
		buf.append("  				c_elec_reconciliation,");
		buf.append("                c_DV_COUNTERPARTY,C_INDUSTRY_TYPE "); //20160330 added by liubo.STORY #29359 【中行】交易对手方、评级机构的维护.对手方、行业类别
	    buf.append("                C_ADMIN_CODE, C_ADMIN_NATURE,C_ADMIN_NAME, C_IVT_CARD_NO,  D_IVT_CARD_VALDUR,  D_IVT_CARD_VALDUR_END,  C_IVT_CARD_TYPE,  C_REP_CARD_CODE,  C_DV_REPCARD_TYPE,  D_CARD_VAL_DUR,  D_CARD_VAL_DUR_END "); //STORY37444针对中登FISP平台改造主动信息模块  add by xuyuanhao 2017-2-14
		// STORY #51721 光大证券-监管类信息完善 add by lujianhao 20180705
		buf.append(", C_ISRElATED ");
	    buf.append("           FROM T_P_BI_ORG A ");
		buf.append("          WHERE NOT EXISTS (SELECT * ");
		buf.append("                   FROM T_P_AB_PORT_RELA B ");
		buf.append("                  WHERE A.C_ORG_CODE = B.C_RELA_CODE ");
		buf.append("                    AND B.C_PORT_CODE =? ");
		buf.append("                    AND B.C_DV_TYPE_CODE = ? ");
		buf.append("                    AND B.N_CHECK_STATE >= 0) ");
		buf.append(" AND  ( A.C_DV_MANAGER = ? OR ");
		buf.append(" a.C_DV_MANAGER_SEC = ?  OR ");
		buf.append(" a.C_DV_TRUSTEE = ?  OR ");
		buf.append(" a.C_DV_TRUSTEE_SEC = ?  OR ");
		buf.append(" a.C_DV_WARRANTOR = ?  OR ");
		buf.append(" a.C_DV_INVEST_ADVISER = ?  OR ");
		buf.append(" a.C_DV_TRUSTEE_XT = ?  OR ");
		buf.append(" a.C_DV_SALES_CHANNELS = ?  OR ");
		buf.append(" a.C_DV_DEPOSITARY = ?  OR ");
		buf.append(" a.C_DV_CLEARING_MEMBER = ?  OR ");

		buf.append(" a.C_DV_CONSIGNER = ?  OR "); // zzk 20151014  STORY #25788
		buf.append(" a.C_DV_ISSUER = ?  or "); // add by wangyaokang STORY #29358 【中行】产品创设及发行（产品要素改造）
		/*
		 * Author : ChenLong
		 * Date   : 2016-11-22
		 * Status : Add
		 * Comment: 电子对账
		 * */
		buf.append(" a.c_elec_reconciliation = ? or");
		buf.append(" a.C_DV_WBFWJG = ?  or "); //20160727 added by heliang.STORY #30646 【云平台】万联证券关联机构设置增加外包服务机构类型
		buf.append(" a.C_DV_TRD_CLIENT = ?  or a.C_DV_BX_CLIENT = ?  or a.C_DV_TRUSTEE_MA = ? or ");//BUG #147121 【云平台万联证券】主体信息设置，资质为外包服务机构的，无法关联到组合
		buf.append(" a.C_DV_MARKETING = ?  or "); //20170113 added by HeLiang STORY #37662 光大银行新增自定义资产情况报表
		buf.append(" a.C_DV_COUNTERPARTY = ?  ) "); //20160330 added by liubo.STORY #29359 【中行】交易对手方、评级机构的维护.对手方
		buf.append("            AND A.N_CHECK_STATE = 1 ");
		buf.append("         UNION ALL ");
		buf.append("         SELECT C_DV_CODE AS C_ORG_CODE, C_DV_NAME AS C_ORG_NAME, C_DV_NAME AS C_DV_ORG_TYPE, "); //edt by sunhe STORY#28914 
		// BUG #131260 【紧急】【招商财富】产品基本信息界面做关联机构设置时点击保存按钮没有任何反应也保存不了
		// xiaozhilong 20160520
		// 没什么好注释的，sql写完各种语法错误，改对了。c_org_code_p为父节点，不设置成[root]前台逻辑无法正确执行
		buf.append("                ' ' as c_org_name_st, ' ' as c_org_name_cn, ' ' as c_mkt_code, '[root]' as c_org_code_p, ");
		buf.append("                0 as n_reg_cap,  ' ' as c_dc_code,  ' ' as c_corp_rep, ' ' as c_corp_code, ");
		buf.append("                ' ' as c_link_man, ' ' as c_link_tel, ' ' as c_mo_tel, ' ' as c_email, ");
		buf.append("                ' ' as c_reg_addr, ' ' as c_offic_addr, ' ' as c_reg_post, ' ' as c_offic_post, ");
		buf.append("                ' ' as c_desc, 1 AS N_CHECK_STATE,  ' ' as c_update_by, ' ' as c_update_time, ");
		buf.append("                ' ' c_dv_manager, '' C_DV_MANAGER_SEC, ' ' c_dv_trustee, ' ' c_dv_trustee_sec, ' ' c_dv_warrantor,  ");
		buf.append("                ' ' c_dv_invest_adviser, ' ' c_dv_trustee_xt, ' ' c_dv_sales_channels, ' ' c_dv_clearing_member,  ");
		buf.append("                ' ' C_PLACE_SETTLEMENT,  ' ' C_CLEAR_ACCOUNT, ' ' C_BROKER_ID,  ' ' C_BROKER_NAME,  ");
		buf.append("                ' ' C_BROKER_ID_TYPE,  ' ' C_CLEARER_ID,  ' ' C_CLEARER_NAME,  ' ' C_CLEARER_ID_TYPE,  ");
		buf.append("                ' ' as c_check_by, ' ' as c_check_time, ' ' as c_iden , ");
		buf.append("                ' '  C_BANK_CODE , ' ' c_dv_bx_client, ' ' C_DV_TRD_CLIENT,' ' C_DV_DEPOSITARY, ' ' C_LOGO_NAME , ' ' C_PAY_CODE,' ' C_DV_ORG_ATTR,' ' C_DV_CONSIGNER, ");//liuxiang 2015-9-6 STORY #22255 保监会报表需要细化银行属性//zzk 20151014 STORY #25788
		buf.append("                ' ' AS C_WWW_ADDR, ' ' AS C_FAX_TEL, "); //add by Yuntao Lau 2015.09.15 STORY #25681
		buf.append("                ' ' AS C_DV_ISSUER, NULL AS D_FUND_TIME, "); //add by wangyaokang STORY #29358 【中行】产品创设及发行（产品要素改造）
		buf.append("                ' '  AS C_BROKER_ACCOUNT,");// add by wsm 2016-5-10 STORY30235 【招商基金母公司】[QDII]TradeBlotter_EXP TradeBlotter招行导出接口
		buf.append("                ' '  AS c_DV_WBFWJG,"); //20160727 added by heliang.STORY #30646 【云平台】万联证券关联机构设置增加外包服务机构类型
		buf.append("                ' '  AS C_DV_TRUSTEE_MA,"); //20161210 added by mzy BUG #147121 【云平台万联证券】主体信息设置，资质为外包服务机构的，无法关联到组合
		buf.append("                ' '  AS C_DV_MARKETING,"); //20170113 added by HeLiang STORY #37662 光大银行新增自定义资产情况报表
		buf.append("                ' ' AS c_DV_COUNTERPARTY, ' ' as C_INDUSTRY_TYPE, "); //20160330 added by liubo.STORY #29359 【中行】交易对手方、评级机构的维护.对手方、行业类别
	    buf.append("                 ' ' as C_ADMIN_CODE, ' ' as C_ADMIN_NATURE, ' ' as C_ADMIN_NAME, ' ' as C_IVT_CARD_NO, to_date(to_char(sysdate, 'yyyy-MM-dd')，'yyyy-MM-dd') as  D_IVT_CARD_VALDUR, to_date(to_char(sysdate, 'yyyy-MM-dd')，'yyyy-MM-dd') as  D_IVT_CARD_VALDUR_END, ' ' as  C_IVT_CARD_TYPE, ' ' as  C_REP_CARD_CODE, ' ' as  C_DV_REPCARD_TYPE, to_date(to_char(sysdate, 'yyyy-MM-dd')，'yyyy-MM-dd') as  D_CARD_VAL_DUR,to_date(to_char(sysdate, 'yyyy-MM-dd')，'yyyy-MM-dd') as  D_CARD_VAL_DUR_END,' ' AS C_ISRElATED "); //STORY37444针对中登FISP平台改造主动信息模块  add by xuyuanhao 2017-2-14
		buf.append("           FROM v_s_dv_voc voc ");
	
		buf.append("  WHERE exists(select '1' from  (SELECT  nvl(trim(C_ORG_CODE_P), C_DV_ORG_TYPE) C_ORG_CODE_P ");
        buf.append("   FROM T_P_BI_ORG A ");
        buf.append("   WHERE  not EXISTS ");
        buf.append("   (SELECT *   ");
        buf.append("            FROM T_P_AB_PORT_RELA B  ");
        buf.append("          WHERE A.C_ORG_CODE = B.C_RELA_CODE ");
        buf.append("            AND B.C_PORT_CODE = ? ");
        buf.append("            AND B.C_DV_TYPE_CODE = ? ");
        buf.append("            AND B.N_CHECK_STATE >= 0) ");
        buf.append("     AND (A.C_DV_MANAGER = ? or a.C_DV_MANAGER_SEC = ?  OR a.C_DV_TRUSTEE = ?  OR ");
        /*
		 * Author : ChenLong
		 * Date   : 2016-09-20
		 * Status : Add
		 * Comment: 委托人条件
		 * */
		buf.append(" 		 a.c_dv_consigner = ? or");
        buf.append("         a.C_DV_TRUSTEE_SEC = ?  OR a.C_DV_WARRANTOR = ?  OR ");
        buf.append("         a.C_DV_INVEST_ADVISER = ?  OR a.C_DV_TRUSTEE_XT = ?  OR ");
        buf.append("         a.C_DV_SALES_CHANNELS = ?  OR a.C_DV_DEPOSITARY = ?  OR ");
      //BUG #147121 【云平台万联证券】主体信息设置，资质为外包服务机构的，无法关联到组合 
        buf.append("         a.C_DV_WBFWJG = ? OR a.C_DV_CONSIGNER = ? OR a.C_DV_ISSUER = ? or a.C_DV_COUNTERPARTY = ? or a.C_DV_TRD_CLIENT = ?  or a.C_DV_BX_CLIENT = ?  or a.C_DV_TRUSTEE_MA = ? or");
        buf.append("         a.C_DV_MARKETING = ? OR "); // 20170113 added by HeLiang STORY #37662 光大银行新增自定义资产情况报表
		/*
		 * Author : ChenLong
		 * Date   : 2016-11-22
		 * Status : Add
		 * Comment: 电子对账
		 * */
		buf.append(" 		a.c_elec_reconciliation = ? or");
        buf.append("        a.C_DV_CLEARING_MEMBER = ? )) a where a.C_ORG_CODE_P = voc.C_DV_CODE )) C ");//add by Sunhe STORY #28914  20160222
		buf.append("  START WITH C.C_ORG_CODE IN ");
		buf.append("             (SELECT C_DV_ORG_TYPE FROM T_P_BI_ORG WHERE N_CHECK_STATE = 1) ");
		buf.append(" CONNECT BY PRIOR C.C_ORG_CODE = C.C_ORG_CODE_P ");

		return buf.toString();
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(OrgTableName.userInfo);
	}
	
	/*START 数据服务方法*/
	
	public String getAllDataSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getCommonQuerySqlListBuf(buf);
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getDataByCode(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		
		buf.append(" where ");
		buf.append(" C_ORG_CODE = ? ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getAllDataSqlByKeys(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		
		buf.append(" where ");
		buf.append(" C_ORG_CODE IN (SELECT * FROM TABLE(?)) ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getDataListByTypes(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getCommonQuerySqlBuf(buf);
		
		buf.append(" where ");
		buf.append(" C_DV_ORG_TYPE ");
		buf.append(" IN (SELECT * FROM TABLE(?)) ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getHeadDataListByTypes(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getCommonQuerySqlBuf(buf);
		
		buf.append(" where ");
		buf.append(" C_DV_ORG_TYPE ");
		buf.append(" IN (SELECT * FROM TABLE(?)) ");
		buf.append(" and C_ORG_CODE_P is null ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getHeadDataListByZtzz() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT * ");
		buf.append(" FROM T_P_BI_ORG A ");
		buf.append(" WHERE A.N_CHECK_STATE = 1 ");
		buf.append(" AND (A.C_DV_MANAGER IN (SELECT * FROM TABLE(?)) OR ");
		buf.append(" A.C_DV_TRUSTEE IN (SELECT * FROM TABLE(?)) OR ");
		buf.append(" A.C_DV_TRUSTEE_SEC IN (SELECT * FROM TABLE(?)) OR ");
		buf.append(" A.C_DV_WARRANTOR IN (SELECT * FROM TABLE(?)) OR ");
		buf.append(" A.C_DV_INVEST_ADVISER IN (SELECT * FROM TABLE(?)) OR ");
		buf.append(" A.C_DV_TRUSTEE_XT IN (SELECT * FROM TABLE(?)) OR ");
		buf.append(" A.C_DV_SALES_CHANNELS IN (SELECT * FROM TABLE(?)) OR ");
		buf.append(" A.C_DV_DEPOSITARY IN (SELECT * FROM TABLE(?)) OR ");
		buf.append(" A.C_DV_CONSIGNER IN (SELECT * FROM TABLE(?)) OR ");
		buf.append(" A.C_DV_ISSUER IN (SELECT * FROM TABLE(?)) OR ");
		buf.append(" A.C_DV_COUNTERPARTY IN (SELECT * FROM TABLE(?)) OR ");
		buf.append(" A.C_DV_WBFWJG IN (SELECT * FROM TABLE(?)) OR ");
		buf.append(" A.C_DV_TRUSTEE_MA IN (SELECT * FROM TABLE(?)) OR ");
		buf.append(" A.C_DV_BX_CLIENT IN (SELECT * FROM TABLE(?)) OR ");
		buf.append(" A.C_DV_TRD_CLIENT IN (SELECT * FROM TABLE(?)) OR ");
		buf.append(" A.C_DV_MARKETING IN (SELECT * FROM TABLE(?)) OR ");
		buf.append(" A.C_ELEC_RECONCILIATION IN (SELECT * FROM TABLE(?)) OR ");
		buf.append(" A.C_DV_CLEARING_MEMBER IN (SELECT * FROM TABLE(?)) OR ");
		buf.append(" PKG_FUN_ISCONTAIN.ISCONTAIN(A.C_DV_SUM, ?, ',', ',') = 1) ");
//		buf.append(" AND 1 = 1 ");
//		buf.append(" ORDER BY A.N_CHECK_STATE ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	private void getCommonQuerySqlListBuf(StringBuffer buf){
	
		buf.append(" select b.C_DV_ORG_TYPE, '' as C_P_CODE, ");
		buf.append(" b.C_ORG_CODE, b.C_ORG_NAME, b.C_ORG_NAME_ST, ");
		buf.append(" b.C_ORG_NAME_CN, b.C_MKT_CODE, b.C_ORG_CODE_P, ");
		buf.append(" b.N_REG_CAP, b.C_DC_CODE, b.C_CORP_REP,");
		buf.append(" b.C_CORP_CODE, b.C_LINK_MAN, b.C_LINK_TEL, ");
		buf.append(" b.C_MO_TEL,b.C_EMAIL,b.C_REG_ADDR, b.C_OFFIC_ADDR, ");
		buf.append(" b.C_REG_POST,b.C_OFFIC_POST,b.C_DESC, b.N_CHECK_STATE, ");
		buf.append(" b.C_UPDATE_BY, b.C_UPDATE_TIME, b.C_CHECK_BY, b.C_CHECK_TIME, ");
		buf.append(" b.C_DV_MANAGER, b.C_DV_MANAGER_SEC, b.C_DV_TRUSTEE, b.C_DV_TRUSTEE_SEC, b.C_DV_WARRANTOR, ");
		buf.append(" b.C_DV_INVEST_ADVISER, b.C_DV_TRUSTEE_XT, b.C_DV_SALES_CHANNELS, b.C_DV_CLEARING_MEMBER, ");
		buf.append(" b.C_PLACE_SETTLEMENT, b.C_CLEAR_ACCOUNT,b.C_BROKER_ID, b.C_BROKER_NAME,  ");
		buf.append(" b.C_BROKER_ID_TYPE, b.C_CLEARER_ID,b.C_CLEARER_NAME, b.C_CLEARER_ID_TYPE,  ");
		buf.append(" B.C_BANK_CODE,b.C_DV_BX_CLIENT,b.C_DV_TRD_CLIENT,b.C_DV_DEPOSITARY,b.C_LOGO_NAME,B.c_PAY_CODE,B.C_DV_ORG_ATTR,B.C_DV_CONSIGNER, ");//liuxiang 2015-9-6 STORY #22255 保监会报表需要细化银行属性//zzk 20151014 STORY #25788
		buf.append(" b.C_WWW_ADDR,b.C_FAX_TEL, "); //add by Yuntao Lau 2015.09.15 STORY #25681
		buf.append(" b.D_FOUND_TIME, "); //// D_FOUND_TIME加于2016.2.25by MeiYuan STORY #28264 机构基本信息 字段优化
		buf.append(" b.C_DV_ISSUER, "); //// add by wangyaokang STORY #29358 【中行】产品创设及发行（产品要素改造）
		buf.append(" b.c_BROKER_ACCOUNT,");// add by wsm 2016-5-10   STORY30235 【招商基金母公司】[QDII]TradeBlotter_EXP TradeBlotter招行导出接口
		buf.append(" b.c_DV_COUNTERPARTY, b.C_INDUSTRY_TYPE, "); ////20160330 added by liubo.STORY #29359 【中行】交易对手方、评级机构的维护.对手方、行业类别
		buf.append(" b.C_ADMIN_CODE, b.C_ADMIN_NATURE,b.C_ADMIN_NAME, b.C_IVT_CARD_NO,  b.D_IVT_CARD_VALDUR,  b.D_IVT_CARD_VALDUR_END,  b.C_IVT_CARD_TYPE,  b.C_REP_CARD_CODE,  b.C_DV_REPCARD_TYPE,  b.D_CARD_VAL_DUR,  b.D_CARD_VAL_DUR_END, "); //STORY37444针对中登FISP平台改造主动信息模块  add by xuyuanhao 2017-2-14

		/*
		 * Author : ChenLong
		 * Date   : 2016-11-22
		 * Status : Add
		 * Comment: 电子对账
		 * */
		buf.append(" b.c_elec_reconciliation,");
		buf.append(" b.c_DV_WBFWJG,"); //20160727 added by heliang.STORY #30646 【云平台】万联证券关联机构设置增加外包服务机构类型
		buf.append(" b.C_DV_TRUSTEE_MA,"); //20161210 added by mzyBUG #147121 【云平台万联证券】主体信息设置，资质为外包服务机构的，无法关联到组合
		buf.append(" b.C_DV_MARKETING, "); //20170113 added by HeLiang STORY #37662 光大银行新增自定义资产情况报表
		buf.append(" b.C_IDEN, b.C_TG_ACCOUNT_CODE, B.C_ISRElATED from T_P_BI_ORG b ");//add by HuangJin 2016.10.19 STORY #34371 关联机构设置其他信息增加托管账户编号需求
		buf.append("  where b.N_CHECK_STATE = 1  ");
	}

	
	private void getCommonQuerySqlBuf(StringBuffer buf){
		buf.append(" select c.*  from ( ");
//		buf.append(" select C_DV_CODE as C_DV_ORG_TYPE, ");
//		buf.append(" '[root]' as C_P_CODE, C_DV_CODE as C_ORG_CODE, ");
//		buf.append(" C_DV_NAME as C_ORG_NAME, '' as C_ORG_NAME_ST, ");
//		buf.append(" '' as C_ORG_NAME_CN, '' as C_MKT_CODE, ");
//		buf.append(" '' as C_ORG_CODE_P, 0 as N_REG_CAP, ");
//		buf.append(" '' as C_DC_CODE, '' as C_CORP_REP, ");
//		buf.append(" '' as C_CORP_CODE, '' as C_LINK_MAN, ");
//		buf.append(" '' as C_LINK_TEL, '' as C_MO_TEL, ");
//		buf.append(" '' as C_EMAIL, '' as C_REG_ADDR, ");
//		buf.append(" '' as C_OFFIC_ADDR, '' as C_REG_POST, ");
//		buf.append(" '' as C_OFFIC_POST, '' as C_DESC, ");
//		buf.append(" 1 as N_CHECK_STATE, '' as C_UPDATE_BY, ");
//		buf.append(" '' as C_UPDATE_TIME, '' as C_CHECK_BY, ");
//		buf.append(" '' as C_DV_MANAGER, '' as C_DV_TRUSTEE, ");
//		buf.append(" '' as C_DV_TRUSTEE_SEC, '' as C_DV_WARRANTOR, ");
//		buf.append(" '' as C_DV_INVEST_ADVISER, '' as C_DV_TRUSTEE_XT, ");
//		buf.append(" '' as C_DV_SALES_CHANNELS, '' as C_DV_CLEARING_MEMBER, ");
//		buf.append(" '' C_PLACE_SETTLEMENT,  '' C_CLEAR_ACCOUNT, '' C_BROKER_ID,  '' C_BROKER_NAME,  ");
//		buf.append(" '' C_BROKER_ID_TYPE,  '' C_CLEARER_ID,  '' C_CLEARER_NAME,  '' C_CLEARER_ID_TYPE,  ");
//		buf.append(" '' as C_CHECK_TIME, '' as C_IDEN ");
//		buf.append(" from v_s_dv_voc ");
//		buf.append(" where C_DV_TYPE = 'ORG_TYPE' ");
//		buf.append(" union all ");
		buf.append(" select b.C_DV_ORG_TYPE, b.C_DV_ORG_TYPE as C_P_CODE, ");
		buf.append(" b.C_ORG_CODE, b.C_ORG_NAME, b.C_ORG_NAME_ST, ");
		buf.append(" b.C_ORG_NAME_CN, b.C_MKT_CODE, b.C_ORG_CODE_P, ");
		buf.append(" b.N_REG_CAP, b.C_DC_CODE, b.C_CORP_REP,");
		buf.append(" b.C_CORP_CODE, b.C_LINK_MAN, b.C_LINK_TEL, ");
		buf.append(" b.C_MO_TEL,b.C_EMAIL,b.C_REG_ADDR, b.C_OFFIC_ADDR, ");
		buf.append(" b.C_REG_POST,b.C_OFFIC_POST,b.C_DESC, b.N_CHECK_STATE, ");
		buf.append(" b.C_UPDATE_BY, b.C_UPDATE_TIME, b.C_CHECK_BY,");
//		buf.append(" '' as C_DV_MANAGER, '' as C_DV_TRUSTEE, ");
//		buf.append(" '' as C_DV_TRUSTEE_SEC, '' as C_DV_WARRANTOR, ");
		buf.append(" '' as C_DV_MANAGER, '' as C_DV_MANAGER_SEC, b.C_DV_TRUSTEE, "); ////托管资质传给前台，对账参数需要判断资质
		buf.append(" b.C_DV_TRUSTEE_SEC, '' as C_DV_WARRANTOR, ");
		buf.append(" '' as C_DV_INVEST_ADVISER, '' as C_DV_TRUSTEE_XT, ");
		buf.append(" '' as C_DV_SALES_CHANNELS, '' as C_DV_CLEARING_MEMBER,  b.C_CHECK_TIME, b.C_IDEN,");
		buf.append(" b.C_DV_BX_CLIENT, b.C_DV_TRD_CLIENT, b.C_DV_DEPOSITARY, ");
		buf.append(" b.C_LOGO_NAME, b.C_PAY_CODE, b.C_BANK_CODE, ");
		buf.append(" b.C_PLACE_SETTLEMENT, b.C_CLEAR_ACCOUNT,b.C_BROKER_ID, b.C_BROKER_NAME,  ");
		buf.append(" b.C_BROKER_ID_TYPE, b.C_CLEARER_ID,b.C_CLEARER_NAME, b.C_CLEARER_ID_TYPE,b.C_DV_ORG_ATTR,b.C_DV_CONSIGNER, ");//liuxiang 2015-9-6 STORY #22255 保监会报表需要细化银行属性//zzk 20151014 STORY #25788
		buf.append(" b.D_FOUND_TIME, "); //// D_FOUND_TIME加于2016.2.25by MeiYuan STORY #28264 机构基本信息 字段优化
		buf.append(" B.C_WWW_ADDR, B.C_FAX_TEL, "); //add by Yuntao Lau 2015.09.15 STORY #25681
		buf.append(" B.C_DV_ISSUER, "); //add by wangyaokang STORY #29358 【中行】产品创设及发行（产品要素改造）
		buf.append(" b.c_BROKER_ACCOUNT,");// add by wsm 2016-5-10   STORY30235 【招商基金母公司】[QDII]TradeBlotter_EXP TradeBlotter招行导出接口
		/*
		 * Author : ChenLong
		 * Date   : 2016-11-22
		 * Status : Add
		 * Comment: 电子对账
		 * */
		buf.append(" b.c_elec_reconciliation,");
		buf.append(" b.c_DV_WBFWJG,"); //20160727 added by heliang.STORY #30646 【云平台】万联证券关联机构设置增加外包服务机构类型
		buf.append(" b.C_DV_TRUSTEE_MA,"); //20161210 added by mzyBUG #147121 【云平台万联证券】主体信息设置，资质为外包服务机构的，无法关联到组合
		buf.append(" b.C_DV_MARKETING,"); //20170113 added by HeLiang STORY #37662 光大银行新增自定义资产情况报表
		buf.append(" B.c_DV_COUNTERPARTY,b.C_INDUSTRY_TYPE, "); //20160330 added by liubo.STORY #29359 【中行】交易对手方、评级机构的维护.对手方、行业类别		
		buf.append(" b.C_ADMIN_CODE, b.C_ADMIN_NATURE,b.C_ADMIN_NAME, b.C_IVT_CARD_NO,  b.D_IVT_CARD_VALDUR,  b.D_IVT_CARD_VALDUR_END,  b.C_IVT_CARD_TYPE,  b.C_REP_CARD_CODE,  b.C_DV_REPCARD_TYPE,  b.D_CARD_VAL_DUR,  b.D_CARD_VAL_DUR_END, "); //STORY37444针对中登FISP平台改造主动信息模块  add by xuyuanhao 2017-2-14
		buf.append(" B.C_TG_ACCOUNT_CODE, ");//add by HuangJin 2016.10.19 STORY #34371 关联机构设置其他信息增加托管账户编号需求
		// STORY #51721 光大证券-监管类信息完善 add by lujianhao 20180705
		buf.append(" B.C_ISRElATED ");
		buf.append(" from T_P_BI_ORG b ");
		buf.append(" where b.N_CHECK_STATE = 1) c ");
	}
	
	/*END 数据服务方法*/
	
	/**
	 * 获取机构信息SQL
	 */
	public String getOraVocSql(){
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" select a.c_dv_code,a.c_dv_name");
		sqlBuff.append(" from v_s_dv_voc a");
		sqlBuff.append(" where a.c_dv_type = 'ORG_TYPE' ");
		sqlBuff.append(" order by a.n_order ");
		return sqlBuff.toString();
	}

	public String queryPortRelaOrgSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		buf.append("  SELECT A.*,                                 ");
		buf.append("         B.C_PORT_CODE,                       ");
		buf.append("         B.C_RELA_TYPE,                       ");
		buf.append("         B.C_RELA_CODE,                       ");
		buf.append("         B.C_DV_TYPE_CODE,                    ");
		buf.append("         B.C_DESC         AS C_DESC_B,        ");
		buf.append("         B.N_CHECK_STATE  AS N_CHECK_STATE_B, ");
		buf.append("         B.C_UPDATE_BY    AS C_UPDATE_BY_B,   ");
		buf.append("         B.C_UPDATE_TIME  AS C_UPDATE_TIME_B, ");
		buf.append("         B.C_CHECK_BY     AS C_CHECK_BY_B,    ");
		buf.append("         B.C_CHECK_TIME   AS C_CHECK_TIME_B,  ");
		buf.append(" 		 B.C_IDEN         AS C_IEDN_B         "); 
		buf.append("    FROM T_P_BI_ORG A                         ");
		buf.append("    JOIN T_P_AB_PORT_RELA B                   ");
		buf.append("      ON A.C_ORG_CODE = B.C_RELA_CODE         ");
		buf.append("   WHERE ");
		buf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));
		buf.append("         B.C_RELA_TYPE = 'RELA_ORG'             ");
		buf.append(" AND B.C_PORT_CODE IN  (SELECT * FROM TABLE(?)) ");

		return buf.toString();
	}

	public String getqueryPortRelaOrgCountSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		buf.append("  SELECT COUNT(*) AS CNT                      ");
		buf.append("    FROM T_P_BI_ORG A                     ");
		buf.append("    JOIN T_P_AB_PORT_RELA B                   ");
		buf.append("      ON A.C_ORG_CODE = B.C_RELA_CODE     ");
		buf.append("   WHERE ");
		buf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));
		buf.append("         B.C_RELA_TYPE = 'RELA_ORG'             ");
		buf.append(" AND B.C_PORT_CODE IN  (SELECT * FROM TABLE(?)) ");

		return buf.toString();
	}
	
	/**
	 * 
	 * @param mainDataSql
	 * @return
	 * @throws Exception
	 */
	public String getQueryConditionSql(String mainDataSql)
			throws Exception {
		String querySql = null;
		StringBuffer sqlBuffer = new StringBuffer();
		try {
			//BUG #318060 当前机构信息查询界面存在数据展示重复的问题
			sqlBuffer.append(" SELECT * FROM ( ");
			sqlBuffer.append(" SELECT DISTINCT * FROM T_P_BI_ORG A ");
			sqlBuffer.append(" START WITH A.C_ORG_CODE IN (SELECT C_ORG_CODE FROM ( ");
			sqlBuffer.append(mainDataSql);
			sqlBuffer.append(" )) CONNECT BY Nocycle PRIOR A.C_ORG_CODE = A.C_ORG_CODE_P ");
			sqlBuffer.append(" order by a.N_CHECK_STATE asc,c_update_time desc,c_check_time desc,c_iden  ");
			sqlBuffer.append(" ) p where 1 = 1 AND p.N_CHECK_STATE >= 0 ");
			querySql = sqlBuffer.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(sqlBuffer);
		}
		return querySql;
	}
	
	/**
	 * 根据付款账号获得所有的父级 机构代码
	 * @return
	 */
	public String getOrgSqlByAccNo() {
		StringBuffer buf = new StringBuffer();
		buf.append(" select c_org_code from t_p_bi_org a start with a.c_org_code in (select distinct c_org_code from T_P_BI_FUND_ACC where c_open_acc_no = ?) connect by prior a.c_org_code_p = a.c_org_code");
		return buf.toString();
	}

	public String getDataListByAptitude(String[] types) {
		String retSql = "";//返回sql
		
		StringBuffer buffer = new StringBuffer();

		buffer.append(" SELECT * FROM T_P_BI_ORG A ");
		buffer.append(" WHERE N_CHECK_STATE = 1 ");
		buffer.append(" AND (");
		for (int i = 0; i < types.length; i++) {
			if (types[i] != null && !"".equals(types[i])) {
				buffer.append(" C_DV_SUM LIKE '%").append(types[i]).append("%' OR ");
			}
		}
		StringUtil.delLastSplitMark(buffer, "OR ");
		
		buffer.append(" ) ");
		buffer.append(" order by C_ORG_CODE");
		retSql = buffer.toString();
		
		StringUtil.clearStringBuffer(buffer);
		
		return retSql;
	}

	public String getParentListByTypes() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getCommonQuerySqlBuf(buf);
		
		buf.append(" where C_ORG_CODE_P IS NULL ");
		buf.append(" AND C_DV_ORG_TYPE IN (SELECT * FROM TABLE(?)) ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getDataListCount(){
		StringBuffer buf = new StringBuffer();
		buf.append(" select count(1) from ( ");
		buf.append(getAllDataSql());
		buf.append(")");
		return buf.toString();
	}
	
	public String getDataListByTimestampCount(){
		StringBuffer buf = new StringBuffer();
		buf.append(" select count(1) from ( ");
		getCommonQuerySqlBuf(buf);
		buf.append(" WHERE (");
//		buf.append(" TO_DATE(a.C_CHECK_TIME,'yyyy-mm-dd hh24:mi:ss') >= to_date(?,'yyyy-mm-dd hh24:mi:ss') ");
		buf.append("  C.C_CHECK_TIME >= ? ");
		buf.append(")");
		buf.append(")");
		return buf.toString();
	}

	public String getDataListByTimestamp() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonQuerySqlBuf(buf);
		
//		sql = " TO_DATE(a.C_CHECK_TIME,'yyyy-mm-dd hh24:mi:ss') >= to_date(?,'yyyy-mm-dd hh24:mi:ss')";
		sql = " c.C_CHECK_TIME >= ? ";
		if (sql.trim().length() > 0) {
			buf.append(" where (");
			buf.append(sql);
			buf.append(")");
		}
		buf.append(" order by c.N_CHECK_STATE asc ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getDeleteSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		buf.append(" delete from T_P_BI_ORG ");
		buf.append(" where c_iden = ? or C_ORG_CODE = ? ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * 获取组合的管理人名称
	 * STORY #70280 add by xiadeqi 2019-3-20 
	 * @param portCode
	 * @return
	 */
	public String getManagerNameByPortCode() {
		StringBuffer buffer = new StringBuffer();
		//这里应该取已经审核的数据，而不是取所有
		buffer.append(" SELECT A.C_ORG_NAME FROM T_P_BI_ORG A ,T_P_AB_PORT_RELA B ");
		buffer.append(" WHERE A.C_DV_MANAGER = 'MANAGER' ");
		buffer.append(" AND A.C_ORG_CODE = B.C_RELA_CODE ");
		buffer.append(" AND A.N_CHECK_STATE = 1 ");
		buffer.append(" AND B.C_PORT_CODE = ? ");
		buffer.append(" AND B.C_RELA_TYPE = 'RELA_ORG' ");
		return buffer.toString();
	}
	/**
	 * Author : zuomignke
	 * Date   : 2019-07-04
	 * Status : Add
	 * Comment: 根据机构代码获取关联组合代码
	 * @return
	 */
	public String getPortByOrgCodeSql()
	{
		StringBuffer buf = new StringBuffer();
		buf.append(" select port.c_port_code from T_P_AB_PORT_RELA rela ");
		buf.append(" left join T_P_AB_PORT port ");
		buf.append(" ON port.c_port_code = rela.c_port_code ");
		buf.append(" where rela.c_rela_type = 'RELA_ORG' and rela.c_rela_code = ? ");
		return buf.toString();
	}
	
	
	public String queryOrgByPortSql(String c_dv_type){
		StringBuffer buf = new StringBuffer();
		
		buf.append("  SELECT a.C_ORG_CODE  ");
		buf.append("    FROM T_P_BI_ORG A                         ");
		buf.append("    JOIN T_P_AB_PORT_RELA B                   ");
		buf.append("      ON A.C_ORG_CODE = B.C_RELA_CODE         ");
		buf.append("      WHERE   B.C_RELA_TYPE = 'RELA_ORG'  ");
		
		if("MANAGER".equals(c_dv_type)){
			buf.append(" AND a.C_DV_MANAGER = 'MANAGER' ");
		}else if("TRUSTEE".equals(c_dv_type)){
			buf.append(" AND a.C_DV_TRUSTEE = 'TRUSTEE' ");
		}
		
		buf.append(" AND B.C_PORT_CODE IN  (SELECT * FROM TABLE(?)) ");
		
		return buf.toString();
	}
	
	/**
	 * 获取机构关联联系人SQL
	 * STORY #81326 【华宝兴业】关联结构设置 中联系信息维护调整
	 * @param where
	 * @return
	 */
	public String getOrgLinkManDataSql(boolean where) {
		StringBuffer sqlBuffer = new StringBuffer();
		String whereSql = where ? "WHERE A.C_ORG_CODE LIKE ?" : "";
		sqlBuffer.append(" SELECT T.* FROM (SELECT A.C_ORG_CODE, A.C_LINK_MAN, A.C_POST_NAME, A.C_LINK_TEL, A.C_MO_TEL, A.C_EMAIL, ");
		sqlBuffer.append(" ROW_NUMBER() OVER(PARTITION BY A.C_ORG_CODE ORDER BY A.N_ORDER) RN ");
		sqlBuffer.append(" FROM T_P_BI_ORG_LINK_RELA A ").append(whereSql).append(" ) T WHERE T.RN = 1 ");
		return sqlBuffer.toString();
	}
	
	/**
	 * 获取机构关联联系人SQL
	 * STORY #81326 【华宝兴业】关联结构设置 中联系信息维护调整
	 * @return
	 */
	public String getOrgLinkManListSql() {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT A.C_ORG_CODE, A.C_LINK_MAN, A.C_POST_NAME, A.C_LINK_TEL, A.C_MO_TEL, A.C_EMAIL ");
		sqlBuffer.append(" FROM T_P_BI_ORG_LINK_RELA A WHERE A.C_ORG_CODE = ? ORDER BY A.N_ORDER ");
		return sqlBuffer.toString();
	}
	
	/**
	 * 删除机构关联联系人信息SQL
	 * STORY #81326 【华宝兴业】关联结构设置 中联系信息维护调整
	 * @return
	 */
	public String deleteOrgLinkManSql() {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" DELETE FROM T_P_BI_ORG_LINK_RELA WHERE C_ORG_CODE = ? ");
		return sqlBuffer.toString();
	}
	
	/**
	 * 插入机构关联联系人信息SQL
	 * STORY #81326 【华宝兴业】关联结构设置 中联系信息维护调整
	 * @return
	 */
	public String getInsertOrgLinkManSql() {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" INSERT INTO T_P_BI_ORG_LINK_RELA (C_IDEN,C_ORG_CODE,C_LINK_MAN,C_POST_NAME,C_LINK_TEL,C_MO_TEL,C_EMAIL,N_ORDER,C_UPDATE_BY,C_UPDATE_TIME) ");
		sqlBuffer.append(" VALUES (SEQU_P_BI_ORG_LINK_RELA.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, TO_CHAR(SYSDATE, 'YYYYMMDD HH24:MI:SS')) ");
		return sqlBuffer.toString();
	}
}
