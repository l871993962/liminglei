package com.yss.uco.elecreco.er.erresult.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

public class ErResultSqlBuilder implements SQLBuilder {

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
		return dbnameresolver.getColumnName(ErResultColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(ErResultTableName.recycle);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(ErResultTableName.userInfo);
	}

	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select COUNT(*) AS CNT from T_D_ER_RESULT A ");
		//STORY73476【鹏华基金】并行组合电子对账需求
//		buf
//				.append(" join (select c_port_code, c_ass_code from t_p_ab_port) b on a.c_ass_code = b.c_ass_code ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}

		return buf.toString();
	}

	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select A.* from T_D_ER_RESULT A ");
		// buf.append(" join (select c_port_code, c_ass_code from t_p_ab_port) b on a.c_ass_code = b.c_ass_code ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		
		buf.append("  order by A.c_B_code"); ///根据本方科目代码排序 by weijj 20151219 bug124277 
		return buf.toString();
	}

	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {

		for (String fieldedName : paraNameList) {
			//STORY73476【鹏华基金】并行组合电子对账需求 与电子对账管理表做关联
			if (fieldedName.equalsIgnoreCase("ARRAY_C_PORT_CODE")) {// BUG149528BUG单-主干大版本功能测试第二轮BUG汇总2-对账 功能代码:dzResult 功能名称:浏览对账结果 错误信息：查询报错
				valueFieldbuf
						.append(" A.C_ASS_CODE IN (SELECT C_ASS_CODE FROM T_D_ER_INFO WHERE C_PORT_CODE IN (SELECT * FROM TABLE(?))) AND ");
			} else if (fieldedName.equalsIgnoreCase("D_TRADE_START")) {
				valueFieldbuf
						.append("A.D_START_DATE >= to_date(?,'yyyy-MM-dd') AND ");
			} else if (fieldedName.equalsIgnoreCase("D_TRADE_END")) {
				valueFieldbuf
						.append("a.D_END_DATE <= to_date(?,'yyyy-MM-dd') AND ");
			} else if (fieldedName.equalsIgnoreCase("C_SN")) {
				valueFieldbuf.append("a.C_SN = ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_RPT_TYPE")) {
				valueFieldbuf.append("a.C_RPT_TYPE = ? AND ");
			}else if (fieldedName.equalsIgnoreCase("C_FILE_TYPE")) {
				valueFieldbuf.append("a.C_FILE_TYPE = ? AND ");
			}else if (fieldedName.equalsIgnoreCase("C_CHECK_FLAG")) {
				valueFieldbuf.append("a.C_CHECK_FLAG = ? AND ");
			}else if (fieldedName.equalsIgnoreCase("C_ASS_CODE")) {
				valueFieldbuf.append("a.C_ASS_CODE = ? AND ");
			}else if("ARRAY_C_B_CODE".equalsIgnoreCase(fieldedName))
			{
				valueFieldbuf.append(" A.C_B_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	/**
	 * 根据业务主键删除
	 * modified by liyanjun 2016-6-13   BUG #128388 电子对账历史不一致数据查看不到
	 * C_REF_NO字段不同日期可能会重复，导致错删数据
	 * @return
	 */
	public String getDeleteBySNCode() {
		StringBuffer buf = new StringBuffer("DELETE FROM ");
		buf.append(ErResultTableName.userInfo);
		//wlx 20160831 BUG139336【招商财富】电子对账反馈出现新问题 
		//原因分析：多次反馈的数据C_REF_NO值逐渐变小，上一次的就删除不了 故删除条件不加C_REF_NO
		buf.append(" WHERE C_SN = ? AND C_ASS_CODE = ? AND C_CHECK_FLAG = ?");
		return buf.toString();
	}
	
	
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * @return
	 */
	public String getSnByPortWithSplitRelaSql() {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT C_TGH_CODE FROM T_D_ER_RELA R ");
		sb.append(" where R.C_BUS_TYPE = 'BUSI_DZ' ");
		sb.append(" and R.C_TARGET_USER = ?  ");
		sb.append(" and R.C_TARGET_APP_LOGO = ? ");
		return sb.toString();
	}

	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * @return
	 */
	public String getSplitSnMapByAssCodeOrSplitCodeSql() {
		StringBuffer sb = new StringBuffer();
		sb.append(" select C_TGH_CODE,C_SN,C_STATE,C_IDEN from T_D_ER_INFO a");
		sb.append(" where (a.c_ass_code = ? or a.c_split_code = ?) ");
		sb.append(" and a.c_file_type = ? ");
		sb.append(" and a.c_rpt_type = ? ");
		sb.append(" and a.d_date = to_date(?,'yyyyMMdd') ");
		sb.append(" ORDER BY DECODE(C_STATE,'ER_SENDED_SECCUSS',1,'ER_SENDED',2,'ER_IDENTICAL', 3, 4), TO_NUMBER(C_IDEN) DESC ");
		return sb.toString();
	}

	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * @return
	 */
	public String getSubOrgSql() {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT C_ORG_CODE FROM T_P_BI_ORG R ");
		sb.append(" start with R.C_ORG_CODE_P = ? ");
		sb.append(" connect by prior R.C_ORG_CODE = R.C_ORG_CODE_P ");
		return sb.toString();
	}
}
