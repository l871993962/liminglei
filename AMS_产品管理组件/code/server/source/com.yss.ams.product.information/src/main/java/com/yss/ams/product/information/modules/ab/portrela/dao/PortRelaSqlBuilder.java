package com.yss.ams.product.information.modules.ab.portrela.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

public class PortRelaSqlBuilder implements SQLBuilder {

	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		return getQueryOrganCountSql(paraNameList);
	}

	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		return this.getQueryOrganSql(paraNameList);
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
		return dbnameresolver.getColumnName(PortRelaColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(PortRelaTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(PortRelaTableName.userInfo);
	}

	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));

		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_RELA_TYPE")) {
				valueFieldbuf.append(" a.C_RELA_TYPE = ?  AND ");
			} else if (fieldedName.equals("C_RELA_CODE")) {
				valueFieldbuf.append(" a.C_RELA_CODE = ? AND ");
			} else if (fieldedName.equals("ARRAY_C_PORT_CODE")) {
				valueFieldbuf
						.append(" a.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("C_DV_TYPE_CODE")) {
				valueFieldbuf.append("a.C_DV_TYPE_CODE = ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append(" a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				valueFieldbuf.append(" a.C_DEL_TIME <= ? AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	/**
	 * 股东账户
	 * 
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getQueryCashAccountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);

		buf.append(" SELECT A.C_RELA_CODE, A.C_IDEN ,");
		buf.append("        A.C_PORT_CODE, ");
		buf.append("        A.C_RELA_TYPE, ");
		buf.append("        A.C_DV_TYPE_CODE, ");
		buf.append("        A.C_RELA_CODE AS C_SH_ACC_CODE, ");
		buf.append("        A.C_DESC, ");
		buf.append("        A.N_CHECK_STATE, ");
		buf.append("        A.C_UPDATE_BY, ");
		buf.append("        A.C_UPDATE_TIME, ");
		buf.append("        A.C_CHECK_BY, ");
		buf.append("        A.C_CHECK_TIME, ");
		buf.append("        B.C_SH_ACC_NAME, ");
		buf.append("        B.C_MKT_CODE, ");
		buf.append("        A.C_CA_CODE ");
		buf.append("   FROM T_P_AB_PORT_RELA A ");
		buf.append("   LEFT JOIN (SELECT C_SH_ACC_NAME, C_MKT_CODE, C_SH_ACC_CODE ");
		buf.append("                FROM T_P_AB_SH_ACC ");
		buf.append("               WHERE N_CHECK_STATE = 1) B ");
		buf.append("     ON B.C_SH_ACC_CODE = A.C_RELA_CODE ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append("  ORDER BY A.N_CHECK_STATE, A.C_UPDATE_TIME DESC ");

		return buf.toString();
	}

	public String getQueryCashAccountCountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" SELECT COUNT(*) AS CNT ");
		buf.append("   FROM T_P_AB_PORT_RELA A ");
		buf.append("   LEFT JOIN (SELECT C_SH_ACC_NAME, C_MKT_CODE, C_SH_ACC_CODE ");
		buf.append("                FROM T_P_AB_SH_ACC ");
		buf.append("               WHERE N_CHECK_STATE = 1) B ");
		buf.append("     ON B.C_SH_ACC_CODE = A.C_RELA_CODE ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append("  ORDER BY A.N_CHECK_STATE, A.C_UPDATE_TIME DESC ");

		return buf.toString();
	}

	/**
	 * 客户编号
	 * 
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getQueryCashAccountIdSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" SELECT A.C_RELA_CODE, A.C_IDEN ,");
		buf.append("        A.C_PORT_CODE, ");
		buf.append("        A.C_RELA_TYPE, ");
		buf.append("        A.C_DV_TYPE_CODE, ");
		buf.append("        A.C_RELA_CODE AS C_SH_ACC_CODE, ");
		buf.append("        A.C_DESC, ");
		buf.append("        A.N_CHECK_STATE, ");
		buf.append("        A.C_UPDATE_BY, ");
		buf.append("        A.C_UPDATE_TIME, ");
		buf.append("        A.C_CHECK_BY, ");
		buf.append("        A.C_CHECK_TIME, ");
		buf.append("        B.C_SH_ACC_NAME, ");
		buf.append("        B.C_MKT_CODE, ");
		buf.append("        A.C_CA_CODE ");
		buf.append("   FROM T_P_AB_PORT_RELA A ");
		buf.append("   LEFT JOIN (SELECT C_SH_ACC_NAME, C_MKT_CODE, C_SH_ACC_CODE ");
		buf.append("                FROM T_P_AB_SH_ACC ");
		buf.append("               WHERE N_CHECK_STATE = 1) B ");
		buf.append("     ON B.C_SH_ACC_CODE = A.C_RELA_CODE ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append("  ORDER BY A.N_CHECK_STATE, A.C_UPDATE_TIME DESC ");

		return buf.toString();
	}

	public String getQueryCashAccountCountIdSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" SELECT COUNT(*) AS CNT ");
		buf.append("   FROM T_P_AB_PORT_RELA A ");
		buf.append("   LEFT JOIN (SELECT C_SH_ACC_NAME, C_MKT_CODE, C_SH_ACC_CODE ");
		buf.append("                FROM T_P_AB_SH_ACC ");
		buf.append("               WHERE N_CHECK_STATE = 1) B ");
		buf.append("     ON B.C_SH_ACC_CODE = A.C_RELA_CODE ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append("  ORDER BY A.N_CHECK_STATE, A.C_UPDATE_TIME DESC ");

		return buf.toString();
	}

	/**
	 * 交易席位
	 * 
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getQueryTradeSeatSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" SELECT A.C_IDEN, ");
		buf.append("        A.C_RELA_CODE, ");
		buf.append("        A.C_PORT_CODE, ");
		buf.append("        A.C_RELA_CODE AS C_TD_CHAN_CODE, ");
		buf.append("        A.C_DESC, ");
		buf.append("        A.N_CHECK_STATE, ");
		buf.append("        A.C_DV_TYPE_CODE, ");
		buf.append("        A.C_UPDATE_BY, ");
		buf.append("        A.C_UPDATE_TIME, ");
		buf.append("        A.C_CHECK_BY, ");
		buf.append("        A.C_CHECK_TIME, ");
		buf.append("        A.C_RELA_TYPE, ");
		buf.append("        B.C_TD_CHAN_NAME, ");
		buf.append("        B.C_DV_CHAN_TYPE, ");
		buf.append("        B.C_MKT_CODE, ");
		buf.append("        A.C_CA_CODE ");
		buf.append("   FROM T_P_AB_PORT_RELA A ");
		buf.append("   LEFT JOIN (SELECT * ");
		buf.append("                FROM T_P_AB_TD_CHAN ");
		buf.append("               WHERE N_CHECK_STATE = 1 ");
		buf.append("                 AND C_DV_CHAN_TYPE = 'CHAN_SEAT') B ");
		buf.append("     ON B.C_TD_CHAN_CODE = A.C_RELA_CODE ");
		//modified by lijinpeng 20170524 "产品关联信息"不保存市场代码到T_P_AB_PORT_RELA表,添加 市场代码为null的条件
		buf.append("     AND (B.C_MKT_CODE = A.C_MKT_CODE  OR A.C_MKT_CODE IS NULL)");
		
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append("  ORDER BY A.N_CHECK_STATE, A.C_UPDATE_TIME DESC ");

		return buf.toString();
	}

	public String getQueryTradeSeatCountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" SELECT COUNT(*) AS CNT ");
		buf.append("   FROM T_P_AB_PORT_RELA A ");
		buf.append("   LEFT JOIN (SELECT * ");
		buf.append("                FROM T_P_AB_TD_CHAN ");
		buf.append("               WHERE N_CHECK_STATE = 1 ");
		buf.append("                 AND C_DV_CHAN_TYPE = 'CHAN_SEAT') B ");
		buf.append("     ON B.C_TD_CHAN_CODE = A.C_RELA_CODE ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append("  ORDER BY A.N_CHECK_STATE, A.C_UPDATE_TIME DESC ");

		return buf.toString();
	}

	/**
	 * 期货公司
	 * 
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getQueryTradeOrgSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" SELECT A.C_IDEN, ");
		buf.append("        A.C_RELA_CODE, ");
		buf.append("        A.C_PORT_CODE, ");
		buf.append("        A.C_RELA_CODE AS C_TD_CHAN_CODE, ");
		buf.append("        A.C_DESC, ");
		buf.append("        A.N_CHECK_STATE, ");
		buf.append("        A.C_DV_TYPE_CODE, ");
		buf.append("        A.C_UPDATE_BY, ");
		buf.append("        A.C_UPDATE_TIME, ");
		buf.append("        A.C_CHECK_BY, ");
		buf.append("        A.C_CHECK_TIME, ");
		buf.append("        A.C_RELA_TYPE, ");
		buf.append("        B.C_TD_CHAN_NAME, ");
		buf.append("        B.C_DV_CHAN_TYPE, ");
		buf.append("        B.C_MKT_CODE, ");
		buf.append("        A.C_CA_CODE ");
		buf.append("   FROM T_P_AB_PORT_RELA A ");
		buf.append("   LEFT JOIN (SELECT * ");
		buf.append("                FROM T_P_AB_TD_CHAN ");
		buf.append("               WHERE N_CHECK_STATE = 1 ");
		buf.append("                 AND C_DV_CHAN_TYPE = 'CHAN_ORG') B ");
		buf.append("     ON B.C_TD_CHAN_CODE = A.C_RELA_CODE AND A.C_MKT_CODE = B.C_MKT_CODE");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append("  ORDER BY A.N_CHECK_STATE, A.C_UPDATE_TIME DESC ");

		return buf.toString();
	}

	/**
	 * 期货公司SET
	 * 
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getQueryTradeOrgSetSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" SELECT distinct A.C_IDEN, ");
		buf.append("        A.C_RELA_CODE, ");
		buf.append("        A.C_PORT_CODE, ");
		buf.append("        A.C_RELA_CODE AS C_TD_CHAN_CODE, ");
		buf.append("        '' as C_DESC, ");
		buf.append("        1 AS N_CHECK_STATE, ");
		buf.append("        '' as C_DV_TYPE_CODE, ");
		buf.append("        '' as C_UPDATE_BY, ");
		buf.append("        '' as C_UPDATE_TIME, ");
		buf.append("        '' as C_CHECK_BY, ");
		buf.append("        '' as C_CHECK_TIME, ");
		buf.append("        A.C_RELA_TYPE, ");
		buf.append("        B.C_TD_CHAN_NAME, ");
		buf.append("        B.C_DV_CHAN_TYPE, ");
		buf.append("        B.C_MKT_CODE, ");
		buf.append("        A.C_CA_CODE ");
		buf.append("   FROM T_P_AB_PORT_RELA A ");
		buf.append("   LEFT JOIN (SELECT * ");
		buf.append("                FROM T_P_AB_TD_CHAN ");
		buf.append("               WHERE N_CHECK_STATE = 1 ");
		buf.append("                 AND C_DV_CHAN_TYPE = 'CHAN_ORG') B ");
		buf.append("     ON B.C_TD_CHAN_CODE = A.C_RELA_CODE AND A.C_MKT_CODE = B.C_MKT_CODE");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		// buf.append("  ORDER BY A.N_CHECK_STATE, A.C_UPDATE_TIME DESC ");

		return buf.toString();
	}
	
	/**
	 * 期货公司SET(新增)
	 * 
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getPortRelaTdOrgSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();

		buf.append(" SELECT distinct '' as C_IDEN, ");
		buf.append("        '' as C_RELA_CODE, ");
		buf.append("        '' as C_PORT_CODE, ");
		buf.append("        A.C_TD_CHAN_CODE, ");
		buf.append("        '' as C_DESC, ");
		buf.append("        1 AS N_CHECK_STATE, ");
		buf.append("        '' as C_DV_TYPE_CODE, ");
		buf.append("        '' as C_UPDATE_BY, ");
		buf.append("        '' as C_UPDATE_TIME, ");
		buf.append("        '' as C_CHECK_BY, ");
		buf.append("        '' as C_CHECK_TIME, ");
		buf.append("        '' as C_RELA_TYPE, ");
		buf.append("        A.C_TD_CHAN_NAME, ");
		buf.append("        A.C_DV_CHAN_TYPE, ");
		//edit by gongyue 20170125 产品关联信息中期货公司设置新增无交易市场数据
		//buf.append("        '' AS C_MKT_CODE, ");
		buf.append("        A.C_MKT_CODE AS C_MKT_CODE, ");
		buf.append("        '' AS C_CA_CODE ");
		buf.append("   FROM T_P_AB_TD_CHAN A ");
		buf.append("   LEFT JOIN T_P_AB_PORT_RELA B ");
		buf.append("   		ON  A.C_TD_CHAN_CODE = B.C_RELA_CODE ");
		buf.append("     	AND B.C_RELA_TYPE='RELA_QHGS' ");
		buf.append("        AND B.N_CHECK_STATE >= 0 ");
		buf.append("        where A.C_TD_CHAN_CODE not in ");
		buf.append("        (select C_RELA_CODE from T_P_AB_PORT_RELA ");
		buf.append("        where C_PORT_CODE IN ");
		buf.append("  		(SELECT * FROM TABLE(?)) ");
		buf.append("        AND C_RELA_TYPE='RELA_QHGS') ");
		buf.append("   		AND A.C_DV_CHAN_TYPE = 'CHAN_ORG' ");
		buf.append("   		AND A.N_CHECK_STATE = 1 ");
		buf.append("    	order by A.C_TD_CHAN_CODE DESC ");
		// buf.append("  ORDER BY A.N_CHECK_STATE, A.C_UPDATE_TIME DESC ");

		return buf.toString();
	}
	
	public String getQueryTradeOrgCountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" SELECT COUNT(*) AS CNT ");
		buf.append("   FROM T_P_AB_PORT_RELA A ");
		buf.append("   LEFT JOIN (SELECT * ");
		buf.append("                FROM T_P_AB_TD_CHAN ");
		buf.append("               WHERE N_CHECK_STATE = 1 ");
		buf.append("                 AND C_DV_CHAN_TYPE = 'CHAN_ORG') B ");
		buf.append("     ON B.C_TD_CHAN_CODE = A.C_RELA_CODE AND A.C_MKT_CODE = B.C_MKT_CODE");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append("  ORDER BY A.N_CHECK_STATE, A.C_UPDATE_TIME DESC ");

		return buf.toString();
	}

	/**
	 * 投资经理
	 * 
	 * @param paraNameList
	 * @return
	 */
	public String getQueryInvestMgrSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		/*
		 * Author : ChenLong
		 * Date   : 2014-03-12
		 * Status : Modify
		 * Comment: 增加左关联 T_P_AB_INV_MGR 获取投资经理中文名（B.C_USER_NAME_CN）和到岗时间（B.D_DUTY）
		 * */
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" SELECT ");
		buf.append(this.getPortRelqsql("A"));
		buf.append(" A.C_RELA_CODE AS C_USER_CODE, B.C_USER_NAME_CN AS C_USER_NAME_CN, B.D_DUTY D_DUTY");
		buf.append(" FROM T_P_AB_PORT_RELA A ");
		buf.append(" left join T_P_AB_INV_MGR B on A.C_RELA_CODE = B.c_User_Code ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append("  ORDER BY A.N_CHECK_STATE, A.C_UPDATE_TIME DESC ");
		return buf.toString();
	}

	public String getQueryInvestMgrCountSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" SELECT COUNT(*) AS CNT ");
		buf.append("   FROM T_P_AB_PORT_RELA A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append("  ORDER BY A.N_CHECK_STATE, A.C_UPDATE_TIME DESC ");
		return buf.toString();
	}


	// BUG #166485 【海通证券】同一个产品可以选择多个托管人，导致报表数据翻倍。 add by songdabang 20170803
	public String getQueryOrganCountSql(String portCode,String organ) {
		StringBuffer buf = new StringBuffer();

		buf.append(" SELECT COUNT(*) AS CNT ");
		buf.append("   FROM T_P_AB_PORT_RELA A ");
		buf.append("   LEFT JOIN (SELECT * FROM T_P_BI_ORG WHERE N_CHECK_STATE = 1) B ");
		buf.append("  ON B.C_ORG_CODE = A.C_RELA_CODE WHERE A.C_PORT_CODE = ? AND A.C_DV_TYPE_CODE = ?");
		return buf.toString();
	}
	
	
	public String getQueryOrganSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" SELECT ");
		buf.append(this.getPortRelqsql("A"));
		buf.append(" A.C_RELA_CODE AS C_ORG_CODE, B.C_ORG_NAME, B.C_DV_ORG_TYPE ");
		buf.append("   FROM T_P_AB_PORT_RELA A ");
		buf.append("   LEFT JOIN (SELECT * FROM T_P_BI_ORG WHERE N_CHECK_STATE = 1) B ");
		buf.append("     ON B.C_ORG_CODE = A.C_RELA_CODE ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append("  ORDER BY A.N_CHECK_STATE, A.C_UPDATE_TIME DESC ");

		return buf.toString();
	}
	
	/**
	 * BUG #296864 银华基金-支付平台-【300.7 1031】后台服务一直报错
	 * @param remark
	 * @return
	 */
	private String getPortRelqsql(String remark){
	   StringBuffer buf = new StringBuffer();
	   buf.append(remark).append(".c_iden,			");
	   buf.append(remark).append(".c_port_code,      ");
	   buf.append(remark).append(".c_rela_type,      ");
	   buf.append(remark).append(".c_rela_code,      ");
	   buf.append(remark).append(".c_dv_type_code,   ");
	   buf.append(remark).append(".c_desc,           ");
	   buf.append(remark).append(".n_check_state,    ");
	   buf.append(remark).append(".c_update_by,      ");
	   buf.append(remark).append(".c_update_time,    ");
	   buf.append(remark).append(".c_check_by,       ");
	   buf.append(remark).append(".c_check_time,     ");
	   buf.append(remark).append(".c_ca_code,        ");
	   buf.append(remark).append(".c_mkt_code,       ");
	   buf.append(remark).append(".c_match_word,     ");
	   buf.append(remark).append(".c_dv_use_range,   ");
	   buf.append(remark).append(".c_dv_match_type,  ");
	   buf.append(remark).append(".d_start,          ");
	   buf.append(remark).append(".d_end,            ");
	   buf.append(remark).append(".c_bic_code,       ");
		return buf.toString();
	}
	

	public String getQueryOrganCountSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" SELECT COUNT(*) AS CNT ");
		buf.append("   FROM T_P_AB_PORT_RELA A ");
		buf.append("   LEFT JOIN (SELECT * FROM T_P_BI_ORG WHERE N_CHECK_STATE = 1) B ");
		buf.append("     ON B.C_ORG_CODE = A.C_RELA_CODE ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append("  ORDER BY A.N_CHECK_STATE, A.C_UPDATE_TIME DESC ");

		return buf.toString();
	}

	/* START 数据服务 */
	
	public String getAllKeyDataSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getKeyQuerySqlBuf(buf);

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	public String getAllDataSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
//edt by zzk 20150310
		// buf.append(" SELECT b.c_port_code, B.C_DV_TYPE_CODE,B.c_RELA_CODE,b.C_RELA_TYPE,a.* ");
		buf.append(" SELECT b.c_port_code, B.C_DV_TYPE_CODE,B.c_RELA_CODE,b.C_RELA_TYPE, ");
		buf.append(" a.c_td_chan_name, a.c_dv_chan_type, a.c_td_chan_code, a.c_org_code, ");
		buf.append(" a.c_mkt_code, a.c_desc, a.n_check_state, a.c_update_by, a.c_update_time,  ");
		//edit by Yuntao Lau 2015.08.24 BUG #116159   sql语句中增加了c_ca_code字段
		buf.append(" a.c_check_by, a.c_check_time, a.c_iden, a.c_ca_code  ");
		buf.append(" FROM t_p_ab_port_rela b ");
		buf.append(" RIGHT JOIN ( SELECT * FROM T_P_AB_TD_CHAN ");
		buf.append(" WHERE n_check_state = 1) a ");
		buf.append(" ON b.c_rela_code =a.c_td_chan_code ");
		buf.append(" where b.n_check_state = 1 ");
		buf.append(" and b.c_rela_type = 'RELA_TD_SEAT' ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	public String getDataByCode() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonQuerySqlBuf(buf);
		buf.append("AND c_port_code = ?");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	public String getDataListByTypes() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonQuerySqlBuf(buf);

		buf.append(" AND c_port_code ");
		buf.append(" IN (SELECT * FROM table(?)) ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	private void getCommonQuerySqlBuf(StringBuffer buf) {
		//edt by zzk 20150310
		// buf.append(" SELECT b.c_port_code, a.* ");
		buf.append(" SELECT b.c_port_code, ");
		buf.append(" a.c_td_chan_name, a.c_dv_chan_type, a.c_td_chan_code, a.c_org_code, ");
		buf.append(" a.c_mkt_code, a.c_desc, a.n_check_state, a.c_update_by, a.c_update_time,  ");
		buf.append(" a.c_check_by, a.c_check_time, a.c_iden  ");
		buf.append(" FROM t_p_ab_port_rela b ");
		buf.append(" RIGHT JOIN ( SELECT * FROM T_P_AB_TD_CHAN ");
		buf.append(" WHERE n_check_state = 1) a ");
		buf.append(" ON b.c_rela_code =a.c_td_chan_code ");
		buf.append(" where b.n_check_state = 1 ");
		buf.append(" and b.c_rela_type = 'RELA_TD_SEAT' ");
	}
	
	private void getKeyQuerySqlBuf(StringBuffer buf) {
		buf.append(" SELECT C_TD_CHAN_CODE,C_TD_CHAN_NAME FROM T_P_AB_TD_CHAN ");
		buf.append(" WHERE n_check_state = 1 ");
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(PortRelaTableName.userInfo);
	}
	
	public String getQueryIndexCountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" SELECT COUNT(*) AS CNT ");
		buf.append("   FROM T_P_AB_PORT_RELA A ");
		buf.append("   LEFT JOIN (SELECT * ");
		buf.append("                FROM T_P_SV_INDEX ");
		buf.append("               WHERE N_CHECK_STATE = 1 ) B ");
		buf.append("     ON B.C_INDEX_CODE = A.C_RELA_CODE ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append("  ORDER BY A.N_CHECK_STATE, A.C_UPDATE_TIME DESC ");

		return buf.toString();
	}

	public String getQueryIndexSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" SELECT ");
		buf.append(this.getPortRelqsql("A"));
		buf.append("  A.C_RELA_CODE AS C_INDEX_CODE, B.C_INDEX_NAME ");
		buf.append("   FROM T_P_AB_PORT_RELA A ");
		buf.append("   LEFT JOIN (SELECT * FROM T_P_SV_INDEX WHERE N_CHECK_STATE = 1) B ");
		buf.append("     ON B.C_INDEX_CODE = A.C_RELA_CODE ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append("  ORDER BY A.N_CHECK_STATE, A.C_UPDATE_TIME DESC ");

		return buf.toString();
	}

	public String getDelInsertSql(){
		return "delete from T_P_AB_PORT_RELA where C_DV_TYPE_CODE=? and C_RELA_CODE=? and C_RELA_TYPE=? ";
	}
	public String getDelSql(){
		return "delete from T_P_AB_PORT_RELA where C_DV_TYPE_CODE=? and C_RELA_CODE=? and C_RELA_TYPE=? and C_PORT_CODE=? ";
	}
	/* END 数据服务 */

	/**
	 * @author liuxiang 2016-5-20 STORY #28860 上交所跨市场ETF基金产品整体需求
	 * @param paraNameList
	 * @return
	 */
	public String getQueryMemberSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" SELECT ");
		buf.append(this.getPortRelqsql("A"));
		buf.append("  A.C_RELA_CODE AS C_MBR_CODE, B.C_ORG_CODE,B.C_BROKER_CODE");
		buf.append("   FROM T_P_AB_PORT_RELA A ");
		buf.append("   LEFT JOIN (SELECT * FROM T_P_BI_ORG_MBR WHERE N_CHECK_STATE = 1) B ");
		buf.append("     ON B.C_MBR_CODE = A.C_RELA_CODE ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append("  ORDER BY A.N_CHECK_STATE, A.C_UPDATE_TIME DESC ");

		return buf.toString();
	}
	
	/**
	 * @author guohui 2017-09-04 STORY #37768 【南方基金】组合可以设置绑定多个现金账户，当有界面筛选现金账户时，只显示绑定的现金账户
	 * @param paraNameList
	 * @return
	 */
	public String getQueryCashAccSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" SELECT ");
		buf.append(this.getPortRelqsql("A"));
		buf.append(" A.C_RELA_CODE AS C_CA_CODE_A, B.C_CA_NAME,B.C_DV_ACC_TYPE,B.C_DC_CODE,B.C_DV_ACC_NATURE,B.C_DESC as C_DESC_A");
		buf.append("   FROM T_P_AB_PORT_RELA A ");
		buf.append("   LEFT JOIN (SELECT * FROM T_P_BI_CASH_ACC WHERE N_CHECK_STATE = 1) B ");
		buf.append("     ON B.C_CA_CODE = A.C_RELA_CODE ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append("  ORDER BY A.N_CHECK_STATE, A.C_UPDATE_TIME DESC ");

		return buf.toString();
	}

	/**
	 * @author liuxiang 2016-5-20 STORY #28860 上交所跨市场ETF基金产品整体需求
	 * @param paraNameList
	 * @return
	 */
	public String getQueryMemberCountSql(List<String> paraNameList) {

		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" SELECT COUNT(*) AS CNT ");
		buf.append("   FROM T_P_AB_PORT_RELA A ");
		buf.append("   LEFT JOIN (SELECT * ");
		buf.append("                FROM T_P_BI_ORG_MBR ");
		buf.append("               WHERE N_CHECK_STATE = 1 ) B ");
		buf.append("     ON B.C_MBR_CODE = A.C_RELA_CODE ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append("  ORDER BY A.N_CHECK_STATE, A.C_UPDATE_TIME DESC ");

		return buf.toString();
	}
	
	/**
	 * Author : ChenLong
	 * Date   : 2016-09-21
	 * Status : Add
	 * Comment: 检测组合是否已经关联委托人机构了
	 * @return
	 */
	public String getCheckORGConsignerForPortSQL(){
		StringBuilder sqlBuild = new StringBuilder();
		sqlBuild.append(" select 1 from t_p_ab_port_rela where c_port_code in (select * from table(?)) and c_dv_type_code = 'CONSIGNER'");
		return sqlBuild.toString();
	}
	
	public String getPortRelaOrgByPortAndDvType(){
		StringBuilder sqlBuild = new StringBuilder();
		sqlBuild.append(" select C_PORT_CODE,C_RELA_CODE from t_p_ab_port_rela ");
		sqlBuild.append(" where c_port_code in (select * from table(?)) ");
		sqlBuild.append(" and c_dv_type_code = ? and C_RELA_TYPE = 'RELA_ORG' ");
		return sqlBuild.toString();
	}
}
