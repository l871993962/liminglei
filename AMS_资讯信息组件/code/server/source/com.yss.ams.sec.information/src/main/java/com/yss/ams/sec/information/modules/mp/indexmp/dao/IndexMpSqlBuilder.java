package com.yss.ams.sec.information.modules.mp.indexmp.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;
/**
 * 指数行情资料 SQL构造类
 * @author gongyue
 * 资讯信息拆分	2017.7.5 STORY #42948 资讯信息管理组件化拆分
 */
public class IndexMpSqlBuilder implements SQLBuilder{

	@Override
	public String buildInsertSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildSelectSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(IndexMpTableName.indexmp);
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(IndexMpColumnName.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(IndexMpTableName.recycle);
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(IndexMpTableName.indexmp);
	}

	/**
	 * 返回 根据前台查询条件获取指数行情资料的所有数据 的SQL
	 * @param paraNameList
	 * @return retSql
	 * @throws Exception
	 */
	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select a.* from T_D_MP_INDEX a ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}		
		//make 排序增加
		buf.append(" order by a.N_CHECK_STATE , a.C_IDEN ");
		retSql = buf.toString();

		return retSql;
	}

	/**
	 * 返回 根据前台查询条件获取指数行情资料数据 的总数量 的SQL
	 * @param paraNameList
	 * @return retSql
	 * @throws Exception
	 */
	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		buf.append(" select count(*) as CNT from T_D_MP_INDEX a ");
		this.setWhereSql(valueFieldbuf, paraNameList);
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		retSql = buf.toString();

		return retSql;
	}
	
	/**
	 * 整合前台条件:包括证券行情,场外行情,汇率行情,存款利率
	 * 根据前台传送过来参数判断添加筛选条件 的SQL
	 * @param valueFieldbuf
	 * @param paraNameList
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));

		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_MKT_CLS")) { // 行情分类 =
				valueFieldbuf.append(" a.C_MKT_CLS = ?  AND ");
			} else if (fieldedName.equals("ARRAY_C_MKT_CLS")) { // 行情分类 in
				valueFieldbuf
						.append(" a.C_MKT_CLS IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("ARRAY_C_MKT_CODE")) { // 交易市场 in
				valueFieldbuf
						.append(" b.C_MKT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("C_MKT_CODE")) { // 交易市场 =
				valueFieldbuf.append(" b.C_MKT_CODE  = ?  AND ");
			} else if (fieldedName.equals("D_BEGIN")) { // >=开始日期
				valueFieldbuf.append("a.D_MKT >= TO_DATE(?,'yyyy-MM-dd') AND ");
			} else if (fieldedName.equals("D_END")) { // <= 结束日期
				valueFieldbuf.append("a.D_MKT <= TO_DATE(?,'yyyy-MM-dd') AND ");
			} else if (fieldedName.equals("C_SEC_CODE")) { // 交易证券=
				valueFieldbuf.append(" a.C_SEC_CODE = ? AND ");
			} else if (fieldedName.equals("ARRAY_C_SEC_CODE")) { // 交易证券 in
					valueFieldbuf.append(" a.C_SEC_CODE IN (SELECT * FROM TABLE(?)) AND ");//交易证券in
			} else if (fieldedName.equals("C_DV_MKT_TYPE")) { // 市场类型
				valueFieldbuf.append(" a.C_DV_MKT_TYPE = ?  AND ");
			} else if (fieldedName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append("a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				valueFieldbuf.append("a.C_DEL_TIME <= ? AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

}
