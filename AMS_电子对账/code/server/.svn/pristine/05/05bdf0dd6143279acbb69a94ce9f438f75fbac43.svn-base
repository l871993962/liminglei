package com.yss.uco.elecreco.er.erbbinfo.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;
import com.yss.uco.elecreco.er.cons.ErStateEnum;

public class ErBbInfoSqlBuilder implements SQLBuilder {

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
		return dbnameresolver.getColumnName(ErBbInfoColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(ErBbInfoTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(ErBbInfoTableName.bbinfo);
	}

	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select COUNT(*) AS CNT from T_D_ER_INFO A ");
		//STORY73476【鹏华基金】并行组合电子对账需求
		buf.append(" where A.C_FILE_TYPE in(select C_DZ_CODE from T_D_ER_DZ_TYPE WHERE C_DZ_CODE_P NOT IN ('[root]')) ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" AND ").append(valueFieldbuf);
		}
		buf.append(" order by a.n_check_state,a.c_update_time");
		return buf.toString();
	}

	public String getQuerySql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		//STORY54447反馈不一致结果人工对账一致处理需填写原因与说明信息
        //添加原因与说明字段
		//BUG #230669 【招商基金】电子对账反馈结果提示信息无法排序   cls 2018-11-24
		//将提示信息字段 中文【个】替代为空
		//buf.append(" SELECT DISTINCT A.C_IDEN,A.C_SN,A.C_DV_ER_WAY,A.C_ASS_CODE,A.C_FILE_TYPE,A.C_RPT_TYPE,A.C_STATE,A.N_CHECK_STATE,A.C_UPDATE_BY,A.C_UPDATE_TIME,A.C_CHECK_BY,A.D_DATE,A.C_CHECK_TIME,A.C_ERR_INFO,B.C_PORT_CODE, E.C_IDEN AS C_CONFIRM_ID,NVL(TRIM(E.C_EXECUTE), 'UNLOCK') AS C_CONFIRM_EXECUTE ");
		buf.append(" SELECT  A.C_TGH_CODE,A.C_SPLIT_CODE,A.C_IDEN,A.C_SN,A.C_DV_ER_WAY,A.C_ASS_CODE,A.C_FILE_TYPE,A.C_RPT_TYPE,A.C_STATE,A.N_CHECK_STATE,A.C_UPDATE_BY,A.C_UPDATE_TIME,A.C_CHECK_BY,A.D_DATE,A.C_CHECK_TIME, REPLACE(A.C_ERR_INFO,'个','') as C_ERR_INFO ,A.C_DV_RESULT,A.C_SUMMARY,a.C_PORT_CODE, E.C_IDEN AS C_CONFIRM_ID,  ");
		buf.append(" C_DEALER,  ");
		//STORY73476【鹏华基金】并行组合电子对账需求 去掉对组合表的关联
		buf.append("  CASE WHEN A.C_CONFIRM_EXECUTE IN ('LOCK','ECONFIRM')  OR E.C_EXECUTE IN('LOCK')  THEN '已锁定' ELSE '未锁定' END AS C_LOCK_EXECUTE, ");
		buf.append("  NVL(TRIM(E.N_CHECK_STATE), '0') AS N_JZAUDIT_STATE, ");
		buf.append("  NVL(TRIM(E.C_EXECUTE), 'UNLOCK') AS C_CONFIRM_EXECUTE ");
		buf.append(" FROM T_D_ER_INFO A ");
		buf.append(" LEFT JOIN T_E_CONFIRM  E ON E.C_BIZ_CLS = 'eConfirm' AND E.C_PORT_CODE = A.C_PORT_CODE AND E.D_BIZ_DATE = A.D_DATE ");
//		//STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
//		buf.append(" left join T_D_ER_SPLIT_RELA RELA on (RELA.C_PORT_CODE = B.C_PORT_CODE and A.C_FILE_TYPE in ('1031','1011') ) ");
		buf.append(" WHERE A.C_FILE_TYPE IN(SELECT C_DZ_CODE FROM T_D_ER_DZ_TYPE WHERE C_DZ_CODE_P NOT IN ('[root]')) ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" AND ").append(valueFieldbuf);
		}
		return buf.toString();
	}
	
	public String getQueryBDZSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT U.C_PORT_CODE,U.C_OPER_BY AS C_UPDATE_BY ,U.C_OPER_TIME AS C_UPDATE_TIME FROM T_D_ER_UN_PORT U");
		return buf.toString();
	}
	//STORY57791【鹏华基金】电子对账管理优化需求 其他对账分页数据总条数查询
	public String getQueryBDZCountSql(List<String> paraNameList) {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT COUNT(*) AS CNT FROM (").append(getQueryBDZSql(paraNameList)).append(")");
		return buf.toString();
	}
	
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		buf.append(getQuerySql(paraNameList));
		buf.append(" order by a.n_check_state asc ,a.c_update_time desc");
		return buf.toString();
	}

	public String getOrderBy(String satate){
		if(satate.contains("ER_ACCECPED")){
			return " order by C_CONFIRM_EXECUTE desc,C_IDEN desc ";
		}else if(satate.contains("ER_SENDED")){
			return " order by a.C_STATE DESC ,a.c_update_time desc";
		}else {
			return " order by C_CONFIRM_EXECUTE desc,C_IDEN desc ";
		}
	}
	
	public String getAllData(List<String> paraNameList, boolean isCount, String[] rptTypes) {
		StringBuffer buf = new StringBuffer();
		if (isCount) {
			buf.append("select count(*) AS CNT from (");
		} else {
			buf.append("select A.* from (");
		}
		buf.append(getQuerySql(paraNameList));
		buf.append(" union all ");
		buf.append(getQueryUnGeneSql(rptTypes,false));

		buf.append(") A ");
	    ////bug115217 weijj 20150707
		buf.append(" order by a.n_check_state asc , C_CONFIRM_EXECUTE desc,C_PORT_CODE asc");
		return buf.toString();
	}

	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * @param rptTypes
	 * @param isCount 是否是查询总条数
	 * @return
	 */
	public String getQueryUnGeneSql(String[] rptTypes,boolean isCount)
	{
		String order = "";
		StringBuffer buf = new StringBuffer();
		if(isCount)
		{
			buf.append(" select count(*) as CNT from ( ");
		}
		else
		{
			buf.append(" select A.* from ( ");
		}
		for(int i = 0 ; i < rptTypes.length; i++)
		{
			buf.append(getUnGeneSqlByRptType(rptTypes[i]));
			if(i != rptTypes.length-1 )
			{
				buf.append(" union all ");
			}
		}
		buf.append(") A ");
		//wlx 20180206 STORY52002嘉实基金-电子对账管理界面展示优化  查询数据根据前台勾选的组合先后顺序排序,对账类型第二排序，对账日期为第三排序
		buf.append(order);
		return buf.toString();
	}
	
	/**
	 * @return
	 */
	private String getUnGeneSqlByRptType(String rptType) {
		StringBuffer buf = new StringBuffer();
		//STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
		buf.append(" SELECT  ");
		buf.append(" RELA.C_TGH_CODE as C_TGH_CODE, ");
		buf.append(" RELA.C_SPLIT_CODE as C_SPLIT_CODE, ");
		buf.append(" ' ' AS C_IDEN, ");
		buf.append(" ' ' AS C_SN, ");
		buf.append(" 'FORWARD' AS C_DV_ER_WAY, ");
		buf.append(" PORT.C_ASS_CODE AS C_ASS_CODE, ");
		buf.append(" FT.C_FILE_TYPE  AS C_FILE_TYPE, ");
		buf.append(" '").append(rptType).append("'  AS C_RPT_TYPE, ");
		buf.append(" '未生成' AS C_STATE, ");
		buf.append(" 0 AS N_CHECK_STATE, ");
		buf.append(" ' ' AS C_UPDATE_BY, ");
		buf.append(" TO_CHAR(D.D_DATE,'YYYYMMDD') || ' 23:59:59' AS C_UPDATE_TIME, ");
		buf.append(" ' ' AS C_CHECK_BY, ");
		buf.append(" D.D_DATE AS D_DATE, ");
		buf.append(" TO_CHAR(D.D_DATE,'YYYYMMDD')  || ' 23:59:59' AS C_CHECK_TIME, ");
		buf.append(" ' ' AS C_ERR_INFO, ");
		buf.append(" ' ' AS C_DV_RESULT, ");
		buf.append(" ' ' AS C_SUMMARY, ");
		buf.append(" PORT.C_PORT_CODE AS C_PORT_CODE, ");
		buf.append(" ' ' AS C_CONFIRM_ID,'UNLOCK' AS C_CONFIRM_EXECUTE ");
		buf.append(" FROM (SELECT C_ASS_CODE,C_PORT_CODE,D_BUILD,D_CLOSE FROM T_P_AB_PORT where C_PORT_CODE IN (SELECT * FROM TABLE(?))) PORT ");
		buf.append(" JOIN (SELECT TO_DATE(COLUMN_VALUE,'YYYY-MM-DD') AS D_DATE FROM TABLE(?)) D");
		buf.append(" ON D_DATE BETWEEN PORT.D_BUILD AND PORT.D_CLOSE ");
		buf.append(" join ( SELECT COLUMN_VALUE AS C_FILE_TYPE FROM TABLE(?) ) FT on 1=1 ");
		//STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
		buf.append(" left join T_D_ER_SPLIT_RELA RELA on (RELA.C_PORT_CODE = PORT.C_PORT_CODE and FT.C_FILE_TYPE in ('1031','1011') ) ");
		//STORY73476【鹏华基金】并行组合电子对账需求 通过组合代码关联
		buf.append(" WHERE NOT EXISTS (SELECT 1 FROM T_D_ER_INFO A WHERE A.C_PORT_CODE = PORT.C_PORT_CODE ");
		buf.append(" AND PORT.C_PORT_CODE not in (SELECT UNPORT.C_PORT_CODE FROM T_D_ER_UN_PORT UNPORT) ");
		buf.append( "AND A.D_DATE = D.D_DATE AND C_FILE_TYPE = FT.C_FILE_TYPE AND C_RPT_TYPE= '").append(rptType).append("' )");
		return buf.toString();
	}

	/**
	 * 拼接电子对账信息修改sql
	 * 
	 * @return
	 */
	public String getDeleteSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" delete from T_D_ER_INFO a ");
		buf.append(" where a.C_SN = ? and a.C_ASS_CODE = ? and a.C_FILE_TYPE = ? and a.c_Rpt_Type = ? ");
		return buf.toString();
	}

	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		String checkStateStr = "";
		if (paraNameList.size() > 0) {
			checkStateStr = paraNameList.get(paraNameList.size() - 1);
		}

		if (checkStateStr.equalsIgnoreCase("SearchAll")) {
			valueFieldbuf.append(" a.n_check_state >= 0 AND ");
			paraNameList.remove(checkStateStr);
		} else if (checkStateStr.equalsIgnoreCase("SearchAudit")) {
			valueFieldbuf.append(" a.n_check_state = 1 AND ");
			paraNameList.remove(checkStateStr);
		} else if (checkStateStr.equalsIgnoreCase("SearchUnAudit")) {
			valueFieldbuf.append(" a.n_check_state = 0 AND ");
			paraNameList.remove(checkStateStr);
		}

		for (String fieldedName : paraNameList) {
			if (fieldedName.equalsIgnoreCase("ARRAY_C_PORT_CODE")) {
				//STORY #50374 电子对账功能优化 增加不对账分页
				//valueFieldbuf
				//		.append(" b.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
				valueFieldbuf
				//STORY54447反馈不一致结果人工对账一致处理需填写原因与说明信息,添加a.C_STATE='ER_ACCECPED' or
				//反馈一致中需要查询到其他对账中对账一致的信息
				//.append(" b.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND b.C_PORT_CODE  not in (SELECT U.C_PORT_CODE FROM T_D_ER_UN_PORT U ) AND ");
				.append("  a.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ( a.C_STATE='ER_ACCECPED' or a.C_PORT_CODE  not in (SELECT U.C_PORT_CODE FROM T_D_ER_UN_PORT U )) AND ");
			} else if (fieldedName.equalsIgnoreCase("C_DZ_CODE")) {
				valueFieldbuf.append(" A.C_FILE_TYPE = ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_RPT_TYPE")) {
				valueFieldbuf.append(" A.C_RPT_TYPE = ? AND ");
			} else if (fieldedName.equalsIgnoreCase("ARRAY_C_DZ_CODE")) {//STORY #67173【加急】【鹏华基金】为方便自动化流程管理，增加电子对账结果处理界面
				valueFieldbuf.append(" A.C_FILE_TYPE IN (SELECT * FROM TABLE(?)) AND ");
			}else if (fieldedName.equalsIgnoreCase("D_TRADE_START")) {
				valueFieldbuf
						.append(" A.D_DATE >= TO_DATE(?,'yyyy-MM-dd') AND ");
			} else if (fieldedName.equalsIgnoreCase("D_TRADE_END")) {
				valueFieldbuf
						.append(" A.D_DATE <= TO_DATE(?,'yyyy-MM-dd') AND ");
			} else if (fieldedName.equalsIgnoreCase("C_DEL_TIME_START")) {
				valueFieldbuf.append(" A.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_DEL_TIME_END")) {
				valueFieldbuf.append(" A.C_DEL_TIME <= ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_DV_ER_WAY")) {
				valueFieldbuf.append(" A.C_DV_ER_WAY = ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_STATE")) {
				valueFieldbuf.append(" A.C_STATE = ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_DV_SHOW")) {
				//////根据ID获取最后生成的数据，防止时间值改动后取错，导致发送数据不是最新的。weijj 20150610
				//////可能出现没发送的情况下，电话通知托管行返回。旧的数据时间值更新， 误把旧的当成新的。
				//STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）新增托管行和拆分代码
				valueFieldbuf
						.append(" not exists (select 1 from t_d_er_info b where to_number(a.c_iden) < to_number(b.c_iden) and nvl(b.c_tgh_code,' ') = nvl(a.c_tgh_code,' ') and nvl(b.c_split_code,' ') = nvl(a.c_split_code,' ')  and b.C_PORT_CODE = a.C_PORT_CODE and b.d_date = a.d_date and b.c_file_Type  = a.c_file_type and b.c_rpt_type = a.c_rpt_type and b.C_DV_ER_WAY = a.C_DV_ER_WAY and 1 = ? ) AND ");
				// //批量根据状态查询
			} else if (fieldedName.equalsIgnoreCase("ARRAY_C_STATE")) {
				valueFieldbuf
						.append(" A.C_STATE IN (SELECT * FROM TABLE(?)) AND ");
			}else if (fieldedName.equalsIgnoreCase("ARRAY_C_SN")) {
				valueFieldbuf.append(" A.C_SN IN (SELECT * FROM TABLE(?)) AND ");
			//STORY54447反馈不一致结果人工对账一致处理需填写原因与说明信息
			//添加查询条件：原因
			}else if (fieldedName.equalsIgnoreCase("C_DV_RESULT")) {
				valueFieldbuf.append(" A.C_DV_RESULT = ? AND ");
			}
			else if("C_DV_SHOW_RECE".equalsIgnoreCase(fieldedName)){//STORY57791【鹏华基金】电子对账管理优化需求  根据修改时间查询最后一次收到托管行反馈的数据
				valueFieldbuf.append(" NOT EXISTS (SELECT 1 FROM T_D_ER_INFO B WHERE A.C_UPDATE_TIME < B.C_UPDATE_TIME AND B.C_PORT_CODE = A.C_PORT_CODE AND B.D_DATE = A.D_DATE ")
						.append(" AND B.C_STATE IN('ER_ACCECPED','ER_IDENTICAL') AND B.C_FILE_TYPE  = A.C_FILE_TYPE AND B.C_RPT_TYPE = A.C_RPT_TYPE AND 1 = ? ) AND ");
			}

		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(ErBbInfoTableName.bbinfo);
	}
	/**
	 * 根据基金代码和日期获得每个文件类型的报文序号 key为文件类型，value 为报文序号
	 * BUG151528南方基金-工行发送月报电子对账，未处理1022对账结果报文
	 * 工行反馈结果没有回传报文序号，需要根据资产代码，报表类型，对账日期从T_D_ER_INFO获取对应的报文序号
	 */
	public String getSelectSqlBySN() {
		//wlx 20171027 BUG #177775 南方基金--工行电子对账估值表、余额表未接收到反馈结果，状态处于发送成功，但伺服器已收到反馈 
		//根据数据的IDEN最大值往小取，大的取到就不再取，而不是用报文序号的最大值
		//  BUG #188173 南方基金-工行电子对账报文未发送，但匹配到了对账一致的结果   --- 只取已经发送成功
		//BUG203839【南方基金】【紧急】我方发送一遍电子对账，对方在不同的时间反馈多次，我方只能匹配到第一次的反馈信息
		//BUG214516【招商基金】工行电子对账发送余额表，当托管行没有反馈1001报文直接反馈1022对账结果，系统没有解析出对应结果
		StringBuffer buf = new StringBuffer(" SELECT C_FILE_TYPE, C_SN, C_STATE  FROM T_D_ER_INFO ");
		buf.append(" WHERE D_DATE = to_date(?,'yyyyMMdd') AND C_ASS_CODE = ? ORDER BY DECODE(C_STATE,'ER_SENDED_SECCUSS',1,'ER_SENDED',2,'ER_IDENTICAL', 3, 4), TO_NUMBER(C_IDEN) DESC ");
		return buf.toString();
	}

	// // 修改状态时，对账不一致的不要修改
	public String getUpdateStatusSql(String C_STATE) {
		StringBuffer buf = new StringBuffer(" UPDATE T_D_ER_INFO ");
		buf.append(" SET C_STATE = ?,C_ERR_INFO = ?,C_UPDATE_TIME = ? WHERE C_SN = ? AND C_FILE_TYPE = ?  AND C_ASS_CODE = ? ");
		// //不发送数据，托管行也可能返回数据，造成无法修改状态
		if (C_STATE.equalsIgnoreCase("ER_IDENTICAL") || C_STATE.equalsIgnoreCase("ER_ACCECPED")) {
			return buf.toString();
		} else {
			buf.append(" AND C_STATE <> 'ER_IDENTICAL'");
		}
		return buf.toString();
	}

	public String getUpdateStatusSql() {
		StringBuffer buf = new StringBuffer(" UPDATE T_D_ER_INFO ");
		buf.append(" SET C_STATE = ?,C_ERR_INFO = ?,C_UPDATE_TIME = ? WHERE C_SN = ? AND C_FILE_TYPE = ?  AND C_ASS_CODE = ?")
		//BUG #215439 发送电子对账数据返回结果有误 这里确定优先级
		.append(" and decode(?,'"+ErStateEnum.ER_SEND+"',1,'"+ErStateEnum.ER_SENDED+"',2,'"+ErStateEnum.ER_SENDED_FAIL+"',2,'"+ErStateEnum.ER_SENDED_SECCUSS+"',3,'"+ErStateEnum.ER_ACCECPED+"',4,'"+ErStateEnum.ER_IDENTICAL+"',4,0)")
		.append(" >= decode(C_STATE,'"+ErStateEnum.ER_SEND+"',1,'"+ErStateEnum.ER_SENDED+"',2,'"+ErStateEnum.ER_SENDED_FAIL+"',2,'"+ErStateEnum.ER_SENDED_SECCUSS+"',3,'"+ErStateEnum.ER_ACCECPED+"',4,'"+ErStateEnum.ER_IDENTICAL+"',4,0)");
		return buf.toString();
	}

	public String getUpdateStatusSql2() {
		StringBuffer buf = new StringBuffer(getUpdateStatusSql());
		buf.append(" AND C_STATE NOT IN ('ER_ACCECPED','ER_IDENTICAL')");
		return buf.toString();
	}

	/**
	 * 修改状态为已发送 如果状态为已生成和发送失败和已发送时，可以修改状态
	 * 
	 * @return
	 */
	public String getUpdateStatusSql3() {
		StringBuffer buf = new StringBuffer(getUpdateStatusSql());
		buf.append(" AND C_STATE IN ('ER_SEND','ER_SENDED_FAIL','ER_SENDED') ");
		return buf.toString();
	}

	public String getSelectSqlById() {
		StringBuffer buf = new StringBuffer(
				" SELECT A.*,' ' AS C_PORT_CODE,' ' as C_CONFIRM_ID ,'UNLOCK' as C_CONFIRM_EXECUTE FROM T_D_ER_INFO A");
		buf.append(" WHERE C_IDEN = ? ");
		return buf.toString();
	}
	
	public String getSelectSqlByIds() {
		StringBuffer buf = new StringBuffer(
				" SELECT A.*,' ' AS C_PORT_CODE, ' ' AS C_CONFIRM_ID, 'UNLOCK' AS C_CONFIRM_EXECUTE FROM T_D_ER_INFO A");
		buf.append(" WHERE A.C_IDEN IN (SELECT * FROM TABLE(?)) ");
		return buf.toString();
	}

	// ///根据业务主键查询
	public String getSelectSqlByIdx() {
		StringBuffer buf = new StringBuffer(
				" SELECT A.*,' ' AS C_PORT_CODE, ' ' AS C_CONFIRM_ID, 'UNLOCK' AS C_CONFIRM_EXECUTE FROM T_D_ER_INFO A");
		buf.append(" WHERE A.C_FILE_TYPE||','||A.C_ASS_CODE||','||A.C_SN IN (SELECT * FROM TABLE(?)) AND C_DV_ER_WAY = 'FORWARD' ");
		return buf.toString();
	}
	
	/**
	 * 根据报文序号获取数据
	 * @return
	 */
	public String getBbInfoBySnSql() {
		return " select C_IDEN,C_SN,C_ASS_CODE,C_FILE_TYPE,C_RPT_TYPE,C_STATE,N_CHECK_STATE,D_DATE,C_ERR_INFO,C_DV_RESULT,C_SUMMARY,C_SPLIT_CODE,C_TGH_CODE,C_PORT_CODE from T_D_ER_INFO where C_SN = ? ";
	}
}
