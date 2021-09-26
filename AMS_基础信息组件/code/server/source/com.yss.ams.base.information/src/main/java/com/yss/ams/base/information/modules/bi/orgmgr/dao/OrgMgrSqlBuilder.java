package com.yss.ams.base.information.modules.bi.orgmgr.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * 机构结算会员设置  操作数据库 SQL生成类
 * @author 马向峰  拆分
 * @Date 20170531
 *
 */
public class OrgMgrSqlBuilder implements SQLBuilder {

	/**
	 * 生成根据 会员号、机构代码查找总数量的SQL
	 * @author 马向峰  拆分
	 * @Date 20170531
	 */
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append("select count(*) as CNT from T_P_BI_ORG_MBR a ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" where ").append(valueFieldbuf);
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

	/**
	 * 生成根据 会员号、机构代码查找的SQL
	 * @author 马向峰  拆分
	 * @Date 20170531
	 */
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append("select a.* from T_P_BI_ORG_MBR a ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" where ").append(valueFieldbuf);
			}
			buf.append("   order by  a.N_CHECK_STATE, a.c_mbr_code  ");
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
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_MBR_CODE")) {
				valueFieldbuf.append(" a.C_MBR_CODE like ? AND ");
			} else if (fieldedName.equals(("C_ORG_CODE"))) {
				valueFieldbuf.append("  a.C_ORG_CODE =? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append("a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				valueFieldbuf.append("a.C_DEL_TIME <= ? AND ");
			} else if (fieldedName.equals("C_BROKER_CODE")) {
				valueFieldbuf.append(" a.C_MBR_CODE like ? AND ");
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
		return dbnameresolver.getColumnName(OrgMgrColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getLogTableName(OrgMgrTableName.userInfo);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(OrgMgrTableName.userInfo);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getSequenceName(OrgMgrTableName.userInfo);
	}

	/**
	 * Author : ChenLong
	 * Date   : 2016-05-10
	 * Status : Add
	 * Comment: 获取所有结算会员代码
	 */
	public String getAllMBRCodesSQL(){
		String sql = "select distinct c_mbr_code from t_p_bi_org_mbr where n_check_state = 1";
		return sql;
	}

	/**
	 * @author liuxiang 2016-5-20 STORY #28860 上交所跨市场ETF基金产品整体需求
	 * @param paraNameList
	 * @return
	 */
	public String getPortRelaMember(List<String> paraNameList) {
		StringBuffer buf  = new StringBuffer();
		buf.append(" SELECT A.* ");
		buf.append("   FROM T_P_BI_ORG_MBR A ");
		buf.append("  WHERE NOT EXISTS (SELECT * ");
		buf.append("           FROM T_P_AB_PORT_RELA B ");
		buf.append("          WHERE A.C_MBR_CODE = B.C_RELA_CODE ");
		buf.append("            AND B.C_PORT_CODE = ? ");
		buf.append("            AND B.N_CHECK_STATE >= 0) ");
		buf.append("     AND A.N_CHECK_STATE = 1   ");

		return buf.toString();
	}
}
