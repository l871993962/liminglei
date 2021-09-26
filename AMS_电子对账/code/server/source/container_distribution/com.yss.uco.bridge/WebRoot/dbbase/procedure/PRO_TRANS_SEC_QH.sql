CREATE OR REPLACE PROCEDURE PRO_TRANS_SEC_QH(ORESULT  OUT VARCHAR2,
                                             OERRTEXT OUT VARCHAR2) IS
  V_ERRORCODE NUMBER; /*错误代码*/
  V_ERRORTEXT VARCHAR2(2000); /*错误信息*/
  /*期货基本信息的导入*/
BEGIN
  DELETE FROM T_P_SV_SEC_BASE A
   WHERE A.C_SEC_VAR_CODE LIKE 'QH%'
     AND EXISTS (SELECT '1'
            FROM TB_006_PARA_SECURITY SEC
           WHERE SEC.FCHECKSTATE <= 1
             AND A.C_SEC_MKT_CODE = SEC.FSECURITYCODE);
  INSERT INTO T_P_SV_SEC_BASE
    (C_IDEN,
     C_SEC_CODE,
     C_SEC_NAME,
     C_SEC_MKT_CODE,
     C_SEC_ISIN_CODE,
     C_MKT_CODE,
     C_SEC_VAR_CODE,
     C_DC_CODE,
     N_PRICE_FCR,
     C_SEC_CODE_TRG,
     N_AMOUNT_HD,
     N_FV_ISSUE,
     D_TO_LIST,
     D_OFF_LIST,
     C_DV_VAR_DUR,
     C_DV_QUT_MOD,
     N_RATE,
     N_FV_IR,
     N_PRICE_ISSUE,
     C_DV_AI_MOD,
     C_DV_AI_EXPR,
     C_DV_PI_MOD,
     D_AI_BEGIN,
     D_AI_END,
     C_DESC,
     N_CHECK_STATE,
     C_UPDATE_BY,
     C_UPDATE_TIME,
     C_CHECK_BY,
     C_CHECK_TIME)
    SELECT SEQ_P_SV_SEC_BASE.NEXTVAL as C_IDEN,
           SEC.FSECURITYCODE || ' ' || SEC.FEXCHANGECODE AS C_SEC_CODE, /*证券代码*/
           SEC.FSECURITYNAME AS C_SEC_NAME, /*期货名称*/
           SEC.FSECURITYCODE AS C_SEC_MKT_CODE, /*期货代码*/
           SEC.FISINCODE AS C_SEC_ISIN_CODE, /*ISIN代码*/
           nvl(mkt.c_mkt_code,SEC.FEXCHANGECODE) AS C_MKT_CODE, /*交易市场*/
           CAT.C_CAT_CODE AS C_SEC_VAR_CODE, /*期货品种*/
           SEC.FTRADECURY AS C_DC_CODE, /*交易币种*/
           0 AS N_PRICE_FCR,
           '' AS C_SEC_CODE_TRG, /*合约标的*/
           0 AS N_AMOUNT_HD,
           FU.FMULTIPLE AS N_FV_ISSUE, /*合约乘数*/
           CASE
             WHEN SEC.FSTARTDATE IS NULL THEN
              TO_DATE('1900-1-1')
             ELSE
              SEC.FSTARTDATE
           END AS D_TO_LIST, /*上市日期*/
           TO_DATE('9998-12-31', 'yyyy-MM-dd') AS D_OFF_LIST, /*交割日期*/
           FU.FDEPDURCODE AS C_DV_VAR_DUR, /*期货期限*/
           ' ' C_DV_QUT_MOD, /*交割方式*/
           FU.FBAILSCALE AS N_RATE, /*保证金比例*/
           0 AS N_FV_IR,
           FU.FBAILFIX AS N_PRICE_ISSUE, /*固定保证金*/
           ' ' AS C_DV_AI_MOD,
           ' ' AS C_DV_AI_EXPR,
           ' ' AS C_DV_PI_MOD,
           '' AS D_AI_BEGIN,
           TO_DATE('9998-12-31', 'yyyy-MM-dd') AS D_AI_END, /*最后交易日*/
           SEC.FDESC AS C_DESC, /*描述*/
           SEC.FCHECKSTATE AS N_CHECK_STATE,
           SEC.FCREATOR AS C_UPDATE_BY,
           SEC.FCREATETIME AS C_UPDATE_TIME,
           SEC.FCHECKUSER AS C_CHECK_BY,
           SEC.FCHECKTIME AS C_CHECK_TIME
      FROM TB_006_PARA_INDEXFUTURES FU
      JOIN TB_006_PARA_SECURITY SEC
        ON SEC.FSECURITYCODE = FU.FSECURITYCODE
      JOIN T_TRANS_SECCAT CAT
        ON SEC.FSUBCATCODE = CAT.FSUBCATCODE
      left join t_trans_mkt mkt
        on mkt.fexchangecode=sec.fexchangecode
     WHERE SEC.FCHECKSTATE <= 1
       AND SEC.FCATCODE = 'FU';
  COMMIT;
  ORESULT := 'SUCCESS';
EXCEPTION
  WHEN OTHERS THEN
    V_ERRORCODE := SQLCODE;
    V_ERRORTEXT := SUBSTR(SQLERRM, 1, 2000);
    OERRTEXT    := V_ERRORTEXT;
    ORESULT     := 'ERROR';
    ROLLBACK;
    WRITELOG('PRO_TRANS_SEC_QH', V_ERRORCODE, V_ERRORTEXT);
END PRO_TRANS_SEC_QH;

  /* 放大倍数映射为合约乘数?,期货期限目标表有字段,但该字段没用上 */
