create or replace procedure insert_shhq_date()
as
  declare cursor c1 is
    select distinct d_date as d_date from t_d_od_show where s1<>'000000';

  v_hqdate t_d_od_show.d_date%TYPE;

  begin
    open c1;
    loop
      fetch c1 into v_hqdate;
      dbms_output.put_line(v_hqdate);
      insert into t_d_od_show
        (S31, S32, S33, C_PATH, D_DATE, C_USER, C_HDAY_CODE, N_ADJUST, C_PATH_TYPE, C_CFG_CODE,
        S5, S6, S7, S8, S9, S10, S11, S13, S15, S16, S17, S18, S19, S21, S22, S1, S2, S3, S4,
        S23, S24, S25, S26, S27, S28, S29, S30)
      values
        ('0', '0', '0', 'SHOW2003', v_hqdate, '¿Ó—‡', 'null', 0, 'H', 'SHOW2003',
        '0', to_char(v_hqdate,'yyyymmdd'), '0', '0', '0', '0', '1111111111', '2374.843',
        '1', '0', '1', '0', '0', '0', '0', '000000', '150308  ', '2487.28', '244.907',
        '0', '0', '0', '0', '0', '0', '0', '0');
      exit with c1%NOTFOUND;
    end loop;
    commit;
  end;
/
