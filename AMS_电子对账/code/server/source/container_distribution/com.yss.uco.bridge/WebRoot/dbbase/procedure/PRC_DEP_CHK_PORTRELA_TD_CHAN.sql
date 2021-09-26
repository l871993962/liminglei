create or replace procedure PRC_DEP_CHK_PORTRELA_TD_CHAN
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
  v_quyCondep     VARCHAR2 (2000);
  v_checkCnt   NUMBER(14,0);
  v_recCnt   NUMBER(14,0);

  v_n_table_name    VARCHAR2 (2000);
  v_dep_table_name VARCHAR2 (2000);
  v_chp_table_name VARCHAR2 (2000);

begin
  v_quyCon := ' ';
  v_quyCondep  := ' ';
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

      IF (instr(v_quyCondep, 'WHERE') = 0) THEN
        v_quyCondep := ' WHERE ';
      END IF;

      IF(v_field='C_TD_CHAN_CODE') THEN
        v_quyCon := v_quyCon || 'C_RELA_CODE=''' || v_value || ''' AND';
      ELSE
        v_quyCon := v_quyCon || v_field || '=''' || v_value || ''' AND';
      END IF;
      v_quyCondep := v_quyCondep || v_field || '=''' || v_value || ''' AND';
      v_fields := SUBSTR (v_fields, v_n_field + 1);
      v_values := SUBSTR (v_values, v_n_value + 1);
   END LOOP;

   v_n_table_name  := instr (tableName, ',');
   v_dep_table_name := LTRIM (RTRIM (SUBSTR (tableName, 1, v_n_table_name - 1)));

   v_chp_table_name := SUBSTR (tableName, v_n_table_name + 1);

   IF (LENGTH(v_quyCon) > 0) THEN
      v_quyCon := SUBSTR (v_quyCon, 1, (LENGTH(v_quyCon) -4));
      v_quyCondep := SUBSTR (v_quyCondep, 1, (LENGTH(v_quyCondep) -4));

      v_sql := 'SELECT COUNT (C_RELA_CODE) FROM ' || v_dep_table_name || v_quyCon;
      dbms_output.put_line(v_sql);

      BEGIN
        EXECUTE IMMEDIATE v_sql
        INTO v_checkCnt;

        EXCEPTION
          WHEN OTHERS THEN
            res := 'Exception'; /*异常情况返回 NULL*/
      END;

      v_sql := 'SELECT COUNT (*) FROM ' || v_chp_table_name ||  v_quyCondep;
      dbms_output.put_line(v_sql);

      BEGIN
        EXECUTE IMMEDIATE v_sql
        INTO v_recCnt;

        EXCEPTION
          WHEN OTHERS THEN
            res := 'Exception'; /*异常情况返回 NULL*/
      END;

      IF(v_recCnt = 1) THEN
        IF(v_checkCnt > 0) THEN
          res := 'Fault';
        ELSE
          res := 'Success';
        END IF;
      ELSE
        res := 'Success';
      END IF;

   END IF;

end PRC_DEP_CHK_PORTRELA_TD_CHAN;
