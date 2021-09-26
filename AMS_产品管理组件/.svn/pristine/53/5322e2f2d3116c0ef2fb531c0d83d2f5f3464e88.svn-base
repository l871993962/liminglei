package com.yss.ams.product.information.modules.pg.portgroup.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * <群组管理>SQL语句构造类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortGroupSqlBuilder implements SQLBuilder {

	@Override
	public String buildInsertSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildSelectSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(PortGroupTableName.clIndex);
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(PortGroupColumnName.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(PortGroupTableName.recycle);
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
//		StringBuffer buf = new StringBuffer();
//	    buf.append("select a.* from T_P_AB_GROUP a  ");
//	    buf.append("  where a.N_CHECK_STATE >= 0 start with a.C_GROUP_CODE_P ='[root]' ");
//	    buf.append(" connect by prior a.C_GROUP_CODE = a.C_GROUP_CODE_P order by a.N_CHECK_STATE ASC  ");

		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append("select a.* from T_P_AB_GROUP a  ");
		
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		
	    return buf.toString();
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			for (String fieldName : paraNameList) {
				if ("C_GROUP_CODE_P".equals(fieldName)) {
					valueFieldbuf.append(" A.C_GROUP_CODE_P = ? AND ");
				} else if ("C_GROUP_CODE".equals(fieldName)) {
					valueFieldbuf.append(" A.C_GROUP_CODE = ? AND ");
				}
			}

			if (valueFieldbuf.length() > 0) {
				StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
			}

			buf.append(" select count(*) AS CNT ");
			buf
					.append(" from T_V_D_GROUP_DETAIL b join (SELECT C_GROUP_CODE_P,C_GROUP_CODE FROM T_V_D_GROUP A ");

			if (valueFieldbuf.length() > 0) {
				buf.append(" START WITH ").append(valueFieldbuf);
				buf
						.append(" connect by prior a.C_GROUP_CODE = a.C_GROUP_CODE_P ");
			}

			buf.append(") C ON B.C_GROUP_CODE = C.C_GROUP_CODE  ");
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
		}
		return retSql;
	}
	
	/**
	 * 获取群组A区数据
	 * 
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getPortGroupA(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT A.*, A.C_GROUP_CODE AS NODECODE, A.C_GROUP_CODE AS FPARAENTCODE, 1 AS N_LEVEL ");
		buf.append("   FROM T_P_AB_GROUP A WHERE A.N_CHECK_STATE >= 0  ");
		buf.append("  ORDER BY A.N_CHECK_STATE ASC ");
		return buf.toString();
	}
	
	/**
	 * 查询群组列表（树形结构）
	 * @param paraNameList
	 * @return
	 * @throws Exception
	 */
	public String getPortGroupListData(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
//		buf.append("SELECT A.*, A.C_GROUP_CODE AS NODECODE, '[root]' AS FPARAENTCODE, 1 AS N_LEVEL ");
//		buf.append("   FROM T_P_AB_GROUP A WHERE A.N_CHECK_STATE >= 0 START WITH A.C_GROUP_CODE_P = '[root]' ");
//		buf.append(" CONNECT BY PRIOR A.C_GROUP_CODE = A.C_GROUP_CODE_P ORDER BY A.N_CHECK_STATE ASC ");
		buf.append(" SELECT A.* FROM T_P_AB_GROUP A WHERE A.N_CHECK_STATE >= 0 ORDER BY A.N_CHECK_STATE ASC ");
		return buf.toString();
	}
	
	public String getCheckGroupCode(){
		return " SELECT * FROM T_P_AB_PORT T WHERE T.C_PORT_CODE = ? ";
	}
	
	/**
	 * 派工单 #2333 估值_V1.300.7.0_UI自动化测试_自动化测试(272)
	 * @return
	 */
	public String getCheckGroupCode(String str){
		
		return " SELECT 1 FROM T_P_AB_GROUP A WHERE A.C_GROUP_CODE = ? AND A.C_IDEN != ? UNION ALL  SELECT 1 FROM T_P_AB_PORT T WHERE T.C_PORT_CODE = ?";
	}
	
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));


		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_GROUP_CODE")) {
				valueFieldbuf.append(" A.C_GROUP_CODE = ?  AND ");
			}else if(fieldedName.equals("C_GROUP_CODE_P")){
				valueFieldbuf.append(" A.C_GROUP_CODE_P = ?  AND ");
			}
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String getPlanRelaPortGroupAddSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" select B.* FROM t_p_Ab_Group B WHERE B.N_CHECK_STATE = 1 ");
		buf.append(" AND NOT EXISTS (SELECT 1 FROM T_E_EXEC_PLAN_RELA A ");
		buf.append(" WHERE A.N_CHECK_STATE >= 0 AND A.C_PORT_CODE = B.C_GROUP_CODE ");
		buf.append(" AND A.C_PLAN_TYPE = ? AND C_PLAN_CODE = ? ");
		buf.append(" AND ((TO_DATE(?, 'yyyy-MM-dd') BETWEEN D_BEGIN AND D_END) OR ");
		buf.append(" (TO_DATE(?, 'yyyy-MM-dd') BETWEEN D_BEGIN AND D_END) OR ");
		buf.append(" (TO_DATE(?, 'yyyy-MM-dd') < D_BEGIN AND ");
		buf.append(" TO_DATE(?, 'yyyy-MM-dd') > D_END))) ");
//		buf.append(" and B.C_GROUP_CODE IN (SELECT DISTINCT c_port_code ");
//		buf.append(" FROM (SELECT t1.c_port_code FROM T_S_USER_RELA t1 ");
//		buf.append(" WHERE t1.C_POST_CODE = ? AND t1.C_USER_CODE = ? ");
//		buf.append(" AND T1.N_CHECK_STATE = 1 UNION ALL ");
//		buf.append(" SELECT t2.c_port_code FROM T_S_BAIL t2 ");
//		buf.append(" WHERE t2.C_POST_CODE = ? AND t2.C_USER_CODE = ? ");
//		buf.append(" AND SYSDATE BETWEEN t2.d_start AND t2.d_end ");
//		buf.append(" AND T2.N_CHECK_STATE = 1)) ");
		return buf.toString();
	}

	public String getPlanRelaPortGroupBrowSql() {
		return "SELECT A.* FROM T_P_AB_GROUP A WHERE a.C_GROUP_CODE = ? ";
	}

	public String getAllDataListSql() {
		return "SELECT A.* FROM T_P_AB_GROUP A WHERE a.N_CHECK_STATE =1 ";
	}
	
	/**
	 * 根据群组代码查询群组信息
	 * src：STORY #62048 新增加的组合自动关联自动化估值方案
	 * author：shijian@ysstech.com
	 * date：2018年10月22日
	 */
	public String getGroupByCode() {
		return "SELECT A.* FROM T_P_AB_GROUP A WHERE a.N_CHECK_STATE = 1 and a.C_GROUP_CODE IN (SELECT * FROM TABLE(?)) ";
	}

}
