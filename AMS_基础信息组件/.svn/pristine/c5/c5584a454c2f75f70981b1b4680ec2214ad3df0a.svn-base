create or replace view v_r_rm_report_fun as
select fname as C_FUN_NAME,
       fcode as C_FUN_CODE,
       'BB' as C_FUN_CODE_P,
       'RC' as C_SRC_MARK,
       'CLS_MOD' as C_DV_FUN_TYPE,
       'D_BB' as C_DV_FUN_CLS,
       'PUB' as C_DV_FUN_LEVEL,
       2 as N_LEVEL
  from t_reportcent_catalog@v45replink1 c
 where c.fsystem_code = 'YSSUCO'
union
select fname as C_FUN_NAME,
       fcode as C_FUN_CODE,
       r.fcatalog_code as C_FUN_CODE_P,
       'RC' as C_SRC_MARK,
       'MEU_FUN' as C_DV_FUN_TYPE,
       'D_BB' as C_DV_FUN_CLS,
       'PUB' as C_DV_FUN_LEVEL,
       3 as N_LEVEL
  from t_reportcent_report@v45replink1 r
  where r.fsystem_code = 'YSSUCO'
union
select f.c_fun_name,
       f.c_fun_code,
       f.c_fun_code_p,
       f.c_src_mark,
       f.c_dv_fun_type,
       f.c_dv_fun_cls,
       f.C_DV_FUN_LEVEL,
       1 as N_LEVEL
  from T_S_FUN f
 where f.c_fun_code = 'BB';
