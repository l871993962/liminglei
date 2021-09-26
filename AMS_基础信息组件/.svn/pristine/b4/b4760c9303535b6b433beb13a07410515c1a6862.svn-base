CREATE OR REPLACE FUNCTION F_Express(c_in_Exprs VARCHAR2) RETURN NUMBER IS
  v_sql    VARCHAR(200);
  v_result NUMBER(38, 20);
BEGIN
  /*
  * 计算 算术表达式 i_calculate 字符串的值
  * 采用动态SQL实现
  */
  /*验证，如果表达式以除号开始的话，表达式前需加上‘1’*/
  if (substr(c_in_Exprs, 1, 1) = '/') then
    v_sql := '1' || c_in_Exprs;
  else
    v_sql := c_in_Exprs;
  end if;
  v_sql := 'select  ' || v_sql || ' from dual';
  BEGIN
    EXECUTE IMMEDIATE v_sql
      INTO v_result;
  EXCEPTION
    WHEN OTHERS THEN
      v_result := null; /*异常情况返回 NULL*/
  END;
  RETURN v_result;
END;
