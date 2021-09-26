CREATE OR REPLACE FUNCTION GETLEVEL_KM(i_Km_Code IN VARCHAR2,
                                       i_level   IN NUMBER) RETURN VARCHAR2 AS
  n_Level  NUMBER(3) := i_level; /*科目级别*/
  n_Len    NUMBER(3) := 0; /*科目长度*/
  n_Km     VARCHAR2(200) := Ltrim(i_Km_Code);
  n_Km_Res VARCHAR2(200) := '';
  /*此函数是通过科目级别找到对应的科目代码[名称]，
  使用时请务必确保科目代码[名称]中的小数点正确*/
BEGIN
  IF (n_Km IS NOT NULL AND n_Level > 0) THEN
    n_Len := INSTR(n_Km, '.', 1, i_level);
    IF (n_Len = 0) THEN
      IF (getkm_level(n_Km) = n_Level) THEN
        /*当取最后一级时*/
        RETURN n_Km;
      END IF;
      n_Len := 1;
    END IF;
    n_Km_Res := SUBSTR(n_Km, 1, n_Len - 1);
  END IF;
  RETURN n_Km_Res;
END GETLEVEL_KM;
