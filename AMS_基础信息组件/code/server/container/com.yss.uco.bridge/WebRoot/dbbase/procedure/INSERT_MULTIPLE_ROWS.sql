create or replace procedure insert_multiple_rows (recordNum INTEGER)

IS
   num   INTEGER := 1;
BEGIN
   LOOP
      EXIT WHEN num > recordNum;

      INSERT INTO t_d_ac_trade_ivt (C_CA_CODE_BAIL,
                                    C_CA_CODE_OT,
                                    C_CA_CODE_SETT_DUE,
                                    C_CA_CODE_SETT_FIRST,
                                    C_CFG_CODE,
                                    C_CHECK_BY,
                                    C_CHECK_TIME,
                                    C_DATA_IDF,
                                    C_DC_CODE,
                                    C_DESC,
                                    C_DT_CODE,
                                    C_DTA_CODE,
                                    C_DV_INVEST_CLS,
                                    C_DV_ISSUE_MODE,
                                    C_DV_VAR_DUR,
                                    C_EXPR_CODE,
                                    C_PORT_CODE,
                                    C_RELA_CODE,
                                    C_SEC_CODE,
                                    C_SEC_CODE_TAG,
                                    C_SEC_VAR_CODE,
                                    C_SETT_STATE,
                                    C_SH_ACC_CODE,
                                    C_TD_CHAN_CODE,
                                    C_TD_NO,
                                    C_TD_TYPE,
                                    C_UPDATE_BY,
                                    C_UPDATE_TIME,
                                    D_DATE,
                                    D_SETT_DUE,
                                    D_SETT_FACT,
                                    D_SETT_FIRST,
                                    D_TRADE,
                                    ID_D_AC_TD_IVT,
                                    ID_D_AC_TD_IVT_SUB,
                                    N_CHECK_STATE,
                                    N_INCOME,
                                    N_SETT_MONEY_DUE,
                                    N_SETT_MONEY_FIRST,
                                    N_TD_AMOUNT,
                                    N_TD_MONEY,
                                    N_TD_PRICE)
         (SELECT   A.C_CA_CODE_BAIL,
                   A.C_CA_CODE_OT,
                   A.C_CA_CODE_SETT_DUE,
                   A.C_CA_CODE_SETT_FIRST,
                   A.C_CFG_CODE,
                   A.C_CHECK_BY,
                   A.C_CHECK_TIME,
                   A.C_DATA_IDF,
                   A.C_DC_CODE,
                   A.C_DESC,
                   A.C_DT_CODE,
                   A.C_DTA_CODE,
                   A.C_DV_INVEST_CLS,
                   A.C_DV_ISSUE_MODE,
                   A.C_DV_VAR_DUR,
                   A.C_EXPR_CODE,
                   A.C_PORT_CODE,
                   A.C_RELA_CODE,
                   A.C_SEC_CODE,
                   A.C_SEC_CODE_TAG,
                   A.C_SEC_VAR_CODE,
                   A.C_SETT_STATE,
                   A.C_SH_ACC_CODE,
                   A.C_TD_CHAN_CODE,
                   A.C_TD_NO,
                   A.C_TD_TYPE,
                   A.C_UPDATE_BY,
                   A.C_UPDATE_TIME,
                   A.D_DATE,
                   A.D_SETT_DUE,
                   A.D_SETT_FACT,
                   A.D_SETT_FIRST,
                   A.D_TRADE,
                   SEQ_D_AC_TRADE_IVT.NEXTVAL AS ID_D_AC_TD_IVT,
                   SEQ_D_AC_TRADE_IVT.CURRVAL AS ID_D_AC_TD_IVT_SUB,
                   A.N_CHECK_STATE,
                   A.N_INCOME,
                   A.N_SETT_MONEY_DUE,
                   A.N_SETT_MONEY_FIRST,
                   A.N_TD_AMOUNT,
                   A.N_TD_MONEY,
                   A.N_TD_PRICE
            FROM   T_D_AC_TRADE_IVT a
           WHERE       c_dt_code = 'GPJY_SELL'
                   AND c_port_Code = 'WCCTEST'
                   AND d_trade = TO_DATE ('2011-11-04', 'yyyy-MM-dd')
                   AND c_sec_code = '600831 CG'
                   and  ID_D_AC_TD_IVT = 1008153043);

      num := num + 1;
   END LOOP;
END;
/
