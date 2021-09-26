package com.yss.uco.elecreco.bi.elecrela.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

public class ElecPerRelaSqlBuilder implements SQLBuilder {

	@Override
	public String buildInsertSql(DBNameResolver dbNameResolver) {
		return null;
	}

	@Override
	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		return null;
	}

	@Override
	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		return null;
	}

	@Override
	public String buildSelectSql(DBNameResolver dbNameResolver) {
		return null;
	}

	@Override
	public String getTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(ElecPerRelaTableName.elecPerRela);
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(ElecPerRelaColumnName.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(ElecPerRelaTableName.recycle);
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(ElecPerRelaTableName.elecPerRela);
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select A.* from T_Z_BI_PER_RELA A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
//		buf.append(" ORDER BY A.N_CHECK_STATE,A.C_ZB_CODE,A.C_UPDATE_TIME DESC  ");
		return buf.toString();
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select COUNT(*) AS CNT from T_Z_BI_PER_RELA A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}

		return buf.toString();
	}
	
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));

		for (String fieldedName : paraNameList) {
			if (fieldedName.equalsIgnoreCase("ARRAY_C_DZ_CODE")) {
				valueFieldbuf.append(" A.C_DZ_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equalsIgnoreCase("C_ZB_CODE")) {
				valueFieldbuf.append(" A.C_ZB_CODE LIKE ? AND ");
			}else if(fieldedName.equalsIgnoreCase("C_ZB_NAME")){
				valueFieldbuf.append(" A.C_ZB_NAME LIKE ? AND ");
			}else if (fieldedName.equalsIgnoreCase("C_DEL_TIME_START")) {
				valueFieldbuf.append(" A.C_DEL_TIME >= ? AND ");
			}else if(fieldedName.equalsIgnoreCase("C_DEL_TIME_END")){
				valueFieldbuf.append(" A.C_DEL_TIME <= ? AND ");
			}else if (fieldedName.equalsIgnoreCase("ARRAY_C_PORT_CODE")) {
				valueFieldbuf.append(" (A.C_PORT_CODE IN (SELECT * FROM TABLE(?)) OR A.C_PORT_CODE IS NULL) AND ");
			}
			
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}
	
	public String getPerRelaSqlByPara(){
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT NVL(S.C_ZB_CODE, R.C_ZB_CODE),NVL(S.C_ZB_NAME, R.C_ZB_NAME),              ");
		sb.append("        NVL(S.C_DZ_CODE, R.C_DZ_CODE),NVL(S.C_SEND_MODE, R.C_SEND_MODE),          ");
		sb.append("        NVL(S.C_ZB_VALUE1, R.C_ZB_VALUE1),NVL(S.C_ZB_VALUE2, R.C_ZB_VALUE2),      ");
		sb.append("        NVL(S.C_ZB_VALUE3, R.C_ZB_VALUE3),NVL(S.C_ZB_VALUE4, R.C_ZB_VALUE4),      ");
		sb.append("        NVL(S.C_ZB_VALUE5, R.C_ZB_VALUE5),NVL(S.C_ZB_VALUE6, R.C_ZB_VALUE6),      ");
		sb.append("        NVL(S.C_ZB_VALUE7, R.C_ZB_VALUE7),NVL(S.C_ZB_VALUE8, R.C_ZB_VALUE8),      ");
		sb.append("        NVL(S.C_ZB_VALUE9, R.C_ZB_VALUE9) FROM (SELECT C_ZB_CODE,C_ZB_NAME,C_DZ_CODE,C_SEND_MODE,C_ZB_ELEM1,C_ZB_VALUE1,C_ZB_ELEM2,C_ZB_VALUE2,C_ZB_ELEM3,C_ZB_VALUE3, ");
		sb.append("   C_ZB_ELEM4,C_ZB_VALUE4,C_ZB_ELEM5,C_ZB_VALUE5,C_ZB_ELEM6,C_ZB_VALUE6,C_ZB_ELEM7,C_ZB_VALUE7,C_ZB_ELEM8,C_ZB_VALUE8,C_ZB_ELEM9,C_ZB_VALUE9  ");
		sb.append("   FROM t_z_bi_per_rela  ");
		sb.append("        WHERE N_CHECK_STATE = '1'  AND C_PORT_CODE IS NULL AND C_ZB_CODE = ? AND C_ZB_NAME = ? AND C_DZ_CODE = ?) R,");
		sb.append(" (SELECT C_ZB_CODE,C_ZB_NAME,C_DZ_CODE,C_SEND_MODE,C_ZB_ELEM1,C_ZB_VALUE1,C_ZB_ELEM2,C_ZB_VALUE2,C_ZB_ELEM3,   ");
		sb.append(" C_ZB_VALUE3,C_ZB_ELEM4,C_ZB_VALUE4,C_ZB_ELEM5,C_ZB_VALUE5,C_ZB_ELEM6,C_ZB_VALUE6,C_ZB_ELEM7,C_ZB_VALUE7,C_ZB_ELEM8,C_ZB_VALUE8,C_ZB_ELEM9,C_ZB_VALUE9 ");
		sb.append(" FROM (SELECT C_PORT_CODE,C_ZB_CODE,C_ZB_NAME,C_DZ_CODE,C_SEND_MODE,C_ZB_ELEM1,C_ZB_VALUE1,C_ZB_ELEM2,C_ZB_VALUE2,  ");
		sb.append(" C_ZB_ELEM3,C_ZB_VALUE3,C_ZB_ELEM4,C_ZB_VALUE4,C_ZB_ELEM5,C_ZB_VALUE5,C_ZB_ELEM6,C_ZB_VALUE6,C_ZB_ELEM7,C_ZB_VALUE7,C_ZB_ELEM8,C_ZB_VALUE8,C_ZB_ELEM9,C_ZB_VALUE9 ");
		sb.append(" FROM t_z_bi_per_rela  WHERE N_CHECK_STATE = '1'  ");
		sb.append("  AND C_PORT_CODE IS NOT NULL) M join (SELECT C_PORT_CODE FROM T_P_AB_PORT                  ");
		sb.append("  WHERE N_CHECK_STATE = '1') n ON M.C_PORT_CODE = N.C_PORT_CODE WHERE C_ZB_CODE = ? AND C_ZB_NAME = ? AND C_DZ_CODE = ?) S  ");
//		sb.append("   ON R.C_ZB_CODE = S.C_ZB_CODE  AND R.C_ZB_NAME = S.C_ZB_NAME AND R.C_DZ_CODE = S.C_DZ_CODE     ");
		return sb.toString();
	}
	
	public String getPerRelaByCode(){
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT NVL(S.C_ZB_CODE, R.C_ZB_CODE),NVL(S.C_ZB_NAME, R.C_ZB_NAME),              ");
		sb.append("        NVL(S.C_DZ_CODE, R.C_DZ_CODE),NVL(S.C_SEND_MODE, R.C_SEND_MODE),          ");
		sb.append("        NVL(S.C_ZB_VALUE1, R.C_ZB_VALUE1),NVL(S.C_ZB_VALUE2, R.C_ZB_VALUE2),      ");
		sb.append("        NVL(S.C_ZB_VALUE3, R.C_ZB_VALUE3),NVL(S.C_ZB_VALUE4, R.C_ZB_VALUE4),      ");
		sb.append("        NVL(S.C_ZB_VALUE5, R.C_ZB_VALUE5),NVL(S.C_ZB_VALUE6, R.C_ZB_VALUE6),      ");
		sb.append("        NVL(S.C_ZB_VALUE7, R.C_ZB_VALUE7),NVL(S.C_ZB_VALUE8, R.C_ZB_VALUE8),      ");
		sb.append("        NVL(S.C_ZB_VALUE9, R.C_ZB_VALUE9) FROM (SELECT C_ZB_CODE,C_ZB_NAME,C_DZ_CODE,C_SEND_MODE,C_ZB_ELEM1,C_ZB_VALUE1,C_ZB_ELEM2,C_ZB_VALUE2,C_ZB_ELEM3,C_ZB_VALUE3, ");
		sb.append("   C_ZB_ELEM4,C_ZB_VALUE4,C_ZB_ELEM5,C_ZB_VALUE5,C_ZB_ELEM6,C_ZB_VALUE6,C_ZB_ELEM7,C_ZB_VALUE7,C_ZB_ELEM8,C_ZB_VALUE8,C_ZB_ELEM9,C_ZB_VALUE9  ");
		sb.append("   FROM t_z_bi_per_rela  ");
		sb.append("        WHERE N_CHECK_STATE = '1'  AND C_PORT_CODE IS NULL AND C_ZB_CODE = ? ) R, ");
		sb.append(" (SELECT C_ZB_CODE,C_ZB_NAME,C_DZ_CODE,C_SEND_MODE,C_ZB_ELEM1,C_ZB_VALUE1,C_ZB_ELEM2,C_ZB_VALUE2,C_ZB_ELEM3,   ");
		sb.append(" C_ZB_VALUE3,C_ZB_ELEM4,C_ZB_VALUE4,C_ZB_ELEM5,C_ZB_VALUE5,C_ZB_ELEM6,C_ZB_VALUE6,C_ZB_ELEM7,C_ZB_VALUE7,C_ZB_ELEM8,C_ZB_VALUE8,C_ZB_ELEM9,C_ZB_VALUE9 ");
		sb.append(" FROM (SELECT C_PORT_CODE,C_ZB_CODE,C_ZB_NAME,C_DZ_CODE,C_SEND_MODE,C_ZB_ELEM1,C_ZB_VALUE1,C_ZB_ELEM2,C_ZB_VALUE2,  ");
		sb.append(" C_ZB_ELEM3,C_ZB_VALUE3,C_ZB_ELEM4,C_ZB_VALUE4,C_ZB_ELEM5,C_ZB_VALUE5,C_ZB_ELEM6,C_ZB_VALUE6,C_ZB_ELEM7,C_ZB_VALUE7,C_ZB_ELEM8,C_ZB_VALUE8,C_ZB_ELEM9,C_ZB_VALUE9 ");
		sb.append(" FROM t_z_bi_per_rela  WHERE N_CHECK_STATE = '1'  ");
		sb.append("  AND C_PORT_CODE IS NOT NULL) M join (SELECT C_PORT_CODE FROM T_P_AB_PORT                  ");
		sb.append("  WHERE N_CHECK_STATE = '1') n ON M.C_PORT_CODE = N.C_PORT_CODE WHERE C_ZB_CODE = ? ) S  ");
//		sb.append("   ON R.C_ZB_CODE = S.C_ZB_CODE  AND R.C_ZB_NAME = S.C_ZB_NAME AND R.C_DZ_CODE = S.C_DZ_CODE     ");
		return sb.toString();
	}

	/**
	 * sql�����������
	 * ����1����ϴ���
	 * ����2����������
	 * @return
	 */
	public String getPerRelaByPortAndDZCodeSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" select A.*, ");
		/**
		 * ������ȼ�������ϵ����ȼ��ߣ����ں��档
		 */
		buf.append(" case when trim(A.C_PORT_CODE) is null then 1 else 2 end as N_LEVEL ");
		buf.append(" from T_Z_BI_PER_RELA A ");
		buf.append(" where ( A.C_PORT_CODE = ? OR A.C_PORT_CODE IS NULL) ");
		buf.append(" AND A.C_DZ_CODE = ? ");
		buf.append(" AND A.N_CHECK_STATE >= 1 ");
		buf.append(" order by N_LEVEL asc ");
		return buf.toString();
	}
	
	/**
	 * STORY #95888 【招商基金】【0331】【公募】新基金成立自动复制''对账指标关联'指标
	 * add by zhanghubin 20210315
	 * 产品参数复制功能优化，需考虑重复复制的情况
	 * @param dbnameresolver
	 * @return
	 */
	public String getdeleteBeforeCopySql(DBNameResolver dbnameresolver) {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" DELETE FROM  ");
		buf.append(getTableName(dbnameresolver));
		buf.append(" WHERE C_PORT_CODE = ? ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
}
