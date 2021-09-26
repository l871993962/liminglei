package com.yss.uco.elecreco.er.reverse.manager.info.dao;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.util.DateUtil;
public class ErReveInfoSqlBuilder implements SQLBuilder  {

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
		return dbnameresolver.getColumnName(ErReveInfoColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(ErReveInfoTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(ErReveInfoTableName.table);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(ErReveInfoTableName.table);
	}

	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select COUNT(*) AS CNT from T_D_ER_REVE_INFO A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		return buf.toString();
	}

	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select A.* from T_D_ER_REVE_INFO A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append(" ORDER BY A.N_CHECK_STATE DESC  ");
		return buf.toString();
	}
	
	public String getGenerateSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(getInfoTableSql());
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		//oracle union 不能在此排序 
		//buf.append(" ORDER BY A.N_CHECK_STATE DESC  ");
		return buf.toString();
	}
	
	public String getQueryConditionSql(List<String> paraNameList,HashMap<String, Object> paras,String[] rptTypes) throws Exception {
	
		Object fileTypeObj = paras.get("C_FILE_TYPE");
		Object startObj = paras.get("D_TRADE_START");
		Object endObj = paras.get("D_TRADE_END");
		Date startDate = DateUtil.stringtoDate(startObj.toString(),"yyyy-MM-dd");
		Date endDate = DateUtil.stringtoDate(endObj.toString(),"yyyy-MM-dd");
		
		
		String unGenerateSql = "";
		String generateSql = "";
		StringBuffer sb = new StringBuffer();
		generateSql = this.getGenerateSql(paraNameList);
		sb.append(generateSql);
		if(isSelectUngenerate(paras))
		{
			for (String rptType : rptTypes) {
				String dateKey = "ARRAY_D_DATE_"+rptType;
				String[] fileTypes = getFileTypesByRptType(rptType,fileTypeObj);
				String reportDate = getDatesWithRptType(startDate, endDate, rptType);
				paras.put(dateKey, reportDate);
				for(String fileType : fileTypes)
				{
					sb.append(" union all ");
					unGenerateSql = this.unGenerateSql(rptType, fileType);
					paraNameList.add("ARRAY_C_PORT_CODE");
					paraNameList.add(dateKey);
					sb.append(unGenerateSql);
				}
			}
		}
		
		return sb.toString();
		
	}
	
	private boolean isSelectUngenerate(HashMap<String, Object> paraMap) {
		if(paraMap.containsKey("C_FK_STATE_YFK"))
		{
			return false;
		}
		if(paraMap.containsKey("C_DV_DZ_RESULT"))
		{
			if(!"RDZ_RESULT_NO".equalsIgnoreCase((String)paraMap.get("C_DV_DZ_RESULT")))
			{
				return false;
			}
		}
		if(paraMap.containsKey("ARRAY_C_PORT_CODE"))
		{
			if(String.valueOf(paraMap.get("ARRAY_C_PORT_CODE")).trim().length()<=0)
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 根据报表类型获取需要生成报表的日期
	 * @param startDate
	 * @param endDate
	 * @param rptType
	 * @return
	 */
	private String getDatesWithRptType(Date startDate,Date endDate,String rptType)
	{
		//List<String> list = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		if("01".equalsIgnoreCase(rptType))//日报
		{
			while(startDate.compareTo(endDate)<=0)
			{
				//list.add(DateUtil.dateToString(startDate, "yyyy-MM-dd"));
				sb.append(DateUtil.dateToString(startDate, "yyyy-MM-dd")).append(",");
				startDate = DateUtil.nextDay(startDate,1);
			}
		}else if("03".equalsIgnoreCase(rptType))//月报
		{
			while(startDate.compareTo(endDate)<=0)
			{
				//list.add(DateUtil.dateToString(startDate, "yyyy-MM-dd"));
				sb.append(DateUtil.dateToString(startDate, "yyyy-MM-dd")).append(",");
				startDate = DateUtil.nextMonth(startDate, 1);
			}
		}else if("04".equalsIgnoreCase(rptType))//季报
		{
			Date endDateQ = DateUtil.getFirstNatureDayOnQuarter(endDate);
			Date startDateQ = DateUtil.getFirstNatureDayOnQuarter(startDate);
			while(startDateQ.compareTo(endDateQ)<=0)
			{
				//list.add(DateUtil.dateToString(startDate, "yyyy-MM-dd"));
				sb.append(DateUtil.dateToString(startDate, "yyyy-MM-dd")).append(",");
				startDate = DateUtil.nextMonth(startDate,3);
				startDateQ = DateUtil.getFirstNatureDayOnQuarter(startDate);
			}
		}else if("05".equalsIgnoreCase(rptType))//半年报
		{
			Date endDateQ = DateUtil.getFirstNatureDayOnHalfYear(endDate);
			Date startDateQ = DateUtil.getFirstNatureDayOnHalfYear(startDate);
			while(startDateQ.compareTo(endDateQ)<=0)
			{
				//list.add(DateUtil.dateToString(startDate, "yyyy-MM-dd"));
				sb.append(DateUtil.dateToString(startDate, "yyyy-MM-dd")).append(",");
				startDate = DateUtil.nextMonth(startDate,6);
				startDateQ = DateUtil.getFirstNatureDayOnHalfYear(startDate);
			}
		}else if("06".equalsIgnoreCase(rptType))//年报
		{
			Date endDateQ = DateUtil.getFirstNatureDayOnYear(endDate);
			Date startDateQ = DateUtil.getFirstNatureDayOnYear(startDate);
			while(startDateQ.compareTo(endDateQ)<=0)
			{
				//list.add(DateUtil.dateToString(startDate, "yyyy-MM-dd"));
				sb.append(DateUtil.dateToString(startDate, "yyyy-MM-dd")).append(",");
				startDate = DateUtil.nextMonth(startDate,12);
				startDateQ = DateUtil.getFirstNatureDayOnYear(startDate);
			}
		}
		//return list;
		if(sb.toString().endsWith(","))
		{
			return sb.substring(0, sb.length()-1);
		}
		return sb.toString();
	}
	
	private String[] getFileTypesByRptType(String rptType, Object fileTypeObj) {
		if(fileTypeObj != null)
		{
			return new String[]{String.valueOf(fileTypeObj)};
		}
		if("01".equalsIgnoreCase(rptType))
		{
			return new String[]{"1011","1001","1031"};
		}else
		{
			return new String[]{"1701","1801","1711","1811","1901"};
		}
	}

	private void setWhereSql(StringBuffer valueFieldbuf,List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));
		for (String fieldedName : paraNameList) {
			if (fieldedName.equalsIgnoreCase("ARRAY_C_PORT_CODE")) {
				valueFieldbuf.append(" A.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equalsIgnoreCase("D_TRADE_START")) {//
				valueFieldbuf.append(" A.D_DATE >= to_date(?, 'yyyy-MM-dd') AND ");
			}else if (fieldedName.equalsIgnoreCase("D_TRADE_END")) {//
				valueFieldbuf.append(" A.D_DATE <= to_date(?, 'yyyy-MM-dd') AND ");
			}
			else if (fieldedName.equalsIgnoreCase("C_FILE_TYPE")) {//
				valueFieldbuf.append(" A.C_FILE_TYPE = ? AND ");
			}
			else if (fieldedName.equalsIgnoreCase("C_FK_STATE_YFK")) {//
				valueFieldbuf.append(" A.N_FK_STATE > ? AND ");
			}else if (fieldedName.equalsIgnoreCase("C_FK_STATE_WFK")) {//
				valueFieldbuf.append(" A.N_FK_STATE <= ? AND ");
			}
			else if (fieldedName.equalsIgnoreCase("C_DV_DZ_RESULT")) {//
				valueFieldbuf.append(" A.C_DV_DZ_RESULT = ? AND ");
			}
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}
	
	/**
	 * 现在只有日报01，估值表1011
	 * @param rptType
	 * @param fileType
	 * @return
	 */
	public String unGenerateSql(String rptType,String fileType)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT '' as C_IDEN,C_PORT_CODE, ");
		sb.append(" '' as C_SN, ");
		sb.append(" '"+fileType+"' ").append("  as C_FILE_TYPE,D_DATE, ");
		sb.append(" '"+rptType+"' ").append("  as C_RPT_TYPE, ");
		//sb.append(" '01' as C_RPT_TYPE, ");
		sb.append(" 'REVERSE' as C_DV_ER_WAY, ");
		sb.append(" 'RLS_SDZT_BSD' as C_DV_LOCK_STATE, ");
		sb.append(" '未反馈' as C_FK_STATE, ");
		sb.append(" '' as C_REMARK, ");
		sb.append(" '' as C_UPDATE_BY, ");
		sb.append(" '' as C_UPDATE_TIME, ");
		sb.append(" 0 as N_CHECK_STATE, ");
		sb.append(" '' as C_DV_HANDLE_STATE,'' as C_HANDLE_INFO, ");
		sb.append(" 'RDZ_RESULT_NO' as C_DV_DZ_RESULT, ");
		//sb.append(" case when exists ( select 1 from t_r_fr_aststat ast where ast.c_port_code = a.c_port_code and ast.d_aststat = c.D_DATE ) then '1' else '0' end as B_DATA, ");
		sb.append(getBfData(rptType, fileType));
		sb.append(getDfNowSize(rptType, fileType));
		sb.append(" ||'/'|| ");
		sb.append(" (SELECT count(1) as df_total FROM (SELECT c_tgh_code,c_port_code FROM t_d_er_reve_ass_map where c_file_type = '").append(fileType).append("' and n_check_state = '1'  group by(c_tgh_code,c_port_code)) where c_port_code = a.c_port_code )");
		sb.append(" as C_D_DATA ");
		sb.append(" FROM (SELECT COLUMN_VALUE as C_PORT_CODE FROM table(?)) a, ");
		sb.append(" (SELECT TO_DATE(COLUMN_VALUE,'YYYY-MM-DD') as D_DATE FROM table(?)) c ");
		sb.append(" where not exists ( ").append(removeRealData(rptType, fileType)).append(" )");
		return sb.toString();
	}
	
	private String removeRealData(String rptType,String fileType)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(" select 1 from T_D_ER_REVE_INFO R ");
		sb.append(" where R.C_PORT_CODE = a.C_PORT_CODE ");
		sb.append(" and R.C_FILE_TYPE = '").append(fileType).append("' ");
		sb.append(" and R.D_DATE = c.D_DATE ");
		sb.append(" and R.C_RPT_TYPE = '").append(rptType).append("' ");
		return sb.toString();
	}
	
	private String getInfoTableSql()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT C_IDEN,C_PORT_CODE,C_SN,C_FILE_TYPE,D_DATE,C_RPT_TYPE,C_DV_ER_WAY,C_DV_LOCK_STATE,  ");
		sb.append(" case when N_FK_STATE <=0 then '未反馈' else '已反馈('||N_FK_STATE||')' end as C_FK_STATE, ");
		sb.append(" C_REMARK,C_UPDATE_BY,C_UPDATE_TIME,N_CHECK_STATE,C_DV_HANDLE_STATE,C_HANDLE_INFO,C_DV_DZ_RESULT, ");
		//已经对过账的，本方数据和对方数据都展示完成√
//		sb.append(" case when C_FILE_TYPE = '1011' and exists ( select 1 from t_r_fr_aststat ast where ast.c_port_code = a.c_port_code and ast.d_aststat = A.D_DATE ) then cast('√' as varchar(20)) ");
//		sb.append(" when C_FILE_TYPE = '1031' and exists ( select 1 from T_F_SC_KM Aa,T_E_EXEC_PLAN_RELA bb where bb.C_PLAN_Type = 'AO_LEVEL' and bb.n_check_state = 1 and bb.C_PORT_CODE = a.c_port_code and bb.d_begin <= a.D_DATE and bb.d_end >= a.D_DATE  and aa.c_plan_code = bb.c_plan_code and aa.n_check_state = 1 and aa.c_km_code not like '%>'  and aa.n_order = 0  ) then cast('√' as varchar(20)) ");
//		sb.append(" else cast('×' as varchar(20))");
//		sb.append(" case when C_FILE_TYPE = '1001' and exists ( select 1 from T_D_Ai_Stock where C_PORT_CODE = a.c_port_code and D_STOCK = c.D_DATE ) then cast('√' as varchar(20)) ");
//		sb.append(" end as C_B_DATA, ");
//		
//		sb.append(" case when C_FILE_TYPE = '1011' then ");
//		sb.append(" (SELECT count(1) as df_nowsize FROM (SELECT c_ass_code, c_tgh_code, d_gz_date FROM t_d_od_gz_out group by (c_ass_code, c_tgh_code, d_gz_date)) where c_ass_code in (select c_port_code_out FROM t_d_er_reve_ass_map where c_port_code = a.c_port_code) and d_gz_date = A.d_date) ");
//		sb.append(" ||'/'||  ");
//		sb.append(" (SELECT count(1) as df_total FROM (SELECT c_tgh_code, c_port_code FROM t_d_er_reve_ass_map where n_check_state = '1' group by (c_tgh_code, c_port_code)) where c_port_code = a.c_port_code) ");
//		sb.append(" else '0/0' end as C_D_DATA  ");
		sb.append(" cast('√' as varchar(20)) as C_B_DATA, ");
		sb.append(" cast('√' as varchar(20)) as C_D_DATA  ");
		sb.append(" FROM  T_D_ER_REVE_INFO A ");
		return sb.toString();
	}
	
	private String getBfData(String rptType,String fileType)
	{
		StringBuffer sb = new StringBuffer();
		if("1011".equalsIgnoreCase(fileType))//估值表
		{
			sb.append(" case when exists ( select 1 from t_r_fr_aststat ast where ast.c_port_code = a.c_port_code and ast.d_aststat = c.D_DATE ) then cast('√' as varchar(20)) else cast('×' as varchar(20)) end as C_B_DATA, ");
		}else if("1001".equalsIgnoreCase(fileType))//余额表
		{
			sb.append(" case when exists ( select 1 from T_D_Ai_Stock where C_PORT_CODE = a.c_port_code and D_STOCK = c.D_DATE ) then cast('√' as varchar(20)) else cast('×' as varchar(20)) end as C_B_DATA, ");
		}else if("1031".equalsIgnoreCase(fileType))//科目表
		{
			sb.append(" case when exists ( select 1 from T_F_SC_KM Aa,T_E_EXEC_PLAN_RELA bb where bb.C_PLAN_Type = 'AO_LEVEL' and bb.n_check_state = 1 and bb.C_PORT_CODE = a.c_port_code and bb.d_begin <= c.D_DATE and bb.d_end >= c.D_DATE  and aa.c_plan_code = bb.c_plan_code and aa.n_check_state = 1 and aa.c_km_code not like '%>'  and aa.n_order = 0 ) then cast('√' as varchar(20)) else cast('×' as varchar(20)) end as C_B_DATA, ");
		}else if("1701".equalsIgnoreCase(fileType) || "1711".equalsIgnoreCase(fileType))//资产负债表
		{
			sb.append(" cast('×' as varchar(20)) as C_B_DATA, ");
		}else if("1801".equalsIgnoreCase(fileType) || "1811".equalsIgnoreCase(fileType))//利润表
		{
			sb.append(" cast('×' as varchar(20)) as C_B_DATA, ");
		}else if("1901".equalsIgnoreCase(fileType))//所有者权益（基金净值）变动表
		{
			sb.append(" cast('×' as varchar(20)) as C_B_DATA, ");
		}
		return sb.toString();
	}
	

	/**
	 * 目前只有估值和科目
	 * @param rptType
	 * @param fileType
	 * @return
	 */
	private String getDfNowSize(String rptType,String fileType)
	{
		StringBuffer sb = new StringBuffer();
		if("1011".equalsIgnoreCase(fileType))//估值表
		{
			sb.append(" ( SELECT count(1) as df_nowsize FROM (SELECT c_ass_code,c_tgh_code,d_gz_date FROM t_d_od_gz_out group by (c_ass_code, c_tgh_code,d_gz_date)) km ,t_d_er_reve_ass_map ass where km.c_tgh_code = ass.c_tgh_code and km.d_gz_date = c.D_DATE and km.c_ass_code = ass.c_port_code_out and ass.c_port_code = a.c_port_code and ass.N_CHECK_STATE = 1 and ass.c_file_type = '").append(fileType).append("' )  ");
		}else if("1001".equalsIgnoreCase(fileType))//余额表
		{
			sb.append(" ( SELECT count(1) as df_nowsize FROM (SELECT c_ass_code,c_tgh_code,d_gz_date FROM t_d_od_ye_out group by (c_ass_code, c_tgh_code,d_gz_date)) km ,t_d_er_reve_ass_map ass where km.c_tgh_code = ass.c_tgh_code and km.d_gz_date = c.D_DATE and km.c_ass_code = ass.c_port_code_out and ass.c_port_code = a.c_port_code and ass.N_CHECK_STATE = 1 and ass.c_file_type = '").append(fileType).append("' )  ");
		}else if("1031".equalsIgnoreCase(fileType))//科目表
		{
			sb.append(" ( SELECT count(1) as df_nowsize FROM (SELECT c_ass_code,c_tgh_code FROM t_d_od_km_out group by (c_ass_code, c_tgh_code)) km ,t_d_er_reve_ass_map ass where km.c_tgh_code = ass.c_tgh_code and km.c_ass_code = ass.c_port_code_out and ass.c_port_code = a.c_port_code and ass.N_CHECK_STATE = 1 and ass.c_file_type = '").append(fileType).append("' )  ");
		}else if("1701".equalsIgnoreCase(fileType) || "1711".equalsIgnoreCase(fileType))//资产负债表
		{
			sb.append(" ('0') ");
		}else if("1801".equalsIgnoreCase(fileType) || "1811".equalsIgnoreCase(fileType))//利润表
		{
			sb.append(" ('0') ");
		}else if("1901".equalsIgnoreCase(fileType))//所有者权益（基金净值）变动表
		{
			sb.append(" ('0') ");
		}
		return sb.toString();
	}

	public String getUpdateLockStateSql() {
		StringBuffer sb = new StringBuffer();
		sb.append(" update T_D_ER_REVE_INFO ");
		sb.append(" set C_DV_LOCK_STATE = ? ");
		sb.append(" where C_IDEN = ? ");
		return sb.toString();
	}

	public String getUpdateDzResultSql() {
		StringBuffer sb = new StringBuffer();
		sb.append(" update T_D_ER_REVE_INFO ");
		sb.append(" set C_DV_DZ_RESULT = ? , ");
		sb.append(" C_REMARK = ? ");
		sb.append(" where C_IDEN = ? ");
		return sb.toString();
	}
	
}