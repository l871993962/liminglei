CREATE OR REPLACE FUNCTION splitstr(p_string IN VARCHAR2, p_delimiter IN VARCHAR2)
    RETURN str_split
    PIPELINED
AS
    v_length   NUMBER := LENGTH(p_string);
    v_start    NUMBER := 1;
    v_index    NUMBER;
BEGIN
    WHILE(v_start <= v_length)
    LOOP
        v_index := INSTR(p_string, p_delimiter, v_start);

        IF v_index = 0
        THEN
            PIPE ROW(SUBSTR(p_string, v_start));
            v_start := v_length + 1;
        ELSE
            PIPE ROW(SUBSTR(p_string, v_start, v_index - v_start));
            v_start := v_index + 1;
        END IF;
    END LOOP;

    RETURN;
END splitstr;