create or replace view v_zf_port_pool_trade as
select o.c_pool_code as c_port_code,
       to_char(o.d_trade, 'yyyymmdd') as c_bizdate,
       o.c_sec_code as c_sec_code,
       b.c_mkt_code as c_mkt_code,
       SubStr(b.c_sec_var_code, 1, INSTR(b.c_sec_var_code, '_') - 1) as c_sec_var,
       b.c_sec_var_code as c_sec_var_mx,
       case when C_DT_CODE in ('CJJY_CC', 'ZQJY_BUY', 'JJJY_BUY', 'HGJY_NHG', 'CKTZ_CFTY') then '00' else '10' end as c_tradekind,
       o.n_td_amount as n_qty,
       o.n_td_money as n_amount,
       to_char(o.d_sett_due, 'yyyymmdd') as n_repurchasedate,
       o.n_income as n_accrualinterest,
       o.n_rate as n_repointerest
  from t_d_om_order o
  join T_P_SV_SEC_BASE b
    on o.c_sec_code = b.c_sec_code
 where o.n_check_state = 1
   and o.c_dv_ord_type = 'ORD_PLAN';
