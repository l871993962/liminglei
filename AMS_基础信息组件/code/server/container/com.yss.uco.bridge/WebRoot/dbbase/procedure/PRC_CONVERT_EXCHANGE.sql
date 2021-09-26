CREATE OR REPLACE PROCEDURE PRC_CONVERT_EXCHANGE
IS
  v_sql VARCHAR2(2000);

BEGIN

      --不存在的交易所
      v_sql := 'insert into t_p_bi_mkt
    (c_mkt_code, c_mkt_name, c_dv_mkt_type, c_org_code, c_de_code, c_hday_code, c_area_code, n_mkt_attr, n_sett_days, c_desc, n_check_state, c_update_by, c_update_time, c_check_by, c_check_time)
     select FEXCHANGECODE, FEXCHANGENAME,  ''FTM'', '' '', '' '', '' '','' '', 1, fsettledays, FDESC,  1,''sysdbo'',to_char(sysdate,''yyyy-mm-dd hh:mm:ss:''),
       ''sysdbo'',to_char(sysdate,''yyyy-mm-dd hh:mm:ss:'') from jhqdii.Tb_Base_Exchange a where not exists(select * from t_p_bi_mkt c where a.fexchangecode = c.c_mkt_code )';

      EXECUTE IMMEDIATE (v_sql);

END;
