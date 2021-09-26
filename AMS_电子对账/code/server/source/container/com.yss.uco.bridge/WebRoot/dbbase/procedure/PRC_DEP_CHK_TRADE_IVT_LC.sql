create or replace procedure PRC_DEP_CHK_TRADE_IVT_LC
(tableName in varchar2,
 fields in varchar2,
 keyValues in varchar2,
 res out varchar2) is

  v_fields     LONG DEFAULT fields || ',' ;
  v_values     LONG DEFAULT keyvalues || ',';
  v_field      VARCHAR2 (2000);
  v_value      VARCHAR2 (2000);
  v_n_field    VARCHAR2 (2000);
  v_n_value    VARCHAR2 (2000);
  v_sql        VARCHAR2 (2000);
  v_quyCon     VARCHAR2 (2000);
  v_quyConCom  VARCHAR2 (2000);
  v_checkCnt   NUMBER(14,0);
  v_recCnt   NUMBER(14,0);

  v_td_type_con VARCHAR2 (2000);

  v_n_table_name    VARCHAR2 (2000);
  v_dep_table_name VARCHAR2 (2000);
  v_chp_table_name VARCHAR2 (2000);

begin
  v_quyCon := ' ';
  v_quyConCom := ' ';
  v_td_type_con := ' ';
  v_checkCnt := 0;
   LOOP
      v_n_field := instr (v_fields, ',');
      v_n_value := instr (v_values, ',');
      EXIT WHEN (NVL (v_n_field, 0) = 0);

      v_field := LTRIM (RTRIM (SUBSTR (v_fields, 1, v_n_field - 1)));
      v_value := LTRIM (RTRIM (SUBSTR (v_values, 1, v_n_value - 1)));

      IF (instr(v_quyCon, 'WHERE') = 0) THEN
        v_quyCon := ' WHERE ';
      END IF;

      IF (instr(v_quyConCom, 'WHERE') = 0) THEN
        v_quyConCom := ' WHERE ';
      END IF;

      IF (v_field = 'C_CA_CODE') THEN
        v_quyCon := v_quyCon || 'C_CA_CODE_SETT_DUE=''' || v_value || ''' AND';
      ELSE
        v_quyCon := v_quyCon || v_field || '=''' || v_value || ''' AND';
      END IF;

      v_quyConCom := v_quyConCom || v_field || '=''' || v_value || ''' AND';

      v_fields := SUBSTR (v_fields, v_n_field + 1);
      v_values := SUBSTR (v_values, v_n_value + 1);
   END LOOP;

   v_n_table_name  := instr (tableName, ',');
   v_dep_table_name := LTRIM (RTRIM (SUBSTR (tableName, 1, v_n_table_name - 1)));

   v_chp_table_name := SUBSTR (tableName, v_n_table_name + 1);

   IF (LENGTH(v_quyCon) > 0) THEN
      v_quyCon := SUBSTR (v_quyCon, 1, (LENGTH(v_quyCon) -4));
      v_quyConCom := SUBSTR (v_quyConCom, 1, (LENGTH(v_quyConCom) -4));
      v_td_type_con := ' AND C_TD_TYPE LIKE ''JJ%'' ';

      v_sql := 'SELECT COUNT (*) FROM ' || v_dep_table_name || v_quyCon || v_td_type_con;
      dbms_output.put_line(v_sql);

      BEGIN
        EXECUTE IMMEDIATE v_sql
        INTO v_checkCnt;

        EXCEPTION
          WHEN OTHERS THEN
            res := 'Exception'; /*异常情况返回 NULL*/
      END;

      v_sql := 'SELECT COUNT (*) FROM ' || v_chp_table_name ||  v_quyConCom;
      dbms_output.put_line(v_sql);

      BEGIN
        EXECUTE IMMEDIATE v_sql
        INTO v_recCnt;

        EXCEPTION
          WHEN OTHERS THEN
            res := 'Exception'; /*异常情况返回 NULL*/
      END;

      IF(v_recCnt < 2) THEN
        IF(v_checkCnt > 0) THEN
          res := 'Fault';
        ELSE
          res := 'Success';
        END IF;
      ELSE
        res := 'Success';
      END IF;

   END IF;

end PRC_DEP_CHK_TRADE_IVT_LC;
