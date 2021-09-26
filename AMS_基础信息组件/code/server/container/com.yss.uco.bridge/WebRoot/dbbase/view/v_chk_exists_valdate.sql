create or replace view v_chk_exists_valdate as
select distinct d_aststat,c_port_code from T_R_FR_ASTSTAT where c_port_code = '888888';
