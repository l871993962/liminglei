create or replace procedure PRC_DATECROSS_CHECK_DEFAULT
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
  v_checkCnt   NUMBER(14,0);
  v_begin_date_field VARCHAR2 (2000);
  v_end_date_field VARCHAR2 (2000);

  v_begin_date_value VARCHAR2 (2000);
  v_end_date_value VARCHAR2 (2000);

  v_field_order NUMBER(14,0);
  v_check_ok_cnt NUMBER(14,0);

  v_sql_date_con VARCHAR2 (2000);
  v_check_sta_sql VARCHAR2 (2000);
  v_bu_code_val  VARCHAR2 (2000);
  v_bu_code_val_cross VARCHAR2 (2000);
  v_field_cross       VARCHAR2 (2000);
begin
  v_quyCon := ' ';
  v_checkCnt := 0;
  v_field_order := 1;
  v_check_ok_cnt := 0;
  v_bu_code_val := ' ';
  v_bu_code_val_cross := ' ';
  v_field_cross := ' ';

  LOOP
      v_n_field := instr (v_fields, ',');
      v_n_value := instr (v_values, ',');
      EXIT WHEN (NVL (v_n_field, 0) = 0);

      v_field := LTRIM (RTRIM (SUBSTR (v_fields, 1, v_n_field - 1)));
      v_value := LTRIM (RTRIM (SUBSTR (v_values, 1, v_n_value - 1)));

      IF (v_value IS NULL) THEN
        v_value := ' ';
      END IF;

      IF (instr(v_quyCon, 'WHERE') = 0) THEN
        v_quyCon := ' WHERE ';
      END IF;

      IF (v_field_order = 1) THEN
        v_begin_date_field := v_field;
        v_begin_date_value := v_value;
      ELSIF (v_field_order = 2) THEN
        v_end_date_field := v_field;
        v_end_date_value := v_value;
      ELSIF (v_field_order = 3) THEN
        v_bu_code_val := v_value;
        v_field_cross := v_field;
        v_quyCon := v_quyCon || v_field || '=''' || v_value || ''' AND ';
      ELSE
        v_quyCon := v_quyCon || v_field || '=''' || v_value || ''' AND ';
      END IF;

      v_fields := SUBSTR (v_fields, v_n_field + 1);
      v_values := SUBSTR (v_values, v_n_value + 1);

      v_field_order := v_field_order + 1;

   END LOOP;

   IF (LENGTH(v_quyCon) > 0) THEN
     --v_check_sta_sql := ' AND N_CHECK_STATE = 1 ';
      v_quyCon := SUBSTR (v_quyCon, 1, (LENGTH(v_quyCon) -4));
      --开始日期在区间内
      v_sql_date_con := ' AND TO_DATE(''' || v_begin_date_value || ''', ''yyyy-MM-dd'') BETWEEN ' || v_begin_date_field || ' AND ' || v_end_date_field;
      v_sql := 'SELECT COUNT (' || v_field || ') FROM ' || tableName || v_quyCon || v_sql_date_con || v_check_sta_sql;
      dbms_output.put_line(v_sql);

      BEGIN
        EXECUTE IMMEDIATE v_sql
        INTO v_checkCnt;

        EXCEPTION
          WHEN OTHERS THEN
            res := 'Exception'; /*异常情况返回 NULL*/
      END;

      IF(v_checkCnt = 0) THEN
        v_check_ok_cnt := v_check_ok_cnt + 1;
      ELSE
         IF ( v_bu_code_val_cross = ' ' ) THEN
           v_sql := 'SELECT ' || v_field_cross || ' FROM ' || tableName || v_quyCon || v_sql_date_con || v_check_sta_sql || ' AND rownum = 1 ';

           dbms_output.put_line(v_sql);

          BEGIN
            EXECUTE IMMEDIATE v_sql
            INTO v_bu_code_val_cross;

            EXCEPTION
              WHEN OTHERS THEN
                res := 'Exception'; /*异常情况返回 NULL*/
          END;
        END IF;
      END IF;

      --结束日期在区间内
      v_sql_date_con := ' AND TO_DATE(''' || v_end_date_value || ''', ''yyyy-MM-dd'') BETWEEN ' || v_begin_date_field || ' AND ' || v_end_date_field;
      v_sql := 'SELECT COUNT (' || v_field || ') FROM ' || tableName || v_quyCon || v_sql_date_con|| v_check_sta_sql;
      dbms_output.put_line(v_sql);

      BEGIN
        EXECUTE IMMEDIATE v_sql
        INTO v_checkCnt;

        EXCEPTION
          WHEN OTHERS THEN
            res := 'Exception'; /*异常情况返回 NULL*/
      END;

      IF(v_checkCnt = 0) THEN
        v_check_ok_cnt := v_check_ok_cnt + 1;
      ELSE
        IF ( v_bu_code_val_cross = ' ' ) THEN
           v_sql := 'SELECT ' || v_field_cross || ' FROM ' || tableName || v_quyCon || v_sql_date_con|| v_check_sta_sql  || ' AND rownum = 1 ';

           dbms_output.put_line(v_sql);

          BEGIN
            EXECUTE IMMEDIATE v_sql
            INTO v_bu_code_val_cross;

            EXCEPTION
              WHEN OTHERS THEN
                res := 'Exception'; /*异常情况返回 NULL*/
          END;
        END IF;
      END IF;

      --输入日期包含存在日期
      v_sql_date_con := ' AND (' || v_begin_date_field || ' BETWEEN TO_DATE(''' || v_begin_date_value || ''', ''yyyy-MM-dd'') AND TO_DATE(''' || v_end_date_value || ''', ''yyyy-MM-dd'')  AND ' || v_end_date_field || ' BETWEEN TO_DATE(''' || v_begin_date_value || ''', ''yyyy-MM-dd'') AND TO_DATE(''' || v_end_date_value || ''', ''yyyy-MM-dd'')) ';
      v_sql := 'SELECT COUNT (' || v_field || ') FROM ' || tableName || v_quyCon || v_sql_date_con|| v_check_sta_sql;
      dbms_output.put_line(v_sql);

      BEGIN
        EXECUTE IMMEDIATE v_sql
        INTO v_checkCnt;

        EXCEPTION
          WHEN OTHERS THEN
            res := 'Exception'; /*异常情况返回 NULL*/
      END;

      IF(v_checkCnt = 0) THEN
        v_check_ok_cnt := v_check_ok_cnt + 1;
      ELSE
        IF ( v_bu_code_val_cross = ' ' ) THEN
           v_sql := 'SELECT ' || v_field_cross || ' FROM ' || tableName || v_quyCon || v_sql_date_con|| v_check_sta_sql  || ' AND rownum = 1 ';

           dbms_output.put_line(v_sql);

          BEGIN
            EXECUTE IMMEDIATE v_sql
            INTO v_bu_code_val_cross;

            EXCEPTION
              WHEN OTHERS THEN
                res := 'Exception'; /*异常情况返回 NULL*/
          END;
        END IF;
      END IF;

      IF(v_check_ok_cnt = 3) THEN
        res := 'Success';
      ELSE
        res := '【' || v_bu_code_val || '】 的日期范围与 【' || v_bu_code_val_cross || '】 的日期范围交叉，不能#OperName#';
      END IF;
   END IF;

end PRC_DATECROSS_CHECK_DEFAULT;
