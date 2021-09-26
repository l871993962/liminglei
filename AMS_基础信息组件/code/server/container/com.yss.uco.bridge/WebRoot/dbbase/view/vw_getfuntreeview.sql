create or replace view vw_getfuntreeview as
select level as N_LEVEL,a.C_FUN_CODE,C_FUN_NAME,C_ICO_FILE,C_FUN_CODE_P,C_SRC_MARK,C_DV_FUN_TYPE,
       C_DV_FUN_CLS,C_DV_FUN_LEVEL,N_ORDER,C_COMP_FILE,C_FUN_PARAMS,C_DV_STATE,
       C_DESC,1 as N_CHECK_STATE, ' ' as C_UPDATE_BY,' ' as C_UPDATE_TIME,' ' as C_CHECK_BY,
       ' ' as C_CHECK_TIME, N_CHECK,N_LOCK,N_RECYCLE,N_USER From T_S_FUN a
 Connect by prior a.C_FUN_CODE = a.C_FUN_CODE_P start with a.C_Fun_Code_P = '[root]'
 order by Level,a.N_Order;