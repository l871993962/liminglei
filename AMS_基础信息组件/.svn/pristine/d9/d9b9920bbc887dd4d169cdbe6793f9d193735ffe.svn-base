CREATE OR REPLACE FUNCTION F_str2List (p_string IN varchar2)
   RETURN VarTableType
AS
   /*将带有逗号的长字符串转换成表类型的数据，可提高SQL语句的查询效率*/
   v_str    long DEFAULT p_string || ',' ;
   v_n      VARCHAR2 (2000);
   v_data   VarTableType := VarTableType ();
BEGIN
   LOOP
      v_n := instr (v_str, ',');
      EXIT WHEN (NVL (v_n, 0) = 0);
      v_data.EXTEND;
      v_data (v_data.COUNT) := LTRIM (RTRIM (SUBSTR (v_str, 1, v_n - 1)));
      v_str := SUBSTR (v_str, v_n + 1);
   END LOOP;

   RETURN v_data;
END;

 