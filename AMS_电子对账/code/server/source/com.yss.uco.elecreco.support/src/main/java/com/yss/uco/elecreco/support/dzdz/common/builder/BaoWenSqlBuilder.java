package com.yss.uco.elecreco.support.dzdz.common.builder;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.uco.elecreco.support.dzdz.common.IBaoWenSqlBuilder;
import com.yss.uco.elecreco.support.dzdz.common.pojo.XmlRootColumnName;



public class BaoWenSqlBuilder implements IBaoWenSqlBuilder{

	/*没被调用到，废弃  wlx 20160907*/
//	public String getInitSql() {
//		////包密码后面几位是随机数字，干扰解密用的，所以要过滤掉
//		/////添加通道类型  by weijj STORY #24165 20150715 
//		return "select distinct C_TARGET_USER,C_SRC_APP_LOGO,substr(C_PKG_PASSWORD,1,16) AS C_PKG_PASSWORD,C_TARGET_APP_LOGO,nvl(B.C_TARGET_USER_LOGO,'') as C_TARGET_USER_LOGO from T_D_ER_RELA where C_COMM_TYPE = 'SZT'";
//	}
	
	/**
	 * STORY73476【鹏华基金】并行组合电子对账需求 去掉资产代码到组合代码的关联
	 */
	@Override
	public String getRootSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT");
		////20161124 WLX STORY35853关联机构需要增加一个“直连对账”的标识给   优先取机构资质为‘电子对账’的
		buf.append(" CASE WHEN B.C_DV_TYPE_CODE = 'ELEC_RECONCILIATION' THEN 1 ELSE 2 END AS N_ROWNUM,");
		//STORY #55269 【富国基金】支持电子对账参数设置支持多管理人
		buf.append(" CASE WHEN trim(B.C_MANAGE_CODE) is null THEN 2 ELSE 1 END AS N_MANAGER_ROWNUM, ");
		buf.append(" C.C_STATE, ");
		buf.append(" C.C_ERR_INFO, ");
		buf.append(" C.C_FILE_TYPE, ");
		buf.append(" C.C_ASS_CODE AS C_ASS_CODE,");
		buf.append(" C.C_RPT_TYPE AS C_RPT_TYPE,");
		buf.append(" TO_CHAR(C.D_DATE,'YYYYMMDD') AS D_START_DATE,");
		buf.append(" TO_CHAR(C.D_DATE,'YYYYMMDD') AS D_END_DATE,");
		buf.append(" B.C_DEPT_CODE AS C_DEPT_CODE,");
		buf.append(" B.C_CERT_ID AS C_CERT_ID,");
		buf.append(" C.C_SN AS C_SN,");
		buf.append(" B.C_TARGET_USER, B.C_TARGET_APP_LOGO,");
		buf.append(" B.C_SRC_APPID AS C_SRC_APP_LOGO, B.C_PKG_PASSWORD, ");
		buf.append(" B.C_SRC_USERID AS C_TARGET_USER_LOGO,");// wlx 20160907 STORY34149【广发证券】【紧急】申请修改电子对账支持多用户的需求
		buf.append(" B.C_PARA_CODE, ");
		//STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
		buf.append(" B.C_SECRETKEY, ");
		buf.append(" B.C_DV_SECRETTYPE, ");
		buf.append(" B.C_DV_CHARSET, ");
		buf.append(" B.C_COMM_TYPE AS C_COMM_TYPE");
		buf.append(" FROM T_D_ER_INFO C");
		/*STORY #33252 深证通电子对账、电子指令参数需要分开配置 
		 * 电子对账的发送时只取电子对账参数设置*/
		//STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
		buf.append(" JOIN (SELECT B.C_PORT_CODE,B.C_SECRETKEY,B.C_DV_SECRETTYPE,B.C_DV_CHARSET,B.C_DV_TYPE_CODE,B.C_TARGET_USER,B.C_TARGET_APP_LOGO,B.C_COMM_TYPE,B.C_DEPT_CODE,D.C_PARA_CODE,D.C_SRC_USERID,D.C_SRC_APPID,D.C_PKG_PASSWORD,B.C_CERT_ID,");
		buf.append(" B.C_MANAGE_CODE FROM ( SELECT R1.*,P1.C_DV_TYPE_CODE,P1.C_PORT_CODE FROM ");
		buf.append(" (select C_IDEN,C_ASS_CODE,C_DEPT_CODE,C_CERT_ID,C_TGH_CODE,C_TGH_NAME,  ");
		buf.append(" C_BUS_TYPE,C_COMM_TYPE,C_TARGET_USER,C_TARGET_USER_LOGO,C_TARGET_APP_LOGO,C_SRC_APP_LOGO,C_PKG_PASSWORD,C_DZ_MODE,N_CHECK_STATE, ");
	    buf.append(" C_UPDATE_BY,C_UPDATE_TIME,C_CHECK_BY,C_CHECK_TIME,C_GZB_MODE,C_MR_PORT,C_MR_IP,C_HIGH_TIME,C_HAS_BRANCH,C_INTERVAL,C_ERPARA_CODE, ");
	    buf.append(" C_MANAGE_CODE,C_KM_NAME_LENGTH,C_DV_CHARSET,C_SECRETKEY,C_DV_SECRETTYPE,C_DV_LICORG ");
	    buf.append(" from T_D_ER_RELA where c_bus_type = 'BUSI_DZ') R1 ");
		buf.append(" JOIN ( "); 
		//STORY #55269 【富国基金】支持电子对账参数设置支持多管理人
		buf.append(" SELECT A.*,B.C_RELA_CODE as C_MANAGER FROM T_P_AB_PORT_RELA A left join ");
		buf.append(" (SELECT C_PORT_CODE,C_RELA_CODE FROM T_P_AB_PORT_RELA  where C_RELA_TYPE = 'RELA_ORG' and C_DV_TYPE_CODE = 'MANAGER') B ");
		buf.append(" on A.C_PORT_CODE = B.C_PORT_CODE and A.C_RELA_TYPE = 'RELA_ORG' and A.C_PORT_CODE = ? ");
		buf.append(" ) P1"); 
		//STORY #55269 【富国基金】支持电子对账参数设置支持多管理人
		buf.append(" ON P1.C_RELA_CODE = R1.C_TGH_CODE and (P1.C_MANAGER = R1.C_MANAGE_CODE or trim(R1.C_MANAGE_CODE) is null )) B "); 
		buf.append(" JOIN (SELECT D.C_PARA_CODE,D.C_SRC_USERID,D.C_SRC_APPID,D.C_PKG_PASSWORD FROM T_D_ER_PARA D) D");
		buf.append("  ON B.C_ERPARA_CODE = D.C_PARA_CODE");
		
		buf.append(" ) B ON B.C_PORT_CODE = C.C_PORT_CODE ");
		buf.append(" WHERE C.C_SN = ? AND C.C_FILE_TYPE = ?  AND C.C_PORT_CODE = ? AND C.C_DV_ER_WAY = 'FORWARD'");
		//STORY #55269 【富国基金】支持电子对账参数设置支持多管理人
		//buf.append(" ORDER BY N_ROWNUM ASC ");
		buf.append(" ORDER BY N_ROWNUM ASC,N_MANAGER_ROWNUM ASC ");
		
		return buf.toString();
	}
	
	/**
	 * STORY73476【鹏华基金】并行组合电子对账需求 去掉资产代码到组合代码的关联
	 */
	@Override
	public String getDzParaSql() {
		StringBuffer buf = new StringBuffer();
		buf.append("      SELECT                                                                                   ");
		buf.append("                                                                                               ");
		buf.append("       CASE                                                                                    ");
		buf.append("         WHEN B.C_DV_TYPE_CODE = 'ELEC_RECONCILIATION' THEN                                    ");
		buf.append("          1                                                                                    ");
		buf.append("         ELSE                                                                                  ");
		buf.append("          2                                                                                    ");
		buf.append("       END AS N_ROWNUM,                                                                        ");
		buf.append("       C.C_STATE,                                                                              ");
		buf.append("       C.C_ERR_INFO,                                                                           ");
		buf.append("       C.C_FILE_TYPE,                                                                          ");
		buf.append("       C.C_ASS_CODE AS C_ASS_CODE,                                                             ");
		buf.append("       C.C_RPT_TYPE AS C_RPT_TYPE,                                                             ");
		buf.append("       TO_CHAR(C.D_DATE, 'YYYYMMDD') AS D_START_DATE,                                          ");
		buf.append("       TO_CHAR(C.D_DATE, 'YYYYMMDD') AS D_END_DATE,                                            ");
		buf.append("       B.C_DEPT_CODE AS C_DEPT_CODE,                                                           ");
		buf.append("       B.C_CERT_ID AS C_CERT_ID,                                                               ");
		buf.append("       C.C_SN AS C_SN,                                                                         ");
		buf.append("       B.C_TARGET_USER,                                                                        ");
		buf.append("       B.C_TARGET_APP_LOGO,                                                                    ");
		buf.append("       B.C_SRC_APPID AS C_SRC_APP_LOGO,                                                        ");
		buf.append("       B.C_PKG_PASSWORD,                                                                       ");
		buf.append("       B.C_SRC_USERID AS C_TARGET_USER_LOGO,                                                   ");
		buf.append("       B.C_PARA_CODE,                                                                          ");
		buf.append("       B.C_COMM_TYPE AS C_COMM_TYPE,                                                           ");
		//STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
		buf.append("       B.C_SECRETKEY ,B.C_DV_SECRETTYPE ,B.C_DV_CHARSET ,                                                            ");
		buf.append("       B.CN                                                                                    ");
		buf.append("        FROM T_D_ER_INFO C                                                                     ");
		buf.append("        JOIN (SELECT P3.C_PORT_CODE,                                                                 ");
		buf.append("                     P3.C_DV_TYPE_CODE,                                                        ");
		buf.append("                     P3.CN,                                                                    ");
		buf.append("                     CASE WHEN M.C_MANAGER = R1.C_MANAGE_CODE THEN 1 " );
		buf.append("                          WHEN trim(R1.C_MANAGE_CODE) is null THEN 2" );
		buf.append("                          ELSE 3 END AS N_MANAGER_NUM,");
		buf.append("                     R1.C_TARGET_USER,                                                         ");
		//STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
		buf.append("                     R1.C_TARGET_APP_LOGO,R1.C_SECRETKEY,R1.C_DV_SECRETTYPE,R1.C_DV_CHARSET,                                                     ");
		buf.append("                     R1.C_DEPT_CODE, R1.C_CERT_ID,                                             ");
		buf.append("                     R1.C_COMM_TYPE,                                                           ");
		buf.append("                     B.*                                                                       ");
		buf.append("                FROM T_D_ER_RELA R1                                                            ");
		buf.append("                JOIN(WITH P1 AS (SELECT R.C_RELA_CODE,                                         ");
		buf.append("                                       R.C_DV_TYPE_CODE,                                       ");
		buf.append("                                       R.C_PORT_CODE                                   ");
		buf.append("                                  FROM T_P_AB_PORT_RELA R where R.C_PORT_CODE = ? )            ");
		buf.append("              SELECT P2.CN AS CN, P2.C_ORG_CODE, P1.C_PORT_CODE, P1.C_DV_TYPE_CODE                   ");
		buf.append("                FROM (SELECT C_ORG_CODE, level as CN, ? AS C_PORT_CODE                          ");
		buf.append("                        FROM T_P_BI_ORG                                                        ");
		buf.append("                      /*现在已经限制一个组合只能关联一个托管行，所以这里等于就可以*/           ");
		buf.append("                       START WITH C_ORG_CODE IN (SELECT C_RELA_CODE FROM P1)                    ");
		buf.append("                      CONNECT BY PRIOR C_ORG_CODE_P = C_ORG_CODE) P2                           ");
		buf.append("                JOIN P1 ON P1.C_PORT_CODE = P2.C_PORT_CODE) P3 ON P3.C_ORG_CODE = R1.C_TGH_CODE AND R1.C_HAS_BRANCH = '1' ");
		buf.append("                LEFT JOIN (SELECT R.C_RELA_CODE as C_MANAGER FROM T_P_AB_PORT_RELA R ");
		buf.append("              WHERE R.C_RELA_TYPE = 'RELA_ORG' and C_DV_TYPE_CODE = 'MANAGER'  AND R.C_PORT_CODE = ?) M ");
		buf.append("                   ON M.C_MANAGER = R1.C_MANAGE_CODE ");
		buf.append("                JOIN (SELECT B.C_PARA_CODE,                                                    ");
		buf.append("                             B.C_SRC_USERID,                                                   ");
		buf.append("                             B.C_SRC_APPID,                                                    ");
		buf.append("                             B.C_PKG_PASSWORD                                                  ");
//		buf.append("                             B.C_CERT_ID                                                       ");
		buf.append("                        FROM T_D_ER_PARA B) B ON R1.C_ERPARA_CODE =                            ");
		buf.append("                                                 B.C_PARA_CODE                                 ");
		buf.append("               WHERE R1.C_BUS_TYPE = 'BUSI_DZ') B ON B.C_PORT_CODE = C.C_PORT_CODE                    ");
		buf.append("       WHERE C.C_SN = ?                                                                        ");
		buf.append("         AND C.C_FILE_TYPE = ?                                                                 ");
		buf.append("         AND C.C_PORT_CODE = ?                                                                  ");
		buf.append("         AND C.C_DV_ER_WAY = 'FORWARD'                                                         ");
		buf.append("       ORDER BY CN, N_MANAGER_NUM, N_ROWNUM ASC                                                               ");
		return buf.toString();
	}
	
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
		String sql = "update TDzbbinfo set fissend=2 where  fsn = ? and fissend < 2";
		return sql;
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(XmlRootColumnName.valueOf(name));
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * STORY73476【鹏华基金】并行组合电子对账需求 去掉资产代码到组合代码的关联
	 */
	public String getRootSqlByReportType(String tableName) {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT");
		buf.append(" C.C_STATE, ");
		buf.append(" C.C_ERR_INFO, ");
		buf.append(" C.C_FILE_TYPE, ");
		buf.append(" C.C_ASS_CODE AS C_ASS_CODE,");
		buf.append(" C.C_RPT_TYPE AS C_RPT_TYPE,");
		buf.append(" D_START_DATE,");
		buf.append(" D_END_DATE,");
		buf.append(" B.C_DEPT_CODE AS C_DEPT_CODE,");
		buf.append(" B.C_CERT_ID AS C_CERT_ID,");
		buf.append(" C.C_SN AS C_SN,");
		buf.append(" B.C_TARGET_USER, B.C_TARGET_APP_LOGO,");
		//STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
		buf.append(" B.C_SECRETKEY,B.C_DV_SECRETTYPE,B.C_DV_CHARSET, ");
		buf.append(" B.C_SRC_APP_LOGO,B.C_PKG_PASSWORD, ");
		buf.append(" nvl(B.C_TARGET_USER_LOGO,'') as C_TARGET_USER_LOGO,");// wlx 20160907 STORY34149【广发证券】【紧急】申请修改电子对账支持多用户的需求
		buf.append(" B.C_MR_IP,B.C_MR_PORT,");
		buf.append(" B.C_COMM_TYPE AS C_COMM_TYPE");
		buf.append(" FROM T_D_ER_INFO C");
		buf.append(" JOIN (SELECT R1.*,P1.C_PORT_CODE FROM T_D_ER_RELA R1 JOIN (SELECT  R.* "); 
		buf.append(" FROM T_P_AB_PORT_RELA R"); 
		buf.append(" where R.C_PORT_CODE = ?"); 
		buf.append(" AND R.C_RELA_TYPE = 'RELA_ORG') P1"); 
		buf.append(" ON P1.C_RELA_CODE = R1.C_TGH_CODE) B ON B.C_PORT_CODE = C.C_PORT_CODE "); 
		buf.append(" JOIN (SELECT DISTINCT C_SN,C_FILE_TYPE,C_ASS_CODE,C_RPT_TYPE,D_START_DATE,D_END_DATE FROM ");
		buf.append(tableName);
		buf.append(" ) T ");
		buf.append(" ON T.C_SN=C.C_SN AND T.C_FILE_TYPE=C.C_FILE_TYPE AND T.C_ASS_CODE=C.C_ASS_CODE AND T.C_RPT_TYPE=C.C_RPT_TYPE ");
		buf.append(" WHERE C.C_SN = ? AND C.C_FILE_TYPE = ?  AND C.C_PORT_CODE = ? AND C.C_RPT_TYPE = ? AND C.C_DV_ER_WAY = 'FORWARD'");
		return buf.toString();
	}

	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * 通过资产代码获取管理机构
	 * STORY73476【鹏华基金】并行组合电子对账需求 资产代码换为组合代码
	 * @return
	 */
	public String getManagerByPortCodeSql() {
		StringBuffer sb = new StringBuffer();
		sb.append(" select R.C_RELA_CODE as C_MANAGE_CODE ");
		sb.append(" from ");
		sb.append(" T_P_AB_PORT_RELA R ");
		sb.append(" where C_RELA_TYPE = 'RELA_ORG' and C_DV_TYPE_CODE = 'MANAGER' and C_PORT_CODE = ? ");
		return sb.toString();
	}

	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * 通过机构代码，获取上级机构
	 * @return
	 */
	public String getParentOrgsSql() {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT C_ORG_CODE as C_TGH_CODE,CN FROM  ");
		sb.append(" ( SELECT C_ORG_CODE,C_ORG_NAME, level as CN FROM T_P_BI_ORG  where C_ORG_CODE != ? ");
		sb.append(" START WITH C_ORG_CODE = ? CONNECT BY PRIOR C_ORG_CODE_P = C_ORG_CODE ");
		sb.append("  ) A ");
		sb.append(" join ");
		sb.append(" ( select C_TGH_CODE from T_D_ER_RELA  where c_bus_type = 'BUSI_DZ' and C_HAS_BRANCH = '1' and N_CHECK_STATE >= 1 ) R ");
		sb.append(" on A.C_ORG_CODE = R.C_TGH_CODE ");
		sb.append(" order by CN asc ");
		return sb.toString();
	}

	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * 获取发送参数
	 * STORY73476【鹏华基金】并行组合电子对账需求 资产代码换为组合代码
	 * @param isFiterManager 是否过滤管理人
	 * @return
	 */
	public String getSplitRootSql(boolean isFiterManager) {
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT ");
		sb.append(" C.C_STATE, ");
		sb.append(" C.C_ERR_INFO, ");
		sb.append(" C.C_FILE_TYPE, ");
		sb.append(" C.C_ASS_CODE AS C_ASS_CODE, ");
		sb.append(" C.C_RPT_TYPE AS C_RPT_TYPE, ");
		sb.append(" TO_CHAR(C.D_DATE, 'YYYYMMDD') AS D_START_DATE, ");
		sb.append(" TO_CHAR(C.D_DATE, 'YYYYMMDD') AS D_END_DATE, ");
		sb.append(" B.C_DEPT_CODE AS C_DEPT_CODE, ");
		sb.append(" B.C_CERT_ID AS C_CERT_ID, ");
		sb.append(" C.C_SN AS C_SN, ");
		sb.append(" B.C_TARGET_USER, ");
		sb.append(" B.C_TARGET_APP_LOGO, ");
		sb.append(" B.C_SRC_APPID AS C_SRC_APP_LOGO, ");
		sb.append(" B.C_PKG_PASSWORD, ");
		sb.append(" B.C_SRC_USERID AS C_TARGET_USER_LOGO, ");
		sb.append(" B.C_PARA_CODE, ");
		//STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
		sb.append(" B.C_SECRETKEY, B.C_DV_SECRETTYPE, B.C_DV_CHARSET,  ");
		sb.append(" B.C_COMM_TYPE AS C_COMM_TYPE ");
		sb.append(" FROM T_D_ER_INFO C ");
		sb.append(" JOIN (SELECT  ");
		//STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
		sb.append(" B.C_SECRETKEY, B.C_DV_SECRETTYPE, B.C_DV_CHARSET,  ");
		sb.append(" B.C_TGH_CODE, B.C_TARGET_USER, B.C_TARGET_APP_LOGO, B.C_COMM_TYPE, B.C_DEPT_CODE, ");
		sb.append(" D.C_PARA_CODE, D.C_SRC_USERID, D.C_SRC_APPID, D.C_PKG_PASSWORD, ");
		sb.append(" B.C_CERT_ID, B.C_MANAGE_CODE, ");
		sb.append(" ? as C_PORT_CODE ");
		sb.append(" FROM ( select C_IDEN,C_ASS_CODE,C_DEPT_CODE,C_CERT_ID,C_TGH_CODE,C_TGH_NAME,  ");
		
		sb.append(" C_BUS_TYPE,C_COMM_TYPE,C_TARGET_USER,C_TARGET_USER_LOGO,C_TARGET_APP_LOGO,C_SRC_APP_LOGO,C_PKG_PASSWORD,C_DZ_MODE,N_CHECK_STATE, ");
		sb.append(" C_UPDATE_BY,C_UPDATE_TIME,C_CHECK_BY,C_CHECK_TIME,C_GZB_MODE,C_MR_PORT,C_MR_IP,C_HIGH_TIME,C_HAS_BRANCH,C_INTERVAL,C_ERPARA_CODE, ");
		sb.append(" C_MANAGE_CODE,C_KM_NAME_LENGTH,C_DV_CHARSET,C_SECRETKEY,C_DV_SECRETTYPE,C_DV_LICORG ");
		sb.append(" from T_D_ER_RELA where c_bus_type = 'BUSI_DZ' ");
		
		sb.append(" and C_TGH_CODE = ? ");
		if(isFiterManager)
		{
			sb.append(" and (C_MANAGE_CODE = ? or trim(C_MANAGE_CODE) is null)  ");
		}else
		{
			sb.append(" and trim(C_MANAGE_CODE) is null  ");
		}
		sb.append(" ) B ");
		sb.append(" JOIN (SELECT D.C_PARA_CODE, D.C_SRC_USERID, D.C_SRC_APPID, D.C_PKG_PASSWORD ");
		sb.append(" FROM T_D_ER_PARA D) D ON B.C_ERPARA_CODE = D.C_PARA_CODE) B on  C.C_PORT_CODE = B.C_PORT_CODE ");
		sb.append("  WHERE C.C_SN = ? ");
		sb.append(" AND C.C_FILE_TYPE = ? ");
		sb.append("  AND C.C_PORT_CODE = ? ");
		sb.append(" AND C.C_DV_ER_WAY = 'FORWARD' ");
		return sb.toString();
	}
}
