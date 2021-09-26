package com.yss.ams.base.information.modules.bi.tdchan.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

public class TdChanSqlBuilder implements SQLBuilder {

	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer paraBuf = new StringBuffer();

		this.setWhereSql(paraBuf, paraNameList);
		buf.append(" select a.* ,'' as C_P_CODE ");
		buf.append(" from T_P_AB_TD_CHAN a ");
		buf
				.append(" left join ( select * from T_P_BI_ORG where N_CHECK_STATE = 1 )b ");
		buf.append(" on b.C_ORG_CODE = a.C_ORG_CODE ");
		
		if (paraBuf.length() > 0) {
			buf.append(" WHERE ").append(paraBuf);
		}
		buf.append(" order by a.N_CHECK_STATE asc,a.C_UPDATE_TIME DESC, a.rowId asc ");
		return buf.toString();
	}

	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer paraBuf = new StringBuffer();
		this.setWhereSql(paraBuf, paraNameList);
		buf.append(" select COUNT(*) AS CNT ");
		buf.append(" from T_P_AB_TD_CHAN a ");
		buf
				.append(" left join ( select * from T_P_BI_ORG where N_CHECK_STATE = 1 )b ");
		buf.append(" on b.C_ORG_CODE = a.C_ORG_CODE ");

		if (paraBuf.length() > 0) {
			buf.append(" WHERE ").append(paraBuf);
		}
		buf.append(" order by a.N_CHECK_STATE asc,a.C_UPDATE_TIME DESC, a.rowId asc ");
		return buf.toString();
	}

	private void setWhereSql(StringBuffer paraBuf, List<String> paraNameList) {
		paraBuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));

		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("ARRAY_C_MKT_CODE")) {
				paraBuf.append(" a.C_MKT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("C_TD_CHAN_CODE")) {
				paraBuf.append(" a.C_TD_CHAN_CODE like ? AND ");
			}else if (fieldedName.equals("ARRAY_C_DV_CHAN_TYPE")) {
			 //STORY #47034 【紧急】【招商基金】交易渠道设置功能界面增加多一个查询条件：渠道类型  add by zhouchi 2017 10 23
					paraBuf.append(" a.C_DV_CHAN_TYPE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("C_TD_CHAN_NAME")) {
				paraBuf.append(" a.C_TD_CHAN_NAME like ? AND ");
			} else if (fieldedName.equals("ARRAY_C_ORG_CODE")) {
				paraBuf
						.append(" a.C_ORG_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("C_DEL_TIME_START")) {
				paraBuf.append("a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				paraBuf.append("a.C_DEL_TIME <= ? AND ");
			} else if (fieldedName.equals("C_ORG_CODE")) {
				paraBuf.append("a.C_ORG_CODE = ? AND ");
			} else if (fieldedName.equals("ARRAY_C_PORT_CODE")) {
				paraBuf.append(" 1 = 0 AND ");
			} else if (fieldedName.equals("ARRAY_C_IDEN")) {
				paraBuf.append(" a.C_IDEN IN (SELECT * FROM TABLE(?)) AND ");
			}
		}

		if (paraBuf.length() > 0) {
			StringUtil.delLastSplitMark(paraBuf, " AND ");
		}
	}

	@Deprecated
	public String buildDeleteSql(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Deprecated
	public String buildInsertSql(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Deprecated
	public String buildSelectSql(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Deprecated
	public String buildUpdateSql(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbnameresolver,
			String s) {
		// TODO Auto-generated method stub
		return dbnameresolver.getColumnName(TdChanColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(TdChanTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(TdChanTableName.userInfo);
	}

	public String getPortRelaTdChanSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT c_td_chan_name, ");
		buf.append("        c_dv_chan_type, ");
		buf.append("        c_td_chan_code, ");
		buf.append("        c_org_code, ");
		buf.append("        c_mkt_code, ");
		buf.append("        c_desc, ");
		buf.append("        n_check_state, ");
		buf.append("        c_update_by, ");
		buf.append("        c_update_time, ");
		buf.append("        c_check_by, ");
		buf.append("        c_check_time, ");
		//buf.append("        C_CA_CODE, ");
		buf.append("        c_iden ");
		buf.append("   FROM T_P_AB_TD_CHAN A ");
		buf.append("  WHERE NOT EXISTS (SELECT * ");
		buf.append("           FROM T_P_AB_PORT_RELA B ");
		buf.append("          WHERE A.C_TD_CHAN_CODE = B.C_RELA_CODE AND A.C_MKT_CODE = B.C_MKT_CODE");
		buf.append("            AND B.C_PORT_CODE IN ");
		buf.append("  (SELECT * FROM TABLE(?)) ");
		buf.append("            AND B.N_CHECK_STATE >= 0) ");
		buf.append("    AND A.C_DV_CHAN_TYPE = 'CHAN_SEAT' ");
		buf.append("    AND A.N_CHECK_STATE = 1 ");
		buf.append("    order by A.C_DV_CHAN_TYPE ");
		
		return buf.toString();
	}

	public String getPortRelaTdOrgSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT distinct c_td_chan_name, ");
		buf.append("        c_dv_chan_type, ");
		buf.append("        c_td_chan_code, ");
		buf.append("       '' as c_org_code, ");
		buf.append("       '' as c_mkt_code, ");
		buf.append("       '' as c_desc, ");
		buf.append("       1 as n_check_state, ");
		buf.append("       '' as c_update_by, ");
		buf.append("       '' as c_update_time, ");
		buf.append("       '' as c_check_by, ");
		buf.append("       '' as c_check_time, ");
		//buf.append("       '' as C_CA_CODE, ");
		buf.append("       '' as c_iden ");
		buf.append("   FROM T_P_AB_TD_CHAN A ");
		buf.append("  WHERE NOT EXISTS (SELECT * ");
		buf.append("           FROM T_P_AB_PORT_RELA B ");
		buf.append("          WHERE A.C_TD_CHAN_CODE = B.C_RELA_CODE ");
		buf.append("            AND B.C_PORT_CODE IN ");
		buf.append("  (SELECT * FROM TABLE(?)) ");
		buf.append("            AND B.N_CHECK_STATE >= 0) ");
		buf.append("    AND A.C_DV_CHAN_TYPE = 'CHAN_ORG' ");
		buf.append("    AND A.N_CHECK_STATE = 1 ");
		buf.append("    order by A.C_DV_CHAN_TYPE ");
		
		return buf.toString();
	}
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// by lihaizhi 20130522
		return dbNameResolver.getLogSequenceName(TdChanTableName.userInfo);
	}
	
	/*START 数据服务方法 */
	
	public String getAllDataSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonQuerySqlBuf(buf);

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	public String getDataByCode() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);

		buf.append(" WHERE ");
		buf.append(" C_TD_CHAN_CODE = ? ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	public String getDataListByTypes() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonQuerySqlBuf(buf);

		buf.append(" WHERE ");
		buf.append(" C_DV_CHAN_TYPE");
		buf.append(" IN (SELECT * FROM TABLE(?)) ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	private void getCommonQuerySqlBuf(StringBuffer buf) {
		buf.append(" select c.* ");
		buf.append(" from (select '' C_IDEN, C_DV_CODE as c_dv_chan_type, ");
		buf.append(" '[root]' as C_P_CODE, ");
		buf.append(" C_DV_CODE as c_td_chan_code, ");
		buf.append(" C_DV_NAME as c_td_chan_name, ");
		buf.append(" '' as c_org_code, ");
		buf.append(" '' as c_mkt_code, '' as c_desc, ");
		buf.append(" 1 as N_CHECK_STATE, '' as C_UPDATE_BY, ");
		buf.append(" '' as C_UPDATE_TIME, '' as C_CHECK_BY, ");
		//motified by lzj 20150310
		//bug108913 收支结转业务新增自动结转，新增过程系统报错。显示错误：无效列名c_ca_code
		buf.append(" '' as C_CHECK_TIME from v_s_dv_voc");
		buf.append(" where C_DV_TYPE = 'CHAN_TYPE' ");
		buf.append(" union all ");
		buf.append(" select b.C_IDEN,b.c_dv_chan_type, ");
		buf.append(" b.c_dv_chan_type as c_p_code,");
		buf.append(" b.c_td_chan_code, b.c_td_chan_name, ");
		buf.append(" b.c_org_code, b.c_mkt_code, b.c_desc, ");
		buf.append(" b.n_check_state,b.c_update_by,b.c_update_time, ");
		//motified by lzj 20150310
		//bug108913 收支结转业务新增自动结转，新增过程系统报错。显示错误：无效列名c_ca_code
		buf.append(" b.c_check_by, b.c_check_time ");
		buf.append(" from t_p_ab_td_chan b ");
		buf.append(" where b.n_check_state = 1 ) c");
//		buf.append(" start with c.C_P_CODE = '[root]' connect by prior c.c_td_chan_code = c.C_P_CODE");
	}

	public String getDataListByTimestamp() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonQuerySqlBuf1(buf);

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * BUG #172866 【加急】【南方基金】支持场外申赎业务流水的EXCEL格式的导入--存在问题汇总
	 * edit by zouyuan 20170914 根据选择的组合信息，加载关联的交易渠道信息，以及所有机构信息
	 */
	public String getCommonQuerySqlBufByPort() {
		StringBuffer buf=new StringBuffer();
		buf.append(" select c.*, DECODE(c.C_P_CODE, '[root]', '交易市场', mkt.C_MKT_NAME) AS C_MKT_NAME ");
		buf.append(" from ( ");
//		buf.append(" from (select '' C_IDEN, C_DV_CODE as c_dv_chan_type, ");
//		buf.append(" '[root]' as C_P_CODE, ");
//		buf.append(" C_DV_CODE as c_td_chan_code, ");
//		buf.append(" C_DV_NAME as c_td_chan_name, ");
//		buf.append(" '' as c_org_code, ");
//		buf.append(" '交易市场代码' as c_mkt_code, '' as c_desc, ");
//		buf.append(" 1 as N_CHECK_STATE, '' as C_UPDATE_BY, ");
//		buf.append(" '' as C_UPDATE_TIME, '' as C_CHECK_BY, ");
//		//motified by lzj 20150310
//		//bug108913 收支结转业务新增自动结转，新增过程系统报错。显示错误：无效列名c_ca_code
//		buf.append(" '' as C_CHECK_TIME from t_s_dv_voc");
//		buf.append(" where C_DV_TYPE = 'CHAN_TYPE' ");
//		buf.append(" union all ");
		buf.append(" select b.C_IDEN,b.c_dv_chan_type, ");
		buf.append(" b.c_dv_chan_type as c_p_code,");
		buf.append(" b.c_td_chan_code, b.c_td_chan_name, ");
		buf.append(" b.c_org_code, b.c_mkt_code, b.c_desc, ");
		buf.append(" b.n_check_state,b.c_update_by,b.c_update_time, ");
		//motified by lzj 20150310
		//bug108913 收支结转业务新增自动结转，新增过程系统报错。显示错误：无效列名c_ca_code
		buf.append(" b.c_check_by, b.c_check_time ");
		buf.append(" from t_p_ab_td_chan b ");
		buf.append(" JOIN T_P_AB_PORT_RELA RELA ON b.C_TD_CHAN_CODE=RELA.C_RELA_CODE AND RELA.N_CHECK_STATE = 1  ");
		buf.append(" where b.n_check_state = 1  AND RELA.C_RELA_TYPE = 'RELA_TD_SEAT'and RELA.c_Port_Code IN (SELECT * FROM TABLE(?)) ");
		buf.append(" union all ");
		buf.append(" select b.C_IDEN,b.c_dv_chan_type, ");
		buf.append(" b.c_dv_chan_type as c_p_code,");
		buf.append(" b.c_td_chan_code, b.c_td_chan_name, ");
		buf.append(" b.c_org_code, b.c_mkt_code, b.c_desc, ");
		buf.append(" b.n_check_state,b.c_update_by,b.c_update_time, ");
		//motified by lzj 20150310
		//bug108913 收支结转业务新增自动结转，新增过程系统报错。显示错误：无效列名c_ca_code
		buf.append(" b.c_check_by, b.c_check_time ");
		buf.append(" from t_p_ab_td_chan b ");
		buf.append(" where b.n_check_state = 1 AND b.c_dv_chan_type = 'CHAN_ORG' ");
		buf.append(" ) c ");
		buf.append(" LEFT JOIN(SELECT C_MKT_CODE, C_MKT_NAME FROM T_P_BI_MKT WHERE N_CHECK_STATE = 1) mkt ON(c.C_MKT_CODE = mkt.C_MKT_CODE AND c.C_P_CODE != '[root]')");
//		buf.append(" start with c.C_P_CODE = '[root]' connect by prior c.c_td_chan_code = c.C_P_CODE");
		return buf.toString();
	}
	
	private void getCommonQuerySqlBuf1(StringBuffer buf) {
		buf.append(" select c.* ");
		buf.append(" from (select '' C_IDEN, C_DV_CODE as c_dv_chan_type, ");
		buf.append(" '[root]' as C_P_CODE, ");
		buf.append(" C_DV_CODE as c_td_chan_code, ");
		buf.append(" C_DV_NAME as c_td_chan_name, ");
		buf.append(" '' as c_org_code,");
		buf.append(" '' as c_mkt_code, '' as c_desc, ");
		buf.append(" 1 as N_CHECK_STATE, '' as C_UPDATE_BY, ");
		buf.append(" '' as C_UPDATE_TIME, '' as C_CHECK_BY, ");
		//motified by lzj 20150310
		//bug108913 收支结转业务新增自动结转，新增过程系统报错。显示错误：无效列名c_ca_code
		buf.append(" '' as C_CHECK_TIME from v_s_dv_voc");
		buf.append(" where C_DV_TYPE = 'CHAN_TYPE' ");
		
		// add by Yuntao Lau 2015.11.18 BUG #122554
        buf.append(" UNION ALL ");		
		buf.append(" SELECT '' C_IDEN, 'CHAN_SEAT'  AS C_DV_CHAN_TYPE, ");
		buf.append(" 'CHAN_SEAT' AS C_P_CODE, ");
		buf.append(" C_DV_CODE AS C_TD_CHAN_CODE, ");
		buf.append(" C_DV_NAME AS C_TD_CHAN_NAME, ");
		buf.append(" '' AS C_ORG_CODE, ");
		buf.append(" '' AS C_MKT_CODE, '0' AS C_DESC, ");
		buf.append(" 1 AS N_CHECK_STATE, '' AS C_UPDATE_BY, ");
		buf.append(" '' AS C_UPDATE_TIME, '' AS C_CHECK_BY, ");
		buf.append(" '' AS C_CHECK_TIME FROM v_s_dv_voc");
		buf.append(" WHERE C_DV_CODE = 'ALL' ");
		
		buf.append(" union all ");
		buf.append(" select b.C_IDEN,b.c_dv_chan_type, ");
		buf.append(" b.c_dv_chan_type as c_p_code,");
		buf.append(" b.c_td_chan_code, b.c_td_chan_name, ");
		buf.append(" b.c_org_code, b.c_mkt_code, b.c_desc, ");
		buf.append(" b.n_check_state,b.c_update_by,b.c_update_time, ");
		//motified by lzj 20150310
		//bug108913 收支结转业务新增自动结转，新增过程系统报错。显示错误：无效列名c_ca_code
		buf.append(" b.c_check_by, b.c_check_time ");
		buf.append(" from t_p_ab_td_chan b ");
		buf.append(" where b.n_check_state = 1 and TO_DATE(b.C_CHECK_TIME,'yyyy-mm-dd hh24:mi:ss') >= to_date(?,'yyyy-mm-dd hh24:mi:ss')) c");
//		buf.append(" start with c.C_P_CODE = '[root]' connect by prior c.c_td_chan_code = c.C_P_CODE");
	}

	public String queryPortRelaTdChanSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		buf.append("  SELECT A.*,                                 ");
		buf.append("         B.C_PORT_CODE,                       ");
		buf.append("         B.C_RELA_TYPE,                       ");
		buf.append("         B.C_RELA_CODE,                       ");
		buf.append("         B.C_DV_TYPE_CODE,                    ");
		buf.append("         B.C_DESC         AS C_DESC_B,        ");
		buf.append("         B.N_CHECK_STATE  AS N_CHECK_STATE_B, ");
		buf.append("         B.C_UPDATE_BY    AS C_UPDATE_BY_B,   ");
		buf.append("         B.C_UPDATE_TIME  AS C_UPDATE_TIME_B, ");
		buf.append("         B.C_CHECK_BY     AS C_CHECK_BY_B,    ");
		buf.append("         B.C_CHECK_TIME   AS C_CHECK_TIME_B,  ");
		buf.append(" 		 B.C_IDEN         AS C_IEDN_B         "); 
		buf.append("    FROM T_P_AB_TD_CHAN A                     ");
		buf.append("    JOIN T_P_AB_PORT_RELA B                   ");
		buf.append("      ON A.C_TD_CHAN_CODE = B.C_RELA_CODE  ");
		//edit by gongyue 2017-4-27   南方基金回归测试在产品基本信息界面查询不到交易渠道
		//因为南方基金4.8版本T_P_AB_PORT_RELA没有C_MKT_CODE字段，所以迁移的数据没有交易市场所以关联不到，先注释，只根据渠道代码关联 
		//BUG #208084 【平安资产】产品关联信息中 只显示一个交易渠道的代码
		//因为21.6版本的交易渠道中没有关联交易市场，所以新增产品基本信息及交易渠道后，审核时只关联渠道代码，他会将渠道代码相同的不同交易市场的新增关联信息全部审核，所以把交易市场代码关联加上
		buf.append("      AND A.C_MKT_CODE = B.C_MKT_CODE     ");
		buf.append("   WHERE ");
		buf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));
		buf.append("         B.C_RELA_TYPE = 'RELA_TD_SEAT'       ");
		buf.append("     AND A.C_DV_CHAN_TYPE = 'CHAN_SEAT'       ");
		buf.append(" AND B.C_PORT_CODE IN  (SELECT * FROM TABLE(?)) ");

		return buf.toString();
	}
	
	public String queryPortRelaTdOrgSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		buf.append("  SELECT A.*,                                 ");
		buf.append("         B.C_PORT_CODE,                       ");
		buf.append("         B.C_RELA_TYPE,                       ");
		buf.append("         B.C_RELA_CODE,                       ");
		buf.append("         B.C_DV_TYPE_CODE,                    ");
		buf.append("         B.C_CA_CODE         AS C_DESC_B,        ");
		buf.append("         B.N_CHECK_STATE  AS N_CHECK_STATE_B, ");
		buf.append("         B.C_UPDATE_BY    AS C_UPDATE_BY_B,   ");
		buf.append("         B.C_UPDATE_TIME  AS C_UPDATE_TIME_B, ");
		buf.append("         B.C_CHECK_BY     AS C_CHECK_BY_B,    ");
		buf.append("         B.C_CHECK_TIME   AS C_CHECK_TIME_B,  ");
		buf.append(" 		 B.C_IDEN         AS C_IEDN_B         "); 
		buf.append("    FROM T_P_AB_TD_CHAN A                     ");
		buf.append("    JOIN T_P_AB_PORT_RELA B                   ");
		buf.append("      ON A.C_TD_CHAN_CODE = B.C_RELA_CODE     ");
		buf.append("   WHERE ");
		buf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));
		buf.append("         B.C_RELA_TYPE = 'RELA_QHGS'       ");
		buf.append("     AND A.C_DV_CHAN_TYPE = 'CHAN_ORG'       ");
		buf.append(" AND B.C_PORT_CODE IN  (SELECT * FROM TABLE(?)) ");

		return buf.toString();
	}

	public String getqueryPortRelaTdChanCountSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		buf.append("  SELECT COUNT(*) AS CNT                      ");
		buf.append("    FROM T_P_AB_TD_CHAN A                     ");
		buf.append("    JOIN T_P_AB_PORT_RELA B                   ");
		buf.append("      ON A.C_TD_CHAN_CODE = B.C_RELA_CODE         ");
		buf.append("   WHERE ");
		buf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));
		buf.append("         B.C_RELA_TYPE = 'RELA_TD_SEAT'        ");
		buf.append("     AND A.C_DV_CHAN_TYPE = 'CHAN_SEAT'         ");
		buf.append(" AND B.C_PORT_CODE IN  (SELECT * FROM TABLE(?)) ");

		return buf.toString();
	}
	
	public String getqueryPortRelaTdOrgCountSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		buf.append("  SELECT COUNT(*) AS CNT                      ");
		buf.append("    FROM T_P_AB_TD_CHAN A                     ");
		buf.append("    JOIN T_P_AB_PORT_RELA B                   ");
		buf.append("      ON A.C_TD_CHAN_CODE = B.C_RELA_CODE     ");
		buf.append("   WHERE ");
		buf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));
		buf.append("         B.C_RELA_TYPE = 'RELA_QHGS'        ");
		buf.append("     AND A.C_DV_CHAN_TYPE = 'CHAN_ORG'         ");
		buf.append(" AND B.C_PORT_CODE IN  (SELECT * FROM TABLE(?)) ");

		return buf.toString();
	}
	
	
	/**
	 * By Jinghehe 2015-9-29 
	 * 获取所有渠道数据，包括ALL 构造的数据
	 * @return
	 */
	public String getDataListByComm() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonSqlBuf(buf);

		buf.append(" WHERE ");
		buf.append(" C_DV_CHAN_TYPE");
		buf.append(" IN (SELECT * FROM TABLE(?)) ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	private void getCommonSqlBuf(StringBuffer buf) {
		buf.append(" select c.* ");
		buf.append(" from (select '' C_IDEN, C_DV_CODE as c_dv_chan_type, ");
		buf.append(" '[root]' as C_P_CODE, ");
		buf.append(" C_DV_CODE as c_td_chan_code, ");
		buf.append(" C_DV_NAME as c_td_chan_name, ");
		buf.append(" '' as c_org_code, ");
		buf.append(" '' as c_mkt_code, '' as c_desc, ");
		buf.append(" 1 as N_CHECK_STATE, '' as C_UPDATE_BY, ");
		buf.append(" '' as C_UPDATE_TIME, '' as C_CHECK_BY, ");
		buf.append(" '' as C_CHECK_TIME from v_s_dv_voc");
		buf.append(" where C_DV_TYPE = 'CHAN_TYPE' ");
		buf.append(" union all ");
		
		buf.append(" select '' C_IDEN, 'CHAN_SEAT'  as c_dv_chan_type, ");
		buf.append(" 'CHAN_SEAT' as C_P_CODE, ");
		buf.append(" C_DV_CODE as c_td_chan_code, ");
		buf.append(" C_DV_NAME as c_td_chan_name, ");
		buf.append(" '' as c_org_code, ");
		buf.append(" '' as c_mkt_code, '0' as c_desc, ");
		buf.append(" 1 as N_CHECK_STATE, '' as C_UPDATE_BY, ");
		buf.append(" '' as C_UPDATE_TIME, '' as C_CHECK_BY, ");
		buf.append(" '' as C_CHECK_TIME from v_s_dv_voc");
		buf.append(" where C_DV_CODE = 'ALL' ");
		
		buf.append(" union all ");
		buf.append(" select b.C_IDEN,b.c_dv_chan_type, ");
		buf.append(" b.c_dv_chan_type as c_p_code,");
		buf.append(" b.c_td_chan_code, b.c_td_chan_name, ");
		buf.append(" b.c_org_code, b.c_mkt_code, '1' as c_desc, ");
		buf.append(" b.n_check_state,b.c_update_by,b.c_update_time, ");
		buf.append(" b.c_check_by, b.c_check_time ");
		buf.append(" from t_p_ab_td_chan b ");
		buf.append(" where b.n_check_state = 1 ) c");
	}
	
	/*END 数据服务方法 */
	
	/**
	 * 组合关联的席位sql
	 * zhoushuhang 2016-4-7  在ETF补票日期界面中增加补票席位。通过选择组合带出组合对应补票席位。
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String queryPortRelaTradeSeatsql() throws Exception {
		StringBuffer buf = new StringBuffer();
		String sql = "";
		try {
			buf.append(" select * from T_P_AB_TD_CHAN chan ");
			buf.append(" where chan.c_td_chan_code in ");
			buf.append(" (select rela.c_rela_code ");
			buf.append(" from T_P_AB_PORT_RELA rela ");
			buf.append("  where rela.c_port_code = ? ");
			buf.append("  and rela.n_check_state = 1 ");
			buf.append("  and rela.c_rela_type = 'RELA_TD_SEAT') ");
			sql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
		}
		return sql;
	}
	public String getDataListByPorts() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonQuerySqlBuf(buf);

		buf.append(" WHERE ");
		buf.append(" C_P_CODE = '[root]' OR ");
		buf.append(" c_td_chan_code IN (SELECT C_RELA_CODE FROM T_P_AB_PORT_RELA WHERE C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND C_RELA_TYPE IN ('RELA_QHGS' ,'RELA_TD_SEAT')) ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
}
