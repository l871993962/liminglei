package com.yss.ams.product.information.modules.ab.assetstree_b.dao;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.VocabularyConsts;

/**
 * <产品树型结构>SQL语句构造类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class AssetsTree_BSqlBuilder implements SQLBuilder {

	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
//			if (!paraNameList.contains("test")) {
//			this.setWhereSql(valueFieldbuf, paraNameList);
//			buf.append("  select count(*) as CNT from T_P_AB_ASS_TR_SUB  a ");
//			if (valueFieldbuf.length() > 0) {
//				buf.append(" where ").append(valueFieldbuf);
//			}
//			retSql = buf.toString();
//			}else{
//				//2017-02-06 STORY36179嘉实基金-新建一个产品树形结构时，可自动生成 “未分配”结构，将没有分配到结构下的组合默认放到“未分配”下
//				//查询为“未分配”时，显示所有未分配的组合
//				buf.append(" select count(*) as CNT from (");
//				buf.append(" select (select c_tr_code ");
//				buf.append(" from T_P_AB_ASS_TR ");
//				buf.append(" where c_tr_code = ?) as c_tr_code, ");
//				buf.append(" null c_iden,");
//				buf.append(" null  C_TR_CODE_R, ");
//				buf.append("  0 as N_CHECK_STATE, ");
//				buf.append("  C_PORT_CODE, ");
//				buf.append("  null c_check_time, ");
//				buf.append("  null c_check_by, ");
//				buf.append("  null c_update_by, ");
//				buf.append("  null c_update_time, ");
//				buf.append("  null C_DESC ");
//				buf.append(" from t_p_ab_port ");
//				buf.append(" where c_port_code not in ");
//				buf.append(" (select c_port_code ");
//				buf.append(" from T_P_AB_ASS_TR_SUB ");
//				buf.append(" where c_tr_code_r in ");
//				buf.append(" (select c_tr_code_r ");
//				buf.append(" from T_P_AB_ASS_TR ");
//				buf.append("  where c_tr_code = ?))  ");
//				buf.append(" ) ") ;
//				retSql = buf.toString();
//			}
			
			if (paraNameList.contains("test")) {
				buf.append("  select count(*) as CNT from (");
				this.setWhereSql(valueFieldbuf, paraNameList);
				buf.append(" select a.c_iden, ");
				buf.append("        a.c_tr_code, ");
				buf.append("        a.c_tr_code_r, ");
				buf.append("        a.c_port_code, ");
				buf.append("        a.c_desc, ");
				buf.append("        a.n_check_state, ");
				buf.append("        a.c_update_by, ");
				buf.append("        a.c_update_time, ");
				buf.append("        a.c_check_by, ");
				buf.append("        a.c_check_time ");
				buf.append(" from T_P_AB_ASS_TR_SUB  a ");
				if (valueFieldbuf.length() > 0) {
					buf.append(" where ").append(valueFieldbuf);
				}
				buf.append("  UNION ALL (SELECT *FROM (SELECT NULL C_IDEN,");
				buf.append(" (CASE  WHEN TRIM(C_PORT_CODE_P) IS NULL THEN C_DAT_CODE");
				buf.append(" ELSE C_PORT_CODE_P");
				buf.append(" END) AS C_TR_CODE_R, ");
				buf.append("  (SELECT C_TR_CODE");
				buf.append(" FROM T_P_AB_ASS_TR");
				buf.append(" WHERE C_TR_CODE_P = ?");
				buf.append(" and C_TR_NAME = '未分配')");
				buf.append(" AS C_TR_CODE, ");
				buf.append(" C_PORT_CODE, ");
				buf.append(" ' ' AS C_DESC,");
				buf.append(" 0 as N_CHECK_STATE,");
				buf.append(" null c_check_by,");
				buf.append(" null c_check_time,");
				buf.append(" null c_update_by,");
				buf.append(" null c_update_time");
				buf.append(" FROM T_P_AB_PORT ");
				buf.append(" WHERE C_PORT_CODE NOT IN ");
				buf.append(" (SELECT C_PORT_CODE ");
				buf.append(" FROM T_P_AB_ASS_TR_SUB ");
				buf.append(" WHERE C_TR_CODE_R IN ");
				buf.append(" (SELECT C_TR_CODE_P ");
				buf.append(" FROM T_P_AB_ASS_TR ");
				buf.append(" WHERE C_TR_CODE_R = ?))");
				buf.append(" )");
				//edit by zhoushuhang 20180320 BUG196104产品树形结构测试问题汇总
				buf.append(" WHERE C_PORT_CODE IN (SELECT * FROM TABLE(?))))");
			}else{
				this.setWhereSql(valueFieldbuf, paraNameList);
				buf.append("  select count(*) as CNT from T_P_AB_ASS_TR_SUB  a ");
				if (valueFieldbuf.length() > 0) {
					buf.append(" where ").append(valueFieldbuf);
				}
			}
			
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
//			if (!paraNameList.contains("test")) {
//				this.setWhereSql(valueFieldbuf, paraNameList);
//				buf.append("  select * from T_P_AB_ASS_TR_SUB  a ");
//				if (valueFieldbuf.length() > 0) {
//					buf.append(" where ").append(valueFieldbuf);
//				}
//				buf.append(" ORDER BY a.N_CHECK_STATE, a.C_IDEN ");
//
//				retSql = buf.toString();
//
//			} else {
//				//2017-02-06 STORY36179嘉实基金-新建一个产品树形结构时，可自动生成 “未分配”结构，将没有分配到结构下的组合默认放到“未分配”下
//				//查询为“未分配”时，显示所有未分配的组合
//				buf.append(" select (select c_tr_code ");
//				buf.append(" from T_P_AB_ASS_TR ");
//				buf.append(" where c_tr_code = ?) as c_tr_code, ");
//				buf.append(" null c_iden,");
//				buf.append(" null  C_TR_CODE_R, ");
//				buf.append("  0 as N_CHECK_STATE, ");
//				buf.append("  C_PORT_CODE, ");
//				buf.append("  null c_check_time, ");
//				buf.append("  null c_check_by, ");
//				buf.append("  null c_update_by, ");
//				buf.append("  null c_update_time, ");
//				buf.append("  null C_DESC ");
//				buf.append(" from t_p_ab_port ");
//				buf.append(" where c_port_code not in ");
//				buf.append(" (select c_port_code ");
//				buf.append(" from T_P_AB_ASS_TR_SUB ");
//				buf.append(" where c_tr_code_r in ");
//				buf.append(" (select c_tr_code_r ");
//				buf.append(" from T_P_AB_ASS_TR ");
//				buf.append("  where c_tr_code = ?))  ");
//				retSql = buf.toString();
//
//			}
			
			//add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" select a.c_iden, ");
			buf.append("        a.c_tr_code, ");
			buf.append("        a.c_tr_code_r, ");
			buf.append("        a.c_port_code, ");
			buf.append("        a.c_desc, ");
			buf.append("        a.n_check_state, ");
			buf.append("        a.c_update_by, ");
			buf.append("        a.c_update_time, ");
			buf.append("        a.c_check_by, ");
			buf.append("        a.c_check_time ");
			buf.append(" from T_P_AB_ASS_TR_SUB  a ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" where ").append(valueFieldbuf);
			}
			
			//增加未分配的组合数据
			if (paraNameList.contains("test")) {
				buf.append("  UNION ALL (SELECT NULL C_IDEN,");
				buf.append(" (CASE  WHEN TRIM(C_PORT_CODE_P) IS NULL THEN C_DAT_CODE");
				buf.append(" ELSE C_PORT_CODE_P");
				buf.append(" END) AS C_TR_CODE_R, ");
				buf.append("  (SELECT C_TR_CODE");
				buf.append(" FROM T_P_AB_ASS_TR");
				buf.append(" WHERE C_TR_CODE_P = ?");
				buf.append(" and C_TR_NAME = '未分配')");
				buf.append(" AS C_TR_CODE, ");
				buf.append(" C_PORT_CODE, ");
				buf.append(" ' ' AS C_DESC,");
				buf.append(" 0 as N_CHECK_STATE,");
				buf.append(" null c_check_by,");
				buf.append(" null c_check_time,");
				buf.append(" null c_update_by,");
				buf.append(" null c_update_time");
				buf.append(" FROM T_P_AB_PORT ");
				buf.append(" WHERE C_PORT_CODE NOT IN ");
				buf.append(" (SELECT C_PORT_CODE ");
				buf.append(" FROM T_P_AB_ASS_TR_SUB ");
				buf.append(" WHERE C_TR_CODE_R IN ");
				buf.append(" (SELECT C_TR_CODE_P ");
				buf.append(" FROM T_P_AB_ASS_TR ");
				buf.append(" WHERE C_TR_CODE_R = ?))");
				//edit by zhoushuhang 20180320 BUG196104产品树形结构测试问题汇总
				buf.append(" AND C_PORT_CODE IN (SELECT * FROM TABLE(?))");
				buf.append(" START WITH TRIM(C_PORT_CODE_P) IS NULL CONNECT BY PRIOR C_PORT_CODE = C_PORT_CODE_P ");
				buf.append(" )");
			}
				
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return retSql;
	}

	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));
		for (String fieldName : paraNameList) {
			if (fieldName.equals("ARRAY_C_TR_CODE")) {
				valueFieldbuf
						.append("  a.C_TR_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append("a.C_DEL_TIME >= ? AND ");
			} else if (fieldName.equals("C_DEL_TIME_END")) {
				valueFieldbuf.append("a.C_DEL_TIME <= ? AND ");
			//edit by gh 2016-9-27 STORY33240【南方基金】复制建仓继承内容增加群组功能的勾选选项
			}else if (fieldName.equals("C_PORT_CODE")) {
				valueFieldbuf.append("a.C_PORT_CODE = ? AND ");
			//add by zhoushuhang 2018-03-13 STORY49928产品树形结构界面优化
			//增加查询条件:父级节点代码、组合代码
			}else if (fieldName.equals("C_TR_CODE_R")) {
//				valueFieldbuf.append(" a.C_TR_CODE_R IN (SELECT C_TR_CODE_P FROM T_P_AB_ASS_TR WHERE C_TR_CODE_R = ?) AND ");
				valueFieldbuf.append(" a.C_TR_CODE_R = ? AND ");
			}else if (fieldName.equals("ARRAY_C_PORT_CODE")) {
				valueFieldbuf.append("a.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}else if (fieldName.equals("ARRAY_C_IDEN")) {
                valueFieldbuf.append("a.C_IDEN IN (SELECT * FROM TABLE(?)) AND ");
        }
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}

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
		return dbnameresolver.getColumnName(AssetsTree_BColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(AssetsTree_BTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(AssetsTree_BTableName.userInfo);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return dbNameResolver.getLogSequenceName(AssetsTree_BTableName.userInfo);
	}
	
	/**
	 * Author : zhoushuhang
	 * Date   : 2018-03-13
	 * Status : Add
	 * Comment: STORY49928产品树形结构界面优化
	 * @description : 拼接查询执行sql
	 */
	public String getQuerySql(String sql, HashMap<String, Object> paraMap)
			throws Exception {
		return getSelectSql(sql, paraMap);
	}

	/**
	 * Author : zhoushuhang
	 * Date   : 2018-03-13
	 * Status : Add
	 * Comment: STORY49928产品树形结构界面优化
	 * @description : 查询执行sql拼接父子节点
	 */
	private String getSelectSql(String sql, HashMap<String, Object> paraMap) {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT");
		buf.append(" C_IDEN,");
		buf.append(" C_TR_CODE, ");
//		buf.append(" C_TR_CODE_R,");
		buf.append(" C_TR_CODE AS C_TR_CODE_R,");
		buf.append(" C_PORT_CODE,");
		buf.append(" C_DESC,");
		buf.append(" N_CHECK_STATE, ");
		buf.append(" C_UPDATE_BY,");
		buf.append(" C_UPDATE_TIME,");
		buf.append(" C_CHECK_BY, ");
		buf.append(" C_CHECK_TIME,");
		buf.append(" 0 as isParent");
		buf.append(" FROM MAIN_SQL");
		//edit by zhoushuhang 20180320 BUG196104产品树形结构测试问题汇总
		String parentCodeR = (String)paraMap.get("C_TR_CODE_R");
		//查询不显示未分配数据
		if(!paraMap.containsKey("test")){
			buf.append(" UNION ALL ");
			buf.append(" SELECT");
			buf.append(" B.C_IDEN,");
//			buf.append(" B.C_TR_CODE, B.C_TR_CODE, B.C_TR_CODE_P,C_TR_NAME,B.N_CHECK_STATE,b.C_UPDATE_BY,");
//			buf.append(" B.C_UPDATE_TIME, B.C_CHECK_BY,B.C_CHECK_TIME ");
			buf.append("  B.C_TR_CODE, B.C_TR_CODE_P,B.C_TR_CODE, C_TR_NAME,B.N_CHECK_STATE,'' AS C_UPDATE_BY,");
			buf.append(" '' AS C_UPDATE_TIME, '' AS C_CHECK_BY,'' AS C_CHECK_TIME, 1 as isParent ");
			buf.append(" FROM T_P_AB_ASS_TR B WHERE B.C_TR_NAME != '未分配'");
			buf.append(" START WITH B.C_TR_CODE IN (SELECT C_TR_CODE_R FROM MAIN_SQL)");
			buf.append(" OR B.C_TR_CODE = '" + parentCodeR + "'");
			buf.append(" CONNECT BY PRIOR B.C_TR_CODE = B.C_TR_CODE_P");
		}else{
			//查询显示未分配数据
			String parentCode = (String)paraMap.get("C_TR_CODE");
			buf.append(" UNION ALL ");
			buf.append(" SELECT *FROM (");
			buf.append("  SELECT NULL C_IDEN,");
			buf.append("  B.C_DAT_CODE,");
			buf.append("  (SELECT C_TR_CODE");
			buf.append(" FROM T_P_AB_ASS_TR");
			buf.append(" WHERE C_TR_CODE_P = '" + parentCode + "'");
			buf.append(" and C_TR_NAME = '未分配')");
			buf.append(" AS C_TR_CODE_R, ");
			buf.append(" B.C_DAT_CODE C_PORT_CODE, ");
			buf.append(" ' ' AS C_DESC,");
			buf.append(" 0 as N_CHECK_STATE,");
			buf.append(" null c_check_by,");
			buf.append(" null c_check_time,");
			buf.append(" null c_update_by,");
			buf.append(" null c_update_time,");
			buf.append(" 1 as isParent");
			buf.append(" FROM T_S_DAT_ASS_TYPE B ) C");
			buf.append(" START WITH C.C_DAT_CODE IN (SELECT C_TR_CODE FROM MAIN_SQL)");
			buf.append(" CONNECT BY PRIOR C.C_TR_CODE_R = C.C_DAT_CODE");
			buf.append(" UNION ALL ");
			buf.append(" SELECT DISTINCT");
			buf.append(" B.C_IDEN,");
			buf.append("  B.C_TR_CODE, B.C_TR_CODE_P,B.C_TR_CODE, C_TR_NAME,B.N_CHECK_STATE,'' AS C_UPDATE_BY,");
			buf.append(" '' AS C_UPDATE_TIME, '' AS C_CHECK_BY,'' AS C_CHECK_TIME,  1 as isParent ");
			buf.append(" FROM T_P_AB_ASS_TR B");
			buf.append(" START WITH B.C_TR_CODE IN (SELECT C_TR_CODE_R FROM MAIN_SQL)");
			buf.append(" OR B.C_TR_CODE = '" + parentCode + "'");
			buf.append(" CONNECT BY PRIOR B.C_TR_CODE = B.C_TR_CODE_P");
		}
		
		sql = "WITH MAIN_SQL AS (" + sql + " )"
				+ buf.toString();
		return sql;
	}
	
	/**
	 * Author : zhoushuhang
	 * Date   : 2018-03-13
	 * Status : Add
	 * Comment: STORY49928产品树形结构界面优化
	 * @description : 获取更新父子结构信息执行sql
	 */
	public String getExecuteSql(String type, String isParent, String trCode){
		StringBuilder buf = new StringBuilder();
		if(VocabularyConsts.JIA.equals(isParent)){
			if("DELETE".equals(type)){
				buf.append(" DELETE FROM T_P_AB_ASS_TR_SUB WHERE C_IDEN = ?");
			}else if("UPDATE".equals(type)){
				buf.append(" UPDATE T_P_AB_ASS_TR_SUB SET");
				buf.append(" C_TR_CODE = '" + trCode + "'");
				buf.append(" WHERE C_IDEN = ?");
			}
		}else if (VocabularyConsts.ZHEN.equals(isParent)){
			buf.append(" UPDATE T_P_AB_ASS_TR SET");
			buf.append(" C_TR_CODE_P = '" + trCode + "'");
			buf.append(" WHERE C_IDEN = ?");
		}
		
		return buf.toString();
	}
}
