package com.yss.ams.base.information.modules.bi.mkt.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * 交易市场sql处理类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class MktSqlBuilder implements SQLBuilder {

	/**
	 * 返回根据前台查询条件获取交易市场总记录数的sql
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" SELECT COUNT(*) AS CNT FROM T_P_BI_MKT A ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" WHERE ").append(valueFieldbuf);
			}
			buf.append("  ORDER BY A.N_CHECK_STATE, A.C_DV_MKT_TYPE  ");
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return retSql;
	}

	/**
	 * 返回根据前台查询条件获取交易市场所有记录的sql
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" SELECT A.C_DV_MKT_TYPE, A.C_MKT_CODE, A.C_MKT_NAME, ");
			buf.append(" A.C_MKT_NAME_EN, A.C_MKT_NAME_ST, A.C_SWIFT_CODE, A.C_DE_CODE, ");
			buf.append(" A.C_HDAY_CODE, A.C_AREA_CODE, A.N_SETT_DAYS, ");
			buf.append(" A.C_DESC, A.N_CHECK_STATE, A.C_UPDATE_BY, "); 
			buf.append(" A.C_UPDATE_TIME, A.C_CHECK_BY, A.C_FIX_CODE, ");
			buf.append(" C_MKT_NO, A.C_CHECK_TIME, A.C_IDEN, ");
			buf.append(" '' AS fParaentCode, '' AS N_LEVEL, '' AS NODECODE , ");
			buf.append(" '' AS NODENAME ,'' AS PARENTCODE,'' AS PARENTNAME , ");
			buf.append(" '' AS ORDERINDEX ,'' AS IMGINDEX FROM T_P_BI_MKT A ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" WHERE ").append(valueFieldbuf);
			}
			buf.append(" ORDER BY A.N_CHECK_STATE, A.C_DV_MKT_TYPE, A.C_IDEN ");
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
		return dbnameresolver.getColumnName(MktColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getLogTableName(MktTableName.userInfo);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(MktTableName.userInfo);
	}

	/**
	 * 返回根据前台查询条件获取交易市场所有记录的sql
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getMktExtendSql(List<String> paraNameList) throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" SELECT A.C_DV_MKT_TYPE, A.C_MKT_CODE, A.C_MKT_NAME, ");
			buf.append(" A.C_MKT_NAME_EN, A.C_MKT_NAME_ST, A.C_SWIFT_CODE, ");
			buf.append(" A.C_DE_CODE, A.C_HDAY_CODE, A.C_AREA_CODE, ");
			buf.append(" A.N_SETT_DAYS,A.C_DESC,A.N_CHECK_STATE, "); 
			buf.append(" A.C_UPDATE_BY,A.C_UPDATE_TIME,A.C_CHECK_BY,");
			buf.append(" A.C_FIX_CODE, A.C_MKT_NO, A.C_CHECK_TIME, ");
			buf.append(" A.C_IDEN , '' AS FPARAENTCODE,'' AS N_LEVEL, ");
			buf.append(" '' AS NODECODE ,'' AS NODENAME ,'' as PARENTCODE, ");
			buf.append(" '' AS PARENTNAME ,'' AS ORDERINDEX ,'' AS IMGINDEX ");
			buf.append(" FROM T_P_BI_MKT A ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" WHERE ").append(valueFieldbuf);
			}
			buf.append(" ORDER BY A.N_CHECK_STATE, A.C_DV_MKT_TYPE, A.C_IDEN ");
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return retSql;
	}

	/**
	 * 返回根据前台查询条件获取交易市场总记录数的sql
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getMktExtendSqlCount(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" SELECT COUNT(*) AS CNT FROM T_P_BI_MKT A ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" WHERE ").append(valueFieldbuf);
			}
			buf.append(" ORDER BY A.N_CHECK_STATE, A.C_DV_MKT_TYPE  ");
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return retSql;
	}
	
	/**
	 * 根据查询条件判断哪些字段需要作为查询条件
	 * @param valueFieldbuf
	 * @param paraNameList
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_MKT_CODE")) {
				valueFieldbuf.append(" A.C_MKT_CODE LIKE ? AND ");
			}else if (fieldedName.equals("C_MKT_NO")) {
				valueFieldbuf.append(" A.C_MKT_NO LIKE ? AND ");
			}else if (fieldedName.equals(("C_MKT_NAME"))) {
				valueFieldbuf.append("  A.C_MKT_NAME LIKE ? AND ");
			} else if (fieldedName.equals("C_AREA_CODE")) {
				valueFieldbuf.append(" C_AREA_CODE LIKE ?  AND ");
			} else if (fieldedName.equals("ARRAY_C_DV_MKT_TYPE")) {
				valueFieldbuf.append(" a.C_DV_MKT_TYPE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append("a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				valueFieldbuf.append("a.C_DEL_TIME <= ? AND ");
			} else if (fieldedName.equals("ARRAY_C_AREA_CODE")){
				valueFieldbuf.append("a.C_AREA_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}
	
	/**
	 * 获取交易市场所有列名信息
	 * @param dbNameResolver
	 * @param name
	 * @return
	 */
	public String getMktExtendColumnName(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(MktExtendColumnName.valueOf(name));
	}
	
	/* START 数据服务方法 */
	public String getAllDataSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getCommonQuerySqlBuf1(buf);
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 获取市场代码代码对应中文转换
	 * @return
	 */
	public String getDataName(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySql(buf);
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getAllDataSqlByKeys(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySql(buf);
		buf.append(" WHERE C_MKT_CODE IN (SELECT * FROM TABLE(?)) ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 根据多个交易市场代码获取交易市场信息
	 * @return
	 */
	public String getDataByCodes(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT * FROM ( ");
		getCommonQuerySqlBuf(buf);
		buf.append(" ) WHERE C_MKT_CODE  IN (SELECT * FROM TABLE(?)) ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 获取市场代码代码对应中文转换
	 * @param buf
	 */
	public void getCommonQuerySql(StringBuffer buf){
		buf.append(" SELECT C_MKT_CODE, C_MKT_NAME FROM T_S_MKT_VAR ");
	}
	
	public String getDataByCode(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT * FROM (");
		getCommonQuerySqlBuf(buf);
		buf.append(" ) WHERE C_MKT_CODE = ? ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getDataListByTypes(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT * FROM (");
		getCommonQuerySqlBuf(buf);
		buf.append(" ) WHERE C_DV_MKT_TYPE ");
		buf.append(" IN (SELECT * FROM TABLE(?)) ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	private void getCommonQuerySqlBuf(StringBuffer buf){
		buf.append(" select distinct * from (");
		
		buf.append(" SELECT level, c.* from (select '' as C_DV_MKT_TYPE, ");
		buf.append(" a.C_AREA_CODE_P as fParaentCode, ");
		buf.append(" a.C_AREA_CODE as c_mkt_code, ");
		buf.append(" a.C_AREA_name as c_mkt_name, ");
		
		buf.append(" '' as c_mkt_name_en, ");
		buf.append(" '' as C_MKT_NAME_ST, ");
		buf.append(" '' as C_SWIFT_CODE, ");
		buf.append(" '' as C_DE_CODE, ");
		buf.append(" '' as C_HDAY_CODE, ");
		buf.append(" '' as C_AREA_CODE, ");
		buf.append(" 0 as N_SETT_DAYS, ");
		buf.append(" '' as C_DESC, ");
		buf.append(" 1 as N_CHECK_STATE, ");
		buf.append(" '' as C_UPDATE_BY, ");
		buf.append(" '' as C_UPDATE_TIME, ");
		buf.append(" '' as C_CHECK_BY, ");
		buf.append(" '' as C_CHECK_TIME, ");
		buf.append(" '' as C_FIX_CODE, ");
		buf.append(" '' as C_IDEN, ");
		buf.append(" a.C_AREA_CODE as C_MKT_NO, "); 
		buf.append(" 0 as detail "); 
		buf.append(" from T_P_BI_AREA a  ");
		buf.append(" union all ");
		buf.append(" select b.C_DV_MKT_TYPE, ");
		buf.append(" b.C_AREA_CODE as fParaentCode, ");
		
		buf.append(" b.C_MKT_CODE, ");
		buf.append(" b.C_MKT_NAME, ");
		
		buf.append(" b.c_mkt_name_en, ");
		buf.append(" b.c_mkt_name_st, ");
		buf.append(" b.c_swift_code, ");
		buf.append(" b.C_DE_CODE, ");
		buf.append(" b.C_HDAY_CODE, ");
		buf.append(" b.C_AREA_CODE, ");
		buf.append(" b.N_SETT_DAYS, ");
		buf.append(" b.C_DESC, ");
		buf.append(" b.N_CHECK_STATE, ");
		buf.append(" b.C_UPDATE_BY, ");
		buf.append(" b.C_UPDATE_TIME, ");
		buf.append(" b.C_CHECK_BY, ");
		buf.append(" b.C_CHECK_TIME, ");
		buf.append(" b.C_FIX_CODE, "); 
		buf.append(" b.C_IDEN, "); 
		buf.append(" b.C_MKT_NO, "); 
		buf.append(" 1 as detail ");
		buf.append(" from T_P_BI_MKT b ");
		buf.append("  where b.N_CHECK_STATE = 1 ");
		buf.append(") c ");
		buf.append(" start with c.detail = 1 ");
		buf.append("  connect by nocycle  c.C_MKT_CODE = prior c.fParaentCode ");
		buf.append(") start with fParaentCode = '[root]' ");
		buf.append(" connect by nocycle prior C_MKT_CODE  =  fParaentCode");
	}
	
	/**
	 * 查询方法，解决审核与未审核数据中"所属市场"显示内容不一致的情况
	 */
	private void getCommonQuerySqlBuf1(StringBuffer buf){
		buf.append(" select distinct * from (");
		
		buf.append(" SELECT level, c.* from (select '' as C_DV_MKT_TYPE, ");
		buf.append(" a.C_AREA_CODE_P as fParaentCode, ");
		buf.append(" a.C_AREA_CODE as c_mkt_code, ");
		buf.append(" a.C_AREA_name as c_mkt_name, ");
		
		buf.append(" '' as c_mkt_name_en, ");
		buf.append(" '' as C_MKT_NAME_ST, ");
		buf.append(" '' as C_SWIFT_CODE, ");
		buf.append(" '' as C_DE_CODE, ");
		buf.append(" '' as C_HDAY_CODE, ");
		buf.append(" '' as C_AREA_CODE, ");
		buf.append(" 0 as N_SETT_DAYS, ");
		buf.append(" '' as C_DESC, ");
		buf.append(" 1 as N_CHECK_STATE, ");
		buf.append(" '' as C_UPDATE_BY, ");
		buf.append(" '' as C_UPDATE_TIME, ");
		buf.append(" '' as C_CHECK_BY, ");
		buf.append(" '' as C_CHECK_TIME, ");
		buf.append(" '' as C_FIX_CODE, ");
		buf.append(" '' as C_IDEN, ");
		buf.append(" a.C_AREA_CODE as C_MKT_NO, "); 
		buf.append(" 0 as detail "); 
		buf.append(" from T_P_BI_AREA a  ");
		buf.append("  where a.N_CHECK_STATE = 1 "); //add by weijj 20130802 BUG8905地区反审核后，不应在A区加载出 
		buf.append(" union all ");
		buf.append(" select b.C_DV_MKT_TYPE, ");
		buf.append(" b.C_AREA_CODE as fParaentCode, ");
		
		buf.append(" b.C_MKT_CODE, ");
		buf.append(" b.C_MKT_NAME, ");
		
		buf.append(" b.c_mkt_name_en, ");
		buf.append(" b.c_mkt_name_st, ");
		buf.append(" b.c_swift_code, ");
		buf.append(" b.C_DE_CODE, ");
		buf.append(" b.C_HDAY_CODE, ");
		buf.append(" b.C_AREA_CODE, ");
		buf.append(" b.N_SETT_DAYS, ");
		buf.append(" b.C_DESC, ");
		buf.append(" b.N_CHECK_STATE, ");
		buf.append(" b.C_UPDATE_BY, ");
		buf.append(" b.C_UPDATE_TIME, ");
		buf.append(" b.C_CHECK_BY, ");
		buf.append(" b.C_CHECK_TIME, ");		
        ////buf.append(" b.C_MKT_NO, "); // 字段的顺序有误byleeyu20130624
		buf.append(" b.C_FIX_CODE, "); 
		buf.append(" b.C_IDEN, "); 
		buf.append(" b.C_MKT_NO, "); // 字段的顺序有误byleeyu20130624
		buf.append(" 1 as detail ");
		buf.append(" from T_P_BI_MKT b ");
		//buf.append("  where b.N_CHECK_STATE = 1 ");
		buf.append(") c ");
		buf.append(" start with c.detail = 1 ");
		buf.append("  connect by nocycle  c.C_MKT_CODE = prior c.fParaentCode ");
		buf.append(") start with fParaentCode = '[root]' ");
		buf.append(" connect by nocycle prior C_MKT_CODE  =  fParaentCode");
	}

	/**
	 * 查询所有可用交易市场字典信息
	 * @return sql
	 */
	public String getAllMktSql(){
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" SELECT A.C_MKT_CODE, A.C_MKT_NAME, A.C_DV_MKT_TYPE FROM T_S_MKT_VAR A ");
		sqlBuff.append(" WHERE A.C_DV_STATE='ENAB' ");
		sqlBuff.append(" ORDER BY C_DV_MKT_TYPE, C_MKT_CODE");
		return sqlBuff.toString();
	}
	
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(MktTableName.userInfo);
	}

	public String getDataListByTimestamp() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonQuerySqlBuf2(buf);

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/* END 数据服务方法 */
	
	/**
	 * 缓存时间戳查询SQL
	 */
	private void getCommonQuerySqlBuf2(StringBuffer buf){
		buf.append(" select distinct * from (");
		
		buf.append(" SELECT level, c.* from (select '' as C_DV_MKT_TYPE, ");
		buf.append(" a.C_AREA_CODE_P as fParaentCode, ");
		buf.append(" a.C_AREA_CODE as c_mkt_code, ");
		buf.append(" a.C_AREA_name as c_mkt_name, ");
		
		buf.append(" '' as c_mkt_name_en, ");
		buf.append(" '' as C_MKT_NAME_ST, ");
		buf.append(" '' as C_SWIFT_CODE, ");
		buf.append(" '' as C_DE_CODE, ");
		buf.append(" '' as C_HDAY_CODE, ");
		buf.append(" '' as C_AREA_CODE, ");
		buf.append(" 0 as N_SETT_DAYS, ");
		buf.append(" '' as C_DESC, ");
		buf.append(" 1 as N_CHECK_STATE, ");
		buf.append(" '' as C_UPDATE_BY, ");
		buf.append(" '' as C_UPDATE_TIME, ");
		buf.append(" '' as C_CHECK_BY, ");
		buf.append(" '' as C_CHECK_TIME, ");
		buf.append(" '' as C_FIX_CODE, ");
		buf.append(" '' as C_IDEN, ");
		buf.append(" a.C_AREA_CODE as C_MKT_NO, "); 
		buf.append(" 0 as detail "); 
		buf.append(" from T_P_BI_AREA a  ");
		buf.append("  where a.N_CHECK_STATE = 1 and TO_DATE(a.C_CHECK_TIME,'yyyy-mm-dd hh24:mi:ss') >= to_date(?,'yyyy-mm-dd hh24:mi:ss')"); //add by weijj 20130802 BUG8905地区反审核后，不应在A区加载出 
		buf.append(" union all ");
		buf.append(" select b.C_DV_MKT_TYPE, ");
		buf.append(" b.C_AREA_CODE as fParaentCode, ");
		
		buf.append(" b.C_MKT_CODE, ");
		buf.append(" b.C_MKT_NAME, ");
		
		buf.append(" b.c_mkt_name_en, ");
		buf.append(" b.c_mkt_name_st, ");
		buf.append(" b.c_swift_code, ");
		buf.append(" b.C_DE_CODE, ");
		buf.append(" b.C_HDAY_CODE, ");
		buf.append(" b.C_AREA_CODE, ");
		buf.append(" b.N_SETT_DAYS, ");
		buf.append(" b.C_DESC, ");
		buf.append(" b.N_CHECK_STATE, ");
		buf.append(" b.C_UPDATE_BY, ");
		buf.append(" b.C_UPDATE_TIME, ");
		buf.append(" b.C_CHECK_BY, ");
		buf.append(" b.C_CHECK_TIME, ");		
        ////buf.append(" b.C_MKT_NO, "); // 字段的顺序有误byleeyu20130624
		buf.append(" b.C_FIX_CODE, "); 
		buf.append(" b.C_IDEN, "); 
		buf.append(" b.C_MKT_NO, "); // 字段的顺序有误byleeyu20130624
		buf.append(" 1 as detail ");
		buf.append(" from T_P_BI_MKT b ");
		//buf.append("  where b.N_CHECK_STATE = 1 ");
		buf.append(") c ");
		buf.append(" start with c.detail = 1 ");
		buf.append("  connect by nocycle  c.C_MKT_CODE = prior c.fParaentCode ");
		buf.append(") start with fParaentCode = '[root]' ");
		buf.append(" connect by nocycle prior C_MKT_CODE  =  fParaentCode");
	}
	
	//获取清算机构
	public String getAllAuxDataSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getCommonQuerySqlBufAux(buf);
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 查询方法，清算机构
	 */
	private void getCommonQuerySqlBufAux(StringBuffer buf){
//		buf.append(" SELECT '' AS C_DV_MKT_TYPE, ");
//		buf.append(" '[root]' AS fParaentCode, ");
//		buf.append(" t.c_dv_code AS c_mkt_code, ");
//		buf.append(" t.c_dv_name AS c_mkt_name, ");
//		buf.append(" '' AS c_mkt_name_en, ");
//		buf.append(" '' AS C_MKT_NAME_ST, ");
//		buf.append(" '' AS C_SWIFT_CODE,'' AS C_DE_CODE, ");
//		buf.append(" '' AS C_HDAY_CODE,'' AS C_AREA_CODE, ");
//		buf.append(" 0 AS N_SETT_DAYS,'' AS C_DESC, ");
//		buf.append(" 1 AS N_CHECK_STATE,'' AS C_UPDATE_BY, ");
//		buf.append(" '' AS C_UPDATE_TIME,'' AS C_CHECK_BY, ");
//		buf.append(" '' AS C_CHECK_TIME,'' AS C_FIX_CODE, ");
//		buf.append(" '' AS C_IDEN,t.c_dv_code AS C_MKT_NO,0 AS detail ");
//		buf.append(" FROM t_s_Dv_Voc t  where c_dv_type = 'ZQ_JSJG' ");
//		buf.append(" union all ");
		buf.append(" select * from (select distinct * from ( ");
		buf.append(" SELECT c.*  ");
		buf.append(" FROM ( ");
		buf.append(" SELECT '' AS C_DV_MKT_TYPE, ");
		buf.append(" a.C_AREA_CODE_P as fParaentCode, ");
		buf.append(" a.C_AREA_CODE as c_mkt_code, ");
		buf.append(" a.C_AREA_name as c_mkt_name, ");
		buf.append(" '' as c_mkt_name_en, ");
		buf.append(" '' as C_MKT_NAME_ST, ");
		buf.append(" '' as C_SWIFT_CODE, ");
		buf.append(" '' as C_DE_CODE, ");
		buf.append(" '' as C_HDAY_CODE, ");
		buf.append(" '' as C_AREA_CODE, ");
		buf.append(" 0 as N_SETT_DAYS, ");
		buf.append(" '' as C_DESC, ");
		buf.append(" 1 as N_CHECK_STATE, ");
		buf.append(" '' as C_UPDATE_BY, ");
		buf.append(" '' as C_UPDATE_TIME, ");
		buf.append(" '' as C_CHECK_BY, ");
		buf.append(" '' as C_CHECK_TIME, ");
		buf.append(" '' as C_FIX_CODE, ");
		buf.append(" '' as C_IDEN, ");
		buf.append(" a.C_AREA_CODE as C_MKT_NO, "); 
		buf.append(" 0 as detail "); 
		buf.append(" from T_P_BI_AREA a  ");
		buf.append("  where a.N_CHECK_STATE = 1 "); 
		buf.append(" union all ");
		buf.append(" select b.C_DV_MKT_TYPE, ");
		buf.append(" b.C_AREA_CODE   AS fParaentCode, ");
		buf.append(" b.C_MKT_CODE, ");
		buf.append(" b.C_MKT_NAME, ");
		buf.append(" b.c_mkt_name_en, ");
		buf.append(" b.c_mkt_name_st, ");
		buf.append(" b.c_swift_code, ");
		buf.append(" b.C_DE_CODE, ");
		buf.append(" b.C_HDAY_CODE, ");
		buf.append(" b.C_AREA_CODE, ");
		buf.append(" b.N_SETT_DAYS, ");
		buf.append(" b.C_DESC, ");
		buf.append(" b.N_CHECK_STATE, ");
		buf.append(" b.C_UPDATE_BY, ");
		buf.append(" b.C_UPDATE_TIME, ");
		buf.append(" b.C_CHECK_BY, ");
		buf.append(" b.C_CHECK_TIME, ");		
		buf.append(" b.C_FIX_CODE,b.C_MKT_NO,  "); 
		buf.append(" b.C_IDEN, "); 
		buf.append(" 1 as detail");
		buf.append(" from T_P_BI_MKT b ");
		buf.append(") c ");
		buf.append(" start with c.detail = 1 ");
		buf.append("  connect by nocycle  c.C_MKT_CODE = prior c.fParaentCode ");
		buf.append(") start with fParaentCode = '[root]' ");
		buf.append(" connect by nocycle prior C_MKT_CODE  =  fParaentCode ");
		buf.append(" order by c_mkt_code desc) ");
	}
}
