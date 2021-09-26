create or replace procedure PROC_DATACHECK
                                       (tableName in varchar2,
                                        fields in varchar2,
                                        keyvalues in varchar2,
                                        res out VARCHAR2) AS

  v_fields     LONG DEFAULT fields || ',' ;
  v_values     LONG DEFAULT keyvalues || ',';
  v_field      VARCHAR2 (2000);
  v_value      VARCHAR2 (2000);
  v_n          VARCHAR2 (2000);
  v_sql        VARCHAR2 (2000);
  v_quyCon     VARCHAR2 (2000);
  v_checkCnt   NUMBER(14,0);

BEGIN

   LOOP
      v_n := instr (v_fields, ',');
      EXIT WHEN (NVL (v_n, 0) = 0);

      v_field := LTRIM (RTRIM (SUBSTR (v_fields, 1, v_n - 1)));
      v_value := LTRIM (RTRIM (SUBSTR (v_values, 1, v_n - 1)));

      IF (instr(v_quyCon, 'WHERE') = 0) THEN
        v_quyCon := 'WHERE';
      END IF;

      v_quyCon := v_quyCon || v_field || '=' || v_value || ' AND';

      v_fields := SUBSTR (v_fields, v_n + 1);
      v_values := SUBSTR (v_values, v_n + 1);
   END LOOP;

   IF (LENGTH(v_quyCon) > 0) THEN
      v_quyCon := SUBSTR (v_quyCon, 1, (LENGTH(v_quyCon) -4));
      v_sql := 'SELECT COUNT (' || v_field || ') FROM ' || tableName || v_quyCon;

      BEGIN
        EXECUTE IMMEDIATE v_sql
        INTO v_checkCnt;

        EXCEPTION
          WHEN OTHERS THEN
            res := 'FAULT'; /*异常情况返回 NULL*/
      END;

      IF(v_checkCnt > 0) THEN
        res := 'FAULT';
      ELSE
        res := 'SUCCESS';
      END IF;
   END IF;

END PROC_DATACHECK;
