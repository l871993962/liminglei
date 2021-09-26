create or replace procedure PRC_DPD_CHK_SECSWAP
(tableName in varchar2,
 fields in varchar2,
 keyValues in varchar2,
 res out varchar2) is

 v_fields  LONG DEFAULT fields || ',';
  v_values  LONG DEFAULT keyvalues || ',';
  v_field   VARCHAR2(2000);
  v_value   VARCHAR2(2000);
  v_n_field VARCHAR2(2000);
  v_n_value VARCHAR2(2000);
  v_sql     VARCHAR2(2000);
  v_quyCon  VARCHAR2(2000);
  type cur_type is ref cursor; /*定义游标类型*/
  my_Cur cur_type; /*自定一个游标*/
begin
  v_quyCon := ' ';
  LOOP
    v_n_field := instr(v_fields, ',');
    v_n_value := instr(v_values, ',');
    EXIT WHEN(NVL(v_n_field, 0) = 0);
  
    v_field := LTRIM(RTRIM(SUBSTR(v_fields, 1, v_n_field - 1)));
    v_value := LTRIM(RTRIM(SUBSTR(v_values, 1, v_n_value - 1)));
  
    IF (instr(v_quyCon, 'WHERE') = 0) THEN
      v_quyCon := ' WHERE ';
    END IF;
  
    v_quyCon := v_quyCon || v_field || '=''' || v_value || ''' AND ';
  
    v_fields := SUBSTR(v_fields, v_n_field + 1);
    v_values := SUBSTR(v_values, v_n_value + 1);
  END LOOP;
  v_value := '';
  IF (LENGTH(v_quyCon) > 0) THEN
    v_quyCon := SUBSTR(v_quyCon, 1, (LENGTH(v_quyCon) - 5));
    v_sql    := 'SELECT /*+ FIRST_ROWS(1) */ ' || v_field || ' FROM ' || tableName || v_quyCon;
    --dbms_output.put_line(v_sql);
  
    BEGIN
      /*EXECUTE IMMEDIATE v_sql
      INTO v_value;*/
      OPEN my_Cur FOR v_Sql; /*打开游标*/
      LOOP
        FETCH my_Cur
          into v_value;
        IF (my_Cur%NOTFOUND) THEN
          res := 'Success';
        ELSE
          res := 'Fault';
        END IF;
        EXIT;
      END LOOP;
      CLOSE my_Cur; /*关闭游标*/
    EXCEPTION
      WHEN OTHERS THEN
        res := 'Exception'; /*异常情况返回 NULL*/
      --CLOSE my_Cur; /*关闭游标*/
    END;
  
    /*IF(v_checkCnt > 0) THEN
      res := 'Fault';
    ELSE
      res := 'Success';
    END IF;*/
  END IF;
end PRC_DPD_CHK_SECSWAP;
/
