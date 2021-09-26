create or replace view v_p_sv_sec_base as
select sec."C_SEC_CODE",sec."C_SEC_NAME",sec."C_SEC_MKT_CODE",sec."C_SEC_ISIN_CODE",sec."C_MKT_CODE",sec."C_SEC_VAR_CODE",sec."C_DC_CODE",sec."N_PRICE_FCR",sec."C_SEC_CODE_TRG",sec."N_AMOUNT_HD",sec."N_FV_ISSUE",sec."D_TO_LIST",sec."D_OFF_LIST",sec."C_DV_VAR_DUR",sec."C_DV_QUT_MOD",sec."N_RATE",sec."N_FV_IR",sec."N_PRICE_ISSUE",sec."C_DV_AI_MOD",sec."C_DV_PI_MOD",sec."D_AI_BEGIN",sec."D_AI_END",sec."C_DESC",sec."N_CHECK_STATE",sec."C_UPDATE_BY",sec."C_UPDATE_TIME",sec."C_CHECK_BY",sec."C_CHECK_TIME",sec."C_DV_AI_EXPR",sec."C_IDEN",sec."C_ORG_CODE",
       sar2.c_da_code      as c_da_main_code,
       sar2.c_sec_var_name as c_da_main_name,
       sar.c_da_code,
       sar.c_sec_var_name
  from T_P_SV_SEC_BASE sec
  left join T_S_DA_SEC_VAR sar
    on sec.c_sec_var_code = sar.c_sec_var_code
  left join T_S_DA_SEC_VAR sar2
    on substr(sar.c_da_code, 1, instr(sar.c_da_code, '_', 1, 1) - 1) =
       sar2.c_da_code;
