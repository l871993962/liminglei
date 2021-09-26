package com.yss.uco.elecreco.er.reverse.manager.result.dao;
import java.util.ArrayList;
import java.util.List;

import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;
public class ReveResultSqlBuilder implements SQLBuilder  {

	//private boolean isPage = false;
	
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

	public String getColumnNameByProperty(DBNameResolver dbnameresolver,String s) {
		return dbnameresolver.getColumnName(ReveResultColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(ReveResultTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(ReveResultTableName.table);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(ReveResultTableName.table);
	}

	public String getQueryConditionCountSql(List<String> paraNameList,PageInation page) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		//主表添加where条件
		String mainSql = getMainTable(valueFieldbuf);
		//mainSql = buildPagingSql(mainSql, page);
		
		buf.append(" select count(1) from ");
		buf.append(" ( ");
		buf.append(" select * ");
		//buf.append(selectItem());
		buf.append(" from ( ").append(mainSql).append(" ) A ");
		//buf.append(" left join T_D_ER_REVE_RESRELA R ");
		//buf.append(" on A.C_IDEN = R.C_RESULT_RELA ");
		buf.append(" ) A ");
		//buf.append(" ORDER BY A.C_KM_CODE_RELAFB DESC  ");
		return buf.toString();
	}
	
	protected String buildPagingSql(String strSql, PageInation page)
			throws Exception {
		StringBuffer sPageSql = new StringBuffer();
		if(page == null)
		{
			return strSql;
		}
		int begin = 0; // 起始行
		int end = 100; // 结束行
		int pageSize = 0; // 分页显示的行数
		// 根据当前页计算出要查询的起始行数和结束行数
		// 获取每页数据的大小
		pageSize = page.getPageSize();
		begin = (page.getCurrPage() - 1) * pageSize + 1;
		end = page.getCurrPage() * pageSize;
		try {
			sPageSql
					.append("  select a3.* from (select a2.* from  (select  a1.*, rownum rn1 from (");
			sPageSql.append(strSql).append(") a1");
			sPageSql.append(" where rownum < =").append(end);
			sPageSql.append(") a2").append(" ) a3 where rn1 >= ").append(begin);
		} catch (Exception ex) {
			throw new Exception("拼接分页查询语句出现异常");
		}
		return sPageSql.toString();
	}
	
	public String selectItem()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(" A.*, ");
		sb.append(" R.C_IDEN as C_IDEN_RELAFB, ");
		sb.append(" R.C_RESULT_RELA as C_RESULT_RELA_RELAFB, ");
		sb.append(" R.C_KM_CODE as C_KM_CODE_RELAFB,  ");
		sb.append(" R.C_KM_NAME as C_KM_NAME_RELAFB, ");
		sb.append(" R.C_TGH_CODE as C_TGH_CODE_RELAFB, ");
		sb.append(" R.C_DV_KM_SCOPE as C_DV_KM_SCOPE_RELAFB, ");
		sb.append(" R.N_JE1 as N_JE1_RELAFB, ");
		sb.append(" R.N_JE2 as N_JE2_RELAFB, ");
		sb.append(" R.N_JE3 as N_JE3_RELAFB, ");
		sb.append(" R.N_JE4 as N_JE4_RELAFB, ");
		sb.append(" R.N_JE5 as N_JE5_RELAFB, ");
		sb.append(" R.N_JE6 as N_JE6_RELAFB, ");
		sb.append(" R.N_JE7 as N_JE7_RELAFB, ");
		sb.append(" R.N_JE8 as N_JE8_RELAFB, ");
		sb.append(" R.N_JE9 as N_JE9_RELAFB, ");
		sb.append(" R.N_JE10 as N_JE10_RELAFB, ");
		sb.append(" R.N_JE11 as N_JE11_RELAFB, ");
		sb.append(" R.N_JE12 as N_JE12_RELAFB, ");
		sb.append(" R.N_JE13 as N_JE13_RELAFB, ");
		sb.append(" R.N_JE14 as N_JE14_RELAFB, ");
		sb.append(" R.N_SL1 as N_SL1_RELAFB, ");
		sb.append(" R.C_BY1 as C_BY1_RELAFB, ");
		sb.append(" R.C_IGNORE_FLAG as C_IGNORE_FLAG_RELAFB ");
		return sb.toString();
	}
	/**
	 * 主表
	 * T_D_ER_REVE_RESULT
	 * @param valueFieldbuf 
	 * @return
	 */
	public String getMainTable(StringBuffer valueFieldbuf)
	{
		StringBuffer sb = new StringBuffer();
		//科目映射多对一，查询出错
		sb.append(" SELECT A.* FROM T_D_ER_REVE_RESULT A ");
		if (valueFieldbuf.length() > 0) {
			sb.append(" WHERE ").append(valueFieldbuf);
		}
//		sb.append(" order by KM_ORDER ");
		return sb.toString();
	}
	

	public String getQueryConditionSql(List<String> paraNameList,PageInation page) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		//主表添加where条件
		String mainSql = getMainTable(valueFieldbuf);
		mainSql = buildPagingSql(mainSql, page);
		
		buf.append(" select A.* from ");
		buf.append(" ( ");
		buf.append(" select ");
		buf.append(selectItem());
		buf.append(" from ( ").append(mainSql).append(" ) A ");
		buf.append(" left join T_D_ER_REVE_RESRELA R ");
		buf.append(" on A.C_IDEN = R.C_RESULT_RELA ");
		buf.append(" ) A ");
		buf.append(" ORDER BY A.C_DV_KM_SCOPE_RELAFB, A.C_KM_CODE_RELAFB  ");
		return buf.toString();
	}

	private void setWhereSql(StringBuffer valueFieldbuf,List<String> paraNameList) {
		//不需要审核机制
		//valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));
		SqlUtil.getCheckStateClause(paraNameList, "A");
		for (String fieldedName : paraNameList) {
			if (fieldedName.equalsIgnoreCase("ARRAY_C_PORT_CODE")) {
				valueFieldbuf.append(" A.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equalsIgnoreCase("C_SN")) {//
				valueFieldbuf.append(" A.C_SN = ? AND ");
			}else if (fieldedName.equalsIgnoreCase("C_FILE_TYPE")) {//
				valueFieldbuf.append(" A.C_FILE_TYPE = ? AND ");
			}else if (fieldedName.equalsIgnoreCase("C_RPT_TYPE")) {//
				valueFieldbuf.append(" A.C_RPT_TYPE = ? AND ");
			}else if (fieldedName.equalsIgnoreCase("D_START_DATE")) {//
				valueFieldbuf.append(" A.D_START_DATE <= to_date(?,'yyyy-MM-dd') AND ");
			}else if (fieldedName.equalsIgnoreCase("D_END_DATE")) {//
				valueFieldbuf.append(" A.D_END_DATE <= to_date(?,'yyyy-MM-dd') AND ");
			}
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}