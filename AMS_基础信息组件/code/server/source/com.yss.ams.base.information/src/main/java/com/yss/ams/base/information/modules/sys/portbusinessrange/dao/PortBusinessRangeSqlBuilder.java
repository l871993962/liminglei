/**
 *
 * @Title: PortBusinessSqlBuilder.java 
 * @Package com.yss.ams.base.information.modules.sys.portbusinessrela.dao 
 * @date 2019年5月13日 下午5:43:23 
 * @version V1.0
 * @Stroy/Bug
 * @author xiadeqi   
 */
package com.yss.ams.base.information.modules.sys.portbusinessrange.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/** 
 * 产品业务范围 sqlbuilder
 * @ClassName: PortBusinessSqlBuilder 
 * @date 2019年5月13日 下午5:43:23
 * @Stroy72335/Bug
 * @author xiadeqi 
 */
public class PortBusinessRangeSqlBuilder implements SQLBuilder{

	/* (非 Javadoc) 
	 * <p>Title: buildInsertSql</p> 
	 * <p>Description: </p> 
	 * @param dbNameResolver
	 * @return
	 * @deprecated 
	 * @see com.yss.framework.api.mvc.dao.sql.SQLBuilder#buildInsertSql(com.yss.framework.api.mvc.dao.sql.DBNameResolver) 
	 * @Stroy/Bug
	 * @author xiadeqi
	 */
	@Override
	public String buildInsertSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc) 
	 * <p>Title: buildUpdateSql</p> 
	 * <p>Description: </p> 
	 * @param dbNameResolver
	 * @return
	 * @deprecated 
	 * @see com.yss.framework.api.mvc.dao.sql.SQLBuilder#buildUpdateSql(com.yss.framework.api.mvc.dao.sql.DBNameResolver) 
	 * @Stroy/Bug
	 * @author xiadeqi
	 */
	@Override
	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc) 
	 * <p>Title: buildDeleteSql</p> 
	 * <p>Description: </p> 
	 * @param dbNameResolver
	 * @return
	 * @deprecated 
	 * @see com.yss.framework.api.mvc.dao.sql.SQLBuilder#buildDeleteSql(com.yss.framework.api.mvc.dao.sql.DBNameResolver) 
	 * @Stroy/Bug
	 * @author xiadeqi
	 */
	@Override
	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc) 
	 * <p>Title: buildSelectSql</p> 
	 * <p>Description: </p> 
	 * @param dbNameResolver
	 * @return
	 * @deprecated 
	 * @see com.yss.framework.api.mvc.dao.sql.SQLBuilder#buildSelectSql(com.yss.framework.api.mvc.dao.sql.DBNameResolver) 
	 * @Stroy/Bug
	 * @author xiadeqi
	 */
	@Override
	public String buildSelectSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (非 Javadoc) 
	 * <p>Title: getTableName</p> 
	 * <p>Description: </p> 
	 * @param dbNameResolver
	 * @return 
	 * @see com.yss.framework.api.mvc.dao.sql.SQLBuilder#getTableName(com.yss.framework.api.mvc.dao.sql.DBNameResolver) 
	 * @Stroy/Bug
	 * @author xiadeqi
	 */
	@Override
	public String getTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(PortBusinessRelaTableName.userInfo);
	}

	/* (非 Javadoc) 
	 * <p>Title: getColumnNameByProperty</p> 
	 * <p>Description: </p> 
	 * @param dbNameResolver
	 * @param name
	 * @return 
	 * @see com.yss.framework.api.mvc.dao.sql.SQLBuilder#getColumnNameByProperty(com.yss.framework.api.mvc.dao.sql.DBNameResolver, java.lang.String) 
	 * @Stroy/Bug
	 * @author xiadeqi
	 */
	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(PortBusinessRangeColumnName.valueOf(name));
	}

	/* (非 Javadoc) 
	 * <p>Title: getRecycleTableName</p> 
	 * <p>Description: </p> 
	 * @param dbNameResolver
	 * @return 
	 * @see com.yss.framework.api.mvc.dao.sql.SQLBuilder#getRecycleTableName(com.yss.framework.api.mvc.dao.sql.DBNameResolver) 
	 * @Stroy/Bug
	 * @author xiadeqi
	 */
	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(PortBusinessRelaTableName.recycle);
	}

	/* (非 Javadoc) 
	 * <p>Title: getLogSequenceName</p> 
	 * <p>Description: </p> 
	 * @param dbNameResolver
	 * @return 
	 * @see com.yss.framework.api.mvc.dao.sql.SQLBuilder#getLogSequenceName(com.yss.framework.api.mvc.dao.sql.DBNameResolver) 
	 * @Stroy/Bug
	 * @author xiadeqi
	 */
	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(PortBusinessRelaTableName.userInfo);
	}

	/* (非 Javadoc) 
	 * <p>Title: getQueryConditionSql</p> 
	 * <p>Description: </p> 
	 * @param paraNameList
	 * @return
	 * @throws Exception 
	 * @see com.yss.framework.api.mvc.dao.sql.SQLBuilder#getQueryConditionSql(java.util.List) 
	 * @Stroy/Bug
	 * @author xiadeqi
	 */
	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		StringBuffer sqlBuff = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		sqlBuff.append(" select A.* ");
		sqlBuff.append(" from ");
		sqlBuff.append(PortBusinessRelaTableName.userInfo + " A ");
		if (valueFieldbuf.length() > 0) {
			sqlBuff.append(" where ").append(valueFieldbuf);
		}
		sqlBuff.append(" order by A.C_BUSINESS_CODE desc");
		return sqlBuff.toString();
	}

	/* (非 Javadoc) 
	 * <p>Title: getQueryConditionCountSql</p> 
	 * <p>Description: </p> 
	 * @param paraNameList
	 * @return
	 * @throws Exception 
	 * @see com.yss.framework.api.mvc.dao.sql.SQLBuilder#getQueryConditionCountSql(java.util.List) 
	 * @Stroy/Bug
	 * @author xiadeqi
	 */
	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer sqlBuff = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		sqlBuff.append(" select count(*) ");
		sqlBuff.append(" from ");
		sqlBuff.append(PortBusinessRelaTableName.userInfo + " A ");
		if (valueFieldbuf.length() > 0) {
			sqlBuff.append(" where ").append(valueFieldbuf);
		}
		return sqlBuff.toString();
	}
	
	/**
	 * 产品业务范围
	 * 根据前台Frm_PortBusinessRange_L.cs 拿到的过滤参数拼接 where 子句 
	 * @Title: setWhereSql 
	 * @param @param valueFieldbuf
	 * @param @param paraNameList
	 * @return void    
	 * @throws 
	 * @Stroy72335/Bug
	 * @author xiadeqi
	 */
	private void setWhereSql(StringBuffer valueFieldbuf, List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));
		
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("ARRAY_C_BUSINESS_CODE")) {
				valueFieldbuf.append(" A.C_BUSINESS_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}else if(fieldedName.equals("ARRAY_C_PORT_CODE")){
				valueFieldbuf.append(" A.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
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

	/**
	 * 
	 *根据组合代码查询业务类型code
	 * @Title: queryRelValueByCodeSql 
	 * @param @return
	 * @return String    
	 * @throws 
	 * @Stroy73411/Bug
	 * @author xiadeqi
	 */
	public String queryProductBusiCodeByProductCode(){
		return " SELECT A.C_BUSINESS_CODE FROM T_P_AB_BUSINESS_RANGE A WHERE A.C_PORT_CODE = ? ";
	}
	
	/**
	 * BUG #288785 【华宝基金】自动化流程中根据产品业务范围判断审核才走
	 * @return
	 */
	public String queryCheckStatusByPortCodeAndBusiCode() {
		return " SELECT COUNT(1) CNUM FROM T_P_AB_BUSINESS_RANGE A WHERE A.C_PORT_CODE = ? AND A.C_BUSINESS_CODE = ? AND A.N_CHECK_STATE = 1 ";
	}
	
	/**
	 * STORY #82160 【华宝基金】产品业务范围增加维护界面
	 * @return
	 */
	public String getDataListByTypeSql() {
		return " SELECT A.C_BUSINESS_CODE, A.C_BUSINESS_NAME FROM T_P_AB_BUSINESS_TYPE A WHERE A.C_BUSINESS_TYPE = ? ORDER BY A.N_ORDER ";
	}
	
	/**
	 * STORY #82160 【华宝基金】产品业务范围增加维护界面
	 * @return
	 */
	public String getDeleteDataByTypeSql() {
		return " DELETE FROM T_P_AB_BUSINESS_TYPE A WHERE A.C_BUSINESS_TYPE = ? ";
	}
	
	/**
	 * STORY #82160 【华宝基金】产品业务范围增加维护界面
	 * @return
	 */
	public String getInsertDataListSql() {
		return " INSERT INTO T_P_AB_BUSINESS_TYPE (C_BUSINESS_CODE,C_BUSINESS_NAME,C_BUSINESS_TYPE,N_ORDER,C_UPDATE_BY,C_UPDATE_TIME) VALUES (?,?,?,?,?,TO_CHAR(SYSDATE,'YYYYMMDD HH24:MI:SS')) ";
	}
	
	/**
	 * STORY #86378 【华宝基金】二期自动化应用范围增加其他自动化组合
	 * @return
	 */
	public String getPortListByBusiCodeSql() {
		return " SELECT A.C_PORT_CODE FROM T_P_AB_BUSINESS_RANGE A WHERE A.C_BUSINESS_CODE = ? AND A.N_CHECK_STATE = 1 ";
	}
	
	/**
	 * STORY #96878 【富国基金】自动化参数复制通过产品参数复制
	 * @return
	 */
	public String getInsertPortBusinessRangeSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" INSERT INTO T_P_AB_BUSINESS_RANGE (C_IDEN, C_PORT_CODE, C_BUSINESS_CODE, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) ");
		buf.append(" SELECT SEQU_P_AB_BUSINESS_RANGE.NEXTVAL, ?, ?, ?, ?, ?, ?, ? FROM DUAL ");
		buf.append(" WHERE NOT EXISTS (SELECT 1 FROM T_P_AB_BUSINESS_RANGE WHERE C_PORT_CODE = ? AND C_BUSINESS_CODE = ? AND N_CHECK_STATE = ?) ");
		return buf.toString();
	}
}
