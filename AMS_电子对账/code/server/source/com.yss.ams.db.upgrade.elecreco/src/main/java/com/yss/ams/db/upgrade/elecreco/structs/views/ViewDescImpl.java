package com.yss.ams.db.upgrade.elecreco.structs.views;

import com.yss.fast.db.upgrade.support.api.BaseStructDesc;
import com.yss.fast.db.upgrade.support.api.ViewBuilder;
import com.yss.fast.db.upgrade.support.script.enums.UpdateType;

public class ViewDescImpl extends BaseStructDesc{
	
	private ViewBuilder builder = null;

	@Override
	public void execute() throws Exception {
		builder = getViewBuilder();
		buildVC_ER_XML_GZ();
	}
	
	/**
	 * STORY #103644 华夏基金-新增托管行电子对账结果数据查询视图 
	 * @throws Exception
	 */
	private void buildVC_ER_XML_GZ() throws Exception {
		builder.createOrReplaceView("VC_ER_XML_GZ","估值表发送详情查询视图").begin_SQL()
		.appendLine(" CREATE OR REPLACE VIEW VC_ER_XML_GZ AS ")
		.appendLine("SELECT AA.C_IDEN AS C_IDEN,                                                         ")
		.appendLine("       AA.C_PORT_CODE,                                                              ")
		.appendLine("       AA.D_DATE,                                                                   ")
		.appendLine("       AA.C_FILE_TYPE,                                                              ")
		.appendLine("       NVL(AA.C_PORT_CLS_CODE,GZ.C_PORT_CLS_CODE) AS C_PORT_CLS_CODE,               ")
		.appendLine("       AA.C_SN AS C_SN,                                                             ")
		.appendLine(" (select MAX(RELA.C_DV_ZB_CODE) from T_Z_BI_RELA RELA where RELA.C_ZB_CODE = aa.C_KM_CODE and RELA.C_DZ_CODE = '1011' AND RELA.N_CHECK_STATE = 1) AS C_DV_ZB_CODE, ")
		.appendLine("       AA.C_KM_CODE AS C_KM_CODE,                                                   ")
		.appendLine(" case when GZ.C_NAV_TYPE = 'TOTAL_ALL' AND INSTR(AA.C_STATE,'不一致') > 0 AND INSTR(GZ.C_KM_NAME,'%') > 0 then AA.N_PORT_MV||'%' ")
		.appendLine(" when GZ.C_NAV_TYPE = 'TOTAL_ALL' AND INSTR(AA.C_STATE,'不一致') > 0 then AA.N_PORT_MV ")
		.appendLine(" else NVL(AA.C_KM_NAME,GZ.C_KM_NAME) end AS C_KM_NAME,                               ")
		.appendLine("       AA.N_AMOUNT AS N_AMOUNT,                                                     ")
		.appendLine("       AA.N_PORT_COST AS N_PORT_COST,                                               ")
		.appendLine("       AA.N_PORT_MV AS N_PORT_MV,                                                   ")
		.appendLine("       AA.N_PORT_IV AS N_PORT_IV,                                                   ")
		.appendLine("       CASE WHEN AA.C_STATE = '0' THEN '核对一致'                                   ")
		.appendLine("         WHEN INSTR(AA.C_STATE,'不一致') > 0 THEN '核对不一致'                      ")
		.appendLine("         WHEN INSTR(AA.C_STATE,'未发送') > 0 THEN '未发送'                          ")
		.appendLine(" WHEN INSTR(AA.C_STATE,'发送中') > 0 THEN '发送中' ")
		.appendLine(" WHEN INSTR(AA.C_STATE,'发送成功') > 0 THEN '发送成功' ")
		.appendLine(" WHEN INSTR(AA.C_STATE,'发送失败') > 0 THEN '发送失败' ")
		.appendLine("           ELSE '' END AS C_STATE                                                   ")
		.appendLine("  FROM (select INFO.C_IDEN AS C_IDEN,                                               ")
		.appendLine("               INFO.C_PORT_CODE AS C_PORT_CODE,                                     ")
		.appendLine("               INFO.D_DATE AS D_DATE,                                               ")
		.appendLine("               INFO.C_FILE_TYPE AS C_FILE_TYPE,                                     ")
		.appendLine("               '' AS C_PORT_CLS_CODE,                                               ")
		.appendLine("               to_char(INFO.C_SN) AS C_SN,                                          ")
		.appendLine("               A.C_B_CODE AS C_KM_CODE,                                             ")
		.appendLine("               '' AS C_KM_NAME,                                                     ")
		.appendLine("               to_char(A.N_D_SL) AS N_AMOUNT, --数量                                ")
		.appendLine("               to_char(A.N_D_JEO) AS N_PORT_COST, --证券成本                        ")
		.appendLine("               to_char(A.N_D_JE) AS N_PORT_MV, --证券市值                           ")
		.appendLine("               to_char(A.N_D_JET) AS N_PORT_IV, --估值增值                          ")
		.appendLine("               NVL(TRIM(A.C_RESULT),'不一致') C_STATE                               ")
		.appendLine("          from T_D_ER_INFO INFO LEFT JOIN t_d_er_result A ON INFO.C_SN = A.C_SN     ")
		.appendLine("          WHERE INFO.C_FILE_TYPE = '1011' AND INFO.N_CHECK_STATE = 1                ")
		.appendLine("        UNION                                                                       ")
		.appendLine("        SELECT INFO.C_IDEN AS C_IDEN,                                               ")
		.appendLine("               INFO.C_PORT_CODE AS C_PORT_CODE,                                     ")
		.appendLine("               INFO.D_DATE AS D_DATE,                                               ")
		.appendLine("               INFO.C_FILE_TYPE AS C_FILE_TYPE,                                     ")
		.appendLine("               GZ.C_PORT_CLS_CODE AS C_PORT_CLS_CODE,                               ")
		.appendLine("               INFO.C_SN AS C_SN,                                                   ")
		.appendLine("               GZ.C_KM_CODE AS C_KM_CODE,                                           ")
		.appendLine("               GZ.C_KM_NAME AS C_KM_NAME,                                           ")
		.appendLine("               GZ.N_AMOUNT AS N_AMOUNT, --证券数量                                  ")
		.appendLine("               GZ.N_PORT_COST AS N_PORT_COST, --证券成本                            ")
		.appendLine("               GZ.N_PORT_MV AS N_PORT_MV, --证券市值                                ")
		.appendLine("               GZ.N_PORT_IV AS N_PORT_IV, --估值增值                                ")
		.appendLine("               CASE                                                                 ")
		.appendLine("         WHEN INFO.C_STATE = 'ER_SEND' THEN                                         ")
		.appendLine("          '未发送'                                                                  ")
		.appendLine("  WHEN INFO.C_STATE = 'ER_SENDED' THEN '发送中'  ")
		.appendLine("  WHEN INFO.C_STATE = 'ER_SENDED_SECCUSS' THEN '发送成功'  ")
		.appendLine("  WHEN INFO.C_STATE = 'ER_SENDED_FAIL' THEN '发送失败'  ")
		.appendLine("         WHEN INFO.C_STATE IN ('ER_ACCECPED', 'ER_IDENTICAL') THEN                  ")
		.appendLine("          '0'                                                                       ")
		.appendLine("         ELSE                                                                       ")
		.appendLine("          ''                                                                        ")
		.appendLine("       END AS C_STATE                                                               ")
		.appendLine("          FROM T_D_ER_INFO INFO                                                     ")
		.appendLine("          LEFT JOIN T_D_ER_XML_GZ GZ ON INFO.C_SN = GZ.C_SN                         ")
		.appendLine("         WHERE INFO.C_FILE_TYPE = '1011' AND INFO.N_CHECK_STATE = 1                 ")
		.appendLine("           AND NOT EXISTS (SELECT 1                                                 ")
		.appendLine("                  FROM t_d_er_result R                                              ")
		.appendLine("                 WHERE R.C_B_CODE = GZ.C_KM_CODE AND R.C_SN = INFO.C_SN)            ")
		.appendLine("           AND NOT EXISTS (SELECT 1                                                 ")
		.appendLine("                  FROM t_d_er_result R                                              ")
		.appendLine("                 WHERE R.C_D_CODE = GZ.C_KM_CODE AND R.C_SN = INFO.C_SN)            ")
		.appendLine("                    ) AA                                                            ")
		.appendLine("LEFT JOIN T_D_ER_GZ GZ ON GZ.C_KM_CODE = AA.C_KM_CODE                               ")
		.appendLine(" WHERE AA.C_SN = GZ.C_SN  AND GZ.C_NAV_TYPE IN ('TOTAL','TOTAL_ALL')                ")
		.end_SQL().build(UpdateType.REQUEST, "103644", "STORY #103644 华夏基金-新增托管行电子对账结果数据查询视图 ","tongdengke@ysstech.com","2021-04-13");
	}
}
