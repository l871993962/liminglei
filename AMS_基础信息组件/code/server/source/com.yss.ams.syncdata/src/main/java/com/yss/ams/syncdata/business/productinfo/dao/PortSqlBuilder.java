package com.yss.ams.syncdata.business.productinfo.dao;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.YssCons;
import com.yss.framework.context.ContextFactory;

/**
 * <产品基本信息>SQL语句构造类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortSqlBuilder implements SQLBuilder {

	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer paraBuf = new StringBuffer();

		paraBuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));
		paraBuf.append(" trim(a.C_PORT_CODE) <> 'ALL' AND ");
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_USER_CODE")) {
				paraBuf.append(" a.C_USER_CODE = ? AND ");
				// ---modified BY ZHAOXIANLIN 20130514 STORY #3659
				// 关于资产类型改造需求---start--//
			} else if (fieldedName.equals("C_PORT_CODE")) {
				paraBuf.append(" a.C_PORT_CODE like ? AND ");
			} else if (fieldedName.equals("C_PORT_NAME")) {
				paraBuf.append(" a.C_PORT_NAME like ? AND ");
			} else if (fieldedName.equals("C_DAT_CODE")) {
				paraBuf.append(" a.C_DAT_CODE = ? AND ");
				// ---modified BY ZHAOXIANLIN 20130514 STORY #3659
				// 关于资产类型改造需求---end--//
			} else if (fieldedName.equals("ARRAY_C_DC_CODE")) {
				paraBuf.append(" a.C_DC_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("ARRAY_C_DAT_CODE")) {
				paraBuf.append(" a.C_DAT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("C_DEL_TIME_START")) {
				paraBuf.append("a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				paraBuf.append("a.C_DEL_TIME <= ? AND ");
			} else if (fieldedName.equals("ARRAY_DV_PROD_STATE_NOTIN")) {
				paraBuf.append("a.C_DV_PROD_STATE NOT IN(SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("ARRAY_C_PORT_CODE")) {
				paraBuf.append(" a.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("ARRAY_C_IDEN")) {
				paraBuf.append(" a.C_IDEN IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("N_STATE")) {
				// 安信基金产品生命周期项目，查询状态为-1的数据
				paraBuf.append(" a.N_CHECK_STATE = ? AND ");
			} else if (fieldedName.equals("C_DV_PROD_STATE_EQU")) {
				paraBuf.append("a.C_DV_PROD_STATE = ? AND ");
			} else if (fieldedName.equals("C_PORT_UNIT")) {
				// 过滤定向产品基本信息产生的单元层组合 added by xzl
				paraBuf.append("(a.C_PORT_UNIT != ? OR a.C_PORT_UNIT IS NULL) AND ");
			} else if (fieldedName.equals("C_DV_PORT_CODE")) {
				// add by liyanjun 20160518 STORY #30595
				// 产品基本信息界面增加“资产代码”字段、“组合级别”下拉列表--默认值“单元层、组合层”，且可以选择过滤
				paraBuf.append("a.C_DV_PORT_CODE = ? AND ");
			} else if (fieldedName.equals("C_ASS_CODE")) {
				paraBuf.append("a.C_ASS_CODE like ? AND ");
			}else if (fieldedName.equals("C_RELA_CODE")) {
				paraBuf.append("a.C_PORT_CODE IN (SELECT DISTINCT C_PORT_CODE FROM T_P_AB_PORT_ACC_RELA WHERE C_RELA_CODE = ?) AND ");
			}else if(fieldedName.equals("D_BUILD_BEGIN")){
				////STORY #64220 支持 产品基本信息根据日期来查询
				paraBuf.append(" a.D_BUILD >= to_date(?,'yyyy-MM-dd')  AND ");
			} else if(fieldedName.equals("D_BUILD_END")){
				paraBuf.append(" a.D_BUILD <= to_date(?,'yyyy-MM-dd')  AND ");
			} 
		}

		if (paraBuf.length() > 0) {
			StringUtil.delLastSplitMark(paraBuf, " AND ");
		}

		buf.append(" select COUNT(a.C_IDEN) AS CNT ");
		buf.append(" from T_P_AB_PORT a ");

		if (paraBuf.length() > 0) {
			buf.append(" WHERE ").append(paraBuf);
			buf.append(" order by a.N_CHECK_STATE asc, a.rowId asc ");
		}

		return buf.toString();
	}

	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer paraBuf = new StringBuffer();

		paraBuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));
		paraBuf.append(" trim(a.C_PORT_CODE) <> 'ALL' AND ");
		
		for (String fieldedName : paraNameList) {
			// ---modified BY ZHAOXIANLIN 20130514 STORY #3659
			// 关于资产类型改造需求---start--//
			if (fieldedName.equals("C_PORT_CODE")) {
				paraBuf.append(" a.C_PORT_CODE like ? AND ");
			} else if (fieldedName.equals("C_PORT_NAME")) {
				paraBuf.append(" a.C_PORT_NAME like ? AND ");
			} else if (fieldedName.equals("C_DAT_CODE")) {
				paraBuf.append(" a.C_DAT_CODE = ? AND ");
				// ---modified BY ZHAOXIANLIN 20130514 STORY #3659
				// 关于资产类型改造需求---end--//
			} else if (fieldedName.equals("ARRAY_C_DC_CODE")) {
				paraBuf.append(" a.C_DC_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("ARRAY_C_DAT_CODE")) {
				paraBuf.append(" a.C_DAT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("C_DEL_TIME_START")) {
				paraBuf.append("a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				paraBuf.append("a.C_DEL_TIME <= ? AND ");
			} else if (fieldedName.equals("ARRAY_C_PORT_CODE")) {
				paraBuf.append(" a.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("ARRAY_C_IDEN")) {
				paraBuf.append(" a.C_IDEN IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("N_STATE")) {
				// 安信基金产品生命周期项目，查询状态为-1的数据
				paraBuf.append(" a.N_CHECK_STATE = ? AND ");
			} else if (paraNameList.contains("APPY_USER_REAL")) {
				// /根据用户代码使用权限过滤
				paraBuf.append("   a.C_PORT_CODE in( ");
				paraBuf.append("   select u.c_port_code from t_s_user_rela u where u.c_user_code = ? and u.n_check_state >= 0  ");
				paraBuf.append("   ) ");
			} else if (fieldedName.equals("C_PORT_CODE_EQUAL")) {
				paraBuf.append(" a.C_PORT_CODE = ? AND ");
			} else if (fieldedName.equals("ARRAY_DV_PROD_STATE_NOTIN")) {
				paraBuf.append("a.C_DV_PROD_STATE NOT IN(SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("C_DV_PROD_STATE_EQU")) {
				paraBuf.append("a.C_DV_PROD_STATE = ? AND ");
			} else if (fieldedName.equals("C_PORT_NAME_EQUAL")) {
				paraBuf.append("a.C_PORT_NAME = ? AND ");
			} else if (fieldedName.equals("C_PORT_UNIT")) {
				// 过滤定向产品基本信息产生的单元层组合 added by xzl
				paraBuf.append("(a.C_PORT_UNIT != ? OR a.C_PORT_UNIT IS NULL)  AND ");
			} else if (fieldedName.equals("C_DAT_CLS")) {
				paraBuf.append("a.C_DAT_CLS = ? AND ");
			} else if (fieldedName.equals("C_DV_PORT_CODE")) {
				paraBuf.append("a.C_DV_PORT_CODE = ? AND ");
			} else if (fieldedName.equals("ARRAY_C_DV_PORT_CODE")) {
				paraBuf.append("a.C_DV_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("C_PORT_CODE_P")) {
				paraBuf.append("a.C_PORT_CODE_P = ? AND ");
			} else if (fieldedName.equals("D_BUILD")) {
				paraBuf.append("to_date(?,'yyyy-mm-dd') between a.D_BUILD AND a.D_CLOSE AND ");
			} else if (fieldedName.equals("D_END")) {
				paraBuf.append(" a.D_CLOSE <= to_date(?,'yyyy-mm-dd') AND ");
			} else if (fieldedName.equals("C_ASS_CODE")) {
				// add by liyanjun 20160518 STORY #30595
				// 产品基本信息界面增加“资产代码”字段、“组合级别”下拉列表--默认值“单元层、组合层”，且可以选择过滤
				paraBuf.append("a.C_ASS_CODE like ? AND ");
			} else if (fieldedName.equals("C_RELA_CODE")) {
				// add by liyanjun 20160518 STORY #30595
				// 产品基本信息界面增加“资产代码”字段、“组合级别”下拉列表--默认值“单元层、组合层”，且可以选择过滤
				paraBuf.append("a.C_PORT_CODE IN (SELECT DISTINCT C_PORT_CODE FROM T_P_AB_PORT_ACC_RELA WHERE C_RELA_CODE = ?) AND ");
			} else if (fieldedName.equals("C_RELA_CODE_FALSE")) {
				// add by liyanjun 20160518 STORY #30595
				// 产品基本信息界面增加“资产代码”字段、“组合级别”下拉列表--默认值“单元层、组合层”，且可以选择过滤
				paraBuf.append("a.C_PORT_CODE NOT IN (SELECT DISTINCT C_PORT_CODE FROM T_P_AB_PORT_ACC_RELA WHERE C_RELA_CODE = ?)  AND ");
			}else if(fieldedName.equals("D_BUILD_BEGIN")){
				//STORY #64220 支持 产品基本信息根据日期来查询
				paraBuf.append(" a.D_BUILD >= to_date(?,'yyyy-MM-dd')  AND ");
			} else if(fieldedName.equals("D_BUILD_END")){
				paraBuf.append(" a.D_BUILD <= to_date(?,'yyyy-MM-dd')  AND ");
			} 
		}

		if (paraBuf.length() > 0) {
			StringUtil.delLastSplitMark(paraBuf, " AND ");
		}

		buf.append(" select a.*,a.C_DAT_CODE as fParaentCode,'PORT_TYPE' as DATA_TYPE ");
		buf.append(" from T_P_AB_PORT a ");
		// ---modified BY ZHAOXIANLIN 20130514 STORY #3659
		// 关于资产类型改造需求---start--//
		// if (paraBuf.length() > 0) {
		// buf.append(" WHERE ").append(paraBuf);
		// buf.append(" order by a.N_CHECK_STATE asc, a.rowId asc ");
		// }
		if (paraBuf.length() > 0) {
			buf.append(" WHERE ");
		}
		buf.append(paraBuf);
		buf.append(" order by decode(a.N_CHECK_STATE,'0',1,2),a.c_Dat_Code,a.D_BUILD  asc,a.c_iden ");
		// ---modified BY ZHAOXIANLIN 20130514 STORY #3659 关于资产类型改造需求---END--//
		return buf.toString();
	}

	/**
	 * 投资组合查询数据sql拼接（树形结构） 查询下面存在单元层组合的组合层组合、计划层组合信息
	 * 
	 * @author tangshifeng
	 * @since 2013-04-10
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
//	public String getTreeViewQuerySql(List<String> paraNameList)
//			throws Exception {
//		String querySql = null;
//		StringBuffer sqlBuffer = new StringBuffer();
//		StringBuffer valueFieldbuf = new StringBuffer();
//		try {
//			// sqlBuffer.append("select a.*,a.C_PORT_CODE as nodeCode,a.C_PORT_CODE_P as fParaentCode,1 as N_LEVEL from T_P_AB_PORT a  WHERE ");
//			// 获取已审核的投资组合数据
//			sqlBuffer
//					.append("select level, c.* from (select C_DAT_CODE as C_DAT_CODE,c_dat_code_p as C_PORT_CODE_P,");
//			sqlBuffer
//					.append("C_DAT_CODE as C_PORT_CODE,C_DAT_NAME as C_PORT_NAME_ST,'' as C_PORT_NAME_EN,");
//			sqlBuffer
//					.append("'' as C_ASS_CODE,''as C_DC_CODE,to_date('1999-12-14','yyyy-MM-dd') as D_BUILD,");
//			sqlBuffer
//					.append(" to_date('9998-12-31', 'YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
//			sqlBuffer
//					.append("to_date('1999-12-14','yyyy-MM-dd') as D_CLOSE,'' as C_DESC,C_DAT_NAME as C_PORT_NAME,1 as N_CHECK_STATE,");
//			sqlBuffer
//					.append("'' as C_UPDATE_BY,'' as C_UPDATE_TIME,'' as C_CHECK_BY,'' as C_CHECK_TIME, ");
//			sqlBuffer
//					.append("' ' as C_DV_PORT_CODE,'' as C_HDAY_CODE,'' as C_DV_PROD_STATE,'' as C_DAT_CLS, '' as c_port_unit,'' as c_assets_code,null as d_colse_acc,'ASS_TYPE' as DATA_TYPE");
//			
//			//edit by shijian 2018-10-07 BUG #223087 汇集调尾设置新增报错
//			sqlBuffer.append(" ,' ' as C_KH_NATURE ");
//			
//			sqlBuffer.append(" from  T_S_DAT_ASS_TYPE "); // // BUG #189835 BUG单-海通功能测试电子对账BUG汇总
//			// BY
//			// ZHAOXIANLIN
//			// 20130518
//			// STORY
//			// #3659
//			sqlBuffer.append(" union all select distinct b.C_PORT_CODE,");
//			sqlBuffer
//					.append("(case when trim (b.C_PORT_CODE_P) is null then b.C_DAT_CODE else b.C_PORT_CODE_P end)  as C_PORT_CODE_P,b.C_PORT_CODE,b.C_PORT_NAME_ST,b.C_PORT_NAME_EN,b.C_ASS_CODE,");
//			sqlBuffer
//					.append(" b.C_DC_CODE,b.D_BUILD,b.D_CLEAR,b.D_CLOSE,b.C_DESC, b.C_PORT_NAME,b.N_CHECK_STATE,b.C_UPDATE_BY,b.C_UPDATE_TIME,"); // 添加清算日期字段byleeyu20130806
//			sqlBuffer                                                                            // BUG #189835 BUG单-海通功能测试电子对账BUG汇总
//					.append(" b.C_CHECK_BY,b.C_CHECK_TIME,b.C_DV_PORT_CODE,b.C_HDAY_CODE,b.C_DV_PROD_STATE,b.C_DAT_CLS, b.c_port_unit,b.c_assets_code,b.d_colse_acc,'PORT_TYPE' as DATA_TYPE ");		
//			
//			//edit by shijian 2018-10-07 BUG #223087 汇集调尾设置新增报错
//			sqlBuffer.append(" ,' ' as C_KH_NATURE ");	
//			
//			sqlBuffer.append(" from T_P_AB_PORT b "); // MODIFIED // BUG #189835 BUG单-海通功能测试电子对账BUG汇总
//			// BY
//			// ZHAOXIANLIN
//			// 20130518
//			// STORY
//			// #3659
//
//			// sqlBuffer
//			// .append(" join t_p_ab_port sub on b.c_port_code = sub.c_port_code_p where b.N_CHECK_STATE = 1 ");
//			sqlBuffer.append(" where b.N_CHECK_STATE = 1 ");
//
//			// sqlBuffer.append(SqlUtil.getCheckStateClause(paraNameList, "a"));
//			for (String fieldedName : paraNameList) {
//				if (fieldedName.equals("C_PORT_CODE")) {
//					valueFieldbuf.append(" sub.C_PORT_CODE = ? AND ");
//				} else if (fieldedName.equals("ARRAY_C_DV_PORT_CODE")) {
//					valueFieldbuf
//							.append(" b.C_DV_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
//					// .append(" sub.C_DV_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");//sub.
//					// } else if (fieldedName.equals("C_PORT_CODE_P")) {
//					// valueFieldbuf.append(" sub.C_PORT_CODE_P = ? AND ");
//					// } else if (fieldedName.equals("ARRAY_C_PORT_CODE")) {
//					// valueFieldbuf
//					// .append(" sub.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
//				} else if (fieldedName.equals("C_POST_CODE")
//						&& paraNameList.contains("APPY_USER_REAL")) {
//					// /根据用户代码使用权限过滤
//					valueFieldbuf.append(" b.C_PORT_CODE in( ");
//					valueFieldbuf
//							.append("select distinct c_port_code_p from t_p_ab_port where c_port_code in ( ");
//					valueFieldbuf
//							.append(" select u.c_port_code from t_s_user_rela u where u.c_post_code = ? and u.c_user_code = ? and u.n_check_state >= 0 ) ");
//					valueFieldbuf.append("  ) AND ");
//				}
//
//				else if (fieldedName.equals("APPY_USER_REAL")
//						&& !paraNameList.contains("C_POST_CODE")) {
//					// /根据用户代码使用权限过滤
//					// valueFieldbuf.append(" sub.C_PORT_CODE in( ");
//					valueFieldbuf.append(" b.C_PORT_CODE in( ");
//					// bug 82895 by Jinghehe 2013-11-06
//					valueFieldbuf
//							.append("select distinct c_port_code_p from t_p_ab_port where  C_DV_PORT_CODE = 'UNIT_LAYER' and c_port_code in ( ");
//					
//					valueFieldbuf
//							.append("select distinct c_data_code  from T_S_USER_POST_DATA where c_user_code = ? and c_data_type ='1' and n_check_state = 1 and n_source=0) ");
////					valueFieldbuf
////							.append(" select u.c_port_code from t_s_user_rela u where u.c_user_code = ? and u.n_check_state >= 0 ) ");
//					// valueFieldbuf
//					// .append(" select u.c_port_code from t_s_user_rela u where u.c_user_code = ? and u.n_check_state >= 0  ");
//					valueFieldbuf.append("  ) AND ");
//				} else if (fieldedName.equals("C_USER_CODE")) {
//					valueFieldbuf
//							.append(" b.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
//					// .append(" sub.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
//				}
//			}
//
//			if (valueFieldbuf.length() > 0) {
//				sqlBuffer.append(" AND ").append(valueFieldbuf);
//			}
//
//			if (valueFieldbuf.length() > 0) {
//				StringUtil.delLastSplitMark(sqlBuffer, " AND ");
//			}
//
//			sqlBuffer
//					.append(" ) c start with c.C_PORT_CODE_P = '[root]' connect by prior c.C_DAT_CODE = c.C_PORT_CODE_P ");
//			querySql = sqlBuffer.toString();
//		} catch (Exception ex) {
//			throw ex;
//		} finally {
//			StringUtil.clearStringBuffer(sqlBuffer);
//			StringUtil.clearStringBuffer(valueFieldbuf);
//		}
//		return querySql;
//	}

	/**
	 * 根据父节点查询单元层组合（列表结构）
	 * 
	 * @author tangshifeng
	 * @since 2013-04-17
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getUnitPortQuerySql(List<String> paraNameList)
			throws Exception {
		String querySql = null;
		StringBuffer sqlBuffer = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			sqlBuffer.append("select b.C_PORT_CODE,b.C_DAT_CLS,");
			sqlBuffer
					.append("(case when trim (b.C_PORT_CODE_P) is null then b.C_DAT_CODE else b.C_PORT_CODE_P end)  as C_PORT_CODE_P,b.C_PORT_CODE,b.C_PORT_NAME_ST,b.C_PORT_NAME_EN,b.C_ASS_CODE,");
			sqlBuffer
					.append(" b.C_DC_CODE,b.D_BUILD,b.D_CLOSE,b.D_CLEAR,b.C_DESC, b.C_PORT_NAME,b.N_CHECK_STATE,b.C_UPDATE_BY,b.C_UPDATE_TIME,"); // 添加清算字段byleeyu20130806
			sqlBuffer
					.append(" b.C_CHECK_BY,b.C_CHECK_TIME,'PORT_TYPE' as DATA_TYPE,b.C_DV_PORT_CODE,b.C_HDAY_CODE,b.C_DV_PROD_STATE,b.C_PORT_UNIT from T_P_AB_PORT b where b.N_CHECK_STATE = 1 ");

			for (String fieldedName : paraNameList) {
				if (fieldedName.equals("C_PORT_CODE")) {
					valueFieldbuf.append(" b.C_PORT_CODE = ? AND ");
				} else if (fieldedName.equals("ARRAY_C_DV_PORT_CODE")) {
					valueFieldbuf
							.append(" b.C_DV_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
				} else if (fieldedName.equals("C_PORT_CODE_P")) {
					valueFieldbuf.append(" b.C_PORT_CODE_P = ? AND ");
				} else if (fieldedName.equals("ARRAY_C_PORT_CODE")) {
					valueFieldbuf
							.append(" b.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
				}
			}

			if (valueFieldbuf.length() > 0) {
				sqlBuffer.append(" AND ").append(valueFieldbuf);
			}

			if (valueFieldbuf.length() > 0) {
				StringUtil.delLastSplitMark(sqlBuffer, " AND ");
			}

			querySql = sqlBuffer.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(sqlBuffer);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return querySql;
	}

	/**
	 * 组合关联方案新增时查询组合
	 * 
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getPlanRelaPortAdd() throws Exception {
		StringBuffer buf = new StringBuffer();

		buf.append(" SELECT LEVEL, C.* ");
		buf.append("   FROM (SELECT C_DAT_CODE AS C_DAT_CODE, ");
		buf.append("                C_DAT_CODE_P AS C_PORT_CODE_P, ");
		buf.append("                '' AS C_IDEN, ");
		buf.append("                C_DAT_CODE AS C_PORT_CODE, ");
		buf.append("                C_DAT_NAME AS C_PORT_NAME_ST, ");
		buf.append("                '' AS C_PORT_NAME_EN, ");
		buf.append("                '' AS C_ASS_CODE, ");
		buf.append("                '' AS C_DC_CODE, ");
		buf.append("                TO_DATE('1999-12-14', 'YYYY-MM-DD') AS D_BUILD, ");
		buf.append("                TO_DATE('1999-12-14', 'YYYY-MM-DD') AS D_CLOSE, ");
		buf.append(" to_date('9998-12-31', 'YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		buf.append("                '' AS C_DESC, ");
		buf.append("                C_DAT_NAME AS C_PORT_NAME, ");
		buf.append("                1 AS N_CHECK_STATE, ");
		buf.append("                '' AS C_UPDATE_BY, ");
		buf.append("                '' AS C_UPDATE_TIME, ");
		buf.append("                '' AS C_CHECK_BY, ");
		buf.append("                '' AS C_CHECK_TIME, ");
		buf.append("                'ASS_TYPE' AS DATA_TYPE, ");
		buf.append("                ' ' AS C_DV_PORT_CODE, ");
		buf.append("                '' AS C_HDAY_CODE, ");
		buf.append("                '' AS C_DV_PROD_STATE, ");// add by weijj
																// 20130712
																// STORY #4023
		buf.append("                '' AS C_PORT_UNIT ");// add by weijj
															// 20130712 STORY
															// #4023
		buf.append("               ,'' AS C_DAT_CLS"); // add by zhaoxianlin
		// 20130518 story #3659
		buf.append("           FROM T_S_DAT_ASS_TYPE ");
		buf.append("         UNION ALL ");
		buf.append("         SELECT B.C_PORT_CODE, ");
		buf.append("                (CASE ");
		buf.append("                  WHEN TRIM(B.C_PORT_CODE_P) IS NULL THEN ");
		buf.append("                   B.C_DAT_CODE ");
		buf.append("                  ELSE ");
		buf.append("                   B.C_PORT_CODE_P ");
		buf.append("                END) AS C_PORT_CODE_P, ");
		buf.append("                B.C_IDEN, ");
		buf.append("                B.C_PORT_CODE, ");
		buf.append("                B.C_PORT_NAME_ST, ");
		buf.append("                B.C_PORT_NAME_EN, ");
		buf.append("                B.C_ASS_CODE, ");
		buf.append("                B.C_DC_CODE, ");
		buf.append("                B.D_BUILD, ");
		buf.append("                B.D_CLOSE, ");
		buf.append("                B.D_CLEAR, "); // 添加清算字段
		buf.append("                B.C_DESC, ");
		buf.append("                B.C_PORT_NAME, ");
		buf.append("                B.N_CHECK_STATE, ");
		buf.append("                B.C_UPDATE_BY, ");
		buf.append("                B.C_UPDATE_TIME, ");
		buf.append("                B.C_CHECK_BY, ");
		buf.append("                B.C_CHECK_TIME, ");
		buf.append("                'PORT_TYPE' AS DATA_TYPE, ");
		buf.append("                B.C_DV_PORT_CODE, ");
		buf.append("                B.C_HDAY_CODE, ");
		buf.append("                B.C_DV_PROD_STATE, ");// add by weijj
															// 20130712 STORY
															// #4023
		buf.append("                B.C_PORT_UNIT ");// add by weijj 20130712
														// STORY #4023
		buf.append("                ,B.C_DAT_CLS ");// add by zhaoxianlin
		// 20130518 story #3659
		buf.append("           FROM T_P_AB_PORT B ");
		buf.append("          WHERE B.N_CHECK_STATE = 1 ");
		buf.append("            AND NOT EXISTS ");
		buf.append("          (SELECT C_PORT_CODE ");
		buf.append("                   FROM T_E_EXEC_PLAN_RELA A ");
		buf.append("                  WHERE A.N_CHECK_STATE >= 0 ");
		buf.append("                    AND A.C_PORT_CODE = B.C_PORT_CODE ");
		buf.append("                    AND A.C_PLAN_TYPE = ? ");
		buf.append("                    AND C_PLAN_CODE = ? ");
		buf.append("                    AND ((TO_DATE(?, 'yyyy-MM-dd') BETWEEN D_BEGIN AND D_END) OR ");
		buf.append("                        (TO_DATE(?, 'yyyy-MM-dd') BETWEEN D_BEGIN AND D_END) OR ");
		buf.append("                        (TO_DATE(?, 'yyyy-MM-dd') < D_BEGIN AND ");
		buf.append("                        TO_DATE(?, 'yyyy-MM-dd') > D_END)))) C ");
		// //新方法这里有问题,赞先用老方法代替
		buf.append("  START WITH C.C_PORT_CODE IN (SELECT * FROM TABLE(?)) ");
		buf.append(" CONNECT BY C.C_DAT_CODE = PRIOR C.C_PORT_CODE_P ");

		return buf.toString();
	}

	/**
	 * 组合关联方案浏览时查询组合
	 * 
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getPlanRelaPortBrow() throws Exception {
		String str = " select a.*,'PORT_TYPE' as DATA_TYPE from t_P_AB_Port a WHERE A.C_PORT_CODE = ? ";
		return str;
	}

	// ---------------DELETE BY ZXL 20130829 ------------START
	// public String getAssetTreeView(List<String> paraNameList) {
	// StringBuffer buf = new StringBuffer();
	// StringBuffer paraBuf = new StringBuffer();
	// StringBuffer portBuf = new StringBuffer();
	//
	// for (String fieldedName : paraNameList) {
	// if (fieldedName.equals("C_TR_CODE_R")) {
	// paraBuf.append(" a.C_TR_CODE_R = ? AND ");
	// }
	// }
	//
	// for (String fieldedName : paraNameList) {
	// if (fieldedName.equals("C_PORT_CODE")) {
	// portBuf
	// .append(" c.C_DAT_CODE IN (SELECT * FROM TABLE(?)) AND ");
	// }
	// }
	//
	// if (paraBuf.length() > 0) {
	// StringUtil.delLastSplitMark(paraBuf, " AND ");
	// }
	//
	// if (portBuf.length() > 0) {
	// StringUtil.delLastSplitMark(portBuf, " AND ");
	// }
	//
	// buf.append(" select c.*  ");
	// buf.append(" from ( ");
	// buf.append(" select '' AS C_IDEN, C_DAT_CODE as C_DAT_CODE,  ");
	// buf.append(" c_dat_code_p as fParaentCode,  ");
	// buf.append(" C_DAT_CODE as nodeCode,  ");
	// buf.append(" C_DAT_CODE as C_PORT_CODE,  ");
	// buf.append(" C_DAT_NAME as C_PORT_NAME_ST,'' as C_PORT_NAME_EN,  ");
	// buf.append(" '' as C_ASS_CODE,''as C_DC_CODE,  ");
	// buf.append(" to_date('1999-12-14','YYYY-MM-DD') as D_BUILD, ");
	// buf.append(" to_date('1999-12-14','YYYY-MM-DD') as D_CLOSE, ");
	// buf.append(" to_date('9998-12-31','YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
	// buf
	// .append(" '' as C_DESC,C_DAT_NAME as C_PORT_NAME,1 as N_CHECK_STATE, ");
	// buf.append(" '' as C_UPDATE_BY,'' as C_UPDATE_TIME, ");
	// buf.append(" '' as C_CHECK_BY,'' as C_CHECK_TIME , ");
	// buf.append(" 'ASS_TYPE' as DATA_TYPE,' ' as C_DV_PORT_CODE, ");
	// buf.append(" c_dat_code_p as C_PORT_CODE_P,'' as C_HDAY_CODE, '' as C_DV_PROD_STATE,");
	// buf.append(" '' as C_DAT_CLS ");
	// buf.append(" from  T_S_DAT_ASS_TYPE ");
	// buf.append(" union all ");
	// buf.append(" select b.C_IDEN,b.C_PORT_CODE, ");
	// buf
	// .append(" (case when trim(b.C_PORT_CODE_P) is null then b.C_DAT_CODE else b.C_PORT_CODE_P end) as fParaentCode, ");
	// buf.append(" b.C_PORT_CODE AS nodeCode, ");
	// buf.append(" b.C_PORT_CODE,b.C_PORT_NAME_ST, ");
	// buf.append(" b.C_PORT_NAME_EN,b.C_ASS_CODE, ");
	// buf.append(" b.C_DC_CODE,b.D_BUILD, ");
	// buf.append(" b.D_CLOSE,b.D_CLEAR,b.C_DESC, "); // 添加清算字段
	// buf.append(" b.C_PORT_NAME,b.N_CHECK_STATE, ");
	// buf.append(" b.C_UPDATE_BY,b.C_UPDATE_TIME, ");
	// buf.append(" b.C_CHECK_BY,b.C_CHECK_TIME, ");
	// buf.append(" 'PORT_TYPE' as DATA_TYPE,b.C_DV_PORT_CODE, ");
	// buf.append(" b.C_PORT_CODE_P,b.C_HDAY_CODE, b.C_DV_PROD_STATE,");
	// buf.append(" b.C_HDAY_CODE ");///????
	// buf.append(" from T_P_AB_PORT b ");
	// buf.append(" where b.N_CHECK_STATE = 1  ");
	// //如果前台传参包含status属性，则此时sql语句中不需要添加 剔除已有资产类型的sql语句，
	// //如果不包含status，添加剔除资产类型sql
	// if(!paraNameList.contains("status")){
	//
	// buf.append(" and not exists  ");
	// buf
	// .append(" (select * from T_P_AB_ASS_TR_SUB a  where a.N_CHECK_STATE >= 0  ");
	// buf.append(" and a.c_port_code = b.c_port_code ");
	//
	// if (paraBuf.length() > 0) {
	// buf.append(" AND ").append(paraBuf);
	// }
	//
	// buf.append(" ))c ");
	// }else{
	// if (paraBuf.length() > 0) {
	// buf.append(" AND ").append(paraBuf);
	// }
	//
	// buf.append(" )c ");
	// }
	// if (portBuf.length() > 0) {
	// buf.append(" WHERE ").append(portBuf);
	// }
	//
	// return buf.toString();
	// }
	// ---------------DELETE BY ZXL 20130829 ------------END
	// ADD BY ZXL 20130829 资产树形结构B区组合加载时按资产类型显示
	public String getAssetTreeView(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		StringBuffer wherebuf = new StringBuffer();
		wherebuf = setWhereSQL(wherebuf, paraNameList);
		String sql = "";

		// buf.append(" select level, c.* from (");
		buf.append(" select  c.* from (");
		// buf.append("   select '' AS C_IDEN, ");
		// buf.append("     C_DAT_CODE as C_DAT_CODE, '' as C_DAT_CLS,");
		// buf.append("     c_dat_code_p as C_PORT_CODE_P, ");
		// buf.append("     C_DAT_CODE as C_PORT_CODE, ");
		// buf.append("     C_DAT_NAME as C_PORT_NAME_ST, ");
		// buf.append("     '' as C_PORT_NAME_EN, '' as C_ASS_CODE, ");
		// buf.append("     ''as C_DC_CODE, ");
//		buf.append("     to_date('1999-12-14','YYYY-MM-DD') as D_BUILD, ");
//		buf.append("     to_date('1999-12-14','YYYY-MM-DD') as D_CLOSE, ");
//		buf.append("     to_date('9998-12-31','YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
//		buf.append("     '' as C_DESC,'' as C_PORT_NAME, ");
//		buf.append("     1 as N_CHECK_STATE, '' as C_UPDATE_BY, ");
//		buf.append("     '' as C_UPDATE_TIME,'' as C_CHECK_BY, ");
		// buf.append("     '' as C_CHECK_TIME,'ASS_TYPE' as DATA_TYPE, ");
		// buf.append("     ' ' as C_DV_PORT_CODE, ");
		// buf.append("     '' as C_HDAY_CODE,'' as C_DV_PROD_STATE, '' as C_PORT_UNIT");
		// buf.append("   from  T_S_DAT_ASS_TYPE ");
		//
		// buf.append("   union all ");
		buf.append("     select b.C_IDEN, b.C_PORT_CODE as C_DAT_CODE,  C_DAT_CLS,");
		buf.append("     case ");
		buf.append("       when trim(b.C_PORT_CODE_P) is null then ");
		buf.append("         b.C_DAT_CODE ");
		buf.append("       else b.C_PORT_CODE_P end ");
		buf.append("       as C_PORT_CODE_P, ");
		buf.append("       b.C_PORT_CODE,b.C_PORT_NAME_ST, ");
		buf.append("       b.C_PORT_NAME_EN,b.C_ASS_CODE, ");
		buf.append("       b.C_DC_CODE,b.D_BUILD,b.D_CLOSE,b.D_CLEAR, ");
		buf.append("       b.C_DESC, b.C_PORT_NAME,b.N_CHECK_STATE, ");
		buf.append("       b.C_UPDATE_BY,b.C_UPDATE_TIME, ");
		buf.append("       b.C_CHECK_BY,b.C_CHECK_TIME, ");
		buf.append("       'PORT_TYPE' as DATA_TYPE, ");
		buf.append("       b.C_DV_PORT_CODE,");
		buf.append("       b.C_HDAY_CODE,b.C_DV_PROD_STATE,b.C_PORT_UNIT ");
		buf.append("     from T_P_AB_PORT b  where b.N_CHECK_STATE = 1");
		if (!paraNameList.contains("status")) {

			buf.append(" and  b.C_PORT_CODE not in  (select C_port_code from T_P_AB_ASS_TR_SUB a  ");
			buf.append("  where a.N_CHECK_STATE >= 0");
			// if (paraNameList.contains("C_TR_CODE_R")) {
			// buf.append(" and a.C_TR_CODE = ? ");
			// }
			if (paraNameList.contains("C_TR_CODE_NEW")) {
				buf.append("  and a.C_TR_CODE_R in ( ");
				buf.append(" select C_TR_CODE_R from T_P_AB_ASS_TR ");
				buf.append("  where c_tr_code = ? ) ");
			}

			buf.append(" and a.c_port_code = b.c_port_code)");

		}
		buf.append(" ) c ");
		// buf.append("           start with c.C_PORT_CODE in( ");
		// buf.append("             select a.C_PORT_CODE  ");
		// buf.append("             from T_P_AB_PORT a ");
		// buf.append("             where a.N_CHECK_STATE = 1 ");
		// buf.append("           ) ");
		if (paraNameList.contains("C_PORT_CODE")) {
			buf.append(" where C.C_PORT_CODE IN (SELECT * FROM TABLE(?)) ");
		}
		// buf.append("     connect by prior c.C_PORT_CODE_P = c.C_DAT_CODE ");
		// buf.append("order by level desc ,c_port_code");
		buf.append("order by  c_port_code");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);

		return sql;
	}

	public String getPortAssTreeListAddFormSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		StringBuffer paraBuf = new StringBuffer();
		StringBuffer portBuf = new StringBuffer();

		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_TR_CODE_R")) {
				paraBuf.append(" a.C_TR_CODE_R = ? AND ");
			}
		}

		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_PORT_CODE")) {
				portBuf.append(" c.C_DAT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}
		}

		if (paraBuf.length() > 0) {
			StringUtil.delLastSplitMark(paraBuf, " AND ");
		}

		if (portBuf.length() > 0) {
			StringUtil.delLastSplitMark(portBuf, " AND ");
		}

		buf.append(" select c.*  ");
		buf.append(" from ( ");
		// // add by weijj '' as C_DAT_CLS 20130604
		buf.append(" select '' AS C_IDEN, C_DAT_CODE as C_DAT_CODE, '' as C_DAT_CLS, ");
		buf.append(" c_dat_code_p as fParaentCode,  ");
		buf.append(" C_DAT_CODE as nodeCode,  ");
		buf.append(" C_DAT_CODE as C_PORT_CODE,  ");
		buf.append(" C_DAT_NAME as C_PORT_NAME_ST,'' as C_PORT_NAME_EN,");
		buf.append(" '' as C_ASS_CODE,''as C_DC_CODE,  ");
		buf.append(" to_date('1999-12-14','YYYY-MM-DD') as D_BUILD, ");
		buf.append(" to_date('1999-12-14','YYYY-MM-DD') as D_CLOSE, ");
		buf.append(" to_date('9998-12-31','YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		buf.append(" '' as C_DESC,C_DAT_NAME as C_PORT_NAME,1 as N_CHECK_STATE, ");
		buf.append(" '' as C_UPDATE_BY,'' as C_UPDATE_TIME, ");
		buf.append(" '' as C_CHECK_BY,'' as C_CHECK_TIME , ");
		buf.append(" 'ASS_TYPE' as DATA_TYPE,' ' as C_DV_PORT_CODE, ");
		buf.append(" c_dat_code_p as C_PORT_CODE_P,'' as C_HDAY_CODE,'' as C_DV_PROD_STATE, '' as C_PORT_UNIT ");
		buf.append(" from  T_S_DAT_ASS_TYPE ");
		buf.append(" union all ");
		// // add by weijj '' as C_DAT_CLS 20130604
		buf.append(" select b.C_IDEN,b.C_PORT_CODE, b.C_DAT_CLS,");
		buf.append(" (case when trim(b.C_PORT_CODE_P) is null then b.C_DAT_CODE else b.C_PORT_CODE_P end) as fParaentCode, ");
		buf.append(" b.C_PORT_CODE AS nodeCode, ");
		buf.append(" b.C_PORT_CODE,b.C_PORT_NAME_ST, ");
		buf.append(" b.C_PORT_NAME_EN,b.C_ASS_CODE, ");
		buf.append(" b.C_DC_CODE,b.D_BUILD, ");
		buf.append(" b.D_CLOSE,b.D_CLEAR,b.C_DESC, "); // 添加清算字段
		buf.append(" b.C_PORT_NAME,b.N_CHECK_STATE, ");
		buf.append(" b.C_UPDATE_BY,b.C_UPDATE_TIME, ");
		buf.append(" b.C_CHECK_BY,b.C_CHECK_TIME, ");
		buf.append(" 'PORT_TYPE' as DATA_TYPE,b.C_DV_PORT_CODE, ");
		buf.append(" b.C_PORT_CODE_P,b.C_HDAY_CODE,b.C_DV_PROD_STATE,b.C_PORT_UNIT ");
		buf.append(" from T_P_AB_PORT b ");
		buf.append(" where b.N_CHECK_STATE = 1  ");
		buf.append(" and not exists  ");
		buf.append(" (select * from T_P_AB_ASS_TR_SUB a  where a.N_CHECK_STATE >= 0  ");
		buf.append(" and a.c_port_code = b.c_port_code ");

		if (paraBuf.length() > 0) {
			buf.append(" AND ").append(paraBuf);
		}

		buf.append(" ))c ");

		if (portBuf.length() > 0) {
			buf.append(" WHERE ").append(portBuf);
		}

		return buf.toString();
	}

	public String getPortAssTreeListALLSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		StringBuffer paraBuf = new StringBuffer();
		StringBuffer portBuf = new StringBuffer();

		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_TR_CODE_R")) {
				paraBuf.append(" a.C_TR_CODE_R = ? AND ");
			}
		}

		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_PORT_CODE")) {
				portBuf.append(" c.C_DAT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}
		}

		if (paraBuf.length() > 0) {
			StringUtil.delLastSplitMark(paraBuf, " AND ");
		}

		if (portBuf.length() > 0) {
			StringUtil.delLastSplitMark(portBuf, " AND ");
		}

		buf.append(" select c.*  ");
		buf.append(" from ( ");
		// // add by weijj '' as C_DAT_CLS 20130604
		buf.append(" select '' AS C_IDEN, C_DAT_CODE as C_DAT_CODE, '' as C_DAT_CLS, ");
		buf.append(" c_dat_code_p as fParaentCode,  ");
		buf.append(" C_DAT_CODE as nodeCode,  ");
		buf.append(" C_DAT_CODE as C_PORT_CODE,  ");
		buf.append(" C_DAT_NAME as C_PORT_NAME_ST,'' as C_PORT_NAME_EN,");
		buf.append(" '' as C_ASS_CODE,''as C_DC_CODE,  ");
		buf.append(" to_date('1999-12-14','YYYY-MM-DD') as D_BUILD, ");
		buf.append(" to_date('1999-12-14','YYYY-MM-DD') as D_CLOSE, ");
		buf.append(" to_date('9998-12-31','YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		buf.append(" '' as C_DESC,C_DAT_NAME as C_PORT_NAME,1 as N_CHECK_STATE, ");
		buf.append(" '' as C_UPDATE_BY,'' as C_UPDATE_TIME, ");
		buf.append(" '' as C_CHECK_BY,'' as C_CHECK_TIME , ");
		buf.append(" 'ASS_TYPE' as DATA_TYPE,' ' as C_DV_PORT_CODE, ");
		buf.append(" c_dat_code_p as C_PORT_CODE_P,'' as C_HDAY_CODE, '' as C_DV_PROD_STATE, '' as C_PORT_UNIT");
		buf.append(" from  T_S_DAT_ASS_TYPE ");
		buf.append(" union all ");
		// // add by weijj '' as C_DAT_CLS 20130604
		buf.append(" select b.C_IDEN,b.C_PORT_CODE, b.C_DAT_CLS,");
		buf.append(" (case when trim(b.C_PORT_CODE_P) is null then b.C_DAT_CODE else b.C_PORT_CODE_P end) as fParaentCode, ");
		buf.append(" b.C_PORT_CODE AS nodeCode, ");
		buf.append(" b.C_PORT_CODE,b.C_PORT_NAME_ST, ");
		buf.append(" b.C_PORT_NAME_EN,b.C_ASS_CODE, ");
		buf.append(" b.C_DC_CODE,b.D_BUILD, ");
		buf.append(" b.D_CLOSE,b.D_CLEAR,b.C_DESC, "); // 添加清算字段
		buf.append(" b.C_PORT_NAME,b.N_CHECK_STATE, ");
		buf.append(" b.C_UPDATE_BY,b.C_UPDATE_TIME, ");
		buf.append(" b.C_CHECK_BY,b.C_CHECK_TIME, ");
		buf.append(" 'PORT_TYPE' as DATA_TYPE,b.C_DV_PORT_CODE, ");
		buf.append(" b.C_PORT_CODE_P,b.C_HDAY_CODE,b.C_DV_PROD_STATE,b.C_PORT_UNIT ");
		buf.append(" from T_P_AB_PORT b ");
		buf.append(" where ");
		buf.append("  not exists  ");
		buf.append(" (select * from T_P_AB_ASS_TR_SUB a  where a.N_CHECK_STATE >= 0  ");
		buf.append(" and a.c_port_code = b.c_port_code ");

		if (paraBuf.length() > 0) {
			buf.append(" AND ").append(paraBuf);
		}

		buf.append(" ))c ");

		if (portBuf.length() > 0) {
			buf.append(" WHERE ").append(portBuf);
		}

		return buf.toString();
	}

	public String getPortAssTreeListSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		StringBuffer paraBuf = new StringBuffer();

		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_TR_CODE_R")) {
				paraBuf.append(" a.C_TR_CODE_R = ? AND ");
			} else if (fieldedName.equals("C_PORT_CODE")) {
				paraBuf.append(" c.C_DAT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}
		}

		if (paraBuf.length() > 0) {
			StringUtil.delLastSplitMark(paraBuf, " AND ");
		}

		buf.append(" select c.*  ");
		buf.append(" from ( ");
		// // add by weijj '' as C_DAT_CLS 20130604
		buf.append(" select '' AS C_IDEN, C_DAT_CODE as C_DAT_CODE,  '' as C_DAT_CLS,");
		buf.append(" c_dat_code_p as fParaentCode,  ");
		buf.append(" C_DAT_CODE as nodeCode,  ");
		buf.append(" C_DAT_CODE as C_PORT_CODE,  ");
		buf.append(" C_DAT_NAME as C_PORT_NAME_ST,'' as C_PORT_NAME_EN,  ");
		buf.append(" '' as C_ASS_CODE,''as C_DC_CODE,  ");
		buf.append(" to_date('1999-12-14','YYYY-MM-DD') as D_BUILD, ");
		buf.append(" to_date('1999-12-14','YYYY-MM-DD') as D_CLOSE, ");
		buf.append(" to_date('9998-12-31','YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		buf.append(" '' as C_DESC,C_DAT_NAME as C_PORT_NAME,1 as N_CHECK_STATE, ");
		buf.append(" '' as C_UPDATE_BY,'' as C_UPDATE_TIME, ");
		buf.append(" '' as C_CHECK_BY,'' as C_CHECK_TIME , ");
		buf.append(" 'ASS_TYPE' as DATA_TYPE,' ' as C_DV_PORT_CODE, ");
		buf.append(" c_dat_code_p as C_PORT_CODE_P,'' as C_HDAY_CODE, '' as C_DV_PROD_STATE, '' as C_PORT_UNIT,");
		buf.append(" '' as C_DAT_CLS ");
		buf.append(" from  T_S_DAT_ASS_TYPE ");
		buf.append(" union all ");
		// // add by weijj '' as C_DAT_CLS 20130604
		buf.append(" select b.C_IDEN,b.C_PORT_CODE, b.C_DAT_CLS,");
		buf.append(" (case when trim(b.C_PORT_CODE_P) is null then b.C_DAT_CODE else b.C_PORT_CODE_P end) as fParaentCode, ");
		buf.append(" b.C_PORT_CODE AS nodeCode, ");
		buf.append(" b.C_PORT_CODE,b.C_PORT_NAME_ST, ");
		buf.append(" b.C_PORT_NAME_EN,b.C_ASS_CODE, ");
		buf.append(" b.C_DC_CODE,b.D_BUILD, ");
		buf.append(" b.D_CLOSE,b.D_CLEAR,b.C_DESC, "); // 添加清算字段
		buf.append(" b.C_PORT_NAME,b.N_CHECK_STATE, ");
		buf.append(" b.C_UPDATE_BY,b.C_UPDATE_TIME, ");
		buf.append(" b.C_CHECK_BY,b.C_CHECK_TIME, ");
		buf.append(" 'PORT_TYPE' as DATA_TYPE,b.C_DV_PORT_CODE, ");
		buf.append(" b.C_PORT_CODE_P,b.C_HDAY_CODE,b.C_DV_PROD_STATE,b.C_PORT_UNIT,");
		buf.append(" b.C_DAT_CLS ");
		buf.append(" from T_P_AB_PORT b ");
		buf.append(" where b.N_CHECK_STATE = 1  ");
		buf.append(" and not exists  ");
		// // add by weijj 20130604 start
		buf.append(" (select * from T_P_AB_ASS_TR_SUB a  where a.N_CHECK_STATE >= 0  ");
		buf.append(" and a.c_port_code = b.c_port_code ");

		if (paraBuf.length() > 0) {
			buf.append(" AND ").append(paraBuf);
		}
		// // add by weijj 20130604 end
		buf.append(" )c ");

		if (paraBuf.length() > 0) {
			buf.append(" WHERE ").append(paraBuf);
		}

		return buf.toString();
	}

	public String buildDeleteSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String buildInsertSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String buildSelectSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String buildUpdateSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbnameresolver,
			String s) {
		return dbnameresolver.getColumnName(PortColumnName.valueOf(s));
	}

//	public String getTreeViewColumnNameByProperty(
//			DBNameResolver dbnameresolver, String s) {
//		return dbnameresolver.getColumnName(PortTreeViewColumnName.valueOf(s));
//	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(PortTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(PortTableName.userInfo);
	}

	public String getParamSetPortListSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		buf.append(" select level, c.* ");
		buf.append(" from ");
		buf.append(" ( ");
		buf.append("  	select b.C_DAT_CODE as C_DAT_CODE, ");
		buf.append("	 b.c_dat_code_p as fParaentCode, ");
		buf.append("	 b.C_DAT_CODE as C_PORT_CODE, ");
		buf.append("	 b.C_DAT_NAME as C_PORT_NAME_ST,'' as C_PORT_NAME_EN, ");
		buf.append("	 '' as C_ASS_CODE,''as C_DC_CODE, ");
		buf.append("	 to_date('9998-12-31','YYYY-MM-DD') as D_BUILD, ");
		buf.append("	 to_date('9998-12-31','YYYY-MM-DD') as D_CLOSE, ");
		buf.append("     to_date('9998-12-31','YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		buf.append("	 '' as C_DESC,'' as C_PORT_NAME,1 as N_CHECK_STATE, ");
		buf.append("	 '' as C_UPDATE_BY,'' as C_UPDATE_TIME,'' as C_CHECK_BY,");
		buf.append("	 '' as C_CHECK_TIME,'ASS_TYPE' as DATA_TYPE, ");
		buf.append("	 ' ' as C_DV_PORT_CODE,'' as C_PORT_CODE_P, ");
		buf.append("	 '' as C_HDAY_CODE ,'' as C_DV_PROD_STATE, '' as C_PORT_UNIT");
		buf.append("	 ,'' as C_DAT_CLS ");// add by zhaoxianlin 20130518 story
		// #3659
		buf.append("	from  T_S_DAT_ASS_TYPE b ");
		buf.append("	WHERE b.C_DAT_CODE = ? ");
		buf.append("	UNION ALL ");
		buf.append("	select b.C_PORT_CODE, ");
		buf.append("	 case ");
		buf.append("	  when trim(b.C_PORT_CODE_P) is null then ");
		buf.append("	    b.C_DAT_CODE ");
		buf.append("	  else ");
		buf.append("	    b.C_PORT_CODE_P ");
		buf.append("	 end AS fParaentCode, ");
		buf.append("	 b.C_PORT_CODE,b.C_PORT_NAME_ST, ");
		buf.append("	 b.C_PORT_NAME_EN,b.C_ASS_CODE,b.C_DC_CODE, ");
		buf.append("	 b.D_BUILD,b.D_CLOSE,b.D_CLEAR,b.C_DESC, b.C_PORT_NAME, "); // 添加清算字段
		buf.append("	 b.N_CHECK_STATE,b.C_UPDATE_BY,b.C_UPDATE_TIME, ");
		buf.append("	 b.C_CHECK_BY,b.C_CHECK_TIME,'PORT_TYPE' as DATA_TYPE, ");
		buf.append("	 b.C_DV_PORT_CODE,b.C_PORT_CODE_P,b.C_HDAY_CODE, b.C_DV_PROD_STATE,b.C_PORT_UNIT");
		buf.append("	 ,b.C_DAT_CLS ");// add by zhaoxianlin 20130518 story #3659
		buf.append("	from T_P_AB_PORT b ");
		buf.append("	  RIGHT JOIN ( ");
		buf.append("	    select COLUMN_VALUE AS C_PORT_CODE ");
		buf.append("	    FROM TABLE(?)) d on 1=1 ");
		buf.append("	    where b.N_CHECK_STATE = 1 ");
		buf.append("	    and b.c_port_code = d.C_PORT_CODE ");
		buf.append("	    and b.C_DAT_CODE = ? ");
		buf.append("	    and not exists ( ");
		buf.append("	     select c_port_code from t_p_ao_params a ");
		buf.append("	     where a.N_CHECK_STATE = 1 ");
		buf.append("	     and a.c_port_code = b.c_port_code ");
		buf.append("	     AND a.C_DSP_CODE = ? ");
		buf.append("	     and (( TO_DATE(?, 'yyyy-MM-dd') >= a.d_begin ");
		buf.append("	     		 and ");
		buf.append("				TO_DATE(?, 'yyyy-MM-dd') <= a.d_end ");
		buf.append("		 or ( TO_DATE(?, 'yyyy-MM-dd') ");
		buf.append("			between a.d_begin and a.d_end) ");
		buf.append("		 or	( TO_DATE(?, 'yyyy-MM-dd') ");
		buf.append("		    between a.d_begin and a.d_end) ");
		buf.append("		 or	(to_date(?, 'yyyy-MM-dd') >= a.d_begin and ");
		buf.append("		 to_date(?, 'yyyy-MM-dd') <= a.d_end) ");
		buf.append("		 ) c ");
		buf.append("		start with c.c_port_code in ");
		buf.append("		 (SELECT * FROM TABLE(?)) ");
		buf.append("		 connect by  c.C_DAT_CODE = prior c.fParaentCode ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);

		return sql;
	}

	public String getRightManagePortListExpertAddSql(
			HashMap<String, Object> paraMap) {
		StringBuffer buf = new StringBuffer();

		String jobTitle = (String) paraMap.get("jobTitle");
		String sql = "";

		buf.append(" select level, c.* from (");
		buf.append("   select '' AS C_IDEN, ");
		buf.append("     C_DAT_CODE as C_DAT_CODE,");
		buf.append("     c_dat_code_p as fParaentCode, ");
		buf.append("     C_DAT_CODE as C_PORT_CODE, ");
		buf.append("     C_DAT_NAME as C_PORT_NAME_ST, ");
		buf.append("     '' as C_PORT_NAME_EN, '' as C_ASS_CODE, ");
		buf.append("     ''as C_DC_CODE, ");
		buf.append("     to_date('1999-12-14','YYYY-MM-DD') as D_BUILD, ");
		buf.append("     to_date('1999-12-14','YYYY-MM-DD') as D_CLOSE, ");
		buf.append("     to_date('9998-12-31','YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		buf.append("     '' as C_DESC,'' as C_PORT_NAME, ");
		buf.append("     1 as N_CHECK_STATE, '' as C_UPDATE_BY, ");
		buf.append("     '' as C_UPDATE_TIME,'' as C_CHECK_BY, ");
		buf.append("     '' as C_CHECK_TIME,'ASS_TYPE' as DATA_TYPE, ");
		buf.append("     ' ' as C_DV_PORT_CODE,'' as C_PORT_CODE_P, ");
		buf.append("     '' as C_HDAY_CODE, '' as C_DV_PROD_STATE, '' as C_PORT_UNIT");
		buf.append("     ,'' as C_DAT_CLS ");// add by zhaoxianlin 20130518
		// story #3659
		buf.append("   from  T_S_DAT_ASS_TYPE ");

		buf.append("   union all ");

		buf.append("   select b.C_IDEN, b.C_PORT_CODE, ");
		buf.append("     case ");
		buf.append("       when trim(b.C_PORT_CODE_P) is null then ");
		buf.append("         b.C_DAT_CODE ");
		buf.append("       else b.C_PORT_CODE_P end ");
		buf.append("       as fParaentCode, ");
		buf.append("       b.C_PORT_CODE,b.C_PORT_NAME_ST, ");
		buf.append("       b.C_PORT_NAME_EN,b.C_ASS_CODE, ");
		buf.append("       b.C_DC_CODE,b.D_BUILD,b.D_CLOSE,b.D_CLEAR, "); // 添加清算字段
		buf.append("       b.C_DESC, b.C_PORT_NAME,b.N_CHECK_STATE, ");
		buf.append("       b.C_UPDATE_BY,b.C_UPDATE_TIME, ");
		buf.append("       b.C_CHECK_BY,b.C_CHECK_TIME, ");
		buf.append("       'PORT_TYPE' as DATA_TYPE, ");
		buf.append("       b.C_DV_PORT_CODE,b.C_PORT_CODE_P, ");
		buf.append("       b.C_HDAY_CODE,b.C_DV_PROD_STATE,b.C_PORT_UNIT ");
		buf.append("       ,b.C_DAT_CLS ");// add by zhaoxianlin 20130518 story
		// #3659
		buf.append("     from T_P_AB_PORT b ");
		buf.append("     where b.N_CHECK_STATE = 1 ) c ");

		if (YssCons.JOB_TITLE.equals(jobTitle)) {
			buf.append("           start with c.C_PORT_CODE in( ");
			buf.append("             select b.C_PORT_CODE  ");
			buf.append("             from T_P_AB_PORT b ");
			buf.append("             where b.N_CHECK_STATE = 1 ");
			buf.append("           ) ");
			buf.append("     connect by prior c.fParaentCode = c.C_DAT_CODE ");
		} else {
			if (paraMap.containsKey("ARRAY_PORT_CODE")) {
				buf.append("           start with c.C_PORT_CODE in( ");
				buf.append("             SELECT * FROM TABLE(?)  ");
				buf.append("           ) ");
				buf.append("connect by  c.C_DAT_CODE = prior c.fParaentCode ");
			} else if (paraMap.containsKey("C_PORT_CODE")) {
				buf.append("           start with c.C_PORT_CODE = ? ");
				buf.append("connect by  c.C_DAT_CODE = prior c.fParaentCode ");
			} else {
				buf.append(" start with c.c_ass_code <> c.c_port_code ");
				buf.append(" connect by c.C_DAT_CODE = prior c.fParaentCode ");
			}
		}

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);

		return sql;
	}

	public String getRightManagePortList(HashMap<String, Object> paraMap) {
		StringBuffer buf = new StringBuffer();

		String jobTitle = (String) paraMap.get("jobTitle");
		String sql = "";

		buf.append(" select level, c.* from (");
		buf.append("   select '' AS C_IDEN, ");
		buf.append("     C_DAT_CODE as C_DAT_CODE,");
		buf.append("     c_dat_code_p as fParaentCode, ");
		buf.append("     C_DAT_CODE as C_PORT_CODE, ");
		buf.append("     C_DAT_NAME as C_PORT_NAME_ST, ");
		buf.append("     '' as C_PORT_NAME_EN, '' as C_ASS_CODE, ");
		buf.append("     ''as C_DC_CODE, ");
		buf.append("     to_date('1999-12-14','YYYY-MM-DD') as D_BUILD, ");
		buf.append("     to_date('1999-12-14','YYYY-MM-DD') as D_CLOSE, ");
		buf.append("     to_date('9998-12-31','YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		buf.append("     '' as C_DESC,'' as C_PORT_NAME, ");
		buf.append("     1 as N_CHECK_STATE, '' as C_UPDATE_BY, ");
		buf.append("     '' as C_UPDATE_TIME,'' as C_CHECK_BY, ");
		buf.append("     '' as C_CHECK_TIME,'ASS_TYPE' as DATA_TYPE, ");
		buf.append("     ' ' as C_DV_PORT_CODE,'' as C_PORT_CODE_P, ");
		buf.append("     '' as C_HDAY_CODE,'' as C_DV_PROD_STATE, '' as C_PORT_UNIT ");
		buf.append("     ,'' as C_DAT_CLS ");// add by zhaoxianlin 20130518
		// story #3659
		buf.append("   from  T_S_DAT_ASS_TYPE ");

		buf.append("   union all ");

		buf.append("   select b.C_IDEN, b.C_PORT_CODE, ");
		buf.append("     case ");
		buf.append("       when trim(b.C_PORT_CODE_P) is null then ");
		buf.append("         b.C_DAT_CODE ");
		buf.append("       else b.C_PORT_CODE_P end ");
		buf.append("       as fParaentCode, ");
		buf.append("       b.C_PORT_CODE,b.C_PORT_NAME_ST, ");
		buf.append("       b.C_PORT_NAME_EN,b.C_ASS_CODE, ");
		buf.append("       b.C_DC_CODE,b.D_BUILD,b.D_CLOSE,b.D_CLEAR, "); // 添加清算字段
		buf.append("       b.C_DESC, b.C_PORT_NAME,b.N_CHECK_STATE, ");
		buf.append("       b.C_UPDATE_BY,b.C_UPDATE_TIME, ");
		buf.append("       b.C_CHECK_BY,b.C_CHECK_TIME, ");
		buf.append("       'PORT_TYPE' as DATA_TYPE, ");
		buf.append("       b.C_DV_PORT_CODE,b.C_PORT_CODE_P, ");
		buf.append("       b.C_HDAY_CODE,b.C_DV_PROD_STATE,b.C_PORT_UNIT ");
		buf.append("       ,b.C_DAT_CLS "); // add by zhaoxianlin 20130518 story
		// #3659
		buf.append("     from T_P_AB_PORT b ");
		buf.append("     where b.N_CHECK_STATE = 1 ");
		buf.append("       and not exists ( ");
		buf.append("         select C_PORT_CODE from t_s_user_rela a ");
		buf.append("         where a.N_CHECK_STATE = 1 ");
		buf.append("           AND a.C_PORT_CODE = b.C_PORT_CODE ");
		buf.append("           AND a.C_USER_CODE = ? ");
		buf.append("           AND a.C_POST_CODE = ?)) c ");

		if (YssCons.JOB_TITLE.equals(jobTitle)) {
			buf.append("           start with c.C_PORT_CODE in( ");
			buf.append("             select b.C_PORT_CODE  ");
			buf.append("             from T_P_AB_PORT b ");
			buf.append("             where b.N_CHECK_STATE = 1 ");
			buf.append("           ) ");
			buf.append("     connect by prior c.fParaentCode = c.C_DAT_CODE ");
		} else {
			if (paraMap.containsKey("ARRAY_PORT_CODE")) {
				buf.append("           start with c.C_PORT_CODE in( ");
				buf.append("             SELECT * FROM TABLE(?)  ");
				buf.append("           ) ");
				buf.append("connect by  c.C_DAT_CODE = prior c.fParaentCode ");
			} else if (paraMap.containsKey("C_PORT_CODE")) {
				buf.append("           start with c.C_PORT_CODE = ? ");
				buf.append("connect by  c.C_DAT_CODE = prior c.fParaentCode ");
			} else {
				buf.append(" start with c.c_ass_code <> c.c_port_code ");
				buf.append(" connect by c.C_DAT_CODE = prior c.fParaentCode ");
			}
		}

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);

		return sql;
	}

	public String getDspPortSql(HashMap<String, Object> paraMap) {
		StringBuffer buf = new StringBuffer();
		String c1 = "";
		String c2 = "";
		String resSql = "";

		buf.append(" select level, c.* ");
		buf.append("   from (select '' as C_IDEN ,b.C_DAT_CODE as C_DAT_CODE, ");
		buf.append("                b.c_dat_code_p as C_PORT_CODE_P, ");
		buf.append("                b.C_DAT_CODE as C_PORT_CODE, ");
		buf.append("                b.C_DAT_NAME as C_PORT_NAME_ST, ");
		buf.append("                '' as C_PORT_NAME_EN, ");
		buf.append("                '' as C_ASS_CODE, ");
		buf.append("                '' as C_DC_CODE, ");
		buf.append("                to_date('1998-01-01', 'YYYY-MM-DD') as D_BUILD, ");
		buf.append("                to_date('9998-12-31', 'YYYY-MM-DD') as D_CLOSE, ");
		buf.append(" to_date('9998-12-31', 'YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		buf.append("                '' as C_DESC, ");
		buf.append("                '' as C_PORT_NAME, ");
		buf.append("                1 as N_CHECK_STATE, ");
		buf.append("                '' as C_UPDATE_BY, ");
		buf.append("                '' as C_UPDATE_TIME, ");
		buf.append("                '' as C_CHECK_BY, ");
		buf.append("                '' as C_CHECK_TIME, ");
		buf.append("                'ASS_TYPE' as DATA_TYPE, ");
		buf.append("                ' ' as C_DV_PORT_CODE, ");
		buf.append("                '' as C_HDAY_CODE,'' as C_DV_PROD_STATE, '' as C_PORT_UNIT ");
		buf.append("                ,'' as C_DAT_CLS ");// add by zhaoxianlin
		// 20130518 story #3659
		buf.append("           from T_S_DAT_ASS_TYPE b ");
		buf.append(" 					CON1 ");
		buf.append("         union all ");
		buf.append("         select b.C_IDEN ,b.C_PORT_CODE, ");
		buf.append("                (case ");
		buf.append("                  when trim(b.C_PORT_CODE_P) is null then ");
		buf.append("                   b.C_DAT_CODE ");
		buf.append("                  else ");
		buf.append("                   b.C_PORT_CODE_P ");
		buf.append("                end) as C_PORT_CODE_P, ");
		buf.append("                b.C_PORT_CODE, ");
		buf.append("                b.C_PORT_NAME_ST, ");
		buf.append("                b.C_PORT_NAME_EN, ");
		buf.append("                b.C_ASS_CODE, ");
		buf.append("                b.C_DC_CODE, ");
		buf.append("                b.D_BUILD, ");
		buf.append("                b.D_CLOSE, ");
		buf.append(" b.D_CLEAR, "); // 添加清算字段
		buf.append("                b.C_DESC, ");
		buf.append("                b.C_PORT_NAME, ");
		buf.append("                b.N_CHECK_STATE, ");
		buf.append("                b.C_UPDATE_BY, ");
		buf.append("                b.C_UPDATE_TIME, ");
		buf.append("                b.C_CHECK_BY, ");
		buf.append("                b.C_CHECK_TIME, ");
		buf.append("                'PORT_TYPE' as DATA_TYPE, ");
		buf.append("                b.C_DV_PORT_CODE, ");
		buf.append("                b.C_HDAY_CODE ,b.C_DV_PROD_STATE,b.C_PORT_UNIT");
		buf.append("                ,b.C_DAT_CLS ");// add by zhaoxianlin
		// 20130518 story #3659
		buf.append("           from T_P_AB_PORT b ");
		buf.append("          RIGHT JOIN (select COLUMN_VALUE AS C_PORT_CODE ");
		buf.append("                        FROM TABLE(?)) d ");
		// buf.append("   RIGHT JOIN (SELECT COLUMN_VALUE AS C_PORT_CODE FROM TABLE(?)) d ");
		buf.append("             on 1 = 1 ");
		buf.append("          where b.N_CHECK_STATE = 1 ");
		buf.append("            and b.c_port_code = d.C_PORT_CODE ");
		buf.append(" 					 CON2 ");
		// buf.append("            and not exists ");
		// buf.append("          (select c_port_code ");
		// buf.append("                   from t_p_ao_params a ");
		// buf.append("                  where a.N_CHECK_STATE >=0 ");
		// buf.append("                    and a.c_port_code = b.c_port_code ");
		// buf.append("                    AND a.C_DSP_CODE = ? ");
		// buf.append("                    and ((to_date(?, 'yyyy-MM-dd') >= a.d_begin and   /* start */ ");
		// buf.append("                        to_date(?, 'yyyy-MM-dd') <= a.d_end) or       /* end */ ");
		// buf.append("                        (to_date(?, 'yyyy-MM-dd') between              /* end */ ");
		// buf.append("                        a.d_begin and a.d_end) or ");
		// buf.append("                        (to_date(?, 'yyyy-MM-dd') between           /* start */ ");
		// buf.append("                        a.d_begin and a.d_end) ))) c");
		buf.append("                        ) c");
		// buf.append("                        (to_date(?, 'yyyy-MM-dd') >= a.d_begin and  /* start */ ");
		// buf.append("                        to_date(?, 'yyyy-MM-dd') <= a.d_end)))) c    /* end */ ");
		// buf.append("  start with c.c_port_code in (SELECT * FROM TABLE(cast(?) as vartabletype)) ");
		buf.append("  start with c.c_port_code in (SELECT * ");
		buf.append("                                 FROM TABLE(?)) ");
		buf.append(" connect by c.C_DAT_CODE = prior c.C_PORT_CODE_P ");
		resSql = buf.toString();
		if (null != paraMap && paraMap.containsKey("C_DAT_CODE")) {
			c1 = " WHERE  b.C_DAT_CODE = ? ";
			c2 = " AND b.C_DAT_CODE =?  ";
			resSql = resSql.replace("CON1", c1);
			resSql = resSql.replace("CON2", c2);
		} else if (null != paraMap && paraMap.containsKey("C_DAT_CLS")) {// add
			// by
			// zhaoxianlin
			// story
			// #3659
			c2 = " AND b.C_DAT_CLS =?  ";
			resSql = resSql.replace("CON1", " ");
			resSql = resSql.replace("CON2", c2);
		} else {
			resSql = resSql.replace("CON1", " ");
			resSql = resSql.replace("CON2", " ");
		}

		return resSql;

	}

	public String getDataByBrowSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		buf.append("   select distinct b.*, 'PORT_TYPE' as DATA_TYPE FROM t_p_ao_params a left JOIN t_p_ab_port b   ");
		buf.append("  on a.c_port_code = b.c_port_code left  join V_S_DSP_PARA c ON   B.c_dat_cls=c.c_dat_code or c.C_Dat_code='PUB'  ");
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals(("C_PORT_CODE"))) {
				valueFieldbuf.append("  a.C_PORT_CODE = ? AND ");
			} else if (fieldedName.equals("C_DSP_CODE")) {
				valueFieldbuf.append(" c.C_DSP_CODE = ? AND ");
			} else if (fieldedName.equals("D_BEGIN")) {
				valueFieldbuf
						.append(" a.D_BEGIN = to_date(?,'yyyy-MM-dd') AND ");
			} else if (fieldedName.equals("D_END")) {
				valueFieldbuf.append(" a.D_END = to_date(?,'yyyy-MM-dd') AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}

		if (valueFieldbuf.length() > 0) {
			buf.append(" where ").append(valueFieldbuf);
		}

		return buf.toString();
	}

	public void setCommonDataListSqlBuf(DBNameResolver dbnameresolver,
			StringBuffer buf) {
		buf.append(" SELECT ");
		buf.append(" a.");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_CODE"));
		buf.append(", ");
		buf.append(" a.");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_NAME"));
		buf.append(", ");
		buf.append(" a.");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_NAME_ST"));
		buf.append(", ");
		buf.append(" a.");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_NAME_EN"));
		buf.append(", ");
		buf.append(" a.");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_ASS_CODE"));
		buf.append(", ");
		buf.append(" a.");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DC_CODE"));
		buf.append(", ");
		buf.append(" a.");
		buf.append(getColumnNameByProperty(dbnameresolver, "d_BUILD"));
		buf.append(", ");
		buf.append(" a.");
		buf.append(getColumnNameByProperty(dbnameresolver, "d_CLOSE"));
		buf.append(", ");
		buf.append(" a.");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DESC"));
		buf.append(", ");
		buf.append(" a.");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DV_PORT_CODE"));
		buf.append(", ");
		buf.append(" a.");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_CODE_P"));
		buf.append(", ");
		buf.append(" a.");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_HDAY_CODE"));
		buf.append(", ");
		buf.append(" a.");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DV_PROD_STATE"));
		buf.append(", ");
		buf.append(" a.");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_UNIT"));
		buf.append(", ");
		buf.append(" a.");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DAT_CLS"));
		buf.append(", ");
		buf.append(" a.");
		buf.append(getColumnNameByProperty(dbnameresolver, "id"));
		buf.append(", ");
		buf.append(" a.");
		buf.append(getColumnNameByProperty(dbnameresolver, "operator"));
		buf.append(", ");
		buf.append(" a.");
		buf.append(getColumnNameByProperty(dbnameresolver, "auditDate"));
		buf.append(", ");
		buf.append(" a.");
		buf.append(getColumnNameByProperty(dbnameresolver, "modifier"));
		buf.append(", ");
		buf.append(" a.");
		buf.append(getColumnNameByProperty(dbnameresolver, "modifyDate"));
		buf.append(", ");
		buf.append(" a.");
		buf.append(getColumnNameByProperty(dbnameresolver, "auditState"));
		buf.append(", ");
		buf.append(" a.");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DAT_CODE"));
		// buf.append(" AS　fParaentCode, "); edit by weijj 20130720 老方法中的东西
		buf.append(" , ");
		buf.append(" a.D_CLEAR, a.D_COLSE_ACC,"); // 添加清算字段
		buf.append(" 'PORT_TYPE' AS DATA_TYPE ");
		// buf.append(getColumnNameByProperty(dbnameresolver, "dATA_TYPE"));
		buf.append(" FROM ");
		buf.append(getTableName(dbnameresolver));
		buf.append(" a ");
	}

	// STORY #4061 A区组合资产类型列转换为名字展示 ，修改最后一列的显示 --liuchi/2013.6.20
	/**
	 * 这里修改查询资产类型结构时 需要更新@see {@link #setCommonTreeSqlBufOnlyWithTssType(DBNameResolver, StringBuffer)}
	 * */
	public void setCommonTreeSqlBuf(DBNameResolver dbnameresolver,
			StringBuffer buf) {
		buf.append(" select '' AS C_IDEN, ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DAT_CODE"));
		buf.append(" AS ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DAT_CODE"));
		buf.append(", ");
		buf.append(" c_dat_code_p as c_PORT_CODE_P, ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DAT_CODE"));
		buf.append(" AS ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_CODE"));
		buf.append(", ");
		buf.append(" C_DAT_NAME as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_NAME_ST"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_NAME_EN"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_ASS_CODE"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DC_CODE"));
		buf.append(", ");
		buf.append(" to_date('1999-12-14', 'yyyy-MM-dd') as  ");
		buf.append(getColumnNameByProperty(dbnameresolver, "d_BUILD"));
		buf.append(", ");
		buf.append(" to_date('1999-12-14', 'yyyy-MM-dd') as  ");
		buf.append(getColumnNameByProperty(dbnameresolver, "d_CLOSE"));
		buf.append(", ");
		buf.append(" to_date('9998-12-31', 'YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DESC"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_NAME"));
		buf.append(", ");
		buf.append(" 1 as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "auditState"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "modifier"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "modifyDate"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "operator"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "auditDate"));
		buf.append(", ");
		buf.append(" 'ASS_TYPE' as DATA_TYPE ");
		// buf.append(getColumnNameByProperty(dbnameresolver, "dATA_TYPE"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DV_PORT_CODE"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_HDAY_CODE"));
		buf.append(",'' as C_DV_PROD_STATE"); // add by weijj STORY #4023
		buf.append(",'' as C_PORT_UNIT"); // add by weijj STORY #4023
											// 2013712
		buf.append(", C_DAT_NAME "); // bug 8610 by lihaizhi 20130711
		buf.append(" AS C_DAT_CLS ");
		buf.append("  FROM T_S_DAT_ASS_TYPE ");
		// 39 T_S_DAT_ASS_TYPE C_DAT_TYPE 字段还没有值，2013-6-3需要确认，确认前先注掉这个条件。
		// buf.append(" where C_DAT_TYPE = 'ASS_TYPE' ");
		buf.append("  union all ");
		buf.append("  select  b.C_IDEN, ");
		buf.append(" b.");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_CODE"));
		buf.append(", ");
		buf.append(" (case ");
		buf.append("   when trim(b.C_PORT_CODE_P) is null then ");
		buf.append("     b.C_DAT_CODE ");
		buf.append("   else ");
		buf.append("     b.C_PORT_CODE_P ");
		buf.append("   end) as C_PORT_CODE_P, ");
		buf.append(" b.");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_CODE"));
		buf.append(", ");
		buf.append(" b.");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_NAME_ST"));
		buf.append(", ");
		buf.append(" b.");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_NAME_EN"));
		buf.append(", ");
		buf.append(" b.");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_ASS_CODE"));
		buf.append(", ");
		buf.append(" b.");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DC_CODE"));
		buf.append(", ");
		buf.append(" b.");
		buf.append(getColumnNameByProperty(dbnameresolver, "d_BUILD"));
		buf.append(", ");
		buf.append(" b.");
		buf.append(getColumnNameByProperty(dbnameresolver, "d_CLOSE"));
		buf.append(", ");
		buf.append(" b.D_CLEAR, "); // 添加清算字段
		buf.append(" b.");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DESC"));
		buf.append(", ");
		buf.append(" b.");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_NAME"));
		buf.append(", ");
		buf.append(" b.");
		buf.append(getColumnNameByProperty(dbnameresolver, "auditState"));
		buf.append(", ");
		buf.append(" b.");
		buf.append(getColumnNameByProperty(dbnameresolver, "modifier"));
		buf.append(", ");
		buf.append(" b.");
		buf.append(getColumnNameByProperty(dbnameresolver, "modifyDate"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "operator"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "auditDate"));
		buf.append(", ");
		buf.append(" 'PORT_TYPE' as DATA_TYPE ");
		// buf.append(getColumnNameByProperty(dbnameresolver, "dATA_TYPE"));
		buf.append(", ");
		buf.append(" b.");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DV_PORT_CODE"));
		buf.append(", ");
		buf.append(" b.");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_HDAY_CODE"));

		buf.append(", b.C_DV_PROD_STATE"); // add by weijj STORY #4023 2013712
		buf.append(", b.C_PORT_UNIT"); // add by weijj STORY #4023 2013712
		buf.append(", C.C_DAT_NAME "); // bug 8610 by lihaizhi 20130711
		// buf.append(PortColumnName.c_DAT_CLS.toString());
		buf.append(" FROM ");
		buf.append(getTableName(dbnameresolver));
		buf.append(" b LEFT JOIN (SELECT C_DAT_name,c_dat_code FROM  T_S_DAT_ASS_TYPE) C ON B.C_DAT_cls = c.C_DAT_code WHERE ");
		buf.append(" trim(b.c_port_code) <> 'ALL' and b.");
		buf.append(getColumnNameByProperty(dbnameresolver, "auditState"));
		buf.append(" = 1 AND ");
		buf.append(PortColumnName.c_DAT_CLS.toString());
		buf.append(" IS NOT NULL AND b.");
		buf.append(PortColumnName.c_DAT_CODE.toString());
		buf.append(" IS NOT NULL");
	}
	
	/**
	 * 
	 * 构造只查询资产类型的sql
	 * @see #setCommonTreeSqlBuf(DBNameResolver, StringBuffer)
	 * */
	public void setCommonTreeSqlBufOnlyWithTssType(DBNameResolver dbnameresolver,
			StringBuffer buf) {
		buf.append(" select '' AS C_IDEN, ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DAT_CODE"));
		buf.append(" AS ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DAT_CODE"));
		buf.append(", ");
		buf.append(" c_dat_code_p as c_PORT_CODE_P, ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DAT_CODE"));
		buf.append(" AS ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_CODE"));
		buf.append(", ");
		buf.append(" C_DAT_NAME as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_NAME_ST"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_NAME_EN"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_ASS_CODE"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DC_CODE"));
		buf.append(", ");
		buf.append(" to_date('1999-12-14', 'yyyy-MM-dd') as  ");
		buf.append(getColumnNameByProperty(dbnameresolver, "d_BUILD"));
		buf.append(", ");
		buf.append(" to_date('1999-12-14', 'yyyy-MM-dd') as  ");
		buf.append(getColumnNameByProperty(dbnameresolver, "d_CLOSE"));
		buf.append(", ");
		buf.append(" to_date('9998-12-31', 'YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DESC"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_NAME"));
		buf.append(", ");
		buf.append(" 1 as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "auditState"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "modifier"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "modifyDate"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "operator"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "auditDate"));
		buf.append(", ");
		buf.append(" 'ASS_TYPE' as DATA_TYPE ");
		// buf.append(getColumnNameByProperty(dbnameresolver, "dATA_TYPE"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DV_PORT_CODE"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_HDAY_CODE"));
		buf.append(",'' as C_DV_PROD_STATE"); // add by weijj STORY #4023
		buf.append(",'' as C_PORT_UNIT"); // add by weijj STORY #4023
		// 2013712
		buf.append(", C_DAT_NAME "); // bug 8610 by lihaizhi 20130711
		buf.append(" AS C_DAT_CLS ");
		buf.append("  FROM T_S_DAT_ASS_TYPE ");
		// 39 T_S_DAT_ASS_TYPE C_DAT_TYPE 字段还没有值，2013-6-3需要确认，确认前先注掉这个条件。
		// buf.append(" where C_DAT_TYPE = 'ASS_TYPE' ");
	}
	
	// STORY #4061 A区组合资产类型列转换为名字展示 ，修改最后一列的显示 --liuchi/2013.6.20
	/**
	 * 该方法修改查询的数据结构时，需要同时修改 {@link #setCommonTreeChildSqlBufOnlyWithTssType(DBNameResolver, StringBuffer, boolean)}
	 * */
		public void setCommonTreeChildSqlBuf(DBNameResolver dbnameresolver,
				StringBuffer buf,boolean flag) {
			buf.append(" select '' AS C_IDEN, ");
			buf.append(getColumnNameByProperty(dbnameresolver, "c_DAT_CODE"));
			buf.append(" AS ");
			buf.append(getColumnNameByProperty(dbnameresolver, "c_DAT_CODE"));
			buf.append(", ");
			buf.append(" c_dat_code_p as c_PORT_CODE_P, ");
			buf.append(getColumnNameByProperty(dbnameresolver, "c_DAT_CODE"));
			buf.append(" AS ");
			buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_CODE"));
			buf.append(", ");
			buf.append(" C_DAT_NAME as ");
			buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_NAME_ST"));
			buf.append(", ");
			buf.append(" '' as ");
			buf.append(getColumnNameByProperty(dbnameresolver, "c_ASS_CODE"));
			buf.append(", ");
			buf.append(" '' as ");
			buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_NAME"));
			buf.append(", ");
			buf.append(" '' as ");
			buf.append(getColumnNameByProperty(dbnameresolver, "c_HDAY_CODE"));
			buf.append(", 1 as ");
			buf.append(getColumnNameByProperty(dbnameresolver, "auditState"));
			buf.append(", ");
			buf.append(" '' as ");
			buf.append(getColumnNameByProperty(dbnameresolver, "c_DV_PORT_CODE"));
			buf.append(", ");
			buf.append(" 'ASS_TYPE' as DATA_TYPE ");
			buf.append(", C_DAT_NAME  AS C_DAT_CLS ");
			buf.append("  FROM T_S_DAT_ASS_TYPE ");
			// 39 T_S_DAT_ASS_TYPE C_DAT_TYPE 字段还没有值，2013-6-3需要确认，确认前先注掉这个条件。
			// buf.append(" where C_DAT_TYPE = 'ASS_TYPE' ");
			buf.append("  union all ");
			buf.append("  select  b.C_IDEN, ");
			buf.append(" b.");
			buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_CODE"));
			buf.append(", ");
			buf.append(" (case ");
			buf.append("   when trim(b.C_PORT_CODE_P) is null then ");
			buf.append("     b.C_DAT_CODE ");
			buf.append("   else ");
			buf.append("     b.C_PORT_CODE_P ");
			buf.append("   end) as C_PORT_CODE_P, ");
			buf.append(" b.");
			buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_CODE"));
			buf.append(", ");
			buf.append(" b.");
			buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_NAME_ST"));
			buf.append(", ");
			buf.append(" b.");
			buf.append(getColumnNameByProperty(dbnameresolver, "c_ASS_CODE"));
			buf.append(", ");
			buf.append(" b.");
			buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_NAME"));
			buf.append(", ");
			buf.append(" b.");
			buf.append(getColumnNameByProperty(dbnameresolver, "c_HDAY_CODE"));
			buf.append(", b.");
			buf.append(getColumnNameByProperty(dbnameresolver, "auditState"));
			buf.append(", ");
			buf.append(" b.");
			buf.append(getColumnNameByProperty(dbnameresolver, "c_DV_PORT_CODE"));
			buf.append(", ");
			buf.append(" 'PORT_TYPE' as DATA_TYPE ");
			buf.append(", C.C_DAT_NAME "); 
			buf.append(" FROM ");
			buf.append(getTableName(dbnameresolver));
			buf.append(" b LEFT JOIN (SELECT C_DAT_name,c_dat_code FROM  T_S_DAT_ASS_TYPE) C ON B.C_DAT_cls = c.C_DAT_code WHERE ");
			buf.append(" trim(b.c_Port_code)<> 'ALL' AND b.");
			buf.append(getColumnNameByProperty(dbnameresolver, "auditState"));
			buf.append(" = 1 AND ");
			buf.append(PortColumnName.c_DAT_CLS.toString());
			buf.append(" IS NOT NULL AND b.");
			buf.append(PortColumnName.c_DAT_CODE.toString());
			buf.append(" IS NOT NULL ");
			if(flag){
				buf.append(" union all                                              ");
				buf.append("       select b.C_IDEN,                                 ");
				buf.append("       		  b.C_PORT_CODE,                            ");
				buf.append("              B.C_PORT_CODE AS C_PORT_CODE_P,           ");
				buf.append("              b.C_PORT_CLS_CODE,                        ");
				buf.append("              b.C_PORT_CLS_NAME,                        ");
				buf.append("              A.C_ASS_CODE,                             ");
				buf.append("              b.c_port_cls_name,                        ");
				buf.append(" a.");
				buf.append(getColumnNameByProperty(dbnameresolver, "c_HDAY_CODE"));
				buf.append(", b.");
				buf.append(getColumnNameByProperty(dbnameresolver, "auditState"));
				buf.append(", ");
				buf.append("              '' AS c_DV_PORT_CODE,         ");
				buf.append("              'PORT_TYPE' as DATA_TYPE,                 ");
				buf.append("              '' AS C_DAT_CODE                          ");
				buf.append("         FROM T_P_AA_PORT_CLS b                         ");
				buf.append(" 			  LEFT JOIN T_P_AB_PORT A                   ");
				buf.append(" 			    ON B.C_PORT_CODE = A.C_PORT_CODE        ");
				buf.append(" 			 WHERE b.N_CHECK_STATE = 1                  ");
			}
		}
		
		// STORY #4061 A区组合资产类型列转换为名字展示 ，修改最后一列的显示 --liuchi/2013.6.20
		/**
		 * 只查询资产类型数据
		 * @see #setCommonTreeChildSqlBuf(DBNameResolver, StringBuffer, boolean)
		 * */
		public void setCommonTreeChildSqlBufOnlyWithTssType(DBNameResolver dbnameresolver,
				StringBuffer buf,boolean flag) {
			buf.append(" select '' AS C_IDEN, ");
			buf.append(getColumnNameByProperty(dbnameresolver, "c_DAT_CODE"));
			buf.append(" AS ");
			buf.append(getColumnNameByProperty(dbnameresolver, "c_DAT_CODE"));
			buf.append(", ");
			buf.append(" c_dat_code_p as c_PORT_CODE_P, ");
			buf.append(getColumnNameByProperty(dbnameresolver, "c_DAT_CODE"));
			buf.append(" AS ");
			buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_CODE"));
			buf.append(", ");
			buf.append(" C_DAT_NAME as ");
			buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_NAME_ST"));
			buf.append(", ");
			buf.append(" '' as ");
			buf.append(getColumnNameByProperty(dbnameresolver, "c_ASS_CODE"));
			buf.append(", ");
			buf.append(" '' as ");
			buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_NAME"));
			buf.append(", ");
			buf.append(" '' as ");
			buf.append(getColumnNameByProperty(dbnameresolver, "c_HDAY_CODE"));
			buf.append(", 1 as ");
			buf.append(getColumnNameByProperty(dbnameresolver, "auditState"));
			buf.append(", ");
			buf.append(" '' as ");
			buf.append(getColumnNameByProperty(dbnameresolver, "c_DV_PORT_CODE"));
			buf.append(", ");
			buf.append(" 'ASS_TYPE' as DATA_TYPE ");
			buf.append(", C_DAT_NAME  AS C_DAT_CLS ");
			buf.append("  FROM T_S_DAT_ASS_TYPE ");
			// 39 T_S_DAT_ASS_TYPE C_DAT_TYPE 字段还没有值，2013-6-3需要确认，确认前先注掉这个条件。
			// buf.append(" where C_DAT_TYPE = 'ASS_TYPE' ");
		}
	 /**
	 * Author : huangshui
	 * Date   : 2016-12-10
	 * Status : add
	 * STORY #34789 南方基金-产品树形结构按托管行自动分类(增加资产托管及托管资产两个默认项)
	 * @return
	 */
	public void setTreeSqlBufZCTG(DBNameResolver dbnameresolver,
			StringBuffer buf,String sql) {
		buf.append(" select '' AS C_IDEN, ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DAT_CODE"));
		buf.append(" AS ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DAT_CODE"));
		buf.append(", ");
		buf.append(" c_dat_code_p as c_PORT_CODE_P, ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DAT_CODE"));
		buf.append(" AS ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_CODE"));
		buf.append(", ");
		buf.append(" C_DAT_NAME as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_NAME_ST"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_NAME_EN"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_ASS_CODE"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DC_CODE"));
		buf.append(", ");
		buf.append(" to_date('1999-12-14', 'yyyy-MM-dd') as  ");
		buf.append(getColumnNameByProperty(dbnameresolver, "d_BUILD"));
		buf.append(", ");
		buf.append(" to_date('1999-12-14', 'yyyy-MM-dd') as  ");
		buf.append(getColumnNameByProperty(dbnameresolver, "d_CLOSE"));
		buf.append(", ");
		buf.append(" to_date('9998-12-31', 'YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DESC"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_NAME"));
		buf.append(", ");
		buf.append(" 1 as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "auditState"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "modifier"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "modifyDate"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "operator"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "auditDate"));
		buf.append(", ");
		buf.append(" 'ASS_TYPE' as DATA_TYPE ");
		// buf.append(getColumnNameByProperty(dbnameresolver, "dATA_TYPE"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DV_PORT_CODE"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_HDAY_CODE"));
		buf.append(",'' as C_DV_PROD_STATE"); // add by weijj STORY #4023
		buf.append(",'' as C_PORT_UNIT"); // add by weijj STORY #4023
											// 2013712
		buf.append(", C_DAT_CODE "); // bug 8610 by lihaizhi 20130711
		buf.append(" AS C_DAT_CLS ");
		buf.append("  FROM T_S_DAT_ASS_TYPE ");
		buf.append(sql);
		buf.append("  union all ");
		buf.append("  select '' AS C_IDEN, '' AS C_DAT_CODE, case when tg.c_org_code_p is null then tg.c_org_code || '' || a.c_dat_code  else tg.c_org_code_p || '' || a.c_dat_code  end AS c_PORT_CODE_P , C_PORT_CODE AS C_PORT_CODE,C_PORT_NAME_ST AS C_PORT_NAME_ST, a.c_port_name AS C_PORT_NAME_EN,'' AS C_ASS_CODE,'' AS C_DC_CODE, ");
		buf.append("   to_date('1999-12-14', 'yyyy-MM-dd') AS D_BUILD, to_date('1999-12-14', 'yyyy-MM-dd') AS D_CLOSE,to_date('9998-12-31', 'YYYY-MM-DD') AS D_CLEAR,'' AS C_DESC,a.c_port_name AS C_PORT_NAME,1 AS N_CHECK_STATE, ");
		buf.append("  '' AS C_UPDATE_BY, '' AS C_UPDATE_TIME,'' AS C_CHECK_BY,'' AS C_CHECK_TIME,'PORT_TYPE' AS DATA_TYPE,'' AS C_DV_PORT_CODE, '' AS C_HDAY_CODE,'' AS C_DV_PROD_STATE,'' AS C_PORT_UNIT,'CLS_PT' AS C_DAT_CLS ");
		buf.append("  from (SELECT b.*,TP.c_rela_code FROM T_P_AB_PORT b left join T_P_AB_PORT_RELA tp   on tp.c_port_code = b.c_port_code");
		buf.append("  where tp.n_check_state = '1' and tp.c_rela_type='RELA_ORG') a left join T_P_BI_ORG tg on a.c_rela_code = tg.c_org_code WHERE tg.c_dv_trustee = 'TRUSTEE'");
		buf.append("  union all ");
		buf.append("  select '' AS C_IDEN, '' AS C_DAT_CODE,");
		buf.append("  'WTG'|| '' || c.c_dat_code AS c_PORT_CODE_P , C_PORT_CODE AS C_PORT_CODE,C_PORT_NAME_ST AS C_PORT_NAME_ST, c.c_port_name AS C_PORT_NAME_EN,'' AS C_ASS_CODE,'' AS C_DC_CODE, ");
		buf.append("   to_date('1999-12-14', 'yyyy-MM-dd') AS D_BUILD, to_date('1999-12-14', 'yyyy-MM-dd') AS D_CLOSE,to_date('9998-12-31', 'YYYY-MM-DD') AS D_CLEAR,'' AS C_DESC,c.c_port_name AS C_PORT_NAME,1 AS N_CHECK_STATE, ");
		buf.append("  '' AS C_UPDATE_BY, '' AS C_UPDATE_TIME,'' AS C_CHECK_BY,'' AS C_CHECK_TIME,'PORT_TYPE' AS DATA_TYPE,'' AS C_DV_PORT_CODE, '' AS C_HDAY_CODE,'' AS C_DV_PROD_STATE,'' AS C_PORT_UNIT,'CLS_PT' AS C_DAT_CLS ");
		buf.append("  from (select * FROM (SELECT b.*,TP.c_rela_code FROM T_P_AB_PORT b left join T_P_AB_PORT_RELA tp   on tp.c_port_code = b.c_port_code");
		buf.append("  where tp.n_check_state = '1' and tp.c_rela_type='RELA_ORG') a left join T_P_BI_ORG tg on a.c_rela_code = tg.c_org_code");
		buf.append("  WHERE tg.c_dv_trustee is null or  tg.c_dv_trustee != 'TRUSTEE')c  where  C_PORT_CODE not in (  select C_PORT_CODE AS C_PORT_CODE from (SELECT b.*, TP.c_rela_code FROM T_P_AB_PORT b");
		buf.append("  left join T_P_AB_PORT_RELA tp on tp.c_port_code = b.c_port_code where tp.n_check_state = '1' and tp.c_rela_type = 'RELA_ORG') a");
		buf.append("  left join T_P_BI_ORG tg on a.c_rela_code = tg.c_org_code WHERE  tg.c_dv_trustee = 'TRUSTEE')");
		buf.append("  union all ");
		buf.append("  select '' AS C_IDEN, '' AS C_DAT_CODE,");
		buf.append("  'WTG'|| '' || a.c_dat_code AS c_PORT_CODE_P , C_PORT_CODE AS C_PORT_CODE,C_PORT_NAME_ST AS C_PORT_NAME_ST, a.c_port_name AS C_PORT_NAME_EN,'' AS C_ASS_CODE,'' AS C_DC_CODE, ");
		buf.append("   to_date('1999-12-14', 'yyyy-MM-dd') AS D_BUILD, to_date('1999-12-14', 'yyyy-MM-dd') AS D_CLOSE,to_date('9998-12-31', 'YYYY-MM-DD') AS D_CLEAR,'' AS C_DESC,a.c_port_name AS C_PORT_NAME,1 AS N_CHECK_STATE, ");
		buf.append("  '' AS C_UPDATE_BY, '' AS C_UPDATE_TIME,'' AS C_CHECK_BY,'' AS C_CHECK_TIME,'PORT_TYPE' AS DATA_TYPE,'' AS C_DV_PORT_CODE, '' AS C_HDAY_CODE,'' AS C_DV_PROD_STATE,'' AS C_PORT_UNIT,'CLS_PT' AS C_DAT_CLS ");
		buf.append("  FROM (SELECT b.* FROM T_P_AB_PORT b where c_port_code not in(select c_port_code from T_P_AB_PORT_RELA tp WHERE tp.n_check_state = '1' AND tp.c_rela_type = 'RELA_ORG')   ) a");
	}

	// STORY #34789 南方基金-产品树形结构按托管行自动分类 --huangshui/2016.12.08
	public void setTreeSqlBufTGZC(DBNameResolver dbnameresolver,
			StringBuffer buf) {
		buf.append("   SELECT '' AS C_IDEN ,'' AS C_DAT_CODE ,'[root]' AS c_PORT_CODE_P,C_ORG_CODE AS C_PORT_CODE,C_ORG_NAME AS C_PORT_NAME_ST,C_ORG_NAME AS C_PORT_NAME_EN,'' AS C_ASS_CODE ");
		buf.append(" ,'' AS C_DC_CODE,to_date('1999-12-14', 'yyyy-MM-dd') AS D_BUILD,to_date('1999-12-14', 'yyyy-MM-dd') AS D_CLOSE ,to_date('9998-12-31', 'YYYY-MM-DD') AS D_CLEAR ");
		buf.append("  ,'' AS C_DESC ,C_ORG_NAME  AS C_PORT_NAME,1 AS N_CHECK_STATE,'' AS C_UPDATE_BY ,'' AS C_UPDATE_TIME ,'' AS C_CHECK_BY ,'' AS C_CHECK_TIME ,'TG_TYPE' AS DATA_TYPE,'' AS C_DV_PORT_CODE");
		buf.append("  ,'' AS C_HDAY_CODE  ,'' AS C_DV_PROD_STATE ,'' AS C_PORT_UNIT,'CLS_PT' AS C_DAT_CLS FROM T_P_BI_ORG ");
		buf.append("  where C_ORG_CODE_p is null and c_dv_trustee ='TRUSTEE' ");
		buf.append("  union all ");
		buf.append("  select '' AS C_IDEN, '' AS C_DAT_CODE,case when tg.c_org_code_p is null then tg.c_org_code || '' || a.c_dat_code else tg.c_org_code_p || '' || a.c_dat_code end AS c_PORT_CODE_P , C_PORT_CODE AS C_PORT_CODE,C_PORT_NAME_ST AS C_PORT_NAME_ST, a.c_port_name AS C_PORT_NAME_EN,'' AS C_ASS_CODE,'' AS C_DC_CODE, ");
		buf.append("   to_date('1999-12-14', 'yyyy-MM-dd') AS D_BUILD, to_date('1999-12-14', 'yyyy-MM-dd') AS D_CLOSE,to_date('9998-12-31', 'YYYY-MM-DD') AS D_CLEAR,'' AS C_DESC,a.c_port_name AS C_PORT_NAME,1 AS N_CHECK_STATE, ");
		buf.append("  '' AS C_UPDATE_BY, '' AS C_UPDATE_TIME,'' AS C_CHECK_BY,'' AS C_CHECK_TIME,'PORT_TYPE' AS DATA_TYPE,'' AS C_DV_PORT_CODE, '' AS C_HDAY_CODE,'' AS C_DV_PROD_STATE,'' AS C_PORT_UNIT,A.c_dat_code AS C_DAT_CLS ");
		buf.append("  from (SELECT b.*,TP.c_rela_code FROM T_P_AB_PORT b left join T_P_AB_PORT_RELA tp   on tp.c_port_code = b.c_port_code");
		buf.append("  where tp.n_check_state = '1' and tp.c_rela_type='RELA_ORG') a left join T_P_BI_ORG tg on a.c_rela_code = tg.c_org_code WHERE tg.c_dv_trustee = 'TRUSTEE'");
		buf.append("  union all ");
		buf.append("  select '' AS C_IDEN, '' AS C_DAT_CODE,");
		buf.append("  'WTG'|| '' || c.c_dat_code AS c_PORT_CODE_P , C_PORT_CODE AS C_PORT_CODE,C_PORT_NAME_ST AS C_PORT_NAME_ST, c.c_port_name AS C_PORT_NAME_EN,'' AS C_ASS_CODE,'' AS C_DC_CODE, ");
		buf.append("   to_date('1999-12-14', 'yyyy-MM-dd') AS D_BUILD, to_date('1999-12-14', 'yyyy-MM-dd') AS D_CLOSE,to_date('9998-12-31', 'YYYY-MM-DD') AS D_CLEAR,'' AS C_DESC,c.c_port_name AS C_PORT_NAME,1 AS N_CHECK_STATE, ");
		buf.append("  '' AS C_UPDATE_BY, '' AS C_UPDATE_TIME,'' AS C_CHECK_BY,'' AS C_CHECK_TIME,'PORT_TYPE' AS DATA_TYPE,'' AS C_DV_PORT_CODE, '' AS C_HDAY_CODE,'' AS C_DV_PROD_STATE,'' AS C_PORT_UNIT,'CLS_PT' AS C_DAT_CLS ");
		buf.append("  from (select * FROM (SELECT b.*,TP.c_rela_code FROM T_P_AB_PORT b left join T_P_AB_PORT_RELA tp   on tp.c_port_code = b.c_port_code");
		buf.append("  where tp.n_check_state = '1' and tp.c_rela_type='RELA_ORG') a left join T_P_BI_ORG tg on a.c_rela_code = tg.c_org_code");
		buf.append("  WHERE tg.c_dv_trustee is null or  tg.c_dv_trustee != 'TRUSTEE')c  where  C_PORT_CODE not in (  select C_PORT_CODE AS C_PORT_CODE from (SELECT b.*, TP.c_rela_code FROM T_P_AB_PORT b");
		buf.append("  left join T_P_AB_PORT_RELA tp on tp.c_port_code = b.c_port_code where tp.n_check_state = '1' and tp.c_rela_type = 'RELA_ORG') a");
		buf.append("  left join T_P_BI_ORG tg on a.c_rela_code = tg.c_org_code WHERE  tg.c_dv_trustee = 'TRUSTEE')");
		buf.append("  union all ");
		buf.append("  select '' AS C_IDEN, '' AS C_DAT_CODE,");
		buf.append("  'WTG'|| '' || a.c_dat_code AS c_PORT_CODE_P , C_PORT_CODE AS C_PORT_CODE,C_PORT_NAME_ST AS C_PORT_NAME_ST, a.c_port_name AS C_PORT_NAME_EN,'' AS C_ASS_CODE,'' AS C_DC_CODE, ");
		buf.append("   to_date('1999-12-14', 'yyyy-MM-dd') AS D_BUILD, to_date('1999-12-14', 'yyyy-MM-dd') AS D_CLOSE,to_date('9998-12-31', 'YYYY-MM-DD') AS D_CLEAR,'' AS C_DESC,a.c_port_name AS C_PORT_NAME,1 AS N_CHECK_STATE, ");
		buf.append("  '' AS C_UPDATE_BY, '' AS C_UPDATE_TIME,'' AS C_CHECK_BY,'' AS C_CHECK_TIME,'PORT_TYPE' AS DATA_TYPE,'' AS C_DV_PORT_CODE, '' AS C_HDAY_CODE,'' AS C_DV_PROD_STATE,'' AS C_PORT_UNIT,'CLS_PT' AS C_DAT_CLS ");
		buf.append("  FROM (SELECT b.* FROM T_P_AB_PORT b where c_port_code not in(select c_port_code from T_P_AB_PORT_RELA tp WHERE tp.n_check_state = '1' AND tp.c_rela_type = 'RELA_ORG')   ) a");
	}

	/**
	 * 
	 * @param dbnamersv
	 * @return
	 */
	public String getAllDataListSql(DBNameResolver dbnamersv) {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		// 添加群组代码转名称 By Jinghehe 2014-6-3
		buf.append(" select * from (  ");
		setCommonDataListSqlBuf(dbnamersv, buf);
		buf.append(" union all  ");
		buf.append(" SELECT a.c_group_code as C_PORT_CODE, ");
		buf.append("        a.c_group_name as C_PORT_NAME, ");
		buf.append("        a.c_group_name as C_PORT_NAME_ST, ");
		buf.append("        a.c_group_name as C_PORT_NAME_EN, ");
		buf.append("        ' ' as C_ASS_CODE, ");
		buf.append("        ' ' as C_DC_CODE, ");
		buf.append("        to_date('1999-12-14','yyyy-MM-dd') as D_BUILD, ");
		buf.append("        to_date('1999-12-14','yyyy-MM-dd') as D_CLOSE, ");
		buf.append("        ' ' as C_DESC, ");
		buf.append("        ' ' as C_DV_PORT_CODE, ");
		buf.append("        ' ' as C_PORT_CODE_P, ");
		buf.append("        ' ' as C_HDAY_CODE, ");
		buf.append("        ' ' as C_DV_PROD_STATE, ");
		buf.append("        ' ' as C_PORT_UNIT, ");
		buf.append("        ' ' as C_DAT_CLS, ");
		buf.append("        a.c_iden as C_IDEN, ");
		buf.append("        ' ' as C_CHECK_BY, ");
		buf.append("        ' ' as C_CHECK_TIME, ");
		buf.append("        ' ' as C_UPDATE_BY, ");
		buf.append("        ' ' as C_UPDATE_TIME, ");
		buf.append("        a.n_check_state as N_CHECK_STATE, ");
		buf.append("        ' ' as C_DAT_CODE, ");
		buf.append("        to_date('9998-12-31', 'YYYY-MM-DD') as D_CLEAR, ");
		//21.6-FAST2.0测试BUG  上下两个查询，结果列不匹配，补充缺失的字段，并给予默认值处理   edit by sunyanlin 20180306
		buf.append("        to_date('9998-12-31', 'YYYY-MM-DD') as D_COLSE_ACC, ");
		buf.append("        'PORT_TYPE' AS DATA_TYPE ");
		buf.append("   FROM t_p_Ab_Group a )");
		retSql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return retSql;
	}

	public String getPortTreeViewCommonSql(DBNameResolver dbnamersv) {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		// buf.append("select c.* from(");
		setCommonTreeSqlBuf(dbnamersv, buf);
		// buf.append(") c ");
		// buf.append(" start with c.c_port_Code_p = '[root]' ");
		// buf.append(" connect by prior c.C_DAT_CODE = c.c_port_Code_p");
		retSql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return retSql;
	}
	/**
	 * 只查询资产类型的sql语句
	 * */
	public String getPortTreeViewCommonSqlOnlyWithTssType(DBNameResolver dbnamersv) {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		setCommonTreeSqlBufOnlyWithTssType(dbnamersv, buf);
		retSql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return retSql;
	}

	/**
	 * 只查询资产类型的sql语句
	 * */
	public String getPortTreeViewCommonSqlChildOnlyWithTssType(DBNameResolver dbnamersv,boolean flag) {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		setCommonTreeChildSqlBufOnlyWithTssType(dbnamersv, buf,flag);
		retSql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return retSql;
	}
	
	public String getPortTreeViewCommonSqlChild(DBNameResolver dbnamersv,boolean flag) {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		// buf.append("select c.* from(");
		setCommonTreeChildSqlBuf(dbnamersv, buf,flag);
		// buf.append(") c ");
		// buf.append(" start with c.c_port_Code_p = '[root]' ");
		// buf.append(" connect by prior c.C_DAT_CODE = c.c_port_Code_p");
		retSql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return retSql;
	}
	
	public String getPortTreeViewZCTGSql(DBNameResolver dbnamersv,String sql) {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		// buf.append("select c.* from(");
		setTreeSqlBufZCTG(dbnamersv, buf, sql);
		// buf.append(") c ");
		// buf.append(" start with c.c_port_Code_p = '[root]' ");
		// buf.append(" connect by prior c.C_DAT_CODE = c.c_port_Code_p");
		retSql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return retSql;
	}
	
	public String getPortTreeViewZCTGSqlBuild(String C_DAT_CODE,String C_DAT_NAME) {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		buf.append("  union all ");
		buf.append("   SELECT '' AS C_IDEN ,'' AS C_DAT_CODE ,'");
		buf.append( C_DAT_CODE+"' AS c_PORT_CODE_P,C_ORG_CODE ||''|| '"+C_DAT_CODE+"' AS C_PORT_CODE,C_ORG_NAME AS C_PORT_NAME_ST,C_ORG_NAME AS C_PORT_NAME_EN,'' AS C_ASS_CODE ");
		buf.append(" ,'' AS C_DC_CODE,to_date('1999-12-14', 'yyyy-MM-dd') AS D_BUILD,to_date('1999-12-14', 'yyyy-MM-dd') AS D_CLOSE ,to_date('9998-12-31', 'YYYY-MM-DD') AS D_CLEAR ");
		buf.append("  ,'' AS C_DESC ,C_ORG_NAME AS C_PORT_NAME,1 AS N_CHECK_STATE,'' AS C_UPDATE_BY ,'' AS C_UPDATE_TIME ,'' AS C_CHECK_BY ,'' AS C_CHECK_TIME ,'TG_TYPE' AS DATA_TYPE,'' AS C_DV_PORT_CODE");
		buf.append("  ,'' AS C_HDAY_CODE  ,'' AS C_DV_PROD_STATE ,'' AS C_PORT_UNIT,'CLS_PT' AS C_DAT_CLS FROM T_P_BI_ORG ");
		buf.append("  where C_ORG_CODE_p is null and c_dv_trustee ='TRUSTEE' ");
		retSql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return retSql;
	}

	public String getPortTreeViewTGZCSql(DBNameResolver dbnamersv) {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		// buf.append("select c.* from(");
		setTreeSqlBufTGZC(dbnamersv, buf);
		// buf.append(") c ");
		// buf.append(" start with c.c_port_Code_p = '[root]' ");
		// buf.append(" connect by prior c.C_DAT_CODE = c.c_port_Code_p");
		retSql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return retSql;
	}
	
	/**
	 * 属性结构-按管理人分类
	 * @param dbnamersv
	 * @return
	 * add by chenyoucai 20180120 STORY #51993 财务报表优化-区分不同管理人出具纳税报表
	 */
	public String getPortTreeViewGLZCSql(DBNameResolver dbnamersv) {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		buf.append("   SELECT '' AS C_IDEN ,'' AS C_DAT_CODE ,'[root]' AS c_PORT_CODE_P,C_ORG_CODE AS C_PORT_CODE,C_ORG_NAME AS C_PORT_NAME_ST,C_ORG_NAME AS C_PORT_NAME_EN,'' AS C_ASS_CODE ");
		buf.append(" ,'' AS C_DC_CODE,to_date('1999-12-14', 'yyyy-MM-dd') AS D_BUILD,to_date('1999-12-14', 'yyyy-MM-dd') AS D_CLOSE ,to_date('9998-12-31', 'YYYY-MM-DD') AS D_CLEAR ");
		buf.append("  ,'' AS C_DESC ,C_ORG_NAME  AS C_PORT_NAME,1 AS N_CHECK_STATE,'' AS C_UPDATE_BY ,'' AS C_UPDATE_TIME ,'' AS C_CHECK_BY ,'' AS C_CHECK_TIME ,'GLR_TYPE' AS DATA_TYPE,'' AS C_DV_PORT_CODE");
		buf.append("  ,'' AS C_HDAY_CODE  ,'' AS C_DV_PROD_STATE ,'' AS C_PORT_UNIT,'CLS_PT' AS C_DAT_CLS FROM T_P_BI_ORG ");
		buf.append("  where C_ORG_CODE_p is null and C_DV_MANAGER = 'MANAGER' ");
		buf.append("  union all ");
		buf.append("  select '' AS C_IDEN, '' AS C_DAT_CODE,case when tg.c_org_code_p is null then tg.c_org_code || '' || a.c_dat_code else tg.c_org_code_p || '' || a.c_dat_code end AS c_PORT_CODE_P , C_PORT_CODE AS C_PORT_CODE,C_PORT_NAME_ST AS C_PORT_NAME_ST, a.c_port_name AS C_PORT_NAME_EN,'' AS C_ASS_CODE,'' AS C_DC_CODE, ");
		buf.append("   to_date('1999-12-14', 'yyyy-MM-dd') AS D_BUILD, to_date('1999-12-14', 'yyyy-MM-dd') AS D_CLOSE,to_date('9998-12-31', 'YYYY-MM-DD') AS D_CLEAR,'' AS C_DESC,a.c_port_name AS C_PORT_NAME,1 AS N_CHECK_STATE, ");
		buf.append("  '' AS C_UPDATE_BY, '' AS C_UPDATE_TIME,'' AS C_CHECK_BY,'' AS C_CHECK_TIME,'PORT_TYPE' AS DATA_TYPE,'' AS C_DV_PORT_CODE, '' AS C_HDAY_CODE,'' AS C_DV_PROD_STATE,'' AS C_PORT_UNIT,A.c_dat_code AS C_DAT_CLS ");
		buf.append("  from (SELECT b.*,TP.c_rela_code FROM T_P_AB_PORT b left join T_P_AB_PORT_RELA tp   on tp.c_port_code = b.c_port_code");
		buf.append("  and tp.n_check_state = '1' and tp.c_rela_type='RELA_ORG' AND TP.C_DV_TYPE_CODE = 'MANAGER') a left join T_P_BI_ORG tg on a.c_rela_code = tg.c_org_code WHERE tg.C_DV_MANAGER = 'MANAGER'");
		buf.append("  union all ");
		buf.append("  select '' AS C_IDEN, '' AS C_DAT_CODE,");
		buf.append("  'WGLR'|| '' || c.c_dat_code AS c_PORT_CODE_P , C_PORT_CODE AS C_PORT_CODE,C_PORT_NAME_ST AS C_PORT_NAME_ST, c.c_port_name AS C_PORT_NAME_EN,'' AS C_ASS_CODE,'' AS C_DC_CODE, ");
		buf.append("   to_date('1999-12-14', 'yyyy-MM-dd') AS D_BUILD, to_date('1999-12-14', 'yyyy-MM-dd') AS D_CLOSE,to_date('9998-12-31', 'YYYY-MM-DD') AS D_CLEAR,'' AS C_DESC,c.c_port_name AS C_PORT_NAME,1 AS N_CHECK_STATE, ");
		buf.append("  '' AS C_UPDATE_BY, '' AS C_UPDATE_TIME,'' AS C_CHECK_BY,'' AS C_CHECK_TIME,'PORT_TYPE' AS DATA_TYPE,'' AS C_DV_PORT_CODE, '' AS C_HDAY_CODE,'' AS C_DV_PROD_STATE,'' AS C_PORT_UNIT, c.c_dat_code AS C_DAT_CLS ");
		buf.append("  from (select * FROM (SELECT b.*,TP.c_rela_code FROM T_P_AB_PORT b left join T_P_AB_PORT_RELA tp   on tp.c_port_code = b.c_port_code");
		buf.append("  and tp.n_check_state = '1' and tp.c_rela_type='RELA_ORG' AND TP.C_DV_TYPE_CODE = 'MANAGER') a left join T_P_BI_ORG tg on a.c_rela_code = tg.c_org_code");
		buf.append("  WHERE tg.C_DV_MANAGER is null or  tg.C_DV_MANAGER != 'MANAGER')c  where  C_PORT_CODE not in (  select C_PORT_CODE AS C_PORT_CODE from (SELECT b.*, TP.c_rela_code FROM T_P_AB_PORT b");
		buf.append("  left join T_P_AB_PORT_RELA tp on tp.c_port_code = b.c_port_code and tp.n_check_state = '1' and tp.c_rela_type = 'RELA_ORG' AND TP.C_DV_TYPE_CODE = 'MANAGER') a");
		buf.append("  left join T_P_BI_ORG tg on a.c_rela_code = tg.c_org_code WHERE  tg.C_DV_MANAGER = 'MANAGER')");
		buf.append("  union all ");
		buf.append("  select '' AS C_IDEN, '' AS C_DAT_CODE,");
		buf.append("  'WGLR'|| '' || a.c_dat_code AS c_PORT_CODE_P , C_PORT_CODE AS C_PORT_CODE,C_PORT_NAME_ST AS C_PORT_NAME_ST, a.c_port_name AS C_PORT_NAME_EN,'' AS C_ASS_CODE,'' AS C_DC_CODE, ");
		buf.append("   to_date('1999-12-14', 'yyyy-MM-dd') AS D_BUILD, to_date('1999-12-14', 'yyyy-MM-dd') AS D_CLOSE,to_date('9998-12-31', 'YYYY-MM-DD') AS D_CLEAR,'' AS C_DESC,a.c_port_name AS C_PORT_NAME,1 AS N_CHECK_STATE, ");
		buf.append("  '' AS C_UPDATE_BY, '' AS C_UPDATE_TIME,'' AS C_CHECK_BY,'' AS C_CHECK_TIME,'PORT_TYPE' AS DATA_TYPE,'' AS C_DV_PORT_CODE, '' AS C_HDAY_CODE,'' AS C_DV_PROD_STATE,'' AS C_PORT_UNIT, A.c_dat_code AS C_DAT_CLS ");
		buf.append("  FROM (SELECT b.* FROM T_P_AB_PORT b where c_port_code not in(select c_port_code from T_P_AB_PORT_RELA tp WHERE tp.n_check_state = '1' AND tp.c_rela_type = 'RELA_ORG' AND TP.C_DV_TYPE_CODE = 'MANAGER')   ) a");
		retSql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return retSql;
	}

	/**
	 * 获取组合树形结构通用查询语句
	 * 
	 * @return
	 */
	public String getPortDataQuyTreeCommonSql() {
		String retSql = "";
		StringBuffer buf = new StringBuffer();

		setPortCommonTreeViewQuySql(buf);

		retSql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return retSql;
	}

	public String getDataByCodeSql(DBNameResolver dbnamersv) {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		// edit by weijj 20130720 只查询组合表，根据组合代码查询组合。不需要树形结构.
		// setCommonTreeSqlBuf(dbnamersv, buf);
		//
		// buf.append(" AND b.");
		// buf.append(getColumnNameByProperty(dbnamersv, "c_PORT_CODE"));
		// buf.append(" = ? ");

		setCommonDataListSqlBuf(dbnamersv, buf);
		buf.append(" where a.");
		buf.append(getColumnNameByProperty(dbnamersv, "c_PORT_CODE"));
		buf.append(" in (select * from table(?)) ");
		retSql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return retSql;
	}

	/**
	 * 获取用户自定义资产树形结构byleeyu20140228
	 * 
	 * @return
	 */
	public String getDefaultUserAssPort() {
		String retSql = "";
		StringBuffer bufSql = new StringBuffer();
		bufSql.append(" select level, d.* ");
		bufSql.append(" from (select C_IDEN,C_TR_CODE as C_DAT_CODE, ");
		bufSql.append(" C_TR_CODE_P as C_PORT_CODE_P, ");
		bufSql.append(" C_TR_CODE as C_PORT_CODE, ");
		bufSql.append(" C_TR_NAME as C_PORT_NAME_ST, ");
		bufSql.append(" '' as C_PORT_NAME_EN, ");
		bufSql.append(" '' as C_ASS_CODE, ");
		bufSql.append(" '' as C_DC_CODE, ");
		bufSql.append(" to_date('1900-01-01', 'yyyy-MM-dd') as D_BUILD, ");
		bufSql.append(" to_date('9998-12-31', 'yyyy-MM-dd') as D_CLOSE, ");
		bufSql.append(" to_date('9998-12-31', 'YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		bufSql.append(" '' as C_DESC, ");
		bufSql.append(" '' as C_PORT_NAME, ");
		bufSql.append(" 1 as N_CHECK_STATE, ");
		bufSql.append(" '' as C_UPDATE_BY, ");
		bufSql.append(" '' as C_UPDATE_TIME, ");
		bufSql.append(" '' as C_CHECK_BY, ");
		bufSql.append(" '' as C_CHECK_TIME, ");
		bufSql.append(" 'ASS_TYPE' as DATA_TYPE, ");
		bufSql.append(" ' ' as C_DV_PORT_CODE, ");
		bufSql.append(" '' as C_HDAY_CODE,'' as C_DV_PROD_STATE, '' as C_PORT_UNIT ,'' as C_DAT_CLS");
		bufSql.append(" from T_P_AB_ASS_TR ");
		bufSql.append(" where C_TR_CODE_R =? ");
		bufSql.append(" and N_CHECK_STATE = 1 ");
		bufSql.append(" union all ");
		bufSql.append(" select b.C_IDEN,b.C_PORT_CODE as C_DAT_CODE, ");
		bufSql.append(" b.c_tr_code AS C_PORT_CODE_P,");
		bufSql.append(" b.C_PORT_CODE, ");
		bufSql.append(" c.C_PORT_NAME_ST, ");
		bufSql.append(" c.C_PORT_NAME_EN, ");
		bufSql.append(" c.C_ASS_CODE, ");
		bufSql.append(" c.C_DC_CODE, ");
		bufSql.append(" c.D_BUILD, ");
		bufSql.append(" c.D_CLOSE, ");
		bufSql.append(" c.D_CLEAR, "); // 添加清算字段
		bufSql.append(" b.C_TR_CODE_R as C_DESC, ");
		bufSql.append(" c.C_PORT_NAME, ");
		bufSql.append(" b.N_CHECK_STATE, ");
		bufSql.append(" c.C_UPDATE_BY, ");
		bufSql.append(" c.C_UPDATE_TIME, ");
		bufSql.append(" c.C_CHECK_BY, ");
		bufSql.append(" c.C_CHECK_TIME, ");
		bufSql.append(" 'PORT_TYPE' as DATA_TYPE, ");
		bufSql.append(" c.C_DV_PORT_CODE, ");
		bufSql.append(" c.C_HDAY_CODE,c.C_DV_PROD_STATE,c.C_PORT_UNIT,c.C_DAT_CLS ");
		bufSql.append(" from T_P_AB_ASS_TR_SUB b ");
		bufSql.append(" left join t_p_ab_port c ");
		bufSql.append(" on c.C_PORT_CODE = b.C_PORT_CODE ");
		bufSql.append(" where b.C_port_code in (select * from table(?)) ");
		bufSql.append(" AND B.N_CHECK_STATE = 1 ");
		bufSql.append(" AND B.C_TR_CODE_R = ? ");
		//2017-02-06 STORY36179嘉实基金-新建一个产品树形结构时，可自动生成 “未分配”结构，将没有分配到结构下的组合默认放到“未分配”下
		//在其他页面展示出未分配下有权限的组合   zhujinyang
		bufSql.append(" UNION ALL ");
		bufSql.append(" SELECT A.C_IDEN, ");
		bufSql.append(" A.C_PORT_CODE AS C_DAT_CODE, ");
		bufSql.append(" C.C_TR_CODE AS C_PORT_CODE_P, ");
		bufSql.append(" A.C_PORT_CODE AS C_PORT_CODE, ");
		bufSql.append(" A.C_PORT_NAME_ST AS C_PORT_NAME_ST, ");
		bufSql.append(" A.C_PORT_NAME_EN, ");
		bufSql.append(" A.C_ASS_CODE, ");
		bufSql.append(" A.C_DC_CODE, ");
		bufSql.append(" A.D_BUILD, ");
		bufSql.append(" A.D_CLOSE, ");
		bufSql.append(" A.D_CLEAR, ");
		bufSql.append(" C.C_TR_CODE_R AS C_DESC, ");
		bufSql.append(" A.C_PORT_NAME, ");
		bufSql.append(" A.N_CHECK_STATE, ");
		bufSql.append(" A.C_UPDATE_BY, ");
		bufSql.append(" A.C_UPDATE_TIME, ");
		bufSql.append(" A.C_CHECK_BY, ");
		bufSql.append(" A.C_CHECK_TIME, ");
		bufSql.append(" 'PORT_TYPE' as DATA_TYPE, ");
		bufSql.append(" A.C_DV_PORT_CODE, ");
		bufSql.append(" A.C_HDAY_CODE, ");
		bufSql.append(" A.C_DV_PROD_STATE, ");
		bufSql.append(" A.C_PORT_UNIT, ");
		bufSql.append(" A.C_DAT_CLS ");
		bufSql.append(" FROM T_P_AB_PORT A , T_P_AB_ASS_TR C ");
		bufSql.append(" WHERE NOT EXISTS ");
		bufSql.append(" (SELECT 1 FROM T_P_AB_ASS_TR_SUB B ");
		bufSql.append(" WHERE B.N_CHECK_STATE = 1 ");
		bufSql.append(" AND B.C_TR_CODE_R = ? ");
		bufSql.append(" AND B.C_PORT_CODE = A.C_PORT_CODE) ");
		bufSql.append(" AND A.C_PORT_CODE IN (SELECT * FROM TABLE(?)) ");
		bufSql.append(" AND C.C_TR_CODE_P = ? ");
		bufSql.append(" AND C_TR_NAME = '未分配' ");
		//鹏华21.6测试  需支持公共A区组合数据按照树形结构设置中 【显示未分配】的值来控制未分配节点数据的展示  edit by sunyanlin 20180531
		bufSql.append(" AND EXISTS (SELECT 1 FROM T_P_AB_ASS_TR D WHERE D.C_TR_CODE = C.C_TR_CODE_P AND D.C_DV_UN_DIS = '1') ");
		bufSql.append(" ) d ");
		bufSql.append(" start with d.C_PORT_CODE_P = '[root]' ");
		bufSql.append(" connect by NOCYCLE prior d.C_DAT_CODE = d.C_PORT_CODE_P ");
		bufSql.append("  ");
		retSql = bufSql.toString();
		StringUtil.clearStringBuffer(bufSql);
		return retSql;
	}

	/**
	 * 获取用户自定义资产树形结构by gongjinzhi 20170210
	 * 
	 * @return
	 */
	public String getDefaultAssPort() {
		String retSql = "";
		StringBuffer bufSql = new StringBuffer();
		bufSql.append(" select level, d.* ");
		bufSql.append(" from (select C_IDEN,C_TR_CODE as C_DAT_CODE, ");
		bufSql.append(" C_TR_CODE_P as C_PORT_CODE_P, ");
		bufSql.append(" C_TR_CODE as C_PORT_CODE, ");
		bufSql.append(" C_TR_NAME as C_PORT_NAME_ST, ");
		bufSql.append(" '' as C_PORT_NAME_EN, ");
		bufSql.append(" '' as C_ASS_CODE, ");
		bufSql.append(" '' as C_DC_CODE, ");
		bufSql.append(" to_date('1900-01-01', 'yyyy-MM-dd') as D_BUILD, ");
		bufSql.append(" to_date('9998-12-31', 'yyyy-MM-dd') as D_CLOSE, ");
		bufSql.append(" to_date('9998-12-31', 'YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		bufSql.append(" '' as C_DESC, ");
		bufSql.append(" '' as C_PORT_NAME, ");
		bufSql.append(" 1 as N_CHECK_STATE, ");
		bufSql.append(" '' as C_UPDATE_BY, ");
		bufSql.append(" '' as C_UPDATE_TIME, ");
		bufSql.append(" '' as C_CHECK_BY, ");
		bufSql.append(" '' as C_CHECK_TIME, ");
		bufSql.append(" 'ASS_TYPE' as DATA_TYPE, ");
		bufSql.append(" ' ' as C_DV_PORT_CODE, ");
		bufSql.append(" '' as C_HDAY_CODE,'' as C_DV_PROD_STATE, '' as C_PORT_UNIT ,'' as C_DAT_CLS");
		bufSql.append(" from T_P_AB_ASS_TR ");
		bufSql.append(" where C_TR_CODE_R =? ");
		bufSql.append(" and N_CHECK_STATE = 1 ");
		bufSql.append(" union all ");
		bufSql.append(" select b.C_IDEN,b.C_PORT_CODE as C_DAT_CODE, ");
		bufSql.append(" b.c_tr_code AS C_PORT_CODE_P,");
		bufSql.append(" b.C_PORT_CODE, ");
		bufSql.append(" c.C_PORT_NAME_ST, ");
		bufSql.append(" c.C_PORT_NAME_EN, ");
		bufSql.append(" c.C_ASS_CODE, ");
		bufSql.append(" c.C_DC_CODE, ");
		bufSql.append(" c.D_BUILD, ");
		bufSql.append(" c.D_CLOSE, ");
		bufSql.append(" c.D_CLEAR, "); // 添加清算字段
		bufSql.append(" b.C_TR_CODE_R as C_DESC, ");
		bufSql.append(" c.C_PORT_NAME, ");
		bufSql.append(" b.N_CHECK_STATE, ");
		bufSql.append(" c.C_UPDATE_BY, ");
		bufSql.append(" c.C_UPDATE_TIME, ");
		bufSql.append(" c.C_CHECK_BY, ");
		bufSql.append(" c.C_CHECK_TIME, ");
		bufSql.append(" 'PORT_TYPE' as DATA_TYPE, ");
		bufSql.append(" c.C_DV_PORT_CODE, ");
		bufSql.append(" c.C_HDAY_CODE,c.C_DV_PROD_STATE,c.C_PORT_UNIT,c.C_DAT_CLS ");
		bufSql.append(" from T_P_AB_ASS_TR_SUB b ");
		bufSql.append(" left join t_p_ab_port c ");
		bufSql.append(" on c.C_PORT_CODE = b.C_PORT_CODE ");
		bufSql.append(" where B.N_CHECK_STATE = 1 ");
		bufSql.append(" AND B.C_TR_CODE_R = ? ");
		bufSql.append(" ) d ");
		bufSql.append(" start with d.C_PORT_CODE_P = '[root]' ");
		bufSql.append(" connect by NOCYCLE prior d.C_DAT_CODE = d.C_PORT_CODE_P ");
		bufSql.append("  ");
		retSql = bufSql.toString();
		StringUtil.clearStringBuffer(bufSql);
		return retSql;
	}

	/**
	 * 获取用户自定义资产树形结构
	 * 
	 * @return
	 */
	public String getAssPort() {
		String retSql = "";
		StringBuffer bufSql = new StringBuffer();
		bufSql.append(" select level, d.* ");
		bufSql.append(" from (select C_IDEN,C_TR_CODE as C_DAT_CODE, ");
		bufSql.append(" C_TR_CODE_P as C_PORT_CODE_P, ");
		bufSql.append(" C_TR_CODE as C_PORT_CODE, ");
		bufSql.append(" C_TR_NAME as C_PORT_NAME_ST, ");
		bufSql.append(" '' as C_PORT_NAME_EN, ");
		bufSql.append(" '' as C_ASS_CODE, ");
		bufSql.append(" '' as C_DC_CODE, ");
		bufSql.append(" to_date('1900-01-01', 'yyyy-MM-dd') as D_BUILD, ");
		bufSql.append(" to_date('9998-12-31', 'yyyy-MM-dd') as D_CLOSE, ");
		bufSql.append(" to_date('9998-12-31', 'YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		bufSql.append(" '' as C_DESC, ");
		bufSql.append(" '' as C_PORT_NAME, ");
		bufSql.append(" 1 as N_CHECK_STATE, ");
		bufSql.append(" '' as C_UPDATE_BY, ");
		bufSql.append(" '' as C_UPDATE_TIME, ");
		bufSql.append(" '' as C_CHECK_BY, ");
		bufSql.append(" '' as C_CHECK_TIME, ");
		bufSql.append(" 'ASS_TYPE' as DATA_TYPE, ");
		bufSql.append(" ' ' as C_DV_PORT_CODE, ");
		bufSql.append(" '' as C_HDAY_CODE,'' as C_DV_PROD_STATE, '' as C_PORT_UNIT ,'' as C_DAT_CLS");
		bufSql.append(" from T_P_AB_ASS_TR ");
		bufSql.append(" where C_TR_CODE_R =? ");
		bufSql.append(" and N_CHECK_STATE = 1 ");
		bufSql.append(" union all ");
		bufSql.append(" select b.C_IDEN,b.C_PORT_CODE as C_DAT_CODE, ");
		bufSql.append(" b.c_tr_code AS C_PORT_CODE_P,");
		bufSql.append(" c.C_PORT_CODE, ");
		bufSql.append(" c.C_PORT_NAME_ST, ");
		bufSql.append(" c.C_PORT_NAME_EN, ");
		bufSql.append(" c.C_ASS_CODE, ");
		bufSql.append(" c.C_DC_CODE, ");
		bufSql.append(" c.D_BUILD, ");
		bufSql.append(" c.D_CLOSE, ");
		bufSql.append(" c.D_CLEAR, "); // 添加清算字段
		bufSql.append(" b.C_TR_CODE_R as C_DESC, ");
		bufSql.append(" c.C_PORT_NAME, ");
		bufSql.append(" b.N_CHECK_STATE, ");
		bufSql.append(" c.C_UPDATE_BY, ");
		bufSql.append(" c.C_UPDATE_TIME, ");
		bufSql.append(" c.C_CHECK_BY, ");
		bufSql.append(" c.C_CHECK_TIME, ");
		bufSql.append(" 'PORT_TYPE' as DATA_TYPE, ");
		bufSql.append(" c.C_DV_PORT_CODE, ");
		bufSql.append(" c.C_HDAY_CODE,c.C_DV_PROD_STATE,c.C_PORT_UNIT,c.C_DAT_CLS ");
		bufSql.append(" from T_P_AB_ASS_TR_SUB b ");
		bufSql.append(" left join t_p_ab_port c ");
		bufSql.append(" on c.C_PORT_CODE = b.C_PORT_CODE ");
		bufSql.append(" where ");
		// bufSql.append(" b.C_port_code in (select * from table(?)) AND");
		bufSql.append(" B.N_CHECK_STATE = 1 ");
		bufSql.append(" AND B.C_TR_CODE_R = ? ");
		bufSql.append(" ) d ");
		bufSql.append(" start with d.C_PORT_CODE_P = '[root]' ");
		bufSql.append(" connect by prior d.C_DAT_CODE = d.C_PORT_CODE_P ");
		bufSql.append("  ");
		retSql = bufSql.toString();
		StringUtil.clearStringBuffer(bufSql);
		return retSql;
	}

	public String getDefaultUserPort() {
		String retSql = "";
		StringBuffer bufSql = new StringBuffer();
		bufSql.append(" select level, d.* ");
		bufSql.append(" from (select C_IDEN,C_TR_CODE as C_DAT_CODE, ");
		bufSql.append(" C_TR_CODE_P as C_PORT_CODE_P, ");
		bufSql.append(" C_TR_CODE as C_PORT_CODE, ");
		bufSql.append(" C_TR_NAME as C_PORT_NAME_ST, ");
		bufSql.append(" '' as C_PORT_NAME_EN, ");
		bufSql.append(" '' as C_ASS_CODE, ");
		bufSql.append(" '' as C_DC_CODE, ");
		bufSql.append(" to_date('1900-01-01', 'yyyy-MM-dd') as D_BUILD, ");
		bufSql.append(" to_date('9998-12-31', 'yyyy-MM-dd') as D_CLOSE, ");
		bufSql.append(" to_date('9998-12-31', 'YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		bufSql.append(" '' as C_DESC, ");
		bufSql.append(" '' as C_PORT_NAME, ");
		bufSql.append(" 1 as N_CHECK_STATE, ");
		bufSql.append(" '' as C_UPDATE_BY, ");
		bufSql.append(" '' as C_UPDATE_TIME, ");
		bufSql.append(" '' as C_CHECK_BY, ");
		bufSql.append(" '' as C_CHECK_TIME, ");
		bufSql.append(" 'ASS_TYPE' as DATA_TYPE, ");
		bufSql.append(" ' ' as C_DV_PORT_CODE, ");
		// bufSql.append(" '' as C_PORT_CODE_P, ");
		bufSql.append(" '' as C_HDAY_CODE,'' as C_DV_PROD_STATE, '' as C_PORT_UNIT ,'' as C_DAT_CLS"); // /add
		// by
		// weijj
		// 缺少字段
		bufSql.append(" from T_P_AB_ASS_TR ");
		bufSql.append(" where C_TR_CODE_R =? ");
		bufSql.append(" and N_CHECK_STATE = 1 ");
		bufSql.append(" union all ");
		bufSql.append(" select b.C_IDEN,b.C_PORT_CODE as C_DAT_CODE, ");
		bufSql.append(" (case ");
		bufSql.append(" when c.C_PORT_CODE_P is null or c.C_PORT_CODE_P=' ' then ");
		bufSql.append(" b.C_TR_CODE ");
		bufSql.append(" else ");
		bufSql.append(" C_PORT_CODE_P ");
		bufSql.append(" end) as C_PORT_CODE_P, ");
		bufSql.append(" c.C_PORT_CODE, ");
		bufSql.append(" c.C_PORT_NAME_ST, ");
		bufSql.append(" c.C_PORT_NAME_EN, ");
		bufSql.append(" c.C_ASS_CODE, ");
		bufSql.append(" c.C_DC_CODE, ");
		bufSql.append(" c.D_BUILD, ");
		bufSql.append(" c.D_CLOSE, ");
		bufSql.append(" c.D_CLEAR, "); // 添加清算字段
		bufSql.append(" b.C_TR_CODE_R as C_DESC, ");
		bufSql.append(" c.C_PORT_NAME, ");
		bufSql.append(" b.N_CHECK_STATE, ");
		bufSql.append(" c.C_UPDATE_BY, ");
		bufSql.append(" c.C_UPDATE_TIME, ");
		bufSql.append(" c.C_CHECK_BY, ");
		bufSql.append(" c.C_CHECK_TIME, ");
		bufSql.append(" 'PORT_TYPE' as DATA_TYPE, ");
		bufSql.append(" c.C_DV_PORT_CODE, ");
		// bufSql.append(" c.C_PORT_CODE_P, ");
		bufSql.append(" c.C_HDAY_CODE,c.C_DV_PROD_STATE,c.C_PORT_UNIT,c.C_DAT_CLS ");
		bufSql.append(" from T_P_AB_ASS_TR_SUB b ");
		bufSql.append(" left join t_p_ab_port c ");
		bufSql.append(" on c.C_PORT_CODE = b.C_PORT_CODE ");
		bufSql.append(" where b.C_port_code in (select * from table(?)) ");
		bufSql.append(" AND B.N_CHECK_STATE = 1 ");
		bufSql.append(" AND B.C_TR_CODE_R = ? ");
		bufSql.append(" ) d ");
		// bufSql.append(" start with d.fParaentCode = '[root]' ");
		// bufSql.append(" connect by prior d.C_DAT_CODE = d.fParaentCode ");
		bufSql.append(" start with d.C_PORT_CODE_P = '[root]' ");
		bufSql.append(" connect by prior d.C_DAT_CODE = d.C_PORT_CODE_P ");
		bufSql.append("  ");
		retSql = bufSql.toString();
		StringUtil.clearStringBuffer(bufSql);
		return retSql;
	}

	public String getListByKeys(DBNameResolver dbnamersv) {
		String retSql = "";
		StringBuffer buf = new StringBuffer();

		setCommonTreeSqlBuf(dbnamersv, buf);

		buf.append(" AND b.");
		buf.append(getColumnNameByProperty(dbnamersv, "c_PORT_CODE"));
		buf.append(" IN (SELECT * FROM TABLE(?)) ");

		retSql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return retSql;
	}

	public String getListByTypes(DBNameResolver dbnamersv) {
		String retSql = "";
		StringBuffer buf = new StringBuffer();

		setCommonTreeSqlBuf(dbnamersv, buf);

		buf.append(" AND b.");
		buf.append(getColumnNameByProperty(dbnamersv, "c_PORT_CODE"));
		buf.append(" IN (SELECT * FROM TABLE(?)) ");

		retSql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return retSql;
	}

	public String getPortDataSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		StringBuffer wherebuf = new StringBuffer();
		wherebuf = setWhereSQL(wherebuf, paraNameList);
		String sql = "";

		buf.append(" select level, c.* from (");
		buf.append("   select '' AS C_IDEN, ");
		buf.append("     C_DAT_CODE as C_DAT_CODE,");
		buf.append("     c_dat_code_p as fParaentCode, ");
		buf.append("     C_DAT_CODE as C_PORT_CODE, ");
		buf.append("     C_DAT_NAME as C_PORT_NAME_ST, ");
		buf.append("     '' as C_PORT_NAME_EN, '' as C_ASS_CODE, ");
		buf.append("     ''as C_DC_CODE, ");
		buf.append("     to_date('1999-12-14','YYYY-MM-DD') as D_BUILD, ");
		buf.append("     to_date('1999-12-14','YYYY-MM-DD') as D_CLOSE, ");
		buf.append("     to_date('9998-12-31','YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		buf.append("     '' as C_DESC,'' as C_PORT_NAME, ");
		buf.append("     1 as N_CHECK_STATE, '' as C_UPDATE_BY, ");
		buf.append("     '' as C_UPDATE_TIME,'' as C_CHECK_BY, ");
		buf.append("     '' as C_CHECK_TIME,'ASS_TYPE' as DATA_TYPE, ");
		buf.append("     ' ' as C_DV_PORT_CODE,'' as C_PORT_CODE_P, ");
		buf.append("     '' as C_HDAY_CODE,'' as C_DV_PROD_STATE, '' as C_PORT_UNIT ");
		buf.append(",     '' as C_DAT_CLS ");// ADD BY ZHAOXIANLIN 20130514
		// STORY #3659 关于资产类型改造需求
		buf.append("   from  T_S_DAT_ASS_TYPE ");
		buf.append("   where C_DAT_TYPE = 'ASS_TYPE' ");// ADD BY ZHAOXIANLIN
		// 20130514 STORY #3659
		// 关于资产类型改造需求

		buf.append("   union all ");
		/**
		 * 根据用户代码和岗位代码确定已经存在的组合信息
		 */
		buf.append("     select b.C_IDEN, b.C_PORT_CODE, ");
		buf.append("     case ");
		buf.append("       when trim(b.C_PORT_CODE_P) is null then ");
		buf.append("         b.C_DAT_CODE ");
		buf.append("       else b.C_PORT_CODE_P end ");
		buf.append("       as fParaentCode, ");
		buf.append("       b.C_PORT_CODE,b.C_PORT_NAME_ST, ");
		buf.append("       b.C_PORT_NAME_EN,b.C_ASS_CODE, ");
		buf.append("       b.C_DC_CODE,b.D_BUILD,b.D_CLOSE,b.D_CLEAR, "); // 添加清算字段
		buf.append("       b.C_DESC, b.C_PORT_NAME,b.N_CHECK_STATE, ");
		buf.append("       b.C_UPDATE_BY,b.C_UPDATE_TIME, ");
		buf.append("       b.C_CHECK_BY,b.C_CHECK_TIME, ");
		buf.append("       'PORT_TYPE' as DATA_TYPE, ");
		buf.append("       b.C_DV_PORT_CODE,b.C_PORT_CODE_P, ");
		buf.append("       b.C_HDAY_CODE,b.C_DV_PROD_STATE,b.C_PORT_UNIT ");
		buf.append(",       b.C_DAT_CLS ");// ADD BY ZHAOXIANLIN 20130514 STORY
		// #3659 关于资产类型改造需求
		buf.append("     from T_P_AB_PORT b  where b.N_CHECK_STATE = 1");
		buf.append("       and  c_dat_cls is not null ");// ADD BY ZHAOXIANLIN
		// 20130514 STORY
		// #3659 关于资产类型改造需求
		buf.append("       and c_dat_code is not null ");// ADD BY ZHAOXIANLIN
		// 20130514 STORY
		// #3659 关于资产类型改造需求
		buf.append("       ) c ");
		if (paraNameList.size() == 0) {
			buf.append("           start with c.C_PORT_CODE in( ");
			buf.append("             select a.C_PORT_CODE  ");
			buf.append("             from T_P_AB_PORT a ");
			buf.append("             where a.N_CHECK_STATE = 1 ");
			buf.append("       and  a.c_dat_cls is not null ");// ADD BY
			// ZHAOXIANLIN
			// 20130514
			// STORY #3659
			// 关于资产类型改造需求
			buf.append("       and a.c_dat_code is not null ");// ADD BY
			// ZHAOXIANLIN
			// 20130514
			// STORY #3659
			// 关于资产类型改造需求
			buf.append("           ) ");
			buf.append("     connect by prior c.fParaentCode = c.C_DAT_CODE ");
		} else if (paraNameList.contains("APPY_USER_REAL")) {
			// /根据用户代码使用权限过滤
			buf.append("           start with c.C_PORT_CODE in( ");

			buf.append("   select u.c_port_code from t_s_user_rela u where ");
			buf.append(" u.n_check_state >= 0 ");

			if (paraNameList.contains("USER_POST_CODE")) {
				int i = paraNameList.indexOf("USER_POST_CODE");
				if (i == 0) {
					buf.append(" and u.c_post_code = ?");
					buf.append(" and u.c_user_code = ?");
				} else {
					buf.append(" and u.c_user_code = ?");
					buf.append(" and u.c_post_code = ?");
				}
			} else {
				buf.append(" and u.c_user_code = ?");
				// //查找当前用户 授权岗对应的组合
				buf.append(" and u.c_post_code in (select c_post_code from T_S_POST where c_dv_post_type ='RIGHT')");

			}

			buf.append("           ) ");
			buf.append("     connect by prior c.fParaentCode = c.C_DAT_CODE ");
		}

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);

		return sql;
	}

	public String getPortTreeForRight(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		StringBuffer wherebuf = new StringBuffer();
		wherebuf = setWhereSQL(wherebuf, paraNameList);
		String sql = "";

		buf.append(" select level, c.* from (");
		buf.append("   select '' AS C_IDEN, ");
		buf.append("     C_DAT_CODE as C_DAT_CODE, '' as C_DAT_CLS,");
		buf.append("     c_dat_code_p as C_PORT_CODE_P, ");
		buf.append("     C_DAT_CODE as C_PORT_CODE, ");
		buf.append("     C_DAT_NAME as C_PORT_NAME_ST, ");
		buf.append("     '' as C_PORT_NAME_EN, '' as C_ASS_CODE, ");
		buf.append("     ''as C_DC_CODE, ");
		buf.append("     to_date('1999-12-14','YYYY-MM-DD') as D_BUILD, ");
		buf.append("     to_date('1999-12-14','YYYY-MM-DD') as D_CLOSE, ");
		buf.append("     to_date('9998-12-31','YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		buf.append("     '' as C_DESC,'' as C_PORT_NAME, ");
		buf.append("     1 as N_CHECK_STATE, '' as C_UPDATE_BY, ");
		buf.append("     '' as C_UPDATE_TIME,'' as C_CHECK_BY, ");
		buf.append("     '' as C_CHECK_TIME,'ASS_TYPE' as DATA_TYPE, ");
		buf.append("     ' ' as C_DV_PORT_CODE, ");
		buf.append("     '' as C_HDAY_CODE,'' as C_DV_PROD_STATE, '' as C_PORT_UNIT ");
		buf.append("   from  T_S_DAT_ASS_TYPE ");

		buf.append("   union all ");
		/**
		 * 根据用户代码和岗位代码确定已经存在的组合信息
		 */
		buf.append("     select b.C_IDEN, b.C_PORT_CODE,  C_DAT_CLS,");
		buf.append("     case ");
		buf.append("       when trim(b.C_PORT_CODE_P) is null then ");
		buf.append("         b.C_DAT_CODE ");
		buf.append("       else b.C_PORT_CODE_P end ");
		buf.append("       as C_PORT_CODE_P, ");
		buf.append("       b.C_PORT_CODE,b.C_PORT_NAME_ST, ");
		buf.append("       b.C_PORT_NAME_EN,b.C_ASS_CODE, ");
		buf.append("       b.C_DC_CODE,b.D_BUILD,b.D_CLOSE,b.D_CLEAR, "); // 添加清算字段
		buf.append("       b.C_DESC, b.C_PORT_NAME,b.N_CHECK_STATE, ");
		buf.append("       b.C_UPDATE_BY,b.C_UPDATE_TIME, ");
		buf.append("       b.C_CHECK_BY,b.C_CHECK_TIME, ");
		buf.append("       'PORT_TYPE' as DATA_TYPE, ");
		buf.append("       b.C_DV_PORT_CODE,");
		buf.append("       b.C_HDAY_CODE,b.C_DV_PROD_STATE,b.C_PORT_UNIT ");
		buf.append("     from T_P_AB_PORT b  where b.N_CHECK_STATE = 1");
		buf.append("       ) c ");
		if (paraNameList.size() == 0) {
			buf.append("           start with c.C_PORT_CODE in( ");
			buf.append("             select a.C_PORT_CODE  ");
			buf.append("             from T_P_AB_PORT a ");
			buf.append("             where a.N_CHECK_STATE = 1 ");
			buf.append("           ) ");
			buf.append("     connect by prior c.C_PORT_CODE_P = c.C_DAT_CODE ");
		} else if (paraNameList.contains("APPY_USER_REAL")) {
			// /根据用户代码使用权限过滤
			buf.append("   start with c.C_PORT_CODE in( ");
			buf.append("   select u.c_port_code from t_s_user_rela u where ");
			buf.append(" u.n_check_state >= 0 ");

			if (paraNameList.contains("USER_POST_CODE")) {
				int i = paraNameList.indexOf("USER_POST_CODE");
				if (i == 0) {
					buf.append(" and u.c_post_code = ?");
					buf.append(" and u.c_user_code = ?");
				} else {
					buf.append(" and u.c_user_code = ?");
					buf.append(" and u.c_post_code = ?");
				}
			} else {
				buf.append(" and u.c_user_code = ?");
			}

			buf.append("   ) ");
			buf.append(" connect by prior c.C_PORT_CODE_P = c.C_DAT_CODE ");
		}
		buf.append("order by level desc ,c_port_code");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);

		return sql;
	}

	public String getPortTreeByCodesSql() {
		StringBuffer buf = new StringBuffer();
		String sql = "";

		buf.append(" select level, c.* from (");
		buf.append("   select '' AS C_IDEN, ");
		buf.append("   C_DAT_CODE as C_DAT_CODE, '' as C_DAT_CLS,");
		buf.append("   c_dat_code_p as C_PORT_CODE_P, ");
		buf.append("   C_DAT_CODE as C_PORT_CODE, ");
		buf.append("   C_DAT_NAME as C_PORT_NAME_ST, ");
		buf.append("   C_DAT_NAME as C_PORT_NAME_EN, '' as C_ASS_CODE, ");
		buf.append("   ''as C_DC_CODE, ");
		buf.append("   to_date('1999-12-14','YYYY-MM-DD') as D_BUILD, ");
		buf.append("   to_date('1999-12-14','YYYY-MM-DD') as D_CLOSE, ");
		buf.append("   to_date('9998-12-31','YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		buf.append("   '' as C_DESC,C_DAT_NAME as C_PORT_NAME, ");
		buf.append("   1 as N_CHECK_STATE, '' as C_UPDATE_BY, ");
		buf.append("   '' as C_UPDATE_TIME,'' as C_CHECK_BY, ");
		buf.append("   '' as C_CHECK_TIME,'ASS_TYPE' as DATA_TYPE, ");
		buf.append("   ' ' as C_DV_PORT_CODE, ");
		buf.append("   '' as C_HDAY_CODE,'' as C_DV_PROD_STATE, '' as C_PORT_UNIT ");
		buf.append("   from  T_S_DAT_ASS_TYPE ");

		buf.append("   union all ");
		/**
		 * 根据用户代码和岗位代码确定已经存在的组合信息
		 */
		// buf.append("     select b.C_IDEN, b.C_PORT_CODE,  C_DAT_CLS,");
		buf.append("     select b.C_IDEN, b.C_DAT_CODE,  C_DAT_CLS,");
		buf.append("     case ");
		buf.append("       when trim(b.C_PORT_CODE_P) is null then ");
		buf.append("         b.C_DAT_CODE ");
		buf.append("       else b.C_PORT_CODE_P end ");
		buf.append("       as C_PORT_CODE_P, ");
		buf.append("       b.C_PORT_CODE,b.C_PORT_NAME_ST, ");
		buf.append("       b.C_PORT_NAME_EN,b.C_ASS_CODE, ");
		buf.append("       b.C_DC_CODE,b.D_BUILD,b.D_CLOSE,b.D_CLEAR, "); // 添加清算字段
		buf.append("       b.C_DESC, b.C_PORT_NAME,b.N_CHECK_STATE, ");
		buf.append("       b.C_UPDATE_BY,b.C_UPDATE_TIME, ");
		buf.append("       b.C_CHECK_BY,b.C_CHECK_TIME, ");
		buf.append("       'PORT_TYPE' as DATA_TYPE, ");
		buf.append("       b.C_DV_PORT_CODE,");
		buf.append("       b.C_HDAY_CODE,b.C_DV_PROD_STATE,b.C_PORT_UNIT ");
		buf.append("     from T_P_AB_PORT b  where b.N_CHECK_STATE = 1");
		buf.append("       ) c ");
		// /根据用户代码使用权限过滤
		buf.append("   start with c.C_PORT_CODE in( select * from table(?) ");

		buf.append(" ) ");
		buf.append(" connect by prior c.C_PORT_CODE_P = c.C_PORT_CODE ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);

		return sql;
	}

	public String getPortTreeByUserAndPost(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		String sql = "";

		buf.append(" select level, c.* from (");
		buf.append("   select '' AS C_IDEN, ");
		buf.append("   C_DAT_CODE as C_DAT_CODE, '' as C_DAT_CLS,");
		buf.append("   c_dat_code_p as C_PORT_CODE_P, ");
		buf.append("   C_DAT_CODE as C_PORT_CODE, ");
		buf.append("   C_DAT_NAME as C_PORT_NAME_ST, ");
		buf.append("   C_DAT_NAME as C_PORT_NAME_EN, '' as C_ASS_CODE, ");
		buf.append("   ''as C_DC_CODE, ");
		buf.append("   to_date('1999-12-14','YYYY-MM-DD') as D_BUILD, ");
		buf.append("   to_date('1999-12-14','YYYY-MM-DD') as D_CLOSE, ");
		buf.append("   to_date('9998-12-31','YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		buf.append("   '' as C_DESC,C_DAT_NAME as C_PORT_NAME, ");
		buf.append("   1 as N_CHECK_STATE, '' as C_UPDATE_BY, ");
		buf.append("   '' as C_UPDATE_TIME,'' as C_CHECK_BY, ");
		buf.append("   '' as C_CHECK_TIME,'ASS_TYPE' as DATA_TYPE, ");
		buf.append("   ' ' as C_DV_PORT_CODE, ");
		buf.append("   '' as C_HDAY_CODE,'' as C_DV_PROD_STATE, '' as C_PORT_UNIT ");
		buf.append("   from  T_S_DAT_ASS_TYPE ");

		buf.append("   union all ");
		/**
		 * 根据用户代码和岗位代码确定已经存在的组合信息
		 */
		// buf.append("     select b.C_IDEN, b.C_PORT_CODE,  C_DAT_CLS,");
		buf.append("     select b.C_IDEN, b.C_DAT_CODE,  C_DAT_CLS,");
		buf.append("     case ");
		buf.append("       when trim(b.C_PORT_CODE_P) is null then ");
		buf.append("         b.C_DAT_CODE ");
		buf.append("       else b.C_PORT_CODE_P end ");
		buf.append("       as C_PORT_CODE_P, ");
		buf.append("       b.C_PORT_CODE,b.C_PORT_NAME_ST, ");
		buf.append("       b.C_PORT_NAME_EN,b.C_ASS_CODE, ");
		buf.append("       b.C_DC_CODE,b.D_BUILD,b.D_CLOSE,b.D_CLEAR, "); // 添加清算字段
		buf.append("       b.C_DESC, b.C_PORT_NAME,b.N_CHECK_STATE, ");
		buf.append("       b.C_UPDATE_BY,b.C_UPDATE_TIME, ");
		buf.append("       b.C_CHECK_BY,b.C_CHECK_TIME, ");
		buf.append("       'PORT_TYPE' as DATA_TYPE, ");
		buf.append("       b.C_DV_PORT_CODE,");
		buf.append("       b.C_HDAY_CODE,b.C_DV_PROD_STATE,b.C_PORT_UNIT ");
		buf.append("     from T_P_AB_PORT b  where b.N_CHECK_STATE = 1");
		buf.append("       ) c ");
		// /根据用户代码使用权限过滤
		buf.append("   start with c.C_PORT_CODE in( ");
		buf.append("   select u.c_port_code from t_s_user_rela u where ");
		buf.append(" u.n_check_state >= 0 ");

		if (paraNameList != null && paraNameList.size() > 0) {
			buf.append(" and ");
			for (String fieldedName : paraNameList) {
				if (fieldedName.equals("C_USER_CODE")) {
					buf.append(" u.C_USER_CODE = ? AND ");
				} else if (fieldedName.equals("C_POST_CODE")) {
					buf.append(" u.C_POST_CODE in (select * from table(?))");
					buf.append(" AND ");
				}
			}
			if (buf.length() > 0) {
				StringUtil.delLastSplitMark(buf, " AND ");
			}
		}

		buf.append(" ) ");
		buf.append(" connect by prior c.C_PORT_CODE_P = c.C_PORT_CODE ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);

		return sql;
	}

	public String getPortTreeByUserAndPostAndProdState(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		String sql = "";

		buf.append(" select level, c.* from (");
		buf.append("   select '' AS C_IDEN, ");
		buf.append("   C_DAT_CODE as C_DAT_CODE, '' as C_DAT_CLS,");
		buf.append("   c_dat_code_p as C_PORT_CODE_P, ");
		buf.append("   C_DAT_CODE as C_PORT_CODE, ");
		buf.append("   C_DAT_NAME as C_PORT_NAME_ST, ");
		buf.append("   '' as C_PORT_NAME_EN, '' as C_ASS_CODE, ");
		buf.append("   ''as C_DC_CODE, ");
		buf.append("   to_date('1999-12-14','YYYY-MM-DD') as D_BUILD, ");
		buf.append("   to_date('1999-12-14','YYYY-MM-DD') as D_CLOSE, ");
		buf.append("   to_date('9998-12-31','YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		buf.append("   '' as C_DESC,'' as C_PORT_NAME, ");
		buf.append("   1 as N_CHECK_STATE, '' as C_UPDATE_BY, ");
		buf.append("   '' as C_UPDATE_TIME,'' as C_CHECK_BY, ");
		buf.append("   '' as C_CHECK_TIME,'ASS_TYPE' as DATA_TYPE, ");
		buf.append("   ' ' as C_DV_PORT_CODE, ");
		buf.append("   '' as C_HDAY_CODE,'' as C_DV_PROD_STATE, '' as C_PORT_UNIT ");
		buf.append("   from  T_S_DAT_ASS_TYPE ");

		buf.append("   union all ");
		/**
		 * 根据用户代码和岗位代码确定已经存在的组合信息
		 */
		buf.append("     select b.C_IDEN, b.C_DAT_CODE,  C_DAT_CLS,");
		buf.append("     case ");
		buf.append("     when trim(b.C_PORT_CODE_P) is null then ");
		buf.append("     b.C_DAT_CODE ");
		buf.append("     else b.C_PORT_CODE_P end ");
		buf.append("     as C_PORT_CODE_P, ");
		buf.append("     b.C_PORT_CODE,b.C_PORT_NAME_ST, ");
		buf.append("     b.C_PORT_NAME_EN,b.C_ASS_CODE, ");
		buf.append("     b.C_DC_CODE,b.D_BUILD,b.D_CLOSE,b.D_CLEAR, "); // 添加清算字段
		buf.append("     b.C_DESC, b.C_PORT_NAME,b.N_CHECK_STATE, ");
		buf.append("     b.C_UPDATE_BY,b.C_UPDATE_TIME, ");
		buf.append("     b.C_CHECK_BY,b.C_CHECK_TIME, ");
		buf.append("     'PORT_TYPE' as DATA_TYPE, ");
		buf.append("     b.C_DV_PORT_CODE,");
		buf.append("     b.C_HDAY_CODE,b.C_DV_PROD_STATE,b.C_PORT_UNIT ");
		buf.append("     from T_P_AB_PORT b  where b.N_CHECK_STATE = 1");
		buf.append("     and b.C_DV_PROD_STATE in (SELECT * FROM TABLE)");
		buf.append("     ) c ");
		// /根据用户代码使用权限过滤
		buf.append("   start with c.C_PORT_CODE in( ");
		buf.append("   select u.c_port_code from t_s_user_rela u where ");
		buf.append(" u.n_check_state >= 0 ");
		for (int j = 0; j < paraNameList.size(); j++) {
			buf.append(" and u.").append(paraNameList.get(j)).append(" = ?");
		}
		buf.append("   ) ");
		buf.append("     connect by prior c.C_PORT_CODE_P = c.C_PORT_CODE ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);

		return sql;
	}

	// added by wangzhiye 2014-03-18 获取证券品种树
	public String getStockTypeTree(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		String sql = "";

		buf.append(" select * from V_S_DA_SEC_VAR");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);

		return sql;
	}

	public StringBuffer setWhereSQL(StringBuffer whereSqlBuf,
			List<String> paraNameList) {
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_USER_CODE")) {
				whereSqlBuf.append(" a.C_USER_CODE = ? AND ");
			} else if (fieldedName.equals("C_POST_CODE")) {
				whereSqlBuf
						.append(" a.C_POST_CODE in (select * from table(?))");
				whereSqlBuf.append(" AND ");
			}
		}
		if (whereSqlBuf.length() > 0) {
			StringUtil.delLastSplitMark(whereSqlBuf, " AND ");
		}

		return whereSqlBuf;
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(PortTableName.userInfo);
	}

	/**
	 * 获取组合信息标准查询Sql（树形结构）
	 * 
	 * @param buf
	 */
	private void setPortCommonTreeViewQuySql(StringBuffer buf) {
		buf.append("   select '' AS C_IDEN, ");
		buf.append("     C_DAT_CODE as C_DAT_CODE,");
		buf.append("     c_dat_code_p as fParaentCode, ");
		buf.append("     C_DAT_CODE as C_PORT_CODE, ");
		buf.append("     C_DAT_NAME as C_PORT_NAME_ST, ");
		buf.append("     '' as C_PORT_NAME_EN, '' as C_ASS_CODE, ");
		buf.append("     ''as C_DC_CODE, ");
		buf.append("     to_date('1999-12-14','YYYY-MM-DD') as D_BUILD, ");
		buf.append("     to_date('1999-12-14','YYYY-MM-DD') as D_CLOSE, ");
		buf.append("     to_date('9998-12-31','YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		buf.append("     '' as C_DESC,'' as C_PORT_NAME, ");
		buf.append("     1 as N_CHECK_STATE, '' as C_UPDATE_BY, ");
		buf.append("     '' as C_UPDATE_TIME,'' as C_CHECK_BY, ");
		buf.append("     '' as C_CHECK_TIME,'ASS_TYPE' as DATA_TYPE, ");
		buf.append("     ' ' as C_DV_PORT_CODE,'' as C_PORT_CODE_P, ");
		buf.append("     '' as C_HDAY_CODE ,'' as C_DV_PROD_STATE, '' as C_PORT_UNIT");
		buf.append(",     '' as C_DAT_CLS ");
		buf.append("   from  T_S_DAT_ASS_TYPE ");
		buf.append("   where C_DAT_TYPE = 'ASS_TYPE' ");
		buf.append("   union all ");
		buf.append("     select b.C_IDEN, b.C_PORT_CODE, ");
		buf.append("     case ");
		buf.append("       when trim(b.C_PORT_CODE_P) is null then ");
		buf.append("         b.C_DAT_CODE ");
		buf.append("       else b.C_PORT_CODE_P end ");
		buf.append("       as fParaentCode, ");
		buf.append("       b.C_PORT_CODE,b.C_PORT_NAME_ST, ");
		buf.append("       b.C_PORT_NAME_EN,b.C_ASS_CODE, ");
		buf.append("       b.C_DC_CODE,b.D_BUILD,b.D_CLOSE,b.D_CLEAR, "); // 添加清算字段
		buf.append("       b.C_DESC, b.C_PORT_NAME,b.N_CHECK_STATE, ");
		buf.append("       b.C_UPDATE_BY,b.C_UPDATE_TIME, ");
		buf.append("       b.C_CHECK_BY,b.C_CHECK_TIME, ");
		buf.append("       'PORT_TYPE' as DATA_TYPE, ");
		buf.append("       b.C_DV_PORT_CODE,b.C_PORT_CODE_P, ");
		buf.append("       b.C_HDAY_CODE,b.C_DV_PROD_STATE,b.C_PORT_UNIT ");
		buf.append(",      b.C_DAT_CLS ");
		buf.append("     from T_P_AB_PORT b  where b.N_CHECK_STATE = 1");
		buf.append("       and c_dat_cls is not null ");
		buf.append("       and c_dat_code is not null ");
	}

	public String getListByCodeAndBuildDateSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT *  FROM T_P_AB_PORT A ");
		buf.append(" WHERE A.C_PORT_CODE = ? ");
		buf.append(" AND A.D_BUILD = ? ");
		buf.append(" AND A.N_CHECK_STATE = 1 ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);

		return sql;
	}

	public String getPortByAssCodeSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT A.*,'PORT_TYPE' AS DATA_TYPE  FROM T_P_AB_PORT A ");
		buf.append(" WHERE A.C_ASS_CODE = ? ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);

		return sql;
	}

	/**
	 * Fixed by huangsq 20160811 STORY #26296 [招商证券]估值表发布及获取接口需求
	 */
	public String getPortByAssCodesSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT A.*,'PORT_TYPE' AS DATA_TYPE  FROM T_P_AB_PORT A ");
		buf.append(" WHERE A.C_ASS_CODE in(select * from table(?))");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);

		return sql;
	}

	public String getDeletePortByIdSQL(DBNameResolver dbNameResolver) {
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" delete from ");
		sqlBuff.append(getTableName(dbNameResolver));
		sqlBuff.append(" where c_iden = ? ");
		return sqlBuff.toString();
	}

//	public String getPortSubCount(String portCode) {
//		String sql = "";
//		StringBuffer sqlBuff = new StringBuffer();
//		sqlBuff.append(" SELECT NVL(MAX(TO_NUMBER(A.NUM)), 0) AS NUM ");
//		sqlBuff.append(" FROM (SELECT SUBSTR(C_PORT_CODE, ");
//		sqlBuff.append(" (LENGTH(C_PORT_CODE) - ");
//		sqlBuff.append(" REGEXP_INSTR(REVERSE(C_PORT_CODE), '_', 1) + 2)) NUM ");
//		sqlBuff.append(" FROM T_P_AB_PORT ");
//		sqlBuff.append(" WHERE C_PORT_CODE_P = ? ");
//		sqlBuff.append(" AND C_PORT_CODE LIKE '").append(portCode + "%')")
//				.append(" A ");
//		sqlBuff.append(" WHERE LENGTH(NUM) = LENGTH(REGEXP_REPLACE(NUM, '[^0-9]')) ");
//		sql = sqlBuff.toString();
//		StringUtil.clearStringBuffer(sqlBuff);
//		return sql;
//	}

	public String getPortTreeWithNode(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		buf.append("   select '' AS C_IDEN, ");
		buf.append("     C_DAT_CODE as C_DAT_CODE,C_DAT_CODE AS NODECODE, ");
		buf.append("     c_dat_code_p as fParaentCode, ");
		buf.append("     C_DAT_CODE as C_PORT_CODE, ");
		buf.append("     C_DAT_NAME as C_PORT_NAME_ST, ");
		buf.append("     '' as C_PORT_NAME_EN, '' as C_ASS_CODE, ");
		buf.append("     ''as C_DC_CODE, ");
		buf.append("     to_date('1999-12-14','YYYY-MM-DD') as D_BUILD, ");
		buf.append("     to_date('1999-12-14','YYYY-MM-DD') as D_CLOSE, ");
		buf.append("     to_date('9998-12-31','YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		buf.append("     '' as C_DESC,'' as C_PORT_NAME, ");
		buf.append("     1 as N_CHECK_STATE, '' as C_UPDATE_BY, ");
		buf.append("     '' as C_UPDATE_TIME,'' as C_CHECK_BY, ");
		buf.append("     '' as C_CHECK_TIME,'ASS_TYPE' as DATA_TYPE, ");
		buf.append("     ' ' as C_DV_PORT_CODE,'' as C_PORT_CODE_P, ");
		buf.append("     '' as C_HDAY_CODE ,'' as C_DV_PROD_STATE, '' as C_PORT_UNIT");
		buf.append(",     '' as C_DAT_CLS ");
		buf.append("   from  T_S_DAT_ASS_TYPE ");
		buf.append("   where C_DAT_TYPE = 'ASS_TYPE' ");
		buf.append("   union all ");
		buf.append("     select b.C_IDEN, b.C_PORT_CODE, b.C_PORT_CODE AS NODECODE, ");
		buf.append("     case ");
		buf.append("       when trim(b.C_PORT_CODE_P) is null then ");
		buf.append("         b.C_DAT_CODE ");
		buf.append("       else b.C_PORT_CODE_P end ");
		buf.append("       as fParaentCode, ");
		buf.append("       b.C_PORT_CODE,b.C_PORT_NAME_ST, ");
		buf.append("       b.C_PORT_NAME_EN,b.C_ASS_CODE, ");
		buf.append("       b.C_DC_CODE,b.D_BUILD,b.D_CLOSE,b.D_CLEAR, "); // 添加清算字段
		buf.append("       b.C_DESC, b.C_PORT_NAME,b.N_CHECK_STATE, ");
		buf.append("       b.C_UPDATE_BY,b.C_UPDATE_TIME, ");
		buf.append("       b.C_CHECK_BY,b.C_CHECK_TIME, ");
		buf.append("       'PORT_TYPE' as DATA_TYPE, ");
		buf.append("       b.C_DV_PORT_CODE,b.C_PORT_CODE_P, ");
		buf.append("       b.C_HDAY_CODE,b.C_DV_PROD_STATE,b.C_PORT_UNIT ");
		buf.append(",      b.C_DAT_CLS ");
		buf.append("     from T_P_AB_PORT b  where b.N_CHECK_STATE = 1");
		buf.append("       and c_dat_cls is not null ");
		buf.append("       and c_dat_code is not null ");
		return buf.toString();
	}

	public String getDataListByTimestamp(DBNameResolver dbnamersv) {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		setCommonDataListSqlBuf(dbnamersv, buf);

		sql = " TO_DATE(a.C_CHECK_TIME,'yyyy-mm-dd hh24:mi:ss') >= to_date(?,'yyyy-mm-dd hh24:mi:ss')";
		if (sql.trim().length() > 0) {
			buf.append(" Where (");
			buf.append(sql);
			buf.append(")");
		}

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * 查询A区群组数据，以树形展示 STORY #16818 产品群组需求 add by chenwenhai 20140603
	 * 
	 * @return
	 */
	public String getGroupDataTree() {
		StringBuffer buf = new StringBuffer();
		buf.append("   select '' AS C_IDEN, ");
		buf.append("   G.C_GROUP_CODE AS C_DAT_CODE, ");
		buf.append("   '[root]' as c_PORT_CODE_P, ");
		buf.append("   G.C_GROUP_CODE AS C_PORT_CODE, ");
		buf.append("   G.C_GROUP_NAME as C_PORT_NAME_ST, ");
		buf.append("   G.C_GROUP_NAME as C_PORT_NAME_EN, ");
		buf.append("   '' as C_ASS_CODE, ");
		buf.append("   '' as C_DC_CODE, ");
		buf.append("   to_date('1999-12-14', 'yyyy-MM-dd') as D_BUILD, ");
		buf.append("   to_date('1999-12-14', 'yyyy-MM-dd') as D_CLOSE, ");
		buf.append("   to_date('9998-12-31', 'YYYY-MM-DD') as D_CLEAR, ");
		buf.append("   '' as C_DESC, ");
		buf.append("   G.C_GROUP_NAME as C_PORT_NAME, ");
		buf.append("   1 as N_CHECK_STATE, ");
		buf.append("   '' as C_UPDATE_BY, ");
		buf.append("   '' as C_UPDATE_TIME, ");
		buf.append("   '' as C_CHECK_BY, ");
		buf.append("   '' as C_CHECK_TIME, ");
		buf.append("   'PORT_TYPE' as DATA_TYPE, ");
		// C_DV_PORT_CODE，用作群组下的组合个数统计
		buf.append("   PC.PortCount as C_DV_PORT_CODE, ");
		buf.append("   '' as C_HDAY_CODE, ");
		buf.append("   '' as C_DV_PROD_STATE, ");
		buf.append("   '' as C_PORT_UNIT, ");
		buf.append("   G.C_GROUP_CODE AS C_DAT_CLS ");
		buf.append("   FROM T_P_AB_GROUP G ");
		// STORY #26586 公共组合区产品群组增加显示组合数。统计群组下的组合个数。张绍林-20151207
		buf.append("   Left Join (");
		buf.append("   select C_GROUP_CODE, COUNT(C_PORT_CODE) AS PortCount ");
		buf.append("   from t_p_ab_group_rela A");
		// 组合个数要按权限过滤。张绍林-20160120
		buf.append("   WHERE EXISTS (select C_PORT_CODE from T_S_USER_RELA B ");
		buf.append("   WHERE C_USER_CODE = '"
				+ ContextFactory.getContext().getLogInfo().getLoggingUserCode()
				+ "' ");
		// buf.append("   AND C_POST_CODE = '" +
		// ContextFactory.getContext().getLogInfo().getLoggingUserPostCode() +
		// "' ");
		buf.append("   AND A.C_PORT_CODE = B.C_PORT_CODE)");
		buf.append("   group by C_GROUP_CODE) PC ");
		buf.append("   on G.C_GROUP_CODE = PC.C_GROUP_CODE ");

		buf.append("   WHERE G.N_CHECK_STATE = 1 ");
		// buf.append("   UNION ALL ");
		// buf.append("   select b.C_IDEN, ");
		// buf.append("   b.C_PORT_CODE, ");
		// buf.append("   C.C_GROUP_CODE as C_PORT_CODE_P, ");
		// buf.append("   b.C_PORT_CODE, ");
		// buf.append("   b.C_PORT_NAME_ST, ");
		// buf.append("   b.C_PORT_NAME_EN, ");
		// buf.append("   b.C_ASS_CODE, ");
		// buf.append("   b.C_DC_CODE, ");
		// buf.append("   b.D_BUILD, ");
		// buf.append("   b.D_CLOSE, ");
		// buf.append("   b.D_CLEAR, ");
		// buf.append("   b.C_DESC, ");
		// buf.append("   b.C_PORT_NAME, ");
		// buf.append("   b.N_CHECK_STATE, ");
		// buf.append("   b.C_UPDATE_BY, ");
		// buf.append("   b.C_UPDATE_TIME, ");
		// buf.append("   '' as C_CHECK_BY, ");
		// buf.append("   '' as C_CHECK_TIME, ");
		// buf.append("   'PORT_TYPE' as DATA_TYPE, ");
		// buf.append("   b.C_DV_PORT_CODE, ");
		// buf.append("   b.C_HDAY_CODE, ");
		// buf.append("   b.C_DV_PROD_STATE, ");
		// buf.append("   b.C_PORT_UNIT, ");
		// buf.append("   C.C_GROUP_CODE ");
		// buf.append("   FROM T_P_AB_GROUP_RELA C ");
		// buf.append("   LEFT JOIN T_P_AB_PORT b ");
		// buf.append("   ON B.C_PORT_CODE = c.c_Port_Code ");
		// buf.append("   WHERE b.N_CHECK_STATE = 1 ");
		return buf.toString();
	}

	public String checkPortCode() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT A.* FROM T_P_AB_GROUP A WHERE A.C_GROUP_CODE = ? ");
		return buf.toString();
	}

	public String duePortSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT A.* FROM T_P_AB_PORT A WHERE A.C_DV_PROD_STATE = ? ");
		buf.append(" AND A.D_CLOSE <= TO_DATE(?, 'yyyy-MM-dd') AND A.C_PORT_CODE_P = ? ");
		return buf.toString();
	}

	/**
	 * 投资组合查询数据sql拼接（树形结构） 查询下面存在子组合的组合信息 By Jinghehe 2014-7-28 根据用户和用户岗位来加载数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getParentPortTreeViewSql() throws Exception {
		String querySql = null;
		StringBuffer sqlBuffer = new StringBuffer();
		try {
			sqlBuffer
					.append("select level, c.* from (select C_DAT_CODE as C_DAT_CODE,c_dat_code_p as C_PORT_CODE_P,");
			sqlBuffer
					.append("C_DAT_CODE as C_PORT_CODE,C_DAT_NAME as C_PORT_NAME_ST,'' as C_PORT_NAME_EN,");
			sqlBuffer
					.append("'' as C_ASS_CODE,''as C_DC_CODE,to_date('1999-12-14','yyyy-MM-dd') as D_BUILD,");
			sqlBuffer
					.append(" to_date('9998-12-31', 'YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
			sqlBuffer
					.append("to_date('1999-12-14','yyyy-MM-dd') as D_CLOSE,'' as C_DESC,C_DAT_NAME as C_PORT_NAME,1 as N_CHECK_STATE,");
			sqlBuffer
					.append("'' as C_UPDATE_BY,'' as C_UPDATE_TIME,'' as C_CHECK_BY,'' as C_CHECK_TIME, ");
			sqlBuffer
					.append("' ' as C_DV_PORT_CODE,'' as C_HDAY_CODE,'' as C_DV_PROD_STATE,'' as C_DAT_CLS, '' as c_port_unit,'ASS_TYPE' as DATA_TYPE from  T_S_DAT_ASS_TYPE "); // MODIFIED
			sqlBuffer.append(" union all select distinct b.C_PORT_CODE,");
			sqlBuffer
					.append("(case when trim (b.C_PORT_CODE_P) is null then b.C_DAT_CODE else b.C_PORT_CODE_P end)  as C_PORT_CODE_P,b.C_PORT_CODE,b.C_PORT_NAME_ST,b.C_PORT_NAME_EN,b.C_ASS_CODE,");
			sqlBuffer
					.append(" b.C_DC_CODE,b.D_BUILD,b.D_CLEAR,b.D_CLOSE,b.C_DESC, b.C_PORT_NAME,b.N_CHECK_STATE,b.C_UPDATE_BY,b.C_UPDATE_TIME,"); // 添加清算日期字段byleeyu20130806
			sqlBuffer
					.append(" b.C_CHECK_BY,b.C_CHECK_TIME,b.C_DV_PORT_CODE,b.C_HDAY_CODE,b.C_DV_PROD_STATE,b.C_DAT_CLS, b.c_port_unit,'PORT_TYPE' as DATA_TYPE from T_P_AB_PORT b "); // MODIFIED
			sqlBuffer.append(" where b.N_CHECK_STATE = 1 ");
			// /根据用户代码使用权限过滤
			sqlBuffer.append(" and b.C_PORT_CODE in( ");
			sqlBuffer
					.append("select distinct c_port_code_p from t_p_ab_port where c_port_code in ( ");
			sqlBuffer
					/**
					 * Start 20150119 modified by liubo.BUG #106610
					 * 分组产品参数界面，双击浏览list区记录报错
					 * 使用sqlOverLongCondition处理长字符串时，这里之前的写法有点问题，应该是用“select *
					 * from table(?)”，之前直接用的=?
					 */
					.append(" select u.c_port_code from t_s_user_rela u where u.c_post_code in (select * from table(?)) ")
					.append(" and u.c_user_code = ? and u.n_check_state >= 0 ) ");
			/**
			 * End 20150119 modified by liubo.BUG #106610 分组产品参数界面，双击浏览list区记录报错
			 */
			sqlBuffer.append("  ) ");
			sqlBuffer
					.append(" ) c start with c.C_PORT_CODE_P = '[root]' connect by prior c.C_DAT_CODE = c.C_PORT_CODE_P ");
			querySql = sqlBuffer.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(sqlBuffer);
		}
		return querySql;
	}

	public String getProductORPortSql() {
		StringBuffer buf = new StringBuffer();
		buf.append("     select b.C_IDEN, b.C_PORT_CODE, ");
		buf.append("     case ");
		buf.append("       when trim(b.C_PORT_CODE_P) is null then ");
		buf.append("         b.C_DAT_CODE ");
		buf.append("       else b.C_PORT_CODE_P end ");
		buf.append("       as fParaentCode, ");
		buf.append("       b.C_PORT_CODE,b.C_PORT_NAME_ST, ");
		buf.append("       b.C_PORT_NAME_EN,b.C_ASS_CODE, ");
		buf.append("       b.C_DC_CODE,b.D_BUILD,b.D_CLOSE,b.D_CLEAR, "); // 添加清算字段
		buf.append("       b.C_DESC, b.C_PORT_NAME,b.N_CHECK_STATE, ");
		buf.append("       b.C_UPDATE_BY,b.C_UPDATE_TIME, ");
		buf.append("       b.C_CHECK_BY,b.C_CHECK_TIME, ");
		buf.append("       'PORT_TYPE' as DATA_TYPE, ");
		buf.append("       b.C_DV_PORT_CODE,b.C_PORT_CODE_P, ");
		buf.append("       b.C_HDAY_CODE,b.C_DV_PROD_STATE,b.C_PORT_UNIT ");
		buf.append(",      b.C_DAT_CLS ,c_dat_code");
		buf.append("     from T_P_AB_PORT b  where b.N_CHECK_STATE = 1");
		buf.append("       and c_dat_cls is not null ");
		buf.append("       and c_dat_code is not null ");

		buf.append("   union ");

		buf.append("   select a.c_iden, ");
		buf.append("     a.c_pd_code,");
		buf.append("    '' as c_PORT_CODE_P, ");
		buf.append("    a.c_pd_code AS C_PORT_CODE, ");
		buf.append("     a.c_pd_name as C_PORT_NAME_ST,");
		buf.append("     '' as C_PORT_NAME_EN, '' as C_ASS_CODE, ");
		buf.append("     ''as C_DC_CODE, ");
		buf.append("     to_date('1999-12-14','YYYY-MM-DD') as D_BUILD, ");
		buf.append("     to_date('1999-12-14','YYYY-MM-DD') as D_CLOSE, ");
		buf.append("     to_date('9998-12-31','YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		buf.append("     '' as C_DESC, a.c_pd_name as C_PORT_NAME, ");
		buf.append("     1 as N_CHECK_STATE, '' as C_UPDATE_BY, ");
		buf.append("     '' as C_UPDATE_TIME,'' as C_CHECK_BY, ");
		buf.append("     '' as C_CHECK_TIME,'ASS_TYPE' as DATA_TYPE, ");
		buf.append("     ' ' as C_DV_PORT_CODE,'' as C_PORT_CODE_P, ");
		buf.append("     '' as C_HDAY_CODE ,'' as C_DV_PROD_STATE, '' as C_PORT_UNIT");
		buf.append(",     '' as C_DAT_CLS ,'' as c_dat_code");
		buf.append("   from t_l_pd_product a ");
		buf.append("   ");

		return buf.toString();
	}

	public String getTreePortDataByCodes() {
		StringBuffer buf = new StringBuffer();
		buf.append(" select * from t_p_ab_port a Connect by prior a.C_port_CODE = a.C_port_CODE_P ");
		buf.append(" start with a.C_port_Code in(select * from table(?))");
		return buf.toString();
	}

	public String getAllGroupAndPortSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT '' AS C_IDEN, ");
		buf.append("        G.C_GROUP_CODE AS C_DAT_CODE, ");
		buf.append("        '[root]' AS C_PORT_CODE_P, ");
		buf.append("        G.C_GROUP_CODE AS C_PORT_CODE, ");
		buf.append("        G.C_GROUP_NAME AS C_PORT_NAME_ST, ");
		buf.append("        G.C_GROUP_NAME AS C_PORT_NAME_EN, ");
		buf.append("        '' AS C_ASS_CODE, ");
		buf.append("        '' AS C_DC_CODE, ");
		buf.append("        TO_DATE('1900-01-01', 'yyyy-MM-dd') AS D_BUILD, ");
		buf.append("        TO_DATE('9998-12-31', 'yyyy-MM-dd') AS D_CLOSE, ");
		buf.append("        TO_DATE('9998-12-31', 'YYYY-MM-DD') AS D_CLEAR, ");
		buf.append("        '' AS C_DESC, ");
		buf.append("        G.C_GROUP_NAME AS C_PORT_NAME, ");
		buf.append("        1 AS N_CHECK_STATE, ");
		buf.append("        '' AS C_UPDATE_BY, ");
		buf.append("        '' AS C_UPDATE_TIME, ");
		buf.append("        '' AS C_CHECK_BY, ");
		buf.append("        '' AS C_CHECK_TIME, ");
		buf.append("        'GROUP_TYPE' AS DATA_TYPE, ");
		buf.append("        '' AS C_DV_PORT_CODE, ");
		buf.append("        '' AS C_HDAY_CODE, ");
		buf.append("        '' AS C_DV_PROD_STATE, ");
		buf.append("        '' AS C_PORT_UNIT, ");
		buf.append("        G.C_GROUP_CODE AS C_DAT_CLS ");
		buf.append("   FROM T_P_AB_GROUP G ");
		buf.append("  WHERE G.N_CHECK_STATE = 1 ");
		buf.append(" UNION ");
		buf.append(" SELECT '' C_IDEN, ");
		buf.append("        PORT.C_DAT_CODE, ");
		buf.append("        RELA.C_GROUP_CODE C_PORT_CODE_P, ");
		buf.append("        PORT.C_PORT_CODE, ");
		buf.append("        PORT.C_PORT_NAME_ST, ");
		buf.append("        PORT.C_PORT_NAME_EN, ");
		buf.append("        PORT.C_ASS_CODE, ");
		buf.append("        PORT.C_DC_CODE, ");
		buf.append("        PORT.D_BUILD, ");
		buf.append("        PORT.D_CLOSE, ");
		buf.append("        PORT.D_CLEAR, ");
		buf.append("        PORT.C_DESC, ");
		buf.append("        PORT.C_PORT_NAME, ");
		buf.append("        PORT.N_CHECK_STATE, ");
		buf.append("        '' AS C_UPDATE_BY, ");
		buf.append("        '' AS C_UPDATE_TIME, ");
		buf.append("        '' AS C_CHECK_BY, ");
		buf.append("        '' AS C_CHECK_TIME, ");
		buf.append("        'PORT_TYPE' AS DATA_TYPE, ");
		buf.append("        '' AS C_DV_PORT_CODE, ");
		buf.append("        '' AS C_HDAY_CODE, ");
		buf.append("        '' AS C_DV_PROD_STATE, ");
		buf.append("        '' AS C_PORT_UNIT, ");
		buf.append("        PORT.C_DAT_CLS AS C_DAT_CLS ");
		buf.append("   FROM T_P_AB_GROUP_RELA RELA ");
		buf.append("   LEFT JOIN T_P_AB_PORT PORT ");
		buf.append("     ON RELA.C_PORT_CODE = PORT.C_PORT_CODE ");
		buf.append("  WHERE PORT.N_CHECK_STATE = 1 ");
		buf.append("    AND RELA.N_CHECK_STATE = 1 ");
		buf.append("    AND RELA.C_PORT_CODE IN (SELECT * FROM TABLE(?)) ");
		return buf.toString();
	}
	
	public String getAllPortByGroupsSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT '' C_IDEN, ");
		buf.append("        PORT.C_DAT_CODE, ");
		buf.append("        RELA.C_GROUP_CODE C_PORT_CODE_P, ");
		buf.append("        PORT.C_PORT_CODE, ");
		buf.append("        PORT.C_PORT_NAME_ST, ");
		buf.append("        PORT.C_PORT_NAME_EN, ");
		buf.append("        PORT.C_ASS_CODE, ");
		buf.append("        PORT.C_DC_CODE, ");
		buf.append("        PORT.D_BUILD, ");
		buf.append("        PORT.D_CLOSE, ");
		buf.append("        PORT.D_CLEAR, ");
		buf.append("        PORT.D_COLSE_ACC, ");
		buf.append("        PORT.C_DESC, ");
		buf.append("        PORT.C_PORT_NAME, ");
		buf.append("        PORT.N_CHECK_STATE, ");
		buf.append("        '' AS C_UPDATE_BY, ");
		buf.append("        '' AS C_UPDATE_TIME, ");
		buf.append("        '' AS C_CHECK_BY, ");
		buf.append("        '' AS C_CHECK_TIME, ");
		buf.append("        'PORT_TYPE' AS DATA_TYPE, ");
		buf.append("        '' AS C_DV_PORT_CODE, ");
		buf.append("        '' AS C_HDAY_CODE, ");
		buf.append("        '' AS C_DV_PROD_STATE, ");
		buf.append("        '' AS C_PORT_UNIT, ");
		buf.append("        PORT.C_DAT_CLS AS C_DAT_CLS,PORT.C_ASSETS_CODE, ");
		buf.append("        RELA.C_GROUP_CODE AS C_GROUP_CODE ");
		buf.append("   FROM T_P_AB_GROUP_RELA RELA ");
		buf.append("   LEFT JOIN T_P_AB_PORT PORT ");
		buf.append("     ON RELA.C_PORT_CODE = PORT.C_PORT_CODE ");
		buf.append("  WHERE PORT.N_CHECK_STATE = 1 ");
		buf.append("    AND RELA.N_CHECK_STATE = 1 ");
		buf.append("    AND RELA.C_GROUP_CODE IN (SELECT * FROM TABLE(?)) ");
		buf.append(" 	AND exists (select 1 from T_P_AB_GROUP g where g.C_GROUP_CODE = RELA.C_GROUP_CODE and g.N_CHECK_STATE = 1) ");
		return buf.toString();
	}

	/**
	 * 获取和目标组合资产代码重复的组合列表
	 * 
	 * @return
	 * @author liuxiang 2015-7-16 BUG #115824 [紧急][招商证券]资产代码修改的问题
	 */
	public String getTheSameAssCodeListSql() {
		String sql = "SELECT * FROM T_P_AB_PORT A WHERE A.C_ASS_CODE = ? AND A.C_PORT_CODE != ?";
		return sql;
	}
	
	public String queryPortTypeSql() {
		StringBuffer buf = new StringBuffer();
		buf.append("        select '' AS C_IDEN,                                                 ");
		buf.append("               C_DAT_CODE as C_DAT_CODE,                                     ");
		buf.append("               '' as C_DAT_CLS,                                              ");
		buf.append("               c_dat_code_p as C_PORT_CODE_P,                                ");
		buf.append("               C_DAT_CODE as C_PORT_CODE,                                    ");
		buf.append("               C_DAT_NAME as C_PORT_NAME_ST,                                 ");
		buf.append("               C_DAT_NAME as C_PORT_NAME_EN,                                 ");
		buf.append("               '' as C_ASS_CODE,                                             ");
		buf.append("               '' as C_DC_CODE,                                              ");
		buf.append("               to_date('1999-12-14', 'YYYY-MM-DD') as D_BUILD,               ");
		buf.append("               to_date('1999-12-14', 'YYYY-MM-DD') as D_CLOSE,               ");
		buf.append("               to_date('9998-12-31', 'YYYY-MM-DD') as D_CLEAR,               ");
		buf.append("               '' as C_DESC,                                                 ");
		buf.append("               C_DAT_NAME as C_PORT_NAME,                                    ");
		buf.append("               1 as N_CHECK_STATE,                                           ");
		buf.append("               '' as C_UPDATE_BY,                                            ");
		buf.append("               '' as C_UPDATE_TIME,                                          ");
		buf.append("               '' as C_CHECK_BY,                                             ");
		buf.append("               '' as C_CHECK_TIME,                                           ");
		buf.append("               'ASS_TYPE' as DATA_TYPE,                                      ");
		buf.append("               ' ' as C_DV_PORT_CODE,                                        ");
		buf.append("               '' as C_HDAY_CODE,                                            ");
		buf.append("               '' as C_DV_PROD_STATE,                                        ");
		buf.append("               '' as C_PORT_UNIT                                             ");
		buf.append("          from T_S_DAT_ASS_TYPE                                              ");
		return buf.toString();
	}

	public String queryPortTreeSql() {
		StringBuffer buf = new StringBuffer();
		buf.append("select level, c.*                                                            ");
		buf.append("  from (select '' AS C_IDEN,                                                 ");
		buf.append("               C_DAT_CODE as C_DAT_CODE,                                     ");
		buf.append("               '' as C_DAT_CLS,                                              ");
		buf.append("               c_dat_code_p as C_PORT_CODE_P,                                ");
		buf.append("               C_DAT_CODE as C_PORT_CODE,                                    ");
		buf.append("               C_DAT_NAME as C_PORT_NAME_ST,                                 ");
		buf.append("               C_DAT_NAME as C_PORT_NAME_EN,                                 ");
		buf.append("               '' as C_ASS_CODE,                                             ");
		buf.append("               '' as C_DC_CODE,                                              ");
		buf.append("               to_date('1999-12-14', 'YYYY-MM-DD') as D_BUILD,               ");
		buf.append("               to_date('1999-12-14', 'YYYY-MM-DD') as D_CLOSE,               ");
		buf.append("               to_date('9998-12-31', 'YYYY-MM-DD') as D_CLEAR,               ");
		buf.append("               '' as C_DESC,                                                 ");
		buf.append("               C_DAT_NAME as C_PORT_NAME,                                    ");
		buf.append("               1 as N_CHECK_STATE,                                           ");
		buf.append("               '' as C_UPDATE_BY,                                            ");
		buf.append("               '' as C_UPDATE_TIME,                                          ");
		buf.append("               '' as C_CHECK_BY,                                             ");
		buf.append("               '' as C_CHECK_TIME,                                           ");
		buf.append("               'ASS_TYPE' as DATA_TYPE,                                      ");
		buf.append("               ' ' as C_DV_PORT_CODE,                                        ");
		buf.append("               '' as C_HDAY_CODE,                                            ");
		buf.append("               '' as C_DV_PROD_STATE,                                        ");
		buf.append("               '' as C_PORT_UNIT                                             ");
		buf.append("          from T_S_DAT_ASS_TYPE                                              ");
		buf.append("        union all                                                            ");
		buf.append("        select b.C_IDEN,                                                     ");
		buf.append("               b.C_DAT_CODE,                                                 ");
		buf.append("               C_DAT_CLS,                                                    ");
		buf.append("               case                                                          ");
		buf.append("                 when trim(b.C_PORT_CODE_P) is null then                     ");
		buf.append("                  b.C_DAT_CODE                                               ");
		buf.append("                 else                                                        ");
		buf.append("                  b.C_PORT_CODE_P                                            ");
		buf.append("               end as C_PORT_CODE_P,                                         ");
		buf.append("               b.C_PORT_CODE,                                                ");
		buf.append("               b.C_PORT_NAME_ST,                                             ");
		buf.append("               b.C_PORT_NAME_EN,                                             ");
		buf.append("               b.C_ASS_CODE,                                                 ");
		buf.append("               b.C_DC_CODE,                                                  ");
		buf.append("               b.D_BUILD,                                                    ");
		buf.append("               b.D_CLOSE,                                                    ");
		buf.append("               b.D_CLEAR,                                                    ");
		buf.append("               b.C_DESC,                                                     ");
		buf.append("               b.C_PORT_NAME,                                                ");
		buf.append("               b.N_CHECK_STATE,                                              ");
		buf.append("               b.C_UPDATE_BY,                                                ");
		buf.append("               b.C_UPDATE_TIME,                                              ");
		buf.append("               b.C_CHECK_BY,                                                 ");
		buf.append("               b.C_CHECK_TIME,                                               ");
		buf.append("               'PORT_TYPE' as DATA_TYPE,                                     ");
		buf.append("               b.C_DV_PORT_CODE,                                             ");
		buf.append("               b.C_HDAY_CODE,                                                ");
		buf.append("               b.C_DV_PROD_STATE,                                            ");
		buf.append("               b.C_PORT_UNIT                                                 ");
		buf.append("          from T_P_AB_PORT b                                                 ");
		buf.append("         where b.N_CHECK_STATE = 1) c                                        ");
		buf.append(" start with c.C_PORT_CODE in (select distinct c_port_code from  T_P_AB_PORT) ");
		buf.append("connect by prior c.C_PORT_CODE_P = c.C_PORT_CODE                             ");
		return buf.toString();
	}

	/**
	 * 根据组合代码获取用户自定义资产树形结构by yinyuyi 20170215
	 * 
	 * @return
	 */
	public String getDefaultAssPortByCodes() {
		String retSql = "";
		StringBuffer bufSql = new StringBuffer();
		bufSql.append(" select level, d.* ");
		bufSql.append(" from (select C_IDEN,C_TR_CODE as C_DAT_CODE, ");
		bufSql.append(" C_TR_CODE_P as C_PORT_CODE_P, ");
		bufSql.append(" C_TR_CODE as C_PORT_CODE, ");
		bufSql.append(" C_TR_NAME as C_PORT_NAME_ST, ");
		bufSql.append(" '' as C_PORT_NAME_EN, ");
		bufSql.append(" '' as C_ASS_CODE, ");
		bufSql.append(" '' as C_DC_CODE, ");
		bufSql.append(" to_date('1900-01-01', 'yyyy-MM-dd') as D_BUILD, ");
		bufSql.append(" to_date('9998-12-31', 'yyyy-MM-dd') as D_CLOSE, ");
		bufSql.append(" to_date('9998-12-31', 'YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		bufSql.append(" '' as C_DESC, ");
		bufSql.append(" '' as C_PORT_NAME, ");
		bufSql.append(" 1 as N_CHECK_STATE, ");
		bufSql.append(" '' as C_UPDATE_BY, ");
		bufSql.append(" '' as C_UPDATE_TIME, ");
		bufSql.append(" '' as C_CHECK_BY, ");
		bufSql.append(" '' as C_CHECK_TIME, ");
		bufSql.append(" 'ASS_TYPE' as DATA_TYPE, ");
		bufSql.append(" ' ' as C_DV_PORT_CODE, ");
		bufSql.append(" '' as C_HDAY_CODE,'' as C_DV_PROD_STATE, '' as C_PORT_UNIT ,'' as C_DAT_CLS");
		bufSql.append(" from T_P_AB_ASS_TR ");
		bufSql.append(" where C_TR_CODE_R =? ");
		bufSql.append(" and N_CHECK_STATE = 1 ");
		bufSql.append(" union all ");
		bufSql.append(" select b.C_IDEN,b.C_PORT_CODE as C_DAT_CODE, ");
		bufSql.append(" b.c_tr_code AS C_PORT_CODE_P,");
		bufSql.append(" b.C_PORT_CODE, ");
		bufSql.append(" c.C_PORT_NAME_ST, ");
		bufSql.append(" c.C_PORT_NAME_EN, ");
		bufSql.append(" c.C_ASS_CODE, ");
		bufSql.append(" c.C_DC_CODE, ");
		bufSql.append(" c.D_BUILD, ");
		bufSql.append(" c.D_CLOSE, ");
		bufSql.append(" c.D_CLEAR, "); // 添加清算字段
		bufSql.append(" b.C_TR_CODE_R as C_DESC, ");
		bufSql.append(" c.C_PORT_NAME, ");
		bufSql.append(" b.N_CHECK_STATE, ");
		bufSql.append(" c.C_UPDATE_BY, ");
		bufSql.append(" c.C_UPDATE_TIME, ");
		bufSql.append(" c.C_CHECK_BY, ");
		bufSql.append(" c.C_CHECK_TIME, ");
		bufSql.append(" 'PORT_TYPE' as DATA_TYPE, ");
		bufSql.append(" c.C_DV_PORT_CODE, ");
		bufSql.append(" c.C_HDAY_CODE,c.C_DV_PROD_STATE,c.C_PORT_UNIT,c.C_DAT_CLS ");
		bufSql.append(" from T_P_AB_ASS_TR_SUB b ");
		bufSql.append(" left join t_p_ab_port c ");
		bufSql.append(" on c.C_PORT_CODE = b.C_PORT_CODE ");
		bufSql.append(" where B.N_CHECK_STATE = 1 ");
		bufSql.append(" AND B.C_TR_CODE_R = ? ");
		bufSql.append(" AND C.C_PORT_CODE IN (SELECT * FROM TABLE(?)) ");
		bufSql.append(" ) d ");
		bufSql.append(" start with d.C_PORT_CODE_P = '[root]' ");
		bufSql.append(" connect by NOCYCLE prior d.C_DAT_CODE = d.C_PORT_CODE_P ");
		bufSql.append("  ");
		retSql = bufSql.toString();
		StringUtil.clearStringBuffer(bufSql);
		return retSql;
	}

	/**
	 * 根据产品编号和产品状态查询
	 * @Title queryByPortCodeAndStateSql 
	 * @Description 该方法用于测试方法注释格式
	 * @author gongjinzhi@ysstech.com
	 * @date 2017年2月20日下午7:04:12
	 * @return
	 * @return String
	 */
	public String queryByPortCodeAndStateSql() {
		return "select t.c_port_code,t.c_port_name,t.c_port_code_p from T_P_AB_PORT t where t.c_port_code in (SELECT * FROM TABLE(?)) and t.c_dv_prod_state in (SELECT * FROM TABLE(?)) and N_CHECK_STATE = 1";
	}
	
	/**
	 * //// By Jinghehe 2017-8-28 BUG #163743 【平安大华】产品树形结构界面点击新增与复制后，界面加载时间过长
	 * start with.....connect by 性能很慢，这边处理成，获取组合数据时候，在根据父节点代码查询父节点
	 * @return
	 */
	public String getPortParentPojoSql() {
		StringBuffer buf = new StringBuffer();
		buf.append("   select '' AS C_IDEN, ");
		buf.append("     C_DAT_CODE as C_DAT_CODE, '' as C_DAT_CLS,");
		buf.append("     c_dat_code_p as C_PORT_CODE_P, ");
		buf.append("     C_DAT_CODE as C_PORT_CODE, ");
		buf.append("     C_DAT_NAME as C_PORT_NAME_ST, ");
		buf.append("     '' as C_PORT_NAME_EN, '' as C_ASS_CODE, ");
		buf.append("     ''as C_DC_CODE, ");
		buf.append("     to_date('1999-12-14','YYYY-MM-DD') as D_BUILD, ");
		buf.append("     to_date('1999-12-14','YYYY-MM-DD') as D_CLOSE, ");
		buf.append("     to_date('9998-12-31','YYYY-MM-DD') as D_CLEAR, ");
		buf.append("     '' as C_DESC,'' as C_PORT_NAME, ");
		buf.append("     1 as N_CHECK_STATE, '' as C_UPDATE_BY, ");
		buf.append("     '' as C_UPDATE_TIME,'' as C_CHECK_BY, ");
		buf.append("     '' as C_CHECK_TIME,'ASS_TYPE' as DATA_TYPE, ");
		buf.append("     ' ' as C_DV_PORT_CODE, ");
		buf.append("     '' as C_HDAY_CODE,'' as C_DV_PROD_STATE, '' as C_PORT_UNIT");
		buf.append("   from  T_S_DAT_ASS_TYPE where C_DAT_CODE in(SELECT * FROM TABLE(?)) ");
		return buf.toString();
	}
	

	/**
	 * 获取资产类型，组合树类型
	 * STORY #45114 估值系统支持查询“组合树类型”列表的接口
	 * @return
	 */
	public String getAssSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		buf.append("select C_TR_CODE,");
		buf.append(" C_TR_NAME ");
		buf.append(" from T_P_AB_ASS_TR ");
		buf.append(" where N_CHECK_STATE = 1 ");
		buf.append(" and C_TR_CODE_P = '[root]' ");
		// buf.append(" and (c_user is null or c_user = ?) ");//由于华泰版本T_P_AB_ASS_TR中没有c_user字段，所以该接口在该版本废除

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * 获取用户自定义资产树形结构byleeyu20140228
	 * 
	 * @return
	 */
	public String getDefaultUserAssPortNoRight() {
		String retSql = "";
		StringBuffer bufSql = new StringBuffer();
		bufSql.append(" select level, d.* ");
		bufSql.append(" from (select C_IDEN,C_TR_CODE as C_DAT_CODE, ");
		bufSql.append(" C_TR_CODE_P as C_PORT_CODE_P, ");
		bufSql.append(" C_TR_CODE as C_PORT_CODE, ");
		bufSql.append(" C_TR_NAME as C_PORT_NAME_ST, ");
		bufSql.append(" '' as C_PORT_NAME_EN, ");
		bufSql.append(" '' as C_ASS_CODE, ");
		bufSql.append(" '' as C_DC_CODE, ");
		bufSql.append(" to_date('1900-01-01', 'yyyy-MM-dd') as D_BUILD, ");
		bufSql.append(" to_date('9998-12-31', 'yyyy-MM-dd') as D_CLOSE, ");
		bufSql.append(" to_date('9998-12-31', 'YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		bufSql.append(" to_date('9998-12-31', 'YYYY-MM-DD') as D_COLSE_ACC, "); // 添加关账字段
		bufSql.append(" '' as C_DESC, ");
		bufSql.append(" '' as C_PORT_NAME, ");
		bufSql.append(" 1 as N_CHECK_STATE, ");
		bufSql.append(" '' as C_UPDATE_BY, ");
		bufSql.append(" '' as C_UPDATE_TIME, ");
		bufSql.append(" '' as C_CHECK_BY, ");
		bufSql.append(" '' as C_CHECK_TIME, ");
		bufSql.append(" 'ASS_TYPE' as DATA_TYPE, ");
		bufSql.append(" ' ' as C_DV_PORT_CODE, ");
		bufSql.append(" '' as C_HDAY_CODE,'' as C_DV_PROD_STATE, '' as C_PORT_UNIT ,'' as C_DAT_CLS,'' as C_ASSETS_CODE ");
		bufSql.append(" from T_P_AB_ASS_TR ");
		bufSql.append(" where C_TR_CODE_R =? ");
		bufSql.append(" and N_CHECK_STATE = 1 ");
		bufSql.append(" union all ");
		bufSql.append(" select b.C_IDEN,b.C_PORT_CODE as C_DAT_CODE, ");
		bufSql.append(" b.c_tr_code AS C_PORT_CODE_P,");
		bufSql.append(" b.C_PORT_CODE, ");
		bufSql.append(" c.C_PORT_NAME_ST, ");
		bufSql.append(" c.C_PORT_NAME_EN, ");
		bufSql.append(" c.C_ASS_CODE, ");
		bufSql.append(" c.C_DC_CODE, ");
		bufSql.append(" c.D_BUILD, ");
		bufSql.append(" c.D_CLOSE, ");
		bufSql.append(" c.D_CLEAR,c.D_COLSE_ACC, "); // 添加清算字段 ，关账字段
		bufSql.append(" b.C_TR_CODE_R as C_DESC, ");
		bufSql.append(" c.C_PORT_NAME, ");
		bufSql.append(" b.N_CHECK_STATE, ");
		bufSql.append(" c.C_UPDATE_BY, ");
		bufSql.append(" c.C_UPDATE_TIME, ");
		bufSql.append(" c.C_CHECK_BY, ");
		bufSql.append(" c.C_CHECK_TIME, ");
		bufSql.append(" 'PORT_TYPE' as DATA_TYPE, ");
		bufSql.append(" c.C_DV_PORT_CODE, ");
		bufSql.append(" c.C_HDAY_CODE,c.C_DV_PROD_STATE,c.C_PORT_UNIT,c.C_DAT_CLS,c.C_ASSETS_CODE ");
		bufSql.append(" from T_P_AB_ASS_TR_SUB b ");
		bufSql.append(" left join t_p_ab_port c ");
		bufSql.append(" on c.C_PORT_CODE = b.C_PORT_CODE ");
		// bufSql.append(" where b.C_port_code in (select * from table(?)) ");
		bufSql.append(" where B.N_CHECK_STATE = 1 ");
		bufSql.append(" AND B.C_TR_CODE_R = ? ");
		//2017-02-06 STORY36179嘉实基金-新建一个产品树形结构时，可自动生成 “未分配”结构，将没有分配到结构下的组合默认放到“未分配”下
		//在其他页面展示出未分配下有权限的组合   zhujinyang
		bufSql.append(" UNION ALL ");
		bufSql.append(" SELECT A.C_IDEN, ");
		bufSql.append(" A.C_PORT_CODE AS C_DAT_CODE, ");
		bufSql.append(" C.C_TR_CODE AS C_PORT_CODE_P, ");
		bufSql.append(" A.C_PORT_CODE AS C_PORT_CODE, ");
		bufSql.append(" A.C_PORT_NAME_ST AS C_PORT_NAME_ST, ");
		bufSql.append(" A.C_PORT_NAME_EN, ");
		bufSql.append(" A.C_ASS_CODE, ");
		bufSql.append(" A.C_DC_CODE, ");
		bufSql.append(" A.D_BUILD, ");
		bufSql.append(" A.D_CLOSE, ");
		bufSql.append(" A.D_CLEAR,A.D_COLSE_ACC, ");
		bufSql.append(" C.C_TR_CODE_R AS C_DESC, ");
		bufSql.append(" A.C_PORT_NAME, ");
		bufSql.append(" A.N_CHECK_STATE, ");
		bufSql.append(" A.C_UPDATE_BY, ");
		bufSql.append(" A.C_UPDATE_TIME, ");
		bufSql.append(" A.C_CHECK_BY, ");
		bufSql.append(" A.C_CHECK_TIME, ");
		bufSql.append(" 'PORT_TYPE' as DATA_TYPE, ");
		bufSql.append(" A.C_DV_PORT_CODE, ");
		bufSql.append(" A.C_HDAY_CODE, ");
		bufSql.append(" A.C_DV_PROD_STATE, ");
		bufSql.append(" A.C_PORT_UNIT, ");
		bufSql.append(" A.C_DAT_CLS,A.C_ASSETS_CODE ");
		bufSql.append(" FROM T_P_AB_PORT A , T_P_AB_ASS_TR C ");
		bufSql.append(" WHERE NOT EXISTS ");
		bufSql.append(" (SELECT 1 FROM T_P_AB_ASS_TR_SUB B ");
		bufSql.append(" WHERE B.N_CHECK_STATE = 1 ");
		bufSql.append(" AND B.C_TR_CODE_R = ? ");
		bufSql.append(" AND B.C_PORT_CODE = A.C_PORT_CODE) ");
		// bufSql.append(" AND A.C_PORT_CODE IN (SELECT * FROM TABLE(?)) ");
		bufSql.append(" AND C.C_TR_CODE_P = ? ");
		bufSql.append(" AND C_TR_NAME = '未分配' ");
		bufSql.append(" ) d ");
		bufSql.append(" left join t_p_ab_ass_tr e "); // BUG #162973 by liulei
		bufSql.append(" on d.c_dat_code = e.c_tr_code ");
		// bufSql.append(" where e.c_user is null or c_user = ? ");
		bufSql.append(" where 1 = 1 ");
		bufSql.append(" start with d.C_PORT_CODE_P = '[root]' ");
		bufSql.append(" connect by NOCYCLE prior d.C_DAT_CODE = d.C_PORT_CODE_P ");
		bufSql.append("  ");
		retSql = bufSql.toString();
		StringUtil.clearStringBuffer(bufSql);
		return retSql;
	}

	/**
	 * 资产类型-管理人
	 * 
	 * @param C_DAT_CODE
	 *            资产类型代码
	 * @param C_DAT_NAME
	 *            资产类型名称
	 * @return
	 */
	public String getPortTreeViewZCGLSqlBuild(String C_DAT_CODE,
			String C_DAT_NAME) {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		buf.append("  union all ");
		buf.append("   SELECT '' AS C_IDEN ,'' AS C_DAT_CODE ,'");
		buf.append(C_DAT_CODE
				+ "' AS c_PORT_CODE_P,C_ORG_CODE ||''|| '"
				+ C_DAT_CODE
				+ "' AS C_PORT_CODE,C_ORG_NAME AS C_PORT_NAME_ST,C_ORG_NAME AS C_PORT_NAME_EN,'' AS C_ASS_CODE ");
		buf.append(" ,'' AS C_DC_CODE,to_date('1999-12-14', 'yyyy-MM-dd') AS D_BUILD,to_date('1999-12-14', 'yyyy-MM-dd') AS D_CLOSE ,to_date('9998-12-31', 'YYYY-MM-DD') AS D_CLEAR ");
		buf.append("  ,'' AS C_DESC ,C_ORG_NAME AS C_PORT_NAME,1 AS N_CHECK_STATE,'' AS C_UPDATE_BY ,'' AS C_UPDATE_TIME ,'' AS C_CHECK_BY ,'' AS C_CHECK_TIME ,'GL_TYPE' AS DATA_TYPE,'' AS C_DV_PORT_CODE");
		buf.append("  ,'' AS C_HDAY_CODE  ,'' AS C_DV_PROD_STATE ,'' AS C_PORT_UNIT,'CLS_PT' AS C_DAT_CLS FROM T_P_BI_ORG ");
		buf.append("  where C_ORG_CODE_p is null and C_DV_MANAGER ='MANAGER' ");
		retSql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return retSql;
	}

	/**
	 * xyh<br>
	 * 2018-1-22<br>
	 * 资产类型-管理人 <br>
	 * 
	 * @param dbnameresolver
	 * @param buf
	 * @param sql
	 */
	public String getPortTreeViewZCGLSql(DBNameResolver dbNameResolver,
			String sql) {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		setTreeSqlBufZCGL(dbNameResolver, buf, sql);
		retSql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return retSql;
	}

	/**
	 * xyh<br>
	 * 2018-1-22<br>
	 * 资产类型-管理人 <br>
	 * 
	 * @param dbnameresolver
	 * @param buf
	 * @param sql
	 */
	public void setTreeSqlBufZCGL(DBNameResolver dbnameresolver,
			StringBuffer buf, String sql) {
		buf.append(" select '' AS C_IDEN, ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DAT_CODE"));
		buf.append(" AS ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DAT_CODE"));
		buf.append(", ");
		buf.append(" c_dat_code_p as c_PORT_CODE_P, ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DAT_CODE"));
		buf.append(" AS ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_CODE"));
		buf.append(", ");
		buf.append(" C_DAT_NAME as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_NAME_ST"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_NAME_EN"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_ASS_CODE"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DC_CODE"));
		buf.append(", ");
		buf.append(" to_date('1999-12-14', 'yyyy-MM-dd') as  ");
		buf.append(getColumnNameByProperty(dbnameresolver, "d_BUILD"));
		buf.append(", ");
		buf.append(" to_date('1999-12-14', 'yyyy-MM-dd') as  ");
		buf.append(getColumnNameByProperty(dbnameresolver, "d_CLOSE"));
		buf.append(", ");
		buf.append(" to_date('9998-12-31', 'YYYY-MM-DD') as D_CLEAR, "); // 添加清算字段
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DESC"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_PORT_NAME"));
		buf.append(", ");
		buf.append(" 1 as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "auditState"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "modifier"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "modifyDate"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "operator"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "auditDate"));
		buf.append(", ");
		buf.append(" 'ASS_TYPE' as DATA_TYPE ");
		// buf.append(getColumnNameByProperty(dbnameresolver, "dATA_TYPE"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_DV_PORT_CODE"));
		buf.append(", ");
		buf.append(" '' as ");
		buf.append(getColumnNameByProperty(dbnameresolver, "c_HDAY_CODE"));
		buf.append(",'' as C_DV_PROD_STATE"); 
		buf.append(",'' as C_PORT_UNIT"); 
		buf.append(", C_DAT_CODE "); 
		buf.append(" AS C_DAT_CLS ");
		buf.append("  FROM T_S_DAT_ASS_TYPE ");
		buf.append(sql);
		buf.append("  union all ");
		buf.append("  select '' AS C_IDEN, '' AS C_DAT_CODE, case when tg.c_org_code_p is null then tg.c_org_code || '' || a.c_dat_code  else tg.c_org_code_p || '' || a.c_dat_code  end AS c_PORT_CODE_P , C_PORT_CODE AS C_PORT_CODE,C_PORT_NAME_ST AS C_PORT_NAME_ST, a.c_port_name AS C_PORT_NAME_EN,'' AS C_ASS_CODE,'' AS C_DC_CODE, ");
		buf.append("   to_date('1999-12-14', 'yyyy-MM-dd') AS D_BUILD, to_date('1999-12-14', 'yyyy-MM-dd') AS D_CLOSE,to_date('9998-12-31', 'YYYY-MM-DD') AS D_CLEAR,'' AS C_DESC,a.c_port_name AS C_PORT_NAME,1 AS N_CHECK_STATE, ");
		buf.append("  '' AS C_UPDATE_BY, '' AS C_UPDATE_TIME,'' AS C_CHECK_BY,'' AS C_CHECK_TIME,'PORT_TYPE' AS DATA_TYPE,'' AS C_DV_PORT_CODE, '' AS C_HDAY_CODE,'' AS C_DV_PROD_STATE,'' AS C_PORT_UNIT,'CLS_PT' AS C_DAT_CLS ");
		buf.append("  from (SELECT b.*,TP.c_rela_code FROM T_P_AB_PORT b left join T_P_AB_PORT_RELA tp   on tp.c_port_code = b.c_port_code");
		buf.append("  and tp.n_check_state = '1' and tp.c_rela_type='RELA_ORG' AND TP.C_DV_TYPE_CODE = 'MANAGER') a left join T_P_BI_ORG tg on a.c_rela_code = tg.c_org_code WHERE tg.C_DV_MANAGER ='MANAGER'");
		buf.append("  union all ");
		buf.append("  select '' AS C_IDEN, '' AS C_DAT_CODE,");
		buf.append("  'WGL'|| '' || c.c_dat_code AS c_PORT_CODE_P , C_PORT_CODE AS C_PORT_CODE,C_PORT_NAME_ST AS C_PORT_NAME_ST, c.c_port_name AS C_PORT_NAME_EN,'' AS C_ASS_CODE,'' AS C_DC_CODE, ");
		buf.append("   to_date('1999-12-14', 'yyyy-MM-dd') AS D_BUILD, to_date('1999-12-14', 'yyyy-MM-dd') AS D_CLOSE,to_date('9998-12-31', 'YYYY-MM-DD') AS D_CLEAR,'' AS C_DESC,c.c_port_name AS C_PORT_NAME,1 AS N_CHECK_STATE, ");
		buf.append("  '' AS C_UPDATE_BY, '' AS C_UPDATE_TIME,'' AS C_CHECK_BY,'' AS C_CHECK_TIME,'PORT_TYPE' AS DATA_TYPE,'' AS C_DV_PORT_CODE, '' AS C_HDAY_CODE,'' AS C_DV_PROD_STATE,'' AS C_PORT_UNIT,'CLS_PT' AS C_DAT_CLS ");
		buf.append("  from (select * FROM (SELECT b.*,TP.c_rela_code FROM T_P_AB_PORT b left join T_P_AB_PORT_RELA tp   on tp.c_port_code = b.c_port_code");
		buf.append("  and tp.n_check_state = '1' and tp.c_rela_type='RELA_ORG' AND TP.C_DV_TYPE_CODE = 'MANAGER') a left join T_P_BI_ORG tg on a.c_rela_code = tg.c_org_code");
		buf.append("  WHERE tg.c_dv_trustee is null or  tg.c_dv_manager != 'MANAGER')c  where  C_PORT_CODE not in (  select C_PORT_CODE AS C_PORT_CODE from (SELECT b.*, TP.c_rela_code FROM T_P_AB_PORT b");
		buf.append("  left join T_P_AB_PORT_RELA tp on tp.c_port_code = b.c_port_code and tp.n_check_state = '1' and tp.c_rela_type = 'RELA_ORG' AND TP.C_DV_TYPE_CODE = 'MANAGER') a");
		buf.append("  left join T_P_BI_ORG tg on a.c_rela_code = tg.c_org_code WHERE  tg.C_DV_MANAGER ='MANAGER')");
		buf.append("  union all ");
		buf.append("  select '' AS C_IDEN, '' AS C_DAT_CODE,");
		buf.append("  'WGL'|| '' || a.c_dat_code AS c_PORT_CODE_P , C_PORT_CODE AS C_PORT_CODE,C_PORT_NAME_ST AS C_PORT_NAME_ST, a.c_port_name AS C_PORT_NAME_EN,'' AS C_ASS_CODE,'' AS C_DC_CODE, ");
		buf.append("   to_date('1999-12-14', 'yyyy-MM-dd') AS D_BUILD, to_date('1999-12-14', 'yyyy-MM-dd') AS D_CLOSE,to_date('9998-12-31', 'YYYY-MM-DD') AS D_CLEAR,'' AS C_DESC,a.c_port_name AS C_PORT_NAME,1 AS N_CHECK_STATE, ");
		buf.append("  '' AS C_UPDATE_BY, '' AS C_UPDATE_TIME,'' AS C_CHECK_BY,'' AS C_CHECK_TIME,'PORT_TYPE' AS DATA_TYPE,'' AS C_DV_PORT_CODE, '' AS C_HDAY_CODE,'' AS C_DV_PROD_STATE,'' AS C_PORT_UNIT,'CLS_PT' AS C_DAT_CLS ");
		buf.append("  FROM (SELECT b.* FROM T_P_AB_PORT b where c_port_code not in(select c_port_code from T_P_AB_PORT_RELA tp WHERE tp.n_check_state = '1' AND tp.c_rela_type = 'RELA_ORG' AND TP.C_DV_TYPE_CODE = 'MANAGER')   ) a");
	}

	/**
	 * 查出所有与组合相关的信息  --纳税人类型-“结转频率”-管理人名称-资产类型-组合代码<br>
	 * C_DV_TAXPAYER_TYPE| C_DV_TAXPAYER_TYPE_NAME| C_JZ_FREQ| C_JZ_FREQ_NAME| C_ORG_NAME| C_ORG_CODE| C_DAT_NAME| C_DAT_CODE| C_PORT_CODE| C_PORT_NAME
	 *   VAT_PAYER_YB		一般纳税人				CF_MONTH_END		每月末			赢时胜		YSS		证券投资基金	ASS_ZQTZJJ		CSWANG		测试王
	 * @return
	 */
//	public String getPortALLINFOSql() {
//		String retSql = "";
//		StringBuffer buf = new StringBuffer();
//		buf.append(" SELECT CASE  ");   
//		buf.append(" 		WHEN f.c_dv_taxpayer_type IS NULL THEN 'WNSLX' ");   
//		buf.append(" 		ELSE f.c_dv_taxpayer_type ");   
//		buf.append(" 	END AS c_dv_taxpayer_type ");   
//		buf.append(" 	, CASE  ");   
//		buf.append(" 		WHEN f.c_dv_taxpayer_type_name IS NULL THEN '无纳税类型' ");   
//		buf.append(" 		ELSE f.c_dv_taxpayer_type_name ");   
//		buf.append(" 	END AS c_dv_taxpayer_type_name ");   
//		buf.append(" 	, CASE  ");   
//		buf.append(" 		WHEN e.C_JZ_FREQ IS NULL THEN 'WJZLX' ");   
//		buf.append(" 		ELSE e.C_JZ_FREQ ");   
//		buf.append(" 	END AS C_JZ_FREQ ");   
//		buf.append(" 	, CASE  ");   
//		buf.append(" 		WHEN e.C_JZ_FREQ_name IS NULL THEN '无结转频率' ");   
//		buf.append(" 		ELSE e.C_JZ_FREQ_name ");   
//		buf.append(" 	END AS C_JZ_FREQ_name ");   
//		buf.append(" 	, CASE  ");   
//		buf.append(" 		WHEN f.c_org_code IS NULL THEN 'WGLLX' ");   
//		buf.append(" 		ELSE f.c_org_code ");   
//		buf.append(" 	END AS c_org_code ");   
//		buf.append(" 	, CASE  ");   
//		buf.append(" 		WHEN f.c_org_name IS NULL THEN '无管理人' ");   
//		buf.append(" 		ELSE f.c_org_name ");   
//		buf.append(" 	END AS c_org_name, ass.C_DAT_NAME, ass.C_DAT_code, a.c_port_code, a.c_port_name ");   
//		buf.append(" FROM T_P_AB_PORT a ");   
//		buf.append(" 	JOIN T_S_DAT_ASS_TYPE ass ON a.C_DAT_CODE = ass.C_DAT_CODE ");   
//		buf.append(" 	LEFT JOIN T_P_AB_PORT_RELA b ");   
//		buf.append(" 	ON a.c_port_code = b.c_port_code ");   
//		buf.append(" 		AND b.c_rela_type = 'RELA_ORG' AND b.C_DV_TYPE_CODE = 'MANAGER'");   
//		buf.append(" 		AND b.N_CHECK_STATE = 1 ");   
//		buf.append(" 	LEFT JOIN T_E_EXEC_PLAN_RELA c ");   
//		buf.append(" 	ON a.c_port_code = c.c_port_code ");   
//		buf.append(" 		AND c.c_plan_type = 'AO_VAT_PLAN' ");   
//		buf.append(" 		AND c.N_CHECK_STATE = 1 ");   
//		buf.append(" 	LEFT JOIN ( ");   
//		buf.append(" 		SELECT C_JZ_FREQ, voc.c_dv_name AS C_JZ_FREQ_name, dd.c_plan_code ");   
//		buf.append(" 		FROM t_p_ao_vat_plan_gene d ");   
//		buf.append(" 			JOIN t_s_dv_voc voc ON d.c_jz_freq = voc.c_dv_code ");   
//		buf.append(" 			LEFT JOIN ( ");   
//		buf.append(" 				SELECT c_iden, c_plan_code ");   
//		buf.append(" 				FROM t_e_exec_plan ");   
//		buf.append(" 				WHERE c_plan_type = 'AO_VAT_PLAN' ");   
//		buf.append(" 					AND N_CHECK_STATE = 1 ");   
//		buf.append(" 			) dd ");   
//		buf.append(" 			ON d.c_rela_id = dd.c_iden ");   
//		buf.append(" 		WHERE d.C_TAX_TYPE = 'JR' ");   
//		buf.append(" 	) e ");   
//		buf.append(" 	ON e.c_plan_code = c.c_plan_code ");   
//		buf.append(" 	LEFT JOIN ( ");   
//		buf.append(" 		SELECT voc.c_dv_name AS c_dv_taxpayer_type_name, org.* ");   
//		buf.append(" 		FROM T_P_BI_ORG org ");   
//		buf.append(" 			left JOIN t_s_dv_voc voc ON voc.c_dv_code = org.c_dv_taxpayer_type ");   
//		buf.append(" 		WHERE C_ORG_CODE_p IS NULL ");   
//		buf.append(" 			AND C_DV_MANAGER = 'MANAGER' ");   
//		buf.append(" 			AND org.n_check_state = 1 ");   
//		buf.append(" 	) f ");   
//		buf.append(" 	ON f.c_org_code = b.c_rela_code ");   
//		buf.append(" WHERE a.N_CHECK_STATE = 1 ");   
//		buf.append("   ORDER BY  C_DV_TAXPAYER_TYPE,C_JZ_FREQ,C_ORG_CODE,C_DAT_CODE,C_PORT_CODE");
//		retSql = buf.toString();
//		StringUtil.clearStringBuffer(buf);
//		return retSql;
//	}

	public String getPortStateByCode() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT C_PORT_CODE,C_DV_PROD_STATE FROM T_P_AB_PORT A WHERE A.C_PORT_CODE IN (SELECT * FROM TABLE(?)) ");
		return buf.toString();
	}
	
	/**
	 * 
	 * @param portCode
	 * @return
	 */
	public String getUnitLayerPort() {
		String sql = "";
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" SELECT * FROM T_P_AB_PORT ");
		sqlBuff.append(" WHERE C_PORT_CODE_P = ? and C_DV_PORT_CODE = 'UNIT_LAYER' ");
		sql = sqlBuff.toString();
		StringUtil.clearStringBuffer(sqlBuff);
		return sql;
	}

	/**
	 * 根据组合代码查询组合信息
	 * src：STORY #62048 新增加的组合自动关联自动化估值方案
	 * author：shijian@ysstech.com
	 * date：2018年10月22日
	 */
	public String getPortByCode() {
		return "SELECT A.* FROM T_P_AB_PORT A WHERE a.N_CHECK_STATE = 1 and a.C_PORT_CODE IN (SELECT * FROM TABLE(?)) ";
	}
	
}