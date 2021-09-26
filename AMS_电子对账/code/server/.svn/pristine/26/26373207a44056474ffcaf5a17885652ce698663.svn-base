create or replace view v_p_ab_port as
select port."C_PORT_CODE",port."C_PORT_NAME",port."C_DAT_CODE",port."C_PORT_NAME_ST",port."C_PORT_NAME_EN",port."C_ASS_CODE",port."C_DC_CODE",port."D_BUILD",port."D_CLOSE",port."C_DESC",port."N_CHECK_STATE",port."C_UPDATE_BY",port."C_UPDATE_TIME",port."C_CHECK_BY",port."C_CHECK_TIME",port."C_PORT_CODE_P",port."C_DV_PORT_CODE",port."C_HDAY_CODE",port."C_IDEN",
       cury.c_dc_name,
       asstype.c_dat_code_p,
       mange.c_org_name as c_org_manage,
       trst.c_org_name  as c_org_trustee,
       sec.c_org_name   as c_org_trustee_sec
  from t_p_ab_port port
  left join T_S_DC_CURY cury on port.c_dc_code = cury.c_dc_code
  left join (select * from T_S_DAT_ASS_TYPE) asstype
    on port.c_dat_code = asstype.c_dat_code
  left join (select distinct r.c_port_code, r.c_rela_code, o.c_org_name
               from T_P_AB_PORT_RELA r
               join t_p_bi_org o
                 on r.c_rela_code = o.c_org_code
              where r.c_dv_type_code = 'MANAGER'
                and r.n_check_state = 1) mange
    on port.c_port_code = mange.c_port_code
  left join (select distinct r.c_port_code, r.c_rela_code, o.c_org_name
               from T_P_AB_PORT_RELA r
               join t_p_bi_org o
                 on r.c_rela_code = o.c_org_code
              where r.c_dv_type_code = 'TRUSTEE'
                and r.n_check_state = 1) trst
    on port.c_port_code = trst.c_port_code
  left join (select distinct r.c_port_code, r.c_rela_code, o.c_org_name
               from T_P_AB_PORT_RELA r
               join t_p_bi_org o
                 on r.c_rela_code = o.c_org_code
              where r.c_dv_type_code = 'TRUSTEE_SEC'
                and r.n_check_state = 1) sec
    on port.c_port_code = sec.c_port_code
 where port.n_check_state = 1;
