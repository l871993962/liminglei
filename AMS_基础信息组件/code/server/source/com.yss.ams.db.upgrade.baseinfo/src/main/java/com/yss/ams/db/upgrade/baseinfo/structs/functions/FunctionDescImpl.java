package com.yss.ams.db.upgrade.baseinfo.structs.functions;

import com.yss.fast.db.upgrade.support.api.BaseStructDesc;
import com.yss.fast.db.upgrade.support.api.FunctionBuilder;
import com.yss.fast.db.upgrade.support.script.builder.ScriptBuilder;
import com.yss.fast.db.upgrade.support.script.enums.ScriptType;
import com.yss.fast.db.upgrade.support.script.enums.UpdateType;

/**
 * 函数信息构建实现类型
 * 
 * @ClassName FunctionDescImpl
 * @Description
 * @author Jinghehe 
 * @CreateDate 2017-9-9
 * @Version V4.5.0.1
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class FunctionDescImpl extends BaseStructDesc {

	private FunctionBuilder builder = null;

	@Override
	public void execute() throws Exception {
		builder = getFunBuilder();
		///BUG #170674 【机构基本信息】查询条件【主体质】选择代销机构查询不出数据
		buildIS_CONTAIN();
		buildGETDAY_HDAY();
		buildGETFJKMBYMAP();  // STORY #63208 人保资产-导给委托人的底层数据，按照3.0系统方式提供【接口需求16】 
	}
	
	
	/**
	 * STORY #63208 人保资产-导给委托人的底层数据，按照3.0系统方式提供【接口需求16】 
	 */
	private void buildGETFJKMBYMAP(){
		builder.createOrReplaceFunciton("GETFJKMBYMAP").begin_SQL()
		.appendLine(" CREATE OR REPLACE FUNCTION GETFJKMBYMAP ")
		.appendLine("               ( var_fjjdm   IN VARCHAR2, ")
		.appendLine("               var_Km_Code IN VARCHAR2, ")
		.appendLine("               var_len     IN NUMBER) ")
		.appendLine("  return VARCHAR2 is R_FJ_KM VARCHAR2(50); ")
		.appendLine(" R_KM VARCHAR2(50); ")
		.appendLine(" begin ")
		.appendLine("     begin ")
		.appendLine(" select facctcode into R_KM from md_km_map a where a.fjjdm = var_fjjdm and substr(c_km_code,0,var_len) = var_Km_Code ")
		.appendLine(" and trim(facctcode) is not null and rownum = 1; ")
		.appendLine(" exception when no_data_found then R_FJ_KM := var_Km_Code; ")
		.appendLine("  end; ")
		.appendLine(" begin ")
		.appendLine("      if (var_len = 4) then ")
		.appendLine("          R_FJ_KM := substr(R_KM,0,4); ")
		.appendLine("      elsif (var_len = 7) then ")
		.appendLine("          R_FJ_KM := substr(R_KM,0,6); ")
		.appendLine("      elsif (var_len = 10) then ")
		.appendLine("          R_FJ_KM := substr(R_KM,0,8); ")
		.appendLine("      elsif (var_len = 13) then  ")
		.appendLine("          R_FJ_KM := substr(R_KM,0,10); ")
		.appendLine("      elsif (var_len = 17) then  ")
		.appendLine("         R_FJ_KM := substr(R_KM,0,13); ")
		.appendLine("      else							    ")
		.appendLine("         R_FJ_KM := var_Km_Code;       ")
		.appendLine("      end if;						    ")
		.appendLine("      exception when no_data_found  then R_FJ_KM := var_Km_Code; ")
		.appendLine("    end;                               ")
		.appendLine("     return R_FJ_KM;  ")
		.appendLine("  end GETFJKMBYMAP;                     ");
		
	}
	
	/**
	 * BUG #185773 【数据升级组件】GETDAY_HDAY函数需要添加到升级组件
	 * @param builder
	 * @author maxiangfeng
	 * @throws Exception 
	 */
	private void buildGETDAY_HDAY() throws Exception {
		builder.createOrReplaceFunciton("GETDAY_HDAY").begin_SQL().appendLine(" CREATE OR REPLACE FUNCTION GETDAY_HDAY (C_HDay_Code varchar2,D_Stand_Date date,N_Interval_Day int,C_Type varchar2) ")
		.appendLine( " return Date as ")
		.appendLine( " /*函数说明：通过节假日代码获取指定的日期[查找基准日期前/后N自然/工作日的日期]"+
				"注：此函数只适用于节假日表中只保存了节假日日期（非工作日记录）的情况"+
				" 警告：若没有设置范围内的节假日信息，此函数会计算出错误的返回日期"+
				" 参数说明："+
				"D_Stand_Date 基准日期"+
				"C_MKT_Code 节假日代码"+
				"N_Interval_Day 间隔天数 大于0向后查找；小于0向前查找；等于0返回基准日期"+
				"C_Type 类型 'W'工作日；'D'自然日；'DW'自然日递延至工作日*/ ")
		.appendLine(" D_Return Date; /*返回的日期*/ ")
		.appendLine(" D_Start Date; /*区间开始日期*/ ")
		.appendLine(" D_End   Date; /*区间结束日期*/ ")
		.appendLine(" D_Tmp   Date; ")
		.appendLine(" N_Count int := 1; ")
		.appendLine(" C_Hday  varchar2(20) := C_HDay_Code; ")
		.appendLine(" begin ")
		.appendLine("   D_Return := D_Stand_Date; ")
		.appendLine("   /*判断参数是否合法*/ ")
		.appendLine("   if (('W' != C_Type) AND ('D' != C_Type) and ('DW' != C_Type)) then ")
		.appendLine("     return D_Return; ")
		.appendLine("   else ")
		.appendLine("     if (('D' = C_Type) or ('DW' = C_Type)) then ")
		.appendLine("       D_Return := D_Stand_Date + N_Interval_Day; ")
		.appendLine("     else ")
		.appendLine("       /*工作日的推算*/ ")
		.appendLine("       D_Start  := D_Stand_Date; ")
		.appendLine("       D_End    := D_Stand_Date + N_Interval_Day; ")
		.appendLine("       D_Return := D_End; ")
		.appendLine("       while (true) loop ")
		.appendLine("         exit when N_Count = 0; ")
		.appendLine("         begin ")
		.appendLine("           if (N_Interval_Day >= 1) then ")
		.appendLine("             select count(*) ")
		.appendLine("               into N_Count ")
		.appendLine("               from T_P_BI_HDAY_SUB a ")
		.appendLine("              where a.C_HDay_Code = C_Hday ")
		.appendLine("                and a.D_HDay > D_Start ")
		.appendLine("                and a.D_HDay <= D_End ")
		.appendLine("                and a.c_Date_Type = 'H' ")
		.appendLine("                and a.N_Check_State = 1; ")
		.appendLine("             if (N_Count > 0) then ")
		.appendLine("               D_Start  := D_End; ")
		.appendLine("               D_End    := D_End + N_Count; ")
		.appendLine("               D_Return := D_End; ")
		.appendLine("             else ")
		.appendLine("               exit; /*退出*/ ")
		.appendLine("             end if; ")
		.appendLine("           else ")
		.appendLine("             /*如果是逆序*/ ")
		.appendLine("             if (D_Start > D_End) then ")
		.appendLine("               D_Tmp   := D_End; ")
		.appendLine("               D_End   := D_Start; ")
		.appendLine("               D_Start := D_Tmp; ")
		.appendLine("             end if; ")
		.appendLine("             select count(*) ")
		.appendLine("              into N_Count ")
		.appendLine("              from T_P_BI_HDAY_SUB a ")
		.appendLine("              where a.C_HDay_Code = C_Hday ")
		.appendLine("                and a.D_HDay >= D_Start ")
		.appendLine("                and a.D_HDay < D_End ")
		.appendLine("                and a.c_Date_Type = 'H' ")
		.appendLine("                and a.N_Check_State = 1; ")
		.appendLine("             if (N_Count > 0) then ")
		.appendLine("               D_End    := D_Start; ")
		.appendLine("               D_Start  := D_End - N_Count; ")
		.appendLine("               D_Return := D_Start; ")
		.appendLine("             else ")
		.appendLine("               exit; /*退出*/ ")
		.appendLine("             end if; ")
		.appendLine("           end if; ")
		.appendLine("         end; ")
		.appendLine("       end loop; ")
		.appendLine("     end if; ")
		.appendLine("     if  ('DW' = C_Type) and (N_Interval_Day>0) then ")
		.appendLine("        N_Count:=1; ")
		.appendLine("        while (true) loop ")
		.appendLine("         exit when N_Count = 0; ")
		.appendLine("              select count(*) ")
		.appendLine("               into N_Count ")
		.appendLine("               from T_P_BI_HDAY_SUB a ")
		.appendLine("              where a.C_HDay_Code = C_Hday ")
		.appendLine("                and a.D_HDay = D_Return ")
		.appendLine("                and a.c_Date_Type = 'H' ")
		.appendLine("                and a.N_Check_State = 1; ")
		.appendLine("               if N_Count!=0 then ")
		.appendLine("                 D_Return:=D_Return+1; ")
		.appendLine("               end if; ")
		.appendLine("        end loop; ")
		.appendLine("     end if; ")
		.appendLine("   end if; ")
		.appendLine("   return D_Return; ")
		.appendLine(" end; ").end_SQL().build(UpdateType.BUG,  "185773", "GETDAY_HDAY函数需要添加到升级组件", "马向峰", "2017-1-16");
	}
	
	private void buildIS_CONTAIN() throws Exception {
		builder.createOrReplaceFunciton("IS_CONTAIN").begin_SQL().appendLine(" CREATE OR REPLACE FUNCTION isContain(p_stringA in varchar2,p_stringB in varchar2,p_delimiterA in varchar2,p_delimiterB in varchar2) return number as ")
		.appendLine(" /*** 函数说明： ")
		.appendLine("      p_stringA 字符串格式 a,b,c ")
		.appendLine("      p_stringB 字符串格式 a,b,c,d ")
		.appendLine("      p_delimiterA  p_stringA字符串的分隔符 ")
		.appendLine("      p_delimiterB  p_stringB字符串的分隔符 ")
		.appendLine("      用于判断：p_stringA 字符串以逗号分隔的数据是否在 p_stringB 字符串以分隔的数据中包含 ")
		.appendLine("      1   包含 0 不包含 ")
		.appendLine("      ***/ ") 
		.appendLine(" v_lengthA NUMBER := LENGTH(p_stringA); ")
		.appendLine(" v_startA NUMBER := 1; ")
		.appendLine(" v_indexA NUMBER; ")
		.appendLine(" v_strsA varchar2(4000); ")

		.appendLine(" v_lengthB NUMBER := LENGTH(p_stringB); ")
		.appendLine(" v_startB NUMBER := 1; ")
		.appendLine(" v_indexB NUMBER; ")
		.appendLine(" v_strsB varchar2(4000); ")
		.appendLine(" v_result NUMBER :=0;-- 返回结果 默认是 0 ")

		.appendLine("BEGIN ")
		.appendLine(" WHILE(v_startA <= v_lengthA) ")
		.appendLine(" LOOP ")
		.appendLine(" v_indexA := INSTR(p_stringA, p_delimiterA, v_startA); ")
		.appendLine(" IF v_indexA = 0 ")
		.appendLine(" THEN ")
		.appendLine(" v_strsA :=SUBSTR(p_stringA, v_startA); ")
		.appendLine(" v_startA := v_lengthA + 1; ")
		.appendLine(" ELSE ")
		.appendLine(" v_strsA :=SUBSTR(p_stringA, v_startA, v_indexA - v_startA); ")
		.appendLine(" v_startA := v_indexA + 1; ")
		.appendLine(" END IF; ")
		.appendLine(" v_startB := 1; ")
		.appendLine(" v_strsB := ''; ")
		.appendLine("     WHILE(v_startB <= v_lengthB) ")
		.appendLine("     LOOP ")
		.appendLine("     v_indexB := INSTR(p_stringB, p_delimiterB, v_startB); ")
		.appendLine("     IF v_indexB = 0 ")
		.appendLine("     THEN ")
		.appendLine("     v_strsB :=SUBSTR(p_stringB, v_startB); ")
		.appendLine("     v_startB := v_lengthB + 1; ")
		.appendLine("     ELSE ")
		.appendLine("     v_strsB :=SUBSTR(p_stringB, v_startB, v_indexB - v_startB); ")
		.appendLine("     v_startB := v_indexB + 1; ")
		.appendLine("     END IF; ")

		.appendLine("     IF v_strsB = v_strsA then ")
		.appendLine("     return 1; ")
		.appendLine("     end if; ")
		.appendLine("     END LOOP; ")
		.appendLine(" END LOOP; ")
		.appendLine(" RETURN v_result; ")
		.appendLine(" END isContain; ").end_SQL().build(UpdateType.BUG,  "170674", "判断A以分隔符a的字符串分割数据是否在字符串B以分隔符b分割的数据", "井呵呵", "2017-9-9");
	}
}
