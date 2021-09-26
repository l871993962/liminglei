create or replace procedure PRC_SEC_INPUT_CHECK(tableName in varchar2,
                                                fields    in varchar2,
                                                keyValues in varchar2,
                                                res       out varchar2) is

  v_fields      LONG DEFAULT fields || ',';
  v_values      LONG DEFAULT keyvalues || ',';
  v_field       VARCHAR2(2000);
  v_value       VARCHAR2(2000);
  v_n_field     VARCHAR2(2000);
  v_n_value     VARCHAR2(2000);
  v_start_date  DATE;
  v_end_date    DATE;
  v_sec_code    VARCHAR2(2000);
  v_to_list     DATE;
  v_off_list    DATE;
  v_checkCnt    NUMBER(14, 0);
  v_start_flag  BOOLEAN;
  v_end_flag    BOOLEAN;
  v_sec_cnt     NUMBER(14,0);

begin
  v_checkCnt := 0;

  LOOP
    v_n_field := instr(v_fields, ',');
    v_n_value := instr(v_values, ',');
    EXIT WHEN(NVL(v_n_field, 0) = 0);

    v_field := LTRIM(RTRIM(SUBSTR(v_fields, 1, v_n_field - 1)));
    v_value := LTRIM(RTRIM(SUBSTR(v_values, 1, v_n_value - 1)));


    IF (v_field = 'd_TO_LIST') THEN
      SELECT TO_DATE(v_value, 'yyyy-MM-dd') INTO v_start_date FROM DUAL;
    ELSIF (v_field = 'd_OFF_LIST') THEN
      SELECT TO_DATE(v_value, 'yyyy-MM-dd') INTO v_end_date FROM DUAL;
    ELSE
      v_sec_code := v_value;
    END IF;

    v_fields := SUBSTR(v_fields, v_n_field + 1);
    v_values := SUBSTR(v_values, v_n_value + 1);
  END LOOP;

  SELECT COUNT(C_SEC_CODE)
  INTO v_sec_cnt
  FROM T_P_SV_SEC_BASE
  WHERE C_SEC_CODE = v_sec_code;

  IF v_sec_cnt > 0 THEN
    SELECT D_TO_LIST, D_OFF_LIST
    INTO v_to_list, v_off_list
    FROM T_P_SV_SEC_BASE
    WHERE C_SEC_CODE = v_sec_code;

    IF v_start_date >= v_to_list AND v_start_date <= v_off_list THEN
      v_start_flag := false;
    ELSE
      v_start_flag := true;
    END IF;

    IF v_end_date >= v_to_list AND v_end_date <= v_off_list THEN
      v_end_flag := false;
    ELSE
      v_end_flag := true;
    END IF;
  ELSE
    v_start_flag := true;
    v_end_flag := true;
  END IF;

  IF v_start_flag AND v_end_flag THEN
    res := 'Success';
  ELSE
    res := '当前股票上市时间区间存在重复数据。';
  END IF;

end PRC_SEC_INPUT_CHECK;
