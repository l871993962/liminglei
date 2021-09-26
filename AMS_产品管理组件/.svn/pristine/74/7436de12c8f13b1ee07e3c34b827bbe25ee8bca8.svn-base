package com.yss.ams.product.information.modules.pg.portgrouprela.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * <产品群组管理>SQL语句构造类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortGroupRelaSqlBuilder implements SQLBuilder {

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
		return dbNameResolver.getTableName(PortGroupRelaTableName.clIndex);
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(PortGroupRelaColumnName.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogTableName(PortGroupRelaTableName.clIndex);
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
//		StringBuffer buf = new StringBuffer();
//	    buf.append("select a.* from T_D_BI_GROUP a  ");
//	    buf.append("  where a.N_CHECK_STATE >= 0 start with a.C_GROUP_CODE_P ='[root]' ");
//	    buf.append(" connect by prior a.C_GROUP_CODE = a.C_GROUP_CODE_P order by a.N_CHECK_STATE ASC  ");

		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append("  SELECT A.C_IDEN, A.C_GROUP_CODE,P.C_GROUP_NAME ,A.C_PORT_CODE, T.C_PORT_NAME,");
		buf.append("  A.C_UPDATE_BY,A.C_UPDATE_TIME,A.C_CHECK_BY,A.C_CHECK_TIME,A.N_CHECK_STATE  ");
		buf.append("  FROM T_P_AB_GROUP_RELA A  ");
		buf.append("  LEFT JOIN T_P_AB_GROUP P ON A.C_GROUP_CODE = P.C_GROUP_CODE  ");
		buf.append("  LEFT JOIN T_P_AB_PORT T ON A.C_PORT_CODE = T.C_PORT_CODE  ");
		
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append("  order by a.n_check_state,a.c_group_code  ");
	    return buf.toString();
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);

			if (valueFieldbuf.length() > 0) {
				StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
			}

			buf.append(" select count(*) AS CNT ");
			buf.append(" FROM T_P_AB_GROUP_RELA A ");
			buf.append(" LEFT JOIN T_P_AB_GROUP P ON A.C_GROUP_CODE = P.C_GROUP_CODE ");

			if (valueFieldbuf.length() > 0) {
				buf.append(" WHERE ").append(valueFieldbuf);
			}

			buf.append("  order by a.n_check_state,a.c_group_code   ");
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
		}
		return retSql;
	}
	
	
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));


		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_GROUP_CODE")) {
				valueFieldbuf.append(" A.C_GROUP_CODE = ?  AND ");
			}else if(fieldedName.equals("ARRAY_C_GROUP_CODE")){
				valueFieldbuf.append(" A.C_GROUP_CODE IN (SELECT * FROM TABLE(?))  AND ");
			}else if (fieldedName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append(" a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
					valueFieldbuf.append(" a.C_DEL_TIME <= ? AND ");
			}else if(fieldedName.equals("ARRAY_C_PORT_CODE")) {
				valueFieldbuf.append(" A.C_PORT_CODE IN (SELECT * FROM TABLE(?))  AND ");
				//edit by gh 2016-9-27 STORY33240【南方基金】复制建仓继承内容增加群组功能的勾选选项
			} else if (fieldedName.equals("C_PORT_CODE")) {
					valueFieldbuf.append("a.C_PORT_CODE = ? AND ");					
			}
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String getDeleteByPortCodesSql() {
		return "DELETE FROM T_P_AB_GROUP_RELA WHERE C_PORT_CODE IN (SELECT * FROM TABLE(?))";
	}

}
