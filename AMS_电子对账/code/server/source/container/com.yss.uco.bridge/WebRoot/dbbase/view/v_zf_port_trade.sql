create or replace view v_zf_port_trade as
select c_iden as n_id,
       to_char(d_trade, 'yyyymmdd') as c_bizdate,
       to_char(D_SETT_FACT, 'yyyymmdd') as c_deliverdate,
       to_char(D_SETT_DUE, 'yyyymmdd') as n_repurchasedate,
       c_port_code,
       c_sec_code,
       c_mkt_code,
       SubStr(c_sec_var_code, 1, INSTR(c_sec_var_code, '_') - 1) as c_sec_var,
       ivt.c_sec_var_code as c_sec_var_mx,
       case when C_DT_CODE in ('CJJY_CC', 'ZQJY_BUY', 'JJJY_BUY', 'HGJY_NHG') then '00' else '10' end as c_tradekind,
       decode(c_dv_invest_cls, 'IC_TD', 'C', 'IC_FS', 'S', 'IC_HM', 'F', 'IC_SL', 'Y', 'C') as c_investtype,
       'PT' as c_trademodel,
       null as c_assisttradekind,
       C_TD_NO as c_tradenum,
       N_TD_AMOUNT as n_qty,
       N_TD_MONEY as n_amount,
       N_SETT_MONEY_DUE as n_occursum,
       N_INCOME as n_accrualinterest,
       null as n_repointerest,
       N_TD_PRICE as n_strikeprice
  from T_D_AC_TRADE_IVT ivt
 where ivt.n_check_state = 1
union all
select dep.c_iden as n_id,
       to_char(dep.D_TRADE, 'yyyymmdd') as c_bizdate,
       to_char(dep.D_SETT_DUE, 'yyyymmdd') as c_deliverdate,
       to_char(dep.D_SETT_FACT, 'yyyymmdd') as n_repurchasedate,
       dep.c_port_code,
       dep.c_sec_code,
       b.c_mkt_code,
       SubStr(b.c_sec_var_code, 1, INSTR(b.c_sec_var_code, '_') - 1) as c_sec_var,
       b.c_sec_var_code as c_sec_var_mx,
       case when C_DT_CODE = 'CKTZ_CFTY' and c_dv_type_sub = 'CKTZ_SQ' then '00' else '10' end as c_tradekind,
       'QT' as c_investtype,
       'PT' as c_trademodel,
       null as c_assisttradekind,
       C_TD_NO as c_tradenum,
       1 as n_qty,
       N_TD_MONEY as n_amount,
       N_TD_MONEY as n_occursum,
       N_INCOME as n_accrualinterest,
       N_RATE as n_repointerest,
       1 as n_strikeprice
  from T_D_AC_TRADE_DEP dep
  left join (select c_sec_code, c_sec_var_code, c_mkt_code from T_P_SV_SEC_BASE) b
    on dep.c_sec_code = b.c_sec_code
 where dep.n_check_state = 1 and dep.c_td_type <> 'PJJY'
union all
select dep.c_iden as n_id,
       to_char(dep.D_TRADE, 'yyyymmdd') as c_bizdate,
       to_char(dep.D_SETT_DUE, 'yyyymmdd') as c_deliverdate,
       to_char(dep.D_SETT_FACT, 'yyyymmdd') as n_repurchasedate,
       dep.c_port_code,
       pj.c_sec_code,
       b.c_mkt_code,
       SubStr(b.c_sec_var_code, 1, INSTR(b.c_sec_var_code, '_') - 1) as c_sec_var,
       b.c_sec_var_code as c_sec_var_mx,
       case when C_DT_CODE = 'PJJY_BUY' then '00' else '10' end as c_tradekind,
       'QT' as c_investtype,
       'PT' as c_trademodel,
       null as c_assisttradekind,
       C_TD_NO as c_tradenum,
       1 as n_qty,
       pj.N_TD_MONEY as n_amount,
       pj.N_TD_MONEY as n_occursum,
       pj.N_INCOME as n_accrualinterest,
       N_RATE as n_repointerest,
       1 as n_strikeprice
  from T_D_AC_TRADE_DEP dep
  left join T_D_AC_TRADE_DEP_PJ pj on dep.c_iden = pj.c_iden_rela
  left join (select c_sec_code, c_sec_var_code, c_mkt_code from T_P_SV_SEC_BASE) b
    on pj.c_sec_code = b.c_sec_code
 where dep.n_check_state = 1 and dep.c_td_type = 'PJJY'
 union all
 select sale.c_iden as n_id,
       to_char(sale.D_TRADE, 'yyyymmdd') as c_bizdate,
       to_char(sale.D_SETT_DUE, 'yyyymmdd') as c_deliverdate,
       null as n_repurchasedate,
       sale.c_port_code,
       sale.c_sec_code,
       b.c_mkt_code,
       SubStr(b.c_sec_var_code, 1, INSTR(b.c_sec_var_code, '_') - 1) as c_sec_var,
       b.c_sec_var_code as c_sec_var_mx,
       case when C_DT_CODE in ('CWSS_RGSQ','CWSS_SGSQ') then '00' else '10' end as c_tradekind,
       'QT' as c_investtype,
       'PT' as c_trademodel,
       null as c_assisttradekind,
       null as c_tradenum,
       N_TD_AMOUNT as n_qty,
       case when C_DT_CODE in ('CWSS_RGSQ','CWSS_SGSQ','CWSS_SHQR') then N_TD_MONEY when C_DT_CODE in ('CWSS_RGQR','CWSS_SGQR') then N_SETT_MONEY_DUE * -1 else 0 end as n_amount,
       case when C_DT_CODE in ('CWSS_RGSQ','CWSS_SGSQ','CWSS_SHQR') then N_TD_MONEY when C_DT_CODE in ('CWSS_RGQR','CWSS_SGQR') then N_SETT_MONEY_DUE * -1 else 0 end as n_accrualinterest,
       N_INCOME as n_accrualinterest,
       b.n_rate as n_repointerest,
       N_TD_PRICE as n_strikeprice
  from T_D_AC_TRADE_SALE sale
 left join (select c_sec_code, c_sec_var_code, c_mkt_code, n_rate from T_P_SV_SEC_BASE) b
    on sale.c_sec_code = b.c_sec_code;
