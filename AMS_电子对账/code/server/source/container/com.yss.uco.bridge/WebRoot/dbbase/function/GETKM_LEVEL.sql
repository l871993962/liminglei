create or replace function GETKM_LEVEL(i_Km_Code in varchar2) return int as
  n_Level number(3) := 1; /*科目级别*/
  n_Km    varchar2(200) := Ltrim(i_Km_Code);
  /*此函数是根据科目代码中的小数点判断生成科目级别，
  使用时请务必确保科目代码中的小数点正确*/
begin
  if (n_Km Is Null) then
    n_Level := 0;
  else
    LOOP
      EXIT WHEN(INSTR(n_Km, '.') = 0);
      n_Level := n_Level + 1;
      n_Km    := substr(n_Km, instr(n_Km, '.') + 1);
    END LOOP;
  end if;
  return n_Level;
end GETKM_LEVEL;
