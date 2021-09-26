package com.yss.ams.product.information.modules.ab.productInfoQuery.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**

 * 2021-03-16
 * STORY #99875 【估值核算】【国寿资产】【V1.300.7.0_GSZC_20201218】-增加产品信息查询界面
 * @author wanghaocheng
 *
 */
public class ProductInfoQuerySqlBuilder implements SQLBuilder {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		// TODO Auto-generated method stub
		return dbNameResolver.getColumnName(ProductInfoQueryColumnName.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" SELECT T.C_ASS_CODE,                                                                                 ");
			buf.append("        T.C_PORT_CODE,                                                                                ");
			buf.append("        T.C_PORT_NAME,                                                                                ");
			buf.append("        T.D_BUILD,                                                                                    ");
			buf.append("        T.D_CLOSE,                                                                                    ");
			buf.append("        T.D_CLEAR,                                                                                    ");
			buf.append("        T.D_COLSE_ACC,                                                                                ");
			buf.append("        ASSE.C_DAT_NAME AS C_DAT_CODE,                                                                ");
			buf.append("        T.C_DV_NAME AS C_DAT_CLS,                                                                     ");
			buf.append("        A.C_RELA_CODE,                                                                                ");
			buf.append("        A.C_ORG_CODE,                                                                                 ");
			buf.append("        A.C_ORG_NAME,                                                                                 ");
			buf.append("        ACCA.C_OPEN_ACC_NO,                                                                           ");
			buf.append("        ACCA.C_OPEN_ACC_NAME,                                                                         ");
			buf.append("        CASH.C_PARA_VALUE,                                                                            ");
			buf.append("        PD.C_ENTRUST_WAY,                                                                             ");
			buf.append("        PD.C_COVER_ACCOUNTS,                                                                          ");
			buf.append("        PD.N_T_DAYS,                                                                                  ");
			buf.append("        PD.C_CPSX_BJH,                                                                                ");
			buf.append("        PD.C_KHXZ,                                                                                    ");
			buf.append("        FEETG.C_FFPL AS C_TGF_FFPL,                                                                   ");
			buf.append("        FEETG.RATE AS N_TGF_RATE,                                                                     ");
			buf.append("        VOCTG.C_DV_NAME AS C_TGF_YEAR_DATE,                                                           ");
			buf.append("        FEETG.C_DESC AS C_TGF_DESC,                                                                   ");
			buf.append("        FEEGL.C_FFPL AS C_GLF_FFPL,                                                                   ");
			buf.append("        FEEGL.RATE AS N_GLF_RATE,                                                                     ");
			buf.append("        VOCGL.C_DV_NAME AS C_GLF_YEAR_DATE,                                                           ");
			buf.append("        FEEGL.C_DESC AS C_GLF_DESC,                                                                   ");
			buf.append("        FEEZSS.C_FFPL AS C_ZSSY_FFPL,                                                                 ");
			buf.append("        FEEZSS.RATE AS N_ZSSY_RATE,                                                                   ");
			buf.append("        VOCZSS.C_DV_NAME AS C_ZSSY_YEAR_DATE,                                                         ");
			buf.append("        FEEZSS.C_DESC AS C_ZSSY_DESC,                                                                 ");
			buf.append("        FEEE.C_FEE_CODE,                                                                              ");
			buf.append("        CASE WHEN FEEE.C_FEE_CODE = 'YYL_YJBCF' THEN 1 ELSE 0 END AS IS_YJBC,                         ");
			buf.append("        NVL(DECODE(TAT.N_PORT_COST, 0, '', TAT.N_PORT_COST), '--') AS N_PORT_COST,                    ");
			buf.append("        IFPORT.C_HDAY_CODE,                                                                           ");
			buf.append("        PVAR.C_SEC_VAR_NAME,                                                                          ");
			buf.append("        T.N_CHECK_STATE,                                                                              ");
			buf.append("        GRO.C_TR_NAME                                                                                 ");
			buf.append("   FROM (SELECT T.*, VOC.C_DV_NAME                                                                    ");
			buf.append("           FROM T_P_AB_PORT T JOIN T_S_DV_VOC VOC                                                     ");
			buf.append("             ON VOC.C_DV_CODE = T.C_DAT_CLS WHERE VOC.C_DV_TYPE = 'DAT_CODE'  ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" and ").append(valueFieldbuf);
			}
			buf.append(" ) T                       ");
			buf.append("   LEFT JOIN (SELECT A.*, ORG.C_ORG_CODE, ORG.C_ORG_NAME                                              ");
			buf.append("                FROM T_P_AB_PORT_RELA A                                                               ");
			buf.append("                LEFT JOIN T_P_BI_ORG ORG                                                              ");
			buf.append("                  ON ORG.C_ORG_CODE = A.C_RELA_CODE                                                   ");
			buf.append("               WHERE A.C_RELA_TYPE = 'RELA_ORG'                                                       ");
			buf.append("                 AND A.C_DV_TYPE_CODE = 'TRUSTEE'                                                     ");
			buf.append("                 AND A.N_CHECK_STATE = '1'                                                            ");
			buf.append("                 AND ORG.N_CHECK_STATE = '1') A                                                       ");
			buf.append("     ON T.C_PORT_CODE = A.C_PORT_CODE                                                                 ");
			buf.append("   LEFT JOIN T_S_DAT_ASS_TYPE ASSE                                                                    ");
			buf.append("     ON ASSE.C_DAT_CODE = T.C_DAT_CODE                                                                ");
			buf.append("   LEFT JOIN (SELECT ACCC.C_OPEN_ACC_NO,                                                              ");
			buf.append("                     ACCA.C_PORT_CODE,                                                                ");
			buf.append("                     ACCC.C_OPEN_ACC_NAME                                                             ");
			buf.append("                FROM T_P_AB_PORT_ACC_RELA ACCA                                                        ");
			buf.append("                LEFT JOIN T_P_BI_FUND_ACC ACCC                                                        ");
			buf.append("                  ON ACCC.C_IDEN = ACCA.C_RELA_CODE                                                   ");
			buf.append("               WHERE ACCC.N_CHECK_STATE = '1') ACCA                                                   ");
			buf.append("     ON ACCA.C_PORT_CODE = T.C_PORT_CODE                                                              ");
			buf.append("   LEFT JOIN (SELECT C.C_PORT_CODE, C.C_ALGO_CODE, PARA.C_PARA_VALUE                                  ");
			buf.append("                FROM T_P_AO_ACCR_CASH C                                                               ");
			buf.append("                LEFT JOIN T_P_AO_ACCR_CASH_PARA PARA                                                  ");
			buf.append("                  ON PARA.C_IDEN_RELA = C.C_IDEN                                                      ");
			buf.append("               WHERE (PARA.C_PARA_CODE = 'accrualRate' OR                                             ");
			buf.append("                     PARA.C_PARA_CODE = 'ratioFormula')                                               ");
			buf.append("                 AND C.N_CHECK_STATE = '1') CASH                                                      ");
			buf.append("     ON CASH.C_PORT_CODE = T.C_PORT_CODE                                                              ");
			buf.append("   LEFT JOIN T_P_AB_PORT_PD PD                                                                        ");
			buf.append("     ON PD.C_PORT_CODE = T.C_PORT_CODE                                                                ");
			buf.append("    AND PD.N_CHECK_STATE = '1'                                                                        ");
			buf.append("   LEFT JOIN (SELECT SUBSTR(to_char(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST (COLLECT (PARA.C_PARA_VALUE) AS vartabletype), ',')),                                             ");
			buf.append("                            0,                                                                        ");
			buf.append("                            INSTR(to_char(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST (COLLECT (PARA.C_PARA_VALUE) AS vartabletype), ',')), ',') - 1) AS RATE,                    ");
			buf.append("                     SUBSTR(to_char(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST (COLLECT (PARA.C_PARA_VALUE) AS vartabletype), ',')),                                             ");
			buf.append("                            INSTR(to_char(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST (COLLECT (PARA.C_PARA_VALUE) AS vartabletype), ',')), ',') + 1) AS YEAR_DATE,               ");
			buf.append("                     FEE.C_FFPL,                                                                      ");
			buf.append("                     FEE.C_DESC,                                                                      ");
			buf.append("                     FEE.C_PORT_CODE                                                                  ");
			buf.append("                FROM T_P_AB_INVE_FEE FEE                                                              ");
			buf.append("                JOIN T_P_AB_INVE_PARA PARA                                                            ");
			buf.append("                  ON FEE.C_IDEN = PARA.C_IDEN_RELA                                                    ");
			buf.append("                 AND FEE.N_CHECK_STATE = '1'                                                          ");
			buf.append("                 AND FEE.C_FEE_CODE = 'YYL_TGF'                                                       ");
			buf.append("                 AND (PARA.C_PARA_CODE = 'accrualFeeRate' OR                                          ");
			buf.append("                     PARA.C_PARA_CODE = 'feeRateYearDates')                                           ");
			buf.append("               GROUP BY FEE.C_FFPL, FEE.C_DESC, FEE.C_PORT_CODE) FEETG                                ");
			buf.append("     ON FEETG.C_PORT_CODE = T.C_PORT_CODE                                                             ");
			buf.append("   LEFT JOIN T_S_DV_VOC VOCTG                                                                         ");
			buf.append("     ON VOCTG.C_DV_CODE = FEETG.C_FFPL                                                                ");
			buf.append("   LEFT JOIN (SELECT SUBSTR(to_char(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST (COLLECT (PARA.C_PARA_VALUE) AS vartabletype), ',')),                                             ");
			buf.append("                            0,                                                                        ");
			buf.append("                            INSTR(to_char(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST (COLLECT (PARA.C_PARA_VALUE) AS vartabletype), ',')), ',') - 1) AS RATE,                    ");
			buf.append("                     SUBSTR(to_char(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST (COLLECT (PARA.C_PARA_VALUE) AS vartabletype), ',')),                                             ");
			buf.append("                            INSTR(to_char(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST (COLLECT (PARA.C_PARA_VALUE) AS vartabletype), ',')), ',') + 1) AS YEAR_DATE,               ");
			buf.append("                     FEE.C_FFPL,                                                                      ");
			buf.append("                     FEE.C_DESC,                                                                      ");
			buf.append("                     FEE.C_PORT_CODE                                                                  ");
			buf.append("                FROM T_P_AB_INVE_FEE FEE                                                              ");
			buf.append("                JOIN T_P_AB_INVE_PARA PARA                                                            ");
			buf.append("                  ON FEE.C_IDEN = PARA.C_IDEN_RELA                                                    ");
			buf.append("                 AND FEE.N_CHECK_STATE = '1'                                                          ");
			buf.append("                 AND FEE.C_FEE_CODE = 'YYL_GLF'                                                       ");
			buf.append("                 AND (PARA.C_PARA_CODE = 'accrualFeeRate' OR                                          ");
			buf.append("                     PARA.C_PARA_CODE = 'feeRateYearDates')                                           ");
			buf.append("               GROUP BY FEE.C_FFPL, FEE.C_DESC, FEE.C_PORT_CODE) FEEGL                                ");
			buf.append("     ON FEEGL.C_PORT_CODE = T.C_PORT_CODE                                                             ");
			buf.append("   LEFT JOIN T_S_DV_VOC VOCGL                                                                         ");
			buf.append("     ON VOCGL.C_DV_CODE = FEEGL.C_FFPL                                                                ");
			buf.append("   LEFT JOIN (SELECT SUBSTR(to_char(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST (COLLECT (PARA.C_PARA_VALUE) AS vartabletype), ',')),                                             ");
			buf.append("                            0,                                                                        ");
			buf.append("                            INSTR(to_char(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST (COLLECT (PARA.C_PARA_VALUE) AS vartabletype), ',')), ',') - 1) AS RATE,                    ");
			buf.append("                     SUBSTR(to_char(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST (COLLECT (PARA.C_PARA_VALUE) AS vartabletype), ',')),                                             ");
			buf.append("                            INSTR(to_char(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST (COLLECT (PARA.C_PARA_VALUE) AS vartabletype), ',')), ',') + 1) AS YEAR_DATE,               ");
			buf.append("                     FEE.C_FFPL,                                                                      ");
			buf.append("                     FEE.C_DESC,                                                                      ");
			buf.append("                     FEE.C_PORT_CODE                                                                  ");
			buf.append("                FROM T_P_AB_INVE_FEE FEE                                                              ");
			buf.append("                JOIN T_P_AB_INVE_PARA PARA                                                            ");
			buf.append("                  ON FEE.C_IDEN = PARA.C_IDEN_RELA                                                    ");
			buf.append("                 AND FEE.N_CHECK_STATE = '1'                                                          ");
			buf.append("                 AND FEE.C_FEE_CODE = 'YYL_ZSSYF'                                                     ");
			buf.append("                 AND (PARA.C_PARA_CODE = 'accrualFeeRate' OR                                          ");
			buf.append("                     PARA.C_PARA_CODE = 'feeRateYearDates')                                           ");
			buf.append("               GROUP BY FEE.C_FFPL, FEE.C_DESC, FEE.C_PORT_CODE) FEEZSS                               ");
			buf.append("     ON FEEZSS.C_PORT_CODE = T.C_PORT_CODE                                                            ");
			buf.append("   LEFT JOIN T_S_DV_VOC VOCZSS                                                                        ");
			buf.append("     ON VOCZSS.C_DV_CODE = FEEZSS.C_FFPL                                                              ");
			buf.append("   LEFT JOIN T_P_AB_INVE_FEE FEEE                                                                     ");
			buf.append("     ON FEEE.C_PORT_CODE = T.C_PORT_CODE                                                              ");
			buf.append("    AND FEEE.C_FEE_CODE = 'YYL_YJBCF'                                                                 ");
			buf.append("    AND FEEE.N_CHECK_STATE = '1'                                                                      ");
			buf.append("   LEFT JOIN T_R_FR_ASTSTAT TAT                                                                       ");
			buf.append("     ON TAT.C_PORT_CODE = T.C_PORT_CODE                                                               ");
			buf.append("    AND TAT.D_ASTSTAT = TO_DATE(?, 'yyyy/mm/dd')                                                      ");
			buf.append("                                                                                                      ");
			buf.append("   LEFT JOIN (SELECT MAX(C_IDEN) AS C_IDEN, C_PORT_CODE                                               ");
			buf.append("                FROM T_R_TREP_DEF_IF_PORT                                                             ");
			buf.append("               WHERE C_REPORT_CODE = 'INFO_PL_NAV_TA'                                                 ");
			buf.append("               GROUP BY C_PORT_CODE) IFP                                                              ");
			buf.append("     ON IFP.C_PORT_CODE = T.C_PORT_CODE                                                               ");
			buf.append("   LEFT JOIN T_R_TREP_DEF_IF_PORT IFPORT                                                              ");
			buf.append("     ON IFPORT.C_IDEN = IFP.C_IDEN                                                                    ");
			buf.append("   LEFT JOIN (SELECT K.C_PORT_CODE, MAX(K.TD_D_AI_STOCK) AS TD_D_AI_STOCK                             ");
			buf.append("                FROM T_D_AI_STOCK K                                                                   ");
			buf.append("               GROUP BY K.C_PORT_CODE) KK                                                             ");
			buf.append("     ON KK.C_PORT_CODE = T.C_PORT_CODE                                                                ");
			buf.append("   LEFT JOIN T_D_AI_STOCK K                                                                           ");
			buf.append("     ON K.TD_D_AI_STOCK = KK.TD_D_AI_STOCK                                                            ");
			buf.append("   LEFT JOIN T_S_DA_SEC_VAR SVAR                                                                      ");
			buf.append("     ON SVAR.C_SEC_VAR_CODE = K.C_SEC_VAR_CODE                                                        ");
			buf.append("   LEFT JOIN T_S_DA_SEC_VAR PVAR                                                                      ");
			buf.append("     ON PVAR.C_SEC_VAR_CODE = SVAR.C_DA_CODE_P                                                        ");
			buf.append("   LEFT JOIN (SELECT TR.C_TR_NAME, SUB.C_PORT_CODE                                                    ");
			buf.append("                FROM T_V_D_GROUP_DETAIL D                                                             ");
			buf.append("                JOIN T_V_D_GROUP P                                                                    ");
			buf.append("                  ON P.C_GROUP_CODE = D.C_GROUP_CODE                                                  ");
			buf.append("                LEFT JOIN T_P_AB_ASS_TR_SUB SUB                                                       ");
			buf.append("                  ON SUB.C_TR_CODE = D.C_T_CODE                                                       ");
			buf.append("                LEFT JOIN T_P_AB_ASS_TR TR                                                            ");
			buf.append("                  ON TR.C_TR_CODE = SUB.C_TR_CODE                                                     ");
			buf.append("               WHERE P.C_GROUP_CODE = 'CPXXCX'                                                        ");
			buf.append("                 AND D.C_S_CODE = 'AYH') GRO                                                          ");
			buf.append("     ON T.C_PORT_CODE = GRO.C_PORT_CODE                                                               ");
			buf.append("  WHERE T.N_CHECK_STATE = '1'                                                                         ");
			retSql = buf.toString();
		} catch (Exception ex) {
			throw new Exception("查询产品信息出错",ex);
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return retSql;
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append("  SELECT  count(*) AS CNT  ");
			buf.append("   FROM (SELECT T.*, VOC.C_DV_NAME                                                                    ");
			buf.append("           FROM T_P_AB_PORT T JOIN T_S_DV_VOC VOC                                                     ");
			buf.append("             ON VOC.C_DV_CODE = T.C_DAT_CLS WHERE VOC.C_DV_TYPE = 'DAT_CODE'  ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" and ").append(valueFieldbuf);
			}
			buf.append(" ) T                       ");
			buf.append("   LEFT JOIN (SELECT A.*, ORG.C_ORG_CODE, ORG.C_ORG_NAME                                              ");
			buf.append("                FROM T_P_AB_PORT_RELA A                                                               ");
			buf.append("                LEFT JOIN T_P_BI_ORG ORG                                                              ");
			buf.append("                  ON ORG.C_ORG_CODE = A.C_RELA_CODE                                                   ");
			buf.append("               WHERE A.C_RELA_TYPE = 'RELA_ORG'                                                       ");
			buf.append("                 AND A.C_DV_TYPE_CODE = 'TRUSTEE'                                                     ");
			buf.append("                 AND A.N_CHECK_STATE = '1'                                                            ");
			buf.append("                 AND ORG.N_CHECK_STATE = '1') A                                                       ");
			buf.append("     ON T.C_PORT_CODE = A.C_PORT_CODE                                                                 ");
			buf.append("   LEFT JOIN T_S_DAT_ASS_TYPE ASSE                                                                    ");
			buf.append("     ON ASSE.C_DAT_CODE = T.C_DAT_CODE                                                                ");
			buf.append("   LEFT JOIN (SELECT ACCC.C_OPEN_ACC_NO,                                                              ");
			buf.append("                     ACCA.C_PORT_CODE,                                                                ");
			buf.append("                     ACCC.C_OPEN_ACC_NAME                                                             ");
			buf.append("                FROM T_P_AB_PORT_ACC_RELA ACCA                                                        ");
			buf.append("                LEFT JOIN T_P_BI_FUND_ACC ACCC                                                        ");
			buf.append("                  ON ACCC.C_IDEN = ACCA.C_RELA_CODE                                                   ");
			buf.append("               WHERE ACCC.N_CHECK_STATE = '1') ACCA                                                   ");
			buf.append("     ON ACCA.C_PORT_CODE = T.C_PORT_CODE                                                              ");
			buf.append("   LEFT JOIN (SELECT C.C_PORT_CODE, C.C_ALGO_CODE, PARA.C_PARA_VALUE                                  ");
			buf.append("                FROM T_P_AO_ACCR_CASH C                                                               ");
			buf.append("                LEFT JOIN T_P_AO_ACCR_CASH_PARA PARA                                                  ");
			buf.append("                  ON PARA.C_IDEN_RELA = C.C_IDEN                                                      ");
			buf.append("               WHERE (PARA.C_PARA_CODE = 'accrualRate' OR                                             ");
			buf.append("                     PARA.C_PARA_CODE = 'ratioFormula')                                               ");
			buf.append("                 AND C.N_CHECK_STATE = '1') CASH                                                      ");
			buf.append("     ON CASH.C_PORT_CODE = T.C_PORT_CODE                                                              ");
			buf.append("   LEFT JOIN T_P_AB_PORT_PD PD                                                                        ");
			buf.append("     ON PD.C_PORT_CODE = T.C_PORT_CODE                                                                ");
			buf.append("    AND PD.N_CHECK_STATE = '1'                                                                        ");
			//buf.append("   LEFT JOIN (SELECT SUBSTR(WM_CONCAT(PARA.C_PARA_VALUE),                                             ");
			buf.append("   LEFT JOIN (SELECT SUBSTR(to_char(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST (collect(PARA.C_PARA_VALUE) AS VARTABLETYPE),',')),                                             ");
			buf.append("                            0,                                                                        ");
			//buf.append("                            INSTR(WM_CONCAT(PARA.C_PARA_VALUE), ',') - 1) AS RATE,                    ");
			//buf.append("                     SUBSTR(WM_CONCAT(PARA.C_PARA_VALUE),                                             ");
			//buf.append("                            INSTR(WM_CONCAT(PARA.C_PARA_VALUE), ',') + 1) AS YEAR_DATE,               ");
			buf.append("                            INSTR(to_char(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST (collect(PARA.C_PARA_VALUE) AS VARTABLETYPE), ',')), ',') - 1) AS RATE,                    ");
			buf.append("                     SUBSTR(to_char(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST (collect(PARA.C_PARA_VALUE) AS VARTABLETYPE), ',')),                                             ");
			buf.append("                            INSTR(to_char(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST (collect(PARA.C_PARA_VALUE) AS VARTABLETYPE), ',')), ',') + 1) AS YEAR_DATE,               ");
			buf.append("                     FEE.C_FFPL,                                                                      ");
			buf.append("                     FEE.C_DESC,                                                                      ");
			buf.append("                     FEE.C_PORT_CODE                                                                  ");
			buf.append("                FROM T_P_AB_INVE_FEE FEE                                                              ");
			buf.append("                JOIN T_P_AB_INVE_PARA PARA                                                            ");
			buf.append("                  ON FEE.C_IDEN = PARA.C_IDEN_RELA                                                    ");
			buf.append("                 AND FEE.N_CHECK_STATE = '1'                                                          ");
			buf.append("                 AND FEE.C_FEE_CODE = 'YYL_TGF'                                                       ");
			buf.append("                 AND (PARA.C_PARA_CODE = 'accrualFeeRate' OR                                          ");
			buf.append("                     PARA.C_PARA_CODE = 'feeRateYearDates')                                           ");
			buf.append("               GROUP BY FEE.C_FFPL, FEE.C_DESC, FEE.C_PORT_CODE) FEETG                                ");
			buf.append("     ON FEETG.C_PORT_CODE = T.C_PORT_CODE                                                             ");
			buf.append("   LEFT JOIN T_S_DV_VOC VOCTG                                                                         ");
			buf.append("     ON VOCTG.C_DV_CODE = FEETG.C_FFPL                                                                ");
			//buf.append("   LEFT JOIN (SELECT SUBSTR(WM_CONCAT(PARA.C_PARA_VALUE),                                             ");
			buf.append("   LEFT JOIN (SELECT SUBSTR(to_char(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST (collect(PARA.C_PARA_VALUE) AS VARTABLETYPE), ',')),                                             ");
			buf.append("                            0,                                                                        ");
			//buf.append("                            INSTR(WM_CONCAT(PARA.C_PARA_VALUE), ',') - 1) AS RATE,                    ");
			//buf.append("                     SUBSTR(WM_CONCAT(PARA.C_PARA_VALUE),                                             ");
			//buf.append("                            INSTR(WM_CONCAT(PARA.C_PARA_VALUE), ',') + 1) AS YEAR_DATE,               ");
			buf.append("                            INSTR(to_char(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST(collect(PARA.C_PARA_VALUE) AS VARTABLETYPE), ',')), ',') - 1) AS RATE,                    ");
			buf.append("                     SUBSTR(to_char(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST (collect(PARA.C_PARA_VALUE) AS VARTABLETYPE), ',')),                                             ");
			buf.append("                            INSTR(to_char(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST (collect(PARA.C_PARA_VALUE) AS VARTABLETYPE), ',')), ',') + 1) AS YEAR_DATE,               ");
			buf.append("                     FEE.C_FFPL,                                                                      ");
			buf.append("                     FEE.C_DESC,                                                                      ");
			buf.append("                     FEE.C_PORT_CODE                                                                  ");
			buf.append("                FROM T_P_AB_INVE_FEE FEE                                                              ");
			buf.append("                JOIN T_P_AB_INVE_PARA PARA                                                            ");
			buf.append("                  ON FEE.C_IDEN = PARA.C_IDEN_RELA                                                    ");
			buf.append("                 AND FEE.N_CHECK_STATE = '1'                                                          ");
			buf.append("                 AND FEE.C_FEE_CODE = 'YYL_GLF'                                                       ");
			buf.append("                 AND (PARA.C_PARA_CODE = 'accrualFeeRate' OR                                          ");
			buf.append("                     PARA.C_PARA_CODE = 'feeRateYearDates')                                           ");
			buf.append("               GROUP BY FEE.C_FFPL, FEE.C_DESC, FEE.C_PORT_CODE) FEEGL                                ");
			buf.append("     ON FEEGL.C_PORT_CODE = T.C_PORT_CODE                                                             ");
			buf.append("   LEFT JOIN T_S_DV_VOC VOCGL                                                                         ");
			buf.append("     ON VOCGL.C_DV_CODE = FEEGL.C_FFPL                                                                ");
			//buf.append("   LEFT JOIN (SELECT SUBSTR(WM_CONCAT(PARA.C_PARA_VALUE),                                             ");
			buf.append("   LEFT JOIN (SELECT SUBSTR(to_char(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST (collect(PARA.C_PARA_VALUE) AS VARTABLETYPE), ',')),                                             ");
			buf.append("                            0,                                                                        ");
			//buf.append("                            INSTR(WM_CONCAT(PARA.C_PARA_VALUE), ',') - 1) AS RATE,                    ");
			//buf.append("                     SUBSTR(WM_CONCAT(PARA.C_PARA_VALUE),                                             ");
			//buf.append("                            INSTR(WM_CONCAT(PARA.C_PARA_VALUE), ',') + 1) AS YEAR_DATE,               ");
			buf.append("                            INSTR(to_char(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST (collect(PARA.C_PARA_VALUE) AS VARTABLETYPE), ',')), ',') - 1) AS RATE,                    ");
			buf.append("                     SUBSTR(to_char(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST (collect(PARA.C_PARA_VALUE) AS VARTABLETYPE), ',')),                                             ");
			buf.append("                            INSTR(to_char(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST (collect(PARA.C_PARA_VALUE) AS VARTABLETYPE), ',')), ',') + 1) AS YEAR_DATE,               ");
			buf.append("                     FEE.C_FFPL,                                                                      ");
			buf.append("                     FEE.C_DESC,                                                                      ");
			buf.append("                     FEE.C_PORT_CODE                                                                  ");
			buf.append("                FROM T_P_AB_INVE_FEE FEE                                                              ");
			buf.append("                JOIN T_P_AB_INVE_PARA PARA                                                            ");
			buf.append("                  ON FEE.C_IDEN = PARA.C_IDEN_RELA                                                    ");
			buf.append("                 AND FEE.N_CHECK_STATE = '1'                                                          ");
			buf.append("                 AND FEE.C_FEE_CODE = 'YYL_ZSSYF'                                                     ");
			buf.append("                 AND (PARA.C_PARA_CODE = 'accrualFeeRate' OR                                          ");
			buf.append("                     PARA.C_PARA_CODE = 'feeRateYearDates')                                           ");
			buf.append("               GROUP BY FEE.C_FFPL, FEE.C_DESC, FEE.C_PORT_CODE) FEEZSS                               ");
			buf.append("     ON FEEZSS.C_PORT_CODE = T.C_PORT_CODE                                                            ");
			buf.append("   LEFT JOIN T_S_DV_VOC VOCZSS                                                                        ");
			buf.append("     ON VOCZSS.C_DV_CODE = FEEZSS.C_FFPL                                                              ");
			buf.append("   LEFT JOIN T_P_AB_INVE_FEE FEEE                                                                     ");
			buf.append("     ON FEEE.C_PORT_CODE = T.C_PORT_CODE                                                              ");
			buf.append("    AND FEEE.C_FEE_CODE = 'YYL_YJBCF'                                                                 ");
			buf.append("    AND FEEE.N_CHECK_STATE = '1'                                                                      ");
			buf.append("   LEFT JOIN T_R_FR_ASTSTAT TAT                                                                       ");
			buf.append("     ON TAT.C_PORT_CODE = T.C_PORT_CODE                                                               ");
			buf.append("    AND TAT.D_ASTSTAT = TO_DATE(?, 'yyyy/mm/dd')                                                      ");
			buf.append("                                                                                                      ");
			buf.append("   LEFT JOIN (SELECT MAX(C_IDEN) AS C_IDEN, C_PORT_CODE                                               ");
			buf.append("                FROM T_R_TREP_DEF_IF_PORT                                                             ");
			buf.append("               WHERE C_REPORT_CODE = 'INFO_PL_NAV_TA'                                                 ");
			buf.append("               GROUP BY C_PORT_CODE) IFP                                                              ");
			buf.append("     ON IFP.C_PORT_CODE = T.C_PORT_CODE                                                               ");
			buf.append("   LEFT JOIN T_R_TREP_DEF_IF_PORT IFPORT                                                              ");
			buf.append("     ON IFPORT.C_IDEN = IFP.C_IDEN                                                                    ");
			buf.append("   LEFT JOIN (SELECT K.C_PORT_CODE, MAX(K.TD_D_AI_STOCK) AS TD_D_AI_STOCK                             ");
			buf.append("                FROM T_D_AI_STOCK K                                                                   ");
			buf.append("               GROUP BY K.C_PORT_CODE) KK                                                             ");
			buf.append("     ON KK.C_PORT_CODE = T.C_PORT_CODE                                                                ");
			buf.append("   LEFT JOIN T_D_AI_STOCK K                                                                           ");
			buf.append("     ON K.TD_D_AI_STOCK = KK.TD_D_AI_STOCK                                                            ");
			buf.append("   LEFT JOIN T_S_DA_SEC_VAR SVAR                                                                      ");
			buf.append("     ON SVAR.C_SEC_VAR_CODE = K.C_SEC_VAR_CODE                                                        ");
			buf.append("   LEFT JOIN T_S_DA_SEC_VAR PVAR                                                                      ");
			buf.append("     ON PVAR.C_SEC_VAR_CODE = SVAR.C_DA_CODE_P                                                        ");
			buf.append("   LEFT JOIN (SELECT TR.C_TR_NAME, SUB.C_PORT_CODE                                                    ");
			buf.append("                FROM T_V_D_GROUP_DETAIL D                                                             ");
			buf.append("                JOIN T_V_D_GROUP P                                                                    ");
			buf.append("                  ON P.C_GROUP_CODE = D.C_GROUP_CODE                                                  ");
			buf.append("                LEFT JOIN T_P_AB_ASS_TR_SUB SUB                                                       ");
			buf.append("                  ON SUB.C_TR_CODE = D.C_T_CODE                                                       ");
			buf.append("                LEFT JOIN T_P_AB_ASS_TR TR                                                            ");
			buf.append("                  ON TR.C_TR_CODE = SUB.C_TR_CODE                                                     ");
			buf.append("               WHERE P.C_GROUP_CODE = 'CPXXCX'                                                        ");
			buf.append("                 AND D.C_S_CODE = 'AYH') GRO                                                          ");
			buf.append("     ON T.C_PORT_CODE = GRO.C_PORT_CODE                                                               ");
			buf.append("  WHERE T.N_CHECK_STATE = '1'                                                                         ");
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
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "T"));
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("ARRAY_C_PORT_CODE")) {
				valueFieldbuf.append(" T.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}
			else if (fieldedName.equals("C_PORT_CODE")) {
				valueFieldbuf.append(" T.C_PORT_CODE like ? ");
			}
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}
}
