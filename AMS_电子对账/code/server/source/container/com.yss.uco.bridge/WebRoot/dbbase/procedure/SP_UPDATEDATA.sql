create or replace procedure sp_UpdateData(strSDate in varchar2,strEDate in varchar2) authid current_user
IS
sDate date;
eDate date;
fdate date;
strDate varchar2(20);
strsql varchar2(1000);
begin
sDate:=to_date(strSDate,'yyyy-MM-dd');
eDate:=to_date(strEDate,'yyyy-MM-dd');
fdate:=sDate;
while fdate<=eDate loop
    strDate:= to_char(fdate,'yyyyMMdd');
    strsql:='insert into T_D_OD_SJSHQ values(''000000'','||strDate||',1,0,0,1,0,0,0,0,0,0,0,0,0,'''',0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,0,''SJSHQ'','||fdate||',''yangwenqi'',''CHN'',0,''Z'',''SJSHQ'')';
    execute immediate strsql;
    sDate := sDate + 1;
    fdate:=sDate;
end loop;
end sp_UpdateData;
