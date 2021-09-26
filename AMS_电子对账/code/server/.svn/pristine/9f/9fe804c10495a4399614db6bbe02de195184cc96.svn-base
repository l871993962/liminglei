-- FUNCTION GETDAY_HDAY
CREATE OR REPLACE FUNCTION GETDAY_HDAY (C_HDay_Code varchar2,D_Stand_Date date,N_Interval_Day int,C_Type varchar2) 
 return Date as 
 /*函数说明：通过节假日代码获取指定的日期[查找基准日期前/后N自然/工作日的日期]注：此函数只适用于节假日表中只保存了节假日日期（非工作日记录）的情况 警告：若没有设置范围内的节假日信息，此函数会计算出错误的返回日期 参数说明：D_Stand_Date 基准日期C_MKT_Code 节假日代码N_Interval_Day 间隔天数 大于0向后查找；小于0向前查找；等于0返回基准日期C_Type 类型 'W'工作日；'D'自然日；'DW'自然日递延至工作日*/ 
 D_Return Date; /*返回的日期*/ 
 D_Start Date; /*区间开始日期*/ 
 D_End   Date; /*区间结束日期*/ 
 D_Tmp   Date; 
 N_Count int := 1; 
 C_Hday  varchar2(20) := C_HDay_Code; 
 begin 
   D_Return := D_Stand_Date; 
   /*判断参数是否合法*/ 
   if (('W' != C_Type) AND ('D' != C_Type) and ('DW' != C_Type)) then 
     return D_Return; 
   else 
     if (('D' = C_Type) or ('DW' = C_Type)) then 
       D_Return := D_Stand_Date + N_Interval_Day; 
     else 
       /*工作日的推算*/ 
       D_Start  := D_Stand_Date; 
       D_End    := D_Stand_Date + N_Interval_Day; 
       D_Return := D_End; 
       while (true) loop 
         exit when N_Count = 0; 
         begin 
           if (N_Interval_Day >= 1) then 
             select count(*) 
               into N_Count 
               from T_P_BI_HDAY_SUB a 
              where a.C_HDay_Code = C_Hday 
                and a.D_HDay > D_Start 
                and a.D_HDay <= D_End 
                and a.c_Date_Type = 'H' 
                and a.N_Check_State = 1; 
             if (N_Count > 0) then 
               D_Start  := D_End; 
               D_End    := D_End + N_Count; 
               D_Return := D_End; 
             else 
               exit; /*退出*/ 
             end if; 
           else 
             /*如果是逆序*/ 
             if (D_Start > D_End) then 
               D_Tmp   := D_End; 
               D_End   := D_Start; 
               D_Start := D_Tmp; 
             end if; 
             select count(*) 
              into N_Count 
              from T_P_BI_HDAY_SUB a 
              where a.C_HDay_Code = C_Hday 
                and a.D_HDay >= D_Start 
                and a.D_HDay < D_End 
                and a.c_Date_Type = 'H' 
                and a.N_Check_State = 1; 
             if (N_Count > 0) then 
               D_End    := D_Start; 
               D_Start  := D_End - N_Count; 
               D_Return := D_Start; 
             else 
               exit; /*退出*/ 
             end if; 
           end if; 
         end; 
       end loop; 
     end if; 
     if  ('DW' = C_Type) and (N_Interval_Day>0) then 
        N_Count:=1; 
        while (true) loop 
         exit when N_Count = 0; 
              select count(*) 
               into N_Count 
               from T_P_BI_HDAY_SUB a 
              where a.C_HDay_Code = C_Hday 
                and a.D_HDay = D_Return 
                and a.c_Date_Type = 'H' 
                and a.N_Check_State = 1; 
               if N_Count!=0 then 
                 D_Return:=D_Return+1; 
               end if; 
        end loop; 
     end if; 
   end if; 
   return D_Return; 
 end; 
;

