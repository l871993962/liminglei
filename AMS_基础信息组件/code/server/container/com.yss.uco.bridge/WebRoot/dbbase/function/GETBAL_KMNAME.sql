CREATE OR REPLACE FUNCTION GETBAL_KMNAME(C_KM_CODE   IN VARCHAR2,
                                         C_KM_CODE_T IN VARCHAR2,
                                         C_KM_NAME   IN VARCHAR2)
  RETURN VARCHAR2 AS
  /*根据余额表的明细科目转换科目名称，解决科目名称中有核算元素键的情况*/
  nKmCode   VARCHAR2(200) := C_KM_CODE; /*已转换明细科目代码*/
  nKmCode_t VARCHAR2(200) := C_KM_CODE_T; /*科目模板代码*/
  nKmName   VARCHAR2(200) := C_KM_NAME; /*科目名称*/
  nCode     VARCHAR2(50) := '';
  nCode_t   VARCHAR2(50) := '';
  nLevel    NUMBER(3) := 0;
BEGIN
  IF (nKmCode IS NOT NULL AND nKmCode_t IS NOT NULL AND nKmName IS NOT NULL AND
     getkm_level(nKmCode) = getkm_level(nKmCode_T)) THEN
    nLevel := getkm_level(nKmCode_T);
    LOOP
      EXIT WHEN((INSTR(nKmName, '>') = 0 AND INSTR(nKmName, '<') = 0) OR
                nLevel < 0);
      nCode     := SUBSTR(nKmCode, 1, INSTR(nKmCode, '.') - 1);
      nCode     := NVL(nCode, nKmCode);
      nCode_t   := SUBSTR(nKmCode_t, 1, INSTR(nKmCode_t, '.') - 1);
      nCode_t   := NVL(nCode_t, nKmCode_t);
      nKmCode   := SUBSTR(nKmCode, INSTR(nKmCode, '.') + 1);
      nKmCode_t := SUBSTR(nKmCode_t, INSTR(nKmCode_t, '.') + 1);
      nLevel    := nLevel - 1;
      IF ((INSTR(nCode_t, '>') > 0) AND (INSTR(nCode_t, '<') > 0) AND
         INSTR(nKmName, nCode_t) > 0) THEN
        nKmName := REGEXP_REPLACE(nKmName, nCode_t, nCode);
      END IF;
    END LOOP;
  END IF;
  RETURN nKmName;
END GETBAL_KMNAME;
