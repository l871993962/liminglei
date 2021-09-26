create or replace function eval_func(t_s in varchar2) return number as
       r number;
     begin
       execute immediate 'select ' || t_s || ' from dual'
         into r;
       return r;
     end;
