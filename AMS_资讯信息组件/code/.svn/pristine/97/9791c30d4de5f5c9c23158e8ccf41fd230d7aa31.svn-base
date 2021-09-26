package com.yss.ams.sec.information.modules.mp.secequ.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;


/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
public class SecEquSqlBuilder implements SQLBuilder {


	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append("  select count(*) as CNT from T_D_MP_SEC_EQU a ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append(" order by a.N_CHECK_STATE asc ,C_UPDATE_TIME DESC ,C_CHECK_TIME DESC ");
		retSql = buf.toString();

		return retSql;
	}


	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select C_IDEN,C_EQU_CLS,C_DATA_IDF,C_SEC_CODE,C_SEC_CODE_TAG,C_DS_CODE,C_ZS_CODE,N_EQU_RATIO_PT,N_EQU_RATIO_AT,N_PRICE_PLAC,C_DC_CODE,C_MKT_CODE, ");
		buf.append(" C_SEC_CODE_TMP,C_DV_VAR_DUR,C_DV_CODE,C_DTA_CODE,D_REG,D_FINAL,D_EXR,C_DESC,N_CHECK_STATE,C_UPDATE_BY,C_UPDATE_TIME,C_CHECK_BY,C_CHECK_TIME ");
		buf.append("  from T_D_MP_SEC_EQU a ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append(" order by a.N_CHECK_STATE asc ,C_UPDATE_TIME DESC ,C_CHECK_TIME DESC ");
		retSql = buf.toString();

		return retSql;
	}

	
	/**
	 * 整合前台条件,包括:证券送配,证券发行,证券流通,对价派息
	 * @param valueFieldbuf
	 * @param paraNameList
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));

		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("ARRAY_C_MKT_CODE")) {
				valueFieldbuf.append(" a.C_MKT_CODE IN (SELECT * FROM TABLE(?)) AND "); //交易市场 in
			}else if(fieldedName.equals("C_EQU_CLS")) {
				valueFieldbuf.append(" a.C_EQU_CLS = ?  AND "); //数据标识 =
			}else if(fieldedName.equals("C_DS_CODE")){
				valueFieldbuf.append(" a.C_DS_CODE = ?  AND "); //权益类型 =
			}else if(fieldedName.equals("C_ZS_CODE")){
				valueFieldbuf.append(" a.C_ZS_CODE = ?  AND "); //折算类型 =
			} else if (fieldedName.equals("C_SEC_CODE")) {
				valueFieldbuf.append(" a.C_SEC_CODE = ? AND ");
			     // STORY #23637 交易证券控件增加上市代码列 Yun-tao Lau 2015-07-22
			}else if (fieldedName.equals("ARRAY_C_SEC_CODE")) {
				valueFieldbuf.append(" a.C_SEC_CODE IN (SELECT * FROM TABLE(?)) AND "); //交易证券 = 
			}else if (fieldedName.equals("D_BEGIN")) {
				valueFieldbuf.append("a.D_EXR >= TO_DATE(?,'yyyy-MM-dd') AND "); //>= 开始时间
			} else if (fieldedName.equals("D_END")) {
				valueFieldbuf.append("a.D_EXR <= TO_DATE(?,'yyyy-MM-dd') AND "); //<= 结束时间
			}else if(fieldedName.equals("C_SEC_CODE_TAG")){
				valueFieldbuf.append(" a.C_SEC_CODE_TAG like (?)  AND "); //表示证券 like
			} else if (fieldedName.equals("D_BEGIN_FX")) {
				valueFieldbuf.append("a.D_REG >= TO_DATE(?,'yyyy-MM-dd') AND "); //>=登记起始时间
			} else if (fieldedName.equals("D_END_FX")) {
				valueFieldbuf.append("a.D_REG <= TO_DATE(?,'yyyy-MM-dd') AND "); // <= 登记结束时间
			} else if(fieldedName.equals("C_DV_CODE")){ 
				valueFieldbuf.append(" a.C_DV_CODE  =?  AND "); //发行方式,分红类型
			}else if (fieldedName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append("a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				valueFieldbuf.append("a.C_DEL_TIME <= ? AND ");
			}else if (fieldedName.equals("D_BEGIN_YFX")) {
				valueFieldbuf.append("a.D_REG >= TO_DATE(?,'yyyy-MM-dd') AND "); // <= 登记结束时间
			}else if (fieldedName.equals("D_END_YFX")) {
				valueFieldbuf.append("a.D_EXR <= TO_DATE(?,'yyyy-MM-dd') AND "); // <= 登记结束时间
			} else if (fieldedName.equals("C_DATA_IDF")) {
				valueFieldbuf.append("a.C_DATA_IDF = ?  AND "); // <= 登记结束时间 
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
		return dbnameresolver.getColumnName(SecEquColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(SecEquTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(SecEquTableName.userInfo);
	}


	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(SecEquTableName.userInfo);
	}

}
