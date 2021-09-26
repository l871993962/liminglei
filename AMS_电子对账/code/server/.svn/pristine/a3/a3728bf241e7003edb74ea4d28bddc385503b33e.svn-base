package com.yss.uco.elecreco.er.autostate.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

public class AutoStateSqlBuilder implements SQLBuilder {

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
		return dbnameresolver.getColumnName(AutoStateColumnName.valueOf(s));
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return dbnameresolver.getTableName(AutoStateTableName.Table);
	}

	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		
		buf.append(" select COUNT(*) AS CNT from( ");
		buf.append(getQueryConditionSql(paraNameList));
		buf.append(" ) ");
		
		return buf.toString();
	}
	
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		
		this.setWhereSql(valueFieldbuf, paraNameList);
		
		buf.append("SELECT A.C_IDEN,A.C_PROCESS_ID,A.C_TASK_ID,A.C_PORT_CODE,A.C_FILE_TYPE,A.C_SN,A.C_STATE FROM T_D_ER_AUTOSTATE A ");
		
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		return buf.toString();
	}

	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {

		for (String fieldedName : paraNameList) {
			if (fieldedName.equalsIgnoreCase("C_PORT_CODE")) {
				valueFieldbuf.append(" A.C_PORT_CODE = ? AND ");
			} 
			else if (fieldedName.equalsIgnoreCase("C_FILE_TYPE")) {
				valueFieldbuf.append(" A.C_FILE_TYPE = ? AND ");
			}
			else if (fieldedName.equalsIgnoreCase("C_STATE")) {
				valueFieldbuf.append(" A.C_STATE = ? AND ");
			}
			else if (fieldedName.equalsIgnoreCase("C_PROCESS_ID")) {
				valueFieldbuf.append(" A.C_PROCESS_ID = ? AND ");
			}
			else if (fieldedName.equalsIgnoreCase("C_TASK_ID")) {
				valueFieldbuf.append(" A.C_TASK_ID = ? AND ");
			}
			else if (fieldedName.equalsIgnoreCase("C_SN")) {
				valueFieldbuf.append(" A.C_SN = ? AND ");
			} 
			else if (fieldedName.equalsIgnoreCase("C_ASS_CODE")) {
				valueFieldbuf.append(" A.C_PORT_CODE = (SELECT DISTINCT C_PORT_CODE FROM T_P_AB_PORT WHERE C_ASS_CODE = ?) AND ");
			} 

		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(AutoStateTableName.Table);
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 通过一条电子对账发送数据查询其他相同任务的发送记录
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @author wulongxing 
	 * @date 2018年4月30日 下午11:02:25
	 */
	public String getSameProcess() {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT A.* FROM T_D_ER_AUTOSTATE A ");
		builder.append("JOIN (SELECT C_IDEN,C_PROCESS_ID,C_TASK_ID,C_PORT_CODE,C_FILE_TYPE,C_SN,C_STATE ");
		builder.append(" FROM T_D_ER_AUTOSTATE WHERE C_SN = ? AND C_FILE_TYPE = ? AND C_PORT_CODE = ");
		builder.append(" (SELECT C_PORT_CODE FROM T_P_AB_PORT WHERE C_ASS_CODE = ?)) B ");
		builder.append("ON A.C_PROCESS_ID = B.C_PROCESS_ID AND A.C_TASK_ID = B.C_TASK_ID");
		
		return builder.toString();
	}

	public String updateState() {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE T_D_ER_AUTOSTATE A SET A.C_STATE = ?  ");
		////STORY73476【鹏华基金】并行组合电子对账需求
		builder.append(" WHERE C_SN = ? AND C_FILE_TYPE = ? ");
//		builder.append(" WHERE C_SN = ? AND C_FILE_TYPE = ? AND C_PORT_CODE = ");
//		builder.append(" (SELECT C_PORT_CODE FROM T_P_AB_PORT WHERE C_ASS_CODE = ?)");
			
		return builder.toString();
	}
}
