package com.yss.ams.base.information.modules.sys.feeRelation.dao;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

/**
 * @classDesc 费用关联sql处理
 * 20150105
 * @author chenchangyou
 */
/**
 * @author yuankai 公共信息拆分 2017.5.31
 */
public class FeeRelationExpandSqlBuilder implements SQLBuilder {
	
	/**
	 * 获取费用关联字典列名
	 * @param
	 */
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver
				.getColumnName(FeeRelationExpandColumnName.valueOf(name));
	}

	public String getTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(FeeRelationExpandTableName.userInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yss.framework.api.mvc.dao.sql.SQLBuilder#buildDeleteSql(com.yss.framework.
	 * db.sql.DBNameResolver)
	 */
	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yss.framework.api.mvc.dao.sql.SQLBuilder#buildInsertSql(com.yss.framework.
	 * db.sql.DBNameResolver)
	 */
	public String buildInsertSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yss.framework.api.mvc.dao.sql.SQLBuilder#buildSelectSql(com.yss.framework.
	 * db.sql.DBNameResolver)
	 */
	public String buildSelectSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yss.framework.api.mvc.dao.sql.SQLBuilder#buildUpdateSql(com.yss.framework.
	 * db.sql.DBNameResolver)
	 */
	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yss.framework.api.mvc.dao.sql.SQLBuilder#getQueryConditionCountSql(java.util
	 * .List)
	 */
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 根据前台传过来的条件参数 按照序号和费用代码升序的方式 拼接查询关联费用字典所有数据的sql
	 * @param paraNameList
	 */
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String querySql = null;
		StringBuffer sqlBuffer = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			sqlBuffer.append("SELECT * FROM T_S_DF_FEE_RELA A WHERE ");
			setWhereSql(valueFieldbuf, paraNameList);
			if (valueFieldbuf.length() > 0) {
				sqlBuffer.append(valueFieldbuf);
			}
			sqlBuffer.append(" ORDER BY A.N_ORDER,A.C_FEE_CODE ");
			querySql = sqlBuffer.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(sqlBuffer);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return querySql;
	}

	private void setWhereSql(StringBuffer buf, List<String> paraNameList) {
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_TD_TYPE")) {
				buf.append(" C_TD_TYPE = ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_START")) {
				buf.append(" a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				buf.append(" a.C_DEL_TIME <= ? AND ");
			}
		}
		if (buf.length() > 0) {
			StringUtil.delLastSplitMark(buf, " AND ");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.yss.framework.api.mvc.dao.sql.SQLBuilder#getRecycleTableName(com.yss.framework
	 * .db.sql.DBNameResolver)
	 */
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(FeeRelationExpandTableName.userInfo);
	}
	/**
	 * 查询资产交易关联费用的sql
	 * chenchangyou
	 * 20141128
	 * @return
	 */
	public String queryRealtionSql(HashMap<String, Object> paraMap){
		String queryDic="";
		if(paraMap!=null&&!paraMap.isEmpty()){
			for(int i=0;i<paraMap.size();i++){
				String para=(String)paraMap.get(i+"");
				if(i==0){
					queryDic+="'"+para+"'";
				}else{
					queryDic+=",'"+para+"'";
				}			
			}
		}
		StringBuffer buf=new StringBuffer();
		buf.append(" select rownum as c_iden,c_fee_code,c_ie_code,c_td_type,rownum as N_ORDER,c_FEE_PARTY  from (");
		buf.append(" select distinct c.c_fee_code,c.c_ie_code,'ZCJY' as c_td_type, ");
		buf.append(" d.c_para_value as c_FEE_PARTY from ( ");
		buf.append(" select distinct a.c_fee_code,b.c_para_value as c_ie_code,a.c_iden ");
		buf.append(" from T_P_CO_NS_TD_FEE a left join T_P_CO_NS_TD_PARA b");
		buf.append(" on a.c_iden=b.c_iden_rela where c_para_code='C_IE_CODE' and  a.c_iden in( ");
		buf.append(" select c_iden_rela from T_P_CO_NS_TD_PARA ");
		buf.append(" where c_para_value in( ");
		buf.append(queryDic);
		buf.append(" )))");
		buf.append(" c left join T_P_CO_NS_TD_PARA d on c.c_iden=d.c_iden_rela where d.c_para_code='CBox_FYSZF')");
		if(paraMap != null && paraMap.containsKey("C_FEE_PARTY")
				&& paraMap.get("C_FEE_PARTY")!=null&&!"".equals((paraMap.get("C_FEE_PARTY")))){
			buf.append(" where c_FEE_PARTY='OTHERS_PARTY'");
		}
		return buf.toString();
	}
}
