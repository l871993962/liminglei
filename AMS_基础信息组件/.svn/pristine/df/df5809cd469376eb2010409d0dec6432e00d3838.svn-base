CREATE OR REPLACE PROCEDURE PRC_CHK_SEC_CODE_DATA
IS
   v_sql         VARCHAR2 (4000);
   v_tb_column   VARCHAR2 (4000);
   v_cnt         NUMBER (18, 0);
   v_exe_sql     VARCHAR2(2000);
   c_Table       VARCHAR2(50);
   c_Column      VARCHAR2(50);
   c_Sysdate     VARCHAR2(20);
   c_Exe_Type    VARCHAR2(20);
   c_Exe_Prc     VARCHAR2(50);
   CURSOR cur
   IS
        SELECT      'SELECT '''
                 || '"'
                 || t1.table_name
                 || '"."'
                 || t1.Column_Name
                 || '"'
                 || ''''
                 || ' as col_name, NVL(COUNT(t."'
                 || t1.Column_Name
                 || '"),0) as cnt FROM "'
                 || t1.table_name
                 || '" t WHERE  NOT EXISTS(SELECT * FROM T_P_SV_SEC_BASE m WHERE t.c_sec_code = m.c_sec_code) '
                 || 'AND t."'
                 || t1.COLUMN_NAME
                 || '" <> '' '''
                    AS str,
                  t1.table_name as C_TABLE_NAME,
                  t1.column_name as C_COLUMN_NAME
          FROM         cols t1
                    LEFT JOIN
                       user_col_comments t2
                    ON t1.Table_name = t2.Table_name
                       AND t1.Column_Name = t2.Column_Name
                 LEFT JOIN
                    user_tab_comments t3
                 ON t1.Table_name = t3.Table_name
         WHERE   NOT EXISTS
                    (SELECT   t4.Object_Name
                       FROM   User_objects t4
                      WHERE       t4.Object_Type = 'TABLE'
                              AND t4.Temporary = 'Y'
                              AND t4.Object_Name = t1.Table_Name)
                 AND (   t1.Data_Type = 'CHAR'
                      OR t1.Data_Type = 'VARCHAR2'
                      OR t1.Data_Type = 'VARCHAR')
                 AND t1.COLUMN_NAME = 'C_SEC_CODE'
      ORDER BY   t1.Table_Name, t1.Column_ID;
BEGIN

   c_Exe_Type := 'CHK_SEC_CODE';
   c_Exe_Prc  := 'PRC_CHK_SEC_CODE_DATA';

   DELETE FROM T_CHK_EXCPTION_DATA WHERE C_EXE_TYPE =  'CHK_SEC_CODE';

   COMMIT;

   FOR i IN cur
   LOOP
      v_sql := i.str;                               -- 获取将要执行的SQL语句；

      EXECUTE IMMEDIATE v_sql INTO v_tb_COLUMN, v_cnt;

      IF v_cnt > 0
      THEN

          c_Table := i.c_table_name;
          c_Column    := i.c_column_name;
          c_Sysdate   := sysdate;

            v_exe_sql := 'INSERT INTO T_CHK_EXCPTION_DATA(C_EXE_TYPE,C_EXE_PRC,C_TABLE_NAME,C_COLUMN_NAME,C_EXE_SQL,C_EXCPTION_COUNT,C_EXE_TIME) VALUES('''|| c_Exe_Type || ''',''' || c_Exe_Prc || ''','''
           ||  c_Table || ''',''' || c_Column || ''',''' ||  REPLACE(v_sql,'''','''''') ||  ''',''' || v_cnt || ''',''' || c_Sysdate || ''')';

           --v_exe_sql := 'INSERT INTO T_CHK_EXCPTION_DATA(C_EXE_TYPE,C_EXE_PRC,C_TABLE_NAME,C_COLUMN_NAME,C_EXE_SQL,C_EXCPTION_COUNT,C_EXE_TIME) VALUES(''CHK_EXISTS_NULL'',''PRC_CHK_EXISTS_NULL_DATA'','''
           --||  i.C_TABLE_NAME || ''',''' || i.C_COLUMN_NAME || ''',''' || replace(i.str,''','-'') ||  ''',''' || v_cnt || ''',''' || sysdate || ''')';
           -- DBMS_OUTPUT.put_line (v_exe_sql);
           EXECUTE IMMEDIATE v_exe_sql;
           COMMIT;
      END IF;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      BEGIN
         DBMS_OUTPUT.put_line (v_exe_sql);
         ROLLBACK;
      END;
END PRC_CHK_SEC_CODE_DATA;
