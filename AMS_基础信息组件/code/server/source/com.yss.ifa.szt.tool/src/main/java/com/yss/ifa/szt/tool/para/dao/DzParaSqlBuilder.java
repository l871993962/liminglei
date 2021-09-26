package com.yss.ifa.szt.tool.para.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

public class DzParaSqlBuilder implements SQLBuilder {

	@Override
	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildInsertSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildSelectSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(DzParaColumnName.valueOf(name));
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(DzParaTableName.userInfo);
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select COUNT(*) AS CNT from T_D_ER_RELA A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}

		return buf.toString();
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" SELECT * FROM T_D_ER_RELA A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}

		return buf.toString();
	}

	public String getInitSql() {
		//源用户  wlx 20160907 STORY34149【广发证券】【紧急】申请修改电子对账支持多用户的需求
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT DISTINCT A.C_TARGET_USER, B.C_SRC_APPID AS C_SRC_APP_LOGO, B.C_PKG_PASSWORD, ");
		//STORY #60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
		builder.append(" A.C_TARGET_APP_LOGO, B.C_SRC_USERID AS C_TARGET_USER_LOGO,B.C_PARA_CODE,A.C_SECRETKEY,A.C_DV_SECRETTYPE,A.C_DV_CHARSET ");
		builder.append(" FROM T_D_ER_RELA A");
		builder.append(" JOIN T_D_ER_PARA B ON A.C_ERPARA_CODE = B.C_PARA_CODE ");
		return builder.toString();
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(DzParaTableName.recycle);
	}

	@Override
	public String getTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(DzParaTableName.userInfo);
	}

	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {

		for (String fieldedName : paraNameList) {
			if (fieldedName.equalsIgnoreCase("ARRAY_C_TGH_CODE")) {
				valueFieldbuf.append(" a.C_TGH_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}else if (fieldedName.equalsIgnoreCase("C_DEL_TIME_START")) {
				valueFieldbuf.append("a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_DEL_TIME_END")) {
				valueFieldbuf.append("a.C_DEL_TIME <= ? AND ");
			}else if (fieldedName.equalsIgnoreCase("ARRAY_C_PORT_CODE")) {
				valueFieldbuf.append("a.C_ASS_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}else if (fieldedName.equalsIgnoreCase("C_PORT_CODE")) {
				valueFieldbuf.append("a.C_ASS_CODE = ? AND ");
			}else if (fieldedName.equalsIgnoreCase("C_TGH_CODE")) {
				valueFieldbuf.append("a.C_TGH_CODE = ? AND ");
			}else if (fieldedName.equalsIgnoreCase("C_BUS_TYPE")) {
				valueFieldbuf.append("a.C_BUS_TYPE = ? AND ");
			}else if (fieldedName.equalsIgnoreCase("C_MANAGE_CODE")) {
				valueFieldbuf.append("a.C_MANAGE_CODE = ? AND ");
			}else if (fieldedName.equalsIgnoreCase("C_MANAGE_CODE_NULL")) {
				valueFieldbuf.append("trim(a.C_MANAGE_CODE) is null and 1 = ? AND ");
			}else if (fieldedName.equalsIgnoreCase("C_NO_ID")) {
				valueFieldbuf.append("a.C_IDEN != ? AND ");
			}
			
			
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}
	/**
	 * 新华资产根据根据账户获取对账参数
	 * @return
	 */
	public String getSqlByPort() {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT B.*, A.* FROM (");
		buf.append(" SELECT * FROM T_D_ER_RELA A WHERE c_ass_code = ? ");
		buf.append(" order by c_ass_code ");
		buf.append(" )A JOIN ");
		buf.append("(SELECT B.C_PARA_CODE,B.C_SRC_USERID,B.C_SRC_APPID,B.C_PKG_PASSWORD ");
		buf.append(" FROM T_D_ER_PARA B) B");
		buf.append(" ON A.C_ERPARA_CODE = B.C_PARA_CODE ");
		return buf.toString();
	}
	
	/**
	 * 新华资产根据根据账户获取对账参数
	 * @return
	 */
	public String getSqlByAccNo() {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT B.*, A.* FROM (");
		buf.append(" SELECT * FROM T_D_ER_RELA A WHERE c_ass_code = ? or");
		buf.append(" (c_tgh_code in (select distinct c_org_code from T_C_CP_FUND_ACC where c_open_acc_no = ?) and c_ass_code =' ') order by c_ass_code ");
		buf.append(" )A JOIN ");
		buf.append("(SELECT B.C_PARA_CODE,B.C_SRC_USERID,B.C_SRC_APPID,B.C_PKG_PASSWORD ");
		buf.append(" FROM T_D_ER_PARA B) B");
		buf.append(" ON A.C_ERPARA_CODE = B.C_PARA_CODE ");
		return buf.toString();
	}
	
	/**
	 * 根据托管行机构代码获得，关联参数
	 * @return
	 */
	public String getSqlByAccNo2() {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT B.*, A.* FROM (");
		buf.append(" SELECT A.* FROM T_D_ER_RELA A WHERE ");
		buf.append(" A.c_tgh_code = ? and A.c_ass_code = ' ' ");
		buf.append(" )A JOIN ");
		buf.append("(SELECT B.C_PARA_CODE,B.C_SRC_USERID,B.C_SRC_APPID,B.C_PKG_PASSWORD ");
		buf.append(" FROM T_D_ER_PARA B) B");
		buf.append(" ON A.C_ERPARA_CODE = B.C_PARA_CODE ");
		return buf.toString();
	}

	public String getSqlByAssCode() {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT B.*, A.* FROM (");
		buf.append(" SELECT A.* FROM T_D_ER_RELA A WHERE ");
		buf.append(" A.c_tgh_code in (SELECT R.C_RELA_CODE FROM T_P_AB_PORT_RELA R ");
		buf.append(" JOIN T_P_AB_PORT P ON R.C_PORT_CODE = P.C_PORT_CODE");
		buf.append(" AND R.C_RELA_TYPE = 'RELA_ORG' AND  P.C_ASS_CODE = ? )  ");
		buf.append(" )A JOIN ");
		buf.append("(SELECT B.C_PARA_CODE, B.C_SRC_USERID,B.C_SRC_APPID,B.C_PKG_PASSWORD ");
		buf.append(" FROM T_D_ER_PARA B) B");
		buf.append(" ON A.C_ERPARA_CODE = B.C_PARA_CODE ");
		return buf.toString();
	}

	/**
	 * 根据资产代码查询电子对账参数设置-资托互动
	 * add by chenyoucai  2018-9-13 STORY #30828 资管和托管互动
	 * @return
	 */
	public String getParaByAssCodeSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT B.*, A.* ");
		buf.append("  FROM (SELECT A.* ");
		buf.append("   FROM T_D_ER_RELA A ");
        buf.append("   JOIN (SELECT C_RELA_CODE ");
        buf.append("        FROM T_P_AB_PORT_RELA A ");
        buf.append("       WHERE A.C_PORT_CODE = (SELECT C_PORT_CODE FROM T_P_AB_PORT t WHERE C_ASS_CODE = ? )");
        buf.append("          AND A.C_RELA_TYPE = 'RELA_ORG' AND A.C_DV_TYPE_CODE = 'TRUSTEE') b");
        buf.append("    ON (B.C_RELA_CODE LIKE A.C_TGH_CODE || '%' )");
        buf.append("   OR (B.C_RELA_CODE = A.C_TGH_CODE )");
        buf.append("  WHERE A.C_ASS_CODE = ' '");
        buf.append("   AND A.N_CHECK_STATE = 1");
        buf.append("   AND A.C_BUS_TYPE = 'BUSI_ZTHD') A");
        buf.append("  JOIN (SELECT B.C_PARA_CODE,");
		buf.append("         B.C_SRC_USERID,");
        buf.append("         B.C_SRC_APPID,");
        buf.append("         B.C_PKG_PASSWORD,");
        buf.append("         B.C_CERT_ID");
        buf.append("    FROM T_D_ER_PARA B) B");
        buf.append("  ON A.C_ERPARA_CODE = B.C_PARA_CODE");
		return buf.toString();
	}

	/**
	 * 获取电子对账参数设置业务类型为电子对账的数量
	 * @return
	 */
	public String getUseCountSql() {
		return "select COUNT(*) AS CNT from T_D_ER_RELA A where A.C_BUS_TYPE = 'BUSI_DZ' ";
	}

	/**
	 * @param C_BUS_TYPE
	 * @param C_DV_LICORG
	 * @return
	 */
	public String getIsUsedLicOrgSql() {
		return " select C_IDEN from T_D_ER_RELA A where A.C_BUS_TYPE = ? and  A.C_DV_LICORG = ? ";
	}

	/**
	 * 根据ID获取参数的业务类型
	 * @return
	 */
	public String getBusiTypeByIdSql() {
		return " select A.C_BUS_TYPE from T_D_ER_RELA A where A.C_IDEN = ? ";
	}
}
