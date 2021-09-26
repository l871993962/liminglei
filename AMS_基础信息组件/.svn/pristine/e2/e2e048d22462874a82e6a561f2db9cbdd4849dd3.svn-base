CREATE OR REPLACE PROCEDURE writeLog(iProCode   in varchar2,
                                     iErrorCode in number,
                                     iErrorText in varchar2) as
  vDate date;
begin
  select to_Date(to_Char(sysDate, 'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')
    into vDate
    from dual;
  insert into T_Trans_LOG
    (proCode, ErrorCode, ErrorText, LogTime)
  values
    (iProCode, iErrorCode, iErrorText, vDate);
  commit;
end writeLog;
