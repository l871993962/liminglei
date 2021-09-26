CREATE OR REPLACE PROCEDURE PRO_DAE_REGEX(p_string    IN varchar2,
                                          p_partition IN varchar2,
                                          v_data      in out nocopy VarTableType) AS
  v_str  long DEFAULT p_string || p_partition;
  v_n    VARCHAR2(2000);
BEGIN
  v_data := VarTableType();
  LOOP
    v_n := instr(v_str, p_partition);
    EXIT WHEN(NVL(v_n, 0) = 0);
    v_data.EXTEND;
    v_data(v_data.COUNT) := LTRIM(RTRIM(SUBSTR(v_str, 1, v_n - 1)));
    v_str := SUBSTR(v_str, v_n + 1);
  END LOOP;
END;
