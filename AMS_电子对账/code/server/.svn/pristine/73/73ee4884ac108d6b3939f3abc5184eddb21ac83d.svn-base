CREATE OR REPLACE PROCEDURE SearchKeyWord (KeyWord VARCHAR2)
IS
   v_sql         VARCHAR2 (4000);
   v_tb_column   VARCHAR2 (4000);
   v_cnt         NUMBER (18, 0);

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
                 || '" t WHERE t."'
                 || t1.column_name
                 || '" like ''%'
                 || KeyWord
                 || '%'''
                    AS str
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
      ORDER BY   t1.Table_Name, t1.Column_ID;
BEGIN
   FOR i IN cur
   LOOP
      v_sql := i.str;                               -- 获取将要执行的SQL语句；

      EXECUTE IMMEDIATE v_sql INTO   v_tb_COLUMN, v_cnt;

      IF v_cnt > 0
      THEN
         DBMS_OUTPUT.put_line (   '表：'
                               || SUBSTR (v_tb_column, 1, INSTR (v_tb_column,
                                                                 '.',
                                                                 1,
                                                                 1)
                                                          - 1)
                               || ' 列：'
                               || SUBSTR (v_tb_column, INSTR (v_tb_column,
                                                              '.',
                                                              1,
                                                              1)
                                                       + 1)
                               || '有 '
                               || TO_CHAR (v_cnt)
                               || '条记录含有字串'
                               || KeyWord);
      END IF;
   END LOOP;
EXCEPTION
   WHEN OTHERS
   THEN
      BEGIN
         DBMS_OUTPUT.put_line (v_sql);
         DBMS_OUTPUT.put_line (v_tb_column);
      END;
END SearchKeyWord;
